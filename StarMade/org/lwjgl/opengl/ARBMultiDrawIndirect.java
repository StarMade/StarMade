/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.IntBuffer;
/*  5:   */
/*  9:   */public final class ARBMultiDrawIndirect
/* 10:   */{
/* 11:   */  public static void glMultiDrawArraysIndirect(int mode, ByteBuffer indirect, int primcount, int stride)
/* 12:   */  {
/* 13:13 */    GL43.glMultiDrawArraysIndirect(mode, indirect, primcount, stride);
/* 14:   */  }
/* 15:   */  
/* 16:16 */  public static void glMultiDrawArraysIndirect(int mode, long indirect_buffer_offset, int primcount, int stride) { GL43.glMultiDrawArraysIndirect(mode, indirect_buffer_offset, primcount, stride); }
/* 17:   */  
/* 19:   */  public static void glMultiDrawArraysIndirect(int mode, IntBuffer indirect, int primcount, int stride)
/* 20:   */  {
/* 21:21 */    GL43.glMultiDrawArraysIndirect(mode, indirect, primcount, stride);
/* 22:   */  }
/* 23:   */  
/* 24:   */  public static void glMultiDrawElementsIndirect(int mode, int type, ByteBuffer indirect, int primcount, int stride) {
/* 25:25 */    GL43.glMultiDrawElementsIndirect(mode, type, indirect, primcount, stride);
/* 26:   */  }
/* 27:   */  
/* 28:28 */  public static void glMultiDrawElementsIndirect(int mode, int type, long indirect_buffer_offset, int primcount, int stride) { GL43.glMultiDrawElementsIndirect(mode, type, indirect_buffer_offset, primcount, stride); }
/* 29:   */  
/* 31:   */  public static void glMultiDrawElementsIndirect(int mode, int type, IntBuffer indirect, int primcount, int stride)
/* 32:   */  {
/* 33:33 */    GL43.glMultiDrawElementsIndirect(mode, type, indirect, primcount, stride);
/* 34:   */  }
/* 35:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBMultiDrawIndirect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */