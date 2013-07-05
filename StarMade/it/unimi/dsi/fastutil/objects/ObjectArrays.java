/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Hash.Strategy;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Comparator;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ObjectArrays
/*     */ {
/*     */   public static final long ONEOVERPHI = 106039L;
/*  87 */   public static final Object[] EMPTY_ARRAY = new Object[0];
/*     */   private static final int SMALL = 7;
/*     */   private static final int MEDIUM = 50;
/* 953 */   public static final Hash.Strategy HASH_STRATEGY = new ArrayHashStrategy(null);
/*     */ 
/*     */   private static <K> K[] newArray(K[] prototype, int length)
/*     */   {
/* 104 */     Class componentType = prototype.getClass().getComponentType();
/* 105 */     if ((length == 0) && (componentType == Object.class)) return (Object[])EMPTY_ARRAY;
/* 106 */     return (Object[])Array.newInstance(prototype.getClass().getComponentType(), length);
/*     */   }
/*     */ 
/*     */   public static <K> K[] ensureCapacity(K[] array, int length)
/*     */   {
/* 122 */     if (length > array.length) {
/* 123 */       Object[] t = newArray(array, length);
/*     */ 
/* 129 */       System.arraycopy(array, 0, t, 0, array.length);
/* 130 */       return t;
/*     */     }
/* 132 */     return array;
/*     */   }
/*     */ 
/*     */   public static <K> K[] ensureCapacity(K[] array, int length, int preserve)
/*     */   {
/* 145 */     if (length > array.length) {
/* 146 */       Object[] t = newArray(array, length);
/*     */ 
/* 152 */       System.arraycopy(array, 0, t, 0, preserve);
/* 153 */       return t;
/*     */     }
/* 155 */     return array;
/*     */   }
/*     */ 
/*     */   public static <K> K[] grow(K[] array, int length)
/*     */   {
/* 175 */     if (length > array.length) {
/* 176 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/* 177 */       Object[] t = newArray(array, newLength);
/*     */ 
/* 183 */       System.arraycopy(array, 0, t, 0, array.length);
/* 184 */       return t;
/*     */     }
/* 186 */     return array;
/*     */   }
/*     */ 
/*     */   public static <K> K[] grow(K[] array, int length, int preserve)
/*     */   {
/* 208 */     if (length > array.length) {
/* 209 */       int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*     */ 
/* 211 */       Object[] t = newArray(array, newLength);
/*     */ 
/* 217 */       System.arraycopy(array, 0, t, 0, preserve);
/*     */ 
/* 219 */       return t;
/*     */     }
/* 221 */     return array;
/*     */   }
/*     */ 
/*     */   public static <K> K[] trim(K[] array, int length)
/*     */   {
/* 237 */     if (length >= array.length) return array;
/* 238 */     Object[] t = newArray(array, length);
/*     */ 
/* 244 */     System.arraycopy(array, 0, t, 0, length);
/* 245 */     return t;
/*     */   }
/*     */ 
/*     */   public static <K> K[] setLength(K[] array, int length)
/*     */   {
/* 263 */     if (length == array.length) return array;
/* 264 */     if (length < array.length) return trim(array, length);
/* 265 */     return ensureCapacity(array, length);
/*     */   }
/*     */ 
/*     */   public static <K> K[] copy(K[] array, int offset, int length)
/*     */   {
/* 277 */     ensureOffsetLength(array, offset, length);
/* 278 */     Object[] a = newArray(array, length);
/*     */ 
/* 284 */     System.arraycopy(array, offset, a, 0, length);
/* 285 */     return a;
/*     */   }
/*     */ 
/*     */   public static <K> K[] copy(K[] array)
/*     */   {
/* 295 */     return (Object[])array.clone();
/*     */   }
/*     */ 
/*     */   public static <K> void fill(K[] array, K value)
/*     */   {
/* 308 */     int i = array.length;
/* 309 */     while (i-- != 0) array[i] = value;
/*     */   }
/*     */ 
/*     */   public static <K> void fill(K[] array, int from, int to, K value)
/*     */   {
/* 325 */     ensureFromTo(array, from, to);
/* 326 */     for (from != 0; to-- != 0; array[to] = value);
/* 327 */     for (int i = from; i < to; i++) array[i] = value;
/*     */   }
/*     */ 
/*     */   public static <K> boolean equals(K[] a1, K[] a2)
/*     */   {
/* 343 */     int i = a1.length;
/* 344 */     if (i != a2.length) return false;
/* 345 */     for (; i-- != 0; return false) label11: if (a1[i] == null ? a2[i] == null : a1[i].equals(a2[i]))
/*     */         break label11; return true;
/*     */   }
/*     */ 
/*     */   public static <K> void ensureFromTo(K[] a, int from, int to)
/*     */   {
/* 363 */     it.unimi.dsi.fastutil.Arrays.ensureFromTo(a.length, from, to);
/*     */   }
/*     */ 
/*     */   public static <K> void ensureOffsetLength(K[] a, int offset, int length)
/*     */   {
/* 377 */     it.unimi.dsi.fastutil.Arrays.ensureOffsetLength(a.length, offset, length);
/*     */   }
/*     */ 
/*     */   private static <K> void swap(K[] x, int a, int b)
/*     */   {
/* 384 */     Object t = x[a];
/* 385 */     x[a] = x[b];
/* 386 */     x[b] = t;
/*     */   }
/*     */ 
/*     */   private static <K> void vecSwap(K[] x, int a, int b, int n) {
/* 390 */     for (int i = 0; i < n; b++) { swap(x, a, b); i++; a++; }
/*     */   }
/*     */ 
/*     */   private static <K> int med3(K[] x, int a, int b, int c, Comparator<K> comp) {
/* 394 */     int ab = comp.compare(x[a], x[b]);
/* 395 */     int ac = comp.compare(x[a], x[c]);
/* 396 */     int bc = comp.compare(x[b], x[c]);
/* 397 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*     */   }
/*     */ 
/*     */   private static <K> void selectionSort(K[] a, int from, int to, Comparator<K> comp)
/*     */   {
/* 404 */     for (int i = from; i < to - 1; i++) {
/* 405 */       int m = i;
/* 406 */       for (int j = i + 1; j < to; j++) if (comp.compare(a[j], a[m]) < 0) m = j;
/* 407 */       if (m != i) {
/* 408 */         Object u = a[i];
/* 409 */         a[i] = a[m];
/* 410 */         a[m] = u;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static <K> void insertionSort(K[] a, int from, int to, Comparator<K> comp)
/*     */   {
/* 417 */     int i = from;
/*     */     while (true) { i++; if (i >= to) break;
/* 418 */       Object t = a[i];
/* 419 */       int j = i;
/* 420 */       for (Object u = a[(j - 1)]; comp.compare(t, u) < 0; u = a[(--j - 1)]) {
/* 421 */         a[j] = u;
/* 422 */         if (from == j - 1) {
/* 423 */           j--;
/* 424 */           break;
/*     */         }
/*     */       }
/* 427 */       a[j] = t;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static <K> void selectionSort(K[] a, int from, int to)
/*     */   {
/* 433 */     for (int i = from; i < to - 1; i++) {
/* 434 */       int m = i;
/* 435 */       for (int j = i + 1; j < to; j++) if (((Comparable)a[j]).compareTo(a[m]) < 0) m = j;
/* 436 */       if (m != i) {
/* 437 */         Object u = a[i];
/* 438 */         a[i] = a[m];
/* 439 */         a[m] = u;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static <K> void insertionSort(K[] a, int from, int to)
/*     */   {
/* 446 */     int i = from;
/*     */     while (true) { i++; if (i >= to) break;
/* 447 */       Object t = a[i];
/* 448 */       int j = i;
/* 449 */       for (Object u = a[(j - 1)]; ((Comparable)t).compareTo(u) < 0; u = a[(--j - 1)]) {
/* 450 */         a[j] = u;
/* 451 */         if (from == j - 1) {
/* 452 */           j--;
/* 453 */           break;
/*     */         }
/*     */       }
/* 456 */       a[j] = t;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static <K> void quickSort(K[] x, int from, int to, Comparator<K> comp)
/*     */   {
/* 475 */     int len = to - from;
/*     */ 
/* 478 */     if (len < 7) {
/* 479 */       selectionSort(x, from, to, comp);
/* 480 */       return;
/*     */     }
/*     */ 
/* 484 */     int m = from + len / 2;
/* 485 */     if (len > 7) {
/* 486 */       int l = from;
/* 487 */       int n = to - 1;
/* 488 */       if (len > 50) {
/* 489 */         int s = len / 8;
/* 490 */         l = med3(x, l, l + s, l + 2 * s, comp);
/* 491 */         m = med3(x, m - s, m, m + s, comp);
/* 492 */         n = med3(x, n - 2 * s, n - s, n, comp);
/*     */       }
/* 494 */       m = med3(x, l, m, n, comp);
/*     */     }
/*     */ 
/* 497 */     Object v = x[m];
/*     */ 
/* 500 */     int a = from; int b = a; int c = to - 1; int d = c;
/*     */     while (true)
/*     */     {
/*     */       int comparison;
/* 503 */       if ((b <= c) && ((comparison = comp.compare(x[b], v)) <= 0)) {
/* 504 */         if (comparison == 0) swap(x, a++, b);
/* 505 */         b++;
/*     */       }
/*     */       else
/*     */       {
/*     */         int comparison;
/* 507 */         while ((c >= b) && ((comparison = comp.compare(x[c], v)) >= 0)) {
/* 508 */           if (comparison == 0) swap(x, c, d--);
/* 509 */           c--;
/*     */         }
/* 511 */         if (b > c) break;
/* 512 */         swap(x, b++, c--);
/*     */       }
/*     */     }
/*     */ 
/* 516 */     int n = to;
/* 517 */     int s = Math.min(a - from, b - a);
/* 518 */     vecSwap(x, from, b - s, s);
/* 519 */     s = Math.min(d - c, n - d - 1);
/* 520 */     vecSwap(x, b, n - s, s);
/*     */ 
/* 523 */     if ((s = b - a) > 1) quickSort(x, from, from + s, comp);
/* 524 */     if ((s = d - c) > 1) quickSort(x, n - s, n, comp);
/*     */   }
/*     */ 
/*     */   public static <K> void quickSort(K[] x, Comparator<K> comp)
/*     */   {
/* 540 */     quickSort(x, 0, x.length, comp);
/*     */   }
/*     */ 
/*     */   private static <K> int med3(K[] x, int a, int b, int c)
/*     */   {
/* 546 */     int ab = ((Comparable)x[a]).compareTo(x[b]);
/* 547 */     int ac = ((Comparable)x[a]).compareTo(x[c]);
/* 548 */     int bc = ((Comparable)x[b]).compareTo(x[c]);
/* 549 */     return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*     */   }
/*     */ 
/*     */   public static <K> void quickSort(K[] x, int from, int to)
/*     */   {
/* 569 */     int len = to - from;
/*     */ 
/* 572 */     if (len < 7) {
/* 573 */       selectionSort(x, from, to);
/* 574 */       return;
/*     */     }
/*     */ 
/* 578 */     int m = from + len / 2;
/* 579 */     if (len > 7) {
/* 580 */       int l = from;
/* 581 */       int n = to - 1;
/* 582 */       if (len > 50) {
/* 583 */         int s = len / 8;
/* 584 */         l = med3(x, l, l + s, l + 2 * s);
/* 585 */         m = med3(x, m - s, m, m + s);
/* 586 */         n = med3(x, n - 2 * s, n - s, n);
/*     */       }
/* 588 */       m = med3(x, l, m, n);
/*     */     }
/*     */ 
/* 591 */     Object v = x[m];
/*     */ 
/* 594 */     int a = from; int b = a; int c = to - 1; int d = c;
/*     */     while (true)
/*     */     {
/*     */       int comparison;
/* 597 */       if ((b <= c) && ((comparison = ((Comparable)x[b]).compareTo(v)) <= 0)) {
/* 598 */         if (comparison == 0) swap(x, a++, b);
/* 599 */         b++;
/*     */       }
/*     */       else
/*     */       {
/*     */         int comparison;
/* 601 */         while ((c >= b) && ((comparison = ((Comparable)x[c]).compareTo(v)) >= 0)) {
/* 602 */           if (comparison == 0) swap(x, c, d--);
/* 603 */           c--;
/*     */         }
/* 605 */         if (b > c) break;
/* 606 */         swap(x, b++, c--);
/*     */       }
/*     */     }
/*     */ 
/* 610 */     int n = to;
/* 611 */     int s = Math.min(a - from, b - a);
/* 612 */     vecSwap(x, from, b - s, s);
/* 613 */     s = Math.min(d - c, n - d - 1);
/* 614 */     vecSwap(x, b, n - s, s);
/*     */ 
/* 617 */     if ((s = b - a) > 1) quickSort(x, from, from + s);
/* 618 */     if ((s = d - c) > 1) quickSort(x, n - s, n);
/*     */   }
/*     */ 
/*     */   public static <K> void quickSort(K[] x)
/*     */   {
/* 632 */     quickSort(x, 0, x.length);
/*     */   }
/*     */ 
/*     */   public static <K> void mergeSort(K[] a, int from, int to, K[] supp)
/*     */   {
/* 648 */     int len = to - from;
/*     */ 
/* 651 */     if (len < 7) {
/* 652 */       insertionSort(a, from, to);
/* 653 */       return;
/*     */     }
/*     */ 
/* 657 */     int mid = from + to >>> 1;
/* 658 */     mergeSort(supp, from, mid, a);
/* 659 */     mergeSort(supp, mid, to, a);
/*     */ 
/* 663 */     if (((Comparable)supp[(mid - 1)]).compareTo(supp[mid]) <= 0) {
/* 664 */       System.arraycopy(supp, from, a, from, len);
/* 665 */       return;
/*     */     }
/*     */ 
/* 669 */     int i = from; int p = from; for (int q = mid; i < to; i++)
/* 670 */       if ((q >= to) || ((p < mid) && (((Comparable)supp[p]).compareTo(supp[q]) <= 0))) a[i] = supp[(p++)]; else
/* 671 */         a[i] = supp[(q++)];
/*     */   }
/*     */ 
/*     */   public static <K> void mergeSort(K[] a, int from, int to)
/*     */   {
/* 685 */     mergeSort(a, from, to, (Object[])a.clone());
/*     */   }
/*     */ 
/*     */   public static <K> void mergeSort(K[] a)
/*     */   {
/* 696 */     mergeSort(a, 0, a.length);
/*     */   }
/*     */ 
/*     */   public static <K> void mergeSort(K[] a, int from, int to, Comparator<K> comp, K[] supp)
/*     */   {
/* 713 */     int len = to - from;
/*     */ 
/* 716 */     if (len < 7) {
/* 717 */       insertionSort(a, from, to, comp);
/* 718 */       return;
/*     */     }
/*     */ 
/* 722 */     int mid = from + to >>> 1;
/* 723 */     mergeSort(supp, from, mid, comp, a);
/* 724 */     mergeSort(supp, mid, to, comp, a);
/*     */ 
/* 728 */     if (comp.compare(supp[(mid - 1)], supp[mid]) <= 0) {
/* 729 */       System.arraycopy(supp, from, a, from, len);
/* 730 */       return;
/*     */     }
/*     */ 
/* 734 */     int i = from; int p = from; for (int q = mid; i < to; i++)
/* 735 */       if ((q >= to) || ((p < mid) && (comp.compare(supp[p], supp[q]) <= 0))) a[i] = supp[(p++)]; else
/* 736 */         a[i] = supp[(q++)];
/*     */   }
/*     */ 
/*     */   public static <K> void mergeSort(K[] a, int from, int to, Comparator<K> comp)
/*     */   {
/* 752 */     mergeSort(a, from, to, comp, (Object[])a.clone());
/*     */   }
/*     */ 
/*     */   public static <K> void mergeSort(K[] a, Comparator<K> comp)
/*     */   {
/* 765 */     mergeSort(a, 0, a.length, comp);
/*     */   }
/*     */ 
/*     */   public static <K> int binarySearch(K[] a, int from, int to, K key)
/*     */   {
/* 793 */     to--;
/* 794 */     while (from <= to) {
/* 795 */       int mid = from + to >>> 1;
/* 796 */       Object midVal = a[mid];
/*     */ 
/* 802 */       int cmp = ((Comparable)midVal).compareTo(key);
/* 803 */       if (cmp < 0) from = mid + 1;
/* 804 */       else if (cmp > 0) to = mid - 1; else {
/* 805 */         return mid;
/*     */       }
/*     */     }
/* 808 */     return -(from + 1);
/*     */   }
/*     */ 
/*     */   public static <K> int binarySearch(K[] a, K key)
/*     */   {
/* 830 */     return binarySearch(a, 0, a.length, key);
/*     */   }
/*     */ 
/*     */   public static <K> int binarySearch(K[] a, int from, int to, K key, Comparator<K> c)
/*     */   {
/* 856 */     to--;
/* 857 */     while (from <= to) {
/* 858 */       int mid = from + to >>> 1;
/* 859 */       Object midVal = a[mid];
/* 860 */       int cmp = c.compare(midVal, key);
/* 861 */       if (cmp < 0) from = mid + 1;
/* 862 */       else if (cmp > 0) to = mid - 1; else
/* 863 */         return mid;
/*     */     }
/* 865 */     return -(from + 1);
/*     */   }
/*     */ 
/*     */   public static <K> int binarySearch(K[] a, K key, Comparator<K> c)
/*     */   {
/* 888 */     return binarySearch(a, 0, a.length, key, c);
/*     */   }
/*     */ 
/*     */   public static <K> K[] shuffle(K[] a, int from, int to, Random random)
/*     */   {
/* 899 */     for (int i = to - from; i-- != 0; ) {
/* 900 */       int p = random.nextInt(i + 1);
/* 901 */       Object t = a[(from + i)];
/* 902 */       a[(from + i)] = a[(from + p)];
/* 903 */       a[(from + p)] = t;
/*     */     }
/* 905 */     return a;
/*     */   }
/*     */ 
/*     */   public static <K> K[] shuffle(K[] a, Random random)
/*     */   {
/* 914 */     for (int i = a.length; i-- != 0; ) {
/* 915 */       int p = random.nextInt(i + 1);
/* 916 */       Object t = a[i];
/* 917 */       a[i] = a[p];
/* 918 */       a[p] = t;
/*     */     }
/* 920 */     return a;
/*     */   }
/*     */ 
/*     */   public static <K> K[] reverse(K[] a)
/*     */   {
/* 928 */     int length = a.length;
/* 929 */     for (int i = length / 2; i-- != 0; ) {
/* 930 */       Object t = a[(length - i - 1)];
/* 931 */       a[(length - i - 1)] = a[i];
/* 932 */       a[i] = t;
/*     */     }
/* 934 */     return a;
/*     */   }
/*     */   private static final class ArrayHashStrategy<K> implements Hash.Strategy<K[]>, Serializable {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public int hashCode(K[] o) {
/* 940 */       return java.util.Arrays.hashCode(o);
/*     */     }
/*     */     public boolean equals(K[] a, K[] b) {
/* 943 */       return ObjectArrays.equals(a, b);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectArrays
 * JD-Core Version:    0.6.2
 */