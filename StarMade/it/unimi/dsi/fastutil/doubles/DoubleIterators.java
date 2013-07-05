/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.bytes.ByteIterator;
/*     */ import it.unimi.dsi.fastutil.floats.FloatIterator;
/*     */ import it.unimi.dsi.fastutil.ints.IntIterator;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class DoubleIterators
/*     */ {
/*  79 */   public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
/*     */ 
/*     */   public static DoubleListIterator singleton(double element)
/*     */   {
/* 112 */     return new SingletonIterator(element);
/*     */   }
/*     */ 
/*     */   public static DoubleListIterator wrap(double[] array, int offset, int length)
/*     */   {
/* 181 */     DoubleArrays.ensureOffsetLength(array, offset, length);
/* 182 */     return new ArrayIterator(array, offset, length);
/*     */   }
/*     */ 
/*     */   public static DoubleListIterator wrap(double[] array)
/*     */   {
/* 193 */     return new ArrayIterator(array, 0, array.length);
/*     */   }
/*     */ 
/*     */   public static int unwrap(DoubleIterator i, double[] array, int offset, int max)
/*     */   {
/* 211 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 212 */     if ((offset < 0) || (offset + max > array.length)) throw new IllegalArgumentException();
/* 213 */     int j = max;
/* 214 */     while ((j-- != 0) && (i.hasNext())) array[(offset++)] = i.nextDouble();
/* 215 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static int unwrap(DoubleIterator i, double[] array)
/*     */   {
/* 229 */     return unwrap(i, array, 0, array.length);
/*     */   }
/*     */ 
/*     */   public static double[] unwrap(DoubleIterator i, int max)
/*     */   {
/* 244 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 245 */     double[] array = new double[16];
/* 246 */     int j = 0;
/*     */ 
/* 248 */     while ((max-- != 0) && (i.hasNext())) {
/* 249 */       if (j == array.length) array = DoubleArrays.grow(array, j + 1);
/* 250 */       array[(j++)] = i.nextDouble();
/*     */     }
/*     */ 
/* 253 */     return DoubleArrays.trim(array, j);
/*     */   }
/*     */ 
/*     */   public static double[] unwrap(DoubleIterator i)
/*     */   {
/* 267 */     return unwrap(i, 2147483647);
/*     */   }
/*     */ 
/*     */   public static int unwrap(DoubleIterator i, DoubleCollection c, int max)
/*     */   {
/* 286 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 287 */     int j = max;
/* 288 */     while ((j-- != 0) && (i.hasNext())) c.add(i.nextDouble());
/* 289 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static long unwrap(DoubleIterator i, DoubleCollection c)
/*     */   {
/* 305 */     long n = 0L;
/* 306 */     while (i.hasNext()) {
/* 307 */       c.add(i.nextDouble());
/* 308 */       n += 1L;
/*     */     }
/* 310 */     return n;
/*     */   }
/*     */ 
/*     */   public static int pour(DoubleIterator i, DoubleCollection s, int max)
/*     */   {
/* 328 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 329 */     int j = max;
/* 330 */     while ((j-- != 0) && (i.hasNext())) s.add(i.nextDouble());
/* 331 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static int pour(DoubleIterator i, DoubleCollection s)
/*     */   {
/* 347 */     return pour(i, s, 2147483647);
/*     */   }
/*     */ 
/*     */   public static DoubleList pour(DoubleIterator i, int max)
/*     */   {
/* 364 */     DoubleArrayList l = new DoubleArrayList();
/* 365 */     pour(i, l, max);
/* 366 */     l.trim();
/* 367 */     return l;
/*     */   }
/*     */ 
/*     */   public static DoubleList pour(DoubleIterator i)
/*     */   {
/* 382 */     return pour(i, 2147483647);
/*     */   }
/*     */ 
/*     */   public static DoubleIterator asDoubleIterator(Iterator i)
/*     */   {
/* 414 */     if ((i instanceof DoubleIterator)) return (DoubleIterator)i;
/* 415 */     return new IteratorWrapper(i);
/*     */   }
/*     */ 
/*     */   public static DoubleListIterator asDoubleIterator(ListIterator i)
/*     */   {
/* 457 */     if ((i instanceof DoubleListIterator)) return (DoubleListIterator)i;
/* 458 */     return new ListIteratorWrapper(i);
/*     */   }
/*     */ 
/*     */   public static DoubleIterator concat(DoubleIterator[] a)
/*     */   {
/* 511 */     return concat(a, 0, a.length);
/*     */   }
/*     */ 
/*     */   public static DoubleIterator concat(DoubleIterator[] a, int offset, int length)
/*     */   {
/* 526 */     return new IteratorConcatenator(a, offset, length);
/*     */   }
/*     */ 
/*     */   public static DoubleIterator unmodifiable(DoubleIterator i)
/*     */   {
/* 544 */     return new UnmodifiableIterator(i);
/*     */   }
/*     */ 
/*     */   public static DoubleBidirectionalIterator unmodifiable(DoubleBidirectionalIterator i)
/*     */   {
/* 564 */     return new UnmodifiableBidirectionalIterator(i);
/*     */   }
/*     */ 
/*     */   public static DoubleListIterator unmodifiable(DoubleListIterator i)
/*     */   {
/* 586 */     return new UnmodifiableListIterator(i);
/*     */   }
/*     */ 
/*     */   public static DoubleIterator wrap(ByteIterator iterator)
/*     */   {
/* 603 */     return new ByteIteratorWrapper(iterator);
/*     */   }
/*     */ 
/*     */   public static DoubleIterator wrap(ShortIterator iterator)
/*     */   {
/* 621 */     return new ShortIteratorWrapper(iterator);
/*     */   }
/*     */ 
/*     */   public static DoubleIterator wrap(IntIterator iterator)
/*     */   {
/* 639 */     return new IntIteratorWrapper(iterator);
/*     */   }
/*     */ 
/*     */   public static DoubleIterator wrap(FloatIterator iterator)
/*     */   {
/* 657 */     return new FloatIteratorWrapper(iterator);
/*     */   }
/*     */ 
/*     */   protected static class FloatIteratorWrapper
/*     */     implements DoubleIterator
/*     */   {
/*     */     final FloatIterator iterator;
/*     */ 
/*     */     public FloatIteratorWrapper(FloatIterator iterator)
/*     */     {
/* 645 */       this.iterator = iterator;
/*     */     }
/* 647 */     public boolean hasNext() { return this.iterator.hasNext(); } 
/* 648 */     public Double next() { return Double.valueOf(this.iterator.nextFloat()); } 
/* 649 */     public double nextDouble() { return this.iterator.nextFloat(); } 
/* 650 */     public void remove() { this.iterator.remove(); } 
/* 651 */     public int skip(int n) { return this.iterator.skip(n); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class IntIteratorWrapper
/*     */     implements DoubleIterator
/*     */   {
/*     */     final IntIterator iterator;
/*     */ 
/*     */     public IntIteratorWrapper(IntIterator iterator)
/*     */     {
/* 627 */       this.iterator = iterator;
/*     */     }
/* 629 */     public boolean hasNext() { return this.iterator.hasNext(); } 
/* 630 */     public Double next() { return Double.valueOf(this.iterator.nextInt()); } 
/* 631 */     public double nextDouble() { return this.iterator.nextInt(); } 
/* 632 */     public void remove() { this.iterator.remove(); } 
/* 633 */     public int skip(int n) { return this.iterator.skip(n); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class ShortIteratorWrapper
/*     */     implements DoubleIterator
/*     */   {
/*     */     final ShortIterator iterator;
/*     */ 
/*     */     public ShortIteratorWrapper(ShortIterator iterator)
/*     */     {
/* 609 */       this.iterator = iterator;
/*     */     }
/* 611 */     public boolean hasNext() { return this.iterator.hasNext(); } 
/* 612 */     public Double next() { return Double.valueOf(this.iterator.nextShort()); } 
/* 613 */     public double nextDouble() { return this.iterator.nextShort(); } 
/* 614 */     public void remove() { this.iterator.remove(); } 
/* 615 */     public int skip(int n) { return this.iterator.skip(n); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class ByteIteratorWrapper
/*     */     implements DoubleIterator
/*     */   {
/*     */     final ByteIterator iterator;
/*     */ 
/*     */     public ByteIteratorWrapper(ByteIterator iterator)
/*     */     {
/* 591 */       this.iterator = iterator;
/*     */     }
/* 593 */     public boolean hasNext() { return this.iterator.hasNext(); } 
/* 594 */     public Double next() { return Double.valueOf(this.iterator.nextByte()); } 
/* 595 */     public double nextDouble() { return this.iterator.nextByte(); } 
/* 596 */     public void remove() { this.iterator.remove(); } 
/* 597 */     public int skip(int n) { return this.iterator.skip(n); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableListIterator extends AbstractDoubleListIterator
/*     */   {
/*     */     protected final DoubleListIterator i;
/*     */ 
/*     */     public UnmodifiableListIterator(DoubleListIterator i)
/*     */     {
/* 570 */       this.i = i;
/*     */     }
/* 572 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 573 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 574 */     public double nextDouble() { return this.i.nextDouble(); } 
/* 575 */     public double previousDouble() { return this.i.previousDouble(); } 
/* 576 */     public int nextIndex() { return this.i.nextIndex(); } 
/* 577 */     public int previousIndex() { return this.i.previousIndex(); } 
/* 578 */     public Double next() { return (Double)this.i.next(); } 
/* 579 */     public Double previous() { return (Double)this.i.previous(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableBidirectionalIterator extends AbstractDoubleBidirectionalIterator
/*     */   {
/*     */     protected final DoubleBidirectionalIterator i;
/*     */ 
/*     */     public UnmodifiableBidirectionalIterator(DoubleBidirectionalIterator i)
/*     */     {
/* 550 */       this.i = i;
/*     */     }
/* 552 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 553 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 554 */     public double nextDouble() { return this.i.nextDouble(); } 
/* 555 */     public double previousDouble() { return this.i.previousDouble(); } 
/* 556 */     public Double next() { return (Double)this.i.next(); } 
/* 557 */     public Double previous() { return (Double)this.i.previous(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableIterator extends AbstractDoubleIterator
/*     */   {
/*     */     protected final DoubleIterator i;
/*     */ 
/*     */     public UnmodifiableIterator(DoubleIterator i)
/*     */     {
/* 533 */       this.i = i;
/*     */     }
/* 535 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 536 */     public double nextDouble() { return this.i.nextDouble(); } 
/* 537 */     public Double next() { return (Double)this.i.next(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class IteratorConcatenator extends AbstractDoubleIterator
/*     */   {
/*     */     final DoubleIterator[] a;
/*     */     int offset;
/*     */     int length;
/* 462 */     int lastOffset = -1;
/*     */ 
/* 464 */     public IteratorConcatenator(DoubleIterator[] a, int offset, int length) { this.a = a;
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
/*     */     public double nextDouble() {
/* 481 */       if (!hasNext()) throw new NoSuchElementException();
/* 482 */       double next = this.a[(this.lastOffset = this.offset)].nextDouble();
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
/*     */   private static class ListIteratorWrapper extends AbstractDoubleListIterator
/*     */   {
/*     */     final ListIterator<Double> i;
/*     */ 
/*     */     public ListIteratorWrapper(ListIterator<Double> i)
/*     */     {
/* 423 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 426 */       return this.i.hasNext(); } 
/* 427 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 428 */     public int nextIndex() { return this.i.nextIndex(); } 
/* 429 */     public int previousIndex() { return this.i.previousIndex(); } 
/*     */     public void set(double k) {
/* 431 */       this.i.set(Double.valueOf(k));
/*     */     }
/* 433 */     public void add(double k) { this.i.add(Double.valueOf(k)); } 
/* 434 */     public void remove() { this.i.remove(); } 
/*     */     public double nextDouble() {
/* 436 */       return ((Double)this.i.next()).doubleValue(); } 
/* 437 */     public double previousDouble() { return ((Double)this.i.previous()).doubleValue(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class IteratorWrapper extends AbstractDoubleIterator
/*     */   {
/*     */     final Iterator<Double> i;
/*     */ 
/*     */     public IteratorWrapper(Iterator<Double> i)
/*     */     {
/* 389 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 392 */       return this.i.hasNext(); } 
/* 393 */     public void remove() { this.i.remove(); } 
/*     */     public double nextDouble() {
/* 395 */       return ((Double)this.i.next()).doubleValue();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class ArrayIterator extends AbstractDoubleListIterator
/*     */   {
/*     */     private final double[] array;
/*     */     private final int offset;
/*     */     private final int length;
/*     */     private int curr;
/*     */ 
/*     */     public ArrayIterator(double[] array, int offset, int length)
/*     */     {
/* 122 */       this.array = array;
/* 123 */       this.offset = offset;
/* 124 */       this.length = length;
/*     */     }
/*     */     public boolean hasNext() {
/* 127 */       return this.curr < this.length; } 
/* 128 */     public boolean hasPrevious() { return this.curr > 0; }
/*     */ 
/*     */     public double nextDouble() {
/* 131 */       if (!hasNext()) throw new NoSuchElementException();
/* 132 */       return this.array[(this.offset + this.curr++)];
/*     */     }
/*     */ 
/*     */     public double previousDouble() {
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
/*     */   private static class SingletonIterator extends AbstractDoubleListIterator
/*     */   {
/*     */     private final double element;
/*     */     private int curr;
/*     */ 
/*     */     public SingletonIterator(double element)
/*     */     {
/*  85 */       this.element = element;
/*     */     }
/*  87 */     public boolean hasNext() { return this.curr == 0; } 
/*  88 */     public boolean hasPrevious() { return this.curr == 1; } 
/*     */     public double nextDouble() {
/*  90 */       if (!hasNext()) throw new NoSuchElementException();
/*  91 */       this.curr = 1;
/*  92 */       return this.element;
/*     */     }
/*     */     public double previousDouble() {
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
/*     */   public static class EmptyIterator extends AbstractDoubleListIterator
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/*  62 */       return false; } 
/*  63 */     public boolean hasPrevious() { return false; } 
/*  64 */     public double nextDouble() { throw new NoSuchElementException(); } 
/*  65 */     public double previousDouble() { throw new NoSuchElementException(); } 
/*  66 */     public int nextIndex() { return 0; } 
/*  67 */     public int previousIndex() { return -1; } 
/*  68 */     public int skip(int n) { return 0; } 
/*  69 */     public int back(int n) { return 0; } 
/*  70 */     public Object clone() { return DoubleIterators.EMPTY_ITERATOR; } 
/*  71 */     private Object readResolve() { return DoubleIterators.EMPTY_ITERATOR; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleIterators
 * JD-Core Version:    0.6.2
 */