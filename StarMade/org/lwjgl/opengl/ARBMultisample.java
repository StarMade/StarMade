/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class ARBMultisample
/*    */ {
/*    */   public static final int GL_MULTISAMPLE_ARB = 32925;
/*    */   public static final int GL_SAMPLE_ALPHA_TO_COVERAGE_ARB = 32926;
/*    */   public static final int GL_SAMPLE_ALPHA_TO_ONE_ARB = 32927;
/*    */   public static final int GL_SAMPLE_COVERAGE_ARB = 32928;
/*    */   public static final int GL_SAMPLE_BUFFERS_ARB = 32936;
/*    */   public static final int GL_SAMPLES_ARB = 32937;
/*    */   public static final int GL_SAMPLE_COVERAGE_VALUE_ARB = 32938;
/*    */   public static final int GL_SAMPLE_COVERAGE_INVERT_ARB = 32939;
/*    */   public static final int GL_MULTISAMPLE_BIT_ARB = 536870912;
/*    */ 
/*    */   public static void glSampleCoverageARB(float value, boolean invert)
/*    */   {
/* 23 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 24 */     long function_pointer = caps.glSampleCoverageARB;
/* 25 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 26 */     nglSampleCoverageARB(value, invert, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglSampleCoverageARB(float paramFloat, boolean paramBoolean, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBMultisample
 * JD-Core Version:    0.6.2
 */