/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 12:   */public final class ARBTextureStorage
/* 13:   */{
/* 14:   */  public static final int GL_TEXTURE_IMMUTABLE_FORMAT = 37167;
/* 15:   */  
/* 16:   */  public static void glTexStorage1D(int target, int levels, int internalformat, int width)
/* 17:   */  {
/* 18:18 */    GL42.glTexStorage1D(target, levels, internalformat, width);
/* 19:   */  }
/* 20:   */  
/* 21:   */  public static void glTexStorage2D(int target, int levels, int internalformat, int width, int height) {
/* 22:22 */    GL42.glTexStorage2D(target, levels, internalformat, width, height);
/* 23:   */  }
/* 24:   */  
/* 25:   */  public static void glTexStorage3D(int target, int levels, int internalformat, int width, int height, int depth) {
/* 26:26 */    GL42.glTexStorage3D(target, levels, internalformat, width, height, depth);
/* 27:   */  }
/* 28:   */  
/* 29:   */  public static void glTextureStorage1DEXT(int texture, int target, int levels, int internalformat, int width) {
/* 30:30 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 31:31 */    long function_pointer = caps.glTextureStorage1DEXT;
/* 32:32 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 33:33 */    nglTextureStorage1DEXT(texture, target, levels, internalformat, width, function_pointer);
/* 34:   */  }
/* 35:   */  
/* 36:   */  static native void nglTextureStorage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 37:   */  
/* 38:38 */  public static void glTextureStorage2DEXT(int texture, int target, int levels, int internalformat, int width, int height) { ContextCapabilities caps = GLContext.getCapabilities();
/* 39:39 */    long function_pointer = caps.glTextureStorage2DEXT;
/* 40:40 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 41:41 */    nglTextureStorage2DEXT(texture, target, levels, internalformat, width, height, function_pointer);
/* 42:   */  }
/* 43:   */  
/* 44:   */  static native void nglTextureStorage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 45:   */  
/* 46:46 */  public static void glTextureStorage3DEXT(int texture, int target, int levels, int internalformat, int width, int height, int depth) { ContextCapabilities caps = GLContext.getCapabilities();
/* 47:47 */    long function_pointer = caps.glTextureStorage3DEXT;
/* 48:48 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 49:49 */    nglTextureStorage3DEXT(texture, target, levels, internalformat, width, height, depth, function_pointer);
/* 50:   */  }
/* 51:   */  
/* 52:   */  static native void nglTextureStorage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong);
/* 53:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBTextureStorage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */