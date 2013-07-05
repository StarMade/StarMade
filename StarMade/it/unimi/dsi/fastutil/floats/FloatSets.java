/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class FloatSets
/*     */ {
/*  71 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static FloatSet singleton(float element)
/*     */   {
/* 114 */     return new Singleton(element);
/*     */   }
/*     */ 
/*     */   public static FloatSet singleton(Float element)
/*     */   {
/* 128 */     return new Singleton(element.floatValue());
/*     */   }
/*     */ 
/*     */   public static FloatSet synchronize(FloatSet s)
/*     */   {
/* 159 */     return new SynchronizedSet(s);
/*     */   }
/*     */ 
/*     */   public static FloatSet synchronize(FloatSet s, Object sync)
/*     */   {
/* 169 */     return new SynchronizedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static FloatSet unmodifiable(FloatSet s)
/*     */   {
/* 195 */     return new UnmodifiableSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSet extends FloatCollections.UnmodifiableCollection
/*     */     implements FloatSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected UnmodifiableSet(FloatSet s)
/*     */     {
/* 180 */       super();
/*     */     }
/*     */     public boolean remove(float k) {
/* 183 */       throw new UnsupportedOperationException(); } 
/* 184 */     public boolean equals(Object o) { return this.collection.equals(o); } 
/* 185 */     public int hashCode() { return this.collection.hashCode(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSet extends FloatCollections.SynchronizedCollection
/*     */     implements FloatSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected SynchronizedSet(FloatSet s, Object sync)
/*     */     {
/* 140 */       super(sync);
/*     */     }
/*     */ 
/*     */     protected SynchronizedSet(FloatSet s) {
/* 144 */       super();
/*     */     }
/*     */     public boolean remove(float k) {
/* 147 */       synchronized (this.sync) { return this.collection.remove(Float.valueOf(k)); }  } 
/* 148 */     public boolean equals(Object o) { synchronized (this.sync) { return this.collection.equals(o); }  } 
/* 149 */     public int hashCode() { synchronized (this.sync) { return this.collection.hashCode(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends AbstractFloatSet
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final float element;
/*     */ 
/*     */     protected Singleton(float element)
/*     */     {
/*  80 */       this.element = element;
/*     */     }
/*  82 */     public boolean add(float k) { throw new UnsupportedOperationException(); } 
/*  83 */     public boolean contains(float k) { return k == this.element; } 
/*  84 */     public boolean addAll(Collection<? extends Float> c) { throw new UnsupportedOperationException(); } 
/*  85 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  86 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public float[] toFloatArray() {
/*  89 */       float[] a = new float[1];
/*  90 */       a[0] = this.element;
/*  91 */       return a;
/*     */     }
/*  93 */     public boolean addAll(FloatCollection c) { throw new UnsupportedOperationException(); } 
/*  94 */     public boolean removeAll(FloatCollection c) { throw new UnsupportedOperationException(); } 
/*  95 */     public boolean retainAll(FloatCollection c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public FloatListIterator iterator() {
/*  98 */       return FloatIterators.singleton(this.element);
/*     */     }
/* 100 */     public int size() { return 1; } 
/*     */     public Object clone() {
/* 102 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptySet extends FloatCollections.EmptyCollection
/*     */     implements FloatSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(float ok)
/*     */     {
/*  61 */       throw new UnsupportedOperationException(); } 
/*  62 */     public Object clone() { return FloatSets.EMPTY_SET; } 
/*  63 */     private Object readResolve() { return FloatSets.EMPTY_SET; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatSets
 * JD-Core Version:    0.6.2
 */