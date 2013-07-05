/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ import java.nio.ShortBuffer;
/*    */ 
/*    */ public final class ARBDrawElementsBaseVertex
/*    */ {
/*    */   public static void glDrawElementsBaseVertex(int mode, ByteBuffer indices, int basevertex)
/*    */   {
/* 13 */     GL32.glDrawElementsBaseVertex(mode, indices, basevertex);
/*    */   }
/*    */   public static void glDrawElementsBaseVertex(int mode, IntBuffer indices, int basevertex) {
/* 16 */     GL32.glDrawElementsBaseVertex(mode, indices, basevertex);
/*    */   }
/*    */   public static void glDrawElementsBaseVertex(int mode, ShortBuffer indices, int basevertex) {
/* 19 */     GL32.glDrawElementsBaseVertex(mode, indices, basevertex);
/*    */   }
/*    */   public static void glDrawElementsBaseVertex(int mode, int indices_count, int type, long indices_buffer_offset, int basevertex) {
/* 22 */     GL32.glDrawElementsBaseVertex(mode, indices_count, type, indices_buffer_offset, basevertex);
/*    */   }
/*    */ 
/*    */   public static void glDrawRangeElementsBaseVertex(int mode, int start, int end, ByteBuffer indices, int basevertex) {
/* 26 */     GL32.glDrawRangeElementsBaseVertex(mode, start, end, indices, basevertex);
/*    */   }
/*    */   public static void glDrawRangeElementsBaseVertex(int mode, int start, int end, IntBuffer indices, int basevertex) {
/* 29 */     GL32.glDrawRangeElementsBaseVertex(mode, start, end, indices, basevertex);
/*    */   }
/*    */   public static void glDrawRangeElementsBaseVertex(int mode, int start, int end, ShortBuffer indices, int basevertex) {
/* 32 */     GL32.glDrawRangeElementsBaseVertex(mode, start, end, indices, basevertex);
/*    */   }
/*    */   public static void glDrawRangeElementsBaseVertex(int mode, int start, int end, int indices_count, int type, long indices_buffer_offset, int basevertex) {
/* 35 */     GL32.glDrawRangeElementsBaseVertex(mode, start, end, indices_count, type, indices_buffer_offset, basevertex);
/*    */   }
/*    */ 
/*    */   public static void glDrawElementsInstancedBaseVertex(int mode, ByteBuffer indices, int primcount, int basevertex) {
/* 39 */     GL32.glDrawElementsInstancedBaseVertex(mode, indices, primcount, basevertex);
/*    */   }
/*    */   public static void glDrawElementsInstancedBaseVertex(int mode, IntBuffer indices, int primcount, int basevertex) {
/* 42 */     GL32.glDrawElementsInstancedBaseVertex(mode, indices, primcount, basevertex);
/*    */   }
/*    */   public static void glDrawElementsInstancedBaseVertex(int mode, ShortBuffer indices, int primcount, int basevertex) {
/* 45 */     GL32.glDrawElementsInstancedBaseVertex(mode, indices, primcount, basevertex);
/*    */   }
/*    */   public static void glDrawElementsInstancedBaseVertex(int mode, int indices_count, int type, long indices_buffer_offset, int primcount, int basevertex) {
/* 48 */     GL32.glDrawElementsInstancedBaseVertex(mode, indices_count, type, indices_buffer_offset, primcount, basevertex);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBDrawElementsBaseVertex
 * JD-Core Version:    0.6.2
 */