/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class Char2DoubleFunctions
/*     */ {
/*  73 */   public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
/*     */ 
/*     */   public static Char2DoubleFunction singleton(char key, double value)
/*     */   {
/* 109 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static Char2DoubleFunction singleton(Character key, Double value)
/*     */   {
/* 124 */     return new Singleton(key.charValue(), value.doubleValue());
/*     */   }
/*     */ 
/*     */   public static Char2DoubleFunction synchronize(Char2DoubleFunction f)
/*     */   {
/* 187 */     return new SynchronizedFunction(f);
/*     */   }
/*     */ 
/*     */   public static Char2DoubleFunction synchronize(Char2DoubleFunction f, Object sync)
/*     */   {
/* 197 */     return new SynchronizedFunction(f, sync);
/*     */   }
/*     */ 
/*     */   public static Char2DoubleFunction unmodifiable(Char2DoubleFunction f)
/*     */   {
/* 244 */     return new UnmodifiableFunction(f);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableFunction extends AbstractChar2DoubleFunction
/*     */     implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Char2DoubleFunction function;
/*     */ 
/*     */     protected UnmodifiableFunction(Char2DoubleFunction f)
/*     */     {
/* 210 */       if (f == null) throw new NullPointerException();
/* 211 */       this.function = f;
/*     */     }
/*     */     public int size() {
/* 214 */       return this.function.size(); } 
/* 215 */     public boolean containsKey(char k) { return this.function.containsKey(k); } 
/*     */     public double defaultReturnValue() {
/* 217 */       return this.function.defaultReturnValue(); } 
/* 218 */     public void defaultReturnValue(double defRetValue) { throw new UnsupportedOperationException(); } 
/*     */     public double put(char k, double v) {
/* 220 */       throw new UnsupportedOperationException();
/*     */     }
/* 222 */     public void clear() { throw new UnsupportedOperationException(); } 
/* 223 */     public String toString() { return this.function.toString(); }
/*     */ 
/*     */     public double remove(char k) {
/* 226 */       throw new UnsupportedOperationException(); } 
/* 227 */     public double get(char k) { return this.function.get(k); } 
/* 228 */     public boolean containsKey(Object ok) { return this.function.containsKey(ok); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedFunction extends AbstractChar2DoubleFunction
/*     */     implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Char2DoubleFunction function;
/*     */     protected final Object sync;
/*     */ 
/*     */     protected SynchronizedFunction(Char2DoubleFunction f, Object sync)
/*     */     {
/* 140 */       if (f == null) throw new NullPointerException();
/* 141 */       this.function = f;
/* 142 */       this.sync = sync;
/*     */     }
/*     */ 
/*     */     protected SynchronizedFunction(Char2DoubleFunction f) {
/* 146 */       if (f == null) throw new NullPointerException();
/* 147 */       this.function = f;
/* 148 */       this.sync = this;
/*     */     }
/*     */     public int size() {
/* 151 */       synchronized (this.sync) { return this.function.size(); }  } 
/* 152 */     public boolean containsKey(char k) { synchronized (this.sync) { return this.function.containsKey(k); }  } 
/*     */     public double defaultReturnValue() {
/* 154 */       synchronized (this.sync) { return this.function.defaultReturnValue(); }  } 
/* 155 */     public void defaultReturnValue(double defRetValue) { synchronized (this.sync) { this.function.defaultReturnValue(defRetValue); }  } 
/*     */     public double put(char k, double v) {
/* 157 */       synchronized (this.sync) { return this.function.put(k, v); } 
/*     */     }
/* 159 */     public void clear() { synchronized (this.sync) { this.function.clear(); }  } 
/* 160 */     public String toString() { synchronized (this.sync) { return this.function.toString(); } }
/*     */ 
/*     */     public Double put(Character k, Double v) {
/* 163 */       synchronized (this.sync) { return (Double)this.function.put(k, v); }  } 
/* 164 */     public Double get(Object k) { synchronized (this.sync) { return (Double)this.function.get(k); }  } 
/* 165 */     public Double remove(Object k) { synchronized (this.sync) { return (Double)this.function.remove(k); } }
/*     */ 
/*     */     public double remove(char k)
/*     */     {
/* 169 */       synchronized (this.sync) { return this.function.remove(k); }  } 
/* 170 */     public double get(char k) { synchronized (this.sync) { return this.function.get(k); }  } 
/* 171 */     public boolean containsKey(Object ok) { synchronized (this.sync) { return this.function.containsKey(ok); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends AbstractChar2DoubleFunction
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final char key;
/*     */     protected final double value;
/*     */ 
/*     */     protected Singleton(char key, double value)
/*     */     {
/*  86 */       this.key = key;
/*  87 */       this.value = value;
/*     */     }
/*     */     public boolean containsKey(char k) {
/*  90 */       return this.key == k;
/*     */     }
/*  92 */     public double get(char k) { if (this.key == k) return this.value; return this.defRetValue; } 
/*     */     public int size() {
/*  94 */       return 1;
/*     */     }
/*  96 */     public Object clone() { return this; }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptyFunction extends AbstractChar2DoubleFunction
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public double get(char k)
/*     */     {
/*  61 */       return 0.0D; } 
/*  62 */     public boolean containsKey(char k) { return false; } 
/*  63 */     public double defaultReturnValue() { return 0.0D; } 
/*  64 */     public void defaultReturnValue(double defRetValue) { throw new UnsupportedOperationException(); } 
/*  65 */     public Double get(Object k) { return null; } 
/*  66 */     public int size() { return 0; } 
/*     */     public void clear() {  } 
/*  68 */     private Object readResolve() { return Char2DoubleFunctions.EMPTY_FUNCTION; } 
/*  69 */     public Object clone() { return Char2DoubleFunctions.EMPTY_FUNCTION; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2DoubleFunctions
 * JD-Core Version:    0.6.2
 */