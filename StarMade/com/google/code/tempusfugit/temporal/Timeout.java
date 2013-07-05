/*    */ package com.google.code.tempusfugit.temporal;
/*    */ 
/*    */ public final class Timeout
/*    */ {
/*    */   private Duration duration;
/*    */   private StopWatch stopWatch;
/*    */ 
/*    */   public static Timeout timeout(Duration duration)
/*    */   {
/* 28 */     return new Timeout(duration);
/*    */   }
/*    */ 
/*    */   public static Timeout timeout(Duration duration, StopWatch stopWatch)
/*    */   {
/* 33 */     return new Timeout(duration, stopWatch);
/*    */   }
/*    */ 
/*    */   private Timeout(Duration duration) {
/* 37 */     this(duration, startStopWatch());
/*    */   }
/*    */ 
/*    */   private Timeout(Duration duration, StopWatch stopWatch) {
/* 41 */     if (duration.inMillis() <= 0L)
/* 42 */       throw new IllegalArgumentException();
/* 43 */     this.duration = duration;
/* 44 */     this.stopWatch = stopWatch;
/*    */   }
/*    */ 
/*    */   public boolean hasExpired() {
/* 48 */     return this.stopWatch.markAndGetTotalElapsedTime().greaterThan(this.duration).booleanValue();
/*    */   }
/*    */ 
/*    */   private static StopWatch startStopWatch() {
/* 52 */     return StopWatch.start(RealClock.now());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.temporal.Timeout
 * JD-Core Version:    0.6.2
 */