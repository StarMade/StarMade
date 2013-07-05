/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.LongBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class NVVertexAttribInteger64bit
/*     */ {
/*     */   public static final int GL_INT64_NV = 5134;
/*     */   public static final int GL_UNSIGNED_INT64_NV = 5135;
/*     */ 
/*     */   public static void glVertexAttribL1i64NV(int index, long x)
/*     */   {
/*  20 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  21 */     long function_pointer = caps.glVertexAttribL1i64NV;
/*  22 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  23 */     nglVertexAttribL1i64NV(index, x, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL1i64NV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribL2i64NV(int index, long x, long y) {
/*  28 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  29 */     long function_pointer = caps.glVertexAttribL2i64NV;
/*  30 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  31 */     nglVertexAttribL2i64NV(index, x, y, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL2i64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glVertexAttribL3i64NV(int index, long x, long y, long z) {
/*  36 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  37 */     long function_pointer = caps.glVertexAttribL3i64NV;
/*  38 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  39 */     nglVertexAttribL3i64NV(index, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL3i64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static void glVertexAttribL4i64NV(int index, long x, long y, long z, long w) {
/*  44 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  45 */     long function_pointer = caps.glVertexAttribL4i64NV;
/*  46 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  47 */     nglVertexAttribL4i64NV(index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL4i64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*     */ 
/*     */   public static void glVertexAttribL1NV(int index, LongBuffer v) {
/*  52 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  53 */     long function_pointer = caps.glVertexAttribL1i64vNV;
/*  54 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  55 */     BufferChecks.checkBuffer(v, 1);
/*  56 */     nglVertexAttribL1i64vNV(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL1i64vNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribL2NV(int index, LongBuffer v) {
/*  61 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  62 */     long function_pointer = caps.glVertexAttribL2i64vNV;
/*  63 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  64 */     BufferChecks.checkBuffer(v, 2);
/*  65 */     nglVertexAttribL2i64vNV(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL2i64vNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribL3NV(int index, LongBuffer v) {
/*  70 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  71 */     long function_pointer = caps.glVertexAttribL3i64vNV;
/*  72 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  73 */     BufferChecks.checkBuffer(v, 3);
/*  74 */     nglVertexAttribL3i64vNV(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL3i64vNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribL4NV(int index, LongBuffer v) {
/*  79 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  80 */     long function_pointer = caps.glVertexAttribL4i64vNV;
/*  81 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  82 */     BufferChecks.checkBuffer(v, 4);
/*  83 */     nglVertexAttribL4i64vNV(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL4i64vNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribL1ui64NV(int index, long x) {
/*  88 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  89 */     long function_pointer = caps.glVertexAttribL1ui64NV;
/*  90 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  91 */     nglVertexAttribL1ui64NV(index, x, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL1ui64NV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribL2ui64NV(int index, long x, long y) {
/*  96 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  97 */     long function_pointer = caps.glVertexAttribL2ui64NV;
/*  98 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  99 */     nglVertexAttribL2ui64NV(index, x, y, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL2ui64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glVertexAttribL3ui64NV(int index, long x, long y, long z) {
/* 104 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 105 */     long function_pointer = caps.glVertexAttribL3ui64NV;
/* 106 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 107 */     nglVertexAttribL3ui64NV(index, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL3ui64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static void glVertexAttribL4ui64NV(int index, long x, long y, long z, long w) {
/* 112 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 113 */     long function_pointer = caps.glVertexAttribL4ui64NV;
/* 114 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 115 */     nglVertexAttribL4ui64NV(index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL4ui64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*     */ 
/*     */   public static void glVertexAttribL1uNV(int index, LongBuffer v) {
/* 120 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 121 */     long function_pointer = caps.glVertexAttribL1ui64vNV;
/* 122 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 123 */     BufferChecks.checkBuffer(v, 1);
/* 124 */     nglVertexAttribL1ui64vNV(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL1ui64vNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribL2uNV(int index, LongBuffer v) {
/* 129 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 130 */     long function_pointer = caps.glVertexAttribL2ui64vNV;
/* 131 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 132 */     BufferChecks.checkBuffer(v, 2);
/* 133 */     nglVertexAttribL2ui64vNV(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL2ui64vNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribL3uNV(int index, LongBuffer v) {
/* 138 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 139 */     long function_pointer = caps.glVertexAttribL3ui64vNV;
/* 140 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 141 */     BufferChecks.checkBuffer(v, 3);
/* 142 */     nglVertexAttribL3ui64vNV(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL3ui64vNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribL4uNV(int index, LongBuffer v) {
/* 147 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 148 */     long function_pointer = caps.glVertexAttribL4ui64vNV;
/* 149 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 150 */     BufferChecks.checkBuffer(v, 4);
/* 151 */     nglVertexAttribL4ui64vNV(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL4ui64vNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetVertexAttribLNV(int index, int pname, LongBuffer params) {
/* 156 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 157 */     long function_pointer = caps.glGetVertexAttribLi64vNV;
/* 158 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 159 */     BufferChecks.checkBuffer(params, 4);
/* 160 */     nglGetVertexAttribLi64vNV(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetVertexAttribLi64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetVertexAttribLuNV(int index, int pname, LongBuffer params) {
/* 165 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 166 */     long function_pointer = caps.glGetVertexAttribLui64vNV;
/* 167 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 168 */     BufferChecks.checkBuffer(params, 4);
/* 169 */     nglGetVertexAttribLui64vNV(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetVertexAttribLui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribLFormatNV(int index, int size, int type, int stride) {
/* 174 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 175 */     long function_pointer = caps.glVertexAttribLFormatNV;
/* 176 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 177 */     nglVertexAttribLFormatNV(index, size, type, stride, function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglVertexAttribLFormatNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVVertexAttribInteger64bit
 * JD-Core Version:    0.6.2
 */