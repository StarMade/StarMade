/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.FloatBuffer;
/*  4:   */import java.nio.IntBuffer;
/*  5:   */import org.lwjgl.BufferChecks;
/*  6:   */import org.lwjgl.MemoryUtil;
/*  7:   */
/* 19:   */public final class NVParameterBufferObject
/* 20:   */{
/* 21:   */  public static final int GL_MAX_PROGRAM_PARAMETER_BUFFER_BINDINGS_NV = 36256;
/* 22:   */  public static final int GL_MAX_PROGRAM_PARAMETER_BUFFER_SIZE_NV = 36257;
/* 23:   */  public static final int GL_VERTEX_PROGRAM_PARAMETER_BUFFER_NV = 36258;
/* 24:   */  public static final int GL_GEOMETRY_PROGRAM_PARAMETER_BUFFER_NV = 36259;
/* 25:   */  public static final int GL_FRAGMENT_PROGRAM_PARAMETER_BUFFER_NV = 36260;
/* 26:   */  
/* 27:   */  public static void glProgramBufferParametersNV(int target, int buffer, int index, FloatBuffer params)
/* 28:   */  {
/* 29:29 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 30:30 */    long function_pointer = caps.glProgramBufferParametersfvNV;
/* 31:31 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 32:32 */    BufferChecks.checkDirect(params);
/* 33:33 */    nglProgramBufferParametersfvNV(target, buffer, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/* 34:   */  }
/* 35:   */  
/* 36:   */  static native void nglProgramBufferParametersfvNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 37:   */  
/* 38:38 */  public static void glProgramBufferParametersINV(int target, int buffer, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 39:39 */    long function_pointer = caps.glProgramBufferParametersIivNV;
/* 40:40 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 41:41 */    BufferChecks.checkDirect(params);
/* 42:42 */    nglProgramBufferParametersIivNV(target, buffer, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/* 43:   */  }
/* 44:   */  
/* 45:   */  static native void nglProgramBufferParametersIivNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 46:   */  
/* 47:47 */  public static void glProgramBufferParametersIuNV(int target, int buffer, int index, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 48:48 */    long function_pointer = caps.glProgramBufferParametersIuivNV;
/* 49:49 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 50:50 */    BufferChecks.checkDirect(params);
/* 51:51 */    nglProgramBufferParametersIuivNV(target, buffer, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/* 52:   */  }
/* 53:   */  
/* 54:   */  static native void nglProgramBufferParametersIuivNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/* 55:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVParameterBufferObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */