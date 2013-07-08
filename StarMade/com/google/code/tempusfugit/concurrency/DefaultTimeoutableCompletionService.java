/*  1:   */package com.google.code.tempusfugit.concurrency;
/*  2:   */
/*  3:   */import com.google.code.tempusfugit.temporal.Clock;
/*  4:   */import com.google.code.tempusfugit.temporal.Duration;
/*  5:   */import com.google.code.tempusfugit.temporal.RealClock;
/*  6:   */import java.util.ArrayList;
/*  7:   */import java.util.Iterator;
/*  8:   */import java.util.List;
/*  9:   */import java.util.concurrent.Callable;
/* 10:   */import java.util.concurrent.CompletionService;
/* 11:   */import java.util.concurrent.ExecutionException;
/* 12:   */import java.util.concurrent.Future;
/* 13:   */import java.util.concurrent.TimeoutException;
/* 14:   */
/* 31:   */public class DefaultTimeoutableCompletionService
/* 32:   */  implements TimeoutableCompletionService
/* 33:   */{
/* 34:   */  private static final boolean INTERRUPT_IF_RUNNING = true;
/* 35:35 */  private static final Duration DEFAULT_TIMEOUT = Duration.seconds(30L);
/* 36:   */  private final CompletionService completionService;
/* 37:   */  private final Duration timeout;
/* 38:   */  private final Clock time;
/* 39:   */  
/* 40:   */  public DefaultTimeoutableCompletionService(CompletionService completionService)
/* 41:   */  {
/* 42:42 */    this(completionService, DEFAULT_TIMEOUT, RealClock.now());
/* 43:   */  }
/* 44:   */  
/* 45:   */  public DefaultTimeoutableCompletionService(CompletionService completionService, Duration timeout, Clock time) {
/* 46:46 */    this.timeout = timeout;
/* 47:47 */    this.time = time;
/* 48:48 */    this.completionService = completionService;
/* 49:   */  }
/* 50:   */  
/* 51:   */  public <T> List<T> submit(List<? extends Callable<T>> tasks) throws ExecutionException, TimeoutException {
/* 52:52 */    List<Future<T>> submitted = new ArrayList();
/* 53:   */    try {
/* 54:54 */      for (Callable task : tasks)
/* 55:55 */        submitted.add(this.completionService.submit(task));
/* 56:   */      Iterator i$;
/* 57:57 */      Future<T> future; return waitFor(tasks.size(), this.timeout);
/* 58:   */    } finally {
/* 59:59 */      for (Future<T> future : submitted) {
/* 60:60 */        future.cancel(true);
/* 61:   */      }
/* 62:   */    }
/* 63:   */  }
/* 64:   */  
/* 65:   */  private <T> List<T> waitFor(int tasks, Duration timeout) throws ExecutionException, TimeoutException {
/* 66:66 */    List<T> completed = new ArrayList();
/* 67:67 */    Interrupter interrupter = Interrupter.interrupt(Thread.currentThread()).using(this.time).after(timeout);
/* 68:   */    try {
/* 69:69 */      for (int i = 0; i < tasks; i++) {
/* 70:   */        try {
/* 71:71 */          Future<T> future = this.completionService.take();
/* 72:72 */          completed.add(future.get());
/* 73:   */        } catch (InterruptedException e) {
/* 74:74 */          throw new TimeoutExceptionWithResults("timed out after " + timeout.toString(), completed);
/* 75:   */        }
/* 76:   */      }
/* 77:   */    } finally {
/* 78:78 */      interrupter.cancel();
/* 79:   */    }
/* 80:80 */    return completed;
/* 81:   */  }
/* 82:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.DefaultTimeoutableCompletionService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */