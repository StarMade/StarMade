/*  1:   */package com.google.code.tempusfugit.concurrency;
/*  2:   */
/*  3:   */import com.google.code.tempusfugit.temporal.Duration;
/*  4:   */import java.util.concurrent.ExecutorService;
/*  5:   */import java.util.concurrent.TimeoutException;
/*  6:   */import org.junit.runners.model.RunnerScheduler;
/*  7:   */
/* 25:   */class ConcurrentScheduler
/* 26:   */  implements RunnerScheduler
/* 27:   */{
/* 28:   */  private final ExecutorService executor;
/* 29:   */  
/* 30:   */  public ConcurrentScheduler(ExecutorService executor)
/* 31:   */  {
/* 32:32 */    this.executor = executor;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public void schedule(Runnable childStatement) {
/* 36:36 */    this.executor.submit(childStatement);
/* 37:   */  }
/* 38:   */  
/* 39:   */  public void finished() {
/* 40:40 */    Boolean completed = ExecutorServiceShutdown.shutdown(this.executor).waitingForCompletion(Duration.days(365L));
/* 41:41 */    if (Thread.currentThread().isInterrupted())
/* 42:42 */      throw new RuntimeException(new InterruptedException("scheduler shutdown was interrupted"));
/* 43:43 */    if (!completed.booleanValue()) {
/* 44:44 */      throw new RuntimeException(new TimeoutException("scheduler shutdown timed out before tests completed"));
/* 45:   */    }
/* 46:   */  }
/* 47:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.ConcurrentScheduler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */