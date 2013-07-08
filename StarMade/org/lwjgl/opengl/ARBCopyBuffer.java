/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  8:   */public final class ARBCopyBuffer
/*  9:   */{
/* 10:   */  public static final int GL_COPY_READ_BUFFER = 36662;
/* 11:   */  
/* 15:   */  public static final int GL_COPY_WRITE_BUFFER = 36663;
/* 16:   */  
/* 21:   */  public static void glCopyBufferSubData(int readTarget, int writeTarget, long readOffset, long writeOffset, long size)
/* 22:   */  {
/* 23:23 */    GL31.glCopyBufferSubData(readTarget, writeTarget, readOffset, writeOffset, size);
/* 24:   */  }
/* 25:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBCopyBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */