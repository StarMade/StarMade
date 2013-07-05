/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Char2DoubleSortedMaps
/*     */ {
/* 103 */   public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/*     */ 
/*     */   public static Comparator<? super Map.Entry<Character, ?>> entryComparator(CharComparator comparator)
/*     */   {
/*  64 */     return new Comparator() {
/*     */       public int compare(Map.Entry<Character, ?> x, Map.Entry<Character, ?> y) {
/*  66 */         return this.val$comparator.compare(x.getKey(), y.getKey());
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public static Char2DoubleSortedMap singleton(Character key, Double value)
/*     */   {
/* 173 */     return new Singleton(key.charValue(), value.doubleValue());
/*     */   }
/*     */ 
/*     */   public static Char2DoubleSortedMap singleton(Character key, Double value, CharComparator comparator)
/*     */   {
/* 187 */     return new Singleton(key.charValue(), value.doubleValue(), comparator);
/*     */   }
/*     */ 
/*     */   public static Char2DoubleSortedMap singleton(char key, double value)
/*     */   {
/* 202 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static Char2DoubleSortedMap singleton(char key, double value, CharComparator comparator)
/*     */   {
/* 216 */     return new Singleton(key, value, comparator);
/*     */   }
/*     */ 
/*     */   public static Char2DoubleSortedMap synchronize(Char2DoubleSortedMap m)
/*     */   {
/* 272 */     return new SynchronizedSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static Char2DoubleSortedMap synchronize(Char2DoubleSortedMap m, Object sync)
/*     */   {
/* 282 */     return new SynchronizedSortedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static Char2DoubleSortedMap unmodifiable(Char2DoubleSortedMap m)
/*     */   {
/* 332 */     return new UnmodifiableSortedMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableSortedMap extends Char2DoubleMaps.UnmodifiableMap
/*     */     implements Char2DoubleSortedMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Char2DoubleSortedMap sortedMap;
/*     */ 
/*     */     protected UnmodifiableSortedMap(Char2DoubleSortedMap m)
/*     */     {
/* 296 */       super();
/* 297 */       this.sortedMap = m;
/*     */     }
/*     */     public CharComparator comparator() {
/* 300 */       return this.sortedMap.comparator();
/*     */     }
/* 302 */     public ObjectSortedSet<Char2DoubleMap.Entry> char2DoubleEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.char2DoubleEntrySet()); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<Character, Double>> entrySet() {
/* 304 */       return char2DoubleEntrySet(); } 
/* 305 */     public CharSortedSet keySet() { if (this.keys == null) this.keys = CharSortedSets.unmodifiable(this.sortedMap.keySet()); return (CharSortedSet)this.keys; } 
/*     */     public Char2DoubleSortedMap subMap(char from, char to) {
/* 307 */       return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 308 */     public Char2DoubleSortedMap headMap(char to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 309 */     public Char2DoubleSortedMap tailMap(char from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); } 
/*     */     public char firstCharKey() {
/* 311 */       return this.sortedMap.firstCharKey(); } 
/* 312 */     public char lastCharKey() { return this.sortedMap.lastCharKey(); }
/*     */ 
/*     */     public Character firstKey() {
/* 315 */       return (Character)this.sortedMap.firstKey(); } 
/* 316 */     public Character lastKey() { return (Character)this.sortedMap.lastKey(); } 
/*     */     public Char2DoubleSortedMap subMap(Character from, Character to) {
/* 318 */       return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); } 
/* 319 */     public Char2DoubleSortedMap headMap(Character to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); } 
/* 320 */     public Char2DoubleSortedMap tailMap(Character from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedSortedMap extends Char2DoubleMaps.SynchronizedMap
/*     */     implements Char2DoubleSortedMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Char2DoubleSortedMap sortedMap;
/*     */ 
/*     */     protected SynchronizedSortedMap(Char2DoubleSortedMap m, Object sync)
/*     */     {
/* 231 */       super(sync);
/* 232 */       this.sortedMap = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedSortedMap(Char2DoubleSortedMap m) {
/* 236 */       super();
/* 237 */       this.sortedMap = m;
/*     */     }
/*     */     public CharComparator comparator() {
/* 240 */       synchronized (this.sync) { return this.sortedMap.comparator(); } 
/*     */     }
/* 242 */     public ObjectSortedSet<Char2DoubleMap.Entry> char2DoubleEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.char2DoubleEntrySet(), this.sync); return (ObjectSortedSet)this.entries; } 
/*     */     public ObjectSortedSet<Map.Entry<Character, Double>> entrySet() {
/* 244 */       return char2DoubleEntrySet(); } 
/* 245 */     public CharSortedSet keySet() { if (this.keys == null) this.keys = CharSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (CharSortedSet)this.keys; } 
/*     */     public Char2DoubleSortedMap subMap(char from, char to) {
/* 247 */       return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 248 */     public Char2DoubleSortedMap headMap(char to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 249 */     public Char2DoubleSortedMap tailMap(char from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); } 
/*     */     public char firstCharKey() {
/* 251 */       synchronized (this.sync) { return this.sortedMap.firstCharKey(); }  } 
/* 252 */     public char lastCharKey() { synchronized (this.sync) { return this.sortedMap.lastCharKey(); } }
/*     */ 
/*     */     public Character firstKey() {
/* 255 */       synchronized (this.sync) { return (Character)this.sortedMap.firstKey(); }  } 
/* 256 */     public Character lastKey() { synchronized (this.sync) { return (Character)this.sortedMap.lastKey(); }  } 
/*     */     public Char2DoubleSortedMap subMap(Character from, Character to) {
/* 258 */       return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); } 
/* 259 */     public Char2DoubleSortedMap headMap(Character to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); } 
/* 260 */     public Char2DoubleSortedMap tailMap(Character from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class Singleton extends Char2DoubleMaps.Singleton
/*     */     implements Char2DoubleSortedMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final CharComparator comparator;
/*     */ 
/*     */     protected Singleton(char key, double value, CharComparator comparator)
/*     */     {
/* 119 */       super(value);
/* 120 */       this.comparator = comparator;
/*     */     }
/*     */ 
/*     */     protected Singleton(char key, double value) {
/* 124 */       this(key, value, null);
/*     */     }
/*     */ 
/*     */     final int compare(char k1, char k2)
/*     */     {
/* 129 */       return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/*     */     }
/*     */     public CharComparator comparator() {
/* 132 */       return this.comparator;
/*     */     }
/*     */     public ObjectSortedSet<Char2DoubleMap.Entry> char2DoubleEntrySet() {
/* 135 */       if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Char2DoubleMaps.Singleton.SingletonEntry(this), Char2DoubleSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries;
/*     */     }
/* 137 */     public ObjectSortedSet<Map.Entry<Character, Double>> entrySet() { return char2DoubleEntrySet(); } 
/*     */     public CharSortedSet keySet() {
/* 139 */       if (this.keys == null) this.keys = CharSortedSets.singleton(this.key, this.comparator); return (CharSortedSet)this.keys;
/*     */     }
/*     */     public Char2DoubleSortedMap subMap(char from, char to) {
/* 142 */       if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Char2DoubleSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Char2DoubleSortedMap headMap(char to) {
/* 145 */       if (compare(this.key, to) < 0) return this; return Char2DoubleSortedMaps.EMPTY_MAP;
/*     */     }
/*     */     public Char2DoubleSortedMap tailMap(char from) {
/* 148 */       if (compare(from, this.key) <= 0) return this; return Char2DoubleSortedMaps.EMPTY_MAP;
/*     */     }
/* 150 */     public char firstCharKey() { return this.key; } 
/* 151 */     public char lastCharKey() { return this.key; }
/*     */ 
/*     */     public Char2DoubleSortedMap headMap(Character oto) {
/* 154 */       return headMap(oto.charValue()); } 
/* 155 */     public Char2DoubleSortedMap tailMap(Character ofrom) { return tailMap(ofrom.charValue()); } 
/* 156 */     public Char2DoubleSortedMap subMap(Character ofrom, Character oto) { return subMap(ofrom.charValue(), oto.charValue()); } 
/*     */     public Character firstKey() {
/* 158 */       return Character.valueOf(firstCharKey()); } 
/* 159 */     public Character lastKey() { return Character.valueOf(lastCharKey()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class EmptySortedMap extends Char2DoubleMaps.EmptyMap
/*     */     implements Char2DoubleSortedMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public CharComparator comparator()
/*     */     {
/*  78 */       return null;
/*     */     }
/*  80 */     public ObjectSortedSet<Char2DoubleMap.Entry> char2DoubleEntrySet() { return ObjectSortedSets.EMPTY_SET; } 
/*     */     public ObjectSortedSet<Map.Entry<Character, Double>> entrySet() {
/*  82 */       return ObjectSortedSets.EMPTY_SET;
/*     */     }
/*  84 */     public CharSortedSet keySet() { return CharSortedSets.EMPTY_SET; } 
/*     */     public Char2DoubleSortedMap subMap(char from, char to) {
/*  86 */       return Char2DoubleSortedMaps.EMPTY_MAP;
/*     */     }
/*  88 */     public Char2DoubleSortedMap headMap(char to) { return Char2DoubleSortedMaps.EMPTY_MAP; } 
/*     */     public Char2DoubleSortedMap tailMap(char from) {
/*  90 */       return Char2DoubleSortedMaps.EMPTY_MAP; } 
/*  91 */     public char firstCharKey() { throw new NoSuchElementException(); } 
/*  92 */     public char lastCharKey() { throw new NoSuchElementException(); } 
/*  93 */     public Char2DoubleSortedMap headMap(Character oto) { return headMap(oto.charValue()); } 
/*  94 */     public Char2DoubleSortedMap tailMap(Character ofrom) { return tailMap(ofrom.charValue()); } 
/*  95 */     public Char2DoubleSortedMap subMap(Character ofrom, Character oto) { return subMap(ofrom.charValue(), oto.charValue()); } 
/*  96 */     public Character firstKey() { return Character.valueOf(firstCharKey()); } 
/*  97 */     public Character lastKey() { return Character.valueOf(lastCharKey()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2DoubleSortedMaps
 * JD-Core Version:    0.6.2
 */