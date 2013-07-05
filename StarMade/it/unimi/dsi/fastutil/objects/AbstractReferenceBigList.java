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
/*     */ public abstract class AbstractReferenceBigList<K> extends AbstractReferenceCollection<K>
/*     */   implements ReferenceBigList<K>, Stack<K>
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
/* 114 */       public boolean hasNext() { return this.pos < AbstractReferenceBigList.this.size64(); } 
/* 115 */       public boolean hasPrevious() { return this.pos > 0L; } 
/* 116 */       public K next() { if (!hasNext()) throw new NoSuchElementException(); return AbstractReferenceBigList.this.get(this.last = this.pos++); } 
/* 117 */       public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractReferenceBigList.this.get(this.last = --this.pos); } 
/* 118 */       public long nextIndex() { return this.pos; } 
/* 119 */       public long previousIndex() { return this.pos - 1L; } 
/*     */       public void add(K k) {
/* 121 */         if (this.last == -1L) throw new IllegalStateException();
/* 122 */         AbstractReferenceBigList.this.add(this.pos++, k);
/* 123 */         this.last = -1L;
/*     */       }
/*     */       public void set(K k) {
/* 126 */         if (this.last == -1L) throw new IllegalStateException();
/* 127 */         AbstractReferenceBigList.this.set(this.last, k);
/*     */       }
/*     */       public void remove() {
/* 130 */         if (this.last == -1L) throw new IllegalStateException();
/* 131 */         AbstractReferenceBigList.this.remove(this.last);
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
/* 152 */     while (i.hasNext()) {
/* 153 */       Object e = i.next();
/* 154 */       if (k == e) return i.previousIndex();
/*     */     }
/* 156 */     return -1L;
/*     */   }
/*     */ 
/*     */   public long lastIndexOf(Object k) {
/* 160 */     ObjectBigListIterator i = listIterator(size64());
/*     */ 
/* 162 */     while (i.hasPrevious()) {
/* 163 */       Object e = i.previous();
/* 164 */       if (k == e) return i.nextIndex();
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
/*     */   public ReferenceBigList<K> subList(long from, long to) {
/* 180 */     ensureIndex(from);
/* 181 */     ensureIndex(to);
/* 182 */     if (from > to) throw new IndexOutOfBoundsException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/*     */ 
/* 184 */     return new ReferenceSubList(this, from, to);
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
/* 268 */     while (s-- != 0L) if (i1.next() != i2.next()) return false;
/*     */ 
/*     */ 
/* 272 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 279 */     ObjectIterator i = iterator();
/* 280 */     int h = 1;
/* 281 */     long s = size64();
/* 282 */     while (s-- != 0L) {
/* 283 */       Object k = i.next();
/* 284 */       h = 31 * h + (k == null ? 0 : System.identityHashCode(k));
/*     */     }
/* 286 */     return h;
/*     */   }
/*     */   public void push(K o) {
/* 289 */     add(o);
/*     */   }
/*     */   public K pop() {
/* 292 */     if (isEmpty()) throw new NoSuchElementException();
/* 293 */     return remove(size64() - 1L);
/*     */   }
/*     */   public K top() {
/* 296 */     if (isEmpty()) throw new NoSuchElementException();
/* 297 */     return get(size64() - 1L);
/*     */   }
/*     */   public K peek(int i) {
/* 300 */     return get(size64() - 1L - i);
/*     */   }
/*     */   public K get(int index) {
/* 303 */     return get(index);
/*     */   }
/*     */   public String toString() {
/* 306 */     StringBuilder s = new StringBuilder();
/* 307 */     ObjectIterator i = iterator();
/* 308 */     long n = size64();
/*     */ 
/* 310 */     boolean first = true;
/* 311 */     s.append("[");
/* 312 */     while (n-- != 0L) {
/* 313 */       if (first) first = false; else
/* 314 */         s.append(", ");
/* 315 */       Object k = i.next();
/* 316 */       if (this == k) s.append("(this big list)"); else
/* 317 */         s.append(String.valueOf(k));
/*     */     }
/* 319 */     s.append("]");
/* 320 */     return s.toString();
/*     */   }
/*     */   public static class ReferenceSubList<K> extends AbstractReferenceBigList<K> implements Serializable { public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ReferenceBigList<K> l;
/*     */     protected final long from;
/*     */     protected long to;
/*     */     private static final boolean ASSERTS = false;
/*     */ 
/* 332 */     public ReferenceSubList(ReferenceBigList<K> l, long from, long to) { this.l = l;
/* 333 */       this.from = from;
/* 334 */       this.to = to;
/*     */     }
/*     */ 
/*     */     private void assertRange()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean add(K k)
/*     */     {
/* 344 */       this.l.add(this.to, k);
/* 345 */       this.to += 1L;
/*     */ 
/* 347 */       return true;
/*     */     }
/*     */     public void add(long index, K k) {
/* 350 */       ensureIndex(index);
/* 351 */       this.l.add(this.from + index, k);
/* 352 */       this.to += 1L;
/*     */     }
/*     */ 
/*     */     public boolean addAll(long index, Collection<? extends K> c) {
/* 356 */       ensureIndex(index);
/* 357 */       this.to += c.size();
/*     */ 
/* 363 */       return this.l.addAll(this.from + index, c);
/*     */     }
/*     */     public K get(long index) {
/* 366 */       ensureRestrictedIndex(index);
/* 367 */       return this.l.get(this.from + index);
/*     */     }
/*     */     public K remove(long index) {
/* 370 */       ensureRestrictedIndex(index);
/* 371 */       this.to -= 1L;
/* 372 */       return this.l.remove(this.from + index);
/*     */     }
/*     */     public K set(long index, K k) {
/* 375 */       ensureRestrictedIndex(index);
/* 376 */       return this.l.set(this.from + index, k);
/*     */     }
/*     */     public void clear() {
/* 379 */       removeElements(0L, size64());
/*     */     }
/*     */ 
/*     */     public long size64() {
/* 383 */       return this.to - this.from;
/*     */     }
/*     */     public void getElements(long from, Object[][] a, long offset, long length) {
/* 386 */       ensureIndex(from);
/* 387 */       if (from + length > size64()) throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size64() + ")");
/* 388 */       this.l.getElements(this.from + from, a, offset, length);
/*     */     }
/*     */     public void removeElements(long from, long to) {
/* 391 */       ensureIndex(from);
/* 392 */       ensureIndex(to);
/* 393 */       this.l.removeElements(this.from + from, this.from + to);
/* 394 */       this.to -= to - from;
/*     */     }
/*     */ 
/*     */     public void addElements(long index, K[][] a, long offset, long length) {
/* 398 */       ensureIndex(index);
/* 399 */       this.l.addElements(this.from + index, a, offset, length);
/* 400 */       this.to += length;
/*     */     }
/*     */ 
/*     */     public ObjectBigListIterator<K> listIterator(final long index) {
/* 404 */       ensureIndex(index);
/* 405 */       return new AbstractObjectBigListIterator() {
/* 406 */         long pos = index; long last = -1L;
/*     */ 
/* 407 */         public boolean hasNext() { return this.pos < AbstractReferenceBigList.ReferenceSubList.this.size64(); } 
/* 408 */         public boolean hasPrevious() { return this.pos > 0L; } 
/* 409 */         public K next() { if (!hasNext()) throw new NoSuchElementException(); return AbstractReferenceBigList.ReferenceSubList.this.l.get(AbstractReferenceBigList.ReferenceSubList.this.from + (this.last = this.pos++)); } 
/* 410 */         public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractReferenceBigList.ReferenceSubList.this.l.get(AbstractReferenceBigList.ReferenceSubList.this.from + (this.last = --this.pos)); } 
/* 411 */         public long nextIndex() { return this.pos; } 
/* 412 */         public long previousIndex() { return this.pos - 1L; } 
/*     */         public void add(K k) {
/* 414 */           if (this.last == -1L) throw new IllegalStateException();
/* 415 */           AbstractReferenceBigList.ReferenceSubList.this.add(this.pos++, k);
/* 416 */           this.last = -1L;
/*     */         }
/*     */ 
/*     */         public void set(K k) {
/* 420 */           if (this.last == -1L) throw new IllegalStateException();
/* 421 */           AbstractReferenceBigList.ReferenceSubList.this.set(this.last, k);
/*     */         }
/*     */         public void remove() {
/* 424 */           if (this.last == -1L) throw new IllegalStateException();
/* 425 */           AbstractReferenceBigList.ReferenceSubList.this.remove(this.last);
/*     */ 
/* 427 */           if (this.last < this.pos) this.pos -= 1L;
/* 428 */           this.last = -1L;
/*     */         }
/*     */       };
/*     */     }
/*     */ 
/*     */     public ReferenceBigList<K> subList(long from, long to) {
/* 434 */       ensureIndex(from);
/* 435 */       ensureIndex(to);
/* 436 */       if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 437 */       return new ReferenceSubList(this, from, to);
/*     */     }
/*     */ 
/*     */     public boolean remove(Object o) {
/* 441 */       long index = indexOf(o);
/* 442 */       if (index == -1L) return false;
/* 443 */       remove(index);
/* 444 */       return true;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReferenceBigList
 * JD-Core Version:    0.6.2
 */