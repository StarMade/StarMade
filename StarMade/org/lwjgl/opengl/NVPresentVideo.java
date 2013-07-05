/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class NVPresentVideo
/*     */ {
/*     */   public static final int GL_FRAME_NV = 36390;
/*     */   public static final int FIELDS_NV = 36391;
/*     */   public static final int GL_CURRENT_TIME_NV = 36392;
/*     */   public static final int GL_NUM_FILL_STREAMS_NV = 36393;
/*     */   public static final int GL_PRESENT_TIME_NV = 36394;
/*     */   public static final int GL_PRESENT_DURATION_NV = 36395;
/*     */   public static final int GL_NUM_VIDEO_SLOTS_NV = 8432;
/*     */ 
/*     */   public static void glPresentFrameKeyedNV(int video_slot, long minPresentTime, int beginPresentTimeId, int presentDurationId, int type, int target0, int fill0, int key0, int target1, int fill1, int key1)
/*     */   {
/*  38 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  39 */     long function_pointer = caps.glPresentFrameKeyedNV;
/*  40 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  41 */     nglPresentFrameKeyedNV(video_slot, minPresentTime, beginPresentTimeId, presentDurationId, type, target0, fill0, key0, target1, fill1, key1, function_pointer);
/*     */   }
/*     */   static native void nglPresentFrameKeyedNV(int paramInt1, long paramLong1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong2);
/*     */ 
/*     */   public static void glPresentFrameDualFillNV(int video_slot, long minPresentTime, int beginPresentTimeId, int presentDurationId, int type, int target0, int fill0, int target1, int fill1, int target2, int fill2, int target3, int fill3) {
/*  46 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  47 */     long function_pointer = caps.glPresentFrameDualFillNV;
/*  48 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  49 */     nglPresentFrameDualFillNV(video_slot, minPresentTime, beginPresentTimeId, presentDurationId, type, target0, fill0, target1, fill1, target2, fill2, target3, fill3, function_pointer);
/*     */   }
/*     */   static native void nglPresentFrameDualFillNV(int paramInt1, long paramLong1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, long paramLong2);
/*     */ 
/*     */   public static void glGetVideoNV(int video_slot, int pname, IntBuffer params) {
/*  54 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  55 */     long function_pointer = caps.glGetVideoivNV;
/*  56 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  57 */     BufferChecks.checkBuffer(params, 1);
/*  58 */     nglGetVideoivNV(video_slot, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetVideoivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetVideoiNV(int video_slot, int pname) {
/*  64 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  65 */     long function_pointer = caps.glGetVideoivNV;
/*  66 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  67 */     IntBuffer params = APIUtil.getBufferInt(caps);
/*  68 */     nglGetVideoivNV(video_slot, pname, MemoryUtil.getAddress(params), function_pointer);
/*  69 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetVideouNV(int video_slot, int pname, IntBuffer params) {
/*  73 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  74 */     long function_pointer = caps.glGetVideouivNV;
/*  75 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  76 */     BufferChecks.checkBuffer(params, 1);
/*  77 */     nglGetVideouivNV(video_slot, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetVideouivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetVideouiNV(int video_slot, int pname) {
/*  83 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  84 */     long function_pointer = caps.glGetVideouivNV;
/*  85 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  86 */     IntBuffer params = APIUtil.getBufferInt(caps);
/*  87 */     nglGetVideouivNV(video_slot, pname, MemoryUtil.getAddress(params), function_pointer);
/*  88 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetVideoNV(int video_slot, int pname, LongBuffer params) {
/*  92 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  93 */     long function_pointer = caps.glGetVideoi64vNV;
/*  94 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  95 */     BufferChecks.checkBuffer(params, 1);
/*  96 */     nglGetVideoi64vNV(video_slot, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetVideoi64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static long glGetVideoi64NV(int video_slot, int pname) {
/* 102 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 103 */     long function_pointer = caps.glGetVideoi64vNV;
/* 104 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 105 */     LongBuffer params = APIUtil.getBufferLong(caps);
/* 106 */     nglGetVideoi64vNV(video_slot, pname, MemoryUtil.getAddress(params), function_pointer);
/* 107 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetVideouNV(int video_slot, int pname, LongBuffer params) {
/* 111 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 112 */     long function_pointer = caps.glGetVideoui64vNV;
/* 113 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 114 */     BufferChecks.checkBuffer(params, 1);
/* 115 */     nglGetVideoui64vNV(video_slot, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetVideoui64vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static long glGetVideoui64NV(int video_slot, int pname) {
/* 121 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 122 */     long function_pointer = caps.glGetVideoui64vNV;
/* 123 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 124 */     LongBuffer params = APIUtil.getBufferLong(caps);
/* 125 */     nglGetVideoui64vNV(video_slot, pname, MemoryUtil.getAddress(params), function_pointer);
/* 126 */     return params.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVPresentVideo
 * JD-Core Version:    0.6.2
 */