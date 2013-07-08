/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.FloatBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */
/*  9:   */public final class ARBTransposeMatrix
/* 10:   */{
/* 11:   */  public static final int GL_TRANSPOSE_MODELVIEW_MATRIX_ARB = 34019;
/* 12:   */  public static final int GL_TRANSPOSE_PROJECTION_MATRIX_ARB = 34020;
/* 13:   */  public static final int GL_TRANSPOSE_TEXTURE_MATRIX_ARB = 34021;
/* 14:   */  public static final int GL_TRANSPOSE_COLOR_MATRIX_ARB = 34022;
/* 15:   */  
/* 16:   */  public static void glLoadTransposeMatrixARB(FloatBuffer pfMtx)
/* 17:   */  {
/* 18:18 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 19:19 */    long function_pointer = caps.glLoadTransposeMatrixfARB;
/* 20:20 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 21:21 */    BufferChecks.checkBuffer(pfMtx, 16);
/* 22:22 */    nglLoadTransposeMatrixfARB(MemoryUtil.getAddress(pfMtx), function_pointer);
/* 23:   */  }
/* 24:   */  
/* 25:   */  static native void nglLoadTransposeMatrixfARB(long paramLong1, long paramLong2);
/* 26:   */  
/* 27:27 */  public static void glMultTransposeMatrixARB(FloatBuffer pfMtx) { ContextCapabilities caps = GLContext.getCapabilities();
/* 28:28 */    long function_pointer = caps.glMultTransposeMatrixfARB;
/* 29:29 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 30:30 */    BufferChecks.checkBuffer(pfMtx, 16);
/* 31:31 */    nglMultTransposeMatrixfARB(MemoryUtil.getAddress(pfMtx), function_pointer);
/* 32:   */  }
/* 33:   */  
/* 34:   */  static native void nglMultTransposeMatrixfARB(long paramLong1, long paramLong2);
/* 35:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBTransposeMatrix
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */