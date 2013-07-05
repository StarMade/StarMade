/*     */ package it.unimi.dsi.fastutil.floats;
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
/*     */ public class FloatBigArrayBigList extends AbstractFloatBigList
/*     */   implements RandomAccess, Cloneable, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -7046029254386353130L;
/*     */   public static final int DEFAULT_INITIAL_CAPACITY = 16;
/*     */   protected static final long ONEOVERPHI = 106039L;
/*     */   protected transient float[][] a;
/*     */   protected long size;
/*     */   private static final boolean ASSERTS = false;
/*     */ 
/*     */   protected FloatBigArrayBigList(float[][] a, boolean dummy)
/*     */   {
/*  89 */     this.a = a;
/*     */   }
/*     */ 
/*     */   public FloatBigArrayBigList(long capacity)
/*     */   {
/*  97 */     if (capacity < 0L) throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
/*  98 */     this.a = FloatBigArrays.newBigArray(capacity);
/*     */   }
/*     */ 
/*     */   public FloatBigArrayBigList()
/*     */   {
/* 103 */     this(16L);
/*     */   }
/*     */ 
/*     */   public FloatBigArrayBigList(FloatCollection c)
/*     */   {
/* 110 */     this(c.size());
/* 111 */     for (FloatIterator i = c.iterator(); i.hasNext(); add(i.nextFloat()));
/*     */   }
/*     */ 
/*     */   public FloatBigArrayBigList(FloatBigList l)
/*     */   {
/* 118 */     this(l.size64());
/* 119 */     l.getElements(0L, this.a, 0L, this.size = l.size64());
/*     */   }
/*     */ 
/*     */   public FloatBigArrayBigList(float[][] a)
/*     */   {
/* 131 */     this(a, 0L, FloatBigArrays.length(a));
/*     */   }
/*     */ 
/*     */   public FloatBigArrayBigList(float[][] a, long offset, long length)
/*     */   {
/* 145 */     this(length);
/* 146 */     FloatBigArrays.copy(a, offset, this.a, 0L, length);
/* 147 */     this.size = length;
/*     */   }
/*     */ 
/*     */   public FloatBigArrayBigList(Iterator<? extends Float> i)
/*     */   {
/* 154 */     this();
/* 155 */     while (i.hasNext()) add((Float)i.next());
/*     */   }
/*     */ 
/*     */   public FloatBigArrayBigList(FloatIterator i)
/*     */   {
/* 162 */     this();
/* 163 */     while (i.hasNext()) add(i.nextFloat());
/*     */   }
/*     */ 
/*     */   public float[][] elements()
/*     */   {
/* 170 */     return this.a;
/*     */   }
/*     */ 
/*     */   public static FloatBigArrayBigList wrap(float[][] a, long length)
/*     */   {
/* 179 */     if (length > FloatBigArrays.length(a)) throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + FloatBigArrays.length(a) + ")");
/* 180 */     FloatBigArrayBigList l = new FloatBigArrayBigList(a, false);
/* 181 */     l.size = length;
/* 182 */     return l;
/*     */   }
/*     */ 
/*     */   public static FloatBigArrayBigList wrap(float[][] a)
/*     */   {
/* 190 */     return wrap(a, FloatBigArrays.length(a));
/*     */   }
/*     */ 
/*     */   public void ensureCapacity(long capacity)
/*     */   {
/* 198 */     this.a = FloatBigArrays.ensureCapacity(this.a, capacity, this.size);
/*     */   }
/*     */ 
/*     */   private void grow(long capacity)
/*     */   {
/* 208 */     this.a = FloatBigArrays.grow(this.a, capacity, this.size);
/*     */   }
/*     */ 
/*     */   public void add(long index, float k) {
/* 212 */     ensureIndex(index);
/* 213 */     grow(this.size + 1L);
/* 214 */     if (index != this.size) FloatBigArrays.copy(this.a, index, this.a, index + 1L, this.size - index);
/* 215 */     FloatBigArrays.set(this.a, index, k);
/* 216 */     this.size += 1L;
/*     */   }
/*     */ 
/*     */   public boolean add(float k) {
/* 220 */     grow(this.size + 1L);
/* 221 */     FloatBigArrays.set(this.a, this.size++, k);
/*     */ 
/* 223 */     return true;
/*     */   }
/*     */   public float getFloat(long index) {
/* 226 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 227 */     return FloatBigArrays.get(this.a, index);
/*     */   }
/*     */   public long indexOf(float k) {
/* 230 */     for (long i = 0L; i < this.size; i += 1L) if (k == FloatBigArrays.get(this.a, i)) return i;
/* 231 */     return -1L;
/*     */   }
/*     */   public long lastIndexOf(float k) {
/* 234 */     for (long i = this.size; i-- != 0L; ) if (k == FloatBigArrays.get(this.a, i)) return i;
/* 235 */     return -1L;
/*     */   }
/*     */   public float removeFloat(long index) {
/* 238 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 239 */     float old = FloatBigArrays.get(this.a, index);
/* 240 */     this.size -= 1L;
/* 241 */     if (index != this.size) FloatBigArrays.copy(this.a, index + 1L, this.a, index, this.size - index);
/*     */ 
/* 243 */     return old;
/*     */   }
/*     */   public boolean rem(float k) {
/* 246 */     long index = indexOf(k);
/* 247 */     if (index == -1L) return false;
/* 248 */     removeFloat(index);
/*     */ 
/* 250 */     return true;
/*     */   }
/*     */   public float set(long index, float k) {
/* 253 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 254 */     float old = FloatBigArrays.get(this.a, index);
/* 255 */     FloatBigArrays.set(this.a, index, k);
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
/* 266 */     if (size > FloatBigArrays.length(this.a)) ensureCapacity(size);
/* 267 */     if (size > this.size) FloatBigArrays.fill(this.a, this.size, size, 0.0F);
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
/* 296 */     long arrayLength = FloatBigArrays.length(this.a);
/* 297 */     if ((n >= arrayLength) || (this.size == arrayLength)) return;
/* 298 */     this.a = FloatBigArrays.trim(this.a, Math.max(n, this.size));
/*     */   }
/*     */ 
/*     */   public void getElements(int from, float[][] a, long offset, long length)
/*     */   {
/* 309 */     FloatBigArrays.copy(this.a, from, a, offset, length);
/*     */   }
/*     */ 
/*     */   public void removeElements(int from, int to)
/*     */   {
/* 317 */     BigArrays.ensureFromTo(this.size, from, to);
/* 318 */     FloatBigArrays.copy(this.a, to, this.a, from, this.size - to);
/* 319 */     this.size -= to - from;
/*     */   }
/*     */ 
/*     */   public void addElements(int index, float[][] a, long offset, long length)
/*     */   {
/* 329 */     ensureIndex(index);
/* 330 */     FloatBigArrays.ensureOffsetLength(a, offset, length);
/* 331 */     grow(this.size + length);
/* 332 */     FloatBigArrays.copy(this.a, index, this.a, index + length, this.size - index);
/* 333 */     FloatBigArrays.copy(a, offset, this.a, index, length);
/* 334 */     this.size += length;
/*     */   }
/*     */   public FloatBigListIterator listIterator(final int index) {
/* 337 */     ensureIndex(index);
/* 338 */     return new AbstractFloatBigListIterator() {
/* 339 */       int pos = index; int last = -1;
/*     */ 
/* 340 */       public boolean hasNext() { return this.pos < FloatBigArrayBigList.this.size; } 
/* 341 */       public boolean hasPrevious() { return this.pos > 0; } 
/* 342 */       public float nextFloat() { if (!hasNext()) throw new NoSuchElementException(); return FloatBigArrays.get(FloatBigArrayBigList.this.a, this.last = this.pos++); } 
/* 343 */       public float previousFloat() { if (!hasPrevious()) throw new NoSuchElementException(); return FloatBigArrays.get(FloatBigArrayBigList.this.a, this.last = --this.pos); } 
/* 344 */       public long nextIndex() { return this.pos; } 
/* 345 */       public long previousIndex() { return this.pos - 1; } 
/*     */       public void add(float k) {
/* 347 */         if (this.last == -1) throw new IllegalStateException();
/* 348 */         FloatBigArrayBigList.this.add(this.pos++, k);
/* 349 */         this.last = -1;
/*     */       }
/*     */       public void set(float k) {
/* 352 */         if (this.last == -1) throw new IllegalStateException();
/* 353 */         FloatBigArrayBigList.this.set(this.last, k);
/*     */       }
/*     */       public void remove() {
/* 356 */         if (this.last == -1) throw new IllegalStateException();
/* 357 */         FloatBigArrayBigList.this.removeFloat(this.last);
/*     */ 
/* 359 */         if (this.last < this.pos) this.pos -= 1;
/* 360 */         this.last = -1;
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public FloatBigArrayBigList clone() {
/* 366 */     FloatBigArrayBigList c = new FloatBigArrayBigList(this.size);
/* 367 */     FloatBigArrays.copy(this.a, 0L, c.a, 0L, this.size);
/* 368 */     c.size = this.size;
/* 369 */     return c;
/*     */   }
/*     */ 
/*     */   public boolean equals(FloatBigArrayBigList l)
/*     */   {
/* 380 */     if (l == this) return true;
/* 381 */     long s = size64();
/* 382 */     if (s != l.size64()) return false;
/* 383 */     float[][] a1 = this.a;
/* 384 */     float[][] a2 = l.a;
/* 385 */     while (s-- != 0L) if (FloatBigArrays.get(a1, s) != FloatBigArrays.get(a2, s)) return false;
/* 386 */     return true;
/*     */   }
/*     */ 
/*     */   public int compareTo(FloatBigArrayBigList l)
/*     */   {
/* 400 */     long s1 = size64(); long s2 = l.size64();
/* 401 */     float[][] a1 = this.a; float[][] a2 = l.a;
/*     */ 
/* 404 */     for (int i = 0; (i < s1) && (i < s2); i++) {
/* 405 */       float e1 = FloatBigArrays.get(a1, i);
/* 406 */       float e2 = FloatBigArrays.get(a2, i);
/*     */       int r;
/* 407 */       if ((r = e1 == e2 ? 0 : e1 < e2 ? -1 : 1) != 0) return r;
/*     */     }
/* 409 */     return i < s1 ? 1 : i < s2 ? -1 : 0;
/*     */   }
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 412 */     s.defaultWriteObject();
/* 413 */     for (int i = 0; i < this.size; i++) s.writeFloat(FloatBigArrays.get(this.a, i)); 
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 417 */     s.defaultReadObject();
/* 418 */     this.a = FloatBigArrays.newBigArray(this.size);
/* 419 */     for (int i = 0; i < this.size; i++) FloatBigArrays.set(this.a, i, s.readFloat());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatBigArrayBigList
 * JD-Core Version:    0.6.2
 */