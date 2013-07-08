package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.CachedRender;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class CachedRenderTest
  extends BasicGame
{
  private Runnable operations;
  private CachedRender cached;
  private boolean drawCached;
  
  public CachedRenderTest()
  {
    super("Cached Render Test");
  }
  
  public void init(final GameContainer container)
    throws SlickException
  {
    this.operations = new Runnable()
    {
      public void run()
      {
        for (int local_i = 0; local_i < 100; local_i++)
        {
          int local_c = local_i + 100;
          container.getGraphics().setColor(new Color(local_c, local_c, local_c, local_c));
          container.getGraphics().drawOval(local_i * 5 + 50, local_i * 3 + 50, 100.0F, 100.0F);
        }
      }
    };
    this.cached = new CachedRender(this.operations);
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {
    if (container.getInput().isKeyPressed(57)) {
      this.drawCached = (!this.drawCached);
    }
  }
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    local_g.setColor(Color.white);
    local_g.drawString("Press space to toggle caching", 10.0F, 130.0F);
    if (this.drawCached)
    {
      local_g.drawString("Drawing from cache", 10.0F, 100.0F);
      this.cached.render();
    }
    else
    {
      local_g.drawString("Drawing direct", 10.0F, 100.0F);
      this.operations.run();
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new CachedRenderTest());
      container.setDisplayMode(800, 600, false);
      container.start();
    }
    catch (SlickException container)
    {
      container.printStackTrace();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.CachedRenderTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */