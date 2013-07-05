/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.BigList;
/*     */ import it.unimi.dsi.fastutil.BigListIterator;
/*     */ import it.unimi.dsi.fastutil.Stack;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public abstract class AbstractObjectBigList<K> extends AbstractObjectCollection<K>
/*     */   implements ObjectBigList<K>, Stack<K>
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
/*  72 */   public void add(long index, K k) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public boolean add(K k) {
/*  75 */     add(size64(), k);
/*  76 */     return true;
/*     */   }
/*     */   public K remove(long i) {
/*  79 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public K remove(int i) {
/*  82 */     return remove(i);
/*     */   }
/*     */   public K set(long index, K k) {
/*  85 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public K set(int index, K k) {
/*  88 */     return set(index, k);
/*     */   }
/*     */   public boolean addAll(long index, Collection<? extends K> c) {
/*  91 */     ensureIndex(index);
/*  92 */     int n = c.size();
/*  93 */     if (n == 0) return false;
/*  94 */     Iterator i = c.iterator();
/*  95 */     while (n-- != 0) add(index++, i.next());
/*  96 */     return true;
/*     */   }
/*     */   public boolean addAll(int index, Collection<? extends K> c) {
/*  99 */     return addAll(index, c);
/*     */   }
/*     */ 
/*     */   public boolean addAll(Collection<? extends K> c) {
/* 103 */     return addAll(size64(), c);
/*     */   }
/*     */   public ObjectBigListIterator<K> iterator() {
/* 106 */     return listIterator();
/*     */   }
/*     */   public ObjectBigListIterator<K> listIterator() {
/* 109 */     return listIterator(0L);
/*     */   }
/*     */   public ObjectBigListIterator<K> listIterator(final long index) {
/* 112 */     return new AbstractObjectBigListIterator() {
/* 113 */       long pos = index; long last = -1L;
/*     */ 
/* 114 */       public boolean hasNext() { return this.pos < AbstractObjectBigList.this.size64(); } 
/* 115 */       public boolean hasPrevious() { return this.pos > 0L; } 
/* 116 */       public K next() { if (!hasNext()) throw new NoSuchElementException(); return AbstractObjectBigList.this.get(this.last = this.pos++); } 
/* 117 */       public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractObjectBigList.this.get(this.last = --this.pos); } 
/* 118 */       public long nextIndex() { return this.pos; } 
/* 119 */       public long previousIndex() { return this.pos - 1L; } 
/*     */       public void add(K k) {
/* 121 */         if (this.last == -1L) throw new IllegalStateException();
/* 122 */         AbstractObjectBigList.this.add(this.pos++, k);
/* 123 */         this.last = -1L;
/*     */       }
/*     */       public void set(K k) {
/* 126 */         if (this.last == -1L) throw new IllegalStateException();
/* 127 */         AbstractObjectBigList.this.set(this.last, k);
/*     */       }
/*     */       public void remove() {
/* 130 */         if (this.last == -1L) throw new IllegalStateException();
/* 131 */         AbstractObjectBigList.this.remove(this.last);
/*     */ 
/* 133 */         if (this.last < this.pos) this.pos -= 1L;
/* 134 */         this.last = -1L;
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectBigListIterator<K> listIterator(int index)
/*     */   {
/* 141 */     return listIterator(index);
/*     */   }
/*     */ 
/*     */   public boolean contains(Object k)
/*     */   {
/* 146 */     return indexOf(k) >= 0L;
/*     */   }
/*     */ 
/*     */   public long indexOf(Object k) {
/* 150 */     ObjectBigListIterator i = listIterator();
/*     */ 
/* 154 */     for (; i.hasNext(); 
/* 154 */       return i.previousIndex())
/*     */     {
/* 153 */       label5: Object e = i.next();
/* 154 */       if (k == null ? e != null : !k.equals(e)) break label5;
/*     */     }
/* 156 */     return -1L;
/*     */   }
/*     */ 
/*     */   public long lastIndexOf(Object k) {
/* 160 */     ObjectBigListIterator i = listIterator(size64());
/*     */ 
/* 164 */     for (; i.hasPrevious(); 
/* 164 */       return i.nextIndex())
/*     */     {
/* 163 */       label9: Object e = i.previous();
/* 164 */       if (k == null ? e != null : !k.equals(e)) break label9;
/*     */     }
/* 166 */     return -1L;
/*     */   }
/*     */ 
/*     */   public void size(long size) {
/* 170 */     long i = size64();
/* 171 */     for (size <= i; i++ < size; add(null));
/* 172 */     while (i-- != size) remove(i); 
/*     */   }
/*     */ 
/*     */   public void size(int size)
/*     */   {
/* 176 */     size(size);
/*     */   }
/*     */ 
/*     */   public ObjectBigList<K> subList(long from, long to) {
/* 180 */     ensureIndex(from);
/* 181 */     ensureIndex(to);
/* 182 */     if (from > to) throw new IndexOutOfBoundsException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/*     */ 
/* 184 */     return new ObjectSubList(this, from, to);
/*     */   }
/*     */ 
/*     */   public void removeElements(long from, long to)
/*     */   {
/* 197 */     ensureIndex(to);
/* 198 */     ObjectBigListIterator i = listIterator(from);
/* 199 */     long n = to - from;
/* 200 */     if (n < 0L) throw new IllegalArgumentException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 201 */     while (n-- != 0L) {
/* 202 */       i.next();
/* 203 */       i.remove();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addElements(long index, K[][] a, long offset, long length)
/*     */   {
/* 219 */     ensureIndex(index);
/* 220 */     ObjectBigArrays.ensureOffsetLength(a, offset, length);
/* 221 */     while (length-- != 0L) add(index++, ObjectBigArrays.get(a, offset++)); 
/*     */   }
/*     */ 
/*     */   public void addElements(long index, K[][] a)
/*     */   {
/* 225 */     addElements(index, a, 0L, ObjectBigArrays.length(a));
/*     */   }
/*     */ 
/*     */   public void getElements(long from, Object[][] a, long offset, long length)
/*     */   {
/* 240 */     ObjectBigListIterator i = listIterator(from);
/* 241 */     ObjectBigArrays.ensureOffsetLength(a, offset, length);
/* 242 */     if (from + length > size64()) throw new IndexOutOfBoundsException(new StringBuilder().append("End index (").append(from + length).append(") is greater than list size (").append(size64()).append(")").toString());
/* 243 */     while (length-- != 0L) ObjectBigArrays.set(a, offset++, i.next()); 
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
/*     */   public int compareTo(BigList<? extends K> l)
/*     */   {
/* 289 */     if (l == this) return 0;
/*     */ 
/* 291 */     if ((l instanceof ObjectBigList))
/*     */     {
/* 293 */       ObjectBigListIterator i1 = listIterator(); ObjectBigListIterator i2 = ((ObjectBigList)l).listIterator();
/*     */ 
/* 297 */       while ((i1.hasNext()) && (i2.hasNext())) {
/* 298 */         Object e1 = i1.next();
/* 299 */         Object e2 = i2.next();
/*     */         int r;
/* 300 */         if ((r = ((Comparable)e1).compareTo(e2)) != 0) return r;
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
/* 320 */     ObjectIterator i = iterator();
/* 321 */     int h = 1;
/* 322 */     long s = size64();
/* 323 */     while (s-- != 0L) {
/* 324 */       Object k = i.next();
/* 325 */       h = 31 * h + (k == null ? 0 : k.hashCode());
/*     */     }
/* 327 */     return h;
/*     */   }
/*     */ 
/*     */   public void push(K o) {
/* 331 */     add(o);
/*     */   }
/*     */ 
/*     */   public K pop() {
/* 335 */     if (isEmpty()) throw new NoSuchElementException();
/* 336 */     return remove(size64() - 1L);
/*     */   }
/*     */ 
/*     */   public K top() {
/* 340 */     if (isEmpty()) throw new NoSuchElementException();
/* 341 */     return get(size64() - 1L);
/*     */   }
/*     */ 
/*     */   public K peek(int i) {
/* 345 */     return get(size64() - 1L - i);
/*     */   }
/*     */   public K get(int index) {
/* 348 */     return get(index);
/*     */   }
/*     */   public String toString() {
/* 351 */     StringBuilder s = new StringBuilder();
/* 352 */     ObjectIterator i = iterator();
/* 353 */     long n = size64();
/*     */ 
/* 355 */     boolean first = true;
/* 356 */     s.append("[");
/* 357 */     while (n-- != 0L) {
/* 358 */       if (first) first = false; else
/* 359 */         s.append(", ");
/* 360 */       Object k = i.next();
/* 361 */       if (this == k) s.append("(this big list)"); else
/* 362 */         s.append(String.valueOf(k));
/*     */     }
/* 364 */     s.append("]");
/* 365 */     return s.toString();
/*     */   }
/*     */   public static class ObjectSubList<K> extends AbstractObjectBigList<K> implements Serializable { public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ObjectBigList<K> l;
/*     */     protected final long from;
/*     */     protected long to;
/*     */     private static final boolean ASSERTS = false;
/*     */ 
/* 377 */     public ObjectSubList(ObjectBigList<K> l, long from, long to) { this.l = l;
/* 378 */       this.from = from;
/* 379 */       this.to = to;
/*     */     }
/*     */ 
/*     */     private void assertRange()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean add(K k)
/*     */     {
/* 389 */       this.l.add(this.to, k);
/* 390 */       this.to += 1L;
/*     */ 
/* 392 */       return true;
/*     */     }
/*     */     public void add(long index, K k) {
/* 395 */       ensureIndex(index);
/* 396 */       this.l.add(this.from + index, k);
/* 397 */       this.to += 1L;
/*     */     }
/*     */ 
/*     */     public boolean addAll(long index, Collection<? extends K> c) {
/* 401 */       ensureIndex(index);
/* 402 */       this.to += c.size();
/*     */ 
/* 408 */       return this.l.addAll(this.from + index, c);
/*     */     }
/*     */     public K get(long index) {
/* 411 */       ensureRestrictedIndex(index);
/* 412 */       return this.l.get(this.from + index);
/*     */     }
/*     */     public K remove(long index) {
/* 415 */       ensureRestrictedIndex(index);
/* 416 */       this.to -= 1L;
/* 417 */       return this.l.remove(this.from + index);
/*     */     }
/*     */     public K set(long index, K k) {
/* 420 */       ensureRestrictedIndex(index);
/* 421 */       return this.l.set(this.from + index, k);
/*     */     }
/*     */     public void clear() {
/* 424 */       removeElements(0L, size64());
/*     */     }
/*     */ 
/*     */     public long size64() {
/* 428 */       return this.to - this.from;
/*     */     }
/*     */     public void getElements(long from, Object[][] a, long offset, long length) {
/* 431 */       ensureIndex(from);
/* 432 */       if (from + length > size64()) throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size64() + ")");
/* 433 */       this.l.getElements(this.from + from, a, offset, length);
/*     */     }
/*     */     public void removeElements(long from, long to) {
/* 436 */       ensureIndex(from);
/* 437 */       ensureIndex(to);
/* 438 */       this.l.removeElements(this.from + from, this.from + to);
/* 439 */       this.to -= to - from;
/*     */     }
/*     */ 
/*     */     public void addElements(long index, K[][] a, long offset, long length) {
/* 443 */       ensureIndex(index);
/* 444 */       this.l.addElements(this.from + index, a, offset, length);
/* 445 */       this.to += length;
/*     */     }
/*     */ 
/*     */     public ObjectBigListIterator<K> listIterator(final long index) {
/* 449 */       ensureIndex(index);
/* 450 */       return new AbstractObjectBigListIterator() {
/* 451 */         long pos = index; long last = -1L;
/*     */ 
/* 452 */         public boolean hasNext() { return this.pos < AbstractObjectBigList.ObjectSubList.this.size64(); } 
/* 453 */         public boolean hasPrevious() { return this.pos > 0L; } 
/* 454 */         public K next() { if (!hasNext()) throw new NoSuchElementException(); return AbstractObjectBigList.ObjectSubList.this.l.get(AbstractObjectBigList.ObjectSubList.this.from + (this.last = this.pos++)); } 
/* 455 */         public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractObjectBigList.ObjectSubList.this.l.get(AbstractObjectBigList.ObjectSubList.this.from + (this.last = --this.pos)); } 
/* 456 */         public long nextIndex() { return this.pos; } 
/* 457 */         public long previousIndex() { return this.pos - 1L; } 
/*     */         public void add(K k) {
/* 459 */           if (this.last == -1L) throw new IllegalStateException();
/* 460 */           AbstractObjectBigList.ObjectSubList.this.add(this.pos++, k);
/* 461 */           this.last = -1L;
/*     */         }
/*     */ 
/*     */         public void set(K k) {
/* 465 */           if (this.last == -1L) throw new IllegalStateException();
/* 466 */           AbstractObjectBigList.ObjectSubList.this.set(this.last, k);
/*     */         }
/*     */         public void remove() {
/* 469 */           if (this.last == -1L) throw new IllegalStateException();
/* 470 */           AbstractObjectBigList.ObjectSubList.this.remove(this.last);
/*     */ 
/* 472 */           if (this.last < this.pos) this.pos -= 1L;
/* 473 */           this.last = -1L;
/*     */         }
/*     */       };
/*     */     }
/*     */ 
/*     */     public ObjectBigList<K> subList(long from, long to) {
/* 479 */       ensureIndex(from);
/* 480 */       ensureIndex(to);
/* 481 */       if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 482 */       return new ObjectSubList(this, from, to);
/*     */     }
/*     */ 
/*     */     public boolean remove(Object o) {
/* 486 */       long index = indexOf(o);
/* 487 */       if (index == -1L) return false;
/* 488 */       remove(index);
/* 489 */       return true;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectBigList
 * JD-Core Version:    0.6.2
 */