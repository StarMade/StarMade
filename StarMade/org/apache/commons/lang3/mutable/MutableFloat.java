/*     */ package org.apache.commons.lang3.mutable;
/*     */ 
/*     */ public class MutableFloat extends Number
/*     */   implements Comparable<MutableFloat>, Mutable<Number>
/*     */ {
/*     */   private static final long serialVersionUID = 5787169186L;
/*     */   private float value;
/*     */ 
/*     */   public MutableFloat()
/*     */   {
/*     */   }
/*     */ 
/*     */   public MutableFloat(float value)
/*     */   {
/*  54 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public MutableFloat(Number value)
/*     */   {
/*  65 */     this.value = value.floatValue();
/*     */   }
/*     */ 
/*     */   public MutableFloat(String value)
/*     */     throws NumberFormatException
/*     */   {
/*  77 */     this.value = Float.parseFloat(value);
/*     */   }
/*     */ 
/*     */   public Float getValue()
/*     */   {
/*  87 */     return Float.valueOf(this.value);
/*     */   }
/*     */ 
/*     */   public void setValue(float value)
/*     */   {
/*  96 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public void setValue(Number value)
/*     */   {
/* 106 */     this.value = value.floatValue();
/*     */   }
/*     */ 
/*     */   public boolean isNaN()
/*     */   {
/* 116 */     return Float.isNaN(this.value);
/*     */   }
/*     */ 
/*     */   public boolean isInfinite()
/*     */   {
/* 125 */     return Float.isInfinite(this.value);
/*     */   }
/*     */ 
/*     */   public void increment()
/*     */   {
/* 135 */     this.value += 1.0F;
/*     */   }
/*     */ 
/*     */   public void decrement()
/*     */   {
/* 144 */     this.value -= 1.0F;
/*     */   }
/*     */ 
/*     */   public void add(float operand)
/*     */   {
/* 155 */     this.value += operand;
/*     */   }
/*     */ 
/*     */   public void add(Number operand)
/*     */   {
/* 166 */     this.value += operand.floatValue();
/*     */   }
/*     */ 
/*     */   public void subtract(float operand)
/*     */   {
/* 176 */     this.value -= operand;
/*     */   }
/*     */ 
/*     */   public void subtract(Number operand)
/*     */   {
/* 187 */     this.value -= operand.floatValue();
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
/* 219 */     return this.value;
/*     */   }
/*     */ 
/*     */   public double doubleValue()
/*     */   {
/* 229 */     return this.value;
/*     */   }
/*     */ 
/*     */   public Float toFloat()
/*     */   {
/* 239 */     return Float.valueOf(floatValue());
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 276 */     return ((obj instanceof MutableFloat)) && (Float.floatToIntBits(((MutableFloat)obj).value) == Float.floatToIntBits(this.value));
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 287 */     return Float.floatToIntBits(this.value);
/*     */   }
/*     */ 
/*     */   public int compareTo(MutableFloat other)
/*     */   {
/* 298 */     float anotherVal = other.value;
/* 299 */     return Float.compare(this.value, anotherVal);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 310 */     return String.valueOf(this.value);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.mutable.MutableFloat
 * JD-Core Version:    0.6.2
 */