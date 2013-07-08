/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 21:   */public final class EXTTextureBufferObject
/* 22:   */{
/* 23:   */  public static final int GL_TEXTURE_BUFFER_EXT = 35882;
/* 24:   */  public static final int GL_MAX_TEXTURE_BUFFER_SIZE_EXT = 35883;
/* 25:   */  public static final int GL_TEXTURE_BINDING_BUFFER_EXT = 35884;
/* 26:   */  public static final int GL_TEXTURE_BUFFER_DATA_STORE_BINDING_EXT = 35885;
/* 27:   */  public static final int GL_TEXTURE_BUFFER_FORMAT_EXT = 35886;
/* 28:   */  
/* 29:   */  public static void glTexBufferEXT(int target, int internalformat, int buffer)
/* 30:   */  {
/* 31:31 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 32:32 */    long function_pointer = caps.glTexBufferEXT;
/* 33:33 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 34:34 */    nglTexBufferEXT(target, internalformat, buffer, function_pointer);
/* 35:   */  }
/* 36:   */  
/* 37:   */  static native void nglTexBufferEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 38:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTTextureBufferObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */