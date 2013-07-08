/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 24:   */public final class AMDVertexShaderTessellator
/* 25:   */{
/* 26:   */  public static final int GL_SAMPLER_BUFFER_AMD = 36865;
/* 27:   */  public static final int GL_INT_SAMPLER_BUFFER_AMD = 36866;
/* 28:   */  public static final int GL_UNSIGNED_INT_SAMPLER_BUFFER_AMD = 36867;
/* 29:   */  public static final int GL_DISCRETE_AMD = 36870;
/* 30:   */  public static final int GL_CONTINUOUS_AMD = 36871;
/* 31:   */  public static final int GL_TESSELLATION_MODE_AMD = 36868;
/* 32:   */  public static final int GL_TESSELLATION_FACTOR_AMD = 36869;
/* 33:   */  
/* 34:   */  public static void glTessellationFactorAMD(float factor)
/* 35:   */  {
/* 36:36 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 37:37 */    long function_pointer = caps.glTessellationFactorAMD;
/* 38:38 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 39:39 */    nglTessellationFactorAMD(factor, function_pointer);
/* 40:   */  }
/* 41:   */  
/* 42:   */  static native void nglTessellationFactorAMD(float paramFloat, long paramLong);
/* 43:   */  
/* 44:44 */  public static void glTessellationModeAMD(int mode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 45:45 */    long function_pointer = caps.glTessellationModeAMD;
/* 46:46 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 47:47 */    nglTessellationModeAMD(mode, function_pointer);
/* 48:   */  }
/* 49:   */  
/* 50:   */  static native void nglTessellationModeAMD(int paramInt, long paramLong);
/* 51:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AMDVertexShaderTessellator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */