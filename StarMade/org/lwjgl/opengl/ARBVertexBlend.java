/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class ARBVertexBlend
/*     */ {
/*     */   public static final int GL_MAX_VERTEX_UNITS_ARB = 34468;
/*     */   public static final int GL_ACTIVE_VERTEX_UNITS_ARB = 34469;
/*     */   public static final int GL_WEIGHT_SUM_UNITY_ARB = 34470;
/*     */   public static final int GL_VERTEX_BLEND_ARB = 34471;
/*     */   public static final int GL_CURRENT_WEIGHT_ARB = 34472;
/*     */   public static final int GL_WEIGHT_ARRAY_TYPE_ARB = 34473;
/*     */   public static final int GL_WEIGHT_ARRAY_STRIDE_ARB = 34474;
/*     */   public static final int GL_WEIGHT_ARRAY_SIZE_ARB = 34475;
/*     */   public static final int GL_WEIGHT_ARRAY_POINTER_ARB = 34476;
/*     */   public static final int GL_WEIGHT_ARRAY_ARB = 34477;
/*     */   public static final int GL_MODELVIEW0_ARB = 5888;
/*     */   public static final int GL_MODELVIEW1_ARB = 34058;
/*     */   public static final int GL_MODELVIEW2_ARB = 34594;
/*     */   public static final int GL_MODELVIEW3_ARB = 34595;
/*     */   public static final int GL_MODELVIEW4_ARB = 34596;
/*     */   public static final int GL_MODELVIEW5_ARB = 34597;
/*     */   public static final int GL_MODELVIEW6_ARB = 34598;
/*     */   public static final int GL_MODELVIEW7_ARB = 34599;
/*     */   public static final int GL_MODELVIEW8_ARB = 34600;
/*     */   public static final int GL_MODELVIEW9_ARB = 34601;
/*     */   public static final int GL_MODELVIEW10_ARB = 34602;
/*     */   public static final int GL_MODELVIEW11_ARB = 34603;
/*     */   public static final int GL_MODELVIEW12_ARB = 34604;
/*     */   public static final int GL_MODELVIEW13_ARB = 34605;
/*     */   public static final int GL_MODELVIEW14_ARB = 34606;
/*     */   public static final int GL_MODELVIEW15_ARB = 34607;
/*     */   public static final int GL_MODELVIEW16_ARB = 34608;
/*     */   public static final int GL_MODELVIEW17_ARB = 34609;
/*     */   public static final int GL_MODELVIEW18_ARB = 34610;
/*     */   public static final int GL_MODELVIEW19_ARB = 34611;
/*     */   public static final int GL_MODELVIEW20_ARB = 34612;
/*     */   public static final int GL_MODELVIEW21_ARB = 34613;
/*     */   public static final int GL_MODELVIEW22_ARB = 34614;
/*     */   public static final int GL_MODELVIEW23_ARB = 34615;
/*     */   public static final int GL_MODELVIEW24_ARB = 34616;
/*     */   public static final int GL_MODELVIEW25_ARB = 34617;
/*     */   public static final int GL_MODELVIEW26_ARB = 34618;
/*     */   public static final int GL_MODELVIEW27_ARB = 34619;
/*     */   public static final int GL_MODELVIEW28_ARB = 34620;
/*     */   public static final int GL_MODELVIEW29_ARB = 34621;
/*     */   public static final int GL_MODELVIEW30_ARB = 34622;
/*     */   public static final int GL_MODELVIEW31_ARB = 34623;
/*     */ 
/*     */   public static void glWeightARB(ByteBuffer pWeights)
/*     */   {
/*  56 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  57 */     long function_pointer = caps.glWeightbvARB;
/*  58 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  59 */     BufferChecks.checkDirect(pWeights);
/*  60 */     nglWeightbvARB(pWeights.remaining(), MemoryUtil.getAddress(pWeights), function_pointer);
/*     */   }
/*     */   static native void nglWeightbvARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glWeightARB(ShortBuffer pWeights) {
/*  65 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  66 */     long function_pointer = caps.glWeightsvARB;
/*  67 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  68 */     BufferChecks.checkDirect(pWeights);
/*  69 */     nglWeightsvARB(pWeights.remaining(), MemoryUtil.getAddress(pWeights), function_pointer);
/*     */   }
/*     */   static native void nglWeightsvARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glWeightARB(IntBuffer pWeights) {
/*  74 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  75 */     long function_pointer = caps.glWeightivARB;
/*  76 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  77 */     BufferChecks.checkDirect(pWeights);
/*  78 */     nglWeightivARB(pWeights.remaining(), MemoryUtil.getAddress(pWeights), function_pointer);
/*     */   }
/*     */   static native void nglWeightivARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glWeightARB(FloatBuffer pWeights) {
/*  83 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  84 */     long function_pointer = caps.glWeightfvARB;
/*  85 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  86 */     BufferChecks.checkDirect(pWeights);
/*  87 */     nglWeightfvARB(pWeights.remaining(), MemoryUtil.getAddress(pWeights), function_pointer);
/*     */   }
/*     */   static native void nglWeightfvARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glWeightARB(DoubleBuffer pWeights) {
/*  92 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  93 */     long function_pointer = caps.glWeightdvARB;
/*  94 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  95 */     BufferChecks.checkDirect(pWeights);
/*  96 */     nglWeightdvARB(pWeights.remaining(), MemoryUtil.getAddress(pWeights), function_pointer);
/*     */   }
/*     */   static native void nglWeightdvARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glWeightuARB(ByteBuffer pWeights) {
/* 101 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 102 */     long function_pointer = caps.glWeightubvARB;
/* 103 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 104 */     BufferChecks.checkDirect(pWeights);
/* 105 */     nglWeightubvARB(pWeights.remaining(), MemoryUtil.getAddress(pWeights), function_pointer);
/*     */   }
/*     */   static native void nglWeightubvARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glWeightuARB(ShortBuffer pWeights) {
/* 110 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 111 */     long function_pointer = caps.glWeightusvARB;
/* 112 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 113 */     BufferChecks.checkDirect(pWeights);
/* 114 */     nglWeightusvARB(pWeights.remaining(), MemoryUtil.getAddress(pWeights), function_pointer);
/*     */   }
/*     */   static native void nglWeightusvARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glWeightuARB(IntBuffer pWeights) {
/* 119 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 120 */     long function_pointer = caps.glWeightuivARB;
/* 121 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 122 */     BufferChecks.checkDirect(pWeights);
/* 123 */     nglWeightuivARB(pWeights.remaining(), MemoryUtil.getAddress(pWeights), function_pointer);
/*     */   }
/*     */   static native void nglWeightuivARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glWeightPointerARB(int size, int stride, DoubleBuffer pPointer) {
/* 128 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 129 */     long function_pointer = caps.glWeightPointerARB;
/* 130 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 131 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 132 */     BufferChecks.checkDirect(pPointer);
/* 133 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).ARB_vertex_blend_glWeightPointerARB_pPointer = pPointer;
/* 134 */     nglWeightPointerARB(size, 5130, stride, MemoryUtil.getAddress(pPointer), function_pointer);
/*     */   }
/*     */   public static void glWeightPointerARB(int size, int stride, FloatBuffer pPointer) {
/* 137 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 138 */     long function_pointer = caps.glWeightPointerARB;
/* 139 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 140 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 141 */     BufferChecks.checkDirect(pPointer);
/* 142 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).ARB_vertex_blend_glWeightPointerARB_pPointer = pPointer;
/* 143 */     nglWeightPointerARB(size, 5126, stride, MemoryUtil.getAddress(pPointer), function_pointer);
/*     */   }
/*     */   public static void glWeightPointerARB(int size, boolean unsigned, int stride, ByteBuffer pPointer) {
/* 146 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 147 */     long function_pointer = caps.glWeightPointerARB;
/* 148 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 149 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 150 */     BufferChecks.checkDirect(pPointer);
/* 151 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).ARB_vertex_blend_glWeightPointerARB_pPointer = pPointer;
/* 152 */     nglWeightPointerARB(size, unsigned ? 5121 : 5120, stride, MemoryUtil.getAddress(pPointer), function_pointer);
/*     */   }
/*     */   public static void glWeightPointerARB(int size, boolean unsigned, int stride, IntBuffer pPointer) {
/* 155 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 156 */     long function_pointer = caps.glWeightPointerARB;
/* 157 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 158 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 159 */     BufferChecks.checkDirect(pPointer);
/* 160 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).ARB_vertex_blend_glWeightPointerARB_pPointer = pPointer;
/* 161 */     nglWeightPointerARB(size, unsigned ? 5125 : 5124, stride, MemoryUtil.getAddress(pPointer), function_pointer);
/*     */   }
/*     */   public static void glWeightPointerARB(int size, boolean unsigned, int stride, ShortBuffer pPointer) {
/* 164 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 165 */     long function_pointer = caps.glWeightPointerARB;
/* 166 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 167 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 168 */     BufferChecks.checkDirect(pPointer);
/* 169 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).ARB_vertex_blend_glWeightPointerARB_pPointer = pPointer;
/* 170 */     nglWeightPointerARB(size, unsigned ? 5123 : 5122, stride, MemoryUtil.getAddress(pPointer), function_pointer);
/*     */   }
/*     */   static native void nglWeightPointerARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/* 174 */   public static void glWeightPointerARB(int size, int type, int stride, long pPointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 175 */     long function_pointer = caps.glWeightPointerARB;
/* 176 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 177 */     GLChecks.ensureArrayVBOenabled(caps);
/* 178 */     nglWeightPointerARBBO(size, type, stride, pPointer_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglWeightPointerARBBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexBlendARB(int count) {
/* 183 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 184 */     long function_pointer = caps.glVertexBlendARB;
/* 185 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 186 */     nglVertexBlendARB(count, function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglVertexBlendARB(int paramInt, long paramLong);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBVertexBlend
 * JD-Core Version:    0.6.2
 */