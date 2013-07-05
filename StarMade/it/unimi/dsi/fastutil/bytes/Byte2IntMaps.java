/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
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
/*     */ public class Byte2IntMaps
/*     */ {
/*  98 */   public static final EmptyMap EMPTY_MAP = new EmptyMap();
/*     */ 
/*     */   public static Byte2IntMap singleton(byte key, int value)
/*     */   {
/* 185 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static Byte2IntMap singleton(Byte key, Integer value)
/*     */   {
/* 200 */     return new Singleton(key.byteValue(), value.intValue());
/*     */   }
/*     */ 
/*     */   public static Byte2IntMap synchronize(Byte2IntMap m)
/*     */   {
/* 279 */     return new SynchronizedMap(m);
/*     */   }
/*     */ 
/*     */   public static Byte2IntMap synchronize(Byte2IntMap m, Object sync)
/*     */   {
/* 289 */     return new SynchronizedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static Byte2IntMap unmodifiable(Byte2IntMap m)
/*     */   {
/* 358 */     return new UnmodifiableMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableMap extends Byte2IntFunctions.UnmodifiableFunction
/*     */     implements Byte2IntMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Byte2IntMap map;
/*     */     protected volatile transient ObjectSet<Byte2IntMap.Entry> entries;
/*     */     protected volatile transient ByteSet keys;
/*     */     protected volatile transient IntCollection values;
/*     */ 
/*     */     protected UnmodifiableMap(Byte2IntMap m)
/*     */     {
/* 306 */       super();
/* 307 */       this.map = m;
/*     */     }
/*     */     public int size() {
/* 310 */       return this.map.size(); } 
/* 311 */     public boolean containsKey(byte k) { return this.map.containsKey(k); } 
/* 312 */     public boolean containsValue(int v) { return this.map.containsValue(v); } 
/*     */     public int defaultReturnValue() {
/* 314 */       throw new UnsupportedOperationException(); } 
/* 315 */     public void defaultReturnValue(int defRetValue) { throw new UnsupportedOperationException(); } 
/*     */     public int put(byte k, int v) {
/* 317 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     public void putAll(Map<? extends Byte, ? extends Integer> m) {
/* 320 */       throw new UnsupportedOperationException();
/*     */     }
/* 322 */     public ObjectSet<Byte2IntMap.Entry> byte2IntEntrySet() { if (this.entries == null) this.entries = ObjectSets.unmodifiable(this.map.byte2IntEntrySet()); return this.entries; } 
/* 323 */     public ByteSet keySet() { if (this.keys == null) this.keys = ByteSets.unmodifiable(this.map.keySet()); return this.keys; } 
/* 324 */     public IntCollection values() { if (this.values == null) return IntCollections.unmodifiable(this.map.values()); return this.values; } 
/*     */     public void clear() {
/* 326 */       throw new UnsupportedOperationException(); } 
/* 327 */     public String toString() { return this.map.toString(); }
/*     */ 
/*     */     public Integer put(Byte k, Integer v) {
/* 330 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public int remove(byte k) {
/* 334 */       throw new UnsupportedOperationException(); } 
/* 335 */     public int get(byte k) { return this.map.get(k); } 
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
/* 349 */     public ObjectSet<Map.Entry<Byte, Integer>> entrySet() { return ObjectSets.unmodifiable(this.map.entrySet()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedMap extends Byte2IntFunctions.SynchronizedFunction
/*     */     implements Byte2IntMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Byte2IntMap map;
/*     */     protected volatile transient ObjectSet<Byte2IntMap.Entry> entries;
/*     */     protected volatile transient ByteSet keys;
/*     */     protected volatile transient IntCollection values;
/*     */ 
/*     */     protected SynchronizedMap(Byte2IntMap m, Object sync)
/*     */     {
/* 219 */       super(sync);
/* 220 */       this.map = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedMap(Byte2IntMap m) {
/* 224 */       super();
/* 225 */       this.map = m;
/*     */     }
/*     */     public int size() {
/* 228 */       synchronized (this.sync) { return this.map.size(); }  } 
/* 229 */     public boolean containsKey(byte k) { synchronized (this.sync) { return this.map.containsKey(k); }  } 
/* 230 */     public boolean containsValue(int v) { synchronized (this.sync) { return this.map.containsValue(v); }  } 
/*     */     public int defaultReturnValue() {
/* 232 */       synchronized (this.sync) { return this.map.defaultReturnValue(); }  } 
/* 233 */     public void defaultReturnValue(int defRetValue) { synchronized (this.sync) { this.map.defaultReturnValue(defRetValue); }  } 
/*     */     public int put(byte k, int v) {
/* 235 */       synchronized (this.sync) { return this.map.put(k, v); } 
/*     */     }
/*     */ 
/* 238 */     public void putAll(Map<? extends Byte, ? extends Integer> m) { synchronized (this.sync) { this.map.putAll(m); }  } 
/*     */     public ObjectSet<Byte2IntMap.Entry> byte2IntEntrySet() {
/* 240 */       if (this.entries == null) this.entries = ObjectSets.synchronize(this.map.byte2IntEntrySet(), this.sync); return this.entries; } 
/* 241 */     public ByteSet keySet() { if (this.keys == null) this.keys = ByteSets.synchronize(this.map.keySet(), this.sync); return this.keys; } 
/* 242 */     public IntCollection values() { if (this.values == null) return IntCollections.synchronize(this.map.values(), this.sync); return this.values; } 
/*     */     public void clear() {
/* 244 */       synchronized (this.sync) { this.map.clear(); }  } 
/* 245 */     public String toString() { synchronized (this.sync) { return this.map.toString(); } }
/*     */ 
/*     */     public Integer put(Byte k, Integer v) {
/* 248 */       synchronized (this.sync) { return (Integer)this.map.put(k, v); }
/*     */     }
/*     */ 
/*     */     public int remove(byte k) {
/* 252 */       synchronized (this.sync) { return this.map.remove(k); }  } 
/* 253 */     public int get(byte k) { synchronized (this.sync) { return this.map.get(k); }  } 
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
/* 267 */     public ObjectSet<Map.Entry<Byte, Integer>> entrySet() { synchronized (this.sync) { return this.map.entrySet(); }  } 
/*     */     public int hashCode() {
/* 269 */       synchronized (this.sync) { return this.map.hashCode(); }  } 
/* 270 */     public boolean equals(Object o) { synchronized (this.sync) { return this.map.equals(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends Byte2IntFunctions.Singleton
/*     */     implements Byte2IntMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected volatile transient ObjectSet<Byte2IntMap.Entry> entries;
/*     */     protected volatile transient ByteSet keys;
/*     */     protected volatile transient IntCollection values;
/*     */ 
/*     */     protected Singleton(byte key, int value)
/*     */     {
/* 116 */       super(value);
/*     */     }
/*     */     public boolean containsValue(int v) {
/* 119 */       return this.value == v;
/*     */     }
/* 121 */     public boolean containsValue(Object ov) { return ((Integer)ov).intValue() == this.value; }
/*     */ 
/*     */     public void putAll(Map<? extends Byte, ? extends Integer> m) {
/* 124 */       throw new UnsupportedOperationException();
/*     */     }
/* 126 */     public ObjectSet<Byte2IntMap.Entry> byte2IntEntrySet() { if (this.entries == null) this.entries = ObjectSets.singleton(new SingletonEntry()); return this.entries; } 
/* 127 */     public ByteSet keySet() { if (this.keys == null) this.keys = ByteSets.singleton(this.key); return this.keys; } 
/* 128 */     public IntCollection values() { if (this.values == null) this.values = IntSets.singleton(this.value); return this.values;
/*     */     }
/*     */ 
/*     */     public boolean isEmpty()
/*     */     {
/* 156 */       return false;
/*     */     }
/*     */     public ObjectSet<Map.Entry<Byte, Integer>> entrySet() {
/* 159 */       return byte2IntEntrySet();
/*     */     }
/* 161 */     public int hashCode() { return this.key ^ this.value; }
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
/*     */       implements Byte2IntMap.Entry, Map.Entry<Byte, Integer>
/*     */     {
/*     */       protected SingletonEntry()
/*     */       {
/*     */       }
/*     */ 
/*     */       public Byte getKey()
/*     */       {
/* 131 */         return Byte.valueOf(Byte2IntMaps.Singleton.this.key); } 
/* 132 */       public Integer getValue() { return Integer.valueOf(Byte2IntMaps.Singleton.this.value); }
/*     */ 
/*     */       public byte getByteKey() {
/* 135 */         return Byte2IntMaps.Singleton.this.key;
/*     */       }
/*     */ 
/*     */       public int getIntValue() {
/* 139 */         return Byte2IntMaps.Singleton.this.value; } 
/* 140 */       public int setValue(int value) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */       public Integer setValue(Integer value) {
/* 143 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       public boolean equals(Object o) {
/* 146 */         if (!(o instanceof Map.Entry)) return false;
/* 147 */         Map.Entry e = (Map.Entry)o;
/*     */ 
/* 149 */         return (Byte2IntMaps.Singleton.this.key == ((Byte)e.getKey()).byteValue()) && (Byte2IntMaps.Singleton.this.value == ((Integer)e.getValue()).intValue());
/*     */       }
/*     */       public int hashCode() {
/* 152 */         return Byte2IntMaps.Singleton.this.key ^ Byte2IntMaps.Singleton.this.value; } 
/* 153 */       public String toString() { return Byte2IntMaps.Singleton.this.key + "->" + Byte2IntMaps.Singleton.this.value; }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptyMap extends Byte2IntFunctions.EmptyFunction
/*     */     implements Byte2IntMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean containsValue(int v)
/*     */     {
/*  67 */       return false; } 
/*  68 */     public void putAll(Map<? extends Byte, ? extends Integer> m) { throw new UnsupportedOperationException(); } 
/*     */     public ObjectSet<Byte2IntMap.Entry> byte2IntEntrySet() {
/*  70 */       return ObjectSets.EMPTY_SET;
/*     */     }
/*  72 */     public ByteSet keySet() { return ByteSets.EMPTY_SET; } 
/*     */     public IntCollection values() {
/*  74 */       return IntSets.EMPTY_SET; } 
/*  75 */     public boolean containsValue(Object ov) { return false; } 
/*  76 */     private Object readResolve() { return Byte2IntMaps.EMPTY_MAP; } 
/*  77 */     public Object clone() { return Byte2IntMaps.EMPTY_MAP; } 
/*  78 */     public boolean isEmpty() { return true; } 
/*     */     public ObjectSet<Map.Entry<Byte, Integer>> entrySet() {
/*  80 */       return byte2IntEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2IntMaps
 * JD-Core Version:    0.6.2
 */