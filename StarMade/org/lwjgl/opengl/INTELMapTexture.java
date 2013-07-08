/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.ByteOrder;
/*  5:   */import java.nio.IntBuffer;
/*  6:   */import org.lwjgl.BufferChecks;
/*  7:   */import org.lwjgl.LWJGLUtil;
/*  8:   */import org.lwjgl.MemoryUtil;
/*  9:   */
/* 21:   */public final class INTELMapTexture
/* 22:   */{
/* 23:   */  public static final int GL_TEXTURE_MEMORY_LAYOUT_INTEL = 33791;
/* 24:   */  public static final int GL_LAYOUT_DEFAULT_INTEL = 0;
/* 25:   */  public static final int GL_LAYOUT_LINEAR_INTEL = 1;
/* 26:   */  public static final int GL_LAYOUT_LINEAR_CPU_CACHED_INTEL = 2;
/* 27:   */  
/* 28:   */  public static ByteBuffer glMapTexture2DINTEL(int texture, int level, long length, int access, IntBuffer stride, IntBuffer layout, ByteBuffer old_buffer)
/* 29:   */  {
/* 30:30 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 31:31 */    long function_pointer = caps.glMapTexture2DINTEL;
/* 32:32 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 33:33 */    BufferChecks.checkBuffer(stride, 1);
/* 34:34 */    BufferChecks.checkBuffer(layout, 1);
/* 35:35 */    if (old_buffer != null)
/* 36:36 */      BufferChecks.checkDirect(old_buffer);
/* 37:37 */    ByteBuffer __result = nglMapTexture2DINTEL(texture, level, length, access, MemoryUtil.getAddress(stride), MemoryUtil.getAddress(layout), old_buffer, function_pointer);
/* 38:38 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 39:   */  }
/* 40:   */  
/* 41:   */  static native ByteBuffer nglMapTexture2DINTEL(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2, long paramLong3, ByteBuffer paramByteBuffer, long paramLong4);
/* 42:   */  
/* 43:43 */  public static void glUnmapTexture2DINTEL(int texture, int level) { ContextCapabilities caps = GLContext.getCapabilities();
/* 44:44 */    long function_pointer = caps.glUnmapTexture2DINTEL;
/* 45:45 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 46:46 */    nglUnmapTexture2DINTEL(texture, level, function_pointer);
/* 47:   */  }
/* 48:   */  
/* 49:   */  static native void nglUnmapTexture2DINTEL(int paramInt1, int paramInt2, long paramLong);
/* 50:   */  
/* 51:51 */  public static void glSyncTextureINTEL(int texture) { ContextCapabilities caps = GLContext.getCapabilities();
/* 52:52 */    long function_pointer = caps.glSyncTextureINTEL;
/* 53:53 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 54:54 */    nglSyncTextureINTEL(texture, function_pointer);
/* 55:   */  }
/* 56:   */  
/* 57:   */  static native void nglSyncTextureINTEL(int paramInt, long paramLong);
/* 58:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.INTELMapTexture
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */