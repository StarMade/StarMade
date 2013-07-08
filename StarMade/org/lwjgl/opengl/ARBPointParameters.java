/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.FloatBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/*  9:   */public final class ARBPointParameters
/* 10:   */{
/* 11:   */  public static final int GL_POINT_SIZE_MIN_ARB = 33062;
/* 12:   */  public static final int GL_POINT_SIZE_MAX_ARB = 33063;
/* 13:   */  public static final int GL_POINT_FADE_THRESHOLD_SIZE_ARB = 33064;
/* 14:   */  public static final int GL_POINT_DISTANCE_ATTENUATION_ARB = 33065;
/* 15:   */  
/* 16:   */  public static void glPointParameterfARB(int pname, float param)
/* 17:   */  {
/* 18:18 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 19:19 */    long function_pointer = caps.glPointParameterfARB;
/* 20:20 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 21:21 */    nglPointParameterfARB(pname, param, function_pointer);
/* 22:   */  }
/* 23:   */  
/* 24:   */  static native void nglPointParameterfARB(int paramInt, float paramFloat, long paramLong);
/* 25:   */  
/* 26:26 */  public static void glPointParameterARB(int pname, FloatBuffer pfParams) { ContextCapabilities caps = GLContext.getCapabilities();
/* 27:27 */    long function_pointer = caps.glPointParameterfvARB;
/* 28:28 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 29:29 */    BufferChecks.checkBuffer(pfParams, 4);
/* 30:30 */    nglPointParameterfvARB(pname, MemoryUtil.getAddress(pfParams), function_pointer);
/* 31:   */  }
/* 32:   */  
/* 33:   */  static native void nglPointParameterfvARB(int paramInt, long paramLong1, long paramLong2);
/* 34:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBPointParameters
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */