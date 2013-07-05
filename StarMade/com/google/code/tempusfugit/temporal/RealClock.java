/*    */ package com.google.code.tempusfugit.temporal;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class RealClock
/*    */   implements Clock
/*    */ {
/*    */   public static RealClock now()
/*    */   {
/* 27 */     return new RealClock();
/*    */   }
/*    */ 
/*    */   public static RealClock today() {
/* 31 */     return new RealClock();
/*    */   }
/*    */ 
/*    */   public Date create() {
/* 35 */     return new Date();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.temporal.RealClock
 * JD-Core Version:    0.6.2
 */