/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
/*     */ import it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractInt2BooleanMap extends AbstractInt2BooleanFunction
/*     */   implements Int2BooleanMap, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object ov)
/*     */   {
/*  70 */     return containsValue(((Boolean)ov).booleanValue());
/*     */   }
/*     */ 
/*     */   public boolean containsValue(boolean v) {
/*  74 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(int k) {
/*  78 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Integer, ? extends Boolean> m)
/*     */   {
/*  88 */     int n = m.size();
/*  89 */     Iterator i = m.entrySet().iterator();
/*  90 */     if ((m instanceof Serializable))
/*     */     {
/*  92 */       while (n-- != 0) {
/*  93 */         Int2BooleanMap.Entry e = (Int2BooleanMap.Entry)i.next();
/*  94 */         put(e.getIntKey(), e.getBooleanValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  99 */       while (n-- != 0) {
/* 100 */         Map.Entry e = (Map.Entry)i.next();
/* 101 */         put((Integer)e.getKey(), (Boolean)e.getValue());
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
/* 193 */         return AbstractInt2BooleanMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractInt2BooleanMap.this.size(); } 
/* 196 */       public void clear() { AbstractInt2BooleanMap.this.clear(); }
/*     */ 
/*     */       public IntIterator iterator() {
/* 199 */         return new AbstractIntIterator() {
/* 200 */           final ObjectIterator<Map.Entry<Integer, Boolean>> i = AbstractInt2BooleanMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public int nextInt() { return ((Int2BooleanMap.Entry)this.i.next()).getIntKey(); } 
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
/* 226 */         return AbstractInt2BooleanMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractInt2BooleanMap.this.size(); } 
/* 229 */       public void clear() { AbstractInt2BooleanMap.this.clear(); }
/*     */ 
/*     */       public BooleanIterator iterator() {
/* 232 */         return new AbstractBooleanIterator() {
/* 233 */           final ObjectIterator<Map.Entry<Integer, Boolean>> i = AbstractInt2BooleanMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public boolean nextBoolean() { return ((Int2BooleanMap.Entry)this.i.next()).getBooleanValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Integer, Boolean>> entrySet()
/*     */   {
/* 246 */     return int2BooleanEntrySet();
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
/* 289 */       Int2BooleanMap.Entry e = (Int2BooleanMap.Entry)i.next();
/*     */ 
/* 294 */       s.append(String.valueOf(e.getIntKey()));
/* 295 */       s.append("=>");
/*     */ 
/* 299 */       s.append(String.valueOf(e.getBooleanValue()));
/*     */     }
/*     */ 
/* 302 */     s.append("}");
/* 303 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry
/*     */     implements Int2BooleanMap.Entry
/*     */   {
/*     */     protected int key;
/*     */     protected boolean value;
/*     */ 
/*     */     public BasicEntry(Integer key, Boolean value)
/*     */     {
/* 118 */       this.key = key.intValue();
/* 119 */       this.value = value.booleanValue();
/*     */     }
/*     */     public BasicEntry(int key, boolean value) {
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
/* 163 */       return (this.key == ((Integer)e.getKey()).intValue()) && (this.value == ((Boolean)e.getValue()).booleanValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return this.key ^ (this.value ? 1231 : 1237);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2BooleanMap
 * JD-Core Version:    0.6.2
 */