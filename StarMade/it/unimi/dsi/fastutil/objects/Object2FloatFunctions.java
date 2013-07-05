/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class Object2FloatFunctions
/*     */ {
/*  71 */   public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
/*     */ 
/*     */   public static <K> Object2FloatFunction<K> singleton(K key, float value)
/*     */   {
/* 109 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static <K> Object2FloatFunction<K> singleton(K key, Float value)
/*     */   {
/* 124 */     return new Singleton(key, value.floatValue());
/*     */   }
/*     */ 
/*     */   public static <K> Object2FloatFunction<K> synchronize(Object2FloatFunction<K> f)
/*     */   {
/* 175 */     return new SynchronizedFunction(f);
/*     */   }
/*     */ 
/*     */   public static <K> Object2FloatFunction<K> synchronize(Object2FloatFunction<K> f, Object sync)
/*     */   {
/* 183 */     return new SynchronizedFunction(f, sync);
/*     */   }
/*     */ 
/*     */   public static <K> Object2FloatFunction<K> unmodifiable(Object2FloatFunction<K> f)
/*     */   {
/* 208 */     return new UnmodifiableFunction(f);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableFunction<K> extends AbstractObject2FloatFunction<K>
/*     */     implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Object2FloatFunction<K> function;
/*     */ 
/*     */     protected UnmodifiableFunction(Object2FloatFunction<K> f)
/*     */     {
/* 189 */       if (f == null) throw new NullPointerException();
/* 190 */       this.function = f;
/*     */     }
/* 192 */     public int size() { return this.function.size(); } 
/* 193 */     public boolean containsKey(Object k) { return this.function.containsKey(k); } 
/* 194 */     public float defaultReturnValue() { return this.function.defaultReturnValue(); } 
/* 195 */     public void defaultReturnValue(float defRetValue) { throw new UnsupportedOperationException(); } 
/* 196 */     public float put(K k, float v) { throw new UnsupportedOperationException(); } 
/* 197 */     public void clear() { throw new UnsupportedOperationException(); } 
/* 198 */     public String toString() { return this.function.toString(); } 
/* 199 */     public float removeFloat(Object k) { throw new UnsupportedOperationException(); } 
/* 200 */     public float getFloat(Object k) { return this.function.getFloat(k); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedFunction<K> extends AbstractObject2FloatFunction<K>
/*     */     implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Object2FloatFunction<K> function;
/*     */     protected final Object sync;
/*     */ 
/*     */     protected SynchronizedFunction(Object2FloatFunction<K> f, Object sync)
/*     */     {
/* 140 */       if (f == null) throw new NullPointerException();
/* 141 */       this.function = f;
/* 142 */       this.sync = sync;
/*     */     }
/*     */ 
/*     */     protected SynchronizedFunction(Object2FloatFunction<K> f) {
/* 146 */       if (f == null) throw new NullPointerException();
/* 147 */       this.function = f;
/* 148 */       this.sync = this;
/*     */     }
/*     */     public int size() {
/* 151 */       synchronized (this.sync) { return this.function.size(); }  } 
/* 152 */     public boolean containsKey(Object k) { synchronized (this.sync) { return this.function.containsKey(k); }  } 
/*     */     public float defaultReturnValue() {
/* 154 */       synchronized (this.sync) { return this.function.defaultReturnValue(); }  } 
/* 155 */     public void defaultReturnValue(float defRetValue) { synchronized (this.sync) { this.function.defaultReturnValue(defRetValue); }  } 
/*     */     public float put(K k, float v) {
/* 157 */       synchronized (this.sync) { return this.function.put(k, v); } 
/*     */     }
/* 159 */     public void clear() { synchronized (this.sync) { this.function.clear(); }  } 
/* 160 */     public String toString() { synchronized (this.sync) { return this.function.toString(); } }
/*     */ 
/*     */     public Float put(K k, Float v) {
/* 163 */       synchronized (this.sync) { return (Float)this.function.put(k, v); }  } 
/* 164 */     public Float get(Object k) { synchronized (this.sync) { return (Float)this.function.get(k); }  } 
/* 165 */     public Float remove(Object k) { synchronized (this.sync) { return (Float)this.function.remove(k); }  } 
/* 166 */     public float removeFloat(Object k) { synchronized (this.sync) { return this.function.removeFloat(k); }  } 
/* 167 */     public float getFloat(Object k) { synchronized (this.sync) { return this.function.getFloat(k); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton<K> extends AbstractObject2FloatFunction<K>
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final K key;
/*     */     protected final float value;
/*     */ 
/*     */     protected Singleton(K key, float value)
/*     */     {
/*  86 */       this.key = key;
/*  87 */       this.value = value;
/*     */     }
/*     */     public boolean containsKey(Object k) {
/*  90 */       return this.key == null ? false : k == null ? true : this.key.equals(k);
/*     */     }
/*  92 */     public float getFloat(Object k) { if (this.key == null ? k == null : this.key.equals(k)) return this.value; return this.defRetValue; } 
/*     */     public int size() {
/*  94 */       return 1;
/*     */     }
/*  96 */     public Object clone() { return this; }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptyFunction<K> extends AbstractObject2FloatFunction<K>
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public float getFloat(Object k)
/*     */     {
/*  60 */       return 0.0F; } 
/*  61 */     public boolean containsKey(Object k) { return false; } 
/*  62 */     public float defaultReturnValue() { return 0.0F; } 
/*  63 */     public void defaultReturnValue(float defRetValue) { throw new UnsupportedOperationException(); } 
/*  64 */     public int size() { return 0; } 
/*     */     public void clear() {  } 
/*  66 */     private Object readResolve() { return Object2FloatFunctions.EMPTY_FUNCTION; } 
/*  67 */     public Object clone() { return Object2FloatFunctions.EMPTY_FUNCTION; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2FloatFunctions
 * JD-Core Version:    0.6.2
 */