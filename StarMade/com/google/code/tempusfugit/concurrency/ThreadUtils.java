/*    */ package com.google.code.tempusfugit.concurrency;
/*    */ 
/*    */ import com.google.code.tempusfugit.temporal.Duration;
/*    */ 
/*    */ public final class ThreadUtils
/*    */ {
/*    */   public static void sleep(Duration duration)
/*    */   {
/* 24 */     resetInterruptFlagWhen(sleepingIsInterrupted(duration));
/*    */   }
/*    */ 
/*    */   private static Interruptible<Void> sleepingIsInterrupted(Duration duration) {
/* 28 */     return new Interruptible() {
/*    */       public Void call() throws InterruptedException {
/* 30 */         Thread.sleep(this.val$duration.inMillis());
/* 31 */         return null;
/*    */       }
/*    */     };
/*    */   }
/*    */ 
/*    */   public static <T> T resetInterruptFlagWhen(Interruptible<T> interruptible) {
/*    */     try {
/* 38 */       return interruptible.call();
/*    */     } catch (InterruptedException e) {
/* 40 */       Thread.currentThread().interrupt();
/*    */     }
/* 42 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.ThreadUtils
 * JD-Core Version:    0.6.2
 */