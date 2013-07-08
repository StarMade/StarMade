/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.FloatBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/*  9:   */public final class EXTGpuProgramParameters
/* 10:   */{
/* 11:   */  public static void glProgramEnvParameters4EXT(int target, int index, int count, FloatBuffer params)
/* 12:   */  {
/* 13:13 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 14:14 */    long function_pointer = caps.glProgramEnvParameters4fvEXT;
/* 15:15 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 16:16 */    BufferChecks.checkBuffer(params, count << 2);
/* 17:17 */    nglProgramEnvParameters4fvEXT(target, index, count, MemoryUtil.getAddress(params), function_pointer);
/* 18:   */  }
/* 19:   */  
/* 20:   */  static native void nglProgramEnvParameters4fvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 21:   */  
/* 22:22 */  public static void glProgramLocalParameters4EXT(int target, int index, int count, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 23:23 */    long function_pointer = caps.glProgramLocalParameters4fvEXT;
/* 24:24 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 25:25 */    BufferChecks.checkBuffer(params, count << 2);
/* 26:26 */    nglProgramLocalParameters4fvEXT(target, index, count, MemoryUtil.getAddress(params), function_pointer);
/* 27:   */  }
/* 28:   */  
/* 29:   */  static native void nglProgramLocalParameters4fvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 30:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTGpuProgramParameters
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */