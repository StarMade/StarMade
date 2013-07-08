package org.newdawn.slick.particles.effects;

import org.newdawn.slick.Image;
import org.newdawn.slick.particles.Particle;
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleSystem;

public class FireEmitter
  implements ParticleEmitter
{
  private int field_378;
  private int field_379;
  private int interval = 50;
  private int timer;
  private float size = 40.0F;
  
  public FireEmitter() {}
  
  public FireEmitter(int local_x, int local_y)
  {
    this.field_378 = local_x;
    this.field_379 = local_y;
  }
  
  public FireEmitter(int local_x, int local_y, float size)
  {
    this.field_378 = local_x;
    this.field_379 = local_y;
    this.size = size;
  }
  
  public void update(ParticleSystem system, int delta)
  {
    this.timer -= delta;
    if (this.timer <= 0)
    {
      this.timer = this.interval;
      Particle local_p = system.getNewParticle(this, 1000.0F);
      local_p.setColor(1.0F, 1.0F, 1.0F, 0.5F);
      local_p.setPosition(this.field_378, this.field_379);
      local_p.setSize(this.size);
      float local_vx = (float)(-0.01999999955296516D + Math.random() * 0.03999999910593033D);
      float local_vy = (float)-(Math.random() * 0.1500000059604645D);
      local_p.setVelocity(local_vx, local_vy, 1.1F);
    }
  }
  
  public void updateParticle(Particle particle, int delta)
  {
    if (particle.getLife() > 600.0F) {
      particle.adjustSize(0.07F * delta);
    } else {
      particle.adjustSize(-0.04F * delta * (this.size / 40.0F));
    }
    float local_c = 0.002F * delta;
    particle.adjustColor(0.0F, -local_c / 2.0F, -local_c * 2.0F, -local_c / 4.0F);
  }
  
  public boolean isEnabled()
  {
    return true;
  }
  
  public void setEnabled(boolean enabled) {}
  
  public boolean completed()
  {
    return false;
  }
  
  public boolean useAdditive()
  {
    return false;
  }
  
  public Image getImage()
  {
    return null;
  }
  
  public boolean usePoints(ParticleSystem system)
  {
    return false;
  }
  
  public boolean isOriented()
  {
    return false;
  }
  
  public void wrapUp() {}
  
  public void resetState() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.particles.effects.FireEmitter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */