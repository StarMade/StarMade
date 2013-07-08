/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */
/*  9:   */public final class ARBInvalidateSubdata
/* 10:   */{
/* 11:   */  public static void glInvalidateTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth)
/* 12:   */  {
/* 13:13 */    GL43.glInvalidateTexSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth);
/* 14:   */  }
/* 15:   */  
/* 16:   */  public static void glInvalidateTexImage(int texture, int level) {
/* 17:17 */    GL43.glInvalidateTexImage(texture, level);
/* 18:   */  }
/* 19:   */  
/* 20:   */  public static void glInvalidateBufferSubData(int buffer, long offset, long length) {
/* 21:21 */    GL43.glInvalidateBufferSubData(buffer, offset, length);
/* 22:   */  }
/* 23:   */  
/* 24:   */  public static void glInvalidateBufferData(int buffer) {
/* 25:25 */    GL43.glInvalidateBufferData(buffer);
/* 26:   */  }
/* 27:   */  
/* 28:   */  public static void glInvalidateFramebuffer(int target, IntBuffer attachments) {
/* 29:29 */    GL43.glInvalidateFramebuffer(target, attachments);
/* 30:   */  }
/* 31:   */  
/* 32:   */  public static void glInvalidateSubFramebuffer(int target, IntBuffer attachments, int x, int y, int width, int height) {
/* 33:33 */    GL43.glInvalidateSubFramebuffer(target, attachments, x, y, width, height);
/* 34:   */  }
/* 35:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBInvalidateSubdata
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */