/*     */ package it.unimi.dsi.fastutil.booleans;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.BigArrays;
/*     */ import it.unimi.dsi.fastutil.Hash.Strategy;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class BooleanBigArrays
/*     */ {
/*     */   public static final long ONEOVERPHI = 106039L;
/*  84 */   public static final boolean[][] EMPTY_BIG_ARRAY = new boolean[0][];
/*     */ 
/* 530 */   public static final Hash.Strategy HASH_STRATEGY = new BigArrayHashStrategy(null);
/*     */   private static final int SMALL = 7;
/*     */   private static final int MEDIUM = 40;
/*     */ 
/*     */   public static boolean get(boolean[][] array, long index)
/*     */   {
/*  92 */     return array[BigArrays.segment(index)][BigArrays.displacement(index)];
/*     */   }
/*     */ 
/*     */   public static void set(boolean[][] array, long index, boolean value)
/*     */   {
/* 100 */     array[BigArrays.segment(index)][BigArrays.displacement(index)] = value;
/*     */   }
/*     */ 
/*     */   public static void swap(boolean[][] array, long first, long second)
/*     */   {
/* 109 */     boolean t = array[BigArrays.segment(first)][BigArrays.displacement(first)];
/* 110 */     array[BigArrays.segment(first)][BigArrays.displacement(first)] = array[BigArrays.segment(second)][BigArrays.displacement(second)];
/* 111 */     array[BigArrays.segment(second)][BigArrays.displacement(second)] = t;
/*     */   }
/*     */ 
/*     */   public static long length(boolean[][] array)
/*     */   {
/* 119 */     int length = array.length;
/* 120 */     return length == 0 ? 0L : BigArrays.start(length - 1) + array[(length - 1)].length;
/*     */   }
/*     */ 
/*     */   public static void copy(boolean[][] srcArray, long srcPos, boolean[][] destArray, long destPos, long length)
/*     */   {
/* 132 */     if (destPos <= srcPos) {
/* 133 */       int srcSegment = BigArrays.segment(srcPos);
/* 134 */       int destSegment = BigArrays.segment(destPos);
/* 135 */       int srcDispl = BigArrays.displacement(srcPos);
/* 136 */       int destDispl = BigArrays.displacement(destPos);
/*     */ 
/* 138 */       while (length > 0L) {
/* 139 */         int l = (int)Math.min(length, Math.min(srcArray[srcSegment].length - srcDispl, destArray[destSegment].length - destDispl));
/* 140 */         System.arraycopy(srcArray[srcSegment], srcDispl, destArray[destSegment], destDispl, l);
/* 141 */         if (srcDispl += l == 134217728) {
/* 142 */           srcDispl = 0;
/* 143 */           srcSegment++;
/*     */         }
/* 145 */         if (destDispl += l == 134217728) {
/* 146 */           destDispl = 0;
/* 147 */           destSegment++;
/*     */         }
/* 149 */         length -= l;
/*     */       }
/*     */     }
/*     */     else {
/* 153 */       int srcSegment = BigArrays.segment(srcPos + length);
/* 154 */       int destSegment = BigArrays.segment(destPos + length);
/* 155 */       int srcDispl = BigArrays.displacement(srcPos + length);
/* 156 */       int destDispl = BigArrays.displacement(destPos + length);
/*     */ 
/* 158 */       while (length > 0L) {
/* 159 */         if (srcDispl == 0) {
/* 160 */           srcDispl = 134217728;
/* 161 */           srcSegment--;
/*     */         }
/* 163 */         if (destDispl == 0) {
/* 164 */           destDispl = 134217728;
/* 165 */           destSegment--;
/*     */         }
/* 167 */         int l = (int)Math.min(length, Math.min(srcDispl, destDispl));
/* 168 */         System.arraycopy(srcArray[srcSegment], srcDispl - l, destArray[destSegment], destDispl - l, l);
/* 169 */         srcDispl -= l;
/* 170 */         destDispl -= l;
/* 171 */         length -= l;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void copyFromBig(boolean[][] srcArray, long srcPos, boolean[] destArray, int destPos, int length)
/*     */   {
/* 184 */     int srcSegment = BigArrays.segment(srcPos);
/* 185 */     int srcDispl = BigArrays.displacement(srcPos);
/*     */ 
/* 187 */     while (length > 0) {
/* 188 */       int l = Math.min(srcArray[srcSegment].length - srcDispl, length);
/* 189 */       System.arraycopy(srcArray[srcSegment], srcDispl, destArray, destPos, l);
/* 190 */       if (srcDispl += l == 134217728) {
/* 191 */         srcDispl = 0;
/* 192 */         srcSegment++;
/*     */       }
/* 194 */       destPos += l;
/* 195 */       length -= l;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void copyToBig(boolean[] srcArray, int srcPos, boolean[][] destArray, long destPos, long length)
/*     */   {
/* 207 */     int destSegment = BigArrays.segment(destPos);
/* 208 */     int destDispl = BigArrays.displacement(destPos);
/*     */ 
/* 210 */     while (length > 0L) {
/* 211 */       int l = (int)Math.min(destArray[destSegment].length - destDispl, length);
/* 212 */       System.arraycopy(srcArray, srcPos, destArray[destSegment], destDispl, l);
/* 213 */       if (destDispl += l == 134217728) {
/* 214 */         destDispl = 0;
/* 215 */         destSegment++;
/*     */       }
/* 217 */       srcPos += l;
/* 218 */       length -= l;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean[][] newBigArray(long length)
/*     */   {
/* 227 */     if (length == 0L) return EMPTY_BIG_ARRAY;
/* 228 */     int baseLength = (int)((length + 134217727L) / 134217728L);
/* 229 */     boolean[][] base = new boolean[baseLength][];
/* 230 */     int residual = (int)(length & 0x7FFFFFF);
/* 231 */     if (residual != 0) {
/* 232 */       for (int i = 0; i < baseLength - 1; i++) base[i] = new boolean[134217728];
/* 233 */       base[(baseLength - 1)] = new boolean[residual];
/*     */     } else {
/* 235 */       for (int i = 0; i < baseLength; i++) base[i] = new boolean[134217728]; 
/*     */     }
/* 236 */     return base;
/*     */   }
/*     */ 
/*     */   public static boolean[][] wrap(boolean[] array)
/*     */   {
/* 246 */     if (array.length == 0) return EMPTY_BIG_ARRAY;
/* 247 */     if (array.length <= 134217728) return new boolean[][] { array };
/* 248 */     boolean[][] bigArray = newBigArray(array.length);
/* 249 */     for (int i = 0; i < bigArray.length; i++) System.arraycopy(array, (int)BigArrays.start(i), bigArray[i], 0, bigArray[i].length);
/* 250 */     return bigArray;
/*     */   }
/*     */ 
/*     */   public static boolean[][] ensureCapacity(boolean[][] array, long length)
/*     */   {
/* 267 */     return ensureCapacity(array, length, length(array));
/*     */   }
/*     */ 
/*     */   public static boolean[][] ensureCapacity(boolean[][] array, long length, long preserve)
/*     */   {
/* 282 */     long oldLength = length(array);
/* 283 */     if (length > oldLength) {
/* 284 */       int valid = array.length - ((array.length == 0) || ((array.length > 0) && (array[(array.length - 1)].length == 134217728)) ? 0 : 1);
/* 285 */       int baseLength = (int)((length + 134217727L) / 134217728L);
/* 286 */       boolean[][] base = (boolean[][])Arrays.copyOf(array, baseLength);
/* 287 */       int residual = (int)(length & 0x7FFFFFF);
/* 288 */       if (residual != 0) {
/* 289 */         for (int i = valid; i < baseLength - 1; i++) base[i] = new boolean[134217728];
/* 290 */         base[(baseLength - 1)] = new boolean[residual];
/*     */       } else {
/* 292 */         for (int i = valid; i < baseLength; i++) base[i] = new boolean[134217728]; 
/*     */       }
/* 293 */       if (preserve - valid * 134217728L > 0L) copy(array, valid * 134217728L, base, valid * 134217728L, preserve - valid * 134217728L);
/* 294 */       return base;
/*     */     }
/* 296 */     return array;
/*     */   }
/*     */ 
/*     */   public static boolean[][] grow(boolean[][] array, long length)
/*     */   {
/* 317 */     long oldLength = length(array);
/* 318 */     return length > oldLength ? grow(array, length, oldLength) : array;
/*     */   }
/*     */ 
/*     */   public static boolean[][] grow(boolean[][] array, long length, long preserve)
/*     */   {
/* 340 */     long oldLength = length(array);
/* 341 */     return length > oldLength ? ensureCapacity(array, Math.max(106039L * oldLength >>> 16, length), preserve) : array;
/*     */   }
/*     */ 
/*     */   public static boolean[][] trim(boolean[][] array, long length)
/*     */   {
/* 357 */     long oldLength = length(array);
/* 358 */     if (length >= oldLength) return array;
/* 359 */     int baseLength = (int)((length + 134217727L) / 134217728L);
/* 360 */     boolean[][] base = (boolean[][])Arrays.copyOf(array, baseLength);
/* 361 */     int residual = (int)(length & 0x7FFFFFF);
/* 362 */     if (residual != 0) base[(baseLength - 1)] = BooleanArrays.trim(base[(baseLength - 1)], residual);
/* 363 */     return base;
/*     */   }
/*     */ 
/*     */   public static boolean[][] setLength(boolean[][] array, long length)
/*     */   {
/* 382 */     long oldLength = length(array);
/* 383 */     if (length == oldLength) return array;
/* 384 */     if (length < oldLength) return trim(array, length);
/* 385 */     return ensureCapacity(array, length);
/*     */   }
/*     */ 
/*     */   public static boolean[][] copy(boolean[][] array, long offset, long length)
/*     */   {
/* 395 */     ensureOffsetLength(array, offset, length);
/* 396 */     boolean[][] a = newBigArray(length);
/*     */ 
/* 398 */     copy(array, offset, a, 0L, length);
/* 399 */     return a;
/*     */   }
/*     */ 
/*     */   public static boolean[][] copy(boolean[][] array)
/*     */   {
/* 407 */     boolean[][] base = (boolean[][])array.clone();
/* 408 */     for (int i = base.length; i-- != 0; base[i] = ((boolean[])array[i].clone()));
/* 409 */     return base;
/*     */   }
/*     */ 
/*     */   public static void fill(boolean[][] array, boolean value)
/*     */   {
/* 420 */     for (int i = array.length; i-- != 0; BooleanArrays.fill(array[i], value));
/*     */   }
/*     */ 
/*     */   public static void fill(boolean[][] array, long from, long to, boolean value)
/*     */   {
/* 434 */     long length = length(array);
/* 435 */     BigArrays.ensureFromTo(length, from, to);
/* 436 */     int fromSegment = BigArrays.segment(from);
/* 437 */     int toSegment = BigArrays.segment(to);
/* 438 */     int fromDispl = BigArrays.displacement(from);
/* 439 */     int toDispl = BigArrays.displacement(to);
/* 440 */     if (fromSegment == toSegment) {
/* 441 */       BooleanArrays.fill(array[fromSegment], fromDispl, toDispl, value);
/* 442 */       return;
/*     */     }
/* 444 */     if (toDispl != 0) BooleanArrays.fill(array[toSegment], 0, toDispl, value); while (true) {
/* 445 */       toSegment--; if (toSegment <= fromSegment) break; BooleanArrays.fill(array[toSegment], value);
/* 446 */     }BooleanArrays.fill(array[fromSegment], fromDispl, 134217728, value);
/*     */   }
/*     */ 
/*     */   public static boolean equals(boolean[][] a1, boolean[][] a2)
/*     */   {
/* 458 */     if (length(a1) != length(a2)) return false; boolean[] t;
/*     */     boolean[] u;
/*     */     int j;
/*     */     do { int i = a1.length;
/*     */ 
/* 465 */       while (j-- == 0)
/*     */       {
/* 461 */         if (i-- == 0) break;
/* 462 */         t = a1[i];
/* 463 */         u = a2[i];
/* 464 */         j = t.length;
/*     */       } } while (t[j] == u[j]); return false;
/*     */ 
/* 467 */     return true;
/*     */   }
/*     */ 
/*     */   public static String toString(boolean[][] a)
/*     */   {
/* 476 */     if (a == null) return "null";
/* 477 */     long last = length(a) - 1L;
/* 478 */     if (last == -1L) return "[]";
/* 479 */     StringBuilder b = new StringBuilder();
/* 480 */     b.append('[');
/* 481 */     for (long i = 0L; ; i += 1L) {
/* 482 */       b.append(String.valueOf(get(a, i)));
/* 483 */       if (i == last) return b.append(']').toString();
/* 484 */       b.append(", ");
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void ensureFromTo(boolean[][] a, long from, long to)
/*     */   {
/* 498 */     BigArrays.ensureFromTo(length(a), from, to);
/*     */   }
/*     */ 
/*     */   public static void ensureOffsetLength(boolean[][] a, long offset, long length)
/*     */   {
/* 511 */     BigArrays.ensureOffsetLength(length(a), offset, length);
/*     */   }
/*     */ 
/*     */   private static void vecSwap(boolean[][] x, long a, long b, long n)
/*     */   {
/* 534 */     for (int i = 0; i < n; b += 1L) { swap(x, a, b); i++; a += 1L; } 
/*     */   }
/*     */ 
/* 537 */   private static long med3(boolean[][] x, long a, long b, long c, BooleanComparator comp) { int ab = comp.compare(get(x, a), get(x, b));
/* 538 */     int ac = comp.compare(get(x, a), get(x, c));
/* 539 */     int bc = comp.compare(get(x, b), get(x, c));
/* 540 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*     */   }
/*     */ 
/*     */   private static void selectionSort(boolean[][] a, long from, long to, BooleanComparator comp)
/*     */   {
/* 545 */     for (long i = from; i < to - 1L; i += 1L) {
/* 546 */       long m = i;
/* 547 */       for (long j = i + 1L; j < to; j += 1L) if (comp.compare(get(a, j), get(a, m)) < 0) m = j;
/* 548 */       if (m != i) swap(a, i, m);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void quickSort(boolean[][] x, long from, long to, BooleanComparator comp)
/*     */   {
/* 564 */     long len = to - from;
/*     */ 
/* 566 */     if (len < 7L) {
/* 567 */       for (long i = from; i < to; i += 1L)
/* 568 */         for (long j = i; (j > from) && (comp.compare(get(x, j - 1L), get(x, j)) > 0); j -= 1L) swap(x, j, j - 1L);
/* 569 */       return;
/*     */     }
/*     */ 
/* 572 */     long m = from + len / 2L;
/* 573 */     if (len > 7L) {
/* 574 */       long l = from;
/* 575 */       long n = to - 1L;
/* 576 */       if (len > 40L) {
/* 577 */         long s = len / 8L;
/* 578 */         l = med3(x, l, l + s, l + 2L * s, comp);
/* 579 */         m = med3(x, m - s, m, m + s, comp);
/* 580 */         n = med3(x, n - 2L * s, n - s, n, comp);
/*     */       }
/* 582 */       m = med3(x, l, m, n, comp);
/*     */     }
/* 584 */     boolean v = get(x, m);
/*     */ 
/* 586 */     long a = from; long b = a; long c = to - 1L; long d = c;
/*     */     while (true)
/*     */     {
/*     */       int comparison;
/* 589 */       if ((b <= c) && ((comparison = comp.compare(get(x, b), v)) <= 0)) {
/* 590 */         if (comparison == 0) swap(x, a++, b);
/* 591 */         b += 1L;
/*     */       }
/*     */       else
/*     */       {
/*     */         int comparison;
/* 593 */         while ((c >= b) && ((comparison = comp.compare(get(x, c), v)) >= 0)) {
/* 594 */           if (comparison == 0) swap(x, c, d--);
/* 595 */           c -= 1L;
/*     */         }
/* 597 */         if (b > c) break;
/* 598 */         swap(x, b++, c--);
/*     */       }
/*     */     }
/* 601 */     long n = to;
/* 602 */     long s = Math.min(a - from, b - a);
/* 603 */     vecSwap(x, from, b - s, s);
/* 604 */     s = Math.min(d - c, n - d - 1L);
/* 605 */     vecSwap(x, b, n - s, s);
/*     */ 
/* 607 */     if ((s = b - a) > 1L) quickSort(x, from, from + s, comp);
/* 608 */     if ((s = d - c) > 1L) quickSort(x, n - s, n, comp); 
/*     */   }
/*     */ 
/*     */   private static long med3(boolean[][] x, long a, long b, long c)
/*     */   {
/* 612 */     int ab = get(x, a) == get(x, b) ? 0 : (!get(x, a)) && (get(x, b)) ? -1 : 1;
/* 613 */     int ac = get(x, a) == get(x, c) ? 0 : (!get(x, a)) && (get(x, c)) ? -1 : 1;
/* 614 */     int bc = get(x, b) == get(x, c) ? 0 : (!get(x, b)) && (get(x, c)) ? -1 : 1;
/* 615 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*     */   }
/*     */ 
/*     */   private static void selectionSort(boolean[][] a, long from, long to)
/*     */   {
/* 620 */     for (long i = from; i < to - 1L; i += 1L) {
/* 621 */       long m = i;
/* 622 */       for (long j = i + 1L; j < to; j += 1L) if ((!get(a, j)) && (get(a, m))) m = j;
/* 623 */       if (m != i) swap(a, i, m);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void quickSort(boolean[][] x, BooleanComparator comp)
/*     */   {
/* 638 */     quickSort(x, 0L, length(x), comp);
/*     */   }
/*     */ 
/*     */   public static void quickSort(boolean[][] x, long from, long to)
/*     */   {
/* 652 */     long len = to - from;
/*     */ 
/* 654 */     if (len < 7L) {
/* 655 */       for (long i = from; i < to; i += 1L)
/* 656 */         for (long j = i; j > from; j -= 1L) { if ((get(x, j - 1L) == get(x, j) ? 0 : (!get(x, j - 1L)) && (get(x, j)) ? -1 : 1) <= 0) break; swap(x, j, j - 1L); }
/* 657 */       return;
/*     */     }
/*     */ 
/* 660 */     long m = from + len / 2L;
/* 661 */     if (len > 7L) {
/* 662 */       long l = from;
/* 663 */       long n = to - 1L;
/* 664 */       if (len > 40L) {
/* 665 */         long s = len / 8L;
/* 666 */         l = med3(x, l, l + s, l + 2L * s);
/* 667 */         m = med3(x, m - s, m, m + s);
/* 668 */         n = med3(x, n - 2L * s, n - s, n);
/*     */       }
/* 670 */       m = med3(x, l, m, n);
/*     */     }
/* 672 */     boolean v = get(x, m);
/*     */ 
/* 674 */     long a = from; long b = a; long c = to - 1L; long d = c;
/*     */     while (true)
/*     */     {
/* 677 */       if (b <= c)
/*     */       {
/*     */         int comparison;
/* 677 */         if ((comparison = get(x, b) == v ? 0 : (!get(x, b)) && (v) ? -1 : 1) <= 0) {
/* 678 */           if (comparison == 0) swap(x, a++, b);
/* 679 */           b += 1L;
/*     */         }
/*     */       } else { while (c >= b)
/*     */         {
/*     */           int comparison;
/* 681 */           if ((comparison = get(x, c) == v ? 0 : (!get(x, c)) && (v) ? -1 : 1) < 0) break;
/* 682 */           if (comparison == 0) swap(x, c, d--);
/* 683 */           c -= 1L;
/*     */         }
/* 685 */         if (b > c) break;
/* 686 */         swap(x, b++, c--);
/*     */       }
/*     */     }
/* 689 */     long n = to;
/* 690 */     long s = Math.min(a - from, b - a);
/* 691 */     vecSwap(x, from, b - s, s);
/* 692 */     s = Math.min(d - c, n - d - 1L);
/* 693 */     vecSwap(x, b, n - s, s);
/*     */ 
/* 695 */     if ((s = b - a) > 1L) quickSort(x, from, from + s);
/* 696 */     if ((s = d - c) > 1L) quickSort(x, n - s, n);
/*     */   }
/*     */ 
/*     */   public static void quickSort(boolean[][] x)
/*     */   {
/* 708 */     quickSort(x, 0L, length(x));
/*     */   }
/*     */ 
/*     */   public static boolean[][] shuffle(boolean[][] a, long from, long to, Random random)
/*     */   {
/* 719 */     for (long i = to - from; i-- != 0L; ) {
/* 720 */       long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/* 721 */       boolean t = get(a, from + i);
/* 722 */       set(a, from + i, get(a, from + p));
/* 723 */       set(a, from + p, t);
/*     */     }
/* 725 */     return a;
/*     */   }
/*     */ 
/*     */   public static boolean[][] shuffle(boolean[][] a, Random random)
/*     */   {
/* 734 */     for (long i = length(a); i-- != 0L; ) {
/* 735 */       long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/* 736 */       boolean t = get(a, i);
/* 737 */       set(a, i, get(a, p));
/* 738 */       set(a, p, t);
/*     */     }
/* 740 */     return a;
/*     */   }
/*     */ 
/*     */   private static final class BigArrayHashStrategy
/*     */     implements Hash.Strategy<boolean[][]>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public int hashCode(boolean[][] o)
/*     */     {
/* 517 */       return Arrays.deepHashCode(o);
/*     */     }
/*     */     public boolean equals(boolean[][] a, boolean[][] b) {
/* 520 */       return BooleanBigArrays.equals(a, b);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanBigArrays
 * JD-Core Version:    0.6.2
 */