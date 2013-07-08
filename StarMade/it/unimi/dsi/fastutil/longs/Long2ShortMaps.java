/*   1:    */package it.unimi.dsi.fastutil.longs;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.HashCommon;
/*   4:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectSet;
/*   6:    */import it.unimi.dsi.fastutil.objects.ObjectSets;
/*   7:    */import it.unimi.dsi.fastutil.shorts.ShortCollection;
/*   8:    */import it.unimi.dsi.fastutil.shorts.ShortCollections;
/*   9:    */import it.unimi.dsi.fastutil.shorts.ShortSets;
/*  10:    */import java.io.Serializable;
/*  11:    */import java.util.Iterator;
/*  12:    */import java.util.Map;
/*  13:    */import java.util.Map.Entry;
/*  14:    */import java.util.Set;
/*  15:    */
/*  59:    */public class Long2ShortMaps
/*  60:    */{
/*  61:    */  public static class EmptyMap
/*  62:    */    extends Long2ShortFunctions.EmptyFunction
/*  63:    */    implements Long2ShortMap, Serializable, Cloneable
/*  64:    */  {
/*  65:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  66:    */    
/*  67: 67 */    public boolean containsValue(short v) { return false; }
/*  68: 68 */    public void putAll(Map<? extends Long, ? extends Short> m) { throw new UnsupportedOperationException(); }
/*  69:    */    
/*  70: 70 */    public ObjectSet<Long2ShortMap.Entry> long2ShortEntrySet() { return ObjectSets.EMPTY_SET; }
/*  71:    */    
/*  72: 72 */    public LongSet keySet() { return LongSets.EMPTY_SET; }
/*  73:    */    
/*  74: 74 */    public ShortCollection values() { return ShortSets.EMPTY_SET; }
/*  75: 75 */    public boolean containsValue(Object ov) { return false; }
/*  76: 76 */    private Object readResolve() { return Long2ShortMaps.EMPTY_MAP; }
/*  77: 77 */    public Object clone() { return Long2ShortMaps.EMPTY_MAP; }
/*  78: 78 */    public boolean isEmpty() { return true; }
/*  79:    */    
/*  80: 80 */    public ObjectSet<Map.Entry<Long, Short>> entrySet() { return long2ShortEntrySet(); }
/*  81:    */    
/*  82: 82 */    public int hashCode() { return 0; }
/*  83:    */    
/*  84:    */    public boolean equals(Object o) {
/*  85: 85 */      if (!(o instanceof Map)) { return false;
/*  86:    */      }
/*  87: 87 */      return ((Map)o).isEmpty();
/*  88:    */    }
/*  89:    */    
/*  90: 90 */    public String toString() { return "{}"; }
/*  91:    */  }
/*  92:    */  
/*  98: 98 */  public static final EmptyMap EMPTY_MAP = new EmptyMap();
/*  99:    */  
/* 101:    */  public static class Singleton
/* 102:    */    extends Long2ShortFunctions.Singleton
/* 103:    */    implements Long2ShortMap, Serializable, Cloneable
/* 104:    */  {
/* 105:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 106:    */    
/* 107:    */    protected volatile transient ObjectSet<Long2ShortMap.Entry> entries;
/* 108:    */    
/* 109:    */    protected volatile transient LongSet keys;
/* 110:    */    
/* 111:    */    protected volatile transient ShortCollection values;
/* 112:    */    
/* 114:    */    protected Singleton(long key, short value)
/* 115:    */    {
/* 116:116 */      super(value);
/* 117:    */    }
/* 118:    */    
/* 119:119 */    public boolean containsValue(short v) { return this.value == v; }
/* 120:    */    
/* 121:121 */    public boolean containsValue(Object ov) { return ((Short)ov).shortValue() == this.value; }
/* 122:    */    
/* 124:124 */    public void putAll(Map<? extends Long, ? extends Short> m) { throw new UnsupportedOperationException(); }
/* 125:    */    
/* 126:126 */    public ObjectSet<Long2ShortMap.Entry> long2ShortEntrySet() { if (this.entries == null) this.entries = ObjectSets.singleton(new SingletonEntry()); return this.entries; }
/* 127:127 */    public LongSet keySet() { if (this.keys == null) this.keys = LongSets.singleton(this.key); return this.keys; }
/* 128:128 */    public ShortCollection values() { if (this.values == null) this.values = ShortSets.singleton(this.value); return this.values; }
/* 129:    */    
/* 130:    */    protected class SingletonEntry implements Long2ShortMap.Entry, Map.Entry<Long, Short> { protected SingletonEntry() {}
/* 131:131 */      public Long getKey() { return Long.valueOf(Long2ShortMaps.Singleton.this.key); }
/* 132:132 */      public Short getValue() { return Short.valueOf(Long2ShortMaps.Singleton.this.value); }
/* 133:    */      
/* 134:    */      public long getLongKey() {
/* 135:135 */        return Long2ShortMaps.Singleton.this.key;
/* 136:    */      }
/* 137:    */      
/* 139:139 */      public short getShortValue() { return Long2ShortMaps.Singleton.this.value; }
/* 140:140 */      public short setValue(short value) { throw new UnsupportedOperationException(); }
/* 141:    */      
/* 143:143 */      public Short setValue(Short value) { throw new UnsupportedOperationException(); }
/* 144:    */      
/* 145:    */      public boolean equals(Object o) {
/* 146:146 */        if (!(o instanceof Map.Entry)) return false;
/* 147:147 */        Map.Entry<?, ?> e = (Map.Entry)o;
/* 148:    */        
/* 149:149 */        return (Long2ShortMaps.Singleton.this.key == ((Long)e.getKey()).longValue()) && (Long2ShortMaps.Singleton.this.value == ((Short)e.getValue()).shortValue());
/* 150:    */      }
/* 151:    */      
/* 152:152 */      public int hashCode() { return HashCommon.long2int(Long2ShortMaps.Singleton.this.key) ^ Long2ShortMaps.Singleton.this.value; }
/* 153:153 */      public String toString() { return Long2ShortMaps.Singleton.this.key + "->" + Long2ShortMaps.Singleton.this.value; }
/* 154:    */    }
/* 155:    */    
/* 156:156 */    public boolean isEmpty() { return false; }
/* 157:    */    
/* 159:159 */    public ObjectSet<Map.Entry<Long, Short>> entrySet() { return long2ShortEntrySet(); }
/* 160:    */    
/* 161:161 */    public int hashCode() { return HashCommon.long2int(this.key) ^ this.value; }
/* 162:    */    
/* 163:    */    public boolean equals(Object o) {
/* 164:164 */      if (o == this) return true;
/* 165:165 */      if (!(o instanceof Map)) { return false;
/* 166:    */      }
/* 167:167 */      Map<?, ?> m = (Map)o;
/* 168:168 */      if (m.size() != 1) return false;
/* 169:169 */      return ((Map.Entry)entrySet().iterator().next()).equals(m.entrySet().iterator().next());
/* 170:    */    }
/* 171:    */    
/* 172:172 */    public String toString() { return "{" + this.key + "=>" + this.value + "}"; }
/* 173:    */  }
/* 174:    */  
/* 183:    */  public static Long2ShortMap singleton(long key, short value)
/* 184:    */  {
/* 185:185 */    return new Singleton(key, value);
/* 186:    */  }
/* 187:    */  
/* 198:    */  public static Long2ShortMap singleton(Long key, Short value)
/* 199:    */  {
/* 200:200 */    return new Singleton(key.longValue(), value.shortValue());
/* 201:    */  }
/* 202:    */  
/* 204:    */  public static class SynchronizedMap
/* 205:    */    extends Long2ShortFunctions.SynchronizedFunction
/* 206:    */    implements Long2ShortMap, Serializable
/* 207:    */  {
/* 208:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 209:    */    
/* 210:    */    protected final Long2ShortMap map;
/* 211:    */    
/* 212:    */    protected volatile transient ObjectSet<Long2ShortMap.Entry> entries;
/* 213:    */    
/* 214:    */    protected volatile transient LongSet keys;
/* 215:    */    protected volatile transient ShortCollection values;
/* 216:    */    
/* 217:    */    protected SynchronizedMap(Long2ShortMap m, Object sync)
/* 218:    */    {
/* 219:219 */      super(sync);
/* 220:220 */      this.map = m;
/* 221:    */    }
/* 222:    */    
/* 223:    */    protected SynchronizedMap(Long2ShortMap m) {
/* 224:224 */      super();
/* 225:225 */      this.map = m;
/* 226:    */    }
/* 227:    */    
/* 228:228 */    public int size() { synchronized (this.sync) { return this.map.size(); } }
/* 229:229 */    public boolean containsKey(long k) { synchronized (this.sync) { return this.map.containsKey(k); } }
/* 230:230 */    public boolean containsValue(short v) { synchronized (this.sync) { return this.map.containsValue(v); } }
/* 231:    */    
/* 232:232 */    public short defaultReturnValue() { synchronized (this.sync) { return this.map.defaultReturnValue(); } }
/* 233:233 */    public void defaultReturnValue(short defRetValue) { synchronized (this.sync) { this.map.defaultReturnValue(defRetValue); } }
/* 234:    */    
/* 235:235 */    public short put(long k, short v) { synchronized (this.sync) { return this.map.put(k, v);
/* 236:    */      } }
/* 237:    */    
/* 238:238 */    public void putAll(Map<? extends Long, ? extends Short> m) { synchronized (this.sync) { this.map.putAll(m); } }
/* 239:    */    
/* 240:240 */    public ObjectSet<Long2ShortMap.Entry> long2ShortEntrySet() { if (this.entries == null) this.entries = ObjectSets.synchronize(this.map.long2ShortEntrySet(), this.sync); return this.entries; }
/* 241:241 */    public LongSet keySet() { if (this.keys == null) this.keys = LongSets.synchronize(this.map.keySet(), this.sync); return this.keys; }
/* 242:242 */    public ShortCollection values() { if (this.values == null) return ShortCollections.synchronize(this.map.values(), this.sync); return this.values; }
/* 243:    */    
/* 244:244 */    public void clear() { synchronized (this.sync) { this.map.clear(); } }
/* 245:245 */    public String toString() { synchronized (this.sync) { return this.map.toString();
/* 246:    */      } }
/* 247:    */    
/* 248:248 */    public Short put(Long k, Short v) { synchronized (this.sync) { return (Short)this.map.put(k, v);
/* 249:    */      }
/* 250:    */    }
/* 251:    */    
/* 252:252 */    public short remove(long k) { synchronized (this.sync) { return this.map.remove(k); } }
/* 253:253 */    public short get(long k) { synchronized (this.sync) { return this.map.get(k); } }
/* 254:254 */    public boolean containsKey(Object ok) { synchronized (this.sync) { return this.map.containsKey(ok);
/* 255:    */      }
/* 256:    */    }
/* 257:    */    
/* 258:258 */    public boolean containsValue(Object ov) { synchronized (this.sync) { return this.map.containsValue(ov);
/* 259:    */      }
/* 260:    */    }
/* 261:    */    
/* 264:    */    public boolean isEmpty()
/* 265:    */    {
/* 266:266 */      synchronized (this.sync) { return this.map.isEmpty(); } }
/* 267:267 */    public ObjectSet<Map.Entry<Long, Short>> entrySet() { synchronized (this.sync) { return this.map.entrySet(); } }
/* 268:    */    
/* 269:269 */    public int hashCode() { synchronized (this.sync) { return this.map.hashCode(); } }
/* 270:270 */    public boolean equals(Object o) { synchronized (this.sync) { return this.map.equals(o);
/* 271:    */      }
/* 272:    */    }
/* 273:    */  }
/* 274:    */  
/* 277:    */  public static Long2ShortMap synchronize(Long2ShortMap m)
/* 278:    */  {
/* 279:279 */    return new SynchronizedMap(m);
/* 280:    */  }
/* 281:    */  
/* 287:    */  public static Long2ShortMap synchronize(Long2ShortMap m, Object sync)
/* 288:    */  {
/* 289:289 */    return new SynchronizedMap(m, sync);
/* 290:    */  }
/* 291:    */  
/* 293:    */  public static class UnmodifiableMap
/* 294:    */    extends Long2ShortFunctions.UnmodifiableFunction
/* 295:    */    implements Long2ShortMap, Serializable
/* 296:    */  {
/* 297:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 298:    */    
/* 299:    */    protected final Long2ShortMap map;
/* 300:    */    protected volatile transient ObjectSet<Long2ShortMap.Entry> entries;
/* 301:    */    protected volatile transient LongSet keys;
/* 302:    */    protected volatile transient ShortCollection values;
/* 303:    */    
/* 304:    */    protected UnmodifiableMap(Long2ShortMap m)
/* 305:    */    {
/* 306:306 */      super();
/* 307:307 */      this.map = m;
/* 308:    */    }
/* 309:    */    
/* 310:310 */    public int size() { return this.map.size(); }
/* 311:311 */    public boolean containsKey(long k) { return this.map.containsKey(k); }
/* 312:312 */    public boolean containsValue(short v) { return this.map.containsValue(v); }
/* 313:    */    
/* 314:314 */    public short defaultReturnValue() { throw new UnsupportedOperationException(); }
/* 315:315 */    public void defaultReturnValue(short defRetValue) { throw new UnsupportedOperationException(); }
/* 316:    */    
/* 317:317 */    public short put(long k, short v) { throw new UnsupportedOperationException(); }
/* 318:    */    
/* 320:320 */    public void putAll(Map<? extends Long, ? extends Short> m) { throw new UnsupportedOperationException(); }
/* 321:    */    
/* 322:322 */    public ObjectSet<Long2ShortMap.Entry> long2ShortEntrySet() { if (this.entries == null) this.entries = ObjectSets.unmodifiable(this.map.long2ShortEntrySet()); return this.entries; }
/* 323:323 */    public LongSet keySet() { if (this.keys == null) this.keys = LongSets.unmodifiable(this.map.keySet()); return this.keys; }
/* 324:324 */    public ShortCollection values() { if (this.values == null) return ShortCollections.unmodifiable(this.map.values()); return this.values; }
/* 325:    */    
/* 326:326 */    public void clear() { throw new UnsupportedOperationException(); }
/* 327:327 */    public String toString() { return this.map.toString(); }
/* 328:    */    
/* 329:    */    public Short put(Long k, Short v) {
/* 330:330 */      throw new UnsupportedOperationException();
/* 331:    */    }
/* 332:    */    
/* 334:334 */    public short remove(long k) { throw new UnsupportedOperationException(); }
/* 335:335 */    public short get(long k) { return this.map.get(k); }
/* 336:336 */    public boolean containsKey(Object ok) { return this.map.containsKey(ok); }
/* 337:    */    
/* 338:    */    public boolean containsValue(Object ov)
/* 339:    */    {
/* 340:340 */      return this.map.containsValue(ov);
/* 341:    */    }
/* 342:    */    
/* 348:348 */    public boolean isEmpty() { return this.map.isEmpty(); }
/* 349:349 */    public ObjectSet<Map.Entry<Long, Short>> entrySet() { return ObjectSets.unmodifiable(this.map.entrySet()); }
/* 350:    */  }
/* 351:    */  
/* 356:    */  public static Long2ShortMap unmodifiable(Long2ShortMap m)
/* 357:    */  {
/* 358:358 */    return new UnmodifiableMap(m);
/* 359:    */  }
/* 360:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ShortMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */