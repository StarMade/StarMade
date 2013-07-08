/*  1:   */package org.newdawn.slick.state.transition;
/*  2:   */
/*  3:   */import org.newdawn.slick.GameContainer;
/*  4:   */import org.newdawn.slick.Graphics;
/*  5:   */import org.newdawn.slick.SlickException;
/*  6:   */import org.newdawn.slick.state.GameState;
/*  7:   */import org.newdawn.slick.state.StateBasedGame;
/*  8:   */
/* 32:   */public abstract class CrossStateTransition
/* 33:   */  implements Transition
/* 34:   */{
/* 35:   */  private GameState secondState;
/* 36:   */  
/* 37:   */  public CrossStateTransition(GameState secondState)
/* 38:   */  {
/* 39:39 */    this.secondState = secondState;
/* 40:   */  }
/* 41:   */  
/* 44:   */  public abstract boolean isComplete();
/* 45:   */  
/* 48:   */  public void postRender(StateBasedGame game, GameContainer container, Graphics g)
/* 49:   */    throws SlickException
/* 50:   */  {
/* 51:51 */    preRenderSecondState(game, container, g);
/* 52:52 */    this.secondState.render(container, game, g);
/* 53:53 */    postRenderSecondState(game, container, g);
/* 54:   */  }
/* 55:   */  
/* 57:   */  public void preRender(StateBasedGame game, GameContainer container, Graphics g)
/* 58:   */    throws SlickException
/* 59:   */  {
/* 60:60 */    preRenderFirstState(game, container, g);
/* 61:   */  }
/* 62:   */  
/* 63:   */  public void update(StateBasedGame game, GameContainer container, int delta)
/* 64:   */    throws SlickException
/* 65:   */  {}
/* 66:   */  
/* 67:   */  public void preRenderFirstState(StateBasedGame game, GameContainer container, Graphics g)
/* 68:   */    throws SlickException
/* 69:   */  {}
/* 70:   */  
/* 71:   */  public void preRenderSecondState(StateBasedGame game, GameContainer container, Graphics g)
/* 72:   */    throws SlickException
/* 73:   */  {}
/* 74:   */  
/* 75:   */  public void postRenderSecondState(StateBasedGame game, GameContainer container, Graphics g)
/* 76:   */    throws SlickException
/* 77:   */  {}
/* 78:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.state.transition.CrossStateTransition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */