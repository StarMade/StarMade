package org.lwjgl.opengl;

import org.lwjgl.BufferChecks;

public final class NVDepthBufferFloat
{
  public static final int GL_DEPTH_COMPONENT32F_NV = 36267;
  public static final int GL_DEPTH32F_STENCIL8_NV = 36268;
  public static final int GL_FLOAT_32_UNSIGNED_INT_24_8_REV_NV = 36269;
  public static final int GL_DEPTH_BUFFER_FLOAT_MODE_NV = 36271;
  
  public static void glDepthRangedNV(double local_n, double local_f)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glDepthRangedNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglDepthRangedNV(local_n, local_f, function_pointer);
  }
  
  static native void nglDepthRangedNV(double paramDouble1, double paramDouble2, long paramLong);
  
  public static void glClearDepthdNV(double local_d)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glClearDepthdNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglClearDepthdNV(local_d, function_pointer);
  }
  
  static native void nglClearDepthdNV(double paramDouble, long paramLong);
  
  public static void glDepthBoundsdNV(double zmin, double zmax)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glDepthBoundsdNV;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglDepthBoundsdNV(zmin, zmax, function_pointer);
  }
  
  static native void nglDepthBoundsdNV(double paramDouble1, double paramDouble2, long paramLong);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.NVDepthBufferFloat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */