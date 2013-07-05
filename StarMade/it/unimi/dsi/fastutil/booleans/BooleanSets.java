/*     */ package it.unimi.dsi.fastutil.booleans;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class BooleanSets
/*     */ {
/*  71 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static BooleanSet singleton(boolean element)
/*     */   {
/* 114 */     return new Singleton(element);
/*     */   }
/*     */ 
/*     */   public static BooleanSet singleton(Boolean element)
/*     */   {
/* 128 */     return new Singleton(element.booleanValue());
/*     */   }
/*     */ 
/*     */   public static BooleanSet synchronize(BooleanSet s)
/*     */   {
/* 159 */     return new SynchronizedSet(s);
/*     */   }
/*     */ 
/*     */   public static BooleanSet synchronize(BooleanSet s, Object sync)
/*     */   {
/* 169 */     return new SynchronizedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static BooleanSet unmodifiable(BooleanSet s)
/*     */   {
/* 195 */     return new UnmodifiableSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSet extends BooleanCollections.UnmodifiableCollection
/*     */     implements BooleanSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected UnmodifiableSet(BooleanSet s)
/*     */     {
/* 180 */       super();
/*     */     }
/*     */     public boolean remove(boolean k) {
/* 183 */       throw new UnsupportedOperationException(); } 
/* 184 */     public boolean equals(Object o) { return this.collection.equals(o); } 
/* 185 */     public int hashCode() { return this.collection.hashCode(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSet extends BooleanCollections.SynchronizedCollection
/*     */     implements BooleanSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected SynchronizedSet(BooleanSet s, Object sync)
/*     */     {
/* 140 */       super(sync);
/*     */     }
/*     */ 
/*     */     protected SynchronizedSet(BooleanSet s) {
/* 144 */       super();
/*     */     }
/*     */     public boolean remove(boolean k) {
/* 147 */       synchronized (this.sync) { return this.collection.remove(Boolean.valueOf(k)); }  } 
/* 148 */     public boolean equals(Object o) { synchronized (this.sync) { return this.collection.equals(o); }  } 
/* 149 */     public int hashCode() { synchronized (this.sync) { return this.collection.hashCode(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends AbstractBooleanSet
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final boolean element;
/*     */ 
/*     */     protected Singleton(boolean element)
/*     */     {
/*  80 */       this.element = element;
/*     */     }
/*  82 */     public boolean add(boolean k) { throw new UnsupportedOperationException(); } 
/*  83 */     public boolean contains(boolean k) { return k == this.element; } 
/*  84 */     public boolean addAll(Collection<? extends Boolean> c) { throw new UnsupportedOperationException(); } 
/*  85 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  86 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public boolean[] toBooleanArray() {
/*  89 */       boolean[] a = new boolean[1];
/*  90 */       a[0] = this.element;
/*  91 */       return a;
/*     */     }
/*  93 */     public boolean addAll(BooleanCollection c) { throw new UnsupportedOperationException(); } 
/*  94 */     public boolean removeAll(BooleanCollection c) { throw new UnsupportedOperationException(); } 
/*  95 */     public boolean retainAll(BooleanCollection c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public BooleanListIterator iterator() {
/*  98 */       return BooleanIterators.singleton(this.element);
/*     */     }
/* 100 */     public int size() { return 1; } 
/*     */     public Object clone() {
/* 102 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptySet extends BooleanCollections.EmptyCollection
/*     */     implements BooleanSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(boolean ok)
/*     */     {
/*  61 */       throw new UnsupportedOperationException(); } 
/*  62 */     public Object clone() { return BooleanSets.EMPTY_SET; } 
/*  63 */     private Object readResolve() { return BooleanSets.EMPTY_SET; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanSets
 * JD-Core Version:    0.6.2
 */