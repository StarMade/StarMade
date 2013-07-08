package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;
import com.jcraft.jogg.Packet;

public class Info
{
  private static final int OV_EBADPACKET = -136;
  private static final int OV_ENOTAUDIO = -135;
  private static byte[] _vorbis = "vorbis".getBytes();
  private static final int VI_TIMEB = 1;
  private static final int VI_FLOORB = 2;
  private static final int VI_RESB = 3;
  private static final int VI_MAPB = 1;
  private static final int VI_WINDOWB = 1;
  public int version;
  public int channels;
  public int rate;
  int bitrate_upper;
  int bitrate_nominal;
  int bitrate_lower;
  int[] blocksizes = new int[2];
  int modes;
  int maps;
  int times;
  int floors;
  int residues;
  int books;
  int psys;
  InfoMode[] mode_param = null;
  int[] map_type = null;
  Object[] map_param = null;
  int[] time_type = null;
  Object[] time_param = null;
  int[] floor_type = null;
  Object[] floor_param = null;
  int[] residue_type = null;
  Object[] residue_param = null;
  StaticCodeBook[] book_param = null;
  PsyInfo[] psy_param = new PsyInfo[64];
  int envelopesa;
  float preecho_thresh;
  float preecho_clamp;
  
  public void init()
  {
    this.rate = 0;
  }
  
  public void clear()
  {
    for (int i = 0; i < this.modes; i++) {
      this.mode_param[i] = null;
    }
    this.mode_param = null;
    for (int j = 0; j < this.maps; j++) {
      FuncMapping.mapping_P[this.map_type[j]].free_info(this.map_param[j]);
    }
    this.map_param = null;
    for (int k = 0; k < this.times; k++) {
      FuncTime.time_P[this.time_type[k]].free_info(this.time_param[k]);
    }
    this.time_param = null;
    for (int m = 0; m < this.floors; m++) {
      FuncFloor.floor_P[this.floor_type[m]].free_info(this.floor_param[m]);
    }
    this.floor_param = null;
    for (int n = 0; n < this.residues; n++) {
      FuncResidue.residue_P[this.residue_type[n]].free_info(this.residue_param[n]);
    }
    this.residue_param = null;
    for (int i1 = 0; i1 < this.books; i1++) {
      if (this.book_param[i1] != null)
      {
        this.book_param[i1].clear();
        this.book_param[i1] = null;
      }
    }
    this.book_param = null;
    for (int i2 = 0; i2 < this.psys; i2++) {
      this.psy_param[i2].free();
    }
  }
  
  int unpack_info(Buffer paramBuffer)
  {
    this.version = paramBuffer.read(32);
    if (this.version != 0) {
      return -1;
    }
    this.channels = paramBuffer.read(8);
    this.rate = paramBuffer.read(32);
    this.bitrate_upper = paramBuffer.read(32);
    this.bitrate_nominal = paramBuffer.read(32);
    this.bitrate_lower = paramBuffer.read(32);
    this.blocksizes[0] = (1 << paramBuffer.read(4));
    this.blocksizes[1] = (1 << paramBuffer.read(4));
    if ((this.rate < 1) || (this.channels < 1) || (this.blocksizes[0] < 8) || (this.blocksizes[1] < this.blocksizes[0]) || (paramBuffer.read(1) != 1))
    {
      clear();
      return -1;
    }
    return 0;
  }
  
  int unpack_books(Buffer paramBuffer)
  {
    this.books = (paramBuffer.read(8) + 1);
    if ((this.book_param == null) || (this.book_param.length != this.books)) {
      this.book_param = new StaticCodeBook[this.books];
    }
    for (int i = 0; i < this.books; i++)
    {
      this.book_param[i] = new StaticCodeBook();
      if (this.book_param[i].unpack(paramBuffer) != 0)
      {
        clear();
        return -1;
      }
    }
    this.times = (paramBuffer.read(6) + 1);
    if ((this.time_type == null) || (this.time_type.length != this.times)) {
      this.time_type = new int[this.times];
    }
    if ((this.time_param == null) || (this.time_param.length != this.times)) {
      this.time_param = new Object[this.times];
    }
    for (int j = 0; j < this.times; j++)
    {
      this.time_type[j] = paramBuffer.read(16);
      if ((this.time_type[j] < 0) || (this.time_type[j] >= 1))
      {
        clear();
        return -1;
      }
      this.time_param[j] = FuncTime.time_P[this.time_type[j]].unpack(this, paramBuffer);
      if (this.time_param[j] == null)
      {
        clear();
        return -1;
      }
    }
    this.floors = (paramBuffer.read(6) + 1);
    if ((this.floor_type == null) || (this.floor_type.length != this.floors)) {
      this.floor_type = new int[this.floors];
    }
    if ((this.floor_param == null) || (this.floor_param.length != this.floors)) {
      this.floor_param = new Object[this.floors];
    }
    for (int k = 0; k < this.floors; k++)
    {
      this.floor_type[k] = paramBuffer.read(16);
      if ((this.floor_type[k] < 0) || (this.floor_type[k] >= 2))
      {
        clear();
        return -1;
      }
      this.floor_param[k] = FuncFloor.floor_P[this.floor_type[k]].unpack(this, paramBuffer);
      if (this.floor_param[k] == null)
      {
        clear();
        return -1;
      }
    }
    this.residues = (paramBuffer.read(6) + 1);
    if ((this.residue_type == null) || (this.residue_type.length != this.residues)) {
      this.residue_type = new int[this.residues];
    }
    if ((this.residue_param == null) || (this.residue_param.length != this.residues)) {
      this.residue_param = new Object[this.residues];
    }
    for (int m = 0; m < this.residues; m++)
    {
      this.residue_type[m] = paramBuffer.read(16);
      if ((this.residue_type[m] < 0) || (this.residue_type[m] >= 3))
      {
        clear();
        return -1;
      }
      this.residue_param[m] = FuncResidue.residue_P[this.residue_type[m]].unpack(this, paramBuffer);
      if (this.residue_param[m] == null)
      {
        clear();
        return -1;
      }
    }
    this.maps = (paramBuffer.read(6) + 1);
    if ((this.map_type == null) || (this.map_type.length != this.maps)) {
      this.map_type = new int[this.maps];
    }
    if ((this.map_param == null) || (this.map_param.length != this.maps)) {
      this.map_param = new Object[this.maps];
    }
    for (int n = 0; n < this.maps; n++)
    {
      this.map_type[n] = paramBuffer.read(16);
      if ((this.map_type[n] < 0) || (this.map_type[n] >= 1))
      {
        clear();
        return -1;
      }
      this.map_param[n] = FuncMapping.mapping_P[this.map_type[n]].unpack(this, paramBuffer);
      if (this.map_param[n] == null)
      {
        clear();
        return -1;
      }
    }
    this.modes = (paramBuffer.read(6) + 1);
    if ((this.mode_param == null) || (this.mode_param.length != this.modes)) {
      this.mode_param = new InfoMode[this.modes];
    }
    for (int i1 = 0; i1 < this.modes; i1++)
    {
      this.mode_param[i1] = new InfoMode();
      this.mode_param[i1].blockflag = paramBuffer.read(1);
      this.mode_param[i1].windowtype = paramBuffer.read(16);
      this.mode_param[i1].transformtype = paramBuffer.read(16);
      this.mode_param[i1].mapping = paramBuffer.read(8);
      if ((this.mode_param[i1].windowtype >= 1) || (this.mode_param[i1].transformtype >= 1) || (this.mode_param[i1].mapping >= this.maps))
      {
        clear();
        return -1;
      }
    }
    if (paramBuffer.read(1) != 1)
    {
      clear();
      return -1;
    }
    return 0;
  }
  
  public int synthesis_headerin(Comment paramComment, Packet paramPacket)
  {
    Buffer localBuffer = new Buffer();
    if (paramPacket != null)
    {
      localBuffer.readinit(paramPacket.packet_base, paramPacket.packet, paramPacket.bytes);
      byte[] arrayOfByte = new byte[6];
      int i = localBuffer.read(8);
      localBuffer.read(arrayOfByte, 6);
      if ((arrayOfByte[0] != 118) || (arrayOfByte[1] != 111) || (arrayOfByte[2] != 114) || (arrayOfByte[3] != 98) || (arrayOfByte[4] != 105) || (arrayOfByte[5] != 115)) {
        return -1;
      }
      switch (i)
      {
      case 1: 
        if (paramPacket.b_o_s == 0) {
          return -1;
        }
        if (this.rate != 0) {
          return -1;
        }
        return unpack_info(localBuffer);
      case 3: 
        if (this.rate == 0) {
          return -1;
        }
        return paramComment.unpack(localBuffer);
      case 5: 
        if ((this.rate == 0) || (paramComment.vendor == null)) {
          return -1;
        }
        return unpack_books(localBuffer);
      }
    }
    return -1;
  }
  
  int pack_info(Buffer paramBuffer)
  {
    paramBuffer.write(1, 8);
    paramBuffer.write(_vorbis);
    paramBuffer.write(0, 32);
    paramBuffer.write(this.channels, 8);
    paramBuffer.write(this.rate, 32);
    paramBuffer.write(this.bitrate_upper, 32);
    paramBuffer.write(this.bitrate_nominal, 32);
    paramBuffer.write(this.bitrate_lower, 32);
    paramBuffer.write(ilog2(this.blocksizes[0]), 4);
    paramBuffer.write(ilog2(this.blocksizes[1]), 4);
    paramBuffer.write(1, 1);
    return 0;
  }
  
  int pack_books(Buffer paramBuffer)
  {
    paramBuffer.write(5, 8);
    paramBuffer.write(_vorbis);
    paramBuffer.write(this.books - 1, 8);
    for (int i = 0; i < this.books; i++) {
      if (this.book_param[i].pack(paramBuffer) != 0) {
        return -1;
      }
    }
    paramBuffer.write(this.times - 1, 6);
    for (int j = 0; j < this.times; j++)
    {
      paramBuffer.write(this.time_type[j], 16);
      FuncTime.time_P[this.time_type[j]].pack(this.time_param[j], paramBuffer);
    }
    paramBuffer.write(this.floors - 1, 6);
    for (int k = 0; k < this.floors; k++)
    {
      paramBuffer.write(this.floor_type[k], 16);
      FuncFloor.floor_P[this.floor_type[k]].pack(this.floor_param[k], paramBuffer);
    }
    paramBuffer.write(this.residues - 1, 6);
    for (int m = 0; m < this.residues; m++)
    {
      paramBuffer.write(this.residue_type[m], 16);
      FuncResidue.residue_P[this.residue_type[m]].pack(this.residue_param[m], paramBuffer);
    }
    paramBuffer.write(this.maps - 1, 6);
    for (int n = 0; n < this.maps; n++)
    {
      paramBuffer.write(this.map_type[n], 16);
      FuncMapping.mapping_P[this.map_type[n]].pack(this, this.map_param[n], paramBuffer);
    }
    paramBuffer.write(this.modes - 1, 6);
    for (int i1 = 0; i1 < this.modes; i1++)
    {
      paramBuffer.write(this.mode_param[i1].blockflag, 1);
      paramBuffer.write(this.mode_param[i1].windowtype, 16);
      paramBuffer.write(this.mode_param[i1].transformtype, 16);
      paramBuffer.write(this.mode_param[i1].mapping, 8);
    }
    paramBuffer.write(1, 1);
    return 0;
  }
  
  public int blocksize(Packet paramPacket)
  {
    Buffer localBuffer = new Buffer();
    localBuffer.readinit(paramPacket.packet_base, paramPacket.packet, paramPacket.bytes);
    if (localBuffer.read(1) != 0) {
      return -135;
    }
    int j = 0;
    int k = this.modes;
    while (k > 1)
    {
      j++;
      k >>>= 1;
    }
    int i = localBuffer.read(j);
    if (i == -1) {
      return -136;
    }
    return this.blocksizes[this.mode_param[i].blockflag];
  }
  
  private static int ilog2(int paramInt)
  {
    int i = 0;
    while (paramInt > 1)
    {
      i++;
      paramInt >>>= 1;
    }
    return i;
  }
  
  public String toString()
  {
    return "version:" + new Integer(this.version) + ", channels:" + new Integer(this.channels) + ", rate:" + new Integer(this.rate) + ", bitrate:" + new Integer(this.bitrate_upper) + "," + new Integer(this.bitrate_nominal) + "," + new Integer(this.bitrate_lower);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.Info
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */