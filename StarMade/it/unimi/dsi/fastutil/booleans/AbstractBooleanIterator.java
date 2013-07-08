/*  1:   */package it.unimi.dsi.fastutil.booleans;
/*  2:   */
/* 57:   */public abstract class AbstractBooleanIterator
/* 58:   */  implements BooleanIterator
/* 59:   */{
/* 60:60 */  public boolean nextBoolean() { return next().booleanValue(); }
/* 61:   */  
/* 62:62 */  public Boolean next() { return Boolean.valueOf(nextBoolean()); }
/* 63:   */  
/* 64:64 */  public void remove() { throw new UnsupportedOperationException(); }
/* 65:   */  
/* 66:   */  public int skip(int n)
/* 67:   */  {
/* 68:68 */    int i = n;
/* 69:69 */    while ((i-- != 0) && (hasNext())) nextBoolean();
/* 70:70 */    return n - i - 1;
/* 71:   */  }
/* 72:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */