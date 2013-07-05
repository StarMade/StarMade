/*     */ package it.unimi.dsi.fastutil.doubles;
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
/*     */ public class DoubleBigArrayBigList extends AbstractDoubleBigList
/*     */   implements RandomAccess, Cloneable, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -7046029254386353130L;
/*     */   public static final int DEFAULT_INITIAL_CAPACITY = 16;
/*     */   protected static final long ONEOVERPHI = 106039L;
/*     */   protected transient double[][] a;
/*     */   protected long size;
/*     */   private static final boolean ASSERTS = false;
/*     */ 
/*     */   protected DoubleBigArrayBigList(double[][] a, boolean dummy)
/*     */   {
/*  89 */     this.a = a;
/*     */   }
/*     */ 
/*     */   public DoubleBigArrayBigList(long capacity)
/*     */   {
/*  97 */     if (capacity < 0L) throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
/*  98 */     this.a = DoubleBigArrays.newBigArray(capacity);
/*     */   }
/*     */ 
/*     */   public DoubleBigArrayBigList()
/*     */   {
/* 103 */     this(16L);
/*     */   }
/*     */ 
/*     */   public DoubleBigArrayBigList(DoubleCollection c)
/*     */   {
/* 110 */     this(c.size());
/* 111 */     for (DoubleIterator i = c.iterator(); i.hasNext(); add(i.nextDouble()));
/*     */   }
/*     */ 
/*     */   public DoubleBigArrayBigList(DoubleBigList l)
/*     */   {
/* 118 */     this(l.size64());
/* 119 */     l.getElements(0L, this.a, 0L, this.size = l.size64());
/*     */   }
/*     */ 
/*     */   public DoubleBigArrayBigList(double[][] a)
/*     */   {
/* 131 */     this(a, 0L, DoubleBigArrays.length(a));
/*     */   }
/*     */ 
/*     */   public DoubleBigArrayBigList(double[][] a, long offset, long length)
/*     */   {
/* 145 */     this(length);
/* 146 */     DoubleBigArrays.copy(a, offset, this.a, 0L, length);
/* 147 */     this.size = length;
/*     */   }
/*     */ 
/*     */   public DoubleBigArrayBigList(Iterator<? extends Double> i)
/*     */   {
/* 154 */     this();
/* 155 */     while (i.hasNext()) add((Double)i.next());
/*     */   }
/*     */ 
/*     */   public DoubleBigArrayBigList(DoubleIterator i)
/*     */   {
/* 162 */     this();
/* 163 */     while (i.hasNext()) add(i.nextDouble());
/*     */   }
/*     */ 
/*     */   public double[][] elements()
/*     */   {
/* 170 */     return this.a;
/*     */   }
/*     */ 
/*     */   public static DoubleBigArrayBigList wrap(double[][] a, long length)
/*     */   {
/* 179 */     if (length > DoubleBigArrays.length(a)) throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + DoubleBigArrays.length(a) + ")");
/* 180 */     DoubleBigArrayBigList l = new DoubleBigArrayBigList(a, false);
/* 181 */     l.size = length;
/* 182 */     return l;
/*     */   }
/*     */ 
/*     */   public static DoubleBigArrayBigList wrap(double[][] a)
/*     */   {
/* 190 */     return wrap(a, DoubleBigArrays.length(a));
/*     */   }
/*     */ 
/*     */   public void ensureCapacity(long capacity)
/*     */   {
/* 198 */     this.a = DoubleBigArrays.ensureCapacity(this.a, capacity, this.size);
/*     */   }
/*     */ 
/*     */   private void grow(long capacity)
/*     */   {
/* 208 */     this.a = DoubleBigArrays.grow(this.a, capacity, this.size);
/*     */   }
/*     */ 
/*     */   public void add(long index, double k) {
/* 212 */     ensureIndex(index);
/* 213 */     grow(this.size + 1L);
/* 214 */     if (index != this.size) DoubleBigArrays.copy(this.a, index, this.a, index + 1L, this.size - index);
/* 215 */     DoubleBigArrays.set(this.a, index, k);
/* 216 */     this.size += 1L;
/*     */   }
/*     */ 
/*     */   public boolean add(double k) {
/* 220 */     grow(this.size + 1L);
/* 221 */     DoubleBigArrays.set(this.a, this.size++, k);
/*     */ 
/* 223 */     return true;
/*     */   }
/*     */   public double getDouble(long index) {
/* 226 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 227 */     return DoubleBigArrays.get(this.a, index);
/*     */   }
/*     */   public long indexOf(double k) {
/* 230 */     for (long i = 0L; i < this.size; i += 1L) if (k == DoubleBigArrays.get(this.a, i)) return i;
/* 231 */     return -1L;
/*     */   }
/*     */   public long lastIndexOf(double k) {
/* 234 */     for (long i = this.size; i-- != 0L; ) if (k == DoubleBigArrays.get(this.a, i)) return i;
/* 235 */     return -1L;
/*     */   }
/*     */   public double removeDouble(long index) {
/* 238 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 239 */     double old = DoubleBigArrays.get(this.a, index);
/* 240 */     this.size -= 1L;
/* 241 */     if (index != this.size) DoubleBigArrays.copy(this.a, index + 1L, this.a, index, this.size - index);
/*     */ 
/* 243 */     return old;
/*     */   }
/*     */   public boolean rem(double k) {
/* 246 */     long index = indexOf(k);
/* 247 */     if (index == -1L) return false;
/* 248 */     removeDouble(index);
/*     */ 
/* 250 */     return true;
/*     */   }
/*     */   public double set(long index, double k) {
/* 253 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 254 */     double old = DoubleBigArrays.get(this.a, index);
/* 255 */     DoubleBigArrays.set(this.a, index, k);
/* 256 */     return old;
/*     */   }
/*     */   public void clear() {
/* 259 */     this.size = 0L;
/*     */   }
/*     */ 
/*     */   public long size64() {
/* 263 */     return this.size;
/*     */   }
/*     */   public void size(long size) {
/* 266 */     if (size > DoubleBigArrays.length(this.a)) ensureCapacity(size);
/* 267 */     if (size > this.size) DoubleBigArrays.fill(this.a, this.size, size, 0.0D);
/* 268 */     this.size = size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 271 */     return this.size == 0L;
/*     */   }
/*     */ 
/*     */   public void trim()
/*     */   {
/* 278 */     trim(0L);
/*     */   }
/*     */ 
/*     */   public void trim(long n)
/*     */   {
/* 296 */     long arrayLength = DoubleBigArrays.length(this.a);
/* 297 */     if ((n >= arrayLength) || (this.size == arrayLength)) return;
/* 298 */     this.a = DoubleBigArrays.trim(this.a, Math.max(n, this.size));
/*     */   }
/*     */ 
/*     */   public void getElements(int from, double[][] a, long offset, long length)
/*     */   {
/* 309 */     DoubleBigArrays.copy(this.a, from, a, offset, length);
/*     */   }
/*     */ 
/*     */   public void removeElements(int from, int to)
/*     */   {
/* 317 */     BigArrays.ensureFromTo(this.size, from, to);
/* 318 */     DoubleBigArrays.copy(this.a, to, this.a, from, this.size - to);
/* 319 */     this.size -= to - from;
/*     */   }
/*     */ 
/*     */   public void addElements(int index, double[][] a, long offset, long length)
/*     */   {
/* 329 */     ensureIndex(index);
/* 330 */     DoubleBigArrays.ensureOffsetLength(a, offset, length);
/* 331 */     grow(this.size + length);
/* 332 */     DoubleBigArrays.copy(this.a, index, this.a, index + length, this.size - index);
/* 333 */     DoubleBigArrays.copy(a, offset, this.a, index, length);
/* 334 */     this.size += length;
/*     */   }
/*     */   public DoubleBigListIterator listIterator(final int index) {
/* 337 */     ensureIndex(index);
/* 338 */     return new AbstractDoubleBigListIterator() {
/* 339 */       int pos = index; int last = -1;
/*     */ 
/* 340 */       public boolean hasNext() { return this.pos < DoubleBigArrayBigList.this.size; } 
/* 341 */       public boolean hasPrevious() { return this.pos > 0; } 
/* 342 */       public double nextDouble() { if (!hasNext()) throw new NoSuchElementException(); return DoubleBigArrays.get(DoubleBigArrayBigList.this.a, this.last = this.pos++); } 
/* 343 */       public double previousDouble() { if (!hasPrevious()) throw new NoSuchElementException(); return DoubleBigArrays.get(DoubleBigArrayBigList.this.a, this.last = --this.pos); } 
/* 344 */       public long nextIndex() { return this.pos; } 
/* 345 */       public long previousIndex() { return this.pos - 1; } 
/*     */       public void add(double k) {
/* 347 */         if (this.last == -1) throw new IllegalStateException();
/* 348 */         DoubleBigArrayBigList.this.add(this.pos++, k);
/* 349 */         this.last = -1;
/*     */       }
/*     */       public void set(double k) {
/* 352 */         if (this.last == -1) throw new IllegalStateException();
/* 353 */         DoubleBigArrayBigList.this.set(this.last, k);
/*     */       }
/*     */       public void remove() {
/* 356 */         if (this.last == -1) throw new IllegalStateException();
/* 357 */         DoubleBigArrayBigList.this.removeDouble(this.last);
/*     */ 
/* 359 */         if (this.last < this.pos) this.pos -= 1;
/* 360 */         this.last = -1;
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public DoubleBigArrayBigList clone() {
/* 366 */     DoubleBigArrayBigList c = new DoubleBigArrayBigList(this.size);
/* 367 */     DoubleBigArrays.copy(this.a, 0L, c.a, 0L, this.size);
/* 368 */     c.size = this.size;
/* 369 */     return c;
/*     */   }
/*     */ 
/*     */   public boolean equals(DoubleBigArrayBigList l)
/*     */   {
/* 380 */     if (l == this) return true;
/* 381 */     long s = size64();
/* 382 */     if (s != l.size64()) return false;
/* 383 */     double[][] a1 = this.a;
/* 384 */     double[][] a2 = l.a;
/* 385 */     while (s-- != 0L) if (DoubleBigArrays.get(a1, s) != DoubleBigArrays.get(a2, s)) return false;
/* 386 */     return true;
/*     */   }
/*     */ 
/*     */   public int compareTo(DoubleBigArrayBigList l)
/*     */   {
/* 400 */     long s1 = size64(); long s2 = l.size64();
/* 401 */     double[][] a1 = this.a; double[][] a2 = l.a;
/*     */ 
/* 404 */     for (int i = 0; (i < s1) && (i < s2); i++) {
/* 405 */       double e1 = DoubleBigArrays.get(a1, i);
/* 406 */       double e2 = DoubleBigArrays.get(a2, i);
/*     */       int r;
/* 407 */       if ((r = e1 == e2 ? 0 : e1 < e2 ? -1 : 1) != 0) return r;
/*     */     }
/* 409 */     return i < s1 ? 1 : i < s2 ? -1 : 0;
/*     */   }
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 412 */     s.defaultWriteObject();
/* 413 */     for (int i = 0; i < this.size; i++) s.writeDouble(DoubleBigArrays.get(this.a, i)); 
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 417 */     s.defaultReadObject();
/* 418 */     this.a = DoubleBigArrays.newBigArray(this.size);
/* 419 */     for (int i = 0; i < this.size; i++) DoubleBigArrays.set(this.a, i, s.readDouble());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleBigArrayBigList
 * JD-Core Version:    0.6.2
 */