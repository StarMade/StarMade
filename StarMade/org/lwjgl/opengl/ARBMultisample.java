/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/*  9:   */public final class ARBMultisample
/* 10:   */{
/* 11:   */  public static final int GL_MULTISAMPLE_ARB = 32925;
/* 12:   */  public static final int GL_SAMPLE_ALPHA_TO_COVERAGE_ARB = 32926;
/* 13:   */  public static final int GL_SAMPLE_ALPHA_TO_ONE_ARB = 32927;
/* 14:   */  public static final int GL_SAMPLE_COVERAGE_ARB = 32928;
/* 15:   */  public static final int GL_SAMPLE_BUFFERS_ARB = 32936;
/* 16:   */  public static final int GL_SAMPLES_ARB = 32937;
/* 17:   */  public static final int GL_SAMPLE_COVERAGE_VALUE_ARB = 32938;
/* 18:   */  public static final int GL_SAMPLE_COVERAGE_INVERT_ARB = 32939;
/* 19:   */  public static final int GL_MULTISAMPLE_BIT_ARB = 536870912;
/* 20:   */  
/* 21:   */  public static void glSampleCoverageARB(float value, boolean invert)
/* 22:   */  {
/* 23:23 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 24:24 */    long function_pointer = caps.glSampleCoverageARB;
/* 25:25 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 26:26 */    nglSampleCoverageARB(value, invert, function_pointer);
/* 27:   */  }
/* 28:   */  
/* 29:   */  static native void nglSampleCoverageARB(float paramFloat, boolean paramBoolean, long paramLong);
/* 30:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBMultisample
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */