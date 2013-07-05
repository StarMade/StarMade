/*    */ package com.jcraft.jorbis;
/*    */ 
/*    */ import com.jcraft.jogg.Buffer;
/*    */ 
/*    */ abstract class FuncFloor
/*    */ {
/* 33 */   public static FuncFloor[] floor_P = { new Floor0(), new Floor1() };
/*    */ 
/*    */   abstract void pack(Object paramObject, Buffer paramBuffer);
/*    */ 
/*    */   abstract Object unpack(Info paramInfo, Buffer paramBuffer);
/*    */ 
/*    */   abstract Object look(DspState paramDspState, InfoMode paramInfoMode, Object paramObject);
/*    */ 
/*    */   abstract void free_info(Object paramObject);
/*    */ 
/*    */   abstract void free_look(Object paramObject);
/*    */ 
/*    */   abstract void free_state(Object paramObject);
/*    */ 
/*    */   abstract int forward(Block paramBlock, Object paramObject1, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, Object paramObject2);
/*    */ 
/*    */   abstract Object inverse1(Block paramBlock, Object paramObject1, Object paramObject2);
/*    */ 
/*    */   abstract int inverse2(Block paramBlock, Object paramObject1, Object paramObject2, float[] paramArrayOfFloat);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.FuncFloor
 * JD-Core Version:    0.6.2
 */