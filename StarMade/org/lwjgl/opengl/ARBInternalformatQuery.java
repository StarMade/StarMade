package org.lwjgl.opengl;

import java.nio.IntBuffer;

public final class ARBInternalformatQuery
{
  public static final int GL_NUM_SAMPLE_COUNTS = 37760;
  
  public static void glGetInternalformat(int target, int internalformat, int pname, IntBuffer params)
  {
    GL42.glGetInternalformat(target, internalformat, pname, params);
  }
  
  public static int glGetInternalformat(int target, int internalformat, int pname)
  {
    return GL42.glGetInternalformat(target, internalformat, pname);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.ARBInternalformatQuery
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */