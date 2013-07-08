package com.google.code.tempusfugit.temporal;

import java.util.concurrent.TimeoutException;

public final class WaitFor
{
  public static final Duration SLEEP_PERIOD = Duration.millis(50L);
  
  public static void waitOrTimeout(Condition condition, Timeout timeout)
    throws InterruptedException, TimeoutException
  {
    waitOrTimeout(condition, timeout, new ThreadSleep(SLEEP_PERIOD));
  }
  
  public static void waitOrTimeout(Condition condition, Timeout timeout, Sleeper sleeper)
    throws TimeoutException, InterruptedException
  {
    if (success(condition, timeout, sleeper)) {
      return;
    }
    throw new TimeoutException();
  }
  
  @Deprecated
  public static void waitOrTimeout(Condition condition, Duration duration)
    throws TimeoutException, InterruptedException
  {
    waitOrTimeout(condition, Timeout.timeout(duration));
  }
  
  @Deprecated
  public static void waitOrTimeout(Condition condition, Duration duration, StopWatch stopWatch)
    throws TimeoutException, InterruptedException
  {
    waitOrTimeout(condition, Timeout.timeout(duration, stopWatch));
  }
  
  public static void waitUntil(Timeout timeout)
    throws InterruptedException
  {
    while (!timeout.hasExpired()) {
      Thread.sleep(SLEEP_PERIOD.inMillis());
    }
  }
  
  private static boolean success(Condition condition, Timeout timeout, Sleeper sleeper)
    throws InterruptedException
  {
    while (!timeout.hasExpired())
    {
      if (condition.isSatisfied()) {
        return true;
      }
      sleeper.sleep();
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.temporal.WaitFor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */