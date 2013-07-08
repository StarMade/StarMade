/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 16:   */public final class NVFramebufferMultisampleCoverage
/* 17:   */{
/* 18:   */  public static final int GL_RENDERBUFFER_COVERAGE_SAMPLES_NV = 36011;
/* 19:   */  public static final int GL_RENDERBUFFER_COLOR_SAMPLES_NV = 36368;
/* 20:   */  public static final int GL_MAX_MULTISAMPLE_COVERAGE_MODES_NV = 36369;
/* 21:   */  public static final int GL_MULTISAMPLE_COVERAGE_MODES_NV = 36370;
/* 22:   */  
/* 23:   */  public static void glRenderbufferStorageMultisampleCoverageNV(int target, int coverageSamples, int colorSamples, int internalformat, int width, int height)
/* 24:   */  {
/* 25:25 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 26:26 */    long function_pointer = caps.glRenderbufferStorageMultisampleCoverageNV;
/* 27:27 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 28:28 */    nglRenderbufferStorageMultisampleCoverageNV(target, coverageSamples, colorSamples, internalformat, width, height, function_pointer);
/* 29:   */  }
/* 30:   */  
/* 31:   */  static native void nglRenderbufferStorageMultisampleCoverageNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 32:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVFramebufferMultisampleCoverage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */