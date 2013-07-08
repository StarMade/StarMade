/*  1:   */package org.newdawn.slick.state.transition;
/*  2:   */
/*  3:   */import org.newdawn.slick.GameContainer;
/*  4:   */import org.newdawn.slick.Graphics;
/*  5:   */import org.newdawn.slick.SlickException;
/*  6:   */import org.newdawn.slick.state.GameState;
/*  7:   */import org.newdawn.slick.state.StateBasedGame;
/*  8:   */
/* 16:   */public class EmptyTransition
/* 17:   */  implements Transition
/* 18:   */{
/* 19:   */  public boolean isComplete()
/* 20:   */  {
/* 21:21 */    return true;
/* 22:   */  }
/* 23:   */  
/* 24:   */  public void postRender(StateBasedGame game, GameContainer container, Graphics g)
/* 25:   */    throws SlickException
/* 26:   */  {}
/* 27:   */  
/* 28:   */  public void preRender(StateBasedGame game, GameContainer container, Graphics g)
/* 29:   */    throws SlickException
/* 30:   */  {}
/* 31:   */  
/* 32:   */  public void update(StateBasedGame game, GameContainer container, int delta)
/* 33:   */    throws SlickException
/* 34:   */  {}
/* 35:   */  
/* 36:   */  public void init(GameState firstState, GameState secondState) {}
/* 37:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.state.transition.EmptyTransition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */