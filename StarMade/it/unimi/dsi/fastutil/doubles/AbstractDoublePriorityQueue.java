/*    */ package it.unimi.dsi.fastutil.doubles;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*    */ 
/*    */ public abstract class AbstractDoublePriorityQueue extends AbstractPriorityQueue<Double>
/*    */   implements DoublePriorityQueue
/*    */ {
/*    */   public void enqueue(Double x)
/*    */   {
/* 51 */     enqueue(x.doubleValue());
/*    */   }
/* 53 */   public Double dequeue() { return Double.valueOf(dequeueDouble()); } 
/*    */   public Double first() {
/* 55 */     return Double.valueOf(firstDouble());
/*    */   }
/* 57 */   public Double last() { return Double.valueOf(lastDouble()); } 
/*    */   public double lastDouble() {
/* 59 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoublePriorityQueue
 * JD-Core Version:    0.6.2
 */