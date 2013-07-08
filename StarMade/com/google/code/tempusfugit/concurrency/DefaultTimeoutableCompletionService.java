package com.google.code.tempusfugit.concurrency;

import com.google.code.tempusfugit.temporal.Clock;
import com.google.code.tempusfugit.temporal.Duration;
import com.google.code.tempusfugit.temporal.RealClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

public class DefaultTimeoutableCompletionService
  implements TimeoutableCompletionService
{
  private static final boolean INTERRUPT_IF_RUNNING = true;
  private static final Duration DEFAULT_TIMEOUT = Duration.seconds(30L);
  private final CompletionService completionService;
  private final Duration timeout;
  private final Clock time;
  
  public DefaultTimeoutableCompletionService(CompletionService completionService)
  {
    this(completionService, DEFAULT_TIMEOUT, RealClock.now());
  }
  
  public DefaultTimeoutableCompletionService(CompletionService completionService, Duration timeout, Clock time)
  {
    this.timeout = timeout;
    this.time = time;
    this.completionService = completionService;
  }
  
  public <T> List<T> submit(List<? extends Callable<T>> tasks)
    throws ExecutionException, TimeoutException
  {
    List<Future<T>> submitted = new ArrayList();
    try
    {
      Iterator local_i$ = tasks.iterator();
      while (local_i$.hasNext())
      {
        Callable task = (Callable)local_i$.next();
        submitted.add(this.completionService.submit(task));
      }
      local_i$ = waitFor(tasks.size(), this.timeout);
      Iterator task;
      Future<T> future;
      return local_i$;
    }
    finally
    {
      Iterator local_i$1 = submitted.iterator();
      while (local_i$1.hasNext())
      {
        Future<T> future = (Future)local_i$1.next();
        future.cancel(true);
      }
    }
  }
  
  private <T> List<T> waitFor(int tasks, Duration timeout)
    throws ExecutionException, TimeoutException
  {
    List<T> completed = new ArrayList();
    Interrupter interrupter = Interrupter.interrupt(Thread.currentThread()).using(this.time).after(timeout);
    try
    {
      for (int local_i = 0; local_i < tasks; local_i++) {
        try
        {
          Future<T> future = this.completionService.take();
          completed.add(future.get());
        }
        catch (InterruptedException future)
        {
          throw new TimeoutExceptionWithResults("timed out after " + timeout.toString(), completed);
        }
      }
    }
    finally
    {
      interrupter.cancel();
    }
    return completed;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.DefaultTimeoutableCompletionService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */