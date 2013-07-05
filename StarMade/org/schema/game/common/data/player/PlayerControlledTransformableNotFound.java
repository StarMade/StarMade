/*    */ package org.schema.game.common.data.player;
/*    */ 
/*    */ import lE;
/*    */ 
/*    */ public class PlayerControlledTransformableNotFound extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 5554862394827461271L;
/*    */ 
/*    */   public PlayerControlledTransformableNotFound(lE paramlE)
/*    */   {
/* 11 */     super("[ERROR] no transformable for  " + paramlE);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.player.PlayerControlledTransformableNotFound
 * JD-Core Version:    0.6.2
 */