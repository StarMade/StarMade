/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
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
/*     */ public abstract class AbstractDouble2BooleanMap extends AbstractDouble2BooleanFunction
/*     */   implements Double2BooleanMap, Serializable
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
/*     */   public boolean containsKey(double k) {
/*  78 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Double, ? extends Boolean> m)
/*     */   {
/*  88 */     int n = m.size();
/*  89 */     Iterator i = m.entrySet().iterator();
/*  90 */     if ((m instanceof Serializable))
/*     */     {
/*  92 */       while (n-- != 0) {
/*  93 */         Double2BooleanMap.Entry e = (Double2BooleanMap.Entry)i.next();
/*  94 */         put(e.getDoubleKey(), e.getBooleanValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  99 */       while (n-- != 0) {
/* 100 */         Map.Entry e = (Map.Entry)i.next();
/* 101 */         put((Double)e.getKey(), (Boolean)e.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 106 */   public boolean isEmpty() { return size() == 0; }
/*     */ 
/*     */ 
/*     */   public DoubleSet keySet()
/*     */   {
/* 191 */     return new AbstractDoubleSet() {
/*     */       public boolean contains(double k) {
/* 193 */         return AbstractDouble2BooleanMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractDouble2BooleanMap.this.size(); } 
/* 196 */       public void clear() { AbstractDouble2BooleanMap.this.clear(); }
/*     */ 
/*     */       public DoubleIterator iterator() {
/* 199 */         return new AbstractDoubleIterator() {
/* 200 */           final ObjectIterator<Map.Entry<Double, Boolean>> i = AbstractDouble2BooleanMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public double nextDouble() { return ((Double2BooleanMap.Entry)this.i.next()).getDoubleKey(); } 
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
/* 226 */         return AbstractDouble2BooleanMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractDouble2BooleanMap.this.size(); } 
/* 229 */       public void clear() { AbstractDouble2BooleanMap.this.clear(); }
/*     */ 
/*     */       public BooleanIterator iterator() {
/* 232 */         return new AbstractBooleanIterator() {
/* 233 */           final ObjectIterator<Map.Entry<Double, Boolean>> i = AbstractDouble2BooleanMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public boolean nextBoolean() { return ((Double2BooleanMap.Entry)this.i.next()).getBooleanValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Double, Boolean>> entrySet()
/*     */   {
/* 246 */     return double2BooleanEntrySet();
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
/* 289 */       Double2BooleanMap.Entry e = (Double2BooleanMap.Entry)i.next();
/*     */ 
/* 294 */       s.append(String.valueOf(e.getDoubleKey()));
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
/*     */     implements Double2BooleanMap.Entry
/*     */   {
/*     */     protected double key;
/*     */     protected boolean value;
/*     */ 
/*     */     public BasicEntry(Double key, Boolean value)
/*     */     {
/* 118 */       this.key = key.doubleValue();
/* 119 */       this.value = value.booleanValue();
/*     */     }
/*     */     public BasicEntry(double key, boolean value) {
/* 122 */       this.key = key;
/* 123 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public Double getKey()
/*     */     {
/* 128 */       return Double.valueOf(this.key);
/*     */     }
/*     */ 
/*     */     public double getDoubleKey()
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
/* 163 */       return (this.key == ((Double)e.getKey()).doubleValue()) && (this.value == ((Boolean)e.getValue()).booleanValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return HashCommon.double2int(this.key) ^ (this.value ? 1231 : 1237);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2BooleanMap
 * JD-Core Version:    0.6.2
 */