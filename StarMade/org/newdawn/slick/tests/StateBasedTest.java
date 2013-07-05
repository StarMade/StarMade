/*    */ package org.newdawn.slick.tests;
/*    */ 
/*    */ import org.newdawn.slick.AppGameContainer;
/*    */ import org.newdawn.slick.GameContainer;
/*    */ import org.newdawn.slick.SlickException;
/*    */ import org.newdawn.slick.state.StateBasedGame;
/*    */ import org.newdawn.slick.tests.states.TestState1;
/*    */ import org.newdawn.slick.tests.states.TestState2;
/*    */ import org.newdawn.slick.tests.states.TestState3;
/*    */ 
/*    */ public class StateBasedTest extends StateBasedGame
/*    */ {
/*    */   public StateBasedTest()
/*    */   {
/* 22 */     super("State Based Test");
/*    */   }
/*    */ 
/*    */   public void initStatesList(GameContainer container)
/*    */   {
/* 29 */     addState(new TestState1());
/* 30 */     addState(new TestState2());
/* 31 */     addState(new TestState3());
/*    */   }
/*    */ 
/*    */   public static void main(String[] argv)
/*    */   {
/*    */     try
/*    */     {
/* 41 */       AppGameContainer container = new AppGameContainer(new StateBasedTest());
/* 42 */       container.setDisplayMode(800, 600, false);
/* 43 */       container.start();
/*    */     } catch (SlickException e) {
/* 45 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.StateBasedTest
 * JD-Core Version:    0.6.2
 */