/*  1:   */package it.unimi.dsi.fastutil.bytes;
/*  2:   */
/* 54:   */public abstract class AbstractByteBigListIterator
/* 55:   */  extends AbstractByteBidirectionalIterator
/* 56:   */  implements ByteBigListIterator
/* 57:   */{
/* 58:58 */  public void set(Byte ok) { set(ok.byteValue()); }
/* 59:   */  
/* 60:60 */  public void add(Byte ok) { add(ok.byteValue()); }
/* 61:   */  
/* 62:62 */  public void set(byte k) { throw new UnsupportedOperationException(); }
/* 63:   */  
/* 64:64 */  public void add(byte k) { throw new UnsupportedOperationException(); }
/* 65:   */  
/* 66:   */  public long skip(long n)
/* 67:   */  {
/* 68:68 */    long i = n;
/* 69:69 */    while ((i-- != 0L) && (hasNext())) nextByte();
/* 70:70 */    return n - i - 1L;
/* 71:   */  }
/* 72:   */  
/* 74:   */  public long back(long n)
/* 75:   */  {
/* 76:76 */    long i = n;
/* 77:77 */    while ((i-- != 0L) && (hasPrevious())) previousByte();
/* 78:78 */    return n - i - 1L;
/* 79:   */  }
/* 80:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */