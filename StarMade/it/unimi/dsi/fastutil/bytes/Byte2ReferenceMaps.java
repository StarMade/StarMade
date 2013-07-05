/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
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
/*     */ public class Byte2ReferenceMaps
/*     */ {
/*  98 */   public static final EmptyMap EMPTY_MAP = new EmptyMap();
/*     */ 
/*     */   public static <V> Byte2ReferenceMap<V> singleton(byte key, V value)
/*     */   {
/* 185 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static <V> Byte2ReferenceMap<V> singleton(Byte key, V value)
/*     */   {
/* 200 */     return new Singleton(key.byteValue(), value);
/*     */   }
/*     */ 
/*     */   public static <V> Byte2ReferenceMap<V> synchronize(Byte2ReferenceMap<V> m)
/*     */   {
/* 266 */     return new SynchronizedMap(m);
/*     */   }
/*     */ 
/*     */   public static <V> Byte2ReferenceMap<V> synchronize(Byte2ReferenceMap<V> m, Object sync)
/*     */   {
/* 274 */     return new SynchronizedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static <V> Byte2ReferenceMap<V> unmodifiable(Byte2ReferenceMap<V> m)
/*     */   {
/* 313 */     return new UnmodifiableMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableMap<V> extends Byte2ReferenceFunctions.UnmodifiableFunction<V>
/*     */     implements Byte2ReferenceMap<V>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Byte2ReferenceMap<V> map;
/*     */     protected volatile transient ObjectSet<Byte2ReferenceMap.Entry<V>> entries;
/*     */     protected volatile transient ByteSet keys;
/*     */     protected volatile transient ReferenceCollection<V> values;
/*     */ 
/*     */     protected UnmodifiableMap(Byte2ReferenceMap<V> m)
/*     */     {
/* 283 */       super();
/* 284 */       this.map = m;
/*     */     }
/* 286 */     public int size() { return this.map.size(); } 
/* 287 */     public boolean containsKey(byte k) { return this.map.containsKey(k); } 
/* 288 */     public boolean containsValue(Object v) { return this.map.containsValue(v); } 
/* 289 */     public V defaultReturnValue() { throw new UnsupportedOperationException(); } 
/* 290 */     public void defaultReturnValue(V defRetValue) { throw new UnsupportedOperationException(); } 
/* 291 */     public V put(byte k, V v) { throw new UnsupportedOperationException(); } 
/*     */     public void putAll(Map<? extends Byte, ? extends V> m) {
/* 293 */       throw new UnsupportedOperationException(); } 
/* 294 */     public ObjectSet<Byte2ReferenceMap.Entry<V>> byte2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSets.unmodifiable(this.map.byte2ReferenceEntrySet()); return this.entries; } 
/* 295 */     public ByteSet keySet() { if (this.keys == null) this.keys = ByteSets.unmodifiable(this.map.keySet()); return this.keys; } 
/* 296 */     public ReferenceCollection<V> values() { if (this.values == null) return ReferenceCollections.unmodifiable(this.map.values()); return this.values; } 
/* 297 */     public void clear() { throw new UnsupportedOperationException(); } 
/* 298 */     public String toString() { return this.map.toString(); } 
/* 299 */     public V remove(byte k) { throw new UnsupportedOperationException(); } 
/* 300 */     public V get(byte k) { return this.map.get(k); } 
/* 301 */     public boolean containsKey(Object ok) { return this.map.containsKey(ok); } 
/* 302 */     public V remove(Object k) { throw new UnsupportedOperationException(); } 
/* 303 */     public V get(Object k) { return this.map.get(k); } 
/* 304 */     public boolean isEmpty() { return this.map.isEmpty(); } 
/* 305 */     public ObjectSet<Map.Entry<Byte, V>> entrySet() { return ObjectSets.unmodifiable(this.map.entrySet()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedMap<V> extends Byte2ReferenceFunctions.SynchronizedFunction<V>
/*     */     implements Byte2ReferenceMap<V>, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Byte2ReferenceMap<V> map;
/*     */     protected volatile transient ObjectSet<Byte2ReferenceMap.Entry<V>> entries;
/*     */     protected volatile transient ByteSet keys;
/*     */     protected volatile transient ReferenceCollection<V> values;
/*     */ 
/*     */     protected SynchronizedMap(Byte2ReferenceMap<V> m, Object sync)
/*     */     {
/* 219 */       super(sync);
/* 220 */       this.map = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedMap(Byte2ReferenceMap<V> m) {
/* 224 */       super();
/* 225 */       this.map = m;
/*     */     }
/*     */     public int size() {
/* 228 */       synchronized (this.sync) { return this.map.size(); }  } 
/* 229 */     public boolean containsKey(byte k) { synchronized (this.sync) { return this.map.containsKey(k); }  } 
/* 230 */     public boolean containsValue(Object v) { synchronized (this.sync) { return this.map.containsValue(v); }  } 
/*     */     public V defaultReturnValue() {
/* 232 */       synchronized (this.sync) { return this.map.defaultReturnValue(); }  } 
/* 233 */     public void defaultReturnValue(V defRetValue) { synchronized (this.sync) { this.map.defaultReturnValue(defRetValue); }  } 
/*     */     public V put(byte k, V v) {
/* 235 */       synchronized (this.sync) { return this.map.put(k, v); } 
/*     */     }
/*     */ 
/* 238 */     public void putAll(Map<? extends Byte, ? extends V> m) { synchronized (this.sync) { this.map.putAll(m); }  } 
/*     */     public ObjectSet<Byte2ReferenceMap.Entry<V>> byte2ReferenceEntrySet() {
/* 240 */       if (this.entries == null) this.entries = ObjectSets.synchronize(this.map.byte2ReferenceEntrySet(), this.sync); return this.entries; } 
/* 241 */     public ByteSet keySet() { if (this.keys == null) this.keys = ByteSets.synchronize(this.map.keySet(), this.sync); return this.keys; } 
/* 242 */     public ReferenceCollection<V> values() { if (this.values == null) return ReferenceCollections.synchronize(this.map.values(), this.sync); return this.values; } 
/*     */     public void clear() {
/* 244 */       synchronized (this.sync) { this.map.clear(); }  } 
/* 245 */     public String toString() { synchronized (this.sync) { return this.map.toString(); } }
/*     */ 
/*     */     public V put(Byte k, V v) {
/* 248 */       synchronized (this.sync) { return this.map.put(k, v); }
/*     */     }
/*     */ 
/*     */     public V remove(byte k) {
/* 252 */       synchronized (this.sync) { return this.map.remove(k); }  } 
/* 253 */     public V get(byte k) { synchronized (this.sync) { return this.map.get(k); }  } 
/* 254 */     public boolean containsKey(Object ok) { synchronized (this.sync) { return this.map.containsKey(ok); }  } 
/* 255 */     public boolean isEmpty() { synchronized (this.sync) { return this.map.isEmpty(); }  } 
/* 256 */     public ObjectSet<Map.Entry<Byte, V>> entrySet() { synchronized (this.sync) { return this.map.entrySet(); }  } 
/* 257 */     public int hashCode() { synchronized (this.sync) { return this.map.hashCode(); }  } 
/* 258 */     public boolean equals(Object o) { synchronized (this.sync) { return this.map.equals(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton<V> extends Byte2ReferenceFunctions.Singleton<V>
/*     */     implements Byte2ReferenceMap<V>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected volatile transient ObjectSet<Byte2ReferenceMap.Entry<V>> entries;
/*     */     protected volatile transient ByteSet keys;
/*     */     protected volatile transient ReferenceCollection<V> values;
/*     */ 
/*     */     protected Singleton(byte key, V value)
/*     */     {
/* 116 */       super(value);
/*     */     }
/*     */     public boolean containsValue(Object v) {
/* 119 */       return this.value == v;
/*     */     }
/*     */ 
/*     */     public void putAll(Map<? extends Byte, ? extends V> m)
/*     */     {
/* 124 */       throw new UnsupportedOperationException();
/*     */     }
/* 126 */     public ObjectSet<Byte2ReferenceMap.Entry<V>> byte2ReferenceEntrySet() { if (this.entries == null) this.entries = ObjectSets.singleton(new SingletonEntry()); return this.entries; } 
/* 127 */     public ByteSet keySet() { if (this.keys == null) this.keys = ByteSets.singleton(this.key); return this.keys; } 
/* 128 */     public ReferenceCollection<V> values() { if (this.values == null) this.values = ReferenceSets.singleton(this.value); return this.values;
/*     */     }
/*     */ 
/*     */     public boolean isEmpty()
/*     */     {
/* 156 */       return false;
/*     */     }
/*     */     public ObjectSet<Map.Entry<Byte, V>> entrySet() {
/* 159 */       return byte2ReferenceEntrySet();
/*     */     }
/* 161 */     public int hashCode() { return this.key ^ (this.value == null ? 0 : System.identityHashCode(this.value)); }
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
/*     */       implements Byte2ReferenceMap.Entry<V>, Map.Entry<Byte, V>
/*     */     {
/*     */       protected SingletonEntry()
/*     */       {
/*     */       }
/*     */ 
/*     */       public Byte getKey()
/*     */       {
/* 131 */         return Byte.valueOf(Byte2ReferenceMaps.Singleton.this.key); } 
/* 132 */       public V getValue() { return Byte2ReferenceMaps.Singleton.this.value; }
/*     */ 
/*     */       public byte getByteKey() {
/* 135 */         return Byte2ReferenceMaps.Singleton.this.key;
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
/* 149 */         return (Byte2ReferenceMaps.Singleton.this.key == ((Byte)e.getKey()).byteValue()) && (Byte2ReferenceMaps.Singleton.this.value == e.getValue());
/*     */       }
/*     */       public int hashCode() {
/* 152 */         return Byte2ReferenceMaps.Singleton.this.key ^ (Byte2ReferenceMaps.Singleton.this.value == null ? 0 : System.identityHashCode(Byte2ReferenceMaps.Singleton.this.value)); } 
/* 153 */       public String toString() { return Byte2ReferenceMaps.Singleton.this.key + "->" + Byte2ReferenceMaps.Singleton.this.value; }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptyMap<V> extends Byte2ReferenceFunctions.EmptyFunction<V>
/*     */     implements Byte2ReferenceMap<V>, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean containsValue(Object v)
/*     */     {
/*  66 */       return false; } 
/*  67 */     public void putAll(Map<? extends Byte, ? extends V> m) { throw new UnsupportedOperationException(); } 
/*     */     public ObjectSet<Byte2ReferenceMap.Entry<V>> byte2ReferenceEntrySet() {
/*  69 */       return ObjectSets.EMPTY_SET;
/*     */     }
/*  71 */     public ByteSet keySet() { return ByteSets.EMPTY_SET; } 
/*     */     public ReferenceCollection<V> values() {
/*  73 */       return ReferenceSets.EMPTY_SET; } 
/*  74 */     private Object readResolve() { return Byte2ReferenceMaps.EMPTY_MAP; } 
/*  75 */     public Object clone() { return Byte2ReferenceMaps.EMPTY_MAP; } 
/*     */     public boolean isEmpty() {
/*  77 */       return true;
/*     */     }
/*     */     public ObjectSet<Map.Entry<Byte, V>> entrySet() {
/*  80 */       return byte2ReferenceEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ReferenceMaps
 * JD-Core Version:    0.6.2
 */