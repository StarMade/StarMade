package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.BigImage;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class BigImageTest
  extends BasicGame
{
  private Image original;
  private Image image;
  private Image imageX;
  private Image imageY;
  private Image sub;
  private Image scaledSub;
  private float field_54;
  private float field_318;
  private float ang = 30.0F;
  private SpriteSheet bigSheet;
  
  public BigImageTest()
  {
    super("Big Image Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.original = (this.image = new BigImage("testdata/bigimage.tga", 2, 512));
    this.sub = this.image.getSubImage(210, 210, 200, 130);
    this.scaledSub = this.sub.getScaledCopy(2.0F);
    this.image = this.image.getScaledCopy(0.3F);
    this.imageX = this.image.getFlippedCopy(true, false);
    this.imageY = this.imageX.getFlippedCopy(true, true);
    this.bigSheet = new SpriteSheet(this.original, 16, 16);
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    this.original.draw(0.0F, 0.0F, new Color(1.0F, 1.0F, 1.0F, 0.4F));
    this.image.draw(this.field_54, this.field_318);
    this.imageX.draw(this.field_54 + 400.0F, this.field_318);
    this.imageY.draw(this.field_54, this.field_318 + 300.0F);
    this.scaledSub.draw(this.field_54 + 300.0F, this.field_318 + 300.0F);
    this.bigSheet.getSprite(7, 5).draw(50.0F, 10.0F);
    local_g.setColor(Color.white);
    local_g.drawRect(50.0F, 10.0F, 64.0F, 64.0F);
    local_g.rotate(this.field_54 + 400.0F, this.field_318 + 165.0F, this.ang);
    local_g.drawImage(this.sub, this.field_54 + 300.0F, this.field_318 + 100.0F);
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new BigImageTest());
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
    this.ang += delta * 0.1F;
    if (container.getInput().isKeyDown(203)) {
      this.field_54 -= delta * 0.1F;
    }
    if (container.getInput().isKeyDown(205)) {
      this.field_54 += delta * 0.1F;
    }
    if (container.getInput().isKeyDown(200)) {
      this.field_318 -= delta * 0.1F;
    }
    if (container.getInput().isKeyDown(208)) {
      this.field_318 += delta * 0.1F;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.BigImageTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */