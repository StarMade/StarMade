/*  1:   */package com.jcraft.jorbis;
/*  2:   */
/*  3:   */import com.jcraft.jogg.Buffer;
/*  4:   */
/* 30:   */abstract class FuncMapping
/* 31:   */{
/* 32:32 */  public static FuncMapping[] mapping_P = { new Mapping0() };
/* 33:   */  
/* 34:   */  abstract void pack(Info paramInfo, Object paramObject, Buffer paramBuffer);
/* 35:   */  
/* 36:   */  abstract Object unpack(Info paramInfo, Buffer paramBuffer);
/* 37:   */  
/* 38:   */  abstract Object look(DspState paramDspState, InfoMode paramInfoMode, Object paramObject);
/* 39:   */  
/* 40:   */  abstract void free_info(Object paramObject);
/* 41:   */  
/* 42:   */  abstract void free_look(Object paramObject);
/* 43:   */  
/* 44:   */  abstract int inverse(Block paramBlock, Object paramObject);
/* 45:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.FuncMapping
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */