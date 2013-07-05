/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.LongBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class NVGpuShader5
/*     */ {
/*     */   public static final int GL_INT64_NV = 5134;
/*     */   public static final int GL_UNSIGNED_INT64_NV = 5135;
/*     */   public static final int GL_INT8_NV = 36832;
/*     */   public static final int GL_INT8_VEC2_NV = 36833;
/*     */   public static final int GL_INT8_VEC3_NV = 36834;
/*     */   public static final int GL_INT8_VEC4_NV = 36835;
/*     */   public static final int GL_INT16_NV = 36836;
/*     */   public static final int GL_INT16_VEC2_NV = 36837;
/*     */   public static final int GL_INT16_VEC3_NV = 36838;
/*     */   public static final int GL_INT16_VEC4_NV = 36839;
/*     */   public static final int GL_INT64_VEC2_NV = 36841;
/*     */   public static final int GL_INT64_VEC3_NV = 36842;
/*     */   public static final int GL_INT64_VEC4_NV = 36843;
/*     */   public static final int GL_UNSIGNED_INT8_NV = 36844;
/*     */   public static final int GL_UNSIGNED_INT8_VEC2_NV = 36845;
/*     */   public static final int GL_UNSIGNED_INT8_VEC3_NV = 36846;
/*     */   public static final int GL_UNSIGNED_INT8_VEC4_NV = 36847;
/*     */   public static final int GL_UNSIGNED_INT16_NV = 36848;
/*     */   public static final int GL_UNSIGNED_INT16_VEC2_NV = 36849;
/*     */   public static final int GL_UNSIGNED_INT16_VEC3_NV = 36850;
/*     */   public static final int GL_UNSIGNED_INT16_VEC4_NV = 36851;
/*     */   public static final int GL_UNSIGNED_INT64_VEC2_NV = 36853;
/*     */   public static final int GL_UNSIGNED_INT64_VEC3_NV = 36854;
/*     */   public static final int GL_UNSIGNED_INT64_VEC4_NV = 36855;
/*     */   public static final int GL_FLOAT16_NV = 36856;
/*     */   public static final int GL_FLOAT16_VEC2_NV = 36857;
/*     */   public static final int GL_FLOAT16_VEC3_NV = 36858;
/*     */   public static final int GL_FLOAT16_VEC4_NV = 36859;
/*     */   public static final int GL_PATCHES = 14;
/*     */ 
/*     */   public static void glUniform1i64NV(int location, long x)
/*     */   {
/*  51 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  52 */     long function_pointer = caps.glUniform1i64NV;
/*  53 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  54 */     nglUniform1i64NV(location, x, function_pointer);
/*     */   }
/*     */   static native void nglUniform1i64NV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform2i64NV(int location, long x, long y) {
/*  59 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  60 */     long function_pointer = caps.glUniform2i64NV;
/*  61 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  62 */     nglUniform2i64NV(location, x, y, function_pointer);
/*     */   }
/*     */   static native void nglUniform2i64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glUniform3i64NV(int location, long x, long y, long z) {
/*  67 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  68 */     long function_pointer = caps.glUniform3i64NV;
/*  69 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  70 */     nglUniform3i64NV(location, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglUniform3i64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static void glUniform4i64NV(int location, long x, long y, long z, long w) {
/*  75 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  76 */     long function_pointer = caps.glUniform4i64NV;
/*  77 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  78 */     nglUniform4i64NV(location, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglUniform4i64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*     */ 
/*     */   public static void glUniform1NV(int location, LongBuffer value) {
/*  83 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  84 */     long function_pointer = caps.glUniform1i64vNV;
/*  85 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  86 */     BufferChecks.checkDirect(value);
/*  87 */     nglUniform1i64vNV(location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniform1i64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform2NV(int location, LongBuffer value) {
/*  92 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  93 */     long function_pointer = caps.glUniform2i64vNV;
/*  94 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  95 */     BufferChecks.checkDirect(value);
/*  96 */     nglUniform2i64vNV(location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniform2i64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform3NV(int location, LongBuffer value) {
/* 101 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 102 */     long function_pointer = caps.glUniform3i64vNV;
/* 103 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 104 */     BufferChecks.checkDirect(value);
/* 105 */     nglUniform3i64vNV(location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniform3i64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform4NV(int location, LongBuffer value) {
/* 110 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 111 */     long function_pointer = caps.glUniform4i64vNV;
/* 112 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 113 */     BufferChecks.checkDirect(value);
/* 114 */     nglUniform4i64vNV(location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniform4i64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform1ui64NV(int location, long x) {
/* 119 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 120 */     long function_pointer = caps.glUniform1ui64NV;
/* 121 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 122 */     nglUniform1ui64NV(location, x, function_pointer);
/*     */   }
/*     */   static native void nglUniform1ui64NV(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform2ui64NV(int location, long x, long y) {
/* 127 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 128 */     long function_pointer = caps.glUniform2ui64NV;
/* 129 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 130 */     nglUniform2ui64NV(location, x, y, function_pointer);
/*     */   }
/*     */   static native void nglUniform2ui64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glUniform3ui64NV(int location, long x, long y, long z) {
/* 135 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 136 */     long function_pointer = caps.glUniform3ui64NV;
/* 137 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 138 */     nglUniform3ui64NV(location, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglUniform3ui64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static void glUniform4ui64NV(int location, long x, long y, long z, long w) {
/* 143 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 144 */     long function_pointer = caps.glUniform4ui64NV;
/* 145 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 146 */     nglUniform4ui64NV(location, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglUniform4ui64NV(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*     */ 
/*     */   public static void glUniform1uNV(int location, LongBuffer value) {
/* 151 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 152 */     long function_pointer = caps.glUniform1ui64vNV;
/* 153 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 154 */     BufferChecks.checkDirect(value);
/* 155 */     nglUniform1ui64vNV(location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniform1ui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform2uNV(int location, LongBuffer value) {
/* 160 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 161 */     long function_pointer = caps.glUniform2ui64vNV;
/* 162 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 163 */     BufferChecks.checkDirect(value);
/* 164 */     nglUniform2ui64vNV(location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniform2ui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform3uNV(int location, LongBuffer value) {
/* 169 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 170 */     long function_pointer = caps.glUniform3ui64vNV;
/* 171 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 172 */     BufferChecks.checkDirect(value);
/* 173 */     nglUniform3ui64vNV(location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniform3ui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform4uNV(int location, LongBuffer value) {
/* 178 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 179 */     long function_pointer = caps.glUniform4ui64vNV;
/* 180 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 181 */     BufferChecks.checkDirect(value);
/* 182 */     nglUniform4ui64vNV(location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglUniform4ui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetUniformNV(int program, int location, LongBuffer params) {
/* 187 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 188 */     long function_pointer = caps.glGetUniformi64vNV;
/* 189 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 190 */     BufferChecks.checkBuffer(params, 1);
/* 191 */     nglGetUniformi64vNV(program, location, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetUniformi64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetUniformuNV(int program, int location, LongBuffer params) {
/* 196 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 197 */     long function_pointer = caps.glGetUniformui64vNV;
/* 198 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 199 */     BufferChecks.checkBuffer(params, 1);
/* 200 */     nglGetUniformui64vNV(program, location, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetUniformui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniform1i64NV(int program, int location, long x) {
/* 205 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 206 */     long function_pointer = caps.glProgramUniform1i64NV;
/* 207 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 208 */     nglProgramUniform1i64NV(program, location, x, function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform1i64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniform2i64NV(int program, int location, long x, long y) {
/* 213 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 214 */     long function_pointer = caps.glProgramUniform2i64NV;
/* 215 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 216 */     nglProgramUniform2i64NV(program, location, x, y, function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform2i64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glProgramUniform3i64NV(int program, int location, long x, long y, long z) {
/* 221 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 222 */     long function_pointer = caps.glProgramUniform3i64NV;
/* 223 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 224 */     nglProgramUniform3i64NV(program, location, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform3i64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static void glProgramUniform4i64NV(int program, int location, long x, long y, long z, long w) {
/* 229 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 230 */     long function_pointer = caps.glProgramUniform4i64NV;
/* 231 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 232 */     nglProgramUniform4i64NV(program, location, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform4i64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*     */ 
/*     */   public static void glProgramUniform1NV(int program, int location, LongBuffer value) {
/* 237 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 238 */     long function_pointer = caps.glProgramUniform1i64vNV;
/* 239 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 240 */     BufferChecks.checkDirect(value);
/* 241 */     nglProgramUniform1i64vNV(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform1i64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniform2NV(int program, int location, LongBuffer value) {
/* 246 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 247 */     long function_pointer = caps.glProgramUniform2i64vNV;
/* 248 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 249 */     BufferChecks.checkDirect(value);
/* 250 */     nglProgramUniform2i64vNV(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform2i64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniform3NV(int program, int location, LongBuffer value) {
/* 255 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 256 */     long function_pointer = caps.glProgramUniform3i64vNV;
/* 257 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 258 */     BufferChecks.checkDirect(value);
/* 259 */     nglProgramUniform3i64vNV(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform3i64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniform4NV(int program, int location, LongBuffer value) {
/* 264 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 265 */     long function_pointer = caps.glProgramUniform4i64vNV;
/* 266 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 267 */     BufferChecks.checkDirect(value);
/* 268 */     nglProgramUniform4i64vNV(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform4i64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniform1ui64NV(int program, int location, long x) {
/* 273 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 274 */     long function_pointer = caps.glProgramUniform1ui64NV;
/* 275 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 276 */     nglProgramUniform1ui64NV(program, location, x, function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform1ui64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniform2ui64NV(int program, int location, long x, long y) {
/* 281 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 282 */     long function_pointer = caps.glProgramUniform2ui64NV;
/* 283 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 284 */     nglProgramUniform2ui64NV(program, location, x, y, function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform2ui64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glProgramUniform3ui64NV(int program, int location, long x, long y, long z) {
/* 289 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 290 */     long function_pointer = caps.glProgramUniform3ui64NV;
/* 291 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 292 */     nglProgramUniform3ui64NV(program, location, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform3ui64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static void glProgramUniform4ui64NV(int program, int location, long x, long y, long z, long w) {
/* 297 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 298 */     long function_pointer = caps.glProgramUniform4ui64NV;
/* 299 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 300 */     nglProgramUniform4ui64NV(program, location, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform4ui64NV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*     */ 
/*     */   public static void glProgramUniform1uNV(int program, int location, LongBuffer value) {
/* 305 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 306 */     long function_pointer = caps.glProgramUniform1ui64vNV;
/* 307 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 308 */     BufferChecks.checkDirect(value);
/* 309 */     nglProgramUniform1ui64vNV(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform1ui64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniform2uNV(int program, int location, LongBuffer value) {
/* 314 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 315 */     long function_pointer = caps.glProgramUniform2ui64vNV;
/* 316 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 317 */     BufferChecks.checkDirect(value);
/* 318 */     nglProgramUniform2ui64vNV(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform2ui64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniform3uNV(int program, int location, LongBuffer value) {
/* 323 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 324 */     long function_pointer = caps.glProgramUniform3ui64vNV;
/* 325 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 326 */     BufferChecks.checkDirect(value);
/* 327 */     nglProgramUniform3ui64vNV(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */   static native void nglProgramUniform3ui64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glProgramUniform4uNV(int program, int location, LongBuffer value) {
/* 332 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 333 */     long function_pointer = caps.glProgramUniform4ui64vNV;
/* 334 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 335 */     BufferChecks.checkDirect(value);
/* 336 */     nglProgramUniform4ui64vNV(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglProgramUniform4ui64vNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVGpuShader5
 * JD-Core Version:    0.6.2
 */