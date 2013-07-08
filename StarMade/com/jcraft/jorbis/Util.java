package com.jcraft.jorbis;

class Util
{
  static int ilog(int local_v)
  {
    int ret = 0;
    while (local_v != 0)
    {
      ret++;
      local_v >>>= 1;
    }
    return ret;
  }
  
  static int ilog2(int local_v)
  {
    int ret = 0;
    while (local_v > 1)
    {
      ret++;
      local_v >>>= 1;
    }
    return ret;
  }
  
  static int icount(int local_v)
  {
    int ret = 0;
    while (local_v != 0)
    {
      ret += (local_v & 0x1);
      local_v >>>= 1;
    }
    return ret;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.Util
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */