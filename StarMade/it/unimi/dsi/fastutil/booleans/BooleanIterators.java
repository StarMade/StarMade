/*     */ package it.unimi.dsi.fastutil.booleans;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class BooleanIterators
/*     */ {
/*  79 */   public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
/*     */ 
/*     */   public static BooleanListIterator singleton(boolean element)
/*     */   {
/* 112 */     return new SingletonIterator(element);
/*     */   }
/*     */ 
/*     */   public static BooleanListIterator wrap(boolean[] array, int offset, int length)
/*     */   {
/* 181 */     BooleanArrays.ensureOffsetLength(array, offset, length);
/* 182 */     return new ArrayIterator(array, offset, length);
/*     */   }
/*     */ 
/*     */   public static BooleanListIterator wrap(boolean[] array)
/*     */   {
/* 193 */     return new ArrayIterator(array, 0, array.length);
/*     */   }
/*     */ 
/*     */   public static int unwrap(BooleanIterator i, boolean[] array, int offset, int max)
/*     */   {
/* 211 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 212 */     if ((offset < 0) || (offset + max > array.length)) throw new IllegalArgumentException();
/* 213 */     int j = max;
/* 214 */     while ((j-- != 0) && (i.hasNext())) array[(offset++)] = i.nextBoolean();
/* 215 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static int unwrap(BooleanIterator i, boolean[] array)
/*     */   {
/* 229 */     return unwrap(i, array, 0, array.length);
/*     */   }
/*     */ 
/*     */   public static boolean[] unwrap(BooleanIterator i, int max)
/*     */   {
/* 244 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 245 */     boolean[] array = new boolean[16];
/* 246 */     int j = 0;
/*     */ 
/* 248 */     while ((max-- != 0) && (i.hasNext())) {
/* 249 */       if (j == array.length) array = BooleanArrays.grow(array, j + 1);
/* 250 */       array[(j++)] = i.nextBoolean();
/*     */     }
/*     */ 
/* 253 */     return BooleanArrays.trim(array, j);
/*     */   }
/*     */ 
/*     */   public static boolean[] unwrap(BooleanIterator i)
/*     */   {
/* 267 */     return unwrap(i, 2147483647);
/*     */   }
/*     */ 
/*     */   public static int unwrap(BooleanIterator i, BooleanCollection c, int max)
/*     */   {
/* 286 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 287 */     int j = max;
/* 288 */     while ((j-- != 0) && (i.hasNext())) c.add(i.nextBoolean());
/* 289 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static long unwrap(BooleanIterator i, BooleanCollection c)
/*     */   {
/* 305 */     long n = 0L;
/* 306 */     while (i.hasNext()) {
/* 307 */       c.add(i.nextBoolean());
/* 308 */       n += 1L;
/*     */     }
/* 310 */     return n;
/*     */   }
/*     */ 
/*     */   public static int pour(BooleanIterator i, BooleanCollection s, int max)
/*     */   {
/* 328 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 329 */     int j = max;
/* 330 */     while ((j-- != 0) && (i.hasNext())) s.add(i.nextBoolean());
/* 331 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static int pour(BooleanIterator i, BooleanCollection s)
/*     */   {
/* 347 */     return pour(i, s, 2147483647);
/*     */   }
/*     */ 
/*     */   public static BooleanList pour(BooleanIterator i, int max)
/*     */   {
/* 364 */     BooleanArrayList l = new BooleanArrayList();
/* 365 */     pour(i, l, max);
/* 366 */     l.trim();
/* 367 */     return l;
/*     */   }
/*     */ 
/*     */   public static BooleanList pour(BooleanIterator i)
/*     */   {
/* 382 */     return pour(i, 2147483647);
/*     */   }
/*     */ 
/*     */   public static BooleanIterator asBooleanIterator(Iterator i)
/*     */   {
/* 414 */     if ((i instanceof BooleanIterator)) return (BooleanIterator)i;
/* 415 */     return new IteratorWrapper(i);
/*     */   }
/*     */ 
/*     */   public static BooleanListIterator asBooleanIterator(ListIterator i)
/*     */   {
/* 457 */     if ((i instanceof BooleanListIterator)) return (BooleanListIterator)i;
/* 458 */     return new ListIteratorWrapper(i);
/*     */   }
/*     */ 
/*     */   public static BooleanIterator concat(BooleanIterator[] a)
/*     */   {
/* 511 */     return concat(a, 0, a.length);
/*     */   }
/*     */ 
/*     */   public static BooleanIterator concat(BooleanIterator[] a, int offset, int length)
/*     */   {
/* 526 */     return new IteratorConcatenator(a, offset, length);
/*     */   }
/*     */ 
/*     */   public static BooleanIterator unmodifiable(BooleanIterator i)
/*     */   {
/* 544 */     return new UnmodifiableIterator(i);
/*     */   }
/*     */ 
/*     */   public static BooleanBidirectionalIterator unmodifiable(BooleanBidirectionalIterator i)
/*     */   {
/* 564 */     return new UnmodifiableBidirectionalIterator(i);
/*     */   }
/*     */ 
/*     */   public static BooleanListIterator unmodifiable(BooleanListIterator i)
/*     */   {
/* 586 */     return new UnmodifiableListIterator(i);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableListIterator extends AbstractBooleanListIterator
/*     */   {
/*     */     protected final BooleanListIterator i;
/*     */ 
/*     */     public UnmodifiableListIterator(BooleanListIterator i)
/*     */     {
/* 570 */       this.i = i;
/*     */     }
/* 572 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 573 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 574 */     public boolean nextBoolean() { return this.i.nextBoolean(); } 
/* 575 */     public boolean previousBoolean() { return this.i.previousBoolean(); } 
/* 576 */     public int nextIndex() { return this.i.nextIndex(); } 
/* 577 */     public int previousIndex() { return this.i.previousIndex(); } 
/* 578 */     public Boolean next() { return (Boolean)this.i.next(); } 
/* 579 */     public Boolean previous() { return (Boolean)this.i.previous(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableBidirectionalIterator extends AbstractBooleanBidirectionalIterator
/*     */   {
/*     */     protected final BooleanBidirectionalIterator i;
/*     */ 
/*     */     public UnmodifiableBidirectionalIterator(BooleanBidirectionalIterator i)
/*     */     {
/* 550 */       this.i = i;
/*     */     }
/* 552 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 553 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 554 */     public boolean nextBoolean() { return this.i.nextBoolean(); } 
/* 555 */     public boolean previousBoolean() { return this.i.previousBoolean(); } 
/* 556 */     public Boolean next() { return (Boolean)this.i.next(); } 
/* 557 */     public Boolean previous() { return (Boolean)this.i.previous(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableIterator extends AbstractBooleanIterator
/*     */   {
/*     */     protected final BooleanIterator i;
/*     */ 
/*     */     public UnmodifiableIterator(BooleanIterator i)
/*     */     {
/* 533 */       this.i = i;
/*     */     }
/* 535 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 536 */     public boolean nextBoolean() { return this.i.nextBoolean(); } 
/* 537 */     public Boolean next() { return (Boolean)this.i.next(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class IteratorConcatenator extends AbstractBooleanIterator
/*     */   {
/*     */     final BooleanIterator[] a;
/*     */     int offset;
/*     */     int length;
/* 462 */     int lastOffset = -1;
/*     */ 
/* 464 */     public IteratorConcatenator(BooleanIterator[] a, int offset, int length) { this.a = a;
/* 465 */       this.offset = offset;
/* 466 */       this.length = length;
/* 467 */       advance(); }
/*     */ 
/*     */     private void advance() {
/* 470 */       while ((this.length != 0) && 
/* 471 */         (!this.a[this.offset].hasNext())) {
/* 472 */         this.length -= 1;
/* 473 */         this.offset += 1;
/*     */       }
/*     */     }
/*     */ 
/*     */     public boolean hasNext() {
/* 478 */       return this.length > 0;
/*     */     }
/*     */     public boolean nextBoolean() {
/* 481 */       if (!hasNext()) throw new NoSuchElementException();
/* 482 */       boolean next = this.a[(this.lastOffset = this.offset)].nextBoolean();
/* 483 */       advance();
/* 484 */       return next;
/*     */     }
/*     */     public void remove() {
/* 487 */       if (this.lastOffset == -1) throw new IllegalStateException();
/* 488 */       this.a[this.lastOffset].remove();
/*     */     }
/*     */     public int skip(int n) {
/* 491 */       this.lastOffset = -1;
/* 492 */       int skipped = 0;
/* 493 */       while ((skipped < n) && (this.length != 0)) {
/* 494 */         skipped += this.a[this.offset].skip(n - skipped);
/* 495 */         if (this.a[this.offset].hasNext()) break;
/* 496 */         this.length -= 1;
/* 497 */         this.offset += 1;
/*     */       }
/* 499 */       return skipped;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class ListIteratorWrapper extends AbstractBooleanListIterator
/*     */   {
/*     */     final ListIterator<Boolean> i;
/*     */ 
/*     */     public ListIteratorWrapper(ListIterator<Boolean> i)
/*     */     {
/* 423 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 426 */       return this.i.hasNext(); } 
/* 427 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 428 */     public int nextIndex() { return this.i.nextIndex(); } 
/* 429 */     public int previousIndex() { return this.i.previousIndex(); } 
/*     */     public void set(boolean k) {
/* 431 */       this.i.set(Boolean.valueOf(k));
/*     */     }
/* 433 */     public void add(boolean k) { this.i.add(Boolean.valueOf(k)); } 
/* 434 */     public void remove() { this.i.remove(); } 
/*     */     public boolean nextBoolean() {
/* 436 */       return ((Boolean)this.i.next()).booleanValue(); } 
/* 437 */     public boolean previousBoolean() { return ((Boolean)this.i.previous()).booleanValue(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class IteratorWrapper extends AbstractBooleanIterator
/*     */   {
/*     */     final Iterator<Boolean> i;
/*     */ 
/*     */     public IteratorWrapper(Iterator<Boolean> i)
/*     */     {
/* 389 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 392 */       return this.i.hasNext(); } 
/* 393 */     public void remove() { this.i.remove(); } 
/*     */     public boolean nextBoolean() {
/* 395 */       return ((Boolean)this.i.next()).booleanValue();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class ArrayIterator extends AbstractBooleanListIterator
/*     */   {
/*     */     private final boolean[] array;
/*     */     private final int offset;
/*     */     private final int length;
/*     */     private int curr;
/*     */ 
/*     */     public ArrayIterator(boolean[] array, int offset, int length)
/*     */     {
/* 122 */       this.array = array;
/* 123 */       this.offset = offset;
/* 124 */       this.length = length;
/*     */     }
/*     */     public boolean hasNext() {
/* 127 */       return this.curr < this.length; } 
/* 128 */     public boolean hasPrevious() { return this.curr > 0; }
/*     */ 
/*     */     public boolean nextBoolean() {
/* 131 */       if (!hasNext()) throw new NoSuchElementException();
/* 132 */       return this.array[(this.offset + this.curr++)];
/*     */     }
/*     */ 
/*     */     public boolean previousBoolean() {
/* 136 */       if (!hasPrevious()) throw new NoSuchElementException();
/* 137 */       return this.array[(this.offset + --this.curr)];
/*     */     }
/*     */ 
/*     */     public int skip(int n) {
/* 141 */       if (n <= this.length - this.curr) {
/* 142 */         this.curr += n;
/* 143 */         return n;
/*     */       }
/* 145 */       n = this.length - this.curr;
/* 146 */       this.curr = this.length;
/* 147 */       return n;
/*     */     }
/*     */ 
/*     */     public int back(int n) {
/* 151 */       if (n <= this.curr) {
/* 152 */         this.curr -= n;
/* 153 */         return n;
/*     */       }
/* 155 */       n = this.curr;
/* 156 */       this.curr = 0;
/* 157 */       return n;
/*     */     }
/*     */ 
/*     */     public int nextIndex() {
/* 161 */       return this.curr;
/*     */     }
/*     */ 
/*     */     public int previousIndex() {
/* 165 */       return this.curr - 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class SingletonIterator extends AbstractBooleanListIterator
/*     */   {
/*     */     private final boolean element;
/*     */     private int curr;
/*     */ 
/*     */     public SingletonIterator(boolean element)
/*     */     {
/*  85 */       this.element = element;
/*     */     }
/*  87 */     public boolean hasNext() { return this.curr == 0; } 
/*  88 */     public boolean hasPrevious() { return this.curr == 1; } 
/*     */     public boolean nextBoolean() {
/*  90 */       if (!hasNext()) throw new NoSuchElementException();
/*  91 */       this.curr = 1;
/*  92 */       return this.element;
/*     */     }
/*     */     public boolean previousBoolean() {
/*  95 */       if (!hasPrevious()) throw new NoSuchElementException();
/*  96 */       this.curr = 0;
/*  97 */       return this.element;
/*     */     }
/*     */     public int nextIndex() {
/* 100 */       return this.curr;
/*     */     }
/*     */     public int previousIndex() {
/* 103 */       return this.curr - 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptyIterator extends AbstractBooleanListIterator
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/*  62 */       return false; } 
/*  63 */     public boolean hasPrevious() { return false; } 
/*  64 */     public boolean nextBoolean() { throw new NoSuchElementException(); } 
/*  65 */     public boolean previousBoolean() { throw new NoSuchElementException(); } 
/*  66 */     public int nextIndex() { return 0; } 
/*  67 */     public int previousIndex() { return -1; } 
/*  68 */     public int skip(int n) { return 0; } 
/*  69 */     public int back(int n) { return 0; } 
/*  70 */     public Object clone() { return BooleanIterators.EMPTY_ITERATOR; } 
/*  71 */     private Object readResolve() { return BooleanIterators.EMPTY_ITERATOR; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanIterators
 * JD-Core Version:    0.6.2
 */