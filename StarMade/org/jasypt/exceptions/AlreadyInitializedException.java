/*    */ package org.jasypt.exceptions;
/*    */ 
/*    */ public final class AlreadyInitializedException extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 4592515503937873874L;
/*    */ 
/*    */   public AlreadyInitializedException()
/*    */   {
/* 38 */     super("Encryption entity already initialized");
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.exceptions.AlreadyInitializedException
 * JD-Core Version:    0.6.2
 */