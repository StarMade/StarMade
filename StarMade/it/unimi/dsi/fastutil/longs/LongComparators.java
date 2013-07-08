/*  1:   */package it.unimi.dsi.fastutil.longs;
/*  2:   */
/* 49:   */public class LongComparators
/* 50:   */{
/* 51:51 */  public static final LongComparator NATURAL_COMPARATOR = new AbstractLongComparator() {
/* 52:   */    public final int compare(long a, long b) {
/* 53:53 */      return a == b ? 0 : a < b ? -1 : 1;
/* 54:   */    }
/* 55:   */  };
/* 56:   */  
/* 58:58 */  public static final LongComparator OPPOSITE_COMPARATOR = new AbstractLongComparator() {
/* 59:   */    public final int compare(long a, long b) {
/* 60:60 */      return b == a ? 0 : b < a ? -1 : 1;
/* 61:   */    }
/* 62:   */  };
/* 63:   */  
/* 67:   */  public static LongComparator oppositeComparator(LongComparator c)
/* 68:   */  {
/* 69:69 */    new AbstractLongComparator() {
/* 70:70 */      private final LongComparator comparator = this.val$c;
/* 71:   */      
/* 72:72 */      public final int compare(long a, long b) { return -this.comparator.compare(a, b); }
/* 73:   */    };
/* 74:   */  }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongComparators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */