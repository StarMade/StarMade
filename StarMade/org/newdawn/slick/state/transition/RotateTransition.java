package org.newdawn.slick.state.transition;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class RotateTransition
  implements Transition
{
  private GameState prev;
  private float ang;
  private boolean finish;
  private float scale = 1.0F;
  private Color background;
  
  public RotateTransition() {}
  
  public RotateTransition(Color background)
  {
    this.background = background;
  }
  
  public void init(GameState firstState, GameState secondState)
  {
    this.prev = secondState;
  }
  
  public boolean isComplete()
  {
    return this.finish;
  }
  
  public void postRender(StateBasedGame game, GameContainer container, Graphics local_g)
    throws SlickException
  {
    local_g.translate(container.getWidth() / 2, container.getHeight() / 2);
    local_g.scale(this.scale, this.scale);
    local_g.rotate(0.0F, 0.0F, this.ang);
    local_g.translate(-container.getWidth() / 2, -container.getHeight() / 2);
    if (this.background != null)
    {
      Color local_c = local_g.getColor();
      local_g.setColor(this.background);
      local_g.fillRect(0.0F, 0.0F, container.getWidth(), container.getHeight());
      local_g.setColor(local_c);
    }
    this.prev.render(container, game, local_g);
    local_g.translate(container.getWidth() / 2, container.getHeight() / 2);
    local_g.rotate(0.0F, 0.0F, -this.ang);
    local_g.scale(1.0F / this.scale, 1.0F / this.scale);
    local_g.translate(-container.getWidth() / 2, -container.getHeight() / 2);
  }
  
  public void preRender(StateBasedGame game, GameContainer container, Graphics local_g)
    throws SlickException
  {}
  
  public void update(StateBasedGame game, GameContainer container, int delta)
    throws SlickException
  {
    this.ang += delta * 0.5F;
    if (this.ang > 500.0F) {
      this.finish = true;
    }
    this.scale -= delta * 0.001F;
    if (this.scale < 0.0F) {
      this.scale = 0.0F;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.state.transition.RotateTransition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */