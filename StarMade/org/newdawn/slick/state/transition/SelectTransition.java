package org.newdawn.slick.state.transition;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class SelectTransition
  implements Transition
{
  protected static SGL field_312 = ;
  private GameState prev;
  private boolean finish;
  private Color background;
  private float scale1 = 1.0F;
  private float xp1 = 0.0F;
  private float yp1 = 0.0F;
  private float scale2 = 0.4F;
  private float xp2 = 0.0F;
  private float yp2 = 0.0F;
  private boolean init = false;
  private boolean moveBackDone = false;
  private int pause = 300;
  
  public SelectTransition() {}
  
  public SelectTransition(Color background)
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
    local_g.resetTransform();
    if (!this.moveBackDone)
    {
      local_g.translate(this.xp1, this.yp1);
      local_g.scale(this.scale1, this.scale1);
      local_g.setClip((int)this.xp1, (int)this.yp1, (int)(this.scale1 * container.getWidth()), (int)(this.scale1 * container.getHeight()));
      this.prev.render(container, game, local_g);
      local_g.resetTransform();
      local_g.clearClip();
    }
  }
  
  public void preRender(StateBasedGame game, GameContainer container, Graphics local_g)
    throws SlickException
  {
    if (this.moveBackDone)
    {
      local_g.translate(this.xp1, this.yp1);
      local_g.scale(this.scale1, this.scale1);
      local_g.setClip((int)this.xp1, (int)this.yp1, (int)(this.scale1 * container.getWidth()), (int)(this.scale1 * container.getHeight()));
      this.prev.render(container, game, local_g);
      local_g.resetTransform();
      local_g.clearClip();
    }
    local_g.translate(this.xp2, this.yp2);
    local_g.scale(this.scale2, this.scale2);
    local_g.setClip((int)this.xp2, (int)this.yp2, (int)(this.scale2 * container.getWidth()), (int)(this.scale2 * container.getHeight()));
  }
  
  public void update(StateBasedGame game, GameContainer container, int delta)
    throws SlickException
  {
    if (!this.init)
    {
      this.init = true;
      this.xp2 = (container.getWidth() / 2 + 50);
      this.yp2 = (container.getHeight() / 4);
    }
    if (!this.moveBackDone)
    {
      if (this.scale1 > 0.4F)
      {
        this.scale1 -= delta * 0.002F;
        if (this.scale1 <= 0.4F) {
          this.scale1 = 0.4F;
        }
        this.xp1 += delta * 0.3F;
        if (this.xp1 > 50.0F) {
          this.xp1 = 50.0F;
        }
        this.yp1 += delta * 0.5F;
        if (this.yp1 > container.getHeight() / 4) {
          this.yp1 = (container.getHeight() / 4);
        }
      }
      else
      {
        this.moveBackDone = true;
      }
    }
    else
    {
      this.pause -= delta;
      if (this.pause > 0) {
        return;
      }
      if (this.scale2 < 1.0F)
      {
        this.scale2 += delta * 0.002F;
        if (this.scale2 >= 1.0F) {
          this.scale2 = 1.0F;
        }
        this.xp2 -= delta * 1.5F;
        if (this.xp2 < 0.0F) {
          this.xp2 = 0.0F;
        }
        this.yp2 -= delta * 0.5F;
        if (this.yp2 < 0.0F) {
          this.yp2 = 0.0F;
        }
      }
      else
      {
        this.finish = true;
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.state.transition.SelectTransition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */