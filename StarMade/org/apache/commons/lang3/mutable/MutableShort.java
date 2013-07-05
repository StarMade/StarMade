/*     */ package org.apache.commons.lang3.mutable;
/*     */ 
/*     */ public class MutableShort extends Number
/*     */   implements Comparable<MutableShort>, Mutable<Number>
/*     */ {
/*     */   private static final long serialVersionUID = -2135791679L;
/*     */   private short value;
/*     */ 
/*     */   public MutableShort()
/*     */   {
/*     */   }
/*     */ 
/*     */   public MutableShort(short value)
/*     */   {
/*  54 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public MutableShort(Number value)
/*     */   {
/*  65 */     this.value = value.shortValue();
/*     */   }
/*     */ 
/*     */   public MutableShort(String value)
/*     */     throws NumberFormatException
/*     */   {
/*  77 */     this.value = Short.parseShort(value);
/*     */   }
/*     */ 
/*     */   public Short getValue()
/*     */   {
/*  87 */     return Short.valueOf(this.value);
/*     */   }
/*     */ 
/*     */   public void setValue(short value)
/*     */   {
/*  96 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public void setValue(Number value)
/*     */   {
/* 106 */     this.value = value.shortValue();
/*     */   }
/*     */ 
/*     */   public void increment()
/*     */   {
/* 116 */     this.value = ((short)(this.value + 1));
/*     */   }
/*     */ 
/*     */   public void decrement()
/*     */   {
/* 125 */     this.value = ((short)(this.value - 1));
/*     */   }
/*     */ 
/*     */   public void add(short operand)
/*     */   {
/* 136 */     this.value = ((short)(this.value + operand));
/*     */   }
/*     */ 
/*     */   public void add(Number operand)
/*     */   {
/* 147 */     this.value = ((short)(this.value + operand.shortValue()));
/*     */   }
/*     */ 
/*     */   public void subtract(short operand)
/*     */   {
/* 157 */     this.value = ((short)(this.value - operand));
/*     */   }
/*     */ 
/*     */   public void subtract(Number operand)
/*     */   {
/* 168 */     this.value = ((short)(this.value - operand.shortValue()));
/*     */   }
/*     */ 
/*     */   public short shortValue()
/*     */   {
/* 180 */     return this.value;
/*     */   }
/*     */ 
/*     */   public int intValue()
/*     */   {
/* 190 */     return this.value;
/*     */   }
/*     */ 
/*     */   public long longValue()
/*     */   {
/* 200 */     return this.value;
/*     */   }
/*     */ 
/*     */   public float floatValue()
/*     */   {
/* 210 */     return this.value;
/*     */   }
/*     */ 
/*     */   public double doubleValue()
/*     */   {
/* 220 */     return this.value;
/*     */   }
/*     */ 
/*     */   public Short toShort()
/*     */   {
/* 230 */     return Short.valueOf(shortValue());
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 244 */     if ((obj instanceof MutableShort)) {
/* 245 */       return this.value == ((MutableShort)obj).shortValue();
/*     */     }
/* 247 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 257 */     return this.value;
/*     */   }
/*     */ 
/*     */   public int compareTo(MutableShort other)
/*     */   {
/* 268 */     short anotherVal = other.value;
/* 269 */     return this.value == anotherVal ? 0 : this.value < anotherVal ? -1 : 1;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 280 */     return String.valueOf(this.value);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.mutable.MutableShort
 * JD-Core Version:    0.6.2
 */