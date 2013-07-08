package org.newdawn.slick.state.transition;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.MaskUtil;

public class BlobbyTransition
  implements Transition
{
  protected static SGL field_312 = ;
  private GameState prev;
  private boolean finish;
  private Color background;
  private ArrayList blobs = new ArrayList();
  private int timer = 1000;
  private int blobCount = 10;
  
  public BlobbyTransition() {}
  
  public BlobbyTransition(Color background)
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
  {}
  
  public void preRender(StateBasedGame game, GameContainer container, Graphics local_g)
    throws SlickException
  {
    this.prev.render(container, game, local_g);
    MaskUtil.defineMask();
    for (int local_i = 0; local_i < this.blobs.size(); local_i++) {
      ((Blob)this.blobs.get(local_i)).render(local_g);
    }
    MaskUtil.finishDefineMask();
    MaskUtil.drawOnMask();
    if (this.background != null)
    {
      Color local_i = local_g.getColor();
      local_g.setColor(this.background);
      local_g.fillRect(0.0F, 0.0F, container.getWidth(), container.getHeight());
      local_g.setColor(local_i);
    }
  }
  
  public void update(StateBasedGame game, GameContainer container, int delta)
    throws SlickException
  {
    if (this.blobs.size() == 0) {
      for (int local_i = 0; local_i < this.blobCount; local_i++) {
        this.blobs.add(new Blob(container));
      }
    }
    for (int local_i = 0; local_i < this.blobs.size(); local_i++) {
      ((Blob)this.blobs.get(local_i)).update(delta);
    }
    this.timer -= delta;
    if (this.timer < 0) {
      this.finish = true;
    }
  }
  
  private class Blob
  {
    private float field_656;
    private float field_657;
    private float growSpeed;
    private float rad;
    
    public Blob(GameContainer container)
    {
      this.field_656 = ((float)(Math.random() * container.getWidth()));
      this.field_657 = ((float)(Math.random() * container.getWidth()));
      this.growSpeed = ((float)(1.0D + Math.random() * 1.0D));
    }
    
    public void update(int delta)
    {
      this.rad += this.growSpeed * delta * 0.4F;
    }
    
    public void render(Graphics local_g)
    {
      local_g.fillOval(this.field_656 - this.rad, this.field_657 - this.rad, this.rad * 2.0F, this.rad * 2.0F);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.state.transition.BlobbyTransition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */