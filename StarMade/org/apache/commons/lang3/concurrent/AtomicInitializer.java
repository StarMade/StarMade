/*    */ package org.apache.commons.lang3.concurrent;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicReference;
/*    */ 
/*    */ public abstract class AtomicInitializer<T>
/*    */   implements ConcurrentInitializer<T>
/*    */ {
/* 69 */   private final AtomicReference<T> reference = new AtomicReference();
/*    */ 
/*    */   public T get()
/*    */     throws ConcurrentException
/*    */   {
/* 81 */     Object result = this.reference.get();
/*    */ 
/* 83 */     if (result == null) {
/* 84 */       result = initialize();
/* 85 */       if (!this.reference.compareAndSet(null, result))
/*    */       {
/* 87 */         result = this.reference.get();
/*    */       }
/*    */     }
/*    */ 
/* 91 */     return result;
/*    */   }
/*    */ 
/*    */   protected abstract T initialize()
/*    */     throws ConcurrentException;
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.AtomicInitializer
 * JD-Core Version:    0.6.2
 */