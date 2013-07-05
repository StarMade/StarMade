/*    */ package com.google.code.tempusfugit.concurrency;
/*    */ 
/*    */ import com.google.code.tempusfugit.temporal.Duration;
/*    */ import java.util.concurrent.CountDownLatch;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import java.util.concurrent.TimeoutException;
/*    */ 
/*    */ public class CountDownLatchWithTimeout
/*    */ {
/*    */   private final CountDownLatch latch;
/*    */ 
/*    */   private CountDownLatchWithTimeout(CountDownLatch latch)
/*    */   {
/* 31 */     this.latch = latch;
/*    */   }
/*    */ 
/*    */   public static CountDownLatchWithTimeout await(CountDownLatch latch) {
/* 35 */     return new CountDownLatchWithTimeout(latch);
/*    */   }
/*    */ 
/*    */   public void with(Duration timeout) throws InterruptedException, TimeoutException {
/* 39 */     if (!this.latch.await(timeout.inMillis(), TimeUnit.MILLISECONDS))
/* 40 */       throw new TimeoutException();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.CountDownLatchWithTimeout
 * JD-Core Version:    0.6.2
 */