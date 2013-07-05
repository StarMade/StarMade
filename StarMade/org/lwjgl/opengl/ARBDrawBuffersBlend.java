/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class ARBDrawBuffersBlend
/*    */ {
/*    */   public static void glBlendEquationiARB(int buf, int mode)
/*    */   {
/* 13 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 14 */     long function_pointer = caps.glBlendEquationiARB;
/* 15 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 16 */     nglBlendEquationiARB(buf, mode, function_pointer);
/*    */   }
/*    */   static native void nglBlendEquationiARB(int paramInt1, int paramInt2, long paramLong);
/*    */ 
/*    */   public static void glBlendEquationSeparateiARB(int buf, int modeRGB, int modeAlpha) {
/* 21 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 22 */     long function_pointer = caps.glBlendEquationSeparateiARB;
/* 23 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 24 */     nglBlendEquationSeparateiARB(buf, modeRGB, modeAlpha, function_pointer);
/*    */   }
/*    */   static native void nglBlendEquationSeparateiARB(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*    */ 
/*    */   public static void glBlendFunciARB(int buf, int src, int dst) {
/* 29 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 30 */     long function_pointer = caps.glBlendFunciARB;
/* 31 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 32 */     nglBlendFunciARB(buf, src, dst, function_pointer);
/*    */   }
/*    */   static native void nglBlendFunciARB(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*    */ 
/*    */   public static void glBlendFuncSeparateiARB(int buf, int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) {
/* 37 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 38 */     long function_pointer = caps.glBlendFuncSeparateiARB;
/* 39 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 40 */     nglBlendFuncSeparateiARB(buf, srcRGB, dstRGB, srcAlpha, dstAlpha, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglBlendFuncSeparateiARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBDrawBuffersBlend
 * JD-Core Version:    0.6.2
 */