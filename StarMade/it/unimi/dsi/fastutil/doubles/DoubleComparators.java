/*  1:   */package it.unimi.dsi.fastutil.doubles;
/*  2:   */
/* 49:   */public class DoubleComparators
/* 50:   */{
/* 51:51 */  public static final DoubleComparator NATURAL_COMPARATOR = new AbstractDoubleComparator() {
/* 52:   */    public final int compare(double a, double b) {
/* 53:53 */      return a == b ? 0 : a < b ? -1 : 1;
/* 54:   */    }
/* 55:   */  };
/* 56:   */  
/* 58:58 */  public static final DoubleComparator OPPOSITE_COMPARATOR = new AbstractDoubleComparator() {
/* 59:   */    public final int compare(double a, double b) {
/* 60:60 */      return b == a ? 0 : b < a ? -1 : 1;
/* 61:   */    }
/* 62:   */  };
/* 63:   */  
/* 67:   */  public static DoubleComparator oppositeComparator(DoubleComparator c)
/* 68:   */  {
/* 69:69 */    new AbstractDoubleComparator() {
/* 70:70 */      private final DoubleComparator comparator = this.val$c;
/* 71:   */      
/* 72:72 */      public final int compare(double a, double b) { return -this.comparator.compare(a, b); }
/* 73:   */    };
/* 74:   */  }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleComparators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */