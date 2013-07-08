/*   1:    */package it.unimi.dsi.fastutil.longs;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   4:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
/*   5:    */import java.io.Serializable;
/*   6:    */import java.util.Comparator;
/*   7:    */import java.util.Map.Entry;
/*   8:    */import java.util.NoSuchElementException;
/*   9:    */
/*  60:    */public class Long2FloatSortedMaps
/*  61:    */{
/*  62:    */  public static Comparator<? super Map.Entry<Long, ?>> entryComparator(LongComparator comparator)
/*  63:    */  {
/*  64: 64 */    new Comparator() {
/*  65:    */      public int compare(Map.Entry<Long, ?> x, Map.Entry<Long, ?> y) {
/*  66: 66 */        return this.val$comparator.compare(x.getKey(), y.getKey());
/*  67:    */      }
/*  68:    */    };
/*  69:    */  }
/*  70:    */  
/*  72:    */  public static class EmptySortedMap
/*  73:    */    extends Long2FloatMaps.EmptyMap
/*  74:    */    implements Long2FloatSortedMap, Serializable, Cloneable
/*  75:    */  {
/*  76:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  77:    */    
/*  78: 78 */    public LongComparator comparator() { return null; }
/*  79:    */    
/*  80: 80 */    public ObjectSortedSet<Long2FloatMap.Entry> long2FloatEntrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  81:    */    
/*  82: 82 */    public ObjectSortedSet<Map.Entry<Long, Float>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  83:    */    
/*  84: 84 */    public LongSortedSet keySet() { return LongSortedSets.EMPTY_SET; }
/*  85:    */    
/*  86: 86 */    public Long2FloatSortedMap subMap(long from, long to) { return Long2FloatSortedMaps.EMPTY_MAP; }
/*  87:    */    
/*  88: 88 */    public Long2FloatSortedMap headMap(long to) { return Long2FloatSortedMaps.EMPTY_MAP; }
/*  89:    */    
/*  90: 90 */    public Long2FloatSortedMap tailMap(long from) { return Long2FloatSortedMaps.EMPTY_MAP; }
/*  91: 91 */    public long firstLongKey() { throw new NoSuchElementException(); }
/*  92: 92 */    public long lastLongKey() { throw new NoSuchElementException(); }
/*  93: 93 */    public Long2FloatSortedMap headMap(Long oto) { return headMap(oto.longValue()); }
/*  94: 94 */    public Long2FloatSortedMap tailMap(Long ofrom) { return tailMap(ofrom.longValue()); }
/*  95: 95 */    public Long2FloatSortedMap subMap(Long ofrom, Long oto) { return subMap(ofrom.longValue(), oto.longValue()); }
/*  96: 96 */    public Long firstKey() { return Long.valueOf(firstLongKey()); }
/*  97: 97 */    public Long lastKey() { return Long.valueOf(lastLongKey()); }
/*  98:    */  }
/*  99:    */  
/* 103:103 */  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/* 104:    */  
/* 107:    */  public static class Singleton
/* 108:    */    extends Long2FloatMaps.Singleton
/* 109:    */    implements Long2FloatSortedMap, Serializable, Cloneable
/* 110:    */  {
/* 111:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 112:    */    
/* 114:    */    protected final LongComparator comparator;
/* 115:    */    
/* 117:    */    protected Singleton(long key, float value, LongComparator comparator)
/* 118:    */    {
/* 119:119 */      super(value);
/* 120:120 */      this.comparator = comparator;
/* 121:    */    }
/* 122:    */    
/* 123:    */    protected Singleton(long key, float value) {
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
/* 134:    */    public ObjectSortedSet<Long2FloatMap.Entry> long2FloatEntrySet() {
/* 135:135 */      if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Long2FloatMaps.Singleton.SingletonEntry(this), Long2FloatSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries; }
/* 136:    */    
/* 137:137 */    public ObjectSortedSet<Map.Entry<Long, Float>> entrySet() { return long2FloatEntrySet(); }
/* 138:    */    
/* 139:139 */    public LongSortedSet keySet() { if (this.keys == null) this.keys = LongSortedSets.singleton(this.key, this.comparator); return (LongSortedSet)this.keys;
/* 140:    */    }
/* 141:    */    
/* 142:142 */    public Long2FloatSortedMap subMap(long from, long to) { if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Long2FloatSortedMaps.EMPTY_MAP;
/* 143:    */    }
/* 144:    */    
/* 145:145 */    public Long2FloatSortedMap headMap(long to) { if (compare(this.key, to) < 0) return this; return Long2FloatSortedMaps.EMPTY_MAP;
/* 146:    */    }
/* 147:    */    
/* 148:148 */    public Long2FloatSortedMap tailMap(long from) { if (compare(from, this.key) <= 0) return this; return Long2FloatSortedMaps.EMPTY_MAP; }
/* 149:    */    
/* 150:150 */    public long firstLongKey() { return this.key; }
/* 151:151 */    public long lastLongKey() { return this.key; }
/* 152:    */    
/* 154:154 */    public Long2FloatSortedMap headMap(Long oto) { return headMap(oto.longValue()); }
/* 155:155 */    public Long2FloatSortedMap tailMap(Long ofrom) { return tailMap(ofrom.longValue()); }
/* 156:156 */    public Long2FloatSortedMap subMap(Long ofrom, Long oto) { return subMap(ofrom.longValue(), oto.longValue()); }
/* 157:    */    
/* 158:158 */    public Long firstKey() { return Long.valueOf(firstLongKey()); }
/* 159:159 */    public Long lastKey() { return Long.valueOf(lastLongKey()); }
/* 160:    */  }
/* 161:    */  
/* 171:    */  public static Long2FloatSortedMap singleton(Long key, Float value)
/* 172:    */  {
/* 173:173 */    return new Singleton(key.longValue(), value.floatValue());
/* 174:    */  }
/* 175:    */  
/* 185:    */  public static Long2FloatSortedMap singleton(Long key, Float value, LongComparator comparator)
/* 186:    */  {
/* 187:187 */    return new Singleton(key.longValue(), value.floatValue(), comparator);
/* 188:    */  }
/* 189:    */  
/* 200:    */  public static Long2FloatSortedMap singleton(long key, float value)
/* 201:    */  {
/* 202:202 */    return new Singleton(key, value);
/* 203:    */  }
/* 204:    */  
/* 214:    */  public static Long2FloatSortedMap singleton(long key, float value, LongComparator comparator)
/* 215:    */  {
/* 216:216 */    return new Singleton(key, value, comparator);
/* 217:    */  }
/* 218:    */  
/* 220:    */  public static class SynchronizedSortedMap
/* 221:    */    extends Long2FloatMaps.SynchronizedMap
/* 222:    */    implements Long2FloatSortedMap, Serializable
/* 223:    */  {
/* 224:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 225:    */    
/* 226:    */    protected final Long2FloatSortedMap sortedMap;
/* 227:    */    
/* 229:    */    protected SynchronizedSortedMap(Long2FloatSortedMap m, Object sync)
/* 230:    */    {
/* 231:231 */      super(sync);
/* 232:232 */      this.sortedMap = m;
/* 233:    */    }
/* 234:    */    
/* 235:    */    protected SynchronizedSortedMap(Long2FloatSortedMap m) {
/* 236:236 */      super();
/* 237:237 */      this.sortedMap = m;
/* 238:    */    }
/* 239:    */    
/* 240:240 */    public LongComparator comparator() { synchronized (this.sync) { return this.sortedMap.comparator(); } }
/* 241:    */    
/* 242:242 */    public ObjectSortedSet<Long2FloatMap.Entry> long2FloatEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.long2FloatEntrySet(), this.sync); return (ObjectSortedSet)this.entries; }
/* 243:    */    
/* 244:244 */    public ObjectSortedSet<Map.Entry<Long, Float>> entrySet() { return long2FloatEntrySet(); }
/* 245:245 */    public LongSortedSet keySet() { if (this.keys == null) this.keys = LongSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (LongSortedSet)this.keys; }
/* 246:    */    
/* 247:247 */    public Long2FloatSortedMap subMap(long from, long to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 248:248 */    public Long2FloatSortedMap headMap(long to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 249:249 */    public Long2FloatSortedMap tailMap(long from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 250:    */    
/* 251:251 */    public long firstLongKey() { synchronized (this.sync) { return this.sortedMap.firstLongKey(); } }
/* 252:252 */    public long lastLongKey() { synchronized (this.sync) { return this.sortedMap.lastLongKey();
/* 253:    */      } }
/* 254:    */    
/* 255:255 */    public Long firstKey() { synchronized (this.sync) { return (Long)this.sortedMap.firstKey(); } }
/* 256:256 */    public Long lastKey() { synchronized (this.sync) { return (Long)this.sortedMap.lastKey(); } }
/* 257:    */    
/* 258:258 */    public Long2FloatSortedMap subMap(Long from, Long to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 259:259 */    public Long2FloatSortedMap headMap(Long to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 260:260 */    public Long2FloatSortedMap tailMap(Long from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 261:    */  }
/* 262:    */  
/* 270:    */  public static Long2FloatSortedMap synchronize(Long2FloatSortedMap m)
/* 271:    */  {
/* 272:272 */    return new SynchronizedSortedMap(m);
/* 273:    */  }
/* 274:    */  
/* 280:    */  public static Long2FloatSortedMap synchronize(Long2FloatSortedMap m, Object sync)
/* 281:    */  {
/* 282:282 */    return new SynchronizedSortedMap(m, sync);
/* 283:    */  }
/* 284:    */  
/* 286:    */  public static class UnmodifiableSortedMap
/* 287:    */    extends Long2FloatMaps.UnmodifiableMap
/* 288:    */    implements Long2FloatSortedMap, Serializable
/* 289:    */  {
/* 290:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 291:    */    
/* 292:    */    protected final Long2FloatSortedMap sortedMap;
/* 293:    */    
/* 294:    */    protected UnmodifiableSortedMap(Long2FloatSortedMap m)
/* 295:    */    {
/* 296:296 */      super();
/* 297:297 */      this.sortedMap = m;
/* 298:    */    }
/* 299:    */    
/* 300:300 */    public LongComparator comparator() { return this.sortedMap.comparator(); }
/* 301:    */    
/* 302:302 */    public ObjectSortedSet<Long2FloatMap.Entry> long2FloatEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.long2FloatEntrySet()); return (ObjectSortedSet)this.entries; }
/* 303:    */    
/* 304:304 */    public ObjectSortedSet<Map.Entry<Long, Float>> entrySet() { return long2FloatEntrySet(); }
/* 305:305 */    public LongSortedSet keySet() { if (this.keys == null) this.keys = LongSortedSets.unmodifiable(this.sortedMap.keySet()); return (LongSortedSet)this.keys; }
/* 306:    */    
/* 307:307 */    public Long2FloatSortedMap subMap(long from, long to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 308:308 */    public Long2FloatSortedMap headMap(long to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 309:309 */    public Long2FloatSortedMap tailMap(long from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 310:    */    
/* 311:311 */    public long firstLongKey() { return this.sortedMap.firstLongKey(); }
/* 312:312 */    public long lastLongKey() { return this.sortedMap.lastLongKey(); }
/* 313:    */    
/* 315:315 */    public Long firstKey() { return (Long)this.sortedMap.firstKey(); }
/* 316:316 */    public Long lastKey() { return (Long)this.sortedMap.lastKey(); }
/* 317:    */    
/* 318:318 */    public Long2FloatSortedMap subMap(Long from, Long to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 319:319 */    public Long2FloatSortedMap headMap(Long to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 320:320 */    public Long2FloatSortedMap tailMap(Long from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 321:    */  }
/* 322:    */  
/* 330:    */  public static Long2FloatSortedMap unmodifiable(Long2FloatSortedMap m)
/* 331:    */  {
/* 332:332 */    return new UnmodifiableSortedMap(m);
/* 333:    */  }
/* 334:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2FloatSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */