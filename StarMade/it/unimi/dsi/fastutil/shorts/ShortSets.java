/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class ShortSets
/*     */ {
/*  71 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static ShortSet singleton(short element)
/*     */   {
/* 114 */     return new Singleton(element);
/*     */   }
/*     */ 
/*     */   public static ShortSet singleton(Short element)
/*     */   {
/* 128 */     return new Singleton(element.shortValue());
/*     */   }
/*     */ 
/*     */   public static ShortSet synchronize(ShortSet s)
/*     */   {
/* 159 */     return new SynchronizedSet(s);
/*     */   }
/*     */ 
/*     */   public static ShortSet synchronize(ShortSet s, Object sync)
/*     */   {
/* 169 */     return new SynchronizedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static ShortSet unmodifiable(ShortSet s)
/*     */   {
/* 195 */     return new UnmodifiableSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSet extends ShortCollections.UnmodifiableCollection
/*     */     implements ShortSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected UnmodifiableSet(ShortSet s)
/*     */     {
/* 180 */       super();
/*     */     }
/*     */     public boolean remove(short k) {
/* 183 */       throw new UnsupportedOperationException(); } 
/* 184 */     public boolean equals(Object o) { return this.collection.equals(o); } 
/* 185 */     public int hashCode() { return this.collection.hashCode(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSet extends ShortCollections.SynchronizedCollection
/*     */     implements ShortSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected SynchronizedSet(ShortSet s, Object sync)
/*     */     {
/* 140 */       super(sync);
/*     */     }
/*     */ 
/*     */     protected SynchronizedSet(ShortSet s) {
/* 144 */       super();
/*     */     }
/*     */     public boolean remove(short k) {
/* 147 */       synchronized (this.sync) { return this.collection.remove(Short.valueOf(k)); }  } 
/* 148 */     public boolean equals(Object o) { synchronized (this.sync) { return this.collection.equals(o); }  } 
/* 149 */     public int hashCode() { synchronized (this.sync) { return this.collection.hashCode(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends AbstractShortSet
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final short element;
/*     */ 
/*     */     protected Singleton(short element)
/*     */     {
/*  80 */       this.element = element;
/*     */     }
/*  82 */     public boolean add(short k) { throw new UnsupportedOperationException(); } 
/*  83 */     public boolean contains(short k) { return k == this.element; } 
/*  84 */     public boolean addAll(Collection<? extends Short> c) { throw new UnsupportedOperationException(); } 
/*  85 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  86 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public short[] toShortArray() {
/*  89 */       short[] a = new short[1];
/*  90 */       a[0] = this.element;
/*  91 */       return a;
/*     */     }
/*  93 */     public boolean addAll(ShortCollection c) { throw new UnsupportedOperationException(); } 
/*  94 */     public boolean removeAll(ShortCollection c) { throw new UnsupportedOperationException(); } 
/*  95 */     public boolean retainAll(ShortCollection c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public ShortListIterator iterator() {
/*  98 */       return ShortIterators.singleton(this.element);
/*     */     }
/* 100 */     public int size() { return 1; } 
/*     */     public Object clone() {
/* 102 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptySet extends ShortCollections.EmptyCollection
/*     */     implements ShortSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(short ok)
/*     */     {
/*  61 */       throw new UnsupportedOperationException(); } 
/*  62 */     public Object clone() { return ShortSets.EMPTY_SET; } 
/*  63 */     private Object readResolve() { return ShortSets.EMPTY_SET; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortSets
 * JD-Core Version:    0.6.2
 */