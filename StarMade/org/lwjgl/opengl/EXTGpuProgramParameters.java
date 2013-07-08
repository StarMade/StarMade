package org.lwjgl.opengl;

import java.nio.FloatBuffer;
import org.lwjgl.BufferChecks;
import org.lwjgl.MemoryUtil;

public final class EXTGpuProgramParameters
{
  public static void glProgramEnvParameters4EXT(int target, int index, int count, FloatBuffer params)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glProgramEnvParameters4fvEXT;
    BufferChecks.checkFunctionAddress(function_pointer);
    BufferChecks.checkBuffer(params, count << 2);
    nglProgramEnvParameters4fvEXT(target, index, count, MemoryUtil.getAddress(params), function_pointer);
  }
  
  static native void nglProgramEnvParameters4fvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
  
  public static void glProgramLocalParameters4EXT(int target, int index, int count, FloatBuffer params)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glProgramLocalParameters4fvEXT;
    BufferChecks.checkFunctionAddress(function_pointer);
    BufferChecks.checkBuffer(params, count << 2);
    nglProgramLocalParameters4fvEXT(target, index, count, MemoryUtil.getAddress(params), function_pointer);
  }
  
  static native void nglProgramLocalParameters4fvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.EXTGpuProgramParameters
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */