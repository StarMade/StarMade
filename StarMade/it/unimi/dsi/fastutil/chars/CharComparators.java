package it.unimi.dsi.fastutil.chars;

public class CharComparators
{
  public static final CharComparator NATURAL_COMPARATOR = new AbstractCharComparator()
  {
    public final int compare(char local_a, char local_b)
    {
      return local_a == local_b ? 0 : local_a < local_b ? -1 : 1;
    }
  };
  public static final CharComparator OPPOSITE_COMPARATOR = new AbstractCharComparator()
  {
    public final int compare(char local_a, char local_b)
    {
      return local_b == local_a ? 0 : local_b < local_a ? -1 : 1;
    }
  };
  
  public static CharComparator oppositeComparator(CharComparator local_c)
  {
    new AbstractCharComparator()
    {
      private final CharComparator comparator = this.val$c;
      
      public final int compare(char local_a, char local_b)
      {
        return -this.comparator.compare(local_a, local_b);
      }
    };
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharComparators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */