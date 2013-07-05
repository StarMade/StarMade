/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class DoubleSortedSets
/*     */ {
/*  93 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static DoubleSortedSet singleton(double element)
/*     */   {
/* 165 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static DoubleSortedSet singleton(double element, DoubleComparator comparator)
/*     */   {
/* 176 */     return new Singleton(element, comparator, null);
/*     */   }
/*     */ 
/*     */   public static DoubleSortedSet singleton(Object element)
/*     */   {
/* 188 */     return new Singleton(((Double)element).doubleValue(), null);
/*     */   }
/*     */ 
/*     */   public static DoubleSortedSet singleton(Object element, DoubleComparator comparator)
/*     */   {
/* 199 */     return new Singleton(((Double)element).doubleValue(), comparator, null);
/*     */   }
/*     */ 
/*     */   public static DoubleSortedSet synchronize(DoubleSortedSet s)
/*     */   {
/* 254 */     return new SynchronizedSortedSet(s);
/*     */   }
/*     */ 
/*     */   public static DoubleSortedSet synchronize(DoubleSortedSet s, Object sync)
/*     */   {
/* 264 */     return new SynchronizedSortedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static DoubleSortedSet unmodifiable(DoubleSortedSet s)
/*     */   {
/* 315 */     return new UnmodifiableSortedSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedSet extends DoubleSets.UnmodifiableSet
/*     */     implements DoubleSortedSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final DoubleSortedSet sortedSet;
/*     */ 
/*     */     protected UnmodifiableSortedSet(DoubleSortedSet s)
/*     */     {
/* 279 */       super();
/* 280 */       this.sortedSet = s;
/*     */     }
/*     */     public DoubleComparator comparator() {
/* 283 */       return this.sortedSet.comparator();
/*     */     }
/* 285 */     public DoubleSortedSet subSet(double from, double to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); } 
/* 286 */     public DoubleSortedSet headSet(double to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); } 
/* 287 */     public DoubleSortedSet tailSet(double from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); } 
/*     */     public DoubleBidirectionalIterator iterator() {
/* 289 */       return DoubleIterators.unmodifiable(this.sortedSet.iterator()); } 
/* 290 */     public DoubleBidirectionalIterator iterator(double from) { return DoubleIterators.unmodifiable(this.sortedSet.iterator(from)); } 
/*     */     @Deprecated
/*     */     public DoubleBidirectionalIterator doubleIterator() {
/* 293 */       return iterator();
/*     */     }
/* 295 */     public double firstDouble() { return this.sortedSet.firstDouble(); } 
/* 296 */     public double lastDouble() { return this.sortedSet.lastDouble(); }
/*     */ 
/*     */     public Double first() {
/* 299 */       return (Double)this.sortedSet.first(); } 
/* 300 */     public Double last() { return (Double)this.sortedSet.last(); } 
/*     */     public DoubleSortedSet subSet(Double from, Double to) {
/* 302 */       return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); } 
/* 303 */     public DoubleSortedSet headSet(Double to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); } 
/* 304 */     public DoubleSortedSet tailSet(Double from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedSet extends DoubleSets.SynchronizedSet
/*     */     implements DoubleSortedSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final DoubleSortedSet sortedSet;
/*     */ 
/*     */     protected SynchronizedSortedSet(DoubleSortedSet s, Object sync)
/*     */     {
/* 213 */       super(sync);
/* 214 */       this.sortedSet = s;
/*     */     }
/*     */ 
/*     */     protected SynchronizedSortedSet(DoubleSortedSet s) {
/* 218 */       super();
/* 219 */       this.sortedSet = s;
/*     */     }
/*     */     public DoubleComparator comparator() {
/* 222 */       synchronized (this.sync) { return this.sortedSet.comparator(); } 
/*     */     }
/* 224 */     public DoubleSortedSet subSet(double from, double to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); } 
/* 225 */     public DoubleSortedSet headSet(double to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); } 
/* 226 */     public DoubleSortedSet tailSet(double from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); } 
/*     */     public DoubleBidirectionalIterator iterator() {
/* 228 */       return this.sortedSet.iterator(); } 
/* 229 */     public DoubleBidirectionalIterator iterator(double from) { return this.sortedSet.iterator(from); } 
/*     */     @Deprecated
/*     */     public DoubleBidirectionalIterator doubleIterator() {
/* 232 */       return this.sortedSet.iterator();
/*     */     }
/* 234 */     public double firstDouble() { synchronized (this.sync) { return this.sortedSet.firstDouble(); }  } 
/* 235 */     public double lastDouble() { synchronized (this.sync) { return this.sortedSet.lastDouble(); } }
/*     */ 
/*     */     public Double first() {
/* 238 */       synchronized (this.sync) { return (Double)this.sortedSet.first(); }  } 
/* 239 */     public Double last() { synchronized (this.sync) { return (Double)this.sortedSet.last(); }  } 
/*     */     public DoubleSortedSet subSet(Double from, Double to) {
/* 241 */       return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); } 
/* 242 */     public DoubleSortedSet headSet(Double to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); } 
/* 243 */     public DoubleSortedSet tailSet(Double from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class Singleton extends DoubleSets.Singleton
/*     */     implements DoubleSortedSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     final DoubleComparator comparator;
/*     */ 
/*     */     private Singleton(double element, DoubleComparator comparator)
/*     */     {
/* 108 */       super();
/* 109 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     private Singleton(double element) {
/* 113 */       this(element, null);
/*     */     }
/*     */ 
/*     */     final int compare(double k1, double k2)
/*     */     {
/* 118 */       return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/*     */     }
/*     */ 
/*     */     @Deprecated
/*     */     public DoubleBidirectionalIterator doubleIterator() {
/* 123 */       return iterator();
/*     */     }
/*     */ 
/*     */     public DoubleBidirectionalIterator iterator(double from) {
/* 127 */       DoubleBidirectionalIterator i = iterator();
/* 128 */       if (compare(this.element, from) <= 0) i.next();
/* 129 */       return i;
/*     */     }
/*     */     public DoubleComparator comparator() {
/* 132 */       return this.comparator;
/*     */     }
/*     */     public DoubleSortedSet subSet(double from, double to) {
/* 135 */       if ((compare(from, this.element) <= 0) && (compare(this.element, to) < 0)) return this; return DoubleSortedSets.EMPTY_SET;
/*     */     }
/*     */     public DoubleSortedSet headSet(double to) {
/* 138 */       if (compare(this.element, to) < 0) return this; return DoubleSortedSets.EMPTY_SET;
/*     */     }
/*     */     public DoubleSortedSet tailSet(double from) {
/* 141 */       if (compare(from, this.element) <= 0) return this; return DoubleSortedSets.EMPTY_SET;
/*     */     }
/* 143 */     public double firstDouble() { return this.element; } 
/* 144 */     public double lastDouble() { return this.element; }
/*     */ 
/*     */     public Double first() {
/* 147 */       return Double.valueOf(this.element); } 
/* 148 */     public Double last() { return Double.valueOf(this.element); }
/*     */ 
/*     */     public DoubleSortedSet subSet(Double from, Double to) {
/* 151 */       return subSet(from.doubleValue(), to.doubleValue()); } 
/* 152 */     public DoubleSortedSet headSet(Double to) { return headSet(to.doubleValue()); } 
/* 153 */     public DoubleSortedSet tailSet(Double from) { return tailSet(from.doubleValue()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySet extends DoubleSets.EmptySet
/*     */     implements DoubleSortedSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(double ok)
/*     */     {
/*  61 */       throw new UnsupportedOperationException(); } 
/*  63 */     @Deprecated
/*     */     public DoubleBidirectionalIterator doubleIterator() { return iterator(); } 
/*     */     public DoubleBidirectionalIterator iterator(double from) {
/*  65 */       return DoubleIterators.EMPTY_ITERATOR;
/*     */     }
/*  67 */     public DoubleSortedSet subSet(double from, double to) { return DoubleSortedSets.EMPTY_SET; } 
/*     */     public DoubleSortedSet headSet(double from) {
/*  69 */       return DoubleSortedSets.EMPTY_SET;
/*     */     }
/*  71 */     public DoubleSortedSet tailSet(double to) { return DoubleSortedSets.EMPTY_SET; } 
/*  72 */     public double firstDouble() { throw new NoSuchElementException(); } 
/*  73 */     public double lastDouble() { throw new NoSuchElementException(); } 
/*  74 */     public DoubleComparator comparator() { return null; } 
/*  75 */     public DoubleSortedSet subSet(Double from, Double to) { return DoubleSortedSets.EMPTY_SET; } 
/*  76 */     public DoubleSortedSet headSet(Double from) { return DoubleSortedSets.EMPTY_SET; } 
/*  77 */     public DoubleSortedSet tailSet(Double to) { return DoubleSortedSets.EMPTY_SET; } 
/*  78 */     public Double first() { throw new NoSuchElementException(); } 
/*  79 */     public Double last() { throw new NoSuchElementException(); } 
/*  80 */     public Object clone() { return DoubleSortedSets.EMPTY_SET; } 
/*     */     private Object readResolve() {
/*  82 */       return DoubleSortedSets.EMPTY_SET;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleSortedSets
 * JD-Core Version:    0.6.2
 */