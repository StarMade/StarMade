/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class IntSortedSets
/*     */ {
/*  93 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static IntSortedSet singleton(int element)
/*     */   {
/* 165 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static IntSortedSet singleton(int element, IntComparator comparator)
/*     */   {
/* 176 */     return new Singleton(element, comparator, null);
/*     */   }
/*     */ 
/*     */   public static IntSortedSet singleton(Object element)
/*     */   {
/* 188 */     return new Singleton(((Integer)element).intValue(), null);
/*     */   }
/*     */ 
/*     */   public static IntSortedSet singleton(Object element, IntComparator comparator)
/*     */   {
/* 199 */     return new Singleton(((Integer)element).intValue(), comparator, null);
/*     */   }
/*     */ 
/*     */   public static IntSortedSet synchronize(IntSortedSet s)
/*     */   {
/* 254 */     return new SynchronizedSortedSet(s);
/*     */   }
/*     */ 
/*     */   public static IntSortedSet synchronize(IntSortedSet s, Object sync)
/*     */   {
/* 264 */     return new SynchronizedSortedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static IntSortedSet unmodifiable(IntSortedSet s)
/*     */   {
/* 315 */     return new UnmodifiableSortedSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedSet extends IntSets.UnmodifiableSet
/*     */     implements IntSortedSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final IntSortedSet sortedSet;
/*     */ 
/*     */     protected UnmodifiableSortedSet(IntSortedSet s)
/*     */     {
/* 279 */       super();
/* 280 */       this.sortedSet = s;
/*     */     }
/*     */     public IntComparator comparator() {
/* 283 */       return this.sortedSet.comparator();
/*     */     }
/* 285 */     public IntSortedSet subSet(int from, int to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); } 
/* 286 */     public IntSortedSet headSet(int to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); } 
/* 287 */     public IntSortedSet tailSet(int from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); } 
/*     */     public IntBidirectionalIterator iterator() {
/* 289 */       return IntIterators.unmodifiable(this.sortedSet.iterator()); } 
/* 290 */     public IntBidirectionalIterator iterator(int from) { return IntIterators.unmodifiable(this.sortedSet.iterator(from)); } 
/*     */     @Deprecated
/*     */     public IntBidirectionalIterator intIterator() {
/* 293 */       return iterator();
/*     */     }
/* 295 */     public int firstInt() { return this.sortedSet.firstInt(); } 
/* 296 */     public int lastInt() { return this.sortedSet.lastInt(); }
/*     */ 
/*     */     public Integer first() {
/* 299 */       return (Integer)this.sortedSet.first(); } 
/* 300 */     public Integer last() { return (Integer)this.sortedSet.last(); } 
/*     */     public IntSortedSet subSet(Integer from, Integer to) {
/* 302 */       return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); } 
/* 303 */     public IntSortedSet headSet(Integer to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); } 
/* 304 */     public IntSortedSet tailSet(Integer from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedSet extends IntSets.SynchronizedSet
/*     */     implements IntSortedSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final IntSortedSet sortedSet;
/*     */ 
/*     */     protected SynchronizedSortedSet(IntSortedSet s, Object sync)
/*     */     {
/* 213 */       super(sync);
/* 214 */       this.sortedSet = s;
/*     */     }
/*     */ 
/*     */     protected SynchronizedSortedSet(IntSortedSet s) {
/* 218 */       super();
/* 219 */       this.sortedSet = s;
/*     */     }
/*     */     public IntComparator comparator() {
/* 222 */       synchronized (this.sync) { return this.sortedSet.comparator(); } 
/*     */     }
/* 224 */     public IntSortedSet subSet(int from, int to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); } 
/* 225 */     public IntSortedSet headSet(int to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); } 
/* 226 */     public IntSortedSet tailSet(int from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); } 
/*     */     public IntBidirectionalIterator iterator() {
/* 228 */       return this.sortedSet.iterator(); } 
/* 229 */     public IntBidirectionalIterator iterator(int from) { return this.sortedSet.iterator(from); } 
/*     */     @Deprecated
/*     */     public IntBidirectionalIterator intIterator() {
/* 232 */       return this.sortedSet.iterator();
/*     */     }
/* 234 */     public int firstInt() { synchronized (this.sync) { return this.sortedSet.firstInt(); }  } 
/* 235 */     public int lastInt() { synchronized (this.sync) { return this.sortedSet.lastInt(); } }
/*     */ 
/*     */     public Integer first() {
/* 238 */       synchronized (this.sync) { return (Integer)this.sortedSet.first(); }  } 
/* 239 */     public Integer last() { synchronized (this.sync) { return (Integer)this.sortedSet.last(); }  } 
/*     */     public IntSortedSet subSet(Integer from, Integer to) {
/* 241 */       return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); } 
/* 242 */     public IntSortedSet headSet(Integer to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); } 
/* 243 */     public IntSortedSet tailSet(Integer from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class Singleton extends IntSets.Singleton
/*     */     implements IntSortedSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     final IntComparator comparator;
/*     */ 
/*     */     private Singleton(int element, IntComparator comparator)
/*     */     {
/* 108 */       super();
/* 109 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     private Singleton(int element) {
/* 113 */       this(element, null);
/*     */     }
/*     */ 
/*     */     final int compare(int k1, int k2)
/*     */     {
/* 118 */       return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/*     */     }
/*     */ 
/*     */     @Deprecated
/*     */     public IntBidirectionalIterator intIterator() {
/* 123 */       return iterator();
/*     */     }
/*     */ 
/*     */     public IntBidirectionalIterator iterator(int from) {
/* 127 */       IntBidirectionalIterator i = iterator();
/* 128 */       if (compare(this.element, from) <= 0) i.next();
/* 129 */       return i;
/*     */     }
/*     */     public IntComparator comparator() {
/* 132 */       return this.comparator;
/*     */     }
/*     */     public IntSortedSet subSet(int from, int to) {
/* 135 */       if ((compare(from, this.element) <= 0) && (compare(this.element, to) < 0)) return this; return IntSortedSets.EMPTY_SET;
/*     */     }
/*     */     public IntSortedSet headSet(int to) {
/* 138 */       if (compare(this.element, to) < 0) return this; return IntSortedSets.EMPTY_SET;
/*     */     }
/*     */     public IntSortedSet tailSet(int from) {
/* 141 */       if (compare(from, this.element) <= 0) return this; return IntSortedSets.EMPTY_SET;
/*     */     }
/* 143 */     public int firstInt() { return this.element; } 
/* 144 */     public int lastInt() { return this.element; }
/*     */ 
/*     */     public Integer first() {
/* 147 */       return Integer.valueOf(this.element); } 
/* 148 */     public Integer last() { return Integer.valueOf(this.element); }
/*     */ 
/*     */     public IntSortedSet subSet(Integer from, Integer to) {
/* 151 */       return subSet(from.intValue(), to.intValue()); } 
/* 152 */     public IntSortedSet headSet(Integer to) { return headSet(to.intValue()); } 
/* 153 */     public IntSortedSet tailSet(Integer from) { return tailSet(from.intValue()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySet extends IntSets.EmptySet
/*     */     implements IntSortedSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(int ok)
/*     */     {
/*  61 */       throw new UnsupportedOperationException(); } 
/*  63 */     @Deprecated
/*     */     public IntBidirectionalIterator intIterator() { return iterator(); } 
/*     */     public IntBidirectionalIterator iterator(int from) {
/*  65 */       return IntIterators.EMPTY_ITERATOR;
/*     */     }
/*  67 */     public IntSortedSet subSet(int from, int to) { return IntSortedSets.EMPTY_SET; } 
/*     */     public IntSortedSet headSet(int from) {
/*  69 */       return IntSortedSets.EMPTY_SET;
/*     */     }
/*  71 */     public IntSortedSet tailSet(int to) { return IntSortedSets.EMPTY_SET; } 
/*  72 */     public int firstInt() { throw new NoSuchElementException(); } 
/*  73 */     public int lastInt() { throw new NoSuchElementException(); } 
/*  74 */     public IntComparator comparator() { return null; } 
/*  75 */     public IntSortedSet subSet(Integer from, Integer to) { return IntSortedSets.EMPTY_SET; } 
/*  76 */     public IntSortedSet headSet(Integer from) { return IntSortedSets.EMPTY_SET; } 
/*  77 */     public IntSortedSet tailSet(Integer to) { return IntSortedSets.EMPTY_SET; } 
/*  78 */     public Integer first() { throw new NoSuchElementException(); } 
/*  79 */     public Integer last() { throw new NoSuchElementException(); } 
/*  80 */     public Object clone() { return IntSortedSets.EMPTY_SET; } 
/*     */     private Object readResolve() {
/*  82 */       return IntSortedSets.EMPTY_SET;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntSortedSets
 * JD-Core Version:    0.6.2
 */