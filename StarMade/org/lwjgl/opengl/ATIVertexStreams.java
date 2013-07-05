/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import org.lwjgl.BufferChecks;
/*     */ 
/*     */ public final class ATIVertexStreams
/*     */ {
/*     */   public static final int GL_MAX_VERTEX_STREAMS_ATI = 34667;
/*     */   public static final int GL_VERTEX_SOURCE_ATI = 34668;
/*     */   public static final int GL_VERTEX_STREAM0_ATI = 34669;
/*     */   public static final int GL_VERTEX_STREAM1_ATI = 34670;
/*     */   public static final int GL_VERTEX_STREAM2_ATI = 34671;
/*     */   public static final int GL_VERTEX_STREAM3_ATI = 34672;
/*     */   public static final int GL_VERTEX_STREAM4_ATI = 34673;
/*     */   public static final int GL_VERTEX_STREAM5_ATI = 34674;
/*     */   public static final int GL_VERTEX_STREAM6_ATI = 34675;
/*     */   public static final int GL_VERTEX_STREAM7_ATI = 34676;
/*     */ 
/*     */   public static void glVertexStream2fATI(int stream, float x, float y)
/*     */   {
/*  24 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  25 */     long function_pointer = caps.glVertexStream2fATI;
/*  26 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  27 */     nglVertexStream2fATI(stream, x, y, function_pointer);
/*     */   }
/*     */   static native void nglVertexStream2fATI(int paramInt, float paramFloat1, float paramFloat2, long paramLong);
/*     */ 
/*     */   public static void glVertexStream2dATI(int stream, double x, double y) {
/*  32 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  33 */     long function_pointer = caps.glVertexStream2dATI;
/*  34 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  35 */     nglVertexStream2dATI(stream, x, y, function_pointer);
/*     */   }
/*     */   static native void nglVertexStream2dATI(int paramInt, double paramDouble1, double paramDouble2, long paramLong);
/*     */ 
/*     */   public static void glVertexStream2iATI(int stream, int x, int y) {
/*  40 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  41 */     long function_pointer = caps.glVertexStream2iATI;
/*  42 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  43 */     nglVertexStream2iATI(stream, x, y, function_pointer);
/*     */   }
/*     */   static native void nglVertexStream2iATI(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*     */ 
/*     */   public static void glVertexStream2sATI(int stream, short x, short y) {
/*  48 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  49 */     long function_pointer = caps.glVertexStream2sATI;
/*  50 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  51 */     nglVertexStream2sATI(stream, x, y, function_pointer);
/*     */   }
/*     */   static native void nglVertexStream2sATI(int paramInt, short paramShort1, short paramShort2, long paramLong);
/*     */ 
/*     */   public static void glVertexStream3fATI(int stream, float x, float y, float z) {
/*  56 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  57 */     long function_pointer = caps.glVertexStream3fATI;
/*  58 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  59 */     nglVertexStream3fATI(stream, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglVertexStream3fATI(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*     */ 
/*     */   public static void glVertexStream3dATI(int stream, double x, double y, double z) {
/*  64 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  65 */     long function_pointer = caps.glVertexStream3dATI;
/*  66 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  67 */     nglVertexStream3dATI(stream, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglVertexStream3dATI(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*     */ 
/*     */   public static void glVertexStream3iATI(int stream, int x, int y, int z) {
/*  72 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  73 */     long function_pointer = caps.glVertexStream3iATI;
/*  74 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  75 */     nglVertexStream3iATI(stream, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglVertexStream3iATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glVertexStream3sATI(int stream, short x, short y, short z) {
/*  80 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  81 */     long function_pointer = caps.glVertexStream3sATI;
/*  82 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  83 */     nglVertexStream3sATI(stream, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglVertexStream3sATI(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*     */ 
/*     */   public static void glVertexStream4fATI(int stream, float x, float y, float z, float w) {
/*  88 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  89 */     long function_pointer = caps.glVertexStream4fATI;
/*  90 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  91 */     nglVertexStream4fATI(stream, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglVertexStream4fATI(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);
/*     */ 
/*     */   public static void glVertexStream4dATI(int stream, double x, double y, double z, double w) {
/*  96 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  97 */     long function_pointer = caps.glVertexStream4dATI;
/*  98 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  99 */     nglVertexStream4dATI(stream, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglVertexStream4dATI(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, long paramLong);
/*     */ 
/*     */   public static void glVertexStream4iATI(int stream, int x, int y, int z, int w) {
/* 104 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 105 */     long function_pointer = caps.glVertexStream4iATI;
/* 106 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 107 */     nglVertexStream4iATI(stream, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglVertexStream4iATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*     */ 
/*     */   public static void glVertexStream4sATI(int stream, short x, short y, short z, short w) {
/* 112 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 113 */     long function_pointer = caps.glVertexStream4sATI;
/* 114 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 115 */     nglVertexStream4sATI(stream, x, y, z, w, function_pointer);
/*     */   }
/*     */   static native void nglVertexStream4sATI(int paramInt, short paramShort1, short paramShort2, short paramShort3, short paramShort4, long paramLong);
/*     */ 
/*     */   public static void glNormalStream3bATI(int stream, byte x, byte y, byte z) {
/* 120 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 121 */     long function_pointer = caps.glNormalStream3bATI;
/* 122 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 123 */     nglNormalStream3bATI(stream, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglNormalStream3bATI(int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, long paramLong);
/*     */ 
/*     */   public static void glNormalStream3fATI(int stream, float x, float y, float z) {
/* 128 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 129 */     long function_pointer = caps.glNormalStream3fATI;
/* 130 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 131 */     nglNormalStream3fATI(stream, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglNormalStream3fATI(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
/*     */ 
/*     */   public static void glNormalStream3dATI(int stream, double x, double y, double z) {
/* 136 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 137 */     long function_pointer = caps.glNormalStream3dATI;
/* 138 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 139 */     nglNormalStream3dATI(stream, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglNormalStream3dATI(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, long paramLong);
/*     */ 
/*     */   public static void glNormalStream3iATI(int stream, int x, int y, int z) {
/* 144 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 145 */     long function_pointer = caps.glNormalStream3iATI;
/* 146 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 147 */     nglNormalStream3iATI(stream, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglNormalStream3iATI(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glNormalStream3sATI(int stream, short x, short y, short z) {
/* 152 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 153 */     long function_pointer = caps.glNormalStream3sATI;
/* 154 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 155 */     nglNormalStream3sATI(stream, x, y, z, function_pointer);
/*     */   }
/*     */   static native void nglNormalStream3sATI(int paramInt, short paramShort1, short paramShort2, short paramShort3, long paramLong);
/*     */ 
/*     */   public static void glClientActiveVertexStreamATI(int stream) {
/* 160 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 161 */     long function_pointer = caps.glClientActiveVertexStreamATI;
/* 162 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 163 */     nglClientActiveVertexStreamATI(stream, function_pointer);
/*     */   }
/*     */   static native void nglClientActiveVertexStreamATI(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glVertexBlendEnvfATI(int pname, float param) {
/* 168 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 169 */     long function_pointer = caps.glVertexBlendEnvfATI;
/* 170 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 171 */     nglVertexBlendEnvfATI(pname, param, function_pointer);
/*     */   }
/*     */   static native void nglVertexBlendEnvfATI(int paramInt, float paramFloat, long paramLong);
/*     */ 
/*     */   public static void glVertexBlendEnviATI(int pname, int param) {
/* 176 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 177 */     long function_pointer = caps.glVertexBlendEnviATI;
/* 178 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 179 */     nglVertexBlendEnviATI(pname, param, function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglVertexBlendEnviATI(int paramInt1, int paramInt2, long paramLong);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ATIVertexStreams
 * JD-Core Version:    0.6.2
 */