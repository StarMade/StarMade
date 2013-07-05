/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class ARBWindowPos
/*    */ {
/*    */   public static void glWindowPos2fARB(float x, float y)
/*    */   {
/* 13 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 14 */     long function_pointer = caps.glWindowPos2fARB;
/* 15 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 16 */     nglWindowPos2fARB(x, y, function_pointer);
/*    */   }
/*    */   static native void nglWindowPos2fARB(float paramFloat1, float paramFloat2, long paramLong);
/*    */ 
/*    */   public static void glWindowPos2dARB(double x, double y) {
/* 21 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 22 */     long function_pointer = caps.glWindowPos2dARB;
/* 23 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 24 */     nglWindowPos2dARB(x, y, function_pointer);
/*    */   }
/*    */   static native void nglWindowPos2dARB(double paramDouble1, double paramDouble2, long paramLong);
/*    */ 
/*    */   public static void glWindowPos2iARB(int x, int y) {
/* 29 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 30 */     long function_pointer = caps.glWindowPos2iARB;
/* 31 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 32 */     nglWindowPos2iARB(x, y, function_pointer);
/*    */   }
/*    */   static native void nglWindowPos2iARB(int paramInt1, int paramInt2, long paramLong);
/*    */ 
/*    */   public static void glWindowPos2sARB(short x, short y) {
/* 37 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 38 */     long function_pointer = caps.glWindowPos2sARB;
/* 39 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 40 */     nglWindowPos2sARB(x, y, function_pointer);
/*    */   }
/*    */   static native void nglWindowPos2sARB(short paramShort1, short paramShort2, long paramLong);
/*    */ 
/*    */   public static void glWindowPos3fARB(float x, float y, float z) {
/* 45 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 46 */     long function_pointer = caps.glWindowPos3fARB;
/* 47 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 48 */     nglWindowPos3fARB(x, y, z, function_pointer);
/*    */   }
/*    */   static native void nglWindowPos3fARB(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*    */ 
/*    */   public static void glWindowPos3dARB(double x, double y, double z) {
/* 53 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 54 */     long function_pointer = caps.glWindowPos3dARB;
/* 55 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 56 */     nglWindowPos3dARB(x, y, z, function_pointer);
/*    */   }
/*    */   static native void nglWindowPos3dARB(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*    */ 
/*    */   public static void glWindowPos3iARB(int x, int y, int z) {
/* 61 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 62 */     long function_pointer = caps.glWindowPos3iARB;
/* 63 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 64 */     nglWindowPos3iARB(x, y, z, function_pointer);
/*    */   }
/*    */   static native void nglWindowPos3iARB(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*    */ 
/*    */   public static void glWindowPos3sARB(short x, short y, short z) {
/* 69 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 70 */     long function_pointer = caps.glWindowPos3sARB;
/* 71 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 72 */     nglWindowPos3sARB(x, y, z, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglWindowPos3sARB(short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBWindowPos
 * JD-Core Version:    0.6.2
 */