/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 38:   */public final class ARBColorBufferFloat
/* 39:   */{
/* 40:   */  public static final int GL_RGBA_FLOAT_MODE_ARB = 34848;
/* 41:   */  public static final int GL_CLAMP_VERTEX_COLOR_ARB = 35098;
/* 42:   */  public static final int GL_CLAMP_FRAGMENT_COLOR_ARB = 35099;
/* 43:   */  public static final int GL_CLAMP_READ_COLOR_ARB = 35100;
/* 44:   */  public static final int GL_FIXED_ONLY_ARB = 35101;
/* 45:   */  public static final int WGL_TYPE_RGBA_FLOAT_ARB = 8608;
/* 46:   */  public static final int GLX_RGBA_FLOAT_TYPE = 8377;
/* 47:   */  public static final int GLX_RGBA_FLOAT_BIT = 4;
/* 48:   */  
/* 49:   */  public static void glClampColorARB(int target, int clamp)
/* 50:   */  {
/* 51:51 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 52:52 */    long function_pointer = caps.glClampColorARB;
/* 53:53 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 54:54 */    nglClampColorARB(target, clamp, function_pointer);
/* 55:   */  }
/* 56:   */  
/* 57:   */  static native void nglClampColorARB(int paramInt1, int paramInt2, long paramLong);
/* 58:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBColorBufferFloat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */