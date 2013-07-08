/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 26:   */public final class NVDepthBufferFloat
/* 27:   */{
/* 28:   */  public static final int GL_DEPTH_COMPONENT32F_NV = 36267;
/* 29:   */  public static final int GL_DEPTH32F_STENCIL8_NV = 36268;
/* 30:   */  public static final int GL_FLOAT_32_UNSIGNED_INT_24_8_REV_NV = 36269;
/* 31:   */  public static final int GL_DEPTH_BUFFER_FLOAT_MODE_NV = 36271;
/* 32:   */  
/* 33:   */  public static void glDepthRangedNV(double n, double f)
/* 34:   */  {
/* 35:35 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 36:36 */    long function_pointer = caps.glDepthRangedNV;
/* 37:37 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 38:38 */    nglDepthRangedNV(n, f, function_pointer);
/* 39:   */  }
/* 40:   */  
/* 41:   */  static native void nglDepthRangedNV(double paramDouble1, double paramDouble2, long paramLong);
/* 42:   */  
/* 43:43 */  public static void glClearDepthdNV(double d) { ContextCapabilities caps = GLContext.getCapabilities();
/* 44:44 */    long function_pointer = caps.glClearDepthdNV;
/* 45:45 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 46:46 */    nglClearDepthdNV(d, function_pointer);
/* 47:   */  }
/* 48:   */  
/* 49:   */  static native void nglClearDepthdNV(double paramDouble, long paramLong);
/* 50:   */  
/* 51:51 */  public static void glDepthBoundsdNV(double zmin, double zmax) { ContextCapabilities caps = GLContext.getCapabilities();
/* 52:52 */    long function_pointer = caps.glDepthBoundsdNV;
/* 53:53 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 54:54 */    nglDepthBoundsdNV(zmin, zmax, function_pointer);
/* 55:   */  }
/* 56:   */  
/* 57:   */  static native void nglDepthBoundsdNV(double paramDouble1, double paramDouble2, long paramLong);
/* 58:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVDepthBufferFloat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */