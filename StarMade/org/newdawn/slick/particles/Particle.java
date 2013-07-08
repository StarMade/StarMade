package org.newdawn.slick.particles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public class Particle
{
  protected static SGL field_2205 = ;
  public static final int INHERIT_POINTS = 1;
  public static final int USE_POINTS = 2;
  public static final int USE_QUADS = 3;
  protected float field_2206;
  protected float field_2207;
  protected float velx;
  protected float vely;
  protected float size = 10.0F;
  protected Color color = Color.white;
  protected float life;
  protected float originalLife;
  private ParticleSystem engine;
  private ParticleEmitter emitter;
  protected Image image;
  protected int type;
  protected int usePoints = 1;
  protected boolean oriented = false;
  protected float scaleY = 1.0F;
  
  public Particle(ParticleSystem engine)
  {
    this.engine = engine;
  }
  
  public float getX()
  {
    return this.field_2206;
  }
  
  public float getY()
  {
    return this.field_2207;
  }
  
  public void move(float local_x, float local_y)
  {
    this.field_2206 += local_x;
    this.field_2207 += local_y;
  }
  
  public float getSize()
  {
    return this.size;
  }
  
  public Color getColor()
  {
    return this.color;
  }
  
  public void setImage(Image image)
  {
    this.image = image;
  }
  
  public float getOriginalLife()
  {
    return this.originalLife;
  }
  
  public float getLife()
  {
    return this.life;
  }
  
  public boolean inUse()
  {
    return this.life > 0.0F;
  }
  
  public void render()
  {
    if (((this.engine.usePoints()) && (this.usePoints == 1)) || (this.usePoints == 2))
    {
      TextureImpl.bindNone();
      field_2205.glEnable(2832);
      field_2205.glPointSize(this.size / 2.0F);
      this.color.bind();
      field_2205.glBegin(0);
      field_2205.glVertex2f(this.field_2206, this.field_2207);
      field_2205.glEnd();
    }
    else if ((this.oriented) || (this.scaleY != 1.0F))
    {
      field_2205.glPushMatrix();
      field_2205.glTranslatef(this.field_2206, this.field_2207, 0.0F);
      if (this.oriented)
      {
        float angle = (float)(Math.atan2(this.field_2207, this.field_2206) * 180.0D / 3.141592653589793D);
        field_2205.glRotatef(angle, 0.0F, 0.0F, 1.0F);
      }
      field_2205.glScalef(1.0F, this.scaleY, 1.0F);
      this.image.draw((int)-(this.size / 2.0F), (int)-(this.size / 2.0F), (int)this.size, (int)this.size, this.color);
      field_2205.glPopMatrix();
    }
    else
    {
      this.color.bind();
      this.image.drawEmbedded((int)(this.field_2206 - this.size / 2.0F), (int)(this.field_2207 - this.size / 2.0F), (int)this.size, (int)this.size);
    }
  }
  
  public void update(int delta)
  {
    this.emitter.updateParticle(this, delta);
    this.life -= delta;
    if (this.life > 0.0F)
    {
      this.field_2206 += delta * this.velx;
      this.field_2207 += delta * this.vely;
    }
    else
    {
      this.engine.release(this);
    }
  }
  
  public void init(ParticleEmitter emitter, float life)
  {
    this.field_2206 = 0.0F;
    this.emitter = emitter;
    this.field_2207 = 0.0F;
    this.velx = 0.0F;
    this.vely = 0.0F;
    this.size = 10.0F;
    this.type = 0;
    this.originalLife = (this.life = life);
    this.oriented = false;
    this.scaleY = 1.0F;
  }
  
  public void setType(int type)
  {
    this.type = type;
  }
  
  public void setUsePoint(int usePoints)
  {
    this.usePoints = usePoints;
  }
  
  public int getType()
  {
    return this.type;
  }
  
  public void setSize(float size)
  {
    this.size = size;
  }
  
  public void adjustSize(float delta)
  {
    this.size += delta;
    this.size = Math.max(0.0F, this.size);
  }
  
  public void setLife(float life)
  {
    this.life = life;
  }
  
  public void adjustLife(float delta)
  {
    this.life += delta;
  }
  
  public void kill()
  {
    this.life = 1.0F;
  }
  
  public void setColor(float local_r, float local_g, float local_b, float local_a)
  {
    if (this.color == Color.white)
    {
      this.color = new Color(local_r, local_g, local_b, local_a);
    }
    else
    {
      this.color.field_1776 = local_r;
      this.color.field_1777 = local_g;
      this.color.field_1778 = local_b;
      this.color.field_1779 = local_a;
    }
  }
  
  public void setPosition(float local_x, float local_y)
  {
    this.field_2206 = local_x;
    this.field_2207 = local_y;
  }
  
  public void setVelocity(float dirx, float diry, float speed)
  {
    this.velx = (dirx * speed);
    this.vely = (diry * speed);
  }
  
  public void setSpeed(float speed)
  {
    float currentSpeed = (float)Math.sqrt(this.velx * this.velx + this.vely * this.vely);
    this.velx *= speed;
    this.vely *= speed;
    this.velx /= currentSpeed;
    this.vely /= currentSpeed;
  }
  
  public void setVelocity(float velx, float vely)
  {
    setVelocity(velx, vely, 1.0F);
  }
  
  public void adjustPosition(float local_dx, float local_dy)
  {
    this.field_2206 += local_dx;
    this.field_2207 += local_dy;
  }
  
  public void adjustColor(float local_r, float local_g, float local_b, float local_a)
  {
    if (this.color == Color.white) {
      this.color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
    }
    this.color.field_1776 += local_r;
    this.color.field_1777 += local_g;
    this.color.field_1778 += local_b;
    this.color.field_1779 += local_a;
  }
  
  public void adjustColor(int local_r, int local_g, int local_b, int local_a)
  {
    if (this.color == Color.white) {
      this.color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
    }
    this.color.field_1776 += local_r / 255.0F;
    this.color.field_1777 += local_g / 255.0F;
    this.color.field_1778 += local_b / 255.0F;
    this.color.field_1779 += local_a / 255.0F;
  }
  
  public void adjustVelocity(float local_dx, float local_dy)
  {
    this.velx += local_dx;
    this.vely += local_dy;
  }
  
  public ParticleEmitter getEmitter()
  {
    return this.emitter;
  }
  
  public String toString()
  {
    return super.toString() + " : " + this.life;
  }
  
  public boolean isOriented()
  {
    return this.oriented;
  }
  
  public void setOriented(boolean oriented)
  {
    this.oriented = oriented;
  }
  
  public float getScaleY()
  {
    return this.scaleY;
  }
  
  public void setScaleY(float scaleY)
  {
    this.scaleY = scaleY;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.particles.Particle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */