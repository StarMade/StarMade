package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.BigImage;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class BigSpriteSheetTest
  extends BasicGame
{
  private Image original;
  private SpriteSheet bigSheet;
  private boolean oldMethod = true;
  
  public BigSpriteSheetTest()
  {
    super("Big SpriteSheet Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.original = new BigImage("testdata/bigimage.tga", 2, 256);
    this.bigSheet = new SpriteSheet(this.original, 16, 16);
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    if (this.oldMethod)
    {
      for (int local_x = 0; local_x < 43; local_x++) {
        for (int local_y = 0; local_y < 27; local_y++) {
          this.bigSheet.getSprite(local_x, local_y).draw(10 + local_x * 18, 50 + local_y * 18);
        }
      }
    }
    else
    {
      this.bigSheet.startUse();
      for (int local_x = 0; local_x < 43; local_x++) {
        for (int local_y = 0; local_y < 27; local_y++) {
          this.bigSheet.renderInUse(10 + local_x * 18, 50 + local_y * 18, local_x, local_y);
        }
      }
      this.bigSheet.endUse();
    }
    local_g.drawString("Press space to toggle rendering method", 10.0F, 30.0F);
    container.getDefaultFont().drawString(10.0F, 100.0F, "TEST");
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new BigSpriteSheetTest());
      container.setDisplayMode(800, 600, false);
      container.start();
    }
    catch (SlickException container)
    {
      container.printStackTrace();
    }
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {
    if (container.getInput().isKeyPressed(57)) {
      this.oldMethod = (!this.oldMethod);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.BigSpriteSheetTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */