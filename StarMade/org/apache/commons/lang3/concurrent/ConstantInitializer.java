/*     */ package org.apache.commons.lang3.concurrent;
/*     */ 
/*     */ import org.apache.commons.lang3.ObjectUtils;
/*     */ 
/*     */ public class ConstantInitializer<T>
/*     */   implements ConcurrentInitializer<T>
/*     */ {
/*     */   private static final String FMT_TO_STRING = "ConstantInitializer@%d [ object = %s ]";
/*     */   private final T object;
/*     */ 
/*     */   public ConstantInitializer(T obj)
/*     */   {
/*  58 */     this.object = obj;
/*     */   }
/*     */ 
/*     */   public final T getObject()
/*     */   {
/*  69 */     return this.object;
/*     */   }
/*     */ 
/*     */   public T get()
/*     */     throws ConcurrentException
/*     */   {
/*  80 */     return getObject();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  91 */     return getObject() != null ? getObject().hashCode() : 0;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 105 */     if (this == obj) {
/* 106 */       return true;
/*     */     }
/* 108 */     if (!(obj instanceof ConstantInitializer)) {
/* 109 */       return false;
/*     */     }
/*     */ 
/* 112 */     ConstantInitializer c = (ConstantInitializer)obj;
/* 113 */     return ObjectUtils.equals(getObject(), c.getObject());
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 125 */     return String.format("ConstantInitializer@%d [ object = %s ]", new Object[] { Integer.valueOf(System.identityHashCode(this)), String.valueOf(getObject()) });
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.ConstantInitializer
 * JD-Core Version:    0.6.2
 */