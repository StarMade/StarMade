/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.IntBuffer;
/*    */ 
/*    */ public final class ARBInvalidateSubdata
/*    */ {
/*    */   public static void glInvalidateTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth)
/*    */   {
/* 13 */     GL43.glInvalidateTexSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth);
/*    */   }
/*    */ 
/*    */   public static void glInvalidateTexImage(int texture, int level) {
/* 17 */     GL43.glInvalidateTexImage(texture, level);
/*    */   }
/*    */ 
/*    */   public static void glInvalidateBufferSubData(int buffer, long offset, long length) {
/* 21 */     GL43.glInvalidateBufferSubData(buffer, offset, length);
/*    */   }
/*    */ 
/*    */   public static void glInvalidateBufferData(int buffer) {
/* 25 */     GL43.glInvalidateBufferData(buffer);
/*    */   }
/*    */ 
/*    */   public static void glInvalidateFramebuffer(int target, IntBuffer attachments) {
/* 29 */     GL43.glInvalidateFramebuffer(target, attachments);
/*    */   }
/*    */ 
/*    */   public static void glInvalidateSubFramebuffer(int target, IntBuffer attachments, int x, int y, int width, int height) {
/* 33 */     GL43.glInvalidateSubFramebuffer(target, attachments, x, y, width, height);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBInvalidateSubdata
 * JD-Core Version:    0.6.2
 */