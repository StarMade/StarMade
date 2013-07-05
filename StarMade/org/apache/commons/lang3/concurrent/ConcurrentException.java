/*    */ package org.apache.commons.lang3.concurrent;
/*    */ 
/*    */ public class ConcurrentException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 6622707671812226130L;
/*    */ 
/*    */   protected ConcurrentException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ConcurrentException(Throwable cause)
/*    */   {
/* 56 */     super(ConcurrentUtils.checkedException(cause));
/*    */   }
/*    */ 
/*    */   public ConcurrentException(String msg, Throwable cause)
/*    */   {
/* 68 */     super(msg, ConcurrentUtils.checkedException(cause));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.ConcurrentException
 * JD-Core Version:    0.6.2
 */