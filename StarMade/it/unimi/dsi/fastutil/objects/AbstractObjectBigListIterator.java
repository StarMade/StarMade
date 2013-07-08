/*  1:   */package it.unimi.dsi.fastutil.objects;
/*  2:   */
/* 53:   */public abstract class AbstractObjectBigListIterator<K>
/* 54:   */  extends AbstractObjectBidirectionalIterator<K>
/* 55:   */  implements ObjectBigListIterator<K>
/* 56:   */{
/* 57:57 */  public void set(K k) { throw new UnsupportedOperationException(); }
/* 58:   */  
/* 59:59 */  public void add(K k) { throw new UnsupportedOperationException(); }
/* 60:   */  
/* 61:   */  public long skip(long n)
/* 62:   */  {
/* 63:63 */    long i = n;
/* 64:64 */    while ((i-- != 0L) && (hasNext())) next();
/* 65:65 */    return n - i - 1L;
/* 66:   */  }
/* 67:   */  
/* 69:   */  public long back(long n)
/* 70:   */  {
/* 71:71 */    long i = n;
/* 72:72 */    while ((i-- != 0L) && (hasPrevious())) previous();
/* 73:73 */    return n - i - 1L;
/* 74:   */  }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */