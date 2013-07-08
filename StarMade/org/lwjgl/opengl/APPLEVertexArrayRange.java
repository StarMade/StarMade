/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/* 35:   */public final class APPLEVertexArrayRange
/* 36:   */{
/* 37:   */  public static final int GL_VERTEX_ARRAY_RANGE_APPLE = 34077;
/* 38:   */  public static final int GL_VERTEX_ARRAY_RANGE_LENGTH_APPLE = 34078;
/* 39:   */  public static final int GL_MAX_VERTEX_ARRAY_RANGE_ELEMENT_APPLE = 34080;
/* 40:   */  public static final int GL_VERTEX_ARRAY_RANGE_POINTER_APPLE = 34081;
/* 41:   */  public static final int GL_VERTEX_ARRAY_STORAGE_HINT_APPLE = 34079;
/* 42:   */  public static final int GL_STORAGE_CACHED_APPLE = 34238;
/* 43:   */  public static final int GL_STORAGE_SHARED_APPLE = 34239;
/* 44:   */  public static final int GL_DRAW_PIXELS_APPLE = 35338;
/* 45:   */  public static final int GL_FENCE_APPLE = 35339;
/* 46:   */  
/* 47:   */  public static void glVertexArrayRangeAPPLE(ByteBuffer pointer)
/* 48:   */  {
/* 49:49 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 50:50 */    long function_pointer = caps.glVertexArrayRangeAPPLE;
/* 51:51 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 52:52 */    BufferChecks.checkDirect(pointer);
/* 53:53 */    nglVertexArrayRangeAPPLE(pointer.remaining(), MemoryUtil.getAddress(pointer), function_pointer);
/* 54:   */  }
/* 55:   */  
/* 56:   */  static native void nglVertexArrayRangeAPPLE(int paramInt, long paramLong1, long paramLong2);
/* 57:   */  
/* 58:58 */  public static void glFlushVertexArrayRangeAPPLE(ByteBuffer pointer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 59:59 */    long function_pointer = caps.glFlushVertexArrayRangeAPPLE;
/* 60:60 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 61:61 */    BufferChecks.checkDirect(pointer);
/* 62:62 */    nglFlushVertexArrayRangeAPPLE(pointer.remaining(), MemoryUtil.getAddress(pointer), function_pointer);
/* 63:   */  }
/* 64:   */  
/* 65:   */  static native void nglFlushVertexArrayRangeAPPLE(int paramInt, long paramLong1, long paramLong2);
/* 66:   */  
/* 67:67 */  public static void glVertexArrayParameteriAPPLE(int pname, int param) { ContextCapabilities caps = GLContext.getCapabilities();
/* 68:68 */    long function_pointer = caps.glVertexArrayParameteriAPPLE;
/* 69:69 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 70:70 */    nglVertexArrayParameteriAPPLE(pname, param, function_pointer);
/* 71:   */  }
/* 72:   */  
/* 73:   */  static native void nglVertexArrayParameteriAPPLE(int paramInt1, int paramInt2, long paramLong);
/* 74:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.APPLEVertexArrayRange
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */