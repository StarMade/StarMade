/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class DoubleSets
/*     */ {
/*  71 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static DoubleSet singleton(double element)
/*     */   {
/* 114 */     return new Singleton(element);
/*     */   }
/*     */ 
/*     */   public static DoubleSet singleton(Double element)
/*     */   {
/* 128 */     return new Singleton(element.doubleValue());
/*     */   }
/*     */ 
/*     */   public static DoubleSet synchronize(DoubleSet s)
/*     */   {
/* 159 */     return new SynchronizedSet(s);
/*     */   }
/*     */ 
/*     */   public static DoubleSet synchronize(DoubleSet s, Object sync)
/*     */   {
/* 169 */     return new SynchronizedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static DoubleSet unmodifiable(DoubleSet s)
/*     */   {
/* 195 */     return new UnmodifiableSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSet extends DoubleCollections.UnmodifiableCollection
/*     */     implements DoubleSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected UnmodifiableSet(DoubleSet s)
/*     */     {
/* 180 */       super();
/*     */     }
/*     */     public boolean remove(double k) {
/* 183 */       throw new UnsupportedOperationException(); } 
/* 184 */     public boolean equals(Object o) { return this.collection.equals(o); } 
/* 185 */     public int hashCode() { return this.collection.hashCode(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSet extends DoubleCollections.SynchronizedCollection
/*     */     implements DoubleSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected SynchronizedSet(DoubleSet s, Object sync)
/*     */     {
/* 140 */       super(sync);
/*     */     }
/*     */ 
/*     */     protected SynchronizedSet(DoubleSet s) {
/* 144 */       super();
/*     */     }
/*     */     public boolean remove(double k) {
/* 147 */       synchronized (this.sync) { return this.collection.remove(Double.valueOf(k)); }  } 
/* 148 */     public boolean equals(Object o) { synchronized (this.sync) { return this.collection.equals(o); }  } 
/* 149 */     public int hashCode() { synchronized (this.sync) { return this.collection.hashCode(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends AbstractDoubleSet
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final double element;
/*     */ 
/*     */     protected Singleton(double element)
/*     */     {
/*  80 */       this.element = element;
/*     */     }
/*  82 */     public boolean add(double k) { throw new UnsupportedOperationException(); } 
/*  83 */     public boolean contains(double k) { return k == this.element; } 
/*  84 */     public boolean addAll(Collection<? extends Double> c) { throw new UnsupportedOperationException(); } 
/*  85 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  86 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public double[] toDoubleArray() {
/*  89 */       double[] a = new double[1];
/*  90 */       a[0] = this.element;
/*  91 */       return a;
/*     */     }
/*  93 */     public boolean addAll(DoubleCollection c) { throw new UnsupportedOperationException(); } 
/*  94 */     public boolean removeAll(DoubleCollection c) { throw new UnsupportedOperationException(); } 
/*  95 */     public boolean retainAll(DoubleCollection c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public DoubleListIterator iterator() {
/*  98 */       return DoubleIterators.singleton(this.element);
/*     */     }
/* 100 */     public int size() { return 1; } 
/*     */     public Object clone() {
/* 102 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptySet extends DoubleCollections.EmptyCollection
/*     */     implements DoubleSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(double ok)
/*     */     {
/*  61 */       throw new UnsupportedOperationException(); } 
/*  62 */     public Object clone() { return DoubleSets.EMPTY_SET; } 
/*  63 */     private Object readResolve() { return DoubleSets.EMPTY_SET; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleSets
 * JD-Core Version:    0.6.2
 */