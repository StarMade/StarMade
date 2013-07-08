package org.newdawn.slick.particles;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.util.Log;

public class ParticleSystem
{
  protected SGL field_1794 = Renderer.get();
  public static final int BLEND_ADDITIVE = 1;
  public static final int BLEND_COMBINE = 2;
  private static final int DEFAULT_PARTICLES = 100;
  private ArrayList removeMe = new ArrayList();
  protected HashMap particlesByEmitter = new HashMap();
  protected int maxParticlesPerEmitter;
  protected ArrayList emitters = new ArrayList();
  protected Particle dummy;
  private int blendingMode = 2;
  private int pCount;
  private boolean usePoints;
  private float field_1795;
  private float field_1796;
  private boolean removeCompletedEmitters = true;
  private Image sprite;
  private boolean visible = true;
  private String defaultImageName;
  private Color mask;
  
  public static void setRelativePath(String path)
  {
    ConfigurableEmitter.setRelativePath(path);
  }
  
  public ParticleSystem(Image defaultSprite)
  {
    this(defaultSprite, 100);
  }
  
  public ParticleSystem(String defaultSpriteRef)
  {
    this(defaultSpriteRef, 100);
  }
  
  public void reset()
  {
    Iterator pools = this.particlesByEmitter.values().iterator();
    while (pools.hasNext())
    {
      ParticlePool pool = (ParticlePool)pools.next();
      pool.reset(this);
    }
    for (int pool = 0; pool < this.emitters.size(); pool++)
    {
      ParticleEmitter emitter = (ParticleEmitter)this.emitters.get(pool);
      emitter.resetState();
    }
  }
  
  public boolean isVisible()
  {
    return this.visible;
  }
  
  public void setVisible(boolean visible)
  {
    this.visible = visible;
  }
  
  public void setRemoveCompletedEmitters(boolean remove)
  {
    this.removeCompletedEmitters = remove;
  }
  
  public void setUsePoints(boolean usePoints)
  {
    this.usePoints = usePoints;
  }
  
  public boolean usePoints()
  {
    return this.usePoints;
  }
  
  public ParticleSystem(String defaultSpriteRef, int maxParticles)
  {
    this(defaultSpriteRef, maxParticles, null);
  }
  
  public ParticleSystem(String defaultSpriteRef, int maxParticles, Color mask)
  {
    this.maxParticlesPerEmitter = maxParticles;
    this.mask = mask;
    setDefaultImageName(defaultSpriteRef);
    this.dummy = createParticle(this);
  }
  
  public ParticleSystem(Image defaultSprite, int maxParticles)
  {
    this.maxParticlesPerEmitter = maxParticles;
    this.sprite = defaultSprite;
    this.dummy = createParticle(this);
  }
  
  public void setDefaultImageName(String ref)
  {
    this.defaultImageName = ref;
    this.sprite = null;
  }
  
  public int getBlendingMode()
  {
    return this.blendingMode;
  }
  
  protected Particle createParticle(ParticleSystem system)
  {
    return new Particle(system);
  }
  
  public void setBlendingMode(int mode)
  {
    this.blendingMode = mode;
  }
  
  public int getEmitterCount()
  {
    return this.emitters.size();
  }
  
  public ParticleEmitter getEmitter(int index)
  {
    return (ParticleEmitter)this.emitters.get(index);
  }
  
  public void addEmitter(ParticleEmitter emitter)
  {
    this.emitters.add(emitter);
    ParticlePool pool = new ParticlePool(this, this.maxParticlesPerEmitter);
    this.particlesByEmitter.put(emitter, pool);
  }
  
  public void removeEmitter(ParticleEmitter emitter)
  {
    this.emitters.remove(emitter);
    this.particlesByEmitter.remove(emitter);
  }
  
  public void removeAllEmitters()
  {
    for (int local_i = 0; local_i < this.emitters.size(); local_i++)
    {
      removeEmitter((ParticleEmitter)this.emitters.get(local_i));
      local_i--;
    }
  }
  
  public float getPositionX()
  {
    return this.field_1795;
  }
  
  public float getPositionY()
  {
    return this.field_1796;
  }
  
  public void setPosition(float local_x, float local_y)
  {
    this.field_1795 = local_x;
    this.field_1796 = local_y;
  }
  
  public void render()
  {
    render(this.field_1795, this.field_1796);
  }
  
  public void render(float local_x, float local_y)
  {
    if ((this.sprite == null) && (this.defaultImageName != null)) {
      loadSystemParticleImage();
    }
    if (!this.visible) {
      return;
    }
    this.field_1794.glTranslatef(local_x, local_y, 0.0F);
    if (this.blendingMode == 1) {
      this.field_1794.glBlendFunc(770, 1);
    }
    if (usePoints())
    {
      this.field_1794.glEnable(2832);
      TextureImpl.bindNone();
    }
    for (int emitterIdx = 0; emitterIdx < this.emitters.size(); emitterIdx++)
    {
      ParticleEmitter emitter = (ParticleEmitter)this.emitters.get(emitterIdx);
      if (emitter.isEnabled())
      {
        if (emitter.useAdditive()) {
          this.field_1794.glBlendFunc(770, 1);
        }
        ParticlePool pool = (ParticlePool)this.particlesByEmitter.get(emitter);
        Image image = emitter.getImage();
        if (image == null) {
          image = this.sprite;
        }
        if ((!emitter.isOriented()) && (!emitter.usePoints(this))) {
          image.startUse();
        }
        for (int local_i = 0; local_i < pool.particles.length; local_i++) {
          if (pool.particles[local_i].inUse()) {
            pool.particles[local_i].render();
          }
        }
        if ((!emitter.isOriented()) && (!emitter.usePoints(this))) {
          image.endUse();
        }
        if (emitter.useAdditive()) {
          this.field_1794.glBlendFunc(770, 771);
        }
      }
    }
    if (usePoints()) {
      this.field_1794.glDisable(2832);
    }
    if (this.blendingMode == 1) {
      this.field_1794.glBlendFunc(770, 771);
    }
    Color.white.bind();
    this.field_1794.glTranslatef(-local_x, -local_y, 0.0F);
  }
  
  private void loadSystemParticleImage()
  {
    AccessController.doPrivileged(new PrivilegedAction()
    {
      public Object run()
      {
        try
        {
          if (ParticleSystem.this.mask != null) {
            ParticleSystem.this.sprite = new Image(ParticleSystem.this.defaultImageName, ParticleSystem.this.mask);
          } else {
            ParticleSystem.this.sprite = new Image(ParticleSystem.this.defaultImageName);
          }
        }
        catch (SlickException local_e)
        {
          Log.error(local_e);
          ParticleSystem.this.defaultImageName = null;
        }
        return null;
      }
    });
  }
  
  public void update(int delta)
  {
    if ((this.sprite == null) && (this.defaultImageName != null)) {
      loadSystemParticleImage();
    }
    this.removeMe.clear();
    ArrayList emitters = new ArrayList(this.emitters);
    for (int local_i = 0; local_i < emitters.size(); local_i++)
    {
      ParticleEmitter emitter = (ParticleEmitter)emitters.get(local_i);
      if (emitter.isEnabled())
      {
        emitter.update(this, delta);
        if ((this.removeCompletedEmitters) && (emitter.completed()))
        {
          this.removeMe.add(emitter);
          this.particlesByEmitter.remove(emitter);
        }
      }
    }
    this.emitters.removeAll(this.removeMe);
    this.pCount = 0;
    if (!this.particlesByEmitter.isEmpty())
    {
      Iterator local_i = this.particlesByEmitter.keySet().iterator();
      while (local_i.hasNext())
      {
        ParticleEmitter emitter = (ParticleEmitter)local_i.next();
        if (emitter.isEnabled())
        {
          ParticlePool pool = (ParticlePool)this.particlesByEmitter.get(emitter);
          for (int local_i1 = 0; local_i1 < pool.particles.length; local_i1++) {
            if (pool.particles[local_i1].life > 0.0F)
            {
              pool.particles[local_i1].update(delta);
              this.pCount += 1;
            }
          }
        }
      }
    }
  }
  
  public int getParticleCount()
  {
    return this.pCount;
  }
  
  public Particle getNewParticle(ParticleEmitter emitter, float life)
  {
    ParticlePool pool = (ParticlePool)this.particlesByEmitter.get(emitter);
    ArrayList available = pool.available;
    if (available.size() > 0)
    {
      Particle local_p = (Particle)available.remove(available.size() - 1);
      local_p.init(emitter, life);
      local_p.setImage(this.sprite);
      return local_p;
    }
    Log.warn("Ran out of particles (increase the limit)!");
    return this.dummy;
  }
  
  public void release(Particle particle)
  {
    if (particle != this.dummy)
    {
      ParticlePool pool = (ParticlePool)this.particlesByEmitter.get(particle.getEmitter());
      pool.available.add(particle);
    }
  }
  
  public void releaseAll(ParticleEmitter emitter)
  {
    if (!this.particlesByEmitter.isEmpty())
    {
      Iterator local_it = this.particlesByEmitter.values().iterator();
      while (local_it.hasNext())
      {
        ParticlePool pool = (ParticlePool)local_it.next();
        for (int local_i = 0; local_i < pool.particles.length; local_i++) {
          if ((pool.particles[local_i].inUse()) && (pool.particles[local_i].getEmitter() == emitter))
          {
            pool.particles[local_i].setLife(-1.0F);
            release(pool.particles[local_i]);
          }
        }
      }
    }
  }
  
  public void moveAll(ParticleEmitter emitter, float local_x, float local_y)
  {
    ParticlePool pool = (ParticlePool)this.particlesByEmitter.get(emitter);
    for (int local_i = 0; local_i < pool.particles.length; local_i++) {
      if (pool.particles[local_i].inUse()) {
        pool.particles[local_i].move(local_x, local_y);
      }
    }
  }
  
  public ParticleSystem duplicate()
    throws SlickException
  {
    for (int local_i = 0; local_i < this.emitters.size(); local_i++) {
      if (!(this.emitters.get(local_i) instanceof ConfigurableEmitter)) {
        throw new SlickException("Only systems contianing configurable emitters can be duplicated");
      }
    }
    ParticleSystem local_i = null;
    try
    {
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      ParticleIO.saveConfiguredSystem(bout, this);
      ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
      local_i = ParticleIO.loadConfiguredSystem(bin);
    }
    catch (IOException bout)
    {
      Log.error("Failed to duplicate particle system");
      throw new SlickException("Unable to duplicated particle system", bout);
    }
    return local_i;
  }
  
  private class ParticlePool
  {
    public Particle[] particles;
    public ArrayList available;
    
    public ParticlePool(ParticleSystem system, int maxParticles)
    {
      this.particles = new Particle[maxParticles];
      this.available = new ArrayList();
      for (int local_i = 0; local_i < this.particles.length; local_i++) {
        this.particles[local_i] = ParticleSystem.this.createParticle(system);
      }
      reset(system);
    }
    
    public void reset(ParticleSystem system)
    {
      this.available.clear();
      for (int local_i = 0; local_i < this.particles.length; local_i++) {
        this.available.add(this.particles[local_i]);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.particles.ParticleSystem
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */