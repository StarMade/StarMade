/*     */ package com.bulletphysics.util;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ObjectPool<T>
/*     */ {
/*     */   private Class<T> cls;
/*  37 */   private ObjectArrayList<T> list = new ObjectArrayList();
/*     */ 
/*  80 */   private static ThreadLocal<Map> threadLocal = new ThreadLocal()
/*     */   {
/*     */     protected Map initialValue() {
/*  83 */       return new HashMap();
/*     */     }
/*  80 */   };
/*     */ 
/*     */   public ObjectPool(Class<T> cls)
/*     */   {
/*  40 */     this.cls = cls;
/*     */   }
/*     */ 
/*     */   private T create() {
/*     */     try {
/*  45 */       return this.cls.newInstance();
/*     */     }
/*     */     catch (InstantiationException e) {
/*  48 */       throw new IllegalStateException(e);
/*     */     }
/*     */     catch (IllegalAccessException e) {
/*  51 */       throw new IllegalStateException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public T get()
/*     */   {
/*  61 */     if (this.list.size() > 0) {
/*  62 */       return this.list.remove(this.list.size() - 1);
/*     */     }
/*     */ 
/*  65 */     return create();
/*     */   }
/*     */ 
/*     */   public void release(T obj)
/*     */   {
/*  75 */     this.list.add(obj);
/*     */   }
/*     */ 
/*     */   public static <T> ObjectPool<T> get(Class<T> cls)
/*     */   {
/*  95 */     Map map = (Map)threadLocal.get();
/*     */ 
/*  97 */     ObjectPool pool = (ObjectPool)map.get(cls);
/*  98 */     if (pool == null) {
/*  99 */       pool = new ObjectPool(cls);
/* 100 */       map.put(cls, pool);
/*     */     }
/*     */ 
/* 103 */     return pool;
/*     */   }
/*     */ 
/*     */   public static void cleanCurrentThread() {
/* 107 */     threadLocal.remove();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.util.ObjectPool
 * JD-Core Version:    0.6.2
 */