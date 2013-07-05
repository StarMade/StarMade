/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ByteLists
/*     */ {
/* 146 */   public static final EmptyList EMPTY_LIST = new EmptyList();
/*     */ 
/*     */   public static ByteList shuffle(ByteList l, Random random)
/*     */   {
/*  61 */     for (int i = l.size(); i-- != 0; ) {
/*  62 */       int p = random.nextInt(i + 1);
/*  63 */       byte t = l.getByte(i);
/*  64 */       l.set(i, l.getByte(p));
/*  65 */       l.set(p, t);
/*     */     }
/*  67 */     return l;
/*     */   }
/*     */ 
/*     */   public static ByteList singleton(byte element)
/*     */   {
/* 227 */     return new Singleton(element, null);
/*     */   }
/*     */ 
/*     */   public static ByteList singleton(Object element)
/*     */   {
/* 237 */     return new Singleton(((Byte)element).byteValue(), null);
/*     */   }
/*     */ 
/*     */   public static ByteList synchronize(ByteList l)
/*     */   {
/* 319 */     return new SynchronizedList(l);
/*     */   }
/*     */ 
/*     */   public static ByteList synchronize(ByteList l, Object sync)
/*     */   {
/* 329 */     return new SynchronizedList(l, sync);
/*     */   }
/*     */ 
/*     */   public static ByteList unmodifiable(ByteList l)
/*     */   {
/* 404 */     return new UnmodifiableList(l);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableList extends ByteCollections.UnmodifiableCollection
/*     */     implements ByteList, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ByteList list;
/*     */ 
/*     */     protected UnmodifiableList(ByteList l)
/*     */     {
/* 342 */       super();
/* 343 */       this.list = l;
/*     */     }
/*     */     public byte getByte(int i) {
/* 346 */       return this.list.getByte(i); } 
/* 347 */     public byte set(int i, byte k) { throw new UnsupportedOperationException(); } 
/* 348 */     public void add(int i, byte k) { throw new UnsupportedOperationException(); } 
/* 349 */     public byte removeByte(int i) { throw new UnsupportedOperationException(); } 
/*     */     public int indexOf(byte k) {
/* 351 */       return this.list.indexOf(k); } 
/* 352 */     public int lastIndexOf(byte k) { return this.list.lastIndexOf(k); } 
/*     */     public boolean addAll(int index, Collection<? extends Byte> c) {
/* 354 */       throw new UnsupportedOperationException();
/*     */     }
/* 356 */     public void getElements(int from, byte[] a, int offset, int length) { this.list.getElements(from, a, offset, length); } 
/* 357 */     public void removeElements(int from, int to) { throw new UnsupportedOperationException(); } 
/* 358 */     public void addElements(int index, byte[] a, int offset, int length) { throw new UnsupportedOperationException(); } 
/* 359 */     public void addElements(int index, byte[] a) { throw new UnsupportedOperationException(); } 
/* 360 */     public void size(int size) { this.list.size(size); } 
/*     */     public ByteListIterator iterator() {
/* 362 */       return listIterator(); } 
/* 363 */     public ByteListIterator listIterator() { return ByteIterators.unmodifiable(this.list.listIterator()); } 
/* 364 */     public ByteListIterator listIterator(int i) { return ByteIterators.unmodifiable(this.list.listIterator(i)); } 
/*     */     @Deprecated
/*     */     public ByteListIterator byteListIterator() {
/* 367 */       return listIterator();
/*     */     }
/* 370 */     @Deprecated
/*     */     public ByteListIterator byteListIterator(int i) { return listIterator(i); } 
/*     */     public ByteList subList(int from, int to) {
/* 372 */       return ByteLists.unmodifiable(this.list.subList(from, to));
/*     */     }
/* 375 */     @Deprecated
/*     */     public ByteList byteSubList(int from, int to) { return subList(from, to); } 
/*     */     public boolean equals(Object o) {
/* 377 */       return this.collection.equals(o); } 
/* 378 */     public int hashCode() { return this.collection.hashCode(); }
/*     */ 
/*     */     public int compareTo(List<? extends Byte> o) {
/* 381 */       return this.list.compareTo(o);
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, ByteCollection c) {
/* 385 */       throw new UnsupportedOperationException(); } 
/* 386 */     public boolean addAll(ByteList l) { throw new UnsupportedOperationException(); } 
/* 387 */     public boolean addAll(int index, ByteList l) { throw new UnsupportedOperationException(); } 
/* 388 */     public Byte get(int i) { return (Byte)this.list.get(i); } 
/* 389 */     public void add(int i, Byte k) { throw new UnsupportedOperationException(); } 
/* 390 */     public Byte set(int index, Byte k) { throw new UnsupportedOperationException(); } 
/* 391 */     public Byte remove(int i) { throw new UnsupportedOperationException(); } 
/* 392 */     public int indexOf(Object o) { return this.list.indexOf(o); } 
/* 393 */     public int lastIndexOf(Object o) { return this.list.lastIndexOf(o); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedList extends ByteCollections.SynchronizedCollection
/*     */     implements ByteList, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ByteList list;
/*     */ 
/*     */     protected SynchronizedList(ByteList l, Object sync)
/*     */     {
/* 251 */       super(sync);
/* 252 */       this.list = l;
/*     */     }
/*     */ 
/*     */     protected SynchronizedList(ByteList l) {
/* 256 */       super();
/* 257 */       this.list = l;
/*     */     }
/*     */     public byte getByte(int i) {
/* 260 */       synchronized (this.sync) { return this.list.getByte(i); }  } 
/* 261 */     public byte set(int i, byte k) { synchronized (this.sync) { return this.list.set(i, k); }  } 
/* 262 */     public void add(int i, byte k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 263 */     public byte removeByte(int i) { synchronized (this.sync) { return this.list.removeByte(i); }  } 
/*     */     public int indexOf(byte k) {
/* 265 */       synchronized (this.sync) { return this.list.indexOf(k); }  } 
/* 266 */     public int lastIndexOf(byte k) { synchronized (this.sync) { return this.list.lastIndexOf(k); }  } 
/*     */     public boolean addAll(int index, Collection<? extends Byte> c) {
/* 268 */       synchronized (this.sync) { return this.list.addAll(index, c); } 
/*     */     }
/* 270 */     public void getElements(int from, byte[] a, int offset, int length) { synchronized (this.sync) { this.list.getElements(from, a, offset, length); }  } 
/* 271 */     public void removeElements(int from, int to) { synchronized (this.sync) { this.list.removeElements(from, to); }  } 
/* 272 */     public void addElements(int index, byte[] a, int offset, int length) { synchronized (this.sync) { this.list.addElements(index, a, offset, length); }  } 
/* 273 */     public void addElements(int index, byte[] a) { synchronized (this.sync) { this.list.addElements(index, a); }  } 
/* 274 */     public void size(int size) { synchronized (this.sync) { this.list.size(size); }  } 
/*     */     public ByteListIterator iterator() {
/* 276 */       return this.list.listIterator(); } 
/* 277 */     public ByteListIterator listIterator() { return this.list.listIterator(); } 
/* 278 */     public ByteListIterator listIterator(int i) { return this.list.listIterator(i); } 
/*     */     @Deprecated
/*     */     public ByteListIterator byteListIterator() {
/* 281 */       return listIterator();
/*     */     }
/* 284 */     @Deprecated
/*     */     public ByteListIterator byteListIterator(int i) { return listIterator(i); } 
/*     */     public ByteList subList(int from, int to) {
/* 286 */       synchronized (this.sync) { return ByteLists.synchronize(this.list.subList(from, to), this.sync); } 
/*     */     }
/* 289 */     @Deprecated
/*     */     public ByteList byteSubList(int from, int to) { return subList(from, to); } 
/*     */     public boolean equals(Object o) {
/* 291 */       synchronized (this.sync) { return this.collection.equals(o); }  } 
/* 292 */     public int hashCode() { synchronized (this.sync) { return this.collection.hashCode(); } }
/*     */ 
/*     */     public int compareTo(List<? extends Byte> o) {
/* 295 */       synchronized (this.sync) { return this.list.compareTo(o); }
/*     */     }
/*     */ 
/*     */     public boolean addAll(int index, ByteCollection c) {
/* 299 */       synchronized (this.sync) { return this.list.addAll(index, c); }  } 
/* 300 */     public boolean addAll(int index, ByteList l) { synchronized (this.sync) { return this.list.addAll(index, l); }  } 
/* 301 */     public boolean addAll(ByteList l) { synchronized (this.sync) { return this.list.addAll(l); }  } 
/*     */     public Byte get(int i) {
/* 303 */       synchronized (this.sync) { return (Byte)this.list.get(i); }  } 
/* 304 */     public void add(int i, Byte k) { synchronized (this.sync) { this.list.add(i, k); }  } 
/* 305 */     public Byte set(int index, Byte k) { synchronized (this.sync) { return (Byte)this.list.set(index, k); }  } 
/* 306 */     public Byte remove(int i) { synchronized (this.sync) { return (Byte)this.list.remove(i); }  } 
/* 307 */     public int indexOf(Object o) { synchronized (this.sync) { return this.list.indexOf(o); }  } 
/* 308 */     public int lastIndexOf(Object o) { synchronized (this.sync) { return this.list.lastIndexOf(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends AbstractByteList
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     private final byte element;
/*     */ 
/*     */     private Singleton(byte element)
/*     */     {
/* 163 */       this.element = element;
/*     */     }
/*     */     public byte getByte(int i) {
/* 166 */       if (i == 0) return this.element; throw new IndexOutOfBoundsException(); } 
/* 167 */     public byte removeByte(int i) { throw new UnsupportedOperationException(); } 
/* 168 */     public boolean contains(byte k) { return k == this.element; } 
/*     */     public boolean addAll(Collection<? extends Byte> c) {
/* 170 */       throw new UnsupportedOperationException(); } 
/* 171 */     public boolean addAll(int i, Collection<? extends Byte> c) { throw new UnsupportedOperationException(); } 
/* 172 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/* 173 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */     public byte[] toByteArray()
/*     */     {
/* 178 */       byte[] a = new byte[1];
/* 179 */       a[0] = this.element;
/* 180 */       return a;
/*     */     }
/*     */ 
/*     */     public ByteListIterator listIterator() {
/* 184 */       return ByteIterators.singleton(this.element);
/*     */     }
/* 186 */     public ByteListIterator iterator() { return listIterator(); }
/*     */ 
/*     */     public ByteListIterator listIterator(int i) {
/* 189 */       if ((i > 1) || (i < 0)) throw new IndexOutOfBoundsException();
/* 190 */       ByteListIterator l = listIterator();
/* 191 */       if (i == 1) l.next();
/* 192 */       return l;
/*     */     }
/*     */ 
/*     */     public ByteList subList(int from, int to)
/*     */     {
/* 197 */       ensureIndex(from);
/* 198 */       ensureIndex(to);
/* 199 */       if (from > to) throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
/*     */ 
/* 201 */       if ((from != 0) || (to != 1)) return ByteLists.EMPTY_LIST;
/* 202 */       return this;
/*     */     }
/*     */     public int size() {
/* 205 */       return 1; } 
/* 206 */     public void size(int size) { throw new UnsupportedOperationException(); } 
/* 207 */     public void clear() { throw new UnsupportedOperationException(); } 
/*     */     public Object clone() {
/* 209 */       return this;
/*     */     }
/*     */     public boolean rem(byte k) {
/* 212 */       throw new UnsupportedOperationException(); } 
/* 213 */     public boolean addAll(ByteCollection c) { throw new UnsupportedOperationException(); } 
/* 214 */     public boolean addAll(int i, ByteCollection c) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptyList extends ByteCollections.EmptyCollection
/*     */     implements ByteList, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public void add(int index, byte k)
/*     */     {
/*  77 */       throw new UnsupportedOperationException(); } 
/*  78 */     public boolean add(byte k) { throw new UnsupportedOperationException(); } 
/*  79 */     public byte removeByte(int i) { throw new UnsupportedOperationException(); } 
/*  80 */     public byte set(int index, byte k) { throw new UnsupportedOperationException(); } 
/*  81 */     public int indexOf(byte k) { return -1; } 
/*  82 */     public int lastIndexOf(byte k) { return -1; } 
/*  83 */     public boolean addAll(Collection<? extends Byte> c) { throw new UnsupportedOperationException(); } 
/*  84 */     public boolean addAll(int i, Collection<? extends Byte> c) { throw new UnsupportedOperationException(); } 
/*  85 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  86 */     public Byte get(int i) { throw new IndexOutOfBoundsException(); } 
/*  87 */     public boolean addAll(ByteCollection c) { throw new UnsupportedOperationException(); } 
/*  88 */     public boolean addAll(ByteList c) { throw new UnsupportedOperationException(); } 
/*  89 */     public boolean addAll(int i, ByteCollection c) { throw new UnsupportedOperationException(); } 
/*  90 */     public boolean addAll(int i, ByteList c) { throw new UnsupportedOperationException(); } 
/*  91 */     public void add(int index, Byte k) { throw new UnsupportedOperationException(); } 
/*  92 */     public boolean add(Byte k) { throw new UnsupportedOperationException(); } 
/*  93 */     public Byte set(int index, Byte k) { throw new UnsupportedOperationException(); } 
/*  94 */     public byte getByte(int i) { throw new IndexOutOfBoundsException(); } 
/*  95 */     public Byte remove(int k) { throw new UnsupportedOperationException(); } 
/*  96 */     public int indexOf(Object k) { return -1; } 
/*  97 */     public int lastIndexOf(Object k) { return -1; }
/*     */ 
/*     */     @Deprecated
/*     */     public ByteIterator byteIterator()
/*     */     {
/* 102 */       return ByteIterators.EMPTY_ITERATOR;
/*     */     }
/* 104 */     public ByteListIterator listIterator() { return ByteIterators.EMPTY_ITERATOR; } 
/*     */     public ByteListIterator iterator() {
/* 106 */       return ByteIterators.EMPTY_ITERATOR;
/*     */     }
/* 108 */     public ByteListIterator listIterator(int i) { if (i == 0) return ByteIterators.EMPTY_ITERATOR; throw new IndexOutOfBoundsException(String.valueOf(i)); } 
/*     */     @Deprecated
/*     */     public ByteListIterator byteListIterator() {
/* 111 */       return listIterator();
/*     */     }
/* 114 */     @Deprecated
/*     */     public ByteListIterator byteListIterator(int i) { return listIterator(i); } 
/*     */     public ByteList subList(int from, int to) {
/* 116 */       if ((from == 0) && (to == 0)) return this; throw new IndexOutOfBoundsException();
/*     */     }
/* 119 */     @Deprecated
/*     */     public ByteList byteSubList(int from, int to) { return subList(from, to); } 
/*     */     public void getElements(int from, byte[] a, int offset, int length) {
/* 121 */       if ((from == 0) && (length == 0) && (offset >= 0) && (offset <= a.length)) return; throw new IndexOutOfBoundsException(); } 
/* 122 */     public void removeElements(int from, int to) { throw new UnsupportedOperationException(); } 
/*     */     public void addElements(int index, byte[] a, int offset, int length) {
/* 124 */       throw new UnsupportedOperationException(); } 
/* 125 */     public void addElements(int index, byte[] a) { throw new UnsupportedOperationException(); } 
/*     */     public void size(int s) {
/* 127 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     public int compareTo(List<? extends Byte> o) {
/* 130 */       if (o == this) return 0;
/* 131 */       return o.isEmpty() ? 0 : -1;
/*     */     }
/*     */     private Object readResolve() {
/* 134 */       return ByteLists.EMPTY_LIST; } 
/* 135 */     public Object clone() { return ByteLists.EMPTY_LIST; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteLists
 * JD-Core Version:    0.6.2
 */