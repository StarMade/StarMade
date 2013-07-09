import datetime
import os
import struct
import time
import zlib

import binary

from PIL import Image
from PIL import ImageDraw

# This was to indicate the latest version where this file works properly
_smVersion = '0.09375'
_baseDir = os.path.dirname(os.path.abspath(__file__))

# StarMade-09375-src\org\schema\game\common\data\element\ElementKeyMap.java
# Doesn't have the full list of block names...get from the wiki...
_blockNameDict = {
    '6': 'WEAPON_CONTROLLER_ID',
    '16': 'WEAPON_ID',
    '1': 'CORE_ID',
    '65': 'DEATHSTAR_CORE_ID',
    '5': 'HULL_ID',
    '63': 'GLASS_ID',
    '8': 'THRUSTER_ID',
    '7': 'TURRET_DOCK_ID',
    '88': 'TURRET_DOCK_ENHANCE_ID',
    '2': 'POWER_ID',
    '331': 'POWER_CAP_ID',
    '3': 'SHIELD_ID',
    '14': 'EXPLOSIVE_ID',
    '15': 'RADAR_JAMMING_ID',
    '22': 'CLOAKING_ID',
    '24': 'SALVAGE_ID',
    '38': 'MISSILE_DUMB_CONTROLLER_ID',
    '32': 'MISSILE_DUMB_ID',
    '46': 'MISSILE_HEAT_CONTROLLER_ID',
    '40': 'MISSILE_HEAT_ID',
    '54': 'MISSILE_FAFO_CONTROLLER_ID',
    '48': 'MISSILE_FAFO_ID',
    '4': 'SALVAGE_CONTROLLER_ID',
    '56': 'GRAVITY_ID',
    '30': 'REPAIR_ID',
    '39': 'REPAIR_CONTROLLER_ID',
    '47': 'COCKPIT_ID',
    '55': 'LIGHT_ID',
    '62': 'LIGHT_BEACON_ID',
    '64': 'TERRAIN_ICE_ID',
    '69': 'HULL_COLOR_PURPLE_ID',
    '70': 'HULL_COLOR_BROWN_ID',
    '75': 'HULL_COLOR_BLACK_ID',
    '76': 'HULL_COLOR_RED_ID',
    '77': 'HULL_COLOR_BLUE_ID',
    '78': 'HULL_COLOR_GREEN_ID',
    '79': 'HULL_COLOR_YELLOW_ID',
    '112': 'LANDING_ELEMENT',
    '113': 'LIFT_ELEMENT',
    '114': 'RECYCLER_ELEMENT',
    '120': 'STASH_ELEMENT',
    '121': 'AI_ELEMENT',
    '122': 'DOOR_ELEMENT',
    '123': 'BUILD_BLOCK_ID',
    '80': 'TERRAIN_LAVA_ID',
    '128': 'TERRAIN_GOLD_ID',
    '129': 'TERRAIN_IRIDIUM_ID',
    '130': 'TERRAIN_MERCURY_ID',
    '131': 'TERRAIN_PALLADIUM_ID',
    '132': 'TERRAIN_PLATINUM_ID',
    '133': 'TERRAIN_LITHIUM_ID',
    '134': 'TERRAIN_MAGNESIUM_ID',
    '135': 'TERRAIN_TITANIUM_ID',
    '136': 'TERRAIN_URANIUM_ID',
    '137': 'TERRAIN_POLONIUM_ID',
    '72': 'TERRAIN_EXTRANIUM_ID',
    '210': 'TERRAIN_INSANIUNM_ID',
    '209': 'TERRAIN_METATE_ID',
    '208': 'TERRAIN_NEGAGATE_ID',
    '207': 'TERRAIN_QUANTACIDE_ID',
    '206': 'TERRAIN_NEGACIDE_ID',
    '138': 'TERRAIN_MARS_TOP',
    '139': 'TERRAIN_MARS_ROCK',
    '140': 'TERRAIN_MARS_DIRT',
    '73': 'TERRAIN_ROCK_ID',
    '74': 'TERRAIN_SAND_ID',
    '82': 'TERRAIN_EARTH_TOP_DIRT',
    '83': 'TERRAIN_EARTH_TOP_ROCK',
    '84': 'TERRAIN_TREE_TRUNK_ID',
    '85': 'TERRAIN_TREE_LEAF_ID',
    '86': 'TERRAIN_WATER',
    '87': 'TERRAIN_DIRT_ID',
    '85': 'TERRAIN_VINES_ID',
    '89': 'TERRAIN_CACTUS_ID',
    '90': 'TERRAIN_PURPLE_ALIEN_TOP',
    '91': 'TERRAIN_PURPLE_ALIEN_ROCK',
    '92': 'TERRAIN_PURPLE_ALIEN_VINE',
    '86': 'WATER',
    '94': 'PLAYER_SPAWN_MODULE',
    '93': 'TERRAIN_GRASS_SPRITE',
    '98': 'TERRAIN_GRASSFLOWERS_SPRITE',
    '102': 'TERRAIN_TALLGRASSFLOWERS_SPRITE',
    '106': 'TERRAIN_TALLFLOWERS_SPRITE',
    '95': 'TERRAIN_BROWNWEED_SPRITE',
    '103': 'TERRAIN_MINICACTUS_SPRITE',
    '99': 'TERRAIN_LONGWEED_SPRITE',
    '107': 'TERRAIN_ROCK_SPRITE',
    '96': 'TERRAIN_MARSTENTACLES_SPRITE',
    '104': 'TERRAIN_REDSHROOM_SPRITE',
    '100': 'TERRAIN_TALLSHROOM_SPRITE',
    '108': 'TERRAIN_ALIENFLOWERS_SPRITE',
    '97': 'TERRAIN_ALIENVINE_SPRITE',
    '101': 'TERRAIN_PURSPIRE_SPRITE',
    '105': 'TERRAIN_PURPTACLES_SPRITE',
    '109': 'TERRAIN_YHOLE_SPRITE',
    '211': 'FACTORY_INPUT_ID',
    '212': 'FACTORY_INPUT_ENH_ID',
    '213': 'FACTORY_POWER_CELL_ID',
    '214': 'FACTORY_POWER_CELL_ENH_ID',
    '215': 'FACTORY_POWER_COIL_ID',
    '216': 'FACTORY_POWER_COIL_ENH_ID',
    '217': 'FACTORY_POWER_BLOCK_ID',
    '218': 'FACTORY_POWER_BLOCK_ENH_ID',
    '274': 'TERRAIN_ICEPLANET_SURFACE',
    '275': 'TERRAIN_ICEPLANET_ROCK',
    '276': 'TERRAIN_ICEPLANET_WOOD',
    '277': 'TERRAIN_ICEPLANET_LEAVES',
    '278': 'TERRAIN_ICEPLANET_SPIKE_SPRITE',
    '279': 'TERRAIN_ICEPLANET_ICECRAG_SPRITE',
    '280': 'TERRAIN_ICEPLANET_ICECORAL_SPRITE',
    '281': 'TERRAIN_ICEPLANET_ICEGRASS_SPRITE',
    '282': 'LIGHT_RED',
    '283': 'LIGHT_BLUE',
    '284': 'LIGHT_GREEN',
    '285': 'LIGHT_YELLOW',
    '286': 'TERRAIN_ICEPLANET_CRYSTAL',
    '287': 'TERRAIN_REDWOOD',
    '288': 'TERRAIN_REDWOOD_LEAVES',
    '289': 'FIXED_DOCK_ID',
    '290': 'FIXED_DOCK_ID_ENHANCER',
    '291': 'FACTION_BLOCK',
    '292': 'FACTION_HUB_BLOCK',
    '219': 'POWER_CELL',
    '220': 'POWER_COIL',
    '332': 'POWER_DRAIN_BEAM_COMPUTER',
    '333': 'POWER_DRAIN_BEAM_MODULE',
    '334': 'POWER_SUPPLY_BEAM_COMPUTER',
    '335': 'POWER_SUPPLY_BEAM_MODULE',
}

def getBlockName(blockId):
    '''
    Return a block name using _blockNameDict.  If not present, the ID itself is returned as a string.
    '''
    strBlockId = str(blockId)
    if strBlockId in _blockNameDict:
        blockName = _blockNameDict[strBlockId]
    else:
        blockName = strBlockId
    
    return blockName

def readBlueprint(dirName):
    '''
    Read a blueprint directory.  Parse all the files inside the directory.
    '''
    if not os.path.isdir(dirName):
        raise Exception('%s is not a directory' % dirName)
    
    readHeaderFile('%s/header.smbph' % dirName)
    readLogicFile('%s/logic.smbpl' % dirName)
    readMetaFile('%s/meta.smbpm' % dirName)
    
    dataDir = '%s/DATA' % dirName
    
    for df in os.listdir(dataDir):
        readDataFile('%s/%s' % (dataDir, df))
    
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
            short       blockID
            int         blockQuantity
    
    '''
    with open(fileName, 'rb') as f:
        print 'Parsing %s' % fileName
    
        bs = binary.BinaryStream(f)
        
        int_a = bs.readInt32()
        int_b = bs.readInt32()
        float3f_a = (bs.readFloat(), bs.readFloat(), bs.readFloat())
        float3f_b = (bs.readFloat(), bs.readFloat(), bs.readFloat())
        
        width = abs(float3f_a[0] - float3f_b[0])
        height = abs(float3f_a[1] - float3f_b[1])
        length = abs(float3f_a[2] - float3f_b[2])
        
        volume = width * height * length
        
        blockTableLen = bs.readInt32()
        blockDict = {}
        
        for i in range(0, blockTableLen):
            index = bs.readInt16()
            qty = bs.readInt32()
            
            blockDict[index] = qty
        
        eof = bs.readByte()
        if eof != '':
            print 'Warning: EOF not reached'
        
        print 'int_a:', int_a
        print 'int_b:', int_b
        print 'float3f_a:', float3f_a
        print 'float3f_b:', float3f_b
        print 'blockTableLen:', blockTableLen
        
        print 'width:', width
        print 'height:', height
        print 'length:', length
        print 'volume:', volume
        numBlocks = 0
        
        print '\nNAME (ID)\t-> QTY'
        
        for blockId in blockDict:
            blockName = getBlockName(blockId)
            
            qty = blockDict[blockId]
            numBlocks += qty

            print '%s (%d)\t-> %d' % (blockName, blockId, qty)

        print 'numBlocks:', numBlocks

def readLogicFile(fileName):
    '''
    Read a blueprint logic file (.smbpl)
    
    Has to do with linking controllable blocks?
    
    '''
    with open(fileName, 'rb') as f:
        print 'Parsing %s' % fileName
        
        bs = binary.BinaryStream(f)
        
        int_a = bs.readInt32()
        numControllerBlocks = bs.readInt32()
        
        print 'int_a:', int_a
        print 'numControllerBlocks:', numControllerBlocks
        
        for i in range(0, numControllerBlocks):
            k = bs.readInt16()
            m = bs.readInt16()
            s = bs.readInt16()
            
            numGroups = bs.readInt32()
            
            print 'numGroups:', numGroups
            
            for j in range(0, numGroups):
                tag = bs.readInt16()
                numBlocks = bs.readInt32()
                
                print '%s (%d)' % (getBlockName(tag), tag)
                print 'numBlocks:', numBlocks
                
                for ii in range(0, numBlocks):
                    q = (bs.readInt16(), bs.readInt16(), bs.readInt16())
        
        eof = bs.readByte()
        if eof != '':
            print 'Warning: EOF not reached'

def readMetaFile(fileName):
    '''
    Read a blueprint meta file (.smbpm)
    
    not implemented
    
    '''
    with open(fileName, 'rb') as f:
        print 'Parsing %s' % fileName

def readDataFile(fileName):
    '''
    Read a starmade data file (.smd2)
    
     start     type
        0         int                       unknown int
        4         int[8192]                 unknown 32KB section
        32772     int[8192]                 unknown 32KB section
        65540     chunkData[]               5120 byte chunks
        
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
    flen = os.path.getsize(fileName)

    with open(fileName, 'rb') as f:
        print 'Parsing %s' % fileName
        
        print 'flen:',flen
        numChunks = (flen-4-32768-32768)/5120
        print 'num chunks:', numChunks
        
        bs = binary.BinaryStream(f)
        
        header_a = bs.readInt32()
        print 'headerint_a:', header_a
        
        # First 32KB area
        for i in range(0, 0x8000, 8):
            int_a = bs.readInt32()
            int_b = bs.readInt32()
            
            if (int_b != 0 and int_b != -1) or int_a != -1:
                print 'at',i,i/8,int_a,int_b
                
        # Second 32KB area
        for i in range(0, 0x8000, 8):
            int_a = bs.readInt32()
            int_b = bs.readInt32()
        
        totalBlocks = 0
        
        
        for chunk in range(0, numChunks):
            int64_a = bs.readInt64()
            q = (bs.readInt32(), bs.readInt32(), bs.readInt32())
            type = bs.readChar()
            inlen = bs.readInt32()
            
            print 'int64_a:', int64_a
            print 'q:', q
            print 'type:', type
            print 'len:', inlen
            
            timeMillis = int64_a / 1000.0
            strTime = datetime.datetime.fromtimestamp(int(timeMillis)).strftime('%Y-%m-%d %H:%M:%S')
            
            print 'time:', strTime
            
            indata = bs.readBytes(5120-25)
            outdata = zlib.decompress(indata)
            
            nonZeroBlocks = 0

            for block in range(0,12288,3):
                data = struct.unpack('>i', '\x00' + outdata[block:block+3])[0]
                blockId = data & 0x7FF
                hp = (data >> 12) & 0x1F
                orient = (data >> 22) & 0x7
                
                linpos = block / 3
                
                pos = (linpos % 16, (linpos / 16) % 16, linpos / 256)

                if blockId != 0:
                    blockName = getBlockName(blockId)
                    print '0x%06X (id: %s (%d), hp: %d, orientation: %d, linpos: %d, pos: %s)' % (data, blockName, blockId, hp, orient, linpos, pos)
                    
                    nonZeroBlocks += 1
            
            print 'nonZeroBlocks:', nonZeroBlocks
            
            totalBlocks += nonZeroBlocks
        
        print 'totalBlocks:', totalBlocks
        
        eof = bs.readByte()
        if eof != '':
            print 'Warning: EOF not reached'
            

def readSmEntFile(fileName):
    # For exported blueprints, these are just zipped blueprint folders
    pass
    