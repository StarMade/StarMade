/*    */ package org.apache.commons.lang3.concurrent;
/*    */ 
/*    */ public class ConcurrentRuntimeException extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = -6582182735562919670L;
/*    */ 
/*    */   protected ConcurrentRuntimeException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ConcurrentRuntimeException(Throwable cause)
/*    */   {
/* 58 */     super(ConcurrentUtils.checkedException(cause));
/*    */   }
/*    */ 
/*    */   public ConcurrentRuntimeException(String msg, Throwable cause)
/*    */   {
/* 70 */     super(msg, ConcurrentUtils.checkedException(cause));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.ConcurrentRuntimeException
 * JD-Core Version:    0.6.2
 */