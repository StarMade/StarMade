import datetime
import os
import pprint
import struct
import time
import zlib

from sets import Set

import binary

# This was to indicate the latest version where this file works properly
_smVersion = '0.09375'
_baseDir = os.path.dirname(os.path.abspath(__file__))

def readBlueprint(dirName):
    '''
    Read a blueprint directory.  Parse all the files inside the directory.
    '''
    if not os.path.isdir(dirName):
        raise Exception('%s is not a directory' % dirName)
    
    data = {}
    
    data['header'] = readHeaderFile('%s/header.smbph' % dirName)
    data['logic'] = readLogicFile('%s/logic.smbpl' % dirName)
    data['meta'] = readMetaFile('%s/meta.smbpm' % dirName)
    
    dataDir = '%s/DATA' % dirName
    
    for df in os.listdir(dataDir):
        data[df] = readDataFile('%s/%s' % (dataDir, df))
    
    return data

def printBlueprint(dirName):
    pprint.pprint(readBlueprint(dirName))
    
def readHeaderFile(fileName):
    '''
    Read a blueprint header file (.smbph)
    
    Header file containing:
        start     type
        0         int                       unknown int
        4         int                       unknown int
        8         float[3]                 3d float vector (bounding box of ship)
        20        float[3]                 3d float fector (bounding box of ship)
        32        int                       number of block table entries (N)
        36        blockEntry[N]             block entry
        
        blockEntry is a 6 byte value
            0   short       blockID
            2   int         blockQuantity
    
    '''
    
    retval = {}
    
    with open(fileName, 'rb') as f:
        bs = binary.BinaryStream(f)
        
        retval['int_a'] = bs.readInt32()
        retval['int_b'] = bs.readInt32()
        retval['bounds_a'] = (bs.readFloat(), bs.readFloat(), bs.readFloat())
        retval['bounds_b'] = (bs.readFloat(), bs.readFloat(), bs.readFloat())
        
        blockTableLen = bs.readInt32()
        retval['blocks'] = {}
        
        for i in range(0, blockTableLen):
            index = bs.readInt16()
            qty = bs.readInt32()
            
            retval['blocks'][index] = qty
        
        eof = bs.readByte()
        if eof != '':
            print 'Warning: EOF not reached'

    return retval

def readLogicFile(fileName):
    '''
    Read a blueprint logic file (.smbpl)
    
    The file describes links between controllers and the associated blocks.    

        start   type
        0       int                         unknown int
        4       int                         numControllers (N)
        8       controllerEntry[N]
        
        controllerEntry is a variable length struct
            0   short[3]    Position of the controller block, for example the core is defined at (8, 8, 8)
            12  int         Number of groups of controlled blocks.  (M)
            16  groupEntry[M]
        
        groupEntry is a variable length struct
            0   short       Block ID for all blocks in this group
            2   int         Number of blocks in the group (I)
            6   short[3][I] Array of blocks positions for each of the I blocks
    
    FOR EXAMPLE, a file might have this data:
    
    numControllers = 2
    controllerEntry[0] (will be the ship core)
        position: (8,8,8) (since it's the ship core)
        numGroups: 2
        group[0]
            blockID: 4 (Salvager computer)
            numBlocks: 1 (# of salvage computer on the ship)
            blockArray: (8,8,13) (1 entry containing 3 shorts, which is the position of the salvage computer)
        group[1]
            blockID: 8 (Thruster)
            numBlocks: 12 (# of thrusters)
            blockArray: (12 entries containing 3 shorts each, the position of each thruster)
    controllerEntry[1] (the salvager computer)
        position: (8,8,13)
        numGroups: 1
        group[0]
            blockID: 24 (Salvage cannon)
            numBlocks: 10
            blockArray: (10 entries containing 3 shorts each, the position of each salvage cannon)
    
    
    '''
    retval = {}
    
    with open(fileName, 'rb') as f:
        print 'Parsing %s' % fileName
        
        bs = binary.BinaryStream(f)
        
        retval['int_a'] = bs.readInt32()
        numControls = bs.readInt32()
        retval['controllers'] = []
        
        for i in range(0, numControls):
            dict = {}
        
            dict['pos'] = (bs.readInt16(), bs.readInt16(), bs.readInt16())
            
            numGroups = bs.readInt32()
            dict['q'] = {}
            
            for j in range(0, numGroups):
                tag = bs.readInt16()
                numBlocks = bs.readInt32()
                
                dict['q'][tag] = []
                
                for ii in range(0, numBlocks):
                    dict['q'][tag].append((bs.readInt16(), bs.readInt16(), bs.readInt16()))
            
            retval['controllers'].append(dict)
        
        eof = bs.readByte()
        if eof != '':
            print 'Warning: EOF not reached'
    
    return retval

def readMetaFile(fileName):
    '''
    Read a blueprint meta file (.smbpm)
    
    start     type
        0       int             unknown int
        4       byte            unknown byte
        5       int             number of dockEntry (docked ship/turrets)
        9       dockEntry[N]    data about each docked ship/turret
        vary    byte            unknown byte
        vary    short           specifies if GZIP compression is used on the tagStruct
        vary    tagStruct[]     additional metadata in a tag structure
        
        
        dockEntry is a variable length struct
        start       type
            0       int             length of the string giving attached ship's subfolder
            4       wchar[N]        ship subfolder string given in modified UTF-8 encoding
            vary    int[3]          q vector, the location of the dock block
            vary    float[3]        a vector, ???
            
        tagStruct encodes variety of data types in a tree structure
        start       type
            0       byte            tag type
            1       int             length of the tag name string
            5       wchar[N]        tag name string in modified UTF-8 encoding
            vary    vary            tag data
            
        special tag types (see code for full list and encoding):
            0x0     End of tag struct marker -- no tag name or data follows this
            0xD     Start of new tag struct
            0xE     Serialized object (not yet implemented here)
    
    '''

    TAG_BYTE = 0x1
    TAG_SHORT = 0x2
    TAG_INT = 0x3
    TAG_LONG = 0x4
    TAG_FLOAT = 0x5
    TAG_DOUBLE = 0x6
    TAG_BYTEARRAY = 0x7
    TAG_STRING = 0x8
    TAG_FLOAT3 = 0x9
    TAG_INT3 = 0xA
    TAG_BYTE3 = 0xB
    TAG_LIST = 0xC
    TAG_STRUCT = 0xD
    # TAG_SERIALIZABLE = 0xE
    
    def parseTagData(bs, type):
      
        data = None
        if type == TAG_BYTE:
            data = bs.readChar()
        elif type == TAG_SHORT:
            data = bs.readInt16()
        elif type == TAG_INT:
            data = bs.readInt32()
        elif type == TAG_LONG:
            data = bs.readInt64()
        elif type == TAG_FLOAT:
            data = bs.readFloat()
        elif type == TAG_DOUBLE:
            data = bs.readDouble()
        elif type == TAG_BYTEARRAY:
            data = []
            len = bs.readInt32()
            for i in range(0, len):
                data.append(bs.readChar())
        elif type == TAG_STRING:
            data = bs.readString()
        elif type == TAG_FLOAT3:
            data = (bs.readFloat(), bs.readFloat(), bs.readFloat())
        elif type == TAG_INT3:
            data = (bs.readInt32(), bs.readInt32(), bs.readInt32())
        elif type == TAG_BYTE3:
            data = (bs.readChar(), bs.readChar(), bs.readChar())
        elif type == TAG_LIST:
            data = []
            next = bs.readChar()
            len = bs.readInt32()
            
            for i in range(0, len):
                data.append(parseTagData(bs, next))
            
        elif type == TAG_STRUCT:
            data = []
            while True:
                next = bs.readChar()
                #print 'next',next
                if next:
                    data.append({
                        'name': bs.readString(),
                        'data': parseTagData(bs, next)
                    })
                else:
                    break
        else:
            print 'WARNING: Unrecognized tag type %d' % type

        return data

    retval = {}
    
    with open(fileName, 'rb') as f:
        print 'Parsing %s' % fileName
        
        bs = binary.BinaryStream(f)
        
        retval['int_a'] = bs.readInt32()
        retval['byte_a'] = bs.readChar()
        
        numDocked = bs.readInt32()
        
        retval['docked'] = []
        
        for i in range(0, numDocked):
            name = bs.readString()
            q = (bs.readInt32(), bs.readInt32(), bs.readInt32())
            a = (bs.readFloat(), bs.readFloat(), bs.readFloat())
            docking = bs.readInt16()
            end = bs.readChar()
            
            retval['docked'].append({
                'name': name,
                'q': q,
                'a': a,
                'dockID': docking,
            })
        
        retval['byte_b'] = bs.readChar()
        retval['short_b'] = bs.readInt16()
        
        
        type = bs.readChar()
        data = {}
        name = None
        if type:
            name = bs.readString()
            data = parseTagData(bs, type)
        
        retval['data'] = {
            'name': name,
            'data': data
        }
        
        eof = bs.readByte()
        if eof != '':
            print 'Warning: EOF not reached'
    
    return retval

