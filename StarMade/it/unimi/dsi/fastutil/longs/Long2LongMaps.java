/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSets;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Long2LongMaps
/*     */ {
/*  98 */   public static final EmptyMap EMPTY_MAP = new EmptyMap();
/*     */ 
/*     */   public static Long2LongMap singleton(long key, long value)
/*     */   {
/* 185 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static Long2LongMap singleton(Long key, Long value)
/*     */   {
/* 200 */     return new Singleton(key.longValue(), value.longValue());
/*     */   }
/*     */ 
/*     */   public static Long2LongMap synchronize(Long2LongMap m)
/*     */   {
/* 279 */     return new SynchronizedMap(m);
/*     */   }
/*     */ 
/*     */   public static Long2LongMap synchronize(Long2LongMap m, Object sync)
/*     */   {
/* 289 */     return new SynchronizedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static Long2LongMap unmodifiable(Long2LongMap m)
/*     */   {
/* 358 */     return new UnmodifiableMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableMap extends Long2LongFunctions.UnmodifiableFunction
/*     */     implements Long2LongMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Long2LongMap map;
/*     */     protected volatile transient ObjectSet<Long2LongMap.Entry> entries;
/*     */     protected volatile transient LongSet keys;
/*     */     protected volatile transient LongCollection values;
/*     */ 
/*     */     protected UnmodifiableMap(Long2LongMap m)
/*     */     {
/* 306 */       super();
/* 307 */       this.map = m;
/*     */     }
/*     */     public int size() {
/* 310 */       return this.map.size(); } 
/* 311 */     public boolean containsKey(long k) { return this.map.containsKey(k); } 
/* 312 */     public boolean containsValue(long v) { return this.map.containsValue(v); } 
/*     */     public long defaultReturnValue() {
/* 314 */       throw new UnsupportedOperationException(); } 
/* 315 */     public void defaultReturnValue(long defRetValue) { throw new UnsupportedOperationException(); } 
/*     */     public long put(long k, long v) {
/* 317 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     public void putAll(Map<? extends Long, ? extends Long> m) {
/* 320 */       throw new UnsupportedOperationException();
/*     */     }
/* 322 */     public ObjectSet<Long2LongMap.Entry> long2LongEntrySet() { if (this.entries == null) this.entries = ObjectSets.unmodifiable(this.map.long2LongEntrySet()); return this.entries; } 
/* 323 */     public LongSet keySet() { if (this.keys == null) this.keys = LongSets.unmodifiable(this.map.keySet()); return this.keys; } 
/* 324 */     public LongCollection values() { if (this.values == null) return LongCollections.unmodifiable(this.map.values()); return this.values; } 
/*     */     public void clear() {
/* 326 */       throw new UnsupportedOperationException(); } 
/* 327 */     public String toString() { return this.map.toString(); }
/*     */ 
/*     */     public Long put(Long k, Long v) {
/* 330 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public long remove(long k) {
/* 334 */       throw new UnsupportedOperationException(); } 
/* 335 */     public long get(long k) { return this.map.get(k); } 
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
/* 349 */     public ObjectSet<Map.Entry<Long, Long>> entrySet() { return ObjectSets.unmodifiable(this.map.entrySet()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedMap extends Long2LongFunctions.SynchronizedFunction
/*     */     implements Long2LongMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Long2LongMap map;
/*     */     protected volatile transient ObjectSet<Long2LongMap.Entry> entries;
/*     */     protected volatile transient LongSet keys;
/*     */     protected volatile transient LongCollection values;
/*     */ 
/*     */     protected SynchronizedMap(Long2LongMap m, Object sync)
/*     */     {
/* 219 */       super(sync);
/* 220 */       this.map = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedMap(Long2LongMap m) {
/* 224 */       super();
/* 225 */       this.map = m;
/*     */     }
/*     */     public int size() {
/* 228 */       synchronized (this.sync) { return this.map.size(); }  } 
/* 229 */     public boolean containsKey(long k) { synchronized (this.sync) { return this.map.containsKey(k); }  } 
/* 230 */     public boolean containsValue(long v) { synchronized (this.sync) { return this.map.containsValue(v); }  } 
/*     */     public long defaultReturnValue() {
/* 232 */       synchronized (this.sync) { return this.map.defaultReturnValue(); }  } 
/* 233 */     public void defaultReturnValue(long defRetValue) { synchronized (this.sync) { this.map.defaultReturnValue(defRetValue); }  } 
/*     */     public long put(long k, long v) {
/* 235 */       synchronized (this.sync) { return this.map.put(k, v); } 
/*     */     }
/*     */ 
/* 238 */     public void putAll(Map<? extends Long, ? extends Long> m) { synchronized (this.sync) { this.map.putAll(m); }  } 
/*     */     public ObjectSet<Long2LongMap.Entry> long2LongEntrySet() {
/* 240 */       if (this.entries == null) this.entries = ObjectSets.synchronize(this.map.long2LongEntrySet(), this.sync); return this.entries; } 
/* 241 */     public LongSet keySet() { if (this.keys == null) this.keys = LongSets.synchronize(this.map.keySet(), this.sync); return this.keys; } 
/* 242 */     public LongCollection values() { if (this.values == null) return LongCollections.synchronize(this.map.values(), this.sync); return this.values; } 
/*     */     public void clear() {
/* 244 */       synchronized (this.sync) { this.map.clear(); }  } 
/* 245 */     public String toString() { synchronized (this.sync) { return this.map.toString(); } }
/*     */ 
/*     */     public Long put(Long k, Long v) {
/* 248 */       synchronized (this.sync) { return (Long)this.map.put(k, v); }
/*     */     }
/*     */ 
/*     */     public long remove(long k) {
/* 252 */       synchronized (this.sync) { return this.map.remove(k); }  } 
/* 253 */     public long get(long k) { synchronized (this.sync) { return this.map.get(k); }  } 
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
/* 267 */     public ObjectSet<Map.Entry<Long, Long>> entrySet() { synchronized (this.sync) { return this.map.entrySet(); }  } 
/*     */     public int hashCode() {
/* 269 */       synchronized (this.sync) { return this.map.hashCode(); }  } 
/* 270 */     public boolean equals(Object o) { synchronized (this.sync) { return this.map.equals(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends Long2LongFunctions.Singleton
/*     */     implements Long2LongMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected volatile transient ObjectSet<Long2LongMap.Entry> entries;
/*     */     protected volatile transient LongSet keys;
/*     */     protected volatile transient LongCollection values;
/*     */ 
/*     */     protected Singleton(long key, long value)
/*     */     {
/* 116 */       super(value);
/*     */     }
/*     */     public boolean containsValue(long v) {
/* 119 */       return this.value == v;
/*     */     }
/* 121 */     public boolean containsValue(Object ov) { return ((Long)ov).longValue() == this.value; }
/*     */ 
/*     */     public void putAll(Map<? extends Long, ? extends Long> m) {
/* 124 */       throw new UnsupportedOperationException();
/*     */     }
/* 126 */     public ObjectSet<Long2LongMap.Entry> long2LongEntrySet() { if (this.entries == null) this.entries = ObjectSets.singleton(new SingletonEntry()); return this.entries; } 
/* 127 */     public LongSet keySet() { if (this.keys == null) this.keys = LongSets.singleton(this.key); return this.keys; } 
/* 128 */     public LongCollection values() { if (this.values == null) this.values = LongSets.singleton(this.value); return this.values;
/*     */     }
/*     */ 
/*     */     public boolean isEmpty()
/*     */     {
/* 156 */       return false;
/*     */     }
/*     */     public ObjectSet<Map.Entry<Long, Long>> entrySet() {
/* 159 */       return long2LongEntrySet();
/*     */     }
/* 161 */     public int hashCode() { return HashCommon.long2int(this.key) ^ HashCommon.long2int(this.value); }
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
/*     */       implements Long2LongMap.Entry, Map.Entry<Long, Long>
/*     */     {
/*     */       protected SingletonEntry()
/*     */       {
/*     */       }
/*     */ 
/*     */       public Long getKey()
/*     */       {
/* 131 */         return Long.valueOf(Long2LongMaps.Singleton.this.key); } 
/* 132 */       public Long getValue() { return Long.valueOf(Long2LongMaps.Singleton.this.value); }
/*     */ 
/*     */       public long getLongKey() {
/* 135 */         return Long2LongMaps.Singleton.this.key;
/*     */       }
/*     */ 
/*     */       public long getLongValue() {
/* 139 */         return Long2LongMaps.Singleton.this.value; } 
/* 140 */       public long setValue(long value) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */       public Long setValue(Long value) {
/* 143 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       public boolean equals(Object o) {
/* 146 */         if (!(o instanceof Map.Entry)) return false;
/* 147 */         Map.Entry e = (Map.Entry)o;
/*     */ 
/* 149 */         return (Long2LongMaps.Singleton.this.key == ((Long)e.getKey()).longValue()) && (Long2LongMaps.Singleton.this.value == ((Long)e.getValue()).longValue());
/*     */       }
/*     */       public int hashCode() {
/* 152 */         return HashCommon.long2int(Long2LongMaps.Singleton.this.key) ^ HashCommon.long2int(Long2LongMaps.Singleton.this.value); } 
/* 153 */       public String toString() { return Long2LongMaps.Singleton.this.key + "->" + Long2LongMaps.Singleton.this.value; }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptyMap extends Long2LongFunctions.EmptyFunction
/*     */     implements Long2LongMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean containsValue(long v)
/*     */     {
/*  67 */       return false; } 
/*  68 */     public void putAll(Map<? extends Long, ? extends Long> m) { throw new UnsupportedOperationException(); } 
/*     */     public ObjectSet<Long2LongMap.Entry> long2LongEntrySet() {
/*  70 */       return ObjectSets.EMPTY_SET;
/*     */     }
/*  72 */     public LongSet keySet() { return LongSets.EMPTY_SET; } 
/*     */     public LongCollection values() {
/*  74 */       return LongSets.EMPTY_SET; } 
/*  75 */     public boolean containsValue(Object ov) { return false; } 
/*  76 */     private Object readResolve() { return Long2LongMaps.EMPTY_MAP; } 
/*  77 */     public Object clone() { return Long2LongMaps.EMPTY_MAP; } 
/*  78 */     public boolean isEmpty() { return true; } 
/*     */     public ObjectSet<Map.Entry<Long, Long>> entrySet() {
/*  80 */       return long2LongEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2LongMaps
 * JD-Core Version:    0.6.2
 */