/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/* 19:   */public final class ARBSampleShading
/* 20:   */{
/* 21:   */  public static final int GL_SAMPLE_SHADING_ARB = 35894;
/* 22:   */  public static final int GL_MIN_SAMPLE_SHADING_VALUE_ARB = 35895;
/* 23:   */  
/* 24:   */  public static void glMinSampleShadingARB(float value)
/* 25:   */  {
/* 26:26 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 27:27 */    long function_pointer = caps.glMinSampleShadingARB;
/* 28:28 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 29:29 */    nglMinSampleShadingARB(value, function_pointer);
/* 30:   */  }
/* 31:   */  
/* 32:   */  static native void nglMinSampleShadingARB(float paramFloat, long paramLong);
/* 33:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBSampleShading
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */