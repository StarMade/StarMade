/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.BigList;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ShortBigLists
/*     */ {
/* 136 */   public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
/*     */ 
/*     */   public static ShortBigList shuffle(ShortBigList l, Random random)
/*     */   {
/*  63 */     for (long i = l.size64(); i-- != 0L; ) {
/*  64 */       long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/*  65 */       short t = l.getShort(i);
/*  66 */       l.set(i, l.getShort(p));
/*  67 */       l.set(p, t);
/*     */     }
/*  69 */     return l;
/*     */   }
/*     */ 
/*     */   public static ShortBigList singleton(short element)
/*     */   {
/* 219 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static ShortBigList singleton(Object element)
/*     */   {
/* 229 */     return new Singleton(((Short)element).shortValue(), null);
/*     */   }
/*     */ 
/*     */   public static ShortBigList synchronize(ShortBigList l)
/*     */   {
/* 303 */     return new SynchronizedBigList(l);
/*     */   }
/*     */ 
/*     */   public static ShortBigList synchronize(ShortBigList l, Object sync)
/*     */   {
/* 313 */     return new SynchronizedBigList(l, sync);
/*     */   }
/*     */ 
/*     */   public static ShortBigList unmodifiable(ShortBigList l)
/*     */   {
/* 380 */     return new UnmodifiableBigList(l);
/*     */   }
/*     */ 
/*     */   public static ShortBigList asBigList(ShortList list)
/*     */   {
/* 443 */     return new ListBigList(list);
/*     */   }
/*     */ 
/*     */   public static class ListBigList extends AbstractShortBigList
/*     */     implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     private final ShortList list;
/*     */ 
/*     */     protected ListBigList(ShortList list)
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
/* 403 */     public ShortBigListIterator iterator() { return ShortBigListIterators.asBigListIterator(this.list.iterator()); } 
/* 404 */     public ShortBigListIterator listIterator() { return ShortBigListIterators.asBigListIterator(this.list.listIterator()); } 
/* 405 */     public boolean addAll(long index, Collection<? extends Short> c) { return this.list.addAll(intIndex(index), c); } 
/* 406 */     public ShortBigListIterator listIterator(long index) { return ShortBigListIterators.asBigListIterator(this.list.listIterator(intIndex(index))); } 
/* 407 */     public ShortBigList subList(long from, long to) { return new ListBigList(this.list.subList(intIndex(from), intIndex(to))); } 
/* 408 */     public boolean contains(short key) { return this.list.contains(key); } 
/* 409 */     public short[] toShortArray() { return this.list.toShortArray(); } 
/* 410 */     public void removeElements(long from, long to) { this.list.removeElements(intIndex(from), intIndex(to)); } 
/*     */     public short[] toShortArray(short[] a) {
/* 412 */       return this.list.toShortArray(a);
/*     */     }
/* 414 */     public void add(long index, short key) { this.list.add(intIndex(index), key); } 
/* 415 */     public boolean addAll(long index, ShortCollection c) { return this.list.addAll(intIndex(index), c); } 
/* 416 */     public boolean addAll(long index, ShortBigList c) { return this.list.addAll(intIndex(index), c); } 
/* 417 */     public boolean add(short key) { return this.list.add(key); } 
/* 418 */     public boolean addAll(ShortBigList c) { return this.list.addAll(c); } 
/* 419 */     public short getShort(long index) { return this.list.getShort(intIndex(index)); } 
/* 420 */     public long indexOf(short k) { return this.list.indexOf(k); } 
/* 421 */     public long lastIndexOf(short k) { return this.list.lastIndexOf(k); } 
/* 422 */     public short removeShort(long index) { return this.list.removeShort(intIndex(index)); } 
/* 423 */     public short set(long index, short k) { return this.list.set(intIndex(index), k); } 
/* 424 */     public boolean addAll(ShortCollection c) { return this.list.addAll(c); } 
/* 425 */     public boolean containsAll(ShortCollection c) { return this.list.containsAll(c); } 
/* 426 */     public boolean removeAll(ShortCollection c) { return this.list.removeAll(c); } 
/* 427 */     public boolean retainAll(ShortCollection c) { return this.list.retainAll(c); } 
/* 428 */     public boolean isEmpty() { return this.list.isEmpty(); } 
/* 429 */     public <T> T[] toArray(T[] a) { return this.list.toArray(a); } 
/* 430 */     public boolean containsAll(Collection<?> c) { return this.list.containsAll(c); } 
/* 431 */     public boolean addAll(Collection<? extends Short> c) { return this.list.addAll(c); } 
/* 432 */     public boolean removeAll(Collection<?> c) { return this.list.removeAll(c); } 
/* 433 */     public boolean retainAll(Collection<?> c) { return this.list.retainAll(c); } 
/* 434 */     public void clear() { this.list.clear(); } 
/* 435 */     public int hashCode() { return this.list.hashCode(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableBigList extends ShortCollections.UnmodifiableCollection
/*     */     implements ShortBigList, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ShortBigList list;
/*     */ 
/*     */     protected UnmodifiableBigList(ShortBigList l)
/*     */     {
/* 326 */       super();
/* 327 */       this.list = l;
/*     */     }
/*     */     public short getShort(long i) {
/* 330 */       return this.list.getShort(i); } 
/* 331 */     public short set(long i, short k) { throw new UnsupportedOperationException(); } 
/* 332 */     public void add(long i, short k) { throw new UnsupportedOperationException(); } 
/* 333 */     public short removeShort(long i) { throw new UnsupportedOperationException(); } 
/*     */     public long indexOf(short k) {
/* 335 */       return this.list.indexOf(k); } 
/* 336 */     public long lastIndexOf(short k) { return this.list.lastIndexOf(k); } 
/*     */     public boolean addAll(long index, Collection<? extends Short> c) {
/* 338 */       throw new UnsupportedOperationException();
/*     */     }
/* 340 */     public void getElements(long from, short[][] a, long offset, long length) { this.list.getElements(from, a, offset, length); } 
/* 341 */     public void removeElements(long from, long to) { throw new UnsupportedOperationException(); } 
/* 342 */     public void addElements(long index, short[][] a, long offset, long length) { throw new UnsupportedOperationException(); } 
/* 343 */     public void addElements(long index, short[][] a) { throw new UnsupportedOperationException(); } 
/* 344 */     public void size(long size) { this.list.size(size); } 
/* 345 */     public long size64() { return this.list.size64(); } 
/*     */     public ShortBigListIterator iterator() {
/* 347 */       return listIterator(); } 
/* 348 */     public ShortBigListIterator listIterator() { return ShortBigListIterators.unmodifiable(this.list.listIterator()); } 
/* 349 */     public ShortBigListIterator listIterator(long i) { return ShortBigListIterators.unmodifiable(this.list.listIterator(i)); } 
/*     */     public ShortBigList subList(long from, long to) {
/* 351 */       return ShortBigLists.unmodifiable(this.list.subList(from, to));
/*     */     }
/* 353 */     public boolean equals(Object o) { return this.list.equals(o); } 
/* 354 */     public int hashCode() { return this.list.hashCode(); }
/*     */ 
/*     */     public int compareTo(BigList<? extends Short> o) {
/* 357 */       return this.list.compareTo(o);
/*     */     }
/*     */ 
/*     */     public boolean addAll(long index, ShortCollection c) {
/* 361 */       throw new UnsupportedOperationException(); } 
/* 362 */     public boolean addAll(ShortBigList l) { throw new UnsupportedOperationException(); } 
/* 363 */     public boolean addAll(long index, ShortBigList l) { throw new UnsupportedOperationException(); } 
/* 364 */     public Short get(long i) { return (Short)this.list.get(i); } 
/* 365 */     public void add(long i, Short k) { throw new UnsupportedOperationException(); } 
/* 366 */     public Short set(long index, Short k) { throw new UnsupportedOperationException(); } 
/* 367 */     public Short remove(long i) { throw new UnsupportedOperationException(); } 
/* 368 */     public long indexOf(Object o) { return this.list.indexOf(o); } 
/* 369 */     public long lastIndexOf(Object o) { return this.list.lastIndexOf(o); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedBigList extends ShortCollections.SynchronizedCollection
/*     */     implements ShortBigList, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ShortBigList list;
/*     */ 
/*     */     protected SynchronizedBigList(ShortBigList l, Object sync)
/*     */     {
/* 243 */       super(sync);
/* 244 */       this.list = l;
/*     */     }
/*     */ 
/*     */     protected SynchronizedBigList(ShortBigList l) {
/* 248 */       super();
/* 249 */       this.list = l;
/*     */     }
/*     */     public short getShort(long i) {
/* 252 */       synchronized (this.sync) { return this.list.getShort(i); }  } 
/* 253 */     public short set(long i, short k) { synchronized (this.sync) { return this.list.set(i, k); }  } 
/* 254 */     public void add(long i, short k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 255 */     public short removeShort(long i) { synchronized (this.sync) { return this.list.removeShort(i); }  } 
/*     */     public long indexOf(short k) {
/* 257 */       synchronized (this.sync) { return this.list.indexOf(k); }  } 
/* 258 */     public long lastIndexOf(short k) { synchronized (this.sync) { return this.list.lastIndexOf(k); }  } 
/*     */     public boolean addAll(long index, Collection<? extends Short> c) {
/* 260 */       synchronized (this.sync) { return this.list.addAll(index, c); } 
/*     */     }
/* 262 */     public void getElements(long from, short[][] a, long offset, long length) { synchronized (this.sync) { this.list.getElements(from, a, offset, length); }  } 
/* 263 */     public void removeElements(long from, long to) { synchronized (this.sync) { this.list.removeElements(from, to); }  } 
/* 264 */     public void addElements(long index, short[][] a, long offset, long length) { synchronized (this.sync) { this.list.addElements(index, a, offset, length); }  } 
/* 265 */     public void addElements(long index, short[][] a) { synchronized (this.sync) { this.list.addElements(index, a); }  } 
/* 266 */     public void size(long size) { synchronized (this.sync) { this.list.size(size); }  } 
/* 267 */     public long size64() { synchronized (this.sync) { return this.list.size64(); }  } 
/*     */     public ShortBigListIterator iterator() {
/* 269 */       return this.list.listIterator(); } 
/* 270 */     public ShortBigListIterator listIterator() { return this.list.listIterator(); } 
/* 271 */     public ShortBigListIterator listIterator(long i) { return this.list.listIterator(i); } 
/*     */     public ShortBigList subList(long from, long to) {
/* 273 */       synchronized (this.sync) { return ShortBigLists.synchronize(this.list.subList(from, to), this.sync); } 
/*     */     }
/* 275 */     public boolean equals(Object o) { synchronized (this.sync) { return this.list.equals(o); }  } 
/* 276 */     public int hashCode() { synchronized (this.sync) { return this.list.hashCode(); } }
/*     */ 
/*     */     public int compareTo(BigList<? extends Short> o) {
/* 279 */       synchronized (this.sync) { return this.list.compareTo(o); }
/*     */     }
/*     */ 
/*     */     public boolean addAll(long index, ShortCollection c) {
/* 283 */       synchronized (this.sync) { return this.list.addAll(index, c); }  } 
/* 284 */     public boolean addAll(long index, ShortBigList l) { synchronized (this.sync) { return this.list.addAll(index, l); }  } 
/* 285 */     public boolean addAll(ShortBigList l) { synchronized (this.sync) { return this.list.addAll(l); }  } 
/*     */     public Short get(long i) {
/* 287 */       synchronized (this.sync) { return (Short)this.list.get(i); }  } 
/* 288 */     public void add(long i, Short k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 289 */     public Short set(long index, Short k) { synchronized (this.sync) { return (Short)this.list.set(index, k); }  } 
/* 290 */     public Short remove(long i) { synchronized (this.sync) { return (Short)this.list.remove(i); }  } 
/* 291 */     public long indexOf(Object o) { synchronized (this.sync) { return this.list.indexOf(o); }  } 
/* 292 */     public long lastIndexOf(Object o) { synchronized (this.sync) { return this.list.lastIndexOf(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends AbstractShortBigList
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     private final short element;
/*     */ 
/*     */     private Singleton(short element)
/*     */     {
/* 153 */       this.element = element;
/*     */     }
/*     */     public short getShort(long i) {
/* 156 */       if (i == 0L) return this.element; throw new IndexOutOfBoundsException(); } 
/* 157 */     public short removeShort(long i) { throw new UnsupportedOperationException(); } 
/* 158 */     public boolean contains(short k) { return k == this.element; } 
/*     */     public boolean addAll(Collection<? extends Short> c) {
/* 160 */       throw new UnsupportedOperationException(); } 
/* 161 */     public boolean addAll(long i, Collection<? extends Short> c) { throw new UnsupportedOperationException(); } 
/* 162 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/* 163 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */     public short[] toShortArray()
/*     */     {
/* 168 */       short[] a = new short[1];
/* 169 */       a[0] = this.element;
/* 170 */       return a;
/*     */     }
/*     */ 
/*     */     public ShortBigListIterator listIterator() {
/* 174 */       return ShortBigListIterators.singleton(this.element);
/*     */     }
/* 176 */     public ShortBigListIterator iterator() { return listIterator(); }
/*     */ 
/*     */     public ShortBigListIterator listIterator(long i) {
/* 179 */       if ((i > 1L) || (i < 0L)) throw new IndexOutOfBoundsException();
/* 180 */       ShortBigListIterator l = listIterator();
/* 181 */       if (i == 1L) l.next();
/* 182 */       return l;
/*     */     }
/*     */ 
/*     */     public ShortBigList subList(long from, long to)
/*     */     {
/* 187 */       ensureIndex(from);
/* 188 */       ensureIndex(to);
/* 189 */       if (from > to) throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
/*     */ 
/* 191 */       if ((from != 0L) || (to != 1L)) return ShortBigLists.EMPTY_BIG_LIST;
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
/*     */     public boolean rem(short k) {
/* 204 */       throw new UnsupportedOperationException(); } 
/* 205 */     public boolean addAll(ShortCollection c) { throw new UnsupportedOperationException(); } 
/* 206 */     public boolean addAll(long i, ShortCollection c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptyBigList extends ShortCollections.EmptyCollection
/*     */     implements ShortBigList, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public void add(long index, short k)
/*     */     {
/*  79 */       throw new UnsupportedOperationException(); } 
/*  80 */     public boolean add(short k) { throw new UnsupportedOperationException(); } 
/*  81 */     public short removeShort(long i) { throw new UnsupportedOperationException(); } 
/*  82 */     public short set(long index, short k) { throw new UnsupportedOperationException(); } 
/*  83 */     public long indexOf(short k) { return -1L; } 
/*  84 */     public long lastIndexOf(short k) { return -1L; } 
/*  85 */     public boolean addAll(Collection<? extends Short> c) { throw new UnsupportedOperationException(); } 
/*  86 */     public boolean addAll(long i, Collection<? extends Short> c) { throw new UnsupportedOperationException(); } 
/*  87 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  88 */     public Short get(long i) { throw new IndexOutOfBoundsException(); } 
/*  89 */     public boolean addAll(ShortCollection c) { throw new UnsupportedOperationException(); } 
/*  90 */     public boolean addAll(ShortBigList c) { throw new UnsupportedOperationException(); } 
/*  91 */     public boolean addAll(long i, ShortCollection c) { throw new UnsupportedOperationException(); } 
/*  92 */     public boolean addAll(long i, ShortBigList c) { throw new UnsupportedOperationException(); } 
/*  93 */     public void add(long index, Short k) { throw new UnsupportedOperationException(); } 
/*  94 */     public boolean add(Short k) { throw new UnsupportedOperationException(); } 
/*  95 */     public Short set(long index, Short k) { throw new UnsupportedOperationException(); } 
/*  96 */     public short getShort(long i) { throw new IndexOutOfBoundsException(); } 
/*  97 */     public Short remove(long k) { throw new UnsupportedOperationException(); } 
/*  98 */     public long indexOf(Object k) { return -1L; } 
/*  99 */     public long lastIndexOf(Object k) { return -1L; } 
/*     */     public ShortBigListIterator listIterator() {
/* 101 */       return ShortBigListIterators.EMPTY_BIG_LIST_ITERATOR;
/*     */     }
/* 103 */     public ShortBigListIterator iterator() { return ShortBigListIterators.EMPTY_BIG_LIST_ITERATOR; }
/*     */ 
/*     */     public ShortBigListIterator listIterator(long i) {
/* 106 */       if (i == 0L) return ShortBigListIterators.EMPTY_BIG_LIST_ITERATOR; throw new IndexOutOfBoundsException(String.valueOf(i));
/*     */     }
/* 108 */     public ShortBigList subList(long from, long to) { if ((from == 0L) && (to == 0L)) return this; throw new IndexOutOfBoundsException(); } 
/*     */     public void getElements(long from, short[][] a, long offset, long length) {
/* 110 */       ShortBigArrays.ensureOffsetLength(a, offset, length); if (from != 0L) throw new IndexOutOfBoundsException();  } 
/* 111 */     public void removeElements(long from, long to) { throw new UnsupportedOperationException(); } 
/*     */     public void addElements(long index, short[][] a, long offset, long length) {
/* 113 */       throw new UnsupportedOperationException(); } 
/* 114 */     public void addElements(long index, short[][] a) { throw new UnsupportedOperationException(); } 
/*     */     public void size(long s) {
/* 116 */       throw new UnsupportedOperationException(); } 
/* 117 */     public long size64() { return 0L; }
/*     */ 
/*     */     public int compareTo(BigList<? extends Short> o) {
/* 120 */       if (o == this) return 0;
/* 121 */       return o.isEmpty() ? 0 : -1;
/*     */     }
/*     */     private Object readResolve() {
/* 124 */       return ShortBigLists.EMPTY_BIG_LIST; } 
/* 125 */     public Object clone() { return ShortBigLists.EMPTY_BIG_LIST; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortBigLists
 * JD-Core Version:    0.6.2
 */