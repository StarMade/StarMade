package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class DoubleClickTest
  extends BasicGame
{
  private String message = "Click or Double Click";
  
  public DoubleClickTest()
  {
    super("Double Click Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {}
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {}
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    local_g.drawString(this.message, 100.0F, 100.0F);
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new DoubleClickTest());
      container.setDisplayMode(800, 600, false);
      container.start();
    }
    catch (SlickException container)
    {
      container.printStackTrace();
    }
  }
  
  public void mouseClicked(int button, int local_x, int local_y, int clickCount)
  {
    if (clickCount == 1) {
      this.message = ("Single Click: " + button + " " + local_x + "," + local_y);
    }
    if (clickCount == 2) {
      this.message = ("Double Click: " + button + " " + local_x + "," + local_y);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.DoubleClickTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */