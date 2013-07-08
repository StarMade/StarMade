package com.jcraft.jorbis;

class LookFloor1
{
  static final int VIF_POSIT = 63;
  int[] sorted_index = new int[65];
  int[] forward_index = new int[65];
  int[] reverse_index = new int[65];
  int[] hineighbor = new int[63];
  int[] loneighbor = new int[63];
  int posts;
  int field_1898;
  int quant_q;
  InfoFloor1 field_1899;
  int phrasebits;
  int postbits;
  int frames;
  
  void free()
  {
    this.sorted_index = null;
    this.forward_index = null;
    this.reverse_index = null;
    this.hineighbor = null;
    this.loneighbor = null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.LookFloor1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */