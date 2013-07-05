/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
/*     */ import it.unimi.dsi.fastutil.shorts.AbstractShortIterator;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortCollection;
/*     */ import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractReference2ShortMap<K> extends AbstractReference2ShortFunction<K>
/*     */   implements Reference2ShortMap<K>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object ov)
/*     */   {
/*  68 */     return containsValue(((Short)ov).shortValue());
/*     */   }
/*     */ 
/*     */   public boolean containsValue(short v) {
/*  72 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(Object k) {
/*  76 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends K, ? extends Short> m)
/*     */   {
/*  86 */     int n = m.size();
/*  87 */     Iterator i = m.entrySet().iterator();
/*  88 */     if ((m instanceof Reference2ShortMap))
/*     */     {
/*  90 */       while (n-- != 0) {
/*  91 */         Reference2ShortMap.Entry e = (Reference2ShortMap.Entry)i.next();
/*  92 */         put(e.getKey(), e.getShortValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  97 */       while (n-- != 0) {
/*  98 */         Map.Entry e = (Map.Entry)i.next();
/*  99 */         put(e.getKey(), (Short)e.getValue());
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
/* 193 */         return AbstractReference2ShortMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractReference2ShortMap.this.size(); } 
/* 196 */       public void clear() { AbstractReference2ShortMap.this.clear(); }
/*     */ 
/*     */       public ObjectIterator<K> iterator() {
/* 199 */         return new AbstractObjectIterator() {
/* 200 */           final ObjectIterator<Map.Entry<K, Short>> i = AbstractReference2ShortMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public K next() { return ((Reference2ShortMap.Entry)this.i.next()).getKey(); } 
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
/* 226 */         return AbstractReference2ShortMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractReference2ShortMap.this.size(); } 
/* 229 */       public void clear() { AbstractReference2ShortMap.this.clear(); }
/*     */ 
/*     */       public ShortIterator iterator() {
/* 232 */         return new AbstractShortIterator() {
/* 233 */           final ObjectIterator<Map.Entry<K, Short>> i = AbstractReference2ShortMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public short nextShort() { return ((Reference2ShortMap.Entry)this.i.next()).getShortValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<K, Short>> entrySet()
/*     */   {
/* 246 */     return reference2ShortEntrySet();
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
/* 289 */       Reference2ShortMap.Entry e = (Reference2ShortMap.Entry)i.next();
/*     */ 
/* 292 */       if (this == e.getKey()) s.append("(this map)");
/*     */       else
/* 294 */         s.append(String.valueOf(e.getKey()));
/* 295 */       s.append("=>");
/*     */ 
/* 299 */       s.append(String.valueOf(e.getShortValue()));
/*     */     }
/*     */ 
/* 302 */     s.append("}");
/* 303 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry<K>
/*     */     implements Reference2ShortMap.Entry<K>
/*     */   {
/*     */     protected K key;
/*     */     protected short value;
/*     */ 
/*     */     public BasicEntry(K key, Short value)
/*     */     {
/* 116 */       this.key = key;
/* 117 */       this.value = value.shortValue();
/*     */     }
/*     */ 
/*     */     public BasicEntry(K key, short value) {
/* 121 */       this.key = key;
/* 122 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public K getKey()
/*     */     {
/* 128 */       return this.key;
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
/* 163 */       return (this.key == e.getKey()) && (this.value == ((Short)e.getValue()).shortValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return (this.key == null ? 0 : System.identityHashCode(this.key)) ^ this.value;
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2ShortMap
 * JD-Core Version:    0.6.2
 */