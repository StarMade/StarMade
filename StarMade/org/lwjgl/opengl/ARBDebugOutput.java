/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.BufferChecks;
/*   6:    */import org.lwjgl.MemoryUtil;
/*   7:    */
/*  41:    */public final class ARBDebugOutput
/*  42:    */{
/*  43:    */  public static final int GL_DEBUG_OUTPUT_SYNCHRONOUS_ARB = 33346;
/*  44:    */  public static final int GL_MAX_DEBUG_MESSAGE_LENGTH_ARB = 37187;
/*  45:    */  public static final int GL_MAX_DEBUG_LOGGED_MESSAGES_ARB = 37188;
/*  46:    */  public static final int GL_DEBUG_LOGGED_MESSAGES_ARB = 37189;
/*  47:    */  public static final int GL_DEBUG_NEXT_LOGGED_MESSAGE_LENGTH_ARB = 33347;
/*  48:    */  public static final int GL_DEBUG_CALLBACK_FUNCTION_ARB = 33348;
/*  49:    */  public static final int GL_DEBUG_CALLBACK_USER_PARAM_ARB = 33349;
/*  50:    */  public static final int GL_DEBUG_SOURCE_API_ARB = 33350;
/*  51:    */  public static final int GL_DEBUG_SOURCE_WINDOW_SYSTEM_ARB = 33351;
/*  52:    */  public static final int GL_DEBUG_SOURCE_SHADER_COMPILER_ARB = 33352;
/*  53:    */  public static final int GL_DEBUG_SOURCE_THIRD_PARTY_ARB = 33353;
/*  54:    */  public static final int GL_DEBUG_SOURCE_APPLICATION_ARB = 33354;
/*  55:    */  public static final int GL_DEBUG_SOURCE_OTHER_ARB = 33355;
/*  56:    */  public static final int GL_DEBUG_TYPE_ERROR_ARB = 33356;
/*  57:    */  public static final int GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR_ARB = 33357;
/*  58:    */  public static final int GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR_ARB = 33358;
/*  59:    */  public static final int GL_DEBUG_TYPE_PORTABILITY_ARB = 33359;
/*  60:    */  public static final int GL_DEBUG_TYPE_PERFORMANCE_ARB = 33360;
/*  61:    */  public static final int GL_DEBUG_TYPE_OTHER_ARB = 33361;
/*  62:    */  public static final int GL_DEBUG_SEVERITY_HIGH_ARB = 37190;
/*  63:    */  public static final int GL_DEBUG_SEVERITY_MEDIUM_ARB = 37191;
/*  64:    */  public static final int GL_DEBUG_SEVERITY_LOW_ARB = 37192;
/*  65:    */  
/*  66:    */  public static void glDebugMessageControlARB(int source, int type, int severity, IntBuffer ids, boolean enabled)
/*  67:    */  {
/*  68: 68 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  69: 69 */    long function_pointer = caps.glDebugMessageControlARB;
/*  70: 70 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  71: 71 */    if (ids != null)
/*  72: 72 */      BufferChecks.checkDirect(ids);
/*  73: 73 */    nglDebugMessageControlARB(source, type, severity, ids == null ? 0 : ids.remaining(), MemoryUtil.getAddressSafe(ids), enabled, function_pointer);
/*  74:    */  }
/*  75:    */  
/*  76:    */  static native void nglDebugMessageControlARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, boolean paramBoolean, long paramLong2);
/*  77:    */  
/*  78: 78 */  public static void glDebugMessageInsertARB(int source, int type, int id, int severity, ByteBuffer buf) { ContextCapabilities caps = GLContext.getCapabilities();
/*  79: 79 */    long function_pointer = caps.glDebugMessageInsertARB;
/*  80: 80 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  81: 81 */    BufferChecks.checkDirect(buf);
/*  82: 82 */    nglDebugMessageInsertARB(source, type, id, severity, buf.remaining(), MemoryUtil.getAddress(buf), function_pointer);
/*  83:    */  }
/*  84:    */  
/*  85:    */  static native void nglDebugMessageInsertARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*  86:    */  
/*  87:    */  public static void glDebugMessageInsertARB(int source, int type, int id, int severity, CharSequence buf) {
/*  88: 88 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  89: 89 */    long function_pointer = caps.glDebugMessageInsertARB;
/*  90: 90 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  91: 91 */    nglDebugMessageInsertARB(source, type, id, severity, buf.length(), APIUtil.getBuffer(caps, buf), function_pointer);
/*  92:    */  }
/*  93:    */  
/* 100:    */  public static void glDebugMessageCallbackARB(ARBDebugOutputCallback callback)
/* 101:    */  {
/* 102:102 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 103:103 */    long function_pointer = caps.glDebugMessageCallbackARB;
/* 104:104 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 105:105 */    long userParam = callback == null ? 0L : CallbackUtil.createGlobalRef(callback.getHandler());
/* 106:106 */    CallbackUtil.registerContextCallbackARB(userParam);
/* 107:107 */    nglDebugMessageCallbackARB(callback == null ? 0L : callback.getPointer(), userParam, function_pointer);
/* 108:    */  }
/* 109:    */  
/* 110:    */  static native void nglDebugMessageCallbackARB(long paramLong1, long paramLong2, long paramLong3);
/* 111:    */  
/* 112:112 */  public static int glGetDebugMessageLogARB(int count, IntBuffer sources, IntBuffer types, IntBuffer ids, IntBuffer severities, IntBuffer lengths, ByteBuffer messageLog) { ContextCapabilities caps = GLContext.getCapabilities();
/* 113:113 */    long function_pointer = caps.glGetDebugMessageLogARB;
/* 114:114 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 115:115 */    if (sources != null)
/* 116:116 */      BufferChecks.checkBuffer(sources, count);
/* 117:117 */    if (types != null)
/* 118:118 */      BufferChecks.checkBuffer(types, count);
/* 119:119 */    if (ids != null)
/* 120:120 */      BufferChecks.checkBuffer(ids, count);
/* 121:121 */    if (severities != null)
/* 122:122 */      BufferChecks.checkBuffer(severities, count);
/* 123:123 */    if (lengths != null)
/* 124:124 */      BufferChecks.checkBuffer(lengths, count);
/* 125:125 */    if (messageLog != null)
/* 126:126 */      BufferChecks.checkDirect(messageLog);
/* 127:127 */    int __result = nglGetDebugMessageLogARB(count, messageLog == null ? 0 : messageLog.remaining(), MemoryUtil.getAddressSafe(sources), MemoryUtil.getAddressSafe(types), MemoryUtil.getAddressSafe(ids), MemoryUtil.getAddressSafe(severities), MemoryUtil.getAddressSafe(lengths), MemoryUtil.getAddressSafe(messageLog), function_pointer);
/* 128:128 */    return __result;
/* 129:    */  }
/* 130:    */  
/* 131:    */  static native int nglGetDebugMessageLogARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7);
/* 132:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBDebugOutput
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */