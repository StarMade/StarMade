package jo.sm.ship.data;

/*
 * +        blockEntry is a 6 byte value
 +            0   short       blockID
 +            2   int         blockQuantity 
 */
public class BlockEntry
{
    private short mBlockID;
    private int mBlockQuantity;

    public short getBlockID()
    {
        return mBlockID;
    }

    public void setBlockID(short blockID)
    {
        mBlockID = blockID;
    }

    public int getBlockQuantity()
    {
        return mBlockQuantity;
    }

    public void setBlockQuantity(int blockQuantity)
    {
        mBlockQuantity = blockQuantity;
    }
}
