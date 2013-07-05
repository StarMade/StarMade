/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class NVTextureMultisample
/*    */ {
/*    */   public static final int GL_TEXTURE_COVERAGE_SAMPLES_NV = 36933;
/*    */   public static final int GL_TEXTURE_COLOR_SAMPLES_NV = 36934;
/*    */ 
/*    */   public static void glTexImage2DMultisampleCoverageNV(int target, int coverageSamples, int colorSamples, int internalFormat, int width, int height, boolean fixedSampleLocations)
/*    */   {
/* 19 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 20 */     long function_pointer = caps.glTexImage2DMultisampleCoverageNV;
/* 21 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 22 */     nglTexImage2DMultisampleCoverageNV(target, coverageSamples, colorSamples, internalFormat, width, height, fixedSampleLocations, function_pointer);
/*    */   }
/*    */   static native void nglTexImage2DMultisampleCoverageNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, long paramLong);
/*    */ 
/*    */   public static void glTexImage3DMultisampleCoverageNV(int target, int coverageSamples, int colorSamples, int internalFormat, int width, int height, int depth, boolean fixedSampleLocations) {
/* 27 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 28 */     long function_pointer = caps.glTexImage3DMultisampleCoverageNV;
/* 29 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 30 */     nglTexImage3DMultisampleCoverageNV(target, coverageSamples, colorSamples, internalFormat, width, height, depth, fixedSampleLocations, function_pointer);
/*    */   }
/*    */   static native void nglTexImage3DMultisampleCoverageNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean, long paramLong);
/*    */ 
/*    */   public static void glTextureImage2DMultisampleNV(int texture, int target, int samples, int internalFormat, int width, int height, boolean fixedSampleLocations) {
/* 35 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 36 */     long function_pointer = caps.glTextureImage2DMultisampleNV;
/* 37 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 38 */     nglTextureImage2DMultisampleNV(texture, target, samples, internalFormat, width, height, fixedSampleLocations, function_pointer);
/*    */   }
/*    */   static native void nglTextureImage2DMultisampleNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, long paramLong);
/*    */ 
/*    */   public static void glTextureImage3DMultisampleNV(int texture, int target, int samples, int internalFormat, int width, int height, int depth, boolean fixedSampleLocations) {
/* 43 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 44 */     long function_pointer = caps.glTextureImage3DMultisampleNV;
/* 45 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 46 */     nglTextureImage3DMultisampleNV(texture, target, samples, internalFormat, width, height, depth, fixedSampleLocations, function_pointer);
/*    */   }
/*    */   static native void nglTextureImage3DMultisampleNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean, long paramLong);
/*    */ 
/*    */   public static void glTextureImage2DMultisampleCoverageNV(int texture, int target, int coverageSamples, int colorSamples, int internalFormat, int width, int height, boolean fixedSampleLocations) {
/* 51 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 52 */     long function_pointer = caps.glTextureImage2DMultisampleCoverageNV;
/* 53 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 54 */     nglTextureImage2DMultisampleCoverageNV(texture, target, coverageSamples, colorSamples, internalFormat, width, height, fixedSampleLocations, function_pointer);
/*    */   }
/*    */   static native void nglTextureImage2DMultisampleCoverageNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean, long paramLong);
/*    */ 
/*    */   public static void glTextureImage3DMultisampleCoverageNV(int texture, int target, int coverageSamples, int colorSamples, int internalFormat, int width, int height, int depth, boolean fixedSampleLocations) {
/* 59 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 60 */     long function_pointer = caps.glTextureImage3DMultisampleCoverageNV;
/* 61 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 62 */     nglTextureImage3DMultisampleCoverageNV(texture, target, coverageSamples, colorSamples, internalFormat, width, height, depth, fixedSampleLocations, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglTextureImage3DMultisampleCoverageNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, boolean paramBoolean, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVTextureMultisample
 * JD-Core Version:    0.6.2
 */