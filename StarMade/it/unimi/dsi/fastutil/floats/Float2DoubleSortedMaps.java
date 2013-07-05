/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Float2DoubleSortedMaps
/*     */ {
/* 103 */   public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/*     */ 
/*     */   public static Comparator<? super Map.Entry<Float, ?>> entryComparator(FloatComparator comparator)
/*     */   {
/*  64 */     return new Comparator() {
/*     */       public int compare(Map.Entry<Float, ?> x, Map.Entry<Float, ?> y) {
/*  66 */         return this.val$comparator.compare(x.getKey(), y.getKey());
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public static Float2DoubleSortedMap singleton(Float key, Double value)
/*     */   {
/* 173 */     return new Singleton(key.floatValue(), value.doubleValue());
/*     */   }
/*     */ 
/*     */   public static Float2DoubleSortedMap singleton(Float key, Double value, FloatComparator comparator)
/*     */   {
/* 187 */     return new Singleton(key.floatValue(), value.doubleValue(), comparator);
/*     */   }
/*     */ 
/*     */   public static Float2DoubleSortedMap singleton(float key, double value)
/*     */   {
/* 202 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static Float2DoubleSortedMap singleton(float key, double value, FloatComparator comparator)
/*     */   {
/* 216 */     return new Singleton(key, value, comparator);
/*     */   }
/*     */ 
/*     */   public static Float2DoubleSortedMap synchronize(Float2DoubleSortedMap m)
/*     */   {
/* 272 */     return new SynchronizedSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static Float2DoubleSortedMap synchronize(Float2DoubleSortedMap m, Object sync)
/*     */   {
/* 282 */     return new SynchronizedSortedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static Float2DoubleSortedMap unmodifiable(Float2DoubleSortedMap m)
/*     */   {
/* 332 */     return new UnmodifiableSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedMap extends Float2DoubleMaps.UnmodifiableMap
/*     */     implements Float2DoubleSortedMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Float2DoubleSortedMap sortedMap;
/*     */ 
/*     */     protected UnmodifiableSortedMap(Float2DoubleSortedMap m)
/*     */     {
/* 296 */       super();
/* 297 */       this.sortedMap = m;
/*     */     }
/*     */     public FloatComparator comparator() {
/* 300 */       return this.sortedMap.comparator();
/*     */     }
/* 302 */     public ObjectSortedSet<Float2DoubleMap.Entry> float2DoubleEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.float2DoubleEntrySet()); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<Float, Double>> entrySet() {
/* 304 */       return float2DoubleEntrySet(); } 
/* 305 */     public FloatSortedSet keySet() { if (this.keys == null) this.keys = FloatSortedSets.unmodifiable(this.sortedMap.keySet()); return (FloatSortedSet)this.keys; } 
/*     */     public Float2DoubleSortedMap subMap(float from, float to) {
/* 307 */       return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 308 */     public Float2DoubleSortedMap headMap(float to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 309 */     public Float2DoubleSortedMap tailMap(float from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); } 
/*     */     public float firstFloatKey() {
/* 311 */       return this.sortedMap.firstFloatKey(); } 
/* 312 */     public float lastFloatKey() { return this.sortedMap.lastFloatKey(); }
/*     */ 
/*     */     public Float firstKey() {
/* 315 */       return (Float)this.sortedMap.firstKey(); } 
/* 316 */     public Float lastKey() { return (Float)this.sortedMap.lastKey(); } 
/*     */     public Float2DoubleSortedMap subMap(Float from, Float to) {
/* 318 */       return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 319 */     public Float2DoubleSortedMap headMap(Float to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 320 */     public Float2DoubleSortedMap tailMap(Float from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedMap extends Float2DoubleMaps.SynchronizedMap
/*     */     implements Float2DoubleSortedMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Float2DoubleSortedMap sortedMap;
/*     */ 
/*     */     protected SynchronizedSortedMap(Float2DoubleSortedMap m, Object sync)
/*     */     {
/* 231 */       super(sync);
/* 232 */       this.sortedMap = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedSortedMap(Float2DoubleSortedMap m) {
/* 236 */       super();
/* 237 */       this.sortedMap = m;
/*     */     }
/*     */     public FloatComparator comparator() {
/* 240 */       synchronized (this.sync) { return this.sortedMap.comparator(); } 
/*     */     }
/* 242 */     public ObjectSortedSet<Float2DoubleMap.Entry> float2DoubleEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.float2DoubleEntrySet(), this.sync); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<Float, Double>> entrySet() {
/* 244 */       return float2DoubleEntrySet(); } 
/* 245 */     public FloatSortedSet keySet() { if (this.keys == null) this.keys = FloatSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (FloatSortedSet)this.keys; } 
/*     */     public Float2DoubleSortedMap subMap(float from, float to) {
/* 247 */       return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 248 */     public Float2DoubleSortedMap headMap(float to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 249 */     public Float2DoubleSortedMap tailMap(float from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); } 
/*     */     public float firstFloatKey() {
/* 251 */       synchronized (this.sync) { return this.sortedMap.firstFloatKey(); }  } 
/* 252 */     public float lastFloatKey() { synchronized (this.sync) { return this.sortedMap.lastFloatKey(); } }
/*     */ 
/*     */     public Float firstKey() {
/* 255 */       synchronized (this.sync) { return (Float)this.sortedMap.firstKey(); }  } 
/* 256 */     public Float lastKey() { synchronized (this.sync) { return (Float)this.sortedMap.lastKey(); }  } 
/*     */     public Float2DoubleSortedMap subMap(Float from, Float to) {
/* 258 */       return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 259 */     public Float2DoubleSortedMap headMap(Float to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 260 */     public Float2DoubleSortedMap tailMap(Float from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class Singleton extends Float2DoubleMaps.Singleton
/*     */     implements Float2DoubleSortedMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final FloatComparator comparator;
/*     */ 
/*     */     protected Singleton(float key, double value, FloatComparator comparator)
/*     */     {
/* 119 */       super(value);
/* 120 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     protected Singleton(float key, double value) {
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
/*     */     public ObjectSortedSet<Float2DoubleMap.Entry> float2DoubleEntrySet() {
/* 135 */       if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Float2DoubleMaps.Singleton.SingletonEntry(this), Float2DoubleSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries;
/*     */     }
/* 137 */     public ObjectSortedSet<Map.Entry<Float, Double>> entrySet() { return float2DoubleEntrySet(); } 
/*     */     public FloatSortedSet keySet() {
/* 139 */       if (this.keys == null) this.keys = FloatSortedSets.singleton(this.key, this.comparator); return (FloatSortedSet)this.keys;
/*     */     }
/*     */     public Float2DoubleSortedMap subMap(float from, float to) {
/* 142 */       if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Float2DoubleSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Float2DoubleSortedMap headMap(float to) {
/* 145 */       if (compare(this.key, to) < 0) return this; return Float2DoubleSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Float2DoubleSortedMap tailMap(float from) {
/* 148 */       if (compare(from, this.key) <= 0) return this; return Float2DoubleSortedMaps.EMPTY_MAP;
/*     */     }
/* 150 */     public float firstFloatKey() { return this.key; } 
/* 151 */     public float lastFloatKey() { return this.key; }
/*     */ 
/*     */     public Float2DoubleSortedMap headMap(Float oto) {
/* 154 */       return headMap(oto.floatValue()); } 
/* 155 */     public Float2DoubleSortedMap tailMap(Float ofrom) { return tailMap(ofrom.floatValue()); } 
/* 156 */     public Float2DoubleSortedMap subMap(Float ofrom, Float oto) { return subMap(ofrom.floatValue(), oto.floatValue()); } 
/*     */     public Float firstKey() {
/* 158 */       return Float.valueOf(firstFloatKey()); } 
/* 159 */     public Float lastKey() { return Float.valueOf(lastFloatKey()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySortedMap extends Float2DoubleMaps.EmptyMap
/*     */     implements Float2DoubleSortedMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public FloatComparator comparator()
/*     */     {
/*  78 */       return null;
/*     */     }
/*  80 */     public ObjectSortedSet<Float2DoubleMap.Entry> float2DoubleEntrySet() { return ObjectSortedSets.EMPTY_SET; } 
/*     */     public ObjectSortedSet<Map.Entry<Float, Double>> entrySet() {
/*  82 */       return ObjectSortedSets.EMPTY_SET;
/*     */     }
/*  84 */     public FloatSortedSet keySet() { return FloatSortedSets.EMPTY_SET; } 
/*     */     public Float2DoubleSortedMap subMap(float from, float to) {
/*  86 */       return Float2DoubleSortedMaps.EMPTY_MAP;
/*     */     }
/*  88 */     public Float2DoubleSortedMap headMap(float to) { return Float2DoubleSortedMaps.EMPTY_MAP; } 
/*     */     public Float2DoubleSortedMap tailMap(float from) {
/*  90 */       return Float2DoubleSortedMaps.EMPTY_MAP; } 
/*  91 */     public float firstFloatKey() { throw new NoSuchElementException(); } 
/*  92 */     public float lastFloatKey() { throw new NoSuchElementException(); } 
/*  93 */     public Float2DoubleSortedMap headMap(Float oto) { return headMap(oto.floatValue()); } 
/*  94 */     public Float2DoubleSortedMap tailMap(Float ofrom) { return tailMap(ofrom.floatValue()); } 
/*  95 */     public Float2DoubleSortedMap subMap(Float ofrom, Float oto) { return subMap(ofrom.floatValue(), oto.floatValue()); } 
/*  96 */     public Float firstKey() { return Float.valueOf(firstFloatKey()); } 
/*  97 */     public Float lastKey() { return Float.valueOf(lastFloatKey()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2DoubleSortedMaps
 * JD-Core Version:    0.6.2
 */