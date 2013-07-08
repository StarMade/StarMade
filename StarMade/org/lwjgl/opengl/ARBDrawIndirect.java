/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.IntBuffer;
/*  5:   */
/* 21:   */public final class ARBDrawIndirect
/* 22:   */{
/* 23:   */  public static final int GL_DRAW_INDIRECT_BUFFER = 36671;
/* 24:   */  public static final int GL_DRAW_INDIRECT_BUFFER_BINDING = 36675;
/* 25:   */  
/* 26:   */  public static void glDrawArraysIndirect(int mode, ByteBuffer indirect)
/* 27:   */  {
/* 28:28 */    GL40.glDrawArraysIndirect(mode, indirect);
/* 29:   */  }
/* 30:   */  
/* 31:31 */  public static void glDrawArraysIndirect(int mode, long indirect_buffer_offset) { GL40.glDrawArraysIndirect(mode, indirect_buffer_offset); }
/* 32:   */  
/* 34:   */  public static void glDrawArraysIndirect(int mode, IntBuffer indirect)
/* 35:   */  {
/* 36:36 */    GL40.glDrawArraysIndirect(mode, indirect);
/* 37:   */  }
/* 38:   */  
/* 39:   */  public static void glDrawElementsIndirect(int mode, int type, ByteBuffer indirect) {
/* 40:40 */    GL40.glDrawElementsIndirect(mode, type, indirect);
/* 41:   */  }
/* 42:   */  
/* 43:43 */  public static void glDrawElementsIndirect(int mode, int type, long indirect_buffer_offset) { GL40.glDrawElementsIndirect(mode, type, indirect_buffer_offset); }
/* 44:   */  
/* 46:   */  public static void glDrawElementsIndirect(int mode, int type, IntBuffer indirect)
/* 47:   */  {
/* 48:48 */    GL40.glDrawElementsIndirect(mode, type, indirect);
/* 49:   */  }
/* 50:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBDrawIndirect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */