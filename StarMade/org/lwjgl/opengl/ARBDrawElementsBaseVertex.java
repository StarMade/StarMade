/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.IntBuffer;
/*  5:   */import java.nio.ShortBuffer;
/*  6:   */
/*  9:   */public final class ARBDrawElementsBaseVertex
/* 10:   */{
/* 11:   */  public static void glDrawElementsBaseVertex(int mode, ByteBuffer indices, int basevertex)
/* 12:   */  {
/* 13:13 */    GL32.glDrawElementsBaseVertex(mode, indices, basevertex);
/* 14:   */  }
/* 15:   */  
/* 16:16 */  public static void glDrawElementsBaseVertex(int mode, IntBuffer indices, int basevertex) { GL32.glDrawElementsBaseVertex(mode, indices, basevertex); }
/* 17:   */  
/* 18:   */  public static void glDrawElementsBaseVertex(int mode, ShortBuffer indices, int basevertex) {
/* 19:19 */    GL32.glDrawElementsBaseVertex(mode, indices, basevertex);
/* 20:   */  }
/* 21:   */  
/* 22:22 */  public static void glDrawElementsBaseVertex(int mode, int indices_count, int type, long indices_buffer_offset, int basevertex) { GL32.glDrawElementsBaseVertex(mode, indices_count, type, indices_buffer_offset, basevertex); }
/* 23:   */  
/* 24:   */  public static void glDrawRangeElementsBaseVertex(int mode, int start, int end, ByteBuffer indices, int basevertex)
/* 25:   */  {
/* 26:26 */    GL32.glDrawRangeElementsBaseVertex(mode, start, end, indices, basevertex);
/* 27:   */  }
/* 28:   */  
/* 29:29 */  public static void glDrawRangeElementsBaseVertex(int mode, int start, int end, IntBuffer indices, int basevertex) { GL32.glDrawRangeElementsBaseVertex(mode, start, end, indices, basevertex); }
/* 30:   */  
/* 31:   */  public static void glDrawRangeElementsBaseVertex(int mode, int start, int end, ShortBuffer indices, int basevertex) {
/* 32:32 */    GL32.glDrawRangeElementsBaseVertex(mode, start, end, indices, basevertex);
/* 33:   */  }
/* 34:   */  
/* 35:35 */  public static void glDrawRangeElementsBaseVertex(int mode, int start, int end, int indices_count, int type, long indices_buffer_offset, int basevertex) { GL32.glDrawRangeElementsBaseVertex(mode, start, end, indices_count, type, indices_buffer_offset, basevertex); }
/* 36:   */  
/* 37:   */  public static void glDrawElementsInstancedBaseVertex(int mode, ByteBuffer indices, int primcount, int basevertex)
/* 38:   */  {
/* 39:39 */    GL32.glDrawElementsInstancedBaseVertex(mode, indices, primcount, basevertex);
/* 40:   */  }
/* 41:   */  
/* 42:42 */  public static void glDrawElementsInstancedBaseVertex(int mode, IntBuffer indices, int primcount, int basevertex) { GL32.glDrawElementsInstancedBaseVertex(mode, indices, primcount, basevertex); }
/* 43:   */  
/* 44:   */  public static void glDrawElementsInstancedBaseVertex(int mode, ShortBuffer indices, int primcount, int basevertex) {
/* 45:45 */    GL32.glDrawElementsInstancedBaseVertex(mode, indices, primcount, basevertex);
/* 46:   */  }
/* 47:   */  
/* 48:48 */  public static void glDrawElementsInstancedBaseVertex(int mode, int indices_count, int type, long indices_buffer_offset, int primcount, int basevertex) { GL32.glDrawElementsInstancedBaseVertex(mode, indices_count, type, indices_buffer_offset, primcount, basevertex); }
/* 49:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBDrawElementsBaseVertex
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */