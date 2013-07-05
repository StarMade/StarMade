/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Object2ReferenceSortedMaps
/*     */ {
/* 103 */   public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/*     */ 
/*     */   public static <K> Comparator<? super Map.Entry<K, ?>> entryComparator(Comparator<K> comparator)
/*     */   {
/*  62 */     return new Comparator() {
/*     */       public int compare(Map.Entry<K, ?> x, Map.Entry<K, ?> y) {
/*  64 */         return this.val$comparator.compare(x.getKey(), y.getKey());
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public static <K, V> Object2ReferenceSortedMap<K, V> singleton(K key, V value)
/*     */   {
/* 162 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static <K, V> Object2ReferenceSortedMap<K, V> singleton(K key, V value, Comparator<? super K> comparator)
/*     */   {
/* 174 */     return new Singleton(key, value, comparator);
/*     */   }
/*     */ 
/*     */   public static <K, V> Object2ReferenceSortedMap<K, V> synchronize(Object2ReferenceSortedMap<K, V> m)
/*     */   {
/* 205 */     return new SynchronizedSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static <K, V> Object2ReferenceSortedMap<K, V> synchronize(Object2ReferenceSortedMap<K, V> m, Object sync)
/*     */   {
/* 213 */     return new SynchronizedSortedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static <K, V> Object2ReferenceSortedMap<K, V> unmodifiable(Object2ReferenceSortedMap<K, V> m)
/*     */   {
/* 239 */     return new UnmodifiableSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedMap<K, V> extends Object2ReferenceMaps.UnmodifiableMap<K, V>
/*     */     implements Object2ReferenceSortedMap<K, V>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Object2ReferenceSortedMap<K, V> sortedMap;
/*     */ 
/*     */     protected UnmodifiableSortedMap(Object2ReferenceSortedMap<K, V> m)
/*     */     {
/* 219 */       super();
/* 220 */       this.sortedMap = m;
/*     */     }
/* 222 */     public Comparator<? super K> comparator() { return this.sortedMap.comparator(); } 
/* 223 */     public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.object2ReferenceEntrySet()); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<K, V>> entrySet() {
/* 225 */       return object2ReferenceEntrySet(); } 
/* 226 */     public ObjectSortedSet<K> keySet() { if (this.keys == null) this.keys = ObjectSortedSets.unmodifiable(this.sortedMap.keySet()); return (ObjectSortedSet)this.keys; } 
/* 227 */     public Object2ReferenceSortedMap<K, V> subMap(K from, K to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 228 */     public Object2ReferenceSortedMap<K, V> headMap(K to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 229 */     public Object2ReferenceSortedMap<K, V> tailMap(K from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); } 
/* 230 */     public K firstKey() { return this.sortedMap.firstKey(); } 
/* 231 */     public K lastKey() { return this.sortedMap.lastKey(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedMap<K, V> extends Object2ReferenceMaps.SynchronizedMap<K, V>
/*     */     implements Object2ReferenceSortedMap<K, V>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Object2ReferenceSortedMap<K, V> sortedMap;
/*     */ 
/*     */     protected SynchronizedSortedMap(Object2ReferenceSortedMap<K, V> m, Object sync)
/*     */     {
/* 181 */       super(sync);
/* 182 */       this.sortedMap = m;
/*     */     }
/*     */     protected SynchronizedSortedMap(Object2ReferenceSortedMap<K, V> m) {
/* 185 */       super();
/* 186 */       this.sortedMap = m;
/*     */     }
/* 188 */     public Comparator<? super K> comparator() { synchronized (this.sync) { return this.sortedMap.comparator(); }  } 
/* 189 */     public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.object2ReferenceEntrySet(), this.sync); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<K, V>> entrySet() {
/* 191 */       return object2ReferenceEntrySet(); } 
/* 192 */     public ObjectSortedSet<K> keySet() { if (this.keys == null) this.keys = ObjectSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (ObjectSortedSet)this.keys; } 
/* 193 */     public Object2ReferenceSortedMap<K, V> subMap(K from, K to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 194 */     public Object2ReferenceSortedMap<K, V> headMap(K to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 195 */     public Object2ReferenceSortedMap<K, V> tailMap(K from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); } 
/* 196 */     public K firstKey() { synchronized (this.sync) { return this.sortedMap.firstKey(); }  } 
/* 197 */     public K lastKey() { synchronized (this.sync) { return this.sortedMap.lastKey(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton<K, V> extends Object2ReferenceMaps.Singleton<K, V>
/*     */     implements Object2ReferenceSortedMap<K, V>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Comparator<? super K> comparator;
/*     */ 
/*     */     protected Singleton(K key, V value, Comparator<? super K> comparator)
/*     */     {
/* 119 */       super(value);
/* 120 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     protected Singleton(K key, V value) {
/* 124 */       this(key, value, null);
/*     */     }
/*     */ 
/*     */     final int compare(K k1, K k2)
/*     */     {
/* 129 */       return this.comparator == null ? ((Comparable)k1).compareTo(k2) : this.comparator.compare(k1, k2);
/*     */     }
/*     */     public Comparator<? super K> comparator() {
/* 132 */       return this.comparator;
/*     */     }
/*     */     public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() {
/* 135 */       if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Object2ReferenceMaps.Singleton.SingletonEntry(this), Object2ReferenceSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries;
/*     */     }
/* 137 */     public ObjectSortedSet<Map.Entry<K, V>> entrySet() { return object2ReferenceEntrySet(); } 
/*     */     public ObjectSortedSet<K> keySet() {
/* 139 */       if (this.keys == null) this.keys = ObjectSortedSets.singleton(this.key, this.comparator); return (ObjectSortedSet)this.keys;
/*     */     }
/*     */     public Object2ReferenceSortedMap<K, V> subMap(K from, K to) {
/* 142 */       if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Object2ReferenceSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Object2ReferenceSortedMap<K, V> headMap(K to) {
/* 145 */       if (compare(this.key, to) < 0) return this; return Object2ReferenceSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Object2ReferenceSortedMap<K, V> tailMap(K from) {
/* 148 */       if (compare(from, this.key) <= 0) return this; return Object2ReferenceSortedMaps.EMPTY_MAP;
/*     */     }
/* 150 */     public K firstKey() { return this.key; } 
/* 151 */     public K lastKey() { return this.key; }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySortedMap<K, V> extends Object2ReferenceMaps.EmptyMap<K, V>
/*     */     implements Object2ReferenceSortedMap<K, V>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public Comparator<? super K> comparator()
/*     */     {
/*  76 */       return null;
/*     */     }
/*  78 */     public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() { return ObjectSortedSets.EMPTY_SET; } 
/*     */     public ObjectSortedSet<Map.Entry<K, V>> entrySet() {
/*  80 */       return ObjectSortedSets.EMPTY_SET;
/*     */     }
/*  82 */     public ObjectSortedSet<K> keySet() { return ObjectSortedSets.EMPTY_SET; } 
/*     */     public Object2ReferenceSortedMap<K, V> subMap(K from, K to) {
/*  84 */       return Object2ReferenceSortedMaps.EMPTY_MAP;
/*     */     }
/*  86 */     public Object2ReferenceSortedMap<K, V> headMap(K to) { return Object2ReferenceSortedMaps.EMPTY_MAP; } 
/*     */     public Object2ReferenceSortedMap<K, V> tailMap(K from) {
/*  88 */       return Object2ReferenceSortedMaps.EMPTY_MAP; } 
/*  89 */     public K firstKey() { throw new NoSuchElementException(); } 
/*  90 */     public K lastKey() { throw new NoSuchElementException(); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ReferenceSortedMaps
 * JD-Core Version:    0.6.2
 */