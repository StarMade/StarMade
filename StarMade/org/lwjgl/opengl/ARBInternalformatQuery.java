/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.IntBuffer;
/*    */ 
/*    */ public final class ARBInternalformatQuery
/*    */ {
/*    */   public static final int GL_NUM_SAMPLE_COUNTS = 37760;
/*    */ 
/*    */   public static void glGetInternalformat(int target, int internalformat, int pname, IntBuffer params)
/*    */   {
/* 18 */     GL42.glGetInternalformat(target, internalformat, pname, params);
/*    */   }
/*    */ 
/*    */   public static int glGetInternalformat(int target, int internalformat, int pname)
/*    */   {
/* 23 */     return GL42.glGetInternalformat(target, internalformat, pname);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBInternalformatQuery
 * JD-Core Version:    0.6.2
 */