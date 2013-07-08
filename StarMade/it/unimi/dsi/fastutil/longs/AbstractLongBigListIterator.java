/*  1:   */package it.unimi.dsi.fastutil.longs;
/*  2:   */
/* 54:   */public abstract class AbstractLongBigListIterator
/* 55:   */  extends AbstractLongBidirectionalIterator
/* 56:   */  implements LongBigListIterator
/* 57:   */{
/* 58:58 */  public void set(Long ok) { set(ok.longValue()); }
/* 59:   */  
/* 60:60 */  public void add(Long ok) { add(ok.longValue()); }
/* 61:   */  
/* 62:62 */  public void set(long k) { throw new UnsupportedOperationException(); }
/* 63:   */  
/* 64:64 */  public void add(long k) { throw new UnsupportedOperationException(); }
/* 65:   */  
/* 66:   */  public long skip(long n)
/* 67:   */  {
/* 68:68 */    long i = n;
/* 69:69 */    while ((i-- != 0L) && (hasNext())) nextLong();
/* 70:70 */    return n - i - 1L;
/* 71:   */  }
/* 72:   */  
/* 74:   */  public long back(long n)
/* 75:   */  {
/* 76:76 */    long i = n;
/* 77:77 */    while ((i-- != 0L) && (hasPrevious())) previousLong();
/* 78:78 */    return n - i - 1L;
/* 79:   */  }
/* 80:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */