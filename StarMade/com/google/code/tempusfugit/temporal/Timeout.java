package com.google.code.tempusfugit.temporal;

public final class Timeout
{
  private Duration duration;
  private StopWatch stopWatch;
  
  public static Timeout timeout(Duration duration)
  {
    return new Timeout(duration);
  }
  
  public static Timeout timeout(Duration duration, StopWatch stopWatch)
  {
    return new Timeout(duration, stopWatch);
  }
  
  private Timeout(Duration duration)
  {
    this(duration, startStopWatch());
  }
  
  private Timeout(Duration duration, StopWatch stopWatch)
  {
    if (duration.inMillis() <= 0L) {
      throw new IllegalArgumentException();
    }
    this.duration = duration;
    this.stopWatch = stopWatch;
  }
  
  public boolean hasExpired()
  {
    return this.stopWatch.markAndGetTotalElapsedTime().greaterThan(this.duration).booleanValue();
  }
  
  private static StopWatch startStopWatch()
  {
    return StopWatch.start(RealClock.now());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.temporal.Timeout
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */