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
/*     */ public final class GL12
/*     */ {
/*     */   public static final int GL_TEXTURE_BINDING_3D = 32874;
/*     */   public static final int GL_PACK_SKIP_IMAGES = 32875;
/*     */   public static final int GL_PACK_IMAGE_HEIGHT = 32876;
/*     */   public static final int GL_UNPACK_SKIP_IMAGES = 32877;
/*     */   public static final int GL_UNPACK_IMAGE_HEIGHT = 32878;
/*     */   public static final int GL_TEXTURE_3D = 32879;
/*     */   public static final int GL_PROXY_TEXTURE_3D = 32880;
/*     */   public static final int GL_TEXTURE_DEPTH = 32881;
/*     */   public static final int GL_TEXTURE_WRAP_R = 32882;
/*     */   public static final int GL_MAX_3D_TEXTURE_SIZE = 32883;
/*     */   public static final int GL_BGR = 32992;
/*     */   public static final int GL_BGRA = 32993;
/*     */   public static final int GL_UNSIGNED_BYTE_3_3_2 = 32818;
/*     */   public static final int GL_UNSIGNED_BYTE_2_3_3_REV = 33634;
/*     */   public static final int GL_UNSIGNED_SHORT_5_6_5 = 33635;
/*     */   public static final int GL_UNSIGNED_SHORT_5_6_5_REV = 33636;
/*     */   public static final int GL_UNSIGNED_SHORT_4_4_4_4 = 32819;
/*     */   public static final int GL_UNSIGNED_SHORT_4_4_4_4_REV = 33637;
/*     */   public static final int GL_UNSIGNED_SHORT_5_5_5_1 = 32820;
/*     */   public static final int GL_UNSIGNED_SHORT_1_5_5_5_REV = 33638;
/*     */   public static final int GL_UNSIGNED_INT_8_8_8_8 = 32821;
/*     */   public static final int GL_UNSIGNED_INT_8_8_8_8_REV = 33639;
/*     */   public static final int GL_UNSIGNED_INT_10_10_10_2 = 32822;
/*     */   public static final int GL_UNSIGNED_INT_2_10_10_10_REV = 33640;
/*     */   public static final int GL_RESCALE_NORMAL = 32826;
/*     */   public static final int GL_LIGHT_MODEL_COLOR_CONTROL = 33272;
/*     */   public static final int GL_SINGLE_COLOR = 33273;
/*     */   public static final int GL_SEPARATE_SPECULAR_COLOR = 33274;
/*     */   public static final int GL_CLAMP_TO_EDGE = 33071;
/*     */   public static final int GL_TEXTURE_MIN_LOD = 33082;
/*     */   public static final int GL_TEXTURE_MAX_LOD = 33083;
/*     */   public static final int GL_TEXTURE_BASE_LEVEL = 33084;
/*     */   public static final int GL_TEXTURE_MAX_LEVEL = 33085;
/*     */   public static final int GL_MAX_ELEMENTS_VERTICES = 33000;
/*     */   public static final int GL_MAX_ELEMENTS_INDICES = 33001;
/*     */   public static final int GL_ALIASED_POINT_SIZE_RANGE = 33901;
/*     */   public static final int GL_ALIASED_LINE_WIDTH_RANGE = 33902;
/*     */   public static final int GL_SMOOTH_POINT_SIZE_RANGE = 2834;
/*     */   public static final int GL_SMOOTH_POINT_SIZE_GRANULARITY = 2835;
/*     */   public static final int GL_SMOOTH_LINE_WIDTH_RANGE = 2850;
/*     */   public static final int GL_SMOOTH_LINE_WIDTH_GRANULARITY = 2851;
/*     */ 
/*     */   public static void glDrawRangeElements(int mode, int start, int end, ByteBuffer indices)
/*     */   {
/*  63 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  64 */     long function_pointer = caps.glDrawRangeElements;
/*  65 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  66 */     GLChecks.ensureElementVBOdisabled(caps);
/*  67 */     BufferChecks.checkDirect(indices);
/*  68 */     nglDrawRangeElements(mode, start, end, indices.remaining(), 5121, MemoryUtil.getAddress(indices), function_pointer);
/*     */   }
/*     */   public static void glDrawRangeElements(int mode, int start, int end, IntBuffer indices) {
/*  71 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  72 */     long function_pointer = caps.glDrawRangeElements;
/*  73 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  74 */     GLChecks.ensureElementVBOdisabled(caps);
/*  75 */     BufferChecks.checkDirect(indices);
/*  76 */     nglDrawRangeElements(mode, start, end, indices.remaining(), 5125, MemoryUtil.getAddress(indices), function_pointer);
/*     */   }
/*     */   public static void glDrawRangeElements(int mode, int start, int end, ShortBuffer indices) {
/*  79 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  80 */     long function_pointer = caps.glDrawRangeElements;
/*  81 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  82 */     GLChecks.ensureElementVBOdisabled(caps);
/*  83 */     BufferChecks.checkDirect(indices);
/*  84 */     nglDrawRangeElements(mode, start, end, indices.remaining(), 5123, MemoryUtil.getAddress(indices), function_pointer);
/*     */   }
/*     */   static native void nglDrawRangeElements(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*     */ 
/*  88 */   public static void glDrawRangeElements(int mode, int start, int end, int indices_count, int type, long indices_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  89 */     long function_pointer = caps.glDrawRangeElements;
/*  90 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  91 */     GLChecks.ensureElementVBOenabled(caps);
/*  92 */     nglDrawRangeElementsBO(mode, start, end, indices_count, type, indices_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglDrawRangeElementsBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels) {
/*  97 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  98 */     long function_pointer = caps.glTexImage3D;
/*  99 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 100 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 101 */     if (pixels != null)
/* 102 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 103 */     nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*     */   }
/*     */   public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, DoubleBuffer pixels) {
/* 106 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 107 */     long function_pointer = caps.glTexImage3D;
/* 108 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 109 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 110 */     if (pixels != null)
/* 111 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 112 */     nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*     */   }
/*     */   public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, FloatBuffer pixels) {
/* 115 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 116 */     long function_pointer = caps.glTexImage3D;
/* 117 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 118 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 119 */     if (pixels != null)
/* 120 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 121 */     nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*     */   }
/*     */   public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, IntBuffer pixels) {
/* 124 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 125 */     long function_pointer = caps.glTexImage3D;
/* 126 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 127 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 128 */     if (pixels != null)
/* 129 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 130 */     nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*     */   }
/*     */   public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ShortBuffer pixels) {
/* 133 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 134 */     long function_pointer = caps.glTexImage3D;
/* 135 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 136 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 137 */     if (pixels != null)
/* 138 */       BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 139 */     nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/*     */   }
/*     */   static native void nglTexImage3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*     */ 
/* 143 */   public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 144 */     long function_pointer = caps.glTexImage3D;
/* 145 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 146 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 147 */     nglTexImage3DBO(target, level, internalFormat, width, height, depth, border, format, type, pixels_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglTexImage3DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) {
/* 152 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 153 */     long function_pointer = caps.glTexSubImage3D;
/* 154 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 155 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 156 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 157 */     nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*     */   }
/*     */   public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, DoubleBuffer pixels) {
/* 160 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 161 */     long function_pointer = caps.glTexSubImage3D;
/* 162 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 163 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 164 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 165 */     nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*     */   }
/*     */   public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels) {
/* 168 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 169 */     long function_pointer = caps.glTexSubImage3D;
/* 170 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 171 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 172 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 173 */     nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*     */   }
/*     */   public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels) {
/* 176 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 177 */     long function_pointer = caps.glTexSubImage3D;
/* 178 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 179 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 180 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 181 */     nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*     */   }
/*     */   public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels) {
/* 184 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 185 */     long function_pointer = caps.glTexSubImage3D;
/* 186 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 187 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 188 */     BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 189 */     nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/*     */   }
/*     */   static native void nglTexSubImage3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/*     */ 
/* 193 */   public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 194 */     long function_pointer = caps.glTexSubImage3D;
/* 195 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 196 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 197 */     nglTexSubImage3DBO(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglTexSubImage3DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height) {
/* 202 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 203 */     long function_pointer = caps.glCopyTexSubImage3D;
/* 204 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 205 */     nglCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset, x, y, width, height, function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglCopyTexSubImage3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL12
 * JD-Core Version:    0.6.2
 */