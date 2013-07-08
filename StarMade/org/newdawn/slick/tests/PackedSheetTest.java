package org.newdawn.slick.tests;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.PackedSpriteSheet;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class PackedSheetTest
  extends BasicGame
{
  private PackedSpriteSheet sheet;
  private GameContainer container;
  private float field_360 = -500.0F;
  private Image rocket;
  private Animation runner;
  private float ang;
  
  public PackedSheetTest()
  {
    super("Packed Sprite Sheet Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.container = container;
    this.sheet = new PackedSpriteSheet("testdata/testpack.def", 2);
    this.rocket = this.sheet.getSprite("rocket");
    SpriteSheet anim = this.sheet.getSpriteSheet("runner");
    this.runner = new Animation();
    for (int local_y = 0; local_y < 2; local_y++) {
      for (int local_x = 0; local_x < 6; local_x++) {
        this.runner.addFrame(anim.getSprite(local_x, local_y), 50);
      }
    }
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    this.rocket.draw((int)this.field_360, 100.0F);
    this.runner.draw(250.0F, 250.0F);
    local_g.scale(1.2F, 1.2F);
    this.runner.draw(250.0F, 250.0F);
    local_g.scale(1.2F, 1.2F);
    this.runner.draw(250.0F, 250.0F);
    local_g.resetTransform();
    local_g.rotate(670.0F, 470.0F, this.ang);
    this.sheet.getSprite("floppy").draw(600.0F, 400.0F);
  }
  
  public void update(GameContainer container, int delta)
  {
    this.field_360 += delta * 0.4F;
    if (this.field_360 > 900.0F) {
      this.field_360 = -500.0F;
    }
    this.ang += delta * 0.1F;
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new PackedSheetTest());
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
    if (key == 1) {
      this.container.exit();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.PackedSheetTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */