/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ShortLists
/*     */ {
/* 146 */   public static final EmptyList EMPTY_LIST = new EmptyList();
/*     */ 
/*     */   public static ShortList shuffle(ShortList l, Random random)
/*     */   {
/*  61 */     for (int i = l.size(); i-- != 0; ) {
/*  62 */       int p = random.nextInt(i + 1);
/*  63 */       short t = l.getShort(i);
/*  64 */       l.set(i, l.getShort(p));
/*  65 */       l.set(p, t);
/*     */     }
/*  67 */     return l;
/*     */   }
/*     */ 
/*     */   public static ShortList singleton(short element)
/*     */   {
/* 227 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static ShortList singleton(Object element)
/*     */   {
/* 237 */     return new Singleton(((Short)element).shortValue(), null);
/*     */   }
/*     */ 
/*     */   public static ShortList synchronize(ShortList l)
/*     */   {
/* 319 */     return new SynchronizedList(l);
/*     */   }
/*     */ 
/*     */   public static ShortList synchronize(ShortList l, Object sync)
/*     */   {
/* 329 */     return new SynchronizedList(l, sync);
/*     */   }
/*     */ 
/*     */   public static ShortList unmodifiable(ShortList l)
/*     */   {
/* 404 */     return new UnmodifiableList(l);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableList extends ShortCollections.UnmodifiableCollection
/*     */     implements ShortList, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ShortList list;
/*     */ 
/*     */     protected UnmodifiableList(ShortList l)
/*     */     {
/* 342 */       super();
/* 343 */       this.list = l;
/*     */     }
/*     */     public short getShort(int i) {
/* 346 */       return this.list.getShort(i); } 
/* 347 */     public short set(int i, short k) { throw new UnsupportedOperationException(); } 
/* 348 */     public void add(int i, short k) { throw new UnsupportedOperationException(); } 
/* 349 */     public short removeShort(int i) { throw new UnsupportedOperationException(); } 
/*     */     public int indexOf(short k) {
/* 351 */       return this.list.indexOf(k); } 
/* 352 */     public int lastIndexOf(short k) { return this.list.lastIndexOf(k); } 
/*     */     public boolean addAll(int index, Collection<? extends Short> c) {
/* 354 */       throw new UnsupportedOperationException();
/*     */     }
/* 356 */     public void getElements(int from, short[] a, int offset, int length) { this.list.getElements(from, a, offset, length); } 
/* 357 */     public void removeElements(int from, int to) { throw new UnsupportedOperationException(); } 
/* 358 */     public void addElements(int index, short[] a, int offset, int length) { throw new UnsupportedOperationException(); } 
/* 359 */     public void addElements(int index, short[] a) { throw new UnsupportedOperationException(); } 
/* 360 */     public void size(int size) { this.list.size(size); } 
/*     */     public ShortListIterator iterator() {
/* 362 */       return listIterator(); } 
/* 363 */     public ShortListIterator listIterator() { return ShortIterators.unmodifiable(this.list.listIterator()); } 
/* 364 */     public ShortListIterator listIterator(int i) { return ShortIterators.unmodifiable(this.list.listIterator(i)); } 
/*     */     @Deprecated
/*     */     public ShortListIterator shortListIterator() {
/* 367 */       return listIterator();
/*     */     }
/* 370 */     @Deprecated
/*     */     public ShortListIterator shortListIterator(int i) { return listIterator(i); } 
/*     */     public ShortList subList(int from, int to) {
/* 372 */       return ShortLists.unmodifiable(this.list.subList(from, to));
/*     */     }
/* 375 */     @Deprecated
/*     */     public ShortList shortSubList(int from, int to) { return subList(from, to); } 
/*     */     public boolean equals(Object o) {
/* 377 */       return this.collection.equals(o); } 
/* 378 */     public int hashCode() { return this.collection.hashCode(); }
/*     */ 
/*     */     public int compareTo(List<? extends Short> o) {
/* 381 */       return this.list.compareTo(o);
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, ShortCollection c) {
/* 385 */       throw new UnsupportedOperationException(); } 
/* 386 */     public boolean addAll(ShortList l) { throw new UnsupportedOperationException(); } 
/* 387 */     public boolean addAll(int index, ShortList l) { throw new UnsupportedOperationException(); } 
/* 388 */     public Short get(int i) { return (Short)this.list.get(i); } 
/* 389 */     public void add(int i, Short k) { throw new UnsupportedOperationException(); } 
/* 390 */     public Short set(int index, Short k) { throw new UnsupportedOperationException(); } 
/* 391 */     public Short remove(int i) { throw new UnsupportedOperationException(); } 
/* 392 */     public int indexOf(Object o) { return this.list.indexOf(o); } 
/* 393 */     public int lastIndexOf(Object o) { return this.list.lastIndexOf(o); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedList extends ShortCollections.SynchronizedCollection
/*     */     implements ShortList, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ShortList list;
/*     */ 
/*     */     protected SynchronizedList(ShortList l, Object sync)
/*     */     {
/* 251 */       super(sync);
/* 252 */       this.list = l;
/*     */     }
/*     */ 
/*     */     protected SynchronizedList(ShortList l) {
/* 256 */       super();
/* 257 */       this.list = l;
/*     */     }
/*     */     public short getShort(int i) {
/* 260 */       synchronized (this.sync) { return this.list.getShort(i); }  } 
/* 261 */     public short set(int i, short k) { synchronized (this.sync) { return this.list.set(i, k); }  } 
/* 262 */     public void add(int i, short k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 263 */     public short removeShort(int i) { synchronized (this.sync) { return this.list.removeShort(i); }  } 
/*     */     public int indexOf(short k) {
/* 265 */       synchronized (this.sync) { return this.list.indexOf(k); }  } 
/* 266 */     public int lastIndexOf(short k) { synchronized (this.sync) { return this.list.lastIndexOf(k); }  } 
/*     */     public boolean addAll(int index, Collection<? extends Short> c) {
/* 268 */       synchronized (this.sync) { return this.list.addAll(index, c); } 
/*     */     }
/* 270 */     public void getElements(int from, short[] a, int offset, int length) { synchronized (this.sync) { this.list.getElements(from, a, offset, length); }  } 
/* 271 */     public void removeElements(int from, int to) { synchronized (this.sync) { this.list.removeElements(from, to); }  } 
/* 272 */     public void addElements(int index, short[] a, int offset, int length) { synchronized (this.sync) { this.list.addElements(index, a, offset, length); }  } 
/* 273 */     public void addElements(int index, short[] a) { synchronized (this.sync) { this.list.addElements(index, a); }  } 
/* 274 */     public void size(int size) { synchronized (this.sync) { this.list.size(size); }  } 
/*     */     public ShortListIterator iterator() {
/* 276 */       return this.list.listIterator(); } 
/* 277 */     public ShortListIterator listIterator() { return this.list.listIterator(); } 
/* 278 */     public ShortListIterator listIterator(int i) { return this.list.listIterator(i); } 
/*     */     @Deprecated
/*     */     public ShortListIterator shortListIterator() {
/* 281 */       return listIterator();
/*     */     }
/* 284 */     @Deprecated
/*     */     public ShortListIterator shortListIterator(int i) { return listIterator(i); } 
/*     */     public ShortList subList(int from, int to) {
/* 286 */       synchronized (this.sync) { return ShortLists.synchronize(this.list.subList(from, to), this.sync); } 
/*     */     }
/* 289 */     @Deprecated
/*     */     public ShortList shortSubList(int from, int to) { return subList(from, to); } 
/*     */     public boolean equals(Object o) {
/* 291 */       synchronized (this.sync) { return this.collection.equals(o); }  } 
/* 292 */     public int hashCode() { synchronized (this.sync) { return this.collection.hashCode(); } }
/*     */ 
/*     */     public int compareTo(List<? extends Short> o) {
/* 295 */       synchronized (this.sync) { return this.list.compareTo(o); }
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, ShortCollection c) {
/* 299 */       synchronized (this.sync) { return this.list.addAll(index, c); }  } 
/* 300 */     public boolean addAll(int index, ShortList l) { synchronized (this.sync) { return this.list.addAll(index, l); }  } 
/* 301 */     public boolean addAll(ShortList l) { synchronized (this.sync) { return this.list.addAll(l); }  } 
/*     */     public Short get(int i) {
/* 303 */       synchronized (this.sync) { return (Short)this.list.get(i); }  } 
/* 304 */     public void add(int i, Short k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 305 */     public Short set(int index, Short k) { synchronized (this.sync) { return (Short)this.list.set(index, k); }  } 
/* 306 */     public Short remove(int i) { synchronized (this.sync) { return (Short)this.list.remove(i); }  } 
/* 307 */     public int indexOf(Object o) { synchronized (this.sync) { return this.list.indexOf(o); }  } 
/* 308 */     public int lastIndexOf(Object o) { synchronized (this.sync) { return this.list.lastIndexOf(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends AbstractShortList
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     private final short element;
/*     */ 
/*     */     private Singleton(short element)
/*     */     {
/* 163 */       this.element = element;
/*     */     }
/*     */     public short getShort(int i) {
/* 166 */       if (i == 0) return this.element; throw new IndexOutOfBoundsException(); } 
/* 167 */     public short removeShort(int i) { throw new UnsupportedOperationException(); } 
/* 168 */     public boolean contains(short k) { return k == this.element; } 
/*     */     public boolean addAll(Collection<? extends Short> c) {
/* 170 */       throw new UnsupportedOperationException(); } 
/* 171 */     public boolean addAll(int i, Collection<? extends Short> c) { throw new UnsupportedOperationException(); } 
/* 172 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/* 173 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */     public short[] toShortArray()
/*     */     {
/* 178 */       short[] a = new short[1];
/* 179 */       a[0] = this.element;
/* 180 */       return a;
/*     */     }
/*     */ 
/*     */     public ShortListIterator listIterator() {
/* 184 */       return ShortIterators.singleton(this.element);
/*     */     }
/* 186 */     public ShortListIterator iterator() { return listIterator(); }
/*     */ 
/*     */     public ShortListIterator listIterator(int i) {
/* 189 */       if ((i > 1) || (i < 0)) throw new IndexOutOfBoundsException();
/* 190 */       ShortListIterator l = listIterator();
/* 191 */       if (i == 1) l.next();
/* 192 */       return l;
/*     */     }
/*     */ 
/*     */     public ShortList subList(int from, int to)
/*     */     {
/* 197 */       ensureIndex(from);
/* 198 */       ensureIndex(to);
/* 199 */       if (from > to) throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
/*     */ 
/* 201 */       if ((from != 0) || (to != 1)) return ShortLists.EMPTY_LIST;
/* 202 */       return this;
/*     */     }
/*     */     public int size() {
/* 205 */       return 1; } 
/* 206 */     public void size(int size) { throw new UnsupportedOperationException(); } 
/* 207 */     public void clear() { throw new UnsupportedOperationException(); } 
/*     */     public Object clone() {
/* 209 */       return this;
/*     */     }
/*     */     public boolean rem(short k) {
/* 212 */       throw new UnsupportedOperationException(); } 
/* 213 */     public boolean addAll(ShortCollection c) { throw new UnsupportedOperationException(); } 
/* 214 */     public boolean addAll(int i, ShortCollection c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptyList extends ShortCollections.EmptyCollection
/*     */     implements ShortList, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public void add(int index, short k)
/*     */     {
/*  77 */       throw new UnsupportedOperationException(); } 
/*  78 */     public boolean add(short k) { throw new UnsupportedOperationException(); } 
/*  79 */     public short removeShort(int i) { throw new UnsupportedOperationException(); } 
/*  80 */     public short set(int index, short k) { throw new UnsupportedOperationException(); } 
/*  81 */     public int indexOf(short k) { return -1; } 
/*  82 */     public int lastIndexOf(short k) { return -1; } 
/*  83 */     public boolean addAll(Collection<? extends Short> c) { throw new UnsupportedOperationException(); } 
/*  84 */     public boolean addAll(int i, Collection<? extends Short> c) { throw new UnsupportedOperationException(); } 
/*  85 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  86 */     public Short get(int i) { throw new IndexOutOfBoundsException(); } 
/*  87 */     public boolean addAll(ShortCollection c) { throw new UnsupportedOperationException(); } 
/*  88 */     public boolean addAll(ShortList c) { throw new UnsupportedOperationException(); } 
/*  89 */     public boolean addAll(int i, ShortCollection c) { throw new UnsupportedOperationException(); } 
/*  90 */     public boolean addAll(int i, ShortList c) { throw new UnsupportedOperationException(); } 
/*  91 */     public void add(int index, Short k) { throw new UnsupportedOperationException(); } 
/*  92 */     public boolean add(Short k) { throw new UnsupportedOperationException(); } 
/*  93 */     public Short set(int index, Short k) { throw new UnsupportedOperationException(); } 
/*  94 */     public short getShort(int i) { throw new IndexOutOfBoundsException(); } 
/*  95 */     public Short remove(int k) { throw new UnsupportedOperationException(); } 
/*  96 */     public int indexOf(Object k) { return -1; } 
/*  97 */     public int lastIndexOf(Object k) { return -1; }
/*     */ 
/*     */     @Deprecated
/*     */     public ShortIterator shortIterator()
/*     */     {
/* 102 */       return ShortIterators.EMPTY_ITERATOR;
/*     */     }
/* 104 */     public ShortListIterator listIterator() { return ShortIterators.EMPTY_ITERATOR; } 
/*     */     public ShortListIterator iterator() {
/* 106 */       return ShortIterators.EMPTY_ITERATOR;
/*     */     }
/* 108 */     public ShortListIterator listIterator(int i) { if (i == 0) return ShortIterators.EMPTY_ITERATOR; throw new IndexOutOfBoundsException(String.valueOf(i)); } 
/*     */     @Deprecated
/*     */     public ShortListIterator shortListIterator() {
/* 111 */       return listIterator();
/*     */     }
/* 114 */     @Deprecated
/*     */     public ShortListIterator shortListIterator(int i) { return listIterator(i); } 
/*     */     public ShortList subList(int from, int to) {
/* 116 */       if ((from == 0) && (to == 0)) return this; throw new IndexOutOfBoundsException();
/*     */     }
/* 119 */     @Deprecated
/*     */     public ShortList shortSubList(int from, int to) { return subList(from, to); } 
/*     */     public void getElements(int from, short[] a, int offset, int length) {
/* 121 */       if ((from == 0) && (length == 0) && (offset >= 0) && (offset <= a.length)) return; throw new IndexOutOfBoundsException(); } 
/* 122 */     public void removeElements(int from, int to) { throw new UnsupportedOperationException(); } 
/*     */     public void addElements(int index, short[] a, int offset, int length) {
/* 124 */       throw new UnsupportedOperationException(); } 
/* 125 */     public void addElements(int index, short[] a) { throw new UnsupportedOperationException(); } 
/*     */     public void size(int s) {
/* 127 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     public int compareTo(List<? extends Short> o) {
/* 130 */       if (o == this) return 0;
/* 131 */       return o.isEmpty() ? 0 : -1;
/*     */     }
/*     */     private Object readResolve() {
/* 134 */       return ShortLists.EMPTY_LIST; } 
/* 135 */     public Object clone() { return ShortLists.EMPTY_LIST; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortLists
 * JD-Core Version:    0.6.2
 */