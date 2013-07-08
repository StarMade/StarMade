package com.jcraft.jorbis;

class Lsp
{
  static final float M_PI = 3.141593F;
  
  static void lsp_to_curve(float[] paramArrayOfFloat1, int[] paramArrayOfInt, int paramInt1, int paramInt2, float[] paramArrayOfFloat2, int paramInt3, float paramFloat1, float paramFloat2)
  {
    float f1 = 3.141593F / paramInt2;
    for (int i = 0; i < paramInt3; i++) {
      paramArrayOfFloat2[i] = Lookup.coslook(paramArrayOfFloat2[i]);
    }
    int j = paramInt3 / 2 * 2;
    i = 0;
    while (i < paramInt1)
    {
      int k = paramArrayOfInt[i];
      float f2 = 0.7071068F;
      float f3 = 0.7071068F;
      float f4 = Lookup.coslook(f1 * k);
      int m = 0;
      int n = paramInt3 >>> 1;
      for (int i1 = 0; i1 < j; i1 += 2)
      {
        f3 *= (paramArrayOfFloat2[i1] - f4);
        f2 *= (paramArrayOfFloat2[(i1 + 1)] - f4);
      }
      if ((paramInt3 & 0x1) != 0)
      {
        f3 *= (paramArrayOfFloat2[(paramInt3 - 1)] - f4);
        f3 *= f3;
        f2 *= f2 * (1.0F - f4 * f4);
      }
      else
      {
        f3 *= f3 * (1.0F + f4);
        f2 *= f2 * (1.0F - f4);
      }
      f3 = f2 + f3;
      int i2 = Float.floatToIntBits(f3);
      int i3 = 0x7FFFFFFF & i2;
      int i4 = 0;
      if ((i3 < 2139095040) && (i3 != 0))
      {
        if (i3 < 8388608)
        {
          f3 = (float)(f3 * 33554432.0D);
          i2 = Float.floatToIntBits(f3);
          i3 = 0x7FFFFFFF & i2;
          i4 = -25;
        }
        i4 += (i3 >>> 23) - 126;
        i2 = i2 & 0x807FFFFF | 0x3F000000;
        f3 = Float.intBitsToFloat(i2);
      }
      f3 = Lookup.fromdBlook(paramFloat1 * Lookup.invsqlook(f3) * Lookup.invsq2explook(i4 + paramInt3) - paramFloat2);
      do
      {
        paramArrayOfFloat1[(i++)] *= f3;
      } while ((i < paramInt1) && (paramArrayOfInt[i] == k));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.Lsp
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */