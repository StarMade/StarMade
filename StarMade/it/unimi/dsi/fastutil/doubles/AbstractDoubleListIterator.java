/*  1:   */package it.unimi.dsi.fastutil.doubles;
/*  2:   */
/* 55:   */public abstract class AbstractDoubleListIterator
/* 56:   */  extends AbstractDoubleBidirectionalIterator
/* 57:   */  implements DoubleListIterator
/* 58:   */{
/* 59:59 */  public void set(Double ok) { set(ok.doubleValue()); }
/* 60:   */  
/* 61:61 */  public void add(Double ok) { add(ok.doubleValue()); }
/* 62:   */  
/* 63:63 */  public void set(double k) { throw new UnsupportedOperationException(); }
/* 64:   */  
/* 65:65 */  public void add(double k) { throw new UnsupportedOperationException(); }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */