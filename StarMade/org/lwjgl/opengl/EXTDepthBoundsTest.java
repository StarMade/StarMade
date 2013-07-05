/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class EXTDepthBoundsTest
/*    */ {
/*    */   public static final int GL_DEPTH_BOUNDS_TEST_EXT = 34960;
/*    */   public static final int GL_DEPTH_BOUNDS_EXT = 34961;
/*    */ 
/*    */   public static void glDepthBoundsEXT(double zmin, double zmax)
/*    */   {
/* 26 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 27 */     long function_pointer = caps.glDepthBoundsEXT;
/* 28 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 29 */     nglDepthBoundsEXT(zmin, zmax, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglDepthBoundsEXT(double paramDouble1, double paramDouble2, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTDepthBoundsTest
 * JD-Core Version:    0.6.2
 */