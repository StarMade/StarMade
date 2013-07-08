package com.bulletphysics.linearmath;

public class ScalarUtil
{
  public static float fsel(float local_a, float local_b, float local_c)
  {
    return local_a >= 0.0F ? local_b : local_c;
  }
  
  public static boolean fuzzyZero(float local_x)
  {
    return Math.abs(local_x) < 1.192093E-007F;
  }
  
  public static float atan2Fast(float local_y, float local_x)
  {
    float coeff_1 = 0.7853982F;
    float coeff_2 = 3.0F * coeff_1;
    float abs_y = Math.abs(local_y);
    float angle;
    float angle;
    if (local_x >= 0.0F)
    {
      float local_r = (local_x - abs_y) / (local_x + abs_y);
      angle = coeff_1 - coeff_1 * local_r;
    }
    else
    {
      float local_r = (local_x + abs_y) / (abs_y - local_x);
      angle = coeff_2 - coeff_1 * local_r;
    }
    return local_y < 0.0F ? -angle : angle;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.ScalarUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */