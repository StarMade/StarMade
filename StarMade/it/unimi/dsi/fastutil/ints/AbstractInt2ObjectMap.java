/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractInt2ObjectMap<V> extends AbstractInt2ObjectFunction<V>
/*     */   implements Int2ObjectMap<V>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object v)
/*     */   {
/*  69 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(int k) {
/*  73 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Integer, ? extends V> m)
/*     */   {
/*  83 */     int n = m.size();
/*  84 */     Iterator i = m.entrySet().iterator();
/*  85 */     if ((m instanceof Int2ObjectMap))
/*     */     {
/*  87 */       while (n-- != 0) {
/*  88 */         Int2ObjectMap.Entry e = (Int2ObjectMap.Entry)i.next();
/*  89 */         put(e.getIntKey(), e.getValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  94 */       while (n-- != 0) {
/*  95 */         Map.Entry e = (Map.Entry)i.next();
/*  96 */         put((Integer)e.getKey(), e.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 101 */   public boolean isEmpty() { return size() == 0; }
/*     */ 
/*     */ 
/*     */   public IntSet keySet()
/*     */   {
/* 174 */     return new AbstractIntSet() {
/* 175 */       public boolean contains(int k) { return AbstractInt2ObjectMap.this.containsKey(k); } 
/* 176 */       public int size() { return AbstractInt2ObjectMap.this.size(); } 
/* 177 */       public void clear() { AbstractInt2ObjectMap.this.clear(); } 
/*     */       public IntIterator iterator() {
/* 179 */         return new AbstractIntIterator() {
/* 180 */           final ObjectIterator<Map.Entry<Integer, V>> i = AbstractInt2ObjectMap.this.entrySet().iterator();
/*     */ 
/* 181 */           public int nextInt() { return ((Int2ObjectMap.Entry)this.i.next()).getIntKey(); } 
/* 182 */           public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectCollection<V> values()
/*     */   {
/* 199 */     return new AbstractObjectCollection() {
/* 200 */       public boolean contains(Object k) { return AbstractInt2ObjectMap.this.containsValue(k); } 
/* 201 */       public int size() { return AbstractInt2ObjectMap.this.size(); } 
/* 202 */       public void clear() { AbstractInt2ObjectMap.this.clear(); } 
/*     */       public ObjectIterator<V> iterator() {
/* 204 */         return new AbstractObjectIterator() {
/* 205 */           final ObjectIterator<Map.Entry<Integer, V>> i = AbstractInt2ObjectMap.this.entrySet().iterator();
/*     */ 
/* 206 */           public V next() { return ((Int2ObjectMap.Entry)this.i.next()).getValue(); } 
/* 207 */           public boolean hasNext() { return this.i.hasNext(); }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Integer, V>> entrySet() {
/* 214 */     return int2ObjectEntrySet();
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
/* 245 */       Int2ObjectMap.Entry e = (Int2ObjectMap.Entry)i.next();
/* 246 */       s.append(String.valueOf(e.getIntKey()));
/* 247 */       s.append("=>");
/* 248 */       if (this == e.getValue()) s.append("(this map)"); else
/* 249 */         s.append(String.valueOf(e.getValue()));
/*     */     }
/* 251 */     s.append("}");
/* 252 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry<V>
/*     */     implements Int2ObjectMap.Entry<V>
/*     */   {
/*     */     protected int key;
/*     */     protected V value;
/*     */ 
/*     */     public BasicEntry(Integer key, V value)
/*     */     {
/* 114 */       this.key = key.intValue();
/* 115 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public BasicEntry(int key, V value)
/*     */     {
/* 121 */       this.key = key;
/* 122 */       this.value = value;
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
/* 153 */       return (this.key == ((Integer)e.getKey()).intValue()) && (this.value == null ? e.getValue() == null : this.value.equals(e.getValue()));
/*     */     }
/*     */     public int hashCode() {
/* 156 */       return this.key ^ (this.value == null ? 0 : this.value.hashCode());
/*     */     }
/*     */     public String toString() {
/* 159 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2ObjectMap
 * JD-Core Version:    0.6.2
 */