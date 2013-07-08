package org.hsqldb.lib;

import java.io.IOException;
import java.io.InputStream;
import java.io.UTFDataFormatException;
import org.hsqldb.store.BitMap;

public class StringConverter
{
  private static final byte[] HEXBYTES = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  
  private static int getNibble(int paramInt)
  {
    if ((paramInt >= 48) && (paramInt <= 57)) {
      return paramInt - 48;
    }
    if ((paramInt >= 97) && (paramInt <= 102)) {
      return 10 + paramInt - 97;
    }
    if ((paramInt >= 65) && (paramInt <= 70)) {
      return 10 + paramInt - 65;
    }
    return -1;
  }
  
  public static byte[] hexStringToByteArray(String paramString)
    throws IOException
  {
    int i = paramString.length();
    byte[] arrayOfByte = new byte[i / 2 + i % 2];
    int k = 0;
    int m = 1;
    int n = 0;
    for (int i1 = 0; i1 < i; i1++)
    {
      int i2 = paramString.charAt(i1);
      if (i2 != 32)
      {
        int j = getNibble(i2);
        if (j == -1) {
          throw new IOException("hexadecimal string contains non hex character");
        }
        if (m != 0)
        {
          k = (j & 0xF) << 4;
          m = 0;
        }
        else
        {
          k += (j & 0xF);
          m = 1;
          arrayOfByte[(n++)] = ((byte)k);
        }
      }
    }
    if (m == 0) {
      throw new IOException("hexadecimal string with odd number of characters");
    }
    if (n < arrayOfByte.length) {
      arrayOfByte = (byte[])ArrayUtil.resizeArray(arrayOfByte, n);
    }
    return arrayOfByte;
  }
  
  public static BitMap sqlBitStringToBitMap(String paramString)
    throws IOException
  {
    int i = paramString.length();
    int k = 0;
    BitMap localBitMap = new BitMap(i);
    for (int m = 0; m < i; m++)
    {
      int n = paramString.charAt(m);
      if (n != 32)
      {
        int j = getNibble(n);
        if ((j != 0) && (j != 1)) {
          throw new IOException("hexadecimal string contains non hex character");
        }
        if (j == 1) {
          localBitMap.set(k);
        }
        k++;
      }
    }
    localBitMap.setSize(k);
    return localBitMap;
  }
  
  public static String byteArrayToHexString(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    char[] arrayOfChar = new char[i * 2];
    int j = 0;
    int k = 0;
    while (j < i)
    {
      int m = paramArrayOfByte[j] & 0xFF;
      arrayOfChar[(k++)] = ((char)HEXBYTES[(m >> 4 & 0xF)]);
      arrayOfChar[(k++)] = ((char)HEXBYTES[(m & 0xF)]);
      j++;
    }
    return new String(arrayOfChar);
  }
  
  public static String byteArrayToSQLHexString(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    char[] arrayOfChar = new char[i * 2 + 3];
    arrayOfChar[0] = 'X';
    arrayOfChar[1] = '\'';
    int j = 2;
    for (int k = 0; k < i; k++)
    {
      int m = paramArrayOfByte[k] & 0xFF;
      arrayOfChar[(j++)] = ((char)HEXBYTES[(m >> 4 & 0xF)]);
      arrayOfChar[(j++)] = ((char)HEXBYTES[(m & 0xF)]);
    }
    arrayOfChar[j] = '\'';
    return new String(arrayOfChar);
  }
  
  public static String byteArrayToBitString(byte[] paramArrayOfByte, int paramInt)
  {
    char[] arrayOfChar = new char[paramInt];
    for (int i = 0; i < paramInt; i++)
    {
      byte b = paramArrayOfByte[(i / 8)];
      arrayOfChar[i] = (BitMap.isSet(b, i % 8) ? 49 : '0');
    }
    return new String(arrayOfChar);
  }
  
  public static String byteArrayToSQLBitString(byte[] paramArrayOfByte, int paramInt)
  {
    char[] arrayOfChar = new char[paramInt + 3];
    arrayOfChar[0] = 'B';
    arrayOfChar[1] = '\'';
    int i = 2;
    for (int j = 0; j < paramInt; j++)
    {
      byte b = paramArrayOfByte[(j / 8)];
      arrayOfChar[(i++)] = (BitMap.isSet(b, j % 8) ? 49 : '0');
    }
    arrayOfChar[i] = '\'';
    return new String(arrayOfChar);
  }
  
  public static void writeHexBytes(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2)
  {
    int i = paramArrayOfByte2.length;
    for (int j = 0; j < i; j++)
    {
      int k = paramArrayOfByte2[j] & 0xFF;
      paramArrayOfByte1[(paramInt++)] = HEXBYTES[(k >> 4 & 0xF)];
      paramArrayOfByte1[(paramInt++)] = HEXBYTES[(k & 0xF)];
    }
  }
  
  public static String byteArrayToString(byte[] paramArrayOfByte, String paramString)
  {
    try
    {
      return paramString == null ? new String(paramArrayOfByte) : new String(paramArrayOfByte, paramString);
    }
    catch (Exception localException) {}
    return null;
  }
  
  public static void stringToUnicodeBytes(HsqlByteArrayOutputStream paramHsqlByteArrayOutputStream, String paramString, boolean paramBoolean)
  {
    if (paramString == null) {
      return;
    }
    int i = paramString.length();
    int j = 0;
    if (i == 0) {
      return;
    }
    char[] arrayOfChar = paramString.toCharArray();
    paramHsqlByteArrayOutputStream.ensureRoom(i * 2 + 5);
    for (int k = 0; k < i; k++)
    {
      int m = arrayOfChar[k];
      if (m == 92)
      {
        if ((k < i - 1) && (arrayOfChar[(k + 1)] == 'u'))
        {
          paramHsqlByteArrayOutputStream.writeNoCheck(m);
          paramHsqlByteArrayOutputStream.writeNoCheck(117);
          paramHsqlByteArrayOutputStream.writeNoCheck(48);
          paramHsqlByteArrayOutputStream.writeNoCheck(48);
          paramHsqlByteArrayOutputStream.writeNoCheck(53);
          paramHsqlByteArrayOutputStream.writeNoCheck(99);
          j += 5;
        }
        else
        {
          paramHsqlByteArrayOutputStream.write(m);
        }
      }
      else if ((m >= 32) && (m <= 127))
      {
        paramHsqlByteArrayOutputStream.writeNoCheck(m);
        if ((m == 39) && (paramBoolean))
        {
          paramHsqlByteArrayOutputStream.writeNoCheck(m);
          j++;
        }
      }
      else
      {
        paramHsqlByteArrayOutputStream.writeNoCheck(92);
        paramHsqlByteArrayOutputStream.writeNoCheck(117);
        paramHsqlByteArrayOutputStream.writeNoCheck(HEXBYTES[(m >> 12 & 0xF)]);
        paramHsqlByteArrayOutputStream.writeNoCheck(HEXBYTES[(m >> 8 & 0xF)]);
        paramHsqlByteArrayOutputStream.writeNoCheck(HEXBYTES[(m >> 4 & 0xF)]);
        paramHsqlByteArrayOutputStream.writeNoCheck(HEXBYTES[(m & 0xF)]);
        j += 5;
      }
      if (j > i)
      {
        paramHsqlByteArrayOutputStream.ensureRoom(i + j + 5);
        j = 0;
      }
    }
  }
  
  public static String unicodeStringToString(String paramString)
  {
    if ((paramString == null) || (paramString.indexOf("\\u") == -1)) {
      return paramString;
    }
    int i = paramString.length();
    char[] arrayOfChar = new char[i];
    int j = 0;
    for (int k = 0; k < i; k++)
    {
      int m = paramString.charAt(k);
      if ((m == 92) && (k < i - 5))
      {
        int n = paramString.charAt(k + 1);
        if (n == 117)
        {
          k++;
          int i1 = getNibble(paramString.charAt(++k)) << 12;
          i1 += (getNibble(paramString.charAt(++k)) << 8);
          i1 += (getNibble(paramString.charAt(++k)) << 4);
          i1 += getNibble(paramString.charAt(++k));
          arrayOfChar[(j++)] = ((char)i1);
        }
        else
        {
          arrayOfChar[(j++)] = m;
        }
      }
      else
      {
        arrayOfChar[(j++)] = m;
      }
    }
    return new String(arrayOfChar, 0, j);
  }
  
  public static String readUTF(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    char[] arrayOfChar = new char[paramInt2];
    return readUTF(paramArrayOfByte, paramInt1, paramInt2, arrayOfChar);
  }
  
  public static String readUTF(byte[] paramArrayOfByte, int paramInt1, int paramInt2, char[] paramArrayOfChar)
    throws IOException
  {
    int i = 0;
    int n = 0;
    while (n < paramInt2)
    {
      int j = paramArrayOfByte[(paramInt1 + n)];
      if (i == paramArrayOfChar.length) {
        paramArrayOfChar = (char[])ArrayUtil.resizeArray(paramArrayOfChar, paramInt2);
      }
      if (j > 0)
      {
        n++;
        paramArrayOfChar[(i++)] = ((char)j);
      }
      else
      {
        j &= 255;
        int k;
        switch (j >> 4)
        {
        case 12: 
        case 13: 
          n += 2;
          if (n > paramInt2) {
            throw new UTFDataFormatException();
          }
          k = paramArrayOfByte[(paramInt1 + n - 1)];
          if ((k & 0xC0) != 128) {
            throw new UTFDataFormatException();
          }
          paramArrayOfChar[(i++)] = ((char)((j & 0x1F) << 6 | k & 0x3F));
          break;
        case 14: 
          n += 3;
          if (n > paramInt2) {
            throw new UTFDataFormatException();
          }
          k = paramArrayOfByte[(paramInt1 + n - 2)];
          int m = paramArrayOfByte[(paramInt1 + n - 1)];
          if (((k & 0xC0) != 128) || ((m & 0xC0) != 128)) {
            throw new UTFDataFormatException();
          }
          paramArrayOfChar[(i++)] = ((char)((j & 0xF) << 12 | (k & 0x3F) << 6 | (m & 0x3F) << 0));
          break;
        default: 
          throw new UTFDataFormatException();
        }
      }
    }
    return new String(paramArrayOfChar, 0, i);
  }
  
  public static int stringToUTFBytes(String paramString, HsqlByteArrayOutputStream paramHsqlByteArrayOutputStream)
  {
    int i = paramString.length();
    int k = 0;
    if (paramHsqlByteArrayOutputStream.count + i + 8 > paramHsqlByteArrayOutputStream.buffer.length) {
      paramHsqlByteArrayOutputStream.ensureRoom(i + 8);
    }
    char[] arrayOfChar = paramString.toCharArray();
    for (int m = 0; m < i; m++)
    {
      int j = arrayOfChar[m];
      if ((j >= 1) && (j <= 127))
      {
        paramHsqlByteArrayOutputStream.buffer[(paramHsqlByteArrayOutputStream.count++)] = ((byte)j);
        k++;
      }
      else if (j > 2047)
      {
        paramHsqlByteArrayOutputStream.buffer[(paramHsqlByteArrayOutputStream.count++)] = ((byte)(0xE0 | j >> 12 & 0xF));
        paramHsqlByteArrayOutputStream.buffer[(paramHsqlByteArrayOutputStream.count++)] = ((byte)(0x80 | j >> 6 & 0x3F));
        paramHsqlByteArrayOutputStream.buffer[(paramHsqlByteArrayOutputStream.count++)] = ((byte)(0x80 | j >> 0 & 0x3F));
        k += 3;
      }
      else
      {
        paramHsqlByteArrayOutputStream.buffer[(paramHsqlByteArrayOutputStream.count++)] = ((byte)(0xC0 | j >> 6 & 0x1F));
        paramHsqlByteArrayOutputStream.buffer[(paramHsqlByteArrayOutputStream.count++)] = ((byte)(0x80 | j >> 0 & 0x3F));
        k += 2;
      }
      if (paramHsqlByteArrayOutputStream.count + 8 > paramHsqlByteArrayOutputStream.buffer.length) {
        paramHsqlByteArrayOutputStream.ensureRoom(i - m + 8);
      }
    }
    return k;
  }
  
  public static int getUTFSize(String paramString)
  {
    int i = paramString == null ? 0 : paramString.length();
    int j = 0;
    for (int k = 0; k < i; k++)
    {
      int m = paramString.charAt(k);
      if ((m >= 1) && (m <= 127)) {
        j++;
      } else if (m > 2047) {
        j += 3;
      } else {
        j += 2;
      }
    }
    return j;
  }
  
  public static String inputStreamToString(InputStream paramInputStream, String paramString)
    throws IOException
  {
    HsqlByteArrayOutputStream localHsqlByteArrayOutputStream = new HsqlByteArrayOutputStream(1024);
    for (;;)
    {
      int i = paramInputStream.read();
      if (i == -1) {
        break;
      }
      localHsqlByteArrayOutputStream.write(i);
    }
    return new String(localHsqlByteArrayOutputStream.getBuffer(), 0, localHsqlByteArrayOutputStream.size(), paramString);
  }
  
  public static String toQuotedString(String paramString, char paramChar, boolean paramBoolean)
  {
    if (paramString == null) {
      return null;
    }
    int i = paramBoolean ? count(paramString, paramChar) : 0;
    int j = paramString.length();
    char[] arrayOfChar = new char[2 + i + j];
    int k = 0;
    int m = 0;
    arrayOfChar[(m++)] = paramChar;
    while (k < j)
    {
      char c = paramString.charAt(k);
      arrayOfChar[(m++)] = c;
      if ((paramBoolean) && (c == paramChar)) {
        arrayOfChar[(m++)] = c;
      }
      k++;
    }
    arrayOfChar[m] = paramChar;
    return new String(arrayOfChar);
  }
  
  static int count(String paramString, char paramChar)
  {
    int i = 0;
    int j = 0;
    if (paramString != null) {
      while ((i = paramString.indexOf(paramChar, i)) > -1)
      {
        j++;
        i++;
      }
    }
    return j;
  }
  
  public static void stringToHtmlBytes(HsqlByteArrayOutputStream paramHsqlByteArrayOutputStream, String paramString)
  {
    if (paramString == null) {
      return;
    }
    int i = paramString.length();
    if (i == 0) {
      return;
    }
    char[] arrayOfChar = paramString.toCharArray();
    paramHsqlByteArrayOutputStream.ensureRoom(i);
    for (int j = 0; j < i; j++)
    {
      int k = arrayOfChar[j];
      if ((k > 127) || (k == 34) || (k == 38) || (k == 60) || (k == 62))
      {
        int m = Character.codePointAt(arrayOfChar, j);
        if (Character.charCount(m) == 2) {
          j++;
        }
        paramHsqlByteArrayOutputStream.ensureRoom(16);
        paramHsqlByteArrayOutputStream.writeNoCheck(38);
        paramHsqlByteArrayOutputStream.writeNoCheck(35);
        paramHsqlByteArrayOutputStream.writeBytes(String.valueOf(m));
        paramHsqlByteArrayOutputStream.writeNoCheck(59);
      }
      else if (k < 32)
      {
        paramHsqlByteArrayOutputStream.writeNoCheck(32);
      }
      else
      {
        paramHsqlByteArrayOutputStream.writeNoCheck(k);
      }
    }
  }
  
  public static String toStringUUID(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = new char[36];
    if (paramArrayOfByte == null) {
      return null;
    }
    if (paramArrayOfByte.length != 16) {
      throw new NumberFormatException();
    }
    int j = 0;
    int k = 0;
    while (j < paramArrayOfByte.length)
    {
      int i = (paramArrayOfByte[j] & 0xF0) >> 4;
      arrayOfChar[(k++)] = ((char)HEXBYTES[i]);
      i = paramArrayOfByte[j] & 0xF;
      arrayOfChar[(k++)] = ((char)HEXBYTES[i]);
      j++;
      if ((j >= 4) && (j <= 10) && (j % 2 == 0)) {
        arrayOfChar[(k++)] = '-';
      }
    }
    return new String(arrayOfChar);
  }
  
  public static byte[] toBinaryUUID(String paramString)
  {
    byte[] arrayOfByte = new byte[16];
    if (paramString == null) {
      return null;
    }
    if (paramString.length() != 36) {
      throw new NumberFormatException();
    }
    int i = 0;
    int j = 0;
    while (i < arrayOfByte.length)
    {
      int k = paramString.charAt(j++);
      int m = getNibble(k);
      k = paramString.charAt(j++);
      arrayOfByte[i] = ((byte)((m << 4) + getNibble(k)));
      i++;
      if ((i >= 4) && (i <= 10) && (i % 2 == 0))
      {
        k = paramString.charAt(j++);
        if (k == 45) {}
      }
    }
    return arrayOfByte;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.StringConverter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */