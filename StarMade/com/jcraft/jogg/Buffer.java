package com.jcraft.jogg;

import java.io.PrintStream;

public class Buffer
{
  private static final int BUFFER_INCREMENT = 256;
  private static final int[] mask = { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, 2147483647, -1 };
  int ptr = 0;
  byte[] buffer = null;
  int endbit = 0;
  int endbyte = 0;
  int storage = 0;
  
  public void writeinit()
  {
    this.buffer = new byte[256];
    this.ptr = 0;
    this.buffer[0] = 0;
    this.storage = 256;
  }
  
  public void write(byte[] paramArrayOfByte)
  {
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      if (paramArrayOfByte[i] == 0) {
        break;
      }
      write(paramArrayOfByte[i], 8);
    }
  }
  
  public void read(byte[] paramArrayOfByte, int paramInt)
  {
    int i = 0;
    while (paramInt-- != 0) {
      paramArrayOfByte[(i++)] = ((byte)read(8));
    }
  }
  
  void reset()
  {
    this.ptr = 0;
    this.buffer[0] = 0;
    this.endbit = (this.endbyte = 0);
  }
  
  public void writeclear()
  {
    this.buffer = null;
  }
  
  public void readinit(byte[] paramArrayOfByte, int paramInt)
  {
    readinit(paramArrayOfByte, 0, paramInt);
  }
  
  public void readinit(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.ptr = paramInt1;
    this.buffer = paramArrayOfByte;
    this.endbit = (this.endbyte = 0);
    this.storage = paramInt2;
  }
  
  public void write(int paramInt1, int paramInt2)
  {
    if (this.endbyte + 4 >= this.storage)
    {
      byte[] arrayOfByte = new byte[this.storage + 256];
      System.arraycopy(this.buffer, 0, arrayOfByte, 0, this.storage);
      this.buffer = arrayOfByte;
      this.storage += 256;
    }
    paramInt1 &= mask[paramInt2];
    paramInt2 += this.endbit;
    int tmp78_75 = this.ptr;
    byte[] tmp78_71 = this.buffer;
    tmp78_71[tmp78_75] = ((byte)(tmp78_71[tmp78_75] | (byte)(paramInt1 << this.endbit)));
    if (paramInt2 >= 8)
    {
      this.buffer[(this.ptr + 1)] = ((byte)(paramInt1 >>> 8 - this.endbit));
      if (paramInt2 >= 16)
      {
        this.buffer[(this.ptr + 2)] = ((byte)(paramInt1 >>> 16 - this.endbit));
        if (paramInt2 >= 24)
        {
          this.buffer[(this.ptr + 3)] = ((byte)(paramInt1 >>> 24 - this.endbit));
          if (paramInt2 >= 32) {
            if (this.endbit > 0) {
              this.buffer[(this.ptr + 4)] = ((byte)(paramInt1 >>> 32 - this.endbit));
            } else {
              this.buffer[(this.ptr + 4)] = 0;
            }
          }
        }
      }
    }
    this.endbyte += paramInt2 / 8;
    this.ptr += paramInt2 / 8;
    this.endbit = (paramInt2 & 0x7);
  }
  
  public int look(int paramInt)
  {
    int j = mask[paramInt];
    paramInt += this.endbit;
    if ((this.endbyte + 4 >= this.storage) && (this.endbyte + (paramInt - 1) / 8 >= this.storage)) {
      return -1;
    }
    int i = (this.buffer[this.ptr] & 0xFF) >>> this.endbit;
    if (paramInt > 8)
    {
      i |= (this.buffer[(this.ptr + 1)] & 0xFF) << 8 - this.endbit;
      if (paramInt > 16)
      {
        i |= (this.buffer[(this.ptr + 2)] & 0xFF) << 16 - this.endbit;
        if (paramInt > 24)
        {
          i |= (this.buffer[(this.ptr + 3)] & 0xFF) << 24 - this.endbit;
          if ((paramInt > 32) && (this.endbit != 0)) {
            i |= (this.buffer[(this.ptr + 4)] & 0xFF) << 32 - this.endbit;
          }
        }
      }
    }
    return j & i;
  }
  
  public int look1()
  {
    if (this.endbyte >= this.storage) {
      return -1;
    }
    return this.buffer[this.ptr] >> this.endbit & 0x1;
  }
  
  public void adv(int paramInt)
  {
    paramInt += this.endbit;
    this.ptr += paramInt / 8;
    this.endbyte += paramInt / 8;
    this.endbit = (paramInt & 0x7);
  }
  
  public void adv1()
  {
    this.endbit += 1;
    if (this.endbit > 7)
    {
      this.endbit = 0;
      this.ptr += 1;
      this.endbyte += 1;
    }
  }
  
  public int read(int paramInt)
  {
    int j = mask[paramInt];
    paramInt += this.endbit;
    if (this.endbyte + 4 >= this.storage)
    {
      i = -1;
      if (this.endbyte + (paramInt - 1) / 8 >= this.storage)
      {
        this.ptr += paramInt / 8;
        this.endbyte += paramInt / 8;
        this.endbit = (paramInt & 0x7);
        return i;
      }
    }
    int i = (this.buffer[this.ptr] & 0xFF) >>> this.endbit;
    if (paramInt > 8)
    {
      i |= (this.buffer[(this.ptr + 1)] & 0xFF) << 8 - this.endbit;
      if (paramInt > 16)
      {
        i |= (this.buffer[(this.ptr + 2)] & 0xFF) << 16 - this.endbit;
        if (paramInt > 24)
        {
          i |= (this.buffer[(this.ptr + 3)] & 0xFF) << 24 - this.endbit;
          if ((paramInt > 32) && (this.endbit != 0)) {
            i |= (this.buffer[(this.ptr + 4)] & 0xFF) << 32 - this.endbit;
          }
        }
      }
    }
    i &= j;
    this.ptr += paramInt / 8;
    this.endbyte += paramInt / 8;
    this.endbit = (paramInt & 0x7);
    return i;
  }
  
  public int readB(int paramInt)
  {
    int j = 32 - paramInt;
    paramInt += this.endbit;
    if (this.endbyte + 4 >= this.storage)
    {
      i = -1;
      if (this.endbyte * 8 + paramInt > this.storage * 8)
      {
        this.ptr += paramInt / 8;
        this.endbyte += paramInt / 8;
        this.endbit = (paramInt & 0x7);
        return i;
      }
    }
    int i = (this.buffer[this.ptr] & 0xFF) << 24 + this.endbit;
    if (paramInt > 8)
    {
      i |= (this.buffer[(this.ptr + 1)] & 0xFF) << 16 + this.endbit;
      if (paramInt > 16)
      {
        i |= (this.buffer[(this.ptr + 2)] & 0xFF) << 8 + this.endbit;
        if (paramInt > 24)
        {
          i |= (this.buffer[(this.ptr + 3)] & 0xFF) << this.endbit;
          if ((paramInt > 32) && (this.endbit != 0)) {
            i |= (this.buffer[(this.ptr + 4)] & 0xFF) >> 8 - this.endbit;
          }
        }
      }
    }
    i = i >>> (j >> 1) >>> (j + 1 >> 1);
    this.ptr += paramInt / 8;
    this.endbyte += paramInt / 8;
    this.endbit = (paramInt & 0x7);
    return i;
  }
  
  public int read1()
  {
    if (this.endbyte >= this.storage)
    {
      i = -1;
      this.endbit += 1;
      if (this.endbit > 7)
      {
        this.endbit = 0;
        this.ptr += 1;
        this.endbyte += 1;
      }
      return i;
    }
    int i = this.buffer[this.ptr] >> this.endbit & 0x1;
    this.endbit += 1;
    if (this.endbit > 7)
    {
      this.endbit = 0;
      this.ptr += 1;
      this.endbyte += 1;
    }
    return i;
  }
  
  public int bytes()
  {
    return this.endbyte + (this.endbit + 7) / 8;
  }
  
  public int bits()
  {
    return this.endbyte * 8 + this.endbit;
  }
  
  public byte[] buffer()
  {
    return this.buffer;
  }
  
  public static int ilog(int paramInt)
  {
    int i = 0;
    while (paramInt > 0)
    {
      i++;
      paramInt >>>= 1;
    }
    return i;
  }
  
  public static void report(String paramString)
  {
    System.err.println(paramString);
    System.exit(1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jogg.Buffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */