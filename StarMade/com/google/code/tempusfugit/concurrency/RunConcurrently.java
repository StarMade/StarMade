/*   1:    */package com.google.code.tempusfugit.concurrency;
/*   2:    */
/*   3:    */import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
/*   4:    */import java.util.concurrent.Callable;
/*   5:    */import java.util.concurrent.CountDownLatch;
/*   6:    */import java.util.concurrent.ExecutionException;
/*   7:    */import java.util.concurrent.Executor;
/*   8:    */import java.util.concurrent.ExecutorCompletionService;
/*   9:    */import java.util.concurrent.Future;
/*  10:    */import org.junit.runners.model.FrameworkMethod;
/*  11:    */import org.junit.runners.model.Statement;
/*  12:    */
/*  24:    */class RunConcurrently
/*  25:    */  extends Statement
/*  26:    */{
/*  27:    */  private final FrameworkMethod method;
/*  28:    */  private final Statement statement;
/*  29:    */  
/*  30:    */  RunConcurrently(FrameworkMethod method, Statement statement)
/*  31:    */  {
/*  32: 32 */    this.method = method;
/*  33: 33 */    this.statement = statement;
/*  34:    */  }
/*  35:    */  
/*  36:    */  public void evaluate() throws Throwable {
/*  37: 37 */    if (concurrent(this.method)) {
/*  38: 38 */      ExecutorCompletionService<Void> service = createCompletionService();
/*  39: 39 */      startThreads(service);
/*  40: 40 */      Throwable throwable = waitFor(service);
/*  41: 41 */      if (throwable != null)
/*  42: 42 */        throw throwable;
/*  43:    */    } else {
/*  44: 44 */      this.statement.evaluate();
/*  45:    */    }
/*  46:    */  }
/*  47:    */  
/*  48: 48 */  private ExecutorCompletionService createCompletionService() { (new Executor() {
/*  49:    */      private int count;
/*  50:    */      
/*  51: 51 */      public void execute(Runnable runnable) { new Thread(runnable, RunConcurrently.this.method.getName() + "-Thread-" + this.count++).start(); }
/*  52:    */    }); }
/*  53:    */  
/*  55:    */  private void startThreads(ExecutorCompletionService<Void> service)
/*  56:    */  {
/*  57: 57 */    CountDownLatch start = new CountDownLatch(1);
/*  58: 58 */    for (int i = 0; i < threadCount(this.method); i++)
/*  59: 59 */      service.submit(new StatementEvaluatingTask(this.statement, start));
/*  60: 60 */    start.countDown();
/*  61:    */  }
/*  62:    */  
/*  63:    */  private Throwable waitFor(ExecutorCompletionService<Void> service) {
/*  64: 64 */    Throwable throwable = null;
/*  65: 65 */    for (int i = 0; i < threadCount(this.method); i++) {
/*  66:    */      try {
/*  67: 67 */        service.take().get();
/*  68:    */      } catch (InterruptedException e) {
/*  69: 69 */        Thread.currentThread().interrupt();
/*  70:    */      } catch (ExecutionException e) {
/*  71: 71 */        throwable = e.getCause();
/*  72: 72 */        break;
/*  73:    */      }
/*  74:    */    }
/*  75: 75 */    return throwable;
/*  76:    */  }
/*  77:    */  
/*  78:    */  private static boolean concurrent(FrameworkMethod method) {
/*  79: 79 */    return method.getAnnotation(Concurrent.class) != null;
/*  80:    */  }
/*  81:    */  
/*  82:    */  private static int threadCount(FrameworkMethod method) {
/*  83: 83 */    return ((Concurrent)method.getAnnotation(Concurrent.class)).count();
/*  84:    */  }
/*  85:    */  
/*  86:    */  private static class StatementEvaluatingTask implements Callable<Void> {
/*  87:    */    private final Statement statement;
/*  88:    */    private final CountDownLatch start;
/*  89:    */    
/*  90:    */    public StatementEvaluatingTask(Statement statement, CountDownLatch start) {
/*  91: 91 */      this.statement = statement;
/*  92: 92 */      this.start = start;
/*  93:    */    }
/*  94:    */    
/*  95:    */    public Void call() throws Exception {
/*  96:    */      try {
/*  97: 97 */        this.start.await();
/*  98: 98 */        this.statement.evaluate();
/*  99:    */      } catch (Throwable throwable) {
/* 100:100 */        throw new RuntimeException(throwable);
/* 101:    */      }
/* 102:102 */      return null;
/* 103:    */    }
/* 104:    */  }
/* 105:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.RunConcurrently
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */