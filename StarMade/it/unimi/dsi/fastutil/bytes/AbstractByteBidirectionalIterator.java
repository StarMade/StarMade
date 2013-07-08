/*  1:   */package it.unimi.dsi.fastutil.bytes;
/*  2:   */
/* 54:   */public abstract class AbstractByteBidirectionalIterator
/* 55:   */  extends AbstractByteIterator
/* 56:   */  implements ByteBidirectionalIterator
/* 57:   */{
/* 58:58 */  public byte previousByte() { return previous().byteValue(); }
/* 59:   */  
/* 60:60 */  public Byte previous() { return Byte.valueOf(previousByte()); }
/* 61:   */  
/* 63:   */  public int back(int n)
/* 64:   */  {
/* 65:65 */    int i = n;
/* 66:66 */    while ((i-- != 0) && (hasPrevious())) previousByte();
/* 67:67 */    return n - i - 1;
/* 68:   */  }
/* 69:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */