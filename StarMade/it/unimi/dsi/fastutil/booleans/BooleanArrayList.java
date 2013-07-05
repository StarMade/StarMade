/*     */ package it.unimi.dsi.fastutil.booleans;
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
/*     */ public class BooleanArrayList extends AbstractBooleanList
/*     */   implements RandomAccess, Cloneable, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -7046029254386353130L;
/*     */   public static final int DEFAULT_INITIAL_CAPACITY = 16;
/*     */   protected static final long ONEOVERPHI = 106039L;
/*     */   protected transient boolean[] a;
/*     */   protected int size;
/*     */   private static final boolean ASSERTS = false;
/*     */ 
/*     */   protected BooleanArrayList(boolean[] a, boolean dummy)
/*     */   {
/*  90 */     this.a = a;
/*     */   }
/*     */ 
/*     */   public BooleanArrayList(int capacity)
/*     */   {
/*  98 */     if (capacity < 0) throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
/*  99 */     this.a = new boolean[capacity];
/*     */   }
/*     */ 
/*     */   public BooleanArrayList()
/*     */   {
/* 104 */     this(16);
/*     */   }
/*     */ 
/*     */   public BooleanArrayList(Collection<? extends Boolean> c)
/*     */   {
/* 111 */     this(c.size());
/* 112 */     this.size = BooleanIterators.unwrap(BooleanIterators.asBooleanIterator(c.iterator()), this.a);
/*     */   }
/*     */ 
/*     */   public BooleanArrayList(BooleanCollection c)
/*     */   {
/* 119 */     this(c.size());
/* 120 */     this.size = BooleanIterators.unwrap(c.iterator(), this.a);
/*     */   }
/*     */ 
/*     */   public BooleanArrayList(BooleanList l)
/*     */   {
/* 127 */     this(l.size());
/* 128 */     l.getElements(0, this.a, 0, this.size = l.size());
/*     */   }
/*     */ 
/*     */   public BooleanArrayList(boolean[] a)
/*     */   {
/* 135 */     this(a, 0, a.length);
/*     */   }
/*     */ 
/*     */   public BooleanArrayList(boolean[] a, int offset, int length)
/*     */   {
/* 144 */     this(length);
/* 145 */     System.arraycopy(a, offset, this.a, 0, length);
/* 146 */     this.size = length;
/*     */   }
/*     */ 
/*     */   public BooleanArrayList(Iterator<? extends Boolean> i)
/*     */   {
/* 153 */     this();
/* 154 */     while (i.hasNext()) add((Boolean)i.next());
/*     */   }
/*     */ 
/*     */   public BooleanArrayList(BooleanIterator i)
/*     */   {
/* 161 */     this();
/* 162 */     while (i.hasNext()) add(i.nextBoolean());
/*     */   }
/*     */ 
/*     */   public boolean[] elements()
/*     */   {
/* 169 */     return this.a;
/*     */   }
/*     */ 
/*     */   public static BooleanArrayList wrap(boolean[] a, int length)
/*     */   {
/* 178 */     if (length > a.length) throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + a.length + ")");
/* 179 */     BooleanArrayList l = new BooleanArrayList(a, false);
/* 180 */     l.size = length;
/* 181 */     return l;
/*     */   }
/*     */ 
/*     */   public static BooleanArrayList wrap(boolean[] a)
/*     */   {
/* 189 */     return wrap(a, a.length);
/*     */   }
/*     */ 
/*     */   public void ensureCapacity(int capacity)
/*     */   {
/* 197 */     this.a = BooleanArrays.ensureCapacity(this.a, capacity, this.size);
/*     */   }
/*     */ 
/*     */   private void grow(int capacity)
/*     */   {
/* 207 */     this.a = BooleanArrays.grow(this.a, capacity, this.size);
/*     */   }
/*     */ 
/*     */   public void add(int index, boolean k) {
/* 211 */     ensureIndex(index);
/* 212 */     grow(this.size + 1);
/* 213 */     if (index != this.size) System.arraycopy(this.a, index, this.a, index + 1, this.size - index);
/* 214 */     this.a[index] = k;
/* 215 */     this.size += 1;
/*     */   }
/*     */ 
/*     */   public boolean add(boolean k) {
/* 219 */     grow(this.size + 1);
/* 220 */     this.a[(this.size++)] = k;
/*     */ 
/* 222 */     return true;
/*     */   }
/*     */   public boolean getBoolean(int index) {
/* 225 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 226 */     return this.a[index];
/*     */   }
/*     */   public int indexOf(boolean k) {
/* 229 */     for (int i = 0; i < this.size; i++) if (k == this.a[i]) return i;
/* 230 */     return -1;
/*     */   }
/*     */   public int lastIndexOf(boolean k) {
/* 233 */     for (int i = this.size; i-- != 0; ) if (k == this.a[i]) return i;
/* 234 */     return -1;
/*     */   }
/*     */   public boolean removeBoolean(int index) {
/* 237 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 238 */     boolean old = this.a[index];
/* 239 */     this.size -= 1;
/* 240 */     if (index != this.size) System.arraycopy(this.a, index + 1, this.a, index, this.size - index);
/*     */ 
/* 242 */     return old;
/*     */   }
/*     */   public boolean rem(boolean k) {
/* 245 */     int index = indexOf(k);
/* 246 */     if (index == -1) return false;
/* 247 */     removeBoolean(index);
/*     */ 
/* 249 */     return true;
/*     */   }
/*     */   public boolean set(int index, boolean k) {
/* 252 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 253 */     boolean old = this.a[index];
/* 254 */     this.a[index] = k;
/* 255 */     return old;
/*     */   }
/*     */   public void clear() {
/* 258 */     this.size = 0;
/*     */   }
/*     */ 
/*     */   public int size() {
/* 262 */     return this.size;
/*     */   }
/*     */   public void size(int size) {
/* 265 */     if (size > this.a.length) ensureCapacity(size);
/* 266 */     if (size > this.size) BooleanArrays.fill(this.a, this.size, size, false);
/* 267 */     this.size = size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 270 */     return this.size == 0;
/*     */   }
/*     */ 
/*     */   public void trim()
/*     */   {
/* 277 */     trim(0);
/*     */   }
/*     */ 
/*     */   public void trim(int n)
/*     */   {
/* 296 */     if ((n >= this.a.length) || (this.size == this.a.length)) return;
/* 297 */     boolean[] t = new boolean[Math.max(n, this.size)];
/* 298 */     System.arraycopy(this.a, 0, t, 0, this.size);
/* 299 */     this.a = t;
/*     */   }
/*     */ 
/*     */   public void getElements(int from, boolean[] a, int offset, int length)
/*     */   {
/* 310 */     BooleanArrays.ensureOffsetLength(a, offset, length);
/* 311 */     System.arraycopy(this.a, from, a, offset, length);
/*     */   }
/*     */ 
/*     */   public void removeElements(int from, int to)
/*     */   {
/* 319 */     Arrays.ensureFromTo(this.size, from, to);
/* 320 */     System.arraycopy(this.a, to, this.a, from, this.size - to);
/* 321 */     this.size -= to - from;
/*     */   }
/*     */ 
/*     */   public void addElements(int index, boolean[] a, int offset, int length)
/*     */   {
/* 331 */     ensureIndex(index);
/* 332 */     BooleanArrays.ensureOffsetLength(a, offset, length);
/* 333 */     grow(this.size + length);
/* 334 */     System.arraycopy(this.a, index, this.a, index + length, this.size - index);
/* 335 */     System.arraycopy(a, offset, this.a, index, length);
/* 336 */     this.size += length;
/*     */   }
/*     */   public boolean[] toBooleanArray(boolean[] a) {
/* 339 */     if ((a == null) || (a.length < this.size)) a = new boolean[this.size];
/* 340 */     System.arraycopy(this.a, 0, a, 0, this.size);
/* 341 */     return a;
/*     */   }
/*     */   public boolean addAll(int index, BooleanCollection c) {
/* 344 */     ensureIndex(index);
/* 345 */     int n = c.size();
/* 346 */     if (n == 0) return false;
/* 347 */     grow(this.size + n);
/* 348 */     if (index != this.size) System.arraycopy(this.a, index, this.a, index + n, this.size - index);
/* 349 */     BooleanIterator i = c.iterator();
/* 350 */     this.size += n;
/* 351 */     while (n-- != 0) this.a[(index++)] = i.nextBoolean();
/*     */ 
/* 353 */     return true;
/*     */   }
/*     */   public boolean addAll(int index, BooleanList l) {
/* 356 */     ensureIndex(index);
/* 357 */     int n = l.size();
/* 358 */     if (n == 0) return false;
/* 359 */     grow(this.size + n);
/* 360 */     if (index != this.size) System.arraycopy(this.a, index, this.a, index + n, this.size - index);
/* 361 */     l.getElements(0, this.a, index, n);
/* 362 */     this.size += n;
/*     */ 
/* 364 */     return true;
/*     */   }
/*     */   public BooleanListIterator listIterator(final int index) {
/* 367 */     ensureIndex(index);
/* 368 */     return new AbstractBooleanListIterator() {
/* 369 */       int pos = index; int last = -1;
/*     */ 
/* 370 */       public boolean hasNext() { return this.pos < BooleanArrayList.this.size; } 
/* 371 */       public boolean hasPrevious() { return this.pos > 0; } 
/* 372 */       public boolean nextBoolean() { if (!hasNext()) throw new NoSuchElementException(); return BooleanArrayList.this.a[(this.last = this.pos++)]; } 
/* 373 */       public boolean previousBoolean() { if (!hasPrevious()) throw new NoSuchElementException(); return BooleanArrayList.this.a[(this.last = --this.pos)]; } 
/* 374 */       public int nextIndex() { return this.pos; } 
/* 375 */       public int previousIndex() { return this.pos - 1; } 
/*     */       public void add(boolean k) {
/* 377 */         if (this.last == -1) throw new IllegalStateException();
/* 378 */         BooleanArrayList.this.add(this.pos++, k);
/* 379 */         this.last = -1;
/*     */       }
/*     */       public void set(boolean k) {
/* 382 */         if (this.last == -1) throw new IllegalStateException();
/* 383 */         BooleanArrayList.this.set(this.last, k);
/*     */       }
/*     */       public void remove() {
/* 386 */         if (this.last == -1) throw new IllegalStateException();
/* 387 */         BooleanArrayList.this.removeBoolean(this.last);
/*     */ 
/* 389 */         if (this.last < this.pos) this.pos -= 1;
/* 390 */         this.last = -1;
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public BooleanArrayList clone() {
/* 396 */     BooleanArrayList c = new BooleanArrayList(this.size);
/* 397 */     System.arraycopy(this.a, 0, c.a, 0, this.size);
/* 398 */     c.size = this.size;
/* 399 */     return c;
/*     */   }
/*     */ 
/*     */   public boolean equals(BooleanArrayList l)
/*     */   {
/* 410 */     if (l == this) return true;
/* 411 */     int s = size();
/* 412 */     if (s != l.size()) return false;
/* 413 */     boolean[] a1 = this.a;
/* 414 */     boolean[] a2 = l.a;
/* 415 */     while (s-- != 0) if (a1[s] != a2[s]) return false;
/* 416 */     return true;
/*     */   }
/*     */ 
/*     */   public int compareTo(BooleanArrayList l)
/*     */   {
/* 430 */     int s1 = size(); int s2 = l.size();
/* 431 */     boolean[] a1 = this.a; boolean[] a2 = l.a;
/*     */ 
/* 434 */     for (int i = 0; (i < s1) && (i < s2); i++) {
/* 435 */       boolean e1 = a1[i];
/* 436 */       boolean e2 = a2[i];
/*     */       int r;
/* 437 */       if ((r = e1 == e2 ? 0 : (!e1) && (e2) ? -1 : 1) != 0) return r;
/*     */     }
/* 439 */     return i < s1 ? 1 : i < s2 ? -1 : 0;
/*     */   }
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 442 */     s.defaultWriteObject();
/* 443 */     for (int i = 0; i < this.size; i++) s.writeBoolean(this.a[i]); 
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 447 */     s.defaultReadObject();
/* 448 */     this.a = new boolean[this.size];
/* 449 */     for (int i = 0; i < this.size; i++) this.a[i] = s.readBoolean();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanArrayList
 * JD-Core Version:    0.6.2
 */