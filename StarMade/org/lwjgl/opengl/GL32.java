/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class GL32
/*     */ {
/*     */   public static final int GL_CONTEXT_PROFILE_MASK = 37158;
/*     */   public static final int GL_CONTEXT_CORE_PROFILE_BIT = 1;
/*     */   public static final int GL_CONTEXT_COMPATIBILITY_PROFILE_BIT = 2;
/*     */   public static final int GL_MAX_VERTEX_OUTPUT_COMPONENTS = 37154;
/*     */   public static final int GL_MAX_GEOMETRY_INPUT_COMPONENTS = 37155;
/*     */   public static final int GL_MAX_GEOMETRY_OUTPUT_COMPONENTS = 37156;
/*     */   public static final int GL_MAX_FRAGMENT_INPUT_COMPONENTS = 37157;
/*     */   public static final int GL_FIRST_VERTEX_CONVENTION = 36429;
/*     */   public static final int GL_LAST_VERTEX_CONVENTION = 36430;
/*     */   public static final int GL_PROVOKING_VERTEX = 36431;
/*     */   public static final int GL_QUADS_FOLLOW_PROVOKING_VERTEX_CONVENTION = 36428;
/*     */   public static final int GL_TEXTURE_CUBE_MAP_SEAMLESS = 34895;
/*     */   public static final int GL_SAMPLE_POSITION = 36432;
/*     */   public static final int GL_SAMPLE_MASK = 36433;
/*     */   public static final int GL_SAMPLE_MASK_VALUE = 36434;
/*     */   public static final int GL_TEXTURE_2D_MULTISAMPLE = 37120;
/*     */   public static final int GL_PROXY_TEXTURE_2D_MULTISAMPLE = 37121;
/*     */   public static final int GL_TEXTURE_2D_MULTISAMPLE_ARRAY = 37122;
/*     */   public static final int GL_PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY = 37123;
/*     */   public static final int GL_MAX_SAMPLE_MASK_WORDS = 36441;
/*     */   public static final int GL_MAX_COLOR_TEXTURE_SAMPLES = 37134;
/*     */   public static final int GL_MAX_DEPTH_TEXTURE_SAMPLES = 37135;
/*     */   public static final int GL_MAX_INTEGER_SAMPLES = 37136;
/*     */   public static final int GL_TEXTURE_BINDING_2D_MULTISAMPLE = 37124;
/*     */   public static final int GL_TEXTURE_BINDING_2D_MULTISAMPLE_ARRAY = 37125;
/*     */   public static final int GL_TEXTURE_SAMPLES = 37126;
/*     */   public static final int GL_TEXTURE_FIXED_SAMPLE_LOCATIONS = 37127;
/*     */   public static final int GL_SAMPLER_2D_MULTISAMPLE = 37128;
/*     */   public static final int GL_INT_SAMPLER_2D_MULTISAMPLE = 37129;
/*     */   public static final int GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE = 37130;
/*     */   public static final int GL_SAMPLER_2D_MULTISAMPLE_ARRAY = 37131;
/*     */   public static final int GL_INT_SAMPLER_2D_MULTISAMPLE_ARRAY = 37132;
/*     */   public static final int GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE_ARRAY = 37133;
/*     */   public static final int GL_DEPTH_CLAMP = 34383;
/*     */   public static final int GL_GEOMETRY_SHADER = 36313;
/*     */   public static final int GL_GEOMETRY_VERTICES_OUT = 36314;
/*     */   public static final int GL_GEOMETRY_INPUT_TYPE = 36315;
/*     */   public static final int GL_GEOMETRY_OUTPUT_TYPE = 36316;
/*     */   public static final int GL_MAX_GEOMETRY_TEXTURE_IMAGE_UNITS = 35881;
/*     */   public static final int GL_MAX_VARYING_COMPONENTS = 35659;
/*     */   public static final int GL_MAX_GEOMETRY_UNIFORM_COMPONENTS = 36319;
/*     */   public static final int GL_MAX_GEOMETRY_OUTPUT_VERTICES = 36320;
/*     */   public static final int GL_MAX_GEOMETRY_TOTAL_OUTPUT_COMPONENTS = 36321;
/*     */   public static final int GL_LINES_ADJACENCY = 10;
/*     */   public static final int GL_LINE_STRIP_ADJACENCY = 11;
/*     */   public static final int GL_TRIANGLES_ADJACENCY = 12;
/*     */   public static final int GL_TRIANGLE_STRIP_ADJACENCY = 13;
/*     */   public static final int GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS = 36264;
/*     */   public static final int GL_FRAMEBUFFER_ATTACHMENT_LAYERED = 36263;
/*     */   public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER = 36052;
/*     */   public static final int GL_PROGRAM_POINT_SIZE = 34370;
/*     */   public static final int GL_MAX_SERVER_WAIT_TIMEOUT = 37137;
/*     */   public static final int GL_OBJECT_TYPE = 37138;
/*     */   public static final int GL_SYNC_CONDITION = 37139;
/*     */   public static final int GL_SYNC_STATUS = 37140;
/*     */   public static final int GL_SYNC_FLAGS = 37141;
/*     */   public static final int GL_SYNC_FENCE = 37142;
/*     */   public static final int GL_SYNC_GPU_COMMANDS_COMPLETE = 37143;
/*     */   public static final int GL_UNSIGNALED = 37144;
/*     */   public static final int GL_SIGNALED = 37145;
/*     */   public static final int GL_SYNC_FLUSH_COMMANDS_BIT = 1;
/*     */   public static final long GL_TIMEOUT_IGNORED = -1L;
/*     */   public static final int GL_ALREADY_SIGNALED = 37146;
/*     */   public static final int GL_TIMEOUT_EXPIRED = 37147;
/*     */   public static final int GL_CONDITION_SATISFIED = 37148;
/*     */   public static final int GL_WAIT_FAILED = 37149;
/*     */ 
/*     */   public static void glGetBufferParameter(int target, int pname, LongBuffer params)
/*     */   {
/* 215 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 216 */     long function_pointer = caps.glGetBufferParameteri64v;
/* 217 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 218 */     BufferChecks.checkBuffer(params, 4);
/* 219 */     nglGetBufferParameteri64v(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetBufferParameteri64v(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   @Deprecated
/*     */   public static long glGetBufferParameter(int target, int pname)
/*     */   {
/* 230 */     return glGetBufferParameteri64(target, pname);
/*     */   }
/*     */ 
/*     */   public static long glGetBufferParameteri64(int target, int pname)
/*     */   {
/* 235 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 236 */     long function_pointer = caps.glGetBufferParameteri64v;
/* 237 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 238 */     LongBuffer params = APIUtil.getBufferLong(caps);
/* 239 */     nglGetBufferParameteri64v(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 240 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glDrawElementsBaseVertex(int mode, ByteBuffer indices, int basevertex) {
/* 244 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 245 */     long function_pointer = caps.glDrawElementsBaseVertex;
/* 246 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 247 */     GLChecks.ensureElementVBOdisabled(caps);
/* 248 */     BufferChecks.checkDirect(indices);
/* 249 */     nglDrawElementsBaseVertex(mode, indices.remaining(), 5121, MemoryUtil.getAddress(indices), basevertex, function_pointer);
/*     */   }
/*     */   public static void glDrawElementsBaseVertex(int mode, IntBuffer indices, int basevertex) {
/* 252 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 253 */     long function_pointer = caps.glDrawElementsBaseVertex;
/* 254 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 255 */     GLChecks.ensureElementVBOdisabled(caps);
/* 256 */     BufferChecks.checkDirect(indices);
/* 257 */     nglDrawElementsBaseVertex(mode, indices.remaining(), 5125, MemoryUtil.getAddress(indices), basevertex, function_pointer);
/*     */   }
/*     */   public static void glDrawElementsBaseVertex(int mode, ShortBuffer indices, int basevertex) {
/* 260 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 261 */     long function_pointer = caps.glDrawElementsBaseVertex;
/* 262 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 263 */     GLChecks.ensureElementVBOdisabled(caps);
/* 264 */     BufferChecks.checkDirect(indices);
/* 265 */     nglDrawElementsBaseVertex(mode, indices.remaining(), 5123, MemoryUtil.getAddress(indices), basevertex, function_pointer);
/*     */   }
/*     */   static native void nglDrawElementsBaseVertex(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, long paramLong2);
/*     */ 
/* 269 */   public static void glDrawElementsBaseVertex(int mode, int indices_count, int type, long indices_buffer_offset, int basevertex) { ContextCapabilities caps = GLContext.getCapabilities();
/* 270 */     long function_pointer = caps.glDrawElementsBaseVertex;
/* 271 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 272 */     GLChecks.ensureElementVBOenabled(caps);
/* 273 */     nglDrawElementsBaseVertexBO(mode, indices_count, type, indices_buffer_offset, basevertex, function_pointer); }
/*     */ 
/*     */   static native void nglDrawElementsBaseVertexBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, long paramLong2);
/*     */ 
/*     */   public static void glDrawRangeElementsBaseVertex(int mode, int start, int end, ByteBuffer indices, int basevertex) {
/* 278 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 279 */     long function_pointer = caps.glDrawRangeElementsBaseVertex;
/* 280 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 281 */     GLChecks.ensureElementVBOdisabled(caps);
/* 282 */     BufferChecks.checkDirect(indices);
/* 283 */     nglDrawRangeElementsBaseVertex(mode, start, end, indices.remaining(), 5121, MemoryUtil.getAddress(indices), basevertex, function_pointer);
/*     */   }
/*     */   public static void glDrawRangeElementsBaseVertex(int mode, int start, int end, IntBuffer indices, int basevertex) {
/* 286 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 287 */     long function_pointer = caps.glDrawRangeElementsBaseVertex;
/* 288 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 289 */     GLChecks.ensureElementVBOdisabled(caps);
/* 290 */     BufferChecks.checkDirect(indices);
/* 291 */     nglDrawRangeElementsBaseVertex(mode, start, end, indices.remaining(), 5125, MemoryUtil.getAddress(indices), basevertex, function_pointer);
/*     */   }
/*     */   public static void glDrawRangeElementsBaseVertex(int mode, int start, int end, ShortBuffer indices, int basevertex) {
/* 294 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 295 */     long function_pointer = caps.glDrawRangeElementsBaseVertex;
/* 296 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 297 */     GLChecks.ensureElementVBOdisabled(caps);
/* 298 */     BufferChecks.checkDirect(indices);
/* 299 */     nglDrawRangeElementsBaseVertex(mode, start, end, indices.remaining(), 5123, MemoryUtil.getAddress(indices), basevertex, function_pointer);
/*     */   }
/*     */   static native void nglDrawRangeElementsBaseVertex(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, int paramInt6, long paramLong2);
/*     */ 
/* 303 */   public static void glDrawRangeElementsBaseVertex(int mode, int start, int end, int indices_count, int type, long indices_buffer_offset, int basevertex) { ContextCapabilities caps = GLContext.getCapabilities();
/* 304 */     long function_pointer = caps.glDrawRangeElementsBaseVertex;
/* 305 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 306 */     GLChecks.ensureElementVBOenabled(caps);
/* 307 */     nglDrawRangeElementsBaseVertexBO(mode, start, end, indices_count, type, indices_buffer_offset, basevertex, function_pointer); }
/*     */ 
/*     */   static native void nglDrawRangeElementsBaseVertexBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, int paramInt6, long paramLong2);
/*     */ 
/*     */   public static void glDrawElementsInstancedBaseVertex(int mode, ByteBuffer indices, int primcount, int basevertex) {
/* 312 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 313 */     long function_pointer = caps.glDrawElementsInstancedBaseVertex;
/* 314 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 315 */     GLChecks.ensureElementVBOdisabled(caps);
/* 316 */     BufferChecks.checkDirect(indices);
/* 317 */     nglDrawElementsInstancedBaseVertex(mode, indices.remaining(), 5121, MemoryUtil.getAddress(indices), primcount, basevertex, function_pointer);
/*     */   }
/*     */   public static void glDrawElementsInstancedBaseVertex(int mode, IntBuffer indices, int primcount, int basevertex) {
/* 320 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 321 */     long function_pointer = caps.glDrawElementsInstancedBaseVertex;
/* 322 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 323 */     GLChecks.ensureElementVBOdisabled(caps);
/* 324 */     BufferChecks.checkDirect(indices);
/* 325 */     nglDrawElementsInstancedBaseVertex(mode, indices.remaining(), 5125, MemoryUtil.getAddress(indices), primcount, basevertex, function_pointer);
/*     */   }
/*     */   public static void glDrawElementsInstancedBaseVertex(int mode, ShortBuffer indices, int primcount, int basevertex) {
/* 328 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 329 */     long function_pointer = caps.glDrawElementsInstancedBaseVertex;
/* 330 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 331 */     GLChecks.ensureElementVBOdisabled(caps);
/* 332 */     BufferChecks.checkDirect(indices);
/* 333 */     nglDrawElementsInstancedBaseVertex(mode, indices.remaining(), 5123, MemoryUtil.getAddress(indices), primcount, basevertex, function_pointer);
/*     */   }
/*     */   static native void nglDrawElementsInstancedBaseVertex(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, int paramInt5, long paramLong2);
/*     */ 
/* 337 */   public static void glDrawElementsInstancedBaseVertex(int mode, int indices_count, int type, long indices_buffer_offset, int primcount, int basevertex) { ContextCapabilities caps = GLContext.getCapabilities();
/* 338 */     long function_pointer = caps.glDrawElementsInstancedBaseVertex;
/* 339 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 340 */     GLChecks.ensureElementVBOenabled(caps);
/* 341 */     nglDrawElementsInstancedBaseVertexBO(mode, indices_count, type, indices_buffer_offset, primcount, basevertex, function_pointer); }
/*     */ 
/*     */   static native void nglDrawElementsInstancedBaseVertexBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, int paramInt5, long paramLong2);
/*     */ 
/*     */   public static void glProvokingVertex(int mode) {
/* 346 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 347 */     long function_pointer = caps.glProvokingVertex;
/* 348 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 349 */     nglProvokingVertex(mode, function_pointer);
/*     */   }
/*     */   static native void nglProvokingVertex(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glTexImage2DMultisample(int target, int samples, int internalformat, int width, int height, boolean fixedsamplelocations) {
/* 354 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 355 */     long function_pointer = caps.glTexImage2DMultisample;
/* 356 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 357 */     nglTexImage2DMultisample(target, samples, internalformat, width, height, fixedsamplelocations, function_pointer);
/*     */   }
/*     */   static native void nglTexImage2DMultisample(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean, long paramLong);
/*     */ 
/*     */   public static void glTexImage3DMultisample(int target, int samples, int internalformat, int width, int height, int depth, boolean fixedsamplelocations) {
/* 362 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 363 */     long function_pointer = caps.glTexImage3DMultisample;
/* 364 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 365 */     nglTexImage3DMultisample(target, samples, internalformat, width, height, depth, fixedsamplelocations, function_pointer);
/*     */   }
/*     */   static native void nglTexImage3DMultisample(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, long paramLong);
/*     */ 
/*     */   public static void glGetMultisample(int pname, int index, FloatBuffer val) {
/* 370 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 371 */     long function_pointer = caps.glGetMultisamplefv;
/* 372 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 373 */     BufferChecks.checkBuffer(val, 2);
/* 374 */     nglGetMultisamplefv(pname, index, MemoryUtil.getAddress(val), function_pointer);
/*     */   }
/*     */   static native void nglGetMultisamplefv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glSampleMaski(int index, int mask) {
/* 379 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 380 */     long function_pointer = caps.glSampleMaski;
/* 381 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 382 */     nglSampleMaski(index, mask, function_pointer);
/*     */   }
/*     */   static native void nglSampleMaski(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glFramebufferTexture(int target, int attachment, int texture, int level) {
/* 387 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 388 */     long function_pointer = caps.glFramebufferTexture;
/* 389 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 390 */     nglFramebufferTexture(target, attachment, texture, level, function_pointer);
/*     */   }
/*     */   static native void nglFramebufferTexture(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static GLSync glFenceSync(int condition, int flags) {
/* 395 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 396 */     long function_pointer = caps.glFenceSync;
/* 397 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 398 */     GLSync __result = new GLSync(nglFenceSync(condition, flags, function_pointer));
/* 399 */     return __result;
/*     */   }
/*     */   static native long nglFenceSync(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static boolean glIsSync(GLSync sync) {
/* 404 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 405 */     long function_pointer = caps.glIsSync;
/* 406 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 407 */     boolean __result = nglIsSync(sync.getPointer(), function_pointer);
/* 408 */     return __result;
/*     */   }
/*     */   static native boolean nglIsSync(long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDeleteSync(GLSync sync) {
/* 413 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 414 */     long function_pointer = caps.glDeleteSync;
/* 415 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 416 */     nglDeleteSync(sync.getPointer(), function_pointer);
/*     */   }
/*     */   static native void nglDeleteSync(long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glClientWaitSync(GLSync sync, int flags, long timeout) {
/* 421 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 422 */     long function_pointer = caps.glClientWaitSync;
/* 423 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 424 */     int __result = nglClientWaitSync(sync.getPointer(), flags, timeout, function_pointer);
/* 425 */     return __result;
/*     */   }
/*     */   static native int nglClientWaitSync(long paramLong1, int paramInt, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glWaitSync(GLSync sync, int flags, long timeout) {
/* 430 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 431 */     long function_pointer = caps.glWaitSync;
/* 432 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 433 */     nglWaitSync(sync.getPointer(), flags, timeout, function_pointer);
/*     */   }
/*     */   static native void nglWaitSync(long paramLong1, int paramInt, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glGetInteger64(int pname, LongBuffer data) {
/* 438 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 439 */     long function_pointer = caps.glGetInteger64v;
/* 440 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 441 */     BufferChecks.checkBuffer(data, 1);
/* 442 */     nglGetInteger64v(pname, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetInteger64v(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static long glGetInteger64(int pname) {
/* 448 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 449 */     long function_pointer = caps.glGetInteger64v;
/* 450 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 451 */     LongBuffer data = APIUtil.getBufferLong(caps);
/* 452 */     nglGetInteger64v(pname, MemoryUtil.getAddress(data), function_pointer);
/* 453 */     return data.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetInteger64(int value, int index, LongBuffer data) {
/* 457 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 458 */     long function_pointer = caps.glGetInteger64i_v;
/* 459 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 460 */     BufferChecks.checkBuffer(data, 4);
/* 461 */     nglGetInteger64i_v(value, index, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetInteger64i_v(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static long glGetInteger64(int value, int index) {
/* 467 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 468 */     long function_pointer = caps.glGetInteger64i_v;
/* 469 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 470 */     LongBuffer data = APIUtil.getBufferLong(caps);
/* 471 */     nglGetInteger64i_v(value, index, MemoryUtil.getAddress(data), function_pointer);
/* 472 */     return data.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetSync(GLSync sync, int pname, IntBuffer length, IntBuffer values) {
/* 476 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 477 */     long function_pointer = caps.glGetSynciv;
/* 478 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 479 */     if (length != null)
/* 480 */       BufferChecks.checkBuffer(length, 1);
/* 481 */     BufferChecks.checkDirect(values);
/* 482 */     nglGetSynciv(sync.getPointer(), pname, values.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(values), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetSynciv(long paramLong1, int paramInt1, int paramInt2, long paramLong2, long paramLong3, long paramLong4);
/*     */ 
/*     */   @Deprecated
/*     */   public static int glGetSync(GLSync sync, int pname)
/*     */   {
/* 493 */     return glGetSynci(sync, pname);
/*     */   }
/*     */ 
/*     */   public static int glGetSynci(GLSync sync, int pname)
/*     */   {
/* 498 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 499 */     long function_pointer = caps.glGetSynciv;
/* 500 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 501 */     IntBuffer values = APIUtil.getBufferInt(caps);
/* 502 */     nglGetSynciv(sync.getPointer(), pname, 1, 0L, MemoryUtil.getAddress(values), function_pointer);
/* 503 */     return values.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL32
 * JD-Core Version:    0.6.2
 */