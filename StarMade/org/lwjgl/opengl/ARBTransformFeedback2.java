/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */
/* 17:   */public final class ARBTransformFeedback2
/* 18:   */{
/* 19:   */  public static final int GL_TRANSFORM_FEEDBACK = 36386;
/* 20:   */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_PAUSED = 36387;
/* 21:   */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_ACTIVE = 36388;
/* 22:   */  public static final int GL_TRANSFORM_FEEDBACK_BINDING = 36389;
/* 23:   */  
/* 24:   */  public static void glBindTransformFeedback(int target, int id)
/* 25:   */  {
/* 26:26 */    GL40.glBindTransformFeedback(target, id);
/* 27:   */  }
/* 28:   */  
/* 29:   */  public static void glDeleteTransformFeedbacks(IntBuffer ids) {
/* 30:30 */    GL40.glDeleteTransformFeedbacks(ids);
/* 31:   */  }
/* 32:   */  
/* 33:   */  public static void glDeleteTransformFeedbacks(int id)
/* 34:   */  {
/* 35:35 */    GL40.glDeleteTransformFeedbacks(id);
/* 36:   */  }
/* 37:   */  
/* 38:   */  public static void glGenTransformFeedbacks(IntBuffer ids) {
/* 39:39 */    GL40.glGenTransformFeedbacks(ids);
/* 40:   */  }
/* 41:   */  
/* 42:   */  public static int glGenTransformFeedbacks()
/* 43:   */  {
/* 44:44 */    return GL40.glGenTransformFeedbacks();
/* 45:   */  }
/* 46:   */  
/* 47:   */  public static boolean glIsTransformFeedback(int id) {
/* 48:48 */    return GL40.glIsTransformFeedback(id);
/* 49:   */  }
/* 50:   */  
/* 52:   */  public static void glPauseTransformFeedback() {}
/* 53:   */  
/* 55:   */  public static void glResumeTransformFeedback() {}
/* 56:   */  
/* 58:   */  public static void glDrawTransformFeedback(int mode, int id)
/* 59:   */  {
/* 60:60 */    GL40.glDrawTransformFeedback(mode, id);
/* 61:   */  }
/* 62:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBTransformFeedback2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */