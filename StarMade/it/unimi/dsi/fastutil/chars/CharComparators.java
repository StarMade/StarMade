/*  1:   */package it.unimi.dsi.fastutil.chars;
/*  2:   */
/* 49:   */public class CharComparators
/* 50:   */{
/* 51:51 */  public static final CharComparator NATURAL_COMPARATOR = new AbstractCharComparator() {
/* 52:   */    public final int compare(char a, char b) {
/* 53:53 */      return a == b ? 0 : a < b ? -1 : 1;
/* 54:   */    }
/* 55:   */  };
/* 56:   */  
/* 58:58 */  public static final CharComparator OPPOSITE_COMPARATOR = new AbstractCharComparator() {
/* 59:   */    public final int compare(char a, char b) {
/* 60:60 */      return b == a ? 0 : b < a ? -1 : 1;
/* 61:   */    }
/* 62:   */  };
/* 63:   */  
/* 67:   */  public static CharComparator oppositeComparator(CharComparator c)
/* 68:   */  {
/* 69:69 */    new AbstractCharComparator() {
/* 70:70 */      private final CharComparator comparator = this.val$c;
/* 71:   */      
/* 72:72 */      public final int compare(char a, char b) { return -this.comparator.compare(a, b); }
/* 73:   */    };
/* 74:   */  }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharComparators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */