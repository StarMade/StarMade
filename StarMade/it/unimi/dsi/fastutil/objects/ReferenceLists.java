/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ReferenceLists
/*     */ {
/* 123 */   public static final EmptyList EMPTY_LIST = new EmptyList();
/*     */ 
/*     */   public static <K> ReferenceList<K> shuffle(ReferenceList<K> l, Random random)
/*     */   {
/*  60 */     for (int i = l.size(); i-- != 0; ) {
/*  61 */       int p = random.nextInt(i + 1);
/*  62 */       Object t = l.get(i);
/*  63 */       l.set(i, l.get(p));
/*  64 */       l.set(p, t);
/*     */     }
/*  66 */     return l;
/*     */   }
/*     */ 
/*     */   public static <K> ReferenceList<K> singleton(K element)
/*     */   {
/* 176 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static <K> ReferenceList<K> synchronize(ReferenceList<K> l)
/*     */   {
/* 220 */     return new SynchronizedList(l);
/*     */   }
/*     */ 
/*     */   public static <K> ReferenceList<K> synchronize(ReferenceList<K> l, Object sync)
/*     */   {
/* 228 */     return new SynchronizedList(l, sync);
/*     */   }
/*     */ 
/*     */   public static <K> ReferenceList<K> unmodifiable(ReferenceList<K> l)
/*     */   {
/* 268 */     return new UnmodifiableList(l);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableList<K> extends ReferenceCollections.UnmodifiableCollection<K>
/*     */     implements ReferenceList<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ReferenceList<K> list;
/*     */ 
/*     */     protected UnmodifiableList(ReferenceList<K> l)
/*     */     {
/* 234 */       super();
/* 235 */       this.list = l;
/*     */     }
/* 237 */     public K get(int i) { return this.list.get(i); } 
/* 238 */     public K set(int i, K k) { throw new UnsupportedOperationException(); } 
/* 239 */     public void add(int i, K k) { throw new UnsupportedOperationException(); } 
/* 240 */     public K remove(int i) { throw new UnsupportedOperationException(); } 
/* 241 */     public int indexOf(Object k) { return this.list.indexOf(k); } 
/* 242 */     public int lastIndexOf(Object k) { return this.list.lastIndexOf(k); } 
/* 243 */     public boolean addAll(int index, Collection<? extends K> c) { throw new UnsupportedOperationException(); } 
/* 244 */     public void getElements(int from, Object[] a, int offset, int length) { this.list.getElements(from, a, offset, length); } 
/* 245 */     public void removeElements(int from, int to) { throw new UnsupportedOperationException(); } 
/* 246 */     public void addElements(int index, K[] a, int offset, int length) { throw new UnsupportedOperationException(); } 
/* 247 */     public void addElements(int index, K[] a) { throw new UnsupportedOperationException(); } 
/* 248 */     public void size(int size) { this.list.size(size); } 
/* 249 */     public ObjectListIterator<K> iterator() { return listIterator(); } 
/* 250 */     public ObjectListIterator<K> listIterator() { return ObjectIterators.unmodifiable(this.list.listIterator()); } 
/* 251 */     public ObjectListIterator<K> listIterator(int i) { return ObjectIterators.unmodifiable(this.list.listIterator(i)); } 
/* 253 */     @Deprecated
/*     */     public ObjectListIterator<K> objectListIterator() { return listIterator(); } 
/* 255 */     @Deprecated
/*     */     public ObjectListIterator<K> objectListIterator(int i) { return listIterator(i); } 
/* 256 */     public ReferenceList<K> subList(int from, int to) { return ReferenceLists.unmodifiable(this.list.subList(from, to)); } 
/* 258 */     @Deprecated
/*     */     public ReferenceList<K> referenceSubList(int from, int to) { return subList(from, to); } 
/* 259 */     public boolean equals(Object o) { return this.collection.equals(o); } 
/* 260 */     public int hashCode() { return this.collection.hashCode(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedList<K> extends ReferenceCollections.SynchronizedCollection<K>
/*     */     implements ReferenceList<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ReferenceList<K> list;
/*     */ 
/*     */     protected SynchronizedList(ReferenceList<K> l, Object sync)
/*     */     {
/* 182 */       super(sync);
/* 183 */       this.list = l;
/*     */     }
/*     */     protected SynchronizedList(ReferenceList<K> l) {
/* 186 */       super();
/* 187 */       this.list = l;
/*     */     }
/* 189 */     public K get(int i) { synchronized (this.sync) { return this.list.get(i); }  } 
/* 190 */     public K set(int i, K k) { synchronized (this.sync) { return this.list.set(i, k); }  } 
/* 191 */     public void add(int i, K k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 192 */     public K remove(int i) { synchronized (this.sync) { return this.list.remove(i); }  } 
/* 193 */     public int indexOf(Object k) { synchronized (this.sync) { return this.list.indexOf(k); }  } 
/* 194 */     public int lastIndexOf(Object k) { synchronized (this.sync) { return this.list.lastIndexOf(k); }  } 
/* 195 */     public boolean addAll(int index, Collection<? extends K> c) { synchronized (this.sync) { return this.list.addAll(index, c); }  } 
/* 196 */     public void getElements(int from, Object[] a, int offset, int length) { synchronized (this.sync) { this.list.getElements(from, a, offset, length); }  } 
/* 197 */     public void removeElements(int from, int to) { synchronized (this.sync) { this.list.removeElements(from, to); }  } 
/* 198 */     public void addElements(int index, K[] a, int offset, int length) { synchronized (this.sync) { this.list.addElements(index, a, offset, length); }  } 
/* 199 */     public void addElements(int index, K[] a) { synchronized (this.sync) { this.list.addElements(index, a); }  } 
/* 200 */     public void size(int size) { synchronized (this.sync) { this.list.size(size); }  } 
/* 201 */     public ObjectListIterator<K> iterator() { return this.list.listIterator(); } 
/* 202 */     public ObjectListIterator<K> listIterator() { return this.list.listIterator(); } 
/* 203 */     public ObjectListIterator<K> listIterator(int i) { return this.list.listIterator(i); } 
/* 205 */     @Deprecated
/*     */     public ObjectListIterator<K> objectListIterator() { return listIterator(); } 
/* 207 */     @Deprecated
/*     */     public ObjectListIterator<K> objectListIterator(int i) { return listIterator(i); } 
/* 208 */     public ReferenceList<K> subList(int from, int to) { synchronized (this.sync) { return ReferenceLists.synchronize(this.list.subList(from, to), this.sync); }  } 
/* 210 */     @Deprecated
/*     */     public ReferenceList<K> referenceSubList(int from, int to) { return subList(from, to); } 
/* 211 */     public boolean equals(Object o) { synchronized (this.sync) { return this.collection.equals(o); }  } 
/* 212 */     public int hashCode() { synchronized (this.sync) { return this.collection.hashCode(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton<K> extends AbstractReferenceList<K>
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     private final K element;
/*     */ 
/*     */     private Singleton(K element)
/*     */     {
/* 133 */       this.element = element;
/*     */     }
/* 135 */     public K get(int i) { if (i == 0) return this.element; throw new IndexOutOfBoundsException(); } 
/* 136 */     public K remove(int i) { throw new UnsupportedOperationException(); } 
/* 137 */     public boolean contains(Object k) { return k == this.element; } 
/* 138 */     public boolean addAll(Collection<? extends K> c) { throw new UnsupportedOperationException(); } 
/* 139 */     public boolean addAll(int i, Collection<? extends K> c) { throw new UnsupportedOperationException(); } 
/* 140 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/* 141 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public Object[] toArray() {
/* 144 */       Object[] a = new Object[1];
/* 145 */       a[0] = this.element;
/* 146 */       return a;
/*     */     }
/*     */     public ObjectListIterator<K> listIterator() {
/* 149 */       return ObjectIterators.singleton(this.element); } 
/* 150 */     public ObjectListIterator<K> iterator() { return listIterator(); } 
/*     */     public ObjectListIterator<K> listIterator(int i) {
/* 152 */       if ((i > 1) || (i < 0)) throw new IndexOutOfBoundsException();
/* 153 */       ObjectListIterator l = listIterator();
/* 154 */       if (i == 1) l.next();
/* 155 */       return l;
/*     */     }
/*     */ 
/*     */     public ReferenceList<K> subList(int from, int to) {
/* 159 */       ensureIndex(from);
/* 160 */       ensureIndex(to);
/* 161 */       if (from > to) throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 162 */       if ((from != 0) || (to != 1)) return ReferenceLists.EMPTY_LIST;
/* 163 */       return this;
/*     */     }
/* 165 */     public int size() { return 1; } 
/* 166 */     public void size(int size) { throw new UnsupportedOperationException(); } 
/* 167 */     public void clear() { throw new UnsupportedOperationException(); } 
/* 168 */     public Object clone() { return this; } 
/* 169 */     public boolean remove(Object k) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptyList<K> extends ReferenceCollections.EmptyCollection<K>
/*     */     implements ReferenceList<K>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public void add(int index, K k)
/*     */     {
/*  76 */       throw new UnsupportedOperationException(); } 
/*  77 */     public boolean add(K k) { throw new UnsupportedOperationException(); } 
/*  78 */     public K remove(int i) { throw new UnsupportedOperationException(); } 
/*  79 */     public K set(int index, K k) { throw new UnsupportedOperationException(); } 
/*  80 */     public int indexOf(Object k) { return -1; } 
/*  81 */     public int lastIndexOf(Object k) { return -1; } 
/*  82 */     public boolean addAll(Collection<? extends K> c) { throw new UnsupportedOperationException(); } 
/*  83 */     public boolean addAll(int i, Collection<? extends K> c) { throw new UnsupportedOperationException(); } 
/*  84 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  85 */     public K get(int i) { throw new IndexOutOfBoundsException(); }
/*     */ 
/*     */     @Deprecated
/*     */     public ObjectIterator<K> objectIterator()
/*     */     {
/*  90 */       return ObjectIterators.EMPTY_ITERATOR;
/*     */     }
/*  92 */     public ObjectListIterator<K> listIterator() { return ObjectIterators.EMPTY_ITERATOR; } 
/*     */     public ObjectListIterator<K> iterator() {
/*  94 */       return ObjectIterators.EMPTY_ITERATOR;
/*     */     }
/*  96 */     public ObjectListIterator<K> listIterator(int i) { if (i == 0) return ObjectIterators.EMPTY_ITERATOR; throw new IndexOutOfBoundsException(String.valueOf(i)); } 
/*  98 */     @Deprecated
/*     */     public ObjectListIterator<K> objectListIterator() { return listIterator(); } 
/* 100 */     @Deprecated
/*     */     public ObjectListIterator<K> objectListIterator(int i) { return listIterator(i); } 
/* 101 */     public ReferenceList<K> subList(int from, int to) { if ((from == 0) && (to == 0)) return this; throw new IndexOutOfBoundsException(); } 
/* 103 */     @Deprecated
/*     */     public ReferenceList<K> referenceSubList(int from, int to) { return subList(from, to); } 
/* 104 */     public void getElements(int from, Object[] a, int offset, int length) { if ((from == 0) && (length == 0) && (offset >= 0) && (offset <= a.length)) return; throw new IndexOutOfBoundsException(); } 
/* 105 */     public void removeElements(int from, int to) { throw new UnsupportedOperationException(); } 
/* 106 */     public void addElements(int index, K[] a, int offset, int length) { throw new UnsupportedOperationException(); } 
/* 107 */     public void addElements(int index, K[] a) { throw new UnsupportedOperationException(); } 
/* 108 */     public void size(int s) { throw new UnsupportedOperationException(); } 
/*     */     public int compareTo(List<? extends K> o) {
/* 110 */       if (o == this) return 0;
/* 111 */       return o.isEmpty() ? 0 : -1;
/*     */     }
/* 113 */     private Object readResolve() { return ReferenceLists.EMPTY_LIST; } 
/* 114 */     public Object clone() { return ReferenceLists.EMPTY_LIST; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceLists
 * JD-Core Version:    0.6.2
 */