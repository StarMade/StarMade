/*   1:    */package it.unimi.dsi.fastutil.bytes;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   4:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
/*   5:    */import java.io.Serializable;
/*   6:    */import java.util.Comparator;
/*   7:    */import java.util.Map.Entry;
/*   8:    */import java.util.NoSuchElementException;
/*   9:    */
/*  59:    */public class Byte2ObjectSortedMaps
/*  60:    */{
/*  61:    */  public static Comparator<? super Map.Entry<Byte, ?>> entryComparator(ByteComparator comparator)
/*  62:    */  {
/*  63: 63 */    new Comparator() {
/*  64:    */      public int compare(Map.Entry<Byte, ?> x, Map.Entry<Byte, ?> y) {
/*  65: 65 */        return this.val$comparator.compare(x.getKey(), y.getKey());
/*  66:    */      }
/*  67:    */    };
/*  68:    */  }
/*  69:    */  
/*  71:    */  public static class EmptySortedMap<V>
/*  72:    */    extends Byte2ObjectMaps.EmptyMap<V>
/*  73:    */    implements Byte2ObjectSortedMap<V>, Serializable, Cloneable
/*  74:    */  {
/*  75:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  76:    */    
/*  77: 77 */    public ByteComparator comparator() { return null; }
/*  78:    */    
/*  79: 79 */    public ObjectSortedSet<Byte2ObjectMap.Entry<V>> byte2ObjectEntrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  80:    */    
/*  81: 81 */    public ObjectSortedSet<Map.Entry<Byte, V>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  82:    */    
/*  83: 83 */    public ByteSortedSet keySet() { return ByteSortedSets.EMPTY_SET; }
/*  84:    */    
/*  85: 85 */    public Byte2ObjectSortedMap<V> subMap(byte from, byte to) { return Byte2ObjectSortedMaps.EMPTY_MAP; }
/*  86:    */    
/*  87: 87 */    public Byte2ObjectSortedMap<V> headMap(byte to) { return Byte2ObjectSortedMaps.EMPTY_MAP; }
/*  88:    */    
/*  89: 89 */    public Byte2ObjectSortedMap<V> tailMap(byte from) { return Byte2ObjectSortedMaps.EMPTY_MAP; }
/*  90: 90 */    public byte firstByteKey() { throw new NoSuchElementException(); }
/*  91: 91 */    public byte lastByteKey() { throw new NoSuchElementException(); }
/*  92: 92 */    public Byte2ObjectSortedMap<V> headMap(Byte oto) { return headMap(oto.byteValue()); }
/*  93: 93 */    public Byte2ObjectSortedMap<V> tailMap(Byte ofrom) { return tailMap(ofrom.byteValue()); }
/*  94: 94 */    public Byte2ObjectSortedMap<V> subMap(Byte ofrom, Byte oto) { return subMap(ofrom.byteValue(), oto.byteValue()); }
/*  95: 95 */    public Byte firstKey() { return Byte.valueOf(firstByteKey()); }
/*  96: 96 */    public Byte lastKey() { return Byte.valueOf(lastByteKey()); }
/*  97:    */  }
/*  98:    */  
/* 103:103 */  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/* 104:    */  
/* 107:    */  public static class Singleton<V>
/* 108:    */    extends Byte2ObjectMaps.Singleton<V>
/* 109:    */    implements Byte2ObjectSortedMap<V>, Serializable, Cloneable
/* 110:    */  {
/* 111:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 112:    */    
/* 114:    */    protected final ByteComparator comparator;
/* 115:    */    
/* 117:    */    protected Singleton(byte key, V value, ByteComparator comparator)
/* 118:    */    {
/* 119:119 */      super(value);
/* 120:120 */      this.comparator = comparator;
/* 121:    */    }
/* 122:    */    
/* 123:    */    protected Singleton(byte key, V value) {
/* 124:124 */      this(key, value, null);
/* 125:    */    }
/* 126:    */    
/* 127:    */    final int compare(byte k1, byte k2)
/* 128:    */    {
/* 129:129 */      return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/* 130:    */    }
/* 131:    */    
/* 132:132 */    public ByteComparator comparator() { return this.comparator; }
/* 133:    */    
/* 134:    */    public ObjectSortedSet<Byte2ObjectMap.Entry<V>> byte2ObjectEntrySet() {
/* 135:135 */      if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Byte2ObjectMaps.Singleton.SingletonEntry(this), Byte2ObjectSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries; }
/* 136:    */    
/* 137:137 */    public ObjectSortedSet<Map.Entry<Byte, V>> entrySet() { return byte2ObjectEntrySet(); }
/* 138:    */    
/* 139:139 */    public ByteSortedSet keySet() { if (this.keys == null) this.keys = ByteSortedSets.singleton(this.key, this.comparator); return (ByteSortedSet)this.keys;
/* 140:    */    }
/* 141:    */    
/* 142:142 */    public Byte2ObjectSortedMap<V> subMap(byte from, byte to) { if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Byte2ObjectSortedMaps.EMPTY_MAP;
/* 143:    */    }
/* 144:    */    
/* 145:145 */    public Byte2ObjectSortedMap<V> headMap(byte to) { if (compare(this.key, to) < 0) return this; return Byte2ObjectSortedMaps.EMPTY_MAP;
/* 146:    */    }
/* 147:    */    
/* 148:148 */    public Byte2ObjectSortedMap<V> tailMap(byte from) { if (compare(from, this.key) <= 0) return this; return Byte2ObjectSortedMaps.EMPTY_MAP; }
/* 149:    */    
/* 150:150 */    public byte firstByteKey() { return this.key; }
/* 151:151 */    public byte lastByteKey() { return this.key; }
/* 152:    */    
/* 154:154 */    public Byte2ObjectSortedMap<V> headMap(Byte oto) { return headMap(oto.byteValue()); }
/* 155:155 */    public Byte2ObjectSortedMap<V> tailMap(Byte ofrom) { return tailMap(ofrom.byteValue()); }
/* 156:156 */    public Byte2ObjectSortedMap<V> subMap(Byte ofrom, Byte oto) { return subMap(ofrom.byteValue(), oto.byteValue()); }
/* 157:    */    
/* 158:158 */    public Byte firstKey() { return Byte.valueOf(firstByteKey()); }
/* 159:159 */    public Byte lastKey() { return Byte.valueOf(lastByteKey()); }
/* 160:    */  }
/* 161:    */  
/* 171:    */  public static <V> Byte2ObjectSortedMap<V> singleton(Byte key, V value)
/* 172:    */  {
/* 173:173 */    return new Singleton(key.byteValue(), value);
/* 174:    */  }
/* 175:    */  
/* 185:    */  public static <V> Byte2ObjectSortedMap<V> singleton(Byte key, V value, ByteComparator comparator)
/* 186:    */  {
/* 187:187 */    return new Singleton(key.byteValue(), value, comparator);
/* 188:    */  }
/* 189:    */  
/* 200:    */  public static <V> Byte2ObjectSortedMap<V> singleton(byte key, V value)
/* 201:    */  {
/* 202:202 */    return new Singleton(key, value);
/* 203:    */  }
/* 204:    */  
/* 214:    */  public static <V> Byte2ObjectSortedMap<V> singleton(byte key, V value, ByteComparator comparator)
/* 215:    */  {
/* 216:216 */    return new Singleton(key, value, comparator);
/* 217:    */  }
/* 218:    */  
/* 220:    */  public static class SynchronizedSortedMap<V>
/* 221:    */    extends Byte2ObjectMaps.SynchronizedMap<V>
/* 222:    */    implements Byte2ObjectSortedMap<V>, Serializable
/* 223:    */  {
/* 224:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 225:    */    
/* 226:    */    protected final Byte2ObjectSortedMap<V> sortedMap;
/* 227:    */    
/* 229:    */    protected SynchronizedSortedMap(Byte2ObjectSortedMap<V> m, Object sync)
/* 230:    */    {
/* 231:231 */      super(sync);
/* 232:232 */      this.sortedMap = m;
/* 233:    */    }
/* 234:    */    
/* 235:    */    protected SynchronizedSortedMap(Byte2ObjectSortedMap<V> m) {
/* 236:236 */      super();
/* 237:237 */      this.sortedMap = m;
/* 238:    */    }
/* 239:    */    
/* 240:240 */    public ByteComparator comparator() { synchronized (this.sync) { return this.sortedMap.comparator(); } }
/* 241:    */    
/* 242:242 */    public ObjectSortedSet<Byte2ObjectMap.Entry<V>> byte2ObjectEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.byte2ObjectEntrySet(), this.sync); return (ObjectSortedSet)this.entries; }
/* 243:    */    
/* 244:244 */    public ObjectSortedSet<Map.Entry<Byte, V>> entrySet() { return byte2ObjectEntrySet(); }
/* 245:245 */    public ByteSortedSet keySet() { if (this.keys == null) this.keys = ByteSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (ByteSortedSet)this.keys; }
/* 246:    */    
/* 247:247 */    public Byte2ObjectSortedMap<V> subMap(byte from, byte to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 248:248 */    public Byte2ObjectSortedMap<V> headMap(byte to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 249:249 */    public Byte2ObjectSortedMap<V> tailMap(byte from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 250:    */    
/* 251:251 */    public byte firstByteKey() { synchronized (this.sync) { return this.sortedMap.firstByteKey(); } }
/* 252:252 */    public byte lastByteKey() { synchronized (this.sync) { return this.sortedMap.lastByteKey();
/* 253:    */      } }
/* 254:    */    
/* 255:255 */    public Byte firstKey() { synchronized (this.sync) { return (Byte)this.sortedMap.firstKey(); } }
/* 256:256 */    public Byte lastKey() { synchronized (this.sync) { return (Byte)this.sortedMap.lastKey(); } }
/* 257:    */    
/* 258:258 */    public Byte2ObjectSortedMap<V> subMap(Byte from, Byte to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 259:259 */    public Byte2ObjectSortedMap<V> headMap(Byte to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 260:260 */    public Byte2ObjectSortedMap<V> tailMap(Byte from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 261:    */  }
/* 262:    */  
/* 270:    */  public static <V> Byte2ObjectSortedMap<V> synchronize(Byte2ObjectSortedMap<V> m)
/* 271:    */  {
/* 272:272 */    return new SynchronizedSortedMap(m);
/* 273:    */  }
/* 274:    */  
/* 280:    */  public static <V> Byte2ObjectSortedMap<V> synchronize(Byte2ObjectSortedMap<V> m, Object sync)
/* 281:    */  {
/* 282:282 */    return new SynchronizedSortedMap(m, sync);
/* 283:    */  }
/* 284:    */  
/* 286:    */  public static class UnmodifiableSortedMap<V>
/* 287:    */    extends Byte2ObjectMaps.UnmodifiableMap<V>
/* 288:    */    implements Byte2ObjectSortedMap<V>, Serializable
/* 289:    */  {
/* 290:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 291:    */    
/* 292:    */    protected final Byte2ObjectSortedMap<V> sortedMap;
/* 293:    */    
/* 294:    */    protected UnmodifiableSortedMap(Byte2ObjectSortedMap<V> m)
/* 295:    */    {
/* 296:296 */      super();
/* 297:297 */      this.sortedMap = m;
/* 298:    */    }
/* 299:    */    
/* 300:300 */    public ByteComparator comparator() { return this.sortedMap.comparator(); }
/* 301:    */    
/* 302:302 */    public ObjectSortedSet<Byte2ObjectMap.Entry<V>> byte2ObjectEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.byte2ObjectEntrySet()); return (ObjectSortedSet)this.entries; }
/* 303:    */    
/* 304:304 */    public ObjectSortedSet<Map.Entry<Byte, V>> entrySet() { return byte2ObjectEntrySet(); }
/* 305:305 */    public ByteSortedSet keySet() { if (this.keys == null) this.keys = ByteSortedSets.unmodifiable(this.sortedMap.keySet()); return (ByteSortedSet)this.keys; }
/* 306:    */    
/* 307:307 */    public Byte2ObjectSortedMap<V> subMap(byte from, byte to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 308:308 */    public Byte2ObjectSortedMap<V> headMap(byte to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 309:309 */    public Byte2ObjectSortedMap<V> tailMap(byte from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 310:    */    
/* 311:311 */    public byte firstByteKey() { return this.sortedMap.firstByteKey(); }
/* 312:312 */    public byte lastByteKey() { return this.sortedMap.lastByteKey(); }
/* 313:    */    
/* 315:315 */    public Byte firstKey() { return (Byte)this.sortedMap.firstKey(); }
/* 316:316 */    public Byte lastKey() { return (Byte)this.sortedMap.lastKey(); }
/* 317:    */    
/* 318:318 */    public Byte2ObjectSortedMap<V> subMap(Byte from, Byte to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 319:319 */    public Byte2ObjectSortedMap<V> headMap(Byte to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 320:320 */    public Byte2ObjectSortedMap<V> tailMap(Byte from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 321:    */  }
/* 322:    */  
/* 330:    */  public static <V> Byte2ObjectSortedMap<V> unmodifiable(Byte2ObjectSortedMap<V> m)
/* 331:    */  {
/* 332:332 */    return new UnmodifiableSortedMap(m);
/* 333:    */  }
/* 334:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ObjectSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */