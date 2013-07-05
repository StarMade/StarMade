/*    */ package org.jasypt.exceptions;
/*    */ 
/*    */ public final class EncryptionInitializationException extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 8929638240023639778L;
/*    */ 
/*    */   public EncryptionInitializationException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public EncryptionInitializationException(Throwable t)
/*    */   {
/* 40 */     super(t);
/*    */   }
/*    */ 
/*    */   public EncryptionInitializationException(String msg, Throwable t) {
/* 44 */     super(msg, t);
/*    */   }
/*    */ 
/*    */   public EncryptionInitializationException(String msg) {
/* 48 */     super(msg);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.exceptions.EncryptionInitializationException
 * JD-Core Version:    0.6.2
 */