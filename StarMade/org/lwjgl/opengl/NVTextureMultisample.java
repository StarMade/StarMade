/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 12:   */public final class NVTextureMultisample
/* 13:   */{
/* 14:   */  public static final int GL_TEXTURE_COVERAGE_SAMPLES_NV = 36933;
/* 15:   */  public static final int GL_TEXTURE_COLOR_SAMPLES_NV = 36934;
/* 16:   */  
/* 17:   */  public static void glTexImage2DMultisampleCoverageNV(int target, int coverageSamples, int colorSamples, int internalFormat, int width, int height, boolean fixedSampleLocations)
/* 18:   */  {
/* 19:19 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 20:20 */    long function_pointer = caps.glTexImage2DMultisampleCoverageNV;
/* 21:21 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 22:22 */    nglTexImage2DMultisampleCoverageNV(target, coverageSamples, colorSamples, internalFormat, width, height, fixedSampleLocations, function_pointer);
/* 23:   */  }
/* 24:   */  
/* 25:   */  static native void nglTexImage2DMultisampleCoverageNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, long paramLong);
/* 26:   */  
/* 27:27 */  public static void glTexImage3DMultisampleCoverageNV(int target, int coverageSamples, int colorSamples, int internalFormat, int width, int height, int depth, boolean fixedSampleLocations) { ContextCapabilities caps = GLContext.getCapabilities();
/* 28:28 */    long function_pointer = caps.glTexImage3DMultisampleCoverageNV;
/* 29:29 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 30:30 */    nglTexImage3DMultisampleCoverageNV(target, coverageSamples, colorSamples, internalFormat, width, height, depth, fixedSampleLocations, function_pointer);
/* 31:   */  }
/* 32:   */  
/* 33:   */  static native void nglTexImage3DMultisampleCoverageNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean, long paramLong);
/* 34:   */  
/* 35:35 */  public static void glTextureImage2DMultisampleNV(int texture, int target, int samples, int internalFormat, int width, int height, boolean fixedSampleLocations) { ContextCapabilities caps = GLContext.getCapabilities();
/* 36:36 */    long function_pointer = caps.glTextureImage2DMultisampleNV;
/* 37:37 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 38:38 */    nglTextureImage2DMultisampleNV(texture, target, samples, internalFormat, width, height, fixedSampleLocations, function_pointer);
/* 39:   */  }
/* 40:   */  
/* 41:   */  static native void nglTextureImage2DMultisampleNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, long paramLong);
/* 42:   */  
/* 43:43 */  public static void glTextureImage3DMultisampleNV(int texture, int target, int samples, int internalFormat, int width, int height, int depth, boolean fixedSampleLocations) { ContextCapabilities caps = GLContext.getCapabilities();
/* 44:44 */    long function_pointer = caps.glTextureImage3DMultisampleNV;
/* 45:45 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 46:46 */    nglTextureImage3DMultisampleNV(texture, target, samples, internalFormat, width, height, depth, fixedSampleLocations, function_pointer);
/* 47:   */  }
/* 48:   */  
/* 49:   */  static native void nglTextureImage3DMultisampleNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean, long paramLong);
/* 50:   */  
/* 51:51 */  public static void glTextureImage2DMultisampleCoverageNV(int texture, int target, int coverageSamples, int colorSamples, int internalFormat, int width, int height, boolean fixedSampleLocations) { ContextCapabilities caps = GLContext.getCapabilities();
/* 52:52 */    long function_pointer = caps.glTextureImage2DMultisampleCoverageNV;
/* 53:53 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 54:54 */    nglTextureImage2DMultisampleCoverageNV(texture, target, coverageSamples, colorSamples, internalFormat, width, height, fixedSampleLocations, function_pointer);
/* 55:   */  }
/* 56:   */  
/* 57:   */  static native void nglTextureImage2DMultisampleCoverageNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean, long paramLong);
/* 58:   */  
/* 59:59 */  public static void glTextureImage3DMultisampleCoverageNV(int texture, int target, int coverageSamples, int colorSamples, int internalFormat, int width, int height, int depth, boolean fixedSampleLocations) { ContextCapabilities caps = GLContext.getCapabilities();
/* 60:60 */    long function_pointer = caps.glTextureImage3DMultisampleCoverageNV;
/* 61:61 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 62:62 */    nglTextureImage3DMultisampleCoverageNV(texture, target, coverageSamples, colorSamples, internalFormat, width, height, depth, fixedSampleLocations, function_pointer);
/* 63:   */  }
/* 64:   */  
/* 65:   */  static native void nglTextureImage3DMultisampleCoverageNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, boolean paramBoolean, long paramLong);
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVTextureMultisample
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */