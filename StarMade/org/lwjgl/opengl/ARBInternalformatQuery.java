/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */
/* 12:   */public final class ARBInternalformatQuery
/* 13:   */{
/* 14:   */  public static final int GL_NUM_SAMPLE_COUNTS = 37760;
/* 15:   */  
/* 16:   */  public static void glGetInternalformat(int target, int internalformat, int pname, IntBuffer params)
/* 17:   */  {
/* 18:18 */    GL42.glGetInternalformat(target, internalformat, pname, params);
/* 19:   */  }
/* 20:   */  
/* 21:   */  public static int glGetInternalformat(int target, int internalformat, int pname)
/* 22:   */  {
/* 23:23 */    return GL42.glGetInternalformat(target, internalformat, pname);
/* 24:   */  }
/* 25:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBInternalformatQuery
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */