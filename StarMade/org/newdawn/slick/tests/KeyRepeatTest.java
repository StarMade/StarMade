package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class KeyRepeatTest
  extends BasicGame
{
  private int count;
  private Input input;
  
  public KeyRepeatTest()
  {
    super("KeyRepeatTest");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.input = container.getInput();
    this.input.enableKeyRepeat(300, 100);
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    local_g.drawString("Key Press Count: " + this.count, 100.0F, 100.0F);
    local_g.drawString("Press Space to Toggle Key Repeat", 100.0F, 150.0F);
    local_g.drawString("Key Repeat Enabled: " + this.input.isKeyRepeatEnabled(), 100.0F, 200.0F);
  }
  
  public void update(GameContainer container, int delta) {}
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new KeyRepeatTest());
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
    this.count += 1;
    if (key == 57) {
      if (this.input.isKeyRepeatEnabled()) {
        this.input.disableKeyRepeat();
      } else {
        this.input.enableKeyRepeat(300, 100);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.KeyRepeatTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */