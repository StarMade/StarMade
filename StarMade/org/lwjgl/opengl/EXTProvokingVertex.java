package org.lwjgl.opengl;

import org.lwjgl.BufferChecks;

public final class EXTProvokingVertex
{
  public static final int GL_FIRST_VERTEX_CONVENTION_EXT = 36429;
  public static final int GL_LAST_VERTEX_CONVENTION_EXT = 36430;
  public static final int GL_PROVOKING_VERTEX_EXT = 36431;
  public static final int GL_QUADS_FOLLOW_PROVOKING_VERTEX_CONVENTION_EXT = 36428;
  
  public static void glProvokingVertexEXT(int mode)
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    long function_pointer = caps.glProvokingVertexEXT;
    BufferChecks.checkFunctionAddress(function_pointer);
    nglProvokingVertexEXT(mode, function_pointer);
  }
  
  static native void nglProvokingVertexEXT(int paramInt, long paramLong);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.EXTProvokingVertex
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */