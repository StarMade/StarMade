/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */
/* 13:   */public final class ARBTransformFeedback3
/* 14:   */{
/* 15:   */  public static final int GL_MAX_TRANSFORM_FEEDBACK_BUFFERS = 36464;
/* 16:   */  public static final int GL_MAX_VERTEX_STREAMS = 36465;
/* 17:   */  
/* 18:   */  public static void glDrawTransformFeedbackStream(int mode, int id, int stream)
/* 19:   */  {
/* 20:20 */    GL40.glDrawTransformFeedbackStream(mode, id, stream);
/* 21:   */  }
/* 22:   */  
/* 23:   */  public static void glBeginQueryIndexed(int target, int index, int id) {
/* 24:24 */    GL40.glBeginQueryIndexed(target, index, id);
/* 25:   */  }
/* 26:   */  
/* 27:   */  public static void glEndQueryIndexed(int target, int index) {
/* 28:28 */    GL40.glEndQueryIndexed(target, index);
/* 29:   */  }
/* 30:   */  
/* 31:   */  public static void glGetQueryIndexed(int target, int index, int pname, IntBuffer params) {
/* 32:32 */    GL40.glGetQueryIndexed(target, index, pname, params);
/* 33:   */  }
/* 34:   */  
/* 35:   */  public static int glGetQueryIndexedi(int target, int index, int pname)
/* 36:   */  {
/* 37:37 */    return GL40.glGetQueryIndexedi(target, index, pname);
/* 38:   */  }
/* 39:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBTransformFeedback3
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */