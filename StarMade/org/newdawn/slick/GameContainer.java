/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.util.Properties;
/*   5:    */import org.lwjgl.LWJGLException;
/*   6:    */import org.lwjgl.Sys;
/*   7:    */import org.lwjgl.input.Cursor;
/*   8:    */import org.lwjgl.opengl.Display;
/*   9:    */import org.lwjgl.opengl.Drawable;
/*  10:    */import org.lwjgl.opengl.Pbuffer;
/*  11:    */import org.lwjgl.opengl.PixelFormat;
/*  12:    */import org.newdawn.slick.gui.GUIContext;
/*  13:    */import org.newdawn.slick.openal.SoundStore;
/*  14:    */import org.newdawn.slick.opengl.CursorLoader;
/*  15:    */import org.newdawn.slick.opengl.ImageData;
/*  16:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*  17:    */import org.newdawn.slick.opengl.renderer.SGL;
/*  18:    */import org.newdawn.slick.util.Log;
/*  19:    */import org.newdawn.slick.util.ResourceLoader;
/*  20:    */
/*  27:    */public abstract class GameContainer
/*  28:    */  implements GUIContext
/*  29:    */{
/*  30: 30 */  protected static SGL GL = ;
/*  31:    */  
/*  33:    */  protected static Drawable SHARED_DRAWABLE;
/*  34:    */  
/*  35:    */  protected long lastFrame;
/*  36:    */  
/*  37:    */  protected long lastFPS;
/*  38:    */  
/*  39:    */  protected int recordedFPS;
/*  40:    */  
/*  41:    */  protected int fps;
/*  42:    */  
/*  43: 43 */  protected boolean running = true;
/*  44:    */  
/*  46:    */  protected int width;
/*  47:    */  
/*  49:    */  protected int height;
/*  50:    */  
/*  52:    */  protected Game game;
/*  53:    */  
/*  54:    */  private Font defaultFont;
/*  55:    */  
/*  56:    */  private Graphics graphics;
/*  57:    */  
/*  58:    */  protected Input input;
/*  59:    */  
/*  60: 60 */  protected int targetFPS = -1;
/*  61:    */  
/*  62: 62 */  private boolean showFPS = true;
/*  63:    */  
/*  64: 64 */  protected long minimumLogicInterval = 1L;
/*  65:    */  
/*  66:    */  protected long storedDelta;
/*  67:    */  
/*  68: 68 */  protected long maximumLogicInterval = 0L;
/*  69:    */  
/*  70:    */  protected Game lastGame;
/*  71:    */  
/*  72: 72 */  protected boolean clearEachFrame = true;
/*  73:    */  
/*  75:    */  protected boolean paused;
/*  76:    */  
/*  77: 77 */  protected boolean forceExit = true;
/*  78:    */  
/*  80:    */  protected boolean vsync;
/*  81:    */  
/*  83:    */  protected boolean smoothDeltas;
/*  84:    */  
/*  86:    */  protected int samples;
/*  87:    */  
/*  89:    */  protected boolean supportsMultiSample;
/*  90:    */  
/*  92:    */  protected boolean alwaysRender;
/*  93:    */  
/*  94:    */  protected static boolean stencil;
/*  95:    */  
/*  97:    */  protected GameContainer(Game game)
/*  98:    */  {
/*  99: 99 */    this.game = game;
/* 100:100 */    this.lastFrame = getTime();
/* 101:    */    
/* 102:102 */    getBuildVersion();
/* 103:103 */    Log.checkVerboseLogSetting();
/* 104:    */  }
/* 105:    */  
/* 106:    */  public static void enableStencil() {
/* 107:107 */    stencil = true;
/* 108:    */  }
/* 109:    */  
/* 114:    */  public void setDefaultFont(Font font)
/* 115:    */  {
/* 116:116 */    if (font != null) {
/* 117:117 */      this.defaultFont = font;
/* 118:    */    } else {
/* 119:119 */      Log.warn("Please provide a non null font");
/* 120:    */    }
/* 121:    */  }
/* 122:    */  
/* 128:    */  public void setMultiSample(int samples)
/* 129:    */  {
/* 130:130 */    this.samples = samples;
/* 131:    */  }
/* 132:    */  
/* 137:    */  public boolean supportsMultiSample()
/* 138:    */  {
/* 139:139 */    return this.supportsMultiSample;
/* 140:    */  }
/* 141:    */  
/* 147:    */  public int getSamples()
/* 148:    */  {
/* 149:149 */    return this.samples;
/* 150:    */  }
/* 151:    */  
/* 157:    */  public void setForceExit(boolean forceExit)
/* 158:    */  {
/* 159:159 */    this.forceExit = forceExit;
/* 160:    */  }
/* 161:    */  
/* 168:    */  public void setSmoothDeltas(boolean smoothDeltas)
/* 169:    */  {
/* 170:170 */    this.smoothDeltas = smoothDeltas;
/* 171:    */  }
/* 172:    */  
/* 177:    */  public boolean isFullscreen()
/* 178:    */  {
/* 179:179 */    return false;
/* 180:    */  }
/* 181:    */  
/* 186:    */  public float getAspectRatio()
/* 187:    */  {
/* 188:188 */    return getWidth() / getHeight();
/* 189:    */  }
/* 190:    */  
/* 196:    */  public void setFullscreen(boolean fullscreen)
/* 197:    */    throws SlickException
/* 198:    */  {}
/* 199:    */  
/* 204:    */  public static void enableSharedContext()
/* 205:    */    throws SlickException
/* 206:    */  {
/* 207:    */    try
/* 208:    */    {
/* 209:209 */      SHARED_DRAWABLE = new Pbuffer(64, 64, new PixelFormat(8, 0, 0), null);
/* 210:    */    } catch (LWJGLException e) {
/* 211:211 */      throw new SlickException("Unable to create the pbuffer used for shard context, buffers not supported", e);
/* 212:    */    }
/* 213:    */  }
/* 214:    */  
/* 219:    */  public static Drawable getSharedContext()
/* 220:    */  {
/* 221:221 */    return SHARED_DRAWABLE;
/* 222:    */  }
/* 223:    */  
/* 230:    */  public void setClearEachFrame(boolean clear)
/* 231:    */  {
/* 232:232 */    this.clearEachFrame = clear;
/* 233:    */  }
/* 234:    */  
/* 239:    */  public void reinit()
/* 240:    */    throws SlickException
/* 241:    */  {}
/* 242:    */  
/* 246:    */  public void pause()
/* 247:    */  {
/* 248:248 */    setPaused(true);
/* 249:    */  }
/* 250:    */  
/* 254:    */  public void resume()
/* 255:    */  {
/* 256:256 */    setPaused(false);
/* 257:    */  }
/* 258:    */  
/* 263:    */  public boolean isPaused()
/* 264:    */  {
/* 265:265 */    return this.paused;
/* 266:    */  }
/* 267:    */  
/* 274:    */  public void setPaused(boolean paused)
/* 275:    */  {
/* 276:276 */    this.paused = paused;
/* 277:    */  }
/* 278:    */  
/* 283:    */  public boolean getAlwaysRender()
/* 284:    */  {
/* 285:285 */    return this.alwaysRender;
/* 286:    */  }
/* 287:    */  
/* 292:    */  public void setAlwaysRender(boolean alwaysRender)
/* 293:    */  {
/* 294:294 */    this.alwaysRender = alwaysRender;
/* 295:    */  }
/* 296:    */  
/* 300:    */  public static int getBuildVersion()
/* 301:    */  {
/* 302:    */    try
/* 303:    */    {
/* 304:304 */      Properties props = new Properties();
/* 305:305 */      props.load(ResourceLoader.getResourceAsStream("version"));
/* 306:    */      
/* 307:307 */      int build = Integer.parseInt(props.getProperty("build"));
/* 308:308 */      Log.info("Slick Build #" + build);
/* 309:    */      
/* 310:310 */      return build;
/* 311:    */    } catch (Exception e) {
/* 312:312 */      Log.error("Unable to determine Slick build number"); }
/* 313:313 */    return -1;
/* 314:    */  }
/* 315:    */  
/* 321:    */  public Font getDefaultFont()
/* 322:    */  {
/* 323:323 */    return this.defaultFont;
/* 324:    */  }
/* 325:    */  
/* 330:    */  public boolean isSoundOn()
/* 331:    */  {
/* 332:332 */    return SoundStore.get().soundsOn();
/* 333:    */  }
/* 334:    */  
/* 339:    */  public boolean isMusicOn()
/* 340:    */  {
/* 341:341 */    return SoundStore.get().musicOn();
/* 342:    */  }
/* 343:    */  
/* 348:    */  public void setMusicOn(boolean on)
/* 349:    */  {
/* 350:350 */    SoundStore.get().setMusicOn(on);
/* 351:    */  }
/* 352:    */  
/* 357:    */  public void setSoundOn(boolean on)
/* 358:    */  {
/* 359:359 */    SoundStore.get().setSoundsOn(on);
/* 360:    */  }
/* 361:    */  
/* 365:    */  public float getMusicVolume()
/* 366:    */  {
/* 367:367 */    return SoundStore.get().getMusicVolume();
/* 368:    */  }
/* 369:    */  
/* 373:    */  public float getSoundVolume()
/* 374:    */  {
/* 375:375 */    return SoundStore.get().getSoundVolume();
/* 376:    */  }
/* 377:    */  
/* 381:    */  public void setSoundVolume(float volume)
/* 382:    */  {
/* 383:383 */    SoundStore.get().setSoundVolume(volume);
/* 384:    */  }
/* 385:    */  
/* 389:    */  public void setMusicVolume(float volume)
/* 390:    */  {
/* 391:391 */    SoundStore.get().setMusicVolume(volume);
/* 392:    */  }
/* 393:    */  
/* 399:    */  public abstract int getScreenWidth();
/* 400:    */  
/* 406:    */  public abstract int getScreenHeight();
/* 407:    */  
/* 412:    */  public int getWidth()
/* 413:    */  {
/* 414:414 */    return this.width;
/* 415:    */  }
/* 416:    */  
/* 421:    */  public int getHeight()
/* 422:    */  {
/* 423:423 */    return this.height;
/* 424:    */  }
/* 425:    */  
/* 433:    */  public abstract void setIcon(String paramString)
/* 434:    */    throws SlickException;
/* 435:    */  
/* 442:    */  public abstract void setIcons(String[] paramArrayOfString)
/* 443:    */    throws SlickException;
/* 444:    */  
/* 451:    */  public long getTime()
/* 452:    */  {
/* 453:453 */    return Sys.getTime() * 1000L / Sys.getTimerResolution();
/* 454:    */  }
/* 455:    */  
/* 460:    */  public void sleep(int milliseconds)
/* 461:    */  {
/* 462:462 */    long target = getTime() + milliseconds;
/* 463:463 */    while (getTime() < target) {
/* 464:464 */      try { Thread.sleep(1L);
/* 465:    */      }
/* 466:    */      catch (Exception e) {}
/* 467:    */    }
/* 468:    */  }
/* 469:    */  
/* 480:    */  public abstract void setMouseCursor(String paramString, int paramInt1, int paramInt2)
/* 481:    */    throws SlickException;
/* 482:    */  
/* 492:    */  public abstract void setMouseCursor(ImageData paramImageData, int paramInt1, int paramInt2)
/* 493:    */    throws SlickException;
/* 494:    */  
/* 504:    */  public abstract void setMouseCursor(Image paramImage, int paramInt1, int paramInt2)
/* 505:    */    throws SlickException;
/* 506:    */  
/* 516:    */  public abstract void setMouseCursor(Cursor paramCursor, int paramInt1, int paramInt2)
/* 517:    */    throws SlickException;
/* 518:    */  
/* 528:    */  public void setAnimatedMouseCursor(String ref, int x, int y, int width, int height, int[] cursorDelays)
/* 529:    */    throws SlickException
/* 530:    */  {
/* 531:    */    try
/* 532:    */    {
/* 533:533 */      Cursor cursor = CursorLoader.get().getAnimatedCursor(ref, x, y, width, height, cursorDelays);
/* 534:534 */      setMouseCursor(cursor, x, y);
/* 535:    */    } catch (IOException e) {
/* 536:536 */      throw new SlickException("Failed to set mouse cursor", e);
/* 537:    */    } catch (LWJGLException e) {
/* 538:538 */      throw new SlickException("Failed to set mouse cursor", e);
/* 539:    */    }
/* 540:    */  }
/* 541:    */  
/* 546:    */  public abstract void setDefaultMouseCursor();
/* 547:    */  
/* 552:    */  public Input getInput()
/* 553:    */  {
/* 554:554 */    return this.input;
/* 555:    */  }
/* 556:    */  
/* 561:    */  public int getFPS()
/* 562:    */  {
/* 563:563 */    return this.recordedFPS;
/* 564:    */  }
/* 565:    */  
/* 571:    */  public abstract void setMouseGrabbed(boolean paramBoolean);
/* 572:    */  
/* 578:    */  public abstract boolean isMouseGrabbed();
/* 579:    */  
/* 585:    */  protected int getDelta()
/* 586:    */  {
/* 587:587 */    long time = getTime();
/* 588:588 */    int delta = (int)(time - this.lastFrame);
/* 589:589 */    this.lastFrame = time;
/* 590:    */    
/* 591:591 */    return delta;
/* 592:    */  }
/* 593:    */  
/* 596:    */  protected void updateFPS()
/* 597:    */  {
/* 598:598 */    if (getTime() - this.lastFPS > 1000L) {
/* 599:599 */      this.lastFPS = getTime();
/* 600:600 */      this.recordedFPS = this.fps;
/* 601:601 */      this.fps = 0;
/* 602:    */    }
/* 603:603 */    this.fps += 1;
/* 604:    */  }
/* 605:    */  
/* 612:    */  public void setMinimumLogicUpdateInterval(int interval)
/* 613:    */  {
/* 614:614 */    this.minimumLogicInterval = interval;
/* 615:    */  }
/* 616:    */  
/* 623:    */  public void setMaximumLogicUpdateInterval(int interval)
/* 624:    */  {
/* 625:625 */    this.maximumLogicInterval = interval;
/* 626:    */  }
/* 627:    */  
/* 632:    */  protected void updateAndRender(int delta)
/* 633:    */    throws SlickException
/* 634:    */  {
/* 635:635 */    if ((this.smoothDeltas) && 
/* 636:636 */      (getFPS() != 0)) {
/* 637:637 */      delta = 1000 / getFPS();
/* 638:    */    }
/* 639:    */    
/* 641:641 */    this.input.poll(this.width, this.height);
/* 642:    */    
/* 643:643 */    Music.poll(delta);
/* 644:644 */    if (!this.paused) {
/* 645:645 */      this.storedDelta += delta;
/* 646:    */      
/* 647:647 */      if (this.storedDelta >= this.minimumLogicInterval) {
/* 648:    */        try {
/* 649:649 */          if (this.maximumLogicInterval != 0L) {
/* 650:650 */            long cycles = this.storedDelta / this.maximumLogicInterval;
/* 651:651 */            for (int i = 0; i < cycles; i++) {
/* 652:652 */              this.game.update(this, (int)this.maximumLogicInterval);
/* 653:    */            }
/* 654:    */            
/* 655:655 */            int remainder = (int)(this.storedDelta % this.maximumLogicInterval);
/* 656:656 */            if (remainder > this.minimumLogicInterval) {
/* 657:657 */              this.game.update(this, (int)(remainder % this.maximumLogicInterval));
/* 658:658 */              this.storedDelta = 0L;
/* 659:    */            } else {
/* 660:660 */              this.storedDelta = remainder;
/* 661:    */            }
/* 662:    */          } else {
/* 663:663 */            this.game.update(this, (int)this.storedDelta);
/* 664:664 */            this.storedDelta = 0L;
/* 665:    */          }
/* 666:    */        }
/* 667:    */        catch (Throwable e) {
/* 668:668 */          Log.error(e);
/* 669:669 */          throw new SlickException("Game.update() failure - check the game code.");
/* 670:    */        }
/* 671:    */      }
/* 672:    */    } else {
/* 673:673 */      this.game.update(this, 0);
/* 674:    */    }
/* 675:    */    
/* 676:676 */    if ((hasFocus()) || (getAlwaysRender())) {
/* 677:677 */      if (this.clearEachFrame) {
/* 678:678 */        GL.glClear(16640);
/* 679:    */      }
/* 680:    */      
/* 681:681 */      GL.glLoadIdentity();
/* 682:    */      
/* 683:683 */      this.graphics.resetTransform();
/* 684:684 */      this.graphics.resetFont();
/* 685:685 */      this.graphics.resetLineWidth();
/* 686:686 */      this.graphics.setAntiAlias(false);
/* 687:    */      try {
/* 688:688 */        this.game.render(this, this.graphics);
/* 689:    */      } catch (Throwable e) {
/* 690:690 */        Log.error(e);
/* 691:691 */        throw new SlickException("Game.render() failure - check the game code.");
/* 692:    */      }
/* 693:693 */      this.graphics.resetTransform();
/* 694:    */      
/* 695:695 */      if (this.showFPS) {
/* 696:696 */        this.defaultFont.drawString(10.0F, 10.0F, "FPS: " + this.recordedFPS);
/* 697:    */      }
/* 698:    */      
/* 699:699 */      GL.flush();
/* 700:    */    }
/* 701:    */    
/* 702:702 */    if (this.targetFPS != -1) {
/* 703:703 */      Display.sync(this.targetFPS);
/* 704:    */    }
/* 705:    */  }
/* 706:    */  
/* 713:    */  public void setUpdateOnlyWhenVisible(boolean updateOnlyWhenVisible) {}
/* 714:    */  
/* 720:    */  public boolean isUpdatingOnlyWhenVisible()
/* 721:    */  {
/* 722:722 */    return true;
/* 723:    */  }
/* 724:    */  
/* 727:    */  protected void initGL()
/* 728:    */  {
/* 729:729 */    Log.info("Starting display " + this.width + "x" + this.height);
/* 730:730 */    GL.initDisplay(this.width, this.height);
/* 731:    */    
/* 732:732 */    if (this.input == null) {
/* 733:733 */      this.input = new Input(this.height);
/* 734:    */    }
/* 735:735 */    this.input.init(this.height);
/* 736:    */    
/* 738:738 */    if ((this.game instanceof InputListener)) {
/* 739:739 */      this.input.removeListener((InputListener)this.game);
/* 740:740 */      this.input.addListener((InputListener)this.game);
/* 741:    */    }
/* 742:    */    
/* 743:743 */    if (this.graphics != null) {
/* 744:744 */      this.graphics.setDimensions(getWidth(), getHeight());
/* 745:    */    }
/* 746:746 */    this.lastGame = this.game;
/* 747:    */  }
/* 748:    */  
/* 752:    */  protected void initSystem()
/* 753:    */    throws SlickException
/* 754:    */  {
/* 755:755 */    initGL();
/* 756:756 */    setMusicVolume(1.0F);
/* 757:757 */    setSoundVolume(1.0F);
/* 758:    */    
/* 759:759 */    this.graphics = new Graphics(this.width, this.height);
/* 760:760 */    this.defaultFont = this.graphics.getFont();
/* 761:    */  }
/* 762:    */  
/* 765:    */  protected void enterOrtho()
/* 766:    */  {
/* 767:767 */    enterOrtho(this.width, this.height);
/* 768:    */  }
/* 769:    */  
/* 774:    */  public void setShowFPS(boolean show)
/* 775:    */  {
/* 776:776 */    this.showFPS = show;
/* 777:    */  }
/* 778:    */  
/* 783:    */  public boolean isShowingFPS()
/* 784:    */  {
/* 785:785 */    return this.showFPS;
/* 786:    */  }
/* 787:    */  
/* 792:    */  public void setTargetFrameRate(int fps)
/* 793:    */  {
/* 794:794 */    this.targetFPS = fps;
/* 795:    */  }
/* 796:    */  
/* 802:    */  public void setVSync(boolean vsync)
/* 803:    */  {
/* 804:804 */    this.vsync = vsync;
/* 805:805 */    Display.setVSyncEnabled(vsync);
/* 806:    */  }
/* 807:    */  
/* 812:    */  public boolean isVSyncRequested()
/* 813:    */  {
/* 814:814 */    return this.vsync;
/* 815:    */  }
/* 816:    */  
/* 821:    */  protected boolean running()
/* 822:    */  {
/* 823:823 */    return this.running;
/* 824:    */  }
/* 825:    */  
/* 830:    */  public void setVerbose(boolean verbose)
/* 831:    */  {
/* 832:832 */    Log.setVerbose(verbose);
/* 833:    */  }
/* 834:    */  
/* 837:    */  public void exit()
/* 838:    */  {
/* 839:839 */    this.running = false;
/* 840:    */  }
/* 841:    */  
/* 847:    */  public abstract boolean hasFocus();
/* 848:    */  
/* 854:    */  public Graphics getGraphics()
/* 855:    */  {
/* 856:856 */    return this.graphics;
/* 857:    */  }
/* 858:    */  
/* 864:    */  protected void enterOrtho(int xsize, int ysize)
/* 865:    */  {
/* 866:866 */    GL.enterOrtho(xsize, ysize);
/* 867:    */  }
/* 868:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.GameContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */