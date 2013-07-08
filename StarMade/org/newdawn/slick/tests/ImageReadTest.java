package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class ImageReadTest
  extends BasicGame
{
  private Image image;
  private Color[] read = new Color[6];
  private Graphics field_86;
  
  public ImageReadTest()
  {
    super("Image Read Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.image = new Image("testdata/testcard.png");
    this.read[0] = this.image.getColor(0, 0);
    this.read[1] = this.image.getColor(30, 40);
    this.read[2] = this.image.getColor(55, 70);
    this.read[3] = this.image.getColor(80, 90);
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    this.field_86 = local_g;
    this.image.draw(100.0F, 100.0F);
    local_g.setColor(Color.white);
    local_g.drawString("Move mouse over test image", 200.0F, 20.0F);
    local_g.setColor(this.read[0]);
    local_g.drawString(this.read[0].toString(), 100.0F, 300.0F);
    local_g.setColor(this.read[1]);
    local_g.drawString(this.read[1].toString(), 150.0F, 320.0F);
    local_g.setColor(this.read[2]);
    local_g.drawString(this.read[2].toString(), 200.0F, 340.0F);
    local_g.setColor(this.read[3]);
    local_g.drawString(this.read[3].toString(), 250.0F, 360.0F);
    if (this.read[4] != null)
    {
      local_g.setColor(this.read[4]);
      local_g.drawString("On image: " + this.read[4].toString(), 100.0F, 250.0F);
    }
    if (this.read[5] != null)
    {
      local_g.setColor(Color.white);
      local_g.drawString("On screen: " + this.read[5].toString(), 100.0F, 270.0F);
    }
  }
  
  public void update(GameContainer container, int delta)
  {
    int local_mx = container.getInput().getMouseX();
    int local_my = container.getInput().getMouseY();
    if ((local_mx >= 100) && (local_my >= 100) && (local_mx < 200) && (local_my < 200)) {
      this.read[4] = this.image.getColor(local_mx - 100, local_my - 100);
    } else {
      this.read[4] = Color.black;
    }
    this.read[5] = this.field_86.getPixel(local_mx, local_my);
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new ImageReadTest());
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
 * Qualified Name:     org.newdawn.slick.tests.ImageReadTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */