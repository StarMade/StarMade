/*     */ package it.unimi.dsi.fastutil.chars;
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
/*     */ public abstract class AbstractChar2LongMap extends AbstractChar2LongFunction
/*     */   implements Char2LongMap, Serializable
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
/*     */   public boolean containsKey(char k) {
/*  78 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Character, ? extends Long> m)
/*     */   {
/*  88 */     int n = m.size();
/*  89 */     Iterator i = m.entrySet().iterator();
/*  90 */     if ((m instanceof Serializable))
/*     */     {
/*  92 */       while (n-- != 0) {
/*  93 */         Char2LongMap.Entry e = (Char2LongMap.Entry)i.next();
/*  94 */         put(e.getCharKey(), e.getLongValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  99 */       while (n-- != 0) {
/* 100 */         Map.Entry e = (Map.Entry)i.next();
/* 101 */         put((Character)e.getKey(), (Long)e.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 106 */   public boolean isEmpty() { return size() == 0; }
/*     */ 
/*     */ 
/*     */   public CharSet keySet()
/*     */   {
/* 191 */     return new AbstractCharSet() {
/*     */       public boolean contains(char k) {
/* 193 */         return AbstractChar2LongMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractChar2LongMap.this.size(); } 
/* 196 */       public void clear() { AbstractChar2LongMap.this.clear(); }
/*     */ 
/*     */       public CharIterator iterator() {
/* 199 */         return new AbstractCharIterator() {
/* 200 */           final ObjectIterator<Map.Entry<Character, Long>> i = AbstractChar2LongMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public char nextChar() { return ((Char2LongMap.Entry)this.i.next()).getCharKey(); } 
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
/* 226 */         return AbstractChar2LongMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractChar2LongMap.this.size(); } 
/* 229 */       public void clear() { AbstractChar2LongMap.this.clear(); }
/*     */ 
/*     */       public LongIterator iterator() {
/* 232 */         return new AbstractLongIterator() {
/* 233 */           final ObjectIterator<Map.Entry<Character, Long>> i = AbstractChar2LongMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public long nextLong() { return ((Char2LongMap.Entry)this.i.next()).getLongValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Character, Long>> entrySet()
/*     */   {
/* 246 */     return char2LongEntrySet();
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
/* 289 */       Char2LongMap.Entry e = (Char2LongMap.Entry)i.next();
/*     */ 
/* 294 */       s.append(String.valueOf(e.getCharKey()));
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
/*     */     implements Char2LongMap.Entry
/*     */   {
/*     */     protected char key;
/*     */     protected long value;
/*     */ 
/*     */     public BasicEntry(Character key, Long value)
/*     */     {
/* 118 */       this.key = key.charValue();
/* 119 */       this.value = value.longValue();
/*     */     }
/*     */     public BasicEntry(char key, long value) {
/* 122 */       this.key = key;
/* 123 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public Character getKey()
/*     */     {
/* 128 */       return Character.valueOf(this.key);
/*     */     }
/*     */ 
/*     */     public char getCharKey()
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
/* 163 */       return (this.key == ((Character)e.getKey()).charValue()) && (this.value == ((Long)e.getValue()).longValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return this.key ^ HashCommon.long2int(this.value);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2LongMap
 * JD-Core Version:    0.6.2
 */