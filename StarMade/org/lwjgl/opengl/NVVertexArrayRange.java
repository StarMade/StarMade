/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteOrder;
/*  4:   */import java.nio.DoubleBuffer;
/*  5:   */import java.nio.FloatBuffer;
/*  6:   */import java.nio.IntBuffer;
/*  7:   */import org.lwjgl.LWJGLUtil;
/*  8:   */
/*  9:   */public final class NVVertexArrayRange
/* 10:   */{
/* 11:   */  public static final int GL_VERTEX_ARRAY_RANGE_NV = 34077;
/* 12:   */  public static final int GL_VERTEX_ARRAY_RANGE_LENGTH_NV = 34078;
/* 13:   */  public static final int GL_VERTEX_ARRAY_RANGE_VALID_NV = 34079;
/* 14:   */  public static final int GL_MAX_VERTEX_ARRAY_RANGE_ELEMENT_NV = 34080;
/* 15:   */  public static final int GL_VERTEX_ARRAY_RANGE_POINTER_NV = 34081;
/* 16:   */  
/* 17:   */  public static void glVertexArrayRangeNV(java.nio.ByteBuffer pPointer)
/* 18:   */  {
/* 19:19 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 20:20 */    long function_pointer = caps.glVertexArrayRangeNV;
/* 21:21 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 22:22 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 23:23 */    nglVertexArrayRangeNV(pPointer.remaining(), org.lwjgl.MemoryUtil.getAddress(pPointer), function_pointer);
/* 24:   */  }
/* 25:   */  
/* 26:26 */  public static void glVertexArrayRangeNV(DoubleBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 27:27 */    long function_pointer = caps.glVertexArrayRangeNV;
/* 28:28 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 29:29 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 30:30 */    nglVertexArrayRangeNV(pPointer.remaining() << 3, org.lwjgl.MemoryUtil.getAddress(pPointer), function_pointer);
/* 31:   */  }
/* 32:   */  
/* 33:33 */  public static void glVertexArrayRangeNV(FloatBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 34:34 */    long function_pointer = caps.glVertexArrayRangeNV;
/* 35:35 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 36:36 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 37:37 */    nglVertexArrayRangeNV(pPointer.remaining() << 2, org.lwjgl.MemoryUtil.getAddress(pPointer), function_pointer);
/* 38:   */  }
/* 39:   */  
/* 40:40 */  public static void glVertexArrayRangeNV(IntBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 41:41 */    long function_pointer = caps.glVertexArrayRangeNV;
/* 42:42 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 43:43 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 44:44 */    nglVertexArrayRangeNV(pPointer.remaining() << 2, org.lwjgl.MemoryUtil.getAddress(pPointer), function_pointer);
/* 45:   */  }
/* 46:   */  
/* 47:47 */  public static void glVertexArrayRangeNV(java.nio.ShortBuffer pPointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 48:48 */    long function_pointer = caps.glVertexArrayRangeNV;
/* 49:49 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 50:50 */    org.lwjgl.BufferChecks.checkDirect(pPointer);
/* 51:51 */    nglVertexArrayRangeNV(pPointer.remaining() << 1, org.lwjgl.MemoryUtil.getAddress(pPointer), function_pointer);
/* 52:   */  }
/* 53:   */  
/* 54:   */  static native void nglVertexArrayRangeNV(int paramInt, long paramLong1, long paramLong2);
/* 55:   */  
/* 56:56 */  public static void glFlushVertexArrayRangeNV() { ContextCapabilities caps = GLContext.getCapabilities();
/* 57:57 */    long function_pointer = caps.glFlushVertexArrayRangeNV;
/* 58:58 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 59:59 */    nglFlushVertexArrayRangeNV(function_pointer);
/* 60:   */  }
/* 61:   */  
/* 62:   */  static native void nglFlushVertexArrayRangeNV(long paramLong);
/* 63:   */  
/* 64:64 */  public static java.nio.ByteBuffer glAllocateMemoryNV(int size, float readFrequency, float writeFrequency, float priority) { ContextCapabilities caps = GLContext.getCapabilities();
/* 65:65 */    long function_pointer = caps.glAllocateMemoryNV;
/* 66:66 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 67:67 */    java.nio.ByteBuffer __result = nglAllocateMemoryNV(size, readFrequency, writeFrequency, priority, size, function_pointer);
/* 68:68 */    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/* 69:   */  }
/* 70:   */  
/* 71:   */  static native java.nio.ByteBuffer nglAllocateMemoryNV(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong1, long paramLong2);
/* 72:   */  
/* 73:73 */  public static void glFreeMemoryNV(java.nio.ByteBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 74:74 */    long function_pointer = caps.glFreeMemoryNV;
/* 75:75 */    org.lwjgl.BufferChecks.checkFunctionAddress(function_pointer);
/* 76:76 */    org.lwjgl.BufferChecks.checkDirect(pointer);
/* 77:77 */    nglFreeMemoryNV(org.lwjgl.MemoryUtil.getAddress(pointer), function_pointer);
/* 78:   */  }
/* 79:   */  
/* 80:   */  static native void nglFreeMemoryNV(long paramLong1, long paramLong2);
/* 81:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVVertexArrayRange
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */