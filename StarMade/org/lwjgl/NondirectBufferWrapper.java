/*     */ package org.lwjgl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ 
/*     */ public final class NondirectBufferWrapper
/*     */ {
/*     */   private static final int INITIAL_BUFFER_SIZE = 1;
/*  54 */   private static final ThreadLocal<CachedBuffers> thread_buffer = new ThreadLocal() {
/*     */     protected NondirectBufferWrapper.CachedBuffers initialValue() {
/*  56 */       return new NondirectBufferWrapper.CachedBuffers(1, null);
/*     */     }
/*  54 */   };
/*     */ 
/*     */   private static CachedBuffers getCachedBuffers(int minimum_byte_size)
/*     */   {
/*  61 */     CachedBuffers buffers = (CachedBuffers)thread_buffer.get();
/*  62 */     int current_byte_size = buffers.byte_buffer.capacity();
/*  63 */     if (minimum_byte_size > current_byte_size) {
/*  64 */       buffers = new CachedBuffers(minimum_byte_size, null);
/*  65 */       thread_buffer.set(buffers);
/*     */     }
/*  67 */     return buffers;
/*     */   }
/*     */ 
/*     */   public static ByteBuffer wrapNoCopyBuffer(ByteBuffer buf, int size) {
/*  71 */     BufferChecks.checkBufferSize(buf, size);
/*  72 */     return wrapNoCopyDirect(buf);
/*     */   }
/*     */ 
/*     */   public static ShortBuffer wrapNoCopyBuffer(ShortBuffer buf, int size) {
/*  76 */     BufferChecks.checkBufferSize(buf, size);
/*  77 */     return wrapNoCopyDirect(buf);
/*     */   }
/*     */ 
/*     */   public static IntBuffer wrapNoCopyBuffer(IntBuffer buf, int size) {
/*  81 */     BufferChecks.checkBufferSize(buf, size);
/*  82 */     return wrapNoCopyDirect(buf);
/*     */   }
/*     */ 
/*     */   public static LongBuffer wrapNoCopyBuffer(LongBuffer buf, int size) {
/*  86 */     BufferChecks.checkBufferSize(buf, size);
/*  87 */     return wrapNoCopyDirect(buf);
/*     */   }
/*     */ 
/*     */   public static FloatBuffer wrapNoCopyBuffer(FloatBuffer buf, int size) {
/*  91 */     BufferChecks.checkBufferSize(buf, size);
/*  92 */     return wrapNoCopyDirect(buf);
/*     */   }
/*     */ 
/*     */   public static DoubleBuffer wrapNoCopyBuffer(DoubleBuffer buf, int size) {
/*  96 */     BufferChecks.checkBufferSize(buf, size);
/*  97 */     return wrapNoCopyDirect(buf);
/*     */   }
/*     */ 
/*     */   public static ByteBuffer wrapBuffer(ByteBuffer buf, int size) {
/* 101 */     BufferChecks.checkBufferSize(buf, size);
/* 102 */     return wrapDirect(buf);
/*     */   }
/*     */ 
/*     */   public static ShortBuffer wrapBuffer(ShortBuffer buf, int size) {
/* 106 */     BufferChecks.checkBufferSize(buf, size);
/* 107 */     return wrapDirect(buf);
/*     */   }
/*     */ 
/*     */   public static IntBuffer wrapBuffer(IntBuffer buf, int size) {
/* 111 */     BufferChecks.checkBufferSize(buf, size);
/* 112 */     return wrapDirect(buf);
/*     */   }
/*     */ 
/*     */   public static LongBuffer wrapBuffer(LongBuffer buf, int size) {
/* 116 */     BufferChecks.checkBufferSize(buf, size);
/* 117 */     return wrapDirect(buf);
/*     */   }
/*     */ 
/*     */   public static FloatBuffer wrapBuffer(FloatBuffer buf, int size) {
/* 121 */     BufferChecks.checkBufferSize(buf, size);
/* 122 */     return wrapDirect(buf);
/*     */   }
/*     */ 
/*     */   public static DoubleBuffer wrapBuffer(DoubleBuffer buf, int size) {
/* 126 */     BufferChecks.checkBufferSize(buf, size);
/* 127 */     return wrapDirect(buf);
/*     */   }
/*     */ 
/*     */   public static ByteBuffer wrapDirect(ByteBuffer buffer) {
/* 131 */     if (!buffer.isDirect())
/* 132 */       return doWrap(buffer);
/* 133 */     return buffer;
/*     */   }
/*     */ 
/*     */   public static ShortBuffer wrapDirect(ShortBuffer buffer) {
/* 137 */     if (!buffer.isDirect())
/* 138 */       return doWrap(buffer);
/* 139 */     return buffer;
/*     */   }
/*     */ 
/*     */   public static FloatBuffer wrapDirect(FloatBuffer buffer) {
/* 143 */     if (!buffer.isDirect())
/* 144 */       return doWrap(buffer);
/* 145 */     return buffer;
/*     */   }
/*     */ 
/*     */   public static IntBuffer wrapDirect(IntBuffer buffer) {
/* 149 */     if (!buffer.isDirect())
/* 150 */       return doWrap(buffer);
/* 151 */     return buffer;
/*     */   }
/*     */ 
/*     */   public static LongBuffer wrapDirect(LongBuffer buffer) {
/* 155 */     if (!buffer.isDirect())
/* 156 */       return doWrap(buffer);
/* 157 */     return buffer;
/*     */   }
/*     */ 
/*     */   public static DoubleBuffer wrapDirect(DoubleBuffer buffer) {
/* 161 */     if (!buffer.isDirect())
/* 162 */       return doWrap(buffer);
/* 163 */     return buffer;
/*     */   }
/*     */ 
/*     */   public static ByteBuffer wrapNoCopyDirect(ByteBuffer buffer) {
/* 167 */     if (!buffer.isDirect())
/* 168 */       return doNoCopyWrap(buffer);
/* 169 */     return buffer;
/*     */   }
/*     */ 
/*     */   public static ShortBuffer wrapNoCopyDirect(ShortBuffer buffer) {
/* 173 */     if (!buffer.isDirect())
/* 174 */       return doNoCopyWrap(buffer);
/* 175 */     return buffer;
/*     */   }
/*     */ 
/*     */   public static FloatBuffer wrapNoCopyDirect(FloatBuffer buffer) {
/* 179 */     if (!buffer.isDirect())
/* 180 */       return doNoCopyWrap(buffer);
/* 181 */     return buffer;
/*     */   }
/*     */ 
/*     */   public static IntBuffer wrapNoCopyDirect(IntBuffer buffer) {
/* 185 */     if (!buffer.isDirect())
/* 186 */       return doNoCopyWrap(buffer);
/* 187 */     return buffer;
/*     */   }
/*     */ 
/*     */   public static LongBuffer wrapNoCopyDirect(LongBuffer buffer) {
/* 191 */     if (!buffer.isDirect())
/* 192 */       return doNoCopyWrap(buffer);
/* 193 */     return buffer;
/*     */   }
/*     */ 
/*     */   public static DoubleBuffer wrapNoCopyDirect(DoubleBuffer buffer) {
/* 197 */     if (!buffer.isDirect())
/* 198 */       return doNoCopyWrap(buffer);
/* 199 */     return buffer;
/*     */   }
/*     */ 
/*     */   public static void copy(ByteBuffer src, ByteBuffer dst) {
/* 203 */     if ((dst != null) && (!dst.isDirect())) {
/* 204 */       int saved_position = dst.position();
/* 205 */       dst.put(src);
/* 206 */       dst.position(saved_position);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void copy(ShortBuffer src, ShortBuffer dst) {
/* 211 */     if ((dst != null) && (!dst.isDirect())) {
/* 212 */       int saved_position = dst.position();
/* 213 */       dst.put(src);
/* 214 */       dst.position(saved_position);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void copy(IntBuffer src, IntBuffer dst) {
/* 219 */     if ((dst != null) && (!dst.isDirect())) {
/* 220 */       int saved_position = dst.position();
/* 221 */       dst.put(src);
/* 222 */       dst.position(saved_position);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void copy(FloatBuffer src, FloatBuffer dst) {
/* 227 */     if ((dst != null) && (!dst.isDirect())) {
/* 228 */       int saved_position = dst.position();
/* 229 */       dst.put(src);
/* 230 */       dst.position(saved_position);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void copy(LongBuffer src, LongBuffer dst) {
/* 235 */     if ((dst != null) && (!dst.isDirect())) {
/* 236 */       int saved_position = dst.position();
/* 237 */       dst.put(src);
/* 238 */       dst.position(saved_position);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void copy(DoubleBuffer src, DoubleBuffer dst) {
/* 243 */     if ((dst != null) && (!dst.isDirect())) {
/* 244 */       int saved_position = dst.position();
/* 245 */       dst.put(src);
/* 246 */       dst.position(saved_position);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static ByteBuffer doNoCopyWrap(ByteBuffer buffer) {
/* 251 */     ByteBuffer direct_buffer = lookupBuffer(buffer);
/* 252 */     direct_buffer.limit(buffer.limit());
/* 253 */     direct_buffer.position(buffer.position());
/* 254 */     return direct_buffer;
/*     */   }
/*     */ 
/*     */   private static ShortBuffer doNoCopyWrap(ShortBuffer buffer) {
/* 258 */     ShortBuffer direct_buffer = lookupBuffer(buffer);
/* 259 */     direct_buffer.limit(buffer.limit());
/* 260 */     direct_buffer.position(buffer.position());
/* 261 */     return direct_buffer;
/*     */   }
/*     */ 
/*     */   private static IntBuffer doNoCopyWrap(IntBuffer buffer) {
/* 265 */     IntBuffer direct_buffer = lookupBuffer(buffer);
/* 266 */     direct_buffer.limit(buffer.limit());
/* 267 */     direct_buffer.position(buffer.position());
/* 268 */     return direct_buffer;
/*     */   }
/*     */ 
/*     */   private static FloatBuffer doNoCopyWrap(FloatBuffer buffer) {
/* 272 */     FloatBuffer direct_buffer = lookupBuffer(buffer);
/* 273 */     direct_buffer.limit(buffer.limit());
/* 274 */     direct_buffer.position(buffer.position());
/* 275 */     return direct_buffer;
/*     */   }
/*     */ 
/*     */   private static LongBuffer doNoCopyWrap(LongBuffer buffer) {
/* 279 */     LongBuffer direct_buffer = lookupBuffer(buffer);
/* 280 */     direct_buffer.limit(buffer.limit());
/* 281 */     direct_buffer.position(buffer.position());
/* 282 */     return direct_buffer;
/*     */   }
/*     */ 
/*     */   private static DoubleBuffer doNoCopyWrap(DoubleBuffer buffer) {
/* 286 */     DoubleBuffer direct_buffer = lookupBuffer(buffer);
/* 287 */     direct_buffer.limit(buffer.limit());
/* 288 */     direct_buffer.position(buffer.position());
/* 289 */     return direct_buffer;
/*     */   }
/*     */ 
/*     */   private static ByteBuffer lookupBuffer(ByteBuffer buffer) {
/* 293 */     return getCachedBuffers(buffer.remaining()).byte_buffer;
/*     */   }
/*     */ 
/*     */   private static ByteBuffer doWrap(ByteBuffer buffer) {
/* 297 */     ByteBuffer direct_buffer = lookupBuffer(buffer);
/* 298 */     direct_buffer.clear();
/* 299 */     int saved_position = buffer.position();
/* 300 */     direct_buffer.put(buffer);
/* 301 */     buffer.position(saved_position);
/* 302 */     direct_buffer.flip();
/* 303 */     return direct_buffer;
/*     */   }
/*     */ 
/*     */   private static ShortBuffer lookupBuffer(ShortBuffer buffer) {
/* 307 */     CachedBuffers buffers = getCachedBuffers(buffer.remaining() * 2);
/* 308 */     return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.short_buffer_little : buffers.short_buffer_big;
/*     */   }
/*     */ 
/*     */   private static ShortBuffer doWrap(ShortBuffer buffer) {
/* 312 */     ShortBuffer direct_buffer = lookupBuffer(buffer);
/* 313 */     direct_buffer.clear();
/* 314 */     int saved_position = buffer.position();
/* 315 */     direct_buffer.put(buffer);
/* 316 */     buffer.position(saved_position);
/* 317 */     direct_buffer.flip();
/* 318 */     return direct_buffer;
/*     */   }
/*     */ 
/*     */   private static FloatBuffer lookupBuffer(FloatBuffer buffer) {
/* 322 */     CachedBuffers buffers = getCachedBuffers(buffer.remaining() * 4);
/* 323 */     return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.float_buffer_little : buffers.float_buffer_big;
/*     */   }
/*     */ 
/*     */   private static FloatBuffer doWrap(FloatBuffer buffer) {
/* 327 */     FloatBuffer direct_buffer = lookupBuffer(buffer);
/* 328 */     direct_buffer.clear();
/* 329 */     int saved_position = buffer.position();
/* 330 */     direct_buffer.put(buffer);
/* 331 */     buffer.position(saved_position);
/* 332 */     direct_buffer.flip();
/* 333 */     return direct_buffer;
/*     */   }
/*     */ 
/*     */   private static IntBuffer lookupBuffer(IntBuffer buffer) {
/* 337 */     CachedBuffers buffers = getCachedBuffers(buffer.remaining() * 4);
/* 338 */     return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.int_buffer_little : buffers.int_buffer_big;
/*     */   }
/*     */ 
/*     */   private static IntBuffer doWrap(IntBuffer buffer) {
/* 342 */     IntBuffer direct_buffer = lookupBuffer(buffer);
/* 343 */     direct_buffer.clear();
/* 344 */     int saved_position = buffer.position();
/* 345 */     direct_buffer.put(buffer);
/* 346 */     buffer.position(saved_position);
/* 347 */     direct_buffer.flip();
/* 348 */     return direct_buffer;
/*     */   }
/*     */ 
/*     */   private static LongBuffer lookupBuffer(LongBuffer buffer) {
/* 352 */     CachedBuffers buffers = getCachedBuffers(buffer.remaining() * 8);
/* 353 */     return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.long_buffer_little : buffers.long_buffer_big;
/*     */   }
/*     */ 
/*     */   private static LongBuffer doWrap(LongBuffer buffer) {
/* 357 */     LongBuffer direct_buffer = lookupBuffer(buffer);
/* 358 */     direct_buffer.clear();
/* 359 */     int saved_position = buffer.position();
/* 360 */     direct_buffer.put(buffer);
/* 361 */     buffer.position(saved_position);
/* 362 */     direct_buffer.flip();
/* 363 */     return direct_buffer;
/*     */   }
/*     */ 
/*     */   private static DoubleBuffer lookupBuffer(DoubleBuffer buffer) {
/* 367 */     CachedBuffers buffers = getCachedBuffers(buffer.remaining() * 8);
/* 368 */     return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.double_buffer_little : buffers.double_buffer_big;
/*     */   }
/*     */ 
/*     */   private static DoubleBuffer doWrap(DoubleBuffer buffer) {
/* 372 */     DoubleBuffer direct_buffer = lookupBuffer(buffer);
/* 373 */     direct_buffer.clear();
/* 374 */     int saved_position = buffer.position();
/* 375 */     direct_buffer.put(buffer);
/* 376 */     buffer.position(saved_position);
/* 377 */     direct_buffer.flip();
/* 378 */     return direct_buffer; } 
/*     */   private static final class CachedBuffers { private final ByteBuffer byte_buffer;
/*     */     private final ShortBuffer short_buffer_big;
/*     */     private final IntBuffer int_buffer_big;
/*     */     private final FloatBuffer float_buffer_big;
/*     */     private final LongBuffer long_buffer_big;
/*     */     private final DoubleBuffer double_buffer_big;
/*     */     private final ShortBuffer short_buffer_little;
/*     */     private final IntBuffer int_buffer_little;
/*     */     private final FloatBuffer float_buffer_little;
/*     */     private final LongBuffer long_buffer_little;
/*     */     private final DoubleBuffer double_buffer_little;
/*     */ 
/* 395 */     private CachedBuffers(int size) { this.byte_buffer = ByteBuffer.allocateDirect(size);
/* 396 */       this.short_buffer_big = this.byte_buffer.asShortBuffer();
/* 397 */       this.int_buffer_big = this.byte_buffer.asIntBuffer();
/* 398 */       this.float_buffer_big = this.byte_buffer.asFloatBuffer();
/* 399 */       this.long_buffer_big = this.byte_buffer.asLongBuffer();
/* 400 */       this.double_buffer_big = this.byte_buffer.asDoubleBuffer();
/* 401 */       this.byte_buffer.order(ByteOrder.LITTLE_ENDIAN);
/* 402 */       this.short_buffer_little = this.byte_buffer.asShortBuffer();
/* 403 */       this.int_buffer_little = this.byte_buffer.asIntBuffer();
/* 404 */       this.float_buffer_little = this.byte_buffer.asFloatBuffer();
/* 405 */       this.long_buffer_little = this.byte_buffer.asLongBuffer();
/* 406 */       this.double_buffer_little = this.byte_buffer.asDoubleBuffer();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.NondirectBufferWrapper
 * JD-Core Version:    0.6.2
 */