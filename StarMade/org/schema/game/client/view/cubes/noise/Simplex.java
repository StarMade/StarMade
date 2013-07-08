package org.schema.game.client.view.cubes.noise;

import class_390;
import java.io.PrintStream;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.Random;
import org.lwjgl.BufferUtils;

public class Simplex
{
  private static final float[][] jdField_field_1763_of_type_Array2dOfFloat = { { 1.0F, 1.0F, 0.0F }, { -1.0F, 1.0F, 0.0F }, { 1.0F, -1.0F, 0.0F }, { -1.0F, -1.0F, 0.0F }, { 1.0F, 0.0F, 1.0F }, { -1.0F, 0.0F, 1.0F }, { 1.0F, 0.0F, -1.0F }, { -1.0F, 0.0F, -1.0F }, { 0.0F, 1.0F, 1.0F }, { 0.0F, -1.0F, 1.0F }, { 0.0F, 1.0F, -1.0F }, { 0.0F, -1.0F, -1.0F } };
  private static final int[] jdField_field_1763_of_type_ArrayOfInt;
  private static final int[] field_1764 = new int[(Simplex.jdField_field_1763_of_type_ArrayOfInt = new int[] { 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180, 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180 }).length];
  private static final IntBuffer jdField_field_1763_of_type_JavaNioIntBuffer;
  private static int[] field_1765 = { 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0 };
  
  public static void main(String[] paramArrayOfString)
  {
    class_390.a(0.0F, 0.0F, 0.0F);
    System.err.println("PERM: " + jdField_field_1763_of_type_ArrayOfInt.length);
    long l = System.currentTimeMillis();
    int i;
    int j;
    for (paramArrayOfString = 0; paramArrayOfString < 500; paramArrayOfString++) {
      for (i = 0; i < 500; i++) {
        for (j = 0; j < 500; j++) {
          a(j, i, paramArrayOfString, jdField_field_1763_of_type_ArrayOfInt);
        }
      }
    }
    System.err.println("1TIME: " + (float)(System.currentTimeMillis() - l) / 1000.0F);
    l = System.currentTimeMillis();
    for (paramArrayOfString = 0; paramArrayOfString < 500; paramArrayOfString++) {
      for (i = 0; i < 500; i++) {
        for (j = 0; j < 500; j++) {
          class_390.a(j, i, paramArrayOfString);
        }
      }
    }
    System.err.println("2TIME: " + (float)(System.currentTimeMillis() - l) / 1000.0F);
  }
  
  public static float a(float paramFloat1, float paramFloat2, float paramFloat3, int[] paramArrayOfInt)
  {
    float f1 = (paramFloat1 + paramFloat2 + paramFloat3) * 0.3333333F;
    int j = (int)(paramFloat1 + f1);
    int k = (int)(paramFloat2 + f1);
    int m = (int)(paramFloat3 + f1);
    f1 = (j + k + m) * 0.1666667F;
    float f3 = j - f1;
    float f4 = k - f1;
    f1 = m - f1;
    paramFloat1 -= f3;
    paramFloat2 -= f4;
    paramFloat3 -= f1;
    int i2;
    if (paramFloat1 >= paramFloat2)
    {
      if (paramFloat2 >= paramFloat3) {
        i2 = 0;
      } else if (paramFloat1 >= paramFloat3) {
        i2 = 6;
      } else {
        i2 = 12;
      }
    }
    else if (paramFloat2 < paramFloat3) {
      i2 = 18;
    } else if (paramFloat1 < paramFloat3) {
      i2 = 24;
    } else {
      i2 = 30;
    }
    f1 = paramFloat1 - field_1765[i2] + 0.1666667F;
    f3 = paramFloat2 - field_1765[(i2 + 1)] + 0.1666667F;
    f4 = paramFloat3 - field_1765[(i2 + 2)] + 0.1666667F;
    float f5 = paramFloat1 - field_1765[(i2 + 3)] + 0.3333333F;
    float f6 = paramFloat2 - field_1765[(i2 + 4)] + 0.3333333F;
    float f7 = paramFloat3 - field_1765[(i2 + 5)] + 0.3333333F;
    float f8 = paramFloat1 + -0.5F;
    float f9 = paramFloat2 + -0.5F;
    float f10 = paramFloat3 + -0.5F;
    int n = j & 0xFF;
    k &= 255;
    m &= 255;
    float f11;
    if ((f11 = 0.6F - paramFloat1 * paramFloat1 - paramFloat2 * paramFloat2 - paramFloat3 * paramFloat3) < 0.0F)
    {
      paramFloat2 = 0.0F;
    }
    else
    {
      int i1 = field_1764[paramArrayOfInt[(n + paramArrayOfInt[(k + paramArrayOfInt[m])])]];
      float tmp340_339 = (f11 * f11);
      paramFloat2 = tmp340_339 * tmp340_339 * (paramFloat1 * jdField_field_1763_of_type_Array2dOfFloat[i1][0] + paramFloat2 * jdField_field_1763_of_type_Array2dOfFloat[i1][1] + paramFloat3 * jdField_field_1763_of_type_Array2dOfFloat[i1][2]);
    }
    if ((paramFloat1 = 0.6F - f1 * f1 - f3 * f3 - f4 * f4) < 0.0F)
    {
      paramFloat3 = 0.0F;
    }
    else
    {
      paramFloat3 = field_1764[paramArrayOfInt[(n + field_1765[i2] + paramArrayOfInt[(k + field_1765[(i2 + 1)] + paramArrayOfInt[(m + field_1765[(i2 + 2)])])])]];
      float tmp455_454 = (paramFloat1 * paramFloat1);
      paramFloat3 = tmp455_454 * tmp455_454 * (f1 * jdField_field_1763_of_type_Array2dOfFloat[paramFloat3][0] + f3 * jdField_field_1763_of_type_Array2dOfFloat[paramFloat3][1] + f4 * jdField_field_1763_of_type_Array2dOfFloat[paramFloat3][2]);
    }
    float f2;
    if ((paramFloat1 = 0.6F - f5 * f5 - f6 * f6 - f7 * f7) < 0.0F)
    {
      f1 = 0.0F;
    }
    else
    {
      int i = field_1764[paramArrayOfInt[(n + field_1765[(i2 + 3)] + paramArrayOfInt[(k + field_1765[(i2 + 4)] + paramArrayOfInt[(m + field_1765[(i2 + 5)])])])]];
      float tmp574_573 = (paramFloat1 * paramFloat1);
      f2 = tmp574_573 * tmp574_573 * (f5 * jdField_field_1763_of_type_Array2dOfFloat[i][0] + f6 * jdField_field_1763_of_type_Array2dOfFloat[i][1] + f7 * jdField_field_1763_of_type_Array2dOfFloat[i][2]);
    }
    if ((paramFloat1 = 0.6F - f8 * f8 - f9 * f9 - f10 * f10) < 0.0F)
    {
      paramFloat1 = 0.0F;
    }
    else
    {
      paramArrayOfInt = field_1764[paramArrayOfInt[(n + 1 + paramArrayOfInt[(k + 1 + paramArrayOfInt[(m + 1)])])]];
      float tmp674_673 = (paramFloat1 * paramFloat1);
      paramFloat1 = tmp674_673 * tmp674_673 * (f8 * jdField_field_1763_of_type_Array2dOfFloat[paramArrayOfInt][0] + f9 * jdField_field_1763_of_type_Array2dOfFloat[paramArrayOfInt][1] + f10 * jdField_field_1763_of_type_Array2dOfFloat[paramArrayOfInt][2]);
    }
    return 16.0F * (paramFloat2 + paramFloat3 + f2 + paramFloat1) + 1.0F;
  }
  
  public static int[] a1(Random paramRandom)
  {
    int[] arrayOfInt;
    Random localRandom1 = paramRandom;
    int i = (paramRandom = arrayOfInt = Arrays.copyOf(jdField_field_1763_of_type_ArrayOfInt, jdField_field_1763_of_type_ArrayOfInt.length)).length;
    for (int j = 0; j < i; j++)
    {
      int k = j + localRandom1.nextInt(i - j);
      int n = k;
      int m = j;
      Random localRandom2 = paramRandom;
      int i1 = paramRandom[m];
      localRandom2[m] = localRandom2[n];
      localRandom2[n] = i1;
    }
    return arrayOfInt;
  }
  
  public static float b(float paramFloat1, float paramFloat2, float paramFloat3, int[] paramArrayOfInt)
  {
    float f = 0.0F;
    for (int i = 1; i <= 4; i++)
    {
      int j = i * i;
      f += a(paramFloat1 * j, paramFloat2 * j, paramFloat3 * j, paramArrayOfInt);
    }
    return f;
  }
  
  static
  {
    for (int i = 0; i < field_1764.length; i++) {
      field_1764[i] = (i % 12);
    }
    jdField_field_1763_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(jdField_field_1763_of_type_ArrayOfInt.length);
    for (i = 0; i < jdField_field_1763_of_type_ArrayOfInt.length; i++) {
      jdField_field_1763_of_type_JavaNioIntBuffer.put(i % 12);
    }
    { { 1, 0, 0, 1, 1, 0 }, { 1, 0, 0, 1, 0, 1 }, { 0, 0, 1, 1, 0, 1 }, { 0, 0, 1, 0, 1, 1 }, { 0, 1, 0, 0, 1, 1 } }[5] = { 0, 1, 0, 1, 1, 0 };
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.client.view.cubes.noise.Simplex
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */