/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.BigList;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class CharBigLists
/*     */ {
/* 136 */   public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
/*     */ 
/*     */   public static CharBigList shuffle(CharBigList l, Random random)
/*     */   {
/*  63 */     for (long i = l.size64(); i-- != 0L; ) {
/*  64 */       long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/*  65 */       char t = l.getChar(i);
/*  66 */       l.set(i, l.getChar(p));
/*  67 */       l.set(p, t);
/*     */     }
/*  69 */     return l;
/*     */   }
/*     */ 
/*     */   public static CharBigList singleton(char element)
/*     */   {
/* 219 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static CharBigList singleton(Object element)
/*     */   {
/* 229 */     return new Singleton(((Character)element).charValue(), null);
/*     */   }
/*     */ 
/*     */   public static CharBigList synchronize(CharBigList l)
/*     */   {
/* 303 */     return new SynchronizedBigList(l);
/*     */   }
/*     */ 
/*     */   public static CharBigList synchronize(CharBigList l, Object sync)
/*     */   {
/* 313 */     return new SynchronizedBigList(l, sync);
/*     */   }
/*     */ 
/*     */   public static CharBigList unmodifiable(CharBigList l)
/*     */   {
/* 380 */     return new UnmodifiableBigList(l);
/*     */   }
/*     */ 
/*     */   public static CharBigList asBigList(CharList list)
/*     */   {
/* 443 */     return new ListBigList(list);
/*     */   }
/*     */ 
/*     */   public static class ListBigList extends AbstractCharBigList
/*     */     implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     private final CharList list;
/*     */ 
/*     */     protected ListBigList(CharList list)
/*     */     {
/* 391 */       this.list = list;
/*     */     }
/*     */ 
/*     */     private int intIndex(long index) {
/* 395 */       if (index >= 2147483647L) throw new IndexOutOfBoundsException("This big list is restricted to 32-bit indices");
/* 396 */       return (int)index;
/*     */     }
/*     */     public long size64() {
/* 399 */       return this.list.size(); } 
/* 401 */     @Deprecated
/*     */     public int size() { return this.list.size(); } 
/* 402 */     public void size(long size) { this.list.size(intIndex(size)); } 
/* 403 */     public CharBigListIterator iterator() { return CharBigListIterators.asBigListIterator(this.list.iterator()); } 
/* 404 */     public CharBigListIterator listIterator() { return CharBigListIterators.asBigListIterator(this.list.listIterator()); } 
/* 405 */     public boolean addAll(long index, Collection<? extends Character> c) { return this.list.addAll(intIndex(index), c); } 
/* 406 */     public CharBigListIterator listIterator(long index) { return CharBigListIterators.asBigListIterator(this.list.listIterator(intIndex(index))); } 
/* 407 */     public CharBigList subList(long from, long to) { return new ListBigList(this.list.subList(intIndex(from), intIndex(to))); } 
/* 408 */     public boolean contains(char key) { return this.list.contains(key); } 
/* 409 */     public char[] toCharArray() { return this.list.toCharArray(); } 
/* 410 */     public void removeElements(long from, long to) { this.list.removeElements(intIndex(from), intIndex(to)); } 
/*     */     public char[] toCharArray(char[] a) {
/* 412 */       return this.list.toCharArray(a);
/*     */     }
/* 414 */     public void add(long index, char key) { this.list.add(intIndex(index), key); } 
/* 415 */     public boolean addAll(long index, CharCollection c) { return this.list.addAll(intIndex(index), c); } 
/* 416 */     public boolean addAll(long index, CharBigList c) { return this.list.addAll(intIndex(index), c); } 
/* 417 */     public boolean add(char key) { return this.list.add(key); } 
/* 418 */     public boolean addAll(CharBigList c) { return this.list.addAll(c); } 
/* 419 */     public char getChar(long index) { return this.list.getChar(intIndex(index)); } 
/* 420 */     public long indexOf(char k) { return this.list.indexOf(k); } 
/* 421 */     public long lastIndexOf(char k) { return this.list.lastIndexOf(k); } 
/* 422 */     public char removeChar(long index) { return this.list.removeChar(intIndex(index)); } 
/* 423 */     public char set(long index, char k) { return this.list.set(intIndex(index), k); } 
/* 424 */     public boolean addAll(CharCollection c) { return this.list.addAll(c); } 
/* 425 */     public boolean containsAll(CharCollection c) { return this.list.containsAll(c); } 
/* 426 */     public boolean removeAll(CharCollection c) { return this.list.removeAll(c); } 
/* 427 */     public boolean retainAll(CharCollection c) { return this.list.retainAll(c); } 
/* 428 */     public boolean isEmpty() { return this.list.isEmpty(); } 
/* 429 */     public <T> T[] toArray(T[] a) { return this.list.toArray(a); } 
/* 430 */     public boolean containsAll(Collection<?> c) { return this.list.containsAll(c); } 
/* 431 */     public boolean addAll(Collection<? extends Character> c) { return this.list.addAll(c); } 
/* 432 */     public boolean removeAll(Collection<?> c) { return this.list.removeAll(c); } 
/* 433 */     public boolean retainAll(Collection<?> c) { return this.list.retainAll(c); } 
/* 434 */     public void clear() { this.list.clear(); } 
/* 435 */     public int hashCode() { return this.list.hashCode(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableBigList extends CharCollections.UnmodifiableCollection
/*     */     implements CharBigList, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final CharBigList list;
/*     */ 
/*     */     protected UnmodifiableBigList(CharBigList l)
/*     */     {
/* 326 */       super();
/* 327 */       this.list = l;
/*     */     }
/*     */     public char getChar(long i) {
/* 330 */       return this.list.getChar(i); } 
/* 331 */     public char set(long i, char k) { throw new UnsupportedOperationException(); } 
/* 332 */     public void add(long i, char k) { throw new UnsupportedOperationException(); } 
/* 333 */     public char removeChar(long i) { throw new UnsupportedOperationException(); } 
/*     */     public long indexOf(char k) {
/* 335 */       return this.list.indexOf(k); } 
/* 336 */     public long lastIndexOf(char k) { return this.list.lastIndexOf(k); } 
/*     */     public boolean addAll(long index, Collection<? extends Character> c) {
/* 338 */       throw new UnsupportedOperationException();
/*     */     }
/* 340 */     public void getElements(long from, char[][] a, long offset, long length) { this.list.getElements(from, a, offset, length); } 
/* 341 */     public void removeElements(long from, long to) { throw new UnsupportedOperationException(); } 
/* 342 */     public void addElements(long index, char[][] a, long offset, long length) { throw new UnsupportedOperationException(); } 
/* 343 */     public void addElements(long index, char[][] a) { throw new UnsupportedOperationException(); } 
/* 344 */     public void size(long size) { this.list.size(size); } 
/* 345 */     public long size64() { return this.list.size64(); } 
/*     */     public CharBigListIterator iterator() {
/* 347 */       return listIterator(); } 
/* 348 */     public CharBigListIterator listIterator() { return CharBigListIterators.unmodifiable(this.list.listIterator()); } 
/* 349 */     public CharBigListIterator listIterator(long i) { return CharBigListIterators.unmodifiable(this.list.listIterator(i)); } 
/*     */     public CharBigList subList(long from, long to) {
/* 351 */       return CharBigLists.unmodifiable(this.list.subList(from, to));
/*     */     }
/* 353 */     public boolean equals(Object o) { return this.list.equals(o); } 
/* 354 */     public int hashCode() { return this.list.hashCode(); }
/*     */ 
/*     */     public int compareTo(BigList<? extends Character> o) {
/* 357 */       return this.list.compareTo(o);
/*     */     }
/*     */ 
/*     */     public boolean addAll(long index, CharCollection c) {
/* 361 */       throw new UnsupportedOperationException(); } 
/* 362 */     public boolean addAll(CharBigList l) { throw new UnsupportedOperationException(); } 
/* 363 */     public boolean addAll(long index, CharBigList l) { throw new UnsupportedOperationException(); } 
/* 364 */     public Character get(long i) { return (Character)this.list.get(i); } 
/* 365 */     public void add(long i, Character k) { throw new UnsupportedOperationException(); } 
/* 366 */     public Character set(long index, Character k) { throw new UnsupportedOperationException(); } 
/* 367 */     public Character remove(long i) { throw new UnsupportedOperationException(); } 
/* 368 */     public long indexOf(Object o) { return this.list.indexOf(o); } 
/* 369 */     public long lastIndexOf(Object o) { return this.list.lastIndexOf(o); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedBigList extends CharCollections.SynchronizedCollection
/*     */     implements CharBigList, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final CharBigList list;
/*     */ 
/*     */     protected SynchronizedBigList(CharBigList l, Object sync)
/*     */     {
/* 243 */       super(sync);
/* 244 */       this.list = l;
/*     */     }
/*     */ 
/*     */     protected SynchronizedBigList(CharBigList l) {
/* 248 */       super();
/* 249 */       this.list = l;
/*     */     }
/*     */     public char getChar(long i) {
/* 252 */       synchronized (this.sync) { return this.list.getChar(i); }  } 
/* 253 */     public char set(long i, char k) { synchronized (this.sync) { return this.list.set(i, k); }  } 
/* 254 */     public void add(long i, char k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 255 */     public char removeChar(long i) { synchronized (this.sync) { return this.list.removeChar(i); }  } 
/*     */     public long indexOf(char k) {
/* 257 */       synchronized (this.sync) { return this.list.indexOf(k); }  } 
/* 258 */     public long lastIndexOf(char k) { synchronized (this.sync) { return this.list.lastIndexOf(k); }  } 
/*     */     public boolean addAll(long index, Collection<? extends Character> c) {
/* 260 */       synchronized (this.sync) { return this.list.addAll(index, c); } 
/*     */     }
/* 262 */     public void getElements(long from, char[][] a, long offset, long length) { synchronized (this.sync) { this.list.getElements(from, a, offset, length); }  } 
/* 263 */     public void removeElements(long from, long to) { synchronized (this.sync) { this.list.removeElements(from, to); }  } 
/* 264 */     public void addElements(long index, char[][] a, long offset, long length) { synchronized (this.sync) { this.list.addElements(index, a, offset, length); }  } 
/* 265 */     public void addElements(long index, char[][] a) { synchronized (this.sync) { this.list.addElements(index, a); }  } 
/* 266 */     public void size(long size) { synchronized (this.sync) { this.list.size(size); }  } 
/* 267 */     public long size64() { synchronized (this.sync) { return this.list.size64(); }  } 
/*     */     public CharBigListIterator iterator() {
/* 269 */       return this.list.listIterator(); } 
/* 270 */     public CharBigListIterator listIterator() { return this.list.listIterator(); } 
/* 271 */     public CharBigListIterator listIterator(long i) { return this.list.listIterator(i); } 
/*     */     public CharBigList subList(long from, long to) {
/* 273 */       synchronized (this.sync) { return CharBigLists.synchronize(this.list.subList(from, to), this.sync); } 
/*     */     }
/* 275 */     public boolean equals(Object o) { synchronized (this.sync) { return this.list.equals(o); }  } 
/* 276 */     public int hashCode() { synchronized (this.sync) { return this.list.hashCode(); } }
/*     */ 
/*     */     public int compareTo(BigList<? extends Character> o) {
/* 279 */       synchronized (this.sync) { return this.list.compareTo(o); }
/*     */     }
/*     */ 
/*     */     public boolean addAll(long index, CharCollection c) {
/* 283 */       synchronized (this.sync) { return this.list.addAll(index, c); }  } 
/* 284 */     public boolean addAll(long index, CharBigList l) { synchronized (this.sync) { return this.list.addAll(index, l); }  } 
/* 285 */     public boolean addAll(CharBigList l) { synchronized (this.sync) { return this.list.addAll(l); }  } 
/*     */     public Character get(long i) {
/* 287 */       synchronized (this.sync) { return (Character)this.list.get(i); }  } 
/* 288 */     public void add(long i, Character k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 289 */     public Character set(long index, Character k) { synchronized (this.sync) { return (Character)this.list.set(index, k); }  } 
/* 290 */     public Character remove(long i) { synchronized (this.sync) { return (Character)this.list.remove(i); }  } 
/* 291 */     public long indexOf(Object o) { synchronized (this.sync) { return this.list.indexOf(o); }  } 
/* 292 */     public long lastIndexOf(Object o) { synchronized (this.sync) { return this.list.lastIndexOf(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends AbstractCharBigList
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     private final char element;
/*     */ 
/*     */     private Singleton(char element)
/*     */     {
/* 153 */       this.element = element;
/*     */     }
/*     */     public char getChar(long i) {
/* 156 */       if (i == 0L) return this.element; throw new IndexOutOfBoundsException(); } 
/* 157 */     public char removeChar(long i) { throw new UnsupportedOperationException(); } 
/* 158 */     public boolean contains(char k) { return k == this.element; } 
/*     */     public boolean addAll(Collection<? extends Character> c) {
/* 160 */       throw new UnsupportedOperationException(); } 
/* 161 */     public boolean addAll(long i, Collection<? extends Character> c) { throw new UnsupportedOperationException(); } 
/* 162 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/* 163 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */     public char[] toCharArray()
/*     */     {
/* 168 */       char[] a = new char[1];
/* 169 */       a[0] = this.element;
/* 170 */       return a;
/*     */     }
/*     */ 
/*     */     public CharBigListIterator listIterator() {
/* 174 */       return CharBigListIterators.singleton(this.element);
/*     */     }
/* 176 */     public CharBigListIterator iterator() { return listIterator(); }
/*     */ 
/*     */     public CharBigListIterator listIterator(long i) {
/* 179 */       if ((i > 1L) || (i < 0L)) throw new IndexOutOfBoundsException();
/* 180 */       CharBigListIterator l = listIterator();
/* 181 */       if (i == 1L) l.next();
/* 182 */       return l;
/*     */     }
/*     */ 
/*     */     public CharBigList subList(long from, long to)
/*     */     {
/* 187 */       ensureIndex(from);
/* 188 */       ensureIndex(to);
/* 189 */       if (from > to) throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
/*     */ 
/* 191 */       if ((from != 0L) || (to != 1L)) return CharBigLists.EMPTY_BIG_LIST;
/* 192 */       return this;
/*     */     }
/*     */     @Deprecated
/*     */     public int size() {
/* 196 */       return 1; } 
/* 197 */     public long size64() { return 1L; } 
/* 198 */     public void size(long size) { throw new UnsupportedOperationException(); } 
/* 199 */     public void clear() { throw new UnsupportedOperationException(); } 
/*     */     public Object clone() {
/* 201 */       return this;
/*     */     }
/*     */     public boolean rem(char k) {
/* 204 */       throw new UnsupportedOperationException(); } 
/* 205 */     public boolean addAll(CharCollection c) { throw new UnsupportedOperationException(); } 
/* 206 */     public boolean addAll(long i, CharCollection c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptyBigList extends CharCollections.EmptyCollection
/*     */     implements CharBigList, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public void add(long index, char k)
/*     */     {
/*  79 */       throw new UnsupportedOperationException(); } 
/*  80 */     public boolean add(char k) { throw new UnsupportedOperationException(); } 
/*  81 */     public char removeChar(long i) { throw new UnsupportedOperationException(); } 
/*  82 */     public char set(long index, char k) { throw new UnsupportedOperationException(); } 
/*  83 */     public long indexOf(char k) { return -1L; } 
/*  84 */     public long lastIndexOf(char k) { return -1L; } 
/*  85 */     public boolean addAll(Collection<? extends Character> c) { throw new UnsupportedOperationException(); } 
/*  86 */     public boolean addAll(long i, Collection<? extends Character> c) { throw new UnsupportedOperationException(); } 
/*  87 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  88 */     public Character get(long i) { throw new IndexOutOfBoundsException(); } 
/*  89 */     public boolean addAll(CharCollection c) { throw new UnsupportedOperationException(); } 
/*  90 */     public boolean addAll(CharBigList c) { throw new UnsupportedOperationException(); } 
/*  91 */     public boolean addAll(long i, CharCollection c) { throw new UnsupportedOperationException(); } 
/*  92 */     public boolean addAll(long i, CharBigList c) { throw new UnsupportedOperationException(); } 
/*  93 */     public void add(long index, Character k) { throw new UnsupportedOperationException(); } 
/*  94 */     public boolean add(Character k) { throw new UnsupportedOperationException(); } 
/*  95 */     public Character set(long index, Character k) { throw new UnsupportedOperationException(); } 
/*  96 */     public char getChar(long i) { throw new IndexOutOfBoundsException(); } 
/*  97 */     public Character remove(long k) { throw new UnsupportedOperationException(); } 
/*  98 */     public long indexOf(Object k) { return -1L; } 
/*  99 */     public long lastIndexOf(Object k) { return -1L; } 
/*     */     public CharBigListIterator listIterator() {
/* 101 */       return CharBigListIterators.EMPTY_BIG_LIST_ITERATOR;
/*     */     }
/* 103 */     public CharBigListIterator iterator() { return CharBigListIterators.EMPTY_BIG_LIST_ITERATOR; }
/*     */ 
/*     */     public CharBigListIterator listIterator(long i) {
/* 106 */       if (i == 0L) return CharBigListIterators.EMPTY_BIG_LIST_ITERATOR; throw new IndexOutOfBoundsException(String.valueOf(i));
/*     */     }
/* 108 */     public CharBigList subList(long from, long to) { if ((from == 0L) && (to == 0L)) return this; throw new IndexOutOfBoundsException(); } 
/*     */     public void getElements(long from, char[][] a, long offset, long length) {
/* 110 */       CharBigArrays.ensureOffsetLength(a, offset, length); if (from != 0L) throw new IndexOutOfBoundsException();  } 
/* 111 */     public void removeElements(long from, long to) { throw new UnsupportedOperationException(); } 
/*     */     public void addElements(long index, char[][] a, long offset, long length) {
/* 113 */       throw new UnsupportedOperationException(); } 
/* 114 */     public void addElements(long index, char[][] a) { throw new UnsupportedOperationException(); } 
/*     */     public void size(long s) {
/* 116 */       throw new UnsupportedOperationException(); } 
/* 117 */     public long size64() { return 0L; }
/*     */ 
/*     */     public int compareTo(BigList<? extends Character> o) {
/* 120 */       if (o == this) return 0;
/* 121 */       return o.isEmpty() ? 0 : -1;
/*     */     }
/*     */     private Object readResolve() {
/* 124 */       return CharBigLists.EMPTY_BIG_LIST; } 
/* 125 */     public Object clone() { return CharBigLists.EMPTY_BIG_LIST; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharBigLists
 * JD-Core Version:    0.6.2
 */