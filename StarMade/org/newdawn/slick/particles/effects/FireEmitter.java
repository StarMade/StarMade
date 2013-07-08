/*   1:    */package org.newdawn.slick.particles.effects;
/*   2:    */
/*   3:    */import org.newdawn.slick.Image;
/*   4:    */import org.newdawn.slick.particles.Particle;
/*   5:    */import org.newdawn.slick.particles.ParticleEmitter;
/*   6:    */import org.newdawn.slick.particles.ParticleSystem;
/*   7:    */
/*  15:    */public class FireEmitter
/*  16:    */  implements ParticleEmitter
/*  17:    */{
/*  18:    */  private int x;
/*  19:    */  private int y;
/*  20: 20 */  private int interval = 50;
/*  21:    */  
/*  22:    */  private int timer;
/*  23:    */  
/*  24: 24 */  private float size = 40.0F;
/*  25:    */  
/*  31:    */  public FireEmitter() {}
/*  32:    */  
/*  37:    */  public FireEmitter(int x, int y)
/*  38:    */  {
/*  39: 39 */    this.x = x;
/*  40: 40 */    this.y = y;
/*  41:    */  }
/*  42:    */  
/*  49:    */  public FireEmitter(int x, int y, float size)
/*  50:    */  {
/*  51: 51 */    this.x = x;
/*  52: 52 */    this.y = y;
/*  53: 53 */    this.size = size;
/*  54:    */  }
/*  55:    */  
/*  58:    */  public void update(ParticleSystem system, int delta)
/*  59:    */  {
/*  60: 60 */    this.timer -= delta;
/*  61: 61 */    if (this.timer <= 0) {
/*  62: 62 */      this.timer = this.interval;
/*  63: 63 */      Particle p = system.getNewParticle(this, 1000.0F);
/*  64: 64 */      p.setColor(1.0F, 1.0F, 1.0F, 0.5F);
/*  65: 65 */      p.setPosition(this.x, this.y);
/*  66: 66 */      p.setSize(this.size);
/*  67: 67 */      float vx = (float)(-0.01999999955296516D + Math.random() * 0.03999999910593033D);
/*  68: 68 */      float vy = (float)-(Math.random() * 0.1500000059604645D);
/*  69: 69 */      p.setVelocity(vx, vy, 1.1F);
/*  70:    */    }
/*  71:    */  }
/*  72:    */  
/*  75:    */  public void updateParticle(Particle particle, int delta)
/*  76:    */  {
/*  77: 77 */    if (particle.getLife() > 600.0F) {
/*  78: 78 */      particle.adjustSize(0.07F * delta);
/*  79:    */    } else {
/*  80: 80 */      particle.adjustSize(-0.04F * delta * (this.size / 40.0F));
/*  81:    */    }
/*  82: 82 */    float c = 0.002F * delta;
/*  83: 83 */    particle.adjustColor(0.0F, -c / 2.0F, -c * 2.0F, -c / 4.0F);
/*  84:    */  }
/*  85:    */  
/*  88:    */  public boolean isEnabled()
/*  89:    */  {
/*  90: 90 */    return true;
/*  91:    */  }
/*  92:    */  
/*  96:    */  public void setEnabled(boolean enabled) {}
/*  97:    */  
/* 101:    */  public boolean completed()
/* 102:    */  {
/* 103:103 */    return false;
/* 104:    */  }
/* 105:    */  
/* 108:    */  public boolean useAdditive()
/* 109:    */  {
/* 110:110 */    return false;
/* 111:    */  }
/* 112:    */  
/* 115:    */  public Image getImage()
/* 116:    */  {
/* 117:117 */    return null;
/* 118:    */  }
/* 119:    */  
/* 122:    */  public boolean usePoints(ParticleSystem system)
/* 123:    */  {
/* 124:124 */    return false;
/* 125:    */  }
/* 126:    */  
/* 129:    */  public boolean isOriented()
/* 130:    */  {
/* 131:131 */    return false;
/* 132:    */  }
/* 133:    */  
/* 134:    */  public void wrapUp() {}
/* 135:    */  
/* 136:    */  public void resetState() {}
/* 137:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.particles.effects.FireEmitter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */