/*  1:   */package it.unimi.dsi.fastutil.longs;
/*  2:   */
/* 54:   */public abstract class AbstractLongBidirectionalIterator
/* 55:   */  extends AbstractLongIterator
/* 56:   */  implements LongBidirectionalIterator
/* 57:   */{
/* 58:58 */  public long previousLong() { return previous().longValue(); }
/* 59:   */  
/* 60:60 */  public Long previous() { return Long.valueOf(previousLong()); }
/* 61:   */  
/* 63:   */  public int back(int n)
/* 64:   */  {
/* 65:65 */    int i = n;
/* 66:66 */    while ((i-- != 0) && (hasPrevious())) previousLong();
/* 67:67 */    return n - i - 1;
/* 68:   */  }
/* 69:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */