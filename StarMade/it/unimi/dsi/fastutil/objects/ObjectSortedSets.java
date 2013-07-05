/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ObjectSortedSets
/*     */ {
/*  93 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static <K> ObjectSortedSet<K> singleton(K element)
/*     */   {
/* 152 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectSortedSet<K> singleton(K element, Comparator<? super K> comparator)
/*     */   {
/* 161 */     return new Singleton(element, comparator, null);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectSortedSet<K> synchronize(ObjectSortedSet<K> s)
/*     */   {
/* 192 */     return new SynchronizedSortedSet(s);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectSortedSet<K> synchronize(ObjectSortedSet<K> s, Object sync)
/*     */   {
/* 200 */     return new SynchronizedSortedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectSortedSet<K> unmodifiable(ObjectSortedSet<K> s)
/*     */   {
/* 226 */     return new UnmodifiableSortedSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedSet<K> extends ObjectSets.UnmodifiableSet<K>
/*     */     implements ObjectSortedSet<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ObjectSortedSet<K> sortedSet;
/*     */ 
/*     */     protected UnmodifiableSortedSet(ObjectSortedSet<K> s)
/*     */     {
/* 206 */       super();
/* 207 */       this.sortedSet = s;
/*     */     }
/* 209 */     public Comparator<? super K> comparator() { return this.sortedSet.comparator(); } 
/* 210 */     public ObjectSortedSet<K> subSet(K from, K to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); } 
/* 211 */     public ObjectSortedSet<K> headSet(K to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); } 
/* 212 */     public ObjectSortedSet<K> tailSet(K from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); } 
/* 213 */     public ObjectBidirectionalIterator<K> iterator() { return ObjectIterators.unmodifiable(this.sortedSet.iterator()); } 
/* 214 */     public ObjectBidirectionalIterator<K> iterator(K from) { return ObjectIterators.unmodifiable(this.sortedSet.iterator(from)); } 
/* 216 */     @Deprecated
/*     */     public ObjectBidirectionalIterator<K> objectIterator() { return iterator(); } 
/* 217 */     public K first() { return this.sortedSet.first(); } 
/* 218 */     public K last() { return this.sortedSet.last(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedSet<K> extends ObjectSets.SynchronizedSet<K>
/*     */     implements ObjectSortedSet<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ObjectSortedSet<K> sortedSet;
/*     */ 
/*     */     protected SynchronizedSortedSet(ObjectSortedSet<K> s, Object sync)
/*     */     {
/* 168 */       super(sync);
/* 169 */       this.sortedSet = s;
/*     */     }
/*     */     protected SynchronizedSortedSet(ObjectSortedSet<K> s) {
/* 172 */       super();
/* 173 */       this.sortedSet = s;
/*     */     }
/* 175 */     public Comparator<? super K> comparator() { synchronized (this.sync) { return this.sortedSet.comparator(); }  } 
/* 176 */     public ObjectSortedSet<K> subSet(K from, K to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); } 
/* 177 */     public ObjectSortedSet<K> headSet(K to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); } 
/* 178 */     public ObjectSortedSet<K> tailSet(K from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); } 
/* 179 */     public ObjectBidirectionalIterator<K> iterator() { return this.sortedSet.iterator(); } 
/* 180 */     public ObjectBidirectionalIterator<K> iterator(K from) { return this.sortedSet.iterator(from); } 
/* 182 */     @Deprecated
/*     */     public ObjectBidirectionalIterator<K> objectIterator() { return this.sortedSet.iterator(); } 
/* 183 */     public K first() { synchronized (this.sync) { return this.sortedSet.first(); }  } 
/* 184 */     public K last() { synchronized (this.sync) { return this.sortedSet.last(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton<K> extends ObjectSets.Singleton<K>
/*     */     implements ObjectSortedSet<K>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     final Comparator<? super K> comparator;
/*     */ 
/*     */     private Singleton(K element, Comparator<? super K> comparator)
/*     */     {
/* 108 */       super();
/* 109 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     private Singleton(K element) {
/* 113 */       this(element, null);
/*     */     }
/*     */ 
/*     */     final int compare(K k1, K k2)
/*     */     {
/* 118 */       return this.comparator == null ? ((Comparable)k1).compareTo(k2) : this.comparator.compare(k1, k2);
/*     */     }
/*     */ 
/*     */     @Deprecated
/*     */     public ObjectBidirectionalIterator<K> objectIterator() {
/* 123 */       return iterator();
/*     */     }
/*     */ 
/*     */     public ObjectBidirectionalIterator<K> iterator(K from) {
/* 127 */       ObjectBidirectionalIterator i = iterator();
/* 128 */       if (compare(this.element, from) <= 0) i.next();
/* 129 */       return i;
/*     */     }
/*     */     public Comparator<? super K> comparator() {
/* 132 */       return this.comparator;
/*     */     }
/*     */     public ObjectSortedSet<K> subSet(K from, K to) {
/* 135 */       if ((compare(from, this.element) <= 0) && (compare(this.element, to) < 0)) return this; return ObjectSortedSets.EMPTY_SET;
/*     */     }
/*     */     public ObjectSortedSet<K> headSet(K to) {
/* 138 */       if (compare(this.element, to) < 0) return this; return ObjectSortedSets.EMPTY_SET;
/*     */     }
/*     */     public ObjectSortedSet<K> tailSet(K from) {
/* 141 */       if (compare(from, this.element) <= 0) return this; return ObjectSortedSets.EMPTY_SET;
/*     */     }
/* 143 */     public K first() { return this.element; } 
/* 144 */     public K last() { return this.element; }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySet<K> extends ObjectSets.EmptySet<K>
/*     */     implements ObjectSortedSet<K>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(Object ok)
/*     */     {
/*  61 */       throw new UnsupportedOperationException(); } 
/*  63 */     @Deprecated
/*     */     public ObjectBidirectionalIterator<K> objectIterator() { return iterator(); } 
/*     */     public ObjectBidirectionalIterator<K> iterator(K from) {
/*  65 */       return ObjectIterators.EMPTY_ITERATOR;
/*     */     }
/*  67 */     public ObjectSortedSet<K> subSet(K from, K to) { return ObjectSortedSets.EMPTY_SET; } 
/*     */     public ObjectSortedSet<K> headSet(K from) {
/*  69 */       return ObjectSortedSets.EMPTY_SET;
/*     */     }
/*  71 */     public ObjectSortedSet<K> tailSet(K to) { return ObjectSortedSets.EMPTY_SET; } 
/*  72 */     public K first() { throw new NoSuchElementException(); } 
/*  73 */     public K last() { throw new NoSuchElementException(); } 
/*  74 */     public Comparator<? super K> comparator() { return null; }
/*     */ 
/*     */ 
/*     */     public Object clone()
/*     */     {
/*  80 */       return ObjectSortedSets.EMPTY_SET;
/*     */     }
/*  82 */     private Object readResolve() { return ObjectSortedSets.EMPTY_SET; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectSortedSets
 * JD-Core Version:    0.6.2
 */