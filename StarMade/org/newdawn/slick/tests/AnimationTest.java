package org.newdawn.slick.tests;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class AnimationTest
  extends BasicGame
{
  private Animation animation;
  private Animation limited;
  private Animation manual;
  private Animation pingPong;
  private GameContainer container;
  private int start = 5000;
  
  public AnimationTest()
  {
    super("Animation Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.container = container;
    SpriteSheet sheet = new SpriteSheet("testdata/homeranim.png", 36, 65);
    this.animation = new Animation();
    for (int local_i = 0; local_i < 8; local_i++) {
      this.animation.addFrame(sheet.getSprite(local_i, 0), 150);
    }
    this.limited = new Animation();
    for (int local_i = 0; local_i < 8; local_i++) {
      this.limited.addFrame(sheet.getSprite(local_i, 0), 150);
    }
    this.limited.stopAt(7);
    this.manual = new Animation(false);
    for (int local_i = 0; local_i < 8; local_i++) {
      this.manual.addFrame(sheet.getSprite(local_i, 0), 150);
    }
    this.pingPong = new Animation(sheet, 0, 0, 7, 0, true, 150, true);
    this.pingPong.setPingPong(true);
    container.getGraphics().setBackground(new Color(0.4F, 0.6F, 0.6F));
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    local_g.drawString("Space to restart() animation", 100.0F, 50.0F);
    local_g.drawString("Til Limited animation: " + this.start, 100.0F, 500.0F);
    local_g.drawString("Hold 1 to move the manually animated", 100.0F, 70.0F);
    local_g.drawString("PingPong Frame:" + this.pingPong.getFrame(), 600.0F, 70.0F);
    local_g.scale(-1.0F, 1.0F);
    this.animation.draw(-100.0F, 100.0F);
    this.animation.draw(-200.0F, 100.0F, 144.0F, 260.0F);
    if (this.start < 0) {
      this.limited.draw(-400.0F, 100.0F, 144.0F, 260.0F);
    }
    this.manual.draw(-600.0F, 100.0F, 144.0F, 260.0F);
    this.pingPong.draw(-700.0F, 100.0F, 72.0F, 130.0F);
  }
  
  public void update(GameContainer container, int delta)
  {
    if (container.getInput().isKeyDown(2)) {
      this.manual.update(delta);
    }
    if (this.start >= 0) {
      this.start -= delta;
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new AnimationTest());
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
    if (key == 57) {
      this.limited.restart();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.AnimationTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */