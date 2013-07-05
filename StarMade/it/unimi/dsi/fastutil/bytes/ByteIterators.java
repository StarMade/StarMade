/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ByteIterators
/*     */ {
/*  79 */   public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
/*     */ 
/*     */   public static ByteListIterator singleton(byte element)
/*     */   {
/* 112 */     return new SingletonIterator(element);
/*     */   }
/*     */ 
/*     */   public static ByteListIterator wrap(byte[] array, int offset, int length)
/*     */   {
/* 181 */     ByteArrays.ensureOffsetLength(array, offset, length);
/* 182 */     return new ArrayIterator(array, offset, length);
/*     */   }
/*     */ 
/*     */   public static ByteListIterator wrap(byte[] array)
/*     */   {
/* 193 */     return new ArrayIterator(array, 0, array.length);
/*     */   }
/*     */ 
/*     */   public static int unwrap(ByteIterator i, byte[] array, int offset, int max)
/*     */   {
/* 211 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 212 */     if ((offset < 0) || (offset + max > array.length)) throw new IllegalArgumentException();
/* 213 */     int j = max;
/* 214 */     while ((j-- != 0) && (i.hasNext())) array[(offset++)] = i.nextByte();
/* 215 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static int unwrap(ByteIterator i, byte[] array)
/*     */   {
/* 229 */     return unwrap(i, array, 0, array.length);
/*     */   }
/*     */ 
/*     */   public static byte[] unwrap(ByteIterator i, int max)
/*     */   {
/* 244 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 245 */     byte[] array = new byte[16];
/* 246 */     int j = 0;
/*     */ 
/* 248 */     while ((max-- != 0) && (i.hasNext())) {
/* 249 */       if (j == array.length) array = ByteArrays.grow(array, j + 1);
/* 250 */       array[(j++)] = i.nextByte();
/*     */     }
/*     */ 
/* 253 */     return ByteArrays.trim(array, j);
/*     */   }
/*     */ 
/*     */   public static byte[] unwrap(ByteIterator i)
/*     */   {
/* 267 */     return unwrap(i, 2147483647);
/*     */   }
/*     */ 
/*     */   public static int unwrap(ByteIterator i, ByteCollection c, int max)
/*     */   {
/* 286 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 287 */     int j = max;
/* 288 */     while ((j-- != 0) && (i.hasNext())) c.add(i.nextByte());
/* 289 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static long unwrap(ByteIterator i, ByteCollection c)
/*     */   {
/* 305 */     long n = 0L;
/* 306 */     while (i.hasNext()) {
/* 307 */       c.add(i.nextByte());
/* 308 */       n += 1L;
/*     */     }
/* 310 */     return n;
/*     */   }
/*     */ 
/*     */   public static int pour(ByteIterator i, ByteCollection s, int max)
/*     */   {
/* 328 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 329 */     int j = max;
/* 330 */     while ((j-- != 0) && (i.hasNext())) s.add(i.nextByte());
/* 331 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static int pour(ByteIterator i, ByteCollection s)
/*     */   {
/* 347 */     return pour(i, s, 2147483647);
/*     */   }
/*     */ 
/*     */   public static ByteList pour(ByteIterator i, int max)
/*     */   {
/* 364 */     ByteArrayList l = new ByteArrayList();
/* 365 */     pour(i, l, max);
/* 366 */     l.trim();
/* 367 */     return l;
/*     */   }
/*     */ 
/*     */   public static ByteList pour(ByteIterator i)
/*     */   {
/* 382 */     return pour(i, 2147483647);
/*     */   }
/*     */ 
/*     */   public static ByteIterator asByteIterator(Iterator i)
/*     */   {
/* 414 */     if ((i instanceof ByteIterator)) return (ByteIterator)i;
/* 415 */     return new IteratorWrapper(i);
/*     */   }
/*     */ 
/*     */   public static ByteListIterator asByteIterator(ListIterator i)
/*     */   {
/* 457 */     if ((i instanceof ByteListIterator)) return (ByteListIterator)i;
/* 458 */     return new ListIteratorWrapper(i);
/*     */   }
/*     */ 
/*     */   public static ByteListIterator fromTo(byte from, byte to)
/*     */   {
/* 532 */     return new IntervalIterator(from, to);
/*     */   }
/*     */ 
/*     */   public static ByteIterator concat(ByteIterator[] a)
/*     */   {
/* 585 */     return concat(a, 0, a.length);
/*     */   }
/*     */ 
/*     */   public static ByteIterator concat(ByteIterator[] a, int offset, int length)
/*     */   {
/* 600 */     return new IteratorConcatenator(a, offset, length);
/*     */   }
/*     */ 
/*     */   public static ByteIterator unmodifiable(ByteIterator i)
/*     */   {
/* 618 */     return new UnmodifiableIterator(i);
/*     */   }
/*     */ 
/*     */   public static ByteBidirectionalIterator unmodifiable(ByteBidirectionalIterator i)
/*     */   {
/* 638 */     return new UnmodifiableBidirectionalIterator(i);
/*     */   }
/*     */ 
/*     */   public static ByteListIterator unmodifiable(ByteListIterator i)
/*     */   {
/* 660 */     return new UnmodifiableListIterator(i);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableListIterator extends AbstractByteListIterator
/*     */   {
/*     */     protected final ByteListIterator i;
/*     */ 
/*     */     public UnmodifiableListIterator(ByteListIterator i)
/*     */     {
/* 644 */       this.i = i;
/*     */     }
/* 646 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 647 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 648 */     public byte nextByte() { return this.i.nextByte(); } 
/* 649 */     public byte previousByte() { return this.i.previousByte(); } 
/* 650 */     public int nextIndex() { return this.i.nextIndex(); } 
/* 651 */     public int previousIndex() { return this.i.previousIndex(); } 
/* 652 */     public Byte next() { return (Byte)this.i.next(); } 
/* 653 */     public Byte previous() { return (Byte)this.i.previous(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableBidirectionalIterator extends AbstractByteBidirectionalIterator
/*     */   {
/*     */     protected final ByteBidirectionalIterator i;
/*     */ 
/*     */     public UnmodifiableBidirectionalIterator(ByteBidirectionalIterator i)
/*     */     {
/* 624 */       this.i = i;
/*     */     }
/* 626 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 627 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 628 */     public byte nextByte() { return this.i.nextByte(); } 
/* 629 */     public byte previousByte() { return this.i.previousByte(); } 
/* 630 */     public Byte next() { return (Byte)this.i.next(); } 
/* 631 */     public Byte previous() { return (Byte)this.i.previous(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableIterator extends AbstractByteIterator
/*     */   {
/*     */     protected final ByteIterator i;
/*     */ 
/*     */     public UnmodifiableIterator(ByteIterator i)
/*     */     {
/* 607 */       this.i = i;
/*     */     }
/* 609 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 610 */     public byte nextByte() { return this.i.nextByte(); } 
/* 611 */     public Byte next() { return (Byte)this.i.next(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class IteratorConcatenator extends AbstractByteIterator
/*     */   {
/*     */     final ByteIterator[] a;
/*     */     int offset;
/*     */     int length;
/* 536 */     int lastOffset = -1;
/*     */ 
/* 538 */     public IteratorConcatenator(ByteIterator[] a, int offset, int length) { this.a = a;
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
/*     */     public byte nextByte() {
/* 555 */       if (!hasNext()) throw new NoSuchElementException();
/* 556 */       byte next = this.a[(this.lastOffset = this.offset)].nextByte();
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
/*     */   private static class IntervalIterator extends AbstractByteListIterator
/*     */   {
/*     */     private final byte from;
/*     */     private final byte to;
/*     */     byte curr;
/*     */ 
/*     */     public IntervalIterator(byte from, byte to)
/*     */     {
/* 473 */       this.from = (this.curr = from);
/* 474 */       this.to = to;
/*     */     }
/*     */     public boolean hasNext() {
/* 477 */       return this.curr < this.to; } 
/* 478 */     public boolean hasPrevious() { return this.curr > this.from; }
/*     */ 
/*     */     public byte nextByte() {
/* 481 */       if (!hasNext()) throw new NoSuchElementException();
/* 482 */       return this.curr++;
/*     */     }
/*     */     public byte previousByte() {
/* 485 */       if (!hasPrevious()) throw new NoSuchElementException();
/* 486 */       return this.curr = (byte)(this.curr - 1);
/*     */     }
/*     */ 
/*     */     public int nextIndex() {
/* 490 */       return this.curr - this.from; } 
/* 491 */     public int previousIndex() { return this.curr - this.from - 1; }
/*     */ 
/*     */     public int skip(int n)
/*     */     {
/* 495 */       if (this.curr + n <= this.to) {
/* 496 */         this.curr = ((byte)(this.curr + n));
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
/* 510 */         this.curr = ((byte)(this.curr - n));
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
/*     */   private static class ListIteratorWrapper extends AbstractByteListIterator
/*     */   {
/*     */     final ListIterator<Byte> i;
/*     */ 
/*     */     public ListIteratorWrapper(ListIterator<Byte> i)
/*     */     {
/* 423 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 426 */       return this.i.hasNext(); } 
/* 427 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 428 */     public int nextIndex() { return this.i.nextIndex(); } 
/* 429 */     public int previousIndex() { return this.i.previousIndex(); } 
/*     */     public void set(byte k) {
/* 431 */       this.i.set(Byte.valueOf(k));
/*     */     }
/* 433 */     public void add(byte k) { this.i.add(Byte.valueOf(k)); } 
/* 434 */     public void remove() { this.i.remove(); } 
/*     */     public byte nextByte() {
/* 436 */       return ((Byte)this.i.next()).byteValue(); } 
/* 437 */     public byte previousByte() { return ((Byte)this.i.previous()).byteValue(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class IteratorWrapper extends AbstractByteIterator
/*     */   {
/*     */     final Iterator<Byte> i;
/*     */ 
/*     */     public IteratorWrapper(Iterator<Byte> i)
/*     */     {
/* 389 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 392 */       return this.i.hasNext(); } 
/* 393 */     public void remove() { this.i.remove(); } 
/*     */     public byte nextByte() {
/* 395 */       return ((Byte)this.i.next()).byteValue();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class ArrayIterator extends AbstractByteListIterator
/*     */   {
/*     */     private final byte[] array;
/*     */     private final int offset;
/*     */     private final int length;
/*     */     private int curr;
/*     */ 
/*     */     public ArrayIterator(byte[] array, int offset, int length)
/*     */     {
/* 122 */       this.array = array;
/* 123 */       this.offset = offset;
/* 124 */       this.length = length;
/*     */     }
/*     */     public boolean hasNext() {
/* 127 */       return this.curr < this.length; } 
/* 128 */     public boolean hasPrevious() { return this.curr > 0; }
/*     */ 
/*     */     public byte nextByte() {
/* 131 */       if (!hasNext()) throw new NoSuchElementException();
/* 132 */       return this.array[(this.offset + this.curr++)];
/*     */     }
/*     */ 
/*     */     public byte previousByte() {
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
/*     */   private static class SingletonIterator extends AbstractByteListIterator
/*     */   {
/*     */     private final byte element;
/*     */     private int curr;
/*     */ 
/*     */     public SingletonIterator(byte element)
/*     */     {
/*  85 */       this.element = element;
/*     */     }
/*  87 */     public boolean hasNext() { return this.curr == 0; } 
/*  88 */     public boolean hasPrevious() { return this.curr == 1; } 
/*     */     public byte nextByte() {
/*  90 */       if (!hasNext()) throw new NoSuchElementException();
/*  91 */       this.curr = 1;
/*  92 */       return this.element;
/*     */     }
/*     */     public byte previousByte() {
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
/*     */   public static class EmptyIterator extends AbstractByteListIterator
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/*  62 */       return false; } 
/*  63 */     public boolean hasPrevious() { return false; } 
/*  64 */     public byte nextByte() { throw new NoSuchElementException(); } 
/*  65 */     public byte previousByte() { throw new NoSuchElementException(); } 
/*  66 */     public int nextIndex() { return 0; } 
/*  67 */     public int previousIndex() { return -1; } 
/*  68 */     public int skip(int n) { return 0; } 
/*  69 */     public int back(int n) { return 0; } 
/*  70 */     public Object clone() { return ByteIterators.EMPTY_ITERATOR; } 
/*  71 */     private Object readResolve() { return ByteIterators.EMPTY_ITERATOR; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteIterators
 * JD-Core Version:    0.6.2
 */