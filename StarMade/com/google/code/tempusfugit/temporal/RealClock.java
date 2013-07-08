/*  1:   */package com.google.code.tempusfugit.temporal;
/*  2:   */
/*  3:   */import java.util.Date;
/*  4:   */
/* 22:   */public class RealClock
/* 23:   */  implements Clock
/* 24:   */{
/* 25:   */  public static RealClock now()
/* 26:   */  {
/* 27:27 */    return new RealClock();
/* 28:   */  }
/* 29:   */  
/* 30:   */  public static RealClock today() {
/* 31:31 */    return new RealClock();
/* 32:   */  }
/* 33:   */  
/* 34:   */  public Date create() {
/* 35:35 */    return new Date();
/* 36:   */  }
/* 37:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.temporal.RealClock
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */