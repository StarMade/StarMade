/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class CharSets
/*     */ {
/*  71 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static CharSet singleton(char element)
/*     */   {
/* 114 */     return new Singleton(element);
/*     */   }
/*     */ 
/*     */   public static CharSet singleton(Character element)
/*     */   {
/* 128 */     return new Singleton(element.charValue());
/*     */   }
/*     */ 
/*     */   public static CharSet synchronize(CharSet s)
/*     */   {
/* 159 */     return new SynchronizedSet(s);
/*     */   }
/*     */ 
/*     */   public static CharSet synchronize(CharSet s, Object sync)
/*     */   {
/* 169 */     return new SynchronizedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static CharSet unmodifiable(CharSet s)
/*     */   {
/* 195 */     return new UnmodifiableSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSet extends CharCollections.UnmodifiableCollection
/*     */     implements CharSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected UnmodifiableSet(CharSet s)
/*     */     {
/* 180 */       super();
/*     */     }
/*     */     public boolean remove(char k) {
/* 183 */       throw new UnsupportedOperationException(); } 
/* 184 */     public boolean equals(Object o) { return this.collection.equals(o); } 
/* 185 */     public int hashCode() { return this.collection.hashCode(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSet extends CharCollections.SynchronizedCollection
/*     */     implements CharSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected SynchronizedSet(CharSet s, Object sync)
/*     */     {
/* 140 */       super(sync);
/*     */     }
/*     */ 
/*     */     protected SynchronizedSet(CharSet s) {
/* 144 */       super();
/*     */     }
/*     */     public boolean remove(char k) {
/* 147 */       synchronized (this.sync) { return this.collection.remove(Character.valueOf(k)); }  } 
/* 148 */     public boolean equals(Object o) { synchronized (this.sync) { return this.collection.equals(o); }  } 
/* 149 */     public int hashCode() { synchronized (this.sync) { return this.collection.hashCode(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends AbstractCharSet
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final char element;
/*     */ 
/*     */     protected Singleton(char element)
/*     */     {
/*  80 */       this.element = element;
/*     */     }
/*  82 */     public boolean add(char k) { throw new UnsupportedOperationException(); } 
/*  83 */     public boolean contains(char k) { return k == this.element; } 
/*  84 */     public boolean addAll(Collection<? extends Character> c) { throw new UnsupportedOperationException(); } 
/*  85 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  86 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public char[] toCharArray() {
/*  89 */       char[] a = new char[1];
/*  90 */       a[0] = this.element;
/*  91 */       return a;
/*     */     }
/*  93 */     public boolean addAll(CharCollection c) { throw new UnsupportedOperationException(); } 
/*  94 */     public boolean removeAll(CharCollection c) { throw new UnsupportedOperationException(); } 
/*  95 */     public boolean retainAll(CharCollection c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public CharListIterator iterator() {
/*  98 */       return CharIterators.singleton(this.element);
/*     */     }
/* 100 */     public int size() { return 1; } 
/*     */     public Object clone() {
/* 102 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptySet extends CharCollections.EmptyCollection
/*     */     implements CharSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(char ok)
/*     */     {
/*  61 */       throw new UnsupportedOperationException(); } 
/*  62 */     public Object clone() { return CharSets.EMPTY_SET; } 
/*  63 */     private Object readResolve() { return CharSets.EMPTY_SET; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharSets
 * JD-Core Version:    0.6.2
 */