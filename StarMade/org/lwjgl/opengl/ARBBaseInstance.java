/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.IntBuffer;
/*  5:   */import java.nio.ShortBuffer;
/*  6:   */
/*  9:   */public final class ARBBaseInstance
/* 10:   */{
/* 11:   */  public static void glDrawArraysInstancedBaseInstance(int mode, int first, int count, int primcount, int baseinstance)
/* 12:   */  {
/* 13:13 */    GL42.glDrawArraysInstancedBaseInstance(mode, first, count, primcount, baseinstance);
/* 14:   */  }
/* 15:   */  
/* 16:   */  public static void glDrawElementsInstancedBaseInstance(int mode, ByteBuffer indices, int primcount, int baseinstance) {
/* 17:17 */    GL42.glDrawElementsInstancedBaseInstance(mode, indices, primcount, baseinstance);
/* 18:   */  }
/* 19:   */  
/* 20:20 */  public static void glDrawElementsInstancedBaseInstance(int mode, IntBuffer indices, int primcount, int baseinstance) { GL42.glDrawElementsInstancedBaseInstance(mode, indices, primcount, baseinstance); }
/* 21:   */  
/* 22:   */  public static void glDrawElementsInstancedBaseInstance(int mode, ShortBuffer indices, int primcount, int baseinstance) {
/* 23:23 */    GL42.glDrawElementsInstancedBaseInstance(mode, indices, primcount, baseinstance);
/* 24:   */  }
/* 25:   */  
/* 26:26 */  public static void glDrawElementsInstancedBaseInstance(int mode, int indices_count, int type, long indices_buffer_offset, int primcount, int baseinstance) { GL42.glDrawElementsInstancedBaseInstance(mode, indices_count, type, indices_buffer_offset, primcount, baseinstance); }
/* 27:   */  
/* 28:   */  public static void glDrawElementsInstancedBaseVertexBaseInstance(int mode, ByteBuffer indices, int primcount, int basevertex, int baseinstance)
/* 29:   */  {
/* 30:30 */    GL42.glDrawElementsInstancedBaseVertexBaseInstance(mode, indices, primcount, basevertex, baseinstance);
/* 31:   */  }
/* 32:   */  
/* 33:33 */  public static void glDrawElementsInstancedBaseVertexBaseInstance(int mode, IntBuffer indices, int primcount, int basevertex, int baseinstance) { GL42.glDrawElementsInstancedBaseVertexBaseInstance(mode, indices, primcount, basevertex, baseinstance); }
/* 34:   */  
/* 35:   */  public static void glDrawElementsInstancedBaseVertexBaseInstance(int mode, ShortBuffer indices, int primcount, int basevertex, int baseinstance) {
/* 36:36 */    GL42.glDrawElementsInstancedBaseVertexBaseInstance(mode, indices, primcount, basevertex, baseinstance);
/* 37:   */  }
/* 38:   */  
/* 39:39 */  public static void glDrawElementsInstancedBaseVertexBaseInstance(int mode, int indices_count, int type, long indices_buffer_offset, int primcount, int basevertex, int baseinstance) { GL42.glDrawElementsInstancedBaseVertexBaseInstance(mode, indices_count, type, indices_buffer_offset, primcount, basevertex, baseinstance); }
/* 40:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBBaseInstance
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */