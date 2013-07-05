/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
/*     */ import it.unimi.dsi.fastutil.longs.AbstractLongIterator;
/*     */ import it.unimi.dsi.fastutil.longs.LongCollection;
/*     */ import it.unimi.dsi.fastutil.longs.LongIterator;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractReference2LongMap<K> extends AbstractReference2LongFunction<K>
/*     */   implements Reference2LongMap<K>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object ov)
/*     */   {
/*  68 */     return containsValue(((Long)ov).longValue());
/*     */   }
/*     */ 
/*     */   public boolean containsValue(long v) {
/*  72 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(Object k) {
/*  76 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends K, ? extends Long> m)
/*     */   {
/*  86 */     int n = m.size();
/*  87 */     Iterator i = m.entrySet().iterator();
/*  88 */     if ((m instanceof Reference2LongMap))
/*     */     {
/*  90 */       while (n-- != 0) {
/*  91 */         Reference2LongMap.Entry e = (Reference2LongMap.Entry)i.next();
/*  92 */         put(e.getKey(), e.getLongValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  97 */       while (n-- != 0) {
/*  98 */         Map.Entry e = (Map.Entry)i.next();
/*  99 */         put(e.getKey(), (Long)e.getValue());
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
/* 193 */         return AbstractReference2LongMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractReference2LongMap.this.size(); } 
/* 196 */       public void clear() { AbstractReference2LongMap.this.clear(); }
/*     */ 
/*     */       public ObjectIterator<K> iterator() {
/* 199 */         return new AbstractObjectIterator() {
/* 200 */           final ObjectIterator<Map.Entry<K, Long>> i = AbstractReference2LongMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public K next() { return ((Reference2LongMap.Entry)this.i.next()).getKey(); } 
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
/* 226 */         return AbstractReference2LongMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractReference2LongMap.this.size(); } 
/* 229 */       public void clear() { AbstractReference2LongMap.this.clear(); }
/*     */ 
/*     */       public LongIterator iterator() {
/* 232 */         return new AbstractLongIterator() {
/* 233 */           final ObjectIterator<Map.Entry<K, Long>> i = AbstractReference2LongMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public long nextLong() { return ((Reference2LongMap.Entry)this.i.next()).getLongValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<K, Long>> entrySet()
/*     */   {
/* 246 */     return reference2LongEntrySet();
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
/* 289 */       Reference2LongMap.Entry e = (Reference2LongMap.Entry)i.next();
/*     */ 
/* 292 */       if (this == e.getKey()) s.append("(this map)");
/*     */       else
/* 294 */         s.append(String.valueOf(e.getKey()));
/* 295 */       s.append("=>");
/*     */ 
/* 299 */       s.append(String.valueOf(e.getLongValue()));
/*     */     }
/*     */ 
/* 302 */     s.append("}");
/* 303 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry<K>
/*     */     implements Reference2LongMap.Entry<K>
/*     */   {
/*     */     protected K key;
/*     */     protected long value;
/*     */ 
/*     */     public BasicEntry(K key, Long value)
/*     */     {
/* 116 */       this.key = key;
/* 117 */       this.value = value.longValue();
/*     */     }
/*     */ 
/*     */     public BasicEntry(K key, long value) {
/* 121 */       this.key = key;
/* 122 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public K getKey()
/*     */     {
/* 128 */       return this.key;
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
/* 163 */       return (this.key == e.getKey()) && (this.value == ((Long)e.getValue()).longValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return (this.key == null ? 0 : System.identityHashCode(this.key)) ^ HashCommon.long2int(this.value);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2LongMap
 * JD-Core Version:    0.6.2
 */