/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ 
/*    */ public final class ARBBlendFuncExtended
/*    */ {
/*    */   public static final int GL_SRC1_COLOR = 35065;
/*    */   public static final int GL_SRC1_ALPHA = 34185;
/*    */   public static final int GL_ONE_MINUS_SRC1_COLOR = 35066;
/*    */   public static final int GL_ONE_MINUS_SRC1_ALPHA = 35067;
/*    */   public static final int GL_MAX_DUAL_SOURCE_DRAW_BUFFERS = 35068;
/*    */ 
/*    */   public static void glBindFragDataLocationIndexed(int program, int colorNumber, int index, ByteBuffer name)
/*    */   {
/* 29 */     GL33.glBindFragDataLocationIndexed(program, colorNumber, index, name);
/*    */   }
/*    */ 
/*    */   public static void glBindFragDataLocationIndexed(int program, int colorNumber, int index, CharSequence name)
/*    */   {
/* 34 */     GL33.glBindFragDataLocationIndexed(program, colorNumber, index, name);
/*    */   }
/*    */ 
/*    */   public static int glGetFragDataIndex(int program, ByteBuffer name) {
/* 38 */     return GL33.glGetFragDataIndex(program, name);
/*    */   }
/*    */ 
/*    */   public static int glGetFragDataIndex(int program, CharSequence name)
/*    */   {
/* 43 */     return GL33.glGetFragDataIndex(program, name);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBBlendFuncExtended
 * JD-Core Version:    0.6.2
 */