/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class ARBTextureCompression
/*     */ {
/*     */   public static final int GL_COMPRESSED_ALPHA_ARB = 34025;
/*     */   public static final int GL_COMPRESSED_LUMINANCE_ARB = 34026;
/*     */   public static final int GL_COMPRESSED_LUMINANCE_ALPHA_ARB = 34027;
/*     */   public static final int GL_COMPRESSED_INTENSITY_ARB = 34028;
/*     */   public static final int GL_COMPRESSED_RGB_ARB = 34029;
/*     */   public static final int GL_COMPRESSED_RGBA_ARB = 34030;
/*     */   public static final int GL_TEXTURE_COMPRESSION_HINT_ARB = 34031;
/*     */   public static final int GL_TEXTURE_COMPRESSED_IMAGE_SIZE_ARB = 34464;
/*     */   public static final int GL_TEXTURE_COMPRESSED_ARB = 34465;
/*     */   public static final int GL_NUM_COMPRESSED_TEXTURE_FORMATS_ARB = 34466;
/*     */   public static final int GL_COMPRESSED_TEXTURE_FORMATS_ARB = 34467;
/*     */ 
/*     */   public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, ByteBuffer pData)
/*     */   {
/*  25 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  26 */     long function_pointer = caps.glCompressedTexImage1DARB;
/*  27 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  28 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  29 */     BufferChecks.checkDirect(pData);
/*  30 */     nglCompressedTexImage1DARB(target, level, internalformat, width, border, pData.remaining(), MemoryUtil.getAddress(pData), function_pointer);
/*     */   }
/*     */   static native void nglCompressedTexImage1DARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*     */ 
/*  34 */   public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int pData_imageSize, long pData_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  35 */     long function_pointer = caps.glCompressedTexImage1DARB;
/*  36 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  37 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  38 */     nglCompressedTexImage1DARBBO(target, level, internalformat, width, border, pData_imageSize, pData_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglCompressedTexImage1DARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, ByteBuffer pData) {
/*  43 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  44 */     long function_pointer = caps.glCompressedTexImage2DARB;
/*  45 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  46 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  47 */     BufferChecks.checkDirect(pData);
/*  48 */     nglCompressedTexImage2DARB(target, level, internalformat, width, height, border, pData.remaining(), MemoryUtil.getAddress(pData), function_pointer);
/*     */   }
/*     */   static native void nglCompressedTexImage2DARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*     */ 
/*  52 */   public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int pData_imageSize, long pData_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  53 */     long function_pointer = caps.glCompressedTexImage2DARB;
/*  54 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  55 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  56 */     nglCompressedTexImage2DARBBO(target, level, internalformat, width, height, border, pData_imageSize, pData_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglCompressedTexImage2DARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, ByteBuffer pData) {
/*  61 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  62 */     long function_pointer = caps.glCompressedTexImage3DARB;
/*  63 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  64 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  65 */     BufferChecks.checkDirect(pData);
/*  66 */     nglCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, pData.remaining(), MemoryUtil.getAddress(pData), function_pointer);
/*     */   }
/*     */   static native void nglCompressedTexImage3DARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*     */ 
/*  70 */   public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int pData_imageSize, long pData_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  71 */     long function_pointer = caps.glCompressedTexImage3DARB;
/*  72 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  73 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  74 */     nglCompressedTexImage3DARBBO(target, level, internalformat, width, height, depth, border, pData_imageSize, pData_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglCompressedTexImage3DARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int format, ByteBuffer pData) {
/*  79 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  80 */     long function_pointer = caps.glCompressedTexSubImage1DARB;
/*  81 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  82 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  83 */     BufferChecks.checkDirect(pData);
/*  84 */     nglCompressedTexSubImage1DARB(target, level, xoffset, width, format, pData.remaining(), MemoryUtil.getAddress(pData), function_pointer);
/*     */   }
/*     */   static native void nglCompressedTexSubImage1DARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*     */ 
/*  88 */   public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int format, int pData_imageSize, long pData_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  89 */     long function_pointer = caps.glCompressedTexSubImage1DARB;
/*  90 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  91 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  92 */     nglCompressedTexSubImage1DARBBO(target, level, xoffset, width, format, pData_imageSize, pData_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglCompressedTexSubImage1DARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int format, ByteBuffer pData) {
/*  97 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  98 */     long function_pointer = caps.glCompressedTexSubImage2DARB;
/*  99 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 100 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 101 */     BufferChecks.checkDirect(pData);
/* 102 */     nglCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, format, pData.remaining(), MemoryUtil.getAddress(pData), function_pointer);
/*     */   }
/*     */   static native void nglCompressedTexSubImage2DARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*     */ 
/* 106 */   public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int format, int pData_imageSize, long pData_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 107 */     long function_pointer = caps.glCompressedTexSubImage2DARB;
/* 108 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 109 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 110 */     nglCompressedTexSubImage2DARBBO(target, level, xoffset, yoffset, width, height, format, pData_imageSize, pData_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglCompressedTexSubImage2DARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, ByteBuffer pData) {
/* 115 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 116 */     long function_pointer = caps.glCompressedTexSubImage3DARB;
/* 117 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 118 */     GLChecks.ensureUnpackPBOdisabled(caps);
/* 119 */     BufferChecks.checkDirect(pData);
/* 120 */     nglCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, format, pData.remaining(), MemoryUtil.getAddress(pData), function_pointer);
/*     */   }
/*     */   static native void nglCompressedTexSubImage3DARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/*     */ 
/* 124 */   public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int pData_imageSize, long pData_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 125 */     long function_pointer = caps.glCompressedTexSubImage3DARB;
/* 126 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 127 */     GLChecks.ensureUnpackPBOenabled(caps);
/* 128 */     nglCompressedTexSubImage3DARBBO(target, level, xoffset, yoffset, zoffset, width, height, depth, format, pData_imageSize, pData_buffer_offset, function_pointer); }
/*     */ 
/*     */   static native void nglCompressedTexSubImage3DARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGetCompressedTexImageARB(int target, int lod, ByteBuffer pImg) {
/* 133 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 134 */     long function_pointer = caps.glGetCompressedTexImageARB;
/* 135 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 136 */     GLChecks.ensurePackPBOdisabled(caps);
/* 137 */     BufferChecks.checkDirect(pImg);
/* 138 */     nglGetCompressedTexImageARB(target, lod, MemoryUtil.getAddress(pImg), function_pointer);
/*     */   }
/*     */   static native void nglGetCompressedTexImageARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/* 142 */   public static void glGetCompressedTexImageARB(int target, int lod, long pImg_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 143 */     long function_pointer = caps.glGetCompressedTexImageARB;
/* 144 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 145 */     GLChecks.ensurePackPBOenabled(caps);
/* 146 */     nglGetCompressedTexImageARBBO(target, lod, pImg_buffer_offset, function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetCompressedTexImageARBBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBTextureCompression
 * JD-Core Version:    0.6.2
 */