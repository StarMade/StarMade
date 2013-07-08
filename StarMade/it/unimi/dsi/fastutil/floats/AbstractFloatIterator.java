/*  1:   */package it.unimi.dsi.fastutil.floats;
/*  2:   */
/* 57:   */public abstract class AbstractFloatIterator
/* 58:   */  implements FloatIterator
/* 59:   */{
/* 60:60 */  public float nextFloat() { return next().floatValue(); }
/* 61:   */  
/* 62:62 */  public Float next() { return Float.valueOf(nextFloat()); }
/* 63:   */  
/* 64:64 */  public void remove() { throw new UnsupportedOperationException(); }
/* 65:   */  
/* 66:   */  public int skip(int n)
/* 67:   */  {
/* 68:68 */    int i = n;
/* 69:69 */    while ((i-- != 0) && (hasNext())) nextFloat();
/* 70:70 */    return n - i - 1;
/* 71:   */  }
/* 72:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */