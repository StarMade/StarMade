/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.DoubleBuffer;
/*  5:   */import java.nio.FloatBuffer;
/*  6:   */import java.nio.IntBuffer;
/*  7:   */import java.nio.ShortBuffer;
/*  8:   */import org.lwjgl.BufferChecks;
/*  9:   */import org.lwjgl.MemoryUtil;
/* 10:   */
/* 23:   */public final class NVPixelDataRange
/* 24:   */{
/* 25:   */  public static final int GL_WRITE_PIXEL_DATA_RANGE_NV = 34936;
/* 26:   */  public static final int GL_READ_PIXEL_DATA_RANGE_NV = 34937;
/* 27:   */  public static final int GL_WRITE_PIXEL_DATA_RANGE_LENGTH_NV = 34938;
/* 28:   */  public static final int GL_READ_PIXEL_DATA_RANGE_LENGTH_NV = 34939;
/* 29:   */  public static final int GL_WRITE_PIXEL_DATA_RANGE_POINTER_NV = 34940;
/* 30:   */  public static final int GL_READ_PIXEL_DATA_RANGE_POINTER_NV = 34941;
/* 31:   */  
/* 32:   */  public static void glPixelDataRangeNV(int target, ByteBuffer data)
/* 33:   */  {
/* 34:34 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 35:35 */    long function_pointer = caps.glPixelDataRangeNV;
/* 36:36 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 37:37 */    BufferChecks.checkDirect(data);
/* 38:38 */    nglPixelDataRangeNV(target, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/* 39:   */  }
/* 40:   */  
/* 41:41 */  public static void glPixelDataRangeNV(int target, DoubleBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 42:42 */    long function_pointer = caps.glPixelDataRangeNV;
/* 43:43 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 44:44 */    BufferChecks.checkDirect(data);
/* 45:45 */    nglPixelDataRangeNV(target, data.remaining() << 3, MemoryUtil.getAddress(data), function_pointer);
/* 46:   */  }
/* 47:   */  
/* 48:48 */  public static void glPixelDataRangeNV(int target, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 49:49 */    long function_pointer = caps.glPixelDataRangeNV;
/* 50:50 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 51:51 */    BufferChecks.checkDirect(data);
/* 52:52 */    nglPixelDataRangeNV(target, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/* 53:   */  }
/* 54:   */  
/* 55:55 */  public static void glPixelDataRangeNV(int target, IntBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 56:56 */    long function_pointer = caps.glPixelDataRangeNV;
/* 57:57 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 58:58 */    BufferChecks.checkDirect(data);
/* 59:59 */    nglPixelDataRangeNV(target, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/* 60:   */  }
/* 61:   */  
/* 62:62 */  public static void glPixelDataRangeNV(int target, ShortBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 63:63 */    long function_pointer = caps.glPixelDataRangeNV;
/* 64:64 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 65:65 */    BufferChecks.checkDirect(data);
/* 66:66 */    nglPixelDataRangeNV(target, data.remaining() << 1, MemoryUtil.getAddress(data), function_pointer);
/* 67:   */  }
/* 68:   */  
/* 69:   */  static native void nglPixelDataRangeNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 70:   */  
/* 71:71 */  public static void glFlushPixelDataRangeNV(int target) { ContextCapabilities caps = GLContext.getCapabilities();
/* 72:72 */    long function_pointer = caps.glFlushPixelDataRangeNV;
/* 73:73 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 74:74 */    nglFlushPixelDataRangeNV(target, function_pointer);
/* 75:   */  }
/* 76:   */  
/* 77:   */  static native void nglFlushPixelDataRangeNV(int paramInt, long paramLong);
/* 78:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVPixelDataRange
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */