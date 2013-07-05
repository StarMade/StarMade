/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Object2BooleanSortedMaps
/*     */ {
/* 103 */   public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/*     */ 
/*     */   public static <K> Comparator<? super Map.Entry<K, ?>> entryComparator(Comparator<K> comparator)
/*     */   {
/*  63 */     return new Comparator() {
/*     */       public int compare(Map.Entry<K, ?> x, Map.Entry<K, ?> y) {
/*  65 */         return this.val$comparator.compare(x.getKey(), y.getKey());
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public static <K> Object2BooleanSortedMap<K> singleton(K key, Boolean value)
/*     */   {
/* 162 */     return new Singleton(key, value.booleanValue());
/*     */   }
/*     */ 
/*     */   public static <K> Object2BooleanSortedMap<K> singleton(K key, Boolean value, Comparator<? super K> comparator)
/*     */   {
/* 174 */     return new Singleton(key, value.booleanValue(), comparator);
/*     */   }
/*     */ 
/*     */   public static <K> Object2BooleanSortedMap<K> singleton(K key, boolean value)
/*     */   {
/* 185 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static <K> Object2BooleanSortedMap<K> singleton(K key, boolean value, Comparator<? super K> comparator)
/*     */   {
/* 197 */     return new Singleton(key, value, comparator);
/*     */   }
/*     */ 
/*     */   public static <K> Object2BooleanSortedMap<K> synchronize(Object2BooleanSortedMap<K> m)
/*     */   {
/* 228 */     return new SynchronizedSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static <K> Object2BooleanSortedMap<K> synchronize(Object2BooleanSortedMap<K> m, Object sync)
/*     */   {
/* 236 */     return new SynchronizedSortedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static <K> Object2BooleanSortedMap<K> unmodifiable(Object2BooleanSortedMap<K> m)
/*     */   {
/* 262 */     return new UnmodifiableSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedMap<K> extends Object2BooleanMaps.UnmodifiableMap<K>
/*     */     implements Object2BooleanSortedMap<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Object2BooleanSortedMap<K> sortedMap;
/*     */ 
/*     */     protected UnmodifiableSortedMap(Object2BooleanSortedMap<K> m)
/*     */     {
/* 242 */       super();
/* 243 */       this.sortedMap = m;
/*     */     }
/* 245 */     public Comparator<? super K> comparator() { return this.sortedMap.comparator(); } 
/* 246 */     public ObjectSortedSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.object2BooleanEntrySet()); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<K, Boolean>> entrySet() {
/* 248 */       return object2BooleanEntrySet(); } 
/* 249 */     public ObjectSortedSet<K> keySet() { if (this.keys == null) this.keys = ObjectSortedSets.unmodifiable(this.sortedMap.keySet()); return (ObjectSortedSet)this.keys; } 
/* 250 */     public Object2BooleanSortedMap<K> subMap(K from, K to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 251 */     public Object2BooleanSortedMap<K> headMap(K to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 252 */     public Object2BooleanSortedMap<K> tailMap(K from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); } 
/* 253 */     public K firstKey() { return this.sortedMap.firstKey(); } 
/* 254 */     public K lastKey() { return this.sortedMap.lastKey(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedMap<K> extends Object2BooleanMaps.SynchronizedMap<K>
/*     */     implements Object2BooleanSortedMap<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Object2BooleanSortedMap<K> sortedMap;
/*     */ 
/*     */     protected SynchronizedSortedMap(Object2BooleanSortedMap<K> m, Object sync)
/*     */     {
/* 204 */       super(sync);
/* 205 */       this.sortedMap = m;
/*     */     }
/*     */     protected SynchronizedSortedMap(Object2BooleanSortedMap<K> m) {
/* 208 */       super();
/* 209 */       this.sortedMap = m;
/*     */     }
/* 211 */     public Comparator<? super K> comparator() { synchronized (this.sync) { return this.sortedMap.comparator(); }  } 
/* 212 */     public ObjectSortedSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.object2BooleanEntrySet(), this.sync); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<K, Boolean>> entrySet() {
/* 214 */       return object2BooleanEntrySet(); } 
/* 215 */     public ObjectSortedSet<K> keySet() { if (this.keys == null) this.keys = ObjectSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (ObjectSortedSet)this.keys; } 
/* 216 */     public Object2BooleanSortedMap<K> subMap(K from, K to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 217 */     public Object2BooleanSortedMap<K> headMap(K to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 218 */     public Object2BooleanSortedMap<K> tailMap(K from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); } 
/* 219 */     public K firstKey() { synchronized (this.sync) { return this.sortedMap.firstKey(); }  } 
/* 220 */     public K lastKey() { synchronized (this.sync) { return this.sortedMap.lastKey(); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton<K> extends Object2BooleanMaps.Singleton<K>
/*     */     implements Object2BooleanSortedMap<K>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Comparator<? super K> comparator;
/*     */ 
/*     */     protected Singleton(K key, boolean value, Comparator<? super K> comparator)
/*     */     {
/* 119 */       super(value);
/* 120 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     protected Singleton(K key, boolean value) {
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
/*     */     public ObjectSortedSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet() {
/* 135 */       if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Object2BooleanMaps.Singleton.SingletonEntry(this), Object2BooleanSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries;
/*     */     }
/* 137 */     public ObjectSortedSet<Map.Entry<K, Boolean>> entrySet() { return object2BooleanEntrySet(); } 
/*     */     public ObjectSortedSet<K> keySet() {
/* 139 */       if (this.keys == null) this.keys = ObjectSortedSets.singleton(this.key, this.comparator); return (ObjectSortedSet)this.keys;
/*     */     }
/*     */     public Object2BooleanSortedMap<K> subMap(K from, K to) {
/* 142 */       if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Object2BooleanSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Object2BooleanSortedMap<K> headMap(K to) {
/* 145 */       if (compare(this.key, to) < 0) return this; return Object2BooleanSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Object2BooleanSortedMap<K> tailMap(K from) {
/* 148 */       if (compare(from, this.key) <= 0) return this; return Object2BooleanSortedMaps.EMPTY_MAP;
/*     */     }
/* 150 */     public K firstKey() { return this.key; } 
/* 151 */     public K lastKey() { return this.key; }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySortedMap<K> extends Object2BooleanMaps.EmptyMap<K>
/*     */     implements Object2BooleanSortedMap<K>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public Comparator<? super K> comparator()
/*     */     {
/*  77 */       return null;
/*     */     }
/*  79 */     public ObjectSortedSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet() { return ObjectSortedSets.EMPTY_SET; } 
/*     */     public ObjectSortedSet<Map.Entry<K, Boolean>> entrySet() {
/*  81 */       return ObjectSortedSets.EMPTY_SET;
/*     */     }
/*  83 */     public ObjectSortedSet<K> keySet() { return ObjectSortedSets.EMPTY_SET; } 
/*     */     public Object2BooleanSortedMap<K> subMap(K from, K to) {
/*  85 */       return Object2BooleanSortedMaps.EMPTY_MAP;
/*     */     }
/*  87 */     public Object2BooleanSortedMap<K> headMap(K to) { return Object2BooleanSortedMaps.EMPTY_MAP; } 
/*     */     public Object2BooleanSortedMap<K> tailMap(K from) {
/*  89 */       return Object2BooleanSortedMaps.EMPTY_MAP; } 
/*  90 */     public K firstKey() { throw new NoSuchElementException(); } 
/*  91 */     public K lastKey() { throw new NoSuchElementException(); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2BooleanSortedMaps
 * JD-Core Version:    0.6.2
 */