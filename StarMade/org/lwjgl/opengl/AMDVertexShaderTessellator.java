/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class AMDVertexShaderTessellator
/*    */ {
/*    */   public static final int GL_SAMPLER_BUFFER_AMD = 36865;
/*    */   public static final int GL_INT_SAMPLER_BUFFER_AMD = 36866;
/*    */   public static final int GL_UNSIGNED_INT_SAMPLER_BUFFER_AMD = 36867;
/*    */   public static final int GL_DISCRETE_AMD = 36870;
/*    */   public static final int GL_CONTINUOUS_AMD = 36871;
/*    */   public static final int GL_TESSELLATION_MODE_AMD = 36868;
/*    */   public static final int GL_TESSELLATION_FACTOR_AMD = 36869;
/*    */ 
/*    */   public static void glTessellationFactorAMD(float factor)
/*    */   {
/* 36 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 37 */     long function_pointer = caps.glTessellationFactorAMD;
/* 38 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 39 */     nglTessellationFactorAMD(factor, function_pointer);
/*    */   }
/*    */   static native void nglTessellationFactorAMD(float paramFloat, long paramLong);
/*    */ 
/*    */   public static void glTessellationModeAMD(int mode) {
/* 44 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 45 */     long function_pointer = caps.glTessellationModeAMD;
/* 46 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 47 */     nglTessellationModeAMD(mode, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglTessellationModeAMD(int paramInt, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AMDVertexShaderTessellator
 * JD-Core Version:    0.6.2
 */