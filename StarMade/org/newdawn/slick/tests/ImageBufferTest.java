package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.SlickException;

public class ImageBufferTest
  extends BasicGame
{
  private Image image;
  
  public ImageBufferTest()
  {
    super("Image Buffer Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    ImageBuffer buffer = new ImageBuffer(320, 200);
    for (int local_x = 0; local_x < 320; local_x++) {
      for (int local_y = 0; local_y < 200; local_y++) {
        if (local_y == 20) {
          buffer.setRGBA(local_x, local_y, 255, 255, 255, 255);
        } else {
          buffer.setRGBA(local_x, local_y, local_x, local_y, 0, 255);
        }
      }
    }
    this.image = buffer.getImage();
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    this.image.draw(50.0F, 50.0F);
  }
  
  public void update(GameContainer container, int delta) {}
  
  public void keyPressed(int key, char local_c)
  {
    if (key == 1) {
      System.exit(0);
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new ImageBufferTest());
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
 * Qualified Name:     org.newdawn.slick.tests.ImageBufferTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */