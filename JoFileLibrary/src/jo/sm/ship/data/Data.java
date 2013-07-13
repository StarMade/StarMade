package jo.sm.ship.data;

public class Data
{
    private int     mUnknown1;
    private byte[]  mOffsetSizeTable;
    private byte[]  mTimestampTable;
    private Chunk[] mChunks;

    public int getUnknown1()
    {
        return mUnknown1;
    }

    public void setUnknown1(int unknown1)
    {
        mUnknown1 = unknown1;
    }

    public byte[] getOffsetSizeTable()
    {
        return mOffsetSizeTable;
    }

    public void setOffsetSizeTable(byte[] unknown2)
    {
        mOffsetSizeTable = unknown2;
    }

    public byte[] getTimestampTable()
    {
        return mTimestampTable;
    }

    public void setTimestampTable(byte[] unknown3)
    {
        mTimestampTable = unknown3;
    }

    public Chunk[] getChunks()
    {
        return mChunks;
    }

    public void setChunks(Chunk[] chunks)
    {
        mChunks = chunks;
    }
}
