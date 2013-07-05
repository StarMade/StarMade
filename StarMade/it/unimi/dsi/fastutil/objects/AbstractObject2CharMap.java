/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
/*     */ import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
/*     */ import it.unimi.dsi.fastutil.chars.CharCollection;
/*     */ import it.unimi.dsi.fastutil.chars.CharIterator;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractObject2CharMap<K> extends AbstractObject2CharFunction<K>
/*     */   implements Object2CharMap<K>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object ov)
/*     */   {
/*  68 */     return containsValue(((Character)ov).charValue());
/*     */   }
/*     */ 
/*     */   public boolean containsValue(char v) {
/*  72 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(Object k) {
/*  76 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends K, ? extends Character> m)
/*     */   {
/*  86 */     int n = m.size();
/*  87 */     Iterator i = m.entrySet().iterator();
/*  88 */     if ((m instanceof Object2CharMap))
/*     */     {
/*  90 */       while (n-- != 0) {
/*  91 */         Object2CharMap.Entry e = (Object2CharMap.Entry)i.next();
/*  92 */         put(e.getKey(), e.getCharValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  97 */       while (n-- != 0) {
/*  98 */         Map.Entry e = (Map.Entry)i.next();
/*  99 */         put(e.getKey(), (Character)e.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 104 */   public boolean isEmpty() { return size() == 0; }
/*     */ 
/*     */ 
/*     */   public ObjectSet<K> keySet()
/*     */   {
/* 191 */     return new AbstractObjectSet() {
/*     */       public boolean contains(Object k) {
/* 193 */         return AbstractObject2CharMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractObject2CharMap.this.size(); } 
/* 196 */       public void clear() { AbstractObject2CharMap.this.clear(); }
/*     */ 
/*     */       public ObjectIterator<K> iterator() {
/* 199 */         return new AbstractObjectIterator() {
/* 200 */           final ObjectIterator<Map.Entry<K, Character>> i = AbstractObject2CharMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public K next() { return ((Object2CharMap.Entry)this.i.next()).getKey(); } 
/*     */           public boolean hasNext() {
/* 204 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public CharCollection values()
/*     */   {
/* 224 */     return new AbstractCharCollection() {
/*     */       public boolean contains(char k) {
/* 226 */         return AbstractObject2CharMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractObject2CharMap.this.size(); } 
/* 229 */       public void clear() { AbstractObject2CharMap.this.clear(); }
/*     */ 
/*     */       public CharIterator iterator() {
/* 232 */         return new AbstractCharIterator() {
/* 233 */           final ObjectIterator<Map.Entry<K, Character>> i = AbstractObject2CharMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public char nextChar() { return ((Object2CharMap.Entry)this.i.next()).getCharValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<K, Character>> entrySet()
/*     */   {
/* 246 */     return object2CharEntrySet();
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
/* 289 */       Object2CharMap.Entry e = (Object2CharMap.Entry)i.next();
/*     */ 
/* 292 */       if (this == e.getKey()) s.append("(this map)");
/*     */       else
/* 294 */         s.append(String.valueOf(e.getKey()));
/* 295 */       s.append("=>");
/*     */ 
/* 299 */       s.append(String.valueOf(e.getCharValue()));
/*     */     }
/*     */ 
/* 302 */     s.append("}");
/* 303 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry<K>
/*     */     implements Object2CharMap.Entry<K>
/*     */   {
/*     */     protected K key;
/*     */     protected char value;
/*     */ 
/*     */     public BasicEntry(K key, Character value)
/*     */     {
/* 116 */       this.key = key;
/* 117 */       this.value = value.charValue();
/*     */     }
/*     */ 
/*     */     public BasicEntry(K key, char value) {
/* 121 */       this.key = key;
/* 122 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public K getKey()
/*     */     {
/* 128 */       return this.key;
/*     */     }
/*     */ 
/*     */     public Character getValue()
/*     */     {
/* 138 */       return Character.valueOf(this.value);
/*     */     }
/*     */ 
/*     */     public char getCharValue()
/*     */     {
/* 143 */       return this.value;
/*     */     }
/*     */ 
/*     */     public char setValue(char value)
/*     */     {
/* 148 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public Character setValue(Character value)
/*     */     {
/* 154 */       return Character.valueOf(setValue(value.charValue()));
/*     */     }
/*     */ 
/*     */     public boolean equals(Object o)
/*     */     {
/* 160 */       if (!(o instanceof Map.Entry)) return false;
/* 161 */       Map.Entry e = (Map.Entry)o;
/*     */ 
/* 163 */       return (this.key == null ? e.getKey() == null : this.key.equals(e.getKey())) && (this.value == ((Character)e.getValue()).charValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return (this.key == null ? 0 : this.key.hashCode()) ^ this.value;
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2CharMap
 * JD-Core Version:    0.6.2
 */