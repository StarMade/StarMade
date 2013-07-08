/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/*  9:   */public final class AMDDrawBuffersBlend
/* 10:   */{
/* 11:   */  public static void glBlendFuncIndexedAMD(int buf, int src, int dst)
/* 12:   */  {
/* 13:13 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 14:14 */    long function_pointer = caps.glBlendFuncIndexedAMD;
/* 15:15 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 16:16 */    nglBlendFuncIndexedAMD(buf, src, dst, function_pointer);
/* 17:   */  }
/* 18:   */  
/* 19:   */  static native void nglBlendFuncIndexedAMD(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 20:   */  
/* 21:21 */  public static void glBlendFuncSeparateIndexedAMD(int buf, int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) { ContextCapabilities caps = GLContext.getCapabilities();
/* 22:22 */    long function_pointer = caps.glBlendFuncSeparateIndexedAMD;
/* 23:23 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 24:24 */    nglBlendFuncSeparateIndexedAMD(buf, srcRGB, dstRGB, srcAlpha, dstAlpha, function_pointer);
/* 25:   */  }
/* 26:   */  
/* 27:   */  static native void nglBlendFuncSeparateIndexedAMD(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 28:   */  
/* 29:29 */  public static void glBlendEquationIndexedAMD(int buf, int mode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 30:30 */    long function_pointer = caps.glBlendEquationIndexedAMD;
/* 31:31 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 32:32 */    nglBlendEquationIndexedAMD(buf, mode, function_pointer);
/* 33:   */  }
/* 34:   */  
/* 35:   */  static native void nglBlendEquationIndexedAMD(int paramInt1, int paramInt2, long paramLong);
/* 36:   */  
/* 37:37 */  public static void glBlendEquationSeparateIndexedAMD(int buf, int modeRGB, int modeAlpha) { ContextCapabilities caps = GLContext.getCapabilities();
/* 38:38 */    long function_pointer = caps.glBlendEquationSeparateIndexedAMD;
/* 39:39 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 40:40 */    nglBlendEquationSeparateIndexedAMD(buf, modeRGB, modeAlpha, function_pointer);
/* 41:   */  }
/* 42:   */  
/* 43:   */  static native void nglBlendEquationSeparateIndexedAMD(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 44:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AMDDrawBuffersBlend
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */