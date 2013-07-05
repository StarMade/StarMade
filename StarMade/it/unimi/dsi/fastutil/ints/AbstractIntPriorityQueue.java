/*    */ package it.unimi.dsi.fastutil.ints;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*    */ 
/*    */ public abstract class AbstractIntPriorityQueue extends AbstractPriorityQueue<Integer>
/*    */   implements IntPriorityQueue
/*    */ {
/*    */   public void enqueue(Integer x)
/*    */   {
/* 51 */     enqueue(x.intValue());
/*    */   }
/* 53 */   public Integer dequeue() { return Integer.valueOf(dequeueInt()); } 
/*    */   public Integer first() {
/* 55 */     return Integer.valueOf(firstInt());
/*    */   }
/* 57 */   public Integer last() { return Integer.valueOf(lastInt()); } 
/*    */   public int lastInt() {
/* 59 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntPriorityQueue
 * JD-Core Version:    0.6.2
 */