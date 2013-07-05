/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class IntSets
/*     */ {
/*  71 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static IntSet singleton(int element)
/*     */   {
/* 114 */     return new Singleton(element);
/*     */   }
/*     */ 
/*     */   public static IntSet singleton(Integer element)
/*     */   {
/* 128 */     return new Singleton(element.intValue());
/*     */   }
/*     */ 
/*     */   public static IntSet synchronize(IntSet s)
/*     */   {
/* 159 */     return new SynchronizedSet(s);
/*     */   }
/*     */ 
/*     */   public static IntSet synchronize(IntSet s, Object sync)
/*     */   {
/* 169 */     return new SynchronizedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static IntSet unmodifiable(IntSet s)
/*     */   {
/* 195 */     return new UnmodifiableSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSet extends IntCollections.UnmodifiableCollection
/*     */     implements IntSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected UnmodifiableSet(IntSet s)
/*     */     {
/* 180 */       super();
/*     */     }
/*     */     public boolean remove(int k) {
/* 183 */       throw new UnsupportedOperationException(); } 
/* 184 */     public boolean equals(Object o) { return this.collection.equals(o); } 
/* 185 */     public int hashCode() { return this.collection.hashCode(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSet extends IntCollections.SynchronizedCollection
/*     */     implements IntSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected SynchronizedSet(IntSet s, Object sync)
/*     */     {
/* 140 */       super(sync);
/*     */     }
/*     */ 
/*     */     protected SynchronizedSet(IntSet s) {
/* 144 */       super();
/*     */     }
/*     */     public boolean remove(int k) {
/* 147 */       synchronized (this.sync) { return this.collection.remove(Integer.valueOf(k)); }  } 
/* 148 */     public boolean equals(Object o) { synchronized (this.sync) { return this.collection.equals(o); }  } 
/* 149 */     public int hashCode() { synchronized (this.sync) { return this.collection.hashCode(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends AbstractIntSet
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final int element;
/*     */ 
/*     */     protected Singleton(int element)
/*     */     {
/*  80 */       this.element = element;
/*     */     }
/*  82 */     public boolean add(int k) { throw new UnsupportedOperationException(); } 
/*  83 */     public boolean contains(int k) { return k == this.element; } 
/*  84 */     public boolean addAll(Collection<? extends Integer> c) { throw new UnsupportedOperationException(); } 
/*  85 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  86 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public int[] toIntArray() {
/*  89 */       int[] a = new int[1];
/*  90 */       a[0] = this.element;
/*  91 */       return a;
/*     */     }
/*  93 */     public boolean addAll(IntCollection c) { throw new UnsupportedOperationException(); } 
/*  94 */     public boolean removeAll(IntCollection c) { throw new UnsupportedOperationException(); } 
/*  95 */     public boolean retainAll(IntCollection c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public IntListIterator iterator() {
/*  98 */       return IntIterators.singleton(this.element);
/*     */     }
/* 100 */     public int size() { return 1; } 
/*     */     public Object clone() {
/* 102 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptySet extends IntCollections.EmptyCollection
/*     */     implements IntSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(int ok)
/*     */     {
/*  61 */       throw new UnsupportedOperationException(); } 
/*  62 */     public Object clone() { return IntSets.EMPTY_SET; } 
/*  63 */     private Object readResolve() { return IntSets.EMPTY_SET; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntSets
 * JD-Core Version:    0.6.2
 */