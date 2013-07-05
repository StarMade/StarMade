/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.IntBuffer;
/*    */ 
/*    */ public final class ARBVertexArrayObject
/*    */ {
/*    */   public static final int GL_VERTEX_ARRAY_BINDING = 34229;
/*    */ 
/*    */   public static void glBindVertexArray(int array)
/*    */   {
/* 19 */     GL30.glBindVertexArray(array);
/*    */   }
/*    */ 
/*    */   public static void glDeleteVertexArrays(IntBuffer arrays) {
/* 23 */     GL30.glDeleteVertexArrays(arrays);
/*    */   }
/*    */ 
/*    */   public static void glDeleteVertexArrays(int array)
/*    */   {
/* 28 */     GL30.glDeleteVertexArrays(array);
/*    */   }
/*    */ 
/*    */   public static void glGenVertexArrays(IntBuffer arrays) {
/* 32 */     GL30.glGenVertexArrays(arrays);
/*    */   }
/*    */ 
/*    */   public static int glGenVertexArrays()
/*    */   {
/* 37 */     return GL30.glGenVertexArrays();
/*    */   }
/*    */ 
/*    */   public static boolean glIsVertexArray(int array) {
/* 41 */     return GL30.glIsVertexArray(array);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBVertexArrayObject
 * JD-Core Version:    0.6.2
 */