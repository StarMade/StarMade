/*   1:    */package it.unimi.dsi.fastutil.shorts;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   4:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
/*   5:    */import java.io.Serializable;
/*   6:    */import java.util.Comparator;
/*   7:    */import java.util.Map.Entry;
/*   8:    */import java.util.NoSuchElementException;
/*   9:    */
/*  59:    */public class Short2ReferenceSortedMaps
/*  60:    */{
/*  61:    */  public static Comparator<? super Map.Entry<Short, ?>> entryComparator(ShortComparator comparator)
/*  62:    */  {
/*  63: 63 */    new Comparator() {
/*  64:    */      public int compare(Map.Entry<Short, ?> x, Map.Entry<Short, ?> y) {
/*  65: 65 */        return this.val$comparator.compare(x.getKey(), y.getKey());
/*  66:    */      }
/*  67:    */    };
/*  68:    */  }
/*  69:    */  
/*  71:    */  public static class EmptySortedMap<V>
/*  72:    */    extends Short2ReferenceMaps.EmptyMap<V>
/*  73:    */    implements Short2ReferenceSortedMap<V>, Serializable, Cloneable
/*  74:    */  {
/*  75:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  76:    */    
/*  77: 77 */    public ShortComparator comparator() { return null; }
/*  78:    */    
/*  79: 79 */    public ObjectSortedSet<Short2ReferenceMap.Entry<V>> short2ReferenceEntrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  80:    */    
/*  81: 81 */    public ObjectSortedSet<Map.Entry<Short, V>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  82:    */    
/*  83: 83 */    public ShortSortedSet keySet() { return ShortSortedSets.EMPTY_SET; }
/*  84:    */    
/*  85: 85 */    public Short2ReferenceSortedMap<V> subMap(short from, short to) { return Short2ReferenceSortedMaps.EMPTY_MAP; }
/*  86:    */    
/*  87: 87 */    public Short2ReferenceSortedMap<V> headMap(short to) { return Short2ReferenceSortedMaps.EMPTY_MAP; }
/*  88:    */    
/*  89: 89 */    public Short2ReferenceSortedMap<V> tailMap(short from) { return Short2ReferenceSortedMaps.EMPTY_MAP; }
/*  90: 90 */    public short firstShortKey() { throw new NoSuchElementException(); }
/*  91: 91 */    public short lastShortKey() { throw new NoSuchElementException(); }
/*  92: 92 */    public Short2ReferenceSortedMap<V> headMap(Short oto) { return headMap(oto.shortValue()); }
/*  93: 93 */    public Short2ReferenceSortedMap<V> tailMap(Short ofrom) { return tailMap(ofrom.shortValue()); }
/*  94: 94 */    public Short2ReferenceSortedMap<V> subMap(Short ofrom, Short oto) { return subMap(ofrom.shortValue(), oto.shortValue()); }
/*  95: 95 */    public Short firstKey() { return Short.valueOf(firstShortKey()); }
/*  96: 96 */    public Short lastKey() { return Short.valueOf(lastShortKey()); }
/*  97:    */  }
/*  98:    */  
/* 103:103 */  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/* 104:    */  
/* 107:    */  public static class Singleton<V>
/* 108:    */    extends Short2ReferenceMaps.Singleton<V>
/* 109:    */    implements Short2ReferenceSortedMap<V>, Serializable, Cloneable
/* 110:    */  {
/* 111:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 112:    */    
/* 114:    */    protected final ShortComparator comparator;
/* 115:    */    
/* 117:    */    protected Singleton(short key, V value, ShortComparator comparator)
/* 118:    */    {
/* 119:119 */      super(value);
/* 120:120 */      this.comparator = comparator;
/* 121:    */    }
/* 122:    */    
/* 123:    */    protected Singleton(short key, V value) {
/* 124:124 */      this(key, value, null);
/* 125:    */    }
/* 126:    */    
/* 127:    */    final int compare(short k1, short k2)
/* 128:    */    {
/* 129:129 */      return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/* 130:    */    }
/* 131:    */    
/* 132:132 */    public ShortComparator comparator() { return this.comparator; }
/* 133:    */    
/* 134:    */    public ObjectSortedSet<Short2ReferenceMap.Entry<V>> short2ReferenceEntrySet() {
/* 135:135 */      if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Short2ReferenceMaps.Singleton.SingletonEntry(this), Short2ReferenceSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries; }
/* 136:    */    
/* 137:137 */    public ObjectSortedSet<Map.Entry<Short, V>> entrySet() { return short2ReferenceEntrySet(); }
/* 138:    */    
/* 139:139 */    public ShortSortedSet keySet() { if (this.keys == null) this.keys = ShortSortedSets.singleton(this.key, this.comparator); return (ShortSortedSet)this.keys;
/* 140:    */    }
/* 141:    */    
/* 142:142 */    public Short2ReferenceSortedMap<V> subMap(short from, short to) { if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Short2ReferenceSortedMaps.EMPTY_MAP;
/* 143:    */    }
/* 144:    */    
/* 145:145 */    public Short2ReferenceSortedMap<V> headMap(short to) { if (compare(this.key, to) < 0) return this; return Short2ReferenceSortedMaps.EMPTY_MAP;
/* 146:    */    }
/* 147:    */    
/* 148:148 */    public Short2ReferenceSortedMap<V> tailMap(short from) { if (compare(from, this.key) <= 0) return this; return Short2ReferenceSortedMaps.EMPTY_MAP; }
/* 149:    */    
/* 150:150 */    public short firstShortKey() { return this.key; }
/* 151:151 */    public short lastShortKey() { return this.key; }
/* 152:    */    
/* 154:154 */    public Short2ReferenceSortedMap<V> headMap(Short oto) { return headMap(oto.shortValue()); }
/* 155:155 */    public Short2ReferenceSortedMap<V> tailMap(Short ofrom) { return tailMap(ofrom.shortValue()); }
/* 156:156 */    public Short2ReferenceSortedMap<V> subMap(Short ofrom, Short oto) { return subMap(ofrom.shortValue(), oto.shortValue()); }
/* 157:    */    
/* 158:158 */    public Short firstKey() { return Short.valueOf(firstShortKey()); }
/* 159:159 */    public Short lastKey() { return Short.valueOf(lastShortKey()); }
/* 160:    */  }
/* 161:    */  
/* 171:    */  public static <V> Short2ReferenceSortedMap<V> singleton(Short key, V value)
/* 172:    */  {
/* 173:173 */    return new Singleton(key.shortValue(), value);
/* 174:    */  }
/* 175:    */  
/* 185:    */  public static <V> Short2ReferenceSortedMap<V> singleton(Short key, V value, ShortComparator comparator)
/* 186:    */  {
/* 187:187 */    return new Singleton(key.shortValue(), value, comparator);
/* 188:    */  }
/* 189:    */  
/* 200:    */  public static <V> Short2ReferenceSortedMap<V> singleton(short key, V value)
/* 201:    */  {
/* 202:202 */    return new Singleton(key, value);
/* 203:    */  }
/* 204:    */  
/* 214:    */  public static <V> Short2ReferenceSortedMap<V> singleton(short key, V value, ShortComparator comparator)
/* 215:    */  {
/* 216:216 */    return new Singleton(key, value, comparator);
/* 217:    */  }
/* 218:    */  
/* 220:    */  public static class SynchronizedSortedMap<V>
/* 221:    */    extends Short2ReferenceMaps.SynchronizedMap<V>
/* 222:    */    implements Short2ReferenceSortedMap<V>, Serializable
/* 223:    */  {
/* 224:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 225:    */    
/* 226:    */    protected final Short2ReferenceSortedMap<V> sortedMap;
/* 227:    */    
/* 229:    */    protected SynchronizedSortedMap(Short2ReferenceSortedMap<V> m, Object sync)
/* 230:    */    {
/* 231:231 */      super(sync);
/* 232:232 */      this.sortedMap = m;
/* 233:    */    }
/* 234:    */    
/* 235:    */    protected SynchronizedSortedMap(Short2ReferenceSortedMap<V> m) {
/* 236:236 */      super();
/* 237:237 */      this.sortedMap = m;
/* 238:    */    }
/* 239:    */    
/* 240:240 */    public ShortComparator comparator() { synchronized (this.sync) { return this.sortedMap.comparator(); } }
/* 241:    */    
/* 242:242 */    public ObjectSortedSet<Short2ReferenceMap.Entry<V>> short2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.short2ReferenceEntrySet(), this.sync); return (ObjectSortedSet)this.entries; }
/* 243:    */    
/* 244:244 */    public ObjectSortedSet<Map.Entry<Short, V>> entrySet() { return short2ReferenceEntrySet(); }
/* 245:245 */    public ShortSortedSet keySet() { if (this.keys == null) this.keys = ShortSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (ShortSortedSet)this.keys; }
/* 246:    */    
/* 247:247 */    public Short2ReferenceSortedMap<V> subMap(short from, short to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 248:248 */    public Short2ReferenceSortedMap<V> headMap(short to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 249:249 */    public Short2ReferenceSortedMap<V> tailMap(short from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 250:    */    
/* 251:251 */    public short firstShortKey() { synchronized (this.sync) { return this.sortedMap.firstShortKey(); } }
/* 252:252 */    public short lastShortKey() { synchronized (this.sync) { return this.sortedMap.lastShortKey();
/* 253:    */      } }
/* 254:    */    
/* 255:255 */    public Short firstKey() { synchronized (this.sync) { return (Short)this.sortedMap.firstKey(); } }
/* 256:256 */    public Short lastKey() { synchronized (this.sync) { return (Short)this.sortedMap.lastKey(); } }
/* 257:    */    
/* 258:258 */    public Short2ReferenceSortedMap<V> subMap(Short from, Short to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 259:259 */    public Short2ReferenceSortedMap<V> headMap(Short to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 260:260 */    public Short2ReferenceSortedMap<V> tailMap(Short from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 261:    */  }
/* 262:    */  
/* 270:    */  public static <V> Short2ReferenceSortedMap<V> synchronize(Short2ReferenceSortedMap<V> m)
/* 271:    */  {
/* 272:272 */    return new SynchronizedSortedMap(m);
/* 273:    */  }
/* 274:    */  
/* 280:    */  public static <V> Short2ReferenceSortedMap<V> synchronize(Short2ReferenceSortedMap<V> m, Object sync)
/* 281:    */  {
/* 282:282 */    return new SynchronizedSortedMap(m, sync);
/* 283:    */  }
/* 284:    */  
/* 286:    */  public static class UnmodifiableSortedMap<V>
/* 287:    */    extends Short2ReferenceMaps.UnmodifiableMap<V>
/* 288:    */    implements Short2ReferenceSortedMap<V>, Serializable
/* 289:    */  {
/* 290:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 291:    */    
/* 292:    */    protected final Short2ReferenceSortedMap<V> sortedMap;
/* 293:    */    
/* 294:    */    protected UnmodifiableSortedMap(Short2ReferenceSortedMap<V> m)
/* 295:    */    {
/* 296:296 */      super();
/* 297:297 */      this.sortedMap = m;
/* 298:    */    }
/* 299:    */    
/* 300:300 */    public ShortComparator comparator() { return this.sortedMap.comparator(); }
/* 301:    */    
/* 302:302 */    public ObjectSortedSet<Short2ReferenceMap.Entry<V>> short2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.short2ReferenceEntrySet()); return (ObjectSortedSet)this.entries; }
/* 303:    */    
/* 304:304 */    public ObjectSortedSet<Map.Entry<Short, V>> entrySet() { return short2ReferenceEntrySet(); }
/* 305:305 */    public ShortSortedSet keySet() { if (this.keys == null) this.keys = ShortSortedSets.unmodifiable(this.sortedMap.keySet()); return (ShortSortedSet)this.keys; }
/* 306:    */    
/* 307:307 */    public Short2ReferenceSortedMap<V> subMap(short from, short to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 308:308 */    public Short2ReferenceSortedMap<V> headMap(short to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 309:309 */    public Short2ReferenceSortedMap<V> tailMap(short from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 310:    */    
/* 311:311 */    public short firstShortKey() { return this.sortedMap.firstShortKey(); }
/* 312:312 */    public short lastShortKey() { return this.sortedMap.lastShortKey(); }
/* 313:    */    
/* 315:315 */    public Short firstKey() { return (Short)this.sortedMap.firstKey(); }
/* 316:316 */    public Short lastKey() { return (Short)this.sortedMap.lastKey(); }
/* 317:    */    
/* 318:318 */    public Short2ReferenceSortedMap<V> subMap(Short from, Short to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 319:319 */    public Short2ReferenceSortedMap<V> headMap(Short to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 320:320 */    public Short2ReferenceSortedMap<V> tailMap(Short from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 321:    */  }
/* 322:    */  
/* 330:    */  public static <V> Short2ReferenceSortedMap<V> unmodifiable(Short2ReferenceSortedMap<V> m)
/* 331:    */  {
/* 332:332 */    return new UnmodifiableSortedMap(m);
/* 333:    */  }
/* 334:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ReferenceSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */