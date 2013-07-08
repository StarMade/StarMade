package org.newdawn.slick.tests;

import java.io.IOException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class DuplicateEmitterTest
  extends BasicGame
{
  private GameContainer container;
  private ParticleSystem explosionSystem;
  private ConfigurableEmitter explosionEmitter;
  
  public DuplicateEmitterTest()
  {
    super("DuplicateEmitterTest");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.container = container;
    try
    {
      this.explosionSystem = ParticleIO.loadConfiguredSystem("testdata/endlessexplosion.xml");
      this.explosionEmitter = ((ConfigurableEmitter)this.explosionSystem.getEmitter(0));
      this.explosionEmitter.setPosition(400.0F, 100.0F);
      for (int local_i = 0; local_i < 5; local_i++)
      {
        ConfigurableEmitter newOne = this.explosionEmitter.duplicate();
        if (newOne == null) {
          throw new SlickException("Failed to duplicate explosionEmitter");
        }
        newOne.name = (newOne.name + "_" + local_i);
        newOne.setPosition((local_i + 1) * 133, 400.0F);
        this.explosionSystem.addEmitter(newOne);
      }
    }
    catch (IOException local_i)
    {
      throw new SlickException("Failed to load particle systems", local_i);
    }
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {
    this.explosionSystem.update(delta);
  }
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    this.explosionSystem.render();
  }
  
  public void keyPressed(int key, char local_c)
  {
    if (key == 1) {
      this.container.exit();
    }
    if (key == 37) {
      this.explosionEmitter.wrapUp();
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new DuplicateEmitterTest());
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
 * Qualified Name:     org.newdawn.slick.tests.DuplicateEmitterTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */