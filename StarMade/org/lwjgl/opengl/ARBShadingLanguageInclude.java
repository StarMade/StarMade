/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class ARBShadingLanguageInclude
/*     */ {
/*     */   public static final int GL_SHADER_INCLUDE_ARB = 36270;
/*     */   public static final int GL_NAMED_STRING_LENGTH_ARB = 36329;
/*     */   public static final int GL_NAMED_STRING_TYPE_ARB = 36330;
/*     */ 
/*     */   public static void glNamedStringARB(int type, ByteBuffer name, ByteBuffer string)
/*     */   {
/*  24 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  25 */     long function_pointer = caps.glNamedStringARB;
/*  26 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  27 */     BufferChecks.checkDirect(name);
/*  28 */     BufferChecks.checkDirect(string);
/*  29 */     nglNamedStringARB(type, name.remaining(), MemoryUtil.getAddress(name), string.remaining(), MemoryUtil.getAddress(string), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglNamedStringARB(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glNamedStringARB(int type, CharSequence name, CharSequence string) {
/*  35 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  36 */     long function_pointer = caps.glNamedStringARB;
/*  37 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  38 */     nglNamedStringARB(type, name.length(), APIUtil.getBuffer(caps, name), string.length(), APIUtil.getBuffer(caps, string, name.length()), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glDeleteNamedStringARB(ByteBuffer name) {
/*  42 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  43 */     long function_pointer = caps.glDeleteNamedStringARB;
/*  44 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  45 */     BufferChecks.checkDirect(name);
/*  46 */     nglDeleteNamedStringARB(name.remaining(), MemoryUtil.getAddress(name), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglDeleteNamedStringARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDeleteNamedStringARB(CharSequence name) {
/*  52 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  53 */     long function_pointer = caps.glDeleteNamedStringARB;
/*  54 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  55 */     nglDeleteNamedStringARB(name.length(), APIUtil.getBuffer(caps, name), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glCompileShaderIncludeARB(int shader, int count, ByteBuffer path) {
/*  59 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  60 */     long function_pointer = caps.glCompileShaderIncludeARB;
/*  61 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  62 */     BufferChecks.checkDirect(path);
/*  63 */     BufferChecks.checkNullTerminated(path, count);
/*  64 */     nglCompileShaderIncludeARB(shader, count, MemoryUtil.getAddress(path), 0L, function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglCompileShaderIncludeARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glCompileShaderIncludeARB(int shader, CharSequence[] path) {
/*  70 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  71 */     long function_pointer = caps.glCompileShaderIncludeARB;
/*  72 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  73 */     BufferChecks.checkArray(path);
/*  74 */     nglCompileShaderIncludeARB2(shader, path.length, APIUtil.getBuffer(caps, path), APIUtil.getLengths(caps, path), function_pointer);
/*     */   }
/*     */   static native void nglCompileShaderIncludeARB2(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static boolean glIsNamedStringARB(ByteBuffer name) {
/*  79 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  80 */     long function_pointer = caps.glIsNamedStringARB;
/*  81 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  82 */     BufferChecks.checkDirect(name);
/*  83 */     boolean __result = nglIsNamedStringARB(name.remaining(), MemoryUtil.getAddress(name), function_pointer);
/*  84 */     return __result;
/*     */   }
/*     */ 
/*     */   static native boolean nglIsNamedStringARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static boolean glIsNamedStringARB(CharSequence name) {
/*  90 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  91 */     long function_pointer = caps.glIsNamedStringARB;
/*  92 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  93 */     boolean __result = nglIsNamedStringARB(name.length(), APIUtil.getBuffer(caps, name), function_pointer);
/*  94 */     return __result;
/*     */   }
/*     */ 
/*     */   public static void glGetNamedStringARB(ByteBuffer name, IntBuffer stringlen, ByteBuffer string) {
/*  98 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  99 */     long function_pointer = caps.glGetNamedStringARB;
/* 100 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 101 */     BufferChecks.checkDirect(name);
/* 102 */     if (stringlen != null)
/* 103 */       BufferChecks.checkBuffer(stringlen, 1);
/* 104 */     BufferChecks.checkDirect(string);
/* 105 */     nglGetNamedStringARB(name.remaining(), MemoryUtil.getAddress(name), string.remaining(), MemoryUtil.getAddressSafe(stringlen), MemoryUtil.getAddress(string), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetNamedStringARB(int paramInt1, long paramLong1, int paramInt2, long paramLong2, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static void glGetNamedStringARB(CharSequence name, IntBuffer stringlen, ByteBuffer string) {
/* 111 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 112 */     long function_pointer = caps.glGetNamedStringARB;
/* 113 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 114 */     if (stringlen != null)
/* 115 */       BufferChecks.checkBuffer(stringlen, 1);
/* 116 */     BufferChecks.checkDirect(string);
/* 117 */     nglGetNamedStringARB(name.length(), APIUtil.getBuffer(caps, name), string.remaining(), MemoryUtil.getAddressSafe(stringlen), MemoryUtil.getAddress(string), function_pointer);
/*     */   }
/*     */ 
/*     */   public static String glGetNamedStringARB(CharSequence name, int bufSize)
/*     */   {
/* 122 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 123 */     long function_pointer = caps.glGetNamedStringARB;
/* 124 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 125 */     IntBuffer string_length = APIUtil.getLengths(caps);
/* 126 */     ByteBuffer string = APIUtil.getBufferByte(caps, bufSize + name.length());
/* 127 */     nglGetNamedStringARB(name.length(), APIUtil.getBuffer(caps, name), bufSize, MemoryUtil.getAddress0(string_length), MemoryUtil.getAddress(string), function_pointer);
/* 128 */     string.limit(name.length() + string_length.get(0));
/* 129 */     return APIUtil.getString(caps, string);
/*     */   }
/*     */ 
/*     */   public static void glGetNamedStringARB(ByteBuffer name, int pname, IntBuffer params) {
/* 133 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 134 */     long function_pointer = caps.glGetNamedStringivARB;
/* 135 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 136 */     BufferChecks.checkDirect(name);
/* 137 */     BufferChecks.checkBuffer(params, 1);
/* 138 */     nglGetNamedStringivARB(name.remaining(), MemoryUtil.getAddress(name), pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetNamedStringivARB(int paramInt1, long paramLong1, int paramInt2, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glGetNamedStringiARB(CharSequence name, int pname, IntBuffer params) {
/* 144 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 145 */     long function_pointer = caps.glGetNamedStringivARB;
/* 146 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 147 */     BufferChecks.checkBuffer(params, 1);
/* 148 */     nglGetNamedStringivARB(name.length(), APIUtil.getBuffer(caps, name), pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   public static int glGetNamedStringiARB(CharSequence name, int pname)
/*     */   {
/* 153 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 154 */     long function_pointer = caps.glGetNamedStringivARB;
/* 155 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 156 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 157 */     nglGetNamedStringivARB(name.length(), APIUtil.getBuffer(caps, name), pname, MemoryUtil.getAddress(params), function_pointer);
/* 158 */     return params.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBShadingLanguageInclude
 * JD-Core Version:    0.6.2
 */