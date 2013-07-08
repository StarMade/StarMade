package org.newdawn.slick.state.transition;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class CrossStateTransition
  implements Transition
{
  private GameState secondState;
  
  public CrossStateTransition(GameState secondState)
  {
    this.secondState = secondState;
  }
  
  public abstract boolean isComplete();
  
  public void postRender(StateBasedGame game, GameContainer container, Graphics local_g)
    throws SlickException
  {
    preRenderSecondState(game, container, local_g);
    this.secondState.render(container, game, local_g);
    postRenderSecondState(game, container, local_g);
  }
  
  public void preRender(StateBasedGame game, GameContainer container, Graphics local_g)
    throws SlickException
  {
    preRenderFirstState(game, container, local_g);
  }
  
  public void update(StateBasedGame game, GameContainer container, int delta)
    throws SlickException
  {}
  
  public void preRenderFirstState(StateBasedGame game, GameContainer container, Graphics local_g)
    throws SlickException
  {}
  
  public void preRenderSecondState(StateBasedGame game, GameContainer container, Graphics local_g)
    throws SlickException
  {}
  
  public void postRenderSecondState(StateBasedGame game, GameContainer container, Graphics local_g)
    throws SlickException
  {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.state.transition.CrossStateTransition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */