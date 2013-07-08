/*   1:    */package it.unimi.dsi.fastutil.ints;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   4:    */import it.unimi.dsi.fastutil.objects.ObjectCollections;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*   6:    */import it.unimi.dsi.fastutil.objects.ObjectSet;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectSets;
/*   8:    */import java.io.Serializable;
/*   9:    */import java.util.Iterator;
/*  10:    */import java.util.Map;
/*  11:    */import java.util.Map.Entry;
/*  12:    */import java.util.Set;
/*  13:    */
/*  57:    */public class Int2ObjectMaps
/*  58:    */{
/*  59:    */  public static class EmptyMap<V>
/*  60:    */    extends Int2ObjectFunctions.EmptyFunction<V>
/*  61:    */    implements Int2ObjectMap<V>, Serializable, Cloneable
/*  62:    */  {
/*  63:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  64:    */    
/*  65: 65 */    public boolean containsValue(Object v) { return false; }
/*  66: 66 */    public void putAll(Map<? extends Integer, ? extends V> m) { throw new UnsupportedOperationException(); }
/*  67:    */    
/*  68: 68 */    public ObjectSet<Int2ObjectMap.Entry<V>> int2ObjectEntrySet() { return ObjectSets.EMPTY_SET; }
/*  69:    */    
/*  70: 70 */    public IntSet keySet() { return IntSets.EMPTY_SET; }
/*  71:    */    
/*  72: 72 */    public ObjectCollection<V> values() { return ObjectSets.EMPTY_SET; }
/*  73: 73 */    private Object readResolve() { return Int2ObjectMaps.EMPTY_MAP; }
/*  74:    */    
/*  75: 75 */    public Object clone() { return Int2ObjectMaps.EMPTY_MAP; }
/*  76:    */    
/*  77: 77 */    public boolean isEmpty() { return true; }
/*  78:    */    
/*  80: 80 */    public ObjectSet<Map.Entry<Integer, V>> entrySet() { return int2ObjectEntrySet(); }
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
/* 101:    */  public static class Singleton<V>
/* 102:    */    extends Int2ObjectFunctions.Singleton<V>
/* 103:    */    implements Int2ObjectMap<V>, Serializable, Cloneable
/* 104:    */  {
/* 105:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 106:    */    
/* 107:    */    protected volatile transient ObjectSet<Int2ObjectMap.Entry<V>> entries;
/* 108:    */    
/* 109:    */    protected volatile transient IntSet keys;
/* 110:    */    
/* 111:    */    protected volatile transient ObjectCollection<V> values;
/* 112:    */    
/* 114:    */    protected Singleton(int key, V value)
/* 115:    */    {
/* 116:116 */      super(value);
/* 117:    */    }
/* 118:    */    
/* 119:119 */    public boolean containsValue(Object v) { return this.value == null ? false : v == null ? true : this.value.equals(v); }
/* 120:    */    
/* 124:124 */    public void putAll(Map<? extends Integer, ? extends V> m) { throw new UnsupportedOperationException(); }
/* 125:    */    
/* 126:126 */    public ObjectSet<Int2ObjectMap.Entry<V>> int2ObjectEntrySet() { if (this.entries == null) this.entries = ObjectSets.singleton(new SingletonEntry()); return this.entries; }
/* 127:127 */    public IntSet keySet() { if (this.keys == null) this.keys = IntSets.singleton(this.key); return this.keys; }
/* 128:128 */    public ObjectCollection<V> values() { if (this.values == null) this.values = ObjectSets.singleton(this.value); return this.values; }
/* 129:    */    
/* 130:    */    protected class SingletonEntry implements Int2ObjectMap.Entry<V>, Map.Entry<Integer, V> { protected SingletonEntry() {}
/* 131:131 */      public Integer getKey() { return Integer.valueOf(Int2ObjectMaps.Singleton.this.key); }
/* 132:132 */      public V getValue() { return Int2ObjectMaps.Singleton.this.value; }
/* 133:    */      
/* 134:    */      public int getIntKey() {
/* 135:135 */        return Int2ObjectMaps.Singleton.this.key;
/* 136:    */      }
/* 137:    */      
/* 143:143 */      public V setValue(V value) { throw new UnsupportedOperationException(); }
/* 144:    */      
/* 145:    */      public boolean equals(Object o) {
/* 146:146 */        if (!(o instanceof Map.Entry)) return false;
/* 147:147 */        Map.Entry<?, ?> e = (Map.Entry)o;
/* 148:    */        
/* 149:149 */        return (Int2ObjectMaps.Singleton.this.key == ((Integer)e.getKey()).intValue()) && (Int2ObjectMaps.Singleton.this.value == null ? e.getValue() == null : Int2ObjectMaps.Singleton.this.value.equals(e.getValue()));
/* 150:    */      }
/* 151:    */      
/* 152:152 */      public int hashCode() { return Int2ObjectMaps.Singleton.this.key ^ (Int2ObjectMaps.Singleton.this.value == null ? 0 : Int2ObjectMaps.Singleton.this.value.hashCode()); }
/* 153:153 */      public String toString() { return Int2ObjectMaps.Singleton.this.key + "->" + Int2ObjectMaps.Singleton.this.value; }
/* 154:    */    }
/* 155:    */    
/* 156:156 */    public boolean isEmpty() { return false; }
/* 157:    */    
/* 159:159 */    public ObjectSet<Map.Entry<Integer, V>> entrySet() { return int2ObjectEntrySet(); }
/* 160:    */    
/* 161:161 */    public int hashCode() { return this.key ^ (this.value == null ? 0 : this.value.hashCode()); }
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
/* 183:    */  public static <V> Int2ObjectMap<V> singleton(int key, V value)
/* 184:    */  {
/* 185:185 */    return new Singleton(key, value);
/* 186:    */  }
/* 187:    */  
/* 198:    */  public static <V> Int2ObjectMap<V> singleton(Integer key, V value)
/* 199:    */  {
/* 200:200 */    return new Singleton(key.intValue(), value);
/* 201:    */  }
/* 202:    */  
/* 204:    */  public static class SynchronizedMap<V>
/* 205:    */    extends Int2ObjectFunctions.SynchronizedFunction<V>
/* 206:    */    implements Int2ObjectMap<V>, Serializable
/* 207:    */  {
/* 208:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 209:    */    
/* 210:    */    protected final Int2ObjectMap<V> map;
/* 211:    */    
/* 212:    */    protected volatile transient ObjectSet<Int2ObjectMap.Entry<V>> entries;
/* 213:    */    
/* 214:    */    protected volatile transient IntSet keys;
/* 215:    */    protected volatile transient ObjectCollection<V> values;
/* 216:    */    
/* 217:    */    protected SynchronizedMap(Int2ObjectMap<V> m, Object sync)
/* 218:    */    {
/* 219:219 */      super(sync);
/* 220:220 */      this.map = m;
/* 221:    */    }
/* 222:    */    
/* 223:    */    protected SynchronizedMap(Int2ObjectMap<V> m) {
/* 224:224 */      super();
/* 225:225 */      this.map = m;
/* 226:    */    }
/* 227:    */    
/* 228:228 */    public int size() { synchronized (this.sync) { return this.map.size(); } }
/* 229:229 */    public boolean containsKey(int k) { synchronized (this.sync) { return this.map.containsKey(k); } }
/* 230:230 */    public boolean containsValue(Object v) { synchronized (this.sync) { return this.map.containsValue(v); } }
/* 231:    */    
/* 232:232 */    public V defaultReturnValue() { synchronized (this.sync) { return this.map.defaultReturnValue(); } }
/* 233:233 */    public void defaultReturnValue(V defRetValue) { synchronized (this.sync) { this.map.defaultReturnValue(defRetValue); } }
/* 234:    */    
/* 235:235 */    public V put(int k, V v) { synchronized (this.sync) { return this.map.put(k, v);
/* 236:    */      } }
/* 237:    */    
/* 238:238 */    public void putAll(Map<? extends Integer, ? extends V> m) { synchronized (this.sync) { this.map.putAll(m); } }
/* 239:    */    
/* 240:240 */    public ObjectSet<Int2ObjectMap.Entry<V>> int2ObjectEntrySet() { if (this.entries == null) this.entries = ObjectSets.synchronize(this.map.int2ObjectEntrySet(), this.sync); return this.entries; }
/* 241:241 */    public IntSet keySet() { if (this.keys == null) this.keys = IntSets.synchronize(this.map.keySet(), this.sync); return this.keys; }
/* 242:242 */    public ObjectCollection<V> values() { if (this.values == null) return ObjectCollections.synchronize(this.map.values(), this.sync); return this.values; }
/* 243:    */    
/* 244:244 */    public void clear() { synchronized (this.sync) { this.map.clear(); } }
/* 245:245 */    public String toString() { synchronized (this.sync) { return this.map.toString();
/* 246:    */      } }
/* 247:    */    
/* 248:248 */    public V put(Integer k, V v) { synchronized (this.sync) { return this.map.put(k, v);
/* 249:    */      }
/* 250:    */    }
/* 251:    */    
/* 252:252 */    public V remove(int k) { synchronized (this.sync) { return this.map.remove(k); } }
/* 253:253 */    public V get(int k) { synchronized (this.sync) { return this.map.get(k); } }
/* 254:254 */    public boolean containsKey(Object ok) { synchronized (this.sync) { return this.map.containsKey(ok); } }
/* 255:255 */    public boolean isEmpty() { synchronized (this.sync) { return this.map.isEmpty(); } }
/* 256:256 */    public ObjectSet<Map.Entry<Integer, V>> entrySet() { synchronized (this.sync) { return this.map.entrySet(); } }
/* 257:257 */    public int hashCode() { synchronized (this.sync) { return this.map.hashCode(); } }
/* 258:258 */    public boolean equals(Object o) { synchronized (this.sync) { return this.map.equals(o);
/* 259:    */      }
/* 260:    */    }
/* 261:    */  }
/* 262:    */  
/* 264:    */  public static <V> Int2ObjectMap<V> synchronize(Int2ObjectMap<V> m)
/* 265:    */  {
/* 266:266 */    return new SynchronizedMap(m);
/* 267:    */  }
/* 268:    */  
/* 274:274 */  public static <V> Int2ObjectMap<V> synchronize(Int2ObjectMap<V> m, Object sync) { return new SynchronizedMap(m, sync); }
/* 275:    */  
/* 276:    */  public static class UnmodifiableMap<V> extends Int2ObjectFunctions.UnmodifiableFunction<V> implements Int2ObjectMap<V>, Serializable {
/* 277:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 278:    */    protected final Int2ObjectMap<V> map;
/* 279:    */    protected volatile transient ObjectSet<Int2ObjectMap.Entry<V>> entries;
/* 280:    */    protected volatile transient IntSet keys;
/* 281:    */    protected volatile transient ObjectCollection<V> values;
/* 282:    */    
/* 283:283 */    protected UnmodifiableMap(Int2ObjectMap<V> m) { super();
/* 284:284 */      this.map = m; }
/* 285:    */    
/* 286:286 */    public int size() { return this.map.size(); }
/* 287:287 */    public boolean containsKey(int k) { return this.map.containsKey(k); }
/* 288:288 */    public boolean containsValue(Object v) { return this.map.containsValue(v); }
/* 289:289 */    public V defaultReturnValue() { throw new UnsupportedOperationException(); }
/* 290:290 */    public void defaultReturnValue(V defRetValue) { throw new UnsupportedOperationException(); }
/* 291:291 */    public V put(int k, V v) { throw new UnsupportedOperationException(); }
/* 292:    */    
/* 293:293 */    public void putAll(Map<? extends Integer, ? extends V> m) { throw new UnsupportedOperationException(); }
/* 294:294 */    public ObjectSet<Int2ObjectMap.Entry<V>> int2ObjectEntrySet() { if (this.entries == null) this.entries = ObjectSets.unmodifiable(this.map.int2ObjectEntrySet()); return this.entries; }
/* 295:295 */    public IntSet keySet() { if (this.keys == null) this.keys = IntSets.unmodifiable(this.map.keySet()); return this.keys; }
/* 296:296 */    public ObjectCollection<V> values() { if (this.values == null) return ObjectCollections.unmodifiable(this.map.values()); return this.values; }
/* 297:297 */    public void clear() { throw new UnsupportedOperationException(); }
/* 298:298 */    public String toString() { return this.map.toString(); }
/* 299:299 */    public V remove(int k) { throw new UnsupportedOperationException(); }
/* 300:300 */    public V get(int k) { return this.map.get(k); }
/* 301:301 */    public boolean containsKey(Object ok) { return this.map.containsKey(ok); }
/* 302:302 */    public V remove(Object k) { throw new UnsupportedOperationException(); }
/* 303:303 */    public V get(Object k) { return this.map.get(k); }
/* 304:304 */    public boolean isEmpty() { return this.map.isEmpty(); }
/* 305:305 */    public ObjectSet<Map.Entry<Integer, V>> entrySet() { return ObjectSets.unmodifiable(this.map.entrySet()); }
/* 306:    */  }
/* 307:    */  
/* 311:    */  public static <V> Int2ObjectMap<V> unmodifiable(Int2ObjectMap<V> m)
/* 312:    */  {
/* 313:313 */    return new UnmodifiableMap(m);
/* 314:    */  }
/* 315:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ObjectMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */