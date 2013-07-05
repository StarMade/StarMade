/*    */ package org.schema.schine.network;
/*    */ 
/*    */ public class LoginFailedException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 9172243621536784232L;
/*    */   private final int errorCode;
/*    */ 
/*    */   public LoginFailedException(int paramInt)
/*    */   {
/* 12 */     this.errorCode = paramInt;
/*    */   }
/*    */ 
/*    */   public int getErrorCode()
/*    */   {
/* 19 */     return this.errorCode;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.LoginFailedException
 * JD-Core Version:    0.6.2
 */