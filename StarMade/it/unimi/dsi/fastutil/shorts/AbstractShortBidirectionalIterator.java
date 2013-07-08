/*  1:   */package it.unimi.dsi.fastutil.shorts;
/*  2:   */
/* 54:   */public abstract class AbstractShortBidirectionalIterator
/* 55:   */  extends AbstractShortIterator
/* 56:   */  implements ShortBidirectionalIterator
/* 57:   */{
/* 58:58 */  public short previousShort() { return previous().shortValue(); }
/* 59:   */  
/* 60:60 */  public Short previous() { return Short.valueOf(previousShort()); }
/* 61:   */  
/* 63:   */  public int back(int n)
/* 64:   */  {
/* 65:65 */    int i = n;
/* 66:66 */    while ((i-- != 0) && (hasPrevious())) previousShort();
/* 67:67 */    return n - i - 1;
/* 68:   */  }
/* 69:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */