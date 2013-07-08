package com.jcraft.jogg;

public class Page
{
  private static int[] crc_lookup = new int[256];
  public byte[] header_base;
  public int header;
  public int header_len;
  public byte[] body_base;
  public int body;
  public int body_len;
  
  private static int crc_entry(int paramInt)
  {
    int i = paramInt << 24;
    for (int j = 0; j < 8; j++) {
      if ((i & 0x80000000) != 0) {
        i = i << 1 ^ 0x4C11DB7;
      } else {
        i <<= 1;
      }
    }
    return i & 0xFFFFFFFF;
  }
  
  int version()
  {
    return this.header_base[(this.header + 4)] & 0xFF;
  }
  
  int continued()
  {
    return this.header_base[(this.header + 5)] & 0x1;
  }
  
  public int bos()
  {
    return this.header_base[(this.header + 5)] & 0x2;
  }
  
  public int eos()
  {
    return this.header_base[(this.header + 5)] & 0x4;
  }
  
  public long granulepos()
  {
    long l = this.header_base[(this.header + 13)] & 0xFF;
    l = l << 8 | this.header_base[(this.header + 12)] & 0xFF;
    l = l << 8 | this.header_base[(this.header + 11)] & 0xFF;
    l = l << 8 | this.header_base[(this.header + 10)] & 0xFF;
    l = l << 8 | this.header_base[(this.header + 9)] & 0xFF;
    l = l << 8 | this.header_base[(this.header + 8)] & 0xFF;
    l = l << 8 | this.header_base[(this.header + 7)] & 0xFF;
    l = l << 8 | this.header_base[(this.header + 6)] & 0xFF;
    return l;
  }
  
  public int serialno()
  {
    return this.header_base[(this.header + 14)] & 0xFF | (this.header_base[(this.header + 15)] & 0xFF) << 8 | (this.header_base[(this.header + 16)] & 0xFF) << 16 | (this.header_base[(this.header + 17)] & 0xFF) << 24;
  }
  
  int pageno()
  {
    return this.header_base[(this.header + 18)] & 0xFF | (this.header_base[(this.header + 19)] & 0xFF) << 8 | (this.header_base[(this.header + 20)] & 0xFF) << 16 | (this.header_base[(this.header + 21)] & 0xFF) << 24;
  }
  
  void checksum()
  {
    int i = 0;
    for (int j = 0; j < this.header_len; j++) {
      i = i << 8 ^ crc_lookup[(i >>> 24 & 0xFF ^ this.header_base[(this.header + j)] & 0xFF)];
    }
    for (int k = 0; k < this.body_len; k++) {
      i = i << 8 ^ crc_lookup[(i >>> 24 & 0xFF ^ this.body_base[(this.body + k)] & 0xFF)];
    }
    this.header_base[(this.header + 22)] = ((byte)i);
    this.header_base[(this.header + 23)] = ((byte)(i >>> 8));
    this.header_base[(this.header + 24)] = ((byte)(i >>> 16));
    this.header_base[(this.header + 25)] = ((byte)(i >>> 24));
  }
  
  public Page copy()
  {
    return copy(new Page());
  }
  
  public Page copy(Page paramPage)
  {
    byte[] arrayOfByte = new byte[this.header_len];
    System.arraycopy(this.header_base, this.header, arrayOfByte, 0, this.header_len);
    paramPage.header_len = this.header_len;
    paramPage.header_base = arrayOfByte;
    paramPage.header = 0;
    arrayOfByte = new byte[this.body_len];
    System.arraycopy(this.body_base, this.body, arrayOfByte, 0, this.body_len);
    paramPage.body_len = this.body_len;
    paramPage.body_base = arrayOfByte;
    paramPage.body = 0;
    return paramPage;
  }
  
  static
  {
    for (int i = 0; i < crc_lookup.length; i++) {
      crc_lookup[i] = crc_entry(i);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jogg.Page
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */