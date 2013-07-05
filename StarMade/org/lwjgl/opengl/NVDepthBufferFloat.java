/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class NVDepthBufferFloat
/*    */ {
/*    */   public static final int GL_DEPTH_COMPONENT32F_NV = 36267;
/*    */   public static final int GL_DEPTH32F_STENCIL8_NV = 36268;
/*    */   public static final int GL_FLOAT_32_UNSIGNED_INT_24_8_REV_NV = 36269;
/*    */   public static final int GL_DEPTH_BUFFER_FLOAT_MODE_NV = 36271;
/*    */ 
/*    */   public static void glDepthRangedNV(double n, double f)
/*    */   {
/* 35 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 36 */     long function_pointer = caps.glDepthRangedNV;
/* 37 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 38 */     nglDepthRangedNV(n, f, function_pointer);
/*    */   }
/*    */   static native void nglDepthRangedNV(double paramDouble1, double paramDouble2, long paramLong);
/*    */ 
/*    */   public static void glClearDepthdNV(double d) {
/* 43 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 44 */     long function_pointer = caps.glClearDepthdNV;
/* 45 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 46 */     nglClearDepthdNV(d, function_pointer);
/*    */   }
/*    */   static native void nglClearDepthdNV(double paramDouble, long paramLong);
/*    */ 
/*    */   public static void glDepthBoundsdNV(double zmin, double zmax) {
/* 51 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 52 */     long function_pointer = caps.glDepthBoundsdNV;
/* 53 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 54 */     nglDepthBoundsdNV(zmin, zmax, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglDepthBoundsdNV(double paramDouble1, double paramDouble2, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVDepthBufferFloat
 * JD-Core Version:    0.6.2
 */