/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.GameContainer;
/*  5:   */import org.newdawn.slick.SlickException;
/*  6:   */import org.newdawn.slick.state.StateBasedGame;
/*  7:   */import org.newdawn.slick.tests.states.TestState1;
/*  8:   */import org.newdawn.slick.tests.states.TestState2;
/*  9:   */import org.newdawn.slick.tests.states.TestState3;
/* 10:   */
/* 17:   */public class StateBasedTest
/* 18:   */  extends StateBasedGame
/* 19:   */{
/* 20:   */  public StateBasedTest()
/* 21:   */  {
/* 22:22 */    super("State Based Test");
/* 23:   */  }
/* 24:   */  
/* 27:   */  public void initStatesList(GameContainer container)
/* 28:   */  {
/* 29:29 */    addState(new TestState1());
/* 30:30 */    addState(new TestState2());
/* 31:31 */    addState(new TestState3());
/* 32:   */  }
/* 33:   */  
/* 37:   */  public static void main(String[] argv)
/* 38:   */  {
/* 39:   */    try
/* 40:   */    {
/* 41:41 */      AppGameContainer container = new AppGameContainer(new StateBasedTest());
/* 42:42 */      container.setDisplayMode(800, 600, false);
/* 43:43 */      container.start();
/* 44:   */    } catch (SlickException e) {
/* 45:45 */      e.printStackTrace();
/* 46:   */    }
/* 47:   */  }
/* 48:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.StateBasedTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */