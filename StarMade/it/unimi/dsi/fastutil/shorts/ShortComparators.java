/*  1:   */package it.unimi.dsi.fastutil.shorts;
/*  2:   */
/* 49:   */public class ShortComparators
/* 50:   */{
/* 51:51 */  public static final ShortComparator NATURAL_COMPARATOR = new AbstractShortComparator() {
/* 52:   */    public final int compare(short a, short b) {
/* 53:53 */      return a == b ? 0 : a < b ? -1 : 1;
/* 54:   */    }
/* 55:   */  };
/* 56:   */  
/* 58:58 */  public static final ShortComparator OPPOSITE_COMPARATOR = new AbstractShortComparator() {
/* 59:   */    public final int compare(short a, short b) {
/* 60:60 */      return b == a ? 0 : b < a ? -1 : 1;
/* 61:   */    }
/* 62:   */  };
/* 63:   */  
/* 67:   */  public static ShortComparator oppositeComparator(ShortComparator c)
/* 68:   */  {
/* 69:69 */    new AbstractShortComparator() {
/* 70:70 */      private final ShortComparator comparator = this.val$c;
/* 71:   */      
/* 72:72 */      public final int compare(short a, short b) { return -this.comparator.compare(a, b); }
/* 73:   */    };
/* 74:   */  }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortComparators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */