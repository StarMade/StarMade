/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ByteSortedSets
/*     */ {
/*  93 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static ByteSortedSet singleton(byte element)
/*     */   {
/* 165 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static ByteSortedSet singleton(byte element, ByteComparator comparator)
/*     */   {
/* 176 */     return new Singleton(element, comparator, null);
/*     */   }
/*     */ 
/*     */   public static ByteSortedSet singleton(Object element)
/*     */   {
/* 188 */     return new Singleton(((Byte)element).byteValue(), null);
/*     */   }
/*     */ 
/*     */   public static ByteSortedSet singleton(Object element, ByteComparator comparator)
/*     */   {
/* 199 */     return new Singleton(((Byte)element).byteValue(), comparator, null);
/*     */   }
/*     */ 
/*     */   public static ByteSortedSet synchronize(ByteSortedSet s)
/*     */   {
/* 254 */     return new SynchronizedSortedSet(s);
/*     */   }
/*     */ 
/*     */   public static ByteSortedSet synchronize(ByteSortedSet s, Object sync)
/*     */   {
/* 264 */     return new SynchronizedSortedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static ByteSortedSet unmodifiable(ByteSortedSet s)
/*     */   {
/* 315 */     return new UnmodifiableSortedSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedSet extends ByteSets.UnmodifiableSet
/*     */     implements ByteSortedSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ByteSortedSet sortedSet;
/*     */ 
/*     */     protected UnmodifiableSortedSet(ByteSortedSet s)
/*     */     {
/* 279 */       super();
/* 280 */       this.sortedSet = s;
/*     */     }
/*     */     public ByteComparator comparator() {
/* 283 */       return this.sortedSet.comparator();
/*     */     }
/* 285 */     public ByteSortedSet subSet(byte from, byte to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); } 
/* 286 */     public ByteSortedSet headSet(byte to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); } 
/* 287 */     public ByteSortedSet tailSet(byte from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); } 
/*     */     public ByteBidirectionalIterator iterator() {
/* 289 */       return ByteIterators.unmodifiable(this.sortedSet.iterator()); } 
/* 290 */     public ByteBidirectionalIterator iterator(byte from) { return ByteIterators.unmodifiable(this.sortedSet.iterator(from)); } 
/*     */     @Deprecated
/*     */     public ByteBidirectionalIterator byteIterator() {
/* 293 */       return iterator();
/*     */     }
/* 295 */     public byte firstByte() { return this.sortedSet.firstByte(); } 
/* 296 */     public byte lastByte() { return this.sortedSet.lastByte(); }
/*     */ 
/*     */     public Byte first() {
/* 299 */       return (Byte)this.sortedSet.first(); } 
/* 300 */     public Byte last() { return (Byte)this.sortedSet.last(); } 
/*     */     public ByteSortedSet subSet(Byte from, Byte to) {
/* 302 */       return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); } 
/* 303 */     public ByteSortedSet headSet(Byte to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); } 
/* 304 */     public ByteSortedSet tailSet(Byte from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedSet extends ByteSets.SynchronizedSet
/*     */     implements ByteSortedSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ByteSortedSet sortedSet;
/*     */ 
/*     */     protected SynchronizedSortedSet(ByteSortedSet s, Object sync)
/*     */     {
/* 213 */       super(sync);
/* 214 */       this.sortedSet = s;
/*     */     }
/*     */ 
/*     */     protected SynchronizedSortedSet(ByteSortedSet s) {
/* 218 */       super();
/* 219 */       this.sortedSet = s;
/*     */     }
/*     */     public ByteComparator comparator() {
/* 222 */       synchronized (this.sync) { return this.sortedSet.comparator(); } 
/*     */     }
/* 224 */     public ByteSortedSet subSet(byte from, byte to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); } 
/* 225 */     public ByteSortedSet headSet(byte to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); } 
/* 226 */     public ByteSortedSet tailSet(byte from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); } 
/*     */     public ByteBidirectionalIterator iterator() {
/* 228 */       return this.sortedSet.iterator(); } 
/* 229 */     public ByteBidirectionalIterator iterator(byte from) { return this.sortedSet.iterator(from); } 
/*     */     @Deprecated
/*     */     public ByteBidirectionalIterator byteIterator() {
/* 232 */       return this.sortedSet.iterator();
/*     */     }
/* 234 */     public byte firstByte() { synchronized (this.sync) { return this.sortedSet.firstByte(); }  } 
/* 235 */     public byte lastByte() { synchronized (this.sync) { return this.sortedSet.lastByte(); } }
/*     */ 
/*     */     public Byte first() {
/* 238 */       synchronized (this.sync) { return (Byte)this.sortedSet.first(); }  } 
/* 239 */     public Byte last() { synchronized (this.sync) { return (Byte)this.sortedSet.last(); }  } 
/*     */     public ByteSortedSet subSet(Byte from, Byte to) {
/* 241 */       return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); } 
/* 242 */     public ByteSortedSet headSet(Byte to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); } 
/* 243 */     public ByteSortedSet tailSet(Byte from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class Singleton extends ByteSets.Singleton
/*     */     implements ByteSortedSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     final ByteComparator comparator;
/*     */ 
/*     */     private Singleton(byte element, ByteComparator comparator)
/*     */     {
/* 108 */       super();
/* 109 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     private Singleton(byte element) {
/* 113 */       this(element, null);
/*     */     }
/*     */ 
/*     */     final int compare(byte k1, byte k2)
/*     */     {
/* 118 */       return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/*     */     }
/*     */ 
/*     */     @Deprecated
/*     */     public ByteBidirectionalIterator byteIterator() {
/* 123 */       return iterator();
/*     */     }
/*     */ 
/*     */     public ByteBidirectionalIterator iterator(byte from) {
/* 127 */       ByteBidirectionalIterator i = iterator();
/* 128 */       if (compare(this.element, from) <= 0) i.next();
/* 129 */       return i;
/*     */     }
/*     */     public ByteComparator comparator() {
/* 132 */       return this.comparator;
/*     */     }
/*     */     public ByteSortedSet subSet(byte from, byte to) {
/* 135 */       if ((compare(from, this.element) <= 0) && (compare(this.element, to) < 0)) return this; return ByteSortedSets.EMPTY_SET;
/*     */     }
/*     */     public ByteSortedSet headSet(byte to) {
/* 138 */       if (compare(this.element, to) < 0) return this; return ByteSortedSets.EMPTY_SET;
/*     */     }
/*     */     public ByteSortedSet tailSet(byte from) {
/* 141 */       if (compare(from, this.element) <= 0) return this; return ByteSortedSets.EMPTY_SET;
/*     */     }
/* 143 */     public byte firstByte() { return this.element; } 
/* 144 */     public byte lastByte() { return this.element; }
/*     */ 
/*     */     public Byte first() {
/* 147 */       return Byte.valueOf(this.element); } 
/* 148 */     public Byte last() { return Byte.valueOf(this.element); }
/*     */ 
/*     */     public ByteSortedSet subSet(Byte from, Byte to) {
/* 151 */       return subSet(from.byteValue(), to.byteValue()); } 
/* 152 */     public ByteSortedSet headSet(Byte to) { return headSet(to.byteValue()); } 
/* 153 */     public ByteSortedSet tailSet(Byte from) { return tailSet(from.byteValue()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySet extends ByteSets.EmptySet
/*     */     implements ByteSortedSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(byte ok)
/*     */     {
/*  61 */       throw new UnsupportedOperationException(); } 
/*  63 */     @Deprecated
/*     */     public ByteBidirectionalIterator byteIterator() { return iterator(); } 
/*     */     public ByteBidirectionalIterator iterator(byte from) {
/*  65 */       return ByteIterators.EMPTY_ITERATOR;
/*     */     }
/*  67 */     public ByteSortedSet subSet(byte from, byte to) { return ByteSortedSets.EMPTY_SET; } 
/*     */     public ByteSortedSet headSet(byte from) {
/*  69 */       return ByteSortedSets.EMPTY_SET;
/*     */     }
/*  71 */     public ByteSortedSet tailSet(byte to) { return ByteSortedSets.EMPTY_SET; } 
/*  72 */     public byte firstByte() { throw new NoSuchElementException(); } 
/*  73 */     public byte lastByte() { throw new NoSuchElementException(); } 
/*  74 */     public ByteComparator comparator() { return null; } 
/*  75 */     public ByteSortedSet subSet(Byte from, Byte to) { return ByteSortedSets.EMPTY_SET; } 
/*  76 */     public ByteSortedSet headSet(Byte from) { return ByteSortedSets.EMPTY_SET; } 
/*  77 */     public ByteSortedSet tailSet(Byte to) { return ByteSortedSets.EMPTY_SET; } 
/*  78 */     public Byte first() { throw new NoSuchElementException(); } 
/*  79 */     public Byte last() { throw new NoSuchElementException(); } 
/*  80 */     public Object clone() { return ByteSortedSets.EMPTY_SET; } 
/*     */     private Object readResolve() {
/*  82 */       return ByteSortedSets.EMPTY_SET;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteSortedSets
 * JD-Core Version:    0.6.2
 */