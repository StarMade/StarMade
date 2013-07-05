/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ShortSortedSets
/*     */ {
/*  93 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static ShortSortedSet singleton(short element)
/*     */   {
/* 165 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static ShortSortedSet singleton(short element, ShortComparator comparator)
/*     */   {
/* 176 */     return new Singleton(element, comparator, null);
/*     */   }
/*     */ 
/*     */   public static ShortSortedSet singleton(Object element)
/*     */   {
/* 188 */     return new Singleton(((Short)element).shortValue(), null);
/*     */   }
/*     */ 
/*     */   public static ShortSortedSet singleton(Object element, ShortComparator comparator)
/*     */   {
/* 199 */     return new Singleton(((Short)element).shortValue(), comparator, null);
/*     */   }
/*     */ 
/*     */   public static ShortSortedSet synchronize(ShortSortedSet s)
/*     */   {
/* 254 */     return new SynchronizedSortedSet(s);
/*     */   }
/*     */ 
/*     */   public static ShortSortedSet synchronize(ShortSortedSet s, Object sync)
/*     */   {
/* 264 */     return new SynchronizedSortedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static ShortSortedSet unmodifiable(ShortSortedSet s)
/*     */   {
/* 315 */     return new UnmodifiableSortedSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedSet extends ShortSets.UnmodifiableSet
/*     */     implements ShortSortedSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ShortSortedSet sortedSet;
/*     */ 
/*     */     protected UnmodifiableSortedSet(ShortSortedSet s)
/*     */     {
/* 279 */       super();
/* 280 */       this.sortedSet = s;
/*     */     }
/*     */     public ShortComparator comparator() {
/* 283 */       return this.sortedSet.comparator();
/*     */     }
/* 285 */     public ShortSortedSet subSet(short from, short to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); } 
/* 286 */     public ShortSortedSet headSet(short to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); } 
/* 287 */     public ShortSortedSet tailSet(short from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); } 
/*     */     public ShortBidirectionalIterator iterator() {
/* 289 */       return ShortIterators.unmodifiable(this.sortedSet.iterator()); } 
/* 290 */     public ShortBidirectionalIterator iterator(short from) { return ShortIterators.unmodifiable(this.sortedSet.iterator(from)); } 
/*     */     @Deprecated
/*     */     public ShortBidirectionalIterator shortIterator() {
/* 293 */       return iterator();
/*     */     }
/* 295 */     public short firstShort() { return this.sortedSet.firstShort(); } 
/* 296 */     public short lastShort() { return this.sortedSet.lastShort(); }
/*     */ 
/*     */     public Short first() {
/* 299 */       return (Short)this.sortedSet.first(); } 
/* 300 */     public Short last() { return (Short)this.sortedSet.last(); } 
/*     */     public ShortSortedSet subSet(Short from, Short to) {
/* 302 */       return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); } 
/* 303 */     public ShortSortedSet headSet(Short to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); } 
/* 304 */     public ShortSortedSet tailSet(Short from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedSet extends ShortSets.SynchronizedSet
/*     */     implements ShortSortedSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ShortSortedSet sortedSet;
/*     */ 
/*     */     protected SynchronizedSortedSet(ShortSortedSet s, Object sync)
/*     */     {
/* 213 */       super(sync);
/* 214 */       this.sortedSet = s;
/*     */     }
/*     */ 
/*     */     protected SynchronizedSortedSet(ShortSortedSet s) {
/* 218 */       super();
/* 219 */       this.sortedSet = s;
/*     */     }
/*     */     public ShortComparator comparator() {
/* 222 */       synchronized (this.sync) { return this.sortedSet.comparator(); } 
/*     */     }
/* 224 */     public ShortSortedSet subSet(short from, short to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); } 
/* 225 */     public ShortSortedSet headSet(short to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); } 
/* 226 */     public ShortSortedSet tailSet(short from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); } 
/*     */     public ShortBidirectionalIterator iterator() {
/* 228 */       return this.sortedSet.iterator(); } 
/* 229 */     public ShortBidirectionalIterator iterator(short from) { return this.sortedSet.iterator(from); } 
/*     */     @Deprecated
/*     */     public ShortBidirectionalIterator shortIterator() {
/* 232 */       return this.sortedSet.iterator();
/*     */     }
/* 234 */     public short firstShort() { synchronized (this.sync) { return this.sortedSet.firstShort(); }  } 
/* 235 */     public short lastShort() { synchronized (this.sync) { return this.sortedSet.lastShort(); } }
/*     */ 
/*     */     public Short first() {
/* 238 */       synchronized (this.sync) { return (Short)this.sortedSet.first(); }  } 
/* 239 */     public Short last() { synchronized (this.sync) { return (Short)this.sortedSet.last(); }  } 
/*     */     public ShortSortedSet subSet(Short from, Short to) {
/* 241 */       return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); } 
/* 242 */     public ShortSortedSet headSet(Short to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); } 
/* 243 */     public ShortSortedSet tailSet(Short from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class Singleton extends ShortSets.Singleton
/*     */     implements ShortSortedSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     final ShortComparator comparator;
/*     */ 
/*     */     private Singleton(short element, ShortComparator comparator)
/*     */     {
/* 108 */       super();
/* 109 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     private Singleton(short element) {
/* 113 */       this(element, null);
/*     */     }
/*     */ 
/*     */     final int compare(short k1, short k2)
/*     */     {
/* 118 */       return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/*     */     }
/*     */ 
/*     */     @Deprecated
/*     */     public ShortBidirectionalIterator shortIterator() {
/* 123 */       return iterator();
/*     */     }
/*     */ 
/*     */     public ShortBidirectionalIterator iterator(short from) {
/* 127 */       ShortBidirectionalIterator i = iterator();
/* 128 */       if (compare(this.element, from) <= 0) i.next();
/* 129 */       return i;
/*     */     }
/*     */     public ShortComparator comparator() {
/* 132 */       return this.comparator;
/*     */     }
/*     */     public ShortSortedSet subSet(short from, short to) {
/* 135 */       if ((compare(from, this.element) <= 0) && (compare(this.element, to) < 0)) return this; return ShortSortedSets.EMPTY_SET;
/*     */     }
/*     */     public ShortSortedSet headSet(short to) {
/* 138 */       if (compare(this.element, to) < 0) return this; return ShortSortedSets.EMPTY_SET;
/*     */     }
/*     */     public ShortSortedSet tailSet(short from) {
/* 141 */       if (compare(from, this.element) <= 0) return this; return ShortSortedSets.EMPTY_SET;
/*     */     }
/* 143 */     public short firstShort() { return this.element; } 
/* 144 */     public short lastShort() { return this.element; }
/*     */ 
/*     */     public Short first() {
/* 147 */       return Short.valueOf(this.element); } 
/* 148 */     public Short last() { return Short.valueOf(this.element); }
/*     */ 
/*     */     public ShortSortedSet subSet(Short from, Short to) {
/* 151 */       return subSet(from.shortValue(), to.shortValue()); } 
/* 152 */     public ShortSortedSet headSet(Short to) { return headSet(to.shortValue()); } 
/* 153 */     public ShortSortedSet tailSet(Short from) { return tailSet(from.shortValue()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySet extends ShortSets.EmptySet
/*     */     implements ShortSortedSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(short ok)
/*     */     {
/*  61 */       throw new UnsupportedOperationException(); } 
/*  63 */     @Deprecated
/*     */     public ShortBidirectionalIterator shortIterator() { return iterator(); } 
/*     */     public ShortBidirectionalIterator iterator(short from) {
/*  65 */       return ShortIterators.EMPTY_ITERATOR;
/*     */     }
/*  67 */     public ShortSortedSet subSet(short from, short to) { return ShortSortedSets.EMPTY_SET; } 
/*     */     public ShortSortedSet headSet(short from) {
/*  69 */       return ShortSortedSets.EMPTY_SET;
/*     */     }
/*  71 */     public ShortSortedSet tailSet(short to) { return ShortSortedSets.EMPTY_SET; } 
/*  72 */     public short firstShort() { throw new NoSuchElementException(); } 
/*  73 */     public short lastShort() { throw new NoSuchElementException(); } 
/*  74 */     public ShortComparator comparator() { return null; } 
/*  75 */     public ShortSortedSet subSet(Short from, Short to) { return ShortSortedSets.EMPTY_SET; } 
/*  76 */     public ShortSortedSet headSet(Short from) { return ShortSortedSets.EMPTY_SET; } 
/*  77 */     public ShortSortedSet tailSet(Short to) { return ShortSortedSets.EMPTY_SET; } 
/*  78 */     public Short first() { throw new NoSuchElementException(); } 
/*  79 */     public Short last() { throw new NoSuchElementException(); } 
/*  80 */     public Object clone() { return ShortSortedSets.EMPTY_SET; } 
/*     */     private Object readResolve() {
/*  82 */       return ShortSortedSets.EMPTY_SET;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortSortedSets
 * JD-Core Version:    0.6.2
 */