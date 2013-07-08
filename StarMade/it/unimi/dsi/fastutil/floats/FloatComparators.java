/*  1:   */package it.unimi.dsi.fastutil.floats;
/*  2:   */
/* 49:   */public class FloatComparators
/* 50:   */{
/* 51:51 */  public static final FloatComparator NATURAL_COMPARATOR = new AbstractFloatComparator() {
/* 52:   */    public final int compare(float a, float b) {
/* 53:53 */      return a == b ? 0 : a < b ? -1 : 1;
/* 54:   */    }
/* 55:   */  };
/* 56:   */  
/* 58:58 */  public static final FloatComparator OPPOSITE_COMPARATOR = new AbstractFloatComparator() {
/* 59:   */    public final int compare(float a, float b) {
/* 60:60 */      return b == a ? 0 : b < a ? -1 : 1;
/* 61:   */    }
/* 62:   */  };
/* 63:   */  
/* 67:   */  public static FloatComparator oppositeComparator(FloatComparator c)
/* 68:   */  {
/* 69:69 */    new AbstractFloatComparator() {
/* 70:70 */      private final FloatComparator comparator = this.val$c;
/* 71:   */      
/* 72:72 */      public final int compare(float a, float b) { return -this.comparator.compare(a, b); }
/* 73:   */    };
/* 74:   */  }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatComparators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */