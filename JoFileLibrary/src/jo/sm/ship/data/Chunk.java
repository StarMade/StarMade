package jo.sm.ship.data;

import javax.vecmath.Point3i;

public class Chunk
{
    private long        mTimestamp;
    private Point3i    mPosition;
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
    public Point3i getPosition()
    {
        return mPosition;
    }
    public void setPosition(Point3i position)
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
