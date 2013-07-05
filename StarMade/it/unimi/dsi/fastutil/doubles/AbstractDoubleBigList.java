/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.BigList;
/*     */ import it.unimi.dsi.fastutil.BigListIterator;
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public abstract class AbstractDoubleBigList extends AbstractDoubleCollection
/*     */   implements DoubleBigList, DoubleStack
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
/*  72 */   public void add(long index, double k) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public boolean add(double k) {
/*  75 */     add(size64(), k);
/*  76 */     return true;
/*     */   }
/*     */   public double removeDouble(long i) {
/*  79 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public double removeDouble(int i) {
/*  82 */     return removeDouble(i);
/*     */   }
/*     */   public double set(long index, double k) {
/*  85 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public double set(int index, double k) {
/*  88 */     return set(index, k);
/*     */   }
/*     */   public boolean addAll(long index, Collection<? extends Double> c) {
/*  91 */     ensureIndex(index);
/*  92 */     int n = c.size();
/*  93 */     if (n == 0) return false;
/*  94 */     Iterator i = c.iterator();
/*  95 */     while (n-- != 0) add(index++, (Double)i.next());
/*  96 */     return true;
/*     */   }
/*     */   public boolean addAll(int index, Collection<? extends Double> c) {
/*  99 */     return addAll(index, c);
/*     */   }
/*     */ 
/*     */   public boolean addAll(Collection<? extends Double> c) {
/* 103 */     return addAll(size64(), c);
/*     */   }
/*     */   public DoubleBigListIterator iterator() {
/* 106 */     return listIterator();
/*     */   }
/*     */   public DoubleBigListIterator listIterator() {
/* 109 */     return listIterator(0L);
/*     */   }
/*     */   public DoubleBigListIterator listIterator(final long index) {
/* 112 */     return new AbstractDoubleBigListIterator() {
/* 113 */       long pos = index; long last = -1L;
/*     */ 
/* 114 */       public boolean hasNext() { return this.pos < AbstractDoubleBigList.this.size64(); } 
/* 115 */       public boolean hasPrevious() { return this.pos > 0L; } 
/* 116 */       public double nextDouble() { if (!hasNext()) throw new NoSuchElementException(); return AbstractDoubleBigList.this.getDouble(this.last = this.pos++); } 
/* 117 */       public double previousDouble() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractDoubleBigList.this.getDouble(this.last = --this.pos); } 
/* 118 */       public long nextIndex() { return this.pos; } 
/* 119 */       public long previousIndex() { return this.pos - 1L; } 
/*     */       public void add(double k) {
/* 121 */         if (this.last == -1L) throw new IllegalStateException();
/* 122 */         AbstractDoubleBigList.this.add(this.pos++, k);
/* 123 */         this.last = -1L;
/*     */       }
/*     */       public void set(double k) {
/* 126 */         if (this.last == -1L) throw new IllegalStateException();
/* 127 */         AbstractDoubleBigList.this.set(this.last, k);
/*     */       }
/*     */       public void remove() {
/* 130 */         if (this.last == -1L) throw new IllegalStateException();
/* 131 */         AbstractDoubleBigList.this.removeDouble(this.last);
/*     */ 
/* 133 */         if (this.last < this.pos) this.pos -= 1L;
/* 134 */         this.last = -1L;
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public DoubleBigListIterator listIterator(int index)
/*     */   {
/* 141 */     return listIterator(index);
/*     */   }
/*     */ 
/*     */   public boolean contains(double k)
/*     */   {
/* 146 */     return indexOf(k) >= 0L;
/*     */   }
/*     */ 
/*     */   public long indexOf(double k) {
/* 150 */     DoubleBigListIterator i = listIterator();
/*     */ 
/* 152 */     while (i.hasNext()) {
/* 153 */       double e = i.nextDouble();
/* 154 */       if (k == e) return i.previousIndex();
/*     */     }
/* 156 */     return -1L;
/*     */   }
/*     */ 
/*     */   public long lastIndexOf(double k) {
/* 160 */     DoubleBigListIterator i = listIterator(size64());
/*     */ 
/* 162 */     while (i.hasPrevious()) {
/* 163 */       double e = i.previousDouble();
/* 164 */       if (k == e) return i.nextIndex();
/*     */     }
/* 166 */     return -1L;
/*     */   }
/*     */ 
/*     */   public void size(long size) {
/* 170 */     long i = size64();
/* 171 */     for (size <= i; i++ < size; add(0.0D));
/* 172 */     while (i-- != size) remove(i); 
/*     */   }
/*     */ 
/*     */   public void size(int size)
/*     */   {
/* 176 */     size(size);
/*     */   }
/*     */ 
/*     */   public DoubleBigList subList(long from, long to) {
/* 180 */     ensureIndex(from);
/* 181 */     ensureIndex(to);
/* 182 */     if (from > to) throw new IndexOutOfBoundsException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/*     */ 
/* 184 */     return new DoubleSubList(this, from, to);
/*     */   }
/*     */ 
/*     */   public void removeElements(long from, long to)
/*     */   {
/* 197 */     ensureIndex(to);
/* 198 */     DoubleBigListIterator i = listIterator(from);
/* 199 */     long n = to - from;
/* 200 */     if (n < 0L) throw new IllegalArgumentException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 201 */     while (n-- != 0L) {
/* 202 */       i.nextDouble();
/* 203 */       i.remove();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addElements(long index, double[][] a, long offset, long length)
/*     */   {
/* 219 */     ensureIndex(index);
/* 220 */     DoubleBigArrays.ensureOffsetLength(a, offset, length);
/* 221 */     while (length-- != 0L) add(index++, DoubleBigArrays.get(a, offset++)); 
/*     */   }
/*     */ 
/*     */   public void addElements(long index, double[][] a)
/*     */   {
/* 225 */     addElements(index, a, 0L, DoubleBigArrays.length(a));
/*     */   }
/*     */ 
/*     */   public void getElements(long from, double[][] a, long offset, long length)
/*     */   {
/* 240 */     DoubleBigListIterator i = listIterator(from);
/* 241 */     DoubleBigArrays.ensureOffsetLength(a, offset, length);
/* 242 */     if (from + length > size64()) throw new IndexOutOfBoundsException(new StringBuilder().append("End index (").append(from + length).append(") is greater than list size (").append(size64()).append(")").toString());
/* 243 */     while (length-- != 0L) DoubleBigArrays.set(a, offset++, i.nextDouble()); 
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
/*     */   public int compareTo(BigList<? extends Double> l)
/*     */   {
/* 289 */     if (l == this) return 0;
/*     */ 
/* 291 */     if ((l instanceof DoubleStack))
/*     */     {
/* 293 */       DoubleBigListIterator i1 = listIterator(); DoubleBigListIterator i2 = ((DoubleStack)l).listIterator();
/*     */ 
/* 297 */       while ((i1.hasNext()) && (i2.hasNext())) {
/* 298 */         double e1 = i1.nextDouble();
/* 299 */         double e2 = i2.nextDouble();
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
/* 320 */     DoubleIterator i = iterator();
/* 321 */     int h = 1;
/* 322 */     long s = size64();
/* 323 */     while (s-- != 0L) {
/* 324 */       double k = i.nextDouble();
/* 325 */       h = 31 * h + HashCommon.double2int(k);
/*     */     }
/* 327 */     return h;
/*     */   }
/*     */ 
/*     */   public void push(double o) {
/* 331 */     add(o);
/*     */   }
/*     */ 
/*     */   public double popDouble() {
/* 335 */     if (isEmpty()) throw new NoSuchElementException();
/* 336 */     return removeDouble(size64() - 1L);
/*     */   }
/*     */ 
/*     */   public double topDouble() {
/* 340 */     if (isEmpty()) throw new NoSuchElementException();
/* 341 */     return getDouble(size64() - 1L);
/*     */   }
/*     */ 
/*     */   public double peekDouble(int i) {
/* 345 */     return getDouble(size64() - 1L - i);
/*     */   }
/*     */ 
/*     */   public double getDouble(int index)
/*     */   {
/* 351 */     return getDouble(index);
/*     */   }
/*     */ 
/*     */   public boolean rem(double k) {
/* 355 */     long index = indexOf(k);
/* 356 */     if (index == -1L) return false;
/* 357 */     removeDouble(index);
/* 358 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean addAll(long index, DoubleCollection c)
/*     */   {
/* 363 */     return addAll(index, c);
/*     */   }
/*     */ 
/*     */   public boolean addAll(long index, DoubleBigList l)
/*     */   {
/* 368 */     return addAll(index, l);
/*     */   }
/*     */ 
/*     */   public boolean addAll(DoubleCollection c) {
/* 372 */     return addAll(size64(), c);
/*     */   }
/*     */ 
/*     */   public boolean addAll(DoubleBigList l) {
/* 376 */     return addAll(size64(), l);
/*     */   }
/*     */ 
/*     */   public void add(long index, Double ok)
/*     */   {
/* 381 */     add(index, ok.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double set(long index, Double ok)
/*     */   {
/* 386 */     return Double.valueOf(set(index, ok.doubleValue()));
/*     */   }
/*     */ 
/*     */   public Double get(long index)
/*     */   {
/* 391 */     return Double.valueOf(getDouble(index));
/*     */   }
/*     */ 
/*     */   public long indexOf(Object ok)
/*     */   {
/* 396 */     return indexOf(((Double)ok).doubleValue());
/*     */   }
/*     */ 
/*     */   public long lastIndexOf(Object ok)
/*     */   {
/* 401 */     return lastIndexOf(((Double)ok).doubleValue());
/*     */   }
/*     */ 
/*     */   public Double remove(int index)
/*     */   {
/* 406 */     return Double.valueOf(removeDouble(index));
/*     */   }
/*     */ 
/*     */   public Double remove(long index)
/*     */   {
/* 411 */     return Double.valueOf(removeDouble(index));
/*     */   }
/*     */ 
/*     */   public void push(Double o)
/*     */   {
/* 416 */     push(o.doubleValue());
/*     */   }
/*     */ 
/*     */   public Double pop()
/*     */   {
/* 421 */     return Double.valueOf(popDouble());
/*     */   }
/*     */ 
/*     */   public Double top()
/*     */   {
/* 426 */     return Double.valueOf(topDouble());
/*     */   }
/*     */ 
/*     */   public Double peek(int i)
/*     */   {
/* 431 */     return Double.valueOf(peekDouble(i));
/*     */   }
/*     */   public String toString() {
/* 434 */     StringBuilder s = new StringBuilder();
/* 435 */     DoubleIterator i = iterator();
/* 436 */     long n = size64();
/*     */ 
/* 438 */     boolean first = true;
/* 439 */     s.append("[");
/* 440 */     while (n-- != 0L) {
/* 441 */       if (first) first = false; else
/* 442 */         s.append(", ");
/* 443 */       double k = i.nextDouble();
/* 444 */       s.append(String.valueOf(k));
/*     */     }
/* 446 */     s.append("]");
/* 447 */     return s.toString();
/*     */   }
/*     */   public static class DoubleSubList extends AbstractDoubleBigList implements Serializable { public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final DoubleBigList l;
/*     */     protected final long from;
/*     */     protected long to;
/*     */     private static final boolean ASSERTS = false;
/*     */ 
/* 459 */     public DoubleSubList(DoubleBigList l, long from, long to) { this.l = l;
/* 460 */       this.from = from;
/* 461 */       this.to = to;
/*     */     }
/*     */ 
/*     */     private void assertRange()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean add(double k)
/*     */     {
/* 471 */       this.l.add(this.to, k);
/* 472 */       this.to += 1L;
/*     */ 
/* 474 */       return true;
/*     */     }
/*     */     public void add(long index, double k) {
/* 477 */       ensureIndex(index);
/* 478 */       this.l.add(this.from + index, k);
/* 479 */       this.to += 1L;
/*     */     }
/*     */ 
/*     */     public boolean addAll(long index, Collection<? extends Double> c) {
/* 483 */       ensureIndex(index);
/* 484 */       this.to += c.size();
/*     */ 
/* 490 */       return this.l.addAll(this.from + index, c);
/*     */     }
/*     */     public double getDouble(long index) {
/* 493 */       ensureRestrictedIndex(index);
/* 494 */       return this.l.getDouble(this.from + index);
/*     */     }
/*     */     public double removeDouble(long index) {
/* 497 */       ensureRestrictedIndex(index);
/* 498 */       this.to -= 1L;
/* 499 */       return this.l.removeDouble(this.from + index);
/*     */     }
/*     */     public double set(long index, double k) {
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
/*     */     public void getElements(long from, double[][] a, long offset, long length) {
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
/*     */     public void addElements(long index, double[][] a, long offset, long length) {
/* 525 */       ensureIndex(index);
/* 526 */       this.l.addElements(this.from + index, a, offset, length);
/* 527 */       this.to += length;
/*     */     }
/*     */ 
/*     */     public DoubleBigListIterator listIterator(final long index) {
/* 531 */       ensureIndex(index);
/* 532 */       return new AbstractDoubleBigListIterator() {
/* 533 */         long pos = index; long last = -1L;
/*     */ 
/* 534 */         public boolean hasNext() { return this.pos < AbstractDoubleBigList.DoubleSubList.this.size64(); } 
/* 535 */         public boolean hasPrevious() { return this.pos > 0L; } 
/* 536 */         public double nextDouble() { if (!hasNext()) throw new NoSuchElementException(); return AbstractDoubleBigList.DoubleSubList.this.l.getDouble(AbstractDoubleBigList.DoubleSubList.this.from + (this.last = this.pos++)); } 
/* 537 */         public double previousDouble() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractDoubleBigList.DoubleSubList.this.l.getDouble(AbstractDoubleBigList.DoubleSubList.this.from + (this.last = --this.pos)); } 
/* 538 */         public long nextIndex() { return this.pos; } 
/* 539 */         public long previousIndex() { return this.pos - 1L; } 
/*     */         public void add(double k) {
/* 541 */           if (this.last == -1L) throw new IllegalStateException();
/* 542 */           AbstractDoubleBigList.DoubleSubList.this.add(this.pos++, k);
/* 543 */           this.last = -1L;
/*     */         }
/*     */ 
/*     */         public void set(double k) {
/* 547 */           if (this.last == -1L) throw new IllegalStateException();
/* 548 */           AbstractDoubleBigList.DoubleSubList.this.set(this.last, k);
/*     */         }
/*     */         public void remove() {
/* 551 */           if (this.last == -1L) throw new IllegalStateException();
/* 552 */           AbstractDoubleBigList.DoubleSubList.this.removeDouble(this.last);
/*     */ 
/* 554 */           if (this.last < this.pos) this.pos -= 1L;
/* 555 */           this.last = -1L;
/*     */         }
/*     */       };
/*     */     }
/*     */ 
/*     */     public DoubleBigList subList(long from, long to) {
/* 561 */       ensureIndex(from);
/* 562 */       ensureIndex(to);
/* 563 */       if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 564 */       return new DoubleSubList(this, from, to);
/*     */     }
/*     */     public boolean rem(double k) {
/* 567 */       long index = indexOf(k);
/* 568 */       if (index == -1L) return false;
/* 569 */       this.to -= 1L;
/* 570 */       this.l.removeDouble(this.from + index);
/*     */ 
/* 572 */       return true;
/*     */     }
/*     */     public boolean remove(Object o) {
/* 575 */       return rem(((Double)o).doubleValue());
/*     */     }
/*     */     public boolean addAll(long index, DoubleCollection c) {
/* 578 */       ensureIndex(index);
/* 579 */       this.to += c.size();
/*     */ 
/* 585 */       return this.l.addAll(this.from + index, c);
/*     */     }
/*     */     public boolean addAll(long index, DoubleList l) {
/* 588 */       ensureIndex(index);
/* 589 */       this.to += l.size();
/*     */ 
/* 595 */       return this.l.addAll(this.from + index, l);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleBigList
 * JD-Core Version:    0.6.2
 */