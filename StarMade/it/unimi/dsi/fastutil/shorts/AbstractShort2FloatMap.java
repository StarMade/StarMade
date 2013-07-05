/*     */ package it.unimi.dsi.fastutil.shorts;
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
/*     */ public abstract class AbstractShort2FloatMap extends AbstractShort2FloatFunction
/*     */   implements Short2FloatMap, Serializable
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
/*     */   public boolean containsKey(short k) {
/*  78 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Short, ? extends Float> m)
/*     */   {
/*  88 */     int n = m.size();
/*  89 */     Iterator i = m.entrySet().iterator();
/*  90 */     if ((m instanceof Serializable))
/*     */     {
/*  92 */       while (n-- != 0) {
/*  93 */         Short2FloatMap.Entry e = (Short2FloatMap.Entry)i.next();
/*  94 */         put(e.getShortKey(), e.getFloatValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  99 */       while (n-- != 0) {
/* 100 */         Map.Entry e = (Map.Entry)i.next();
/* 101 */         put((Short)e.getKey(), (Float)e.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 106 */   public boolean isEmpty() { return size() == 0; }
/*     */ 
/*     */ 
/*     */   public ShortSet keySet()
/*     */   {
/* 191 */     return new AbstractShortSet() {
/*     */       public boolean contains(short k) {
/* 193 */         return AbstractShort2FloatMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractShort2FloatMap.this.size(); } 
/* 196 */       public void clear() { AbstractShort2FloatMap.this.clear(); }
/*     */ 
/*     */       public ShortIterator iterator() {
/* 199 */         return new AbstractShortIterator() {
/* 200 */           final ObjectIterator<Map.Entry<Short, Float>> i = AbstractShort2FloatMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public short nextShort() { return ((Short2FloatMap.Entry)this.i.next()).getShortKey(); } 
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
/* 226 */         return AbstractShort2FloatMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractShort2FloatMap.this.size(); } 
/* 229 */       public void clear() { AbstractShort2FloatMap.this.clear(); }
/*     */ 
/*     */       public FloatIterator iterator() {
/* 232 */         return new AbstractFloatIterator() {
/* 233 */           final ObjectIterator<Map.Entry<Short, Float>> i = AbstractShort2FloatMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public float nextFloat() { return ((Short2FloatMap.Entry)this.i.next()).getFloatValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Short, Float>> entrySet()
/*     */   {
/* 246 */     return short2FloatEntrySet();
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
/* 289 */       Short2FloatMap.Entry e = (Short2FloatMap.Entry)i.next();
/*     */ 
/* 294 */       s.append(String.valueOf(e.getShortKey()));
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
/*     */     implements Short2FloatMap.Entry
/*     */   {
/*     */     protected short key;
/*     */     protected float value;
/*     */ 
/*     */     public BasicEntry(Short key, Float value)
/*     */     {
/* 118 */       this.key = key.shortValue();
/* 119 */       this.value = value.floatValue();
/*     */     }
/*     */     public BasicEntry(short key, float value) {
/* 122 */       this.key = key;
/* 123 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public Short getKey()
/*     */     {
/* 128 */       return Short.valueOf(this.key);
/*     */     }
/*     */ 
/*     */     public short getShortKey()
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
/* 163 */       return (this.key == ((Short)e.getKey()).shortValue()) && (this.value == ((Float)e.getValue()).floatValue());
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
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2FloatMap
 * JD-Core Version:    0.6.2
 */