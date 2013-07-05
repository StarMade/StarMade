/*    */ package com.google.code.tempusfugit.temporal;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public final class MovableClock
/*    */   implements Clock
/*    */ {
/*    */   private final Date now;
/*    */ 
/*    */   public MovableClock()
/*    */   {
/* 26 */     this.now = new Date(0L);
/*    */   }
/*    */ 
/*    */   public MovableClock(Date date) {
/* 30 */     this.now = new Date(date.getTime());
/*    */   }
/*    */ 
/*    */   public Date create() {
/* 34 */     return new Date(this.now.getTime());
/*    */   }
/*    */ 
/*    */   public void setTime(Duration time) {
/* 38 */     this.now.setTime(time.inMillis());
/*    */   }
/*    */ 
/*    */   public void incrementBy(Duration time) {
/* 42 */     this.now.setTime(this.now.getTime() + time.inMillis());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.temporal.MovableClock
 * JD-Core Version:    0.6.2
 */