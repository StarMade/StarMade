/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class ARBDebugOutput
/*     */ {
/*     */   public static final int GL_DEBUG_OUTPUT_SYNCHRONOUS_ARB = 33346;
/*     */   public static final int GL_MAX_DEBUG_MESSAGE_LENGTH_ARB = 37187;
/*     */   public static final int GL_MAX_DEBUG_LOGGED_MESSAGES_ARB = 37188;
/*     */   public static final int GL_DEBUG_LOGGED_MESSAGES_ARB = 37189;
/*     */   public static final int GL_DEBUG_NEXT_LOGGED_MESSAGE_LENGTH_ARB = 33347;
/*     */   public static final int GL_DEBUG_CALLBACK_FUNCTION_ARB = 33348;
/*     */   public static final int GL_DEBUG_CALLBACK_USER_PARAM_ARB = 33349;
/*     */   public static final int GL_DEBUG_SOURCE_API_ARB = 33350;
/*     */   public static final int GL_DEBUG_SOURCE_WINDOW_SYSTEM_ARB = 33351;
/*     */   public static final int GL_DEBUG_SOURCE_SHADER_COMPILER_ARB = 33352;
/*     */   public static final int GL_DEBUG_SOURCE_THIRD_PARTY_ARB = 33353;
/*     */   public static final int GL_DEBUG_SOURCE_APPLICATION_ARB = 33354;
/*     */   public static final int GL_DEBUG_SOURCE_OTHER_ARB = 33355;
/*     */   public static final int GL_DEBUG_TYPE_ERROR_ARB = 33356;
/*     */   public static final int GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR_ARB = 33357;
/*     */   public static final int GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR_ARB = 33358;
/*     */   public static final int GL_DEBUG_TYPE_PORTABILITY_ARB = 33359;
/*     */   public static final int GL_DEBUG_TYPE_PERFORMANCE_ARB = 33360;
/*     */   public static final int GL_DEBUG_TYPE_OTHER_ARB = 33361;
/*     */   public static final int GL_DEBUG_SEVERITY_HIGH_ARB = 37190;
/*     */   public static final int GL_DEBUG_SEVERITY_MEDIUM_ARB = 37191;
/*     */   public static final int GL_DEBUG_SEVERITY_LOW_ARB = 37192;
/*     */ 
/*     */   public static void glDebugMessageControlARB(int source, int type, int severity, IntBuffer ids, boolean enabled)
/*     */   {
/*  68 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  69 */     long function_pointer = caps.glDebugMessageControlARB;
/*  70 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  71 */     if (ids != null)
/*  72 */       BufferChecks.checkDirect(ids);
/*  73 */     nglDebugMessageControlARB(source, type, severity, ids == null ? 0 : ids.remaining(), MemoryUtil.getAddressSafe(ids), enabled, function_pointer);
/*     */   }
/*     */   static native void nglDebugMessageControlARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, boolean paramBoolean, long paramLong2);
/*     */ 
/*     */   public static void glDebugMessageInsertARB(int source, int type, int id, int severity, ByteBuffer buf) {
/*  78 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  79 */     long function_pointer = caps.glDebugMessageInsertARB;
/*  80 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  81 */     BufferChecks.checkDirect(buf);
/*  82 */     nglDebugMessageInsertARB(source, type, id, severity, buf.remaining(), MemoryUtil.getAddress(buf), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglDebugMessageInsertARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDebugMessageInsertARB(int source, int type, int id, int severity, CharSequence buf) {
/*  88 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  89 */     long function_pointer = caps.glDebugMessageInsertARB;
/*  90 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  91 */     nglDebugMessageInsertARB(source, type, id, severity, buf.length(), APIUtil.getBuffer(caps, buf), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glDebugMessageCallbackARB(ARBDebugOutputCallback callback)
/*     */   {
/* 102 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 103 */     long function_pointer = caps.glDebugMessageCallbackARB;
/* 104 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 105 */     long userParam = callback == null ? 0L : CallbackUtil.createGlobalRef(callback.getHandler());
/* 106 */     CallbackUtil.registerContextCallbackARB(userParam);
/* 107 */     nglDebugMessageCallbackARB(callback == null ? 0L : callback.getPointer(), userParam, function_pointer);
/*     */   }
/*     */   static native void nglDebugMessageCallbackARB(long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static int glGetDebugMessageLogARB(int count, IntBuffer sources, IntBuffer types, IntBuffer ids, IntBuffer severities, IntBuffer lengths, ByteBuffer messageLog) {
/* 112 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 113 */     long function_pointer = caps.glGetDebugMessageLogARB;
/* 114 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 115 */     if (sources != null)
/* 116 */       BufferChecks.checkBuffer(sources, count);
/* 117 */     if (types != null)
/* 118 */       BufferChecks.checkBuffer(types, count);
/* 119 */     if (ids != null)
/* 120 */       BufferChecks.checkBuffer(ids, count);
/* 121 */     if (severities != null)
/* 122 */       BufferChecks.checkBuffer(severities, count);
/* 123 */     if (lengths != null)
/* 124 */       BufferChecks.checkBuffer(lengths, count);
/* 125 */     if (messageLog != null)
/* 126 */       BufferChecks.checkDirect(messageLog);
/* 127 */     int __result = nglGetDebugMessageLogARB(count, messageLog == null ? 0 : messageLog.remaining(), MemoryUtil.getAddressSafe(sources), MemoryUtil.getAddressSafe(types), MemoryUtil.getAddressSafe(ids), MemoryUtil.getAddressSafe(severities), MemoryUtil.getAddressSafe(lengths), MemoryUtil.getAddressSafe(messageLog), function_pointer);
/* 128 */     return __result;
/*     */   }
/*     */ 
/*     */   static native int nglGetDebugMessageLogARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBDebugOutput
 * JD-Core Version:    0.6.2
 */