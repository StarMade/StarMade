/*  1:   */package com.jcraft.jorbis;
/*  2:   */
/* 27:   */class Residue1
/* 28:   */  extends Residue0
/* 29:   */{
/* 30:   */  int inverse(Block vb, Object vl, float[][] in, int[] nonzero, int ch)
/* 31:   */  {
/* 32:32 */    int used = 0;
/* 33:33 */    for (int i = 0; i < ch; i++) {
/* 34:34 */      if (nonzero[i] != 0) {
/* 35:35 */        in[(used++)] = in[i];
/* 36:   */      }
/* 37:   */    }
/* 38:38 */    if (used != 0) {
/* 39:39 */      return _01inverse(vb, vl, in, used, 1);
/* 40:   */    }
/* 41:   */    
/* 42:42 */    return 0;
/* 43:   */  }
/* 44:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Residue1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */