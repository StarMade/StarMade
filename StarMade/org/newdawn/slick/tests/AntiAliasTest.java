package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class AntiAliasTest
  extends BasicGame
{
  public AntiAliasTest()
  {
    super("AntiAlias Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    container.getGraphics().setBackground(Color.green);
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {}
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    local_g.setAntiAlias(true);
    local_g.setColor(Color.red);
    local_g.drawOval(100.0F, 100.0F, 100.0F, 100.0F);
    local_g.fillOval(300.0F, 100.0F, 100.0F, 100.0F);
    local_g.setAntiAlias(false);
    local_g.setColor(Color.red);
    local_g.drawOval(100.0F, 300.0F, 100.0F, 100.0F);
    local_g.fillOval(300.0F, 300.0F, 100.0F, 100.0F);
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new AntiAliasTest());
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
 * Qualified Name:     org.newdawn.slick.tests.AntiAliasTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */