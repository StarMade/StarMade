/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.FloatBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/*  9:   */public final class NVRegisterCombiners2
/* 10:   */{
/* 11:   */  public static final int GL_PER_STAGE_CONSTANTS_NV = 34101;
/* 12:   */  
/* 13:   */  public static void glCombinerStageParameterNV(int stage, int pname, FloatBuffer params)
/* 14:   */  {
/* 15:15 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 16:16 */    long function_pointer = caps.glCombinerStageParameterfvNV;
/* 17:17 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 18:18 */    BufferChecks.checkBuffer(params, 4);
/* 19:19 */    nglCombinerStageParameterfvNV(stage, pname, MemoryUtil.getAddress(params), function_pointer);
/* 20:   */  }
/* 21:   */  
/* 22:   */  static native void nglCombinerStageParameterfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 23:   */  
/* 24:24 */  public static void glGetCombinerStageParameterNV(int stage, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 25:25 */    long function_pointer = caps.glGetCombinerStageParameterfvNV;
/* 26:26 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 27:27 */    BufferChecks.checkBuffer(params, 4);
/* 28:28 */    nglGetCombinerStageParameterfvNV(stage, pname, MemoryUtil.getAddress(params), function_pointer);
/* 29:   */  }
/* 30:   */  
/* 31:   */  static native void nglGetCombinerStageParameterfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 32:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVRegisterCombiners2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */