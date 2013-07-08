package com.jcraft.jogg;

public class StreamState
{
  byte[] body_data;
  int body_storage;
  int body_fill;
  private int body_returned;
  int[] lacing_vals;
  long[] granule_vals;
  int lacing_storage;
  int lacing_fill;
  int lacing_packet;
  int lacing_returned;
  byte[] header = new byte[282];
  int header_fill;
  public int e_o_s;
  int b_o_s;
  int serialno;
  int pageno;
  long packetno;
  long granulepos;
  
  public StreamState()
  {
    init();
  }
  
  StreamState(int paramInt)
  {
    this();
    init(paramInt);
  }
  
  void init()
  {
    this.body_storage = 16384;
    this.body_data = new byte[this.body_storage];
    this.lacing_storage = 1024;
    this.lacing_vals = new int[this.lacing_storage];
    this.granule_vals = new long[this.lacing_storage];
  }
  
  public void init(int paramInt)
  {
    if (this.body_data == null)
    {
      init();
    }
    else
    {
      for (int i = 0; i < this.body_data.length; i++) {
        this.body_data[i] = 0;
      }
      for (int j = 0; j < this.lacing_vals.length; j++) {
        this.lacing_vals[j] = 0;
      }
      for (int k = 0; k < this.granule_vals.length; k++) {
        this.granule_vals[k] = 0L;
      }
    }
    this.serialno = paramInt;
  }
  
  public void clear()
  {
    this.body_data = null;
    this.lacing_vals = null;
    this.granule_vals = null;
  }
  
  void destroy()
  {
    clear();
  }
  
  void body_expand(int paramInt)
  {
    if (this.body_storage <= this.body_fill + paramInt)
    {
      this.body_storage += paramInt + 1024;
      byte[] arrayOfByte = new byte[this.body_storage];
      System.arraycopy(this.body_data, 0, arrayOfByte, 0, this.body_data.length);
      this.body_data = arrayOfByte;
    }
  }
  
  void lacing_expand(int paramInt)
  {
    if (this.lacing_storage <= this.lacing_fill + paramInt)
    {
      this.lacing_storage += paramInt + 32;
      int[] arrayOfInt = new int[this.lacing_storage];
      System.arraycopy(this.lacing_vals, 0, arrayOfInt, 0, this.lacing_vals.length);
      this.lacing_vals = arrayOfInt;
      long[] arrayOfLong = new long[this.lacing_storage];
      System.arraycopy(this.granule_vals, 0, arrayOfLong, 0, this.granule_vals.length);
      this.granule_vals = arrayOfLong;
    }
  }
  
  public int packetin(Packet paramPacket)
  {
    int i = paramPacket.bytes / 255 + 1;
    if (this.body_returned != 0)
    {
      this.body_fill -= this.body_returned;
      if (this.body_fill != 0) {
        System.arraycopy(this.body_data, this.body_returned, this.body_data, 0, this.body_fill);
      }
      this.body_returned = 0;
    }
    body_expand(paramPacket.bytes);
    lacing_expand(i);
    System.arraycopy(paramPacket.packet_base, paramPacket.packet, this.body_data, this.body_fill, paramPacket.bytes);
    this.body_fill += paramPacket.bytes;
    for (int j = 0; j < i - 1; j++)
    {
      this.lacing_vals[(this.lacing_fill + j)] = 255;
      this.granule_vals[(this.lacing_fill + j)] = this.granulepos;
    }
    this.lacing_vals[(this.lacing_fill + j)] = (paramPacket.bytes % 255);
    this.granulepos = (this.granule_vals[(this.lacing_fill + j)] = paramPacket.granulepos);
    this.lacing_vals[this.lacing_fill] |= 256;
    this.lacing_fill += i;
    this.packetno += 1L;
    if (paramPacket.e_o_s != 0) {
      this.e_o_s = 1;
    }
    return 0;
  }
  
