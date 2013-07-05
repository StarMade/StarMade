/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class Reference2DoubleFunctions
/*     */ {
/*  71 */   public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
/*     */ 
/*     */   public static <K> Reference2DoubleFunction<K> singleton(K key, double value)
/*     */   {
/* 109 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static <K> Reference2DoubleFunction<K> singleton(K key, Double value)
/*     */   {
/* 124 */     return new Singleton(key, value.doubleValue());
/*     */   }
/*     */ 
/*     */   public static <K> Reference2DoubleFunction<K> synchronize(Reference2DoubleFunction<K> f)
/*     */   {
/* 175 */     return new SynchronizedFunction(f);
/*     */   }
/*     */ 
/*     */   public static <K> Reference2DoubleFunction<K> synchronize(Reference2DoubleFunction<K> f, Object sync)
/*     */   {
/* 183 */     return new SynchronizedFunction(f, sync);
/*     */   }
/*     */ 
/*     */   public static <K> Reference2DoubleFunction<K> unmodifiable(Reference2DoubleFunction<K> f)
/*     */   {
/* 208 */     return new UnmodifiableFunction(f);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableFunction<K> extends AbstractReference2DoubleFunction<K>
/*     */     implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Reference2DoubleFunction<K> function;
/*     */ 
/*     */     protected UnmodifiableFunction(Reference2DoubleFunction<K> f)
/*     */     {
/* 189 */       if (f == null) throw new NullPointerException();
/* 190 */       this.function = f;
/*     */     }
/* 192 */     public int size() { return this.function.size(); } 
/* 193 */     public boolean containsKey(Object k) { return this.function.containsKey(k); } 
/* 194 */     public double defaultReturnValue() { return this.function.defaultReturnValue(); } 
/* 195 */     public void defaultReturnValue(double defRetValue) { throw new UnsupportedOperationException(); } 
/* 196 */     public double put(K k, double v) { throw new UnsupportedOperationException(); } 
/* 197 */     public void clear() { throw new UnsupportedOperationException(); } 
/* 198 */     public String toString() { return this.function.toString(); } 
/* 199 */     public double removeDouble(Object k) { throw new UnsupportedOperationException(); } 
/* 200 */     public double getDouble(Object k) { return this.function.getDouble(k); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedFunction<K> extends AbstractReference2DoubleFunction<K>
/*     */     implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Reference2DoubleFunction<K> function;
/*     */     protected final Object sync;
/*     */ 
/*     */     protected SynchronizedFunction(Reference2DoubleFunction<K> f, Object sync)
/*     */     {
/* 140 */       if (f == null) throw new NullPointerException();
/* 141 */       this.function = f;
/* 142 */       this.sync = sync;
/*     */     }
/*     */ 
/*     */     protected SynchronizedFunction(Reference2DoubleFunction<K> f) {
/* 146 */       if (f == null) throw new NullPointerException();
/* 147 */       this.function = f;
/* 148 */       this.sync = this;
/*     */     }
/*     */     public int size() {
/* 151 */       synchronized (this.sync) { return this.function.size(); }  } 
/* 152 */     public boolean containsKey(Object k) { synchronized (this.sync) { return this.function.containsKey(k); }  } 
/*     */     public double defaultReturnValue() {
/* 154 */       synchronized (this.sync) { return this.function.defaultReturnValue(); }  } 
/* 155 */     public void defaultReturnValue(double defRetValue) { synchronized (this.sync) { this.function.defaultReturnValue(defRetValue); }  } 
/*     */     public double put(K k, double v) {
/* 157 */       synchronized (this.sync) { return this.function.put(k, v); } 
/*     */     }
/* 159 */     public void clear() { synchronized (this.sync) { this.function.clear(); }  } 
/* 160 */     public String toString() { synchronized (this.sync) { return this.function.toString(); } }
/*     */ 
/*     */     public Double put(K k, Double v) {
/* 163 */       synchronized (this.sync) { return (Double)this.function.put(k, v); }  } 
/* 164 */     public Double get(Object k) { synchronized (this.sync) { return (Double)this.function.get(k); }  } 
/* 165 */     public Double remove(Object k) { synchronized (this.sync) { return (Double)this.function.remove(k); }  } 
/* 166 */     public double removeDouble(Object k) { synchronized (this.sync) { return this.function.removeDouble(k); }  } 
/* 167 */     public double getDouble(Object k) { synchronized (this.sync) { return this.function.getDouble(k); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton<K> extends AbstractReference2DoubleFunction<K>
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final K key;
/*     */     protected final double value;
/*     */ 
/*     */     protected Singleton(K key, double value)
/*     */     {
/*  86 */       this.key = key;
/*  87 */       this.value = value;
/*     */     }
/*     */     public boolean containsKey(Object k) {
/*  90 */       return this.key == k;
/*     */     }
/*  92 */     public double getDouble(Object k) { if (this.key == k) return this.value; return this.defRetValue; } 
/*     */     public int size() {
/*  94 */       return 1;
/*     */     }
/*  96 */     public Object clone() { return this; }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptyFunction<K> extends AbstractReference2DoubleFunction<K>
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public double getDouble(Object k)
/*     */     {
/*  60 */       return 0.0D; } 
/*  61 */     public boolean containsKey(Object k) { return false; } 
/*  62 */     public double defaultReturnValue() { return 0.0D; } 
/*  63 */     public void defaultReturnValue(double defRetValue) { throw new UnsupportedOperationException(); } 
/*  64 */     public int size() { return 0; } 
/*     */     public void clear() {  } 
/*  66 */     private Object readResolve() { return Reference2DoubleFunctions.EMPTY_FUNCTION; } 
/*  67 */     public Object clone() { return Reference2DoubleFunctions.EMPTY_FUNCTION; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2DoubleFunctions
 * JD-Core Version:    0.6.2
 */