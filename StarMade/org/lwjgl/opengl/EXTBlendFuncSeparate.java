/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/*  9:   */public final class EXTBlendFuncSeparate
/* 10:   */{
/* 11:   */  public static final int GL_BLEND_DST_RGB_EXT = 32968;
/* 12:   */  public static final int GL_BLEND_SRC_RGB_EXT = 32969;
/* 13:   */  public static final int GL_BLEND_DST_ALPHA_EXT = 32970;
/* 14:   */  public static final int GL_BLEND_SRC_ALPHA_EXT = 32971;
/* 15:   */  
/* 16:   */  public static void glBlendFuncSeparateEXT(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha)
/* 17:   */  {
/* 18:18 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 19:19 */    long function_pointer = caps.glBlendFuncSeparateEXT;
/* 20:20 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 21:21 */    nglBlendFuncSeparateEXT(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha, function_pointer);
/* 22:   */  }
/* 23:   */  
/* 24:   */  static native void nglBlendFuncSeparateEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 25:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTBlendFuncSeparate
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */