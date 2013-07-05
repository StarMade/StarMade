/*     */ package org.apache.commons.lang3.mutable;
/*     */ 
/*     */ public class MutableInt extends Number
/*     */   implements Comparable<MutableInt>, Mutable<Number>
/*     */ {
/*     */   private static final long serialVersionUID = 512176391864L;
/*     */   private int value;
/*     */ 
/*     */   public MutableInt()
/*     */   {
/*     */   }
/*     */ 
/*     */   public MutableInt(int value)
/*     */   {
/*  54 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public MutableInt(Number value)
/*     */   {
/*  65 */     this.value = value.intValue();
/*     */   }
/*     */ 
/*     */   public MutableInt(String value)
/*     */     throws NumberFormatException
/*     */   {
/*  77 */     this.value = Integer.parseInt(value);
/*     */   }
/*     */ 
/*     */   public Integer getValue()
/*     */   {
/*  87 */     return Integer.valueOf(this.value);
/*     */   }
/*     */ 
/*     */   public void setValue(int value)
/*     */   {
/*  96 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public void setValue(Number value)
/*     */   {
/* 106 */     this.value = value.intValue();
/*     */   }
/*     */ 
/*     */   public void increment()
/*     */   {
/* 116 */     this.value += 1;
/*     */   }
/*     */ 
/*     */   public void decrement()
/*     */   {
/* 125 */     this.value -= 1;
/*     */   }
/*     */ 
/*     */   public void add(int operand)
/*     */   {
/* 136 */     this.value += operand;
/*     */   }
/*     */ 
/*     */   public void add(Number operand)
/*     */   {
/* 147 */     this.value += operand.intValue();
/*     */   }
/*     */ 
/*     */   public void subtract(int operand)
/*     */   {
/* 157 */     this.value -= operand;
/*     */   }
/*     */ 
/*     */   public void subtract(Number operand)
/*     */   {
/* 168 */     this.value -= operand.intValue();
/*     */   }
/*     */ 
/*     */   public int intValue()
/*     */   {
/* 180 */     return this.value;
/*     */   }
/*     */ 
/*     */   public long longValue()
/*     */   {
/* 190 */     return this.value;
/*     */   }
/*     */ 
/*     */   public float floatValue()
/*     */   {
/* 200 */     return this.value;
/*     */   }
/*     */ 
/*     */   public double doubleValue()
/*     */   {
/* 210 */     return this.value;
/*     */   }
/*     */ 
/*     */   public Integer toInteger()
/*     */   {
/* 220 */     return Integer.valueOf(intValue());
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 234 */     if ((obj instanceof MutableInt)) {
/* 235 */       return this.value == ((MutableInt)obj).intValue();
/*     */     }
/* 237 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 247 */     return this.value;
/*     */   }
/*     */ 
/*     */   public int compareTo(MutableInt other)
/*     */   {
/* 258 */     int anotherVal = other.value;
/* 259 */     return this.value == anotherVal ? 0 : this.value < anotherVal ? -1 : 1;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 270 */     return String.valueOf(this.value);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.mutable.MutableInt
 * JD-Core Version:    0.6.2
 */