/*      */ package it.unimi.dsi.fastutil.shorts;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.Hash.Strategy;
/*      */ import it.unimi.dsi.fastutil.ints.IntArrays;
/*      */ import java.io.Serializable;
/*      */ import java.util.Random;
/*      */ 
/*      */ public class ShortArrays
/*      */ {
/*      */   public static final long ONEOVERPHI = 106039L;
/*   87 */   public static final short[] EMPTY_ARRAY = new short[0];
/*      */   private static final int SMALL = 7;
/*      */   private static final int MEDIUM = 50;
/*      */   private static final int DIGIT_BITS = 8;
/*      */   private static final int DIGIT_MASK = 255;
/*      */   private static final int DIGITS_PER_ELEMENT = 2;
/* 1456 */   public static final Hash.Strategy<short[]> HASH_STRATEGY = new ArrayHashStrategy(null);
/*      */ 
/*      */   public static short[] ensureCapacity(short[] array, int length)
/*      */   {
/*  100 */     if (length > array.length) {
/*  101 */       short[] t = new short[length];
/*      */ 
/*  103 */       System.arraycopy(array, 0, t, 0, array.length);
/*  104 */       return t;
/*      */     }
/*  106 */     return array;
/*      */   }
/*      */ 
/*      */   public static short[] ensureCapacity(short[] array, int length, int preserve)
/*      */   {
/*  118 */     if (length > array.length) {
/*  119 */       short[] t = new short[length];
/*      */ 
/*  121 */       System.arraycopy(array, 0, t, 0, preserve);
/*  122 */       return t;
/*      */     }
/*  124 */     return array;
/*      */   }
/*      */ 
/*      */   public static short[] grow(short[] array, int length)
/*      */   {
/*  142 */     if (length > array.length) {
/*  143 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  144 */       short[] t = new short[newLength];
/*      */ 
/*  146 */       System.arraycopy(array, 0, t, 0, array.length);
/*  147 */       return t;
/*      */     }
/*  149 */     return array;
/*      */   }
/*      */ 
/*      */   public static short[] grow(short[] array, int length, int preserve)
/*      */   {
/*  168 */     if (length > array.length) {
/*  169 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  170 */       short[] t = new short[newLength];
/*      */ 
/*  172 */       System.arraycopy(array, 0, t, 0, preserve);
/*  173 */       return t;
/*      */     }
/*  175 */     return array;
/*      */   }
/*      */ 
/*      */   public static short[] trim(short[] array, int length)
/*      */   {
/*  188 */     if (length >= array.length) return array;
/*  189 */     short[] t = length == 0 ? EMPTY_ARRAY : new short[length];
/*      */ 
/*  191 */     System.arraycopy(array, 0, t, 0, length);
/*  192 */     return t;
/*      */   }
/*      */ 
/*      */   public static short[] setLength(short[] array, int length)
/*      */   {
/*  208 */     if (length == array.length) return array;
/*  209 */     if (length < array.length) return trim(array, length);
/*  210 */     return ensureCapacity(array, length);
/*      */   }
/*      */ 
/*      */   public static short[] copy(short[] array, int offset, int length)
/*      */   {
/*  220 */     ensureOffsetLength(array, offset, length);
/*  221 */     short[] a = length == 0 ? EMPTY_ARRAY : new short[length];
/*      */ 
/*  223 */     System.arraycopy(array, offset, a, 0, length);
/*  224 */     return a;
/*      */   }
/*      */ 
/*      */   public static short[] copy(short[] array)
/*      */   {
/*  232 */     return (short[])array.clone();
/*      */   }
/*      */ 
/*      */   public static void fill(short[] array, short value)
/*      */   {
/*  243 */     int i = array.length;
/*  244 */     while (i-- != 0) array[i] = value;
/*      */   }
/*      */ 
/*      */   public static void fill(short[] array, int from, int to, short value)
/*      */   {
/*  258 */     ensureFromTo(array, from, to);
/*  259 */     for (from != 0; to-- != 0; array[to] = value);
/*  260 */     for (int i = from; i < to; i++) array[i] = value;
/*      */   }
/*      */ 
/*      */   public static boolean equals(short[] a1, short[] a2)
/*      */   {
/*  272 */     int i = a1.length;
/*  273 */     if (i != a2.length) return false;
/*  274 */     while (i-- != 0) if (a1[i] != a2[i]) return false;
/*  275 */     return true;
/*      */   }
/*      */ 
/*      */   public static void ensureFromTo(short[] a, int from, int to)
/*      */   {
/*  288 */     it.unimi.dsi.fastutil.Arrays.ensureFromTo(a.length, from, to);
/*      */   }
/*      */ 
/*      */   public static void ensureOffsetLength(short[] a, int offset, int length)
/*      */   {
/*  301 */     it.unimi.dsi.fastutil.Arrays.ensureOffsetLength(a.length, offset, length);
/*      */   }
/*      */ 
/*      */   private static void swap(short[] x, int a, int b)
/*      */   {
/*  306 */     short t = x[a];
/*  307 */     x[a] = x[b];
/*  308 */     x[b] = t;
/*      */   }
/*      */   private static void vecSwap(short[] x, int a, int b, int n) {
/*  311 */     for (int i = 0; i < n; b++) { swap(x, a, b); i++; a++; } 
/*      */   }
/*      */ 
/*  314 */   private static int med3(short[] x, int a, int b, int c, ShortComparator comp) { int ab = comp.compare(x[a], x[b]);
/*  315 */     int ac = comp.compare(x[a], x[c]);
/*  316 */     int bc = comp.compare(x[b], x[c]);
/*  317 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*      */   }
/*      */ 
/*      */   private static void selectionSort(short[] a, int from, int to, ShortComparator comp)
/*      */   {
/*  322 */     for (int i = from; i < to - 1; i++) {
/*  323 */       int m = i;
/*  324 */       for (int j = i + 1; j < to; j++) if (comp.compare(a[j], a[m]) < 0) m = j;
/*  325 */       if (m != i) {
/*  326 */         short u = a[i];
/*  327 */         a[i] = a[m];
/*  328 */         a[m] = u;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*  333 */   private static void insertionSort(short[] a, int from, int to, ShortComparator comp) { int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/*  334 */       short t = a[i];
/*  335 */       int j = i;
/*  336 */       for (short u = a[(j - 1)]; comp.compare(t, u) < 0; u = a[(--j - 1)]) {
/*  337 */         a[j] = u;
/*  338 */         if (from == j - 1) {
/*  339 */           j--;
/*  340 */           break;
/*      */         }
/*      */       }
/*  343 */       a[j] = t; }
/*      */   }
/*      */ 
/*      */   private static void selectionSort(short[] a, int from, int to)
/*      */   {
/*  348 */     for (int i = from; i < to - 1; i++) {
/*  349 */       int m = i;
/*  350 */       for (int j = i + 1; j < to; j++) if (a[j] < a[m]) m = j;
/*  351 */       if (m != i) {
/*  352 */         short u = a[i];
/*  353 */         a[i] = a[m];
/*  354 */         a[m] = u;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void insertionSort(short[] a, int from, int to) {
/*  360 */     int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/*  361 */       short t = a[i];
/*  362 */       int j = i;
/*  363 */       for (short u = a[(j - 1)]; t < u; u = a[(--j - 1)]) {
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
/*      */   public static void quickSort(short[] x, int from, int to, ShortComparator comp)
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
/*  406 */     short v = x[m];
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
/*      */   public static void quickSort(short[] x, ShortComparator comp)
/*      */   {
/*  444 */     quickSort(x, 0, x.length, comp);
/*      */   }
/*      */ 
/*      */   private static int med3(short[] x, int a, int b, int c) {
/*  448 */     int ab = x[a] == x[b] ? 0 : x[a] < x[b] ? -1 : 1;
/*  449 */     int ac = x[a] == x[c] ? 0 : x[a] < x[c] ? -1 : 1;
/*  450 */     int bc = x[b] == x[c] ? 0 : x[b] < x[c] ? -1 : 1;
/*  451 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*      */   }
/*      */ 
/*      */   public static void quickSort(short[] x, int from, int to)
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
/*  487 */     short v = x[m];
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
/*      */   public static void quickSort(short[] x)
/*      */   {
/*  523 */     quickSort(x, 0, x.length);
/*      */   }
/*      */ 
/*      */   public static void mergeSort(short[] a, int from, int to, short[] supp)
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
/*      */   public static void mergeSort(short[] a, int from, int to)
/*      */   {
/*  569 */     mergeSort(a, from, to, (short[])a.clone());
/*      */   }
/*      */ 
/*      */   public static void mergeSort(short[] a)
/*      */   {
/*  579 */     mergeSort(a, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void mergeSort(short[] a, int from, int to, ShortComparator comp, short[] supp)
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
/*      */   public static void mergeSort(short[] a, int from, int to, ShortComparator comp)
/*      */   {
/*  629 */     mergeSort(a, from, to, comp, (short[])a.clone());
/*      */   }
/*      */ 
/*      */   public static void mergeSort(short[] a, ShortComparator comp)
/*      */   {
/*  641 */     mergeSort(a, 0, a.length, comp);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(short[] a, int from, int to, short key)
/*      */   {
/*  666 */     to--;
/*  667 */     while (from <= to) {
/*  668 */       int mid = from + to >>> 1;
/*  669 */       short midVal = a[mid];
/*  670 */       if (midVal < key) from = mid + 1;
/*  671 */       else if (midVal > key) to = mid - 1; else
/*  672 */         return mid;
/*      */     }
/*  674 */     return -(from + 1);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(short[] a, short key)
/*      */   {
/*  695 */     return binarySearch(a, 0, a.length, key);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(short[] a, int from, int to, short key, ShortComparator c)
/*      */   {
/*  720 */     to--;
/*  721 */     while (from <= to) {
/*  722 */       int mid = from + to >>> 1;
/*  723 */       short midVal = a[mid];
/*  724 */       int cmp = c.compare(midVal, key);
/*  725 */       if (cmp < 0) from = mid + 1;
/*  726 */       else if (cmp > 0) to = mid - 1; else
/*  727 */         return mid;
/*      */     }
/*  729 */     return -(from + 1);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(short[] a, short key, ShortComparator c)
/*      */   {
/*  751 */     return binarySearch(a, 0, a.length, key, c);
/*      */   }
/*      */ 
/*      */   public static void radixSort(short[] a)
/*      */   {
/*  777 */     radixSort(a, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(short[] a, int from, int to)
/*      */   {
/*  798 */     int maxLevel = 1;
/*  799 */     int stackSize = 256;
/*  800 */     int[] offsetStack = new int[256];
/*  801 */     int offsetPos = 0;
/*  802 */     int[] lengthStack = new int[256];
/*  803 */     int lengthPos = 0;
/*  804 */     int[] levelStack = new int[256];
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
/*  816 */       int signMask = level % 2 == 0 ? 128 : 0;
/*  817 */       if (length < 50) {
/*  818 */         selectionSort(a, first, first + length);
/*      */       }
/*      */       else {
/*  821 */         int shift = (1 - level % 2) * 8;
/*      */ 
/*  823 */         for (int i = length; i-- != 0; digit[i] = ((byte)(a[(first + i)] >>> shift & 0xFF ^ signMask)));
/*  824 */         for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1);
/*  826 */         int lastUsed = -1;
/*  827 */         int i = 0; for (int p = 0; i < 256; i++) {
/*  828 */           if (count[i] != 0) {
/*  829 */             lastUsed = i;
/*  830 */             if ((level < 1) && (count[i] > 1))
/*      */             {
/*  832 */               offsetStack[(offsetPos++)] = (p + first);
/*  833 */               lengthStack[(lengthPos++)] = count[i];
/*  834 */               levelStack[(levelPos++)] = (level + 1);
/*      */             }
/*      */           }
/*      */           int tmp333_332 = (p + count[i]); p = tmp333_332; pos[i] = tmp333_332;
/*      */         }
/*      */ 
/*  840 */         int end = length - count[lastUsed];
/*  841 */         count[lastUsed] = 0;
/*      */ 
/*  843 */         int i = 0; for (int c = -1; i < end; count[c] = 0) {
/*  844 */           short t = a[(i + first)];
/*  845 */           c = digit[i] & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             int d;
/*  846 */             if ((d = pos[c] -= 1) <= i) break;
/*  847 */             short z = t;
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
/*      */   private static void insertionSortIndirect(int[] perm, short[] a, int from, int to)
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
/*      */   public static void radixSortIndirect(int[] perm, short[] a, boolean stable)
/*      */   {
/*  897 */     radixSortIndirect(perm, a, 0, perm.length, stable); } 
/*      */   public static void radixSortIndirect(int[] perm, short[] a, int from, int to, boolean stable) { // Byte code:
/*      */     //   0: iconst_1
/*      */     //   1: istore 5
/*      */     //   3: sipush 256
/*      */     //   6: istore 6
/*      */     //   8: sipush 256
/*      */     //   11: newarray int
/*      */     //   13: astore 7
/*      */     //   15: iconst_0
/*      */     //   16: istore 8
/*      */     //   18: sipush 256
/*      */     //   21: newarray int
/*      */     //   23: astore 9
/*      */     //   25: iconst_0
/*      */     //   26: istore 10
/*      */     //   28: sipush 256
/*      */     //   31: newarray int
/*      */     //   33: astore 11
/*      */     //   35: iconst_0
/*      */     //   36: istore 12
/*      */     //   38: aload 7
/*      */     //   40: iload 8
/*      */     //   42: iinc 8 1
/*      */     //   45: iload_2
/*      */     //   46: iastore
/*      */     //   47: aload 9
/*      */     //   49: iload 10
/*      */     //   51: iinc 10 1
/*      */     //   54: iload_3
/*      */     //   55: iload_2
/*      */     //   56: isub
/*      */     //   57: iastore
/*      */     //   58: aload 11
/*      */     //   60: iload 12
/*      */     //   62: iinc 12 1
/*      */     //   65: iconst_0
/*      */     //   66: iastore
/*      */     //   67: sipush 256
/*      */     //   70: newarray int
/*      */     //   72: astore 13
/*      */     //   74: iload 4
/*      */     //   76: ifeq +7 -> 83
/*      */     //   79: aconst_null
/*      */     //   80: goto +8 -> 88
/*      */     //   83: sipush 256
/*      */     //   86: newarray int
/*      */     //   88: astore 14
/*      */     //   90: iload 4
/*      */     //   92: ifeq +10 -> 102
/*      */     //   95: aload_0
/*      */     //   96: arraylength
/*      */     //   97: newarray int
/*      */     //   99: goto +4 -> 103
/*      */     //   102: aconst_null
/*      */     //   103: astore 15
/*      */     //   105: iload_3
/*      */     //   106: iload_2
/*      */     //   107: isub
/*      */     //   108: newarray byte
/*      */     //   110: astore 16
/*      */     //   112: iload 8
/*      */     //   114: ifle +494 -> 608
/*      */     //   117: aload 7
/*      */     //   119: iinc 8 255
/*      */     //   122: iload 8
/*      */     //   124: iaload
/*      */     //   125: istore 17
/*      */     //   127: aload 9
/*      */     //   129: iinc 10 255
/*      */     //   132: iload 10
/*      */     //   134: iaload
/*      */     //   135: istore 18
/*      */     //   137: aload 11
/*      */     //   139: iinc 12 255
/*      */     //   142: iload 12
/*      */     //   144: iaload
/*      */     //   145: istore 19
/*      */     //   147: iload 19
/*      */     //   149: iconst_2
/*      */     //   150: irem
/*      */     //   151: ifne +9 -> 160
/*      */     //   154: sipush 128
/*      */     //   157: goto +4 -> 161
/*      */     //   160: iconst_0
/*      */     //   161: istore 20
/*      */     //   163: iload 18
/*      */     //   165: bipush 50
/*      */     //   167: if_icmpge +18 -> 185
/*      */     //   170: aload_0
/*      */     //   171: aload_1
/*      */     //   172: iload 17
/*      */     //   174: iload 17
/*      */     //   176: iload 18
/*      */     //   178: iadd
/*      */     //   179: invokestatic 40	it/unimi/dsi/fastutil/shorts/ShortArrays:insertionSortIndirect	([I[SII)V
/*      */     //   182: goto -70 -> 112
/*      */     //   185: iconst_1
/*      */     //   186: iload 19
/*      */     //   188: iconst_2
/*      */     //   189: irem
/*      */     //   190: isub
/*      */     //   191: bipush 8
/*      */     //   193: imul
/*      */     //   194: istore 21
/*      */     //   196: iload 18
/*      */     //   198: istore 22
/*      */     //   200: iload 22
/*      */     //   202: iinc 22 255
/*      */     //   205: ifeq +31 -> 236
/*      */     //   208: aload 16
/*      */     //   210: iload 22
/*      */     //   212: aload_1
/*      */     //   213: aload_0
/*      */     //   214: iload 17
/*      */     //   216: iload 22
/*      */     //   218: iadd
/*      */     //   219: iaload
/*      */     //   220: saload
/*      */     //   221: iload 21
/*      */     //   223: iushr
/*      */     //   224: sipush 255
/*      */     //   227: iand
/*      */     //   228: iload 20
/*      */     //   230: ixor
/*      */     //   231: i2b
/*      */     //   232: bastore
/*      */     //   233: goto -33 -> 200
/*      */     //   236: iload 18
/*      */     //   238: istore 22
/*      */     //   240: iload 22
/*      */     //   242: iinc 22 255
/*      */     //   245: ifeq +22 -> 267
/*      */     //   248: aload 13
/*      */     //   250: aload 16
/*      */     //   252: iload 22
/*      */     //   254: baload
/*      */     //   255: sipush 255
/*      */     //   258: iand
/*      */     //   259: dup2
/*      */     //   260: iaload
/*      */     //   261: iconst_1
/*      */     //   262: iadd
/*      */     //   263: iastore
/*      */     //   264: goto -24 -> 240
/*      */     //   267: iconst_m1
/*      */     //   268: istore 22
/*      */     //   270: iconst_0
/*      */     //   271: istore 23
/*      */     //   273: iconst_0
/*      */     //   274: istore 24
/*      */     //   276: iload 23
/*      */     //   278: sipush 256
/*      */     //   281: if_icmpge +114 -> 395
/*      */     //   284: aload 13
/*      */     //   286: iload 23
/*      */     //   288: iaload
/*      */     //   289: ifeq +60 -> 349
/*      */     //   292: iload 23
/*      */     //   294: istore 22
/*      */     //   296: iload 19
/*      */     //   298: iconst_1
/*      */     //   299: if_icmpge +50 -> 349
/*      */     //   302: aload 13
/*      */     //   304: iload 23
/*      */     //   306: iaload
/*      */     //   307: iconst_1
/*      */     //   308: if_icmple +41 -> 349
/*      */     //   311: aload 7
/*      */     //   313: iload 8
/*      */     //   315: iinc 8 1
/*      */     //   318: iload 24
/*      */     //   320: iload 17
/*      */     //   322: iadd
/*      */     //   323: iastore
/*      */     //   324: aload 9
/*      */     //   326: iload 10
/*      */     //   328: iinc 10 1
/*      */     //   331: aload 13
/*      */     //   333: iload 23
/*      */     //   335: iaload
/*      */     //   336: iastore
/*      */     //   337: aload 11
/*      */     //   339: iload 12
/*      */     //   341: iinc 12 1
/*      */     //   344: iload 19
/*      */     //   346: iconst_1
/*      */     //   347: iadd
/*      */     //   348: iastore
/*      */     //   349: iload 4
/*      */     //   351: ifeq +22 -> 373
/*      */     //   354: aload 13
/*      */     //   356: iload 23
/*      */     //   358: iload 24
/*      */     //   360: aload 13
/*      */     //   362: iload 23
/*      */     //   364: iaload
/*      */     //   365: iadd
/*      */     //   366: dup
/*      */     //   367: istore 24
/*      */     //   369: iastore
/*      */     //   370: goto +19 -> 389
/*      */     //   373: aload 14
/*      */     //   375: iload 23
/*      */     //   377: iload 24
/*      */     //   379: aload 13
/*      */     //   381: iload 23
/*      */     //   383: iaload
/*      */     //   384: iadd
/*      */     //   385: dup
/*      */     //   386: istore 24
/*      */     //   388: iastore
/*      */     //   389: iinc 23 1
/*      */     //   392: goto -116 -> 276
/*      */     //   395: iload 4
/*      */     //   397: ifeq +65 -> 462
/*      */     //   400: iload 18
/*      */     //   402: istore 23
/*      */     //   404: iload 23
/*      */     //   406: iinc 23 255
/*      */     //   409: ifeq +33 -> 442
/*      */     //   412: aload 15
/*      */     //   414: aload 13
/*      */     //   416: aload 16
/*      */     //   418: iload 23
/*      */     //   420: baload
/*      */     //   421: sipush 255
/*      */     //   424: iand
/*      */     //   425: dup2
/*      */     //   426: iaload
/*      */     //   427: iconst_1
/*      */     //   428: isub
/*      */     //   429: dup_x2
/*      */     //   430: iastore
/*      */     //   431: aload_0
/*      */     //   432: iload 17
/*      */     //   434: iload 23
/*      */     //   436: iadd
/*      */     //   437: iaload
/*      */     //   438: iastore
/*      */     //   439: goto -35 -> 404
/*      */     //   442: aload 15
/*      */     //   444: iconst_0
/*      */     //   445: aload_0
/*      */     //   446: iload 17
/*      */     //   448: iload 18
/*      */     //   450: invokestatic 2	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
/*      */     //   453: aload 13
/*      */     //   455: iconst_0
/*      */     //   456: invokestatic 41	it/unimi/dsi/fastutil/ints/IntArrays:fill	([II)V
/*      */     //   459: goto +146 -> 605
/*      */     //   462: iload 18
/*      */     //   464: aload 13
/*      */     //   466: iload 22
/*      */     //   468: iaload
/*      */     //   469: isub
/*      */     //   470: istore 23
/*      */     //   472: aload 13
/*      */     //   474: iload 22
/*      */     //   476: iconst_0
/*      */     //   477: iastore
/*      */     //   478: iconst_0
/*      */     //   479: istore 24
/*      */     //   481: iconst_m1
/*      */     //   482: istore 25
/*      */     //   484: iload 24
/*      */     //   486: iload 23
/*      */     //   488: if_icmpge +117 -> 605
/*      */     //   491: aload_0
/*      */     //   492: iload 24
/*      */     //   494: iload 17
/*      */     //   496: iadd
/*      */     //   497: iaload
/*      */     //   498: istore 27
/*      */     //   500: aload 16
/*      */     //   502: iload 24
/*      */     //   504: baload
/*      */     //   505: sipush 255
/*      */     //   508: iand
/*      */     //   509: istore 25
/*      */     //   511: aload 14
/*      */     //   513: iload 25
/*      */     //   515: dup2
/*      */     //   516: iaload
/*      */     //   517: iconst_1
/*      */     //   518: isub
/*      */     //   519: dup_x2
/*      */     //   520: iastore
/*      */     //   521: dup
/*      */     //   522: istore 26
/*      */     //   524: iload 24
/*      */     //   526: if_icmple +51 -> 577
/*      */     //   529: iload 27
/*      */     //   531: istore 28
/*      */     //   533: iload 25
/*      */     //   535: istore 29
/*      */     //   537: aload_0
/*      */     //   538: iload 26
/*      */     //   540: iload 17
/*      */     //   542: iadd
/*      */     //   543: iaload
/*      */     //   544: istore 27
/*      */     //   546: aload 16
/*      */     //   548: iload 26
/*      */     //   550: baload
/*      */     //   551: sipush 255
/*      */     //   554: iand
/*      */     //   555: istore 25
/*      */     //   557: aload_0
/*      */     //   558: iload 26
/*      */     //   560: iload 17
/*      */     //   562: iadd
/*      */     //   563: iload 28
/*      */     //   565: iastore
/*      */     //   566: aload 16
/*      */     //   568: iload 26
/*      */     //   570: iload 29
/*      */     //   572: i2b
/*      */     //   573: bastore
/*      */     //   574: goto -63 -> 511
/*      */     //   577: aload_0
/*      */     //   578: iload 24
/*      */     //   580: iload 17
/*      */     //   582: iadd
/*      */     //   583: iload 27
/*      */     //   585: iastore
/*      */     //   586: iload 24
/*      */     //   588: aload 13
/*      */     //   590: iload 25
/*      */     //   592: iaload
/*      */     //   593: iadd
/*      */     //   594: istore 24
/*      */     //   596: aload 13
/*      */     //   598: iload 25
/*      */     //   600: iconst_0
/*      */     //   601: iastore
/*      */     //   602: goto -118 -> 484
/*      */     //   605: goto -493 -> 112
/*      */     //   608: return } 
/*  995 */   private static void selectionSort(short[] a, short[] b, int from, int to) { for (int i = from; i < to - 1; i++) {
/*  996 */       int m = i;
/*  997 */       for (int j = i + 1; j < to; j++)
/*  998 */         if ((a[j] < a[m]) || ((a[j] == a[m]) && (b[j] < b[m]))) m = j;
/*  999 */       if (m != i) {
/* 1000 */         short t = a[i];
/* 1001 */         a[i] = a[m];
/* 1002 */         a[m] = t;
/* 1003 */         t = b[i];
/* 1004 */         b[i] = b[m];
/* 1005 */         b[m] = t;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSort(short[] a, short[] b)
/*      */   {
/* 1029 */     radixSort(a, b, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(short[] a, short[] b, int from, int to)
/*      */   {
/* 1054 */     int layers = 2;
/* 1055 */     if (a.length != b.length) throw new IllegalArgumentException("Array size mismatch.");
/* 1056 */     int maxLevel = 3;
/* 1057 */     int stackSize = 766;
/* 1058 */     int[] offsetStack = new int[766];
/* 1059 */     int offsetPos = 0;
/* 1060 */     int[] lengthStack = new int[766];
/* 1061 */     int lengthPos = 0;
/* 1062 */     int[] levelStack = new int[766];
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
/* 1074 */       int signMask = level % 2 == 0 ? 128 : 0;
/* 1075 */       if (length < 50) {
/* 1076 */         selectionSort(a, b, first, first + length);
/*      */       }
/*      */       else {
/* 1079 */         short[] k = level < 2 ? a : b;
/* 1080 */         int shift = (1 - level % 2) * 8;
/*      */ 
/* 1082 */         for (int i = length; i-- != 0; digit[i] = ((byte)(k[(first + i)] >>> shift & 0xFF ^ signMask)));
/* 1083 */         for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1);
/* 1085 */         int lastUsed = -1;
/* 1086 */         int i = 0; for (int p = 0; i < 256; i++) {
/* 1087 */           if (count[i] != 0) {
/* 1088 */             lastUsed = i;
/* 1089 */             if ((level < 3) && (count[i] > 1)) {
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
/* 1102 */           short t = a[(i + first)];
/* 1103 */           short u = b[(i + first)];
/* 1104 */           c = digit[i] & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             int d;
/* 1105 */             if ((d = pos[c] -= 1) <= i) break;
/* 1106 */             short z = t;
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
/*      */   private static void insertionSortIndirect(int[] perm, short[] a, short[] b, int from, int to)
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
/*      */   public static void radixSortIndirect(int[] perm, short[] a, short[] b, boolean stable)
/*      */   {
/* 1161 */     radixSortIndirect(perm, a, b, 0, perm.length, stable); } 
/*      */   public static void radixSortIndirect(int[] perm, short[] a, short[] b, int from, int to, boolean stable) { // Byte code:
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
/*      */     //   20: iconst_3
/*      */     //   21: istore 7
/*      */     //   23: sipush 766
/*      */     //   26: istore 8
/*      */     //   28: sipush 766
/*      */     //   31: newarray int
/*      */     //   33: astore 9
/*      */     //   35: iconst_0
/*      */     //   36: istore 10
/*      */     //   38: sipush 766
/*      */     //   41: newarray int
/*      */     //   43: astore 11
/*      */     //   45: iconst_0
/*      */     //   46: istore 12
/*      */     //   48: sipush 766
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
/*      */     //   171: iconst_2
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
/*      */     //   202: invokestatic 48	it/unimi/dsi/fastutil/shorts/ShortArrays:insertionSortIndirect	([I[S[SII)V
/*      */     //   205: goto -71 -> 134
/*      */     //   208: iload 21
/*      */     //   210: iconst_2
/*      */     //   211: if_icmpge +7 -> 218
/*      */     //   214: aload_1
/*      */     //   215: goto +4 -> 219
/*      */     //   218: aload_2
/*      */     //   219: astore 23
/*      */     //   221: iconst_1
/*      */     //   222: iload 21
/*      */     //   224: iconst_2
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
/*      */     //   257: saload
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
/*      */     //   335: iconst_3
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
/* 1263 */   private static void selectionSort(short[][] a, int from, int to, int level) { int layers = a.length;
/* 1264 */     int firstLayer = level / 2;
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
/* 1278 */           short u = a[p][i];
/* 1279 */           a[p][i] = a[p][m];
/* 1280 */           a[p][m] = u;
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSort(short[][] a)
/*      */   {
/* 1305 */     radixSort(a, 0, a[0].length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(short[][] a, int from, int to)
/*      */   {
/* 1329 */     int layers = a.length;
/* 1330 */     int maxLevel = 2 * layers - 1;
/* 1331 */     int p = layers; for (int l = a[0].length; p-- != 0; ) if (a[p].length != l) throw new IllegalArgumentException("The array of index " + p + " has not the same length of the array of index 0.");
/* 1332 */     int stackSize = 255 * (layers * 2 - 1) + 1;
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
/* 1345 */     short[] t = new short[layers];
/* 1346 */     while (offsetPos > 0) {
/* 1347 */       int first = offsetStack[(--offsetPos)];
/* 1348 */       int length = lengthStack[(--lengthPos)];
/* 1349 */       int level = levelStack[(--levelPos)];
/* 1350 */       int signMask = level % 2 == 0 ? 128 : 0;
/* 1351 */       if (length < 50) {
/* 1352 */         selectionSort(a, first, first + length, level);
/*      */       }
/*      */       else {
/* 1355 */         short[] k = a[(level / 2)];
/* 1356 */         int shift = (1 - level % 2) * 8;
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
/* 1382 */               short u = t[p];
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
/*      */   public static short[] shuffle(short[] a, int from, int to, Random random)
/*      */   {
/* 1403 */     for (int i = to - from; i-- != 0; ) {
/* 1404 */       int p = random.nextInt(i + 1);
/* 1405 */       short t = a[(from + i)];
/* 1406 */       a[(from + i)] = a[(from + p)];
/* 1407 */       a[(from + p)] = t;
/*      */     }
/* 1409 */     return a;
/*      */   }
/*      */ 
/*      */   public static short[] shuffle(short[] a, Random random)
/*      */   {
/* 1418 */     for (int i = a.length; i-- != 0; ) {
/* 1419 */       int p = random.nextInt(i + 1);
/* 1420 */       short t = a[i];
/* 1421 */       a[i] = a[p];
/* 1422 */       a[p] = t;
/*      */     }
/* 1424 */     return a;
/*      */   }
/*      */ 
/*      */   public static short[] reverse(short[] a)
/*      */   {
/* 1432 */     int length = a.length;
/* 1433 */     for (int i = length / 2; i-- != 0; ) {
/* 1434 */       short t = a[(length - i - 1)];
/* 1435 */       a[(length - i - 1)] = a[i];
/* 1436 */       a[i] = t;
/*      */     }
/* 1438 */     return a;
/*      */   }
/*      */   private static final class ArrayHashStrategy implements Hash.Strategy<short[]>, Serializable {
/*      */     public static final long serialVersionUID = -7046029254386353129L;
/*      */ 
/*      */     public int hashCode(short[] o) {
/* 1444 */       return java.util.Arrays.hashCode(o);
/*      */     }
/*      */     public boolean equals(short[] a, short[] b) {
/* 1447 */       return ShortArrays.equals(a, b);
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortArrays
 * JD-Core Version:    0.6.2
 */