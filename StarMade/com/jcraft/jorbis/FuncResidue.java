package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;

abstract class FuncResidue
{
  public static FuncResidue[] residue_P = { new Residue0(), new Residue1(), new Residue2() };
  
  abstract void pack(Object paramObject, Buffer paramBuffer);
  
  abstract Object unpack(Info paramInfo, Buffer paramBuffer);
  
  abstract Object look(DspState paramDspState, InfoMode paramInfoMode, Object paramObject);
  
  abstract void free_info(Object paramObject);
  
  abstract void free_look(Object paramObject);
  
  abstract int forward(Block paramBlock, Object paramObject, float[][] paramArrayOfFloat, int paramInt);
  
  abstract int inverse(Block paramBlock, Object paramObject, float[][] paramArrayOfFloat, int[] paramArrayOfInt, int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.FuncResidue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */