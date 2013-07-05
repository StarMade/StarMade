/*     */ package com.bulletphysics.linearmath;
/*     */ 
/*     */ import com.bulletphysics.util.FloatArrayList;
/*     */ import com.bulletphysics.util.IntArrayList;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class MiscUtil
/*     */ {
/*     */   public static int getListCapacityForHash(ObjectArrayList<?> list)
/*     */   {
/*  39 */     return getListCapacityForHash(list.size());
/*     */   }
/*     */ 
/*     */   public static int getListCapacityForHash(int size) {
/*  43 */     int n = 2;
/*  44 */     while (n < size) {
/*  45 */       n <<= 1;
/*     */     }
/*  47 */     return n;
/*     */   }
/*     */ 
/*     */   public static <T> void ensureIndex(ObjectArrayList<T> list, int index, T value)
/*     */   {
/*  55 */     while (list.size() <= index)
/*  56 */       list.add(value);
/*     */   }
/*     */ 
/*     */   public static void resize(IntArrayList list, int size, int value)
/*     */   {
/*  64 */     while (list.size() < size) {
/*  65 */       list.add(value);
/*     */     }
/*     */ 
/*  68 */     while (list.size() > size)
/*  69 */       list.remove(list.size() - 1);
/*     */   }
/*     */ 
/*     */   public static void resize(FloatArrayList list, int size, float value)
/*     */   {
/*  77 */     while (list.size() < size) {
/*  78 */       list.add(value);
/*     */     }
/*     */ 
/*  81 */     while (list.size() > size)
/*  82 */       list.remove(list.size() - 1);
/*     */   }
/*     */ 
/*     */   public static <T> void resize(ObjectArrayList<T> list, int size, Class<T> valueCls)
/*     */   {
/*     */     try
/*     */     {
/*  92 */       while (list.size() < size) {
/*  93 */         list.add(valueCls != null ? valueCls.newInstance() : null);
/*     */       }
/*     */ 
/*  96 */       while (list.size() > size)
/*  97 */         list.removeQuick(list.size() - 1);
/*     */     }
/*     */     catch (IllegalAccessException e)
/*     */     {
/* 101 */       throw new IllegalStateException(e);
/*     */     }
/*     */     catch (InstantiationException e) {
/* 104 */       throw new IllegalStateException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static <T> int indexOf(T[] array, T obj)
/*     */   {
/* 114 */     for (int i = 0; i < array.length; i++) {
/* 115 */       if (array[i] == obj) return i;
/*     */     }
/* 117 */     return -1;
/*     */   }
/*     */ 
/*     */   public static float GEN_clamped(float a, float lb, float ub) {
/* 121 */     return ub < a ? ub : a < lb ? lb : a;
/*     */   }
/*     */ 
/*     */   private static <T> void downHeap(ObjectArrayList<T> pArr, int k, int n, Comparator<T> comparator)
/*     */   {
/* 128 */     Object temp = pArr.getQuick(k - 1);
/*     */ 
/* 130 */     while (k <= n / 2) {
/* 131 */       int child = 2 * k;
/*     */ 
/* 133 */       if ((child < n) && (comparator.compare(pArr.getQuick(child - 1), pArr.getQuick(child)) < 0)) {
/* 134 */         child++;
/*     */       }
/*     */ 
/* 137 */       if (comparator.compare(temp, pArr.getQuick(child - 1)) >= 0)
/*     */         break;
/* 139 */       pArr.setQuick(k - 1, pArr.getQuick(child - 1));
/* 140 */       k = child;
/*     */     }
/*     */ 
/* 146 */     pArr.setQuick(k - 1, temp);
/*     */   }
/*     */ 
/*     */   public static <T> void heapSort(ObjectArrayList<T> list, Comparator<T> comparator)
/*     */   {
/* 157 */     int n = list.size();
/* 158 */     for (int k = n / 2; k > 0; k--) {
/* 159 */       downHeap(list, k, n, comparator);
/*     */     }
/*     */ 
/* 163 */     while (n >= 1) {
/* 164 */       swap(list, 0, n - 1);
/*     */ 
/* 166 */       n -= 1;
/*     */ 
/* 168 */       downHeap(list, 1, n, comparator);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static <T> void swap(ObjectArrayList<T> list, int index0, int index1) {
/* 173 */     Object temp = list.getQuick(index0);
/* 174 */     list.setQuick(index0, list.getQuick(index1));
/* 175 */     list.setQuick(index1, temp);
/*     */   }
/*     */ 
/*     */   public static <T> void quickSort(ObjectArrayList<T> list, Comparator<T> comparator)
/*     */   {
/* 183 */     if (list.size() > 1)
/* 184 */       quickSortInternal(list, comparator, 0, list.size() - 1);
/*     */   }
/*     */ 
/*     */   private static <T> void quickSortInternal(ObjectArrayList<T> list, Comparator<T> comparator, int lo, int hi)
/*     */   {
/* 191 */     int i = lo; int j = hi;
/* 192 */     Object x = list.getQuick((lo + hi) / 2);
/*     */     do
/*     */     {
/* 196 */       while (comparator.compare(list.getQuick(i), x) < 0) i++;
/* 197 */       while (comparator.compare(x, list.getQuick(j)) < 0) j--;
/*     */ 
/* 199 */       if (i <= j) {
/* 200 */         swap(list, i, j);
/* 201 */         i++;
/* 202 */         j--;
/*     */       }
/*     */     }
/* 205 */     while (i <= j);
/*     */ 
/* 208 */     if (lo < j) {
/* 209 */       quickSortInternal(list, comparator, lo, j);
/*     */     }
/* 211 */     if (i < hi)
/* 212 */       quickSortInternal(list, comparator, i, hi);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.MiscUtil
 * JD-Core Version:    0.6.2
 */