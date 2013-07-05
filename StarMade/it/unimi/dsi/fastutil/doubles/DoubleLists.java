/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class DoubleLists
/*     */ {
/* 146 */   public static final EmptyList EMPTY_LIST = new EmptyList();
/*     */ 
/*     */   public static DoubleList shuffle(DoubleList l, Random random)
/*     */   {
/*  61 */     for (int i = l.size(); i-- != 0; ) {
/*  62 */       int p = random.nextInt(i + 1);
/*  63 */       double t = l.getDouble(i);
/*  64 */       l.set(i, l.getDouble(p));
/*  65 */       l.set(p, t);
/*     */     }
/*  67 */     return l;
/*     */   }
/*     */ 
/*     */   public static DoubleList singleton(double element)
/*     */   {
/* 227 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static DoubleList singleton(Object element)
/*     */   {
/* 237 */     return new Singleton(((Double)element).doubleValue(), null);
/*     */   }
/*     */ 
/*     */   public static DoubleList synchronize(DoubleList l)
/*     */   {
/* 319 */     return new SynchronizedList(l);
/*     */   }
/*     */ 
/*     */   public static DoubleList synchronize(DoubleList l, Object sync)
/*     */   {
/* 329 */     return new SynchronizedList(l, sync);
/*     */   }
/*     */ 
/*     */   public static DoubleList unmodifiable(DoubleList l)
/*     */   {
/* 404 */     return new UnmodifiableList(l);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableList extends DoubleCollections.UnmodifiableCollection
/*     */     implements DoubleList, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final DoubleList list;
/*     */ 
/*     */     protected UnmodifiableList(DoubleList l)
/*     */     {
/* 342 */       super();
/* 343 */       this.list = l;
/*     */     }
/*     */     public double getDouble(int i) {
/* 346 */       return this.list.getDouble(i); } 
/* 347 */     public double set(int i, double k) { throw new UnsupportedOperationException(); } 
/* 348 */     public void add(int i, double k) { throw new UnsupportedOperationException(); } 
/* 349 */     public double removeDouble(int i) { throw new UnsupportedOperationException(); } 
/*     */     public int indexOf(double k) {
/* 351 */       return this.list.indexOf(k); } 
/* 352 */     public int lastIndexOf(double k) { return this.list.lastIndexOf(k); } 
/*     */     public boolean addAll(int index, Collection<? extends Double> c) {
/* 354 */       throw new UnsupportedOperationException();
/*     */     }
/* 356 */     public void getElements(int from, double[] a, int offset, int length) { this.list.getElements(from, a, offset, length); } 
/* 357 */     public void removeElements(int from, int to) { throw new UnsupportedOperationException(); } 
/* 358 */     public void addElements(int index, double[] a, int offset, int length) { throw new UnsupportedOperationException(); } 
/* 359 */     public void addElements(int index, double[] a) { throw new UnsupportedOperationException(); } 
/* 360 */     public void size(int size) { this.list.size(size); } 
/*     */     public DoubleListIterator iterator() {
/* 362 */       return listIterator(); } 
/* 363 */     public DoubleListIterator listIterator() { return DoubleIterators.unmodifiable(this.list.listIterator()); } 
/* 364 */     public DoubleListIterator listIterator(int i) { return DoubleIterators.unmodifiable(this.list.listIterator(i)); } 
/*     */     @Deprecated
/*     */     public DoubleListIterator doubleListIterator() {
/* 367 */       return listIterator();
/*     */     }
/* 370 */     @Deprecated
/*     */     public DoubleListIterator doubleListIterator(int i) { return listIterator(i); } 
/*     */     public DoubleList subList(int from, int to) {
/* 372 */       return DoubleLists.unmodifiable(this.list.subList(from, to));
/*     */     }
/* 375 */     @Deprecated
/*     */     public DoubleList doubleSubList(int from, int to) { return subList(from, to); } 
/*     */     public boolean equals(Object o) {
/* 377 */       return this.collection.equals(o); } 
/* 378 */     public int hashCode() { return this.collection.hashCode(); }
/*     */ 
/*     */     public int compareTo(List<? extends Double> o) {
/* 381 */       return this.list.compareTo(o);
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, DoubleCollection c) {
/* 385 */       throw new UnsupportedOperationException(); } 
/* 386 */     public boolean addAll(DoubleList l) { throw new UnsupportedOperationException(); } 
/* 387 */     public boolean addAll(int index, DoubleList l) { throw new UnsupportedOperationException(); } 
/* 388 */     public Double get(int i) { return (Double)this.list.get(i); } 
/* 389 */     public void add(int i, Double k) { throw new UnsupportedOperationException(); } 
/* 390 */     public Double set(int index, Double k) { throw new UnsupportedOperationException(); } 
/* 391 */     public Double remove(int i) { throw new UnsupportedOperationException(); } 
/* 392 */     public int indexOf(Object o) { return this.list.indexOf(o); } 
/* 393 */     public int lastIndexOf(Object o) { return this.list.lastIndexOf(o); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedList extends DoubleCollections.SynchronizedCollection
/*     */     implements DoubleList, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final DoubleList list;
/*     */ 
/*     */     protected SynchronizedList(DoubleList l, Object sync)
/*     */     {
/* 251 */       super(sync);
/* 252 */       this.list = l;
/*     */     }
/*     */ 
/*     */     protected SynchronizedList(DoubleList l) {
/* 256 */       super();
/* 257 */       this.list = l;
/*     */     }
/*     */     public double getDouble(int i) {
/* 260 */       synchronized (this.sync) { return this.list.getDouble(i); }  } 
/* 261 */     public double set(int i, double k) { synchronized (this.sync) { return this.list.set(i, k); }  } 
/* 262 */     public void add(int i, double k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 263 */     public double removeDouble(int i) { synchronized (this.sync) { return this.list.removeDouble(i); }  } 
/*     */     public int indexOf(double k) {
/* 265 */       synchronized (this.sync) { return this.list.indexOf(k); }  } 
/* 266 */     public int lastIndexOf(double k) { synchronized (this.sync) { return this.list.lastIndexOf(k); }  } 
/*     */     public boolean addAll(int index, Collection<? extends Double> c) {
/* 268 */       synchronized (this.sync) { return this.list.addAll(index, c); } 
/*     */     }
/* 270 */     public void getElements(int from, double[] a, int offset, int length) { synchronized (this.sync) { this.list.getElements(from, a, offset, length); }  } 
/* 271 */     public void removeElements(int from, int to) { synchronized (this.sync) { this.list.removeElements(from, to); }  } 
/* 272 */     public void addElements(int index, double[] a, int offset, int length) { synchronized (this.sync) { this.list.addElements(index, a, offset, length); }  } 
/* 273 */     public void addElements(int index, double[] a) { synchronized (this.sync) { this.list.addElements(index, a); }  } 
/* 274 */     public void size(int size) { synchronized (this.sync) { this.list.size(size); }  } 
/*     */     public DoubleListIterator iterator() {
/* 276 */       return this.list.listIterator(); } 
/* 277 */     public DoubleListIterator listIterator() { return this.list.listIterator(); } 
/* 278 */     public DoubleListIterator listIterator(int i) { return this.list.listIterator(i); } 
/*     */     @Deprecated
/*     */     public DoubleListIterator doubleListIterator() {
/* 281 */       return listIterator();
/*     */     }
/* 284 */     @Deprecated
/*     */     public DoubleListIterator doubleListIterator(int i) { return listIterator(i); } 
/*     */     public DoubleList subList(int from, int to) {
/* 286 */       synchronized (this.sync) { return DoubleLists.synchronize(this.list.subList(from, to), this.sync); } 
/*     */     }
/* 289 */     @Deprecated
/*     */     public DoubleList doubleSubList(int from, int to) { return subList(from, to); } 
/*     */     public boolean equals(Object o) {
/* 291 */       synchronized (this.sync) { return this.collection.equals(o); }  } 
/* 292 */     public int hashCode() { synchronized (this.sync) { return this.collection.hashCode(); } }
/*     */ 
/*     */     public int compareTo(List<? extends Double> o) {
/* 295 */       synchronized (this.sync) { return this.list.compareTo(o); }
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, DoubleCollection c) {
/* 299 */       synchronized (this.sync) { return this.list.addAll(index, c); }  } 
/* 300 */     public boolean addAll(int index, DoubleList l) { synchronized (this.sync) { return this.list.addAll(index, l); }  } 
/* 301 */     public boolean addAll(DoubleList l) { synchronized (this.sync) { return this.list.addAll(l); }  } 
/*     */     public Double get(int i) {
/* 303 */       synchronized (this.sync) { return (Double)this.list.get(i); }  } 
/* 304 */     public void add(int i, Double k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 305 */     public Double set(int index, Double k) { synchronized (this.sync) { return (Double)this.list.set(index, k); }  } 
/* 306 */     public Double remove(int i) { synchronized (this.sync) { return (Double)this.list.remove(i); }  } 
/* 307 */     public int indexOf(Object o) { synchronized (this.sync) { return this.list.indexOf(o); }  } 
/* 308 */     public int lastIndexOf(Object o) { synchronized (this.sync) { return this.list.lastIndexOf(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends AbstractDoubleList
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     private final double element;
/*     */ 
/*     */     private Singleton(double element)
/*     */     {
/* 163 */       this.element = element;
/*     */     }
/*     */     public double getDouble(int i) {
/* 166 */       if (i == 0) return this.element; throw new IndexOutOfBoundsException(); } 
/* 167 */     public double removeDouble(int i) { throw new UnsupportedOperationException(); } 
/* 168 */     public boolean contains(double k) { return k == this.element; } 
/*     */     public boolean addAll(Collection<? extends Double> c) {
/* 170 */       throw new UnsupportedOperationException(); } 
/* 171 */     public boolean addAll(int i, Collection<? extends Double> c) { throw new UnsupportedOperationException(); } 
/* 172 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/* 173 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */     public double[] toDoubleArray()
/*     */     {
/* 178 */       double[] a = new double[1];
/* 179 */       a[0] = this.element;
/* 180 */       return a;
/*     */     }
/*     */ 
/*     */     public DoubleListIterator listIterator() {
/* 184 */       return DoubleIterators.singleton(this.element);
/*     */     }
/* 186 */     public DoubleListIterator iterator() { return listIterator(); }
/*     */ 
/*     */     public DoubleListIterator listIterator(int i) {
/* 189 */       if ((i > 1) || (i < 0)) throw new IndexOutOfBoundsException();
/* 190 */       DoubleListIterator l = listIterator();
/* 191 */       if (i == 1) l.next();
/* 192 */       return l;
/*     */     }
/*     */ 
/*     */     public DoubleList subList(int from, int to)
/*     */     {
/* 197 */       ensureIndex(from);
/* 198 */       ensureIndex(to);
/* 199 */       if (from > to) throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
/*     */ 
/* 201 */       if ((from != 0) || (to != 1)) return DoubleLists.EMPTY_LIST;
/* 202 */       return this;
/*     */     }
/*     */     public int size() {
/* 205 */       return 1; } 
/* 206 */     public void size(int size) { throw new UnsupportedOperationException(); } 
/* 207 */     public void clear() { throw new UnsupportedOperationException(); } 
/*     */     public Object clone() {
/* 209 */       return this;
/*     */     }
/*     */     public boolean rem(double k) {
/* 212 */       throw new UnsupportedOperationException(); } 
/* 213 */     public boolean addAll(DoubleCollection c) { throw new UnsupportedOperationException(); } 
/* 214 */     public boolean addAll(int i, DoubleCollection c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptyList extends DoubleCollections.EmptyCollection
/*     */     implements DoubleList, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public void add(int index, double k)
/*     */     {
/*  77 */       throw new UnsupportedOperationException(); } 
/*  78 */     public boolean add(double k) { throw new UnsupportedOperationException(); } 
/*  79 */     public double removeDouble(int i) { throw new UnsupportedOperationException(); } 
/*  80 */     public double set(int index, double k) { throw new UnsupportedOperationException(); } 
/*  81 */     public int indexOf(double k) { return -1; } 
/*  82 */     public int lastIndexOf(double k) { return -1; } 
/*  83 */     public boolean addAll(Collection<? extends Double> c) { throw new UnsupportedOperationException(); } 
/*  84 */     public boolean addAll(int i, Collection<? extends Double> c) { throw new UnsupportedOperationException(); } 
/*  85 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  86 */     public Double get(int i) { throw new IndexOutOfBoundsException(); } 
/*  87 */     public boolean addAll(DoubleCollection c) { throw new UnsupportedOperationException(); } 
/*  88 */     public boolean addAll(DoubleList c) { throw new UnsupportedOperationException(); } 
/*  89 */     public boolean addAll(int i, DoubleCollection c) { throw new UnsupportedOperationException(); } 
/*  90 */     public boolean addAll(int i, DoubleList c) { throw new UnsupportedOperationException(); } 
/*  91 */     public void add(int index, Double k) { throw new UnsupportedOperationException(); } 
/*  92 */     public boolean add(Double k) { throw new UnsupportedOperationException(); } 
/*  93 */     public Double set(int index, Double k) { throw new UnsupportedOperationException(); } 
/*  94 */     public double getDouble(int i) { throw new IndexOutOfBoundsException(); } 
/*  95 */     public Double remove(int k) { throw new UnsupportedOperationException(); } 
/*  96 */     public int indexOf(Object k) { return -1; } 
/*  97 */     public int lastIndexOf(Object k) { return -1; }
/*     */ 
/*     */     @Deprecated
/*     */     public DoubleIterator doubleIterator()
/*     */     {
/* 102 */       return DoubleIterators.EMPTY_ITERATOR;
/*     */     }
/* 104 */     public DoubleListIterator listIterator() { return DoubleIterators.EMPTY_ITERATOR; } 
/*     */     public DoubleListIterator iterator() {
/* 106 */       return DoubleIterators.EMPTY_ITERATOR;
/*     */     }
/* 108 */     public DoubleListIterator listIterator(int i) { if (i == 0) return DoubleIterators.EMPTY_ITERATOR; throw new IndexOutOfBoundsException(String.valueOf(i)); } 
/*     */     @Deprecated
/*     */     public DoubleListIterator doubleListIterator() {
/* 111 */       return listIterator();
/*     */     }
/* 114 */     @Deprecated
/*     */     public DoubleListIterator doubleListIterator(int i) { return listIterator(i); } 
/*     */     public DoubleList subList(int from, int to) {
/* 116 */       if ((from == 0) && (to == 0)) return this; throw new IndexOutOfBoundsException();
/*     */     }
/* 119 */     @Deprecated
/*     */     public DoubleList doubleSubList(int from, int to) { return subList(from, to); } 
/*     */     public void getElements(int from, double[] a, int offset, int length) {
/* 121 */       if ((from == 0) && (length == 0) && (offset >= 0) && (offset <= a.length)) return; throw new IndexOutOfBoundsException(); } 
/* 122 */     public void removeElements(int from, int to) { throw new UnsupportedOperationException(); } 
/*     */     public void addElements(int index, double[] a, int offset, int length) {
/* 124 */       throw new UnsupportedOperationException(); } 
/* 125 */     public void addElements(int index, double[] a) { throw new UnsupportedOperationException(); } 
/*     */     public void size(int s) {
/* 127 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     public int compareTo(List<? extends Double> o) {
/* 130 */       if (o == this) return 0;
/* 131 */       return o.isEmpty() ? 0 : -1;
/*     */     }
/*     */     private Object readResolve() {
/* 134 */       return DoubleLists.EMPTY_LIST; } 
/* 135 */     public Object clone() { return DoubleLists.EMPTY_LIST; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleLists
 * JD-Core Version:    0.6.2
 */