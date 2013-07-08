package com.jcraft.jorbis;

import java.io.PrintStream;

class Residue1
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
    for (int j = 0; j < paramInt; j++) {
      if (paramArrayOfInt[j] != 0) {
        paramArrayOfFloat[(i++)] = paramArrayOfFloat[j];
      }
    }
    if (i != 0) {
      return Residue0._01inverse(paramBlock, paramObject, paramArrayOfFloat, i, 1);
    }
    return 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.Residue1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */