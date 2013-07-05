/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class ReferenceSets
/*     */ {
/*  70 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static <K> ReferenceSet<K> singleton(K element)
/*     */   {
/*  97 */     return new Singleton(element);
/*     */   }
/*     */ 
/*     */   public static <K> ReferenceSet<K> synchronize(ReferenceSet<K> s)
/*     */   {
/* 118 */     return new SynchronizedSet(s);
/*     */   }
/*     */ 
/*     */   public static <K> ReferenceSet<K> synchronize(ReferenceSet<K> s, Object sync)
/*     */   {
/* 126 */     return new SynchronizedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static <K> ReferenceSet<K> unmodifiable(ReferenceSet<K> s)
/*     */   {
/* 143 */     return new UnmodifiableSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSet<K> extends ReferenceCollections.UnmodifiableCollection<K>
/*     */     implements ReferenceSet<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected UnmodifiableSet(ReferenceSet<K> s)
/*     */     {
/* 131 */       super();
/*     */     }
/* 133 */     public boolean remove(Object k) { throw new UnsupportedOperationException(); } 
/* 134 */     public boolean equals(Object o) { return this.collection.equals(o); } 
/* 135 */     public int hashCode() { return this.collection.hashCode(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSet<K> extends ReferenceCollections.SynchronizedCollection<K>
/*     */     implements ReferenceSet<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected SynchronizedSet(ReferenceSet<K> s, Object sync)
/*     */     {
/* 103 */       super(sync);
/*     */     }
/*     */     protected SynchronizedSet(ReferenceSet<K> s) {
/* 106 */       super();
/*     */     }
/* 108 */     public boolean remove(Object k) { synchronized (this.sync) { return this.collection.remove(k); }  } 
/* 109 */     public boolean equals(Object o) { synchronized (this.sync) { return this.collection.equals(o); }  } 
/* 110 */     public int hashCode() { synchronized (this.sync) { return this.collection.hashCode(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton<K> extends AbstractReferenceSet<K>
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final K element;
/*     */ 
/*     */     protected Singleton(K element)
/*     */     {
/*  79 */       this.element = element;
/*     */     }
/*  81 */     public boolean add(K k) { throw new UnsupportedOperationException(); } 
/*  82 */     public boolean contains(Object k) { return k == this.element; } 
/*  83 */     public boolean addAll(Collection<? extends K> c) { throw new UnsupportedOperationException(); } 
/*  84 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  85 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*     */     public ObjectListIterator<K> iterator() {
/*  87 */       return ObjectIterators.singleton(this.element); } 
/*  88 */     public int size() { return 1; } 
/*  89 */     public Object clone() { return this; }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySet<K> extends ReferenceCollections.EmptyCollection<K>
/*     */     implements ReferenceSet<K>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(Object ok)
/*     */     {
/*  60 */       throw new UnsupportedOperationException(); } 
/*  61 */     public Object clone() { return ReferenceSets.EMPTY_SET; } 
/*  62 */     private Object readResolve() { return ReferenceSets.EMPTY_SET; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceSets
 * JD-Core Version:    0.6.2
 */