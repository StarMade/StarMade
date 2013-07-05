/*     */ package org.apache.commons.lang3.concurrent;
/*     */ 
/*     */ public abstract class LazyInitializer<T>
/*     */   implements ConcurrentInitializer<T>
/*     */ {
/*     */   private volatile T object;
/*     */ 
/*     */   public T get()
/*     */     throws ConcurrentException
/*     */   {
/*  94 */     Object result = this.object;
/*     */ 
/*  96 */     if (result == null) {
/*  97 */       synchronized (this) {
/*  98 */         result = this.object;
/*  99 */         if (result == null) {
/* 100 */           this.object = (result = initialize());
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 105 */     return result;
/*     */   }
/*     */ 
/*     */   protected abstract T initialize()
/*     */     throws ConcurrentException;
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.LazyInitializer
 * JD-Core Version:    0.6.2
 */