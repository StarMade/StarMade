/*  1:   */package org.newdawn.slick.tests.states;
/*  2:   */
/*  3:   */import org.newdawn.slick.AngelCodeFont;
/*  4:   */import org.newdawn.slick.Color;
/*  5:   */import org.newdawn.slick.Font;
/*  6:   */import org.newdawn.slick.GameContainer;
/*  7:   */import org.newdawn.slick.Graphics;
/*  8:   */import org.newdawn.slick.Image;
/*  9:   */import org.newdawn.slick.SlickException;
/* 10:   */import org.newdawn.slick.state.BasicGameState;
/* 11:   */import org.newdawn.slick.state.StateBasedGame;
/* 12:   */import org.newdawn.slick.state.transition.FadeInTransition;
/* 13:   */import org.newdawn.slick.state.transition.FadeOutTransition;
/* 14:   */
/* 26:   */public class TestState2
/* 27:   */  extends BasicGameState
/* 28:   */{
/* 29:   */  public static final int ID = 2;
/* 30:   */  private Font font;
/* 31:   */  private Image image;
/* 32:   */  private float ang;
/* 33:   */  private StateBasedGame game;
/* 34:   */  
/* 35:   */  public int getID()
/* 36:   */  {
/* 37:37 */    return 2;
/* 38:   */  }
/* 39:   */  
/* 41:   */  public void init(GameContainer container, StateBasedGame game)
/* 42:   */    throws SlickException
/* 43:   */  {
/* 44:44 */    this.game = game;
/* 45:45 */    this.font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
/* 46:46 */    this.image = new Image("testdata/logo.tga");
/* 47:   */  }
/* 48:   */  
/* 51:   */  public void render(GameContainer container, StateBasedGame game, Graphics g)
/* 52:   */  {
/* 53:53 */    g.setFont(this.font);
/* 54:54 */    g.setColor(Color.green);
/* 55:55 */    g.drawString("This is State 2", 200.0F, 50.0F);
/* 56:   */    
/* 57:57 */    g.rotate(400.0F, 300.0F, this.ang);
/* 58:58 */    g.drawImage(this.image, 400 - this.image.getWidth() / 2, 300 - this.image.getHeight() / 2);
/* 59:   */  }
/* 60:   */  
/* 63:   */  public void update(GameContainer container, StateBasedGame game, int delta)
/* 64:   */  {
/* 65:65 */    this.ang += delta * 0.1F;
/* 66:   */  }
/* 67:   */  
/* 70:   */  public void keyReleased(int key, char c)
/* 71:   */  {
/* 72:72 */    if (key == 2) {
/* 73:73 */      this.game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
/* 74:   */    }
/* 75:75 */    if (key == 4) {
/* 76:76 */      this.game.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
/* 77:   */    }
/* 78:   */  }
/* 79:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.states.TestState2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */