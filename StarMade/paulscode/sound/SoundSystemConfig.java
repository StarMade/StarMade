/*    1:     */package paulscode.sound;
/*    2:     */
/*    3:     */import java.lang.reflect.InvocationTargetException;
/*    4:     */import java.lang.reflect.Method;
/*    5:     */import java.util.LinkedList;
/*    6:     */import java.util.ListIterator;
/*    7:     */import java.util.Locale;
/*    8:     */
/*   53:     */public class SoundSystemConfig
/*   54:     */{
/*   55:  55 */  public static final Object THREAD_SYNC = new Object();
/*   56:     */  
/*   62:     */  public static final int TYPE_NORMAL = 0;
/*   63:     */  
/*   69:     */  public static final int TYPE_STREAMING = 1;
/*   70:     */  
/*   76:     */  public static final int ATTENUATION_NONE = 0;
/*   77:     */  
/*   82:     */  public static final int ATTENUATION_ROLLOFF = 1;
/*   83:     */  
/*   88:     */  public static final int ATTENUATION_LINEAR = 2;
/*   89:     */  
/*   94:  94 */  public static String EXTENSION_MIDI = ".*[mM][iI][dD][iI]?$";
/*   95:     */  
/*   99:  99 */  public static String PREFIX_URL = "^[hH][tT][tT][pP]://.*";
/*  100:     */  
/*  112: 112 */  private static SoundSystemLogger logger = null;
/*  113:     */  
/*  117:     */  private static LinkedList<Class> libraries;
/*  118:     */  
/*  122: 122 */  private static LinkedList<Codec> codecs = null;
/*  123:     */  
/*  127: 127 */  private static LinkedList<IStreamListener> streamListeners = null;
/*  128:     */  
/*  131: 131 */  private static final Object streamListenersLock = new Object();
/*  132:     */  
/*  138: 138 */  private static int numberNormalChannels = 28;
/*  139:     */  
/*  144: 144 */  private static int numberStreamingChannels = 4;
/*  145:     */  
/*  148: 148 */  private static float masterGain = 1.0F;
/*  149:     */  
/*  153: 153 */  private static int defaultAttenuationModel = 1;
/*  154:     */  
/*  157: 157 */  private static float defaultRolloffFactor = 0.03F;
/*  158:     */  
/*  161: 161 */  private static float dopplerFactor = 0.0F;
/*  162:     */  
/*  165: 165 */  private static float dopplerVelocity = 1.0F;
/*  166:     */  
/*  169: 169 */  private static float defaultFadeDistance = 1000.0F;
/*  170:     */  
/*  173: 173 */  private static String soundFilesPackage = "Sounds/";
/*  174:     */  
/*  178: 178 */  private static int streamingBufferSize = 131072;
/*  179:     */  
/*  183: 183 */  private static int numberStreamingBuffers = 3;
/*  184:     */  
/*  190: 190 */  private static boolean streamQueueFormatsMatch = false;
/*  191:     */  
/*  196: 196 */  private static int maxFileSize = 268435456;
/*  197:     */  
/*  201: 201 */  private static int fileChunkSize = 1048576;
/*  202:     */  
/*  207: 207 */  private static boolean midiCodec = false;
/*  208:     */  
/*  215: 215 */  private static String overrideMIDISynthesizer = "";
/*  216:     */  
/*  228:     */  public static void addLibrary(Class libraryClass)
/*  229:     */    throws SoundSystemException
/*  230:     */  {
/*  231: 231 */    if (libraryClass == null) {
/*  232: 232 */      throw new SoundSystemException("Parameter null in method 'addLibrary'", 2);
/*  233:     */    }
/*  234:     */    
/*  235: 235 */    if (!Library.class.isAssignableFrom(libraryClass)) {
/*  236: 236 */      throw new SoundSystemException("The specified class does not extend class 'Library' in method 'addLibrary'");
/*  237:     */    }
/*  238:     */    
/*  239: 239 */    if (libraries == null) {
/*  240: 240 */      libraries = new LinkedList();
/*  241:     */    }
/*  242: 242 */    if (!libraries.contains(libraryClass)) {
/*  243: 243 */      libraries.add(libraryClass);
/*  244:     */    }
/*  245:     */  }
/*  246:     */  
/*  250:     */  public static void removeLibrary(Class libraryClass)
/*  251:     */    throws SoundSystemException
/*  252:     */  {
/*  253: 253 */    if ((libraries == null) || (libraryClass == null)) {
/*  254: 254 */      return;
/*  255:     */    }
/*  256: 256 */    libraries.remove(libraryClass);
/*  257:     */  }
/*  258:     */  
/*  263:     */  public static LinkedList<Class> getLibraries()
/*  264:     */  {
/*  265: 265 */    return libraries;
/*  266:     */  }
/*  267:     */  
/*  273:     */  public static boolean libraryCompatible(Class libraryClass)
/*  274:     */  {
/*  275: 275 */    if (libraryClass == null)
/*  276:     */    {
/*  277: 277 */      errorMessage("Parameter 'libraryClass' null in method'librayCompatible'");
/*  278:     */      
/*  279: 279 */      return false;
/*  280:     */    }
/*  281: 281 */    if (!Library.class.isAssignableFrom(libraryClass))
/*  282:     */    {
/*  283: 283 */      errorMessage("The specified class does not extend class 'Library' in method 'libraryCompatible'");
/*  284:     */      
/*  285: 285 */      return false;
/*  286:     */    }
/*  287:     */    
/*  288: 288 */    Object o = runMethod(libraryClass, "libraryCompatible", new Class[0], new Object[0]);
/*  289:     */    
/*  291: 291 */    if (o == null)
/*  292:     */    {
/*  293: 293 */      errorMessage("Method 'Library.libraryCompatible' returned 'null' in method 'libraryCompatible'");
/*  294:     */      
/*  295: 295 */      return false;
/*  296:     */    }
/*  297:     */    
/*  298: 298 */    return ((Boolean)o).booleanValue();
/*  299:     */  }
/*  300:     */  
/*  306:     */  public static String getLibraryTitle(Class libraryClass)
/*  307:     */  {
/*  308: 308 */    if (libraryClass == null)
/*  309:     */    {
/*  310: 310 */      errorMessage("Parameter 'libraryClass' null in method'getLibrayTitle'");
/*  311:     */      
/*  312: 312 */      return null;
/*  313:     */    }
/*  314: 314 */    if (!Library.class.isAssignableFrom(libraryClass))
/*  315:     */    {
/*  316: 316 */      errorMessage("The specified class does not extend class 'Library' in method 'getLibraryTitle'");
/*  317:     */      
/*  318: 318 */      return null;
/*  319:     */    }
/*  320:     */    
/*  321: 321 */    Object o = runMethod(libraryClass, "getTitle", new Class[0], new Object[0]);
/*  322:     */    
/*  323: 323 */    if (o == null)
/*  324:     */    {
/*  325: 325 */      errorMessage("Method 'Library.getTitle' returned 'null' in method 'getLibraryTitle'");
/*  326:     */      
/*  327: 327 */      return null;
/*  328:     */    }
/*  329:     */    
/*  330: 330 */    return (String)o;
/*  331:     */  }
/*  332:     */  
/*  338:     */  public static String getLibraryDescription(Class libraryClass)
/*  339:     */  {
/*  340: 340 */    if (libraryClass == null)
/*  341:     */    {
/*  342: 342 */      errorMessage("Parameter 'libraryClass' null in method'getLibrayDescription'");
/*  343:     */      
/*  344: 344 */      return null;
/*  345:     */    }
/*  346: 346 */    if (!Library.class.isAssignableFrom(libraryClass))
/*  347:     */    {
/*  348: 348 */      errorMessage("The specified class does not extend class 'Library' in method 'getLibraryDescription'");
/*  349:     */      
/*  350: 350 */      return null;
/*  351:     */    }
/*  352:     */    
/*  353: 353 */    Object o = runMethod(libraryClass, "getDescription", new Class[0], new Object[0]);
/*  354:     */    
/*  355: 355 */    if (o == null)
/*  356:     */    {
/*  357: 357 */      errorMessage("Method 'Library.getDescription' returned 'null' in method 'getLibraryDescription'");
/*  358:     */      
/*  359: 359 */      return null;
/*  360:     */    }
/*  361:     */    
/*  362: 362 */    return (String)o;
/*  363:     */  }
/*  364:     */  
/*  370:     */  public static boolean reverseByteOrder(Class libraryClass)
/*  371:     */  {
/*  372: 372 */    if (libraryClass == null)
/*  373:     */    {
/*  374: 374 */      errorMessage("Parameter 'libraryClass' null in method'reverseByteOrder'");
/*  375:     */      
/*  376: 376 */      return false;
/*  377:     */    }
/*  378: 378 */    if (!Library.class.isAssignableFrom(libraryClass))
/*  379:     */    {
/*  380: 380 */      errorMessage("The specified class does not extend class 'Library' in method 'reverseByteOrder'");
/*  381:     */      
/*  382: 382 */      return false;
/*  383:     */    }
/*  384:     */    
/*  385: 385 */    Object o = runMethod(libraryClass, "reversByteOrder", new Class[0], new Object[0]);
/*  386:     */    
/*  387: 387 */    if (o == null)
/*  388:     */    {
/*  389: 389 */      errorMessage("Method 'Library.reverseByteOrder' returned 'null' in method 'getLibraryDescription'");
/*  390:     */      
/*  391: 391 */      return false;
/*  392:     */    }
/*  393:     */    
/*  394: 394 */    return ((Boolean)o).booleanValue();
/*  395:     */  }
/*  396:     */  
/*  418:     */  public static void setLogger(SoundSystemLogger l)
/*  419:     */  {
/*  420: 420 */    logger = l;
/*  421:     */  }
/*  422:     */  
/*  426:     */  public static SoundSystemLogger getLogger()
/*  427:     */  {
/*  428: 428 */    return logger;
/*  429:     */  }
/*  430:     */  
/*  443:     */  public static synchronized void setNumberNormalChannels(int number)
/*  444:     */  {
/*  445: 445 */    numberNormalChannels = number;
/*  446:     */  }
/*  447:     */  
/*  453:     */  public static synchronized int getNumberNormalChannels()
/*  454:     */  {
/*  455: 455 */    return numberNormalChannels;
/*  456:     */  }
/*  457:     */  
/*  468:     */  public static synchronized void setNumberStreamingChannels(int number)
/*  469:     */  {
/*  470: 470 */    numberStreamingChannels = number;
/*  471:     */  }
/*  472:     */  
/*  477:     */  public static synchronized int getNumberStreamingChannels()
/*  478:     */  {
/*  479: 479 */    return numberStreamingChannels;
/*  480:     */  }
/*  481:     */  
/*  486:     */  public static synchronized void setMasterGain(float value)
/*  487:     */  {
/*  488: 488 */    masterGain = value;
/*  489:     */  }
/*  490:     */  
/*  495:     */  public static synchronized float getMasterGain()
/*  496:     */  {
/*  497: 497 */    return masterGain;
/*  498:     */  }
/*  499:     */  
/*  505:     */  public static synchronized void setDefaultAttenuation(int model)
/*  506:     */  {
/*  507: 507 */    defaultAttenuationModel = model;
/*  508:     */  }
/*  509:     */  
/*  513:     */  public static synchronized int getDefaultAttenuation()
/*  514:     */  {
/*  515: 515 */    return defaultAttenuationModel;
/*  516:     */  }
/*  517:     */  
/*  521:     */  public static synchronized void setDefaultRolloff(float rolloff)
/*  522:     */  {
/*  523: 523 */    defaultRolloffFactor = rolloff;
/*  524:     */  }
/*  525:     */  
/*  529:     */  public static synchronized float getDopplerFactor()
/*  530:     */  {
/*  531: 531 */    return dopplerFactor;
/*  532:     */  }
/*  533:     */  
/*  540:     */  public static synchronized void setDopplerFactor(float factor)
/*  541:     */  {
/*  542: 542 */    dopplerFactor = factor;
/*  543:     */  }
/*  544:     */  
/*  548:     */  public static synchronized float getDopplerVelocity()
/*  549:     */  {
/*  550: 550 */    return dopplerVelocity;
/*  551:     */  }
/*  552:     */  
/*  559:     */  public static synchronized void setDopplerVelocity(float velocity)
/*  560:     */  {
/*  561: 561 */    dopplerVelocity = velocity;
/*  562:     */  }
/*  563:     */  
/*  567:     */  public static synchronized float getDefaultRolloff()
/*  568:     */  {
/*  569: 569 */    return defaultRolloffFactor;
/*  570:     */  }
/*  571:     */  
/*  575:     */  public static synchronized void setDefaultFadeDistance(float distance)
/*  576:     */  {
/*  577: 577 */    defaultFadeDistance = distance;
/*  578:     */  }
/*  579:     */  
/*  583:     */  public static synchronized float getDefaultFadeDistance()
/*  584:     */  {
/*  585: 585 */    return defaultFadeDistance;
/*  586:     */  }
/*  587:     */  
/*  591:     */  public static synchronized void setSoundFilesPackage(String location)
/*  592:     */  {
/*  593: 593 */    soundFilesPackage = location;
/*  594:     */  }
/*  595:     */  
/*  599:     */  public static synchronized String getSoundFilesPackage()
/*  600:     */  {
/*  601: 601 */    return soundFilesPackage;
/*  602:     */  }
/*  603:     */  
/*  607:     */  public static synchronized void setStreamingBufferSize(int size)
/*  608:     */  {
/*  609: 609 */    streamingBufferSize = size;
/*  610:     */  }
/*  611:     */  
/*  615:     */  public static synchronized int getStreamingBufferSize()
/*  616:     */  {
/*  617: 617 */    return streamingBufferSize;
/*  618:     */  }
/*  619:     */  
/*  625:     */  public static synchronized void setNumberStreamingBuffers(int num)
/*  626:     */  {
/*  627: 627 */    numberStreamingBuffers = num;
/*  628:     */  }
/*  629:     */  
/*  633:     */  public static synchronized int getNumberStreamingBuffers()
/*  634:     */  {
/*  635: 635 */    return numberStreamingBuffers;
/*  636:     */  }
/*  637:     */  
/*  645:     */  public static synchronized void setStreamQueueFormatsMatch(boolean val)
/*  646:     */  {
/*  647: 647 */    streamQueueFormatsMatch = val;
/*  648:     */  }
/*  649:     */  
/*  657:     */  public static synchronized boolean getStreamQueueFormatsMatch()
/*  658:     */  {
/*  659: 659 */    return streamQueueFormatsMatch;
/*  660:     */  }
/*  661:     */  
/*  668:     */  public static synchronized void setMaxFileSize(int size)
/*  669:     */  {
/*  670: 670 */    maxFileSize = size;
/*  671:     */  }
/*  672:     */  
/*  676:     */  public static synchronized int getMaxFileSize()
/*  677:     */  {
/*  678: 678 */    return maxFileSize;
/*  679:     */  }
/*  680:     */  
/*  685:     */  public static synchronized void setFileChunkSize(int size)
/*  686:     */  {
/*  687: 687 */    fileChunkSize = size;
/*  688:     */  }
/*  689:     */  
/*  694:     */  public static synchronized int getFileChunkSize()
/*  695:     */  {
/*  696: 696 */    return fileChunkSize;
/*  697:     */  }
/*  698:     */  
/*  703:     */  public static synchronized String getOverrideMIDISynthesizer()
/*  704:     */  {
/*  705: 705 */    return overrideMIDISynthesizer;
/*  706:     */  }
/*  707:     */  
/*  713:     */  public static synchronized void setOverrideMIDISynthesizer(String name)
/*  714:     */  {
/*  715: 715 */    overrideMIDISynthesizer = name;
/*  716:     */  }
/*  717:     */  
/*  724:     */  public static synchronized void setCodec(String extension, Class iCodecClass)
/*  725:     */    throws SoundSystemException
/*  726:     */  {
/*  727: 727 */    if (extension == null) {
/*  728: 728 */      throw new SoundSystemException("Parameter 'extension' null in method 'setCodec'.", 2);
/*  729:     */    }
/*  730:     */    
/*  731: 731 */    if (iCodecClass == null) {
/*  732: 732 */      throw new SoundSystemException("Parameter 'iCodecClass' null in method 'setCodec'.", 2);
/*  733:     */    }
/*  734:     */    
/*  735: 735 */    if (!ICodec.class.isAssignableFrom(iCodecClass)) {
/*  736: 736 */      throw new SoundSystemException("The specified class does not implement interface 'ICodec' in method 'setCodec'", 3);
/*  737:     */    }
/*  738:     */    
/*  740: 740 */    if (codecs == null) {
/*  741: 741 */      codecs = new LinkedList();
/*  742:     */    }
/*  743: 743 */    ListIterator<Codec> i = codecs.listIterator();
/*  744:     */    
/*  746: 746 */    while (i.hasNext())
/*  747:     */    {
/*  748: 748 */      Codec codec = (Codec)i.next();
/*  749: 749 */      if (extension.matches(codec.extensionRegX))
/*  750: 750 */        i.remove();
/*  751:     */    }
/*  752: 752 */    codecs.add(new Codec(extension, iCodecClass));
/*  753:     */    
/*  756: 756 */    if (extension.matches(EXTENSION_MIDI)) {
/*  757: 757 */      midiCodec = true;
/*  758:     */    }
/*  759:     */  }
/*  760:     */  
/*  765:     */  public static synchronized ICodec getCodec(String filename)
/*  766:     */  {
/*  767: 767 */    if (codecs == null) {
/*  768: 768 */      return null;
/*  769:     */    }
/*  770: 770 */    ListIterator<Codec> i = codecs.listIterator();
/*  771:     */    
/*  773: 773 */    while (i.hasNext())
/*  774:     */    {
/*  775: 775 */      Codec codec = (Codec)i.next();
/*  776: 776 */      if (filename.matches(codec.extensionRegX)) {
/*  777: 777 */        return codec.getInstance();
/*  778:     */      }
/*  779:     */    }
/*  780: 780 */    return null;
/*  781:     */  }
/*  782:     */  
/*  788:     */  public static boolean midiCodec()
/*  789:     */  {
/*  790: 790 */    return midiCodec;
/*  791:     */  }
/*  792:     */  
/*  798:     */  public static void addStreamListener(IStreamListener streamListener)
/*  799:     */  {
/*  800: 800 */    synchronized (streamListenersLock)
/*  801:     */    {
/*  802: 802 */      if (streamListeners == null) {
/*  803: 803 */        streamListeners = new LinkedList();
/*  804:     */      }
/*  805: 805 */      if (!streamListeners.contains(streamListener)) {
/*  806: 806 */        streamListeners.add(streamListener);
/*  807:     */      }
/*  808:     */    }
/*  809:     */  }
/*  810:     */  
/*  815:     */  public static void removeStreamListener(IStreamListener streamListener)
/*  816:     */  {
/*  817: 817 */    synchronized (streamListenersLock)
/*  818:     */    {
/*  819: 819 */      if (streamListeners == null) {
/*  820: 820 */        streamListeners = new LinkedList();
/*  821:     */      }
/*  822: 822 */      if (streamListeners.contains(streamListener)) {
/*  823: 823 */        streamListeners.remove(streamListener);
/*  824:     */      }
/*  825:     */    }
/*  826:     */  }
/*  827:     */  
/*  833:     */  public static void notifyEOS(String sourcename, int queueSize)
/*  834:     */  {
/*  835: 835 */    synchronized (streamListenersLock)
/*  836:     */    {
/*  837: 837 */      if (streamListeners == null)
/*  838: 838 */        return;
/*  839:     */    }
/*  840: 840 */    String srcName = sourcename;
/*  841: 841 */    final int qSize = queueSize;
/*  842:     */    
/*  843: 843 */    new Thread()
/*  844:     */    {
/*  846:     */      public void run()
/*  847:     */      {
/*  848: 848 */        synchronized (SoundSystemConfig.streamListenersLock)
/*  849:     */        {
/*  850: 850 */          if (SoundSystemConfig.streamListeners == null)
/*  851: 851 */            return;
/*  852: 852 */          ListIterator<IStreamListener> i = SoundSystemConfig.streamListeners.listIterator();
/*  853:     */          
/*  854: 854 */          while (i.hasNext())
/*  855:     */          {
/*  856: 856 */            IStreamListener streamListener = (IStreamListener)i.next();
/*  857: 857 */            if (streamListener == null) {
/*  858: 858 */              i.remove();
/*  859:     */            } else {
/*  860: 860 */              streamListener.endOfStream(this.val$srcName, qSize);
/*  861:     */            }
/*  862:     */          }
/*  863:     */        }
/*  864:     */      }
/*  865:     */    }.start();
/*  866:     */  }
/*  867:     */  
/*  876:     */  private static void errorMessage(String message)
/*  877:     */  {
/*  878: 878 */    if (logger != null) {
/*  879: 879 */      logger.errorMessage("SoundSystemConfig", message, 0);
/*  880:     */    }
/*  881:     */  }
/*  882:     */  
/*  895:     */  private static Object runMethod(Class c, String method, Class[] paramTypes, Object[] params)
/*  896:     */  {
/*  897: 897 */    Method m = null;
/*  898:     */    try
/*  899:     */    {
/*  900: 900 */      m = c.getMethod(method, paramTypes);
/*  901:     */    }
/*  902:     */    catch (NoSuchMethodException nsme)
/*  903:     */    {
/*  904: 904 */      errorMessage("NoSuchMethodException thrown when attempting to call method '" + method + "' in " + "method 'runMethod'");
/*  905:     */      
/*  907: 907 */      return null;
/*  908:     */    }
/*  909:     */    catch (SecurityException se)
/*  910:     */    {
/*  911: 911 */      errorMessage("Access denied when attempting to call method '" + method + "' in method 'runMethod'");
/*  912:     */      
/*  913: 913 */      return null;
/*  914:     */    }
/*  915:     */    catch (NullPointerException npe)
/*  916:     */    {
/*  917: 917 */      errorMessage("NullPointerException thrown when attempting to call method '" + method + "' in " + "method 'runMethod'");
/*  918:     */      
/*  920: 920 */      return null;
/*  921:     */    }
/*  922: 922 */    if (m == null)
/*  923:     */    {
/*  924: 924 */      errorMessage("Method '" + method + "' not found for the class " + "specified in method 'runMethod'");
/*  925:     */      
/*  926: 926 */      return null;
/*  927:     */    }
/*  928:     */    
/*  929: 929 */    Object o = null;
/*  930:     */    try
/*  931:     */    {
/*  932: 932 */      o = m.invoke(null, params);
/*  933:     */    }
/*  934:     */    catch (IllegalAccessException iae)
/*  935:     */    {
/*  936: 936 */      errorMessage("IllegalAccessException thrown when attempting to invoke method '" + method + "' in " + "method 'runMethod'");
/*  937:     */      
/*  939: 939 */      return null;
/*  940:     */    }
/*  941:     */    catch (IllegalArgumentException iae)
/*  942:     */    {
/*  943: 943 */      errorMessage("IllegalArgumentException thrown when attempting to invoke method '" + method + "' in " + "method 'runMethod'");
/*  944:     */      
/*  946: 946 */      return null;
/*  947:     */    }
/*  948:     */    catch (InvocationTargetException ite)
/*  949:     */    {
/*  950: 950 */      errorMessage("InvocationTargetException thrown while attempting to invoke method 'Library.getTitle' in method 'getLibraryTitle'");
/*  951:     */      
/*  953: 953 */      return null;
/*  954:     */    }
/*  955:     */    catch (NullPointerException npe)
/*  956:     */    {
/*  957: 957 */      errorMessage("NullPointerException thrown when attempting to invoke method '" + method + "' in " + "method 'runMethod'");
/*  958:     */      
/*  960: 960 */      return null;
/*  961:     */    }
/*  962:     */    catch (ExceptionInInitializerError eiie)
/*  963:     */    {
/*  964: 964 */      errorMessage("ExceptionInInitializerError thrown when attempting to invoke method '" + method + "' in " + "method 'runMethod'");
/*  965:     */      
/*  967: 967 */      return null;
/*  968:     */    }
/*  969:     */    
/*  970: 970 */    return o;
/*  971:     */  }
/*  972:     */  
/*  981:     */  private static class Codec
/*  982:     */  {
/*  983:     */    public String extensionRegX;
/*  984:     */    
/*  992:     */    public Class iCodecClass;
/*  993:     */    
/* 1001:     */    public Codec(String extension, Class iCodecClass)
/* 1002:     */    {
/* 1003:1003 */      this.extensionRegX = "";
/* 1004:     */      
/* 1005:1005 */      if ((extension != null) && (extension.length() > 0))
/* 1006:     */      {
/* 1009:1009 */        this.extensionRegX = ".*";
/* 1010:     */        
/* 1011:1011 */        for (int x = 0; x < extension.length(); x++)
/* 1012:     */        {
/* 1014:1014 */          String c = extension.substring(x, x + 1);
/* 1015:1015 */          this.extensionRegX = (this.extensionRegX + "[" + c.toLowerCase(Locale.ENGLISH) + c.toUpperCase(Locale.ENGLISH) + "]");
/* 1016:     */        }
/* 1017:     */        
/* 1019:1019 */        this.extensionRegX += "$";
/* 1020:     */      }
/* 1021:     */      
/* 1022:1022 */      this.iCodecClass = iCodecClass;
/* 1023:     */    }
/* 1024:     */    
/* 1025:     */    public ICodec getInstance()
/* 1026:     */    {
/* 1027:1027 */      if (this.iCodecClass == null) {
/* 1028:1028 */        return null;
/* 1029:     */      }
/* 1030:1030 */      Object o = null;
/* 1031:     */      try
/* 1032:     */      {
/* 1033:1033 */        o = this.iCodecClass.newInstance();
/* 1034:     */      }
/* 1035:     */      catch (InstantiationException ie)
/* 1036:     */      {
/* 1037:1037 */        instantiationErrorMessage();
/* 1038:1038 */        return null;
/* 1039:     */      }
/* 1040:     */      catch (IllegalAccessException iae)
/* 1041:     */      {
/* 1042:1042 */        instantiationErrorMessage();
/* 1043:1043 */        return null;
/* 1044:     */      }
/* 1045:     */      catch (ExceptionInInitializerError eiie)
/* 1046:     */      {
/* 1047:1047 */        instantiationErrorMessage();
/* 1048:1048 */        return null;
/* 1049:     */      }
/* 1050:     */      catch (SecurityException se)
/* 1051:     */      {
/* 1052:1052 */        instantiationErrorMessage();
/* 1053:1053 */        return null;
/* 1054:     */      }
/* 1055:     */      
/* 1057:1057 */      if (o == null)
/* 1058:     */      {
/* 1059:1059 */        instantiationErrorMessage();
/* 1060:1060 */        return null;
/* 1061:     */      }
/* 1062:     */      
/* 1063:1063 */      return (ICodec)o;
/* 1064:     */    }
/* 1065:     */    
/* 1066:     */    private void instantiationErrorMessage()
/* 1067:     */    {
/* 1068:1068 */      SoundSystemConfig.errorMessage("Unrecognized ICodec implementation in method 'getInstance'.  Ensure that the implementing class has one public, parameterless constructor.");
/* 1069:     */    }
/* 1070:     */  }
/* 1071:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.SoundSystemConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */