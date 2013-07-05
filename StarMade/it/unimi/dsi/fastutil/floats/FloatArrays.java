/*      */ package it.unimi.dsi.fastutil.floats;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.Hash.Strategy;
/*      */ import it.unimi.dsi.fastutil.ints.IntArrays;
/*      */ import java.io.Serializable;
/*      */ import java.util.Random;
/*      */ 
/*      */ public class FloatArrays
/*      */ {
/*      */   public static final long ONEOVERPHI = 106039L;
/*   87 */   public static final float[] EMPTY_ARRAY = new float[0];
/*      */   private static final int SMALL = 7;
/*      */   private static final int MEDIUM = 50;
/*      */   private static final int DIGIT_BITS = 8;
/*      */   private static final int DIGIT_MASK = 255;
/*      */   private static final int DIGITS_PER_ELEMENT = 4;
/* 1460 */   public static final Hash.Strategy<float[]> HASH_STRATEGY = new ArrayHashStrategy(null);
/*      */ 
/*      */   public static float[] ensureCapacity(float[] array, int length)
/*      */   {
/*  100 */     if (length > array.length) {
/*  101 */       float[] t = new float[length];
/*      */ 
/*  103 */       System.arraycopy(array, 0, t, 0, array.length);
/*  104 */       return t;
/*      */     }
/*  106 */     return array;
/*      */   }
/*      */ 
/*      */   public static float[] ensureCapacity(float[] array, int length, int preserve)
/*      */   {
/*  118 */     if (length > array.length) {
/*  119 */       float[] t = new float[length];
/*      */ 
/*  121 */       System.arraycopy(array, 0, t, 0, preserve);
/*  122 */       return t;
/*      */     }
/*  124 */     return array;
/*      */   }
/*      */ 
/*      */   public static float[] grow(float[] array, int length)
/*      */   {
/*  142 */     if (length > array.length) {
/*  143 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  144 */       float[] t = new float[newLength];
/*      */ 
/*  146 */       System.arraycopy(array, 0, t, 0, array.length);
/*  147 */       return t;
/*      */     }
/*  149 */     return array;
/*      */   }
/*      */ 
/*      */   public static float[] grow(float[] array, int length, int preserve)
/*      */   {
/*  168 */     if (length > array.length) {
/*  169 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  170 */       float[] t = new float[newLength];
/*      */ 
/*  172 */       System.arraycopy(array, 0, t, 0, preserve);
/*  173 */       return t;
/*      */     }
/*  175 */     return array;
/*      */   }
/*      */ 
/*      */   public static float[] trim(float[] array, int length)
/*      */   {
/*  188 */     if (length >= array.length) return array;
/*  189 */     float[] t = length == 0 ? EMPTY_ARRAY : new float[length];
/*      */ 
/*  191 */     System.arraycopy(array, 0, t, 0, length);
/*  192 */     return t;
/*      */   }
/*      */ 
/*      */   public static float[] setLength(float[] array, int length)
/*      */   {
/*  208 */     if (length == array.length) return array;
/*  209 */     if (length < array.length) return trim(array, length);
/*  210 */     return ensureCapacity(array, length);
/*      */   }
/*      */ 
/*      */   public static float[] copy(float[] array, int offset, int length)
/*      */   {
/*  220 */     ensureOffsetLength(array, offset, length);
/*  221 */     float[] a = length == 0 ? EMPTY_ARRAY : new float[length];
/*      */ 
/*  223 */     System.arraycopy(array, offset, a, 0, length);
/*  224 */     return a;
/*      */   }
/*      */ 
/*      */   public static float[] copy(float[] array)
/*      */   {
/*  232 */     return (float[])array.clone();
/*      */   }
/*      */ 
/*      */   public static void fill(float[] array, float value)
/*      */   {
/*  243 */     int i = array.length;
/*  244 */     while (i-- != 0) array[i] = value;
/*      */   }
/*      */ 
/*      */   public static void fill(float[] array, int from, int to, float value)
/*      */   {
/*  258 */     ensureFromTo(array, from, to);
/*  259 */     for (from != 0; to-- != 0; array[to] = value);
/*  260 */     for (int i = from; i < to; i++) array[i] = value;
/*      */   }
/*      */ 
/*      */   public static boolean equals(float[] a1, float[] a2)
/*      */   {
/*  272 */     int i = a1.length;
/*  273 */     if (i != a2.length) return false;
/*  274 */     while (i-- != 0) if (a1[i] != a2[i]) return false;
/*  275 */     return true;
/*      */   }
/*      */ 
/*      */   public static void ensureFromTo(float[] a, int from, int to)
/*      */   {
/*  288 */     it.unimi.dsi.fastutil.Arrays.ensureFromTo(a.length, from, to);
/*      */   }
/*      */ 
/*      */   public static void ensureOffsetLength(float[] a, int offset, int length)
/*      */   {
/*  301 */     it.unimi.dsi.fastutil.Arrays.ensureOffsetLength(a.length, offset, length);
/*      */   }
/*      */ 
/*      */   private static void swap(float[] x, int a, int b)
/*      */   {
/*  306 */     float t = x[a];
/*  307 */     x[a] = x[b];
/*  308 */     x[b] = t;
/*      */   }
/*      */   private static void vecSwap(float[] x, int a, int b, int n) {
/*  311 */     for (int i = 0; i < n; b++) { swap(x, a, b); i++; a++; } 
/*      */   }
/*      */ 
/*  314 */   private static int med3(float[] x, int a, int b, int c, FloatComparator comp) { int ab = comp.compare(x[a], x[b]);
/*  315 */     int ac = comp.compare(x[a], x[c]);
/*  316 */     int bc = comp.compare(x[b], x[c]);
/*  317 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*      */   }
/*      */ 
/*      */   private static void selectionSort(float[] a, int from, int to, FloatComparator comp)
/*      */   {
/*  322 */     for (int i = from; i < to - 1; i++) {
/*  323 */       int m = i;
/*  324 */       for (int j = i + 1; j < to; j++) if (comp.compare(a[j], a[m]) < 0) m = j;
/*  325 */       if (m != i) {
/*  326 */         float u = a[i];
/*  327 */         a[i] = a[m];
/*  328 */         a[m] = u;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*  333 */   private static void insertionSort(float[] a, int from, int to, FloatComparator comp) { int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/*  334 */       float t = a[i];
/*  335 */       int j = i;
/*  336 */       for (float u = a[(j - 1)]; comp.compare(t, u) < 0; u = a[(--j - 1)]) {
/*  337 */         a[j] = u;
/*  338 */         if (from == j - 1) {
/*  339 */           j--;
/*  340 */           break;
/*      */         }
/*      */       }
/*  343 */       a[j] = t; }
/*      */   }
/*      */ 
/*      */   private static void selectionSort(float[] a, int from, int to)
/*      */   {
/*  348 */     for (int i = from; i < to - 1; i++) {
/*  349 */       int m = i;
/*  350 */       for (int j = i + 1; j < to; j++) if (a[j] < a[m]) m = j;
/*  351 */       if (m != i) {
/*  352 */         float u = a[i];
/*  353 */         a[i] = a[m];
/*  354 */         a[m] = u;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void insertionSort(float[] a, int from, int to) {
/*  360 */     int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/*  361 */       float t = a[i];
/*  362 */       int j = i;
/*  363 */       for (float u = a[(j - 1)]; t < u; u = a[(--j - 1)]) {
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
/*      */   public static void quickSort(float[] x, int from, int to, FloatComparator comp)
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
/*  406 */     float v = x[m];
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
/*      */   public static void quickSort(float[] x, FloatComparator comp)
/*      */   {
/*  444 */     quickSort(x, 0, x.length, comp);
/*      */   }
/*      */ 
/*      */   private static int med3(float[] x, int a, int b, int c) {
/*  448 */     int ab = x[a] == x[b] ? 0 : x[a] < x[b] ? -1 : 1;
/*  449 */     int ac = x[a] == x[c] ? 0 : x[a] < x[c] ? -1 : 1;
/*  450 */     int bc = x[b] == x[c] ? 0 : x[b] < x[c] ? -1 : 1;
/*  451 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*      */   }
/*      */ 
/*      */   public static void quickSort(float[] x, int from, int to)
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
/*  487 */     float v = x[m];
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
/*      */   public static void quickSort(float[] x)
/*      */   {
/*  523 */     quickSort(x, 0, x.length);
/*      */   }
/*      */ 
/*      */   public static void mergeSort(float[] a, int from, int to, float[] supp)
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
/*      */   public static void mergeSort(float[] a, int from, int to)
/*      */   {
/*  569 */     mergeSort(a, from, to, (float[])a.clone());
/*      */   }
/*      */ 
/*      */   public static void mergeSort(float[] a)
/*      */   {
/*  579 */     mergeSort(a, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void mergeSort(float[] a, int from, int to, FloatComparator comp, float[] supp)
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
/*      */   public static void mergeSort(float[] a, int from, int to, FloatComparator comp)
/*      */   {
/*  629 */     mergeSort(a, from, to, comp, (float[])a.clone());
/*      */   }
/*      */ 
/*      */   public static void mergeSort(float[] a, FloatComparator comp)
/*      */   {
/*  641 */     mergeSort(a, 0, a.length, comp);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(float[] a, int from, int to, float key)
/*      */   {
/*  666 */     to--;
/*  667 */     while (from <= to) {
/*  668 */       int mid = from + to >>> 1;
/*  669 */       float midVal = a[mid];
/*  670 */       if (midVal < key) from = mid + 1;
/*  671 */       else if (midVal > key) to = mid - 1; else
/*  672 */         return mid;
/*      */     }
/*  674 */     return -(from + 1);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(float[] a, float key)
/*      */   {
/*  695 */     return binarySearch(a, 0, a.length, key);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(float[] a, int from, int to, float key, FloatComparator c)
/*      */   {
/*  720 */     to--;
/*  721 */     while (from <= to) {
/*  722 */       int mid = from + to >>> 1;
/*  723 */       float midVal = a[mid];
/*  724 */       int cmp = c.compare(midVal, key);
/*  725 */       if (cmp < 0) from = mid + 1;
/*  726 */       else if (cmp > 0) to = mid - 1; else
/*  727 */         return mid;
/*      */     }
/*  729 */     return -(from + 1);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(float[] a, float key, FloatComparator c)
/*      */   {
/*  751 */     return binarySearch(a, 0, a.length, key, c);
/*      */   }
/*      */ 
/*      */   private static final long fixFloat(float f)
/*      */   {
/*  761 */     long i = Float.floatToRawIntBits(f);
/*  762 */     return i >= 0L ? i : i ^ 0x7FFFFFFF;
/*      */   }
/*      */ 
/*      */   public static void radixSort(float[] a)
/*      */   {
/*  781 */     radixSort(a, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(float[] a, int from, int to)
/*      */   {
/*  802 */     int maxLevel = 3;
/*  803 */     int stackSize = 766;
/*  804 */     int[] offsetStack = new int[766];
/*  805 */     int offsetPos = 0;
/*  806 */     int[] lengthStack = new int[766];
/*  807 */     int lengthPos = 0;
/*  808 */     int[] levelStack = new int[766];
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
/*  820 */       int signMask = level % 4 == 0 ? 128 : 0;
/*  821 */       if (length < 50) {
/*  822 */         selectionSort(a, first, first + length);
/*      */       }
/*      */       else {
/*  825 */         int shift = (3 - level % 4) * 8;
/*      */ 
/*  827 */         for (int i = length; i-- != 0; digit[i] = ((byte)(int)(fixFloat(a[(first + i)]) >>> shift & 0xFF ^ signMask)));
/*  828 */         for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1);
/*  830 */         int lastUsed = -1;
/*  831 */         int i = 0; for (int p = 0; i < 256; i++) {
/*  832 */           if (count[i] != 0) {
/*  833 */             lastUsed = i;
/*  834 */             if ((level < 3) && (count[i] > 1))
/*      */             {
/*  836 */               offsetStack[(offsetPos++)] = (p + first);
/*  837 */               lengthStack[(lengthPos++)] = count[i];
/*  838 */               levelStack[(levelPos++)] = (level + 1);
/*      */             }
/*      */           }
/*      */           int tmp338_337 = (p + count[i]); p = tmp338_337; pos[i] = tmp338_337;
/*      */         }
/*      */ 
/*  844 */         int end = length - count[lastUsed];
/*  845 */         count[lastUsed] = 0;
/*      */ 
/*  847 */         int i = 0; for (int c = -1; i < end; count[c] = 0) {
/*  848 */           float t = a[(i + first)];
/*  849 */           c = digit[i] & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             int d;
/*  850 */             if ((d = pos[c] -= 1) <= i) break;
/*  851 */             float z = t;
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
/*      */   private static void insertionSortIndirect(int[] perm, float[] a, int from, int to)
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
/*      */   public static void radixSortIndirect(int[] perm, float[] a, boolean stable)
/*      */   {
/*  901 */     radixSortIndirect(perm, a, 0, perm.length, stable); } 
/*      */   public static void radixSortIndirect(int[] perm, float[] a, int from, int to, boolean stable) { // Byte code:
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
/*      */     //   114: ifle +499 -> 613
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
/*      */     //   179: invokestatic 44	it/unimi/dsi/fastutil/floats/FloatArrays:insertionSortIndirect	([I[FII)V
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
/*      */     //   205: ifeq +36 -> 241
/*      */     //   208: aload 16
/*      */     //   210: iload 22
/*      */     //   212: aload_1
/*      */     //   213: aload_0
/*      */     //   214: iload 17
/*      */     //   216: iload 22
/*      */     //   218: iadd
/*      */     //   219: iaload
/*      */     //   220: faload
/*      */     //   221: invokestatic 40	it/unimi/dsi/fastutil/floats/FloatArrays:fixFloat	(F)J
/*      */     //   224: iload 21
/*      */     //   226: lushr
/*      */     //   227: ldc2_w 41
/*      */     //   230: land
/*      */     //   231: iload 20
/*      */     //   233: i2l
/*      */     //   234: lxor
/*      */     //   235: l2i
/*      */     //   236: i2b
/*      */     //   237: bastore
/*      */     //   238: goto -38 -> 200
/*      */     //   241: iload 18
/*      */     //   243: istore 22
/*      */     //   245: iload 22
/*      */     //   247: iinc 22 255
/*      */     //   250: ifeq +22 -> 272
/*      */     //   253: aload 13
/*      */     //   255: aload 16
/*      */     //   257: iload 22
/*      */     //   259: baload
/*      */     //   260: sipush 255
/*      */     //   263: iand
/*      */     //   264: dup2
/*      */     //   265: iaload
/*      */     //   266: iconst_1
/*      */     //   267: iadd
/*      */     //   268: iastore
/*      */     //   269: goto -24 -> 245
/*      */     //   272: iconst_m1
/*      */     //   273: istore 22
/*      */     //   275: iconst_0
/*      */     //   276: istore 23
/*      */     //   278: iconst_0
/*      */     //   279: istore 24
/*      */     //   281: iload 23
/*      */     //   283: sipush 256
/*      */     //   286: if_icmpge +114 -> 400
/*      */     //   289: aload 13
/*      */     //   291: iload 23
/*      */     //   293: iaload
/*      */     //   294: ifeq +60 -> 354
/*      */     //   297: iload 23
/*      */     //   299: istore 22
/*      */     //   301: iload 19
/*      */     //   303: iconst_3
/*      */     //   304: if_icmpge +50 -> 354
/*      */     //   307: aload 13
/*      */     //   309: iload 23
/*      */     //   311: iaload
/*      */     //   312: iconst_1
/*      */     //   313: if_icmple +41 -> 354
/*      */     //   316: aload 7
/*      */     //   318: iload 8
/*      */     //   320: iinc 8 1
/*      */     //   323: iload 24
/*      */     //   325: iload 17
/*      */     //   327: iadd
/*      */     //   328: iastore
/*      */     //   329: aload 9
/*      */     //   331: iload 10
/*      */     //   333: iinc 10 1
/*      */     //   336: aload 13
/*      */     //   338: iload 23
/*      */     //   340: iaload
/*      */     //   341: iastore
/*      */     //   342: aload 11
/*      */     //   344: iload 12
/*      */     //   346: iinc 12 1
/*      */     //   349: iload 19
/*      */     //   351: iconst_1
/*      */     //   352: iadd
/*      */     //   353: iastore
/*      */     //   354: iload 4
/*      */     //   356: ifeq +22 -> 378
/*      */     //   359: aload 13
/*      */     //   361: iload 23
/*      */     //   363: iload 24
/*      */     //   365: aload 13
/*      */     //   367: iload 23
/*      */     //   369: iaload
/*      */     //   370: iadd
/*      */     //   371: dup
/*      */     //   372: istore 24
/*      */     //   374: iastore
/*      */     //   375: goto +19 -> 394
/*      */     //   378: aload 14
/*      */     //   380: iload 23
/*      */     //   382: iload 24
/*      */     //   384: aload 13
/*      */     //   386: iload 23
/*      */     //   388: iaload
/*      */     //   389: iadd
/*      */     //   390: dup
/*      */     //   391: istore 24
/*      */     //   393: iastore
/*      */     //   394: iinc 23 1
/*      */     //   397: goto -116 -> 281
/*      */     //   400: iload 4
/*      */     //   402: ifeq +65 -> 467
/*      */     //   405: iload 18
/*      */     //   407: istore 23
/*      */     //   409: iload 23
/*      */     //   411: iinc 23 255
/*      */     //   414: ifeq +33 -> 447
/*      */     //   417: aload 15
/*      */     //   419: aload 13
/*      */     //   421: aload 16
/*      */     //   423: iload 23
/*      */     //   425: baload
/*      */     //   426: sipush 255
/*      */     //   429: iand
/*      */     //   430: dup2
/*      */     //   431: iaload
/*      */     //   432: iconst_1
/*      */     //   433: isub
/*      */     //   434: dup_x2
/*      */     //   435: iastore
/*      */     //   436: aload_0
/*      */     //   437: iload 17
/*      */     //   439: iload 23
/*      */     //   441: iadd
/*      */     //   442: iaload
/*      */     //   443: iastore
/*      */     //   444: goto -35 -> 409
/*      */     //   447: aload 15
/*      */     //   449: iconst_0
/*      */     //   450: aload_0
/*      */     //   451: iload 17
/*      */     //   453: iload 18
/*      */     //   455: invokestatic 2	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
/*      */     //   458: aload 13
/*      */     //   460: iconst_0
/*      */     //   461: invokestatic 45	it/unimi/dsi/fastutil/ints/IntArrays:fill	([II)V
/*      */     //   464: goto +146 -> 610
/*      */     //   467: iload 18
/*      */     //   469: aload 13
/*      */     //   471: iload 22
/*      */     //   473: iaload
/*      */     //   474: isub
/*      */     //   475: istore 23
/*      */     //   477: aload 13
/*      */     //   479: iload 22
/*      */     //   481: iconst_0
/*      */     //   482: iastore
/*      */     //   483: iconst_0
/*      */     //   484: istore 24
/*      */     //   486: iconst_m1
/*      */     //   487: istore 25
/*      */     //   489: iload 24
/*      */     //   491: iload 23
/*      */     //   493: if_icmpge +117 -> 610
/*      */     //   496: aload_0
/*      */     //   497: iload 24
/*      */     //   499: iload 17
/*      */     //   501: iadd
/*      */     //   502: iaload
/*      */     //   503: istore 27
/*      */     //   505: aload 16
/*      */     //   507: iload 24
/*      */     //   509: baload
/*      */     //   510: sipush 255
/*      */     //   513: iand
/*      */     //   514: istore 25
/*      */     //   516: aload 14
/*      */     //   518: iload 25
/*      */     //   520: dup2
/*      */     //   521: iaload
/*      */     //   522: iconst_1
/*      */     //   523: isub
/*      */     //   524: dup_x2
/*      */     //   525: iastore
/*      */     //   526: dup
/*      */     //   527: istore 26
/*      */     //   529: iload 24
/*      */     //   531: if_icmple +51 -> 582
/*      */     //   534: iload 27
/*      */     //   536: istore 28
/*      */     //   538: iload 25
/*      */     //   540: istore 29
/*      */     //   542: aload_0
/*      */     //   543: iload 26
/*      */     //   545: iload 17
/*      */     //   547: iadd
/*      */     //   548: iaload
/*      */     //   549: istore 27
/*      */     //   551: aload 16
/*      */     //   553: iload 26
/*      */     //   555: baload
/*      */     //   556: sipush 255
/*      */     //   559: iand
/*      */     //   560: istore 25
/*      */     //   562: aload_0
/*      */     //   563: iload 26
/*      */     //   565: iload 17
/*      */     //   567: iadd
/*      */     //   568: iload 28
/*      */     //   570: iastore
/*      */     //   571: aload 16
/*      */     //   573: iload 26
/*      */     //   575: iload 29
/*      */     //   577: i2b
/*      */     //   578: bastore
/*      */     //   579: goto -63 -> 516
/*      */     //   582: aload_0
/*      */     //   583: iload 24
/*      */     //   585: iload 17
/*      */     //   587: iadd
/*      */     //   588: iload 27
/*      */     //   590: iastore
/*      */     //   591: iload 24
/*      */     //   593: aload 13
/*      */     //   595: iload 25
/*      */     //   597: iaload
/*      */     //   598: iadd
/*      */     //   599: istore 24
/*      */     //   601: aload 13
/*      */     //   603: iload 25
/*      */     //   605: iconst_0
/*      */     //   606: iastore
/*      */     //   607: goto -118 -> 489
/*      */     //   610: goto -498 -> 112
/*      */     //   613: return } 
/*  999 */   private static void selectionSort(float[] a, float[] b, int from, int to) { for (int i = from; i < to - 1; i++) {
/* 1000 */       int m = i;
/* 1001 */       for (int j = i + 1; j < to; j++)
/* 1002 */         if ((a[j] < a[m]) || ((a[j] == a[m]) && (b[j] < b[m]))) m = j;
/* 1003 */       if (m != i) {
/* 1004 */         float t = a[i];
/* 1005 */         a[i] = a[m];
/* 1006 */         a[m] = t;
/* 1007 */         t = b[i];
/* 1008 */         b[i] = b[m];
/* 1009 */         b[m] = t;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSort(float[] a, float[] b)
/*      */   {
/* 1033 */     radixSort(a, b, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(float[] a, float[] b, int from, int to)
/*      */   {
/* 1058 */     int layers = 2;
/* 1059 */     if (a.length != b.length) throw new IllegalArgumentException("Array size mismatch.");
/* 1060 */     int maxLevel = 7;
/* 1061 */     int stackSize = 1786;
/* 1062 */     int[] offsetStack = new int[1786];
/* 1063 */     int offsetPos = 0;
/* 1064 */     int[] lengthStack = new int[1786];
/* 1065 */     int lengthPos = 0;
/* 1066 */     int[] levelStack = new int[1786];
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
/* 1078 */       int signMask = level % 4 == 0 ? 128 : 0;
/* 1079 */       if (length < 50) {
/* 1080 */         selectionSort(a, b, first, first + length);
/*      */       }
/*      */       else {
/* 1083 */         float[] k = level < 4 ? a : b;
/* 1084 */         int shift = (3 - level % 4) * 8;
/*      */ 
/* 1086 */         for (int i = length; i-- != 0; digit[i] = ((byte)(int)(fixFloat(k[(first + i)]) >>> shift & 0xFF ^ signMask)));
/* 1087 */         for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1);
/* 1089 */         int lastUsed = -1;
/* 1090 */         int i = 0; for (int p = 0; i < 256; i++) {
/* 1091 */           if (count[i] != 0) {
/* 1092 */             lastUsed = i;
/* 1093 */             if ((level < 7) && (count[i] > 1)) {
/* 1094 */               offsetStack[(offsetPos++)] = (p + first);
/* 1095 */               lengthStack[(lengthPos++)] = count[i];
/* 1096 */               levelStack[(levelPos++)] = (level + 1);
/*      */             }
/*      */           }
/*      */           int tmp376_375 = (p + count[i]); p = tmp376_375; pos[i] = tmp376_375;
/*      */         }
/*      */ 
/* 1102 */         int end = length - count[lastUsed];
/* 1103 */         count[lastUsed] = 0;
/*      */ 
/* 1105 */         int i = 0; for (int c = -1; i < end; count[c] = 0) {
/* 1106 */           float t = a[(i + first)];
/* 1107 */           float u = b[(i + first)];
/* 1108 */           c = digit[i] & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             int d;
/* 1109 */             if ((d = pos[c] -= 1) <= i) break;
/* 1110 */             float z = t;
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
/*      */   private static void insertionSortIndirect(int[] perm, float[] a, float[] b, int from, int to)
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
/*      */   public static void radixSortIndirect(int[] perm, float[] a, float[] b, boolean stable)
/*      */   {
/* 1165 */     radixSortIndirect(perm, a, b, 0, perm.length, stable); } 
/*      */   public static void radixSortIndirect(int[] perm, float[] a, float[] b, int from, int to, boolean stable) { // Byte code:
/*      */     //   0: iconst_2
/*      */     //   1: istore 6
/*      */     //   3: aload_1
/*      */     //   4: arraylength
/*      */     //   5: aload_2
/*      */     //   6: arraylength
/*      */     //   7: if_icmpeq +13 -> 20
/*      */     //   10: new 47	java/lang/IllegalArgumentException
/*      */     //   13: dup
/*      */     //   14: ldc 48
/*      */     //   16: invokespecial 49	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
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
/*      */     //   137: ifle +515 -> 652
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
/*      */     //   203: invokestatic 52	it/unimi/dsi/fastutil/floats/FloatArrays:insertionSortIndirect	([I[F[FII)V
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
/*      */     //   242: ifeq +37 -> 279
/*      */     //   245: aload 18
/*      */     //   247: iload 25
/*      */     //   249: aload 23
/*      */     //   251: aload_0
/*      */     //   252: iload 19
/*      */     //   254: iload 25
/*      */     //   256: iadd
/*      */     //   257: iaload
/*      */     //   258: faload
/*      */     //   259: invokestatic 40	it/unimi/dsi/fastutil/floats/FloatArrays:fixFloat	(F)J
/*      */     //   262: iload 24
/*      */     //   264: lushr
/*      */     //   265: ldc2_w 41
/*      */     //   268: land
/*      */     //   269: iload 22
/*      */     //   271: i2l
/*      */     //   272: lxor
/*      */     //   273: l2i
/*      */     //   274: i2b
/*      */     //   275: bastore
/*      */     //   276: goto -39 -> 237
/*      */     //   279: iload 20
/*      */     //   281: istore 25
/*      */     //   283: iload 25
/*      */     //   285: iinc 25 255
/*      */     //   288: ifeq +22 -> 310
/*      */     //   291: aload 15
/*      */     //   293: aload 18
/*      */     //   295: iload 25
/*      */     //   297: baload
/*      */     //   298: sipush 255
/*      */     //   301: iand
/*      */     //   302: dup2
/*      */     //   303: iaload
/*      */     //   304: iconst_1
/*      */     //   305: iadd
/*      */     //   306: iastore
/*      */     //   307: goto -24 -> 283
/*      */     //   310: iconst_m1
/*      */     //   311: istore 25
/*      */     //   313: iconst_0
/*      */     //   314: istore 26
/*      */     //   316: iconst_0
/*      */     //   317: istore 27
/*      */     //   319: iload 26
/*      */     //   321: sipush 256
/*      */     //   324: if_icmpge +115 -> 439
/*      */     //   327: aload 15
/*      */     //   329: iload 26
/*      */     //   331: iaload
/*      */     //   332: ifeq +61 -> 393
/*      */     //   335: iload 26
/*      */     //   337: istore 25
/*      */     //   339: iload 21
/*      */     //   341: bipush 7
/*      */     //   343: if_icmpge +50 -> 393
/*      */     //   346: aload 15
/*      */     //   348: iload 26
/*      */     //   350: iaload
/*      */     //   351: iconst_1
/*      */     //   352: if_icmple +41 -> 393
/*      */     //   355: aload 9
/*      */     //   357: iload 10
/*      */     //   359: iinc 10 1
/*      */     //   362: iload 27
/*      */     //   364: iload 19
/*      */     //   366: iadd
/*      */     //   367: iastore
/*      */     //   368: aload 11
/*      */     //   370: iload 12
/*      */     //   372: iinc 12 1
/*      */     //   375: aload 15
/*      */     //   377: iload 26
/*      */     //   379: iaload
/*      */     //   380: iastore
/*      */     //   381: aload 13
/*      */     //   383: iload 14
/*      */     //   385: iinc 14 1
/*      */     //   388: iload 21
/*      */     //   390: iconst_1
/*      */     //   391: iadd
/*      */     //   392: iastore
/*      */     //   393: iload 5
/*      */     //   395: ifeq +22 -> 417
/*      */     //   398: aload 15
/*      */     //   400: iload 26
/*      */     //   402: iload 27
/*      */     //   404: aload 15
/*      */     //   406: iload 26
/*      */     //   408: iaload
/*      */     //   409: iadd
/*      */     //   410: dup
/*      */     //   411: istore 27
/*      */     //   413: iastore
/*      */     //   414: goto +19 -> 433
/*      */     //   417: aload 16
/*      */     //   419: iload 26
/*      */     //   421: iload 27
/*      */     //   423: aload 15
/*      */     //   425: iload 26
/*      */     //   427: iaload
/*      */     //   428: iadd
/*      */     //   429: dup
/*      */     //   430: istore 27
/*      */     //   432: iastore
/*      */     //   433: iinc 26 1
/*      */     //   436: goto -117 -> 319
/*      */     //   439: iload 5
/*      */     //   441: ifeq +65 -> 506
/*      */     //   444: iload 20
/*      */     //   446: istore 26
/*      */     //   448: iload 26
/*      */     //   450: iinc 26 255
/*      */     //   453: ifeq +33 -> 486
/*      */     //   456: aload 17
/*      */     //   458: aload 15
/*      */     //   460: aload 18
/*      */     //   462: iload 26
/*      */     //   464: baload
/*      */     //   465: sipush 255
/*      */     //   468: iand
/*      */     //   469: dup2
/*      */     //   470: iaload
/*      */     //   471: iconst_1
/*      */     //   472: isub
/*      */     //   473: dup_x2
/*      */     //   474: iastore
/*      */     //   475: aload_0
/*      */     //   476: iload 19
/*      */     //   478: iload 26
/*      */     //   480: iadd
/*      */     //   481: iaload
/*      */     //   482: iastore
/*      */     //   483: goto -35 -> 448
/*      */     //   486: aload 17
/*      */     //   488: iconst_0
/*      */     //   489: aload_0
/*      */     //   490: iload 19
/*      */     //   492: iload 20
/*      */     //   494: invokestatic 2	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
/*      */     //   497: aload 15
/*      */     //   499: iconst_0
/*      */     //   500: invokestatic 45	it/unimi/dsi/fastutil/ints/IntArrays:fill	([II)V
/*      */     //   503: goto +146 -> 649
/*      */     //   506: iload 20
/*      */     //   508: aload 15
/*      */     //   510: iload 25
/*      */     //   512: iaload
/*      */     //   513: isub
/*      */     //   514: istore 26
/*      */     //   516: aload 15
/*      */     //   518: iload 25
/*      */     //   520: iconst_0
/*      */     //   521: iastore
/*      */     //   522: iconst_0
/*      */     //   523: istore 27
/*      */     //   525: iconst_m1
/*      */     //   526: istore 28
/*      */     //   528: iload 27
/*      */     //   530: iload 26
/*      */     //   532: if_icmpge +117 -> 649
/*      */     //   535: aload_0
/*      */     //   536: iload 27
/*      */     //   538: iload 19
/*      */     //   540: iadd
/*      */     //   541: iaload
/*      */     //   542: istore 30
/*      */     //   544: aload 18
/*      */     //   546: iload 27
/*      */     //   548: baload
/*      */     //   549: sipush 255
/*      */     //   552: iand
/*      */     //   553: istore 28
/*      */     //   555: aload 16
/*      */     //   557: iload 28
/*      */     //   559: dup2
/*      */     //   560: iaload
/*      */     //   561: iconst_1
/*      */     //   562: isub
/*      */     //   563: dup_x2
/*      */     //   564: iastore
/*      */     //   565: dup
/*      */     //   566: istore 29
/*      */     //   568: iload 27
/*      */     //   570: if_icmple +51 -> 621
/*      */     //   573: iload 30
/*      */     //   575: istore 31
/*      */     //   577: iload 28
/*      */     //   579: istore 32
/*      */     //   581: aload_0
/*      */     //   582: iload 29
/*      */     //   584: iload 19
/*      */     //   586: iadd
/*      */     //   587: iaload
/*      */     //   588: istore 30
/*      */     //   590: aload 18
/*      */     //   592: iload 29
/*      */     //   594: baload
/*      */     //   595: sipush 255
/*      */     //   598: iand
/*      */     //   599: istore 28
/*      */     //   601: aload_0
/*      */     //   602: iload 29
/*      */     //   604: iload 19
/*      */     //   606: iadd
/*      */     //   607: iload 31
/*      */     //   609: iastore
/*      */     //   610: aload 18
/*      */     //   612: iload 29
/*      */     //   614: iload 32
/*      */     //   616: i2b
/*      */     //   617: bastore
/*      */     //   618: goto -63 -> 555
/*      */     //   621: aload_0
/*      */     //   622: iload 27
/*      */     //   624: iload 19
/*      */     //   626: iadd
/*      */     //   627: iload 30
/*      */     //   629: iastore
/*      */     //   630: iload 27
/*      */     //   632: aload 15
/*      */     //   634: iload 28
/*      */     //   636: iaload
/*      */     //   637: iadd
/*      */     //   638: istore 27
/*      */     //   640: aload 15
/*      */     //   642: iload 28
/*      */     //   644: iconst_0
/*      */     //   645: iastore
/*      */     //   646: goto -118 -> 528
/*      */     //   649: goto -514 -> 135
/*      */     //   652: return } 
/* 1267 */   private static void selectionSort(float[][] a, int from, int to, int level) { int layers = a.length;
/* 1268 */     int firstLayer = level / 4;
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
/* 1282 */           float u = a[p][i];
/* 1283 */           a[p][i] = a[p][m];
/* 1284 */           a[p][m] = u;
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSort(float[][] a)
/*      */   {
/* 1309 */     radixSort(a, 0, a[0].length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(float[][] a, int from, int to)
/*      */   {
/* 1333 */     int layers = a.length;
/* 1334 */     int maxLevel = 4 * layers - 1;
/* 1335 */     int p = layers; for (int l = a[0].length; p-- != 0; ) if (a[p].length != l) throw new IllegalArgumentException("The array of index " + p + " has not the same length of the array of index 0.");
/* 1336 */     int stackSize = 255 * (layers * 4 - 1) + 1;
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
/* 1349 */     float[] t = new float[layers];
/* 1350 */     while (offsetPos > 0) {
/* 1351 */       int first = offsetStack[(--offsetPos)];
/* 1352 */       int length = lengthStack[(--lengthPos)];
/* 1353 */       int level = levelStack[(--levelPos)];
/* 1354 */       int signMask = level % 4 == 0 ? 128 : 0;
/* 1355 */       if (length < 50) {
/* 1356 */         selectionSort(a, first, first + length, level);
/*      */       }
/*      */       else {
/* 1359 */         float[] k = a[(level / 4)];
/* 1360 */         int shift = (3 - level % 4) * 8;
/*      */ 
/* 1362 */         for (int i = length; i-- != 0; digit[i] = ((byte)(int)(fixFloat(k[(first + i)]) >>> shift & 0xFF ^ signMask)));
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
/*      */           int tmp428_427 = (p + count[i]); p = tmp428_427; pos[i] = tmp428_427;
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
/* 1386 */               float u = t[p];
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
/*      */   public static float[] shuffle(float[] a, int from, int to, Random random)
/*      */   {
/* 1407 */     for (int i = to - from; i-- != 0; ) {
/* 1408 */       int p = random.nextInt(i + 1);
/* 1409 */       float t = a[(from + i)];
/* 1410 */       a[(from + i)] = a[(from + p)];
/* 1411 */       a[(from + p)] = t;
/*      */     }
/* 1413 */     return a;
/*      */   }
/*      */ 
/*      */   public static float[] shuffle(float[] a, Random random)
/*      */   {
/* 1422 */     for (int i = a.length; i-- != 0; ) {
/* 1423 */       int p = random.nextInt(i + 1);
/* 1424 */       float t = a[i];
/* 1425 */       a[i] = a[p];
/* 1426 */       a[p] = t;
/*      */     }
/* 1428 */     return a;
/*      */   }
/*      */ 
/*      */   public static float[] reverse(float[] a)
/*      */   {
/* 1436 */     int length = a.length;
/* 1437 */     for (int i = length / 2; i-- != 0; ) {
/* 1438 */       float t = a[(length - i - 1)];
/* 1439 */       a[(length - i - 1)] = a[i];
/* 1440 */       a[i] = t;
/*      */     }
/* 1442 */     return a;
/*      */   }
/*      */   private static final class ArrayHashStrategy implements Hash.Strategy<float[]>, Serializable {
/*      */     public static final long serialVersionUID = -7046029254386353129L;
/*      */ 
/*      */     public int hashCode(float[] o) {
/* 1448 */       return java.util.Arrays.hashCode(o);
/*      */     }
/*      */     public boolean equals(float[] a, float[] b) {
/* 1451 */       return FloatArrays.equals(a, b);
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatArrays
 * JD-Core Version:    0.6.2
 */