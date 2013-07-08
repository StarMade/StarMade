package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class CopyAreaAlphaTest
  extends BasicGame
{
  private Image textureMap;
  private Image copy;
  
  public CopyAreaAlphaTest()
  {
    super("CopyArea Alpha Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.textureMap = new Image("testdata/grass.png");
    container.getGraphics().setBackground(Color.black);
    this.copy = new Image(100, 100);
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {}
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    local_g.clearAlphaMap();
    local_g.setDrawMode(Graphics.MODE_NORMAL);
    local_g.setColor(Color.white);
    local_g.fillOval(100.0F, 100.0F, 150.0F, 150.0F);
    this.textureMap.draw(10.0F, 50.0F);
    local_g.copyArea(this.copy, 100, 100);
    local_g.setColor(Color.red);
    local_g.fillRect(300.0F, 100.0F, 200.0F, 200.0F);
    this.copy.draw(350.0F, 150.0F);
  }
  
  public void keyPressed(int key, char local_c) {}
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new CopyAreaAlphaTest());
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
 * Qualified Name:     org.newdawn.slick.tests.CopyAreaAlphaTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */