/*    */ package org.newdawn.slick.tests.states;
/*    */ 
/*    */ import org.newdawn.slick.AngelCodeFont;
/*    */ import org.newdawn.slick.Color;
/*    */ import org.newdawn.slick.Font;
/*    */ import org.newdawn.slick.GameContainer;
/*    */ import org.newdawn.slick.Graphics;
/*    */ import org.newdawn.slick.SlickException;
/*    */ import org.newdawn.slick.state.BasicGameState;
/*    */ import org.newdawn.slick.state.StateBasedGame;
/*    */ import org.newdawn.slick.state.transition.FadeInTransition;
/*    */ import org.newdawn.slick.state.transition.FadeOutTransition;
/*    */ 
/*    */ public class TestState3 extends BasicGameState
/*    */ {
/*    */   public static final int ID = 3;
/*    */   private Font font;
/* 26 */   private String[] options = { "Start Game", "Credits", "Highscores", "Instructions", "Exit" };
/*    */   private int selected;
/*    */   private StateBasedGame game;
/*    */ 
/*    */   public int getID()
/*    */   {
/* 36 */     return 3;
/*    */   }
/*    */ 
/*    */   public void init(GameContainer container, StateBasedGame game)
/*    */     throws SlickException
/*    */   {
/* 43 */     this.font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
/* 44 */     this.game = game;
/*    */   }
/*    */ 
/*    */   public void render(GameContainer container, StateBasedGame game, Graphics g)
/*    */   {
/* 51 */     g.setFont(this.font);
/* 52 */     g.setColor(Color.blue);
/* 53 */     g.drawString("This is State 3", 200.0F, 50.0F);
/* 54 */     g.setColor(Color.white);
/*    */ 
/* 56 */     for (int i = 0; i < this.options.length; i++) {
/* 57 */       g.drawString(this.options[i], 400 - this.font.getWidth(this.options[i]) / 2, 200 + i * 50);
/* 58 */       if (this.selected == i)
/* 59 */         g.drawRect(200.0F, 190 + i * 50, 400.0F, 50.0F);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void update(GameContainer container, StateBasedGame game, int delta)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void keyReleased(int key, char c)
/*    */   {
/* 74 */     if (key == 208) {
/* 75 */       this.selected += 1;
/* 76 */       if (this.selected >= this.options.length) {
/* 77 */         this.selected = 0;
/*    */       }
/*    */     }
/* 80 */     if (key == 200) {
/* 81 */       this.selected -= 1;
/* 82 */       if (this.selected < 0) {
/* 83 */         this.selected = (this.options.length - 1);
/*    */       }
/*    */     }
/* 86 */     if (key == 2) {
/* 87 */       this.game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
/*    */     }
/* 89 */     if (key == 3)
/* 90 */       this.game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.states.TestState3
 * JD-Core Version:    0.6.2
 */