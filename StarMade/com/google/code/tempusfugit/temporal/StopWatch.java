/*  1:   */package com.google.code.tempusfugit.temporal;
/*  2:   */
/*  3:   */import java.util.Date;
/*  4:   */
/* 23:   */public final class StopWatch
/* 24:   */{
/* 25:   */  private Clock clock;
/* 26:   */  private Date startDate;
/* 27:   */  private Date lastMarkDate;
/* 28:   */  
/* 29:   */  public static StopWatch start(Clock clock)
/* 30:   */  {
/* 31:31 */    return new StopWatch(clock);
/* 32:   */  }
/* 33:   */  
/* 34:   */  private StopWatch(Clock clock) {
/* 35:35 */    this.clock = clock;
/* 36:36 */    this.startDate = ((Date)clock.create());
/* 37:   */  }
/* 38:   */  
/* 39:   */  public Date getStartDate() {
/* 40:40 */    return this.startDate;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public Duration markAndGetTotalElapsedTime() {
/* 44:44 */    this.lastMarkDate = ((Date)this.clock.create());
/* 45:45 */    return getTotalElapsedTime();
/* 46:   */  }
/* 47:   */  
/* 48:   */  private Duration getTotalElapsedTime() {
/* 49:49 */    long startTime = this.startDate.getTime();
/* 50:50 */    long lastMarkTime = this.lastMarkDate.getTime();
/* 51:51 */    assert (lastMarkTime >= startTime);
/* 52:52 */    return Duration.millis(lastMarkTime - startTime);
/* 53:   */  }
/* 54:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.temporal.StopWatch
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */