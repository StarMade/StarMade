package org.schema.common.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;

public class ByteUtil
{
  public static final byte[] field_2133;
  
  public static final int a(int paramInt)
  {
    if (paramInt >= 0) {
      return paramInt >> 4;
    }
    return -(-paramInt >> 4);
  }
  
  public static final int b(int paramInt)
  {
    return paramInt >> 4;
  }
  
  public static final int c(int paramInt)
  {
    return paramInt >> 3;
  }
  
  public static int a1(int paramInt1, int paramInt2, int paramInt3)
  {
    paramInt3 = (1 << paramInt3 - paramInt2) - 1;
    return paramInt1 >> paramInt2 & paramInt3;
  }
  
  public static float a2(byte paramByte1, byte paramByte2, short paramShort, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6)
  {
    paramByte1 = paramByte1 << 2;
    paramByte2 = paramByte2 << 5;
    paramShort = paramShort << 9;
    paramByte3 = paramByte3 << 17;
    paramByte4 = paramByte4 << 20;
    paramByte5 = paramByte5 << 21;
    paramByte6 = paramByte6 << 22;
    return paramByte1 + paramByte2 + paramShort + paramByte3 + paramByte4 + paramByte5 + paramByte6;
  }
  
  public static float a3(float paramFloat, byte paramByte1, byte paramByte2, byte paramByte3)
  {
    paramByte1 = paramByte1 << 12;
    paramByte2 = paramByte2 << 16;
    paramByte3 = paramByte3 << 20;
    return paramFloat + paramByte1 + paramByte2 + paramByte3;
  }
  
  public static int a4(byte[] paramArrayOfByte, int paramInt)
  {
    int i = 0;
    for (int j = 0; j < 3; j++) {
      i = i << 8 ^ paramArrayOfByte[(paramInt + j)] & 0xFF;
    }
    return i;
  }
  
  public static byte[] a5(int paramInt)
  {
    byte[] arrayOfByte = new byte[4];
    for (int i = 0; i < 4; i++)
    {
      int j = arrayOfByte.length - 1 - i << 3;
      arrayOfByte[i] = ((byte)(paramInt >>> j));
    }
    return arrayOfByte;
  }
  
  private static void a6(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    paramArrayOfByte[paramInt2] = ((byte)(paramInt1 >>> 16));
    paramArrayOfByte[(paramInt2 + 1)] = ((byte)(paramInt1 >>> 8));
    paramArrayOfByte[(paramInt2 + 2)] = ((byte)paramInt1);
  }
  
  public static byte[] a7(long paramLong)
  {
    byte[] arrayOfByte;
    ByteBuffer.wrap(arrayOfByte = new byte[8]).putLong(paramLong);
    return arrayOfByte;
  }
  
  public static void main(String[] paramArrayOfString)
  {
    for (paramArrayOfString = 0.0F; paramArrayOfString < 6.0F; paramArrayOfString += 1.0F)
    {
      float f1 = Math.max(0.0F, (float)Math.floor(paramArrayOfString) % 2.0F - (paramArrayOfString - 1.0F) - (paramArrayOfString + 1.0F) % 2.0F);
      float f2 = Math.max(0.0F, (float)Math.floor(paramArrayOfString) % 2.0F - (paramArrayOfString - 3.0F) - (paramArrayOfString + 1.0F) % 2.0F);
      float f3 = Math.max(0.0F, (float)Math.floor(paramArrayOfString) % 2.0F - (paramArrayOfString - 5.0F) - (paramArrayOfString + 1.0F) % 2.0F);
      System.err.println(f1 + ", " + f2 + ", " + f3);
    }
  }
  
  public static final int d(int paramInt)
  {
    return paramInt & 0xF;
  }
  
  public static void a8(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    a6(a9(a4(paramArrayOfByte, paramInt4), paramInt1, paramInt2, paramInt3), paramArrayOfByte, paramInt4);
  }
  
  private static int a9(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt4 = (1 << paramInt4 - paramInt3) - 1 << paramInt3;
    return paramInt1 & (paramInt4 ^ 0xFFFFFFFF) | paramInt2 << paramInt3 & paramInt4;
  }
  
  public static int a10(InputStream paramInputStream)
  {
    int i = 0;
    for (int j = 0; j < 4; j++)
    {
      i <<= 8;
      int k = 0;
      while ((k = paramInputStream.read()) == -1) {}
      i ^= k & 0xFF;
    }
    return i;
  }
  
  public static long a11(InputStream paramInputStream)
  {
    long l = 0L;
    for (int i = 0; i < 8; i++)
    {
      l <<= 8;
      int j = 0;
      while ((j = paramInputStream.read()) == -1) {}
      l ^= j & 0xFF;
    }
    return l;
  }
  
  public static short a12(byte[] paramArrayOfByte, int paramInt)
  {
    short s = 0;
    for (int i = 0; i < 2; i++) {
      s = (short)((short)(s << 8) ^ paramArrayOfByte[(paramInt + i)] & 0xFF);
    }
    return s;
  }
  
  public static void a13(short paramShort, byte[] paramArrayOfByte, int paramInt)
  {
    for (int i = 0; i < 2; i++)
    {
      short s = 1 - i << 3;
      paramArrayOfByte[(paramInt + i)] = ((byte)(paramShort >>> s));
    }
  }
  
  public static void a14(int paramInt, OutputStream paramOutputStream)
  {
    for (int i = 0; i < 4; i++)
    {
      int j = 3 - i << 3;
      paramOutputStream.write((byte)(paramInt >>> j));
    }
  }
  
  static
  {
    ByteUtil.class.desiredAssertionStatus();
    int i = (ByteUtil.field_2133 = new byte[24576]).length / 3;
    for (int j = 0; j < i; j = (short)(j + 1))
    {
      int k = j * 3;
      a6(a9(a4(field_2133, k), j, 0, 11), field_2133, k);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.common.util.ByteUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */