/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.FloatBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/*  9:   */public final class EXTPointParameters
/* 10:   */{
/* 11:   */  public static final int GL_POINT_SIZE_MIN_EXT = 33062;
/* 12:   */  public static final int GL_POINT_SIZE_MAX_EXT = 33063;
/* 13:   */  public static final int GL_POINT_FADE_THRESHOLD_SIZE_EXT = 33064;
/* 14:   */  public static final int GL_DISTANCE_ATTENUATION_EXT = 33065;
/* 15:   */  
/* 16:   */  public static void glPointParameterfEXT(int pname, float param)
/* 17:   */  {
/* 18:18 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 19:19 */    long function_pointer = caps.glPointParameterfEXT;
/* 20:20 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 21:21 */    nglPointParameterfEXT(pname, param, function_pointer);
/* 22:   */  }
/* 23:   */  
/* 24:   */  static native void nglPointParameterfEXT(int paramInt, float paramFloat, long paramLong);
/* 25:   */  
/* 26:26 */  public static void glPointParameterEXT(int pname, FloatBuffer pfParams) { ContextCapabilities caps = GLContext.getCapabilities();
/* 27:27 */    long function_pointer = caps.glPointParameterfvEXT;
/* 28:28 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 29:29 */    BufferChecks.checkBuffer(pfParams, 4);
/* 30:30 */    nglPointParameterfvEXT(pname, MemoryUtil.getAddress(pfParams), function_pointer);
/* 31:   */  }
/* 32:   */  
/* 33:   */  static native void nglPointParameterfvEXT(int paramInt, long paramLong1, long paramLong2);
/* 34:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTPointParameters
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */