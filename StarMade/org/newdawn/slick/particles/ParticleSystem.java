/*   1:    */package org.newdawn.slick.particles;
/*   2:    */
/*   3:    */import java.io.ByteArrayInputStream;
/*   4:    */import java.io.ByteArrayOutputStream;
/*   5:    */import java.io.IOException;
/*   6:    */import java.security.AccessController;
/*   7:    */import java.security.PrivilegedAction;
/*   8:    */import java.util.ArrayList;
/*   9:    */import java.util.Collection;
/*  10:    */import java.util.HashMap;
/*  11:    */import java.util.Iterator;
/*  12:    */import java.util.Set;
/*  13:    */import org.newdawn.slick.Color;
/*  14:    */import org.newdawn.slick.Image;
/*  15:    */import org.newdawn.slick.SlickException;
/*  16:    */import org.newdawn.slick.opengl.TextureImpl;
/*  17:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*  18:    */import org.newdawn.slick.opengl.renderer.SGL;
/*  19:    */import org.newdawn.slick.util.Log;
/*  20:    */
/*  27:    */public class ParticleSystem
/*  28:    */{
/*  29: 29 */  protected SGL GL = Renderer.get();
/*  30:    */  
/*  32:    */  public static final int BLEND_ADDITIVE = 1;
/*  33:    */  
/*  35:    */  public static final int BLEND_COMBINE = 2;
/*  36:    */  
/*  38:    */  private static final int DEFAULT_PARTICLES = 100;
/*  39:    */  
/*  40: 40 */  private ArrayList removeMe = new ArrayList();
/*  41:    */  
/*  47:    */  public static void setRelativePath(String path)
/*  48:    */  {
/*  49: 49 */    ConfigurableEmitter.setRelativePath(path);
/*  50:    */  }
/*  51:    */  
/*  57:    */  private class ParticlePool
/*  58:    */  {
/*  59:    */    public Particle[] particles;
/*  60:    */    
/*  64:    */    public ArrayList available;
/*  65:    */    
/*  70:    */    public ParticlePool(ParticleSystem system, int maxParticles)
/*  71:    */    {
/*  72: 72 */      this.particles = new Particle[maxParticles];
/*  73: 73 */      this.available = new ArrayList();
/*  74:    */      
/*  75: 75 */      for (int i = 0; i < this.particles.length; i++)
/*  76:    */      {
/*  77: 77 */        this.particles[i] = ParticleSystem.this.createParticle(system);
/*  78:    */      }
/*  79:    */      
/*  80: 80 */      reset(system);
/*  81:    */    }
/*  82:    */    
/*  87:    */    public void reset(ParticleSystem system)
/*  88:    */    {
/*  89: 89 */      this.available.clear();
/*  90:    */      
/*  91: 91 */      for (int i = 0; i < this.particles.length; i++)
/*  92:    */      {
/*  93: 93 */        this.available.add(this.particles[i]);
/*  94:    */      }
/*  95:    */    }
/*  96:    */  }
/*  97:    */  
/* 104:104 */  protected HashMap particlesByEmitter = new HashMap();
/* 105:    */  
/* 107:    */  protected int maxParticlesPerEmitter;
/* 108:    */  
/* 109:109 */  protected ArrayList emitters = new ArrayList();
/* 110:    */  
/* 112:    */  protected Particle dummy;
/* 113:    */  
/* 114:114 */  private int blendingMode = 2;
/* 115:    */  
/* 116:    */  private int pCount;
/* 117:    */  
/* 118:    */  private boolean usePoints;
/* 119:    */  
/* 120:    */  private float x;
/* 121:    */  
/* 122:    */  private float y;
/* 123:    */  
/* 124:124 */  private boolean removeCompletedEmitters = true;
/* 125:    */  
/* 127:    */  private Image sprite;
/* 128:    */  
/* 129:129 */  private boolean visible = true;
/* 130:    */  
/* 132:    */  private String defaultImageName;
/* 133:    */  
/* 135:    */  private Color mask;
/* 136:    */  
/* 139:    */  public ParticleSystem(Image defaultSprite)
/* 140:    */  {
/* 141:141 */    this(defaultSprite, 100);
/* 142:    */  }
/* 143:    */  
/* 148:    */  public ParticleSystem(String defaultSpriteRef)
/* 149:    */  {
/* 150:150 */    this(defaultSpriteRef, 100);
/* 151:    */  }
/* 152:    */  
/* 155:    */  public void reset()
/* 156:    */  {
/* 157:157 */    Iterator pools = this.particlesByEmitter.values().iterator();
/* 158:158 */    while (pools.hasNext()) {
/* 159:159 */      ParticlePool pool = (ParticlePool)pools.next();
/* 160:160 */      pool.reset(this);
/* 161:    */    }
/* 162:    */    
/* 163:163 */    for (int i = 0; i < this.emitters.size(); i++) {
/* 164:164 */      ParticleEmitter emitter = (ParticleEmitter)this.emitters.get(i);
/* 165:165 */      emitter.resetState();
/* 166:    */    }
/* 167:    */  }
/* 168:    */  
/* 174:    */  public boolean isVisible()
/* 175:    */  {
/* 176:176 */    return this.visible;
/* 177:    */  }
/* 178:    */  
/* 184:    */  public void setVisible(boolean visible)
/* 185:    */  {
/* 186:186 */    this.visible = visible;
/* 187:    */  }
/* 188:    */  
/* 193:    */  public void setRemoveCompletedEmitters(boolean remove)
/* 194:    */  {
/* 195:195 */    this.removeCompletedEmitters = remove;
/* 196:    */  }
/* 197:    */  
/* 202:    */  public void setUsePoints(boolean usePoints)
/* 203:    */  {
/* 204:204 */    this.usePoints = usePoints;
/* 205:    */  }
/* 206:    */  
/* 211:    */  public boolean usePoints()
/* 212:    */  {
/* 213:213 */    return this.usePoints;
/* 214:    */  }
/* 215:    */  
/* 221:    */  public ParticleSystem(String defaultSpriteRef, int maxParticles)
/* 222:    */  {
/* 223:223 */    this(defaultSpriteRef, maxParticles, null);
/* 224:    */  }
/* 225:    */  
/* 232:    */  public ParticleSystem(String defaultSpriteRef, int maxParticles, Color mask)
/* 233:    */  {
/* 234:234 */    this.maxParticlesPerEmitter = maxParticles;
/* 235:235 */    this.mask = mask;
/* 236:    */    
/* 237:237 */    setDefaultImageName(defaultSpriteRef);
/* 238:238 */    this.dummy = createParticle(this);
/* 239:    */  }
/* 240:    */  
/* 246:    */  public ParticleSystem(Image defaultSprite, int maxParticles)
/* 247:    */  {
/* 248:248 */    this.maxParticlesPerEmitter = maxParticles;
/* 249:    */    
/* 250:250 */    this.sprite = defaultSprite;
/* 251:251 */    this.dummy = createParticle(this);
/* 252:    */  }
/* 253:    */  
/* 258:    */  public void setDefaultImageName(String ref)
/* 259:    */  {
/* 260:260 */    this.defaultImageName = ref;
/* 261:261 */    this.sprite = null;
/* 262:    */  }
/* 263:    */  
/* 270:    */  public int getBlendingMode()
/* 271:    */  {
/* 272:272 */    return this.blendingMode;
/* 273:    */  }
/* 274:    */  
/* 281:    */  protected Particle createParticle(ParticleSystem system)
/* 282:    */  {
/* 283:283 */    return new Particle(system);
/* 284:    */  }
/* 285:    */  
/* 290:    */  public void setBlendingMode(int mode)
/* 291:    */  {
/* 292:292 */    this.blendingMode = mode;
/* 293:    */  }
/* 294:    */  
/* 299:    */  public int getEmitterCount()
/* 300:    */  {
/* 301:301 */    return this.emitters.size();
/* 302:    */  }
/* 303:    */  
/* 309:    */  public ParticleEmitter getEmitter(int index)
/* 310:    */  {
/* 311:311 */    return (ParticleEmitter)this.emitters.get(index);
/* 312:    */  }
/* 313:    */  
/* 318:    */  public void addEmitter(ParticleEmitter emitter)
/* 319:    */  {
/* 320:320 */    this.emitters.add(emitter);
/* 321:    */    
/* 322:322 */    ParticlePool pool = new ParticlePool(this, this.maxParticlesPerEmitter);
/* 323:323 */    this.particlesByEmitter.put(emitter, pool);
/* 324:    */  }
/* 325:    */  
/* 330:    */  public void removeEmitter(ParticleEmitter emitter)
/* 331:    */  {
/* 332:332 */    this.emitters.remove(emitter);
/* 333:333 */    this.particlesByEmitter.remove(emitter);
/* 334:    */  }
/* 335:    */  
/* 338:    */  public void removeAllEmitters()
/* 339:    */  {
/* 340:340 */    for (int i = 0; i < this.emitters.size(); i++) {
/* 341:341 */      removeEmitter((ParticleEmitter)this.emitters.get(i));
/* 342:342 */      i--;
/* 343:    */    }
/* 344:    */  }
/* 345:    */  
/* 350:    */  public float getPositionX()
/* 351:    */  {
/* 352:352 */    return this.x;
/* 353:    */  }
/* 354:    */  
/* 359:    */  public float getPositionY()
/* 360:    */  {
/* 361:361 */    return this.y;
/* 362:    */  }
/* 363:    */  
/* 370:    */  public void setPosition(float x, float y)
/* 371:    */  {
/* 372:372 */    this.x = x;
/* 373:373 */    this.y = y;
/* 374:    */  }
/* 375:    */  
/* 378:    */  public void render()
/* 379:    */  {
/* 380:380 */    render(this.x, this.y);
/* 381:    */  }
/* 382:    */  
/* 388:    */  public void render(float x, float y)
/* 389:    */  {
/* 390:390 */    if ((this.sprite == null) && (this.defaultImageName != null)) {
/* 391:391 */      loadSystemParticleImage();
/* 392:    */    }
/* 393:    */    
/* 394:394 */    if (!this.visible) {
/* 395:395 */      return;
/* 396:    */    }
/* 397:    */    
/* 398:398 */    this.GL.glTranslatef(x, y, 0.0F);
/* 399:    */    
/* 400:400 */    if (this.blendingMode == 1) {
/* 401:401 */      this.GL.glBlendFunc(770, 1);
/* 402:    */    }
/* 403:403 */    if (usePoints()) {
/* 404:404 */      this.GL.glEnable(2832);
/* 405:405 */      TextureImpl.bindNone();
/* 406:    */    }
/* 407:    */    
/* 409:409 */    for (int emitterIdx = 0; emitterIdx < this.emitters.size(); emitterIdx++)
/* 410:    */    {
/* 412:412 */      ParticleEmitter emitter = (ParticleEmitter)this.emitters.get(emitterIdx);
/* 413:    */      
/* 414:414 */      if (emitter.isEnabled())
/* 415:    */      {
/* 419:419 */        if (emitter.useAdditive()) {
/* 420:420 */          this.GL.glBlendFunc(770, 1);
/* 421:    */        }
/* 422:    */        
/* 424:424 */        ParticlePool pool = (ParticlePool)this.particlesByEmitter.get(emitter);
/* 425:425 */        Image image = emitter.getImage();
/* 426:426 */        if (image == null) {
/* 427:427 */          image = this.sprite;
/* 428:    */        }
/* 429:    */        
/* 430:430 */        if ((!emitter.isOriented()) && (!emitter.usePoints(this))) {
/* 431:431 */          image.startUse();
/* 432:    */        }
/* 433:    */        
/* 434:434 */        for (int i = 0; i < pool.particles.length; i++)
/* 435:    */        {
/* 436:436 */          if (pool.particles[i].inUse()) {
/* 437:437 */            pool.particles[i].render();
/* 438:    */          }
/* 439:    */        }
/* 440:440 */        if ((!emitter.isOriented()) && (!emitter.usePoints(this))) {
/* 441:441 */          image.endUse();
/* 442:    */        }
/* 443:    */        
/* 445:445 */        if (emitter.useAdditive()) {
/* 446:446 */          this.GL.glBlendFunc(770, 771);
/* 447:    */        }
/* 448:    */      }
/* 449:    */    }
/* 450:450 */    if (usePoints()) {
/* 451:451 */      this.GL.glDisable(2832);
/* 452:    */    }
/* 453:453 */    if (this.blendingMode == 1) {
/* 454:454 */      this.GL.glBlendFunc(770, 771);
/* 455:    */    }
/* 456:    */    
/* 457:457 */    Color.white.bind();
/* 458:458 */    this.GL.glTranslatef(-x, -y, 0.0F);
/* 459:    */  }
/* 460:    */  
/* 463:    */  private void loadSystemParticleImage()
/* 464:    */  {
/* 465:465 */    AccessController.doPrivileged(new PrivilegedAction() {
/* 466:    */      public Object run() {
/* 467:    */        try {
/* 468:468 */          if (ParticleSystem.this.mask != null) {
/* 469:469 */            ParticleSystem.this.sprite = new Image(ParticleSystem.this.defaultImageName, ParticleSystem.this.mask);
/* 470:    */          } else {
/* 471:471 */            ParticleSystem.this.sprite = new Image(ParticleSystem.this.defaultImageName);
/* 472:    */          }
/* 473:    */        } catch (SlickException e) {
/* 474:474 */          Log.error(e);
/* 475:475 */          ParticleSystem.this.defaultImageName = null;
/* 476:    */        }
/* 477:477 */        return null;
/* 478:    */      }
/* 479:    */    });
/* 480:    */  }
/* 481:    */  
/* 486:    */  public void update(int delta)
/* 487:    */  {
/* 488:488 */    if ((this.sprite == null) && (this.defaultImageName != null)) {
/* 489:489 */      loadSystemParticleImage();
/* 490:    */    }
/* 491:    */    
/* 492:492 */    this.removeMe.clear();
/* 493:493 */    ArrayList emitters = new ArrayList(this.emitters);
/* 494:494 */    for (int i = 0; i < emitters.size(); i++) {
/* 495:495 */      ParticleEmitter emitter = (ParticleEmitter)emitters.get(i);
/* 496:496 */      if (emitter.isEnabled()) {
/* 497:497 */        emitter.update(this, delta);
/* 498:498 */        if ((this.removeCompletedEmitters) && 
/* 499:499 */          (emitter.completed())) {
/* 500:500 */          this.removeMe.add(emitter);
/* 501:501 */          this.particlesByEmitter.remove(emitter);
/* 502:    */        }
/* 503:    */      }
/* 504:    */    }
/* 505:    */    
/* 506:506 */    this.emitters.removeAll(this.removeMe);
/* 507:    */    
/* 508:508 */    this.pCount = 0;
/* 509:    */    
/* 510:510 */    if (!this.particlesByEmitter.isEmpty())
/* 511:    */    {
/* 512:512 */      Iterator it = this.particlesByEmitter.keySet().iterator();
/* 513:513 */      while (it.hasNext())
/* 514:    */      {
/* 515:515 */        ParticleEmitter emitter = (ParticleEmitter)it.next();
/* 516:516 */        if (emitter.isEnabled()) {
/* 517:517 */          ParticlePool pool = (ParticlePool)this.particlesByEmitter.get(emitter);
/* 518:518 */          for (int i = 0; i < pool.particles.length; i++) {
/* 519:519 */            if (pool.particles[i].life > 0.0F) {
/* 520:520 */              pool.particles[i].update(delta);
/* 521:521 */              this.pCount += 1;
/* 522:    */            }
/* 523:    */          }
/* 524:    */        }
/* 525:    */      }
/* 526:    */    }
/* 527:    */  }
/* 528:    */  
/* 533:    */  public int getParticleCount()
/* 534:    */  {
/* 535:535 */    return this.pCount;
/* 536:    */  }
/* 537:    */  
/* 546:    */  public Particle getNewParticle(ParticleEmitter emitter, float life)
/* 547:    */  {
/* 548:548 */    ParticlePool pool = (ParticlePool)this.particlesByEmitter.get(emitter);
/* 549:549 */    ArrayList available = pool.available;
/* 550:550 */    if (available.size() > 0)
/* 551:    */    {
/* 552:552 */      Particle p = (Particle)available.remove(available.size() - 1);
/* 553:553 */      p.init(emitter, life);
/* 554:554 */      p.setImage(this.sprite);
/* 555:    */      
/* 556:556 */      return p;
/* 557:    */    }
/* 558:    */    
/* 559:559 */    Log.warn("Ran out of particles (increase the limit)!");
/* 560:560 */    return this.dummy;
/* 561:    */  }
/* 562:    */  
/* 567:    */  public void release(Particle particle)
/* 568:    */  {
/* 569:569 */    if (particle != this.dummy)
/* 570:    */    {
/* 571:571 */      ParticlePool pool = (ParticlePool)this.particlesByEmitter.get(particle.getEmitter());
/* 572:572 */      pool.available.add(particle);
/* 573:    */    }
/* 574:    */  }
/* 575:    */  
/* 580:    */  public void releaseAll(ParticleEmitter emitter)
/* 581:    */  {
/* 582:582 */    if (!this.particlesByEmitter.isEmpty())
/* 583:    */    {
/* 584:584 */      Iterator it = this.particlesByEmitter.values().iterator();
/* 585:585 */      while (it.hasNext())
/* 586:    */      {
/* 587:587 */        ParticlePool pool = (ParticlePool)it.next();
/* 588:588 */        for (int i = 0; i < pool.particles.length; i++) {
/* 589:589 */          if ((pool.particles[i].inUse()) && 
/* 590:590 */            (pool.particles[i].getEmitter() == emitter)) {
/* 591:591 */            pool.particles[i].setLife(-1.0F);
/* 592:592 */            release(pool.particles[i]);
/* 593:    */          }
/* 594:    */        }
/* 595:    */      }
/* 596:    */    }
/* 597:    */  }
/* 598:    */  
/* 606:    */  public void moveAll(ParticleEmitter emitter, float x, float y)
/* 607:    */  {
/* 608:608 */    ParticlePool pool = (ParticlePool)this.particlesByEmitter.get(emitter);
/* 609:609 */    for (int i = 0; i < pool.particles.length; i++) {
/* 610:610 */      if (pool.particles[i].inUse()) {
/* 611:611 */        pool.particles[i].move(x, y);
/* 612:    */      }
/* 613:    */    }
/* 614:    */  }
/* 615:    */  
/* 624:    */  public ParticleSystem duplicate()
/* 625:    */    throws SlickException
/* 626:    */  {
/* 627:627 */    for (int i = 0; i < this.emitters.size(); i++) {
/* 628:628 */      if (!(this.emitters.get(i) instanceof ConfigurableEmitter)) {
/* 629:629 */        throw new SlickException("Only systems contianing configurable emitters can be duplicated");
/* 630:    */      }
/* 631:    */    }
/* 632:    */    
/* 633:633 */    ParticleSystem theCopy = null;
/* 634:    */    try {
/* 635:635 */      ByteArrayOutputStream bout = new ByteArrayOutputStream();
/* 636:636 */      ParticleIO.saveConfiguredSystem(bout, this);
/* 637:637 */      ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
/* 638:638 */      theCopy = ParticleIO.loadConfiguredSystem(bin);
/* 639:    */    } catch (IOException e) {
/* 640:640 */      Log.error("Failed to duplicate particle system");
/* 641:641 */      throw new SlickException("Unable to duplicated particle system", e);
/* 642:    */    }
/* 643:    */    
/* 644:644 */    return theCopy;
/* 645:    */  }
/* 646:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.particles.ParticleSystem
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */