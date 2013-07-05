/*    */ package org.newdawn.slick.tests;
/*    */ 
/*    */ import org.newdawn.slick.AppGameContainer;
/*    */ import org.newdawn.slick.BasicGame;
/*    */ import org.newdawn.slick.GameContainer;
/*    */ import org.newdawn.slick.Graphics;
/*    */ import org.newdawn.slick.SlickException;
/*    */ 
/*    */ public class DoubleClickTest extends BasicGame
/*    */ {
/* 24 */   private String message = "Click or Double Click";
/*    */ 
/*    */   public DoubleClickTest()
/*    */   {
/* 20 */     super("Double Click Test");
/*    */   }
/*    */ 
/*    */   public void init(GameContainer container)
/*    */     throws SlickException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void update(GameContainer container, int delta)
/*    */     throws SlickException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void render(GameContainer container, Graphics g)
/*    */     throws SlickException
/*    */   {
/* 42 */     g.drawString(this.message, 100.0F, 100.0F);
/*    */   }
/*    */ 
/*    */   public static void main(String[] argv)
/*    */   {
/*    */     try
/*    */     {
/* 52 */       AppGameContainer container = new AppGameContainer(new DoubleClickTest());
/* 53 */       container.setDisplayMode(800, 600, false);
/* 54 */       container.start();
/*    */     } catch (SlickException e) {
/* 56 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void mouseClicked(int button, int x, int y, int clickCount)
/*    */   {
/* 64 */     if (clickCount == 1) {
/* 65 */       this.message = ("Single Click: " + button + " " + x + "," + y);
/*    */     }
/* 67 */     if (clickCount == 2)
/* 68 */       this.message = ("Double Click: " + button + " " + x + "," + y);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.DoubleClickTest
 * JD-Core Version:    0.6.2
 */