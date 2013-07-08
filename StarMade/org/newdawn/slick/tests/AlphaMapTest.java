package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AlphaMapTest
  extends BasicGame
{
  private Image alphaMap;
  private Image textureMap;
  
  public AlphaMapTest()
  {
    super("AlphaMap Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.alphaMap = new Image("testdata/alphamap.png");
    this.textureMap = new Image("testdata/grass.png");
    container.getGraphics().setBackground(Color.black);
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {}
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    local_g.clearAlphaMap();
    local_g.setDrawMode(Graphics.MODE_NORMAL);
    this.textureMap.draw(10.0F, 50.0F);
    local_g.setColor(Color.red);
    local_g.fillRect(290.0F, 40.0F, 200.0F, 200.0F);
    local_g.setColor(Color.white);
    local_g.setDrawMode(Graphics.MODE_ALPHA_MAP);
    this.alphaMap.draw(300.0F, 50.0F);
    local_g.setDrawMode(Graphics.MODE_ALPHA_BLEND);
    this.textureMap.draw(300.0F, 50.0F);
    local_g.setDrawMode(Graphics.MODE_NORMAL);
  }
  
  public void keyPressed(int key, char local_c) {}
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new AlphaMapTest());
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
 * Qualified Name:     org.newdawn.slick.tests.AlphaMapTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */