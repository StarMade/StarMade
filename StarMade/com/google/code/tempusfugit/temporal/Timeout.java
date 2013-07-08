/*  1:   */package com.google.code.tempusfugit.temporal;
/*  2:   */
/*  9:   */public final class Timeout
/* 10:   */{
/* 11:   */  private Duration duration;
/* 12:   */  
/* 18:   */  private StopWatch stopWatch;
/* 19:   */  
/* 26:   */  public static Timeout timeout(Duration duration)
/* 27:   */  {
/* 28:28 */    return new Timeout(duration);
/* 29:   */  }
/* 30:   */  
/* 31:   */  public static Timeout timeout(Duration duration, StopWatch stopWatch)
/* 32:   */  {
/* 33:33 */    return new Timeout(duration, stopWatch);
/* 34:   */  }
/* 35:   */  
/* 36:   */  private Timeout(Duration duration) {
/* 37:37 */    this(duration, startStopWatch());
/* 38:   */  }
/* 39:   */  
/* 40:   */  private Timeout(Duration duration, StopWatch stopWatch) {
/* 41:41 */    if (duration.inMillis() <= 0L)
/* 42:42 */      throw new IllegalArgumentException();
/* 43:43 */    this.duration = duration;
/* 44:44 */    this.stopWatch = stopWatch;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public boolean hasExpired() {
/* 48:48 */    return this.stopWatch.markAndGetTotalElapsedTime().greaterThan(this.duration).booleanValue();
/* 49:   */  }
/* 50:   */  
/* 51:   */  private static StopWatch startStopWatch() {
/* 52:52 */    return StopWatch.start(RealClock.now());
/* 53:   */  }
/* 54:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.temporal.Timeout
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */