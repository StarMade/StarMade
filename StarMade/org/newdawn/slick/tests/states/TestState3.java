/*  1:   */package org.newdawn.slick.tests.states;
/*  2:   */
/*  3:   */import org.newdawn.slick.AngelCodeFont;
/*  4:   */import org.newdawn.slick.Color;
/*  5:   */import org.newdawn.slick.Font;
/*  6:   */import org.newdawn.slick.GameContainer;
/*  7:   */import org.newdawn.slick.Graphics;
/*  8:   */import org.newdawn.slick.SlickException;
/*  9:   */import org.newdawn.slick.state.BasicGameState;
/* 10:   */import org.newdawn.slick.state.StateBasedGame;
/* 11:   */import org.newdawn.slick.state.transition.FadeInTransition;
/* 12:   */import org.newdawn.slick.state.transition.FadeOutTransition;
/* 13:   */
/* 21:   */public class TestState3
/* 22:   */  extends BasicGameState
/* 23:   */{
/* 24:   */  public static final int ID = 3;
/* 25:   */  private Font font;
/* 26:26 */  private String[] options = { "Start Game", "Credits", "Highscores", "Instructions", "Exit" };
/* 27:   */  
/* 29:   */  private int selected;
/* 30:   */  
/* 31:   */  private StateBasedGame game;
/* 32:   */  
/* 34:   */  public int getID()
/* 35:   */  {
/* 36:36 */    return 3;
/* 37:   */  }
/* 38:   */  
/* 40:   */  public void init(GameContainer container, StateBasedGame game)
/* 41:   */    throws SlickException
/* 42:   */  {
/* 43:43 */    this.font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
/* 44:44 */    this.game = game;
/* 45:   */  }
/* 46:   */  
/* 49:   */  public void render(GameContainer container, StateBasedGame game, Graphics g)
/* 50:   */  {
/* 51:51 */    g.setFont(this.font);
/* 52:52 */    g.setColor(Color.blue);
/* 53:53 */    g.drawString("This is State 3", 200.0F, 50.0F);
/* 54:54 */    g.setColor(Color.white);
/* 55:   */    
/* 56:56 */    for (int i = 0; i < this.options.length; i++) {
/* 57:57 */      g.drawString(this.options[i], 400 - this.font.getWidth(this.options[i]) / 2, 200 + i * 50);
/* 58:58 */      if (this.selected == i) {
/* 59:59 */        g.drawRect(200.0F, 190 + i * 50, 400.0F, 50.0F);
/* 60:   */      }
/* 61:   */    }
/* 62:   */  }
/* 63:   */  
/* 67:   */  public void update(GameContainer container, StateBasedGame game, int delta) {}
/* 68:   */  
/* 72:   */  public void keyReleased(int key, char c)
/* 73:   */  {
/* 74:74 */    if (key == 208) {
/* 75:75 */      this.selected += 1;
/* 76:76 */      if (this.selected >= this.options.length) {
/* 77:77 */        this.selected = 0;
/* 78:   */      }
/* 79:   */    }
/* 80:80 */    if (key == 200) {
/* 81:81 */      this.selected -= 1;
/* 82:82 */      if (this.selected < 0) {
/* 83:83 */        this.selected = (this.options.length - 1);
/* 84:   */      }
/* 85:   */    }
/* 86:86 */    if (key == 2) {
/* 87:87 */      this.game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
/* 88:   */    }
/* 89:89 */    if (key == 3) {
/* 90:90 */      this.game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
/* 91:   */    }
/* 92:   */  }
/* 93:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.states.TestState3
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */