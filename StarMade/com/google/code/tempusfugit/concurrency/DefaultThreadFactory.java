/*    */ package com.google.code.tempusfugit.concurrency;
/*    */ 
/*    */ import java.util.concurrent.ThreadFactory;
/*    */ 
/*    */ public class DefaultThreadFactory
/*    */   implements ThreadFactory
/*    */ {
/*    */   public Thread newThread(Runnable runnable)
/*    */   {
/* 24 */     return new Thread(runnable);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.DefaultThreadFactory
 * JD-Core Version:    0.6.2
 */