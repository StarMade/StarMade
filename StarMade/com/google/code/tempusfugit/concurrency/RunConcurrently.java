package com.google.code.tempusfugit.concurrency;

import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

class RunConcurrently
  extends Statement
{
  private final FrameworkMethod method;
  private final Statement statement;
  
  RunConcurrently(FrameworkMethod method, Statement statement)
  {
    this.method = method;
    this.statement = statement;
  }
  
  public void evaluate()
    throws Throwable
  {
    if (concurrent(this.method))
    {
      ExecutorCompletionService<Void> service = createCompletionService();
      startThreads(service);
      Throwable throwable = waitFor(service);
      if (throwable != null) {
        throw throwable;
      }
    }
    else
    {
      this.statement.evaluate();
    }
  }
  
  private ExecutorCompletionService createCompletionService()
  {
    (new Executor()
    {
      private int count;
      
      public void execute(Runnable runnable)
      {
        new Thread(runnable, RunConcurrently.this.method.getName() + "-Thread-" + this.count++).start();
      }
    });
  }
  
  private void startThreads(ExecutorCompletionService<Void> service)
  {
    CountDownLatch start = new CountDownLatch(1);
    for (int local_i = 0; local_i < threadCount(this.method); local_i++) {
      service.submit(new StatementEvaluatingTask(this.statement, start));
    }
    start.countDown();
  }
  
  private Throwable waitFor(ExecutorCompletionService<Void> service)
  {
    Throwable throwable = null;
    for (int local_i = 0; local_i < threadCount(this.method); local_i++) {
      try
      {
        service.take().get();
      }
      catch (InterruptedException local_e)
      {
        Thread.currentThread().interrupt();
      }
      catch (ExecutionException local_e)
      {
        throwable = local_e.getCause();
        break;
      }
    }
    return throwable;
  }
  
  private static boolean concurrent(FrameworkMethod method)
  {
    return method.getAnnotation(Concurrent.class) != null;
  }
  
  private static int threadCount(FrameworkMethod method)
  {
    return ((Concurrent)method.getAnnotation(Concurrent.class)).count();
  }
  
  private static class StatementEvaluatingTask
    implements Callable<Void>
  {
    private final Statement statement;
    private final CountDownLatch start;
    
    public StatementEvaluatingTask(Statement statement, CountDownLatch start)
    {
      this.statement = statement;
      this.start = start;
    }
    
    public Void call()
      throws Exception
    {
      try
      {
        this.start.await();
        this.statement.evaluate();
      }
      catch (Throwable throwable)
      {
        throw new RuntimeException(throwable);
      }
      return null;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.RunConcurrently
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */