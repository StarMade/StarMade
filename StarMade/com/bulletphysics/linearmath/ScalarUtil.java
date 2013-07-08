/*  1:   */package com.bulletphysics.linearmath;
/*  2:   */
/* 32:   */public class ScalarUtil
/* 33:   */{
/* 34:   */  public static float fsel(float a, float b, float c)
/* 35:   */  {
/* 36:36 */    return a >= 0.0F ? b : c;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public static boolean fuzzyZero(float x) {
/* 40:40 */    return Math.abs(x) < 1.192093E-007F;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public static float atan2Fast(float y, float x) {
/* 44:44 */    float coeff_1 = 0.7853982F;
/* 45:45 */    float coeff_2 = 3.0F * coeff_1;
/* 46:46 */    float abs_y = Math.abs(y);
/* 47:   */    float angle;
/* 48:48 */    float angle; if (x >= 0.0F) {
/* 49:49 */      float r = (x - abs_y) / (x + abs_y);
/* 50:50 */      angle = coeff_1 - coeff_1 * r;
/* 51:   */    }
/* 52:   */    else {
/* 53:53 */      float r = (x + abs_y) / (abs_y - x);
/* 54:54 */      angle = coeff_2 - coeff_1 * r;
/* 55:   */    }
/* 56:56 */    return y < 0.0F ? -angle : angle;
/* 57:   */  }
/* 58:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.ScalarUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */