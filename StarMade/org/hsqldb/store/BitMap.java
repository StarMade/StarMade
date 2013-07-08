package org.hsqldb.store;

public class BitMap
{
  int defaultCapacity;
  int capacity;
  int[] map;
  int limitPos;
  
  public BitMap(int paramInt)
  {
    int i = paramInt / 32;
    if (paramInt % 32 != 0) {
      i++;
    }
    this.defaultCapacity = (this.capacity = i * 32);
    this.map = new int[i];
    this.limitPos = 0;
  }
  
  public int size()
  {
    return this.limitPos;
  }
  
  public void setSize(int paramInt)
  {
    this.limitPos = paramInt;
  }
  
  public void reset()
  {
    this.map = new int[this.defaultCapacity / 32];
    this.capacity = this.defaultCapacity;
    this.limitPos = 0;
  }
  
  public int set(int paramInt)
  {
    while (paramInt >= this.capacity) {
      doubleCapacity();
    }
    if (paramInt >= this.limitPos) {
      this.limitPos = (paramInt + 1);
    }
    int i = paramInt >> 5;
    int j = -2147483648 >>> (paramInt & 0x1F);
    int k = this.map[i];
    int m = (k & j) == 0 ? 0 : 1;
    this.map[i] = (k | j);
    return m;
  }
  
  public int unset(int paramInt)
  {
    while (paramInt >= this.capacity) {
      doubleCapacity();
    }
    if (paramInt >= this.limitPos)
    {
      this.limitPos = (paramInt + 1);
      return 0;
    }
    int i = paramInt >> 5;
    int j = -2147483648 >>> (paramInt & 0x1F);
    int k = this.map[i];
    int m = (k & j) == 0 ? 0 : 1;
    j ^= -1;
    this.map[i] = (k & j);
    return m;
  }
  
  public int get(int paramInt)
  {
    while (paramInt >= this.capacity) {
      doubleCapacity();
    }
    if (paramInt >= this.limitPos)
    {
      this.limitPos = (paramInt + 1);
      return 0;
    }
    int i = paramInt >> 5;
    int j = -2147483648 >>> (paramInt & 0x1F);
    int k = this.map[i];
    return (k & j) == 0 ? 0 : 1;
  }
  
  public boolean isSet(int paramInt)
  {
    return get(paramInt) == 1;
  }
  
  public byte[] getBytes()
  {
    byte[] arrayOfByte = new byte[(this.limitPos + 7) / 8];
    if (arrayOfByte.length == 0) {
      return arrayOfByte;
    }
    int i = 0;
    for (;;)
    {
      int j = this.map[(i / 4)];
      arrayOfByte[(i++)] = ((byte)(j >>> 24));
      if (i == arrayOfByte.length) {
        break;
      }
      arrayOfByte[(i++)] = ((byte)(j >>> 16));
      if (i == arrayOfByte.length) {
        break;
      }
      arrayOfByte[(i++)] = ((byte)(j >>> 8));
      if (i == arrayOfByte.length) {
        break;
      }
      arrayOfByte[(i++)] = ((byte)j);
      if (i == arrayOfByte.length) {
        break;
      }
    }
    return arrayOfByte;
  }
  
  private void doubleCapacity()
  {
    int[] arrayOfInt = new int[this.map.length * 2];
    this.capacity *= 2;
    System.arraycopy(this.map, 0, arrayOfInt, 0, this.map.length);
    this.map = arrayOfInt;
  }
  
  public static int setByte(int paramInt1, byte paramByte, int paramInt2)
  {
    int i = (paramByte & 0xFF) << 24 - paramInt2;
    int j = -16777216 >>> paramInt2;
    j ^= -1;
    paramInt1 &= j;
    return paramInt1 | i;
  }
  
  public static int set(int paramInt1, int paramInt2)
  {
    int i = -2147483648 >>> paramInt2;
    return paramInt1 | i;
  }
  
  public static byte set(byte paramByte, int paramInt)
  {
    byte b = 128 >>> paramInt;
    return (byte)(paramByte | b);
  }
  
  public static int unset(int paramInt1, int paramInt2)
  {
    int i = -2147483648 >>> paramInt2;
    i ^= -1;
    return paramInt1 & i;
  }
  
  public static boolean isSet(int paramInt1, int paramInt2)
  {
    int i = -2147483648 >>> paramInt2;
    return (paramInt1 & i) != 0;
  }
  
  public static boolean isSet(byte paramByte, int paramInt)
  {
    byte b = 128 >>> paramInt;
    return (paramByte & b) != 0;
  }
  
  public static boolean isSet(byte[] paramArrayOfByte, int paramInt)
  {
    int i = 128 >>> (paramInt & 0x7);
    int j = paramInt / 8;
    if (j >= paramArrayOfByte.length) {
      return false;
    }
    int k = paramArrayOfByte[j];
    return (k & i) != 0;
  }
  
  public static void unset(byte[] paramArrayOfByte, int paramInt)
  {
    int i = 128 >>> (paramInt & 0x7);
    i ^= -1;
    int j = paramInt / 8;
    if (j >= paramArrayOfByte.length) {
      return;
    }
    int k = paramArrayOfByte[j];
    paramArrayOfByte[j] = ((byte)(k & i));
  }
  
  public static void set(byte[] paramArrayOfByte, int paramInt)
  {
    int i = 128 >>> (paramInt & 0x7);
    int j = paramInt / 8;
    if (j >= paramArrayOfByte.length) {
      return;
    }
    int k = paramArrayOfByte[j];
    paramArrayOfByte[j] = ((byte)(k | i));
  }
  
  public static void and(byte[] paramArrayOfByte, int paramInt1, byte paramByte, int paramInt2)
  {
    int i = paramInt1 & 0x7;
    int j = (paramByte & 0xFF) >>> i;
    int k = 255 >> i;
    int m = paramInt1 / 8;
    if (paramInt2 < 8)
    {
      k >>>= 8 - paramInt2;
      k <<= 8 - paramInt2;
    }
    j &= k;
    k ^= -1;
    if (m >= paramArrayOfByte.length) {
      return;
    }
    int n = paramArrayOfByte[m];
    paramArrayOfByte[m] = ((byte)(n & k));
    n = (byte)(n & j);
    paramArrayOfByte[m] = ((byte)(paramArrayOfByte[m] | n));
    if (i == 0) {
      return;
    }
    i = 8 - i;
    if (paramInt2 > i)
    {
      j = (paramByte & 0xFF) << 8 >>> i;
      k = 65280 >>> i;
      k ^= -1;
      n = paramArrayOfByte[(m + 1)];
      paramArrayOfByte[(m + 1)] = ((byte)(n & k));
      n = (byte)(n & j);
      paramArrayOfByte[(m + 1)] = ((byte)(paramArrayOfByte[(m + 1)] | n));
    }
  }
  
  public static void or(byte[] paramArrayOfByte, int paramInt1, byte paramByte, int paramInt2)
  {
    int i = paramInt1 & 0x7;
    int j = (paramByte & 0xFF) >>> i;
    int k = paramInt1 / 8;
    if (k >= paramArrayOfByte.length) {
      return;
    }
    int m = (byte)(paramArrayOfByte[k] | j);
    paramArrayOfByte[k] = m;
    if (i == 0) {
      return;
    }
    i = 8 - i;
    if (paramInt2 > i)
    {
      j = (paramByte & 0xFF) << 8 >>> i;
      m = (byte)(paramArrayOfByte[(k + 1)] | j);
      paramArrayOfByte[(k + 1)] = m;
    }
  }
  
  public static void overlay(byte[] paramArrayOfByte, int paramInt1, byte paramByte, int paramInt2)
  {
    int i = paramInt1 & 0x7;
    int j = (paramByte & 0xFF) >>> i;
    int k = 255 >> i;
    int m = paramInt1 / 8;
    if (paramInt2 < 8)
    {
      k >>>= 8 - paramInt2;
      k <<= 8 - paramInt2;
    }
    j &= k;
    k ^= -1;
    if (m >= paramArrayOfByte.length) {
      return;
    }
    int n = paramArrayOfByte[m];
    n = (byte)(n & k);
    paramArrayOfByte[m] = ((byte)(n | j));
    if (i == 0) {
      return;
    }
    i = 8 - i;
    if (paramInt2 > i)
    {
      j = (paramByte & 0xFF) << 8 >>> i;
      k = 65280 >>> i;
      k ^= -1;
      n = paramArrayOfByte[(m + 1)];
      n = (byte)(n & k);
      paramArrayOfByte[(m + 1)] = ((byte)(n | j));
    }
  }
  
  public static int compare(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int i = paramArrayOfByte1.length > paramArrayOfByte2.length ? paramArrayOfByte2.length : paramArrayOfByte1.length;
    for (int j = 0; j < i; j++) {
      if (paramArrayOfByte1[j] != paramArrayOfByte2[j]) {
        return (paramArrayOfByte1[j] & 0xFF) > (paramArrayOfByte2[j] & 0xFF) ? 1 : -1;
      }
    }
    if (paramArrayOfByte1.length == paramArrayOfByte2.length) {
      return 0;
    }
    return paramArrayOfByte1.length > paramArrayOfByte2.length ? 1 : -1;
  }
  
  public static byte[] and(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int i = paramArrayOfByte1.length > paramArrayOfByte2.length ? paramArrayOfByte1.length : paramArrayOfByte2.length;
    int j = paramArrayOfByte1.length > paramArrayOfByte2.length ? paramArrayOfByte2.length : paramArrayOfByte1.length;
    byte[] arrayOfByte = new byte[i];
    for (int k = 0; k < j; k++) {
      arrayOfByte[k] = ((byte)(paramArrayOfByte1[k] & paramArrayOfByte2[k]));
    }
    return arrayOfByte;
  }
  
  public static byte[] or(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int i = paramArrayOfByte1.length > paramArrayOfByte2.length ? paramArrayOfByte1.length : paramArrayOfByte2.length;
    int j = paramArrayOfByte1.length > paramArrayOfByte2.length ? paramArrayOfByte2.length : paramArrayOfByte1.length;
    byte[] arrayOfByte1 = new byte[i];
    if (i != j)
    {
      byte[] arrayOfByte2 = paramArrayOfByte1.length > paramArrayOfByte2.length ? paramArrayOfByte1 : paramArrayOfByte2;
      System.arraycopy(arrayOfByte2, j, arrayOfByte1, j, i - j);
    }
    for (int k = 0; k < j; k++) {
      arrayOfByte1[k] = ((byte)(paramArrayOfByte1[k] | paramArrayOfByte2[k]));
    }
    return arrayOfByte1;
  }
  
  public static byte[] xor(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int i = paramArrayOfByte1.length > paramArrayOfByte2.length ? paramArrayOfByte1.length : paramArrayOfByte2.length;
    int j = paramArrayOfByte1.length > paramArrayOfByte2.length ? paramArrayOfByte2.length : paramArrayOfByte1.length;
    byte[] arrayOfByte1 = new byte[i];
    if (i != j)
    {
      byte[] arrayOfByte2 = paramArrayOfByte1.length > paramArrayOfByte2.length ? paramArrayOfByte1 : paramArrayOfByte2;
      System.arraycopy(arrayOfByte2, j, arrayOfByte1, j, i - j);
    }
    for (int k = 0; k < j; k++) {
      arrayOfByte1[k] = ((byte)(paramArrayOfByte1[k] ^ paramArrayOfByte2[k]));
    }
    return arrayOfByte1;
  }
  
  public static byte[] not(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[paramArrayOfByte.length];
    for (int i = 0; i < paramArrayOfByte.length; i++) {
      arrayOfByte[i] = ((byte)(paramArrayOfByte[i] ^ 0xFFFFFFFF));
    }
    return arrayOfByte;
  }
  
  public static boolean hasAnyBitSet(byte[] paramArrayOfByte)
  {
    for (int i = 0; i < paramArrayOfByte.length; i++) {
      if (paramArrayOfByte[i] != 0) {
        return true;
      }
    }
    return false;
  }
  
  public static byte[] leftShift(byte[] paramArrayOfByte, int paramInt)
  {
    byte[] arrayOfByte = new byte[paramArrayOfByte.length];
    int i = paramInt / 8;
    if (i >= paramArrayOfByte.length) {
      return arrayOfByte;
    }
    paramInt %= 8;
    int j;
    int k;
    if (paramInt == 0)
    {
      j = 0;
      for (k = i; k < paramArrayOfByte.length; k++)
      {
        arrayOfByte[j] = paramArrayOfByte[k];
        j++;
      }
    }
    else
    {
      j = 0;
      for (k = i; k < paramArrayOfByte.length; k++)
      {
        int m = (paramArrayOfByte[k] & 0xFF) << paramInt;
        arrayOfByte[j] = ((byte)m);
        if (j > 0)
        {
          int tmp102_101 = (j - 1);
          byte[] tmp102_97 = arrayOfByte;
          tmp102_97[tmp102_101] = ((byte)(tmp102_97[tmp102_101] | (byte)(m >>> 8)));
        }
        j++;
      }
    }
    return arrayOfByte;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.store.BitMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */