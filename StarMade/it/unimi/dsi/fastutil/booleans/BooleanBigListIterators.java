/*     */ package it.unimi.dsi.fastutil.booleans;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class BooleanBigListIterators
/*     */ {
/*  78 */   public static final EmptyBigListIterator EMPTY_BIG_LIST_ITERATOR = new EmptyBigListIterator();
/*     */ 
/*     */   public static BooleanBigListIterator singleton(boolean element)
/*     */   {
/* 111 */     return new SingletonBigListIterator(element);
/*     */   }
/*     */ 
/*     */   public static BooleanBigListIterator unmodifiable(BooleanBigListIterator i)
/*     */   {
/* 140 */     return new UnmodifiableBigListIterator(i);
/*     */   }
/*     */ 
/*     */   public static BooleanBigListIterator asBigListIterator(BooleanListIterator i)
/*     */   {
/* 177 */     return new BigListIteratorListIterator(i);
/*     */   }
/*     */ 
/*     */   public static class BigListIteratorListIterator extends AbstractBooleanBigListIterator
/*     */   {
/*     */     protected final BooleanListIterator i;
/*     */ 
/*     */     protected BigListIteratorListIterator(BooleanListIterator i)
/*     */     {
/* 149 */       this.i = i;
/*     */     }
/*     */ 
/*     */     private int intDisplacement(long n) {
/* 153 */       if ((n < -2147483648L) || (n > 2147483647L)) throw new IndexOutOfBoundsException("This big iterator is restricted to 32-bit displacements");
/* 154 */       return (int)n;
/*     */     }
/*     */     public void set(boolean ok) {
/* 157 */       this.i.set(ok); } 
/* 158 */     public void add(boolean ok) { this.i.add(ok); } 
/* 159 */     public int back(int n) { return this.i.back(n); } 
/* 160 */     public long back(long n) { return this.i.back(intDisplacement(n)); } 
/* 161 */     public void remove() { this.i.remove(); } 
/* 162 */     public int skip(int n) { return this.i.skip(n); } 
/* 163 */     public long skip(long n) { return this.i.skip(intDisplacement(n)); } 
/* 164 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 165 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 166 */     public boolean nextBoolean() { return this.i.nextBoolean(); } 
/* 167 */     public boolean previousBoolean() { return this.i.previousBoolean(); } 
/* 168 */     public long nextIndex() { return this.i.nextIndex(); } 
/* 169 */     public long previousIndex() { return this.i.previousIndex(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableBigListIterator extends AbstractBooleanBigListIterator
/*     */   {
/*     */     protected final BooleanBigListIterator i;
/*     */ 
/*     */     public UnmodifiableBigListIterator(BooleanBigListIterator i)
/*     */     {
/* 120 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 123 */       return this.i.hasNext(); } 
/* 124 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 125 */     public boolean nextBoolean() { return this.i.nextBoolean(); } 
/* 126 */     public boolean previousBoolean() { return this.i.previousBoolean(); } 
/* 127 */     public long nextIndex() { return this.i.nextIndex(); } 
/* 128 */     public long previousIndex() { return this.i.previousIndex(); } 
/*     */     public Boolean next() {
/* 130 */       return (Boolean)this.i.next(); } 
/* 131 */     public Boolean previous() { return (Boolean)this.i.previous(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class SingletonBigListIterator extends AbstractBooleanBigListIterator
/*     */   {
/*     */     private final boolean element;
/*     */     private int curr;
/*     */ 
/*     */     public SingletonBigListIterator(boolean element)
/*     */     {
/*  84 */       this.element = element;
/*     */     }
/*  86 */     public boolean hasNext() { return this.curr == 0; } 
/*  87 */     public boolean hasPrevious() { return this.curr == 1; } 
/*     */     public boolean nextBoolean() {
/*  89 */       if (!hasNext()) throw new NoSuchElementException();
/*  90 */       this.curr = 1;
/*  91 */       return this.element;
/*     */     }
/*     */     public boolean previousBoolean() {
/*  94 */       if (!hasPrevious()) throw new NoSuchElementException();
/*  95 */       this.curr = 0;
/*  96 */       return this.element;
/*     */     }
/*     */     public long nextIndex() {
/*  99 */       return this.curr;
/*     */     }
/*     */     public long previousIndex() {
/* 102 */       return this.curr - 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptyBigListIterator extends AbstractBooleanBigListIterator
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/*  61 */       return false; } 
/*  62 */     public boolean hasPrevious() { return false; } 
/*  63 */     public boolean nextBoolean() { throw new NoSuchElementException(); } 
/*  64 */     public boolean previousBoolean() { throw new NoSuchElementException(); } 
/*  65 */     public long nextIndex() { return 0L; } 
/*  66 */     public long previousIndex() { return -1L; } 
/*  67 */     public long skip(long n) { return 0L; } 
/*  68 */     public long back(long n) { return 0L; } 
/*  69 */     public Object clone() { return BooleanBigListIterators.EMPTY_BIG_LIST_ITERATOR; } 
/*  70 */     private Object readResolve() { return BooleanBigListIterators.EMPTY_BIG_LIST_ITERATOR; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanBigListIterators
 * JD-Core Version:    0.6.2
 */