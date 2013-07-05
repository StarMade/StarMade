/*      */ package it.unimi.dsi.fastutil.doubles;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.Hash.Strategy;
/*      */ import it.unimi.dsi.fastutil.ints.IntArrays;
/*      */ import java.io.Serializable;
/*      */ import java.util.Random;
/*      */ 
/*      */ public class DoubleArrays
/*      */ {
/*      */   public static final long ONEOVERPHI = 106039L;
/*   87 */   public static final double[] EMPTY_ARRAY = new double[0];
/*      */   private static final int SMALL = 7;
/*      */   private static final int MEDIUM = 50;
/*      */   private static final int DIGIT_BITS = 8;
/*      */   private static final int DIGIT_MASK = 255;
/*      */   private static final int DIGITS_PER_ELEMENT = 8;
/* 1460 */   public static final Hash.Strategy<double[]> HASH_STRATEGY = new ArrayHashStrategy(null);
/*      */ 
/*      */   public static double[] ensureCapacity(double[] array, int length)
/*      */   {
/*  100 */     if (length > array.length) {
/*  101 */       double[] t = new double[length];
/*      */ 
/*  103 */       System.arraycopy(array, 0, t, 0, array.length);
/*  104 */       return t;
/*      */     }
/*  106 */     return array;
/*      */   }
/*      */ 
/*      */   public static double[] ensureCapacity(double[] array, int length, int preserve)
/*      */   {
/*  118 */     if (length > array.length) {
/*  119 */       double[] t = new double[length];
/*      */ 
/*  121 */       System.arraycopy(array, 0, t, 0, preserve);
/*  122 */       return t;
/*      */     }
/*  124 */     return array;
/*      */   }
/*      */ 
/*      */   public static double[] grow(double[] array, int length)
/*      */   {
/*  142 */     if (length > array.length) {
/*  143 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  144 */       double[] t = new double[newLength];
/*      */ 
/*  146 */       System.arraycopy(array, 0, t, 0, array.length);
/*  147 */       return t;
/*      */     }
/*  149 */     return array;
/*      */   }
/*      */ 
/*      */   public static double[] grow(double[] array, int length, int preserve)
/*      */   {
/*  168 */     if (length > array.length) {
/*  169 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  170 */       double[] t = new double[newLength];
/*      */ 
/*  172 */       System.arraycopy(array, 0, t, 0, preserve);
/*  173 */       return t;
/*      */     }
/*  175 */     return array;
/*      */   }
/*      */ 
/*      */   public static double[] trim(double[] array, int length)
/*      */   {
/*  188 */     if (length >= array.length) return array;
/*  189 */     double[] t = length == 0 ? EMPTY_ARRAY : new double[length];
/*      */ 
/*  191 */     System.arraycopy(array, 0, t, 0, length);
/*  192 */     return t;
/*      */   }
/*      */ 
/*      */   public static double[] setLength(double[] array, int length)
/*      */   {
/*  208 */     if (length == array.length) return array;
/*  209 */     if (length < array.length) return trim(array, length);
/*  210 */     return ensureCapacity(array, length);
/*      */   }
/*      */ 
/*      */   public static double[] copy(double[] array, int offset, int length)
/*      */   {
/*  220 */     ensureOffsetLength(array, offset, length);
/*  221 */     double[] a = length == 0 ? EMPTY_ARRAY : new double[length];
/*      */ 
/*  223 */     System.arraycopy(array, offset, a, 0, length);
/*  224 */     return a;
/*      */   }
/*      */ 
/*      */   public static double[] copy(double[] array)
/*      */   {
/*  232 */     return (double[])array.clone();
/*      */   }
/*      */ 
/*      */   public static void fill(double[] array, double value)
/*      */   {
/*  243 */     int i = array.length;
/*  244 */     while (i-- != 0) array[i] = value;
/*      */   }
/*      */ 
/*      */   public static void fill(double[] array, int from, int to, double value)
/*      */   {
/*  258 */     ensureFromTo(array, from, to);
/*  259 */     for (from != 0; to-- != 0; array[to] = value);
/*  260 */     for (int i = from; i < to; i++) array[i] = value;
/*      */   }
/*      */ 
/*      */   public static boolean equals(double[] a1, double[] a2)
/*      */   {
/*  272 */     int i = a1.length;
/*  273 */     if (i != a2.length) return false;
/*  274 */     while (i-- != 0) if (a1[i] != a2[i]) return false;
/*  275 */     return true;
/*      */   }
/*      */ 
/*      */   public static void ensureFromTo(double[] a, int from, int to)
/*      */   {
/*  288 */     it.unimi.dsi.fastutil.Arrays.ensureFromTo(a.length, from, to);
/*      */   }
/*      */ 
/*      */   public static void ensureOffsetLength(double[] a, int offset, int length)
/*      */   {
/*  301 */     it.unimi.dsi.fastutil.Arrays.ensureOffsetLength(a.length, offset, length);
/*      */   }
/*      */ 
/*      */   private static void swap(double[] x, int a, int b)
/*      */   {
/*  306 */     double t = x[a];
/*  307 */     x[a] = x[b];
/*  308 */     x[b] = t;
/*      */   }
/*      */   private static void vecSwap(double[] x, int a, int b, int n) {
/*  311 */     for (int i = 0; i < n; b++) { swap(x, a, b); i++; a++; } 
/*      */   }
/*      */ 
/*  314 */   private static int med3(double[] x, int a, int b, int c, DoubleComparator comp) { int ab = comp.compare(x[a], x[b]);
/*  315 */     int ac = comp.compare(x[a], x[c]);
/*  316 */     int bc = comp.compare(x[b], x[c]);
/*  317 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*      */   }
/*      */ 
/*      */   private static void selectionSort(double[] a, int from, int to, DoubleComparator comp)
/*      */   {
/*  322 */     for (int i = from; i < to - 1; i++) {
/*  323 */       int m = i;
/*  324 */       for (int j = i + 1; j < to; j++) if (comp.compare(a[j], a[m]) < 0) m = j;
/*  325 */       if (m != i) {
/*  326 */         double u = a[i];
/*  327 */         a[i] = a[m];
/*  328 */         a[m] = u;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*  333 */   private static void insertionSort(double[] a, int from, int to, DoubleComparator comp) { int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/*  334 */       double t = a[i];
/*  335 */       int j = i;
/*  336 */       for (double u = a[(j - 1)]; comp.compare(t, u) < 0; u = a[(--j - 1)]) {
/*  337 */         a[j] = u;
/*  338 */         if (from == j - 1) {
/*  339 */           j--;
/*  340 */           break;
/*      */         }
/*      */       }
/*  343 */       a[j] = t; }
/*      */   }
/*      */ 
/*      */   private static void selectionSort(double[] a, int from, int to)
/*      */   {
/*  348 */     for (int i = from; i < to - 1; i++) {
/*  349 */       int m = i;
/*  350 */       for (int j = i + 1; j < to; j++) if (a[j] < a[m]) m = j;
/*  351 */       if (m != i) {
/*  352 */         double u = a[i];
/*  353 */         a[i] = a[m];
/*  354 */         a[m] = u;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void insertionSort(double[] a, int from, int to) {
/*  360 */     int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/*  361 */       double t = a[i];
/*  362 */       int j = i;
/*  363 */       for (double u = a[(j - 1)]; t < u; u = a[(--j - 1)]) {
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
/*      */   public static void quickSort(double[] x, int from, int to, DoubleComparator comp)
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
/*  406 */     double v = x[m];
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
/*      */   public static void quickSort(double[] x, DoubleComparator comp)
/*      */   {
/*  444 */     quickSort(x, 0, x.length, comp);
/*      */   }
/*      */ 
/*      */   private static int med3(double[] x, int a, int b, int c) {
/*  448 */     int ab = x[a] == x[b] ? 0 : x[a] < x[b] ? -1 : 1;
/*  449 */     int ac = x[a] == x[c] ? 0 : x[a] < x[c] ? -1 : 1;
/*  450 */     int bc = x[b] == x[c] ? 0 : x[b] < x[c] ? -1 : 1;
/*  451 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*      */   }
/*      */ 
/*      */   public static void quickSort(double[] x, int from, int to)
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
/*  487 */     double v = x[m];
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
/*      */   public static void quickSort(double[] x)
/*      */   {
/*  523 */     quickSort(x, 0, x.length);
/*      */   }
/*      */ 
/*      */   public static void mergeSort(double[] a, int from, int to, double[] supp)
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
/*      */   public static void mergeSort(double[] a, int from, int to)
/*      */   {
/*  569 */     mergeSort(a, from, to, (double[])a.clone());
/*      */   }
/*      */ 
/*      */   public static void mergeSort(double[] a)
/*      */   {
/*  579 */     mergeSort(a, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void mergeSort(double[] a, int from, int to, DoubleComparator comp, double[] supp)
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
/*      */   public static void mergeSort(double[] a, int from, int to, DoubleComparator comp)
/*      */   {
/*  629 */     mergeSort(a, from, to, comp, (double[])a.clone());
/*      */   }
/*      */ 
/*      */   public static void mergeSort(double[] a, DoubleComparator comp)
/*      */   {
/*  641 */     mergeSort(a, 0, a.length, comp);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(double[] a, int from, int to, double key)
/*      */   {
/*  666 */     to--;
/*  667 */     while (from <= to) {
/*  668 */       int mid = from + to >>> 1;
/*  669 */       double midVal = a[mid];
/*  670 */       if (midVal < key) from = mid + 1;
/*  671 */       else if (midVal > key) to = mid - 1; else
/*  672 */         return mid;
/*      */     }
/*  674 */     return -(from + 1);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(double[] a, double key)
/*      */   {
/*  695 */     return binarySearch(a, 0, a.length, key);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(double[] a, int from, int to, double key, DoubleComparator c)
/*      */   {
/*  720 */     to--;
/*  721 */     while (from <= to) {
/*  722 */       int mid = from + to >>> 1;
/*  723 */       double midVal = a[mid];
/*  724 */       int cmp = c.compare(midVal, key);
/*  725 */       if (cmp < 0) from = mid + 1;
/*  726 */       else if (cmp > 0) to = mid - 1; else
/*  727 */         return mid;
/*      */     }
/*  729 */     return -(from + 1);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(double[] a, double key, DoubleComparator c)
/*      */   {
/*  751 */     return binarySearch(a, 0, a.length, key, c);
/*      */   }
/*      */ 
/*      */   private static final long fixDouble(double d)
/*      */   {
/*  761 */     long l = Double.doubleToRawLongBits(d);
/*  762 */     return l >= 0L ? l : l ^ 0xFFFFFFFF;
/*      */   }
/*      */ 
/*      */   public static void radixSort(double[] a)
/*      */   {
/*  781 */     radixSort(a, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(double[] a, int from, int to)
/*      */   {
/*  802 */     int maxLevel = 7;
/*  803 */     int stackSize = 1786;
/*  804 */     int[] offsetStack = new int[1786];
/*  805 */     int offsetPos = 0;
/*  806 */     int[] lengthStack = new int[1786];
/*  807 */     int lengthPos = 0;
/*  808 */     int[] levelStack = new int[1786];
/*  809 */     int levelPos = 0;
/*  810 */     offsetStack[(offsetPos++)] = from;
/*  811 */     lengthStack[(lengthPos++)] = (to - from);
/*  812 */     levelStack[(levelPos++)] = 0;
/*  813 */     int[] count = new int[256];
/*  814 */     int[] pos = new int[256];
/*  815 */     byte[] digit = new byte[to - from];
/*  816 */     while (offsetPos > 0) {
/*  817 */       int first = offsetStack[(--offsetPos)];
/*  818 */       int length = lengthStack[(--lengthPos)];
/*  819 */       int level = levelStack[(--levelPos)];
/*  820 */       int signMask = level % 8 == 0 ? 128 : 0;
/*  821 */       if (length < 50) {
/*  822 */         selectionSort(a, first, first + length);
/*      */       }
/*      */       else {
/*  825 */         int shift = (7 - level % 8) * 8;
/*      */ 
/*  827 */         for (int i = length; i-- != 0; digit[i] = ((byte)(int)(fixDouble(a[(first + i)]) >>> shift & 0xFF ^ signMask)));
/*  828 */         for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1);
/*  830 */         int lastUsed = -1;
/*  831 */         int i = 0; for (int p = 0; i < 256; i++) {
/*  832 */           if (count[i] != 0) {
/*  833 */             lastUsed = i;
/*  834 */             if ((level < 7) && (count[i] > 1))
/*      */             {
/*  836 */               offsetStack[(offsetPos++)] = (p + first);
/*  837 */               lengthStack[(lengthPos++)] = count[i];
/*  838 */               levelStack[(levelPos++)] = (level + 1);
/*      */             }
/*      */           }
/*      */           int tmp343_342 = (p + count[i]); p = tmp343_342; pos[i] = tmp343_342;
/*      */         }
/*      */ 
/*  844 */         int end = length - count[lastUsed];
/*  845 */         count[lastUsed] = 0;
/*      */ 
/*  847 */         int i = 0; for (int c = -1; i < end; count[c] = 0) {
/*  848 */           double t = a[(i + first)];
/*  849 */           c = digit[i] & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             int d;
/*  850 */             if ((d = pos[c] -= 1) <= i) break;
/*  851 */             double z = t;
/*  852 */             int zz = c;
/*  853 */             t = a[(d + first)];
/*  854 */             c = digit[d] & 0xFF;
/*  855 */             a[(d + first)] = z;
/*  856 */             digit[d] = ((byte)zz);
/*      */           }
/*  858 */           a[(i + first)] = t;
/*      */ 
/*  847 */           i += count[c];
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void insertionSortIndirect(int[] perm, double[] a, int from, int to)
/*      */   {
/*  863 */     int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/*  864 */       int t = perm[i];
/*  865 */       int j = i;
/*  866 */       for (int u = perm[(j - 1)]; a[t] < a[u]; u = perm[(--j - 1)]) {
/*  867 */         perm[j] = u;
/*  868 */         if (from == j - 1) {
/*  869 */           j--;
/*  870 */           break;
/*      */         }
/*      */       }
/*  873 */       perm[j] = t;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSortIndirect(int[] perm, double[] a, boolean stable)
/*      */   {
/*  901 */     radixSortIndirect(perm, a, 0, perm.length, stable); } 
/*      */   public static void radixSortIndirect(int[] perm, double[] a, int from, int to, boolean stable) { // Byte code:
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
/*      */     //   115: ifle +503 -> 618
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
/*      */     //   181: invokestatic 46	it/unimi/dsi/fastutil/doubles/DoubleArrays:insertionSortIndirect	([I[DII)V
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
/*      */     //   209: ifeq +36 -> 245
/*      */     //   212: aload 16
/*      */     //   214: iload 22
/*      */     //   216: aload_1
/*      */     //   217: aload_0
/*      */     //   218: iload 17
/*      */     //   220: iload 22
/*      */     //   222: iadd
/*      */     //   223: iaload
/*      */     //   224: daload
/*      */     //   225: invokestatic 42	it/unimi/dsi/fastutil/doubles/DoubleArrays:fixDouble	(D)J
/*      */     //   228: iload 21
/*      */     //   230: lushr
/*      */     //   231: ldc2_w 43
/*      */     //   234: land
/*      */     //   235: iload 20
/*      */     //   237: i2l
/*      */     //   238: lxor
/*      */     //   239: l2i
/*      */     //   240: i2b
/*      */     //   241: bastore
/*      */     //   242: goto -38 -> 204
/*      */     //   245: iload 18
/*      */     //   247: istore 22
/*      */     //   249: iload 22
/*      */     //   251: iinc 22 255
/*      */     //   254: ifeq +22 -> 276
/*      */     //   257: aload 13
/*      */     //   259: aload 16
/*      */     //   261: iload 22
/*      */     //   263: baload
/*      */     //   264: sipush 255
/*      */     //   267: iand
/*      */     //   268: dup2
/*      */     //   269: iaload
/*      */     //   270: iconst_1
/*      */     //   271: iadd
/*      */     //   272: iastore
/*      */     //   273: goto -24 -> 249
/*      */     //   276: iconst_m1
/*      */     //   277: istore 22
/*      */     //   279: iconst_0
/*      */     //   280: istore 23
/*      */     //   282: iconst_0
/*      */     //   283: istore 24
/*      */     //   285: iload 23
/*      */     //   287: sipush 256
/*      */     //   290: if_icmpge +115 -> 405
/*      */     //   293: aload 13
/*      */     //   295: iload 23
/*      */     //   297: iaload
/*      */     //   298: ifeq +61 -> 359
/*      */     //   301: iload 23
/*      */     //   303: istore 22
/*      */     //   305: iload 19
/*      */     //   307: bipush 7
/*      */     //   309: if_icmpge +50 -> 359
/*      */     //   312: aload 13
/*      */     //   314: iload 23
/*      */     //   316: iaload
/*      */     //   317: iconst_1
/*      */     //   318: if_icmple +41 -> 359
/*      */     //   321: aload 7
/*      */     //   323: iload 8
/*      */     //   325: iinc 8 1
/*      */     //   328: iload 24
/*      */     //   330: iload 17
/*      */     //   332: iadd
/*      */     //   333: iastore
/*      */     //   334: aload 9
/*      */     //   336: iload 10
/*      */     //   338: iinc 10 1
/*      */     //   341: aload 13
/*      */     //   343: iload 23
/*      */     //   345: iaload
/*      */     //   346: iastore
/*      */     //   347: aload 11
/*      */     //   349: iload 12
/*      */     //   351: iinc 12 1
/*      */     //   354: iload 19
/*      */     //   356: iconst_1
/*      */     //   357: iadd
/*      */     //   358: iastore
/*      */     //   359: iload 4
/*      */     //   361: ifeq +22 -> 383
/*      */     //   364: aload 13
/*      */     //   366: iload 23
/*      */     //   368: iload 24
/*      */     //   370: aload 13
/*      */     //   372: iload 23
/*      */     //   374: iaload
/*      */     //   375: iadd
/*      */     //   376: dup
/*      */     //   377: istore 24
/*      */     //   379: iastore
/*      */     //   380: goto +19 -> 399
/*      */     //   383: aload 14
/*      */     //   385: iload 23
/*      */     //   387: iload 24
/*      */     //   389: aload 13
/*      */     //   391: iload 23
/*      */     //   393: iaload
/*      */     //   394: iadd
/*      */     //   395: dup
/*      */     //   396: istore 24
/*      */     //   398: iastore
/*      */     //   399: iinc 23 1
/*      */     //   402: goto -117 -> 285
/*      */     //   405: iload 4
/*      */     //   407: ifeq +65 -> 472
/*      */     //   410: iload 18
/*      */     //   412: istore 23
/*      */     //   414: iload 23
/*      */     //   416: iinc 23 255
/*      */     //   419: ifeq +33 -> 452
/*      */     //   422: aload 15
/*      */     //   424: aload 13
/*      */     //   426: aload 16
/*      */     //   428: iload 23
/*      */     //   430: baload
/*      */     //   431: sipush 255
/*      */     //   434: iand
/*      */     //   435: dup2
/*      */     //   436: iaload
/*      */     //   437: iconst_1
/*      */     //   438: isub
/*      */     //   439: dup_x2
/*      */     //   440: iastore
/*      */     //   441: aload_0
/*      */     //   442: iload 17
/*      */     //   444: iload 23
/*      */     //   446: iadd
/*      */     //   447: iaload
/*      */     //   448: iastore
/*      */     //   449: goto -35 -> 414
/*      */     //   452: aload 15
/*      */     //   454: iconst_0
/*      */     //   455: aload_0
/*      */     //   456: iload 17
/*      */     //   458: iload 18
/*      */     //   460: invokestatic 2	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
/*      */     //   463: aload 13
/*      */     //   465: iconst_0
/*      */     //   466: invokestatic 47	it/unimi/dsi/fastutil/ints/IntArrays:fill	([II)V
/*      */     //   469: goto +146 -> 615
/*      */     //   472: iload 18
/*      */     //   474: aload 13
/*      */     //   476: iload 22
/*      */     //   478: iaload
/*      */     //   479: isub
/*      */     //   480: istore 23
/*      */     //   482: aload 13
/*      */     //   484: iload 22
/*      */     //   486: iconst_0
/*      */     //   487: iastore
/*      */     //   488: iconst_0
/*      */     //   489: istore 24
/*      */     //   491: iconst_m1
/*      */     //   492: istore 25
/*      */     //   494: iload 24
/*      */     //   496: iload 23
/*      */     //   498: if_icmpge +117 -> 615
/*      */     //   501: aload_0
/*      */     //   502: iload 24
/*      */     //   504: iload 17
/*      */     //   506: iadd
/*      */     //   507: iaload
/*      */     //   508: istore 27
/*      */     //   510: aload 16
/*      */     //   512: iload 24
/*      */     //   514: baload
/*      */     //   515: sipush 255
/*      */     //   518: iand
/*      */     //   519: istore 25
/*      */     //   521: aload 14
/*      */     //   523: iload 25
/*      */     //   525: dup2
/*      */     //   526: iaload
/*      */     //   527: iconst_1
/*      */     //   528: isub
/*      */     //   529: dup_x2
/*      */     //   530: iastore
/*      */     //   531: dup
/*      */     //   532: istore 26
/*      */     //   534: iload 24
/*      */     //   536: if_icmple +51 -> 587
/*      */     //   539: iload 27
/*      */     //   541: istore 28
/*      */     //   543: iload 25
/*      */     //   545: istore 29
/*      */     //   547: aload_0
/*      */     //   548: iload 26
/*      */     //   550: iload 17
/*      */     //   552: iadd
/*      */     //   553: iaload
/*      */     //   554: istore 27
/*      */     //   556: aload 16
/*      */     //   558: iload 26
/*      */     //   560: baload
/*      */     //   561: sipush 255
/*      */     //   564: iand
/*      */     //   565: istore 25
/*      */     //   567: aload_0
/*      */     //   568: iload 26
/*      */     //   570: iload 17
/*      */     //   572: iadd
/*      */     //   573: iload 28
/*      */     //   575: iastore
/*      */     //   576: aload 16
/*      */     //   578: iload 26
/*      */     //   580: iload 29
/*      */     //   582: i2b
/*      */     //   583: bastore
/*      */     //   584: goto -63 -> 521
/*      */     //   587: aload_0
/*      */     //   588: iload 24
/*      */     //   590: iload 17
/*      */     //   592: iadd
/*      */     //   593: iload 27
/*      */     //   595: iastore
/*      */     //   596: iload 24
/*      */     //   598: aload 13
/*      */     //   600: iload 25
/*      */     //   602: iaload
/*      */     //   603: iadd
/*      */     //   604: istore 24
/*      */     //   606: aload 13
/*      */     //   608: iload 25
/*      */     //   610: iconst_0
/*      */     //   611: iastore
/*      */     //   612: goto -118 -> 494
/*      */     //   615: goto -502 -> 113
/*      */     //   618: return } 
/*  999 */   private static void selectionSort(double[] a, double[] b, int from, int to) { for (int i = from; i < to - 1; i++) {
/* 1000 */       int m = i;
/* 1001 */       for (int j = i + 1; j < to; j++)
/* 1002 */         if ((a[j] < a[m]) || ((a[j] == a[m]) && (b[j] < b[m]))) m = j;
/* 1003 */       if (m != i) {
/* 1004 */         double t = a[i];
/* 1005 */         a[i] = a[m];
/* 1006 */         a[m] = t;
/* 1007 */         t = b[i];
/* 1008 */         b[i] = b[m];
/* 1009 */         b[m] = t;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSort(double[] a, double[] b)
/*      */   {
/* 1033 */     radixSort(a, b, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(double[] a, double[] b, int from, int to)
/*      */   {
/* 1058 */     int layers = 2;
/* 1059 */     if (a.length != b.length) throw new IllegalArgumentException("Array size mismatch.");
/* 1060 */     int maxLevel = 15;
/* 1061 */     int stackSize = 3826;
/* 1062 */     int[] offsetStack = new int[3826];
/* 1063 */     int offsetPos = 0;
/* 1064 */     int[] lengthStack = new int[3826];
/* 1065 */     int lengthPos = 0;
/* 1066 */     int[] levelStack = new int[3826];
/* 1067 */     int levelPos = 0;
/* 1068 */     offsetStack[(offsetPos++)] = from;
/* 1069 */     lengthStack[(lengthPos++)] = (to - from);
/* 1070 */     levelStack[(levelPos++)] = 0;
/* 1071 */     int[] count = new int[256];
/* 1072 */     int[] pos = new int[256];
/* 1073 */     byte[] digit = new byte[to - from];
/* 1074 */     while (offsetPos > 0) {
/* 1075 */       int first = offsetStack[(--offsetPos)];
/* 1076 */       int length = lengthStack[(--lengthPos)];
/* 1077 */       int level = levelStack[(--levelPos)];
/* 1078 */       int signMask = level % 8 == 0 ? 128 : 0;
/* 1079 */       if (length < 50) {
/* 1080 */         selectionSort(a, b, first, first + length);
/*      */       }
/*      */       else {
/* 1083 */         double[] k = level < 8 ? a : b;
/* 1084 */         int shift = (7 - level % 8) * 8;
/*      */ 
/* 1086 */         for (int i = length; i-- != 0; digit[i] = ((byte)(int)(fixDouble(k[(first + i)]) >>> shift & 0xFF ^ signMask)));
/* 1087 */         for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1);
/* 1089 */         int lastUsed = -1;
/* 1090 */         int i = 0; for (int p = 0; i < 256; i++) {
/* 1091 */           if (count[i] != 0) {
/* 1092 */             lastUsed = i;
/* 1093 */             if ((level < 15) && (count[i] > 1)) {
/* 1094 */               offsetStack[(offsetPos++)] = (p + first);
/* 1095 */               lengthStack[(lengthPos++)] = count[i];
/* 1096 */               levelStack[(levelPos++)] = (level + 1);
/*      */             }
/*      */           }
/*      */           int tmp380_379 = (p + count[i]); p = tmp380_379; pos[i] = tmp380_379;
/*      */         }
/*      */ 
/* 1102 */         int end = length - count[lastUsed];
/* 1103 */         count[lastUsed] = 0;
/*      */ 
/* 1105 */         int i = 0; for (int c = -1; i < end; count[c] = 0) {
/* 1106 */           double t = a[(i + first)];
/* 1107 */           double u = b[(i + first)];
/* 1108 */           c = digit[i] & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             int d;
/* 1109 */             if ((d = pos[c] -= 1) <= i) break;
/* 1110 */             double z = t;
/* 1111 */             int zz = c;
/* 1112 */             t = a[(d + first)];
/* 1113 */             a[(d + first)] = z;
/* 1114 */             z = u;
/* 1115 */             u = b[(d + first)];
/* 1116 */             b[(d + first)] = z;
/* 1117 */             c = digit[d] & 0xFF;
/* 1118 */             digit[d] = ((byte)zz);
/*      */           }
/* 1120 */           a[(i + first)] = t;
/* 1121 */           b[(i + first)] = u;
/*      */ 
/* 1105 */           i += count[c];
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void insertionSortIndirect(int[] perm, double[] a, double[] b, int from, int to)
/*      */   {
/* 1126 */     int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/* 1127 */       int t = perm[i];
/* 1128 */       int j = i;
/* 1129 */       for (int u = perm[(j - 1)]; (a[t] < a[u]) || ((a[t] == a[u]) && (b[t] < b[u])); u = perm[(--j - 1)]) {
/* 1130 */         perm[j] = u;
/* 1131 */         if (from == j - 1) {
/* 1132 */           j--;
/* 1133 */           break;
/*      */         }
/*      */       }
/* 1136 */       perm[j] = t;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSortIndirect(int[] perm, double[] a, double[] b, boolean stable)
/*      */   {
/* 1165 */     radixSortIndirect(perm, a, b, 0, perm.length, stable); } 
/*      */   public static void radixSortIndirect(int[] perm, double[] a, double[] b, int from, int to, boolean stable) { // Byte code:
/*      */     //   0: iconst_2
/*      */     //   1: istore 6
/*      */     //   3: aload_1
/*      */     //   4: arraylength
/*      */     //   5: aload_2
/*      */     //   6: arraylength
/*      */     //   7: if_icmpeq +13 -> 20
/*      */     //   10: new 49	java/lang/IllegalArgumentException
/*      */     //   13: dup
/*      */     //   14: ldc 50
/*      */     //   16: invokespecial 51	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
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
/*      */     //   137: ifle +519 -> 656
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
/*      */     //   204: invokestatic 54	it/unimi/dsi/fastutil/doubles/DoubleArrays:insertionSortIndirect	([I[D[DII)V
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
/*      */     //   246: ifeq +37 -> 283
/*      */     //   249: aload 18
/*      */     //   251: iload 25
/*      */     //   253: aload 23
/*      */     //   255: aload_0
/*      */     //   256: iload 19
/*      */     //   258: iload 25
/*      */     //   260: iadd
/*      */     //   261: iaload
/*      */     //   262: daload
/*      */     //   263: invokestatic 42	it/unimi/dsi/fastutil/doubles/DoubleArrays:fixDouble	(D)J
/*      */     //   266: iload 24
/*      */     //   268: lushr
/*      */     //   269: ldc2_w 43
/*      */     //   272: land
/*      */     //   273: iload 22
/*      */     //   275: i2l
/*      */     //   276: lxor
/*      */     //   277: l2i
/*      */     //   278: i2b
/*      */     //   279: bastore
/*      */     //   280: goto -39 -> 241
/*      */     //   283: iload 20
/*      */     //   285: istore 25
/*      */     //   287: iload 25
/*      */     //   289: iinc 25 255
/*      */     //   292: ifeq +22 -> 314
/*      */     //   295: aload 15
/*      */     //   297: aload 18
/*      */     //   299: iload 25
/*      */     //   301: baload
/*      */     //   302: sipush 255
/*      */     //   305: iand
/*      */     //   306: dup2
/*      */     //   307: iaload
/*      */     //   308: iconst_1
/*      */     //   309: iadd
/*      */     //   310: iastore
/*      */     //   311: goto -24 -> 287
/*      */     //   314: iconst_m1
/*      */     //   315: istore 25
/*      */     //   317: iconst_0
/*      */     //   318: istore 26
/*      */     //   320: iconst_0
/*      */     //   321: istore 27
/*      */     //   323: iload 26
/*      */     //   325: sipush 256
/*      */     //   328: if_icmpge +115 -> 443
/*      */     //   331: aload 15
/*      */     //   333: iload 26
/*      */     //   335: iaload
/*      */     //   336: ifeq +61 -> 397
/*      */     //   339: iload 26
/*      */     //   341: istore 25
/*      */     //   343: iload 21
/*      */     //   345: bipush 15
/*      */     //   347: if_icmpge +50 -> 397
/*      */     //   350: aload 15
/*      */     //   352: iload 26
/*      */     //   354: iaload
/*      */     //   355: iconst_1
/*      */     //   356: if_icmple +41 -> 397
/*      */     //   359: aload 9
/*      */     //   361: iload 10
/*      */     //   363: iinc 10 1
/*      */     //   366: iload 27
/*      */     //   368: iload 19
/*      */     //   370: iadd
/*      */     //   371: iastore
/*      */     //   372: aload 11
/*      */     //   374: iload 12
/*      */     //   376: iinc 12 1
/*      */     //   379: aload 15
/*      */     //   381: iload 26
/*      */     //   383: iaload
/*      */     //   384: iastore
/*      */     //   385: aload 13
/*      */     //   387: iload 14
/*      */     //   389: iinc 14 1
/*      */     //   392: iload 21
/*      */     //   394: iconst_1
/*      */     //   395: iadd
/*      */     //   396: iastore
/*      */     //   397: iload 5
/*      */     //   399: ifeq +22 -> 421
/*      */     //   402: aload 15
/*      */     //   404: iload 26
/*      */     //   406: iload 27
/*      */     //   408: aload 15
/*      */     //   410: iload 26
/*      */     //   412: iaload
/*      */     //   413: iadd
/*      */     //   414: dup
/*      */     //   415: istore 27
/*      */     //   417: iastore
/*      */     //   418: goto +19 -> 437
/*      */     //   421: aload 16
/*      */     //   423: iload 26
/*      */     //   425: iload 27
/*      */     //   427: aload 15
/*      */     //   429: iload 26
/*      */     //   431: iaload
/*      */     //   432: iadd
/*      */     //   433: dup
/*      */     //   434: istore 27
/*      */     //   436: iastore
/*      */     //   437: iinc 26 1
/*      */     //   440: goto -117 -> 323
/*      */     //   443: iload 5
/*      */     //   445: ifeq +65 -> 510
/*      */     //   448: iload 20
/*      */     //   450: istore 26
/*      */     //   452: iload 26
/*      */     //   454: iinc 26 255
/*      */     //   457: ifeq +33 -> 490
/*      */     //   460: aload 17
/*      */     //   462: aload 15
/*      */     //   464: aload 18
/*      */     //   466: iload 26
/*      */     //   468: baload
/*      */     //   469: sipush 255
/*      */     //   472: iand
/*      */     //   473: dup2
/*      */     //   474: iaload
/*      */     //   475: iconst_1
/*      */     //   476: isub
/*      */     //   477: dup_x2
/*      */     //   478: iastore
/*      */     //   479: aload_0
/*      */     //   480: iload 19
/*      */     //   482: iload 26
/*      */     //   484: iadd
/*      */     //   485: iaload
/*      */     //   486: iastore
/*      */     //   487: goto -35 -> 452
/*      */     //   490: aload 17
/*      */     //   492: iconst_0
/*      */     //   493: aload_0
/*      */     //   494: iload 19
/*      */     //   496: iload 20
/*      */     //   498: invokestatic 2	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
/*      */     //   501: aload 15
/*      */     //   503: iconst_0
/*      */     //   504: invokestatic 47	it/unimi/dsi/fastutil/ints/IntArrays:fill	([II)V
/*      */     //   507: goto +146 -> 653
/*      */     //   510: iload 20
/*      */     //   512: aload 15
/*      */     //   514: iload 25
/*      */     //   516: iaload
/*      */     //   517: isub
/*      */     //   518: istore 26
/*      */     //   520: aload 15
/*      */     //   522: iload 25
/*      */     //   524: iconst_0
/*      */     //   525: iastore
/*      */     //   526: iconst_0
/*      */     //   527: istore 27
/*      */     //   529: iconst_m1
/*      */     //   530: istore 28
/*      */     //   532: iload 27
/*      */     //   534: iload 26
/*      */     //   536: if_icmpge +117 -> 653
/*      */     //   539: aload_0
/*      */     //   540: iload 27
/*      */     //   542: iload 19
/*      */     //   544: iadd
/*      */     //   545: iaload
/*      */     //   546: istore 30
/*      */     //   548: aload 18
/*      */     //   550: iload 27
/*      */     //   552: baload
/*      */     //   553: sipush 255
/*      */     //   556: iand
/*      */     //   557: istore 28
/*      */     //   559: aload 16
/*      */     //   561: iload 28
/*      */     //   563: dup2
/*      */     //   564: iaload
/*      */     //   565: iconst_1
/*      */     //   566: isub
/*      */     //   567: dup_x2
/*      */     //   568: iastore
/*      */     //   569: dup
/*      */     //   570: istore 29
/*      */     //   572: iload 27
/*      */     //   574: if_icmple +51 -> 625
/*      */     //   577: iload 30
/*      */     //   579: istore 31
/*      */     //   581: iload 28
/*      */     //   583: istore 32
/*      */     //   585: aload_0
/*      */     //   586: iload 29
/*      */     //   588: iload 19
/*      */     //   590: iadd
/*      */     //   591: iaload
/*      */     //   592: istore 30
/*      */     //   594: aload 18
/*      */     //   596: iload 29
/*      */     //   598: baload
/*      */     //   599: sipush 255
/*      */     //   602: iand
/*      */     //   603: istore 28
/*      */     //   605: aload_0
/*      */     //   606: iload 29
/*      */     //   608: iload 19
/*      */     //   610: iadd
/*      */     //   611: iload 31
/*      */     //   613: iastore
/*      */     //   614: aload 18
/*      */     //   616: iload 29
/*      */     //   618: iload 32
/*      */     //   620: i2b
/*      */     //   621: bastore
/*      */     //   622: goto -63 -> 559
/*      */     //   625: aload_0
/*      */     //   626: iload 27
/*      */     //   628: iload 19
/*      */     //   630: iadd
/*      */     //   631: iload 30
/*      */     //   633: iastore
/*      */     //   634: iload 27
/*      */     //   636: aload 15
/*      */     //   638: iload 28
/*      */     //   640: iaload
/*      */     //   641: iadd
/*      */     //   642: istore 27
/*      */     //   644: aload 15
/*      */     //   646: iload 28
/*      */     //   648: iconst_0
/*      */     //   649: iastore
/*      */     //   650: goto -118 -> 532
/*      */     //   653: goto -518 -> 135
/*      */     //   656: return } 
/* 1267 */   private static void selectionSort(double[][] a, int from, int to, int level) { int layers = a.length;
/* 1268 */     int firstLayer = level / 8;
/*      */     int m;
/*      */     int p;
/* 1269 */     for (int i = from; i < to - 1; i++) {
/* 1270 */       m = i;
/* 1271 */       for (int j = i + 1; j < to; j++) {
/* 1272 */         for (int p = firstLayer; p < layers; p++)
/* 1273 */           if (a[p][j] < a[p][m]) {
/* 1274 */             m = j;
/*      */           }
/*      */           else
/* 1277 */             if (a[p][j] > a[p][m])
/*      */               break;
/*      */       }
/* 1280 */       if (m != i)
/* 1281 */         for (p = layers; p-- != 0; ) {
/* 1282 */           double u = a[p][i];
/* 1283 */           a[p][i] = a[p][m];
/* 1284 */           a[p][m] = u;
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSort(double[][] a)
/*      */   {
/* 1309 */     radixSort(a, 0, a[0].length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(double[][] a, int from, int to)
/*      */   {
/* 1333 */     int layers = a.length;
/* 1334 */     int maxLevel = 8 * layers - 1;
/* 1335 */     int p = layers; for (int l = a[0].length; p-- != 0; ) if (a[p].length != l) throw new IllegalArgumentException("The array of index " + p + " has not the same length of the array of index 0.");
/* 1336 */     int stackSize = 255 * (layers * 8 - 1) + 1;
/* 1337 */     int[] offsetStack = new int[stackSize];
/* 1338 */     int offsetPos = 0;
/* 1339 */     int[] lengthStack = new int[stackSize];
/* 1340 */     int lengthPos = 0;
/* 1341 */     int[] levelStack = new int[stackSize];
/* 1342 */     int levelPos = 0;
/* 1343 */     offsetStack[(offsetPos++)] = from;
/* 1344 */     lengthStack[(lengthPos++)] = (to - from);
/* 1345 */     levelStack[(levelPos++)] = 0;
/* 1346 */     int[] count = new int[256];
/* 1347 */     int[] pos = new int[256];
/* 1348 */     byte[] digit = new byte[to - from];
/* 1349 */     double[] t = new double[layers];
/* 1350 */     while (offsetPos > 0) {
/* 1351 */       int first = offsetStack[(--offsetPos)];
/* 1352 */       int length = lengthStack[(--lengthPos)];
/* 1353 */       int level = levelStack[(--levelPos)];
/* 1354 */       int signMask = level % 8 == 0 ? 128 : 0;
/* 1355 */       if (length < 50) {
/* 1356 */         selectionSort(a, first, first + length, level);
/*      */       }
/*      */       else {
/* 1359 */         double[] k = a[(level / 8)];
/* 1360 */         int shift = (7 - level % 8) * 8;
/*      */ 
/* 1362 */         for (int i = length; i-- != 0; digit[i] = ((byte)(int)(fixDouble(k[(first + i)]) >>> shift & 0xFF ^ signMask)));
/* 1363 */         for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1);
/* 1365 */         int lastUsed = -1;
/* 1366 */         int i = 0; for (int p = 0; i < 256; i++) {
/* 1367 */           if (count[i] != 0) {
/* 1368 */             lastUsed = i;
/* 1369 */             if ((level < maxLevel) && (count[i] > 1)) {
/* 1370 */               offsetStack[(offsetPos++)] = (p + first);
/* 1371 */               lengthStack[(lengthPos++)] = count[i];
/* 1372 */               levelStack[(levelPos++)] = (level + 1);
/*      */             }
/*      */           }
/*      */           int tmp434_433 = (p + count[i]); p = tmp434_433; pos[i] = tmp434_433;
/*      */         }
/*      */ 
/* 1378 */         int end = length - count[lastUsed];
/* 1379 */         count[lastUsed] = 0;
/*      */ 
/* 1381 */         int i = 0; for (int c = -1; i < end; count[c] = 0) {
/* 1382 */           for (int p = layers; p-- != 0; t[p] = a[p][(i + first)]);
/* 1383 */           c = digit[i] & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             int d;
/* 1384 */             if ((d = pos[c] -= 1) <= i) break;
/* 1385 */             for (int p = layers; p-- != 0; ) {
/* 1386 */               double u = t[p];
/* 1387 */               t[p] = a[p][(d + first)];
/* 1388 */               a[p][(d + first)] = u;
/*      */             }
/* 1390 */             int zz = c;
/* 1391 */             c = digit[d] & 0xFF;
/* 1392 */             digit[d] = ((byte)zz);
/*      */           }
/* 1394 */           for (int p = layers; p-- != 0; a[p][(i + first)] = t[p]);
/* 1381 */           i += count[c];
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static double[] shuffle(double[] a, int from, int to, Random random)
/*      */   {
/* 1407 */     for (int i = to - from; i-- != 0; ) {
/* 1408 */       int p = random.nextInt(i + 1);
/* 1409 */       double t = a[(from + i)];
/* 1410 */       a[(from + i)] = a[(from + p)];
/* 1411 */       a[(from + p)] = t;
/*      */     }
/* 1413 */     return a;
/*      */   }
/*      */ 
/*      */   public static double[] shuffle(double[] a, Random random)
/*      */   {
/* 1422 */     for (int i = a.length; i-- != 0; ) {
/* 1423 */       int p = random.nextInt(i + 1);
/* 1424 */       double t = a[i];
/* 1425 */       a[i] = a[p];
/* 1426 */       a[p] = t;
/*      */     }
/* 1428 */     return a;
/*      */   }
/*      */ 
/*      */   public static double[] reverse(double[] a)
/*      */   {
/* 1436 */     int length = a.length;
/* 1437 */     for (int i = length / 2; i-- != 0; ) {
/* 1438 */       double t = a[(length - i - 1)];
/* 1439 */       a[(length - i - 1)] = a[i];
/* 1440 */       a[i] = t;
/*      */     }
/* 1442 */     return a;
/*      */   }
/*      */   private static final class ArrayHashStrategy implements Hash.Strategy<double[]>, Serializable {
/*      */     public static final long serialVersionUID = -7046029254386353129L;
/*      */ 
/*      */     public int hashCode(double[] o) {
/* 1448 */       return java.util.Arrays.hashCode(o);
/*      */     }
/*      */     public boolean equals(double[] a, double[] b) {
/* 1451 */       return DoubleArrays.equals(a, b);
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleArrays
 * JD-Core Version:    0.6.2
 */