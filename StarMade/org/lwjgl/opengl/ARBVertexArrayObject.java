/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */
/* 13:   */public final class ARBVertexArrayObject
/* 14:   */{
/* 15:   */  public static final int GL_VERTEX_ARRAY_BINDING = 34229;
/* 16:   */  
/* 17:   */  public static void glBindVertexArray(int array)
/* 18:   */  {
/* 19:19 */    GL30.glBindVertexArray(array);
/* 20:   */  }
/* 21:   */  
/* 22:   */  public static void glDeleteVertexArrays(IntBuffer arrays) {
/* 23:23 */    GL30.glDeleteVertexArrays(arrays);
/* 24:   */  }
/* 25:   */  
/* 26:   */  public static void glDeleteVertexArrays(int array)
/* 27:   */  {
/* 28:28 */    GL30.glDeleteVertexArrays(array);
/* 29:   */  }
/* 30:   */  
/* 31:   */  public static void glGenVertexArrays(IntBuffer arrays) {
/* 32:32 */    GL30.glGenVertexArrays(arrays);
/* 33:   */  }
/* 34:   */  
/* 35:   */  public static int glGenVertexArrays()
/* 36:   */  {
/* 37:37 */    return GL30.glGenVertexArrays();
/* 38:   */  }
/* 39:   */  
/* 40:   */  public static boolean glIsVertexArray(int array) {
/* 41:41 */    return GL30.glIsVertexArray(array);
/* 42:   */  }
/* 43:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBVertexArrayObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */