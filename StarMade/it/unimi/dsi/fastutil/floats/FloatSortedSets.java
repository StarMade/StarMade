/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class FloatSortedSets
/*     */ {
/*  93 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static FloatSortedSet singleton(float element)
/*     */   {
/* 165 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static FloatSortedSet singleton(float element, FloatComparator comparator)
/*     */   {
/* 176 */     return new Singleton(element, comparator, null);
/*     */   }
/*     */ 
/*     */   public static FloatSortedSet singleton(Object element)
/*     */   {
/* 188 */     return new Singleton(((Float)element).floatValue(), null);
/*     */   }
/*     */ 
/*     */   public static FloatSortedSet singleton(Object element, FloatComparator comparator)
/*     */   {
/* 199 */     return new Singleton(((Float)element).floatValue(), comparator, null);
/*     */   }
/*     */ 
/*     */   public static FloatSortedSet synchronize(FloatSortedSet s)
/*     */   {
/* 254 */     return new SynchronizedSortedSet(s);
/*     */   }
/*     */ 
/*     */   public static FloatSortedSet synchronize(FloatSortedSet s, Object sync)
/*     */   {
/* 264 */     return new SynchronizedSortedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static FloatSortedSet unmodifiable(FloatSortedSet s)
/*     */   {
/* 315 */     return new UnmodifiableSortedSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedSet extends FloatSets.UnmodifiableSet
/*     */     implements FloatSortedSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final FloatSortedSet sortedSet;
/*     */ 
/*     */     protected UnmodifiableSortedSet(FloatSortedSet s)
/*     */     {
/* 279 */       super();
/* 280 */       this.sortedSet = s;
/*     */     }
/*     */     public FloatComparator comparator() {
/* 283 */       return this.sortedSet.comparator();
/*     */     }
/* 285 */     public FloatSortedSet subSet(float from, float to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); } 
/* 286 */     public FloatSortedSet headSet(float to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); } 
/* 287 */     public FloatSortedSet tailSet(float from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); } 
/*     */     public FloatBidirectionalIterator iterator() {
/* 289 */       return FloatIterators.unmodifiable(this.sortedSet.iterator()); } 
/* 290 */     public FloatBidirectionalIterator iterator(float from) { return FloatIterators.unmodifiable(this.sortedSet.iterator(from)); } 
/*     */     @Deprecated
/*     */     public FloatBidirectionalIterator floatIterator() {
/* 293 */       return iterator();
/*     */     }
/* 295 */     public float firstFloat() { return this.sortedSet.firstFloat(); } 
/* 296 */     public float lastFloat() { return this.sortedSet.lastFloat(); }
/*     */ 
/*     */     public Float first() {
/* 299 */       return (Float)this.sortedSet.first(); } 
/* 300 */     public Float last() { return (Float)this.sortedSet.last(); } 
/*     */     public FloatSortedSet subSet(Float from, Float to) {
/* 302 */       return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); } 
/* 303 */     public FloatSortedSet headSet(Float to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); } 
/* 304 */     public FloatSortedSet tailSet(Float from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedSet extends FloatSets.SynchronizedSet
/*     */     implements FloatSortedSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final FloatSortedSet sortedSet;
/*     */ 
/*     */     protected SynchronizedSortedSet(FloatSortedSet s, Object sync)
/*     */     {
/* 213 */       super(sync);
/* 214 */       this.sortedSet = s;
/*     */     }
/*     */ 
/*     */     protected SynchronizedSortedSet(FloatSortedSet s) {
/* 218 */       super();
/* 219 */       this.sortedSet = s;
/*     */     }
/*     */     public FloatComparator comparator() {
/* 222 */       synchronized (this.sync) { return this.sortedSet.comparator(); } 
/*     */     }
/* 224 */     public FloatSortedSet subSet(float from, float to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); } 
/* 225 */     public FloatSortedSet headSet(float to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); } 
/* 226 */     public FloatSortedSet tailSet(float from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); } 
/*     */     public FloatBidirectionalIterator iterator() {
/* 228 */       return this.sortedSet.iterator(); } 
/* 229 */     public FloatBidirectionalIterator iterator(float from) { return this.sortedSet.iterator(from); } 
/*     */     @Deprecated
/*     */     public FloatBidirectionalIterator floatIterator() {
/* 232 */       return this.sortedSet.iterator();
/*     */     }
/* 234 */     public float firstFloat() { synchronized (this.sync) { return this.sortedSet.firstFloat(); }  } 
/* 235 */     public float lastFloat() { synchronized (this.sync) { return this.sortedSet.lastFloat(); } }
/*     */ 
/*     */     public Float first() {
/* 238 */       synchronized (this.sync) { return (Float)this.sortedSet.first(); }  } 
/* 239 */     public Float last() { synchronized (this.sync) { return (Float)this.sortedSet.last(); }  } 
/*     */     public FloatSortedSet subSet(Float from, Float to) {
/* 241 */       return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); } 
/* 242 */     public FloatSortedSet headSet(Float to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); } 
/* 243 */     public FloatSortedSet tailSet(Float from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class Singleton extends FloatSets.Singleton
/*     */     implements FloatSortedSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     final FloatComparator comparator;
/*     */ 
/*     */     private Singleton(float element, FloatComparator comparator)
/*     */     {
/* 108 */       super();
/* 109 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     private Singleton(float element) {
/* 113 */       this(element, null);
/*     */     }
/*     */ 
/*     */     final int compare(float k1, float k2)
/*     */     {
/* 118 */       return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/*     */     }
/*     */ 
/*     */     @Deprecated
/*     */     public FloatBidirectionalIterator floatIterator() {
/* 123 */       return iterator();
/*     */     }
/*     */ 
/*     */     public FloatBidirectionalIterator iterator(float from) {
/* 127 */       FloatBidirectionalIterator i = iterator();
/* 128 */       if (compare(this.element, from) <= 0) i.next();
/* 129 */       return i;
/*     */     }
/*     */     public FloatComparator comparator() {
/* 132 */       return this.comparator;
/*     */     }
/*     */     public FloatSortedSet subSet(float from, float to) {
/* 135 */       if ((compare(from, this.element) <= 0) && (compare(this.element, to) < 0)) return this; return FloatSortedSets.EMPTY_SET;
/*     */     }
/*     */     public FloatSortedSet headSet(float to) {
/* 138 */       if (compare(this.element, to) < 0) return this; return FloatSortedSets.EMPTY_SET;
/*     */     }
/*     */     public FloatSortedSet tailSet(float from) {
/* 141 */       if (compare(from, this.element) <= 0) return this; return FloatSortedSets.EMPTY_SET;
/*     */     }
/* 143 */     public float firstFloat() { return this.element; } 
/* 144 */     public float lastFloat() { return this.element; }
/*     */ 
/*     */     public Float first() {
/* 147 */       return Float.valueOf(this.element); } 
/* 148 */     public Float last() { return Float.valueOf(this.element); }
/*     */ 
/*     */     public FloatSortedSet subSet(Float from, Float to) {
/* 151 */       return subSet(from.floatValue(), to.floatValue()); } 
/* 152 */     public FloatSortedSet headSet(Float to) { return headSet(to.floatValue()); } 
/* 153 */     public FloatSortedSet tailSet(Float from) { return tailSet(from.floatValue()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySet extends FloatSets.EmptySet
/*     */     implements FloatSortedSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(float ok)
/*     */     {
/*  61 */       throw new UnsupportedOperationException(); } 
/*  63 */     @Deprecated
/*     */     public FloatBidirectionalIterator floatIterator() { return iterator(); } 
/*     */     public FloatBidirectionalIterator iterator(float from) {
/*  65 */       return FloatIterators.EMPTY_ITERATOR;
/*     */     }
/*  67 */     public FloatSortedSet subSet(float from, float to) { return FloatSortedSets.EMPTY_SET; } 
/*     */     public FloatSortedSet headSet(float from) {
/*  69 */       return FloatSortedSets.EMPTY_SET;
/*     */     }
/*  71 */     public FloatSortedSet tailSet(float to) { return FloatSortedSets.EMPTY_SET; } 
/*  72 */     public float firstFloat() { throw new NoSuchElementException(); } 
/*  73 */     public float lastFloat() { throw new NoSuchElementException(); } 
/*  74 */     public FloatComparator comparator() { return null; } 
/*  75 */     public FloatSortedSet subSet(Float from, Float to) { return FloatSortedSets.EMPTY_SET; } 
/*  76 */     public FloatSortedSet headSet(Float from) { return FloatSortedSets.EMPTY_SET; } 
/*  77 */     public FloatSortedSet tailSet(Float to) { return FloatSortedSets.EMPTY_SET; } 
/*  78 */     public Float first() { throw new NoSuchElementException(); } 
/*  79 */     public Float last() { throw new NoSuchElementException(); } 
/*  80 */     public Object clone() { return FloatSortedSets.EMPTY_SET; } 
/*     */     private Object readResolve() {
/*  82 */       return FloatSortedSets.EMPTY_SET;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatSortedSets
 * JD-Core Version:    0.6.2
 */