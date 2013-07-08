/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/*  9:   */public final class ARBClearBufferObject
/* 10:   */{
/* 11:   */  public static void glClearBufferData(int target, int internalformat, int format, int type, ByteBuffer data)
/* 12:   */  {
/* 13:13 */    GL43.glClearBufferData(target, internalformat, format, type, data);
/* 14:   */  }
/* 15:   */  
/* 16:   */  public static void glClearBufferSubData(int target, int internalformat, long offset, int format, int type, ByteBuffer data) {
/* 17:17 */    GL43.glClearBufferSubData(target, internalformat, offset, format, type, data);
/* 18:   */  }
/* 19:   */  
/* 20:   */  public static void glClearNamedBufferDataEXT(int buffer, int internalformat, int format, int type, ByteBuffer data) {
/* 21:21 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 22:22 */    long function_pointer = caps.glClearNamedBufferDataEXT;
/* 23:23 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 24:24 */    BufferChecks.checkBuffer(data, 1);
/* 25:25 */    nglClearNamedBufferDataEXT(buffer, internalformat, format, type, MemoryUtil.getAddress(data), function_pointer);
/* 26:   */  }
/* 27:   */  
/* 28:   */  static native void nglClearNamedBufferDataEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 29:   */  
/* 30:30 */  public static void glClearNamedBufferSubDataEXT(int buffer, int internalformat, long offset, int format, int type, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 31:31 */    long function_pointer = caps.glClearNamedBufferSubDataEXT;
/* 32:32 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 33:33 */    BufferChecks.checkDirect(data);
/* 34:34 */    nglClearNamedBufferSubDataEXT(buffer, internalformat, offset, data.remaining(), format, type, MemoryUtil.getAddress(data), function_pointer);
/* 35:   */  }
/* 36:   */  
/* 37:   */  static native void nglClearNamedBufferSubDataEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2, int paramInt3, int paramInt4, long paramLong3, long paramLong4);
/* 38:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBClearBufferObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */