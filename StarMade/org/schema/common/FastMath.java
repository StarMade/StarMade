package org.schema.common;

import java.io.PrintStream;
import java.util.Random;

public final class FastMath
{
  private static float[] jdField_field_1862_of_type_ArrayOfFloat = new float[65536];
  private static float jdField_field_1862_of_type_Float;
  public static final Random field_1862;
  private static final float[] jdField_field_1863_of_type_ArrayOfFloat;
  private static final float[] field_1864;
  private static final int jdField_field_1862_of_type_Int;
  private static final float jdField_field_1863_of_type_Float;
  private static final float[] field_1865;
  
  public static float a(float paramFloat)
  {
    if (paramFloat < 0.0F) {
      return -paramFloat;
    }
    return paramFloat;
  }
  
  public static short a1(short paramShort)
  {
    if (paramShort < 0) {
      return (short)-paramShort;
    }
    return paramShort;
  }
  
  public static float b(float paramFloat)
  {
    if (-1.0F < paramFloat)
    {
      if (paramFloat < 1.0F) {
        return (float)Math.acos(paramFloat);
      }
      return 0.0F;
    }
    return 3.141593F;
  }
  
  public static double a2(double paramDouble)
  {
    return (paramDouble * -0.6981317007977321D * paramDouble - 0.8726646259971648D) * paramDouble + 1.570796326794897D;
  }
  
  public static final float a3(float paramFloat1, float paramFloat2)
  {
    float f2;
    float f1;
    if (paramFloat2 < 0.0F)
    {
      if (paramFloat1 < 0.0F)
      {
        paramFloat2 = -paramFloat2;
        paramFloat1 = -paramFloat1;
        f2 = 1.0F;
      }
      else
      {
        paramFloat2 = -paramFloat2;
        f2 = -1.0F;
      }
      f1 = -3.141593F;
    }
    else
    {
      if (paramFloat1 < 0.0F)
      {
        paramFloat1 = -paramFloat1;
        f2 = -1.0F;
      }
      else
      {
        f2 = 1.0F;
      }
      f1 = 0.0F;
    }
    float f3 = jdField_field_1863_of_type_Float / (paramFloat2 < paramFloat1 ? paramFloat1 : paramFloat2);
    paramFloat2 = (int)(paramFloat2 * f3);
    paramFloat1 = (int)(paramFloat1 * f3);
    return (field_1865[(paramFloat1 * jdField_field_1862_of_type_Int + paramFloat2)] + f1) * f2;
  }
  
  public static float c(float paramFloat)
  {
    return (float)Math.ceil(paramFloat);
  }
  
  public static byte a4(byte paramByte)
  {
    if (paramByte < 0) {
      return 0;
    }
    if (paramByte > 7) {
      return 7;
    }
    return paramByte;
  }
  
  public static float a5(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (paramFloat1 < paramFloat2) {
      return paramFloat2;
    }
    if (paramFloat1 > paramFloat3) {
      return paramFloat3;
    }
    return paramFloat1;
  }
  
  public static float d(float paramFloat)
  {
    return h(paramFloat + 1.570796F);
  }
  
  public static final float e(float paramFloat)
  {
    return jdField_field_1862_of_type_ArrayOfFloat[((int)(paramFloat * 10430.38F + 16384.0F) & 0xFFFF)];
  }
  
  public static int a6(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0) {
      return 0;
    }
    if (paramInt1 < 0) {
      return Math.abs(paramInt1 + 1) % paramInt2;
    }
    return paramInt1 % paramInt2;
  }
  
  public static int b1(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0) {
      return 0;
    }
    if (paramInt1 < 0) {
      return paramInt2 - 1 - Math.abs(paramInt1 + 1) % paramInt2;
    }
    return paramInt1 % paramInt2;
  }
  
  public static float f(float paramFloat)
  {
    return (float)Math.exp(paramFloat);
  }
  
  public static boolean a7(int paramInt)
  {
    return (paramInt > 0) && ((paramInt & paramInt - 1) == 0);
  }
  
  public static float g(float paramFloat)
  {
    return (float)Math.log(paramFloat) / jdField_field_1862_of_type_Float;
  }
  
  public static void main(String[] paramArrayOfString)
  {
    for (paramArrayOfString = 32; paramArrayOfString >= -32; paramArrayOfString--) {
      System.err.println(paramArrayOfString + ": " + a6(paramArrayOfString, 2));
    }
    System.err.println("-------------------------");
    for (paramArrayOfString = 32; paramArrayOfString >= -32; paramArrayOfString--) {
      System.err.println(paramArrayOfString + ": " + a6(paramArrayOfString, 16));
    }
  }
  
  public static float a8()
  {
    return jdField_field_1862_of_type_JavaUtilRandom.nextFloat();
  }
  
  public static float b2(float paramFloat1, float paramFloat2)
  {
    return (float)Math.pow(paramFloat1, paramFloat2);
  }
  
  public static double b3(double paramDouble)
  {
    return (1.0D / (1.0D + Math.pow(1.000696D, -paramDouble)) - 0.5D) * 2.0D * 1000000.0D;
  }
  
  public static float h(float paramFloat)
  {
    if (Math.abs(paramFloat %= 6.283186F) > 3.141593F) {
      paramFloat -= 6.283186F;
    }
    if (Math.abs(paramFloat) > 1.570796F) {
      paramFloat = 3.141593F - paramFloat;
    }
    if (Math.abs(paramFloat = paramFloat) <= 0.7853981633974483D) {
      return (float)Math.sin(paramFloat);
    }
    return (float)Math.cos(1.570796326794897D - paramFloat);
  }
  
  public static final float i(float paramFloat)
  {
    return jdField_field_1863_of_type_ArrayOfFloat[((int)(paramFloat * 651.89862F) & 0xFFF)];
  }
  
  public static final float j(float paramFloat)
  {
    return field_1864[((int)(paramFloat * 651.89862F) & 0xFFF)];
  }
  
  public static final float k(float paramFloat)
  {
    return jdField_field_1862_of_type_ArrayOfFloat[((int)(paramFloat * 10430.38F) & 0xFFFF)];
  }
  
  public static float l(float paramFloat)
  {
    return (float)Math.sqrt(paramFloat);
  }
  
  public static float m(float paramFloat)
  {
    float f2 = 0.5F * paramFloat;
    int i = Float.floatToIntBits(paramFloat);
    float f1;
    return (f1 = Float.intBitsToFloat(1597463007 - (i >> 1))) * (1.5F - f2 * f1 * f1) * paramFloat;
  }
  
  public static int a9(float paramFloat)
  {
    return (int)(paramFloat + 16384.0D) - 16384;
  }
  
  public static int b4(float paramFloat)
  {
    return 16384 - (int)(16384.0D - paramFloat);
  }
  
  public static float n(float paramFloat)
  {
    return (float)Math.tan(paramFloat);
  }
  
  static
  {
    for (int i = 0; i < 65536; i++) {
      jdField_field_1862_of_type_ArrayOfFloat[i] = ((float)Math.sin(i * 3.141592653589793D * 2.0D / 65536.0D));
    }
    jdField_field_1862_of_type_Float = (float)Math.log(2.0D);
    jdField_field_1862_of_type_JavaUtilRandom = new Random(System.currentTimeMillis());
    jdField_field_1863_of_type_ArrayOfFloat = new float[4096];
    field_1864 = new float[4096];
    for (i = 0; i < 4096; i++)
    {
      jdField_field_1863_of_type_ArrayOfFloat[i] = ((float)Math.sin((i + 0.5F) / 4096.0F * 6.283186F));
      field_1864[i] = ((float)Math.cos((i + 0.5F) / 4096.0F * 6.283186F));
    }
    jdField_field_1863_of_type_Float = (FastMath.jdField_field_1862_of_type_Int = (int)Math.sqrt(16384.0D)) - 1;
    field_1865 = new float[16384];
    for (i = 0; i < jdField_field_1862_of_type_Int; i++) {
      for (int j = 0; j < jdField_field_1862_of_type_Int; j++)
      {
        float f1 = i / jdField_field_1862_of_type_Int;
        float f2 = j / jdField_field_1862_of_type_Int;
        field_1865[(j * jdField_field_1862_of_type_Int + i)] = ((float)Math.atan2(f2, f1));
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.common.FastMath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */