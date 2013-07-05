/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class EXTStencilTwoSide
/*    */ {
/*    */   public static final int GL_STENCIL_TEST_TWO_SIDE_EXT = 35088;
/*    */   public static final int GL_ACTIVE_STENCIL_FACE_EXT = 35089;
/*    */ 
/*    */   public static void glActiveStencilFaceEXT(int face)
/*    */   {
/* 16 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 17 */     long function_pointer = caps.glActiveStencilFaceEXT;
/* 18 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 19 */     nglActiveStencilFaceEXT(face, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglActiveStencilFaceEXT(int paramInt, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTStencilTwoSide
 * JD-Core Version:    0.6.2
 */