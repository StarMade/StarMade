/*     */ package org.apache.commons.lang3.mutable;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class MutableObject<T>
/*     */   implements Mutable<T>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 86241875189L;
/*     */   private T value;
/*     */ 
/*     */   public MutableObject()
/*     */   {
/*     */   }
/*     */ 
/*     */   public MutableObject(T value)
/*     */   {
/*  54 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public T getValue()
/*     */   {
/*  64 */     return this.value;
/*     */   }
/*     */ 
/*     */   public void setValue(T value)
/*     */   {
/*  73 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/*  91 */     if (obj == null) {
/*  92 */       return false;
/*     */     }
/*  94 */     if (this == obj) {
/*  95 */       return true;
/*     */     }
/*  97 */     if (getClass() == obj.getClass()) {
/*  98 */       MutableObject that = (MutableObject)obj;
/*  99 */       return this.value.equals(that.value);
/*     */     }
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 112 */     return this.value == null ? 0 : this.value.hashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 123 */     return this.value == null ? "null" : this.value.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.mutable.MutableObject
 * JD-Core Version:    0.6.2
 */