/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractShort2ShortMap extends AbstractShort2ShortFunction
/*     */   implements Short2ShortMap, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object ov)
/*     */   {
/*  70 */     return containsValue(((Short)ov).shortValue());
/*     */   }
/*     */ 
/*     */   public boolean containsValue(short v) {
/*  74 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(short k) {
/*  78 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Short, ? extends Short> m)
/*     */   {
/*  88 */     int n = m.size();
/*  89 */     Iterator i = m.entrySet().iterator();
/*  90 */     if ((m instanceof Serializable))
/*     */     {
/*  92 */       while (n-- != 0) {
/*  93 */         Short2ShortMap.Entry e = (Short2ShortMap.Entry)i.next();
/*  94 */         put(e.getShortKey(), e.getShortValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  99 */       while (n-- != 0) {
/* 100 */         Map.Entry e = (Map.Entry)i.next();
/* 101 */         put((Short)e.getKey(), (Short)e.getValue());
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
/* 193 */         return AbstractShort2ShortMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractShort2ShortMap.this.size(); } 
/* 196 */       public void clear() { AbstractShort2ShortMap.this.clear(); }
/*     */ 
/*     */       public ShortIterator iterator() {
/* 199 */         return new AbstractShortIterator() {
/* 200 */           final ObjectIterator<Map.Entry<Short, Short>> i = AbstractShort2ShortMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public short nextShort() { return ((Short2ShortMap.Entry)this.i.next()).getShortKey(); } 
/*     */           public boolean hasNext() {
/* 204 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ShortCollection values()
/*     */   {
/* 224 */     return new AbstractShortCollection() {
/*     */       public boolean contains(short k) {
/* 226 */         return AbstractShort2ShortMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractShort2ShortMap.this.size(); } 
/* 229 */       public void clear() { AbstractShort2ShortMap.this.clear(); }
/*     */ 
/*     */       public ShortIterator iterator() {
/* 232 */         return new AbstractShortIterator() {
/* 233 */           final ObjectIterator<Map.Entry<Short, Short>> i = AbstractShort2ShortMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public short nextShort() { return ((Short2ShortMap.Entry)this.i.next()).getShortValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Short, Short>> entrySet()
/*     */   {
/* 246 */     return short2ShortEntrySet();
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
/* 289 */       Short2ShortMap.Entry e = (Short2ShortMap.Entry)i.next();
/*     */ 
/* 294 */       s.append(String.valueOf(e.getShortKey()));
/* 295 */       s.append("=>");
/*     */ 
/* 299 */       s.append(String.valueOf(e.getShortValue()));
/*     */     }
/*     */ 
/* 302 */     s.append("}");
/* 303 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry
/*     */     implements Short2ShortMap.Entry
/*     */   {
/*     */     protected short key;
/*     */     protected short value;
/*     */ 
/*     */     public BasicEntry(Short key, Short value)
/*     */     {
/* 118 */       this.key = key.shortValue();
/* 119 */       this.value = value.shortValue();
/*     */     }
/*     */     public BasicEntry(short key, short value) {
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
/*     */     public Short getValue()
/*     */     {
/* 138 */       return Short.valueOf(this.value);
/*     */     }
/*     */ 
/*     */     public short getShortValue()
/*     */     {
/* 143 */       return this.value;
/*     */     }
/*     */ 
/*     */     public short setValue(short value)
/*     */     {
/* 148 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public Short setValue(Short value)
/*     */     {
/* 154 */       return Short.valueOf(setValue(value.shortValue()));
/*     */     }
/*     */ 
/*     */     public boolean equals(Object o)
/*     */     {
/* 160 */       if (!(o instanceof Map.Entry)) return false;
/* 161 */       Map.Entry e = (Map.Entry)o;
/*     */ 
/* 163 */       return (this.key == ((Short)e.getKey()).shortValue()) && (this.value == ((Short)e.getValue()).shortValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return this.key ^ this.value;
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2ShortMap
 * JD-Core Version:    0.6.2
 */