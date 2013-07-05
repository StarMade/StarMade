/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.DoubleBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class EXTVertexAttrib64bit
/*     */ {
/*     */   public static final int GL_DOUBLE_VEC2_EXT = 36860;
/*     */   public static final int GL_DOUBLE_VEC3_EXT = 36861;
/*     */   public static final int GL_DOUBLE_VEC4_EXT = 36862;
/*     */   public static final int GL_DOUBLE_MAT2_EXT = 36678;
/*     */   public static final int GL_DOUBLE_MAT3_EXT = 36679;
/*     */   public static final int GL_DOUBLE_MAT4_EXT = 36680;
/*     */   public static final int GL_DOUBLE_MAT2x3_EXT = 36681;
/*     */   public static final int GL_DOUBLE_MAT2x4_EXT = 36682;
/*     */   public static final int GL_DOUBLE_MAT3x2_EXT = 36683;
/*     */   public static final int GL_DOUBLE_MAT3x4_EXT = 36684;
/*     */   public static final int GL_DOUBLE_MAT4x2_EXT = 36685;
/*     */   public static final int GL_DOUBLE_MAT4x3_EXT = 36686;
/*     */ 
/*     */   public static void glVertexAttribL1dEXT(int index, double x)
/*     */   {
/*  29 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  30 */     long function_pointer = caps.glVertexAttribL1dEXT;
/*  31 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  32 */     nglVertexAttribL1dEXT(index, x, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL1dEXT(int paramInt, double paramDouble, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribL2dEXT(int index, double x, double y) {
/*  37 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  38 */     long function_pointer = caps.glVertexAttribL2dEXT;
/*  39 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  40 */     nglVertexAttribL2dEXT(index, x, y, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL2dEXT(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribL3dEXT(int index, double x, double y, double z) {
/*  45 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  46 */     long function_pointer = caps.glVertexAttribL3dEXT;
/*  47 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  48 */     nglVertexAttribL3dEXT(index, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL3dEXT(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribL4dEXT(int index, double x, double y, double z, double w) {
/*  53 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  54 */     long function_pointer = caps.glVertexAttribL4dEXT;
/*  55 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  56 */     nglVertexAttribL4dEXT(index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL4dEXT(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribL1EXT(int index, DoubleBuffer v) {
/*  61 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  62 */     long function_pointer = caps.glVertexAttribL1dvEXT;
/*  63 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  64 */     BufferChecks.checkBuffer(v, 1);
/*  65 */     nglVertexAttribL1dvEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL1dvEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribL2EXT(int index, DoubleBuffer v) {
/*  70 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  71 */     long function_pointer = caps.glVertexAttribL2dvEXT;
/*  72 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  73 */     BufferChecks.checkBuffer(v, 2);
/*  74 */     nglVertexAttribL2dvEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL2dvEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribL3EXT(int index, DoubleBuffer v) {
/*  79 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  80 */     long function_pointer = caps.glVertexAttribL3dvEXT;
/*  81 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  82 */     BufferChecks.checkBuffer(v, 3);
/*  83 */     nglVertexAttribL3dvEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL3dvEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribL4EXT(int index, DoubleBuffer v) {
/*  88 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  89 */     long function_pointer = caps.glVertexAttribL4dvEXT;
/*  90 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  91 */     BufferChecks.checkBuffer(v, 4);
/*  92 */     nglVertexAttribL4dvEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribL4dvEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribLPointerEXT(int index, int size, int stride, DoubleBuffer pointer) {
/*  97 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  98 */     long function_pointer = caps.glVertexAttribLPointerEXT;
/*  99 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 100 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 101 */     BufferChecks.checkDirect(pointer);
/* 102 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = pointer;
/* 103 */     nglVertexAttribLPointerEXT(index, size, 5130, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribLPointerEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*     */ 
/* 107 */   public static void glVertexAttribLPointerEXT(int index, int size, int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 108 */     long function_pointer = caps.glVertexAttribLPointerEXT;
/* 109 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 110 */     GLChecks.ensureArrayVBOenabled(caps);
/* 111 */     nglVertexAttribLPointerEXTBO(index, size, 5130, stride, pointer_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglVertexAttribLPointerEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetVertexAttribLEXT(int index, int pname, DoubleBuffer params) {
/* 116 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 117 */     long function_pointer = caps.glGetVertexAttribLdvEXT;
/* 118 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 119 */     BufferChecks.checkBuffer(params, 4);
/* 120 */     nglGetVertexAttribLdvEXT(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetVertexAttribLdvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexArrayVertexAttribLOffsetEXT(int vaobj, int buffer, int index, int size, int type, int stride, long offset) {
/* 125 */     ARBVertexAttrib64bit.glVertexArrayVertexAttribLOffsetEXT(vaobj, buffer, index, size, type, stride, offset);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTVertexAttrib64bit
 * JD-Core Version:    0.6.2
 */