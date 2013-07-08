package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class FlashTest
  extends BasicGame
{
  private Image image;
  private boolean flash;
  private GameContainer container;
  
  public FlashTest()
  {
    super("Flash Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.container = container;
    this.image = new Image("testdata/logo.tga");
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    local_g.drawString("Press space to toggle", 10.0F, 50.0F);
    if (this.flash) {
      this.image.draw(100.0F, 100.0F);
    } else {
      this.image.drawFlash(100.0F, 100.0F, this.image.getWidth(), this.image.getHeight(), new Color(1.0F, 0.0F, 1.0F, 1.0F));
    }
  }
  
  public void update(GameContainer container, int delta) {}
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new FlashTest());
      container.setDisplayMode(800, 600, false);
      container.start();
    }
    catch (SlickException container)
    {
      container.printStackTrace();
    }
  }
  
  public void keyPressed(int key, char local_c)
  {
    if (key == 57) {
      this.flash = (!this.flash);
    }
    if (key == 1) {
      this.container.exit();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.FlashTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */