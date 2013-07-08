/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.LongBuffer;
/*  4:   */
/* 19:   */public final class ARBTimerQuery
/* 20:   */{
/* 21:   */  public static final int GL_TIME_ELAPSED = 35007;
/* 22:   */  public static final int GL_TIMESTAMP = 36392;
/* 23:   */  
/* 24:   */  public static void glQueryCounter(int id, int target)
/* 25:   */  {
/* 26:26 */    GL33.glQueryCounter(id, target);
/* 27:   */  }
/* 28:   */  
/* 29:   */  public static void glGetQueryObject(int id, int pname, LongBuffer params) {
/* 30:30 */    GL33.glGetQueryObject(id, pname, params);
/* 31:   */  }
/* 32:   */  
/* 33:   */  public static long glGetQueryObjecti64(int id, int pname)
/* 34:   */  {
/* 35:35 */    return GL33.glGetQueryObjecti64(id, pname);
/* 36:   */  }
/* 37:   */  
/* 38:   */  public static void glGetQueryObjectu(int id, int pname, LongBuffer params) {
/* 39:39 */    GL33.glGetQueryObjectu(id, pname, params);
/* 40:   */  }
/* 41:   */  
/* 42:   */  public static long glGetQueryObjectui64(int id, int pname)
/* 43:   */  {
/* 44:44 */    return GL33.glGetQueryObjectui64(id, pname);
/* 45:   */  }
/* 46:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBTimerQuery
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */