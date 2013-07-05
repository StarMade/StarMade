/*     */ package org.apache.commons.lang3.mutable;
/*     */ 
/*     */ public class MutableDouble extends Number
/*     */   implements Comparable<MutableDouble>, Mutable<Number>
/*     */ {
/*     */   private static final long serialVersionUID = 1587163916L;
/*     */   private double value;
/*     */ 
/*     */   public MutableDouble()
/*     */   {
/*     */   }
/*     */ 
/*     */   public MutableDouble(double value)
/*     */   {
/*  54 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public MutableDouble(Number value)
/*     */   {
/*  65 */     this.value = value.doubleValue();
/*     */   }
/*     */ 
/*     */   public MutableDouble(String value)
/*     */     throws NumberFormatException
/*     */   {
/*  77 */     this.value = Double.parseDouble(value);
/*     */   }
/*     */ 
/*     */   public Double getValue()
/*     */   {
/*  87 */     return Double.valueOf(this.value);
/*     */   }
/*     */ 
/*     */   public void setValue(double value)
/*     */   {
/*  96 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public void setValue(Number value)
/*     */   {
/* 106 */     this.value = value.doubleValue();
/*     */   }
/*     */ 
/*     */   public boolean isNaN()
/*     */   {
/* 116 */     return Double.isNaN(this.value);
/*     */   }
/*     */ 
/*     */   public boolean isInfinite()
/*     */   {
/* 125 */     return Double.isInfinite(this.value);
/*     */   }
/*     */ 
/*     */   public void increment()
/*     */   {
/* 135 */     this.value += 1.0D;
/*     */   }
/*     */ 
/*     */   public void decrement()
/*     */   {
/* 144 */     this.value -= 1.0D;
/*     */   }
/*     */ 
/*     */   public void add(double operand)
/*     */   {
/* 155 */     this.value += operand;
/*     */   }
/*     */ 
/*     */   public void add(Number operand)
/*     */   {
/* 166 */     this.value += operand.doubleValue();
/*     */   }
/*     */ 
/*     */   public void subtract(double operand)
/*     */   {
/* 176 */     this.value -= operand;
/*     */   }
/*     */ 
/*     */   public void subtract(Number operand)
/*     */   {
/* 187 */     this.value -= operand.doubleValue();
/*     */   }
/*     */ 
/*     */   public int intValue()
/*     */   {
/* 199 */     return (int)this.value;
/*     */   }
/*     */ 
/*     */   public long longValue()
/*     */   {
/* 209 */     return ()this.value;
/*     */   }
/*     */ 
/*     */   public float floatValue()
/*     */   {
/* 219 */     return (float)this.value;
/*     */   }
/*     */ 
/*     */   public double doubleValue()
/*     */   {
/* 229 */     return this.value;
/*     */   }
/*     */ 
/*     */   public Double toDouble()
/*     */   {
/* 239 */     return Double.valueOf(doubleValue());
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 274 */     return ((obj instanceof MutableDouble)) && (Double.doubleToLongBits(((MutableDouble)obj).value) == Double.doubleToLongBits(this.value));
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 285 */     long bits = Double.doubleToLongBits(this.value);
/* 286 */     return (int)(bits ^ bits >>> 32);
/*     */   }
/*     */ 
/*     */   public int compareTo(MutableDouble other)
/*     */   {
/* 297 */     double anotherVal = other.value;
/* 298 */     return Double.compare(this.value, anotherVal);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 309 */     return String.valueOf(this.value);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.mutable.MutableDouble
 * JD-Core Version:    0.6.2
 */