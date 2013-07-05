/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class EXTBlendEquationSeparate
/*    */ {
/*    */   public static final int GL_BLEND_EQUATION_RGB_EXT = 32777;
/*    */   public static final int GL_BLEND_EQUATION_ALPHA_EXT = 34877;
/*    */ 
/*    */   public static void glBlendEquationSeparateEXT(int modeRGB, int modeAlpha)
/*    */   {
/* 20 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 21 */     long function_pointer = caps.glBlendEquationSeparateEXT;
/* 22 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 23 */     nglBlendEquationSeparateEXT(modeRGB, modeAlpha, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglBlendEquationSeparateEXT(int paramInt1, int paramInt2, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTBlendEquationSeparate
 * JD-Core Version:    0.6.2
 */