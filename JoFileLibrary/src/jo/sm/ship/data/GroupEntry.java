package jo.sm.ship.data;

import javax.vecmath.Point3s;


public class GroupEntry
{
    private short      mBlockID;
    private Point3s[] mBlocks;

    public short getBlockID()
    {
        return mBlockID;
    }

    public void setBlockID(short blockID)
    {
        mBlockID = blockID;
    }

    public Point3s[] getBlocks()
    {
        return mBlocks;
    }

    public void setBlocks(Point3s[] blocks)
    {
        mBlocks = blocks;
    }
}
