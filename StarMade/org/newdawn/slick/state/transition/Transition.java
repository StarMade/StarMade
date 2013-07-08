package org.newdawn.slick.state.transition;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract interface Transition
{
  public abstract void update(StateBasedGame paramStateBasedGame, GameContainer paramGameContainer, int paramInt)
    throws SlickException;
  
  public abstract void preRender(StateBasedGame paramStateBasedGame, GameContainer paramGameContainer, Graphics paramGraphics)
    throws SlickException;
  
  public abstract void postRender(StateBasedGame paramStateBasedGame, GameContainer paramGameContainer, Graphics paramGraphics)
    throws SlickException;
  
  public abstract boolean isComplete();
  
  public abstract void init(GameState paramGameState1, GameState paramGameState2);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.state.transition.Transition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */