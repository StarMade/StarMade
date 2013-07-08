/*  1:   */package it.unimi.dsi.fastutil.booleans;
/*  2:   */
/* 54:   */public abstract class AbstractBooleanBidirectionalIterator
/* 55:   */  extends AbstractBooleanIterator
/* 56:   */  implements BooleanBidirectionalIterator
/* 57:   */{
/* 58:58 */  public boolean previousBoolean() { return previous().booleanValue(); }
/* 59:   */  
/* 60:60 */  public Boolean previous() { return Boolean.valueOf(previousBoolean()); }
/* 61:   */  
/* 63:   */  public int back(int n)
/* 64:   */  {
/* 65:65 */    int i = n;
/* 66:66 */    while ((i-- != 0) && (hasPrevious())) previousBoolean();
/* 67:67 */    return n - i - 1;
/* 68:   */  }
/* 69:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */