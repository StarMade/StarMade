/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*   4:    */import it.unimi.dsi.fastutil.booleans.BooleanCollections;
/*   5:    */import it.unimi.dsi.fastutil.booleans.BooleanSets;
/*   6:    */import java.io.Serializable;
/*   7:    */import java.util.Iterator;
/*   8:    */import java.util.Map;
/*   9:    */import java.util.Map.Entry;
/*  10:    */import java.util.Set;
/*  11:    */
/*  58:    */public class Object2BooleanMaps
/*  59:    */{
/*  60:    */  public static class EmptyMap<K>
/*  61:    */    extends Object2BooleanFunctions.EmptyFunction<K>
/*  62:    */    implements Object2BooleanMap<K>, Serializable, Cloneable
/*  63:    */  {
/*  64:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  65:    */    
/*  66: 66 */    public boolean containsValue(boolean v) { return false; }
/*  67: 67 */    public void putAll(Map<? extends K, ? extends Boolean> m) { throw new UnsupportedOperationException(); }
/*  68:    */    
/*  69: 69 */    public ObjectSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet() { return ObjectSets.EMPTY_SET; }
/*  70:    */    
/*  71: 71 */    public ObjectSet<K> keySet() { return ObjectSets.EMPTY_SET; }
/*  72:    */    
/*  73: 73 */    public BooleanCollection values() { return BooleanSets.EMPTY_SET; }
/*  74: 74 */    public boolean containsValue(Object ov) { return false; }
/*  75: 75 */    private Object readResolve() { return Object2BooleanMaps.EMPTY_MAP; }
/*  76: 76 */    public Object clone() { return Object2BooleanMaps.EMPTY_MAP; }
/*  77: 77 */    public boolean isEmpty() { return true; }
/*  78:    */    
/*  80: 80 */    public ObjectSet<Map.Entry<K, Boolean>> entrySet() { return object2BooleanEntrySet(); }
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
/* 101:    */  public static class Singleton<K>
/* 102:    */    extends Object2BooleanFunctions.Singleton<K>
/* 103:    */    implements Object2BooleanMap<K>, Serializable, Cloneable
/* 104:    */  {
/* 105:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 106:    */    
/* 107:    */    protected volatile transient ObjectSet<Object2BooleanMap.Entry<K>> entries;
/* 108:    */    
/* 109:    */    protected volatile transient ObjectSet<K> keys;
/* 110:    */    
/* 111:    */    protected volatile transient BooleanCollection values;
/* 112:    */    
/* 114:    */    protected Singleton(K key, boolean value)
/* 115:    */    {
/* 116:116 */      super(value);
/* 117:    */    }
/* 118:    */    
/* 119:119 */    public boolean containsValue(boolean v) { return this.value == v; }
/* 120:    */    
/* 121:121 */    public boolean containsValue(Object ov) { return ((Boolean)ov).booleanValue() == this.value; }
/* 122:    */    
/* 124:124 */    public void putAll(Map<? extends K, ? extends Boolean> m) { throw new UnsupportedOperationException(); }
/* 125:    */    
/* 126:126 */    public ObjectSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet() { if (this.entries == null) this.entries = ObjectSets.singleton(new SingletonEntry()); return this.entries; }
/* 127:127 */    public ObjectSet<K> keySet() { if (this.keys == null) this.keys = ObjectSets.singleton(this.key); return this.keys; }
/* 128:128 */    public BooleanCollection values() { if (this.values == null) this.values = BooleanSets.singleton(this.value); return this.values; }
/* 129:    */    
/* 130:    */    protected class SingletonEntry implements Object2BooleanMap.Entry<K>, Map.Entry<K, Boolean> { protected SingletonEntry() {}
/* 131:131 */      public K getKey() { return Object2BooleanMaps.Singleton.this.key; }
/* 132:132 */      public Boolean getValue() { return Boolean.valueOf(Object2BooleanMaps.Singleton.this.value); }
/* 133:    */      
/* 139:139 */      public boolean getBooleanValue() { return Object2BooleanMaps.Singleton.this.value; }
/* 140:140 */      public boolean setValue(boolean value) { throw new UnsupportedOperationException(); }
/* 141:    */      
/* 143:143 */      public Boolean setValue(Boolean value) { throw new UnsupportedOperationException(); }
/* 144:    */      
/* 145:    */      public boolean equals(Object o) {
/* 146:146 */        if (!(o instanceof Map.Entry)) return false;
/* 147:147 */        Map.Entry<?, ?> e = (Map.Entry)o;
/* 148:    */        
/* 149:149 */        return (Object2BooleanMaps.Singleton.this.key == null ? e.getKey() == null : Object2BooleanMaps.Singleton.this.key.equals(e.getKey())) && (Object2BooleanMaps.Singleton.this.value == ((Boolean)e.getValue()).booleanValue());
/* 150:    */      }
/* 151:    */      
/* 152:152 */      public int hashCode() { return (Object2BooleanMaps.Singleton.this.key == null ? 0 : Object2BooleanMaps.Singleton.this.key.hashCode()) ^ (Object2BooleanMaps.Singleton.this.value ? 1231 : 1237); }
/* 153:153 */      public String toString() { return Object2BooleanMaps.Singleton.this.key + "->" + Object2BooleanMaps.Singleton.this.value; }
/* 154:    */    }
/* 155:    */    
/* 156:156 */    public boolean isEmpty() { return false; }
/* 157:    */    
/* 159:159 */    public ObjectSet<Map.Entry<K, Boolean>> entrySet() { return object2BooleanEntrySet(); }
/* 160:    */    
/* 161:161 */    public int hashCode() { return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value ? 1231 : 1237); }
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
/* 183:    */  public static <K> Object2BooleanMap<K> singleton(K key, boolean value)
/* 184:    */  {
/* 185:185 */    return new Singleton(key, value);
/* 186:    */  }
/* 187:    */  
/* 198:    */  public static <K> Object2BooleanMap<K> singleton(K key, Boolean value)
/* 199:    */  {
/* 200:200 */    return new Singleton(key, value.booleanValue());
/* 201:    */  }
/* 202:    */  
/* 204:    */  public static class SynchronizedMap<K>
/* 205:    */    extends Object2BooleanFunctions.SynchronizedFunction<K>
/* 206:    */    implements Object2BooleanMap<K>, Serializable
/* 207:    */  {
/* 208:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 209:    */    
/* 210:    */    protected final Object2BooleanMap<K> map;
/* 211:    */    
/* 212:    */    protected volatile transient ObjectSet<Object2BooleanMap.Entry<K>> entries;
/* 213:    */    
/* 214:    */    protected volatile transient ObjectSet<K> keys;
/* 215:    */    protected volatile transient BooleanCollection values;
/* 216:    */    
/* 217:    */    protected SynchronizedMap(Object2BooleanMap<K> m, Object sync)
/* 218:    */    {
/* 219:219 */      super(sync);
/* 220:220 */      this.map = m;
/* 221:    */    }
/* 222:    */    
/* 223:    */    protected SynchronizedMap(Object2BooleanMap<K> m) {
/* 224:224 */      super();
/* 225:225 */      this.map = m;
/* 226:    */    }
/* 227:    */    
/* 228:228 */    public int size() { synchronized (this.sync) { return this.map.size(); } }
/* 229:229 */    public boolean containsKey(Object k) { synchronized (this.sync) { return this.map.containsKey(k); } }
/* 230:230 */    public boolean containsValue(boolean v) { synchronized (this.sync) { return this.map.containsValue(v); } }
/* 231:    */    
/* 232:232 */    public boolean defaultReturnValue() { synchronized (this.sync) { return this.map.defaultReturnValue(); } }
/* 233:233 */    public void defaultReturnValue(boolean defRetValue) { synchronized (this.sync) { this.map.defaultReturnValue(defRetValue); } }
/* 234:    */    
/* 235:235 */    public boolean put(K k, boolean v) { synchronized (this.sync) { return this.map.put(k, v);
/* 236:    */      } }
/* 237:    */    
/* 238:238 */    public void putAll(Map<? extends K, ? extends Boolean> m) { synchronized (this.sync) { this.map.putAll(m); } }
/* 239:    */    
/* 240:240 */    public ObjectSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet() { if (this.entries == null) this.entries = ObjectSets.synchronize(this.map.object2BooleanEntrySet(), this.sync); return this.entries; }
/* 241:241 */    public ObjectSet<K> keySet() { if (this.keys == null) this.keys = ObjectSets.synchronize(this.map.keySet(), this.sync); return this.keys; }
/* 242:242 */    public BooleanCollection values() { if (this.values == null) return BooleanCollections.synchronize(this.map.values(), this.sync); return this.values; }
/* 243:    */    
/* 244:244 */    public void clear() { synchronized (this.sync) { this.map.clear(); } }
/* 245:245 */    public String toString() { synchronized (this.sync) { return this.map.toString();
/* 246:    */      } }
/* 247:    */    
/* 248:248 */    public Boolean put(K k, Boolean v) { synchronized (this.sync) { return (Boolean)this.map.put(k, v); } }
/* 249:249 */    public boolean containsValue(Object ov) { synchronized (this.sync) { return this.map.containsValue(ov); } }
/* 250:250 */    public boolean removeBoolean(Object k) { synchronized (this.sync) { return this.map.removeBoolean(k); } }
/* 251:251 */    public boolean getBoolean(Object k) { synchronized (this.sync) { return this.map.getBoolean(k); } }
/* 252:252 */    public boolean isEmpty() { synchronized (this.sync) { return this.map.isEmpty(); } }
/* 253:253 */    public ObjectSet<Map.Entry<K, Boolean>> entrySet() { synchronized (this.sync) { return this.map.entrySet(); } }
/* 254:254 */    public int hashCode() { synchronized (this.sync) { return this.map.hashCode(); } }
/* 255:255 */    public boolean equals(Object o) { synchronized (this.sync) { return this.map.equals(o);
/* 256:    */      }
/* 257:    */    }
/* 258:    */  }
/* 259:    */  
/* 261:    */  public static <K> Object2BooleanMap<K> synchronize(Object2BooleanMap<K> m)
/* 262:    */  {
/* 263:263 */    return new SynchronizedMap(m);
/* 264:    */  }
/* 265:    */  
/* 271:271 */  public static <K> Object2BooleanMap<K> synchronize(Object2BooleanMap<K> m, Object sync) { return new SynchronizedMap(m, sync); }
/* 272:    */  
/* 273:    */  public static class UnmodifiableMap<K> extends Object2BooleanFunctions.UnmodifiableFunction<K> implements Object2BooleanMap<K>, Serializable {
/* 274:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 275:    */    protected final Object2BooleanMap<K> map;
/* 276:    */    protected volatile transient ObjectSet<Object2BooleanMap.Entry<K>> entries;
/* 277:    */    protected volatile transient ObjectSet<K> keys;
/* 278:    */    protected volatile transient BooleanCollection values;
/* 279:    */    
/* 280:280 */    protected UnmodifiableMap(Object2BooleanMap<K> m) { super();
/* 281:281 */      this.map = m; }
/* 282:    */    
/* 283:283 */    public int size() { return this.map.size(); }
/* 284:284 */    public boolean containsKey(Object k) { return this.map.containsKey(k); }
/* 285:285 */    public boolean containsValue(boolean v) { return this.map.containsValue(v); }
/* 286:286 */    public boolean defaultReturnValue() { throw new UnsupportedOperationException(); }
/* 287:287 */    public void defaultReturnValue(boolean defRetValue) { throw new UnsupportedOperationException(); }
/* 288:288 */    public boolean put(K k, boolean v) { throw new UnsupportedOperationException(); }
/* 289:    */    
/* 290:290 */    public void putAll(Map<? extends K, ? extends Boolean> m) { throw new UnsupportedOperationException(); }
/* 291:291 */    public ObjectSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet() { if (this.entries == null) this.entries = ObjectSets.unmodifiable(this.map.object2BooleanEntrySet()); return this.entries; }
/* 292:292 */    public ObjectSet<K> keySet() { if (this.keys == null) this.keys = ObjectSets.unmodifiable(this.map.keySet()); return this.keys; }
/* 293:293 */    public BooleanCollection values() { if (this.values == null) return BooleanCollections.unmodifiable(this.map.values()); return this.values; }
/* 294:294 */    public void clear() { throw new UnsupportedOperationException(); }
/* 295:295 */    public String toString() { return this.map.toString(); }
/* 296:296 */    public boolean containsValue(Object ov) { return this.map.containsValue(ov); }
/* 297:297 */    public boolean removeBoolean(Object k) { throw new UnsupportedOperationException(); }
/* 298:298 */    public boolean getBoolean(Object k) { return this.map.getBoolean(k); }
/* 299:299 */    public boolean isEmpty() { return this.map.isEmpty(); }
/* 300:300 */    public ObjectSet<Map.Entry<K, Boolean>> entrySet() { return ObjectSets.unmodifiable(this.map.entrySet()); }
/* 301:    */  }
/* 302:    */  
/* 306:    */  public static <K> Object2BooleanMap<K> unmodifiable(Object2BooleanMap<K> m)
/* 307:    */  {
/* 308:308 */    return new UnmodifiableMap(m);
/* 309:    */  }
/* 310:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2BooleanMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */