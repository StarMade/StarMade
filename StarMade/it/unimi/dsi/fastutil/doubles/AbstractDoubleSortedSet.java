/*  1:   */package it.unimi.dsi.fastutil.doubles;
/*  2:   */
/* 44:   */public abstract class AbstractDoubleSortedSet
/* 45:   */  extends AbstractDoubleSet
/* 46:   */  implements DoubleSortedSet
/* 47:   */{
/* 48:   */  public DoubleSortedSet headSet(Double to)
/* 49:   */  {
/* 50:50 */    return headSet(to.doubleValue());
/* 51:   */  }
/* 52:   */  
/* 53:   */  public DoubleSortedSet tailSet(Double from) {
/* 54:54 */    return tailSet(from.doubleValue());
/* 55:   */  }
/* 56:   */  
/* 57:   */  public DoubleSortedSet subSet(Double from, Double to) {
/* 58:58 */    return subSet(from.doubleValue(), to.doubleValue());
/* 59:   */  }
/* 60:   */  
/* 61:   */  public Double first() {
/* 62:62 */    return Double.valueOf(firstDouble());
/* 63:   */  }
/* 64:   */  
/* 65:   */  public Double last() {
/* 66:66 */    return Double.valueOf(lastDouble());
/* 67:   */  }
/* 68:   */  
/* 69:   */  @Deprecated
/* 70:   */  public DoubleBidirectionalIterator doubleIterator() {
/* 71:71 */    return iterator();
/* 72:   */  }
/* 73:   */  
/* 74:   */  public abstract DoubleBidirectionalIterator iterator();
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */