package org.newdawn.slick.state.transition;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class CombinedTransition
  implements Transition
{
  private ArrayList transitions = new ArrayList();
  
  public void addTransition(Transition local_t)
  {
    this.transitions.add(local_t);
  }
  
  public boolean isComplete()
  {
    for (int local_i = 0; local_i < this.transitions.size(); local_i++) {
      if (!((Transition)this.transitions.get(local_i)).isComplete()) {
        return false;
      }
    }
    return true;
  }
  
  public void postRender(StateBasedGame game, GameContainer container, Graphics local_g)
    throws SlickException
  {
    for (int local_i = this.transitions.size() - 1; local_i >= 0; local_i--) {
      ((Transition)this.transitions.get(local_i)).postRender(game, container, local_g);
    }
  }
  
  public void preRender(StateBasedGame game, GameContainer container, Graphics local_g)
    throws SlickException
  {
    for (int local_i = 0; local_i < this.transitions.size(); local_i++) {
      ((Transition)this.transitions.get(local_i)).postRender(game, container, local_g);
    }
  }
  
  public void update(StateBasedGame game, GameContainer container, int delta)
    throws SlickException
  {
    for (int local_i = 0; local_i < this.transitions.size(); local_i++)
    {
      Transition local_t = (Transition)this.transitions.get(local_i);
      if (!local_t.isComplete()) {
        local_t.update(game, container, delta);
      }
    }
  }
  
  public void init(GameState firstState, GameState secondState)
  {
    for (int local_i = this.transitions.size() - 1; local_i >= 0; local_i--) {
      ((Transition)this.transitions.get(local_i)).init(firstState, secondState);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.state.transition.CombinedTransition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */