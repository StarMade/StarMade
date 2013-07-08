/*   1:    */package org.newdawn.slick.particles;
/*   2:    */
/*   3:    */import java.io.ByteArrayInputStream;
/*   4:    */import java.io.ByteArrayOutputStream;
/*   5:    */import java.io.IOException;
/*   6:    */import java.util.ArrayList;
/*   7:    */import org.newdawn.slick.Color;
/*   8:    */import org.newdawn.slick.Image;
/*   9:    */import org.newdawn.slick.SlickException;
/*  10:    */import org.newdawn.slick.geom.Vector2f;
/*  11:    */import org.newdawn.slick.util.FastTrig;
/*  12:    */import org.newdawn.slick.util.Log;
/*  13:    */
/*  22:    */public class ConfigurableEmitter
/*  23:    */  implements ParticleEmitter
/*  24:    */{
/*  25: 25 */  private static String relativePath = "";
/*  26:    */  
/*  32:    */  public static void setRelativePath(String path)
/*  33:    */  {
/*  34: 34 */    if (!path.endsWith("/")) {
/*  35: 35 */      path = path + "/";
/*  36:    */    }
/*  37: 37 */    relativePath = path;
/*  38:    */  }
/*  39:    */  
/*  41: 41 */  public Range spawnInterval = new Range(100.0F, 100.0F, null);
/*  42:    */  
/*  43: 43 */  public Range spawnCount = new Range(5.0F, 5.0F, null);
/*  44:    */  
/*  45: 45 */  public Range initialLife = new Range(1000.0F, 1000.0F, null);
/*  46:    */  
/*  47: 47 */  public Range initialSize = new Range(10.0F, 10.0F, null);
/*  48:    */  
/*  49: 49 */  public Range xOffset = new Range(0.0F, 0.0F, null);
/*  50:    */  
/*  51: 51 */  public Range yOffset = new Range(0.0F, 0.0F, null);
/*  52:    */  
/*  53: 53 */  public RandomValue spread = new RandomValue(360.0F, null);
/*  54:    */  
/*  55: 55 */  public SimpleValue angularOffset = new SimpleValue(0.0F, null);
/*  56:    */  
/*  57: 57 */  public Range initialDistance = new Range(0.0F, 0.0F, null);
/*  58:    */  
/*  59: 59 */  public Range speed = new Range(50.0F, 50.0F, null);
/*  60:    */  
/*  61: 61 */  public SimpleValue growthFactor = new SimpleValue(0.0F, null);
/*  62:    */  
/*  63: 63 */  public SimpleValue gravityFactor = new SimpleValue(0.0F, null);
/*  64:    */  
/*  65: 65 */  public SimpleValue windFactor = new SimpleValue(0.0F, null);
/*  66:    */  
/*  67: 67 */  public Range length = new Range(1000.0F, 1000.0F, null);
/*  68:    */  
/*  73: 73 */  public ArrayList colors = new ArrayList();
/*  74:    */  
/*  75: 75 */  public SimpleValue startAlpha = new SimpleValue(255.0F, null);
/*  76:    */  
/*  77: 77 */  public SimpleValue endAlpha = new SimpleValue(0.0F, null);
/*  78:    */  
/*  80:    */  public LinearInterpolator alpha;
/*  81:    */  
/*  83:    */  public LinearInterpolator size;
/*  84:    */  
/*  85:    */  public LinearInterpolator velocity;
/*  86:    */  
/*  87:    */  public LinearInterpolator scaleY;
/*  88:    */  
/*  89: 89 */  public Range emitCount = new Range(1000.0F, 1000.0F, null);
/*  90:    */  
/*  91: 91 */  public int usePoints = 1;
/*  92:    */  
/*  94: 94 */  public boolean useOriented = false;
/*  95:    */  
/*  99: 99 */  public boolean useAdditive = false;
/* 100:    */  
/* 102:    */  public String name;
/* 103:    */  
/* 104:104 */  public String imageName = "";
/* 105:    */  
/* 107:    */  private Image image;
/* 108:    */  
/* 109:    */  private boolean updateImage;
/* 110:    */  
/* 111:111 */  private boolean enabled = true;
/* 112:    */  
/* 113:    */  private float x;
/* 114:    */  
/* 115:    */  private float y;
/* 116:    */  
/* 117:117 */  private int nextSpawn = 0;
/* 118:    */  
/* 120:    */  private int timeout;
/* 121:    */  
/* 123:    */  private int particleCount;
/* 124:    */  
/* 125:    */  private ParticleSystem engine;
/* 126:    */  
/* 127:    */  private int leftToEmit;
/* 128:    */  
/* 129:129 */  protected boolean wrapUp = false;
/* 130:    */  
/* 131:131 */  protected boolean completed = false;
/* 132:    */  
/* 134:    */  protected boolean adjust;
/* 135:    */  
/* 137:    */  protected float adjustx;
/* 138:    */  
/* 140:    */  protected float adjusty;
/* 141:    */  
/* 144:    */  public ConfigurableEmitter(String name)
/* 145:    */  {
/* 146:146 */    this.name = name;
/* 147:147 */    this.leftToEmit = ((int)this.emitCount.random());
/* 148:148 */    this.timeout = ((int)this.length.random());
/* 149:    */    
/* 150:150 */    this.colors.add(new ColorRecord(0.0F, Color.white));
/* 151:151 */    this.colors.add(new ColorRecord(1.0F, Color.red));
/* 152:    */    
/* 153:153 */    ArrayList curve = new ArrayList();
/* 154:154 */    curve.add(new Vector2f(0.0F, 0.0F));
/* 155:155 */    curve.add(new Vector2f(1.0F, 255.0F));
/* 156:156 */    this.alpha = new LinearInterpolator(curve, 0, 255);
/* 157:    */    
/* 158:158 */    curve = new ArrayList();
/* 159:159 */    curve.add(new Vector2f(0.0F, 0.0F));
/* 160:160 */    curve.add(new Vector2f(1.0F, 255.0F));
/* 161:161 */    this.size = new LinearInterpolator(curve, 0, 255);
/* 162:    */    
/* 163:163 */    curve = new ArrayList();
/* 164:164 */    curve.add(new Vector2f(0.0F, 0.0F));
/* 165:165 */    curve.add(new Vector2f(1.0F, 1.0F));
/* 166:166 */    this.velocity = new LinearInterpolator(curve, 0, 1);
/* 167:    */    
/* 168:168 */    curve = new ArrayList();
/* 169:169 */    curve.add(new Vector2f(0.0F, 0.0F));
/* 170:170 */    curve.add(new Vector2f(1.0F, 1.0F));
/* 171:171 */    this.scaleY = new LinearInterpolator(curve, 0, 1);
/* 172:    */  }
/* 173:    */  
/* 182:    */  public void setImageName(String imageName)
/* 183:    */  {
/* 184:184 */    if (imageName.length() == 0) {
/* 185:185 */      imageName = null;
/* 186:    */    }
/* 187:    */    
/* 188:188 */    this.imageName = imageName;
/* 189:189 */    if (imageName == null) {
/* 190:190 */      this.image = null;
/* 191:    */    } else {
/* 192:192 */      this.updateImage = true;
/* 193:    */    }
/* 194:    */  }
/* 195:    */  
/* 200:    */  public String getImageName()
/* 201:    */  {
/* 202:202 */    return this.imageName;
/* 203:    */  }
/* 204:    */  
/* 207:    */  public String toString()
/* 208:    */  {
/* 209:209 */    return "[" + this.name + "]";
/* 210:    */  }
/* 211:    */  
/* 219:    */  public void setPosition(float x, float y)
/* 220:    */  {
/* 221:221 */    setPosition(x, y, true);
/* 222:    */  }
/* 223:    */  
/* 233:    */  public void setPosition(float x, float y, boolean moveParticles)
/* 234:    */  {
/* 235:235 */    if (moveParticles) {
/* 236:236 */      this.adjust = true;
/* 237:237 */      this.adjustx -= this.x - x;
/* 238:238 */      this.adjusty -= this.y - y;
/* 239:    */    }
/* 240:240 */    this.x = x;
/* 241:241 */    this.y = y;
/* 242:    */  }
/* 243:    */  
/* 248:    */  public float getX()
/* 249:    */  {
/* 250:250 */    return this.x;
/* 251:    */  }
/* 252:    */  
/* 257:    */  public float getY()
/* 258:    */  {
/* 259:259 */    return this.y;
/* 260:    */  }
/* 261:    */  
/* 264:    */  public boolean isEnabled()
/* 265:    */  {
/* 266:266 */    return this.enabled;
/* 267:    */  }
/* 268:    */  
/* 271:    */  public void setEnabled(boolean enabled)
/* 272:    */  {
/* 273:273 */    this.enabled = enabled;
/* 274:    */  }
/* 275:    */  
/* 279:    */  public void update(ParticleSystem system, int delta)
/* 280:    */  {
/* 281:281 */    this.engine = system;
/* 282:    */    
/* 283:283 */    if (!this.adjust) {
/* 284:284 */      this.adjustx = 0.0F;
/* 285:285 */      this.adjusty = 0.0F;
/* 286:    */    } else {
/* 287:287 */      this.adjust = false;
/* 288:    */    }
/* 289:    */    
/* 290:290 */    if (this.updateImage) {
/* 291:291 */      this.updateImage = false;
/* 292:    */      try {
/* 293:293 */        this.image = new Image(relativePath + this.imageName);
/* 294:    */      } catch (SlickException e) {
/* 295:295 */        this.image = null;
/* 296:296 */        Log.error(e);
/* 297:    */      }
/* 298:    */    }
/* 299:    */    
/* 300:300 */    if ((this.wrapUp) || ((this.length.isEnabled()) && (this.timeout < 0)) || ((this.emitCount.isEnabled()) && (this.leftToEmit <= 0)))
/* 301:    */    {
/* 303:303 */      if (this.particleCount == 0) {
/* 304:304 */        this.completed = true;
/* 305:    */      }
/* 306:    */    }
/* 307:307 */    this.particleCount = 0;
/* 308:    */    
/* 309:309 */    if (this.wrapUp) {
/* 310:310 */      return;
/* 311:    */    }
/* 312:    */    
/* 313:313 */    if (this.length.isEnabled()) {
/* 314:314 */      if (this.timeout < 0) {
/* 315:315 */        return;
/* 316:    */      }
/* 317:317 */      this.timeout -= delta;
/* 318:    */    }
/* 319:319 */    if ((this.emitCount.isEnabled()) && 
/* 320:320 */      (this.leftToEmit <= 0)) {
/* 321:321 */      return;
/* 322:    */    }
/* 323:    */    
/* 325:325 */    this.nextSpawn -= delta;
/* 326:326 */    if (this.nextSpawn < 0) {
/* 327:327 */      this.nextSpawn = ((int)this.spawnInterval.random());
/* 328:328 */      int count = (int)this.spawnCount.random();
/* 329:    */      
/* 330:330 */      for (int i = 0; i < count; i++) {
/* 331:331 */        Particle p = system.getNewParticle(this, this.initialLife.random());
/* 332:332 */        p.setSize(this.initialSize.random());
/* 333:333 */        p.setPosition(this.x + this.xOffset.random(), this.y + this.yOffset.random());
/* 334:334 */        p.setVelocity(0.0F, 0.0F, 0.0F);
/* 335:    */        
/* 336:336 */        float dist = this.initialDistance.random();
/* 337:337 */        float power = this.speed.random();
/* 338:338 */        if ((dist != 0.0F) || (power != 0.0F)) {
/* 339:339 */          float s = this.spread.getValue(0.0F);
/* 340:340 */          float ang = s + this.angularOffset.getValue(0.0F) - this.spread.getValue() / 2.0F - 90.0F;
/* 341:    */          
/* 342:342 */          float xa = (float)FastTrig.cos(Math.toRadians(ang)) * dist;
/* 343:343 */          float ya = (float)FastTrig.sin(Math.toRadians(ang)) * dist;
/* 344:344 */          p.adjustPosition(xa, ya);
/* 345:    */          
/* 346:346 */          float xv = (float)FastTrig.cos(Math.toRadians(ang));
/* 347:347 */          float yv = (float)FastTrig.sin(Math.toRadians(ang));
/* 348:348 */          p.setVelocity(xv, yv, power * 0.001F);
/* 349:    */        }
/* 350:    */        
/* 351:351 */        if (this.image != null) {
/* 352:352 */          p.setImage(this.image);
/* 353:    */        }
/* 354:    */        
/* 355:355 */        ColorRecord start = (ColorRecord)this.colors.get(0);
/* 356:356 */        p.setColor(start.col.r, start.col.g, start.col.b, this.startAlpha.getValue(0.0F) / 255.0F);
/* 357:    */        
/* 358:358 */        p.setUsePoint(this.usePoints);
/* 359:359 */        p.setOriented(this.useOriented);
/* 360:    */        
/* 361:361 */        if (this.emitCount.isEnabled()) {
/* 362:362 */          this.leftToEmit -= 1;
/* 363:363 */          if (this.leftToEmit <= 0) {
/* 364:    */            break;
/* 365:    */          }
/* 366:    */        }
/* 367:    */      }
/* 368:    */    }
/* 369:    */  }
/* 370:    */  
/* 374:    */  public void updateParticle(Particle particle, int delta)
/* 375:    */  {
/* 376:376 */    this.particleCount += 1;
/* 377:    */    
/* 379:379 */    particle.x += this.adjustx;
/* 380:380 */    particle.y += this.adjusty;
/* 381:    */    
/* 382:382 */    particle.adjustVelocity(this.windFactor.getValue(0.0F) * 5.0E-005F * delta, this.gravityFactor.getValue(0.0F) * 5.0E-005F * delta);
/* 383:    */    
/* 385:385 */    float offset = particle.getLife() / particle.getOriginalLife();
/* 386:386 */    float inv = 1.0F - offset;
/* 387:387 */    float colOffset = 0.0F;
/* 388:388 */    float colInv = 1.0F;
/* 389:    */    
/* 390:390 */    Color startColor = null;
/* 391:391 */    Color endColor = null;
/* 392:392 */    for (int i = 0; i < this.colors.size() - 1; i++) {
/* 393:393 */      ColorRecord rec1 = (ColorRecord)this.colors.get(i);
/* 394:394 */      ColorRecord rec2 = (ColorRecord)this.colors.get(i + 1);
/* 395:    */      
/* 396:396 */      if ((inv >= rec1.pos) && (inv <= rec2.pos)) {
/* 397:397 */        startColor = rec1.col;
/* 398:398 */        endColor = rec2.col;
/* 399:    */        
/* 400:400 */        float step = rec2.pos - rec1.pos;
/* 401:401 */        colOffset = inv - rec1.pos;
/* 402:402 */        colOffset /= step;
/* 403:403 */        colOffset = 1.0F - colOffset;
/* 404:404 */        colInv = 1.0F - colOffset;
/* 405:    */      }
/* 406:    */    }
/* 407:    */    
/* 408:408 */    if (startColor != null) {
/* 409:409 */      float r = startColor.r * colOffset + endColor.r * colInv;
/* 410:410 */      float g = startColor.g * colOffset + endColor.g * colInv;
/* 411:411 */      float b = startColor.b * colOffset + endColor.b * colInv;
/* 412:    */      float a;
/* 413:    */      float a;
/* 414:414 */      if (this.alpha.isActive()) {
/* 415:415 */        a = this.alpha.getValue(inv) / 255.0F;
/* 416:    */      } else {
/* 417:417 */        a = this.startAlpha.getValue(0.0F) / 255.0F * offset + this.endAlpha.getValue(0.0F) / 255.0F * inv;
/* 418:    */      }
/* 419:    */      
/* 420:420 */      particle.setColor(r, g, b, a);
/* 421:    */    }
/* 422:    */    
/* 423:423 */    if (this.size.isActive()) {
/* 424:424 */      float s = this.size.getValue(inv);
/* 425:425 */      particle.setSize(s);
/* 426:    */    } else {
/* 427:427 */      particle.adjustSize(delta * this.growthFactor.getValue(0.0F) * 0.001F);
/* 428:    */    }
/* 429:    */    
/* 430:430 */    if (this.velocity.isActive()) {
/* 431:431 */      particle.setSpeed(this.velocity.getValue(inv));
/* 432:    */    }
/* 433:    */    
/* 434:434 */    if (this.scaleY.isActive()) {
/* 435:435 */      particle.setScaleY(this.scaleY.getValue(inv));
/* 436:    */    }
/* 437:    */  }
/* 438:    */  
/* 443:    */  public boolean completed()
/* 444:    */  {
/* 445:445 */    if (this.engine == null) {
/* 446:446 */      return false;
/* 447:    */    }
/* 448:    */    
/* 449:449 */    if (this.length.isEnabled()) {
/* 450:450 */      if (this.timeout > 0) {
/* 451:451 */        return false;
/* 452:    */      }
/* 453:453 */      return this.completed;
/* 454:    */    }
/* 455:455 */    if (this.emitCount.isEnabled()) {
/* 456:456 */      if (this.leftToEmit > 0) {
/* 457:457 */        return false;
/* 458:    */      }
/* 459:459 */      return this.completed;
/* 460:    */    }
/* 461:    */    
/* 462:462 */    if (this.wrapUp) {
/* 463:463 */      return this.completed;
/* 464:    */    }
/* 465:    */    
/* 466:466 */    return false;
/* 467:    */  }
/* 468:    */  
/* 471:    */  public void replay()
/* 472:    */  {
/* 473:473 */    reset();
/* 474:474 */    this.nextSpawn = 0;
/* 475:475 */    this.leftToEmit = ((int)this.emitCount.random());
/* 476:476 */    this.timeout = ((int)this.length.random());
/* 477:    */  }
/* 478:    */  
/* 481:    */  public void reset()
/* 482:    */  {
/* 483:483 */    this.completed = false;
/* 484:484 */    if (this.engine != null) {
/* 485:485 */      this.engine.releaseAll(this);
/* 486:    */    }
/* 487:    */  }
/* 488:    */  
/* 491:    */  public void replayCheck()
/* 492:    */  {
/* 493:493 */    if ((completed()) && 
/* 494:494 */      (this.engine != null) && 
/* 495:495 */      (this.engine.getParticleCount() == 0)) {
/* 496:496 */      replay();
/* 497:    */    }
/* 498:    */  }
/* 499:    */  
/* 506:    */  public ConfigurableEmitter duplicate()
/* 507:    */  {
/* 508:508 */    ConfigurableEmitter theCopy = null;
/* 509:    */    try {
/* 510:510 */      ByteArrayOutputStream bout = new ByteArrayOutputStream();
/* 511:511 */      ParticleIO.saveEmitter(bout, this);
/* 512:512 */      ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
/* 513:513 */      theCopy = ParticleIO.loadEmitter(bin);
/* 514:    */    } catch (IOException e) {
/* 515:515 */      Log.error("Slick: ConfigurableEmitter.duplicate(): caught exception " + e.toString());
/* 516:516 */      return null;
/* 517:    */    }
/* 518:518 */    return theCopy;
/* 519:    */  }
/* 520:    */  
/* 526:    */  public static abstract interface Value
/* 527:    */  {
/* 528:    */    public abstract float getValue(float paramFloat);
/* 529:    */  }
/* 530:    */  
/* 536:    */  public class SimpleValue
/* 537:    */    implements ConfigurableEmitter.Value
/* 538:    */  {
/* 539:    */    private float value;
/* 540:    */    
/* 545:    */    private float next;
/* 546:    */    
/* 552:    */    private SimpleValue(float value)
/* 553:    */    {
/* 554:554 */      this.value = value;
/* 555:    */    }
/* 556:    */    
/* 561:    */    public float getValue(float time)
/* 562:    */    {
/* 563:563 */      return this.value;
/* 564:    */    }
/* 565:    */    
/* 571:    */    public void setValue(float value)
/* 572:    */    {
/* 573:573 */      this.value = value;
/* 574:    */    }
/* 575:    */  }
/* 576:    */  
/* 582:    */  public class RandomValue
/* 583:    */    implements ConfigurableEmitter.Value
/* 584:    */  {
/* 585:    */    private float value;
/* 586:    */    
/* 591:    */    private RandomValue(float value)
/* 592:    */    {
/* 593:593 */      this.value = value;
/* 594:    */    }
/* 595:    */    
/* 600:    */    public float getValue(float time)
/* 601:    */    {
/* 602:602 */      return (float)(Math.random() * this.value);
/* 603:    */    }
/* 604:    */    
/* 610:    */    public void setValue(float value)
/* 611:    */    {
/* 612:612 */      this.value = value;
/* 613:    */    }
/* 614:    */    
/* 619:    */    public float getValue()
/* 620:    */    {
/* 621:621 */      return this.value;
/* 622:    */    }
/* 623:    */  }
/* 624:    */  
/* 628:    */  public class LinearInterpolator
/* 629:    */    implements ConfigurableEmitter.Value
/* 630:    */  {
/* 631:    */    private ArrayList curve;
/* 632:    */    
/* 635:    */    private boolean active;
/* 636:    */    
/* 639:    */    private int min;
/* 640:    */    
/* 642:    */    private int max;
/* 643:    */    
/* 646:    */    public LinearInterpolator(ArrayList curve, int min, int max)
/* 647:    */    {
/* 648:648 */      this.curve = curve;
/* 649:649 */      this.min = min;
/* 650:650 */      this.max = max;
/* 651:651 */      this.active = false;
/* 652:    */    }
/* 653:    */    
/* 658:    */    public void setCurve(ArrayList curve)
/* 659:    */    {
/* 660:660 */      this.curve = curve;
/* 661:    */    }
/* 662:    */    
/* 667:    */    public ArrayList getCurve()
/* 668:    */    {
/* 669:669 */      return this.curve;
/* 670:    */    }
/* 671:    */    
/* 678:    */    public float getValue(float t)
/* 679:    */    {
/* 680:680 */      Vector2f p0 = (Vector2f)this.curve.get(0);
/* 681:681 */      for (int i = 1; i < this.curve.size(); i++) {
/* 682:682 */        Vector2f p1 = (Vector2f)this.curve.get(i);
/* 683:    */        
/* 684:684 */        if ((t >= p0.getX()) && (t <= p1.getX()))
/* 685:    */        {
/* 686:686 */          float st = (t - p0.getX()) / (p1.getX() - p0.getX());
/* 687:    */          
/* 688:688 */          float r = p0.getY() + st * (p1.getY() - p0.getY());
/* 689:    */          
/* 693:693 */          return r;
/* 694:    */        }
/* 695:    */        
/* 696:696 */        p0 = p1;
/* 697:    */      }
/* 698:698 */      return 0.0F;
/* 699:    */    }
/* 700:    */    
/* 705:    */    public boolean isActive()
/* 706:    */    {
/* 707:707 */      return this.active;
/* 708:    */    }
/* 709:    */    
/* 714:    */    public void setActive(boolean active)
/* 715:    */    {
/* 716:716 */      this.active = active;
/* 717:    */    }
/* 718:    */    
/* 723:    */    public int getMax()
/* 724:    */    {
/* 725:725 */      return this.max;
/* 726:    */    }
/* 727:    */    
/* 732:    */    public void setMax(int max)
/* 733:    */    {
/* 734:734 */      this.max = max;
/* 735:    */    }
/* 736:    */    
/* 741:    */    public int getMin()
/* 742:    */    {
/* 743:743 */      return this.min;
/* 744:    */    }
/* 745:    */    
/* 750:    */    public void setMin(int min)
/* 751:    */    {
/* 752:752 */      this.min = min;
/* 753:    */    }
/* 754:    */  }
/* 755:    */  
/* 761:    */  public class ColorRecord
/* 762:    */  {
/* 763:    */    public float pos;
/* 764:    */    
/* 768:    */    public Color col;
/* 769:    */    
/* 774:    */    public ColorRecord(float pos, Color col)
/* 775:    */    {
/* 776:776 */      this.pos = pos;
/* 777:777 */      this.col = col;
/* 778:    */    }
/* 779:    */  }
/* 780:    */  
/* 788:    */  public void addColorPoint(float pos, Color col)
/* 789:    */  {
/* 790:790 */    this.colors.add(new ColorRecord(pos, col));
/* 791:    */  }
/* 792:    */  
/* 796:    */  public class Range
/* 797:    */  {
/* 798:    */    private float max;
/* 799:    */    
/* 801:    */    private float min;
/* 802:    */    
/* 804:804 */    private boolean enabled = false;
/* 805:    */    
/* 813:    */    private Range(float min, float max)
/* 814:    */    {
/* 815:815 */      this.min = min;
/* 816:816 */      this.max = max;
/* 817:    */    }
/* 818:    */    
/* 823:    */    public float random()
/* 824:    */    {
/* 825:825 */      return (float)(this.min + Math.random() * (this.max - this.min));
/* 826:    */    }
/* 827:    */    
/* 832:    */    public boolean isEnabled()
/* 833:    */    {
/* 834:834 */      return this.enabled;
/* 835:    */    }
/* 836:    */    
/* 842:    */    public void setEnabled(boolean enabled)
/* 843:    */    {
/* 844:844 */      this.enabled = enabled;
/* 845:    */    }
/* 846:    */    
/* 851:    */    public float getMax()
/* 852:    */    {
/* 853:853 */      return this.max;
/* 854:    */    }
/* 855:    */    
/* 861:    */    public void setMax(float max)
/* 862:    */    {
/* 863:863 */      this.max = max;
/* 864:    */    }
/* 865:    */    
/* 870:    */    public float getMin()
/* 871:    */    {
/* 872:872 */      return this.min;
/* 873:    */    }
/* 874:    */    
/* 880:    */    public void setMin(float min)
/* 881:    */    {
/* 882:882 */      this.min = min;
/* 883:    */    }
/* 884:    */  }
/* 885:    */  
/* 886:    */  public boolean useAdditive() {
/* 887:887 */    return this.useAdditive;
/* 888:    */  }
/* 889:    */  
/* 890:    */  public boolean isOriented() {
/* 891:891 */    return this.useOriented;
/* 892:    */  }
/* 893:    */  
/* 894:    */  public boolean usePoints(ParticleSystem system) {
/* 895:895 */    return ((this.usePoints == 1) && (system.usePoints())) || (this.usePoints == 2);
/* 896:    */  }
/* 897:    */  
/* 898:    */  public Image getImage()
/* 899:    */  {
/* 900:900 */    return this.image;
/* 901:    */  }
/* 902:    */  
/* 903:    */  public void wrapUp() {
/* 904:904 */    this.wrapUp = true;
/* 905:    */  }
/* 906:    */  
/* 907:    */  public void resetState() {
/* 908:908 */    this.wrapUp = false;
/* 909:909 */    replay();
/* 910:    */  }
/* 911:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.particles.ConfigurableEmitter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */