package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import org.lwjgl.BufferChecks;
import org.lwjgl.MemoryUtil;

public final class NVFragmentProgram
  extends NVProgram
{
  public static final int GL_FRAGMENT_PROGRAM_NV = 34928;
  public static final int GL_MAX_TEXTURE_COORDS_NV = 34929;
  public static final int GL_MAX_TEXTURE_IMAGE_UNITS_NV = 34930;
  public static final int GL_FRAGMENT_PROGRAM_BINDING_NV = 34931;
  public static final int GL_MAX_FRAGMENT_PROGRAM_LOCAL_PARAMETERS_NV = 34920;
  
  public static void glProgramNamedParameter4fNV(int local_id, ByteBuffer name, float local_x, float local_y, float local_z, float local_w)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glProgramNamedParameter4fNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    BufferChecks.checkDirect(name);
    nglProgramNamedParameter4fNV(local_id, name.remaining(), MemoryUtil.getAddress(name), local_x, local_y, local_z, local_w, function_pointer);
  }
  
  static native void nglProgramNamedParameter4fNV(int paramInt1, int paramInt2, long paramLong1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong2);
  
  public static void glProgramNamedParameter4dNV(int local_id, ByteBuffer name, double local_x, double local_y, double local_z, double local_w)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glProgramNamedParameter4dNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    BufferChecks.checkDirect(name);
    nglProgramNamedParameter4dNV(local_id, name.remaining(), MemoryUtil.getAddress(name), local_x, local_y, local_z, local_w, function_pointer);
  }
  
  static native void nglProgramNamedParameter4dNV(int paramInt1, int paramInt2, long paramLong1, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong2);
  
  public static void glGetProgramNamedParameterNV(int local_id, ByteBuffer name, FloatBuffer params)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glGetProgramNamedParameterfvNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    BufferChecks.checkDirect(name);
    BufferChecks.checkBuffer(params, 4);
    nglGetProgramNamedParameterfvNV(local_id, name.remaining(), MemoryUtil.getAddress(name), MemoryUtil.getAddress(params), function_pointer);
  }
  
  static native void nglGetProgramNamedParameterfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
  
  public static void glGetProgramNamedParameterNV(int local_id, ByteBuffer name, DoubleBuffer params)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glGetProgramNamedParameterdvNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    BufferChecks.checkDirect(name);
    BufferChecks.checkBuffer(params, 4);
    nglGetProgramNamedParameterdvNV(local_id, name.remaining(), MemoryUtil.getAddress(name), MemoryUtil.getAddress(params), function_pointer);
  }
  
  static native void nglGetProgramNamedParameterdvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.NVFragmentProgram
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */