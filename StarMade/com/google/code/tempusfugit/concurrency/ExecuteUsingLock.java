/*    */ package com.google.code.tempusfugit.concurrency;
/*    */ 
/*    */ import java.util.concurrent.locks.Lock;
/*    */ 
/*    */ public class ExecuteUsingLock<T, E extends Exception>
/*    */ {
/*    */   private final Callable<T, E> callable;
/*    */ 
/*    */   private ExecuteUsingLock(Callable<T, E> callable)
/*    */   {
/* 26 */     this.callable = callable;
/*    */   }
/*    */ 
/*    */   public static <T, E extends Exception> ExecuteUsingLock<T, E> execute(Callable<T, E> callable) {
/* 30 */     return new ExecuteUsingLock(callable);
/*    */   }
/*    */ 
/*    */   public T using(Lock lock) throws Exception {
/*    */     try {
/* 35 */       lock.lock();
/* 36 */       return this.callable.call();
/*    */     } finally {
/* 38 */       lock.unlock();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.ExecuteUsingLock
 * JD-Core Version:    0.6.2
 */