/*  1:   */package it.unimi.dsi.fastutil.bytes;
/*  2:   */
/* 52:   */public abstract class AbstractByteComparator
/* 53:   */  implements ByteComparator
/* 54:   */{
/* 55:   */  public int compare(Byte ok1, Byte ok2)
/* 56:   */  {
/* 57:57 */    return compare(ok1.byteValue(), ok2.byteValue());
/* 58:   */  }
/* 59:   */  
/* 60:   */  public abstract int compare(byte paramByte1, byte paramByte2);
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */