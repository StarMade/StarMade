package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class TransformTest
  extends BasicGame
{
  private float scale = 1.0F;
  private boolean scaleUp;
  private boolean scaleDown;
  
  public TransformTest()
  {
    super("Transform Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    container.setTargetFrameRate(100);
  }
  
  public void render(GameContainer contiainer, Graphics local_g)
  {
    local_g.translate(320.0F, 240.0F);
    local_g.scale(this.scale, this.scale);
    local_g.setColor(Color.red);
    for (int local_x = 0; local_x < 10; local_x++) {
      for (int local_y = 0; local_y < 10; local_y++) {
        local_g.fillRect(-500 + local_x * 100, -500 + local_y * 100, 80.0F, 80.0F);
      }
    }
    local_g.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
    local_g.fillRect(-320.0F, -240.0F, 640.0F, 480.0F);
    local_g.setColor(Color.white);
    local_g.drawRect(-320.0F, -240.0F, 640.0F, 480.0F);
  }
  
  public void update(GameContainer container, int delta)
  {
    if (this.scaleUp) {
      this.scale += delta * 0.001F;
    }
    if (this.scaleDown) {
      this.scale -= delta * 0.001F;
    }
  }
  
  public void keyPressed(int key, char local_c)
  {
    if (key == 1) {
      System.exit(0);
    }
    if (key == 16) {
      this.scaleUp = true;
    }
    if (key == 30) {
      this.scaleDown = true;
    }
  }
  
  public void keyReleased(int key, char local_c)
  {
    if (key == 16) {
      this.scaleUp = false;
    }
    if (key == 30) {
      this.scaleDown = false;
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new TransformTest());
      container.setDisplayMode(640, 480, false);
      container.start();
    }
    catch (SlickException container)
    {
      container.printStackTrace();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.TransformTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */