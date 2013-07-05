/*     */ package it.unimi.dsi.fastutil.chars;
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
/*     */ public class CharArrayList extends AbstractCharList
/*     */   implements RandomAccess, Cloneable, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -7046029254386353130L;
/*     */   public static final int DEFAULT_INITIAL_CAPACITY = 16;
/*     */   protected static final long ONEOVERPHI = 106039L;
/*     */   protected transient char[] a;
/*     */   protected int size;
/*     */   private static final boolean ASSERTS = false;
/*     */ 
/*     */   protected CharArrayList(char[] a, boolean dummy)
/*     */   {
/*  90 */     this.a = a;
/*     */   }
/*     */ 
/*     */   public CharArrayList(int capacity)
/*     */   {
/*  98 */     if (capacity < 0) throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
/*  99 */     this.a = new char[capacity];
/*     */   }
/*     */ 
/*     */   public CharArrayList()
/*     */   {
/* 104 */     this(16);
/*     */   }
/*     */ 
/*     */   public CharArrayList(Collection<? extends Character> c)
/*     */   {
/* 111 */     this(c.size());
/* 112 */     this.size = CharIterators.unwrap(CharIterators.asCharIterator(c.iterator()), this.a);
/*     */   }
/*     */ 
/*     */   public CharArrayList(CharCollection c)
/*     */   {
/* 119 */     this(c.size());
/* 120 */     this.size = CharIterators.unwrap(c.iterator(), this.a);
/*     */   }
/*     */ 
/*     */   public CharArrayList(CharList l)
/*     */   {
/* 127 */     this(l.size());
/* 128 */     l.getElements(0, this.a, 0, this.size = l.size());
/*     */   }
/*     */ 
/*     */   public CharArrayList(char[] a)
/*     */   {
/* 135 */     this(a, 0, a.length);
/*     */   }
/*     */ 
/*     */   public CharArrayList(char[] a, int offset, int length)
/*     */   {
/* 144 */     this(length);
/* 145 */     System.arraycopy(a, offset, this.a, 0, length);
/* 146 */     this.size = length;
/*     */   }
/*     */ 
/*     */   public CharArrayList(Iterator<? extends Character> i)
/*     */   {
/* 153 */     this();
/* 154 */     while (i.hasNext()) add((Character)i.next());
/*     */   }
/*     */ 
/*     */   public CharArrayList(CharIterator i)
/*     */   {
/* 161 */     this();
/* 162 */     while (i.hasNext()) add(i.nextChar());
/*     */   }
/*     */ 
/*     */   public char[] elements()
/*     */   {
/* 169 */     return this.a;
/*     */   }
/*     */ 
/*     */   public static CharArrayList wrap(char[] a, int length)
/*     */   {
/* 178 */     if (length > a.length) throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + a.length + ")");
/* 179 */     CharArrayList l = new CharArrayList(a, false);
/* 180 */     l.size = length;
/* 181 */     return l;
/*     */   }
/*     */ 
/*     */   public static CharArrayList wrap(char[] a)
/*     */   {
/* 189 */     return wrap(a, a.length);
/*     */   }
/*     */ 
/*     */   public void ensureCapacity(int capacity)
/*     */   {
/* 197 */     this.a = CharArrays.ensureCapacity(this.a, capacity, this.size);
/*     */   }
/*     */ 
/*     */   private void grow(int capacity)
/*     */   {
/* 207 */     this.a = CharArrays.grow(this.a, capacity, this.size);
/*     */   }
/*     */ 
/*     */   public void add(int index, char k) {
/* 211 */     ensureIndex(index);
/* 212 */     grow(this.size + 1);
/* 213 */     if (index != this.size) System.arraycopy(this.a, index, this.a, index + 1, this.size - index);
/* 214 */     this.a[index] = k;
/* 215 */     this.size += 1;
/*     */   }
/*     */ 
/*     */   public boolean add(char k) {
/* 219 */     grow(this.size + 1);
/* 220 */     this.a[(this.size++)] = k;
/*     */ 
/* 222 */     return true;
/*     */   }
/*     */   public char getChar(int index) {
/* 225 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 226 */     return this.a[index];
/*     */   }
/*     */   public int indexOf(char k) {
/* 229 */     for (int i = 0; i < this.size; i++) if (k == this.a[i]) return i;
/* 230 */     return -1;
/*     */   }
/*     */   public int lastIndexOf(char k) {
/* 233 */     for (int i = this.size; i-- != 0; ) if (k == this.a[i]) return i;
/* 234 */     return -1;
/*     */   }
/*     */   public char removeChar(int index) {
/* 237 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 238 */     char old = this.a[index];
/* 239 */     this.size -= 1;
/* 240 */     if (index != this.size) System.arraycopy(this.a, index + 1, this.a, index, this.size - index);
/*     */ 
/* 242 */     return old;
/*     */   }
/*     */   public boolean rem(char k) {
/* 245 */     int index = indexOf(k);
/* 246 */     if (index == -1) return false;
/* 247 */     removeChar(index);
/*     */ 
/* 249 */     return true;
/*     */   }
/*     */   public char set(int index, char k) {
/* 252 */     if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 253 */     char old = this.a[index];
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
/* 266 */     if (size > this.size) CharArrays.fill(this.a, this.size, size, '\000');
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
/* 297 */     char[] t = new char[Math.max(n, this.size)];
/* 298 */     System.arraycopy(this.a, 0, t, 0, this.size);
/* 299 */     this.a = t;
/*     */   }
/*     */ 
/*     */   public void getElements(int from, char[] a, int offset, int length)
/*     */   {
/* 310 */     CharArrays.ensureOffsetLength(a, offset, length);
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
/*     */   public void addElements(int index, char[] a, int offset, int length)
/*     */   {
/* 331 */     ensureIndex(index);
/* 332 */     CharArrays.ensureOffsetLength(a, offset, length);
/* 333 */     grow(this.size + length);
/* 334 */     System.arraycopy(this.a, index, this.a, index + length, this.size - index);
/* 335 */     System.arraycopy(a, offset, this.a, index, length);
/* 336 */     this.size += length;
/*     */   }
/*     */   public char[] toCharArray(char[] a) {
/* 339 */     if ((a == null) || (a.length < this.size)) a = new char[this.size];
/* 340 */     System.arraycopy(this.a, 0, a, 0, this.size);
/* 341 */     return a;
/*     */   }
/*     */   public boolean addAll(int index, CharCollection c) {
/* 344 */     ensureIndex(index);
/* 345 */     int n = c.size();
/* 346 */     if (n == 0) return false;
/* 347 */     grow(this.size + n);
/* 348 */     if (index != this.size) System.arraycopy(this.a, index, this.a, index + n, this.size - index);
/* 349 */     CharIterator i = c.iterator();
/* 350 */     this.size += n;
/* 351 */     while (n-- != 0) this.a[(index++)] = i.nextChar();
/*     */ 
/* 353 */     return true;
/*     */   }
/*     */   public boolean addAll(int index, CharList l) {
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
/*     */   public CharListIterator listIterator(final int index) {
/* 367 */     ensureIndex(index);
/* 368 */     return new AbstractCharListIterator() {
/* 369 */       int pos = index; int last = -1;
/*     */ 
/* 370 */       public boolean hasNext() { return this.pos < CharArrayList.this.size; } 
/* 371 */       public boolean hasPrevious() { return this.pos > 0; } 
/* 372 */       public char nextChar() { if (!hasNext()) throw new NoSuchElementException(); return CharArrayList.this.a[(this.last = this.pos++)]; } 
/* 373 */       public char previousChar() { if (!hasPrevious()) throw new NoSuchElementException(); return CharArrayList.this.a[(this.last = --this.pos)]; } 
/* 374 */       public int nextIndex() { return this.pos; } 
/* 375 */       public int previousIndex() { return this.pos - 1; } 
/*     */       public void add(char k) {
/* 377 */         if (this.last == -1) throw new IllegalStateException();
/* 378 */         CharArrayList.this.add(this.pos++, k);
/* 379 */         this.last = -1;
/*     */       }
/*     */       public void set(char k) {
/* 382 */         if (this.last == -1) throw new IllegalStateException();
/* 383 */         CharArrayList.this.set(this.last, k);
/*     */       }
/*     */       public void remove() {
/* 386 */         if (this.last == -1) throw new IllegalStateException();
/* 387 */         CharArrayList.this.removeChar(this.last);
/*     */ 
/* 389 */         if (this.last < this.pos) this.pos -= 1;
/* 390 */         this.last = -1;
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public CharArrayList clone() {
/* 396 */     CharArrayList c = new CharArrayList(this.size);
/* 397 */     System.arraycopy(this.a, 0, c.a, 0, this.size);
/* 398 */     c.size = this.size;
/* 399 */     return c;
/*     */   }
/*     */ 
/*     */   public boolean equals(CharArrayList l)
/*     */   {
/* 410 */     if (l == this) return true;
/* 411 */     int s = size();
/* 412 */     if (s != l.size()) return false;
/* 413 */     char[] a1 = this.a;
/* 414 */     char[] a2 = l.a;
/* 415 */     while (s-- != 0) if (a1[s] != a2[s]) return false;
/* 416 */     return true;
/*     */   }
/*     */ 
/*     */   public int compareTo(CharArrayList l)
/*     */   {
/* 430 */     int s1 = size(); int s2 = l.size();
/* 431 */     char[] a1 = this.a; char[] a2 = l.a;
/*     */ 
/* 434 */     for (int i = 0; (i < s1) && (i < s2); i++) {
/* 435 */       char e1 = a1[i];
/* 436 */       char e2 = a2[i];
/*     */       int r;
/* 437 */       if ((r = e1 == e2 ? 0 : e1 < e2 ? -1 : 1) != 0) return r;
/*     */     }
/* 439 */     return i < s1 ? 1 : i < s2 ? -1 : 0;
/*     */   }
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 442 */     s.defaultWriteObject();
/* 443 */     for (int i = 0; i < this.size; i++) s.writeChar(this.a[i]); 
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 447 */     s.defaultReadObject();
/* 448 */     this.a = new char[this.size];
/* 449 */     for (int i = 0; i < this.size; i++) this.a[i] = s.readChar();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharArrayList
 * JD-Core Version:    0.6.2
 */