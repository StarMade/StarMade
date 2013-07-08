/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  9:   */public final class ARBCopyImage
/* 10:   */{
/* 11:   */  public static void glCopyImageSubData(int srcName, int srcTarget, int srcLevel, int srcX, int srcY, int srcZ, int dstName, int dstTarget, int dstLevel, int dstX, int dstY, int dstZ, int srcWidth, int srcHeight, int srcDepth)
/* 12:   */  {
/* 13:13 */    GL43.glCopyImageSubData(srcName, srcTarget, srcLevel, srcX, srcY, srcZ, dstName, dstTarget, dstLevel, dstX, dstY, dstZ, srcWidth, srcHeight, srcDepth);
/* 14:   */  }
/* 15:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBCopyImage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */