/*     */ package org.apache.commons.lang3.mutable;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class MutableBoolean
/*     */   implements Mutable<Boolean>, Serializable, Comparable<MutableBoolean>
/*     */ {
/*     */   private static final long serialVersionUID = -4830728138360036487L;
/*     */   private boolean value;
/*     */ 
/*     */   public MutableBoolean()
/*     */   {
/*     */   }
/*     */ 
/*     */   public MutableBoolean(boolean value)
/*     */   {
/*  57 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public MutableBoolean(Boolean value)
/*     */   {
/*  68 */     this.value = value.booleanValue();
/*     */   }
/*     */ 
/*     */   public Boolean getValue()
/*     */   {
/*  78 */     return Boolean.valueOf(this.value);
/*     */   }
/*     */ 
/*     */   public void setValue(boolean value)
/*     */   {
/*  87 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public void setValue(Boolean value)
/*     */   {
/*  97 */     this.value = value.booleanValue();
/*     */   }
/*     */ 
/*     */   public boolean isTrue()
/*     */   {
/* 108 */     return this.value == true;
/*     */   }
/*     */ 
/*     */   public boolean isFalse()
/*     */   {
/* 118 */     return !this.value;
/*     */   }
/*     */ 
/*     */   public boolean booleanValue()
/*     */   {
/* 128 */     return this.value;
/*     */   }
/*     */ 
/*     */   public Boolean toBoolean()
/*     */   {
/* 139 */     return Boolean.valueOf(booleanValue());
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 153 */     if ((obj instanceof MutableBoolean)) {
/* 154 */       return this.value == ((MutableBoolean)obj).booleanValue();
/*     */     }
/* 156 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 166 */     return this.value ? Boolean.TRUE.hashCode() : Boolean.FALSE.hashCode();
/*     */   }
/*     */ 
/*     */   public int compareTo(MutableBoolean other)
/*     */   {
/* 178 */     boolean anotherVal = other.value;
/* 179 */     return this.value ? 1 : this.value == anotherVal ? 0 : -1;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 190 */     return String.valueOf(this.value);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.mutable.MutableBoolean
 * JD-Core Version:    0.6.2
 */