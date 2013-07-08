package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;

abstract class FuncTime
{
  public static FuncTime[] time_P = { new Time0() };
  
  abstract void pack(Object paramObject, Buffer paramBuffer);
  
  abstract Object unpack(Info paramInfo, Buffer paramBuffer);
  
  abstract Object look(DspState paramDspState, InfoMode paramInfoMode, Object paramObject);
  
  abstract void free_info(Object paramObject);
  
  abstract void free_look(Object paramObject);
  
  abstract int forward(Block paramBlock, Object paramObject);
  
  abstract int inverse(Block paramBlock, Object paramObject, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.FuncTime
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */