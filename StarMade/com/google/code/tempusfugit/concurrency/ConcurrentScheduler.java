package com.google.code.tempusfugit.concurrency;

import com.google.code.tempusfugit.temporal.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;
import org.junit.runners.model.RunnerScheduler;

class ConcurrentScheduler
  implements RunnerScheduler
{
  private final ExecutorService executor;
  
  public ConcurrentScheduler(ExecutorService executor)
  {
    this.executor = executor;
  }
  
  public void schedule(Runnable childStatement)
  {
    this.executor.submit(childStatement);
  }
  
  public void finished()
  {
    Boolean completed = ExecutorServiceShutdown.shutdown(this.executor).waitingForCompletion(Duration.days(365L));
    if (Thread.currentThread().isInterrupted()) {
      throw new RuntimeException(new InterruptedException("scheduler shutdown was interrupted"));
    }
    if (!completed.booleanValue()) {
      throw new RuntimeException(new TimeoutException("scheduler shutdown timed out before tests completed"));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.ConcurrentScheduler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */