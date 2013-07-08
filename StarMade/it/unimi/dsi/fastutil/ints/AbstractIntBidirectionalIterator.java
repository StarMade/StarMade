/*  1:   */package it.unimi.dsi.fastutil.ints;
/*  2:   */
/* 54:   */public abstract class AbstractIntBidirectionalIterator
/* 55:   */  extends AbstractIntIterator
/* 56:   */  implements IntBidirectionalIterator
/* 57:   */{
/* 58:58 */  public int previousInt() { return previous().intValue(); }
/* 59:   */  
/* 60:60 */  public Integer previous() { return Integer.valueOf(previousInt()); }
/* 61:   */  
/* 63:   */  public int back(int n)
/* 64:   */  {
/* 65:65 */    int i = n;
/* 66:66 */    while ((i-- != 0) && (hasPrevious())) previousInt();
/* 67:67 */    return n - i - 1;
/* 68:   */  }
/* 69:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */