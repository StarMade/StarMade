/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ public final class ARBCopyBuffer
/*    */ {
/*    */   public static final int GL_COPY_READ_BUFFER = 36662;
/*    */   public static final int GL_COPY_WRITE_BUFFER = 36663;
/*    */ 
/*    */   public static void glCopyBufferSubData(int readTarget, int writeTarget, long readOffset, long writeOffset, long size)
/*    */   {
/* 23 */     GL31.glCopyBufferSubData(readTarget, writeTarget, readOffset, writeOffset, size);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBCopyBuffer
 * JD-Core Version:    0.6.2
 */