/*    */ package it.unimi.dsi.fastutil.shorts;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*    */ 
/*    */ public abstract class AbstractShortPriorityQueue extends AbstractPriorityQueue<Short>
/*    */   implements ShortPriorityQueue
/*    */ {
/*    */   public void enqueue(Short x)
/*    */   {
/* 51 */     enqueue(x.shortValue());
/*    */   }
/* 53 */   public Short dequeue() { return Short.valueOf(dequeueShort()); } 
/*    */   public Short first() {
/* 55 */     return Short.valueOf(firstShort());
/*    */   }
/* 57 */   public Short last() { return Short.valueOf(lastShort()); } 
/*    */   public short lastShort() {
/* 59 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortPriorityQueue
 * JD-Core Version:    0.6.2
 */