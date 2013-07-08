package org.newdawn.slick.util;

public class FastTrig
{
  private static double reduceSinAngle(double radians)
  {
    double orig = radians;
    radians %= 6.283185307179586D;
    if (Math.abs(radians) > 3.141592653589793D) {
      radians -= 6.283185307179586D;
    }
    if (Math.abs(radians) > 1.570796326794897D) {
      radians = 3.141592653589793D - radians;
    }
    return radians;
  }
  
  public static double sin(double radians)
  {
    radians = reduceSinAngle(radians);
    if (Math.abs(radians) <= 0.7853981633974483D) {
      return Math.sin(radians);
    }
    return Math.cos(1.570796326794897D - radians);
  }
  
  public static double cos(double radians)
  {
    return sin(radians + 1.570796326794897D);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.FastTrig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */