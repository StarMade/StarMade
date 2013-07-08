/*  1:   */package org.newdawn.slick.state.transition;
/*  2:   */
/*  3:   */import org.newdawn.slick.Color;
/*  4:   */import org.newdawn.slick.GameContainer;
/*  5:   */import org.newdawn.slick.Graphics;
/*  6:   */import org.newdawn.slick.SlickException;
/*  7:   */import org.newdawn.slick.state.GameState;
/*  8:   */import org.newdawn.slick.state.StateBasedGame;
/*  9:   */
/* 20:   */public class RotateTransition
/* 21:   */  implements Transition
/* 22:   */{
/* 23:   */  private GameState prev;
/* 24:   */  private float ang;
/* 25:   */  private boolean finish;
/* 26:26 */  private float scale = 1.0F;
/* 27:   */  
/* 31:   */  private Color background;
/* 32:   */  
/* 36:   */  public RotateTransition() {}
/* 37:   */  
/* 41:   */  public RotateTransition(Color background)
/* 42:   */  {
/* 43:43 */    this.background = background;
/* 44:   */  }
/* 45:   */  
/* 48:   */  public void init(GameState firstState, GameState secondState)
/* 49:   */  {
/* 50:50 */    this.prev = secondState;
/* 51:   */  }
/* 52:   */  
/* 55:   */  public boolean isComplete()
/* 56:   */  {
/* 57:57 */    return this.finish;
/* 58:   */  }
/* 59:   */  
/* 61:   */  public void postRender(StateBasedGame game, GameContainer container, Graphics g)
/* 62:   */    throws SlickException
/* 63:   */  {
/* 64:64 */    g.translate(container.getWidth() / 2, container.getHeight() / 2);
/* 65:65 */    g.scale(this.scale, this.scale);
/* 66:66 */    g.rotate(0.0F, 0.0F, this.ang);
/* 67:67 */    g.translate(-container.getWidth() / 2, -container.getHeight() / 2);
/* 68:68 */    if (this.background != null) {
/* 69:69 */      Color c = g.getColor();
/* 70:70 */      g.setColor(this.background);
/* 71:71 */      g.fillRect(0.0F, 0.0F, container.getWidth(), container.getHeight());
/* 72:72 */      g.setColor(c);
/* 73:   */    }
/* 74:74 */    this.prev.render(container, game, g);
/* 75:75 */    g.translate(container.getWidth() / 2, container.getHeight() / 2);
/* 76:76 */    g.rotate(0.0F, 0.0F, -this.ang);
/* 77:77 */    g.scale(1.0F / this.scale, 1.0F / this.scale);
/* 78:78 */    g.translate(-container.getWidth() / 2, -container.getHeight() / 2);
/* 79:   */  }
/* 80:   */  
/* 84:   */  public void preRender(StateBasedGame game, GameContainer container, Graphics g)
/* 85:   */    throws SlickException
/* 86:   */  {}
/* 87:   */  
/* 90:   */  public void update(StateBasedGame game, GameContainer container, int delta)
/* 91:   */    throws SlickException
/* 92:   */  {
/* 93:93 */    this.ang += delta * 0.5F;
/* 94:94 */    if (this.ang > 500.0F) {
/* 95:95 */      this.finish = true;
/* 96:   */    }
/* 97:97 */    this.scale -= delta * 0.001F;
/* 98:98 */    if (this.scale < 0.0F) {
/* 99:99 */      this.scale = 0.0F;
/* 100:   */    }
/* 101:   */  }
/* 102:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.state.transition.RotateTransition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */