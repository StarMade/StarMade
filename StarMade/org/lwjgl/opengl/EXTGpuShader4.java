/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class EXTGpuShader4
/*     */ {
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_INTEGER_EXT = 35069;
/*     */   public static final int GL_SAMPLER_1D_ARRAY_EXT = 36288;
/*     */   public static final int GL_SAMPLER_2D_ARRAY_EXT = 36289;
/*     */   public static final int GL_SAMPLER_BUFFER_EXT = 36290;
/*     */   public static final int GL_SAMPLER_1D_ARRAY_SHADOW_EXT = 36291;
/*     */   public static final int GL_SAMPLER_2D_ARRAY_SHADOW_EXT = 36292;
/*     */   public static final int GL_SAMPLER_CUBE_SHADOW_EXT = 36293;
/*     */   public static final int GL_UNSIGNED_INT_VEC2_EXT = 36294;
/*     */   public static final int GL_UNSIGNED_INT_VEC3_EXT = 36295;
/*     */   public static final int GL_UNSIGNED_INT_VEC4_EXT = 36296;
/*     */   public static final int GL_INT_SAMPLER_1D_EXT = 36297;
/*     */   public static final int GL_INT_SAMPLER_2D_EXT = 36298;
/*     */   public static final int GL_INT_SAMPLER_3D_EXT = 36299;
/*     */   public static final int GL_INT_SAMPLER_CUBE_EXT = 36300;
/*     */   public static final int GL_INT_SAMPLER_2D_RECT_EXT = 36301;
/*     */   public static final int GL_INT_SAMPLER_1D_ARRAY_EXT = 36302;
/*     */   public static final int GL_INT_SAMPLER_2D_ARRAY_EXT = 36303;
/*     */   public static final int GL_INT_SAMPLER_BUFFER_EXT = 36304;
/*     */   public static final int GL_UNSIGNED_INT_SAMPLER_1D_EXT = 36305;
/*     */   public static final int GL_UNSIGNED_INT_SAMPLER_2D_EXT = 36306;
/*     */   public static final int GL_UNSIGNED_INT_SAMPLER_3D_EXT = 36307;
/*     */   public static final int GL_UNSIGNED_INT_SAMPLER_CUBE_EXT = 36308;
/*     */   public static final int GL_UNSIGNED_INT_SAMPLER_2D_RECT_EXT = 36309;
/*     */   public static final int GL_UNSIGNED_INT_SAMPLER_1D_ARRAY_EXT = 36310;
/*     */   public static final int GL_UNSIGNED_INT_SAMPLER_2D_ARRAY_EXT = 36311;
/*     */   public static final int GL_UNSIGNED_INT_SAMPLER_BUFFER_EXT = 36312;
/*     */   public static final int GL_MIN_PROGRAM_TEXEL_OFFSET_EXT = 35076;
/*     */   public static final int GL_MAX_PROGRAM_TEXEL_OFFSET_EXT = 35077;
/*     */ 
/*     */   public static void glVertexAttribI1iEXT(int index, int x)
/*     */   {
/*  56 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  57 */     long function_pointer = caps.glVertexAttribI1iEXT;
/*  58 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  59 */     nglVertexAttribI1iEXT(index, x, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI1iEXT(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribI2iEXT(int index, int x, int y) {
/*  64 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  65 */     long function_pointer = caps.glVertexAttribI2iEXT;
/*  66 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  67 */     nglVertexAttribI2iEXT(index, x, y, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI2iEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribI3iEXT(int index, int x, int y, int z) {
/*  72 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  73 */     long function_pointer = caps.glVertexAttribI3iEXT;
/*  74 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  75 */     nglVertexAttribI3iEXT(index, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI3iEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribI4iEXT(int index, int x, int y, int z, int w) {
/*  80 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  81 */     long function_pointer = caps.glVertexAttribI4iEXT;
/*  82 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  83 */     nglVertexAttribI4iEXT(index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI4iEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribI1uiEXT(int index, int x) {
/*  88 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  89 */     long function_pointer = caps.glVertexAttribI1uiEXT;
/*  90 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  91 */     nglVertexAttribI1uiEXT(index, x, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI1uiEXT(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribI2uiEXT(int index, int x, int y) {
/*  96 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  97 */     long function_pointer = caps.glVertexAttribI2uiEXT;
/*  98 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  99 */     nglVertexAttribI2uiEXT(index, x, y, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI2uiEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribI3uiEXT(int index, int x, int y, int z) {
/* 104 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 105 */     long function_pointer = caps.glVertexAttribI3uiEXT;
/* 106 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 107 */     nglVertexAttribI3uiEXT(index, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI3uiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribI4uiEXT(int index, int x, int y, int z, int w) {
/* 112 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 113 */     long function_pointer = caps.glVertexAttribI4uiEXT;
/* 114 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 115 */     nglVertexAttribI4uiEXT(index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI4uiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribI1EXT(int index, IntBuffer v) {
/* 120 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 121 */     long function_pointer = caps.glVertexAttribI1ivEXT;
/* 122 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 123 */     BufferChecks.checkBuffer(v, 1);
/* 124 */     nglVertexAttribI1ivEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI1ivEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribI2EXT(int index, IntBuffer v) {
/* 129 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 130 */     long function_pointer = caps.glVertexAttribI2ivEXT;
/* 131 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 132 */     BufferChecks.checkBuffer(v, 2);
/* 133 */     nglVertexAttribI2ivEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI2ivEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribI3EXT(int index, IntBuffer v) {
/* 138 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 139 */     long function_pointer = caps.glVertexAttribI3ivEXT;
/* 140 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 141 */     BufferChecks.checkBuffer(v, 3);
/* 142 */     nglVertexAttribI3ivEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI3ivEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribI4EXT(int index, IntBuffer v) {
/* 147 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 148 */     long function_pointer = caps.glVertexAttribI4ivEXT;
/* 149 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 150 */     BufferChecks.checkBuffer(v, 4);
/* 151 */     nglVertexAttribI4ivEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI4ivEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribI1uEXT(int index, IntBuffer v) {
/* 156 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 157 */     long function_pointer = caps.glVertexAttribI1uivEXT;
/* 158 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 159 */     BufferChecks.checkBuffer(v, 1);
/* 160 */     nglVertexAttribI1uivEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI1uivEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribI2uEXT(int index, IntBuffer v) {
/* 165 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 166 */     long function_pointer = caps.glVertexAttribI2uivEXT;
/* 167 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 168 */     BufferChecks.checkBuffer(v, 2);
/* 169 */     nglVertexAttribI2uivEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI2uivEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribI3uEXT(int index, IntBuffer v) {
/* 174 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 175 */     long function_pointer = caps.glVertexAttribI3uivEXT;
/* 176 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 177 */     BufferChecks.checkBuffer(v, 3);
/* 178 */     nglVertexAttribI3uivEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI3uivEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribI4uEXT(int index, IntBuffer v) {
/* 183 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 184 */     long function_pointer = caps.glVertexAttribI4uivEXT;
/* 185 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 186 */     BufferChecks.checkBuffer(v, 4);
/* 187 */     nglVertexAttribI4uivEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI4uivEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribI4EXT(int index, ByteBuffer v) {
/* 192 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 193 */     long function_pointer = caps.glVertexAttribI4bvEXT;
/* 194 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 195 */     BufferChecks.checkBuffer(v, 4);
/* 196 */     nglVertexAttribI4bvEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI4bvEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribI4EXT(int index, ShortBuffer v) {
/* 201 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 202 */     long function_pointer = caps.glVertexAttribI4svEXT;
/* 203 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 204 */     BufferChecks.checkBuffer(v, 4);
/* 205 */     nglVertexAttribI4svEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI4svEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribI4uEXT(int index, ByteBuffer v) {
/* 210 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 211 */     long function_pointer = caps.glVertexAttribI4ubvEXT;
/* 212 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 213 */     BufferChecks.checkBuffer(v, 4);
/* 214 */     nglVertexAttribI4ubvEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI4ubvEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribI4uEXT(int index, ShortBuffer v) {
/* 219 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 220 */     long function_pointer = caps.glVertexAttribI4usvEXT;
/* 221 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 222 */     BufferChecks.checkBuffer(v, 4);
/* 223 */     nglVertexAttribI4usvEXT(index, MemoryUtil.getAddress(v), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribI4usvEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribIPointerEXT(int index, int size, int type, int stride, ByteBuffer buffer) {
/* 228 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 229 */     long function_pointer = caps.glVertexAttribIPointerEXT;
/* 230 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 231 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 232 */     BufferChecks.checkDirect(buffer);
/* 233 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 234 */     nglVertexAttribIPointerEXT(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*     */   }
/*     */   public static void glVertexAttribIPointerEXT(int index, int size, int type, int stride, IntBuffer buffer) {
/* 237 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 238 */     long function_pointer = caps.glVertexAttribIPointerEXT;
/* 239 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 240 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 241 */     BufferChecks.checkDirect(buffer);
/* 242 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 243 */     nglVertexAttribIPointerEXT(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*     */   }
/*     */   public static void glVertexAttribIPointerEXT(int index, int size, int type, int stride, ShortBuffer buffer) {
/* 246 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 247 */     long function_pointer = caps.glVertexAttribIPointerEXT;
/* 248 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 249 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 250 */     BufferChecks.checkDirect(buffer);
/* 251 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).glVertexAttribPointer_buffer[index] = buffer;
/* 252 */     nglVertexAttribIPointerEXT(index, size, type, stride, MemoryUtil.getAddress(buffer), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribIPointerEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*     */ 
/* 256 */   public static void glVertexAttribIPointerEXT(int index, int size, int type, int stride, long buffer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 257 */     long function_pointer = caps.glVertexAttribIPointerEXT;
/* 258 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 259 */     GLChecks.ensureArrayVBOenabled(caps);
/* 260 */     nglVertexAttribIPointerEXTBO(index, size, type, stride, buffer_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglVertexAttribIPointerEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetVertexAttribIEXT(int index, int pname, IntBuffer params) {
/* 265 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 266 */     long function_pointer = caps.glGetVertexAttribIivEXT;
/* 267 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 268 */     BufferChecks.checkBuffer(params, 4);
/* 269 */     nglGetVertexAttribIivEXT(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetVertexAttribIivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetVertexAttribIuEXT(int index, int pname, IntBuffer params) {
/* 274 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 275 */     long function_pointer = caps.glGetVertexAttribIuivEXT;
/* 276 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 277 */     BufferChecks.checkBuffer(params, 4);
/* 278 */     nglGetVertexAttribIuivEXT(index, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetVertexAttribIuivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform1uiEXT(int location, int v0) {
/* 283 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 284 */     long function_pointer = caps.glUniform1uiEXT;
/* 285 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 286 */     nglUniform1uiEXT(location, v0, function_pointer);
/*     */   }
/*     */   static native void nglUniform1uiEXT(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glUniform2uiEXT(int location, int v0, int v1) {
/* 291 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 292 */     long function_pointer = caps.glUniform2uiEXT;
/* 293 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 294 */     nglUniform2uiEXT(location, v0, v1, function_pointer);
/*     */   }
/*     */   static native void nglUniform2uiEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glUniform3uiEXT(int location, int v0, int v1, int v2) {
/* 299 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 300 */     long function_pointer = caps.glUniform3uiEXT;
/* 301 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 302 */     nglUniform3uiEXT(location, v0, v1, v2, function_pointer);
/*     */   }
/*     */   static native void nglUniform3uiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glUniform4uiEXT(int location, int v0, int v1, int v2, int v3) {
/* 307 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 308 */     long function_pointer = caps.glUniform4uiEXT;
/* 309 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 310 */     nglUniform4uiEXT(location, v0, v1, v2, v3, function_pointer);
/*     */   }
/*     */   static native void nglUniform4uiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*     */ 
/*     */   public static void glUniform1uEXT(int location, IntBuffer value) {
/* 315 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 316 */     long function_pointer = caps.glUniform1uivEXT;
/* 317 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 318 */     BufferChecks.checkDirect(value);
/* 319 */     nglUniform1uivEXT(location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniform1uivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform2uEXT(int location, IntBuffer value) {
/* 324 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 325 */     long function_pointer = caps.glUniform2uivEXT;
/* 326 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 327 */     BufferChecks.checkDirect(value);
/* 328 */     nglUniform2uivEXT(location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniform2uivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform3uEXT(int location, IntBuffer value) {
/* 333 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 334 */     long function_pointer = caps.glUniform3uivEXT;
/* 335 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 336 */     BufferChecks.checkDirect(value);
/* 337 */     nglUniform3uivEXT(location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniform3uivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform4uEXT(int location, IntBuffer value) {
/* 342 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 343 */     long function_pointer = caps.glUniform4uivEXT;
/* 344 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 345 */     BufferChecks.checkDirect(value);
/* 346 */     nglUniform4uivEXT(location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniform4uivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetUniformuEXT(int program, int location, IntBuffer params) {
/* 351 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 352 */     long function_pointer = caps.glGetUniformuivEXT;
/* 353 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 354 */     BufferChecks.checkDirect(params);
/* 355 */     nglGetUniformuivEXT(program, location, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetUniformuivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glBindFragDataLocationEXT(int program, int colorNumber, ByteBuffer name) {
/* 360 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 361 */     long function_pointer = caps.glBindFragDataLocationEXT;
/* 362 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 363 */     BufferChecks.checkDirect(name);
/* 364 */     BufferChecks.checkNullTerminated(name);
/* 365 */     nglBindFragDataLocationEXT(program, colorNumber, MemoryUtil.getAddress(name), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglBindFragDataLocationEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glBindFragDataLocationEXT(int program, int colorNumber, CharSequence name) {
/* 371 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 372 */     long function_pointer = caps.glBindFragDataLocationEXT;
/* 373 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 374 */     nglBindFragDataLocationEXT(program, colorNumber, APIUtil.getBufferNT(caps, name), function_pointer);
/*     */   }
/*     */ 
/*     */   public static int glGetFragDataLocationEXT(int program, ByteBuffer name) {
/* 378 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 379 */     long function_pointer = caps.glGetFragDataLocationEXT;
/* 380 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 381 */     BufferChecks.checkDirect(name);
/* 382 */     BufferChecks.checkNullTerminated(name);
/* 383 */     int __result = nglGetFragDataLocationEXT(program, MemoryUtil.getAddress(name), function_pointer);
/* 384 */     return __result;
/*     */   }
/*     */ 
/*     */   static native int nglGetFragDataLocationEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetFragDataLocationEXT(int program, CharSequence name) {
/* 390 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 391 */     long function_pointer = caps.glGetFragDataLocationEXT;
/* 392 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 393 */     int __result = nglGetFragDataLocationEXT(program, APIUtil.getBufferNT(caps, name), function_pointer);
/* 394 */     return __result;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTGpuShader4
 * JD-Core Version:    0.6.2
 */