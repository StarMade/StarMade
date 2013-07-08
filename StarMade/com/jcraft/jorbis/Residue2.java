/*  1:   */package com.jcraft.jorbis;
/*  2:   */
/* 27:   */class Residue2
/* 28:   */  extends Residue0
/* 29:   */{
/* 30:   */  int inverse(Block vb, Object vl, float[][] in, int[] nonzero, int ch)
/* 31:   */  {
/* 32:32 */    int i = 0;
/* 33:33 */    for (i = 0; i < ch; i++)
/* 34:34 */      if (nonzero[i] != 0)
/* 35:   */        break;
/* 36:36 */    if (i == ch) {
/* 37:37 */      return 0;
/* 38:   */    }
/* 39:39 */    return _2inverse(vb, vl, in, ch);
/* 40:   */  }
/* 41:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Residue2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */