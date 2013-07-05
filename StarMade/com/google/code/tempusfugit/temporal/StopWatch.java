/*    */ package com.google.code.tempusfugit.temporal;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public final class StopWatch
/*    */ {
/*    */   private Clock clock;
/*    */   private Date startDate;
/*    */   private Date lastMarkDate;
/*    */ 
/*    */   public static StopWatch start(Clock clock)
/*    */   {
/* 31 */     return new StopWatch(clock);
/*    */   }
/*    */ 
/*    */   private StopWatch(Clock clock) {
/* 35 */     this.clock = clock;
/* 36 */     this.startDate = ((Date)clock.create());
/*    */   }
/*    */ 
/*    */   public Date getStartDate() {
/* 40 */     return this.startDate;
/*    */   }
/*    */ 
/*    */   public Duration markAndGetTotalElapsedTime() {
/* 44 */     this.lastMarkDate = ((Date)this.clock.create());
/* 45 */     return getTotalElapsedTime();
/*    */   }
/*    */ 
/*    */   private Duration getTotalElapsedTime() {
/* 49 */     long startTime = this.startDate.getTime();
/* 50 */     long lastMarkTime = this.lastMarkDate.getTime();
/* 51 */     assert (lastMarkTime >= startTime);
/* 52 */     return Duration.millis(lastMarkTime - startTime);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.temporal.StopWatch
 * JD-Core Version:    0.6.2
 */