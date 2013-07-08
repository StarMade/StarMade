/*  1:   */package it.unimi.dsi.fastutil.bytes;
/*  2:   */
/* 55:   */public abstract class AbstractByteListIterator
/* 56:   */  extends AbstractByteBidirectionalIterator
/* 57:   */  implements ByteListIterator
/* 58:   */{
/* 59:59 */  public void set(Byte ok) { set(ok.byteValue()); }
/* 60:   */  
/* 61:61 */  public void add(Byte ok) { add(ok.byteValue()); }
/* 62:   */  
/* 63:63 */  public void set(byte k) { throw new UnsupportedOperationException(); }
/* 64:   */  
/* 65:65 */  public void add(byte k) { throw new UnsupportedOperationException(); }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */