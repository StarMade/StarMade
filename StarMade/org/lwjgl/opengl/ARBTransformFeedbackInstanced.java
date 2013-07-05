/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ public final class ARBTransformFeedbackInstanced
/*    */ {
/*    */   public static void glDrawTransformFeedbackInstanced(int mode, int id, int primcount)
/*    */   {
/* 13 */     GL42.glDrawTransformFeedbackInstanced(mode, id, primcount);
/*    */   }
/*    */ 
/*    */   public static void glDrawTransformFeedbackStreamInstanced(int mode, int id, int stream, int primcount) {
/* 17 */     GL42.glDrawTransformFeedbackStreamInstanced(mode, id, stream, primcount);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBTransformFeedbackInstanced
 * JD-Core Version:    0.6.2
 */