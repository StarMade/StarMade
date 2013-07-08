/*    1:     */package paulscode.sound;
/*    2:     */
/*    3:     */import java.util.HashMap;
/*    4:     */import java.util.Iterator;
/*    5:     */import java.util.LinkedList;
/*    6:     */import java.util.List;
/*    7:     */import java.util.Set;
/*    8:     */import javax.sound.sampled.AudioFormat;
/*    9:     */
/*   64:     */public class Library
/*   65:     */{
/*   66:     */  private SoundSystemLogger logger;
/*   67:     */  protected ListenerData listener;
/*   68:  68 */  protected HashMap<String, SoundBuffer> bufferMap = null;
/*   69:     */  
/*   73:     */  protected HashMap<String, Source> sourceMap;
/*   74:     */  
/*   78:     */  private MidiChannel midiChannel;
/*   79:     */  
/*   83:     */  protected List<Channel> streamingChannels;
/*   84:     */  
/*   88:     */  protected List<Channel> normalChannels;
/*   89:     */  
/*   93:     */  private String[] streamingChannelSourceNames;
/*   94:     */  
/*   98:     */  private String[] normalChannelSourceNames;
/*   99:     */  
/*  103: 103 */  private int nextStreamingChannel = 0;
/*  104:     */  
/*  108: 108 */  private int nextNormalChannel = 0;
/*  109:     */  
/*  113:     */  protected StreamThread streamThread;
/*  114:     */  
/*  118: 118 */  protected boolean reverseByteOrder = false;
/*  119:     */  
/*  126:     */  public Library()
/*  127:     */    throws SoundSystemException
/*  128:     */  {
/*  129: 129 */    this.logger = SoundSystemConfig.getLogger();
/*  130:     */    
/*  132: 132 */    this.bufferMap = new HashMap();
/*  133:     */    
/*  135: 135 */    this.sourceMap = new HashMap();
/*  136:     */    
/*  137: 137 */    this.listener = new ListenerData(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F);
/*  138:     */    
/*  142: 142 */    this.streamingChannels = new LinkedList();
/*  143: 143 */    this.normalChannels = new LinkedList();
/*  144: 144 */    this.streamingChannelSourceNames = new String[SoundSystemConfig.getNumberStreamingChannels()];
/*  145:     */    
/*  146: 146 */    this.normalChannelSourceNames = new String[SoundSystemConfig.getNumberNormalChannels()];
/*  147:     */    
/*  149: 149 */    this.streamThread = new StreamThread();
/*  150: 150 */    this.streamThread.start();
/*  151:     */  }
/*  152:     */  
/*  164:     */  public void cleanup()
/*  165:     */  {
/*  166: 166 */    this.streamThread.kill();
/*  167: 167 */    this.streamThread.interrupt();
/*  168:     */    
/*  170: 170 */    for (int i = 0; i < 50; i++)
/*  171:     */    {
/*  172: 172 */      if (!this.streamThread.alive()) {
/*  173:     */        break;
/*  174:     */      }
/*  175:     */      try {
/*  176: 176 */        Thread.sleep(100L);
/*  177:     */      }
/*  178:     */      catch (Exception e) {}
/*  179:     */    }
/*  180:     */    
/*  182: 182 */    if (this.streamThread.alive())
/*  183:     */    {
/*  184: 184 */      errorMessage("Stream thread did not die!");
/*  185: 185 */      message("Ignoring errors... continuing clean-up.");
/*  186:     */    }
/*  187:     */    
/*  188: 188 */    if (this.midiChannel != null)
/*  189:     */    {
/*  190: 190 */      this.midiChannel.cleanup();
/*  191: 191 */      this.midiChannel = null;
/*  192:     */    }
/*  193:     */    
/*  194: 194 */    Channel channel = null;
/*  195: 195 */    if (this.streamingChannels != null)
/*  196:     */    {
/*  197: 197 */      while (!this.streamingChannels.isEmpty())
/*  198:     */      {
/*  199: 199 */        channel = (Channel)this.streamingChannels.remove(0);
/*  200: 200 */        channel.close();
/*  201: 201 */        channel.cleanup();
/*  202: 202 */        channel = null;
/*  203:     */      }
/*  204: 204 */      this.streamingChannels.clear();
/*  205: 205 */      this.streamingChannels = null;
/*  206:     */    }
/*  207: 207 */    if (this.normalChannels != null)
/*  208:     */    {
/*  209: 209 */      while (!this.normalChannels.isEmpty())
/*  210:     */      {
/*  211: 211 */        channel = (Channel)this.normalChannels.remove(0);
/*  212: 212 */        channel.close();
/*  213: 213 */        channel.cleanup();
/*  214: 214 */        channel = null;
/*  215:     */      }
/*  216: 216 */      this.normalChannels.clear();
/*  217: 217 */      this.normalChannels = null;
/*  218:     */    }
/*  219:     */    
/*  220: 220 */    Set<String> keys = this.sourceMap.keySet();
/*  221: 221 */    Iterator<String> iter = keys.iterator();
/*  222:     */    
/*  226: 226 */    while (iter.hasNext())
/*  227:     */    {
/*  228: 228 */      String sourcename = (String)iter.next();
/*  229: 229 */      Source source = (Source)this.sourceMap.get(sourcename);
/*  230: 230 */      if (source != null)
/*  231: 231 */        source.cleanup();
/*  232:     */    }
/*  233: 233 */    this.sourceMap.clear();
/*  234: 234 */    this.sourceMap = null;
/*  235:     */    
/*  236: 236 */    this.listener = null;
/*  237: 237 */    this.streamThread = null;
/*  238:     */  }
/*  239:     */  
/*  242:     */  public void init()
/*  243:     */    throws SoundSystemException
/*  244:     */  {
/*  245: 245 */    Channel channel = null;
/*  246:     */    
/*  248: 248 */    for (int x = 0; x < SoundSystemConfig.getNumberStreamingChannels(); x++)
/*  249:     */    {
/*  250: 250 */      channel = createChannel(1);
/*  251: 251 */      if (channel == null)
/*  252:     */        break;
/*  253: 253 */      this.streamingChannels.add(channel);
/*  254:     */    }
/*  255:     */    
/*  256: 256 */    for (int x = 0; x < SoundSystemConfig.getNumberNormalChannels(); x++)
/*  257:     */    {
/*  258: 258 */      channel = createChannel(0);
/*  259: 259 */      if (channel == null)
/*  260:     */        break;
/*  261: 261 */      this.normalChannels.add(channel);
/*  262:     */    }
/*  263:     */  }
/*  264:     */  
/*  269:     */  public static boolean libraryCompatible()
/*  270:     */  {
/*  271: 271 */    return true;
/*  272:     */  }
/*  273:     */  
/*  281:     */  protected Channel createChannel(int type)
/*  282:     */  {
/*  283: 283 */    return new Channel(type);
/*  284:     */  }
/*  285:     */  
/*  291:     */  public boolean loadSound(FilenameURL filenameURL)
/*  292:     */  {
/*  293: 293 */    return true;
/*  294:     */  }
/*  295:     */  
/*  304:     */  public boolean loadSound(SoundBuffer buffer, String identifier)
/*  305:     */  {
/*  306: 306 */    return true;
/*  307:     */  }
/*  308:     */  
/*  313:     */  public LinkedList<String> getAllLoadedFilenames()
/*  314:     */  {
/*  315: 315 */    LinkedList<String> filenames = new LinkedList();
/*  316: 316 */    Set<String> keys = this.bufferMap.keySet();
/*  317: 317 */    Iterator<String> iter = keys.iterator();
/*  318:     */    
/*  320: 320 */    while (iter.hasNext())
/*  321:     */    {
/*  322: 322 */      filenames.add(iter.next());
/*  323:     */    }
/*  324:     */    
/*  325: 325 */    return filenames;
/*  326:     */  }
/*  327:     */  
/*  332:     */  public LinkedList<String> getAllSourcenames()
/*  333:     */  {
/*  334: 334 */    LinkedList<String> sourcenames = new LinkedList();
/*  335: 335 */    Set<String> keys = this.sourceMap.keySet();
/*  336: 336 */    Iterator<String> iter = keys.iterator();
/*  337:     */    
/*  338: 338 */    if (this.midiChannel != null) {
/*  339: 339 */      sourcenames.add(this.midiChannel.getSourcename());
/*  340:     */    }
/*  341:     */    
/*  342: 342 */    while (iter.hasNext())
/*  343:     */    {
/*  344: 344 */      sourcenames.add(iter.next());
/*  345:     */    }
/*  346:     */    
/*  347: 347 */    return sourcenames;
/*  348:     */  }
/*  349:     */  
/*  357:     */  public void unloadSound(String filename)
/*  358:     */  {
/*  359: 359 */    this.bufferMap.remove(filename);
/*  360:     */  }
/*  361:     */  
/*  374:     */  public void rawDataStream(AudioFormat audioFormat, boolean priority, String sourcename, float posX, float posY, float posZ, int attModel, float distOrRoll)
/*  375:     */  {
/*  376: 376 */    this.sourceMap.put(sourcename, new Source(audioFormat, priority, sourcename, posX, posY, posZ, attModel, distOrRoll));
/*  377:     */  }
/*  378:     */  
/*  397:     */  public void newSource(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float posX, float posY, float posZ, int attModel, float distOrRoll)
/*  398:     */  {
/*  399: 399 */    this.sourceMap.put(sourcename, new Source(priority, toStream, toLoop, sourcename, filenameURL, null, posX, posY, posZ, attModel, distOrRoll, false));
/*  400:     */  }
/*  401:     */  
/*  422:     */  public void quickPlay(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float posX, float posY, float posZ, int attModel, float distOrRoll, boolean tmp)
/*  423:     */  {
/*  424: 424 */    this.sourceMap.put(sourcename, new Source(priority, toStream, toLoop, sourcename, filenameURL, null, posX, posY, posZ, attModel, distOrRoll, tmp));
/*  425:     */  }
/*  426:     */  
/*  437:     */  public void setTemporary(String sourcename, boolean temporary)
/*  438:     */  {
/*  439: 439 */    Source mySource = (Source)this.sourceMap.get(sourcename);
/*  440: 440 */    if (mySource != null) {
/*  441: 441 */      mySource.setTemporary(temporary);
/*  442:     */    }
/*  443:     */  }
/*  444:     */  
/*  451:     */  public void setPosition(String sourcename, float x, float y, float z)
/*  452:     */  {
/*  453: 453 */    Source mySource = (Source)this.sourceMap.get(sourcename);
/*  454: 454 */    if (mySource != null) {
/*  455: 455 */      mySource.setPosition(x, y, z);
/*  456:     */    }
/*  457:     */  }
/*  458:     */  
/*  464:     */  public void setPriority(String sourcename, boolean pri)
/*  465:     */  {
/*  466: 466 */    Source mySource = (Source)this.sourceMap.get(sourcename);
/*  467: 467 */    if (mySource != null) {
/*  468: 468 */      mySource.setPriority(pri);
/*  469:     */    }
/*  470:     */  }
/*  471:     */  
/*  477:     */  public void setLooping(String sourcename, boolean lp)
/*  478:     */  {
/*  479: 479 */    Source mySource = (Source)this.sourceMap.get(sourcename);
/*  480: 480 */    if (mySource != null) {
/*  481: 481 */      mySource.setLooping(lp);
/*  482:     */    }
/*  483:     */  }
/*  484:     */  
/*  489:     */  public void setAttenuation(String sourcename, int model)
/*  490:     */  {
/*  491: 491 */    Source mySource = (Source)this.sourceMap.get(sourcename);
/*  492: 492 */    if (mySource != null) {
/*  493: 493 */      mySource.setAttenuation(model);
/*  494:     */    }
/*  495:     */  }
/*  496:     */  
/*  501:     */  public void setDistOrRoll(String sourcename, float dr)
/*  502:     */  {
/*  503: 503 */    Source mySource = (Source)this.sourceMap.get(sourcename);
/*  504: 504 */    if (mySource != null) {
/*  505: 505 */      mySource.setDistOrRoll(dr);
/*  506:     */    }
/*  507:     */  }
/*  508:     */  
/*  515:     */  public void setVelocity(String sourcename, float x, float y, float z)
/*  516:     */  {
/*  517: 517 */    Source mySource = (Source)this.sourceMap.get(sourcename);
/*  518: 518 */    if (mySource != null) {
/*  519: 519 */      mySource.setVelocity(x, y, z);
/*  520:     */    }
/*  521:     */  }
/*  522:     */  
/*  528:     */  public void setListenerVelocity(float x, float y, float z)
/*  529:     */  {
/*  530: 530 */    this.listener.setVelocity(x, y, z);
/*  531:     */  }
/*  532:     */  
/*  537:     */  public void dopplerChanged() {}
/*  538:     */  
/*  543:     */  public float millisecondsPlayed(String sourcename)
/*  544:     */  {
/*  545: 545 */    if ((sourcename == null) || (sourcename.equals("")))
/*  546:     */    {
/*  547: 547 */      errorMessage("Sourcename not specified in method 'millisecondsPlayed'");
/*  548:     */      
/*  549: 549 */      return -1.0F;
/*  550:     */    }
/*  551:     */    
/*  552: 552 */    if (midiSourcename(sourcename))
/*  553:     */    {
/*  554: 554 */      errorMessage("Unable to calculate milliseconds for MIDI source.");
/*  555: 555 */      return -1.0F;
/*  556:     */    }
/*  557:     */    
/*  559: 559 */    Source source = (Source)this.sourceMap.get(sourcename);
/*  560: 560 */    if (source == null)
/*  561:     */    {
/*  562: 562 */      errorMessage("Source '" + sourcename + "' not found in " + "method 'millisecondsPlayed'");
/*  563:     */    }
/*  564:     */    
/*  565: 565 */    return source.millisecondsPlayed();
/*  566:     */  }
/*  567:     */  
/*  576:     */  public int feedRawAudioData(String sourcename, byte[] buffer)
/*  577:     */  {
/*  578: 578 */    if ((sourcename == null) || (sourcename.equals("")))
/*  579:     */    {
/*  580: 580 */      errorMessage("Sourcename not specified in method 'feedRawAudioData'");
/*  581:     */      
/*  582: 582 */      return -1;
/*  583:     */    }
/*  584:     */    
/*  585: 585 */    if (midiSourcename(sourcename))
/*  586:     */    {
/*  587: 587 */      errorMessage("Raw audio data can not be fed to the MIDI channel.");
/*  588:     */      
/*  589: 589 */      return -1;
/*  590:     */    }
/*  591:     */    
/*  593: 593 */    Source source = (Source)this.sourceMap.get(sourcename);
/*  594: 594 */    if (source == null)
/*  595:     */    {
/*  596: 596 */      errorMessage("Source '" + sourcename + "' not found in " + "method 'feedRawAudioData'");
/*  597:     */    }
/*  598:     */    
/*  599: 599 */    return feedRawAudioData(source, buffer);
/*  600:     */  }
/*  601:     */  
/*  611:     */  public int feedRawAudioData(Source source, byte[] buffer)
/*  612:     */  {
/*  613: 613 */    if (source == null)
/*  614:     */    {
/*  615: 615 */      errorMessage("Source parameter null in method 'feedRawAudioData'");
/*  616:     */      
/*  617: 617 */      return -1;
/*  618:     */    }
/*  619: 619 */    if (!source.toStream)
/*  620:     */    {
/*  621: 621 */      errorMessage("Only a streaming source may be specified in method 'feedRawAudioData'");
/*  622:     */      
/*  623: 623 */      return -1;
/*  624:     */    }
/*  625: 625 */    if (!source.rawDataStream)
/*  626:     */    {
/*  627: 627 */      errorMessage("Streaming source already associated with a file or URL in method'feedRawAudioData'");
/*  628:     */      
/*  629: 629 */      return -1;
/*  630:     */    }
/*  631:     */    
/*  632: 632 */    if ((!source.playing()) || (source.channel == null)) {
/*  633:     */      Channel channel;
/*  634:     */      Channel channel;
/*  635: 635 */      if ((source.channel != null) && (source.channel.attachedSource == source))
/*  636:     */      {
/*  637: 637 */        channel = source.channel;
/*  638:     */      } else {
/*  639: 639 */        channel = getNextChannel(source);
/*  640:     */      }
/*  641: 641 */      int processed = source.feedRawAudioData(channel, buffer);
/*  642: 642 */      channel.attachedSource = source;
/*  643: 643 */      this.streamThread.watch(source);
/*  644: 644 */      this.streamThread.interrupt();
/*  645: 645 */      return processed;
/*  646:     */    }
/*  647:     */    
/*  648: 648 */    return source.feedRawAudioData(source.channel, buffer);
/*  649:     */  }
/*  650:     */  
/*  655:     */  public void play(String sourcename)
/*  656:     */  {
/*  657: 657 */    if ((sourcename == null) || (sourcename.equals("")))
/*  658:     */    {
/*  659: 659 */      errorMessage("Sourcename not specified in method 'play'");
/*  660: 660 */      return;
/*  661:     */    }
/*  662:     */    
/*  663: 663 */    if (midiSourcename(sourcename))
/*  664:     */    {
/*  665: 665 */      this.midiChannel.play();
/*  666:     */    }
/*  667:     */    else
/*  668:     */    {
/*  669: 669 */      Source source = (Source)this.sourceMap.get(sourcename);
/*  670: 670 */      if (source == null)
/*  671:     */      {
/*  672: 672 */        errorMessage("Source '" + sourcename + "' not found in " + "method 'play'");
/*  673:     */      }
/*  674:     */      
/*  675: 675 */      play(source);
/*  676:     */    }
/*  677:     */  }
/*  678:     */  
/*  683:     */  public void play(Source source)
/*  684:     */  {
/*  685: 685 */    if (source == null) {
/*  686: 686 */      return;
/*  687:     */    }
/*  688:     */    
/*  690: 690 */    if (source.rawDataStream) {
/*  691: 691 */      return;
/*  692:     */    }
/*  693: 693 */    if (!source.active()) {
/*  694: 694 */      return;
/*  695:     */    }
/*  696: 696 */    if (!source.playing())
/*  697:     */    {
/*  698: 698 */      Channel channel = getNextChannel(source);
/*  699:     */      
/*  700: 700 */      if ((source != null) && (channel != null))
/*  701:     */      {
/*  702: 702 */        if ((source.channel != null) && (source.channel.attachedSource != source))
/*  703:     */        {
/*  704: 704 */          source.channel = null; }
/*  705: 705 */        channel.attachedSource = source;
/*  706: 706 */        source.play(channel);
/*  707: 707 */        if (source.toStream)
/*  708:     */        {
/*  709: 709 */          this.streamThread.watch(source);
/*  710: 710 */          this.streamThread.interrupt();
/*  711:     */        }
/*  712:     */      }
/*  713:     */    }
/*  714:     */  }
/*  715:     */  
/*  720:     */  public void stop(String sourcename)
/*  721:     */  {
/*  722: 722 */    if ((sourcename == null) || (sourcename.equals("")))
/*  723:     */    {
/*  724: 724 */      errorMessage("Sourcename not specified in method 'stop'");
/*  725: 725 */      return;
/*  726:     */    }
/*  727: 727 */    if (midiSourcename(sourcename))
/*  728:     */    {
/*  729: 729 */      this.midiChannel.stop();
/*  730:     */    }
/*  731:     */    else
/*  732:     */    {
/*  733: 733 */      Source mySource = (Source)this.sourceMap.get(sourcename);
/*  734: 734 */      if (mySource != null) {
/*  735: 735 */        mySource.stop();
/*  736:     */      }
/*  737:     */    }
/*  738:     */  }
/*  739:     */  
/*  743:     */  public void pause(String sourcename)
/*  744:     */  {
/*  745: 745 */    if ((sourcename == null) || (sourcename.equals("")))
/*  746:     */    {
/*  747: 747 */      errorMessage("Sourcename not specified in method 'stop'");
/*  748: 748 */      return;
/*  749:     */    }
/*  750: 750 */    if (midiSourcename(sourcename))
/*  751:     */    {
/*  752: 752 */      this.midiChannel.pause();
/*  753:     */    }
/*  754:     */    else
/*  755:     */    {
/*  756: 756 */      Source mySource = (Source)this.sourceMap.get(sourcename);
/*  757: 757 */      if (mySource != null) {
/*  758: 758 */        mySource.pause();
/*  759:     */      }
/*  760:     */    }
/*  761:     */  }
/*  762:     */  
/*  766:     */  public void rewind(String sourcename)
/*  767:     */  {
/*  768: 768 */    if (midiSourcename(sourcename))
/*  769:     */    {
/*  770: 770 */      this.midiChannel.rewind();
/*  771:     */    }
/*  772:     */    else
/*  773:     */    {
/*  774: 774 */      Source mySource = (Source)this.sourceMap.get(sourcename);
/*  775: 775 */      if (mySource != null) {
/*  776: 776 */        mySource.rewind();
/*  777:     */      }
/*  778:     */    }
/*  779:     */  }
/*  780:     */  
/*  784:     */  public void flush(String sourcename)
/*  785:     */  {
/*  786: 786 */    if (midiSourcename(sourcename)) {
/*  787: 787 */      errorMessage("You can not flush the MIDI channel");
/*  788:     */    }
/*  789:     */    else {
/*  790: 790 */      Source mySource = (Source)this.sourceMap.get(sourcename);
/*  791: 791 */      if (mySource != null) {
/*  792: 792 */        mySource.flush();
/*  793:     */      }
/*  794:     */    }
/*  795:     */  }
/*  796:     */  
/*  801:     */  public void cull(String sourcename)
/*  802:     */  {
/*  803: 803 */    Source mySource = (Source)this.sourceMap.get(sourcename);
/*  804: 804 */    if (mySource != null) {
/*  805: 805 */      mySource.cull();
/*  806:     */    }
/*  807:     */  }
/*  808:     */  
/*  812:     */  public void activate(String sourcename)
/*  813:     */  {
/*  814: 814 */    Source mySource = (Source)this.sourceMap.get(sourcename);
/*  815: 815 */    if (mySource != null)
/*  816:     */    {
/*  817: 817 */      mySource.activate();
/*  818: 818 */      if (mySource.toPlay) {
/*  819: 819 */        play(mySource);
/*  820:     */      }
/*  821:     */    }
/*  822:     */  }
/*  823:     */  
/*  827:     */  public void setMasterVolume(float value)
/*  828:     */  {
/*  829: 829 */    SoundSystemConfig.setMasterGain(value);
/*  830: 830 */    if (this.midiChannel != null) {
/*  831: 831 */      this.midiChannel.resetGain();
/*  832:     */    }
/*  833:     */  }
/*  834:     */  
/*  839:     */  public void setVolume(String sourcename, float value)
/*  840:     */  {
/*  841: 841 */    if (midiSourcename(sourcename))
/*  842:     */    {
/*  843: 843 */      this.midiChannel.setVolume(value);
/*  844:     */    }
/*  845:     */    else
/*  846:     */    {
/*  847: 847 */      Source mySource = (Source)this.sourceMap.get(sourcename);
/*  848: 848 */      if (mySource != null)
/*  849:     */      {
/*  850: 850 */        float newVolume = value;
/*  851: 851 */        if (newVolume < 0.0F) {
/*  852: 852 */          newVolume = 0.0F;
/*  853: 853 */        } else if (newVolume > 1.0F) {
/*  854: 854 */          newVolume = 1.0F;
/*  855:     */        }
/*  856: 856 */        mySource.sourceVolume = newVolume;
/*  857: 857 */        mySource.positionChanged();
/*  858:     */      }
/*  859:     */    }
/*  860:     */  }
/*  861:     */  
/*  868:     */  public float getVolume(String sourcename)
/*  869:     */  {
/*  870: 870 */    if (midiSourcename(sourcename))
/*  871:     */    {
/*  872: 872 */      return this.midiChannel.getVolume();
/*  873:     */    }
/*  874:     */    
/*  876: 876 */    Source mySource = (Source)this.sourceMap.get(sourcename);
/*  877: 877 */    if (mySource != null) {
/*  878: 878 */      return mySource.sourceVolume;
/*  879:     */    }
/*  880: 880 */    return 0.0F;
/*  881:     */  }
/*  882:     */  
/*  889:     */  public void setPitch(String sourcename, float value)
/*  890:     */  {
/*  891: 891 */    if (!midiSourcename(sourcename))
/*  892:     */    {
/*  893: 893 */      Source mySource = (Source)this.sourceMap.get(sourcename);
/*  894: 894 */      if (mySource != null)
/*  895:     */      {
/*  896: 896 */        float newPitch = value;
/*  897: 897 */        if (newPitch < 0.5F) {
/*  898: 898 */          newPitch = 0.5F;
/*  899: 899 */        } else if (newPitch > 2.0F) {
/*  900: 900 */          newPitch = 2.0F;
/*  901:     */        }
/*  902: 902 */        mySource.setPitch(newPitch);
/*  903: 903 */        mySource.positionChanged();
/*  904:     */      }
/*  905:     */    }
/*  906:     */  }
/*  907:     */  
/*  913:     */  public float getPitch(String sourcename)
/*  914:     */  {
/*  915: 915 */    if (!midiSourcename(sourcename))
/*  916:     */    {
/*  917: 917 */      Source mySource = (Source)this.sourceMap.get(sourcename);
/*  918: 918 */      if (mySource != null)
/*  919: 919 */        return mySource.getPitch();
/*  920:     */    }
/*  921: 921 */    return 1.0F;
/*  922:     */  }
/*  923:     */  
/*  930:     */  public void moveListener(float x, float y, float z)
/*  931:     */  {
/*  932: 932 */    setListenerPosition(this.listener.position.x + x, this.listener.position.y + y, this.listener.position.z + z);
/*  933:     */  }
/*  934:     */  
/*  943:     */  public void setListenerPosition(float x, float y, float z)
/*  944:     */  {
/*  945: 945 */    this.listener.setPosition(x, y, z);
/*  946:     */    
/*  947: 947 */    Set<String> keys = this.sourceMap.keySet();
/*  948: 948 */    Iterator<String> iter = keys.iterator();
/*  949:     */    
/*  953: 953 */    while (iter.hasNext())
/*  954:     */    {
/*  955: 955 */      String sourcename = (String)iter.next();
/*  956: 956 */      Source source = (Source)this.sourceMap.get(sourcename);
/*  957: 957 */      if (source != null) {
/*  958: 958 */        source.positionChanged();
/*  959:     */      }
/*  960:     */    }
/*  961:     */  }
/*  962:     */  
/*  967:     */  public void turnListener(float angle)
/*  968:     */  {
/*  969: 969 */    setListenerAngle(this.listener.angle + angle);
/*  970:     */    
/*  971: 971 */    Set<String> keys = this.sourceMap.keySet();
/*  972: 972 */    Iterator<String> iter = keys.iterator();
/*  973:     */    
/*  977: 977 */    while (iter.hasNext())
/*  978:     */    {
/*  979: 979 */      String sourcename = (String)iter.next();
/*  980: 980 */      Source source = (Source)this.sourceMap.get(sourcename);
/*  981: 981 */      if (source != null) {
/*  982: 982 */        source.positionChanged();
/*  983:     */      }
/*  984:     */    }
/*  985:     */  }
/*  986:     */  
/*  991:     */  public void setListenerAngle(float angle)
/*  992:     */  {
/*  993: 993 */    this.listener.setAngle(angle);
/*  994:     */    
/*  995: 995 */    Set<String> keys = this.sourceMap.keySet();
/*  996: 996 */    Iterator<String> iter = keys.iterator();
/*  997:     */    
/* 1001:1001 */    while (iter.hasNext())
/* 1002:     */    {
/* 1003:1003 */      String sourcename = (String)iter.next();
/* 1004:1004 */      Source source = (Source)this.sourceMap.get(sourcename);
/* 1005:1005 */      if (source != null) {
/* 1006:1006 */        source.positionChanged();
/* 1007:     */      }
/* 1008:     */    }
/* 1009:     */  }
/* 1010:     */  
/* 1020:     */  public void setListenerOrientation(float lookX, float lookY, float lookZ, float upX, float upY, float upZ)
/* 1021:     */  {
/* 1022:1022 */    this.listener.setOrientation(lookX, lookY, lookZ, upX, upY, upZ);
/* 1023:     */    
/* 1024:1024 */    Set<String> keys = this.sourceMap.keySet();
/* 1025:1025 */    Iterator<String> iter = keys.iterator();
/* 1026:     */    
/* 1030:1030 */    while (iter.hasNext())
/* 1031:     */    {
/* 1032:1032 */      String sourcename = (String)iter.next();
/* 1033:1033 */      Source source = (Source)this.sourceMap.get(sourcename);
/* 1034:1034 */      if (source != null) {
/* 1035:1035 */        source.positionChanged();
/* 1036:     */      }
/* 1037:     */    }
/* 1038:     */  }
/* 1039:     */  
/* 1044:     */  public void setListenerData(ListenerData l)
/* 1045:     */  {
/* 1046:1046 */    this.listener.setData(l);
/* 1047:     */  }
/* 1048:     */  
/* 1053:     */  public void copySources(HashMap<String, Source> srcMap)
/* 1054:     */  {
/* 1055:1055 */    if (srcMap == null)
/* 1056:1056 */      return;
/* 1057:1057 */    Set<String> keys = srcMap.keySet();
/* 1058:1058 */    Iterator<String> iter = keys.iterator();
/* 1059:     */    
/* 1063:1063 */    this.sourceMap.clear();
/* 1064:     */    
/* 1066:1066 */    while (iter.hasNext())
/* 1067:     */    {
/* 1068:1068 */      String sourcename = (String)iter.next();
/* 1069:1069 */      Source srcData = (Source)srcMap.get(sourcename);
/* 1070:1070 */      if (srcData != null)
/* 1071:     */      {
/* 1072:1072 */        loadSound(srcData.filenameURL);
/* 1073:1073 */        this.sourceMap.put(sourcename, new Source(srcData, null));
/* 1074:     */      }
/* 1075:     */    }
/* 1076:     */  }
/* 1077:     */  
/* 1082:     */  public void removeSource(String sourcename)
/* 1083:     */  {
/* 1084:1084 */    Source mySource = (Source)this.sourceMap.get(sourcename);
/* 1085:1085 */    if (mySource != null)
/* 1086:1086 */      mySource.cleanup();
/* 1087:1087 */    this.sourceMap.remove(sourcename);
/* 1088:     */  }
/* 1089:     */  
/* 1093:     */  public void removeTemporarySources()
/* 1094:     */  {
/* 1095:1095 */    Set<String> keys = this.sourceMap.keySet();
/* 1096:1096 */    Iterator<String> iter = keys.iterator();
/* 1097:     */    
/* 1101:1101 */    while (iter.hasNext())
/* 1102:     */    {
/* 1103:1103 */      String sourcename = (String)iter.next();
/* 1104:1104 */      Source srcData = (Source)this.sourceMap.get(sourcename);
/* 1105:1105 */      if ((srcData != null) && (srcData.temporary) && (!srcData.playing()))
/* 1106:     */      {
/* 1108:1108 */        srcData.cleanup();
/* 1109:1109 */        iter.remove();
/* 1110:     */      }
/* 1111:     */    }
/* 1112:     */  }
/* 1113:     */  
/* 1128:     */  private Channel getNextChannel(Source source)
/* 1129:     */  {
/* 1130:1130 */    if (source == null) {
/* 1131:1131 */      return null;
/* 1132:     */    }
/* 1133:1133 */    String sourcename = source.sourcename;
/* 1134:1134 */    if (sourcename == null) {
/* 1135:1135 */      return null;
/* 1136:     */    }
/* 1137:     */    
/* 1138:     */    String[] sourceNames;
/* 1139:     */    
/* 1140:     */    int nextChannel;
/* 1141:     */    
/* 1142:     */    List<Channel> channelList;
/* 1143:     */    String[] sourceNames;
/* 1144:1144 */    if (source.toStream)
/* 1145:     */    {
/* 1146:1146 */      int nextChannel = this.nextStreamingChannel;
/* 1147:1147 */      List<Channel> channelList = this.streamingChannels;
/* 1148:1148 */      sourceNames = this.streamingChannelSourceNames;
/* 1149:     */    }
/* 1150:     */    else
/* 1151:     */    {
/* 1152:1152 */      nextChannel = this.nextNormalChannel;
/* 1153:1153 */      channelList = this.normalChannels;
/* 1154:1154 */      sourceNames = this.normalChannelSourceNames;
/* 1155:     */    }
/* 1156:     */    
/* 1157:1157 */    int channels = channelList.size();
/* 1158:     */    
/* 1160:1160 */    for (int x = 0; x < channels; x++)
/* 1161:     */    {
/* 1162:1162 */      if (sourcename.equals(sourceNames[x])) {
/* 1163:1163 */        return (Channel)channelList.get(x);
/* 1164:     */      }
/* 1165:     */    }
/* 1166:1166 */    int n = nextChannel;
/* 1167:     */    
/* 1169:1169 */    for (x = 0; x < channels; x++)
/* 1170:     */    {
/* 1171:1171 */      String name = sourceNames[n];
/* 1172:1172 */      Source src; Source src; if (name == null) {
/* 1173:1173 */        src = null;
/* 1174:     */      } else {
/* 1175:1175 */        src = (Source)this.sourceMap.get(name);
/* 1176:     */      }
/* 1177:1177 */      if ((src == null) || (!src.playing()))
/* 1178:     */      {
/* 1179:1179 */        if (source.toStream)
/* 1180:     */        {
/* 1181:1181 */          this.nextStreamingChannel = (n + 1);
/* 1182:1182 */          if (this.nextStreamingChannel >= channels) {
/* 1183:1183 */            this.nextStreamingChannel = 0;
/* 1184:     */          }
/* 1185:     */        }
/* 1186:     */        else {
/* 1187:1187 */          this.nextNormalChannel = (n + 1);
/* 1188:1188 */          if (this.nextNormalChannel >= channels)
/* 1189:1189 */            this.nextNormalChannel = 0;
/* 1190:     */        }
/* 1191:1191 */        sourceNames[n] = sourcename;
/* 1192:1192 */        return (Channel)channelList.get(n);
/* 1193:     */      }
/* 1194:1194 */      n++;
/* 1195:1195 */      if (n >= channels) {
/* 1196:1196 */        n = 0;
/* 1197:     */      }
/* 1198:     */    }
/* 1199:1199 */    n = nextChannel;
/* 1200:     */    
/* 1201:1201 */    for (x = 0; x < channels; x++)
/* 1202:     */    {
/* 1203:1203 */      String name = sourceNames[n];
/* 1204:1204 */      Source src; Source src; if (name == null) {
/* 1205:1205 */        src = null;
/* 1206:     */      } else {
/* 1207:1207 */        src = (Source)this.sourceMap.get(name);
/* 1208:     */      }
/* 1209:1209 */      if ((src == null) || (!src.playing()) || (!src.priority))
/* 1210:     */      {
/* 1211:1211 */        if (source.toStream)
/* 1212:     */        {
/* 1213:1213 */          this.nextStreamingChannel = (n + 1);
/* 1214:1214 */          if (this.nextStreamingChannel >= channels) {
/* 1215:1215 */            this.nextStreamingChannel = 0;
/* 1216:     */          }
/* 1217:     */        }
/* 1218:     */        else {
/* 1219:1219 */          this.nextNormalChannel = (n + 1);
/* 1220:1220 */          if (this.nextNormalChannel >= channels)
/* 1221:1221 */            this.nextNormalChannel = 0;
/* 1222:     */        }
/* 1223:1223 */        sourceNames[n] = sourcename;
/* 1224:1224 */        return (Channel)channelList.get(n);
/* 1225:     */      }
/* 1226:1226 */      n++;
/* 1227:1227 */      if (n >= channels) {
/* 1228:1228 */        n = 0;
/* 1229:     */      }
/* 1230:     */    }
/* 1231:1231 */    return null;
/* 1232:     */  }
/* 1233:     */  
/* 1239:     */  public void replaySources()
/* 1240:     */  {
/* 1241:1241 */    Set<String> keys = this.sourceMap.keySet();
/* 1242:1242 */    Iterator<String> iter = keys.iterator();
/* 1243:     */    
/* 1247:1247 */    while (iter.hasNext())
/* 1248:     */    {
/* 1249:1249 */      String sourcename = (String)iter.next();
/* 1250:1250 */      Source source = (Source)this.sourceMap.get(sourcename);
/* 1251:1251 */      if ((source != null) && 
/* 1252:     */      
/* 1253:1253 */        (source.toPlay) && (!source.playing()))
/* 1254:     */      {
/* 1255:1255 */        play(sourcename);
/* 1256:1256 */        source.toPlay = false;
/* 1257:     */      }
/* 1258:     */    }
/* 1259:     */  }
/* 1260:     */  
/* 1269:     */  public void queueSound(String sourcename, FilenameURL filenameURL)
/* 1270:     */  {
/* 1271:1271 */    if (midiSourcename(sourcename))
/* 1272:     */    {
/* 1273:1273 */      this.midiChannel.queueSound(filenameURL);
/* 1274:     */    }
/* 1275:     */    else
/* 1276:     */    {
/* 1277:1277 */      Source mySource = (Source)this.sourceMap.get(sourcename);
/* 1278:1278 */      if (mySource != null) {
/* 1279:1279 */        mySource.queueSound(filenameURL);
/* 1280:     */      }
/* 1281:     */    }
/* 1282:     */  }
/* 1283:     */  
/* 1290:     */  public void dequeueSound(String sourcename, String filename)
/* 1291:     */  {
/* 1292:1292 */    if (midiSourcename(sourcename))
/* 1293:     */    {
/* 1294:1294 */      this.midiChannel.dequeueSound(filename);
/* 1295:     */    }
/* 1296:     */    else
/* 1297:     */    {
/* 1298:1298 */      Source mySource = (Source)this.sourceMap.get(sourcename);
/* 1299:1299 */      if (mySource != null) {
/* 1300:1300 */        mySource.dequeueSound(filename);
/* 1301:     */      }
/* 1302:     */    }
/* 1303:     */  }
/* 1304:     */  
/* 1318:     */  public void fadeOut(String sourcename, FilenameURL filenameURL, long milis)
/* 1319:     */  {
/* 1320:1320 */    if (midiSourcename(sourcename))
/* 1321:     */    {
/* 1322:1322 */      this.midiChannel.fadeOut(filenameURL, milis);
/* 1323:     */    }
/* 1324:     */    else
/* 1325:     */    {
/* 1326:1326 */      Source mySource = (Source)this.sourceMap.get(sourcename);
/* 1327:1327 */      if (mySource != null) {
/* 1328:1328 */        mySource.fadeOut(filenameURL, milis);
/* 1329:     */      }
/* 1330:     */    }
/* 1331:     */  }
/* 1332:     */  
/* 1347:     */  public void fadeOutIn(String sourcename, FilenameURL filenameURL, long milisOut, long milisIn)
/* 1348:     */  {
/* 1349:1349 */    if (midiSourcename(sourcename))
/* 1350:     */    {
/* 1351:1351 */      this.midiChannel.fadeOutIn(filenameURL, milisOut, milisIn);
/* 1352:     */    }
/* 1353:     */    else
/* 1354:     */    {
/* 1355:1355 */      Source mySource = (Source)this.sourceMap.get(sourcename);
/* 1356:1356 */      if (mySource != null) {
/* 1357:1357 */        mySource.fadeOutIn(filenameURL, milisOut, milisIn);
/* 1358:     */      }
/* 1359:     */    }
/* 1360:     */  }
/* 1361:     */  
/* 1372:     */  public void checkFadeVolumes()
/* 1373:     */  {
/* 1374:1374 */    if (this.midiChannel != null) {
/* 1375:1375 */      this.midiChannel.resetGain();
/* 1376:     */    }
/* 1377:     */    
/* 1378:1378 */    for (int x = 0; x < this.streamingChannels.size(); x++)
/* 1379:     */    {
/* 1380:1380 */      Channel c = (Channel)this.streamingChannels.get(x);
/* 1381:1381 */      if (c != null)
/* 1382:     */      {
/* 1383:1383 */        Source s = c.attachedSource;
/* 1384:1384 */        if (s != null)
/* 1385:1385 */          s.checkFadeOut();
/* 1386:     */      }
/* 1387:     */    }
/* 1388:1388 */    Channel c = null;
/* 1389:1389 */    Source s = null;
/* 1390:     */  }
/* 1391:     */  
/* 1399:     */  public void loadMidi(boolean toLoop, String sourcename, FilenameURL filenameURL)
/* 1400:     */  {
/* 1401:1401 */    if (filenameURL == null)
/* 1402:     */    {
/* 1403:1403 */      errorMessage("Filename/URL not specified in method 'loadMidi'.");
/* 1404:1404 */      return;
/* 1405:     */    }
/* 1406:     */    
/* 1407:1407 */    if (!filenameURL.getFilename().matches(SoundSystemConfig.EXTENSION_MIDI))
/* 1408:     */    {
/* 1410:1410 */      errorMessage("Filename/identifier doesn't end in '.mid' or'.midi' in method loadMidi.");
/* 1411:     */      
/* 1412:1412 */      return;
/* 1413:     */    }
/* 1414:     */    
/* 1415:1415 */    if (this.midiChannel == null)
/* 1416:     */    {
/* 1417:1417 */      this.midiChannel = new MidiChannel(toLoop, sourcename, filenameURL);
/* 1418:     */    }
/* 1419:     */    else
/* 1420:     */    {
/* 1421:1421 */      this.midiChannel.switchSource(toLoop, sourcename, filenameURL);
/* 1422:     */    }
/* 1423:     */  }
/* 1424:     */  
/* 1428:     */  public void unloadMidi()
/* 1429:     */  {
/* 1430:1430 */    if (this.midiChannel != null)
/* 1431:1431 */      this.midiChannel.cleanup();
/* 1432:1432 */    this.midiChannel = null;
/* 1433:     */  }
/* 1434:     */  
/* 1440:     */  public boolean midiSourcename(String sourcename)
/* 1441:     */  {
/* 1442:1442 */    if ((this.midiChannel == null) || (sourcename == null)) {
/* 1443:1443 */      return false;
/* 1444:     */    }
/* 1445:1445 */    if ((this.midiChannel.getSourcename() == null) || (sourcename.equals(""))) {
/* 1446:1446 */      return false;
/* 1447:     */    }
/* 1448:1448 */    if (sourcename.equals(this.midiChannel.getSourcename())) {
/* 1449:1449 */      return true;
/* 1450:     */    }
/* 1451:1451 */    return false;
/* 1452:     */  }
/* 1453:     */  
/* 1460:     */  public Source getSource(String sourcename)
/* 1461:     */  {
/* 1462:1462 */    return (Source)this.sourceMap.get(sourcename);
/* 1463:     */  }
/* 1464:     */  
/* 1470:     */  public MidiChannel getMidiChannel()
/* 1471:     */  {
/* 1472:1472 */    return this.midiChannel;
/* 1473:     */  }
/* 1474:     */  
/* 1480:     */  public void setMidiChannel(MidiChannel c)
/* 1481:     */  {
/* 1482:1482 */    if ((this.midiChannel != null) && (this.midiChannel != c)) {
/* 1483:1483 */      this.midiChannel.cleanup();
/* 1484:     */    }
/* 1485:1485 */    this.midiChannel = c;
/* 1486:     */  }
/* 1487:     */  
/* 1491:     */  public void listenerMoved()
/* 1492:     */  {
/* 1493:1493 */    Set<String> keys = this.sourceMap.keySet();
/* 1494:1494 */    Iterator<String> iter = keys.iterator();
/* 1495:     */    
/* 1499:1499 */    while (iter.hasNext())
/* 1500:     */    {
/* 1501:1501 */      String sourcename = (String)iter.next();
/* 1502:1502 */      Source srcData = (Source)this.sourceMap.get(sourcename);
/* 1503:1503 */      if (srcData != null)
/* 1504:     */      {
/* 1505:1505 */        srcData.listenerMoved();
/* 1506:     */      }
/* 1507:     */    }
/* 1508:     */  }
/* 1509:     */  
/* 1514:     */  public HashMap<String, Source> getSources()
/* 1515:     */  {
/* 1516:1516 */    return this.sourceMap;
/* 1517:     */  }
/* 1518:     */  
/* 1523:     */  public ListenerData getListenerData()
/* 1524:     */  {
/* 1525:1525 */    return this.listener;
/* 1526:     */  }
/* 1527:     */  
/* 1533:     */  public boolean reverseByteOrder()
/* 1534:     */  {
/* 1535:1535 */    return this.reverseByteOrder;
/* 1536:     */  }
/* 1537:     */  
/* 1541:     */  public static String getTitle()
/* 1542:     */  {
/* 1543:1543 */    return "No Sound";
/* 1544:     */  }
/* 1545:     */  
/* 1550:     */  public static String getDescription()
/* 1551:     */  {
/* 1552:1552 */    return "Silent Mode";
/* 1553:     */  }
/* 1554:     */  
/* 1559:     */  public String getClassName()
/* 1560:     */  {
/* 1561:1561 */    return "Library";
/* 1562:     */  }
/* 1563:     */  
/* 1568:     */  protected void message(String message)
/* 1569:     */  {
/* 1570:1570 */    this.logger.message(message, 0);
/* 1571:     */  }
/* 1572:     */  
/* 1577:     */  protected void importantMessage(String message)
/* 1578:     */  {
/* 1579:1579 */    this.logger.importantMessage(message, 0);
/* 1580:     */  }
/* 1581:     */  
/* 1588:     */  protected boolean errorCheck(boolean error, String message)
/* 1589:     */  {
/* 1590:1590 */    return this.logger.errorCheck(error, getClassName(), message, 0);
/* 1591:     */  }
/* 1592:     */  
/* 1597:     */  protected void errorMessage(String message)
/* 1598:     */  {
/* 1599:1599 */    this.logger.errorMessage(getClassName(), message, 0);
/* 1600:     */  }
/* 1601:     */  
/* 1606:     */  protected void printStackTrace(Exception e)
/* 1607:     */  {
/* 1608:1608 */    this.logger.printStackTrace(e, 1);
/* 1609:     */  }
/* 1610:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.Library
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */