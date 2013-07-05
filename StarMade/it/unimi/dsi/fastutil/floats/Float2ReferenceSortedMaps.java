/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Float2ReferenceSortedMaps
/*     */ {
/* 103 */   public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/*     */ 
/*     */   public static Comparator<? super Map.Entry<Float, ?>> entryComparator(FloatComparator comparator)
/*     */   {
/*  63 */     return new Comparator() {
/*     */       public int compare(Map.Entry<Float, ?> x, Map.Entry<Float, ?> y) {
/*  65 */         return this.val$comparator.compare(x.getKey(), y.getKey());
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public static <V> Float2ReferenceSortedMap<V> singleton(Float key, V value)
/*     */   {
/* 173 */     return new Singleton(key.floatValue(), value);
/*     */   }
/*     */ 
/*     */   public static <V> Float2ReferenceSortedMap<V> singleton(Float key, V value, FloatComparator comparator)
/*     */   {
/* 187 */     return new Singleton(key.floatValue(), value, comparator);
/*     */   }
/*     */ 
/*     */   public static <V> Float2ReferenceSortedMap<V> singleton(float key, V value)
/*     */   {
/* 202 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static <V> Float2ReferenceSortedMap<V> singleton(float key, V value, FloatComparator comparator)
/*     */   {
/* 216 */     return new Singleton(key, value, comparator);
/*     */   }
/*     */ 
/*     */   public static <V> Float2ReferenceSortedMap<V> synchronize(Float2ReferenceSortedMap<V> m)
/*     */   {
/* 272 */     return new SynchronizedSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static <V> Float2ReferenceSortedMap<V> synchronize(Float2ReferenceSortedMap<V> m, Object sync)
/*     */   {
/* 282 */     return new SynchronizedSortedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static <V> Float2ReferenceSortedMap<V> unmodifiable(Float2ReferenceSortedMap<V> m)
/*     */   {
/* 332 */     return new UnmodifiableSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedMap<V> extends Float2ReferenceMaps.UnmodifiableMap<V>
/*     */     implements Float2ReferenceSortedMap<V>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Float2ReferenceSortedMap<V> sortedMap;
/*     */ 
/*     */     protected UnmodifiableSortedMap(Float2ReferenceSortedMap<V> m)
/*     */     {
/* 296 */       super();
/* 297 */       this.sortedMap = m;
/*     */     }
/*     */     public FloatComparator comparator() {
/* 300 */       return this.sortedMap.comparator();
/*     */     }
/* 302 */     public ObjectSortedSet<Float2ReferenceMap.Entry<V>> float2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.float2ReferenceEntrySet()); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<Float, V>> entrySet() {
/* 304 */       return float2ReferenceEntrySet(); } 
/* 305 */     public FloatSortedSet keySet() { if (this.keys == null) this.keys = FloatSortedSets.unmodifiable(this.sortedMap.keySet()); return (FloatSortedSet)this.keys; } 
/*     */     public Float2ReferenceSortedMap<V> subMap(float from, float to) {
/* 307 */       return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 308 */     public Float2ReferenceSortedMap<V> headMap(float to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 309 */     public Float2ReferenceSortedMap<V> tailMap(float from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); } 
/*     */     public float firstFloatKey() {
/* 311 */       return this.sortedMap.firstFloatKey(); } 
/* 312 */     public float lastFloatKey() { return this.sortedMap.lastFloatKey(); }
/*     */ 
/*     */     public Float firstKey() {
/* 315 */       return (Float)this.sortedMap.firstKey(); } 
/* 316 */     public Float lastKey() { return (Float)this.sortedMap.lastKey(); } 
/*     */     public Float2ReferenceSortedMap<V> subMap(Float from, Float to) {
/* 318 */       return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 319 */     public Float2ReferenceSortedMap<V> headMap(Float to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 320 */     public Float2ReferenceSortedMap<V> tailMap(Float from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedMap<V> extends Float2ReferenceMaps.SynchronizedMap<V>
/*     */     implements Float2ReferenceSortedMap<V>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Float2ReferenceSortedMap<V> sortedMap;
/*     */ 
/*     */     protected SynchronizedSortedMap(Float2ReferenceSortedMap<V> m, Object sync)
/*     */     {
/* 231 */       super(sync);
/* 232 */       this.sortedMap = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedSortedMap(Float2ReferenceSortedMap<V> m) {
/* 236 */       super();
/* 237 */       this.sortedMap = m;
/*     */     }
/*     */     public FloatComparator comparator() {
/* 240 */       synchronized (this.sync) { return this.sortedMap.comparator(); } 
/*     */     }
/* 242 */     public ObjectSortedSet<Float2ReferenceMap.Entry<V>> float2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.float2ReferenceEntrySet(), this.sync); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<Float, V>> entrySet() {
/* 244 */       return float2ReferenceEntrySet(); } 
/* 245 */     public FloatSortedSet keySet() { if (this.keys == null) this.keys = FloatSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (FloatSortedSet)this.keys; } 
/*     */     public Float2ReferenceSortedMap<V> subMap(float from, float to) {
/* 247 */       return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 248 */     public Float2ReferenceSortedMap<V> headMap(float to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 249 */     public Float2ReferenceSortedMap<V> tailMap(float from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); } 
/*     */     public float firstFloatKey() {
/* 251 */       synchronized (this.sync) { return this.sortedMap.firstFloatKey(); }  } 
/* 252 */     public float lastFloatKey() { synchronized (this.sync) { return this.sortedMap.lastFloatKey(); } }
/*     */ 
/*     */     public Float firstKey() {
/* 255 */       synchronized (this.sync) { return (Float)this.sortedMap.firstKey(); }  } 
/* 256 */     public Float lastKey() { synchronized (this.sync) { return (Float)this.sortedMap.lastKey(); }  } 
/*     */     public Float2ReferenceSortedMap<V> subMap(Float from, Float to) {
/* 258 */       return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 259 */     public Float2ReferenceSortedMap<V> headMap(Float to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 260 */     public Float2ReferenceSortedMap<V> tailMap(Float from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class Singleton<V> extends Float2ReferenceMaps.Singleton<V>
/*     */     implements Float2ReferenceSortedMap<V>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final FloatComparator comparator;
/*     */ 
/*     */     protected Singleton(float key, V value, FloatComparator comparator)
/*     */     {
/* 119 */       super(value);
/* 120 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     protected Singleton(float key, V value) {
/* 124 */       this(key, value, null);
/*     */     }
/*     */ 
/*     */     final int compare(float k1, float k2)
/*     */     {
/* 129 */       return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/*     */     }
/*     */     public FloatComparator comparator() {
/* 132 */       return this.comparator;
/*     */     }
/*     */     public ObjectSortedSet<Float2ReferenceMap.Entry<V>> float2ReferenceEntrySet() {
/* 135 */       if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Float2ReferenceMaps.Singleton.SingletonEntry(this), Float2ReferenceSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries;
/*     */     }
/* 137 */     public ObjectSortedSet<Map.Entry<Float, V>> entrySet() { return float2ReferenceEntrySet(); } 
/*     */     public FloatSortedSet keySet() {
/* 139 */       if (this.keys == null) this.keys = FloatSortedSets.singleton(this.key, this.comparator); return (FloatSortedSet)this.keys;
/*     */     }
/*     */     public Float2ReferenceSortedMap<V> subMap(float from, float to) {
/* 142 */       if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Float2ReferenceSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Float2ReferenceSortedMap<V> headMap(float to) {
/* 145 */       if (compare(this.key, to) < 0) return this; return Float2ReferenceSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Float2ReferenceSortedMap<V> tailMap(float from) {
/* 148 */       if (compare(from, this.key) <= 0) return this; return Float2ReferenceSortedMaps.EMPTY_MAP;
/*     */     }
/* 150 */     public float firstFloatKey() { return this.key; } 
/* 151 */     public float lastFloatKey() { return this.key; }
/*     */ 
/*     */     public Float2ReferenceSortedMap<V> headMap(Float oto) {
/* 154 */       return headMap(oto.floatValue()); } 
/* 155 */     public Float2ReferenceSortedMap<V> tailMap(Float ofrom) { return tailMap(ofrom.floatValue()); } 
/* 156 */     public Float2ReferenceSortedMap<V> subMap(Float ofrom, Float oto) { return subMap(ofrom.floatValue(), oto.floatValue()); } 
/*     */     public Float firstKey() {
/* 158 */       return Float.valueOf(firstFloatKey()); } 
/* 159 */     public Float lastKey() { return Float.valueOf(lastFloatKey()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySortedMap<V> extends Float2ReferenceMaps.EmptyMap<V>
/*     */     implements Float2ReferenceSortedMap<V>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public FloatComparator comparator()
/*     */     {
/*  77 */       return null;
/*     */     }
/*  79 */     public ObjectSortedSet<Float2ReferenceMap.Entry<V>> float2ReferenceEntrySet() { return ObjectSortedSets.EMPTY_SET; } 
/*     */     public ObjectSortedSet<Map.Entry<Float, V>> entrySet() {
/*  81 */       return ObjectSortedSets.EMPTY_SET;
/*     */     }
/*  83 */     public FloatSortedSet keySet() { return FloatSortedSets.EMPTY_SET; } 
/*     */     public Float2ReferenceSortedMap<V> subMap(float from, float to) {
/*  85 */       return Float2ReferenceSortedMaps.EMPTY_MAP;
/*     */     }
/*  87 */     public Float2ReferenceSortedMap<V> headMap(float to) { return Float2ReferenceSortedMaps.EMPTY_MAP; } 
/*     */     public Float2ReferenceSortedMap<V> tailMap(float from) {
/*  89 */       return Float2ReferenceSortedMaps.EMPTY_MAP; } 
/*  90 */     public float firstFloatKey() { throw new NoSuchElementException(); } 
/*  91 */     public float lastFloatKey() { throw new NoSuchElementException(); } 
/*  92 */     public Float2ReferenceSortedMap<V> headMap(Float oto) { return headMap(oto.floatValue()); } 
/*  93 */     public Float2ReferenceSortedMap<V> tailMap(Float ofrom) { return tailMap(ofrom.floatValue()); } 
/*  94 */     public Float2ReferenceSortedMap<V> subMap(Float ofrom, Float oto) { return subMap(ofrom.floatValue(), oto.floatValue()); } 
/*  95 */     public Float firstKey() { return Float.valueOf(firstFloatKey()); } 
/*  96 */     public Float lastKey() { return Float.valueOf(lastFloatKey()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ReferenceSortedMaps
 * JD-Core Version:    0.6.2
 */