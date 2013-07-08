package com.google.code.tempusfugit.temporal;

import java.util.Date;

public final class StopWatch
{
  private Clock clock;
  private Date startDate;
  private Date lastMarkDate;
  
  public static StopWatch start(Clock clock)
  {
    return new StopWatch(clock);
  }
  
  private StopWatch(Clock clock)
  {
    this.clock = clock;
    this.startDate = ((Date)clock.create());
  }
  
  public Date getStartDate()
  {
    return this.startDate;
  }
  
  public Duration markAndGetTotalElapsedTime()
  {
    this.lastMarkDate = ((Date)this.clock.create());
    return getTotalElapsedTime();
  }
  
  private Duration getTotalElapsedTime()
  {
    long startTime = this.startDate.getTime();
    long lastMarkTime = this.lastMarkDate.getTime();
    assert (lastMarkTime >= startTime);
    return Duration.millis(lastMarkTime - startTime);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.temporal.StopWatch
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */