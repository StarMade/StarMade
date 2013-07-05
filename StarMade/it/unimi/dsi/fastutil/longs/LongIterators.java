/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.bytes.ByteIterator;
/*     */ import it.unimi.dsi.fastutil.ints.IntIterator;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class LongIterators
/*     */ {
/*  79 */   public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
/*     */ 
/*     */   public static LongListIterator singleton(long element)
/*     */   {
/* 112 */     return new SingletonIterator(element);
/*     */   }
/*     */ 
/*     */   public static LongListIterator wrap(long[] array, int offset, int length)
/*     */   {
/* 181 */     LongArrays.ensureOffsetLength(array, offset, length);
/* 182 */     return new ArrayIterator(array, offset, length);
/*     */   }
/*     */ 
/*     */   public static LongListIterator wrap(long[] array)
/*     */   {
/* 193 */     return new ArrayIterator(array, 0, array.length);
/*     */   }
/*     */ 
/*     */   public static int unwrap(LongIterator i, long[] array, int offset, int max)
/*     */   {
/* 211 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 212 */     if ((offset < 0) || (offset + max > array.length)) throw new IllegalArgumentException();
/* 213 */     int j = max;
/* 214 */     while ((j-- != 0) && (i.hasNext())) array[(offset++)] = i.nextLong();
/* 215 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static int unwrap(LongIterator i, long[] array)
/*     */   {
/* 229 */     return unwrap(i, array, 0, array.length);
/*     */   }
/*     */ 
/*     */   public static long[] unwrap(LongIterator i, int max)
/*     */   {
/* 244 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 245 */     long[] array = new long[16];
/* 246 */     int j = 0;
/*     */ 
/* 248 */     while ((max-- != 0) && (i.hasNext())) {
/* 249 */       if (j == array.length) array = LongArrays.grow(array, j + 1);
/* 250 */       array[(j++)] = i.nextLong();
/*     */     }
/*     */ 
/* 253 */     return LongArrays.trim(array, j);
/*     */   }
/*     */ 
/*     */   public static long[] unwrap(LongIterator i)
/*     */   {
/* 267 */     return unwrap(i, 2147483647);
/*     */   }
/*     */ 
/*     */   public static int unwrap(LongIterator i, LongCollection c, int max)
/*     */   {
/* 286 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 287 */     int j = max;
/* 288 */     while ((j-- != 0) && (i.hasNext())) c.add(i.nextLong());
/* 289 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static long unwrap(LongIterator i, LongCollection c)
/*     */   {
/* 305 */     long n = 0L;
/* 306 */     while (i.hasNext()) {
/* 307 */       c.add(i.nextLong());
/* 308 */       n += 1L;
/*     */     }
/* 310 */     return n;
/*     */   }
/*     */ 
/*     */   public static int pour(LongIterator i, LongCollection s, int max)
/*     */   {
/* 328 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 329 */     int j = max;
/* 330 */     while ((j-- != 0) && (i.hasNext())) s.add(i.nextLong());
/* 331 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static int pour(LongIterator i, LongCollection s)
/*     */   {
/* 347 */     return pour(i, s, 2147483647);
/*     */   }
/*     */ 
/*     */   public static LongList pour(LongIterator i, int max)
/*     */   {
/* 364 */     LongArrayList l = new LongArrayList();
/* 365 */     pour(i, l, max);
/* 366 */     l.trim();
/* 367 */     return l;
/*     */   }
/*     */ 
/*     */   public static LongList pour(LongIterator i)
/*     */   {
/* 382 */     return pour(i, 2147483647);
/*     */   }
/*     */ 
/*     */   public static LongIterator asLongIterator(Iterator i)
/*     */   {
/* 414 */     if ((i instanceof LongIterator)) return (LongIterator)i;
/* 415 */     return new IteratorWrapper(i);
/*     */   }
/*     */ 
/*     */   public static LongListIterator asLongIterator(ListIterator i)
/*     */   {
/* 457 */     if ((i instanceof LongListIterator)) return (LongListIterator)i;
/* 458 */     return new ListIteratorWrapper(i);
/*     */   }
/*     */ 
/*     */   public static LongBidirectionalIterator fromTo(long from, long to)
/*     */   {
/* 539 */     return new IntervalIterator(from, to);
/*     */   }
/*     */ 
/*     */   public static LongIterator concat(LongIterator[] a)
/*     */   {
/* 592 */     return concat(a, 0, a.length);
/*     */   }
/*     */ 
/*     */   public static LongIterator concat(LongIterator[] a, int offset, int length)
/*     */   {
/* 607 */     return new IteratorConcatenator(a, offset, length);
/*     */   }
/*     */ 
/*     */   public static LongIterator unmodifiable(LongIterator i)
/*     */   {
/* 625 */     return new UnmodifiableIterator(i);
/*     */   }
/*     */ 
/*     */   public static LongBidirectionalIterator unmodifiable(LongBidirectionalIterator i)
/*     */   {
/* 645 */     return new UnmodifiableBidirectionalIterator(i);
/*     */   }
/*     */ 
/*     */   public static LongListIterator unmodifiable(LongListIterator i)
/*     */   {
/* 667 */     return new UnmodifiableListIterator(i);
/*     */   }
/*     */ 
/*     */   public static LongIterator wrap(ByteIterator iterator)
/*     */   {
/* 684 */     return new ByteIteratorWrapper(iterator);
/*     */   }
/*     */ 
/*     */   public static LongIterator wrap(ShortIterator iterator)
/*     */   {
/* 702 */     return new ShortIteratorWrapper(iterator);
/*     */   }
/*     */ 
/*     */   public static LongIterator wrap(IntIterator iterator)
/*     */   {
/* 720 */     return new IntIteratorWrapper(iterator);
/*     */   }
/*     */ 
/*     */   protected static class IntIteratorWrapper
/*     */     implements LongIterator
/*     */   {
/*     */     final IntIterator iterator;
/*     */ 
/*     */     public IntIteratorWrapper(IntIterator iterator)
/*     */     {
/* 708 */       this.iterator = iterator;
/*     */     }
/* 710 */     public boolean hasNext() { return this.iterator.hasNext(); } 
/* 711 */     public Long next() { return Long.valueOf(this.iterator.nextInt()); } 
/* 712 */     public long nextLong() { return this.iterator.nextInt(); } 
/* 713 */     public void remove() { this.iterator.remove(); } 
/* 714 */     public int skip(int n) { return this.iterator.skip(n); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class ShortIteratorWrapper
/*     */     implements LongIterator
/*     */   {
/*     */     final ShortIterator iterator;
/*     */ 
/*     */     public ShortIteratorWrapper(ShortIterator iterator)
/*     */     {
/* 690 */       this.iterator = iterator;
/*     */     }
/* 692 */     public boolean hasNext() { return this.iterator.hasNext(); } 
/* 693 */     public Long next() { return Long.valueOf(this.iterator.nextShort()); } 
/* 694 */     public long nextLong() { return this.iterator.nextShort(); } 
/* 695 */     public void remove() { this.iterator.remove(); } 
/* 696 */     public int skip(int n) { return this.iterator.skip(n); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class ByteIteratorWrapper
/*     */     implements LongIterator
/*     */   {
/*     */     final ByteIterator iterator;
/*     */ 
/*     */     public ByteIteratorWrapper(ByteIterator iterator)
/*     */     {
/* 672 */       this.iterator = iterator;
/*     */     }
/* 674 */     public boolean hasNext() { return this.iterator.hasNext(); } 
/* 675 */     public Long next() { return Long.valueOf(this.iterator.nextByte()); } 
/* 676 */     public long nextLong() { return this.iterator.nextByte(); } 
/* 677 */     public void remove() { this.iterator.remove(); } 
/* 678 */     public int skip(int n) { return this.iterator.skip(n); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableListIterator extends AbstractLongListIterator
/*     */   {
/*     */     protected final LongListIterator i;
/*     */ 
/*     */     public UnmodifiableListIterator(LongListIterator i)
/*     */     {
/* 651 */       this.i = i;
/*     */     }
/* 653 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 654 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 655 */     public long nextLong() { return this.i.nextLong(); } 
/* 656 */     public long previousLong() { return this.i.previousLong(); } 
/* 657 */     public int nextIndex() { return this.i.nextIndex(); } 
/* 658 */     public int previousIndex() { return this.i.previousIndex(); } 
/* 659 */     public Long next() { return (Long)this.i.next(); } 
/* 660 */     public Long previous() { return (Long)this.i.previous(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableBidirectionalIterator extends AbstractLongBidirectionalIterator
/*     */   {
/*     */     protected final LongBidirectionalIterator i;
/*     */ 
/*     */     public UnmodifiableBidirectionalIterator(LongBidirectionalIterator i)
/*     */     {
/* 631 */       this.i = i;
/*     */     }
/* 633 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 634 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 635 */     public long nextLong() { return this.i.nextLong(); } 
/* 636 */     public long previousLong() { return this.i.previousLong(); } 
/* 637 */     public Long next() { return (Long)this.i.next(); } 
/* 638 */     public Long previous() { return (Long)this.i.previous(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableIterator extends AbstractLongIterator
/*     */   {
/*     */     protected final LongIterator i;
/*     */ 
/*     */     public UnmodifiableIterator(LongIterator i)
/*     */     {
/* 614 */       this.i = i;
/*     */     }
/* 616 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 617 */     public long nextLong() { return this.i.nextLong(); } 
/* 618 */     public Long next() { return (Long)this.i.next(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class IteratorConcatenator extends AbstractLongIterator
/*     */   {
/*     */     final LongIterator[] a;
/*     */     int offset;
/*     */     int length;
/* 543 */     int lastOffset = -1;
/*     */ 
/* 545 */     public IteratorConcatenator(LongIterator[] a, int offset, int length) { this.a = a;
/* 546 */       this.offset = offset;
/* 547 */       this.length = length;
/* 548 */       advance(); }
/*     */ 
/*     */     private void advance() {
/* 551 */       while ((this.length != 0) && 
/* 552 */         (!this.a[this.offset].hasNext())) {
/* 553 */         this.length -= 1;
/* 554 */         this.offset += 1;
/*     */       }
/*     */     }
/*     */ 
/*     */     public boolean hasNext() {
/* 559 */       return this.length > 0;
/*     */     }
/*     */     public long nextLong() {
/* 562 */       if (!hasNext()) throw new NoSuchElementException();
/* 563 */       long next = this.a[(this.lastOffset = this.offset)].nextLong();
/* 564 */       advance();
/* 565 */       return next;
/*     */     }
/*     */     public void remove() {
/* 568 */       if (this.lastOffset == -1) throw new IllegalStateException();
/* 569 */       this.a[this.lastOffset].remove();
/*     */     }
/*     */     public int skip(int n) {
/* 572 */       this.lastOffset = -1;
/* 573 */       int skipped = 0;
/* 574 */       while ((skipped < n) && (this.length != 0)) {
/* 575 */         skipped += this.a[this.offset].skip(n - skipped);
/* 576 */         if (this.a[this.offset].hasNext()) break;
/* 577 */         this.length -= 1;
/* 578 */         this.offset += 1;
/*     */       }
/* 580 */       return skipped;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class IntervalIterator extends AbstractLongBidirectionalIterator
/*     */   {
/*     */     private final long from;
/*     */     private final long to;
/*     */     long curr;
/*     */ 
/*     */     public IntervalIterator(long from, long to)
/*     */     {
/* 473 */       this.from = (this.curr = from);
/* 474 */       this.to = to;
/*     */     }
/*     */     public boolean hasNext() {
/* 477 */       return this.curr < this.to; } 
/* 478 */     public boolean hasPrevious() { return this.curr > this.from; }
/*     */ 
/*     */     public long nextLong() {
/* 481 */       if (!hasNext()) throw new NoSuchElementException();
/* 482 */       return this.curr++;
/*     */     }
/*     */     public long previousLong() {
/* 485 */       if (!hasPrevious()) throw new NoSuchElementException();
/* 486 */       return --this.curr;
/*     */     }
/*     */ 
/*     */     public int skip(int n)
/*     */     {
/* 495 */       if (this.curr + n <= this.to) {
/* 496 */         this.curr += n;
/* 497 */         return n;
/*     */       }
/*     */ 
/* 502 */       n = (int)(this.to - this.curr);
/*     */ 
/* 504 */       this.curr = this.to;
/* 505 */       return n;
/*     */     }
/*     */ 
/*     */     public int back(int n) {
/* 509 */       if (this.curr - n >= this.from) {
/* 510 */         this.curr -= n;
/* 511 */         return n;
/*     */       }
/*     */ 
/* 516 */       n = (int)(this.curr - this.from);
/*     */ 
/* 518 */       this.curr = this.from;
/* 519 */       return n;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class ListIteratorWrapper extends AbstractLongListIterator
/*     */   {
/*     */     final ListIterator<Long> i;
/*     */ 
/*     */     public ListIteratorWrapper(ListIterator<Long> i)
/*     */     {
/* 423 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 426 */       return this.i.hasNext(); } 
/* 427 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 428 */     public int nextIndex() { return this.i.nextIndex(); } 
/* 429 */     public int previousIndex() { return this.i.previousIndex(); } 
/*     */     public void set(long k) {
/* 431 */       this.i.set(Long.valueOf(k));
/*     */     }
/* 433 */     public void add(long k) { this.i.add(Long.valueOf(k)); } 
/* 434 */     public void remove() { this.i.remove(); } 
/*     */     public long nextLong() {
/* 436 */       return ((Long)this.i.next()).longValue(); } 
/* 437 */     public long previousLong() { return ((Long)this.i.previous()).longValue(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class IteratorWrapper extends AbstractLongIterator
/*     */   {
/*     */     final Iterator<Long> i;
/*     */ 
/*     */     public IteratorWrapper(Iterator<Long> i)
/*     */     {
/* 389 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 392 */       return this.i.hasNext(); } 
/* 393 */     public void remove() { this.i.remove(); } 
/*     */     public long nextLong() {
/* 395 */       return ((Long)this.i.next()).longValue();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class ArrayIterator extends AbstractLongListIterator
/*     */   {
/*     */     private final long[] array;
/*     */     private final int offset;
/*     */     private final int length;
/*     */     private int curr;
/*     */ 
/*     */     public ArrayIterator(long[] array, int offset, int length)
/*     */     {
/* 122 */       this.array = array;
/* 123 */       this.offset = offset;
/* 124 */       this.length = length;
/*     */     }
/*     */     public boolean hasNext() {
/* 127 */       return this.curr < this.length; } 
/* 128 */     public boolean hasPrevious() { return this.curr > 0; }
/*     */ 
/*     */     public long nextLong() {
/* 131 */       if (!hasNext()) throw new NoSuchElementException();
/* 132 */       return this.array[(this.offset + this.curr++)];
/*     */     }
/*     */ 
/*     */     public long previousLong() {
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
/*     */   private static class SingletonIterator extends AbstractLongListIterator
/*     */   {
/*     */     private final long element;
/*     */     private int curr;
/*     */ 
/*     */     public SingletonIterator(long element)
/*     */     {
/*  85 */       this.element = element;
/*     */     }
/*  87 */     public boolean hasNext() { return this.curr == 0; } 
/*  88 */     public boolean hasPrevious() { return this.curr == 1; } 
/*     */     public long nextLong() {
/*  90 */       if (!hasNext()) throw new NoSuchElementException();
/*  91 */       this.curr = 1;
/*  92 */       return this.element;
/*     */     }
/*     */     public long previousLong() {
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
/*     */   public static class EmptyIterator extends AbstractLongListIterator
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/*  62 */       return false; } 
/*  63 */     public boolean hasPrevious() { return false; } 
/*  64 */     public long nextLong() { throw new NoSuchElementException(); } 
/*  65 */     public long previousLong() { throw new NoSuchElementException(); } 
/*  66 */     public int nextIndex() { return 0; } 
/*  67 */     public int previousIndex() { return -1; } 
/*  68 */     public int skip(int n) { return 0; } 
/*  69 */     public int back(int n) { return 0; } 
/*  70 */     public Object clone() { return LongIterators.EMPTY_ITERATOR; } 
/*  71 */     private Object readResolve() { return LongIterators.EMPTY_ITERATOR; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongIterators
 * JD-Core Version:    0.6.2
 */