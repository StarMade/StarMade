/*    */ package org.newdawn.slick.util;
/*    */ 
/*    */ import org.newdawn.slick.AppGameContainer;
/*    */ import org.newdawn.slick.Game;
/*    */ 
/*    */ public class Bootstrap
/*    */ {
/*    */   public static void runAsApplication(Game game, int width, int height, boolean fullscreen)
/*    */   {
/*    */     try
/*    */     {
/* 23 */       AppGameContainer container = new AppGameContainer(game, width, height, fullscreen);
/* 24 */       container.start();
/*    */     } catch (Exception e) {
/* 26 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.Bootstrap
 * JD-Core Version:    0.6.2
 */