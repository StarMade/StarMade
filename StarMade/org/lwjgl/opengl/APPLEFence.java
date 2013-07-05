/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class APPLEFence
/*     */ {
/*     */   public static final int GL_DRAW_PIXELS_APPLE = 35338;
/*     */   public static final int GL_FENCE_APPLE = 35339;
/*     */ 
/*     */   public static void glGenFencesAPPLE(IntBuffer fences)
/*     */   {
/*  19 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  20 */     long function_pointer = caps.glGenFencesAPPLE;
/*  21 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  22 */     BufferChecks.checkDirect(fences);
/*  23 */     nglGenFencesAPPLE(fences.remaining(), MemoryUtil.getAddress(fences), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGenFencesAPPLE(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGenFencesAPPLE() {
/*  29 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  30 */     long function_pointer = caps.glGenFencesAPPLE;
/*  31 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  32 */     IntBuffer fences = APIUtil.getBufferInt(caps);
/*  33 */     nglGenFencesAPPLE(1, MemoryUtil.getAddress(fences), function_pointer);
/*  34 */     return fences.get(0);
/*     */   }
/*     */ 
/*     */   public static void glDeleteFencesAPPLE(IntBuffer fences) {
/*  38 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  39 */     long function_pointer = caps.glDeleteFencesAPPLE;
/*  40 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  41 */     BufferChecks.checkDirect(fences);
/*  42 */     nglDeleteFencesAPPLE(fences.remaining(), MemoryUtil.getAddress(fences), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglDeleteFencesAPPLE(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDeleteFencesAPPLE(int fence) {
/*  48 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  49 */     long function_pointer = caps.glDeleteFencesAPPLE;
/*  50 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  51 */     nglDeleteFencesAPPLE(1, APIUtil.getInt(caps, fence), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glSetFenceAPPLE(int fence) {
/*  55 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  56 */     long function_pointer = caps.glSetFenceAPPLE;
/*  57 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  58 */     nglSetFenceAPPLE(fence, function_pointer);
/*     */   }
/*     */   static native void nglSetFenceAPPLE(int paramInt, long paramLong);
/*     */ 
/*     */   public static boolean glIsFenceAPPLE(int fence) {
/*  63 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  64 */     long function_pointer = caps.glIsFenceAPPLE;
/*  65 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  66 */     boolean __result = nglIsFenceAPPLE(fence, function_pointer);
/*  67 */     return __result;
/*     */   }
/*     */   static native boolean nglIsFenceAPPLE(int paramInt, long paramLong);
/*     */ 
/*     */   public static boolean glTestFenceAPPLE(int fence) {
/*  72 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  73 */     long function_pointer = caps.glTestFenceAPPLE;
/*  74 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  75 */     boolean __result = nglTestFenceAPPLE(fence, function_pointer);
/*  76 */     return __result;
/*     */   }
/*     */   static native boolean nglTestFenceAPPLE(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glFinishFenceAPPLE(int fence) {
/*  81 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  82 */     long function_pointer = caps.glFinishFenceAPPLE;
/*  83 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  84 */     nglFinishFenceAPPLE(fence, function_pointer);
/*     */   }
/*     */   static native void nglFinishFenceAPPLE(int paramInt, long paramLong);
/*     */ 
/*     */   public static boolean glTestObjectAPPLE(int object, int name) {
/*  89 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  90 */     long function_pointer = caps.glTestObjectAPPLE;
/*  91 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  92 */     boolean __result = nglTestObjectAPPLE(object, name, function_pointer);
/*  93 */     return __result;
/*     */   }
/*     */   static native boolean nglTestObjectAPPLE(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glFinishObjectAPPLE(int object, int name) {
/*  98 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  99 */     long function_pointer = caps.glFinishObjectAPPLE;
/* 100 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 101 */     nglFinishObjectAPPLE(object, name, function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglFinishObjectAPPLE(int paramInt1, int paramInt2, long paramLong);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.APPLEFence
 * JD-Core Version:    0.6.2
 */