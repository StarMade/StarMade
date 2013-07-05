/*    */ package org.apache.commons.lang3.concurrent;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicReference;
/*    */ 
/*    */ public abstract class AtomicSafeInitializer<T>
/*    */   implements ConcurrentInitializer<T>
/*    */ {
/* 59 */   private final AtomicReference<AtomicSafeInitializer<T>> factory = new AtomicReference();
/*    */ 
/* 63 */   private final AtomicReference<T> reference = new AtomicReference();
/*    */ 
/*    */   public final T get()
/*    */     throws ConcurrentException
/*    */   {
/*    */     Object result;
/* 75 */     while ((result = this.reference.get()) == null) {
/* 76 */       if (this.factory.compareAndSet(null, this)) {
/* 77 */         this.reference.set(initialize());
/*    */       }
/*    */     }
/*    */ 
/* 81 */     return result;
/*    */   }
/*    */ 
/*    */   protected abstract T initialize()
/*    */     throws ConcurrentException;
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.AtomicSafeInitializer
 * JD-Core Version:    0.6.2
 */