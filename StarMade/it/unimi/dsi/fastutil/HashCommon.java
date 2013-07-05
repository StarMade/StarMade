/*     */ package it.unimi.dsi.fastutil;
/*     */ 
/*     */ public class HashCommon
/*     */ {
/*  29 */   public static final Object REMOVED = new Object();
/*     */ 
/*     */   public static final int murmurHash3(int x)
/*     */   {
/*  41 */     x ^= x >>> 16;
/*  42 */     x *= -2048144789;
/*  43 */     x ^= x >>> 13;
/*  44 */     x *= -1028477387;
/*  45 */     x ^= x >>> 16;
/*  46 */     return x;
/*     */   }
/*     */ 
/*     */   public static final long murmurHash3(long x)
/*     */   {
/*  59 */     x ^= x >>> 33;
/*  60 */     x *= -49064778989728563L;
/*  61 */     x ^= x >>> 33;
/*  62 */     x *= -4265267296055464877L;
/*  63 */     x ^= x >>> 33;
/*     */ 
/*  65 */     return x;
/*     */   }
/*     */ 
/*     */   public static final int float2int(float f)
/*     */   {
/*  74 */     return Float.floatToRawIntBits(f);
/*     */   }
/*     */ 
/*     */   public static final int double2int(double d)
/*     */   {
/*  83 */     long l = Double.doubleToRawLongBits(d);
/*  84 */     return (int)(l ^ l >>> 32);
/*     */   }
/*     */ 
/*     */   public static final int long2int(long l)
/*     */   {
/*  92 */     return (int)(l ^ l >>> 32);
/*     */   }
/*     */ 
/*     */   public static int nextPowerOfTwo(int x)
/*     */   {
/* 103 */     if (x == 0) return 1;
/* 104 */     x--;
/* 105 */     x |= x >> 1;
/* 106 */     x |= x >> 2;
/* 107 */     x |= x >> 4;
/* 108 */     x |= x >> 8;
/* 109 */     return (x | x >> 16) + 1;
/*     */   }
/*     */ 
/*     */   public static long nextPowerOfTwo(long x)
/*     */   {
/* 120 */     if (x == 0L) return 1L;
/* 121 */     x -= 1L;
/* 122 */     x |= x >> 1;
/* 123 */     x |= x >> 2;
/* 124 */     x |= x >> 4;
/* 125 */     x |= x >> 8;
/* 126 */     x |= x >> 16;
/* 127 */     return (x | x >> 32) + 1L;
/*     */   }
/*     */ 
/*     */   public static int maxFill(int n, float f)
/*     */   {
/* 138 */     return (int)Math.ceil(n * f);
/*     */   }
/*     */ 
/*     */   public static long maxFill(long n, float f)
/*     */   {
/* 148 */     return ()Math.ceil((float)n * f);
/*     */   }
/*     */ 
/*     */   public static int arraySize(int expected, float f)
/*     */   {
/* 159 */     long s = nextPowerOfTwo(()Math.ceil(expected / f));
/* 160 */     if (s > 1073741824L) throw new IllegalArgumentException("Too large (" + expected + " expected elements with load factor " + f + ")");
/* 161 */     return (int)s;
/*     */   }
/*     */ 
/*     */   public static long bigArraySize(long expected, float f)
/*     */   {
/* 171 */     return nextPowerOfTwo(()Math.ceil((float)expected / f));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.HashCommon
 * JD-Core Version:    0.6.2
 */