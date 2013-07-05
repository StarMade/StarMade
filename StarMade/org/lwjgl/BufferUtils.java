/*     */ package org.lwjgl;
/*     */ 
/*     */ import java.nio.Buffer;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ 
/*     */ public final class BufferUtils
/*     */ {
/*     */   public static ByteBuffer createByteBuffer(int size)
/*     */   {
/*  60 */     return ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder());
/*     */   }
/*     */ 
/*     */   public static ShortBuffer createShortBuffer(int size)
/*     */   {
/*  70 */     return createByteBuffer(size << 1).asShortBuffer();
/*     */   }
/*     */ 
/*     */   public static CharBuffer createCharBuffer(int size)
/*     */   {
/*  80 */     return createByteBuffer(size << 1).asCharBuffer();
/*     */   }
/*     */ 
/*     */   public static IntBuffer createIntBuffer(int size)
/*     */   {
/*  90 */     return createByteBuffer(size << 2).asIntBuffer();
/*     */   }
/*     */ 
/*     */   public static LongBuffer createLongBuffer(int size)
/*     */   {
/* 100 */     return createByteBuffer(size << 3).asLongBuffer();
/*     */   }
/*     */ 
/*     */   public static FloatBuffer createFloatBuffer(int size)
/*     */   {
/* 110 */     return createByteBuffer(size << 2).asFloatBuffer();
/*     */   }
/*     */ 
/*     */   public static DoubleBuffer createDoubleBuffer(int size)
/*     */   {
/* 120 */     return createByteBuffer(size << 3).asDoubleBuffer();
/*     */   }
/*     */ 
/*     */   public static PointerBuffer createPointerBuffer(int size)
/*     */   {
/* 130 */     return PointerBuffer.allocateDirect(size);
/*     */   }
/*     */ 
/*     */   public static int getElementSizeExponent(Buffer buf)
/*     */   {
/* 137 */     if ((buf instanceof ByteBuffer))
/* 138 */       return 0;
/* 139 */     if (((buf instanceof ShortBuffer)) || ((buf instanceof CharBuffer)))
/* 140 */       return 1;
/* 141 */     if (((buf instanceof FloatBuffer)) || ((buf instanceof IntBuffer)))
/* 142 */       return 2;
/* 143 */     if (((buf instanceof LongBuffer)) || ((buf instanceof DoubleBuffer))) {
/* 144 */       return 3;
/*     */     }
/* 146 */     throw new IllegalStateException("Unsupported buffer type: " + buf);
/*     */   }
/*     */ 
/*     */   public static int getOffset(Buffer buffer)
/*     */   {
/* 155 */     return buffer.position() << getElementSizeExponent(buffer);
/*     */   }
/*     */ 
/*     */   public static void zeroBuffer(ByteBuffer b)
/*     */   {
/* 160 */     zeroBuffer0(b, b.position(), b.remaining());
/*     */   }
/*     */ 
/*     */   public static void zeroBuffer(ShortBuffer b)
/*     */   {
/* 165 */     zeroBuffer0(b, b.position() * 2L, b.remaining() * 2L);
/*     */   }
/*     */ 
/*     */   public static void zeroBuffer(CharBuffer b)
/*     */   {
/* 170 */     zeroBuffer0(b, b.position() * 2L, b.remaining() * 2L);
/*     */   }
/*     */ 
/*     */   public static void zeroBuffer(IntBuffer b)
/*     */   {
/* 175 */     zeroBuffer0(b, b.position() * 4L, b.remaining() * 4L);
/*     */   }
/*     */ 
/*     */   public static void zeroBuffer(FloatBuffer b)
/*     */   {
/* 180 */     zeroBuffer0(b, b.position() * 4L, b.remaining() * 4L);
/*     */   }
/*     */ 
/*     */   public static void zeroBuffer(LongBuffer b)
/*     */   {
/* 185 */     zeroBuffer0(b, b.position() * 8L, b.remaining() * 8L);
/*     */   }
/*     */ 
/*     */   public static void zeroBuffer(DoubleBuffer b)
/*     */   {
/* 190 */     zeroBuffer0(b, b.position() * 8L, b.remaining() * 8L);
/*     */   }
/*     */ 
/*     */   private static native void zeroBuffer0(Buffer paramBuffer, long paramLong1, long paramLong2);
/*     */ 
/*     */   static native long getBufferAddress(Buffer paramBuffer);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.BufferUtils
 * JD-Core Version:    0.6.2
 */