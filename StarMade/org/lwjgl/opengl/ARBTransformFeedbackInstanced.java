package org.lwjgl.opengl;

public final class ARBTransformFeedbackInstanced
{
  public static void glDrawTransformFeedbackInstanced(int mode, int local_id, int primcount)
  {
    GL42.glDrawTransformFeedbackInstanced(mode, local_id, primcount);
  }
  
  public static void glDrawTransformFeedbackStreamInstanced(int mode, int local_id, int stream, int primcount)
  {
    GL42.glDrawTransformFeedbackStreamInstanced(mode, local_id, stream, primcount);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.ARBTransformFeedbackInstanced
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */