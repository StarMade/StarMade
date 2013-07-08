/*  1:   */package com.jcraft.jorbis;
/*  2:   */
/*  3:   */import com.jcraft.jogg.Buffer;
/*  4:   */
/* 30:   */abstract class FuncResidue
/* 31:   */{
/* 32:32 */  public static FuncResidue[] residue_P = { new Residue0(), new Residue1(), new Residue2() };
/* 33:   */  
/* 34:   */  abstract void pack(Object paramObject, Buffer paramBuffer);
/* 35:   */  
/* 36:   */  abstract Object unpack(Info paramInfo, Buffer paramBuffer);
/* 37:   */  
/* 38:   */  abstract Object look(DspState paramDspState, InfoMode paramInfoMode, Object paramObject);
/* 39:   */  
/* 40:   */  abstract void free_info(Object paramObject);
/* 41:   */  
/* 42:   */  abstract void free_look(Object paramObject);
/* 43:   */  
/* 44:   */  abstract int inverse(Block paramBlock, Object paramObject, float[][] paramArrayOfFloat, int[] paramArrayOfInt, int paramInt);
/* 45:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.FuncResidue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */