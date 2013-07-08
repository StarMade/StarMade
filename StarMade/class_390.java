import org.schema.common.FastMath;

public final class class_390
{
  private static int[] field_783 = new int[512];
  private static int[] field_784 = { 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180 };
  
  public static float a(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    int i = FastMath.a9(paramFloat1);
    int j = FastMath.a9(paramFloat2);
    int k = FastMath.a9(paramFloat3);
    int m = i & 0xFF;
    int n = j & 0xFF;
    int i1 = k & 0xFF;
    paramFloat1 -= i;
    paramFloat2 -= j;
    paramFloat3 -= k;
    float f1 = a1(paramFloat1);
    float f2 = a1(paramFloat2);
    float f3 = a1(paramFloat3);
    int i2 = field_783[m] + n;
    int i3 = field_783[i2] + i1;
    i2 = field_783[(i2 + 1)] + i1;
    m = field_783[(m + 1)] + n;
    n = field_783[m] + i1;
    m = field_783[(m + 1)] + i1;
    float f5 = a2(field_783[n], paramFloat1 - 1.0F, paramFloat2, paramFloat3);
    f5 = a2(field_783[m], paramFloat1 - 1.0F, paramFloat2 - 1.0F, paramFloat3);
    f5 = (f4 = a2(field_783[i2], paramFloat1, paramFloat2 - 1.0F, paramFloat3)) + f1 * (f5 - f4);
    f5 = a2(field_783[(n + 1)], paramFloat1 - 1.0F, paramFloat2, paramFloat3 - 1.0F);
    f5 = a2(field_783[(m + 1)], paramFloat1 - 1.0F, paramFloat2 - 1.0F, paramFloat3 - 1.0F);
    f5 = (f4 = a2(field_783[(i2 + 1)], paramFloat1, paramFloat2 - 1.0F, paramFloat3 - 1.0F)) + f1 * (f5 - f4);
    f5 = (f4 = (f4 = a2(field_783[(i3 + 1)], paramFloat1, paramFloat2, paramFloat3 - 1.0F)) + f1 * (f5 - f4)) + f2 * (f5 - f4);
    float f4 = (f4 = (f4 = a2(field_783[i3], paramFloat1, paramFloat2, paramFloat3)) + f1 * (f5 - f4)) + f2 * (f5 - f4);
    f3 = f3;
    return f4 + f3 * (f5 - f4);
  }
  
  private static float a1(float paramFloat)
  {
    return paramFloat * paramFloat * paramFloat * (paramFloat * (paramFloat * 6.0F - 15.0F) + 10.0F);
  }
  
  private static float a2(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    float f = (paramInt &= 15) < 8 ? paramFloat1 : paramFloat2;
    paramFloat1 = (paramInt == 12) || (paramInt == 14) ? paramFloat1 : paramInt < 4 ? paramFloat2 : paramFloat3;
    return ((paramInt & 0x1) == 0 ? f : -f) + ((paramInt & 0x2) == 0 ? paramFloat1 : -paramFloat1);
  }
  
  static
  {
    for (int i = 0; i < 256; i++)
    {
      int tmp1822_1821 = field_784[i];
      field_783[i] = tmp1822_1821;
      field_783[(i + 256)] = tmp1822_1821;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_390
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */