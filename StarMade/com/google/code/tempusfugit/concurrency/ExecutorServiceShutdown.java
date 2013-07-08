package com.google.code.tempusfugit.concurrency;

import com.google.code.tempusfugit.temporal.Conditions;
import com.google.code.tempusfugit.temporal.Duration;
import com.google.code.tempusfugit.temporal.Timeout;
import com.google.code.tempusfugit.temporal.WaitFor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class ExecutorServiceShutdown
{
  private final ExecutorService executor;
  
  private ExecutorServiceShutdown(ExecutorService executor)
  {
    this.executor = executor;
  }
  
  public static ExecutorServiceShutdown shutdown(ExecutorService executor)
  {
    return new ExecutorServiceShutdown(executor);
  }
  
  public Boolean waitingForCompletion(Duration duration)
  {
    if (this.executor == null) {
      return Boolean.valueOf(false);
    }
    this.executor.shutdown();
    return (Boolean)ThreadUtils.resetInterruptFlagWhen(awaitingTerminationIsInterrupted(duration));
  }
  
  public Boolean waitingForShutdown(Timeout timeout)
    throws TimeoutException, InterruptedException
  {
    if (this.executor == null) {
      return Boolean.valueOf(false);
    }
    this.executor.shutdownNow();
    WaitFor.waitOrTimeout(Conditions.shutdown(this.executor), timeout);
    return Boolean.valueOf(true);
  }
  
  @Deprecated
  public Boolean waitingForShutdown(Duration timeout)
    throws TimeoutException, InterruptedException
  {
    return waitingForShutdown(Timeout.timeout(timeout));
  }
  
  private Interruptible<Boolean> awaitingTerminationIsInterrupted(final Duration timeout)
  {
    new Interruptible()
    {
      public Boolean call()
        throws InterruptedException
      {
        return Boolean.valueOf(ExecutorServiceShutdown.this.executor.awaitTermination(timeout.inMillis(), TimeUnit.MILLISECONDS));
      }
    };
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.ExecutorServiceShutdown
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */