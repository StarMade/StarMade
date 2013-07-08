/*  1:   */package com.jcraft.jorbis;
/*  2:   */
/*  3:   */import com.jcraft.jogg.Buffer;
/*  4:   */
/* 31:   */abstract class FuncFloor
/* 32:   */{
/* 33:33 */  public static FuncFloor[] floor_P = { new Floor0(), new Floor1() };
/* 34:   */  
/* 35:   */  abstract void pack(Object paramObject, Buffer paramBuffer);
/* 36:   */  
/* 37:   */  abstract Object unpack(Info paramInfo, Buffer paramBuffer);
/* 38:   */  
/* 39:   */  abstract Object look(DspState paramDspState, InfoMode paramInfoMode, Object paramObject);
/* 40:   */  
/* 41:   */  abstract void free_info(Object paramObject);
/* 42:   */  
/* 43:   */  abstract void free_look(Object paramObject);
/* 44:   */  
/* 45:   */  abstract void free_state(Object paramObject);
/* 46:   */  
/* 47:   */  abstract int forward(Block paramBlock, Object paramObject1, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, Object paramObject2);
/* 48:   */  
/* 49:   */  abstract Object inverse1(Block paramBlock, Object paramObject1, Object paramObject2);
/* 50:   */  
/* 51:   */  abstract int inverse2(Block paramBlock, Object paramObject1, Object paramObject2, float[] paramArrayOfFloat);
/* 52:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.FuncFloor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */