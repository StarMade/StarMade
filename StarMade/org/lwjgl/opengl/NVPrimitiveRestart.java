/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 20:   */public final class NVPrimitiveRestart
/* 21:   */{
/* 22:   */  public static final int GL_PRIMITIVE_RESTART_NV = 34136;
/* 23:   */  public static final int GL_PRIMITIVE_RESTART_INDEX_NV = 34137;
/* 24:   */  
/* 25:   */  public static void glPrimitiveRestartNV()
/* 26:   */  {
/* 27:27 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 28:28 */    long function_pointer = caps.glPrimitiveRestartNV;
/* 29:29 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 30:30 */    nglPrimitiveRestartNV(function_pointer);
/* 31:   */  }
/* 32:   */  
/* 33:   */  static native void nglPrimitiveRestartNV(long paramLong);
/* 34:   */  
/* 35:35 */  public static void glPrimitiveRestartIndexNV(int index) { ContextCapabilities caps = GLContext.getCapabilities();
/* 36:36 */    long function_pointer = caps.glPrimitiveRestartIndexNV;
/* 37:37 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 38:38 */    nglPrimitiveRestartIndexNV(index, function_pointer);
/* 39:   */  }
/* 40:   */  
/* 41:   */  static native void nglPrimitiveRestartIndexNV(int paramInt, long paramLong);
/* 42:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVPrimitiveRestart
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */