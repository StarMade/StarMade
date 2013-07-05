/*      */ package org.lwjgl.opengl;
/*      */ 
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.ByteOrder;
/*      */ import java.nio.DoubleBuffer;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.nio.ShortBuffer;
/*      */ import org.lwjgl.BufferChecks;
/*      */ import org.lwjgl.LWJGLUtil;
/*      */ import org.lwjgl.MemoryUtil;
/*      */ 
/*      */ public final class EXTDirectStateAccess
/*      */ {
/*      */   public static final int GL_PROGRAM_MATRIX_EXT = 36397;
/*      */   public static final int GL_TRANSPOSE_PROGRAM_MATRIX_EXT = 36398;
/*      */   public static final int GL_PROGRAM_MATRIX_STACK_DEPTH_EXT = 36399;
/*      */ 
/*      */   public static void glClientAttribDefaultEXT(int mask)
/*      */   {
/*   22 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   23 */     long function_pointer = caps.glClientAttribDefaultEXT;
/*   24 */     BufferChecks.checkFunctionAddress(function_pointer);
/*   25 */     nglClientAttribDefaultEXT(mask, function_pointer);
/*      */   }
/*      */   static native void nglClientAttribDefaultEXT(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glPushClientAttribDefaultEXT(int mask) {
/*   30 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   31 */     long function_pointer = caps.glPushClientAttribDefaultEXT;
/*   32 */     BufferChecks.checkFunctionAddress(function_pointer);
/*   33 */     nglPushClientAttribDefaultEXT(mask, function_pointer);
/*      */   }
/*      */   static native void nglPushClientAttribDefaultEXT(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glMatrixLoadEXT(int matrixMode, FloatBuffer m) {
/*   38 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   39 */     long function_pointer = caps.glMatrixLoadfEXT;
/*   40 */     BufferChecks.checkFunctionAddress(function_pointer);
/*   41 */     BufferChecks.checkBuffer(m, 16);
/*   42 */     nglMatrixLoadfEXT(matrixMode, MemoryUtil.getAddress(m), function_pointer);
/*      */   }
/*      */   static native void nglMatrixLoadfEXT(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMatrixLoadEXT(int matrixMode, DoubleBuffer m) {
/*   47 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   48 */     long function_pointer = caps.glMatrixLoaddEXT;
/*   49 */     BufferChecks.checkFunctionAddress(function_pointer);
/*   50 */     BufferChecks.checkBuffer(m, 16);
/*   51 */     nglMatrixLoaddEXT(matrixMode, MemoryUtil.getAddress(m), function_pointer);
/*      */   }
/*      */   static native void nglMatrixLoaddEXT(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMatrixMultEXT(int matrixMode, FloatBuffer m) {
/*   56 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   57 */     long function_pointer = caps.glMatrixMultfEXT;
/*   58 */     BufferChecks.checkFunctionAddress(function_pointer);
/*   59 */     BufferChecks.checkBuffer(m, 16);
/*   60 */     nglMatrixMultfEXT(matrixMode, MemoryUtil.getAddress(m), function_pointer);
/*      */   }
/*      */   static native void nglMatrixMultfEXT(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMatrixMultEXT(int matrixMode, DoubleBuffer m) {
/*   65 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   66 */     long function_pointer = caps.glMatrixMultdEXT;
/*   67 */     BufferChecks.checkFunctionAddress(function_pointer);
/*   68 */     BufferChecks.checkBuffer(m, 16);
/*   69 */     nglMatrixMultdEXT(matrixMode, MemoryUtil.getAddress(m), function_pointer);
/*      */   }
/*      */   static native void nglMatrixMultdEXT(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMatrixLoadIdentityEXT(int matrixMode) {
/*   74 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   75 */     long function_pointer = caps.glMatrixLoadIdentityEXT;
/*   76 */     BufferChecks.checkFunctionAddress(function_pointer);
/*   77 */     nglMatrixLoadIdentityEXT(matrixMode, function_pointer);
/*      */   }
/*      */   static native void nglMatrixLoadIdentityEXT(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glMatrixRotatefEXT(int matrixMode, float angle, float x, float y, float z) {
/*   82 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   83 */     long function_pointer = caps.glMatrixRotatefEXT;
/*   84 */     BufferChecks.checkFunctionAddress(function_pointer);
/*   85 */     nglMatrixRotatefEXT(matrixMode, angle, x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglMatrixRotatefEXT(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*      */ 
/*      */   public static void glMatrixRotatedEXT(int matrixMode, double angle, double x, double y, double z) {
/*   90 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   91 */     long function_pointer = caps.glMatrixRotatedEXT;
/*   92 */     BufferChecks.checkFunctionAddress(function_pointer);
/*   93 */     nglMatrixRotatedEXT(matrixMode, angle, x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglMatrixRotatedEXT(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*      */ 
/*      */   public static void glMatrixScalefEXT(int matrixMode, float x, float y, float z) {
/*   98 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   99 */     long function_pointer = caps.glMatrixScalefEXT;
/*  100 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  101 */     nglMatrixScalefEXT(matrixMode, x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglMatrixScalefEXT(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*      */ 
/*      */   public static void glMatrixScaledEXT(int matrixMode, double x, double y, double z) {
/*  106 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  107 */     long function_pointer = caps.glMatrixScaledEXT;
/*  108 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  109 */     nglMatrixScaledEXT(matrixMode, x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglMatrixScaledEXT(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*      */ 
/*      */   public static void glMatrixTranslatefEXT(int matrixMode, float x, float y, float z) {
/*  114 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  115 */     long function_pointer = caps.glMatrixTranslatefEXT;
/*  116 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  117 */     nglMatrixTranslatefEXT(matrixMode, x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglMatrixTranslatefEXT(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*      */ 
/*      */   public static void glMatrixTranslatedEXT(int matrixMode, double x, double y, double z) {
/*  122 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  123 */     long function_pointer = caps.glMatrixTranslatedEXT;
/*  124 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  125 */     nglMatrixTranslatedEXT(matrixMode, x, y, z, function_pointer);
/*      */   }
/*      */   static native void nglMatrixTranslatedEXT(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*      */ 
/*      */   public static void glMatrixOrthoEXT(int matrixMode, double l, double r, double b, double t, double n, double f) {
/*  130 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  131 */     long function_pointer = caps.glMatrixOrthoEXT;
/*  132 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  133 */     nglMatrixOrthoEXT(matrixMode, l, r, b, t, n, f, function_pointer);
/*      */   }
/*      */   static native void nglMatrixOrthoEXT(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, long paramLong);
/*      */ 
/*      */   public static void glMatrixFrustumEXT(int matrixMode, double l, double r, double b, double t, double n, double f) {
/*  138 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  139 */     long function_pointer = caps.glMatrixFrustumEXT;
/*  140 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  141 */     nglMatrixFrustumEXT(matrixMode, l, r, b, t, n, f, function_pointer);
/*      */   }
/*      */   static native void nglMatrixFrustumEXT(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, long paramLong);
/*      */ 
/*      */   public static void glMatrixPushEXT(int matrixMode) {
/*  146 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  147 */     long function_pointer = caps.glMatrixPushEXT;
/*  148 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  149 */     nglMatrixPushEXT(matrixMode, function_pointer);
/*      */   }
/*      */   static native void nglMatrixPushEXT(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glMatrixPopEXT(int matrixMode) {
/*  154 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  155 */     long function_pointer = caps.glMatrixPopEXT;
/*  156 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  157 */     nglMatrixPopEXT(matrixMode, function_pointer);
/*      */   }
/*      */   static native void nglMatrixPopEXT(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glTextureParameteriEXT(int texture, int target, int pname, int param) {
/*  162 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  163 */     long function_pointer = caps.glTextureParameteriEXT;
/*  164 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  165 */     nglTextureParameteriEXT(texture, target, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglTextureParameteriEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glTextureParameterEXT(int texture, int target, int pname, IntBuffer param) {
/*  170 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  171 */     long function_pointer = caps.glTextureParameterivEXT;
/*  172 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  173 */     BufferChecks.checkBuffer(param, 4);
/*  174 */     nglTextureParameterivEXT(texture, target, pname, MemoryUtil.getAddress(param), function_pointer);
/*      */   }
/*      */   static native void nglTextureParameterivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTextureParameterfEXT(int texture, int target, int pname, float param) {
/*  179 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  180 */     long function_pointer = caps.glTextureParameterfEXT;
/*  181 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  182 */     nglTextureParameterfEXT(texture, target, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglTextureParameterfEXT(int paramInt1, int paramInt2, int paramInt3, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glTextureParameterEXT(int texture, int target, int pname, FloatBuffer param) {
/*  187 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  188 */     long function_pointer = caps.glTextureParameterfvEXT;
/*  189 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  190 */     BufferChecks.checkBuffer(param, 4);
/*  191 */     nglTextureParameterfvEXT(texture, target, pname, MemoryUtil.getAddress(param), function_pointer);
/*      */   }
/*      */   static native void nglTextureParameterfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTextureImage1DEXT(int texture, int target, int level, int internalformat, int width, int border, int format, int type, ByteBuffer pixels) {
/*  196 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  197 */     long function_pointer = caps.glTextureImage1DEXT;
/*  198 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  199 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  200 */     if (pixels != null)
/*  201 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  202 */     nglTextureImage1DEXT(texture, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureImage1DEXT(int texture, int target, int level, int internalformat, int width, int border, int format, int type, DoubleBuffer pixels) {
/*  205 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  206 */     long function_pointer = caps.glTextureImage1DEXT;
/*  207 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  208 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  209 */     if (pixels != null)
/*  210 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  211 */     nglTextureImage1DEXT(texture, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureImage1DEXT(int texture, int target, int level, int internalformat, int width, int border, int format, int type, FloatBuffer pixels) {
/*  214 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  215 */     long function_pointer = caps.glTextureImage1DEXT;
/*  216 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  217 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  218 */     if (pixels != null)
/*  219 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  220 */     nglTextureImage1DEXT(texture, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureImage1DEXT(int texture, int target, int level, int internalformat, int width, int border, int format, int type, IntBuffer pixels) {
/*  223 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  224 */     long function_pointer = caps.glTextureImage1DEXT;
/*  225 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  226 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  227 */     if (pixels != null)
/*  228 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  229 */     nglTextureImage1DEXT(texture, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureImage1DEXT(int texture, int target, int level, int internalformat, int width, int border, int format, int type, ShortBuffer pixels) {
/*  232 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  233 */     long function_pointer = caps.glTextureImage1DEXT;
/*  234 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  235 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  236 */     if (pixels != null)
/*  237 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  238 */     nglTextureImage1DEXT(texture, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   static native void nglTextureImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*      */ 
/*  242 */   public static void glTextureImage1DEXT(int texture, int target, int level, int internalformat, int width, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  243 */     long function_pointer = caps.glTextureImage1DEXT;
/*  244 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  245 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  246 */     nglTextureImage1DEXTBO(texture, target, level, internalformat, width, border, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglTextureImage1DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTextureImage2DEXT(int texture, int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels) {
/*  251 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  252 */     long function_pointer = caps.glTextureImage2DEXT;
/*  253 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  254 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  255 */     if (pixels != null)
/*  256 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  257 */     nglTextureImage2DEXT(texture, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureImage2DEXT(int texture, int target, int level, int internalformat, int width, int height, int border, int format, int type, DoubleBuffer pixels) {
/*  260 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  261 */     long function_pointer = caps.glTextureImage2DEXT;
/*  262 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  263 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  264 */     if (pixels != null)
/*  265 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  266 */     nglTextureImage2DEXT(texture, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureImage2DEXT(int texture, int target, int level, int internalformat, int width, int height, int border, int format, int type, FloatBuffer pixels) {
/*  269 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  270 */     long function_pointer = caps.glTextureImage2DEXT;
/*  271 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  272 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  273 */     if (pixels != null)
/*  274 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  275 */     nglTextureImage2DEXT(texture, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureImage2DEXT(int texture, int target, int level, int internalformat, int width, int height, int border, int format, int type, IntBuffer pixels) {
/*  278 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  279 */     long function_pointer = caps.glTextureImage2DEXT;
/*  280 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  281 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  282 */     if (pixels != null)
/*  283 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  284 */     nglTextureImage2DEXT(texture, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureImage2DEXT(int texture, int target, int level, int internalformat, int width, int height, int border, int format, int type, ShortBuffer pixels) {
/*  287 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  288 */     long function_pointer = caps.glTextureImage2DEXT;
/*  289 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  290 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  291 */     if (pixels != null)
/*  292 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  293 */     nglTextureImage2DEXT(texture, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   static native void nglTextureImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*      */ 
/*  297 */   public static void glTextureImage2DEXT(int texture, int target, int level, int internalformat, int width, int height, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  298 */     long function_pointer = caps.glTextureImage2DEXT;
/*  299 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  300 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  301 */     nglTextureImage2DEXTBO(texture, target, level, internalformat, width, height, border, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglTextureImage2DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int width, int format, int type, ByteBuffer pixels) {
/*  306 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  307 */     long function_pointer = caps.glTextureSubImage1DEXT;
/*  308 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  309 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  310 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/*  311 */     nglTextureSubImage1DEXT(texture, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int width, int format, int type, DoubleBuffer pixels) {
/*  314 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  315 */     long function_pointer = caps.glTextureSubImage1DEXT;
/*  316 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  317 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  318 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/*  319 */     nglTextureSubImage1DEXT(texture, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int width, int format, int type, FloatBuffer pixels) {
/*  322 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  323 */     long function_pointer = caps.glTextureSubImage1DEXT;
/*  324 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  325 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  326 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/*  327 */     nglTextureSubImage1DEXT(texture, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int width, int format, int type, IntBuffer pixels) {
/*  330 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  331 */     long function_pointer = caps.glTextureSubImage1DEXT;
/*  332 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  333 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  334 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/*  335 */     nglTextureSubImage1DEXT(texture, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int width, int format, int type, ShortBuffer pixels) {
/*  338 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  339 */     long function_pointer = caps.glTextureSubImage1DEXT;
/*  340 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  341 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  342 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/*  343 */     nglTextureSubImage1DEXT(texture, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   static native void nglTextureSubImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*      */ 
/*  347 */   public static void glTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int width, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  348 */     long function_pointer = caps.glTextureSubImage1DEXT;
/*  349 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  350 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  351 */     nglTextureSubImage1DEXTBO(texture, target, level, xoffset, width, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglTextureSubImage1DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels) {
/*  356 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  357 */     long function_pointer = caps.glTextureSubImage2DEXT;
/*  358 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  359 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  360 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/*  361 */     nglTextureSubImage2DEXT(texture, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, DoubleBuffer pixels) {
/*  364 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  365 */     long function_pointer = caps.glTextureSubImage2DEXT;
/*  366 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  367 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  368 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/*  369 */     nglTextureSubImage2DEXT(texture, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, FloatBuffer pixels) {
/*  372 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  373 */     long function_pointer = caps.glTextureSubImage2DEXT;
/*  374 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  375 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  376 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/*  377 */     nglTextureSubImage2DEXT(texture, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, IntBuffer pixels) {
/*  380 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  381 */     long function_pointer = caps.glTextureSubImage2DEXT;
/*  382 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  383 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  384 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/*  385 */     nglTextureSubImage2DEXT(texture, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ShortBuffer pixels) {
/*  388 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  389 */     long function_pointer = caps.glTextureSubImage2DEXT;
/*  390 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  391 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  392 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/*  393 */     nglTextureSubImage2DEXT(texture, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   static native void nglTextureSubImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*      */ 
/*  397 */   public static void glTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  398 */     long function_pointer = caps.glTextureSubImage2DEXT;
/*  399 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  400 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  401 */     nglTextureSubImage2DEXTBO(texture, target, level, xoffset, yoffset, width, height, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglTextureSubImage2DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCopyTextureImage1DEXT(int texture, int target, int level, int internalformat, int x, int y, int width, int border) {
/*  406 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  407 */     long function_pointer = caps.glCopyTextureImage1DEXT;
/*  408 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  409 */     nglCopyTextureImage1DEXT(texture, target, level, internalformat, x, y, width, border, function_pointer);
/*      */   }
/*      */   static native void nglCopyTextureImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong);
/*      */ 
/*      */   public static void glCopyTextureImage2DEXT(int texture, int target, int level, int internalformat, int x, int y, int width, int height, int border) {
/*  414 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  415 */     long function_pointer = caps.glCopyTextureImage2DEXT;
/*  416 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  417 */     nglCopyTextureImage2DEXT(texture, target, level, internalformat, x, y, width, height, border, function_pointer);
/*      */   }
/*      */   static native void nglCopyTextureImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong);
/*      */ 
/*      */   public static void glCopyTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int x, int y, int width) {
/*  422 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  423 */     long function_pointer = caps.glCopyTextureSubImage1DEXT;
/*  424 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  425 */     nglCopyTextureSubImage1DEXT(texture, target, level, xoffset, x, y, width, function_pointer);
/*      */   }
/*      */   static native void nglCopyTextureSubImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong);
/*      */ 
/*      */   public static void glCopyTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
/*  430 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  431 */     long function_pointer = caps.glCopyTextureSubImage2DEXT;
/*  432 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  433 */     nglCopyTextureSubImage2DEXT(texture, target, level, xoffset, yoffset, x, y, width, height, function_pointer);
/*      */   }
/*      */   static native void nglCopyTextureSubImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong);
/*      */ 
/*      */   public static void glGetTextureImageEXT(int texture, int target, int level, int format, int type, ByteBuffer pixels) {
/*  438 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  439 */     long function_pointer = caps.glGetTextureImageEXT;
/*  440 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  441 */     GLChecks.ensurePackPBOdisabled(caps);
/*  442 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/*  443 */     nglGetTextureImageEXT(texture, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glGetTextureImageEXT(int texture, int target, int level, int format, int type, DoubleBuffer pixels) {
/*  446 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  447 */     long function_pointer = caps.glGetTextureImageEXT;
/*  448 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  449 */     GLChecks.ensurePackPBOdisabled(caps);
/*  450 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/*  451 */     nglGetTextureImageEXT(texture, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glGetTextureImageEXT(int texture, int target, int level, int format, int type, FloatBuffer pixels) {
/*  454 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  455 */     long function_pointer = caps.glGetTextureImageEXT;
/*  456 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  457 */     GLChecks.ensurePackPBOdisabled(caps);
/*  458 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/*  459 */     nglGetTextureImageEXT(texture, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glGetTextureImageEXT(int texture, int target, int level, int format, int type, IntBuffer pixels) {
/*  462 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  463 */     long function_pointer = caps.glGetTextureImageEXT;
/*  464 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  465 */     GLChecks.ensurePackPBOdisabled(caps);
/*  466 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/*  467 */     nglGetTextureImageEXT(texture, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glGetTextureImageEXT(int texture, int target, int level, int format, int type, ShortBuffer pixels) {
/*  470 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  471 */     long function_pointer = caps.glGetTextureImageEXT;
/*  472 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  473 */     GLChecks.ensurePackPBOdisabled(caps);
/*  474 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/*  475 */     nglGetTextureImageEXT(texture, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   static native void nglGetTextureImageEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*  479 */   public static void glGetTextureImageEXT(int texture, int target, int level, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  480 */     long function_pointer = caps.glGetTextureImageEXT;
/*  481 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  482 */     GLChecks.ensurePackPBOenabled(caps);
/*  483 */     nglGetTextureImageEXTBO(texture, target, level, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetTextureImageEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetTextureParameterEXT(int texture, int target, int pname, FloatBuffer params) {
/*  488 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  489 */     long function_pointer = caps.glGetTextureParameterfvEXT;
/*  490 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  491 */     BufferChecks.checkBuffer(params, 4);
/*  492 */     nglGetTextureParameterfvEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTextureParameterfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static float glGetTextureParameterfEXT(int texture, int target, int pname) {
/*  498 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  499 */     long function_pointer = caps.glGetTextureParameterfvEXT;
/*  500 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  501 */     FloatBuffer params = APIUtil.getBufferFloat(caps);
/*  502 */     nglGetTextureParameterfvEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  503 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetTextureParameterEXT(int texture, int target, int pname, IntBuffer params) {
/*  507 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  508 */     long function_pointer = caps.glGetTextureParameterivEXT;
/*  509 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  510 */     BufferChecks.checkBuffer(params, 4);
/*  511 */     nglGetTextureParameterivEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTextureParameterivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetTextureParameteriEXT(int texture, int target, int pname) {
/*  517 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  518 */     long function_pointer = caps.glGetTextureParameterivEXT;
/*  519 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  520 */     IntBuffer params = APIUtil.getBufferInt(caps);
/*  521 */     nglGetTextureParameterivEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  522 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetTextureLevelParameterEXT(int texture, int target, int level, int pname, FloatBuffer params) {
/*  526 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  527 */     long function_pointer = caps.glGetTextureLevelParameterfvEXT;
/*  528 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  529 */     BufferChecks.checkBuffer(params, 4);
/*  530 */     nglGetTextureLevelParameterfvEXT(texture, target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTextureLevelParameterfvEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static float glGetTextureLevelParameterfEXT(int texture, int target, int level, int pname) {
/*  536 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  537 */     long function_pointer = caps.glGetTextureLevelParameterfvEXT;
/*  538 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  539 */     FloatBuffer params = APIUtil.getBufferFloat(caps);
/*  540 */     nglGetTextureLevelParameterfvEXT(texture, target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/*  541 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetTextureLevelParameterEXT(int texture, int target, int level, int pname, IntBuffer params) {
/*  545 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  546 */     long function_pointer = caps.glGetTextureLevelParameterivEXT;
/*  547 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  548 */     BufferChecks.checkBuffer(params, 4);
/*  549 */     nglGetTextureLevelParameterivEXT(texture, target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTextureLevelParameterivEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetTextureLevelParameteriEXT(int texture, int target, int level, int pname) {
/*  555 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  556 */     long function_pointer = caps.glGetTextureLevelParameterivEXT;
/*  557 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  558 */     IntBuffer params = APIUtil.getBufferInt(caps);
/*  559 */     nglGetTextureLevelParameterivEXT(texture, target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/*  560 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glTextureImage3DEXT(int texture, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels) {
/*  564 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  565 */     long function_pointer = caps.glTextureImage3DEXT;
/*  566 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  567 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  568 */     if (pixels != null)
/*  569 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/*  570 */     nglTextureImage3DEXT(texture, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureImage3DEXT(int texture, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, DoubleBuffer pixels) {
/*  573 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  574 */     long function_pointer = caps.glTextureImage3DEXT;
/*  575 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  576 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  577 */     if (pixels != null)
/*  578 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/*  579 */     nglTextureImage3DEXT(texture, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureImage3DEXT(int texture, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, FloatBuffer pixels) {
/*  582 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  583 */     long function_pointer = caps.glTextureImage3DEXT;
/*  584 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  585 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  586 */     if (pixels != null)
/*  587 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/*  588 */     nglTextureImage3DEXT(texture, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureImage3DEXT(int texture, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, IntBuffer pixels) {
/*  591 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  592 */     long function_pointer = caps.glTextureImage3DEXT;
/*  593 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  594 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  595 */     if (pixels != null)
/*  596 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/*  597 */     nglTextureImage3DEXT(texture, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureImage3DEXT(int texture, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, ShortBuffer pixels) {
/*  600 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  601 */     long function_pointer = caps.glTextureImage3DEXT;
/*  602 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  603 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  604 */     if (pixels != null)
/*  605 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/*  606 */     nglTextureImage3DEXT(texture, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   static native void nglTextureImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/*      */ 
/*  610 */   public static void glTextureImage3DEXT(int texture, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  611 */     long function_pointer = caps.glTextureImage3DEXT;
/*  612 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  613 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  614 */     nglTextureImage3DEXTBO(texture, target, level, internalformat, width, height, depth, border, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglTextureImage3DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) {
/*  619 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  620 */     long function_pointer = caps.glTextureSubImage3DEXT;
/*  621 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  622 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  623 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/*  624 */     nglTextureSubImage3DEXT(texture, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, DoubleBuffer pixels) {
/*  627 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  628 */     long function_pointer = caps.glTextureSubImage3DEXT;
/*  629 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  630 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  631 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/*  632 */     nglTextureSubImage3DEXT(texture, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels) {
/*  635 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  636 */     long function_pointer = caps.glTextureSubImage3DEXT;
/*  637 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  638 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  639 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/*  640 */     nglTextureSubImage3DEXT(texture, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels) {
/*  643 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  644 */     long function_pointer = caps.glTextureSubImage3DEXT;
/*  645 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  646 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  647 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/*  648 */     nglTextureSubImage3DEXT(texture, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels) {
/*  651 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  652 */     long function_pointer = caps.glTextureSubImage3DEXT;
/*  653 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  654 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  655 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/*  656 */     nglTextureSubImage3DEXT(texture, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   static native void nglTextureSubImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, long paramLong1, long paramLong2);
/*      */ 
/*  660 */   public static void glTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  661 */     long function_pointer = caps.glTextureSubImage3DEXT;
/*  662 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  663 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  664 */     nglTextureSubImage3DEXTBO(texture, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglTextureSubImage3DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCopyTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height) {
/*  669 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  670 */     long function_pointer = caps.glCopyTextureSubImage3DEXT;
/*  671 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  672 */     nglCopyTextureSubImage3DEXT(texture, target, level, xoffset, yoffset, zoffset, x, y, width, height, function_pointer);
/*      */   }
/*      */   static native void nglCopyTextureSubImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong);
/*      */ 
/*      */   public static void glBindMultiTextureEXT(int texunit, int target, int texture) {
/*  677 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  678 */     long function_pointer = caps.glBindMultiTextureEXT;
/*  679 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  680 */     nglBindMultiTextureEXT(texunit, target, texture, function_pointer);
/*      */   }
/*      */   static native void nglBindMultiTextureEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glMultiTexCoordPointerEXT(int texunit, int size, int stride, DoubleBuffer pointer) {
/*  685 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  686 */     long function_pointer = caps.glMultiTexCoordPointerEXT;
/*  687 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  688 */     GLChecks.ensureArrayVBOdisabled(caps);
/*  689 */     BufferChecks.checkDirect(pointer);
/*  690 */     nglMultiTexCoordPointerEXT(texunit, size, 5130, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   public static void glMultiTexCoordPointerEXT(int texunit, int size, int stride, FloatBuffer pointer) {
/*  693 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  694 */     long function_pointer = caps.glMultiTexCoordPointerEXT;
/*  695 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  696 */     GLChecks.ensureArrayVBOdisabled(caps);
/*  697 */     BufferChecks.checkDirect(pointer);
/*  698 */     nglMultiTexCoordPointerEXT(texunit, size, 5126, stride, MemoryUtil.getAddress(pointer), function_pointer);
/*      */   }
/*      */   static native void nglMultiTexCoordPointerEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*  702 */   public static void glMultiTexCoordPointerEXT(int texunit, int size, int type, int stride, long pointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  703 */     long function_pointer = caps.glMultiTexCoordPointerEXT;
/*  704 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  705 */     GLChecks.ensureArrayVBOenabled(caps);
/*  706 */     nglMultiTexCoordPointerEXTBO(texunit, size, type, stride, pointer_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglMultiTexCoordPointerEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMultiTexEnvfEXT(int texunit, int target, int pname, float param) {
/*  711 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  712 */     long function_pointer = caps.glMultiTexEnvfEXT;
/*  713 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  714 */     nglMultiTexEnvfEXT(texunit, target, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglMultiTexEnvfEXT(int paramInt1, int paramInt2, int paramInt3, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glMultiTexEnvEXT(int texunit, int target, int pname, FloatBuffer params) {
/*  719 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  720 */     long function_pointer = caps.glMultiTexEnvfvEXT;
/*  721 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  722 */     BufferChecks.checkBuffer(params, 4);
/*  723 */     nglMultiTexEnvfvEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglMultiTexEnvfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMultiTexEnviEXT(int texunit, int target, int pname, int param) {
/*  728 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  729 */     long function_pointer = caps.glMultiTexEnviEXT;
/*  730 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  731 */     nglMultiTexEnviEXT(texunit, target, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglMultiTexEnviEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glMultiTexEnvEXT(int texunit, int target, int pname, IntBuffer params) {
/*  736 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  737 */     long function_pointer = caps.glMultiTexEnvivEXT;
/*  738 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  739 */     BufferChecks.checkBuffer(params, 4);
/*  740 */     nglMultiTexEnvivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglMultiTexEnvivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMultiTexGendEXT(int texunit, int coord, int pname, double param) {
/*  745 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  746 */     long function_pointer = caps.glMultiTexGendEXT;
/*  747 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  748 */     nglMultiTexGendEXT(texunit, coord, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglMultiTexGendEXT(int paramInt1, int paramInt2, int paramInt3, double paramDouble, long paramLong);
/*      */ 
/*      */   public static void glMultiTexGenEXT(int texunit, int coord, int pname, DoubleBuffer params) {
/*  753 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  754 */     long function_pointer = caps.glMultiTexGendvEXT;
/*  755 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  756 */     BufferChecks.checkBuffer(params, 4);
/*  757 */     nglMultiTexGendvEXT(texunit, coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglMultiTexGendvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMultiTexGenfEXT(int texunit, int coord, int pname, float param) {
/*  762 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  763 */     long function_pointer = caps.glMultiTexGenfEXT;
/*  764 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  765 */     nglMultiTexGenfEXT(texunit, coord, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglMultiTexGenfEXT(int paramInt1, int paramInt2, int paramInt3, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glMultiTexGenEXT(int texunit, int coord, int pname, FloatBuffer params) {
/*  770 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  771 */     long function_pointer = caps.glMultiTexGenfvEXT;
/*  772 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  773 */     BufferChecks.checkBuffer(params, 4);
/*  774 */     nglMultiTexGenfvEXT(texunit, coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglMultiTexGenfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMultiTexGeniEXT(int texunit, int coord, int pname, int param) {
/*  779 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  780 */     long function_pointer = caps.glMultiTexGeniEXT;
/*  781 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  782 */     nglMultiTexGeniEXT(texunit, coord, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglMultiTexGeniEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glMultiTexGenEXT(int texunit, int coord, int pname, IntBuffer params) {
/*  787 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  788 */     long function_pointer = caps.glMultiTexGenivEXT;
/*  789 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  790 */     BufferChecks.checkBuffer(params, 4);
/*  791 */     nglMultiTexGenivEXT(texunit, coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglMultiTexGenivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetMultiTexEnvEXT(int texunit, int target, int pname, FloatBuffer params) {
/*  796 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  797 */     long function_pointer = caps.glGetMultiTexEnvfvEXT;
/*  798 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  799 */     BufferChecks.checkBuffer(params, 4);
/*  800 */     nglGetMultiTexEnvfvEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetMultiTexEnvfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetMultiTexEnvEXT(int texunit, int target, int pname, IntBuffer params) {
/*  805 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  806 */     long function_pointer = caps.glGetMultiTexEnvivEXT;
/*  807 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  808 */     BufferChecks.checkBuffer(params, 4);
/*  809 */     nglGetMultiTexEnvivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetMultiTexEnvivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetMultiTexGenEXT(int texunit, int coord, int pname, DoubleBuffer params) {
/*  814 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  815 */     long function_pointer = caps.glGetMultiTexGendvEXT;
/*  816 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  817 */     BufferChecks.checkBuffer(params, 4);
/*  818 */     nglGetMultiTexGendvEXT(texunit, coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetMultiTexGendvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetMultiTexGenEXT(int texunit, int coord, int pname, FloatBuffer params) {
/*  823 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  824 */     long function_pointer = caps.glGetMultiTexGenfvEXT;
/*  825 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  826 */     BufferChecks.checkBuffer(params, 4);
/*  827 */     nglGetMultiTexGenfvEXT(texunit, coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetMultiTexGenfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetMultiTexGenEXT(int texunit, int coord, int pname, IntBuffer params) {
/*  832 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  833 */     long function_pointer = caps.glGetMultiTexGenivEXT;
/*  834 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  835 */     BufferChecks.checkBuffer(params, 4);
/*  836 */     nglGetMultiTexGenivEXT(texunit, coord, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetMultiTexGenivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMultiTexParameteriEXT(int texunit, int target, int pname, int param) {
/*  841 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  842 */     long function_pointer = caps.glMultiTexParameteriEXT;
/*  843 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  844 */     nglMultiTexParameteriEXT(texunit, target, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglMultiTexParameteriEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glMultiTexParameterEXT(int texunit, int target, int pname, IntBuffer param) {
/*  849 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  850 */     long function_pointer = caps.glMultiTexParameterivEXT;
/*  851 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  852 */     BufferChecks.checkBuffer(param, 4);
/*  853 */     nglMultiTexParameterivEXT(texunit, target, pname, MemoryUtil.getAddress(param), function_pointer);
/*      */   }
/*      */   static native void nglMultiTexParameterivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMultiTexParameterfEXT(int texunit, int target, int pname, float param) {
/*  858 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  859 */     long function_pointer = caps.glMultiTexParameterfEXT;
/*  860 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  861 */     nglMultiTexParameterfEXT(texunit, target, pname, param, function_pointer);
/*      */   }
/*      */   static native void nglMultiTexParameterfEXT(int paramInt1, int paramInt2, int paramInt3, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glMultiTexParameterEXT(int texunit, int target, int pname, FloatBuffer param) {
/*  866 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  867 */     long function_pointer = caps.glMultiTexParameterfvEXT;
/*  868 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  869 */     BufferChecks.checkBuffer(param, 4);
/*  870 */     nglMultiTexParameterfvEXT(texunit, target, pname, MemoryUtil.getAddress(param), function_pointer);
/*      */   }
/*      */   static native void nglMultiTexParameterfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int width, int border, int format, int type, ByteBuffer pixels) {
/*  875 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  876 */     long function_pointer = caps.glMultiTexImage1DEXT;
/*  877 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  878 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  879 */     if (pixels != null)
/*  880 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  881 */     nglMultiTexImage1DEXT(texunit, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int width, int border, int format, int type, DoubleBuffer pixels) {
/*  884 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  885 */     long function_pointer = caps.glMultiTexImage1DEXT;
/*  886 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  887 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  888 */     if (pixels != null)
/*  889 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  890 */     nglMultiTexImage1DEXT(texunit, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int width, int border, int format, int type, FloatBuffer pixels) {
/*  893 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  894 */     long function_pointer = caps.glMultiTexImage1DEXT;
/*  895 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  896 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  897 */     if (pixels != null)
/*  898 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  899 */     nglMultiTexImage1DEXT(texunit, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int width, int border, int format, int type, IntBuffer pixels) {
/*  902 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  903 */     long function_pointer = caps.glMultiTexImage1DEXT;
/*  904 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  905 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  906 */     if (pixels != null)
/*  907 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  908 */     nglMultiTexImage1DEXT(texunit, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int width, int border, int format, int type, ShortBuffer pixels) {
/*  911 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  912 */     long function_pointer = caps.glMultiTexImage1DEXT;
/*  913 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  914 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  915 */     if (pixels != null)
/*  916 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage1DStorage(pixels, format, type, width));
/*  917 */     nglMultiTexImage1DEXT(texunit, target, level, internalformat, width, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   static native void nglMultiTexImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*      */ 
/*  921 */   public static void glMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int width, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  922 */     long function_pointer = caps.glMultiTexImage1DEXT;
/*  923 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  924 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  925 */     nglMultiTexImage1DEXTBO(texunit, target, level, internalformat, width, border, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglMultiTexImage1DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels) {
/*  930 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  931 */     long function_pointer = caps.glMultiTexImage2DEXT;
/*  932 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  933 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  934 */     if (pixels != null)
/*  935 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  936 */     nglMultiTexImage2DEXT(texunit, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int width, int height, int border, int format, int type, DoubleBuffer pixels) {
/*  939 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  940 */     long function_pointer = caps.glMultiTexImage2DEXT;
/*  941 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  942 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  943 */     if (pixels != null)
/*  944 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  945 */     nglMultiTexImage2DEXT(texunit, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int width, int height, int border, int format, int type, FloatBuffer pixels) {
/*  948 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  949 */     long function_pointer = caps.glMultiTexImage2DEXT;
/*  950 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  951 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  952 */     if (pixels != null)
/*  953 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  954 */     nglMultiTexImage2DEXT(texunit, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int width, int height, int border, int format, int type, IntBuffer pixels) {
/*  957 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  958 */     long function_pointer = caps.glMultiTexImage2DEXT;
/*  959 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  960 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  961 */     if (pixels != null)
/*  962 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  963 */     nglMultiTexImage2DEXT(texunit, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int width, int height, int border, int format, int type, ShortBuffer pixels) {
/*  966 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  967 */     long function_pointer = caps.glMultiTexImage2DEXT;
/*  968 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  969 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  970 */     if (pixels != null)
/*  971 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height));
/*  972 */     nglMultiTexImage2DEXT(texunit, target, level, internalformat, width, height, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   static native void nglMultiTexImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*      */ 
/*  976 */   public static void glMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int width, int height, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  977 */     long function_pointer = caps.glMultiTexImage2DEXT;
/*  978 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  979 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  980 */     nglMultiTexImage2DEXTBO(texunit, target, level, internalformat, width, height, border, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglMultiTexImage2DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int width, int format, int type, ByteBuffer pixels) {
/*  985 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  986 */     long function_pointer = caps.glMultiTexSubImage1DEXT;
/*  987 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  988 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  989 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/*  990 */     nglMultiTexSubImage1DEXT(texunit, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int width, int format, int type, DoubleBuffer pixels) {
/*  993 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  994 */     long function_pointer = caps.glMultiTexSubImage1DEXT;
/*  995 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  996 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  997 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/*  998 */     nglMultiTexSubImage1DEXT(texunit, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int width, int format, int type, FloatBuffer pixels) {
/* 1001 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1002 */     long function_pointer = caps.glMultiTexSubImage1DEXT;
/* 1003 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1004 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1005 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/* 1006 */     nglMultiTexSubImage1DEXT(texunit, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int width, int format, int type, IntBuffer pixels) {
/* 1009 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1010 */     long function_pointer = caps.glMultiTexSubImage1DEXT;
/* 1011 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1012 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1013 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/* 1014 */     nglMultiTexSubImage1DEXT(texunit, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int width, int format, int type, ShortBuffer pixels) {
/* 1017 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1018 */     long function_pointer = caps.glMultiTexSubImage1DEXT;
/* 1019 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1020 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1021 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
/* 1022 */     nglMultiTexSubImage1DEXT(texunit, target, level, xoffset, width, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   static native void nglMultiTexSubImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*      */ 
/* 1026 */   public static void glMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int width, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1027 */     long function_pointer = caps.glMultiTexSubImage1DEXT;
/* 1028 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1029 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 1030 */     nglMultiTexSubImage1DEXTBO(texunit, target, level, xoffset, width, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglMultiTexSubImage1DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels) {
/* 1035 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1036 */     long function_pointer = caps.glMultiTexSubImage2DEXT;
/* 1037 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1038 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1039 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 1040 */     nglMultiTexSubImage2DEXT(texunit, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, DoubleBuffer pixels) {
/* 1043 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1044 */     long function_pointer = caps.glMultiTexSubImage2DEXT;
/* 1045 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1046 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1047 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 1048 */     nglMultiTexSubImage2DEXT(texunit, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, FloatBuffer pixels) {
/* 1051 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1052 */     long function_pointer = caps.glMultiTexSubImage2DEXT;
/* 1053 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1054 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1055 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 1056 */     nglMultiTexSubImage2DEXT(texunit, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, IntBuffer pixels) {
/* 1059 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1060 */     long function_pointer = caps.glMultiTexSubImage2DEXT;
/* 1061 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1062 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1063 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 1064 */     nglMultiTexSubImage2DEXT(texunit, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ShortBuffer pixels) {
/* 1067 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1068 */     long function_pointer = caps.glMultiTexSubImage2DEXT;
/* 1069 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1070 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1071 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, 1));
/* 1072 */     nglMultiTexSubImage2DEXT(texunit, target, level, xoffset, yoffset, width, height, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   static native void nglMultiTexSubImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*      */ 
/* 1076 */   public static void glMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1077 */     long function_pointer = caps.glMultiTexSubImage2DEXT;
/* 1078 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1079 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 1080 */     nglMultiTexSubImage2DEXTBO(texunit, target, level, xoffset, yoffset, width, height, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglMultiTexSubImage2DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCopyMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int x, int y, int width, int border) {
/* 1085 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1086 */     long function_pointer = caps.glCopyMultiTexImage1DEXT;
/* 1087 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1088 */     nglCopyMultiTexImage1DEXT(texunit, target, level, internalformat, x, y, width, border, function_pointer);
/*      */   }
/*      */   static native void nglCopyMultiTexImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong);
/*      */ 
/*      */   public static void glCopyMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int x, int y, int width, int height, int border) {
/* 1093 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1094 */     long function_pointer = caps.glCopyMultiTexImage2DEXT;
/* 1095 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1096 */     nglCopyMultiTexImage2DEXT(texunit, target, level, internalformat, x, y, width, height, border, function_pointer);
/*      */   }
/*      */   static native void nglCopyMultiTexImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong);
/*      */ 
/*      */   public static void glCopyMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int x, int y, int width) {
/* 1101 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1102 */     long function_pointer = caps.glCopyMultiTexSubImage1DEXT;
/* 1103 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1104 */     nglCopyMultiTexSubImage1DEXT(texunit, target, level, xoffset, x, y, width, function_pointer);
/*      */   }
/*      */   static native void nglCopyMultiTexSubImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong);
/*      */ 
/*      */   public static void glCopyMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
/* 1109 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1110 */     long function_pointer = caps.glCopyMultiTexSubImage2DEXT;
/* 1111 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1112 */     nglCopyMultiTexSubImage2DEXT(texunit, target, level, xoffset, yoffset, x, y, width, height, function_pointer);
/*      */   }
/*      */   static native void nglCopyMultiTexSubImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong);
/*      */ 
/*      */   public static void glGetMultiTexImageEXT(int texunit, int target, int level, int format, int type, ByteBuffer pixels) {
/* 1117 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1118 */     long function_pointer = caps.glGetMultiTexImageEXT;
/* 1119 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1120 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1121 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1122 */     nglGetMultiTexImageEXT(texunit, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glGetMultiTexImageEXT(int texunit, int target, int level, int format, int type, DoubleBuffer pixels) {
/* 1125 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1126 */     long function_pointer = caps.glGetMultiTexImageEXT;
/* 1127 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1128 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1129 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1130 */     nglGetMultiTexImageEXT(texunit, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glGetMultiTexImageEXT(int texunit, int target, int level, int format, int type, FloatBuffer pixels) {
/* 1133 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1134 */     long function_pointer = caps.glGetMultiTexImageEXT;
/* 1135 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1136 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1137 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1138 */     nglGetMultiTexImageEXT(texunit, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glGetMultiTexImageEXT(int texunit, int target, int level, int format, int type, IntBuffer pixels) {
/* 1141 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1142 */     long function_pointer = caps.glGetMultiTexImageEXT;
/* 1143 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1144 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1145 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1146 */     nglGetMultiTexImageEXT(texunit, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glGetMultiTexImageEXT(int texunit, int target, int level, int format, int type, ShortBuffer pixels) {
/* 1149 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1150 */     long function_pointer = caps.glGetMultiTexImageEXT;
/* 1151 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1152 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1153 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
/* 1154 */     nglGetMultiTexImageEXT(texunit, target, level, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   static native void nglGetMultiTexImageEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/* 1158 */   public static void glGetMultiTexImageEXT(int texunit, int target, int level, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1159 */     long function_pointer = caps.glGetMultiTexImageEXT;
/* 1160 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1161 */     GLChecks.ensurePackPBOenabled(caps);
/* 1162 */     nglGetMultiTexImageEXTBO(texunit, target, level, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetMultiTexImageEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetMultiTexParameterEXT(int texunit, int target, int pname, FloatBuffer params) {
/* 1167 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1168 */     long function_pointer = caps.glGetMultiTexParameterfvEXT;
/* 1169 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1170 */     BufferChecks.checkBuffer(params, 4);
/* 1171 */     nglGetMultiTexParameterfvEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetMultiTexParameterfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static float glGetMultiTexParameterfEXT(int texunit, int target, int pname) {
/* 1177 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1178 */     long function_pointer = caps.glGetMultiTexParameterfvEXT;
/* 1179 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1180 */     FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1181 */     nglGetMultiTexParameterfvEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1182 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetMultiTexParameterEXT(int texunit, int target, int pname, IntBuffer params) {
/* 1186 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1187 */     long function_pointer = caps.glGetMultiTexParameterivEXT;
/* 1188 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1189 */     BufferChecks.checkBuffer(params, 4);
/* 1190 */     nglGetMultiTexParameterivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetMultiTexParameterivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetMultiTexParameteriEXT(int texunit, int target, int pname) {
/* 1196 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1197 */     long function_pointer = caps.glGetMultiTexParameterivEXT;
/* 1198 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1199 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 1200 */     nglGetMultiTexParameterivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1201 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetMultiTexLevelParameterEXT(int texunit, int target, int level, int pname, FloatBuffer params) {
/* 1205 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1206 */     long function_pointer = caps.glGetMultiTexLevelParameterfvEXT;
/* 1207 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1208 */     BufferChecks.checkBuffer(params, 4);
/* 1209 */     nglGetMultiTexLevelParameterfvEXT(texunit, target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetMultiTexLevelParameterfvEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static float glGetMultiTexLevelParameterfEXT(int texunit, int target, int level, int pname) {
/* 1215 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1216 */     long function_pointer = caps.glGetMultiTexLevelParameterfvEXT;
/* 1217 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1218 */     FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1219 */     nglGetMultiTexLevelParameterfvEXT(texunit, target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1220 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetMultiTexLevelParameterEXT(int texunit, int target, int level, int pname, IntBuffer params) {
/* 1224 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1225 */     long function_pointer = caps.glGetMultiTexLevelParameterivEXT;
/* 1226 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1227 */     BufferChecks.checkBuffer(params, 4);
/* 1228 */     nglGetMultiTexLevelParameterivEXT(texunit, target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetMultiTexLevelParameterivEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetMultiTexLevelParameteriEXT(int texunit, int target, int level, int pname) {
/* 1234 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1235 */     long function_pointer = caps.glGetMultiTexLevelParameterivEXT;
/* 1236 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1237 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 1238 */     nglGetMultiTexLevelParameterivEXT(texunit, target, level, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1239 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glMultiTexImage3DEXT(int texunit, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels) {
/* 1243 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1244 */     long function_pointer = caps.glMultiTexImage3DEXT;
/* 1245 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1246 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1247 */     if (pixels != null)
/* 1248 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 1249 */     nglMultiTexImage3DEXT(texunit, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexImage3DEXT(int texunit, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, DoubleBuffer pixels) {
/* 1252 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1253 */     long function_pointer = caps.glMultiTexImage3DEXT;
/* 1254 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1255 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1256 */     if (pixels != null)
/* 1257 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 1258 */     nglMultiTexImage3DEXT(texunit, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexImage3DEXT(int texunit, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, FloatBuffer pixels) {
/* 1261 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1262 */     long function_pointer = caps.glMultiTexImage3DEXT;
/* 1263 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1264 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1265 */     if (pixels != null)
/* 1266 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 1267 */     nglMultiTexImage3DEXT(texunit, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexImage3DEXT(int texunit, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, IntBuffer pixels) {
/* 1270 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1271 */     long function_pointer = caps.glMultiTexImage3DEXT;
/* 1272 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1273 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1274 */     if (pixels != null)
/* 1275 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 1276 */     nglMultiTexImage3DEXT(texunit, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexImage3DEXT(int texunit, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, ShortBuffer pixels) {
/* 1279 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1280 */     long function_pointer = caps.glMultiTexImage3DEXT;
/* 1281 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1282 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1283 */     if (pixels != null)
/* 1284 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 1285 */     nglMultiTexImage3DEXT(texunit, target, level, internalformat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*      */   }
/*      */   static native void nglMultiTexImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/*      */ 
/* 1289 */   public static void glMultiTexImage3DEXT(int texunit, int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1290 */     long function_pointer = caps.glMultiTexImage3DEXT;
/* 1291 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1292 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 1293 */     nglMultiTexImage3DEXTBO(texunit, target, level, internalformat, width, height, depth, border, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglMultiTexImage3DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) {
/* 1298 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1299 */     long function_pointer = caps.glMultiTexSubImage3DEXT;
/* 1300 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1301 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1302 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 1303 */     nglMultiTexSubImage3DEXT(texunit, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, DoubleBuffer pixels) {
/* 1306 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1307 */     long function_pointer = caps.glMultiTexSubImage3DEXT;
/* 1308 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1309 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1310 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 1311 */     nglMultiTexSubImage3DEXT(texunit, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels) {
/* 1314 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1315 */     long function_pointer = caps.glMultiTexSubImage3DEXT;
/* 1316 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1317 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1318 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 1319 */     nglMultiTexSubImage3DEXT(texunit, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels) {
/* 1322 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1323 */     long function_pointer = caps.glMultiTexSubImage3DEXT;
/* 1324 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1325 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1326 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 1327 */     nglMultiTexSubImage3DEXT(texunit, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   public static void glMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels) {
/* 1330 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1331 */     long function_pointer = caps.glMultiTexSubImage3DEXT;
/* 1332 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1333 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1334 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 1335 */     nglMultiTexSubImage3DEXT(texunit, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*      */   }
/*      */   static native void nglMultiTexSubImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, long paramLong1, long paramLong2);
/*      */ 
/* 1339 */   public static void glMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1340 */     long function_pointer = caps.glMultiTexSubImage3DEXT;
/* 1341 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1342 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 1343 */     nglMultiTexSubImage3DEXTBO(texunit, target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglMultiTexSubImage3DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCopyMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height) {
/* 1348 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1349 */     long function_pointer = caps.glCopyMultiTexSubImage3DEXT;
/* 1350 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1351 */     nglCopyMultiTexSubImage3DEXT(texunit, target, level, xoffset, yoffset, zoffset, x, y, width, height, function_pointer);
/*      */   }
/*      */   static native void nglCopyMultiTexSubImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong);
/*      */ 
/*      */   public static void glEnableClientStateIndexedEXT(int array, int index) {
/* 1356 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1357 */     long function_pointer = caps.glEnableClientStateIndexedEXT;
/* 1358 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1359 */     nglEnableClientStateIndexedEXT(array, index, function_pointer);
/*      */   }
/*      */   static native void nglEnableClientStateIndexedEXT(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glDisableClientStateIndexedEXT(int array, int index) {
/* 1364 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1365 */     long function_pointer = caps.glDisableClientStateIndexedEXT;
/* 1366 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1367 */     nglDisableClientStateIndexedEXT(array, index, function_pointer);
/*      */   }
/*      */   static native void nglDisableClientStateIndexedEXT(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glEnableClientStateiEXT(int array, int index) {
/* 1372 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1373 */     long function_pointer = caps.glEnableClientStateiEXT;
/* 1374 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1375 */     nglEnableClientStateiEXT(array, index, function_pointer);
/*      */   }
/*      */   static native void nglEnableClientStateiEXT(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glDisableClientStateiEXT(int array, int index) {
/* 1380 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1381 */     long function_pointer = caps.glDisableClientStateiEXT;
/* 1382 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1383 */     nglDisableClientStateiEXT(array, index, function_pointer);
/*      */   }
/*      */   static native void nglDisableClientStateiEXT(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glGetFloatIndexedEXT(int pname, int index, FloatBuffer params) {
/* 1388 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1389 */     long function_pointer = caps.glGetFloatIndexedvEXT;
/* 1390 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1391 */     BufferChecks.checkBuffer(params, 16);
/* 1392 */     nglGetFloatIndexedvEXT(pname, index, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetFloatIndexedvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static float glGetFloatIndexedEXT(int pname, int index) {
/* 1398 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1399 */     long function_pointer = caps.glGetFloatIndexedvEXT;
/* 1400 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1401 */     FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1402 */     nglGetFloatIndexedvEXT(pname, index, MemoryUtil.getAddress(params), function_pointer);
/* 1403 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetDoubleIndexedEXT(int pname, int index, DoubleBuffer params) {
/* 1407 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1408 */     long function_pointer = caps.glGetDoubleIndexedvEXT;
/* 1409 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1410 */     BufferChecks.checkBuffer(params, 16);
/* 1411 */     nglGetDoubleIndexedvEXT(pname, index, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetDoubleIndexedvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static double glGetDoubleIndexedEXT(int pname, int index) {
/* 1417 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1418 */     long function_pointer = caps.glGetDoubleIndexedvEXT;
/* 1419 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1420 */     DoubleBuffer params = APIUtil.getBufferDouble(caps);
/* 1421 */     nglGetDoubleIndexedvEXT(pname, index, MemoryUtil.getAddress(params), function_pointer);
/* 1422 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static ByteBuffer glGetPointerIndexedEXT(int pname, int index, long result_size) {
/* 1426 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1427 */     long function_pointer = caps.glGetPointerIndexedvEXT;
/* 1428 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1429 */     ByteBuffer __result = nglGetPointerIndexedvEXT(pname, index, result_size, function_pointer);
/* 1430 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*      */   }
/*      */   static native ByteBuffer nglGetPointerIndexedvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetFloatEXT(int pname, int index, FloatBuffer params) {
/* 1435 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1436 */     long function_pointer = caps.glGetFloati_vEXT;
/* 1437 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1438 */     BufferChecks.checkBuffer(params, 16);
/* 1439 */     nglGetFloati_vEXT(pname, index, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetFloati_vEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static float glGetFloatEXT(int pname, int index) {
/* 1445 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1446 */     long function_pointer = caps.glGetFloati_vEXT;
/* 1447 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1448 */     FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 1449 */     nglGetFloati_vEXT(pname, index, MemoryUtil.getAddress(params), function_pointer);
/* 1450 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetDoubleEXT(int pname, int index, DoubleBuffer params) {
/* 1454 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1455 */     long function_pointer = caps.glGetDoublei_vEXT;
/* 1456 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1457 */     BufferChecks.checkBuffer(params, 16);
/* 1458 */     nglGetDoublei_vEXT(pname, index, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetDoublei_vEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static double glGetDoubleEXT(int pname, int index) {
/* 1464 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1465 */     long function_pointer = caps.glGetDoublei_vEXT;
/* 1466 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1467 */     DoubleBuffer params = APIUtil.getBufferDouble(caps);
/* 1468 */     nglGetDoublei_vEXT(pname, index, MemoryUtil.getAddress(params), function_pointer);
/* 1469 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static ByteBuffer glGetPointerEXT(int pname, int index, long result_size) {
/* 1473 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1474 */     long function_pointer = caps.glGetPointeri_vEXT;
/* 1475 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1476 */     ByteBuffer __result = nglGetPointeri_vEXT(pname, index, result_size, function_pointer);
/* 1477 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*      */   }
/*      */   static native ByteBuffer nglGetPointeri_vEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glEnableIndexedEXT(int cap, int index) {
/* 1482 */     EXTDrawBuffers2.glEnableIndexedEXT(cap, index);
/*      */   }
/*      */ 
/*      */   public static void glDisableIndexedEXT(int cap, int index) {
/* 1486 */     EXTDrawBuffers2.glDisableIndexedEXT(cap, index);
/*      */   }
/*      */ 
/*      */   public static boolean glIsEnabledIndexedEXT(int cap, int index) {
/* 1490 */     return EXTDrawBuffers2.glIsEnabledIndexedEXT(cap, index);
/*      */   }
/*      */ 
/*      */   public static void glGetIntegerIndexedEXT(int pname, int index, IntBuffer params) {
/* 1494 */     EXTDrawBuffers2.glGetIntegerIndexedEXT(pname, index, params);
/*      */   }
/*      */ 
/*      */   public static int glGetIntegerIndexedEXT(int pname, int index)
/*      */   {
/* 1499 */     return EXTDrawBuffers2.glGetIntegerIndexedEXT(pname, index);
/*      */   }
/*      */ 
/*      */   public static void glGetBooleanIndexedEXT(int pname, int index, ByteBuffer params) {
/* 1503 */     EXTDrawBuffers2.glGetBooleanIndexedEXT(pname, index, params);
/*      */   }
/*      */ 
/*      */   public static boolean glGetBooleanIndexedEXT(int pname, int index)
/*      */   {
/* 1508 */     return EXTDrawBuffers2.glGetBooleanIndexedEXT(pname, index);
/*      */   }
/*      */ 
/*      */   public static void glNamedProgramStringEXT(int program, int target, int format, ByteBuffer string) {
/* 1512 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1513 */     long function_pointer = caps.glNamedProgramStringEXT;
/* 1514 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1515 */     BufferChecks.checkDirect(string);
/* 1516 */     nglNamedProgramStringEXT(program, target, format, string.remaining(), MemoryUtil.getAddress(string), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglNamedProgramStringEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glNamedProgramStringEXT(int program, int target, int format, CharSequence string) {
/* 1522 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1523 */     long function_pointer = caps.glNamedProgramStringEXT;
/* 1524 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1525 */     nglNamedProgramStringEXT(program, target, format, string.length(), APIUtil.getBuffer(caps, string), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glNamedProgramLocalParameter4dEXT(int program, int target, int index, double x, double y, double z, double w) {
/* 1529 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1530 */     long function_pointer = caps.glNamedProgramLocalParameter4dEXT;
/* 1531 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1532 */     nglNamedProgramLocalParameter4dEXT(program, target, index, x, y, z, w, function_pointer);
/*      */   }
/*      */   static native void nglNamedProgramLocalParameter4dEXT(int paramInt1, int paramInt2, int paramInt3, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*      */ 
/*      */   public static void glNamedProgramLocalParameter4EXT(int program, int target, int index, DoubleBuffer params) {
/* 1537 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1538 */     long function_pointer = caps.glNamedProgramLocalParameter4dvEXT;
/* 1539 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1540 */     BufferChecks.checkBuffer(params, 4);
/* 1541 */     nglNamedProgramLocalParameter4dvEXT(program, target, index, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglNamedProgramLocalParameter4dvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glNamedProgramLocalParameter4fEXT(int program, int target, int index, float x, float y, float z, float w) {
/* 1546 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1547 */     long function_pointer = caps.glNamedProgramLocalParameter4fEXT;
/* 1548 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1549 */     nglNamedProgramLocalParameter4fEXT(program, target, index, x, y, z, w, function_pointer);
/*      */   }
/*      */   static native void nglNamedProgramLocalParameter4fEXT(int paramInt1, int paramInt2, int paramInt3, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*      */ 
/*      */   public static void glNamedProgramLocalParameter4EXT(int program, int target, int index, FloatBuffer params) {
/* 1554 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1555 */     long function_pointer = caps.glNamedProgramLocalParameter4fvEXT;
/* 1556 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1557 */     BufferChecks.checkBuffer(params, 4);
/* 1558 */     nglNamedProgramLocalParameter4fvEXT(program, target, index, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglNamedProgramLocalParameter4fvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetNamedProgramLocalParameterEXT(int program, int target, int index, DoubleBuffer params) {
/* 1563 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1564 */     long function_pointer = caps.glGetNamedProgramLocalParameterdvEXT;
/* 1565 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1566 */     BufferChecks.checkBuffer(params, 4);
/* 1567 */     nglGetNamedProgramLocalParameterdvEXT(program, target, index, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetNamedProgramLocalParameterdvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetNamedProgramLocalParameterEXT(int program, int target, int index, FloatBuffer params) {
/* 1572 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1573 */     long function_pointer = caps.glGetNamedProgramLocalParameterfvEXT;
/* 1574 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1575 */     BufferChecks.checkBuffer(params, 4);
/* 1576 */     nglGetNamedProgramLocalParameterfvEXT(program, target, index, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetNamedProgramLocalParameterfvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetNamedProgramEXT(int program, int target, int pname, IntBuffer params) {
/* 1581 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1582 */     long function_pointer = caps.glGetNamedProgramivEXT;
/* 1583 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1584 */     BufferChecks.checkBuffer(params, 4);
/* 1585 */     nglGetNamedProgramivEXT(program, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetNamedProgramivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetNamedProgramEXT(int program, int target, int pname) {
/* 1591 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1592 */     long function_pointer = caps.glGetNamedProgramivEXT;
/* 1593 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1594 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 1595 */     nglGetNamedProgramivEXT(program, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 1596 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetNamedProgramStringEXT(int program, int target, int pname, ByteBuffer string) {
/* 1600 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1601 */     long function_pointer = caps.glGetNamedProgramStringEXT;
/* 1602 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1603 */     BufferChecks.checkDirect(string);
/* 1604 */     nglGetNamedProgramStringEXT(program, target, pname, MemoryUtil.getAddress(string), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetNamedProgramStringEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static String glGetNamedProgramStringEXT(int program, int target, int pname) {
/* 1610 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1611 */     long function_pointer = caps.glGetNamedProgramStringEXT;
/* 1612 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1613 */     int programLength = glGetNamedProgramEXT(program, target, 34343);
/* 1614 */     ByteBuffer paramString = APIUtil.getBufferByte(caps, programLength);
/* 1615 */     nglGetNamedProgramStringEXT(program, target, pname, MemoryUtil.getAddress(paramString), function_pointer);
/* 1616 */     paramString.limit(programLength);
/* 1617 */     return APIUtil.getString(caps, paramString);
/*      */   }
/*      */ 
/*      */   public static void glCompressedTextureImage3DEXT(int texture, int target, int level, int internalformat, int width, int height, int depth, int border, ByteBuffer data) {
/* 1621 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1622 */     long function_pointer = caps.glCompressedTextureImage3DEXT;
/* 1623 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1624 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1625 */     BufferChecks.checkDirect(data);
/* 1626 */     nglCompressedTextureImage3DEXT(texture, target, level, internalformat, width, height, depth, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglCompressedTextureImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*      */ 
/* 1630 */   public static void glCompressedTextureImage3DEXT(int texture, int target, int level, int internalformat, int width, int height, int depth, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1631 */     long function_pointer = caps.glCompressedTextureImage3DEXT;
/* 1632 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1633 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 1634 */     nglCompressedTextureImage3DEXTBO(texture, target, level, internalformat, width, height, depth, border, data_imageSize, data_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglCompressedTextureImage3DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCompressedTextureImage2DEXT(int texture, int target, int level, int internalformat, int width, int height, int border, ByteBuffer data) {
/* 1639 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1640 */     long function_pointer = caps.glCompressedTextureImage2DEXT;
/* 1641 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1642 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1643 */     BufferChecks.checkDirect(data);
/* 1644 */     nglCompressedTextureImage2DEXT(texture, target, level, internalformat, width, height, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglCompressedTextureImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*      */ 
/* 1648 */   public static void glCompressedTextureImage2DEXT(int texture, int target, int level, int internalformat, int width, int height, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1649 */     long function_pointer = caps.glCompressedTextureImage2DEXT;
/* 1650 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1651 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 1652 */     nglCompressedTextureImage2DEXTBO(texture, target, level, internalformat, width, height, border, data_imageSize, data_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglCompressedTextureImage2DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCompressedTextureImage1DEXT(int texture, int target, int level, int internalformat, int width, int border, ByteBuffer data) {
/* 1657 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1658 */     long function_pointer = caps.glCompressedTextureImage1DEXT;
/* 1659 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1660 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1661 */     BufferChecks.checkDirect(data);
/* 1662 */     nglCompressedTextureImage1DEXT(texture, target, level, internalformat, width, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglCompressedTextureImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*      */ 
/* 1666 */   public static void glCompressedTextureImage1DEXT(int texture, int target, int level, int internalformat, int width, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1667 */     long function_pointer = caps.glCompressedTextureImage1DEXT;
/* 1668 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1669 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 1670 */     nglCompressedTextureImage1DEXTBO(texture, target, level, internalformat, width, border, data_imageSize, data_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglCompressedTextureImage1DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCompressedTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, ByteBuffer data) {
/* 1675 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1676 */     long function_pointer = caps.glCompressedTextureSubImage3DEXT;
/* 1677 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1678 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1679 */     BufferChecks.checkDirect(data);
/* 1680 */     nglCompressedTextureSubImage3DEXT(texture, target, level, xoffset, yoffset, zoffset, width, height, depth, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglCompressedTextureSubImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, long paramLong1, long paramLong2);
/*      */ 
/* 1684 */   public static void glCompressedTextureSubImage3DEXT(int texture, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1685 */     long function_pointer = caps.glCompressedTextureSubImage3DEXT;
/* 1686 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1687 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 1688 */     nglCompressedTextureSubImage3DEXTBO(texture, target, level, xoffset, yoffset, zoffset, width, height, depth, format, data_imageSize, data_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglCompressedTextureSubImage3DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCompressedTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int width, int height, int format, ByteBuffer data) {
/* 1693 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1694 */     long function_pointer = caps.glCompressedTextureSubImage2DEXT;
/* 1695 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1696 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1697 */     BufferChecks.checkDirect(data);
/* 1698 */     nglCompressedTextureSubImage2DEXT(texture, target, level, xoffset, yoffset, width, height, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglCompressedTextureSubImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*      */ 
/* 1702 */   public static void glCompressedTextureSubImage2DEXT(int texture, int target, int level, int xoffset, int yoffset, int width, int height, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1703 */     long function_pointer = caps.glCompressedTextureSubImage2DEXT;
/* 1704 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1705 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 1706 */     nglCompressedTextureSubImage2DEXTBO(texture, target, level, xoffset, yoffset, width, height, format, data_imageSize, data_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglCompressedTextureSubImage2DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCompressedTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int width, int format, ByteBuffer data) {
/* 1711 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1712 */     long function_pointer = caps.glCompressedTextureSubImage1DEXT;
/* 1713 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1714 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1715 */     BufferChecks.checkDirect(data);
/* 1716 */     nglCompressedTextureSubImage1DEXT(texture, target, level, xoffset, width, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglCompressedTextureSubImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*      */ 
/* 1720 */   public static void glCompressedTextureSubImage1DEXT(int texture, int target, int level, int xoffset, int width, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1721 */     long function_pointer = caps.glCompressedTextureSubImage1DEXT;
/* 1722 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1723 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 1724 */     nglCompressedTextureSubImage1DEXTBO(texture, target, level, xoffset, width, format, data_imageSize, data_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglCompressedTextureSubImage1DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetCompressedTextureImageEXT(int texture, int target, int level, ByteBuffer img) {
/* 1729 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1730 */     long function_pointer = caps.glGetCompressedTextureImageEXT;
/* 1731 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1732 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1733 */     BufferChecks.checkDirect(img);
/* 1734 */     nglGetCompressedTextureImageEXT(texture, target, level, MemoryUtil.getAddress(img), function_pointer);
/*      */   }
/*      */   public static void glGetCompressedTextureImageEXT(int texture, int target, int level, IntBuffer img) {
/* 1737 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1738 */     long function_pointer = caps.glGetCompressedTextureImageEXT;
/* 1739 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1740 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1741 */     BufferChecks.checkDirect(img);
/* 1742 */     nglGetCompressedTextureImageEXT(texture, target, level, MemoryUtil.getAddress(img), function_pointer);
/*      */   }
/*      */   public static void glGetCompressedTextureImageEXT(int texture, int target, int level, ShortBuffer img) {
/* 1745 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1746 */     long function_pointer = caps.glGetCompressedTextureImageEXT;
/* 1747 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1748 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1749 */     BufferChecks.checkDirect(img);
/* 1750 */     nglGetCompressedTextureImageEXT(texture, target, level, MemoryUtil.getAddress(img), function_pointer);
/*      */   }
/*      */   static native void nglGetCompressedTextureImageEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/* 1754 */   public static void glGetCompressedTextureImageEXT(int texture, int target, int level, long img_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1755 */     long function_pointer = caps.glGetCompressedTextureImageEXT;
/* 1756 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1757 */     GLChecks.ensurePackPBOenabled(caps);
/* 1758 */     nglGetCompressedTextureImageEXTBO(texture, target, level, img_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetCompressedTextureImageEXTBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCompressedMultiTexImage3DEXT(int texunit, int target, int level, int internalformat, int width, int height, int depth, int border, ByteBuffer data) {
/* 1763 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1764 */     long function_pointer = caps.glCompressedMultiTexImage3DEXT;
/* 1765 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1766 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1767 */     BufferChecks.checkDirect(data);
/* 1768 */     nglCompressedMultiTexImage3DEXT(texunit, target, level, internalformat, width, height, depth, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglCompressedMultiTexImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*      */ 
/* 1772 */   public static void glCompressedMultiTexImage3DEXT(int texunit, int target, int level, int internalformat, int width, int height, int depth, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1773 */     long function_pointer = caps.glCompressedMultiTexImage3DEXT;
/* 1774 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1775 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 1776 */     nglCompressedMultiTexImage3DEXTBO(texunit, target, level, internalformat, width, height, depth, border, data_imageSize, data_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglCompressedMultiTexImage3DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCompressedMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int width, int height, int border, ByteBuffer data) {
/* 1781 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1782 */     long function_pointer = caps.glCompressedMultiTexImage2DEXT;
/* 1783 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1784 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1785 */     BufferChecks.checkDirect(data);
/* 1786 */     nglCompressedMultiTexImage2DEXT(texunit, target, level, internalformat, width, height, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglCompressedMultiTexImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*      */ 
/* 1790 */   public static void glCompressedMultiTexImage2DEXT(int texunit, int target, int level, int internalformat, int width, int height, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1791 */     long function_pointer = caps.glCompressedMultiTexImage2DEXT;
/* 1792 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1793 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 1794 */     nglCompressedMultiTexImage2DEXTBO(texunit, target, level, internalformat, width, height, border, data_imageSize, data_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglCompressedMultiTexImage2DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCompressedMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int width, int border, ByteBuffer data) {
/* 1799 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1800 */     long function_pointer = caps.glCompressedMultiTexImage1DEXT;
/* 1801 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1802 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1803 */     BufferChecks.checkDirect(data);
/* 1804 */     nglCompressedMultiTexImage1DEXT(texunit, target, level, internalformat, width, border, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglCompressedMultiTexImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*      */ 
/* 1808 */   public static void glCompressedMultiTexImage1DEXT(int texunit, int target, int level, int internalformat, int width, int border, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1809 */     long function_pointer = caps.glCompressedMultiTexImage1DEXT;
/* 1810 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1811 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 1812 */     nglCompressedMultiTexImage1DEXTBO(texunit, target, level, internalformat, width, border, data_imageSize, data_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglCompressedMultiTexImage1DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCompressedMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, ByteBuffer data) {
/* 1817 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1818 */     long function_pointer = caps.glCompressedMultiTexSubImage3DEXT;
/* 1819 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1820 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1821 */     BufferChecks.checkDirect(data);
/* 1822 */     nglCompressedMultiTexSubImage3DEXT(texunit, target, level, xoffset, yoffset, zoffset, width, height, depth, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglCompressedMultiTexSubImage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, long paramLong1, long paramLong2);
/*      */ 
/* 1826 */   public static void glCompressedMultiTexSubImage3DEXT(int texunit, int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1827 */     long function_pointer = caps.glCompressedMultiTexSubImage3DEXT;
/* 1828 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1829 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 1830 */     nglCompressedMultiTexSubImage3DEXTBO(texunit, target, level, xoffset, yoffset, zoffset, width, height, depth, format, data_imageSize, data_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglCompressedMultiTexSubImage3DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCompressedMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int width, int height, int format, ByteBuffer data) {
/* 1835 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1836 */     long function_pointer = caps.glCompressedMultiTexSubImage2DEXT;
/* 1837 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1838 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1839 */     BufferChecks.checkDirect(data);
/* 1840 */     nglCompressedMultiTexSubImage2DEXT(texunit, target, level, xoffset, yoffset, width, height, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglCompressedMultiTexSubImage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*      */ 
/* 1844 */   public static void glCompressedMultiTexSubImage2DEXT(int texunit, int target, int level, int xoffset, int yoffset, int width, int height, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1845 */     long function_pointer = caps.glCompressedMultiTexSubImage2DEXT;
/* 1846 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1847 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 1848 */     nglCompressedMultiTexSubImage2DEXTBO(texunit, target, level, xoffset, yoffset, width, height, format, data_imageSize, data_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglCompressedMultiTexSubImage2DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCompressedMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int width, int format, ByteBuffer data) {
/* 1853 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1854 */     long function_pointer = caps.glCompressedMultiTexSubImage1DEXT;
/* 1855 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1856 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 1857 */     BufferChecks.checkDirect(data);
/* 1858 */     nglCompressedMultiTexSubImage1DEXT(texunit, target, level, xoffset, width, format, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglCompressedMultiTexSubImage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*      */ 
/* 1862 */   public static void glCompressedMultiTexSubImage1DEXT(int texunit, int target, int level, int xoffset, int width, int format, int data_imageSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1863 */     long function_pointer = caps.glCompressedMultiTexSubImage1DEXT;
/* 1864 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1865 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 1866 */     nglCompressedMultiTexSubImage1DEXTBO(texunit, target, level, xoffset, width, format, data_imageSize, data_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglCompressedMultiTexSubImage1DEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetCompressedMultiTexImageEXT(int texunit, int target, int level, ByteBuffer img) {
/* 1871 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1872 */     long function_pointer = caps.glGetCompressedMultiTexImageEXT;
/* 1873 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1874 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1875 */     BufferChecks.checkDirect(img);
/* 1876 */     nglGetCompressedMultiTexImageEXT(texunit, target, level, MemoryUtil.getAddress(img), function_pointer);
/*      */   }
/*      */   public static void glGetCompressedMultiTexImageEXT(int texunit, int target, int level, IntBuffer img) {
/* 1879 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1880 */     long function_pointer = caps.glGetCompressedMultiTexImageEXT;
/* 1881 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1882 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1883 */     BufferChecks.checkDirect(img);
/* 1884 */     nglGetCompressedMultiTexImageEXT(texunit, target, level, MemoryUtil.getAddress(img), function_pointer);
/*      */   }
/*      */   public static void glGetCompressedMultiTexImageEXT(int texunit, int target, int level, ShortBuffer img) {
/* 1887 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1888 */     long function_pointer = caps.glGetCompressedMultiTexImageEXT;
/* 1889 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1890 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1891 */     BufferChecks.checkDirect(img);
/* 1892 */     nglGetCompressedMultiTexImageEXT(texunit, target, level, MemoryUtil.getAddress(img), function_pointer);
/*      */   }
/*      */   static native void nglGetCompressedMultiTexImageEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/* 1896 */   public static void glGetCompressedMultiTexImageEXT(int texunit, int target, int level, long img_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1897 */     long function_pointer = caps.glGetCompressedMultiTexImageEXT;
/* 1898 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1899 */     GLChecks.ensurePackPBOenabled(caps);
/* 1900 */     nglGetCompressedMultiTexImageEXTBO(texunit, target, level, img_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetCompressedMultiTexImageEXTBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMatrixLoadTransposeEXT(int matrixMode, FloatBuffer m) {
/* 1905 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1906 */     long function_pointer = caps.glMatrixLoadTransposefEXT;
/* 1907 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1908 */     BufferChecks.checkBuffer(m, 16);
/* 1909 */     nglMatrixLoadTransposefEXT(matrixMode, MemoryUtil.getAddress(m), function_pointer);
/*      */   }
/*      */   static native void nglMatrixLoadTransposefEXT(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMatrixLoadTransposeEXT(int matrixMode, DoubleBuffer m) {
/* 1914 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1915 */     long function_pointer = caps.glMatrixLoadTransposedEXT;
/* 1916 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1917 */     BufferChecks.checkBuffer(m, 16);
/* 1918 */     nglMatrixLoadTransposedEXT(matrixMode, MemoryUtil.getAddress(m), function_pointer);
/*      */   }
/*      */   static native void nglMatrixLoadTransposedEXT(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMatrixMultTransposeEXT(int matrixMode, FloatBuffer m) {
/* 1923 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1924 */     long function_pointer = caps.glMatrixMultTransposefEXT;
/* 1925 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1926 */     BufferChecks.checkBuffer(m, 16);
/* 1927 */     nglMatrixMultTransposefEXT(matrixMode, MemoryUtil.getAddress(m), function_pointer);
/*      */   }
/*      */   static native void nglMatrixMultTransposefEXT(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMatrixMultTransposeEXT(int matrixMode, DoubleBuffer m) {
/* 1932 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1933 */     long function_pointer = caps.glMatrixMultTransposedEXT;
/* 1934 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1935 */     BufferChecks.checkBuffer(m, 16);
/* 1936 */     nglMatrixMultTransposedEXT(matrixMode, MemoryUtil.getAddress(m), function_pointer);
/*      */   }
/*      */   static native void nglMatrixMultTransposedEXT(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glNamedBufferDataEXT(int buffer, long data_size, int usage) {
/* 1941 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1942 */     long function_pointer = caps.glNamedBufferDataEXT;
/* 1943 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1944 */     nglNamedBufferDataEXT(buffer, data_size, 0L, usage, function_pointer);
/*      */   }
/*      */   public static void glNamedBufferDataEXT(int buffer, ByteBuffer data, int usage) {
/* 1947 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1948 */     long function_pointer = caps.glNamedBufferDataEXT;
/* 1949 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1950 */     BufferChecks.checkDirect(data);
/* 1951 */     nglNamedBufferDataEXT(buffer, data.remaining(), MemoryUtil.getAddress(data), usage, function_pointer);
/*      */   }
/*      */   public static void glNamedBufferDataEXT(int buffer, DoubleBuffer data, int usage) {
/* 1954 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1955 */     long function_pointer = caps.glNamedBufferDataEXT;
/* 1956 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1957 */     BufferChecks.checkDirect(data);
/* 1958 */     nglNamedBufferDataEXT(buffer, data.remaining() << 3, MemoryUtil.getAddress(data), usage, function_pointer);
/*      */   }
/*      */   public static void glNamedBufferDataEXT(int buffer, FloatBuffer data, int usage) {
/* 1961 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1962 */     long function_pointer = caps.glNamedBufferDataEXT;
/* 1963 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1964 */     BufferChecks.checkDirect(data);
/* 1965 */     nglNamedBufferDataEXT(buffer, data.remaining() << 2, MemoryUtil.getAddress(data), usage, function_pointer);
/*      */   }
/*      */   public static void glNamedBufferDataEXT(int buffer, IntBuffer data, int usage) {
/* 1968 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1969 */     long function_pointer = caps.glNamedBufferDataEXT;
/* 1970 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1971 */     BufferChecks.checkDirect(data);
/* 1972 */     nglNamedBufferDataEXT(buffer, data.remaining() << 2, MemoryUtil.getAddress(data), usage, function_pointer);
/*      */   }
/*      */   public static void glNamedBufferDataEXT(int buffer, ShortBuffer data, int usage) {
/* 1975 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1976 */     long function_pointer = caps.glNamedBufferDataEXT;
/* 1977 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1978 */     BufferChecks.checkDirect(data);
/* 1979 */     nglNamedBufferDataEXT(buffer, data.remaining() << 1, MemoryUtil.getAddress(data), usage, function_pointer);
/*      */   }
/*      */   static native void nglNamedBufferDataEXT(int paramInt1, long paramLong1, long paramLong2, int paramInt2, long paramLong3);
/*      */ 
/*      */   public static void glNamedBufferSubDataEXT(int buffer, long offset, ByteBuffer data) {
/* 1984 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1985 */     long function_pointer = caps.glNamedBufferSubDataEXT;
/* 1986 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1987 */     BufferChecks.checkDirect(data);
/* 1988 */     nglNamedBufferSubDataEXT(buffer, offset, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glNamedBufferSubDataEXT(int buffer, long offset, DoubleBuffer data) {
/* 1991 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1992 */     long function_pointer = caps.glNamedBufferSubDataEXT;
/* 1993 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1994 */     BufferChecks.checkDirect(data);
/* 1995 */     nglNamedBufferSubDataEXT(buffer, offset, data.remaining() << 3, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glNamedBufferSubDataEXT(int buffer, long offset, FloatBuffer data) {
/* 1998 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1999 */     long function_pointer = caps.glNamedBufferSubDataEXT;
/* 2000 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2001 */     BufferChecks.checkDirect(data);
/* 2002 */     nglNamedBufferSubDataEXT(buffer, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glNamedBufferSubDataEXT(int buffer, long offset, IntBuffer data) {
/* 2005 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2006 */     long function_pointer = caps.glNamedBufferSubDataEXT;
/* 2007 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2008 */     BufferChecks.checkDirect(data);
/* 2009 */     nglNamedBufferSubDataEXT(buffer, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glNamedBufferSubDataEXT(int buffer, long offset, ShortBuffer data) {
/* 2012 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2013 */     long function_pointer = caps.glNamedBufferSubDataEXT;
/* 2014 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2015 */     BufferChecks.checkDirect(data);
/* 2016 */     nglNamedBufferSubDataEXT(buffer, offset, data.remaining() << 1, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglNamedBufferSubDataEXT(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*      */ 
/*      */   public static ByteBuffer glMapNamedBufferEXT(int buffer, int access, ByteBuffer old_buffer)
/*      */   {
/* 2044 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2045 */     long function_pointer = caps.glMapNamedBufferEXT;
/* 2046 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2047 */     if (old_buffer != null)
/* 2048 */       BufferChecks.checkDirect(old_buffer);
/* 2049 */     ByteBuffer __result = nglMapNamedBufferEXT(buffer, access, GLChecks.getNamedBufferObjectSize(caps, buffer), old_buffer, function_pointer);
/* 2050 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*      */   }
/*      */ 
/*      */   public static ByteBuffer glMapNamedBufferEXT(int buffer, int access, long length, ByteBuffer old_buffer)
/*      */   {
/* 2076 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2077 */     long function_pointer = caps.glMapNamedBufferEXT;
/* 2078 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2079 */     if (old_buffer != null)
/* 2080 */       BufferChecks.checkDirect(old_buffer);
/* 2081 */     ByteBuffer __result = nglMapNamedBufferEXT(buffer, access, length, old_buffer, function_pointer);
/* 2082 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*      */   }
/*      */   static native ByteBuffer nglMapNamedBufferEXT(int paramInt1, int paramInt2, long paramLong1, ByteBuffer paramByteBuffer, long paramLong2);
/*      */ 
/*      */   public static boolean glUnmapNamedBufferEXT(int buffer) {
/* 2087 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2088 */     long function_pointer = caps.glUnmapNamedBufferEXT;
/* 2089 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2090 */     boolean __result = nglUnmapNamedBufferEXT(buffer, function_pointer);
/* 2091 */     return __result;
/*      */   }
/*      */   static native boolean nglUnmapNamedBufferEXT(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glGetNamedBufferParameterEXT(int buffer, int pname, IntBuffer params) {
/* 2096 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2097 */     long function_pointer = caps.glGetNamedBufferParameterivEXT;
/* 2098 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2099 */     BufferChecks.checkBuffer(params, 4);
/* 2100 */     nglGetNamedBufferParameterivEXT(buffer, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetNamedBufferParameterivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetNamedBufferParameterEXT(int buffer, int pname) {
/* 2106 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2107 */     long function_pointer = caps.glGetNamedBufferParameterivEXT;
/* 2108 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2109 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 2110 */     nglGetNamedBufferParameterivEXT(buffer, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2111 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static ByteBuffer glGetNamedBufferPointerEXT(int buffer, int pname) {
/* 2115 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2116 */     long function_pointer = caps.glGetNamedBufferPointervEXT;
/* 2117 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2118 */     ByteBuffer __result = nglGetNamedBufferPointervEXT(buffer, pname, GLChecks.getNamedBufferObjectSize(caps, buffer), function_pointer);
/* 2119 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*      */   }
/*      */   static native ByteBuffer nglGetNamedBufferPointervEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetNamedBufferSubDataEXT(int buffer, long offset, ByteBuffer data) {
/* 2124 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2125 */     long function_pointer = caps.glGetNamedBufferSubDataEXT;
/* 2126 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2127 */     BufferChecks.checkDirect(data);
/* 2128 */     nglGetNamedBufferSubDataEXT(buffer, offset, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glGetNamedBufferSubDataEXT(int buffer, long offset, DoubleBuffer data) {
/* 2131 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2132 */     long function_pointer = caps.glGetNamedBufferSubDataEXT;
/* 2133 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2134 */     BufferChecks.checkDirect(data);
/* 2135 */     nglGetNamedBufferSubDataEXT(buffer, offset, data.remaining() << 3, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glGetNamedBufferSubDataEXT(int buffer, long offset, FloatBuffer data) {
/* 2138 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2139 */     long function_pointer = caps.glGetNamedBufferSubDataEXT;
/* 2140 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2141 */     BufferChecks.checkDirect(data);
/* 2142 */     nglGetNamedBufferSubDataEXT(buffer, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glGetNamedBufferSubDataEXT(int buffer, long offset, IntBuffer data) {
/* 2145 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2146 */     long function_pointer = caps.glGetNamedBufferSubDataEXT;
/* 2147 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2148 */     BufferChecks.checkDirect(data);
/* 2149 */     nglGetNamedBufferSubDataEXT(buffer, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glGetNamedBufferSubDataEXT(int buffer, long offset, ShortBuffer data) {
/* 2152 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2153 */     long function_pointer = caps.glGetNamedBufferSubDataEXT;
/* 2154 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2155 */     BufferChecks.checkDirect(data);
/* 2156 */     nglGetNamedBufferSubDataEXT(buffer, offset, data.remaining() << 1, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglGetNamedBufferSubDataEXT(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*      */ 
/*      */   public static void glProgramUniform1fEXT(int program, int location, float v0) {
/* 2161 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2162 */     long function_pointer = caps.glProgramUniform1fEXT;
/* 2163 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2164 */     nglProgramUniform1fEXT(program, location, v0, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform1fEXT(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform2fEXT(int program, int location, float v0, float v1) {
/* 2169 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2170 */     long function_pointer = caps.glProgramUniform2fEXT;
/* 2171 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2172 */     nglProgramUniform2fEXT(program, location, v0, v1, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform2fEXT(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform3fEXT(int program, int location, float v0, float v1, float v2) {
/* 2177 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2178 */     long function_pointer = caps.glProgramUniform3fEXT;
/* 2179 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2180 */     nglProgramUniform3fEXT(program, location, v0, v1, v2, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform3fEXT(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform4fEXT(int program, int location, float v0, float v1, float v2, float v3) {
/* 2185 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2186 */     long function_pointer = caps.glProgramUniform4fEXT;
/* 2187 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2188 */     nglProgramUniform4fEXT(program, location, v0, v1, v2, v3, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform4fEXT(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform1iEXT(int program, int location, int v0) {
/* 2193 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2194 */     long function_pointer = caps.glProgramUniform1iEXT;
/* 2195 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2196 */     nglProgramUniform1iEXT(program, location, v0, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform1iEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform2iEXT(int program, int location, int v0, int v1) {
/* 2201 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2202 */     long function_pointer = caps.glProgramUniform2iEXT;
/* 2203 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2204 */     nglProgramUniform2iEXT(program, location, v0, v1, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform2iEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform3iEXT(int program, int location, int v0, int v1, int v2) {
/* 2209 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2210 */     long function_pointer = caps.glProgramUniform3iEXT;
/* 2211 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2212 */     nglProgramUniform3iEXT(program, location, v0, v1, v2, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform3iEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform4iEXT(int program, int location, int v0, int v1, int v2, int v3) {
/* 2217 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2218 */     long function_pointer = caps.glProgramUniform4iEXT;
/* 2219 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2220 */     nglProgramUniform4iEXT(program, location, v0, v1, v2, v3, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform4iEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform1EXT(int program, int location, FloatBuffer value) {
/* 2225 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2226 */     long function_pointer = caps.glProgramUniform1fvEXT;
/* 2227 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2228 */     BufferChecks.checkDirect(value);
/* 2229 */     nglProgramUniform1fvEXT(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform1fvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform2EXT(int program, int location, FloatBuffer value) {
/* 2234 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2235 */     long function_pointer = caps.glProgramUniform2fvEXT;
/* 2236 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2237 */     BufferChecks.checkDirect(value);
/* 2238 */     nglProgramUniform2fvEXT(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform2fvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform3EXT(int program, int location, FloatBuffer value) {
/* 2243 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2244 */     long function_pointer = caps.glProgramUniform3fvEXT;
/* 2245 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2246 */     BufferChecks.checkDirect(value);
/* 2247 */     nglProgramUniform3fvEXT(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform3fvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform4EXT(int program, int location, FloatBuffer value) {
/* 2252 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2253 */     long function_pointer = caps.glProgramUniform4fvEXT;
/* 2254 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2255 */     BufferChecks.checkDirect(value);
/* 2256 */     nglProgramUniform4fvEXT(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform4fvEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform1EXT(int program, int location, IntBuffer value) {
/* 2261 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2262 */     long function_pointer = caps.glProgramUniform1ivEXT;
/* 2263 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2264 */     BufferChecks.checkDirect(value);
/* 2265 */     nglProgramUniform1ivEXT(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform1ivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform2EXT(int program, int location, IntBuffer value) {
/* 2270 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2271 */     long function_pointer = caps.glProgramUniform2ivEXT;
/* 2272 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2273 */     BufferChecks.checkDirect(value);
/* 2274 */     nglProgramUniform2ivEXT(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform2ivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform3EXT(int program, int location, IntBuffer value) {
/* 2279 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2280 */     long function_pointer = caps.glProgramUniform3ivEXT;
/* 2281 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2282 */     BufferChecks.checkDirect(value);
/* 2283 */     nglProgramUniform3ivEXT(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform3ivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform4EXT(int program, int location, IntBuffer value) {
/* 2288 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2289 */     long function_pointer = caps.glProgramUniform4ivEXT;
/* 2290 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2291 */     BufferChecks.checkDirect(value);
/* 2292 */     nglProgramUniform4ivEXT(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform4ivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix2EXT(int program, int location, boolean transpose, FloatBuffer value) {
/* 2297 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2298 */     long function_pointer = caps.glProgramUniformMatrix2fvEXT;
/* 2299 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2300 */     BufferChecks.checkDirect(value);
/* 2301 */     nglProgramUniformMatrix2fvEXT(program, location, value.remaining() >> 2, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix2fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix3EXT(int program, int location, boolean transpose, FloatBuffer value) {
/* 2306 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2307 */     long function_pointer = caps.glProgramUniformMatrix3fvEXT;
/* 2308 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2309 */     BufferChecks.checkDirect(value);
/* 2310 */     nglProgramUniformMatrix3fvEXT(program, location, value.remaining() / 9, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix3fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix4EXT(int program, int location, boolean transpose, FloatBuffer value) {
/* 2315 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2316 */     long function_pointer = caps.glProgramUniformMatrix4fvEXT;
/* 2317 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2318 */     BufferChecks.checkDirect(value);
/* 2319 */     nglProgramUniformMatrix4fvEXT(program, location, value.remaining() >> 4, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix4fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix2x3EXT(int program, int location, boolean transpose, FloatBuffer value) {
/* 2324 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2325 */     long function_pointer = caps.glProgramUniformMatrix2x3fvEXT;
/* 2326 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2327 */     BufferChecks.checkDirect(value);
/* 2328 */     nglProgramUniformMatrix2x3fvEXT(program, location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix2x3fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix3x2EXT(int program, int location, boolean transpose, FloatBuffer value) {
/* 2333 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2334 */     long function_pointer = caps.glProgramUniformMatrix3x2fvEXT;
/* 2335 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2336 */     BufferChecks.checkDirect(value);
/* 2337 */     nglProgramUniformMatrix3x2fvEXT(program, location, value.remaining() / 6, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix3x2fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix2x4EXT(int program, int location, boolean transpose, FloatBuffer value) {
/* 2342 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2343 */     long function_pointer = caps.glProgramUniformMatrix2x4fvEXT;
/* 2344 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2345 */     BufferChecks.checkDirect(value);
/* 2346 */     nglProgramUniformMatrix2x4fvEXT(program, location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix2x4fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix4x2EXT(int program, int location, boolean transpose, FloatBuffer value) {
/* 2351 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2352 */     long function_pointer = caps.glProgramUniformMatrix4x2fvEXT;
/* 2353 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2354 */     BufferChecks.checkDirect(value);
/* 2355 */     nglProgramUniformMatrix4x2fvEXT(program, location, value.remaining() >> 3, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix4x2fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix3x4EXT(int program, int location, boolean transpose, FloatBuffer value) {
/* 2360 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2361 */     long function_pointer = caps.glProgramUniformMatrix3x4fvEXT;
/* 2362 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2363 */     BufferChecks.checkDirect(value);
/* 2364 */     nglProgramUniformMatrix3x4fvEXT(program, location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix3x4fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniformMatrix4x3EXT(int program, int location, boolean transpose, FloatBuffer value) {
/* 2369 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2370 */     long function_pointer = caps.glProgramUniformMatrix4x3fvEXT;
/* 2371 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2372 */     BufferChecks.checkDirect(value);
/* 2373 */     nglProgramUniformMatrix4x3fvEXT(program, location, value.remaining() / 12, transpose, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniformMatrix4x3fvEXT(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTextureBufferEXT(int texture, int target, int internalformat, int buffer) {
/* 2378 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2379 */     long function_pointer = caps.glTextureBufferEXT;
/* 2380 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2381 */     nglTextureBufferEXT(texture, target, internalformat, buffer, function_pointer);
/*      */   }
/*      */   static native void nglTextureBufferEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glMultiTexBufferEXT(int texunit, int target, int internalformat, int buffer) {
/* 2386 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2387 */     long function_pointer = caps.glMultiTexBufferEXT;
/* 2388 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2389 */     nglMultiTexBufferEXT(texunit, target, internalformat, buffer, function_pointer);
/*      */   }
/*      */   static native void nglMultiTexBufferEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glTextureParameterIEXT(int texture, int target, int pname, IntBuffer params) {
/* 2394 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2395 */     long function_pointer = caps.glTextureParameterIivEXT;
/* 2396 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2397 */     BufferChecks.checkBuffer(params, 4);
/* 2398 */     nglTextureParameterIivEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglTextureParameterIivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTextureParameterIEXT(int texture, int target, int pname, int param) {
/* 2404 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2405 */     long function_pointer = caps.glTextureParameterIivEXT;
/* 2406 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2407 */     nglTextureParameterIivEXT(texture, target, pname, APIUtil.getInt(caps, param), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glTextureParameterIuEXT(int texture, int target, int pname, IntBuffer params) {
/* 2411 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2412 */     long function_pointer = caps.glTextureParameterIuivEXT;
/* 2413 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2414 */     BufferChecks.checkBuffer(params, 4);
/* 2415 */     nglTextureParameterIuivEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglTextureParameterIuivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glTextureParameterIuEXT(int texture, int target, int pname, int param) {
/* 2421 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2422 */     long function_pointer = caps.glTextureParameterIuivEXT;
/* 2423 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2424 */     nglTextureParameterIuivEXT(texture, target, pname, APIUtil.getInt(caps, param), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glGetTextureParameterIEXT(int texture, int target, int pname, IntBuffer params) {
/* 2428 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2429 */     long function_pointer = caps.glGetTextureParameterIivEXT;
/* 2430 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2431 */     BufferChecks.checkBuffer(params, 4);
/* 2432 */     nglGetTextureParameterIivEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTextureParameterIivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetTextureParameterIiEXT(int texture, int target, int pname) {
/* 2438 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2439 */     long function_pointer = caps.glGetTextureParameterIivEXT;
/* 2440 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2441 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 2442 */     nglGetTextureParameterIivEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2443 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetTextureParameterIuEXT(int texture, int target, int pname, IntBuffer params) {
/* 2447 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2448 */     long function_pointer = caps.glGetTextureParameterIuivEXT;
/* 2449 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2450 */     BufferChecks.checkBuffer(params, 4);
/* 2451 */     nglGetTextureParameterIuivEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetTextureParameterIuivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetTextureParameterIuiEXT(int texture, int target, int pname) {
/* 2457 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2458 */     long function_pointer = caps.glGetTextureParameterIuivEXT;
/* 2459 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2460 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 2461 */     nglGetTextureParameterIuivEXT(texture, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2462 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glMultiTexParameterIEXT(int texunit, int target, int pname, IntBuffer params) {
/* 2466 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2467 */     long function_pointer = caps.glMultiTexParameterIivEXT;
/* 2468 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2469 */     BufferChecks.checkBuffer(params, 4);
/* 2470 */     nglMultiTexParameterIivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglMultiTexParameterIivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMultiTexParameterIEXT(int texunit, int target, int pname, int param) {
/* 2476 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2477 */     long function_pointer = caps.glMultiTexParameterIivEXT;
/* 2478 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2479 */     nglMultiTexParameterIivEXT(texunit, target, pname, APIUtil.getInt(caps, param), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glMultiTexParameterIuEXT(int texunit, int target, int pname, IntBuffer params) {
/* 2483 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2484 */     long function_pointer = caps.glMultiTexParameterIuivEXT;
/* 2485 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2486 */     BufferChecks.checkBuffer(params, 4);
/* 2487 */     nglMultiTexParameterIuivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglMultiTexParameterIuivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMultiTexParameterIuEXT(int texunit, int target, int pname, int param) {
/* 2493 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2494 */     long function_pointer = caps.glMultiTexParameterIuivEXT;
/* 2495 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2496 */     nglMultiTexParameterIuivEXT(texunit, target, pname, APIUtil.getInt(caps, param), function_pointer);
/*      */   }
/*      */ 
/*      */   public static void glGetMultiTexParameterIEXT(int texunit, int target, int pname, IntBuffer params) {
/* 2500 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2501 */     long function_pointer = caps.glGetMultiTexParameterIivEXT;
/* 2502 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2503 */     BufferChecks.checkBuffer(params, 4);
/* 2504 */     nglGetMultiTexParameterIivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetMultiTexParameterIivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetMultiTexParameterIiEXT(int texunit, int target, int pname) {
/* 2510 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2511 */     long function_pointer = caps.glGetMultiTexParameterIivEXT;
/* 2512 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2513 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 2514 */     nglGetMultiTexParameterIivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2515 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGetMultiTexParameterIuEXT(int texunit, int target, int pname, IntBuffer params) {
/* 2519 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2520 */     long function_pointer = caps.glGetMultiTexParameterIuivEXT;
/* 2521 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2522 */     BufferChecks.checkBuffer(params, 4);
/* 2523 */     nglGetMultiTexParameterIuivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetMultiTexParameterIuivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetMultiTexParameterIuiEXT(int texunit, int target, int pname) {
/* 2529 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2530 */     long function_pointer = caps.glGetMultiTexParameterIuivEXT;
/* 2531 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2532 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 2533 */     nglGetMultiTexParameterIuivEXT(texunit, target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2534 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glProgramUniform1uiEXT(int program, int location, int v0) {
/* 2538 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2539 */     long function_pointer = caps.glProgramUniform1uiEXT;
/* 2540 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2541 */     nglProgramUniform1uiEXT(program, location, v0, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform1uiEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform2uiEXT(int program, int location, int v0, int v1) {
/* 2546 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2547 */     long function_pointer = caps.glProgramUniform2uiEXT;
/* 2548 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2549 */     nglProgramUniform2uiEXT(program, location, v0, v1, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform2uiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform3uiEXT(int program, int location, int v0, int v1, int v2) {
/* 2554 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2555 */     long function_pointer = caps.glProgramUniform3uiEXT;
/* 2556 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2557 */     nglProgramUniform3uiEXT(program, location, v0, v1, v2, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform3uiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform4uiEXT(int program, int location, int v0, int v1, int v2, int v3) {
/* 2562 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2563 */     long function_pointer = caps.glProgramUniform4uiEXT;
/* 2564 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2565 */     nglProgramUniform4uiEXT(program, location, v0, v1, v2, v3, function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform4uiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*      */ 
/*      */   public static void glProgramUniform1uEXT(int program, int location, IntBuffer value) {
/* 2570 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2571 */     long function_pointer = caps.glProgramUniform1uivEXT;
/* 2572 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2573 */     BufferChecks.checkDirect(value);
/* 2574 */     nglProgramUniform1uivEXT(program, location, value.remaining(), MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform1uivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform2uEXT(int program, int location, IntBuffer value) {
/* 2579 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2580 */     long function_pointer = caps.glProgramUniform2uivEXT;
/* 2581 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2582 */     BufferChecks.checkDirect(value);
/* 2583 */     nglProgramUniform2uivEXT(program, location, value.remaining() >> 1, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform2uivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform3uEXT(int program, int location, IntBuffer value) {
/* 2588 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2589 */     long function_pointer = caps.glProgramUniform3uivEXT;
/* 2590 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2591 */     BufferChecks.checkDirect(value);
/* 2592 */     nglProgramUniform3uivEXT(program, location, value.remaining() / 3, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform3uivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glProgramUniform4uEXT(int program, int location, IntBuffer value) {
/* 2597 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2598 */     long function_pointer = caps.glProgramUniform4uivEXT;
/* 2599 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2600 */     BufferChecks.checkDirect(value);
/* 2601 */     nglProgramUniform4uivEXT(program, location, value.remaining() >> 2, MemoryUtil.getAddress(value), function_pointer);
/*      */   }
/*      */   static native void nglProgramUniform4uivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glNamedProgramLocalParameters4EXT(int program, int target, int index, FloatBuffer params) {
/* 2606 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2607 */     long function_pointer = caps.glNamedProgramLocalParameters4fvEXT;
/* 2608 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2609 */     BufferChecks.checkDirect(params);
/* 2610 */     nglNamedProgramLocalParameters4fvEXT(program, target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglNamedProgramLocalParameters4fvEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glNamedProgramLocalParameterI4iEXT(int program, int target, int index, int x, int y, int z, int w) {
/* 2615 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2616 */     long function_pointer = caps.glNamedProgramLocalParameterI4iEXT;
/* 2617 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2618 */     nglNamedProgramLocalParameterI4iEXT(program, target, index, x, y, z, w, function_pointer);
/*      */   }
/*      */   static native void nglNamedProgramLocalParameterI4iEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong);
/*      */ 
/*      */   public static void glNamedProgramLocalParameterI4EXT(int program, int target, int index, IntBuffer params) {
/* 2623 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2624 */     long function_pointer = caps.glNamedProgramLocalParameterI4ivEXT;
/* 2625 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2626 */     BufferChecks.checkBuffer(params, 4);
/* 2627 */     nglNamedProgramLocalParameterI4ivEXT(program, target, index, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglNamedProgramLocalParameterI4ivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glNamedProgramLocalParametersI4EXT(int program, int target, int index, IntBuffer params) {
/* 2632 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2633 */     long function_pointer = caps.glNamedProgramLocalParametersI4ivEXT;
/* 2634 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2635 */     BufferChecks.checkDirect(params);
/* 2636 */     nglNamedProgramLocalParametersI4ivEXT(program, target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglNamedProgramLocalParametersI4ivEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glNamedProgramLocalParameterI4uiEXT(int program, int target, int index, int x, int y, int z, int w) {
/* 2641 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2642 */     long function_pointer = caps.glNamedProgramLocalParameterI4uiEXT;
/* 2643 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2644 */     nglNamedProgramLocalParameterI4uiEXT(program, target, index, x, y, z, w, function_pointer);
/*      */   }
/*      */   static native void nglNamedProgramLocalParameterI4uiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong);
/*      */ 
/*      */   public static void glNamedProgramLocalParameterI4uEXT(int program, int target, int index, IntBuffer params) {
/* 2649 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2650 */     long function_pointer = caps.glNamedProgramLocalParameterI4uivEXT;
/* 2651 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2652 */     BufferChecks.checkBuffer(params, 4);
/* 2653 */     nglNamedProgramLocalParameterI4uivEXT(program, target, index, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglNamedProgramLocalParameterI4uivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glNamedProgramLocalParametersI4uEXT(int program, int target, int index, IntBuffer params) {
/* 2658 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2659 */     long function_pointer = caps.glNamedProgramLocalParametersI4uivEXT;
/* 2660 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2661 */     BufferChecks.checkDirect(params);
/* 2662 */     nglNamedProgramLocalParametersI4uivEXT(program, target, index, params.remaining() >> 2, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglNamedProgramLocalParametersI4uivEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetNamedProgramLocalParameterIEXT(int program, int target, int index, IntBuffer params) {
/* 2667 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2668 */     long function_pointer = caps.glGetNamedProgramLocalParameterIivEXT;
/* 2669 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2670 */     BufferChecks.checkBuffer(params, 4);
/* 2671 */     nglGetNamedProgramLocalParameterIivEXT(program, target, index, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetNamedProgramLocalParameterIivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetNamedProgramLocalParameterIuEXT(int program, int target, int index, IntBuffer params) {
/* 2676 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2677 */     long function_pointer = caps.glGetNamedProgramLocalParameterIuivEXT;
/* 2678 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2679 */     BufferChecks.checkBuffer(params, 4);
/* 2680 */     nglGetNamedProgramLocalParameterIuivEXT(program, target, index, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetNamedProgramLocalParameterIuivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glNamedRenderbufferStorageEXT(int renderbuffer, int internalformat, int width, int height) {
/* 2685 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2686 */     long function_pointer = caps.glNamedRenderbufferStorageEXT;
/* 2687 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2688 */     nglNamedRenderbufferStorageEXT(renderbuffer, internalformat, width, height, function_pointer);
/*      */   }
/*      */   static native void nglNamedRenderbufferStorageEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glGetNamedRenderbufferParameterEXT(int renderbuffer, int pname, IntBuffer params) {
/* 2693 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2694 */     long function_pointer = caps.glGetNamedRenderbufferParameterivEXT;
/* 2695 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2696 */     BufferChecks.checkBuffer(params, 4);
/* 2697 */     nglGetNamedRenderbufferParameterivEXT(renderbuffer, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetNamedRenderbufferParameterivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetNamedRenderbufferParameterEXT(int renderbuffer, int pname) {
/* 2703 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2704 */     long function_pointer = caps.glGetNamedRenderbufferParameterivEXT;
/* 2705 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2706 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 2707 */     nglGetNamedRenderbufferParameterivEXT(renderbuffer, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2708 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glNamedRenderbufferStorageMultisampleEXT(int renderbuffer, int samples, int internalformat, int width, int height) {
/* 2712 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2713 */     long function_pointer = caps.glNamedRenderbufferStorageMultisampleEXT;
/* 2714 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2715 */     nglNamedRenderbufferStorageMultisampleEXT(renderbuffer, samples, internalformat, width, height, function_pointer);
/*      */   }
/*      */   static native void nglNamedRenderbufferStorageMultisampleEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glNamedRenderbufferStorageMultisampleCoverageEXT(int renderbuffer, int coverageSamples, int colorSamples, int internalformat, int width, int height) {
/* 2720 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2721 */     long function_pointer = caps.glNamedRenderbufferStorageMultisampleCoverageEXT;
/* 2722 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2723 */     nglNamedRenderbufferStorageMultisampleCoverageEXT(renderbuffer, coverageSamples, colorSamples, internalformat, width, height, function_pointer);
/*      */   }
/*      */   static native void nglNamedRenderbufferStorageMultisampleCoverageEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*      */ 
/*      */   public static int glCheckNamedFramebufferStatusEXT(int framebuffer, int target) {
/* 2728 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2729 */     long function_pointer = caps.glCheckNamedFramebufferStatusEXT;
/* 2730 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2731 */     int __result = nglCheckNamedFramebufferStatusEXT(framebuffer, target, function_pointer);
/* 2732 */     return __result;
/*      */   }
/*      */   static native int nglCheckNamedFramebufferStatusEXT(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glNamedFramebufferTexture1DEXT(int framebuffer, int attachment, int textarget, int texture, int level) {
/* 2737 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2738 */     long function_pointer = caps.glNamedFramebufferTexture1DEXT;
/* 2739 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2740 */     nglNamedFramebufferTexture1DEXT(framebuffer, attachment, textarget, texture, level, function_pointer);
/*      */   }
/*      */   static native void nglNamedFramebufferTexture1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glNamedFramebufferTexture2DEXT(int framebuffer, int attachment, int textarget, int texture, int level) {
/* 2745 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2746 */     long function_pointer = caps.glNamedFramebufferTexture2DEXT;
/* 2747 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2748 */     nglNamedFramebufferTexture2DEXT(framebuffer, attachment, textarget, texture, level, function_pointer);
/*      */   }
/*      */   static native void nglNamedFramebufferTexture2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glNamedFramebufferTexture3DEXT(int framebuffer, int attachment, int textarget, int texture, int level, int zoffset) {
/* 2753 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2754 */     long function_pointer = caps.glNamedFramebufferTexture3DEXT;
/* 2755 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2756 */     nglNamedFramebufferTexture3DEXT(framebuffer, attachment, textarget, texture, level, zoffset, function_pointer);
/*      */   }
/*      */   static native void nglNamedFramebufferTexture3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*      */ 
/*      */   public static void glNamedFramebufferRenderbufferEXT(int framebuffer, int attachment, int renderbuffertarget, int renderbuffer) {
/* 2761 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2762 */     long function_pointer = caps.glNamedFramebufferRenderbufferEXT;
/* 2763 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2764 */     nglNamedFramebufferRenderbufferEXT(framebuffer, attachment, renderbuffertarget, renderbuffer, function_pointer);
/*      */   }
/*      */   static native void nglNamedFramebufferRenderbufferEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glGetNamedFramebufferAttachmentParameterEXT(int framebuffer, int attachment, int pname, IntBuffer params) {
/* 2769 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2770 */     long function_pointer = caps.glGetNamedFramebufferAttachmentParameterivEXT;
/* 2771 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2772 */     BufferChecks.checkBuffer(params, 4);
/* 2773 */     nglGetNamedFramebufferAttachmentParameterivEXT(framebuffer, attachment, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetNamedFramebufferAttachmentParameterivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetNamedFramebufferAttachmentParameterEXT(int framebuffer, int attachment, int pname) {
/* 2779 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2780 */     long function_pointer = caps.glGetNamedFramebufferAttachmentParameterivEXT;
/* 2781 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2782 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 2783 */     nglGetNamedFramebufferAttachmentParameterivEXT(framebuffer, attachment, pname, MemoryUtil.getAddress(params), function_pointer);
/* 2784 */     return params.get(0);
/*      */   }
/*      */ 
/*      */   public static void glGenerateTextureMipmapEXT(int texture, int target) {
/* 2788 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2789 */     long function_pointer = caps.glGenerateTextureMipmapEXT;
/* 2790 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2791 */     nglGenerateTextureMipmapEXT(texture, target, function_pointer);
/*      */   }
/*      */   static native void nglGenerateTextureMipmapEXT(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glGenerateMultiTexMipmapEXT(int texunit, int target) {
/* 2796 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2797 */     long function_pointer = caps.glGenerateMultiTexMipmapEXT;
/* 2798 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2799 */     nglGenerateMultiTexMipmapEXT(texunit, target, function_pointer);
/*      */   }
/*      */   static native void nglGenerateMultiTexMipmapEXT(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glFramebufferDrawBufferEXT(int framebuffer, int mode) {
/* 2804 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2805 */     long function_pointer = caps.glFramebufferDrawBufferEXT;
/* 2806 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2807 */     nglFramebufferDrawBufferEXT(framebuffer, mode, function_pointer);
/*      */   }
/*      */   static native void nglFramebufferDrawBufferEXT(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glFramebufferDrawBuffersEXT(int framebuffer, IntBuffer bufs) {
/* 2812 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2813 */     long function_pointer = caps.glFramebufferDrawBuffersEXT;
/* 2814 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2815 */     BufferChecks.checkDirect(bufs);
/* 2816 */     nglFramebufferDrawBuffersEXT(framebuffer, bufs.remaining(), MemoryUtil.getAddress(bufs), function_pointer);
/*      */   }
/*      */   static native void nglFramebufferDrawBuffersEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glFramebufferReadBufferEXT(int framebuffer, int mode) {
/* 2821 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2822 */     long function_pointer = caps.glFramebufferReadBufferEXT;
/* 2823 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2824 */     nglFramebufferReadBufferEXT(framebuffer, mode, function_pointer);
/*      */   }
/*      */   static native void nglFramebufferReadBufferEXT(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glGetFramebufferParameterEXT(int framebuffer, int pname, IntBuffer param) {
/* 2829 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2830 */     long function_pointer = caps.glGetFramebufferParameterivEXT;
/* 2831 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2832 */     BufferChecks.checkBuffer(param, 4);
/* 2833 */     nglGetFramebufferParameterivEXT(framebuffer, pname, MemoryUtil.getAddress(param), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetFramebufferParameterivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetFramebufferParameterEXT(int framebuffer, int pname) {
/* 2839 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2840 */     long function_pointer = caps.glGetFramebufferParameterivEXT;
/* 2841 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2842 */     IntBuffer param = APIUtil.getBufferInt(caps);
/* 2843 */     nglGetFramebufferParameterivEXT(framebuffer, pname, MemoryUtil.getAddress(param), function_pointer);
/* 2844 */     return param.get(0);
/*      */   }
/*      */ 
/*      */   public static void glNamedCopyBufferSubDataEXT(int readBuffer, int writeBuffer, long readoffset, long writeoffset, long size) {
/* 2848 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2849 */     long function_pointer = caps.glNamedCopyBufferSubDataEXT;
/* 2850 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2851 */     nglNamedCopyBufferSubDataEXT(readBuffer, writeBuffer, readoffset, writeoffset, size, function_pointer);
/*      */   }
/*      */   static native void nglNamedCopyBufferSubDataEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*      */ 
/*      */   public static void glNamedFramebufferTextureEXT(int framebuffer, int attachment, int texture, int level) {
/* 2856 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2857 */     long function_pointer = caps.glNamedFramebufferTextureEXT;
/* 2858 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2859 */     nglNamedFramebufferTextureEXT(framebuffer, attachment, texture, level, function_pointer);
/*      */   }
/*      */   static native void nglNamedFramebufferTextureEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*      */ 
/*      */   public static void glNamedFramebufferTextureLayerEXT(int framebuffer, int attachment, int texture, int level, int layer) {
/* 2864 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2865 */     long function_pointer = caps.glNamedFramebufferTextureLayerEXT;
/* 2866 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2867 */     nglNamedFramebufferTextureLayerEXT(framebuffer, attachment, texture, level, layer, function_pointer);
/*      */   }
/*      */   static native void nglNamedFramebufferTextureLayerEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glNamedFramebufferTextureFaceEXT(int framebuffer, int attachment, int texture, int level, int face) {
/* 2872 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2873 */     long function_pointer = caps.glNamedFramebufferTextureFaceEXT;
/* 2874 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2875 */     nglNamedFramebufferTextureFaceEXT(framebuffer, attachment, texture, level, face, function_pointer);
/*      */   }
/*      */   static native void nglNamedFramebufferTextureFaceEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glTextureRenderbufferEXT(int texture, int target, int renderbuffer) {
/* 2880 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2881 */     long function_pointer = caps.glTextureRenderbufferEXT;
/* 2882 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2883 */     nglTextureRenderbufferEXT(texture, target, renderbuffer, function_pointer);
/*      */   }
/*      */   static native void nglTextureRenderbufferEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glMultiTexRenderbufferEXT(int texunit, int target, int renderbuffer) {
/* 2888 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2889 */     long function_pointer = caps.glMultiTexRenderbufferEXT;
/* 2890 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2891 */     nglMultiTexRenderbufferEXT(texunit, target, renderbuffer, function_pointer);
/*      */   }
/*      */   static native void nglMultiTexRenderbufferEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glVertexArrayVertexOffsetEXT(int vaobj, int buffer, int size, int type, int stride, long offset) {
/* 2896 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2897 */     long function_pointer = caps.glVertexArrayVertexOffsetEXT;
/* 2898 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2899 */     nglVertexArrayVertexOffsetEXT(vaobj, buffer, size, type, stride, offset, function_pointer);
/*      */   }
/*      */   static native void nglVertexArrayVertexOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexArrayColorOffsetEXT(int vaobj, int buffer, int size, int type, int stride, long offset) {
/* 2904 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2905 */     long function_pointer = caps.glVertexArrayColorOffsetEXT;
/* 2906 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2907 */     nglVertexArrayColorOffsetEXT(vaobj, buffer, size, type, stride, offset, function_pointer);
/*      */   }
/*      */   static native void nglVertexArrayColorOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexArrayEdgeFlagOffsetEXT(int vaobj, int buffer, int stride, long offset) {
/* 2912 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2913 */     long function_pointer = caps.glVertexArrayEdgeFlagOffsetEXT;
/* 2914 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2915 */     nglVertexArrayEdgeFlagOffsetEXT(vaobj, buffer, stride, offset, function_pointer);
/*      */   }
/*      */   static native void nglVertexArrayEdgeFlagOffsetEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexArrayIndexOffsetEXT(int vaobj, int buffer, int type, int stride, long offset) {
/* 2920 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2921 */     long function_pointer = caps.glVertexArrayIndexOffsetEXT;
/* 2922 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2923 */     nglVertexArrayIndexOffsetEXT(vaobj, buffer, type, stride, offset, function_pointer);
/*      */   }
/*      */   static native void nglVertexArrayIndexOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexArrayNormalOffsetEXT(int vaobj, int buffer, int type, int stride, long offset) {
/* 2928 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2929 */     long function_pointer = caps.glVertexArrayNormalOffsetEXT;
/* 2930 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2931 */     nglVertexArrayNormalOffsetEXT(vaobj, buffer, type, stride, offset, function_pointer);
/*      */   }
/*      */   static native void nglVertexArrayNormalOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexArrayTexCoordOffsetEXT(int vaobj, int buffer, int size, int type, int stride, long offset) {
/* 2936 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2937 */     long function_pointer = caps.glVertexArrayTexCoordOffsetEXT;
/* 2938 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2939 */     nglVertexArrayTexCoordOffsetEXT(vaobj, buffer, size, type, stride, offset, function_pointer);
/*      */   }
/*      */   static native void nglVertexArrayTexCoordOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexArrayMultiTexCoordOffsetEXT(int vaobj, int buffer, int texunit, int size, int type, int stride, long offset) {
/* 2944 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2945 */     long function_pointer = caps.glVertexArrayMultiTexCoordOffsetEXT;
/* 2946 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2947 */     nglVertexArrayMultiTexCoordOffsetEXT(vaobj, buffer, texunit, size, type, stride, offset, function_pointer);
/*      */   }
/*      */   static native void nglVertexArrayMultiTexCoordOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexArrayFogCoordOffsetEXT(int vaobj, int buffer, int type, int stride, long offset) {
/* 2952 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2953 */     long function_pointer = caps.glVertexArrayFogCoordOffsetEXT;
/* 2954 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2955 */     nglVertexArrayFogCoordOffsetEXT(vaobj, buffer, type, stride, offset, function_pointer);
/*      */   }
/*      */   static native void nglVertexArrayFogCoordOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexArraySecondaryColorOffsetEXT(int vaobj, int buffer, int size, int type, int stride, long offset) {
/* 2960 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2961 */     long function_pointer = caps.glVertexArraySecondaryColorOffsetEXT;
/* 2962 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2963 */     nglVertexArraySecondaryColorOffsetEXT(vaobj, buffer, size, type, stride, offset, function_pointer);
/*      */   }
/*      */   static native void nglVertexArraySecondaryColorOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexArrayVertexAttribOffsetEXT(int vaobj, int buffer, int index, int size, int type, boolean normalized, int stride, long offset) {
/* 2968 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2969 */     long function_pointer = caps.glVertexArrayVertexAttribOffsetEXT;
/* 2970 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2971 */     nglVertexArrayVertexAttribOffsetEXT(vaobj, buffer, index, size, type, normalized, stride, offset, function_pointer);
/*      */   }
/*      */   static native void nglVertexArrayVertexAttribOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean, int paramInt6, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glVertexArrayVertexAttribIOffsetEXT(int vaobj, int buffer, int index, int size, int type, int stride, long offset) {
/* 2976 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2977 */     long function_pointer = caps.glVertexArrayVertexAttribIOffsetEXT;
/* 2978 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2979 */     nglVertexArrayVertexAttribIOffsetEXT(vaobj, buffer, index, size, type, stride, offset, function_pointer);
/*      */   }
/*      */   static native void nglVertexArrayVertexAttribIOffsetEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glEnableVertexArrayEXT(int vaobj, int array) {
/* 2984 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2985 */     long function_pointer = caps.glEnableVertexArrayEXT;
/* 2986 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2987 */     nglEnableVertexArrayEXT(vaobj, array, function_pointer);
/*      */   }
/*      */   static native void nglEnableVertexArrayEXT(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glDisableVertexArrayEXT(int vaobj, int array) {
/* 2992 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 2993 */     long function_pointer = caps.glDisableVertexArrayEXT;
/* 2994 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 2995 */     nglDisableVertexArrayEXT(vaobj, array, function_pointer);
/*      */   }
/*      */   static native void nglDisableVertexArrayEXT(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glEnableVertexArrayAttribEXT(int vaobj, int index) {
/* 3000 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3001 */     long function_pointer = caps.glEnableVertexArrayAttribEXT;
/* 3002 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3003 */     nglEnableVertexArrayAttribEXT(vaobj, index, function_pointer);
/*      */   }
/*      */   static native void nglEnableVertexArrayAttribEXT(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glDisableVertexArrayAttribEXT(int vaobj, int index) {
/* 3008 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3009 */     long function_pointer = caps.glDisableVertexArrayAttribEXT;
/* 3010 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3011 */     nglDisableVertexArrayAttribEXT(vaobj, index, function_pointer);
/*      */   }
/*      */   static native void nglDisableVertexArrayAttribEXT(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void glGetVertexArrayIntegerEXT(int vaobj, int pname, IntBuffer param) {
/* 3016 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3017 */     long function_pointer = caps.glGetVertexArrayIntegervEXT;
/* 3018 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3019 */     BufferChecks.checkBuffer(param, 16);
/* 3020 */     nglGetVertexArrayIntegervEXT(vaobj, pname, MemoryUtil.getAddress(param), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetVertexArrayIntegervEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetVertexArrayIntegerEXT(int vaobj, int pname) {
/* 3026 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3027 */     long function_pointer = caps.glGetVertexArrayIntegervEXT;
/* 3028 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3029 */     IntBuffer param = APIUtil.getBufferInt(caps);
/* 3030 */     nglGetVertexArrayIntegervEXT(vaobj, pname, MemoryUtil.getAddress(param), function_pointer);
/* 3031 */     return param.get(0);
/*      */   }
/*      */ 
/*      */   public static ByteBuffer glGetVertexArrayPointerEXT(int vaobj, int pname, long result_size) {
/* 3035 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3036 */     long function_pointer = caps.glGetVertexArrayPointervEXT;
/* 3037 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3038 */     ByteBuffer __result = nglGetVertexArrayPointervEXT(vaobj, pname, result_size, function_pointer);
/* 3039 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*      */   }
/*      */   static native ByteBuffer nglGetVertexArrayPointervEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetVertexArrayIntegerEXT(int vaobj, int index, int pname, IntBuffer param) {
/* 3044 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3045 */     long function_pointer = caps.glGetVertexArrayIntegeri_vEXT;
/* 3046 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3047 */     BufferChecks.checkBuffer(param, 16);
/* 3048 */     nglGetVertexArrayIntegeri_vEXT(vaobj, index, pname, MemoryUtil.getAddress(param), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetVertexArrayIntegeri_vEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static int glGetVertexArrayIntegeriEXT(int vaobj, int index, int pname) {
/* 3054 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3055 */     long function_pointer = caps.glGetVertexArrayIntegeri_vEXT;
/* 3056 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3057 */     IntBuffer param = APIUtil.getBufferInt(caps);
/* 3058 */     nglGetVertexArrayIntegeri_vEXT(vaobj, index, pname, MemoryUtil.getAddress(param), function_pointer);
/* 3059 */     return param.get(0);
/*      */   }
/*      */ 
/*      */   public static ByteBuffer glGetVertexArrayPointeri_EXT(int vaobj, int index, int pname, long result_size) {
/* 3063 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3064 */     long function_pointer = caps.glGetVertexArrayPointeri_vEXT;
/* 3065 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3066 */     ByteBuffer __result = nglGetVertexArrayPointeri_vEXT(vaobj, index, pname, result_size, function_pointer);
/* 3067 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*      */   }
/*      */ 
/*      */   static native ByteBuffer nglGetVertexArrayPointeri_vEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static ByteBuffer glMapNamedBufferRangeEXT(int buffer, long offset, long length, int access, ByteBuffer old_buffer)
/*      */   {
/* 3086 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3087 */     long function_pointer = caps.glMapNamedBufferRangeEXT;
/* 3088 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3089 */     if (old_buffer != null)
/* 3090 */       BufferChecks.checkDirect(old_buffer);
/* 3091 */     ByteBuffer __result = nglMapNamedBufferRangeEXT(buffer, offset, length, access, old_buffer, function_pointer);
/* 3092 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*      */   }
/*      */   static native ByteBuffer nglMapNamedBufferRangeEXT(int paramInt1, long paramLong1, long paramLong2, int paramInt2, ByteBuffer paramByteBuffer, long paramLong3);
/*      */ 
/*      */   public static void glFlushMappedNamedBufferRangeEXT(int buffer, long offset, long length) {
/* 3097 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 3098 */     long function_pointer = caps.glFlushMappedNamedBufferRangeEXT;
/* 3099 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 3100 */     nglFlushMappedNamedBufferRangeEXT(buffer, offset, length, function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglFlushMappedNamedBufferRangeEXT(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTDirectStateAccess
 * JD-Core Version:    0.6.2
 */