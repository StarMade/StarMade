package com.jcraft.jogg;

public class SyncState
{
  public byte[] data;
  int storage;
  int fill;
  int returned;
  int unsynced;
  int headerbytes;
  int bodybytes;
  private Page pageseek = new Page();
  private byte[] chksum = new byte[4];
  
  public int clear()
  {
    this.data = null;
    return 0;
  }
  
  public int buffer(int paramInt)
  {
    if (this.returned != 0)
    {
      this.fill -= this.returned;
      if (this.fill > 0) {
        System.arraycopy(this.data, this.returned, this.data, 0, this.fill);
      }
      this.returned = 0;
    }
    if (paramInt > this.storage - this.fill)
    {
      int i = paramInt + this.fill + 4096;
      if (this.data != null)
      {
        byte[] arrayOfByte = new byte[i];
        System.arraycopy(this.data, 0, arrayOfByte, 0, this.data.length);
        this.data = arrayOfByte;
      }
      else
      {
        this.data = new byte[i];
      }
      this.storage = i;
    }
    return this.fill;
  }
  
  public int wrote(int paramInt)
  {
    if (this.fill + paramInt > this.storage) {
      return -1;
    }
    this.fill += paramInt;
    return 0;
  }
  
  public int pageseek(Page paramPage)
  {
    int i = this.returned;
    int k = this.fill - this.returned;
    int j;
    int i1;
    if (this.headerbytes == 0)
    {
      if (k < 27) {
        return 0;
      }
      if ((this.data[i] != 79) || (this.data[(i + 1)] != 103) || (this.data[(i + 2)] != 103) || (this.data[(i + 3)] != 83))
      {
        this.headerbytes = 0;
        this.bodybytes = 0;
        j = 0;
        for (i1 = 0; i1 < k - 1; i1++) {
          if (this.data[(i + 1 + i1)] == 79)
          {
            j = i + 1 + i1;
            break;
          }
        }
        if (j == 0) {
          j = this.fill;
        }
        this.returned = j;
        return -(j - i);
      }
      int m = (this.data[(i + 26)] & 0xFF) + 27;
      if (k < m) {
        return 0;
      }
      for (int n = 0; n < (this.data[(i + 26)] & 0xFF); n++) {
        this.bodybytes += (this.data[(i + 27 + n)] & 0xFF);
      }
      this.headerbytes = m;
    }
    if (this.bodybytes + this.headerbytes > k) {
      return 0;
    }
    synchronized (this.chksum)
    {
      System.arraycopy(this.data, i + 22, this.chksum, 0, 4);
      this.data[(i + 22)] = 0;
      this.data[(i + 23)] = 0;
      this.data[(i + 24)] = 0;
      this.data[(i + 25)] = 0;
      Page localPage = this.pageseek;
      localPage.header_base = this.data;
      localPage.header = i;
      localPage.header_len = this.headerbytes;
      localPage.body_base = this.data;
      localPage.body = (i + this.headerbytes);
      localPage.body_len = this.bodybytes;
      localPage.checksum();
      if ((this.chksum[0] != this.data[(i + 22)]) || (this.chksum[1] != this.data[(i + 23)]) || (this.chksum[2] != this.data[(i + 24)]) || (this.chksum[3] != this.data[(i + 25)]))
      {
        System.arraycopy(this.chksum, 0, this.data, i + 22, 4);
        this.headerbytes = 0;
        this.bodybytes = 0;
        j = 0;
        for (i1 = 0; i1 < k - 1; i1++) {
          if (this.data[(i + 1 + i1)] == 79)
          {
            j = i + 1 + i1;
            break;
          }
        }
        if (j == 0) {
          j = this.fill;
        }
        this.returned = j;
        int i2 = -(j - i);
        return i2;
      }
    }
    i = this.returned;
    if (paramPage != null)
    {
      paramPage.header_base = this.data;
      paramPage.header = i;
      paramPage.header_len = this.headerbytes;
      paramPage.body_base = this.data;
      paramPage.body = (i + this.headerbytes);
      paramPage.body_len = this.bodybytes;
    }
    this.unsynced = 0;
    this.returned += (k = this.headerbytes + this.bodybytes);
    this.headerbytes = 0;
    this.bodybytes = 0;
    return k;
  }
  
  public int pageout(Page paramPage)
  {
    do
    {
      int i = pageseek(paramPage);
      if (i > 0) {
        return 1;
      }
      if (i == 0) {
        return 0;
      }
    } while (this.unsynced != 0);
    this.unsynced = 1;
    return -1;
  }
  
  public int reset()
  {
    this.fill = 0;
    this.returned = 0;
    this.unsynced = 0;
    this.headerbytes = 0;
    this.bodybytes = 0;
    return 0;
  }
  
  public void init() {}
  
  public int getDataOffset()
  {
    return this.returned;
  }
  
  public int getBufferOffset()
  {
    return this.fill;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jogg.SyncState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */