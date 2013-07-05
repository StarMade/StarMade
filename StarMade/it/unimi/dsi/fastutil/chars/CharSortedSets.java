/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class CharSortedSets
/*     */ {
/*  93 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static CharSortedSet singleton(char element)
/*     */   {
/* 165 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static CharSortedSet singleton(char element, CharComparator comparator)
/*     */   {
/* 176 */     return new Singleton(element, comparator, null);
/*     */   }
/*     */ 
/*     */   public static CharSortedSet singleton(Object element)
/*     */   {
/* 188 */     return new Singleton(((Character)element).charValue(), null);
/*     */   }
/*     */ 
/*     */   public static CharSortedSet singleton(Object element, CharComparator comparator)
/*     */   {
/* 199 */     return new Singleton(((Character)element).charValue(), comparator, null);
/*     */   }
/*     */ 
/*     */   public static CharSortedSet synchronize(CharSortedSet s)
/*     */   {
/* 254 */     return new SynchronizedSortedSet(s);
/*     */   }
/*     */ 
/*     */   public static CharSortedSet synchronize(CharSortedSet s, Object sync)
/*     */   {
/* 264 */     return new SynchronizedSortedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static CharSortedSet unmodifiable(CharSortedSet s)
/*     */   {
/* 315 */     return new UnmodifiableSortedSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedSet extends CharSets.UnmodifiableSet
/*     */     implements CharSortedSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final CharSortedSet sortedSet;
/*     */ 
/*     */     protected UnmodifiableSortedSet(CharSortedSet s)
/*     */     {
/* 279 */       super();
/* 280 */       this.sortedSet = s;
/*     */     }
/*     */     public CharComparator comparator() {
/* 283 */       return this.sortedSet.comparator();
/*     */     }
/* 285 */     public CharSortedSet subSet(char from, char to) { return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); } 
/* 286 */     public CharSortedSet headSet(char to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); } 
/* 287 */     public CharSortedSet tailSet(char from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); } 
/*     */     public CharBidirectionalIterator iterator() {
/* 289 */       return CharIterators.unmodifiable(this.sortedSet.iterator()); } 
/* 290 */     public CharBidirectionalIterator iterator(char from) { return CharIterators.unmodifiable(this.sortedSet.iterator(from)); } 
/*     */     @Deprecated
/*     */     public CharBidirectionalIterator charIterator() {
/* 293 */       return iterator();
/*     */     }
/* 295 */     public char firstChar() { return this.sortedSet.firstChar(); } 
/* 296 */     public char lastChar() { return this.sortedSet.lastChar(); }
/*     */ 
/*     */     public Character first() {
/* 299 */       return (Character)this.sortedSet.first(); } 
/* 300 */     public Character last() { return (Character)this.sortedSet.last(); } 
/*     */     public CharSortedSet subSet(Character from, Character to) {
/* 302 */       return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to)); } 
/* 303 */     public CharSortedSet headSet(Character to) { return new UnmodifiableSortedSet(this.sortedSet.headSet(to)); } 
/* 304 */     public CharSortedSet tailSet(Character from) { return new UnmodifiableSortedSet(this.sortedSet.tailSet(from)); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedSet extends CharSets.SynchronizedSet
/*     */     implements CharSortedSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final CharSortedSet sortedSet;
/*     */ 
/*     */     protected SynchronizedSortedSet(CharSortedSet s, Object sync)
/*     */     {
/* 213 */       super(sync);
/* 214 */       this.sortedSet = s;
/*     */     }
/*     */ 
/*     */     protected SynchronizedSortedSet(CharSortedSet s) {
/* 218 */       super();
/* 219 */       this.sortedSet = s;
/*     */     }
/*     */     public CharComparator comparator() {
/* 222 */       synchronized (this.sync) { return this.sortedSet.comparator(); } 
/*     */     }
/* 224 */     public CharSortedSet subSet(char from, char to) { return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); } 
/* 225 */     public CharSortedSet headSet(char to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); } 
/* 226 */     public CharSortedSet tailSet(char from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); } 
/*     */     public CharBidirectionalIterator iterator() {
/* 228 */       return this.sortedSet.iterator(); } 
/* 229 */     public CharBidirectionalIterator iterator(char from) { return this.sortedSet.iterator(from); } 
/*     */     @Deprecated
/*     */     public CharBidirectionalIterator charIterator() {
/* 232 */       return this.sortedSet.iterator();
/*     */     }
/* 234 */     public char firstChar() { synchronized (this.sync) { return this.sortedSet.firstChar(); }  } 
/* 235 */     public char lastChar() { synchronized (this.sync) { return this.sortedSet.lastChar(); } }
/*     */ 
/*     */     public Character first() {
/* 238 */       synchronized (this.sync) { return (Character)this.sortedSet.first(); }  } 
/* 239 */     public Character last() { synchronized (this.sync) { return (Character)this.sortedSet.last(); }  } 
/*     */     public CharSortedSet subSet(Character from, Character to) {
/* 241 */       return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync); } 
/* 242 */     public CharSortedSet headSet(Character to) { return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync); } 
/* 243 */     public CharSortedSet tailSet(Character from) { return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class Singleton extends CharSets.Singleton
/*     */     implements CharSortedSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     final CharComparator comparator;
/*     */ 
/*     */     private Singleton(char element, CharComparator comparator)
/*     */     {
/* 108 */       super();
/* 109 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     private Singleton(char element) {
/* 113 */       this(element, null);
/*     */     }
/*     */ 
/*     */     final int compare(char k1, char k2)
/*     */     {
/* 118 */       return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/*     */     }
/*     */ 
/*     */     @Deprecated
/*     */     public CharBidirectionalIterator charIterator() {
/* 123 */       return iterator();
/*     */     }
/*     */ 
/*     */     public CharBidirectionalIterator iterator(char from) {
/* 127 */       CharBidirectionalIterator i = iterator();
/* 128 */       if (compare(this.element, from) <= 0) i.next();
/* 129 */       return i;
/*     */     }
/*     */     public CharComparator comparator() {
/* 132 */       return this.comparator;
/*     */     }
/*     */     public CharSortedSet subSet(char from, char to) {
/* 135 */       if ((compare(from, this.element) <= 0) && (compare(this.element, to) < 0)) return this; return CharSortedSets.EMPTY_SET;
/*     */     }
/*     */     public CharSortedSet headSet(char to) {
/* 138 */       if (compare(this.element, to) < 0) return this; return CharSortedSets.EMPTY_SET;
/*     */     }
/*     */     public CharSortedSet tailSet(char from) {
/* 141 */       if (compare(from, this.element) <= 0) return this; return CharSortedSets.EMPTY_SET;
/*     */     }
/* 143 */     public char firstChar() { return this.element; } 
/* 144 */     public char lastChar() { return this.element; }
/*     */ 
/*     */     public Character first() {
/* 147 */       return Character.valueOf(this.element); } 
/* 148 */     public Character last() { return Character.valueOf(this.element); }
/*     */ 
/*     */     public CharSortedSet subSet(Character from, Character to) {
/* 151 */       return subSet(from.charValue(), to.charValue()); } 
/* 152 */     public CharSortedSet headSet(Character to) { return headSet(to.charValue()); } 
/* 153 */     public CharSortedSet tailSet(Character from) { return tailSet(from.charValue()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySet extends CharSets.EmptySet
/*     */     implements CharSortedSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(char ok)
/*     */     {
/*  61 */       throw new UnsupportedOperationException(); } 
/*  63 */     @Deprecated
/*     */     public CharBidirectionalIterator charIterator() { return iterator(); } 
/*     */     public CharBidirectionalIterator iterator(char from) {
/*  65 */       return CharIterators.EMPTY_ITERATOR;
/*     */     }
/*  67 */     public CharSortedSet subSet(char from, char to) { return CharSortedSets.EMPTY_SET; } 
/*     */     public CharSortedSet headSet(char from) {
/*  69 */       return CharSortedSets.EMPTY_SET;
/*     */     }
/*  71 */     public CharSortedSet tailSet(char to) { return CharSortedSets.EMPTY_SET; } 
/*  72 */     public char firstChar() { throw new NoSuchElementException(); } 
/*  73 */     public char lastChar() { throw new NoSuchElementException(); } 
/*  74 */     public CharComparator comparator() { return null; } 
/*  75 */     public CharSortedSet subSet(Character from, Character to) { return CharSortedSets.EMPTY_SET; } 
/*  76 */     public CharSortedSet headSet(Character from) { return CharSortedSets.EMPTY_SET; } 
/*  77 */     public CharSortedSet tailSet(Character to) { return CharSortedSets.EMPTY_SET; } 
/*  78 */     public Character first() { throw new NoSuchElementException(); } 
/*  79 */     public Character last() { throw new NoSuchElementException(); } 
/*  80 */     public Object clone() { return CharSortedSets.EMPTY_SET; } 
/*     */     private Object readResolve() {
/*  82 */       return CharSortedSets.EMPTY_SET;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharSortedSets
 * JD-Core Version:    0.6.2
 */