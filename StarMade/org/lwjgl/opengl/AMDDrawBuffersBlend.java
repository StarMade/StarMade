/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class AMDDrawBuffersBlend
/*    */ {
/*    */   public static void glBlendFuncIndexedAMD(int buf, int src, int dst)
/*    */   {
/* 13 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 14 */     long function_pointer = caps.glBlendFuncIndexedAMD;
/* 15 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 16 */     nglBlendFuncIndexedAMD(buf, src, dst, function_pointer);
/*    */   }
/*    */   static native void nglBlendFuncIndexedAMD(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*    */ 
/*    */   public static void glBlendFuncSeparateIndexedAMD(int buf, int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) {
/* 21 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 22 */     long function_pointer = caps.glBlendFuncSeparateIndexedAMD;
/* 23 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 24 */     nglBlendFuncSeparateIndexedAMD(buf, srcRGB, dstRGB, srcAlpha, dstAlpha, function_pointer);
/*    */   }
/*    */   static native void nglBlendFuncSeparateIndexedAMD(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*    */ 
/*    */   public static void glBlendEquationIndexedAMD(int buf, int mode) {
/* 29 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 30 */     long function_pointer = caps.glBlendEquationIndexedAMD;
/* 31 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 32 */     nglBlendEquationIndexedAMD(buf, mode, function_pointer);
/*    */   }
/*    */   static native void nglBlendEquationIndexedAMD(int paramInt1, int paramInt2, long paramLong);
/*    */ 
/*    */   public static void glBlendEquationSeparateIndexedAMD(int buf, int modeRGB, int modeAlpha) {
/* 37 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 38 */     long function_pointer = caps.glBlendEquationSeparateIndexedAMD;
/* 39 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 40 */     nglBlendEquationSeparateIndexedAMD(buf, modeRGB, modeAlpha, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglBlendEquationSeparateIndexedAMD(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AMDDrawBuffersBlend
 * JD-Core Version:    0.6.2
 */