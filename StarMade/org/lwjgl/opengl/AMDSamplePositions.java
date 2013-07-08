/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.FloatBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/* 12:   */public final class AMDSamplePositions
/* 13:   */{
/* 14:   */  public static final int GL_SUBSAMPLE_DISTANCE_AMD = 34879;
/* 15:   */  
/* 16:   */  public static void glSetMultisampleAMD(int pname, int index, FloatBuffer val)
/* 17:   */  {
/* 18:18 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 19:19 */    long function_pointer = caps.glSetMultisamplefvAMD;
/* 20:20 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 21:21 */    BufferChecks.checkBuffer(val, 2);
/* 22:22 */    nglSetMultisamplefvAMD(pname, index, MemoryUtil.getAddress(val), function_pointer);
/* 23:   */  }
/* 24:   */  
/* 25:   */  static native void nglSetMultisamplefvAMD(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 26:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AMDSamplePositions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */