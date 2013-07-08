package com.google.code.tempusfugit.concurrency;

import com.google.code.tempusfugit.temporal.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CountDownLatchWithTimeout
{
  private final CountDownLatch latch;
  
  private CountDownLatchWithTimeout(CountDownLatch latch)
  {
    this.latch = latch;
  }
  
  public static CountDownLatchWithTimeout await(CountDownLatch latch)
  {
    return new CountDownLatchWithTimeout(latch);
  }
  
  public void with(Duration timeout)
    throws InterruptedException, TimeoutException
  {
    if (!this.latch.await(timeout.inMillis(), TimeUnit.MILLISECONDS)) {
      throw new TimeoutException();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.CountDownLatchWithTimeout
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */