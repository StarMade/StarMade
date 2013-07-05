/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public abstract class AbstractByteList extends AbstractByteCollection
/*     */   implements ByteList, ByteStack
/*     */ {
/*     */   protected void ensureIndex(int index)
/*     */   {
/*  62 */     if (index < 0) throw new IndexOutOfBoundsException(new StringBuilder().append("Index (").append(index).append(") is negative").toString());
/*  63 */     if (index > size()) throw new IndexOutOfBoundsException(new StringBuilder().append("Index (").append(index).append(") is greater than list size (").append(size()).append(")").toString());
/*     */   }
/*     */ 
/*     */   protected void ensureRestrictedIndex(int index)
/*     */   {
/*  71 */     if (index < 0) throw new IndexOutOfBoundsException(new StringBuilder().append("Index (").append(index).append(") is negative").toString());
/*  72 */     if (index >= size()) throw new IndexOutOfBoundsException(new StringBuilder().append("Index (").append(index).append(") is greater than or equal to list size (").append(size()).append(")").toString()); 
/*     */   }
/*     */ 
/*  75 */   public void add(int index, byte k) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public boolean add(byte k) {
/*  78 */     add(size(), k);
/*  79 */     return true;
/*     */   }
/*     */   public byte removeByte(int i) {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public byte set(int index, byte k) {
/*  85 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean addAll(int index, Collection<? extends Byte> c) {
/*  88 */     ensureIndex(index);
/*  89 */     int n = c.size();
/*  90 */     if (n == 0) return false;
/*  91 */     Iterator i = c.iterator();
/*  92 */     while (n-- != 0) add(index++, (Byte)i.next());
/*  93 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean addAll(Collection<? extends Byte> c) {
/*  97 */     return addAll(size(), c);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public ByteListIterator byteListIterator() {
/* 102 */     return listIterator();
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public ByteListIterator byteListIterator(int index) {
/* 107 */     return listIterator(index);
/*     */   }
/*     */   public ByteListIterator iterator() {
/* 110 */     return listIterator();
/*     */   }
/*     */   public ByteListIterator listIterator() {
/* 113 */     return listIterator(0);
/*     */   }
/*     */   public ByteListIterator listIterator(final int index) {
/* 116 */     return new AbstractByteListIterator() {
/* 117 */       int pos = index; int last = -1;
/*     */ 
/* 118 */       public boolean hasNext() { return this.pos < AbstractByteList.this.size(); } 
/* 119 */       public boolean hasPrevious() { return this.pos > 0; } 
/* 120 */       public byte nextByte() { if (!hasNext()) throw new NoSuchElementException(); return AbstractByteList.this.getByte(this.last = this.pos++); } 
/* 121 */       public byte previousByte() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractByteList.this.getByte(this.last = --this.pos); } 
/* 122 */       public int nextIndex() { return this.pos; } 
/* 123 */       public int previousIndex() { return this.pos - 1; } 
/*     */       public void add(byte k) {
/* 125 */         if (this.last == -1) throw new IllegalStateException();
/* 126 */         AbstractByteList.this.add(this.pos++, k);
/* 127 */         this.last = -1;
/*     */       }
/*     */       public void set(byte k) {
/* 130 */         if (this.last == -1) throw new IllegalStateException();
/* 131 */         AbstractByteList.this.set(this.last, k);
/*     */       }
/*     */       public void remove() {
/* 134 */         if (this.last == -1) throw new IllegalStateException();
/* 135 */         AbstractByteList.this.removeByte(this.last);
/*     */ 
/* 137 */         if (this.last < this.pos) this.pos -= 1;
/* 138 */         this.last = -1;
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public boolean contains(byte k)
/*     */   {
/* 146 */     return indexOf(k) >= 0;
/*     */   }
/*     */ 
/*     */   public int indexOf(byte k) {
/* 150 */     ByteListIterator i = listIterator();
/*     */ 
/* 152 */     while (i.hasNext()) {
/* 153 */       byte e = i.nextByte();
/* 154 */       if (k == e) return i.previousIndex();
/*     */     }
/* 156 */     return -1;
/*     */   }
/*     */ 
/*     */   public int lastIndexOf(byte k) {
/* 160 */     ByteListIterator i = listIterator(size());
/*     */ 
/* 162 */     while (i.hasPrevious()) {
/* 163 */       byte e = i.previousByte();
/* 164 */       if (k == e) return i.nextIndex();
/*     */     }
/* 166 */     return -1;
/*     */   }
/*     */ 
/*     */   public void size(int size) {
/* 170 */     int i = size();
/* 171 */     for (size <= i; i++ < size; add((byte)0));
/* 172 */     while (i-- != size) remove(i);
/*     */   }
/*     */ 
/*     */   public ByteList subList(int from, int to)
/*     */   {
/* 177 */     ensureIndex(from);
/* 178 */     ensureIndex(to);
/* 179 */     if (from > to) throw new IndexOutOfBoundsException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/*     */ 
/* 181 */     return new ByteSubList(this, from, to);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public ByteList byteSubList(int from, int to)
/*     */   {
/* 188 */     return subList(from, to);
/*     */   }
/*     */ 
/*     */   public void removeElements(int from, int to)
/*     */   {
/* 202 */     ensureIndex(to);
/* 203 */     ByteListIterator i = listIterator(from);
/* 204 */     int n = to - from;
/* 205 */     if (n < 0) throw new IllegalArgumentException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 206 */     while (n-- != 0) {
/* 207 */       i.nextByte();
/* 208 */       i.remove();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addElements(int index, byte[] a, int offset, int length)
/*     */   {
/* 224 */     ensureIndex(index);
/* 225 */     if (offset < 0) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("Offset (").append(offset).append(") is negative").toString());
/* 226 */     if (offset + length > a.length) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("End index (").append(offset + length).append(") is greater than array length (").append(a.length).append(")").toString());
/* 227 */     while (length-- != 0) add(index++, a[(offset++)]); 
/*     */   }
/*     */ 
/*     */   public void addElements(int index, byte[] a)
/*     */   {
/* 231 */     addElements(index, a, 0, a.length);
/*     */   }
/*     */ 
/*     */   public void getElements(int from, byte[] a, int offset, int length)
/*     */   {
/* 246 */     ByteListIterator i = listIterator(from);
/* 247 */     if (offset < 0) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("Offset (").append(offset).append(") is negative").toString());
/* 248 */     if (offset + length > a.length) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("End index (").append(offset + length).append(") is greater than array length (").append(a.length).append(")").toString());
/* 249 */     if (from + length > size()) throw new IndexOutOfBoundsException(new StringBuilder().append("End index (").append(from + length).append(") is greater than list size (").append(size()).append(")").toString());
/* 250 */     while (length-- != 0) a[(offset++)] = i.nextByte();
/*     */   }
/*     */ 
/*     */   private boolean valEquals(Object a, Object b)
/*     */   {
/* 255 */     return a == null ? false : b == null ? true : a.equals(b);
/*     */   }
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 260 */     if (o == this) return true;
/* 261 */     if (!(o instanceof List)) return false;
/* 262 */     List l = (List)o;
/* 263 */     int s = size();
/* 264 */     if (s != l.size()) return false;
/*     */ 
/* 266 */     ListIterator i1 = listIterator(); ListIterator i2 = l.listIterator();
/*     */ 
/* 271 */     while (s-- != 0) if (!valEquals(i1.next(), i2.next())) return false;
/*     */ 
/* 273 */     return true;
/*     */   }
/*     */ 
/*     */   public int compareTo(List<? extends Byte> l)
/*     */   {
/* 290 */     if (l == this) return 0;
/*     */ 
/* 292 */     if ((l instanceof ByteStack))
/*     */     {
/* 294 */       ByteListIterator i1 = listIterator(); ByteListIterator i2 = ((ByteStack)l).listIterator();
/*     */ 
/* 298 */       while ((i1.hasNext()) && (i2.hasNext())) {
/* 299 */         byte e1 = i1.nextByte();
/* 300 */         byte e2 = i2.nextByte();
/*     */         int r;
/* 301 */         if ((r = e1 == e2 ? 0 : e1 < e2 ? -1 : 1) != 0) return r;
/*     */       }
/* 303 */       return i1.hasNext() ? 1 : i2.hasNext() ? -1 : 0;
/*     */     }
/*     */ 
/* 306 */     ListIterator i1 = listIterator(); ListIterator i2 = l.listIterator();
/*     */ 
/* 309 */     while ((i1.hasNext()) && (i2.hasNext()))
/*     */     {
/* 310 */       int r;
/* 310 */       if ((r = ((Comparable)i1.next()).compareTo(i2.next())) != 0) return r;
/*     */     }
/* 312 */     return i1.hasNext() ? 1 : i2.hasNext() ? -1 : 0;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 321 */     ByteIterator i = iterator();
/* 322 */     int h = 1; int s = size();
/* 323 */     while (s-- != 0) {
/* 324 */       byte k = i.nextByte();
/* 325 */       h = 31 * h + k;
/*     */     }
/* 327 */     return h;
/*     */   }
/*     */ 
/*     */   public void push(byte o)
/*     */   {
/* 332 */     add(o);
/*     */   }
/*     */ 
/*     */   public byte popByte() {
/* 336 */     if (isEmpty()) throw new NoSuchElementException();
/* 337 */     return removeByte(size() - 1);
/*     */   }
/*     */ 
/*     */   public byte topByte() {
/* 341 */     if (isEmpty()) throw new NoSuchElementException();
/* 342 */     return getByte(size() - 1);
/*     */   }
/*     */ 
/*     */   public byte peekByte(int i) {
/* 346 */     return getByte(size() - 1 - i);
/*     */   }
/*     */ 
/*     */   public boolean rem(byte k)
/*     */   {
/* 352 */     int index = indexOf(k);
/* 353 */     if (index == -1) return false;
/* 354 */     removeByte(index);
/* 355 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean remove(Object o)
/*     */   {
/* 360 */     return rem(((Byte)o).byteValue());
/*     */   }
/*     */ 
/*     */   public boolean addAll(int index, ByteCollection c)
/*     */   {
/* 365 */     return addAll(index, c);
/*     */   }
/*     */ 
/*     */   public boolean addAll(int index, ByteList l)
/*     */   {
/* 370 */     return addAll(index, l);
/*     */   }
/*     */ 
/*     */   public boolean addAll(ByteCollection c) {
/* 374 */     return addAll(size(), c);
/*     */   }
/*     */ 
/*     */   public boolean addAll(ByteList l) {
/* 378 */     return addAll(size(), l);
/*     */   }
/*     */ 
/*     */   public void add(int index, Byte ok)
/*     */   {
/* 383 */     add(index, ok.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte set(int index, Byte ok)
/*     */   {
/* 388 */     return Byte.valueOf(set(index, ok.byteValue()));
/*     */   }
/*     */ 
/*     */   public Byte get(int index)
/*     */   {
/* 393 */     return Byte.valueOf(getByte(index));
/*     */   }
/*     */ 
/*     */   public int indexOf(Object ok)
/*     */   {
/* 398 */     return indexOf(((Byte)ok).byteValue());
/*     */   }
/*     */ 
/*     */   public int lastIndexOf(Object ok)
/*     */   {
/* 403 */     return lastIndexOf(((Byte)ok).byteValue());
/*     */   }
/*     */ 
/*     */   public Byte remove(int index)
/*     */   {
/* 408 */     return Byte.valueOf(removeByte(index));
/*     */   }
/*     */ 
/*     */   public void push(Byte o)
/*     */   {
/* 413 */     push(o.byteValue());
/*     */   }
/*     */ 
/*     */   public Byte pop()
/*     */   {
/* 418 */     return Byte.valueOf(popByte());
/*     */   }
/*     */ 
/*     */   public Byte top()
/*     */   {
/* 423 */     return Byte.valueOf(topByte());
/*     */   }
/*     */ 
/*     */   public Byte peek(int i)
/*     */   {
/* 428 */     return Byte.valueOf(peekByte(i));
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 435 */     StringBuilder s = new StringBuilder();
/* 436 */     ByteIterator i = iterator();
/* 437 */     int n = size();
/*     */ 
/* 439 */     boolean first = true;
/*     */ 
/* 441 */     s.append("[");
/*     */ 
/* 443 */     while (n-- != 0) {
/* 444 */       if (first) first = false; else
/* 445 */         s.append(", ");
/* 446 */       byte k = i.nextByte();
/*     */ 
/* 450 */       s.append(String.valueOf(k));
/*     */     }
/*     */ 
/* 453 */     s.append("]");
/* 454 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class ByteSubList extends AbstractByteList implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ByteList l;
/*     */     protected final int from;
/*     */     protected int to;
/*     */     private static final boolean ASSERTS = false;
/*     */ 
/*     */     public ByteSubList(ByteList l, int from, int to) {
/* 470 */       this.l = l;
/* 471 */       this.from = from;
/* 472 */       this.to = to;
/*     */     }
/*     */ 
/*     */     private void assertRange()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean add(byte k)
/*     */     {
/* 484 */       this.l.add(this.to, k);
/* 485 */       this.to += 1;
/*     */ 
/* 487 */       return true;
/*     */     }
/*     */ 
/*     */     public void add(int index, byte k) {
/* 491 */       ensureIndex(index);
/* 492 */       this.l.add(this.from + index, k);
/* 493 */       this.to += 1;
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, Collection<? extends Byte> c)
/*     */     {
/* 498 */       ensureIndex(index);
/* 499 */       this.to += c.size();
/*     */ 
/* 505 */       return this.l.addAll(this.from + index, c);
/*     */     }
/*     */ 
/*     */     public byte getByte(int index) {
/* 509 */       ensureRestrictedIndex(index);
/* 510 */       return this.l.getByte(this.from + index);
/*     */     }
/*     */ 
/*     */     public byte removeByte(int index) {
/* 514 */       ensureRestrictedIndex(index);
/* 515 */       this.to -= 1;
/* 516 */       return this.l.removeByte(this.from + index);
/*     */     }
/*     */ 
/*     */     public byte set(int index, byte k) {
/* 520 */       ensureRestrictedIndex(index);
/* 521 */       return this.l.set(this.from + index, k);
/*     */     }
/*     */ 
/*     */     public void clear() {
/* 525 */       removeElements(0, size());
/*     */     }
/*     */ 
/*     */     public int size()
/*     */     {
/* 530 */       return this.to - this.from;
/*     */     }
/*     */ 
/*     */     public void getElements(int from, byte[] a, int offset, int length) {
/* 534 */       ensureIndex(from);
/* 535 */       if (from + length > size()) throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size() + ")");
/* 536 */       this.l.getElements(this.from + from, a, offset, length);
/*     */     }
/*     */ 
/*     */     public void removeElements(int from, int to) {
/* 540 */       ensureIndex(from);
/* 541 */       ensureIndex(to);
/* 542 */       this.l.removeElements(this.from + from, this.from + to);
/* 543 */       this.to -= to - from;
/*     */     }
/*     */ 
/*     */     public void addElements(int index, byte[] a, int offset, int length)
/*     */     {
/* 548 */       ensureIndex(index);
/* 549 */       this.l.addElements(this.from + index, a, offset, length);
/* 550 */       this.to += length;
/*     */     }
/*     */ 
/*     */     public ByteListIterator listIterator(final int index)
/*     */     {
/* 555 */       ensureIndex(index);
/*     */ 
/* 557 */       return new AbstractByteListIterator() {
/* 558 */         int pos = index; int last = -1;
/*     */ 
/* 560 */         public boolean hasNext() { return this.pos < AbstractByteList.ByteSubList.this.size(); } 
/* 561 */         public boolean hasPrevious() { return this.pos > 0; } 
/* 562 */         public byte nextByte() { if (!hasNext()) throw new NoSuchElementException(); return AbstractByteList.ByteSubList.this.l.getByte(AbstractByteList.ByteSubList.this.from + (this.last = this.pos++)); } 
/* 563 */         public byte previousByte() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractByteList.ByteSubList.this.l.getByte(AbstractByteList.ByteSubList.this.from + (this.last = --this.pos)); } 
/* 564 */         public int nextIndex() { return this.pos; } 
/* 565 */         public int previousIndex() { return this.pos - 1; } 
/*     */         public void add(byte k) {
/* 567 */           if (this.last == -1) throw new IllegalStateException();
/* 568 */           AbstractByteList.ByteSubList.this.add(this.pos++, k);
/* 569 */           this.last = -1;
/*     */         }
/*     */ 
/*     */         public void set(byte k) {
/* 573 */           if (this.last == -1) throw new IllegalStateException();
/* 574 */           AbstractByteList.ByteSubList.this.set(this.last, k);
/*     */         }
/*     */         public void remove() {
/* 577 */           if (this.last == -1) throw new IllegalStateException();
/* 578 */           AbstractByteList.ByteSubList.this.removeByte(this.last);
/*     */ 
/* 580 */           if (this.last < this.pos) this.pos -= 1;
/* 581 */           this.last = -1;
/*     */         }
/*     */       };
/*     */     }
/*     */ 
/*     */     public ByteList subList(int from, int to)
/*     */     {
/* 588 */       ensureIndex(from);
/* 589 */       ensureIndex(to);
/* 590 */       if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/*     */ 
/* 592 */       return new ByteSubList(this, from, to);
/*     */     }
/*     */ 
/*     */     public boolean rem(byte k)
/*     */     {
/* 598 */       int index = indexOf(k);
/* 599 */       if (index == -1) return false;
/* 600 */       this.to -= 1;
/* 601 */       this.l.removeByte(this.from + index);
/*     */ 
/* 603 */       return true;
/*     */     }
/*     */ 
/*     */     public boolean remove(Object o) {
/* 607 */       return rem(((Byte)o).byteValue());
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, ByteCollection c) {
/* 611 */       ensureIndex(index);
/* 612 */       this.to += c.size();
/*     */ 
/* 618 */       return this.l.addAll(this.from + index, c);
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, ByteList l) {
/* 622 */       ensureIndex(index);
/* 623 */       this.to += l.size();
/*     */ 
/* 629 */       return this.l.addAll(this.from + index, l);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteList
 * JD-Core Version:    0.6.2
 */