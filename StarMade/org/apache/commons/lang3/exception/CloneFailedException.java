/*    */ package org.apache.commons.lang3.exception;
/*    */ 
/*    */ public class CloneFailedException extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 20091223L;
/*    */ 
/*    */   public CloneFailedException(String message)
/*    */   {
/* 39 */     super(message);
/*    */   }
/*    */ 
/*    */   public CloneFailedException(Throwable cause)
/*    */   {
/* 49 */     super(cause);
/*    */   }
/*    */ 
/*    */   public CloneFailedException(String message, Throwable cause)
/*    */   {
/* 60 */     super(message, cause);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.exception.CloneFailedException
 * JD-Core Version:    0.6.2
 */