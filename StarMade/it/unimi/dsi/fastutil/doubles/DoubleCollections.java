/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrays;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class DoubleCollections
/*     */ {
/*     */   public static DoubleCollection synchronize(DoubleCollection c)
/*     */   {
/* 149 */     return new SynchronizedCollection(c);
/*     */   }
/*     */ 
/*     */   public static DoubleCollection synchronize(DoubleCollection c, Object sync)
/*     */   {
/* 159 */     return new SynchronizedCollection(c, sync);
/*     */   }
/*     */ 
/*     */   public static DoubleCollection unmodifiable(DoubleCollection c)
/*     */   {
/* 222 */     return new UnmodifiableCollection(c);
/*     */   }
/*     */ 
/*     */   public static DoubleCollection asCollection(DoubleIterable iterable)
/*     */   {
/* 263 */     if ((iterable instanceof DoubleCollection)) return (DoubleCollection)iterable;
/* 264 */     return new IterableCollection(iterable);
/*     */   }
/*     */ 
/*     */   public static class IterableCollection extends AbstractDoubleCollection
/*     */     implements Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final DoubleIterable iterable;
/*     */ 
/*     */     protected IterableCollection(DoubleIterable iterable)
/*     */     {
/* 233 */       if (iterable == null) throw new NullPointerException();
/* 234 */       this.iterable = iterable;
/*     */     }
/*     */ 
/*     */     public int size() {
/* 238 */       int c = 0;
/* 239 */       DoubleIterator iterator = iterator();
/* 240 */       while (iterator.hasNext()) {
/* 241 */         iterator.next();
/* 242 */         c++;
/*     */       }
/*     */ 
/* 245 */       return c;
/*     */     }
/*     */     public boolean isEmpty() {
/* 248 */       return this.iterable.iterator().hasNext(); } 
/* 249 */     public DoubleIterator iterator() { return this.iterable.iterator(); } 
/*     */     @Deprecated
/*     */     public DoubleIterator doubleIterator() {
/* 252 */       return iterator();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableCollection
/*     */     implements DoubleCollection, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final DoubleCollection collection;
/*     */ 
/*     */     protected UnmodifiableCollection(DoubleCollection c)
/*     */     {
/* 171 */       if (c == null) throw new NullPointerException();
/* 172 */       this.collection = c;
/*     */     }
/*     */     public int size() {
/* 175 */       return this.collection.size(); } 
/* 176 */     public boolean isEmpty() { return this.collection.isEmpty(); } 
/* 177 */     public boolean contains(double o) { return this.collection.contains(o); } 
/*     */     public DoubleIterator iterator() {
/* 179 */       return DoubleIterators.unmodifiable(this.collection.iterator());
/*     */     }
/* 182 */     @Deprecated
/*     */     public DoubleIterator doubleIterator() { return iterator(); } 
/*     */     public boolean add(double k) {
/* 184 */       throw new UnsupportedOperationException(); } 
/* 185 */     public boolean remove(Object ok) { throw new UnsupportedOperationException(); } 
/*     */     public boolean addAll(Collection<? extends Double> c) {
/* 187 */       throw new UnsupportedOperationException(); } 
/* 188 */     public boolean containsAll(Collection<?> c) { return this.collection.containsAll(c); } 
/* 189 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/* 190 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*     */     public void clear() {
/* 192 */       throw new UnsupportedOperationException(); } 
/* 193 */     public String toString() { return this.collection.toString(); } 
/*     */     public <T> T[] toArray(T[] a) {
/* 195 */       return this.collection.toArray(a);
/*     */     }
/* 197 */     public Object[] toArray() { return this.collection.toArray(); }
/*     */ 
/*     */     public double[] toDoubleArray() {
/* 200 */       return this.collection.toDoubleArray(); } 
/* 201 */     public double[] toDoubleArray(double[] a) { return this.collection.toDoubleArray(a); } 
/* 202 */     public double[] toArray(double[] a) { return this.collection.toArray(a); } 
/* 203 */     public boolean rem(double k) { throw new UnsupportedOperationException(); } 
/*     */     public boolean addAll(DoubleCollection c) {
/* 205 */       throw new UnsupportedOperationException(); } 
/* 206 */     public boolean containsAll(DoubleCollection c) { return this.collection.containsAll(c); } 
/* 207 */     public boolean removeAll(DoubleCollection c) { throw new UnsupportedOperationException(); } 
/* 208 */     public boolean retainAll(DoubleCollection c) { throw new UnsupportedOperationException(); } 
/*     */     public boolean add(Double k) {
/* 210 */       throw new UnsupportedOperationException(); } 
/* 211 */     public boolean contains(Object k) { return this.collection.contains(k); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedCollection
/*     */     implements DoubleCollection, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final DoubleCollection collection;
/*     */     protected final Object sync;
/*     */ 
/*     */     protected SynchronizedCollection(DoubleCollection c, Object sync)
/*     */     {
/*  91 */       if (c == null) throw new NullPointerException();
/*  92 */       this.collection = c;
/*  93 */       this.sync = sync;
/*     */     }
/*     */ 
/*     */     protected SynchronizedCollection(DoubleCollection c) {
/*  97 */       if (c == null) throw new NullPointerException();
/*  98 */       this.collection = c;
/*  99 */       this.sync = this;
/*     */     }
/*     */     public int size() {
/* 102 */       synchronized (this.sync) { return this.collection.size(); }  } 
/* 103 */     public boolean isEmpty() { synchronized (this.sync) { return this.collection.isEmpty(); }  } 
/* 104 */     public boolean contains(double o) { synchronized (this.sync) { return this.collection.contains(o); }  } 
/*     */     public double[] toDoubleArray() {
/* 106 */       synchronized (this.sync) { return this.collection.toDoubleArray(); } 
/*     */     }
/*     */ 
/* 109 */     public Object[] toArray() { synchronized (this.sync) { return this.collection.toArray(); }  } 
/* 110 */     public double[] toDoubleArray(double[] a) { synchronized (this.sync) { return this.collection.toDoubleArray(a); }  } 
/* 111 */     public double[] toArray(double[] a) { synchronized (this.sync) { return this.collection.toDoubleArray(a); }  } 
/*     */     public boolean addAll(DoubleCollection c) {
/* 113 */       synchronized (this.sync) { return this.collection.addAll(c); }  } 
/* 114 */     public boolean containsAll(DoubleCollection c) { synchronized (this.sync) { return this.collection.containsAll(c); }  } 
/* 115 */     public boolean removeAll(DoubleCollection c) { synchronized (this.sync) { return this.collection.removeAll(c); }  } 
/* 116 */     public boolean retainAll(DoubleCollection c) { synchronized (this.sync) { return this.collection.retainAll(c); }  } 
/*     */     public boolean add(Double k) {
/* 118 */       synchronized (this.sync) { return this.collection.add(k); }  } 
/* 119 */     public boolean contains(Object k) { synchronized (this.sync) { return this.collection.contains(k); } }
/*     */ 
/*     */     public <T> T[] toArray(T[] a) {
/* 122 */       synchronized (this.sync) { return this.collection.toArray(a); } 
/*     */     }
/* 124 */     public DoubleIterator iterator() { return this.collection.iterator(); } 
/*     */     @Deprecated
/*     */     public DoubleIterator doubleIterator() {
/* 127 */       return iterator();
/*     */     }
/* 129 */     public boolean add(double k) { synchronized (this.sync) { return this.collection.add(k); }  } 
/* 130 */     public boolean rem(double k) { synchronized (this.sync) { return this.collection.rem(k); }  } 
/* 131 */     public boolean remove(Object ok) { synchronized (this.sync) { return this.collection.remove(ok); }  } 
/*     */     public boolean addAll(Collection<? extends Double> c) {
/* 133 */       synchronized (this.sync) { return this.collection.addAll(c); }  } 
/* 134 */     public boolean containsAll(Collection<?> c) { synchronized (this.sync) { return this.collection.containsAll(c); }  } 
/* 135 */     public boolean removeAll(Collection<?> c) { synchronized (this.sync) { return this.collection.removeAll(c); }  } 
/* 136 */     public boolean retainAll(Collection<?> c) { synchronized (this.sync) { return this.collection.retainAll(c); }  } 
/*     */     public void clear() {
/* 138 */       synchronized (this.sync) { this.collection.clear(); }  } 
/* 139 */     public String toString() { synchronized (this.sync) { return this.collection.toString(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static abstract class EmptyCollection extends AbstractDoubleCollection
/*     */   {
/*     */     public boolean add(double k)
/*     */     {
/*  60 */       throw new UnsupportedOperationException(); } 
/*  61 */     public boolean contains(double k) { return false; } 
/*  62 */     public Object[] toArray() { return ObjectArrays.EMPTY_ARRAY; } 
/*  63 */     public double[] toDoubleArray(double[] a) { return a; } 
/*  64 */     public double[] toDoubleArray() { return DoubleArrays.EMPTY_ARRAY; } 
/*  65 */     public boolean rem(double k) { throw new UnsupportedOperationException(); } 
/*  66 */     public boolean addAll(DoubleCollection c) { throw new UnsupportedOperationException(); } 
/*  67 */     public boolean removeAll(DoubleCollection c) { throw new UnsupportedOperationException(); } 
/*  68 */     public boolean retainAll(DoubleCollection c) { throw new UnsupportedOperationException(); } 
/*  69 */     public boolean containsAll(DoubleCollection c) { return c.isEmpty(); } 
/*     */     public DoubleBidirectionalIterator iterator() {
/*  71 */       return DoubleIterators.EMPTY_ITERATOR; } 
/*  72 */     public int size() { return 0; } 
/*     */     public void clear() {  } 
/*  74 */     public int hashCode() { return 0; } 
/*     */     public boolean equals(Object o) {
/*  76 */       if (o == this) return true;
/*  77 */       if (!(o instanceof Collection)) return false;
/*  78 */       return ((Collection)o).isEmpty();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleCollections
 * JD-Core Version:    0.6.2
 */