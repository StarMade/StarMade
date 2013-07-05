/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class NVFence
/*    */ {
/*    */   public static final int GL_ALL_COMPLETED_NV = 34034;
/*    */   public static final int GL_FENCE_STATUS_NV = 34035;
/*    */   public static final int GL_FENCE_CONDITION_NV = 34036;
/*    */ 
/*    */   public static void glGenFencesNV(IntBuffer piFences)
/*    */   {
/* 17 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 18 */     long function_pointer = caps.glGenFencesNV;
/* 19 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 20 */     BufferChecks.checkDirect(piFences);
/* 21 */     nglGenFencesNV(piFences.remaining(), MemoryUtil.getAddress(piFences), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglGenFencesNV(int paramInt, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static int glGenFencesNV() {
/* 27 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 28 */     long function_pointer = caps.glGenFencesNV;
/* 29 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 30 */     IntBuffer piFences = APIUtil.getBufferInt(caps);
/* 31 */     nglGenFencesNV(1, MemoryUtil.getAddress(piFences), function_pointer);
/* 32 */     return piFences.get(0);
/*    */   }
/*    */ 
/*    */   public static void glDeleteFencesNV(IntBuffer piFences) {
/* 36 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 37 */     long function_pointer = caps.glDeleteFencesNV;
/* 38 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 39 */     BufferChecks.checkDirect(piFences);
/* 40 */     nglDeleteFencesNV(piFences.remaining(), MemoryUtil.getAddress(piFences), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglDeleteFencesNV(int paramInt, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glDeleteFencesNV(int fence) {
/* 46 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 47 */     long function_pointer = caps.glDeleteFencesNV;
/* 48 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 49 */     nglDeleteFencesNV(1, APIUtil.getInt(caps, fence), function_pointer);
/*    */   }
/*    */ 
/*    */   public static void glSetFenceNV(int fence, int condition) {
/* 53 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 54 */     long function_pointer = caps.glSetFenceNV;
/* 55 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 56 */     nglSetFenceNV(fence, condition, function_pointer);
/*    */   }
/*    */   static native void nglSetFenceNV(int paramInt1, int paramInt2, long paramLong);
/*    */ 
/*    */   public static boolean glTestFenceNV(int fence) {
/* 61 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 62 */     long function_pointer = caps.glTestFenceNV;
/* 63 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 64 */     boolean __result = nglTestFenceNV(fence, function_pointer);
/* 65 */     return __result;
/*    */   }
/*    */   static native boolean nglTestFenceNV(int paramInt, long paramLong);
/*    */ 
/*    */   public static void glFinishFenceNV(int fence) {
/* 70 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 71 */     long function_pointer = caps.glFinishFenceNV;
/* 72 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 73 */     nglFinishFenceNV(fence, function_pointer);
/*    */   }
/*    */   static native void nglFinishFenceNV(int paramInt, long paramLong);
/*    */ 
/*    */   public static boolean glIsFenceNV(int fence) {
/* 78 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 79 */     long function_pointer = caps.glIsFenceNV;
/* 80 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 81 */     boolean __result = nglIsFenceNV(fence, function_pointer);
/* 82 */     return __result;
/*    */   }
/*    */   static native boolean nglIsFenceNV(int paramInt, long paramLong);
/*    */ 
/*    */   public static void glGetFenceivNV(int fence, int pname, IntBuffer piParams) {
/* 87 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 88 */     long function_pointer = caps.glGetFenceivNV;
/* 89 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 90 */     BufferChecks.checkBuffer(piParams, 4);
/* 91 */     nglGetFenceivNV(fence, pname, MemoryUtil.getAddress(piParams), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglGetFenceivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVFence
 * JD-Core Version:    0.6.2
 */