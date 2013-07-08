package com.google.code.tempusfugit.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class ConcurrentTestRunner
  extends BlockJUnit4ClassRunner
{
  public ConcurrentTestRunner(Class<?> type)
    throws InitializationError
  {
    super(type);
    setScheduler(new ConcurrentScheduler(Executors.newCachedThreadPool(new ConcurrentTestRunnerThreadFactory(null))));
  }
  
  private static class ConcurrentTestRunnerThreadFactory
    implements ThreadFactory
  {
    private AtomicLong count = new AtomicLong();
    
    public Thread newThread(Runnable runnable)
    {
      return new Thread(runnable, "ConcurrentTestRunner-Thread-" + this.count.getAndIncrement());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.ConcurrentTestRunner
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */