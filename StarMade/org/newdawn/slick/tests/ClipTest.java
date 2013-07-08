package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class ClipTest
  extends BasicGame
{
  private float ang = 0.0F;
  private boolean world;
  private boolean clip;
  
  public ClipTest()
  {
    super("Clip Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {}
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {
    this.ang += delta * 0.01F;
  }
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    local_g.setColor(Color.white);
    local_g.drawString("1 - No Clipping", 100.0F, 10.0F);
    local_g.drawString("2 - Screen Clipping", 100.0F, 30.0F);
    local_g.drawString("3 - World Clipping", 100.0F, 50.0F);
    if (this.world) {
      local_g.drawString("WORLD CLIPPING ENABLED", 200.0F, 80.0F);
    }
    if (this.clip) {
      local_g.drawString("SCREEN CLIPPING ENABLED", 200.0F, 80.0F);
    }
    local_g.rotate(400.0F, 400.0F, this.ang);
    if (this.world) {
      local_g.setWorldClip(350.0F, 302.0F, 100.0F, 196.0F);
    }
    if (this.clip) {
      local_g.setClip(350, 302, 100, 196);
    }
    local_g.setColor(Color.red);
    local_g.fillOval(300.0F, 300.0F, 200.0F, 200.0F);
    local_g.setColor(Color.blue);
    local_g.fillRect(390.0F, 200.0F, 20.0F, 400.0F);
    local_g.clearClip();
    local_g.clearWorldClip();
  }
  
  public void keyPressed(int key, char local_c)
  {
    if (key == 2)
    {
      this.world = false;
      this.clip = false;
    }
    if (key == 3)
    {
      this.world = false;
      this.clip = true;
    }
    if (key == 4)
    {
      this.world = true;
      this.clip = false;
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new ClipTest());
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
 * Qualified Name:     org.newdawn.slick.tests.ClipTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */