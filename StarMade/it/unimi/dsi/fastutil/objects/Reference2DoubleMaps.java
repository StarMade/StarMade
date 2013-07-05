/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleCollection;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleCollections;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleSets;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Reference2DoubleMaps
/*     */ {
/*  98 */   public static final EmptyMap EMPTY_MAP = new EmptyMap();
/*     */ 
/*     */   public static <K> Reference2DoubleMap<K> singleton(K key, double value)
/*     */   {
/* 185 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static <K> Reference2DoubleMap<K> singleton(K key, Double value)
/*     */   {
/* 200 */     return new Singleton(key, value.doubleValue());
/*     */   }
/*     */ 
/*     */   public static <K> Reference2DoubleMap<K> synchronize(Reference2DoubleMap<K> m)
/*     */   {
/* 263 */     return new SynchronizedMap(m);
/*     */   }
/*     */ 
/*     */   public static <K> Reference2DoubleMap<K> synchronize(Reference2DoubleMap<K> m, Object sync)
/*     */   {
/* 271 */     return new SynchronizedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static <K> Reference2DoubleMap<K> unmodifiable(Reference2DoubleMap<K> m)
/*     */   {
/* 308 */     return new UnmodifiableMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableMap<K> extends Reference2DoubleFunctions.UnmodifiableFunction<K>
/*     */     implements Reference2DoubleMap<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Reference2DoubleMap<K> map;
/*     */     protected volatile transient ObjectSet<Reference2DoubleMap.Entry<K>> entries;
/*     */     protected volatile transient ReferenceSet<K> keys;
/*     */     protected volatile transient DoubleCollection values;
/*     */ 
/*     */     protected UnmodifiableMap(Reference2DoubleMap<K> m)
/*     */     {
/* 280 */       super();
/* 281 */       this.map = m;
/*     */     }
/* 283 */     public int size() { return this.map.size(); } 
/* 284 */     public boolean containsKey(Object k) { return this.map.containsKey(k); } 
/* 285 */     public boolean containsValue(double v) { return this.map.containsValue(v); } 
/* 286 */     public double defaultReturnValue() { throw new UnsupportedOperationException(); } 
/* 287 */     public void defaultReturnValue(double defRetValue) { throw new UnsupportedOperationException(); } 
/* 288 */     public double put(K k, double v) { throw new UnsupportedOperationException(); } 
/*     */     public void putAll(Map<? extends K, ? extends Double> m) {
/* 290 */       throw new UnsupportedOperationException(); } 
/* 291 */     public ObjectSet<Reference2DoubleMap.Entry<K>> reference2DoubleEntrySet() { if (this.entries == null) this.entries = ObjectSets.unmodifiable(this.map.reference2DoubleEntrySet()); return this.entries; } 
/* 292 */     public ReferenceSet<K> keySet() { if (this.keys == null) this.keys = ReferenceSets.unmodifiable(this.map.keySet()); return this.keys; } 
/* 293 */     public DoubleCollection values() { if (this.values == null) return DoubleCollections.unmodifiable(this.map.values()); return this.values; } 
/* 294 */     public void clear() { throw new UnsupportedOperationException(); } 
/* 295 */     public String toString() { return this.map.toString(); } 
/* 296 */     public boolean containsValue(Object ov) { return this.map.containsValue(ov); } 
/* 297 */     public double removeDouble(Object k) { throw new UnsupportedOperationException(); } 
/* 298 */     public double getDouble(Object k) { return this.map.getDouble(k); } 
/* 299 */     public boolean isEmpty() { return this.map.isEmpty(); } 
/* 300 */     public ObjectSet<Map.Entry<K, Double>> entrySet() { return ObjectSets.unmodifiable(this.map.entrySet()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedMap<K> extends Reference2DoubleFunctions.SynchronizedFunction<K>
/*     */     implements Reference2DoubleMap<K>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Reference2DoubleMap<K> map;
/*     */     protected volatile transient ObjectSet<Reference2DoubleMap.Entry<K>> entries;
/*     */     protected volatile transient ReferenceSet<K> keys;
/*     */     protected volatile transient DoubleCollection values;
/*     */ 
/*     */     protected SynchronizedMap(Reference2DoubleMap<K> m, Object sync)
/*     */     {
/* 219 */       super(sync);
/* 220 */       this.map = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedMap(Reference2DoubleMap<K> m) {
/* 224 */       super();
/* 225 */       this.map = m;
/*     */     }
/*     */     public int size() {
/* 228 */       synchronized (this.sync) { return this.map.size(); }  } 
/* 229 */     public boolean containsKey(Object k) { synchronized (this.sync) { return this.map.containsKey(k); }  } 
/* 230 */     public boolean containsValue(double v) { synchronized (this.sync) { return this.map.containsValue(v); }  } 
/*     */     public double defaultReturnValue() {
/* 232 */       synchronized (this.sync) { return this.map.defaultReturnValue(); }  } 
/* 233 */     public void defaultReturnValue(double defRetValue) { synchronized (this.sync) { this.map.defaultReturnValue(defRetValue); }  } 
/*     */     public double put(K k, double v) {
/* 235 */       synchronized (this.sync) { return this.map.put(k, v); } 
/*     */     }
/*     */ 
/* 238 */     public void putAll(Map<? extends K, ? extends Double> m) { synchronized (this.sync) { this.map.putAll(m); }  } 
/*     */     public ObjectSet<Reference2DoubleMap.Entry<K>> reference2DoubleEntrySet() {
/* 240 */       if (this.entries == null) this.entries = ObjectSets.synchronize(this.map.reference2DoubleEntrySet(), this.sync); return this.entries; } 
/* 241 */     public ReferenceSet<K> keySet() { if (this.keys == null) this.keys = ReferenceSets.synchronize(this.map.keySet(), this.sync); return this.keys; } 
/* 242 */     public DoubleCollection values() { if (this.values == null) return DoubleCollections.synchronize(this.map.values(), this.sync); return this.values; } 
/*     */     public void clear() {
/* 244 */       synchronized (this.sync) { this.map.clear(); }  } 
/* 245 */     public String toString() { synchronized (this.sync) { return this.map.toString(); } }
/*     */ 
/*     */     public Double put(K k, Double v) {
/* 248 */       synchronized (this.sync) { return (Double)this.map.put(k, v); }  } 
/* 249 */     public boolean containsValue(Object ov) { synchronized (this.sync) { return this.map.containsValue(ov); }  } 
/* 250 */     public double removeDouble(Object k) { synchronized (this.sync) { return this.map.removeDouble(k); }  } 
/* 251 */     public double getDouble(Object k) { synchronized (this.sync) { return this.map.getDouble(k); }  } 
/* 252 */     public boolean isEmpty() { synchronized (this.sync) { return this.map.isEmpty(); }  } 
/* 253 */     public ObjectSet<Map.Entry<K, Double>> entrySet() { synchronized (this.sync) { return this.map.entrySet(); }  } 
/* 254 */     public int hashCode() { synchronized (this.sync) { return this.map.hashCode(); }  } 
/* 255 */     public boolean equals(Object o) { synchronized (this.sync) { return this.map.equals(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton<K> extends Reference2DoubleFunctions.Singleton<K>
/*     */     implements Reference2DoubleMap<K>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected volatile transient ObjectSet<Reference2DoubleMap.Entry<K>> entries;
/*     */     protected volatile transient ReferenceSet<K> keys;
/*     */     protected volatile transient DoubleCollection values;
/*     */ 
/*     */     protected Singleton(K key, double value)
/*     */     {
/* 116 */       super(value);
/*     */     }
/*     */     public boolean containsValue(double v) {
/* 119 */       return this.value == v;
/*     */     }
/* 121 */     public boolean containsValue(Object ov) { return ((Double)ov).doubleValue() == this.value; }
/*     */ 
/*     */     public void putAll(Map<? extends K, ? extends Double> m) {
/* 124 */       throw new UnsupportedOperationException();
/*     */     }
/* 126 */     public ObjectSet<Reference2DoubleMap.Entry<K>> reference2DoubleEntrySet() { if (this.entries == null) this.entries = ObjectSets.singleton(new SingletonEntry()); return this.entries; } 
/* 127 */     public ReferenceSet<K> keySet() { if (this.keys == null) this.keys = ReferenceSets.singleton(this.key); return this.keys; } 
/* 128 */     public DoubleCollection values() { if (this.values == null) this.values = DoubleSets.singleton(this.value); return this.values;
/*     */     }
/*     */ 
/*     */     public boolean isEmpty()
/*     */     {
/* 156 */       return false;
/*     */     }
/*     */     public ObjectSet<Map.Entry<K, Double>> entrySet() {
/* 159 */       return reference2DoubleEntrySet();
/*     */     }
/* 161 */     public int hashCode() { return (this.key == null ? 0 : System.identityHashCode(this.key)) ^ HashCommon.double2int(this.value); }
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
/*     */       implements Reference2DoubleMap.Entry<K>, Map.Entry<K, Double>
/*     */     {
/*     */       protected SingletonEntry()
/*     */       {
/*     */       }
/*     */ 
/*     */       public K getKey()
/*     */       {
/* 131 */         return Reference2DoubleMaps.Singleton.this.key; } 
/* 132 */       public Double getValue() { return Double.valueOf(Reference2DoubleMaps.Singleton.this.value); }
/*     */ 
/*     */ 
/*     */       public double getDoubleValue()
/*     */       {
/* 139 */         return Reference2DoubleMaps.Singleton.this.value; } 
/* 140 */       public double setValue(double value) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */       public Double setValue(Double value) {
/* 143 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       public boolean equals(Object o) {
/* 146 */         if (!(o instanceof Map.Entry)) return false;
/* 147 */         Map.Entry e = (Map.Entry)o;
/*     */ 
/* 149 */         return (Reference2DoubleMaps.Singleton.this.key == e.getKey()) && (Reference2DoubleMaps.Singleton.this.value == ((Double)e.getValue()).doubleValue());
/*     */       }
/*     */       public int hashCode() {
/* 152 */         return (Reference2DoubleMaps.Singleton.this.key == null ? 0 : System.identityHashCode(Reference2DoubleMaps.Singleton.this.key)) ^ HashCommon.double2int(Reference2DoubleMaps.Singleton.this.value); } 
/* 153 */       public String toString() { return Reference2DoubleMaps.Singleton.this.key + "->" + Reference2DoubleMaps.Singleton.this.value; }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptyMap<K> extends Reference2DoubleFunctions.EmptyFunction<K>
/*     */     implements Reference2DoubleMap<K>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean containsValue(double v)
/*     */     {
/*  66 */       return false; } 
/*  67 */     public void putAll(Map<? extends K, ? extends Double> m) { throw new UnsupportedOperationException(); } 
/*     */     public ObjectSet<Reference2DoubleMap.Entry<K>> reference2DoubleEntrySet() {
/*  69 */       return ObjectSets.EMPTY_SET;
/*     */     }
/*  71 */     public ReferenceSet<K> keySet() { return ReferenceSets.EMPTY_SET; } 
/*     */     public DoubleCollection values() {
/*  73 */       return DoubleSets.EMPTY_SET; } 
/*  74 */     public boolean containsValue(Object ov) { return false; } 
/*  75 */     private Object readResolve() { return Reference2DoubleMaps.EMPTY_MAP; } 
/*  76 */     public Object clone() { return Reference2DoubleMaps.EMPTY_MAP; } 
/*  77 */     public boolean isEmpty() { return true; }
/*     */ 
/*     */     public ObjectSet<Map.Entry<K, Double>> entrySet() {
/*  80 */       return reference2DoubleEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2DoubleMaps
 * JD-Core Version:    0.6.2
 */