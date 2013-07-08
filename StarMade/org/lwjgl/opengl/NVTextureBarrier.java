package org.lwjgl.opengl;

import org.lwjgl.BufferChecks;

public final class NVTextureBarrier
{
  public static void glTextureBarrierNV()
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glTextureBarrierNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglTextureBarrierNV(function_pointer);
  }
  
  static native void nglTextureBarrierNV(long paramLong);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.NVTextureBarrier
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */