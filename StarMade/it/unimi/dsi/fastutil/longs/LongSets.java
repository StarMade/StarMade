/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class LongSets
/*     */ {
/*  71 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static LongSet singleton(long element)
/*     */   {
/* 114 */     return new Singleton(element);
/*     */   }
/*     */ 
/*     */   public static LongSet singleton(Long element)
/*     */   {
/* 128 */     return new Singleton(element.longValue());
/*     */   }
/*     */ 
/*     */   public static LongSet synchronize(LongSet s)
/*     */   {
/* 159 */     return new SynchronizedSet(s);
/*     */   }
/*     */ 
/*     */   public static LongSet synchronize(LongSet s, Object sync)
/*     */   {
/* 169 */     return new SynchronizedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static LongSet unmodifiable(LongSet s)
/*     */   {
/* 195 */     return new UnmodifiableSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSet extends LongCollections.UnmodifiableCollection
/*     */     implements LongSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected UnmodifiableSet(LongSet s)
/*     */     {
/* 180 */       super();
/*     */     }
/*     */     public boolean remove(long k) {
/* 183 */       throw new UnsupportedOperationException(); } 
/* 184 */     public boolean equals(Object o) { return this.collection.equals(o); } 
/* 185 */     public int hashCode() { return this.collection.hashCode(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSet extends LongCollections.SynchronizedCollection
/*     */     implements LongSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected SynchronizedSet(LongSet s, Object sync)
/*     */     {
/* 140 */       super(sync);
/*     */     }
/*     */ 
/*     */     protected SynchronizedSet(LongSet s) {
/* 144 */       super();
/*     */     }
/*     */     public boolean remove(long k) {
/* 147 */       synchronized (this.sync) { return this.collection.remove(Long.valueOf(k)); }  } 
/* 148 */     public boolean equals(Object o) { synchronized (this.sync) { return this.collection.equals(o); }  } 
/* 149 */     public int hashCode() { synchronized (this.sync) { return this.collection.hashCode(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends AbstractLongSet
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final long element;
/*     */ 
/*     */     protected Singleton(long element)
/*     */     {
/*  80 */       this.element = element;
/*     */     }
/*  82 */     public boolean add(long k) { throw new UnsupportedOperationException(); } 
/*  83 */     public boolean contains(long k) { return k == this.element; } 
/*  84 */     public boolean addAll(Collection<? extends Long> c) { throw new UnsupportedOperationException(); } 
/*  85 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  86 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public long[] toLongArray() {
/*  89 */       long[] a = new long[1];
/*  90 */       a[0] = this.element;
/*  91 */       return a;
/*     */     }
/*  93 */     public boolean addAll(LongCollection c) { throw new UnsupportedOperationException(); } 
/*  94 */     public boolean removeAll(LongCollection c) { throw new UnsupportedOperationException(); } 
/*  95 */     public boolean retainAll(LongCollection c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public LongListIterator iterator() {
/*  98 */       return LongIterators.singleton(this.element);
/*     */     }
/* 100 */     public int size() { return 1; } 
/*     */     public Object clone() {
/* 102 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptySet extends LongCollections.EmptyCollection
/*     */     implements LongSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(long ok)
/*     */     {
/*  61 */       throw new UnsupportedOperationException(); } 
/*  62 */     public Object clone() { return LongSets.EMPTY_SET; } 
/*  63 */     private Object readResolve() { return LongSets.EMPTY_SET; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongSets
 * JD-Core Version:    0.6.2
 */