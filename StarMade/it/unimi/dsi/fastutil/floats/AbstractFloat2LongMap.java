/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
/*     */ import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
/*     */ import it.unimi.dsi.fastutil.longs.LongCollection;
/*     */ import it.unimi.dsi.fastutil.longs.LongIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractFloat2LongMap extends AbstractFloat2LongFunction
/*     */   implements Float2LongMap, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object ov)
/*     */   {
/*  70 */     return containsValue(((Long)ov).longValue());
/*     */   }
/*     */ 
/*     */   public boolean containsValue(long v) {
/*  74 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(float k) {
/*  78 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Float, ? extends Long> m)
/*     */   {
/*  88 */     int n = m.size();
/*  89 */     Iterator i = m.entrySet().iterator();
/*  90 */     if ((m instanceof Serializable))
/*     */     {
/*  92 */       while (n-- != 0) {
/*  93 */         Float2LongMap.Entry e = (Float2LongMap.Entry)i.next();
/*  94 */         put(e.getFloatKey(), e.getLongValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  99 */       while (n-- != 0) {
/* 100 */         Map.Entry e = (Map.Entry)i.next();
/* 101 */         put((Float)e.getKey(), (Long)e.getValue());
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
/* 193 */         return AbstractFloat2LongMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractFloat2LongMap.this.size(); } 
/* 196 */       public void clear() { AbstractFloat2LongMap.this.clear(); }
/*     */ 
/*     */       public FloatIterator iterator() {
/* 199 */         return new AbstractFloatIterator() {
/* 200 */           final ObjectIterator<Map.Entry<Float, Long>> i = AbstractFloat2LongMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public float nextFloat() { return ((Float2LongMap.Entry)this.i.next()).getFloatKey(); } 
/*     */           public boolean hasNext() {
/* 204 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public LongCollection values()
/*     */   {
/* 224 */     return new AbstractLongCollection() {
/*     */       public boolean contains(long k) {
/* 226 */         return AbstractFloat2LongMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractFloat2LongMap.this.size(); } 
/* 229 */       public void clear() { AbstractFloat2LongMap.this.clear(); }
/*     */ 
/*     */       public LongIterator iterator() {
/* 232 */         return new AbstractLongIterator() {
/* 233 */           final ObjectIterator<Map.Entry<Float, Long>> i = AbstractFloat2LongMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public long nextLong() { return ((Float2LongMap.Entry)this.i.next()).getLongValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Float, Long>> entrySet()
/*     */   {
/* 246 */     return float2LongEntrySet();
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
/* 289 */       Float2LongMap.Entry e = (Float2LongMap.Entry)i.next();
/*     */ 
/* 294 */       s.append(String.valueOf(e.getFloatKey()));
/* 295 */       s.append("=>");
/*     */ 
/* 299 */       s.append(String.valueOf(e.getLongValue()));
/*     */     }
/*     */ 
/* 302 */     s.append("}");
/* 303 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry
/*     */     implements Float2LongMap.Entry
/*     */   {
/*     */     protected float key;
/*     */     protected long value;
/*     */ 
/*     */     public BasicEntry(Float key, Long value)
/*     */     {
/* 118 */       this.key = key.floatValue();
/* 119 */       this.value = value.longValue();
/*     */     }
/*     */     public BasicEntry(float key, long value) {
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
/*     */     public Long getValue()
/*     */     {
/* 138 */       return Long.valueOf(this.value);
/*     */     }
/*     */ 
/*     */     public long getLongValue()
/*     */     {
/* 143 */       return this.value;
/*     */     }
/*     */ 
/*     */     public long setValue(long value)
/*     */     {
/* 148 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public Long setValue(Long value)
/*     */     {
/* 154 */       return Long.valueOf(setValue(value.longValue()));
/*     */     }
/*     */ 
/*     */     public boolean equals(Object o)
/*     */     {
/* 160 */       if (!(o instanceof Map.Entry)) return false;
/* 161 */       Map.Entry e = (Map.Entry)o;
/*     */ 
/* 163 */       return (this.key == ((Float)e.getKey()).floatValue()) && (this.value == ((Long)e.getValue()).longValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return HashCommon.float2int(this.key) ^ HashCommon.long2int(this.value);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2LongMap
 * JD-Core Version:    0.6.2
 */