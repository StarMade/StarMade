/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import it.unimi.dsi.fastutil.objects.ReferenceCollection;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractShort2ReferenceMap<V> extends AbstractShort2ReferenceFunction<V>
/*     */   implements Short2ReferenceMap<V>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object v)
/*     */   {
/*  69 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(short k) {
/*  73 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Short, ? extends V> m)
/*     */   {
/*  83 */     int n = m.size();
/*  84 */     Iterator i = m.entrySet().iterator();
/*  85 */     if ((m instanceof Short2ReferenceMap))
/*     */     {
/*  87 */       while (n-- != 0) {
/*  88 */         Short2ReferenceMap.Entry e = (Short2ReferenceMap.Entry)i.next();
/*  89 */         put(e.getShortKey(), e.getValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  94 */       while (n-- != 0) {
/*  95 */         Map.Entry e = (Map.Entry)i.next();
/*  96 */         put((Short)e.getKey(), e.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 101 */   public boolean isEmpty() { return size() == 0; }
/*     */ 
/*     */ 
/*     */   public ShortSet keySet()
/*     */   {
/* 174 */     return new AbstractShortSet() {
/* 175 */       public boolean contains(short k) { return AbstractShort2ReferenceMap.this.containsKey(k); } 
/* 176 */       public int size() { return AbstractShort2ReferenceMap.this.size(); } 
/* 177 */       public void clear() { AbstractShort2ReferenceMap.this.clear(); } 
/*     */       public ShortIterator iterator() {
/* 179 */         return new AbstractShortIterator() {
/* 180 */           final ObjectIterator<Map.Entry<Short, V>> i = AbstractShort2ReferenceMap.this.entrySet().iterator();
/*     */ 
/* 181 */           public short nextShort() { return ((Short2ReferenceMap.Entry)this.i.next()).getShortKey(); } 
/* 182 */           public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ReferenceCollection<V> values()
/*     */   {
/* 199 */     return new AbstractReferenceCollection() {
/* 200 */       public boolean contains(Object k) { return AbstractShort2ReferenceMap.this.containsValue(k); } 
/* 201 */       public int size() { return AbstractShort2ReferenceMap.this.size(); } 
/* 202 */       public void clear() { AbstractShort2ReferenceMap.this.clear(); } 
/*     */       public ObjectIterator<V> iterator() {
/* 204 */         return new AbstractObjectIterator() {
/* 205 */           final ObjectIterator<Map.Entry<Short, V>> i = AbstractShort2ReferenceMap.this.entrySet().iterator();
/*     */ 
/* 206 */           public V next() { return ((Short2ReferenceMap.Entry)this.i.next()).getValue(); } 
/* 207 */           public boolean hasNext() { return this.i.hasNext(); }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Short, V>> entrySet() {
/* 214 */     return short2ReferenceEntrySet();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 223 */     int h = 0; int n = size();
/* 224 */     ObjectIterator i = entrySet().iterator();
/* 225 */     while (n-- != 0) h += ((Map.Entry)i.next()).hashCode();
/* 226 */     return h;
/*     */   }
/*     */   public boolean equals(Object o) {
/* 229 */     if (o == this) return true;
/* 230 */     if (!(o instanceof Map)) return false;
/* 231 */     Map m = (Map)o;
/* 232 */     if (m.size() != size()) return false;
/* 233 */     return entrySet().containsAll(m.entrySet());
/*     */   }
/*     */   public String toString() {
/* 236 */     StringBuilder s = new StringBuilder();
/* 237 */     ObjectIterator i = entrySet().iterator();
/* 238 */     int n = size();
/*     */ 
/* 240 */     boolean first = true;
/* 241 */     s.append("{");
/* 242 */     while (n-- != 0) {
/* 243 */       if (first) first = false; else
/* 244 */         s.append(", ");
/* 245 */       Short2ReferenceMap.Entry e = (Short2ReferenceMap.Entry)i.next();
/* 246 */       s.append(String.valueOf(e.getShortKey()));
/* 247 */       s.append("=>");
/* 248 */       if (this == e.getValue()) s.append("(this map)"); else
/* 249 */         s.append(String.valueOf(e.getValue()));
/*     */     }
/* 251 */     s.append("}");
/* 252 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry<V>
/*     */     implements Short2ReferenceMap.Entry<V>
/*     */   {
/*     */     protected short key;
/*     */     protected V value;
/*     */ 
/*     */     public BasicEntry(Short key, V value)
/*     */     {
/* 114 */       this.key = key.shortValue();
/* 115 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public BasicEntry(short key, V value)
/*     */     {
/* 121 */       this.key = key;
/* 122 */       this.value = value;
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
/*     */     public V getValue()
/*     */     {
/* 138 */       return this.value;
/*     */     }
/*     */ 
/*     */     public V setValue(V value)
/*     */     {
/* 148 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     public boolean equals(Object o) {
/* 151 */       if (!(o instanceof Map.Entry)) return false;
/* 152 */       Map.Entry e = (Map.Entry)o;
/* 153 */       return (this.key == ((Short)e.getKey()).shortValue()) && (this.value == e.getValue());
/*     */     }
/*     */     public int hashCode() {
/* 156 */       return this.key ^ (this.value == null ? 0 : System.identityHashCode(this.value));
/*     */     }
/*     */     public String toString() {
/* 159 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2ReferenceMap
 * JD-Core Version:    0.6.2
 */