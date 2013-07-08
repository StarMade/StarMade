/*  1:   */package it.unimi.dsi.fastutil.doubles;
/*  2:   */
/* 54:   */public abstract class AbstractDoubleBidirectionalIterator
/* 55:   */  extends AbstractDoubleIterator
/* 56:   */  implements DoubleBidirectionalIterator
/* 57:   */{
/* 58:58 */  public double previousDouble() { return previous().doubleValue(); }
/* 59:   */  
/* 60:60 */  public Double previous() { return Double.valueOf(previousDouble()); }
/* 61:   */  
/* 63:   */  public int back(int n)
/* 64:   */  {
/* 65:65 */    int i = n;
/* 66:66 */    while ((i-- != 0) && (hasPrevious())) previousDouble();
/* 67:67 */    return n - i - 1;
/* 68:   */  }
/* 69:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */