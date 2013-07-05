/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractInt2IntMap extends AbstractInt2IntFunction
/*     */   implements Int2IntMap, Serializable
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
/*     */   public boolean containsKey(int k) {
/*  78 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Integer, ? extends Integer> m)
/*     */   {
/*  88 */     int n = m.size();
/*  89 */     Iterator i = m.entrySet().iterator();
/*  90 */     if ((m instanceof Serializable))
/*     */     {
/*  92 */       while (n-- != 0) {
/*  93 */         Int2IntMap.Entry e = (Int2IntMap.Entry)i.next();
/*  94 */         put(e.getIntKey(), e.getIntValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  99 */       while (n-- != 0) {
/* 100 */         Map.Entry e = (Map.Entry)i.next();
/* 101 */         put((Integer)e.getKey(), (Integer)e.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 106 */   public boolean isEmpty() { return size() == 0; }
/*     */ 
/*     */ 
/*     */   public IntSet keySet()
/*     */   {
/* 191 */     return new AbstractIntSet() {
/*     */       public boolean contains(int k) {
/* 193 */         return AbstractInt2IntMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractInt2IntMap.this.size(); } 
/* 196 */       public void clear() { AbstractInt2IntMap.this.clear(); }
/*     */ 
/*     */       public IntIterator iterator() {
/* 199 */         return new AbstractIntIterator() {
/* 200 */           final ObjectIterator<Map.Entry<Integer, Integer>> i = AbstractInt2IntMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public int nextInt() { return ((Int2IntMap.Entry)this.i.next()).getIntKey(); } 
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
/* 226 */         return AbstractInt2IntMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractInt2IntMap.this.size(); } 
/* 229 */       public void clear() { AbstractInt2IntMap.this.clear(); }
/*     */ 
/*     */       public IntIterator iterator() {
/* 232 */         return new AbstractIntIterator() {
/* 233 */           final ObjectIterator<Map.Entry<Integer, Integer>> i = AbstractInt2IntMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public int nextInt() { return ((Int2IntMap.Entry)this.i.next()).getIntValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Integer, Integer>> entrySet()
/*     */   {
/* 246 */     return int2IntEntrySet();
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
/* 289 */       Int2IntMap.Entry e = (Int2IntMap.Entry)i.next();
/*     */ 
/* 294 */       s.append(String.valueOf(e.getIntKey()));
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
/*     */     implements Int2IntMap.Entry
/*     */   {
/*     */     protected int key;
/*     */     protected int value;
/*     */ 
/*     */     public BasicEntry(Integer key, Integer value)
/*     */     {
/* 118 */       this.key = key.intValue();
/* 119 */       this.value = value.intValue();
/*     */     }
/*     */     public BasicEntry(int key, int value) {
/* 122 */       this.key = key;
/* 123 */       this.value = value;
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
/* 163 */       return (this.key == ((Integer)e.getKey()).intValue()) && (this.value == ((Integer)e.getValue()).intValue());
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
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2IntMap
 * JD-Core Version:    0.6.2
 */