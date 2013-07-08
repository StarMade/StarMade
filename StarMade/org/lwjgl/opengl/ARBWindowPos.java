package org.lwjgl.opengl;

import org.lwjgl.BufferChecks;

public final class ARBWindowPos
{
  public static void glWindowPos2fARB(float local_x, float local_y)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glWindowPos2fARB;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglWindowPos2fARB(local_x, local_y, function_pointer);
  }
  
  static native void nglWindowPos2fARB(float paramFloat1, float paramFloat2, long paramLong);
  
  public static void glWindowPos2dARB(double local_x, double local_y)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glWindowPos2dARB;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglWindowPos2dARB(local_x, local_y, function_pointer);
  }
  
  static native void nglWindowPos2dARB(double paramDouble1, double paramDouble2, long paramLong);
  
  public static void glWindowPos2iARB(int local_x, int local_y)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glWindowPos2iARB;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglWindowPos2iARB(local_x, local_y, function_pointer);
  }
  
  static native void nglWindowPos2iARB(int paramInt1, int paramInt2, long paramLong);
  
  public static void glWindowPos2sARB(short local_x, short local_y)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glWindowPos2sARB;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglWindowPos2sARB(local_x, local_y, function_pointer);
  }
  
  static native void nglWindowPos2sARB(short paramShort1, short paramShort2, long paramLong);
  
  public static void glWindowPos3fARB(float local_x, float local_y, float local_z)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glWindowPos3fARB;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglWindowPos3fARB(local_x, local_y, local_z, function_pointer);
  }
  
  static native void nglWindowPos3fARB(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
  
  public static void glWindowPos3dARB(double local_x, double local_y, double local_z)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glWindowPos3dARB;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglWindowPos3dARB(local_x, local_y, local_z, function_pointer);
  }
  
  static native void nglWindowPos3dARB(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
  
  public static void glWindowPos3iARB(int local_x, int local_y, int local_z)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glWindowPos3iARB;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglWindowPos3iARB(local_x, local_y, local_z, function_pointer);
  }
  
  static native void nglWindowPos3iARB(int paramInt1, int paramInt2, int paramInt3, long paramLong);
  
  public static void glWindowPos3sARB(short local_x, short local_y, short local_z)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glWindowPos3sARB;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglWindowPos3sARB(local_x, local_y, local_z, function_pointer);
  }
  
  static native void nglWindowPos3sARB(short paramShort1, short paramShort2, short paramShort3, long paramLong);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.ARBWindowPos
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */