/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 36:   */public final class EXTFramebufferBlit
/* 37:   */{
/* 38:   */  public static final int GL_READ_FRAMEBUFFER_EXT = 36008;
/* 39:   */  public static final int GL_DRAW_FRAMEBUFFER_EXT = 36009;
/* 40:   */  public static final int GL_DRAW_FRAMEBUFFER_BINDING_EXT = 36006;
/* 41:   */  public static final int GL_READ_FRAMEBUFFER_BINDING_EXT = 36010;
/* 42:   */  
/* 43:   */  public static void glBlitFramebufferEXT(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, int mask, int filter)
/* 44:   */  {
/* 45:45 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 46:46 */    long function_pointer = caps.glBlitFramebufferEXT;
/* 47:47 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 48:48 */    nglBlitFramebufferEXT(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, mask, filter, function_pointer);
/* 49:   */  }
/* 50:   */  
/* 51:   */  static native void nglBlitFramebufferEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong);
/* 52:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTFramebufferBlit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */