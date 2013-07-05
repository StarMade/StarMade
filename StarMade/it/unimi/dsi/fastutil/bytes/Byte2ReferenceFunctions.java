/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class Byte2ReferenceFunctions
/*     */ {
/*  72 */   public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
/*     */ 
/*     */   public static <V> Byte2ReferenceFunction<V> singleton(byte key, V value)
/*     */   {
/* 109 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static <V> Byte2ReferenceFunction<V> singleton(Byte key, V value)
/*     */   {
/* 124 */     return new Singleton(key.byteValue(), value);
/*     */   }
/*     */ 
/*     */   public static <V> Byte2ReferenceFunction<V> synchronize(Byte2ReferenceFunction<V> f)
/*     */   {
/* 187 */     return new SynchronizedFunction(f);
/*     */   }
/*     */ 
/*     */   public static <V> Byte2ReferenceFunction<V> synchronize(Byte2ReferenceFunction<V> f, Object sync)
/*     */   {
/* 197 */     return new SynchronizedFunction(f, sync);
/*     */   }
/*     */ 
/*     */   public static <V> Byte2ReferenceFunction<V> unmodifiable(Byte2ReferenceFunction<V> f)
/*     */   {
/* 244 */     return new UnmodifiableFunction(f);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableFunction<V> extends AbstractByte2ReferenceFunction<V>
/*     */     implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Byte2ReferenceFunction<V> function;
/*     */ 
/*     */     protected UnmodifiableFunction(Byte2ReferenceFunction<V> f)
/*     */     {
/* 210 */       if (f == null) throw new NullPointerException();
/* 211 */       this.function = f;
/*     */     }
/*     */     public int size() {
/* 214 */       return this.function.size(); } 
/* 215 */     public boolean containsKey(byte k) { return this.function.containsKey(k); } 
/*     */     public V defaultReturnValue() {
/* 217 */       return this.function.defaultReturnValue(); } 
/* 218 */     public void defaultReturnValue(V defRetValue) { throw new UnsupportedOperationException(); } 
/*     */     public V put(byte k, V v) {
/* 220 */       throw new UnsupportedOperationException();
/*     */     }
/* 222 */     public void clear() { throw new UnsupportedOperationException(); } 
/* 223 */     public String toString() { return this.function.toString(); }
/*     */ 
/*     */     public V remove(byte k) {
/* 226 */       throw new UnsupportedOperationException(); } 
/* 227 */     public V get(byte k) { return this.function.get(k); } 
/* 228 */     public boolean containsKey(Object ok) { return this.function.containsKey(ok); }
/*     */ 
/*     */     public V remove(Object k)
/*     */     {
/* 232 */       throw new UnsupportedOperationException(); } 
/* 233 */     public V get(Object k) { return this.function.get(k); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedFunction<V> extends AbstractByte2ReferenceFunction<V>
/*     */     implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Byte2ReferenceFunction<V> function;
/*     */     protected final Object sync;
/*     */ 
/*     */     protected SynchronizedFunction(Byte2ReferenceFunction<V> f, Object sync)
/*     */     {
/* 140 */       if (f == null) throw new NullPointerException();
/* 141 */       this.function = f;
/* 142 */       this.sync = sync;
/*     */     }
/*     */ 
/*     */     protected SynchronizedFunction(Byte2ReferenceFunction<V> f) {
/* 146 */       if (f == null) throw new NullPointerException();
/* 147 */       this.function = f;
/* 148 */       this.sync = this;
/*     */     }
/*     */     public int size() {
/* 151 */       synchronized (this.sync) { return this.function.size(); }  } 
/* 152 */     public boolean containsKey(byte k) { synchronized (this.sync) { return this.function.containsKey(k); }  } 
/*     */     public V defaultReturnValue() {
/* 154 */       synchronized (this.sync) { return this.function.defaultReturnValue(); }  } 
/* 155 */     public void defaultReturnValue(V defRetValue) { synchronized (this.sync) { this.function.defaultReturnValue(defRetValue); }  } 
/*     */     public V put(byte k, V v) {
/* 157 */       synchronized (this.sync) { return this.function.put(k, v); } 
/*     */     }
/* 159 */     public void clear() { synchronized (this.sync) { this.function.clear(); }  } 
/* 160 */     public String toString() { synchronized (this.sync) { return this.function.toString(); } }
/*     */ 
/*     */     public V put(Byte k, V v) {
/* 163 */       synchronized (this.sync) { return this.function.put(k, v); }  } 
/* 164 */     public V get(Object k) { synchronized (this.sync) { return this.function.get(k); }  } 
/* 165 */     public V remove(Object k) { synchronized (this.sync) { return this.function.remove(k); } }
/*     */ 
/*     */     public V remove(byte k)
/*     */     {
/* 169 */       synchronized (this.sync) { return this.function.remove(k); }  } 
/* 170 */     public V get(byte k) { synchronized (this.sync) { return this.function.get(k); }  } 
/* 171 */     public boolean containsKey(Object ok) { synchronized (this.sync) { return this.function.containsKey(ok); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton<V> extends AbstractByte2ReferenceFunction<V>
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final byte key;
/*     */     protected final V value;
/*     */ 
/*     */     protected Singleton(byte key, V value)
/*     */     {
/*  86 */       this.key = key;
/*  87 */       this.value = value;
/*     */     }
/*     */     public boolean containsKey(byte k) {
/*  90 */       return this.key == k;
/*     */     }
/*  92 */     public V get(byte k) { if (this.key == k) return this.value; return this.defRetValue; } 
/*     */     public int size() {
/*  94 */       return 1;
/*     */     }
/*  96 */     public Object clone() { return this; }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptyFunction<V> extends AbstractByte2ReferenceFunction<V>
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public V get(byte k)
/*     */     {
/*  60 */       return null; } 
/*  61 */     public boolean containsKey(byte k) { return false; } 
/*  62 */     public V defaultReturnValue() { return null; } 
/*  63 */     public void defaultReturnValue(V defRetValue) { throw new UnsupportedOperationException(); } 
/*  64 */     public V get(Object k) { return null; } 
/*  65 */     public int size() { return 0; } 
/*     */     public void clear() {  } 
/*  67 */     private Object readResolve() { return Byte2ReferenceFunctions.EMPTY_FUNCTION; } 
/*  68 */     public Object clone() { return Byte2ReferenceFunctions.EMPTY_FUNCTION; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ReferenceFunctions
 * JD-Core Version:    0.6.2
 */