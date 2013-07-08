/*  1:   */package com.google.code.tempusfugit.concurrency;
/*  2:   */
/*  3:   */import com.google.code.tempusfugit.temporal.Duration;
/*  4:   */import java.util.concurrent.CountDownLatch;
/*  5:   */import java.util.concurrent.TimeUnit;
/*  6:   */import java.util.concurrent.TimeoutException;
/*  7:   */
/* 25:   */public class CountDownLatchWithTimeout
/* 26:   */{
/* 27:   */  private final CountDownLatch latch;
/* 28:   */  
/* 29:   */  private CountDownLatchWithTimeout(CountDownLatch latch)
/* 30:   */  {
/* 31:31 */    this.latch = latch;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public static CountDownLatchWithTimeout await(CountDownLatch latch) {
/* 35:35 */    return new CountDownLatchWithTimeout(latch);
/* 36:   */  }
/* 37:   */  
/* 38:   */  public void with(Duration timeout) throws InterruptedException, TimeoutException {
/* 39:39 */    if (!this.latch.await(timeout.inMillis(), TimeUnit.MILLISECONDS)) {
/* 40:40 */      throw new TimeoutException();
/* 41:   */    }
/* 42:   */  }
/* 43:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.CountDownLatchWithTimeout
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */