/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import org.lwjgl.Sys;
/*   5:    */import org.newdawn.slick.util.Log;
/*   6:    */
/*  13:    */public class Animation
/*  14:    */  implements Renderable
/*  15:    */{
/*  16: 16 */  private ArrayList frames = new ArrayList();
/*  17:    */  
/*  18: 18 */  private int currentFrame = -1;
/*  19:    */  
/*  20: 20 */  private long nextChange = 0L;
/*  21:    */  
/*  22: 22 */  private boolean stopped = false;
/*  23:    */  
/*  24:    */  private long timeLeft;
/*  25:    */  
/*  26: 26 */  private float speed = 1.0F;
/*  27:    */  
/*  28: 28 */  private int stopAt = -2;
/*  29:    */  
/*  30:    */  private long lastUpdate;
/*  31:    */  
/*  32: 32 */  private boolean firstUpdate = true;
/*  33:    */  
/*  34: 34 */  private boolean autoUpdate = true;
/*  35:    */  
/*  36: 36 */  private int direction = 1;
/*  37:    */  
/*  38:    */  private boolean pingPong;
/*  39:    */  
/*  40: 40 */  private boolean loop = true;
/*  41:    */  
/*  42: 42 */  private SpriteSheet spriteSheet = null;
/*  43:    */  
/*  46:    */  public Animation()
/*  47:    */  {
/*  48: 48 */    this(true);
/*  49:    */  }
/*  50:    */  
/*  56:    */  public Animation(Image[] frames, int duration)
/*  57:    */  {
/*  58: 58 */    this(frames, duration, true);
/*  59:    */  }
/*  60:    */  
/*  66:    */  public Animation(Image[] frames, int[] durations)
/*  67:    */  {
/*  68: 68 */    this(frames, durations, true);
/*  69:    */  }
/*  70:    */  
/*  76:    */  public Animation(boolean autoUpdate)
/*  77:    */  {
/*  78: 78 */    this.currentFrame = 0;
/*  79: 79 */    this.autoUpdate = autoUpdate;
/*  80:    */  }
/*  81:    */  
/*  89:    */  public Animation(Image[] frames, int duration, boolean autoUpdate)
/*  90:    */  {
/*  91: 91 */    for (int i = 0; i < frames.length; i++) {
/*  92: 92 */      addFrame(frames[i], duration);
/*  93:    */    }
/*  94: 94 */    this.currentFrame = 0;
/*  95: 95 */    this.autoUpdate = autoUpdate;
/*  96:    */  }
/*  97:    */  
/* 105:    */  public Animation(Image[] frames, int[] durations, boolean autoUpdate)
/* 106:    */  {
/* 107:107 */    this.autoUpdate = autoUpdate;
/* 108:108 */    if (frames.length != durations.length) {
/* 109:109 */      throw new RuntimeException("There must be one duration per frame");
/* 110:    */    }
/* 111:    */    
/* 112:112 */    for (int i = 0; i < frames.length; i++) {
/* 113:113 */      addFrame(frames[i], durations[i]);
/* 114:    */    }
/* 115:115 */    this.currentFrame = 0;
/* 116:    */  }
/* 117:    */  
/* 125:    */  public Animation(SpriteSheet frames, int duration)
/* 126:    */  {
/* 127:127 */    this(frames, 0, 0, frames.getHorizontalCount() - 1, frames.getVerticalCount() - 1, true, duration, true);
/* 128:    */  }
/* 129:    */  
/* 142:    */  public Animation(SpriteSheet frames, int x1, int y1, int x2, int y2, boolean horizontalScan, int duration, boolean autoUpdate)
/* 143:    */  {
/* 144:144 */    this.autoUpdate = autoUpdate;
/* 145:    */    
/* 146:146 */    if (!horizontalScan) {
/* 147:147 */      for (int x = x1; x <= x2; x++) {
/* 148:148 */        for (int y = y1; y <= y2; y++) {
/* 149:149 */          addFrame(frames.getSprite(x, y), duration);
/* 150:    */        }
/* 151:    */      }
/* 152:    */    } else {
/* 153:153 */      for (int y = y1; y <= y2; y++) {
/* 154:154 */        for (int x = x1; x <= x2; x++) {
/* 155:155 */          addFrame(frames.getSprite(x, y), duration);
/* 156:    */        }
/* 157:    */      }
/* 158:    */    }
/* 159:    */  }
/* 160:    */  
/* 166:    */  public Animation(SpriteSheet ss, int[] frames, int[] duration)
/* 167:    */  {
/* 168:168 */    this.spriteSheet = ss;
/* 169:169 */    int x = -1;
/* 170:170 */    int y = -1;
/* 171:    */    
/* 172:172 */    for (int i = 0; i < frames.length / 2; i++) {
/* 173:173 */      x = frames[(i * 2)];
/* 174:174 */      y = frames[(i * 2 + 1)];
/* 175:175 */      addFrame(duration[i], x, y);
/* 176:    */    }
/* 177:    */  }
/* 178:    */  
/* 184:    */  public void addFrame(int duration, int x, int y)
/* 185:    */  {
/* 186:186 */    if (duration == 0) {
/* 187:187 */      Log.error("Invalid duration: " + duration);
/* 188:188 */      throw new RuntimeException("Invalid duration: " + duration);
/* 189:    */    }
/* 190:    */    
/* 191:191 */    if (this.frames.isEmpty()) {
/* 192:192 */      this.nextChange = ((int)(duration / this.speed));
/* 193:    */    }
/* 194:    */    
/* 195:195 */    this.frames.add(new Frame(duration, x, y));
/* 196:196 */    this.currentFrame = 0;
/* 197:    */  }
/* 198:    */  
/* 205:    */  public void setAutoUpdate(boolean auto)
/* 206:    */  {
/* 207:207 */    this.autoUpdate = auto;
/* 208:    */  }
/* 209:    */  
/* 214:    */  public void setPingPong(boolean pingPong)
/* 215:    */  {
/* 216:216 */    this.pingPong = pingPong;
/* 217:    */  }
/* 218:    */  
/* 224:    */  public boolean isStopped()
/* 225:    */  {
/* 226:226 */    return this.stopped;
/* 227:    */  }
/* 228:    */  
/* 233:    */  public void setSpeed(float spd)
/* 234:    */  {
/* 235:235 */    if (spd > 0.0F)
/* 236:    */    {
/* 237:237 */      this.nextChange = (((float)this.nextChange * this.speed / spd));
/* 238:    */      
/* 239:239 */      this.speed = spd;
/* 240:    */    }
/* 241:    */  }
/* 242:    */  
/* 247:    */  public float getSpeed()
/* 248:    */  {
/* 249:249 */    return this.speed;
/* 250:    */  }
/* 251:    */  
/* 255:    */  public void stop()
/* 256:    */  {
/* 257:257 */    if (this.frames.size() == 0) {
/* 258:258 */      return;
/* 259:    */    }
/* 260:260 */    this.timeLeft = this.nextChange;
/* 261:261 */    this.stopped = true;
/* 262:    */  }
/* 263:    */  
/* 266:    */  public void start()
/* 267:    */  {
/* 268:268 */    if (!this.stopped) {
/* 269:269 */      return;
/* 270:    */    }
/* 271:271 */    if (this.frames.size() == 0) {
/* 272:272 */      return;
/* 273:    */    }
/* 274:274 */    this.stopped = false;
/* 275:275 */    this.nextChange = this.timeLeft;
/* 276:    */  }
/* 277:    */  
/* 280:    */  public void restart()
/* 281:    */  {
/* 282:282 */    if (this.frames.size() == 0) {
/* 283:283 */      return;
/* 284:    */    }
/* 285:285 */    this.stopped = false;
/* 286:286 */    this.currentFrame = 0;
/* 287:287 */    this.nextChange = ((int)(((Frame)this.frames.get(0)).duration / this.speed));
/* 288:288 */    this.firstUpdate = true;
/* 289:289 */    this.lastUpdate = 0L;
/* 290:    */  }
/* 291:    */  
/* 297:    */  public void addFrame(Image frame, int duration)
/* 298:    */  {
/* 299:299 */    if (duration == 0) {
/* 300:300 */      Log.error("Invalid duration: " + duration);
/* 301:301 */      throw new RuntimeException("Invalid duration: " + duration);
/* 302:    */    }
/* 303:    */    
/* 304:304 */    if (this.frames.isEmpty()) {
/* 305:305 */      this.nextChange = ((int)(duration / this.speed));
/* 306:    */    }
/* 307:    */    
/* 308:308 */    this.frames.add(new Frame(frame, duration));
/* 309:309 */    this.currentFrame = 0;
/* 310:    */  }
/* 311:    */  
/* 314:    */  public void draw()
/* 315:    */  {
/* 316:316 */    draw(0.0F, 0.0F);
/* 317:    */  }
/* 318:    */  
/* 324:    */  public void draw(float x, float y)
/* 325:    */  {
/* 326:326 */    draw(x, y, getWidth(), getHeight());
/* 327:    */  }
/* 328:    */  
/* 335:    */  public void draw(float x, float y, Color filter)
/* 336:    */  {
/* 337:337 */    draw(x, y, getWidth(), getHeight(), filter);
/* 338:    */  }
/* 339:    */  
/* 347:    */  public void draw(float x, float y, float width, float height)
/* 348:    */  {
/* 349:349 */    draw(x, y, width, height, Color.white);
/* 350:    */  }
/* 351:    */  
/* 360:    */  public void draw(float x, float y, float width, float height, Color col)
/* 361:    */  {
/* 362:362 */    if (this.frames.size() == 0) {
/* 363:363 */      return;
/* 364:    */    }
/* 365:    */    
/* 366:366 */    if (this.autoUpdate) {
/* 367:367 */      long now = getTime();
/* 368:368 */      long delta = now - this.lastUpdate;
/* 369:369 */      if (this.firstUpdate) {
/* 370:370 */        delta = 0L;
/* 371:371 */        this.firstUpdate = false;
/* 372:    */      }
/* 373:373 */      this.lastUpdate = now;
/* 374:374 */      nextFrame(delta);
/* 375:    */    }
/* 376:    */    
/* 377:377 */    Frame frame = (Frame)this.frames.get(this.currentFrame);
/* 378:378 */    frame.image.draw(x, y, width, height, col);
/* 379:    */  }
/* 380:    */  
/* 385:    */  public void renderInUse(int x, int y)
/* 386:    */  {
/* 387:387 */    if (this.frames.size() == 0) {
/* 388:388 */      return;
/* 389:    */    }
/* 390:    */    
/* 391:391 */    if (this.autoUpdate) {
/* 392:392 */      long now = getTime();
/* 393:393 */      long delta = now - this.lastUpdate;
/* 394:394 */      if (this.firstUpdate) {
/* 395:395 */        delta = 0L;
/* 396:396 */        this.firstUpdate = false;
/* 397:    */      }
/* 398:398 */      this.lastUpdate = now;
/* 399:399 */      nextFrame(delta);
/* 400:    */    }
/* 401:    */    
/* 402:402 */    Frame frame = (Frame)this.frames.get(this.currentFrame);
/* 403:403 */    this.spriteSheet.renderInUse(x, y, frame.x, frame.y);
/* 404:    */  }
/* 405:    */  
/* 410:    */  public int getWidth()
/* 411:    */  {
/* 412:412 */    return ((Frame)this.frames.get(this.currentFrame)).image.getWidth();
/* 413:    */  }
/* 414:    */  
/* 419:    */  public int getHeight()
/* 420:    */  {
/* 421:421 */    return ((Frame)this.frames.get(this.currentFrame)).image.getHeight();
/* 422:    */  }
/* 423:    */  
/* 431:    */  public void drawFlash(float x, float y, float width, float height)
/* 432:    */  {
/* 433:433 */    drawFlash(x, y, width, height, Color.white);
/* 434:    */  }
/* 435:    */  
/* 444:    */  public void drawFlash(float x, float y, float width, float height, Color col)
/* 445:    */  {
/* 446:446 */    if (this.frames.size() == 0) {
/* 447:447 */      return;
/* 448:    */    }
/* 449:    */    
/* 450:450 */    if (this.autoUpdate) {
/* 451:451 */      long now = getTime();
/* 452:452 */      long delta = now - this.lastUpdate;
/* 453:453 */      if (this.firstUpdate) {
/* 454:454 */        delta = 0L;
/* 455:455 */        this.firstUpdate = false;
/* 456:    */      }
/* 457:457 */      this.lastUpdate = now;
/* 458:458 */      nextFrame(delta);
/* 459:    */    }
/* 460:    */    
/* 461:461 */    Frame frame = (Frame)this.frames.get(this.currentFrame);
/* 462:462 */    frame.image.drawFlash(x, y, width, height, col);
/* 463:    */  }
/* 464:    */  
/* 467:    */  /**
/* 468:    */   * @deprecated
/* 469:    */   */
/* 470:    */  public void updateNoDraw()
/* 471:    */  {
/* 472:472 */    if (this.autoUpdate) {
/* 473:473 */      long now = getTime();
/* 474:474 */      long delta = now - this.lastUpdate;
/* 475:475 */      if (this.firstUpdate) {
/* 476:476 */        delta = 0L;
/* 477:477 */        this.firstUpdate = false;
/* 478:    */      }
/* 479:479 */      this.lastUpdate = now;
/* 480:480 */      nextFrame(delta);
/* 481:    */    }
/* 482:    */  }
/* 483:    */  
/* 490:    */  public void update(long delta)
/* 491:    */  {
/* 492:492 */    nextFrame(delta);
/* 493:    */  }
/* 494:    */  
/* 499:    */  public int getFrame()
/* 500:    */  {
/* 501:501 */    return this.currentFrame;
/* 502:    */  }
/* 503:    */  
/* 508:    */  public void setCurrentFrame(int index)
/* 509:    */  {
/* 510:510 */    this.currentFrame = index;
/* 511:    */  }
/* 512:    */  
/* 518:    */  public Image getImage(int index)
/* 519:    */  {
/* 520:520 */    Frame frame = (Frame)this.frames.get(index);
/* 521:521 */    return frame.image;
/* 522:    */  }
/* 523:    */  
/* 528:    */  public int getFrameCount()
/* 529:    */  {
/* 530:530 */    return this.frames.size();
/* 531:    */  }
/* 532:    */  
/* 537:    */  public Image getCurrentFrame()
/* 538:    */  {
/* 539:539 */    Frame frame = (Frame)this.frames.get(this.currentFrame);
/* 540:540 */    return frame.image;
/* 541:    */  }
/* 542:    */  
/* 547:    */  private void nextFrame(long delta)
/* 548:    */  {
/* 549:549 */    if (this.stopped) {
/* 550:550 */      return;
/* 551:    */    }
/* 552:552 */    if (this.frames.size() == 0) {
/* 553:553 */      return;
/* 554:    */    }
/* 555:    */    
/* 556:556 */    this.nextChange -= delta;
/* 557:    */    
/* 558:558 */    while ((this.nextChange < 0L) && (!this.stopped)) {
/* 559:559 */      if (this.currentFrame == this.stopAt) {
/* 560:560 */        this.stopped = true;
/* 561:561 */        break;
/* 562:    */      }
/* 563:563 */      if ((this.currentFrame == this.frames.size() - 1) && (!this.loop) && (!this.pingPong)) {
/* 564:564 */        this.stopped = true;
/* 565:565 */        break;
/* 566:    */      }
/* 567:567 */      this.currentFrame = ((this.currentFrame + this.direction) % this.frames.size());
/* 568:    */      
/* 569:569 */      if (this.pingPong) {
/* 570:570 */        if (this.currentFrame <= 0) {
/* 571:571 */          this.currentFrame = 0;
/* 572:572 */          this.direction = 1;
/* 573:573 */          if (!this.loop) {
/* 574:574 */            this.stopped = true;
/* 575:575 */            break;
/* 576:    */          }
/* 577:    */        }
/* 578:578 */        else if (this.currentFrame >= this.frames.size() - 1) {
/* 579:579 */          this.currentFrame = (this.frames.size() - 1);
/* 580:580 */          this.direction = -1;
/* 581:    */        }
/* 582:    */      }
/* 583:583 */      int realDuration = (int)(((Frame)this.frames.get(this.currentFrame)).duration / this.speed);
/* 584:584 */      this.nextChange += realDuration;
/* 585:    */    }
/* 586:    */  }
/* 587:    */  
/* 592:    */  public void setLooping(boolean loop)
/* 593:    */  {
/* 594:594 */    this.loop = loop;
/* 595:    */  }
/* 596:    */  
/* 601:    */  private long getTime()
/* 602:    */  {
/* 603:603 */    return Sys.getTime() * 1000L / Sys.getTimerResolution();
/* 604:    */  }
/* 605:    */  
/* 611:    */  public void stopAt(int frameIndex)
/* 612:    */  {
/* 613:613 */    this.stopAt = frameIndex;
/* 614:    */  }
/* 615:    */  
/* 621:    */  public int getDuration(int index)
/* 622:    */  {
/* 623:623 */    return ((Frame)this.frames.get(index)).duration;
/* 624:    */  }
/* 625:    */  
/* 631:    */  public void setDuration(int index, int duration)
/* 632:    */  {
/* 633:633 */    ((Frame)this.frames.get(index)).duration = duration;
/* 634:    */  }
/* 635:    */  
/* 640:    */  public int[] getDurations()
/* 641:    */  {
/* 642:642 */    int[] durations = new int[this.frames.size()];
/* 643:643 */    for (int i = 0; i < this.frames.size(); i++) {
/* 644:644 */      durations[i] = getDuration(i);
/* 645:    */    }
/* 646:    */    
/* 647:647 */    return durations;
/* 648:    */  }
/* 649:    */  
/* 653:    */  public String toString()
/* 654:    */  {
/* 655:655 */    String res = "[Animation (" + this.frames.size() + ") ";
/* 656:656 */    for (int i = 0; i < this.frames.size(); i++) {
/* 657:657 */      Frame frame = (Frame)this.frames.get(i);
/* 658:658 */      res = res + frame.duration + ",";
/* 659:    */    }
/* 660:    */    
/* 661:661 */    res = res + "]";
/* 662:662 */    return res;
/* 663:    */  }
/* 664:    */  
/* 670:    */  public Animation copy()
/* 671:    */  {
/* 672:672 */    Animation copy = new Animation();
/* 673:    */    
/* 674:674 */    copy.spriteSheet = this.spriteSheet;
/* 675:675 */    copy.frames = this.frames;
/* 676:676 */    copy.autoUpdate = this.autoUpdate;
/* 677:677 */    copy.direction = this.direction;
/* 678:678 */    copy.loop = this.loop;
/* 679:679 */    copy.pingPong = this.pingPong;
/* 680:680 */    copy.speed = this.speed;
/* 681:    */    
/* 682:682 */    return copy;
/* 683:    */  }
/* 684:    */  
/* 688:    */  private class Frame
/* 689:    */  {
/* 690:    */    public Image image;
/* 691:    */    
/* 693:    */    public int duration;
/* 694:    */    
/* 696:696 */    public int x = -1;
/* 697:    */    
/* 698:698 */    public int y = -1;
/* 699:    */    
/* 705:    */    public Frame(Image image, int duration)
/* 706:    */    {
/* 707:707 */      this.image = image;
/* 708:708 */      this.duration = duration;
/* 709:    */    }
/* 710:    */    
/* 716:    */    public Frame(int duration, int x, int y)
/* 717:    */    {
/* 718:718 */      this.image = Animation.this.spriteSheet.getSubImage(x, y);
/* 719:719 */      this.duration = duration;
/* 720:720 */      this.x = x;
/* 721:721 */      this.y = y;
/* 722:    */    }
/* 723:    */  }
/* 724:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.Animation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */