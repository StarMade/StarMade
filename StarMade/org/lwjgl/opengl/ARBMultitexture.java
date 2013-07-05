/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import org.lwjgl.BufferChecks;
/*     */ 
/*     */ public final class ARBMultitexture
/*     */ {
/*     */   public static final int GL_TEXTURE0_ARB = 33984;
/*     */   public static final int GL_TEXTURE1_ARB = 33985;
/*     */   public static final int GL_TEXTURE2_ARB = 33986;
/*     */   public static final int GL_TEXTURE3_ARB = 33987;
/*     */   public static final int GL_TEXTURE4_ARB = 33988;
/*     */   public static final int GL_TEXTURE5_ARB = 33989;
/*     */   public static final int GL_TEXTURE6_ARB = 33990;
/*     */   public static final int GL_TEXTURE7_ARB = 33991;
/*     */   public static final int GL_TEXTURE8_ARB = 33992;
/*     */   public static final int GL_TEXTURE9_ARB = 33993;
/*     */   public static final int GL_TEXTURE10_ARB = 33994;
/*     */   public static final int GL_TEXTURE11_ARB = 33995;
/*     */   public static final int GL_TEXTURE12_ARB = 33996;
/*     */   public static final int GL_TEXTURE13_ARB = 33997;
/*     */   public static final int GL_TEXTURE14_ARB = 33998;
/*     */   public static final int GL_TEXTURE15_ARB = 33999;
/*     */   public static final int GL_TEXTURE16_ARB = 34000;
/*     */   public static final int GL_TEXTURE17_ARB = 34001;
/*     */   public static final int GL_TEXTURE18_ARB = 34002;
/*     */   public static final int GL_TEXTURE19_ARB = 34003;
/*     */   public static final int GL_TEXTURE20_ARB = 34004;
/*     */   public static final int GL_TEXTURE21_ARB = 34005;
/*     */   public static final int GL_TEXTURE22_ARB = 34006;
/*     */   public static final int GL_TEXTURE23_ARB = 34007;
/*     */   public static final int GL_TEXTURE24_ARB = 34008;
/*     */   public static final int GL_TEXTURE25_ARB = 34009;
/*     */   public static final int GL_TEXTURE26_ARB = 34010;
/*     */   public static final int GL_TEXTURE27_ARB = 34011;
/*     */   public static final int GL_TEXTURE28_ARB = 34012;
/*     */   public static final int GL_TEXTURE29_ARB = 34013;
/*     */   public static final int GL_TEXTURE30_ARB = 34014;
/*     */   public static final int GL_TEXTURE31_ARB = 34015;
/*     */   public static final int GL_ACTIVE_TEXTURE_ARB = 34016;
/*     */   public static final int GL_CLIENT_ACTIVE_TEXTURE_ARB = 34017;
/*     */   public static final int GL_MAX_TEXTURE_UNITS_ARB = 34018;
/*     */ 
/*     */   public static void glClientActiveTextureARB(int texture)
/*     */   {
/*  49 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  50 */     long function_pointer = caps.glClientActiveTextureARB;
/*  51 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  52 */     nglClientActiveTextureARB(texture, function_pointer);
/*     */   }
/*     */   static native void nglClientActiveTextureARB(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glActiveTextureARB(int texture) {
/*  57 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  58 */     long function_pointer = caps.glActiveTextureARB;
/*  59 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  60 */     nglActiveTextureARB(texture, function_pointer);
/*     */   }
/*     */   static native void nglActiveTextureARB(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord1fARB(int target, float s) {
/*  65 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  66 */     long function_pointer = caps.glMultiTexCoord1fARB;
/*  67 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  68 */     nglMultiTexCoord1fARB(target, s, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord1fARB(int paramInt, float paramFloat, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord1dARB(int target, double s) {
/*  73 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  74 */     long function_pointer = caps.glMultiTexCoord1dARB;
/*  75 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  76 */     nglMultiTexCoord1dARB(target, s, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord1dARB(int paramInt, double paramDouble, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord1iARB(int target, int s) {
/*  81 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  82 */     long function_pointer = caps.glMultiTexCoord1iARB;
/*  83 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  84 */     nglMultiTexCoord1iARB(target, s, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord1iARB(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord1sARB(int target, short s) {
/*  89 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  90 */     long function_pointer = caps.glMultiTexCoord1sARB;
/*  91 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  92 */     nglMultiTexCoord1sARB(target, s, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord1sARB(int paramInt, short paramShort, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord2fARB(int target, float s, float t) {
/*  97 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  98 */     long function_pointer = caps.glMultiTexCoord2fARB;
/*  99 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 100 */     nglMultiTexCoord2fARB(target, s, t, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord2fARB(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord2dARB(int target, double s, double t) {
/* 105 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 106 */     long function_pointer = caps.glMultiTexCoord2dARB;
/* 107 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 108 */     nglMultiTexCoord2dARB(target, s, t, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord2dARB(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord2iARB(int target, int s, int t) {
/* 113 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 114 */     long function_pointer = caps.glMultiTexCoord2iARB;
/* 115 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 116 */     nglMultiTexCoord2iARB(target, s, t, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord2iARB(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord2sARB(int target, short s, short t) {
/* 121 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 122 */     long function_pointer = caps.glMultiTexCoord2sARB;
/* 123 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 124 */     nglMultiTexCoord2sARB(target, s, t, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord2sARB(int paramInt, short paramShort1, short paramShort2, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord3fARB(int target, float s, float t, float r) {
/* 129 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 130 */     long function_pointer = caps.glMultiTexCoord3fARB;
/* 131 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 132 */     nglMultiTexCoord3fARB(target, s, t, r, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord3fARB(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord3dARB(int target, double s, double t, double r) {
/* 137 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 138 */     long function_pointer = caps.glMultiTexCoord3dARB;
/* 139 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 140 */     nglMultiTexCoord3dARB(target, s, t, r, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord3dARB(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord3iARB(int target, int s, int t, int r) {
/* 145 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 146 */     long function_pointer = caps.glMultiTexCoord3iARB;
/* 147 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 148 */     nglMultiTexCoord3iARB(target, s, t, r, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord3iARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord3sARB(int target, short s, short t, short r) {
/* 153 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 154 */     long function_pointer = caps.glMultiTexCoord3sARB;
/* 155 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 156 */     nglMultiTexCoord3sARB(target, s, t, r, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord3sARB(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord4fARB(int target, float s, float t, float r, float q) {
/* 161 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 162 */     long function_pointer = caps.glMultiTexCoord4fARB;
/* 163 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 164 */     nglMultiTexCoord4fARB(target, s, t, r, q, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord4fARB(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord4dARB(int target, double s, double t, double r, double q) {
/* 169 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 170 */     long function_pointer = caps.glMultiTexCoord4dARB;
/* 171 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 172 */     nglMultiTexCoord4dARB(target, s, t, r, q, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord4dARB(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord4iARB(int target, int s, int t, int r, int q) {
/* 177 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 178 */     long function_pointer = caps.glMultiTexCoord4iARB;
/* 179 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 180 */     nglMultiTexCoord4iARB(target, s, t, r, q, function_pointer);
/*     */   }
/*     */   static native void nglMultiTexCoord4iARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*     */ 
/*     */   public static void glMultiTexCoord4sARB(int target, short s, short t, short r, short q) {
/* 185 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 186 */     long function_pointer = caps.glMultiTexCoord4sARB;
/* 187 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 188 */     nglMultiTexCoord4sARB(target, s, t, r, q, function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglMultiTexCoord4sARB(int paramInt, short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBMultitexture
 * JD-Core Version:    0.6.2
 */