/*     */ package org.apache.commons.lang3.mutable;
/*     */ 
/*     */ public class MutableLong extends Number
/*     */   implements Comparable<MutableLong>, Mutable<Number>
/*     */ {
/*     */   private static final long serialVersionUID = 62986528375L;
/*     */   private long value;
/*     */ 
/*     */   public MutableLong()
/*     */   {
/*     */   }
/*     */ 
/*     */   public MutableLong(long value)
/*     */   {
/*  54 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public MutableLong(Number value)
/*     */   {
/*  65 */     this.value = value.longValue();
/*     */   }
/*     */ 
/*     */   public MutableLong(String value)
/*     */     throws NumberFormatException
/*     */   {
/*  77 */     this.value = Long.parseLong(value);
/*     */   }
/*     */ 
/*     */   public Long getValue()
/*     */   {
/*  87 */     return Long.valueOf(this.value);
/*     */   }
/*     */ 
/*     */   public void setValue(long value)
/*     */   {
/*  96 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public void setValue(Number value)
/*     */   {
/* 106 */     this.value = value.longValue();
/*     */   }
/*     */ 
/*     */   public void increment()
/*     */   {
/* 116 */     this.value += 1L;
/*     */   }
/*     */ 
/*     */   public void decrement()
/*     */   {
/* 125 */     this.value -= 1L;
/*     */   }
/*     */ 
/*     */   public void add(long operand)
/*     */   {
/* 136 */     this.value += operand;
/*     */   }
/*     */ 
/*     */   public void add(Number operand)
/*     */   {
/* 147 */     this.value += operand.longValue();
/*     */   }
/*     */ 
/*     */   public void subtract(long operand)
/*     */   {
/* 157 */     this.value -= operand;
/*     */   }
/*     */ 
/*     */   public void subtract(Number operand)
/*     */   {
/* 168 */     this.value -= operand.longValue();
/*     */   }
/*     */ 
/*     */   public int intValue()
/*     */   {
/* 180 */     return (int)this.value;
/*     */   }
/*     */ 
/*     */   public long longValue()
/*     */   {
/* 190 */     return this.value;
/*     */   }
/*     */ 
/*     */   public float floatValue()
/*     */   {
/* 200 */     return (float)this.value;
/*     */   }
/*     */ 
/*     */   public double doubleValue()
/*     */   {
/* 210 */     return this.value;
/*     */   }
/*     */ 
/*     */   public Long toLong()
/*     */   {
/* 220 */     return Long.valueOf(longValue());
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 234 */     if ((obj instanceof MutableLong)) {
/* 235 */       return this.value == ((MutableLong)obj).longValue();
/*     */     }
/* 237 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 247 */     return (int)(this.value ^ this.value >>> 32);
/*     */   }
/*     */ 
/*     */   public int compareTo(MutableLong other)
/*     */   {
/* 258 */     long anotherVal = other.value;
/* 259 */     return this.value == anotherVal ? 0 : this.value < anotherVal ? -1 : 1;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 270 */     return String.valueOf(this.value);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.mutable.MutableLong
 * JD-Core Version:    0.6.2
 */