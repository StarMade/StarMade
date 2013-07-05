/*    */ package com.bulletphysics.linearmath;
/*    */ 
/*    */ public class Clock
/*    */ {
/*    */   private long startTime;
/*    */ 
/*    */   public Clock()
/*    */   {
/* 39 */     reset();
/*    */   }
/*    */ 
/*    */   public void reset()
/*    */   {
/* 46 */     this.startTime = System.nanoTime();
/*    */   }
/*    */ 
/*    */   public long getTimeMilliseconds()
/*    */   {
/* 53 */     return (System.nanoTime() - this.startTime) / 1000000L;
/*    */   }
/*    */ 
/*    */   public long getTimeMicroseconds()
/*    */   {
/* 60 */     return (System.nanoTime() - this.startTime) / 1000L;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.Clock
 * JD-Core Version:    0.6.2
 */