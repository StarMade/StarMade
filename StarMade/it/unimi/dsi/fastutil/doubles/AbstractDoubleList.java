/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public abstract class AbstractDoubleList extends AbstractDoubleCollection
/*     */   implements DoubleList, DoubleStack
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
/*  75 */   public void add(int index, double k) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public boolean add(double k) {
/*  78 */     add(size(), k);
/*  79 */     return true;
/*     */   }
/*     */   public double removeDouble(int i) {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public double set(int index, double k) {
/*  85 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean addAll(int index, Collection<? extends Double> c) {
/*  88 */     ensureIndex(index);
/*  89 */     int n = c.size();
/*  90 */     if (n == 0) return false;
/*  91 */     Iterator i = c.iterator();
/*  92 */     while (n-- != 0) add(index++, (Double)i.next());
/*  93 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean addAll(Collection<? extends Double> c) {
/*  97 */     return addAll(size(), c);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public DoubleListIterator doubleListIterator() {
/* 102 */     return listIterator();
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public DoubleListIterator doubleListIterator(int index) {
/* 107 */     return listIterator(index);
/*     */   }
/*     */   public DoubleListIterator iterator() {
/* 110 */     return listIterator();
/*     */   }
/*     */   public DoubleListIterator listIterator() {
/* 113 */     return listIterator(0);
/*     */   }
/*     */   public DoubleListIterator listIterator(final int index) {
/* 116 */     return new AbstractDoubleListIterator() {
/* 117 */       int pos = index; int last = -1;
/*     */ 
/* 118 */       public boolean hasNext() { return this.pos < AbstractDoubleList.this.size(); } 
/* 119 */       public boolean hasPrevious() { return this.pos > 0; } 
/* 120 */       public double nextDouble() { if (!hasNext()) throw new NoSuchElementException(); return AbstractDoubleList.this.getDouble(this.last = this.pos++); } 
/* 121 */       public double previousDouble() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractDoubleList.this.getDouble(this.last = --this.pos); } 
/* 122 */       public int nextIndex() { return this.pos; } 
/* 123 */       public int previousIndex() { return this.pos - 1; } 
/*     */       public void add(double k) {
/* 125 */         if (this.last == -1) throw new IllegalStateException();
/* 126 */         AbstractDoubleList.this.add(this.pos++, k);
/* 127 */         this.last = -1;
/*     */       }
/*     */       public void set(double k) {
/* 130 */         if (this.last == -1) throw new IllegalStateException();
/* 131 */         AbstractDoubleList.this.set(this.last, k);
/*     */       }
/*     */       public void remove() {
/* 134 */         if (this.last == -1) throw new IllegalStateException();
/* 135 */         AbstractDoubleList.this.removeDouble(this.last);
/*     */ 
/* 137 */         if (this.last < this.pos) this.pos -= 1;
/* 138 */         this.last = -1;
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public boolean contains(double k)
/*     */   {
/* 146 */     return indexOf(k) >= 0;
/*     */   }
/*     */ 
/*     */   public int indexOf(double k) {
/* 150 */     DoubleListIterator i = listIterator();
/*     */ 
/* 152 */     while (i.hasNext()) {
/* 153 */       double e = i.nextDouble();
/* 154 */       if (k == e) return i.previousIndex();
/*     */     }
/* 156 */     return -1;
/*     */   }
/*     */ 
/*     */   public int lastIndexOf(double k) {
/* 160 */     DoubleListIterator i = listIterator(size());
/*     */ 
/* 162 */     while (i.hasPrevious()) {
/* 163 */       double e = i.previousDouble();
/* 164 */       if (k == e) return i.nextIndex();
/*     */     }
/* 166 */     return -1;
/*     */   }
/*     */ 
/*     */   public void size(int size) {
/* 170 */     int i = size();
/* 171 */     for (size <= i; i++ < size; add(0.0D));
/* 172 */     while (i-- != size) remove(i);
/*     */   }
/*     */ 
/*     */   public DoubleList subList(int from, int to)
/*     */   {
/* 177 */     ensureIndex(from);
/* 178 */     ensureIndex(to);
/* 179 */     if (from > to) throw new IndexOutOfBoundsException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/*     */ 
/* 181 */     return new DoubleSubList(this, from, to);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public DoubleList doubleSubList(int from, int to)
/*     */   {
/* 188 */     return subList(from, to);
/*     */   }
/*     */ 
/*     */   public void removeElements(int from, int to)
/*     */   {
/* 202 */     ensureIndex(to);
/* 203 */     DoubleListIterator i = listIterator(from);
/* 204 */     int n = to - from;
/* 205 */     if (n < 0) throw new IllegalArgumentException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 206 */     while (n-- != 0) {
/* 207 */       i.nextDouble();
/* 208 */       i.remove();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addElements(int index, double[] a, int offset, int length)
/*     */   {
/* 224 */     ensureIndex(index);
/* 225 */     if (offset < 0) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("Offset (").append(offset).append(") is negative").toString());
/* 226 */     if (offset + length > a.length) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("End index (").append(offset + length).append(") is greater than array length (").append(a.length).append(")").toString());
/* 227 */     while (length-- != 0) add(index++, a[(offset++)]); 
/*     */   }
/*     */ 
/*     */   public void addElements(int index, double[] a)
/*     */   {
/* 231 */     addElements(index, a, 0, a.length);
/*     */   }
/*     */ 
/*     */   public void getElements(int from, double[] a, int offset, int length)
/*     */   {
/* 246 */     DoubleListIterator i = listIterator(from);
/* 247 */     if (offset < 0) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("Offset (").append(offset).append(") is negative").toString());
/* 248 */     if (offset + length > a.length) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("End index (").append(offset + length).append(") is greater than array length (").append(a.length).append(")").toString());
/* 249 */     if (from + length > size()) throw new IndexOutOfBoundsException(new StringBuilder().append("End index (").append(from + length).append(") is greater than list size (").append(size()).append(")").toString());
/* 250 */     while (length-- != 0) a[(offset++)] = i.nextDouble();
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
/*     */   public int compareTo(List<? extends Double> l)
/*     */   {
/* 290 */     if (l == this) return 0;
/*     */ 
/* 292 */     if ((l instanceof DoubleStack))
/*     */     {
/* 294 */       DoubleListIterator i1 = listIterator(); DoubleListIterator i2 = ((DoubleStack)l).listIterator();
/*     */ 
/* 298 */       while ((i1.hasNext()) && (i2.hasNext())) {
/* 299 */         double e1 = i1.nextDouble();
/* 300 */         double e2 = i2.nextDouble();
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
/* 321 */     DoubleIterator i = iterator();
/* 322 */     int h = 1; int s = size();
/* 323 */     while (s-- != 0) {
/* 324 */       double k = i.nextDouble();
/* 325 */       h = 31 * h + HashCommon.double2int(k);
/*     */     }
/* 327 */     return h;
/*     */   }
/*     */ 
/*     */   public void push(double o)
/*     */   {
/* 332 */     add(o);
/*     */   }
/*     */ 
/*     */   public double popDouble() {
/* 336 */     if (isEmpty()) throw new NoSuchElementException();
/* 337 */     return removeDouble(size() - 1);
/*     */   }
/*     */ 
/*     */   public double topDouble() {
/* 341 */     if (isEmpty()) throw new NoSuchElementException();
/* 342 */     return getDouble(size() - 1);
/*     */   }
/*     */ 
/*     */   public double peekDouble(int i) {
/* 346 */     return getDouble(size() - 1 - i);
/*     */   }
/*     */ 
/*     */   public boolean rem(double k)
/*     */   {
/* 352 */     int index = indexOf(k);
/* 353 */     if (index == -1) return false;
/* 354 */     removeDouble(index);
/* 355 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean remove(Object o)
/*     */   {
/* 360 */     return rem(((Double)o).doubleValue());
/*     */   }
/*     */ 
/*     */   public boolean addAll(int index, DoubleCollection c)
/*     */   {
/* 365 */     return addAll(index, c);
/*     */   }
/*     */ 
/*     */   public boolean addAll(int index, DoubleList l)
/*     */   {
/* 370 */     return addAll(index, l);
/*     */   }
/*     */ 
/*     */   public boolean addAll(DoubleCollection c) {
/* 374 */     return addAll(size(), c);
/*     */   }
/*     */ 
/*     */   public boolean addAll(DoubleList l) {
/* 378 */     return addAll(size(), l);
/*     */   }
/*     */ 
/*     */   public void add(int index, Double ok)
/*     */   {
/* 383 */     add(index, ok.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double set(int index, Double ok)
/*     */   {
/* 388 */     return Double.valueOf(set(index, ok.doubleValue()));
/*     */   }
/*     */ 
/*     */   public Double get(int index)
/*     */   {
/* 393 */     return Double.valueOf(getDouble(index));
/*     */   }
/*     */ 
/*     */   public int indexOf(Object ok)
/*     */   {
/* 398 */     return indexOf(((Double)ok).doubleValue());
/*     */   }
/*     */ 
/*     */   public int lastIndexOf(Object ok)
/*     */   {
/* 403 */     return lastIndexOf(((Double)ok).doubleValue());
/*     */   }
/*     */ 
/*     */   public Double remove(int index)
/*     */   {
/* 408 */     return Double.valueOf(removeDouble(index));
/*     */   }
/*     */ 
/*     */   public void push(Double o)
/*     */   {
/* 413 */     push(o.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double pop()
/*     */   {
/* 418 */     return Double.valueOf(popDouble());
/*     */   }
/*     */ 
/*     */   public Double top()
/*     */   {
/* 423 */     return Double.valueOf(topDouble());
/*     */   }
/*     */ 
/*     */   public Double peek(int i)
/*     */   {
/* 428 */     return Double.valueOf(peekDouble(i));
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 435 */     StringBuilder s = new StringBuilder();
/* 436 */     DoubleIterator i = iterator();
/* 437 */     int n = size();
/*     */ 
/* 439 */     boolean first = true;
/*     */ 
/* 441 */     s.append("[");
/*     */ 
/* 443 */     while (n-- != 0) {
/* 444 */       if (first) first = false; else
/* 445 */         s.append(", ");
/* 446 */       double k = i.nextDouble();
/*     */ 
/* 450 */       s.append(String.valueOf(k));
/*     */     }
/*     */ 
/* 453 */     s.append("]");
/* 454 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class DoubleSubList extends AbstractDoubleList implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final DoubleList l;
/*     */     protected final int from;
/*     */     protected int to;
/*     */     private static final boolean ASSERTS = false;
/*     */ 
/*     */     public DoubleSubList(DoubleList l, int from, int to) {
/* 470 */       this.l = l;
/* 471 */       this.from = from;
/* 472 */       this.to = to;
/*     */     }
/*     */ 
/*     */     private void assertRange()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean add(double k)
/*     */     {
/* 484 */       this.l.add(this.to, k);
/* 485 */       this.to += 1;
/*     */ 
/* 487 */       return true;
/*     */     }
/*     */ 
/*     */     public void add(int index, double k) {
/* 491 */       ensureIndex(index);
/* 492 */       this.l.add(this.from + index, k);
/* 493 */       this.to += 1;
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, Collection<? extends Double> c)
/*     */     {
/* 498 */       ensureIndex(index);
/* 499 */       this.to += c.size();
/*     */ 
/* 505 */       return this.l.addAll(this.from + index, c);
/*     */     }
/*     */ 
/*     */     public double getDouble(int index) {
/* 509 */       ensureRestrictedIndex(index);
/* 510 */       return this.l.getDouble(this.from + index);
/*     */     }
/*     */ 
/*     */     public double removeDouble(int index) {
/* 514 */       ensureRestrictedIndex(index);
/* 515 */       this.to -= 1;
/* 516 */       return this.l.removeDouble(this.from + index);
/*     */     }
/*     */ 
/*     */     public double set(int index, double k) {
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
/*     */     public void getElements(int from, double[] a, int offset, int length) {
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
/*     */     public void addElements(int index, double[] a, int offset, int length)
/*     */     {
/* 548 */       ensureIndex(index);
/* 549 */       this.l.addElements(this.from + index, a, offset, length);
/* 550 */       this.to += length;
/*     */     }
/*     */ 
/*     */     public DoubleListIterator listIterator(final int index)
/*     */     {
/* 555 */       ensureIndex(index);
/*     */ 
/* 557 */       return new AbstractDoubleListIterator() {
/* 558 */         int pos = index; int last = -1;
/*     */ 
/* 560 */         public boolean hasNext() { return this.pos < AbstractDoubleList.DoubleSubList.this.size(); } 
/* 561 */         public boolean hasPrevious() { return this.pos > 0; } 
/* 562 */         public double nextDouble() { if (!hasNext()) throw new NoSuchElementException(); return AbstractDoubleList.DoubleSubList.this.l.getDouble(AbstractDoubleList.DoubleSubList.this.from + (this.last = this.pos++)); } 
/* 563 */         public double previousDouble() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractDoubleList.DoubleSubList.this.l.getDouble(AbstractDoubleList.DoubleSubList.this.from + (this.last = --this.pos)); } 
/* 564 */         public int nextIndex() { return this.pos; } 
/* 565 */         public int previousIndex() { return this.pos - 1; } 
/*     */         public void add(double k) {
/* 567 */           if (this.last == -1) throw new IllegalStateException();
/* 568 */           AbstractDoubleList.DoubleSubList.this.add(this.pos++, k);
/* 569 */           this.last = -1;
/*     */         }
/*     */ 
/*     */         public void set(double k) {
/* 573 */           if (this.last == -1) throw new IllegalStateException();
/* 574 */           AbstractDoubleList.DoubleSubList.this.set(this.last, k);
/*     */         }
/*     */         public void remove() {
/* 577 */           if (this.last == -1) throw new IllegalStateException();
/* 578 */           AbstractDoubleList.DoubleSubList.this.removeDouble(this.last);
/*     */ 
/* 580 */           if (this.last < this.pos) this.pos -= 1;
/* 581 */           this.last = -1;
/*     */         }
/*     */       };
/*     */     }
/*     */ 
/*     */     public DoubleList subList(int from, int to)
/*     */     {
/* 588 */       ensureIndex(from);
/* 589 */       ensureIndex(to);
/* 590 */       if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/*     */ 
/* 592 */       return new DoubleSubList(this, from, to);
/*     */     }
/*     */ 
/*     */     public boolean rem(double k)
/*     */     {
/* 598 */       int index = indexOf(k);
/* 599 */       if (index == -1) return false;
/* 600 */       this.to -= 1;
/* 601 */       this.l.removeDouble(this.from + index);
/*     */ 
/* 603 */       return true;
/*     */     }
/*     */ 
/*     */     public boolean remove(Object o) {
/* 607 */       return rem(((Double)o).doubleValue());
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, DoubleCollection c) {
/* 611 */       ensureIndex(index);
/* 612 */       this.to += c.size();
/*     */ 
/* 618 */       return this.l.addAll(this.from + index, c);
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, DoubleList l) {
/* 622 */       ensureIndex(index);
/* 623 */       this.to += l.size();
/*     */ 
/* 629 */       return this.l.addAll(this.from + index, l);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleList
 * JD-Core Version:    0.6.2
 */