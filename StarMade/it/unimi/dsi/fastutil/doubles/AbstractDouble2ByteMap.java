/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
/*     */ import it.unimi.dsi.fastutil.bytes.AbstractByteIterator;
/*     */ import it.unimi.dsi.fastutil.bytes.ByteCollection;
/*     */ import it.unimi.dsi.fastutil.bytes.ByteIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class AbstractDouble2ByteMap extends AbstractDouble2ByteFunction
/*     */   implements Double2ByteMap, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */ 
/*     */   public boolean containsValue(Object ov)
/*     */   {
/*  70 */     return containsValue(((Byte)ov).byteValue());
/*     */   }
/*     */ 
/*     */   public boolean containsValue(byte v) {
/*  74 */     return values().contains(v);
/*     */   }
/*     */ 
/*     */   public boolean containsKey(double k) {
/*  78 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Double, ? extends Byte> m)
/*     */   {
/*  88 */     int n = m.size();
/*  89 */     Iterator i = m.entrySet().iterator();
/*  90 */     if ((m instanceof Serializable))
/*     */     {
/*  92 */       while (n-- != 0) {
/*  93 */         Double2ByteMap.Entry e = (Double2ByteMap.Entry)i.next();
/*  94 */         put(e.getDoubleKey(), e.getByteValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  99 */       while (n-- != 0) {
/* 100 */         Map.Entry e = (Map.Entry)i.next();
/* 101 */         put((Double)e.getKey(), (Byte)e.getValue());
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
/* 193 */         return AbstractDouble2ByteMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractDouble2ByteMap.this.size(); } 
/* 196 */       public void clear() { AbstractDouble2ByteMap.this.clear(); }
/*     */ 
/*     */       public DoubleIterator iterator() {
/* 199 */         return new AbstractDoubleIterator() {
/* 200 */           final ObjectIterator<Map.Entry<Double, Byte>> i = AbstractDouble2ByteMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public double nextDouble() { return ((Double2ByteMap.Entry)this.i.next()).getDoubleKey(); } 
/*     */           public boolean hasNext() {
/* 204 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ByteCollection values()
/*     */   {
/* 224 */     return new AbstractByteCollection() {
/*     */       public boolean contains(byte k) {
/* 226 */         return AbstractDouble2ByteMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractDouble2ByteMap.this.size(); } 
/* 229 */       public void clear() { AbstractDouble2ByteMap.this.clear(); }
/*     */ 
/*     */       public ByteIterator iterator() {
/* 232 */         return new AbstractByteIterator() {
/* 233 */           final ObjectIterator<Map.Entry<Double, Byte>> i = AbstractDouble2ByteMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public byte nextByte() { return ((Double2ByteMap.Entry)this.i.next()).getByteValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Double, Byte>> entrySet()
/*     */   {
/* 246 */     return double2ByteEntrySet();
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
/* 289 */       Double2ByteMap.Entry e = (Double2ByteMap.Entry)i.next();
/*     */ 
/* 294 */       s.append(String.valueOf(e.getDoubleKey()));
/* 295 */       s.append("=>");
/*     */ 
/* 299 */       s.append(String.valueOf(e.getByteValue()));
/*     */     }
/*     */ 
/* 302 */     s.append("}");
/* 303 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static class BasicEntry
/*     */     implements Double2ByteMap.Entry
/*     */   {
/*     */     protected double key;
/*     */     protected byte value;
/*     */ 
/*     */     public BasicEntry(Double key, Byte value)
/*     */     {
/* 118 */       this.key = key.doubleValue();
/* 119 */       this.value = value.byteValue();
/*     */     }
/*     */     public BasicEntry(double key, byte value) {
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
/*     */     public Byte getValue()
/*     */     {
/* 138 */       return Byte.valueOf(this.value);
/*     */     }
/*     */ 
/*     */     public byte getByteValue()
/*     */     {
/* 143 */       return this.value;
/*     */     }
/*     */ 
/*     */     public byte setValue(byte value)
/*     */     {
/* 148 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public Byte setValue(Byte value)
/*     */     {
/* 154 */       return Byte.valueOf(setValue(value.byteValue()));
/*     */     }
/*     */ 
/*     */     public boolean equals(Object o)
/*     */     {
/* 160 */       if (!(o instanceof Map.Entry)) return false;
/* 161 */       Map.Entry e = (Map.Entry)o;
/*     */ 
/* 163 */       return (this.key == ((Double)e.getKey()).doubleValue()) && (this.value == ((Byte)e.getValue()).byteValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return HashCommon.double2int(this.key) ^ this.value;
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2ByteMap
 * JD-Core Version:    0.6.2
 */