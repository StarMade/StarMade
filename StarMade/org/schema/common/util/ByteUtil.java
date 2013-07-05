/*     */ package org.schema.common.util;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ public class ByteUtil
/*     */ {
/*     */   public static final byte[] a;
/*     */ 
/*     */   public static final int a(int paramInt)
/*     */   {
/* 101 */     if (paramInt >= 0) return paramInt >> 4; return -(-paramInt >> 4);
/*     */   }
/*     */ 
/*     */   public static final int b(int paramInt) {
/* 105 */     return paramInt >> 4;
/*     */   }
/*     */   public static final int c(int paramInt) {
/* 108 */     return paramInt >> 3;
/*     */   }
/*     */ 
/*     */   public static int a(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 120 */     paramInt3 = (1 << paramInt3 - paramInt2) - 1;
/* 121 */     return paramInt1 >> paramInt2 & paramInt3;
/*     */   }
/*     */ 
/*     */   public static float a(byte paramByte1, byte paramByte2, short paramShort, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6)
/*     */   {
/* 154 */     paramByte1 = paramByte1 << 2;
/* 155 */     paramByte2 = paramByte2 << 5;
/* 156 */     paramShort = paramShort << 9;
/* 157 */     paramByte3 = paramByte3 << 17;
/* 158 */     paramByte4 = paramByte4 << 20;
/* 159 */     paramByte5 = paramByte5 << 21;
/* 160 */     paramByte6 = paramByte6 << 22;
/*     */ 
/* 164 */     return paramByte1 + paramByte2 + paramShort + paramByte3 + paramByte4 + paramByte5 + paramByte6;
/*     */   }
/*     */ 
/*     */   public static float a(float paramFloat, byte paramByte1, byte paramByte2, byte paramByte3)
/*     */   {
/* 172 */     paramByte1 = paramByte1 << 12;
/*     */ 
/* 174 */     paramByte2 = paramByte2 << 16;
/* 175 */     paramByte3 = paramByte3 << 20;
/*     */ 
/* 177 */     return paramFloat + paramByte1 + paramByte2 + paramByte3;
/*     */   }
/*     */ 
/*     */   public static int a(byte[] paramArrayOfByte, int paramInt)
/*     */   {
/* 185 */     int i = 0;
/* 186 */     for (int j = 0; j < 3; j++)
/*     */     {
/* 188 */       i = i << 8 ^ 
/* 188 */         paramArrayOfByte[(paramInt + j)] & 0xFF;
/*     */     }
/*     */ 
/* 191 */     return i;
/*     */   }
/*     */ 
/*     */   public static byte[] a(int paramInt)
/*     */   {
/* 209 */     byte[] arrayOfByte = new byte[4];
/* 210 */     for (int i = 0; i < 4; i++) {
/* 211 */       int j = arrayOfByte.length - 1 - i << 3;
/* 212 */       arrayOfByte[i] = ((byte)(paramInt >>> j));
/*     */     }
/* 214 */     return arrayOfByte;
/*     */   }
/*     */ 
/*     */   private static void a(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
/*     */   {
/* 224 */     paramArrayOfByte[paramInt2] = ((byte)(paramInt1 >>> 16));
/* 225 */     paramArrayOfByte[(paramInt2 + 1)] = ((byte)(paramInt1 >>> 8));
/* 226 */     paramArrayOfByte[(paramInt2 + 2)] = ((byte)paramInt1);
/*     */   }
/*     */ 
/*     */   public static byte[] a(long paramLong)
/*     */   {
/*     */     byte[] arrayOfByte;
/* 242 */     ByteBuffer.wrap(arrayOfByte = new byte[8])
/* 242 */       .putLong(paramLong);
/*     */ 
/* 244 */     return arrayOfByte;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 252 */     for (paramArrayOfString = 0.0F; paramArrayOfString < 6.0F; paramArrayOfString += 1.0F)
/*     */     {
/* 254 */       float f1 = Math.max(0.0F, (float)Math.floor(paramArrayOfString) % 2.0F - (paramArrayOfString - 1.0F) - (paramArrayOfString + 1.0F) % 2.0F);
/* 255 */       float f2 = Math.max(0.0F, (float)Math.floor(paramArrayOfString) % 2.0F - (paramArrayOfString - 3.0F) - (paramArrayOfString + 1.0F) % 2.0F);
/* 256 */       float f3 = Math.max(0.0F, (float)Math.floor(paramArrayOfString) % 2.0F - (paramArrayOfString - 5.0F) - (paramArrayOfString + 1.0F) % 2.0F);
/*     */ 
/* 258 */       System.err.println(f1 + ", " + f2 + ", " + f3);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static final int d(int paramInt)
/*     */   {
/* 271 */     return paramInt & 0xF;
/*     */   }
/*     */ 
/*     */   public static void a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 290 */     a(a(a(paramArrayOfByte, paramInt4), paramInt1, paramInt2, paramInt3), paramArrayOfByte, paramInt4);
/*     */   }
/*     */ 
/*     */   private static int a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 300 */     paramInt4 = (1 << paramInt4 - paramInt3) - 1 << paramInt3;
/*     */ 
/* 302 */     return paramInt1 & (paramInt4 ^ 0xFFFFFFFF) | paramInt2 << paramInt3 & paramInt4;
/*     */   }
/*     */ 
/*     */   public static int a(InputStream paramInputStream)
/*     */   {
/* 338 */     int i = 0;
/*     */ 
/* 340 */     for (int j = 0; j < 4; j++) {
/* 341 */       i <<= 8;
/* 342 */       int k = 0;
/* 343 */       while ((k = paramInputStream.read()) == -1);
/* 347 */       i ^= k & 0xFF;
/*     */     }
/*     */ 
/* 350 */     return i;
/*     */   }
/*     */ 
/*     */   public static long a(InputStream paramInputStream) {
/* 354 */     long l = 0L;
/* 355 */     for (int i = 0; i < 8; i++) {
/* 356 */       l <<= 8;
/* 357 */       int j = 0;
/* 358 */       while ((j = paramInputStream.read()) == -1);
/* 360 */       l ^= j & 0xFF;
/*     */     }
/* 362 */     return l;
/*     */   }
/*     */ 
/*     */   public static short a(byte[] paramArrayOfByte, int paramInt)
/*     */   {
/* 393 */     short s = 0;
/*     */ 
/* 395 */     for (int i = 0; i < 2; i++)
/*     */     {
/* 397 */       s = (short)((short)(s << 8) ^ 
/* 397 */         paramArrayOfByte[(paramInt + i)] & 0xFF);
/*     */     }
/*     */ 
/* 400 */     return s;
/*     */   }
/*     */ 
/*     */   public static void a(short paramShort, byte[] paramArrayOfByte, int paramInt)
/*     */   {
/* 426 */     for (int i = 0; i < 2; i++) {
/* 427 */       short s = 1 - i << 3;
/* 428 */       paramArrayOfByte[(paramInt + i)] = ((byte)(paramShort >>> s));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void a(int paramInt, OutputStream paramOutputStream)
/*     */   {
/* 615 */     for (int i = 0; i < 4; i++) {
/* 616 */       int j = 3 - i << 3;
/* 617 */       paramOutputStream.write((byte)(paramInt >>> j));
/*     */     }
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  12 */     ByteUtil.class.desiredAssertionStatus();
/*     */ 
/* 279 */     int i = (
/* 279 */       ByteUtil.a = new byte[24576]).length / 
/* 279 */       3;
/* 280 */     for (int j = 0; j < i; j = (short)(j + 1)) {
/* 281 */       int k = j * 3;
/*     */ 
/* 284 */       a(a(a(a, k), 
/* 283 */         j, 0, 11), 
/* 284 */         a, k);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.common.util.ByteUtil
 * JD-Core Version:    0.6.2
 */