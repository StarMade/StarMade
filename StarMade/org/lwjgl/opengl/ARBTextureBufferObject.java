/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 21:   */public final class ARBTextureBufferObject
/* 22:   */{
/* 23:   */  public static final int GL_TEXTURE_BUFFER_ARB = 35882;
/* 24:   */  public static final int GL_MAX_TEXTURE_BUFFER_SIZE_ARB = 35883;
/* 25:   */  public static final int GL_TEXTURE_BINDING_BUFFER_ARB = 35884;
/* 26:   */  public static final int GL_TEXTURE_BUFFER_DATA_STORE_BINDING_ARB = 35885;
/* 27:   */  public static final int GL_TEXTURE_BUFFER_FORMAT_ARB = 35886;
/* 28:   */  
/* 29:   */  public static void glTexBufferARB(int target, int internalformat, int buffer)
/* 30:   */  {
/* 31:31 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 32:32 */    long function_pointer = caps.glTexBufferARB;
/* 33:33 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 34:34 */    nglTexBufferARB(target, internalformat, buffer, function_pointer);
/* 35:   */  }
/* 36:   */  
/* 37:   */  static native void nglTexBufferARB(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 38:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBTextureBufferObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */