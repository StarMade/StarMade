/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 13:   */public final class APPLEFlushBufferRange
/* 14:   */{
/* 15:   */  public static final int GL_BUFFER_SERIALIZED_MODIFY_APPLE = 35346;
/* 16:   */  public static final int GL_BUFFER_FLUSHING_UNMAP_APPLE = 35347;
/* 17:   */  
/* 18:   */  public static void glBufferParameteriAPPLE(int target, int pname, int param)
/* 19:   */  {
/* 20:20 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 21:21 */    long function_pointer = caps.glBufferParameteriAPPLE;
/* 22:22 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 23:23 */    nglBufferParameteriAPPLE(target, pname, param, function_pointer);
/* 24:   */  }
/* 25:   */  
/* 26:   */  static native void nglBufferParameteriAPPLE(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 27:   */  
/* 28:28 */  public static void glFlushMappedBufferRangeAPPLE(int target, long offset, long size) { ContextCapabilities caps = GLContext.getCapabilities();
/* 29:29 */    long function_pointer = caps.glFlushMappedBufferRangeAPPLE;
/* 30:30 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 31:31 */    nglFlushMappedBufferRangeAPPLE(target, offset, size, function_pointer);
/* 32:   */  }
/* 33:   */  
/* 34:   */  static native void nglFlushMappedBufferRangeAPPLE(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/* 35:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.APPLEFlushBufferRange
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */