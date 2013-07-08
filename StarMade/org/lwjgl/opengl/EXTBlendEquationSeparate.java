/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 13:   */public final class EXTBlendEquationSeparate
/* 14:   */{
/* 15:   */  public static final int GL_BLEND_EQUATION_RGB_EXT = 32777;
/* 16:   */  public static final int GL_BLEND_EQUATION_ALPHA_EXT = 34877;
/* 17:   */  
/* 18:   */  public static void glBlendEquationSeparateEXT(int modeRGB, int modeAlpha)
/* 19:   */  {
/* 20:20 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 21:21 */    long function_pointer = caps.glBlendEquationSeparateEXT;
/* 22:22 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 23:23 */    nglBlendEquationSeparateEXT(modeRGB, modeAlpha, function_pointer);
/* 24:   */  }
/* 25:   */  
/* 26:   */  static native void nglBlendEquationSeparateEXT(int paramInt1, int paramInt2, long paramLong);
/* 27:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTBlendEquationSeparate
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */