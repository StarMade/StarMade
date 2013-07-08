package org.lwjgl.opengl;

import org.lwjgl.BufferChecks;

public final class ARBInstancedArrays
{
  public static final int GL_VERTEX_ATTRIB_ARRAY_DIVISOR_ARB = 35070;
  
  public static void glVertexAttribDivisorARB(int index, int divisor)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glVertexAttribDivisorARB;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglVertexAttribDivisorARB(index, divisor, function_pointer);
  }
  
  static native void nglVertexAttribDivisorARB(int paramInt1, int paramInt2, long paramLong);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.ARBInstancedArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */