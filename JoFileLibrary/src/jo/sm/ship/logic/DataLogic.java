package jo.sm.ship.logic;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.InflaterInputStream;

import jo.sm.logic.ByteUtils;
import jo.sm.logic.IOLogic;
import jo.sm.ship.data.Block;
import jo.sm.ship.data.Chunk;
import jo.sm.ship.data.Data;

public class DataLogic 
{
    public static Data readFile(InputStream is, boolean close) throws IOException
	{
		DataInputStream dis;
		if (is instanceof DataInputStream)
			dis = (DataInputStream)is;
		else
			dis = new DataInputStream(is);
		Data data = new Data();
		data.setUnknown1(dis.readInt());
		byte[] unknown2 = new byte[32768];
		dis.readFully(unknown2);
		data.setUnknown2(unknown2);
		//System.out.println(ByteUtils.toStringDump(unknown2));
        byte[] unknown3 = new byte[32768];
        dis.readFully(unknown3);
        data.setUnknown3(unknown3);
        //System.out.println(ByteUtils.toStringDump(unknown3));
        List<Chunk> chunks = new ArrayList<Chunk>();
        for (;;)
        {
            byte[] chunkData = new byte[5120];
            try
            {
                dis.readFully(chunkData);
            }
            catch (EOFException e)
            {
                break;
            }
            //System.out.println(ByteUtils.toStringDump(chunkData));
            DataInputStream dis2 = new DataInputStream(new ByteArrayInputStream(chunkData));
            Chunk chunk = new Chunk();
            chunk.setTimestamp(dis2.readLong());
            chunk.setPosition(IOLogic.readVector3i(dis2));
            chunk.setType(dis2.readByte());
            int compressedLen = dis2.readInt();
            //System.out.println("CompressedLen="+compressedLen);
            byte[] compressedData = new byte[compressedLen];
            dis2.readFully(compressedData);
            DataInputStream dis3 = new DataInputStream(new InflaterInputStream(new ByteArrayInputStream(compressedData)));
            Block[][][] blocks = new Block[16][16][16];
            byte[] inbuf = new byte[3];
            for (int z = 0; z < 16; z++)
                for (int y = 0; y < 16; y++)
                    for (int x = 0; x < 16; x++)
                    {
                        blocks[x][y][z] = new Block();
                        dis3.readFully(inbuf);
                        int bitfield = toUnsignedInt(inbuf);
                        blocks[x][y][z].setBlockID((short)((bitfield>>0)&0x7ff));
                        blocks[x][y][z].setActive(((bitfield>>11)&0x1) == 1);
                        blocks[x][y][z].setHitPoints((short)((bitfield>>12)&0xff));
                        blocks[x][y][z].setOrientation((short)((bitfield>>20)&0x7));
                    }
            chunk.setBlocks(blocks);
            chunks.add(chunk);
        }
        data.setChunks(chunks.toArray(new Chunk[0]));
        if (close)
            dis.close();
		return data;
	}
    
    private static int toUnsignedInt(byte[] bytes)
    {
        return toUnsignedInt(bytes, 0, bytes.length);
    }
    
    private static int toUnsignedInt(byte[] bytes, int o, int l)
    {
        long v = 0;
        for (int i = 0; i < l; i++)
            v = (v<<8) | (bytes[o + i]&0xff);
        return (int)v;
    }
}
