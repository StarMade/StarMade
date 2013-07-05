/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class NVGpuProgram4
/*     */ {
/*     */   public static final int GL_PROGRAM_ATTRIB_COMPONENTS_NV = 35078;
/*     */   public static final int GL_PROGRAM_RESULT_COMPONENTS_NV = 35079;
/*     */   public static final int GL_MAX_PROGRAM_ATTRIB_COMPONENTS_NV = 35080;
/*     */   public static final int GL_MAX_PROGRAM_RESULT_COMPONENTS_NV = 35081;
/*     */   public static final int GL_MAX_PROGRAM_GENERIC_ATTRIBS_NV = 36261;
/*     */   public static final int GL_MAX_PROGRAM_GENERIC_RESULTS_NV = 36262;
/*     */ 
/*     */   public static void glProgramLocalParameterI4iNV(int target, int index, int x, int y, int z, int w)
/*     */   {
/*  23 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  24 */     long function_pointer = caps.glProgramLocalParameterI4iNV;
/*  25 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  26 */     nglProgramLocalParameterI4iNV(target, index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglProgramLocalParameterI4iNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*     */ 
/*     */   public static void glProgramLocalParameterI4NV(int target, int index, IntBuffer params) {
/*  31 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  32 */     long function_pointer = caps.glProgramLocalParameterI4ivNV;
/*  33 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  34 */     BufferChecks.checkBuffer(params, 4);
/*  35 */     nglProgramLocalParameterI4ivNV(target, index, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglProgramLocalParameterI4ivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramLocalParametersI4NV(int target, int index, IntBuffer params) {
/*  40 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  41 */     long function_pointer = caps.glProgramLocalParametersI4ivNV;
/*  42 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  43 */     BufferChecks.checkDirect(params);
/*  44 */     nglProgramLocalParametersI4ivNV(target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglProgramLocalParametersI4ivNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramLocalParameterI4uiNV(int target, int index, int x, int y, int z, int w) {
/*  49 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  50 */     long function_pointer = caps.glProgramLocalParameterI4uiNV;
/*  51 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  52 */     nglProgramLocalParameterI4uiNV(target, index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglProgramLocalParameterI4uiNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*     */ 
/*     */   public static void glProgramLocalParameterI4uNV(int target, int index, IntBuffer params) {
/*  57 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  58 */     long function_pointer = caps.glProgramLocalParameterI4uivNV;
/*  59 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  60 */     BufferChecks.checkBuffer(params, 4);
/*  61 */     nglProgramLocalParameterI4uivNV(target, index, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglProgramLocalParameterI4uivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramLocalParametersI4uNV(int target, int index, IntBuffer params) {
/*  66 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  67 */     long function_pointer = caps.glProgramLocalParametersI4uivNV;
/*  68 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  69 */     BufferChecks.checkDirect(params);
/*  70 */     nglProgramLocalParametersI4uivNV(target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglProgramLocalParametersI4uivNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramEnvParameterI4iNV(int target, int index, int x, int y, int z, int w) {
/*  75 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  76 */     long function_pointer = caps.glProgramEnvParameterI4iNV;
/*  77 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  78 */     nglProgramEnvParameterI4iNV(target, index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglProgramEnvParameterI4iNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*     */ 
/*     */   public static void glProgramEnvParameterI4NV(int target, int index, IntBuffer params) {
/*  83 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  84 */     long function_pointer = caps.glProgramEnvParameterI4ivNV;
/*  85 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  86 */     BufferChecks.checkBuffer(params, 4);
/*  87 */     nglProgramEnvParameterI4ivNV(target, index, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglProgramEnvParameterI4ivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramEnvParametersI4NV(int target, int index, IntBuffer params) {
/*  92 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  93 */     long function_pointer = caps.glProgramEnvParametersI4ivNV;
/*  94 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  95 */     BufferChecks.checkDirect(params);
/*  96 */     nglProgramEnvParametersI4ivNV(target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglProgramEnvParametersI4ivNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramEnvParameterI4uiNV(int target, int index, int x, int y, int z, int w) {
/* 101 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 102 */     long function_pointer = caps.glProgramEnvParameterI4uiNV;
/* 103 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 104 */     nglProgramEnvParameterI4uiNV(target, index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglProgramEnvParameterI4uiNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*     */ 
/*     */   public static void glProgramEnvParameterI4uNV(int target, int index, IntBuffer params) {
/* 109 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 110 */     long function_pointer = caps.glProgramEnvParameterI4uivNV;
/* 111 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 112 */     BufferChecks.checkBuffer(params, 4);
/* 113 */     nglProgramEnvParameterI4uivNV(target, index, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglProgramEnvParameterI4uivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramEnvParametersI4uNV(int target, int index, IntBuffer params) {
/* 118 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 119 */     long function_pointer = caps.glProgramEnvParametersI4uivNV;
/* 120 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 121 */     BufferChecks.checkDirect(params);
/* 122 */     nglProgramEnvParametersI4uivNV(target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglProgramEnvParametersI4uivNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetProgramLocalParameterINV(int target, int index, IntBuffer params) {
/* 127 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 128 */     long function_pointer = caps.glGetProgramLocalParameterIivNV;
/* 129 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 130 */     BufferChecks.checkBuffer(params, 4);
/* 131 */     nglGetProgramLocalParameterIivNV(target, index, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetProgramLocalParameterIivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetProgramLocalParameterIuNV(int target, int index, IntBuffer params) {
/* 136 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 137 */     long function_pointer = caps.glGetProgramLocalParameterIuivNV;
/* 138 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 139 */     BufferChecks.checkBuffer(params, 4);
/* 140 */     nglGetProgramLocalParameterIuivNV(target, index, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetProgramLocalParameterIuivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetProgramEnvParameterINV(int target, int index, IntBuffer params) {
/* 145 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 146 */     long function_pointer = caps.glGetProgramEnvParameterIivNV;
/* 147 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 148 */     BufferChecks.checkBuffer(params, 4);
/* 149 */     nglGetProgramEnvParameterIivNV(target, index, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetProgramEnvParameterIivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetProgramEnvParameterIuNV(int target, int index, IntBuffer params) {
/* 154 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 155 */     long function_pointer = caps.glGetProgramEnvParameterIuivNV;
/* 156 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 157 */     BufferChecks.checkBuffer(params, 4);
/* 158 */     nglGetProgramEnvParameterIuivNV(target, index, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetProgramEnvParameterIuivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVGpuProgram4
 * JD-Core Version:    0.6.2
 */