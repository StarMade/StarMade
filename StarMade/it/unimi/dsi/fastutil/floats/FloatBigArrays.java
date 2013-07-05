/*      */ package it.unimi.dsi.fastutil.floats;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.BigArrays;
/*      */ import it.unimi.dsi.fastutil.Hash.Strategy;
/*      */ import it.unimi.dsi.fastutil.bytes.ByteBigArrays;
/*      */ import java.io.Serializable;
/*      */ import java.util.Arrays;
/*      */ import java.util.Random;
/*      */ 
/*      */ public class FloatBigArrays
/*      */ {
/*      */   public static final long ONEOVERPHI = 106039L;
/*   84 */   public static final float[][] EMPTY_BIG_ARRAY = new float[0][];
/*      */ 
/*  564 */   public static final Hash.Strategy HASH_STRATEGY = new BigArrayHashStrategy(null);
/*      */   private static final int SMALL = 7;
/*      */   private static final int MEDIUM = 40;
/*      */   private static final int DIGIT_BITS = 8;
/*      */   private static final int DIGIT_MASK = 255;
/*      */   private static final int DIGITS_PER_ELEMENT = 4;
/*      */ 
/*      */   public static float get(float[][] array, long index)
/*      */   {
/*   92 */     return array[BigArrays.segment(index)][BigArrays.displacement(index)];
/*      */   }
/*      */ 
/*      */   public static void set(float[][] array, long index, float value)
/*      */   {
/*  100 */     array[BigArrays.segment(index)][BigArrays.displacement(index)] = value;
/*      */   }
/*      */ 
/*      */   public static void swap(float[][] array, long first, long second)
/*      */   {
/*  109 */     float t = array[BigArrays.segment(first)][BigArrays.displacement(first)];
/*  110 */     array[BigArrays.segment(first)][BigArrays.displacement(first)] = array[BigArrays.segment(second)][BigArrays.displacement(second)];
/*  111 */     array[BigArrays.segment(second)][BigArrays.displacement(second)] = t;
/*      */   }
/*      */ 
/*      */   public static void add(float[][] array, long index, float incr)
/*      */   {
/*  120 */     array[BigArrays.segment(index)][BigArrays.displacement(index)] += incr;
/*      */   }
/*      */ 
/*      */   public static void mul(float[][] array, long index, float factor)
/*      */   {
/*  129 */     array[BigArrays.segment(index)][BigArrays.displacement(index)] *= factor;
/*      */   }
/*      */ 
/*      */   public static void incr(float[][] array, long index)
/*      */   {
/*  137 */     array[BigArrays.segment(index)][BigArrays.displacement(index)] += 1.0F;
/*      */   }
/*      */ 
/*      */   public static void decr(float[][] array, long index)
/*      */   {
/*  145 */     array[BigArrays.segment(index)][BigArrays.displacement(index)] -= 1.0F;
/*      */   }
/*      */ 
/*      */   public static long length(float[][] array)
/*      */   {
/*  153 */     int length = array.length;
/*  154 */     return length == 0 ? 0L : BigArrays.start(length - 1) + array[(length - 1)].length;
/*      */   }
/*      */ 
/*      */   public static void copy(float[][] srcArray, long srcPos, float[][] destArray, long destPos, long length)
/*      */   {
/*  166 */     if (destPos <= srcPos) {
/*  167 */       int srcSegment = BigArrays.segment(srcPos);
/*  168 */       int destSegment = BigArrays.segment(destPos);
/*  169 */       int srcDispl = BigArrays.displacement(srcPos);
/*  170 */       int destDispl = BigArrays.displacement(destPos);
/*      */ 
/*  172 */       while (length > 0L) {
/*  173 */         int l = (int)Math.min(length, Math.min(srcArray[srcSegment].length - srcDispl, destArray[destSegment].length - destDispl));
/*  174 */         System.arraycopy(srcArray[srcSegment], srcDispl, destArray[destSegment], destDispl, l);
/*  175 */         if (srcDispl += l == 134217728) {
/*  176 */           srcDispl = 0;
/*  177 */           srcSegment++;
/*      */         }
/*  179 */         if (destDispl += l == 134217728) {
/*  180 */           destDispl = 0;
/*  181 */           destSegment++;
/*      */         }
/*  183 */         length -= l;
/*      */       }
/*      */     }
/*      */     else {
/*  187 */       int srcSegment = BigArrays.segment(srcPos + length);
/*  188 */       int destSegment = BigArrays.segment(destPos + length);
/*  189 */       int srcDispl = BigArrays.displacement(srcPos + length);
/*  190 */       int destDispl = BigArrays.displacement(destPos + length);
/*      */ 
/*  192 */       while (length > 0L) {
/*  193 */         if (srcDispl == 0) {
/*  194 */           srcDispl = 134217728;
/*  195 */           srcSegment--;
/*      */         }
/*  197 */         if (destDispl == 0) {
/*  198 */           destDispl = 134217728;
/*  199 */           destSegment--;
/*      */         }
/*  201 */         int l = (int)Math.min(length, Math.min(srcDispl, destDispl));
/*  202 */         System.arraycopy(srcArray[srcSegment], srcDispl - l, destArray[destSegment], destDispl - l, l);
/*  203 */         srcDispl -= l;
/*  204 */         destDispl -= l;
/*  205 */         length -= l;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void copyFromBig(float[][] srcArray, long srcPos, float[] destArray, int destPos, int length)
/*      */   {
/*  218 */     int srcSegment = BigArrays.segment(srcPos);
/*  219 */     int srcDispl = BigArrays.displacement(srcPos);
/*      */ 
/*  221 */     while (length > 0) {
/*  222 */       int l = Math.min(srcArray[srcSegment].length - srcDispl, length);
/*  223 */       System.arraycopy(srcArray[srcSegment], srcDispl, destArray, destPos, l);
/*  224 */       if (srcDispl += l == 134217728) {
/*  225 */         srcDispl = 0;
/*  226 */         srcSegment++;
/*      */       }
/*  228 */       destPos += l;
/*  229 */       length -= l;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void copyToBig(float[] srcArray, int srcPos, float[][] destArray, long destPos, long length)
/*      */   {
/*  241 */     int destSegment = BigArrays.segment(destPos);
/*  242 */     int destDispl = BigArrays.displacement(destPos);
/*      */ 
/*  244 */     while (length > 0L) {
/*  245 */       int l = (int)Math.min(destArray[destSegment].length - destDispl, length);
/*  246 */       System.arraycopy(srcArray, srcPos, destArray[destSegment], destDispl, l);
/*  247 */       if (destDispl += l == 134217728) {
/*  248 */         destDispl = 0;
/*  249 */         destSegment++;
/*      */       }
/*  251 */       srcPos += l;
/*  252 */       length -= l;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static float[][] newBigArray(long length)
/*      */   {
/*  261 */     if (length == 0L) return EMPTY_BIG_ARRAY;
/*  262 */     int baseLength = (int)((length + 134217727L) / 134217728L);
/*  263 */     float[][] base = new float[baseLength][];
/*  264 */     int residual = (int)(length & 0x7FFFFFF);
/*  265 */     if (residual != 0) {
/*  266 */       for (int i = 0; i < baseLength - 1; i++) base[i] = new float[134217728];
/*  267 */       base[(baseLength - 1)] = new float[residual];
/*      */     } else {
/*  269 */       for (int i = 0; i < baseLength; i++) base[i] = new float[134217728]; 
/*      */     }
/*  270 */     return base;
/*      */   }
/*      */ 
/*      */   public static float[][] wrap(float[] array)
/*      */   {
/*  280 */     if (array.length == 0) return EMPTY_BIG_ARRAY;
/*  281 */     if (array.length <= 134217728) return new float[][] { array };
/*  282 */     float[][] bigArray = newBigArray(array.length);
/*  283 */     for (int i = 0; i < bigArray.length; i++) System.arraycopy(array, (int)BigArrays.start(i), bigArray[i], 0, bigArray[i].length);
/*  284 */     return bigArray;
/*      */   }
/*      */ 
/*      */   public static float[][] ensureCapacity(float[][] array, long length)
/*      */   {
/*  301 */     return ensureCapacity(array, length, length(array));
/*      */   }
/*      */ 
/*      */   public static float[][] ensureCapacity(float[][] array, long length, long preserve)
/*      */   {
/*  316 */     long oldLength = length(array);
/*  317 */     if (length > oldLength) {
/*  318 */       int valid = array.length - ((array.length == 0) || ((array.length > 0) && (array[(array.length - 1)].length == 134217728)) ? 0 : 1);
/*  319 */       int baseLength = (int)((length + 134217727L) / 134217728L);
/*  320 */       float[][] base = (float[][])Arrays.copyOf(array, baseLength);
/*  321 */       int residual = (int)(length & 0x7FFFFFF);
/*  322 */       if (residual != 0) {
/*  323 */         for (int i = valid; i < baseLength - 1; i++) base[i] = new float[134217728];
/*  324 */         base[(baseLength - 1)] = new float[residual];
/*      */       } else {
/*  326 */         for (int i = valid; i < baseLength; i++) base[i] = new float[134217728]; 
/*      */       }
/*  327 */       if (preserve - valid * 134217728L > 0L) copy(array, valid * 134217728L, base, valid * 134217728L, preserve - valid * 134217728L);
/*  328 */       return base;
/*      */     }
/*  330 */     return array;
/*      */   }
/*      */ 
/*      */   public static float[][] grow(float[][] array, long length)
/*      */   {
/*  351 */     long oldLength = length(array);
/*  352 */     return length > oldLength ? grow(array, length, oldLength) : array;
/*      */   }
/*      */ 
/*      */   public static float[][] grow(float[][] array, long length, long preserve)
/*      */   {
/*  374 */     long oldLength = length(array);
/*  375 */     return length > oldLength ? ensureCapacity(array, Math.max(106039L * oldLength >>> 16, length), preserve) : array;
/*      */   }
/*      */ 
/*      */   public static float[][] trim(float[][] array, long length)
/*      */   {
/*  391 */     long oldLength = length(array);
/*  392 */     if (length >= oldLength) return array;
/*  393 */     int baseLength = (int)((length + 134217727L) / 134217728L);
/*  394 */     float[][] base = (float[][])Arrays.copyOf(array, baseLength);
/*  395 */     int residual = (int)(length & 0x7FFFFFF);
/*  396 */     if (residual != 0) base[(baseLength - 1)] = FloatArrays.trim(base[(baseLength - 1)], residual);
/*  397 */     return base;
/*      */   }
/*      */ 
/*      */   public static float[][] setLength(float[][] array, long length)
/*      */   {
/*  416 */     long oldLength = length(array);
/*  417 */     if (length == oldLength) return array;
/*  418 */     if (length < oldLength) return trim(array, length);
/*  419 */     return ensureCapacity(array, length);
/*      */   }
/*      */ 
/*      */   public static float[][] copy(float[][] array, long offset, long length)
/*      */   {
/*  429 */     ensureOffsetLength(array, offset, length);
/*  430 */     float[][] a = newBigArray(length);
/*      */ 
/*  432 */     copy(array, offset, a, 0L, length);
/*  433 */     return a;
/*      */   }
/*      */ 
/*      */   public static float[][] copy(float[][] array)
/*      */   {
/*  441 */     float[][] base = (float[][])array.clone();
/*  442 */     for (int i = base.length; i-- != 0; base[i] = ((float[])array[i].clone()));
/*  443 */     return base;
/*      */   }
/*      */ 
/*      */   public static void fill(float[][] array, float value)
/*      */   {
/*  454 */     for (int i = array.length; i-- != 0; FloatArrays.fill(array[i], value));
/*      */   }
/*      */ 
/*      */   public static void fill(float[][] array, long from, long to, float value)
/*      */   {
/*  468 */     long length = length(array);
/*  469 */     BigArrays.ensureFromTo(length, from, to);
/*  470 */     int fromSegment = BigArrays.segment(from);
/*  471 */     int toSegment = BigArrays.segment(to);
/*  472 */     int fromDispl = BigArrays.displacement(from);
/*  473 */     int toDispl = BigArrays.displacement(to);
/*  474 */     if (fromSegment == toSegment) {
/*  475 */       FloatArrays.fill(array[fromSegment], fromDispl, toDispl, value);
/*  476 */       return;
/*      */     }
/*  478 */     if (toDispl != 0) FloatArrays.fill(array[toSegment], 0, toDispl, value); while (true) {
/*  479 */       toSegment--; if (toSegment <= fromSegment) break; FloatArrays.fill(array[toSegment], value);
/*  480 */     }FloatArrays.fill(array[fromSegment], fromDispl, 134217728, value);
/*      */   }
/*      */ 
/*      */   public static boolean equals(float[][] a1, float[][] a2)
/*      */   {
/*  492 */     if (length(a1) != length(a2)) return false; float[] t;
/*      */     float[] u;
/*      */     int j;
/*      */     do { int i = a1.length;
/*      */ 
/*  499 */       while (j-- == 0)
/*      */       {
/*  495 */         if (i-- == 0) break;
/*  496 */         t = a1[i];
/*  497 */         u = a2[i];
/*  498 */         j = t.length;
/*      */       } } while (t[j] == u[j]); return false;
/*      */ 
/*  501 */     return true;
/*      */   }
/*      */ 
/*      */   public static String toString(float[][] a)
/*      */   {
/*  510 */     if (a == null) return "null";
/*  511 */     long last = length(a) - 1L;
/*  512 */     if (last == -1L) return "[]";
/*  513 */     StringBuilder b = new StringBuilder();
/*  514 */     b.append('[');
/*  515 */     for (long i = 0L; ; i += 1L) {
/*  516 */       b.append(String.valueOf(get(a, i)));
/*  517 */       if (i == last) return b.append(']').toString();
/*  518 */       b.append(", ");
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void ensureFromTo(float[][] a, long from, long to)
/*      */   {
/*  532 */     BigArrays.ensureFromTo(length(a), from, to);
/*      */   }
/*      */ 
/*      */   public static void ensureOffsetLength(float[][] a, long offset, long length)
/*      */   {
/*  545 */     BigArrays.ensureOffsetLength(length(a), offset, length);
/*      */   }
/*      */ 
/*      */   private static void vecSwap(float[][] x, long a, long b, long n)
/*      */   {
/*  568 */     for (int i = 0; i < n; b += 1L) { swap(x, a, b); i++; a += 1L; } 
/*      */   }
/*      */ 
/*  571 */   private static long med3(float[][] x, long a, long b, long c, FloatComparator comp) { int ab = comp.compare(get(x, a), get(x, b));
/*  572 */     int ac = comp.compare(get(x, a), get(x, c));
/*  573 */     int bc = comp.compare(get(x, b), get(x, c));
/*  574 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*      */   }
/*      */ 
/*      */   private static void selectionSort(float[][] a, long from, long to, FloatComparator comp)
/*      */   {
/*  579 */     for (long i = from; i < to - 1L; i += 1L) {
/*  580 */       long m = i;
/*  581 */       for (long j = i + 1L; j < to; j += 1L) if (comp.compare(get(a, j), get(a, m)) < 0) m = j;
/*  582 */       if (m != i) swap(a, i, m);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void quickSort(float[][] x, long from, long to, FloatComparator comp)
/*      */   {
/*  598 */     long len = to - from;
/*      */ 
/*  600 */     if (len < 7L) {
/*  601 */       for (long i = from; i < to; i += 1L)
/*  602 */         for (long j = i; (j > from) && (comp.compare(get(x, j - 1L), get(x, j)) > 0); j -= 1L) swap(x, j, j - 1L);
/*  603 */       return;
/*      */     }
/*      */ 
/*  606 */     long m = from + len / 2L;
/*  607 */     if (len > 7L) {
/*  608 */       long l = from;
/*  609 */       long n = to - 1L;
/*  610 */       if (len > 40L) {
/*  611 */         long s = len / 8L;
/*  612 */         l = med3(x, l, l + s, l + 2L * s, comp);
/*  613 */         m = med3(x, m - s, m, m + s, comp);
/*  614 */         n = med3(x, n - 2L * s, n - s, n, comp);
/*      */       }
/*  616 */       m = med3(x, l, m, n, comp);
/*      */     }
/*  618 */     float v = get(x, m);
/*      */ 
/*  620 */     long a = from; long b = a; long c = to - 1L; long d = c;
/*      */     while (true)
/*      */     {
/*      */       int comparison;
/*  623 */       if ((b <= c) && ((comparison = comp.compare(get(x, b), v)) <= 0)) {
/*  624 */         if (comparison == 0) swap(x, a++, b);
/*  625 */         b += 1L;
/*      */       }
/*      */       else
/*      */       {
/*      */         int comparison;
/*  627 */         while ((c >= b) && ((comparison = comp.compare(get(x, c), v)) >= 0)) {
/*  628 */           if (comparison == 0) swap(x, c, d--);
/*  629 */           c -= 1L;
/*      */         }
/*  631 */         if (b > c) break;
/*  632 */         swap(x, b++, c--);
/*      */       }
/*      */     }
/*  635 */     long n = to;
/*  636 */     long s = Math.min(a - from, b - a);
/*  637 */     vecSwap(x, from, b - s, s);
/*  638 */     s = Math.min(d - c, n - d - 1L);
/*  639 */     vecSwap(x, b, n - s, s);
/*      */ 
/*  641 */     if ((s = b - a) > 1L) quickSort(x, from, from + s, comp);
/*  642 */     if ((s = d - c) > 1L) quickSort(x, n - s, n, comp); 
/*      */   }
/*      */ 
/*      */   private static long med3(float[][] x, long a, long b, long c)
/*      */   {
/*  646 */     int ab = get(x, a) == get(x, b) ? 0 : get(x, a) < get(x, b) ? -1 : 1;
/*  647 */     int ac = get(x, a) == get(x, c) ? 0 : get(x, a) < get(x, c) ? -1 : 1;
/*  648 */     int bc = get(x, b) == get(x, c) ? 0 : get(x, b) < get(x, c) ? -1 : 1;
/*  649 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*      */   }
/*      */ 
/*      */   private static void selectionSort(float[][] a, long from, long to)
/*      */   {
/*  654 */     for (long i = from; i < to - 1L; i += 1L) {
/*  655 */       long m = i;
/*  656 */       for (long j = i + 1L; j < to; j += 1L) if (get(a, j) < get(a, m)) m = j;
/*  657 */       if (m != i) swap(a, i, m);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void quickSort(float[][] x, FloatComparator comp)
/*      */   {
/*  672 */     quickSort(x, 0L, length(x), comp);
/*      */   }
/*      */ 
/*      */   public static void quickSort(float[][] x, long from, long to)
/*      */   {
/*  686 */     long len = to - from;
/*      */ 
/*  688 */     if (len < 7L) {
/*  689 */       for (long i = from; i < to; i += 1L)
/*  690 */         for (long j = i; j > from; j -= 1L) { if ((get(x, j - 1L) == get(x, j) ? 0 : get(x, j - 1L) < get(x, j) ? -1 : 1) <= 0) break; swap(x, j, j - 1L); }
/*  691 */       return;
/*      */     }
/*      */ 
/*  694 */     long m = from + len / 2L;
/*  695 */     if (len > 7L) {
/*  696 */       long l = from;
/*  697 */       long n = to - 1L;
/*  698 */       if (len > 40L) {
/*  699 */         long s = len / 8L;
/*  700 */         l = med3(x, l, l + s, l + 2L * s);
/*  701 */         m = med3(x, m - s, m, m + s);
/*  702 */         n = med3(x, n - 2L * s, n - s, n);
/*      */       }
/*  704 */       m = med3(x, l, m, n);
/*      */     }
/*  706 */     float v = get(x, m);
/*      */ 
/*  708 */     long a = from; long b = a; long c = to - 1L; long d = c;
/*      */     while (true)
/*      */     {
/*  711 */       if (b <= c)
/*      */       {
/*      */         int comparison;
/*  711 */         if ((comparison = get(x, b) == v ? 0 : get(x, b) < v ? -1 : 1) <= 0) {
/*  712 */           if (comparison == 0) swap(x, a++, b);
/*  713 */           b += 1L;
/*      */         }
/*      */       } else { while (c >= b)
/*      */         {
/*      */           int comparison;
/*  715 */           if ((comparison = get(x, c) == v ? 0 : get(x, c) < v ? -1 : 1) < 0) break;
/*  716 */           if (comparison == 0) swap(x, c, d--);
/*  717 */           c -= 1L;
/*      */         }
/*  719 */         if (b > c) break;
/*  720 */         swap(x, b++, c--);
/*      */       }
/*      */     }
/*  723 */     long n = to;
/*  724 */     long s = Math.min(a - from, b - a);
/*  725 */     vecSwap(x, from, b - s, s);
/*  726 */     s = Math.min(d - c, n - d - 1L);
/*  727 */     vecSwap(x, b, n - s, s);
/*      */ 
/*  729 */     if ((s = b - a) > 1L) quickSort(x, from, from + s);
/*  730 */     if ((s = d - c) > 1L) quickSort(x, n - s, n);
/*      */   }
/*      */ 
/*      */   public static void quickSort(float[][] x)
/*      */   {
/*  742 */     quickSort(x, 0L, length(x));
/*      */   }
/*      */ 
/*      */   public static long binarySearch(float[][] a, long from, long to, float key)
/*      */   {
/*  767 */     to -= 1L;
/*  768 */     while (from <= to) {
/*  769 */       long mid = from + to >>> 1;
/*  770 */       float midVal = get(a, mid);
/*  771 */       if (midVal < key) from = mid + 1L;
/*  772 */       else if (midVal > key) to = mid - 1L; else
/*  773 */         return mid;
/*      */     }
/*  775 */     return -(from + 1L);
/*      */   }
/*      */ 
/*      */   public static long binarySearch(float[][] a, float key)
/*      */   {
/*  796 */     return binarySearch(a, 0L, length(a), key);
/*      */   }
/*      */ 
/*      */   public static long binarySearch(float[][] a, long from, long to, float key, FloatComparator c)
/*      */   {
/*  821 */     to -= 1L;
/*  822 */     while (from <= to) {
/*  823 */       long mid = from + to >>> 1;
/*  824 */       float midVal = get(a, mid);
/*  825 */       int cmp = c.compare(midVal, key);
/*  826 */       if (cmp < 0) from = mid + 1L;
/*  827 */       else if (cmp > 0) to = mid - 1L; else
/*  828 */         return mid;
/*      */     }
/*  830 */     return -(from + 1L);
/*      */   }
/*      */ 
/*      */   public static long binarySearch(float[][] a, float key, FloatComparator c)
/*      */   {
/*  852 */     return binarySearch(a, 0L, length(a), key, c);
/*      */   }
/*      */ 
/*      */   private static final long fixFloat(float f)
/*      */   {
/*  862 */     long i = Float.floatToRawIntBits(f);
/*  863 */     return i >= 0L ? i : i ^ 0x7FFFFFFF;
/*      */   }
/*      */ 
/*      */   public static void radixSort(float[][] a)
/*      */   {
/*  882 */     radixSort(a, 0L, length(a));
/*      */   }
/*      */ 
/*      */   public static void radixSort(float[][] a, long from, long to)
/*      */   {
/*  903 */     int maxLevel = 3;
/*  904 */     int stackSize = 766;
/*  905 */     long[] offsetStack = new long[766];
/*  906 */     int offsetPos = 0;
/*  907 */     long[] lengthStack = new long[766];
/*  908 */     int lengthPos = 0;
/*  909 */     int[] levelStack = new int[766];
/*  910 */     int levelPos = 0;
/*  911 */     offsetStack[(offsetPos++)] = from;
/*  912 */     lengthStack[(lengthPos++)] = (to - from);
/*  913 */     levelStack[(levelPos++)] = 0;
/*  914 */     long[] count = new long[256];
/*  915 */     long[] pos = new long[256];
/*  916 */     byte[][] digit = ByteBigArrays.newBigArray(to - from);
/*  917 */     while (offsetPos > 0) {
/*  918 */       long first = offsetStack[(--offsetPos)];
/*  919 */       long length = lengthStack[(--lengthPos)];
/*  920 */       int level = levelStack[(--levelPos)];
/*  921 */       int signMask = level % 4 == 0 ? 128 : 0;
/*  922 */       if (length < 40L) {
/*  923 */         selectionSort(a, first, first + length);
/*      */       }
/*      */       else {
/*  926 */         int shift = (3 - level % 4) * 8;
/*      */ 
/*  928 */         for (long i = length; i-- != 0L; ByteBigArrays.set(digit, i, (byte)(int)(fixFloat(get(a, first + i)) >>> shift & 0xFF ^ signMask)));
/*  929 */         for (long i = length; i-- != 0L; count[(ByteBigArrays.get(digit, i) & 0xFF)] += 1L);
/*  931 */         int lastUsed = -1;
/*  932 */         long p = 0L;
/*  933 */         for (int i = 0; i < 256; i++) {
/*  934 */           if (count[i] != 0L) {
/*  935 */             lastUsed = i;
/*  936 */             if ((level < 3) && (count[i] > 1L))
/*      */             {
/*  938 */               offsetStack[(offsetPos++)] = (p + first);
/*  939 */               lengthStack[(lengthPos++)] = count[i];
/*  940 */               levelStack[(levelPos++)] = (level + 1);
/*      */             }
/*      */           }
/*      */           long tmp359_358 = (p + count[i]); p = tmp359_358; pos[i] = tmp359_358;
/*      */         }
/*      */ 
/*  946 */         long end = length - count[lastUsed];
/*  947 */         count[lastUsed] = 0L;
/*      */ 
/*  949 */         int c = -1;
/*  950 */         for (long i = 0L; i < end; count[c] = 0L) {
/*  951 */           float t = get(a, i + first);
/*  952 */           c = ByteBigArrays.get(digit, i) & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             long d;
/*  953 */             if ((d = pos[c] -= 1L) <= i) break;
/*  954 */             float z = t;
/*  955 */             int zz = c;
/*  956 */             t = get(a, d + first);
/*  957 */             c = ByteBigArrays.get(digit, d) & 0xFF;
/*  958 */             set(a, d + first, z);
/*  959 */             ByteBigArrays.set(digit, d, (byte)zz);
/*      */           }
/*  961 */           set(a, i + first, t);
/*      */ 
/*  950 */           i += count[c];
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void selectionSort(float[][] a, float[][] b, long from, long to)
/*      */   {
/*  966 */     for (long i = from; i < to - 1L; i += 1L) {
/*  967 */       long m = i;
/*  968 */       for (long j = i + 1L; j < to; j += 1L)
/*  969 */         if ((get(a, j) < get(a, m)) || ((get(a, j) == get(a, m)) && (get(b, j) < get(b, m)))) m = j;
/*  970 */       if (m != i) {
/*  971 */         float t = get(a, i);
/*  972 */         set(a, i, get(a, m));
/*  973 */         set(a, m, t);
/*  974 */         t = get(b, i);
/*  975 */         set(b, i, get(b, m));
/*  976 */         set(b, m, t);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void radixSort(float[][] a, float[][] b)
/*      */   {
/* 1000 */     radixSort(a, b, 0L, length(a));
/*      */   }
/*      */ 
/*      */   public static void radixSort(float[][] a, float[][] b, long from, long to)
/*      */   {
/* 1025 */     int layers = 2;
/* 1026 */     if (length(a) != length(b)) throw new IllegalArgumentException("Array size mismatch.");
/* 1027 */     int maxLevel = 7;
/* 1028 */     int stackSize = 1786;
/* 1029 */     long[] offsetStack = new long[1786];
/* 1030 */     int offsetPos = 0;
/* 1031 */     long[] lengthStack = new long[1786];
/* 1032 */     int lengthPos = 0;
/* 1033 */     int[] levelStack = new int[1786];
/* 1034 */     int levelPos = 0;
/* 1035 */     offsetStack[(offsetPos++)] = from;
/* 1036 */     lengthStack[(lengthPos++)] = (to - from);
/* 1037 */     levelStack[(levelPos++)] = 0;
/* 1038 */     long[] count = new long[256];
/* 1039 */     long[] pos = new long[256];
/* 1040 */     byte[][] digit = ByteBigArrays.newBigArray(to - from);
/* 1041 */     while (offsetPos > 0) {
/* 1042 */       long first = offsetStack[(--offsetPos)];
/* 1043 */       long length = lengthStack[(--lengthPos)];
/* 1044 */       int level = levelStack[(--levelPos)];
/* 1045 */       int signMask = level % 4 == 0 ? 128 : 0;
/* 1046 */       if (length < 40L) {
/* 1047 */         selectionSort(a, b, first, first + length);
/*      */       }
/*      */       else {
/* 1050 */         float[][] k = level < 4 ? a : b;
/* 1051 */         int shift = (3 - level % 4) * 8;
/*      */ 
/* 1053 */         for (long i = length; i-- != 0L; ByteBigArrays.set(digit, i, (byte)(int)(fixFloat(get(k, first + i)) >>> shift & 0xFF ^ signMask)));
/* 1054 */         for (long i = length; i-- != 0L; count[(ByteBigArrays.get(digit, i) & 0xFF)] += 1L);
/* 1056 */         int lastUsed = -1;
/* 1057 */         long p = 0L;
/* 1058 */         for (int i = 0; i < 256; i++) {
/* 1059 */           if (count[i] != 0L) {
/* 1060 */             lastUsed = i;
/* 1061 */             if ((level < 7) && (count[i] > 1L)) {
/* 1062 */               offsetStack[(offsetPos++)] = (p + first);
/* 1063 */               lengthStack[(lengthPos++)] = count[i];
/* 1064 */               levelStack[(levelPos++)] = (level + 1);
/*      */             }
/*      */           }
/*      */           long tmp403_402 = (p + count[i]); p = tmp403_402; pos[i] = tmp403_402;
/*      */         }
/*      */ 
/* 1070 */         long end = length - count[lastUsed];
/* 1071 */         count[lastUsed] = 0L;
/*      */ 
/* 1073 */         int c = -1;
/* 1074 */         for (long i = 0L; i < end; count[c] = 0L) {
/* 1075 */           float t = get(a, i + first);
/* 1076 */           float u = get(b, i + first);
/* 1077 */           c = ByteBigArrays.get(digit, i) & 0xFF;
/*      */           while (true)
/*      */           {
/*      */             long d;
/* 1078 */             if ((d = pos[c] -= 1L) <= i) break;
/* 1079 */             float z = t;
/* 1080 */             int zz = c;
/* 1081 */             t = get(a, d + first);
/* 1082 */             set(a, d + first, z);
/* 1083 */             z = u;
/* 1084 */             u = get(b, d + first);
/* 1085 */             set(b, d + first, z);
/* 1086 */             c = ByteBigArrays.get(digit, d) & 0xFF;
/* 1087 */             ByteBigArrays.set(digit, d, (byte)zz);
/*      */           }
/* 1089 */           set(a, i + first, t);
/* 1090 */           set(b, i + first, u);
/*      */ 
/* 1074 */           i += count[c];
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static float[][] shuffle(float[][] a, long from, long to, Random random)
/*      */   {
/* 1103 */     for (long i = to - from; i-- != 0L; ) {
/* 1104 */       long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/* 1105 */       float t = get(a, from + i);
/* 1106 */       set(a, from + i, get(a, from + p));
/* 1107 */       set(a, from + p, t);
/*      */     }
/* 1109 */     return a;
/*      */   }
/*      */ 
/*      */   public static float[][] shuffle(float[][] a, Random random)
/*      */   {
/* 1118 */     for (long i = length(a); i-- != 0L; ) {
/* 1119 */       long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/* 1120 */       float t = get(a, i);
/* 1121 */       set(a, i, get(a, p));
/* 1122 */       set(a, p, t);
/*      */     }
/* 1124 */     return a;
/*      */   }
/*      */ 
/*      */   private static final class BigArrayHashStrategy
/*      */     implements Hash.Strategy<float[][]>, Serializable
/*      */   {
/*      */     public static final long serialVersionUID = -7046029254386353129L;
/*      */ 
/*      */     public int hashCode(float[][] o)
/*      */     {
/*  551 */       return Arrays.deepHashCode(o);
/*      */     }
/*      */     public boolean equals(float[][] a, float[][] b) {
/*  554 */       return FloatBigArrays.equals(a, b);
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatBigArrays
 * JD-Core Version:    0.6.2
 */