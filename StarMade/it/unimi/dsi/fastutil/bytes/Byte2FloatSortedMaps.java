/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Byte2FloatSortedMaps
/*     */ {
/* 103 */   public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/*     */ 
/*     */   public static Comparator<? super Map.Entry<Byte, ?>> entryComparator(ByteComparator comparator)
/*     */   {
/*  64 */     return new Comparator() {
/*     */       public int compare(Map.Entry<Byte, ?> x, Map.Entry<Byte, ?> y) {
/*  66 */         return this.val$comparator.compare(x.getKey(), y.getKey());
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public static Byte2FloatSortedMap singleton(Byte key, Float value)
/*     */   {
/* 173 */     return new Singleton(key.byteValue(), value.floatValue());
/*     */   }
/*     */ 
/*     */   public static Byte2FloatSortedMap singleton(Byte key, Float value, ByteComparator comparator)
/*     */   {
/* 187 */     return new Singleton(key.byteValue(), value.floatValue(), comparator);
/*     */   }
/*     */ 
/*     */   public static Byte2FloatSortedMap singleton(byte key, float value)
/*     */   {
/* 202 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static Byte2FloatSortedMap singleton(byte key, float value, ByteComparator comparator)
/*     */   {
/* 216 */     return new Singleton(key, value, comparator);
/*     */   }
/*     */ 
/*     */   public static Byte2FloatSortedMap synchronize(Byte2FloatSortedMap m)
/*     */   {
/* 272 */     return new SynchronizedSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static Byte2FloatSortedMap synchronize(Byte2FloatSortedMap m, Object sync)
/*     */   {
/* 282 */     return new SynchronizedSortedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static Byte2FloatSortedMap unmodifiable(Byte2FloatSortedMap m)
/*     */   {
/* 332 */     return new UnmodifiableSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedMap extends Byte2FloatMaps.UnmodifiableMap
/*     */     implements Byte2FloatSortedMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Byte2FloatSortedMap sortedMap;
/*     */ 
/*     */     protected UnmodifiableSortedMap(Byte2FloatSortedMap m)
/*     */     {
/* 296 */       super();
/* 297 */       this.sortedMap = m;
/*     */     }
/*     */     public ByteComparator comparator() {
/* 300 */       return this.sortedMap.comparator();
/*     */     }
/* 302 */     public ObjectSortedSet<Byte2FloatMap.Entry> byte2FloatEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.byte2FloatEntrySet()); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<Byte, Float>> entrySet() {
/* 304 */       return byte2FloatEntrySet(); } 
/* 305 */     public ByteSortedSet keySet() { if (this.keys == null) this.keys = ByteSortedSets.unmodifiable(this.sortedMap.keySet()); return (ByteSortedSet)this.keys; } 
/*     */     public Byte2FloatSortedMap subMap(byte from, byte to) {
/* 307 */       return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 308 */     public Byte2FloatSortedMap headMap(byte to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 309 */     public Byte2FloatSortedMap tailMap(byte from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); } 
/*     */     public byte firstByteKey() {
/* 311 */       return this.sortedMap.firstByteKey(); } 
/* 312 */     public byte lastByteKey() { return this.sortedMap.lastByteKey(); }
/*     */ 
/*     */     public Byte firstKey() {
/* 315 */       return (Byte)this.sortedMap.firstKey(); } 
/* 316 */     public Byte lastKey() { return (Byte)this.sortedMap.lastKey(); } 
/*     */     public Byte2FloatSortedMap subMap(Byte from, Byte to) {
/* 318 */       return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 319 */     public Byte2FloatSortedMap headMap(Byte to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 320 */     public Byte2FloatSortedMap tailMap(Byte from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedMap extends Byte2FloatMaps.SynchronizedMap
/*     */     implements Byte2FloatSortedMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Byte2FloatSortedMap sortedMap;
/*     */ 
/*     */     protected SynchronizedSortedMap(Byte2FloatSortedMap m, Object sync)
/*     */     {
/* 231 */       super(sync);
/* 232 */       this.sortedMap = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedSortedMap(Byte2FloatSortedMap m) {
/* 236 */       super();
/* 237 */       this.sortedMap = m;
/*     */     }
/*     */     public ByteComparator comparator() {
/* 240 */       synchronized (this.sync) { return this.sortedMap.comparator(); } 
/*     */     }
/* 242 */     public ObjectSortedSet<Byte2FloatMap.Entry> byte2FloatEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.byte2FloatEntrySet(), this.sync); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<Byte, Float>> entrySet() {
/* 244 */       return byte2FloatEntrySet(); } 
/* 245 */     public ByteSortedSet keySet() { if (this.keys == null) this.keys = ByteSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (ByteSortedSet)this.keys; } 
/*     */     public Byte2FloatSortedMap subMap(byte from, byte to) {
/* 247 */       return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 248 */     public Byte2FloatSortedMap headMap(byte to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 249 */     public Byte2FloatSortedMap tailMap(byte from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); } 
/*     */     public byte firstByteKey() {
/* 251 */       synchronized (this.sync) { return this.sortedMap.firstByteKey(); }  } 
/* 252 */     public byte lastByteKey() { synchronized (this.sync) { return this.sortedMap.lastByteKey(); } }
/*     */ 
/*     */     public Byte firstKey() {
/* 255 */       synchronized (this.sync) { return (Byte)this.sortedMap.firstKey(); }  } 
/* 256 */     public Byte lastKey() { synchronized (this.sync) { return (Byte)this.sortedMap.lastKey(); }  } 
/*     */     public Byte2FloatSortedMap subMap(Byte from, Byte to) {
/* 258 */       return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 259 */     public Byte2FloatSortedMap headMap(Byte to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 260 */     public Byte2FloatSortedMap tailMap(Byte from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class Singleton extends Byte2FloatMaps.Singleton
/*     */     implements Byte2FloatSortedMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ByteComparator comparator;
/*     */ 
/*     */     protected Singleton(byte key, float value, ByteComparator comparator)
/*     */     {
/* 119 */       super(value);
/* 120 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     protected Singleton(byte key, float value) {
/* 124 */       this(key, value, null);
/*     */     }
/*     */ 
/*     */     final int compare(byte k1, byte k2)
/*     */     {
/* 129 */       return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/*     */     }
/*     */     public ByteComparator comparator() {
/* 132 */       return this.comparator;
/*     */     }
/*     */     public ObjectSortedSet<Byte2FloatMap.Entry> byte2FloatEntrySet() {
/* 135 */       if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Byte2FloatMaps.Singleton.SingletonEntry(this), Byte2FloatSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries;
/*     */     }
/* 137 */     public ObjectSortedSet<Map.Entry<Byte, Float>> entrySet() { return byte2FloatEntrySet(); } 
/*     */     public ByteSortedSet keySet() {
/* 139 */       if (this.keys == null) this.keys = ByteSortedSets.singleton(this.key, this.comparator); return (ByteSortedSet)this.keys;
/*     */     }
/*     */     public Byte2FloatSortedMap subMap(byte from, byte to) {
/* 142 */       if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Byte2FloatSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Byte2FloatSortedMap headMap(byte to) {
/* 145 */       if (compare(this.key, to) < 0) return this; return Byte2FloatSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Byte2FloatSortedMap tailMap(byte from) {
/* 148 */       if (compare(from, this.key) <= 0) return this; return Byte2FloatSortedMaps.EMPTY_MAP;
/*     */     }
/* 150 */     public byte firstByteKey() { return this.key; } 
/* 151 */     public byte lastByteKey() { return this.key; }
/*     */ 
/*     */     public Byte2FloatSortedMap headMap(Byte oto) {
/* 154 */       return headMap(oto.byteValue()); } 
/* 155 */     public Byte2FloatSortedMap tailMap(Byte ofrom) { return tailMap(ofrom.byteValue()); } 
/* 156 */     public Byte2FloatSortedMap subMap(Byte ofrom, Byte oto) { return subMap(ofrom.byteValue(), oto.byteValue()); } 
/*     */     public Byte firstKey() {
/* 158 */       return Byte.valueOf(firstByteKey()); } 
/* 159 */     public Byte lastKey() { return Byte.valueOf(lastByteKey()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySortedMap extends Byte2FloatMaps.EmptyMap
/*     */     implements Byte2FloatSortedMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public ByteComparator comparator()
/*     */     {
/*  78 */       return null;
/*     */     }
/*  80 */     public ObjectSortedSet<Byte2FloatMap.Entry> byte2FloatEntrySet() { return ObjectSortedSets.EMPTY_SET; } 
/*     */     public ObjectSortedSet<Map.Entry<Byte, Float>> entrySet() {
/*  82 */       return ObjectSortedSets.EMPTY_SET;
/*     */     }
/*  84 */     public ByteSortedSet keySet() { return ByteSortedSets.EMPTY_SET; } 
/*     */     public Byte2FloatSortedMap subMap(byte from, byte to) {
/*  86 */       return Byte2FloatSortedMaps.EMPTY_MAP;
/*     */     }
/*  88 */     public Byte2FloatSortedMap headMap(byte to) { return Byte2FloatSortedMaps.EMPTY_MAP; } 
/*     */     public Byte2FloatSortedMap tailMap(byte from) {
/*  90 */       return Byte2FloatSortedMaps.EMPTY_MAP; } 
/*  91 */     public byte firstByteKey() { throw new NoSuchElementException(); } 
/*  92 */     public byte lastByteKey() { throw new NoSuchElementException(); } 
/*  93 */     public Byte2FloatSortedMap headMap(Byte oto) { return headMap(oto.byteValue()); } 
/*  94 */     public Byte2FloatSortedMap tailMap(Byte ofrom) { return tailMap(ofrom.byteValue()); } 
/*  95 */     public Byte2FloatSortedMap subMap(Byte ofrom, Byte oto) { return subMap(ofrom.byteValue(), oto.byteValue()); } 
/*  96 */     public Byte firstKey() { return Byte.valueOf(firstByteKey()); } 
/*  97 */     public Byte lastKey() { return Byte.valueOf(lastByteKey()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2FloatSortedMaps
 * JD-Core Version:    0.6.2
 */