/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class Object2ObjectFunctions
/*     */ {
/*  70 */   public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
/*     */ 
/*     */   public static <K, V> Object2ObjectFunction<K, V> singleton(K key, V value)
/*     */   {
/* 109 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static <K, V> Object2ObjectFunction<K, V> synchronize(Object2ObjectFunction<K, V> f)
/*     */   {
/* 142 */     return new SynchronizedFunction(f);
/*     */   }
/*     */ 
/*     */   public static <K, V> Object2ObjectFunction<K, V> synchronize(Object2ObjectFunction<K, V> f, Object sync)
/*     */   {
/* 150 */     return new SynchronizedFunction(f, sync);
/*     */   }
/*     */ 
/*     */   public static <K, V> Object2ObjectFunction<K, V> unmodifiable(Object2ObjectFunction<K, V> f)
/*     */   {
/* 175 */     return new UnmodifiableFunction(f);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableFunction<K, V> extends AbstractObject2ObjectFunction<K, V>
/*     */     implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Object2ObjectFunction<K, V> function;
/*     */ 
/*     */     protected UnmodifiableFunction(Object2ObjectFunction<K, V> f)
/*     */     {
/* 156 */       if (f == null) throw new NullPointerException();
/* 157 */       this.function = f;
/*     */     }
/* 159 */     public int size() { return this.function.size(); } 
/* 160 */     public boolean containsKey(Object k) { return this.function.containsKey(k); } 
/* 161 */     public V defaultReturnValue() { return this.function.defaultReturnValue(); } 
/* 162 */     public void defaultReturnValue(V defRetValue) { throw new UnsupportedOperationException(); } 
/* 163 */     public V put(K k, V v) { throw new UnsupportedOperationException(); } 
/* 164 */     public void clear() { throw new UnsupportedOperationException(); } 
/* 165 */     public String toString() { return this.function.toString(); } 
/* 166 */     public V remove(Object k) { throw new UnsupportedOperationException(); } 
/* 167 */     public V get(Object k) { return this.function.get(k); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedFunction<K, V> extends AbstractObject2ObjectFunction<K, V>
/*     */     implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Object2ObjectFunction<K, V> function;
/*     */     protected final Object sync;
/*     */ 
/*     */     protected SynchronizedFunction(Object2ObjectFunction<K, V> f, Object sync)
/*     */     {
/* 117 */       if (f == null) throw new NullPointerException();
/* 118 */       this.function = f;
/* 119 */       this.sync = sync;
/*     */     }
/*     */     protected SynchronizedFunction(Object2ObjectFunction<K, V> f) {
/* 122 */       if (f == null) throw new NullPointerException();
/* 123 */       this.function = f;
/* 124 */       this.sync = this;
/*     */     }
/* 126 */     public int size() { synchronized (this.sync) { return this.function.size(); }  } 
/* 127 */     public boolean containsKey(Object k) { synchronized (this.sync) { return this.function.containsKey(k); }  } 
/* 128 */     public V defaultReturnValue() { synchronized (this.sync) { return this.function.defaultReturnValue(); }  } 
/* 129 */     public void defaultReturnValue(V defRetValue) { synchronized (this.sync) { this.function.defaultReturnValue(defRetValue); }  } 
/* 130 */     public V put(K k, V v) { synchronized (this.sync) { return this.function.put(k, v); }  } 
/* 131 */     public void clear() { synchronized (this.sync) { this.function.clear(); }  } 
/* 132 */     public String toString() { synchronized (this.sync) { return this.function.toString(); }  } 
/* 133 */     public V remove(Object k) { synchronized (this.sync) { return this.function.remove(k); }  } 
/* 134 */     public V get(Object k) { synchronized (this.sync) { return this.function.get(k); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton<K, V> extends AbstractObject2ObjectFunction<K, V>
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final K key;
/*     */     protected final V value;
/*     */ 
/*     */     protected Singleton(K key, V value)
/*     */     {
/*  86 */       this.key = key;
/*  87 */       this.value = value;
/*     */     }
/*     */     public boolean containsKey(Object k) {
/*  90 */       return this.key == null ? false : k == null ? true : this.key.equals(k);
/*     */     }
/*  92 */     public V get(Object k) { if (this.key == null ? k == null : this.key.equals(k)) return this.value; return this.defRetValue; } 
/*     */     public int size() {
/*  94 */       return 1;
/*     */     }
/*  96 */     public Object clone() { return this; }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptyFunction<K, V> extends AbstractObject2ObjectFunction<K, V>
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public V get(Object k)
/*     */     {
/*  59 */       return null; } 
/*  60 */     public boolean containsKey(Object k) { return false; } 
/*  61 */     public V defaultReturnValue() { return null; } 
/*  62 */     public void defaultReturnValue(V defRetValue) { throw new UnsupportedOperationException(); } 
/*  63 */     public int size() { return 0; } 
/*     */     public void clear() {  } 
/*  65 */     private Object readResolve() { return Object2ObjectFunctions.EMPTY_FUNCTION; } 
/*  66 */     public Object clone() { return Object2ObjectFunctions.EMPTY_FUNCTION; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ObjectFunctions
 * JD-Core Version:    0.6.2
 */