/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 17:   */public final class EXTBlendColor
/* 18:   */{
/* 19:   */  public static final int GL_CONSTANT_COLOR_EXT = 32769;
/* 20:   */  public static final int GL_ONE_MINUS_CONSTANT_COLOR_EXT = 32770;
/* 21:   */  public static final int GL_CONSTANT_ALPHA_EXT = 32771;
/* 22:   */  public static final int GL_ONE_MINUS_CONSTANT_ALPHA_EXT = 32772;
/* 23:   */  public static final int GL_BLEND_COLOR_EXT = 32773;
/* 24:   */  
/* 25:   */  public static void glBlendColorEXT(float red, float green, float blue, float alpha)
/* 26:   */  {
/* 27:27 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 28:28 */    long function_pointer = caps.glBlendColorEXT;
/* 29:29 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 30:30 */    nglBlendColorEXT(red, green, blue, alpha, function_pointer);
/* 31:   */  }
/* 32:   */  
/* 33:   */  static native void nglBlendColorEXT(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/* 34:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTBlendColor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */