/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class CharIterators
/*     */ {
/*  79 */   public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
/*     */ 
/*     */   public static CharListIterator singleton(char element)
/*     */   {
/* 112 */     return new SingletonIterator(element);
/*     */   }
/*     */ 
/*     */   public static CharListIterator wrap(char[] array, int offset, int length)
/*     */   {
/* 181 */     CharArrays.ensureOffsetLength(array, offset, length);
/* 182 */     return new ArrayIterator(array, offset, length);
/*     */   }
/*     */ 
/*     */   public static CharListIterator wrap(char[] array)
/*     */   {
/* 193 */     return new ArrayIterator(array, 0, array.length);
/*     */   }
/*     */ 
/*     */   public static int unwrap(CharIterator i, char[] array, int offset, int max)
/*     */   {
/* 211 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 212 */     if ((offset < 0) || (offset + max > array.length)) throw new IllegalArgumentException();
/* 213 */     int j = max;
/* 214 */     while ((j-- != 0) && (i.hasNext())) array[(offset++)] = i.nextChar();
/* 215 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static int unwrap(CharIterator i, char[] array)
/*     */   {
/* 229 */     return unwrap(i, array, 0, array.length);
/*     */   }
/*     */ 
/*     */   public static char[] unwrap(CharIterator i, int max)
/*     */   {
/* 244 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 245 */     char[] array = new char[16];
/* 246 */     int j = 0;
/*     */ 
/* 248 */     while ((max-- != 0) && (i.hasNext())) {
/* 249 */       if (j == array.length) array = CharArrays.grow(array, j + 1);
/* 250 */       array[(j++)] = i.nextChar();
/*     */     }
/*     */ 
/* 253 */     return CharArrays.trim(array, j);
/*     */   }
/*     */ 
/*     */   public static char[] unwrap(CharIterator i)
/*     */   {
/* 267 */     return unwrap(i, 2147483647);
/*     */   }
/*     */ 
/*     */   public static int unwrap(CharIterator i, CharCollection c, int max)
/*     */   {
/* 286 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 287 */     int j = max;
/* 288 */     while ((j-- != 0) && (i.hasNext())) c.add(i.nextChar());
/* 289 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static long unwrap(CharIterator i, CharCollection c)
/*     */   {
/* 305 */     long n = 0L;
/* 306 */     while (i.hasNext()) {
/* 307 */       c.add(i.nextChar());
/* 308 */       n += 1L;
/*     */     }
/* 310 */     return n;
/*     */   }
/*     */ 
/*     */   public static int pour(CharIterator i, CharCollection s, int max)
/*     */   {
/* 328 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 329 */     int j = max;
/* 330 */     while ((j-- != 0) && (i.hasNext())) s.add(i.nextChar());
/* 331 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static int pour(CharIterator i, CharCollection s)
/*     */   {
/* 347 */     return pour(i, s, 2147483647);
/*     */   }
/*     */ 
/*     */   public static CharList pour(CharIterator i, int max)
/*     */   {
/* 364 */     CharArrayList l = new CharArrayList();
/* 365 */     pour(i, l, max);
/* 366 */     l.trim();
/* 367 */     return l;
/*     */   }
/*     */ 
/*     */   public static CharList pour(CharIterator i)
/*     */   {
/* 382 */     return pour(i, 2147483647);
/*     */   }
/*     */ 
/*     */   public static CharIterator asCharIterator(Iterator i)
/*     */   {
/* 414 */     if ((i instanceof CharIterator)) return (CharIterator)i;
/* 415 */     return new IteratorWrapper(i);
/*     */   }
/*     */ 
/*     */   public static CharListIterator asCharIterator(ListIterator i)
/*     */   {
/* 457 */     if ((i instanceof CharListIterator)) return (CharListIterator)i;
/* 458 */     return new ListIteratorWrapper(i);
/*     */   }
/*     */ 
/*     */   public static CharListIterator fromTo(char from, char to)
/*     */   {
/* 532 */     return new IntervalIterator(from, to);
/*     */   }
/*     */ 
/*     */   public static CharIterator concat(CharIterator[] a)
/*     */   {
/* 585 */     return concat(a, 0, a.length);
/*     */   }
/*     */ 
/*     */   public static CharIterator concat(CharIterator[] a, int offset, int length)
/*     */   {
/* 600 */     return new IteratorConcatenator(a, offset, length);
/*     */   }
/*     */ 
/*     */   public static CharIterator unmodifiable(CharIterator i)
/*     */   {
/* 618 */     return new UnmodifiableIterator(i);
/*     */   }
/*     */ 
/*     */   public static CharBidirectionalIterator unmodifiable(CharBidirectionalIterator i)
/*     */   {
/* 638 */     return new UnmodifiableBidirectionalIterator(i);
/*     */   }
/*     */ 
/*     */   public static CharListIterator unmodifiable(CharListIterator i)
/*     */   {
/* 660 */     return new UnmodifiableListIterator(i);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableListIterator extends AbstractCharListIterator
/*     */   {
/*     */     protected final CharListIterator i;
/*     */ 
/*     */     public UnmodifiableListIterator(CharListIterator i)
/*     */     {
/* 644 */       this.i = i;
/*     */     }
/* 646 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 647 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 648 */     public char nextChar() { return this.i.nextChar(); } 
/* 649 */     public char previousChar() { return this.i.previousChar(); } 
/* 650 */     public int nextIndex() { return this.i.nextIndex(); } 
/* 651 */     public int previousIndex() { return this.i.previousIndex(); } 
/* 652 */     public Character next() { return (Character)this.i.next(); } 
/* 653 */     public Character previous() { return (Character)this.i.previous(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableBidirectionalIterator extends AbstractCharBidirectionalIterator
/*     */   {
/*     */     protected final CharBidirectionalIterator i;
/*     */ 
/*     */     public UnmodifiableBidirectionalIterator(CharBidirectionalIterator i)
/*     */     {
/* 624 */       this.i = i;
/*     */     }
/* 626 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 627 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 628 */     public char nextChar() { return this.i.nextChar(); } 
/* 629 */     public char previousChar() { return this.i.previousChar(); } 
/* 630 */     public Character next() { return (Character)this.i.next(); } 
/* 631 */     public Character previous() { return (Character)this.i.previous(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableIterator extends AbstractCharIterator
/*     */   {
/*     */     protected final CharIterator i;
/*     */ 
/*     */     public UnmodifiableIterator(CharIterator i)
/*     */     {
/* 607 */       this.i = i;
/*     */     }
/* 609 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 610 */     public char nextChar() { return this.i.nextChar(); } 
/* 611 */     public Character next() { return (Character)this.i.next(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class IteratorConcatenator extends AbstractCharIterator
/*     */   {
/*     */     final CharIterator[] a;
/*     */     int offset;
/*     */     int length;
/* 536 */     int lastOffset = -1;
/*     */ 
/* 538 */     public IteratorConcatenator(CharIterator[] a, int offset, int length) { this.a = a;
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
/*     */     public char nextChar() {
/* 555 */       if (!hasNext()) throw new NoSuchElementException();
/* 556 */       char next = this.a[(this.lastOffset = this.offset)].nextChar();
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
/*     */   private static class IntervalIterator extends AbstractCharListIterator
/*     */   {
/*     */     private final char from;
/*     */     private final char to;
/*     */     char curr;
/*     */ 
/*     */     public IntervalIterator(char from, char to)
/*     */     {
/* 473 */       this.from = (this.curr = from);
/* 474 */       this.to = to;
/*     */     }
/*     */     public boolean hasNext() {
/* 477 */       return this.curr < this.to; } 
/* 478 */     public boolean hasPrevious() { return this.curr > this.from; }
/*     */ 
/*     */     public char nextChar() {
/* 481 */       if (!hasNext()) throw new NoSuchElementException();
/* 482 */       return this.curr++;
/*     */     }
/*     */     public char previousChar() {
/* 485 */       if (!hasPrevious()) throw new NoSuchElementException();
/* 486 */       return this.curr = (char)(this.curr - '\001');
/*     */     }
/*     */ 
/*     */     public int nextIndex() {
/* 490 */       return this.curr - this.from; } 
/* 491 */     public int previousIndex() { return this.curr - this.from - 1; }
/*     */ 
/*     */     public int skip(int n)
/*     */     {
/* 495 */       if (this.curr + n <= this.to) {
/* 496 */         this.curr = ((char)(this.curr + n));
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
/* 510 */         this.curr = ((char)(this.curr - n));
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
/*     */   private static class ListIteratorWrapper extends AbstractCharListIterator
/*     */   {
/*     */     final ListIterator<Character> i;
/*     */ 
/*     */     public ListIteratorWrapper(ListIterator<Character> i)
/*     */     {
/* 423 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 426 */       return this.i.hasNext(); } 
/* 427 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 428 */     public int nextIndex() { return this.i.nextIndex(); } 
/* 429 */     public int previousIndex() { return this.i.previousIndex(); } 
/*     */     public void set(char k) {
/* 431 */       this.i.set(Character.valueOf(k));
/*     */     }
/* 433 */     public void add(char k) { this.i.add(Character.valueOf(k)); } 
/* 434 */     public void remove() { this.i.remove(); } 
/*     */     public char nextChar() {
/* 436 */       return ((Character)this.i.next()).charValue(); } 
/* 437 */     public char previousChar() { return ((Character)this.i.previous()).charValue(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class IteratorWrapper extends AbstractCharIterator
/*     */   {
/*     */     final Iterator<Character> i;
/*     */ 
/*     */     public IteratorWrapper(Iterator<Character> i)
/*     */     {
/* 389 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 392 */       return this.i.hasNext(); } 
/* 393 */     public void remove() { this.i.remove(); } 
/*     */     public char nextChar() {
/* 395 */       return ((Character)this.i.next()).charValue();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class ArrayIterator extends AbstractCharListIterator
/*     */   {
/*     */     private final char[] array;
/*     */     private final int offset;
/*     */     private final int length;
/*     */     private int curr;
/*     */ 
/*     */     public ArrayIterator(char[] array, int offset, int length)
/*     */     {
/* 122 */       this.array = array;
/* 123 */       this.offset = offset;
/* 124 */       this.length = length;
/*     */     }
/*     */     public boolean hasNext() {
/* 127 */       return this.curr < this.length; } 
/* 128 */     public boolean hasPrevious() { return this.curr > 0; }
/*     */ 
/*     */     public char nextChar() {
/* 131 */       if (!hasNext()) throw new NoSuchElementException();
/* 132 */       return this.array[(this.offset + this.curr++)];
/*     */     }
/*     */ 
/*     */     public char previousChar() {
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
/*     */   private static class SingletonIterator extends AbstractCharListIterator
/*     */   {
/*     */     private final char element;
/*     */     private int curr;
/*     */ 
/*     */     public SingletonIterator(char element)
/*     */     {
/*  85 */       this.element = element;
/*     */     }
/*  87 */     public boolean hasNext() { return this.curr == 0; } 
/*  88 */     public boolean hasPrevious() { return this.curr == 1; } 
/*     */     public char nextChar() {
/*  90 */       if (!hasNext()) throw new NoSuchElementException();
/*  91 */       this.curr = 1;
/*  92 */       return this.element;
/*     */     }
/*     */     public char previousChar() {
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
/*     */   public static class EmptyIterator extends AbstractCharListIterator
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/*  62 */       return false; } 
/*  63 */     public boolean hasPrevious() { return false; } 
/*  64 */     public char nextChar() { throw new NoSuchElementException(); } 
/*  65 */     public char previousChar() { throw new NoSuchElementException(); } 
/*  66 */     public int nextIndex() { return 0; } 
/*  67 */     public int previousIndex() { return -1; } 
/*  68 */     public int skip(int n) { return 0; } 
/*  69 */     public int back(int n) { return 0; } 
/*  70 */     public Object clone() { return CharIterators.EMPTY_ITERATOR; } 
/*  71 */     private Object readResolve() { return CharIterators.EMPTY_ITERATOR; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharIterators
 * JD-Core Version:    0.6.2
 */