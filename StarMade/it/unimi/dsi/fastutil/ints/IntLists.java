/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class IntLists
/*     */ {
/* 146 */   public static final EmptyList EMPTY_LIST = new EmptyList();
/*     */ 
/*     */   public static IntList shuffle(IntList l, Random random)
/*     */   {
/*  61 */     for (int i = l.size(); i-- != 0; ) {
/*  62 */       int p = random.nextInt(i + 1);
/*  63 */       int t = l.getInt(i);
/*  64 */       l.set(i, l.getInt(p));
/*  65 */       l.set(p, t);
/*     */     }
/*  67 */     return l;
/*     */   }
/*     */ 
/*     */   public static IntList singleton(int element)
/*     */   {
/* 227 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static IntList singleton(Object element)
/*     */   {
/* 237 */     return new Singleton(((Integer)element).intValue(), null);
/*     */   }
/*     */ 
/*     */   public static IntList synchronize(IntList l)
/*     */   {
/* 319 */     return new SynchronizedList(l);
/*     */   }
/*     */ 
/*     */   public static IntList synchronize(IntList l, Object sync)
/*     */   {
/* 329 */     return new SynchronizedList(l, sync);
/*     */   }
/*     */ 
/*     */   public static IntList unmodifiable(IntList l)
/*     */   {
/* 404 */     return new UnmodifiableList(l);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableList extends IntCollections.UnmodifiableCollection
/*     */     implements IntList, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final IntList list;
/*     */ 
/*     */     protected UnmodifiableList(IntList l)
/*     */     {
/* 342 */       super();
/* 343 */       this.list = l;
/*     */     }
/*     */     public int getInt(int i) {
/* 346 */       return this.list.getInt(i); } 
/* 347 */     public int set(int i, int k) { throw new UnsupportedOperationException(); } 
/* 348 */     public void add(int i, int k) { throw new UnsupportedOperationException(); } 
/* 349 */     public int removeInt(int i) { throw new UnsupportedOperationException(); } 
/*     */     public int indexOf(int k) {
/* 351 */       return this.list.indexOf(k); } 
/* 352 */     public int lastIndexOf(int k) { return this.list.lastIndexOf(k); } 
/*     */     public boolean addAll(int index, Collection<? extends Integer> c) {
/* 354 */       throw new UnsupportedOperationException();
/*     */     }
/* 356 */     public void getElements(int from, int[] a, int offset, int length) { this.list.getElements(from, a, offset, length); } 
/* 357 */     public void removeElements(int from, int to) { throw new UnsupportedOperationException(); } 
/* 358 */     public void addElements(int index, int[] a, int offset, int length) { throw new UnsupportedOperationException(); } 
/* 359 */     public void addElements(int index, int[] a) { throw new UnsupportedOperationException(); } 
/* 360 */     public void size(int size) { this.list.size(size); } 
/*     */     public IntListIterator iterator() {
/* 362 */       return listIterator(); } 
/* 363 */     public IntListIterator listIterator() { return IntIterators.unmodifiable(this.list.listIterator()); } 
/* 364 */     public IntListIterator listIterator(int i) { return IntIterators.unmodifiable(this.list.listIterator(i)); } 
/*     */     @Deprecated
/*     */     public IntListIterator intListIterator() {
/* 367 */       return listIterator();
/*     */     }
/* 370 */     @Deprecated
/*     */     public IntListIterator intListIterator(int i) { return listIterator(i); } 
/*     */     public IntList subList(int from, int to) {
/* 372 */       return IntLists.unmodifiable(this.list.subList(from, to));
/*     */     }
/* 375 */     @Deprecated
/*     */     public IntList intSubList(int from, int to) { return subList(from, to); } 
/*     */     public boolean equals(Object o) {
/* 377 */       return this.collection.equals(o); } 
/* 378 */     public int hashCode() { return this.collection.hashCode(); }
/*     */ 
/*     */     public int compareTo(List<? extends Integer> o) {
/* 381 */       return this.list.compareTo(o);
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, IntCollection c) {
/* 385 */       throw new UnsupportedOperationException(); } 
/* 386 */     public boolean addAll(IntList l) { throw new UnsupportedOperationException(); } 
/* 387 */     public boolean addAll(int index, IntList l) { throw new UnsupportedOperationException(); } 
/* 388 */     public Integer get(int i) { return (Integer)this.list.get(i); } 
/* 389 */     public void add(int i, Integer k) { throw new UnsupportedOperationException(); } 
/* 390 */     public Integer set(int index, Integer k) { throw new UnsupportedOperationException(); } 
/* 391 */     public Integer remove(int i) { throw new UnsupportedOperationException(); } 
/* 392 */     public int indexOf(Object o) { return this.list.indexOf(o); } 
/* 393 */     public int lastIndexOf(Object o) { return this.list.lastIndexOf(o); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedList extends IntCollections.SynchronizedCollection
/*     */     implements IntList, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final IntList list;
/*     */ 
/*     */     protected SynchronizedList(IntList l, Object sync)
/*     */     {
/* 251 */       super(sync);
/* 252 */       this.list = l;
/*     */     }
/*     */ 
/*     */     protected SynchronizedList(IntList l) {
/* 256 */       super();
/* 257 */       this.list = l;
/*     */     }
/*     */     public int getInt(int i) {
/* 260 */       synchronized (this.sync) { return this.list.getInt(i); }  } 
/* 261 */     public int set(int i, int k) { synchronized (this.sync) { return this.list.set(i, k); }  } 
/* 262 */     public void add(int i, int k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 263 */     public int removeInt(int i) { synchronized (this.sync) { return this.list.removeInt(i); }  } 
/*     */     public int indexOf(int k) {
/* 265 */       synchronized (this.sync) { return this.list.indexOf(k); }  } 
/* 266 */     public int lastIndexOf(int k) { synchronized (this.sync) { return this.list.lastIndexOf(k); }  } 
/*     */     public boolean addAll(int index, Collection<? extends Integer> c) {
/* 268 */       synchronized (this.sync) { return this.list.addAll(index, c); } 
/*     */     }
/* 270 */     public void getElements(int from, int[] a, int offset, int length) { synchronized (this.sync) { this.list.getElements(from, a, offset, length); }  } 
/* 271 */     public void removeElements(int from, int to) { synchronized (this.sync) { this.list.removeElements(from, to); }  } 
/* 272 */     public void addElements(int index, int[] a, int offset, int length) { synchronized (this.sync) { this.list.addElements(index, a, offset, length); }  } 
/* 273 */     public void addElements(int index, int[] a) { synchronized (this.sync) { this.list.addElements(index, a); }  } 
/* 274 */     public void size(int size) { synchronized (this.sync) { this.list.size(size); }  } 
/*     */     public IntListIterator iterator() {
/* 276 */       return this.list.listIterator(); } 
/* 277 */     public IntListIterator listIterator() { return this.list.listIterator(); } 
/* 278 */     public IntListIterator listIterator(int i) { return this.list.listIterator(i); } 
/*     */     @Deprecated
/*     */     public IntListIterator intListIterator() {
/* 281 */       return listIterator();
/*     */     }
/* 284 */     @Deprecated
/*     */     public IntListIterator intListIterator(int i) { return listIterator(i); } 
/*     */     public IntList subList(int from, int to) {
/* 286 */       synchronized (this.sync) { return IntLists.synchronize(this.list.subList(from, to), this.sync); } 
/*     */     }
/* 289 */     @Deprecated
/*     */     public IntList intSubList(int from, int to) { return subList(from, to); } 
/*     */     public boolean equals(Object o) {
/* 291 */       synchronized (this.sync) { return this.collection.equals(o); }  } 
/* 292 */     public int hashCode() { synchronized (this.sync) { return this.collection.hashCode(); } }
/*     */ 
/*     */     public int compareTo(List<? extends Integer> o) {
/* 295 */       synchronized (this.sync) { return this.list.compareTo(o); }
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, IntCollection c) {
/* 299 */       synchronized (this.sync) { return this.list.addAll(index, c); }  } 
/* 300 */     public boolean addAll(int index, IntList l) { synchronized (this.sync) { return this.list.addAll(index, l); }  } 
/* 301 */     public boolean addAll(IntList l) { synchronized (this.sync) { return this.list.addAll(l); }  } 
/*     */     public Integer get(int i) {
/* 303 */       synchronized (this.sync) { return (Integer)this.list.get(i); }  } 
/* 304 */     public void add(int i, Integer k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 305 */     public Integer set(int index, Integer k) { synchronized (this.sync) { return (Integer)this.list.set(index, k); }  } 
/* 306 */     public Integer remove(int i) { synchronized (this.sync) { return (Integer)this.list.remove(i); }  } 
/* 307 */     public int indexOf(Object o) { synchronized (this.sync) { return this.list.indexOf(o); }  } 
/* 308 */     public int lastIndexOf(Object o) { synchronized (this.sync) { return this.list.lastIndexOf(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends AbstractIntList
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     private final int element;
/*     */ 
/*     */     private Singleton(int element)
/*     */     {
/* 163 */       this.element = element;
/*     */     }
/*     */     public int getInt(int i) {
/* 166 */       if (i == 0) return this.element; throw new IndexOutOfBoundsException(); } 
/* 167 */     public int removeInt(int i) { throw new UnsupportedOperationException(); } 
/* 168 */     public boolean contains(int k) { return k == this.element; } 
/*     */     public boolean addAll(Collection<? extends Integer> c) {
/* 170 */       throw new UnsupportedOperationException(); } 
/* 171 */     public boolean addAll(int i, Collection<? extends Integer> c) { throw new UnsupportedOperationException(); } 
/* 172 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/* 173 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */     public int[] toIntArray()
/*     */     {
/* 178 */       int[] a = new int[1];
/* 179 */       a[0] = this.element;
/* 180 */       return a;
/*     */     }
/*     */ 
/*     */     public IntListIterator listIterator() {
/* 184 */       return IntIterators.singleton(this.element);
/*     */     }
/* 186 */     public IntListIterator iterator() { return listIterator(); }
/*     */ 
/*     */     public IntListIterator listIterator(int i) {
/* 189 */       if ((i > 1) || (i < 0)) throw new IndexOutOfBoundsException();
/* 190 */       IntListIterator l = listIterator();
/* 191 */       if (i == 1) l.next();
/* 192 */       return l;
/*     */     }
/*     */ 
/*     */     public IntList subList(int from, int to)
/*     */     {
/* 197 */       ensureIndex(from);
/* 198 */       ensureIndex(to);
/* 199 */       if (from > to) throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
/*     */ 
/* 201 */       if ((from != 0) || (to != 1)) return IntLists.EMPTY_LIST;
/* 202 */       return this;
/*     */     }
/*     */     public int size() {
/* 205 */       return 1; } 
/* 206 */     public void size(int size) { throw new UnsupportedOperationException(); } 
/* 207 */     public void clear() { throw new UnsupportedOperationException(); } 
/*     */     public Object clone() {
/* 209 */       return this;
/*     */     }
/*     */     public boolean rem(int k) {
/* 212 */       throw new UnsupportedOperationException(); } 
/* 213 */     public boolean addAll(IntCollection c) { throw new UnsupportedOperationException(); } 
/* 214 */     public boolean addAll(int i, IntCollection c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptyList extends IntCollections.EmptyCollection
/*     */     implements IntList, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public void add(int index, int k)
/*     */     {
/*  77 */       throw new UnsupportedOperationException(); } 
/*  78 */     public boolean add(int k) { throw new UnsupportedOperationException(); } 
/*  79 */     public int removeInt(int i) { throw new UnsupportedOperationException(); } 
/*  80 */     public int set(int index, int k) { throw new UnsupportedOperationException(); } 
/*  81 */     public int indexOf(int k) { return -1; } 
/*  82 */     public int lastIndexOf(int k) { return -1; } 
/*  83 */     public boolean addAll(Collection<? extends Integer> c) { throw new UnsupportedOperationException(); } 
/*  84 */     public boolean addAll(int i, Collection<? extends Integer> c) { throw new UnsupportedOperationException(); } 
/*  85 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  86 */     public Integer get(int i) { throw new IndexOutOfBoundsException(); } 
/*  87 */     public boolean addAll(IntCollection c) { throw new UnsupportedOperationException(); } 
/*  88 */     public boolean addAll(IntList c) { throw new UnsupportedOperationException(); } 
/*  89 */     public boolean addAll(int i, IntCollection c) { throw new UnsupportedOperationException(); } 
/*  90 */     public boolean addAll(int i, IntList c) { throw new UnsupportedOperationException(); } 
/*  91 */     public void add(int index, Integer k) { throw new UnsupportedOperationException(); } 
/*  92 */     public boolean add(Integer k) { throw new UnsupportedOperationException(); } 
/*  93 */     public Integer set(int index, Integer k) { throw new UnsupportedOperationException(); } 
/*  94 */     public int getInt(int i) { throw new IndexOutOfBoundsException(); } 
/*  95 */     public Integer remove(int k) { throw new UnsupportedOperationException(); } 
/*  96 */     public int indexOf(Object k) { return -1; } 
/*  97 */     public int lastIndexOf(Object k) { return -1; }
/*     */ 
/*     */     @Deprecated
/*     */     public IntIterator intIterator()
/*     */     {
/* 102 */       return IntIterators.EMPTY_ITERATOR;
/*     */     }
/* 104 */     public IntListIterator listIterator() { return IntIterators.EMPTY_ITERATOR; } 
/*     */     public IntListIterator iterator() {
/* 106 */       return IntIterators.EMPTY_ITERATOR;
/*     */     }
/* 108 */     public IntListIterator listIterator(int i) { if (i == 0) return IntIterators.EMPTY_ITERATOR; throw new IndexOutOfBoundsException(String.valueOf(i)); } 
/*     */     @Deprecated
/*     */     public IntListIterator intListIterator() {
/* 111 */       return listIterator();
/*     */     }
/* 114 */     @Deprecated
/*     */     public IntListIterator intListIterator(int i) { return listIterator(i); } 
/*     */     public IntList subList(int from, int to) {
/* 116 */       if ((from == 0) && (to == 0)) return this; throw new IndexOutOfBoundsException();
/*     */     }
/* 119 */     @Deprecated
/*     */     public IntList intSubList(int from, int to) { return subList(from, to); } 
/*     */     public void getElements(int from, int[] a, int offset, int length) {
/* 121 */       if ((from == 0) && (length == 0) && (offset >= 0) && (offset <= a.length)) return; throw new IndexOutOfBoundsException(); } 
/* 122 */     public void removeElements(int from, int to) { throw new UnsupportedOperationException(); } 
/*     */     public void addElements(int index, int[] a, int offset, int length) {
/* 124 */       throw new UnsupportedOperationException(); } 
/* 125 */     public void addElements(int index, int[] a) { throw new UnsupportedOperationException(); } 
/*     */     public void size(int s) {
/* 127 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     public int compareTo(List<? extends Integer> o) {
/* 130 */       if (o == this) return 0;
/* 131 */       return o.isEmpty() ? 0 : -1;
/*     */     }
/*     */     private Object readResolve() {
/* 134 */       return IntLists.EMPTY_LIST; } 
/* 135 */     public Object clone() { return IntLists.EMPTY_LIST; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntLists
 * JD-Core Version:    0.6.2
 */