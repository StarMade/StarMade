/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class GL33
/*     */ {
/*     */   public static final int GL_SRC1_COLOR = 35065;
/*     */   public static final int GL_SRC1_ALPHA = 34185;
/*     */   public static final int GL_ONE_MINUS_SRC1_COLOR = 35066;
/*     */   public static final int GL_ONE_MINUS_SRC1_ALPHA = 35067;
/*     */   public static final int GL_MAX_DUAL_SOURCE_DRAW_BUFFERS = 35068;
/*     */   public static final int GL_ANY_SAMPLES_PASSED = 35887;
/*     */   public static final int GL_SAMPLER_BINDING = 35097;
/*     */   public static final int GL_RGB10_A2UI = 36975;
/*     */   public static final int GL_TEXTURE_SWIZZLE_R = 36418;
/*     */   public static final int GL_TEXTURE_SWIZZLE_G = 36419;
/*     */   public static final int GL_TEXTURE_SWIZZLE_B = 36420;
/*     */   public static final int GL_TEXTURE_SWIZZLE_A = 36421;
/*     */   public static final int GL_TEXTURE_SWIZZLE_RGBA = 36422;
/*     */   public static final int GL_TIME_ELAPSED = 35007;
/*     */   public static final int GL_TIMESTAMP = 36392;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_DIVISOR = 35070;
/*     */   public static final int GL_INT_2_10_10_10_REV = 36255;
/*     */ 
/*     */   public static void glBindFragDataLocationIndexed(int program, int colorNumber, int index, ByteBuffer name)
/*     */   {
/*  91 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  92 */     long function_pointer = caps.glBindFragDataLocationIndexed;
/*  93 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  94 */     BufferChecks.checkDirect(name);
/*  95 */     BufferChecks.checkNullTerminated(name);
/*  96 */     nglBindFragDataLocationIndexed(program, colorNumber, index, MemoryUtil.getAddress(name), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglBindFragDataLocationIndexed(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glBindFragDataLocationIndexed(int program, int colorNumber, int index, CharSequence name) {
/* 102 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 103 */     long function_pointer = caps.glBindFragDataLocationIndexed;
/* 104 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 105 */     nglBindFragDataLocationIndexed(program, colorNumber, index, APIUtil.getBufferNT(caps, name), function_pointer);
/*     */   }
/*     */ 
/*     */   public static int glGetFragDataIndex(int program, ByteBuffer name) {
/* 109 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 110 */     long function_pointer = caps.glGetFragDataIndex;
/* 111 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 112 */     BufferChecks.checkDirect(name);
/* 113 */     BufferChecks.checkNullTerminated(name);
/* 114 */     int __result = nglGetFragDataIndex(program, MemoryUtil.getAddress(name), function_pointer);
/* 115 */     return __result;
/*     */   }
/*     */ 
/*     */   static native int nglGetFragDataIndex(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetFragDataIndex(int program, CharSequence name) {
/* 121 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 122 */     long function_pointer = caps.glGetFragDataIndex;
/* 123 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 124 */     int __result = nglGetFragDataIndex(program, APIUtil.getBufferNT(caps, name), function_pointer);
/* 125 */     return __result;
/*     */   }
/*     */ 
/*     */   public static void glGenSamplers(IntBuffer samplers) {
/* 129 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 130 */     long function_pointer = caps.glGenSamplers;
/* 131 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 132 */     BufferChecks.checkDirect(samplers);
/* 133 */     nglGenSamplers(samplers.remaining(), MemoryUtil.getAddress(samplers), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGenSamplers(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGenSamplers() {
/* 139 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 140 */     long function_pointer = caps.glGenSamplers;
/* 141 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 142 */     IntBuffer samplers = APIUtil.getBufferInt(caps);
/* 143 */     nglGenSamplers(1, MemoryUtil.getAddress(samplers), function_pointer);
/* 144 */     return samplers.get(0);
/*     */   }
/*     */ 
/*     */   public static void glDeleteSamplers(IntBuffer samplers) {
/* 148 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 149 */     long function_pointer = caps.glDeleteSamplers;
/* 150 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 151 */     BufferChecks.checkDirect(samplers);
/* 152 */     nglDeleteSamplers(samplers.remaining(), MemoryUtil.getAddress(samplers), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglDeleteSamplers(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDeleteSamplers(int sampler) {
/* 158 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 159 */     long function_pointer = caps.glDeleteSamplers;
/* 160 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 161 */     nglDeleteSamplers(1, APIUtil.getInt(caps, sampler), function_pointer);
/*     */   }
/*     */ 
/*     */   public static boolean glIsSampler(int sampler) {
/* 165 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 166 */     long function_pointer = caps.glIsSampler;
/* 167 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 168 */     boolean __result = nglIsSampler(sampler, function_pointer);
/* 169 */     return __result;
/*     */   }
/*     */   static native boolean nglIsSampler(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glBindSampler(int unit, int sampler) {
/* 174 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 175 */     long function_pointer = caps.glBindSampler;
/* 176 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 177 */     nglBindSampler(unit, sampler, function_pointer);
/*     */   }
/*     */   static native void nglBindSampler(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glSamplerParameteri(int sampler, int pname, int param) {
/* 182 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 183 */     long function_pointer = caps.glSamplerParameteri;
/* 184 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 185 */     nglSamplerParameteri(sampler, pname, param, function_pointer);
/*     */   }
/*     */   static native void nglSamplerParameteri(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glSamplerParameterf(int sampler, int pname, float param) {
/* 190 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 191 */     long function_pointer = caps.glSamplerParameterf;
/* 192 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 193 */     nglSamplerParameterf(sampler, pname, param, function_pointer);
/*     */   }
/*     */   static native void nglSamplerParameterf(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/*     */ 
/*     */   public static void glSamplerParameter(int sampler, int pname, IntBuffer params) {
/* 198 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 199 */     long function_pointer = caps.glSamplerParameteriv;
/* 200 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 201 */     BufferChecks.checkBuffer(params, 4);
/* 202 */     nglSamplerParameteriv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglSamplerParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glSamplerParameter(int sampler, int pname, FloatBuffer params) {
/* 207 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 208 */     long function_pointer = caps.glSamplerParameterfv;
/* 209 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 210 */     BufferChecks.checkBuffer(params, 4);
/* 211 */     nglSamplerParameterfv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglSamplerParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glSamplerParameterI(int sampler, int pname, IntBuffer params) {
/* 216 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 217 */     long function_pointer = caps.glSamplerParameterIiv;
/* 218 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 219 */     BufferChecks.checkBuffer(params, 4);
/* 220 */     nglSamplerParameterIiv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglSamplerParameterIiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glSamplerParameterIu(int sampler, int pname, IntBuffer params) {
/* 225 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 226 */     long function_pointer = caps.glSamplerParameterIuiv;
/* 227 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 228 */     BufferChecks.checkBuffer(params, 4);
/* 229 */     nglSamplerParameterIuiv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglSamplerParameterIuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetSamplerParameter(int sampler, int pname, IntBuffer params) {
/* 234 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 235 */     long function_pointer = caps.glGetSamplerParameteriv;
/* 236 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 237 */     BufferChecks.checkBuffer(params, 4);
/* 238 */     nglGetSamplerParameteriv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetSamplerParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetSamplerParameteri(int sampler, int pname) {
/* 244 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 245 */     long function_pointer = caps.glGetSamplerParameteriv;
/* 246 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 247 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 248 */     nglGetSamplerParameteriv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/* 249 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetSamplerParameter(int sampler, int pname, FloatBuffer params) {
/* 253 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 254 */     long function_pointer = caps.glGetSamplerParameterfv;
/* 255 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 256 */     BufferChecks.checkBuffer(params, 4);
/* 257 */     nglGetSamplerParameterfv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetSamplerParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static float glGetSamplerParameterf(int sampler, int pname) {
/* 263 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 264 */     long function_pointer = caps.glGetSamplerParameterfv;
/* 265 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 266 */     FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 267 */     nglGetSamplerParameterfv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/* 268 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetSamplerParameterI(int sampler, int pname, IntBuffer params) {
/* 272 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 273 */     long function_pointer = caps.glGetSamplerParameterIiv;
/* 274 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 275 */     BufferChecks.checkBuffer(params, 4);
/* 276 */     nglGetSamplerParameterIiv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetSamplerParameterIiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetSamplerParameterIi(int sampler, int pname) {
/* 282 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 283 */     long function_pointer = caps.glGetSamplerParameterIiv;
/* 284 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 285 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 286 */     nglGetSamplerParameterIiv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/* 287 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetSamplerParameterIu(int sampler, int pname, IntBuffer params) {
/* 291 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 292 */     long function_pointer = caps.glGetSamplerParameterIuiv;
/* 293 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 294 */     BufferChecks.checkBuffer(params, 4);
/* 295 */     nglGetSamplerParameterIuiv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetSamplerParameterIuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetSamplerParameterIui(int sampler, int pname) {
/* 301 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 302 */     long function_pointer = caps.glGetSamplerParameterIuiv;
/* 303 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 304 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 305 */     nglGetSamplerParameterIuiv(sampler, pname, MemoryUtil.getAddress(params), function_pointer);
/* 306 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glQueryCounter(int id, int target) {
/* 310 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 311 */     long function_pointer = caps.glQueryCounter;
/* 312 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 313 */     nglQueryCounter(id, target, function_pointer);
/*     */   }
/*     */   static native void nglQueryCounter(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glGetQueryObject(int id, int pname, LongBuffer params) {
/* 318 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 319 */     long function_pointer = caps.glGetQueryObjecti64v;
/* 320 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 321 */     BufferChecks.checkBuffer(params, 1);
/* 322 */     nglGetQueryObjecti64v(id, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetQueryObjecti64v(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   @Deprecated
/*     */   public static long glGetQueryObject(int id, int pname)
/*     */   {
/* 333 */     return glGetQueryObjecti64(id, pname);
/*     */   }
/*     */ 
/*     */   public static long glGetQueryObjecti64(int id, int pname)
/*     */   {
/* 338 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 339 */     long function_pointer = caps.glGetQueryObjecti64v;
/* 340 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 341 */     LongBuffer params = APIUtil.getBufferLong(caps);
/* 342 */     nglGetQueryObjecti64v(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 343 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetQueryObjectu(int id, int pname, LongBuffer params) {
/* 347 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 348 */     long function_pointer = caps.glGetQueryObjectui64v;
/* 349 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 350 */     BufferChecks.checkBuffer(params, 1);
/* 351 */     nglGetQueryObjectui64v(id, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetQueryObjectui64v(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   @Deprecated
/*     */   public static long glGetQueryObjectu(int id, int pname)
/*     */   {
/* 362 */     return glGetQueryObjectui64(id, pname);
/*     */   }
/*     */ 
/*     */   public static long glGetQueryObjectui64(int id, int pname)
/*     */   {
/* 367 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 368 */     long function_pointer = caps.glGetQueryObjectui64v;
/* 369 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 370 */     LongBuffer params = APIUtil.getBufferLong(caps);
/* 371 */     nglGetQueryObjectui64v(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 372 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glVertexAttribDivisor(int index, int divisor) {
/* 376 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 377 */     long function_pointer = caps.glVertexAttribDivisor;
/* 378 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 379 */     nglVertexAttribDivisor(index, divisor, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribDivisor(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glVertexP2ui(int type, int value) {
/* 384 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 385 */     long function_pointer = caps.glVertexP2ui;
/* 386 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 387 */     nglVertexP2ui(type, value, function_pointer);
/*     */   }
/*     */   static native void nglVertexP2ui(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glVertexP3ui(int type, int value) {
/* 392 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 393 */     long function_pointer = caps.glVertexP3ui;
/* 394 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 395 */     nglVertexP3ui(type, value, function_pointer);
/*     */   }
/*     */   static native void nglVertexP3ui(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glVertexP4ui(int type, int value) {
/* 400 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 401 */     long function_pointer = caps.glVertexP4ui;
/* 402 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 403 */     nglVertexP4ui(type, value, function_pointer);
/*     */   }
/*     */   static native void nglVertexP4ui(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glVertexP2u(int type, IntBuffer value) {
/* 408 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 409 */     long function_pointer = caps.glVertexP2uiv;
/* 410 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 411 */     BufferChecks.checkBuffer(value, 2);
/* 412 */     nglVertexP2uiv(type, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglVertexP2uiv(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexP3u(int type, IntBuffer value) {
/* 417 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 418 */     long function_pointer = caps.glVertexP3uiv;
/* 419 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 420 */     BufferChecks.checkBuffer(value, 3);
/* 421 */     nglVertexP3uiv(type, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglVertexP3uiv(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexP4u(int type, IntBuffer value) {
/* 426 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 427 */     long function_pointer = caps.glVertexP4uiv;
/* 428 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 429 */     BufferChecks.checkBuffer(value, 4);
/* 430 */     nglVertexP4uiv(type, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglVertexP4uiv(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glTexCoordP1ui(int type, int coords) {
/* 435 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 436 */     long function_pointer = caps.glTexCoordP1ui;
/* 437 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 438 */     nglTexCoordP1ui(type, coords, function_pointer);
/*     */   }
/*     */   static native void nglTexCoordP1ui(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glTexCoordP2ui(int type, int coords) {
/* 443 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 444 */     long function_pointer = caps.glTexCoordP2ui;
/* 445 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 446 */     nglTexCoordP2ui(type, coords, function_pointer);
/*     */   }
/*     */   static native void nglTexCoordP2ui(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glTexCoordP3ui(int type, int coords) {
/* 451 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 452 */     long function_pointer = caps.glTexCoordP3ui;
/* 453 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 454 */     nglTexCoordP3ui(type, coords, function_pointer);
/*     */   }
/*     */   static native void nglTexCoordP3ui(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glTexCoordP4ui(int type, int coords) {
/* 459 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 460 */     long function_pointer = caps.glTexCoordP4ui;
/* 461 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 462 */     nglTexCoordP4ui(type, coords, function_pointer);
/*     */   }
/*     */   static native void nglTexCoordP4ui(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glTexCoordP1u(int type, IntBuffer coords) {
/* 467 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 468 */     long function_pointer = caps.glTexCoordP1uiv;
/* 469 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 470 */     BufferChecks.checkBuffer(coords, 1);
/* 471 */     nglTexCoordP1uiv(type, MemoryUtil.getAddress(coords), function_pointer);
/*     */   }
/*     */   static native void nglTexCoordP1uiv(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glTexCoordP2u(int type, IntBuffer coords) {
/* 476 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 477 */     long function_pointer = caps.glTexCoordP2uiv;
/* 478 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 479 */     BufferChecks.checkBuffer(coords, 2);
/* 480 */     nglTexCoordP2uiv(type, MemoryUtil.getAddress(coords), function_pointer);
/*     */   }
/*     */   static native void nglTexCoordP2uiv(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glTexCoordP3u(int type, IntBuffer coords) {
/* 485 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 486 */     long function_pointer = caps.glTexCoordP3uiv;
/* 487 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 488 */     BufferChecks.checkBuffer(coords, 3);
/* 489 */     nglTexCoordP3uiv(type, MemoryUtil.getAddress(coords), function_pointer);
/*     */   }
/*     */   static native void nglTexCoordP3uiv(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glTexCoordP4u(int type, IntBuffer coords) {
/* 494 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 495 */     long function_pointer = caps.glTexCoordP4uiv;
/* 496 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 497 */     BufferChecks.checkBuffer(coords, 4);
/* 498 */     nglTexCoordP4uiv(type, MemoryUtil.getAddress(coords), function_pointer);
/*     */   }
/*     */   static native void nglTexCoordP4uiv(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glMultiTexCoordP1ui(int texture, int type, int coords) {
/* 503 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 504 */     long function_pointer = caps.glMultiTexCoordP1ui;
/* 505 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 506 */     nglMultiTexCoordP1ui(texture, type, coords, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoordP1ui(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoordP2ui(int texture, int type, int coords) {
/* 511 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 512 */     long function_pointer = caps.glMultiTexCoordP2ui;
/* 513 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 514 */     nglMultiTexCoordP2ui(texture, type, coords, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoordP2ui(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoordP3ui(int texture, int type, int coords) {
/* 519 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 520 */     long function_pointer = caps.glMultiTexCoordP3ui;
/* 521 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 522 */     nglMultiTexCoordP3ui(texture, type, coords, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoordP3ui(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoordP4ui(int texture, int type, int coords) {
/* 527 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 528 */     long function_pointer = caps.glMultiTexCoordP4ui;
/* 529 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 530 */     nglMultiTexCoordP4ui(texture, type, coords, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoordP4ui(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoordP1u(int texture, int type, IntBuffer coords) {
/* 535 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 536 */     long function_pointer = caps.glMultiTexCoordP1uiv;
/* 537 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 538 */     BufferChecks.checkBuffer(coords, 1);
/* 539 */     nglMultiTexCoordP1uiv(texture, type, MemoryUtil.getAddress(coords), function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoordP1uiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glMultiTexCoordP2u(int texture, int type, IntBuffer coords) {
/* 544 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 545 */     long function_pointer = caps.glMultiTexCoordP2uiv;
/* 546 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 547 */     BufferChecks.checkBuffer(coords, 2);
/* 548 */     nglMultiTexCoordP2uiv(texture, type, MemoryUtil.getAddress(coords), function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoordP2uiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glMultiTexCoordP3u(int texture, int type, IntBuffer coords) {
/* 553 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 554 */     long function_pointer = caps.glMultiTexCoordP3uiv;
/* 555 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 556 */     BufferChecks.checkBuffer(coords, 3);
/* 557 */     nglMultiTexCoordP3uiv(texture, type, MemoryUtil.getAddress(coords), function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoordP3uiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glMultiTexCoordP4u(int texture, int type, IntBuffer coords) {
/* 562 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 563 */     long function_pointer = caps.glMultiTexCoordP4uiv;
/* 564 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 565 */     BufferChecks.checkBuffer(coords, 4);
/* 566 */     nglMultiTexCoordP4uiv(texture, type, MemoryUtil.getAddress(coords), function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoordP4uiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glNormalP3ui(int type, int coords) {
/* 571 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 572 */     long function_pointer = caps.glNormalP3ui;
/* 573 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 574 */     nglNormalP3ui(type, coords, function_pointer);
/*     */   }
/*     */   static native void nglNormalP3ui(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glNormalP3u(int type, IntBuffer coords) {
/* 579 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 580 */     long function_pointer = caps.glNormalP3uiv;
/* 581 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 582 */     BufferChecks.checkBuffer(coords, 3);
/* 583 */     nglNormalP3uiv(type, MemoryUtil.getAddress(coords), function_pointer);
/*     */   }
/*     */   static native void nglNormalP3uiv(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glColorP3ui(int type, int color) {
/* 588 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 589 */     long function_pointer = caps.glColorP3ui;
/* 590 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 591 */     nglColorP3ui(type, color, function_pointer);
/*     */   }
/*     */   static native void nglColorP3ui(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glColorP4ui(int type, int color) {
/* 596 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 597 */     long function_pointer = caps.glColorP4ui;
/* 598 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 599 */     nglColorP4ui(type, color, function_pointer);
/*     */   }
/*     */   static native void nglColorP4ui(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glColorP3u(int type, IntBuffer color) {
/* 604 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 605 */     long function_pointer = caps.glColorP3uiv;
/* 606 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 607 */     BufferChecks.checkBuffer(color, 3);
/* 608 */     nglColorP3uiv(type, MemoryUtil.getAddress(color), function_pointer);
/*     */   }
/*     */   static native void nglColorP3uiv(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glColorP4u(int type, IntBuffer color) {
/* 613 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 614 */     long function_pointer = caps.glColorP4uiv;
/* 615 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 616 */     BufferChecks.checkBuffer(color, 4);
/* 617 */     nglColorP4uiv(type, MemoryUtil.getAddress(color), function_pointer);
/*     */   }
/*     */   static native void nglColorP4uiv(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glSecondaryColorP3ui(int type, int color) {
/* 622 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 623 */     long function_pointer = caps.glSecondaryColorP3ui;
/* 624 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 625 */     nglSecondaryColorP3ui(type, color, function_pointer);
/*     */   }
/*     */   static native void nglSecondaryColorP3ui(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glSecondaryColorP3u(int type, IntBuffer color) {
/* 630 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 631 */     long function_pointer = caps.glSecondaryColorP3uiv;
/* 632 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 633 */     BufferChecks.checkBuffer(color, 3);
/* 634 */     nglSecondaryColorP3uiv(type, MemoryUtil.getAddress(color), function_pointer);
/*     */   }
/*     */   static native void nglSecondaryColorP3uiv(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribP1ui(int index, int type, boolean normalized, int value) {
/* 639 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 640 */     long function_pointer = caps.glVertexAttribP1ui;
/* 641 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 642 */     nglVertexAttribP1ui(index, type, normalized, value, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribP1ui(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribP2ui(int index, int type, boolean normalized, int value) {
/* 647 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 648 */     long function_pointer = caps.glVertexAttribP2ui;
/* 649 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 650 */     nglVertexAttribP2ui(index, type, normalized, value, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribP2ui(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribP3ui(int index, int type, boolean normalized, int value) {
/* 655 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 656 */     long function_pointer = caps.glVertexAttribP3ui;
/* 657 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 658 */     nglVertexAttribP3ui(index, type, normalized, value, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribP3ui(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribP4ui(int index, int type, boolean normalized, int value) {
/* 663 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 664 */     long function_pointer = caps.glVertexAttribP4ui;
/* 665 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 666 */     nglVertexAttribP4ui(index, type, normalized, value, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribP4ui(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribP1u(int index, int type, boolean normalized, IntBuffer value) {
/* 671 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 672 */     long function_pointer = caps.glVertexAttribP1uiv;
/* 673 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 674 */     BufferChecks.checkBuffer(value, 1);
/* 675 */     nglVertexAttribP1uiv(index, type, normalized, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribP1uiv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribP2u(int index, int type, boolean normalized, IntBuffer value) {
/* 680 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 681 */     long function_pointer = caps.glVertexAttribP2uiv;
/* 682 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 683 */     BufferChecks.checkBuffer(value, 2);
/* 684 */     nglVertexAttribP2uiv(index, type, normalized, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribP2uiv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribP3u(int index, int type, boolean normalized, IntBuffer value) {
/* 689 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 690 */     long function_pointer = caps.glVertexAttribP3uiv;
/* 691 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 692 */     BufferChecks.checkBuffer(value, 3);
/* 693 */     nglVertexAttribP3uiv(index, type, normalized, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribP3uiv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribP4u(int index, int type, boolean normalized, IntBuffer value) {
/* 698 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 699 */     long function_pointer = caps.glVertexAttribP4uiv;
/* 700 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 701 */     BufferChecks.checkBuffer(value, 4);
/* 702 */     nglVertexAttribP4uiv(index, type, normalized, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglVertexAttribP4uiv(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL33
 * JD-Core Version:    0.6.2
 */