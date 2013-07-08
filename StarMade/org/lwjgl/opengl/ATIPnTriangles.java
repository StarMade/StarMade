/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/*  9:   */public final class ATIPnTriangles
/* 10:   */{
/* 11:   */  public static final int GL_PN_TRIANGLES_ATI = 34800;
/* 12:   */  public static final int GL_MAX_PN_TRIANGLES_TESSELATION_LEVEL_ATI = 34801;
/* 13:   */  public static final int GL_PN_TRIANGLES_POINT_MODE_ATI = 34802;
/* 14:   */  public static final int GL_PN_TRIANGLES_NORMAL_MODE_ATI = 34803;
/* 15:   */  public static final int GL_PN_TRIANGLES_TESSELATION_LEVEL_ATI = 34804;
/* 16:   */  public static final int GL_PN_TRIANGLES_POINT_MODE_LINEAR_ATI = 34805;
/* 17:   */  public static final int GL_PN_TRIANGLES_POINT_MODE_CUBIC_ATI = 34806;
/* 18:   */  public static final int GL_PN_TRIANGLES_NORMAL_MODE_LINEAR_ATI = 34807;
/* 19:   */  public static final int GL_PN_TRIANGLES_NORMAL_MODE_QUADRATIC_ATI = 34808;
/* 20:   */  
/* 21:   */  public static void glPNTrianglesfATI(int pname, float param)
/* 22:   */  {
/* 23:23 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 24:24 */    long function_pointer = caps.glPNTrianglesfATI;
/* 25:25 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 26:26 */    nglPNTrianglesfATI(pname, param, function_pointer);
/* 27:   */  }
/* 28:   */  
/* 29:   */  static native void nglPNTrianglesfATI(int paramInt, float paramFloat, long paramLong);
/* 30:   */  
/* 31:31 */  public static void glPNTrianglesiATI(int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 32:32 */    long function_pointer = caps.glPNTrianglesiATI;
/* 33:33 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 34:34 */    nglPNTrianglesiATI(pname, param, function_pointer);
/* 35:   */  }
/* 36:   */  
/* 37:   */  static native void nglPNTrianglesiATI(int paramInt1, int paramInt2, long paramLong);
/* 38:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ATIPnTriangles
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */