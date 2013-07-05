/*    */ package com.bulletphysics.linearmath;
/*    */ 
/*    */ public class ScalarUtil
/*    */ {
/*    */   public static float fsel(float a, float b, float c)
/*    */   {
/* 36 */     return a >= 0.0F ? b : c;
/*    */   }
/*    */ 
/*    */   public static boolean fuzzyZero(float x) {
/* 40 */     return Math.abs(x) < 1.192093E-007F;
/*    */   }
/*    */ 
/*    */   public static float atan2Fast(float y, float x) {
/* 44 */     float coeff_1 = 0.7853982F;
/* 45 */     float coeff_2 = 3.0F * coeff_1;
/* 46 */     float abs_y = Math.abs(y);
/*    */     float angle;
/*    */     float angle;
/* 48 */     if (x >= 0.0F) {
/* 49 */       float r = (x - abs_y) / (x + abs_y);
/* 50 */       angle = coeff_1 - coeff_1 * r;
/*    */     }
/*    */     else {
/* 53 */       float r = (x + abs_y) / (abs_y - x);
/* 54 */       angle = coeff_2 - coeff_1 * r;
/*    */     }
/* 56 */     return y < 0.0F ? -angle : angle;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.ScalarUtil
 * JD-Core Version:    0.6.2
 */