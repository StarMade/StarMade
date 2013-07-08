/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 25:   */public final class EXTFramebufferMultisample
/* 26:   */{
/* 27:   */  public static final int GL_RENDERBUFFER_SAMPLES_EXT = 36011;
/* 28:   */  public static final int GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE_EXT = 36182;
/* 29:   */  public static final int GL_MAX_SAMPLES_EXT = 36183;
/* 30:   */  
/* 31:   */  public static void glRenderbufferStorageMultisampleEXT(int target, int samples, int internalformat, int width, int height)
/* 32:   */  {
/* 33:33 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 34:34 */    long function_pointer = caps.glRenderbufferStorageMultisampleEXT;
/* 35:35 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 36:36 */    nglRenderbufferStorageMultisampleEXT(target, samples, internalformat, width, height, function_pointer);
/* 37:   */  }
/* 38:   */  
/* 39:   */  static native void nglRenderbufferStorageMultisampleEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 40:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTFramebufferMultisample
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */