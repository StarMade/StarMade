package it.unimi.dsi.fastutil.objects;

import java.util.Comparator;

public class ObjectComparators
{
  public static final Comparator NATURAL_COMPARATOR = new Comparator()
  {
    public final int compare(Object local_a, Object local_b)
    {
      return ((Comparable)local_a).compareTo(local_b);
    }
  };
  public static final Comparator OPPOSITE_COMPARATOR = new Comparator()
  {
    public final int compare(Object local_a, Object local_b)
    {
      return ((Comparable)local_b).compareTo(local_a);
    }
  };
  
  public static <K> Comparator<K> oppositeComparator(Comparator<K> local_c)
  {
    new Comparator()
    {
      private final Comparator<K> comparator = this.val$c;
      
      public final int compare(K local_a, K local_b)
      {
        return -this.comparator.compare(local_a, local_b);
      }
    };
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectComparators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */