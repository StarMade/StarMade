/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class EXTTextureInteger
/*     */ {
/*     */   public static final int GL_RGBA_INTEGER_MODE_EXT = 36254;
/*     */   public static final int GL_RGBA32UI_EXT = 36208;
/*     */   public static final int GL_RGB32UI_EXT = 36209;
/*     */   public static final int GL_ALPHA32UI_EXT = 36210;
/*     */   public static final int GL_INTENSITY32UI_EXT = 36211;
/*     */   public static final int GL_LUMINANCE32UI_EXT = 36212;
/*     */   public static final int GL_LUMINANCE_ALPHA32UI_EXT = 36213;
/*     */   public static final int GL_RGBA16UI_EXT = 36214;
/*     */   public static final int GL_RGB16UI_EXT = 36215;
/*     */   public static final int GL_ALPHA16UI_EXT = 36216;
/*     */   public static final int GL_INTENSITY16UI_EXT = 36217;
/*     */   public static final int GL_LUMINANCE16UI_EXT = 36218;
/*     */   public static final int GL_LUMINANCE_ALPHA16UI_EXT = 36219;
/*     */   public static final int GL_RGBA8UI_EXT = 36220;
/*     */   public static final int GL_RGB8UI_EXT = 36221;
/*     */   public static final int GL_ALPHA8UI_EXT = 36222;
/*     */   public static final int GL_INTENSITY8UI_EXT = 36223;
/*     */   public static final int GL_LUMINANCE8UI_EXT = 36224;
/*     */   public static final int GL_LUMINANCE_ALPHA8UI_EXT = 36225;
/*     */   public static final int GL_RGBA32I_EXT = 36226;
/*     */   public static final int GL_RGB32I_EXT = 36227;
/*     */   public static final int GL_ALPHA32I_EXT = 36228;
/*     */   public static final int GL_INTENSITY32I_EXT = 36229;
/*     */   public static final int GL_LUMINANCE32I_EXT = 36230;
/*     */   public static final int GL_LUMINANCE_ALPHA32I_EXT = 36231;
/*     */   public static final int GL_RGBA16I_EXT = 36232;
/*     */   public static final int GL_RGB16I_EXT = 36233;
/*     */   public static final int GL_ALPHA16I_EXT = 36234;
/*     */   public static final int GL_INTENSITY16I_EXT = 36235;
/*     */   public static final int GL_LUMINANCE16I_EXT = 36236;
/*     */   public static final int GL_LUMINANCE_ALPHA16I_EXT = 36237;
/*     */   public static final int GL_RGBA8I_EXT = 36238;
/*     */   public static final int GL_RGB8I_EXT = 36239;
/*     */   public static final int GL_ALPHA8I_EXT = 36240;
/*     */   public static final int GL_INTENSITY8I_EXT = 36241;
/*     */   public static final int GL_LUMINANCE8I_EXT = 36242;
/*     */   public static final int GL_LUMINANCE_ALPHA8I_EXT = 36243;
/*     */   public static final int GL_RED_INTEGER_EXT = 36244;
/*     */   public static final int GL_GREEN_INTEGER_EXT = 36245;
/*     */   public static final int GL_BLUE_INTEGER_EXT = 36246;
/*     */   public static final int GL_ALPHA_INTEGER_EXT = 36247;
/*     */   public static final int GL_RGB_INTEGER_EXT = 36248;
/*     */   public static final int GL_RGBA_INTEGER_EXT = 36249;
/*     */   public static final int GL_BGR_INTEGER_EXT = 36250;
/*     */   public static final int GL_BGRA_INTEGER_EXT = 36251;
/*     */   public static final int GL_LUMINANCE_INTEGER_EXT = 36252;
/*     */   public static final int GL_LUMINANCE_ALPHA_INTEGER_EXT = 36253;
/*     */ 
/*     */   public static void glClearColorIiEXT(int r, int g, int b, int a)
/*     */   {
/*  76 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  77 */     long function_pointer = caps.glClearColorIiEXT;
/*  78 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  79 */     nglClearColorIiEXT(r, g, b, a, function_pointer);
/*     */   }
/*     */   static native void nglClearColorIiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glClearColorIuiEXT(int r, int g, int b, int a) {
/*  84 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  85 */     long function_pointer = caps.glClearColorIuiEXT;
/*  86 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  87 */     nglClearColorIuiEXT(r, g, b, a, function_pointer);
/*     */   }
/*     */   static native void nglClearColorIuiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glTexParameterIEXT(int target, int pname, IntBuffer params) {
/*  92 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  93 */     long function_pointer = caps.glTexParameterIivEXT;
/*  94 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  95 */     BufferChecks.checkBuffer(params, 4);
/*  96 */     nglTexParameterIivEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglTexParameterIivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glTexParameterIiEXT(int target, int pname, int param) {
/* 102 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 103 */     long function_pointer = caps.glTexParameterIivEXT;
/* 104 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 105 */     nglTexParameterIivEXT(target, pname, APIUtil.getInt(caps, param), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glTexParameterIuEXT(int target, int pname, IntBuffer params) {
/* 109 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 110 */     long function_pointer = caps.glTexParameterIuivEXT;
/* 111 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 112 */     BufferChecks.checkBuffer(params, 4);
/* 113 */     nglTexParameterIuivEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglTexParameterIuivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glTexParameterIuiEXT(int target, int pname, int param) {
/* 119 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 120 */     long function_pointer = caps.glTexParameterIuivEXT;
/* 121 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 122 */     nglTexParameterIuivEXT(target, pname, APIUtil.getInt(caps, param), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glGetTexParameterIEXT(int target, int pname, IntBuffer params) {
/* 126 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 127 */     long function_pointer = caps.glGetTexParameterIivEXT;
/* 128 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 129 */     BufferChecks.checkBuffer(params, 4);
/* 130 */     nglGetTexParameterIivEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetTexParameterIivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetTexParameterIiEXT(int target, int pname) {
/* 136 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 137 */     long function_pointer = caps.glGetTexParameterIivEXT;
/* 138 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 139 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 140 */     nglGetTexParameterIivEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 141 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetTexParameterIuEXT(int target, int pname, IntBuffer params) {
/* 145 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 146 */     long function_pointer = caps.glGetTexParameterIuivEXT;
/* 147 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 148 */     BufferChecks.checkBuffer(params, 4);
/* 149 */     nglGetTexParameterIuivEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetTexParameterIuivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetTexParameterIuiEXT(int target, int pname) {
/* 155 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 156 */     long function_pointer = caps.glGetTexParameterIuivEXT;
/* 157 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 158 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 159 */     nglGetTexParameterIuivEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 160 */     return params.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTTextureInteger
 * JD-Core Version:    0.6.2
 */