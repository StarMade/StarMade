/*  1:   */package it.unimi.dsi.fastutil.objects;
/*  2:   */
/*  3:   */import java.util.Comparator;
/*  4:   */
/* 49:   */public class ObjectComparators
/* 50:   */{
/* 51:51 */  public static final Comparator NATURAL_COMPARATOR = new Comparator() {
/* 52:   */    public final int compare(Object a, Object b) {
/* 53:53 */      return ((Comparable)a).compareTo(b);
/* 54:   */    }
/* 55:   */  };
/* 56:   */  
/* 58:58 */  public static final Comparator OPPOSITE_COMPARATOR = new Comparator() {
/* 59:   */    public final int compare(Object a, Object b) {
/* 60:60 */      return ((Comparable)b).compareTo(a);
/* 61:   */    }
/* 62:   */  };
/* 63:   */  
/* 67:   */  public static <K> Comparator<K> oppositeComparator(Comparator<K> c)
/* 68:   */  {
/* 69:69 */    new Comparator() {
/* 70:70 */      private final Comparator<K> comparator = this.val$c;
/* 71:   */      
/* 72:72 */      public final int compare(K a, K b) { return -this.comparator.compare(a, b); }
/* 73:   */    };
/* 74:   */  }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectComparators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */