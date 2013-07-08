/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 30:   */public final class AMDSparseTexture
/* 31:   */{
/* 32:   */  public static final int GL_TEXTURE_STORAGE_SPARSE_BIT_AMD = 1;
/* 33:   */  public static final int GL_VIRTUAL_PAGE_SIZE_X_AMD = 37269;
/* 34:   */  public static final int GL_VIRTUAL_PAGE_SIZE_Y_AMD = 37270;
/* 35:   */  public static final int GL_VIRTUAL_PAGE_SIZE_Z_AMD = 37271;
/* 36:   */  public static final int GL_MAX_SPARSE_TEXTURE_SIZE_AMD = 37272;
/* 37:   */  public static final int GL_MAX_SPARSE_3D_TEXTURE_SIZE_AMD = 37273;
/* 38:   */  public static final int GL_MAX_SPARSE_ARRAY_TEXTURE_LAYERS = 37274;
/* 39:   */  public static final int GL_MIN_SPARSE_LEVEL_AMD = 37275;
/* 40:   */  public static final int GL_MIN_LOD_WARNING_AMD = 37276;
/* 41:   */  
/* 42:   */  public static void glTexStorageSparseAMD(int target, int internalFormat, int width, int height, int depth, int layers, int flags)
/* 43:   */  {
/* 44:44 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 45:45 */    long function_pointer = caps.glTexStorageSparseAMD;
/* 46:46 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 47:47 */    nglTexStorageSparseAMD(target, internalFormat, width, height, depth, layers, flags, function_pointer);
/* 48:   */  }
/* 49:   */  
/* 50:   */  static native void nglTexStorageSparseAMD(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong);
/* 51:   */  
/* 52:52 */  public static void glTextureStorageSparseAMD(int texture, int target, int internalFormat, int width, int height, int depth, int layers, int flags) { ContextCapabilities caps = GLContext.getCapabilities();
/* 53:53 */    long function_pointer = caps.glTextureStorageSparseAMD;
/* 54:54 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 55:55 */    nglTextureStorageSparseAMD(texture, target, internalFormat, width, height, depth, layers, flags, function_pointer);
/* 56:   */  }
/* 57:   */  
/* 58:   */  static native void nglTextureStorageSparseAMD(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong);
/* 59:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AMDSparseTexture
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */