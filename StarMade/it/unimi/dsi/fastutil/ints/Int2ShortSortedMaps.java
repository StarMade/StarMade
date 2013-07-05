/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Int2ShortSortedMaps
/*     */ {
/* 103 */   public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/*     */ 
/*     */   public static Comparator<? super Map.Entry<Integer, ?>> entryComparator(IntComparator comparator)
/*     */   {
/*  64 */     return new Comparator() {
/*     */       public int compare(Map.Entry<Integer, ?> x, Map.Entry<Integer, ?> y) {
/*  66 */         return this.val$comparator.compare(x.getKey(), y.getKey());
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public static Int2ShortSortedMap singleton(Integer key, Short value)
/*     */   {
/* 173 */     return new Singleton(key.intValue(), value.shortValue());
/*     */   }
/*     */ 
/*     */   public static Int2ShortSortedMap singleton(Integer key, Short value, IntComparator comparator)
/*     */   {
/* 187 */     return new Singleton(key.intValue(), value.shortValue(), comparator);
/*     */   }
/*     */ 
/*     */   public static Int2ShortSortedMap singleton(int key, short value)
/*     */   {
/* 202 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static Int2ShortSortedMap singleton(int key, short value, IntComparator comparator)
/*     */   {
/* 216 */     return new Singleton(key, value, comparator);
/*     */   }
/*     */ 
/*     */   public static Int2ShortSortedMap synchronize(Int2ShortSortedMap m)
/*     */   {
/* 272 */     return new SynchronizedSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static Int2ShortSortedMap synchronize(Int2ShortSortedMap m, Object sync)
/*     */   {
/* 282 */     return new SynchronizedSortedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static Int2ShortSortedMap unmodifiable(Int2ShortSortedMap m)
/*     */   {
/* 332 */     return new UnmodifiableSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedMap extends Int2ShortMaps.UnmodifiableMap
/*     */     implements Int2ShortSortedMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Int2ShortSortedMap sortedMap;
/*     */ 
/*     */     protected UnmodifiableSortedMap(Int2ShortSortedMap m)
/*     */     {
/* 296 */       super();
/* 297 */       this.sortedMap = m;
/*     */     }
/*     */     public IntComparator comparator() {
/* 300 */       return this.sortedMap.comparator();
/*     */     }
/* 302 */     public ObjectSortedSet<Int2ShortMap.Entry> int2ShortEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.int2ShortEntrySet()); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<Integer, Short>> entrySet() {
/* 304 */       return int2ShortEntrySet(); } 
/* 305 */     public IntSortedSet keySet() { if (this.keys == null) this.keys = IntSortedSets.unmodifiable(this.sortedMap.keySet()); return (IntSortedSet)this.keys; } 
/*     */     public Int2ShortSortedMap subMap(int from, int to) {
/* 307 */       return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 308 */     public Int2ShortSortedMap headMap(int to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 309 */     public Int2ShortSortedMap tailMap(int from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); } 
/*     */     public int firstIntKey() {
/* 311 */       return this.sortedMap.firstIntKey(); } 
/* 312 */     public int lastIntKey() { return this.sortedMap.lastIntKey(); }
/*     */ 
/*     */     public Integer firstKey() {
/* 315 */       return (Integer)this.sortedMap.firstKey(); } 
/* 316 */     public Integer lastKey() { return (Integer)this.sortedMap.lastKey(); } 
/*     */     public Int2ShortSortedMap subMap(Integer from, Integer to) {
/* 318 */       return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 319 */     public Int2ShortSortedMap headMap(Integer to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 320 */     public Int2ShortSortedMap tailMap(Integer from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedMap extends Int2ShortMaps.SynchronizedMap
/*     */     implements Int2ShortSortedMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Int2ShortSortedMap sortedMap;
/*     */ 
/*     */     protected SynchronizedSortedMap(Int2ShortSortedMap m, Object sync)
/*     */     {
/* 231 */       super(sync);
/* 232 */       this.sortedMap = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedSortedMap(Int2ShortSortedMap m) {
/* 236 */       super();
/* 237 */       this.sortedMap = m;
/*     */     }
/*     */     public IntComparator comparator() {
/* 240 */       synchronized (this.sync) { return this.sortedMap.comparator(); } 
/*     */     }
/* 242 */     public ObjectSortedSet<Int2ShortMap.Entry> int2ShortEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.int2ShortEntrySet(), this.sync); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<Integer, Short>> entrySet() {
/* 244 */       return int2ShortEntrySet(); } 
/* 245 */     public IntSortedSet keySet() { if (this.keys == null) this.keys = IntSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (IntSortedSet)this.keys; } 
/*     */     public Int2ShortSortedMap subMap(int from, int to) {
/* 247 */       return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 248 */     public Int2ShortSortedMap headMap(int to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 249 */     public Int2ShortSortedMap tailMap(int from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); } 
/*     */     public int firstIntKey() {
/* 251 */       synchronized (this.sync) { return this.sortedMap.firstIntKey(); }  } 
/* 252 */     public int lastIntKey() { synchronized (this.sync) { return this.sortedMap.lastIntKey(); } }
/*     */ 
/*     */     public Integer firstKey() {
/* 255 */       synchronized (this.sync) { return (Integer)this.sortedMap.firstKey(); }  } 
/* 256 */     public Integer lastKey() { synchronized (this.sync) { return (Integer)this.sortedMap.lastKey(); }  } 
/*     */     public Int2ShortSortedMap subMap(Integer from, Integer to) {
/* 258 */       return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 259 */     public Int2ShortSortedMap headMap(Integer to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 260 */     public Int2ShortSortedMap tailMap(Integer from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class Singleton extends Int2ShortMaps.Singleton
/*     */     implements Int2ShortSortedMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final IntComparator comparator;
/*     */ 
/*     */     protected Singleton(int key, short value, IntComparator comparator)
/*     */     {
/* 119 */       super(value);
/* 120 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     protected Singleton(int key, short value) {
/* 124 */       this(key, value, null);
/*     */     }
/*     */ 
/*     */     final int compare(int k1, int k2)
/*     */     {
/* 129 */       return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/*     */     }
/*     */     public IntComparator comparator() {
/* 132 */       return this.comparator;
/*     */     }
/*     */     public ObjectSortedSet<Int2ShortMap.Entry> int2ShortEntrySet() {
/* 135 */       if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Int2ShortMaps.Singleton.SingletonEntry(this), Int2ShortSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries;
/*     */     }
/* 137 */     public ObjectSortedSet<Map.Entry<Integer, Short>> entrySet() { return int2ShortEntrySet(); } 
/*     */     public IntSortedSet keySet() {
/* 139 */       if (this.keys == null) this.keys = IntSortedSets.singleton(this.key, this.comparator); return (IntSortedSet)this.keys;
/*     */     }
/*     */     public Int2ShortSortedMap subMap(int from, int to) {
/* 142 */       if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Int2ShortSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Int2ShortSortedMap headMap(int to) {
/* 145 */       if (compare(this.key, to) < 0) return this; return Int2ShortSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Int2ShortSortedMap tailMap(int from) {
/* 148 */       if (compare(from, this.key) <= 0) return this; return Int2ShortSortedMaps.EMPTY_MAP;
/*     */     }
/* 150 */     public int firstIntKey() { return this.key; } 
/* 151 */     public int lastIntKey() { return this.key; }
/*     */ 
/*     */     public Int2ShortSortedMap headMap(Integer oto) {
/* 154 */       return headMap(oto.intValue()); } 
/* 155 */     public Int2ShortSortedMap tailMap(Integer ofrom) { return tailMap(ofrom.intValue()); } 
/* 156 */     public Int2ShortSortedMap subMap(Integer ofrom, Integer oto) { return subMap(ofrom.intValue(), oto.intValue()); } 
/*     */     public Integer firstKey() {
/* 158 */       return Integer.valueOf(firstIntKey()); } 
/* 159 */     public Integer lastKey() { return Integer.valueOf(lastIntKey()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySortedMap extends Int2ShortMaps.EmptyMap
/*     */     implements Int2ShortSortedMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public IntComparator comparator()
/*     */     {
/*  78 */       return null;
/*     */     }
/*  80 */     public ObjectSortedSet<Int2ShortMap.Entry> int2ShortEntrySet() { return ObjectSortedSets.EMPTY_SET; } 
/*     */     public ObjectSortedSet<Map.Entry<Integer, Short>> entrySet() {
/*  82 */       return ObjectSortedSets.EMPTY_SET;
/*     */     }
/*  84 */     public IntSortedSet keySet() { return IntSortedSets.EMPTY_SET; } 
/*     */     public Int2ShortSortedMap subMap(int from, int to) {
/*  86 */       return Int2ShortSortedMaps.EMPTY_MAP;
/*     */     }
/*  88 */     public Int2ShortSortedMap headMap(int to) { return Int2ShortSortedMaps.EMPTY_MAP; } 
/*     */     public Int2ShortSortedMap tailMap(int from) {
/*  90 */       return Int2ShortSortedMaps.EMPTY_MAP; } 
/*  91 */     public int firstIntKey() { throw new NoSuchElementException(); } 
/*  92 */     public int lastIntKey() { throw new NoSuchElementException(); } 
/*  93 */     public Int2ShortSortedMap headMap(Integer oto) { return headMap(oto.intValue()); } 
/*  94 */     public Int2ShortSortedMap tailMap(Integer ofrom) { return tailMap(ofrom.intValue()); } 
/*  95 */     public Int2ShortSortedMap subMap(Integer ofrom, Integer oto) { return subMap(ofrom.intValue(), oto.intValue()); } 
/*  96 */     public Integer firstKey() { return Integer.valueOf(firstIntKey()); } 
/*  97 */     public Integer lastKey() { return Integer.valueOf(lastIntKey()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ShortSortedMaps
 * JD-Core Version:    0.6.2
 */