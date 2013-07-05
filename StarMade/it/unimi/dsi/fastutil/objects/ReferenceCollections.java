/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class ReferenceCollections
/*     */ {
/*     */   public static <K> ReferenceCollection<K> synchronize(ReferenceCollection<K> c)
/*     */   {
/* 127 */     return new SynchronizedCollection(c);
/*     */   }
/*     */ 
/*     */   public static <K> ReferenceCollection<K> synchronize(ReferenceCollection<K> c, Object sync)
/*     */   {
/* 135 */     return new SynchronizedCollection(c, sync);
/*     */   }
/*     */ 
/*     */   public static <K> ReferenceCollection<K> unmodifiable(ReferenceCollection<K> c)
/*     */   {
/* 167 */     return new UnmodifiableCollection(c);
/*     */   }
/*     */ 
/*     */   public static <K> ReferenceCollection<K> asCollection(ObjectIterable<K> iterable)
/*     */   {
/* 197 */     if ((iterable instanceof ReferenceCollection)) return (ReferenceCollection)iterable;
/* 198 */     return new IterableCollection(iterable);
/*     */   }
/*     */ 
/*     */   public static class IterableCollection<K> extends AbstractReferenceCollection<K>
/*     */     implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ObjectIterable<K> iterable;
/*     */ 
/*     */     protected IterableCollection(ObjectIterable<K> iterable)
/*     */     {
/* 173 */       if (iterable == null) throw new NullPointerException();
/* 174 */       this.iterable = iterable;
/*     */     }
/*     */     public int size() {
/* 177 */       int c = 0;
/* 178 */       ObjectIterator iterator = iterator();
/* 179 */       while (iterator.hasNext()) {
/* 180 */         iterator.next();
/* 181 */         c++;
/*     */       }
/* 183 */       return c;
/*     */     }
/* 185 */     public boolean isEmpty() { return this.iterable.iterator().hasNext(); } 
/* 186 */     public ObjectIterator<K> iterator() { return this.iterable.iterator(); } 
/* 188 */     @Deprecated
/*     */     public ObjectIterator<K> objectIterator() { return iterator(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableCollection<K>
/*     */     implements ReferenceCollection<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ReferenceCollection<K> collection;
/*     */ 
/*     */     protected UnmodifiableCollection(ReferenceCollection<K> c)
/*     */     {
/* 141 */       if (c == null) throw new NullPointerException();
/* 142 */       this.collection = c;
/*     */     }
/* 144 */     public int size() { return this.collection.size(); } 
/* 145 */     public boolean isEmpty() { return this.collection.isEmpty(); } 
/* 146 */     public boolean contains(Object o) { return this.collection.contains(o); } 
/* 147 */     public ObjectIterator<K> iterator() { return ObjectIterators.unmodifiable(this.collection.iterator()); } 
/* 149 */     @Deprecated
/*     */     public ObjectIterator<K> objectIterator() { return iterator(); } 
/* 150 */     public boolean add(K k) { throw new UnsupportedOperationException(); } 
/* 151 */     public boolean remove(Object ok) { throw new UnsupportedOperationException(); } 
/* 152 */     public boolean addAll(Collection<? extends K> c) { throw new UnsupportedOperationException(); } 
/* 153 */     public boolean containsAll(Collection<?> c) { return this.collection.containsAll(c); } 
/* 154 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/* 155 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/* 156 */     public void clear() { throw new UnsupportedOperationException(); } 
/* 157 */     public String toString() { return this.collection.toString(); } 
/* 158 */     public <T> T[] toArray(T[] a) { return this.collection.toArray(a); } 
/* 159 */     public Object[] toArray() { return this.collection.toArray(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedCollection<K>
/*     */     implements ReferenceCollection<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ReferenceCollection<K> collection;
/*     */     protected final Object sync;
/*     */ 
/*     */     protected SynchronizedCollection(ReferenceCollection<K> c, Object sync)
/*     */     {
/*  91 */       if (c == null) throw new NullPointerException();
/*  92 */       this.collection = c;
/*  93 */       this.sync = sync;
/*     */     }
/*     */ 
/*     */     protected SynchronizedCollection(ReferenceCollection<K> c) {
/*  97 */       if (c == null) throw new NullPointerException();
/*  98 */       this.collection = c;
/*  99 */       this.sync = this;
/*     */     }
/*     */     public int size() {
/* 102 */       synchronized (this.sync) { return this.collection.size(); }  } 
/* 103 */     public boolean isEmpty() { synchronized (this.sync) { return this.collection.isEmpty(); }  } 
/* 104 */     public boolean contains(Object o) { synchronized (this.sync) { return this.collection.contains(o); }  } 
/*     */     public Object[] toArray() {
/* 106 */       synchronized (this.sync) { return this.collection.toArray(); }  } 
/* 107 */     public <T> T[] toArray(T[] a) { synchronized (this.sync) { return this.collection.toArray(a); }  } 
/* 108 */     public ObjectIterator<K> iterator() { return this.collection.iterator(); } 
/* 110 */     @Deprecated
/*     */     public ObjectIterator<K> objectIterator() { return iterator(); } 
/* 111 */     public boolean add(K k) { synchronized (this.sync) { return this.collection.add(k); }  } 
/* 112 */     public boolean rem(Object k) { synchronized (this.sync) { return this.collection.remove(k); }  } 
/* 113 */     public boolean remove(Object ok) { synchronized (this.sync) { return this.collection.remove(ok); }  } 
/* 114 */     public boolean addAll(Collection<? extends K> c) { synchronized (this.sync) { return this.collection.addAll(c); }  } 
/* 115 */     public boolean containsAll(Collection<?> c) { synchronized (this.sync) { return this.collection.containsAll(c); }  } 
/* 116 */     public boolean removeAll(Collection<?> c) { synchronized (this.sync) { return this.collection.removeAll(c); }  } 
/* 117 */     public boolean retainAll(Collection<?> c) { synchronized (this.sync) { return this.collection.retainAll(c); }  } 
/* 118 */     public void clear() { synchronized (this.sync) { this.collection.clear(); }  } 
/* 119 */     public String toString() { synchronized (this.sync) { return this.collection.toString(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static abstract class EmptyCollection<K> extends AbstractReferenceCollection<K>
/*     */   {
/*     */     public boolean add(K k)
/*     */     {
/*  59 */       throw new UnsupportedOperationException(); } 
/*  60 */     public boolean contains(Object k) { return false; } 
/*  61 */     public Object[] toArray() { return ObjectArrays.EMPTY_ARRAY; } 
/*  62 */     public boolean remove(Object k) { throw new UnsupportedOperationException(); } 
/*  63 */     public <T> T[] toArray(T[] a) { return a; }
/*     */ 
/*     */     public ObjectBidirectionalIterator<K> iterator()
/*     */     {
/*  67 */       return ObjectIterators.EMPTY_ITERATOR;
/*     */     }
/*  69 */     public int size() { return 0; } 
/*     */     public void clear() {
/*     */     }
/*  72 */     public int hashCode() { return 0; } 
/*     */     public boolean equals(Object o) {
/*  74 */       if (o == this) return true;
/*  75 */       if (!(o instanceof Collection)) return false;
/*  76 */       return ((Collection)o).isEmpty();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceCollections
 * JD-Core Version:    0.6.2
 */