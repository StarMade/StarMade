/*   1:    */package it.unimi.dsi.fastutil.floats;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   4:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
/*   5:    */import java.io.Serializable;
/*   6:    */import java.util.Comparator;
/*   7:    */import java.util.Map.Entry;
/*   8:    */import java.util.NoSuchElementException;
/*   9:    */
/*  60:    */public class Float2CharSortedMaps
/*  61:    */{
/*  62:    */  public static Comparator<? super Map.Entry<Float, ?>> entryComparator(FloatComparator comparator)
/*  63:    */  {
/*  64: 64 */    new Comparator() {
/*  65:    */      public int compare(Map.Entry<Float, ?> x, Map.Entry<Float, ?> y) {
/*  66: 66 */        return this.val$comparator.compare(x.getKey(), y.getKey());
/*  67:    */      }
/*  68:    */    };
/*  69:    */  }
/*  70:    */  
/*  72:    */  public static class EmptySortedMap
/*  73:    */    extends Float2CharMaps.EmptyMap
/*  74:    */    implements Float2CharSortedMap, Serializable, Cloneable
/*  75:    */  {
/*  76:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  77:    */    
/*  78: 78 */    public FloatComparator comparator() { return null; }
/*  79:    */    
/*  80: 80 */    public ObjectSortedSet<Float2CharMap.Entry> float2CharEntrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  81:    */    
/*  82: 82 */    public ObjectSortedSet<Map.Entry<Float, Character>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  83:    */    
/*  84: 84 */    public FloatSortedSet keySet() { return FloatSortedSets.EMPTY_SET; }
/*  85:    */    
/*  86: 86 */    public Float2CharSortedMap subMap(float from, float to) { return Float2CharSortedMaps.EMPTY_MAP; }
/*  87:    */    
/*  88: 88 */    public Float2CharSortedMap headMap(float to) { return Float2CharSortedMaps.EMPTY_MAP; }
/*  89:    */    
/*  90: 90 */    public Float2CharSortedMap tailMap(float from) { return Float2CharSortedMaps.EMPTY_MAP; }
/*  91: 91 */    public float firstFloatKey() { throw new NoSuchElementException(); }
/*  92: 92 */    public float lastFloatKey() { throw new NoSuchElementException(); }
/*  93: 93 */    public Float2CharSortedMap headMap(Float oto) { return headMap(oto.floatValue()); }
/*  94: 94 */    public Float2CharSortedMap tailMap(Float ofrom) { return tailMap(ofrom.floatValue()); }
/*  95: 95 */    public Float2CharSortedMap subMap(Float ofrom, Float oto) { return subMap(ofrom.floatValue(), oto.floatValue()); }
/*  96: 96 */    public Float firstKey() { return Float.valueOf(firstFloatKey()); }
/*  97: 97 */    public Float lastKey() { return Float.valueOf(lastFloatKey()); }
/*  98:    */  }
/*  99:    */  
/* 103:103 */  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/* 104:    */  
/* 107:    */  public static class Singleton
/* 108:    */    extends Float2CharMaps.Singleton
/* 109:    */    implements Float2CharSortedMap, Serializable, Cloneable
/* 110:    */  {
/* 111:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 112:    */    
/* 114:    */    protected final FloatComparator comparator;
/* 115:    */    
/* 117:    */    protected Singleton(float key, char value, FloatComparator comparator)
/* 118:    */    {
/* 119:119 */      super(value);
/* 120:120 */      this.comparator = comparator;
/* 121:    */    }
/* 122:    */    
/* 123:    */    protected Singleton(float key, char value) {
/* 124:124 */      this(key, value, null);
/* 125:    */    }
/* 126:    */    
/* 127:    */    final int compare(float k1, float k2)
/* 128:    */    {
/* 129:129 */      return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/* 130:    */    }
/* 131:    */    
/* 132:132 */    public FloatComparator comparator() { return this.comparator; }
/* 133:    */    
/* 134:    */    public ObjectSortedSet<Float2CharMap.Entry> float2CharEntrySet() {
/* 135:135 */      if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Float2CharMaps.Singleton.SingletonEntry(this), Float2CharSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries; }
/* 136:    */    
/* 137:137 */    public ObjectSortedSet<Map.Entry<Float, Character>> entrySet() { return float2CharEntrySet(); }
/* 138:    */    
/* 139:139 */    public FloatSortedSet keySet() { if (this.keys == null) this.keys = FloatSortedSets.singleton(this.key, this.comparator); return (FloatSortedSet)this.keys;
/* 140:    */    }
/* 141:    */    
/* 142:142 */    public Float2CharSortedMap subMap(float from, float to) { if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Float2CharSortedMaps.EMPTY_MAP;
/* 143:    */    }
/* 144:    */    
/* 145:145 */    public Float2CharSortedMap headMap(float to) { if (compare(this.key, to) < 0) return this; return Float2CharSortedMaps.EMPTY_MAP;
/* 146:    */    }
/* 147:    */    
/* 148:148 */    public Float2CharSortedMap tailMap(float from) { if (compare(from, this.key) <= 0) return this; return Float2CharSortedMaps.EMPTY_MAP; }
/* 149:    */    
/* 150:150 */    public float firstFloatKey() { return this.key; }
/* 151:151 */    public float lastFloatKey() { return this.key; }
/* 152:    */    
/* 154:154 */    public Float2CharSortedMap headMap(Float oto) { return headMap(oto.floatValue()); }
/* 155:155 */    public Float2CharSortedMap tailMap(Float ofrom) { return tailMap(ofrom.floatValue()); }
/* 156:156 */    public Float2CharSortedMap subMap(Float ofrom, Float oto) { return subMap(ofrom.floatValue(), oto.floatValue()); }
/* 157:    */    
/* 158:158 */    public Float firstKey() { return Float.valueOf(firstFloatKey()); }
/* 159:159 */    public Float lastKey() { return Float.valueOf(lastFloatKey()); }
/* 160:    */  }
/* 161:    */  
/* 171:    */  public static Float2CharSortedMap singleton(Float key, Character value)
/* 172:    */  {
/* 173:173 */    return new Singleton(key.floatValue(), value.charValue());
/* 174:    */  }
/* 175:    */  
/* 185:    */  public static Float2CharSortedMap singleton(Float key, Character value, FloatComparator comparator)
/* 186:    */  {
/* 187:187 */    return new Singleton(key.floatValue(), value.charValue(), comparator);
/* 188:    */  }
/* 189:    */  
/* 200:    */  public static Float2CharSortedMap singleton(float key, char value)
/* 201:    */  {
/* 202:202 */    return new Singleton(key, value);
/* 203:    */  }
/* 204:    */  
/* 214:    */  public static Float2CharSortedMap singleton(float key, char value, FloatComparator comparator)
/* 215:    */  {
/* 216:216 */    return new Singleton(key, value, comparator);
/* 217:    */  }
/* 218:    */  
/* 220:    */  public static class SynchronizedSortedMap
/* 221:    */    extends Float2CharMaps.SynchronizedMap
/* 222:    */    implements Float2CharSortedMap, Serializable
/* 223:    */  {
/* 224:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 225:    */    
/* 226:    */    protected final Float2CharSortedMap sortedMap;
/* 227:    */    
/* 229:    */    protected SynchronizedSortedMap(Float2CharSortedMap m, Object sync)
/* 230:    */    {
/* 231:231 */      super(sync);
/* 232:232 */      this.sortedMap = m;
/* 233:    */    }
/* 234:    */    
/* 235:    */    protected SynchronizedSortedMap(Float2CharSortedMap m) {
/* 236:236 */      super();
/* 237:237 */      this.sortedMap = m;
/* 238:    */    }
/* 239:    */    
/* 240:240 */    public FloatComparator comparator() { synchronized (this.sync) { return this.sortedMap.comparator(); } }
/* 241:    */    
/* 242:242 */    public ObjectSortedSet<Float2CharMap.Entry> float2CharEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.float2CharEntrySet(), this.sync); return (ObjectSortedSet)this.entries; }
/* 243:    */    
/* 244:244 */    public ObjectSortedSet<Map.Entry<Float, Character>> entrySet() { return float2CharEntrySet(); }
/* 245:245 */    public FloatSortedSet keySet() { if (this.keys == null) this.keys = FloatSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (FloatSortedSet)this.keys; }
/* 246:    */    
/* 247:247 */    public Float2CharSortedMap subMap(float from, float to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 248:248 */    public Float2CharSortedMap headMap(float to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 249:249 */    public Float2CharSortedMap tailMap(float from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 250:    */    
/* 251:251 */    public float firstFloatKey() { synchronized (this.sync) { return this.sortedMap.firstFloatKey(); } }
/* 252:252 */    public float lastFloatKey() { synchronized (this.sync) { return this.sortedMap.lastFloatKey();
/* 253:    */      } }
/* 254:    */    
/* 255:255 */    public Float firstKey() { synchronized (this.sync) { return (Float)this.sortedMap.firstKey(); } }
/* 256:256 */    public Float lastKey() { synchronized (this.sync) { return (Float)this.sortedMap.lastKey(); } }
/* 257:    */    
/* 258:258 */    public Float2CharSortedMap subMap(Float from, Float to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 259:259 */    public Float2CharSortedMap headMap(Float to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 260:260 */    public Float2CharSortedMap tailMap(Float from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 261:    */  }
/* 262:    */  
/* 270:    */  public static Float2CharSortedMap synchronize(Float2CharSortedMap m)
/* 271:    */  {
/* 272:272 */    return new SynchronizedSortedMap(m);
/* 273:    */  }
/* 274:    */  
/* 280:    */  public static Float2CharSortedMap synchronize(Float2CharSortedMap m, Object sync)
/* 281:    */  {
/* 282:282 */    return new SynchronizedSortedMap(m, sync);
/* 283:    */  }
/* 284:    */  
/* 286:    */  public static class UnmodifiableSortedMap
/* 287:    */    extends Float2CharMaps.UnmodifiableMap
/* 288:    */    implements Float2CharSortedMap, Serializable
/* 289:    */  {
/* 290:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 291:    */    
/* 292:    */    protected final Float2CharSortedMap sortedMap;
/* 293:    */    
/* 294:    */    protected UnmodifiableSortedMap(Float2CharSortedMap m)
/* 295:    */    {
/* 296:296 */      super();
/* 297:297 */      this.sortedMap = m;
/* 298:    */    }
/* 299:    */    
/* 300:300 */    public FloatComparator comparator() { return this.sortedMap.comparator(); }
/* 301:    */    
/* 302:302 */    public ObjectSortedSet<Float2CharMap.Entry> float2CharEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.float2CharEntrySet()); return (ObjectSortedSet)this.entries; }
/* 303:    */    
/* 304:304 */    public ObjectSortedSet<Map.Entry<Float, Character>> entrySet() { return float2CharEntrySet(); }
/* 305:305 */    public FloatSortedSet keySet() { if (this.keys == null) this.keys = FloatSortedSets.unmodifiable(this.sortedMap.keySet()); return (FloatSortedSet)this.keys; }
/* 306:    */    
/* 307:307 */    public Float2CharSortedMap subMap(float from, float to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 308:308 */    public Float2CharSortedMap headMap(float to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 309:309 */    public Float2CharSortedMap tailMap(float from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 310:    */    
/* 311:311 */    public float firstFloatKey() { return this.sortedMap.firstFloatKey(); }
/* 312:312 */    public float lastFloatKey() { return this.sortedMap.lastFloatKey(); }
/* 313:    */    
/* 315:315 */    public Float firstKey() { return (Float)this.sortedMap.firstKey(); }
/* 316:316 */    public Float lastKey() { return (Float)this.sortedMap.lastKey(); }
/* 317:    */    
/* 318:318 */    public Float2CharSortedMap subMap(Float from, Float to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 319:319 */    public Float2CharSortedMap headMap(Float to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 320:320 */    public Float2CharSortedMap tailMap(Float from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 321:    */  }
/* 322:    */  
/* 330:    */  public static Float2CharSortedMap unmodifiable(Float2CharSortedMap m)
/* 331:    */  {
/* 332:332 */    return new UnmodifiableSortedMap(m);
/* 333:    */  }
/* 334:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2CharSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */