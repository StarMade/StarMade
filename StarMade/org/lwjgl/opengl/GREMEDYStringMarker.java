package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.BufferChecks;
import org.lwjgl.MemoryUtil;

public final class GREMEDYStringMarker
{
  public static void glStringMarkerGREMEDY(ByteBuffer string)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glStringMarkerGREMEDY;
    BufferChecks.checkFunctionAddress(function_pointer);
    BufferChecks.checkDirect(string);
    nglStringMarkerGREMEDY(string.remaining(), MemoryUtil.getAddress(string), function_pointer);
  }
  
  static native void nglStringMarkerGREMEDY(int paramInt, long paramLong1, long paramLong2);
  
  public static void glStringMarkerGREMEDY(CharSequence string)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glStringMarkerGREMEDY;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglStringMarkerGREMEDY(string.length(), APIUtil.getBuffer(caps, string), function_pointer);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.GREMEDYStringMarker
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */