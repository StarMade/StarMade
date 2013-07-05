/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class ATIPnTriangles
/*    */ {
/*    */   public static final int GL_PN_TRIANGLES_ATI = 34800;
/*    */   public static final int GL_MAX_PN_TRIANGLES_TESSELATION_LEVEL_ATI = 34801;
/*    */   public static final int GL_PN_TRIANGLES_POINT_MODE_ATI = 34802;
/*    */   public static final int GL_PN_TRIANGLES_NORMAL_MODE_ATI = 34803;
/*    */   public static final int GL_PN_TRIANGLES_TESSELATION_LEVEL_ATI = 34804;
/*    */   public static final int GL_PN_TRIANGLES_POINT_MODE_LINEAR_ATI = 34805;
/*    */   public static final int GL_PN_TRIANGLES_POINT_MODE_CUBIC_ATI = 34806;
/*    */   public static final int GL_PN_TRIANGLES_NORMAL_MODE_LINEAR_ATI = 34807;
/*    */   public static final int GL_PN_TRIANGLES_NORMAL_MODE_QUADRATIC_ATI = 34808;
/*    */ 
/*    */   public static void glPNTrianglesfATI(int pname, float param)
/*    */   {
/* 23 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 24 */     long function_pointer = caps.glPNTrianglesfATI;
/* 25 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 26 */     nglPNTrianglesfATI(pname, param, function_pointer);
/*    */   }
/*    */   static native void nglPNTrianglesfATI(int paramInt, float paramFloat, long paramLong);
/*    */ 
/*    */   public static void glPNTrianglesiATI(int pname, int param) {
/* 31 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 32 */     long function_pointer = caps.glPNTrianglesiATI;
/* 33 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 34 */     nglPNTrianglesiATI(pname, param, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglPNTrianglesiATI(int paramInt1, int paramInt2, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ATIPnTriangles
 * JD-Core Version:    0.6.2
 */