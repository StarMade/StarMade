package org.lwjgl.opengl;

public final class ARBCopyBuffer
{
  public static final int GL_COPY_READ_BUFFER = 36662;
  public static final int GL_COPY_WRITE_BUFFER = 36663;
  
  public static void glCopyBufferSubData(int readTarget, int writeTarget, long readOffset, long writeOffset, long size)
  {
    GL31.glCopyBufferSubData(readTarget, writeTarget, readOffset, writeOffset, size);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.ARBCopyBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */