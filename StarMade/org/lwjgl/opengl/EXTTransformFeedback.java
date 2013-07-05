/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class EXTTransformFeedback
/*     */ {
/*     */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_EXT = 35982;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_START_EXT = 35972;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_SIZE_EXT = 35973;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_BINDING_EXT = 35983;
/*     */   public static final int GL_INTERLEAVED_ATTRIBS_EXT = 35980;
/*     */   public static final int GL_SEPARATE_ATTRIBS_EXT = 35981;
/*     */   public static final int GL_PRIMITIVES_GENERATED_EXT = 35975;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN_EXT = 35976;
/*     */   public static final int GL_RASTERIZER_DISCARD_EXT = 35977;
/*     */   public static final int GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS_EXT = 35978;
/*     */   public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS_EXT = 35979;
/*     */   public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS_EXT = 35968;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_VARYINGS_EXT = 35971;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_MODE_EXT = 35967;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH_EXT = 35958;
/*     */ 
/*     */   public static void glBindBufferRangeEXT(int target, int index, int buffer, long offset, long size)
/*     */   {
/*  70 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  71 */     long function_pointer = caps.glBindBufferRangeEXT;
/*  72 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  73 */     nglBindBufferRangeEXT(target, index, buffer, offset, size, function_pointer);
/*     */   }
/*     */   static native void nglBindBufferRangeEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glBindBufferOffsetEXT(int target, int index, int buffer, long offset) {
/*  78 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  79 */     long function_pointer = caps.glBindBufferOffsetEXT;
/*  80 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  81 */     nglBindBufferOffsetEXT(target, index, buffer, offset, function_pointer);
/*     */   }
/*     */   static native void nglBindBufferOffsetEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glBindBufferBaseEXT(int target, int index, int buffer) {
/*  86 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  87 */     long function_pointer = caps.glBindBufferBaseEXT;
/*  88 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  89 */     nglBindBufferBaseEXT(target, index, buffer, function_pointer);
/*     */   }
/*     */   static native void nglBindBufferBaseEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glBeginTransformFeedbackEXT(int primitiveMode) {
/*  94 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  95 */     long function_pointer = caps.glBeginTransformFeedbackEXT;
/*  96 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  97 */     nglBeginTransformFeedbackEXT(primitiveMode, function_pointer);
/*     */   }
/*     */   static native void nglBeginTransformFeedbackEXT(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glEndTransformFeedbackEXT() {
/* 102 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 103 */     long function_pointer = caps.glEndTransformFeedbackEXT;
/* 104 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 105 */     nglEndTransformFeedbackEXT(function_pointer);
/*     */   }
/*     */   static native void nglEndTransformFeedbackEXT(long paramLong);
/*     */ 
/*     */   public static void glTransformFeedbackVaryingsEXT(int program, int count, ByteBuffer varyings, int bufferMode) {
/* 110 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 111 */     long function_pointer = caps.glTransformFeedbackVaryingsEXT;
/* 112 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 113 */     BufferChecks.checkDirect(varyings);
/* 114 */     BufferChecks.checkNullTerminated(varyings, count);
/* 115 */     nglTransformFeedbackVaryingsEXT(program, count, MemoryUtil.getAddress(varyings), bufferMode, function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglTransformFeedbackVaryingsEXT(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2);
/*     */ 
/*     */   public static void glTransformFeedbackVaryingsEXT(int program, CharSequence[] varyings, int bufferMode) {
/* 121 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 122 */     long function_pointer = caps.glTransformFeedbackVaryingsEXT;
/* 123 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 124 */     BufferChecks.checkArray(varyings);
/* 125 */     nglTransformFeedbackVaryingsEXT(program, varyings.length, APIUtil.getBufferNT(caps, varyings), bufferMode, function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glGetTransformFeedbackVaryingEXT(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
/* 129 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 130 */     long function_pointer = caps.glGetTransformFeedbackVaryingEXT;
/* 131 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 132 */     if (length != null)
/* 133 */       BufferChecks.checkBuffer(length, 1);
/* 134 */     BufferChecks.checkBuffer(size, 1);
/* 135 */     BufferChecks.checkBuffer(type, 1);
/* 136 */     BufferChecks.checkDirect(name);
/* 137 */     nglGetTransformFeedbackVaryingEXT(program, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetTransformFeedbackVaryingEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*     */ 
/*     */   public static String glGetTransformFeedbackVaryingEXT(int program, int index, int bufSize, IntBuffer size, IntBuffer type) {
/* 143 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 144 */     long function_pointer = caps.glGetTransformFeedbackVaryingEXT;
/* 145 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 146 */     BufferChecks.checkBuffer(size, 1);
/* 147 */     BufferChecks.checkBuffer(type, 1);
/* 148 */     IntBuffer name_length = APIUtil.getLengths(caps);
/* 149 */     ByteBuffer name = APIUtil.getBufferByte(caps, bufSize);
/* 150 */     nglGetTransformFeedbackVaryingEXT(program, index, bufSize, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/* 151 */     name.limit(name_length.get(0));
/* 152 */     return APIUtil.getString(caps, name);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTTransformFeedback
 * JD-Core Version:    0.6.2
 */