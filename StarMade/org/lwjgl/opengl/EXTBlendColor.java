/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class EXTBlendColor
/*    */ {
/*    */   public static final int GL_CONSTANT_COLOR_EXT = 32769;
/*    */   public static final int GL_ONE_MINUS_CONSTANT_COLOR_EXT = 32770;
/*    */   public static final int GL_CONSTANT_ALPHA_EXT = 32771;
/*    */   public static final int GL_ONE_MINUS_CONSTANT_ALPHA_EXT = 32772;
/*    */   public static final int GL_BLEND_COLOR_EXT = 32773;
/*    */ 
/*    */   public static void glBlendColorEXT(float red, float green, float blue, float alpha)
/*    */   {
/* 27 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 28 */     long function_pointer = caps.glBlendColorEXT;
/* 29 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 30 */     nglBlendColorEXT(red, green, blue, alpha, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglBlendColorEXT(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTBlendColor
 * JD-Core Version:    0.6.2
 */