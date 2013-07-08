/*    1:     */package paulscode.sound;
/*    2:     */
/*    3:     */import java.net.URL;
/*    4:     */import java.util.LinkedList;
/*    5:     */import java.util.ListIterator;
/*    6:     */import javax.sound.sampled.AudioFormat;
/*    7:     */
/*   51:     */public class Source
/*   52:     */{
/*   53:  53 */  protected Class libraryType = Library.class;
/*   54:     */  
/*   59:     */  private static final boolean GET = false;
/*   60:     */  
/*   65:     */  private static final boolean SET = true;
/*   66:     */  
/*   70:     */  private static final boolean XXX = false;
/*   71:     */  
/*   75:     */  private SoundSystemLogger logger;
/*   76:     */  
/*   80:  80 */  public boolean rawDataStream = false;
/*   81:     */  
/*   85:  85 */  public AudioFormat rawDataFormat = null;
/*   86:     */  
/*   90:  90 */  public boolean temporary = false;
/*   91:     */  
/*   96:  96 */  public boolean priority = false;
/*   97:     */  
/*  101: 101 */  public boolean toStream = false;
/*  102:     */  
/*  106: 106 */  public boolean toLoop = false;
/*  107:     */  
/*  112: 112 */  public boolean toPlay = false;
/*  113:     */  
/*  118: 118 */  public String sourcename = "";
/*  119:     */  
/*  123: 123 */  public FilenameURL filenameURL = null;
/*  124:     */  
/*  128:     */  public Vector3D position;
/*  129:     */  
/*  133: 133 */  public int attModel = 0;
/*  134:     */  
/*  138: 138 */  public float distOrRoll = 0.0F;
/*  139:     */  
/*  144:     */  public Vector3D velocity;
/*  145:     */  
/*  150: 150 */  public float gain = 1.0F;
/*  151:     */  
/*  155: 155 */  public float sourceVolume = 1.0F;
/*  156:     */  
/*  160: 160 */  protected float pitch = 1.0F;
/*  161:     */  
/*  165: 165 */  public float distanceFromListener = 0.0F;
/*  166:     */  
/*  170: 170 */  public Channel channel = null;
/*  171:     */  
/*  175: 175 */  public SoundBuffer soundBuffer = null;
/*  176:     */  
/*  180: 180 */  private boolean active = true;
/*  181:     */  
/*  185: 185 */  private boolean stopped = true;
/*  186:     */  
/*  190: 190 */  private boolean paused = false;
/*  191:     */  
/*  195: 195 */  protected ICodec codec = null;
/*  196:     */  
/*  200: 200 */  protected ICodec nextCodec = null;
/*  201:     */  
/*  205: 205 */  protected LinkedList<SoundBuffer> nextBuffers = null;
/*  206:     */  
/*  211: 211 */  protected LinkedList<FilenameURL> soundSequenceQueue = null;
/*  212:     */  
/*  216: 216 */  protected final Object soundSequenceLock = new Object();
/*  217:     */  
/*  222: 222 */  public boolean preLoad = false;
/*  223:     */  
/*  228: 228 */  protected float fadeOutGain = -1.0F;
/*  229:     */  
/*  234: 234 */  protected float fadeInGain = 1.0F;
/*  235:     */  
/*  239: 239 */  protected long fadeOutMilis = 0L;
/*  240:     */  
/*  244: 244 */  protected long fadeInMilis = 0L;
/*  245:     */  
/*  249: 249 */  protected long lastFadeCheck = 0L;
/*  250:     */  
/*  270:     */  public Source(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, SoundBuffer soundBuffer, float x, float y, float z, int attModel, float distOrRoll, boolean temporary)
/*  271:     */  {
/*  272: 272 */    this.logger = SoundSystemConfig.getLogger();
/*  273:     */    
/*  274: 274 */    this.priority = priority;
/*  275: 275 */    this.toStream = toStream;
/*  276: 276 */    this.toLoop = toLoop;
/*  277: 277 */    this.sourcename = sourcename;
/*  278: 278 */    this.filenameURL = filenameURL;
/*  279: 279 */    this.soundBuffer = soundBuffer;
/*  280: 280 */    this.position = new Vector3D(x, y, z);
/*  281: 281 */    this.attModel = attModel;
/*  282: 282 */    this.distOrRoll = distOrRoll;
/*  283: 283 */    this.velocity = new Vector3D(0.0F, 0.0F, 0.0F);
/*  284: 284 */    this.temporary = temporary;
/*  285:     */    
/*  286: 286 */    if ((toStream) && (filenameURL != null)) {
/*  287: 287 */      this.codec = SoundSystemConfig.getCodec(filenameURL.getFilename());
/*  288:     */    }
/*  289:     */  }
/*  290:     */  
/*  296:     */  public Source(Source old, SoundBuffer soundBuffer)
/*  297:     */  {
/*  298: 298 */    this.logger = SoundSystemConfig.getLogger();
/*  299:     */    
/*  300: 300 */    this.priority = old.priority;
/*  301: 301 */    this.toStream = old.toStream;
/*  302: 302 */    this.toLoop = old.toLoop;
/*  303: 303 */    this.sourcename = old.sourcename;
/*  304: 304 */    this.filenameURL = old.filenameURL;
/*  305: 305 */    this.position = old.position.clone();
/*  306: 306 */    this.attModel = old.attModel;
/*  307: 307 */    this.distOrRoll = old.distOrRoll;
/*  308: 308 */    this.velocity = old.velocity.clone();
/*  309: 309 */    this.temporary = old.temporary;
/*  310:     */    
/*  311: 311 */    this.sourceVolume = old.sourceVolume;
/*  312:     */    
/*  313: 313 */    this.rawDataStream = old.rawDataStream;
/*  314: 314 */    this.rawDataFormat = old.rawDataFormat;
/*  315:     */    
/*  316: 316 */    this.soundBuffer = soundBuffer;
/*  317:     */    
/*  318: 318 */    if ((this.toStream) && (this.filenameURL != null)) {
/*  319: 319 */      this.codec = SoundSystemConfig.getCodec(this.filenameURL.getFilename());
/*  320:     */    }
/*  321:     */  }
/*  322:     */  
/*  336:     */  public Source(AudioFormat audioFormat, boolean priority, String sourcename, float x, float y, float z, int attModel, float distOrRoll)
/*  337:     */  {
/*  338: 338 */    this.logger = SoundSystemConfig.getLogger();
/*  339:     */    
/*  340: 340 */    this.priority = priority;
/*  341: 341 */    this.toStream = true;
/*  342: 342 */    this.toLoop = false;
/*  343: 343 */    this.sourcename = sourcename;
/*  344: 344 */    this.filenameURL = null;
/*  345: 345 */    this.soundBuffer = null;
/*  346: 346 */    this.position = new Vector3D(x, y, z);
/*  347: 347 */    this.attModel = attModel;
/*  348: 348 */    this.distOrRoll = distOrRoll;
/*  349: 349 */    this.velocity = new Vector3D(0.0F, 0.0F, 0.0F);
/*  350: 350 */    this.temporary = false;
/*  351:     */    
/*  352: 352 */    this.rawDataStream = true;
/*  353: 353 */    this.rawDataFormat = audioFormat;
/*  354:     */  }
/*  355:     */  
/*  360:     */  public void cleanup()
/*  361:     */  {
/*  362: 362 */    if (this.codec != null) {
/*  363: 363 */      this.codec.cleanup();
/*  364:     */    }
/*  365: 365 */    synchronized (this.soundSequenceLock)
/*  366:     */    {
/*  367: 367 */      if (this.soundSequenceQueue != null)
/*  368: 368 */        this.soundSequenceQueue.clear();
/*  369: 369 */      this.soundSequenceQueue = null;
/*  370:     */    }
/*  371:     */    
/*  372: 372 */    this.sourcename = null;
/*  373: 373 */    this.filenameURL = null;
/*  374: 374 */    this.position = null;
/*  375: 375 */    this.soundBuffer = null;
/*  376: 376 */    this.codec = null;
/*  377:     */  }
/*  378:     */  
/*  385:     */  public void queueSound(FilenameURL filenameURL)
/*  386:     */  {
/*  387: 387 */    if (!this.toStream)
/*  388:     */    {
/*  389: 389 */      errorMessage("Method 'queueSound' may only be used for streaming and MIDI sources.");
/*  390:     */      
/*  391: 391 */      return;
/*  392:     */    }
/*  393: 393 */    if (filenameURL == null)
/*  394:     */    {
/*  395: 395 */      errorMessage("File not specified in method 'queueSound'");
/*  396: 396 */      return;
/*  397:     */    }
/*  398:     */    
/*  399: 399 */    synchronized (this.soundSequenceLock)
/*  400:     */    {
/*  401: 401 */      if (this.soundSequenceQueue == null)
/*  402: 402 */        this.soundSequenceQueue = new LinkedList();
/*  403: 403 */      this.soundSequenceQueue.add(filenameURL);
/*  404:     */    }
/*  405:     */  }
/*  406:     */  
/*  413:     */  public void dequeueSound(String filename)
/*  414:     */  {
/*  415: 415 */    if (!this.toStream)
/*  416:     */    {
/*  417: 417 */      errorMessage("Method 'dequeueSound' may only be used for streaming and MIDI sources.");
/*  418:     */      
/*  419: 419 */      return;
/*  420:     */    }
/*  421: 421 */    if ((filename == null) || (filename.equals("")))
/*  422:     */    {
/*  423: 423 */      errorMessage("Filename not specified in method 'dequeueSound'");
/*  424: 424 */      return;
/*  425:     */    }
/*  426:     */    
/*  427: 427 */    synchronized (this.soundSequenceLock)
/*  428:     */    {
/*  429: 429 */      if (this.soundSequenceQueue != null)
/*  430:     */      {
/*  431: 431 */        ListIterator<FilenameURL> i = this.soundSequenceQueue.listIterator();
/*  432: 432 */        while (i.hasNext())
/*  433:     */        {
/*  434: 434 */          if (((FilenameURL)i.next()).getFilename().equals(filename))
/*  435:     */          {
/*  436: 436 */            i.remove();
/*  437:     */          }
/*  438:     */        }
/*  439:     */      }
/*  440:     */    }
/*  441:     */  }
/*  442:     */  
/*  455:     */  public void fadeOut(FilenameURL filenameURL, long milis)
/*  456:     */  {
/*  457: 457 */    if (!this.toStream)
/*  458:     */    {
/*  459: 459 */      errorMessage("Method 'fadeOut' may only be used for streaming and MIDI sources.");
/*  460:     */      
/*  461: 461 */      return;
/*  462:     */    }
/*  463: 463 */    if (milis < 0L)
/*  464:     */    {
/*  465: 465 */      errorMessage("Miliseconds may not be negative in method 'fadeOut'.");
/*  466:     */      
/*  467: 467 */      return;
/*  468:     */    }
/*  469:     */    
/*  470: 470 */    this.fadeOutMilis = milis;
/*  471: 471 */    this.fadeInMilis = 0L;
/*  472: 472 */    this.fadeOutGain = 1.0F;
/*  473: 473 */    this.lastFadeCheck = System.currentTimeMillis();
/*  474:     */    
/*  475: 475 */    synchronized (this.soundSequenceLock)
/*  476:     */    {
/*  477: 477 */      if (this.soundSequenceQueue != null) {
/*  478: 478 */        this.soundSequenceQueue.clear();
/*  479:     */      }
/*  480: 480 */      if (filenameURL != null)
/*  481:     */      {
/*  482: 482 */        if (this.soundSequenceQueue == null)
/*  483: 483 */          this.soundSequenceQueue = new LinkedList();
/*  484: 484 */        this.soundSequenceQueue.add(filenameURL);
/*  485:     */      }
/*  486:     */    }
/*  487:     */  }
/*  488:     */  
/*  502:     */  public void fadeOutIn(FilenameURL filenameURL, long milisOut, long milisIn)
/*  503:     */  {
/*  504: 504 */    if (!this.toStream)
/*  505:     */    {
/*  506: 506 */      errorMessage("Method 'fadeOutIn' may only be used for streaming and MIDI sources.");
/*  507:     */      
/*  508: 508 */      return;
/*  509:     */    }
/*  510: 510 */    if (filenameURL == null)
/*  511:     */    {
/*  512: 512 */      errorMessage("Filename/URL not specified in method 'fadeOutIn'.");
/*  513: 513 */      return;
/*  514:     */    }
/*  515: 515 */    if ((milisOut < 0L) || (milisIn < 0L))
/*  516:     */    {
/*  517: 517 */      errorMessage("Miliseconds may not be negative in method 'fadeOutIn'.");
/*  518:     */      
/*  519: 519 */      return;
/*  520:     */    }
/*  521:     */    
/*  522: 522 */    this.fadeOutMilis = milisOut;
/*  523: 523 */    this.fadeInMilis = milisIn;
/*  524:     */    
/*  525: 525 */    this.fadeOutGain = 1.0F;
/*  526: 526 */    this.lastFadeCheck = System.currentTimeMillis();
/*  527:     */    
/*  528: 528 */    synchronized (this.soundSequenceLock)
/*  529:     */    {
/*  530: 530 */      if (this.soundSequenceQueue == null)
/*  531: 531 */        this.soundSequenceQueue = new LinkedList();
/*  532: 532 */      this.soundSequenceQueue.clear();
/*  533: 533 */      this.soundSequenceQueue.add(filenameURL);
/*  534:     */    }
/*  535:     */  }
/*  536:     */  
/*  544:     */  public boolean checkFadeOut()
/*  545:     */  {
/*  546: 546 */    if (!this.toStream) {
/*  547: 547 */      return false;
/*  548:     */    }
/*  549: 549 */    if ((this.fadeOutGain == -1.0F) && (this.fadeInGain == 1.0F)) {
/*  550: 550 */      return false;
/*  551:     */    }
/*  552: 552 */    long currentTime = System.currentTimeMillis();
/*  553: 553 */    long milisPast = currentTime - this.lastFadeCheck;
/*  554: 554 */    this.lastFadeCheck = currentTime;
/*  555:     */    
/*  556: 556 */    if (this.fadeOutGain >= 0.0F)
/*  557:     */    {
/*  558: 558 */      if (this.fadeOutMilis == 0L)
/*  559:     */      {
/*  560: 560 */        this.fadeOutGain = -1.0F;
/*  561: 561 */        this.fadeInGain = 0.0F;
/*  562: 562 */        if (!incrementSoundSequence())
/*  563:     */        {
/*  564: 564 */          stop();
/*  565:     */        }
/*  566: 566 */        positionChanged();
/*  567: 567 */        this.preLoad = true;
/*  568: 568 */        return false;
/*  569:     */      }
/*  570:     */      
/*  572: 572 */      float fadeOutReduction = (float)milisPast / (float)this.fadeOutMilis;
/*  573: 573 */      this.fadeOutGain -= fadeOutReduction;
/*  574: 574 */      if (this.fadeOutGain <= 0.0F)
/*  575:     */      {
/*  576: 576 */        this.fadeOutGain = -1.0F;
/*  577: 577 */        this.fadeInGain = 0.0F;
/*  578: 578 */        if (!incrementSoundSequence())
/*  579: 579 */          stop();
/*  580: 580 */        positionChanged();
/*  581: 581 */        this.preLoad = true;
/*  582: 582 */        return false;
/*  583:     */      }
/*  584:     */      
/*  585: 585 */      positionChanged();
/*  586: 586 */      return true;
/*  587:     */    }
/*  588:     */    
/*  589: 589 */    if (this.fadeInGain < 1.0F)
/*  590:     */    {
/*  591: 591 */      this.fadeOutGain = -1.0F;
/*  592: 592 */      if (this.fadeInMilis == 0L)
/*  593:     */      {
/*  594: 594 */        this.fadeOutGain = -1.0F;
/*  595: 595 */        this.fadeInGain = 1.0F;
/*  596:     */      }
/*  597:     */      else
/*  598:     */      {
/*  599: 599 */        float fadeInIncrease = (float)milisPast / (float)this.fadeInMilis;
/*  600: 600 */        this.fadeInGain += fadeInIncrease;
/*  601: 601 */        if (this.fadeInGain >= 1.0F)
/*  602:     */        {
/*  603: 603 */          this.fadeOutGain = -1.0F;
/*  604: 604 */          this.fadeInGain = 1.0F;
/*  605:     */        }
/*  606:     */      }
/*  607: 607 */      positionChanged();
/*  608: 608 */      return true;
/*  609:     */    }
/*  610: 610 */    return false;
/*  611:     */  }
/*  612:     */  
/*  620:     */  public boolean incrementSoundSequence()
/*  621:     */  {
/*  622: 622 */    if (!this.toStream)
/*  623:     */    {
/*  624: 624 */      errorMessage("Method 'incrementSoundSequence' may only be used for streaming and MIDI sources.");
/*  625:     */      
/*  626: 626 */      return false;
/*  627:     */    }
/*  628:     */    
/*  629: 629 */    synchronized (this.soundSequenceLock)
/*  630:     */    {
/*  631: 631 */      if ((this.soundSequenceQueue != null) && (this.soundSequenceQueue.size() > 0))
/*  632:     */      {
/*  633: 633 */        this.filenameURL = ((FilenameURL)this.soundSequenceQueue.remove(0));
/*  634: 634 */        if (this.codec != null)
/*  635: 635 */          this.codec.cleanup();
/*  636: 636 */        this.codec = SoundSystemConfig.getCodec(this.filenameURL.getFilename());
/*  637: 637 */        return true;
/*  638:     */      }
/*  639:     */    }
/*  640: 640 */    return false;
/*  641:     */  }
/*  642:     */  
/*  650:     */  public boolean readBuffersFromNextSoundInSequence()
/*  651:     */  {
/*  652: 652 */    if (!this.toStream)
/*  653:     */    {
/*  654: 654 */      errorMessage("Method 'readBuffersFromNextSoundInSequence' may only be used for streaming sources.");
/*  655:     */      
/*  656: 656 */      return false;
/*  657:     */    }
/*  658:     */    
/*  659: 659 */    synchronized (this.soundSequenceLock)
/*  660:     */    {
/*  661: 661 */      if ((this.soundSequenceQueue != null) && (this.soundSequenceQueue.size() > 0))
/*  662:     */      {
/*  663: 663 */        if (this.nextCodec != null)
/*  664: 664 */          this.nextCodec.cleanup();
/*  665: 665 */        this.nextCodec = SoundSystemConfig.getCodec(((FilenameURL)this.soundSequenceQueue.get(0)).getFilename());
/*  666:     */        
/*  667: 667 */        this.nextCodec.initialize(((FilenameURL)this.soundSequenceQueue.get(0)).getURL());
/*  668:     */        
/*  669: 669 */        SoundBuffer buffer = null;
/*  670: 670 */        for (int i = 0; 
/*  671:     */            
/*  672: 672 */            (i < SoundSystemConfig.getNumberStreamingBuffers()) && (!this.nextCodec.endOfStream()); 
/*  673: 673 */            i++)
/*  674:     */        {
/*  675: 675 */          buffer = this.nextCodec.read();
/*  676: 676 */          if (buffer != null)
/*  677:     */          {
/*  678: 678 */            if (this.nextBuffers == null)
/*  679: 679 */              this.nextBuffers = new LinkedList();
/*  680: 680 */            this.nextBuffers.add(buffer);
/*  681:     */          }
/*  682:     */        }
/*  683: 683 */        return true;
/*  684:     */      }
/*  685:     */    }
/*  686: 686 */    return false;
/*  687:     */  }
/*  688:     */  
/*  694:     */  public int getSoundSequenceQueueSize()
/*  695:     */  {
/*  696: 696 */    if (this.soundSequenceQueue == null)
/*  697: 697 */      return 0;
/*  698: 698 */    return this.soundSequenceQueue.size();
/*  699:     */  }
/*  700:     */  
/*  705:     */  public void setTemporary(boolean tmp)
/*  706:     */  {
/*  707: 707 */    this.temporary = tmp;
/*  708:     */  }
/*  709:     */  
/*  715:     */  public void listenerMoved() {}
/*  716:     */  
/*  722:     */  public void setPosition(float x, float y, float z)
/*  723:     */  {
/*  724: 724 */    this.position.x = x;
/*  725: 725 */    this.position.y = y;
/*  726: 726 */    this.position.z = z;
/*  727:     */  }
/*  728:     */  
/*  734:     */  public void positionChanged() {}
/*  735:     */  
/*  741:     */  public void setPriority(boolean pri)
/*  742:     */  {
/*  743: 743 */    this.priority = pri;
/*  744:     */  }
/*  745:     */  
/*  750:     */  public void setLooping(boolean lp)
/*  751:     */  {
/*  752: 752 */    this.toLoop = lp;
/*  753:     */  }
/*  754:     */  
/*  759:     */  public void setAttenuation(int model)
/*  760:     */  {
/*  761: 761 */    this.attModel = model;
/*  762:     */  }
/*  763:     */  
/*  769:     */  public void setDistOrRoll(float dr)
/*  770:     */  {
/*  771: 771 */    this.distOrRoll = dr;
/*  772:     */  }
/*  773:     */  
/*  780:     */  public void setVelocity(float x, float y, float z)
/*  781:     */  {
/*  782: 782 */    this.velocity.x = x;
/*  783: 783 */    this.velocity.y = y;
/*  784: 784 */    this.velocity.z = z;
/*  785:     */  }
/*  786:     */  
/*  791:     */  public float getDistanceFromListener()
/*  792:     */  {
/*  793: 793 */    return this.distanceFromListener;
/*  794:     */  }
/*  795:     */  
/*  800:     */  public void setPitch(float value)
/*  801:     */  {
/*  802: 802 */    float newPitch = value;
/*  803: 803 */    if (newPitch < 0.5F) {
/*  804: 804 */      newPitch = 0.5F;
/*  805: 805 */    } else if (newPitch > 2.0F)
/*  806: 806 */      newPitch = 2.0F;
/*  807: 807 */    this.pitch = newPitch;
/*  808:     */  }
/*  809:     */  
/*  814:     */  public float getPitch()
/*  815:     */  {
/*  816: 816 */    return this.pitch;
/*  817:     */  }
/*  818:     */  
/*  824:     */  public boolean reverseByteOrder()
/*  825:     */  {
/*  826: 826 */    return SoundSystemConfig.reverseByteOrder(this.libraryType);
/*  827:     */  }
/*  828:     */  
/*  847:     */  public void changeSource(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, SoundBuffer soundBuffer, float x, float y, float z, int attModel, float distOrRoll, boolean temporary)
/*  848:     */  {
/*  849: 849 */    this.priority = priority;
/*  850: 850 */    this.toStream = toStream;
/*  851: 851 */    this.toLoop = toLoop;
/*  852: 852 */    this.sourcename = sourcename;
/*  853: 853 */    this.filenameURL = filenameURL;
/*  854: 854 */    this.soundBuffer = soundBuffer;
/*  855: 855 */    this.position.x = x;
/*  856: 856 */    this.position.y = y;
/*  857: 857 */    this.position.z = z;
/*  858: 858 */    this.attModel = attModel;
/*  859: 859 */    this.distOrRoll = distOrRoll;
/*  860: 860 */    this.temporary = temporary;
/*  861:     */  }
/*  862:     */  
/*  869:     */  public int feedRawAudioData(Channel c, byte[] buffer)
/*  870:     */  {
/*  871: 871 */    if (!active(false, false))
/*  872:     */    {
/*  873: 873 */      this.toPlay = true;
/*  874: 874 */      return -1;
/*  875:     */    }
/*  876: 876 */    if (this.channel != c)
/*  877:     */    {
/*  878: 878 */      this.channel = c;
/*  879: 879 */      this.channel.close();
/*  880: 880 */      this.channel.setAudioFormat(this.rawDataFormat);
/*  881: 881 */      positionChanged();
/*  882:     */    }
/*  883:     */    
/*  885: 885 */    stopped(true, false);
/*  886: 886 */    paused(true, false);
/*  887:     */    
/*  888: 888 */    return this.channel.feedRawAudioData(buffer);
/*  889:     */  }
/*  890:     */  
/*  895:     */  public void play(Channel c)
/*  896:     */  {
/*  897: 897 */    if (!active(false, false))
/*  898:     */    {
/*  899: 899 */      if (this.toLoop)
/*  900: 900 */        this.toPlay = true;
/*  901: 901 */      return;
/*  902:     */    }
/*  903: 903 */    if (this.channel != c)
/*  904:     */    {
/*  905: 905 */      this.channel = c;
/*  906: 906 */      this.channel.close();
/*  907:     */    }
/*  908:     */    
/*  909: 909 */    stopped(true, false);
/*  910: 910 */    paused(true, false);
/*  911:     */  }
/*  912:     */  
/*  918:     */  public boolean stream()
/*  919:     */  {
/*  920: 920 */    if (this.channel == null) {
/*  921: 921 */      return false;
/*  922:     */    }
/*  923: 923 */    if (this.preLoad)
/*  924:     */    {
/*  925: 925 */      if (this.rawDataStream) {
/*  926: 926 */        this.preLoad = false;
/*  927:     */      } else {
/*  928: 928 */        return preLoad();
/*  929:     */      }
/*  930:     */    }
/*  931: 931 */    if (this.rawDataStream)
/*  932:     */    {
/*  933: 933 */      if ((stopped()) || (paused()))
/*  934: 934 */        return true;
/*  935: 935 */      if (this.channel.buffersProcessed() > 0)
/*  936: 936 */        this.channel.processBuffer();
/*  937: 937 */      return true;
/*  938:     */    }
/*  939:     */    
/*  941: 941 */    if (this.codec == null)
/*  942: 942 */      return false;
/*  943: 943 */    if (stopped())
/*  944: 944 */      return false;
/*  945: 945 */    if (paused()) {
/*  946: 946 */      return true;
/*  947:     */    }
/*  948: 948 */    int processed = this.channel.buffersProcessed();
/*  949:     */    
/*  950: 950 */    SoundBuffer buffer = null;
/*  951: 951 */    for (int i = 0; i < processed; i++)
/*  952:     */    {
/*  953: 953 */      buffer = this.codec.read();
/*  954: 954 */      if (buffer != null)
/*  955:     */      {
/*  956: 956 */        if (buffer.audioData != null)
/*  957: 957 */          this.channel.queueBuffer(buffer.audioData);
/*  958: 958 */        buffer.cleanup();
/*  959: 959 */        buffer = null;
/*  960: 960 */        return true;
/*  961:     */      }
/*  962: 962 */      if (this.codec.endOfStream())
/*  963:     */      {
/*  964: 964 */        synchronized (this.soundSequenceLock)
/*  965:     */        {
/*  966: 966 */          if (SoundSystemConfig.getStreamQueueFormatsMatch())
/*  967:     */          {
/*  968: 968 */            if ((this.soundSequenceQueue != null) && (this.soundSequenceQueue.size() > 0))
/*  969:     */            {
/*  971: 971 */              if (this.codec != null)
/*  972: 972 */                this.codec.cleanup();
/*  973: 973 */              this.filenameURL = ((FilenameURL)this.soundSequenceQueue.remove(0));
/*  974: 974 */              this.codec = SoundSystemConfig.getCodec(this.filenameURL.getFilename());
/*  975:     */              
/*  976: 976 */              this.codec.initialize(this.filenameURL.getURL());
/*  977: 977 */              buffer = this.codec.read();
/*  978: 978 */              if (buffer != null)
/*  979:     */              {
/*  980: 980 */                if (buffer.audioData != null)
/*  981: 981 */                  this.channel.queueBuffer(buffer.audioData);
/*  982: 982 */                buffer.cleanup();
/*  983: 983 */                buffer = null;
/*  984: 984 */                return true;
/*  985:     */              }
/*  986:     */            }
/*  987: 987 */            else if (this.toLoop)
/*  988:     */            {
/*  989: 989 */              this.codec.initialize(this.filenameURL.getURL());
/*  990: 990 */              buffer = this.codec.read();
/*  991: 991 */              if (buffer != null)
/*  992:     */              {
/*  993: 993 */                if (buffer.audioData != null)
/*  994: 994 */                  this.channel.queueBuffer(buffer.audioData);
/*  995: 995 */                buffer.cleanup();
/*  996: 996 */                buffer = null;
/*  997: 997 */                return true;
/*  998:     */              }
/*  999:     */            }
/* 1000:     */          }
/* 1001:     */        }
/* 1002:     */      }
/* 1003:     */    }
/* 1004:     */    
/* 1040:1040 */    return false;
/* 1041:     */  }
/* 1042:     */  
/* 1047:     */  public boolean preLoad()
/* 1048:     */  {
/* 1049:1049 */    if (this.channel == null) {
/* 1050:1050 */      return false;
/* 1051:     */    }
/* 1052:1052 */    if (this.codec == null) {
/* 1053:1053 */      return false;
/* 1054:     */    }
/* 1055:1055 */    SoundBuffer buffer = null;
/* 1056:     */    
/* 1057:1057 */    boolean noNextBuffers = false;
/* 1058:1058 */    synchronized (this.soundSequenceLock)
/* 1059:     */    {
/* 1060:1060 */      if ((this.nextBuffers == null) || (this.nextBuffers.isEmpty())) {
/* 1061:1061 */        noNextBuffers = true;
/* 1062:     */      }
/* 1063:     */    }
/* 1064:1064 */    if ((this.nextCodec != null) && (!noNextBuffers))
/* 1065:     */    {
/* 1066:1066 */      this.codec = this.nextCodec;
/* 1067:1067 */      this.nextCodec = null;
/* 1068:1068 */      synchronized (this.soundSequenceLock)
/* 1069:     */      {
/* 1070:1070 */        while (!this.nextBuffers.isEmpty())
/* 1071:     */        {
/* 1072:1072 */          buffer = (SoundBuffer)this.nextBuffers.remove(0);
/* 1073:1073 */          if (buffer != null)
/* 1074:     */          {
/* 1075:1075 */            if (buffer.audioData != null)
/* 1076:1076 */              this.channel.queueBuffer(buffer.audioData);
/* 1077:1077 */            buffer.cleanup();
/* 1078:1078 */            buffer = null;
/* 1079:     */          }
/* 1080:     */        }
/* 1081:     */      }
/* 1082:     */    }
/* 1083:     */    else
/* 1084:     */    {
/* 1085:1085 */      this.nextCodec = null;
/* 1086:1086 */      URL url = this.filenameURL.getURL();
/* 1087:     */      
/* 1088:1088 */      this.codec.initialize(url);
/* 1089:1089 */      for (int i = 0; i < SoundSystemConfig.getNumberStreamingBuffers(); 
/* 1090:1090 */          i++)
/* 1091:     */      {
/* 1092:1092 */        buffer = this.codec.read();
/* 1093:1093 */        if (buffer != null)
/* 1094:     */        {
/* 1095:1095 */          if (buffer.audioData != null)
/* 1096:1096 */            this.channel.queueBuffer(buffer.audioData);
/* 1097:1097 */          buffer.cleanup();
/* 1098:1098 */          buffer = null;
/* 1099:     */        }
/* 1100:     */      }
/* 1101:     */    }
/* 1102:     */    
/* 1103:1103 */    return true;
/* 1104:     */  }
/* 1105:     */  
/* 1109:     */  public void pause()
/* 1110:     */  {
/* 1111:1111 */    this.toPlay = false;
/* 1112:1112 */    paused(true, true);
/* 1113:1113 */    if (this.channel != null) {
/* 1114:1114 */      this.channel.pause();
/* 1115:     */    } else {
/* 1116:1116 */      errorMessage("Channel null in method 'pause'");
/* 1117:     */    }
/* 1118:     */  }
/* 1119:     */  
/* 1122:     */  public void stop()
/* 1123:     */  {
/* 1124:1124 */    this.toPlay = false;
/* 1125:1125 */    stopped(true, true);
/* 1126:1126 */    paused(true, false);
/* 1127:1127 */    if (this.channel != null) {
/* 1128:1128 */      this.channel.stop();
/* 1129:     */    } else {
/* 1130:1130 */      errorMessage("Channel null in method 'stop'");
/* 1131:     */    }
/* 1132:     */  }
/* 1133:     */  
/* 1136:     */  public void rewind()
/* 1137:     */  {
/* 1138:1138 */    if (paused(false, false))
/* 1139:     */    {
/* 1140:1140 */      stop();
/* 1141:     */    }
/* 1142:1142 */    if (this.channel != null)
/* 1143:     */    {
/* 1144:1144 */      boolean rePlay = playing();
/* 1145:1145 */      this.channel.rewind();
/* 1146:1146 */      if ((this.toStream) && (rePlay))
/* 1147:     */      {
/* 1148:1148 */        stop();
/* 1149:1149 */        play(this.channel);
/* 1150:     */      }
/* 1151:     */    }
/* 1152:     */    else {
/* 1153:1153 */      errorMessage("Channel null in method 'rewind'");
/* 1154:     */    }
/* 1155:     */  }
/* 1156:     */  
/* 1159:     */  public void flush()
/* 1160:     */  {
/* 1161:1161 */    if (this.channel != null) {
/* 1162:1162 */      this.channel.flush();
/* 1163:     */    } else {
/* 1164:1164 */      errorMessage("Channel null in method 'flush'");
/* 1165:     */    }
/* 1166:     */  }
/* 1167:     */  
/* 1171:     */  public void cull()
/* 1172:     */  {
/* 1173:1173 */    if (!active(false, false))
/* 1174:1174 */      return;
/* 1175:1175 */    if ((playing()) && (this.toLoop))
/* 1176:1176 */      this.toPlay = true;
/* 1177:1177 */    if (this.rawDataStream)
/* 1178:1178 */      this.toPlay = true;
/* 1179:1179 */    active(true, false);
/* 1180:1180 */    if (this.channel != null)
/* 1181:1181 */      this.channel.close();
/* 1182:1182 */    this.channel = null;
/* 1183:     */  }
/* 1184:     */  
/* 1188:     */  public void activate()
/* 1189:     */  {
/* 1190:1190 */    active(true, true);
/* 1191:     */  }
/* 1192:     */  
/* 1197:     */  public boolean active()
/* 1198:     */  {
/* 1199:1199 */    return active(false, false);
/* 1200:     */  }
/* 1201:     */  
/* 1206:     */  public boolean playing()
/* 1207:     */  {
/* 1208:1208 */    if ((this.channel == null) || (this.channel.attachedSource != this))
/* 1209:1209 */      return false;
/* 1210:1210 */    if ((paused()) || (stopped())) {
/* 1211:1211 */      return false;
/* 1212:     */    }
/* 1213:1213 */    return this.channel.playing();
/* 1214:     */  }
/* 1215:     */  
/* 1220:     */  public boolean stopped()
/* 1221:     */  {
/* 1222:1222 */    return stopped(false, false);
/* 1223:     */  }
/* 1224:     */  
/* 1229:     */  public boolean paused()
/* 1230:     */  {
/* 1231:1231 */    return paused(false, false);
/* 1232:     */  }
/* 1233:     */  
/* 1238:     */  public float millisecondsPlayed()
/* 1239:     */  {
/* 1240:1240 */    if (this.channel == null) {
/* 1241:1241 */      return -1.0F;
/* 1242:     */    }
/* 1243:1243 */    return this.channel.millisecondsPlayed();
/* 1244:     */  }
/* 1245:     */  
/* 1250:     */  private synchronized boolean active(boolean action, boolean value)
/* 1251:     */  {
/* 1252:1252 */    if (action == true)
/* 1253:1253 */      this.active = value;
/* 1254:1254 */    return this.active;
/* 1255:     */  }
/* 1256:     */  
/* 1261:     */  private synchronized boolean stopped(boolean action, boolean value)
/* 1262:     */  {
/* 1263:1263 */    if (action == true)
/* 1264:1264 */      this.stopped = value;
/* 1265:1265 */    return this.stopped;
/* 1266:     */  }
/* 1267:     */  
/* 1272:     */  private synchronized boolean paused(boolean action, boolean value)
/* 1273:     */  {
/* 1274:1274 */    if (action == true)
/* 1275:1275 */      this.paused = value;
/* 1276:1276 */    return this.paused;
/* 1277:     */  }
/* 1278:     */  
/* 1283:     */  public String getClassName()
/* 1284:     */  {
/* 1285:1285 */    String libTitle = SoundSystemConfig.getLibraryTitle(this.libraryType);
/* 1286:     */    
/* 1287:1287 */    if (libTitle.equals("No Sound")) {
/* 1288:1288 */      return "Source";
/* 1289:     */    }
/* 1290:1290 */    return "Source" + libTitle;
/* 1291:     */  }
/* 1292:     */  
/* 1296:     */  protected void message(String message)
/* 1297:     */  {
/* 1298:1298 */    this.logger.message(message, 0);
/* 1299:     */  }
/* 1300:     */  
/* 1305:     */  protected void importantMessage(String message)
/* 1306:     */  {
/* 1307:1307 */    this.logger.importantMessage(message, 0);
/* 1308:     */  }
/* 1309:     */  
/* 1316:     */  protected boolean errorCheck(boolean error, String message)
/* 1317:     */  {
/* 1318:1318 */    return this.logger.errorCheck(error, getClassName(), message, 0);
/* 1319:     */  }
/* 1320:     */  
/* 1325:     */  protected void errorMessage(String message)
/* 1326:     */  {
/* 1327:1327 */    this.logger.errorMessage(getClassName(), message, 0);
/* 1328:     */  }
/* 1329:     */  
/* 1334:     */  protected void printStackTrace(Exception e)
/* 1335:     */  {
/* 1336:1336 */    this.logger.printStackTrace(e, 1);
/* 1337:     */  }
/* 1338:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.Source
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */