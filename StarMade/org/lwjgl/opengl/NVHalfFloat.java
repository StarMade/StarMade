/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ShortBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class NVHalfFloat
/*     */ {
/*     */   public static final int GL_HALF_FLOAT_NV = 5131;
/*     */ 
/*     */   public static void glVertex2hNV(short x, short y)
/*     */   {
/*  22 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  23 */     long function_pointer = caps.glVertex2hNV;
/*  24 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  25 */     nglVertex2hNV(x, y, function_pointer);
/*     */   }
/*     */   static native void nglVertex2hNV(short paramShort1, short paramShort2, long paramLong);
/*     */ 
/*     */   public static void glVertex3hNV(short x, short y, short z) {
/*  30 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  31 */     long function_pointer = caps.glVertex3hNV;
/*  32 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  33 */     nglVertex3hNV(x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglVertex3hNV(short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*     */ 
/*     */   public static void glVertex4hNV(short x, short y, short z, short w) {
/*  38 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  39 */     long function_pointer = caps.glVertex4hNV;
/*  40 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  41 */     nglVertex4hNV(x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglVertex4hNV(short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/*     */ 
/*     */   public static void glNormal3hNV(short nx, short ny, short nz) {
/*  46 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  47 */     long function_pointer = caps.glNormal3hNV;
/*  48 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  49 */     nglNormal3hNV(nx, ny, nz, function_pointer);
/*     */   }
/*     */   static native void nglNormal3hNV(short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*     */ 
/*     */   public static void glColor3hNV(short red, short green, short blue) {
/*  54 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  55 */     long function_pointer = caps.glColor3hNV;
/*  56 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  57 */     nglColor3hNV(red, green, blue, function_pointer);
/*     */   }
/*     */   static native void nglColor3hNV(short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*     */ 
/*     */   public static void glColor4hNV(short red, short green, short blue, short alpha) {
/*  62 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  63 */     long function_pointer = caps.glColor4hNV;
/*  64 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  65 */     nglColor4hNV(red, green, blue, alpha, function_pointer);
/*     */   }
/*     */   static native void nglColor4hNV(short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/*     */ 
/*     */   public static void glTexCoord1hNV(short s) {
/*  70 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  71 */     long function_pointer = caps.glTexCoord1hNV;
/*  72 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  73 */     nglTexCoord1hNV(s, function_pointer);
/*     */   }
/*     */   static native void nglTexCoord1hNV(short paramShort, long paramLong);
/*     */ 
/*     */   public static void glTexCoord2hNV(short s, short t) {
/*  78 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  79 */     long function_pointer = caps.glTexCoord2hNV;
/*  80 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  81 */     nglTexCoord2hNV(s, t, function_pointer);
/*     */   }
/*     */   static native void nglTexCoord2hNV(short paramShort1, short paramShort2, long paramLong);
/*     */ 
/*     */   public static void glTexCoord3hNV(short s, short t, short r) {
/*  86 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  87 */     long function_pointer = caps.glTexCoord3hNV;
/*  88 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  89 */     nglTexCoord3hNV(s, t, r, function_pointer);
/*     */   }
/*     */   static native void nglTexCoord3hNV(short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*     */ 
/*     */   public static void glTexCoord4hNV(short s, short t, short r, short q) {
/*  94 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  95 */     long function_pointer = caps.glTexCoord4hNV;
/*  96 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  97 */     nglTexCoord4hNV(s, t, r, q, function_pointer);
/*     */   }
/*     */   static native void nglTexCoord4hNV(short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord1hNV(int target, short s) {
/* 102 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 103 */     long function_pointer = caps.glMultiTexCoord1hNV;
/* 104 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 105 */     nglMultiTexCoord1hNV(target, s, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord1hNV(int paramInt, short paramShort, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord2hNV(int target, short s, short t) {
/* 110 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 111 */     long function_pointer = caps.glMultiTexCoord2hNV;
/* 112 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 113 */     nglMultiTexCoord2hNV(target, s, t, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord2hNV(int paramInt, short paramShort1, short paramShort2, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord3hNV(int target, short s, short t, short r) {
/* 118 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 119 */     long function_pointer = caps.glMultiTexCoord3hNV;
/* 120 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 121 */     nglMultiTexCoord3hNV(target, s, t, r, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord3hNV(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord4hNV(int target, short s, short t, short r, short q) {
/* 126 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 127 */     long function_pointer = caps.glMultiTexCoord4hNV;
/* 128 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 129 */     nglMultiTexCoord4hNV(target, s, t, r, q, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord4hNV(int paramInt, short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/*     */ 
/*     */   public static void glFogCoordhNV(short fog) {
/* 134 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 135 */     long function_pointer = caps.glFogCoordhNV;
/* 136 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 137 */     nglFogCoordhNV(fog, function_pointer);
/*     */   }
/*     */   static native void nglFogCoordhNV(short paramShort, long paramLong);
/*     */ 
/*     */   public static void glSecondaryColor3hNV(short red, short green, short blue) {
/* 142 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 143 */     long function_pointer = caps.glSecondaryColor3hNV;
/* 144 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 145 */     nglSecondaryColor3hNV(red, green, blue, function_pointer);
/*     */   }
/*     */   static native void nglSecondaryColor3hNV(short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*     */ 
/*     */   public static void glVertexWeighthNV(short weight) {
/* 150 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 151 */     long function_pointer = caps.glVertexWeighthNV;
/* 152 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 153 */     nglVertexWeighthNV(weight, function_pointer);
/*     */   }
/*     */   static native void nglVertexWeighthNV(short paramShort, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib1hNV(int index, short x) {
/* 158 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 159 */     long function_pointer = caps.glVertexAttrib1hNV;
/* 160 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 161 */     nglVertexAttrib1hNV(index, x, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib1hNV(int paramInt, short paramShort, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib2hNV(int index, short x, short y) {
/* 166 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 167 */     long function_pointer = caps.glVertexAttrib2hNV;
/* 168 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 169 */     nglVertexAttrib2hNV(index, x, y, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib2hNV(int paramInt, short paramShort1, short paramShort2, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib3hNV(int index, short x, short y, short z) {
/* 174 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 175 */     long function_pointer = caps.glVertexAttrib3hNV;
/* 176 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 177 */     nglVertexAttrib3hNV(index, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib3hNV(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*     */ 
/*     */   public static void glVertexAttrib4hNV(int index, short x, short y, short z, short w) {
/* 182 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 183 */     long function_pointer = caps.glVertexAttrib4hNV;
/* 184 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 185 */     nglVertexAttrib4hNV(index, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglVertexAttrib4hNV(int paramInt, short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/*     */ 
/*     */   public static void glVertexAttribs1NV(int index, ShortBuffer attribs) {
/* 190 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 191 */     long function_pointer = caps.glVertexAttribs1hvNV;
/* 192 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 193 */     BufferChecks.checkDirect(attribs);
/* 194 */     nglVertexAttribs1hvNV(index, attribs.remaining(), MemoryUtil.getAddress(attribs), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribs1hvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribs2NV(int index, ShortBuffer attribs) {
/* 199 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 200 */     long function_pointer = caps.glVertexAttribs2hvNV;
/* 201 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 202 */     BufferChecks.checkDirect(attribs);
/* 203 */     nglVertexAttribs2hvNV(index, attribs.remaining() >> 1, MemoryUtil.getAddress(attribs), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribs2hvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribs3NV(int index, ShortBuffer attribs) {
/* 208 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 209 */     long function_pointer = caps.glVertexAttribs3hvNV;
/* 210 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 211 */     BufferChecks.checkDirect(attribs);
/* 212 */     nglVertexAttribs3hvNV(index, attribs.remaining() / 3, MemoryUtil.getAddress(attribs), function_pointer);
/*     */   }
/*     */   static native void nglVertexAttribs3hvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVertexAttribs4NV(int index, ShortBuffer attribs) {
/* 217 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 218 */     long function_pointer = caps.glVertexAttribs4hvNV;
/* 219 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 220 */     BufferChecks.checkDirect(attribs);
/* 221 */     nglVertexAttribs4hvNV(index, attribs.remaining() >> 2, MemoryUtil.getAddress(attribs), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglVertexAttribs4hvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVHalfFloat
 * JD-Core Version:    0.6.2
 */