/*  1:   */package it.unimi.dsi.fastutil.shorts;
/*  2:   */
/* 44:   */public abstract class AbstractShortSortedSet
/* 45:   */  extends AbstractShortSet
/* 46:   */  implements ShortSortedSet
/* 47:   */{
/* 48:   */  public ShortSortedSet headSet(Short to)
/* 49:   */  {
/* 50:50 */    return headSet(to.shortValue());
/* 51:   */  }
/* 52:   */  
/* 53:   */  public ShortSortedSet tailSet(Short from) {
/* 54:54 */    return tailSet(from.shortValue());
/* 55:   */  }
/* 56:   */  
/* 57:   */  public ShortSortedSet subSet(Short from, Short to) {
/* 58:58 */    return subSet(from.shortValue(), to.shortValue());
/* 59:   */  }
/* 60:   */  
/* 61:   */  public Short first() {
/* 62:62 */    return Short.valueOf(firstShort());
/* 63:   */  }
/* 64:   */  
/* 65:   */  public Short last() {
/* 66:66 */    return Short.valueOf(lastShort());
/* 67:   */  }
/* 68:   */  
/* 69:   */  @Deprecated
/* 70:   */  public ShortBidirectionalIterator shortIterator() {
/* 71:71 */    return iterator();
/* 72:   */  }
/* 73:   */  
/* 74:   */  public abstract ShortBidirectionalIterator iterator();
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */