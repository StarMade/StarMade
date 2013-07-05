/*    */ package org.apache.commons.logging;
/*    */ 
/*    */ public class LogConfigurationException extends RuntimeException
/*    */ {
/* 85 */   protected Throwable cause = null;
/*    */ 
/*    */   public LogConfigurationException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public LogConfigurationException(String message)
/*    */   {
/* 50 */     super(message);
/*    */   }
/*    */ 
/*    */   public LogConfigurationException(Throwable cause)
/*    */   {
/* 63 */     this(cause == null ? null : cause.toString(), cause);
/*    */   }
/*    */ 
/*    */   public LogConfigurationException(String message, Throwable cause)
/*    */   {
/* 76 */     super(message + " (Caused by " + cause + ")");
/* 77 */     this.cause = cause;
/*    */   }
/*    */ 
/*    */   public Throwable getCause()
/*    */   {
/* 93 */     return this.cause;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.LogConfigurationException
 * JD-Core Version:    0.6.2
 */