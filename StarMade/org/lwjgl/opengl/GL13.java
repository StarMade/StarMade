/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class GL13
/*     */ {
/*     */   public static final int GL_TEXTURE0 = 33984;
/*     */   public static final int GL_TEXTURE1 = 33985;
/*     */   public static final int GL_TEXTURE2 = 33986;
/*     */   public static final int GL_TEXTURE3 = 33987;
/*     */   public static final int GL_TEXTURE4 = 33988;
/*     */   public static final int GL_TEXTURE5 = 33989;
/*     */   public static final int GL_TEXTURE6 = 33990;
/*     */   public static final int GL_TEXTURE7 = 33991;
/*     */   public static final int GL_TEXTURE8 = 33992;
/*     */   public static final int GL_TEXTURE9 = 33993;
/*     */   public static final int GL_TEXTURE10 = 33994;
/*     */   public static final int GL_TEXTURE11 = 33995;
/*     */   public static final int GL_TEXTURE12 = 33996;
/*     */   public static final int GL_TEXTURE13 = 33997;
/*     */   public static final int GL_TEXTURE14 = 33998;
/*     */   public static final int GL_TEXTURE15 = 33999;
/*     */   public static final int GL_TEXTURE16 = 34000;
/*     */   public static final int GL_TEXTURE17 = 34001;
/*     */   public static final int GL_TEXTURE18 = 34002;
/*     */   public static final int GL_TEXTURE19 = 34003;
/*     */   public static final int GL_TEXTURE20 = 34004;
/*     */   public static final int GL_TEXTURE21 = 34005;
/*     */   public static final int GL_TEXTURE22 = 34006;
/*     */   public static final int GL_TEXTURE23 = 34007;
/*     */   public static final int GL_TEXTURE24 = 34008;
/*     */   public static final int GL_TEXTURE25 = 34009;
/*     */   public static final int GL_TEXTURE26 = 34010;
/*     */   public static final int GL_TEXTURE27 = 34011;
/*     */   public static final int GL_TEXTURE28 = 34012;
/*     */   public static final int GL_TEXTURE29 = 34013;
/*     */   public static final int GL_TEXTURE30 = 34014;
/*     */   public static final int GL_TEXTURE31 = 34015;
/*     */   public static final int GL_ACTIVE_TEXTURE = 34016;
/*     */   public static final int GL_CLIENT_ACTIVE_TEXTURE = 34017;
/*     */   public static final int GL_MAX_TEXTURE_UNITS = 34018;
/*     */   public static final int GL_NORMAL_MAP = 34065;
/*     */   public static final int GL_REFLECTION_MAP = 34066;
/*     */   public static final int GL_TEXTURE_CUBE_MAP = 34067;
/*     */   public static final int GL_TEXTURE_BINDING_CUBE_MAP = 34068;
/*     */   public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_X = 34069;
/*     */   public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_X = 34070;
/*     */   public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Y = 34071;
/*     */   public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Y = 34072;
/*     */   public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Z = 34073;
/*     */   public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Z = 34074;
/*     */   public static final int GL_PROXY_TEXTURE_CUBE_MAP = 34075;
/*     */   public static final int GL_MAX_CUBE_MAP_TEXTURE_SIZE = 34076;
/*     */   public static final int GL_COMPRESSED_ALPHA = 34025;
/*     */   public static final int GL_COMPRESSED_LUMINANCE = 34026;
/*     */   public static final int GL_COMPRESSED_LUMINANCE_ALPHA = 34027;
/*     */   public static final int GL_COMPRESSED_INTENSITY = 34028;
/*     */   public static final int GL_COMPRESSED_RGB = 34029;
/*     */   public static final int GL_COMPRESSED_RGBA = 34030;
/*     */   public static final int GL_TEXTURE_COMPRESSION_HINT = 34031;
/*     */   public static final int GL_TEXTURE_COMPRESSED_IMAGE_SIZE = 34464;
/*     */   public static final int GL_TEXTURE_COMPRESSED = 34465;
/*     */   public static final int GL_NUM_COMPRESSED_TEXTURE_FORMATS = 34466;
/*     */   public static final int GL_COMPRESSED_TEXTURE_FORMATS = 34467;
/*     */   public static final int GL_MULTISAMPLE = 32925;
/*     */   public static final int GL_SAMPLE_ALPHA_TO_COVERAGE = 32926;
/*     */   public static final int GL_SAMPLE_ALPHA_TO_ONE = 32927;
/*     */   public static final int GL_SAMPLE_COVERAGE = 32928;
/*     */   public static final int GL_SAMPLE_BUFFERS = 32936;
/*     */   public static final int GL_SAMPLES = 32937;
/*     */   public static final int GL_SAMPLE_COVERAGE_VALUE = 32938;
/*     */   public static final int GL_SAMPLE_COVERAGE_INVERT = 32939;
/*     */   public static final int GL_MULTISAMPLE_BIT = 536870912;
/*     */   public static final int GL_TRANSPOSE_MODELVIEW_MATRIX = 34019;
/*     */   public static final int GL_TRANSPOSE_PROJECTION_MATRIX = 34020;
/*     */   public static final int GL_TRANSPOSE_TEXTURE_MATRIX = 34021;
/*     */   public static final int GL_TRANSPOSE_COLOR_MATRIX = 34022;
/*     */   public static final int GL_COMBINE = 34160;
/*     */   public static final int GL_COMBINE_RGB = 34161;
/*     */   public static final int GL_COMBINE_ALPHA = 34162;
/*     */   public static final int GL_SOURCE0_RGB = 34176;
/*     */   public static final int GL_SOURCE1_RGB = 34177;
/*     */   public static final int GL_SOURCE2_RGB = 34178;
/*     */   public static final int GL_SOURCE0_ALPHA = 34184;
/*     */   public static final int GL_SOURCE1_ALPHA = 34185;
/*     */   public static final int GL_SOURCE2_ALPHA = 34186;
/*     */   public static final int GL_OPERAND0_RGB = 34192;
/*     */   public static final int GL_OPERAND1_RGB = 34193;
/*     */   public static final int GL_OPERAND2_RGB = 34194;
/*     */   public static final int GL_OPERAND0_ALPHA = 34200;
/*     */   public static final int GL_OPERAND1_ALPHA = 34201;
/*     */   public static final int GL_OPERAND2_ALPHA = 34202;
/*     */   public static final int GL_RGB_SCALE = 34163;
/*     */   public static final int GL_ADD_SIGNED = 34164;
/*     */   public static final int GL_INTERPOLATE = 34165;
/*     */   public static final int GL_SUBTRACT = 34023;
/*     */   public static final int GL_CONSTANT = 34166;
/*     */   public static final int GL_PRIMARY_COLOR = 34167;
/*     */   public static final int GL_PREVIOUS = 34168;
/*     */   public static final int GL_DOT3_RGB = 34478;
/*     */   public static final int GL_DOT3_RGBA = 34479;
/*     */   public static final int GL_CLAMP_TO_BORDER = 33069;
/*     */ 
/*     */   public static void glActiveTexture(int texture)
/*     */   {
/* 118 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 119 */     long function_pointer = caps.glActiveTexture;
/* 120 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 121 */     nglActiveTexture(texture, function_pointer);
/*     */   }
/*     */   static native void nglActiveTexture(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glClientActiveTexture(int texture) {
/* 126 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 127 */     long function_pointer = caps.glClientActiveTexture;
/* 128 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 129 */     StateTracker.getReferences(caps).glClientActiveTexture = (texture - 33984);
/* 130 */     nglClientActiveTexture(texture, function_pointer);
/*     */   }
/*     */   static native void nglClientActiveTexture(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, ByteBuffer data) {
/* 135 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 136 */     long function_pointer = caps.glCompressedTexImage1D;
/* 137 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 138 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 139 */     BufferChecks.checkDirect(data);
/* 140 */     nglCompressedTexImage1D(target, level, internalformat, width, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   static native void nglCompressedTexImage1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*     */ 
/* 144 */   public static void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 145 */     long function_pointer = caps.glCompressedTexImage1D;
/* 146 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 147 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 148 */     nglCompressedTexImage1DBO(target, level, internalformat, width, border, data_imageSize, data_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglCompressedTexImage1DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, ByteBuffer data) {
/* 153 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 154 */     long function_pointer = caps.glCompressedTexImage2D;
/* 155 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 156 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 157 */     BufferChecks.checkDirect(data);
/* 158 */     nglCompressedTexImage2D(target, level, internalformat, width, height, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   static native void nglCompressedTexImage2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*     */ 
/* 162 */   public static void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 163 */     long function_pointer = caps.glCompressedTexImage2D;
/* 164 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 165 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 166 */     nglCompressedTexImage2DBO(target, level, internalformat, width, height, border, data_imageSize, data_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglCompressedTexImage2DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, ByteBuffer data) {
/* 171 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 172 */     long function_pointer = caps.glCompressedTexImage3D;
/* 173 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 174 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 175 */     BufferChecks.checkDirect(data);
/* 176 */     nglCompressedTexImage3D(target, level, internalformat, width, height, depth, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   static native void nglCompressedTexImage3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*     */ 
/* 180 */   public static void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 181 */     long function_pointer = caps.glCompressedTexImage3D;
/* 182 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 183 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 184 */     nglCompressedTexImage3DBO(target, level, internalformat, width, height, depth, border, data_imageSize, data_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglCompressedTexImage3DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, ByteBuffer data) {
/* 189 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 190 */     long function_pointer = caps.glCompressedTexSubImage1D;
/* 191 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 192 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 193 */     BufferChecks.checkDirect(data);
/* 194 */     nglCompressedTexSubImage1D(target, level, xoffset, width, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   static native void nglCompressedTexSubImage1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*     */ 
/* 198 */   public static void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 199 */     long function_pointer = caps.glCompressedTexSubImage1D;
/* 200 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 201 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 202 */     nglCompressedTexSubImage1DBO(target, level, xoffset, width, format, data_imageSize, data_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglCompressedTexSubImage1DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, ByteBuffer data) {
/* 207 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 208 */     long function_pointer = caps.glCompressedTexSubImage2D;
/* 209 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 210 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 211 */     BufferChecks.checkDirect(data);
/* 212 */     nglCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   static native void nglCompressedTexSubImage2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*     */ 
/* 216 */   public static void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 217 */     long function_pointer = caps.glCompressedTexSubImage2D;
/* 218 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 219 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 220 */     nglCompressedTexSubImage2DBO(target, level, xoffset, yoffset, width, height, format, data_imageSize, data_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglCompressedTexSubImage2DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, ByteBuffer data) {
/* 225 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 226 */     long function_pointer = caps.glCompressedTexSubImage3D;
/* 227 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 228 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 229 */     BufferChecks.checkDirect(data);
/* 230 */     nglCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   static native void nglCompressedTexSubImage3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/*     */ 
/* 234 */   public static void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 235 */     long function_pointer = caps.glCompressedTexSubImage3D;
/* 236 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 237 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 238 */     nglCompressedTexSubImage3DBO(target, level, xoffset, yoffset, zoffset, width, height, depth, format, data_imageSize, data_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglCompressedTexSubImage3DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetCompressedTexImage(int target, int lod, ByteBuffer img) {
/* 243 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 244 */     long function_pointer = caps.glGetCompressedTexImage;
/* 245 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 246 */     GLChecks.ensurePackPBOdisabled(caps);
/* 247 */     BufferChecks.checkDirect(img);
/* 248 */     nglGetCompressedTexImage(target, lod, MemoryUtil.getAddress(img), function_pointer);
/*     */   }
/*     */   public static void glGetCompressedTexImage(int target, int lod, IntBuffer img) {
/* 251 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 252 */     long function_pointer = caps.glGetCompressedTexImage;
/* 253 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 254 */     GLChecks.ensurePackPBOdisabled(caps);
/* 255 */     BufferChecks.checkDirect(img);
/* 256 */     nglGetCompressedTexImage(target, lod, MemoryUtil.getAddress(img), function_pointer);
/*     */   }
/*     */   public static void glGetCompressedTexImage(int target, int lod, ShortBuffer img) {
/* 259 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 260 */     long function_pointer = caps.glGetCompressedTexImage;
/* 261 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 262 */     GLChecks.ensurePackPBOdisabled(caps);
/* 263 */     BufferChecks.checkDirect(img);
/* 264 */     nglGetCompressedTexImage(target, lod, MemoryUtil.getAddress(img), function_pointer);
/*     */   }
/*     */   static native void nglGetCompressedTexImage(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/* 268 */   public static void glGetCompressedTexImage(int target, int lod, long img_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 269 */     long function_pointer = caps.glGetCompressedTexImage;
/* 270 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 271 */     GLChecks.ensurePackPBOenabled(caps);
/* 272 */     nglGetCompressedTexImageBO(target, lod, img_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglGetCompressedTexImageBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glMultiTexCoord1f(int target, float s) {
/* 277 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 278 */     long function_pointer = caps.glMultiTexCoord1f;
/* 279 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 280 */     nglMultiTexCoord1f(target, s, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord1f(int paramInt, float paramFloat, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord1d(int target, double s) {
/* 285 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 286 */     long function_pointer = caps.glMultiTexCoord1d;
/* 287 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 288 */     nglMultiTexCoord1d(target, s, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord1d(int paramInt, double paramDouble, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord2f(int target, float s, float t) {
/* 293 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 294 */     long function_pointer = caps.glMultiTexCoord2f;
/* 295 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 296 */     nglMultiTexCoord2f(target, s, t, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord2f(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord2d(int target, double s, double t) {
/* 301 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 302 */     long function_pointer = caps.glMultiTexCoord2d;
/* 303 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 304 */     nglMultiTexCoord2d(target, s, t, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord2d(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord3f(int target, float s, float t, float r) {
/* 309 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 310 */     long function_pointer = caps.glMultiTexCoord3f;
/* 311 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 312 */     nglMultiTexCoord3f(target, s, t, r, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord3f(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord3d(int target, double s, double t, double r) {
/* 317 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 318 */     long function_pointer = caps.glMultiTexCoord3d;
/* 319 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 320 */     nglMultiTexCoord3d(target, s, t, r, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord3d(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord4f(int target, float s, float t, float r, float q) {
/* 325 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 326 */     long function_pointer = caps.glMultiTexCoord4f;
/* 327 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 328 */     nglMultiTexCoord4f(target, s, t, r, q, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord4f(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord4d(int target, double s, double t, double r, double q) {
/* 333 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 334 */     long function_pointer = caps.glMultiTexCoord4d;
/* 335 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 336 */     nglMultiTexCoord4d(target, s, t, r, q, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord4d(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*     */ 
/*     */   public static void glLoadTransposeMatrix(FloatBuffer m) {
/* 341 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 342 */     long function_pointer = caps.glLoadTransposeMatrixf;
/* 343 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 344 */     BufferChecks.checkBuffer(m, 16);
/* 345 */     nglLoadTransposeMatrixf(MemoryUtil.getAddress(m), function_pointer);
/*     */   }
/*     */   static native void nglLoadTransposeMatrixf(long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glLoadTransposeMatrix(DoubleBuffer m) {
/* 350 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 351 */     long function_pointer = caps.glLoadTransposeMatrixd;
/* 352 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 353 */     BufferChecks.checkBuffer(m, 16);
/* 354 */     nglLoadTransposeMatrixd(MemoryUtil.getAddress(m), function_pointer);
/*     */   }
/*     */   static native void nglLoadTransposeMatrixd(long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glMultTransposeMatrix(FloatBuffer m) {
/* 359 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 360 */     long function_pointer = caps.glMultTransposeMatrixf;
/* 361 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 362 */     BufferChecks.checkBuffer(m, 16);
/* 363 */     nglMultTransposeMatrixf(MemoryUtil.getAddress(m), function_pointer);
/*     */   }
/*     */   static native void nglMultTransposeMatrixf(long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glMultTransposeMatrix(DoubleBuffer m) {
/* 368 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 369 */     long function_pointer = caps.glMultTransposeMatrixd;
/* 370 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 371 */     BufferChecks.checkBuffer(m, 16);
/* 372 */     nglMultTransposeMatrixd(MemoryUtil.getAddress(m), function_pointer);
/*     */   }
/*     */   static native void nglMultTransposeMatrixd(long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glSampleCoverage(float value, boolean invert) {
/* 377 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 378 */     long function_pointer = caps.glSampleCoverage;
/* 379 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 380 */     nglSampleCoverage(value, invert, function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglSampleCoverage(float paramFloat, boolean paramBoolean, long paramLong);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL13
 * JD-Core Version:    0.6.2
 */