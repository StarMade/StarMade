/*  1:   */package com.google.code.tempusfugit.concurrency;
/*  2:   */
/*  3:   */import java.util.concurrent.Executors;
/*  4:   */import java.util.concurrent.ThreadFactory;
/*  5:   */import java.util.concurrent.atomic.AtomicLong;
/*  6:   */import org.junit.runners.BlockJUnit4ClassRunner;
/*  7:   */import org.junit.runners.model.InitializationError;
/*  8:   */
/* 24:   */public class ConcurrentTestRunner
/* 25:   */  extends BlockJUnit4ClassRunner
/* 26:   */{
/* 27:   */  public ConcurrentTestRunner(Class<?> type)
/* 28:   */    throws InitializationError
/* 29:   */  {
/* 30:30 */    super(type);
/* 31:31 */    setScheduler(new ConcurrentScheduler(Executors.newCachedThreadPool(new ConcurrentTestRunnerThreadFactory(null))));
/* 32:   */  }
/* 33:   */  
/* 34:   */  private static class ConcurrentTestRunnerThreadFactory implements ThreadFactory {
/* 35:35 */    private AtomicLong count = new AtomicLong();
/* 36:   */    
/* 37:   */    public Thread newThread(Runnable runnable) {
/* 38:38 */      return new Thread(runnable, "ConcurrentTestRunner-Thread-" + this.count.getAndIncrement());
/* 39:   */    }
/* 40:   */  }
/* 41:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.ConcurrentTestRunner
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */