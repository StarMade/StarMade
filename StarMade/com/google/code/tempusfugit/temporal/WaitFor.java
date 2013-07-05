/*    */ package com.google.code.tempusfugit.temporal;
/*    */ 
/*    */ import java.util.concurrent.TimeoutException;
/*    */ 
/*    */ public final class WaitFor
/*    */ {
/* 26 */   public static final Duration SLEEP_PERIOD = Duration.millis(50L);
/*    */ 
/*    */   public static void waitOrTimeout(Condition condition, Timeout timeout)
/*    */     throws InterruptedException, TimeoutException
/*    */   {
/* 33 */     waitOrTimeout(condition, timeout, new ThreadSleep(SLEEP_PERIOD));
/*    */   }
/*    */ 
/*    */   public static void waitOrTimeout(Condition condition, Timeout timeout, Sleeper sleeper) throws TimeoutException, InterruptedException
/*    */   {
/* 38 */     if (success(condition, timeout, sleeper))
/* 39 */       return;
/* 40 */     throw new TimeoutException();
/*    */   }
/*    */ 
/*    */   @Deprecated
/*    */   public static void waitOrTimeout(Condition condition, Duration duration) throws TimeoutException, InterruptedException {
/* 45 */     waitOrTimeout(condition, Timeout.timeout(duration));
/*    */   }
/*    */ 
/*    */   @Deprecated
/*    */   public static void waitOrTimeout(Condition condition, Duration duration, StopWatch stopWatch) throws TimeoutException, InterruptedException {
/* 50 */     waitOrTimeout(condition, Timeout.timeout(duration, stopWatch));
/*    */   }
/*    */ 
/*    */   public static void waitUntil(Timeout timeout) throws InterruptedException {
/* 54 */     while (!timeout.hasExpired())
/* 55 */       Thread.sleep(SLEEP_PERIOD.inMillis());
/*    */   }
/*    */ 
/*    */   private static boolean success(Condition condition, Timeout timeout, Sleeper sleeper) throws InterruptedException {
/* 59 */     while (!timeout.hasExpired()) {
/* 60 */       if (condition.isSatisfied()) {
/* 61 */         return true;
/*    */       }
/* 63 */       sleeper.sleep();
/*    */     }
/* 65 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.temporal.WaitFor
 * JD-Core Version:    0.6.2
 */