package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageMemTest
  extends BasicGame
{
  public ImageMemTest()
  {
    super("Image Memory Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    try
    {
      Image img = new Image(2400, 2400);
      img.getGraphics();
      img.destroy();
      img = new Image(2400, 2400);
      img.getGraphics();
    }
    catch (Exception img)
    {
      img.printStackTrace();
    }
  }
  
  public void render(GameContainer container, Graphics local_g) {}
  
  public void update(GameContainer container, int delta) {}
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new ImageMemTest());
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
 * Qualified Name:     org.newdawn.slick.tests.ImageMemTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */