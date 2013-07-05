/*      */ package it.unimi.dsi.fastutil.bytes;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.Hash.Strategy;
/*      */ import it.unimi.dsi.fastutil.ints.IntArrays;
/*      */ import java.io.Serializable;
/*      */ import java.util.Random;
/*      */ 
/*      */ public class ByteArrays
/*      */ {
/*      */   public static final long ONEOVERPHI = 106039L;
/*   87 */   public static final byte[] EMPTY_ARRAY = new byte[0];
/*      */   private static final int SMALL = 7;
/*      */   private static final int MEDIUM = 50;
/*      */   private static final int DIGIT_BITS = 8;
/*      */   private static final int DIGIT_MASK = 255;
/*      */   private static final int DIGITS_PER_ELEMENT = 1;
/* 1456 */   public static final Hash.Strategy<byte[]> HASH_STRATEGY = new ArrayHashStrategy(null);
/*      */ 
/*      */   public static byte[] ensureCapacity(byte[] array, int length)
/*      */   {
/*  100 */     if (length > array.length) {
/*  101 */       byte[] t = new byte[length];
/*      */ 
/*  103 */       System.arraycopy(array, 0, t, 0, array.length);
/*  104 */       return t;
/*      */     }
/*  106 */     return array;
/*      */   }
/*      */ 
/*      */   public static byte[] ensureCapacity(byte[] array, int length, int preserve)
/*      */   {
/*  118 */     if (length > array.length) {
/*  119 */       byte[] t = new byte[length];
/*      */ 
/*  121 */       System.arraycopy(array, 0, t, 0, preserve);
/*  122 */       return t;
/*      */     }
/*  124 */     return array;
/*      */   }
/*      */ 
/*      */   public static byte[] grow(byte[] array, int length)
/*      */   {
/*  142 */     if (length > array.length) {
/*  143 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  144 */       byte[] t = new byte[newLength];
/*      */ 
/*  146 */       System.arraycopy(array, 0, t, 0, array.length);
/*  147 */       return t;
/*      */     }
/*  149 */     return array;
/*      */   }
/*      */ 
/*      */   public static byte[] grow(byte[] array, int length, int preserve)
/*      */   {
/*  168 */     if (length > array.length) {
/*  169 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  170 */       byte[] t = new byte[newLength];
/*      */ 
/*  172 */       System.arraycopy(array, 0, t, 0, preserve);
/*  173 */       return t;
/*      */     }
/*  175 */     return array;
/*      */   }
/*      */ 
/*      */   public static byte[] trim(byte[] array, int length)
/*      */   {
/*  188 */     if (length >= array.length) return array;
/*  189 */     byte[] t = length == 0 ? EMPTY_ARRAY : new byte[length];
/*      */ 
/*  191 */     System.arraycopy(array, 0, t, 0, length);
/*  192 */     return t;
/*      */   }
/*      */ 
/*      */   public static byte[] setLength(byte[] array, int length)
/*      */   {
/*  208 */     if (length == array.length) return array;
/*  209 */     if (length < array.length) return trim(array, length);
/*  210 */     return ensureCapacity(array, length);
/*      */   }
/*      */ 
/*      */   public static byte[] copy(byte[] array, int offset, int length)
/*      */   {
/*  220 */     ensureOffsetLength(array, offset, length);
/*  221 */     byte[] a = length == 0 ? EMPTY_ARRAY : new byte[length];
/*      */ 
/*  223 */     System.arraycopy(array, offset, a, 0, length);
/*  224 */     return a;
/*      */   }
/*      */ 
/*      */   public static byte[] copy(byte[] array)
/*      */   {
/*  232 */     return (byte[])array.clone();
/*      */   }
/*      */ 
/*      */   public static void fill(byte[] array, byte value)
/*      */   {
/*  243 */     int i = array.length;
/*  244 */     while (i-- != 0) array[i] = value;
/*      */   }
/*      */ 
/*      */   public static void fill(byte[] array, int from, int to, byte value)
/*      */   {
/*  258 */     ensureFromTo(array, from, to);
/*  259 */     for (from != 0; to-- != 0; array[to] = value);
/*  260 */     for (int i = from; i < to; i++) array[i] = value;
/*      */   }
/*      */ 
/*      */   public static boolean equals(byte[] a1, byte[] a2)
/*      */   {
/*  272 */     int i = a1.length;
/*  273 */     if (i != a2.length) return false;
/*  274 */     while (i-- != 0) if (a1[i] != a2[i]) return false;
/*  275 */     return true;
/*      */   }
/*      */ 
/*      */   public static void ensureFromTo(byte[] a, int from, int to)
/*      */   {
/*  288 */     it.unimi.dsi.fastutil.Arrays.ensureFromTo(a.length, from, to);
/*      */   }
/*      */ 
/*      */   public static void ensureOffsetLength(byte[] a, int offset, int length)
/*      */   {
/*  301 */     it.unimi.dsi.fastutil.Arrays.ensureOffsetLength(a.length, offset, length);
/*      */   }
/*      */ 
/*      */   private static void swap(byte[] x, int a, int b)
/*      */   {
/*  306 */     byte t = x[a];
/*  307 */     x[a] = x[b];
/*  308 */     x[b] = t;
/*      */   }
/*      */   private static void vecSwap(byte[] x, int a, int b, int n) {
/*  311 */     for (int i = 0; i < n; b++) { swap(x, a, b); i++; a++; } 
/*      */   }
/*      */ 
/*  314 */   private static int med3(byte[] x, int a, int b, int c, ByteComparator comp) { int ab = comp.compare(x[a], x[b]);
/*  315 */     int ac = comp.compare(x[a], x[c]);
/*  316 */     int bc = comp.compare(x[b], x[c]);
/*  317 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*      */   }
/*      */ 
/*      */   private static void selectionSort(byte[] a, int from, int to, ByteComparator comp)
/*      */   {
/*  322 */     for (int i = from; i < to - 1; i++) {
/*  323 */       int m = i;
/*  324 */       for (int j = i + 1; j < to; j++) if (comp.compare(a[j], a[m]) < 0) m = j;
/*  325 */       if (m != i) {
/*  326 */         byte u = a[i];
/*  327 */         a[i] = a[m];
/*  328 */         a[m] = u;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*  333 */   private static void insertionSort(byte[] a, int from, int to, ByteComparator comp) { int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/*  334 */       byte t = a[i];
/*  335 */       int j = i;
/*  336 */       for (byte u = a[(j - 1)]; comp.compare(t, u) < 0; u = a[(--j - 1)]) {
/*  337 */         a[j] = u;
/*  338 */         if (from == j - 1) {
/*  339 */           j--;
/*  340 */           break;
/*      */         }
/*      */       }
/*  343 */       a[j] = t; }
/*      */   }
/*      */ 
/*      */   private static void selectionSort(byte[] a, int from, int to)
/*      */   {
/*  348 */     for (int i = from; i < to - 1; i++) {
/*  349 */       int m = i;
/*  350 */       for (int j = i + 1; j < to; j++) if (a[j] < a[m]) m = j;
/*  351 */       if (m != i) {
/*  352 */         byte u = a[i];
/*  353 */         a[i] = a[m];
/*  354 */         a[m] = u;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void insertionSort(byte[] a, int from, int to) {
/*  360 */     int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/*  361 */       byte t = a[i];
/*  362 */       int j = i;
/*  363 */       for (byte u = a[(j - 1)]; t < u; u = a[(--j - 1)]) {
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
/*      */   public static void quickSort(byte[] x, int from, int to, ByteComparator comp)
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
/*  406 */     byte v = x[m];
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
/*      */   public static void quickSort(byte[] x, ByteComparator comp)
/*      */   {
/*  444 */     quickSort(x, 0, x.length, comp);
/*      */   }
/*      */ 
/*      */   private static int med3(byte[] x, int a, int b, int c) {
/*  448 */     int ab = x[a] == x[b] ? 0 : x[a] < x[b] ? -1 : 1;
/*  449 */     int ac = x[a] == x[c] ? 0 : x[a] < x[c] ? -1 : 1;
/*  450 */     int bc = x[b] == x[c] ? 0 : x[b] < x[c] ? -1 : 1;
/*  451 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*      */   }
/*      */ 
/*      */   public static void quickSort(byte[] x, int from, int to)
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
/*  487 */     byte v = x[m];
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
/*      */   public static void quickSort(byte[] x)
/*      */   {
/*  523 */     quickSort(x, 0, x.length);
/*      */   }
/*      */ 
/*      */   public static void mergeSort(byte[] a, int from, int to, byte[] supp)
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
/*      */   public static void mergeSort(byte[] a, int from, int to)
/*      */   {
/*  569 */     mergeSort(a, from, to, (byte[])a.clone());
/*      */   }
/*      */ 
/*      */   public static void mergeSort(byte[] a)
/*      */   {
/*  579 */     mergeSort(a, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void mergeSort(byte[] a, int from, int to, ByteComparator comp, byte[] supp)
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
/*      */   public static void mergeSort(byte[] a, int from, int to, ByteComparator comp)
/*      */   {
/*  629 */     mergeSort(a, from, to, comp, (byte[])a.clone());
/*      */   }
/*      */ 
/*      */   public static void mergeSort(byte[] a, ByteComparator comp)
/*      */   {
/*  641 */     mergeSort(a, 0, a.length, comp);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(byte[] a, int from, int to, byte key)
/*      */   {
/*  666 */     to--;
/*  667 */     while (from <= to) {
/*  668 */       int mid = from + to >>> 1;
/*  669 */       byte midVal = a[mid];
/*  670 */       if (midVal < key) from = mid + 1;
/*  671 */       else if (midVal > key) to = mid - 1; else
/*  672 */         return mid;
/*      */     }
/*  674 */     return -(from + 1);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(byte[] a, byte key)
/*      */   {
/*  695 */     return binarySearch(a, 0, a.length, key);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(byte[] a, int from, int to, byte key, ByteComparator c)
/*      */   {
/*  720 */     to--;
/*  721 */     while (from <= to) {
/*  722 */       int mid = from + to >>> 1;
/*  723 */       byte midVal = a[mid];
/*  724 */       int cmp = c.compare(midVal, key);
/*  725 */       if (cmp < 0) from = mid + 1;
/*  726 */       else if (cmp > 0) to = mid - 1; else
/*  727 */         return mid;
/*      */     }
/*  729 */     return -(from + 1);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(byte[] a, byte key, ByteComparator c)
/*      */   {
/*  751 */     return binarySearch(a, 0, a.length, key, c);
/*      */   }
/*      */ 
/*      */   public static void radixSort(byte[] a)
/*      */   {
/*  777 */     radixSort(a, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(byte[] a, int from, int to)
/*      */   {
/*  798 */     int maxLevel = 0;
/*  799 */     int stackSize = 1;
/*  800 */     int[] offsetStack = new int[1];
/*  801 */     int offsetPos = 0;
/*  802 */     int[] lengthStack = new int[1];
/*  803 */     int lengthPos = 0;
/*  804 */     int[] levelStack = new int[1];
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
/*  816 */       int signMask = level % 1 == 0 ? 128 : 0;
/*  817 */       if (length < 50) {
/*  818 */         selectionSort(a, first, first + length);
/*      */       }
/*      */       else {
/*  821 */         int shift = (0 - level % 1) * 8;
/*      */ 
/*  823 */         for (int i = length; i-- != 0; digit[i] = ((byte)(a[(first + i)] >>> shift & 0xFF ^ signMask)));
/*  824 */         for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1);
/*  826 */         int lastUsed = -1;
/*  827 */         int i = 0; for (int p = 0; i < 256; i++) {
/*  828 */           if (count[i] != 0) {
/*  829 */             lastUsed = i;
/*  830 */             if ((level < 0) && (count[i] > 1))
/*      */             {
/*  832 */               offsetStack[(offsetPos++)] = (p + first);
/*  833 */               lengthStack[(lengthPos++)] = count[i];
/*  834 */               levelStack[(levelPos++)] = (level + 1);
/*      */             }
/*      */           }
/*      */           int tmp324_323 = (p + count[i]); p = tmp324_323; pos[i] = tmp324_323;
/*      */         }
/*      */ 
/*  840 */         int end = length - count[lastUsed];
/*  841 */         count[lastUsed] = 0;
/*      */ 
/*  843 */         int i = 0; for (int c = -1; i < end; count[c] = 0) {
/*  844 */           byte t = a[(i + first)];
/*  845 */           c = digit[i] & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             int d;
/*  846 */             if ((d = pos[c] -= 1) <= i) break;
/*  847 */             byte z = t;
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
/*      */   private static void insertionSortIndirect(int[] perm, byte[] a, int from, int to)
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
/*      */   public static void radixSortIndirect(int[] perm, byte[] a, boolean stable)
/*      */   {
/*  897 */     radixSortIndirect(perm, a, 0, perm.length, stable); } 
/*      */   public static void radixSortIndirect(int[] perm, byte[] a, int from, int to, boolean stable) { // Byte code:
/*      */     //   0: iconst_0
/*      */     //   1: istore 5
/*      */     //   3: iconst_1
/*      */     //   4: istore 6
/*      */     //   6: iconst_1
/*      */     //   7: newarray int
/*      */     //   9: astore 7
/*      */     //   11: iconst_0
/*      */     //   12: istore 8
/*      */     //   14: iconst_1
/*      */     //   15: newarray int
/*      */     //   17: astore 9
/*      */     //   19: iconst_0
/*      */     //   20: istore 10
/*      */     //   22: iconst_1
/*      */     //   23: newarray int
/*      */     //   25: astore 11
/*      */     //   27: iconst_0
/*      */     //   28: istore 12
/*      */     //   30: aload 7
/*      */     //   32: iload 8
/*      */     //   34: iinc 8 1
/*      */     //   37: iload_2
/*      */     //   38: iastore
/*      */     //   39: aload 9
/*      */     //   41: iload 10
/*      */     //   43: iinc 10 1
/*      */     //   46: iload_3
/*      */     //   47: iload_2
/*      */     //   48: isub
/*      */     //   49: iastore
/*      */     //   50: aload 11
/*      */     //   52: iload 12
/*      */     //   54: iinc 12 1
/*      */     //   57: iconst_0
/*      */     //   58: iastore
/*      */     //   59: sipush 256
/*      */     //   62: newarray int
/*      */     //   64: astore 13
/*      */     //   66: iload 4
/*      */     //   68: ifeq +7 -> 75
/*      */     //   71: aconst_null
/*      */     //   72: goto +8 -> 80
/*      */     //   75: sipush 256
/*      */     //   78: newarray int
/*      */     //   80: astore 14
/*      */     //   82: iload 4
/*      */     //   84: ifeq +10 -> 94
/*      */     //   87: aload_0
/*      */     //   88: arraylength
/*      */     //   89: newarray int
/*      */     //   91: goto +4 -> 95
/*      */     //   94: aconst_null
/*      */     //   95: astore 15
/*      */     //   97: iload_3
/*      */     //   98: iload_2
/*      */     //   99: isub
/*      */     //   100: newarray byte
/*      */     //   102: astore 16
/*      */     //   104: iload 8
/*      */     //   106: ifle +493 -> 599
/*      */     //   109: aload 7
/*      */     //   111: iinc 8 255
/*      */     //   114: iload 8
/*      */     //   116: iaload
/*      */     //   117: istore 17
/*      */     //   119: aload 9
/*      */     //   121: iinc 10 255
/*      */     //   124: iload 10
/*      */     //   126: iaload
/*      */     //   127: istore 18
/*      */     //   129: aload 11
/*      */     //   131: iinc 12 255
/*      */     //   134: iload 12
/*      */     //   136: iaload
/*      */     //   137: istore 19
/*      */     //   139: iload 19
/*      */     //   141: iconst_1
/*      */     //   142: irem
/*      */     //   143: ifne +9 -> 152
/*      */     //   146: sipush 128
/*      */     //   149: goto +4 -> 153
/*      */     //   152: iconst_0
/*      */     //   153: istore 20
/*      */     //   155: iload 18
/*      */     //   157: bipush 50
/*      */     //   159: if_icmpge +18 -> 177
/*      */     //   162: aload_0
/*      */     //   163: aload_1
/*      */     //   164: iload 17
/*      */     //   166: iload 17
/*      */     //   168: iload 18
/*      */     //   170: iadd
/*      */     //   171: invokestatic 40	it/unimi/dsi/fastutil/bytes/ByteArrays:insertionSortIndirect	([I[BII)V
/*      */     //   174: goto -70 -> 104
/*      */     //   177: iconst_0
/*      */     //   178: iload 19
/*      */     //   180: iconst_1
/*      */     //   181: irem
/*      */     //   182: isub
/*      */     //   183: bipush 8
/*      */     //   185: imul
/*      */     //   186: istore 21
/*      */     //   188: iload 18
/*      */     //   190: istore 22
/*      */     //   192: iload 22
/*      */     //   194: iinc 22 255
/*      */     //   197: ifeq +31 -> 228
/*      */     //   200: aload 16
/*      */     //   202: iload 22
/*      */     //   204: aload_1
/*      */     //   205: aload_0
/*      */     //   206: iload 17
/*      */     //   208: iload 22
/*      */     //   210: iadd
/*      */     //   211: iaload
/*      */     //   212: baload
/*      */     //   213: iload 21
/*      */     //   215: iushr
/*      */     //   216: sipush 255
/*      */     //   219: iand
/*      */     //   220: iload 20
/*      */     //   222: ixor
/*      */     //   223: i2b
/*      */     //   224: bastore
/*      */     //   225: goto -33 -> 192
/*      */     //   228: iload 18
/*      */     //   230: istore 22
/*      */     //   232: iload 22
/*      */     //   234: iinc 22 255
/*      */     //   237: ifeq +22 -> 259
/*      */     //   240: aload 13
/*      */     //   242: aload 16
/*      */     //   244: iload 22
/*      */     //   246: baload
/*      */     //   247: sipush 255
/*      */     //   250: iand
/*      */     //   251: dup2
/*      */     //   252: iaload
/*      */     //   253: iconst_1
/*      */     //   254: iadd
/*      */     //   255: iastore
/*      */     //   256: goto -24 -> 232
/*      */     //   259: iconst_m1
/*      */     //   260: istore 22
/*      */     //   262: iconst_0
/*      */     //   263: istore 23
/*      */     //   265: iconst_0
/*      */     //   266: istore 24
/*      */     //   268: iload 23
/*      */     //   270: sipush 256
/*      */     //   273: if_icmpge +113 -> 386
/*      */     //   276: aload 13
/*      */     //   278: iload 23
/*      */     //   280: iaload
/*      */     //   281: ifeq +59 -> 340
/*      */     //   284: iload 23
/*      */     //   286: istore 22
/*      */     //   288: iload 19
/*      */     //   290: ifge +50 -> 340
/*      */     //   293: aload 13
/*      */     //   295: iload 23
/*      */     //   297: iaload
/*      */     //   298: iconst_1
/*      */     //   299: if_icmple +41 -> 340
/*      */     //   302: aload 7
/*      */     //   304: iload 8
/*      */     //   306: iinc 8 1
/*      */     //   309: iload 24
/*      */     //   311: iload 17
/*      */     //   313: iadd
/*      */     //   314: iastore
/*      */     //   315: aload 9
/*      */     //   317: iload 10
/*      */     //   319: iinc 10 1
/*      */     //   322: aload 13
/*      */     //   324: iload 23
/*      */     //   326: iaload
/*      */     //   327: iastore
/*      */     //   328: aload 11
/*      */     //   330: iload 12
/*      */     //   332: iinc 12 1
/*      */     //   335: iload 19
/*      */     //   337: iconst_1
/*      */     //   338: iadd
/*      */     //   339: iastore
/*      */     //   340: iload 4
/*      */     //   342: ifeq +22 -> 364
/*      */     //   345: aload 13
/*      */     //   347: iload 23
/*      */     //   349: iload 24
/*      */     //   351: aload 13
/*      */     //   353: iload 23
/*      */     //   355: iaload
/*      */     //   356: iadd
/*      */     //   357: dup
/*      */     //   358: istore 24
/*      */     //   360: iastore
/*      */     //   361: goto +19 -> 380
/*      */     //   364: aload 14
/*      */     //   366: iload 23
/*      */     //   368: iload 24
/*      */     //   370: aload 13
/*      */     //   372: iload 23
/*      */     //   374: iaload
/*      */     //   375: iadd
/*      */     //   376: dup
/*      */     //   377: istore 24
/*      */     //   379: iastore
/*      */     //   380: iinc 23 1
/*      */     //   383: goto -115 -> 268
/*      */     //   386: iload 4
/*      */     //   388: ifeq +65 -> 453
/*      */     //   391: iload 18
/*      */     //   393: istore 23
/*      */     //   395: iload 23
/*      */     //   397: iinc 23 255
/*      */     //   400: ifeq +33 -> 433
/*      */     //   403: aload 15
/*      */     //   405: aload 13
/*      */     //   407: aload 16
/*      */     //   409: iload 23
/*      */     //   411: baload
/*      */     //   412: sipush 255
/*      */     //   415: iand
/*      */     //   416: dup2
/*      */     //   417: iaload
/*      */     //   418: iconst_1
/*      */     //   419: isub
/*      */     //   420: dup_x2
/*      */     //   421: iastore
/*      */     //   422: aload_0
/*      */     //   423: iload 17
/*      */     //   425: iload 23
/*      */     //   427: iadd
/*      */     //   428: iaload
/*      */     //   429: iastore
/*      */     //   430: goto -35 -> 395
/*      */     //   433: aload 15
/*      */     //   435: iconst_0
/*      */     //   436: aload_0
/*      */     //   437: iload 17
/*      */     //   439: iload 18
/*      */     //   441: invokestatic 2	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
/*      */     //   444: aload 13
/*      */     //   446: iconst_0
/*      */     //   447: invokestatic 41	it/unimi/dsi/fastutil/ints/IntArrays:fill	([II)V
/*      */     //   450: goto +146 -> 596
/*      */     //   453: iload 18
/*      */     //   455: aload 13
/*      */     //   457: iload 22
/*      */     //   459: iaload
/*      */     //   460: isub
/*      */     //   461: istore 23
/*      */     //   463: aload 13
/*      */     //   465: iload 22
/*      */     //   467: iconst_0
/*      */     //   468: iastore
/*      */     //   469: iconst_0
/*      */     //   470: istore 24
/*      */     //   472: iconst_m1
/*      */     //   473: istore 25
/*      */     //   475: iload 24
/*      */     //   477: iload 23
/*      */     //   479: if_icmpge +117 -> 596
/*      */     //   482: aload_0
/*      */     //   483: iload 24
/*      */     //   485: iload 17
/*      */     //   487: iadd
/*      */     //   488: iaload
/*      */     //   489: istore 27
/*      */     //   491: aload 16
/*      */     //   493: iload 24
/*      */     //   495: baload
/*      */     //   496: sipush 255
/*      */     //   499: iand
/*      */     //   500: istore 25
/*      */     //   502: aload 14
/*      */     //   504: iload 25
/*      */     //   506: dup2
/*      */     //   507: iaload
/*      */     //   508: iconst_1
/*      */     //   509: isub
/*      */     //   510: dup_x2
/*      */     //   511: iastore
/*      */     //   512: dup
/*      */     //   513: istore 26
/*      */     //   515: iload 24
/*      */     //   517: if_icmple +51 -> 568
/*      */     //   520: iload 27
/*      */     //   522: istore 28
/*      */     //   524: iload 25
/*      */     //   526: istore 29
/*      */     //   528: aload_0
/*      */     //   529: iload 26
/*      */     //   531: iload 17
/*      */     //   533: iadd
/*      */     //   534: iaload
/*      */     //   535: istore 27
/*      */     //   537: aload 16
/*      */     //   539: iload 26
/*      */     //   541: baload
/*      */     //   542: sipush 255
/*      */     //   545: iand
/*      */     //   546: istore 25
/*      */     //   548: aload_0
/*      */     //   549: iload 26
/*      */     //   551: iload 17
/*      */     //   553: iadd
/*      */     //   554: iload 28
/*      */     //   556: iastore
/*      */     //   557: aload 16
/*      */     //   559: iload 26
/*      */     //   561: iload 29
/*      */     //   563: i2b
/*      */     //   564: bastore
/*      */     //   565: goto -63 -> 502
/*      */     //   568: aload_0
/*      */     //   569: iload 24
/*      */     //   571: iload 17
/*      */     //   573: iadd
/*      */     //   574: iload 27
/*      */     //   576: iastore
/*      */     //   577: iload 24
/*      */     //   579: aload 13
/*      */     //   581: iload 25
/*      */     //   583: iaload
/*      */     //   584: iadd
/*      */     //   585: istore 24
/*      */     //   587: aload 13
/*      */     //   589: iload 25
/*      */     //   591: iconst_0
/*      */     //   592: iastore
/*      */     //   593: goto -118 -> 475
/*      */     //   596: goto -492 -> 104
/*      */     //   599: return } 
/*  995 */   private static void selectionSort(byte[] a, byte[] b, int from, int to) { for (int i = from; i < to - 1; i++) {
/*  996 */       int m = i;
/*  997 */       for (int j = i + 1; j < to; j++)
/*  998 */         if ((a[j] < a[m]) || ((a[j] == a[m]) && (b[j] < b[m]))) m = j;
/*  999 */       if (m != i) {
/* 1000 */         byte t = a[i];
/* 1001 */         a[i] = a[m];
/* 1002 */         a[m] = t;
/* 1003 */         t = b[i];
/* 1004 */         b[i] = b[m];
/* 1005 */         b[m] = t;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSort(byte[] a, byte[] b)
/*      */   {
/* 1029 */     radixSort(a, b, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(byte[] a, byte[] b, int from, int to)
/*      */   {
/* 1054 */     int layers = 2;
/* 1055 */     if (a.length != b.length) throw new IllegalArgumentException("Array size mismatch.");
/* 1056 */     int maxLevel = 1;
/* 1057 */     int stackSize = 256;
/* 1058 */     int[] offsetStack = new int[256];
/* 1059 */     int offsetPos = 0;
/* 1060 */     int[] lengthStack = new int[256];
/* 1061 */     int lengthPos = 0;
/* 1062 */     int[] levelStack = new int[256];
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
/* 1074 */       int signMask = level % 1 == 0 ? 128 : 0;
/* 1075 */       if (length < 50) {
/* 1076 */         selectionSort(a, b, first, first + length);
/*      */       }
/*      */       else {
/* 1079 */         byte[] k = level < 1 ? a : b;
/* 1080 */         int shift = (0 - level % 1) * 8;
/*      */ 
/* 1082 */         for (int i = length; i-- != 0; digit[i] = ((byte)(k[(first + i)] >>> shift & 0xFF ^ signMask)));
/* 1083 */         for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1);
/* 1085 */         int lastUsed = -1;
/* 1086 */         int i = 0; for (int p = 0; i < 256; i++) {
/* 1087 */           if (count[i] != 0) {
/* 1088 */             lastUsed = i;
/* 1089 */             if ((level < 1) && (count[i] > 1)) {
/* 1090 */               offsetStack[(offsetPos++)] = (p + first);
/* 1091 */               lengthStack[(lengthPos++)] = count[i];
/* 1092 */               levelStack[(levelPos++)] = (level + 1);
/*      */             }
/*      */           }
/*      */           int tmp369_368 = (p + count[i]); p = tmp369_368; pos[i] = tmp369_368;
/*      */         }
/*      */ 
/* 1098 */         int end = length - count[lastUsed];
/* 1099 */         count[lastUsed] = 0;
/*      */ 
/* 1101 */         int i = 0; for (int c = -1; i < end; count[c] = 0) {
/* 1102 */           byte t = a[(i + first)];
/* 1103 */           byte u = b[(i + first)];
/* 1104 */           c = digit[i] & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             int d;
/* 1105 */             if ((d = pos[c] -= 1) <= i) break;
/* 1106 */             byte z = t;
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
/*      */   private static void insertionSortIndirect(int[] perm, byte[] a, byte[] b, int from, int to)
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
/*      */   public static void radixSortIndirect(int[] perm, byte[] a, byte[] b, boolean stable)
/*      */   {
/* 1161 */     radixSortIndirect(perm, a, b, 0, perm.length, stable); } 
/*      */   public static void radixSortIndirect(int[] perm, byte[] a, byte[] b, int from, int to, boolean stable) { // Byte code:
/*      */     //   0: iconst_2
/*      */     //   1: istore 6
/*      */     //   3: aload_1
/*      */     //   4: arraylength
/*      */     //   5: aload_2
/*      */     //   6: arraylength
/*      */     //   7: if_icmpeq +13 -> 20
/*      */     //   10: new 43	java/lang/IllegalArgumentException
/*      */     //   13: dup
/*      */     //   14: ldc 44
/*      */     //   16: invokespecial 45	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
/*      */     //   19: athrow
/*      */     //   20: iconst_1
/*      */     //   21: istore 7
/*      */     //   23: sipush 256
/*      */     //   26: istore 8
/*      */     //   28: sipush 256
/*      */     //   31: newarray int
/*      */     //   33: astore 9
/*      */     //   35: iconst_0
/*      */     //   36: istore 10
/*      */     //   38: sipush 256
/*      */     //   41: newarray int
/*      */     //   43: astore 11
/*      */     //   45: iconst_0
/*      */     //   46: istore 12
/*      */     //   48: sipush 256
/*      */     //   51: newarray int
/*      */     //   53: astore 13
/*      */     //   55: iconst_0
/*      */     //   56: istore 14
/*      */     //   58: aload 9
/*      */     //   60: iload 10
/*      */     //   62: iinc 10 1
/*      */     //   65: iload_3
/*      */     //   66: iastore
/*      */     //   67: aload 11
/*      */     //   69: iload 12
/*      */     //   71: iinc 12 1
/*      */     //   74: iload 4
/*      */     //   76: iload_3
/*      */     //   77: isub
/*      */     //   78: iastore
/*      */     //   79: aload 13
/*      */     //   81: iload 14
/*      */     //   83: iinc 14 1
/*      */     //   86: iconst_0
/*      */     //   87: iastore
/*      */     //   88: sipush 256
/*      */     //   91: newarray int
/*      */     //   93: astore 15
/*      */     //   95: iload 5
/*      */     //   97: ifeq +7 -> 104
/*      */     //   100: aconst_null
/*      */     //   101: goto +8 -> 109
/*      */     //   104: sipush 256
/*      */     //   107: newarray int
/*      */     //   109: astore 16
/*      */     //   111: iload 5
/*      */     //   113: ifeq +10 -> 123
/*      */     //   116: aload_0
/*      */     //   117: arraylength
/*      */     //   118: newarray int
/*      */     //   120: goto +4 -> 124
/*      */     //   123: aconst_null
/*      */     //   124: astore 17
/*      */     //   126: iload 4
/*      */     //   128: iload_3
/*      */     //   129: isub
/*      */     //   130: newarray byte
/*      */     //   132: astore 18
/*      */     //   134: iload 10
/*      */     //   136: ifle +509 -> 645
/*      */     //   139: aload 9
/*      */     //   141: iinc 10 255
/*      */     //   144: iload 10
/*      */     //   146: iaload
/*      */     //   147: istore 19
/*      */     //   149: aload 11
/*      */     //   151: iinc 12 255
/*      */     //   154: iload 12
/*      */     //   156: iaload
/*      */     //   157: istore 20
/*      */     //   159: aload 13
/*      */     //   161: iinc 14 255
/*      */     //   164: iload 14
/*      */     //   166: iaload
/*      */     //   167: istore 21
/*      */     //   169: iload 21
/*      */     //   171: iconst_1
/*      */     //   172: irem
/*      */     //   173: ifne +9 -> 182
/*      */     //   176: sipush 128
/*      */     //   179: goto +4 -> 183
/*      */     //   182: iconst_0
/*      */     //   183: istore 22
/*      */     //   185: iload 20
/*      */     //   187: bipush 50
/*      */     //   189: if_icmpge +19 -> 208
/*      */     //   192: aload_0
/*      */     //   193: aload_1
/*      */     //   194: aload_2
/*      */     //   195: iload 19
/*      */     //   197: iload 19
/*      */     //   199: iload 20
/*      */     //   201: iadd
/*      */     //   202: invokestatic 48	it/unimi/dsi/fastutil/bytes/ByteArrays:insertionSortIndirect	([I[B[BII)V
/*      */     //   205: goto -71 -> 134
/*      */     //   208: iload 21
/*      */     //   210: iconst_1
/*      */     //   211: if_icmpge +7 -> 218
/*      */     //   214: aload_1
/*      */     //   215: goto +4 -> 219
/*      */     //   218: aload_2
/*      */     //   219: astore 23
/*      */     //   221: iconst_0
/*      */     //   222: iload 21
/*      */     //   224: iconst_1
/*      */     //   225: irem
/*      */     //   226: isub
/*      */     //   227: bipush 8
/*      */     //   229: imul
/*      */     //   230: istore 24
/*      */     //   232: iload 20
/*      */     //   234: istore 25
/*      */     //   236: iload 25
/*      */     //   238: iinc 25 255
/*      */     //   241: ifeq +32 -> 273
/*      */     //   244: aload 18
/*      */     //   246: iload 25
/*      */     //   248: aload 23
/*      */     //   250: aload_0
/*      */     //   251: iload 19
/*      */     //   253: iload 25
/*      */     //   255: iadd
/*      */     //   256: iaload
/*      */     //   257: baload
/*      */     //   258: iload 24
/*      */     //   260: iushr
/*      */     //   261: sipush 255
/*      */     //   264: iand
/*      */     //   265: iload 22
/*      */     //   267: ixor
/*      */     //   268: i2b
/*      */     //   269: bastore
/*      */     //   270: goto -34 -> 236
/*      */     //   273: iload 20
/*      */     //   275: istore 25
/*      */     //   277: iload 25
/*      */     //   279: iinc 25 255
/*      */     //   282: ifeq +22 -> 304
/*      */     //   285: aload 15
/*      */     //   287: aload 18
/*      */     //   289: iload 25
/*      */     //   291: baload
/*      */     //   292: sipush 255
/*      */     //   295: iand
/*      */     //   296: dup2
/*      */     //   297: iaload
/*      */     //   298: iconst_1
/*      */     //   299: iadd
/*      */     //   300: iastore
/*      */     //   301: goto -24 -> 277
/*      */     //   304: iconst_m1
/*      */     //   305: istore 25
/*      */     //   307: iconst_0
/*      */     //   308: istore 26
/*      */     //   310: iconst_0
/*      */     //   311: istore 27
/*      */     //   313: iload 26
/*      */     //   315: sipush 256
/*      */     //   318: if_icmpge +114 -> 432
/*      */     //   321: aload 15
/*      */     //   323: iload 26
/*      */     //   325: iaload
/*      */     //   326: ifeq +60 -> 386
/*      */     //   329: iload 26
/*      */     //   331: istore 25
/*      */     //   333: iload 21
/*      */     //   335: iconst_1
/*      */     //   336: if_icmpge +50 -> 386
/*      */     //   339: aload 15
/*      */     //   341: iload 26
/*      */     //   343: iaload
/*      */     //   344: iconst_1
/*      */     //   345: if_icmple +41 -> 386
/*      */     //   348: aload 9
/*      */     //   350: iload 10
/*      */     //   352: iinc 10 1
/*      */     //   355: iload 27
/*      */     //   357: iload 19
/*      */     //   359: iadd
/*      */     //   360: iastore
/*      */     //   361: aload 11
/*      */     //   363: iload 12
/*      */     //   365: iinc 12 1
/*      */     //   368: aload 15
/*      */     //   370: iload 26
/*      */     //   372: iaload
/*      */     //   373: iastore
/*      */     //   374: aload 13
/*      */     //   376: iload 14
/*      */     //   378: iinc 14 1
/*      */     //   381: iload 21
/*      */     //   383: iconst_1
/*      */     //   384: iadd
/*      */     //   385: iastore
/*      */     //   386: iload 5
/*      */     //   388: ifeq +22 -> 410
/*      */     //   391: aload 15
/*      */     //   393: iload 26
/*      */     //   395: iload 27
/*      */     //   397: aload 15
/*      */     //   399: iload 26
/*      */     //   401: iaload
/*      */     //   402: iadd
/*      */     //   403: dup
/*      */     //   404: istore 27
/*      */     //   406: iastore
/*      */     //   407: goto +19 -> 426
/*      */     //   410: aload 16
/*      */     //   412: iload 26
/*      */     //   414: iload 27
/*      */     //   416: aload 15
/*      */     //   418: iload 26
/*      */     //   420: iaload
/*      */     //   421: iadd
/*      */     //   422: dup
/*      */     //   423: istore 27
/*      */     //   425: iastore
/*      */     //   426: iinc 26 1
/*      */     //   429: goto -116 -> 313
/*      */     //   432: iload 5
/*      */     //   434: ifeq +65 -> 499
/*      */     //   437: iload 20
/*      */     //   439: istore 26
/*      */     //   441: iload 26
/*      */     //   443: iinc 26 255
/*      */     //   446: ifeq +33 -> 479
/*      */     //   449: aload 17
/*      */     //   451: aload 15
/*      */     //   453: aload 18
/*      */     //   455: iload 26
/*      */     //   457: baload
/*      */     //   458: sipush 255
/*      */     //   461: iand
/*      */     //   462: dup2
/*      */     //   463: iaload
/*      */     //   464: iconst_1
/*      */     //   465: isub
/*      */     //   466: dup_x2
/*      */     //   467: iastore
/*      */     //   468: aload_0
/*      */     //   469: iload 19
/*      */     //   471: iload 26
/*      */     //   473: iadd
/*      */     //   474: iaload
/*      */     //   475: iastore
/*      */     //   476: goto -35 -> 441
/*      */     //   479: aload 17
/*      */     //   481: iconst_0
/*      */     //   482: aload_0
/*      */     //   483: iload 19
/*      */     //   485: iload 20
/*      */     //   487: invokestatic 2	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
/*      */     //   490: aload 15
/*      */     //   492: iconst_0
/*      */     //   493: invokestatic 41	it/unimi/dsi/fastutil/ints/IntArrays:fill	([II)V
/*      */     //   496: goto +146 -> 642
/*      */     //   499: iload 20
/*      */     //   501: aload 15
/*      */     //   503: iload 25
/*      */     //   505: iaload
/*      */     //   506: isub
/*      */     //   507: istore 26
/*      */     //   509: aload 15
/*      */     //   511: iload 25
/*      */     //   513: iconst_0
/*      */     //   514: iastore
/*      */     //   515: iconst_0
/*      */     //   516: istore 27
/*      */     //   518: iconst_m1
/*      */     //   519: istore 28
/*      */     //   521: iload 27
/*      */     //   523: iload 26
/*      */     //   525: if_icmpge +117 -> 642
/*      */     //   528: aload_0
/*      */     //   529: iload 27
/*      */     //   531: iload 19
/*      */     //   533: iadd
/*      */     //   534: iaload
/*      */     //   535: istore 30
/*      */     //   537: aload 18
/*      */     //   539: iload 27
/*      */     //   541: baload
/*      */     //   542: sipush 255
/*      */     //   545: iand
/*      */     //   546: istore 28
/*      */     //   548: aload 16
/*      */     //   550: iload 28
/*      */     //   552: dup2
/*      */     //   553: iaload
/*      */     //   554: iconst_1
/*      */     //   555: isub
/*      */     //   556: dup_x2
/*      */     //   557: iastore
/*      */     //   558: dup
/*      */     //   559: istore 29
/*      */     //   561: iload 27
/*      */     //   563: if_icmple +51 -> 614
/*      */     //   566: iload 30
/*      */     //   568: istore 31
/*      */     //   570: iload 28
/*      */     //   572: istore 32
/*      */     //   574: aload_0
/*      */     //   575: iload 29
/*      */     //   577: iload 19
/*      */     //   579: iadd
/*      */     //   580: iaload
/*      */     //   581: istore 30
/*      */     //   583: aload 18
/*      */     //   585: iload 29
/*      */     //   587: baload
/*      */     //   588: sipush 255
/*      */     //   591: iand
/*      */     //   592: istore 28
/*      */     //   594: aload_0
/*      */     //   595: iload 29
/*      */     //   597: iload 19
/*      */     //   599: iadd
/*      */     //   600: iload 31
/*      */     //   602: iastore
/*      */     //   603: aload 18
/*      */     //   605: iload 29
/*      */     //   607: iload 32
/*      */     //   609: i2b
/*      */     //   610: bastore
/*      */     //   611: goto -63 -> 548
/*      */     //   614: aload_0
/*      */     //   615: iload 27
/*      */     //   617: iload 19
/*      */     //   619: iadd
/*      */     //   620: iload 30
/*      */     //   622: iastore
/*      */     //   623: iload 27
/*      */     //   625: aload 15
/*      */     //   627: iload 28
/*      */     //   629: iaload
/*      */     //   630: iadd
/*      */     //   631: istore 27
/*      */     //   633: aload 15
/*      */     //   635: iload 28
/*      */     //   637: iconst_0
/*      */     //   638: iastore
/*      */     //   639: goto -118 -> 521
/*      */     //   642: goto -508 -> 134
/*      */     //   645: return } 
/* 1263 */   private static void selectionSort(byte[][] a, int from, int to, int level) { int layers = a.length;
/* 1264 */     int firstLayer = level / 1;
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
/* 1278 */           byte u = a[p][i];
/* 1279 */           a[p][i] = a[p][m];
/* 1280 */           a[p][m] = u;
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSort(byte[][] a)
/*      */   {
/* 1305 */     radixSort(a, 0, a[0].length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(byte[][] a, int from, int to)
/*      */   {
/* 1329 */     int layers = a.length;
/* 1330 */     int maxLevel = 1 * layers - 1;
/* 1331 */     int p = layers; for (int l = a[0].length; p-- != 0; ) if (a[p].length != l) throw new IllegalArgumentException("The array of index " + p + " has not the same length of the array of index 0.");
/* 1332 */     int stackSize = 255 * (layers * 1 - 1) + 1;
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
/* 1345 */     byte[] t = new byte[layers];
/* 1346 */     while (offsetPos > 0) {
/* 1347 */       int first = offsetStack[(--offsetPos)];
/* 1348 */       int length = lengthStack[(--lengthPos)];
/* 1349 */       int level = levelStack[(--levelPos)];
/* 1350 */       int signMask = level % 1 == 0 ? 128 : 0;
/* 1351 */       if (length < 50) {
/* 1352 */         selectionSort(a, first, first + length, level);
/*      */       }
/*      */       else {
/* 1355 */         byte[] k = a[(level / 1)];
/* 1356 */         int shift = (0 - level % 1) * 8;
/*      */ 
/* 1358 */         for (int i = length; i-- != 0; digit[i] = ((byte)(k[(first + i)] >>> shift & 0xFF ^ signMask)));
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
/*      */           int tmp423_422 = (p + count[i]); p = tmp423_422; pos[i] = tmp423_422;
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
/* 1382 */               byte u = t[p];
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
/*      */   public static byte[] shuffle(byte[] a, int from, int to, Random random)
/*      */   {
/* 1403 */     for (int i = to - from; i-- != 0; ) {
/* 1404 */       int p = random.nextInt(i + 1);
/* 1405 */       byte t = a[(from + i)];
/* 1406 */       a[(from + i)] = a[(from + p)];
/* 1407 */       a[(from + p)] = t;
/*      */     }
/* 1409 */     return a;
/*      */   }
/*      */ 
/*      */   public static byte[] shuffle(byte[] a, Random random)
/*      */   {
/* 1418 */     for (int i = a.length; i-- != 0; ) {
/* 1419 */       int p = random.nextInt(i + 1);
/* 1420 */       byte t = a[i];
/* 1421 */       a[i] = a[p];
/* 1422 */       a[p] = t;
/*      */     }
/* 1424 */     return a;
/*      */   }
/*      */ 
/*      */   public static byte[] reverse(byte[] a)
/*      */   {
/* 1432 */     int length = a.length;
/* 1433 */     for (int i = length / 2; i-- != 0; ) {
/* 1434 */       byte t = a[(length - i - 1)];
/* 1435 */       a[(length - i - 1)] = a[i];
/* 1436 */       a[i] = t;
/*      */     }
/* 1438 */     return a;
/*      */   }
/*      */   private static final class ArrayHashStrategy implements Hash.Strategy<byte[]>, Serializable {
/*      */     public static final long serialVersionUID = -7046029254386353129L;
/*      */ 
/*      */     public int hashCode(byte[] o) {
/* 1444 */       return java.util.Arrays.hashCode(o);
/*      */     }
/*      */     public boolean equals(byte[] a, byte[] b) {
/* 1447 */       return ByteArrays.equals(a, b);
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteArrays
 * JD-Core Version:    0.6.2
 */