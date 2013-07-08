package org.lwjgl.opengl;

import org.lwjgl.BufferChecks;

public final class GREMEDYFrameTerminator
{
  public static void glFrameTerminatorGREMEDY()
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glFrameTerminatorGREMEDY;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglFrameTerminatorGREMEDY(function_pointer);
  }
  
  static native void nglFrameTerminatorGREMEDY(long paramLong);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.GREMEDYFrameTerminator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */