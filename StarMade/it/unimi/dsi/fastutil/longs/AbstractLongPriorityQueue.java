/*    */ package it.unimi.dsi.fastutil.longs;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*    */ 
/*    */ public abstract class AbstractLongPriorityQueue extends AbstractPriorityQueue<Long>
/*    */   implements LongPriorityQueue
/*    */ {
/*    */   public void enqueue(Long x)
/*    */   {
/* 51 */     enqueue(x.longValue());
/*    */   }
/* 53 */   public Long dequeue() { return Long.valueOf(dequeueLong()); } 
/*    */   public Long first() {
/* 55 */     return Long.valueOf(firstLong());
/*    */   }
/* 57 */   public Long last() { return Long.valueOf(lastLong()); } 
/*    */   public long lastLong() {
/* 59 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongPriorityQueue
 * JD-Core Version:    0.6.2
 */