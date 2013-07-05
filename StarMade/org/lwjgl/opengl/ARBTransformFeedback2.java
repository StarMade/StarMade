/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.IntBuffer;
/*    */ 
/*    */ public final class ARBTransformFeedback2
/*    */ {
/*    */   public static final int GL_TRANSFORM_FEEDBACK = 36386;
/*    */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_PAUSED = 36387;
/*    */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_ACTIVE = 36388;
/*    */   public static final int GL_TRANSFORM_FEEDBACK_BINDING = 36389;
/*    */ 
/*    */   public static void glBindTransformFeedback(int target, int id)
/*    */   {
/* 26 */     GL40.glBindTransformFeedback(target, id);
/*    */   }
/*    */ 
/*    */   public static void glDeleteTransformFeedbacks(IntBuffer ids) {
/* 30 */     GL40.glDeleteTransformFeedbacks(ids);
/*    */   }
/*    */ 
/*    */   public static void glDeleteTransformFeedbacks(int id)
/*    */   {
/* 35 */     GL40.glDeleteTransformFeedbacks(id);
/*    */   }
/*    */ 
/*    */   public static void glGenTransformFeedbacks(IntBuffer ids) {
/* 39 */     GL40.glGenTransformFeedbacks(ids);
/*    */   }
/*    */ 
/*    */   public static int glGenTransformFeedbacks()
/*    */   {
/* 44 */     return GL40.glGenTransformFeedbacks();
/*    */   }
/*    */ 
/*    */   public static boolean glIsTransformFeedback(int id) {
/* 48 */     return GL40.glIsTransformFeedback(id);
/*    */   }
/*    */ 
/*    */   public static void glPauseTransformFeedback() {
/* 52 */     GL40.glPauseTransformFeedback();
/*    */   }
/*    */ 
/*    */   public static void glResumeTransformFeedback() {
/* 56 */     GL40.glResumeTransformFeedback();
/*    */   }
/*    */ 
/*    */   public static void glDrawTransformFeedback(int mode, int id) {
/* 60 */     GL40.glDrawTransformFeedback(mode, id);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBTransformFeedback2
 * JD-Core Version:    0.6.2
 */