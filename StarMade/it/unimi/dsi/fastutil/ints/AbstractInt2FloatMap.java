/*     */ package it.unimi.dsi.fastutil.ints;
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
/*     */ public abstract class AbstractInt2FloatMap extends AbstractInt2FloatFunction
/*     */   implements Int2FloatMap, Serializable
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
/*     */   public boolean containsKey(int k) {
/*  78 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Integer, ? extends Float> m)
/*     */   {
/*  88 */     int n = m.size();
/*  89 */     Iterator i = m.entrySet().iterator();
/*  90 */     if ((m instanceof Serializable))
/*     */     {
/*  92 */       while (n-- != 0) {
/*  93 */         Int2FloatMap.Entry e = (Int2FloatMap.Entry)i.next();
/*  94 */         put(e.getIntKey(), e.getFloatValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  99 */       while (n-- != 0) {
/* 100 */         Map.Entry e = (Map.Entry)i.next();
/* 101 */         put((Integer)e.getKey(), (Float)e.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 106 */   public boolean isEmpty() { return size() == 0; }
/*     */ 
/*     */ 
/*     */   public IntSet keySet()
/*     */   {
/* 191 */     return new AbstractIntSet() {
/*     */       public boolean contains(int k) {
/* 193 */         return AbstractInt2FloatMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractInt2FloatMap.this.size(); } 
/* 196 */       public void clear() { AbstractInt2FloatMap.this.clear(); }
/*     */ 
/*     */       public IntIterator iterator() {
/* 199 */         return new AbstractIntIterator() {
/* 200 */           final ObjectIterator<Map.Entry<Integer, Float>> i = AbstractInt2FloatMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public int nextInt() { return ((Int2FloatMap.Entry)this.i.next()).getIntKey(); } 
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
/* 226 */         return AbstractInt2FloatMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractInt2FloatMap.this.size(); } 
/* 229 */       public void clear() { AbstractInt2FloatMap.this.clear(); }
/*     */ 
/*     */       public FloatIterator iterator() {
/* 232 */         return new AbstractFloatIterator() {
/* 233 */           final ObjectIterator<Map.Entry<Integer, Float>> i = AbstractInt2FloatMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public float nextFloat() { return ((Int2FloatMap.Entry)this.i.next()).getFloatValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Integer, Float>> entrySet()
/*     */   {
/* 246 */     return int2FloatEntrySet();
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
/* 289 */       Int2FloatMap.Entry e = (Int2FloatMap.Entry)i.next();
/*     */ 
/* 294 */       s.append(String.valueOf(e.getIntKey()));
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
/*     */     implements Int2FloatMap.Entry
/*     */   {
/*     */     protected int key;
/*     */     protected float value;
/*     */ 
/*     */     public BasicEntry(Integer key, Float value)
/*     */     {
/* 118 */       this.key = key.intValue();
/* 119 */       this.value = value.floatValue();
/*     */     }
/*     */     public BasicEntry(int key, float value) {
/* 122 */       this.key = key;
/* 123 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public Integer getKey()
/*     */     {
/* 128 */       return Integer.valueOf(this.key);
/*     */     }
/*     */ 
/*     */     public int getIntKey()
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
/* 163 */       return (this.key == ((Integer)e.getKey()).intValue()) && (this.value == ((Float)e.getValue()).floatValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return this.key ^ HashCommon.float2int(this.value);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2FloatMap
 * JD-Core Version:    0.6.2
 */