/*   1:    */package it.unimi.dsi.fastutil.bytes;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   4:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
/*   5:    */import java.io.Serializable;
/*   6:    */import java.util.Comparator;
/*   7:    */import java.util.Map.Entry;
/*   8:    */import java.util.NoSuchElementException;
/*   9:    */
/*  60:    */public class Byte2CharSortedMaps
/*  61:    */{
/*  62:    */  public static Comparator<? super Map.Entry<Byte, ?>> entryComparator(ByteComparator comparator)
/*  63:    */  {
/*  64: 64 */    new Comparator() {
/*  65:    */      public int compare(Map.Entry<Byte, ?> x, Map.Entry<Byte, ?> y) {
/*  66: 66 */        return this.val$comparator.compare(x.getKey(), y.getKey());
/*  67:    */      }
/*  68:    */    };
/*  69:    */  }
/*  70:    */  
/*  72:    */  public static class EmptySortedMap
/*  73:    */    extends Byte2CharMaps.EmptyMap
/*  74:    */    implements Byte2CharSortedMap, Serializable, Cloneable
/*  75:    */  {
/*  76:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  77:    */    
/*  78: 78 */    public ByteComparator comparator() { return null; }
/*  79:    */    
/*  80: 80 */    public ObjectSortedSet<Byte2CharMap.Entry> byte2CharEntrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  81:    */    
/*  82: 82 */    public ObjectSortedSet<Map.Entry<Byte, Character>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  83:    */    
/*  84: 84 */    public ByteSortedSet keySet() { return ByteSortedSets.EMPTY_SET; }
/*  85:    */    
/*  86: 86 */    public Byte2CharSortedMap subMap(byte from, byte to) { return Byte2CharSortedMaps.EMPTY_MAP; }
/*  87:    */    
/*  88: 88 */    public Byte2CharSortedMap headMap(byte to) { return Byte2CharSortedMaps.EMPTY_MAP; }
/*  89:    */    
/*  90: 90 */    public Byte2CharSortedMap tailMap(byte from) { return Byte2CharSortedMaps.EMPTY_MAP; }
/*  91: 91 */    public byte firstByteKey() { throw new NoSuchElementException(); }
/*  92: 92 */    public byte lastByteKey() { throw new NoSuchElementException(); }
/*  93: 93 */    public Byte2CharSortedMap headMap(Byte oto) { return headMap(oto.byteValue()); }
/*  94: 94 */    public Byte2CharSortedMap tailMap(Byte ofrom) { return tailMap(ofrom.byteValue()); }
/*  95: 95 */    public Byte2CharSortedMap subMap(Byte ofrom, Byte oto) { return subMap(ofrom.byteValue(), oto.byteValue()); }
/*  96: 96 */    public Byte firstKey() { return Byte.valueOf(firstByteKey()); }
/*  97: 97 */    public Byte lastKey() { return Byte.valueOf(lastByteKey()); }
/*  98:    */  }
/*  99:    */  
/* 103:103 */  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/* 104:    */  
/* 107:    */  public static class Singleton
/* 108:    */    extends Byte2CharMaps.Singleton
/* 109:    */    implements Byte2CharSortedMap, Serializable, Cloneable
/* 110:    */  {
/* 111:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 112:    */    
/* 114:    */    protected final ByteComparator comparator;
/* 115:    */    
/* 117:    */    protected Singleton(byte key, char value, ByteComparator comparator)
/* 118:    */    {
/* 119:119 */      super(value);
/* 120:120 */      this.comparator = comparator;
/* 121:    */    }
/* 122:    */    
/* 123:    */    protected Singleton(byte key, char value) {
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
/* 134:    */    public ObjectSortedSet<Byte2CharMap.Entry> byte2CharEntrySet() {
/* 135:135 */      if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Byte2CharMaps.Singleton.SingletonEntry(this), Byte2CharSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries; }
/* 136:    */    
/* 137:137 */    public ObjectSortedSet<Map.Entry<Byte, Character>> entrySet() { return byte2CharEntrySet(); }
/* 138:    */    
/* 139:139 */    public ByteSortedSet keySet() { if (this.keys == null) this.keys = ByteSortedSets.singleton(this.key, this.comparator); return (ByteSortedSet)this.keys;
/* 140:    */    }
/* 141:    */    
/* 142:142 */    public Byte2CharSortedMap subMap(byte from, byte to) { if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Byte2CharSortedMaps.EMPTY_MAP;
/* 143:    */    }
/* 144:    */    
/* 145:145 */    public Byte2CharSortedMap headMap(byte to) { if (compare(this.key, to) < 0) return this; return Byte2CharSortedMaps.EMPTY_MAP;
/* 146:    */    }
/* 147:    */    
/* 148:148 */    public Byte2CharSortedMap tailMap(byte from) { if (compare(from, this.key) <= 0) return this; return Byte2CharSortedMaps.EMPTY_MAP; }
/* 149:    */    
/* 150:150 */    public byte firstByteKey() { return this.key; }
/* 151:151 */    public byte lastByteKey() { return this.key; }
/* 152:    */    
/* 154:154 */    public Byte2CharSortedMap headMap(Byte oto) { return headMap(oto.byteValue()); }
/* 155:155 */    public Byte2CharSortedMap tailMap(Byte ofrom) { return tailMap(ofrom.byteValue()); }
/* 156:156 */    public Byte2CharSortedMap subMap(Byte ofrom, Byte oto) { return subMap(ofrom.byteValue(), oto.byteValue()); }
/* 157:    */    
/* 158:158 */    public Byte firstKey() { return Byte.valueOf(firstByteKey()); }
/* 159:159 */    public Byte lastKey() { return Byte.valueOf(lastByteKey()); }
/* 160:    */  }
/* 161:    */  
/* 171:    */  public static Byte2CharSortedMap singleton(Byte key, Character value)
/* 172:    */  {
/* 173:173 */    return new Singleton(key.byteValue(), value.charValue());
/* 174:    */  }
/* 175:    */  
/* 185:    */  public static Byte2CharSortedMap singleton(Byte key, Character value, ByteComparator comparator)
/* 186:    */  {
/* 187:187 */    return new Singleton(key.byteValue(), value.charValue(), comparator);
/* 188:    */  }
/* 189:    */  
/* 200:    */  public static Byte2CharSortedMap singleton(byte key, char value)
/* 201:    */  {
/* 202:202 */    return new Singleton(key, value);
/* 203:    */  }
/* 204:    */  
/* 214:    */  public static Byte2CharSortedMap singleton(byte key, char value, ByteComparator comparator)
/* 215:    */  {
/* 216:216 */    return new Singleton(key, value, comparator);
/* 217:    */  }
/* 218:    */  
/* 220:    */  public static class SynchronizedSortedMap
/* 221:    */    extends Byte2CharMaps.SynchronizedMap
/* 222:    */    implements Byte2CharSortedMap, Serializable
/* 223:    */  {
/* 224:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 225:    */    
/* 226:    */    protected final Byte2CharSortedMap sortedMap;
/* 227:    */    
/* 229:    */    protected SynchronizedSortedMap(Byte2CharSortedMap m, Object sync)
/* 230:    */    {
/* 231:231 */      super(sync);
/* 232:232 */      this.sortedMap = m;
/* 233:    */    }
/* 234:    */    
/* 235:    */    protected SynchronizedSortedMap(Byte2CharSortedMap m) {
/* 236:236 */      super();
/* 237:237 */      this.sortedMap = m;
/* 238:    */    }
/* 239:    */    
/* 240:240 */    public ByteComparator comparator() { synchronized (this.sync) { return this.sortedMap.comparator(); } }
/* 241:    */    
/* 242:242 */    public ObjectSortedSet<Byte2CharMap.Entry> byte2CharEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.byte2CharEntrySet(), this.sync); return (ObjectSortedSet)this.entries; }
/* 243:    */    
/* 244:244 */    public ObjectSortedSet<Map.Entry<Byte, Character>> entrySet() { return byte2CharEntrySet(); }
/* 245:245 */    public ByteSortedSet keySet() { if (this.keys == null) this.keys = ByteSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (ByteSortedSet)this.keys; }
/* 246:    */    
/* 247:247 */    public Byte2CharSortedMap subMap(byte from, byte to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 248:248 */    public Byte2CharSortedMap headMap(byte to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 249:249 */    public Byte2CharSortedMap tailMap(byte from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 250:    */    
/* 251:251 */    public byte firstByteKey() { synchronized (this.sync) { return this.sortedMap.firstByteKey(); } }
/* 252:252 */    public byte lastByteKey() { synchronized (this.sync) { return this.sortedMap.lastByteKey();
/* 253:    */      } }
/* 254:    */    
/* 255:255 */    public Byte firstKey() { synchronized (this.sync) { return (Byte)this.sortedMap.firstKey(); } }
/* 256:256 */    public Byte lastKey() { synchronized (this.sync) { return (Byte)this.sortedMap.lastKey(); } }
/* 257:    */    
/* 258:258 */    public Byte2CharSortedMap subMap(Byte from, Byte to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 259:259 */    public Byte2CharSortedMap headMap(Byte to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 260:260 */    public Byte2CharSortedMap tailMap(Byte from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 261:    */  }
/* 262:    */  
/* 270:    */  public static Byte2CharSortedMap synchronize(Byte2CharSortedMap m)
/* 271:    */  {
/* 272:272 */    return new SynchronizedSortedMap(m);
/* 273:    */  }
/* 274:    */  
/* 280:    */  public static Byte2CharSortedMap synchronize(Byte2CharSortedMap m, Object sync)
/* 281:    */  {
/* 282:282 */    return new SynchronizedSortedMap(m, sync);
/* 283:    */  }
/* 284:    */  
/* 286:    */  public static class UnmodifiableSortedMap
/* 287:    */    extends Byte2CharMaps.UnmodifiableMap
/* 288:    */    implements Byte2CharSortedMap, Serializable
/* 289:    */  {
/* 290:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 291:    */    
/* 292:    */    protected final Byte2CharSortedMap sortedMap;
/* 293:    */    
/* 294:    */    protected UnmodifiableSortedMap(Byte2CharSortedMap m)
/* 295:    */    {
/* 296:296 */      super();
/* 297:297 */      this.sortedMap = m;
/* 298:    */    }
/* 299:    */    
/* 300:300 */    public ByteComparator comparator() { return this.sortedMap.comparator(); }
/* 301:    */    
/* 302:302 */    public ObjectSortedSet<Byte2CharMap.Entry> byte2CharEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.byte2CharEntrySet()); return (ObjectSortedSet)this.entries; }
/* 303:    */    
/* 304:304 */    public ObjectSortedSet<Map.Entry<Byte, Character>> entrySet() { return byte2CharEntrySet(); }
/* 305:305 */    public ByteSortedSet keySet() { if (this.keys == null) this.keys = ByteSortedSets.unmodifiable(this.sortedMap.keySet()); return (ByteSortedSet)this.keys; }
/* 306:    */    
/* 307:307 */    public Byte2CharSortedMap subMap(byte from, byte to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 308:308 */    public Byte2CharSortedMap headMap(byte to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 309:309 */    public Byte2CharSortedMap tailMap(byte from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 310:    */    
/* 311:311 */    public byte firstByteKey() { return this.sortedMap.firstByteKey(); }
/* 312:312 */    public byte lastByteKey() { return this.sortedMap.lastByteKey(); }
/* 313:    */    
/* 315:315 */    public Byte firstKey() { return (Byte)this.sortedMap.firstKey(); }
/* 316:316 */    public Byte lastKey() { return (Byte)this.sortedMap.lastKey(); }
/* 317:    */    
/* 318:318 */    public Byte2CharSortedMap subMap(Byte from, Byte to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 319:319 */    public Byte2CharSortedMap headMap(Byte to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 320:320 */    public Byte2CharSortedMap tailMap(Byte from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 321:    */  }
/* 322:    */  
/* 330:    */  public static Byte2CharSortedMap unmodifiable(Byte2CharSortedMap m)
/* 331:    */  {
/* 332:332 */    return new UnmodifiableSortedMap(m);
/* 333:    */  }
/* 334:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2CharSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */