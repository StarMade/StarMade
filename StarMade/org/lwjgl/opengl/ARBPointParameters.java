/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.FloatBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class ARBPointParameters
/*    */ {
/*    */   public static final int GL_POINT_SIZE_MIN_ARB = 33062;
/*    */   public static final int GL_POINT_SIZE_MAX_ARB = 33063;
/*    */   public static final int GL_POINT_FADE_THRESHOLD_SIZE_ARB = 33064;
/*    */   public static final int GL_POINT_DISTANCE_ATTENUATION_ARB = 33065;
/*    */ 
/*    */   public static void glPointParameterfARB(int pname, float param)
/*    */   {
/* 18 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 19 */     long function_pointer = caps.glPointParameterfARB;
/* 20 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 21 */     nglPointParameterfARB(pname, param, function_pointer);
/*    */   }
/*    */   static native void nglPointParameterfARB(int paramInt, float paramFloat, long paramLong);
/*    */ 
/*    */   public static void glPointParameterARB(int pname, FloatBuffer pfParams) {
/* 26 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 27 */     long function_pointer = caps.glPointParameterfvARB;
/* 28 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 29 */     BufferChecks.checkBuffer(pfParams, 4);
/* 30 */     nglPointParameterfvARB(pname, MemoryUtil.getAddress(pfParams), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglPointParameterfvARB(int paramInt, long paramLong1, long paramLong2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBPointParameters
 * JD-Core Version:    0.6.2
 */