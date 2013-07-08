package org.newdawn.slick.state.transition;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class VerticalSplitTransition
  implements Transition
{
  protected static SGL field_312 = ;
  private GameState prev;
  private float offset;
  private boolean finish;
  private Color background;
  
  public VerticalSplitTransition() {}
  
  public VerticalSplitTransition(Color background)
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
    local_g.translate(0.0F, -this.offset);
    local_g.setClip(0, (int)-this.offset, container.getWidth(), container.getHeight() / 2);
    if (this.background != null)
    {
      Color local_c = local_g.getColor();
      local_g.setColor(this.background);
      local_g.fillRect(0.0F, 0.0F, container.getWidth(), container.getHeight());
      local_g.setColor(local_c);
    }
    field_312.glPushMatrix();
    this.prev.render(container, game, local_g);
    field_312.glPopMatrix();
    local_g.clearClip();
    local_g.resetTransform();
    local_g.translate(0.0F, this.offset);
    local_g.setClip(0, (int)(container.getHeight() / 2 + this.offset), container.getWidth(), container.getHeight() / 2);
    if (this.background != null)
    {
      Color local_c = local_g.getColor();
      local_g.setColor(this.background);
      local_g.fillRect(0.0F, 0.0F, container.getWidth(), container.getHeight());
      local_g.setColor(local_c);
    }
    field_312.glPushMatrix();
    this.prev.render(container, game, local_g);
    field_312.glPopMatrix();
    local_g.clearClip();
    local_g.translate(0.0F, -this.offset);
  }
  
  public void preRender(StateBasedGame game, GameContainer container, Graphics local_g)
    throws SlickException
  {}
  
  public void update(StateBasedGame game, GameContainer container, int delta)
    throws SlickException
  {
    this.offset += delta * 1.0F;
    if (this.offset > container.getHeight() / 2) {
      this.finish = true;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.state.transition.VerticalSplitTransition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */