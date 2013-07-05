/*     */ package it.unimi.dsi.fastutil;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.IntBigArrays;
/*     */ import it.unimi.dsi.fastutil.longs.LongComparator;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class BigArrays
/*     */ {
/*     */   public static final int SEGMENT_SHIFT = 27;
/*     */   public static final int SEGMENT_SIZE = 134217728;
/*     */   public static final int SEGMENT_MASK = 134217727;
/*     */   private static final int SMALL = 7;
/*     */   private static final int MEDIUM = 40;
/*     */ 
/*     */   public static int segment(long index)
/*     */   {
/* 146 */     return (int)(index >>> 27);
/*     */   }
/*     */ 
/*     */   public static int displacement(long index)
/*     */   {
/* 155 */     return (int)(index & 0x7FFFFFF);
/*     */   }
/*     */ 
/*     */   public static long start(int segment)
/*     */   {
/* 164 */     return segment << 27;
/*     */   }
/*     */ 
/*     */   public static long index(int segment, int displacement)
/*     */   {
/* 175 */     return start(segment) + displacement;
/*     */   }
/*     */ 
/*     */   public static void ensureFromTo(long bigArrayLength, long from, long to)
/*     */   {
/* 189 */     if (from < 0L) throw new ArrayIndexOutOfBoundsException("Start index (" + from + ") is negative");
/* 190 */     if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 191 */     if (to > bigArrayLength) throw new ArrayIndexOutOfBoundsException("End index (" + to + ") is greater than big-array length (" + bigArrayLength + ")");
/*     */   }
/*     */ 
/*     */   public static void ensureOffsetLength(long bigArrayLength, long offset, long length)
/*     */   {
/* 205 */     if (offset < 0L) throw new ArrayIndexOutOfBoundsException("Offset (" + offset + ") is negative");
/* 206 */     if (length < 0L) throw new IllegalArgumentException("Length (" + length + ") is negative");
/* 207 */     if (offset + length > bigArrayLength) throw new ArrayIndexOutOfBoundsException("Last index (" + (offset + length) + ") is greater than big-array length (" + bigArrayLength + ")");
/*     */   }
/*     */ 
/*     */   private static void inPlaceMerge(long from, long mid, long to, LongComparator comp, BigSwapper swapper)
/*     */   {
/* 221 */     if ((from >= mid) || (mid >= to)) return;
/* 222 */     if (to - from == 2L) {
/* 223 */       if (comp.compare(mid, from) < 0)
/* 224 */         swapper.swap(from, mid);
/*     */       return;
/*     */     }
/*     */     long secondCut;
/*     */     long secondCut;
/*     */     long firstCut;
/* 230 */     if (mid - from > to - mid) {
/* 231 */       long firstCut = from + (mid - from) / 2L;
/* 232 */       secondCut = lowerBound(mid, to, firstCut, comp);
/*     */     }
/*     */     else {
/* 235 */       secondCut = mid + (to - mid) / 2L;
/* 236 */       firstCut = upperBound(from, mid, secondCut, comp);
/*     */     }
/*     */ 
/* 239 */     long first2 = firstCut;
/* 240 */     long middle2 = mid;
/* 241 */     long last2 = secondCut;
/* 242 */     if ((middle2 != first2) && (middle2 != last2)) {
/* 243 */       long first1 = first2;
/* 244 */       long last1 = middle2;
/* 245 */       while (first1 < --last1)
/* 246 */         swapper.swap(first1++, last1);
/* 247 */       first1 = middle2;
/* 248 */       last1 = last2;
/* 249 */       while (first1 < --last1)
/* 250 */         swapper.swap(first1++, last1);
/* 251 */       first1 = first2;
/* 252 */       last1 = last2;
/* 253 */       while (first1 < --last1) {
/* 254 */         swapper.swap(first1++, last1);
/*     */       }
/*     */     }
/* 257 */     mid = firstCut + (secondCut - mid);
/* 258 */     inPlaceMerge(from, firstCut, mid, comp, swapper);
/* 259 */     inPlaceMerge(mid, secondCut, to, comp, swapper);
/*     */   }
/*     */ 
/*     */   private static long lowerBound(long mid, long to, long firstCut, LongComparator comp)
/*     */   {
/* 275 */     long len = to - mid;
/* 276 */     while (len > 0L) {
/* 277 */       long half = len / 2L;
/* 278 */       long middle = mid + half;
/* 279 */       if (comp.compare(middle, firstCut) < 0) {
/* 280 */         mid = middle + 1L;
/* 281 */         len -= half + 1L;
/*     */       }
/*     */       else {
/* 284 */         len = half;
/*     */       }
/*     */     }
/* 287 */     return mid;
/*     */   }
/*     */ 
/*     */   private static long med3(long a, long b, long c, LongComparator comp)
/*     */   {
/* 292 */     int ab = comp.compare(a, b);
/* 293 */     int ac = comp.compare(a, c);
/* 294 */     int bc = comp.compare(b, c);
/* 295 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*     */   }
/*     */ 
/*     */   public static void mergeSort(long from, long to, LongComparator comp, BigSwapper swapper)
/*     */   {
/* 314 */     long length = to - from;
/*     */ 
/* 317 */     if (length < 7L) {
/* 318 */       for (long i = from; i < to; i += 1L) {
/* 319 */         for (long j = i; (j > from) && (comp.compare(j - 1L, j) > 0); j -= 1L) {
/* 320 */           swapper.swap(j, j - 1L);
/*     */         }
/*     */       }
/* 323 */       return;
/*     */     }
/*     */ 
/* 327 */     long mid = from + to >>> 1;
/* 328 */     mergeSort(from, mid, comp, swapper);
/* 329 */     mergeSort(mid, to, comp, swapper);
/*     */ 
/* 333 */     if (comp.compare(mid - 1L, mid) <= 0) return;
/*     */ 
/* 336 */     inPlaceMerge(from, mid, to, comp, swapper);
/*     */   }
/*     */ 
/*     */   public static void quickSort(long from, long to, LongComparator comp, BigSwapper swapper)
/*     */   {
/* 353 */     long len = to - from;
/*     */ 
/* 355 */     if (len < 7L) {
/* 356 */       for (long i = from; i < to; i += 1L) {
/* 357 */         for (long j = i; (j > from) && (comp.compare(j - 1L, j) > 0); j -= 1L)
/* 358 */           swapper.swap(j, j - 1L);
/*     */       }
/* 360 */       return;
/*     */     }
/*     */ 
/* 364 */     long m = from + len / 2L;
/* 365 */     if (len > 7L) {
/* 366 */       long l = from; long n = to - 1L;
/* 367 */       if (len > 40L) {
/* 368 */         long s = len / 8L;
/* 369 */         l = med3(l, l + s, l + 2L * s, comp);
/* 370 */         m = med3(m - s, m, m + s, comp);
/* 371 */         n = med3(n - 2L * s, n - s, n, comp);
/*     */       }
/* 373 */       m = med3(l, m, n, comp);
/*     */     }
/*     */ 
/* 377 */     long a = from; long b = a; long c = to - 1L; long d = c;
/*     */     while (true)
/*     */     {
/*     */       int comparison;
/* 381 */       if ((b <= c) && ((comparison = comp.compare(b, m)) <= 0)) {
/* 382 */         if (comparison == 0) {
/* 383 */           if (a == m) m = b;
/* 384 */           else if (b == m) m = a;
/* 385 */           swapper.swap(a++, b);
/*     */         }
/* 387 */         b += 1L;
/*     */       }
/*     */       else
/*     */       {
/*     */         int comparison;
/* 389 */         while ((c >= b) && ((comparison = comp.compare(c, m)) >= 0)) {
/* 390 */           if (comparison == 0) {
/* 391 */             if (c == m) m = d;
/* 392 */             else if (d == m) m = c;
/* 393 */             swapper.swap(c, d--);
/*     */           }
/* 395 */           c -= 1L;
/*     */         }
/* 397 */         if (b > c) break;
/* 398 */         if (b == m) m = d;
/* 399 */         else if (c == m) m = c;
/* 400 */         swapper.swap(b++, c--);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 405 */     long n = from + len;
/* 406 */     long s = Math.min(a - from, b - a);
/* 407 */     vecSwap(swapper, from, b - s, s);
/* 408 */     s = Math.min(d - c, n - d - 1L);
/* 409 */     vecSwap(swapper, b, n - s, s);
/*     */ 
/* 412 */     if ((s = b - a) > 1L) quickSort(from, from + s, comp, swapper);
/* 413 */     if ((s = d - c) > 1L) quickSort(n - s, n, comp, swapper);
/*     */   }
/*     */ 
/*     */   private static long upperBound(long from, long mid, long secondCut, LongComparator comp)
/*     */   {
/* 429 */     long len = mid - from;
/* 430 */     while (len > 0L) {
/* 431 */       long half = len / 2L;
/* 432 */       long middle = from + half;
/* 433 */       if (comp.compare(secondCut, middle) < 0) {
/* 434 */         len = half;
/*     */       }
/*     */       else {
/* 437 */         from = middle + 1L;
/* 438 */         len -= half + 1L;
/*     */       }
/*     */     }
/* 441 */     return from;
/*     */   }
/*     */ 
/*     */   private static void vecSwap(BigSwapper swapper, long from, long l, long s)
/*     */   {
/* 448 */     for (int i = 0; i < s; l += 1L) { swapper.swap(from, l); i++; from += 1L; }
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg) {
/* 452 */     int[][] a = IntBigArrays.newBigArray(1L << Integer.parseInt(arg[0]));
/*     */ 
/* 455 */     for (int k = 10; k-- != 0; )
/*     */     {
/* 457 */       long start = -System.currentTimeMillis();
/*     */ 
/* 459 */       long x = 0L;
/* 460 */       for (long i = IntBigArrays.length(a); i-- != 0L; x ^= i ^ IntBigArrays.get(a, i));
/* 461 */       if (x == 0L) System.err.println();
/*     */ 
/* 463 */       System.out.println("Single loop: " + (start + System.currentTimeMillis()) + "ms");
/*     */ 
/* 465 */       start = -System.currentTimeMillis();
/*     */ 
/* 467 */       long y = 0L;
/* 468 */       for (int i = a.length; i-- != 0; ) { int[] t = a[i];
/* 470 */         for (int d = t.length; d-- != 0; y ^= t[d] ^ index(i, d));
/*     */       }
/* 472 */       if (y == 0L) System.err.println();
/* 473 */       if (x != y) throw new AssertionError();
/*     */ 
/* 475 */       System.out.println("Double loop: " + (start + System.currentTimeMillis()) + "ms");
/*     */ 
/* 477 */       long z = 0L;
/* 478 */       long j = IntBigArrays.length(a);
/* 479 */       for (int i = a.length; i-- != 0; ) { int[] t = a[i];
/* 481 */         for (int d = t.length; d-- != 0; y ^= t[d] ^ --j);
/*     */       }
/* 483 */       if (z == 0L) System.err.println();
/* 484 */       if (x != z) throw new AssertionError();
/*     */ 
/* 486 */       System.out.println("Double loop (with additional index): " + (start + System.currentTimeMillis()) + "ms");
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.BigArrays
 * JD-Core Version:    0.6.2
 */