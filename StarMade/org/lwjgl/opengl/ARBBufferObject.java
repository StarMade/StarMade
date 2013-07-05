/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public class ARBBufferObject
/*     */ {
/*     */   public static final int GL_STREAM_DRAW_ARB = 35040;
/*     */   public static final int GL_STREAM_READ_ARB = 35041;
/*     */   public static final int GL_STREAM_COPY_ARB = 35042;
/*     */   public static final int GL_STATIC_DRAW_ARB = 35044;
/*     */   public static final int GL_STATIC_READ_ARB = 35045;
/*     */   public static final int GL_STATIC_COPY_ARB = 35046;
/*     */   public static final int GL_DYNAMIC_DRAW_ARB = 35048;
/*     */   public static final int GL_DYNAMIC_READ_ARB = 35049;
/*     */   public static final int GL_DYNAMIC_COPY_ARB = 35050;
/*     */   public static final int GL_READ_ONLY_ARB = 35000;
/*     */   public static final int GL_WRITE_ONLY_ARB = 35001;
/*     */   public static final int GL_READ_WRITE_ARB = 35002;
/*     */   public static final int GL_BUFFER_SIZE_ARB = 34660;
/*     */   public static final int GL_BUFFER_USAGE_ARB = 34661;
/*     */   public static final int GL_BUFFER_ACCESS_ARB = 35003;
/*     */   public static final int GL_BUFFER_MAPPED_ARB = 35004;
/*     */   public static final int GL_BUFFER_MAP_POINTER_ARB = 35005;
/*     */ 
/*     */   public static void glBindBufferARB(int target, int buffer)
/*     */   {
/*  41 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  42 */     long function_pointer = caps.glBindBufferARB;
/*  43 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  44 */     StateTracker.bindBuffer(caps, target, buffer);
/*  45 */     nglBindBufferARB(target, buffer, function_pointer);
/*     */   }
/*     */   static native void nglBindBufferARB(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glDeleteBuffersARB(IntBuffer buffers) {
/*  50 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  51 */     long function_pointer = caps.glDeleteBuffersARB;
/*  52 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  53 */     BufferChecks.checkDirect(buffers);
/*  54 */     nglDeleteBuffersARB(buffers.remaining(), MemoryUtil.getAddress(buffers), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglDeleteBuffersARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDeleteBuffersARB(int buffer) {
/*  60 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  61 */     long function_pointer = caps.glDeleteBuffersARB;
/*  62 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  63 */     nglDeleteBuffersARB(1, APIUtil.getInt(caps, buffer), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glGenBuffersARB(IntBuffer buffers) {
/*  67 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  68 */     long function_pointer = caps.glGenBuffersARB;
/*  69 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  70 */     BufferChecks.checkDirect(buffers);
/*  71 */     nglGenBuffersARB(buffers.remaining(), MemoryUtil.getAddress(buffers), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGenBuffersARB(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGenBuffersARB() {
/*  77 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  78 */     long function_pointer = caps.glGenBuffersARB;
/*  79 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  80 */     IntBuffer buffers = APIUtil.getBufferInt(caps);
/*  81 */     nglGenBuffersARB(1, MemoryUtil.getAddress(buffers), function_pointer);
/*  82 */     return buffers.get(0);
/*     */   }
/*     */ 
/*     */   public static boolean glIsBufferARB(int buffer) {
/*  86 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  87 */     long function_pointer = caps.glIsBufferARB;
/*  88 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  89 */     boolean __result = nglIsBufferARB(buffer, function_pointer);
/*  90 */     return __result;
/*     */   }
/*     */   static native boolean nglIsBufferARB(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glBufferDataARB(int target, long data_size, int usage) {
/*  95 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  96 */     long function_pointer = caps.glBufferDataARB;
/*  97 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  98 */     nglBufferDataARB(target, data_size, 0L, usage, function_pointer);
/*     */   }
/*     */   public static void glBufferDataARB(int target, ByteBuffer data, int usage) {
/* 101 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 102 */     long function_pointer = caps.glBufferDataARB;
/* 103 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 104 */     BufferChecks.checkDirect(data);
/* 105 */     nglBufferDataARB(target, data.remaining(), MemoryUtil.getAddress(data), usage, function_pointer);
/*     */   }
/*     */   public static void glBufferDataARB(int target, DoubleBuffer data, int usage) {
/* 108 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 109 */     long function_pointer = caps.glBufferDataARB;
/* 110 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 111 */     BufferChecks.checkDirect(data);
/* 112 */     nglBufferDataARB(target, data.remaining() << 3, MemoryUtil.getAddress(data), usage, function_pointer);
/*     */   }
/*     */   public static void glBufferDataARB(int target, FloatBuffer data, int usage) {
/* 115 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 116 */     long function_pointer = caps.glBufferDataARB;
/* 117 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 118 */     BufferChecks.checkDirect(data);
/* 119 */     nglBufferDataARB(target, data.remaining() << 2, MemoryUtil.getAddress(data), usage, function_pointer);
/*     */   }
/*     */   public static void glBufferDataARB(int target, IntBuffer data, int usage) {
/* 122 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 123 */     long function_pointer = caps.glBufferDataARB;
/* 124 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 125 */     BufferChecks.checkDirect(data);
/* 126 */     nglBufferDataARB(target, data.remaining() << 2, MemoryUtil.getAddress(data), usage, function_pointer);
/*     */   }
/*     */   public static void glBufferDataARB(int target, ShortBuffer data, int usage) {
/* 129 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 130 */     long function_pointer = caps.glBufferDataARB;
/* 131 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 132 */     BufferChecks.checkDirect(data);
/* 133 */     nglBufferDataARB(target, data.remaining() << 1, MemoryUtil.getAddress(data), usage, function_pointer);
/*     */   }
/*     */   static native void nglBufferDataARB(int paramInt1, long paramLong1, long paramLong2, int paramInt2, long paramLong3);
/*     */ 
/*     */   public static void glBufferSubDataARB(int target, long offset, ByteBuffer data) {
/* 138 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 139 */     long function_pointer = caps.glBufferSubDataARB;
/* 140 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 141 */     BufferChecks.checkDirect(data);
/* 142 */     nglBufferSubDataARB(target, offset, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glBufferSubDataARB(int target, long offset, DoubleBuffer data) {
/* 145 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 146 */     long function_pointer = caps.glBufferSubDataARB;
/* 147 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 148 */     BufferChecks.checkDirect(data);
/* 149 */     nglBufferSubDataARB(target, offset, data.remaining() << 3, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glBufferSubDataARB(int target, long offset, FloatBuffer data) {
/* 152 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 153 */     long function_pointer = caps.glBufferSubDataARB;
/* 154 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 155 */     BufferChecks.checkDirect(data);
/* 156 */     nglBufferSubDataARB(target, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glBufferSubDataARB(int target, long offset, IntBuffer data) {
/* 159 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 160 */     long function_pointer = caps.glBufferSubDataARB;
/* 161 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 162 */     BufferChecks.checkDirect(data);
/* 163 */     nglBufferSubDataARB(target, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glBufferSubDataARB(int target, long offset, ShortBuffer data) {
/* 166 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 167 */     long function_pointer = caps.glBufferSubDataARB;
/* 168 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 169 */     BufferChecks.checkDirect(data);
/* 170 */     nglBufferSubDataARB(target, offset, data.remaining() << 1, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   static native void nglBufferSubDataARB(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static void glGetBufferSubDataARB(int target, long offset, ByteBuffer data) {
/* 175 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 176 */     long function_pointer = caps.glGetBufferSubDataARB;
/* 177 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 178 */     BufferChecks.checkDirect(data);
/* 179 */     nglGetBufferSubDataARB(target, offset, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glGetBufferSubDataARB(int target, long offset, DoubleBuffer data) {
/* 182 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 183 */     long function_pointer = caps.glGetBufferSubDataARB;
/* 184 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 185 */     BufferChecks.checkDirect(data);
/* 186 */     nglGetBufferSubDataARB(target, offset, data.remaining() << 3, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glGetBufferSubDataARB(int target, long offset, FloatBuffer data) {
/* 189 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 190 */     long function_pointer = caps.glGetBufferSubDataARB;
/* 191 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 192 */     BufferChecks.checkDirect(data);
/* 193 */     nglGetBufferSubDataARB(target, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glGetBufferSubDataARB(int target, long offset, IntBuffer data) {
/* 196 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 197 */     long function_pointer = caps.glGetBufferSubDataARB;
/* 198 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 199 */     BufferChecks.checkDirect(data);
/* 200 */     nglGetBufferSubDataARB(target, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glGetBufferSubDataARB(int target, long offset, ShortBuffer data) {
/* 203 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 204 */     long function_pointer = caps.glGetBufferSubDataARB;
/* 205 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 206 */     BufferChecks.checkDirect(data);
/* 207 */     nglGetBufferSubDataARB(target, offset, data.remaining() << 1, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetBufferSubDataARB(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static ByteBuffer glMapBufferARB(int target, int access, ByteBuffer old_buffer)
/*     */   {
/* 235 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 236 */     long function_pointer = caps.glMapBufferARB;
/* 237 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 238 */     if (old_buffer != null)
/* 239 */       BufferChecks.checkDirect(old_buffer);
/* 240 */     ByteBuffer __result = nglMapBufferARB(target, access, GLChecks.getBufferObjectSizeARB(caps, target), old_buffer, function_pointer);
/* 241 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*     */   }
/*     */ 
/*     */   public static ByteBuffer glMapBufferARB(int target, int access, long length, ByteBuffer old_buffer)
/*     */   {
/* 267 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 268 */     long function_pointer = caps.glMapBufferARB;
/* 269 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 270 */     if (old_buffer != null)
/* 271 */       BufferChecks.checkDirect(old_buffer);
/* 272 */     ByteBuffer __result = nglMapBufferARB(target, access, length, old_buffer, function_pointer);
/* 273 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*     */   }
/*     */   static native ByteBuffer nglMapBufferARB(int paramInt1, int paramInt2, long paramLong1, ByteBuffer paramByteBuffer, long paramLong2);
/*     */ 
/*     */   public static boolean glUnmapBufferARB(int target) {
/* 278 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 279 */     long function_pointer = caps.glUnmapBufferARB;
/* 280 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 281 */     boolean __result = nglUnmapBufferARB(target, function_pointer);
/* 282 */     return __result;
/*     */   }
/*     */   static native boolean nglUnmapBufferARB(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glGetBufferParameterARB(int target, int pname, IntBuffer params) {
/* 287 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 288 */     long function_pointer = caps.glGetBufferParameterivARB;
/* 289 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 290 */     BufferChecks.checkBuffer(params, 4);
/* 291 */     nglGetBufferParameterivARB(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetBufferParameterivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   @Deprecated
/*     */   public static int glGetBufferParameterARB(int target, int pname)
/*     */   {
/* 302 */     return glGetBufferParameteriARB(target, pname);
/*     */   }
/*     */ 
/*     */   public static int glGetBufferParameteriARB(int target, int pname)
/*     */   {
/* 307 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 308 */     long function_pointer = caps.glGetBufferParameterivARB;
/* 309 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 310 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 311 */     nglGetBufferParameterivARB(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 312 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static ByteBuffer glGetBufferPointerARB(int target, int pname) {
/* 316 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 317 */     long function_pointer = caps.glGetBufferPointervARB;
/* 318 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 319 */     ByteBuffer __result = nglGetBufferPointervARB(target, pname, GLChecks.getBufferObjectSizeARB(caps, target), function_pointer);
/* 320 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*     */   }
/*     */ 
/*     */   static native ByteBuffer nglGetBufferPointervARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBBufferObject
 * JD-Core Version:    0.6.2
 */