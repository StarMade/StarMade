/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.ints.IntCollection;
/*     */ import it.unimi.dsi.fastutil.ints.IntCollections;
/*     */ import it.unimi.dsi.fastutil.ints.IntSets;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSets;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Float2IntMaps
/*     */ {
/*  98 */   public static final EmptyMap EMPTY_MAP = new EmptyMap();
/*     */ 
/*     */   public static Float2IntMap singleton(float key, int value)
/*     */   {
/* 185 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static Float2IntMap singleton(Float key, Integer value)
/*     */   {
/* 200 */     return new Singleton(key.floatValue(), value.intValue());
/*     */   }
/*     */ 
/*     */   public static Float2IntMap synchronize(Float2IntMap m)
/*     */   {
/* 279 */     return new SynchronizedMap(m);
/*     */   }
/*     */ 
/*     */   public static Float2IntMap synchronize(Float2IntMap m, Object sync)
/*     */   {
/* 289 */     return new SynchronizedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static Float2IntMap unmodifiable(Float2IntMap m)
/*     */   {
/* 358 */     return new UnmodifiableMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableMap extends Float2IntFunctions.UnmodifiableFunction
/*     */     implements Float2IntMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Float2IntMap map;
/*     */     protected volatile transient ObjectSet<Float2IntMap.Entry> entries;
/*     */     protected volatile transient FloatSet keys;
/*     */     protected volatile transient IntCollection values;
/*     */ 
/*     */     protected UnmodifiableMap(Float2IntMap m)
/*     */     {
/* 306 */       super();
/* 307 */       this.map = m;
/*     */     }
/*     */     public int size() {
/* 310 */       return this.map.size(); } 
/* 311 */     public boolean containsKey(float k) { return this.map.containsKey(k); } 
/* 312 */     public boolean containsValue(int v) { return this.map.containsValue(v); } 
/*     */     public int defaultReturnValue() {
/* 314 */       throw new UnsupportedOperationException(); } 
/* 315 */     public void defaultReturnValue(int defRetValue) { throw new UnsupportedOperationException(); } 
/*     */     public int put(float k, int v) {
/* 317 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     public void putAll(Map<? extends Float, ? extends Integer> m) {
/* 320 */       throw new UnsupportedOperationException();
/*     */     }
/* 322 */     public ObjectSet<Float2IntMap.Entry> float2IntEntrySet() { if (this.entries == null) this.entries = ObjectSets.unmodifiable(this.map.float2IntEntrySet()); return this.entries; } 
/* 323 */     public FloatSet keySet() { if (this.keys == null) this.keys = FloatSets.unmodifiable(this.map.keySet()); return this.keys; } 
/* 324 */     public IntCollection values() { if (this.values == null) return IntCollections.unmodifiable(this.map.values()); return this.values; } 
/*     */     public void clear() {
/* 326 */       throw new UnsupportedOperationException(); } 
/* 327 */     public String toString() { return this.map.toString(); }
/*     */ 
/*     */     public Integer put(Float k, Integer v) {
/* 330 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public int remove(float k) {
/* 334 */       throw new UnsupportedOperationException(); } 
/* 335 */     public int get(float k) { return this.map.get(k); } 
/* 336 */     public boolean containsKey(Object ok) { return this.map.containsKey(ok); }
/*     */ 
/*     */     public boolean containsValue(Object ov)
/*     */     {
/* 340 */       return this.map.containsValue(ov);
/*     */     }
/*     */ 
/*     */     public boolean isEmpty()
/*     */     {
/* 348 */       return this.map.isEmpty(); } 
/* 349 */     public ObjectSet<Map.Entry<Float, Integer>> entrySet() { return ObjectSets.unmodifiable(this.map.entrySet()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedMap extends Float2IntFunctions.SynchronizedFunction
/*     */     implements Float2IntMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Float2IntMap map;
/*     */     protected volatile transient ObjectSet<Float2IntMap.Entry> entries;
/*     */     protected volatile transient FloatSet keys;
/*     */     protected volatile transient IntCollection values;
/*     */ 
/*     */     protected SynchronizedMap(Float2IntMap m, Object sync)
/*     */     {
/* 219 */       super(sync);
/* 220 */       this.map = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedMap(Float2IntMap m) {
/* 224 */       super();
/* 225 */       this.map = m;
/*     */     }
/*     */     public int size() {
/* 228 */       synchronized (this.sync) { return this.map.size(); }  } 
/* 229 */     public boolean containsKey(float k) { synchronized (this.sync) { return this.map.containsKey(k); }  } 
/* 230 */     public boolean containsValue(int v) { synchronized (this.sync) { return this.map.containsValue(v); }  } 
/*     */     public int defaultReturnValue() {
/* 232 */       synchronized (this.sync) { return this.map.defaultReturnValue(); }  } 
/* 233 */     public void defaultReturnValue(int defRetValue) { synchronized (this.sync) { this.map.defaultReturnValue(defRetValue); }  } 
/*     */     public int put(float k, int v) {
/* 235 */       synchronized (this.sync) { return this.map.put(k, v); } 
/*     */     }
/*     */ 
/* 238 */     public void putAll(Map<? extends Float, ? extends Integer> m) { synchronized (this.sync) { this.map.putAll(m); }  } 
/*     */     public ObjectSet<Float2IntMap.Entry> float2IntEntrySet() {
/* 240 */       if (this.entries == null) this.entries = ObjectSets.synchronize(this.map.float2IntEntrySet(), this.sync); return this.entries; } 
/* 241 */     public FloatSet keySet() { if (this.keys == null) this.keys = FloatSets.synchronize(this.map.keySet(), this.sync); return this.keys; } 
/* 242 */     public IntCollection values() { if (this.values == null) return IntCollections.synchronize(this.map.values(), this.sync); return this.values; } 
/*     */     public void clear() {
/* 244 */       synchronized (this.sync) { this.map.clear(); }  } 
/* 245 */     public String toString() { synchronized (this.sync) { return this.map.toString(); } }
/*     */ 
/*     */     public Integer put(Float k, Integer v) {
/* 248 */       synchronized (this.sync) { return (Integer)this.map.put(k, v); }
/*     */     }
/*     */ 
/*     */     public int remove(float k) {
/* 252 */       synchronized (this.sync) { return this.map.remove(k); }  } 
/* 253 */     public int get(float k) { synchronized (this.sync) { return this.map.get(k); }  } 
/* 254 */     public boolean containsKey(Object ok) { synchronized (this.sync) { return this.map.containsKey(ok); } }
/*     */ 
/*     */     public boolean containsValue(Object ov)
/*     */     {
/* 258 */       synchronized (this.sync) { return this.map.containsValue(ov); }
/*     */ 
/*     */     }
/*     */ 
/*     */     public boolean isEmpty()
/*     */     {
/* 266 */       synchronized (this.sync) { return this.map.isEmpty(); }  } 
/* 267 */     public ObjectSet<Map.Entry<Float, Integer>> entrySet() { synchronized (this.sync) { return this.map.entrySet(); }  } 
/*     */     public int hashCode() {
/* 269 */       synchronized (this.sync) { return this.map.hashCode(); }  } 
/* 270 */     public boolean equals(Object o) { synchronized (this.sync) { return this.map.equals(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends Float2IntFunctions.Singleton
/*     */     implements Float2IntMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected volatile transient ObjectSet<Float2IntMap.Entry> entries;
/*     */     protected volatile transient FloatSet keys;
/*     */     protected volatile transient IntCollection values;
/*     */ 
/*     */     protected Singleton(float key, int value)
/*     */     {
/* 116 */       super(value);
/*     */     }
/*     */     public boolean containsValue(int v) {
/* 119 */       return this.value == v;
/*     */     }
/* 121 */     public boolean containsValue(Object ov) { return ((Integer)ov).intValue() == this.value; }
/*     */ 
/*     */     public void putAll(Map<? extends Float, ? extends Integer> m) {
/* 124 */       throw new UnsupportedOperationException();
/*     */     }
/* 126 */     public ObjectSet<Float2IntMap.Entry> float2IntEntrySet() { if (this.entries == null) this.entries = ObjectSets.singleton(new SingletonEntry()); return this.entries; } 
/* 127 */     public FloatSet keySet() { if (this.keys == null) this.keys = FloatSets.singleton(this.key); return this.keys; } 
/* 128 */     public IntCollection values() { if (this.values == null) this.values = IntSets.singleton(this.value); return this.values;
/*     */     }
/*     */ 
/*     */     public boolean isEmpty()
/*     */     {
/* 156 */       return false;
/*     */     }
/*     */     public ObjectSet<Map.Entry<Float, Integer>> entrySet() {
/* 159 */       return float2IntEntrySet();
/*     */     }
/* 161 */     public int hashCode() { return HashCommon.float2int(this.key) ^ this.value; }
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
/*     */       implements Float2IntMap.Entry, Map.Entry<Float, Integer>
/*     */     {
/*     */       protected SingletonEntry()
/*     */       {
/*     */       }
/*     */ 
/*     */       public Float getKey()
/*     */       {
/* 131 */         return Float.valueOf(Float2IntMaps.Singleton.this.key); } 
/* 132 */       public Integer getValue() { return Integer.valueOf(Float2IntMaps.Singleton.this.value); }
/*     */ 
/*     */       public float getFloatKey() {
/* 135 */         return Float2IntMaps.Singleton.this.key;
/*     */       }
/*     */ 
/*     */       public int getIntValue() {
/* 139 */         return Float2IntMaps.Singleton.this.value; } 
/* 140 */       public int setValue(int value) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */       public Integer setValue(Integer value) {
/* 143 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       public boolean equals(Object o) {
/* 146 */         if (!(o instanceof Map.Entry)) return false;
/* 147 */         Map.Entry e = (Map.Entry)o;
/*     */ 
/* 149 */         return (Float2IntMaps.Singleton.this.key == ((Float)e.getKey()).floatValue()) && (Float2IntMaps.Singleton.this.value == ((Integer)e.getValue()).intValue());
/*     */       }
/*     */       public int hashCode() {
/* 152 */         return HashCommon.float2int(Float2IntMaps.Singleton.this.key) ^ Float2IntMaps.Singleton.this.value; } 
/* 153 */       public String toString() { return Float2IntMaps.Singleton.this.key + "->" + Float2IntMaps.Singleton.this.value; }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptyMap extends Float2IntFunctions.EmptyFunction
/*     */     implements Float2IntMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean containsValue(int v)
/*     */     {
/*  67 */       return false; } 
/*  68 */     public void putAll(Map<? extends Float, ? extends Integer> m) { throw new UnsupportedOperationException(); } 
/*     */     public ObjectSet<Float2IntMap.Entry> float2IntEntrySet() {
/*  70 */       return ObjectSets.EMPTY_SET;
/*     */     }
/*  72 */     public FloatSet keySet() { return FloatSets.EMPTY_SET; } 
/*     */     public IntCollection values() {
/*  74 */       return IntSets.EMPTY_SET; } 
/*  75 */     public boolean containsValue(Object ov) { return false; } 
/*  76 */     private Object readResolve() { return Float2IntMaps.EMPTY_MAP; } 
/*  77 */     public Object clone() { return Float2IntMaps.EMPTY_MAP; } 
/*  78 */     public boolean isEmpty() { return true; } 
/*     */     public ObjectSet<Map.Entry<Float, Integer>> entrySet() {
/*  80 */       return float2IntEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2IntMaps
 * JD-Core Version:    0.6.2
 */