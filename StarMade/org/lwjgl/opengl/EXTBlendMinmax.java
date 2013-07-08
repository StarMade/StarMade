/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 17:   */public final class EXTBlendMinmax
/* 18:   */{
/* 19:   */  public static final int GL_FUNC_ADD_EXT = 32774;
/* 20:   */  public static final int GL_MIN_EXT = 32775;
/* 21:   */  public static final int GL_MAX_EXT = 32776;
/* 22:   */  public static final int GL_BLEND_EQUATION_EXT = 32777;
/* 23:   */  
/* 24:   */  public static void glBlendEquationEXT(int mode)
/* 25:   */  {
/* 26:26 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 27:27 */    long function_pointer = caps.glBlendEquationEXT;
/* 28:28 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 29:29 */    nglBlendEquationEXT(mode, function_pointer);
/* 30:   */  }
/* 31:   */  
/* 32:   */  static native void nglBlendEquationEXT(int paramInt, long paramLong);
/* 33:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTBlendMinmax
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */