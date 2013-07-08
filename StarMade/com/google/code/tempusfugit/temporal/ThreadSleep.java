package com.google.code.tempusfugit.temporal;

public class ThreadSleep
  implements Sleeper
{
  private final Duration period;
  
  public ThreadSleep(Duration period)
  {
    this.period = period;
  }
  
  public void sleep()
    throws InterruptedException
  {
    Thread.sleep(this.period.inMillis());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.temporal.ThreadSleep
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */