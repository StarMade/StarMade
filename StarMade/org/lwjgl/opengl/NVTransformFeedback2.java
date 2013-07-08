/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/* 17:   */public final class NVTransformFeedback2
/* 18:   */{
/* 19:   */  public static final int GL_TRANSFORM_FEEDBACK_NV = 36386;
/* 20:   */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_PAUSED_NV = 36387;
/* 21:   */  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_ACTIVE_NV = 36388;
/* 22:   */  public static final int GL_TRANSFORM_FEEDBACK_BINDING_NV = 36389;
/* 23:   */  
/* 24:   */  public static void glBindTransformFeedbackNV(int target, int id)
/* 25:   */  {
/* 26:26 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 27:27 */    long function_pointer = caps.glBindTransformFeedbackNV;
/* 28:28 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 29:29 */    nglBindTransformFeedbackNV(target, id, function_pointer);
/* 30:   */  }
/* 31:   */  
/* 32:   */  static native void nglBindTransformFeedbackNV(int paramInt1, int paramInt2, long paramLong);
/* 33:   */  
/* 34:34 */  public static void glDeleteTransformFeedbacksNV(IntBuffer ids) { ContextCapabilities caps = GLContext.getCapabilities();
/* 35:35 */    long function_pointer = caps.glDeleteTransformFeedbacksNV;
/* 36:36 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 37:37 */    BufferChecks.checkDirect(ids);
/* 38:38 */    nglDeleteTransformFeedbacksNV(ids.remaining(), MemoryUtil.getAddress(ids), function_pointer);
/* 39:   */  }
/* 40:   */  
/* 41:   */  static native void nglDeleteTransformFeedbacksNV(int paramInt, long paramLong1, long paramLong2);
/* 42:   */  
/* 43:   */  public static void glDeleteTransformFeedbacksNV(int id) {
/* 44:44 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 45:45 */    long function_pointer = caps.glDeleteTransformFeedbacksNV;
/* 46:46 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 47:47 */    nglDeleteTransformFeedbacksNV(1, APIUtil.getInt(caps, id), function_pointer);
/* 48:   */  }
/* 49:   */  
/* 50:   */  public static void glGenTransformFeedbacksNV(IntBuffer ids) {
/* 51:51 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 52:52 */    long function_pointer = caps.glGenTransformFeedbacksNV;
/* 53:53 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 54:54 */    BufferChecks.checkDirect(ids);
/* 55:55 */    nglGenTransformFeedbacksNV(ids.remaining(), MemoryUtil.getAddress(ids), function_pointer);
/* 56:   */  }
/* 57:   */  
/* 58:   */  static native void nglGenTransformFeedbacksNV(int paramInt, long paramLong1, long paramLong2);
/* 59:   */  
/* 60:   */  public static int glGenTransformFeedbacksNV() {
/* 61:61 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 62:62 */    long function_pointer = caps.glGenTransformFeedbacksNV;
/* 63:63 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 64:64 */    IntBuffer ids = APIUtil.getBufferInt(caps);
/* 65:65 */    nglGenTransformFeedbacksNV(1, MemoryUtil.getAddress(ids), function_pointer);
/* 66:66 */    return ids.get(0);
/* 67:   */  }
/* 68:   */  
/* 69:   */  public static boolean glIsTransformFeedbackNV(int id) {
/* 70:70 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 71:71 */    long function_pointer = caps.glIsTransformFeedbackNV;
/* 72:72 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 73:73 */    boolean __result = nglIsTransformFeedbackNV(id, function_pointer);
/* 74:74 */    return __result;
/* 75:   */  }
/* 76:   */  
/* 77:   */  static native boolean nglIsTransformFeedbackNV(int paramInt, long paramLong);
/* 78:   */  
/* 79:79 */  public static void glPauseTransformFeedbackNV() { ContextCapabilities caps = GLContext.getCapabilities();
/* 80:80 */    long function_pointer = caps.glPauseTransformFeedbackNV;
/* 81:81 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 82:82 */    nglPauseTransformFeedbackNV(function_pointer);
/* 83:   */  }
/* 84:   */  
/* 85:   */  static native void nglPauseTransformFeedbackNV(long paramLong);
/* 86:   */  
/* 87:87 */  public static void glResumeTransformFeedbackNV() { ContextCapabilities caps = GLContext.getCapabilities();
/* 88:88 */    long function_pointer = caps.glResumeTransformFeedbackNV;
/* 89:89 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 90:90 */    nglResumeTransformFeedbackNV(function_pointer);
/* 91:   */  }
/* 92:   */  
/* 93:   */  static native void nglResumeTransformFeedbackNV(long paramLong);
/* 94:   */  
/* 95:95 */  public static void glDrawTransformFeedbackNV(int mode, int id) { ContextCapabilities caps = GLContext.getCapabilities();
/* 96:96 */    long function_pointer = caps.glDrawTransformFeedbackNV;
/* 97:97 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 98:98 */    nglDrawTransformFeedbackNV(mode, id, function_pointer);
/* 99:   */  }
/* 100:   */  
/* 101:   */  static native void nglDrawTransformFeedbackNV(int paramInt1, int paramInt2, long paramLong);
/* 102:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVTransformFeedback2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */