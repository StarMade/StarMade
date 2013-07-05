/*     */ package de.jarnbjo.vorbis;
/*     */ 
/*     */ public final class Util
/*     */ {
/*     */   public static final int ilog(int x)
/*     */   {
/*  32 */     for (int res = 0; 
/*  33 */       x > 0; res++) x >>= 1;
/*  34 */     return res;
/*     */   }
/*     */ 
/*     */   public static final float float32unpack(int x) {
/*  38 */     float mantissa = x & 0x1FFFFF;
/*  39 */     float e = (x & 0x7FE00000) >> 21;
/*  40 */     if ((x & 0x80000000) != 0) {
/*  41 */       mantissa = -mantissa;
/*     */     }
/*  43 */     return mantissa * (float)Math.pow(2.0D, e - 788.0D);
/*     */   }
/*     */ 
/*     */   public static final int lookup1Values(int a, int b) {
/*  47 */     int res = (int)Math.pow(2.718281828459045D, Math.log(a) / b);
/*  48 */     return intPow(res + 1, b) <= a ? res + 1 : res;
/*     */   }
/*     */ 
/*     */   public static final int intPow(int base, int e) {
/*  52 */     int res = 1;
/*  53 */     for (; e > 0; res *= base) e--;
/*  54 */     return res;
/*     */   }
/*     */ 
/*     */   public static final boolean isBitSet(int value, int bit) {
/*  58 */     return (value & 1 << bit) != 0;
/*     */   }
/*     */ 
/*     */   public static final int icount(int value) {
/*  62 */     int res = 0;
/*  63 */     while (value > 0) {
/*  64 */       res += (value & 0x1);
/*  65 */       value >>= 1;
/*     */     }
/*  67 */     return res;
/*     */   }
/*     */ 
/*     */   public static final int lowNeighbour(int[] v, int x) {
/*  71 */     int max = -1; int n = 0;
/*  72 */     for (int i = 0; (i < v.length) && (i < x); i++) {
/*  73 */       if ((v[i] > max) && (v[i] < v[x])) {
/*  74 */         max = v[i];
/*  75 */         n = i;
/*     */       }
/*     */     }
/*  78 */     return n;
/*     */   }
/*     */ 
/*     */   public static final int highNeighbour(int[] v, int x) {
/*  82 */     int min = 2147483647; int n = 0;
/*  83 */     for (int i = 0; (i < v.length) && (i < x); i++) {
/*  84 */       if ((v[i] < min) && (v[i] > v[x])) {
/*  85 */         min = v[i];
/*  86 */         n = i;
/*     */       }
/*     */     }
/*  89 */     return n;
/*     */   }
/*     */ 
/*     */   public static final int renderPoint(int x0, int x1, int y0, int y1, int x) {
/*  93 */     int dy = y1 - y0;
/*  94 */     int ady = dy < 0 ? -dy : dy;
/*  95 */     int off = ady * (x - x0) / (x1 - x0);
/*  96 */     return dy < 0 ? y0 - off : y0 + off;
/*     */   }
/*     */ 
/*     */   public static final void renderLine(int x0, int y0, int x1, int y1, float[] v) {
/* 100 */     int dy = y1 - y0;
/* 101 */     int adx = x1 - x0;
/* 102 */     int base = dy / adx;
/* 103 */     int sy = dy < 0 ? base - 1 : base + 1;
/* 104 */     int x = x0;
/* 105 */     int y = y0;
/* 106 */     int err = 0;
/* 107 */     int ady = (dy < 0 ? -dy : dy) - (base > 0 ? base * adx : -base * adx);
/*     */ 
/* 109 */     v[x] *= Floor.DB_STATIC_TABLE[y];
/* 110 */     for (x = x0 + 1; x < x1; x++) {
/* 111 */       err += ady;
/* 112 */       if (err >= adx) {
/* 113 */         err -= adx;
/*     */         int tmp139_138 = (y + sy); y = tmp139_138; v[x] *= Floor.DB_STATIC_TABLE[tmp139_138];
/*     */       }
/*     */       else
/*     */       {
/*     */         int tmp162_161 = (y + base); y = tmp162_161; v[x] *= Floor.DB_STATIC_TABLE[tmp162_161];
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.Util
 * JD-Core Version:    0.6.2
 */