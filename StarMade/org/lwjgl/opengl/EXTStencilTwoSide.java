package org.lwjgl.opengl;

import org.lwjgl.BufferChecks;

public final class EXTStencilTwoSide
{
  public static final int GL_STENCIL_TEST_TWO_SIDE_EXT = 35088;
  public static final int GL_ACTIVE_STENCIL_FACE_EXT = 35089;
  
  public static void glActiveStencilFaceEXT(int face)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glActiveStencilFaceEXT;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglActiveStencilFaceEXT(face, function_pointer);
  }
  
  static native void nglActiveStencilFaceEXT(int paramInt, long paramLong);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.EXTStencilTwoSide
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */