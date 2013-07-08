/*  1:   */package org.newdawn.slick.state.transition;
/*  2:   */
/*  3:   */import org.newdawn.slick.Color;
/*  4:   */import org.newdawn.slick.GameContainer;
/*  5:   */import org.newdawn.slick.Graphics;
/*  6:   */import org.newdawn.slick.state.GameState;
/*  7:   */import org.newdawn.slick.state.StateBasedGame;
/*  8:   */
/* 16:   */public class FadeOutTransition
/* 17:   */  implements Transition
/* 18:   */{
/* 19:   */  private Color color;
/* 20:   */  private int fadeTime;
/* 21:   */  
/* 22:   */  public FadeOutTransition()
/* 23:   */  {
/* 24:24 */    this(Color.black, 500);
/* 25:   */  }
/* 26:   */  
/* 31:   */  public FadeOutTransition(Color color)
/* 32:   */  {
/* 33:33 */    this(color, 500);
/* 34:   */  }
/* 35:   */  
/* 41:   */  public FadeOutTransition(Color color, int fadeTime)
/* 42:   */  {
/* 43:43 */    this.color = new Color(color);
/* 44:44 */    this.color.a = 0.0F;
/* 45:45 */    this.fadeTime = fadeTime;
/* 46:   */  }
/* 47:   */  
/* 50:   */  public boolean isComplete()
/* 51:   */  {
/* 52:52 */    return this.color.a >= 1.0F;
/* 53:   */  }
/* 54:   */  
/* 57:   */  public void postRender(StateBasedGame game, GameContainer container, Graphics g)
/* 58:   */  {
/* 59:59 */    Color old = g.getColor();
/* 60:60 */    g.setColor(this.color);
/* 61:61 */    g.fillRect(0.0F, 0.0F, container.getWidth() * 2, container.getHeight() * 2);
/* 62:62 */    g.setColor(old);
/* 63:   */  }
/* 64:   */  
/* 67:   */  public void update(StateBasedGame game, GameContainer container, int delta)
/* 68:   */  {
/* 69:69 */    this.color.a += delta * (1.0F / this.fadeTime);
/* 70:70 */    if (this.color.a > 1.0F) {
/* 71:71 */      this.color.a = 1.0F;
/* 72:   */    }
/* 73:   */  }
/* 74:   */  
/* 75:   */  public void preRender(StateBasedGame game, GameContainer container, Graphics g) {}
/* 76:   */  
/* 77:   */  public void init(GameState firstState, GameState secondState) {}
/* 78:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.state.transition.FadeOutTransition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */