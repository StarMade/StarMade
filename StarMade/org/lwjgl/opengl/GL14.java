/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class GL14
/*     */ {
/*     */   public static final int GL_GENERATE_MIPMAP = 33169;
/*     */   public static final int GL_GENERATE_MIPMAP_HINT = 33170;
/*     */   public static final int GL_DEPTH_COMPONENT16 = 33189;
/*     */   public static final int GL_DEPTH_COMPONENT24 = 33190;
/*     */   public static final int GL_DEPTH_COMPONENT32 = 33191;
/*     */   public static final int GL_TEXTURE_DEPTH_SIZE = 34890;
/*     */   public static final int GL_DEPTH_TEXTURE_MODE = 34891;
/*     */   public static final int GL_TEXTURE_COMPARE_MODE = 34892;
/*     */   public static final int GL_TEXTURE_COMPARE_FUNC = 34893;
/*     */   public static final int GL_COMPARE_R_TO_TEXTURE = 34894;
/*     */   public static final int GL_FOG_COORDINATE_SOURCE = 33872;
/*     */   public static final int GL_FOG_COORDINATE = 33873;
/*     */   public static final int GL_FRAGMENT_DEPTH = 33874;
/*     */   public static final int GL_CURRENT_FOG_COORDINATE = 33875;
/*     */   public static final int GL_FOG_COORDINATE_ARRAY_TYPE = 33876;
/*     */   public static final int GL_FOG_COORDINATE_ARRAY_STRIDE = 33877;
/*     */   public static final int GL_FOG_COORDINATE_ARRAY_POINTER = 33878;
/*     */   public static final int GL_FOG_COORDINATE_ARRAY = 33879;
/*     */   public static final int GL_POINT_SIZE_MIN = 33062;
/*     */   public static final int GL_POINT_SIZE_MAX = 33063;
/*     */   public static final int GL_POINT_FADE_THRESHOLD_SIZE = 33064;
/*     */   public static final int GL_POINT_DISTANCE_ATTENUATION = 33065;
/*     */   public static final int GL_COLOR_SUM = 33880;
/*     */   public static final int GL_CURRENT_SECONDARY_COLOR = 33881;
/*     */   public static final int GL_SECONDARY_COLOR_ARRAY_SIZE = 33882;
/*     */   public static final int GL_SECONDARY_COLOR_ARRAY_TYPE = 33883;
/*     */   public static final int GL_SECONDARY_COLOR_ARRAY_STRIDE = 33884;
/*     */   public static final int GL_SECONDARY_COLOR_ARRAY_POINTER = 33885;
/*     */   public static final int GL_SECONDARY_COLOR_ARRAY = 33886;
/*     */   public static final int GL_BLEND_DST_RGB = 32968;
/*     */   public static final int GL_BLEND_SRC_RGB = 32969;
/*     */   public static final int GL_BLEND_DST_ALPHA = 32970;
/*     */   public static final int GL_BLEND_SRC_ALPHA = 32971;
/*     */   public static final int GL_INCR_WRAP = 34055;
/*     */   public static final int GL_DECR_WRAP = 34056;
/*     */   public static final int GL_TEXTURE_FILTER_CONTROL = 34048;
/*     */   public static final int GL_TEXTURE_LOD_BIAS = 34049;
/*     */   public static final int GL_MAX_TEXTURE_LOD_BIAS = 34045;
/*     */   public static final int GL_MIRRORED_REPEAT = 33648;
/*     */   public static final int GL_BLEND_COLOR = 32773;
/*     */   public static final int GL_BLEND_EQUATION = 32777;
/*     */   public static final int GL_FUNC_ADD = 32774;
/*     */   public static final int GL_FUNC_SUBTRACT = 32778;
/*     */   public static final int GL_FUNC_REVERSE_SUBTRACT = 32779;
/*     */   public static final int GL_MIN = 32775;
/*     */   public static final int GL_MAX = 32776;
/*     */ 
/*     */   public static void glBlendEquation(int mode)
/*     */   {
/*  68 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  69 */     long function_pointer = caps.glBlendEquation;
/*  70 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  71 */     nglBlendEquation(mode, function_pointer);
/*     */   }
/*     */   static native void nglBlendEquation(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glBlendColor(float red, float green, float blue, float alpha) {
/*  76 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  77 */     long function_pointer = caps.glBlendColor;
/*  78 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  79 */     nglBlendColor(red, green, blue, alpha, function_pointer);
/*     */   }
/*     */   static native void nglBlendColor(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*     */ 
/*     */   public static void glFogCoordf(float coord) {
/*  84 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  85 */     long function_pointer = caps.glFogCoordf;
/*  86 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  87 */     nglFogCoordf(coord, function_pointer);
/*     */   }
/*     */   static native void nglFogCoordf(float paramFloat, long paramLong);
/*     */ 
/*     */   public static void glFogCoordd(double coord) {
/*  92 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  93 */     long function_pointer = caps.glFogCoordd;
/*  94 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  95 */     nglFogCoordd(coord, function_pointer);
/*     */   }
/*     */   static native void nglFogCoordd(double paramDouble, long paramLong);
/*     */ 
/*     */   public static void glFogCoordPointer(int stride, DoubleBuffer data) {
/* 100 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 101 */     long function_pointer = caps.glFogCoordPointer;
/* 102 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 103 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 104 */     BufferChecks.checkDirect(data);
/* 105 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL14_glFogCoordPointer_data = data;
/* 106 */     nglFogCoordPointer(5130, stride, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glFogCoordPointer(int stride, FloatBuffer data) {
/* 109 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 110 */     long function_pointer = caps.glFogCoordPointer;
/* 111 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 112 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 113 */     BufferChecks.checkDirect(data);
/* 114 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).GL14_glFogCoordPointer_data = data;
/* 115 */     nglFogCoordPointer(5126, stride, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   static native void nglFogCoordPointer(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/* 119 */   public static void glFogCoordPointer(int type, int stride, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 120 */     long function_pointer = caps.glFogCoordPointer;
/* 121 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 122 */     GLChecks.ensureArrayVBOenabled(caps);
/* 123 */     nglFogCoordPointerBO(type, stride, data_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglFogCoordPointerBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glMultiDrawArrays(int mode, IntBuffer piFirst, IntBuffer piCount) {
/* 128 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 129 */     long function_pointer = caps.glMultiDrawArrays;
/* 130 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 131 */     BufferChecks.checkDirect(piFirst);
/* 132 */     BufferChecks.checkBuffer(piCount, piFirst.remaining());
/* 133 */     nglMultiDrawArrays(mode, MemoryUtil.getAddress(piFirst), MemoryUtil.getAddress(piCount), piFirst.remaining(), function_pointer);
/*     */   }
/*     */   static native void nglMultiDrawArrays(int paramInt1, long paramLong1, long paramLong2, int paramInt2, long paramLong3);
/*     */ 
/*     */   public static void glPointParameteri(int pname, int param) {
/* 138 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 139 */     long function_pointer = caps.glPointParameteri;
/* 140 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 141 */     nglPointParameteri(pname, param, function_pointer);
/*     */   }
/*     */   static native void nglPointParameteri(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glPointParameterf(int pname, float param) {
/* 146 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 147 */     long function_pointer = caps.glPointParameterf;
/* 148 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 149 */     nglPointParameterf(pname, param, function_pointer);
/*     */   }
/*     */   static native void nglPointParameterf(int paramInt, float paramFloat, long paramLong);
/*     */ 
/*     */   public static void glPointParameter(int pname, IntBuffer params) {
/* 154 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 155 */     long function_pointer = caps.glPointParameteriv;
/* 156 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 157 */     BufferChecks.checkBuffer(params, 4);
/* 158 */     nglPointParameteriv(pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglPointParameteriv(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glPointParameter(int pname, FloatBuffer params) {
/* 163 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 164 */     long function_pointer = caps.glPointParameterfv;
/* 165 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 166 */     BufferChecks.checkBuffer(params, 4);
/* 167 */     nglPointParameterfv(pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglPointParameterfv(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glSecondaryColor3b(byte red, byte green, byte blue) {
/* 172 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 173 */     long function_pointer = caps.glSecondaryColor3b;
/* 174 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 175 */     nglSecondaryColor3b(red, green, blue, function_pointer);
/*     */   }
/*     */   static native void nglSecondaryColor3b(byte paramByte1, byte paramByte2, byte paramByte3, long paramLong);
/*     */ 
/*     */   public static void glSecondaryColor3f(float red, float green, float blue) {
/* 180 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 181 */     long function_pointer = caps.glSecondaryColor3f;
/* 182 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 183 */     nglSecondaryColor3f(red, green, blue, function_pointer);
/*     */   }
/*     */   static native void nglSecondaryColor3f(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*     */ 
/*     */   public static void glSecondaryColor3d(double red, double green, double blue) {
/* 188 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 189 */     long function_pointer = caps.glSecondaryColor3d;
/* 190 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 191 */     nglSecondaryColor3d(red, green, blue, function_pointer);
/*     */   }
/*     */   static native void nglSecondaryColor3d(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*     */ 
/*     */   public static void glSecondaryColor3ub(byte red, byte green, byte blue) {
/* 196 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 197 */     long function_pointer = caps.glSecondaryColor3ub;
/* 198 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 199 */     nglSecondaryColor3ub(red, green, blue, function_pointer);
/*     */   }
/*     */   static native void nglSecondaryColor3ub(byte paramByte1, byte paramByte2, byte paramByte3, long paramLong);
/*     */ 
/*     */   public static void glSecondaryColorPointer(int size, int stride, DoubleBuffer data) {
/* 204 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 205 */     long function_pointer = caps.glSecondaryColorPointer;
/* 206 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 207 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 208 */     BufferChecks.checkDirect(data);
/* 209 */     nglSecondaryColorPointer(size, 5130, stride, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glSecondaryColorPointer(int size, int stride, FloatBuffer data) {
/* 212 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 213 */     long function_pointer = caps.glSecondaryColorPointer;
/* 214 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 215 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 216 */     BufferChecks.checkDirect(data);
/* 217 */     nglSecondaryColorPointer(size, 5126, stride, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glSecondaryColorPointer(int size, boolean unsigned, int stride, ByteBuffer data) {
/* 220 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 221 */     long function_pointer = caps.glSecondaryColorPointer;
/* 222 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 223 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 224 */     BufferChecks.checkDirect(data);
/* 225 */     nglSecondaryColorPointer(size, unsigned ? 5121 : 5120, stride, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   static native void nglSecondaryColorPointer(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/* 229 */   public static void glSecondaryColorPointer(int size, int type, int stride, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 230 */     long function_pointer = caps.glSecondaryColorPointer;
/* 231 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 232 */     GLChecks.ensureArrayVBOenabled(caps);
/* 233 */     nglSecondaryColorPointerBO(size, type, stride, data_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglSecondaryColorPointerBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glBlendFuncSeparate(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha) {
/* 238 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 239 */     long function_pointer = caps.glBlendFuncSeparate;
/* 240 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 241 */     nglBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha, function_pointer);
/*     */   }
/*     */   static native void nglBlendFuncSeparate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glWindowPos2f(float x, float y) {
/* 246 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 247 */     long function_pointer = caps.glWindowPos2f;
/* 248 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 249 */     nglWindowPos2f(x, y, function_pointer);
/*     */   }
/*     */   static native void nglWindowPos2f(float paramFloat1, float paramFloat2, long paramLong);
/*     */ 
/*     */   public static void glWindowPos2d(double x, double y) {
/* 254 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 255 */     long function_pointer = caps.glWindowPos2d;
/* 256 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 257 */     nglWindowPos2d(x, y, function_pointer);
/*     */   }
/*     */   static native void nglWindowPos2d(double paramDouble1, double paramDouble2, long paramLong);
/*     */ 
/*     */   public static void glWindowPos2i(int x, int y) {
/* 262 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 263 */     long function_pointer = caps.glWindowPos2i;
/* 264 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 265 */     nglWindowPos2i(x, y, function_pointer);
/*     */   }
/*     */   static native void nglWindowPos2i(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glWindowPos3f(float x, float y, float z) {
/* 270 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 271 */     long function_pointer = caps.glWindowPos3f;
/* 272 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 273 */     nglWindowPos3f(x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglWindowPos3f(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*     */ 
/*     */   public static void glWindowPos3d(double x, double y, double z) {
/* 278 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 279 */     long function_pointer = caps.glWindowPos3d;
/* 280 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 281 */     nglWindowPos3d(x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglWindowPos3d(double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*     */ 
/*     */   public static void glWindowPos3i(int x, int y, int z) {
/* 286 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 287 */     long function_pointer = caps.glWindowPos3i;
/* 288 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 289 */     nglWindowPos3i(x, y, z, function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglWindowPos3i(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL14
 * JD-Core Version:    0.6.2
 */