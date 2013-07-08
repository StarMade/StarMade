package com.jcraft.jorbis;

class InfoMapping0
{
  int submaps;
  int[] chmuxlist = new int[256];
  int[] timesubmap = new int[16];
  int[] floorsubmap = new int[16];
  int[] residuesubmap = new int[16];
  int[] psysubmap = new int[16];
  int coupling_steps;
  int[] coupling_mag = new int[256];
  int[] coupling_ang = new int[256];
  
  void free()
  {
    this.chmuxlist = null;
    this.timesubmap = null;
    this.floorsubmap = null;
    this.residuesubmap = null;
    this.psysubmap = null;
    this.coupling_mag = null;
    this.coupling_ang = null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.InfoMapping0
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */