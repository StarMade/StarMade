/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.LongBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/* 13:   */public final class EXTTimerQuery
/* 14:   */{
/* 15:   */  public static final int GL_TIME_ELAPSED_EXT = 35007;
/* 16:   */  
/* 17:   */  public static void glGetQueryObjectEXT(int id, int pname, LongBuffer params)
/* 18:   */  {
/* 19:19 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 20:20 */    long function_pointer = caps.glGetQueryObjecti64vEXT;
/* 21:21 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 22:22 */    BufferChecks.checkBuffer(params, 1);
/* 23:23 */    nglGetQueryObjecti64vEXT(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 24:   */  }
/* 25:   */  
/* 26:   */  static native void nglGetQueryObjecti64vEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 27:   */  
/* 28:   */  public static long glGetQueryObjectEXT(int id, int pname) {
/* 29:29 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 30:30 */    long function_pointer = caps.glGetQueryObjecti64vEXT;
/* 31:31 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 32:32 */    LongBuffer params = APIUtil.getBufferLong(caps);
/* 33:33 */    nglGetQueryObjecti64vEXT(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 34:34 */    return params.get(0);
/* 35:   */  }
/* 36:   */  
/* 37:   */  public static void glGetQueryObjectuEXT(int id, int pname, LongBuffer params) {
/* 38:38 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 39:39 */    long function_pointer = caps.glGetQueryObjectui64vEXT;
/* 40:40 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 41:41 */    BufferChecks.checkBuffer(params, 1);
/* 42:42 */    nglGetQueryObjectui64vEXT(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 43:   */  }
/* 44:   */  
/* 45:   */  static native void nglGetQueryObjectui64vEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 46:   */  
/* 47:   */  public static long glGetQueryObjectuEXT(int id, int pname) {
/* 48:48 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 49:49 */    long function_pointer = caps.glGetQueryObjectui64vEXT;
/* 50:50 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 51:51 */    LongBuffer params = APIUtil.getBufferLong(caps);
/* 52:52 */    nglGetQueryObjectui64vEXT(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 53:53 */    return params.get(0);
/* 54:   */  }
/* 55:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTTimerQuery
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */