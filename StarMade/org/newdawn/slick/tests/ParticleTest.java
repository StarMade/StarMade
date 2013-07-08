package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.particles.effects.FireEmitter;

public class ParticleTest
  extends BasicGame
{
  private ParticleSystem system;
  private int mode = 2;
  
  public ParticleTest()
  {
    super("Particle Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    Image image = new Image("testdata/particle.tga", true);
    this.system = new ParticleSystem(image);
    this.system.addEmitter(new FireEmitter(400, 300, 45.0F));
    this.system.addEmitter(new FireEmitter(200, 300, 60.0F));
    this.system.addEmitter(new FireEmitter(600, 300, 30.0F));
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    for (int local_i = 0; local_i < 100; local_i++)
    {
      local_g.translate(1.0F, 1.0F);
      this.system.render();
    }
    local_g.resetTransform();
    local_g.drawString("Press space to toggle blending mode", 200.0F, 500.0F);
    local_g.drawString("Particle Count: " + this.system.getParticleCount() * 100, 200.0F, 520.0F);
  }
  
  public void update(GameContainer container, int delta)
  {
    this.system.update(delta);
  }
  
  public void keyPressed(int key, char local_c)
  {
    if (key == 1) {
      System.exit(0);
    }
    if (key == 57)
    {
      this.mode = (1 == this.mode ? 2 : 1);
      this.system.setBlendingMode(this.mode);
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new ParticleTest());
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
 * Qualified Name:     org.newdawn.slick.tests.ParticleTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */