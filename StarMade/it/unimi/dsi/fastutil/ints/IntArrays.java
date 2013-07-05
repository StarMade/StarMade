/*      */ package it.unimi.dsi.fastutil.ints;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.Hash.Strategy;
/*      */ import java.io.Serializable;
/*      */ import java.util.Random;
/*      */ 
/*      */ public class IntArrays
/*      */ {
/*      */   public static final long ONEOVERPHI = 106039L;
/*   87 */   public static final int[] EMPTY_ARRAY = new int[0];
/*      */   private static final int SMALL = 7;
/*      */   private static final int MEDIUM = 50;
/*      */   private static final int DIGIT_BITS = 8;
/*      */   private static final int DIGIT_MASK = 255;
/*      */   private static final int DIGITS_PER_ELEMENT = 4;
/* 1456 */   public static final Hash.Strategy<int[]> HASH_STRATEGY = new ArrayHashStrategy(null);
/*      */ 
/*      */   public static int[] ensureCapacity(int[] array, int length)
/*      */   {
/*  100 */     if (length > array.length) {
/*  101 */       int[] t = new int[length];
/*      */ 
/*  103 */       System.arraycopy(array, 0, t, 0, array.length);
/*  104 */       return t;
/*      */     }
/*  106 */     return array;
/*      */   }
/*      */ 
/*      */   public static int[] ensureCapacity(int[] array, int length, int preserve)
/*      */   {
/*  118 */     if (length > array.length) {
/*  119 */       int[] t = new int[length];
/*      */ 
/*  121 */       System.arraycopy(array, 0, t, 0, preserve);
/*  122 */       return t;
/*      */     }
/*  124 */     return array;
/*      */   }
/*      */ 
/*      */   public static int[] grow(int[] array, int length)
/*      */   {
/*  142 */     if (length > array.length) {
/*  143 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  144 */       int[] t = new int[newLength];
/*      */ 
/*  146 */       System.arraycopy(array, 0, t, 0, array.length);
/*  147 */       return t;
/*      */     }
/*  149 */     return array;
/*      */   }
/*      */ 
/*      */   public static int[] grow(int[] array, int length, int preserve)
/*      */   {
/*  168 */     if (length > array.length) {
/*  169 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  170 */       int[] t = new int[newLength];
/*      */ 
/*  172 */       System.arraycopy(array, 0, t, 0, preserve);
/*  173 */       return t;
/*      */     }
/*  175 */     return array;
/*      */   }
/*      */ 
/*      */   public static int[] trim(int[] array, int length)
/*      */   {
/*  188 */     if (length >= array.length) return array;
/*  189 */     int[] t = length == 0 ? EMPTY_ARRAY : new int[length];
/*      */ 
/*  191 */     System.arraycopy(array, 0, t, 0, length);
/*  192 */     return t;
/*      */   }
/*      */ 
/*      */   public static int[] setLength(int[] array, int length)
/*      */   {
/*  208 */     if (length == array.length) return array;
/*  209 */     if (length < array.length) return trim(array, length);
/*  210 */     return ensureCapacity(array, length);
/*      */   }
/*      */ 
/*      */   public static int[] copy(int[] array, int offset, int length)
/*      */   {
/*  220 */     ensureOffsetLength(array, offset, length);
/*  221 */     int[] a = length == 0 ? EMPTY_ARRAY : new int[length];
/*      */ 
/*  223 */     System.arraycopy(array, offset, a, 0, length);
/*  224 */     return a;
/*      */   }
/*      */ 
/*      */   public static int[] copy(int[] array)
/*      */   {
/*  232 */     return (int[])array.clone();
/*      */   }
/*      */ 
/*      */   public static void fill(int[] array, int value)
/*      */   {
/*  243 */     int i = array.length;
/*  244 */     while (i-- != 0) array[i] = value;
/*      */   }
/*      */ 
/*      */   public static void fill(int[] array, int from, int to, int value)
/*      */   {
/*  258 */     ensureFromTo(array, from, to);
/*  259 */     for (from != 0; to-- != 0; array[to] = value);
/*  260 */     for (int i = from; i < to; i++) array[i] = value;
/*      */   }
/*      */ 
/*      */   public static boolean equals(int[] a1, int[] a2)
/*      */   {
/*  272 */     int i = a1.length;
/*  273 */     if (i != a2.length) return false;
/*  274 */     while (i-- != 0) if (a1[i] != a2[i]) return false;
/*  275 */     return true;
/*      */   }
/*      */ 
/*      */   public static void ensureFromTo(int[] a, int from, int to)
/*      */   {
/*  288 */     it.unimi.dsi.fastutil.Arrays.ensureFromTo(a.length, from, to);
/*      */   }
/*      */ 
/*      */   public static void ensureOffsetLength(int[] a, int offset, int length)
/*      */   {
/*  301 */     it.unimi.dsi.fastutil.Arrays.ensureOffsetLength(a.length, offset, length);
/*      */   }
/*      */ 
/*      */   private static void swap(int[] x, int a, int b)
/*      */   {
/*  306 */     int t = x[a];
/*  307 */     x[a] = x[b];
/*  308 */     x[b] = t;
/*      */   }
/*      */   private static void vecSwap(int[] x, int a, int b, int n) {
/*  311 */     for (int i = 0; i < n; b++) { swap(x, a, b); i++; a++; } 
/*      */   }
/*      */ 
/*  314 */   private static int med3(int[] x, int a, int b, int c, IntComparator comp) { int ab = comp.compare(x[a], x[b]);
/*  315 */     int ac = comp.compare(x[a], x[c]);
/*  316 */     int bc = comp.compare(x[b], x[c]);
/*  317 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*      */   }
/*      */ 
/*      */   private static void selectionSort(int[] a, int from, int to, IntComparator comp)
/*      */   {
/*  322 */     for (int i = from; i < to - 1; i++) {
/*  323 */       int m = i;
/*  324 */       for (int j = i + 1; j < to; j++) if (comp.compare(a[j], a[m]) < 0) m = j;
/*  325 */       if (m != i) {
/*  326 */         int u = a[i];
/*  327 */         a[i] = a[m];
/*  328 */         a[m] = u;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*  333 */   private static void insertionSort(int[] a, int from, int to, IntComparator comp) { int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/*  334 */       int t = a[i];
/*  335 */       int j = i;
/*  336 */       for (int u = a[(j - 1)]; comp.compare(t, u) < 0; u = a[(--j - 1)]) {
/*  337 */         a[j] = u;
/*  338 */         if (from == j - 1) {
/*  339 */           j--;
/*  340 */           break;
/*      */         }
/*      */       }
/*  343 */       a[j] = t; }
/*      */   }
/*      */ 
/*      */   private static void selectionSort(int[] a, int from, int to)
/*      */   {
/*  348 */     for (int i = from; i < to - 1; i++) {
/*  349 */       int m = i;
/*  350 */       for (int j = i + 1; j < to; j++) if (a[j] < a[m]) m = j;
/*  351 */       if (m != i) {
/*  352 */         int u = a[i];
/*  353 */         a[i] = a[m];
/*  354 */         a[m] = u;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void insertionSort(int[] a, int from, int to) {
/*  360 */     int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/*  361 */       int t = a[i];
/*  362 */       int j = i;
/*  363 */       for (int u = a[(j - 1)]; t < u; u = a[(--j - 1)]) {
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
/*      */   public static void quickSort(int[] x, int from, int to, IntComparator comp)
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
/*  406 */     int v = x[m];
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
/*      */   public static void quickSort(int[] x, IntComparator comp)
/*      */   {
/*  444 */     quickSort(x, 0, x.length, comp);
/*      */   }
/*      */ 
/*      */   private static int med3(int[] x, int a, int b, int c) {
/*  448 */     int ab = x[a] == x[b] ? 0 : x[a] < x[b] ? -1 : 1;
/*  449 */     int ac = x[a] == x[c] ? 0 : x[a] < x[c] ? -1 : 1;
/*  450 */     int bc = x[b] == x[c] ? 0 : x[b] < x[c] ? -1 : 1;
/*  451 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*      */   }
/*      */ 
/*      */   public static void quickSort(int[] x, int from, int to)
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
/*  487 */     int v = x[m];
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
/*      */   public static void quickSort(int[] x)
/*      */   {
/*  523 */     quickSort(x, 0, x.length);
/*      */   }
/*      */ 
/*      */   public static void mergeSort(int[] a, int from, int to, int[] supp)
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
/*      */   public static void mergeSort(int[] a, int from, int to)
/*      */   {
/*  569 */     mergeSort(a, from, to, (int[])a.clone());
/*      */   }
/*      */ 
/*      */   public static void mergeSort(int[] a)
/*      */   {
/*  579 */     mergeSort(a, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void mergeSort(int[] a, int from, int to, IntComparator comp, int[] supp)
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
/*      */   public static void mergeSort(int[] a, int from, int to, IntComparator comp)
/*      */   {
/*  629 */     mergeSort(a, from, to, comp, (int[])a.clone());
/*      */   }
/*      */ 
/*      */   public static void mergeSort(int[] a, IntComparator comp)
/*      */   {
/*  641 */     mergeSort(a, 0, a.length, comp);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(int[] a, int from, int to, int key)
/*      */   {
/*  666 */     to--;
/*  667 */     while (from <= to) {
/*  668 */       int mid = from + to >>> 1;
/*  669 */       int midVal = a[mid];
/*  670 */       if (midVal < key) from = mid + 1;
/*  671 */       else if (midVal > key) to = mid - 1; else
/*  672 */         return mid;
/*      */     }
/*  674 */     return -(from + 1);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(int[] a, int key)
/*      */   {
/*  695 */     return binarySearch(a, 0, a.length, key);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(int[] a, int from, int to, int key, IntComparator c)
/*      */   {
/*  720 */     to--;
/*  721 */     while (from <= to) {
/*  722 */       int mid = from + to >>> 1;
/*  723 */       int midVal = a[mid];
/*  724 */       int cmp = c.compare(midVal, key);
/*  725 */       if (cmp < 0) from = mid + 1;
/*  726 */       else if (cmp > 0) to = mid - 1; else
/*  727 */         return mid;
/*      */     }
/*  729 */     return -(from + 1);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(int[] a, int key, IntComparator c)
/*      */   {
/*  751 */     return binarySearch(a, 0, a.length, key, c);
/*      */   }
/*      */ 
/*      */   public static void radixSort(int[] a)
/*      */   {
/*  777 */     radixSort(a, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(int[] a, int from, int to)
/*      */   {
/*  798 */     int maxLevel = 3;
/*  799 */     int stackSize = 766;
/*  800 */     int[] offsetStack = new int[766];
/*  801 */     int offsetPos = 0;
/*  802 */     int[] lengthStack = new int[766];
/*  803 */     int lengthPos = 0;
/*  804 */     int[] levelStack = new int[766];
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
/*  816 */       int signMask = level % 4 == 0 ? 128 : 0;
/*  817 */       if (length < 50) {
/*  818 */         selectionSort(a, first, first + length);
/*      */       }
/*      */       else {
/*  821 */         int shift = (3 - level % 4) * 8;
/*      */ 
/*  823 */         for (int i = length; i-- != 0; digit[i] = ((byte)(a[(first + i)] >>> shift & 0xFF ^ signMask)));
/*  824 */         for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1);
/*  826 */         int lastUsed = -1;
/*  827 */         int i = 0; for (int p = 0; i < 256; i++) {
/*  828 */           if (count[i] != 0) {
/*  829 */             lastUsed = i;
/*  830 */             if ((level < 3) && (count[i] > 1))
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
/*  844 */           int t = a[(i + first)];
/*  845 */           c = digit[i] & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             int d;
/*  846 */             if ((d = pos[c] -= 1) <= i) break;
/*  847 */             int z = t;
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
/*      */   private static void insertionSortIndirect(int[] perm, int[] a, int from, int to)
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
/*      */   public static void radixSortIndirect(int[] perm, int[] a, boolean stable)
/*      */   {
/*  897 */     radixSortIndirect(perm, a, 0, perm.length, stable); } 
/*      */   public static void radixSortIndirect(int[] perm, int[] a, int from, int to, boolean stable) { // Byte code:
/*      */     //   0: iconst_3
/*      */     //   1: istore 5
/*      */     //   3: sipush 766
/*      */     //   6: istore 6
/*      */     //   8: sipush 766
/*      */     //   11: newarray int
/*      */     //   13: astore 7
/*      */     //   15: iconst_0
/*      */     //   16: istore 8
/*      */     //   18: sipush 766
/*      */     //   21: newarray int
/*      */     //   23: astore 9
/*      */     //   25: iconst_0
/*      */     //   26: istore 10
/*      */     //   28: sipush 766
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
/*      */     //   149: iconst_4
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
/*      */     //   179: invokestatic 40	it/unimi/dsi/fastutil/ints/IntArrays:insertionSortIndirect	([I[III)V
/*      */     //   182: goto -70 -> 112
/*      */     //   185: iconst_3
/*      */     //   186: iload 19
/*      */     //   188: iconst_4
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
/*      */     //   220: iaload
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
/*      */     //   298: iconst_3
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
/*  995 */   private static void selectionSort(int[] a, int[] b, int from, int to) { for (int i = from; i < to - 1; i++) {
/*  996 */       int m = i;
/*  997 */       for (int j = i + 1; j < to; j++)
/*  998 */         if ((a[j] < a[m]) || ((a[j] == a[m]) && (b[j] < b[m]))) m = j;
/*  999 */       if (m != i) {
/* 1000 */         int t = a[i];
/* 1001 */         a[i] = a[m];
/* 1002 */         a[m] = t;
/* 1003 */         t = b[i];
/* 1004 */         b[i] = b[m];
/* 1005 */         b[m] = t;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSort(int[] a, int[] b)
/*      */   {
/* 1029 */     radixSort(a, b, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(int[] a, int[] b, int from, int to)
/*      */   {
/* 1054 */     int layers = 2;
/* 1055 */     if (a.length != b.length) throw new IllegalArgumentException("Array size mismatch.");
/* 1056 */     int maxLevel = 7;
/* 1057 */     int stackSize = 1786;
/* 1058 */     int[] offsetStack = new int[1786];
/* 1059 */     int offsetPos = 0;
/* 1060 */     int[] lengthStack = new int[1786];
/* 1061 */     int lengthPos = 0;
/* 1062 */     int[] levelStack = new int[1786];
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
/* 1074 */       int signMask = level % 4 == 0 ? 128 : 0;
/* 1075 */       if (length < 50) {
/* 1076 */         selectionSort(a, b, first, first + length);
/*      */       }
/*      */       else {
/* 1079 */         int[] k = level < 4 ? a : b;
/* 1080 */         int shift = (3 - level % 4) * 8;
/*      */ 
/* 1082 */         for (int i = length; i-- != 0; digit[i] = ((byte)(k[(first + i)] >>> shift & 0xFF ^ signMask)));
/* 1083 */         for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1);
/* 1085 */         int lastUsed = -1;
/* 1086 */         int i = 0; for (int p = 0; i < 256; i++) {
/* 1087 */           if (count[i] != 0) {
/* 1088 */             lastUsed = i;
/* 1089 */             if ((level < 7) && (count[i] > 1)) {
/* 1090 */               offsetStack[(offsetPos++)] = (p + first);
/* 1091 */               lengthStack[(lengthPos++)] = count[i];
/* 1092 */               levelStack[(levelPos++)] = (level + 1);
/*      */             }
/*      */           }
/*      */           int tmp371_370 = (p + count[i]); p = tmp371_370; pos[i] = tmp371_370;
/*      */         }
/*      */ 
/* 1098 */         int end = length - count[lastUsed];
/* 1099 */         count[lastUsed] = 0;
/*      */ 
/* 1101 */         int i = 0; for (int c = -1; i < end; count[c] = 0) {
/* 1102 */           int t = a[(i + first)];
/* 1103 */           int u = b[(i + first)];
/* 1104 */           c = digit[i] & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             int d;
/* 1105 */             if ((d = pos[c] -= 1) <= i) break;
/* 1106 */             int z = t;
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
/*      */   private static void insertionSortIndirect(int[] perm, int[] a, int[] b, int from, int to)
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
/*      */   public static void radixSortIndirect(int[] perm, int[] a, int[] b, boolean stable)
/*      */   {
/* 1161 */     radixSortIndirect(perm, a, b, 0, perm.length, stable); } 
/*      */   public static void radixSortIndirect(int[] perm, int[] a, int[] b, int from, int to, boolean stable) { // Byte code:
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
/*      */     //   20: bipush 7
/*      */     //   22: istore 7
/*      */     //   24: sipush 1786
/*      */     //   27: istore 8
/*      */     //   29: sipush 1786
/*      */     //   32: newarray int
/*      */     //   34: astore 9
/*      */     //   36: iconst_0
/*      */     //   37: istore 10
/*      */     //   39: sipush 1786
/*      */     //   42: newarray int
/*      */     //   44: astore 11
/*      */     //   46: iconst_0
/*      */     //   47: istore 12
/*      */     //   49: sipush 1786
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
/*      */     //   137: ifle +510 -> 647
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
/*      */     //   172: iconst_4
/*      */     //   173: irem
/*      */     //   174: ifne +9 -> 183
/*      */     //   177: sipush 128
/*      */     //   180: goto +4 -> 184
/*      */     //   183: iconst_0
/*      */     //   184: istore 22
/*      */     //   186: iload 20
/*      */     //   188: bipush 50
/*      */     //   190: if_icmpge +19 -> 209
/*      */     //   193: aload_0
/*      */     //   194: aload_1
/*      */     //   195: aload_2
/*      */     //   196: iload 19
/*      */     //   198: iload 19
/*      */     //   200: iload 20
/*      */     //   202: iadd
/*      */     //   203: invokestatic 48	it/unimi/dsi/fastutil/ints/IntArrays:insertionSortIndirect	([I[I[III)V
/*      */     //   206: goto -71 -> 135
/*      */     //   209: iload 21
/*      */     //   211: iconst_4
/*      */     //   212: if_icmpge +7 -> 219
/*      */     //   215: aload_1
/*      */     //   216: goto +4 -> 220
/*      */     //   219: aload_2
/*      */     //   220: astore 23
/*      */     //   222: iconst_3
/*      */     //   223: iload 21
/*      */     //   225: iconst_4
/*      */     //   226: irem
/*      */     //   227: isub
/*      */     //   228: bipush 8
/*      */     //   230: imul
/*      */     //   231: istore 24
/*      */     //   233: iload 20
/*      */     //   235: istore 25
/*      */     //   237: iload 25
/*      */     //   239: iinc 25 255
/*      */     //   242: ifeq +32 -> 274
/*      */     //   245: aload 18
/*      */     //   247: iload 25
/*      */     //   249: aload 23
/*      */     //   251: aload_0
/*      */     //   252: iload 19
/*      */     //   254: iload 25
/*      */     //   256: iadd
/*      */     //   257: iaload
/*      */     //   258: iaload
/*      */     //   259: iload 24
/*      */     //   261: iushr
/*      */     //   262: sipush 255
/*      */     //   265: iand
/*      */     //   266: iload 22
/*      */     //   268: ixor
/*      */     //   269: i2b
/*      */     //   270: bastore
/*      */     //   271: goto -34 -> 237
/*      */     //   274: iload 20
/*      */     //   276: istore 25
/*      */     //   278: iload 25
/*      */     //   280: iinc 25 255
/*      */     //   283: ifeq +22 -> 305
/*      */     //   286: aload 15
/*      */     //   288: aload 18
/*      */     //   290: iload 25
/*      */     //   292: baload
/*      */     //   293: sipush 255
/*      */     //   296: iand
/*      */     //   297: dup2
/*      */     //   298: iaload
/*      */     //   299: iconst_1
/*      */     //   300: iadd
/*      */     //   301: iastore
/*      */     //   302: goto -24 -> 278
/*      */     //   305: iconst_m1
/*      */     //   306: istore 25
/*      */     //   308: iconst_0
/*      */     //   309: istore 26
/*      */     //   311: iconst_0
/*      */     //   312: istore 27
/*      */     //   314: iload 26
/*      */     //   316: sipush 256
/*      */     //   319: if_icmpge +115 -> 434
/*      */     //   322: aload 15
/*      */     //   324: iload 26
/*      */     //   326: iaload
/*      */     //   327: ifeq +61 -> 388
/*      */     //   330: iload 26
/*      */     //   332: istore 25
/*      */     //   334: iload 21
/*      */     //   336: bipush 7
/*      */     //   338: if_icmpge +50 -> 388
/*      */     //   341: aload 15
/*      */     //   343: iload 26
/*      */     //   345: iaload
/*      */     //   346: iconst_1
/*      */     //   347: if_icmple +41 -> 388
/*      */     //   350: aload 9
/*      */     //   352: iload 10
/*      */     //   354: iinc 10 1
/*      */     //   357: iload 27
/*      */     //   359: iload 19
/*      */     //   361: iadd
/*      */     //   362: iastore
/*      */     //   363: aload 11
/*      */     //   365: iload 12
/*      */     //   367: iinc 12 1
/*      */     //   370: aload 15
/*      */     //   372: iload 26
/*      */     //   374: iaload
/*      */     //   375: iastore
/*      */     //   376: aload 13
/*      */     //   378: iload 14
/*      */     //   380: iinc 14 1
/*      */     //   383: iload 21
/*      */     //   385: iconst_1
/*      */     //   386: iadd
/*      */     //   387: iastore
/*      */     //   388: iload 5
/*      */     //   390: ifeq +22 -> 412
/*      */     //   393: aload 15
/*      */     //   395: iload 26
/*      */     //   397: iload 27
/*      */     //   399: aload 15
/*      */     //   401: iload 26
/*      */     //   403: iaload
/*      */     //   404: iadd
/*      */     //   405: dup
/*      */     //   406: istore 27
/*      */     //   408: iastore
/*      */     //   409: goto +19 -> 428
/*      */     //   412: aload 16
/*      */     //   414: iload 26
/*      */     //   416: iload 27
/*      */     //   418: aload 15
/*      */     //   420: iload 26
/*      */     //   422: iaload
/*      */     //   423: iadd
/*      */     //   424: dup
/*      */     //   425: istore 27
/*      */     //   427: iastore
/*      */     //   428: iinc 26 1
/*      */     //   431: goto -117 -> 314
/*      */     //   434: iload 5
/*      */     //   436: ifeq +65 -> 501
/*      */     //   439: iload 20
/*      */     //   441: istore 26
/*      */     //   443: iload 26
/*      */     //   445: iinc 26 255
/*      */     //   448: ifeq +33 -> 481
/*      */     //   451: aload 17
/*      */     //   453: aload 15
/*      */     //   455: aload 18
/*      */     //   457: iload 26
/*      */     //   459: baload
/*      */     //   460: sipush 255
/*      */     //   463: iand
/*      */     //   464: dup2
/*      */     //   465: iaload
/*      */     //   466: iconst_1
/*      */     //   467: isub
/*      */     //   468: dup_x2
/*      */     //   469: iastore
/*      */     //   470: aload_0
/*      */     //   471: iload 19
/*      */     //   473: iload 26
/*      */     //   475: iadd
/*      */     //   476: iaload
/*      */     //   477: iastore
/*      */     //   478: goto -35 -> 443
/*      */     //   481: aload 17
/*      */     //   483: iconst_0
/*      */     //   484: aload_0
/*      */     //   485: iload 19
/*      */     //   487: iload 20
/*      */     //   489: invokestatic 2	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
/*      */     //   492: aload 15
/*      */     //   494: iconst_0
/*      */     //   495: invokestatic 41	it/unimi/dsi/fastutil/ints/IntArrays:fill	([II)V
/*      */     //   498: goto +146 -> 644
/*      */     //   501: iload 20
/*      */     //   503: aload 15
/*      */     //   505: iload 25
/*      */     //   507: iaload
/*      */     //   508: isub
/*      */     //   509: istore 26
/*      */     //   511: aload 15
/*      */     //   513: iload 25
/*      */     //   515: iconst_0
/*      */     //   516: iastore
/*      */     //   517: iconst_0
/*      */     //   518: istore 27
/*      */     //   520: iconst_m1
/*      */     //   521: istore 28
/*      */     //   523: iload 27
/*      */     //   525: iload 26
/*      */     //   527: if_icmpge +117 -> 644
/*      */     //   530: aload_0
/*      */     //   531: iload 27
/*      */     //   533: iload 19
/*      */     //   535: iadd
/*      */     //   536: iaload
/*      */     //   537: istore 30
/*      */     //   539: aload 18
/*      */     //   541: iload 27
/*      */     //   543: baload
/*      */     //   544: sipush 255
/*      */     //   547: iand
/*      */     //   548: istore 28
/*      */     //   550: aload 16
/*      */     //   552: iload 28
/*      */     //   554: dup2
/*      */     //   555: iaload
/*      */     //   556: iconst_1
/*      */     //   557: isub
/*      */     //   558: dup_x2
/*      */     //   559: iastore
/*      */     //   560: dup
/*      */     //   561: istore 29
/*      */     //   563: iload 27
/*      */     //   565: if_icmple +51 -> 616
/*      */     //   568: iload 30
/*      */     //   570: istore 31
/*      */     //   572: iload 28
/*      */     //   574: istore 32
/*      */     //   576: aload_0
/*      */     //   577: iload 29
/*      */     //   579: iload 19
/*      */     //   581: iadd
/*      */     //   582: iaload
/*      */     //   583: istore 30
/*      */     //   585: aload 18
/*      */     //   587: iload 29
/*      */     //   589: baload
/*      */     //   590: sipush 255
/*      */     //   593: iand
/*      */     //   594: istore 28
/*      */     //   596: aload_0
/*      */     //   597: iload 29
/*      */     //   599: iload 19
/*      */     //   601: iadd
/*      */     //   602: iload 31
/*      */     //   604: iastore
/*      */     //   605: aload 18
/*      */     //   607: iload 29
/*      */     //   609: iload 32
/*      */     //   611: i2b
/*      */     //   612: bastore
/*      */     //   613: goto -63 -> 550
/*      */     //   616: aload_0
/*      */     //   617: iload 27
/*      */     //   619: iload 19
/*      */     //   621: iadd
/*      */     //   622: iload 30
/*      */     //   624: iastore
/*      */     //   625: iload 27
/*      */     //   627: aload 15
/*      */     //   629: iload 28
/*      */     //   631: iaload
/*      */     //   632: iadd
/*      */     //   633: istore 27
/*      */     //   635: aload 15
/*      */     //   637: iload 28
/*      */     //   639: iconst_0
/*      */     //   640: iastore
/*      */     //   641: goto -118 -> 523
/*      */     //   644: goto -509 -> 135
/*      */     //   647: return } 
/* 1263 */   private static void selectionSort(int[][] a, int from, int to, int level) { int layers = a.length;
/* 1264 */     int firstLayer = level / 4;
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
/* 1278 */           int u = a[p][i];
/* 1279 */           a[p][i] = a[p][m];
/* 1280 */           a[p][m] = u;
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSort(int[][] a)
/*      */   {
/* 1305 */     radixSort(a, 0, a[0].length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(int[][] a, int from, int to)
/*      */   {
/* 1329 */     int layers = a.length;
/* 1330 */     int maxLevel = 4 * layers - 1;
/* 1331 */     int p = layers; for (int l = a[0].length; p-- != 0; ) if (a[p].length != l) throw new IllegalArgumentException("The array of index " + p + " has not the same length of the array of index 0.");
/* 1332 */     int stackSize = 255 * (layers * 4 - 1) + 1;
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
/* 1345 */     int[] t = new int[layers];
/* 1346 */     while (offsetPos > 0) {
/* 1347 */       int first = offsetStack[(--offsetPos)];
/* 1348 */       int length = lengthStack[(--lengthPos)];
/* 1349 */       int level = levelStack[(--levelPos)];
/* 1350 */       int signMask = level % 4 == 0 ? 128 : 0;
/* 1351 */       if (length < 50) {
/* 1352 */         selectionSort(a, first, first + length, level);
/*      */       }
/*      */       else {
/* 1355 */         int[] k = a[(level / 4)];
/* 1356 */         int shift = (3 - level % 4) * 8;
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
/* 1382 */               int u = t[p];
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
/*      */   public static int[] shuffle(int[] a, int from, int to, Random random)
/*      */   {
/* 1403 */     for (int i = to - from; i-- != 0; ) {
/* 1404 */       int p = random.nextInt(i + 1);
/* 1405 */       int t = a[(from + i)];
/* 1406 */       a[(from + i)] = a[(from + p)];
/* 1407 */       a[(from + p)] = t;
/*      */     }
/* 1409 */     return a;
/*      */   }
/*      */ 
/*      */   public static int[] shuffle(int[] a, Random random)
/*      */   {
/* 1418 */     for (int i = a.length; i-- != 0; ) {
/* 1419 */       int p = random.nextInt(i + 1);
/* 1420 */       int t = a[i];
/* 1421 */       a[i] = a[p];
/* 1422 */       a[p] = t;
/*      */     }
/* 1424 */     return a;
/*      */   }
/*      */ 
/*      */   public static int[] reverse(int[] a)
/*      */   {
/* 1432 */     int length = a.length;
/* 1433 */     for (int i = length / 2; i-- != 0; ) {
/* 1434 */       int t = a[(length - i - 1)];
/* 1435 */       a[(length - i - 1)] = a[i];
/* 1436 */       a[i] = t;
/*      */     }
/* 1438 */     return a;
/*      */   }
/*      */   private static final class ArrayHashStrategy implements Hash.Strategy<int[]>, Serializable {
/*      */     public static final long serialVersionUID = -7046029254386353129L;
/*      */ 
/*      */     public int hashCode(int[] o) {
/* 1444 */       return java.util.Arrays.hashCode(o);
/*      */     }
/*      */     public boolean equals(int[] a, int[] b) {
/* 1447 */       return IntArrays.equals(a, b);
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntArrays
 * JD-Core Version:    0.6.2
 */