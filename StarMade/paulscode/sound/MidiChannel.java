/*    1:     */package paulscode.sound;
/*    2:     */
/*    3:     */import java.io.IOException;
/*    4:     */import java.net.URL;
/*    5:     */import java.util.LinkedList;
/*    6:     */import java.util.ListIterator;
/*    7:     */import javax.sound.midi.InvalidMidiDataException;
/*    8:     */import javax.sound.midi.MetaEventListener;
/*    9:     */import javax.sound.midi.MetaMessage;
/*   10:     */import javax.sound.midi.MidiDevice;
/*   11:     */import javax.sound.midi.MidiDevice.Info;
/*   12:     */import javax.sound.midi.MidiSystem;
/*   13:     */import javax.sound.midi.MidiUnavailableException;
/*   14:     */import javax.sound.midi.Receiver;
/*   15:     */import javax.sound.midi.Sequence;
/*   16:     */import javax.sound.midi.Sequencer;
/*   17:     */import javax.sound.midi.ShortMessage;
/*   18:     */import javax.sound.midi.Synthesizer;
/*   19:     */import javax.sound.midi.Transmitter;
/*   20:     */
/*   94:     */public class MidiChannel
/*   95:     */  implements MetaEventListener
/*   96:     */{
/*   97:     */  private SoundSystemLogger logger;
/*   98:     */  private FilenameURL filenameURL;
/*   99:     */  private String sourcename;
/*  100:     */  private static final int CHANGE_VOLUME = 7;
/*  101:     */  private static final int END_OF_TRACK = 47;
/*  102:     */  private static final boolean GET = false;
/*  103:     */  private static final boolean SET = true;
/*  104:     */  private static final boolean XXX = false;
/*  105: 105 */  private Sequencer sequencer = null;
/*  106:     */  
/*  110: 110 */  private Synthesizer synthesizer = null;
/*  111:     */  
/*  115: 115 */  private MidiDevice synthDevice = null;
/*  116:     */  
/*  120: 120 */  private Sequence sequence = null;
/*  121:     */  
/*  125: 125 */  private boolean toLoop = true;
/*  126:     */  
/*  130: 130 */  private float gain = 1.0F;
/*  131:     */  
/*  135: 135 */  private boolean loading = true;
/*  136:     */  
/*  140: 140 */  private LinkedList<FilenameURL> sequenceQueue = null;
/*  141:     */  
/*  145: 145 */  private final Object sequenceQueueLock = new Object();
/*  146:     */  
/*  151: 151 */  protected float fadeOutGain = -1.0F;
/*  152:     */  
/*  157: 157 */  protected float fadeInGain = 1.0F;
/*  158:     */  
/*  162: 162 */  protected long fadeOutMilis = 0L;
/*  163:     */  
/*  167: 167 */  protected long fadeInMilis = 0L;
/*  168:     */  
/*  172: 172 */  protected long lastFadeCheck = 0L;
/*  173:     */  
/*  177: 177 */  private FadeThread fadeThread = null;
/*  178:     */  
/*  186:     */  public MidiChannel(boolean toLoop, String sourcename, String filename)
/*  187:     */  {
/*  188: 188 */    loading(true, true);
/*  189:     */    
/*  191: 191 */    this.logger = SoundSystemConfig.getLogger();
/*  192:     */    
/*  194: 194 */    filenameURL(true, new FilenameURL(filename));
/*  195: 195 */    sourcename(true, sourcename);
/*  196: 196 */    setLooping(toLoop);
/*  197:     */    
/*  199: 199 */    init();
/*  200:     */    
/*  202: 202 */    loading(true, false);
/*  203:     */  }
/*  204:     */  
/*  216:     */  public MidiChannel(boolean toLoop, String sourcename, URL midiFile, String identifier)
/*  217:     */  {
/*  218: 218 */    loading(true, true);
/*  219:     */    
/*  221: 221 */    this.logger = SoundSystemConfig.getLogger();
/*  222:     */    
/*  224: 224 */    filenameURL(true, new FilenameURL(midiFile, identifier));
/*  225: 225 */    sourcename(true, sourcename);
/*  226: 226 */    setLooping(toLoop);
/*  227:     */    
/*  229: 229 */    init();
/*  230:     */    
/*  232: 232 */    loading(true, false);
/*  233:     */  }
/*  234:     */  
/*  243:     */  public MidiChannel(boolean toLoop, String sourcename, FilenameURL midiFilenameURL)
/*  244:     */  {
/*  245: 245 */    loading(true, true);
/*  246:     */    
/*  248: 248 */    this.logger = SoundSystemConfig.getLogger();
/*  249:     */    
/*  251: 251 */    filenameURL(true, midiFilenameURL);
/*  252: 252 */    sourcename(true, sourcename);
/*  253: 253 */    setLooping(toLoop);
/*  254:     */    
/*  256: 256 */    init();
/*  257:     */    
/*  259: 259 */    loading(true, false);
/*  260:     */  }
/*  261:     */  
/*  266:     */  private void init()
/*  267:     */  {
/*  268: 268 */    getSequencer();
/*  269:     */    
/*  271: 271 */    setSequence(filenameURL(false, null).getURL());
/*  272:     */    
/*  274: 274 */    getSynthesizer();
/*  275:     */    
/*  278: 278 */    resetGain();
/*  279:     */  }
/*  280:     */  
/*  284:     */  public void cleanup()
/*  285:     */  {
/*  286: 286 */    loading(true, true);
/*  287: 287 */    setLooping(true);
/*  288:     */    
/*  289: 289 */    if (this.sequencer != null)
/*  290:     */    {
/*  291:     */      try
/*  292:     */      {
/*  293: 293 */        this.sequencer.stop();
/*  294: 294 */        this.sequencer.close();
/*  295: 295 */        this.sequencer.removeMetaEventListener(this);
/*  296:     */      }
/*  297:     */      catch (Exception e) {}
/*  298:     */    }
/*  299:     */    
/*  301: 301 */    this.logger = null;
/*  302: 302 */    this.sequencer = null;
/*  303: 303 */    this.synthesizer = null;
/*  304: 304 */    this.sequence = null;
/*  305:     */    
/*  306: 306 */    synchronized (this.sequenceQueueLock)
/*  307:     */    {
/*  308: 308 */      if (this.sequenceQueue != null)
/*  309: 309 */        this.sequenceQueue.clear();
/*  310: 310 */      this.sequenceQueue = null;
/*  311:     */    }
/*  312:     */    
/*  314: 314 */    if (this.fadeThread != null)
/*  315:     */    {
/*  316: 316 */      boolean killException = false;
/*  317:     */      try
/*  318:     */      {
/*  319: 319 */        this.fadeThread.kill();
/*  320: 320 */        this.fadeThread.interrupt();
/*  321:     */      }
/*  322:     */      catch (Exception e)
/*  323:     */      {
/*  324: 324 */        killException = true;
/*  325:     */      }
/*  326:     */      
/*  327: 327 */      if (!killException)
/*  328:     */      {
/*  330: 330 */        for (int i = 0; i < 50; i++)
/*  331:     */        {
/*  332: 332 */          if (!this.fadeThread.alive())
/*  333:     */            break;
/*  334: 334 */          try { Thread.sleep(100L);
/*  335:     */          }
/*  336:     */          catch (InterruptedException e) {}
/*  337:     */        }
/*  338:     */      }
/*  339: 339 */      if ((killException) || (this.fadeThread.alive()))
/*  340:     */      {
/*  341: 341 */        errorMessage("MIDI fade effects thread did not die!");
/*  342: 342 */        message("Ignoring errors... continuing clean-up.");
/*  343:     */      }
/*  344:     */    }
/*  345:     */    
/*  346: 346 */    this.fadeThread = null;
/*  347:     */    
/*  348: 348 */    loading(true, false);
/*  349:     */  }
/*  350:     */  
/*  355:     */  public void queueSound(FilenameURL filenameURL)
/*  356:     */  {
/*  357: 357 */    if (filenameURL == null)
/*  358:     */    {
/*  359: 359 */      errorMessage("Filename/URL not specified in method 'queueSound'");
/*  360: 360 */      return;
/*  361:     */    }
/*  362:     */    
/*  363: 363 */    synchronized (this.sequenceQueueLock)
/*  364:     */    {
/*  365: 365 */      if (this.sequenceQueue == null)
/*  366: 366 */        this.sequenceQueue = new LinkedList();
/*  367: 367 */      this.sequenceQueue.add(filenameURL);
/*  368:     */    }
/*  369:     */  }
/*  370:     */  
/*  377:     */  public void dequeueSound(String filename)
/*  378:     */  {
/*  379: 379 */    if ((filename == null) || (filename.equals("")))
/*  380:     */    {
/*  381: 381 */      errorMessage("Filename not specified in method 'dequeueSound'");
/*  382: 382 */      return;
/*  383:     */    }
/*  384:     */    
/*  385: 385 */    synchronized (this.sequenceQueueLock)
/*  386:     */    {
/*  387: 387 */      if (this.sequenceQueue != null)
/*  388:     */      {
/*  389: 389 */        ListIterator<FilenameURL> i = this.sequenceQueue.listIterator();
/*  390: 390 */        while (i.hasNext())
/*  391:     */        {
/*  392: 392 */          if (((FilenameURL)i.next()).getFilename().equals(filename))
/*  393:     */          {
/*  394: 394 */            i.remove();
/*  395:     */          }
/*  396:     */        }
/*  397:     */      }
/*  398:     */    }
/*  399:     */  }
/*  400:     */  
/*  413:     */  public void fadeOut(FilenameURL filenameURL, long milis)
/*  414:     */  {
/*  415: 415 */    if (milis < 0L)
/*  416:     */    {
/*  417: 417 */      errorMessage("Miliseconds may not be negative in method 'fadeOut'.");
/*  418:     */      
/*  419: 419 */      return;
/*  420:     */    }
/*  421:     */    
/*  422: 422 */    this.fadeOutMilis = milis;
/*  423: 423 */    this.fadeInMilis = 0L;
/*  424: 424 */    this.fadeOutGain = 1.0F;
/*  425: 425 */    this.lastFadeCheck = System.currentTimeMillis();
/*  426:     */    
/*  427: 427 */    synchronized (this.sequenceQueueLock)
/*  428:     */    {
/*  429: 429 */      if (this.sequenceQueue != null) {
/*  430: 430 */        this.sequenceQueue.clear();
/*  431:     */      }
/*  432: 432 */      if (filenameURL != null)
/*  433:     */      {
/*  434: 434 */        if (this.sequenceQueue == null)
/*  435: 435 */          this.sequenceQueue = new LinkedList();
/*  436: 436 */        this.sequenceQueue.add(filenameURL);
/*  437:     */      }
/*  438:     */    }
/*  439: 439 */    if (this.fadeThread == null)
/*  440:     */    {
/*  441: 441 */      this.fadeThread = new FadeThread(null);
/*  442: 442 */      this.fadeThread.start();
/*  443:     */    }
/*  444: 444 */    this.fadeThread.interrupt();
/*  445:     */  }
/*  446:     */  
/*  460:     */  public void fadeOutIn(FilenameURL filenameURL, long milisOut, long milisIn)
/*  461:     */  {
/*  462: 462 */    if (filenameURL == null)
/*  463:     */    {
/*  464: 464 */      errorMessage("Filename/URL not specified in method 'fadeOutIn'.");
/*  465: 465 */      return;
/*  466:     */    }
/*  467: 467 */    if ((milisOut < 0L) || (milisIn < 0L))
/*  468:     */    {
/*  469: 469 */      errorMessage("Miliseconds may not be negative in method 'fadeOutIn'.");
/*  470:     */      
/*  471: 471 */      return;
/*  472:     */    }
/*  473:     */    
/*  474: 474 */    this.fadeOutMilis = milisOut;
/*  475: 475 */    this.fadeInMilis = milisIn;
/*  476: 476 */    this.fadeOutGain = 1.0F;
/*  477: 477 */    this.lastFadeCheck = System.currentTimeMillis();
/*  478:     */    
/*  479: 479 */    synchronized (this.sequenceQueueLock)
/*  480:     */    {
/*  481: 481 */      if (this.sequenceQueue == null)
/*  482: 482 */        this.sequenceQueue = new LinkedList();
/*  483: 483 */      this.sequenceQueue.clear();
/*  484: 484 */      this.sequenceQueue.add(filenameURL);
/*  485:     */    }
/*  486: 486 */    if (this.fadeThread == null)
/*  487:     */    {
/*  488: 488 */      this.fadeThread = new FadeThread(null);
/*  489: 489 */      this.fadeThread.start();
/*  490:     */    }
/*  491: 491 */    this.fadeThread.interrupt();
/*  492:     */  }
/*  493:     */  
/*  501:     */  private synchronized boolean checkFadeOut()
/*  502:     */  {
/*  503: 503 */    if ((this.fadeOutGain == -1.0F) && (this.fadeInGain == 1.0F)) {
/*  504: 504 */      return false;
/*  505:     */    }
/*  506: 506 */    long currentTime = System.currentTimeMillis();
/*  507: 507 */    long milisPast = currentTime - this.lastFadeCheck;
/*  508: 508 */    this.lastFadeCheck = currentTime;
/*  509:     */    
/*  510: 510 */    if (this.fadeOutGain >= 0.0F)
/*  511:     */    {
/*  512: 512 */      if (this.fadeOutMilis == 0L)
/*  513:     */      {
/*  514: 514 */        this.fadeOutGain = 0.0F;
/*  515: 515 */        this.fadeInGain = 0.0F;
/*  516: 516 */        if (!incrementSequence())
/*  517: 517 */          stop();
/*  518: 518 */        rewind();
/*  519: 519 */        resetGain();
/*  520: 520 */        return false;
/*  521:     */      }
/*  522:     */      
/*  524: 524 */      float fadeOutReduction = (float)milisPast / (float)this.fadeOutMilis;
/*  525:     */      
/*  526: 526 */      this.fadeOutGain -= fadeOutReduction;
/*  527: 527 */      if (this.fadeOutGain <= 0.0F)
/*  528:     */      {
/*  529: 529 */        this.fadeOutGain = -1.0F;
/*  530: 530 */        this.fadeInGain = 0.0F;
/*  531: 531 */        if (!incrementSequence())
/*  532: 532 */          stop();
/*  533: 533 */        rewind();
/*  534: 534 */        resetGain();
/*  535: 535 */        return false;
/*  536:     */      }
/*  537:     */      
/*  538: 538 */      resetGain();
/*  539: 539 */      return true;
/*  540:     */    }
/*  541:     */    
/*  542: 542 */    if (this.fadeInGain < 1.0F)
/*  543:     */    {
/*  544: 544 */      this.fadeOutGain = -1.0F;
/*  545: 545 */      if (this.fadeInMilis == 0L)
/*  546:     */      {
/*  547: 547 */        this.fadeOutGain = -1.0F;
/*  548: 548 */        this.fadeInGain = 1.0F;
/*  549:     */      }
/*  550:     */      else
/*  551:     */      {
/*  552: 552 */        float fadeInIncrease = (float)milisPast / (float)this.fadeInMilis;
/*  553: 553 */        this.fadeInGain += fadeInIncrease;
/*  554: 554 */        if (this.fadeInGain >= 1.0F)
/*  555:     */        {
/*  556: 556 */          this.fadeOutGain = -1.0F;
/*  557: 557 */          this.fadeInGain = 1.0F;
/*  558:     */        }
/*  559:     */      }
/*  560: 560 */      resetGain();
/*  561:     */    }
/*  562:     */    
/*  563: 563 */    return false;
/*  564:     */  }
/*  565:     */  
/*  570:     */  private boolean incrementSequence()
/*  571:     */  {
/*  572: 572 */    synchronized (this.sequenceQueueLock)
/*  573:     */    {
/*  575: 575 */      if ((this.sequenceQueue != null) && (this.sequenceQueue.size() > 0))
/*  576:     */      {
/*  578: 578 */        filenameURL(true, (FilenameURL)this.sequenceQueue.remove(0));
/*  579:     */        
/*  581: 581 */        loading(true, true);
/*  582:     */        
/*  584: 584 */        if (this.sequencer == null)
/*  585:     */        {
/*  587: 587 */          getSequencer();
/*  589:     */        }
/*  590:     */        else
/*  591:     */        {
/*  592: 592 */          this.sequencer.stop();
/*  593:     */          
/*  594: 594 */          this.sequencer.setMicrosecondPosition(0L);
/*  595:     */          
/*  596: 596 */          this.sequencer.removeMetaEventListener(this);
/*  597:     */          try {
/*  598: 598 */            Thread.sleep(100L);
/*  599:     */          } catch (InterruptedException e) {}
/*  600:     */        }
/*  601: 601 */        if (this.sequencer == null)
/*  602:     */        {
/*  603: 603 */          errorMessage("Unable to set the sequence in method 'incrementSequence', because there wasn't a sequencer to use.");
/*  604:     */          
/*  608: 608 */          loading(true, false);
/*  609:     */          
/*  611: 611 */          return false;
/*  612:     */        }
/*  613:     */        
/*  614: 614 */        setSequence(filenameURL(false, null).getURL());
/*  615:     */        
/*  616: 616 */        this.sequencer.start();
/*  617:     */        
/*  619: 619 */        resetGain();
/*  620:     */        
/*  621: 621 */        this.sequencer.addMetaEventListener(this);
/*  622:     */        
/*  624: 624 */        loading(true, false);
/*  625:     */        
/*  627: 627 */        return true;
/*  628:     */      }
/*  629:     */    }
/*  630:     */    
/*  632: 632 */    return false;
/*  633:     */  }
/*  634:     */  
/*  639:     */  public void play()
/*  640:     */  {
/*  641: 641 */    if (!loading())
/*  642:     */    {
/*  644: 644 */      if (this.sequencer == null) {
/*  645: 645 */        return;
/*  646:     */      }
/*  647:     */      
/*  648:     */      try
/*  649:     */      {
/*  650: 650 */        this.sequencer.start();
/*  651:     */        
/*  652: 652 */        this.sequencer.addMetaEventListener(this);
/*  653:     */      }
/*  654:     */      catch (Exception e)
/*  655:     */      {
/*  656: 656 */        errorMessage("Exception in method 'play'");
/*  657: 657 */        printStackTrace(e);
/*  658: 658 */        SoundSystemException sse = new SoundSystemException(e.getMessage());
/*  659:     */        
/*  660: 660 */        SoundSystem.setException(sse);
/*  661:     */      }
/*  662:     */    }
/*  663:     */  }
/*  664:     */  
/*  668:     */  public void stop()
/*  669:     */  {
/*  670: 670 */    if (!loading())
/*  671:     */    {
/*  673: 673 */      if (this.sequencer == null) {
/*  674: 674 */        return;
/*  675:     */      }
/*  676:     */      
/*  677:     */      try
/*  678:     */      {
/*  679: 679 */        this.sequencer.stop();
/*  680:     */        
/*  681: 681 */        this.sequencer.setMicrosecondPosition(0L);
/*  682:     */        
/*  683: 683 */        this.sequencer.removeMetaEventListener(this);
/*  684:     */      }
/*  685:     */      catch (Exception e)
/*  686:     */      {
/*  687: 687 */        errorMessage("Exception in method 'stop'");
/*  688: 688 */        printStackTrace(e);
/*  689: 689 */        SoundSystemException sse = new SoundSystemException(e.getMessage());
/*  690:     */        
/*  691: 691 */        SoundSystem.setException(sse);
/*  692:     */      }
/*  693:     */    }
/*  694:     */  }
/*  695:     */  
/*  699:     */  public void pause()
/*  700:     */  {
/*  701: 701 */    if (!loading())
/*  702:     */    {
/*  704: 704 */      if (this.sequencer == null) {
/*  705: 705 */        return;
/*  706:     */      }
/*  707:     */      
/*  708:     */      try
/*  709:     */      {
/*  710: 710 */        this.sequencer.stop();
/*  711:     */      }
/*  712:     */      catch (Exception e)
/*  713:     */      {
/*  714: 714 */        errorMessage("Exception in method 'pause'");
/*  715: 715 */        printStackTrace(e);
/*  716: 716 */        SoundSystemException sse = new SoundSystemException(e.getMessage());
/*  717:     */        
/*  718: 718 */        SoundSystem.setException(sse);
/*  719:     */      }
/*  720:     */    }
/*  721:     */  }
/*  722:     */  
/*  726:     */  public void rewind()
/*  727:     */  {
/*  728: 728 */    if (!loading())
/*  729:     */    {
/*  731: 731 */      if (this.sequencer == null) {
/*  732: 732 */        return;
/*  733:     */      }
/*  734:     */      
/*  735:     */      try
/*  736:     */      {
/*  737: 737 */        this.sequencer.setMicrosecondPosition(0L);
/*  738:     */      }
/*  739:     */      catch (Exception e)
/*  740:     */      {
/*  741: 741 */        errorMessage("Exception in method 'rewind'");
/*  742: 742 */        printStackTrace(e);
/*  743: 743 */        SoundSystemException sse = new SoundSystemException(e.getMessage());
/*  744:     */        
/*  745: 745 */        SoundSystem.setException(sse);
/*  746:     */      }
/*  747:     */    }
/*  748:     */  }
/*  749:     */  
/*  754:     */  public void setVolume(float value)
/*  755:     */  {
/*  756: 756 */    this.gain = value;
/*  757: 757 */    resetGain();
/*  758:     */  }
/*  759:     */  
/*  764:     */  public float getVolume()
/*  765:     */  {
/*  766: 766 */    return this.gain;
/*  767:     */  }
/*  768:     */  
/*  779:     */  public void switchSource(boolean toLoop, String sourcename, String filename)
/*  780:     */  {
/*  781: 781 */    loading(true, true);
/*  782:     */    
/*  784: 784 */    filenameURL(true, new FilenameURL(filename));
/*  785: 785 */    sourcename(true, sourcename);
/*  786: 786 */    setLooping(toLoop);
/*  787:     */    
/*  788: 788 */    reset();
/*  789:     */    
/*  791: 791 */    loading(true, false);
/*  792:     */  }
/*  793:     */  
/*  807:     */  public void switchSource(boolean toLoop, String sourcename, URL midiFile, String identifier)
/*  808:     */  {
/*  809: 809 */    loading(true, true);
/*  810:     */    
/*  812: 812 */    filenameURL(true, new FilenameURL(midiFile, identifier));
/*  813: 813 */    sourcename(true, sourcename);
/*  814: 814 */    setLooping(toLoop);
/*  815:     */    
/*  816: 816 */    reset();
/*  817:     */    
/*  819: 819 */    loading(true, false);
/*  820:     */  }
/*  821:     */  
/*  832:     */  public void switchSource(boolean toLoop, String sourcename, FilenameURL filenameURL)
/*  833:     */  {
/*  834: 834 */    loading(true, true);
/*  835:     */    
/*  837: 837 */    filenameURL(true, filenameURL);
/*  838: 838 */    sourcename(true, sourcename);
/*  839: 839 */    setLooping(toLoop);
/*  840:     */    
/*  841: 841 */    reset();
/*  842:     */    
/*  844: 844 */    loading(true, false);
/*  845:     */  }
/*  846:     */  
/*  850:     */  private void reset()
/*  851:     */  {
/*  852: 852 */    synchronized (this.sequenceQueueLock)
/*  853:     */    {
/*  854: 854 */      if (this.sequenceQueue != null) {
/*  855: 855 */        this.sequenceQueue.clear();
/*  856:     */      }
/*  857:     */    }
/*  858:     */    
/*  859: 859 */    if (this.sequencer == null)
/*  860:     */    {
/*  862: 862 */      getSequencer();
/*  864:     */    }
/*  865:     */    else
/*  866:     */    {
/*  867: 867 */      this.sequencer.stop();
/*  868:     */      
/*  869: 869 */      this.sequencer.setMicrosecondPosition(0L);
/*  870:     */      
/*  871: 871 */      this.sequencer.removeMetaEventListener(this);
/*  872:     */      try {
/*  873: 873 */        Thread.sleep(100L);
/*  874:     */      } catch (InterruptedException e) {}
/*  875:     */    }
/*  876: 876 */    if (this.sequencer == null)
/*  877:     */    {
/*  878: 878 */      errorMessage("Unable to set the sequence in method 'reset', because there wasn't a sequencer to use.");
/*  879:     */      
/*  881: 881 */      return;
/*  882:     */    }
/*  883:     */    
/*  885: 885 */    setSequence(filenameURL(false, null).getURL());
/*  886:     */    
/*  887: 887 */    this.sequencer.start();
/*  888:     */    
/*  890: 890 */    resetGain();
/*  891:     */    
/*  892: 892 */    this.sequencer.addMetaEventListener(this);
/*  893:     */  }
/*  894:     */  
/*  899:     */  public void setLooping(boolean value)
/*  900:     */  {
/*  901: 901 */    toLoop(true, value);
/*  902:     */  }
/*  903:     */  
/*  908:     */  public boolean getLooping()
/*  909:     */  {
/*  910: 910 */    return toLoop(false, false);
/*  911:     */  }
/*  912:     */  
/*  919:     */  private synchronized boolean toLoop(boolean action, boolean value)
/*  920:     */  {
/*  921: 921 */    if (action == true)
/*  922: 922 */      this.toLoop = value;
/*  923: 923 */    return this.toLoop;
/*  924:     */  }
/*  925:     */  
/*  929:     */  public boolean loading()
/*  930:     */  {
/*  931: 931 */    return loading(false, false);
/*  932:     */  }
/*  933:     */  
/*  940:     */  private synchronized boolean loading(boolean action, boolean value)
/*  941:     */  {
/*  942: 942 */    if (action == true)
/*  943: 943 */      this.loading = value;
/*  944: 944 */    return this.loading;
/*  945:     */  }
/*  946:     */  
/*  951:     */  public void setSourcename(String value)
/*  952:     */  {
/*  953: 953 */    sourcename(true, value);
/*  954:     */  }
/*  955:     */  
/*  960:     */  public String getSourcename()
/*  961:     */  {
/*  962: 962 */    return sourcename(false, null);
/*  963:     */  }
/*  964:     */  
/*  971:     */  private synchronized String sourcename(boolean action, String value)
/*  972:     */  {
/*  973: 973 */    if (action == true)
/*  974: 974 */      this.sourcename = value;
/*  975: 975 */    return this.sourcename;
/*  976:     */  }
/*  977:     */  
/*  982:     */  public void setFilenameURL(FilenameURL value)
/*  983:     */  {
/*  984: 984 */    filenameURL(true, value);
/*  985:     */  }
/*  986:     */  
/*  991:     */  public String getFilename()
/*  992:     */  {
/*  993: 993 */    return filenameURL(false, null).getFilename();
/*  994:     */  }
/*  995:     */  
/* 1000:     */  public FilenameURL getFilenameURL()
/* 1001:     */  {
/* 1002:1002 */    return filenameURL(false, null);
/* 1003:     */  }
/* 1004:     */  
/* 1012:     */  private synchronized FilenameURL filenameURL(boolean action, FilenameURL value)
/* 1013:     */  {
/* 1014:1014 */    if (action == true)
/* 1015:1015 */      this.filenameURL = value;
/* 1016:1016 */    return this.filenameURL;
/* 1017:     */  }
/* 1018:     */  
/* 1023:     */  public void meta(MetaMessage message)
/* 1024:     */  {
/* 1025:1025 */    if (message.getType() == 47)
/* 1026:     */    {
/* 1028:1028 */      SoundSystemConfig.notifyEOS(this.sourcename, this.sequenceQueue.size());
/* 1029:     */      
/* 1031:1031 */      if (this.toLoop)
/* 1032:     */      {
/* 1035:1035 */        if (!checkFadeOut())
/* 1036:     */        {
/* 1039:1039 */          if (!incrementSequence())
/* 1040:     */          {
/* 1041:     */            try
/* 1042:     */            {
/* 1044:1044 */              this.sequencer.setMicrosecondPosition(0L);
/* 1045:1045 */              this.sequencer.start();
/* 1046:     */              
/* 1047:1047 */              resetGain();
/* 1048:     */            }
/* 1049:     */            catch (Exception e) {}
/* 1050:     */          }
/* 1051:     */        }
/* 1052:1052 */        else if (this.sequencer != null)
/* 1053:     */        {
/* 1054:     */          try
/* 1055:     */          {
/* 1057:1057 */            this.sequencer.setMicrosecondPosition(0L);
/* 1058:1058 */            this.sequencer.start();
/* 1059:     */            
/* 1060:1060 */            resetGain();
/* 1062:     */          }
/* 1063:     */          catch (Exception e) {}
/* 1064:     */        }
/* 1065:     */        
/* 1067:     */      }
/* 1068:1068 */      else if (!checkFadeOut())
/* 1069:     */      {
/* 1070:1070 */        if (!incrementSequence())
/* 1071:     */        {
/* 1072:     */          try
/* 1073:     */          {
/* 1075:1075 */            this.sequencer.stop();
/* 1076:     */            
/* 1077:1077 */            this.sequencer.setMicrosecondPosition(0L);
/* 1078:     */            
/* 1079:1079 */            this.sequencer.removeMetaEventListener(this);
/* 1081:     */          }
/* 1082:     */          catch (Exception e) {}
/* 1083:     */        }
/* 1084:     */        
/* 1085:     */      }
/* 1086:     */      else {
/* 1087:     */        try
/* 1088:     */        {
/* 1089:1089 */          this.sequencer.stop();
/* 1090:     */          
/* 1091:1091 */          this.sequencer.setMicrosecondPosition(0L);
/* 1092:     */          
/* 1093:1093 */          this.sequencer.removeMetaEventListener(this);
/* 1094:     */        }
/* 1095:     */        catch (Exception e) {}
/* 1096:     */      }
/* 1097:     */    }
/* 1098:     */  }
/* 1099:     */  
/* 1105:     */  public void resetGain()
/* 1106:     */  {
/* 1107:1107 */    if (this.gain < 0.0F)
/* 1108:1108 */      this.gain = 0.0F;
/* 1109:1109 */    if (this.gain > 1.0F) {
/* 1110:1110 */      this.gain = 1.0F;
/* 1111:     */    }
/* 1112:1112 */    int midiVolume = (int)(this.gain * SoundSystemConfig.getMasterGain() * Math.abs(this.fadeOutGain) * this.fadeInGain * 127.0F);
/* 1113:     */    
/* 1115:1115 */    if (this.synthesizer != null)
/* 1116:     */    {
/* 1117:1117 */      javax.sound.midi.MidiChannel[] channels = this.synthesizer.getChannels();
/* 1118:1118 */      for (int c = 0; (channels != null) && (c < channels.length); c++)
/* 1119:     */      {
/* 1120:1120 */        channels[c].controlChange(7, midiVolume);
/* 1121:     */      }
/* 1122:     */    }
/* 1123:1123 */    else if (this.synthDevice != null)
/* 1124:     */    {
/* 1125:     */      try
/* 1126:     */      {
/* 1127:1127 */        ShortMessage volumeMessage = new ShortMessage();
/* 1128:1128 */        for (int i = 0; i < 16; i++)
/* 1129:     */        {
/* 1130:1130 */          volumeMessage.setMessage(176, i, 7, midiVolume);
/* 1131:     */          
/* 1132:1132 */          this.synthDevice.getReceiver().send(volumeMessage, -1L);
/* 1133:     */        }
/* 1134:     */      }
/* 1135:     */      catch (Exception e)
/* 1136:     */      {
/* 1137:1137 */        errorMessage("Error resetting gain on MIDI device");
/* 1138:1138 */        printStackTrace(e);
/* 1139:     */      }
/* 1140:     */    }
/* 1141:1141 */    else if ((this.sequencer != null) && ((this.sequencer instanceof Synthesizer)))
/* 1142:     */    {
/* 1143:1143 */      this.synthesizer = ((Synthesizer)this.sequencer);
/* 1144:1144 */      javax.sound.midi.MidiChannel[] channels = this.synthesizer.getChannels();
/* 1145:1145 */      for (int c = 0; (channels != null) && (c < channels.length); c++)
/* 1146:     */      {
/* 1147:1147 */        channels[c].controlChange(7, midiVolume);
/* 1148:     */      }
/* 1149:     */    }
/* 1150:     */    else
/* 1151:     */    {
/* 1152:     */      try
/* 1153:     */      {
/* 1154:1154 */        Receiver receiver = MidiSystem.getReceiver();
/* 1155:1155 */        ShortMessage volumeMessage = new ShortMessage();
/* 1156:1156 */        for (int c = 0; c < 16; c++)
/* 1157:     */        {
/* 1158:1158 */          volumeMessage.setMessage(176, c, 7, midiVolume);
/* 1159:     */          
/* 1160:1160 */          receiver.send(volumeMessage, -1L);
/* 1161:     */        }
/* 1162:     */      }
/* 1163:     */      catch (Exception e)
/* 1164:     */      {
/* 1165:1165 */        errorMessage("Error resetting gain on default receiver");
/* 1166:1166 */        printStackTrace(e);
/* 1167:     */      }
/* 1168:     */    }
/* 1169:     */  }
/* 1170:     */  
/* 1176:     */  private void getSequencer()
/* 1177:     */  {
/* 1178:     */    try
/* 1179:     */    {
/* 1180:1180 */      this.sequencer = MidiSystem.getSequencer();
/* 1181:1181 */      if (this.sequencer != null)
/* 1182:     */      {
/* 1183:     */        try
/* 1184:     */        {
/* 1185:1185 */          this.sequencer.getTransmitter();
/* 1186:     */        }
/* 1187:     */        catch (MidiUnavailableException mue)
/* 1188:     */        {
/* 1189:1189 */          message("Unable to get a transmitter from the default MIDI sequencer");
/* 1190:     */        }
/* 1191:     */        
/* 1192:1192 */        this.sequencer.open();
/* 1193:     */      }
/* 1194:     */    }
/* 1195:     */    catch (MidiUnavailableException mue)
/* 1196:     */    {
/* 1197:1197 */      message("Unable to open the default MIDI sequencer");
/* 1198:1198 */      this.sequencer = null;
/* 1199:     */    }
/* 1200:     */    catch (Exception e)
/* 1201:     */    {
/* 1202:1202 */      if ((e instanceof InterruptedException))
/* 1203:     */      {
/* 1204:1204 */        message("Caught InterruptedException while attempting to open the default MIDI sequencer.  Trying again.");
/* 1205:     */        
/* 1206:1206 */        this.sequencer = null;
/* 1207:     */      }
/* 1208:     */      try
/* 1209:     */      {
/* 1210:1210 */        this.sequencer = MidiSystem.getSequencer();
/* 1211:1211 */        if (this.sequencer != null)
/* 1212:     */        {
/* 1213:     */          try
/* 1214:     */          {
/* 1215:1215 */            this.sequencer.getTransmitter();
/* 1216:     */          }
/* 1217:     */          catch (MidiUnavailableException mue)
/* 1218:     */          {
/* 1219:1219 */            message("Unable to get a transmitter from the default MIDI sequencer");
/* 1220:     */          }
/* 1221:     */          
/* 1222:1222 */          this.sequencer.open();
/* 1223:     */        }
/* 1224:     */      }
/* 1225:     */      catch (MidiUnavailableException mue)
/* 1226:     */      {
/* 1227:1227 */        message("Unable to open the default MIDI sequencer");
/* 1228:1228 */        this.sequencer = null;
/* 1229:     */      }
/* 1230:     */      catch (Exception e2)
/* 1231:     */      {
/* 1232:1232 */        message("Unknown error opening the default MIDI sequencer");
/* 1233:1233 */        this.sequencer = null;
/* 1234:     */      }
/* 1235:     */    }
/* 1236:     */    
/* 1237:1237 */    if (this.sequencer == null)
/* 1238:1238 */      this.sequencer = openSequencer("Real Time Sequencer");
/* 1239:1239 */    if (this.sequencer == null)
/* 1240:1240 */      this.sequencer = openSequencer("Java Sound Sequencer");
/* 1241:1241 */    if (this.sequencer == null)
/* 1242:     */    {
/* 1243:1243 */      errorMessage("Failed to find an available MIDI sequencer");
/* 1244:1244 */      return;
/* 1245:     */    }
/* 1246:     */  }
/* 1247:     */  
/* 1254:     */  private void setSequence(URL midiSource)
/* 1255:     */  {
/* 1256:1256 */    if (this.sequencer == null)
/* 1257:     */    {
/* 1258:1258 */      errorMessage("Unable to update the sequence in method 'setSequence', because variable 'sequencer' is null");
/* 1259:     */      
/* 1261:1261 */      return;
/* 1262:     */    }
/* 1263:     */    
/* 1264:1264 */    if (midiSource == null)
/* 1265:     */    {
/* 1266:1266 */      errorMessage("Unable to load Midi file in method 'setSequence'.");
/* 1267:1267 */      return;
/* 1268:     */    }
/* 1269:     */    
/* 1270:     */    try
/* 1271:     */    {
/* 1272:1272 */      this.sequence = MidiSystem.getSequence(midiSource);
/* 1273:     */    }
/* 1274:     */    catch (IOException ioe)
/* 1275:     */    {
/* 1276:1276 */      errorMessage("Input failed while reading from MIDI file in method 'setSequence'.");
/* 1277:     */      
/* 1278:1278 */      printStackTrace(ioe);
/* 1279:1279 */      return;
/* 1280:     */    }
/* 1281:     */    catch (InvalidMidiDataException imde)
/* 1282:     */    {
/* 1283:1283 */      errorMessage("Invalid MIDI data encountered, or not a MIDI file in method 'setSequence' (1).");
/* 1284:     */      
/* 1285:1285 */      printStackTrace(imde);
/* 1286:1286 */      return;
/* 1287:     */    }
/* 1288:1288 */    if (this.sequence == null)
/* 1289:     */    {
/* 1290:1290 */      errorMessage("MidiSystem 'getSequence' method returned null in method 'setSequence'.");
/* 1291:     */    }
/* 1292:     */    else
/* 1293:     */    {
/* 1294:     */      try
/* 1295:     */      {
/* 1297:1297 */        this.sequencer.setSequence(this.sequence);
/* 1298:     */      }
/* 1299:     */      catch (InvalidMidiDataException imde)
/* 1300:     */      {
/* 1301:1301 */        errorMessage("Invalid MIDI data encountered, or not a MIDI file in method 'setSequence' (2).");
/* 1302:     */        
/* 1303:1303 */        printStackTrace(imde);
/* 1304:1304 */        return;
/* 1305:     */      }
/* 1306:     */      catch (Exception e)
/* 1307:     */      {
/* 1308:1308 */        errorMessage("Problem setting sequence from MIDI file in method 'setSequence'.");
/* 1309:     */        
/* 1310:1310 */        printStackTrace(e);
/* 1311:1311 */        return;
/* 1312:     */      }
/* 1313:     */    }
/* 1314:     */  }
/* 1315:     */  
/* 1322:     */  private void getSynthesizer()
/* 1323:     */  {
/* 1324:1324 */    if (this.sequencer == null)
/* 1325:     */    {
/* 1326:1326 */      errorMessage("Unable to load a Synthesizer in method 'getSynthesizer', because variable 'sequencer' is null");
/* 1327:     */      
/* 1329:1329 */      return;
/* 1330:     */    }
/* 1331:     */    
/* 1333:1333 */    String overrideMIDISynthesizer = SoundSystemConfig.getOverrideMIDISynthesizer();
/* 1334:     */    
/* 1335:1335 */    if ((overrideMIDISynthesizer != null) && (!overrideMIDISynthesizer.equals("")))
/* 1336:     */    {
/* 1339:1339 */      this.synthDevice = openMidiDevice(overrideMIDISynthesizer);
/* 1340:     */      
/* 1341:1341 */      if (this.synthDevice != null)
/* 1342:     */      {
/* 1343:     */        try
/* 1344:     */        {
/* 1346:1346 */          this.sequencer.getTransmitter().setReceiver(this.synthDevice.getReceiver());
/* 1347:     */          
/* 1349:1349 */          return;
/* 1351:     */        }
/* 1352:     */        catch (MidiUnavailableException mue)
/* 1353:     */        {
/* 1354:1354 */          errorMessage("Unable to link sequencer transmitter with receiver for MIDI device '" + overrideMIDISynthesizer + "'");
/* 1355:     */        }
/* 1356:     */      }
/* 1357:     */    }
/* 1358:     */    
/* 1364:1364 */    if ((this.sequencer instanceof Synthesizer))
/* 1365:     */    {
/* 1366:1366 */      this.synthesizer = ((Synthesizer)this.sequencer);
/* 1368:     */    }
/* 1369:     */    else
/* 1370:     */    {
/* 1371:     */      try
/* 1372:     */      {
/* 1373:1373 */        this.synthesizer = MidiSystem.getSynthesizer();
/* 1374:1374 */        this.synthesizer.open();
/* 1375:     */      }
/* 1376:     */      catch (MidiUnavailableException mue)
/* 1377:     */      {
/* 1378:1378 */        message("Unable to open the default synthesizer");
/* 1379:1379 */        this.synthesizer = null;
/* 1380:     */      }
/* 1381:     */      
/* 1383:1383 */      if (this.synthesizer == null)
/* 1384:     */      {
/* 1386:1386 */        this.synthDevice = openMidiDevice("Java Sound Synthesizer");
/* 1387:1387 */        if (this.synthDevice == null)
/* 1388:1388 */          this.synthDevice = openMidiDevice("Microsoft GS Wavetable");
/* 1389:1389 */        if (this.synthDevice == null)
/* 1390:1390 */          this.synthDevice = openMidiDevice("Gervill");
/* 1391:1391 */        if (this.synthDevice == null)
/* 1392:     */        {
/* 1394:1394 */          errorMessage("Failed to find an available MIDI synthesizer");
/* 1395:     */          
/* 1396:1396 */          return;
/* 1397:     */        }
/* 1398:     */      }
/* 1399:     */      
/* 1401:1401 */      if (this.synthesizer == null)
/* 1402:     */      {
/* 1403:     */        try
/* 1404:     */        {
/* 1406:1406 */          this.sequencer.getTransmitter().setReceiver(this.synthDevice.getReceiver());
/* 1408:     */        }
/* 1409:     */        catch (MidiUnavailableException mue)
/* 1410:     */        {
/* 1411:1411 */          errorMessage("Unable to link sequencer transmitter with MIDI device receiver");
/* 1414:     */        }
/* 1415:     */        
/* 1417:     */      }
/* 1418:1418 */      else if (this.synthesizer.getDefaultSoundbank() == null)
/* 1419:     */      {
/* 1420:     */        try
/* 1421:     */        {
/* 1423:1423 */          this.sequencer.getTransmitter().setReceiver(MidiSystem.getReceiver());
/* 1425:     */        }
/* 1426:     */        catch (MidiUnavailableException mue)
/* 1427:     */        {
/* 1428:1428 */          errorMessage("Unable to link sequencer transmitter with default receiver");
/* 1429:     */        }
/* 1430:     */        
/* 1431:     */      }
/* 1432:     */      else
/* 1433:     */      {
/* 1434:     */        try
/* 1435:     */        {
/* 1437:1437 */          this.sequencer.getTransmitter().setReceiver(this.synthesizer.getReceiver());
/* 1439:     */        }
/* 1440:     */        catch (MidiUnavailableException mue)
/* 1441:     */        {
/* 1442:1442 */          errorMessage("Unable to link sequencer transmitter with synthesizer receiver");
/* 1443:     */        }
/* 1444:     */      }
/* 1445:     */    }
/* 1446:     */  }
/* 1447:     */  
/* 1456:     */  private Sequencer openSequencer(String containsString)
/* 1457:     */  {
/* 1458:1458 */    Sequencer s = null;
/* 1459:1459 */    s = (Sequencer)openMidiDevice(containsString);
/* 1460:1460 */    if (s == null) {
/* 1461:1461 */      return null;
/* 1462:     */    }
/* 1463:     */    try {
/* 1464:1464 */      s.getTransmitter();
/* 1465:     */    }
/* 1466:     */    catch (MidiUnavailableException mue)
/* 1467:     */    {
/* 1468:1468 */      message("    Unable to get a transmitter from this sequencer");
/* 1469:1469 */      s = null;
/* 1470:1470 */      return null;
/* 1471:     */    }
/* 1472:     */    
/* 1473:1473 */    return s;
/* 1474:     */  }
/* 1475:     */  
/* 1482:     */  private MidiDevice openMidiDevice(String containsString)
/* 1483:     */  {
/* 1484:1484 */    message("Searching for MIDI device with name containing '" + containsString + "'");
/* 1485:     */    
/* 1486:1486 */    MidiDevice device = null;
/* 1487:1487 */    MidiDevice.Info[] midiDevices = MidiSystem.getMidiDeviceInfo();
/* 1488:1488 */    for (int i = 0; i < midiDevices.length; i++)
/* 1489:     */    {
/* 1490:1490 */      device = null;
/* 1491:     */      try
/* 1492:     */      {
/* 1493:1493 */        device = MidiSystem.getMidiDevice(midiDevices[i]);
/* 1494:     */      }
/* 1495:     */      catch (MidiUnavailableException e)
/* 1496:     */      {
/* 1497:1497 */        message("    Problem in method 'getMidiDevice':  MIDIUnavailableException was thrown");
/* 1498:     */        
/* 1499:1499 */        device = null;
/* 1500:     */      }
/* 1501:1501 */      if ((device != null) && (midiDevices[i].getName().contains(containsString)))
/* 1502:     */      {
/* 1504:1504 */        message("    Found MIDI device named '" + midiDevices[i].getName() + "'");
/* 1505:     */        
/* 1506:1506 */        if ((device instanceof Synthesizer))
/* 1507:1507 */          message("        *this is a Synthesizer instance");
/* 1508:1508 */        if ((device instanceof Sequencer)) {
/* 1509:1509 */          message("        *this is a Sequencer instance");
/* 1510:     */        }
/* 1511:     */        try {
/* 1512:1512 */          device.open();
/* 1513:     */        }
/* 1514:     */        catch (MidiUnavailableException mue)
/* 1515:     */        {
/* 1516:1516 */          message("    Unable to open this MIDI device");
/* 1517:1517 */          device = null;
/* 1518:     */        }
/* 1519:1519 */        return device;
/* 1520:     */      }
/* 1521:     */    }
/* 1522:1522 */    message("    MIDI device not found");
/* 1523:1523 */    return null;
/* 1524:     */  }
/* 1525:     */  
/* 1530:     */  protected void message(String message)
/* 1531:     */  {
/* 1532:1532 */    this.logger.message(message, 0);
/* 1533:     */  }
/* 1534:     */  
/* 1539:     */  protected void importantMessage(String message)
/* 1540:     */  {
/* 1541:1541 */    this.logger.importantMessage(message, 0);
/* 1542:     */  }
/* 1543:     */  
/* 1550:     */  protected boolean errorCheck(boolean error, String message)
/* 1551:     */  {
/* 1552:1552 */    return this.logger.errorCheck(error, "MidiChannel", message, 0);
/* 1553:     */  }
/* 1554:     */  
/* 1559:     */  protected void errorMessage(String message)
/* 1560:     */  {
/* 1561:1561 */    this.logger.errorMessage("MidiChannel", message, 0);
/* 1562:     */  }
/* 1563:     */  
/* 1568:     */  protected void printStackTrace(Exception e)
/* 1569:     */  {
/* 1570:1570 */    this.logger.printStackTrace(e, 1);
/* 1571:     */  }
/* 1572:     */  
/* 1576:     */  private class FadeThread
/* 1577:     */    extends SimpleThread
/* 1578:     */  {
/* 1579:     */    private FadeThread() {}
/* 1580:     */    
/* 1584:     */    public void run()
/* 1585:     */    {
/* 1586:1586 */      while (!dying())
/* 1587:     */      {
/* 1589:1589 */        if ((MidiChannel.this.fadeOutGain == -1.0F) && (MidiChannel.this.fadeInGain == 1.0F))
/* 1590:1590 */          snooze(3600000L);
/* 1591:1591 */        MidiChannel.this.checkFadeOut();
/* 1592:     */        
/* 1593:1593 */        snooze(50L);
/* 1594:     */      }
/* 1595:     */      
/* 1596:1596 */      cleanup();
/* 1597:     */    }
/* 1598:     */  }
/* 1599:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.MidiChannel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */