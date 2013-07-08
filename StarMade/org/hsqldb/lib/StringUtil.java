package org.hsqldb.lib;

import java.lang.reflect.Array;

public class StringUtil
{
  public static String toZeroPaddedString(long paramLong, int paramInt1, int paramInt2)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramLong < 0L) {
      paramLong = -paramLong;
    }
    String str = Long.toString(paramLong);
    if (str.length() > paramInt1) {
      str = str.substring(paramInt1);
    }
    for (int i = str.length(); i < paramInt1; i++) {
      localStringBuffer.append('0');
    }
    localStringBuffer.append(str);
    if (paramInt2 < paramInt1) {
      localStringBuffer.setLength(paramInt2);
    }
    return localStringBuffer.toString();
  }
  
  public static String toPaddedString(String paramString, int paramInt, char paramChar, boolean paramBoolean)
  {
    int i = paramString.length();
    if (i >= paramInt) {
      return paramString;
    }
    StringBuffer localStringBuffer = new StringBuffer(paramInt);
    if (paramBoolean) {
      localStringBuffer.append(paramString);
    }
    for (int j = i; j < paramInt; j++) {
      localStringBuffer.append(paramChar);
    }
    if (!paramBoolean) {
      localStringBuffer.append(paramString);
    }
    return localStringBuffer.toString();
  }
  
  public static String toPaddedString(String paramString1, int paramInt, String paramString2, boolean paramBoolean)
  {
    int i = paramString1.length();
    if (i == paramInt) {
      return paramString1;
    }
    if (i > paramInt)
    {
      if (paramBoolean) {
        return paramString1.substring(0, paramInt);
      }
      return paramString1.substring(i - paramInt, i);
    }
    StringBuffer localStringBuffer = new StringBuffer(paramInt);
    int j = paramString1.length();
    int k = (paramInt - j) % paramString2.length();
    if (paramBoolean)
    {
      localStringBuffer.append(paramString1);
      localStringBuffer.append(paramString2.substring(paramString2.length() - k, paramString2.length()));
    }
    while (j + paramString2.length() <= paramInt)
    {
      localStringBuffer.append(paramString2);
      j += paramString2.length();
    }
    if (!paramBoolean)
    {
      localStringBuffer.append(paramString2.substring(0, k));
      localStringBuffer.append(paramString1);
    }
    return localStringBuffer.toString();
  }
  
  public static String toLowerSubset(String paramString, char paramChar)
  {
    int i = paramString.length();
    StringBuffer localStringBuffer = new StringBuffer(i);
    for (int j = 0; j < i; j++)
    {
      char c = paramString.charAt(j);
      if (!Character.isLetterOrDigit(c)) {
        localStringBuffer.append(paramChar);
      } else if ((j == 0) && (Character.isDigit(c))) {
        localStringBuffer.append(paramChar);
      } else {
        localStringBuffer.append(Character.toLowerCase(c));
      }
    }
    return localStringBuffer.toString();
  }
  
  public static String arrayToString(Object paramObject)
  {
    int i = Array.getLength(paramObject);
    int j = i - 1;
    StringBuffer localStringBuffer = new StringBuffer(2 * (i + 1));
    localStringBuffer.append('{');
    for (int k = 0; k < i; k++)
    {
      localStringBuffer.append(Array.get(paramObject, k));
      if (k != j) {
        localStringBuffer.append(',');
      }
    }
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
  
  public static String getList(String[] paramArrayOfString, String paramString1, String paramString2)
  {
    int i = paramArrayOfString.length;
    StringBuffer localStringBuffer = new StringBuffer(i * 16);
    for (int j = 0; j < i; j++)
    {
      localStringBuffer.append(paramString2);
      localStringBuffer.append(paramArrayOfString[j]);
      localStringBuffer.append(paramString2);
      if (j + 1 < i) {
        localStringBuffer.append(paramString1);
      }
    }
    return localStringBuffer.toString();
  }
  
  public static String getList(int[] paramArrayOfInt, String paramString1, String paramString2)
  {
    int i = paramArrayOfInt.length;
    StringBuffer localStringBuffer = new StringBuffer(i * 8);
    for (int j = 0; j < i; j++)
    {
      localStringBuffer.append(paramString2);
      localStringBuffer.append(paramArrayOfInt[j]);
      localStringBuffer.append(paramString2);
      if (j + 1 < i) {
        localStringBuffer.append(paramString1);
      }
    }
    return localStringBuffer.toString();
  }
  
  public static String getList(long[] paramArrayOfLong, String paramString1, String paramString2)
  {
    int i = paramArrayOfLong.length;
    StringBuffer localStringBuffer = new StringBuffer(i * 8);
    for (int j = 0; j < i; j++)
    {
      localStringBuffer.append(paramString2);
      localStringBuffer.append(paramArrayOfLong[j]);
      localStringBuffer.append(paramString2);
      if (j + 1 < i) {
        localStringBuffer.append(paramString1);
      }
    }
    return localStringBuffer.toString();
  }
  
  public static String getList(String[][] paramArrayOfString, String paramString1, String paramString2)
  {
    int i = paramArrayOfString.length;
    StringBuffer localStringBuffer = new StringBuffer(i * 16);
    for (int j = 0; j < i; j++)
    {
      localStringBuffer.append(paramString2);
      localStringBuffer.append(paramArrayOfString[j][0]);
      localStringBuffer.append(paramString2);
      if (j + 1 < i) {
        localStringBuffer.append(paramString1);
      }
    }
    return localStringBuffer.toString();
  }
  
  public static boolean isEmpty(String paramString)
  {
    int i = paramString == null ? 0 : paramString.length();
    while (i > 0) {
      if (paramString.charAt(--i) > ' ') {
        return false;
      }
    }
    return true;
  }
  
  public static int rightTrimSize(String paramString)
  {
    int i = paramString.length();
    while (i > 0)
    {
      i--;
      if (paramString.charAt(i) != ' ') {
        return i + 1;
      }
    }
    return 0;
  }
  
  public static int skipSpaces(String paramString, int paramInt)
  {
    int i = paramString.length();
    for (int j = paramInt; (j < i) && (paramString.charAt(j) == ' '); j++) {}
    return j;
  }
  
  public static String[] split(String paramString1, String paramString2)
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    int i = 0;
    int j = 1;
    while (j != 0)
    {
      int k = paramString1.indexOf(paramString2, i);
      if (k == -1)
      {
        k = paramString1.length();
        j = 0;
      }
      localHsqlArrayList.add(paramString1.substring(i, k));
      i = k + paramString2.length();
    }
    return (String[])localHsqlArrayList.toArray(new String[localHsqlArrayList.size()]);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.StringUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */