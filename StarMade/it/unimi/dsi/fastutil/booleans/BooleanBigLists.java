/*     */ package it.unimi.dsi.fastutil.booleans;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.BigList;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class BooleanBigLists
/*     */ {
/* 136 */   public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
/*     */ 
/*     */   public static BooleanBigList shuffle(BooleanBigList l, Random random)
/*     */   {
/*  63 */     for (long i = l.size64(); i-- != 0L; ) {
/*  64 */       long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/*  65 */       boolean t = l.getBoolean(i);
/*  66 */       l.set(i, l.getBoolean(p));
/*  67 */       l.set(p, t);
/*     */     }
/*  69 */     return l;
/*     */   }
/*     */ 
/*     */   public static BooleanBigList singleton(boolean element)
/*     */   {
/* 219 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static BooleanBigList singleton(Object element)
/*     */   {
/* 229 */     return new Singleton(((Boolean)element).booleanValue(), null);
/*     */   }
/*     */ 
/*     */   public static BooleanBigList synchronize(BooleanBigList l)
/*     */   {
/* 303 */     return new SynchronizedBigList(l);
/*     */   }
/*     */ 
/*     */   public static BooleanBigList synchronize(BooleanBigList l, Object sync)
/*     */   {
/* 313 */     return new SynchronizedBigList(l, sync);
/*     */   }
/*     */ 
/*     */   public static BooleanBigList unmodifiable(BooleanBigList l)
/*     */   {
/* 380 */     return new UnmodifiableBigList(l);
/*     */   }
/*     */ 
/*     */   public static BooleanBigList asBigList(BooleanList list)
/*     */   {
/* 443 */     return new ListBigList(list);
/*     */   }
/*     */ 
/*     */   public static class ListBigList extends AbstractBooleanBigList
/*     */     implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     private final BooleanList list;
/*     */ 
/*     */     protected ListBigList(BooleanList list)
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
/* 403 */     public BooleanBigListIterator iterator() { return BooleanBigListIterators.asBigListIterator(this.list.iterator()); } 
/* 404 */     public BooleanBigListIterator listIterator() { return BooleanBigListIterators.asBigListIterator(this.list.listIterator()); } 
/* 405 */     public boolean addAll(long index, Collection<? extends Boolean> c) { return this.list.addAll(intIndex(index), c); } 
/* 406 */     public BooleanBigListIterator listIterator(long index) { return BooleanBigListIterators.asBigListIterator(this.list.listIterator(intIndex(index))); } 
/* 407 */     public BooleanBigList subList(long from, long to) { return new ListBigList(this.list.subList(intIndex(from), intIndex(to))); } 
/* 408 */     public boolean contains(boolean key) { return this.list.contains(key); } 
/* 409 */     public boolean[] toBooleanArray() { return this.list.toBooleanArray(); } 
/* 410 */     public void removeElements(long from, long to) { this.list.removeElements(intIndex(from), intIndex(to)); } 
/*     */     public boolean[] toBooleanArray(boolean[] a) {
/* 412 */       return this.list.toBooleanArray(a);
/*     */     }
/* 414 */     public void add(long index, boolean key) { this.list.add(intIndex(index), key); } 
/* 415 */     public boolean addAll(long index, BooleanCollection c) { return this.list.addAll(intIndex(index), c); } 
/* 416 */     public boolean addAll(long index, BooleanBigList c) { return this.list.addAll(intIndex(index), c); } 
/* 417 */     public boolean add(boolean key) { return this.list.add(key); } 
/* 418 */     public boolean addAll(BooleanBigList c) { return this.list.addAll(c); } 
/* 419 */     public boolean getBoolean(long index) { return this.list.getBoolean(intIndex(index)); } 
/* 420 */     public long indexOf(boolean k) { return this.list.indexOf(k); } 
/* 421 */     public long lastIndexOf(boolean k) { return this.list.lastIndexOf(k); } 
/* 422 */     public boolean removeBoolean(long index) { return this.list.removeBoolean(intIndex(index)); } 
/* 423 */     public boolean set(long index, boolean k) { return this.list.set(intIndex(index), k); } 
/* 424 */     public boolean addAll(BooleanCollection c) { return this.list.addAll(c); } 
/* 425 */     public boolean containsAll(BooleanCollection c) { return this.list.containsAll(c); } 
/* 426 */     public boolean removeAll(BooleanCollection c) { return this.list.removeAll(c); } 
/* 427 */     public boolean retainAll(BooleanCollection c) { return this.list.retainAll(c); } 
/* 428 */     public boolean isEmpty() { return this.list.isEmpty(); } 
/* 429 */     public <T> T[] toArray(T[] a) { return this.list.toArray(a); } 
/* 430 */     public boolean containsAll(Collection<?> c) { return this.list.containsAll(c); } 
/* 431 */     public boolean addAll(Collection<? extends Boolean> c) { return this.list.addAll(c); } 
/* 432 */     public boolean removeAll(Collection<?> c) { return this.list.removeAll(c); } 
/* 433 */     public boolean retainAll(Collection<?> c) { return this.list.retainAll(c); } 
/* 434 */     public void clear() { this.list.clear(); } 
/* 435 */     public int hashCode() { return this.list.hashCode(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableBigList extends BooleanCollections.UnmodifiableCollection
/*     */     implements BooleanBigList, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final BooleanBigList list;
/*     */ 
/*     */     protected UnmodifiableBigList(BooleanBigList l)
/*     */     {
/* 326 */       super();
/* 327 */       this.list = l;
/*     */     }
/*     */     public boolean getBoolean(long i) {
/* 330 */       return this.list.getBoolean(i); } 
/* 331 */     public boolean set(long i, boolean k) { throw new UnsupportedOperationException(); } 
/* 332 */     public void add(long i, boolean k) { throw new UnsupportedOperationException(); } 
/* 333 */     public boolean removeBoolean(long i) { throw new UnsupportedOperationException(); } 
/*     */     public long indexOf(boolean k) {
/* 335 */       return this.list.indexOf(k); } 
/* 336 */     public long lastIndexOf(boolean k) { return this.list.lastIndexOf(k); } 
/*     */     public boolean addAll(long index, Collection<? extends Boolean> c) {
/* 338 */       throw new UnsupportedOperationException();
/*     */     }
/* 340 */     public void getElements(long from, boolean[][] a, long offset, long length) { this.list.getElements(from, a, offset, length); } 
/* 341 */     public void removeElements(long from, long to) { throw new UnsupportedOperationException(); } 
/* 342 */     public void addElements(long index, boolean[][] a, long offset, long length) { throw new UnsupportedOperationException(); } 
/* 343 */     public void addElements(long index, boolean[][] a) { throw new UnsupportedOperationException(); } 
/* 344 */     public void size(long size) { this.list.size(size); } 
/* 345 */     public long size64() { return this.list.size64(); } 
/*     */     public BooleanBigListIterator iterator() {
/* 347 */       return listIterator(); } 
/* 348 */     public BooleanBigListIterator listIterator() { return BooleanBigListIterators.unmodifiable(this.list.listIterator()); } 
/* 349 */     public BooleanBigListIterator listIterator(long i) { return BooleanBigListIterators.unmodifiable(this.list.listIterator(i)); } 
/*     */     public BooleanBigList subList(long from, long to) {
/* 351 */       return BooleanBigLists.unmodifiable(this.list.subList(from, to));
/*     */     }
/* 353 */     public boolean equals(Object o) { return this.list.equals(o); } 
/* 354 */     public int hashCode() { return this.list.hashCode(); }
/*     */ 
/*     */     public int compareTo(BigList<? extends Boolean> o) {
/* 357 */       return this.list.compareTo(o);
/*     */     }
/*     */ 
/*     */     public boolean addAll(long index, BooleanCollection c) {
/* 361 */       throw new UnsupportedOperationException(); } 
/* 362 */     public boolean addAll(BooleanBigList l) { throw new UnsupportedOperationException(); } 
/* 363 */     public boolean addAll(long index, BooleanBigList l) { throw new UnsupportedOperationException(); } 
/* 364 */     public Boolean get(long i) { return (Boolean)this.list.get(i); } 
/* 365 */     public void add(long i, Boolean k) { throw new UnsupportedOperationException(); } 
/* 366 */     public Boolean set(long index, Boolean k) { throw new UnsupportedOperationException(); } 
/* 367 */     public Boolean remove(long i) { throw new UnsupportedOperationException(); } 
/* 368 */     public long indexOf(Object o) { return this.list.indexOf(o); } 
/* 369 */     public long lastIndexOf(Object o) { return this.list.lastIndexOf(o); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedBigList extends BooleanCollections.SynchronizedCollection
/*     */     implements BooleanBigList, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final BooleanBigList list;
/*     */ 
/*     */     protected SynchronizedBigList(BooleanBigList l, Object sync)
/*     */     {
/* 243 */       super(sync);
/* 244 */       this.list = l;
/*     */     }
/*     */ 
/*     */     protected SynchronizedBigList(BooleanBigList l) {
/* 248 */       super();
/* 249 */       this.list = l;
/*     */     }
/*     */     public boolean getBoolean(long i) {
/* 252 */       synchronized (this.sync) { return this.list.getBoolean(i); }  } 
/* 253 */     public boolean set(long i, boolean k) { synchronized (this.sync) { return this.list.set(i, k); }  } 
/* 254 */     public void add(long i, boolean k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 255 */     public boolean removeBoolean(long i) { synchronized (this.sync) { return this.list.removeBoolean(i); }  } 
/*     */     public long indexOf(boolean k) {
/* 257 */       synchronized (this.sync) { return this.list.indexOf(k); }  } 
/* 258 */     public long lastIndexOf(boolean k) { synchronized (this.sync) { return this.list.lastIndexOf(k); }  } 
/*     */     public boolean addAll(long index, Collection<? extends Boolean> c) {
/* 260 */       synchronized (this.sync) { return this.list.addAll(index, c); } 
/*     */     }
/* 262 */     public void getElements(long from, boolean[][] a, long offset, long length) { synchronized (this.sync) { this.list.getElements(from, a, offset, length); }  } 
/* 263 */     public void removeElements(long from, long to) { synchronized (this.sync) { this.list.removeElements(from, to); }  } 
/* 264 */     public void addElements(long index, boolean[][] a, long offset, long length) { synchronized (this.sync) { this.list.addElements(index, a, offset, length); }  } 
/* 265 */     public void addElements(long index, boolean[][] a) { synchronized (this.sync) { this.list.addElements(index, a); }  } 
/* 266 */     public void size(long size) { synchronized (this.sync) { this.list.size(size); }  } 
/* 267 */     public long size64() { synchronized (this.sync) { return this.list.size64(); }  } 
/*     */     public BooleanBigListIterator iterator() {
/* 269 */       return this.list.listIterator(); } 
/* 270 */     public BooleanBigListIterator listIterator() { return this.list.listIterator(); } 
/* 271 */     public BooleanBigListIterator listIterator(long i) { return this.list.listIterator(i); } 
/*     */     public BooleanBigList subList(long from, long to) {
/* 273 */       synchronized (this.sync) { return BooleanBigLists.synchronize(this.list.subList(from, to), this.sync); } 
/*     */     }
/* 275 */     public boolean equals(Object o) { synchronized (this.sync) { return this.list.equals(o); }  } 
/* 276 */     public int hashCode() { synchronized (this.sync) { return this.list.hashCode(); } }
/*     */ 
/*     */     public int compareTo(BigList<? extends Boolean> o) {
/* 279 */       synchronized (this.sync) { return this.list.compareTo(o); }
/*     */     }
/*     */ 
/*     */     public boolean addAll(long index, BooleanCollection c) {
/* 283 */       synchronized (this.sync) { return this.list.addAll(index, c); }  } 
/* 284 */     public boolean addAll(long index, BooleanBigList l) { synchronized (this.sync) { return this.list.addAll(index, l); }  } 
/* 285 */     public boolean addAll(BooleanBigList l) { synchronized (this.sync) { return this.list.addAll(l); }  } 
/*     */     public Boolean get(long i) {
/* 287 */       synchronized (this.sync) { return (Boolean)this.list.get(i); }  } 
/* 288 */     public void add(long i, Boolean k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 289 */     public Boolean set(long index, Boolean k) { synchronized (this.sync) { return (Boolean)this.list.set(index, k); }  } 
/* 290 */     public Boolean remove(long i) { synchronized (this.sync) { return (Boolean)this.list.remove(i); }  } 
/* 291 */     public long indexOf(Object o) { synchronized (this.sync) { return this.list.indexOf(o); }  } 
/* 292 */     public long lastIndexOf(Object o) { synchronized (this.sync) { return this.list.lastIndexOf(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends AbstractBooleanBigList
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     private final boolean element;
/*     */ 
/*     */     private Singleton(boolean element)
/*     */     {
/* 153 */       this.element = element;
/*     */     }
/*     */     public boolean getBoolean(long i) {
/* 156 */       if (i == 0L) return this.element; throw new IndexOutOfBoundsException(); } 
/* 157 */     public boolean removeBoolean(long i) { throw new UnsupportedOperationException(); } 
/* 158 */     public boolean contains(boolean k) { return k == this.element; } 
/*     */     public boolean addAll(Collection<? extends Boolean> c) {
/* 160 */       throw new UnsupportedOperationException(); } 
/* 161 */     public boolean addAll(long i, Collection<? extends Boolean> c) { throw new UnsupportedOperationException(); } 
/* 162 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/* 163 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */     public boolean[] toBooleanArray()
/*     */     {
/* 168 */       boolean[] a = new boolean[1];
/* 169 */       a[0] = this.element;
/* 170 */       return a;
/*     */     }
/*     */ 
/*     */     public BooleanBigListIterator listIterator() {
/* 174 */       return BooleanBigListIterators.singleton(this.element);
/*     */     }
/* 176 */     public BooleanBigListIterator iterator() { return listIterator(); }
/*     */ 
/*     */     public BooleanBigListIterator listIterator(long i) {
/* 179 */       if ((i > 1L) || (i < 0L)) throw new IndexOutOfBoundsException();
/* 180 */       BooleanBigListIterator l = listIterator();
/* 181 */       if (i == 1L) l.next();
/* 182 */       return l;
/*     */     }
/*     */ 
/*     */     public BooleanBigList subList(long from, long to)
/*     */     {
/* 187 */       ensureIndex(from);
/* 188 */       ensureIndex(to);
/* 189 */       if (from > to) throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
/*     */ 
/* 191 */       if ((from != 0L) || (to != 1L)) return BooleanBigLists.EMPTY_BIG_LIST;
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
/*     */     public boolean rem(boolean k) {
/* 204 */       throw new UnsupportedOperationException(); } 
/* 205 */     public boolean addAll(BooleanCollection c) { throw new UnsupportedOperationException(); } 
/* 206 */     public boolean addAll(long i, BooleanCollection c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptyBigList extends BooleanCollections.EmptyCollection
/*     */     implements BooleanBigList, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public void add(long index, boolean k)
/*     */     {
/*  79 */       throw new UnsupportedOperationException(); } 
/*  80 */     public boolean add(boolean k) { throw new UnsupportedOperationException(); } 
/*  81 */     public boolean removeBoolean(long i) { throw new UnsupportedOperationException(); } 
/*  82 */     public boolean set(long index, boolean k) { throw new UnsupportedOperationException(); } 
/*  83 */     public long indexOf(boolean k) { return -1L; } 
/*  84 */     public long lastIndexOf(boolean k) { return -1L; } 
/*  85 */     public boolean addAll(Collection<? extends Boolean> c) { throw new UnsupportedOperationException(); } 
/*  86 */     public boolean addAll(long i, Collection<? extends Boolean> c) { throw new UnsupportedOperationException(); } 
/*  87 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  88 */     public Boolean get(long i) { throw new IndexOutOfBoundsException(); } 
/*  89 */     public boolean addAll(BooleanCollection c) { throw new UnsupportedOperationException(); } 
/*  90 */     public boolean addAll(BooleanBigList c) { throw new UnsupportedOperationException(); } 
/*  91 */     public boolean addAll(long i, BooleanCollection c) { throw new UnsupportedOperationException(); } 
/*  92 */     public boolean addAll(long i, BooleanBigList c) { throw new UnsupportedOperationException(); } 
/*  93 */     public void add(long index, Boolean k) { throw new UnsupportedOperationException(); } 
/*  94 */     public boolean add(Boolean k) { throw new UnsupportedOperationException(); } 
/*  95 */     public Boolean set(long index, Boolean k) { throw new UnsupportedOperationException(); } 
/*  96 */     public boolean getBoolean(long i) { throw new IndexOutOfBoundsException(); } 
/*  97 */     public Boolean remove(long k) { throw new UnsupportedOperationException(); } 
/*  98 */     public long indexOf(Object k) { return -1L; } 
/*  99 */     public long lastIndexOf(Object k) { return -1L; } 
/*     */     public BooleanBigListIterator listIterator() {
/* 101 */       return BooleanBigListIterators.EMPTY_BIG_LIST_ITERATOR;
/*     */     }
/* 103 */     public BooleanBigListIterator iterator() { return BooleanBigListIterators.EMPTY_BIG_LIST_ITERATOR; }
/*     */ 
/*     */     public BooleanBigListIterator listIterator(long i) {
/* 106 */       if (i == 0L) return BooleanBigListIterators.EMPTY_BIG_LIST_ITERATOR; throw new IndexOutOfBoundsException(String.valueOf(i));
/*     */     }
/* 108 */     public BooleanBigList subList(long from, long to) { if ((from == 0L) && (to == 0L)) return this; throw new IndexOutOfBoundsException(); } 
/*     */     public void getElements(long from, boolean[][] a, long offset, long length) {
/* 110 */       BooleanBigArrays.ensureOffsetLength(a, offset, length); if (from != 0L) throw new IndexOutOfBoundsException();  } 
/* 111 */     public void removeElements(long from, long to) { throw new UnsupportedOperationException(); } 
/*     */     public void addElements(long index, boolean[][] a, long offset, long length) {
/* 113 */       throw new UnsupportedOperationException(); } 
/* 114 */     public void addElements(long index, boolean[][] a) { throw new UnsupportedOperationException(); } 
/*     */     public void size(long s) {
/* 116 */       throw new UnsupportedOperationException(); } 
/* 117 */     public long size64() { return 0L; }
/*     */ 
/*     */     public int compareTo(BigList<? extends Boolean> o) {
/* 120 */       if (o == this) return 0;
/* 121 */       return o.isEmpty() ? 0 : -1;
/*     */     }
/*     */     private Object readResolve() {
/* 124 */       return BooleanBigLists.EMPTY_BIG_LIST; } 
/* 125 */     public Object clone() { return BooleanBigLists.EMPTY_BIG_LIST; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanBigLists
 * JD-Core Version:    0.6.2
 */