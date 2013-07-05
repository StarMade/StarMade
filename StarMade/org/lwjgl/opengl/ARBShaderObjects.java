/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class ARBShaderObjects
/*     */ {
/*     */   public static final int GL_PROGRAM_OBJECT_ARB = 35648;
/*     */   public static final int GL_OBJECT_TYPE_ARB = 35662;
/*     */   public static final int GL_OBJECT_SUBTYPE_ARB = 35663;
/*     */   public static final int GL_OBJECT_DELETE_STATUS_ARB = 35712;
/*     */   public static final int GL_OBJECT_COMPILE_STATUS_ARB = 35713;
/*     */   public static final int GL_OBJECT_LINK_STATUS_ARB = 35714;
/*     */   public static final int GL_OBJECT_VALIDATE_STATUS_ARB = 35715;
/*     */   public static final int GL_OBJECT_INFO_LOG_LENGTH_ARB = 35716;
/*     */   public static final int GL_OBJECT_ATTACHED_OBJECTS_ARB = 35717;
/*     */   public static final int GL_OBJECT_ACTIVE_UNIFORMS_ARB = 35718;
/*     */   public static final int GL_OBJECT_ACTIVE_UNIFORM_MAX_LENGTH_ARB = 35719;
/*     */   public static final int GL_OBJECT_SHADER_SOURCE_LENGTH_ARB = 35720;
/*     */   public static final int GL_SHADER_OBJECT_ARB = 35656;
/*     */   public static final int GL_FLOAT_VEC2_ARB = 35664;
/*     */   public static final int GL_FLOAT_VEC3_ARB = 35665;
/*     */   public static final int GL_FLOAT_VEC4_ARB = 35666;
/*     */   public static final int GL_INT_VEC2_ARB = 35667;
/*     */   public static final int GL_INT_VEC3_ARB = 35668;
/*     */   public static final int GL_INT_VEC4_ARB = 35669;
/*     */   public static final int GL_BOOL_ARB = 35670;
/*     */   public static final int GL_BOOL_VEC2_ARB = 35671;
/*     */   public static final int GL_BOOL_VEC3_ARB = 35672;
/*     */   public static final int GL_BOOL_VEC4_ARB = 35673;
/*     */   public static final int GL_FLOAT_MAT2_ARB = 35674;
/*     */   public static final int GL_FLOAT_MAT3_ARB = 35675;
/*     */   public static final int GL_FLOAT_MAT4_ARB = 35676;
/*     */   public static final int GL_SAMPLER_1D_ARB = 35677;
/*     */   public static final int GL_SAMPLER_2D_ARB = 35678;
/*     */   public static final int GL_SAMPLER_3D_ARB = 35679;
/*     */   public static final int GL_SAMPLER_CUBE_ARB = 35680;
/*     */   public static final int GL_SAMPLER_1D_SHADOW_ARB = 35681;
/*     */   public static final int GL_SAMPLER_2D_SHADOW_ARB = 35682;
/*     */   public static final int GL_SAMPLER_2D_RECT_ARB = 35683;
/*     */   public static final int GL_SAMPLER_2D_RECT_SHADOW_ARB = 35684;
/*     */ 
/*     */   public static void glDeleteObjectARB(int obj)
/*     */   {
/*  63 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  64 */     long function_pointer = caps.glDeleteObjectARB;
/*  65 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  66 */     nglDeleteObjectARB(obj, function_pointer);
/*     */   }
/*     */   static native void nglDeleteObjectARB(int paramInt, long paramLong);
/*     */ 
/*     */   public static int glGetHandleARB(int pname) {
/*  71 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  72 */     long function_pointer = caps.glGetHandleARB;
/*  73 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  74 */     int __result = nglGetHandleARB(pname, function_pointer);
/*  75 */     return __result;
/*     */   }
/*     */   static native int nglGetHandleARB(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glDetachObjectARB(int containerObj, int attachedObj) {
/*  80 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  81 */     long function_pointer = caps.glDetachObjectARB;
/*  82 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  83 */     nglDetachObjectARB(containerObj, attachedObj, function_pointer);
/*     */   }
/*     */   static native void nglDetachObjectARB(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static int glCreateShaderObjectARB(int shaderType) {
/*  88 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  89 */     long function_pointer = caps.glCreateShaderObjectARB;
/*  90 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  91 */     int __result = nglCreateShaderObjectARB(shaderType, function_pointer);
/*  92 */     return __result;
/*     */   }
/*     */ 
/*     */   static native int nglCreateShaderObjectARB(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glShaderSourceARB(int shader, ByteBuffer string)
/*     */   {
/* 102 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 103 */     long function_pointer = caps.glShaderSourceARB;
/* 104 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 105 */     BufferChecks.checkDirect(string);
/* 106 */     nglShaderSourceARB(shader, 1, MemoryUtil.getAddress(string), string.remaining(), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglShaderSourceARB(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2);
/*     */ 
/*     */   public static void glShaderSourceARB(int shader, CharSequence string) {
/* 112 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 113 */     long function_pointer = caps.glShaderSourceARB;
/* 114 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 115 */     nglShaderSourceARB(shader, 1, APIUtil.getBuffer(caps, string), string.length(), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glShaderSourceARB(int shader, CharSequence[] strings)
/*     */   {
/* 120 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 121 */     long function_pointer = caps.glShaderSourceARB;
/* 122 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 123 */     BufferChecks.checkArray(strings);
/* 124 */     nglShaderSourceARB3(shader, strings.length, APIUtil.getBuffer(caps, strings), APIUtil.getLengths(caps, strings), function_pointer);
/*     */   }
/*     */   static native void nglShaderSourceARB3(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glCompileShaderARB(int shaderObj) {
/* 129 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 130 */     long function_pointer = caps.glCompileShaderARB;
/* 131 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 132 */     nglCompileShaderARB(shaderObj, function_pointer);
/*     */   }
/*     */   static native void nglCompileShaderARB(int paramInt, long paramLong);
/*     */ 
/*     */   public static int glCreateProgramObjectARB() {
/* 137 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 138 */     long function_pointer = caps.glCreateProgramObjectARB;
/* 139 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 140 */     int __result = nglCreateProgramObjectARB(function_pointer);
/* 141 */     return __result;
/*     */   }
/*     */   static native int nglCreateProgramObjectARB(long paramLong);
/*     */ 
/*     */   public static void glAttachObjectARB(int containerObj, int obj) {
/* 146 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 147 */     long function_pointer = caps.glAttachObjectARB;
/* 148 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 149 */     nglAttachObjectARB(containerObj, obj, function_pointer);
/*     */   }
/*     */   static native void nglAttachObjectARB(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glLinkProgramARB(int programObj) {
/* 154 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 155 */     long function_pointer = caps.glLinkProgramARB;
/* 156 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 157 */     nglLinkProgramARB(programObj, function_pointer);
/*     */   }
/*     */   static native void nglLinkProgramARB(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glUseProgramObjectARB(int programObj) {
/* 162 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 163 */     long function_pointer = caps.glUseProgramObjectARB;
/* 164 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 165 */     nglUseProgramObjectARB(programObj, function_pointer);
/*     */   }
/*     */   static native void nglUseProgramObjectARB(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glValidateProgramARB(int programObj) {
/* 170 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 171 */     long function_pointer = caps.glValidateProgramARB;
/* 172 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 173 */     nglValidateProgramARB(programObj, function_pointer);
/*     */   }
/*     */   static native void nglValidateProgramARB(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glUniform1fARB(int location, float v0) {
/* 178 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 179 */     long function_pointer = caps.glUniform1fARB;
/* 180 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 181 */     nglUniform1fARB(location, v0, function_pointer);
/*     */   }
/*     */   static native void nglUniform1fARB(int paramInt, float paramFloat, long paramLong);
/*     */ 
/*     */   public static void glUniform2fARB(int location, float v0, float v1) {
/* 186 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 187 */     long function_pointer = caps.glUniform2fARB;
/* 188 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 189 */     nglUniform2fARB(location, v0, v1, function_pointer);
/*     */   }
/*     */   static native void nglUniform2fARB(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/*     */ 
/*     */   public static void glUniform3fARB(int location, float v0, float v1, float v2) {
/* 194 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 195 */     long function_pointer = caps.glUniform3fARB;
/* 196 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 197 */     nglUniform3fARB(location, v0, v1, v2, function_pointer);
/*     */   }
/*     */   static native void nglUniform3fARB(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*     */ 
/*     */   public static void glUniform4fARB(int location, float v0, float v1, float v2, float v3) {
/* 202 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 203 */     long function_pointer = caps.glUniform4fARB;
/* 204 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 205 */     nglUniform4fARB(location, v0, v1, v2, v3, function_pointer);
/*     */   }
/*     */   static native void nglUniform4fARB(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*     */ 
/*     */   public static void glUniform1iARB(int location, int v0) {
/* 210 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 211 */     long function_pointer = caps.glUniform1iARB;
/* 212 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 213 */     nglUniform1iARB(location, v0, function_pointer);
/*     */   }
/*     */   static native void nglUniform1iARB(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glUniform2iARB(int location, int v0, int v1) {
/* 218 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 219 */     long function_pointer = caps.glUniform2iARB;
/* 220 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 221 */     nglUniform2iARB(location, v0, v1, function_pointer);
/*     */   }
/*     */   static native void nglUniform2iARB(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glUniform3iARB(int location, int v0, int v1, int v2) {
/* 226 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 227 */     long function_pointer = caps.glUniform3iARB;
/* 228 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 229 */     nglUniform3iARB(location, v0, v1, v2, function_pointer);
/*     */   }
/*     */   static native void nglUniform3iARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glUniform4iARB(int location, int v0, int v1, int v2, int v3) {
/* 234 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 235 */     long function_pointer = caps.glUniform4iARB;
/* 236 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 237 */     nglUniform4iARB(location, v0, v1, v2, v3, function_pointer);
/*     */   }
/*     */   static native void nglUniform4iARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*     */ 
/*     */   public static void glUniform1ARB(int location, FloatBuffer values) {
/* 242 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 243 */     long function_pointer = caps.glUniform1fvARB;
/* 244 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 245 */     BufferChecks.checkDirect(values);
/* 246 */     nglUniform1fvARB(location, values.remaining(), MemoryUtil.getAddress(values), function_pointer);
/*     */   }
/*     */   static native void nglUniform1fvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform2ARB(int location, FloatBuffer values) {
/* 251 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 252 */     long function_pointer = caps.glUniform2fvARB;
/* 253 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 254 */     BufferChecks.checkDirect(values);
/* 255 */     nglUniform2fvARB(location, values.remaining() >> 1, MemoryUtil.getAddress(values), function_pointer);
/*     */   }
/*     */   static native void nglUniform2fvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform3ARB(int location, FloatBuffer values) {
/* 260 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 261 */     long function_pointer = caps.glUniform3fvARB;
/* 262 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 263 */     BufferChecks.checkDirect(values);
/* 264 */     nglUniform3fvARB(location, values.remaining() / 3, MemoryUtil.getAddress(values), function_pointer);
/*     */   }
/*     */   static native void nglUniform3fvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform4ARB(int location, FloatBuffer values) {
/* 269 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 270 */     long function_pointer = caps.glUniform4fvARB;
/* 271 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 272 */     BufferChecks.checkDirect(values);
/* 273 */     nglUniform4fvARB(location, values.remaining() >> 2, MemoryUtil.getAddress(values), function_pointer);
/*     */   }
/*     */   static native void nglUniform4fvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform1ARB(int location, IntBuffer values) {
/* 278 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 279 */     long function_pointer = caps.glUniform1ivARB;
/* 280 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 281 */     BufferChecks.checkDirect(values);
/* 282 */     nglUniform1ivARB(location, values.remaining(), MemoryUtil.getAddress(values), function_pointer);
/*     */   }
/*     */   static native void nglUniform1ivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform2ARB(int location, IntBuffer values) {
/* 287 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 288 */     long function_pointer = caps.glUniform2ivARB;
/* 289 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 290 */     BufferChecks.checkDirect(values);
/* 291 */     nglUniform2ivARB(location, values.remaining() >> 1, MemoryUtil.getAddress(values), function_pointer);
/*     */   }
/*     */   static native void nglUniform2ivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform3ARB(int location, IntBuffer values) {
/* 296 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 297 */     long function_pointer = caps.glUniform3ivARB;
/* 298 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 299 */     BufferChecks.checkDirect(values);
/* 300 */     nglUniform3ivARB(location, values.remaining() / 3, MemoryUtil.getAddress(values), function_pointer);
/*     */   }
/*     */   static native void nglUniform3ivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniform4ARB(int location, IntBuffer values) {
/* 305 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 306 */     long function_pointer = caps.glUniform4ivARB;
/* 307 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 308 */     BufferChecks.checkDirect(values);
/* 309 */     nglUniform4ivARB(location, values.remaining() >> 2, MemoryUtil.getAddress(values), function_pointer);
/*     */   }
/*     */   static native void nglUniform4ivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniformMatrix2ARB(int location, boolean transpose, FloatBuffer matrices) {
/* 314 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 315 */     long function_pointer = caps.glUniformMatrix2fvARB;
/* 316 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 317 */     BufferChecks.checkDirect(matrices);
/* 318 */     nglUniformMatrix2fvARB(location, matrices.remaining() >> 2, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/*     */   }
/*     */   static native void nglUniformMatrix2fvARB(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniformMatrix3ARB(int location, boolean transpose, FloatBuffer matrices) {
/* 323 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 324 */     long function_pointer = caps.glUniformMatrix3fvARB;
/* 325 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 326 */     BufferChecks.checkDirect(matrices);
/* 327 */     nglUniformMatrix3fvARB(location, matrices.remaining() / 9, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/*     */   }
/*     */   static native void nglUniformMatrix3fvARB(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glUniformMatrix4ARB(int location, boolean transpose, FloatBuffer matrices) {
/* 332 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 333 */     long function_pointer = caps.glUniformMatrix4fvARB;
/* 334 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 335 */     BufferChecks.checkDirect(matrices);
/* 336 */     nglUniformMatrix4fvARB(location, matrices.remaining() >> 4, transpose, MemoryUtil.getAddress(matrices), function_pointer);
/*     */   }
/*     */   static native void nglUniformMatrix4fvARB(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetObjectParameterARB(int obj, int pname, FloatBuffer params) {
/* 341 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 342 */     long function_pointer = caps.glGetObjectParameterfvARB;
/* 343 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 344 */     BufferChecks.checkDirect(params);
/* 345 */     nglGetObjectParameterfvARB(obj, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetObjectParameterfvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static float glGetObjectParameterfARB(int obj, int pname) {
/* 351 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 352 */     long function_pointer = caps.glGetObjectParameterfvARB;
/* 353 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 354 */     FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 355 */     nglGetObjectParameterfvARB(obj, pname, MemoryUtil.getAddress(params), function_pointer);
/* 356 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetObjectParameterARB(int obj, int pname, IntBuffer params) {
/* 360 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 361 */     long function_pointer = caps.glGetObjectParameterivARB;
/* 362 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 363 */     BufferChecks.checkDirect(params);
/* 364 */     nglGetObjectParameterivARB(obj, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetObjectParameterivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetObjectParameteriARB(int obj, int pname) {
/* 370 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 371 */     long function_pointer = caps.glGetObjectParameterivARB;
/* 372 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 373 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 374 */     nglGetObjectParameterivARB(obj, pname, MemoryUtil.getAddress(params), function_pointer);
/* 375 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetInfoLogARB(int obj, IntBuffer length, ByteBuffer infoLog) {
/* 379 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 380 */     long function_pointer = caps.glGetInfoLogARB;
/* 381 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 382 */     if (length != null)
/* 383 */       BufferChecks.checkBuffer(length, 1);
/* 384 */     BufferChecks.checkDirect(infoLog);
/* 385 */     nglGetInfoLogARB(obj, infoLog.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(infoLog), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetInfoLogARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static String glGetInfoLogARB(int obj, int maxLength) {
/* 391 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 392 */     long function_pointer = caps.glGetInfoLogARB;
/* 393 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 394 */     IntBuffer infoLog_length = APIUtil.getLengths(caps);
/* 395 */     ByteBuffer infoLog = APIUtil.getBufferByte(caps, maxLength);
/* 396 */     nglGetInfoLogARB(obj, maxLength, MemoryUtil.getAddress0(infoLog_length), MemoryUtil.getAddress(infoLog), function_pointer);
/* 397 */     infoLog.limit(infoLog_length.get(0));
/* 398 */     return APIUtil.getString(caps, infoLog);
/*     */   }
/*     */ 
/*     */   public static void glGetAttachedObjectsARB(int containerObj, IntBuffer count, IntBuffer obj) {
/* 402 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 403 */     long function_pointer = caps.glGetAttachedObjectsARB;
/* 404 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 405 */     if (count != null)
/* 406 */       BufferChecks.checkBuffer(count, 1);
/* 407 */     BufferChecks.checkDirect(obj);
/* 408 */     nglGetAttachedObjectsARB(containerObj, obj.remaining(), MemoryUtil.getAddressSafe(count), MemoryUtil.getAddress(obj), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetAttachedObjectsARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static int glGetUniformLocationARB(int programObj, ByteBuffer name)
/*     */   {
/* 419 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 420 */     long function_pointer = caps.glGetUniformLocationARB;
/* 421 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 422 */     BufferChecks.checkDirect(name);
/* 423 */     BufferChecks.checkNullTerminated(name);
/* 424 */     int __result = nglGetUniformLocationARB(programObj, MemoryUtil.getAddress(name), function_pointer);
/* 425 */     return __result;
/*     */   }
/*     */ 
/*     */   static native int nglGetUniformLocationARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetUniformLocationARB(int programObj, CharSequence name) {
/* 431 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 432 */     long function_pointer = caps.glGetUniformLocationARB;
/* 433 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 434 */     int __result = nglGetUniformLocationARB(programObj, APIUtil.getBufferNT(caps, name), function_pointer);
/* 435 */     return __result;
/*     */   }
/*     */ 
/*     */   public static void glGetActiveUniformARB(int programObj, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
/* 439 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 440 */     long function_pointer = caps.glGetActiveUniformARB;
/* 441 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 442 */     if (length != null)
/* 443 */       BufferChecks.checkBuffer(length, 1);
/* 444 */     BufferChecks.checkBuffer(size, 1);
/* 445 */     BufferChecks.checkBuffer(type, 1);
/* 446 */     BufferChecks.checkDirect(name);
/* 447 */     nglGetActiveUniformARB(programObj, index, name.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(size), MemoryUtil.getAddress(type), MemoryUtil.getAddress(name), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetActiveUniformARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
/*     */ 
/*     */   public static String glGetActiveUniformARB(int programObj, int index, int maxLength, IntBuffer sizeType)
/*     */   {
/* 457 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 458 */     long function_pointer = caps.glGetActiveUniformARB;
/* 459 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 460 */     BufferChecks.checkBuffer(sizeType, 2);
/* 461 */     IntBuffer name_length = APIUtil.getLengths(caps);
/* 462 */     ByteBuffer name = APIUtil.getBufferByte(caps, maxLength);
/* 463 */     nglGetActiveUniformARB(programObj, index, maxLength, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress(sizeType), MemoryUtil.getAddress(sizeType, sizeType.position() + 1), MemoryUtil.getAddress(name), function_pointer);
/* 464 */     name.limit(name_length.get(0));
/* 465 */     return APIUtil.getString(caps, name);
/*     */   }
/*     */ 
/*     */   public static String glGetActiveUniformARB(int programObj, int index, int maxLength)
/*     */   {
/* 474 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 475 */     long function_pointer = caps.glGetActiveUniformARB;
/* 476 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 477 */     IntBuffer name_length = APIUtil.getLengths(caps);
/* 478 */     ByteBuffer name = APIUtil.getBufferByte(caps, maxLength);
/* 479 */     nglGetActiveUniformARB(programObj, index, maxLength, MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress0(APIUtil.getBufferInt(caps)), MemoryUtil.getAddress(APIUtil.getBufferInt(caps), 1), MemoryUtil.getAddress(name), function_pointer);
/* 480 */     name.limit(name_length.get(0));
/* 481 */     return APIUtil.getString(caps, name);
/*     */   }
/*     */ 
/*     */   public static int glGetActiveUniformSizeARB(int programObj, int index)
/*     */   {
/* 490 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 491 */     long function_pointer = caps.glGetActiveUniformARB;
/* 492 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 493 */     IntBuffer size = APIUtil.getBufferInt(caps);
/* 494 */     nglGetActiveUniformARB(programObj, index, 0, 0L, MemoryUtil.getAddress(size), MemoryUtil.getAddress(size, 1), APIUtil.getBufferByte0(caps), function_pointer);
/* 495 */     return size.get(0);
/*     */   }
/*     */ 
/*     */   public static int glGetActiveUniformTypeARB(int programObj, int index)
/*     */   {
/* 504 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 505 */     long function_pointer = caps.glGetActiveUniformARB;
/* 506 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 507 */     IntBuffer type = APIUtil.getBufferInt(caps);
/* 508 */     nglGetActiveUniformARB(programObj, index, 0, 0L, MemoryUtil.getAddress(type, 1), MemoryUtil.getAddress(type), APIUtil.getBufferByte0(caps), function_pointer);
/* 509 */     return type.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetUniformARB(int programObj, int location, FloatBuffer params) {
/* 513 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 514 */     long function_pointer = caps.glGetUniformfvARB;
/* 515 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 516 */     BufferChecks.checkDirect(params);
/* 517 */     nglGetUniformfvARB(programObj, location, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetUniformfvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetUniformARB(int programObj, int location, IntBuffer params) {
/* 522 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 523 */     long function_pointer = caps.glGetUniformivARB;
/* 524 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 525 */     BufferChecks.checkDirect(params);
/* 526 */     nglGetUniformivARB(programObj, location, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglGetUniformivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetShaderSourceARB(int obj, IntBuffer length, ByteBuffer source) {
/* 531 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 532 */     long function_pointer = caps.glGetShaderSourceARB;
/* 533 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 534 */     if (length != null)
/* 535 */       BufferChecks.checkBuffer(length, 1);
/* 536 */     BufferChecks.checkDirect(source);
/* 537 */     nglGetShaderSourceARB(obj, source.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddress(source), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetShaderSourceARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static String glGetShaderSourceARB(int obj, int maxLength) {
/* 543 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 544 */     long function_pointer = caps.glGetShaderSourceARB;
/* 545 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 546 */     IntBuffer source_length = APIUtil.getLengths(caps);
/* 547 */     ByteBuffer source = APIUtil.getBufferByte(caps, maxLength);
/* 548 */     nglGetShaderSourceARB(obj, maxLength, MemoryUtil.getAddress0(source_length), MemoryUtil.getAddress(source), function_pointer);
/* 549 */     source.limit(source_length.get(0));
/* 550 */     return APIUtil.getString(caps, source);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBShaderObjects
 * JD-Core Version:    0.6.2
 */