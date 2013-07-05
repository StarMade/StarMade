/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
/*     */ import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleCollection;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleIterator;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractReference2DoubleMap<K> extends AbstractReference2DoubleFunction<K>
/*     */   implements Reference2DoubleMap<K>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object ov)
/*     */   {
/*  68 */     return containsValue(((Double)ov).doubleValue());
/*     */   }
/*     */ 
/*     */   public boolean containsValue(double v) {
/*  72 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(Object k) {
/*  76 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends K, ? extends Double> m)
/*     */   {
/*  86 */     int n = m.size();
/*  87 */     Iterator i = m.entrySet().iterator();
/*  88 */     if ((m instanceof Reference2DoubleMap))
/*     */     {
/*  90 */       while (n-- != 0) {
/*  91 */         Reference2DoubleMap.Entry e = (Reference2DoubleMap.Entry)i.next();
/*  92 */         put(e.getKey(), e.getDoubleValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  97 */       while (n-- != 0) {
/*  98 */         Map.Entry e = (Map.Entry)i.next();
/*  99 */         put(e.getKey(), (Double)e.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 104 */   public boolean isEmpty() { return size() == 0; }
/*     */ 
/*     */ 
/*     */   public ReferenceSet<K> keySet()
/*     */   {
/* 191 */     return new AbstractReferenceSet() {
/*     */       public boolean contains(Object k) {
/* 193 */         return AbstractReference2DoubleMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractReference2DoubleMap.this.size(); } 
/* 196 */       public void clear() { AbstractReference2DoubleMap.this.clear(); }
/*     */ 
/*     */       public ObjectIterator<K> iterator() {
/* 199 */         return new AbstractObjectIterator() {
/* 200 */           final ObjectIterator<Map.Entry<K, Double>> i = AbstractReference2DoubleMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public K next() { return ((Reference2DoubleMap.Entry)this.i.next()).getKey(); } 
/*     */           public boolean hasNext() {
/* 204 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public DoubleCollection values()
/*     */   {
/* 224 */     return new AbstractDoubleCollection() {
/*     */       public boolean contains(double k) {
/* 226 */         return AbstractReference2DoubleMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractReference2DoubleMap.this.size(); } 
/* 229 */       public void clear() { AbstractReference2DoubleMap.this.clear(); }
/*     */ 
/*     */       public DoubleIterator iterator() {
/* 232 */         return new AbstractDoubleIterator() {
/* 233 */           final ObjectIterator<Map.Entry<K, Double>> i = AbstractReference2DoubleMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public double nextDouble() { return ((Reference2DoubleMap.Entry)this.i.next()).getDoubleValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<K, Double>> entrySet()
/*     */   {
/* 246 */     return reference2DoubleEntrySet();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 259 */     int h = 0; int n = size();
/* 260 */     ObjectIterator i = entrySet().iterator();
/*     */ 
/* 262 */     while (n-- != 0) h += ((Map.Entry)i.next()).hashCode();
/* 263 */     return h;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object o) {
/* 267 */     if (o == this) return true;
/* 268 */     if (!(o instanceof Map)) return false;
/*     */ 
/* 270 */     Map m = (Map)o;
/* 271 */     if (m.size() != size()) return false;
/* 272 */     return entrySet().containsAll(m.entrySet());
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 277 */     StringBuilder s = new StringBuilder();
/* 278 */     ObjectIterator i = entrySet().iterator();
/* 279 */     int n = size();
/*     */ 
/* 281 */     boolean first = true;
/*     */ 
/* 283 */     s.append("{");
/*     */ 
/* 285 */     while (n-- != 0) {
/* 286 */       if (first) first = false; else {
/* 287 */         s.append(", ");
/*     */       }
/* 289 */       Reference2DoubleMap.Entry e = (Reference2DoubleMap.Entry)i.next();
/*     */ 
/* 292 */       if (this == e.getKey()) s.append("(this map)");
/*     */       else
/* 294 */         s.append(String.valueOf(e.getKey()));
/* 295 */       s.append("=>");
/*     */ 
/* 299 */       s.append(String.valueOf(e.getDoubleValue()));
/*     */     }
/*     */ 
/* 302 */     s.append("}");
/* 303 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry<K>
/*     */     implements Reference2DoubleMap.Entry<K>
/*     */   {
/*     */     protected K key;
/*     */     protected double value;
/*     */ 
/*     */     public BasicEntry(K key, Double value)
/*     */     {
/* 116 */       this.key = key;
/* 117 */       this.value = value.doubleValue();
/*     */     }
/*     */ 
/*     */     public BasicEntry(K key, double value) {
/* 121 */       this.key = key;
/* 122 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public K getKey()
/*     */     {
/* 128 */       return this.key;
/*     */     }
/*     */ 
/*     */     public Double getValue()
/*     */     {
/* 138 */       return Double.valueOf(this.value);
/*     */     }
/*     */ 
/*     */     public double getDoubleValue()
/*     */     {
/* 143 */       return this.value;
/*     */     }
/*     */ 
/*     */     public double setValue(double value)
/*     */     {
/* 148 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public Double setValue(Double value)
/*     */     {
/* 154 */       return Double.valueOf(setValue(value.doubleValue()));
/*     */     }
/*     */ 
/*     */     public boolean equals(Object o)
/*     */     {
/* 160 */       if (!(o instanceof Map.Entry)) return false;
/* 161 */       Map.Entry e = (Map.Entry)o;
/*     */ 
/* 163 */       return (this.key == e.getKey()) && (this.value == ((Double)e.getValue()).doubleValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return (this.key == null ? 0 : System.identityHashCode(this.key)) ^ HashCommon.double2int(this.value);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2DoubleMap
 * JD-Core Version:    0.6.2
 */