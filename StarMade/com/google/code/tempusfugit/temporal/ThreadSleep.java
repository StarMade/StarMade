/*  1:   */package com.google.code.tempusfugit.temporal;
/*  2:   */
/* 10:   */public class ThreadSleep
/* 11:   */  implements Sleeper
/* 12:   */{
/* 13:   */  private final Duration period;
/* 14:   */  
/* 22:   */  public ThreadSleep(Duration period)
/* 23:   */  {
/* 24:24 */    this.period = period;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public void sleep() throws InterruptedException {
/* 28:28 */    Thread.sleep(this.period.inMillis());
/* 29:   */  }
/* 30:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.temporal.ThreadSleep
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */