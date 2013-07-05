/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class ByteSets
/*     */ {
/*  71 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static ByteSet singleton(byte element)
/*     */   {
/* 114 */     return new Singleton(element);
/*     */   }
/*     */ 
/*     */   public static ByteSet singleton(Byte element)
/*     */   {
/* 128 */     return new Singleton(element.byteValue());
/*     */   }
/*     */ 
/*     */   public static ByteSet synchronize(ByteSet s)
/*     */   {
/* 159 */     return new SynchronizedSet(s);
/*     */   }
/*     */ 
/*     */   public static ByteSet synchronize(ByteSet s, Object sync)
/*     */   {
/* 169 */     return new SynchronizedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static ByteSet unmodifiable(ByteSet s)
/*     */   {
/* 195 */     return new UnmodifiableSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSet extends ByteCollections.UnmodifiableCollection
/*     */     implements ByteSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected UnmodifiableSet(ByteSet s)
/*     */     {
/* 180 */       super();
/*     */     }
/*     */     public boolean remove(byte k) {
/* 183 */       throw new UnsupportedOperationException(); } 
/* 184 */     public boolean equals(Object o) { return this.collection.equals(o); } 
/* 185 */     public int hashCode() { return this.collection.hashCode(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSet extends ByteCollections.SynchronizedCollection
/*     */     implements ByteSet, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected SynchronizedSet(ByteSet s, Object sync)
/*     */     {
/* 140 */       super(sync);
/*     */     }
/*     */ 
/*     */     protected SynchronizedSet(ByteSet s) {
/* 144 */       super();
/*     */     }
/*     */     public boolean remove(byte k) {
/* 147 */       synchronized (this.sync) { return this.collection.remove(Byte.valueOf(k)); }  } 
/* 148 */     public boolean equals(Object o) { synchronized (this.sync) { return this.collection.equals(o); }  } 
/* 149 */     public int hashCode() { synchronized (this.sync) { return this.collection.hashCode(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends AbstractByteSet
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final byte element;
/*     */ 
/*     */     protected Singleton(byte element)
/*     */     {
/*  80 */       this.element = element;
/*     */     }
/*  82 */     public boolean add(byte k) { throw new UnsupportedOperationException(); } 
/*  83 */     public boolean contains(byte k) { return k == this.element; } 
/*  84 */     public boolean addAll(Collection<? extends Byte> c) { throw new UnsupportedOperationException(); } 
/*  85 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  86 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public byte[] toByteArray() {
/*  89 */       byte[] a = new byte[1];
/*  90 */       a[0] = this.element;
/*  91 */       return a;
/*     */     }
/*  93 */     public boolean addAll(ByteCollection c) { throw new UnsupportedOperationException(); } 
/*  94 */     public boolean removeAll(ByteCollection c) { throw new UnsupportedOperationException(); } 
/*  95 */     public boolean retainAll(ByteCollection c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public ByteListIterator iterator() {
/*  98 */       return ByteIterators.singleton(this.element);
/*     */     }
/* 100 */     public int size() { return 1; } 
/*     */     public Object clone() {
/* 102 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptySet extends ByteCollections.EmptyCollection
/*     */     implements ByteSet, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(byte ok)
/*     */     {
/*  61 */       throw new UnsupportedOperationException(); } 
/*  62 */     public Object clone() { return ByteSets.EMPTY_SET; } 
/*  63 */     private Object readResolve() { return ByteSets.EMPTY_SET; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteSets
 * JD-Core Version:    0.6.2
 */