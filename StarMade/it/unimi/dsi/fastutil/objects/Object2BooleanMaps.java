/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanCollections;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanSets;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Object2BooleanMaps
/*     */ {
/*  98 */   public static final EmptyMap EMPTY_MAP = new EmptyMap();
/*     */ 
/*     */   public static <K> Object2BooleanMap<K> singleton(K key, boolean value)
/*     */   {
/* 185 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static <K> Object2BooleanMap<K> singleton(K key, Boolean value)
/*     */   {
/* 200 */     return new Singleton(key, value.booleanValue());
/*     */   }
/*     */ 
/*     */   public static <K> Object2BooleanMap<K> synchronize(Object2BooleanMap<K> m)
/*     */   {
/* 263 */     return new SynchronizedMap(m);
/*     */   }
/*     */ 
/*     */   public static <K> Object2BooleanMap<K> synchronize(Object2BooleanMap<K> m, Object sync)
/*     */   {
/* 271 */     return new SynchronizedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static <K> Object2BooleanMap<K> unmodifiable(Object2BooleanMap<K> m)
/*     */   {
/* 308 */     return new UnmodifiableMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableMap<K> extends Object2BooleanFunctions.UnmodifiableFunction<K>
/*     */     implements Object2BooleanMap<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Object2BooleanMap<K> map;
/*     */     protected volatile transient ObjectSet<Object2BooleanMap.Entry<K>> entries;
/*     */     protected volatile transient ObjectSet<K> keys;
/*     */     protected volatile transient BooleanCollection values;
/*     */ 
/*     */     protected UnmodifiableMap(Object2BooleanMap<K> m)
/*     */     {
/* 280 */       super();
/* 281 */       this.map = m;
/*     */     }
/* 283 */     public int size() { return this.map.size(); } 
/* 284 */     public boolean containsKey(Object k) { return this.map.containsKey(k); } 
/* 285 */     public boolean containsValue(boolean v) { return this.map.containsValue(v); } 
/* 286 */     public boolean defaultReturnValue() { throw new UnsupportedOperationException(); } 
/* 287 */     public void defaultReturnValue(boolean defRetValue) { throw new UnsupportedOperationException(); } 
/* 288 */     public boolean put(K k, boolean v) { throw new UnsupportedOperationException(); } 
/*     */     public void putAll(Map<? extends K, ? extends Boolean> m) {
/* 290 */       throw new UnsupportedOperationException(); } 
/* 291 */     public ObjectSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet() { if (this.entries == null) this.entries = ObjectSets.unmodifiable(this.map.object2BooleanEntrySet()); return this.entries; } 
/* 292 */     public ObjectSet<K> keySet() { if (this.keys == null) this.keys = ObjectSets.unmodifiable(this.map.keySet()); return this.keys; } 
/* 293 */     public BooleanCollection values() { if (this.values == null) return BooleanCollections.unmodifiable(this.map.values()); return this.values; } 
/* 294 */     public void clear() { throw new UnsupportedOperationException(); } 
/* 295 */     public String toString() { return this.map.toString(); } 
/* 296 */     public boolean containsValue(Object ov) { return this.map.containsValue(ov); } 
/* 297 */     public boolean removeBoolean(Object k) { throw new UnsupportedOperationException(); } 
/* 298 */     public boolean getBoolean(Object k) { return this.map.getBoolean(k); } 
/* 299 */     public boolean isEmpty() { return this.map.isEmpty(); } 
/* 300 */     public ObjectSet<Map.Entry<K, Boolean>> entrySet() { return ObjectSets.unmodifiable(this.map.entrySet()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedMap<K> extends Object2BooleanFunctions.SynchronizedFunction<K>
/*     */     implements Object2BooleanMap<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Object2BooleanMap<K> map;
/*     */     protected volatile transient ObjectSet<Object2BooleanMap.Entry<K>> entries;
/*     */     protected volatile transient ObjectSet<K> keys;
/*     */     protected volatile transient BooleanCollection values;
/*     */ 
/*     */     protected SynchronizedMap(Object2BooleanMap<K> m, Object sync)
/*     */     {
/* 219 */       super(sync);
/* 220 */       this.map = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedMap(Object2BooleanMap<K> m) {
/* 224 */       super();
/* 225 */       this.map = m;
/*     */     }
/*     */     public int size() {
/* 228 */       synchronized (this.sync) { return this.map.size(); }  } 
/* 229 */     public boolean containsKey(Object k) { synchronized (this.sync) { return this.map.containsKey(k); }  } 
/* 230 */     public boolean containsValue(boolean v) { synchronized (this.sync) { return this.map.containsValue(v); }  } 
/*     */     public boolean defaultReturnValue() {
/* 232 */       synchronized (this.sync) { return this.map.defaultReturnValue(); }  } 
/* 233 */     public void defaultReturnValue(boolean defRetValue) { synchronized (this.sync) { this.map.defaultReturnValue(defRetValue); }  } 
/*     */     public boolean put(K k, boolean v) {
/* 235 */       synchronized (this.sync) { return this.map.put(k, v); } 
/*     */     }
/*     */ 
/* 238 */     public void putAll(Map<? extends K, ? extends Boolean> m) { synchronized (this.sync) { this.map.putAll(m); }  } 
/*     */     public ObjectSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet() {
/* 240 */       if (this.entries == null) this.entries = ObjectSets.synchronize(this.map.object2BooleanEntrySet(), this.sync); return this.entries; } 
/* 241 */     public ObjectSet<K> keySet() { if (this.keys == null) this.keys = ObjectSets.synchronize(this.map.keySet(), this.sync); return this.keys; } 
/* 242 */     public BooleanCollection values() { if (this.values == null) return BooleanCollections.synchronize(this.map.values(), this.sync); return this.values; } 
/*     */     public void clear() {
/* 244 */       synchronized (this.sync) { this.map.clear(); }  } 
/* 245 */     public String toString() { synchronized (this.sync) { return this.map.toString(); } }
/*     */ 
/*     */     public Boolean put(K k, Boolean v) {
/* 248 */       synchronized (this.sync) { return (Boolean)this.map.put(k, v); }  } 
/* 249 */     public boolean containsValue(Object ov) { synchronized (this.sync) { return this.map.containsValue(ov); }  } 
/* 250 */     public boolean removeBoolean(Object k) { synchronized (this.sync) { return this.map.removeBoolean(k); }  } 
/* 251 */     public boolean getBoolean(Object k) { synchronized (this.sync) { return this.map.getBoolean(k); }  } 
/* 252 */     public boolean isEmpty() { synchronized (this.sync) { return this.map.isEmpty(); }  } 
/* 253 */     public ObjectSet<Map.Entry<K, Boolean>> entrySet() { synchronized (this.sync) { return this.map.entrySet(); }  } 
/* 254 */     public int hashCode() { synchronized (this.sync) { return this.map.hashCode(); }  } 
/* 255 */     public boolean equals(Object o) { synchronized (this.sync) { return this.map.equals(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton<K> extends Object2BooleanFunctions.Singleton<K>
/*     */     implements Object2BooleanMap<K>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected volatile transient ObjectSet<Object2BooleanMap.Entry<K>> entries;
/*     */     protected volatile transient ObjectSet<K> keys;
/*     */     protected volatile transient BooleanCollection values;
/*     */ 
/*     */     protected Singleton(K key, boolean value)
/*     */     {
/* 116 */       super(value);
/*     */     }
/*     */     public boolean containsValue(boolean v) {
/* 119 */       return this.value == v;
/*     */     }
/* 121 */     public boolean containsValue(Object ov) { return ((Boolean)ov).booleanValue() == this.value; }
/*     */ 
/*     */     public void putAll(Map<? extends K, ? extends Boolean> m) {
/* 124 */       throw new UnsupportedOperationException();
/*     */     }
/* 126 */     public ObjectSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet() { if (this.entries == null) this.entries = ObjectSets.singleton(new SingletonEntry()); return this.entries; } 
/* 127 */     public ObjectSet<K> keySet() { if (this.keys == null) this.keys = ObjectSets.singleton(this.key); return this.keys; } 
/* 128 */     public BooleanCollection values() { if (this.values == null) this.values = BooleanSets.singleton(this.value); return this.values;
/*     */     }
/*     */ 
/*     */     public boolean isEmpty()
/*     */     {
/* 156 */       return false;
/*     */     }
/*     */     public ObjectSet<Map.Entry<K, Boolean>> entrySet() {
/* 159 */       return object2BooleanEntrySet();
/*     */     }
/* 161 */     public int hashCode() { return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value ? 1231 : 1237); }
/*     */ 
/*     */     public boolean equals(Object o) {
/* 164 */       if (o == this) return true;
/* 165 */       if (!(o instanceof Map)) return false;
/*     */ 
/* 167 */       Map m = (Map)o;
/* 168 */       if (m.size() != 1) return false;
/* 169 */       return ((Map.Entry)entrySet().iterator().next()).equals(m.entrySet().iterator().next());
/*     */     }
/*     */     public String toString() {
/* 172 */       return "{" + this.key + "=>" + this.value + "}";
/*     */     }
/*     */ 
/*     */     protected class SingletonEntry
/*     */       implements Object2BooleanMap.Entry<K>, Map.Entry<K, Boolean>
/*     */     {
/*     */       protected SingletonEntry()
/*     */       {
/*     */       }
/*     */ 
/*     */       public K getKey()
/*     */       {
/* 131 */         return Object2BooleanMaps.Singleton.this.key; } 
/* 132 */       public Boolean getValue() { return Boolean.valueOf(Object2BooleanMaps.Singleton.this.value); }
/*     */ 
/*     */ 
/*     */       public boolean getBooleanValue()
/*     */       {
/* 139 */         return Object2BooleanMaps.Singleton.this.value; } 
/* 140 */       public boolean setValue(boolean value) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */       public Boolean setValue(Boolean value) {
/* 143 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       public boolean equals(Object o) {
/* 146 */         if (!(o instanceof Map.Entry)) return false;
/* 147 */         Map.Entry e = (Map.Entry)o;
/*     */ 
/* 149 */         return (Object2BooleanMaps.Singleton.this.key == null ? e.getKey() == null : Object2BooleanMaps.Singleton.this.key.equals(e.getKey())) && (Object2BooleanMaps.Singleton.this.value == ((Boolean)e.getValue()).booleanValue());
/*     */       }
/*     */       public int hashCode() {
/* 152 */         return (Object2BooleanMaps.Singleton.this.key == null ? 0 : Object2BooleanMaps.Singleton.this.key.hashCode()) ^ (Object2BooleanMaps.Singleton.this.value ? 1231 : 1237); } 
/* 153 */       public String toString() { return Object2BooleanMaps.Singleton.this.key + "->" + Object2BooleanMaps.Singleton.this.value; }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptyMap<K> extends Object2BooleanFunctions.EmptyFunction<K>
/*     */     implements Object2BooleanMap<K>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean containsValue(boolean v)
/*     */     {
/*  66 */       return false; } 
/*  67 */     public void putAll(Map<? extends K, ? extends Boolean> m) { throw new UnsupportedOperationException(); } 
/*     */     public ObjectSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet() {
/*  69 */       return ObjectSets.EMPTY_SET;
/*     */     }
/*  71 */     public ObjectSet<K> keySet() { return ObjectSets.EMPTY_SET; } 
/*     */     public BooleanCollection values() {
/*  73 */       return BooleanSets.EMPTY_SET; } 
/*  74 */     public boolean containsValue(Object ov) { return false; } 
/*  75 */     private Object readResolve() { return Object2BooleanMaps.EMPTY_MAP; } 
/*  76 */     public Object clone() { return Object2BooleanMaps.EMPTY_MAP; } 
/*  77 */     public boolean isEmpty() { return true; }
/*     */ 
/*     */     public ObjectSet<Map.Entry<K, Boolean>> entrySet() {
/*  80 */       return object2BooleanEntrySet();
/*     */     }
/*  82 */     public int hashCode() { return 0; }
/*     */ 
/*     */     public boolean equals(Object o) {
/*  85 */       if (!(o instanceof Map)) return false;
/*     */ 
/*  87 */       return ((Map)o).isEmpty();
/*     */     }
/*     */     public String toString() {
/*  90 */       return "{}";
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2BooleanMaps
 * JD-Core Version:    0.6.2
 */