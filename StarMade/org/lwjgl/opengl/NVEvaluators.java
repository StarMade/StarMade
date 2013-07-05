/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class NVEvaluators
/*     */ {
/*     */   public static final int GL_EVAL_2D_NV = 34496;
/*     */   public static final int GL_EVAL_TRIANGULAR_2D_NV = 34497;
/*     */   public static final int GL_MAP_TESSELLATION_NV = 34498;
/*     */   public static final int GL_MAP_ATTRIB_U_ORDER_NV = 34499;
/*     */   public static final int GL_MAP_ATTRIB_V_ORDER_NV = 34500;
/*     */   public static final int GL_EVAL_FRACTIONAL_TESSELLATION_NV = 34501;
/*     */   public static final int GL_EVAL_VERTEX_ATTRIB0_NV = 34502;
/*     */   public static final int GL_EVAL_VERTEX_ATTRIB1_NV = 34503;
/*     */   public static final int GL_EVAL_VERTEX_ATTRIB2_NV = 34504;
/*     */   public static final int GL_EVAL_VERTEX_ATTRIB3_NV = 34505;
/*     */   public static final int GL_EVAL_VERTEX_ATTRIB4_NV = 34506;
/*     */   public static final int GL_EVAL_VERTEX_ATTRIB5_NV = 34507;
/*     */   public static final int GL_EVAL_VERTEX_ATTRIB6_NV = 34508;
/*     */   public static final int GL_EVAL_VERTEX_ATTRIB7_NV = 34509;
/*     */   public static final int GL_EVAL_VERTEX_ATTRIB8_NV = 34510;
/*     */   public static final int GL_EVAL_VERTEX_ATTRIB9_NV = 34511;
/*     */   public static final int GL_EVAL_VERTEX_ATTRIB10_NV = 34512;
/*     */   public static final int GL_EVAL_VERTEX_ATTRIB11_NV = 34513;
/*     */   public static final int GL_EVAL_VERTEX_ATTRIB12_NV = 34514;
/*     */   public static final int GL_EVAL_VERTEX_ATTRIB13_NV = 34515;
/*     */   public static final int GL_EVAL_VERTEX_ATTRIB14_NV = 34516;
/*     */   public static final int GL_EVAL_VERTEX_ATTRIB15_NV = 34517;
/*     */   public static final int GL_MAX_MAP_TESSELLATION_NV = 34518;
/*     */   public static final int GL_MAX_RATIONAL_EVAL_ORDER_NV = 34519;
/*     */ 
/*     */   public static void glGetMapControlPointsNV(int target, int index, int type, int ustride, int vstride, boolean packed, FloatBuffer pPoints)
/*     */   {
/*  38 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  39 */     long function_pointer = caps.glGetMapControlPointsNV;
/*  40 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  41 */     BufferChecks.checkDirect(pPoints);
/*  42 */     nglGetMapControlPointsNV(target, index, type, ustride, vstride, packed, MemoryUtil.getAddress(pPoints), function_pointer);
/*     */   }
/*     */   static native void nglGetMapControlPointsNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glMapControlPointsNV(int target, int index, int type, int ustride, int vstride, int uorder, int vorder, boolean packed, FloatBuffer pPoints) {
/*  47 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  48 */     long function_pointer = caps.glMapControlPointsNV;
/*  49 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  50 */     BufferChecks.checkDirect(pPoints);
/*  51 */     nglMapControlPointsNV(target, index, type, ustride, vstride, uorder, vorder, packed, MemoryUtil.getAddress(pPoints), function_pointer);
/*     */   }
/*     */   static native void nglMapControlPointsNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glMapParameterNV(int target, int pname, FloatBuffer params) {
/*  56 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  57 */     long function_pointer = caps.glMapParameterfvNV;
/*  58 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  59 */     BufferChecks.checkBuffer(params, 4);
/*  60 */     nglMapParameterfvNV(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglMapParameterfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glMapParameterNV(int target, int pname, IntBuffer params) {
/*  65 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  66 */     long function_pointer = caps.glMapParameterivNV;
/*  67 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  68 */     BufferChecks.checkBuffer(params, 4);
/*  69 */     nglMapParameterivNV(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglMapParameterivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetMapParameterNV(int target, int pname, FloatBuffer params) {
/*  74 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  75 */     long function_pointer = caps.glGetMapParameterfvNV;
/*  76 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  77 */     BufferChecks.checkBuffer(params, 4);
/*  78 */     nglGetMapParameterfvNV(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetMapParameterfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetMapParameterNV(int target, int pname, IntBuffer params) {
/*  83 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  84 */     long function_pointer = caps.glGetMapParameterivNV;
/*  85 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  86 */     BufferChecks.checkBuffer(params, 4);
/*  87 */     nglGetMapParameterivNV(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetMapParameterivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetMapAttribParameterNV(int target, int index, int pname, FloatBuffer params) {
/*  92 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  93 */     long function_pointer = caps.glGetMapAttribParameterfvNV;
/*  94 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  95 */     BufferChecks.checkBuffer(params, 4);
/*  96 */     nglGetMapAttribParameterfvNV(target, index, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetMapAttribParameterfvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetMapAttribParameterNV(int target, int index, int pname, IntBuffer params) {
/* 101 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 102 */     long function_pointer = caps.glGetMapAttribParameterivNV;
/* 103 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 104 */     BufferChecks.checkBuffer(params, 4);
/* 105 */     nglGetMapAttribParameterivNV(target, index, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetMapAttribParameterivNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glEvalMapsNV(int target, int mode) {
/* 110 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 111 */     long function_pointer = caps.glEvalMapsNV;
/* 112 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 113 */     nglEvalMapsNV(target, mode, function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglEvalMapsNV(int paramInt1, int paramInt2, long paramLong);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVEvaluators
 * JD-Core Version:    0.6.2
 */