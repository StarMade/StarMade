/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractDouble2ObjectMap<V> extends AbstractDouble2ObjectFunction<V>
/*     */   implements Double2ObjectMap<V>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object v)
/*     */   {
/*  69 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(double k) {
/*  73 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Double, ? extends V> m)
/*     */   {
/*  83 */     int n = m.size();
/*  84 */     Iterator i = m.entrySet().iterator();
/*  85 */     if ((m instanceof Double2ObjectMap))
/*     */     {
/*  87 */       while (n-- != 0) {
/*  88 */         Double2ObjectMap.Entry e = (Double2ObjectMap.Entry)i.next();
/*  89 */         put(e.getDoubleKey(), e.getValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  94 */       while (n-- != 0) {
/*  95 */         Map.Entry e = (Map.Entry)i.next();
/*  96 */         put((Double)e.getKey(), e.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 101 */   public boolean isEmpty() { return size() == 0; }
/*     */ 
/*     */ 
/*     */   public DoubleSet keySet()
/*     */   {
/* 174 */     return new AbstractDoubleSet() {
/* 175 */       public boolean contains(double k) { return AbstractDouble2ObjectMap.this.containsKey(k); } 
/* 176 */       public int size() { return AbstractDouble2ObjectMap.this.size(); } 
/* 177 */       public void clear() { AbstractDouble2ObjectMap.this.clear(); } 
/*     */       public DoubleIterator iterator() {
/* 179 */         return new AbstractDoubleIterator() {
/* 180 */           final ObjectIterator<Map.Entry<Double, V>> i = AbstractDouble2ObjectMap.this.entrySet().iterator();
/*     */ 
/* 181 */           public double nextDouble() { return ((Double2ObjectMap.Entry)this.i.next()).getDoubleKey(); } 
/* 182 */           public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectCollection<V> values()
/*     */   {
/* 199 */     return new AbstractObjectCollection() {
/* 200 */       public boolean contains(Object k) { return AbstractDouble2ObjectMap.this.containsValue(k); } 
/* 201 */       public int size() { return AbstractDouble2ObjectMap.this.size(); } 
/* 202 */       public void clear() { AbstractDouble2ObjectMap.this.clear(); } 
/*     */       public ObjectIterator<V> iterator() {
/* 204 */         return new AbstractObjectIterator() {
/* 205 */           final ObjectIterator<Map.Entry<Double, V>> i = AbstractDouble2ObjectMap.this.entrySet().iterator();
/*     */ 
/* 206 */           public V next() { return ((Double2ObjectMap.Entry)this.i.next()).getValue(); } 
/* 207 */           public boolean hasNext() { return this.i.hasNext(); }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Double, V>> entrySet() {
/* 214 */     return double2ObjectEntrySet();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 223 */     int h = 0; int n = size();
/* 224 */     ObjectIterator i = entrySet().iterator();
/* 225 */     while (n-- != 0) h += ((Map.Entry)i.next()).hashCode();
/* 226 */     return h;
/*     */   }
/*     */   public boolean equals(Object o) {
/* 229 */     if (o == this) return true;
/* 230 */     if (!(o instanceof Map)) return false;
/* 231 */     Map m = (Map)o;
/* 232 */     if (m.size() != size()) return false;
/* 233 */     return entrySet().containsAll(m.entrySet());
/*     */   }
/*     */   public String toString() {
/* 236 */     StringBuilder s = new StringBuilder();
/* 237 */     ObjectIterator i = entrySet().iterator();
/* 238 */     int n = size();
/*     */ 
/* 240 */     boolean first = true;
/* 241 */     s.append("{");
/* 242 */     while (n-- != 0) {
/* 243 */       if (first) first = false; else
/* 244 */         s.append(", ");
/* 245 */       Double2ObjectMap.Entry e = (Double2ObjectMap.Entry)i.next();
/* 246 */       s.append(String.valueOf(e.getDoubleKey()));
/* 247 */       s.append("=>");
/* 248 */       if (this == e.getValue()) s.append("(this map)"); else
/* 249 */         s.append(String.valueOf(e.getValue()));
/*     */     }
/* 251 */     s.append("}");
/* 252 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry<V>
/*     */     implements Double2ObjectMap.Entry<V>
/*     */   {
/*     */     protected double key;
/*     */     protected V value;
/*     */ 
/*     */     public BasicEntry(Double key, V value)
/*     */     {
/* 114 */       this.key = key.doubleValue();
/* 115 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public BasicEntry(double key, V value)
/*     */     {
/* 121 */       this.key = key;
/* 122 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public Double getKey()
/*     */     {
/* 128 */       return Double.valueOf(this.key);
/*     */     }
/*     */ 
/*     */     public double getDoubleKey()
/*     */     {
/* 133 */       return this.key;
/*     */     }
/*     */ 
/*     */     public V getValue()
/*     */     {
/* 138 */       return this.value;
/*     */     }
/*     */ 
/*     */     public V setValue(V value)
/*     */     {
/* 148 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     public boolean equals(Object o) {
/* 151 */       if (!(o instanceof Map.Entry)) return false;
/* 152 */       Map.Entry e = (Map.Entry)o;
/* 153 */       return (this.key == ((Double)e.getKey()).doubleValue()) && (this.value == null ? e.getValue() == null : this.value.equals(e.getValue()));
/*     */     }
/*     */     public int hashCode() {
/* 156 */       return HashCommon.double2int(this.key) ^ (this.value == null ? 0 : this.value.hashCode());
/*     */     }
/*     */     public String toString() {
/* 159 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2ObjectMap
 * JD-Core Version:    0.6.2
 */