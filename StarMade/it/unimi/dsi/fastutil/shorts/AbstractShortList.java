/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public abstract class AbstractShortList extends AbstractShortCollection
/*     */   implements ShortList, ShortStack
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
/*  75 */   public void add(int index, short k) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public boolean add(short k) {
/*  78 */     add(size(), k);
/*  79 */     return true;
/*     */   }
/*     */   public short removeShort(int i) {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public short set(int index, short k) {
/*  85 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean addAll(int index, Collection<? extends Short> c) {
/*  88 */     ensureIndex(index);
/*  89 */     int n = c.size();
/*  90 */     if (n == 0) return false;
/*  91 */     Iterator i = c.iterator();
/*  92 */     while (n-- != 0) add(index++, (Short)i.next());
/*  93 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean addAll(Collection<? extends Short> c) {
/*  97 */     return addAll(size(), c);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public ShortListIterator shortListIterator() {
/* 102 */     return listIterator();
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public ShortListIterator shortListIterator(int index) {
/* 107 */     return listIterator(index);
/*     */   }
/*     */   public ShortListIterator iterator() {
/* 110 */     return listIterator();
/*     */   }
/*     */   public ShortListIterator listIterator() {
/* 113 */     return listIterator(0);
/*     */   }
/*     */   public ShortListIterator listIterator(final int index) {
/* 116 */     return new AbstractShortListIterator() {
/* 117 */       int pos = index; int last = -1;
/*     */ 
/* 118 */       public boolean hasNext() { return this.pos < AbstractShortList.this.size(); } 
/* 119 */       public boolean hasPrevious() { return this.pos > 0; } 
/* 120 */       public short nextShort() { if (!hasNext()) throw new NoSuchElementException(); return AbstractShortList.this.getShort(this.last = this.pos++); } 
/* 121 */       public short previousShort() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractShortList.this.getShort(this.last = --this.pos); } 
/* 122 */       public int nextIndex() { return this.pos; } 
/* 123 */       public int previousIndex() { return this.pos - 1; } 
/*     */       public void add(short k) {
/* 125 */         if (this.last == -1) throw new IllegalStateException();
/* 126 */         AbstractShortList.this.add(this.pos++, k);
/* 127 */         this.last = -1;
/*     */       }
/*     */       public void set(short k) {
/* 130 */         if (this.last == -1) throw new IllegalStateException();
/* 131 */         AbstractShortList.this.set(this.last, k);
/*     */       }
/*     */       public void remove() {
/* 134 */         if (this.last == -1) throw new IllegalStateException();
/* 135 */         AbstractShortList.this.removeShort(this.last);
/*     */ 
/* 137 */         if (this.last < this.pos) this.pos -= 1;
/* 138 */         this.last = -1;
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public boolean contains(short k)
/*     */   {
/* 146 */     return indexOf(k) >= 0;
/*     */   }
/*     */ 
/*     */   public int indexOf(short k) {
/* 150 */     ShortListIterator i = listIterator();
/*     */ 
/* 152 */     while (i.hasNext()) {
/* 153 */       short e = i.nextShort();
/* 154 */       if (k == e) return i.previousIndex();
/*     */     }
/* 156 */     return -1;
/*     */   }
/*     */ 
/*     */   public int lastIndexOf(short k) {
/* 160 */     ShortListIterator i = listIterator(size());
/*     */ 
/* 162 */     while (i.hasPrevious()) {
/* 163 */       short e = i.previousShort();
/* 164 */       if (k == e) return i.nextIndex();
/*     */     }
/* 166 */     return -1;
/*     */   }
/*     */ 
/*     */   public void size(int size) {
/* 170 */     int i = size();
/* 171 */     for (size <= i; i++ < size; add((short)0));
/* 172 */     while (i-- != size) remove(i);
/*     */   }
/*     */ 
/*     */   public ShortList subList(int from, int to)
/*     */   {
/* 177 */     ensureIndex(from);
/* 178 */     ensureIndex(to);
/* 179 */     if (from > to) throw new IndexOutOfBoundsException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/*     */ 
/* 181 */     return new ShortSubList(this, from, to);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public ShortList shortSubList(int from, int to)
/*     */   {
/* 188 */     return subList(from, to);
/*     */   }
/*     */ 
/*     */   public void removeElements(int from, int to)
/*     */   {
/* 202 */     ensureIndex(to);
/* 203 */     ShortListIterator i = listIterator(from);
/* 204 */     int n = to - from;
/* 205 */     if (n < 0) throw new IllegalArgumentException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 206 */     while (n-- != 0) {
/* 207 */       i.nextShort();
/* 208 */       i.remove();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addElements(int index, short[] a, int offset, int length)
/*     */   {
/* 224 */     ensureIndex(index);
/* 225 */     if (offset < 0) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("Offset (").append(offset).append(") is negative").toString());
/* 226 */     if (offset + length > a.length) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("End index (").append(offset + length).append(") is greater than array length (").append(a.length).append(")").toString());
/* 227 */     while (length-- != 0) add(index++, a[(offset++)]); 
/*     */   }
/*     */ 
/*     */   public void addElements(int index, short[] a)
/*     */   {
/* 231 */     addElements(index, a, 0, a.length);
/*     */   }
/*     */ 
/*     */   public void getElements(int from, short[] a, int offset, int length)
/*     */   {
/* 246 */     ShortListIterator i = listIterator(from);
/* 247 */     if (offset < 0) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("Offset (").append(offset).append(") is negative").toString());
/* 248 */     if (offset + length > a.length) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("End index (").append(offset + length).append(") is greater than array length (").append(a.length).append(")").toString());
/* 249 */     if (from + length > size()) throw new IndexOutOfBoundsException(new StringBuilder().append("End index (").append(from + length).append(") is greater than list size (").append(size()).append(")").toString());
/* 250 */     while (length-- != 0) a[(offset++)] = i.nextShort();
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
/*     */   public int compareTo(List<? extends Short> l)
/*     */   {
/* 290 */     if (l == this) return 0;
/*     */ 
/* 292 */     if ((l instanceof ShortStack))
/*     */     {
/* 294 */       ShortListIterator i1 = listIterator(); ShortListIterator i2 = ((ShortStack)l).listIterator();
/*     */ 
/* 298 */       while ((i1.hasNext()) && (i2.hasNext())) {
/* 299 */         short e1 = i1.nextShort();
/* 300 */         short e2 = i2.nextShort();
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
/* 321 */     ShortIterator i = iterator();
/* 322 */     int h = 1; int s = size();
/* 323 */     while (s-- != 0) {
/* 324 */       short k = i.nextShort();
/* 325 */       h = 31 * h + k;
/*     */     }
/* 327 */     return h;
/*     */   }
/*     */ 
/*     */   public void push(short o)
/*     */   {
/* 332 */     add(o);
/*     */   }
/*     */ 
/*     */   public short popShort() {
/* 336 */     if (isEmpty()) throw new NoSuchElementException();
/* 337 */     return removeShort(size() - 1);
/*     */   }
/*     */ 
/*     */   public short topShort() {
/* 341 */     if (isEmpty()) throw new NoSuchElementException();
/* 342 */     return getShort(size() - 1);
/*     */   }
/*     */ 
/*     */   public short peekShort(int i) {
/* 346 */     return getShort(size() - 1 - i);
/*     */   }
/*     */ 
/*     */   public boolean rem(short k)
/*     */   {
/* 352 */     int index = indexOf(k);
/* 353 */     if (index == -1) return false;
/* 354 */     removeShort(index);
/* 355 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean remove(Object o)
/*     */   {
/* 360 */     return rem(((Short)o).shortValue());
/*     */   }
/*     */ 
/*     */   public boolean addAll(int index, ShortCollection c)
/*     */   {
/* 365 */     return addAll(index, c);
/*     */   }
/*     */ 
/*     */   public boolean addAll(int index, ShortList l)
/*     */   {
/* 370 */     return addAll(index, l);
/*     */   }
/*     */ 
/*     */   public boolean addAll(ShortCollection c) {
/* 374 */     return addAll(size(), c);
/*     */   }
/*     */ 
/*     */   public boolean addAll(ShortList l) {
/* 378 */     return addAll(size(), l);
/*     */   }
/*     */ 
/*     */   public void add(int index, Short ok)
/*     */   {
/* 383 */     add(index, ok.shortValue());
/*     */   }
/*     */ 
/*     */   public Short set(int index, Short ok)
/*     */   {
/* 388 */     return Short.valueOf(set(index, ok.shortValue()));
/*     */   }
/*     */ 
/*     */   public Short get(int index)
/*     */   {
/* 393 */     return Short.valueOf(getShort(index));
/*     */   }
/*     */ 
/*     */   public int indexOf(Object ok)
/*     */   {
/* 398 */     return indexOf(((Short)ok).shortValue());
/*     */   }
/*     */ 
/*     */   public int lastIndexOf(Object ok)
/*     */   {
/* 403 */     return lastIndexOf(((Short)ok).shortValue());
/*     */   }
/*     */ 
/*     */   public Short remove(int index)
/*     */   {
/* 408 */     return Short.valueOf(removeShort(index));
/*     */   }
/*     */ 
/*     */   public void push(Short o)
/*     */   {
/* 413 */     push(o.shortValue());
/*     */   }
/*     */ 
/*     */   public Short pop()
/*     */   {
/* 418 */     return Short.valueOf(popShort());
/*     */   }
/*     */ 
/*     */   public Short top()
/*     */   {
/* 423 */     return Short.valueOf(topShort());
/*     */   }
/*     */ 
/*     */   public Short peek(int i)
/*     */   {
/* 428 */     return Short.valueOf(peekShort(i));
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 435 */     StringBuilder s = new StringBuilder();
/* 436 */     ShortIterator i = iterator();
/* 437 */     int n = size();
/*     */ 
/* 439 */     boolean first = true;
/*     */ 
/* 441 */     s.append("[");
/*     */ 
/* 443 */     while (n-- != 0) {
/* 444 */       if (first) first = false; else
/* 445 */         s.append(", ");
/* 446 */       short k = i.nextShort();
/*     */ 
/* 450 */       s.append(String.valueOf(k));
/*     */     }
/*     */ 
/* 453 */     s.append("]");
/* 454 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class ShortSubList extends AbstractShortList implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ShortList l;
/*     */     protected final int from;
/*     */     protected int to;
/*     */     private static final boolean ASSERTS = false;
/*     */ 
/*     */     public ShortSubList(ShortList l, int from, int to) {
/* 470 */       this.l = l;
/* 471 */       this.from = from;
/* 472 */       this.to = to;
/*     */     }
/*     */ 
/*     */     private void assertRange()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean add(short k)
/*     */     {
/* 484 */       this.l.add(this.to, k);
/* 485 */       this.to += 1;
/*     */ 
/* 487 */       return true;
/*     */     }
/*     */ 
/*     */     public void add(int index, short k) {
/* 491 */       ensureIndex(index);
/* 492 */       this.l.add(this.from + index, k);
/* 493 */       this.to += 1;
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, Collection<? extends Short> c)
/*     */     {
/* 498 */       ensureIndex(index);
/* 499 */       this.to += c.size();
/*     */ 
/* 505 */       return this.l.addAll(this.from + index, c);
/*     */     }
/*     */ 
/*     */     public short getShort(int index) {
/* 509 */       ensureRestrictedIndex(index);
/* 510 */       return this.l.getShort(this.from + index);
/*     */     }
/*     */ 
/*     */     public short removeShort(int index) {
/* 514 */       ensureRestrictedIndex(index);
/* 515 */       this.to -= 1;
/* 516 */       return this.l.removeShort(this.from + index);
/*     */     }
/*     */ 
/*     */     public short set(int index, short k) {
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
/*     */     public void getElements(int from, short[] a, int offset, int length) {
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
/*     */     public void addElements(int index, short[] a, int offset, int length)
/*     */     {
/* 548 */       ensureIndex(index);
/* 549 */       this.l.addElements(this.from + index, a, offset, length);
/* 550 */       this.to += length;
/*     */     }
/*     */ 
/*     */     public ShortListIterator listIterator(final int index)
/*     */     {
/* 555 */       ensureIndex(index);
/*     */ 
/* 557 */       return new AbstractShortListIterator() {
/* 558 */         int pos = index; int last = -1;
/*     */ 
/* 560 */         public boolean hasNext() { return this.pos < AbstractShortList.ShortSubList.this.size(); } 
/* 561 */         public boolean hasPrevious() { return this.pos > 0; } 
/* 562 */         public short nextShort() { if (!hasNext()) throw new NoSuchElementException(); return AbstractShortList.ShortSubList.this.l.getShort(AbstractShortList.ShortSubList.this.from + (this.last = this.pos++)); } 
/* 563 */         public short previousShort() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractShortList.ShortSubList.this.l.getShort(AbstractShortList.ShortSubList.this.from + (this.last = --this.pos)); } 
/* 564 */         public int nextIndex() { return this.pos; } 
/* 565 */         public int previousIndex() { return this.pos - 1; } 
/*     */         public void add(short k) {
/* 567 */           if (this.last == -1) throw new IllegalStateException();
/* 568 */           AbstractShortList.ShortSubList.this.add(this.pos++, k);
/* 569 */           this.last = -1;
/*     */         }
/*     */ 
/*     */         public void set(short k) {
/* 573 */           if (this.last == -1) throw new IllegalStateException();
/* 574 */           AbstractShortList.ShortSubList.this.set(this.last, k);
/*     */         }
/*     */         public void remove() {
/* 577 */           if (this.last == -1) throw new IllegalStateException();
/* 578 */           AbstractShortList.ShortSubList.this.removeShort(this.last);
/*     */ 
/* 580 */           if (this.last < this.pos) this.pos -= 1;
/* 581 */           this.last = -1;
/*     */         }
/*     */       };
/*     */     }
/*     */ 
/*     */     public ShortList subList(int from, int to)
/*     */     {
/* 588 */       ensureIndex(from);
/* 589 */       ensureIndex(to);
/* 590 */       if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/*     */ 
/* 592 */       return new ShortSubList(this, from, to);
/*     */     }
/*     */ 
/*     */     public boolean rem(short k)
/*     */     {
/* 598 */       int index = indexOf(k);
/* 599 */       if (index == -1) return false;
/* 600 */       this.to -= 1;
/* 601 */       this.l.removeShort(this.from + index);
/*     */ 
/* 603 */       return true;
/*     */     }
/*     */ 
/*     */     public boolean remove(Object o) {
/* 607 */       return rem(((Short)o).shortValue());
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, ShortCollection c) {
/* 611 */       ensureIndex(index);
/* 612 */       this.to += c.size();
/*     */ 
/* 618 */       return this.l.addAll(this.from + index, c);
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, ShortList l) {
/* 622 */       ensureIndex(index);
/* 623 */       this.to += l.size();
/*     */ 
/* 629 */       return this.l.addAll(this.from + index, l);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortList
 * JD-Core Version:    0.6.2
 */