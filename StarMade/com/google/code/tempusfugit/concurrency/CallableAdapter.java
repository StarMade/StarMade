/*    */ package com.google.code.tempusfugit.concurrency;
/*    */ 
/*    */ import java.util.concurrent.Callable;
/*    */ 
/*    */ public class CallableAdapter
/*    */ {
/*    */   public static Runnable runnable(Callable callable)
/*    */   {
/* 22 */     return new Runnable() {
/*    */       public void run() {
/*    */         try {
/* 25 */           this.val$callable.call();
/*    */         } catch (Exception e) {
/* 27 */           throw new RuntimeException(e);
/*    */         }
/*    */       }
/*    */     };
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.CallableAdapter
 * JD-Core Version:    0.6.2
 */