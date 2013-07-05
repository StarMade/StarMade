/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class ARBSampleShading
/*    */ {
/*    */   public static final int GL_SAMPLE_SHADING_ARB = 35894;
/*    */   public static final int GL_MIN_SAMPLE_SHADING_VALUE_ARB = 35895;
/*    */ 
/*    */   public static void glMinSampleShadingARB(float value)
/*    */   {
/* 26 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 27 */     long function_pointer = caps.glMinSampleShadingARB;
/* 28 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 29 */     nglMinSampleShadingARB(value, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglMinSampleShadingARB(float paramFloat, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBSampleShading
 * JD-Core Version:    0.6.2
 */