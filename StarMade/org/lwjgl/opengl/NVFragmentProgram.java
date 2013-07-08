/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.DoubleBuffer;
/*  5:   */import java.nio.FloatBuffer;
/*  6:   */import org.lwjgl.BufferChecks;
/*  7:   */import org.lwjgl.MemoryUtil;
/*  8:   */
/* 21:   */public final class NVFragmentProgram
/* 22:   */  extends NVProgram
/* 23:   */{
/* 24:   */  public static final int GL_FRAGMENT_PROGRAM_NV = 34928;
/* 25:   */  public static final int GL_MAX_TEXTURE_COORDS_NV = 34929;
/* 26:   */  public static final int GL_MAX_TEXTURE_IMAGE_UNITS_NV = 34930;
/* 27:   */  public static final int GL_FRAGMENT_PROGRAM_BINDING_NV = 34931;
/* 28:   */  public static final int GL_MAX_FRAGMENT_PROGRAM_LOCAL_PARAMETERS_NV = 34920;
/* 29:   */  
/* 30:   */  public static void glProgramNamedParameter4fNV(int id, ByteBuffer name, float x, float y, float z, float w)
/* 31:   */  {
/* 32:32 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 33:33 */    long function_pointer = caps.glProgramNamedParameter4fNV;
/* 34:34 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 35:35 */    BufferChecks.checkDirect(name);
/* 36:36 */    nglProgramNamedParameter4fNV(id, name.remaining(), MemoryUtil.getAddress(name), x, y, z, w, function_pointer);
/* 37:   */  }
/* 38:   */  
/* 39:   */  static native void nglProgramNamedParameter4fNV(int paramInt1, int paramInt2, long paramLong1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong2);
/* 40:   */  
/* 41:41 */  public static void glProgramNamedParameter4dNV(int id, ByteBuffer name, double x, double y, double z, double w) { ContextCapabilities caps = GLContext.getCapabilities();
/* 42:42 */    long function_pointer = caps.glProgramNamedParameter4dNV;
/* 43:43 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 44:44 */    BufferChecks.checkDirect(name);
/* 45:45 */    nglProgramNamedParameter4dNV(id, name.remaining(), MemoryUtil.getAddress(name), x, y, z, w, function_pointer);
/* 46:   */  }
/* 47:   */  
/* 48:   */  static native void nglProgramNamedParameter4dNV(int paramInt1, int paramInt2, long paramLong1, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong2);
/* 49:   */  
/* 50:50 */  public static void glGetProgramNamedParameterNV(int id, ByteBuffer name, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 51:51 */    long function_pointer = caps.glGetProgramNamedParameterfvNV;
/* 52:52 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 53:53 */    BufferChecks.checkDirect(name);
/* 54:54 */    BufferChecks.checkBuffer(params, 4);
/* 55:55 */    nglGetProgramNamedParameterfvNV(id, name.remaining(), MemoryUtil.getAddress(name), MemoryUtil.getAddress(params), function_pointer);
/* 56:   */  }
/* 57:   */  
/* 58:   */  static native void nglGetProgramNamedParameterfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/* 59:   */  
/* 60:60 */  public static void glGetProgramNamedParameterNV(int id, ByteBuffer name, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 61:61 */    long function_pointer = caps.glGetProgramNamedParameterdvNV;
/* 62:62 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 63:63 */    BufferChecks.checkDirect(name);
/* 64:64 */    BufferChecks.checkBuffer(params, 4);
/* 65:65 */    nglGetProgramNamedParameterdvNV(id, name.remaining(), MemoryUtil.getAddress(name), MemoryUtil.getAddress(params), function_pointer);
/* 66:   */  }
/* 67:   */  
/* 68:   */  static native void nglGetProgramNamedParameterdvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/* 69:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVFragmentProgram
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */