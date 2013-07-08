/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/*  9:   */public final class ATISeparateStencil
/* 10:   */{
/* 11:   */  public static final int GL_STENCIL_BACK_FUNC_ATI = 34816;
/* 12:   */  public static final int GL_STENCIL_BACK_FAIL_ATI = 34817;
/* 13:   */  public static final int GL_STENCIL_BACK_PASS_DEPTH_FAIL_ATI = 34818;
/* 14:   */  public static final int GL_STENCIL_BACK_PASS_DEPTH_PASS_ATI = 34819;
/* 15:   */  
/* 16:   */  public static void glStencilOpSeparateATI(int face, int sfail, int dpfail, int dppass)
/* 17:   */  {
/* 18:18 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 19:19 */    long function_pointer = caps.glStencilOpSeparateATI;
/* 20:20 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 21:21 */    nglStencilOpSeparateATI(face, sfail, dpfail, dppass, function_pointer);
/* 22:   */  }
/* 23:   */  
/* 24:   */  static native void nglStencilOpSeparateATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 25:   */  
/* 26:26 */  public static void glStencilFuncSeparateATI(int frontfunc, int backfunc, int ref, int mask) { ContextCapabilities caps = GLContext.getCapabilities();
/* 27:27 */    long function_pointer = caps.glStencilFuncSeparateATI;
/* 28:28 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 29:29 */    nglStencilFuncSeparateATI(frontfunc, backfunc, ref, mask, function_pointer);
/* 30:   */  }
/* 31:   */  
/* 32:   */  static native void nglStencilFuncSeparateATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 33:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ATISeparateStencil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */