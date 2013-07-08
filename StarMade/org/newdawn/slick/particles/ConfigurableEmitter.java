package org.newdawn.slick.particles;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.FastTrig;
import org.newdawn.slick.util.Log;

public class ConfigurableEmitter
  implements ParticleEmitter
{
  private static String relativePath = "";
  public Range spawnInterval = new Range(100.0F, 100.0F, null);
  public Range spawnCount = new Range(5.0F, 5.0F, null);
  public Range initialLife = new Range(1000.0F, 1000.0F, null);
  public Range initialSize = new Range(10.0F, 10.0F, null);
  public Range xOffset = new Range(0.0F, 0.0F, null);
  public Range yOffset = new Range(0.0F, 0.0F, null);
  public RandomValue spread = new RandomValue(360.0F, null);
  public SimpleValue angularOffset = new SimpleValue(0.0F, null);
  public Range initialDistance = new Range(0.0F, 0.0F, null);
  public Range speed = new Range(50.0F, 50.0F, null);
  public SimpleValue growthFactor = new SimpleValue(0.0F, null);
  public SimpleValue gravityFactor = new SimpleValue(0.0F, null);
  public SimpleValue windFactor = new SimpleValue(0.0F, null);
  public Range length = new Range(1000.0F, 1000.0F, null);
  public ArrayList colors = new ArrayList();
  public SimpleValue startAlpha = new SimpleValue(255.0F, null);
  public SimpleValue endAlpha = new SimpleValue(0.0F, null);
  public LinearInterpolator alpha;
  public LinearInterpolator size;
  public LinearInterpolator velocity;
  public LinearInterpolator scaleY;
  public Range emitCount = new Range(1000.0F, 1000.0F, null);
  public int usePoints = 1;
  public boolean useOriented = false;
  public boolean useAdditive = false;
  public String name;
  public String imageName = "";
  private Image image;
  private boolean updateImage;
  private boolean enabled = true;
  private float field_378;
  private float field_379;
  private int nextSpawn = 0;
  private int timeout;
  private int particleCount;
  private ParticleSystem engine;
  private int leftToEmit;
  protected boolean wrapUp = false;
  protected boolean completed = false;
  protected boolean adjust;
  protected float adjustx;
  protected float adjusty;
  
  public static void setRelativePath(String path)
  {
    if (!path.endsWith("/")) {
      path = path + "/";
    }
    relativePath = path;
  }
  
  public ConfigurableEmitter(String name)
  {
    this.name = name;
    this.leftToEmit = ((int)this.emitCount.random());
    this.timeout = ((int)this.length.random());
    this.colors.add(new ColorRecord(0.0F, Color.white));
    this.colors.add(new ColorRecord(1.0F, Color.red));
    ArrayList curve = new ArrayList();
    curve.add(new Vector2f(0.0F, 0.0F));
    curve.add(new Vector2f(1.0F, 255.0F));
    this.alpha = new LinearInterpolator(curve, 0, 255);
    curve = new ArrayList();
    curve.add(new Vector2f(0.0F, 0.0F));
    curve.add(new Vector2f(1.0F, 255.0F));
    this.size = new LinearInterpolator(curve, 0, 255);
    curve = new ArrayList();
    curve.add(new Vector2f(0.0F, 0.0F));
    curve.add(new Vector2f(1.0F, 1.0F));
    this.velocity = new LinearInterpolator(curve, 0, 1);
    curve = new ArrayList();
    curve.add(new Vector2f(0.0F, 0.0F));
    curve.add(new Vector2f(1.0F, 1.0F));
    this.scaleY = new LinearInterpolator(curve, 0, 1);
  }
  
  public void setImageName(String imageName)
  {
    if (imageName.length() == 0) {
      imageName = null;
    }
    this.imageName = imageName;
    if (imageName == null) {
      this.image = null;
    } else {
      this.updateImage = true;
    }
  }
  
  public String getImageName()
  {
    return this.imageName;
  }
  
  public String toString()
  {
    return "[" + this.name + "]";
  }
  
  public void setPosition(float local_x, float local_y)
  {
    setPosition(local_x, local_y, true);
  }
  
  public void setPosition(float local_x, float local_y, boolean moveParticles)
  {
    if (moveParticles)
    {
      this.adjust = true;
      this.adjustx -= this.field_378 - local_x;
      this.adjusty -= this.field_379 - local_y;
    }
    this.field_378 = local_x;
    this.field_379 = local_y;
  }
  
  public float getX()
  {
    return this.field_378;
  }
  
  public float getY()
  {
    return this.field_379;
  }
  
  public boolean isEnabled()
  {
    return this.enabled;
  }
  
  public void setEnabled(boolean enabled)
  {
    this.enabled = enabled;
  }
  
  public void update(ParticleSystem system, int delta)
  {
    this.engine = system;
    if (!this.adjust)
    {
      this.adjustx = 0.0F;
      this.adjusty = 0.0F;
    }
    else
    {
      this.adjust = false;
    }
    if (this.updateImage)
    {
      this.updateImage = false;
      try
      {
        this.image = new Image(relativePath + this.imageName);
      }
      catch (SlickException local_e)
      {
        this.image = null;
        Log.error(local_e);
      }
    }
    if (((this.wrapUp) || ((this.length.isEnabled()) && (this.timeout < 0)) || ((this.emitCount.isEnabled()) && (this.leftToEmit <= 0))) && (this.particleCount == 0)) {
      this.completed = true;
    }
    this.particleCount = 0;
    if (this.wrapUp) {
      return;
    }
    if (this.length.isEnabled())
    {
      if (this.timeout < 0) {
        return;
      }
      this.timeout -= delta;
    }
    if ((this.emitCount.isEnabled()) && (this.leftToEmit <= 0)) {
      return;
    }
    this.nextSpawn -= delta;
    if (this.nextSpawn < 0)
    {
      this.nextSpawn = ((int)this.spawnInterval.random());
      int local_e = (int)this.spawnCount.random();
      for (int local_i = 0; local_i < local_e; local_i++)
      {
        Particle local_p = system.getNewParticle(this, this.initialLife.random());
        local_p.setSize(this.initialSize.random());
        local_p.setPosition(this.field_378 + this.xOffset.random(), this.field_379 + this.yOffset.random());
        local_p.setVelocity(0.0F, 0.0F, 0.0F);
        float dist = this.initialDistance.random();
        float power = this.speed.random();
        if ((dist != 0.0F) || (power != 0.0F))
        {
          float local_s = this.spread.getValue(0.0F);
          float ang = local_s + this.angularOffset.getValue(0.0F) - this.spread.getValue() / 2.0F - 90.0F;
          float local_xa = (float)FastTrig.cos(Math.toRadians(ang)) * dist;
          float local_ya = (float)FastTrig.sin(Math.toRadians(ang)) * dist;
          local_p.adjustPosition(local_xa, local_ya);
          float local_xv = (float)FastTrig.cos(Math.toRadians(ang));
          float local_yv = (float)FastTrig.sin(Math.toRadians(ang));
          local_p.setVelocity(local_xv, local_yv, power * 0.001F);
        }
        if (this.image != null) {
          local_p.setImage(this.image);
        }
        ColorRecord local_s = (ColorRecord)this.colors.get(0);
        local_p.setColor(local_s.col.field_1776, local_s.col.field_1777, local_s.col.field_1778, this.startAlpha.getValue(0.0F) / 255.0F);
        local_p.setUsePoint(this.usePoints);
        local_p.setOriented(this.useOriented);
        if (this.emitCount.isEnabled())
        {
          this.leftToEmit -= 1;
          if (this.leftToEmit <= 0) {
            break;
          }
        }
      }
    }
  }
  
  public void updateParticle(Particle particle, int delta)
  {
    this.particleCount += 1;
    particle.field_2206 += this.adjustx;
    particle.field_2207 += this.adjusty;
    particle.adjustVelocity(this.windFactor.getValue(0.0F) * 5.0E-005F * delta, this.gravityFactor.getValue(0.0F) * 5.0E-005F * delta);
    float offset = particle.getLife() / particle.getOriginalLife();
    float inv = 1.0F - offset;
    float colOffset = 0.0F;
    float colInv = 1.0F;
    Color startColor = null;
    Color endColor = null;
    for (int local_i = 0; local_i < this.colors.size() - 1; local_i++)
    {
      ColorRecord rec1 = (ColorRecord)this.colors.get(local_i);
      ColorRecord rec2 = (ColorRecord)this.colors.get(local_i + 1);
      if ((inv >= rec1.pos) && (inv <= rec2.pos))
      {
        startColor = rec1.col;
        endColor = rec2.col;
        float step = rec2.pos - rec1.pos;
        colOffset = inv - rec1.pos;
        colOffset /= step;
        colOffset = 1.0F - colOffset;
        colInv = 1.0F - colOffset;
      }
    }
    if (startColor != null)
    {
      float local_i = startColor.field_1776 * colOffset + endColor.field_1776 * colInv;
      float rec1 = startColor.field_1777 * colOffset + endColor.field_1777 * colInv;
      float rec2 = startColor.field_1778 * colOffset + endColor.field_1778 * colInv;
      float step;
      float step;
      if (this.alpha.isActive()) {
        step = this.alpha.getValue(inv) / 255.0F;
      } else {
        step = this.startAlpha.getValue(0.0F) / 255.0F * offset + this.endAlpha.getValue(0.0F) / 255.0F * inv;
      }
      particle.setColor(local_i, rec1, rec2, step);
    }
    if (this.size.isActive())
    {
      float local_i = this.size.getValue(inv);
      particle.setSize(local_i);
    }
    else
    {
      particle.adjustSize(delta * this.growthFactor.getValue(0.0F) * 0.001F);
    }
    if (this.velocity.isActive()) {
      particle.setSpeed(this.velocity.getValue(inv));
    }
    if (this.scaleY.isActive()) {
      particle.setScaleY(this.scaleY.getValue(inv));
    }
  }
  
  public boolean completed()
  {
    if (this.engine == null) {
      return false;
    }
    if (this.length.isEnabled())
    {
      if (this.timeout > 0) {
        return false;
      }
      return this.completed;
    }
    if (this.emitCount.isEnabled())
    {
      if (this.leftToEmit > 0) {
        return false;
      }
      return this.completed;
    }
    if (this.wrapUp) {
      return this.completed;
    }
    return false;
  }
  
  public void replay()
  {
    reset();
    this.nextSpawn = 0;
    this.leftToEmit = ((int)this.emitCount.random());
    this.timeout = ((int)this.length.random());
  }
  
  public void reset()
  {
    this.completed = false;
    if (this.engine != null) {
      this.engine.releaseAll(this);
    }
  }
  
  public void replayCheck()
  {
    if ((completed()) && (this.engine != null) && (this.engine.getParticleCount() == 0)) {
      replay();
    }
  }
  
  public ConfigurableEmitter duplicate()
  {
    ConfigurableEmitter theCopy = null;
    try
    {
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      ParticleIO.saveEmitter(bout, this);
      ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
      theCopy = ParticleIO.loadEmitter(bin);
    }
    catch (IOException bout)
    {
      Log.error("Slick: ConfigurableEmitter.duplicate(): caught exception " + bout.toString());
      return null;
    }
    return theCopy;
  }
  
  public void addColorPoint(float pos, Color col)
  {
    this.colors.add(new ColorRecord(pos, col));
  }
  
  public boolean useAdditive()
  {
    return this.useAdditive;
  }
  
  public boolean isOriented()
  {
    return this.useOriented;
  }
  
  public boolean usePoints(ParticleSystem system)
  {
    return ((this.usePoints == 1) && (system.usePoints())) || (this.usePoints == 2);
  }
  
  public Image getImage()
  {
    return this.image;
  }
  
  public void wrapUp()
  {
    this.wrapUp = true;
  }
  
  public void resetState()
  {
    this.wrapUp = false;
    replay();
  }
  
  public class Range
  {
    private float max;
    private float min;
    private boolean enabled = false;
    
    private Range(float min, float max)
    {
      this.min = min;
      this.max = max;
    }
    
    public float random()
    {
      return (float)(this.min + Math.random() * (this.max - this.min));
    }
    
    public boolean isEnabled()
    {
      return this.enabled;
    }
    
    public void setEnabled(boolean enabled)
    {
      this.enabled = enabled;
    }
    
    public float getMax()
    {
      return this.max;
    }
    
    public void setMax(float max)
    {
      this.max = max;
    }
    
    public float getMin()
    {
      return this.min;
    }
    
    public void setMin(float min)
    {
      this.min = min;
    }
  }
  
  public class ColorRecord
  {
    public float pos;
    public Color col;
    
    public ColorRecord(float pos, Color col)
    {
      this.pos = pos;
      this.col = col;
    }
  }
  
  public class LinearInterpolator
    implements ConfigurableEmitter.Value
  {
    private ArrayList curve;
    private boolean active;
    private int min;
    private int max;
    
    public LinearInterpolator(ArrayList curve, int min, int max)
    {
      this.curve = curve;
      this.min = min;
      this.max = max;
      this.active = false;
    }
    
    public void setCurve(ArrayList curve)
    {
      this.curve = curve;
    }
    
    public ArrayList getCurve()
    {
      return this.curve;
    }
    
    public float getValue(float local_t)
    {
      Vector2f local_p0 = (Vector2f)this.curve.get(0);
      for (int local_i = 1; local_i < this.curve.size(); local_i++)
      {
        Vector2f local_p1 = (Vector2f)this.curve.get(local_i);
        if ((local_t >= local_p0.getX()) && (local_t <= local_p1.getX()))
        {
          float local_st = (local_t - local_p0.getX()) / (local_p1.getX() - local_p0.getX());
          float local_r = local_p0.getY() + local_st * (local_p1.getY() - local_p0.getY());
          return local_r;
        }
        local_p0 = local_p1;
      }
      return 0.0F;
    }
    
    public boolean isActive()
    {
      return this.active;
    }
    
    public void setActive(boolean active)
    {
      this.active = active;
    }
    
    public int getMax()
    {
      return this.max;
    }
    
    public void setMax(int max)
    {
      this.max = max;
    }
    
    public int getMin()
    {
      return this.min;
    }
    
    public void setMin(int min)
    {
      this.min = min;
    }
  }
  
  public class RandomValue
    implements ConfigurableEmitter.Value
  {
    private float value;
    
    private RandomValue(float value)
    {
      this.value = value;
    }
    
    public float getValue(float time)
    {
      return (float)(Math.random() * this.value);
    }
    
    public void setValue(float value)
    {
      this.value = value;
    }
    
    public float getValue()
    {
      return this.value;
    }
  }
  
  public class SimpleValue
    implements ConfigurableEmitter.Value
  {
    private float value;
    private float next;
    
    private SimpleValue(float value)
    {
      this.value = value;
    }
    
    public float getValue(float time)
    {
      return this.value;
    }
    
    public void setValue(float value)
    {
      this.value = value;
    }
  }
  
  public static abstract interface Value
  {
    public abstract float getValue(float paramFloat);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.particles.ConfigurableEmitter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */