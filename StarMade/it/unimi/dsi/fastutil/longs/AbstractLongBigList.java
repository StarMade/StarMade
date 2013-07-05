/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.BigList;
/*     */ import it.unimi.dsi.fastutil.BigListIterator;
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public abstract class AbstractLongBigList extends AbstractLongCollection
/*     */   implements LongBigList, LongStack
/*     */ {
/*     */   protected void ensureIndex(long index)
/*     */   {
/*  59 */     if (index < 0L) throw new IndexOutOfBoundsException(new StringBuilder().append("Index (").append(index).append(") is negative").toString());
/*  60 */     if (index > size64()) throw new IndexOutOfBoundsException(new StringBuilder().append("Index (").append(index).append(") is greater than list size (").append(size64()).append(")").toString());
/*     */   }
/*     */ 
/*     */   protected void ensureRestrictedIndex(long index)
/*     */   {
/*  68 */     if (index < 0L) throw new IndexOutOfBoundsException(new StringBuilder().append("Index (").append(index).append(") is negative").toString());
/*  69 */     if (index >= size64()) throw new IndexOutOfBoundsException(new StringBuilder().append("Index (").append(index).append(") is greater than or equal to list size (").append(size64()).append(")").toString()); 
/*     */   }
/*     */ 
/*  72 */   public void add(long index, long k) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public boolean add(long k) {
/*  75 */     add(size64(), k);
/*  76 */     return true;
/*     */   }
/*     */   public long removeLong(long i) {
/*  79 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public long removeLong(int i) {
/*  82 */     return removeLong(i);
/*     */   }
/*     */   public long set(long index, long k) {
/*  85 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public long set(int index, long k) {
/*  88 */     return set(index, k);
/*     */   }
/*     */   public boolean addAll(long index, Collection<? extends Long> c) {
/*  91 */     ensureIndex(index);
/*  92 */     int n = c.size();
/*  93 */     if (n == 0) return false;
/*  94 */     Iterator i = c.iterator();
/*  95 */     while (n-- != 0) add(index++, (Long)i.next());
/*  96 */     return true;
/*     */   }
/*     */   public boolean addAll(int index, Collection<? extends Long> c) {
/*  99 */     return addAll(index, c);
/*     */   }
/*     */ 
/*     */   public boolean addAll(Collection<? extends Long> c) {
/* 103 */     return addAll(size64(), c);
/*     */   }
/*     */   public LongBigListIterator iterator() {
/* 106 */     return listIterator();
/*     */   }
/*     */   public LongBigListIterator listIterator() {
/* 109 */     return listIterator(0L);
/*     */   }
/*     */   public LongBigListIterator listIterator(final long index) {
/* 112 */     return new AbstractLongBigListIterator() {
/* 113 */       long pos = index; long last = -1L;
/*     */ 
/* 114 */       public boolean hasNext() { return this.pos < AbstractLongBigList.this.size64(); } 
/* 115 */       public boolean hasPrevious() { return this.pos > 0L; } 
/* 116 */       public long nextLong() { if (!hasNext()) throw new NoSuchElementException(); return AbstractLongBigList.this.getLong(this.last = this.pos++); } 
/* 117 */       public long previousLong() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractLongBigList.this.getLong(this.last = --this.pos); } 
/* 118 */       public long nextIndex() { return this.pos; } 
/* 119 */       public long previousIndex() { return this.pos - 1L; } 
/*     */       public void add(long k) {
/* 121 */         if (this.last == -1L) throw new IllegalStateException();
/* 122 */         AbstractLongBigList.this.add(this.pos++, k);
/* 123 */         this.last = -1L;
/*     */       }
/*     */       public void set(long k) {
/* 126 */         if (this.last == -1L) throw new IllegalStateException();
/* 127 */         AbstractLongBigList.this.set(this.last, k);
/*     */       }
/*     */       public void remove() {
/* 130 */         if (this.last == -1L) throw new IllegalStateException();
/* 131 */         AbstractLongBigList.this.removeLong(this.last);
/*     */ 
/* 133 */         if (this.last < this.pos) this.pos -= 1L;
/* 134 */         this.last = -1L;
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public LongBigListIterator listIterator(int index)
/*     */   {
/* 141 */     return listIterator(index);
/*     */   }
/*     */ 
/*     */   public boolean contains(long k)
/*     */   {
/* 146 */     return indexOf(k) >= 0L;
/*     */   }
/*     */ 
/*     */   public long indexOf(long k) {
/* 150 */     LongBigListIterator i = listIterator();
/*     */ 
/* 152 */     while (i.hasNext()) {
/* 153 */       long e = i.nextLong();
/* 154 */       if (k == e) return i.previousIndex();
/*     */     }
/* 156 */     return -1L;
/*     */   }
/*     */ 
/*     */   public long lastIndexOf(long k) {
/* 160 */     LongBigListIterator i = listIterator(size64());
/*     */ 
/* 162 */     while (i.hasPrevious()) {
/* 163 */       long e = i.previousLong();
/* 164 */       if (k == e) return i.nextIndex();
/*     */     }
/* 166 */     return -1L;
/*     */   }
/*     */ 
/*     */   public void size(long size) {
/* 170 */     long i = size64();
/* 171 */     for (size <= i; i++ < size; add(0L));
/* 172 */     while (i-- != size) remove(i); 
/*     */   }
/*     */ 
/*     */   public void size(int size)
/*     */   {
/* 176 */     size(size);
/*     */   }
/*     */ 
/*     */   public LongBigList subList(long from, long to) {
/* 180 */     ensureIndex(from);
/* 181 */     ensureIndex(to);
/* 182 */     if (from > to) throw new IndexOutOfBoundsException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/*     */ 
/* 184 */     return new LongSubList(this, from, to);
/*     */   }
/*     */ 
/*     */   public void removeElements(long from, long to)
/*     */   {
/* 197 */     ensureIndex(to);
/* 198 */     LongBigListIterator i = listIterator(from);
/* 199 */     long n = to - from;
/* 200 */     if (n < 0L) throw new IllegalArgumentException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 201 */     while (n-- != 0L) {
/* 202 */       i.nextLong();
/* 203 */       i.remove();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addElements(long index, long[][] a, long offset, long length)
/*     */   {
/* 219 */     ensureIndex(index);
/* 220 */     LongBigArrays.ensureOffsetLength(a, offset, length);
/* 221 */     while (length-- != 0L) add(index++, LongBigArrays.get(a, offset++)); 
/*     */   }
/*     */ 
/*     */   public void addElements(long index, long[][] a)
/*     */   {
/* 225 */     addElements(index, a, 0L, LongBigArrays.length(a));
/*     */   }
/*     */ 
/*     */   public void getElements(long from, long[][] a, long offset, long length)
/*     */   {
/* 240 */     LongBigListIterator i = listIterator(from);
/* 241 */     LongBigArrays.ensureOffsetLength(a, offset, length);
/* 242 */     if (from + length > size64()) throw new IndexOutOfBoundsException(new StringBuilder().append("End index (").append(from + length).append(") is greater than list size (").append(size64()).append(")").toString());
/* 243 */     while (length-- != 0L) LongBigArrays.set(a, offset++, i.nextLong()); 
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public int size()
/*     */   {
/* 248 */     return (int)Math.min(2147483647L, size64());
/*     */   }
/*     */ 
/*     */   private boolean valEquals(Object a, Object b)
/*     */   {
/* 253 */     return a == null ? false : b == null ? true : a.equals(b);
/*     */   }
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 259 */     if (o == this) return true;
/* 260 */     if (!(o instanceof BigList)) return false;
/* 261 */     BigList l = (BigList)o;
/* 262 */     long s = size64();
/* 263 */     if (s != l.size64()) return false;
/*     */ 
/* 265 */     BigListIterator i1 = listIterator(); BigListIterator i2 = l.listIterator();
/*     */ 
/* 270 */     while (s-- != 0L) if (!valEquals(i1.next(), i2.next())) return false;
/*     */ 
/* 272 */     return true;
/*     */   }
/*     */ 
/*     */   public int compareTo(BigList<? extends Long> l)
/*     */   {
/* 289 */     if (l == this) return 0;
/*     */ 
/* 291 */     if ((l instanceof LongStack))
/*     */     {
/* 293 */       LongBigListIterator i1 = listIterator(); LongBigListIterator i2 = ((LongStack)l).listIterator();
/*     */ 
/* 297 */       while ((i1.hasNext()) && (i2.hasNext())) {
/* 298 */         long e1 = i1.nextLong();
/* 299 */         long e2 = i2.nextLong();
/*     */         int r;
/* 300 */         if ((r = e1 == e2 ? 0 : e1 < e2 ? -1 : 1) != 0) return r;
/*     */       }
/* 302 */       return i1.hasNext() ? 1 : i2.hasNext() ? -1 : 0;
/*     */     }
/*     */ 
/* 305 */     BigListIterator i1 = listIterator(); BigListIterator i2 = l.listIterator();
/*     */ 
/* 308 */     while ((i1.hasNext()) && (i2.hasNext()))
/*     */     {
/* 309 */       int r;
/* 309 */       if ((r = ((Comparable)i1.next()).compareTo(i2.next())) != 0) return r;
/*     */     }
/* 311 */     return i1.hasNext() ? 1 : i2.hasNext() ? -1 : 0;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 320 */     LongIterator i = iterator();
/* 321 */     int h = 1;
/* 322 */     long s = size64();
/* 323 */     while (s-- != 0L) {
/* 324 */       long k = i.nextLong();
/* 325 */       h = 31 * h + HashCommon.long2int(k);
/*     */     }
/* 327 */     return h;
/*     */   }
/*     */ 
/*     */   public void push(long o) {
/* 331 */     add(o);
/*     */   }
/*     */ 
/*     */   public long popLong() {
/* 335 */     if (isEmpty()) throw new NoSuchElementException();
/* 336 */     return removeLong(size64() - 1L);
/*     */   }
/*     */ 
/*     */   public long topLong() {
/* 340 */     if (isEmpty()) throw new NoSuchElementException();
/* 341 */     return getLong(size64() - 1L);
/*     */   }
/*     */ 
/*     */   public long peekLong(int i) {
/* 345 */     return getLong(size64() - 1L - i);
/*     */   }
/*     */ 
/*     */   public long getLong(int index)
/*     */   {
/* 351 */     return getLong(index);
/*     */   }
/*     */ 
/*     */   public boolean rem(long k) {
/* 355 */     long index = indexOf(k);
/* 356 */     if (index == -1L) return false;
/* 357 */     removeLong(index);
/* 358 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean addAll(long index, LongCollection c)
/*     */   {
/* 363 */     return addAll(index, c);
/*     */   }
/*     */ 
/*     */   public boolean addAll(long index, LongBigList l)
/*     */   {
/* 368 */     return addAll(index, l);
/*     */   }
/*     */ 
/*     */   public boolean addAll(LongCollection c) {
/* 372 */     return addAll(size64(), c);
/*     */   }
/*     */ 
/*     */   public boolean addAll(LongBigList l) {
/* 376 */     return addAll(size64(), l);
/*     */   }
/*     */ 
/*     */   public void add(long index, Long ok)
/*     */   {
/* 381 */     add(index, ok.longValue());
/*     */   }
/*     */ 
/*     */   public Long set(long index, Long ok)
/*     */   {
/* 386 */     return Long.valueOf(set(index, ok.longValue()));
/*     */   }
/*     */ 
/*     */   public Long get(long index)
/*     */   {
/* 391 */     return Long.valueOf(getLong(index));
/*     */   }
/*     */ 
/*     */   public long indexOf(Object ok)
/*     */   {
/* 396 */     return indexOf(((Long)ok).longValue());
/*     */   }
/*     */ 
/*     */   public long lastIndexOf(Object ok)
/*     */   {
/* 401 */     return lastIndexOf(((Long)ok).longValue());
/*     */   }
/*     */ 
/*     */   public Long remove(int index)
/*     */   {
/* 406 */     return Long.valueOf(removeLong(index));
/*     */   }
/*     */ 
/*     */   public Long remove(long index)
/*     */   {
/* 411 */     return Long.valueOf(removeLong(index));
/*     */   }
/*     */ 
/*     */   public void push(Long o)
/*     */   {
/* 416 */     push(o.longValue());
/*     */   }
/*     */ 
/*     */   public Long pop()
/*     */   {
/* 421 */     return Long.valueOf(popLong());
/*     */   }
/*     */ 
/*     */   public Long top()
/*     */   {
/* 426 */     return Long.valueOf(topLong());
/*     */   }
/*     */ 
/*     */   public Long peek(int i)
/*     */   {
/* 431 */     return Long.valueOf(peekLong(i));
/*     */   }
/*     */   public String toString() {
/* 434 */     StringBuilder s = new StringBuilder();
/* 435 */     LongIterator i = iterator();
/* 436 */     long n = size64();
/*     */ 
/* 438 */     boolean first = true;
/* 439 */     s.append("[");
/* 440 */     while (n-- != 0L) {
/* 441 */       if (first) first = false; else
/* 442 */         s.append(", ");
/* 443 */       long k = i.nextLong();
/* 444 */       s.append(String.valueOf(k));
/*     */     }
/* 446 */     s.append("]");
/* 447 */     return s.toString();
/*     */   }
/*     */   public static class LongSubList extends AbstractLongBigList implements Serializable { public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final LongBigList l;
/*     */     protected final long from;
/*     */     protected long to;
/*     */     private static final boolean ASSERTS = false;
/*     */ 
/* 459 */     public LongSubList(LongBigList l, long from, long to) { this.l = l;
/* 460 */       this.from = from;
/* 461 */       this.to = to;
/*     */     }
/*     */ 
/*     */     private void assertRange()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean add(long k)
/*     */     {
/* 471 */       this.l.add(this.to, k);
/* 472 */       this.to += 1L;
/*     */ 
/* 474 */       return true;
/*     */     }
/*     */     public void add(long index, long k) {
/* 477 */       ensureIndex(index);
/* 478 */       this.l.add(this.from + index, k);
/* 479 */       this.to += 1L;
/*     */     }
/*     */ 
/*     */     public boolean addAll(long index, Collection<? extends Long> c) {
/* 483 */       ensureIndex(index);
/* 484 */       this.to += c.size();
/*     */ 
/* 490 */       return this.l.addAll(this.from + index, c);
/*     */     }
/*     */     public long getLong(long index) {
/* 493 */       ensureRestrictedIndex(index);
/* 494 */       return this.l.getLong(this.from + index);
/*     */     }
/*     */     public long removeLong(long index) {
/* 497 */       ensureRestrictedIndex(index);
/* 498 */       this.to -= 1L;
/* 499 */       return this.l.removeLong(this.from + index);
/*     */     }
/*     */     public long set(long index, long k) {
/* 502 */       ensureRestrictedIndex(index);
/* 503 */       return this.l.set(this.from + index, k);
/*     */     }
/*     */     public void clear() {
/* 506 */       removeElements(0L, size64());
/*     */     }
/*     */ 
/*     */     public long size64() {
/* 510 */       return this.to - this.from;
/*     */     }
/*     */     public void getElements(long from, long[][] a, long offset, long length) {
/* 513 */       ensureIndex(from);
/* 514 */       if (from + length > size64()) throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size64() + ")");
/* 515 */       this.l.getElements(this.from + from, a, offset, length);
/*     */     }
/*     */     public void removeElements(long from, long to) {
/* 518 */       ensureIndex(from);
/* 519 */       ensureIndex(to);
/* 520 */       this.l.removeElements(this.from + from, this.from + to);
/* 521 */       this.to -= to - from;
/*     */     }
/*     */ 
/*     */     public void addElements(long index, long[][] a, long offset, long length) {
/* 525 */       ensureIndex(index);
/* 526 */       this.l.addElements(this.from + index, a, offset, length);
/* 527 */       this.to += length;
/*     */     }
/*     */ 
/*     */     public LongBigListIterator listIterator(final long index) {
/* 531 */       ensureIndex(index);
/* 532 */       return new AbstractLongBigListIterator() {
/* 533 */         long pos = index; long last = -1L;
/*     */ 
/* 534 */         public boolean hasNext() { return this.pos < AbstractLongBigList.LongSubList.this.size64(); } 
/* 535 */         public boolean hasPrevious() { return this.pos > 0L; } 
/* 536 */         public long nextLong() { if (!hasNext()) throw new NoSuchElementException(); return AbstractLongBigList.LongSubList.this.l.getLong(AbstractLongBigList.LongSubList.this.from + (this.last = this.pos++)); } 
/* 537 */         public long previousLong() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractLongBigList.LongSubList.this.l.getLong(AbstractLongBigList.LongSubList.this.from + (this.last = --this.pos)); } 
/* 538 */         public long nextIndex() { return this.pos; } 
/* 539 */         public long previousIndex() { return this.pos - 1L; } 
/*     */         public void add(long k) {
/* 541 */           if (this.last == -1L) throw new IllegalStateException();
/* 542 */           AbstractLongBigList.LongSubList.this.add(this.pos++, k);
/* 543 */           this.last = -1L;
/*     */         }
/*     */ 
/*     */         public void set(long k) {
/* 547 */           if (this.last == -1L) throw new IllegalStateException();
/* 548 */           AbstractLongBigList.LongSubList.this.set(this.last, k);
/*     */         }
/*     */         public void remove() {
/* 551 */           if (this.last == -1L) throw new IllegalStateException();
/* 552 */           AbstractLongBigList.LongSubList.this.removeLong(this.last);
/*     */ 
/* 554 */           if (this.last < this.pos) this.pos -= 1L;
/* 555 */           this.last = -1L;
/*     */         }
/*     */       };
/*     */     }
/*     */ 
/*     */     public LongBigList subList(long from, long to) {
/* 561 */       ensureIndex(from);
/* 562 */       ensureIndex(to);
/* 563 */       if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 564 */       return new LongSubList(this, from, to);
/*     */     }
/*     */     public boolean rem(long k) {
/* 567 */       long index = indexOf(k);
/* 568 */       if (index == -1L) return false;
/* 569 */       this.to -= 1L;
/* 570 */       this.l.removeLong(this.from + index);
/*     */ 
/* 572 */       return true;
/*     */     }
/*     */     public boolean remove(Object o) {
/* 575 */       return rem(((Long)o).longValue());
/*     */     }
/*     */     public boolean addAll(long index, LongCollection c) {
/* 578 */       ensureIndex(index);
/* 579 */       this.to += c.size();
/*     */ 
/* 585 */       return this.l.addAll(this.from + index, c);
/*     */     }
/*     */     public boolean addAll(long index, LongList l) {
/* 588 */       ensureIndex(index);
/* 589 */       this.to += l.size();
/*     */ 
/* 595 */       return this.l.addAll(this.from + index, l);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongBigList
 * JD-Core Version:    0.6.2
 */