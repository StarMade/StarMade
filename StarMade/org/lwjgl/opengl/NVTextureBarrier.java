/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class NVTextureBarrier
/*    */ {
/*    */   public static void glTextureBarrierNV()
/*    */   {
/* 13 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 14 */     long function_pointer = caps.glTextureBarrierNV;
/* 15 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 16 */     nglTextureBarrierNV(function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglTextureBarrierNV(long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVTextureBarrier
 * JD-Core Version:    0.6.2
 */