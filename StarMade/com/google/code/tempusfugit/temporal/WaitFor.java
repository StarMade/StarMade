/*  1:   */package com.google.code.tempusfugit.temporal;
/*  2:   */
/*  3:   */import java.util.concurrent.TimeoutException;
/*  4:   */
/* 24:   */public final class WaitFor
/* 25:   */{
/* 26:26 */  public static final Duration SLEEP_PERIOD = Duration.millis(50L);
/* 27:   */  
/* 30:   */  public static void waitOrTimeout(Condition condition, Timeout timeout)
/* 31:   */    throws InterruptedException, TimeoutException
/* 32:   */  {
/* 33:33 */    waitOrTimeout(condition, timeout, new ThreadSleep(SLEEP_PERIOD));
/* 34:   */  }
/* 35:   */  
/* 36:   */  public static void waitOrTimeout(Condition condition, Timeout timeout, Sleeper sleeper) throws TimeoutException, InterruptedException
/* 37:   */  {
/* 38:38 */    if (success(condition, timeout, sleeper))
/* 39:39 */      return;
/* 40:40 */    throw new TimeoutException();
/* 41:   */  }
/* 42:   */  
/* 43:   */  @Deprecated
/* 44:   */  public static void waitOrTimeout(Condition condition, Duration duration) throws TimeoutException, InterruptedException {
/* 45:45 */    waitOrTimeout(condition, Timeout.timeout(duration));
/* 46:   */  }
/* 47:   */  
/* 48:   */  @Deprecated
/* 49:   */  public static void waitOrTimeout(Condition condition, Duration duration, StopWatch stopWatch) throws TimeoutException, InterruptedException {
/* 50:50 */    waitOrTimeout(condition, Timeout.timeout(duration, stopWatch));
/* 51:   */  }
/* 52:   */  
/* 53:   */  public static void waitUntil(Timeout timeout) throws InterruptedException {
/* 54:54 */    while (!timeout.hasExpired())
/* 55:55 */      Thread.sleep(SLEEP_PERIOD.inMillis());
/* 56:   */  }
/* 57:   */  
/* 58:   */  private static boolean success(Condition condition, Timeout timeout, Sleeper sleeper) throws InterruptedException {
/* 59:59 */    while (!timeout.hasExpired()) {
/* 60:60 */      if (condition.isSatisfied()) {
/* 61:61 */        return true;
/* 62:   */      }
/* 63:63 */      sleeper.sleep();
/* 64:   */    }
/* 65:65 */    return false;
/* 66:   */  }
/* 67:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.temporal.WaitFor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */