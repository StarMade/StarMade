/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.BigList;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ObjectBigLists
/*     */ {
/* 116 */   public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
/*     */ 
/*     */   public static <K> ObjectBigList<K> shuffle(ObjectBigList<K> l, Random random)
/*     */   {
/*  62 */     for (long i = l.size64(); i-- != 0L; ) {
/*  63 */       long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/*  64 */       Object t = l.get(i);
/*  65 */       l.set(i, l.get(p));
/*  66 */       l.set(p, t);
/*     */     }
/*  68 */     return l;
/*     */   }
/*     */ 
/*     */   public static <K> ObjectBigList<K> singleton(K element)
/*     */   {
/* 171 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectBigList<K> synchronize(ObjectBigList<K> l)
/*     */   {
/* 211 */     return new SynchronizedBigList(l);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectBigList<K> synchronize(ObjectBigList<K> l, Object sync)
/*     */   {
/* 219 */     return new SynchronizedBigList(l, sync);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectBigList<K> unmodifiable(ObjectBigList<K> l)
/*     */   {
/* 255 */     return new UnmodifiableBigList(l);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectBigList<K> asBigList(ObjectList<K> list)
/*     */   {
/* 307 */     return new ListBigList(list);
/*     */   }
/*     */ 
/*     */   public static class ListBigList<K> extends AbstractObjectBigList<K>
/*     */     implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     private final ObjectList<K> list;
/*     */ 
/*     */     protected ListBigList(ObjectList<K> list)
/*     */     {
/* 261 */       this.list = list;
/*     */     }
/*     */     private int intIndex(long index) {
/* 264 */       if (index >= 2147483647L) throw new IndexOutOfBoundsException("This big list is restricted to 32-bit indices");
/* 265 */       return (int)index;
/*     */     }
/* 267 */     public long size64() { return this.list.size(); } 
/* 269 */     @Deprecated
/*     */     public int size() { return this.list.size(); } 
/* 270 */     public void size(long size) { this.list.size(intIndex(size)); } 
/* 271 */     public ObjectBigListIterator<K> iterator() { return ObjectBigListIterators.asBigListIterator(this.list.iterator()); } 
/* 272 */     public ObjectBigListIterator<K> listIterator() { return ObjectBigListIterators.asBigListIterator(this.list.listIterator()); } 
/* 273 */     public boolean addAll(long index, Collection<? extends K> c) { return this.list.addAll(intIndex(index), c); } 
/* 274 */     public ObjectBigListIterator<K> listIterator(long index) { return ObjectBigListIterators.asBigListIterator(this.list.listIterator(intIndex(index))); } 
/* 275 */     public ObjectBigList<K> subList(long from, long to) { return new ListBigList(this.list.subList(intIndex(from), intIndex(to))); } 
/* 276 */     public boolean contains(Object key) { return this.list.contains(key); } 
/* 277 */     public Object[] toArray() { return this.list.toArray(); } 
/* 278 */     public void removeElements(long from, long to) { this.list.removeElements(intIndex(from), intIndex(to)); } 
/* 279 */     public void add(long index, K key) { this.list.add(intIndex(index), key); } 
/* 280 */     public boolean addAll(long index, ObjectCollection<K> c) { return this.list.addAll(intIndex(index), c); } 
/* 281 */     public boolean addAll(long index, ObjectBigList<K> c) { return this.list.addAll(intIndex(index), c); } 
/* 282 */     public boolean add(K key) { return this.list.add(key); } 
/* 283 */     public boolean addAll(ObjectBigList<K> c) { return this.list.addAll(c); } 
/* 284 */     public K get(long index) { return this.list.get(intIndex(index)); } 
/* 285 */     public long indexOf(Object k) { return this.list.indexOf(k); } 
/* 286 */     public long lastIndexOf(Object k) { return this.list.lastIndexOf(k); } 
/* 287 */     public K remove(long index) { return this.list.remove(intIndex(index)); } 
/* 288 */     public K set(long index, K k) { return this.list.set(intIndex(index), k); } 
/* 289 */     public boolean addAll(ObjectCollection<K> c) { return this.list.addAll(c); } 
/* 290 */     public boolean containsAll(ObjectCollection<K> c) { return this.list.containsAll(c); } 
/* 291 */     public boolean removeAll(ObjectCollection<K> c) { return this.list.removeAll(c); } 
/* 292 */     public boolean retainAll(ObjectCollection<K> c) { return this.list.retainAll(c); } 
/* 293 */     public boolean isEmpty() { return this.list.isEmpty(); } 
/* 294 */     public <T> T[] toArray(T[] a) { return this.list.toArray(a); } 
/* 295 */     public boolean containsAll(Collection<?> c) { return this.list.containsAll(c); } 
/* 296 */     public boolean addAll(Collection<? extends K> c) { return this.list.addAll(c); } 
/* 297 */     public boolean removeAll(Collection<?> c) { return this.list.removeAll(c); } 
/* 298 */     public boolean retainAll(Collection<?> c) { return this.list.retainAll(c); } 
/* 299 */     public void clear() { this.list.clear(); } 
/* 300 */     public int hashCode() { return this.list.hashCode(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableBigList<K> extends ObjectCollections.UnmodifiableCollection<K>
/*     */     implements ObjectBigList<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ObjectBigList<K> list;
/*     */ 
/*     */     protected UnmodifiableBigList(ObjectBigList<K> l)
/*     */     {
/* 225 */       super();
/* 226 */       this.list = l;
/*     */     }
/* 228 */     public K get(long i) { return this.list.get(i); } 
/* 229 */     public K set(long i, K k) { throw new UnsupportedOperationException(); } 
/* 230 */     public void add(long i, K k) { throw new UnsupportedOperationException(); } 
/* 231 */     public K remove(long i) { throw new UnsupportedOperationException(); } 
/* 232 */     public long indexOf(Object k) { return this.list.indexOf(k); } 
/* 233 */     public long lastIndexOf(Object k) { return this.list.lastIndexOf(k); } 
/* 234 */     public boolean addAll(long index, Collection<? extends K> c) { throw new UnsupportedOperationException(); } 
/* 235 */     public void getElements(long from, Object[][] a, long offset, long length) { this.list.getElements(from, a, offset, length); } 
/* 236 */     public void removeElements(long from, long to) { throw new UnsupportedOperationException(); } 
/* 237 */     public void addElements(long index, K[][] a, long offset, long length) { throw new UnsupportedOperationException(); } 
/* 238 */     public void addElements(long index, K[][] a) { throw new UnsupportedOperationException(); } 
/* 239 */     public void size(long size) { this.list.size(size); } 
/* 240 */     public long size64() { return this.list.size64(); } 
/* 241 */     public ObjectBigListIterator<K> iterator() { return listIterator(); } 
/* 242 */     public ObjectBigListIterator<K> listIterator() { return ObjectBigListIterators.unmodifiable(this.list.listIterator()); } 
/* 243 */     public ObjectBigListIterator<K> listIterator(long i) { return ObjectBigListIterators.unmodifiable(this.list.listIterator(i)); } 
/* 244 */     public ObjectBigList<K> subList(long from, long to) { return ObjectBigLists.unmodifiable(this.list.subList(from, to)); } 
/* 245 */     public boolean equals(Object o) { return this.list.equals(o); } 
/* 246 */     public int hashCode() { return this.list.hashCode(); } 
/* 247 */     public int compareTo(BigList<? extends K> o) { return this.list.compareTo(o); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedBigList<K> extends ObjectCollections.SynchronizedCollection<K>
/*     */     implements ObjectBigList<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ObjectBigList<K> list;
/*     */ 
/*     */     protected SynchronizedBigList(ObjectBigList<K> l, Object sync)
/*     */     {
/* 177 */       super(sync);
/* 178 */       this.list = l;
/*     */     }
/*     */     protected SynchronizedBigList(ObjectBigList<K> l) {
/* 181 */       super();
/* 182 */       this.list = l;
/*     */     }
/* 184 */     public K get(long i) { synchronized (this.sync) { return this.list.get(i); }  } 
/* 185 */     public K set(long i, K k) { synchronized (this.sync) { return this.list.set(i, k); }  } 
/* 186 */     public void add(long i, K k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 187 */     public K remove(long i) { synchronized (this.sync) { return this.list.remove(i); }  } 
/* 188 */     public long indexOf(Object k) { synchronized (this.sync) { return this.list.indexOf(k); }  } 
/* 189 */     public long lastIndexOf(Object k) { synchronized (this.sync) { return this.list.lastIndexOf(k); }  } 
/* 190 */     public boolean addAll(long index, Collection<? extends K> c) { synchronized (this.sync) { return this.list.addAll(index, c); }  } 
/* 191 */     public void getElements(long from, Object[][] a, long offset, long length) { synchronized (this.sync) { this.list.getElements(from, a, offset, length); }  } 
/* 192 */     public void removeElements(long from, long to) { synchronized (this.sync) { this.list.removeElements(from, to); }  } 
/* 193 */     public void addElements(long index, K[][] a, long offset, long length) { synchronized (this.sync) { this.list.addElements(index, a, offset, length); }  } 
/* 194 */     public void addElements(long index, K[][] a) { synchronized (this.sync) { this.list.addElements(index, a); }  } 
/* 195 */     public void size(long size) { synchronized (this.sync) { this.list.size(size); }  } 
/* 196 */     public long size64() { synchronized (this.sync) { return this.list.size64(); }  } 
/* 197 */     public ObjectBigListIterator<K> iterator() { return this.list.listIterator(); } 
/* 198 */     public ObjectBigListIterator<K> listIterator() { return this.list.listIterator(); } 
/* 199 */     public ObjectBigListIterator<K> listIterator(long i) { return this.list.listIterator(i); } 
/* 200 */     public ObjectBigList<K> subList(long from, long to) { synchronized (this.sync) { return ObjectBigLists.synchronize(this.list.subList(from, to), this.sync); }  } 
/* 201 */     public boolean equals(Object o) { synchronized (this.sync) { return this.list.equals(o); }  } 
/* 202 */     public int hashCode() { synchronized (this.sync) { return this.list.hashCode(); }  } 
/* 203 */     public int compareTo(BigList<? extends K> o) { synchronized (this.sync) { return this.list.compareTo(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton<K> extends AbstractObjectBigList<K>
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     private final K element;
/*     */ 
/*     */     private Singleton(K element)
/*     */     {
/* 126 */       this.element = element;
/*     */     }
/* 128 */     public K get(long i) { if (i == 0L) return this.element; throw new IndexOutOfBoundsException(); } 
/* 129 */     public K remove(long i) { throw new UnsupportedOperationException(); } 
/* 130 */     public boolean contains(Object k) { return k == null ? false : this.element == null ? true : k.equals(this.element); } 
/* 131 */     public boolean addAll(Collection<? extends K> c) { throw new UnsupportedOperationException(); } 
/* 132 */     public boolean addAll(long i, Collection<? extends K> c) { throw new UnsupportedOperationException(); } 
/* 133 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/* 134 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public Object[] toArray() {
/* 137 */       Object[] a = new Object[1];
/* 138 */       a[0] = this.element;
/* 139 */       return a;
/*     */     }
/*     */     public ObjectBigListIterator<K> listIterator() {
/* 142 */       return ObjectBigListIterators.singleton(this.element); } 
/* 143 */     public ObjectBigListIterator<K> iterator() { return listIterator(); } 
/*     */     public ObjectBigListIterator<K> listIterator(long i) {
/* 145 */       if ((i > 1L) || (i < 0L)) throw new IndexOutOfBoundsException();
/* 146 */       ObjectBigListIterator l = listIterator();
/* 147 */       if (i == 1L) l.next();
/* 148 */       return l;
/*     */     }
/*     */ 
/*     */     public ObjectBigList<K> subList(long from, long to) {
/* 152 */       ensureIndex(from);
/* 153 */       ensureIndex(to);
/* 154 */       if (from > to) throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 155 */       if ((from != 0L) || (to != 1L)) return ObjectBigLists.EMPTY_BIG_LIST;
/* 156 */       return this;
/*     */     }
/* 159 */     @Deprecated
/*     */     public int size() { return 1; } 
/* 160 */     public long size64() { return 1L; } 
/* 161 */     public void size(long size) { throw new UnsupportedOperationException(); } 
/* 162 */     public void clear() { throw new UnsupportedOperationException(); } 
/* 163 */     public Object clone() { return this; } 
/* 164 */     public boolean remove(Object k) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptyBigList<K> extends ObjectCollections.EmptyCollection<K>
/*     */     implements ObjectBigList<K>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public void add(long index, K k)
/*     */     {
/*  78 */       throw new UnsupportedOperationException(); } 
/*  79 */     public boolean add(K k) { throw new UnsupportedOperationException(); } 
/*  80 */     public K remove(long i) { throw new UnsupportedOperationException(); } 
/*  81 */     public K set(long index, K k) { throw new UnsupportedOperationException(); } 
/*  82 */     public long indexOf(Object k) { return -1L; } 
/*  83 */     public long lastIndexOf(Object k) { return -1L; } 
/*  84 */     public boolean addAll(Collection<? extends K> c) { throw new UnsupportedOperationException(); } 
/*  85 */     public boolean addAll(long i, Collection<? extends K> c) { throw new UnsupportedOperationException(); } 
/*  86 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  87 */     public K get(long i) { throw new IndexOutOfBoundsException(); } 
/*  88 */     public boolean remove(Object k) { throw new UnsupportedOperationException(); } 
/*     */     public ObjectBigListIterator<K> listIterator() {
/*  90 */       return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR;
/*     */     }
/*  92 */     public ObjectBigListIterator<K> iterator() { return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR; } 
/*     */     public ObjectBigListIterator<K> listIterator(long i) {
/*  94 */       if (i == 0L) return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR; throw new IndexOutOfBoundsException(String.valueOf(i)); } 
/*  95 */     public ObjectBigList<K> subList(long from, long to) { if ((from == 0L) && (to == 0L)) return this; throw new IndexOutOfBoundsException(); } 
/*  96 */     public void getElements(long from, Object[][] a, long offset, long length) { ObjectBigArrays.ensureOffsetLength(a, offset, length); if (from != 0L) throw new IndexOutOfBoundsException();  } 
/*  97 */     public void removeElements(long from, long to) { throw new UnsupportedOperationException(); } 
/*  98 */     public void addElements(long index, K[][] a, long offset, long length) { throw new UnsupportedOperationException(); } 
/*  99 */     public void addElements(long index, K[][] a) { throw new UnsupportedOperationException(); } 
/* 100 */     public void size(long s) { throw new UnsupportedOperationException(); } 
/* 101 */     public long size64() { return 0L; } 
/*     */     public int compareTo(BigList<? extends K> o) {
/* 103 */       if (o == this) return 0;
/* 104 */       return o.isEmpty() ? 0 : -1;
/*     */     }
/* 106 */     private Object readResolve() { return ObjectBigLists.EMPTY_BIG_LIST; } 
/* 107 */     public Object clone() { return ObjectBigLists.EMPTY_BIG_LIST; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectBigLists
 * JD-Core Version:    0.6.2
 */