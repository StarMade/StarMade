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
/*     */ public final class GL15
/*     */ {
/*     */   public static final int GL_ARRAY_BUFFER = 34962;
/*     */   public static final int GL_ELEMENT_ARRAY_BUFFER = 34963;
/*     */   public static final int GL_ARRAY_BUFFER_BINDING = 34964;
/*     */   public static final int GL_ELEMENT_ARRAY_BUFFER_BINDING = 34965;
/*     */   public static final int GL_VERTEX_ARRAY_BUFFER_BINDING = 34966;
/*     */   public static final int GL_NORMAL_ARRAY_BUFFER_BINDING = 34967;
/*     */   public static final int GL_COLOR_ARRAY_BUFFER_BINDING = 34968;
/*     */   public static final int GL_INDEX_ARRAY_BUFFER_BINDING = 34969;
/*     */   public static final int GL_TEXTURE_COORD_ARRAY_BUFFER_BINDING = 34970;
/*     */   public static final int GL_EDGE_FLAG_ARRAY_BUFFER_BINDING = 34971;
/*     */   public static final int GL_SECONDARY_COLOR_ARRAY_BUFFER_BINDING = 34972;
/*     */   public static final int GL_FOG_COORDINATE_ARRAY_BUFFER_BINDING = 34973;
/*     */   public static final int GL_WEIGHT_ARRAY_BUFFER_BINDING = 34974;
/*     */   public static final int GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = 34975;
/*     */   public static final int GL_STREAM_DRAW = 35040;
/*     */   public static final int GL_STREAM_READ = 35041;
/*     */   public static final int GL_STREAM_COPY = 35042;
/*     */   public static final int GL_STATIC_DRAW = 35044;
/*     */   public static final int GL_STATIC_READ = 35045;
/*     */   public static final int GL_STATIC_COPY = 35046;
/*     */   public static final int GL_DYNAMIC_DRAW = 35048;
/*     */   public static final int GL_DYNAMIC_READ = 35049;
/*     */   public static final int GL_DYNAMIC_COPY = 35050;
/*     */   public static final int GL_READ_ONLY = 35000;
/*     */   public static final int GL_WRITE_ONLY = 35001;
/*     */   public static final int GL_READ_WRITE = 35002;
/*     */   public static final int GL_BUFFER_SIZE = 34660;
/*     */   public static final int GL_BUFFER_USAGE = 34661;
/*     */   public static final int GL_BUFFER_ACCESS = 35003;
/*     */   public static final int GL_BUFFER_MAPPED = 35004;
/*     */   public static final int GL_BUFFER_MAP_POINTER = 35005;
/*     */   public static final int GL_FOG_COORD_SRC = 33872;
/*     */   public static final int GL_FOG_COORD = 33873;
/*     */   public static final int GL_CURRENT_FOG_COORD = 33875;
/*     */   public static final int GL_FOG_COORD_ARRAY_TYPE = 33876;
/*     */   public static final int GL_FOG_COORD_ARRAY_STRIDE = 33877;
/*     */   public static final int GL_FOG_COORD_ARRAY_POINTER = 33878;
/*     */   public static final int GL_FOG_COORD_ARRAY = 33879;
/*     */   public static final int GL_FOG_COORD_ARRAY_BUFFER_BINDING = 34973;
/*     */   public static final int GL_SRC0_RGB = 34176;
/*     */   public static final int GL_SRC1_RGB = 34177;
/*     */   public static final int GL_SRC2_RGB = 34178;
/*     */   public static final int GL_SRC0_ALPHA = 34184;
/*     */   public static final int GL_SRC1_ALPHA = 34185;
/*     */   public static final int GL_SRC2_ALPHA = 34186;
/*     */   public static final int GL_SAMPLES_PASSED = 35092;
/*     */   public static final int GL_QUERY_COUNTER_BITS = 34916;
/*     */   public static final int GL_CURRENT_QUERY = 34917;
/*     */   public static final int GL_QUERY_RESULT = 34918;
/*     */   public static final int GL_QUERY_RESULT_AVAILABLE = 34919;
/*     */ 
/*     */   public static void glBindBuffer(int target, int buffer)
/*     */   {
/*  78 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  79 */     long function_pointer = caps.glBindBuffer;
/*  80 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  81 */     StateTracker.bindBuffer(caps, target, buffer);
/*  82 */     nglBindBuffer(target, buffer, function_pointer);
/*     */   }
/*     */   static native void nglBindBuffer(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glDeleteBuffers(IntBuffer buffers) {
/*  87 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  88 */     long function_pointer = caps.glDeleteBuffers;
/*  89 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  90 */     BufferChecks.checkDirect(buffers);
/*  91 */     nglDeleteBuffers(buffers.remaining(), MemoryUtil.getAddress(buffers), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglDeleteBuffers(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDeleteBuffers(int buffer) {
/*  97 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  98 */     long function_pointer = caps.glDeleteBuffers;
/*  99 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 100 */     nglDeleteBuffers(1, APIUtil.getInt(caps, buffer), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glGenBuffers(IntBuffer buffers) {
/* 104 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 105 */     long function_pointer = caps.glGenBuffers;
/* 106 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 107 */     BufferChecks.checkDirect(buffers);
/* 108 */     nglGenBuffers(buffers.remaining(), MemoryUtil.getAddress(buffers), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGenBuffers(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGenBuffers() {
/* 114 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 115 */     long function_pointer = caps.glGenBuffers;
/* 116 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 117 */     IntBuffer buffers = APIUtil.getBufferInt(caps);
/* 118 */     nglGenBuffers(1, MemoryUtil.getAddress(buffers), function_pointer);
/* 119 */     return buffers.get(0);
/*     */   }
/*     */ 
/*     */   public static boolean glIsBuffer(int buffer) {
/* 123 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 124 */     long function_pointer = caps.glIsBuffer;
/* 125 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 126 */     boolean __result = nglIsBuffer(buffer, function_pointer);
/* 127 */     return __result;
/*     */   }
/*     */   static native boolean nglIsBuffer(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glBufferData(int target, long data_size, int usage) {
/* 132 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 133 */     long function_pointer = caps.glBufferData;
/* 134 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 135 */     nglBufferData(target, data_size, 0L, usage, function_pointer);
/*     */   }
/*     */   public static void glBufferData(int target, ByteBuffer data, int usage) {
/* 138 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 139 */     long function_pointer = caps.glBufferData;
/* 140 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 141 */     BufferChecks.checkDirect(data);
/* 142 */     nglBufferData(target, data.remaining(), MemoryUtil.getAddress(data), usage, function_pointer);
/*     */   }
/*     */   public static void glBufferData(int target, DoubleBuffer data, int usage) {
/* 145 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 146 */     long function_pointer = caps.glBufferData;
/* 147 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 148 */     BufferChecks.checkDirect(data);
/* 149 */     nglBufferData(target, data.remaining() << 3, MemoryUtil.getAddress(data), usage, function_pointer);
/*     */   }
/*     */   public static void glBufferData(int target, FloatBuffer data, int usage) {
/* 152 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 153 */     long function_pointer = caps.glBufferData;
/* 154 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 155 */     BufferChecks.checkDirect(data);
/* 156 */     nglBufferData(target, data.remaining() << 2, MemoryUtil.getAddress(data), usage, function_pointer);
/*     */   }
/*     */   public static void glBufferData(int target, IntBuffer data, int usage) {
/* 159 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 160 */     long function_pointer = caps.glBufferData;
/* 161 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 162 */     BufferChecks.checkDirect(data);
/* 163 */     nglBufferData(target, data.remaining() << 2, MemoryUtil.getAddress(data), usage, function_pointer);
/*     */   }
/*     */   public static void glBufferData(int target, ShortBuffer data, int usage) {
/* 166 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 167 */     long function_pointer = caps.glBufferData;
/* 168 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 169 */     BufferChecks.checkDirect(data);
/* 170 */     nglBufferData(target, data.remaining() << 1, MemoryUtil.getAddress(data), usage, function_pointer);
/*     */   }
/*     */   static native void nglBufferData(int paramInt1, long paramLong1, long paramLong2, int paramInt2, long paramLong3);
/*     */ 
/*     */   public static void glBufferSubData(int target, long offset, ByteBuffer data) {
/* 175 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 176 */     long function_pointer = caps.glBufferSubData;
/* 177 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 178 */     BufferChecks.checkDirect(data);
/* 179 */     nglBufferSubData(target, offset, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glBufferSubData(int target, long offset, DoubleBuffer data) {
/* 182 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 183 */     long function_pointer = caps.glBufferSubData;
/* 184 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 185 */     BufferChecks.checkDirect(data);
/* 186 */     nglBufferSubData(target, offset, data.remaining() << 3, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glBufferSubData(int target, long offset, FloatBuffer data) {
/* 189 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 190 */     long function_pointer = caps.glBufferSubData;
/* 191 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 192 */     BufferChecks.checkDirect(data);
/* 193 */     nglBufferSubData(target, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glBufferSubData(int target, long offset, IntBuffer data) {
/* 196 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 197 */     long function_pointer = caps.glBufferSubData;
/* 198 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 199 */     BufferChecks.checkDirect(data);
/* 200 */     nglBufferSubData(target, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glBufferSubData(int target, long offset, ShortBuffer data) {
/* 203 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 204 */     long function_pointer = caps.glBufferSubData;
/* 205 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 206 */     BufferChecks.checkDirect(data);
/* 207 */     nglBufferSubData(target, offset, data.remaining() << 1, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   static native void nglBufferSubData(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static void glGetBufferSubData(int target, long offset, ByteBuffer data) {
/* 212 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 213 */     long function_pointer = caps.glGetBufferSubData;
/* 214 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 215 */     BufferChecks.checkDirect(data);
/* 216 */     nglGetBufferSubData(target, offset, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glGetBufferSubData(int target, long offset, DoubleBuffer data) {
/* 219 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 220 */     long function_pointer = caps.glGetBufferSubData;
/* 221 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 222 */     BufferChecks.checkDirect(data);
/* 223 */     nglGetBufferSubData(target, offset, data.remaining() << 3, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glGetBufferSubData(int target, long offset, FloatBuffer data) {
/* 226 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 227 */     long function_pointer = caps.glGetBufferSubData;
/* 228 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 229 */     BufferChecks.checkDirect(data);
/* 230 */     nglGetBufferSubData(target, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glGetBufferSubData(int target, long offset, IntBuffer data) {
/* 233 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 234 */     long function_pointer = caps.glGetBufferSubData;
/* 235 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 236 */     BufferChecks.checkDirect(data);
/* 237 */     nglGetBufferSubData(target, offset, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   public static void glGetBufferSubData(int target, long offset, ShortBuffer data) {
/* 240 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 241 */     long function_pointer = caps.glGetBufferSubData;
/* 242 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 243 */     BufferChecks.checkDirect(data);
/* 244 */     nglGetBufferSubData(target, offset, data.remaining() << 1, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetBufferSubData(int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static ByteBuffer glMapBuffer(int target, int access, ByteBuffer old_buffer)
/*     */   {
/* 271 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 272 */     long function_pointer = caps.glMapBuffer;
/* 273 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 274 */     if (old_buffer != null)
/* 275 */       BufferChecks.checkDirect(old_buffer);
/* 276 */     ByteBuffer __result = nglMapBuffer(target, access, GLChecks.getBufferObjectSize(caps, target), old_buffer, function_pointer);
/* 277 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*     */   }
/*     */ 
/*     */   public static ByteBuffer glMapBuffer(int target, int access, long length, ByteBuffer old_buffer)
/*     */   {
/* 302 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 303 */     long function_pointer = caps.glMapBuffer;
/* 304 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 305 */     if (old_buffer != null)
/* 306 */       BufferChecks.checkDirect(old_buffer);
/* 307 */     ByteBuffer __result = nglMapBuffer(target, access, length, old_buffer, function_pointer);
/* 308 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*     */   }
/*     */   static native ByteBuffer nglMapBuffer(int paramInt1, int paramInt2, long paramLong1, ByteBuffer paramByteBuffer, long paramLong2);
/*     */ 
/*     */   public static boolean glUnmapBuffer(int target) {
/* 313 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 314 */     long function_pointer = caps.glUnmapBuffer;
/* 315 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 316 */     boolean __result = nglUnmapBuffer(target, function_pointer);
/* 317 */     return __result;
/*     */   }
/*     */   static native boolean nglUnmapBuffer(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glGetBufferParameter(int target, int pname, IntBuffer params) {
/* 322 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 323 */     long function_pointer = caps.glGetBufferParameteriv;
/* 324 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 325 */     BufferChecks.checkBuffer(params, 4);
/* 326 */     nglGetBufferParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetBufferParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   @Deprecated
/*     */   public static int glGetBufferParameter(int target, int pname)
/*     */   {
/* 337 */     return glGetBufferParameteri(target, pname);
/*     */   }
/*     */ 
/*     */   public static int glGetBufferParameteri(int target, int pname)
/*     */   {
/* 342 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 343 */     long function_pointer = caps.glGetBufferParameteriv;
/* 344 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 345 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 346 */     nglGetBufferParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 347 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static ByteBuffer glGetBufferPointer(int target, int pname) {
/* 351 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 352 */     long function_pointer = caps.glGetBufferPointerv;
/* 353 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 354 */     ByteBuffer __result = nglGetBufferPointerv(target, pname, GLChecks.getBufferObjectSize(caps, target), function_pointer);
/* 355 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*     */   }
/*     */   static native ByteBuffer nglGetBufferPointerv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGenQueries(IntBuffer ids) {
/* 360 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 361 */     long function_pointer = caps.glGenQueries;
/* 362 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 363 */     BufferChecks.checkDirect(ids);
/* 364 */     nglGenQueries(ids.remaining(), MemoryUtil.getAddress(ids), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGenQueries(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGenQueries() {
/* 370 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 371 */     long function_pointer = caps.glGenQueries;
/* 372 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 373 */     IntBuffer ids = APIUtil.getBufferInt(caps);
/* 374 */     nglGenQueries(1, MemoryUtil.getAddress(ids), function_pointer);
/* 375 */     return ids.get(0);
/*     */   }
/*     */ 
/*     */   public static void glDeleteQueries(IntBuffer ids) {
/* 379 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 380 */     long function_pointer = caps.glDeleteQueries;
/* 381 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 382 */     BufferChecks.checkDirect(ids);
/* 383 */     nglDeleteQueries(ids.remaining(), MemoryUtil.getAddress(ids), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglDeleteQueries(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDeleteQueries(int id) {
/* 389 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 390 */     long function_pointer = caps.glDeleteQueries;
/* 391 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 392 */     nglDeleteQueries(1, APIUtil.getInt(caps, id), function_pointer);
/*     */   }
/*     */ 
/*     */   public static boolean glIsQuery(int id) {
/* 396 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 397 */     long function_pointer = caps.glIsQuery;
/* 398 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 399 */     boolean __result = nglIsQuery(id, function_pointer);
/* 400 */     return __result;
/*     */   }
/*     */   static native boolean nglIsQuery(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glBeginQuery(int target, int id) {
/* 405 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 406 */     long function_pointer = caps.glBeginQuery;
/* 407 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 408 */     nglBeginQuery(target, id, function_pointer);
/*     */   }
/*     */   static native void nglBeginQuery(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glEndQuery(int target) {
/* 413 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 414 */     long function_pointer = caps.glEndQuery;
/* 415 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 416 */     nglEndQuery(target, function_pointer);
/*     */   }
/*     */   static native void nglEndQuery(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glGetQuery(int target, int pname, IntBuffer params) {
/* 421 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 422 */     long function_pointer = caps.glGetQueryiv;
/* 423 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 424 */     BufferChecks.checkBuffer(params, 1);
/* 425 */     nglGetQueryiv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetQueryiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   @Deprecated
/*     */   public static int glGetQuery(int target, int pname)
/*     */   {
/* 436 */     return glGetQueryi(target, pname);
/*     */   }
/*     */ 
/*     */   public static int glGetQueryi(int target, int pname)
/*     */   {
/* 441 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 442 */     long function_pointer = caps.glGetQueryiv;
/* 443 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 444 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 445 */     nglGetQueryiv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 446 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetQueryObject(int id, int pname, IntBuffer params) {
/* 450 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 451 */     long function_pointer = caps.glGetQueryObjectiv;
/* 452 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 453 */     BufferChecks.checkBuffer(params, 1);
/* 454 */     nglGetQueryObjectiv(id, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetQueryObjectiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetQueryObjecti(int id, int pname) {
/* 460 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 461 */     long function_pointer = caps.glGetQueryObjectiv;
/* 462 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 463 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 464 */     nglGetQueryObjectiv(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 465 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetQueryObjectu(int id, int pname, IntBuffer params) {
/* 469 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 470 */     long function_pointer = caps.glGetQueryObjectuiv;
/* 471 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 472 */     BufferChecks.checkBuffer(params, 1);
/* 473 */     nglGetQueryObjectuiv(id, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetQueryObjectuiv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetQueryObjectui(int id, int pname) {
/* 479 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 480 */     long function_pointer = caps.glGetQueryObjectuiv;
/* 481 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 482 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 483 */     nglGetQueryObjectuiv(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 484 */     return params.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL15
 * JD-Core Version:    0.6.2
 */