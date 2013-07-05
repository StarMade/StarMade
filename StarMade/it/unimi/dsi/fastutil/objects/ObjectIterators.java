/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ObjectIterators
/*     */ {
/*  78 */   public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
/*     */ 
/*     */   public static <K> ObjectListIterator<K> singleton(K element)
/*     */   {
/* 111 */     return new SingletonIterator(element);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectListIterator<K> wrap(K[] array, int offset, int length)
/*     */   {
/* 181 */     ObjectArrays.ensureOffsetLength(array, offset, length);
/* 182 */     return new ArrayIterator(array, offset, length);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectListIterator<K> wrap(K[] array)
/*     */   {
/* 193 */     return new ArrayIterator(array, 0, array.length);
/*     */   }
/*     */ 
/*     */   public static <K> int unwrap(Iterator<? extends K> i, K[] array, int offset, int max)
/*     */   {
/* 211 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 212 */     if ((offset < 0) || (offset + max > array.length)) throw new IllegalArgumentException();
/* 213 */     int j = max;
/* 214 */     while ((j-- != 0) && (i.hasNext())) array[(offset++)] = i.next();
/* 215 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static <K> int unwrap(Iterator<? extends K> i, K[] array)
/*     */   {
/* 229 */     return unwrap(i, array, 0, array.length);
/*     */   }
/*     */ 
/*     */   public static <K> K[] unwrap(Iterator<? extends K> i, int max)
/*     */   {
/* 244 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 245 */     Object[] array = (Object[])new Object[16];
/* 246 */     int j = 0;
/*     */ 
/* 248 */     while ((max-- != 0) && (i.hasNext())) {
/* 249 */       if (j == array.length) array = ObjectArrays.grow(array, j + 1);
/* 250 */       array[(j++)] = i.next();
/*     */     }
/*     */ 
/* 253 */     return ObjectArrays.trim(array, j);
/*     */   }
/*     */ 
/*     */   public static <K> K[] unwrap(Iterator<? extends K> i)
/*     */   {
/* 267 */     return unwrap(i, 2147483647);
/*     */   }
/*     */ 
/*     */   public static <K> int unwrap(Iterator<K> i, ObjectCollection<? super K> c, int max)
/*     */   {
/* 286 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 287 */     int j = max;
/* 288 */     while ((j-- != 0) && (i.hasNext())) c.add(i.next());
/* 289 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static <K> long unwrap(Iterator<K> i, ObjectCollection<? super K> c)
/*     */   {
/* 305 */     long n = 0L;
/* 306 */     while (i.hasNext()) {
/* 307 */       c.add(i.next());
/* 308 */       n += 1L;
/*     */     }
/* 310 */     return n;
/*     */   }
/*     */ 
/*     */   public static <K> int pour(Iterator<K> i, ObjectCollection<? super K> s, int max)
/*     */   {
/* 328 */     if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 329 */     int j = max;
/* 330 */     while ((j-- != 0) && (i.hasNext())) s.add(i.next());
/* 331 */     return max - j - 1;
/*     */   }
/*     */ 
/*     */   public static <K> int pour(Iterator<K> i, ObjectCollection<? super K> s)
/*     */   {
/* 347 */     return pour(i, s, 2147483647);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectList<K> pour(Iterator<K> i, int max)
/*     */   {
/* 364 */     ObjectArrayList l = new ObjectArrayList();
/* 365 */     pour(i, l, max);
/* 366 */     l.trim();
/* 367 */     return l;
/*     */   }
/*     */ 
/*     */   public static <K> ObjectList<K> pour(Iterator<K> i)
/*     */   {
/* 382 */     return pour(i, 2147483647);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectIterator<K> asObjectIterator(Iterator<K> i)
/*     */   {
/* 414 */     if ((i instanceof ObjectIterator)) return (ObjectIterator)i;
/* 415 */     return new IteratorWrapper(i);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectListIterator<K> asObjectIterator(ListIterator<K> i)
/*     */   {
/* 457 */     if ((i instanceof ObjectListIterator)) return (ObjectListIterator)i;
/* 458 */     return new ListIteratorWrapper(i);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectIterator<K> concat(ObjectIterator<? extends K>[] a)
/*     */   {
/* 511 */     return concat(a, 0, a.length);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectIterator<K> concat(ObjectIterator<? extends K>[] a, int offset, int length)
/*     */   {
/* 526 */     return new IteratorConcatenator(a, offset, length);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectIterator<K> unmodifiable(ObjectIterator<K> i)
/*     */   {
/* 543 */     return new UnmodifiableIterator(i);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectBidirectionalIterator<K> unmodifiable(ObjectBidirectionalIterator<K> i)
/*     */   {
/* 561 */     return new UnmodifiableBidirectionalIterator(i);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectListIterator<K> unmodifiable(ObjectListIterator<K> i)
/*     */   {
/* 581 */     return new UnmodifiableListIterator(i);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableListIterator<K> extends AbstractObjectListIterator<K>
/*     */   {
/*     */     protected final ObjectListIterator<K> i;
/*     */ 
/*     */     public UnmodifiableListIterator(ObjectListIterator<K> i)
/*     */     {
/* 567 */       this.i = i;
/*     */     }
/* 569 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 570 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 571 */     public K next() { return this.i.next(); } 
/* 572 */     public K previous() { return this.i.previous(); } 
/* 573 */     public int nextIndex() { return this.i.nextIndex(); } 
/* 574 */     public int previousIndex() { return this.i.previousIndex(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableBidirectionalIterator<K> extends AbstractObjectBidirectionalIterator<K>
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<K> i;
/*     */ 
/*     */     public UnmodifiableBidirectionalIterator(ObjectBidirectionalIterator<K> i)
/*     */     {
/* 549 */       this.i = i;
/*     */     }
/* 551 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 552 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 553 */     public K next() { return this.i.next(); } 
/* 554 */     public K previous() { return this.i.previous(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableIterator<K> extends AbstractObjectIterator<K>
/*     */   {
/*     */     protected final ObjectIterator<K> i;
/*     */ 
/*     */     public UnmodifiableIterator(ObjectIterator<K> i)
/*     */     {
/* 533 */       this.i = i;
/*     */     }
/* 535 */     public boolean hasNext() { return this.i.hasNext(); } 
/* 536 */     public K next() { return this.i.next(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class IteratorConcatenator<K> extends AbstractObjectIterator<K>
/*     */   {
/*     */     final ObjectIterator<? extends K>[] a;
/*     */     int offset;
/*     */     int length;
/* 462 */     int lastOffset = -1;
/*     */ 
/* 464 */     public IteratorConcatenator(ObjectIterator<? extends K>[] a, int offset, int length) { this.a = a;
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
/*     */     public K next() {
/* 481 */       if (!hasNext()) throw new NoSuchElementException();
/* 482 */       Object next = this.a[(this.lastOffset = this.offset)].next();
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
/*     */   private static class ListIteratorWrapper<K> extends AbstractObjectListIterator<K>
/*     */   {
/*     */     final ListIterator<K> i;
/*     */ 
/*     */     public ListIteratorWrapper(ListIterator<K> i)
/*     */     {
/* 423 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 426 */       return this.i.hasNext(); } 
/* 427 */     public boolean hasPrevious() { return this.i.hasPrevious(); } 
/* 428 */     public int nextIndex() { return this.i.nextIndex(); } 
/* 429 */     public int previousIndex() { return this.i.previousIndex(); } 
/*     */     public void set(K k) {
/* 431 */       this.i.set(k);
/*     */     }
/* 433 */     public void add(K k) { this.i.add(k); } 
/* 434 */     public void remove() { this.i.remove(); } 
/*     */     public K next() {
/* 436 */       return this.i.next(); } 
/* 437 */     public K previous() { return this.i.previous(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private static class IteratorWrapper<K> extends AbstractObjectIterator<K>
/*     */   {
/*     */     final Iterator<K> i;
/*     */ 
/*     */     public IteratorWrapper(Iterator<K> i)
/*     */     {
/* 389 */       this.i = i;
/*     */     }
/*     */     public boolean hasNext() {
/* 392 */       return this.i.hasNext(); } 
/* 393 */     public void remove() { this.i.remove(); } 
/*     */     public K next() {
/* 395 */       return this.i.next();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class ArrayIterator<K> extends AbstractObjectListIterator<K>
/*     */   {
/*     */     private final K[] array;
/*     */     private final int offset;
/*     */     private final int length;
/*     */     private int curr;
/*     */ 
/*     */     public ArrayIterator(K[] array, int offset, int length)
/*     */     {
/* 122 */       this.array = array;
/* 123 */       this.offset = offset;
/* 124 */       this.length = length;
/*     */     }
/*     */     public boolean hasNext() {
/* 127 */       return this.curr < this.length; } 
/* 128 */     public boolean hasPrevious() { return this.curr > 0; }
/*     */ 
/*     */     public K next() {
/* 131 */       if (!hasNext()) throw new NoSuchElementException();
/* 132 */       return this.array[(this.offset + this.curr++)];
/*     */     }
/*     */ 
/*     */     public K previous() {
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
/*     */   private static class SingletonIterator<K> extends AbstractObjectListIterator<K>
/*     */   {
/*     */     private final K element;
/*     */     private int curr;
/*     */ 
/*     */     public SingletonIterator(K element)
/*     */     {
/*  84 */       this.element = element;
/*     */     }
/*  86 */     public boolean hasNext() { return this.curr == 0; } 
/*  87 */     public boolean hasPrevious() { return this.curr == 1; } 
/*     */     public K next() {
/*  89 */       if (!hasNext()) throw new NoSuchElementException();
/*  90 */       this.curr = 1;
/*  91 */       return this.element;
/*     */     }
/*     */     public K previous() {
/*  94 */       if (!hasPrevious()) throw new NoSuchElementException();
/*  95 */       this.curr = 0;
/*  96 */       return this.element;
/*     */     }
/*     */     public int nextIndex() {
/*  99 */       return this.curr;
/*     */     }
/*     */     public int previousIndex() {
/* 102 */       return this.curr - 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptyIterator<K> extends AbstractObjectListIterator<K>
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/*  61 */       return false; } 
/*  62 */     public boolean hasPrevious() { return false; } 
/*  63 */     public K next() { throw new NoSuchElementException(); } 
/*  64 */     public K previous() { throw new NoSuchElementException(); } 
/*  65 */     public int nextIndex() { return 0; } 
/*  66 */     public int previousIndex() { return -1; } 
/*  67 */     public int skip(int n) { return 0; } 
/*  68 */     public int back(int n) { return 0; } 
/*  69 */     public Object clone() { return ObjectIterators.EMPTY_ITERATOR; } 
/*  70 */     private Object readResolve() { return ObjectIterators.EMPTY_ITERATOR; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectIterators
 * JD-Core Version:    0.6.2
 */