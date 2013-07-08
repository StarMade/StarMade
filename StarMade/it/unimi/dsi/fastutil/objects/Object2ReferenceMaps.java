/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.Map;
/*   6:    */import java.util.Map.Entry;
/*   7:    */import java.util.Set;
/*   8:    */
/*  57:    */public class Object2ReferenceMaps
/*  58:    */{
/*  59:    */  public static class EmptyMap<K, V>
/*  60:    */    extends Object2ReferenceFunctions.EmptyFunction<K, V>
/*  61:    */    implements Object2ReferenceMap<K, V>, Serializable, Cloneable
/*  62:    */  {
/*  63:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  64:    */    
/*  65: 65 */    public boolean containsValue(Object v) { return false; }
/*  66: 66 */    public void putAll(Map<? extends K, ? extends V> m) { throw new UnsupportedOperationException(); }
/*  67:    */    
/*  68: 68 */    public ObjectSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() { return ObjectSets.EMPTY_SET; }
/*  69:    */    
/*  70: 70 */    public ObjectSet<K> keySet() { return ObjectSets.EMPTY_SET; }
/*  71:    */    
/*  72: 72 */    public ReferenceCollection<V> values() { return ReferenceSets.EMPTY_SET; }
/*  73: 73 */    private Object readResolve() { return Object2ReferenceMaps.EMPTY_MAP; }
/*  74:    */    
/*  75: 75 */    public Object clone() { return Object2ReferenceMaps.EMPTY_MAP; }
/*  76:    */    
/*  77: 77 */    public boolean isEmpty() { return true; }
/*  78:    */    
/*  80: 80 */    public ObjectSet<Map.Entry<K, V>> entrySet() { return object2ReferenceEntrySet(); }
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
/* 101:    */  public static class Singleton<K, V>
/* 102:    */    extends Object2ReferenceFunctions.Singleton<K, V>
/* 103:    */    implements Object2ReferenceMap<K, V>, Serializable, Cloneable
/* 104:    */  {
/* 105:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 106:    */    
/* 107:    */    protected volatile transient ObjectSet<Object2ReferenceMap.Entry<K, V>> entries;
/* 108:    */    
/* 109:    */    protected volatile transient ObjectSet<K> keys;
/* 110:    */    
/* 111:    */    protected volatile transient ReferenceCollection<V> values;
/* 112:    */    
/* 114:    */    protected Singleton(K key, V value)
/* 115:    */    {
/* 116:116 */      super(value);
/* 117:    */    }
/* 118:    */    
/* 119:119 */    public boolean containsValue(Object v) { return this.value == v; }
/* 120:    */    
/* 124:124 */    public void putAll(Map<? extends K, ? extends V> m) { throw new UnsupportedOperationException(); }
/* 125:    */    
/* 126:126 */    public ObjectSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSets.singleton(new SingletonEntry()); return this.entries; }
/* 127:127 */    public ObjectSet<K> keySet() { if (this.keys == null) this.keys = ObjectSets.singleton(this.key); return this.keys; }
/* 128:128 */    public ReferenceCollection<V> values() { if (this.values == null) this.values = ReferenceSets.singleton(this.value); return this.values; }
/* 129:    */    
/* 130:    */    protected class SingletonEntry implements Object2ReferenceMap.Entry<K, V>, Map.Entry<K, V> { protected SingletonEntry() {}
/* 131:131 */      public K getKey() { return Object2ReferenceMaps.Singleton.this.key; }
/* 132:132 */      public V getValue() { return Object2ReferenceMaps.Singleton.this.value; }
/* 133:133 */      public V setValue(V value) { throw new UnsupportedOperationException(); }
/* 134:    */      
/* 135:135 */      public boolean equals(Object o) { if (!(o instanceof Map.Entry)) return false;
/* 136:136 */        Map.Entry<?, ?> e = (Map.Entry)o;
/* 137:137 */        return (Object2ReferenceMaps.Singleton.this.key == null ? e.getKey() == null : Object2ReferenceMaps.Singleton.this.key.equals(e.getKey())) && (Object2ReferenceMaps.Singleton.this.value == e.getValue()); }
/* 138:    */      
/* 139:139 */      public int hashCode() { return (Object2ReferenceMaps.Singleton.this.key == null ? 0 : Object2ReferenceMaps.Singleton.this.key.hashCode()) ^ (Object2ReferenceMaps.Singleton.this.value == null ? 0 : System.identityHashCode(Object2ReferenceMaps.Singleton.this.value)); }
/* 140:140 */      public String toString() { return Object2ReferenceMaps.Singleton.this.key + "->" + Object2ReferenceMaps.Singleton.this.value; } }
/* 141:    */    
/* 142:142 */    public boolean isEmpty() { return false; }
/* 143:    */    
/* 144:144 */    public ObjectSet<Map.Entry<K, V>> entrySet() { return object2ReferenceEntrySet(); }
/* 145:145 */    public int hashCode() { return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : System.identityHashCode(this.value)); }
/* 146:    */    
/* 147:147 */    public boolean equals(Object o) { if (o == this) return true;
/* 148:148 */      if (!(o instanceof Map)) return false;
/* 149:149 */      Map<?, ?> m = (Map)o;
/* 150:150 */      if (m.size() != 1) return false;
/* 151:151 */      return ((Map.Entry)entrySet().iterator().next()).equals(m.entrySet().iterator().next()); }
/* 152:    */    
/* 153:153 */    public String toString() { return "{" + this.key + "=>" + this.value + "}"; }
/* 154:    */  }
/* 155:    */  
/* 164:164 */  public static <K, V> Object2ReferenceMap<K, V> singleton(K key, V value) { return new Singleton(key, value); }
/* 165:    */  
/* 166:    */  public static class SynchronizedMap<K, V> extends Object2ReferenceFunctions.SynchronizedFunction<K, V> implements Object2ReferenceMap<K, V>, Serializable {
/* 167:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 168:    */    protected final Object2ReferenceMap<K, V> map;
/* 169:    */    protected volatile transient ObjectSet<Object2ReferenceMap.Entry<K, V>> entries;
/* 170:    */    protected volatile transient ObjectSet<K> keys;
/* 171:    */    protected volatile transient ReferenceCollection<V> values;
/* 172:    */    
/* 173:    */    protected SynchronizedMap(Object2ReferenceMap<K, V> m, Object sync) {
/* 174:174 */      super(sync);
/* 175:175 */      this.map = m;
/* 176:    */    }
/* 177:    */    
/* 178:178 */    protected SynchronizedMap(Object2ReferenceMap<K, V> m) { super();
/* 179:179 */      this.map = m; }
/* 180:    */    
/* 181:181 */    public int size() { synchronized (this.sync) { return this.map.size(); } }
/* 182:182 */    public boolean containsKey(Object k) { synchronized (this.sync) { return this.map.containsKey(k); } }
/* 183:183 */    public boolean containsValue(Object v) { synchronized (this.sync) { return this.map.containsValue(v); } }
/* 184:184 */    public V defaultReturnValue() { synchronized (this.sync) { return this.map.defaultReturnValue(); } }
/* 185:185 */    public void defaultReturnValue(V defRetValue) { synchronized (this.sync) { this.map.defaultReturnValue(defRetValue); } }
/* 186:186 */    public V put(K k, V v) { synchronized (this.sync) { return this.map.put(k, v); } }
/* 187:    */    
/* 188:188 */    public void putAll(Map<? extends K, ? extends V> m) { synchronized (this.sync) { this.map.putAll(m); } }
/* 189:189 */    public ObjectSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSets.synchronize(this.map.object2ReferenceEntrySet(), this.sync); return this.entries; }
/* 190:190 */    public ObjectSet<K> keySet() { if (this.keys == null) this.keys = ObjectSets.synchronize(this.map.keySet(), this.sync); return this.keys; }
/* 191:191 */    public ReferenceCollection<V> values() { if (this.values == null) return ReferenceCollections.synchronize(this.map.values(), this.sync); return this.values; }
/* 192:192 */    public void clear() { synchronized (this.sync) { this.map.clear(); } }
/* 193:193 */    public String toString() { synchronized (this.sync) { return this.map.toString(); } }
/* 194:194 */    public V remove(Object k) { synchronized (this.sync) { return this.map.remove(k); } }
/* 195:195 */    public V get(Object k) { synchronized (this.sync) { return this.map.get(k); } }
/* 196:196 */    public boolean isEmpty() { synchronized (this.sync) { return this.map.isEmpty(); } }
/* 197:197 */    public ObjectSet<Map.Entry<K, V>> entrySet() { synchronized (this.sync) { return this.map.entrySet(); } }
/* 198:198 */    public int hashCode() { synchronized (this.sync) { return this.map.hashCode(); } }
/* 199:199 */    public boolean equals(Object o) { synchronized (this.sync) { return this.map.equals(o);
/* 200:    */      }
/* 201:    */    }
/* 202:    */  }
/* 203:    */  
/* 205:    */  public static <K, V> Object2ReferenceMap<K, V> synchronize(Object2ReferenceMap<K, V> m)
/* 206:    */  {
/* 207:207 */    return new SynchronizedMap(m);
/* 208:    */  }
/* 209:    */  
/* 215:215 */  public static <K, V> Object2ReferenceMap<K, V> synchronize(Object2ReferenceMap<K, V> m, Object sync) { return new SynchronizedMap(m, sync); }
/* 216:    */  
/* 217:    */  public static class UnmodifiableMap<K, V> extends Object2ReferenceFunctions.UnmodifiableFunction<K, V> implements Object2ReferenceMap<K, V>, Serializable {
/* 218:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 219:    */    protected final Object2ReferenceMap<K, V> map;
/* 220:    */    protected volatile transient ObjectSet<Object2ReferenceMap.Entry<K, V>> entries;
/* 221:    */    protected volatile transient ObjectSet<K> keys;
/* 222:    */    protected volatile transient ReferenceCollection<V> values;
/* 223:    */    
/* 224:224 */    protected UnmodifiableMap(Object2ReferenceMap<K, V> m) { super();
/* 225:225 */      this.map = m; }
/* 226:    */    
/* 227:227 */    public int size() { return this.map.size(); }
/* 228:228 */    public boolean containsKey(Object k) { return this.map.containsKey(k); }
/* 229:229 */    public boolean containsValue(Object v) { return this.map.containsValue(v); }
/* 230:230 */    public V defaultReturnValue() { throw new UnsupportedOperationException(); }
/* 231:231 */    public void defaultReturnValue(V defRetValue) { throw new UnsupportedOperationException(); }
/* 232:232 */    public V put(K k, V v) { throw new UnsupportedOperationException(); }
/* 233:    */    
/* 234:234 */    public void putAll(Map<? extends K, ? extends V> m) { throw new UnsupportedOperationException(); }
/* 235:235 */    public ObjectSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSets.unmodifiable(this.map.object2ReferenceEntrySet()); return this.entries; }
/* 236:236 */    public ObjectSet<K> keySet() { if (this.keys == null) this.keys = ObjectSets.unmodifiable(this.map.keySet()); return this.keys; }
/* 237:237 */    public ReferenceCollection<V> values() { if (this.values == null) return ReferenceCollections.unmodifiable(this.map.values()); return this.values; }
/* 238:238 */    public void clear() { throw new UnsupportedOperationException(); }
/* 239:239 */    public String toString() { return this.map.toString(); }
/* 240:240 */    public V remove(Object k) { throw new UnsupportedOperationException(); }
/* 241:241 */    public V get(Object k) { return this.map.get(k); }
/* 242:242 */    public boolean isEmpty() { return this.map.isEmpty(); }
/* 243:243 */    public ObjectSet<Map.Entry<K, V>> entrySet() { return ObjectSets.unmodifiable(this.map.entrySet()); }
/* 244:    */  }
/* 245:    */  
/* 249:    */  public static <K, V> Object2ReferenceMap<K, V> unmodifiable(Object2ReferenceMap<K, V> m)
/* 250:    */  {
/* 251:251 */    return new UnmodifiableMap(m);
/* 252:    */  }
/* 253:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ReferenceMaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */