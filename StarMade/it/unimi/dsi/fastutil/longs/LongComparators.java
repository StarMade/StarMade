package it.unimi.dsi.fastutil.longs;

public class LongComparators
{
  public static final LongComparator NATURAL_COMPARATOR = new AbstractLongComparator()
  {
    public final int compare(long local_a, long local_b)
    {
      return local_a == local_b ? 0 : local_a < local_b ? -1 : 1;
    }
  };
  public static final LongComparator OPPOSITE_COMPARATOR = new AbstractLongComparator()
  {
    public final int compare(long local_a, long local_b)
    {
      return local_b == local_a ? 0 : local_b < local_a ? -1 : 1;
    }
  };
  
  public static LongComparator oppositeComparator(LongComparator local_c)
  {
    new AbstractLongComparator()
    {
      private final LongComparator comparator = this.val$c;
      
      public final int compare(long local_a, long local_b)
      {
        return -this.comparator.compare(local_a, local_b);
      }
    };
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongComparators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */