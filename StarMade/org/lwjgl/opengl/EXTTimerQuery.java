package org.lwjgl.opengl;

import java.nio.LongBuffer;
import org.lwjgl.BufferChecks;
import org.lwjgl.MemoryUtil;

public final class EXTTimerQuery
{
  public static final int GL_TIME_ELAPSED_EXT = 35007;
  
  public static void glGetQueryObjectEXT(int local_id, int pname, LongBuffer params)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glGetQueryObjecti64vEXT;
    BufferChecks.checkFunctionAddress(function_pointer);
    BufferChecks.checkBuffer(params, 1);
    nglGetQueryObjecti64vEXT(local_id, pname, MemoryUtil.getAddress(params), function_pointer);
  }
  
  static native void nglGetQueryObjecti64vEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
  
  public static long glGetQueryObjectEXT(int local_id, int pname)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glGetQueryObjecti64vEXT;
    BufferChecks.checkFunctionAddress(function_pointer);
    LongBuffer params = APIUtil.getBufferLong(caps);
    nglGetQueryObjecti64vEXT(local_id, pname, MemoryUtil.getAddress(params), function_pointer);
    return params.get(0);
  }
  
  public static void glGetQueryObjectuEXT(int local_id, int pname, LongBuffer params)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glGetQueryObjectui64vEXT;
    BufferChecks.checkFunctionAddress(function_pointer);
    BufferChecks.checkBuffer(params, 1);
    nglGetQueryObjectui64vEXT(local_id, pname, MemoryUtil.getAddress(params), function_pointer);
  }
  
  static native void nglGetQueryObjectui64vEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
  
  public static long glGetQueryObjectuEXT(int local_id, int pname)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glGetQueryObjectui64vEXT;
    BufferChecks.checkFunctionAddress(function_pointer);
    LongBuffer params = APIUtil.getBufferLong(caps);
    nglGetQueryObjectui64vEXT(local_id, pname, MemoryUtil.getAddress(params), function_pointer);
    return params.get(0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.EXTTimerQuery
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */