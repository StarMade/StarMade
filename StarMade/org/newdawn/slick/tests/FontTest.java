package org.newdawn.slick.tests;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

public class FontTest
  extends BasicGame
{
  private AngelCodeFont font;
  private AngelCodeFont font2;
  private Image image;
  private static AppGameContainer container;
  
  public FontTest()
  {
    super("Font Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
    this.font2 = new AngelCodeFont("testdata/hiero.fnt", "testdata/hiero.png");
    this.image = new Image("testdata/demo2_00.tga", false);
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    this.font.drawString(80.0F, 5.0F, "A Font Example", Color.red);
    this.font.drawString(100.0F, 32.0F, "We - AV - Here is a more complete line that hopefully");
    this.font.drawString(100.0F, 36 + this.font.getHeight("We Here is a more complete line that hopefully"), "will show some kerning.");
    this.font2.drawString(80.0F, 85.0F, "A Font Example", Color.red);
    this.font2.drawString(100.0F, 132.0F, "We - AV - Here is a more complete line that hopefully");
    this.font2.drawString(100.0F, 136 + this.font2.getHeight("We - Here is a more complete line that hopefully"), "will show some kerning.");
    this.image.draw(100.0F, 400.0F);
    String testStr = "Testing Font";
    this.font2.drawString(100.0F, 300.0F, testStr);
    local_g.setColor(Color.white);
    local_g.drawRect(100.0F, 300 + this.font2.getYOffset(testStr), this.font2.getWidth(testStr), this.font2.getHeight(testStr) - this.font2.getYOffset(testStr));
    this.font.drawString(500.0F, 300.0F, testStr);
    local_g.setColor(Color.white);
    local_g.drawRect(500.0F, 300 + this.font.getYOffset(testStr), this.font.getWidth(testStr), this.font.getHeight(testStr) - this.font.getYOffset(testStr));
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {}
  
  public void keyPressed(int key, char local_c)
  {
    if (key == 1) {
      System.exit(0);
    }
    if (key == 57) {
      try
      {
        container.setDisplayMode(640, 480, false);
      }
      catch (SlickException local_e)
      {
        Log.error(local_e);
      }
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      container = new AppGameContainer(new FontTest());
      container.setDisplayMode(800, 600, false);
      container.start();
    }
    catch (SlickException local_e)
    {
      local_e.printStackTrace();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.FontTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */