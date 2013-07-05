/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class GL40
/*     */ {
/*     */   public static final int GL_DRAW_INDIRECT_BUFFER = 36671;
/*     */   public static final int GL_DRAW_INDIRECT_BUFFER_BINDING = 36675;
/*     */   public static final int GL_GEOMETRY_SHADER_INVOCATIONS = 34943;
/*     */   public static final int GL_MAX_GEOMETRY_SHADER_INVOCATIONS = 36442;
/*     */   public static final int GL_MIN_FRAGMENT_INTERPOLATION_OFFSET = 36443;
/*     */   public static final int GL_MAX_FRAGMENT_INTERPOLATION_OFFSET = 36444;
/*     */   public static final int GL_FRAGMENT_INTERPOLATION_OFFSET_BITS = 36445;
/*     */   public static final int GL_MAX_VERTEX_STREAMS = 36465;
/*     */   public static final int GL_DOUBLE_VEC2 = 36860;
/*     */   public static final int GL_DOUBLE_VEC3 = 36861;
/*     */   public static final int GL_DOUBLE_VEC4 = 36862;
/*     */   public static final int GL_DOUBLE_MAT2 = 36678;
/*     */   public static final int GL_DOUBLE_MAT3 = 36679;
/*     */   public static final int GL_DOUBLE_MAT4 = 36680;
/*     */   public static final int GL_DOUBLE_MAT2x3 = 36681;
/*     */   public static final int GL_DOUBLE_MAT2x4 = 36682;
/*     */   public static final int GL_DOUBLE_MAT3x2 = 36683;
/*     */   public static final int GL_DOUBLE_MAT3x4 = 36684;
/*     */   public static final int GL_DOUBLE_MAT4x2 = 36685;
/*     */   public static final int GL_DOUBLE_MAT4x3 = 36686;
/*     */   public static final int GL_SAMPLE_SHADING = 35894;
/*     */   public static final int GL_MIN_SAMPLE_SHADING_VALUE = 35895;
/*     */   public static final int GL_ACTIVE_SUBROUTINES = 36325;
/*     */   public static final int GL_ACTIVE_SUBROUTINE_UNIFORMS = 36326;
/*     */   public static final int GL_ACTIVE_SUBROUTINE_UNIFORM_LOCATIONS = 36423;
/*     */   public static final int GL_ACTIVE_SUBROUTINE_MAX_LENGTH = 36424;
/*     */   public static final int GL_ACTIVE_SUBROUTINE_UNIFORM_MAX_LENGTH = 36425;
/*     */   public static final int GL_MAX_SUBROUTINES = 36327;
/*     */   public static final int GL_MAX_SUBROUTINE_UNIFORM_LOCATIONS = 36328;
/*     */   public static final int GL_NUM_COMPATIBLE_SUBROUTINES = 36426;
/*     */   public static final int GL_COMPATIBLE_SUBROUTINES = 36427;
/*     */   public static final int GL_UNIFORM_SIZE = 35384;
/*     */   public static final int GL_UNIFORM_NAME_LENGTH = 35385;
/*     */   public static final int GL_PATCHES = 14;
/*     */   public static final int GL_PATCH_VERTICES = 36466;
/*     */   public static final int GL_PATCH_DEFAULT_INNER_LEVEL = 36467;
/*     */   public static final int GL_PATCH_DEFAULT_OUTER_LEVEL = 36468;
/*     */   public static final int GL_TESS_CONTROL_OUTPUT_VERTICES = 36469;
/*     */   public static final int GL_TESS_GEN_MODE = 36470;
/*     */   public static final int GL_TESS_GEN_SPACING = 36471;
/*     */   public static final int GL_TESS_GEN_VERTEX_ORDER = 36472;
/*     */   public static final int GL_TESS_GEN_POINT_MODE = 36473;
/*     */   public static final int GL_ISOLINES = 36474;
/*     */   public static final int GL_FRACTIONAL_ODD = 36475;
/*     */   public static final int GL_FRACTIONAL_EVEN = 36476;
/*     */   public static final int GL_MAX_PATCH_VERTICES = 36477;
/*     */   public static final int GL_MAX_TESS_GEN_LEVEL = 36478;
/*     */   public static final int GL_MAX_TESS_CONTROL_UNIFORM_COMPONENTS = 36479;
/*     */   public static final int GL_MAX_TESS_EVALUATION_UNIFORM_COMPONENTS = 36480;
/*     */   public static final int GL_MAX_TESS_CONTROL_TEXTURE_IMAGE_UNITS = 36481;
/*     */   public static final int GL_MAX_TESS_EVALUATION_TEXTURE_IMAGE_UNITS = 36482;
/*     */   public static final int GL_MAX_TESS_CONTROL_OUTPUT_COMPONENTS = 36483;
/*     */   public static final int GL_MAX_TESS_PATCH_COMPONENTS = 36484;
/*     */   public static final int GL_MAX_TESS_CONTROL_TOTAL_OUTPUT_COMPONENTS = 36485;
/*     */   public static final int GL_MAX_TESS_EVALUATION_OUTPUT_COMPONENTS = 36486;
/*     */   public static final int GL_MAX_TESS_CONTROL_UNIFORM_BLOCKS = 36489;
/*     */   public static final int GL_MAX_TESS_EVALUATION_UNIFORM_BLOCKS = 36490;
/*     */   public static final int GL_MAX_TESS_CONTROL_INPUT_COMPONENTS = 34924;
/*     */   public static final int GL_MAX_TESS_EVALUATION_INPUT_COMPONENTS = 34925;
/*     */   public static final int GL_MAX_COMBINED_TESS_CONTROL_UNIFORM_COMPONENTS = 36382;
/*     */   public static final int GL_MAX_COMBINED_TESS_EVALUATION_UNIFORM_COMPONENTS = 36383;
/*     */   public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_CONTROL_SHADER = 34032;
/*     */   public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_EVALUATION_SHADER = 34033;
/*     */   public static final int GL_TESS_EVALUATION_SHADER = 36487;
/*     */   public static final int GL_TESS_CONTROL_SHADER = 36488;
/*     */   public static final int GL_TEXTURE_CUBE_MAP_ARRAY = 36873;
/*     */   public static final int GL_TEXTURE_BINDING_CUBE_MAP_ARRAY = 36874;
/*     */   public static final int GL_PROXY_TEXTURE_CUBE_MAP_ARRAY = 36875;
/*     */   public static final int GL_SAMPLER_CUBE_MAP_ARRAY = 36876;
/*     */   public static final int GL_SAMPLER_CUBE_MAP_ARRAY_SHADOW = 36877;
/*     */   public static final int GL_INT_SAMPLER_CUBE_MAP_ARRAY = 36878;
/*     */   public static final int GL_UNSIGNED_INT_SAMPLER_CUBE_MAP_ARRAY = 36879;
/*     */   public static final int GL_MIN_PROGRAM_TEXTURE_GATHER_OFFSET_ARB = 36446;
/*     */   public static final int GL_MAX_PROGRAM_TEXTURE_GATHER_OFFSET_ARB = 36447;
/*     */   public static final int GL_MAX_PROGRAM_TEXTURE_GATHER_COMPONENTS_ARB = 36767;
/*     */   public static final int GL_TRANSFORM_FEEDBACK = 36386;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_PAUSED = 36387;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_ACTIVE = 36388;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_PAUSED = 36387;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_BUFFER_ACTIVE = 36388;
/*     */   public static final int GL_TRANSFORM_FEEDBACK_BINDING = 36389;
/*     */   public static final int GL_MAX_TRANSFORM_FEEDBACK_BUFFERS = 36464;
/*     */ 
/*     */   public static void glBlendEquationi(int buf, int mode)
/*     */   {
/* 230 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 231 */     long function_pointer = caps.glBlendEquationi;
/* 232 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 233 */     nglBlendEquationi(buf, mode, function_pointer);
/*     */   }
/*     */   static native void nglBlendEquationi(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glBlendEquationSeparatei(int buf, int modeRGB, int modeAlpha) {
/* 238 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 239 */     long function_pointer = caps.glBlendEquationSeparatei;
/* 240 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 241 */     nglBlendEquationSeparatei(buf, modeRGB, modeAlpha, function_pointer);
/*     */   }
/*     */   static native void nglBlendEquationSeparatei(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glBlendFunci(int buf, int src, int dst) {
/* 246 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 247 */     long function_pointer = caps.glBlendFunci;
/* 248 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 249 */     nglBlendFunci(buf, src, dst, function_pointer);
/*     */   }
/*     */   static native void nglBlendFunci(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glBlendFuncSeparatei(int buf, int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) {
/* 254 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 255 */     long function_pointer = caps.glBlendFuncSeparatei;
/* 256 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 257 */     nglBlendFuncSeparatei(buf, srcRGB, dstRGB, srcAlpha, dstAlpha, function_pointer);
/*     */   }
/*     */   static native void nglBlendFuncSeparatei(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*     */ 
/*     */   public static void glDrawArraysIndirect(int mode, ByteBuffer indirect) {
/* 262 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 263 */     long function_pointer = caps.glDrawArraysIndirect;
/* 264 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 265 */     GLChecks.ensureIndirectBOdisabled(caps);
/* 266 */     BufferChecks.checkBuffer(indirect, 16);
/* 267 */     nglDrawArraysIndirect(mode, MemoryUtil.getAddress(indirect), function_pointer);
/*     */   }
/*     */   static native void nglDrawArraysIndirect(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/* 271 */   public static void glDrawArraysIndirect(int mode, long indirect_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 272 */     long function_pointer = caps.glDrawArraysIndirect;
/* 273 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 274 */     GLChecks.ensureIndirectBOenabled(caps);
/* 275 */     nglDrawArraysIndirectBO(mode, indirect_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglDrawArraysIndirectBO(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDrawArraysIndirect(int mode, IntBuffer indirect)
/*     */   {
/* 281 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 282 */     long function_pointer = caps.glDrawArraysIndirect;
/* 283 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 284 */     GLChecks.ensureIndirectBOdisabled(caps);
/* 285 */     BufferChecks.checkBuffer(indirect, 4);
/* 286 */     nglDrawArraysIndirect(mode, MemoryUtil.getAddress(indirect), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glDrawElementsIndirect(int mode, int type, ByteBuffer indirect) {
/* 290 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 291 */     long function_pointer = caps.glDrawElementsIndirect;
/* 292 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 293 */     GLChecks.ensureIndirectBOdisabled(caps);
/* 294 */     BufferChecks.checkBuffer(indirect, 20);
/* 295 */     nglDrawElementsIndirect(mode, type, MemoryUtil.getAddress(indirect), function_pointer);
/*     */   }
/*     */   static native void nglDrawElementsIndirect(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/* 299 */   public static void glDrawElementsIndirect(int mode, int type, long indirect_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 300 */     long function_pointer = caps.glDrawElementsIndirect;
/* 301 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 302 */     GLChecks.ensureIndirectBOenabled(caps);
/* 303 */     nglDrawElementsIndirectBO(mode, type, indirect_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglDrawElementsIndirectBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDrawElementsIndirect(int mode, int type, IntBuffer indirect)
/*     */   {
/* 309 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 310 */     long function_pointer = caps.glDrawElementsIndirect;
/* 311 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 312 */     GLChecks.ensureIndirectBOdisabled(caps);
/* 313 */     BufferChecks.checkBuffer(indirect, 5);
/* 314 */     nglDrawElementsIndirect(mode, type, MemoryUtil.getAddress(indirect), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glUniform1d(int location, double x) {
/* 318 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 319 */     long function_pointer = caps.glUniform1d;
/* 320 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 321 */     nglUniform1d(location, x, function_pointer);
/*     */   }
/*     */   static native void nglUniform1d(int paramInt, double paramDouble, long paramLong);
/*     */ 
/*     */   public static void glUniform2d(int location, double x, double y) {
/* 326 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 327 */     long function_pointer = caps.glUniform2d;
/* 328 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 329 */     nglUniform2d(location, x, y, function_pointer);
/*     */   }
/*     */   static native void nglUniform2d(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/*     */ 
/*     */   public static void glUniform3d(int location, double x, double y, double z) {
/* 334 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 335 */     long function_pointer = caps.glUniform3d;
/* 336 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 337 */     nglUniform3d(location, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglUniform3d(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*     */ 
/*     */   public static void glUniform4d(int location, double x, double y, double z, double w) {
/* 342 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 343 */     long function_pointer = caps.glUniform4d;
/* 344 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 345 */     nglUniform4d(location, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglUniform4d(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*     */ 
/*     */   public static void glUniform1(int location, DoubleBuffer value) {
/* 350 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 351 */     long function_pointer = caps.glUniform1dv;
/* 352 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 353 */     BufferChecks.checkDirect(value);
/* 354 */     nglUniform1dv(location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniform1dv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform2(int location, DoubleBuffer value) {
/* 359 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 360 */     long function_pointer = caps.glUniform2dv;
/* 361 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 362 */     BufferChecks.checkDirect(value);
/* 363 */     nglUniform2dv(location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniform2dv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform3(int location, DoubleBuffer value) {
/* 368 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 369 */     long function_pointer = caps.glUniform3dv;
/* 370 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 371 */     BufferChecks.checkDirect(value);
/* 372 */     nglUniform3dv(location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniform3dv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform4(int location, DoubleBuffer value) {
/* 377 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 378 */     long function_pointer = caps.glUniform4dv;
/* 379 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 380 */     BufferChecks.checkDirect(value);
/* 381 */     nglUniform4dv(location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniform4dv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniformMatrix2(int location, boolean transpose, DoubleBuffer value) {
/* 386 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 387 */     long function_pointer = caps.glUniformMatrix2dv;
/* 388 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 389 */     BufferChecks.checkDirect(value);
/* 390 */     nglUniformMatrix2dv(location, value.remaining() >> 2, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniformMatrix2dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniformMatrix3(int location, boolean transpose, DoubleBuffer value) {
/* 395 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 396 */     long function_pointer = caps.glUniformMatrix3dv;
/* 397 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 398 */     BufferChecks.checkDirect(value);
/* 399 */     nglUniformMatrix3dv(location, value.remaining() / 9, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniformMatrix3dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniformMatrix4(int location, boolean transpose, DoubleBuffer value) {
/* 404 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 405 */     long function_pointer = caps.glUniformMatrix4dv;
/* 406 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 407 */     BufferChecks.checkDirect(value);
/* 408 */     nglUniformMatrix4dv(location, value.remaining() >> 4, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniformMatrix4dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniformMatrix2x3(int location, boolean transpose, DoubleBuffer value) {
/* 413 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 414 */     long function_pointer = caps.glUniformMatrix2x3dv;
/* 415 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 416 */     BufferChecks.checkDirect(value);
/* 417 */     nglUniformMatrix2x3dv(location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniformMatrix2x3dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniformMatrix2x4(int location, boolean transpose, DoubleBuffer value) {
/* 422 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 423 */     long function_pointer = caps.glUniformMatrix2x4dv;
/* 424 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 425 */     BufferChecks.checkDirect(value);
/* 426 */     nglUniformMatrix2x4dv(location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniformMatrix2x4dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniformMatrix3x2(int location, boolean transpose, DoubleBuffer value) {
/* 431 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 432 */     long function_pointer = caps.glUniformMatrix3x2dv;
/* 433 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 434 */     BufferChecks.checkDirect(value);
/* 435 */     nglUniformMatrix3x2dv(location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniformMatrix3x2dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniformMatrix3x4(int location, boolean transpose, DoubleBuffer value) {
/* 440 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 441 */     long function_pointer = caps.glUniformMatrix3x4dv;
/* 442 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 443 */     BufferChecks.checkDirect(value);
/* 444 */     nglUniformMatrix3x4dv(location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniformMatrix3x4dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniformMatrix4x2(int location, boolean transpose, DoubleBuffer value) {
/* 449 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 450 */     long function_pointer = caps.glUniformMatrix4x2dv;
/* 451 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 452 */     BufferChecks.checkDirect(value);
/* 453 */     nglUniformMatrix4x2dv(location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniformMatrix4x2dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniformMatrix4x3(int location, boolean transpose, DoubleBuffer value) {
/* 458 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 459 */     long function_pointer = caps.glUniformMatrix4x3dv;
/* 460 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 461 */     BufferChecks.checkDirect(value);
/* 462 */     nglUniformMatrix4x3dv(location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniformMatrix4x3dv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetUniform(int program, int location, DoubleBuffer params) {
/* 467 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 468 */     long function_pointer = caps.glGetUniformdv;
/* 469 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 470 */     BufferChecks.checkDirect(params);
/* 471 */     nglGetUniformdv(program, location, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetUniformdv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glMinSampleShading(float value) {
/* 476 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 477 */     long function_pointer = caps.glMinSampleShading;
/* 478 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 479 */     nglMinSampleShading(value, function_pointer);
/*     */   }
/*     */   static native void nglMinSampleShading(float paramFloat, long paramLong);
/*     */ 
/*     */   public static int glGetSubroutineUniformLocation(int program, int shadertype, ByteBuffer name) {
/* 484 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 485 */     long function_pointer = caps.glGetSubroutineUniformLocation;
/* 486 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 487 */     BufferChecks.checkDirect(name);
/* 488 */     BufferChecks.checkNullTerminated(name);
/* 489 */     int __result = nglGetSubroutineUniformLocation(program, shadertype, MemoryUtil.getAddress(name), function_pointer);
/* 490 */     return __result;
/*     */   }
/*     */ 
/*     */   static native int nglGetSubroutineUniformLocation(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetSubroutineUniformLocation(int program, int shadertype, CharSequence name) {
/* 496 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 497 */     long function_pointer = caps.glGetSubroutineUniformLocation;
/* 498 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 499 */     int __result = nglGetSubroutineUniformLocation(program, shadertype, APIUtil.getBufferNT(caps, name), function_pointer);
/* 500 */     return __result;
/*     */   }
/*     */ 
/*     */   public static int glGetSubroutineIndex(int program, int shadertype, ByteBuffer name) {
/* 504 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 505 */     long function_pointer = caps.glGetSubroutineIndex;
/* 506 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 507 */     BufferChecks.checkDirect(name);
/* 508 */     BufferChecks.checkNullTerminated(name);
/* 509 */     int __result = nglGetSubroutineIndex(program, shadertype, MemoryUtil.getAddress(name), function_pointer);
/* 510 */     return __result;
/*     */   }
/*     */ 
/*     */   static native int nglGetSubroutineIndex(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetSubroutineIndex(int program, int shadertype, CharSequence name) {
/* 516 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 517 */     long function_pointer = caps.glGetSubroutineIndex;
/* 518 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 519 */     int __result = nglGetSubroutineIndex(program, shadertype, APIUtil.getBufferNT(caps, name), function_pointer);
/* 520 */     return __result;
/*     */   }
/*     */ 
/*     */   public static void glGetActiveSubroutineUniform(int program, int shadertype, int index, int pname, IntBuffer values) {
/* 524 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 525 */     long function_pointer = caps.glGetActiveSubroutineUniformiv;
/* 526 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 527 */     BufferChecks.checkBuffer(values, 1);
/* 528 */     nglGetActiveSubroutineUniformiv(program, shadertype, index, pname, MemoryUtil.getAddress(values), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetActiveSubroutineUniformiv(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*     */ 
/*     */   @Deprecated
/*     */   public static int glGetActiveSubroutineUniform(int program, int shadertype, int index, int pname)
/*     */   {
/* 539 */     return glGetActiveSubroutineUniformi(program, shadertype, index, pname);
/*     */   }
/*     */ 
/*     */   public static int glGetActiveSubroutineUniformi(int program, int shadertype, int index, int pname)
/*     */   {
/* 544 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 545 */     long function_pointer = caps.glGetActiveSubroutineUniformiv;
/* 546 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 547 */     IntBuffer values = APIUtil.getBufferInt(caps);
/* 548 */     nglGetActiveSubroutineUniformiv(program, shadertype, index, pname, MemoryUtil.getAddress(values), function_pointer);
/* 549 */     return values.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetActiveSubroutineUniformName(int program, int shadertype, int index, IntBuffer length, ByteBuffer name) {
/* 553 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 554 */     long function_pointer = caps.glGetActiveSubroutineUniformName;
/* 555 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 556 */     if (length != null)
/* 557 */       BufferChecks.checkBuffer(length, 1);
/* 558 */     BufferChecks.checkDirect(name);
/* 559 */     nglGetActiveSubroutineUniformName(program, shadertype, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(name), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetActiveSubroutineUniformName(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static String glGetActiveSubroutineUniformName(int program, int shadertype, int index, int bufsize) {
/* 565 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 566 */     long function_pointer = caps.glGetActiveSubroutineUniformName;
/* 567 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 568 */     IntBuffer name_length = APIUtil.getLengths(caps);
/* 569 */     ByteBuffer name = APIUtil.getBufferByte(caps, bufsize);
/* 570 */     nglGetActiveSubroutineUniformName(program, shadertype, index, bufsize, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(name), function_pointer);
/* 571 */     name.limit(name_length.get(0));
/* 572 */     return APIUtil.getString(caps, name);
/*     */   }
/*     */ 
/*     */   public static void glGetActiveSubroutineName(int program, int shadertype, int index, IntBuffer length, ByteBuffer name) {
/* 576 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 577 */     long function_pointer = caps.glGetActiveSubroutineName;
/* 578 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 579 */     if (length != null)
/* 580 */       BufferChecks.checkBuffer(length, 1);
/* 581 */     BufferChecks.checkDirect(name);
/* 582 */     nglGetActiveSubroutineName(program, shadertype, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(name), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetActiveSubroutineName(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static String glGetActiveSubroutineName(int program, int shadertype, int index, int bufsize) {
/* 588 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 589 */     long function_pointer = caps.glGetActiveSubroutineName;
/* 590 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 591 */     IntBuffer name_length = APIUtil.getLengths(caps);
/* 592 */     ByteBuffer name = APIUtil.getBufferByte(caps, bufsize);
/* 593 */     nglGetActiveSubroutineName(program, shadertype, index, bufsize, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(name), function_pointer);
/* 594 */     name.limit(name_length.get(0));
/* 595 */     return APIUtil.getString(caps, name);
/*     */   }
/*     */ 
/*     */   public static void glUniformSubroutinesu(int shadertype, IntBuffer indices) {
/* 599 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 600 */     long function_pointer = caps.glUniformSubroutinesuiv;
/* 601 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 602 */     BufferChecks.checkDirect(indices);
/* 603 */     nglUniformSubroutinesuiv(shadertype, indices.remaining(), MemoryUtil.getAddress(indices), function_pointer);
/*     */   }
/*     */   static native void nglUniformSubroutinesuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetUniformSubroutineu(int shadertype, int location, IntBuffer params) {
/* 608 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 609 */     long function_pointer = caps.glGetUniformSubroutineuiv;
/* 610 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 611 */     BufferChecks.checkBuffer(params, 1);
/* 612 */     nglGetUniformSubroutineuiv(shadertype, location, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetUniformSubroutineuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   @Deprecated
/*     */   public static int glGetUniformSubroutineu(int shadertype, int location)
/*     */   {
/* 623 */     return glGetUniformSubroutineui(shadertype, location);
/*     */   }
/*     */ 
/*     */   public static int glGetUniformSubroutineui(int shadertype, int location)
/*     */   {
/* 628 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 629 */     long function_pointer = caps.glGetUniformSubroutineuiv;
/* 630 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 631 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 632 */     nglGetUniformSubroutineuiv(shadertype, location, MemoryUtil.getAddress(params), function_pointer);
/* 633 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetProgramStage(int program, int shadertype, int pname, IntBuffer values) {
/* 637 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 638 */     long function_pointer = caps.glGetProgramStageiv;
/* 639 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 640 */     BufferChecks.checkBuffer(values, 1);
/* 641 */     nglGetProgramStageiv(program, shadertype, pname, MemoryUtil.getAddress(values), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetProgramStageiv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   @Deprecated
/*     */   public static int glGetProgramStage(int program, int shadertype, int pname)
/*     */   {
/* 652 */     return glGetProgramStagei(program, shadertype, pname);
/*     */   }
/*     */ 
/*     */   public static int glGetProgramStagei(int program, int shadertype, int pname)
/*     */   {
/* 657 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 658 */     long function_pointer = caps.glGetProgramStageiv;
/* 659 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 660 */     IntBuffer values = APIUtil.getBufferInt(caps);
/* 661 */     nglGetProgramStageiv(program, shadertype, pname, MemoryUtil.getAddress(values), function_pointer);
/* 662 */     return values.get(0);
/*     */   }
/*     */ 
/*     */   public static void glPatchParameteri(int pname, int value) {
/* 666 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 667 */     long function_pointer = caps.glPatchParameteri;
/* 668 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 669 */     nglPatchParameteri(pname, value, function_pointer);
/*     */   }
/*     */   static native void nglPatchParameteri(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glPatchParameter(int pname, FloatBuffer values) {
/* 674 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 675 */     long function_pointer = caps.glPatchParameterfv;
/* 676 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 677 */     BufferChecks.checkBuffer(values, 4);
/* 678 */     nglPatchParameterfv(pname, MemoryUtil.getAddress(values), function_pointer);
/*     */   }
/*     */   static native void nglPatchParameterfv(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glBindTransformFeedback(int target, int id) {
/* 683 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 684 */     long function_pointer = caps.glBindTransformFeedback;
/* 685 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 686 */     nglBindTransformFeedback(target, id, function_pointer);
/*     */   }
/*     */   static native void nglBindTransformFeedback(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glDeleteTransformFeedbacks(IntBuffer ids) {
/* 691 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 692 */     long function_pointer = caps.glDeleteTransformFeedbacks;
/* 693 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 694 */     BufferChecks.checkDirect(ids);
/* 695 */     nglDeleteTransformFeedbacks(ids.remaining(), MemoryUtil.getAddress(ids), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglDeleteTransformFeedbacks(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDeleteTransformFeedbacks(int id) {
/* 701 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 702 */     long function_pointer = caps.glDeleteTransformFeedbacks;
/* 703 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 704 */     nglDeleteTransformFeedbacks(1, APIUtil.getInt(caps, id), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glGenTransformFeedbacks(IntBuffer ids) {
/* 708 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 709 */     long function_pointer = caps.glGenTransformFeedbacks;
/* 710 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 711 */     BufferChecks.checkDirect(ids);
/* 712 */     nglGenTransformFeedbacks(ids.remaining(), MemoryUtil.getAddress(ids), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGenTransformFeedbacks(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGenTransformFeedbacks() {
/* 718 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 719 */     long function_pointer = caps.glGenTransformFeedbacks;
/* 720 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 721 */     IntBuffer ids = APIUtil.getBufferInt(caps);
/* 722 */     nglGenTransformFeedbacks(1, MemoryUtil.getAddress(ids), function_pointer);
/* 723 */     return ids.get(0);
/*     */   }
/*     */ 
/*     */   public static boolean glIsTransformFeedback(int id) {
/* 727 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 728 */     long function_pointer = caps.glIsTransformFeedback;
/* 729 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 730 */     boolean __result = nglIsTransformFeedback(id, function_pointer);
/* 731 */     return __result;
/*     */   }
/*     */   static native boolean nglIsTransformFeedback(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glPauseTransformFeedback() {
/* 736 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 737 */     long function_pointer = caps.glPauseTransformFeedback;
/* 738 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 739 */     nglPauseTransformFeedback(function_pointer);
/*     */   }
/*     */   static native void nglPauseTransformFeedback(long paramLong);
/*     */ 
/*     */   public static void glResumeTransformFeedback() {
/* 744 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 745 */     long function_pointer = caps.glResumeTransformFeedback;
/* 746 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 747 */     nglResumeTransformFeedback(function_pointer);
/*     */   }
/*     */   static native void nglResumeTransformFeedback(long paramLong);
/*     */ 
/*     */   public static void glDrawTransformFeedback(int mode, int id) {
/* 752 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 753 */     long function_pointer = caps.glDrawTransformFeedback;
/* 754 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 755 */     nglDrawTransformFeedback(mode, id, function_pointer);
/*     */   }
/*     */   static native void nglDrawTransformFeedback(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glDrawTransformFeedbackStream(int mode, int id, int stream) {
/* 760 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 761 */     long function_pointer = caps.glDrawTransformFeedbackStream;
/* 762 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 763 */     nglDrawTransformFeedbackStream(mode, id, stream, function_pointer);
/*     */   }
/*     */   static native void nglDrawTransformFeedbackStream(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glBeginQueryIndexed(int target, int index, int id) {
/* 768 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 769 */     long function_pointer = caps.glBeginQueryIndexed;
/* 770 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 771 */     nglBeginQueryIndexed(target, index, id, function_pointer);
/*     */   }
/*     */   static native void nglBeginQueryIndexed(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glEndQueryIndexed(int target, int index) {
/* 776 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 777 */     long function_pointer = caps.glEndQueryIndexed;
/* 778 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 779 */     nglEndQueryIndexed(target, index, function_pointer);
/*     */   }
/*     */   static native void nglEndQueryIndexed(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glGetQueryIndexed(int target, int index, int pname, IntBuffer params) {
/* 784 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 785 */     long function_pointer = caps.glGetQueryIndexediv;
/* 786 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 787 */     BufferChecks.checkBuffer(params, 1);
/* 788 */     nglGetQueryIndexediv(target, index, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetQueryIndexediv(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   @Deprecated
/*     */   public static int glGetQueryIndexed(int target, int index, int pname)
/*     */   {
/* 799 */     return glGetQueryIndexedi(target, index, pname);
/*     */   }
/*     */ 
/*     */   public static int glGetQueryIndexedi(int target, int index, int pname)
/*     */   {
/* 804 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 805 */     long function_pointer = caps.glGetQueryIndexediv;
/* 806 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 807 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 808 */     nglGetQueryIndexediv(target, index, pname, MemoryUtil.getAddress(params), function_pointer);
/* 809 */     return params.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL40
 * JD-Core Version:    0.6.2
 */