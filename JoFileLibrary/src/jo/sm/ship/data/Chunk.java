package jo.sm.ship.data;

import jo.sm.data.Vector3i;

public class Chunk
{
    private long        mTimestamp;
    private Vector3i    mPosition;
    private int         mType;
    private Block[][][] mBlocks;
    public long getTimestamp()
    {
        return mTimestamp;
    }
    public void setTimestamp(long timestamp)
    {
        mTimestamp = timestamp;
    }
    public Vector3i getPosition()
    {
        return mPosition;
    }
    public void setPosition(Vector3i position)
    {
        mPosition = position;
    }
    public int getType()
    {
        return mType;
    }
    public void setType(int type)
    {
        mType = type;
    }
    public Block[][][] getBlocks()
    {
        return mBlocks;
    }
    public void setBlocks(Block[][][] blocks)
    {
        mBlocks = blocks;
    }
}
