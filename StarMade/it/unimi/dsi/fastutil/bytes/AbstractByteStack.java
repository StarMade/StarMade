/*  1:   */package it.unimi.dsi.fastutil.bytes;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.AbstractStack;
/*  4:   */
/* 50:   */public abstract class AbstractByteStack
/* 51:   */  extends AbstractStack<Byte>
/* 52:   */  implements ByteStack
/* 53:   */{
/* 54:   */  public void push(Byte o)
/* 55:   */  {
/* 56:56 */    push(o.byteValue());
/* 57:   */  }
/* 58:   */  
/* 59:   */  public Byte pop() {
/* 60:60 */    return Byte.valueOf(popByte());
/* 61:   */  }
/* 62:   */  
/* 63:   */  public Byte top() {
/* 64:64 */    return Byte.valueOf(topByte());
/* 65:   */  }
/* 66:   */  
/* 67:   */  public Byte peek(int i) {
/* 68:68 */    return Byte.valueOf(peekByte(i));
/* 69:   */  }
/* 70:   */  
/* 71:   */  public void push(byte k) {
/* 72:72 */    push(Byte.valueOf(k));
/* 73:   */  }
/* 74:   */  
/* 75:   */  public byte popByte() {
/* 76:76 */    return pop().byteValue();
/* 77:   */  }
/* 78:   */  
/* 79:   */  public byte topByte() {
/* 80:80 */    return top().byteValue();
/* 81:   */  }
/* 82:   */  
/* 83:   */  public byte peekByte(int i) {
/* 84:84 */    return peek(i).byteValue();
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */