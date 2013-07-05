/*    */ package it.unimi.dsi.fastutil.floats;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*    */ 
/*    */ public abstract class AbstractFloatPriorityQueue extends AbstractPriorityQueue<Float>
/*    */   implements FloatPriorityQueue
/*    */ {
/*    */   public void enqueue(Float x)
/*    */   {
/* 51 */     enqueue(x.floatValue());
/*    */   }
/* 53 */   public Float dequeue() { return Float.valueOf(dequeueFloat()); } 
/*    */   public Float first() {
/* 55 */     return Float.valueOf(firstFloat());
/*    */   }
/* 57 */   public Float last() { return Float.valueOf(lastFloat()); } 
/*    */   public float lastFloat() {
/* 59 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatPriorityQueue
 * JD-Core Version:    0.6.2
 */