/*  1:   */package it.unimi.dsi.fastutil.bytes;
/*  2:   */
/* 49:   */public class ByteComparators
/* 50:   */{
/* 51:51 */  public static final ByteComparator NATURAL_COMPARATOR = new AbstractByteComparator() {
/* 52:   */    public final int compare(byte a, byte b) {
/* 53:53 */      return a == b ? 0 : a < b ? -1 : 1;
/* 54:   */    }
/* 55:   */  };
/* 56:   */  
/* 58:58 */  public static final ByteComparator OPPOSITE_COMPARATOR = new AbstractByteComparator() {
/* 59:   */    public final int compare(byte a, byte b) {
/* 60:60 */      return b == a ? 0 : b < a ? -1 : 1;
/* 61:   */    }
/* 62:   */  };
/* 63:   */  
/* 67:   */  public static ByteComparator oppositeComparator(ByteComparator c)
/* 68:   */  {
/* 69:69 */    new AbstractByteComparator() {
/* 70:70 */      private final ByteComparator comparator = this.val$c;
/* 71:   */      
/* 72:72 */      public final int compare(byte a, byte b) { return -this.comparator.compare(a, b); }
/* 73:   */    };
/* 74:   */  }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteComparators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */