/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Long2ObjectSortedMaps
/*     */ {
/* 103 */   public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/*     */ 
/*     */   public static Comparator<? super Map.Entry<Long, ?>> entryComparator(LongComparator comparator)
/*     */   {
/*  63 */     return new Comparator() {
/*     */       public int compare(Map.Entry<Long, ?> x, Map.Entry<Long, ?> y) {
/*  65 */         return this.val$comparator.compare(x.getKey(), y.getKey());
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public static <V> Long2ObjectSortedMap<V> singleton(Long key, V value)
/*     */   {
/* 173 */     return new Singleton(key.longValue(), value);
/*     */   }
/*     */ 
/*     */   public static <V> Long2ObjectSortedMap<V> singleton(Long key, V value, LongComparator comparator)
/*     */   {
/* 187 */     return new Singleton(key.longValue(), value, comparator);
/*     */   }
/*     */ 
/*     */   public static <V> Long2ObjectSortedMap<V> singleton(long key, V value)
/*     */   {
/* 202 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static <V> Long2ObjectSortedMap<V> singleton(long key, V value, LongComparator comparator)
/*     */   {
/* 216 */     return new Singleton(key, value, comparator);
/*     */   }
/*     */ 
/*     */   public static <V> Long2ObjectSortedMap<V> synchronize(Long2ObjectSortedMap<V> m)
/*     */   {
/* 272 */     return new SynchronizedSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static <V> Long2ObjectSortedMap<V> synchronize(Long2ObjectSortedMap<V> m, Object sync)
/*     */   {
/* 282 */     return new SynchronizedSortedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static <V> Long2ObjectSortedMap<V> unmodifiable(Long2ObjectSortedMap<V> m)
/*     */   {
/* 332 */     return new UnmodifiableSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedMap<V> extends Long2ObjectMaps.UnmodifiableMap<V>
/*     */     implements Long2ObjectSortedMap<V>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Long2ObjectSortedMap<V> sortedMap;
/*     */ 
/*     */     protected UnmodifiableSortedMap(Long2ObjectSortedMap<V> m)
/*     */     {
/* 296 */       super();
/* 297 */       this.sortedMap = m;
/*     */     }
/*     */     public LongComparator comparator() {
/* 300 */       return this.sortedMap.comparator();
/*     */     }
/* 302 */     public ObjectSortedSet<Long2ObjectMap.Entry<V>> long2ObjectEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.long2ObjectEntrySet()); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<Long, V>> entrySet() {
/* 304 */       return long2ObjectEntrySet(); } 
/* 305 */     public LongSortedSet keySet() { if (this.keys == null) this.keys = LongSortedSets.unmodifiable(this.sortedMap.keySet()); return (LongSortedSet)this.keys; } 
/*     */     public Long2ObjectSortedMap<V> subMap(long from, long to) {
/* 307 */       return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 308 */     public Long2ObjectSortedMap<V> headMap(long to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 309 */     public Long2ObjectSortedMap<V> tailMap(long from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); } 
/*     */     public long firstLongKey() {
/* 311 */       return this.sortedMap.firstLongKey(); } 
/* 312 */     public long lastLongKey() { return this.sortedMap.lastLongKey(); }
/*     */ 
/*     */     public Long firstKey() {
/* 315 */       return (Long)this.sortedMap.firstKey(); } 
/* 316 */     public Long lastKey() { return (Long)this.sortedMap.lastKey(); } 
/*     */     public Long2ObjectSortedMap<V> subMap(Long from, Long to) {
/* 318 */       return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 319 */     public Long2ObjectSortedMap<V> headMap(Long to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 320 */     public Long2ObjectSortedMap<V> tailMap(Long from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedMap<V> extends Long2ObjectMaps.SynchronizedMap<V>
/*     */     implements Long2ObjectSortedMap<V>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Long2ObjectSortedMap<V> sortedMap;
/*     */ 
/*     */     protected SynchronizedSortedMap(Long2ObjectSortedMap<V> m, Object sync)
/*     */     {
/* 231 */       super(sync);
/* 232 */       this.sortedMap = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedSortedMap(Long2ObjectSortedMap<V> m) {
/* 236 */       super();
/* 237 */       this.sortedMap = m;
/*     */     }
/*     */     public LongComparator comparator() {
/* 240 */       synchronized (this.sync) { return this.sortedMap.comparator(); } 
/*     */     }
/* 242 */     public ObjectSortedSet<Long2ObjectMap.Entry<V>> long2ObjectEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.long2ObjectEntrySet(), this.sync); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<Long, V>> entrySet() {
/* 244 */       return long2ObjectEntrySet(); } 
/* 245 */     public LongSortedSet keySet() { if (this.keys == null) this.keys = LongSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (LongSortedSet)this.keys; } 
/*     */     public Long2ObjectSortedMap<V> subMap(long from, long to) {
/* 247 */       return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 248 */     public Long2ObjectSortedMap<V> headMap(long to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 249 */     public Long2ObjectSortedMap<V> tailMap(long from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); } 
/*     */     public long firstLongKey() {
/* 251 */       synchronized (this.sync) { return this.sortedMap.firstLongKey(); }  } 
/* 252 */     public long lastLongKey() { synchronized (this.sync) { return this.sortedMap.lastLongKey(); } }
/*     */ 
/*     */     public Long firstKey() {
/* 255 */       synchronized (this.sync) { return (Long)this.sortedMap.firstKey(); }  } 
/* 256 */     public Long lastKey() { synchronized (this.sync) { return (Long)this.sortedMap.lastKey(); }  } 
/*     */     public Long2ObjectSortedMap<V> subMap(Long from, Long to) {
/* 258 */       return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 259 */     public Long2ObjectSortedMap<V> headMap(Long to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 260 */     public Long2ObjectSortedMap<V> tailMap(Long from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class Singleton<V> extends Long2ObjectMaps.Singleton<V>
/*     */     implements Long2ObjectSortedMap<V>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final LongComparator comparator;
/*     */ 
/*     */     protected Singleton(long key, V value, LongComparator comparator)
/*     */     {
/* 119 */       super(value);
/* 120 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     protected Singleton(long key, V value) {
/* 124 */       this(key, value, null);
/*     */     }
/*     */ 
/*     */     final int compare(long k1, long k2)
/*     */     {
/* 129 */       return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/*     */     }
/*     */     public LongComparator comparator() {
/* 132 */       return this.comparator;
/*     */     }
/*     */     public ObjectSortedSet<Long2ObjectMap.Entry<V>> long2ObjectEntrySet() {
/* 135 */       if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Long2ObjectMaps.Singleton.SingletonEntry(this), Long2ObjectSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries;
/*     */     }
/* 137 */     public ObjectSortedSet<Map.Entry<Long, V>> entrySet() { return long2ObjectEntrySet(); } 
/*     */     public LongSortedSet keySet() {
/* 139 */       if (this.keys == null) this.keys = LongSortedSets.singleton(this.key, this.comparator); return (LongSortedSet)this.keys;
/*     */     }
/*     */     public Long2ObjectSortedMap<V> subMap(long from, long to) {
/* 142 */       if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Long2ObjectSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Long2ObjectSortedMap<V> headMap(long to) {
/* 145 */       if (compare(this.key, to) < 0) return this; return Long2ObjectSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Long2ObjectSortedMap<V> tailMap(long from) {
/* 148 */       if (compare(from, this.key) <= 0) return this; return Long2ObjectSortedMaps.EMPTY_MAP;
/*     */     }
/* 150 */     public long firstLongKey() { return this.key; } 
/* 151 */     public long lastLongKey() { return this.key; }
/*     */ 
/*     */     public Long2ObjectSortedMap<V> headMap(Long oto) {
/* 154 */       return headMap(oto.longValue()); } 
/* 155 */     public Long2ObjectSortedMap<V> tailMap(Long ofrom) { return tailMap(ofrom.longValue()); } 
/* 156 */     public Long2ObjectSortedMap<V> subMap(Long ofrom, Long oto) { return subMap(ofrom.longValue(), oto.longValue()); } 
/*     */     public Long firstKey() {
/* 158 */       return Long.valueOf(firstLongKey()); } 
/* 159 */     public Long lastKey() { return Long.valueOf(lastLongKey()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySortedMap<V> extends Long2ObjectMaps.EmptyMap<V>
/*     */     implements Long2ObjectSortedMap<V>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public LongComparator comparator()
/*     */     {
/*  77 */       return null;
/*     */     }
/*  79 */     public ObjectSortedSet<Long2ObjectMap.Entry<V>> long2ObjectEntrySet() { return ObjectSortedSets.EMPTY_SET; } 
/*     */     public ObjectSortedSet<Map.Entry<Long, V>> entrySet() {
/*  81 */       return ObjectSortedSets.EMPTY_SET;
/*     */     }
/*  83 */     public LongSortedSet keySet() { return LongSortedSets.EMPTY_SET; } 
/*     */     public Long2ObjectSortedMap<V> subMap(long from, long to) {
/*  85 */       return Long2ObjectSortedMaps.EMPTY_MAP;
/*     */     }
/*  87 */     public Long2ObjectSortedMap<V> headMap(long to) { return Long2ObjectSortedMaps.EMPTY_MAP; } 
/*     */     public Long2ObjectSortedMap<V> tailMap(long from) {
/*  89 */       return Long2ObjectSortedMaps.EMPTY_MAP; } 
/*  90 */     public long firstLongKey() { throw new NoSuchElementException(); } 
/*  91 */     public long lastLongKey() { throw new NoSuchElementException(); } 
/*  92 */     public Long2ObjectSortedMap<V> headMap(Long oto) { return headMap(oto.longValue()); } 
/*  93 */     public Long2ObjectSortedMap<V> tailMap(Long ofrom) { return tailMap(ofrom.longValue()); } 
/*  94 */     public Long2ObjectSortedMap<V> subMap(Long ofrom, Long oto) { return subMap(ofrom.longValue(), oto.longValue()); } 
/*  95 */     public Long firstKey() { return Long.valueOf(firstLongKey()); } 
/*  96 */     public Long lastKey() { return Long.valueOf(lastLongKey()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ObjectSortedMaps
 * JD-Core Version:    0.6.2
 */