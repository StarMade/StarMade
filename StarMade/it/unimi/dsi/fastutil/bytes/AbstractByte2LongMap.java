/*     */ package it.unimi.dsi.fastutil.bytes;
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
/*     */ public abstract class AbstractByte2LongMap extends AbstractByte2LongFunction
/*     */   implements Byte2LongMap, Serializable
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
/*     */   public boolean containsKey(byte k) {
/*  78 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Byte, ? extends Long> m)
/*     */   {
/*  88 */     int n = m.size();
/*  89 */     Iterator i = m.entrySet().iterator();
/*  90 */     if ((m instanceof Serializable))
/*     */     {
/*  92 */       while (n-- != 0) {
/*  93 */         Byte2LongMap.Entry e = (Byte2LongMap.Entry)i.next();
/*  94 */         put(e.getByteKey(), e.getLongValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  99 */       while (n-- != 0) {
/* 100 */         Map.Entry e = (Map.Entry)i.next();
/* 101 */         put((Byte)e.getKey(), (Long)e.getValue());
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
/* 193 */         return AbstractByte2LongMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractByte2LongMap.this.size(); } 
/* 196 */       public void clear() { AbstractByte2LongMap.this.clear(); }
/*     */ 
/*     */       public ByteIterator iterator() {
/* 199 */         return new AbstractByteIterator() {
/* 200 */           final ObjectIterator<Map.Entry<Byte, Long>> i = AbstractByte2LongMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public byte nextByte() { return ((Byte2LongMap.Entry)this.i.next()).getByteKey(); } 
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
/* 226 */         return AbstractByte2LongMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractByte2LongMap.this.size(); } 
/* 229 */       public void clear() { AbstractByte2LongMap.this.clear(); }
/*     */ 
/*     */       public LongIterator iterator() {
/* 232 */         return new AbstractLongIterator() {
/* 233 */           final ObjectIterator<Map.Entry<Byte, Long>> i = AbstractByte2LongMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public long nextLong() { return ((Byte2LongMap.Entry)this.i.next()).getLongValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Byte, Long>> entrySet()
/*     */   {
/* 246 */     return byte2LongEntrySet();
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
/* 289 */       Byte2LongMap.Entry e = (Byte2LongMap.Entry)i.next();
/*     */ 
/* 294 */       s.append(String.valueOf(e.getByteKey()));
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
/*     */     implements Byte2LongMap.Entry
/*     */   {
/*     */     protected byte key;
/*     */     protected long value;
/*     */ 
/*     */     public BasicEntry(Byte key, Long value)
/*     */     {
/* 118 */       this.key = key.byteValue();
/* 119 */       this.value = value.longValue();
/*     */     }
/*     */     public BasicEntry(byte key, long value) {
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
/* 163 */       return (this.key == ((Byte)e.getKey()).byteValue()) && (this.value == ((Long)e.getValue()).longValue());
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
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2LongMap
 * JD-Core Version:    0.6.2
 */