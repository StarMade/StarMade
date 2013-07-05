/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
/*     */ import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
/*     */ import it.unimi.dsi.fastutil.ints.IntCollection;
/*     */ import it.unimi.dsi.fastutil.ints.IntIterator;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractReference2IntMap<K> extends AbstractReference2IntFunction<K>
/*     */   implements Reference2IntMap<K>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object ov)
/*     */   {
/*  68 */     return containsValue(((Integer)ov).intValue());
/*     */   }
/*     */ 
/*     */   public boolean containsValue(int v) {
/*  72 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(Object k) {
/*  76 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends K, ? extends Integer> m)
/*     */   {
/*  86 */     int n = m.size();
/*  87 */     Iterator i = m.entrySet().iterator();
/*  88 */     if ((m instanceof Reference2IntMap))
/*     */     {
/*  90 */       while (n-- != 0) {
/*  91 */         Reference2IntMap.Entry e = (Reference2IntMap.Entry)i.next();
/*  92 */         put(e.getKey(), e.getIntValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  97 */       while (n-- != 0) {
/*  98 */         Map.Entry e = (Map.Entry)i.next();
/*  99 */         put(e.getKey(), (Integer)e.getValue());
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
/* 193 */         return AbstractReference2IntMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractReference2IntMap.this.size(); } 
/* 196 */       public void clear() { AbstractReference2IntMap.this.clear(); }
/*     */ 
/*     */       public ObjectIterator<K> iterator() {
/* 199 */         return new AbstractObjectIterator() {
/* 200 */           final ObjectIterator<Map.Entry<K, Integer>> i = AbstractReference2IntMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public K next() { return ((Reference2IntMap.Entry)this.i.next()).getKey(); } 
/*     */           public boolean hasNext() {
/* 204 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public IntCollection values()
/*     */   {
/* 224 */     return new AbstractIntCollection() {
/*     */       public boolean contains(int k) {
/* 226 */         return AbstractReference2IntMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractReference2IntMap.this.size(); } 
/* 229 */       public void clear() { AbstractReference2IntMap.this.clear(); }
/*     */ 
/*     */       public IntIterator iterator() {
/* 232 */         return new AbstractIntIterator() {
/* 233 */           final ObjectIterator<Map.Entry<K, Integer>> i = AbstractReference2IntMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public int nextInt() { return ((Reference2IntMap.Entry)this.i.next()).getIntValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<K, Integer>> entrySet()
/*     */   {
/* 246 */     return reference2IntEntrySet();
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
/* 289 */       Reference2IntMap.Entry e = (Reference2IntMap.Entry)i.next();
/*     */ 
/* 292 */       if (this == e.getKey()) s.append("(this map)");
/*     */       else
/* 294 */         s.append(String.valueOf(e.getKey()));
/* 295 */       s.append("=>");
/*     */ 
/* 299 */       s.append(String.valueOf(e.getIntValue()));
/*     */     }
/*     */ 
/* 302 */     s.append("}");
/* 303 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry<K>
/*     */     implements Reference2IntMap.Entry<K>
/*     */   {
/*     */     protected K key;
/*     */     protected int value;
/*     */ 
/*     */     public BasicEntry(K key, Integer value)
/*     */     {
/* 116 */       this.key = key;
/* 117 */       this.value = value.intValue();
/*     */     }
/*     */ 
/*     */     public BasicEntry(K key, int value) {
/* 121 */       this.key = key;
/* 122 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public K getKey()
/*     */     {
/* 128 */       return this.key;
/*     */     }
/*     */ 
/*     */     public Integer getValue()
/*     */     {
/* 138 */       return Integer.valueOf(this.value);
/*     */     }
/*     */ 
/*     */     public int getIntValue()
/*     */     {
/* 143 */       return this.value;
/*     */     }
/*     */ 
/*     */     public int setValue(int value)
/*     */     {
/* 148 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public Integer setValue(Integer value)
/*     */     {
/* 154 */       return Integer.valueOf(setValue(value.intValue()));
/*     */     }
/*     */ 
/*     */     public boolean equals(Object o)
/*     */     {
/* 160 */       if (!(o instanceof Map.Entry)) return false;
/* 161 */       Map.Entry e = (Map.Entry)o;
/*     */ 
/* 163 */       return (this.key == e.getKey()) && (this.value == ((Integer)e.getValue()).intValue());
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2IntMap
 * JD-Core Version:    0.6.2
 */