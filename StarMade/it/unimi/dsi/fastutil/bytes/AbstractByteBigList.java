/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.BigList;
/*     */ import it.unimi.dsi.fastutil.BigListIterator;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public abstract class AbstractByteBigList extends AbstractByteCollection
/*     */   implements ByteBigList, ByteStack
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
/*  72 */   public void add(long index, byte k) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public boolean add(byte k) {
/*  75 */     add(size64(), k);
/*  76 */     return true;
/*     */   }
/*     */   public byte removeByte(long i) {
/*  79 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public byte removeByte(int i) {
/*  82 */     return removeByte(i);
/*     */   }
/*     */   public byte set(long index, byte k) {
/*  85 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public byte set(int index, byte k) {
/*  88 */     return set(index, k);
/*     */   }
/*     */   public boolean addAll(long index, Collection<? extends Byte> c) {
/*  91 */     ensureIndex(index);
/*  92 */     int n = c.size();
/*  93 */     if (n == 0) return false;
/*  94 */     Iterator i = c.iterator();
/*  95 */     while (n-- != 0) add(index++, (Byte)i.next());
/*  96 */     return true;
/*     */   }
/*     */   public boolean addAll(int index, Collection<? extends Byte> c) {
/*  99 */     return addAll(index, c);
/*     */   }
/*     */ 
/*     */   public boolean addAll(Collection<? extends Byte> c) {
/* 103 */     return addAll(size64(), c);
/*     */   }
/*     */   public ByteBigListIterator iterator() {
/* 106 */     return listIterator();
/*     */   }
/*     */   public ByteBigListIterator listIterator() {
/* 109 */     return listIterator(0L);
/*     */   }
/*     */   public ByteBigListIterator listIterator(final long index) {
/* 112 */     return new AbstractByteBigListIterator() {
/* 113 */       long pos = index; long last = -1L;
/*     */ 
/* 114 */       public boolean hasNext() { return this.pos < AbstractByteBigList.this.size64(); } 
/* 115 */       public boolean hasPrevious() { return this.pos > 0L; } 
/* 116 */       public byte nextByte() { if (!hasNext()) throw new NoSuchElementException(); return AbstractByteBigList.this.getByte(this.last = this.pos++); } 
/* 117 */       public byte previousByte() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractByteBigList.this.getByte(this.last = --this.pos); } 
/* 118 */       public long nextIndex() { return this.pos; } 
/* 119 */       public long previousIndex() { return this.pos - 1L; } 
/*     */       public void add(byte k) {
/* 121 */         if (this.last == -1L) throw new IllegalStateException();
/* 122 */         AbstractByteBigList.this.add(this.pos++, k);
/* 123 */         this.last = -1L;
/*     */       }
/*     */       public void set(byte k) {
/* 126 */         if (this.last == -1L) throw new IllegalStateException();
/* 127 */         AbstractByteBigList.this.set(this.last, k);
/*     */       }
/*     */       public void remove() {
/* 130 */         if (this.last == -1L) throw new IllegalStateException();
/* 131 */         AbstractByteBigList.this.removeByte(this.last);
/*     */ 
/* 133 */         if (this.last < this.pos) this.pos -= 1L;
/* 134 */         this.last = -1L;
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ByteBigListIterator listIterator(int index)
/*     */   {
/* 141 */     return listIterator(index);
/*     */   }
/*     */ 
/*     */   public boolean contains(byte k)
/*     */   {
/* 146 */     return indexOf(k) >= 0L;
/*     */   }
/*     */ 
/*     */   public long indexOf(byte k) {
/* 150 */     ByteBigListIterator i = listIterator();
/*     */ 
/* 152 */     while (i.hasNext()) {
/* 153 */       byte e = i.nextByte();
/* 154 */       if (k == e) return i.previousIndex();
/*     */     }
/* 156 */     return -1L;
/*     */   }
/*     */ 
/*     */   public long lastIndexOf(byte k) {
/* 160 */     ByteBigListIterator i = listIterator(size64());
/*     */ 
/* 162 */     while (i.hasPrevious()) {
/* 163 */       byte e = i.previousByte();
/* 164 */       if (k == e) return i.nextIndex();
/*     */     }
/* 166 */     return -1L;
/*     */   }
/*     */ 
/*     */   public void size(long size) {
/* 170 */     long i = size64();
/* 171 */     for (size <= i; i++ < size; add((byte)0));
/* 172 */     while (i-- != size) remove(i); 
/*     */   }
/*     */ 
/*     */   public void size(int size)
/*     */   {
/* 176 */     size(size);
/*     */   }
/*     */ 
/*     */   public ByteBigList subList(long from, long to) {
/* 180 */     ensureIndex(from);
/* 181 */     ensureIndex(to);
/* 182 */     if (from > to) throw new IndexOutOfBoundsException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/*     */ 
/* 184 */     return new ByteSubList(this, from, to);
/*     */   }
/*     */ 
/*     */   public void removeElements(long from, long to)
/*     */   {
/* 197 */     ensureIndex(to);
/* 198 */     ByteBigListIterator i = listIterator(from);
/* 199 */     long n = to - from;
/* 200 */     if (n < 0L) throw new IllegalArgumentException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 201 */     while (n-- != 0L) {
/* 202 */       i.nextByte();
/* 203 */       i.remove();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addElements(long index, byte[][] a, long offset, long length)
/*     */   {
/* 219 */     ensureIndex(index);
/* 220 */     ByteBigArrays.ensureOffsetLength(a, offset, length);
/* 221 */     while (length-- != 0L) add(index++, ByteBigArrays.get(a, offset++)); 
/*     */   }
/*     */ 
/*     */   public void addElements(long index, byte[][] a)
/*     */   {
/* 225 */     addElements(index, a, 0L, ByteBigArrays.length(a));
/*     */   }
/*     */ 
/*     */   public void getElements(long from, byte[][] a, long offset, long length)
/*     */   {
/* 240 */     ByteBigListIterator i = listIterator(from);
/* 241 */     ByteBigArrays.ensureOffsetLength(a, offset, length);
/* 242 */     if (from + length > size64()) throw new IndexOutOfBoundsException(new StringBuilder().append("End index (").append(from + length).append(") is greater than list size (").append(size64()).append(")").toString());
/* 243 */     while (length-- != 0L) ByteBigArrays.set(a, offset++, i.nextByte()); 
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
/*     */   public int compareTo(BigList<? extends Byte> l)
/*     */   {
/* 289 */     if (l == this) return 0;
/*     */ 
/* 291 */     if ((l instanceof ByteStack))
/*     */     {
/* 293 */       ByteBigListIterator i1 = listIterator(); ByteBigListIterator i2 = ((ByteStack)l).listIterator();
/*     */ 
/* 297 */       while ((i1.hasNext()) && (i2.hasNext())) {
/* 298 */         byte e1 = i1.nextByte();
/* 299 */         byte e2 = i2.nextByte();
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
/* 320 */     ByteIterator i = iterator();
/* 321 */     int h = 1;
/* 322 */     long s = size64();
/* 323 */     while (s-- != 0L) {
/* 324 */       byte k = i.nextByte();
/* 325 */       h = 31 * h + k;
/*     */     }
/* 327 */     return h;
/*     */   }
/*     */ 
/*     */   public void push(byte o) {
/* 331 */     add(o);
/*     */   }
/*     */ 
/*     */   public byte popByte() {
/* 335 */     if (isEmpty()) throw new NoSuchElementException();
/* 336 */     return removeByte(size64() - 1L);
/*     */   }
/*     */ 
/*     */   public byte topByte() {
/* 340 */     if (isEmpty()) throw new NoSuchElementException();
/* 341 */     return getByte(size64() - 1L);
/*     */   }
/*     */ 
/*     */   public byte peekByte(int i) {
/* 345 */     return getByte(size64() - 1L - i);
/*     */   }
/*     */ 
/*     */   public byte getByte(int index)
/*     */   {
/* 351 */     return getByte(index);
/*     */   }
/*     */ 
/*     */   public boolean rem(byte k) {
/* 355 */     long index = indexOf(k);
/* 356 */     if (index == -1L) return false;
/* 357 */     removeByte(index);
/* 358 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean addAll(long index, ByteCollection c)
/*     */   {
/* 363 */     return addAll(index, c);
/*     */   }
/*     */ 
/*     */   public boolean addAll(long index, ByteBigList l)
/*     */   {
/* 368 */     return addAll(index, l);
/*     */   }
/*     */ 
/*     */   public boolean addAll(ByteCollection c) {
/* 372 */     return addAll(size64(), c);
/*     */   }
/*     */ 
/*     */   public boolean addAll(ByteBigList l) {
/* 376 */     return addAll(size64(), l);
/*     */   }
/*     */ 
/*     */   public void add(long index, Byte ok)
/*     */   {
/* 381 */     add(index, ok.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte set(long index, Byte ok)
/*     */   {
/* 386 */     return Byte.valueOf(set(index, ok.byteValue()));
/*     */   }
/*     */ 
/*     */   public Byte get(long index)
/*     */   {
/* 391 */     return Byte.valueOf(getByte(index));
/*     */   }
/*     */ 
/*     */   public long indexOf(Object ok)
/*     */   {
/* 396 */     return indexOf(((Byte)ok).byteValue());
/*     */   }
/*     */ 
/*     */   public long lastIndexOf(Object ok)
/*     */   {
/* 401 */     return lastIndexOf(((Byte)ok).byteValue());
/*     */   }
/*     */ 
/*     */   public Byte remove(int index)
/*     */   {
/* 406 */     return Byte.valueOf(removeByte(index));
/*     */   }
/*     */ 
/*     */   public Byte remove(long index)
/*     */   {
/* 411 */     return Byte.valueOf(removeByte(index));
/*     */   }
/*     */ 
/*     */   public void push(Byte o)
/*     */   {
/* 416 */     push(o.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte pop()
/*     */   {
/* 421 */     return Byte.valueOf(popByte());
/*     */   }
/*     */ 
/*     */   public Byte top()
/*     */   {
/* 426 */     return Byte.valueOf(topByte());
/*     */   }
/*     */ 
/*     */   public Byte peek(int i)
/*     */   {
/* 431 */     return Byte.valueOf(peekByte(i));
/*     */   }
/*     */   public String toString() {
/* 434 */     StringBuilder s = new StringBuilder();
/* 435 */     ByteIterator i = iterator();
/* 436 */     long n = size64();
/*     */ 
/* 438 */     boolean first = true;
/* 439 */     s.append("[");
/* 440 */     while (n-- != 0L) {
/* 441 */       if (first) first = false; else
/* 442 */         s.append(", ");
/* 443 */       byte k = i.nextByte();
/* 444 */       s.append(String.valueOf(k));
/*     */     }
/* 446 */     s.append("]");
/* 447 */     return s.toString();
/*     */   }
/*     */   public static class ByteSubList extends AbstractByteBigList implements Serializable { public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ByteBigList l;
/*     */     protected final long from;
/*     */     protected long to;
/*     */     private static final boolean ASSERTS = false;
/*     */ 
/* 459 */     public ByteSubList(ByteBigList l, long from, long to) { this.l = l;
/* 460 */       this.from = from;
/* 461 */       this.to = to;
/*     */     }
/*     */ 
/*     */     private void assertRange()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean add(byte k)
/*     */     {
/* 471 */       this.l.add(this.to, k);
/* 472 */       this.to += 1L;
/*     */ 
/* 474 */       return true;
/*     */     }
/*     */     public void add(long index, byte k) {
/* 477 */       ensureIndex(index);
/* 478 */       this.l.add(this.from + index, k);
/* 479 */       this.to += 1L;
/*     */     }
/*     */ 
/*     */     public boolean addAll(long index, Collection<? extends Byte> c) {
/* 483 */       ensureIndex(index);
/* 484 */       this.to += c.size();
/*     */ 
/* 490 */       return this.l.addAll(this.from + index, c);
/*     */     }
/*     */     public byte getByte(long index) {
/* 493 */       ensureRestrictedIndex(index);
/* 494 */       return this.l.getByte(this.from + index);
/*     */     }
/*     */     public byte removeByte(long index) {
/* 497 */       ensureRestrictedIndex(index);
/* 498 */       this.to -= 1L;
/* 499 */       return this.l.removeByte(this.from + index);
/*     */     }
/*     */     public byte set(long index, byte k) {
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
/*     */     public void getElements(long from, byte[][] a, long offset, long length) {
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
/*     */     public void addElements(long index, byte[][] a, long offset, long length) {
/* 525 */       ensureIndex(index);
/* 526 */       this.l.addElements(this.from + index, a, offset, length);
/* 527 */       this.to += length;
/*     */     }
/*     */ 
/*     */     public ByteBigListIterator listIterator(final long index) {
/* 531 */       ensureIndex(index);
/* 532 */       return new AbstractByteBigListIterator() {
/* 533 */         long pos = index; long last = -1L;
/*     */ 
/* 534 */         public boolean hasNext() { return this.pos < AbstractByteBigList.ByteSubList.this.size64(); } 
/* 535 */         public boolean hasPrevious() { return this.pos > 0L; } 
/* 536 */         public byte nextByte() { if (!hasNext()) throw new NoSuchElementException(); return AbstractByteBigList.ByteSubList.this.l.getByte(AbstractByteBigList.ByteSubList.this.from + (this.last = this.pos++)); } 
/* 537 */         public byte previousByte() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractByteBigList.ByteSubList.this.l.getByte(AbstractByteBigList.ByteSubList.this.from + (this.last = --this.pos)); } 
/* 538 */         public long nextIndex() { return this.pos; } 
/* 539 */         public long previousIndex() { return this.pos - 1L; } 
/*     */         public void add(byte k) {
/* 541 */           if (this.last == -1L) throw new IllegalStateException();
/* 542 */           AbstractByteBigList.ByteSubList.this.add(this.pos++, k);
/* 543 */           this.last = -1L;
/*     */         }
/*     */ 
/*     */         public void set(byte k) {
/* 547 */           if (this.last == -1L) throw new IllegalStateException();
/* 548 */           AbstractByteBigList.ByteSubList.this.set(this.last, k);
/*     */         }
/*     */         public void remove() {
/* 551 */           if (this.last == -1L) throw new IllegalStateException();
/* 552 */           AbstractByteBigList.ByteSubList.this.removeByte(this.last);
/*     */ 
/* 554 */           if (this.last < this.pos) this.pos -= 1L;
/* 555 */           this.last = -1L;
/*     */         }
/*     */       };
/*     */     }
/*     */ 
/*     */     public ByteBigList subList(long from, long to) {
/* 561 */       ensureIndex(from);
/* 562 */       ensureIndex(to);
/* 563 */       if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 564 */       return new ByteSubList(this, from, to);
/*     */     }
/*     */     public boolean rem(byte k) {
/* 567 */       long index = indexOf(k);
/* 568 */       if (index == -1L) return false;
/* 569 */       this.to -= 1L;
/* 570 */       this.l.removeByte(this.from + index);
/*     */ 
/* 572 */       return true;
/*     */     }
/*     */     public boolean remove(Object o) {
/* 575 */       return rem(((Byte)o).byteValue());
/*     */     }
/*     */     public boolean addAll(long index, ByteCollection c) {
/* 578 */       ensureIndex(index);
/* 579 */       this.to += c.size();
/*     */ 
/* 585 */       return this.l.addAll(this.from + index, c);
/*     */     }
/*     */     public boolean addAll(long index, ByteList l) {
/* 588 */       ensureIndex(index);
/* 589 */       this.to += l.size();
/*     */ 
/* 595 */       return this.l.addAll(this.from + index, l);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteBigList
 * JD-Core Version:    0.6.2
 */