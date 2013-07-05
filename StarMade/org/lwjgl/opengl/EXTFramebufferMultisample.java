/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class EXTFramebufferMultisample
/*    */ {
/*    */   public static final int GL_RENDERBUFFER_SAMPLES_EXT = 36011;
/*    */   public static final int GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE_EXT = 36182;
/*    */   public static final int GL_MAX_SAMPLES_EXT = 36183;
/*    */ 
/*    */   public static void glRenderbufferStorageMultisampleEXT(int target, int samples, int internalformat, int width, int height)
/*    */   {
/* 33 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 34 */     long function_pointer = caps.glRenderbufferStorageMultisampleEXT;
/* 35 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 36 */     nglRenderbufferStorageMultisampleEXT(target, samples, internalformat, width, height, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglRenderbufferStorageMultisampleEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTFramebufferMultisample
 * JD-Core Version:    0.6.2
 */