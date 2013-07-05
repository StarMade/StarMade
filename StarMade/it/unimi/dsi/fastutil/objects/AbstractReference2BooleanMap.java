/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
/*     */ import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractReference2BooleanMap<K> extends AbstractReference2BooleanFunction<K>
/*     */   implements Reference2BooleanMap<K>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object ov)
/*     */   {
/*  68 */     return containsValue(((Boolean)ov).booleanValue());
/*     */   }
/*     */ 
/*     */   public boolean containsValue(boolean v) {
/*  72 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(Object k) {
/*  76 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends K, ? extends Boolean> m)
/*     */   {
/*  86 */     int n = m.size();
/*  87 */     Iterator i = m.entrySet().iterator();
/*  88 */     if ((m instanceof Reference2BooleanMap))
/*     */     {
/*  90 */       while (n-- != 0) {
/*  91 */         Reference2BooleanMap.Entry e = (Reference2BooleanMap.Entry)i.next();
/*  92 */         put(e.getKey(), e.getBooleanValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  97 */       while (n-- != 0) {
/*  98 */         Map.Entry e = (Map.Entry)i.next();
/*  99 */         put(e.getKey(), (Boolean)e.getValue());
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
/* 193 */         return AbstractReference2BooleanMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractReference2BooleanMap.this.size(); } 
/* 196 */       public void clear() { AbstractReference2BooleanMap.this.clear(); }
/*     */ 
/*     */       public ObjectIterator<K> iterator() {
/* 199 */         return new AbstractObjectIterator() {
/* 200 */           final ObjectIterator<Map.Entry<K, Boolean>> i = AbstractReference2BooleanMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public K next() { return ((Reference2BooleanMap.Entry)this.i.next()).getKey(); } 
/*     */           public boolean hasNext() {
/* 204 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public BooleanCollection values()
/*     */   {
/* 224 */     return new AbstractBooleanCollection() {
/*     */       public boolean contains(boolean k) {
/* 226 */         return AbstractReference2BooleanMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractReference2BooleanMap.this.size(); } 
/* 229 */       public void clear() { AbstractReference2BooleanMap.this.clear(); }
/*     */ 
/*     */       public BooleanIterator iterator() {
/* 232 */         return new AbstractBooleanIterator() {
/* 233 */           final ObjectIterator<Map.Entry<K, Boolean>> i = AbstractReference2BooleanMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public boolean nextBoolean() { return ((Reference2BooleanMap.Entry)this.i.next()).getBooleanValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<K, Boolean>> entrySet()
/*     */   {
/* 246 */     return reference2BooleanEntrySet();
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
/* 289 */       Reference2BooleanMap.Entry e = (Reference2BooleanMap.Entry)i.next();
/*     */ 
/* 292 */       if (this == e.getKey()) s.append("(this map)");
/*     */       else
/* 294 */         s.append(String.valueOf(e.getKey()));
/* 295 */       s.append("=>");
/*     */ 
/* 299 */       s.append(String.valueOf(e.getBooleanValue()));
/*     */     }
/*     */ 
/* 302 */     s.append("}");
/* 303 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry<K>
/*     */     implements Reference2BooleanMap.Entry<K>
/*     */   {
/*     */     protected K key;
/*     */     protected boolean value;
/*     */ 
/*     */     public BasicEntry(K key, Boolean value)
/*     */     {
/* 116 */       this.key = key;
/* 117 */       this.value = value.booleanValue();
/*     */     }
/*     */ 
/*     */     public BasicEntry(K key, boolean value) {
/* 121 */       this.key = key;
/* 122 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public K getKey()
/*     */     {
/* 128 */       return this.key;
/*     */     }
/*     */ 
/*     */     public Boolean getValue()
/*     */     {
/* 138 */       return Boolean.valueOf(this.value);
/*     */     }
/*     */ 
/*     */     public boolean getBooleanValue()
/*     */     {
/* 143 */       return this.value;
/*     */     }
/*     */ 
/*     */     public boolean setValue(boolean value)
/*     */     {
/* 148 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public Boolean setValue(Boolean value)
/*     */     {
/* 154 */       return Boolean.valueOf(setValue(value.booleanValue()));
/*     */     }
/*     */ 
/*     */     public boolean equals(Object o)
/*     */     {
/* 160 */       if (!(o instanceof Map.Entry)) return false;
/* 161 */       Map.Entry e = (Map.Entry)o;
/*     */ 
/* 163 */       return (this.key == e.getKey()) && (this.value == ((Boolean)e.getValue()).booleanValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return (this.key == null ? 0 : System.identityHashCode(this.key)) ^ (this.value ? 1231 : 1237);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2BooleanMap
 * JD-Core Version:    0.6.2
 */