/*     */ package org.apache.commons.lang3.mutable;
/*     */ 
/*     */ public class MutableByte extends Number
/*     */   implements Comparable<MutableByte>, Mutable<Number>
/*     */ {
/*     */   private static final long serialVersionUID = -1585823265L;
/*     */   private byte value;
/*     */ 
/*     */   public MutableByte()
/*     */   {
/*     */   }
/*     */ 
/*     */   public MutableByte(byte value)
/*     */   {
/*  54 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public MutableByte(Number value)
/*     */   {
/*  65 */     this.value = value.byteValue();
/*     */   }
/*     */ 
/*     */   public MutableByte(String value)
/*     */     throws NumberFormatException
/*     */   {
/*  77 */     this.value = Byte.parseByte(value);
/*     */   }
/*     */ 
/*     */   public Byte getValue()
/*     */   {
/*  87 */     return Byte.valueOf(this.value);
/*     */   }
/*     */ 
/*     */   public void setValue(byte value)
/*     */   {
/*  96 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public void setValue(Number value)
/*     */   {
/* 106 */     this.value = value.byteValue();
/*     */   }
/*     */ 
/*     */   public void increment()
/*     */   {
/* 116 */     this.value = ((byte)(this.value + 1));
/*     */   }
/*     */ 
/*     */   public void decrement()
/*     */   {
/* 125 */     this.value = ((byte)(this.value - 1));
/*     */   }
/*     */ 
/*     */   public void add(byte operand)
/*     */   {
/* 136 */     this.value = ((byte)(this.value + operand));
/*     */   }
/*     */ 
/*     */   public void add(Number operand)
/*     */   {
/* 147 */     this.value = ((byte)(this.value + operand.byteValue()));
/*     */   }
/*     */ 
/*     */   public void subtract(byte operand)
/*     */   {
/* 157 */     this.value = ((byte)(this.value - operand));
/*     */   }
/*     */ 
/*     */   public void subtract(Number operand)
/*     */   {
/* 168 */     this.value = ((byte)(this.value - operand.byteValue()));
/*     */   }
/*     */ 
/*     */   public byte byteValue()
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
/*     */   public Byte toByte()
/*     */   {
/* 230 */     return Byte.valueOf(byteValue());
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 244 */     if ((obj instanceof MutableByte)) {
/* 245 */       return this.value == ((MutableByte)obj).byteValue();
/*     */     }
/* 247 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 257 */     return this.value;
/*     */   }
/*     */ 
/*     */   public int compareTo(MutableByte other)
/*     */   {
/* 268 */     byte anotherVal = other.value;
/* 269 */     return this.value == anotherVal ? 0 : this.value < anotherVal ? -1 : 1;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 280 */     return String.valueOf(this.value);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.mutable.MutableByte
 * JD-Core Version:    0.6.2
 */