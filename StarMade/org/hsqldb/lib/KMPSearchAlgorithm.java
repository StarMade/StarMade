package org.hsqldb.lib;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class KMPSearchAlgorithm
{
  public static long search(InputStream paramInputStream, byte[] paramArrayOfByte, int[] paramArrayOfInt)
    throws IOException
  {
    if ((paramInputStream == null) || (paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
      return -1L;
    int i = paramArrayOfByte.length;
    long l = -1L;
    int j;
    if (i == 1)
    {
      k = paramArrayOfByte[0];
      while (-1 != (j = paramInputStream.read()))
      {
        l += 1L;
        if (j == k)
          return l;
      }
      return -1L;
    }
    int k = 0;
    if (paramArrayOfInt == null)
      paramArrayOfInt = computeTable(paramArrayOfByte);
    while (-1 != (j = paramInputStream.read()))
    {
      l += 1L;
      if (j == paramArrayOfByte[k])
      {
        k++;
      }
      else if (k > 0)
      {
        k = paramArrayOfInt[k];
        k++;
      }
      if (k == i)
        return l - (i - 1);
    }
    return -1L;
  }

  public static long search(Reader paramReader, char[] paramArrayOfChar, int[] paramArrayOfInt)
    throws IOException
  {
    if ((paramReader == null) || (paramArrayOfChar == null) || (paramArrayOfChar.length == 0))
      return -1L;
    int i = paramArrayOfChar.length;
    long l = -1L;
    int j;
    if (i == 1)
    {
      k = paramArrayOfChar[0];
      while (-1 != (j = paramReader.read()))
      {
        l += 1L;
        if (j == k)
          return l;
      }
      return -1L;
    }
    int k = 0;
    if (paramArrayOfInt == null)
      paramArrayOfInt = computeTable(paramArrayOfChar);
    while (-1 != (j = paramReader.read()))
    {
      l += 1L;
      if (j == paramArrayOfChar[k])
      {
        k++;
      }
      else if (k > 0)
      {
        k = paramArrayOfInt[k];
        k++;
      }
      if (k == i)
        return l - (i - 1);
    }
    return -1L;
  }

  public static int search(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int[] paramArrayOfInt, int paramInt)
  {
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte2 == null) || (paramArrayOfByte2.length == 0))
      return -1;
    int i = paramArrayOfByte1.length;
    int j = paramArrayOfByte2.length;
    int k = paramInt;
    if (j == 1)
    {
      m = paramArrayOfByte2[0];
      while (k < i)
      {
        if (paramArrayOfByte1[k] == m)
          return k;
        k++;
      }
      return -1;
    }
    int m = paramInt;
    int n = 0;
    if (paramArrayOfInt == null)
      paramArrayOfInt = computeTable(paramArrayOfByte2);
    while ((k < i) && (n < j))
    {
      if (paramArrayOfByte1[k] == paramArrayOfByte2[n])
      {
        n++;
      }
      else
      {
        int i1 = paramArrayOfInt[n];
        m += n - i1;
        if (n > 0)
          n = i1;
        n++;
      }
      k = m + n;
    }
    if (n == j)
      return m;
    return -1;
  }

  public static int search(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int[] paramArrayOfInt, int paramInt)
  {
    if ((paramArrayOfChar1 == null) || (paramArrayOfChar2 == null) || (paramArrayOfChar2.length == 0))
      return -1;
    int i = paramArrayOfChar1.length;
    int j = paramArrayOfChar2.length;
    int k = paramInt;
    if (j == 1)
    {
      m = paramArrayOfChar2[0];
      while (k < i)
      {
        if (paramArrayOfChar1[k] == m)
          return k;
        k++;
      }
      return -1;
    }
    int m = paramInt;
    int n = 0;
    if (paramArrayOfInt == null)
      paramArrayOfInt = computeTable(paramArrayOfChar2);
    while ((k < i) && (n < j))
    {
      if (paramArrayOfChar1[k] == paramArrayOfChar2[n])
      {
        n++;
      }
      else
      {
        int i1 = paramArrayOfInt[n];
        m += n - i1;
        if (n > 0)
          n = i1;
        n++;
      }
      k = m + n;
    }
    if (n == j)
      return m;
    return -1;
  }

  public static int search(String paramString1, String paramString2, int[] paramArrayOfInt, int paramInt)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString2.length() == 0))
      return -1;
    int i = paramString2.length();
    if (i == 1)
      return paramString1.indexOf(paramString2, paramInt);
    int j = paramString1.length();
    int k = paramInt;
    int m = paramInt;
    int n = 0;
    if (paramArrayOfInt == null)
      paramArrayOfInt = computeTable(paramString2);
    while ((m < j) && (n < i))
    {
      if (paramString1.charAt(m) == paramString2.charAt(n))
      {
        n++;
      }
      else
      {
        int i1 = paramArrayOfInt[n];
        k += n - i1;
        if (n > 0)
          n = i1;
        n++;
      }
      m = k + n;
    }
    if (n == i)
      return k;
    return -1;
  }

  public static int[] computeTable(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Pattern must  not be null.");
    if (paramArrayOfByte.length < 2)
      throw new IllegalArgumentException("Pattern length must be > 1.");
    int[] arrayOfInt = new int[paramArrayOfByte.length];
    int i = 2;
    int j = 0;
    arrayOfInt[0] = -1;
    arrayOfInt[1] = 0;
    while (i < paramArrayOfByte.length)
      if (paramArrayOfByte[(i - 1)] == paramArrayOfByte[j])
      {
        arrayOfInt[i] = (j + 1);
        j++;
        i++;
      }
      else if (j > 0)
      {
        j = arrayOfInt[j];
      }
      else
      {
        arrayOfInt[i] = 0;
        i++;
        j = 0;
      }
    return arrayOfInt;
  }

  public static int[] computeTable(char[] paramArrayOfChar)
  {
    if (paramArrayOfChar == null)
      throw new IllegalArgumentException("Pattern must  not be null.");
    if (paramArrayOfChar.length < 2)
      throw new IllegalArgumentException("Pattern length must be > 1.");
    int[] arrayOfInt = new int[paramArrayOfChar.length];
    int i = 2;
    int j = 0;
    arrayOfInt[0] = -1;
    arrayOfInt[1] = 0;
    while (i < paramArrayOfChar.length)
      if (paramArrayOfChar[(i - 1)] == paramArrayOfChar[j])
      {
        arrayOfInt[i] = (j + 1);
        j++;
        i++;
      }
      else if (j > 0)
      {
        j = arrayOfInt[j];
      }
      else
      {
        arrayOfInt[i] = 0;
        i++;
        j = 0;
      }
    return arrayOfInt;
  }

  public static int[] computeTable(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Pattern must  not be null.");
    if (paramString.length() < 2)
      throw new IllegalArgumentException("Pattern length must be > 1.");
    int i = paramString.length();
    int[] arrayOfInt = new int[i];
    int j = 2;
    int k = 0;
    arrayOfInt[0] = -1;
    arrayOfInt[1] = 0;
    while (j < i)
      if (paramString.charAt(j - 1) == paramString.charAt(k))
      {
        arrayOfInt[j] = (k + 1);
        k++;
        j++;
      }
      else if (k > 0)
      {
        k = arrayOfInt[k];
      }
      else
      {
        arrayOfInt[j] = 0;
        j++;
        k = 0;
      }
    return arrayOfInt;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.KMPSearchAlgorithm
 * JD-Core Version:    0.6.2
 */