/*      */ package it.unimi.dsi.fastutil.longs;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.Hash.Strategy;
/*      */ import it.unimi.dsi.fastutil.ints.IntArrays;
/*      */ import java.io.Serializable;
/*      */ import java.util.Random;
/*      */ 
/*      */ public class LongArrays
/*      */ {
/*      */   public static final long ONEOVERPHI = 106039L;
/*   87 */   public static final long[] EMPTY_ARRAY = new long[0];
/*      */   private static final int SMALL = 7;
/*      */   private static final int MEDIUM = 50;
/*      */   private static final int DIGIT_BITS = 8;
/*      */   private static final int DIGIT_MASK = 255;
/*      */   private static final int DIGITS_PER_ELEMENT = 8;
/* 1456 */   public static final Hash.Strategy<long[]> HASH_STRATEGY = new ArrayHashStrategy(null);
/*      */ 
/*      */   public static long[] ensureCapacity(long[] array, int length)
/*      */   {
/*  100 */     if (length > array.length) {
/*  101 */       long[] t = new long[length];
/*      */ 
/*  103 */       System.arraycopy(array, 0, t, 0, array.length);
/*  104 */       return t;
/*      */     }
/*  106 */     return array;
/*      */   }
/*      */ 
/*      */   public static long[] ensureCapacity(long[] array, int length, int preserve)
/*      */   {
/*  118 */     if (length > array.length) {
/*  119 */       long[] t = new long[length];
/*      */ 
/*  121 */       System.arraycopy(array, 0, t, 0, preserve);
/*  122 */       return t;
/*      */     }
/*  124 */     return array;
/*      */   }
/*      */ 
/*      */   public static long[] grow(long[] array, int length)
/*      */   {
/*  142 */     if (length > array.length) {
/*  143 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  144 */       long[] t = new long[newLength];
/*      */ 
/*  146 */       System.arraycopy(array, 0, t, 0, array.length);
/*  147 */       return t;
/*      */     }
/*  149 */     return array;
/*      */   }
/*      */ 
/*      */   public static long[] grow(long[] array, int length, int preserve)
/*      */   {
/*  168 */     if (length > array.length) {
/*  169 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  170 */       long[] t = new long[newLength];
/*      */ 
/*  172 */       System.arraycopy(array, 0, t, 0, preserve);
/*  173 */       return t;
/*      */     }
/*  175 */     return array;
/*      */   }
/*      */ 
/*      */   public static long[] trim(long[] array, int length)
/*      */   {
/*  188 */     if (length >= array.length) return array;
/*  189 */     long[] t = length == 0 ? EMPTY_ARRAY : new long[length];
/*      */ 
/*  191 */     System.arraycopy(array, 0, t, 0, length);
/*  192 */     return t;
/*      */   }
/*      */ 
/*      */   public static long[] setLength(long[] array, int length)
/*      */   {
/*  208 */     if (length == array.length) return array;
/*  209 */     if (length < array.length) return trim(array, length);
/*  210 */     return ensureCapacity(array, length);
/*      */   }
/*      */ 
/*      */   public static long[] copy(long[] array, int offset, int length)
/*      */   {
/*  220 */     ensureOffsetLength(array, offset, length);
/*  221 */     long[] a = length == 0 ? EMPTY_ARRAY : new long[length];
/*      */ 
/*  223 */     System.arraycopy(array, offset, a, 0, length);
/*  224 */     return a;
/*      */   }
/*      */ 
/*      */   public static long[] copy(long[] array)
/*      */   {
/*  232 */     return (long[])array.clone();
/*      */   }
/*      */ 
/*      */   public static void fill(long[] array, long value)
/*      */   {
/*  243 */     int i = array.length;
/*  244 */     while (i-- != 0) array[i] = value;
/*      */   }
/*      */ 
/*      */   public static void fill(long[] array, int from, int to, long value)
/*      */   {
/*  258 */     ensureFromTo(array, from, to);
/*  259 */     for (from != 0; to-- != 0; array[to] = value);
/*  260 */     for (int i = from; i < to; i++) array[i] = value;
/*      */   }
/*      */ 
/*      */   public static boolean equals(long[] a1, long[] a2)
/*      */   {
/*  272 */     int i = a1.length;
/*  273 */     if (i != a2.length) return false;
/*  274 */     while (i-- != 0) if (a1[i] != a2[i]) return false;
/*  275 */     return true;
/*      */   }
/*      */ 
/*      */   public static void ensureFromTo(long[] a, int from, int to)
/*      */   {
/*  288 */     it.unimi.dsi.fastutil.Arrays.ensureFromTo(a.length, from, to);
/*      */   }
/*      */ 
/*      */   public static void ensureOffsetLength(long[] a, int offset, int length)
/*      */   {
/*  301 */     it.unimi.dsi.fastutil.Arrays.ensureOffsetLength(a.length, offset, length);
/*      */   }
/*      */ 
/*      */   private static void swap(long[] x, int a, int b)
/*      */   {
/*  306 */     long t = x[a];
/*  307 */     x[a] = x[b];
/*  308 */     x[b] = t;
/*      */   }
/*      */   private static void vecSwap(long[] x, int a, int b, int n) {
/*  311 */     for (int i = 0; i < n; b++) { swap(x, a, b); i++; a++; } 
/*      */   }
/*      */ 
/*  314 */   private static int med3(long[] x, int a, int b, int c, LongComparator comp) { int ab = comp.compare(x[a], x[b]);
/*  315 */     int ac = comp.compare(x[a], x[c]);
/*  316 */     int bc = comp.compare(x[b], x[c]);
/*  317 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*      */   }
/*      */ 
/*      */   private static void selectionSort(long[] a, int from, int to, LongComparator comp)
/*      */   {
/*  322 */     for (int i = from; i < to - 1; i++) {
/*  323 */       int m = i;
/*  324 */       for (int j = i + 1; j < to; j++) if (comp.compare(a[j], a[m]) < 0) m = j;
/*  325 */       if (m != i) {
/*  326 */         long u = a[i];
/*  327 */         a[i] = a[m];
/*  328 */         a[m] = u;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*  333 */   private static void insertionSort(long[] a, int from, int to, LongComparator comp) { int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/*  334 */       long t = a[i];
/*  335 */       int j = i;
/*  336 */       for (long u = a[(j - 1)]; comp.compare(t, u) < 0; u = a[(--j - 1)]) {
/*  337 */         a[j] = u;
/*  338 */         if (from == j - 1) {
/*  339 */           j--;
/*  340 */           break;
/*      */         }
/*      */       }
/*  343 */       a[j] = t; }
/*      */   }
/*      */ 
/*      */   private static void selectionSort(long[] a, int from, int to)
/*      */   {
/*  348 */     for (int i = from; i < to - 1; i++) {
/*  349 */       int m = i;
/*  350 */       for (int j = i + 1; j < to; j++) if (a[j] < a[m]) m = j;
/*  351 */       if (m != i) {
/*  352 */         long u = a[i];
/*  353 */         a[i] = a[m];
/*  354 */         a[m] = u;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void insertionSort(long[] a, int from, int to) {
/*  360 */     int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/*  361 */       long t = a[i];
/*  362 */       int j = i;
/*  363 */       for (long u = a[(j - 1)]; t < u; u = a[(--j - 1)]) {
/*  364 */         a[j] = u;
/*  365 */         if (from == j - 1) {
/*  366 */           j--;
/*  367 */           break;
/*      */         }
/*      */       }
/*  370 */       a[j] = t;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void quickSort(long[] x, int from, int to, LongComparator comp)
/*      */   {
/*  387 */     int len = to - from;
/*      */ 
/*  389 */     if (len < 7) {
/*  390 */       selectionSort(x, from, to, comp);
/*  391 */       return;
/*      */     }
/*      */ 
/*  394 */     int m = from + len / 2;
/*  395 */     if (len > 7) {
/*  396 */       int l = from;
/*  397 */       int n = to - 1;
/*  398 */       if (len > 50) {
/*  399 */         int s = len / 8;
/*  400 */         l = med3(x, l, l + s, l + 2 * s, comp);
/*  401 */         m = med3(x, m - s, m, m + s, comp);
/*  402 */         n = med3(x, n - 2 * s, n - s, n, comp);
/*      */       }
/*  404 */       m = med3(x, l, m, n, comp);
/*      */     }
/*  406 */     long v = x[m];
/*      */ 
/*  408 */     int a = from; int b = a; int c = to - 1; int d = c;
/*      */     while (true)
/*      */     {
/*      */       int comparison;
/*  411 */       if ((b <= c) && ((comparison = comp.compare(x[b], v)) <= 0)) {
/*  412 */         if (comparison == 0) swap(x, a++, b);
/*  413 */         b++;
/*      */       }
/*      */       else
/*      */       {
/*      */         int comparison;
/*  415 */         while ((c >= b) && ((comparison = comp.compare(x[c], v)) >= 0)) {
/*  416 */           if (comparison == 0) swap(x, c, d--);
/*  417 */           c--;
/*      */         }
/*  419 */         if (b > c) break;
/*  420 */         swap(x, b++, c--);
/*      */       }
/*      */     }
/*  423 */     int n = to;
/*  424 */     int s = Math.min(a - from, b - a);
/*  425 */     vecSwap(x, from, b - s, s);
/*  426 */     s = Math.min(d - c, n - d - 1);
/*  427 */     vecSwap(x, b, n - s, s);
/*      */ 
/*  429 */     if ((s = b - a) > 1) quickSort(x, from, from + s, comp);
/*  430 */     if ((s = d - c) > 1) quickSort(x, n - s, n, comp);
/*      */   }
/*      */ 
/*      */   public static void quickSort(long[] x, LongComparator comp)
/*      */   {
/*  444 */     quickSort(x, 0, x.length, comp);
/*      */   }
/*      */ 
/*      */   private static int med3(long[] x, int a, int b, int c) {
/*  448 */     int ab = x[a] == x[b] ? 0 : x[a] < x[b] ? -1 : 1;
/*  449 */     int ac = x[a] == x[c] ? 0 : x[a] < x[c] ? -1 : 1;
/*  450 */     int bc = x[b] == x[c] ? 0 : x[b] < x[c] ? -1 : 1;
/*  451 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*      */   }
/*      */ 
/*      */   public static void quickSort(long[] x, int from, int to)
/*      */   {
/*  468 */     int len = to - from;
/*      */ 
/*  470 */     if (len < 7) {
/*  471 */       selectionSort(x, from, to);
/*  472 */       return;
/*      */     }
/*      */ 
/*  475 */     int m = from + len / 2;
/*  476 */     if (len > 7) {
/*  477 */       int l = from;
/*  478 */       int n = to - 1;
/*  479 */       if (len > 50) {
/*  480 */         int s = len / 8;
/*  481 */         l = med3(x, l, l + s, l + 2 * s);
/*  482 */         m = med3(x, m - s, m, m + s);
/*  483 */         n = med3(x, n - 2 * s, n - s, n);
/*      */       }
/*  485 */       m = med3(x, l, m, n);
/*      */     }
/*  487 */     long v = x[m];
/*      */ 
/*  489 */     int a = from; int b = a; int c = to - 1; int d = c;
/*      */     while (true)
/*      */     {
/*  492 */       if (b <= c)
/*      */       {
/*      */         int comparison;
/*  492 */         if ((comparison = x[b] == v ? 0 : x[b] < v ? -1 : 1) <= 0) {
/*  493 */           if (comparison == 0) swap(x, a++, b);
/*  494 */           b++;
/*      */         }
/*      */       } else { while (c >= b)
/*      */         {
/*      */           int comparison;
/*  496 */           if ((comparison = x[c] == v ? 0 : x[c] < v ? -1 : 1) < 0) break;
/*  497 */           if (comparison == 0) swap(x, c, d--);
/*  498 */           c--;
/*      */         }
/*  500 */         if (b > c) break;
/*  501 */         swap(x, b++, c--);
/*      */       }
/*      */     }
/*  504 */     int n = to;
/*  505 */     int s = Math.min(a - from, b - a);
/*  506 */     vecSwap(x, from, b - s, s);
/*  507 */     s = Math.min(d - c, n - d - 1);
/*  508 */     vecSwap(x, b, n - s, s);
/*      */ 
/*  510 */     if ((s = b - a) > 1) quickSort(x, from, from + s);
/*  511 */     if ((s = d - c) > 1) quickSort(x, n - s, n);
/*      */   }
/*      */ 
/*      */   public static void quickSort(long[] x)
/*      */   {
/*  523 */     quickSort(x, 0, x.length);
/*      */   }
/*      */ 
/*      */   public static void mergeSort(long[] a, int from, int to, long[] supp)
/*      */   {
/*  537 */     int len = to - from;
/*      */ 
/*  539 */     if (len < 7) {
/*  540 */       insertionSort(a, from, to);
/*  541 */       return;
/*      */     }
/*      */ 
/*  544 */     int mid = from + to >>> 1;
/*  545 */     mergeSort(supp, from, mid, a);
/*  546 */     mergeSort(supp, mid, to, a);
/*      */ 
/*  549 */     if (supp[(mid - 1)] <= supp[mid]) {
/*  550 */       System.arraycopy(supp, from, a, from, len);
/*  551 */       return;
/*      */     }
/*      */ 
/*  554 */     int i = from; int p = from; for (int q = mid; i < to; i++)
/*  555 */       if ((q >= to) || ((p < mid) && (supp[p] <= supp[q]))) a[i] = supp[(p++)]; else
/*  556 */         a[i] = supp[(q++)];
/*      */   }
/*      */ 
/*      */   public static void mergeSort(long[] a, int from, int to)
/*      */   {
/*  569 */     mergeSort(a, from, to, (long[])a.clone());
/*      */   }
/*      */ 
/*      */   public static void mergeSort(long[] a)
/*      */   {
/*  579 */     mergeSort(a, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void mergeSort(long[] a, int from, int to, LongComparator comp, long[] supp)
/*      */   {
/*  595 */     int len = to - from;
/*      */ 
/*  597 */     if (len < 7) {
/*  598 */       insertionSort(a, from, to, comp);
/*  599 */       return;
/*      */     }
/*      */ 
/*  602 */     int mid = from + to >>> 1;
/*  603 */     mergeSort(supp, from, mid, comp, a);
/*  604 */     mergeSort(supp, mid, to, comp, a);
/*      */ 
/*  607 */     if (comp.compare(supp[(mid - 1)], supp[mid]) <= 0) {
/*  608 */       System.arraycopy(supp, from, a, from, len);
/*  609 */       return;
/*      */     }
/*      */ 
/*  612 */     int i = from; int p = from; for (int q = mid; i < to; i++)
/*  613 */       if ((q >= to) || ((p < mid) && (comp.compare(supp[p], supp[q]) <= 0))) a[i] = supp[(p++)]; else
/*  614 */         a[i] = supp[(q++)];
/*      */   }
/*      */ 
/*      */   public static void mergeSort(long[] a, int from, int to, LongComparator comp)
/*      */   {
/*  629 */     mergeSort(a, from, to, comp, (long[])a.clone());
/*      */   }
/*      */ 
/*      */   public static void mergeSort(long[] a, LongComparator comp)
/*      */   {
/*  641 */     mergeSort(a, 0, a.length, comp);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(long[] a, int from, int to, long key)
/*      */   {
/*  666 */     to--;
/*  667 */     while (from <= to) {
/*  668 */       int mid = from + to >>> 1;
/*  669 */       long midVal = a[mid];
/*  670 */       if (midVal < key) from = mid + 1;
/*  671 */       else if (midVal > key) to = mid - 1; else
/*  672 */         return mid;
/*      */     }
/*  674 */     return -(from + 1);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(long[] a, long key)
/*      */   {
/*  695 */     return binarySearch(a, 0, a.length, key);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(long[] a, int from, int to, long key, LongComparator c)
/*      */   {
/*  720 */     to--;
/*  721 */     while (from <= to) {
/*  722 */       int mid = from + to >>> 1;
/*  723 */       long midVal = a[mid];
/*  724 */       int cmp = c.compare(midVal, key);
/*  725 */       if (cmp < 0) from = mid + 1;
/*  726 */       else if (cmp > 0) to = mid - 1; else
/*  727 */         return mid;
/*      */     }
/*  729 */     return -(from + 1);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(long[] a, long key, LongComparator c)
/*      */   {
/*  751 */     return binarySearch(a, 0, a.length, key, c);
/*      */   }
/*      */ 
/*      */   public static void radixSort(long[] a)
/*      */   {
/*  777 */     radixSort(a, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(long[] a, int from, int to)
/*      */   {
/*  798 */     int maxLevel = 7;
/*  799 */     int stackSize = 1786;
/*  800 */     int[] offsetStack = new int[1786];
/*  801 */     int offsetPos = 0;
/*  802 */     int[] lengthStack = new int[1786];
/*  803 */     int lengthPos = 0;
/*  804 */     int[] levelStack = new int[1786];
/*  805 */     int levelPos = 0;
/*  806 */     offsetStack[(offsetPos++)] = from;
/*  807 */     lengthStack[(lengthPos++)] = (to - from);
/*  808 */     levelStack[(levelPos++)] = 0;
/*  809 */     int[] count = new int[256];
/*  810 */     int[] pos = new int[256];
/*  811 */     byte[] digit = new byte[to - from];
/*  812 */     while (offsetPos > 0) {
/*  813 */       int first = offsetStack[(--offsetPos)];
/*  814 */       int length = lengthStack[(--lengthPos)];
/*  815 */       int level = levelStack[(--levelPos)];
/*  816 */       int signMask = level % 8 == 0 ? 128 : 0;
/*  817 */       if (length < 50) {
/*  818 */         selectionSort(a, first, first + length);
/*      */       }
/*      */       else {
/*  821 */         int shift = (7 - level % 8) * 8;
/*      */ 
/*  823 */         for (int i = length; i-- != 0; digit[i] = ((byte)(int)(a[(first + i)] >>> shift & 0xFF ^ signMask)));
/*  824 */         for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1);
/*  826 */         int lastUsed = -1;
/*  827 */         int i = 0; for (int p = 0; i < 256; i++) {
/*  828 */           if (count[i] != 0) {
/*  829 */             lastUsed = i;
/*  830 */             if ((level < 7) && (count[i] > 1))
/*      */             {
/*  832 */               offsetStack[(offsetPos++)] = (p + first);
/*  833 */               lengthStack[(lengthPos++)] = count[i];
/*  834 */               levelStack[(levelPos++)] = (level + 1);
/*      */             }
/*      */           }
/*      */           int tmp340_339 = (p + count[i]); p = tmp340_339; pos[i] = tmp340_339;
/*      */         }
/*      */ 
/*  840 */         int end = length - count[lastUsed];
/*  841 */         count[lastUsed] = 0;
/*      */ 
/*  843 */         int i = 0; for (int c = -1; i < end; count[c] = 0) {
/*  844 */           long t = a[(i + first)];
/*  845 */           c = digit[i] & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             int d;
/*  846 */             if ((d = pos[c] -= 1) <= i) break;
/*  847 */             long z = t;
/*  848 */             int zz = c;
/*  849 */             t = a[(d + first)];
/*  850 */             c = digit[d] & 0xFF;
/*  851 */             a[(d + first)] = z;
/*  852 */             digit[d] = ((byte)zz);
/*      */           }
/*  854 */           a[(i + first)] = t;
/*      */ 
/*  843 */           i += count[c];
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void insertionSortIndirect(int[] perm, long[] a, int from, int to)
/*      */   {
/*  859 */     int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/*  860 */       int t = perm[i];
/*  861 */       int j = i;
/*  862 */       for (int u = perm[(j - 1)]; a[t] < a[u]; u = perm[(--j - 1)]) {
/*  863 */         perm[j] = u;
/*  864 */         if (from == j - 1) {
/*  865 */           j--;
/*  866 */           break;
/*      */         }
/*      */       }
/*  869 */       perm[j] = t;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSortIndirect(int[] perm, long[] a, boolean stable)
/*      */   {
/*  897 */     radixSortIndirect(perm, a, 0, perm.length, stable); } 
/*      */   public static void radixSortIndirect(int[] perm, long[] a, int from, int to, boolean stable) { // Byte code:
/*      */     //   0: bipush 7
/*      */     //   2: istore 5
/*      */     //   4: sipush 1786
/*      */     //   7: istore 6
/*      */     //   9: sipush 1786
/*      */     //   12: newarray int
/*      */     //   14: astore 7
/*      */     //   16: iconst_0
/*      */     //   17: istore 8
/*      */     //   19: sipush 1786
/*      */     //   22: newarray int
/*      */     //   24: astore 9
/*      */     //   26: iconst_0
/*      */     //   27: istore 10
/*      */     //   29: sipush 1786
/*      */     //   32: newarray int
/*      */     //   34: astore 11
/*      */     //   36: iconst_0
/*      */     //   37: istore 12
/*      */     //   39: aload 7
/*      */     //   41: iload 8
/*      */     //   43: iinc 8 1
/*      */     //   46: iload_2
/*      */     //   47: iastore
/*      */     //   48: aload 9
/*      */     //   50: iload 10
/*      */     //   52: iinc 10 1
/*      */     //   55: iload_3
/*      */     //   56: iload_2
/*      */     //   57: isub
/*      */     //   58: iastore
/*      */     //   59: aload 11
/*      */     //   61: iload 12
/*      */     //   63: iinc 12 1
/*      */     //   66: iconst_0
/*      */     //   67: iastore
/*      */     //   68: sipush 256
/*      */     //   71: newarray int
/*      */     //   73: astore 13
/*      */     //   75: iload 4
/*      */     //   77: ifeq +7 -> 84
/*      */     //   80: aconst_null
/*      */     //   81: goto +8 -> 89
/*      */     //   84: sipush 256
/*      */     //   87: newarray int
/*      */     //   89: astore 14
/*      */     //   91: iload 4
/*      */     //   93: ifeq +10 -> 103
/*      */     //   96: aload_0
/*      */     //   97: arraylength
/*      */     //   98: newarray int
/*      */     //   100: goto +4 -> 104
/*      */     //   103: aconst_null
/*      */     //   104: astore 15
/*      */     //   106: iload_3
/*      */     //   107: iload_2
/*      */     //   108: isub
/*      */     //   109: newarray byte
/*      */     //   111: astore 16
/*      */     //   113: iload 8
/*      */     //   115: ifle +500 -> 615
/*      */     //   118: aload 7
/*      */     //   120: iinc 8 255
/*      */     //   123: iload 8
/*      */     //   125: iaload
/*      */     //   126: istore 17
/*      */     //   128: aload 9
/*      */     //   130: iinc 10 255
/*      */     //   133: iload 10
/*      */     //   135: iaload
/*      */     //   136: istore 18
/*      */     //   138: aload 11
/*      */     //   140: iinc 12 255
/*      */     //   143: iload 12
/*      */     //   145: iaload
/*      */     //   146: istore 19
/*      */     //   148: iload 19
/*      */     //   150: bipush 8
/*      */     //   152: irem
/*      */     //   153: ifne +9 -> 162
/*      */     //   156: sipush 128
/*      */     //   159: goto +4 -> 163
/*      */     //   162: iconst_0
/*      */     //   163: istore 20
/*      */     //   165: iload 18
/*      */     //   167: bipush 50
/*      */     //   169: if_icmpge +18 -> 187
/*      */     //   172: aload_0
/*      */     //   173: aload_1
/*      */     //   174: iload 17
/*      */     //   176: iload 17
/*      */     //   178: iload 18
/*      */     //   180: iadd
/*      */     //   181: invokestatic 42	it/unimi/dsi/fastutil/longs/LongArrays:insertionSortIndirect	([I[JII)V
/*      */     //   184: goto -71 -> 113
/*      */     //   187: bipush 7
/*      */     //   189: iload 19
/*      */     //   191: bipush 8
/*      */     //   193: irem
/*      */     //   194: isub
/*      */     //   195: bipush 8
/*      */     //   197: imul
/*      */     //   198: istore 21
/*      */     //   200: iload 18
/*      */     //   202: istore 22
/*      */     //   204: iload 22
/*      */     //   206: iinc 22 255
/*      */     //   209: ifeq +33 -> 242
/*      */     //   212: aload 16
/*      */     //   214: iload 22
/*      */     //   216: aload_1
/*      */     //   217: aload_0
/*      */     //   218: iload 17
/*      */     //   220: iload 22
/*      */     //   222: iadd
/*      */     //   223: iaload
/*      */     //   224: laload
/*      */     //   225: iload 21
/*      */     //   227: lushr
/*      */     //   228: ldc2_w 39
/*      */     //   231: land
/*      */     //   232: iload 20
/*      */     //   234: i2l
/*      */     //   235: lxor
/*      */     //   236: l2i
/*      */     //   237: i2b
/*      */     //   238: bastore
/*      */     //   239: goto -35 -> 204
/*      */     //   242: iload 18
/*      */     //   244: istore 22
/*      */     //   246: iload 22
/*      */     //   248: iinc 22 255
/*      */     //   251: ifeq +22 -> 273
/*      */     //   254: aload 13
/*      */     //   256: aload 16
/*      */     //   258: iload 22
/*      */     //   260: baload
/*      */     //   261: sipush 255
/*      */     //   264: iand
/*      */     //   265: dup2
/*      */     //   266: iaload
/*      */     //   267: iconst_1
/*      */     //   268: iadd
/*      */     //   269: iastore
/*      */     //   270: goto -24 -> 246
/*      */     //   273: iconst_m1
/*      */     //   274: istore 22
/*      */     //   276: iconst_0
/*      */     //   277: istore 23
/*      */     //   279: iconst_0
/*      */     //   280: istore 24
/*      */     //   282: iload 23
/*      */     //   284: sipush 256
/*      */     //   287: if_icmpge +115 -> 402
/*      */     //   290: aload 13
/*      */     //   292: iload 23
/*      */     //   294: iaload
/*      */     //   295: ifeq +61 -> 356
/*      */     //   298: iload 23
/*      */     //   300: istore 22
/*      */     //   302: iload 19
/*      */     //   304: bipush 7
/*      */     //   306: if_icmpge +50 -> 356
/*      */     //   309: aload 13
/*      */     //   311: iload 23
/*      */     //   313: iaload
/*      */     //   314: iconst_1
/*      */     //   315: if_icmple +41 -> 356
/*      */     //   318: aload 7
/*      */     //   320: iload 8
/*      */     //   322: iinc 8 1
/*      */     //   325: iload 24
/*      */     //   327: iload 17
/*      */     //   329: iadd
/*      */     //   330: iastore
/*      */     //   331: aload 9
/*      */     //   333: iload 10
/*      */     //   335: iinc 10 1
/*      */     //   338: aload 13
/*      */     //   340: iload 23
/*      */     //   342: iaload
/*      */     //   343: iastore
/*      */     //   344: aload 11
/*      */     //   346: iload 12
/*      */     //   348: iinc 12 1
/*      */     //   351: iload 19
/*      */     //   353: iconst_1
/*      */     //   354: iadd
/*      */     //   355: iastore
/*      */     //   356: iload 4
/*      */     //   358: ifeq +22 -> 380
/*      */     //   361: aload 13
/*      */     //   363: iload 23
/*      */     //   365: iload 24
/*      */     //   367: aload 13
/*      */     //   369: iload 23
/*      */     //   371: iaload
/*      */     //   372: iadd
/*      */     //   373: dup
/*      */     //   374: istore 24
/*      */     //   376: iastore
/*      */     //   377: goto +19 -> 396
/*      */     //   380: aload 14
/*      */     //   382: iload 23
/*      */     //   384: iload 24
/*      */     //   386: aload 13
/*      */     //   388: iload 23
/*      */     //   390: iaload
/*      */     //   391: iadd
/*      */     //   392: dup
/*      */     //   393: istore 24
/*      */     //   395: iastore
/*      */     //   396: iinc 23 1
/*      */     //   399: goto -117 -> 282
/*      */     //   402: iload 4
/*      */     //   404: ifeq +65 -> 469
/*      */     //   407: iload 18
/*      */     //   409: istore 23
/*      */     //   411: iload 23
/*      */     //   413: iinc 23 255
/*      */     //   416: ifeq +33 -> 449
/*      */     //   419: aload 15
/*      */     //   421: aload 13
/*      */     //   423: aload 16
/*      */     //   425: iload 23
/*      */     //   427: baload
/*      */     //   428: sipush 255
/*      */     //   431: iand
/*      */     //   432: dup2
/*      */     //   433: iaload
/*      */     //   434: iconst_1
/*      */     //   435: isub
/*      */     //   436: dup_x2
/*      */     //   437: iastore
/*      */     //   438: aload_0
/*      */     //   439: iload 17
/*      */     //   441: iload 23
/*      */     //   443: iadd
/*      */     //   444: iaload
/*      */     //   445: iastore
/*      */     //   446: goto -35 -> 411
/*      */     //   449: aload 15
/*      */     //   451: iconst_0
/*      */     //   452: aload_0
/*      */     //   453: iload 17
/*      */     //   455: iload 18
/*      */     //   457: invokestatic 2	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
/*      */     //   460: aload 13
/*      */     //   462: iconst_0
/*      */     //   463: invokestatic 43	it/unimi/dsi/fastutil/ints/IntArrays:fill	([II)V
/*      */     //   466: goto +146 -> 612
/*      */     //   469: iload 18
/*      */     //   471: aload 13
/*      */     //   473: iload 22
/*      */     //   475: iaload
/*      */     //   476: isub
/*      */     //   477: istore 23
/*      */     //   479: aload 13
/*      */     //   481: iload 22
/*      */     //   483: iconst_0
/*      */     //   484: iastore
/*      */     //   485: iconst_0
/*      */     //   486: istore 24
/*      */     //   488: iconst_m1
/*      */     //   489: istore 25
/*      */     //   491: iload 24
/*      */     //   493: iload 23
/*      */     //   495: if_icmpge +117 -> 612
/*      */     //   498: aload_0
/*      */     //   499: iload 24
/*      */     //   501: iload 17
/*      */     //   503: iadd
/*      */     //   504: iaload
/*      */     //   505: istore 27
/*      */     //   507: aload 16
/*      */     //   509: iload 24
/*      */     //   511: baload
/*      */     //   512: sipush 255
/*      */     //   515: iand
/*      */     //   516: istore 25
/*      */     //   518: aload 14
/*      */     //   520: iload 25
/*      */     //   522: dup2
/*      */     //   523: iaload
/*      */     //   524: iconst_1
/*      */     //   525: isub
/*      */     //   526: dup_x2
/*      */     //   527: iastore
/*      */     //   528: dup
/*      */     //   529: istore 26
/*      */     //   531: iload 24
/*      */     //   533: if_icmple +51 -> 584
/*      */     //   536: iload 27
/*      */     //   538: istore 28
/*      */     //   540: iload 25
/*      */     //   542: istore 29
/*      */     //   544: aload_0
/*      */     //   545: iload 26
/*      */     //   547: iload 17
/*      */     //   549: iadd
/*      */     //   550: iaload
/*      */     //   551: istore 27
/*      */     //   553: aload 16
/*      */     //   555: iload 26
/*      */     //   557: baload
/*      */     //   558: sipush 255
/*      */     //   561: iand
/*      */     //   562: istore 25
/*      */     //   564: aload_0
/*      */     //   565: iload 26
/*      */     //   567: iload 17
/*      */     //   569: iadd
/*      */     //   570: iload 28
/*      */     //   572: iastore
/*      */     //   573: aload 16
/*      */     //   575: iload 26
/*      */     //   577: iload 29
/*      */     //   579: i2b
/*      */     //   580: bastore
/*      */     //   581: goto -63 -> 518
/*      */     //   584: aload_0
/*      */     //   585: iload 24
/*      */     //   587: iload 17
/*      */     //   589: iadd
/*      */     //   590: iload 27
/*      */     //   592: iastore
/*      */     //   593: iload 24
/*      */     //   595: aload 13
/*      */     //   597: iload 25
/*      */     //   599: iaload
/*      */     //   600: iadd
/*      */     //   601: istore 24
/*      */     //   603: aload 13
/*      */     //   605: iload 25
/*      */     //   607: iconst_0
/*      */     //   608: iastore
/*      */     //   609: goto -118 -> 491
/*      */     //   612: goto -499 -> 113
/*      */     //   615: return } 
/*  995 */   private static void selectionSort(long[] a, long[] b, int from, int to) { for (int i = from; i < to - 1; i++) {
/*  996 */       int m = i;
/*  997 */       for (int j = i + 1; j < to; j++)
/*  998 */         if ((a[j] < a[m]) || ((a[j] == a[m]) && (b[j] < b[m]))) m = j;
/*  999 */       if (m != i) {
/* 1000 */         long t = a[i];
/* 1001 */         a[i] = a[m];
/* 1002 */         a[m] = t;
/* 1003 */         t = b[i];
/* 1004 */         b[i] = b[m];
/* 1005 */         b[m] = t;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSort(long[] a, long[] b)
/*      */   {
/* 1029 */     radixSort(a, b, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(long[] a, long[] b, int from, int to)
/*      */   {
/* 1054 */     int layers = 2;
/* 1055 */     if (a.length != b.length) throw new IllegalArgumentException("Array size mismatch.");
/* 1056 */     int maxLevel = 15;
/* 1057 */     int stackSize = 3826;
/* 1058 */     int[] offsetStack = new int[3826];
/* 1059 */     int offsetPos = 0;
/* 1060 */     int[] lengthStack = new int[3826];
/* 1061 */     int lengthPos = 0;
/* 1062 */     int[] levelStack = new int[3826];
/* 1063 */     int levelPos = 0;
/* 1064 */     offsetStack[(offsetPos++)] = from;
/* 1065 */     lengthStack[(lengthPos++)] = (to - from);
/* 1066 */     levelStack[(levelPos++)] = 0;
/* 1067 */     int[] count = new int[256];
/* 1068 */     int[] pos = new int[256];
/* 1069 */     byte[] digit = new byte[to - from];
/* 1070 */     while (offsetPos > 0) {
/* 1071 */       int first = offsetStack[(--offsetPos)];
/* 1072 */       int length = lengthStack[(--lengthPos)];
/* 1073 */       int level = levelStack[(--levelPos)];
/* 1074 */       int signMask = level % 8 == 0 ? 128 : 0;
/* 1075 */       if (length < 50) {
/* 1076 */         selectionSort(a, b, first, first + length);
/*      */       }
/*      */       else {
/* 1079 */         long[] k = level < 8 ? a : b;
/* 1080 */         int shift = (7 - level % 8) * 8;
/*      */ 
/* 1082 */         for (int i = length; i-- != 0; digit[i] = ((byte)(int)(k[(first + i)] >>> shift & 0xFF ^ signMask)));
/* 1083 */         for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1);
/* 1085 */         int lastUsed = -1;
/* 1086 */         int i = 0; for (int p = 0; i < 256; i++) {
/* 1087 */           if (count[i] != 0) {
/* 1088 */             lastUsed = i;
/* 1089 */             if ((level < 15) && (count[i] > 1)) {
/* 1090 */               offsetStack[(offsetPos++)] = (p + first);
/* 1091 */               lengthStack[(lengthPos++)] = count[i];
/* 1092 */               levelStack[(levelPos++)] = (level + 1);
/*      */             }
/*      */           }
/*      */           int tmp377_376 = (p + count[i]); p = tmp377_376; pos[i] = tmp377_376;
/*      */         }
/*      */ 
/* 1098 */         int end = length - count[lastUsed];
/* 1099 */         count[lastUsed] = 0;
/*      */ 
/* 1101 */         int i = 0; for (int c = -1; i < end; count[c] = 0) {
/* 1102 */           long t = a[(i + first)];
/* 1103 */           long u = b[(i + first)];
/* 1104 */           c = digit[i] & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             int d;
/* 1105 */             if ((d = pos[c] -= 1) <= i) break;
/* 1106 */             long z = t;
/* 1107 */             int zz = c;
/* 1108 */             t = a[(d + first)];
/* 1109 */             a[(d + first)] = z;
/* 1110 */             z = u;
/* 1111 */             u = b[(d + first)];
/* 1112 */             b[(d + first)] = z;
/* 1113 */             c = digit[d] & 0xFF;
/* 1114 */             digit[d] = ((byte)zz);
/*      */           }
/* 1116 */           a[(i + first)] = t;
/* 1117 */           b[(i + first)] = u;
/*      */ 
/* 1101 */           i += count[c];
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void insertionSortIndirect(int[] perm, long[] a, long[] b, int from, int to)
/*      */   {
/* 1122 */     int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/* 1123 */       int t = perm[i];
/* 1124 */       int j = i;
/* 1125 */       for (int u = perm[(j - 1)]; (a[t] < a[u]) || ((a[t] == a[u]) && (b[t] < b[u])); u = perm[(--j - 1)]) {
/* 1126 */         perm[j] = u;
/* 1127 */         if (from == j - 1) {
/* 1128 */           j--;
/* 1129 */           break;
/*      */         }
/*      */       }
/* 1132 */       perm[j] = t;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSortIndirect(int[] perm, long[] a, long[] b, boolean stable)
/*      */   {
/* 1161 */     radixSortIndirect(perm, a, b, 0, perm.length, stable); } 
/*      */   public static void radixSortIndirect(int[] perm, long[] a, long[] b, int from, int to, boolean stable) { // Byte code:
/*      */     //   0: iconst_2
/*      */     //   1: istore 6
/*      */     //   3: aload_1
/*      */     //   4: arraylength
/*      */     //   5: aload_2
/*      */     //   6: arraylength
/*      */     //   7: if_icmpeq +13 -> 20
/*      */     //   10: new 45	java/lang/IllegalArgumentException
/*      */     //   13: dup
/*      */     //   14: ldc 46
/*      */     //   16: invokespecial 47	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
/*      */     //   19: athrow
/*      */     //   20: bipush 15
/*      */     //   22: istore 7
/*      */     //   24: sipush 3826
/*      */     //   27: istore 8
/*      */     //   29: sipush 3826
/*      */     //   32: newarray int
/*      */     //   34: astore 9
/*      */     //   36: iconst_0
/*      */     //   37: istore 10
/*      */     //   39: sipush 3826
/*      */     //   42: newarray int
/*      */     //   44: astore 11
/*      */     //   46: iconst_0
/*      */     //   47: istore 12
/*      */     //   49: sipush 3826
/*      */     //   52: newarray int
/*      */     //   54: astore 13
/*      */     //   56: iconst_0
/*      */     //   57: istore 14
/*      */     //   59: aload 9
/*      */     //   61: iload 10
/*      */     //   63: iinc 10 1
/*      */     //   66: iload_3
/*      */     //   67: iastore
/*      */     //   68: aload 11
/*      */     //   70: iload 12
/*      */     //   72: iinc 12 1
/*      */     //   75: iload 4
/*      */     //   77: iload_3
/*      */     //   78: isub
/*      */     //   79: iastore
/*      */     //   80: aload 13
/*      */     //   82: iload 14
/*      */     //   84: iinc 14 1
/*      */     //   87: iconst_0
/*      */     //   88: iastore
/*      */     //   89: sipush 256
/*      */     //   92: newarray int
/*      */     //   94: astore 15
/*      */     //   96: iload 5
/*      */     //   98: ifeq +7 -> 105
/*      */     //   101: aconst_null
/*      */     //   102: goto +8 -> 110
/*      */     //   105: sipush 256
/*      */     //   108: newarray int
/*      */     //   110: astore 16
/*      */     //   112: iload 5
/*      */     //   114: ifeq +10 -> 124
/*      */     //   117: aload_0
/*      */     //   118: arraylength
/*      */     //   119: newarray int
/*      */     //   121: goto +4 -> 125
/*      */     //   124: aconst_null
/*      */     //   125: astore 17
/*      */     //   127: iload 4
/*      */     //   129: iload_3
/*      */     //   130: isub
/*      */     //   131: newarray byte
/*      */     //   133: astore 18
/*      */     //   135: iload 10
/*      */     //   137: ifle +516 -> 653
/*      */     //   140: aload 9
/*      */     //   142: iinc 10 255
/*      */     //   145: iload 10
/*      */     //   147: iaload
/*      */     //   148: istore 19
/*      */     //   150: aload 11
/*      */     //   152: iinc 12 255
/*      */     //   155: iload 12
/*      */     //   157: iaload
/*      */     //   158: istore 20
/*      */     //   160: aload 13
/*      */     //   162: iinc 14 255
/*      */     //   165: iload 14
/*      */     //   167: iaload
/*      */     //   168: istore 21
/*      */     //   170: iload 21
/*      */     //   172: bipush 8
/*      */     //   174: irem
/*      */     //   175: ifne +9 -> 184
/*      */     //   178: sipush 128
/*      */     //   181: goto +4 -> 185
/*      */     //   184: iconst_0
/*      */     //   185: istore 22
/*      */     //   187: iload 20
/*      */     //   189: bipush 50
/*      */     //   191: if_icmpge +19 -> 210
/*      */     //   194: aload_0
/*      */     //   195: aload_1
/*      */     //   196: aload_2
/*      */     //   197: iload 19
/*      */     //   199: iload 19
/*      */     //   201: iload 20
/*      */     //   203: iadd
/*      */     //   204: invokestatic 50	it/unimi/dsi/fastutil/longs/LongArrays:insertionSortIndirect	([I[J[JII)V
/*      */     //   207: goto -72 -> 135
/*      */     //   210: iload 21
/*      */     //   212: bipush 8
/*      */     //   214: if_icmpge +7 -> 221
/*      */     //   217: aload_1
/*      */     //   218: goto +4 -> 222
/*      */     //   221: aload_2
/*      */     //   222: astore 23
/*      */     //   224: bipush 7
/*      */     //   226: iload 21
/*      */     //   228: bipush 8
/*      */     //   230: irem
/*      */     //   231: isub
/*      */     //   232: bipush 8
/*      */     //   234: imul
/*      */     //   235: istore 24
/*      */     //   237: iload 20
/*      */     //   239: istore 25
/*      */     //   241: iload 25
/*      */     //   243: iinc 25 255
/*      */     //   246: ifeq +34 -> 280
/*      */     //   249: aload 18
/*      */     //   251: iload 25
/*      */     //   253: aload 23
/*      */     //   255: aload_0
/*      */     //   256: iload 19
/*      */     //   258: iload 25
/*      */     //   260: iadd
/*      */     //   261: iaload
/*      */     //   262: laload
/*      */     //   263: iload 24
/*      */     //   265: lushr
/*      */     //   266: ldc2_w 39
/*      */     //   269: land
/*      */     //   270: iload 22
/*      */     //   272: i2l
/*      */     //   273: lxor
/*      */     //   274: l2i
/*      */     //   275: i2b
/*      */     //   276: bastore
/*      */     //   277: goto -36 -> 241
/*      */     //   280: iload 20
/*      */     //   282: istore 25
/*      */     //   284: iload 25
/*      */     //   286: iinc 25 255
/*      */     //   289: ifeq +22 -> 311
/*      */     //   292: aload 15
/*      */     //   294: aload 18
/*      */     //   296: iload 25
/*      */     //   298: baload
/*      */     //   299: sipush 255
/*      */     //   302: iand
/*      */     //   303: dup2
/*      */     //   304: iaload
/*      */     //   305: iconst_1
/*      */     //   306: iadd
/*      */     //   307: iastore
/*      */     //   308: goto -24 -> 284
/*      */     //   311: iconst_m1
/*      */     //   312: istore 25
/*      */     //   314: iconst_0
/*      */     //   315: istore 26
/*      */     //   317: iconst_0
/*      */     //   318: istore 27
/*      */     //   320: iload 26
/*      */     //   322: sipush 256
/*      */     //   325: if_icmpge +115 -> 440
/*      */     //   328: aload 15
/*      */     //   330: iload 26
/*      */     //   332: iaload
/*      */     //   333: ifeq +61 -> 394
/*      */     //   336: iload 26
/*      */     //   338: istore 25
/*      */     //   340: iload 21
/*      */     //   342: bipush 15
/*      */     //   344: if_icmpge +50 -> 394
/*      */     //   347: aload 15
/*      */     //   349: iload 26
/*      */     //   351: iaload
/*      */     //   352: iconst_1
/*      */     //   353: if_icmple +41 -> 394
/*      */     //   356: aload 9
/*      */     //   358: iload 10
/*      */     //   360: iinc 10 1
/*      */     //   363: iload 27
/*      */     //   365: iload 19
/*      */     //   367: iadd
/*      */     //   368: iastore
/*      */     //   369: aload 11
/*      */     //   371: iload 12
/*      */     //   373: iinc 12 1
/*      */     //   376: aload 15
/*      */     //   378: iload 26
/*      */     //   380: iaload
/*      */     //   381: iastore
/*      */     //   382: aload 13
/*      */     //   384: iload 14
/*      */     //   386: iinc 14 1
/*      */     //   389: iload 21
/*      */     //   391: iconst_1
/*      */     //   392: iadd
/*      */     //   393: iastore
/*      */     //   394: iload 5
/*      */     //   396: ifeq +22 -> 418
/*      */     //   399: aload 15
/*      */     //   401: iload 26
/*      */     //   403: iload 27
/*      */     //   405: aload 15
/*      */     //   407: iload 26
/*      */     //   409: iaload
/*      */     //   410: iadd
/*      */     //   411: dup
/*      */     //   412: istore 27
/*      */     //   414: iastore
/*      */     //   415: goto +19 -> 434
/*      */     //   418: aload 16
/*      */     //   420: iload 26
/*      */     //   422: iload 27
/*      */     //   424: aload 15
/*      */     //   426: iload 26
/*      */     //   428: iaload
/*      */     //   429: iadd
/*      */     //   430: dup
/*      */     //   431: istore 27
/*      */     //   433: iastore
/*      */     //   434: iinc 26 1
/*      */     //   437: goto -117 -> 320
/*      */     //   440: iload 5
/*      */     //   442: ifeq +65 -> 507
/*      */     //   445: iload 20
/*      */     //   447: istore 26
/*      */     //   449: iload 26
/*      */     //   451: iinc 26 255
/*      */     //   454: ifeq +33 -> 487
/*      */     //   457: aload 17
/*      */     //   459: aload 15
/*      */     //   461: aload 18
/*      */     //   463: iload 26
/*      */     //   465: baload
/*      */     //   466: sipush 255
/*      */     //   469: iand
/*      */     //   470: dup2
/*      */     //   471: iaload
/*      */     //   472: iconst_1
/*      */     //   473: isub
/*      */     //   474: dup_x2
/*      */     //   475: iastore
/*      */     //   476: aload_0
/*      */     //   477: iload 19
/*      */     //   479: iload 26
/*      */     //   481: iadd
/*      */     //   482: iaload
/*      */     //   483: iastore
/*      */     //   484: goto -35 -> 449
/*      */     //   487: aload 17
/*      */     //   489: iconst_0
/*      */     //   490: aload_0
/*      */     //   491: iload 19
/*      */     //   493: iload 20
/*      */     //   495: invokestatic 2	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
/*      */     //   498: aload 15
/*      */     //   500: iconst_0
/*      */     //   501: invokestatic 43	it/unimi/dsi/fastutil/ints/IntArrays:fill	([II)V
/*      */     //   504: goto +146 -> 650
/*      */     //   507: iload 20
/*      */     //   509: aload 15
/*      */     //   511: iload 25
/*      */     //   513: iaload
/*      */     //   514: isub
/*      */     //   515: istore 26
/*      */     //   517: aload 15
/*      */     //   519: iload 25
/*      */     //   521: iconst_0
/*      */     //   522: iastore
/*      */     //   523: iconst_0
/*      */     //   524: istore 27
/*      */     //   526: iconst_m1
/*      */     //   527: istore 28
/*      */     //   529: iload 27
/*      */     //   531: iload 26
/*      */     //   533: if_icmpge +117 -> 650
/*      */     //   536: aload_0
/*      */     //   537: iload 27
/*      */     //   539: iload 19
/*      */     //   541: iadd
/*      */     //   542: iaload
/*      */     //   543: istore 30
/*      */     //   545: aload 18
/*      */     //   547: iload 27
/*      */     //   549: baload
/*      */     //   550: sipush 255
/*      */     //   553: iand
/*      */     //   554: istore 28
/*      */     //   556: aload 16
/*      */     //   558: iload 28
/*      */     //   560: dup2
/*      */     //   561: iaload
/*      */     //   562: iconst_1
/*      */     //   563: isub
/*      */     //   564: dup_x2
/*      */     //   565: iastore
/*      */     //   566: dup
/*      */     //   567: istore 29
/*      */     //   569: iload 27
/*      */     //   571: if_icmple +51 -> 622
/*      */     //   574: iload 30
/*      */     //   576: istore 31
/*      */     //   578: iload 28
/*      */     //   580: istore 32
/*      */     //   582: aload_0
/*      */     //   583: iload 29
/*      */     //   585: iload 19
/*      */     //   587: iadd
/*      */     //   588: iaload
/*      */     //   589: istore 30
/*      */     //   591: aload 18
/*      */     //   593: iload 29
/*      */     //   595: baload
/*      */     //   596: sipush 255
/*      */     //   599: iand
/*      */     //   600: istore 28
/*      */     //   602: aload_0
/*      */     //   603: iload 29
/*      */     //   605: iload 19
/*      */     //   607: iadd
/*      */     //   608: iload 31
/*      */     //   610: iastore
/*      */     //   611: aload 18
/*      */     //   613: iload 29
/*      */     //   615: iload 32
/*      */     //   617: i2b
/*      */     //   618: bastore
/*      */     //   619: goto -63 -> 556
/*      */     //   622: aload_0
/*      */     //   623: iload 27
/*      */     //   625: iload 19
/*      */     //   627: iadd
/*      */     //   628: iload 30
/*      */     //   630: iastore
/*      */     //   631: iload 27
/*      */     //   633: aload 15
/*      */     //   635: iload 28
/*      */     //   637: iaload
/*      */     //   638: iadd
/*      */     //   639: istore 27
/*      */     //   641: aload 15
/*      */     //   643: iload 28
/*      */     //   645: iconst_0
/*      */     //   646: iastore
/*      */     //   647: goto -118 -> 529
/*      */     //   650: goto -515 -> 135
/*      */     //   653: return } 
/* 1263 */   private static void selectionSort(long[][] a, int from, int to, int level) { int layers = a.length;
/* 1264 */     int firstLayer = level / 8;
/*      */     int m;
/*      */     int p;
/* 1265 */     for (int i = from; i < to - 1; i++) {
/* 1266 */       m = i;
/* 1267 */       for (int j = i + 1; j < to; j++) {
/* 1268 */         for (int p = firstLayer; p < layers; p++)
/* 1269 */           if (a[p][j] < a[p][m]) {
/* 1270 */             m = j;
/*      */           }
/*      */           else
/* 1273 */             if (a[p][j] > a[p][m])
/*      */               break;
/*      */       }
/* 1276 */       if (m != i)
/* 1277 */         for (p = layers; p-- != 0; ) {
/* 1278 */           long u = a[p][i];
/* 1279 */           a[p][i] = a[p][m];
/* 1280 */           a[p][m] = u;
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSort(long[][] a)
/*      */   {
/* 1305 */     radixSort(a, 0, a[0].length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(long[][] a, int from, int to)
/*      */   {
/* 1329 */     int layers = a.length;
/* 1330 */     int maxLevel = 8 * layers - 1;
/* 1331 */     int p = layers; for (int l = a[0].length; p-- != 0; ) if (a[p].length != l) throw new IllegalArgumentException("The array of index " + p + " has not the same length of the array of index 0.");
/* 1332 */     int stackSize = 255 * (layers * 8 - 1) + 1;
/* 1333 */     int[] offsetStack = new int[stackSize];
/* 1334 */     int offsetPos = 0;
/* 1335 */     int[] lengthStack = new int[stackSize];
/* 1336 */     int lengthPos = 0;
/* 1337 */     int[] levelStack = new int[stackSize];
/* 1338 */     int levelPos = 0;
/* 1339 */     offsetStack[(offsetPos++)] = from;
/* 1340 */     lengthStack[(lengthPos++)] = (to - from);
/* 1341 */     levelStack[(levelPos++)] = 0;
/* 1342 */     int[] count = new int[256];
/* 1343 */     int[] pos = new int[256];
/* 1344 */     byte[] digit = new byte[to - from];
/* 1345 */     long[] t = new long[layers];
/* 1346 */     while (offsetPos > 0) {
/* 1347 */       int first = offsetStack[(--offsetPos)];
/* 1348 */       int length = lengthStack[(--lengthPos)];
/* 1349 */       int level = levelStack[(--levelPos)];
/* 1350 */       int signMask = level % 8 == 0 ? 128 : 0;
/* 1351 */       if (length < 50) {
/* 1352 */         selectionSort(a, first, first + length, level);
/*      */       }
/*      */       else {
/* 1355 */         long[] k = a[(level / 8)];
/* 1356 */         int shift = (7 - level % 8) * 8;
/*      */ 
/* 1358 */         for (int i = length; i-- != 0; digit[i] = ((byte)(int)(k[(first + i)] >>> shift & 0xFF ^ signMask)));
/* 1359 */         for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1);
/* 1361 */         int lastUsed = -1;
/* 1362 */         int i = 0; for (int p = 0; i < 256; i++) {
/* 1363 */           if (count[i] != 0) {
/* 1364 */             lastUsed = i;
/* 1365 */             if ((level < maxLevel) && (count[i] > 1)) {
/* 1366 */               offsetStack[(offsetPos++)] = (p + first);
/* 1367 */               lengthStack[(lengthPos++)] = count[i];
/* 1368 */               levelStack[(levelPos++)] = (level + 1);
/*      */             }
/*      */           }
/*      */           int tmp431_430 = (p + count[i]); p = tmp431_430; pos[i] = tmp431_430;
/*      */         }
/*      */ 
/* 1374 */         int end = length - count[lastUsed];
/* 1375 */         count[lastUsed] = 0;
/*      */ 
/* 1377 */         int i = 0; for (int c = -1; i < end; count[c] = 0) {
/* 1378 */           for (int p = layers; p-- != 0; t[p] = a[p][(i + first)]);
/* 1379 */           c = digit[i] & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             int d;
/* 1380 */             if ((d = pos[c] -= 1) <= i) break;
/* 1381 */             for (int p = layers; p-- != 0; ) {
/* 1382 */               long u = t[p];
/* 1383 */               t[p] = a[p][(d + first)];
/* 1384 */               a[p][(d + first)] = u;
/*      */             }
/* 1386 */             int zz = c;
/* 1387 */             c = digit[d] & 0xFF;
/* 1388 */             digit[d] = ((byte)zz);
/*      */           }
/* 1390 */           for (int p = layers; p-- != 0; a[p][(i + first)] = t[p]);
/* 1377 */           i += count[c];
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static long[] shuffle(long[] a, int from, int to, Random random)
/*      */   {
/* 1403 */     for (int i = to - from; i-- != 0; ) {
/* 1404 */       int p = random.nextInt(i + 1);
/* 1405 */       long t = a[(from + i)];
/* 1406 */       a[(from + i)] = a[(from + p)];
/* 1407 */       a[(from + p)] = t;
/*      */     }
/* 1409 */     return a;
/*      */   }
/*      */ 
/*      */   public static long[] shuffle(long[] a, Random random)
/*      */   {
/* 1418 */     for (int i = a.length; i-- != 0; ) {
/* 1419 */       int p = random.nextInt(i + 1);
/* 1420 */       long t = a[i];
/* 1421 */       a[i] = a[p];
/* 1422 */       a[p] = t;
/*      */     }
/* 1424 */     return a;
/*      */   }
/*      */ 
/*      */   public static long[] reverse(long[] a)
/*      */   {
/* 1432 */     int length = a.length;
/* 1433 */     for (int i = length / 2; i-- != 0; ) {
/* 1434 */       long t = a[(length - i - 1)];
/* 1435 */       a[(length - i - 1)] = a[i];
/* 1436 */       a[i] = t;
/*      */     }
/* 1438 */     return a;
/*      */   }
/*      */   private static final class ArrayHashStrategy implements Hash.Strategy<long[]>, Serializable {
/*      */     public static final long serialVersionUID = -7046029254386353129L;
/*      */ 
/*      */     public int hashCode(long[] o) {
/* 1444 */       return java.util.Arrays.hashCode(o);
/*      */     }
/*      */     public boolean equals(long[] a, long[] b) {
/* 1447 */       return LongArrays.equals(a, b);
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongArrays
 * JD-Core Version:    0.6.2
 */