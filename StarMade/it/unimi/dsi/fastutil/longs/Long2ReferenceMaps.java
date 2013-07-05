/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSets;
/*     */ import it.unimi.dsi.fastutil.objects.ReferenceCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ReferenceCollections;
/*     */ import it.unimi.dsi.fastutil.objects.ReferenceSets;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Long2ReferenceMaps
/*     */ {
/*  98 */   public static final EmptyMap EMPTY_MAP = new EmptyMap();
/*     */ 
/*     */   public static <V> Long2ReferenceMap<V> singleton(long key, V value)
/*     */   {
/* 185 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static <V> Long2ReferenceMap<V> singleton(Long key, V value)
/*     */   {
/* 200 */     return new Singleton(key.longValue(), value);
/*     */   }
/*     */ 
/*     */   public static <V> Long2ReferenceMap<V> synchronize(Long2ReferenceMap<V> m)
/*     */   {
/* 266 */     return new SynchronizedMap(m);
/*     */   }
/*     */ 
/*     */   public static <V> Long2ReferenceMap<V> synchronize(Long2ReferenceMap<V> m, Object sync)
/*     */   {
/* 274 */     return new SynchronizedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static <V> Long2ReferenceMap<V> unmodifiable(Long2ReferenceMap<V> m)
/*     */   {
/* 313 */     return new UnmodifiableMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableMap<V> extends Long2ReferenceFunctions.UnmodifiableFunction<V>
/*     */     implements Long2ReferenceMap<V>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Long2ReferenceMap<V> map;
/*     */     protected volatile transient ObjectSet<Long2ReferenceMap.Entry<V>> entries;
/*     */     protected volatile transient LongSet keys;
/*     */     protected volatile transient ReferenceCollection<V> values;
/*     */ 
/*     */     protected UnmodifiableMap(Long2ReferenceMap<V> m)
/*     */     {
/* 283 */       super();
/* 284 */       this.map = m;
/*     */     }
/* 286 */     public int size() { return this.map.size(); } 
/* 287 */     public boolean containsKey(long k) { return this.map.containsKey(k); } 
/* 288 */     public boolean containsValue(Object v) { return this.map.containsValue(v); } 
/* 289 */     public V defaultReturnValue() { throw new UnsupportedOperationException(); } 
/* 290 */     public void defaultReturnValue(V defRetValue) { throw new UnsupportedOperationException(); } 
/* 291 */     public V put(long k, V v) { throw new UnsupportedOperationException(); } 
/*     */     public void putAll(Map<? extends Long, ? extends V> m) {
/* 293 */       throw new UnsupportedOperationException(); } 
/* 294 */     public ObjectSet<Long2ReferenceMap.Entry<V>> long2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSets.unmodifiable(this.map.long2ReferenceEntrySet()); return this.entries; } 
/* 295 */     public LongSet keySet() { if (this.keys == null) this.keys = LongSets.unmodifiable(this.map.keySet()); return this.keys; } 
/* 296 */     public ReferenceCollection<V> values() { if (this.values == null) return ReferenceCollections.unmodifiable(this.map.values()); return this.values; } 
/* 297 */     public void clear() { throw new UnsupportedOperationException(); } 
/* 298 */     public String toString() { return this.map.toString(); } 
/* 299 */     public V remove(long k) { throw new UnsupportedOperationException(); } 
/* 300 */     public V get(long k) { return this.map.get(k); } 
/* 301 */     public boolean containsKey(Object ok) { return this.map.containsKey(ok); } 
/* 302 */     public V remove(Object k) { throw new UnsupportedOperationException(); } 
/* 303 */     public V get(Object k) { return this.map.get(k); } 
/* 304 */     public boolean isEmpty() { return this.map.isEmpty(); } 
/* 305 */     public ObjectSet<Map.Entry<Long, V>> entrySet() { return ObjectSets.unmodifiable(this.map.entrySet()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedMap<V> extends Long2ReferenceFunctions.SynchronizedFunction<V>
/*     */     implements Long2ReferenceMap<V>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Long2ReferenceMap<V> map;
/*     */     protected volatile transient ObjectSet<Long2ReferenceMap.Entry<V>> entries;
/*     */     protected volatile transient LongSet keys;
/*     */     protected volatile transient ReferenceCollection<V> values;
/*     */ 
/*     */     protected SynchronizedMap(Long2ReferenceMap<V> m, Object sync)
/*     */     {
/* 219 */       super(sync);
/* 220 */       this.map = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedMap(Long2ReferenceMap<V> m) {
/* 224 */       super();
/* 225 */       this.map = m;
/*     */     }
/*     */     public int size() {
/* 228 */       synchronized (this.sync) { return this.map.size(); }  } 
/* 229 */     public boolean containsKey(long k) { synchronized (this.sync) { return this.map.containsKey(k); }  } 
/* 230 */     public boolean containsValue(Object v) { synchronized (this.sync) { return this.map.containsValue(v); }  } 
/*     */     public V defaultReturnValue() {
/* 232 */       synchronized (this.sync) { return this.map.defaultReturnValue(); }  } 
/* 233 */     public void defaultReturnValue(V defRetValue) { synchronized (this.sync) { this.map.defaultReturnValue(defRetValue); }  } 
/*     */     public V put(long k, V v) {
/* 235 */       synchronized (this.sync) { return this.map.put(k, v); } 
/*     */     }
/*     */ 
/* 238 */     public void putAll(Map<? extends Long, ? extends V> m) { synchronized (this.sync) { this.map.putAll(m); }  } 
/*     */     public ObjectSet<Long2ReferenceMap.Entry<V>> long2ReferenceEntrySet() {
/* 240 */       if (this.entries == null) this.entries = ObjectSets.synchronize(this.map.long2ReferenceEntrySet(), this.sync); return this.entries; } 
/* 241 */     public LongSet keySet() { if (this.keys == null) this.keys = LongSets.synchronize(this.map.keySet(), this.sync); return this.keys; } 
/* 242 */     public ReferenceCollection<V> values() { if (this.values == null) return ReferenceCollections.synchronize(this.map.values(), this.sync); return this.values; } 
/*     */     public void clear() {
/* 244 */       synchronized (this.sync) { this.map.clear(); }  } 
/* 245 */     public String toString() { synchronized (this.sync) { return this.map.toString(); } }
/*     */ 
/*     */     public V put(Long k, V v) {
/* 248 */       synchronized (this.sync) { return this.map.put(k, v); }
/*     */     }
/*     */ 
/*     */     public V remove(long k) {
/* 252 */       synchronized (this.sync) { return this.map.remove(k); }  } 
/* 253 */     public V get(long k) { synchronized (this.sync) { return this.map.get(k); }  } 
/* 254 */     public boolean containsKey(Object ok) { synchronized (this.sync) { return this.map.containsKey(ok); }  } 
/* 255 */     public boolean isEmpty() { synchronized (this.sync) { return this.map.isEmpty(); }  } 
/* 256 */     public ObjectSet<Map.Entry<Long, V>> entrySet() { synchronized (this.sync) { return this.map.entrySet(); }  } 
/* 257 */     public int hashCode() { synchronized (this.sync) { return this.map.hashCode(); }  } 
/* 258 */     public boolean equals(Object o) { synchronized (this.sync) { return this.map.equals(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton<V> extends Long2ReferenceFunctions.Singleton<V>
/*     */     implements Long2ReferenceMap<V>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected volatile transient ObjectSet<Long2ReferenceMap.Entry<V>> entries;
/*     */     protected volatile transient LongSet keys;
/*     */     protected volatile transient ReferenceCollection<V> values;
/*     */ 
/*     */     protected Singleton(long key, V value)
/*     */     {
/* 116 */       super(value);
/*     */     }
/*     */     public boolean containsValue(Object v) {
/* 119 */       return this.value == v;
/*     */     }
/*     */ 
/*     */     public void putAll(Map<? extends Long, ? extends V> m)
/*     */     {
/* 124 */       throw new UnsupportedOperationException();
/*     */     }
/* 126 */     public ObjectSet<Long2ReferenceMap.Entry<V>> long2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSets.singleton(new SingletonEntry()); return this.entries; } 
/* 127 */     public LongSet keySet() { if (this.keys == null) this.keys = LongSets.singleton(this.key); return this.keys; } 
/* 128 */     public ReferenceCollection<V> values() { if (this.values == null) this.values = ReferenceSets.singleton(this.value); return this.values;
/*     */     }
/*     */ 
/*     */     public boolean isEmpty()
/*     */     {
/* 156 */       return false;
/*     */     }
/*     */     public ObjectSet<Map.Entry<Long, V>> entrySet() {
/* 159 */       return long2ReferenceEntrySet();
/*     */     }
/* 161 */     public int hashCode() { return HashCommon.long2int(this.key) ^ (this.value == null ? 0 : System.identityHashCode(this.value)); }
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
/*     */       implements Long2ReferenceMap.Entry<V>, Map.Entry<Long, V>
/*     */     {
/*     */       protected SingletonEntry()
/*     */       {
/*     */       }
/*     */ 
/*     */       public Long getKey()
/*     */       {
/* 131 */         return Long.valueOf(Long2ReferenceMaps.Singleton.this.key); } 
/* 132 */       public V getValue() { return Long2ReferenceMaps.Singleton.this.value; }
/*     */ 
/*     */       public long getLongKey() {
/* 135 */         return Long2ReferenceMaps.Singleton.this.key;
/*     */       }
/*     */ 
/*     */       public V setValue(V value)
/*     */       {
/* 143 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       public boolean equals(Object o) {
/* 146 */         if (!(o instanceof Map.Entry)) return false;
/* 147 */         Map.Entry e = (Map.Entry)o;
/*     */ 
/* 149 */         return (Long2ReferenceMaps.Singleton.this.key == ((Long)e.getKey()).longValue()) && (Long2ReferenceMaps.Singleton.this.value == e.getValue());
/*     */       }
/*     */       public int hashCode() {
/* 152 */         return HashCommon.long2int(Long2ReferenceMaps.Singleton.this.key) ^ (Long2ReferenceMaps.Singleton.this.value == null ? 0 : System.identityHashCode(Long2ReferenceMaps.Singleton.this.value)); } 
/* 153 */       public String toString() { return Long2ReferenceMaps.Singleton.this.key + "->" + Long2ReferenceMaps.Singleton.this.value; }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptyMap<V> extends Long2ReferenceFunctions.EmptyFunction<V>
/*     */     implements Long2ReferenceMap<V>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean containsValue(Object v)
/*     */     {
/*  66 */       return false; } 
/*  67 */     public void putAll(Map<? extends Long, ? extends V> m) { throw new UnsupportedOperationException(); } 
/*     */     public ObjectSet<Long2ReferenceMap.Entry<V>> long2ReferenceEntrySet() {
/*  69 */       return ObjectSets.EMPTY_SET;
/*     */     }
/*  71 */     public LongSet keySet() { return LongSets.EMPTY_SET; } 
/*     */     public ReferenceCollection<V> values() {
/*  73 */       return ReferenceSets.EMPTY_SET; } 
/*  74 */     private Object readResolve() { return Long2ReferenceMaps.EMPTY_MAP; } 
/*  75 */     public Object clone() { return Long2ReferenceMaps.EMPTY_MAP; } 
/*     */     public boolean isEmpty() {
/*  77 */       return true;
/*     */     }
/*     */     public ObjectSet<Map.Entry<Long, V>> entrySet() {
/*  80 */       return long2ReferenceEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ReferenceMaps
 * JD-Core Version:    0.6.2
 */