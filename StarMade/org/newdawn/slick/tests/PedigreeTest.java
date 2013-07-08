package org.newdawn.slick.tests;

import java.io.IOException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class PedigreeTest
  extends BasicGame
{
  private Image image;
  private GameContainer container;
  private ParticleSystem trail;
  private ParticleSystem fire;
  private float field_298;
  private float field_299 = 900.0F;
  
  public PedigreeTest()
  {
    super("Pedigree Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.container = container;
    try
    {
      this.fire = ParticleIO.loadConfiguredSystem("testdata/system.xml");
      this.trail = ParticleIO.loadConfiguredSystem("testdata/smoketrail.xml");
    }
    catch (IOException local_e)
    {
      throw new SlickException("Failed to load particle systems", local_e);
    }
    this.image = new Image("testdata/rocket.png");
    spawnRocket();
  }
  
  private void spawnRocket()
  {
    this.field_299 = 700.0F;
    this.field_298 = ((float)(Math.random() * 600.0D + 100.0D));
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    ((ConfigurableEmitter)this.trail.getEmitter(0)).setPosition(this.field_298 + 14.0F, this.field_299 + 35.0F);
    this.trail.render();
    this.image.draw((int)this.field_298, (int)this.field_299);
    local_g.translate(400.0F, 300.0F);
    this.fire.render();
  }
  
  public void update(GameContainer container, int delta)
  {
    this.fire.update(delta);
    this.trail.update(delta);
    this.field_299 -= delta * 0.25F;
    if (this.field_299 < -100.0F) {
      spawnRocket();
    }
  }
  
  public void mousePressed(int button, int local_x, int local_y)
  {
    super.mousePressed(button, local_x, local_y);
    for (int local_i = 0; local_i < this.fire.getEmitterCount(); local_i++) {
      ((ConfigurableEmitter)this.fire.getEmitter(local_i)).setPosition(local_x - 400, local_y - 300, true);
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new PedigreeTest());
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
 * Qualified Name:     org.newdawn.slick.tests.PedigreeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */