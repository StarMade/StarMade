/*    */ package com.google.code.tempusfugit.concurrency;
/*    */ 
/*    */ import com.google.code.tempusfugit.temporal.Conditions;
/*    */ import com.google.code.tempusfugit.temporal.Duration;
/*    */ import com.google.code.tempusfugit.temporal.Timeout;
/*    */ import com.google.code.tempusfugit.temporal.WaitFor;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import java.util.concurrent.TimeoutException;
/*    */ 
/*    */ public final class ExecutorServiceShutdown
/*    */ {
/*    */   private final ExecutorService executor;
/*    */ 
/*    */   private ExecutorServiceShutdown(ExecutorService executor)
/*    */   {
/* 36 */     this.executor = executor;
/*    */   }
/*    */ 
/*    */   public static ExecutorServiceShutdown shutdown(ExecutorService executor) {
/* 40 */     return new ExecutorServiceShutdown(executor);
/*    */   }
/*    */ 
/*    */   public Boolean waitingForCompletion(Duration duration) {
/* 44 */     if (this.executor == null)
/* 45 */       return Boolean.valueOf(false);
/* 46 */     this.executor.shutdown();
/* 47 */     return (Boolean)ThreadUtils.resetInterruptFlagWhen(awaitingTerminationIsInterrupted(duration));
/*    */   }
/*    */ 
/*    */   public Boolean waitingForShutdown(Timeout timeout) throws TimeoutException, InterruptedException
/*    */   {
/* 52 */     if (this.executor == null)
/* 53 */       return Boolean.valueOf(false);
/* 54 */     this.executor.shutdownNow();
/* 55 */     WaitFor.waitOrTimeout(Conditions.shutdown(this.executor), timeout);
/* 56 */     return Boolean.valueOf(true);
/*    */   }
/*    */ 
/*    */   @Deprecated
/*    */   public Boolean waitingForShutdown(Duration timeout) throws TimeoutException, InterruptedException {
/* 61 */     return waitingForShutdown(Timeout.timeout(timeout));
/*    */   }
/*    */ 
/*    */   private Interruptible<Boolean> awaitingTerminationIsInterrupted(final Duration timeout) {
/* 65 */     return new Interruptible() {
/*    */       public Boolean call() throws InterruptedException {
/* 67 */         return Boolean.valueOf(ExecutorServiceShutdown.this.executor.awaitTermination(timeout.inMillis(), TimeUnit.MILLISECONDS));
/*    */       }
/*    */     };
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.ExecutorServiceShutdown
 * JD-Core Version:    0.6.2
 */