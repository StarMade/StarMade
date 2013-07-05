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
/*     */ public final class NVVertexProgram extends NVProgram
/*     */ {
/*     */   public static final int GL_VERTEX_PROGRAM_NV = 34336;
/*     */   public static final int GL_VERTEX_PROGRAM_POINT_SIZE_NV = 34370;
/*     */   public static final int GL_VERTEX_PROGRAM_TWO_SIDE_NV = 34371;
/*     */   public static final int GL_VERTEX_STATE_PROGRAM_NV = 34337;
/*     */   public static final int GL_ATTRIB_ARRAY_SIZE_NV = 34339;
/*     */   public static final int GL_ATTRIB_ARRAY_STRIDE_NV = 34340;
/*     */   public static final int GL_ATTRIB_ARRAY_TYPE_NV = 34341;
/*     */   public static final int GL_CURRENT_ATTRIB_NV = 34342;
/*     */   public static final int GL_PROGRAM_PARAMETER_NV = 34372;
/*     */   public static final int GL_ATTRIB_ARRAY_POINTER_NV = 34373;
/*     */   public static final int GL_TRACK_MATRIX_NV = 34376;
/*     */   public static final int GL_TRACK_MATRIX_TRANSFORM_NV = 34377;
/*     */   public static final int GL_MAX_TRACK_MATRIX_STACK_DEPTH_NV = 34350;
/*     */   public static final int GL_MAX_TRACK_MATRICES_NV = 34351;
/*     */   public static final int GL_CURRENT_MATRIX_STACK_DEPTH_NV = 34368;
/*     */   public static final int GL_CURRENT_MATRIX_NV = 34369;
/*     */   public static final int GL_VERTEX_PROGRAM_BINDING_NV = 34378;
/*     */   public static final int GL_MODELVIEW_PROJECTION_NV = 34345;
/*     */   public static final int GL_MATRIX0_NV = 34352;
/*     */   public static final int GL_MATRIX1_NV = 34353;
/*     */   public static final int GL_MATRIX2_NV = 34354;
/*     */   public static final int GL_MATRIX3_NV = 34355;
/*     */   public static final int GL_MATRIX4_NV = 34356;
/*     */   public static final int GL_MATRIX5_NV = 34357;
/*     */   public static final int GL_MATRIX6_NV = 34358;
/*     */   public static final int GL_MATRIX7_NV = 34359;
/*     */   public static final int GL_IDENTITY_NV = 34346;
/*     */   public static final int GL_INVERSE_NV = 34347;
/*     */   public static final int GL_TRANSPOSE_NV = 34348;
/*     */   public static final int GL_INVERSE_TRANSPOSE_NV = 34349;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY0_NV = 34384;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY1_NV = 34385;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY2_NV = 34386;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY3_NV = 34387;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY4_NV = 34388;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY5_NV = 34389;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY6_NV = 34390;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY7_NV = 34391;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY8_NV = 34392;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY9_NV = 34393;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY10_NV = 34394;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY11_NV = 34395;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY12_NV = 34396;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY13_NV = 34397;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY14_NV = 34398;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY15_NV = 34399;
/*     */   public static final int GL_MAP1_VERTEX_ATTRIB0_4_NV = 34400;
/*     */   public static final int GL_MAP1_VERTEX_ATTRIB1_4_NV = 34401;
/*     */   public static final int GL_MAP1_VERTEX_ATTRIB2_4_NV = 34402;
/*     */   public static final int GL_MAP1_VERTEX_ATTRIB3_4_NV = 34403;
/*     */   public static final int GL_MAP1_VERTEX_ATTRIB4_4_NV = 34404;
/*     */   public static final int GL_MAP1_VERTEX_ATTRIB5_4_NV = 34405;
/*     */   public static final int GL_MAP1_VERTEX_ATTRIB6_4_NV = 34406;
/*     */   public static final int GL_MAP1_VERTEX_ATTRIB7_4_NV = 34407;
/*     */   public static final int GL_MAP1_VERTEX_ATTRIB8_4_NV = 34408;
/*     */   public static final int GL_MAP1_VERTEX_ATTRIB9_4_NV = 34409;
/*     */   public static final int GL_MAP1_VERTEX_ATTRIB10_4_NV = 34410;
/*     */   public static final int GL_MAP1_VERTEX_ATTRIB11_4_NV = 34411;
/*     */   public static final int GL_MAP1_VERTEX_ATTRIB12_4_NV = 34412;
/*     */   public static final int GL_MAP1_VERTEX_ATTRIB13_4_NV = 34413;
/*     */   public static final int GL_MAP1_VERTEX_ATTRIB14_4_NV = 34414;
/*     */   public static final int GL_MAP1_VERTEX_ATTRIB15_4_NV = 34415;
/*     */   public static final int GL_MAP2_VERTEX_ATTRIB0_4_NV = 34416;
/*     */   public static final int GL_MAP2_VERTEX_ATTRIB1_4_NV = 34417;
/*     */   public static final int GL_MAP2_VERTEX_ATTRIB2_4_NV = 34418;
/*     */   public static final int GL_MAP2_VERTEX_ATTRIB3_4_NV = 34419;
/*     */   public static final int GL_MAP2_VERTEX_ATTRIB4_4_NV = 34420;
/*     */   public static final int GL_MAP2_VERTEX_ATTRIB5_4_NV = 34421;
/*     */   public static final int GL_MAP2_VERTEX_ATTRIB6_4_NV = 34422;
/*     */   public static final int GL_MAP2_VERTEX_ATTRIB7_4_NV = 34423;
/*     */   public static final int GL_MAP2_VERTEX_ATTRIB8_4_NV = 34424;
/*     */   public static final int GL_MAP2_VERTEX_ATTRIB9_4_NV = 34425;
/*     */   public static final int GL_MAP2_VERTEX_ATTRIB10_4_NV = 34426;
/*     */   public static final int GL_MAP2_VERTEX_ATTRIB11_4_NV = 34427;
/*     */   public static final int GL_MAP2_VERTEX_ATTRIB12_4_NV = 34428;
/*     */   public static final int GL_MAP2_VERTEX_ATTRIB13_4_NV = 34429;
/*     */   public static final int GL_MAP2_VERTEX_ATTRIB14_4_NV = 34430;
/*     */   public static final int GL_MAP2_VERTEX_ATTRIB15_4_NV = 34431;
/*     */ 
/*     */   public static void glExecuteProgramNV(int target, int id, FloatBuffer params)
/*     */   {
/* 166 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 167 */     long function_pointer = caps.glExecuteProgramNV;
/* 168 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 169 */     BufferChecks.checkBuffer(params, 4);
/* 170 */     nglExecuteProgramNV(target, id, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglExecuteProgramNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetProgramParameterNV(int target, int index, int parameterName, FloatBuffer params) {
/* 175 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 176 */     long function_pointer = caps.glGetProgramParameterfvNV;
/* 177 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 178 */     BufferChecks.checkBuffer(params, 4);
/* 179 */     nglGetProgramParameterfvNV(target, index, parameterName, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetProgramParameterfvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetProgramParameterNV(int target, int index, int parameterName, DoubleBuffer params) {
/* 184 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 185 */     long function_pointer = caps.glGetProgramParameterdvNV;
/* 186 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 187 */     BufferChecks.checkBuffer(params, 4);
/* 188 */     nglGetProgramParameterdvNV(target, index, parameterName, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetProgramParameterdvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetTrackMatrixNV(int target, int address, int parameterName, IntBuffer params) {
/* 193 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 194 */     long function_pointer = caps.glGetTrackMatrixivNV;
/* 195 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 196 */     BufferChecks.checkBuffer(params, 4);
/* 197 */     nglGetTrackMatrixivNV(target, address, parameterName, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetTrackMatrixivNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetVertexAttribNV(int index, int parameterName, FloatBuffer params) {
/* 202 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 203 */     long function_pointer = caps.glGetVertexAttribfvNV;
/* 204 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 205 */     BufferChecks.checkBuffer(params, 4);
/* 206 */     nglGetVertexAttribfvNV(index, parameterName, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetVertexAttribfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetVertexAttribNV(int index, int parameterName, DoubleBuffer params) {
/* 211 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 212 */     long function_pointer = caps.glGetVertexAttribdvNV;
/* 213 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 214 */     BufferChecks.checkBuffer(params, 4);
/* 215 */     nglGetVertexAttribdvNV(index, parameterName, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetVertexAttribdvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetVertexAttribNV(int index, int parameterName, IntBuffer params) {
/* 220 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 221 */     long function_pointer = caps.glGetVertexAttribivNV;
/* 222 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 223 */     BufferChecks.checkBuffer(params, 4);
/* 224 */     nglGetVertexAttribivNV(index, parameterName, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetVertexAttribivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static ByteBuffer glGetVertexAttribPointerNV(int index, int parameterName, long result_size) {
/* 229 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 230 */     long function_pointer = caps.glGetVertexAttribPointervNV;
/* 231 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 232 */     ByteBuffer __result = nglGetVertexAttribPointervNV(index, parameterName, result_size, function_pointer);
/* 233 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*     */   }
/*     */   static native ByteBuffer nglGetVertexAttribPointervNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramParameter4fNV(int target, int index, float x, float y, float z, float w) {
/* 238 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 239 */     long function_pointer = caps.glProgramParameter4fNV;
/* 240 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 241 */     nglProgramParameter4fNV(target, index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglProgramParameter4fNV(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*     */ 
/*     */   public static void glProgramParameter4dNV(int target, int index, double x, double y, double z, double w) {
/* 246 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 247 */     long function_pointer = caps.glProgramParameter4dNV;
/* 248 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 249 */     nglProgramParameter4dNV(target, index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglProgramParameter4dNV(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*     */ 
/*     */   public static void glProgramParameters4NV(int target, int index, FloatBuffer params) {
/* 254 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 255 */     long function_pointer = caps.glProgramParameters4fvNV;
/* 256 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 257 */     BufferChecks.checkDirect(params);
/* 258 */     nglProgramParameters4fvNV(target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglProgramParameters4fvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramParameters4NV(int target, int index, DoubleBuffer params) {
/* 263 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 264 */     long function_pointer = caps.glProgramParameters4dvNV;
/* 265 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 266 */     BufferChecks.checkDirect(params);
/* 267 */     nglProgramParameters4dvNV(target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglProgramParameters4dvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glTrackMatrixNV(int target, int address, int matrix, int transform) {
/* 272 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 273 */     long function_pointer = caps.glTrackMatrixNV;
/* 274 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 275 */     nglTrackMatrixNV(target, address, matrix, transform, function_pointer);
/*     */   }
/*     */   static native void nglTrackMatrixNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribPointerNV(int index, int size, int type, int stride, DoubleBuffer buffer) {
/* 280 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 281 */     long function_pointer = caps.glVertexAttribPointerNV;
/* 282 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 283 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 284 */     BufferChecks.checkDirect(buffer);
/* 285 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 286 */     nglVertexAttribPointerNV(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*     */   }
/*     */   public static void glVertexAttribPointerNV(int index, int size, int type, int stride, FloatBuffer buffer) {
/* 289 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 290 */     long function_pointer = caps.glVertexAttribPointerNV;
/* 291 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 292 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 293 */     BufferChecks.checkDirect(buffer);
/* 294 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 295 */     nglVertexAttribPointerNV(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*     */   }
/*     */   public static void glVertexAttribPointerNV(int index, int size, int type, int stride, ByteBuffer buffer) {
/* 298 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 299 */     long function_pointer = caps.glVertexAttribPointerNV;
/* 300 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 301 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 302 */     BufferChecks.checkDirect(buffer);
/* 303 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 304 */     nglVertexAttribPointerNV(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*     */   }
/*     */   public static void glVertexAttribPointerNV(int index, int size, int type, int stride, IntBuffer buffer) {
/* 307 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 308 */     long function_pointer = caps.glVertexAttribPointerNV;
/* 309 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 310 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 311 */     BufferChecks.checkDirect(buffer);
/* 312 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 313 */     nglVertexAttribPointerNV(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*     */   }
/*     */   public static void glVertexAttribPointerNV(int index, int size, int type, int stride, ShortBuffer buffer) {
/* 316 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 317 */     long function_pointer = caps.glVertexAttribPointerNV;
/* 318 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 319 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 320 */     BufferChecks.checkDirect(buffer);
/* 321 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 322 */     nglVertexAttribPointerNV(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribPointerNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*     */ 
/* 326 */   public static void glVertexAttribPointerNV(int index, int size, int type, int stride, long buffer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 327 */     long function_pointer = caps.glVertexAttribPointerNV;
/* 328 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 329 */     GLChecks.ensureArrayVBOenabled(caps);
/* 330 */     nglVertexAttribPointerNVBO(index, size, type, stride, buffer_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglVertexAttribPointerNVBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttrib1sNV(int index, short x) {
/* 335 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 336 */     long function_pointer = caps.glVertexAttrib1sNV;
/* 337 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 338 */     nglVertexAttrib1sNV(index, x, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib1sNV(int paramInt, short paramShort, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib1fNV(int index, float x) {
/* 343 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 344 */     long function_pointer = caps.glVertexAttrib1fNV;
/* 345 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 346 */     nglVertexAttrib1fNV(index, x, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib1fNV(int paramInt, float paramFloat, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib1dNV(int index, double x) {
/* 351 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 352 */     long function_pointer = caps.glVertexAttrib1dNV;
/* 353 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 354 */     nglVertexAttrib1dNV(index, x, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib1dNV(int paramInt, double paramDouble, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib2sNV(int index, short x, short y) {
/* 359 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 360 */     long function_pointer = caps.glVertexAttrib2sNV;
/* 361 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 362 */     nglVertexAttrib2sNV(index, x, y, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib2sNV(int paramInt, short paramShort1, short paramShort2, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib2fNV(int index, float x, float y) {
/* 367 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 368 */     long function_pointer = caps.glVertexAttrib2fNV;
/* 369 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 370 */     nglVertexAttrib2fNV(index, x, y, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib2fNV(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib2dNV(int index, double x, double y) {
/* 375 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 376 */     long function_pointer = caps.glVertexAttrib2dNV;
/* 377 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 378 */     nglVertexAttrib2dNV(index, x, y, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib2dNV(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib3sNV(int index, short x, short y, short z) {
/* 383 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 384 */     long function_pointer = caps.glVertexAttrib3sNV;
/* 385 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 386 */     nglVertexAttrib3sNV(index, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib3sNV(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib3fNV(int index, float x, float y, float z) {
/* 391 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 392 */     long function_pointer = caps.glVertexAttrib3fNV;
/* 393 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 394 */     nglVertexAttrib3fNV(index, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib3fNV(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib3dNV(int index, double x, double y, double z) {
/* 399 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 400 */     long function_pointer = caps.glVertexAttrib3dNV;
/* 401 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 402 */     nglVertexAttrib3dNV(index, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib3dNV(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib4sNV(int index, short x, short y, short z, short w) {
/* 407 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 408 */     long function_pointer = caps.glVertexAttrib4sNV;
/* 409 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 410 */     nglVertexAttrib4sNV(index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib4sNV(int paramInt, short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib4fNV(int index, float x, float y, float z, float w) {
/* 415 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 416 */     long function_pointer = caps.glVertexAttrib4fNV;
/* 417 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 418 */     nglVertexAttrib4fNV(index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib4fNV(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib4dNV(int index, double x, double y, double z, double w) {
/* 423 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 424 */     long function_pointer = caps.glVertexAttrib4dNV;
/* 425 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 426 */     nglVertexAttrib4dNV(index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib4dNV(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib4ubNV(int index, byte x, byte y, byte z, byte w) {
/* 431 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 432 */     long function_pointer = caps.glVertexAttrib4ubNV;
/* 433 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 434 */     nglVertexAttrib4ubNV(index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib4ubNV(int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribs1NV(int index, ShortBuffer v) {
/* 439 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 440 */     long function_pointer = caps.glVertexAttribs1svNV;
/* 441 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 442 */     BufferChecks.checkDirect(v);
/* 443 */     nglVertexAttribs1svNV(index, v.remaining(), MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribs1svNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribs1NV(int index, FloatBuffer v) {
/* 448 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 449 */     long function_pointer = caps.glVertexAttribs1fvNV;
/* 450 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 451 */     BufferChecks.checkDirect(v);
/* 452 */     nglVertexAttribs1fvNV(index, v.remaining(), MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribs1fvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribs1NV(int index, DoubleBuffer v) {
/* 457 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 458 */     long function_pointer = caps.glVertexAttribs1dvNV;
/* 459 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 460 */     BufferChecks.checkDirect(v);
/* 461 */     nglVertexAttribs1dvNV(index, v.remaining(), MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribs1dvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribs2NV(int index, ShortBuffer v) {
/* 466 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 467 */     long function_pointer = caps.glVertexAttribs2svNV;
/* 468 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 469 */     BufferChecks.checkDirect(v);
/* 470 */     nglVertexAttribs2svNV(index, v.remaining() >> 1, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribs2svNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribs2NV(int index, FloatBuffer v) {
/* 475 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 476 */     long function_pointer = caps.glVertexAttribs2fvNV;
/* 477 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 478 */     BufferChecks.checkDirect(v);
/* 479 */     nglVertexAttribs2fvNV(index, v.remaining() >> 1, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribs2fvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribs2NV(int index, DoubleBuffer v) {
/* 484 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 485 */     long function_pointer = caps.glVertexAttribs2dvNV;
/* 486 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 487 */     BufferChecks.checkDirect(v);
/* 488 */     nglVertexAttribs2dvNV(index, v.remaining() >> 1, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribs2dvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribs3NV(int index, ShortBuffer v) {
/* 493 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 494 */     long function_pointer = caps.glVertexAttribs3svNV;
/* 495 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 496 */     BufferChecks.checkDirect(v);
/* 497 */     nglVertexAttribs3svNV(index, v.remaining() / 3, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribs3svNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribs3NV(int index, FloatBuffer v) {
/* 502 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 503 */     long function_pointer = caps.glVertexAttribs3fvNV;
/* 504 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 505 */     BufferChecks.checkDirect(v);
/* 506 */     nglVertexAttribs3fvNV(index, v.remaining() / 3, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribs3fvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribs3NV(int index, DoubleBuffer v) {
/* 511 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 512 */     long function_pointer = caps.glVertexAttribs3dvNV;
/* 513 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 514 */     BufferChecks.checkDirect(v);
/* 515 */     nglVertexAttribs3dvNV(index, v.remaining() / 3, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribs3dvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribs4NV(int index, ShortBuffer v) {
/* 520 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 521 */     long function_pointer = caps.glVertexAttribs4svNV;
/* 522 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 523 */     BufferChecks.checkDirect(v);
/* 524 */     nglVertexAttribs4svNV(index, v.remaining() >> 2, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribs4svNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribs4NV(int index, FloatBuffer v) {
/* 529 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 530 */     long function_pointer = caps.glVertexAttribs4fvNV;
/* 531 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 532 */     BufferChecks.checkDirect(v);
/* 533 */     nglVertexAttribs4fvNV(index, v.remaining() >> 2, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribs4fvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribs4NV(int index, DoubleBuffer v) {
/* 538 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 539 */     long function_pointer = caps.glVertexAttribs4dvNV;
/* 540 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 541 */     BufferChecks.checkDirect(v);
/* 542 */     nglVertexAttribs4dvNV(index, v.remaining() >> 2, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglVertexAttribs4dvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVVertexProgram
 * JD-Core Version:    0.6.2
 */