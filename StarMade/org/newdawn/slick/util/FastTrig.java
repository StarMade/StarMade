/*  1:   */package org.newdawn.slick.util;
/*  2:   */
/* 15:   */public class FastTrig
/* 16:   */{
/* 17:   */  private static double reduceSinAngle(double radians)
/* 18:   */  {
/* 19:19 */    double orig = radians;
/* 20:20 */    radians %= 6.283185307179586D;
/* 21:21 */    if (Math.abs(radians) > 3.141592653589793D) {
/* 22:22 */      radians -= 6.283185307179586D;
/* 23:   */    }
/* 24:24 */    if (Math.abs(radians) > 1.570796326794897D) {
/* 25:25 */      radians = 3.141592653589793D - radians;
/* 26:   */    }
/* 27:   */    
/* 28:28 */    return radians;
/* 29:   */  }
/* 30:   */  
/* 36:   */  public static double sin(double radians)
/* 37:   */  {
/* 38:38 */    radians = reduceSinAngle(radians);
/* 39:39 */    if (Math.abs(radians) <= 0.7853981633974483D) {
/* 40:40 */      return Math.sin(radians);
/* 41:   */    }
/* 42:42 */    return Math.cos(1.570796326794897D - radians);
/* 43:   */  }
/* 44:   */  
/* 51:   */  public static double cos(double radians)
/* 52:   */  {
/* 53:53 */    return sin(radians + 1.570796326794897D);
/* 54:   */  }
/* 55:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.FastTrig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */