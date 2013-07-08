/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/*  9:   */public final class EXTStencilTwoSide
/* 10:   */{
/* 11:   */  public static final int GL_STENCIL_TEST_TWO_SIDE_EXT = 35088;
/* 12:   */  public static final int GL_ACTIVE_STENCIL_FACE_EXT = 35089;
/* 13:   */  
/* 14:   */  public static void glActiveStencilFaceEXT(int face)
/* 15:   */  {
/* 16:16 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 17:17 */    long function_pointer = caps.glActiveStencilFaceEXT;
/* 18:18 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 19:19 */    nglActiveStencilFaceEXT(face, function_pointer);
/* 20:   */  }
/* 21:   */  
/* 22:   */  static native void nglActiveStencilFaceEXT(int paramInt, long paramLong);
/* 23:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTStencilTwoSide
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */