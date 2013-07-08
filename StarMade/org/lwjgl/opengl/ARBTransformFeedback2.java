package org.lwjgl.opengl;

import java.nio.IntBuffer;

public final class ARBTransformFeedback2
{
  public static final int GL_TRANSFORM_FEEDBACK = 36386;
  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_PAUSED = 36387;
  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_ACTIVE = 36388;
  public static final int GL_TRANSFORM_FEEDBACK_BINDING = 36389;
  
  public static void glBindTransformFeedback(int target, int local_id)
  {
    GL40.glBindTransformFeedback(target, local_id);
  }
  
  public static void glDeleteTransformFeedbacks(IntBuffer ids)
  {
    GL40.glDeleteTransformFeedbacks(ids);
  }
  
  public static void glDeleteTransformFeedbacks(int local_id)
  {
    GL40.glDeleteTransformFeedbacks(local_id);
  }
  
  public static void glGenTransformFeedbacks(IntBuffer ids)
  {
    GL40.glGenTransformFeedbacks(ids);
  }
  
  public static int glGenTransformFeedbacks()
  {
    return GL40.glGenTransformFeedbacks();
  }
  
  public static boolean glIsTransformFeedback(int local_id)
  {
    return GL40.glIsTransformFeedback(local_id);
  }
  
  public static void glPauseTransformFeedback() {}
  
  public static void glResumeTransformFeedback() {}
  
  public static void glDrawTransformFeedback(int mode, int local_id)
  {
    GL40.glDrawTransformFeedback(mode, local_id);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.ARBTransformFeedback2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */