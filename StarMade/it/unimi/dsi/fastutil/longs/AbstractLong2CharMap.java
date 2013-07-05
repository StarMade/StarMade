/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
/*     */ import it.unimi.dsi.fastutil.chars.AbstractCharIterator;
/*     */ import it.unimi.dsi.fastutil.chars.CharCollection;
/*     */ import it.unimi.dsi.fastutil.chars.CharIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractLong2CharMap extends AbstractLong2CharFunction
/*     */   implements Long2CharMap, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object ov)
/*     */   {
/*  70 */     return containsValue(((Character)ov).charValue());
/*     */   }
/*     */ 
/*     */   public boolean containsValue(char v) {
/*  74 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(long k) {
/*  78 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Long, ? extends Character> m)
/*     */   {
/*  88 */     int n = m.size();
/*  89 */     Iterator i = m.entrySet().iterator();
/*  90 */     if ((m instanceof Serializable))
/*     */     {
/*  92 */       while (n-- != 0) {
/*  93 */         Long2CharMap.Entry e = (Long2CharMap.Entry)i.next();
/*  94 */         put(e.getLongKey(), e.getCharValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  99 */       while (n-- != 0) {
/* 100 */         Map.Entry e = (Map.Entry)i.next();
/* 101 */         put((Long)e.getKey(), (Character)e.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 106 */   public boolean isEmpty() { return size() == 0; }
/*     */ 
/*     */ 
/*     */   public LongSet keySet()
/*     */   {
/* 191 */     return new AbstractLongSet() {
/*     */       public boolean contains(long k) {
/* 193 */         return AbstractLong2CharMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractLong2CharMap.this.size(); } 
/* 196 */       public void clear() { AbstractLong2CharMap.this.clear(); }
/*     */ 
/*     */       public LongIterator iterator() {
/* 199 */         return new AbstractLongIterator() {
/* 200 */           final ObjectIterator<Map.Entry<Long, Character>> i = AbstractLong2CharMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public long nextLong() { return ((Long2CharMap.Entry)this.i.next()).getLongKey(); } 
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
/* 226 */         return AbstractLong2CharMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractLong2CharMap.this.size(); } 
/* 229 */       public void clear() { AbstractLong2CharMap.this.clear(); }
/*     */ 
/*     */       public CharIterator iterator() {
/* 232 */         return new AbstractCharIterator() {
/* 233 */           final ObjectIterator<Map.Entry<Long, Character>> i = AbstractLong2CharMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public char nextChar() { return ((Long2CharMap.Entry)this.i.next()).getCharValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Long, Character>> entrySet()
/*     */   {
/* 246 */     return long2CharEntrySet();
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
/* 289 */       Long2CharMap.Entry e = (Long2CharMap.Entry)i.next();
/*     */ 
/* 294 */       s.append(String.valueOf(e.getLongKey()));
/* 295 */       s.append("=>");
/*     */ 
/* 299 */       s.append(String.valueOf(e.getCharValue()));
/*     */     }
/*     */ 
/* 302 */     s.append("}");
/* 303 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry
/*     */     implements Long2CharMap.Entry
/*     */   {
/*     */     protected long key;
/*     */     protected char value;
/*     */ 
/*     */     public BasicEntry(Long key, Character value)
/*     */     {
/* 118 */       this.key = key.longValue();
/* 119 */       this.value = value.charValue();
/*     */     }
/*     */     public BasicEntry(long key, char value) {
/* 122 */       this.key = key;
/* 123 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public Long getKey()
/*     */     {
/* 128 */       return Long.valueOf(this.key);
/*     */     }
/*     */ 
/*     */     public long getLongKey()
/*     */     {
/* 133 */       return this.key;
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
/* 163 */       return (this.key == ((Long)e.getKey()).longValue()) && (this.value == ((Character)e.getValue()).charValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return HashCommon.long2int(this.key) ^ this.value;
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2CharMap
 * JD-Core Version:    0.6.2
 */