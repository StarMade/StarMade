package it.unimi.dsi.fastutil.doubles;

public class DoubleComparators
{
  public static final DoubleComparator NATURAL_COMPARATOR = new AbstractDoubleComparator()
  {
    public final int compare(double local_a, double local_b)
    {
      return local_a == local_b ? 0 : local_a < local_b ? -1 : 1;
    }
  };
  public static final DoubleComparator OPPOSITE_COMPARATOR = new AbstractDoubleComparator()
  {
    public final int compare(double local_a, double local_b)
    {
      return local_b == local_a ? 0 : local_b < local_a ? -1 : 1;
    }
  };
  
  public static DoubleComparator oppositeComparator(DoubleComparator local_c)
  {
    new AbstractDoubleComparator()
    {
      private final DoubleComparator comparator = this.val$c;
      
      public final int compare(double local_a, double local_b)
      {
        return -this.comparator.compare(local_a, local_b);
      }
    };
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleComparators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */