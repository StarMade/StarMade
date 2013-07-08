package com.bulletphysics.linearmath;

public class Clock
{
  private long startTime;
  
  public Clock()
  {
    reset();
  }
  
  public void reset()
  {
    this.startTime = System.nanoTime();
  }
  
  public long getTimeMilliseconds()
  {
    return (System.nanoTime() - this.startTime) / 1000000L;
  }
  
  public long getTimeMicroseconds()
  {
    return (System.nanoTime() - this.startTime) / 1000L;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.Clock
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */