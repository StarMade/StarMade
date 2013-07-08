package com.google.code.tempusfugit.temporal;

import java.util.Date;

public final class MovableClock
  implements Clock
{
  private final Date now;
  
  public MovableClock()
  {
    this.now = new Date(0L);
  }
  
  public MovableClock(Date date)
  {
    this.now = new Date(date.getTime());
  }
  
  public Date create()
  {
    return new Date(this.now.getTime());
  }
  
  public void setTime(Duration time)
  {
    this.now.setTime(time.inMillis());
  }
  
  public void incrementBy(Duration time)
  {
    this.now.setTime(this.now.getTime() + time.inMillis());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.temporal.MovableClock
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */