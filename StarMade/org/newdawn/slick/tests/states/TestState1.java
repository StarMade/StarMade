/*  1:   */package org.newdawn.slick.tests.states;
/*  2:   */
/*  3:   */import org.newdawn.slick.AngelCodeFont;
/*  4:   */import org.newdawn.slick.Color;
/*  5:   */import org.newdawn.slick.Font;
/*  6:   */import org.newdawn.slick.GameContainer;
/*  7:   */import org.newdawn.slick.Graphics;
/*  8:   */import org.newdawn.slick.SlickException;
/*  9:   */import org.newdawn.slick.state.BasicGameState;
/* 10:   */import org.newdawn.slick.state.GameState;
/* 11:   */import org.newdawn.slick.state.StateBasedGame;
/* 12:   */import org.newdawn.slick.state.transition.CrossStateTransition;
/* 13:   */import org.newdawn.slick.state.transition.EmptyTransition;
/* 14:   */import org.newdawn.slick.state.transition.FadeInTransition;
/* 15:   */import org.newdawn.slick.state.transition.FadeOutTransition;
/* 16:   */
/* 26:   */public class TestState1
/* 27:   */  extends BasicGameState
/* 28:   */{
/* 29:   */  public static final int ID = 1;
/* 30:   */  private Font font;
/* 31:   */  private StateBasedGame game;
/* 32:   */  
/* 33:   */  public int getID()
/* 34:   */  {
/* 35:35 */    return 1;
/* 36:   */  }
/* 37:   */  
/* 39:   */  public void init(GameContainer container, StateBasedGame game)
/* 40:   */    throws SlickException
/* 41:   */  {
/* 42:42 */    this.game = game;
/* 43:43 */    this.font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
/* 44:   */  }
/* 45:   */  
/* 48:   */  public void render(GameContainer container, StateBasedGame game, Graphics g)
/* 49:   */  {
/* 50:50 */    g.setFont(this.font);
/* 51:51 */    g.setColor(Color.white);
/* 52:52 */    g.drawString("State Based Game Test", 100.0F, 100.0F);
/* 53:53 */    g.drawString("Numbers 1-3 will switch between states.", 150.0F, 300.0F);
/* 54:54 */    g.setColor(Color.red);
/* 55:55 */    g.drawString("This is State 1", 200.0F, 50.0F);
/* 56:   */  }
/* 57:   */  
/* 62:   */  public void update(GameContainer container, StateBasedGame game, int delta) {}
/* 63:   */  
/* 67:   */  public void keyReleased(int key, char c)
/* 68:   */  {
/* 69:69 */    if (key == 3) {
/* 70:70 */      GameState target = this.game.getState(2);
/* 71:   */      
/* 72:72 */      final long start = System.currentTimeMillis();
/* 73:73 */      CrossStateTransition t = new CrossStateTransition(target) {
/* 74:   */        public boolean isComplete() {
/* 75:75 */          return System.currentTimeMillis() - start > 2000L;
/* 76:   */        }
/* 77:   */        
/* 80:   */        public void init(GameState firstState, GameState secondState) {}
/* 81:81 */      };
/* 82:82 */      this.game.enterState(2, t, new EmptyTransition());
/* 83:   */    }
/* 84:84 */    if (key == 4) {
/* 85:85 */      this.game.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
/* 86:   */    }
/* 87:   */  }
/* 88:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.states.TestState1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */