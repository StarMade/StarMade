/*    */ package com.google.code.tempusfugit.concurrency;
/*    */ 
/*    */ import com.google.code.tempusfugit.temporal.Clock;
/*    */ import com.google.code.tempusfugit.temporal.Duration;
/*    */ import com.google.code.tempusfugit.temporal.RealClock;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.Callable;
/*    */ import java.util.concurrent.CompletionService;
/*    */ import java.util.concurrent.ExecutionException;
/*    */ import java.util.concurrent.Future;
/*    */ import java.util.concurrent.TimeoutException;
/*    */ 
/*    */ public class DefaultTimeoutableCompletionService
/*    */   implements TimeoutableCompletionService
/*    */ {
/*    */   private static final boolean INTERRUPT_IF_RUNNING = true;
/* 35 */   private static final Duration DEFAULT_TIMEOUT = Duration.seconds(30L);
/*    */   private final CompletionService completionService;
/*    */   private final Duration timeout;
/*    */   private final Clock time;
/*    */ 
/*    */   public DefaultTimeoutableCompletionService(CompletionService completionService)
/*    */   {
/* 42 */     this(completionService, DEFAULT_TIMEOUT, RealClock.now());
/*    */   }
/*    */ 
/*    */   public DefaultTimeoutableCompletionService(CompletionService completionService, Duration timeout, Clock time) {
/* 46 */     this.timeout = timeout;
/* 47 */     this.time = time;
/* 48 */     this.completionService = completionService;
/*    */   }
/*    */ 
/*    */   public <T> List<T> submit(List<? extends Callable<T>> tasks) throws ExecutionException, TimeoutException {
/* 52 */     List submitted = new ArrayList();
/*    */     try {
/* 54 */       for (Callable task : tasks)
/* 55 */         submitted.add(this.completionService.submit(task));
/*    */       Iterator i$;
/*    */       Future future;
/* 57 */       return waitFor(tasks.size(), this.timeout);
/*    */     } finally {
/* 59 */       for (Future future : submitted)
/* 60 */         future.cancel(true);
/*    */     }
/*    */   }
/*    */ 
/*    */   private <T> List<T> waitFor(int tasks, Duration timeout) throws ExecutionException, TimeoutException
/*    */   {
/* 66 */     List completed = new ArrayList();
/* 67 */     Interrupter interrupter = Interrupter.interrupt(Thread.currentThread()).using(this.time).after(timeout);
/*    */     try {
/* 69 */       for (int i = 0; i < tasks; i++)
/*    */         try {
/* 71 */           Future future = this.completionService.take();
/* 72 */           completed.add(future.get());
/*    */         } catch (InterruptedException e) {
/* 74 */           throw new TimeoutExceptionWithResults("timed out after " + timeout.toString(), completed);
/*    */         }
/*    */     }
/*    */     finally {
/* 78 */       interrupter.cancel();
/*    */     }
/* 80 */     return completed;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.DefaultTimeoutableCompletionService
 * JD-Core Version:    0.6.2
 */