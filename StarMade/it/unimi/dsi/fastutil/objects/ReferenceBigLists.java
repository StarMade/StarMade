/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.BigList;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ReferenceBigLists
/*     */ {
/* 116 */   public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
/*     */ 
/*     */   public static <K> ReferenceBigList<K> shuffle(ReferenceBigList<K> l, Random random)
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
/*     */   public static <K> ReferenceBigList<K> singleton(K element)
/*     */   {
/* 171 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static <K> ReferenceBigList<K> synchronize(ReferenceBigList<K> l)
/*     */   {
/* 210 */     return new SynchronizedBigList(l);
/*     */   }
/*     */ 
/*     */   public static <K> ReferenceBigList<K> synchronize(ReferenceBigList<K> l, Object sync)
/*     */   {
/* 218 */     return new SynchronizedBigList(l, sync);
/*     */   }
/*     */ 
/*     */   public static <K> ReferenceBigList<K> unmodifiable(ReferenceBigList<K> l)
/*     */   {
/* 253 */     return new UnmodifiableBigList(l);
/*     */   }
/*     */ 
/*     */   public static <K> ReferenceBigList<K> asBigList(ReferenceList<K> list)
/*     */   {
/* 305 */     return new ListBigList(list);
/*     */   }
/*     */ 
/*     */   public static class ListBigList<K> extends AbstractReferenceBigList<K>
/*     */     implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     private final ReferenceList<K> list;
/*     */ 
/*     */     protected ListBigList(ReferenceList<K> list)
/*     */     {
/* 259 */       this.list = list;
/*     */     }
/*     */     private int intIndex(long index) {
/* 262 */       if (index >= 2147483647L) throw new IndexOutOfBoundsException("This big list is restricted to 32-bit indices");
/* 263 */       return (int)index;
/*     */     }
/* 265 */     public long size64() { return this.list.size(); } 
/* 267 */     @Deprecated
/*     */     public int size() { return this.list.size(); } 
/* 268 */     public void size(long size) { this.list.size(intIndex(size)); } 
/* 269 */     public ObjectBigListIterator<K> iterator() { return ObjectBigListIterators.asBigListIterator(this.list.iterator()); } 
/* 270 */     public ObjectBigListIterator<K> listIterator() { return ObjectBigListIterators.asBigListIterator(this.list.listIterator()); } 
/* 271 */     public boolean addAll(long index, Collection<? extends K> c) { return this.list.addAll(intIndex(index), c); } 
/* 272 */     public ObjectBigListIterator<K> listIterator(long index) { return ObjectBigListIterators.asBigListIterator(this.list.listIterator(intIndex(index))); } 
/* 273 */     public ReferenceBigList<K> subList(long from, long to) { return new ListBigList(this.list.subList(intIndex(from), intIndex(to))); } 
/* 274 */     public boolean contains(Object key) { return this.list.contains(key); } 
/* 275 */     public Object[] toArray() { return this.list.toArray(); } 
/* 276 */     public void removeElements(long from, long to) { this.list.removeElements(intIndex(from), intIndex(to)); } 
/* 277 */     public void add(long index, K key) { this.list.add(intIndex(index), key); } 
/* 278 */     public boolean addAll(long index, ReferenceCollection<K> c) { return this.list.addAll(intIndex(index), c); } 
/* 279 */     public boolean addAll(long index, ReferenceBigList<K> c) { return this.list.addAll(intIndex(index), c); } 
/* 280 */     public boolean add(K key) { return this.list.add(key); } 
/* 281 */     public boolean addAll(ReferenceBigList<K> c) { return this.list.addAll(c); } 
/* 282 */     public K get(long index) { return this.list.get(intIndex(index)); } 
/* 283 */     public long indexOf(Object k) { return this.list.indexOf(k); } 
/* 284 */     public long lastIndexOf(Object k) { return this.list.lastIndexOf(k); } 
/* 285 */     public K remove(long index) { return this.list.remove(intIndex(index)); } 
/* 286 */     public K set(long index, K k) { return this.list.set(intIndex(index), k); } 
/* 287 */     public boolean addAll(ReferenceCollection<K> c) { return this.list.addAll(c); } 
/* 288 */     public boolean containsAll(ReferenceCollection<K> c) { return this.list.containsAll(c); } 
/* 289 */     public boolean removeAll(ReferenceCollection<K> c) { return this.list.removeAll(c); } 
/* 290 */     public boolean retainAll(ReferenceCollection<K> c) { return this.list.retainAll(c); } 
/* 291 */     public boolean isEmpty() { return this.list.isEmpty(); } 
/* 292 */     public <T> T[] toArray(T[] a) { return this.list.toArray(a); } 
/* 293 */     public boolean containsAll(Collection<?> c) { return this.list.containsAll(c); } 
/* 294 */     public boolean addAll(Collection<? extends K> c) { return this.list.addAll(c); } 
/* 295 */     public boolean removeAll(Collection<?> c) { return this.list.removeAll(c); } 
/* 296 */     public boolean retainAll(Collection<?> c) { return this.list.retainAll(c); } 
/* 297 */     public void clear() { this.list.clear(); } 
/* 298 */     public int hashCode() { return this.list.hashCode(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableBigList<K> extends ReferenceCollections.UnmodifiableCollection<K>
/*     */     implements ReferenceBigList<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ReferenceBigList<K> list;
/*     */ 
/*     */     protected UnmodifiableBigList(ReferenceBigList<K> l)
/*     */     {
/* 224 */       super();
/* 225 */       this.list = l;
/*     */     }
/* 227 */     public K get(long i) { return this.list.get(i); } 
/* 228 */     public K set(long i, K k) { throw new UnsupportedOperationException(); } 
/* 229 */     public void add(long i, K k) { throw new UnsupportedOperationException(); } 
/* 230 */     public K remove(long i) { throw new UnsupportedOperationException(); } 
/* 231 */     public long indexOf(Object k) { return this.list.indexOf(k); } 
/* 232 */     public long lastIndexOf(Object k) { return this.list.lastIndexOf(k); } 
/* 233 */     public boolean addAll(long index, Collection<? extends K> c) { throw new UnsupportedOperationException(); } 
/* 234 */     public void getElements(long from, Object[][] a, long offset, long length) { this.list.getElements(from, a, offset, length); } 
/* 235 */     public void removeElements(long from, long to) { throw new UnsupportedOperationException(); } 
/* 236 */     public void addElements(long index, K[][] a, long offset, long length) { throw new UnsupportedOperationException(); } 
/* 237 */     public void addElements(long index, K[][] a) { throw new UnsupportedOperationException(); } 
/* 238 */     public void size(long size) { this.list.size(size); } 
/* 239 */     public long size64() { return this.list.size64(); } 
/* 240 */     public ObjectBigListIterator<K> iterator() { return listIterator(); } 
/* 241 */     public ObjectBigListIterator<K> listIterator() { return ObjectBigListIterators.unmodifiable(this.list.listIterator()); } 
/* 242 */     public ObjectBigListIterator<K> listIterator(long i) { return ObjectBigListIterators.unmodifiable(this.list.listIterator(i)); } 
/* 243 */     public ReferenceBigList<K> subList(long from, long to) { return ReferenceBigLists.unmodifiable(this.list.subList(from, to)); } 
/* 244 */     public boolean equals(Object o) { return this.list.equals(o); } 
/* 245 */     public int hashCode() { return this.list.hashCode(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedBigList<K> extends ReferenceCollections.SynchronizedCollection<K>
/*     */     implements ReferenceBigList<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ReferenceBigList<K> list;
/*     */ 
/*     */     protected SynchronizedBigList(ReferenceBigList<K> l, Object sync)
/*     */     {
/* 177 */       super(sync);
/* 178 */       this.list = l;
/*     */     }
/*     */     protected SynchronizedBigList(ReferenceBigList<K> l) {
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
/* 200 */     public ReferenceBigList<K> subList(long from, long to) { synchronized (this.sync) { return ReferenceBigLists.synchronize(this.list.subList(from, to), this.sync); }  } 
/* 201 */     public boolean equals(Object o) { synchronized (this.sync) { return this.list.equals(o); }  } 
/* 202 */     public int hashCode() { synchronized (this.sync) { return this.list.hashCode(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton<K> extends AbstractReferenceBigList<K>
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
/* 130 */     public boolean contains(Object k) { return k == this.element; } 
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
/*     */     public ReferenceBigList<K> subList(long from, long to) {
/* 152 */       ensureIndex(from);
/* 153 */       ensureIndex(to);
/* 154 */       if (from > to) throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 155 */       if ((from != 0L) || (to != 1L)) return ReferenceBigLists.EMPTY_BIG_LIST;
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
/*     */   public static class EmptyBigList<K> extends ReferenceCollections.EmptyCollection<K>
/*     */     implements ReferenceBigList<K>, Serializable, Cloneable
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
/*  95 */     public ReferenceBigList<K> subList(long from, long to) { if ((from == 0L) && (to == 0L)) return this; throw new IndexOutOfBoundsException(); } 
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
/* 106 */     private Object readResolve() { return ReferenceBigLists.EMPTY_BIG_LIST; } 
/* 107 */     public Object clone() { return ReferenceBigLists.EMPTY_BIG_LIST; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceBigLists
 * JD-Core Version:    0.6.2
 */