/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.floats.FloatCollection;
/*     */ import it.unimi.dsi.fastutil.floats.FloatCollections;
/*     */ import it.unimi.dsi.fastutil.floats.FloatSets;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSets;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Int2FloatMaps
/*     */ {
/*  98 */   public static final EmptyMap EMPTY_MAP = new EmptyMap();
/*     */ 
/*     */   public static Int2FloatMap singleton(int key, float value)
/*     */   {
/* 185 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static Int2FloatMap singleton(Integer key, Float value)
/*     */   {
/* 200 */     return new Singleton(key.intValue(), value.floatValue());
/*     */   }
/*     */ 
/*     */   public static Int2FloatMap synchronize(Int2FloatMap m)
/*     */   {
/* 279 */     return new SynchronizedMap(m);
/*     */   }
/*     */ 
/*     */   public static Int2FloatMap synchronize(Int2FloatMap m, Object sync)
/*     */   {
/* 289 */     return new SynchronizedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static Int2FloatMap unmodifiable(Int2FloatMap m)
/*     */   {
/* 358 */     return new UnmodifiableMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableMap extends Int2FloatFunctions.UnmodifiableFunction
/*     */     implements Int2FloatMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Int2FloatMap map;
/*     */     protected volatile transient ObjectSet<Int2FloatMap.Entry> entries;
/*     */     protected volatile transient IntSet keys;
/*     */     protected volatile transient FloatCollection values;
/*     */ 
/*     */     protected UnmodifiableMap(Int2FloatMap m)
/*     */     {
/* 306 */       super();
/* 307 */       this.map = m;
/*     */     }
/*     */     public int size() {
/* 310 */       return this.map.size(); } 
/* 311 */     public boolean containsKey(int k) { return this.map.containsKey(k); } 
/* 312 */     public boolean containsValue(float v) { return this.map.containsValue(v); } 
/*     */     public float defaultReturnValue() {
/* 314 */       throw new UnsupportedOperationException(); } 
/* 315 */     public void defaultReturnValue(float defRetValue) { throw new UnsupportedOperationException(); } 
/*     */     public float put(int k, float v) {
/* 317 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     public void putAll(Map<? extends Integer, ? extends Float> m) {
/* 320 */       throw new UnsupportedOperationException();
/*     */     }
/* 322 */     public ObjectSet<Int2FloatMap.Entry> int2FloatEntrySet() { if (this.entries == null) this.entries = ObjectSets.unmodifiable(this.map.int2FloatEntrySet()); return this.entries; } 
/* 323 */     public IntSet keySet() { if (this.keys == null) this.keys = IntSets.unmodifiable(this.map.keySet()); return this.keys; } 
/* 324 */     public FloatCollection values() { if (this.values == null) return FloatCollections.unmodifiable(this.map.values()); return this.values; } 
/*     */     public void clear() {
/* 326 */       throw new UnsupportedOperationException(); } 
/* 327 */     public String toString() { return this.map.toString(); }
/*     */ 
/*     */     public Float put(Integer k, Float v) {
/* 330 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public float remove(int k) {
/* 334 */       throw new UnsupportedOperationException(); } 
/* 335 */     public float get(int k) { return this.map.get(k); } 
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
/* 349 */     public ObjectSet<Map.Entry<Integer, Float>> entrySet() { return ObjectSets.unmodifiable(this.map.entrySet()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedMap extends Int2FloatFunctions.SynchronizedFunction
/*     */     implements Int2FloatMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Int2FloatMap map;
/*     */     protected volatile transient ObjectSet<Int2FloatMap.Entry> entries;
/*     */     protected volatile transient IntSet keys;
/*     */     protected volatile transient FloatCollection values;
/*     */ 
/*     */     protected SynchronizedMap(Int2FloatMap m, Object sync)
/*     */     {
/* 219 */       super(sync);
/* 220 */       this.map = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedMap(Int2FloatMap m) {
/* 224 */       super();
/* 225 */       this.map = m;
/*     */     }
/*     */     public int size() {
/* 228 */       synchronized (this.sync) { return this.map.size(); }  } 
/* 229 */     public boolean containsKey(int k) { synchronized (this.sync) { return this.map.containsKey(k); }  } 
/* 230 */     public boolean containsValue(float v) { synchronized (this.sync) { return this.map.containsValue(v); }  } 
/*     */     public float defaultReturnValue() {
/* 232 */       synchronized (this.sync) { return this.map.defaultReturnValue(); }  } 
/* 233 */     public void defaultReturnValue(float defRetValue) { synchronized (this.sync) { this.map.defaultReturnValue(defRetValue); }  } 
/*     */     public float put(int k, float v) {
/* 235 */       synchronized (this.sync) { return this.map.put(k, v); } 
/*     */     }
/*     */ 
/* 238 */     public void putAll(Map<? extends Integer, ? extends Float> m) { synchronized (this.sync) { this.map.putAll(m); }  } 
/*     */     public ObjectSet<Int2FloatMap.Entry> int2FloatEntrySet() {
/* 240 */       if (this.entries == null) this.entries = ObjectSets.synchronize(this.map.int2FloatEntrySet(), this.sync); return this.entries; } 
/* 241 */     public IntSet keySet() { if (this.keys == null) this.keys = IntSets.synchronize(this.map.keySet(), this.sync); return this.keys; } 
/* 242 */     public FloatCollection values() { if (this.values == null) return FloatCollections.synchronize(this.map.values(), this.sync); return this.values; } 
/*     */     public void clear() {
/* 244 */       synchronized (this.sync) { this.map.clear(); }  } 
/* 245 */     public String toString() { synchronized (this.sync) { return this.map.toString(); } }
/*     */ 
/*     */     public Float put(Integer k, Float v) {
/* 248 */       synchronized (this.sync) { return (Float)this.map.put(k, v); }
/*     */     }
/*     */ 
/*     */     public float remove(int k) {
/* 252 */       synchronized (this.sync) { return this.map.remove(k); }  } 
/* 253 */     public float get(int k) { synchronized (this.sync) { return this.map.get(k); }  } 
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
/* 267 */     public ObjectSet<Map.Entry<Integer, Float>> entrySet() { synchronized (this.sync) { return this.map.entrySet(); }  } 
/*     */     public int hashCode() {
/* 269 */       synchronized (this.sync) { return this.map.hashCode(); }  } 
/* 270 */     public boolean equals(Object o) { synchronized (this.sync) { return this.map.equals(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends Int2FloatFunctions.Singleton
/*     */     implements Int2FloatMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected volatile transient ObjectSet<Int2FloatMap.Entry> entries;
/*     */     protected volatile transient IntSet keys;
/*     */     protected volatile transient FloatCollection values;
/*     */ 
/*     */     protected Singleton(int key, float value)
/*     */     {
/* 116 */       super(value);
/*     */     }
/*     */     public boolean containsValue(float v) {
/* 119 */       return this.value == v;
/*     */     }
/* 121 */     public boolean containsValue(Object ov) { return ((Float)ov).floatValue() == this.value; }
/*     */ 
/*     */     public void putAll(Map<? extends Integer, ? extends Float> m) {
/* 124 */       throw new UnsupportedOperationException();
/*     */     }
/* 126 */     public ObjectSet<Int2FloatMap.Entry> int2FloatEntrySet() { if (this.entries == null) this.entries = ObjectSets.singleton(new SingletonEntry()); return this.entries; } 
/* 127 */     public IntSet keySet() { if (this.keys == null) this.keys = IntSets.singleton(this.key); return this.keys; } 
/* 128 */     public FloatCollection values() { if (this.values == null) this.values = FloatSets.singleton(this.value); return this.values;
/*     */     }
/*     */ 
/*     */     public boolean isEmpty()
/*     */     {
/* 156 */       return false;
/*     */     }
/*     */     public ObjectSet<Map.Entry<Integer, Float>> entrySet() {
/* 159 */       return int2FloatEntrySet();
/*     */     }
/* 161 */     public int hashCode() { return this.key ^ HashCommon.float2int(this.value); }
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
/*     */       implements Int2FloatMap.Entry, Map.Entry<Integer, Float>
/*     */     {
/*     */       protected SingletonEntry()
/*     */       {
/*     */       }
/*     */ 
/*     */       public Integer getKey()
/*     */       {
/* 131 */         return Integer.valueOf(Int2FloatMaps.Singleton.this.key); } 
/* 132 */       public Float getValue() { return Float.valueOf(Int2FloatMaps.Singleton.this.value); }
/*     */ 
/*     */       public int getIntKey() {
/* 135 */         return Int2FloatMaps.Singleton.this.key;
/*     */       }
/*     */ 
/*     */       public float getFloatValue() {
/* 139 */         return Int2FloatMaps.Singleton.this.value; } 
/* 140 */       public float setValue(float value) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */       public Float setValue(Float value) {
/* 143 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       public boolean equals(Object o) {
/* 146 */         if (!(o instanceof Map.Entry)) return false;
/* 147 */         Map.Entry e = (Map.Entry)o;
/*     */ 
/* 149 */         return (Int2FloatMaps.Singleton.this.key == ((Integer)e.getKey()).intValue()) && (Int2FloatMaps.Singleton.this.value == ((Float)e.getValue()).floatValue());
/*     */       }
/*     */       public int hashCode() {
/* 152 */         return Int2FloatMaps.Singleton.this.key ^ HashCommon.float2int(Int2FloatMaps.Singleton.this.value); } 
/* 153 */       public String toString() { return Int2FloatMaps.Singleton.this.key + "->" + Int2FloatMaps.Singleton.this.value; }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptyMap extends Int2FloatFunctions.EmptyFunction
/*     */     implements Int2FloatMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean containsValue(float v)
/*     */     {
/*  67 */       return false; } 
/*  68 */     public void putAll(Map<? extends Integer, ? extends Float> m) { throw new UnsupportedOperationException(); } 
/*     */     public ObjectSet<Int2FloatMap.Entry> int2FloatEntrySet() {
/*  70 */       return ObjectSets.EMPTY_SET;
/*     */     }
/*  72 */     public IntSet keySet() { return IntSets.EMPTY_SET; } 
/*     */     public FloatCollection values() {
/*  74 */       return FloatSets.EMPTY_SET; } 
/*  75 */     public boolean containsValue(Object ov) { return false; } 
/*  76 */     private Object readResolve() { return Int2FloatMaps.EMPTY_MAP; } 
/*  77 */     public Object clone() { return Int2FloatMaps.EMPTY_MAP; } 
/*  78 */     public boolean isEmpty() { return true; } 
/*     */     public ObjectSet<Map.Entry<Integer, Float>> entrySet() {
/*  80 */       return int2FloatEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2FloatMaps
 * JD-Core Version:    0.6.2
 */