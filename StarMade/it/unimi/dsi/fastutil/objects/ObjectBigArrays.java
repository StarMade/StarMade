/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.BigArrays;
/*     */ import it.unimi.dsi.fastutil.Hash.Strategy;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ObjectBigArrays
/*     */ {
/*     */   public static final long ONEOVERPHI = 106039L;
/*  95 */   public static final Object[][] EMPTY_BIG_ARRAY = new Object[0][];
/*     */ 
/* 591 */   public static final Hash.Strategy HASH_STRATEGY = new BigArrayHashStrategy(null);
/*     */   private static final int SMALL = 7;
/*     */   private static final int MEDIUM = 40;
/*     */ 
/*     */   public static <K> K get(K[][] array, long index)
/*     */   {
/* 104 */     return array[BigArrays.segment(index)][BigArrays.displacement(index)];
/*     */   }
/*     */ 
/*     */   public static <K> void set(K[][] array, long index, K value)
/*     */   {
/* 113 */     array[BigArrays.segment(index)][BigArrays.displacement(index)] = value;
/*     */   }
/*     */ 
/*     */   public static <K> void swap(K[][] array, long first, long second)
/*     */   {
/* 123 */     Object t = array[BigArrays.segment(first)][BigArrays.displacement(first)];
/* 124 */     array[BigArrays.segment(first)][BigArrays.displacement(first)] = array[BigArrays.segment(second)][BigArrays.displacement(second)];
/* 125 */     array[BigArrays.segment(second)][BigArrays.displacement(second)] = t;
/*     */   }
/*     */ 
/*     */   public static <K> long length(K[][] array)
/*     */   {
/* 133 */     int length = array.length;
/* 134 */     return length == 0 ? 0L : BigArrays.start(length - 1) + array[(length - 1)].length;
/*     */   }
/*     */ 
/*     */   public static <K> void copy(K[][] srcArray, long srcPos, K[][] destArray, long destPos, long length)
/*     */   {
/* 146 */     if (destPos <= srcPos) {
/* 147 */       int srcSegment = BigArrays.segment(srcPos);
/* 148 */       int destSegment = BigArrays.segment(destPos);
/* 149 */       int srcDispl = BigArrays.displacement(srcPos);
/* 150 */       int destDispl = BigArrays.displacement(destPos);
/*     */ 
/* 152 */       while (length > 0L) {
/* 153 */         int l = (int)Math.min(length, Math.min(srcArray[srcSegment].length - srcDispl, destArray[destSegment].length - destDispl));
/* 154 */         System.arraycopy(srcArray[srcSegment], srcDispl, destArray[destSegment], destDispl, l);
/* 155 */         if (srcDispl += l == 134217728) {
/* 156 */           srcDispl = 0;
/* 157 */           srcSegment++;
/*     */         }
/* 159 */         if (destDispl += l == 134217728) {
/* 160 */           destDispl = 0;
/* 161 */           destSegment++;
/*     */         }
/* 163 */         length -= l;
/*     */       }
/*     */     }
/*     */     else {
/* 167 */       int srcSegment = BigArrays.segment(srcPos + length);
/* 168 */       int destSegment = BigArrays.segment(destPos + length);
/* 169 */       int srcDispl = BigArrays.displacement(srcPos + length);
/* 170 */       int destDispl = BigArrays.displacement(destPos + length);
/*     */ 
/* 172 */       while (length > 0L) {
/* 173 */         if (srcDispl == 0) {
/* 174 */           srcDispl = 134217728;
/* 175 */           srcSegment--;
/*     */         }
/* 177 */         if (destDispl == 0) {
/* 178 */           destDispl = 134217728;
/* 179 */           destSegment--;
/*     */         }
/* 181 */         int l = (int)Math.min(length, Math.min(srcDispl, destDispl));
/* 182 */         System.arraycopy(srcArray[srcSegment], srcDispl - l, destArray[destSegment], destDispl - l, l);
/* 183 */         srcDispl -= l;
/* 184 */         destDispl -= l;
/* 185 */         length -= l;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static <K> void copyFromBig(K[][] srcArray, long srcPos, K[] destArray, int destPos, int length)
/*     */   {
/* 198 */     int srcSegment = BigArrays.segment(srcPos);
/* 199 */     int srcDispl = BigArrays.displacement(srcPos);
/*     */ 
/* 201 */     while (length > 0) {
/* 202 */       int l = Math.min(srcArray[srcSegment].length - srcDispl, length);
/* 203 */       System.arraycopy(srcArray[srcSegment], srcDispl, destArray, destPos, l);
/* 204 */       if (srcDispl += l == 134217728) {
/* 205 */         srcDispl = 0;
/* 206 */         srcSegment++;
/*     */       }
/* 208 */       destPos += l;
/* 209 */       length -= l;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static <K> void copyToBig(K[] srcArray, int srcPos, K[][] destArray, long destPos, long length)
/*     */   {
/* 221 */     int destSegment = BigArrays.segment(destPos);
/* 222 */     int destDispl = BigArrays.displacement(destPos);
/*     */ 
/* 224 */     while (length > 0L) {
/* 225 */       int l = (int)Math.min(destArray[destSegment].length - destDispl, length);
/* 226 */       System.arraycopy(srcArray, srcPos, destArray[destSegment], destDispl, l);
/* 227 */       if (destDispl += l == 134217728) {
/* 228 */         destDispl = 0;
/* 229 */         destSegment++;
/*     */       }
/* 231 */       srcPos += l;
/* 232 */       length -= l;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static <K> K[][] newBigArray(K[][] prototype, long length)
/*     */   {
/* 247 */     return (Object[][])newBigArray(prototype.getClass().getComponentType(), length);
/*     */   }
/*     */ 
/*     */   private static Object[][] newBigArray(Class<?> componentType, long length)
/*     */   {
/* 261 */     if ((length == 0L) && (componentType == [Ljava.lang.Object.class)) return EMPTY_BIG_ARRAY;
/* 262 */     int baseLength = (int)((length + 134217727L) / 134217728L);
/* 263 */     Object[][] base = (Object[][])Array.newInstance(componentType, baseLength);
/* 264 */     int residual = (int)(length & 0x7FFFFFF);
/* 265 */     if (residual != 0) {
/* 266 */       for (int i = 0; i < baseLength - 1; i++) base[i] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), 134217728));
/* 267 */       base[(baseLength - 1)] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), residual));
/*     */     } else {
/* 269 */       for (int i = 0; i < baseLength; i++) base[i] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), 134217728)); 
/*     */     }
/* 270 */     return base;
/*     */   }
/*     */ 
/*     */   public static Object[][] newBigArray(long length)
/*     */   {
/* 278 */     if (length == 0L) return EMPTY_BIG_ARRAY;
/* 279 */     int baseLength = (int)((length + 134217727L) / 134217728L);
/* 280 */     Object[][] base = new Object[baseLength][];
/* 281 */     int residual = (int)(length & 0x7FFFFFF);
/* 282 */     if (residual != 0) {
/* 283 */       for (int i = 0; i < baseLength - 1; i++) base[i] = new Object[134217728];
/* 284 */       base[(baseLength - 1)] = new Object[residual];
/*     */     } else {
/* 286 */       for (int i = 0; i < baseLength; i++) base[i] = new Object[134217728]; 
/*     */     }
/* 287 */     return base;
/*     */   }
/*     */ 
/*     */   public static <K> K[][] wrap(K[] array)
/*     */   {
/* 298 */     if ((array.length == 0) && (array.getClass() == [Ljava.lang.Object.class)) return (Object[][])EMPTY_BIG_ARRAY;
/* 299 */     if (array.length <= 134217728) {
/* 300 */       Object[][] bigArray = (Object[][])Array.newInstance(array.getClass(), 1);
/* 301 */       bigArray[0] = array;
/* 302 */       return bigArray;
/*     */     }
/* 304 */     Object[][] bigArray = (Object[][])newBigArray(array.getClass(), array.length);
/* 305 */     for (int i = 0; i < bigArray.length; i++) System.arraycopy(array, (int)BigArrays.start(i), bigArray[i], 0, bigArray[i].length);
/* 306 */     return bigArray;
/*     */   }
/*     */ 
/*     */   public static <K> K[][] ensureCapacity(K[][] array, long length)
/*     */   {
/* 323 */     return ensureCapacity(array, length, length(array));
/*     */   }
/*     */ 
/*     */   public static <K> K[][] ensureCapacity(K[][] array, long length, long preserve)
/*     */   {
/* 342 */     long oldLength = length(array);
/* 343 */     if (length > oldLength) {
/* 344 */       int valid = array.length - ((array.length == 0) || ((array.length > 0) && (array[(array.length - 1)].length == 134217728)) ? 0 : 1);
/* 345 */       int baseLength = (int)((length + 134217727L) / 134217728L);
/* 346 */       Object[][] base = (Object[][])Arrays.copyOf(array, baseLength);
/* 347 */       Class componentType = array.getClass().getComponentType();
/* 348 */       int residual = (int)(length & 0x7FFFFFF);
/* 349 */       if (residual != 0) {
/* 350 */         for (int i = valid; i < baseLength - 1; i++) base[i] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), 134217728));
/* 351 */         base[(baseLength - 1)] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), residual));
/*     */       } else {
/* 353 */         for (int i = valid; i < baseLength; i++) base[i] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), 134217728)); 
/*     */       }
/* 354 */       if (preserve - valid * 134217728L > 0L) copy(array, valid * 134217728L, base, valid * 134217728L, preserve - valid * 134217728L);
/* 355 */       return base;
/*     */     }
/* 357 */     return array;
/*     */   }
/*     */ 
/*     */   public static <K> K[][] grow(K[][] array, long length)
/*     */   {
/* 378 */     long oldLength = length(array);
/* 379 */     return length > oldLength ? grow(array, length, oldLength) : array;
/*     */   }
/*     */ 
/*     */   public static <K> K[][] grow(K[][] array, long length, long preserve)
/*     */   {
/* 401 */     long oldLength = length(array);
/* 402 */     return length > oldLength ? ensureCapacity(array, Math.max(106039L * oldLength >>> 16, length), preserve) : array;
/*     */   }
/*     */ 
/*     */   public static <K> K[][] trim(K[][] array, long length)
/*     */   {
/* 418 */     long oldLength = length(array);
/* 419 */     if (length >= oldLength) return array;
/* 420 */     int baseLength = (int)((length + 134217727L) / 134217728L);
/* 421 */     Object[][] base = (Object[][])Arrays.copyOf(array, baseLength);
/* 422 */     int residual = (int)(length & 0x7FFFFFF);
/* 423 */     if (residual != 0) base[(baseLength - 1)] = ObjectArrays.trim(base[(baseLength - 1)], residual);
/* 424 */     return base;
/*     */   }
/*     */ 
/*     */   public static <K> K[][] setLength(K[][] array, long length)
/*     */   {
/* 443 */     long oldLength = length(array);
/* 444 */     if (length == oldLength) return array;
/* 445 */     if (length < oldLength) return trim(array, length);
/* 446 */     return ensureCapacity(array, length);
/*     */   }
/*     */ 
/*     */   public static <K> K[][] copy(K[][] array, long offset, long length)
/*     */   {
/* 456 */     ensureOffsetLength(array, offset, length);
/* 457 */     Object[][] a = newBigArray(array, length);
/*     */ 
/* 459 */     copy(array, offset, a, 0L, length);
/* 460 */     return a;
/*     */   }
/*     */ 
/*     */   public static <K> K[][] copy(K[][] array)
/*     */   {
/* 468 */     Object[][] base = (Object[][])array.clone();
/* 469 */     for (int i = base.length; i-- != 0; base[i] = ((Object[])array[i].clone()));
/* 470 */     return base;
/*     */   }
/*     */ 
/*     */   public static <K> void fill(K[][] array, K value)
/*     */   {
/* 481 */     for (int i = array.length; i-- != 0; ObjectArrays.fill(array[i], value));
/*     */   }
/*     */ 
/*     */   public static <K> void fill(K[][] array, long from, long to, K value)
/*     */   {
/* 495 */     long length = length(array);
/* 496 */     BigArrays.ensureFromTo(length, from, to);
/* 497 */     int fromSegment = BigArrays.segment(from);
/* 498 */     int toSegment = BigArrays.segment(to);
/* 499 */     int fromDispl = BigArrays.displacement(from);
/* 500 */     int toDispl = BigArrays.displacement(to);
/* 501 */     if (fromSegment == toSegment) {
/* 502 */       ObjectArrays.fill(array[fromSegment], fromDispl, toDispl, value);
/* 503 */       return;
/*     */     }
/* 505 */     if (toDispl != 0) ObjectArrays.fill(array[toSegment], 0, toDispl, value); while (true) {
/* 506 */       toSegment--; if (toSegment <= fromSegment) break; ObjectArrays.fill(array[toSegment], value);
/* 507 */     }ObjectArrays.fill(array[fromSegment], fromDispl, 134217728, value);
/*     */   }
/*     */ 
/*     */   public static <K> boolean equals(K[][] a1, K[][] a2)
/*     */   {
/* 519 */     if (length(a1) != length(a2)) return false; Object[] t;
/*     */     Object[] u;
/*     */     int j;
/*     */     do { int i = a1.length;
/*     */ 
/* 526 */       while (j-- == 0)
/*     */       {
/* 522 */         if (i-- == 0) break;
/* 523 */         t = a1[i];
/* 524 */         u = a2[i];
/* 525 */         j = t.length;
/*     */       } } while (t[j] == null ? u[j] == null : t[j].equals(u[j])); return false;
/*     */ 
/* 528 */     return true;
/*     */   }
/*     */ 
/*     */   public static <K> String toString(K[][] a)
/*     */   {
/* 537 */     if (a == null) return "null";
/* 538 */     long last = length(a) - 1L;
/* 539 */     if (last == -1L) return "[]";
/* 540 */     StringBuilder b = new StringBuilder();
/* 541 */     b.append('[');
/* 542 */     for (long i = 0L; ; i += 1L) {
/* 543 */       b.append(String.valueOf(get(a, i)));
/* 544 */       if (i == last) return b.append(']').toString();
/* 545 */       b.append(", ");
/*     */     }
/*     */   }
/*     */ 
/*     */   public static <K> void ensureFromTo(K[][] a, long from, long to)
/*     */   {
/* 559 */     BigArrays.ensureFromTo(length(a), from, to);
/*     */   }
/*     */ 
/*     */   public static <K> void ensureOffsetLength(K[][] a, long offset, long length)
/*     */   {
/* 572 */     BigArrays.ensureOffsetLength(length(a), offset, length);
/*     */   }
/*     */ 
/*     */   private static <K> void vecSwap(K[][] x, long a, long b, long n)
/*     */   {
/* 595 */     for (int i = 0; i < n; b += 1L) { swap(x, a, b); i++; a += 1L; } 
/*     */   }
/*     */ 
/* 598 */   private static <K> long med3(K[][] x, long a, long b, long c, Comparator<K> comp) { int ab = comp.compare(get(x, a), get(x, b));
/* 599 */     int ac = comp.compare(get(x, a), get(x, c));
/* 600 */     int bc = comp.compare(get(x, b), get(x, c));
/* 601 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*     */   }
/*     */ 
/*     */   private static <K> void selectionSort(K[][] a, long from, long to, Comparator<K> comp)
/*     */   {
/* 606 */     for (long i = from; i < to - 1L; i += 1L) {
/* 607 */       long m = i;
/* 608 */       for (long j = i + 1L; j < to; j += 1L) if (comp.compare(get(a, j), get(a, m)) < 0) m = j;
/* 609 */       if (m != i) swap(a, i, m);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static <K> void quickSort(K[][] x, long from, long to, Comparator<K> comp)
/*     */   {
/* 625 */     long len = to - from;
/*     */ 
/* 627 */     if (len < 7L) {
/* 628 */       for (long i = from; i < to; i += 1L)
/* 629 */         for (long j = i; (j > from) && (comp.compare(get(x, j - 1L), get(x, j)) > 0); j -= 1L) swap(x, j, j - 1L);
/* 630 */       return;
/*     */     }
/*     */ 
/* 633 */     long m = from + len / 2L;
/* 634 */     if (len > 7L) {
/* 635 */       long l = from;
/* 636 */       long n = to - 1L;
/* 637 */       if (len > 40L) {
/* 638 */         long s = len / 8L;
/* 639 */         l = med3(x, l, l + s, l + 2L * s, comp);
/* 640 */         m = med3(x, m - s, m, m + s, comp);
/* 641 */         n = med3(x, n - 2L * s, n - s, n, comp);
/*     */       }
/* 643 */       m = med3(x, l, m, n, comp);
/*     */     }
/* 645 */     Object v = get(x, m);
/*     */ 
/* 647 */     long a = from; long b = a; long c = to - 1L; long d = c;
/*     */     while (true)
/*     */     {
/*     */       int comparison;
/* 650 */       if ((b <= c) && ((comparison = comp.compare(get(x, b), v)) <= 0)) {
/* 651 */         if (comparison == 0) swap(x, a++, b);
/* 652 */         b += 1L;
/*     */       }
/*     */       else
/*     */       {
/*     */         int comparison;
/* 654 */         while ((c >= b) && ((comparison = comp.compare(get(x, c), v)) >= 0)) {
/* 655 */           if (comparison == 0) swap(x, c, d--);
/* 656 */           c -= 1L;
/*     */         }
/* 658 */         if (b > c) break;
/* 659 */         swap(x, b++, c--);
/*     */       }
/*     */     }
/* 662 */     long n = to;
/* 663 */     long s = Math.min(a - from, b - a);
/* 664 */     vecSwap(x, from, b - s, s);
/* 665 */     s = Math.min(d - c, n - d - 1L);
/* 666 */     vecSwap(x, b, n - s, s);
/*     */ 
/* 668 */     if ((s = b - a) > 1L) quickSort(x, from, from + s, comp);
/* 669 */     if ((s = d - c) > 1L) quickSort(x, n - s, n, comp); 
/*     */   }
/*     */ 
/*     */   private static <K> long med3(K[][] x, long a, long b, long c)
/*     */   {
/* 673 */     int ab = ((Comparable)get(x, a)).compareTo(get(x, b));
/* 674 */     int ac = ((Comparable)get(x, a)).compareTo(get(x, c));
/* 675 */     int bc = ((Comparable)get(x, b)).compareTo(get(x, c));
/* 676 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*     */   }
/*     */ 
/*     */   private static <K> void selectionSort(K[][] a, long from, long to)
/*     */   {
/* 681 */     for (long i = from; i < to - 1L; i += 1L) {
/* 682 */       long m = i;
/* 683 */       for (long j = i + 1L; j < to; j += 1L) if (((Comparable)get(a, j)).compareTo(get(a, m)) < 0) m = j;
/* 684 */       if (m != i) swap(a, i, m);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static <K> void quickSort(K[][] x, Comparator<K> comp)
/*     */   {
/* 699 */     quickSort(x, 0L, length(x), comp);
/*     */   }
/*     */ 
/*     */   public static <K> void quickSort(K[][] x, long from, long to)
/*     */   {
/* 713 */     long len = to - from;
/*     */ 
/* 715 */     if (len < 7L) {
/* 716 */       for (long i = from; i < to; i += 1L)
/* 717 */         for (long j = i; (j > from) && (((Comparable)get(x, j - 1L)).compareTo(get(x, j)) > 0); j -= 1L) swap(x, j, j - 1L);
/* 718 */       return;
/*     */     }
/*     */ 
/* 721 */     long m = from + len / 2L;
/* 722 */     if (len > 7L) {
/* 723 */       long l = from;
/* 724 */       long n = to - 1L;
/* 725 */       if (len > 40L) {
/* 726 */         long s = len / 8L;
/* 727 */         l = med3(x, l, l + s, l + 2L * s);
/* 728 */         m = med3(x, m - s, m, m + s);
/* 729 */         n = med3(x, n - 2L * s, n - s, n);
/*     */       }
/* 731 */       m = med3(x, l, m, n);
/*     */     }
/* 733 */     Object v = get(x, m);
/*     */ 
/* 735 */     long a = from; long b = a; long c = to - 1L; long d = c;
/*     */     while (true)
/*     */     {
/*     */       int comparison;
/* 738 */       if ((b <= c) && ((comparison = ((Comparable)get(x, b)).compareTo(v)) <= 0)) {
/* 739 */         if (comparison == 0) swap(x, a++, b);
/* 740 */         b += 1L;
/*     */       }
/*     */       else
/*     */       {
/*     */         int comparison;
/* 742 */         while ((c >= b) && ((comparison = ((Comparable)get(x, c)).compareTo(v)) >= 0)) {
/* 743 */           if (comparison == 0) swap(x, c, d--);
/* 744 */           c -= 1L;
/*     */         }
/* 746 */         if (b > c) break;
/* 747 */         swap(x, b++, c--);
/*     */       }
/*     */     }
/* 750 */     long n = to;
/* 751 */     long s = Math.min(a - from, b - a);
/* 752 */     vecSwap(x, from, b - s, s);
/* 753 */     s = Math.min(d - c, n - d - 1L);
/* 754 */     vecSwap(x, b, n - s, s);
/*     */ 
/* 756 */     if ((s = b - a) > 1L) quickSort(x, from, from + s);
/* 757 */     if ((s = d - c) > 1L) quickSort(x, n - s, n);
/*     */   }
/*     */ 
/*     */   public static <K> void quickSort(K[][] x)
/*     */   {
/* 769 */     quickSort(x, 0L, length(x));
/*     */   }
/*     */ 
/*     */   public static <K> long binarySearch(K[][] a, long from, long to, K key)
/*     */   {
/* 794 */     to -= 1L;
/* 795 */     while (from <= to) {
/* 796 */       long mid = from + to >>> 1;
/* 797 */       Object midVal = get(a, mid);
/* 798 */       int cmp = ((Comparable)midVal).compareTo(key);
/* 799 */       if (cmp < 0) from = mid + 1L;
/* 800 */       else if (cmp > 0) to = mid - 1L; else
/* 801 */         return mid;
/*     */     }
/* 803 */     return -(from + 1L);
/*     */   }
/*     */ 
/*     */   public static <K> long binarySearch(K[][] a, Object key)
/*     */   {
/* 824 */     return binarySearch(a, 0L, length(a), key);
/*     */   }
/*     */ 
/*     */   public static <K> long binarySearch(K[][] a, long from, long to, K key, Comparator<K> c)
/*     */   {
/* 849 */     to -= 1L;
/* 850 */     while (from <= to) {
/* 851 */       long mid = from + to >>> 1;
/* 852 */       Object midVal = get(a, mid);
/* 853 */       int cmp = c.compare(midVal, key);
/* 854 */       if (cmp < 0) from = mid + 1L;
/* 855 */       else if (cmp > 0) to = mid - 1L; else
/* 856 */         return mid;
/*     */     }
/* 858 */     return -(from + 1L);
/*     */   }
/*     */ 
/*     */   public static <K> long binarySearch(K[][] a, K key, Comparator<K> c)
/*     */   {
/* 880 */     return binarySearch(a, 0L, length(a), key, c);
/*     */   }
/*     */ 
/*     */   public static <K> K[][] shuffle(K[][] a, long from, long to, Random random)
/*     */   {
/* 891 */     for (long i = to - from; i-- != 0L; ) {
/* 892 */       long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/* 893 */       Object t = get(a, from + i);
/* 894 */       set(a, from + i, get(a, from + p));
/* 895 */       set(a, from + p, t);
/*     */     }
/* 897 */     return a;
/*     */   }
/*     */ 
/*     */   public static <K> K[][] shuffle(K[][] a, Random random)
/*     */   {
/* 906 */     for (long i = length(a); i-- != 0L; ) {
/* 907 */       long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/* 908 */       Object t = get(a, i);
/* 909 */       set(a, i, get(a, p));
/* 910 */       set(a, p, t);
/*     */     }
/* 912 */     return a;
/*     */   }
/*     */ 
/*     */   private static final class BigArrayHashStrategy<K>
/*     */     implements Hash.Strategy<K[][]>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public int hashCode(K[][] o)
/*     */     {
/* 578 */       return Arrays.deepHashCode(o);
/*     */     }
/*     */     public boolean equals(K[][] a, K[][] b) {
/* 581 */       return ObjectBigArrays.equals(a, b);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectBigArrays
 * JD-Core Version:    0.6.2
 */