/*  1:   */package it.unimi.dsi.fastutil.ints;
/*  2:   */
/* 44:   */public abstract class AbstractIntSortedSet
/* 45:   */  extends AbstractIntSet
/* 46:   */  implements IntSortedSet
/* 47:   */{
/* 48:   */  public IntSortedSet headSet(Integer to)
/* 49:   */  {
/* 50:50 */    return headSet(to.intValue());
/* 51:   */  }
/* 52:   */  
/* 53:   */  public IntSortedSet tailSet(Integer from) {
/* 54:54 */    return tailSet(from.intValue());
/* 55:   */  }
/* 56:   */  
/* 57:   */  public IntSortedSet subSet(Integer from, Integer to) {
/* 58:58 */    return subSet(from.intValue(), to.intValue());
/* 59:   */  }
/* 60:   */  
/* 61:   */  public Integer first() {
/* 62:62 */    return Integer.valueOf(firstInt());
/* 63:   */  }
/* 64:   */  
/* 65:   */  public Integer last() {
/* 66:66 */    return Integer.valueOf(lastInt());
/* 67:   */  }
/* 68:   */  
/* 69:   */  @Deprecated
/* 70:   */  public IntBidirectionalIterator intIterator() {
/* 71:71 */    return iterator();
/* 72:   */  }
/* 73:   */  
/* 74:   */  public abstract IntBidirectionalIterator iterator();
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */