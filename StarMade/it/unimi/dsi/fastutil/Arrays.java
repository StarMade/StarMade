/*     */ package it.unimi.dsi.fastutil;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.IntComparator;
/*     */ 
/*     */ public class Arrays
/*     */ {
/*     */   private static final int SMALL = 7;
/*     */   private static final int MEDIUM = 40;
/*     */ 
/*     */   public static void ensureFromTo(int arrayLength, int from, int to)
/*     */   {
/*  47 */     if (from < 0) throw new ArrayIndexOutOfBoundsException("Start index (" + from + ") is negative");
/*  48 */     if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/*  49 */     if (to > arrayLength) throw new ArrayIndexOutOfBoundsException("End index (" + to + ") is greater than array length (" + arrayLength + ")");
/*     */   }
/*     */ 
/*     */   public static void ensureOffsetLength(int arrayLength, int offset, int length)
/*     */   {
/*  63 */     if (offset < 0) throw new ArrayIndexOutOfBoundsException("Offset (" + offset + ") is negative");
/*  64 */     if (length < 0) throw new IllegalArgumentException("Length (" + length + ") is negative");
/*  65 */     if (offset + length > arrayLength) throw new ArrayIndexOutOfBoundsException("Last index (" + (offset + length) + ") is greater than array length (" + arrayLength + ")");
/*     */   }
/*     */ 
/*     */   private static void inPlaceMerge(int from, int mid, int to, IntComparator comp, Swapper swapper)
/*     */   {
/*  78 */     if ((from >= mid) || (mid >= to)) return;
/*  79 */     if (to - from == 2) {
/*  80 */       if (comp.compare(mid, from) < 0) swapper.swap(from, mid);
/*     */       return;
/*     */     }
/*     */     int secondCut;
/*     */     int secondCut;
/*     */     int firstCut;
/*  87 */     if (mid - from > to - mid) {
/*  88 */       int firstCut = from + (mid - from) / 2;
/*  89 */       secondCut = lowerBound(mid, to, firstCut, comp);
/*     */     }
/*     */     else {
/*  92 */       secondCut = mid + (to - mid) / 2;
/*  93 */       firstCut = upperBound(from, mid, secondCut, comp);
/*     */     }
/*     */ 
/*  96 */     int first2 = firstCut;
/*  97 */     int middle2 = mid;
/*  98 */     int last2 = secondCut;
/*  99 */     if ((middle2 != first2) && (middle2 != last2)) {
/* 100 */       int first1 = first2;
/* 101 */       int last1 = middle2;
/* 102 */       while (first1 < --last1)
/* 103 */         swapper.swap(first1++, last1);
/* 104 */       first1 = middle2;
/* 105 */       last1 = last2;
/* 106 */       while (first1 < --last1)
/* 107 */         swapper.swap(first1++, last1);
/* 108 */       first1 = first2;
/* 109 */       last1 = last2;
/* 110 */       while (first1 < --last1) {
/* 111 */         swapper.swap(first1++, last1);
/*     */       }
/*     */     }
/* 114 */     mid = firstCut + (secondCut - mid);
/* 115 */     inPlaceMerge(from, firstCut, mid, comp, swapper);
/* 116 */     inPlaceMerge(mid, secondCut, to, comp, swapper);
/*     */   }
/*     */ 
/*     */   private static int lowerBound(int from, int to, int pos, IntComparator comp)
/*     */   {
/* 133 */     int len = to - from;
/* 134 */     while (len > 0) {
/* 135 */       int half = len / 2;
/* 136 */       int middle = from + half;
/* 137 */       if (comp.compare(middle, pos) < 0) {
/* 138 */         from = middle + 1;
/* 139 */         len -= half + 1;
/*     */       }
/*     */       else {
/* 142 */         len = half;
/*     */       }
/*     */     }
/* 145 */     return from;
/*     */   }
/*     */ 
/*     */   private static int upperBound(int from, int mid, int pos, IntComparator comp)
/*     */   {
/* 163 */     int len = mid - from;
/* 164 */     while (len > 0) {
/* 165 */       int half = len / 2;
/* 166 */       int middle = from + half;
/* 167 */       if (comp.compare(pos, middle) < 0) {
/* 168 */         len = half;
/*     */       }
/*     */       else {
/* 171 */         from = middle + 1;
/* 172 */         len -= half + 1;
/*     */       }
/*     */     }
/* 175 */     return from;
/*     */   }
/*     */ 
/*     */   private static int med3(int a, int b, int c, IntComparator comp)
/*     */   {
/* 182 */     int ab = comp.compare(a, b);
/* 183 */     int ac = comp.compare(a, c);
/* 184 */     int bc = comp.compare(b, c);
/* 185 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*     */   }
/*     */ 
/*     */   public static void mergeSort(int from, int to, IntComparator c, Swapper swapper)
/*     */   {
/* 211 */     int length = to - from;
/*     */ 
/* 214 */     if (length < 7) {
/* 215 */       for (int i = from; i < to; i++) {
/* 216 */         for (int j = i; (j > from) && (c.compare(j - 1, j) > 0); j--) {
/* 217 */           swapper.swap(j, j - 1);
/*     */         }
/*     */       }
/* 220 */       return;
/*     */     }
/*     */ 
/* 224 */     int mid = from + to >>> 1;
/* 225 */     mergeSort(from, mid, c, swapper);
/* 226 */     mergeSort(mid, to, c, swapper);
/*     */ 
/* 230 */     if (c.compare(mid - 1, mid) <= 0) return;
/*     */ 
/* 233 */     inPlaceMerge(from, mid, to, c, swapper);
/*     */   }
/*     */ 
/*     */   public static void quickSort(int from, int to, IntComparator comp, Swapper swapper)
/*     */   {
/* 250 */     int len = to - from;
/*     */ 
/* 252 */     if (len < 7) {
/* 253 */       for (int i = from; i < to; i++) {
/* 254 */         for (int j = i; (j > from) && (comp.compare(j - 1, j) > 0); j--)
/* 255 */           swapper.swap(j, j - 1);
/*     */       }
/* 257 */       return;
/*     */     }
/*     */ 
/* 261 */     int m = from + len / 2;
/* 262 */     if (len > 7) {
/* 263 */       int l = from;
/* 264 */       int n = to - 1;
/* 265 */       if (len > 40) {
/* 266 */         int s = len / 8;
/* 267 */         l = med3(l, l + s, l + 2 * s, comp);
/* 268 */         m = med3(m - s, m, m + s, comp);
/* 269 */         n = med3(n - 2 * s, n - s, n, comp);
/*     */       }
/* 271 */       m = med3(l, m, n, comp);
/*     */     }
/*     */ 
/* 275 */     int a = from;
/* 276 */     int b = a;
/* 277 */     int c = to - 1;
/*     */ 
/* 279 */     int d = c;
/*     */     while (true)
/*     */     {
/*     */       int comparison;
/* 282 */       if ((b <= c) && ((comparison = comp.compare(b, m)) <= 0)) {
/* 283 */         if (comparison == 0) {
/* 284 */           if (a == m) m = b;
/* 285 */           else if (b == m) m = a;
/* 286 */           swapper.swap(a++, b);
/*     */         }
/* 288 */         b++;
/*     */       }
/*     */       else
/*     */       {
/*     */         int comparison;
/* 290 */         while ((c >= b) && ((comparison = comp.compare(c, m)) >= 0)) {
/* 291 */           if (comparison == 0) {
/* 292 */             if (c == m) m = d;
/* 293 */             else if (d == m) m = c;
/* 294 */             swapper.swap(c, d--);
/*     */           }
/* 296 */           c--;
/*     */         }
/* 298 */         if (b > c) break;
/* 299 */         if (b == m) m = d;
/* 300 */         else if (c == m) m = c;
/* 301 */         swapper.swap(b++, c--);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 306 */     int n = to;
/* 307 */     int s = Math.min(a - from, b - a);
/* 308 */     vecSwap(swapper, from, b - s, s);
/* 309 */     s = Math.min(d - c, n - d - 1);
/* 310 */     vecSwap(swapper, b, n - s, s);
/*     */ 
/* 313 */     if ((s = b - a) > 1) quickSort(from, from + s, comp, swapper);
/* 314 */     if ((s = d - c) > 1) quickSort(n - s, n, comp, swapper);
/*     */   }
/*     */ 
/*     */   private static void vecSwap(Swapper swapper, int from, int l, int s)
/*     */   {
/* 322 */     for (int i = 0; i < s; l++) { swapper.swap(from, l); i++; from++;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.Arrays
 * JD-Core Version:    0.6.2
 */