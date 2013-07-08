/*  1:   */package it.unimi.dsi.fastutil.bytes;
/*  2:   */
/* 44:   */public abstract class AbstractByteSortedSet
/* 45:   */  extends AbstractByteSet
/* 46:   */  implements ByteSortedSet
/* 47:   */{
/* 48:   */  public ByteSortedSet headSet(Byte to)
/* 49:   */  {
/* 50:50 */    return headSet(to.byteValue());
/* 51:   */  }
/* 52:   */  
/* 53:   */  public ByteSortedSet tailSet(Byte from) {
/* 54:54 */    return tailSet(from.byteValue());
/* 55:   */  }
/* 56:   */  
/* 57:   */  public ByteSortedSet subSet(Byte from, Byte to) {
/* 58:58 */    return subSet(from.byteValue(), to.byteValue());
/* 59:   */  }
/* 60:   */  
/* 61:   */  public Byte first() {
/* 62:62 */    return Byte.valueOf(firstByte());
/* 63:   */  }
/* 64:   */  
/* 65:   */  public Byte last() {
/* 66:66 */    return Byte.valueOf(lastByte());
/* 67:   */  }
/* 68:   */  
/* 69:   */  @Deprecated
/* 70:   */  public ByteBidirectionalIterator byteIterator() {
/* 71:71 */    return iterator();
/* 72:   */  }
/* 73:   */  
/* 74:   */  public abstract ByteBidirectionalIterator iterator();
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */