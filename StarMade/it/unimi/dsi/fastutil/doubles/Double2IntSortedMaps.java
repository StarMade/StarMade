/*   1:    */package it.unimi.dsi.fastutil.doubles;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   4:    */import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
/*   5:    */import java.io.Serializable;
/*   6:    */import java.util.Comparator;
/*   7:    */import java.util.Map.Entry;
/*   8:    */import java.util.NoSuchElementException;
/*   9:    */
/*  60:    */public class Double2IntSortedMaps
/*  61:    */{
/*  62:    */  public static Comparator<? super Map.Entry<Double, ?>> entryComparator(DoubleComparator comparator)
/*  63:    */  {
/*  64: 64 */    new Comparator() {
/*  65:    */      public int compare(Map.Entry<Double, ?> x, Map.Entry<Double, ?> y) {
/*  66: 66 */        return this.val$comparator.compare(x.getKey(), y.getKey());
/*  67:    */      }
/*  68:    */    };
/*  69:    */  }
/*  70:    */  
/*  72:    */  public static class EmptySortedMap
/*  73:    */    extends Double2IntMaps.EmptyMap
/*  74:    */    implements Double2IntSortedMap, Serializable, Cloneable
/*  75:    */  {
/*  76:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  77:    */    
/*  78: 78 */    public DoubleComparator comparator() { return null; }
/*  79:    */    
/*  80: 80 */    public ObjectSortedSet<Double2IntMap.Entry> double2IntEntrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  81:    */    
/*  82: 82 */    public ObjectSortedSet<Map.Entry<Double, Integer>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
/*  83:    */    
/*  84: 84 */    public DoubleSortedSet keySet() { return DoubleSortedSets.EMPTY_SET; }
/*  85:    */    
/*  86: 86 */    public Double2IntSortedMap subMap(double from, double to) { return Double2IntSortedMaps.EMPTY_MAP; }
/*  87:    */    
/*  88: 88 */    public Double2IntSortedMap headMap(double to) { return Double2IntSortedMaps.EMPTY_MAP; }
/*  89:    */    
/*  90: 90 */    public Double2IntSortedMap tailMap(double from) { return Double2IntSortedMaps.EMPTY_MAP; }
/*  91: 91 */    public double firstDoubleKey() { throw new NoSuchElementException(); }
/*  92: 92 */    public double lastDoubleKey() { throw new NoSuchElementException(); }
/*  93: 93 */    public Double2IntSortedMap headMap(Double oto) { return headMap(oto.doubleValue()); }
/*  94: 94 */    public Double2IntSortedMap tailMap(Double ofrom) { return tailMap(ofrom.doubleValue()); }
/*  95: 95 */    public Double2IntSortedMap subMap(Double ofrom, Double oto) { return subMap(ofrom.doubleValue(), oto.doubleValue()); }
/*  96: 96 */    public Double firstKey() { return Double.valueOf(firstDoubleKey()); }
/*  97: 97 */    public Double lastKey() { return Double.valueOf(lastDoubleKey()); }
/*  98:    */  }
/*  99:    */  
/* 103:103 */  public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();
/* 104:    */  
/* 107:    */  public static class Singleton
/* 108:    */    extends Double2IntMaps.Singleton
/* 109:    */    implements Double2IntSortedMap, Serializable, Cloneable
/* 110:    */  {
/* 111:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 112:    */    
/* 114:    */    protected final DoubleComparator comparator;
/* 115:    */    
/* 117:    */    protected Singleton(double key, int value, DoubleComparator comparator)
/* 118:    */    {
/* 119:119 */      super(value);
/* 120:120 */      this.comparator = comparator;
/* 121:    */    }
/* 122:    */    
/* 123:    */    protected Singleton(double key, int value) {
/* 124:124 */      this(key, value, null);
/* 125:    */    }
/* 126:    */    
/* 127:    */    final int compare(double k1, double k2)
/* 128:    */    {
/* 129:129 */      return this.comparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.comparator.compare(k1, k2);
/* 130:    */    }
/* 131:    */    
/* 132:132 */    public DoubleComparator comparator() { return this.comparator; }
/* 133:    */    
/* 134:    */    public ObjectSortedSet<Double2IntMap.Entry> double2IntEntrySet() {
/* 135:135 */      if (this.entries == null) this.entries = ObjectSortedSets.singleton(new Double2IntMaps.Singleton.SingletonEntry(this), Double2IntSortedMaps.entryComparator(this.comparator)); return (ObjectSortedSet)this.entries; }
/* 136:    */    
/* 137:137 */    public ObjectSortedSet<Map.Entry<Double, Integer>> entrySet() { return double2IntEntrySet(); }
/* 138:    */    
/* 139:139 */    public DoubleSortedSet keySet() { if (this.keys == null) this.keys = DoubleSortedSets.singleton(this.key, this.comparator); return (DoubleSortedSet)this.keys;
/* 140:    */    }
/* 141:    */    
/* 142:142 */    public Double2IntSortedMap subMap(double from, double to) { if ((compare(from, this.key) <= 0) && (compare(this.key, to) < 0)) return this; return Double2IntSortedMaps.EMPTY_MAP;
/* 143:    */    }
/* 144:    */    
/* 145:145 */    public Double2IntSortedMap headMap(double to) { if (compare(this.key, to) < 0) return this; return Double2IntSortedMaps.EMPTY_MAP;
/* 146:    */    }
/* 147:    */    
/* 148:148 */    public Double2IntSortedMap tailMap(double from) { if (compare(from, this.key) <= 0) return this; return Double2IntSortedMaps.EMPTY_MAP; }
/* 149:    */    
/* 150:150 */    public double firstDoubleKey() { return this.key; }
/* 151:151 */    public double lastDoubleKey() { return this.key; }
/* 152:    */    
/* 154:154 */    public Double2IntSortedMap headMap(Double oto) { return headMap(oto.doubleValue()); }
/* 155:155 */    public Double2IntSortedMap tailMap(Double ofrom) { return tailMap(ofrom.doubleValue()); }
/* 156:156 */    public Double2IntSortedMap subMap(Double ofrom, Double oto) { return subMap(ofrom.doubleValue(), oto.doubleValue()); }
/* 157:    */    
/* 158:158 */    public Double firstKey() { return Double.valueOf(firstDoubleKey()); }
/* 159:159 */    public Double lastKey() { return Double.valueOf(lastDoubleKey()); }
/* 160:    */  }
/* 161:    */  
/* 171:    */  public static Double2IntSortedMap singleton(Double key, Integer value)
/* 172:    */  {
/* 173:173 */    return new Singleton(key.doubleValue(), value.intValue());
/* 174:    */  }
/* 175:    */  
/* 185:    */  public static Double2IntSortedMap singleton(Double key, Integer value, DoubleComparator comparator)
/* 186:    */  {
/* 187:187 */    return new Singleton(key.doubleValue(), value.intValue(), comparator);
/* 188:    */  }
/* 189:    */  
/* 200:    */  public static Double2IntSortedMap singleton(double key, int value)
/* 201:    */  {
/* 202:202 */    return new Singleton(key, value);
/* 203:    */  }
/* 204:    */  
/* 214:    */  public static Double2IntSortedMap singleton(double key, int value, DoubleComparator comparator)
/* 215:    */  {
/* 216:216 */    return new Singleton(key, value, comparator);
/* 217:    */  }
/* 218:    */  
/* 220:    */  public static class SynchronizedSortedMap
/* 221:    */    extends Double2IntMaps.SynchronizedMap
/* 222:    */    implements Double2IntSortedMap, Serializable
/* 223:    */  {
/* 224:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 225:    */    
/* 226:    */    protected final Double2IntSortedMap sortedMap;
/* 227:    */    
/* 229:    */    protected SynchronizedSortedMap(Double2IntSortedMap m, Object sync)
/* 230:    */    {
/* 231:231 */      super(sync);
/* 232:232 */      this.sortedMap = m;
/* 233:    */    }
/* 234:    */    
/* 235:    */    protected SynchronizedSortedMap(Double2IntSortedMap m) {
/* 236:236 */      super();
/* 237:237 */      this.sortedMap = m;
/* 238:    */    }
/* 239:    */    
/* 240:240 */    public DoubleComparator comparator() { synchronized (this.sync) { return this.sortedMap.comparator(); } }
/* 241:    */    
/* 242:242 */    public ObjectSortedSet<Double2IntMap.Entry> double2IntEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.synchronize(this.sortedMap.double2IntEntrySet(), this.sync); return (ObjectSortedSet)this.entries; }
/* 243:    */    
/* 244:244 */    public ObjectSortedSet<Map.Entry<Double, Integer>> entrySet() { return double2IntEntrySet(); }
/* 245:245 */    public DoubleSortedSet keySet() { if (this.keys == null) this.keys = DoubleSortedSets.synchronize(this.sortedMap.keySet(), this.sync); return (DoubleSortedSet)this.keys; }
/* 246:    */    
/* 247:247 */    public Double2IntSortedMap subMap(double from, double to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 248:248 */    public Double2IntSortedMap headMap(double to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 249:249 */    public Double2IntSortedMap tailMap(double from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 250:    */    
/* 251:251 */    public double firstDoubleKey() { synchronized (this.sync) { return this.sortedMap.firstDoubleKey(); } }
/* 252:252 */    public double lastDoubleKey() { synchronized (this.sync) { return this.sortedMap.lastDoubleKey();
/* 253:    */      } }
/* 254:    */    
/* 255:255 */    public Double firstKey() { synchronized (this.sync) { return (Double)this.sortedMap.firstKey(); } }
/* 256:256 */    public Double lastKey() { synchronized (this.sync) { return (Double)this.sortedMap.lastKey(); } }
/* 257:    */    
/* 258:258 */    public Double2IntSortedMap subMap(Double from, Double to) { return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync); }
/* 259:259 */    public Double2IntSortedMap headMap(Double to) { return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync); }
/* 260:260 */    public Double2IntSortedMap tailMap(Double from) { return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync); }
/* 261:    */  }
/* 262:    */  
/* 270:    */  public static Double2IntSortedMap synchronize(Double2IntSortedMap m)
/* 271:    */  {
/* 272:272 */    return new SynchronizedSortedMap(m);
/* 273:    */  }
/* 274:    */  
/* 280:    */  public static Double2IntSortedMap synchronize(Double2IntSortedMap m, Object sync)
/* 281:    */  {
/* 282:282 */    return new SynchronizedSortedMap(m, sync);
/* 283:    */  }
/* 284:    */  
/* 286:    */  public static class UnmodifiableSortedMap
/* 287:    */    extends Double2IntMaps.UnmodifiableMap
/* 288:    */    implements Double2IntSortedMap, Serializable
/* 289:    */  {
/* 290:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 291:    */    
/* 292:    */    protected final Double2IntSortedMap sortedMap;
/* 293:    */    
/* 294:    */    protected UnmodifiableSortedMap(Double2IntSortedMap m)
/* 295:    */    {
/* 296:296 */      super();
/* 297:297 */      this.sortedMap = m;
/* 298:    */    }
/* 299:    */    
/* 300:300 */    public DoubleComparator comparator() { return this.sortedMap.comparator(); }
/* 301:    */    
/* 302:302 */    public ObjectSortedSet<Double2IntMap.Entry> double2IntEntrySet() { if (this.entries == null) this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.double2IntEntrySet()); return (ObjectSortedSet)this.entries; }
/* 303:    */    
/* 304:304 */    public ObjectSortedSet<Map.Entry<Double, Integer>> entrySet() { return double2IntEntrySet(); }
/* 305:305 */    public DoubleSortedSet keySet() { if (this.keys == null) this.keys = DoubleSortedSets.unmodifiable(this.sortedMap.keySet()); return (DoubleSortedSet)this.keys; }
/* 306:    */    
/* 307:307 */    public Double2IntSortedMap subMap(double from, double to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 308:308 */    public Double2IntSortedMap headMap(double to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 309:309 */    public Double2IntSortedMap tailMap(double from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 310:    */    
/* 311:311 */    public double firstDoubleKey() { return this.sortedMap.firstDoubleKey(); }
/* 312:312 */    public double lastDoubleKey() { return this.sortedMap.lastDoubleKey(); }
/* 313:    */    
/* 315:315 */    public Double firstKey() { return (Double)this.sortedMap.firstKey(); }
/* 316:316 */    public Double lastKey() { return (Double)this.sortedMap.lastKey(); }
/* 317:    */    
/* 318:318 */    public Double2IntSortedMap subMap(Double from, Double to) { return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to)); }
/* 319:319 */    public Double2IntSortedMap headMap(Double to) { return new UnmodifiableSortedMap(this.sortedMap.headMap(to)); }
/* 320:320 */    public Double2IntSortedMap tailMap(Double from) { return new UnmodifiableSortedMap(this.sortedMap.tailMap(from)); }
/* 321:    */  }
/* 322:    */  
/* 330:    */  public static Double2IntSortedMap unmodifiable(Double2IntSortedMap m)
/* 331:    */  {
/* 332:332 */    return new UnmodifiableSortedMap(m);
/* 333:    */  }
/* 334:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2IntSortedMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */