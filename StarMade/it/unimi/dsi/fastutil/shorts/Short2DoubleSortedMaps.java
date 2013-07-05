/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Short2DoubleSortedMaps
/*     */ {
/* 103 */   public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/*     */ 
/*     */   public static Comparator<? super Map.Entry<Short, ?>> entryComparator(ShortComparator comparator)
/*     */   {
/*  64 */     return new Comparator() {
/*     */       public int compare(Map.Entry<Short, ?> x, Map.Entry<Short, ?> y) {
/*  66 */         return this.val$comparator.compare(x.getKey(), y.getKey());
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public static Short2DoubleSortedMap singleton(Short key, Double value)
/*     */   {
/* 173 */     return new Singleton(key.shortValue(), value.doubleValue());
/*     */   }
/*     */ 
/*     */   public static Short2DoubleSortedMap singleton(Short key, Double value, ShortComparator comparator)
/*     */   {
/* 187 */     return new Singleton(key.shortValue(), value.doubleValue(), comparator);
/*     */   }
/*     */ 
/*     */   public static Short2DoubleSortedMap singleton(short key, double value)
/*     */   {
/* 202 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static Short2DoubleSortedMap singleton(short key, double value, ShortComparator comparator)
/*     */   {
/* 216 */     return new Singleton(key, value, comparator);
/*     */   }
/*     */ 
/*     */   public static Short2DoubleSortedMap synchronize(Short2DoubleSortedMap m)
/*     */   {
/* 272 */     return new SynchronizedSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static Short2DoubleSortedMap synchronize(Short2DoubleSortedMap m, Object sync)
/*     */   {
/* 282 */     return new SynchronizedSortedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static Short2DoubleSortedMap unmodifiable(Short2DoubleSortedMap m)
/*     */   {
/* 332 */     return new UnmodifiableSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedMap extends Short2DoubleMaps.UnmodifiableMap
/*     */     implements Short2DoubleSortedMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Short2DoubleSortedMap sortedMap;
/*     */ 
/*     */     protected UnmodifiableSortedMap(Short2DoubleSortedMap m)
/*     */     {
/* 296 */       super();
/* 297 */       this.sortedMap = m;
/*     */     }
/*     */     public ShortComparator comparator() {
/* 300 */       return this.sortedMap.comparator();
/*     */     }
/* 302 */     public ObjectSortedSet<Short2DoubleMap.Entry> short2DoubleEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.short2DoubleEntrySet()); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<Short, Double>> entrySet() {
/* 304 */       return short2DoubleEntrySet(); } 
/* 305 */     public ShortSortedSet keySet() { if (this.keys == null) this.keys = ShortSortedSets.unmodifiable(this.sortedMap.keySet()); return (ShortSortedSet)this.keys; } 
/*     */     public Short2DoubleSortedMap subMap(short from, short to) {
/* 307 */       return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 308 */     public Short2DoubleSortedMap headMap(short to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 309 */     public Short2DoubleSortedMap tailMap(short from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); } 
/*     */     public short firstShortKey() {
/* 311 */       return this.sortedMap.firstShortKey(); } 
/* 312 */     public short lastShortKey() { return this.sortedMap.lastShortKey(); }
/*     */ 
/*     */     public Short firstKey() {
/* 315 */       return (Short)this.sortedMap.firstKey(); } 
/* 316 */     public Short lastKey() { return (Short)this.sortedMap.lastKey(); } 
/*     */     public Short2DoubleSortedMap subMap(Short from, Short to) {
/* 318 */       return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 319 */     public Short2DoubleSortedMap headMap(Short to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 320 */     public Short2DoubleSortedMap tailMap(Short from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedMap extends Short2DoubleMaps.SynchronizedMap
/*     */     implements Short2DoubleSortedMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Short2DoubleSortedMap sortedMap;
/*     */ 
/*     */     protected SynchronizedSortedMap(Short2DoubleSortedMap m, Object sync)
/*     */     {
/* 231 */       super(sync);
/* 232 */       this.sortedMap = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedSortedMap(Short2DoubleSortedMap m) {
/* 236 */       super();
/* 237 */       this.sortedMap = m;
/*     */     }
/*     */     public ShortComparator comparator() {
/* 240 */       synchronized (this.sync) { return this.sortedMap.comparator(); } 
/*     */     }
/* 242 */     public ObjectSortedSet<Short2DoubleMap.Entry> short2DoubleEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.short2DoubleEntrySet(), this.sync); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<Short, Double>> entrySet() {
/* 244 */       return short2DoubleEntrySet(); } 
/* 245 */     public ShortSortedSet keySet() { if (this.keys == null) this.keys = ShortSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (ShortSortedSet)this.keys; } 
/*     */     public Short2DoubleSortedMap subMap(short from, short to) {
/* 247 */       return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 248 */     public Short2DoubleSortedMap headMap(short to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 249 */     public Short2DoubleSortedMap tailMap(short from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); } 
/*     */     public short firstShortKey() {
/* 251 */       synchronized (this.sync) { return this.sortedMap.firstShortKey(); }  } 
/* 252 */     public short lastShortKey() { synchronized (this.sync) { return this.sortedMap.lastShortKey(); } }
/*     */ 
/*     */     public Short firstKey() {
/* 255 */       synchronized (this.sync) { return (Short)this.sortedMap.firstKey(); }  } 
/* 256 */     public Short lastKey() { synchronized (this.sync) { return (Short)this.sortedMap.lastKey(); }  } 
/*     */     public Short2DoubleSortedMap subMap(Short from, Short to) {
/* 258 */       return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 259 */     public Short2DoubleSortedMap headMap(Short to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 260 */     public Short2DoubleSortedMap tailMap(Short from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class Singleton extends Short2DoubleMaps.Singleton
/*     */     implements Short2DoubleSortedMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ShortComparator comparator;
/*     */ 
/*     */     protected Singleton(short key, double value, ShortComparator comparator)
/*     */     {
/* 119 */       super(value);
/* 120 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     protected Singleton(short key, double value) {
/* 124 */       this(key, value, null);
/*     */     }
/*     */ 
/*     */     final int compare(short k1, short k2)
/*     */     {
/* 129 */       return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/*     */     }
/*     */     public ShortComparator comparator() {
/* 132 */       return this.comparator;
/*     */     }
/*     */     public ObjectSortedSet<Short2DoubleMap.Entry> short2DoubleEntrySet() {
/* 135 */       if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Short2DoubleMaps.Singleton.SingletonEntry(this), Short2DoubleSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries;
/*     */     }
/* 137 */     public ObjectSortedSet<Map.Entry<Short, Double>> entrySet() { return short2DoubleEntrySet(); } 
/*     */     public ShortSortedSet keySet() {
/* 139 */       if (this.keys == null) this.keys = ShortSortedSets.singleton(this.key, this.comparator); return (ShortSortedSet)this.keys;
/*     */     }
/*     */     public Short2DoubleSortedMap subMap(short from, short to) {
/* 142 */       if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Short2DoubleSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Short2DoubleSortedMap headMap(short to) {
/* 145 */       if (compare(this.key, to) < 0) return this; return Short2DoubleSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Short2DoubleSortedMap tailMap(short from) {
/* 148 */       if (compare(from, this.key) <= 0) return this; return Short2DoubleSortedMaps.EMPTY_MAP;
/*     */     }
/* 150 */     public short firstShortKey() { return this.key; } 
/* 151 */     public short lastShortKey() { return this.key; }
/*     */ 
/*     */     public Short2DoubleSortedMap headMap(Short oto) {
/* 154 */       return headMap(oto.shortValue()); } 
/* 155 */     public Short2DoubleSortedMap tailMap(Short ofrom) { return tailMap(ofrom.shortValue()); } 
/* 156 */     public Short2DoubleSortedMap subMap(Short ofrom, Short oto) { return subMap(ofrom.shortValue(), oto.shortValue()); } 
/*     */     public Short firstKey() {
/* 158 */       return Short.valueOf(firstShortKey()); } 
/* 159 */     public Short lastKey() { return Short.valueOf(lastShortKey()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySortedMap extends Short2DoubleMaps.EmptyMap
/*     */     implements Short2DoubleSortedMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public ShortComparator comparator()
/*     */     {
/*  78 */       return null;
/*     */     }
/*  80 */     public ObjectSortedSet<Short2DoubleMap.Entry> short2DoubleEntrySet() { return ObjectSortedSets.EMPTY_SET; } 
/*     */     public ObjectSortedSet<Map.Entry<Short, Double>> entrySet() {
/*  82 */       return ObjectSortedSets.EMPTY_SET;
/*     */     }
/*  84 */     public ShortSortedSet keySet() { return ShortSortedSets.EMPTY_SET; } 
/*     */     public Short2DoubleSortedMap subMap(short from, short to) {
/*  86 */       return Short2DoubleSortedMaps.EMPTY_MAP;
/*     */     }
/*  88 */     public Short2DoubleSortedMap headMap(short to) { return Short2DoubleSortedMaps.EMPTY_MAP; } 
/*     */     public Short2DoubleSortedMap tailMap(short from) {
/*  90 */       return Short2DoubleSortedMaps.EMPTY_MAP; } 
/*  91 */     public short firstShortKey() { throw new NoSuchElementException(); } 
/*  92 */     public short lastShortKey() { throw new NoSuchElementException(); } 
/*  93 */     public Short2DoubleSortedMap headMap(Short oto) { return headMap(oto.shortValue()); } 
/*  94 */     public Short2DoubleSortedMap tailMap(Short ofrom) { return tailMap(ofrom.shortValue()); } 
/*  95 */     public Short2DoubleSortedMap subMap(Short ofrom, Short oto) { return subMap(ofrom.shortValue(), oto.shortValue()); } 
/*  96 */     public Short firstKey() { return Short.valueOf(firstShortKey()); } 
/*  97 */     public Short lastKey() { return Short.valueOf(lastShortKey()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2DoubleSortedMaps
 * JD-Core Version:    0.6.2
 */