  public int packetout(Packet paramPacket)
  {
    int i = this.lacing_returned;
    if (this.lacing_packet <= i) {
      return 0;
    }
    if ((this.lacing_vals[i] & 0x400) != 0)
    {
      this.lacing_returned += 1;
      this.packetno += 1L;
      return -1;
    }
    int j = this.lacing_vals[i] & 0xFF;
    int k = 0;
    paramPacket.packet_base = this.body_data;
    paramPacket.packet = this.body_returned;
    paramPacket.e_o_s = (this.lacing_vals[i] & 0x200);
    paramPacket.b_o_s = (this.lacing_vals[i] & 0x100);
    k += j;
    while (j == 255)
    {
      int m = this.lacing_vals[(++i)];
      j = m & 0xFF;
      if ((m & 0x200) != 0) {
        paramPacket.e_o_s = 512;
      }
      k += j;
    }
    paramPacket.packetno = this.packetno;
    paramPacket.granulepos = this.granule_vals[i];
    paramPacket.bytes = k;
    this.body_returned += k;
    this.lacing_returned = (i + 1);
    this.packetno += 1L;
    return 1;
  }
  
  public int pagein(Page paramPage)
  {
    byte[] arrayOfByte1 = paramPage.header_base;
    int i = paramPage.header;
    byte[] arrayOfByte2 = paramPage.body_base;
    int j = paramPage.body;
    int k = paramPage.body_len;
    int m = 0;
    int n = paramPage.version();
    int i1 = paramPage.continued();
    int i2 = paramPage.bos();
    int i3 = paramPage.eos();
    long l = paramPage.granulepos();
    int i4 = paramPage.serialno();
    int i5 = paramPage.pageno();
    int i6 = arrayOfByte1[(i + 26)] & 0xFF;
    int i7 = this.lacing_returned;
    int i8 = this.body_returned;
    if (i8 != 0)
    {
      this.body_fill -= i8;
      if (this.body_fill != 0) {
        System.arraycopy(this.body_data, i8, this.body_data, 0, this.body_fill);
      }
      this.body_returned = 0;
    }
    if (i7 != 0)
    {
      if (this.lacing_fill - i7 != 0)
      {
        System.arraycopy(this.lacing_vals, i7, this.lacing_vals, 0, this.lacing_fill - i7);
        System.arraycopy(this.granule_vals, i7, this.granule_vals, 0, this.lacing_fill - i7);
      }
      this.lacing_fill -= i7;
      this.lacing_packet -= i7;
      this.lacing_returned = 0;
    }
    if (i4 != this.serialno) {
      return -1;
    }
    if (n > 0) {
      return -1;
    }
    lacing_expand(i6 + 1);
    if (i5 != this.pageno)
    {
      for (i7 = this.lacing_packet; i7 < this.lacing_fill; i7++) {
        this.body_fill -= (this.lacing_vals[i7] & 0xFF);
      }
      this.lacing_fill = this.lacing_packet;
      if (this.pageno != -1)
      {
        this.lacing_vals[(this.lacing_fill++)] = 1024;
        this.lacing_packet += 1;
      }
      if (i1 != 0)
      {
        i2 = 0;
        while (m < i6)
        {
          i8 = arrayOfByte1[(i + 27 + m)] & 0xFF;
          j += i8;
          k -= i8;
          if (i8 < 255)
          {
            m++;
            break;
          }
          m++;
        }
      }
    }
    if (k != 0)
    {
      body_expand(k);
      System.arraycopy(arrayOfByte2, j, this.body_data, this.body_fill, k);
      this.body_fill += k;
    }
    i7 = -1;
    while (m < i6)
    {
      i8 = arrayOfByte1[(i + 27 + m)] & 0xFF;
      this.lacing_vals[this.lacing_fill] = i8;
      this.granule_vals[this.lacing_fill] = -1L;
      if (i2 != 0)
      {
        this.lacing_vals[this.lacing_fill] |= 256;
        i2 = 0;
      }
      if (i8 < 255) {
        i7 = this.lacing_fill;
      }
      this.lacing_fill += 1;
      m++;
      if (i8 < 255) {
        this.lacing_packet = this.lacing_fill;
      }
    }
    if (i7 != -1) {
      this.granule_vals[i7] = l;
    }
    if (i3 != 0)
    {
      this.e_o_s = 1;
      if (this.lacing_fill > 0) {
        this.lacing_vals[(this.lacing_fill - 1)] |= 512;
      }
    }
    this.pageno = (i5 + 1);
    return 0;
  }
  
  public int flush(Page paramPage)
  {
    int j = 0;
    int k = this.lacing_fill > 255 ? 255 : this.lacing_fill;
    int m = 0;
    int n = 0;
    long l = this.granule_vals[0];
    if (k == 0) {
      return 0;
    }
    if (this.b_o_s == 0)
    {
      l = 0L;
      for (j = 0; j < k; j++) {
        if ((this.lacing_vals[j] & 0xFF) < 255)
        {
          j++;
          break;
        }
      }
    }
    else
    {
      for (j = 0; j < k; j++)
      {
        if (n > 4096) {
          break;
        }
        n += (this.lacing_vals[j] & 0xFF);
        l = this.granule_vals[j];
      }
    }
    System.arraycopy("OggS".getBytes(), 0, this.header, 0, 4);
    this.header[4] = 0;
    this.header[5] = 0;
    if ((this.lacing_vals[0] & 0x100) == 0)
    {
      int tmp189_188 = 5;
      byte[] tmp189_185 = this.header;
      tmp189_185[tmp189_188] = ((byte)(tmp189_185[tmp189_188] | 0x1));
    }
    if (this.b_o_s == 0)
    {
      int tmp207_206 = 5;
      byte[] tmp207_203 = this.header;
      tmp207_203[tmp207_206] = ((byte)(tmp207_203[tmp207_206] | 0x2));
    }
    if ((this.e_o_s != 0) && (this.lacing_fill == j))
    {
      int tmp233_232 = 5;
      byte[] tmp233_229 = this.header;
      tmp233_229[tmp233_232] = ((byte)(tmp233_229[tmp233_232] | 0x4));
    }
    this.b_o_s = 1;
    for (int i = 6; i < 14; i++)
    {
      this.header[i] = ((byte)(int)l);
      l >>>= 8;
    }
    int i1 = this.serialno;
    for (i = 14; i < 18; i++)
    {
      this.header[i] = ((byte)i1);
      i1 >>>= 8;
    }
    if (this.pageno == -1) {
      this.pageno = 0;
    }
    i1 = this.pageno++;
    for (i = 18; i < 22; i++)
    {
      this.header[i] = ((byte)i1);
      i1 >>>= 8;
    }
    this.header[22] = 0;
    this.header[23] = 0;
    this.header[24] = 0;
    this.header[25] = 0;
    this.header[26] = ((byte)j);
    for (i = 0; i < j; i++)
    {
      this.header[(i + 27)] = ((byte)this.lacing_vals[i]);
      m += (this.header[(i + 27)] & 0xFF);
    }
    paramPage.header_base = this.header;
    paramPage.header = 0;
    paramPage.header_len = (this.header_fill = j + 27);
    paramPage.body_base = this.body_data;
    paramPage.body = this.body_returned;
    paramPage.body_len = m;
    this.lacing_fill -= j;
    System.arraycopy(this.lacing_vals, j, this.lacing_vals, 0, this.lacing_fill * 4);
    System.arraycopy(this.granule_vals, j, this.granule_vals, 0, this.lacing_fill * 8);
    this.body_returned += m;
    paramPage.checksum();
    return 1;
  }
  
  public int pageout(Page paramPage)
  {
    if (((this.e_o_s != 0) && (this.lacing_fill != 0)) || (this.body_fill - this.body_returned > 4096) || (this.lacing_fill >= 255) || ((this.lacing_fill != 0) && (this.b_o_s == 0))) {
      return flush(paramPage);
    }
    return 0;
  }
  
  public int eof()
  {
    return this.e_o_s;
  }
  
  public int reset()
  {
    this.body_fill = 0;
    this.body_returned = 0;
    this.lacing_fill = 0;
    this.lacing_packet = 0;
    this.lacing_returned = 0;
    this.header_fill = 0;
    this.e_o_s = 0;
    this.b_o_s = 0;
    this.pageno = -1;
    this.packetno = 0L;
    this.granulepos = 0L;
    return 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jogg.StreamState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */