/*  1:   */package com.google.code.tempusfugit.concurrency;
/*  2:   */
/*  3:   */import com.google.code.tempusfugit.temporal.Conditions;
/*  4:   */import com.google.code.tempusfugit.temporal.Duration;
/*  5:   */import com.google.code.tempusfugit.temporal.Timeout;
/*  6:   */import com.google.code.tempusfugit.temporal.WaitFor;
/*  7:   */import java.util.concurrent.ExecutorService;
/*  8:   */import java.util.concurrent.TimeUnit;
/*  9:   */import java.util.concurrent.TimeoutException;
/* 10:   */
/* 30:   */public final class ExecutorServiceShutdown
/* 31:   */{
/* 32:   */  private final ExecutorService executor;
/* 33:   */  
/* 34:   */  private ExecutorServiceShutdown(ExecutorService executor)
/* 35:   */  {
/* 36:36 */    this.executor = executor;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public static ExecutorServiceShutdown shutdown(ExecutorService executor) {
/* 40:40 */    return new ExecutorServiceShutdown(executor);
/* 41:   */  }
/* 42:   */  
/* 43:   */  public Boolean waitingForCompletion(Duration duration) {
/* 44:44 */    if (this.executor == null)
/* 45:45 */      return Boolean.valueOf(false);
/* 46:46 */    this.executor.shutdown();
/* 47:47 */    return (Boolean)ThreadUtils.resetInterruptFlagWhen(awaitingTerminationIsInterrupted(duration));
/* 48:   */  }
/* 49:   */  
/* 50:   */  public Boolean waitingForShutdown(Timeout timeout) throws TimeoutException, InterruptedException
/* 51:   */  {
/* 52:52 */    if (this.executor == null)
/* 53:53 */      return Boolean.valueOf(false);
/* 54:54 */    this.executor.shutdownNow();
/* 55:55 */    WaitFor.waitOrTimeout(Conditions.shutdown(this.executor), timeout);
/* 56:56 */    return Boolean.valueOf(true);
/* 57:   */  }
/* 58:   */  
/* 59:   */  @Deprecated
/* 60:   */  public Boolean waitingForShutdown(Duration timeout) throws TimeoutException, InterruptedException {
/* 61:61 */    return waitingForShutdown(Timeout.timeout(timeout));
/* 62:   */  }
/* 63:   */  
/* 64:   */  private Interruptible<Boolean> awaitingTerminationIsInterrupted(final Duration timeout) {
/* 65:65 */    new Interruptible() {
/* 66:   */      public Boolean call() throws InterruptedException {
/* 67:67 */        return Boolean.valueOf(ExecutorServiceShutdown.this.executor.awaitTermination(timeout.inMillis(), TimeUnit.MILLISECONDS));
/* 68:   */      }
/* 69:   */    };
/* 70:   */  }
/* 71:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.ExecutorServiceShutdown
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */