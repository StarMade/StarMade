/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Arrays;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.RandomAccess;
/*     */ 
/*     */ public class ReferenceArrayList<K> extends AbstractReferenceList<K>
/*     */   implements RandomAccess, Cloneable, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -7046029254386353131L;
/*     */   public static final int DEFAULT_INITIAL_CAPACITY = 16;
/*     */   protected static final long ONEOVERPHI = 106039L;
/*     */   protected final boolean wrapped;
/*     */   protected transient K[] a;
/*     */   protected int size;
/*     */   private static final boolean ASSERTS = false;
/*     */ 
/*     */   protected ReferenceArrayList(K[] a, boolean dummy)
/*     */   {
/*  97 */     this.a = a;
/*  98 */     this.wrapped = true;
/*     */   }
/*     */ 
/*     */   public ReferenceArrayList(int capacity)
/*     */   {
/* 106 */     if (capacity < 0) throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
/* 107 */     this.a = ((Object[])new Object[capacity]);
/* 108 */     this.wrapped = false;
/*     */   }
/*     */ 
/*     */   public ReferenceArrayList()
/*     */   {
/* 113 */     this(16);
/*     */   }
/*     */ 
/*     */   public ReferenceArrayList(Collection<? extends K> c)
/*     */   {
/* 120 */     this(c.size());
/* 121 */     this.size = ObjectIterators.unwrap(c.iterator(), this.a);
/*     */   }
/*     */ 
/*     */   public ReferenceArrayList(ReferenceCollection<? extends K> c)
/*     */   {
/* 128 */     this(c.size());
/* 129 */     this.size = ObjectIterators.unwrap(c.iterator(), this.a);
/*     */   }
/*     */ 
/*     */   public ReferenceArrayList(ReferenceList<? extends K> l)
/*     */   {
/* 136 */     this(l.size());
/* 137 */     l.getElements(0, this.a, 0, this.size = l.size());
/*     */   }
/*     */ 
/*     */   public ReferenceArrayList(K[] a)
/*     */   {
/* 144 */     this(a, 0, a.length);
/*     */   }
/*     */ 
/*     */   public ReferenceArrayList(K[] a, int offset, int length)
/*     */   {
/* 153 */     this(length);
/* 154 */     System.arraycopy(a, offset, this.a, 0, length);
/* 155 */     this.size = length;
/*     */   }
/*     */ 
/*     */   public ReferenceArrayList(Iterator<? extends K> i)
/*     */   {
/* 162 */     this();
/* 163 */     while (i.hasNext()) add(i.next());
/*     */   }
/*     */ 
/*     */   public ReferenceArrayList(ObjectIterator<? extends K> i)
/*     */   {
/* 170 */     this();
/* 171 */     while (i.hasNext()) add(i.next());
/*     */   }
/*     */ 
/*     */   public K[] elements()
/*     */   {
/* 187 */     return this.a;
/*     */   }
/*     */ 
/*     */   public static <K> ReferenceArrayList<K> wrap(K[] a, int length)
/*     */   {
/* 196 */     if (length > a.length) throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + a.length + ")");
/* 197 */     ReferenceArrayList l = new ReferenceArrayList(a, false);
/* 198 */     l.size = length;
/* 199 */     return l;
/*     */   }
/*     */ 
/*     */   public static <K> ReferenceArrayList<K> wrap(K[] a)
/*     */   {
/* 207 */     return wrap(a, a.length);
/*     */   }
/*     */ 
/*     */   public void ensureCapacity(int capacity)
/*     */   {
/* 215 */     if (this.wrapped) { this.a = ObjectArrays.ensureCapacity(this.a, capacity, this.size); }
/* 217 */     else if (capacity > this.a.length) {
/* 218 */       Object[] t = new Object[capacity];
/* 219 */       System.arraycopy(this.a, 0, t, 0, this.size);
/* 220 */       this.a = ((Object[])t);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void grow(int capacity)
/*     */   {
/* 232 */     if (this.wrapped) { this.a = ObjectArrays.grow(this.a, capacity, this.size); }
/* 234 */     else if (capacity > this.a.length) {
/* 235 */       int newLength = (int)Math.min(Math.max(106039L * this.a.length >>> 16, capacity), 2147483647L);
/* 236 */       Object[] t = new Object[newLength];
/* 237 */       System.arraycopy(this.a, 0, t, 0, this.size);
/* 238 */       this.a = ((Object[])t);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void add(int index, K k)
/*     */   {
/* 244 */     ensureIndex(index);
/* 245 */     grow(this.size + 1);
/* 246 */     if (index != this.size) System.arraycopy(this.a, index, this.a, index + 1, this.size - index);
/* 247 */     this.a[index] = k;
/* 248 */     this.size += 1;
/*     */   }
/*     */ 
/*     */   public boolean add(K k) {
/* 252 */     grow(this.size + 1);
/* 253 */     this.a[(this.size++)] = k;
/*     */ 
/* 255 */     return true;
/*     */   }
/*     */   public K get(int index) {
/* 258 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 259 */     return this.a[index];
/*     */   }
/*     */   public int indexOf(Object k) {
/* 262 */     for (int i = 0; i < this.size; i++) if (k == this.a[i]) return i;
/* 263 */     return -1;
/*     */   }
/*     */   public int lastIndexOf(Object k) {
/* 266 */     for (int i = this.size; i-- != 0; ) if (k == this.a[i]) return i;
/* 267 */     return -1;
/*     */   }
/*     */   public K remove(int index) {
/* 270 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 271 */     Object old = this.a[index];
/* 272 */     this.size -= 1;
/* 273 */     if (index != this.size) System.arraycopy(this.a, index + 1, this.a, index, this.size - index);
/* 274 */     this.a[this.size] = null;
/*     */ 
/* 276 */     return old;
/*     */   }
/*     */   public boolean rem(Object k) {
/* 279 */     int index = indexOf(k);
/* 280 */     if (index == -1) return false;
/* 281 */     remove(index);
/*     */ 
/* 283 */     return true;
/*     */   }
/*     */   public boolean remove(Object o) {
/* 286 */     return rem(o);
/*     */   }
/*     */   public K set(int index, K k) {
/* 289 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 290 */     Object old = this.a[index];
/* 291 */     this.a[index] = k;
/* 292 */     return old;
/*     */   }
/*     */   public void clear() {
/* 295 */     ObjectArrays.fill(this.a, 0, this.size, null);
/* 296 */     this.size = 0;
/*     */   }
/*     */ 
/*     */   public int size() {
/* 300 */     return this.size;
/*     */   }
/*     */   public void size(int size) {
/* 303 */     if (size > this.a.length) ensureCapacity(size);
/* 304 */     if (size > this.size) ObjectArrays.fill(this.a, this.size, size, null); else
/* 305 */       ObjectArrays.fill(this.a, size, this.size, null);
/* 306 */     this.size = size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 309 */     return this.size == 0;
/*     */   }
/*     */ 
/*     */   public void trim()
/*     */   {
/* 316 */     trim(0);
/*     */   }
/*     */ 
/*     */   public void trim(int n)
/*     */   {
/* 335 */     if ((n >= this.a.length) || (this.size == this.a.length)) return;
/* 336 */     Object[] t = (Object[])new Object[Math.max(n, this.size)];
/* 337 */     System.arraycopy(this.a, 0, t, 0, this.size);
/* 338 */     this.a = t;
/*     */   }
/*     */ 
/*     */   public void getElements(int from, Object[] a, int offset, int length)
/*     */   {
/* 349 */     ObjectArrays.ensureOffsetLength(a, offset, length);
/* 350 */     System.arraycopy(this.a, from, a, offset, length);
/*     */   }
/*     */ 
/*     */   public void removeElements(int from, int to)
/*     */   {
/* 358 */     Arrays.ensureFromTo(this.size, from, to);
/* 359 */     System.arraycopy(this.a, to, this.a, from, this.size - to);
/* 360 */     this.size -= to - from;
/* 361 */     int i = to - from;
/* 362 */     while (i-- != 0) this.a[(this.size + i)] = null;
/*     */   }
/*     */ 
/*     */   public void addElements(int index, K[] a, int offset, int length)
/*     */   {
/* 372 */     ensureIndex(index);
/* 373 */     ObjectArrays.ensureOffsetLength(a, offset, length);
/* 374 */     grow(this.size + length);
/* 375 */     System.arraycopy(this.a, index, this.a, index + length, this.size - index);
/* 376 */     System.arraycopy(a, offset, this.a, index, length);
/* 377 */     this.size += length;
/*     */   }
/*     */   public ObjectListIterator<K> listIterator(final int index) {
/* 380 */     ensureIndex(index);
/* 381 */     return new AbstractObjectListIterator() {
/* 382 */       int pos = index; int last = -1;
/*     */ 
/* 383 */       public boolean hasNext() { return this.pos < ReferenceArrayList.this.size; } 
/* 384 */       public boolean hasPrevious() { return this.pos > 0; } 
/* 385 */       public K next() { if (!hasNext()) throw new NoSuchElementException(); return ReferenceArrayList.this.a[(this.last = this.pos++)]; } 
/* 386 */       public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return ReferenceArrayList.this.a[(this.last = --this.pos)]; } 
/* 387 */       public int nextIndex() { return this.pos; } 
/* 388 */       public int previousIndex() { return this.pos - 1; } 
/*     */       public void add(K k) {
/* 390 */         if (this.last == -1) throw new IllegalStateException();
/* 391 */         ReferenceArrayList.this.add(this.pos++, k);
/* 392 */         this.last = -1;
/*     */       }
/*     */       public void set(K k) {
/* 395 */         if (this.last == -1) throw new IllegalStateException();
/* 396 */         ReferenceArrayList.this.set(this.last, k);
/*     */       }
/*     */       public void remove() {
/* 399 */         if (this.last == -1) throw new IllegalStateException();
/* 400 */         ReferenceArrayList.this.remove(this.last);
/*     */ 
/* 402 */         if (this.last < this.pos) this.pos -= 1;
/* 403 */         this.last = -1;
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ReferenceArrayList<K> clone() {
/* 409 */     ReferenceArrayList c = new ReferenceArrayList(this.size);
/* 410 */     System.arraycopy(this.a, 0, c.a, 0, this.size);
/* 411 */     c.size = this.size;
/* 412 */     return c;
/*     */   }
/*     */ 
/*     */   public boolean equals(ReferenceArrayList<K> l)
/*     */   {
/* 423 */     if (l == this) return true;
/* 424 */     int s = size();
/* 425 */     if (s != l.size()) return false;
/* 426 */     Object[] a1 = this.a;
/* 427 */     Object[] a2 = l.a;
/* 428 */     while (s-- != 0) if (a1[s] != a2[s]) return false;
/* 429 */     return true;
/*     */   }
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 432 */     s.defaultWriteObject();
/* 433 */     for (int i = 0; i < this.size; i++) s.writeObject(this.a[i]); 
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 437 */     s.defaultReadObject();
/* 438 */     this.a = ((Object[])new Object[this.size]);
/* 439 */     for (int i = 0; i < this.size; i++) this.a[i] = s.readObject();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceArrayList
 * JD-Core Version:    0.6.2
 */