/*    */ package org.schema.game.server.data;
/*    */ 
/*    */ public class PlayerNotFountException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = -4501401334942597976L;
/*    */ 
/*    */   public PlayerNotFountException(String paramString)
/*    */   {
/* 12 */     super("Player not found: \"" + paramString + "\"");
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.server.data.PlayerNotFountException
 * JD-Core Version:    0.6.2
 */