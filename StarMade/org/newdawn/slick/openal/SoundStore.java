/*   1:    */package org.newdawn.slick.openal;
/*   2:    */
/*   3:    */import java.io.BufferedInputStream;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.InputStream;
/*   6:    */import java.net.URL;
/*   7:    */import java.nio.FloatBuffer;
/*   8:    */import java.nio.IntBuffer;
/*   9:    */import java.security.AccessController;
/*  10:    */import java.security.PrivilegedAction;
/*  11:    */import java.util.HashMap;
/*  12:    */import org.lwjgl.BufferUtils;
/*  13:    */import org.lwjgl.Sys;
/*  14:    */import org.lwjgl.openal.AL;
/*  15:    */import org.lwjgl.openal.AL10;
/*  16:    */import org.lwjgl.openal.OpenALException;
/*  17:    */import org.newdawn.slick.util.Log;
/*  18:    */import org.newdawn.slick.util.ResourceLoader;
/*  19:    */
/*  28:    */public class SoundStore
/*  29:    */{
/*  30: 30 */  private static SoundStore store = new SoundStore();
/*  31:    */  
/*  33:    */  private boolean sounds;
/*  34:    */  
/*  35:    */  private boolean music;
/*  36:    */  
/*  37:    */  private boolean soundWorks;
/*  38:    */  
/*  39:    */  private int sourceCount;
/*  40:    */  
/*  41: 41 */  private HashMap loaded = new HashMap();
/*  42:    */  
/*  43: 43 */  private int currentMusic = -1;
/*  44:    */  
/*  45:    */  private IntBuffer sources;
/*  46:    */  
/*  47:    */  private int nextSource;
/*  48:    */  
/*  49: 49 */  private boolean inited = false;
/*  50:    */  
/*  52:    */  private MODSound mod;
/*  53:    */  
/*  54:    */  private OpenALStreamPlayer stream;
/*  55:    */  
/*  56: 56 */  private float musicVolume = 1.0F;
/*  57:    */  
/*  58: 58 */  private float soundVolume = 1.0F;
/*  59:    */  
/*  60: 60 */  private float lastCurrentMusicVolume = 1.0F;
/*  61:    */  
/*  63:    */  private boolean paused;
/*  64:    */  
/*  66:    */  private boolean deferred;
/*  67:    */  
/*  68: 68 */  private FloatBuffer sourceVel = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F });
/*  69:    */  
/*  70: 70 */  private FloatBuffer sourcePos = BufferUtils.createFloatBuffer(3);
/*  71:    */  
/*  73: 73 */  private int maxSources = 64;
/*  74:    */  
/*  78:    */  public static boolean sound3d;
/*  79:    */  
/*  83:    */  public void clear()
/*  84:    */  {
/*  85: 85 */    store = new SoundStore();
/*  86:    */  }
/*  87:    */  
/*  90:    */  public void disable()
/*  91:    */  {
/*  92: 92 */    this.inited = true;
/*  93:    */  }
/*  94:    */  
/* 100:    */  public void setDeferredLoading(boolean deferred)
/* 101:    */  {
/* 102:102 */    this.deferred = deferred;
/* 103:    */  }
/* 104:    */  
/* 109:    */  public boolean isDeferredLoading()
/* 110:    */  {
/* 111:111 */    return this.deferred;
/* 112:    */  }
/* 113:    */  
/* 118:    */  public void setMusicOn(boolean music)
/* 119:    */  {
/* 120:120 */    if (this.soundWorks) {
/* 121:121 */      this.music = music;
/* 122:122 */      if (music) {
/* 123:123 */        restartLoop();
/* 124:124 */        setMusicVolume(this.musicVolume);
/* 125:    */      } else {
/* 126:126 */        pauseLoop();
/* 127:    */      }
/* 128:    */    }
/* 129:    */  }
/* 130:    */  
/* 135:    */  public boolean isMusicOn()
/* 136:    */  {
/* 137:137 */    return this.music;
/* 138:    */  }
/* 139:    */  
/* 144:    */  public void setMusicVolume(float volume)
/* 145:    */  {
/* 146:146 */    if (volume < 0.0F) {
/* 147:147 */      volume = 0.0F;
/* 148:    */    }
/* 149:149 */    if (volume > 1.0F) {
/* 150:150 */      volume = 1.0F;
/* 151:    */    }
/* 152:    */    
/* 153:153 */    this.musicVolume = volume;
/* 154:154 */    if (this.soundWorks) {
/* 155:155 */      AL10.alSourcef(this.sources.get(0), 4106, this.lastCurrentMusicVolume * this.musicVolume);
/* 156:    */    }
/* 157:    */  }
/* 158:    */  
/* 163:    */  public float getCurrentMusicVolume()
/* 164:    */  {
/* 165:165 */    return this.lastCurrentMusicVolume;
/* 166:    */  }
/* 167:    */  
/* 172:    */  public void setCurrentMusicVolume(float volume)
/* 173:    */  {
/* 174:174 */    if (volume < 0.0F) {
/* 175:175 */      volume = 0.0F;
/* 176:    */    }
/* 177:177 */    if (volume > 1.0F) {
/* 178:178 */      volume = 1.0F;
/* 179:    */    }
/* 180:    */    
/* 181:181 */    if (this.soundWorks) {
/* 182:182 */      this.lastCurrentMusicVolume = volume;
/* 183:183 */      AL10.alSourcef(this.sources.get(0), 4106, this.lastCurrentMusicVolume * this.musicVolume);
/* 184:    */    }
/* 185:    */  }
/* 186:    */  
/* 191:    */  public void setSoundVolume(float volume)
/* 192:    */  {
/* 193:193 */    if (volume < 0.0F) {
/* 194:194 */      volume = 0.0F;
/* 195:    */    }
/* 196:196 */    this.soundVolume = volume;
/* 197:    */  }
/* 198:    */  
/* 203:    */  public boolean soundWorks()
/* 204:    */  {
/* 205:205 */    return this.soundWorks;
/* 206:    */  }
/* 207:    */  
/* 212:    */  public boolean musicOn()
/* 213:    */  {
/* 214:214 */    return this.music;
/* 215:    */  }
/* 216:    */  
/* 221:    */  public float getSoundVolume()
/* 222:    */  {
/* 223:223 */    return this.soundVolume;
/* 224:    */  }
/* 225:    */  
/* 230:    */  public float getMusicVolume()
/* 231:    */  {
/* 232:232 */    return this.musicVolume;
/* 233:    */  }
/* 234:    */  
/* 240:    */  public int getSource(int index)
/* 241:    */  {
/* 242:242 */    if (!this.soundWorks) {
/* 243:243 */      return -1;
/* 244:    */    }
/* 245:245 */    if (index < 0) {
/* 246:246 */      return -1;
/* 247:    */    }
/* 248:248 */    return this.sources.get(index);
/* 249:    */  }
/* 250:    */  
/* 255:    */  public void setSoundsOn(boolean sounds)
/* 256:    */  {
/* 257:257 */    if (this.soundWorks) {
/* 258:258 */      this.sounds = sounds;
/* 259:    */    }
/* 260:    */  }
/* 261:    */  
/* 266:    */  public boolean soundsOn()
/* 267:    */  {
/* 268:268 */    return this.sounds;
/* 269:    */  }
/* 270:    */  
/* 276:    */  public void setMaxSources(int max)
/* 277:    */  {
/* 278:278 */    this.maxSources = max;
/* 279:    */  }
/* 280:    */  
/* 284:    */  public void init()
/* 285:    */  {
/* 286:286 */    if (this.inited) {
/* 287:287 */      return;
/* 288:    */    }
/* 289:289 */    Log.info("Initialising sounds..");
/* 290:290 */    this.inited = true;
/* 291:    */    
/* 292:292 */    AccessController.doPrivileged(new PrivilegedAction()
/* 293:    */    {
/* 294:    */      public Object run()
/* 295:    */      {
/* 296:    */        try {
/* 297:297 */          if (!SoundStore.sound3d) {
/* 298:298 */            AL.create();
/* 299:    */          } else {
/* 300:300 */            AL.create("DirectSound3D", 44100, 15, false);
/* 301:    */          }
/* 302:302 */          SoundStore.this.soundWorks = true;
/* 303:303 */          SoundStore.this.sounds = true;
/* 304:304 */          SoundStore.this.music = true;
/* 305:305 */          Log.info("- Sound works");
/* 306:    */        } catch (Exception e) {
/* 307:307 */          Log.error("Sound initialisation failure.");
/* 308:308 */          Log.error(e);
/* 309:309 */          SoundStore.this.soundWorks = false;
/* 310:310 */          SoundStore.this.sounds = false;
/* 311:311 */          SoundStore.this.music = false;
/* 312:    */        }
/* 313:    */        
/* 314:314 */        return null;
/* 315:    */      }
/* 316:    */    });
/* 317:317 */    if (this.soundWorks) {
/* 318:318 */      this.sourceCount = 0;
/* 319:319 */      this.sources = BufferUtils.createIntBuffer(this.maxSources);
/* 320:320 */      while (AL10.alGetError() == 0) {
/* 321:321 */        IntBuffer temp = BufferUtils.createIntBuffer(1);
/* 322:    */        try
/* 323:    */        {
/* 324:324 */          AL10.alGenSources(temp);
/* 325:    */          
/* 326:326 */          if (AL10.alGetError() == 0) {
/* 327:327 */            this.sourceCount += 1;
/* 328:328 */            this.sources.put(temp.get(0));
/* 329:329 */            if (this.sourceCount > this.maxSources - 1) {
/* 330:330 */              break;
/* 331:    */            }
/* 332:    */          }
/* 333:    */        }
/* 334:    */        catch (OpenALException e) {
/* 335:335 */          break;
/* 336:    */        }
/* 337:    */      }
/* 338:338 */      Log.info("- " + this.sourceCount + " OpenAL source available");
/* 339:    */      
/* 340:340 */      if (AL10.alGetError() != 0) {
/* 341:341 */        this.sounds = false;
/* 342:342 */        this.music = false;
/* 343:343 */        this.soundWorks = false;
/* 344:344 */        Log.error("- AL init failed");
/* 345:    */      } else {
/* 346:346 */        FloatBuffer listenerOri = BufferUtils.createFloatBuffer(6).put(new float[] { 0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F });
/* 347:    */        
/* 348:348 */        FloatBuffer listenerVel = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F });
/* 349:    */        
/* 350:350 */        FloatBuffer listenerPos = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F });
/* 351:    */        
/* 352:352 */        listenerPos.flip();
/* 353:353 */        listenerVel.flip();
/* 354:354 */        listenerOri.flip();
/* 355:355 */        AL10.alListener(4100, listenerPos);
/* 356:356 */        AL10.alListener(4102, listenerVel);
/* 357:357 */        AL10.alListener(4111, listenerOri);
/* 358:    */        
/* 359:359 */        Log.info("- Sounds source generated");
/* 360:    */      }
/* 361:    */    }
/* 362:    */  }
/* 363:    */  
/* 368:    */  void stopSource(int index)
/* 369:    */  {
/* 370:370 */    AL10.alSourceStop(this.sources.get(index));
/* 371:    */  }
/* 372:    */  
/* 382:    */  int playAsSound(int buffer, float pitch, float gain, boolean loop)
/* 383:    */  {
/* 384:384 */    return playAsSoundAt(buffer, pitch, gain, loop, 0.0F, 0.0F, 0.0F);
/* 385:    */  }
/* 386:    */  
/* 399:    */  int playAsSoundAt(int buffer, float pitch, float gain, boolean loop, float x, float y, float z)
/* 400:    */  {
/* 401:401 */    gain *= this.soundVolume;
/* 402:402 */    if (gain == 0.0F) {
/* 403:403 */      gain = 0.001F;
/* 404:    */    }
/* 405:405 */    if ((this.soundWorks) && 
/* 406:406 */      (this.sounds)) {
/* 407:407 */      int nextSource = findFreeSource();
/* 408:408 */      if (nextSource == -1) {
/* 409:409 */        return -1;
/* 410:    */      }
/* 411:    */      
/* 412:412 */      AL10.alSourceStop(this.sources.get(nextSource));
/* 413:    */      
/* 414:414 */      AL10.alSourcei(this.sources.get(nextSource), 4105, buffer);
/* 415:415 */      AL10.alSourcef(this.sources.get(nextSource), 4099, pitch);
/* 416:416 */      AL10.alSourcef(this.sources.get(nextSource), 4106, gain);
/* 417:417 */      AL10.alSourcei(this.sources.get(nextSource), 4103, loop ? 1 : 0);
/* 418:    */      
/* 419:419 */      this.sourcePos.clear();
/* 420:420 */      this.sourceVel.clear();
/* 421:421 */      this.sourceVel.put(new float[] { 0.0F, 0.0F, 0.0F });
/* 422:422 */      this.sourcePos.put(new float[] { x, y, z });
/* 423:423 */      this.sourcePos.flip();
/* 424:424 */      this.sourceVel.flip();
/* 425:425 */      AL10.alSource(this.sources.get(nextSource), 4100, this.sourcePos);
/* 426:426 */      AL10.alSource(this.sources.get(nextSource), 4102, this.sourceVel);
/* 427:    */      
/* 428:428 */      AL10.alSourcePlay(this.sources.get(nextSource));
/* 429:    */      
/* 430:430 */      return nextSource;
/* 431:    */    }
/* 432:    */    
/* 434:434 */    return -1;
/* 435:    */  }
/* 436:    */  
/* 441:    */  boolean isPlaying(int index)
/* 442:    */  {
/* 443:443 */    int state = AL10.alGetSourcei(this.sources.get(index), 4112);
/* 444:    */    
/* 445:445 */    return state == 4114;
/* 446:    */  }
/* 447:    */  
/* 452:    */  private int findFreeSource()
/* 453:    */  {
/* 454:454 */    for (int i = 1; i < this.sourceCount - 1; i++) {
/* 455:455 */      int state = AL10.alGetSourcei(this.sources.get(i), 4112);
/* 456:    */      
/* 457:457 */      if ((state != 4114) && (state != 4115)) {
/* 458:458 */        return i;
/* 459:    */      }
/* 460:    */    }
/* 461:    */    
/* 462:462 */    return -1;
/* 463:    */  }
/* 464:    */  
/* 472:    */  void playAsMusic(int buffer, float pitch, float gain, boolean loop)
/* 473:    */  {
/* 474:474 */    this.paused = false;
/* 475:    */    
/* 476:476 */    setMOD(null);
/* 477:    */    
/* 478:478 */    if (this.soundWorks) {
/* 479:479 */      if (this.currentMusic != -1) {
/* 480:480 */        AL10.alSourceStop(this.sources.get(0));
/* 481:    */      }
/* 482:    */      
/* 483:483 */      getMusicSource();
/* 484:    */      
/* 485:485 */      AL10.alSourcei(this.sources.get(0), 4105, buffer);
/* 486:486 */      AL10.alSourcef(this.sources.get(0), 4099, pitch);
/* 487:487 */      AL10.alSourcei(this.sources.get(0), 4103, loop ? 1 : 0);
/* 488:    */      
/* 489:489 */      this.currentMusic = this.sources.get(0);
/* 490:    */      
/* 491:491 */      if (!this.music) {
/* 492:492 */        pauseLoop();
/* 493:    */      } else {
/* 494:494 */        AL10.alSourcePlay(this.sources.get(0));
/* 495:    */      }
/* 496:    */    }
/* 497:    */  }
/* 498:    */  
/* 503:    */  private int getMusicSource()
/* 504:    */  {
/* 505:505 */    return this.sources.get(0);
/* 506:    */  }
/* 507:    */  
/* 512:    */  public void setMusicPitch(float pitch)
/* 513:    */  {
/* 514:514 */    if (this.soundWorks) {
/* 515:515 */      AL10.alSourcef(this.sources.get(0), 4099, pitch);
/* 516:    */    }
/* 517:    */  }
/* 518:    */  
/* 521:    */  public void pauseLoop()
/* 522:    */  {
/* 523:523 */    if ((this.soundWorks) && (this.currentMusic != -1)) {
/* 524:524 */      this.paused = true;
/* 525:525 */      AL10.alSourcePause(this.currentMusic);
/* 526:    */    }
/* 527:    */  }
/* 528:    */  
/* 531:    */  public void restartLoop()
/* 532:    */  {
/* 533:533 */    if ((this.music) && (this.soundWorks) && (this.currentMusic != -1)) {
/* 534:534 */      this.paused = false;
/* 535:535 */      AL10.alSourcePlay(this.currentMusic);
/* 536:    */    }
/* 537:    */  }
/* 538:    */  
/* 545:    */  boolean isPlaying(OpenALStreamPlayer player)
/* 546:    */  {
/* 547:547 */    return this.stream == player;
/* 548:    */  }
/* 549:    */  
/* 555:    */  public Audio getMOD(String ref)
/* 556:    */    throws IOException
/* 557:    */  {
/* 558:558 */    return getMOD(ref, ResourceLoader.getResourceAsStream(ref));
/* 559:    */  }
/* 560:    */  
/* 566:    */  public Audio getMOD(InputStream in)
/* 567:    */    throws IOException
/* 568:    */  {
/* 569:569 */    return getMOD(in.toString(), in);
/* 570:    */  }
/* 571:    */  
/* 578:    */  public Audio getMOD(String ref, InputStream in)
/* 579:    */    throws IOException
/* 580:    */  {
/* 581:581 */    if (!this.soundWorks) {
/* 582:582 */      return new NullAudio();
/* 583:    */    }
/* 584:584 */    if (!this.inited) {
/* 585:585 */      throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method.");
/* 586:    */    }
/* 587:587 */    if (this.deferred) {
/* 588:588 */      return new DeferredSound(ref, in, 3);
/* 589:    */    }
/* 590:    */    
/* 591:591 */    return new MODSound(this, in);
/* 592:    */  }
/* 593:    */  
/* 599:    */  public Audio getAIF(String ref)
/* 600:    */    throws IOException
/* 601:    */  {
/* 602:602 */    return getAIF(ref, ResourceLoader.getResourceAsStream(ref));
/* 603:    */  }
/* 604:    */  
/* 611:    */  public Audio getAIF(InputStream in)
/* 612:    */    throws IOException
/* 613:    */  {
/* 614:614 */    return getAIF(in.toString(), in);
/* 615:    */  }
/* 616:    */  
/* 623:    */  public Audio getAIF(String ref, InputStream in)
/* 624:    */    throws IOException
/* 625:    */  {
/* 626:626 */    in = new BufferedInputStream(in);
/* 627:    */    
/* 628:628 */    if (!this.soundWorks) {
/* 629:629 */      return new NullAudio();
/* 630:    */    }
/* 631:631 */    if (!this.inited) {
/* 632:632 */      throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method.");
/* 633:    */    }
/* 634:634 */    if (this.deferred) {
/* 635:635 */      return new DeferredSound(ref, in, 4);
/* 636:    */    }
/* 637:    */    
/* 638:638 */    int buffer = -1;
/* 639:    */    
/* 640:640 */    if (this.loaded.get(ref) != null) {
/* 641:641 */      buffer = ((Integer)this.loaded.get(ref)).intValue();
/* 642:    */    } else {
/* 643:    */      try {
/* 644:644 */        IntBuffer buf = BufferUtils.createIntBuffer(1);
/* 645:    */        
/* 646:646 */        AiffData data = AiffData.create(in);
/* 647:647 */        AL10.alGenBuffers(buf);
/* 648:648 */        AL10.alBufferData(buf.get(0), data.format, data.data, data.samplerate);
/* 649:    */        
/* 650:650 */        this.loaded.put(ref, new Integer(buf.get(0)));
/* 651:651 */        buffer = buf.get(0);
/* 652:    */      } catch (Exception e) {
/* 653:653 */        Log.error(e);
/* 654:654 */        IOException x = new IOException("Failed to load: " + ref);
/* 655:655 */        x.initCause(e);
/* 656:    */        
/* 657:657 */        throw x;
/* 658:    */      }
/* 659:    */    }
/* 660:    */    
/* 661:661 */    if (buffer == -1) {
/* 662:662 */      throw new IOException("Unable to load: " + ref);
/* 663:    */    }
/* 664:    */    
/* 665:665 */    return new AudioImpl(this, buffer);
/* 666:    */  }
/* 667:    */  
/* 675:    */  public Audio getWAV(String ref)
/* 676:    */    throws IOException
/* 677:    */  {
/* 678:678 */    return getWAV(ref, ResourceLoader.getResourceAsStream(ref));
/* 679:    */  }
/* 680:    */  
/* 686:    */  public Audio getWAV(InputStream in)
/* 687:    */    throws IOException
/* 688:    */  {
/* 689:689 */    return getWAV(in.toString(), in);
/* 690:    */  }
/* 691:    */  
/* 698:    */  public Audio getWAV(String ref, InputStream in)
/* 699:    */    throws IOException
/* 700:    */  {
/* 701:701 */    if (!this.soundWorks) {
/* 702:702 */      return new NullAudio();
/* 703:    */    }
/* 704:704 */    if (!this.inited) {
/* 705:705 */      throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method.");
/* 706:    */    }
/* 707:707 */    if (this.deferred) {
/* 708:708 */      return new DeferredSound(ref, in, 2);
/* 709:    */    }
/* 710:    */    
/* 711:711 */    int buffer = -1;
/* 712:    */    
/* 713:713 */    if (this.loaded.get(ref) != null) {
/* 714:714 */      buffer = ((Integer)this.loaded.get(ref)).intValue();
/* 715:    */    } else {
/* 716:    */      try {
/* 717:717 */        IntBuffer buf = BufferUtils.createIntBuffer(1);
/* 718:    */        
/* 719:719 */        WaveData data = WaveData.create(in);
/* 720:720 */        AL10.alGenBuffers(buf);
/* 721:721 */        AL10.alBufferData(buf.get(0), data.format, data.data, data.samplerate);
/* 722:    */        
/* 723:723 */        this.loaded.put(ref, new Integer(buf.get(0)));
/* 724:724 */        buffer = buf.get(0);
/* 725:    */      } catch (Exception e) {
/* 726:726 */        Log.error(e);
/* 727:727 */        IOException x = new IOException("Failed to load: " + ref);
/* 728:728 */        x.initCause(e);
/* 729:    */        
/* 730:730 */        throw x;
/* 731:    */      }
/* 732:    */    }
/* 733:    */    
/* 734:734 */    if (buffer == -1) {
/* 735:735 */      throw new IOException("Unable to load: " + ref);
/* 736:    */    }
/* 737:    */    
/* 738:738 */    return new AudioImpl(this, buffer);
/* 739:    */  }
/* 740:    */  
/* 746:    */  public Audio getOggStream(String ref)
/* 747:    */    throws IOException
/* 748:    */  {
/* 749:749 */    if (!this.soundWorks) {
/* 750:750 */      return new NullAudio();
/* 751:    */    }
/* 752:    */    
/* 753:753 */    setMOD(null);
/* 754:754 */    setStream(null);
/* 755:    */    
/* 756:756 */    if (this.currentMusic != -1) {
/* 757:757 */      AL10.alSourceStop(this.sources.get(0));
/* 758:    */    }
/* 759:    */    
/* 760:760 */    getMusicSource();
/* 761:761 */    this.currentMusic = this.sources.get(0);
/* 762:    */    
/* 763:763 */    return new StreamSound(new OpenALStreamPlayer(this.currentMusic, ref));
/* 764:    */  }
/* 765:    */  
/* 771:    */  public Audio getOggStream(URL ref)
/* 772:    */    throws IOException
/* 773:    */  {
/* 774:774 */    if (!this.soundWorks) {
/* 775:775 */      return new NullAudio();
/* 776:    */    }
/* 777:    */    
/* 778:778 */    setMOD(null);
/* 779:779 */    setStream(null);
/* 780:    */    
/* 781:781 */    if (this.currentMusic != -1) {
/* 782:782 */      AL10.alSourceStop(this.sources.get(0));
/* 783:    */    }
/* 784:    */    
/* 785:785 */    getMusicSource();
/* 786:786 */    this.currentMusic = this.sources.get(0);
/* 787:    */    
/* 788:788 */    return new StreamSound(new OpenALStreamPlayer(this.currentMusic, ref));
/* 789:    */  }
/* 790:    */  
/* 796:    */  public Audio getOgg(String ref)
/* 797:    */    throws IOException
/* 798:    */  {
/* 799:799 */    return getOgg(ref, ResourceLoader.getResourceAsStream(ref));
/* 800:    */  }
/* 801:    */  
/* 807:    */  public Audio getOgg(InputStream in)
/* 808:    */    throws IOException
/* 809:    */  {
/* 810:810 */    return getOgg(in.toString(), in);
/* 811:    */  }
/* 812:    */  
/* 819:    */  public Audio getOgg(String ref, InputStream in)
/* 820:    */    throws IOException
/* 821:    */  {
/* 822:822 */    if (!this.soundWorks) {
/* 823:823 */      return new NullAudio();
/* 824:    */    }
/* 825:825 */    if (!this.inited) {
/* 826:826 */      throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method.");
/* 827:    */    }
/* 828:828 */    if (this.deferred) {
/* 829:829 */      return new DeferredSound(ref, in, 1);
/* 830:    */    }
/* 831:    */    
/* 832:832 */    int buffer = -1;
/* 833:    */    
/* 834:834 */    if (this.loaded.get(ref) != null) {
/* 835:835 */      buffer = ((Integer)this.loaded.get(ref)).intValue();
/* 836:    */    } else {
/* 837:    */      try {
/* 838:838 */        IntBuffer buf = BufferUtils.createIntBuffer(1);
/* 839:    */        
/* 840:840 */        OggDecoder decoder = new OggDecoder();
/* 841:841 */        OggData ogg = decoder.getData(in);
/* 842:    */        
/* 843:843 */        AL10.alGenBuffers(buf);
/* 844:844 */        AL10.alBufferData(buf.get(0), ogg.channels > 1 ? 4355 : 4353, ogg.data, ogg.rate);
/* 845:    */        
/* 846:846 */        this.loaded.put(ref, new Integer(buf.get(0)));
/* 847:    */        
/* 848:848 */        buffer = buf.get(0);
/* 849:    */      } catch (Exception e) {
/* 850:850 */        Log.error(e);
/* 851:851 */        Sys.alert("Error", "Failed to load: " + ref + " - " + e.getMessage());
/* 852:852 */        throw new IOException("Unable to load: " + ref);
/* 853:    */      }
/* 854:    */    }
/* 855:    */    
/* 856:856 */    if (buffer == -1) {
/* 857:857 */      throw new IOException("Unable to load: " + ref);
/* 858:    */    }
/* 859:    */    
/* 860:860 */    return new AudioImpl(this, buffer);
/* 861:    */  }
/* 862:    */  
/* 867:    */  void setMOD(MODSound sound)
/* 868:    */  {
/* 869:869 */    if (!this.soundWorks) {
/* 870:870 */      return;
/* 871:    */    }
/* 872:    */    
/* 873:873 */    this.currentMusic = this.sources.get(0);
/* 874:874 */    stopSource(0);
/* 875:    */    
/* 876:876 */    this.mod = sound;
/* 877:877 */    if (sound != null) {
/* 878:878 */      this.stream = null;
/* 879:    */    }
/* 880:880 */    this.paused = false;
/* 881:    */  }
/* 882:    */  
/* 887:    */  void setStream(OpenALStreamPlayer stream)
/* 888:    */  {
/* 889:889 */    if (!this.soundWorks) {
/* 890:890 */      return;
/* 891:    */    }
/* 892:    */    
/* 893:893 */    this.currentMusic = this.sources.get(0);
/* 894:894 */    this.stream = stream;
/* 895:895 */    if (stream != null) {
/* 896:896 */      this.mod = null;
/* 897:    */    }
/* 898:898 */    this.paused = false;
/* 899:    */  }
/* 900:    */  
/* 905:    */  public void poll(int delta)
/* 906:    */  {
/* 907:907 */    if (!this.soundWorks) {
/* 908:908 */      return;
/* 909:    */    }
/* 910:910 */    if (this.paused) {
/* 911:911 */      return;
/* 912:    */    }
/* 913:    */    
/* 914:914 */    if (this.music) {
/* 915:915 */      if (this.mod != null) {
/* 916:    */        try {
/* 917:917 */          this.mod.poll();
/* 918:    */        } catch (OpenALException e) {
/* 919:919 */          Log.error("Error with OpenGL MOD Player on this this platform");
/* 920:920 */          Log.error(e);
/* 921:921 */          this.mod = null;
/* 922:    */        }
/* 923:    */      }
/* 924:924 */      if (this.stream != null) {
/* 925:    */        try {
/* 926:926 */          this.stream.update();
/* 927:    */        } catch (OpenALException e) {
/* 928:928 */          Log.error("Error with OpenGL Streaming Player on this this platform");
/* 929:929 */          Log.error(e);
/* 930:930 */          this.mod = null;
/* 931:    */        }
/* 932:    */      }
/* 933:    */    }
/* 934:    */  }
/* 935:    */  
/* 941:    */  public boolean isMusicPlaying()
/* 942:    */  {
/* 943:943 */    if (!this.soundWorks) {
/* 944:944 */      return false;
/* 945:    */    }
/* 946:    */    
/* 947:947 */    int state = AL10.alGetSourcei(this.sources.get(0), 4112);
/* 948:948 */    return (state == 4114) || (state == 4115);
/* 949:    */  }
/* 950:    */  
/* 955:    */  public static SoundStore get()
/* 956:    */  {
/* 957:957 */    return store;
/* 958:    */  }
/* 959:    */  
/* 966:    */  public void stopSoundEffect(int id)
/* 967:    */  {
/* 968:968 */    AL10.alSourceStop(id);
/* 969:    */  }
/* 970:    */  
/* 976:    */  public int getSourceCount()
/* 977:    */  {
/* 978:978 */    return this.sourceCount;
/* 979:    */  }
/* 980:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.SoundStore
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */