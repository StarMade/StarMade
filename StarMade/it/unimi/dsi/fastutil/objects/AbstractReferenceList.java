/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Stack;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public abstract class AbstractReferenceList<K> extends AbstractReferenceCollection<K>
/*     */   implements ReferenceList<K>, Stack<K>
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
/*  75 */   public void add(int index, K k) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   public boolean add(K k) {
/*  78 */     add(size(), k);
/*  79 */     return true;
/*     */   }
/*     */   public K remove(int i) {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public K set(int index, K k) {
/*  85 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean addAll(int index, Collection<? extends K> c) {
/*  88 */     ensureIndex(index);
/*  89 */     int n = c.size();
/*  90 */     if (n == 0) return false;
/*  91 */     Iterator i = c.iterator();
/*  92 */     while (n-- != 0) add(index++, i.next());
/*  93 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean addAll(Collection<? extends K> c) {
/*  97 */     return addAll(size(), c);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public ObjectListIterator<K> objectListIterator() {
/* 102 */     return listIterator();
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public ObjectListIterator<K> objectListIterator(int index) {
/* 107 */     return listIterator(index);
/*     */   }
/*     */   public ObjectListIterator<K> iterator() {
/* 110 */     return listIterator();
/*     */   }
/*     */   public ObjectListIterator<K> listIterator() {
/* 113 */     return listIterator(0);
/*     */   }
/*     */   public ObjectListIterator<K> listIterator(final int index) {
/* 116 */     return new AbstractObjectListIterator() {
/* 117 */       int pos = index; int last = -1;
/*     */ 
/* 118 */       public boolean hasNext() { return this.pos < AbstractReferenceList.this.size(); } 
/* 119 */       public boolean hasPrevious() { return this.pos > 0; } 
/* 120 */       public K next() { if (!hasNext()) throw new NoSuchElementException(); return AbstractReferenceList.this.get(this.last = this.pos++); } 
/* 121 */       public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractReferenceList.this.get(this.last = --this.pos); } 
/* 122 */       public int nextIndex() { return this.pos; } 
/* 123 */       public int previousIndex() { return this.pos - 1; } 
/*     */       public void add(K k) {
/* 125 */         if (this.last == -1) throw new IllegalStateException();
/* 126 */         AbstractReferenceList.this.add(this.pos++, k);
/* 127 */         this.last = -1;
/*     */       }
/*     */       public void set(K k) {
/* 130 */         if (this.last == -1) throw new IllegalStateException();
/* 131 */         AbstractReferenceList.this.set(this.last, k);
/*     */       }
/*     */       public void remove() {
/* 134 */         if (this.last == -1) throw new IllegalStateException();
/* 135 */         AbstractReferenceList.this.remove(this.last);
/*     */ 
/* 137 */         if (this.last < this.pos) this.pos -= 1;
/* 138 */         this.last = -1;
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public boolean contains(Object k)
/*     */   {
/* 146 */     return indexOf(k) >= 0;
/*     */   }
/*     */ 
/*     */   public int indexOf(Object k) {
/* 150 */     ObjectListIterator i = listIterator();
/*     */ 
/* 152 */     while (i.hasNext()) {
/* 153 */       Object e = i.next();
/* 154 */       if (k == e) return i.previousIndex();
/*     */     }
/* 156 */     return -1;
/*     */   }
/*     */ 
/*     */   public int lastIndexOf(Object k) {
/* 160 */     ObjectListIterator i = listIterator(size());
/*     */ 
/* 162 */     while (i.hasPrevious()) {
/* 163 */       Object e = i.previous();
/* 164 */       if (k == e) return i.nextIndex();
/*     */     }
/* 166 */     return -1;
/*     */   }
/*     */ 
/*     */   public void size(int size) {
/* 170 */     int i = size();
/* 171 */     for (size <= i; i++ < size; add(null));
/* 172 */     while (i-- != size) remove(i);
/*     */   }
/*     */ 
/*     */   public ReferenceList<K> subList(int from, int to)
/*     */   {
/* 177 */     ensureIndex(from);
/* 178 */     ensureIndex(to);
/* 179 */     if (from > to) throw new IndexOutOfBoundsException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/*     */ 
/* 181 */     return new ReferenceSubList(this, from, to);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public ReferenceList<K> referenceSubList(int from, int to)
/*     */   {
/* 188 */     return subList(from, to);
/*     */   }
/*     */ 
/*     */   public void removeElements(int from, int to)
/*     */   {
/* 202 */     ensureIndex(to);
/* 203 */     ObjectListIterator i = listIterator(from);
/* 204 */     int n = to - from;
/* 205 */     if (n < 0) throw new IllegalArgumentException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 206 */     while (n-- != 0) {
/* 207 */       i.next();
/* 208 */       i.remove();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addElements(int index, K[] a, int offset, int length)
/*     */   {
/* 224 */     ensureIndex(index);
/* 225 */     if (offset < 0) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("Offset (").append(offset).append(") is negative").toString());
/* 226 */     if (offset + length > a.length) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("End index (").append(offset + length).append(") is greater than array length (").append(a.length).append(")").toString());
/* 227 */     while (length-- != 0) add(index++, a[(offset++)]); 
/*     */   }
/*     */ 
/*     */   public void addElements(int index, K[] a)
/*     */   {
/* 231 */     addElements(index, a, 0, a.length);
/*     */   }
/*     */ 
/*     */   public void getElements(int from, Object[] a, int offset, int length)
/*     */   {
/* 246 */     ObjectListIterator i = listIterator(from);
/* 247 */     if (offset < 0) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("Offset (").append(offset).append(") is negative").toString());
/* 248 */     if (offset + length > a.length) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("End index (").append(offset + length).append(") is greater than array length (").append(a.length).append(")").toString());
/* 249 */     if (from + length > size()) throw new IndexOutOfBoundsException(new StringBuilder().append("End index (").append(from + length).append(") is greater than list size (").append(size()).append(")").toString());
/* 250 */     while (length-- != 0) a[(offset++)] = i.next();
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
/* 269 */     while (s-- != 0) if (i1.next() != i2.next()) return false;
/*     */ 
/*     */ 
/* 273 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 280 */     ObjectIterator i = iterator();
/* 281 */     int h = 1; int s = size();
/* 282 */     while (s-- != 0) {
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
/* 293 */     return remove(size() - 1);
/*     */   }
/*     */   public K top() {
/* 296 */     if (isEmpty()) throw new NoSuchElementException();
/* 297 */     return get(size() - 1);
/*     */   }
/*     */   public K peek(int i) {
/* 300 */     return get(size() - 1 - i);
/*     */   }
/*     */   public String toString() {
/* 303 */     StringBuilder s = new StringBuilder();
/* 304 */     ObjectIterator i = iterator();
/* 305 */     int n = size();
/*     */ 
/* 307 */     boolean first = true;
/* 308 */     s.append("[");
/* 309 */     while (n-- != 0) {
/* 310 */       if (first) first = false; else
/* 311 */         s.append(", ");
/* 312 */       Object k = i.next();
/* 313 */       if (this == k) s.append("(this list)"); else
/* 314 */         s.append(String.valueOf(k));
/*     */     }
/* 316 */     s.append("]");
/* 317 */     return s.toString();
/*     */   }
/*     */   public static class ReferenceSubList<K> extends AbstractReferenceList<K> implements Serializable { public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ReferenceList<K> l;
/*     */     protected final int from;
/*     */     protected int to;
/*     */     private static final boolean ASSERTS = false;
/*     */ 
/* 329 */     public ReferenceSubList(ReferenceList<K> l, int from, int to) { this.l = l;
/* 330 */       this.from = from;
/* 331 */       this.to = to;
/*     */     }
/*     */ 
/*     */     private void assertRange()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean add(K k)
/*     */     {
/* 341 */       this.l.add(this.to, k);
/* 342 */       this.to += 1;
/*     */ 
/* 344 */       return true;
/*     */     }
/*     */     public void add(int index, K k) {
/* 347 */       ensureIndex(index);
/* 348 */       this.l.add(this.from + index, k);
/* 349 */       this.to += 1;
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, Collection<? extends K> c) {
/* 353 */       ensureIndex(index);
/* 354 */       this.to += c.size();
/*     */ 
/* 360 */       return this.l.addAll(this.from + index, c);
/*     */     }
/*     */     public K get(int index) {
/* 363 */       ensureRestrictedIndex(index);
/* 364 */       return this.l.get(this.from + index);
/*     */     }
/*     */     public K remove(int index) {
/* 367 */       ensureRestrictedIndex(index);
/* 368 */       this.to -= 1;
/* 369 */       return this.l.remove(this.from + index);
/*     */     }
/*     */     public K set(int index, K k) {
/* 372 */       ensureRestrictedIndex(index);
/* 373 */       return this.l.set(this.from + index, k);
/*     */     }
/*     */     public void clear() {
/* 376 */       removeElements(0, size());
/*     */     }
/*     */ 
/*     */     public int size() {
/* 380 */       return this.to - this.from;
/*     */     }
/*     */     public void getElements(int from, Object[] a, int offset, int length) {
/* 383 */       ensureIndex(from);
/* 384 */       if (from + length > size()) throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size() + ")");
/* 385 */       this.l.getElements(this.from + from, a, offset, length);
/*     */     }
/*     */     public void removeElements(int from, int to) {
/* 388 */       ensureIndex(from);
/* 389 */       ensureIndex(to);
/* 390 */       this.l.removeElements(this.from + from, this.from + to);
/* 391 */       this.to -= to - from;
/*     */     }
/*     */ 
/*     */     public void addElements(int index, K[] a, int offset, int length) {
/* 395 */       ensureIndex(index);
/* 396 */       this.l.addElements(this.from + index, a, offset, length);
/* 397 */       this.to += length;
/*     */     }
/*     */ 
/*     */     public ObjectListIterator<K> listIterator(final int index) {
/* 401 */       ensureIndex(index);
/* 402 */       return new AbstractObjectListIterator() {
/* 403 */         int pos = index; int last = -1;
/*     */ 
/* 404 */         public boolean hasNext() { return this.pos < AbstractReferenceList.ReferenceSubList.this.size(); } 
/* 405 */         public boolean hasPrevious() { return this.pos > 0; } 
/* 406 */         public K next() { if (!hasNext()) throw new NoSuchElementException(); return AbstractReferenceList.ReferenceSubList.this.l.get(AbstractReferenceList.ReferenceSubList.this.from + (this.last = this.pos++)); } 
/* 407 */         public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractReferenceList.ReferenceSubList.this.l.get(AbstractReferenceList.ReferenceSubList.this.from + (this.last = --this.pos)); } 
/* 408 */         public int nextIndex() { return this.pos; } 
/* 409 */         public int previousIndex() { return this.pos - 1; } 
/*     */         public void add(K k) {
/* 411 */           if (this.last == -1) throw new IllegalStateException();
/* 412 */           AbstractReferenceList.ReferenceSubList.this.add(this.pos++, k);
/* 413 */           this.last = -1;
/*     */         }
/*     */ 
/*     */         public void set(K k) {
/* 417 */           if (this.last == -1) throw new IllegalStateException();
/* 418 */           AbstractReferenceList.ReferenceSubList.this.set(this.last, k);
/*     */         }
/*     */         public void remove() {
/* 421 */           if (this.last == -1) throw new IllegalStateException();
/* 422 */           AbstractReferenceList.ReferenceSubList.this.remove(this.last);
/*     */ 
/* 424 */           if (this.last < this.pos) this.pos -= 1;
/* 425 */           this.last = -1;
/*     */         }
/*     */       };
/*     */     }
/*     */ 
/*     */     public ReferenceList<K> subList(int from, int to) {
/* 431 */       ensureIndex(from);
/* 432 */       ensureIndex(to);
/* 433 */       if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 434 */       return new ReferenceSubList(this, from, to);
/*     */     }
/*     */ 
/*     */     public boolean remove(Object o) {
/* 438 */       int index = indexOf(o);
/* 439 */       if (index == -1) return false;
/* 440 */       remove(index);
/* 441 */       return true;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReferenceList
 * JD-Core Version:    0.6.2
 */