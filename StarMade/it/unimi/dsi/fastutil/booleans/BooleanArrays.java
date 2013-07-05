/*     */ package it.unimi.dsi.fastutil.booleans;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Hash.Strategy;
/*     */ import java.io.Serializable;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class BooleanArrays
/*     */ {
/*     */   public static final long ONEOVERPHI = 106039L;
/*  87 */   public static final boolean[] EMPTY_ARRAY = new boolean[0];
/*     */   private static final int SMALL = 7;
/*     */   private static final int MEDIUM = 50;
/* 705 */   public static final Hash.Strategy<boolean[]> HASH_STRATEGY = new ArrayHashStrategy(null);
/*     */ 
/*     */   public static boolean[] ensureCapacity(boolean[] array, int length)
/*     */   {
/* 100 */     if (length > array.length) {
/* 101 */       boolean[] t = new boolean[length];
/*     */ 
/* 103 */       System.arraycopy(array, 0, t, 0, array.length);
/* 104 */       return t;
/*     */     }
/* 106 */     return array;
/*     */   }
/*     */ 
/*     */   public static boolean[] ensureCapacity(boolean[] array, int length, int preserve)
/*     */   {
/* 118 */     if (length > array.length) {
/* 119 */       boolean[] t = new boolean[length];
/*     */ 
/* 121 */       System.arraycopy(array, 0, t, 0, preserve);
/* 122 */       return t;
/*     */     }
/* 124 */     return array;
/*     */   }
/*     */ 
/*     */   public static boolean[] grow(boolean[] array, int length)
/*     */   {
/* 142 */     if (length > array.length) {
/* 143 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/* 144 */       boolean[] t = new boolean[newLength];
/*     */ 
/* 146 */       System.arraycopy(array, 0, t, 0, array.length);
/* 147 */       return t;
/*     */     }
/* 149 */     return array;
/*     */   }
/*     */ 
/*     */   public static boolean[] grow(boolean[] array, int length, int preserve)
/*     */   {
/* 168 */     if (length > array.length) {
/* 169 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/* 170 */       boolean[] t = new boolean[newLength];
/*     */ 
/* 172 */       System.arraycopy(array, 0, t, 0, preserve);
/* 173 */       return t;
/*     */     }
/* 175 */     return array;
/*     */   }
/*     */ 
/*     */   public static boolean[] trim(boolean[] array, int length)
/*     */   {
/* 188 */     if (length >= array.length) return array;
/* 189 */     boolean[] t = length == 0 ? EMPTY_ARRAY : new boolean[length];
/*     */ 
/* 191 */     System.arraycopy(array, 0, t, 0, length);
/* 192 */     return t;
/*     */   }
/*     */ 
/*     */   public static boolean[] setLength(boolean[] array, int length)
/*     */   {
/* 208 */     if (length == array.length) return array;
/* 209 */     if (length < array.length) return trim(array, length);
/* 210 */     return ensureCapacity(array, length);
/*     */   }
/*     */ 
/*     */   public static boolean[] copy(boolean[] array, int offset, int length)
/*     */   {
/* 220 */     ensureOffsetLength(array, offset, length);
/* 221 */     boolean[] a = length == 0 ? EMPTY_ARRAY : new boolean[length];
/*     */ 
/* 223 */     System.arraycopy(array, offset, a, 0, length);
/* 224 */     return a;
/*     */   }
/*     */ 
/*     */   public static boolean[] copy(boolean[] array)
/*     */   {
/* 232 */     return (boolean[])array.clone();
/*     */   }
/*     */ 
/*     */   public static void fill(boolean[] array, boolean value)
/*     */   {
/* 243 */     int i = array.length;
/* 244 */     while (i-- != 0) array[i] = value;
/*     */   }
/*     */ 
/*     */   public static void fill(boolean[] array, int from, int to, boolean value)
/*     */   {
/* 258 */     ensureFromTo(array, from, to);
/* 259 */     for (from != 0; to-- != 0; array[to] = value);
/* 260 */     for (int i = from; i < to; i++) array[i] = value;
/*     */   }
/*     */ 
/*     */   public static boolean equals(boolean[] a1, boolean[] a2)
/*     */   {
/* 272 */     int i = a1.length;
/* 273 */     if (i != a2.length) return false;
/* 274 */     while (i-- != 0) if (a1[i] != a2[i]) return false;
/* 275 */     return true;
/*     */   }
/*     */ 
/*     */   public static void ensureFromTo(boolean[] a, int from, int to)
/*     */   {
/* 288 */     it.unimi.dsi.fastutil.Arrays.ensureFromTo(a.length, from, to);
/*     */   }
/*     */ 
/*     */   public static void ensureOffsetLength(boolean[] a, int offset, int length)
/*     */   {
/* 301 */     it.unimi.dsi.fastutil.Arrays.ensureOffsetLength(a.length, offset, length);
/*     */   }
/*     */ 
/*     */   private static void swap(boolean[] x, int a, int b)
/*     */   {
/* 306 */     boolean t = x[a];
/* 307 */     x[a] = x[b];
/* 308 */     x[b] = t;
/*     */   }
/*     */   private static void vecSwap(boolean[] x, int a, int b, int n) {
/* 311 */     for (int i = 0; i < n; b++) { swap(x, a, b); i++; a++; } 
/*     */   }
/*     */ 
/* 314 */   private static int med3(boolean[] x, int a, int b, int c, BooleanComparator comp) { int ab = comp.compare(x[a], x[b]);
/* 315 */     int ac = comp.compare(x[a], x[c]);
/* 316 */     int bc = comp.compare(x[b], x[c]);
/* 317 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*     */   }
/*     */ 
/*     */   private static void selectionSort(boolean[] a, int from, int to, BooleanComparator comp)
/*     */   {
/* 322 */     for (int i = from; i < to - 1; i++) {
/* 323 */       int m = i;
/* 324 */       for (int j = i + 1; j < to; j++) if (comp.compare(a[j], a[m]) < 0) m = j;
/* 325 */       if (m != i) {
/* 326 */         boolean u = a[i];
/* 327 */         a[i] = a[m];
/* 328 */         a[m] = u;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 333 */   private static void insertionSort(boolean[] a, int from, int to, BooleanComparator comp) { int i = from;
/*     */     while (true) { i++; if (i >= to) break;
/* 334 */       boolean t = a[i];
/* 335 */       int j = i;
/* 336 */       for (boolean u = a[(j - 1)]; comp.compare(t, u) < 0; u = a[(--j - 1)]) {
/* 337 */         a[j] = u;
/* 338 */         if (from == j - 1) {
/* 339 */           j--;
/* 340 */           break;
/*     */         }
/*     */       }
/* 343 */       a[j] = t; }
/*     */   }
/*     */ 
/*     */   private static void selectionSort(boolean[] a, int from, int to)
/*     */   {
/* 348 */     for (int i = from; i < to - 1; i++) {
/* 349 */       int m = i;
/* 350 */       for (int j = i + 1; j < to; j++) if ((a[j] == 0) && (a[m] != 0)) m = j;
/* 351 */       if (m != i) {
/* 352 */         boolean u = a[i];
/* 353 */         a[i] = a[m];
/* 354 */         a[m] = u;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void insertionSort(boolean[] a, int from, int to) {
/* 360 */     int i = from;
/*     */     while (true) { i++; if (i >= to) break;
/* 361 */       boolean t = a[i];
/* 362 */       int j = i;
/* 363 */       for (boolean u = a[(j - 1)]; (!t) && (u); u = a[(--j - 1)]) {
/* 364 */         a[j] = u;
/* 365 */         if (from == j - 1) {
/* 366 */           j--;
/* 367 */           break;
/*     */         }
/*     */       }
/* 370 */       a[j] = t;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void quickSort(boolean[] x, int from, int to, BooleanComparator comp)
/*     */   {
/* 387 */     int len = to - from;
/*     */ 
/* 389 */     if (len < 7) {
/* 390 */       selectionSort(x, from, to, comp);
/* 391 */       return;
/*     */     }
/*     */ 
/* 394 */     int m = from + len / 2;
/* 395 */     if (len > 7) {
/* 396 */       int l = from;
/* 397 */       int n = to - 1;
/* 398 */       if (len > 50) {
/* 399 */         int s = len / 8;
/* 400 */         l = med3(x, l, l + s, l + 2 * s, comp);
/* 401 */         m = med3(x, m - s, m, m + s, comp);
/* 402 */         n = med3(x, n - 2 * s, n - s, n, comp);
/*     */       }
/* 404 */       m = med3(x, l, m, n, comp);
/*     */     }
/* 406 */     boolean v = x[m];
/*     */ 
/* 408 */     int a = from; int b = a; int c = to - 1; int d = c;
/*     */     while (true)
/*     */     {
/*     */       int comparison;
/* 411 */       if ((b <= c) && ((comparison = comp.compare(x[b], v)) <= 0)) {
/* 412 */         if (comparison == 0) swap(x, a++, b);
/* 413 */         b++;
/*     */       }
/*     */       else
/*     */       {
/*     */         int comparison;
/* 415 */         while ((c >= b) && ((comparison = comp.compare(x[c], v)) >= 0)) {
/* 416 */           if (comparison == 0) swap(x, c, d--);
/* 417 */           c--;
/*     */         }
/* 419 */         if (b > c) break;
/* 420 */         swap(x, b++, c--);
/*     */       }
/*     */     }
/* 423 */     int n = to;
/* 424 */     int s = Math.min(a - from, b - a);
/* 425 */     vecSwap(x, from, b - s, s);
/* 426 */     s = Math.min(d - c, n - d - 1);
/* 427 */     vecSwap(x, b, n - s, s);
/*     */ 
/* 429 */     if ((s = b - a) > 1) quickSort(x, from, from + s, comp);
/* 430 */     if ((s = d - c) > 1) quickSort(x, n - s, n, comp);
/*     */   }
/*     */ 
/*     */   public static void quickSort(boolean[] x, BooleanComparator comp)
/*     */   {
/* 444 */     quickSort(x, 0, x.length, comp);
/*     */   }
/*     */ 
/*     */   private static int med3(boolean[] x, int a, int b, int c) {
/* 448 */     int ab = x[a] == x[b] ? 0 : (x[a] == 0) && (x[b] != 0) ? -1 : 1;
/* 449 */     int ac = x[a] == x[c] ? 0 : (x[a] == 0) && (x[c] != 0) ? -1 : 1;
/* 450 */     int bc = x[b] == x[c] ? 0 : (x[b] == 0) && (x[c] != 0) ? -1 : 1;
/* 451 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*     */   }
/*     */ 
/*     */   public static void quickSort(boolean[] x, int from, int to)
/*     */   {
/* 468 */     int len = to - from;
/*     */ 
/* 470 */     if (len < 7) {
/* 471 */       selectionSort(x, from, to);
/* 472 */       return;
/*     */     }
/*     */ 
/* 475 */     int m = from + len / 2;
/* 476 */     if (len > 7) {
/* 477 */       int l = from;
/* 478 */       int n = to - 1;
/* 479 */       if (len > 50) {
/* 480 */         int s = len / 8;
/* 481 */         l = med3(x, l, l + s, l + 2 * s);
/* 482 */         m = med3(x, m - s, m, m + s);
/* 483 */         n = med3(x, n - 2 * s, n - s, n);
/*     */       }
/* 485 */       m = med3(x, l, m, n);
/*     */     }
/* 487 */     boolean v = x[m];
/*     */ 
/* 489 */     int a = from; int b = a; int c = to - 1; int d = c;
/*     */     while (true)
/*     */     {
/* 492 */       if (b <= c)
/*     */       {
/*     */         int comparison;
/* 492 */         if ((comparison = x[b] == v ? 0 : (x[b] == 0) && (v) ? -1 : 1) <= 0) {
/* 493 */           if (comparison == 0) swap(x, a++, b);
/* 494 */           b++;
/*     */         }
/*     */       } else { while (c >= b)
/*     */         {
/*     */           int comparison;
/* 496 */           if ((comparison = x[c] == v ? 0 : (x[c] == 0) && (v) ? -1 : 1) < 0) break;
/* 497 */           if (comparison == 0) swap(x, c, d--);
/* 498 */           c--;
/*     */         }
/* 500 */         if (b > c) break;
/* 501 */         swap(x, b++, c--);
/*     */       }
/*     */     }
/* 504 */     int n = to;
/* 505 */     int s = Math.min(a - from, b - a);
/* 506 */     vecSwap(x, from, b - s, s);
/* 507 */     s = Math.min(d - c, n - d - 1);
/* 508 */     vecSwap(x, b, n - s, s);
/*     */ 
/* 510 */     if ((s = b - a) > 1) quickSort(x, from, from + s);
/* 511 */     if ((s = d - c) > 1) quickSort(x, n - s, n);
/*     */   }
/*     */ 
/*     */   public static void quickSort(boolean[] x)
/*     */   {
/* 523 */     quickSort(x, 0, x.length);
/*     */   }
/*     */ 
/*     */   public static void mergeSort(boolean[] a, int from, int to, boolean[] supp)
/*     */   {
/* 537 */     int len = to - from;
/*     */ 
/* 539 */     if (len < 7) {
/* 540 */       insertionSort(a, from, to);
/* 541 */       return;
/*     */     }
/*     */ 
/* 544 */     int mid = from + to >>> 1;
/* 545 */     mergeSort(supp, from, mid, a);
/* 546 */     mergeSort(supp, mid, to, a);
/*     */ 
/* 549 */     if ((supp[(mid - 1)] == 0) || (supp[mid] != 0)) {
/* 550 */       System.arraycopy(supp, from, a, from, len);
/* 551 */       return;
/*     */     }
/*     */ 
/* 554 */     int i = from; int p = from; for (int q = mid; i < to; i++)
/* 555 */       if ((q >= to) || ((p < mid) && ((supp[p] == 0) || (supp[q] != 0)))) a[i] = supp[(p++)]; else
/* 556 */         a[i] = supp[(q++)];
/*     */   }
/*     */ 
/*     */   public static void mergeSort(boolean[] a, int from, int to)
/*     */   {
/* 569 */     mergeSort(a, from, to, (boolean[])a.clone());
/*     */   }
/*     */ 
/*     */   public static void mergeSort(boolean[] a)
/*     */   {
/* 579 */     mergeSort(a, 0, a.length);
/*     */   }
/*     */ 
/*     */   public static void mergeSort(boolean[] a, int from, int to, BooleanComparator comp, boolean[] supp)
/*     */   {
/* 595 */     int len = to - from;
/*     */ 
/* 597 */     if (len < 7) {
/* 598 */       insertionSort(a, from, to, comp);
/* 599 */       return;
/*     */     }
/*     */ 
/* 602 */     int mid = from + to >>> 1;
/* 603 */     mergeSort(supp, from, mid, comp, a);
/* 604 */     mergeSort(supp, mid, to, comp, a);
/*     */ 
/* 607 */     if (comp.compare(supp[(mid - 1)], supp[mid]) <= 0) {
/* 608 */       System.arraycopy(supp, from, a, from, len);
/* 609 */       return;
/*     */     }
/*     */ 
/* 612 */     int i = from; int p = from; for (int q = mid; i < to; i++)
/* 613 */       if ((q >= to) || ((p < mid) && (comp.compare(supp[p], supp[q]) <= 0))) a[i] = supp[(p++)]; else
/* 614 */         a[i] = supp[(q++)];
/*     */   }
/*     */ 
/*     */   public static void mergeSort(boolean[] a, int from, int to, BooleanComparator comp)
/*     */   {
/* 629 */     mergeSort(a, from, to, comp, (boolean[])a.clone());
/*     */   }
/*     */ 
/*     */   public static void mergeSort(boolean[] a, BooleanComparator comp)
/*     */   {
/* 641 */     mergeSort(a, 0, a.length, comp);
/*     */   }
/*     */ 
/*     */   public static boolean[] shuffle(boolean[] a, int from, int to, Random random)
/*     */   {
/* 652 */     for (int i = to - from; i-- != 0; ) {
/* 653 */       int p = random.nextInt(i + 1);
/* 654 */       boolean t = a[(from + i)];
/* 655 */       a[(from + i)] = a[(from + p)];
/* 656 */       a[(from + p)] = t;
/*     */     }
/* 658 */     return a;
/*     */   }
/*     */ 
/*     */   public static boolean[] shuffle(boolean[] a, Random random)
/*     */   {
/* 667 */     for (int i = a.length; i-- != 0; ) {
/* 668 */       int p = random.nextInt(i + 1);
/* 669 */       boolean t = a[i];
/* 670 */       a[i] = a[p];
/* 671 */       a[p] = t;
/*     */     }
/* 673 */     return a;
/*     */   }
/*     */ 
/*     */   public static boolean[] reverse(boolean[] a)
/*     */   {
/* 681 */     int length = a.length;
/* 682 */     for (int i = length / 2; i-- != 0; ) {
/* 683 */       boolean t = a[(length - i - 1)];
/* 684 */       a[(length - i - 1)] = a[i];
/* 685 */       a[i] = t;
/*     */     }
/* 687 */     return a;
/*     */   }
/*     */   private static final class ArrayHashStrategy implements Hash.Strategy<boolean[]>, Serializable {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public int hashCode(boolean[] o) {
/* 693 */       return java.util.Arrays.hashCode(o);
/*     */     }
/*     */     public boolean equals(boolean[] a, boolean[] b) {
/* 696 */       return BooleanArrays.equals(a, b);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanArrays
 * JD-Core Version:    0.6.2
 */