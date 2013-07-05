/*     */ package com.google.code.tempusfugit.concurrency;
/*     */ 
/*     */ import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.CountDownLatch;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.ExecutorCompletionService;
/*     */ import java.util.concurrent.Future;
/*     */ import org.junit.runners.model.FrameworkMethod;
/*     */ import org.junit.runners.model.Statement;
/*     */ 
/*     */ class RunConcurrently extends Statement
/*     */ {
/*     */   private final FrameworkMethod method;
/*     */   private final Statement statement;
/*     */ 
/*     */   RunConcurrently(FrameworkMethod method, Statement statement)
/*     */   {
/*  32 */     this.method = method;
/*  33 */     this.statement = statement;
/*     */   }
/*     */ 
/*     */   public void evaluate() throws Throwable {
/*  37 */     if (concurrent(this.method)) {
/*  38 */       ExecutorCompletionService service = createCompletionService();
/*  39 */       startThreads(service);
/*  40 */       Throwable throwable = waitFor(service);
/*  41 */       if (throwable != null)
/*  42 */         throw throwable;
/*     */     } else {
/*  44 */       this.statement.evaluate();
/*     */     }
/*     */   }
/*     */ 
/*  48 */   private ExecutorCompletionService createCompletionService() { return new ExecutorCompletionService(new Executor() {
/*     */       private int count;
/*     */ 
/*  51 */       public void execute(Runnable runnable) { new Thread(runnable, RunConcurrently.this.method.getName() + "-Thread-" + this.count++).start(); }
/*     */     });
/*     */   }
/*     */ 
/*     */   private void startThreads(ExecutorCompletionService<Void> service)
/*     */   {
/*  57 */     CountDownLatch start = new CountDownLatch(1);
/*  58 */     for (int i = 0; i < threadCount(this.method); i++)
/*  59 */       service.submit(new StatementEvaluatingTask(this.statement, start));
/*  60 */     start.countDown();
/*     */   }
/*     */ 
/*     */   private Throwable waitFor(ExecutorCompletionService<Void> service) {
/*  64 */     Throwable throwable = null;
/*  65 */     for (int i = 0; i < threadCount(this.method); i++) {
/*     */       try {
/*  67 */         service.take().get();
/*     */       } catch (InterruptedException e) {
/*  69 */         Thread.currentThread().interrupt();
/*     */       } catch (ExecutionException e) {
/*  71 */         throwable = e.getCause();
/*  72 */         break;
/*     */       }
/*     */     }
/*  75 */     return throwable;
/*     */   }
/*     */ 
/*     */   private static boolean concurrent(FrameworkMethod method) {
/*  79 */     return method.getAnnotation(Concurrent.class) != null;
/*     */   }
/*     */ 
/*     */   private static int threadCount(FrameworkMethod method) {
/*  83 */     return ((Concurrent)method.getAnnotation(Concurrent.class)).count();
/*     */   }
/*     */   private static class StatementEvaluatingTask implements Callable<Void> {
/*     */     private final Statement statement;
/*     */     private final CountDownLatch start;
/*     */ 
/*     */     public StatementEvaluatingTask(Statement statement, CountDownLatch start) {
/*  91 */       this.statement = statement;
/*  92 */       this.start = start;
/*     */     }
/*     */ 
/*     */     public Void call() throws Exception {
/*     */       try {
/*  97 */         this.start.await();
/*  98 */         this.statement.evaluate();
/*     */       } catch (Throwable throwable) {
/* 100 */         throw new RuntimeException(throwable);
/*     */       }
/* 102 */       return null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.RunConcurrently
 * JD-Core Version:    0.6.2
 */