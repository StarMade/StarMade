/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
/*     */ import it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleCollection;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractByte2DoubleMap extends AbstractByte2DoubleFunction
/*     */   implements Byte2DoubleMap, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object ov)
/*     */   {
/*  70 */     return containsValue(((Double)ov).doubleValue());
/*     */   }
/*     */ 
/*     */   public boolean containsValue(double v) {
/*  74 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(byte k) {
/*  78 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Byte, ? extends Double> m)
/*     */   {
/*  88 */     int n = m.size();
/*  89 */     Iterator i = m.entrySet().iterator();
/*  90 */     if ((m instanceof Serializable))
/*     */     {
/*  92 */       while (n-- != 0) {
/*  93 */         Byte2DoubleMap.Entry e = (Byte2DoubleMap.Entry)i.next();
/*  94 */         put(e.getByteKey(), e.getDoubleValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  99 */       while (n-- != 0) {
/* 100 */         Map.Entry e = (Map.Entry)i.next();
/* 101 */         put((Byte)e.getKey(), (Double)e.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 106 */   public boolean isEmpty() { return size() == 0; }
/*     */ 
/*     */ 
/*     */   public ByteSet keySet()
/*     */   {
/* 191 */     return new AbstractByteSet() {
/*     */       public boolean contains(byte k) {
/* 193 */         return AbstractByte2DoubleMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractByte2DoubleMap.this.size(); } 
/* 196 */       public void clear() { AbstractByte2DoubleMap.this.clear(); }
/*     */ 
/*     */       public ByteIterator iterator() {
/* 199 */         return new AbstractByteIterator() {
/* 200 */           final ObjectIterator<Map.Entry<Byte, Double>> i = AbstractByte2DoubleMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public byte nextByte() { return ((Byte2DoubleMap.Entry)this.i.next()).getByteKey(); } 
/*     */           public boolean hasNext() {
/* 204 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public DoubleCollection values()
/*     */   {
/* 224 */     return new AbstractDoubleCollection() {
/*     */       public boolean contains(double k) {
/* 226 */         return AbstractByte2DoubleMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractByte2DoubleMap.this.size(); } 
/* 229 */       public void clear() { AbstractByte2DoubleMap.this.clear(); }
/*     */ 
/*     */       public DoubleIterator iterator() {
/* 232 */         return new AbstractDoubleIterator() {
/* 233 */           final ObjectIterator<Map.Entry<Byte, Double>> i = AbstractByte2DoubleMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public double nextDouble() { return ((Byte2DoubleMap.Entry)this.i.next()).getDoubleValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Byte, Double>> entrySet()
/*     */   {
/* 246 */     return byte2DoubleEntrySet();
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
/* 289 */       Byte2DoubleMap.Entry e = (Byte2DoubleMap.Entry)i.next();
/*     */ 
/* 294 */       s.append(String.valueOf(e.getByteKey()));
/* 295 */       s.append("=>");
/*     */ 
/* 299 */       s.append(String.valueOf(e.getDoubleValue()));
/*     */     }
/*     */ 
/* 302 */     s.append("}");
/* 303 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry
/*     */     implements Byte2DoubleMap.Entry
/*     */   {
/*     */     protected byte key;
/*     */     protected double value;
/*     */ 
/*     */     public BasicEntry(Byte key, Double value)
/*     */     {
/* 118 */       this.key = key.byteValue();
/* 119 */       this.value = value.doubleValue();
/*     */     }
/*     */     public BasicEntry(byte key, double value) {
/* 122 */       this.key = key;
/* 123 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public Byte getKey()
/*     */     {
/* 128 */       return Byte.valueOf(this.key);
/*     */     }
/*     */ 
/*     */     public byte getByteKey()
/*     */     {
/* 133 */       return this.key;
/*     */     }
/*     */ 
/*     */     public Double getValue()
/*     */     {
/* 138 */       return Double.valueOf(this.value);
/*     */     }
/*     */ 
/*     */     public double getDoubleValue()
/*     */     {
/* 143 */       return this.value;
/*     */     }
/*     */ 
/*     */     public double setValue(double value)
/*     */     {
/* 148 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public Double setValue(Double value)
/*     */     {
/* 154 */       return Double.valueOf(setValue(value.doubleValue()));
/*     */     }
/*     */ 
/*     */     public boolean equals(Object o)
/*     */     {
/* 160 */       if (!(o instanceof Map.Entry)) return false;
/* 161 */       Map.Entry e = (Map.Entry)o;
/*     */ 
/* 163 */       return (this.key == ((Byte)e.getKey()).byteValue()) && (this.value == ((Double)e.getValue()).doubleValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return this.key ^ HashCommon.double2int(this.value);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2DoubleMap
 * JD-Core Version:    0.6.2
 */