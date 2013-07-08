package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;
import com.jcraft.jogg.Packet;

public class Block
{
  float[][] pcm = new float[0][];
  Buffer opb = new Buffer();
  int field_2095;
  int field_2096;
  int field_2097;
  int pcmend;
  int mode;
  int eofflag;
  long granulepos;
  long sequence;
  DspState field_2098;
  int glue_bits;
  int time_bits;
  int floor_bits;
  int res_bits;
  
  public Block(DspState paramDspState)
  {
    this.field_2098 = paramDspState;
    if (paramDspState.analysisp != 0) {
      this.opb.writeinit();
    }
  }
  
  public void init(DspState paramDspState)
  {
    this.field_2098 = paramDspState;
  }
  
  public int clear()
  {
    if ((this.field_2098 != null) && (this.field_2098.analysisp != 0)) {
      this.opb.writeclear();
    }
    return 0;
  }
  
  public int synthesis(Packet paramPacket)
  {
    Info localInfo = this.field_2098.field_2242;
    this.opb.readinit(paramPacket.packet_base, paramPacket.packet, paramPacket.bytes);
    if (this.opb.read(1) != 0) {
      return -1;
    }
    int i = this.opb.read(this.field_2098.modebits);
    if (i == -1) {
      return -1;
    }
    this.mode = i;
    this.field_2096 = localInfo.mode_param[this.mode].blockflag;
    if (this.field_2096 != 0)
    {
      this.field_2095 = this.opb.read(1);
      this.field_2097 = this.opb.read(1);
      if (this.field_2097 == -1) {
        return -1;
      }
    }
    else
    {
      this.field_2095 = 0;
      this.field_2097 = 0;
    }
    this.granulepos = paramPacket.granulepos;
    this.sequence = (paramPacket.packetno - 3L);
    this.eofflag = paramPacket.e_o_s;
    this.pcmend = localInfo.blocksizes[this.field_2096];
    if (this.pcm.length < localInfo.channels) {
      this.pcm = new float[localInfo.channels][];
    }
    for (int j = 0; j < localInfo.channels; j++) {
      if ((this.pcm[j] == null) || (this.pcm[j].length < this.pcmend)) {
        this.pcm[j] = new float[this.pcmend];
      } else {
        for (k = 0; k < this.pcmend; k++) {
          this.pcm[j][k] = 0.0F;
        }
      }
    }
    int k = localInfo.map_type[localInfo.mode_param[this.mode].mapping];
    return FuncMapping.mapping_P[k].inverse(this, this.field_2098.mode[this.mode]);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.Block
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */