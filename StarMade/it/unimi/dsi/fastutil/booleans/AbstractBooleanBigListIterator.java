/*  1:   */package it.unimi.dsi.fastutil.booleans;
/*  2:   */
/* 54:   */public abstract class AbstractBooleanBigListIterator
/* 55:   */  extends AbstractBooleanBidirectionalIterator
/* 56:   */  implements BooleanBigListIterator
/* 57:   */{
/* 58:58 */  public void set(Boolean ok) { set(ok.booleanValue()); }
/* 59:   */  
/* 60:60 */  public void add(Boolean ok) { add(ok.booleanValue()); }
/* 61:   */  
/* 62:62 */  public void set(boolean k) { throw new UnsupportedOperationException(); }
/* 63:   */  
/* 64:64 */  public void add(boolean k) { throw new UnsupportedOperationException(); }
/* 65:   */  
/* 66:   */  public long skip(long n)
/* 67:   */  {
/* 68:68 */    long i = n;
/* 69:69 */    while ((i-- != 0L) && (hasNext())) nextBoolean();
/* 70:70 */    return n - i - 1L;
/* 71:   */  }
/* 72:   */  
/* 74:   */  public long back(long n)
/* 75:   */  {
/* 76:76 */    long i = n;
/* 77:77 */    while ((i-- != 0L) && (hasPrevious())) previousBoolean();
/* 78:78 */    return n - i - 1L;
/* 79:   */  }
/* 80:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */