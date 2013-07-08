/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/*  9:   */public final class ARBTextureStorageMultisample
/* 10:   */{
/* 11:   */  public static void glTexStorage2DMultisample(int target, int samples, int internalformat, int width, int height, boolean fixedsamplelocations)
/* 12:   */  {
/* 13:13 */    GL43.glTexStorage2DMultisample(target, samples, internalformat, width, height, fixedsamplelocations);
/* 14:   */  }
/* 15:   */  
/* 16:   */  public static void glTexStorage3DMultisample(int target, int samples, int internalformat, int width, int height, int depth, boolean fixedsamplelocations) {
/* 17:17 */    GL43.glTexStorage3DMultisample(target, samples, internalformat, width, height, depth, fixedsamplelocations);
/* 18:   */  }
/* 19:   */  
/* 20:   */  public static void glTextureStorage2DMultisampleEXT(int texture, int target, int samples, int internalformat, int width, int height, boolean fixedsamplelocations) {
/* 21:21 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 22:22 */    long function_pointer = caps.glTextureStorage2DMultisampleEXT;
/* 23:23 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 24:24 */    nglTextureStorage2DMultisampleEXT(texture, target, samples, internalformat, width, height, fixedsamplelocations, function_pointer);
/* 25:   */  }
/* 26:   */  
/* 27:   */  static native void nglTextureStorage2DMultisampleEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, long paramLong);
/* 28:   */  
/* 29:29 */  public static void glTextureStorage3DMultisampleEXT(int texture, int target, int samples, int internalformat, int width, int height, int depth, boolean fixedsamplelocations) { ContextCapabilities caps = GLContext.getCapabilities();
/* 30:30 */    long function_pointer = caps.glTextureStorage3DMultisampleEXT;
/* 31:31 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 32:32 */    nglTextureStorage3DMultisampleEXT(texture, target, samples, internalformat, width, height, depth, fixedsamplelocations, function_pointer);
/* 33:   */  }
/* 34:   */  
/* 35:   */  static native void nglTextureStorage3DMultisampleEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean, long paramLong);
/* 36:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBTextureStorageMultisample
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */