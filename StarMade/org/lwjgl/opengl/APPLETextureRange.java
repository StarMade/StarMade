/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.Buffer;
/*  4:   */import java.nio.ByteBuffer;
/*  5:   */import org.lwjgl.BufferChecks;
/*  6:   */import org.lwjgl.MemoryUtil;
/*  7:   */
/* 28:   */public final class APPLETextureRange
/* 29:   */{
/* 30:   */  public static final int GL_TEXTURE_STORAGE_HINT_APPLE = 34236;
/* 31:   */  public static final int GL_STORAGE_PRIVATE_APPLE = 34237;
/* 32:   */  public static final int GL_STORAGE_CACHED_APPLE = 34238;
/* 33:   */  public static final int GL_STORAGE_SHARED_APPLE = 34239;
/* 34:   */  public static final int GL_TEXTURE_RANGE_LENGTH_APPLE = 34231;
/* 35:   */  public static final int GL_TEXTURE_RANGE_POINTER_APPLE = 34232;
/* 36:   */  
/* 37:   */  public static void glTextureRangeAPPLE(int target, ByteBuffer pointer)
/* 38:   */  {
/* 39:39 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 40:40 */    long function_pointer = caps.glTextureRangeAPPLE;
/* 41:41 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 42:42 */    BufferChecks.checkDirect(pointer);
/* 43:43 */    nglTextureRangeAPPLE(target, pointer.remaining(), MemoryUtil.getAddress(pointer), function_pointer);
/* 44:   */  }
/* 45:   */  
/* 46:   */  static native void nglTextureRangeAPPLE(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 47:   */  
/* 48:48 */  public static Buffer glGetTexParameterPointervAPPLE(int target, int pname, long result_size) { ContextCapabilities caps = GLContext.getCapabilities();
/* 49:49 */    long function_pointer = caps.glGetTexParameterPointervAPPLE;
/* 50:50 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 51:51 */    Buffer __result = nglGetTexParameterPointervAPPLE(target, pname, result_size, function_pointer);
/* 52:52 */    return __result;
/* 53:   */  }
/* 54:   */  
/* 55:   */  static native Buffer nglGetTexParameterPointervAPPLE(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 56:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.APPLETextureRange
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */