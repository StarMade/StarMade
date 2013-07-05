/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class EXTBlendFuncSeparate
/*    */ {
/*    */   public static final int GL_BLEND_DST_RGB_EXT = 32968;
/*    */   public static final int GL_BLEND_SRC_RGB_EXT = 32969;
/*    */   public static final int GL_BLEND_DST_ALPHA_EXT = 32970;
/*    */   public static final int GL_BLEND_SRC_ALPHA_EXT = 32971;
/*    */ 
/*    */   public static void glBlendFuncSeparateEXT(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha)
/*    */   {
/* 18 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 19 */     long function_pointer = caps.glBlendFuncSeparateEXT;
/* 20 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 21 */     nglBlendFuncSeparateEXT(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglBlendFuncSeparateEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTBlendFuncSeparate
 * JD-Core Version:    0.6.2
 */