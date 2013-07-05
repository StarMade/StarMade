/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.bytes.ByteIterator;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class IntIterators
/*     */ {
/*  79 */   public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
/*     */ 
/*     */   public static IntListIterator singleton(int element)
/*     */   {
/* 112 */     return new SingletonIterator(element);
/*     */   }
/*     */ 
/*     */   public static IntListIterator wrap(int[] array, int offset, int length)
/*     */   {
/* 181 */     IntArrays.ensureOffsetLength(array, offset, length);
/* 182 */     return new ArrayIterator(array, offset, length);
/*     */   }
/*     */ 
/*     */   public static IntListIterator wrap(int[] array)
/*     */   {
/* 193 */     return new ArrayIterator(array, 0, array.length);
/*     */   }
/*     */ 
/*     */   public static int unwrap(IntIterator i, int[] array, int offset, int max)
/*     */   {
/* 211 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 212 */     if ((offset < 0) || (offset + max > array.length)) throw new IllegalArgumentException();
/* 213 */     int j = max;
/* 214 */     while ((j-- != 0) && (i.hasNext())) array[(offset++)] = i.nextInt();
/* 215 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static int unwrap(IntIterator i, int[] array)
/*     */   {
/* 229 */     return unwrap(i, array, 0, array.length);
/*     */   }
/*     */ 
/*     */   public static int[] unwrap(IntIterator i, int max)
/*     */   {
/* 244 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 245 */     int[] array = new int[16];
/* 246 */     int j = 0;
/*     */ 
/* 248 */     while ((max-- != 0) && (i.hasNext())) {
/* 249 */       if (j == array.length) array = IntArrays.grow(array, j + 1);
/* 250 */       array[(j++)] = i.nextInt();
/*     */     }
/*     */ 
/* 253 */     return IntArrays.trim(array, j);
/*     */   }
/*     */ 
/*     */   public static int[] unwrap(IntIterator i)
/*     */   {
/* 267 */     return unwrap(i, 2147483647);
/*     */   }
/*     */ 
/*     */   public static int unwrap(IntIterator i, IntCollection c, int max)
/*     */   {
/* 286 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 287 */     int j = max;
/* 288 */     while ((j-- != 0) && (i.hasNext())) c.add(i.nextInt());
/* 289 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static long unwrap(IntIterator i, IntCollection c)
/*     */   {
/* 305 */     long n = 0L;
/* 306 */     while (i.hasNext()) {
/* 307 */       c.add(i.nextInt());
/* 308 */       n += 1L;
/*     */     }
/* 310 */     return n;
/*     */   }
/*     */ 
/*     */   public static int pour(IntIterator i, IntCollection s, int max)
/*     */   {
/* 328 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 329 */     int j = max;
/* 330 */     while ((j-- != 0) && (i.hasNext())) s.add(i.nextInt());
/* 331 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static int pour(IntIterator i, IntCollection s)
/*     */   {
/* 347 */     return pour(i, s, 2147483647);
/*     */   }
/*     */ 
/*     */   public static IntList pour(IntIterator i, int max)
/*     */   {
/* 364 */     IntArrayList l = new IntArrayList();
/* 365 */     pour(i, l, max);
/* 366 */     l.trim();
/* 367 */     return l;
/*     */   }
/*     */ 
/*     */   public static IntList pour(IntIterator i)
/*     */   {
/* 382 */     return pour(i, 2147483647);
/*     */   }
/*     */ 
/*     */   public static IntIterator asIntIterator(Iterator i)
/*     */   {
/* 414 */     if ((i instanceof IntIterator)) return (IntIterator)i;
/* 415 */     return new IteratorWrapper(i);
/*     */   }
/*     */ 
/*     */   public static IntListIterator asIntIterator(ListIterator i)
/*     */   {
/* 457 */     if ((i instanceof IntListIterator)) return (IntListIterator)i;
/* 458 */     return new ListIteratorWrapper(i);
/*     */   }
/*     */ 
/*     */   public static IntListIterator fromTo(int from, int to)
/*     */   {
/* 532 */     return new IntervalIterator(from, to);
/*     */   }
/*     */ 
/*     */   public static IntIterator concat(IntIterator[] a)
/*     */   {
/* 585 */     return concat(a, 0, a.length);
/*     */   }
/*     */ 
/*     */   public static IntIterator concat(IntIterator[] a, int offset, int length)
/*     */   {
/* 600 */     return new IteratorConcatenator(a, offset, length);
/*     */   }
/*     */ 
/*     */   public static IntIterator unmodifiable(IntIterator i)
/*     */   {
/* 618 */     return new UnmodifiableIterator(i);
/*     */   }
/*     */ 
/*     */   public static IntBidirectionalIterator unmodifiable(IntBidirectionalIterator i)
/*     */   {
/* 638 */     return new UnmodifiableBidirectionalIterator(i);
/*     */   }
/*     */ 
/*     */   public static IntListIterator unmodifiable(IntListIterator i)
/*     */   {
/* 660 */     return new UnmodifiableListIterator(i);
/*     */   }
/*     */ 
/*     */   public static IntIterator wrap(ByteIterator iterator)
/*     */   {
/* 677 */     return new ByteIteratorWrapper(iterator);
/*     */   }
/*     */ 
/*     */   public static IntIterator wrap(ShortIterator iterator)
/*     */   {
/* 695 */     return new ShortIteratorWrapper(iterator);
/*     */   }
/*     */ 
/*     */   protected static class ShortIteratorWrapper
/*     */     implements IntIterator
/*     */   {
/*     */     final ShortIterator iterator;
/*     */ 
/*     */     public ShortIteratorWrapper(ShortIterator iterator)
/*     */     {
/* 683 */       this.iterator = iterator;
/*     */     }
/* 685 */     public boolean hasNext() { return this.iterator.hasNext(); } 
/* 686 */     public Integer next() { return Integer.valueOf(this.iterator.nextShort()); } 
/* 687 */     public int nextInt() { return this.iterator.nextShort(); } 
/* 688 */     public void remove() { this.iterator.remove(); } 
/* 689 */     public int skip(int n) { return this.iterator.skip(n); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class ByteIteratorWrapper
/*     */     implements IntIterator
/*     */   {
/*     */     final ByteIterator iterator;
/*     */ 
/*     */     public ByteIteratorWrapper(ByteIterator iterator)
/*     */     {
/* 665 */       this.iterator = iterator;
/*     */     }
/* 667 */     public boolean hasNext() { return this.iterator.hasNext(); } 
/* 668 */     public Integer next() { return Integer.valueOf(this.iterator.nextByte()); } 
/* 669 */     public int nextInt() { return this.iterator.nextByte(); } 
/* 670 */     public void remove() { this.iterator.remove(); } 
/* 671 */     public int skip(int n) { return this.iterator.skip(n); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableListIterator extends AbstractIntListIterator
/*     */   {
/*     */     protected final IntListIterator i;
/*     */ 
/*     */     public UnmodifiableListIterator(IntListIterator i)
/*     */     {
/* 644 */       this.i = i;
/*     */     }
/* 646 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 647 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 648 */     public int nextInt() { return this.i.nextInt(); } 
/* 649 */     public int previousInt() { return this.i.previousInt(); } 
/* 650 */     public int nextIndex() { return this.i.nextIndex(); } 
/* 651 */     public int previousIndex() { return this.i.previousIndex(); } 
/* 652 */     public Integer next() { return (Integer)this.i.next(); } 
/* 653 */     public Integer previous() { return (Integer)this.i.previous(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableBidirectionalIterator extends AbstractIntBidirectionalIterator
/*     */   {
/*     */     protected final IntBidirectionalIterator i;
/*     */ 
/*     */     public UnmodifiableBidirectionalIterator(IntBidirectionalIterator i)
/*     */     {
/* 624 */       this.i = i;
/*     */     }
/* 626 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 627 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 628 */     public int nextInt() { return this.i.nextInt(); } 
/* 629 */     public int previousInt() { return this.i.previousInt(); } 
/* 630 */     public Integer next() { return (Integer)this.i.next(); } 
/* 631 */     public Integer previous() { return (Integer)this.i.previous(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableIterator extends AbstractIntIterator
/*     */   {
/*     */     protected final IntIterator i;
/*     */ 
/*     */     public UnmodifiableIterator(IntIterator i)
/*     */     {
/* 607 */       this.i = i;
/*     */     }
/* 609 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 610 */     public int nextInt() { return this.i.nextInt(); } 
/* 611 */     public Integer next() { return (Integer)this.i.next(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class IteratorConcatenator extends AbstractIntIterator
/*     */   {
/*     */     final IntIterator[] a;
/*     */     int offset;
/*     */     int length;
/* 536 */     int lastOffset = -1;
/*     */ 
/* 538 */     public IteratorConcatenator(IntIterator[] a, int offset, int length) { this.a = a;
/* 539 */       this.offset = offset;
/* 540 */       this.length = length;
/* 541 */       advance(); }
/*     */ 
/*     */     private void advance() {
/* 544 */       while ((this.length != 0) && 
/* 545 */         (!this.a[this.offset].hasNext())) {
/* 546 */         this.length -= 1;
/* 547 */         this.offset += 1;
/*     */       }
/*     */     }
/*     */ 
/*     */     public boolean hasNext() {
/* 552 */       return this.length > 0;
/*     */     }
/*     */     public int nextInt() {
/* 555 */       if (!hasNext()) throw new NoSuchElementException();
/* 556 */       int next = this.a[(this.lastOffset = this.offset)].nextInt();
/* 557 */       advance();
/* 558 */       return next;
/*     */     }
/*     */     public void remove() {
/* 561 */       if (this.lastOffset == -1) throw new IllegalStateException();
/* 562 */       this.a[this.lastOffset].remove();
/*     */     }
/*     */     public int skip(int n) {
/* 565 */       this.lastOffset = -1;
/* 566 */       int skipped = 0;
/* 567 */       while ((skipped < n) && (this.length != 0)) {
/* 568 */         skipped += this.a[this.offset].skip(n - skipped);
/* 569 */         if (this.a[this.offset].hasNext()) break;
/* 570 */         this.length -= 1;
/* 571 */         this.offset += 1;
/*     */       }
/* 573 */       return skipped;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class IntervalIterator extends AbstractIntListIterator
/*     */   {
/*     */     private final int from;
/*     */     private final int to;
/*     */     int curr;
/*     */ 
/*     */     public IntervalIterator(int from, int to)
/*     */     {
/* 473 */       this.from = (this.curr = from);
/* 474 */       this.to = to;
/*     */     }
/*     */     public boolean hasNext() {
/* 477 */       return this.curr < this.to; } 
/* 478 */     public boolean hasPrevious() { return this.curr > this.from; }
/*     */ 
/*     */     public int nextInt() {
/* 481 */       if (!hasNext()) throw new NoSuchElementException();
/* 482 */       return this.curr++;
/*     */     }
/*     */     public int previousInt() {
/* 485 */       if (!hasPrevious()) throw new NoSuchElementException();
/* 486 */       return --this.curr;
/*     */     }
/*     */ 
/*     */     public int nextIndex() {
/* 490 */       return this.curr - this.from; } 
/* 491 */     public int previousIndex() { return this.curr - this.from - 1; }
/*     */ 
/*     */     public int skip(int n)
/*     */     {
/* 495 */       if (this.curr + n <= this.to) {
/* 496 */         this.curr += n;
/* 497 */         return n;
/*     */       }
/*     */ 
/* 500 */       n = this.to - this.curr;
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
/* 514 */       n = this.curr - this.from;
/*     */ 
/* 518 */       this.curr = this.from;
/* 519 */       return n;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class ListIteratorWrapper extends AbstractIntListIterator
/*     */   {
/*     */     final ListIterator<Integer> i;
/*     */ 
/*     */     public ListIteratorWrapper(ListIterator<Integer> i)
/*     */     {
/* 423 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 426 */       return this.i.hasNext(); } 
/* 427 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 428 */     public int nextIndex() { return this.i.nextIndex(); } 
/* 429 */     public int previousIndex() { return this.i.previousIndex(); } 
/*     */     public void set(int k) {
/* 431 */       this.i.set(Integer.valueOf(k));
/*     */     }
/* 433 */     public void add(int k) { this.i.add(Integer.valueOf(k)); } 
/* 434 */     public void remove() { this.i.remove(); } 
/*     */     public int nextInt() {
/* 436 */       return ((Integer)this.i.next()).intValue(); } 
/* 437 */     public int previousInt() { return ((Integer)this.i.previous()).intValue(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class IteratorWrapper extends AbstractIntIterator
/*     */   {
/*     */     final Iterator<Integer> i;
/*     */ 
/*     */     public IteratorWrapper(Iterator<Integer> i)
/*     */     {
/* 389 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 392 */       return this.i.hasNext(); } 
/* 393 */     public void remove() { this.i.remove(); } 
/*     */     public int nextInt() {
/* 395 */       return ((Integer)this.i.next()).intValue();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class ArrayIterator extends AbstractIntListIterator
/*     */   {
/*     */     private final int[] array;
/*     */     private final int offset;
/*     */     private final int length;
/*     */     private int curr;
/*     */ 
/*     */     public ArrayIterator(int[] array, int offset, int length)
/*     */     {
/* 122 */       this.array = array;
/* 123 */       this.offset = offset;
/* 124 */       this.length = length;
/*     */     }
/*     */     public boolean hasNext() {
/* 127 */       return this.curr < this.length; } 
/* 128 */     public boolean hasPrevious() { return this.curr > 0; }
/*     */ 
/*     */     public int nextInt() {
/* 131 */       if (!hasNext()) throw new NoSuchElementException();
/* 132 */       return this.array[(this.offset + this.curr++)];
/*     */     }
/*     */ 
/*     */     public int previousInt() {
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
/*     */   private static class SingletonIterator extends AbstractIntListIterator
/*     */   {
/*     */     private final int element;
/*     */     private int curr;
/*     */ 
/*     */     public SingletonIterator(int element)
/*     */     {
/*  85 */       this.element = element;
/*     */     }
/*  87 */     public boolean hasNext() { return this.curr == 0; } 
/*  88 */     public boolean hasPrevious() { return this.curr == 1; } 
/*     */     public int nextInt() {
/*  90 */       if (!hasNext()) throw new NoSuchElementException();
/*  91 */       this.curr = 1;
/*  92 */       return this.element;
/*     */     }
/*     */     public int previousInt() {
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
/*     */   public static class EmptyIterator extends AbstractIntListIterator
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/*  62 */       return false; } 
/*  63 */     public boolean hasPrevious() { return false; } 
/*  64 */     public int nextInt() { throw new NoSuchElementException(); } 
/*  65 */     public int previousInt() { throw new NoSuchElementException(); } 
/*  66 */     public int nextIndex() { return 0; } 
/*  67 */     public int previousIndex() { return -1; } 
/*  68 */     public int skip(int n) { return 0; } 
/*  69 */     public int back(int n) { return 0; } 
/*  70 */     public Object clone() { return IntIterators.EMPTY_ITERATOR; } 
/*  71 */     private Object readResolve() { return IntIterators.EMPTY_ITERATOR; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntIterators
 * JD-Core Version:    0.6.2
 */