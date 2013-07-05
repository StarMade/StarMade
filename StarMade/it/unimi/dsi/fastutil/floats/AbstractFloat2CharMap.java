/*     */ package it.unimi.dsi.fastutil.floats;
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
/*     */ public abstract class AbstractFloat2CharMap extends AbstractFloat2CharFunction
/*     */   implements Float2CharMap, Serializable
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
/*     */   public boolean containsKey(float k) {
/*  78 */     return keySet().contains(k);
/*     */   }
/*     */ 
/*     */   public void putAll(Map<? extends Float, ? extends Character> m)
/*     */   {
/*  88 */     int n = m.size();
/*  89 */     Iterator i = m.entrySet().iterator();
/*  90 */     if ((m instanceof Serializable))
/*     */     {
/*  92 */       while (n-- != 0) {
/*  93 */         Float2CharMap.Entry e = (Float2CharMap.Entry)i.next();
/*  94 */         put(e.getFloatKey(), e.getCharValue());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  99 */       while (n-- != 0) {
/* 100 */         Map.Entry e = (Map.Entry)i.next();
/* 101 */         put((Float)e.getKey(), (Character)e.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 106 */   public boolean isEmpty() { return size() == 0; }
/*     */ 
/*     */ 
/*     */   public FloatSet keySet()
/*     */   {
/* 191 */     return new AbstractFloatSet() {
/*     */       public boolean contains(float k) {
/* 193 */         return AbstractFloat2CharMap.this.containsKey(k);
/*     */       }
/* 195 */       public int size() { return AbstractFloat2CharMap.this.size(); } 
/* 196 */       public void clear() { AbstractFloat2CharMap.this.clear(); }
/*     */ 
/*     */       public FloatIterator iterator() {
/* 199 */         return new AbstractFloatIterator() {
/* 200 */           final ObjectIterator<Map.Entry<Float, Character>> i = AbstractFloat2CharMap.this.entrySet().iterator();
/*     */ 
/* 202 */           public float nextFloat() { return ((Float2CharMap.Entry)this.i.next()).getFloatKey(); } 
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
/* 226 */         return AbstractFloat2CharMap.this.containsValue(k);
/*     */       }
/* 228 */       public int size() { return AbstractFloat2CharMap.this.size(); } 
/* 229 */       public void clear() { AbstractFloat2CharMap.this.clear(); }
/*     */ 
/*     */       public CharIterator iterator() {
/* 232 */         return new AbstractCharIterator() {
/* 233 */           final ObjectIterator<Map.Entry<Float, Character>> i = AbstractFloat2CharMap.this.entrySet().iterator();
/*     */ 
/* 235 */           public char nextChar() { return ((Float2CharMap.Entry)this.i.next()).getCharValue(); } 
/*     */           public boolean hasNext() {
/* 237 */             return this.i.hasNext();
/*     */           }
/*     */         };
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ObjectSet<Map.Entry<Float, Character>> entrySet()
/*     */   {
/* 246 */     return float2CharEntrySet();
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
/* 289 */       Float2CharMap.Entry e = (Float2CharMap.Entry)i.next();
/*     */ 
/* 294 */       s.append(String.valueOf(e.getFloatKey()));
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
/*     */     implements Float2CharMap.Entry
/*     */   {
/*     */     protected float key;
/*     */     protected char value;
/*     */ 
/*     */     public BasicEntry(Float key, Character value)
/*     */     {
/* 118 */       this.key = key.floatValue();
/* 119 */       this.value = value.charValue();
/*     */     }
/*     */     public BasicEntry(float key, char value) {
/* 122 */       this.key = key;
/* 123 */       this.value = value;
/*     */     }
/*     */ 
/*     */     public Float getKey()
/*     */     {
/* 128 */       return Float.valueOf(this.key);
/*     */     }
/*     */ 
/*     */     public float getFloatKey()
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
/* 163 */       return (this.key == ((Float)e.getKey()).floatValue()) && (this.value == ((Character)e.getValue()).charValue());
/*     */     }
/*     */ 
/*     */     public int hashCode() {
/* 167 */       return HashCommon.float2int(this.key) ^ this.value;
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 172 */       return this.key + "->" + this.value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2CharMap
 * JD-Core Version:    0.6.2
 */