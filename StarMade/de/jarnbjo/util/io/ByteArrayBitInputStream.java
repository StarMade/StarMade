package de.jarnbjo.util.io;

import java.io.IOException;

public class ByteArrayBitInputStream
  implements BitInputStream
{
  private byte[] source;
  private byte currentByte;
  private int endian;
  private int byteIndex = 0;
  private int bitIndex = 0;
  
  public ByteArrayBitInputStream(byte[] source)
  {
    this(source, 0);
  }
  
  public ByteArrayBitInputStream(byte[] source, int endian)
  {
    this.endian = endian;
    this.source = source;
    this.currentByte = source[0];
    this.bitIndex = (endian == 0 ? 0 : 7);
  }
  
  public boolean getBit()
    throws IOException
  {
    if (this.endian == 0)
    {
      if (this.bitIndex > 7)
      {
        this.bitIndex = 0;
        this.currentByte = this.source[(++this.byteIndex)];
      }
      return (this.currentByte & 1 << this.bitIndex++) != 0;
    }
    if (this.bitIndex < 0)
    {
      this.bitIndex = 7;
      this.currentByte = this.source[(++this.byteIndex)];
    }
    return (this.currentByte & 1 << this.bitIndex--) != 0;
  }
  
  public int getInt(int bits)
    throws IOException
  {
    if (bits > 32) {
      throw new IllegalArgumentException("Argument \"bits\" must be <= 32");
    }
    int res = 0;
    if (this.endian == 0)
    {
      for (int local_i = 0; local_i < bits; local_i++) {
        if (getBit()) {
          res |= 1 << local_i;
        }
      }
    }
    else
    {
      if (this.bitIndex < 0)
      {
        this.bitIndex = 7;
        this.currentByte = this.source[(++this.byteIndex)];
      }
      if (bits <= this.bitIndex + 1)
      {
        int local_i = this.currentByte & 0xFF;
        int offset = 1 + this.bitIndex - bits;
        int mask = (1 << bits) - 1 << offset;
        res = (local_i & mask) >> offset;
        this.bitIndex -= bits;
      }
      else
      {
        res = (this.currentByte & 0xFF & (1 << this.bitIndex + 1) - 1) << bits - this.bitIndex - 1;
        bits -= this.bitIndex + 1;
        for (this.currentByte = this.source[(++this.byteIndex)]; bits >= 8; this.currentByte = this.source[(++this.byteIndex)])
        {
          bits -= 8;
          res |= (this.source[this.byteIndex] & 0xFF) << bits;
        }
        if (bits > 0)
        {
          int local_i = this.source[this.byteIndex] & 0xFF;
          res |= local_i >> 8 - bits & (1 << bits) - 1;
          this.bitIndex = (7 - bits);
        }
        else
        {
          this.currentByte = this.source[(--this.byteIndex)];
          this.bitIndex = -1;
        }
      }
    }
    return res;
  }
  
  public int getSignedInt(int bits)
    throws IOException
  {
    int raw = getInt(bits);
    if (raw >= 1 << bits - 1) {
      raw -= (1 << bits);
    }
    return raw;
  }
  
  public int getInt(HuffmanNode root)
    throws IOException
  {
    while (root.value == null)
    {
      if (this.bitIndex > 7)
      {
        this.bitIndex = 0;
        this.currentByte = this.source[(++this.byteIndex)];
      }
      root = (this.currentByte & 1 << this.bitIndex++) != 0 ? root.field_2236 : root.field_2235;
    }
    return root.value.intValue();
  }
  
  public long getLong(int bits)
    throws IOException
  {
    if (bits > 64) {
      throw new IllegalArgumentException("Argument \"bits\" must be <= 64");
    }
    long res = 0L;
    if (this.endian == 0) {
      for (int local_i = 0; local_i < bits; local_i++) {
        if (getBit()) {
          res |= 1L << local_i;
        }
      }
    } else {
      for (int local_i = bits - 1; local_i >= 0; local_i--) {
        if (getBit()) {
          res |= 1L << local_i;
        }
      }
    }
    return res;
  }
  
  public int readSignedRice(int order)
    throws IOException
  {
    int msbs = -1;
    int lsbs = 0;
    int res = 0;
    if (this.endian == 0) {
      throw new UnsupportedOperationException("ByteArrayBitInputStream.readSignedRice() is only supported in big endian mode");
    }
    byte local_cb = this.source[this.byteIndex];
    do
    {
      msbs++;
      if (this.bitIndex < 0)
      {
        this.bitIndex = 7;
        this.byteIndex += 1;
        local_cb = this.source[this.byteIndex];
      }
    } while ((local_cb & 1 << this.bitIndex--) == 0);
    int bits = order;
    if (this.bitIndex < 0)
    {
      this.bitIndex = 7;
      this.byteIndex += 1;
    }
    if (bits <= this.bitIndex + 1)
    {
      int local_ci = this.source[this.byteIndex] & 0xFF;
      int offset = 1 + this.bitIndex - bits;
      int mask = (1 << bits) - 1 << offset;
      lsbs = (local_ci & mask) >> offset;
      this.bitIndex -= bits;
    }
    else
    {
      lsbs = (this.source[this.byteIndex] & 0xFF & (1 << this.bitIndex + 1) - 1) << bits - this.bitIndex - 1;
      bits -= this.bitIndex + 1;
      for (this.byteIndex += 1; bits >= 8; this.byteIndex += 1)
      {
        bits -= 8;
        lsbs |= (this.source[this.byteIndex] & 0xFF) << bits;
      }
      if (bits > 0)
      {
        int local_ci = this.source[this.byteIndex] & 0xFF;
        lsbs |= local_ci >> 8 - bits & (1 << bits) - 1;
        this.bitIndex = (7 - bits);
      }
      else
      {
        this.byteIndex -= 1;
        this.bitIndex = -1;
      }
    }
    res = msbs << order | lsbs;
    return (res & 0x1) == 1 ? -(res >> 1) - 1 : res >> 1;
  }
  
  public void readSignedRice(int order, int[] buffer, int off, int len)
    throws IOException
  {
    if (this.endian == 0) {
      throw new UnsupportedOperationException("ByteArrayBitInputStream.readSignedRice() is only supported in big endian mode");
    }
    for (int local_i = off; local_i < off + len; local_i++)
    {
      int msbs = -1;
      int lsbs = 0;
      byte local_cb = this.source[this.byteIndex];
      do
      {
        msbs++;
        if (this.bitIndex < 0)
        {
          this.bitIndex = 7;
          this.byteIndex += 1;
          local_cb = this.source[this.byteIndex];
        }
      } while ((local_cb & 1 << this.bitIndex--) == 0);
      int bits = order;
      if (this.bitIndex < 0)
      {
        this.bitIndex = 7;
        this.byteIndex += 1;
      }
      if (bits <= this.bitIndex + 1)
      {
        int local_ci = this.source[this.byteIndex] & 0xFF;
        int offset = 1 + this.bitIndex - bits;
        int mask = (1 << bits) - 1 << offset;
        lsbs = (local_ci & mask) >> offset;
        this.bitIndex -= bits;
      }
      else
      {
        lsbs = (this.source[this.byteIndex] & 0xFF & (1 << this.bitIndex + 1) - 1) << bits - this.bitIndex - 1;
        bits -= this.bitIndex + 1;
        for (this.byteIndex += 1; bits >= 8; this.byteIndex += 1)
        {
          bits -= 8;
          lsbs |= (this.source[this.byteIndex] & 0xFF) << bits;
        }
        if (bits > 0)
        {
          int local_ci = this.source[this.byteIndex] & 0xFF;
          lsbs |= local_ci >> 8 - bits & (1 << bits) - 1;
          this.bitIndex = (7 - bits);
        }
        else
        {
          this.byteIndex -= 1;
          this.bitIndex = -1;
        }
      }
      int local_ci = msbs << order | lsbs;
      buffer[local_i] = ((local_ci & 0x1) == 1 ? -(local_ci >> 1) - 1 : local_ci >> 1);
    }
  }
  
  public void align()
  {
    if ((this.endian == 1) && (this.bitIndex >= 0))
    {
      this.bitIndex = 7;
      this.byteIndex += 1;
    }
    else if ((this.endian == 0) && (this.bitIndex <= 7))
    {
      this.bitIndex = 0;
      this.byteIndex += 1;
    }
  }
  
  public void setEndian(int endian)
  {
    if ((this.endian == 1) && (endian == 0))
    {
      this.bitIndex = 0;
      this.byteIndex += 1;
    }
    else if ((this.endian == 0) && (endian == 1))
    {
      this.bitIndex = 7;
      this.byteIndex += 1;
    }
    this.endian = endian;
  }
  
  public byte[] getSource()
  {
    return this.source;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.util.io.ByteArrayBitInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */