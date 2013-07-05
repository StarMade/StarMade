/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class NVOcclusionQuery
/*     */ {
/*     */   public static final int GL_OCCLUSION_TEST_HP = 33125;
/*     */   public static final int GL_OCCLUSION_TEST_RESULT_HP = 33126;
/*     */   public static final int GL_PIXEL_COUNTER_BITS_NV = 34916;
/*     */   public static final int GL_CURRENT_OCCLUSION_QUERY_ID_NV = 34917;
/*     */   public static final int GL_PIXEL_COUNT_NV = 34918;
/*     */   public static final int GL_PIXEL_COUNT_AVAILABLE_NV = 34919;
/*     */ 
/*     */   public static void glGenOcclusionQueriesNV(IntBuffer piIDs)
/*     */   {
/*  20 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  21 */     long function_pointer = caps.glGenOcclusionQueriesNV;
/*  22 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  23 */     BufferChecks.checkDirect(piIDs);
/*  24 */     nglGenOcclusionQueriesNV(piIDs.remaining(), MemoryUtil.getAddress(piIDs), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGenOcclusionQueriesNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGenOcclusionQueriesNV() {
/*  30 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  31 */     long function_pointer = caps.glGenOcclusionQueriesNV;
/*  32 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  33 */     IntBuffer piIDs = APIUtil.getBufferInt(caps);
/*  34 */     nglGenOcclusionQueriesNV(1, MemoryUtil.getAddress(piIDs), function_pointer);
/*  35 */     return piIDs.get(0);
/*     */   }
/*     */ 
/*     */   public static void glDeleteOcclusionQueriesNV(IntBuffer piIDs) {
/*  39 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  40 */     long function_pointer = caps.glDeleteOcclusionQueriesNV;
/*  41 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  42 */     BufferChecks.checkDirect(piIDs);
/*  43 */     nglDeleteOcclusionQueriesNV(piIDs.remaining(), MemoryUtil.getAddress(piIDs), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglDeleteOcclusionQueriesNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDeleteOcclusionQueriesNV(int piID) {
/*  49 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  50 */     long function_pointer = caps.glDeleteOcclusionQueriesNV;
/*  51 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  52 */     nglDeleteOcclusionQueriesNV(1, APIUtil.getInt(caps, piID), function_pointer);
/*     */   }
/*     */ 
/*     */   public static boolean glIsOcclusionQueryNV(int id) {
/*  56 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  57 */     long function_pointer = caps.glIsOcclusionQueryNV;
/*  58 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  59 */     boolean __result = nglIsOcclusionQueryNV(id, function_pointer);
/*  60 */     return __result;
/*     */   }
/*     */   static native boolean nglIsOcclusionQueryNV(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glBeginOcclusionQueryNV(int id) {
/*  65 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  66 */     long function_pointer = caps.glBeginOcclusionQueryNV;
/*  67 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  68 */     nglBeginOcclusionQueryNV(id, function_pointer);
/*     */   }
/*     */   static native void nglBeginOcclusionQueryNV(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glEndOcclusionQueryNV() {
/*  73 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  74 */     long function_pointer = caps.glEndOcclusionQueryNV;
/*  75 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  76 */     nglEndOcclusionQueryNV(function_pointer);
/*     */   }
/*     */   static native void nglEndOcclusionQueryNV(long paramLong);
/*     */ 
/*     */   public static void glGetOcclusionQueryuNV(int id, int pname, IntBuffer params) {
/*  81 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  82 */     long function_pointer = caps.glGetOcclusionQueryuivNV;
/*  83 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  84 */     BufferChecks.checkBuffer(params, 1);
/*  85 */     nglGetOcclusionQueryuivNV(id, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetOcclusionQueryuivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetOcclusionQueryuiNV(int id, int pname) {
/*  91 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  92 */     long function_pointer = caps.glGetOcclusionQueryuivNV;
/*  93 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  94 */     IntBuffer params = APIUtil.getBufferInt(caps);
/*  95 */     nglGetOcclusionQueryuivNV(id, pname, MemoryUtil.getAddress(params), function_pointer);
/*  96 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetOcclusionQueryNV(int id, int pname, IntBuffer params) {
/* 100 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 101 */     long function_pointer = caps.glGetOcclusionQueryivNV;
/* 102 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 103 */     BufferChecks.checkBuffer(params, 1);
/* 104 */     nglGetOcclusionQueryivNV(id, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetOcclusionQueryivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetOcclusionQueryiNV(int id, int pname) {
/* 110 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 111 */     long function_pointer = caps.glGetOcclusionQueryivNV;
/* 112 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 113 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 114 */     nglGetOcclusionQueryivNV(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 115 */     return params.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVOcclusionQuery
 * JD-Core Version:    0.6.2
 */