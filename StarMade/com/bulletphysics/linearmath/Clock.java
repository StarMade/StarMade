/*  1:   */package com.bulletphysics.linearmath;
/*  2:   */
/* 18:   */public class Clock
/* 19:   */{
/* 20:   */  private long startTime;
/* 21:   */  
/* 37:   */  public Clock()
/* 38:   */  {
/* 39:39 */    reset();
/* 40:   */  }
/* 41:   */  
/* 44:   */  public void reset()
/* 45:   */  {
/* 46:46 */    this.startTime = System.nanoTime();
/* 47:   */  }
/* 48:   */  
/* 51:   */  public long getTimeMilliseconds()
/* 52:   */  {
/* 53:53 */    return (System.nanoTime() - this.startTime) / 1000000L;
/* 54:   */  }
/* 55:   */  
/* 58:   */  public long getTimeMicroseconds()
/* 59:   */  {
/* 60:60 */    return (System.nanoTime() - this.startTime) / 1000L;
/* 61:   */  }
/* 62:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.Clock
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */