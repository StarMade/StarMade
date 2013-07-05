/*    */ package org.schema.game.common.api.exceptions;
/*    */ 
/*    */ public class LoginFailedException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = -1347598156325355886L;
/*    */ 
/*    */   public LoginFailedException(int paramInt)
/*    */   {
/* 10 */     super("server response code: " + paramInt);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.api.exceptions.LoginFailedException
 * JD-Core Version:    0.6.2
 */