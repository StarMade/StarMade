package it.unimi.dsi.fastutil.floats;

public class FloatComparators
{
  public static final FloatComparator NATURAL_COMPARATOR = new AbstractFloatComparator()
  {
    public final int compare(float local_a, float local_b)
    {
      return local_a == local_b ? 0 : local_a < local_b ? -1 : 1;
    }
  };
  public static final FloatComparator OPPOSITE_COMPARATOR = new AbstractFloatComparator()
  {
    public final int compare(float local_a, float local_b)
    {
      return local_b == local_a ? 0 : local_b < local_a ? -1 : 1;
    }
  };
  
  public static FloatComparator oppositeComparator(FloatComparator local_c)
  {
    new AbstractFloatComparator()
    {
      private final FloatComparator comparator = this.val$c;
      
      public final int compare(float local_a, float local_b)
      {
        return -this.comparator.compare(local_a, local_b);
      }
    };
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatComparators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */