/*      */ package it.unimi.dsi.fastutil.chars;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.Hash.Strategy;
/*      */ import it.unimi.dsi.fastutil.ints.IntArrays;
/*      */ import java.io.Serializable;
/*      */ import java.util.Random;
/*      */ 
/*      */ public class CharArrays
/*      */ {
/*      */   public static final long ONEOVERPHI = 106039L;
/*   87 */   public static final char[] EMPTY_ARRAY = new char[0];
/*      */   private static final int SMALL = 7;
/*      */   private static final int MEDIUM = 50;
/*      */   private static final int DIGIT_BITS = 8;
/*      */   private static final int DIGIT_MASK = 255;
/*      */   private static final int DIGITS_PER_ELEMENT = 2;
/* 1456 */   public static final Hash.Strategy<char[]> HASH_STRATEGY = new ArrayHashStrategy(null);
/*      */ 
/*      */   public static char[] ensureCapacity(char[] array, int length)
/*      */   {
/*  100 */     if (length > array.length) {
/*  101 */       char[] t = new char[length];
/*      */ 
/*  103 */       System.arraycopy(array, 0, t, 0, array.length);
/*  104 */       return t;
/*      */     }
/*  106 */     return array;
/*      */   }
/*      */ 
/*      */   public static char[] ensureCapacity(char[] array, int length, int preserve)
/*      */   {
/*  118 */     if (length > array.length) {
/*  119 */       char[] t = new char[length];
/*      */ 
/*  121 */       System.arraycopy(array, 0, t, 0, preserve);
/*  122 */       return t;
/*      */     }
/*  124 */     return array;
/*      */   }
/*      */ 
/*      */   public static char[] grow(char[] array, int length)
/*      */   {
/*  142 */     if (length > array.length) {
/*  143 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  144 */       char[] t = new char[newLength];
/*      */ 
/*  146 */       System.arraycopy(array, 0, t, 0, array.length);
/*  147 */       return t;
/*      */     }
/*  149 */     return array;
/*      */   }
/*      */ 
/*      */   public static char[] grow(char[] array, int length, int preserve)
/*      */   {
/*  168 */     if (length > array.length) {
/*  169 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  170 */       char[] t = new char[newLength];
/*      */ 
/*  172 */       System.arraycopy(array, 0, t, 0, preserve);
/*  173 */       return t;
/*      */     }
/*  175 */     return array;
/*      */   }
/*      */ 
/*      */   public static char[] trim(char[] array, int length)
/*      */   {
/*  188 */     if (length >= array.length) return array;
/*  189 */     char[] t = length == 0 ? EMPTY_ARRAY : new char[length];
/*      */ 
/*  191 */     System.arraycopy(array, 0, t, 0, length);
/*  192 */     return t;
/*      */   }
/*      */ 
/*      */   public static char[] setLength(char[] array, int length)
/*      */   {
/*  208 */     if (length == array.length) return array;
/*  209 */     if (length < array.length) return trim(array, length);
/*  210 */     return ensureCapacity(array, length);
/*      */   }
/*      */ 
/*      */   public static char[] copy(char[] array, int offset, int length)
/*      */   {
/*  220 */     ensureOffsetLength(array, offset, length);
/*  221 */     char[] a = length == 0 ? EMPTY_ARRAY : new char[length];
/*      */ 
/*  223 */     System.arraycopy(array, offset, a, 0, length);
/*  224 */     return a;
/*      */   }
/*      */ 
/*      */   public static char[] copy(char[] array)
/*      */   {
/*  232 */     return (char[])array.clone();
/*      */   }
/*      */ 
/*      */   public static void fill(char[] array, char value)
/*      */   {
/*  243 */     int i = array.length;
/*  244 */     while (i-- != 0) array[i] = value;
/*      */   }
/*      */ 
/*      */   public static void fill(char[] array, int from, int to, char value)
/*      */   {
/*  258 */     ensureFromTo(array, from, to);
/*  259 */     for (from != 0; to-- != 0; array[to] = value);
/*  260 */     for (int i = from; i < to; i++) array[i] = value;
/*      */   }
/*      */ 
/*      */   public static boolean equals(char[] a1, char[] a2)
/*      */   {
/*  272 */     int i = a1.length;
/*  273 */     if (i != a2.length) return false;
/*  274 */     while (i-- != 0) if (a1[i] != a2[i]) return false;
/*  275 */     return true;
/*      */   }
/*      */ 
/*      */   public static void ensureFromTo(char[] a, int from, int to)
/*      */   {
/*  288 */     it.unimi.dsi.fastutil.Arrays.ensureFromTo(a.length, from, to);
/*      */   }
/*      */ 
/*      */   public static void ensureOffsetLength(char[] a, int offset, int length)
/*      */   {
/*  301 */     it.unimi.dsi.fastutil.Arrays.ensureOffsetLength(a.length, offset, length);
/*      */   }
/*      */ 
/*      */   private static void swap(char[] x, int a, int b)
/*      */   {
/*  306 */     char t = x[a];
/*  307 */     x[a] = x[b];
/*  308 */     x[b] = t;
/*      */   }
/*      */   private static void vecSwap(char[] x, int a, int b, int n) {
/*  311 */     for (int i = 0; i < n; b++) { swap(x, a, b); i++; a++; } 
/*      */   }
/*      */ 
/*  314 */   private static int med3(char[] x, int a, int b, int c, CharComparator comp) { int ab = comp.compare(x[a], x[b]);
/*  315 */     int ac = comp.compare(x[a], x[c]);
/*  316 */     int bc = comp.compare(x[b], x[c]);
/*  317 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*      */   }
/*      */ 
/*      */   private static void selectionSort(char[] a, int from, int to, CharComparator comp)
/*      */   {
/*  322 */     for (int i = from; i < to - 1; i++) {
/*  323 */       int m = i;
/*  324 */       for (int j = i + 1; j < to; j++) if (comp.compare(a[j], a[m]) < 0) m = j;
/*  325 */       if (m != i) {
/*  326 */         char u = a[i];
/*  327 */         a[i] = a[m];
/*  328 */         a[m] = u;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*  333 */   private static void insertionSort(char[] a, int from, int to, CharComparator comp) { int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/*  334 */       char t = a[i];
/*  335 */       int j = i;
/*  336 */       for (char u = a[(j - 1)]; comp.compare(t, u) < 0; u = a[(--j - 1)]) {
/*  337 */         a[j] = u;
/*  338 */         if (from == j - 1) {
/*  339 */           j--;
/*  340 */           break;
/*      */         }
/*      */       }
/*  343 */       a[j] = t; }
/*      */   }
/*      */ 
/*      */   private static void selectionSort(char[] a, int from, int to)
/*      */   {
/*  348 */     for (int i = from; i < to - 1; i++) {
/*  349 */       int m = i;
/*  350 */       for (int j = i + 1; j < to; j++) if (a[j] < a[m]) m = j;
/*  351 */       if (m != i) {
/*  352 */         char u = a[i];
/*  353 */         a[i] = a[m];
/*  354 */         a[m] = u;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void insertionSort(char[] a, int from, int to) {
/*  360 */     int i = from;
/*      */     while (true) { i++; if (i >= to) break;
/*  361 */       char t = a[i];
/*  362 */       int j = i;
/*  363 */       for (char u = a[(j - 1)]; t < u; u = a[(--j - 1)]) {
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
/*      */   public static void quickSort(char[] x, int from, int to, CharComparator comp)
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
/*  406 */     char v = x[m];
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
/*      */   public static void quickSort(char[] x, CharComparator comp)
/*      */   {
/*  444 */     quickSort(x, 0, x.length, comp);
/*      */   }
/*      */ 
/*      */   private static int med3(char[] x, int a, int b, int c) {
/*  448 */     int ab = x[a] == x[b] ? 0 : x[a] < x[b] ? -1 : 1;
/*  449 */     int ac = x[a] == x[c] ? 0 : x[a] < x[c] ? -1 : 1;
/*  450 */     int bc = x[b] == x[c] ? 0 : x[b] < x[c] ? -1 : 1;
/*  451 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*      */   }
/*      */ 
/*      */   public static void quickSort(char[] x, int from, int to)
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
/*  487 */     char v = x[m];
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
/*      */   public static void quickSort(char[] x)
/*      */   {
/*  523 */     quickSort(x, 0, x.length);
/*      */   }
/*      */ 
/*      */   public static void mergeSort(char[] a, int from, int to, char[] supp)
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
/*      */   public static void mergeSort(char[] a, int from, int to)
/*      */   {
/*  569 */     mergeSort(a, from, to, (char[])a.clone());
/*      */   }
/*      */ 
/*      */   public static void mergeSort(char[] a)
/*      */   {
/*  579 */     mergeSort(a, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void mergeSort(char[] a, int from, int to, CharComparator comp, char[] supp)
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
/*      */   public static void mergeSort(char[] a, int from, int to, CharComparator comp)
/*      */   {
/*  629 */     mergeSort(a, from, to, comp, (char[])a.clone());
/*      */   }
/*      */ 
/*      */   public static void mergeSort(char[] a, CharComparator comp)
/*      */   {
/*  641 */     mergeSort(a, 0, a.length, comp);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(char[] a, int from, int to, char key)
/*      */   {
/*  666 */     to--;
/*  667 */     while (from <= to) {
/*  668 */       int mid = from + to >>> 1;
/*  669 */       char midVal = a[mid];
/*  670 */       if (midVal < key) from = mid + 1;
/*  671 */       else if (midVal > key) to = mid - 1; else
/*  672 */         return mid;
/*      */     }
/*  674 */     return -(from + 1);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(char[] a, char key)
/*      */   {
/*  695 */     return binarySearch(a, 0, a.length, key);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(char[] a, int from, int to, char key, CharComparator c)
/*      */   {
/*  720 */     to--;
/*  721 */     while (from <= to) {
/*  722 */       int mid = from + to >>> 1;
/*  723 */       char midVal = a[mid];
/*  724 */       int cmp = c.compare(midVal, key);
/*  725 */       if (cmp < 0) from = mid + 1;
/*  726 */       else if (cmp > 0) to = mid - 1; else
/*  727 */         return mid;
/*      */     }
/*  729 */     return -(from + 1);
/*      */   }
/*      */ 
/*      */   public static int binarySearch(char[] a, char key, CharComparator c)
/*      */   {
/*  751 */     return binarySearch(a, 0, a.length, key, c);
/*      */   }
/*      */ 
/*      */   public static void radixSort(char[] a)
/*      */   {
/*  777 */     radixSort(a, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(char[] a, int from, int to)
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
/*  816 */       int signMask = 0;
/*  817 */       if (length < 50) {
/*  818 */         selectionSort(a, first, first + length);
/*      */       }
/*      */       else {
/*  821 */         int shift = (1 - level % 2) * 8;
/*      */ 
/*  823 */         for (int i = length; i-- != 0; digit[i] = ((byte)(a[(first + i)] >>> shift & 0xFF ^ 0x0)));
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
/*      */           int tmp319_318 = (p + count[i]); p = tmp319_318; pos[i] = tmp319_318;
/*      */         }
/*      */ 
/*  840 */         int end = length - count[lastUsed];
/*  841 */         count[lastUsed] = 0;
/*      */ 
/*  843 */         int i = 0; for (int c = -1; i < end; count[c] = 0) {
/*  844 */           char t = a[(i + first)];
/*  845 */           c = digit[i] & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             int d;
/*  846 */             if ((d = pos[c] -= 1) <= i) break;
/*  847 */             char z = t;
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
/*      */   private static void insertionSortIndirect(int[] perm, char[] a, int from, int to)
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
/*      */   public static void radixSortIndirect(int[] perm, char[] a, boolean stable)
/*      */   {
/*  897 */     radixSortIndirect(perm, a, 0, perm.length, stable); } 
/*      */   public static void radixSortIndirect(int[] perm, char[] a, int from, int to, boolean stable) { // Byte code:
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
/*      */     //   114: ifle +480 -> 594
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
/*      */     //   147: iconst_0
/*      */     //   148: istore 20
/*      */     //   150: iload 18
/*      */     //   152: bipush 50
/*      */     //   154: if_icmpge +18 -> 172
/*      */     //   157: aload_0
/*      */     //   158: aload_1
/*      */     //   159: iload 17
/*      */     //   161: iload 17
/*      */     //   163: iload 18
/*      */     //   165: iadd
/*      */     //   166: invokestatic 40	it/unimi/dsi/fastutil/chars/CharArrays:insertionSortIndirect	([I[CII)V
/*      */     //   169: goto -57 -> 112
/*      */     //   172: iconst_1
/*      */     //   173: iload 19
/*      */     //   175: iconst_2
/*      */     //   176: irem
/*      */     //   177: isub
/*      */     //   178: bipush 8
/*      */     //   180: imul
/*      */     //   181: istore 21
/*      */     //   183: iload 18
/*      */     //   185: istore 22
/*      */     //   187: iload 22
/*      */     //   189: iinc 22 255
/*      */     //   192: ifeq +30 -> 222
/*      */     //   195: aload 16
/*      */     //   197: iload 22
/*      */     //   199: aload_1
/*      */     //   200: aload_0
/*      */     //   201: iload 17
/*      */     //   203: iload 22
/*      */     //   205: iadd
/*      */     //   206: iaload
/*      */     //   207: caload
/*      */     //   208: iload 21
/*      */     //   210: iushr
/*      */     //   211: sipush 255
/*      */     //   214: iand
/*      */     //   215: iconst_0
/*      */     //   216: ixor
/*      */     //   217: i2b
/*      */     //   218: bastore
/*      */     //   219: goto -32 -> 187
/*      */     //   222: iload 18
/*      */     //   224: istore 22
/*      */     //   226: iload 22
/*      */     //   228: iinc 22 255
/*      */     //   231: ifeq +22 -> 253
/*      */     //   234: aload 13
/*      */     //   236: aload 16
/*      */     //   238: iload 22
/*      */     //   240: baload
/*      */     //   241: sipush 255
/*      */     //   244: iand
/*      */     //   245: dup2
/*      */     //   246: iaload
/*      */     //   247: iconst_1
/*      */     //   248: iadd
/*      */     //   249: iastore
/*      */     //   250: goto -24 -> 226
/*      */     //   253: iconst_m1
/*      */     //   254: istore 22
/*      */     //   256: iconst_0
/*      */     //   257: istore 23
/*      */     //   259: iconst_0
/*      */     //   260: istore 24
/*      */     //   262: iload 23
/*      */     //   264: sipush 256
/*      */     //   267: if_icmpge +114 -> 381
/*      */     //   270: aload 13
/*      */     //   272: iload 23
/*      */     //   274: iaload
/*      */     //   275: ifeq +60 -> 335
/*      */     //   278: iload 23
/*      */     //   280: istore 22
/*      */     //   282: iload 19
/*      */     //   284: iconst_1
/*      */     //   285: if_icmpge +50 -> 335
/*      */     //   288: aload 13
/*      */     //   290: iload 23
/*      */     //   292: iaload
/*      */     //   293: iconst_1
/*      */     //   294: if_icmple +41 -> 335
/*      */     //   297: aload 7
/*      */     //   299: iload 8
/*      */     //   301: iinc 8 1
/*      */     //   304: iload 24
/*      */     //   306: iload 17
/*      */     //   308: iadd
/*      */     //   309: iastore
/*      */     //   310: aload 9
/*      */     //   312: iload 10
/*      */     //   314: iinc 10 1
/*      */     //   317: aload 13
/*      */     //   319: iload 23
/*      */     //   321: iaload
/*      */     //   322: iastore
/*      */     //   323: aload 11
/*      */     //   325: iload 12
/*      */     //   327: iinc 12 1
/*      */     //   330: iload 19
/*      */     //   332: iconst_1
/*      */     //   333: iadd
/*      */     //   334: iastore
/*      */     //   335: iload 4
/*      */     //   337: ifeq +22 -> 359
/*      */     //   340: aload 13
/*      */     //   342: iload 23
/*      */     //   344: iload 24
/*      */     //   346: aload 13
/*      */     //   348: iload 23
/*      */     //   350: iaload
/*      */     //   351: iadd
/*      */     //   352: dup
/*      */     //   353: istore 24
/*      */     //   355: iastore
/*      */     //   356: goto +19 -> 375
/*      */     //   359: aload 14
/*      */     //   361: iload 23
/*      */     //   363: iload 24
/*      */     //   365: aload 13
/*      */     //   367: iload 23
/*      */     //   369: iaload
/*      */     //   370: iadd
/*      */     //   371: dup
/*      */     //   372: istore 24
/*      */     //   374: iastore
/*      */     //   375: iinc 23 1
/*      */     //   378: goto -116 -> 262
/*      */     //   381: iload 4
/*      */     //   383: ifeq +65 -> 448
/*      */     //   386: iload 18
/*      */     //   388: istore 23
/*      */     //   390: iload 23
/*      */     //   392: iinc 23 255
/*      */     //   395: ifeq +33 -> 428
/*      */     //   398: aload 15
/*      */     //   400: aload 13
/*      */     //   402: aload 16
/*      */     //   404: iload 23
/*      */     //   406: baload
/*      */     //   407: sipush 255
/*      */     //   410: iand
/*      */     //   411: dup2
/*      */     //   412: iaload
/*      */     //   413: iconst_1
/*      */     //   414: isub
/*      */     //   415: dup_x2
/*      */     //   416: iastore
/*      */     //   417: aload_0
/*      */     //   418: iload 17
/*      */     //   420: iload 23
/*      */     //   422: iadd
/*      */     //   423: iaload
/*      */     //   424: iastore
/*      */     //   425: goto -35 -> 390
/*      */     //   428: aload 15
/*      */     //   430: iconst_0
/*      */     //   431: aload_0
/*      */     //   432: iload 17
/*      */     //   434: iload 18
/*      */     //   436: invokestatic 2	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
/*      */     //   439: aload 13
/*      */     //   441: iconst_0
/*      */     //   442: invokestatic 41	it/unimi/dsi/fastutil/ints/IntArrays:fill	([II)V
/*      */     //   445: goto +146 -> 591
/*      */     //   448: iload 18
/*      */     //   450: aload 13
/*      */     //   452: iload 22
/*      */     //   454: iaload
/*      */     //   455: isub
/*      */     //   456: istore 23
/*      */     //   458: aload 13
/*      */     //   460: iload 22
/*      */     //   462: iconst_0
/*      */     //   463: iastore
/*      */     //   464: iconst_0
/*      */     //   465: istore 24
/*      */     //   467: iconst_m1
/*      */     //   468: istore 25
/*      */     //   470: iload 24
/*      */     //   472: iload 23
/*      */     //   474: if_icmpge +117 -> 591
/*      */     //   477: aload_0
/*      */     //   478: iload 24
/*      */     //   480: iload 17
/*      */     //   482: iadd
/*      */     //   483: iaload
/*      */     //   484: istore 27
/*      */     //   486: aload 16
/*      */     //   488: iload 24
/*      */     //   490: baload
/*      */     //   491: sipush 255
/*      */     //   494: iand
/*      */     //   495: istore 25
/*      */     //   497: aload 14
/*      */     //   499: iload 25
/*      */     //   501: dup2
/*      */     //   502: iaload
/*      */     //   503: iconst_1
/*      */     //   504: isub
/*      */     //   505: dup_x2
/*      */     //   506: iastore
/*      */     //   507: dup
/*      */     //   508: istore 26
/*      */     //   510: iload 24
/*      */     //   512: if_icmple +51 -> 563
/*      */     //   515: iload 27
/*      */     //   517: istore 28
/*      */     //   519: iload 25
/*      */     //   521: istore 29
/*      */     //   523: aload_0
/*      */     //   524: iload 26
/*      */     //   526: iload 17
/*      */     //   528: iadd
/*      */     //   529: iaload
/*      */     //   530: istore 27
/*      */     //   532: aload 16
/*      */     //   534: iload 26
/*      */     //   536: baload
/*      */     //   537: sipush 255
/*      */     //   540: iand
/*      */     //   541: istore 25
/*      */     //   543: aload_0
/*      */     //   544: iload 26
/*      */     //   546: iload 17
/*      */     //   548: iadd
/*      */     //   549: iload 28
/*      */     //   551: iastore
/*      */     //   552: aload 16
/*      */     //   554: iload 26
/*      */     //   556: iload 29
/*      */     //   558: i2b
/*      */     //   559: bastore
/*      */     //   560: goto -63 -> 497
/*      */     //   563: aload_0
/*      */     //   564: iload 24
/*      */     //   566: iload 17
/*      */     //   568: iadd
/*      */     //   569: iload 27
/*      */     //   571: iastore
/*      */     //   572: iload 24
/*      */     //   574: aload 13
/*      */     //   576: iload 25
/*      */     //   578: iaload
/*      */     //   579: iadd
/*      */     //   580: istore 24
/*      */     //   582: aload 13
/*      */     //   584: iload 25
/*      */     //   586: iconst_0
/*      */     //   587: iastore
/*      */     //   588: goto -118 -> 470
/*      */     //   591: goto -479 -> 112
/*      */     //   594: return } 
/*  995 */   private static void selectionSort(char[] a, char[] b, int from, int to) { for (int i = from; i < to - 1; i++) {
/*  996 */       int m = i;
/*  997 */       for (int j = i + 1; j < to; j++)
/*  998 */         if ((a[j] < a[m]) || ((a[j] == a[m]) && (b[j] < b[m]))) m = j;
/*  999 */       if (m != i) {
/* 1000 */         char t = a[i];
/* 1001 */         a[i] = a[m];
/* 1002 */         a[m] = t;
/* 1003 */         t = b[i];
/* 1004 */         b[i] = b[m];
/* 1005 */         b[m] = t;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSort(char[] a, char[] b)
/*      */   {
/* 1029 */     radixSort(a, b, 0, a.length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(char[] a, char[] b, int from, int to)
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
/* 1074 */       int signMask = 0;
/* 1075 */       if (length < 50) {
/* 1076 */         selectionSort(a, b, first, first + length);
/*      */       }
/*      */       else {
/* 1079 */         char[] k = level < 2 ? a : b;
/* 1080 */         int shift = (1 - level % 2) * 8;
/*      */ 
/* 1082 */         for (int i = length; i-- != 0; digit[i] = ((byte)(k[(first + i)] >>> shift & 0xFF ^ 0x0)));
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
/*      */           int tmp355_354 = (p + count[i]); p = tmp355_354; pos[i] = tmp355_354;
/*      */         }
/*      */ 
/* 1098 */         int end = length - count[lastUsed];
/* 1099 */         count[lastUsed] = 0;
/*      */ 
/* 1101 */         int i = 0; for (int c = -1; i < end; count[c] = 0) {
/* 1102 */           char t = a[(i + first)];
/* 1103 */           char u = b[(i + first)];
/* 1104 */           c = digit[i] & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             int d;
/* 1105 */             if ((d = pos[c] -= 1) <= i) break;
/* 1106 */             char z = t;
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
/*      */   private static void insertionSortIndirect(int[] perm, char[] a, char[] b, int from, int to)
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
/*      */   public static void radixSortIndirect(int[] perm, char[] a, char[] b, boolean stable)
/*      */   {
/* 1161 */     radixSortIndirect(perm, a, b, 0, perm.length, stable); } 
/*      */   public static void radixSortIndirect(int[] perm, char[] a, char[] b, int from, int to, boolean stable) { // Byte code:
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
/*      */     //   136: ifle +495 -> 631
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
/*      */     //   169: iconst_0
/*      */     //   170: istore 22
/*      */     //   172: iload 20
/*      */     //   174: bipush 50
/*      */     //   176: if_icmpge +19 -> 195
/*      */     //   179: aload_0
/*      */     //   180: aload_1
/*      */     //   181: aload_2
/*      */     //   182: iload 19
/*      */     //   184: iload 19
/*      */     //   186: iload 20
/*      */     //   188: iadd
/*      */     //   189: invokestatic 48	it/unimi/dsi/fastutil/chars/CharArrays:insertionSortIndirect	([I[C[CII)V
/*      */     //   192: goto -58 -> 134
/*      */     //   195: iload 21
/*      */     //   197: iconst_2
/*      */     //   198: if_icmpge +7 -> 205
/*      */     //   201: aload_1
/*      */     //   202: goto +4 -> 206
/*      */     //   205: aload_2
/*      */     //   206: astore 23
/*      */     //   208: iconst_1
/*      */     //   209: iload 21
/*      */     //   211: iconst_2
/*      */     //   212: irem
/*      */     //   213: isub
/*      */     //   214: bipush 8
/*      */     //   216: imul
/*      */     //   217: istore 24
/*      */     //   219: iload 20
/*      */     //   221: istore 25
/*      */     //   223: iload 25
/*      */     //   225: iinc 25 255
/*      */     //   228: ifeq +31 -> 259
/*      */     //   231: aload 18
/*      */     //   233: iload 25
/*      */     //   235: aload 23
/*      */     //   237: aload_0
/*      */     //   238: iload 19
/*      */     //   240: iload 25
/*      */     //   242: iadd
/*      */     //   243: iaload
/*      */     //   244: caload
/*      */     //   245: iload 24
/*      */     //   247: iushr
/*      */     //   248: sipush 255
/*      */     //   251: iand
/*      */     //   252: iconst_0
/*      */     //   253: ixor
/*      */     //   254: i2b
/*      */     //   255: bastore
/*      */     //   256: goto -33 -> 223
/*      */     //   259: iload 20
/*      */     //   261: istore 25
/*      */     //   263: iload 25
/*      */     //   265: iinc 25 255
/*      */     //   268: ifeq +22 -> 290
/*      */     //   271: aload 15
/*      */     //   273: aload 18
/*      */     //   275: iload 25
/*      */     //   277: baload
/*      */     //   278: sipush 255
/*      */     //   281: iand
/*      */     //   282: dup2
/*      */     //   283: iaload
/*      */     //   284: iconst_1
/*      */     //   285: iadd
/*      */     //   286: iastore
/*      */     //   287: goto -24 -> 263
/*      */     //   290: iconst_m1
/*      */     //   291: istore 25
/*      */     //   293: iconst_0
/*      */     //   294: istore 26
/*      */     //   296: iconst_0
/*      */     //   297: istore 27
/*      */     //   299: iload 26
/*      */     //   301: sipush 256
/*      */     //   304: if_icmpge +114 -> 418
/*      */     //   307: aload 15
/*      */     //   309: iload 26
/*      */     //   311: iaload
/*      */     //   312: ifeq +60 -> 372
/*      */     //   315: iload 26
/*      */     //   317: istore 25
/*      */     //   319: iload 21
/*      */     //   321: iconst_3
/*      */     //   322: if_icmpge +50 -> 372
/*      */     //   325: aload 15
/*      */     //   327: iload 26
/*      */     //   329: iaload
/*      */     //   330: iconst_1
/*      */     //   331: if_icmple +41 -> 372
/*      */     //   334: aload 9
/*      */     //   336: iload 10
/*      */     //   338: iinc 10 1
/*      */     //   341: iload 27
/*      */     //   343: iload 19
/*      */     //   345: iadd
/*      */     //   346: iastore
/*      */     //   347: aload 11
/*      */     //   349: iload 12
/*      */     //   351: iinc 12 1
/*      */     //   354: aload 15
/*      */     //   356: iload 26
/*      */     //   358: iaload
/*      */     //   359: iastore
/*      */     //   360: aload 13
/*      */     //   362: iload 14
/*      */     //   364: iinc 14 1
/*      */     //   367: iload 21
/*      */     //   369: iconst_1
/*      */     //   370: iadd
/*      */     //   371: iastore
/*      */     //   372: iload 5
/*      */     //   374: ifeq +22 -> 396
/*      */     //   377: aload 15
/*      */     //   379: iload 26
/*      */     //   381: iload 27
/*      */     //   383: aload 15
/*      */     //   385: iload 26
/*      */     //   387: iaload
/*      */     //   388: iadd
/*      */     //   389: dup
/*      */     //   390: istore 27
/*      */     //   392: iastore
/*      */     //   393: goto +19 -> 412
/*      */     //   396: aload 16
/*      */     //   398: iload 26
/*      */     //   400: iload 27
/*      */     //   402: aload 15
/*      */     //   404: iload 26
/*      */     //   406: iaload
/*      */     //   407: iadd
/*      */     //   408: dup
/*      */     //   409: istore 27
/*      */     //   411: iastore
/*      */     //   412: iinc 26 1
/*      */     //   415: goto -116 -> 299
/*      */     //   418: iload 5
/*      */     //   420: ifeq +65 -> 485
/*      */     //   423: iload 20
/*      */     //   425: istore 26
/*      */     //   427: iload 26
/*      */     //   429: iinc 26 255
/*      */     //   432: ifeq +33 -> 465
/*      */     //   435: aload 17
/*      */     //   437: aload 15
/*      */     //   439: aload 18
/*      */     //   441: iload 26
/*      */     //   443: baload
/*      */     //   444: sipush 255
/*      */     //   447: iand
/*      */     //   448: dup2
/*      */     //   449: iaload
/*      */     //   450: iconst_1
/*      */     //   451: isub
/*      */     //   452: dup_x2
/*      */     //   453: iastore
/*      */     //   454: aload_0
/*      */     //   455: iload 19
/*      */     //   457: iload 26
/*      */     //   459: iadd
/*      */     //   460: iaload
/*      */     //   461: iastore
/*      */     //   462: goto -35 -> 427
/*      */     //   465: aload 17
/*      */     //   467: iconst_0
/*      */     //   468: aload_0
/*      */     //   469: iload 19
/*      */     //   471: iload 20
/*      */     //   473: invokestatic 2	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
/*      */     //   476: aload 15
/*      */     //   478: iconst_0
/*      */     //   479: invokestatic 41	it/unimi/dsi/fastutil/ints/IntArrays:fill	([II)V
/*      */     //   482: goto +146 -> 628
/*      */     //   485: iload 20
/*      */     //   487: aload 15
/*      */     //   489: iload 25
/*      */     //   491: iaload
/*      */     //   492: isub
/*      */     //   493: istore 26
/*      */     //   495: aload 15
/*      */     //   497: iload 25
/*      */     //   499: iconst_0
/*      */     //   500: iastore
/*      */     //   501: iconst_0
/*      */     //   502: istore 27
/*      */     //   504: iconst_m1
/*      */     //   505: istore 28
/*      */     //   507: iload 27
/*      */     //   509: iload 26
/*      */     //   511: if_icmpge +117 -> 628
/*      */     //   514: aload_0
/*      */     //   515: iload 27
/*      */     //   517: iload 19
/*      */     //   519: iadd
/*      */     //   520: iaload
/*      */     //   521: istore 30
/*      */     //   523: aload 18
/*      */     //   525: iload 27
/*      */     //   527: baload
/*      */     //   528: sipush 255
/*      */     //   531: iand
/*      */     //   532: istore 28
/*      */     //   534: aload 16
/*      */     //   536: iload 28
/*      */     //   538: dup2
/*      */     //   539: iaload
/*      */     //   540: iconst_1
/*      */     //   541: isub
/*      */     //   542: dup_x2
/*      */     //   543: iastore
/*      */     //   544: dup
/*      */     //   545: istore 29
/*      */     //   547: iload 27
/*      */     //   549: if_icmple +51 -> 600
/*      */     //   552: iload 30
/*      */     //   554: istore 31
/*      */     //   556: iload 28
/*      */     //   558: istore 32
/*      */     //   560: aload_0
/*      */     //   561: iload 29
/*      */     //   563: iload 19
/*      */     //   565: iadd
/*      */     //   566: iaload
/*      */     //   567: istore 30
/*      */     //   569: aload 18
/*      */     //   571: iload 29
/*      */     //   573: baload
/*      */     //   574: sipush 255
/*      */     //   577: iand
/*      */     //   578: istore 28
/*      */     //   580: aload_0
/*      */     //   581: iload 29
/*      */     //   583: iload 19
/*      */     //   585: iadd
/*      */     //   586: iload 31
/*      */     //   588: iastore
/*      */     //   589: aload 18
/*      */     //   591: iload 29
/*      */     //   593: iload 32
/*      */     //   595: i2b
/*      */     //   596: bastore
/*      */     //   597: goto -63 -> 534
/*      */     //   600: aload_0
/*      */     //   601: iload 27
/*      */     //   603: iload 19
/*      */     //   605: iadd
/*      */     //   606: iload 30
/*      */     //   608: iastore
/*      */     //   609: iload 27
/*      */     //   611: aload 15
/*      */     //   613: iload 28
/*      */     //   615: iaload
/*      */     //   616: iadd
/*      */     //   617: istore 27
/*      */     //   619: aload 15
/*      */     //   621: iload 28
/*      */     //   623: iconst_0
/*      */     //   624: iastore
/*      */     //   625: goto -118 -> 507
/*      */     //   628: goto -494 -> 134
/*      */     //   631: return } 
/* 1263 */   private static void selectionSort(char[][] a, int from, int to, int level) { int layers = a.length;
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
/* 1278 */           char u = a[p][i];
/* 1279 */           a[p][i] = a[p][m];
/* 1280 */           a[p][m] = u;
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSort(char[][] a)
/*      */   {
/* 1305 */     radixSort(a, 0, a[0].length);
/*      */   }
/*      */ 
/*      */   public static void radixSort(char[][] a, int from, int to)
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
/* 1345 */     char[] t = new char[layers];
/* 1346 */     while (offsetPos > 0) {
/* 1347 */       int first = offsetStack[(--offsetPos)];
/* 1348 */       int length = lengthStack[(--lengthPos)];
/* 1349 */       int level = levelStack[(--levelPos)];
/* 1350 */       int signMask = 0;
/* 1351 */       if (length < 50) {
/* 1352 */         selectionSort(a, first, first + length, level);
/*      */       }
/*      */       else {
/* 1355 */         char[] k = a[(level / 2)];
/* 1356 */         int shift = (1 - level % 2) * 8;
/*      */ 
/* 1358 */         for (int i = length; i-- != 0; digit[i] = ((byte)(k[(first + i)] >>> shift & 0xFF ^ 0x0)));
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
/*      */           int tmp409_408 = (p + count[i]); p = tmp409_408; pos[i] = tmp409_408;
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
/* 1382 */               char u = t[p];
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
/*      */   public static char[] shuffle(char[] a, int from, int to, Random random)
/*      */   {
/* 1403 */     for (int i = to - from; i-- != 0; ) {
/* 1404 */       int p = random.nextInt(i + 1);
/* 1405 */       char t = a[(from + i)];
/* 1406 */       a[(from + i)] = a[(from + p)];
/* 1407 */       a[(from + p)] = t;
/*      */     }
/* 1409 */     return a;
/*      */   }
/*      */ 
/*      */   public static char[] shuffle(char[] a, Random random)
/*      */   {
/* 1418 */     for (int i = a.length; i-- != 0; ) {
/* 1419 */       int p = random.nextInt(i + 1);
/* 1420 */       char t = a[i];
/* 1421 */       a[i] = a[p];
/* 1422 */       a[p] = t;
/*      */     }
/* 1424 */     return a;
/*      */   }
/*      */ 
/*      */   public static char[] reverse(char[] a)
/*      */   {
/* 1432 */     int length = a.length;
/* 1433 */     for (int i = length / 2; i-- != 0; ) {
/* 1434 */       char t = a[(length - i - 1)];
/* 1435 */       a[(length - i - 1)] = a[i];
/* 1436 */       a[i] = t;
/*      */     }
/* 1438 */     return a;
/*      */   }
/*      */   private static final class ArrayHashStrategy implements Hash.Strategy<char[]>, Serializable {
/*      */     public static final long serialVersionUID = -7046029254386353129L;
/*      */ 
/*      */     public int hashCode(char[] o) {
/* 1444 */       return java.util.Arrays.hashCode(o);
/*      */     }
/*      */     public boolean equals(char[] a, char[] b) {
/* 1447 */       return CharArrays.equals(a, b);
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharArrays
 * JD-Core Version:    0.6.2
 */