/*  1:   */package com.google.code.tempusfugit.temporal;
/*  2:   */
/*  3:   */import java.util.Date;
/*  4:   */
/* 19:   */public final class MovableClock
/* 20:   */  implements Clock
/* 21:   */{
/* 22:   */  private final Date now;
/* 23:   */  
/* 24:   */  public MovableClock()
/* 25:   */  {
/* 26:26 */    this.now = new Date(0L);
/* 27:   */  }
/* 28:   */  
/* 29:   */  public MovableClock(Date date) {
/* 30:30 */    this.now = new Date(date.getTime());
/* 31:   */  }
/* 32:   */  
/* 33:   */  public Date create() {
/* 34:34 */    return new Date(this.now.getTime());
/* 35:   */  }
/* 36:   */  
/* 37:   */  public void setTime(Duration time) {
/* 38:38 */    this.now.setTime(time.inMillis());
/* 39:   */  }
/* 40:   */  
/* 41:   */  public void incrementBy(Duration time) {
/* 42:42 */    this.now.setTime(this.now.getTime() + time.inMillis());
/* 43:   */  }
/* 44:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.temporal.MovableClock
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */