package it.unimi.dsi.fastutil.ints;

public class IntComparators
{
  public static final IntComparator NATURAL_COMPARATOR = new AbstractIntComparator()
  {
    public final int compare(int local_a, int local_b)
    {
      return local_a == local_b ? 0 : local_a < local_b ? -1 : 1;
    }
  };
  public static final IntComparator OPPOSITE_COMPARATOR = new AbstractIntComparator()
  {
    public final int compare(int local_a, int local_b)
    {
      return local_b == local_a ? 0 : local_b < local_a ? -1 : 1;
    }
  };
  
  public static IntComparator oppositeComparator(IntComparator local_c)
  {
    new AbstractIntComparator()
    {
      private final IntComparator comparator = this.val$c;
      
      public final int compare(int local_a, int local_b)
      {
        return -this.comparator.compare(local_a, local_b);
      }
    };
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntComparators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */