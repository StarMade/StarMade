/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 12:   */public final class NVConditionalRender
/* 13:   */{
/* 14:   */  public static final int GL_QUERY_WAIT_NV = 36371;
/* 15:   */  public static final int GL_QUERY_NO_WAIT_NV = 36372;
/* 16:   */  public static final int GL_QUERY_BY_REGION_WAIT_NV = 36373;
/* 17:   */  public static final int GL_QUERY_BY_REGION_NO_WAIT_NV = 36374;
/* 18:   */  
/* 19:   */  public static void glBeginConditionalRenderNV(int id, int mode)
/* 20:   */  {
/* 21:21 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 22:22 */    long function_pointer = caps.glBeginConditionalRenderNV;
/* 23:23 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 24:24 */    nglBeginConditionalRenderNV(id, mode, function_pointer);
/* 25:   */  }
/* 26:   */  
/* 27:   */  static native void nglBeginConditionalRenderNV(int paramInt1, int paramInt2, long paramLong);
/* 28:   */  
/* 29:29 */  public static void glEndConditionalRenderNV() { ContextCapabilities caps = GLContext.getCapabilities();
/* 30:30 */    long function_pointer = caps.glEndConditionalRenderNV;
/* 31:31 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 32:32 */    nglEndConditionalRenderNV(function_pointer);
/* 33:   */  }
/* 34:   */  
/* 35:   */  static native void nglEndConditionalRenderNV(long paramLong);
/* 36:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVConditionalRender
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */