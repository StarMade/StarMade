/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class ObjectSets
/*     */ {
/*  70 */   public static final EmptySet EMPTY_SET = new EmptySet();
/*     */ 
/*     */   public static <K> ObjectSet<K> singleton(K element)
/*     */   {
/*  97 */     return new Singleton(element);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectSet<K> synchronize(ObjectSet<K> s)
/*     */   {
/* 118 */     return new SynchronizedSet(s);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectSet<K> synchronize(ObjectSet<K> s, Object sync)
/*     */   {
/* 126 */     return new SynchronizedSet(s, sync);
/*     */   }
/*     */ 
/*     */   public static <K> ObjectSet<K> unmodifiable(ObjectSet<K> s)
/*     */   {
/* 143 */     return new UnmodifiableSet(s);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSet<K> extends ObjectCollections.UnmodifiableCollection<K>
/*     */     implements ObjectSet<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected UnmodifiableSet(ObjectSet<K> s)
/*     */     {
/* 131 */       super();
/*     */     }
/* 133 */     public boolean remove(Object k) { throw new UnsupportedOperationException(); } 
/* 134 */     public boolean equals(Object o) { return this.collection.equals(o); } 
/* 135 */     public int hashCode() { return this.collection.hashCode(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSet<K> extends ObjectCollections.SynchronizedCollection<K>
/*     */     implements ObjectSet<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     protected SynchronizedSet(ObjectSet<K> s, Object sync)
/*     */     {
/* 103 */       super(sync);
/*     */     }
/*     */     protected SynchronizedSet(ObjectSet<K> s) {
/* 106 */       super();
/*     */     }
/* 108 */     public boolean remove(Object k) { synchronized (this.sync) { return this.collection.remove(k); }  } 
/* 109 */     public boolean equals(Object o) { synchronized (this.sync) { return this.collection.equals(o); }  } 
/* 110 */     public int hashCode() { synchronized (this.sync) { return this.collection.hashCode(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton<K> extends AbstractObjectSet<K>
/*     */     implements Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final K element;
/*     */ 
/*     */     protected Singleton(K element)
/*     */     {
/*  79 */       this.element = element;
/*     */     }
/*  81 */     public boolean add(K k) { throw new UnsupportedOperationException(); } 
/*  82 */     public boolean contains(Object k) { return k == null ? false : this.element == null ? true : k.equals(this.element); } 
/*  83 */     public boolean addAll(Collection<? extends K> c) { throw new UnsupportedOperationException(); } 
/*  84 */     public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*  85 */     public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); } 
/*     */     public ObjectListIterator<K> iterator() {
/*  87 */       return ObjectIterators.singleton(this.element); } 
/*  88 */     public int size() { return 1; } 
/*  89 */     public Object clone() { return this; }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySet<K> extends ObjectCollections.EmptyCollection<K>
/*     */     implements ObjectSet<K>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean remove(Object ok)
/*     */     {
/*  60 */       throw new UnsupportedOperationException(); } 
/*  61 */     public Object clone() { return ObjectSets.EMPTY_SET; } 
/*  62 */     private Object readResolve() { return ObjectSets.EMPTY_SET; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectSets
 * JD-Core Version:    0.6.2
 */