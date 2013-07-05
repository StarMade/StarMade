/*    */ package it.unimi.dsi.fastutil.bytes;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*    */ 
/*    */ public abstract class AbstractBytePriorityQueue extends AbstractPriorityQueue<Byte>
/*    */   implements BytePriorityQueue
/*    */ {
/*    */   public void enqueue(Byte x)
/*    */   {
/* 51 */     enqueue(x.byteValue());
/*    */   }
/* 53 */   public Byte dequeue() { return Byte.valueOf(dequeueByte()); } 
/*    */   public Byte first() {
/* 55 */     return Byte.valueOf(firstByte());
/*    */   }
/* 57 */   public Byte last() { return Byte.valueOf(lastByte()); } 
/*    */   public byte lastByte() {
/* 59 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractBytePriorityQueue
 * JD-Core Version:    0.6.2
 */