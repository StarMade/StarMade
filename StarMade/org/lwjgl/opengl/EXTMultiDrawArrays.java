package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.BufferChecks;
import org.lwjgl.MemoryUtil;

public final class EXTMultiDrawArrays
{
  public static void glMultiDrawArraysEXT(int mode, IntBuffer piFirst, IntBuffer piCount)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glMultiDrawArraysEXT;
    BufferChecks.checkFunctionAddress(function_pointer);
    BufferChecks.checkDirect(piFirst);
    BufferChecks.checkBuffer(piCount, piFirst.remaining());
    nglMultiDrawArraysEXT(mode, MemoryUtil.getAddress(piFirst), MemoryUtil.getAddress(piCount), piFirst.remaining(), function_pointer);
  }
  
  static native void nglMultiDrawArraysEXT(int paramInt1, long paramLong1, long paramLong2, int paramInt2, long paramLong3);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.EXTMultiDrawArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */