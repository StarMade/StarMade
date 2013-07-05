/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
/*     */ import it.unimi.dsi.fastutil.ints.AbstractIntIterator;
/*     */ import it.unimi.dsi.fastutil.ints.IntCollection;
/*     */ import it.unimi.dsi.fastutil.ints.IntIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractShort2IntMap extends AbstractShort2IntFunction
/*     */   implements Short2IntMap, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object ov)
/*     */   {
/*  70 */     return containsValue(((Integer)ov).intValue());
/*     */   }
/*     */ 
/*     */   public boolean containsValue(int v) {
/*  74 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(short k) {
/*  78 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Short, ? extends Integer> m)
/*     */   {
/*  88 */     int n = m.size();
/*  89 */     Iterator i = m.entrySet().iterator();
/*  90 */     if ((m instanceof Serializable))
/*     */     {
/*  92 */       while (n-- != 0) {
/*  93 */         Short2IntMap.Entry e = (Short2IntMap.Entry)i.next();
/*  94 */         put(e.getShortKey(), e.getIntValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  99 */       while (n-- != 0) {
/* 100 */         Map.Entry e = (Map.Entry)i.next();
/* 101 */         put((Short)e.getKey(), (Integer)e.getValue());
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
/* 193 */         return AbstractShort2IntMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractShort2IntMap.this.size(); } 
/* 196 */       public void clear() { AbstractShort2IntMap.this.clear(); }
/*     */ 
/*     */       public ShortIterator iterator() {
/* 199 */         return new AbstractShortIterator() {
/* 200 */           final ObjectIterator<Map.Entry<Short, Integer>> i = AbstractShort2IntMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public short nextShort() { return ((Short2IntMap.Entry)this.i.next()).getShortKey(); } 
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
/* 226 */         return AbstractShort2IntMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractShort2IntMap.this.size(); } 
/* 229 */       public void clear() { AbstractShort2IntMap.this.clear(); }
/*     */ 
/*     */       public IntIterator iterator() {
/* 232 */         return new AbstractIntIterator() {
/* 233 */           final ObjectIterator<Map.Entry<Short, Integer>> i = AbstractShort2IntMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public int nextInt() { return ((Short2IntMap.Entry)this.i.next()).getIntValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Short, Integer>> entrySet()
/*     */   {
/* 246 */     return short2IntEntrySet();
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
/* 289 */       Short2IntMap.Entry e = (Short2IntMap.Entry)i.next();
/*     */ 
/* 294 */       s.append(String.valueOf(e.getShortKey()));
/* 295 */       s.append("=>");
/*     */ 
/* 299 */       s.append(String.valueOf(e.getIntValue()));
/*     */     }
/*     */ 
/* 302 */     s.append("}");
/* 303 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry
/*     */     implements Short2IntMap.Entry
/*     */   {
/*     */     protected short key;
/*     */     protected int value;
/*     */ 
/*     */     public BasicEntry(Short key, Integer value)
/*     */     {
/* 118 */       this.key = key.shortValue();
/* 119 */       this.value = value.intValue();
/*     */     }
/*     */     public BasicEntry(short key, int value) {
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
/* 163 */       return (this.key == ((Short)e.getKey()).shortValue()) && (this.value == ((Integer)e.getValue()).intValue());
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
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2IntMap
 * JD-Core Version:    0.6.2
 */