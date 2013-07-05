/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class NVTransformFeedback
/*     */ {
/*     */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_NV = 35982;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_START_NV = 35972;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_SIZE_NV = 35973;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_RECORD_NV = 35974;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_BINDING_NV = 35983;
/*     */   public static final int GL_INTERLEAVED_ATTRIBS_NV = 35980;
/*     */   public static final int GL_SEPARATE_ATTRIBS_NV = 35981;
/*     */   public static final int GL_PRIMITIVES_GENERATED_NV = 35975;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN_NV = 35976;
/*     */   public static final int GL_RASTERIZER_DISCARD_NV = 35977;
/*     */   public static final int GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS_NV = 35978;
/*     */   public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS_NV = 35979;
/*     */   public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS_NV = 35968;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_ATTRIBS_NV = 35966;
/*     */   public static final int GL_ACTIVE_VARYINGS_NV = 35969;
/*     */   public static final int GL_ACTIVE_VARYING_MAX_LENGTH_NV = 35970;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_VARYINGS_NV = 35971;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_MODE_NV = 35967;
/*     */   public static final int GL_BACK_PRIMARY_COLOR_NV = 35959;
/*     */   public static final int GL_BACK_SECONDARY_COLOR_NV = 35960;
/*     */   public static final int GL_TEXTURE_COORD_NV = 35961;
/*     */   public static final int GL_CLIP_DISTANCE_NV = 35962;
/*     */   public static final int GL_VERTEX_ID_NV = 35963;
/*     */   public static final int GL_PRIMITIVE_ID_NV = 35964;
/*     */   public static final int GL_GENERIC_ATTRIB_NV = 35965;
/*     */   public static final int GL_LAYER_NV = 36266;
/*     */ 
/*     */   public static void glBindBufferRangeNV(int target, int index, int buffer, long offset, long size)
/*     */   {
/*  91 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  92 */     long function_pointer = caps.glBindBufferRangeNV;
/*  93 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  94 */     nglBindBufferRangeNV(target, index, buffer, offset, size, function_pointer);
/*     */   }
/*     */   static native void nglBindBufferRangeNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glBindBufferOffsetNV(int target, int index, int buffer, long offset) {
/*  99 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 100 */     long function_pointer = caps.glBindBufferOffsetNV;
/* 101 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 102 */     nglBindBufferOffsetNV(target, index, buffer, offset, function_pointer);
/*     */   }
/*     */   static native void nglBindBufferOffsetNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glBindBufferBaseNV(int target, int index, int buffer) {
/* 107 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 108 */     long function_pointer = caps.glBindBufferBaseNV;
/* 109 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 110 */     nglBindBufferBaseNV(target, index, buffer, function_pointer);
/*     */   }
/*     */   static native void nglBindBufferBaseNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glTransformFeedbackAttribsNV(IntBuffer attribs, int bufferMode) {
/* 115 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 116 */     long function_pointer = caps.glTransformFeedbackAttribsNV;
/* 117 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 118 */     BufferChecks.checkBuffer(attribs, 3);
/* 119 */     nglTransformFeedbackAttribsNV(attribs.remaining() / 3, MemoryUtil.getAddress(attribs), bufferMode, function_pointer);
/*     */   }
/*     */   static native void nglTransformFeedbackAttribsNV(int paramInt1, long paramLong1, int paramInt2, long paramLong2);
/*     */ 
/*     */   public static void glTransformFeedbackVaryingsNV(int program, IntBuffer locations, int bufferMode) {
/* 124 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 125 */     long function_pointer = caps.glTransformFeedbackVaryingsNV;
/* 126 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 127 */     BufferChecks.checkDirect(locations);
/* 128 */     nglTransformFeedbackVaryingsNV(program, locations.remaining(), MemoryUtil.getAddress(locations), bufferMode, function_pointer);
/*     */   }
/*     */   static native void nglTransformFeedbackVaryingsNV(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2);
/*     */ 
/*     */   public static void glBeginTransformFeedbackNV(int primitiveMode) {
/* 133 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 134 */     long function_pointer = caps.glBeginTransformFeedbackNV;
/* 135 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 136 */     nglBeginTransformFeedbackNV(primitiveMode, function_pointer);
/*     */   }
/*     */   static native void nglBeginTransformFeedbackNV(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glEndTransformFeedbackNV() {
/* 141 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 142 */     long function_pointer = caps.glEndTransformFeedbackNV;
/* 143 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 144 */     nglEndTransformFeedbackNV(function_pointer);
/*     */   }
/*     */   static native void nglEndTransformFeedbackNV(long paramLong);
/*     */ 
/*     */   public static int glGetVaryingLocationNV(int program, ByteBuffer name) {
/* 149 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 150 */     long function_pointer = caps.glGetVaryingLocationNV;
/* 151 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 152 */     BufferChecks.checkDirect(name);
/* 153 */     BufferChecks.checkNullTerminated(name);
/* 154 */     int __result = nglGetVaryingLocationNV(program, MemoryUtil.getAddress(name), function_pointer);
/* 155 */     return __result;
/*     */   }
/*     */ 
/*     */   static native int nglGetVaryingLocationNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetVaryingLocationNV(int program, CharSequence name) {
/* 161 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 162 */     long function_pointer = caps.glGetVaryingLocationNV;
/* 163 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 164 */     int __result = nglGetVaryingLocationNV(program, APIUtil.getBufferNT(caps, name), function_pointer);
/* 165 */     return __result;
/*     */   }
/*     */ 
/*     */   public static void glGetActiveVaryingNV(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
/* 169 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 170 */     long function_pointer = caps.glGetActiveVaryingNV;
/* 171 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 172 */     if (length != null)
/* 173 */       BufferChecks.checkBuffer(length, 1);
/* 174 */     BufferChecks.checkBuffer(size, 1);
/* 175 */     BufferChecks.checkBuffer(type, 1);
/* 176 */     BufferChecks.checkDirect(name);
/* 177 */     nglGetActiveVaryingNV(program, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetActiveVaryingNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*     */ 
/*     */   public static String glGetActiveVaryingNV(int program, int index, int bufSize, IntBuffer sizeType)
/*     */   {
/* 187 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 188 */     long function_pointer = caps.glGetActiveVaryingNV;
/* 189 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 190 */     BufferChecks.checkBuffer(sizeType, 2);
/* 191 */     IntBuffer name_length = APIUtil.getLengths(caps);
/* 192 */     ByteBuffer name = APIUtil.getBufferByte(caps, bufSize);
/* 193 */     nglGetActiveVaryingNV(program, index, bufSize, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(sizeType), MemoryUtil.getAddress(sizeType, sizeType.position() + 1), MemoryUtil.getAddress(name), function_pointer);
/* 194 */     name.limit(name_length.get(0));
/* 195 */     return APIUtil.getString(caps, name);
/*     */   }
/*     */ 
/*     */   public static String glGetActiveVaryingNV(int program, int index, int bufSize)
/*     */   {
/* 204 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 205 */     long function_pointer = caps.glGetActiveVaryingNV;
/* 206 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 207 */     IntBuffer name_length = APIUtil.getLengths(caps);
/* 208 */     ByteBuffer name = APIUtil.getBufferByte(caps, bufSize);
/* 209 */     nglGetActiveVaryingNV(program, index, bufSize, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress0(APIUtil.getBufferInt(caps)), MemoryUtil.getAddress(APIUtil.getBufferInt(caps), 1), MemoryUtil.getAddress(name), function_pointer);
/* 210 */     name.limit(name_length.get(0));
/* 211 */     return APIUtil.getString(caps, name);
/*     */   }
/*     */ 
/*     */   public static int glGetActiveVaryingSizeNV(int program, int index)
/*     */   {
/* 220 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 221 */     long function_pointer = caps.glGetActiveVaryingNV;
/* 222 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 223 */     IntBuffer size = APIUtil.getBufferInt(caps);
/* 224 */     nglGetActiveVaryingNV(program, index, 0, 0L, MemoryUtil.getAddress(size), MemoryUtil.getAddress(size, 1), APIUtil.getBufferByte0(caps), function_pointer);
/* 225 */     return size.get(0);
/*     */   }
/*     */ 
/*     */   public static int glGetActiveVaryingTypeNV(int program, int index)
/*     */   {
/* 234 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 235 */     long function_pointer = caps.glGetActiveVaryingNV;
/* 236 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 237 */     IntBuffer type = APIUtil.getBufferInt(caps);
/* 238 */     nglGetActiveVaryingNV(program, index, 0, 0L, MemoryUtil.getAddress(type, 1), MemoryUtil.getAddress(type), APIUtil.getBufferByte0(caps), function_pointer);
/* 239 */     return type.get(0);
/*     */   }
/*     */ 
/*     */   public static void glActiveVaryingNV(int program, ByteBuffer name) {
/* 243 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 244 */     long function_pointer = caps.glActiveVaryingNV;
/* 245 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 246 */     BufferChecks.checkDirect(name);
/* 247 */     BufferChecks.checkNullTerminated(name);
/* 248 */     nglActiveVaryingNV(program, MemoryUtil.getAddress(name), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglActiveVaryingNV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glActiveVaryingNV(int program, CharSequence name) {
/* 254 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 255 */     long function_pointer = caps.glActiveVaryingNV;
/* 256 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 257 */     nglActiveVaryingNV(program, APIUtil.getBufferNT(caps, name), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glGetTransformFeedbackVaryingNV(int program, int index, IntBuffer location) {
/* 261 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 262 */     long function_pointer = caps.glGetTransformFeedbackVaryingNV;
/* 263 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 264 */     BufferChecks.checkBuffer(location, 1);
/* 265 */     nglGetTransformFeedbackVaryingNV(program, index, MemoryUtil.getAddress(location), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetTransformFeedbackVaryingNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetTransformFeedbackVaryingNV(int program, int index) {
/* 271 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 272 */     long function_pointer = caps.glGetTransformFeedbackVaryingNV;
/* 273 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 274 */     IntBuffer location = APIUtil.getBufferInt(caps);
/* 275 */     nglGetTransformFeedbackVaryingNV(program, index, MemoryUtil.getAddress(location), function_pointer);
/* 276 */     return location.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVTransformFeedback
 * JD-Core Version:    0.6.2
 */