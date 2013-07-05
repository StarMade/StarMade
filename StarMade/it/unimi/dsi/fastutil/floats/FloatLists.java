/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class FloatLists
/*     */ {
/* 146 */   public static final EmptyList EMPTY_LIST = new EmptyList();
/*     */ 
/*     */   public static FloatList shuffle(FloatList l, Random random)
/*     */   {
/*  61 */     for (int i = l.size(); i-- != 0; ) {
/*  62 */       int p = random.nextInt(i + 1);
/*  63 */       float t = l.getFloat(i);
/*  64 */       l.set(i, l.getFloat(p));
/*  65 */       l.set(p, t);
/*     */     }
/*  67 */     return l;
/*     */   }
/*     */ 
/*     */   public static FloatList singleton(float element)
/*     */   {
/* 227 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static FloatList singleton(Object element)
/*     */   {
/* 237 */     return new Singleton(((Float)element).floatValue(), null);
/*     */   }
/*     */ 
/*     */   public static FloatList synchronize(FloatList l)
/*     */   {
/* 319 */     return new SynchronizedList(l);
/*     */   }
/*     */ 
/*     */   public static FloatList synchronize(FloatList l, Object sync)
/*     */   {
/* 329 */     return new SynchronizedList(l, sync);
/*     */   }
/*     */ 
/*     */   public static FloatList unmodifiable(FloatList l)
/*     */   {
/* 404 */     return new UnmodifiableList(l);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableList extends FloatCollections.UnmodifiableCollection
/*     */     implements FloatList, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final FloatList list;
/*     */ 
/*     */     protected UnmodifiableList(FloatList l)
/*     */     {
/* 342 */       super();
/* 343 */       this.list = l;
/*     */     }
/*     */     public float getFloat(int i) {
/* 346 */       return this.list.getFloat(i); } 
/* 347 */     public float set(int i, float k) { throw new UnsupportedOperationException(); } 
/* 348 */     public void add(int i, float k) { throw new UnsupportedOperationException(); } 
/* 349 */     public float removeFloat(int i) { throw new UnsupportedOperationException(); } 
/*     */     public int indexOf(float k) {
/* 351 */       return this.list.indexOf(k); } 
/* 352 */     public int lastIndexOf(float k) { return this.list.lastIndexOf(k); } 
/*     */     public boolean addAll(int index, Collection<? extends Float> c) {
/* 354 */       throw new UnsupportedOperationException();
/*     */     }
/* 356 */     public void getElements(int from, float[] a, int offset, int length) { this.list.getElements(from, a, offset, length); } 
/* 357 */     public void removeElements(int from, int to) { throw new UnsupportedOperationException(); } 
/* 358 */     public void addElements(int index, float[] a, int offset, int length) { throw new UnsupportedOperationException(); } 
/* 359 */     public void addElements(int index, float[] a) { throw new UnsupportedOperationException(); } 
/* 360 */     public void size(int size) { this.list.size(size); } 
/*     */     public FloatListIterator iterator() {
/* 362 */       return listIterator(); } 
/* 363 */     public FloatListIterator listIterator() { return FloatIterators.unmodifiable(this.list.listIterator()); } 
/* 364 */     public FloatListIterator listIterator(int i) { return FloatIterators.unmodifiable(this.list.listIterator(i)); } 
/*     */     @Deprecated
/*     */     public FloatListIterator floatListIterator() {
/* 367 */       return listIterator();
/*     */     }
/* 370 */     @Deprecated
/*     */     public FloatListIterator floatListIterator(int i) { return listIterator(i); } 
/*     */     public FloatList subList(int from, int to) {
/* 372 */       return FloatLists.unmodifiable(this.list.subList(from, to));
/*     */     }
/* 375 */     @Deprecated
/*     */     public FloatList floatSubList(int from, int to) { return subList(from, to); } 
/*     */     public boolean equals(Object o) {
/* 377 */       return this.collection.equals(o); } 
/* 378 */     public int hashCode() { return this.collection.hashCode(); }
/*     */ 
/*     */     public int compareTo(List<? extends Float> o) {
/* 381 */       return this.list.compareTo(o);
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, FloatCollection c) {
/* 385 */       throw new UnsupportedOperationException(); } 
/* 386 */     public boolean addAll(FloatList l) { throw new UnsupportedOperationException(); } 
/* 387 */     public boolean addAll(int index, FloatList l) { throw new UnsupportedOperationException(); } 
/* 388 */     public Float get(int i) { return (Float)this.list.get(i); } 
/* 389 */     public void add(int i, Float k) { throw new UnsupportedOperationException(); } 
/* 390 */     public Float set(int index, Float k) { throw new UnsupportedOperationException(); } 
/* 391 */     public Float remove(int i) { throw new UnsupportedOperationException(); } 
/* 392 */     public int indexOf(Object o) { return this.list.indexOf(o); } 
/* 393 */     public int lastIndexOf(Object o) { return this.list.lastIndexOf(o); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedList extends FloatCollections.SynchronizedCollection
/*     */     implements FloatList, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final FloatList list;
/*     */ 
/*     */     protected SynchronizedList(FloatList l, Object sync)
/*     */     {
/* 251 */       super(sync);
/* 252 */       this.list = l;
/*     */     }
/*     */ 
/*     */     protected SynchronizedList(FloatList l) {
/* 256 */       super();
/* 257 */       this.list = l;
/*     */     }
/*     */     public float getFloat(int i) {
/* 260 */       synchronized (this.sync) { return this.list.getFloat(i); }  } 
/* 261 */     public float set(int i, float k) { synchronized (this.sync) { return this.list.set(i, k); }  } 
/* 262 */     public void add(int i, float k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 263 */     public float removeFloat(int i) { synchronized (this.sync) { return this.list.removeFloat(i); }  } 
/*     */     public int indexOf(float k) {
/* 265 */       synchronized (this.sync) { return this.list.indexOf(k); }  } 
/* 266 */     public int lastIndexOf(float k) { synchronized (this.sync) { return this.list.lastIndexOf(k); }  } 
/*     */     public boolean addAll(int index, Collection<? extends Float> c) {
/* 268 */       synchronized (this.sync) { return this.list.addAll(index, c); } 
/*     */     }
/* 270 */     public void getElements(int from, float[] a, int offset, int length) { synchronized (this.sync) { this.list.getElements(from, a, offset, length); }  } 
/* 271 */     public void removeElements(int from, int to) { synchronized (this.sync) { this.list.removeElements(from, to); }  } 
/* 272 */     public void addElements(int index, float[] a, int offset, int length) { synchronized (this.sync) { this.list.addElements(index, a, offset, length); }  } 
/* 273 */     public void addElements(int index, float[] a) { synchronized (this.sync) { this.list.addElements(index, a); }  } 
/* 274 */     public void size(int size) { synchronized (this.sync) { this.list.size(size); }  } 
/*     */     public FloatListIterator iterator() {
/* 276 */       return this.list.listIterator(); } 
/* 277 */     public FloatListIterator listIterator() { return this.list.listIterator(); } 
/* 278 */     public FloatListIterator listIterator(int i) { return this.list.listIterator(i); } 
/*     */     @Deprecated
/*     */     public FloatListIterator floatListIterator() {
/* 281 */       return listIterator();
/*     */     }
/* 284 */     @Deprecated
/*     */     public FloatListIterator floatListIterator(int i) { return listIterator(i); } 
/*     */     public FloatList subList(int from, int to) {
/* 286 */       synchronized (this.sync) { return FloatLists.synchronize(this.list.subList(from, to), this.sync); } 
/*     */     }
/* 289 */     @Deprecated
/*     */     public FloatList floatSubList(int from, int to) { return subList(from, to); } 
/*     */     public boolean equals(Object o) {
/* 291 */       synchronized (this.sync) { return this.collection.equals(o); }  } 
/* 292 */     public int hashCode() { synchronized (this.sync) { return this.collection.hashCode(); } }
/*     */ 
/*     */     public int compareTo(List<? extends Float> o) {
/* 295 */       synchronized (this.sync) { return this.list.compareTo(o); }
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, FloatCollection c) {
/* 299 */       synchronized (this.sync) { return this.list.addAll(index, c); }  } 
/* 300 */     public boolean addAll(int index, FloatList l) { synchronized (this.sync) { return this.list.addAll(index, l); }  } 
/* 301 */     public boolean addAll(FloatList l) { synchronized (this.sync) { return this.list.addAll(l); }  } 
/*     */     public Float get(int i) {
/* 303 */       synchronized (this.sync) { return (Float)this.list.get(i); }  } 
/* 304 */     public void add(int i, Float k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 305 */     public Float set(int index, Float k) { synchronized (this.sync) { return (Float)this.list.set(index, k); }  } 
/* 306 */     public Float remove(int i) { synchronized (this.sync) { return (Float)this.list.remove(i); }  } 
/* 307 */     public int indexOf(Object o) { synchronized (this.sync) { return this.list.indexOf(o); }  } 
/* 308 */     public int lastIndexOf(Object o) { synchronized (this.sync) { return this.list.lastIndexOf(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends AbstractFloatList
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     private final float element;
/*     */ 
/*     */     private Singleton(float element)
/*     */     {
/* 163 */       this.element = element;
/*     */     }
/*     */     public float getFloat(int i) {
/* 166 */       if (i == 0) return this.element; throw new IndexOutOfBoundsException(); } 
/* 167 */     public float removeFloat(int i) { throw new UnsupportedOperationException(); } 
/* 168 */     public boolean contains(float k) { return k == this.element; } 
/*     */     public boolean addAll(Collection<? extends Float> c) {
/* 170 */       throw new UnsupportedOperationException(); } 
/* 171 */     public boolean addAll(int i, Collection<? extends Float> c) { throw new UnsupportedOperationException(); } 
/* 172 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/* 173 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */     public float[] toFloatArray()
/*     */     {
/* 178 */       float[] a = new float[1];
/* 179 */       a[0] = this.element;
/* 180 */       return a;
/*     */     }
/*     */ 
/*     */     public FloatListIterator listIterator() {
/* 184 */       return FloatIterators.singleton(this.element);
/*     */     }
/* 186 */     public FloatListIterator iterator() { return listIterator(); }
/*     */ 
/*     */     public FloatListIterator listIterator(int i) {
/* 189 */       if ((i > 1) || (i < 0)) throw new IndexOutOfBoundsException();
/* 190 */       FloatListIterator l = listIterator();
/* 191 */       if (i == 1) l.next();
/* 192 */       return l;
/*     */     }
/*     */ 
/*     */     public FloatList subList(int from, int to)
/*     */     {
/* 197 */       ensureIndex(from);
/* 198 */       ensureIndex(to);
/* 199 */       if (from > to) throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
/*     */ 
/* 201 */       if ((from != 0) || (to != 1)) return FloatLists.EMPTY_LIST;
/* 202 */       return this;
/*     */     }
/*     */     public int size() {
/* 205 */       return 1; } 
/* 206 */     public void size(int size) { throw new UnsupportedOperationException(); } 
/* 207 */     public void clear() { throw new UnsupportedOperationException(); } 
/*     */     public Object clone() {
/* 209 */       return this;
/*     */     }
/*     */     public boolean rem(float k) {
/* 212 */       throw new UnsupportedOperationException(); } 
/* 213 */     public boolean addAll(FloatCollection c) { throw new UnsupportedOperationException(); } 
/* 214 */     public boolean addAll(int i, FloatCollection c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptyList extends FloatCollections.EmptyCollection
/*     */     implements FloatList, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public void add(int index, float k)
/*     */     {
/*  77 */       throw new UnsupportedOperationException(); } 
/*  78 */     public boolean add(float k) { throw new UnsupportedOperationException(); } 
/*  79 */     public float removeFloat(int i) { throw new UnsupportedOperationException(); } 
/*  80 */     public float set(int index, float k) { throw new UnsupportedOperationException(); } 
/*  81 */     public int indexOf(float k) { return -1; } 
/*  82 */     public int lastIndexOf(float k) { return -1; } 
/*  83 */     public boolean addAll(Collection<? extends Float> c) { throw new UnsupportedOperationException(); } 
/*  84 */     public boolean addAll(int i, Collection<? extends Float> c) { throw new UnsupportedOperationException(); } 
/*  85 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  86 */     public Float get(int i) { throw new IndexOutOfBoundsException(); } 
/*  87 */     public boolean addAll(FloatCollection c) { throw new UnsupportedOperationException(); } 
/*  88 */     public boolean addAll(FloatList c) { throw new UnsupportedOperationException(); } 
/*  89 */     public boolean addAll(int i, FloatCollection c) { throw new UnsupportedOperationException(); } 
/*  90 */     public boolean addAll(int i, FloatList c) { throw new UnsupportedOperationException(); } 
/*  91 */     public void add(int index, Float k) { throw new UnsupportedOperationException(); } 
/*  92 */     public boolean add(Float k) { throw new UnsupportedOperationException(); } 
/*  93 */     public Float set(int index, Float k) { throw new UnsupportedOperationException(); } 
/*  94 */     public float getFloat(int i) { throw new IndexOutOfBoundsException(); } 
/*  95 */     public Float remove(int k) { throw new UnsupportedOperationException(); } 
/*  96 */     public int indexOf(Object k) { return -1; } 
/*  97 */     public int lastIndexOf(Object k) { return -1; }
/*     */ 
/*     */     @Deprecated
/*     */     public FloatIterator floatIterator()
/*     */     {
/* 102 */       return FloatIterators.EMPTY_ITERATOR;
/*     */     }
/* 104 */     public FloatListIterator listIterator() { return FloatIterators.EMPTY_ITERATOR; } 
/*     */     public FloatListIterator iterator() {
/* 106 */       return FloatIterators.EMPTY_ITERATOR;
/*     */     }
/* 108 */     public FloatListIterator listIterator(int i) { if (i == 0) return FloatIterators.EMPTY_ITERATOR; throw new IndexOutOfBoundsException(String.valueOf(i)); } 
/*     */     @Deprecated
/*     */     public FloatListIterator floatListIterator() {
/* 111 */       return listIterator();
/*     */     }
/* 114 */     @Deprecated
/*     */     public FloatListIterator floatListIterator(int i) { return listIterator(i); } 
/*     */     public FloatList subList(int from, int to) {
/* 116 */       if ((from == 0) && (to == 0)) return this; throw new IndexOutOfBoundsException();
/*     */     }
/* 119 */     @Deprecated
/*     */     public FloatList floatSubList(int from, int to) { return subList(from, to); } 
/*     */     public void getElements(int from, float[] a, int offset, int length) {
/* 121 */       if ((from == 0) && (length == 0) && (offset >= 0) && (offset <= a.length)) return; throw new IndexOutOfBoundsException(); } 
/* 122 */     public void removeElements(int from, int to) { throw new UnsupportedOperationException(); } 
/*     */     public void addElements(int index, float[] a, int offset, int length) {
/* 124 */       throw new UnsupportedOperationException(); } 
/* 125 */     public void addElements(int index, float[] a) { throw new UnsupportedOperationException(); } 
/*     */     public void size(int s) {
/* 127 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     public int compareTo(List<? extends Float> o) {
/* 130 */       if (o == this) return 0;
/* 131 */       return o.isEmpty() ? 0 : -1;
/*     */     }
/*     */     private Object readResolve() {
/* 134 */       return FloatLists.EMPTY_LIST; } 
/* 135 */     public Object clone() { return FloatLists.EMPTY_LIST; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatLists
 * JD-Core Version:    0.6.2
 */