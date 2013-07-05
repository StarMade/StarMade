package org.hsqldb.lib;

public class ArrayCounter
{
  public static int[] countSegments(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int[] arrayOfInt = new int[paramInt2];
    long l = calcInterval(paramInt2, paramInt3, paramInt4);
    int i = 0;
    int j = 0;
    if (l <= 0L)
      return arrayOfInt;
    for (int k = 0; k < paramInt1; k++)
    {
      j = paramArrayOfInt[k];
      if ((j >= paramInt3) && (j < paramInt4))
      {
        i = (int)((j - paramInt3) / l);
        arrayOfInt[i] += 1;
      }
    }
    return arrayOfInt;
  }

  public static int rank(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    int i = 0;
    long l;
    for (int j = paramInt4; ; j = paramInt3 + l < paramInt4 ? (int)(paramInt3 + l) : paramInt4)
    {
      l = calcInterval(256, paramInt3, j);
      int[] arrayOfInt = countSegments(paramArrayOfInt, paramInt1, 256, paramInt3, j);
      for (int k = 0; (k < arrayOfInt.length) && (i + arrayOfInt[k] < paramInt2); k++)
      {
        i += arrayOfInt[k];
        paramInt3 = (int)(paramInt3 + l);
      }
      if (i + paramInt5 >= paramInt2)
        return paramInt3;
      if (l <= 1L)
        return paramInt3;
    }
  }

  static long calcInterval(int paramInt1, int paramInt2, int paramInt3)
  {
    long l = paramInt3 - paramInt2;
    if (l < 0L)
      return 0L;
    int i = l % paramInt1 == 0L ? 0 : 1;
    return l / paramInt1 + i;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.ArrayCounter
 * JD-Core Version:    0.6.2
 */