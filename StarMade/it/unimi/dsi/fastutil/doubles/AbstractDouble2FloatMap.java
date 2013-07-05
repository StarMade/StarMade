/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
/*     */ import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
/*     */ import it.unimi.dsi.fastutil.floats.FloatCollection;
/*     */ import it.unimi.dsi.fastutil.floats.FloatIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractDouble2FloatMap extends AbstractDouble2FloatFunction
/*     */   implements Double2FloatMap, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object ov)
/*     */   {
/*  70 */     return containsValue(((Float)ov).floatValue());
/*     */   }
/*     */ 
/*     */   public boolean containsValue(float v) {
/*  74 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(double k) {
/*  78 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Double, ? extends Float> m)
/*     */   {
/*  88 */     int n = m.size();
/*  89 */     Iterator i = m.entrySet().iterator();
/*  90 */     if ((m instanceof Serializable))
/*     */     {
/*  92 */       while (n-- != 0) {
/*  93 */         Double2FloatMap.Entry e = (Double2FloatMap.Entry)i.next();
/*  94 */         put(e.getDoubleKey(), e.getFloatValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  99 */       while (n-- != 0) {
/* 100 */         Map.Entry e = (Map.Entry)i.next();
/* 101 */         put((Double)e.getKey(), (Float)e.getValue());
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
/* 193 */         return AbstractDouble2FloatMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractDouble2FloatMap.this.size(); } 
/* 196 */       public void clear() { AbstractDouble2FloatMap.this.clear(); }
/*     */ 
/*     */       public DoubleIterator iterator() {
/* 199 */         return new AbstractDoubleIterator() {
/* 200 */           final ObjectIterator<Map.Entry<Double, Float>> i = AbstractDouble2FloatMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public double nextDouble() { return ((Double2FloatMap.Entry)this.i.next()).getDoubleKey(); } 
/*     */           public boolean hasNext() {
/* 204 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public FloatCollection values()
/*     */   {
/* 224 */     return new AbstractFloatCollection() {
/*     */       public boolean contains(float k) {
/* 226 */         return AbstractDouble2FloatMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractDouble2FloatMap.this.size(); } 
/* 229 */       public void clear() { AbstractDouble2FloatMap.this.clear(); }
/*     */ 
/*     */       public FloatIterator iterator() {
/* 232 */         return new AbstractFloatIterator() {
/* 233 */           final ObjectIterator<Map.Entry<Double, Float>> i = AbstractDouble2FloatMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public float nextFloat() { return ((Double2FloatMap.Entry)this.i.next()).getFloatValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Double, Float>> entrySet()
/*     */   {
/* 246 */     return double2FloatEntrySet();
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
/* 289 */       Double2FloatMap.Entry e = (Double2FloatMap.Entry)i.next();
/*     */ 
/* 294 */       s.append(String.valueOf(e.getDoubleKey()));
/* 295 */       s.append("=>");
/*     */ 
/* 299 */       s.append(String.valueOf(e.getFloatValue()));
/*     */     }
/*     */ 
/* 302 */     s.append("}");
/* 303 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry
/*     */     implements Double2FloatMap.Entry
/*     */   {
/*     */     protected double key;
/*     */     protected float value;
/*     */ 
/*     */     public BasicEntry(Double key, Float value)
/*     */     {
/* 118 */       this.key = key.doubleValue();
/* 119 */       this.value = value.floatValue();
/*     */     }
/*     */     public BasicEntry(double key, float value) {
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
/*     */     public Float getValue()
/*     */     {
/* 138 */       return Float.valueOf(this.value);
/*     */     }
/*     */ 
/*     */     public float getFloatValue()
/*     */     {
/* 143 */       return this.value;
/*     */     }
/*     */ 
/*     */     public float setValue(float value)
/*     */     {
/* 148 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public Float setValue(Float value)
/*     */     {
/* 154 */       return Float.valueOf(setValue(value.floatValue()));
/*     */     }
/*     */ 
/*     */     public boolean equals(Object o)
/*     */     {
/* 160 */       if (!(o instanceof Map.Entry)) return false;
/* 161 */       Map.Entry e = (Map.Entry)o;
/*     */ 
/* 163 */       return (this.key == ((Double)e.getKey()).doubleValue()) && (this.value == ((Float)e.getValue()).floatValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return HashCommon.double2int(this.key) ^ HashCommon.float2int(this.value);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2FloatMap
 * JD-Core Version:    0.6.2
 */