def readDataFile(fileName):
    '''
    Read a starmade data file (.smd2)
    
    This function will probably be really inefficient for large ships!
    
     start     type
        0         int                       unknown int
        4         chunkIndex[16][16][16]      chunkIndex struct (see below) arranged in a 16x16x16 array
        32772     long[16][16][16]          chunk timestamp information arranged in a 16x16x16 array
        65540     chunkData[]               5120 byte chunks

        Note that there may exist chunk timestamps and chunks that are not referenced in the chunkIndex table and seemingly serve no purpose.
        
        chunkIndex is an 8 byte struct
            int     chunkId     Index into the chunkData[] array.  If no chunk exists for this point in the array, chunkId = -1
            int     chunklen    The total chunk size in bytes (excluding the zero padding).  Equal to the chunk's "inlen" field plus 25 (the chunk header size)
        
        chunkData is a 5120 byte structure
            long    timestamp   Unix timestamp in milliseconds
            int[3]  q           Relative chunk position
            int     type        Chunk type (?) usually 0x1
            int     inlen       Compressed data length
            byte    data[inlen] ZLIB-compressed data of rawChunkData[16][16][16]
            byte    padding[]   Zero padded to 5120 byte boundary
        
        rawChunkData is a 3 byte bitfield
            Bits
            23-21   orientation
            20-13   hitpoints
            12      isActive
            11-0    blockID
        
    '''
    retval = {}
    flen = os.path.getsize(fileName)

    with open(fileName, 'rb') as f:
        print 'Parsing %s' % fileName
        
        retval['filelen'] = flen
        numChunks = (flen-4-32768-32768)/5120
        
        bs = binary.BinaryStream(f)
        
        retval['int_a'] = bs.readInt32()
        
        retval['chunk_index'] = {}
        retval['chunk_timestamps'] = {}
        
        # First 32KB area
        for i in range(0, 4096):
            chunkId = bs.readInt32()
            chunkLen = bs.readInt32()
            
            if chunkId != -1:
                pos = (i % 16, (i / 16) % 16, i / 256)
                pos = (16 * (pos[0] - 8), 16 * (pos[1] - 8), 16 * (pos[2] - 8))
                
                retval['chunk_index'][pos] = {
                    'id': chunkId,
                    'len': chunkLen
                }
                
        # Second 32KB area
        for i in range(0, 4096):
            timestamp = bs.readInt64()
            
            if timestamp:
                pos = (i % 16, (i / 16) % 16, i / 256)
                pos = (16 * (pos[0] - 8), 16 * (pos[1] - 8), 16 * (pos[2] - 8))
            
                retval['chunk_timestamps'][pos] = timestamp
        
        retval['chunks'] = []
        for chunk in range(0, numChunks):
            chunkDict = {}
        
            chunkDict['timestamp'] = bs.readInt64()
            chunkDict['pos'] = (bs.readInt32(), bs.readInt32(), bs.readInt32())
            chunkDict['type'] = bs.readChar()
            inlen = bs.readInt32()
            
            indata = bs.readBytes(5120-25)
            outdata = zlib.decompress(indata)

            chunkDict['blocks'] = {}
            for block in range(0,16*16*16):
                idx = block*3
                data = struct.unpack('>i', '\x00' + outdata[idx:idx+3])[0]
                blockId = data & 0x7FF
                
                if blockId:
                    pos = (block % 16, (block / 16) % 16, block / 256)
                    chunkDict['blocks'][pos] = {
                        'id': blockId,
                        'hp': (data >> 12) & 0x1F,
                        'orient':  (data >> 22) & 0x7
                    }
            
            retval['chunks'].append(chunkDict)
        
        eof = bs.readByte()
        if eof != '':
            print 'Warning: EOF not reached'
    
    return retval

def readSmEntFile(fileName):
    # For exported blueprints, these are just zipped blueprint folders
    pass
    