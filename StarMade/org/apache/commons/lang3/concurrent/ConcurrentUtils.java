/*     */ package org.apache.commons.lang3.concurrent;
/*     */ 
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ 
/*     */ public class ConcurrentUtils
/*     */ {
/*     */   public static ConcurrentException extractCause(ExecutionException ex)
/*     */   {
/*  61 */     if ((ex == null) || (ex.getCause() == null)) {
/*  62 */       return null;
/*     */     }
/*     */ 
/*  65 */     throwCause(ex);
/*  66 */     return new ConcurrentException(ex.getMessage(), ex.getCause());
/*     */   }
/*     */ 
/*     */   public static ConcurrentRuntimeException extractCauseUnchecked(ExecutionException ex)
/*     */   {
/*  83 */     if ((ex == null) || (ex.getCause() == null)) {
/*  84 */       return null;
/*     */     }
/*     */ 
/*  87 */     throwCause(ex);
/*  88 */     return new ConcurrentRuntimeException(ex.getMessage(), ex.getCause());
/*     */   }
/*     */ 
/*     */   public static void handleCause(ExecutionException ex)
/*     */     throws ConcurrentException
/*     */   {
/* 106 */     ConcurrentException cex = extractCause(ex);
/*     */ 
/* 108 */     if (cex != null)
/* 109 */       throw cex;
/*     */   }
/*     */ 
/*     */   public static void handleCauseUnchecked(ExecutionException ex)
/*     */   {
/* 127 */     ConcurrentRuntimeException crex = extractCauseUnchecked(ex);
/*     */ 
/* 129 */     if (crex != null)
/* 130 */       throw crex;
/*     */   }
/*     */ 
/*     */   static Throwable checkedException(Throwable ex)
/*     */   {
/* 144 */     if ((ex != null) && (!(ex instanceof RuntimeException)) && (!(ex instanceof Error)))
/*     */     {
/* 146 */       return ex;
/*     */     }
/* 148 */     throw new IllegalArgumentException("Not a checked exception: " + ex);
/*     */   }
/*     */ 
/*     */   private static void throwCause(ExecutionException ex)
/*     */   {
/* 159 */     if ((ex.getCause() instanceof RuntimeException)) {
/* 160 */       throw ((RuntimeException)ex.getCause());
/*     */     }
/*     */ 
/* 163 */     if ((ex.getCause() instanceof Error))
/* 164 */       throw ((Error)ex.getCause());
/*     */   }
/*     */ 
/*     */   public static <T> T initialize(ConcurrentInitializer<T> initializer)
/*     */     throws ConcurrentException
/*     */   {
/* 184 */     return initializer != null ? initializer.get() : null;
/*     */   }
/*     */ 
/*     */   public static <T> T initializeUnchecked(ConcurrentInitializer<T> initializer)
/*     */   {
/*     */     try
/*     */     {
/* 202 */       return initialize(initializer);
/*     */     } catch (ConcurrentException cex) {
/* 204 */       throw new ConcurrentRuntimeException(cex.getCause());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static <K, V> V putIfAbsent(ConcurrentMap<K, V> map, K key, V value)
/*     */   {
/* 242 */     if (map == null) {
/* 243 */       return null;
/*     */     }
/*     */ 
/* 246 */     Object result = map.putIfAbsent(key, value);
/* 247 */     return result != null ? result : value;
/*     */   }
/*     */ 
/*     */   public static <K, V> V createIfAbsent(ConcurrentMap<K, V> map, K key, ConcurrentInitializer<V> init)
/*     */     throws ConcurrentException
/*     */   {
/* 272 */     if ((map == null) || (init == null)) {
/* 273 */       return null;
/*     */     }
/*     */ 
/* 276 */     Object value = map.get(key);
/* 277 */     if (value == null) {
/* 278 */       return putIfAbsent(map, key, init.get());
/*     */     }
/* 280 */     return value;
/*     */   }
/*     */ 
/*     */   public static <K, V> V createIfAbsentUnchecked(ConcurrentMap<K, V> map, K key, ConcurrentInitializer<V> init)
/*     */   {
/*     */     try
/*     */     {
/* 301 */       return createIfAbsent(map, key, init);
/*     */     } catch (ConcurrentException cex) {
/* 303 */       throw new ConcurrentRuntimeException(cex.getCause());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static <T> Future<T> constantFuture(T value)
/*     */   {
/* 324 */     return new ConstantFuture(value);
/*     */   }
/*     */ 
/*     */   static final class ConstantFuture<T>
/*     */     implements Future<T>
/*     */   {
/*     */     private final T value;
/*     */ 
/*     */     ConstantFuture(T value)
/*     */     {
/* 342 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public boolean isDone()
/*     */     {
/* 351 */       return true;
/*     */     }
/*     */ 
/*     */     public T get()
/*     */     {
/* 358 */       return this.value;
/*     */     }
/*     */ 
/*     */     public T get(long timeout, TimeUnit unit)
/*     */     {
/* 366 */       return this.value;
/*     */     }
/*     */ 
/*     */     public boolean isCancelled()
/*     */     {
/* 374 */       return false;
/*     */     }
/*     */ 
/*     */     public boolean cancel(boolean mayInterruptIfRunning)
/*     */     {
/* 382 */       return false;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.ConcurrentUtils
 * JD-Core Version:    0.6.2
 */