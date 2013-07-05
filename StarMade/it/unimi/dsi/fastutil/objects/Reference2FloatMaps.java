/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.floats.FloatCollection;
/*     */ import it.unimi.dsi.fastutil.floats.FloatCollections;
/*     */ import it.unimi.dsi.fastutil.floats.FloatSets;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Reference2FloatMaps
/*     */ {
/*  98 */   public static final EmptyMap EMPTY_MAP = new EmptyMap();
/*     */ 
/*     */   public static <K> Reference2FloatMap<K> singleton(K key, float value)
/*     */   {
/* 185 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static <K> Reference2FloatMap<K> singleton(K key, Float value)
/*     */   {
/* 200 */     return new Singleton(key, value.floatValue());
/*     */   }
/*     */ 
/*     */   public static <K> Reference2FloatMap<K> synchronize(Reference2FloatMap<K> m)
/*     */   {
/* 263 */     return new SynchronizedMap(m);
/*     */   }
/*     */ 
/*     */   public static <K> Reference2FloatMap<K> synchronize(Reference2FloatMap<K> m, Object sync)
/*     */   {
/* 271 */     return new SynchronizedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static <K> Reference2FloatMap<K> unmodifiable(Reference2FloatMap<K> m)
/*     */   {
/* 308 */     return new UnmodifiableMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableMap<K> extends Reference2FloatFunctions.UnmodifiableFunction<K>
/*     */     implements Reference2FloatMap<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Reference2FloatMap<K> map;
/*     */     protected volatile transient ObjectSet<Reference2FloatMap.Entry<K>> entries;
/*     */     protected volatile transient ReferenceSet<K> keys;
/*     */     protected volatile transient FloatCollection values;
/*     */ 
/*     */     protected UnmodifiableMap(Reference2FloatMap<K> m)
/*     */     {
/* 280 */       super();
/* 281 */       this.map = m;
/*     */     }
/* 283 */     public int size() { return this.map.size(); } 
/* 284 */     public boolean containsKey(Object k) { return this.map.containsKey(k); } 
/* 285 */     public boolean containsValue(float v) { return this.map.containsValue(v); } 
/* 286 */     public float defaultReturnValue() { throw new UnsupportedOperationException(); } 
/* 287 */     public void defaultReturnValue(float defRetValue) { throw new UnsupportedOperationException(); } 
/* 288 */     public float put(K k, float v) { throw new UnsupportedOperationException(); } 
/*     */     public void putAll(Map<? extends K, ? extends Float> m) {
/* 290 */       throw new UnsupportedOperationException(); } 
/* 291 */     public ObjectSet<Reference2FloatMap.Entry<K>> reference2FloatEntrySet() { if (this.entries == null) this.entries = ObjectSets.unmodifiable(this.map.reference2FloatEntrySet()); return this.entries; } 
/* 292 */     public ReferenceSet<K> keySet() { if (this.keys == null) this.keys = ReferenceSets.unmodifiable(this.map.keySet()); return this.keys; } 
/* 293 */     public FloatCollection values() { if (this.values == null) return FloatCollections.unmodifiable(this.map.values()); return this.values; } 
/* 294 */     public void clear() { throw new UnsupportedOperationException(); } 
/* 295 */     public String toString() { return this.map.toString(); } 
/* 296 */     public boolean containsValue(Object ov) { return this.map.containsValue(ov); } 
/* 297 */     public float removeFloat(Object k) { throw new UnsupportedOperationException(); } 
/* 298 */     public float getFloat(Object k) { return this.map.getFloat(k); } 
/* 299 */     public boolean isEmpty() { return this.map.isEmpty(); } 
/* 300 */     public ObjectSet<Map.Entry<K, Float>> entrySet() { return ObjectSets.unmodifiable(this.map.entrySet()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedMap<K> extends Reference2FloatFunctions.SynchronizedFunction<K>
/*     */     implements Reference2FloatMap<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Reference2FloatMap<K> map;
/*     */     protected volatile transient ObjectSet<Reference2FloatMap.Entry<K>> entries;
/*     */     protected volatile transient ReferenceSet<K> keys;
/*     */     protected volatile transient FloatCollection values;
/*     */ 
/*     */     protected SynchronizedMap(Reference2FloatMap<K> m, Object sync)
/*     */     {
/* 219 */       super(sync);
/* 220 */       this.map = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedMap(Reference2FloatMap<K> m) {
/* 224 */       super();
/* 225 */       this.map = m;
/*     */     }
/*     */     public int size() {
/* 228 */       synchronized (this.sync) { return this.map.size(); }  } 
/* 229 */     public boolean containsKey(Object k) { synchronized (this.sync) { return this.map.containsKey(k); }  } 
/* 230 */     public boolean containsValue(float v) { synchronized (this.sync) { return this.map.containsValue(v); }  } 
/*     */     public float defaultReturnValue() {
/* 232 */       synchronized (this.sync) { return this.map.defaultReturnValue(); }  } 
/* 233 */     public void defaultReturnValue(float defRetValue) { synchronized (this.sync) { this.map.defaultReturnValue(defRetValue); }  } 
/*     */     public float put(K k, float v) {
/* 235 */       synchronized (this.sync) { return this.map.put(k, v); } 
/*     */     }
/*     */ 
/* 238 */     public void putAll(Map<? extends K, ? extends Float> m) { synchronized (this.sync) { this.map.putAll(m); }  } 
/*     */     public ObjectSet<Reference2FloatMap.Entry<K>> reference2FloatEntrySet() {
/* 240 */       if (this.entries == null) this.entries = ObjectSets.synchronize(this.map.reference2FloatEntrySet(), this.sync); return this.entries; } 
/* 241 */     public ReferenceSet<K> keySet() { if (this.keys == null) this.keys = ReferenceSets.synchronize(this.map.keySet(), this.sync); return this.keys; } 
/* 242 */     public FloatCollection values() { if (this.values == null) return FloatCollections.synchronize(this.map.values(), this.sync); return this.values; } 
/*     */     public void clear() {
/* 244 */       synchronized (this.sync) { this.map.clear(); }  } 
/* 245 */     public String toString() { synchronized (this.sync) { return this.map.toString(); } }
/*     */ 
/*     */     public Float put(K k, Float v) {
/* 248 */       synchronized (this.sync) { return (Float)this.map.put(k, v); }  } 
/* 249 */     public boolean containsValue(Object ov) { synchronized (this.sync) { return this.map.containsValue(ov); }  } 
/* 250 */     public float removeFloat(Object k) { synchronized (this.sync) { return this.map.removeFloat(k); }  } 
/* 251 */     public float getFloat(Object k) { synchronized (this.sync) { return this.map.getFloat(k); }  } 
/* 252 */     public boolean isEmpty() { synchronized (this.sync) { return this.map.isEmpty(); }  } 
/* 253 */     public ObjectSet<Map.Entry<K, Float>> entrySet() { synchronized (this.sync) { return this.map.entrySet(); }  } 
/* 254 */     public int hashCode() { synchronized (this.sync) { return this.map.hashCode(); }  } 
/* 255 */     public boolean equals(Object o) { synchronized (this.sync) { return this.map.equals(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton<K> extends Reference2FloatFunctions.Singleton<K>
/*     */     implements Reference2FloatMap<K>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected volatile transient ObjectSet<Reference2FloatMap.Entry<K>> entries;
/*     */     protected volatile transient ReferenceSet<K> keys;
/*     */     protected volatile transient FloatCollection values;
/*     */ 
/*     */     protected Singleton(K key, float value)
/*     */     {
/* 116 */       super(value);
/*     */     }
/*     */     public boolean containsValue(float v) {
/* 119 */       return this.value == v;
/*     */     }
/* 121 */     public boolean containsValue(Object ov) { return ((Float)ov).floatValue() == this.value; }
/*     */ 
/*     */     public void putAll(Map<? extends K, ? extends Float> m) {
/* 124 */       throw new UnsupportedOperationException();
/*     */     }
/* 126 */     public ObjectSet<Reference2FloatMap.Entry<K>> reference2FloatEntrySet() { if (this.entries == null) this.entries = ObjectSets.singleton(new SingletonEntry()); return this.entries; } 
/* 127 */     public ReferenceSet<K> keySet() { if (this.keys == null) this.keys = ReferenceSets.singleton(this.key); return this.keys; } 
/* 128 */     public FloatCollection values() { if (this.values == null) this.values = FloatSets.singleton(this.value); return this.values;
/*     */     }
/*     */ 
/*     */     public boolean isEmpty()
/*     */     {
/* 156 */       return false;
/*     */     }
/*     */     public ObjectSet<Map.Entry<K, Float>> entrySet() {
/* 159 */       return reference2FloatEntrySet();
/*     */     }
/* 161 */     public int hashCode() { return (this.key == null ? 0 : System.identityHashCode(this.key)) ^ HashCommon.float2int(this.value); }
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
/*     */       implements Reference2FloatMap.Entry<K>, Map.Entry<K, Float>
/*     */     {
/*     */       protected SingletonEntry()
/*     */       {
/*     */       }
/*     */ 
/*     */       public K getKey()
/*     */       {
/* 131 */         return Reference2FloatMaps.Singleton.this.key; } 
/* 132 */       public Float getValue() { return Float.valueOf(Reference2FloatMaps.Singleton.this.value); }
/*     */ 
/*     */ 
/*     */       public float getFloatValue()
/*     */       {
/* 139 */         return Reference2FloatMaps.Singleton.this.value; } 
/* 140 */       public float setValue(float value) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */       public Float setValue(Float value) {
/* 143 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       public boolean equals(Object o) {
/* 146 */         if (!(o instanceof Map.Entry)) return false;
/* 147 */         Map.Entry e = (Map.Entry)o;
/*     */ 
/* 149 */         return (Reference2FloatMaps.Singleton.this.key == e.getKey()) && (Reference2FloatMaps.Singleton.this.value == ((Float)e.getValue()).floatValue());
/*     */       }
/*     */       public int hashCode() {
/* 152 */         return (Reference2FloatMaps.Singleton.this.key == null ? 0 : System.identityHashCode(Reference2FloatMaps.Singleton.this.key)) ^ HashCommon.float2int(Reference2FloatMaps.Singleton.this.value); } 
/* 153 */       public String toString() { return Reference2FloatMaps.Singleton.this.key + "->" + Reference2FloatMaps.Singleton.this.value; }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptyMap<K> extends Reference2FloatFunctions.EmptyFunction<K>
/*     */     implements Reference2FloatMap<K>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean containsValue(float v)
/*     */     {
/*  66 */       return false; } 
/*  67 */     public void putAll(Map<? extends K, ? extends Float> m) { throw new UnsupportedOperationException(); } 
/*     */     public ObjectSet<Reference2FloatMap.Entry<K>> reference2FloatEntrySet() {
/*  69 */       return ObjectSets.EMPTY_SET;
/*     */     }
/*  71 */     public ReferenceSet<K> keySet() { return ReferenceSets.EMPTY_SET; } 
/*     */     public FloatCollection values() {
/*  73 */       return FloatSets.EMPTY_SET; } 
/*  74 */     public boolean containsValue(Object ov) { return false; } 
/*  75 */     private Object readResolve() { return Reference2FloatMaps.EMPTY_MAP; } 
/*  76 */     public Object clone() { return Reference2FloatMaps.EMPTY_MAP; } 
/*  77 */     public boolean isEmpty() { return true; }
/*     */ 
/*     */     public ObjectSet<Map.Entry<K, Float>> entrySet() {
/*  80 */       return reference2FloatEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2FloatMaps
 * JD-Core Version:    0.6.2
 */