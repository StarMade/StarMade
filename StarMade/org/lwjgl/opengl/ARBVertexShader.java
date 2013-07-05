/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class ARBVertexShader
/*     */ {
/*     */   public static final int GL_VERTEX_SHADER_ARB = 35633;
/*     */   public static final int GL_MAX_VERTEX_UNIFORM_COMPONENTS_ARB = 35658;
/*     */   public static final int GL_MAX_VARYING_FLOATS_ARB = 35659;
/*     */   public static final int GL_MAX_VERTEX_ATTRIBS_ARB = 34921;
/*     */   public static final int GL_MAX_TEXTURE_IMAGE_UNITS_ARB = 34930;
/*     */   public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS_ARB = 35660;
/*     */   public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS_ARB = 35661;
/*     */   public static final int GL_MAX_TEXTURE_COORDS_ARB = 34929;
/*     */   public static final int GL_VERTEX_PROGRAM_POINT_SIZE_ARB = 34370;
/*     */   public static final int GL_VERTEX_PROGRAM_TWO_SIDE_ARB = 34371;
/*     */   public static final int GL_OBJECT_ACTIVE_ATTRIBUTES_ARB = 35721;
/*     */   public static final int GL_OBJECT_ACTIVE_ATTRIBUTE_MAX_LENGTH_ARB = 35722;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED_ARB = 34338;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE_ARB = 34339;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE_ARB = 34340;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE_ARB = 34341;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED_ARB = 34922;
/*     */   public static final int GL_CURRENT_VERTEX_ATTRIB_ARB = 34342;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER_ARB = 34373;
/*     */   public static final int GL_FLOAT_VEC2_ARB = 35664;
/*     */   public static final int GL_FLOAT_VEC3_ARB = 35665;
/*     */   public static final int GL_FLOAT_VEC4_ARB = 35666;
/*     */   public static final int GL_FLOAT_MAT2_ARB = 35674;
/*     */   public static final int GL_FLOAT_MAT3_ARB = 35675;
/*     */   public static final int GL_FLOAT_MAT4_ARB = 35676;
/*     */ 
/*     */   public static void glVertexAttrib1sARB(int index, short v0)
/*     */   {
/*  70 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  71 */     long function_pointer = caps.glVertexAttrib1sARB;
/*  72 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  73 */     nglVertexAttrib1sARB(index, v0, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib1sARB(int paramInt, short paramShort, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib1fARB(int index, float v0) {
/*  78 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  79 */     long function_pointer = caps.glVertexAttrib1fARB;
/*  80 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  81 */     nglVertexAttrib1fARB(index, v0, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib1fARB(int paramInt, float paramFloat, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib1dARB(int index, double v0) {
/*  86 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  87 */     long function_pointer = caps.glVertexAttrib1dARB;
/*  88 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  89 */     nglVertexAttrib1dARB(index, v0, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib1dARB(int paramInt, double paramDouble, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib2sARB(int index, short v0, short v1) {
/*  94 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  95 */     long function_pointer = caps.glVertexAttrib2sARB;
/*  96 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  97 */     nglVertexAttrib2sARB(index, v0, v1, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib2sARB(int paramInt, short paramShort1, short paramShort2, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib2fARB(int index, float v0, float v1) {
/* 102 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 103 */     long function_pointer = caps.glVertexAttrib2fARB;
/* 104 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 105 */     nglVertexAttrib2fARB(index, v0, v1, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib2fARB(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib2dARB(int index, double v0, double v1) {
/* 110 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 111 */     long function_pointer = caps.glVertexAttrib2dARB;
/* 112 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 113 */     nglVertexAttrib2dARB(index, v0, v1, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib2dARB(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib3sARB(int index, short v0, short v1, short v2) {
/* 118 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 119 */     long function_pointer = caps.glVertexAttrib3sARB;
/* 120 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 121 */     nglVertexAttrib3sARB(index, v0, v1, v2, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib3sARB(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib3fARB(int index, float v0, float v1, float v2) {
/* 126 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 127 */     long function_pointer = caps.glVertexAttrib3fARB;
/* 128 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 129 */     nglVertexAttrib3fARB(index, v0, v1, v2, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib3fARB(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib3dARB(int index, double v0, double v1, double v2) {
/* 134 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 135 */     long function_pointer = caps.glVertexAttrib3dARB;
/* 136 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 137 */     nglVertexAttrib3dARB(index, v0, v1, v2, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib3dARB(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib4sARB(int index, short v0, short v1, short v2, short v3) {
/* 142 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 143 */     long function_pointer = caps.glVertexAttrib4sARB;
/* 144 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 145 */     nglVertexAttrib4sARB(index, v0, v1, v2, v3, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib4sARB(int paramInt, short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib4fARB(int index, float v0, float v1, float v2, float v3) {
/* 150 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 151 */     long function_pointer = caps.glVertexAttrib4fARB;
/* 152 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 153 */     nglVertexAttrib4fARB(index, v0, v1, v2, v3, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib4fARB(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib4dARB(int index, double v0, double v1, double v2, double v3) {
/* 158 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 159 */     long function_pointer = caps.glVertexAttrib4dARB;
/* 160 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 161 */     nglVertexAttrib4dARB(index, v0, v1, v2, v3, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib4dARB(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib4NubARB(int index, byte x, byte y, byte z, byte w) {
/* 166 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 167 */     long function_pointer = caps.glVertexAttrib4NubARB;
/* 168 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 169 */     nglVertexAttrib4NubARB(index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib4NubARB(int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, DoubleBuffer buffer) {
/* 174 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 175 */     long function_pointer = caps.glVertexAttribPointerARB;
/* 176 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 177 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 178 */     BufferChecks.checkDirect(buffer);
/* 179 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 180 */     nglVertexAttribPointerARB(index, size, 5130, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*     */   }
/*     */   public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, FloatBuffer buffer) {
/* 183 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 184 */     long function_pointer = caps.glVertexAttribPointerARB;
/* 185 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 186 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 187 */     BufferChecks.checkDirect(buffer);
/* 188 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 189 */     nglVertexAttribPointerARB(index, size, 5126, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*     */   }
/*     */   public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer) {
/* 192 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 193 */     long function_pointer = caps.glVertexAttribPointerARB;
/* 194 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 195 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 196 */     BufferChecks.checkDirect(buffer);
/* 197 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 198 */     nglVertexAttribPointerARB(index, size, unsigned ? 5121 : 5120, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*     */   }
/*     */   public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer) {
/* 201 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 202 */     long function_pointer = caps.glVertexAttribPointerARB;
/* 203 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 204 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 205 */     BufferChecks.checkDirect(buffer);
/* 206 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 207 */     nglVertexAttribPointerARB(index, size, unsigned ? 5125 : 5124, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*     */   }
/*     */   public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer) {
/* 210 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 211 */     long function_pointer = caps.glVertexAttribPointerARB;
/* 212 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 213 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 214 */     BufferChecks.checkDirect(buffer);
/* 215 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 216 */     nglVertexAttribPointerARB(index, size, unsigned ? 5123 : 5122, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribPointerARB(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, long paramLong1, long paramLong2);
/*     */ 
/* 220 */   public static void glVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, long buffer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 221 */     long function_pointer = caps.glVertexAttribPointerARB;
/* 222 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 223 */     GLChecks.ensureArrayVBOenabled(caps);
/* 224 */     nglVertexAttribPointerARBBO(index, size, type, normalized, stride, buffer_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglVertexAttribPointerARBBO(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, ByteBuffer buffer)
/*     */   {
/* 230 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 231 */     long function_pointer = caps.glVertexAttribPointerARB;
/* 232 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 233 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 234 */     BufferChecks.checkDirect(buffer);
/* 235 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 236 */     nglVertexAttribPointerARB(index, size, type, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glEnableVertexAttribArrayARB(int index) {
/* 240 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 241 */     long function_pointer = caps.glEnableVertexAttribArrayARB;
/* 242 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 243 */     nglEnableVertexAttribArrayARB(index, function_pointer);
/*     */   }
/*     */   static native void nglEnableVertexAttribArrayARB(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glDisableVertexAttribArrayARB(int index) {
/* 248 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 249 */     long function_pointer = caps.glDisableVertexAttribArrayARB;
/* 250 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 251 */     nglDisableVertexAttribArrayARB(index, function_pointer);
/*     */   }
/*     */   static native void nglDisableVertexAttribArrayARB(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glBindAttribLocationARB(int programObj, int index, ByteBuffer name) {
/* 256 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 257 */     long function_pointer = caps.glBindAttribLocationARB;
/* 258 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 259 */     BufferChecks.checkDirect(name);
/* 260 */     BufferChecks.checkNullTerminated(name);
/* 261 */     nglBindAttribLocationARB(programObj, index, MemoryUtil.getAddress(name), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglBindAttribLocationARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glBindAttribLocationARB(int programObj, int index, CharSequence name) {
/* 267 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 268 */     long function_pointer = caps.glBindAttribLocationARB;
/* 269 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 270 */     nglBindAttribLocationARB(programObj, index, APIUtil.getBufferNT(caps, name), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glGetActiveAttribARB(int programObj, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
/* 274 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 275 */     long function_pointer = caps.glGetActiveAttribARB;
/* 276 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 277 */     if (length != null)
/* 278 */       BufferChecks.checkBuffer(length, 1);
/* 279 */     BufferChecks.checkBuffer(size, 1);
/* 280 */     BufferChecks.checkBuffer(type, 1);
/* 281 */     BufferChecks.checkDirect(name);
/* 282 */     nglGetActiveAttribARB(programObj, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetActiveAttribARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*     */ 
/*     */   public static String glGetActiveAttribARB(int programObj, int index, int maxLength, IntBuffer sizeType)
/*     */   {
/* 292 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 293 */     long function_pointer = caps.glGetActiveAttribARB;
/* 294 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 295 */     BufferChecks.checkBuffer(sizeType, 2);
/* 296 */     IntBuffer name_length = APIUtil.getLengths(caps);
/* 297 */     ByteBuffer name = APIUtil.getBufferByte(caps, maxLength);
/* 298 */     nglGetActiveAttribARB(programObj, index, maxLength, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(sizeType), MemoryUtil.getAddress(sizeType, sizeType.position() + 1), MemoryUtil.getAddress(name), function_pointer);
/* 299 */     name.limit(name_length.get(0));
/* 300 */     return APIUtil.getString(caps, name);
/*     */   }
/*     */ 
/*     */   public static String glGetActiveAttribARB(int programObj, int index, int maxLength)
/*     */   {
/* 309 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 310 */     long function_pointer = caps.glGetActiveAttribARB;
/* 311 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 312 */     IntBuffer name_length = APIUtil.getLengths(caps);
/* 313 */     ByteBuffer name = APIUtil.getBufferByte(caps, maxLength);
/* 314 */     nglGetActiveAttribARB(programObj, index, maxLength, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress0(APIUtil.getBufferInt(caps)), MemoryUtil.getAddress(APIUtil.getBufferInt(caps), 1), MemoryUtil.getAddress(name), function_pointer);
/* 315 */     name.limit(name_length.get(0));
/* 316 */     return APIUtil.getString(caps, name);
/*     */   }
/*     */ 
/*     */   public static int glGetActiveAttribSizeARB(int programObj, int index)
/*     */   {
/* 325 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 326 */     long function_pointer = caps.glGetActiveAttribARB;
/* 327 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 328 */     IntBuffer size = APIUtil.getBufferInt(caps);
/* 329 */     nglGetActiveAttribARB(programObj, index, 0, 0L, MemoryUtil.getAddress(size), MemoryUtil.getAddress(size, 1), APIUtil.getBufferByte0(caps), function_pointer);
/* 330 */     return size.get(0);
/*     */   }
/*     */ 
/*     */   public static int glGetActiveAttribTypeARB(int programObj, int index)
/*     */   {
/* 339 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 340 */     long function_pointer = caps.glGetActiveAttribARB;
/* 341 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 342 */     IntBuffer type = APIUtil.getBufferInt(caps);
/* 343 */     nglGetActiveAttribARB(programObj, index, 0, 0L, MemoryUtil.getAddress(type, 1), MemoryUtil.getAddress(type), APIUtil.getBufferByte0(caps), function_pointer);
/* 344 */     return type.get(0);
/*     */   }
/*     */ 
/*     */   public static int glGetAttribLocationARB(int programObj, ByteBuffer name) {
/* 348 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 349 */     long function_pointer = caps.glGetAttribLocationARB;
/* 350 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 351 */     BufferChecks.checkDirect(name);
/* 352 */     BufferChecks.checkNullTerminated(name);
/* 353 */     int __result = nglGetAttribLocationARB(programObj, MemoryUtil.getAddress(name), function_pointer);
/* 354 */     return __result;
/*     */   }
/*     */ 
/*     */   static native int nglGetAttribLocationARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetAttribLocationARB(int programObj, CharSequence name) {
/* 360 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 361 */     long function_pointer = caps.glGetAttribLocationARB;
/* 362 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 363 */     int __result = nglGetAttribLocationARB(programObj, APIUtil.getBufferNT(caps, name), function_pointer);
/* 364 */     return __result;
/*     */   }
/*     */ 
/*     */   public static void glGetVertexAttribARB(int index, int pname, FloatBuffer params) {
/* 368 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 369 */     long function_pointer = caps.glGetVertexAttribfvARB;
/* 370 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 371 */     BufferChecks.checkBuffer(params, 4);
/* 372 */     nglGetVertexAttribfvARB(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetVertexAttribfvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetVertexAttribARB(int index, int pname, DoubleBuffer params) {
/* 377 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 378 */     long function_pointer = caps.glGetVertexAttribdvARB;
/* 379 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 380 */     BufferChecks.checkBuffer(params, 4);
/* 381 */     nglGetVertexAttribdvARB(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetVertexAttribdvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetVertexAttribARB(int index, int pname, IntBuffer params) {
/* 386 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 387 */     long function_pointer = caps.glGetVertexAttribivARB;
/* 388 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 389 */     BufferChecks.checkBuffer(params, 4);
/* 390 */     nglGetVertexAttribivARB(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetVertexAttribivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static ByteBuffer glGetVertexAttribPointerARB(int index, int pname, long result_size) {
/* 395 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 396 */     long function_pointer = caps.glGetVertexAttribPointervARB;
/* 397 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 398 */     ByteBuffer __result = nglGetVertexAttribPointervARB(index, pname, result_size, function_pointer);
/* 399 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*     */   }
/*     */ 
/*     */   static native ByteBuffer nglGetVertexAttribPointervARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBVertexShader
 * JD-Core Version:    0.6.2
 */