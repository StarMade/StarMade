/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.LongBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class NVBindlessTexture
/*     */ {
/*     */   public static long glGetTextureHandleNV(int texture)
/*     */   {
/*  13 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  14 */     long function_pointer = caps.glGetTextureHandleNV;
/*  15 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  16 */     long __result = nglGetTextureHandleNV(texture, function_pointer);
/*  17 */     return __result;
/*     */   }
/*     */   static native long nglGetTextureHandleNV(int paramInt, long paramLong);
/*     */ 
/*     */   public static long glGetTextureSamplerHandleNV(int texture, int sampler) {
/*  22 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  23 */     long function_pointer = caps.glGetTextureSamplerHandleNV;
/*  24 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  25 */     long __result = nglGetTextureSamplerHandleNV(texture, sampler, function_pointer);
/*  26 */     return __result;
/*     */   }
/*     */   static native long nglGetTextureSamplerHandleNV(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glMakeTextureHandleResidentNV(long handle) {
/*  31 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  32 */     long function_pointer = caps.glMakeTextureHandleResidentNV;
/*  33 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  34 */     nglMakeTextureHandleResidentNV(handle, function_pointer);
/*     */   }
/*     */   static native void nglMakeTextureHandleResidentNV(long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glMakeTextureHandleNonResidentNV(long handle) {
/*  39 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  40 */     long function_pointer = caps.glMakeTextureHandleNonResidentNV;
/*  41 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  42 */     nglMakeTextureHandleNonResidentNV(handle, function_pointer);
/*     */   }
/*     */   static native void nglMakeTextureHandleNonResidentNV(long paramLong1, long paramLong2);
/*     */ 
/*     */   public static long glGetImageHandleNV(int texture, int level, boolean layered, int layer, int format) {
/*  47 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  48 */     long function_pointer = caps.glGetImageHandleNV;
/*  49 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  50 */     long __result = nglGetImageHandleNV(texture, level, layered, layer, format, function_pointer);
/*  51 */     return __result;
/*     */   }
/*     */   static native long nglGetImageHandleNV(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glMakeImageHandleResidentNV(long handle, int access) {
/*  56 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  57 */     long function_pointer = caps.glMakeImageHandleResidentNV;
/*  58 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  59 */     nglMakeImageHandleResidentNV(handle, access, function_pointer);
/*     */   }
/*     */   static native void nglMakeImageHandleResidentNV(long paramLong1, int paramInt, long paramLong2);
/*     */ 
/*     */   public static void glMakeImageHandleNonResidentNV(long handle) {
/*  64 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  65 */     long function_pointer = caps.glMakeImageHandleNonResidentNV;
/*  66 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  67 */     nglMakeImageHandleNonResidentNV(handle, function_pointer);
/*     */   }
/*     */   static native void nglMakeImageHandleNonResidentNV(long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniformHandleui64NV(int location, long value) {
/*  72 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  73 */     long function_pointer = caps.glUniformHandleui64NV;
/*  74 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  75 */     nglUniformHandleui64NV(location, value, function_pointer);
/*     */   }
/*     */   static native void nglUniformHandleui64NV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniformHandleuNV(int location, LongBuffer value) {
/*  80 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  81 */     long function_pointer = caps.glUniformHandleui64vNV;
/*  82 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  83 */     BufferChecks.checkDirect(value);
/*  84 */     nglUniformHandleui64vNV(location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniformHandleui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniformHandleui64NV(int program, int location, long value) {
/*  89 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  90 */     long function_pointer = caps.glProgramUniformHandleui64NV;
/*  91 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  92 */     nglProgramUniformHandleui64NV(program, location, value, function_pointer);
/*     */   }
/*     */   static native void nglProgramUniformHandleui64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniformHandleuNV(int program, int location, LongBuffer values) {
/*  97 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  98 */     long function_pointer = caps.glProgramUniformHandleui64vNV;
/*  99 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 100 */     BufferChecks.checkDirect(values);
/* 101 */     nglProgramUniformHandleui64vNV(program, location, values.remaining(), MemoryUtil.getAddress(values), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniformHandleui64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static boolean glIsTextureHandleResidentNV(long handle) {
/* 106 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 107 */     long function_pointer = caps.glIsTextureHandleResidentNV;
/* 108 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 109 */     boolean __result = nglIsTextureHandleResidentNV(handle, function_pointer);
/* 110 */     return __result;
/*     */   }
/*     */   static native boolean nglIsTextureHandleResidentNV(long paramLong1, long paramLong2);
/*     */ 
/*     */   public static boolean glIsImageHandleResidentNV(long handle) {
/* 115 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 116 */     long function_pointer = caps.glIsImageHandleResidentNV;
/* 117 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 118 */     boolean __result = nglIsImageHandleResidentNV(handle, function_pointer);
/* 119 */     return __result;
/*     */   }
/*     */ 
/*     */   static native boolean nglIsImageHandleResidentNV(long paramLong1, long paramLong2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVBindlessTexture
 * JD-Core Version:    0.6.2
 */