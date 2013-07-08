package org.lwjgl.opengl;

import org.lwjgl.BufferChecks;

public final class EXTCompiledVertexArray
{
  public static final int GL_ARRAY_ELEMENT_LOCK_FIRST_EXT = 33192;
  public static final int GL_ARRAY_ELEMENT_LOCK_COUNT_EXT = 33193;
  
  public static void glLockArraysEXT(int first, int count)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glLockArraysEXT;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglLockArraysEXT(first, count, function_pointer);
  }
  
  static native void nglLockArraysEXT(int paramInt1, int paramInt2, long paramLong);
  
  public static void glUnlockArraysEXT()
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glUnlockArraysEXT;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglUnlockArraysEXT(function_pointer);
  }
  
  static native void nglUnlockArraysEXT(long paramLong);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.EXTCompiledVertexArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */