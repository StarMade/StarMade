/*  1:   */package it.unimi.dsi.fastutil.bytes;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*  4:   */
/* 47:   */public abstract class AbstractBytePriorityQueue
/* 48:   */  extends AbstractPriorityQueue<Byte>
/* 49:   */  implements BytePriorityQueue
/* 50:   */{
/* 51:51 */  public void enqueue(Byte x) { enqueue(x.byteValue()); }
/* 52:   */  
/* 53:53 */  public Byte dequeue() { return Byte.valueOf(dequeueByte()); }
/* 54:   */  
/* 55:55 */  public Byte first() { return Byte.valueOf(firstByte()); }
/* 56:   */  
/* 57:57 */  public Byte last() { return Byte.valueOf(lastByte()); }
/* 58:   */  
/* 59:59 */  public byte lastByte() { throw new UnsupportedOperationException(); }
/* 60:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractBytePriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */