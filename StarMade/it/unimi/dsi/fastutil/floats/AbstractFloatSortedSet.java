/*  1:   */package it.unimi.dsi.fastutil.floats;
/*  2:   */
/* 44:   */public abstract class AbstractFloatSortedSet
/* 45:   */  extends AbstractFloatSet
/* 46:   */  implements FloatSortedSet
/* 47:   */{
/* 48:   */  public FloatSortedSet headSet(Float to)
/* 49:   */  {
/* 50:50 */    return headSet(to.floatValue());
/* 51:   */  }
/* 52:   */  
/* 53:   */  public FloatSortedSet tailSet(Float from) {
/* 54:54 */    return tailSet(from.floatValue());
/* 55:   */  }
/* 56:   */  
/* 57:   */  public FloatSortedSet subSet(Float from, Float to) {
/* 58:58 */    return subSet(from.floatValue(), to.floatValue());
/* 59:   */  }
/* 60:   */  
/* 61:   */  public Float first() {
/* 62:62 */    return Float.valueOf(firstFloat());
/* 63:   */  }
/* 64:   */  
/* 65:   */  public Float last() {
/* 66:66 */    return Float.valueOf(lastFloat());
/* 67:   */  }
/* 68:   */  
/* 69:   */  @Deprecated
/* 70:   */  public FloatBidirectionalIterator floatIterator() {
/* 71:71 */    return iterator();
/* 72:   */  }
/* 73:   */  
/* 74:   */  public abstract FloatBidirectionalIterator iterator();
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */