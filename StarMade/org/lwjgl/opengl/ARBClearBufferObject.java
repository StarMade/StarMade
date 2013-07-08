package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.BufferChecks;
import org.lwjgl.MemoryUtil;

public final class ARBClearBufferObject
{
  public static void glClearBufferData(int target, int internalformat, int format, int type, ByteBuffer data)
  {
    GL43.glClearBufferData(target, internalformat, format, type, data);
  }
  
  public static void glClearBufferSubData(int target, int internalformat, long offset, int format, int type, ByteBuffer data)
  {
    GL43.glClearBufferSubData(target, internalformat, offset, format, type, data);
  }
  
  public static void glClearNamedBufferDataEXT(int buffer, int internalformat, int format, int type, ByteBuffer data)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glClearNamedBufferDataEXT;
    BufferChecks.checkFunctionAddress(function_pointer);
    BufferChecks.checkBuffer(data, 1);
    nglClearNamedBufferDataEXT(buffer, internalformat, format, type, MemoryUtil.getAddress(data), function_pointer);
  }
  
  static native void nglClearNamedBufferDataEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
  
  public static void glClearNamedBufferSubDataEXT(int buffer, int internalformat, long offset, int format, int type, ByteBuffer data)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glClearNamedBufferSubDataEXT;
    BufferChecks.checkFunctionAddress(function_pointer);
    BufferChecks.checkDirect(data);
    nglClearNamedBufferSubDataEXT(buffer, internalformat, offset, data.remaining(), format, type, MemoryUtil.getAddress(data), function_pointer);
  }
  
  static native void nglClearNamedBufferSubDataEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2, int paramInt3, int paramInt4, long paramLong3, long paramLong4);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.ARBClearBufferObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */