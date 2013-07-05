/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class NVPrimitiveRestart
/*    */ {
/*    */   public static final int GL_PRIMITIVE_RESTART_NV = 34136;
/*    */   public static final int GL_PRIMITIVE_RESTART_INDEX_NV = 34137;
/*    */ 
/*    */   public static void glPrimitiveRestartNV()
/*    */   {
/* 27 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 28 */     long function_pointer = caps.glPrimitiveRestartNV;
/* 29 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 30 */     nglPrimitiveRestartNV(function_pointer);
/*    */   }
/*    */   static native void nglPrimitiveRestartNV(long paramLong);
/*    */ 
/*    */   public static void glPrimitiveRestartIndexNV(int index) {
/* 35 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 36 */     long function_pointer = caps.glPrimitiveRestartIndexNV;
/* 37 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 38 */     nglPrimitiveRestartIndexNV(index, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglPrimitiveRestartIndexNV(int paramInt, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVPrimitiveRestart
 * JD-Core Version:    0.6.2
 */