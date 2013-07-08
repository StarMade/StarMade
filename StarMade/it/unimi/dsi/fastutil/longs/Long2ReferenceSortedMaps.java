/*   1:    */package it.unimi.dsi.fastutil.longs;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   4:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
/*   5:    */import java.io.Serializable;
/*   6:    */import java.util.Comparator;
/*   7:    */import java.util.Map.Entry;
/*   8:    */import java.util.NoSuchElementException;
/*   9:    */
/*  59:    */public class Long2ReferenceSortedMaps
/*  60:    */{
/*  61:    */  public static Comparator<? super Map.Entry<Long, ?>> entryComparator(LongComparator comparator)
/*  62:    */  {
/*  63: 63 */    new Comparator() {
/*  64:    */      public int compare(Map.Entry<Long, ?> x, Map.Entry<Long, ?> y) {
/*  65: 65 */        return this.val$comparator.compare(x.getKey(), y.getKey());
/*  66:    */      }
/*  67:    */    };
/*  68:    */  }
/*  69:    */  
/*  71:    */  public static class EmptySortedMap<V>
/*  72:    */    extends Long2ReferenceMaps.EmptyMap<V>
/*  73:    */    implements Long2ReferenceSortedMap<V>, Serializable, Cloneable
/*  74:    */  {
/*  75:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  76:    */    
/*  77: 77 */    public LongComparator comparator() { return null; }
/*  78:    */    
/*  79: 79 */    public ObjectSortedSet<Long2ReferenceMap.Entry<V>> long2ReferenceEntrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  80:    */    
/*  81: 81 */    public ObjectSortedSet<Map.Entry<Long, V>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  82:    */    
/*  83: 83 */    public LongSortedSet keySet() { return LongSortedSets.EMPTY_SET; }
/*  84:    */    
/*  85: 85 */    public Long2ReferenceSortedMap<V> subMap(long from, long to) { return Long2ReferenceSortedMaps.EMPTY_MAP; }
/*  86:    */    
/*  87: 87 */    public Long2ReferenceSortedMap<V> headMap(long to) { return Long2ReferenceSortedMaps.EMPTY_MAP; }
/*  88:    */    
/*  89: 89 */    public Long2ReferenceSortedMap<V> tailMap(long from) { return Long2ReferenceSortedMaps.EMPTY_MAP; }
/*  90: 90 */    public long firstLongKey() { throw new NoSuchElementException(); }
/*  91: 91 */    public long lastLongKey() { throw new NoSuchElementException(); }
/*  92: 92 */    public Long2ReferenceSortedMap<V> headMap(Long oto) { return headMap(oto.longValue()); }
/*  93: 93 */    public Long2ReferenceSortedMap<V> tailMap(Long ofrom) { return tailMap(ofrom.longValue()); }
/*  94: 94 */    public Long2ReferenceSortedMap<V> subMap(Long ofrom, Long oto) { return subMap(ofrom.longValue(), oto.longValue()); }
/*  95: 95 */    public Long firstKey() { return Long.valueOf(firstLongKey()); }
/*  96: 96 */    public Long lastKey() { return Long.valueOf(lastLongKey()); }
/*  97:    */  }
/*  98:    */  
/* 103:103 */  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/* 104:    */  
/* 107:    */  public static class Singleton<V>
/* 108:    */    extends Long2ReferenceMaps.Singleton<V>
/* 109:    */    implements Long2ReferenceSortedMap<V>, Serializable, Cloneable
/* 110:    */  {
/* 111:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 112:    */    
/* 114:    */    protected final LongComparator comparator;
/* 115:    */    
/* 117:    */    protected Singleton(long key, V value, LongComparator comparator)
/* 118:    */    {
/* 119:119 */      super(value);
/* 120:120 */      this.comparator = comparator;
/* 121:    */    }
/* 122:    */    
/* 123:    */    protected Singleton(long key, V value) {
/* 124:124 */      this(key, value, null);
/* 125:    */    }
/* 126:    */    
/* 127:    */    final int compare(long k1, long k2)
/* 128:    */    {
/* 129:129 */      return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/* 130:    */    }
/* 131:    */    
/* 132:132 */    public LongComparator comparator() { return this.comparator; }
/* 133:    */    
/* 134:    */    public ObjectSortedSet<Long2ReferenceMap.Entry<V>> long2ReferenceEntrySet() {
/* 135:135 */      if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Long2ReferenceMaps.Singleton.SingletonEntry(this), Long2ReferenceSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries; }
/* 136:    */    
/* 137:137 */    public ObjectSortedSet<Map.Entry<Long, V>> entrySet() { return long2ReferenceEntrySet(); }
/* 138:    */    
/* 139:139 */    public LongSortedSet keySet() { if (this.keys == null) this.keys = LongSortedSets.singleton(this.key, this.comparator); return (LongSortedSet)this.keys;
/* 140:    */    }
/* 141:    */    
/* 142:142 */    public Long2ReferenceSortedMap<V> subMap(long from, long to) { if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Long2ReferenceSortedMaps.EMPTY_MAP;
/* 143:    */    }
/* 144:    */    
/* 145:145 */    public Long2ReferenceSortedMap<V> headMap(long to) { if (compare(this.key, to) < 0) return this; return Long2ReferenceSortedMaps.EMPTY_MAP;
/* 146:    */    }
/* 147:    */    
/* 148:148 */    public Long2ReferenceSortedMap<V> tailMap(long from) { if (compare(from, this.key) <= 0) return this; return Long2ReferenceSortedMaps.EMPTY_MAP; }
/* 149:    */    
/* 150:150 */    public long firstLongKey() { return this.key; }
/* 151:151 */    public long lastLongKey() { return this.key; }
/* 152:    */    
/* 154:154 */    public Long2ReferenceSortedMap<V> headMap(Long oto) { return headMap(oto.longValue()); }
/* 155:155 */    public Long2ReferenceSortedMap<V> tailMap(Long ofrom) { return tailMap(ofrom.longValue()); }
/* 156:156 */    public Long2ReferenceSortedMap<V> subMap(Long ofrom, Long oto) { return subMap(ofrom.longValue(), oto.longValue()); }
/* 157:    */    
/* 158:158 */    public Long firstKey() { return Long.valueOf(firstLongKey()); }
/* 159:159 */    public Long lastKey() { return Long.valueOf(lastLongKey()); }
/* 160:    */  }
/* 161:    */  
/* 171:    */  public static <V> Long2ReferenceSortedMap<V> singleton(Long key, V value)
/* 172:    */  {
/* 173:173 */    return new Singleton(key.longValue(), value);
/* 174:    */  }
/* 175:    */  
/* 185:    */  public static <V> Long2ReferenceSortedMap<V> singleton(Long key, V value, LongComparator comparator)
/* 186:    */  {
/* 187:187 */    return new Singleton(key.longValue(), value, comparator);
/* 188:    */  }
/* 189:    */  
/* 200:    */  public static <V> Long2ReferenceSortedMap<V> singleton(long key, V value)
/* 201:    */  {
/* 202:202 */    return new Singleton(key, value);
/* 203:    */  }
/* 204:    */  
/* 214:    */  public static <V> Long2ReferenceSortedMap<V> singleton(long key, V value, LongComparator comparator)
/* 215:    */  {
/* 216:216 */    return new Singleton(key, value, comparator);
/* 217:    */  }
/* 218:    */  
/* 220:    */  public static class SynchronizedSortedMap<V>
/* 221:    */    extends Long2ReferenceMaps.SynchronizedMap<V>
/* 222:    */    implements Long2ReferenceSortedMap<V>, Serializable
/* 223:    */  {
/* 224:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 225:    */    
/* 226:    */    protected final Long2ReferenceSortedMap<V> sortedMap;
/* 227:    */    
/* 229:    */    protected SynchronizedSortedMap(Long2ReferenceSortedMap<V> m, Object sync)
/* 230:    */    {
/* 231:231 */      super(sync);
/* 232:232 */      this.sortedMap = m;
/* 233:    */    }
/* 234:    */    
/* 235:    */    protected SynchronizedSortedMap(Long2ReferenceSortedMap<V> m) {
/* 236:236 */      super();
/* 237:237 */      this.sortedMap = m;
/* 238:    */    }
/* 239:    */    
/* 240:240 */    public LongComparator comparator() { synchronized (this.sync) { return this.sortedMap.comparator(); } }
/* 241:    */    
/* 242:242 */    public ObjectSortedSet<Long2ReferenceMap.Entry<V>> long2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.long2ReferenceEntrySet(), this.sync); return (ObjectSortedSet)this.entries; }
/* 243:    */    
/* 244:244 */    public ObjectSortedSet<Map.Entry<Long, V>> entrySet() { return long2ReferenceEntrySet(); }
/* 245:245 */    public LongSortedSet keySet() { if (this.keys == null) this.keys = LongSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (LongSortedSet)this.keys; }
/* 246:    */    
/* 247:247 */    public Long2ReferenceSortedMap<V> subMap(long from, long to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 248:248 */    public Long2ReferenceSortedMap<V> headMap(long to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 249:249 */    public Long2ReferenceSortedMap<V> tailMap(long from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 250:    */    
/* 251:251 */    public long firstLongKey() { synchronized (this.sync) { return this.sortedMap.firstLongKey(); } }
/* 252:252 */    public long lastLongKey() { synchronized (this.sync) { return this.sortedMap.lastLongKey();
/* 253:    */      } }
/* 254:    */    
/* 255:255 */    public Long firstKey() { synchronized (this.sync) { return (Long)this.sortedMap.firstKey(); } }
/* 256:256 */    public Long lastKey() { synchronized (this.sync) { return (Long)this.sortedMap.lastKey(); } }
/* 257:    */    
/* 258:258 */    public Long2ReferenceSortedMap<V> subMap(Long from, Long to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 259:259 */    public Long2ReferenceSortedMap<V> headMap(Long to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 260:260 */    public Long2ReferenceSortedMap<V> tailMap(Long from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 261:    */  }
/* 262:    */  
/* 270:    */  public static <V> Long2ReferenceSortedMap<V> synchronize(Long2ReferenceSortedMap<V> m)
/* 271:    */  {
/* 272:272 */    return new SynchronizedSortedMap(m);
/* 273:    */  }
/* 274:    */  
/* 280:    */  public static <V> Long2ReferenceSortedMap<V> synchronize(Long2ReferenceSortedMap<V> m, Object sync)
/* 281:    */  {
/* 282:282 */    return new SynchronizedSortedMap(m, sync);
/* 283:    */  }
/* 284:    */  
/* 286:    */  public static class UnmodifiableSortedMap<V>
/* 287:    */    extends Long2ReferenceMaps.UnmodifiableMap<V>
/* 288:    */    implements Long2ReferenceSortedMap<V>, Serializable
/* 289:    */  {
/* 290:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 291:    */    
/* 292:    */    protected final Long2ReferenceSortedMap<V> sortedMap;
/* 293:    */    
/* 294:    */    protected UnmodifiableSortedMap(Long2ReferenceSortedMap<V> m)
/* 295:    */    {
/* 296:296 */      super();
/* 297:297 */      this.sortedMap = m;
/* 298:    */    }
/* 299:    */    
/* 300:300 */    public LongComparator comparator() { return this.sortedMap.comparator(); }
/* 301:    */    
/* 302:302 */    public ObjectSortedSet<Long2ReferenceMap.Entry<V>> long2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.long2ReferenceEntrySet()); return (ObjectSortedSet)this.entries; }
/* 303:    */    
/* 304:304 */    public ObjectSortedSet<Map.Entry<Long, V>> entrySet() { return long2ReferenceEntrySet(); }
/* 305:305 */    public LongSortedSet keySet() { if (this.keys == null) this.keys = LongSortedSets.unmodifiable(this.sortedMap.keySet()); return (LongSortedSet)this.keys; }
/* 306:    */    
/* 307:307 */    public Long2ReferenceSortedMap<V> subMap(long from, long to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 308:308 */    public Long2ReferenceSortedMap<V> headMap(long to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 309:309 */    public Long2ReferenceSortedMap<V> tailMap(long from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 310:    */    
/* 311:311 */    public long firstLongKey() { return this.sortedMap.firstLongKey(); }
/* 312:312 */    public long lastLongKey() { return this.sortedMap.lastLongKey(); }
/* 313:    */    
/* 315:315 */    public Long firstKey() { return (Long)this.sortedMap.firstKey(); }
/* 316:316 */    public Long lastKey() { return (Long)this.sortedMap.lastKey(); }
/* 317:    */    
/* 318:318 */    public Long2ReferenceSortedMap<V> subMap(Long from, Long to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 319:319 */    public Long2ReferenceSortedMap<V> headMap(Long to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 320:320 */    public Long2ReferenceSortedMap<V> tailMap(Long from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 321:    */  }
/* 322:    */  
/* 330:    */  public static <V> Long2ReferenceSortedMap<V> unmodifiable(Long2ReferenceSortedMap<V> m)
/* 331:    */  {
/* 332:332 */    return new UnmodifiableSortedMap(m);
/* 333:    */  }
/* 334:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ReferenceSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */