package com.jcraft.jorbis;

import java.io.PrintStream;

class Residue2
  extends Residue0
{
  int forward(Block paramBlock, Object paramObject, float[][] paramArrayOfFloat, int paramInt)
  {
    System.err.println("Residue0.forward: not implemented");
    return 0;
  }
  
  int inverse(Block paramBlock, Object paramObject, float[][] paramArrayOfFloat, int[] paramArrayOfInt, int paramInt)
  {
    int i = 0;
    for (i = 0; i < paramInt; i++) {
      if (paramArrayOfInt[i] != 0) {
        break;
      }
    }
    if (i == paramInt) {
      return 0;
    }
    return Residue0._2inverse(paramBlock, paramObject, paramArrayOfFloat, paramInt);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.Residue2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */