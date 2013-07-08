package it.unimi.dsi.fastutil.shorts;

public class ShortComparators
{
  public static final ShortComparator NATURAL_COMPARATOR = new AbstractShortComparator()
  {
    public final int compare(short local_a, short local_b)
    {
      return local_a == local_b ? 0 : local_a < local_b ? -1 : 1;
    }
  };
  public static final ShortComparator OPPOSITE_COMPARATOR = new AbstractShortComparator()
  {
    public final int compare(short local_a, short local_b)
    {
      return local_b == local_a ? 0 : local_b < local_a ? -1 : 1;
    }
  };
  
  public static ShortComparator oppositeComparator(ShortComparator local_c)
  {
    new AbstractShortComparator()
    {
      private final ShortComparator comparator = this.val$c;
      
      public final int compare(short local_a, short local_b)
      {
        return -this.comparator.compare(local_a, local_b);
      }
    };
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortComparators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */