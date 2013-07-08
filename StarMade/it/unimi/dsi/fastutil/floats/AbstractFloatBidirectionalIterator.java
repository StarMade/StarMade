/*  1:   */package it.unimi.dsi.fastutil.floats;
/*  2:   */
/* 54:   */public abstract class AbstractFloatBidirectionalIterator
/* 55:   */  extends AbstractFloatIterator
/* 56:   */  implements FloatBidirectionalIterator
/* 57:   */{
/* 58:58 */  public float previousFloat() { return previous().floatValue(); }
/* 59:   */  
/* 60:60 */  public Float previous() { return Float.valueOf(previousFloat()); }
/* 61:   */  
/* 63:   */  public int back(int n)
/* 64:   */  {
/* 65:65 */    int i = n;
/* 66:66 */    while ((i-- != 0) && (hasPrevious())) previousFloat();
/* 67:67 */    return n - i - 1;
/* 68:   */  }
/* 69:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */