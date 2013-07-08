package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;
import com.jcraft.jogg.Packet;

public class Comment
{
  private static byte[] _vorbis = "vorbis".getBytes();
  private static final int OV_EFAULT = -129;
  private static final int OV_EIMPL = -130;
  public byte[][] user_comments;
  public int[] comment_lengths;
  public int comments;
  public byte[] vendor;
  
  public void init()
  {
    this.user_comments = null;
    this.comments = 0;
    this.vendor = null;
  }
  
  public void add(String paramString)
  {
    add(paramString.getBytes());
  }
  
  private void add(byte[] paramArrayOfByte)
  {
    byte[][] arrayOfByte = new byte[this.comments + 2][];
    if (this.user_comments != null) {
      System.arraycopy(this.user_comments, 0, arrayOfByte, 0, this.comments);
    }
    this.user_comments = arrayOfByte;
    int[] arrayOfInt = new int[this.comments + 2];
    if (this.comment_lengths != null) {
      System.arraycopy(this.comment_lengths, 0, arrayOfInt, 0, this.comments);
    }
    this.comment_lengths = arrayOfInt;
    byte[] arrayOfByte1 = new byte[paramArrayOfByte.length + 1];
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte1, 0, paramArrayOfByte.length);
    this.user_comments[this.comments] = arrayOfByte1;
    this.comment_lengths[this.comments] = paramArrayOfByte.length;
    this.comments += 1;
    this.user_comments[this.comments] = null;
  }
  
  public void add_tag(String paramString1, String paramString2)
  {
    if (paramString2 == null) {
      paramString2 = "";
    }
    add(paramString1 + "=" + paramString2);
  }
  
  static boolean tagcompare(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt)
  {
    for (int i = 0; i < paramInt; i++)
    {
      int j = paramArrayOfByte1[i];
      int k = paramArrayOfByte2[i];
      if ((90 >= j) && (j >= 65)) {
        j = (byte)(j - 65 + 97);
      }
      if ((90 >= k) && (k >= 65)) {
        k = (byte)(k - 65 + 97);
      }
      if (j != k) {
        return false;
      }
    }
    return true;
  }
  
  public String query(String paramString)
  {
    return query(paramString, 0);
  }
  
  public String query(String paramString, int paramInt)
  {
    int i = query(paramString.getBytes(), paramInt);
    if (i == -1) {
      return null;
    }
    byte[] arrayOfByte = this.user_comments[i];
    for (int j = 0; j < this.comment_lengths[i]; j++) {
      if (arrayOfByte[j] == 61) {
        return new String(arrayOfByte, j + 1, this.comment_lengths[i] - (j + 1));
      }
    }
    return null;
  }
  
  private int query(byte[] paramArrayOfByte, int paramInt)
  {
    int i = 0;
    int j = 0;
    int k = paramArrayOfByte.length + 1;
    byte[] arrayOfByte = new byte[k];
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, paramArrayOfByte.length);
    arrayOfByte[paramArrayOfByte.length] = 61;
    for (i = 0; i < this.comments; i++) {
      if (tagcompare(this.user_comments[i], arrayOfByte, k))
      {
        if (paramInt == j) {
          return i;
        }
        j++;
      }
    }
    return -1;
  }
  
  int unpack(Buffer paramBuffer)
  {
    int i = paramBuffer.read(32);
    if (i < 0)
    {
      clear();
      return -1;
    }
    this.vendor = new byte[i + 1];
    paramBuffer.read(this.vendor, i);
    this.comments = paramBuffer.read(32);
    if (this.comments < 0)
    {
      clear();
      return -1;
    }
    this.user_comments = new byte[this.comments + 1][];
    this.comment_lengths = new int[this.comments + 1];
    for (int j = 0; j < this.comments; j++)
    {
      int k = paramBuffer.read(32);
      if (k < 0)
      {
        clear();
        return -1;
      }
      this.comment_lengths[j] = k;
      this.user_comments[j] = new byte[k + 1];
      paramBuffer.read(this.user_comments[j], k);
    }
    if (paramBuffer.read(1) != 1)
    {
      clear();
      return -1;
    }
    return 0;
  }
  
  int pack(Buffer paramBuffer)
  {
    byte[] arrayOfByte = "Xiphophorus libVorbis I 20000508".getBytes();
    paramBuffer.write(3, 8);
    paramBuffer.write(_vorbis);
    paramBuffer.write(arrayOfByte.length, 32);
    paramBuffer.write(arrayOfByte);
    paramBuffer.write(this.comments, 32);
    if (this.comments != 0) {
      for (int i = 0; i < this.comments; i++) {
        if (this.user_comments[i] != null)
        {
          paramBuffer.write(this.comment_lengths[i], 32);
          paramBuffer.write(this.user_comments[i]);
        }
        else
        {
          paramBuffer.write(0, 32);
        }
      }
    }
    paramBuffer.write(1, 1);
    return 0;
  }
  
  public int header_out(Packet paramPacket)
  {
    Buffer localBuffer = new Buffer();
    localBuffer.writeinit();
    if (pack(localBuffer) != 0) {
      return -130;
    }
    paramPacket.packet_base = new byte[localBuffer.bytes()];
    paramPacket.packet = 0;
    paramPacket.bytes = localBuffer.bytes();
    System.arraycopy(localBuffer.buffer(), 0, paramPacket.packet_base, 0, paramPacket.bytes);
    paramPacket.b_o_s = 0;
    paramPacket.e_o_s = 0;
    paramPacket.granulepos = 0L;
    return 0;
  }
  
  void clear()
  {
    for (int i = 0; i < this.comments; i++) {
      this.user_comments[i] = null;
    }
    this.user_comments = null;
    this.vendor = null;
  }
  
  public String getVendor()
  {
    return new String(this.vendor, 0, this.vendor.length - 1);
  }
  
  public String getComment(int paramInt)
  {
    if (this.comments <= paramInt) {
      return null;
    }
    return new String(this.user_comments[paramInt], 0, this.user_comments[paramInt].length - 1);
  }
  
  public String toString()
  {
    String str = "Vendor: " + new String(this.vendor, 0, this.vendor.length - 1);
    for (int i = 0; i < this.comments; i++) {
      str = str + "\nComment: " + new String(this.user_comments[i], 0, this.user_comments[i].length - 1);
    }
    str = str + "\n";
    return str;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.Comment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */