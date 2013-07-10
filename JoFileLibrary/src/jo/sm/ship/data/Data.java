package jo.sm.ship.data;

public class Data
{
    private int     mUnknown1;
    private byte[]  mUnknown2;
    private byte[]  mUnknown3;
    private Chunk[] mChunks;

    public int getUnknown1()
    {
        return mUnknown1;
    }

    public void setUnknown1(int unknown1)
    {
        mUnknown1 = unknown1;
    }

    public byte[] getUnknown2()
    {
        return mUnknown2;
    }

    public void setUnknown2(byte[] unknown2)
    {
        mUnknown2 = unknown2;
    }

    public byte[] getUnknown3()
    {
        return mUnknown3;
    }

    public void setUnknown3(byte[] unknown3)
    {
        mUnknown3 = unknown3;
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
