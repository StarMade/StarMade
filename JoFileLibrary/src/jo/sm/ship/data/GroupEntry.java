package jo.sm.ship.data;

import jo.sm.data.Vector3s;

public class GroupEntry
{
    private short      mBlockID;
    private Vector3s[] mBlocks;

    public short getBlockID()
    {
        return mBlockID;
    }

    public void setBlockID(short blockID)
    {
        mBlockID = blockID;
    }

    public Vector3s[] getBlocks()
    {
        return mBlocks;
    }

    public void setBlocks(Vector3s[] blocks)
    {
        mBlocks = blocks;
    }
}
