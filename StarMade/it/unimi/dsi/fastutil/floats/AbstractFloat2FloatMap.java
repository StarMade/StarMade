/*     */ package it.unimi.dsi.fastutil.floats;
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
/*     */ public abstract class AbstractFloat2FloatMap extends AbstractFloat2FloatFunction
/*     */   implements Float2FloatMap, Serializable
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
/*     */   public boolean containsKey(float k) {
/*  78 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Float, ? extends Float> m)
/*     */   {
/*  88 */     int n = m.size();
/*  89 */     Iterator i = m.entrySet().iterator();
/*  90 */     if ((m instanceof Serializable))
/*     */     {
/*  92 */       while (n-- != 0) {
/*  93 */         Float2FloatMap.Entry e = (Float2FloatMap.Entry)i.next();
/*  94 */         put(e.getFloatKey(), e.getFloatValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  99 */       while (n-- != 0) {
/* 100 */         Map.Entry e = (Map.Entry)i.next();
/* 101 */         put((Float)e.getKey(), (Float)e.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 106 */   public boolean isEmpty() { return size() == 0; }
/*     */ 
/*     */ 
/*     */   public FloatSet keySet()
/*     */   {
/* 191 */     return new AbstractFloatSet() {
/*     */       public boolean contains(float k) {
/* 193 */         return AbstractFloat2FloatMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractFloat2FloatMap.this.size(); } 
/* 196 */       public void clear() { AbstractFloat2FloatMap.this.clear(); }
/*     */ 
/*     */       public FloatIterator iterator() {
/* 199 */         return new AbstractFloatIterator() {
/* 200 */           final ObjectIterator<Map.Entry<Float, Float>> i = AbstractFloat2FloatMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public float nextFloat() { return ((Float2FloatMap.Entry)this.i.next()).getFloatKey(); } 
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
/* 226 */         return AbstractFloat2FloatMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractFloat2FloatMap.this.size(); } 
/* 229 */       public void clear() { AbstractFloat2FloatMap.this.clear(); }
/*     */ 
/*     */       public FloatIterator iterator() {
/* 232 */         return new AbstractFloatIterator() {
/* 233 */           final ObjectIterator<Map.Entry<Float, Float>> i = AbstractFloat2FloatMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public float nextFloat() { return ((Float2FloatMap.Entry)this.i.next()).getFloatValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Float, Float>> entrySet()
/*     */   {
/* 246 */     return float2FloatEntrySet();
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
/* 289 */       Float2FloatMap.Entry e = (Float2FloatMap.Entry)i.next();
/*     */ 
/* 294 */       s.append(String.valueOf(e.getFloatKey()));
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
/*     */     implements Float2FloatMap.Entry
/*     */   {
/*     */     protected float key;
/*     */     protected float value;
/*     */ 
/*     */     public BasicEntry(Float key, Float value)
/*     */     {
/* 118 */       this.key = key.floatValue();
/* 119 */       this.value = value.floatValue();
/*     */     }
/*     */     public BasicEntry(float key, float value) {
/* 122 */       this.key = key;
/* 123 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public Float getKey()
/*     */     {
/* 128 */       return Float.valueOf(this.key);
/*     */     }
/*     */ 
/*     */     public float getFloatKey()
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
/* 163 */       return (this.key == ((Float)e.getKey()).floatValue()) && (this.value == ((Float)e.getValue()).floatValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return HashCommon.float2int(this.key) ^ HashCommon.float2int(this.value);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2FloatMap
 * JD-Core Version:    0.6.2
 */