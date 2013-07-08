package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.lwjgl.BufferChecks;
import org.lwjgl.LWJGLUtil;

public final class ATIMapObjectBuffer
{
  public static ByteBuffer glMapObjectBufferATI(int buffer, ByteBuffer old_buffer)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glMapObjectBufferATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    if (old_buffer != null) {
      BufferChecks.checkDirect(old_buffer);
    }
    ByteBuffer __result = nglMapObjectBufferATI(buffer, GLChecks.getBufferObjectSizeATI(caps, buffer), old_buffer, function_pointer);
    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
  }
  
  public static ByteBuffer glMapObjectBufferATI(int buffer, long length, ByteBuffer old_buffer)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glMapObjectBufferATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    if (old_buffer != null) {
      BufferChecks.checkDirect(old_buffer);
    }
    ByteBuffer __result = nglMapObjectBufferATI(buffer, length, old_buffer, function_pointer);
    return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
  }
  
  static native ByteBuffer nglMapObjectBufferATI(int paramInt, long paramLong1, ByteBuffer paramByteBuffer, long paramLong2);
  
  public static void glUnmapObjectBufferATI(int buffer)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glUnmapObjectBufferATI;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglUnmapObjectBufferATI(buffer, function_pointer);
  }
  
  static native void nglUnmapObjectBufferATI(int paramInt, long paramLong);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.ATIMapObjectBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */