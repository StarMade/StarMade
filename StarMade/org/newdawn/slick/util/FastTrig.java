/*    */ package org.newdawn.slick.util;
/*    */ 
/*    */ public class FastTrig
/*    */ {
/*    */   private static double reduceSinAngle(double radians)
/*    */   {
/* 19 */     double orig = radians;
/* 20 */     radians %= 6.283185307179586D;
/* 21 */     if (Math.abs(radians) > 3.141592653589793D) {
/* 22 */       radians -= 6.283185307179586D;
/*    */     }
/* 24 */     if (Math.abs(radians) > 1.570796326794897D) {
/* 25 */       radians = 3.141592653589793D - radians;
/*    */     }
/*    */ 
/* 28 */     return radians;
/*    */   }
/*    */ 
/*    */   public static double sin(double radians)
/*    */   {
/* 38 */     radians = reduceSinAngle(radians);
/* 39 */     if (Math.abs(radians) <= 0.7853981633974483D) {
/* 40 */       return Math.sin(radians);
/*    */     }
/* 42 */     return Math.cos(1.570796326794897D - radians);
/*    */   }
/*    */ 
/*    */   public static double cos(double radians)
/*    */   {
/* 53 */     return sin(radians + 1.570796326794897D);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.FastTrig
 * JD-Core Version:    0.6.2
 */