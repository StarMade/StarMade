/*     */ package com.bulletphysics.util;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ArrayPool<T>
/*     */ {
/*     */   private Class componentType;
/*  40 */   private ObjectArrayList list = new ObjectArrayList();
/*     */   private Comparator comparator;
/*  42 */   private IntValue key = new IntValue(null);
/*     */ 
/* 133 */   private static Comparator floatComparator = new Comparator() {
/*     */     public int compare(Object o1, Object o2) {
/* 135 */       int len1 = (o1 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)o1).value : ((float[])o1).length;
/* 136 */       int len2 = (o2 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)o2).value : ((float[])o2).length;
/* 137 */       return len1 < len2 ? -1 : len1 > len2 ? 1 : 0;
/*     */     }
/* 133 */   };
/*     */ 
/* 141 */   private static Comparator intComparator = new Comparator() {
/*     */     public int compare(Object o1, Object o2) {
/* 143 */       int len1 = (o1 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)o1).value : ((int[])o1).length;
/* 144 */       int len2 = (o2 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)o2).value : ((int[])o2).length;
/* 145 */       return len1 < len2 ? -1 : len1 > len2 ? 1 : 0;
/*     */     }
/* 141 */   };
/*     */ 
/* 149 */   private static Comparator objectComparator = new Comparator() {
/*     */     public int compare(Object o1, Object o2) {
/* 151 */       int len1 = (o1 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)o1).value : ((Object[])o1).length;
/* 152 */       int len2 = (o2 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)o2).value : ((Object[])o2).length;
/* 153 */       return len1 < len2 ? -1 : len1 > len2 ? 1 : 0;
/*     */     }
/* 149 */   };
/*     */ 
/* 163 */   private static ThreadLocal<Map> threadLocal = new ThreadLocal()
/*     */   {
/*     */     protected Map initialValue() {
/* 166 */       return new HashMap();
/*     */     }
/* 163 */   };
/*     */ 
/*     */   public ArrayPool(Class componentType)
/*     */   {
/*  50 */     this.componentType = componentType;
/*     */ 
/*  52 */     if (componentType == Float.TYPE) {
/*  53 */       this.comparator = floatComparator;
/*     */     }
/*  55 */     else if (componentType == Integer.TYPE) {
/*  56 */       this.comparator = intComparator;
/*     */     }
/*  58 */     else if (!componentType.isPrimitive()) {
/*  59 */       this.comparator = objectComparator;
/*     */     }
/*     */     else
/*  62 */       throw new UnsupportedOperationException("unsupported type " + componentType);
/*     */   }
/*     */ 
/*     */   private T create(int length)
/*     */   {
/*  68 */     return Array.newInstance(this.componentType, length);
/*     */   }
/*     */ 
/*     */   public T getFixed(int length)
/*     */   {
/*  80 */     this.key.value = length;
/*  81 */     int index = Collections.binarySearch(this.list, this.key, this.comparator);
/*  82 */     if (index < 0) {
/*  83 */       return create(length);
/*     */     }
/*  85 */     return this.list.remove(index);
/*     */   }
/*     */ 
/*     */   public T getAtLeast(int length)
/*     */   {
/*  97 */     this.key.value = length;
/*  98 */     int index = Collections.binarySearch(this.list, this.key, this.comparator);
/*  99 */     if (index < 0) {
/* 100 */       index = -index - 1;
/* 101 */       if (index < this.list.size()) {
/* 102 */         return this.list.remove(index);
/*     */       }
/*     */ 
/* 105 */       return create(length);
/*     */     }
/*     */ 
/* 108 */     return this.list.remove(index);
/*     */   }
/*     */ 
/*     */   public void release(T array)
/*     */   {
/* 118 */     int index = Collections.binarySearch(this.list, array, this.comparator);
/* 119 */     if (index < 0) index = -index - 1;
/* 120 */     this.list.add(index, array);
/*     */ 
/* 123 */     if (this.comparator == objectComparator) {
/* 124 */       Object[] objArray = (Object[])array;
/* 125 */       for (int i = 0; i < objArray.length; i++)
/* 126 */         objArray[i] = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static <T> ArrayPool<T> get(Class cls)
/*     */   {
/* 178 */     Map map = (Map)threadLocal.get();
/*     */ 
/* 180 */     ArrayPool pool = (ArrayPool)map.get(cls);
/* 181 */     if (pool == null) {
/* 182 */       pool = new ArrayPool(cls);
/* 183 */       map.put(cls, pool);
/*     */     }
/*     */ 
/* 186 */     return pool;
/*     */   }
/*     */ 
/*     */   public static void cleanCurrentThread() {
/* 190 */     threadLocal.remove();
/*     */   }
/*     */ 
/*     */   private static class IntValue
/*     */   {
/*     */     public int value;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.util.ArrayPool
 * JD-Core Version:    0.6.2
 */