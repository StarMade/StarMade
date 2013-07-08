/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 18:   */public final class EXTStencilClearTag
/* 19:   */{
/* 20:   */  public static final int GL_STENCIL_TAG_BITS_EXT = 35058;
/* 21:   */  public static final int GL_STENCIL_CLEAR_TAG_VALUE_EXT = 35059;
/* 22:   */  
/* 23:   */  public static void glStencilClearTagEXT(int stencilTagBits, int stencilClearTag)
/* 24:   */  {
/* 25:25 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 26:26 */    long function_pointer = caps.glStencilClearTagEXT;
/* 27:27 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 28:28 */    nglStencilClearTagEXT(stencilTagBits, stencilClearTag, function_pointer);
/* 29:   */  }
/* 30:   */  
/* 31:   */  static native void nglStencilClearTagEXT(int paramInt1, int paramInt2, long paramLong);
/* 32:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTStencilClearTag
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */