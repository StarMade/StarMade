/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class EXTBlendMinmax
/*    */ {
/*    */   public static final int GL_FUNC_ADD_EXT = 32774;
/*    */   public static final int GL_MIN_EXT = 32775;
/*    */   public static final int GL_MAX_EXT = 32776;
/*    */   public static final int GL_BLEND_EQUATION_EXT = 32777;
/*    */ 
/*    */   public static void glBlendEquationEXT(int mode)
/*    */   {
/* 26 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 27 */     long function_pointer = caps.glBlendEquationEXT;
/* 28 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 29 */     nglBlendEquationEXT(mode, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglBlendEquationEXT(int paramInt, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTBlendMinmax
 * JD-Core Version:    0.6.2
 */