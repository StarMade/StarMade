/*  1:   */package it.unimi.dsi.fastutil.ints;
/*  2:   */
/* 49:   */public class IntComparators
/* 50:   */{
/* 51:51 */  public static final IntComparator NATURAL_COMPARATOR = new AbstractIntComparator() {
/* 52:   */    public final int compare(int a, int b) {
/* 53:53 */      return a == b ? 0 : a < b ? -1 : 1;
/* 54:   */    }
/* 55:   */  };
/* 56:   */  
/* 58:58 */  public static final IntComparator OPPOSITE_COMPARATOR = new AbstractIntComparator() {
/* 59:   */    public final int compare(int a, int b) {
/* 60:60 */      return b == a ? 0 : b < a ? -1 : 1;
/* 61:   */    }
/* 62:   */  };
/* 63:   */  
/* 67:   */  public static IntComparator oppositeComparator(IntComparator c)
/* 68:   */  {
/* 69:69 */    new AbstractIntComparator() {
/* 70:70 */      private final IntComparator comparator = this.val$c;
/* 71:   */      
/* 72:72 */      public final int compare(int a, int b) { return -this.comparator.compare(a, b); }
/* 73:   */    };
/* 74:   */  }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntComparators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */