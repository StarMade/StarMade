/*    */ package com.google.code.tempusfugit.concurrency;
/*    */ 
/*    */ import com.google.code.tempusfugit.temporal.Duration;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.TimeoutException;
/*    */ import org.junit.runners.model.RunnerScheduler;
/*    */ 
/*    */ class ConcurrentScheduler
/*    */   implements RunnerScheduler
/*    */ {
/*    */   private final ExecutorService executor;
/*    */ 
/*    */   public ConcurrentScheduler(ExecutorService executor)
/*    */   {
/* 32 */     this.executor = executor;
/*    */   }
/*    */ 
/*    */   public void schedule(Runnable childStatement) {
/* 36 */     this.executor.submit(childStatement);
/*    */   }
/*    */ 
/*    */   public void finished() {
/* 40 */     Boolean completed = ExecutorServiceShutdown.shutdown(this.executor).waitingForCompletion(Duration.days(365L));
/* 41 */     if (Thread.currentThread().isInterrupted())
/* 42 */       throw new RuntimeException(new InterruptedException("scheduler shutdown was interrupted"));
/* 43 */     if (!completed.booleanValue())
/* 44 */       throw new RuntimeException(new TimeoutException("scheduler shutdown timed out before tests completed"));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.ConcurrentScheduler
 * JD-Core Version:    0.6.2
 */