/*    */ package com.google.code.tempusfugit.temporal;
/*    */ 
/*    */ public class ThreadSleep
/*    */   implements Sleeper
/*    */ {
/*    */   private final Duration period;
/*    */ 
/*    */   public ThreadSleep(Duration period)
/*    */   {
/* 24 */     this.period = period;
/*    */   }
/*    */ 
/*    */   public void sleep() throws InterruptedException {
/* 28 */     Thread.sleep(this.period.inMillis());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.temporal.ThreadSleep
 * JD-Core Version:    0.6.2
 */