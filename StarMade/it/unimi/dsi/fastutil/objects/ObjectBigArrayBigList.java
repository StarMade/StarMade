/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.BigArrays;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.RandomAccess;
/*     */ 
/*     */ public class ObjectBigArrayBigList<K> extends AbstractObjectBigList<K>
/*     */   implements RandomAccess, Cloneable, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -7046029254386353131L;
/*     */   public static final int DEFAULT_INITIAL_CAPACITY = 16;
/*     */   protected static final long ONEOVERPHI = 106039L;
/*     */   protected final boolean wrapped;
/*     */   protected transient K[][] a;
/*     */   protected long size;
/*     */   private static final boolean ASSERTS = false;
/*     */ 
/*     */   protected ObjectBigArrayBigList(K[][] a, boolean dummy)
/*     */   {
/*  96 */     this.a = a;
/*  97 */     this.wrapped = true;
/*     */   }
/*     */ 
/*     */   public ObjectBigArrayBigList(long capacity)
/*     */   {
/* 105 */     if (capacity < 0L) throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
/* 106 */     this.a = ((Object[][])ObjectBigArrays.newBigArray(capacity));
/* 107 */     this.wrapped = false;
/*     */   }
/*     */ 
/*     */   public ObjectBigArrayBigList()
/*     */   {
/* 112 */     this(16L);
/*     */   }
/*     */ 
/*     */   public ObjectBigArrayBigList(ObjectCollection<? extends K> c)
/*     */   {
/* 119 */     this(c.size());
/* 120 */     for (ObjectIterator i = c.iterator(); i.hasNext(); add(i.next()));
/*     */   }
/*     */ 
/*     */   public ObjectBigArrayBigList(ObjectBigList<? extends K> l)
/*     */   {
/* 127 */     this(l.size64());
/* 128 */     l.getElements(0L, this.a, 0L, this.size = l.size64());
/*     */   }
/*     */ 
/*     */   public ObjectBigArrayBigList(K[][] a)
/*     */   {
/* 140 */     this(a, 0L, ObjectBigArrays.length(a));
/*     */   }
/*     */ 
/*     */   public ObjectBigArrayBigList(K[][] a, long offset, long length)
/*     */   {
/* 154 */     this(length);
/* 155 */     ObjectBigArrays.copy(a, offset, this.a, 0L, length);
/* 156 */     this.size = length;
/*     */   }
/*     */ 
/*     */   public ObjectBigArrayBigList(Iterator<? extends K> i)
/*     */   {
/* 163 */     this();
/* 164 */     while (i.hasNext()) add(i.next());
/*     */   }
/*     */ 
/*     */   public ObjectBigArrayBigList(ObjectIterator<? extends K> i)
/*     */   {
/* 171 */     this();
/* 172 */     while (i.hasNext()) add(i.next());
/*     */   }
/*     */ 
/*     */   public K[][] elements()
/*     */   {
/* 183 */     return this.a;
/*     */   }
/*     */ 
/*     */   public static <K> ObjectBigArrayBigList<K> wrap(K[][] a, long length)
/*     */   {
/* 192 */     if (length > ObjectBigArrays.length(a)) throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + ObjectBigArrays.length(a) + ")");
/* 193 */     ObjectBigArrayBigList l = new ObjectBigArrayBigList(a, false);
/* 194 */     l.size = length;
/* 195 */     return l;
/*     */   }
/*     */ 
/*     */   public static <K> ObjectBigArrayBigList<K> wrap(K[][] a)
/*     */   {
/* 203 */     return wrap(a, ObjectBigArrays.length(a));
/*     */   }
/*     */ 
/*     */   public void ensureCapacity(long capacity)
/*     */   {
/* 211 */     if (this.wrapped) { this.a = ObjectBigArrays.ensureCapacity(this.a, capacity, this.size); }
/* 213 */     else if (capacity > ObjectBigArrays.length(this.a)) {
/* 214 */       Object[][] t = ObjectBigArrays.newBigArray(capacity);
/* 215 */       ObjectBigArrays.copy(this.a, 0L, t, 0L, this.size);
/* 216 */       this.a = ((Object[][])t);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void grow(long capacity)
/*     */   {
/* 228 */     if (this.wrapped) { this.a = ObjectBigArrays.grow(this.a, capacity, this.size); }
/* 230 */     else if (capacity > ObjectBigArrays.length(this.a)) {
/* 231 */       int newLength = (int)Math.min(Math.max(106039L * ObjectBigArrays.length(this.a) >>> 16, capacity), 2147483647L);
/* 232 */       Object[][] t = ObjectBigArrays.newBigArray(newLength);
/* 233 */       ObjectBigArrays.copy(this.a, 0L, t, 0L, this.size);
/* 234 */       this.a = ((Object[][])t);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void add(long index, K k)
/*     */   {
/* 240 */     ensureIndex(index);
/* 241 */     grow(this.size + 1L);
/* 242 */     if (index != this.size) ObjectBigArrays.copy(this.a, index, this.a, index + 1L, this.size - index);
/* 243 */     ObjectBigArrays.set(this.a, index, k);
/* 244 */     this.size += 1L;
/*     */   }
/*     */ 
/*     */   public boolean add(K k) {
/* 248 */     grow(this.size + 1L);
/* 249 */     ObjectBigArrays.set(this.a, this.size++, k);
/*     */ 
/* 251 */     return true;
/*     */   }
/*     */   public K get(long index) {
/* 254 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 255 */     return ObjectBigArrays.get(this.a, index);
/*     */   }
/*     */   public long indexOf(Object k) {
/* 258 */     for (long i = 0L; i < this.size; i += 1L) if (k == null ? ObjectBigArrays.get(this.a, i) == null : k.equals(ObjectBigArrays.get(this.a, i))) return i;
/* 259 */     return -1L;
/*     */   }
/*     */   public long lastIndexOf(Object k) {
/* 262 */     for (long i = this.size; i-- != 0L; return i) label5: if (k == null ? ObjectBigArrays.get(this.a, i) != null : !k.equals(ObjectBigArrays.get(this.a, i)))
/*     */         break label5; return -1L;
/*     */   }
/*     */   public K remove(long index) {
/* 266 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 267 */     Object old = ObjectBigArrays.get(this.a, index);
/* 268 */     this.size -= 1L;
/* 269 */     if (index != this.size) ObjectBigArrays.copy(this.a, index + 1L, this.a, index, this.size - index);
/* 270 */     ObjectBigArrays.set(this.a, this.size, null);
/*     */ 
/* 272 */     return old;
/*     */   }
/*     */   public boolean rem(Object k) {
/* 275 */     long index = indexOf(k);
/* 276 */     if (index == -1L) return false;
/* 277 */     remove(index);
/*     */ 
/* 279 */     return true;
/*     */   }
/*     */   public boolean remove(Object o) {
/* 282 */     return rem(o);
/*     */   }
/*     */   public K set(long index, K k) {
/* 285 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 286 */     Object old = ObjectBigArrays.get(this.a, index);
/* 287 */     ObjectBigArrays.set(this.a, index, k);
/* 288 */     return old;
/*     */   }
/*     */   public void clear() {
/* 291 */     ObjectBigArrays.fill(this.a, 0L, this.size, null);
/* 292 */     this.size = 0L;
/*     */   }
/*     */ 
/*     */   public long size64() {
/* 296 */     return this.size;
/*     */   }
/*     */   public void size(long size) {
/* 299 */     if (size > ObjectBigArrays.length(this.a)) ensureCapacity(size);
/* 300 */     if (size > this.size) ObjectBigArrays.fill(this.a, this.size, size, null); else
/* 301 */       ObjectBigArrays.fill(this.a, size, this.size, null);
/* 302 */     this.size = size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 305 */     return this.size == 0L;
/*     */   }
/*     */ 
/*     */   public void trim()
/*     */   {
/* 312 */     trim(0L);
/*     */   }
/*     */ 
/*     */   public void trim(long n)
/*     */   {
/* 330 */     long arrayLength = ObjectBigArrays.length(this.a);
/* 331 */     if ((n >= arrayLength) || (this.size == arrayLength)) return;
/* 332 */     this.a = ObjectBigArrays.trim(this.a, Math.max(n, this.size));
/*     */   }
/*     */ 
/*     */   public void getElements(int from, Object[][] a, long offset, long length)
/*     */   {
/* 343 */     ObjectBigArrays.copy(this.a, from, a, offset, length);
/*     */   }
/*     */ 
/*     */   public void removeElements(int from, int to)
/*     */   {
/* 351 */     BigArrays.ensureFromTo(this.size, from, to);
/* 352 */     ObjectBigArrays.copy(this.a, to, this.a, from, this.size - to);
/* 353 */     this.size -= to - from;
/* 354 */     ObjectBigArrays.fill(this.a, this.size, this.size + to - from, null);
/*     */   }
/*     */ 
/*     */   public void addElements(int index, K[][] a, long offset, long length)
/*     */   {
/* 364 */     ensureIndex(index);
/* 365 */     ObjectBigArrays.ensureOffsetLength(a, offset, length);
/* 366 */     grow(this.size + length);
/* 367 */     ObjectBigArrays.copy(this.a, index, this.a, index + length, this.size - index);
/* 368 */     ObjectBigArrays.copy(a, offset, this.a, index, length);
/* 369 */     this.size += length;
/*     */   }
/*     */   public ObjectBigListIterator<K> listIterator(final int index) {
/* 372 */     ensureIndex(index);
/* 373 */     return new AbstractObjectBigListIterator() {
/* 374 */       int pos = index; int last = -1;
/*     */ 
/* 375 */       public boolean hasNext() { return this.pos < ObjectBigArrayBigList.this.size; } 
/* 376 */       public boolean hasPrevious() { return this.pos > 0; } 
/* 377 */       public K next() { if (!hasNext()) throw new NoSuchElementException(); return ObjectBigArrays.get(ObjectBigArrayBigList.this.a, this.last = this.pos++); } 
/* 378 */       public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return ObjectBigArrays.get(ObjectBigArrayBigList.this.a, this.last = --this.pos); } 
/* 379 */       public long nextIndex() { return this.pos; } 
/* 380 */       public long previousIndex() { return this.pos - 1; } 
/*     */       public void add(K k) {
/* 382 */         if (this.last == -1) throw new IllegalStateException();
/* 383 */         ObjectBigArrayBigList.this.add(this.pos++, k);
/* 384 */         this.last = -1;
/*     */       }
/*     */       public void set(K k) {
/* 387 */         if (this.last == -1) throw new IllegalStateException();
/* 388 */         ObjectBigArrayBigList.this.set(this.last, k);
/*     */       }
/*     */       public void remove() {
/* 391 */         if (this.last == -1) throw new IllegalStateException();
/* 392 */         ObjectBigArrayBigList.this.remove(this.last);
/*     */ 
/* 394 */         if (this.last < this.pos) this.pos -= 1;
/* 395 */         this.last = -1;
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectBigArrayBigList<K> clone() {
/* 401 */     ObjectBigArrayBigList c = new ObjectBigArrayBigList(this.size);
/* 402 */     ObjectBigArrays.copy(this.a, 0L, c.a, 0L, this.size);
/* 403 */     c.size = this.size;
/* 404 */     return c;
/*     */   }
/*     */   private boolean valEquals(K a, K b) {
/* 407 */     return a == null ? false : b == null ? true : a.equals(b);
/*     */   }
/*     */ 
/*     */   public boolean equals(ObjectBigArrayBigList<K> l)
/*     */   {
/* 418 */     if (l == this) return true;
/* 419 */     long s = size64();
/* 420 */     if (s != l.size64()) return false;
/* 421 */     Object[][] a1 = this.a;
/* 422 */     Object[][] a2 = l.a;
/* 423 */     while (s-- != 0L) if (!valEquals(ObjectBigArrays.get(a1, s), ObjectBigArrays.get(a2, s))) return false;
/* 424 */     return true;
/*     */   }
/*     */ 
/*     */   public int compareTo(ObjectBigArrayBigList<? extends K> l)
/*     */   {
/* 438 */     long s1 = size64(); long s2 = l.size64();
/* 439 */     Object[][] a1 = this.a; Object[][] a2 = l.a;
/*     */ 
/* 442 */     for (int i = 0; (i < s1) && (i < s2); i++) {
/* 443 */       Object e1 = ObjectBigArrays.get(a1, i);
/* 444 */       Object e2 = ObjectBigArrays.get(a2, i);
/*     */       int r;
/* 445 */       if ((r = ((Comparable)e1).compareTo(e2)) != 0) return r;
/*     */     }
/* 447 */     return i < s1 ? 1 : i < s2 ? -1 : 0;
/*     */   }
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 450 */     s.defaultWriteObject();
/* 451 */     for (int i = 0; i < this.size; i++) s.writeObject(ObjectBigArrays.get(this.a, i)); 
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 455 */     s.defaultReadObject();
/* 456 */     this.a = ((Object[][])ObjectBigArrays.newBigArray(this.size));
/* 457 */     for (int i = 0; i < this.size; i++) ObjectBigArrays.set(this.a, i, s.readObject());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectBigArrayBigList
 * JD-Core Version:    0.6.2
 */