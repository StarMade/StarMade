package it.unimi.dsi.fastutil.bytes;

public class ByteComparators
{
  public static final ByteComparator NATURAL_COMPARATOR = new AbstractByteComparator()
  {
    public final int compare(byte local_a, byte local_b)
    {
      return local_a == local_b ? 0 : local_a < local_b ? -1 : 1;
    }
  };
  public static final ByteComparator OPPOSITE_COMPARATOR = new AbstractByteComparator()
  {
    public final int compare(byte local_a, byte local_b)
    {
      return local_b == local_a ? 0 : local_b < local_a ? -1 : 1;
    }
  };
  
  public static ByteComparator oppositeComparator(ByteComparator local_c)
  {
    new AbstractByteComparator()
    {
      private final ByteComparator comparator = this.val$c;
      
      public final int compare(byte local_a, byte local_b)
      {
        return -this.comparator.compare(local_a, local_b);
      }
    };
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteComparators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */