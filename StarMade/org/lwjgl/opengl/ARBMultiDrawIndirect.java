/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ 
/*    */ public final class ARBMultiDrawIndirect
/*    */ {
/*    */   public static void glMultiDrawArraysIndirect(int mode, ByteBuffer indirect, int primcount, int stride)
/*    */   {
/* 13 */     GL43.glMultiDrawArraysIndirect(mode, indirect, primcount, stride);
/*    */   }
/*    */   public static void glMultiDrawArraysIndirect(int mode, long indirect_buffer_offset, int primcount, int stride) {
/* 16 */     GL43.glMultiDrawArraysIndirect(mode, indirect_buffer_offset, primcount, stride);
/*    */   }
/*    */ 
/*    */   public static void glMultiDrawArraysIndirect(int mode, IntBuffer indirect, int primcount, int stride)
/*    */   {
/* 21 */     GL43.glMultiDrawArraysIndirect(mode, indirect, primcount, stride);
/*    */   }
/*    */ 
/*    */   public static void glMultiDrawElementsIndirect(int mode, int type, ByteBuffer indirect, int primcount, int stride) {
/* 25 */     GL43.glMultiDrawElementsIndirect(mode, type, indirect, primcount, stride);
/*    */   }
/*    */   public static void glMultiDrawElementsIndirect(int mode, int type, long indirect_buffer_offset, int primcount, int stride) {
/* 28 */     GL43.glMultiDrawElementsIndirect(mode, type, indirect_buffer_offset, primcount, stride);
/*    */   }
/*    */ 
/*    */   public static void glMultiDrawElementsIndirect(int mode, int type, IntBuffer indirect, int primcount, int stride)
/*    */   {
/* 33 */     GL43.glMultiDrawElementsIndirect(mode, type, indirect, primcount, stride);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBMultiDrawIndirect
 * JD-Core Version:    0.6.2
 */