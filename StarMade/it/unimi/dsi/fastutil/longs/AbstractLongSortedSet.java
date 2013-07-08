/*  1:   */package it.unimi.dsi.fastutil.longs;
/*  2:   */
/* 44:   */public abstract class AbstractLongSortedSet
/* 45:   */  extends AbstractLongSet
/* 46:   */  implements LongSortedSet
/* 47:   */{
/* 48:   */  public LongSortedSet headSet(Long to)
/* 49:   */  {
/* 50:50 */    return headSet(to.longValue());
/* 51:   */  }
/* 52:   */  
/* 53:   */  public LongSortedSet tailSet(Long from) {
/* 54:54 */    return tailSet(from.longValue());
/* 55:   */  }
/* 56:   */  
/* 57:   */  public LongSortedSet subSet(Long from, Long to) {
/* 58:58 */    return subSet(from.longValue(), to.longValue());
/* 59:   */  }
/* 60:   */  
/* 61:   */  public Long first() {
/* 62:62 */    return Long.valueOf(firstLong());
/* 63:   */  }
/* 64:   */  
/* 65:   */  public Long last() {
/* 66:66 */    return Long.valueOf(lastLong());
/* 67:   */  }
/* 68:   */  
/* 69:   */  @Deprecated
/* 70:   */  public LongBidirectionalIterator longIterator() {
/* 71:71 */    return iterator();
/* 72:   */  }
/* 73:   */  
/* 74:   */  public abstract LongBidirectionalIterator iterator();
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */