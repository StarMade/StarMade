/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Byte2ReferenceSortedMaps
/*     */ {
/* 103 */   public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/*     */ 
/*     */   public static Comparator<? super Map.Entry<Byte, ?>> entryComparator(ByteComparator comparator)
/*     */   {
/*  63 */     return new Comparator() {
/*     */       public int compare(Map.Entry<Byte, ?> x, Map.Entry<Byte, ?> y) {
/*  65 */         return this.val$comparator.compare(x.getKey(), y.getKey());
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public static <V> Byte2ReferenceSortedMap<V> singleton(Byte key, V value)
/*     */   {
/* 173 */     return new Singleton(key.byteValue(), value);
/*     */   }
/*     */ 
/*     */   public static <V> Byte2ReferenceSortedMap<V> singleton(Byte key, V value, ByteComparator comparator)
/*     */   {
/* 187 */     return new Singleton(key.byteValue(), value, comparator);
/*     */   }
/*     */ 
/*     */   public static <V> Byte2ReferenceSortedMap<V> singleton(byte key, V value)
/*     */   {
/* 202 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static <V> Byte2ReferenceSortedMap<V> singleton(byte key, V value, ByteComparator comparator)
/*     */   {
/* 216 */     return new Singleton(key, value, comparator);
/*     */   }
/*     */ 
/*     */   public static <V> Byte2ReferenceSortedMap<V> synchronize(Byte2ReferenceSortedMap<V> m)
/*     */   {
/* 272 */     return new SynchronizedSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static <V> Byte2ReferenceSortedMap<V> synchronize(Byte2ReferenceSortedMap<V> m, Object sync)
/*     */   {
/* 282 */     return new SynchronizedSortedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static <V> Byte2ReferenceSortedMap<V> unmodifiable(Byte2ReferenceSortedMap<V> m)
/*     */   {
/* 332 */     return new UnmodifiableSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedMap<V> extends Byte2ReferenceMaps.UnmodifiableMap<V>
/*     */     implements Byte2ReferenceSortedMap<V>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Byte2ReferenceSortedMap<V> sortedMap;
/*     */ 
/*     */     protected UnmodifiableSortedMap(Byte2ReferenceSortedMap<V> m)
/*     */     {
/* 296 */       super();
/* 297 */       this.sortedMap = m;
/*     */     }
/*     */     public ByteComparator comparator() {
/* 300 */       return this.sortedMap.comparator();
/*     */     }
/* 302 */     public ObjectSortedSet<Byte2ReferenceMap.Entry<V>> byte2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.byte2ReferenceEntrySet()); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<Byte, V>> entrySet() {
/* 304 */       return byte2ReferenceEntrySet(); } 
/* 305 */     public ByteSortedSet keySet() { if (this.keys == null) this.keys = ByteSortedSets.unmodifiable(this.sortedMap.keySet()); return (ByteSortedSet)this.keys; } 
/*     */     public Byte2ReferenceSortedMap<V> subMap(byte from, byte to) {
/* 307 */       return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 308 */     public Byte2ReferenceSortedMap<V> headMap(byte to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 309 */     public Byte2ReferenceSortedMap<V> tailMap(byte from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); } 
/*     */     public byte firstByteKey() {
/* 311 */       return this.sortedMap.firstByteKey(); } 
/* 312 */     public byte lastByteKey() { return this.sortedMap.lastByteKey(); }
/*     */ 
/*     */     public Byte firstKey() {
/* 315 */       return (Byte)this.sortedMap.firstKey(); } 
/* 316 */     public Byte lastKey() { return (Byte)this.sortedMap.lastKey(); } 
/*     */     public Byte2ReferenceSortedMap<V> subMap(Byte from, Byte to) {
/* 318 */       return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 319 */     public Byte2ReferenceSortedMap<V> headMap(Byte to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 320 */     public Byte2ReferenceSortedMap<V> tailMap(Byte from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedMap<V> extends Byte2ReferenceMaps.SynchronizedMap<V>
/*     */     implements Byte2ReferenceSortedMap<V>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Byte2ReferenceSortedMap<V> sortedMap;
/*     */ 
/*     */     protected SynchronizedSortedMap(Byte2ReferenceSortedMap<V> m, Object sync)
/*     */     {
/* 231 */       super(sync);
/* 232 */       this.sortedMap = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedSortedMap(Byte2ReferenceSortedMap<V> m) {
/* 236 */       super();
/* 237 */       this.sortedMap = m;
/*     */     }
/*     */     public ByteComparator comparator() {
/* 240 */       synchronized (this.sync) { return this.sortedMap.comparator(); } 
/*     */     }
/* 242 */     public ObjectSortedSet<Byte2ReferenceMap.Entry<V>> byte2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.byte2ReferenceEntrySet(), this.sync); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<Byte, V>> entrySet() {
/* 244 */       return byte2ReferenceEntrySet(); } 
/* 245 */     public ByteSortedSet keySet() { if (this.keys == null) this.keys = ByteSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (ByteSortedSet)this.keys; } 
/*     */     public Byte2ReferenceSortedMap<V> subMap(byte from, byte to) {
/* 247 */       return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 248 */     public Byte2ReferenceSortedMap<V> headMap(byte to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 249 */     public Byte2ReferenceSortedMap<V> tailMap(byte from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); } 
/*     */     public byte firstByteKey() {
/* 251 */       synchronized (this.sync) { return this.sortedMap.firstByteKey(); }  } 
/* 252 */     public byte lastByteKey() { synchronized (this.sync) { return this.sortedMap.lastByteKey(); } }
/*     */ 
/*     */     public Byte firstKey() {
/* 255 */       synchronized (this.sync) { return (Byte)this.sortedMap.firstKey(); }  } 
/* 256 */     public Byte lastKey() { synchronized (this.sync) { return (Byte)this.sortedMap.lastKey(); }  } 
/*     */     public Byte2ReferenceSortedMap<V> subMap(Byte from, Byte to) {
/* 258 */       return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 259 */     public Byte2ReferenceSortedMap<V> headMap(Byte to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 260 */     public Byte2ReferenceSortedMap<V> tailMap(Byte from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class Singleton<V> extends Byte2ReferenceMaps.Singleton<V>
/*     */     implements Byte2ReferenceSortedMap<V>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final ByteComparator comparator;
/*     */ 
/*     */     protected Singleton(byte key, V value, ByteComparator comparator)
/*     */     {
/* 119 */       super(value);
/* 120 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     protected Singleton(byte key, V value) {
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
/*     */     public ObjectSortedSet<Byte2ReferenceMap.Entry<V>> byte2ReferenceEntrySet() {
/* 135 */       if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Byte2ReferenceMaps.Singleton.SingletonEntry(this), Byte2ReferenceSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries;
/*     */     }
/* 137 */     public ObjectSortedSet<Map.Entry<Byte, V>> entrySet() { return byte2ReferenceEntrySet(); } 
/*     */     public ByteSortedSet keySet() {
/* 139 */       if (this.keys == null) this.keys = ByteSortedSets.singleton(this.key, this.comparator); return (ByteSortedSet)this.keys;
/*     */     }
/*     */     public Byte2ReferenceSortedMap<V> subMap(byte from, byte to) {
/* 142 */       if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Byte2ReferenceSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Byte2ReferenceSortedMap<V> headMap(byte to) {
/* 145 */       if (compare(this.key, to) < 0) return this; return Byte2ReferenceSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Byte2ReferenceSortedMap<V> tailMap(byte from) {
/* 148 */       if (compare(from, this.key) <= 0) return this; return Byte2ReferenceSortedMaps.EMPTY_MAP;
/*     */     }
/* 150 */     public byte firstByteKey() { return this.key; } 
/* 151 */     public byte lastByteKey() { return this.key; }
/*     */ 
/*     */     public Byte2ReferenceSortedMap<V> headMap(Byte oto) {
/* 154 */       return headMap(oto.byteValue()); } 
/* 155 */     public Byte2ReferenceSortedMap<V> tailMap(Byte ofrom) { return tailMap(ofrom.byteValue()); } 
/* 156 */     public Byte2ReferenceSortedMap<V> subMap(Byte ofrom, Byte oto) { return subMap(ofrom.byteValue(), oto.byteValue()); } 
/*     */     public Byte firstKey() {
/* 158 */       return Byte.valueOf(firstByteKey()); } 
/* 159 */     public Byte lastKey() { return Byte.valueOf(lastByteKey()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySortedMap<V> extends Byte2ReferenceMaps.EmptyMap<V>
/*     */     implements Byte2ReferenceSortedMap<V>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public ByteComparator comparator()
/*     */     {
/*  77 */       return null;
/*     */     }
/*  79 */     public ObjectSortedSet<Byte2ReferenceMap.Entry<V>> byte2ReferenceEntrySet() { return ObjectSortedSets.EMPTY_SET; } 
/*     */     public ObjectSortedSet<Map.Entry<Byte, V>> entrySet() {
/*  81 */       return ObjectSortedSets.EMPTY_SET;
/*     */     }
/*  83 */     public ByteSortedSet keySet() { return ByteSortedSets.EMPTY_SET; } 
/*     */     public Byte2ReferenceSortedMap<V> subMap(byte from, byte to) {
/*  85 */       return Byte2ReferenceSortedMaps.EMPTY_MAP;
/*     */     }
/*  87 */     public Byte2ReferenceSortedMap<V> headMap(byte to) { return Byte2ReferenceSortedMaps.EMPTY_MAP; } 
/*     */     public Byte2ReferenceSortedMap<V> tailMap(byte from) {
/*  89 */       return Byte2ReferenceSortedMaps.EMPTY_MAP; } 
/*  90 */     public byte firstByteKey() { throw new NoSuchElementException(); } 
/*  91 */     public byte lastByteKey() { throw new NoSuchElementException(); } 
/*  92 */     public Byte2ReferenceSortedMap<V> headMap(Byte oto) { return headMap(oto.byteValue()); } 
/*  93 */     public Byte2ReferenceSortedMap<V> tailMap(Byte ofrom) { return tailMap(ofrom.byteValue()); } 
/*  94 */     public Byte2ReferenceSortedMap<V> subMap(Byte ofrom, Byte oto) { return subMap(ofrom.byteValue(), oto.byteValue()); } 
/*  95 */     public Byte firstKey() { return Byte.valueOf(firstByteKey()); } 
/*  96 */     public Byte lastKey() { return Byte.valueOf(lastByteKey()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ReferenceSortedMaps
 * JD-Core Version:    0.6.2
 */