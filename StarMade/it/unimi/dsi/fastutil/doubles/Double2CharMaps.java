/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.chars.CharCollection;
/*     */ import it.unimi.dsi.fastutil.chars.CharCollections;
/*     */ import it.unimi.dsi.fastutil.chars.CharSets;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSets;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Double2CharMaps
/*     */ {
/*  98 */   public static final EmptyMap EMPTY_MAP = new EmptyMap();
/*     */ 
/*     */   public static Double2CharMap singleton(double key, char value)
/*     */   {
/* 185 */     return new Singleton(key, value);
/*     */   }
/*     */ 
/*     */   public static Double2CharMap singleton(Double key, Character value)
/*     */   {
/* 200 */     return new Singleton(key.doubleValue(), value.charValue());
/*     */   }
/*     */ 
/*     */   public static Double2CharMap synchronize(Double2CharMap m)
/*     */   {
/* 279 */     return new SynchronizedMap(m);
/*     */   }
/*     */ 
/*     */   public static Double2CharMap synchronize(Double2CharMap m, Object sync)
/*     */   {
/* 289 */     return new SynchronizedMap(m, sync);
/*     */   }
/*     */ 
/*     */   public static Double2CharMap unmodifiable(Double2CharMap m)
/*     */   {
/* 358 */     return new UnmodifiableMap(m);
/*     */   }
/*     */ 
/*     */   public static class UnmodifiableMap extends Double2CharFunctions.UnmodifiableFunction
/*     */     implements Double2CharMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Double2CharMap map;
/*     */     protected volatile transient ObjectSet<Double2CharMap.Entry> entries;
/*     */     protected volatile transient DoubleSet keys;
/*     */     protected volatile transient CharCollection values;
/*     */ 
/*     */     protected UnmodifiableMap(Double2CharMap m)
/*     */     {
/* 306 */       super();
/* 307 */       this.map = m;
/*     */     }
/*     */     public int size() {
/* 310 */       return this.map.size(); } 
/* 311 */     public boolean containsKey(double k) { return this.map.containsKey(k); } 
/* 312 */     public boolean containsValue(char v) { return this.map.containsValue(v); } 
/*     */     public char defaultReturnValue() {
/* 314 */       throw new UnsupportedOperationException(); } 
/* 315 */     public void defaultReturnValue(char defRetValue) { throw new UnsupportedOperationException(); } 
/*     */     public char put(double k, char v) {
/* 317 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     public void putAll(Map<? extends Double, ? extends Character> m) {
/* 320 */       throw new UnsupportedOperationException();
/*     */     }
/* 322 */     public ObjectSet<Double2CharMap.Entry> double2CharEntrySet() { if (this.entries == null) this.entries = ObjectSets.unmodifiable(this.map.double2CharEntrySet()); return this.entries; } 
/* 323 */     public DoubleSet keySet() { if (this.keys == null) this.keys = DoubleSets.unmodifiable(this.map.keySet()); return this.keys; } 
/* 324 */     public CharCollection values() { if (this.values == null) return CharCollections.unmodifiable(this.map.values()); return this.values; } 
/*     */     public void clear() {
/* 326 */       throw new UnsupportedOperationException(); } 
/* 327 */     public String toString() { return this.map.toString(); }
/*     */ 
/*     */     public Character put(Double k, Character v) {
/* 330 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public char remove(double k) {
/* 334 */       throw new UnsupportedOperationException(); } 
/* 335 */     public char get(double k) { return this.map.get(k); } 
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
/* 349 */     public ObjectSet<Map.Entry<Double, Character>> entrySet() { return ObjectSets.unmodifiable(this.map.entrySet()); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static class SynchronizedMap extends Double2CharFunctions.SynchronizedFunction
/*     */     implements Double2CharMap, Serializable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected final Double2CharMap map;
/*     */     protected volatile transient ObjectSet<Double2CharMap.Entry> entries;
/*     */     protected volatile transient DoubleSet keys;
/*     */     protected volatile transient CharCollection values;
/*     */ 
/*     */     protected SynchronizedMap(Double2CharMap m, Object sync)
/*     */     {
/* 219 */       super(sync);
/* 220 */       this.map = m;
/*     */     }
/*     */ 
/*     */     protected SynchronizedMap(Double2CharMap m) {
/* 224 */       super();
/* 225 */       this.map = m;
/*     */     }
/*     */     public int size() {
/* 228 */       synchronized (this.sync) { return this.map.size(); }  } 
/* 229 */     public boolean containsKey(double k) { synchronized (this.sync) { return this.map.containsKey(k); }  } 
/* 230 */     public boolean containsValue(char v) { synchronized (this.sync) { return this.map.containsValue(v); }  } 
/*     */     public char defaultReturnValue() {
/* 232 */       synchronized (this.sync) { return this.map.defaultReturnValue(); }  } 
/* 233 */     public void defaultReturnValue(char defRetValue) { synchronized (this.sync) { this.map.defaultReturnValue(defRetValue); }  } 
/*     */     public char put(double k, char v) {
/* 235 */       synchronized (this.sync) { return this.map.put(k, v); } 
/*     */     }
/*     */ 
/* 238 */     public void putAll(Map<? extends Double, ? extends Character> m) { synchronized (this.sync) { this.map.putAll(m); }  } 
/*     */     public ObjectSet<Double2CharMap.Entry> double2CharEntrySet() {
/* 240 */       if (this.entries == null) this.entries = ObjectSets.synchronize(this.map.double2CharEntrySet(), this.sync); return this.entries; } 
/* 241 */     public DoubleSet keySet() { if (this.keys == null) this.keys = DoubleSets.synchronize(this.map.keySet(), this.sync); return this.keys; } 
/* 242 */     public CharCollection values() { if (this.values == null) return CharCollections.synchronize(this.map.values(), this.sync); return this.values; } 
/*     */     public void clear() {
/* 244 */       synchronized (this.sync) { this.map.clear(); }  } 
/* 245 */     public String toString() { synchronized (this.sync) { return this.map.toString(); } }
/*     */ 
/*     */     public Character put(Double k, Character v) {
/* 248 */       synchronized (this.sync) { return (Character)this.map.put(k, v); }
/*     */     }
/*     */ 
/*     */     public char remove(double k) {
/* 252 */       synchronized (this.sync) { return this.map.remove(k); }  } 
/* 253 */     public char get(double k) { synchronized (this.sync) { return this.map.get(k); }  } 
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
/* 267 */     public ObjectSet<Map.Entry<Double, Character>> entrySet() { synchronized (this.sync) { return this.map.entrySet(); }  } 
/*     */     public int hashCode() {
/* 269 */       synchronized (this.sync) { return this.map.hashCode(); }  } 
/* 270 */     public boolean equals(Object o) { synchronized (this.sync) { return this.map.equals(o); }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Singleton extends Double2CharFunctions.Singleton
/*     */     implements Double2CharMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */     protected volatile transient ObjectSet<Double2CharMap.Entry> entries;
/*     */     protected volatile transient DoubleSet keys;
/*     */     protected volatile transient CharCollection values;
/*     */ 
/*     */     protected Singleton(double key, char value)
/*     */     {
/* 116 */       super(value);
/*     */     }
/*     */     public boolean containsValue(char v) {
/* 119 */       return this.value == v;
/*     */     }
/* 121 */     public boolean containsValue(Object ov) { return ((Character)ov).charValue() == this.value; }
/*     */ 
/*     */     public void putAll(Map<? extends Double, ? extends Character> m) {
/* 124 */       throw new UnsupportedOperationException();
/*     */     }
/* 126 */     public ObjectSet<Double2CharMap.Entry> double2CharEntrySet() { if (this.entries == null) this.entries = ObjectSets.singleton(new SingletonEntry()); return this.entries; } 
/* 127 */     public DoubleSet keySet() { if (this.keys == null) this.keys = DoubleSets.singleton(this.key); return this.keys; } 
/* 128 */     public CharCollection values() { if (this.values == null) this.values = CharSets.singleton(this.value); return this.values;
/*     */     }
/*     */ 
/*     */     public boolean isEmpty()
/*     */     {
/* 156 */       return false;
/*     */     }
/*     */     public ObjectSet<Map.Entry<Double, Character>> entrySet() {
/* 159 */       return double2CharEntrySet();
/*     */     }
/* 161 */     public int hashCode() { return HashCommon.double2int(this.key) ^ this.value; }
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
/*     */       implements Double2CharMap.Entry, Map.Entry<Double, Character>
/*     */     {
/*     */       protected SingletonEntry()
/*     */       {
/*     */       }
/*     */ 
/*     */       public Double getKey()
/*     */       {
/* 131 */         return Double.valueOf(Double2CharMaps.Singleton.this.key); } 
/* 132 */       public Character getValue() { return Character.valueOf(Double2CharMaps.Singleton.this.value); }
/*     */ 
/*     */       public double getDoubleKey() {
/* 135 */         return Double2CharMaps.Singleton.this.key;
/*     */       }
/*     */ 
/*     */       public char getCharValue() {
/* 139 */         return Double2CharMaps.Singleton.this.value; } 
/* 140 */       public char setValue(char value) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */       public Character setValue(Character value) {
/* 143 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       public boolean equals(Object o) {
/* 146 */         if (!(o instanceof Map.Entry)) return false;
/* 147 */         Map.Entry e = (Map.Entry)o;
/*     */ 
/* 149 */         return (Double2CharMaps.Singleton.this.key == ((Double)e.getKey()).doubleValue()) && (Double2CharMaps.Singleton.this.value == ((Character)e.getValue()).charValue());
/*     */       }
/*     */       public int hashCode() {
/* 152 */         return HashCommon.double2int(Double2CharMaps.Singleton.this.key) ^ Double2CharMaps.Singleton.this.value; } 
/* 153 */       public String toString() { return Double2CharMaps.Singleton.this.key + "->" + Double2CharMaps.Singleton.this.value; }
/*     */ 
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EmptyMap extends Double2CharFunctions.EmptyFunction
/*     */     implements Double2CharMap, Serializable, Cloneable
/*     */   {
/*     */     public static final long serialVersionUID = -7046029254386353129L;
/*     */ 
/*     */     public boolean containsValue(char v)
/*     */     {
/*  67 */       return false; } 
/*  68 */     public void putAll(Map<? extends Double, ? extends Character> m) { throw new UnsupportedOperationException(); } 
/*     */     public ObjectSet<Double2CharMap.Entry> double2CharEntrySet() {
/*  70 */       return ObjectSets.EMPTY_SET;
/*     */     }
/*  72 */     public DoubleSet keySet() { return DoubleSets.EMPTY_SET; } 
/*     */     public CharCollection values() {
/*  74 */       return CharSets.EMPTY_SET; } 
/*  75 */     public boolean containsValue(Object ov) { return false; } 
/*  76 */     private Object readResolve() { return Double2CharMaps.EMPTY_MAP; } 
/*  77 */     public Object clone() { return Double2CharMaps.EMPTY_MAP; } 
/*  78 */     public boolean isEmpty() { return true; } 
/*     */     public ObjectSet<Map.Entry<Double, Character>> entrySet() {
/*  80 */       return double2CharEntrySet();
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
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2CharMaps
 * JD-Core Version:    0.6.2
 */