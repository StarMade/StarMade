/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractDouble2DoubleMap extends AbstractDouble2DoubleFunction
/*     */   implements Double2DoubleMap, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object ov)
/*     */   {
/*  70 */     return containsValue(((Double)ov).doubleValue());
/*     */   }
/*     */ 
/*     */   public boolean containsValue(double v) {
/*  74 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(double k) {
/*  78 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Double, ? extends Double> m)
/*     */   {
/*  88 */     int n = m.size();
/*  89 */     Iterator i = m.entrySet().iterator();
/*  90 */     if ((m instanceof Serializable))
/*     */     {
/*  92 */       while (n-- != 0) {
/*  93 */         Double2DoubleMap.Entry e = (Double2DoubleMap.Entry)i.next();
/*  94 */         put(e.getDoubleKey(), e.getDoubleValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  99 */       while (n-- != 0) {
/* 100 */         Map.Entry e = (Map.Entry)i.next();
/* 101 */         put((Double)e.getKey(), (Double)e.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 106 */   public boolean isEmpty() { return size() == 0; }
/*     */ 
/*     */ 
/*     */   public DoubleSet keySet()
/*     */   {
/* 191 */     return new AbstractDoubleSet() {
/*     */       public boolean contains(double k) {
/* 193 */         return AbstractDouble2DoubleMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractDouble2DoubleMap.this.size(); } 
/* 196 */       public void clear() { AbstractDouble2DoubleMap.this.clear(); }
/*     */ 
/*     */       public DoubleIterator iterator() {
/* 199 */         return new AbstractDoubleIterator() {
/* 200 */           final ObjectIterator<Map.Entry<Double, Double>> i = AbstractDouble2DoubleMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public double nextDouble() { return ((Double2DoubleMap.Entry)this.i.next()).getDoubleKey(); } 
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
/* 226 */         return AbstractDouble2DoubleMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractDouble2DoubleMap.this.size(); } 
/* 229 */       public void clear() { AbstractDouble2DoubleMap.this.clear(); }
/*     */ 
/*     */       public DoubleIterator iterator() {
/* 232 */         return new AbstractDoubleIterator() {
/* 233 */           final ObjectIterator<Map.Entry<Double, Double>> i = AbstractDouble2DoubleMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public double nextDouble() { return ((Double2DoubleMap.Entry)this.i.next()).getDoubleValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Double, Double>> entrySet()
/*     */   {
/* 246 */     return double2DoubleEntrySet();
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
/* 289 */       Double2DoubleMap.Entry e = (Double2DoubleMap.Entry)i.next();
/*     */ 
/* 294 */       s.append(String.valueOf(e.getDoubleKey()));
/* 295 */       s.append("=>");
/*     */ 
/* 299 */       s.append(String.valueOf(e.getDoubleValue()));
/*     */     }
/*     */ 
/* 302 */     s.append("}");
/* 303 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry
/*     */     implements Double2DoubleMap.Entry
/*     */   {
/*     */     protected double key;
/*     */     protected double value;
/*     */ 
/*     */     public BasicEntry(Double key, Double value)
/*     */     {
/* 118 */       this.key = key.doubleValue();
/* 119 */       this.value = value.doubleValue();
/*     */     }
/*     */     public BasicEntry(double key, double value) {
/* 122 */       this.key = key;
/* 123 */       this.value = value;
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
/* 163 */       return (this.key == ((Double)e.getKey()).doubleValue()) && (this.value == ((Double)e.getValue()).doubleValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return HashCommon.double2int(this.key) ^ HashCommon.double2int(this.value);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2DoubleMap
 * JD-Core Version:    0.6.2
 */