/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class ARBOcclusionQuery
/*     */ {
/*     */   public static final int GL_SAMPLES_PASSED_ARB = 35092;
/*     */   public static final int GL_QUERY_COUNTER_BITS_ARB = 34916;
/*     */   public static final int GL_CURRENT_QUERY_ARB = 34917;
/*     */   public static final int GL_QUERY_RESULT_ARB = 34918;
/*     */   public static final int GL_QUERY_RESULT_AVAILABLE_ARB = 34919;
/*     */ 
/*     */   public static void glGenQueriesARB(IntBuffer ids)
/*     */   {
/*  32 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  33 */     long function_pointer = caps.glGenQueriesARB;
/*  34 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  35 */     BufferChecks.checkDirect(ids);
/*  36 */     nglGenQueriesARB(ids.remaining(), MemoryUtil.getAddress(ids), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGenQueriesARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGenQueriesARB() {
/*  42 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  43 */     long function_pointer = caps.glGenQueriesARB;
/*  44 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  45 */     IntBuffer ids = APIUtil.getBufferInt(caps);
/*  46 */     nglGenQueriesARB(1, MemoryUtil.getAddress(ids), function_pointer);
/*  47 */     return ids.get(0);
/*     */   }
/*     */ 
/*     */   public static void glDeleteQueriesARB(IntBuffer ids) {
/*  51 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  52 */     long function_pointer = caps.glDeleteQueriesARB;
/*  53 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  54 */     BufferChecks.checkDirect(ids);
/*  55 */     nglDeleteQueriesARB(ids.remaining(), MemoryUtil.getAddress(ids), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglDeleteQueriesARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDeleteQueriesARB(int id) {
/*  61 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  62 */     long function_pointer = caps.glDeleteQueriesARB;
/*  63 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  64 */     nglDeleteQueriesARB(1, APIUtil.getInt(caps, id), function_pointer);
/*     */   }
/*     */ 
/*     */   public static boolean glIsQueryARB(int id) {
/*  68 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  69 */     long function_pointer = caps.glIsQueryARB;
/*  70 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  71 */     boolean __result = nglIsQueryARB(id, function_pointer);
/*  72 */     return __result;
/*     */   }
/*     */   static native boolean nglIsQueryARB(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glBeginQueryARB(int target, int id) {
/*  77 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  78 */     long function_pointer = caps.glBeginQueryARB;
/*  79 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  80 */     nglBeginQueryARB(target, id, function_pointer);
/*     */   }
/*     */   static native void nglBeginQueryARB(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glEndQueryARB(int target) {
/*  85 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  86 */     long function_pointer = caps.glEndQueryARB;
/*  87 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  88 */     nglEndQueryARB(target, function_pointer);
/*     */   }
/*     */   static native void nglEndQueryARB(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glGetQueryARB(int target, int pname, IntBuffer params) {
/*  93 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  94 */     long function_pointer = caps.glGetQueryivARB;
/*  95 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  96 */     BufferChecks.checkBuffer(params, 1);
/*  97 */     nglGetQueryivARB(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetQueryivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   @Deprecated
/*     */   public static int glGetQueryARB(int target, int pname)
/*     */   {
/* 108 */     return glGetQueryiARB(target, pname);
/*     */   }
/*     */ 
/*     */   public static int glGetQueryiARB(int target, int pname)
/*     */   {
/* 113 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 114 */     long function_pointer = caps.glGetQueryivARB;
/* 115 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 116 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 117 */     nglGetQueryivARB(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 118 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetQueryObjectARB(int id, int pname, IntBuffer params) {
/* 122 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 123 */     long function_pointer = caps.glGetQueryObjectivARB;
/* 124 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 125 */     BufferChecks.checkBuffer(params, 1);
/* 126 */     nglGetQueryObjectivARB(id, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetQueryObjectivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetQueryObjectiARB(int id, int pname) {
/* 132 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 133 */     long function_pointer = caps.glGetQueryObjectivARB;
/* 134 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 135 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 136 */     nglGetQueryObjectivARB(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 137 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetQueryObjectuARB(int id, int pname, IntBuffer params) {
/* 141 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 142 */     long function_pointer = caps.glGetQueryObjectuivARB;
/* 143 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 144 */     BufferChecks.checkBuffer(params, 1);
/* 145 */     nglGetQueryObjectuivARB(id, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetQueryObjectuivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetQueryObjectuiARB(int id, int pname) {
/* 151 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 152 */     long function_pointer = caps.glGetQueryObjectuivARB;
/* 153 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 154 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 155 */     nglGetQueryObjectuivARB(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 156 */     return params.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBOcclusionQuery
 * JD-Core Version:    0.6.2
 */