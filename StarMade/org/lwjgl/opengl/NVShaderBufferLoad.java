/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.LongBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class NVShaderBufferLoad
/*     */ {
/*     */   public static final int GL_BUFFER_GPU_ADDRESS_NV = 36637;
/*     */   public static final int GL_GPU_ADDRESS_NV = 36660;
/*     */   public static final int GL_MAX_SHADER_BUFFER_ADDRESS_NV = 36661;
/*     */ 
/*     */   public static void glMakeBufferResidentNV(int target, int access)
/*     */   {
/*  29 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  30 */     long function_pointer = caps.glMakeBufferResidentNV;
/*  31 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  32 */     nglMakeBufferResidentNV(target, access, function_pointer);
/*     */   }
/*     */   static native void nglMakeBufferResidentNV(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glMakeBufferNonResidentNV(int target) {
/*  37 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  38 */     long function_pointer = caps.glMakeBufferNonResidentNV;
/*  39 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  40 */     nglMakeBufferNonResidentNV(target, function_pointer);
/*     */   }
/*     */   static native void nglMakeBufferNonResidentNV(int paramInt, long paramLong);
/*     */ 
/*     */   public static boolean glIsBufferResidentNV(int target) {
/*  45 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  46 */     long function_pointer = caps.glIsBufferResidentNV;
/*  47 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  48 */     boolean __result = nglIsBufferResidentNV(target, function_pointer);
/*  49 */     return __result;
/*     */   }
/*     */   static native boolean nglIsBufferResidentNV(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glMakeNamedBufferResidentNV(int buffer, int access) {
/*  54 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  55 */     long function_pointer = caps.glMakeNamedBufferResidentNV;
/*  56 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  57 */     nglMakeNamedBufferResidentNV(buffer, access, function_pointer);
/*     */   }
/*     */   static native void nglMakeNamedBufferResidentNV(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glMakeNamedBufferNonResidentNV(int buffer) {
/*  62 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  63 */     long function_pointer = caps.glMakeNamedBufferNonResidentNV;
/*  64 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  65 */     nglMakeNamedBufferNonResidentNV(buffer, function_pointer);
/*     */   }
/*     */   static native void nglMakeNamedBufferNonResidentNV(int paramInt, long paramLong);
/*     */ 
/*     */   public static boolean glIsNamedBufferResidentNV(int buffer) {
/*  70 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  71 */     long function_pointer = caps.glIsNamedBufferResidentNV;
/*  72 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  73 */     boolean __result = nglIsNamedBufferResidentNV(buffer, function_pointer);
/*  74 */     return __result;
/*     */   }
/*     */   static native boolean nglIsNamedBufferResidentNV(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glGetBufferParameteruNV(int target, int pname, LongBuffer params) {
/*  79 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  80 */     long function_pointer = caps.glGetBufferParameterui64vNV;
/*  81 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  82 */     BufferChecks.checkBuffer(params, 1);
/*  83 */     nglGetBufferParameterui64vNV(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetBufferParameterui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static long glGetBufferParameterui64NV(int target, int pname) {
/*  89 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  90 */     long function_pointer = caps.glGetBufferParameterui64vNV;
/*  91 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  92 */     LongBuffer params = APIUtil.getBufferLong(caps);
/*  93 */     nglGetBufferParameterui64vNV(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  94 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetNamedBufferParameteruNV(int buffer, int pname, LongBuffer params) {
/*  98 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  99 */     long function_pointer = caps.glGetNamedBufferParameterui64vNV;
/* 100 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 101 */     BufferChecks.checkBuffer(params, 1);
/* 102 */     nglGetNamedBufferParameterui64vNV(buffer, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetNamedBufferParameterui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static long glGetNamedBufferParameterui64NV(int buffer, int pname) {
/* 108 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 109 */     long function_pointer = caps.glGetNamedBufferParameterui64vNV;
/* 110 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 111 */     LongBuffer params = APIUtil.getBufferLong(caps);
/* 112 */     nglGetNamedBufferParameterui64vNV(buffer, pname, MemoryUtil.getAddress(params), function_pointer);
/* 113 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetIntegeruNV(int value, LongBuffer result) {
/* 117 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 118 */     long function_pointer = caps.glGetIntegerui64vNV;
/* 119 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 120 */     BufferChecks.checkBuffer(result, 1);
/* 121 */     nglGetIntegerui64vNV(value, MemoryUtil.getAddress(result), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetIntegerui64vNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static long glGetIntegerui64NV(int value) {
/* 127 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 128 */     long function_pointer = caps.glGetIntegerui64vNV;
/* 129 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 130 */     LongBuffer result = APIUtil.getBufferLong(caps);
/* 131 */     nglGetIntegerui64vNV(value, MemoryUtil.getAddress(result), function_pointer);
/* 132 */     return result.get(0);
/*     */   }
/*     */ 
/*     */   public static void glUniformui64NV(int location, long value) {
/* 136 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 137 */     long function_pointer = caps.glUniformui64NV;
/* 138 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 139 */     nglUniformui64NV(location, value, function_pointer);
/*     */   }
/*     */   static native void nglUniformui64NV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniformuNV(int location, LongBuffer value) {
/* 144 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 145 */     long function_pointer = caps.glUniformui64vNV;
/* 146 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 147 */     BufferChecks.checkDirect(value);
/* 148 */     nglUniformui64vNV(location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniformui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetUniformuNV(int program, int location, LongBuffer params) {
/* 153 */     NVGpuShader5.glGetUniformuNV(program, location, params);
/*     */   }
/*     */ 
/*     */   public static void glProgramUniformui64NV(int program, int location, long value) {
/* 157 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 158 */     long function_pointer = caps.glProgramUniformui64NV;
/* 159 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 160 */     nglProgramUniformui64NV(program, location, value, function_pointer);
/*     */   }
/*     */   static native void nglProgramUniformui64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniformuNV(int program, int location, LongBuffer value) {
/* 165 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 166 */     long function_pointer = caps.glProgramUniformui64vNV;
/* 167 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 168 */     BufferChecks.checkDirect(value);
/* 169 */     nglProgramUniformui64vNV(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglProgramUniformui64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVShaderBufferLoad
 * JD-Core Version:    0.6.2
 */