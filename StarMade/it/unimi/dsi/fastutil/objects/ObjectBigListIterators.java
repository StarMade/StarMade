/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ObjectBigListIterators
/*     */ {
/*  77 */   public static final EmptyBigListIterator EMPTY_BIG_LIST_ITERATOR = new EmptyBigListIterator();
/*     */ 
/*     */   public static <K> ObjectBigListIterator<K> singleton(K element)
/*     */   {
/* 110 */     return new SingletonBigListIterator(element);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectBigListIterator<K> unmodifiable(ObjectBigListIterator<K> i)
/*     */   {
/* 140 */     return new UnmodifiableBigListIterator(i);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectBigListIterator<K> asBigListIterator(ObjectListIterator<K> i)
/*     */   {
/* 177 */     return new BigListIteratorListIterator(i);
/*     */   }
/*     */ 
/*     */   public static class BigListIteratorListIterator<K> extends AbstractObjectBigListIterator<K>
/*     */   {
/*     */     protected final ObjectListIterator<K> i;
/*     */ 
/*     */     protected BigListIteratorListIterator(ObjectListIterator<K> i)
/*     */     {
/* 149 */       this.i = i;
/*     */     }
/*     */ 
/*     */     private int intDisplacement(long n) {
/* 153 */       if ((n < -2147483648L) || (n > 2147483647L)) throw new IndexOutOfBoundsException("This big iterator is restricted to 32-bit displacements");
/* 154 */       return (int)n;
/*     */     }
/*     */     public void set(K ok) {
/* 157 */       this.i.set(ok); } 
/* 158 */     public void add(K ok) { this.i.add(ok); } 
/* 159 */     public int back(int n) { return this.i.back(n); } 
/* 160 */     public long back(long n) { return this.i.back(intDisplacement(n)); } 
/* 161 */     public void remove() { this.i.remove(); } 
/* 162 */     public int skip(int n) { return this.i.skip(n); } 
/* 163 */     public long skip(long n) { return this.i.skip(intDisplacement(n)); } 
/* 164 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 165 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 166 */     public K next() { return this.i.next(); } 
/* 167 */     public K previous() { return this.i.previous(); } 
/* 168 */     public long nextIndex() { return this.i.nextIndex(); } 
/* 169 */     public long previousIndex() { return this.i.previousIndex(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableBigListIterator<K> extends AbstractObjectBigListIterator<K>
/*     */   {
/*     */     protected final ObjectBigListIterator<K> i;
/*     */ 
/*     */     public UnmodifiableBigListIterator(ObjectBigListIterator<K> i)
/*     */     {
/* 120 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 123 */       return this.i.hasNext(); } 
/* 124 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 125 */     public K next() { return this.i.next(); } 
/* 126 */     public K previous() { return this.i.previous(); } 
/* 127 */     public long nextIndex() { return this.i.nextIndex(); } 
/* 128 */     public long previousIndex() { return this.i.previousIndex(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class SingletonBigListIterator<K> extends AbstractObjectBigListIterator<K>
/*     */   {
/*     */     private final K element;
/*     */     private int curr;
/*     */ 
/*     */     public SingletonBigListIterator(K element)
/*     */     {
/*  83 */       this.element = element;
/*     */     }
/*  85 */     public boolean hasNext() { return this.curr == 0; } 
/*  86 */     public boolean hasPrevious() { return this.curr == 1; } 
/*     */     public K next() {
/*  88 */       if (!hasNext()) throw new NoSuchElementException();
/*  89 */       this.curr = 1;
/*  90 */       return this.element;
/*     */     }
/*     */     public K previous() {
/*  93 */       if (!hasPrevious()) throw new NoSuchElementException();
/*  94 */       this.curr = 0;
/*  95 */       return this.element;
/*     */     }
/*     */     public long nextIndex() {
/*  98 */       return this.curr;
/*     */     }
/*     */     public long previousIndex() {
/* 101 */       return this.curr - 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptyBigListIterator<K> extends AbstractObjectBigListIterator<K>
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/*  60 */       return false; } 
/*  61 */     public boolean hasPrevious() { return false; } 
/*  62 */     public K next() { throw new NoSuchElementException(); } 
/*  63 */     public K previous() { throw new NoSuchElementException(); } 
/*  64 */     public long nextIndex() { return 0L; } 
/*  65 */     public long previousIndex() { return -1L; } 
/*  66 */     public long skip(long n) { return 0L; } 
/*  67 */     public long back(long n) { return 0L; } 
/*  68 */     public Object clone() { return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR; } 
/*  69 */     private Object readResolve() { return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectBigListIterators
 * JD-Core Version:    0.6.2
 */