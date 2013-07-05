/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class NVConditionalRender
/*    */ {
/*    */   public static final int GL_QUERY_WAIT_NV = 36371;
/*    */   public static final int GL_QUERY_NO_WAIT_NV = 36372;
/*    */   public static final int GL_QUERY_BY_REGION_WAIT_NV = 36373;
/*    */   public static final int GL_QUERY_BY_REGION_NO_WAIT_NV = 36374;
/*    */ 
/*    */   public static void glBeginConditionalRenderNV(int id, int mode)
/*    */   {
/* 21 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 22 */     long function_pointer = caps.glBeginConditionalRenderNV;
/* 23 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 24 */     nglBeginConditionalRenderNV(id, mode, function_pointer);
/*    */   }
/*    */   static native void nglBeginConditionalRenderNV(int paramInt1, int paramInt2, long paramLong);
/*    */ 
/*    */   public static void glEndConditionalRenderNV() {
/* 29 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 30 */     long function_pointer = caps.glEndConditionalRenderNV;
/* 31 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 32 */     nglEndConditionalRenderNV(function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglEndConditionalRenderNV(long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVConditionalRender
 * JD-Core Version:    0.6.2
 */