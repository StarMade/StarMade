/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.bytes.ByteIterator;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class FloatIterators
/*     */ {
/*  79 */   public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
/*     */ 
/*     */   public static FloatListIterator singleton(float element)
/*     */   {
/* 112 */     return new SingletonIterator(element);
/*     */   }
/*     */ 
/*     */   public static FloatListIterator wrap(float[] array, int offset, int length)
/*     */   {
/* 181 */     FloatArrays.ensureOffsetLength(array, offset, length);
/* 182 */     return new ArrayIterator(array, offset, length);
/*     */   }
/*     */ 
/*     */   public static FloatListIterator wrap(float[] array)
/*     */   {
/* 193 */     return new ArrayIterator(array, 0, array.length);
/*     */   }
/*     */ 
/*     */   public static int unwrap(FloatIterator i, float[] array, int offset, int max)
/*     */   {
/* 211 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 212 */     if ((offset < 0) || (offset + max > array.length)) throw new IllegalArgumentException();
/* 213 */     int j = max;
/* 214 */     while ((j-- != 0) && (i.hasNext())) array[(offset++)] = i.nextFloat();
/* 215 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static int unwrap(FloatIterator i, float[] array)
/*     */   {
/* 229 */     return unwrap(i, array, 0, array.length);
/*     */   }
/*     */ 
/*     */   public static float[] unwrap(FloatIterator i, int max)
/*     */   {
/* 244 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 245 */     float[] array = new float[16];
/* 246 */     int j = 0;
/*     */ 
/* 248 */     while ((max-- != 0) && (i.hasNext())) {
/* 249 */       if (j == array.length) array = FloatArrays.grow(array, j + 1);
/* 250 */       array[(j++)] = i.nextFloat();
/*     */     }
/*     */ 
/* 253 */     return FloatArrays.trim(array, j);
/*     */   }
/*     */ 
/*     */   public static float[] unwrap(FloatIterator i)
/*     */   {
/* 267 */     return unwrap(i, 2147483647);
/*     */   }
/*     */ 
/*     */   public static int unwrap(FloatIterator i, FloatCollection c, int max)
/*     */   {
/* 286 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 287 */     int j = max;
/* 288 */     while ((j-- != 0) && (i.hasNext())) c.add(i.nextFloat());
/* 289 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static long unwrap(FloatIterator i, FloatCollection c)
/*     */   {
/* 305 */     long n = 0L;
/* 306 */     while (i.hasNext()) {
/* 307 */       c.add(i.nextFloat());
/* 308 */       n += 1L;
/*     */     }
/* 310 */     return n;
/*     */   }
/*     */ 
/*     */   public static int pour(FloatIterator i, FloatCollection s, int max)
/*     */   {
/* 328 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 329 */     int j = max;
/* 330 */     while ((j-- != 0) && (i.hasNext())) s.add(i.nextFloat());
/* 331 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static int pour(FloatIterator i, FloatCollection s)
/*     */   {
/* 347 */     return pour(i, s, 2147483647);
/*     */   }
/*     */ 
/*     */   public static FloatList pour(FloatIterator i, int max)
/*     */   {
/* 364 */     FloatArrayList l = new FloatArrayList();
/* 365 */     pour(i, l, max);
/* 366 */     l.trim();
/* 367 */     return l;
/*     */   }
/*     */ 
/*     */   public static FloatList pour(FloatIterator i)
/*     */   {
/* 382 */     return pour(i, 2147483647);
/*     */   }
/*     */ 
/*     */   public static FloatIterator asFloatIterator(Iterator i)
/*     */   {
/* 414 */     if ((i instanceof FloatIterator)) return (FloatIterator)i;
/* 415 */     return new IteratorWrapper(i);
/*     */   }
/*     */ 
/*     */   public static FloatListIterator asFloatIterator(ListIterator i)
/*     */   {
/* 457 */     if ((i instanceof FloatListIterator)) return (FloatListIterator)i;
/* 458 */     return new ListIteratorWrapper(i);
/*     */   }
/*     */ 
/*     */   public static FloatIterator concat(FloatIterator[] a)
/*     */   {
/* 511 */     return concat(a, 0, a.length);
/*     */   }
/*     */ 
/*     */   public static FloatIterator concat(FloatIterator[] a, int offset, int length)
/*     */   {
/* 526 */     return new IteratorConcatenator(a, offset, length);
/*     */   }
/*     */ 
/*     */   public static FloatIterator unmodifiable(FloatIterator i)
/*     */   {
/* 544 */     return new UnmodifiableIterator(i);
/*     */   }
/*     */ 
/*     */   public static FloatBidirectionalIterator unmodifiable(FloatBidirectionalIterator i)
/*     */   {
/* 564 */     return new UnmodifiableBidirectionalIterator(i);
/*     */   }
/*     */ 
/*     */   public static FloatListIterator unmodifiable(FloatListIterator i)
/*     */   {
/* 586 */     return new UnmodifiableListIterator(i);
/*     */   }
/*     */ 
/*     */   public static FloatIterator wrap(ByteIterator iterator)
/*     */   {
/* 603 */     return new ByteIteratorWrapper(iterator);
/*     */   }
/*     */ 
/*     */   public static FloatIterator wrap(ShortIterator iterator)
/*     */   {
/* 621 */     return new ShortIteratorWrapper(iterator);
/*     */   }
/*     */ 
/*     */   protected static class ShortIteratorWrapper
/*     */     implements FloatIterator
/*     */   {
/*     */     final ShortIterator iterator;
/*     */ 
/*     */     public ShortIteratorWrapper(ShortIterator iterator)
/*     */     {
/* 609 */       this.iterator = iterator;
/*     */     }
/* 611 */     public boolean hasNext() { return this.iterator.hasNext(); } 
/* 612 */     public Float next() { return Float.valueOf(this.iterator.nextShort()); } 
/* 613 */     public float nextFloat() { return this.iterator.nextShort(); } 
/* 614 */     public void remove() { this.iterator.remove(); } 
/* 615 */     public int skip(int n) { return this.iterator.skip(n); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class ByteIteratorWrapper
/*     */     implements FloatIterator
/*     */   {
/*     */     final ByteIterator iterator;
/*     */ 
/*     */     public ByteIteratorWrapper(ByteIterator iterator)
/*     */     {
/* 591 */       this.iterator = iterator;
/*     */     }
/* 593 */     public boolean hasNext() { return this.iterator.hasNext(); } 
/* 594 */     public Float next() { return Float.valueOf(this.iterator.nextByte()); } 
/* 595 */     public float nextFloat() { return this.iterator.nextByte(); } 
/* 596 */     public void remove() { this.iterator.remove(); } 
/* 597 */     public int skip(int n) { return this.iterator.skip(n); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableListIterator extends AbstractFloatListIterator
/*     */   {
/*     */     protected final FloatListIterator i;
/*     */ 
/*     */     public UnmodifiableListIterator(FloatListIterator i)
/*     */     {
/* 570 */       this.i = i;
/*     */     }
/* 572 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 573 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 574 */     public float nextFloat() { return this.i.nextFloat(); } 
/* 575 */     public float previousFloat() { return this.i.previousFloat(); } 
/* 576 */     public int nextIndex() { return this.i.nextIndex(); } 
/* 577 */     public int previousIndex() { return this.i.previousIndex(); } 
/* 578 */     public Float next() { return (Float)this.i.next(); } 
/* 579 */     public Float previous() { return (Float)this.i.previous(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableBidirectionalIterator extends AbstractFloatBidirectionalIterator
/*     */   {
/*     */     protected final FloatBidirectionalIterator i;
/*     */ 
/*     */     public UnmodifiableBidirectionalIterator(FloatBidirectionalIterator i)
/*     */     {
/* 550 */       this.i = i;
/*     */     }
/* 552 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 553 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 554 */     public float nextFloat() { return this.i.nextFloat(); } 
/* 555 */     public float previousFloat() { return this.i.previousFloat(); } 
/* 556 */     public Float next() { return (Float)this.i.next(); } 
/* 557 */     public Float previous() { return (Float)this.i.previous(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableIterator extends AbstractFloatIterator
/*     */   {
/*     */     protected final FloatIterator i;
/*     */ 
/*     */     public UnmodifiableIterator(FloatIterator i)
/*     */     {
/* 533 */       this.i = i;
/*     */     }
/* 535 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 536 */     public float nextFloat() { return this.i.nextFloat(); } 
/* 537 */     public Float next() { return (Float)this.i.next(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class IteratorConcatenator extends AbstractFloatIterator
/*     */   {
/*     */     final FloatIterator[] a;
/*     */     int offset;
/*     */     int length;
/* 462 */     int lastOffset = -1;
/*     */ 
/* 464 */     public IteratorConcatenator(FloatIterator[] a, int offset, int length) { this.a = a;
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
/*     */     public float nextFloat() {
/* 481 */       if (!hasNext()) throw new NoSuchElementException();
/* 482 */       float next = this.a[(this.lastOffset = this.offset)].nextFloat();
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
/*     */   private static class ListIteratorWrapper extends AbstractFloatListIterator
/*     */   {
/*     */     final ListIterator<Float> i;
/*     */ 
/*     */     public ListIteratorWrapper(ListIterator<Float> i)
/*     */     {
/* 423 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 426 */       return this.i.hasNext(); } 
/* 427 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 428 */     public int nextIndex() { return this.i.nextIndex(); } 
/* 429 */     public int previousIndex() { return this.i.previousIndex(); } 
/*     */     public void set(float k) {
/* 431 */       this.i.set(Float.valueOf(k));
/*     */     }
/* 433 */     public void add(float k) { this.i.add(Float.valueOf(k)); } 
/* 434 */     public void remove() { this.i.remove(); } 
/*     */     public float nextFloat() {
/* 436 */       return ((Float)this.i.next()).floatValue(); } 
/* 437 */     public float previousFloat() { return ((Float)this.i.previous()).floatValue(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class IteratorWrapper extends AbstractFloatIterator
/*     */   {
/*     */     final Iterator<Float> i;
/*     */ 
/*     */     public IteratorWrapper(Iterator<Float> i)
/*     */     {
/* 389 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 392 */       return this.i.hasNext(); } 
/* 393 */     public void remove() { this.i.remove(); } 
/*     */     public float nextFloat() {
/* 395 */       return ((Float)this.i.next()).floatValue();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class ArrayIterator extends AbstractFloatListIterator
/*     */   {
/*     */     private final float[] array;
/*     */     private final int offset;
/*     */     private final int length;
/*     */     private int curr;
/*     */ 
/*     */     public ArrayIterator(float[] array, int offset, int length)
/*     */     {
/* 122 */       this.array = array;
/* 123 */       this.offset = offset;
/* 124 */       this.length = length;
/*     */     }
/*     */     public boolean hasNext() {
/* 127 */       return this.curr < this.length; } 
/* 128 */     public boolean hasPrevious() { return this.curr > 0; }
/*     */ 
/*     */     public float nextFloat() {
/* 131 */       if (!hasNext()) throw new NoSuchElementException();
/* 132 */       return this.array[(this.offset + this.curr++)];
/*     */     }
/*     */ 
/*     */     public float previousFloat() {
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
/*     */   private static class SingletonIterator extends AbstractFloatListIterator
/*     */   {
/*     */     private final float element;
/*     */     private int curr;
/*     */ 
/*     */     public SingletonIterator(float element)
/*     */     {
/*  85 */       this.element = element;
/*     */     }
/*  87 */     public boolean hasNext() { return this.curr == 0; } 
/*  88 */     public boolean hasPrevious() { return this.curr == 1; } 
/*     */     public float nextFloat() {
/*  90 */       if (!hasNext()) throw new NoSuchElementException();
/*  91 */       this.curr = 1;
/*  92 */       return this.element;
/*     */     }
/*     */     public float previousFloat() {
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
/*     */   public static class EmptyIterator extends AbstractFloatListIterator
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/*  62 */       return false; } 
/*  63 */     public boolean hasPrevious() { return false; } 
/*  64 */     public float nextFloat() { throw new NoSuchElementException(); } 
/*  65 */     public float previousFloat() { throw new NoSuchElementException(); } 
/*  66 */     public int nextIndex() { return 0; } 
/*  67 */     public int previousIndex() { return -1; } 
/*  68 */     public int skip(int n) { return 0; } 
/*  69 */     public int back(int n) { return 0; } 
/*  70 */     public Object clone() { return FloatIterators.EMPTY_ITERATOR; } 
/*  71 */     private Object readResolve() { return FloatIterators.EMPTY_ITERATOR; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatIterators
 * JD-Core Version:    0.6.2
 */