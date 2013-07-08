package org.lwjgl.opengl;

import org.lwjgl.BufferChecks;

public final class NVDrawTexture
{
  public static void glDrawTextureNV(int texture, int sampler, float local_x0, float local_y0, float local_x1, float local_y1, float local_z, float local_s0, float local_t0, float local_s1, float local_t1)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glDrawTextureNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglDrawTextureNV(texture, sampler, local_x0, local_y0, local_x1, local_y1, local_z, local_s0, local_t0, local_s1, local_t1, function_pointer);
  }
  
  static native void nglDrawTextureNV(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, long paramLong);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.NVDrawTexture
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */