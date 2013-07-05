/*     */ package org.lwjgl.util.mapped;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ import org.lwjgl.PointerBuffer;
/*     */ 
/*     */ public final class CacheUtil
/*     */ {
/*     */   private static final int CACHE_LINE_SIZE;
/*     */ 
/*     */   public static int getCacheLineSize()
/*     */   {
/*  86 */     return CACHE_LINE_SIZE;
/*     */   }
/*     */ 
/*     */   public static ByteBuffer createByteBuffer(int size)
/*     */   {
/*  97 */     ByteBuffer buffer = ByteBuffer.allocateDirect(size + CACHE_LINE_SIZE);
/*     */ 
/* 100 */     if (MemoryUtil.getAddress(buffer) % CACHE_LINE_SIZE != 0L)
/*     */     {
/* 102 */       buffer.position(CACHE_LINE_SIZE - (int)(MemoryUtil.getAddress(buffer) & CACHE_LINE_SIZE - 1));
/*     */     }
/*     */ 
/* 105 */     buffer.limit(buffer.position() + size);
/* 106 */     return buffer.slice().order(ByteOrder.nativeOrder());
/*     */   }
/*     */ 
/*     */   public static ShortBuffer createShortBuffer(int size)
/*     */   {
/* 118 */     return createByteBuffer(size << 1).asShortBuffer();
/*     */   }
/*     */ 
/*     */   public static CharBuffer createCharBuffer(int size)
/*     */   {
/* 130 */     return createByteBuffer(size << 1).asCharBuffer();
/*     */   }
/*     */ 
/*     */   public static IntBuffer createIntBuffer(int size)
/*     */   {
/* 142 */     return createByteBuffer(size << 2).asIntBuffer();
/*     */   }
/*     */ 
/*     */   public static LongBuffer createLongBuffer(int size)
/*     */   {
/* 154 */     return createByteBuffer(size << 3).asLongBuffer();
/*     */   }
/*     */ 
/*     */   public static FloatBuffer createFloatBuffer(int size)
/*     */   {
/* 166 */     return createByteBuffer(size << 2).asFloatBuffer();
/*     */   }
/*     */ 
/*     */   public static DoubleBuffer createDoubleBuffer(int size)
/*     */   {
/* 178 */     return createByteBuffer(size << 3).asDoubleBuffer();
/*     */   }
/*     */ 
/*     */   public static PointerBuffer createPointerBuffer(int size)
/*     */   {
/* 190 */     return new PointerBuffer(createByteBuffer(size * PointerBuffer.getPointerSize()));
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  55 */     Integer size = LWJGLUtil.getPrivilegedInteger("org.lwjgl.util.mapped.CacheLineSize");
/*     */ 
/*  57 */     if (size != null) {
/*  58 */       if (size.intValue() < 1)
/*  59 */         throw new IllegalStateException("Invalid CacheLineSize specified: " + size);
/*  60 */       CACHE_LINE_SIZE = size.intValue();
/*  61 */     } else if (Runtime.getRuntime().availableProcessors() == 1)
/*     */     {
/*  70 */       if (LWJGLUtil.DEBUG)
/*  71 */         LWJGLUtil.log("Cannot detect cache line size on single-core CPUs, assuming 64 bytes.");
/*  72 */       CACHE_LINE_SIZE = 64;
/*     */     } else {
/*  74 */       CACHE_LINE_SIZE = CacheLineSize.getCacheLineSize();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.CacheUtil
 * JD-Core Version:    0.6.2
 */