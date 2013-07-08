/*  1:   */package it.unimi.dsi.fastutil.shorts;
/*  2:   */
/* 54:   */public abstract class AbstractShortBigListIterator
/* 55:   */  extends AbstractShortBidirectionalIterator
/* 56:   */  implements ShortBigListIterator
/* 57:   */{
/* 58:58 */  public void set(Short ok) { set(ok.shortValue()); }
/* 59:   */  
/* 60:60 */  public void add(Short ok) { add(ok.shortValue()); }
/* 61:   */  
/* 62:62 */  public void set(short k) { throw new UnsupportedOperationException(); }
/* 63:   */  
/* 64:64 */  public void add(short k) { throw new UnsupportedOperationException(); }
/* 65:   */  
/* 66:   */  public long skip(long n)
/* 67:   */  {
/* 68:68 */    long i = n;
/* 69:69 */    while ((i-- != 0L) && (hasNext())) nextShort();
/* 70:70 */    return n - i - 1L;
/* 71:   */  }
/* 72:   */  
/* 74:   */  public long back(long n)
/* 75:   */  {
/* 76:76 */    long i = n;
/* 77:77 */    while ((i-- != 0L) && (hasPrevious())) previousShort();
/* 78:78 */    return n - i - 1L;
/* 79:   */  }
/* 80:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */