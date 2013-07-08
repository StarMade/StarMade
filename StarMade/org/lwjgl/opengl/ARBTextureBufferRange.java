/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 17:   */public final class ARBTextureBufferRange
/* 18:   */{
/* 19:   */  public static final int GL_TEXTURE_BUFFER_OFFSET = 37277;
/* 20:   */  public static final int GL_TEXTURE_BUFFER_SIZE = 37278;
/* 21:   */  public static final int GL_TEXTURE_BUFFER_OFFSET_ALIGNMENT = 37279;
/* 22:   */  
/* 23:   */  public static void glTexBufferRange(int target, int internalformat, int buffer, long offset, long size)
/* 24:   */  {
/* 25:25 */    GL43.glTexBufferRange(target, internalformat, buffer, offset, size);
/* 26:   */  }
/* 27:   */  
/* 28:   */  public static void glTextureBufferRangeEXT(int texture, int target, int internalformat, int buffer, long offset, long size) {
/* 29:29 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 30:30 */    long function_pointer = caps.glTextureBufferRangeEXT;
/* 31:31 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 32:32 */    nglTextureBufferRangeEXT(texture, target, internalformat, buffer, offset, size, function_pointer);
/* 33:   */  }
/* 34:   */  
/* 35:   */  static native void nglTextureBufferRangeEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2, long paramLong3);
/* 36:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBTextureBufferRange
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */