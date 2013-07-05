/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class LongSortedSets
/*     */ {
/*  93 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static LongSortedSet singleton(long element)
/*     */   {
/* 165 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static LongSortedSet singleton(long element, LongComparator comparator)
/*     */   {
/* 176 */     return new Singleton(element, comparator, null);
/*     */   }
/*     */ 
/*     */   public static LongSortedSet singleton(Object element)
/*     */   {
/* 188 */     return new Singleton(((Long)element).longValue(), null);
/*     */   }
/*     */ 
/*     */   public static LongSortedSet singleton(Object element, LongComparator comparator)
/*     */   {
/* 199 */     return new Singleton(((Long)element).longValue(), comparator, null);
/*     */   }
/*     */ 
/*     */   public static LongSortedSet synchronize(LongSortedSet s)
/*     */   {
/* 254 */     return new SynchronizedSortedSet(s);
/*     */   }
/*     */ 
/*     */   public static LongSortedSet synchronize(LongSortedSet s, Object sync)
/*     */   {
/* 264 */     return new SynchronizedSortedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static LongSortedSet unmodifiable(LongSortedSet s)
/*     */   {
/* 315 */     return new UnmodifiableSortedSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedSet extends LongSets.UnmodifiableSet
/*     */     implements LongSortedSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final LongSortedSet sortedSet;
/*     */ 
/*     */     protected UnmodifiableSortedSet(LongSortedSet s)
/*     */     {
/* 279 */       super();
/* 280 */       this.sortedSet = s;
/*     */     }
/*     */     public LongComparator comparator() {
/* 283 */       return this.sortedSet.comparator();
/*     */     }
/* 285 */     public LongSortedSet subSet(long from, long to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); } 
/* 286 */     public LongSortedSet headSet(long to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); } 
/* 287 */     public LongSortedSet tailSet(long from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); } 
/*     */     public LongBidirectionalIterator iterator() {
/* 289 */       return LongIterators.unmodifiable(this.sortedSet.iterator()); } 
/* 290 */     public LongBidirectionalIterator iterator(long from) { return LongIterators.unmodifiable(this.sortedSet.iterator(from)); } 
/*     */     @Deprecated
/*     */     public LongBidirectionalIterator longIterator() {
/* 293 */       return iterator();
/*     */     }
/* 295 */     public long firstLong() { return this.sortedSet.firstLong(); } 
/* 296 */     public long lastLong() { return this.sortedSet.lastLong(); }
/*     */ 
/*     */     public Long first() {
/* 299 */       return (Long)this.sortedSet.first(); } 
/* 300 */     public Long last() { return (Long)this.sortedSet.last(); } 
/*     */     public LongSortedSet subSet(Long from, Long to) {
/* 302 */       return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); } 
/* 303 */     public LongSortedSet headSet(Long to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); } 
/* 304 */     public LongSortedSet tailSet(Long from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedSet extends LongSets.SynchronizedSet
/*     */     implements LongSortedSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final LongSortedSet sortedSet;
/*     */ 
/*     */     protected SynchronizedSortedSet(LongSortedSet s, Object sync)
/*     */     {
/* 213 */       super(sync);
/* 214 */       this.sortedSet = s;
/*     */     }
/*     */ 
/*     */     protected SynchronizedSortedSet(LongSortedSet s) {
/* 218 */       super();
/* 219 */       this.sortedSet = s;
/*     */     }
/*     */     public LongComparator comparator() {
/* 222 */       synchronized (this.sync) { return this.sortedSet.comparator(); } 
/*     */     }
/* 224 */     public LongSortedSet subSet(long from, long to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); } 
/* 225 */     public LongSortedSet headSet(long to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); } 
/* 226 */     public LongSortedSet tailSet(long from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); } 
/*     */     public LongBidirectionalIterator iterator() {
/* 228 */       return this.sortedSet.iterator(); } 
/* 229 */     public LongBidirectionalIterator iterator(long from) { return this.sortedSet.iterator(from); } 
/*     */     @Deprecated
/*     */     public LongBidirectionalIterator longIterator() {
/* 232 */       return this.sortedSet.iterator();
/*     */     }
/* 234 */     public long firstLong() { synchronized (this.sync) { return this.sortedSet.firstLong(); }  } 
/* 235 */     public long lastLong() { synchronized (this.sync) { return this.sortedSet.lastLong(); } }
/*     */ 
/*     */     public Long first() {
/* 238 */       synchronized (this.sync) { return (Long)this.sortedSet.first(); }  } 
/* 239 */     public Long last() { synchronized (this.sync) { return (Long)this.sortedSet.last(); }  } 
/*     */     public LongSortedSet subSet(Long from, Long to) {
/* 241 */       return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); } 
/* 242 */     public LongSortedSet headSet(Long to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); } 
/* 243 */     public LongSortedSet tailSet(Long from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class Singleton extends LongSets.Singleton
/*     */     implements LongSortedSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     final LongComparator comparator;
/*     */ 
/*     */     private Singleton(long element, LongComparator comparator)
/*     */     {
/* 108 */       super();
/* 109 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     private Singleton(long element) {
/* 113 */       this(element, null);
/*     */     }
/*     */ 
/*     */     final int compare(long k1, long k2)
/*     */     {
/* 118 */       return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/*     */     }
/*     */ 
/*     */     @Deprecated
/*     */     public LongBidirectionalIterator longIterator() {
/* 123 */       return iterator();
/*     */     }
/*     */ 
/*     */     public LongBidirectionalIterator iterator(long from) {
/* 127 */       LongBidirectionalIterator i = iterator();
/* 128 */       if (compare(this.element, from) <= 0) i.next();
/* 129 */       return i;
/*     */     }
/*     */     public LongComparator comparator() {
/* 132 */       return this.comparator;
/*     */     }
/*     */     public LongSortedSet subSet(long from, long to) {
/* 135 */       if ((compare(from, this.element) <= 0) && (compare(this.element, to) < 0)) return this; return LongSortedSets.EMPTY_SET;
/*     */     }
/*     */     public LongSortedSet headSet(long to) {
/* 138 */       if (compare(this.element, to) < 0) return this; return LongSortedSets.EMPTY_SET;
/*     */     }
/*     */     public LongSortedSet tailSet(long from) {
/* 141 */       if (compare(from, this.element) <= 0) return this; return LongSortedSets.EMPTY_SET;
/*     */     }
/* 143 */     public long firstLong() { return this.element; } 
/* 144 */     public long lastLong() { return this.element; }
/*     */ 
/*     */     public Long first() {
/* 147 */       return Long.valueOf(this.element); } 
/* 148 */     public Long last() { return Long.valueOf(this.element); }
/*     */ 
/*     */     public LongSortedSet subSet(Long from, Long to) {
/* 151 */       return subSet(from.longValue(), to.longValue()); } 
/* 152 */     public LongSortedSet headSet(Long to) { return headSet(to.longValue()); } 
/* 153 */     public LongSortedSet tailSet(Long from) { return tailSet(from.longValue()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySet extends LongSets.EmptySet
/*     */     implements LongSortedSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(long ok)
/*     */     {
/*  61 */       throw new UnsupportedOperationException(); } 
/*  63 */     @Deprecated
/*     */     public LongBidirectionalIterator longIterator() { return iterator(); } 
/*     */     public LongBidirectionalIterator iterator(long from) {
/*  65 */       return LongIterators.EMPTY_ITERATOR;
/*     */     }
/*  67 */     public LongSortedSet subSet(long from, long to) { return LongSortedSets.EMPTY_SET; } 
/*     */     public LongSortedSet headSet(long from) {
/*  69 */       return LongSortedSets.EMPTY_SET;
/*     */     }
/*  71 */     public LongSortedSet tailSet(long to) { return LongSortedSets.EMPTY_SET; } 
/*  72 */     public long firstLong() { throw new NoSuchElementException(); } 
/*  73 */     public long lastLong() { throw new NoSuchElementException(); } 
/*  74 */     public LongComparator comparator() { return null; } 
/*  75 */     public LongSortedSet subSet(Long from, Long to) { return LongSortedSets.EMPTY_SET; } 
/*  76 */     public LongSortedSet headSet(Long from) { return LongSortedSets.EMPTY_SET; } 
/*  77 */     public LongSortedSet tailSet(Long to) { return LongSortedSets.EMPTY_SET; } 
/*  78 */     public Long first() { throw new NoSuchElementException(); } 
/*  79 */     public Long last() { throw new NoSuchElementException(); } 
/*  80 */     public Object clone() { return LongSortedSets.EMPTY_SET; } 
/*     */     private Object readResolve() {
/*  82 */       return LongSortedSets.EMPTY_SET;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongSortedSets
 * JD-Core Version:    0.6.2
 */