/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 19:   */public final class EXTDepthBoundsTest
/* 20:   */{
/* 21:   */  public static final int GL_DEPTH_BOUNDS_TEST_EXT = 34960;
/* 22:   */  public static final int GL_DEPTH_BOUNDS_EXT = 34961;
/* 23:   */  
/* 24:   */  public static void glDepthBoundsEXT(double zmin, double zmax)
/* 25:   */  {
/* 26:26 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 27:27 */    long function_pointer = caps.glDepthBoundsEXT;
/* 28:28 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 29:29 */    nglDepthBoundsEXT(zmin, zmax, function_pointer);
/* 30:   */  }
/* 31:   */  
/* 32:   */  static native void nglDepthBoundsEXT(double paramDouble1, double paramDouble2, long paramLong);
/* 33:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTDepthBoundsTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */