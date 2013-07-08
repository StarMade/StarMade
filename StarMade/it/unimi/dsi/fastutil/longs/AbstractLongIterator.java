/*  1:   */package it.unimi.dsi.fastutil.longs;
/*  2:   */
/* 57:   */public abstract class AbstractLongIterator
/* 58:   */  implements LongIterator
/* 59:   */{
/* 60:60 */  public long nextLong() { return next().longValue(); }
/* 61:   */  
/* 62:62 */  public Long next() { return Long.valueOf(nextLong()); }
/* 63:   */  
/* 64:64 */  public void remove() { throw new UnsupportedOperationException(); }
/* 65:   */  
/* 66:   */  public int skip(int n)
/* 67:   */  {
/* 68:68 */    int i = n;
/* 69:69 */    while ((i-- != 0) && (hasNext())) nextLong();
/* 70:70 */    return n - i - 1;
/* 71:   */  }
/* 72:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */