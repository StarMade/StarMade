/*    1:     */package paulscode.sound;
/*    2:     */
/*    3:     */import java.net.URL;
/*    4:     */import java.util.HashMap;
/*    5:     */import java.util.Iterator;
/*    6:     */import java.util.LinkedList;
/*    7:     */import java.util.List;
/*    8:     */import java.util.ListIterator;
/*    9:     */import java.util.Random;
/*   10:     */import java.util.Set;
/*   11:     */import javax.sound.sampled.AudioFormat;
/*   12:     */
/*  104:     */public class SoundSystem
/*  105:     */{
/*  106:     */  private static final boolean GET = false;
/*  107:     */  private static final boolean SET = true;
/*  108:     */  private static final boolean XXX = false;
/*  109:     */  protected SoundSystemLogger logger;
/*  110:     */  protected Library soundLibrary;
/*  111:     */  protected List<CommandObject> commandQueue;
/*  112:     */  private List<CommandObject> sourcePlayList;
/*  113:     */  protected CommandThread commandThread;
/*  114:     */  public Random randomNumberGenerator;
/*  115: 115 */  protected String className = "SoundSystem";
/*  116:     */  
/*  120: 120 */  private static Class currentLibrary = null;
/*  121:     */  
/*  125: 125 */  private static boolean initialized = false;
/*  126:     */  
/*  130: 130 */  private static SoundSystemException lastException = null;
/*  131:     */  
/*  140:     */  public SoundSystem()
/*  141:     */  {
/*  142: 142 */    this.logger = SoundSystemConfig.getLogger();
/*  143:     */    
/*  144: 144 */    if (this.logger == null)
/*  145:     */    {
/*  146: 146 */      this.logger = new SoundSystemLogger();
/*  147: 147 */      SoundSystemConfig.setLogger(this.logger);
/*  148:     */    }
/*  149:     */    
/*  150: 150 */    linkDefaultLibrariesAndCodecs();
/*  151:     */    
/*  152: 152 */    LinkedList<Class> libraries = SoundSystemConfig.getLibraries();
/*  153:     */    
/*  154: 154 */    if (libraries != null)
/*  155:     */    {
/*  156: 156 */      ListIterator<Class> i = libraries.listIterator();
/*  157:     */      
/*  158: 158 */      while (i.hasNext())
/*  159:     */      {
/*  160: 160 */        Class c = (Class)i.next();
/*  161:     */        try
/*  162:     */        {
/*  163: 163 */          init(c);
/*  164: 164 */          return;
/*  165:     */        }
/*  166:     */        catch (SoundSystemException sse)
/*  167:     */        {
/*  168: 168 */          this.logger.printExceptionMessage(sse, 1);
/*  169:     */        }
/*  170:     */      }
/*  171:     */    }
/*  172:     */    try
/*  173:     */    {
/*  174: 174 */      init(Library.class);
/*  175: 175 */      return;
/*  176:     */    }
/*  177:     */    catch (SoundSystemException sse)
/*  178:     */    {
/*  179: 179 */      this.logger.printExceptionMessage(sse, 1);
/*  180:     */    }
/*  181:     */  }
/*  182:     */  
/*  189:     */  public SoundSystem(Class libraryClass)
/*  190:     */    throws SoundSystemException
/*  191:     */  {
/*  192: 192 */    this.logger = SoundSystemConfig.getLogger();
/*  193:     */    
/*  194: 194 */    if (this.logger == null)
/*  195:     */    {
/*  196: 196 */      this.logger = new SoundSystemLogger();
/*  197: 197 */      SoundSystemConfig.setLogger(this.logger);
/*  198:     */    }
/*  199: 199 */    linkDefaultLibrariesAndCodecs();
/*  200:     */    
/*  201: 201 */    init(libraryClass);
/*  202:     */  }
/*  203:     */  
/*  212:     */  protected void linkDefaultLibrariesAndCodecs() {}
/*  213:     */  
/*  222:     */  protected void init(Class libraryClass)
/*  223:     */    throws SoundSystemException
/*  224:     */  {
/*  225: 225 */    message("", 0);
/*  226: 226 */    message("Starting up " + this.className + "...", 0);
/*  227:     */    
/*  229: 229 */    this.randomNumberGenerator = new Random();
/*  230:     */    
/*  231: 231 */    this.commandQueue = new LinkedList();
/*  232:     */    
/*  233: 233 */    this.sourcePlayList = new LinkedList();
/*  234:     */    
/*  236: 236 */    this.commandThread = new CommandThread(this);
/*  237: 237 */    this.commandThread.start();
/*  238:     */    
/*  239: 239 */    snooze(200L);
/*  240:     */    
/*  241: 241 */    newLibrary(libraryClass);
/*  242: 242 */    message("", 0);
/*  243:     */  }
/*  244:     */  
/*  249:     */  public void cleanup()
/*  250:     */  {
/*  251: 251 */    boolean killException = false;
/*  252: 252 */    message("", 0);
/*  253: 253 */    message(this.className + " shutting down...", 0);
/*  254:     */    
/*  256:     */    try
/*  257:     */    {
/*  258: 258 */      this.commandThread.kill();
/*  259: 259 */      this.commandThread.interrupt();
/*  260:     */    }
/*  261:     */    catch (Exception e)
/*  262:     */    {
/*  263: 263 */      killException = true;
/*  264:     */    }
/*  265:     */    
/*  266: 266 */    if (!killException)
/*  267:     */    {
/*  269: 269 */      for (int i = 0; i < 50; i++)
/*  270:     */      {
/*  271: 271 */        if (!this.commandThread.alive())
/*  272:     */          break;
/*  273: 273 */        snooze(100L);
/*  274:     */      }
/*  275:     */    }
/*  276:     */    
/*  278: 278 */    if ((killException) || (this.commandThread.alive()))
/*  279:     */    {
/*  280: 280 */      errorMessage("Command thread did not die!", 0);
/*  281: 281 */      message("Ignoring errors... continuing clean-up.", 0);
/*  282:     */    }
/*  283:     */    
/*  284: 284 */    initialized(true, false);
/*  285: 285 */    currentLibrary(true, null);
/*  286:     */    
/*  287:     */    try
/*  288:     */    {
/*  289: 289 */      if (this.soundLibrary != null) {
/*  290: 290 */        this.soundLibrary.cleanup();
/*  291:     */      }
/*  292:     */    }
/*  293:     */    catch (Exception e) {
/*  294: 294 */      errorMessage("Problem during Library.cleanup()!", 0);
/*  295: 295 */      message("Ignoring errors... continuing clean-up.", 0);
/*  296:     */    }
/*  297:     */    
/*  299:     */    try
/*  300:     */    {
/*  301: 301 */      if (this.commandQueue != null) {
/*  302: 302 */        this.commandQueue.clear();
/*  303:     */      }
/*  304:     */    }
/*  305:     */    catch (Exception e) {
/*  306: 306 */      errorMessage("Unable to clear the command queue!", 0);
/*  307: 307 */      message("Ignoring errors... continuing clean-up.", 0);
/*  308:     */    }
/*  309:     */    
/*  311:     */    try
/*  312:     */    {
/*  313: 313 */      if (this.sourcePlayList != null) {
/*  314: 314 */        this.sourcePlayList.clear();
/*  315:     */      }
/*  316:     */    }
/*  317:     */    catch (Exception e) {
/*  318: 318 */      errorMessage("Unable to clear the source management list!", 0);
/*  319: 319 */      message("Ignoring errors... continuing clean-up.", 0);
/*  320:     */    }
/*  321:     */    
/*  323: 323 */    this.randomNumberGenerator = null;
/*  324: 324 */    this.soundLibrary = null;
/*  325: 325 */    this.commandQueue = null;
/*  326: 326 */    this.sourcePlayList = null;
/*  327: 327 */    this.commandThread = null;
/*  328:     */    
/*  329: 329 */    importantMessage("Author: Paul Lamb, www.paulscode.com", 1);
/*  330: 330 */    message("", 0);
/*  331:     */  }
/*  332:     */  
/*  340:     */  public void interruptCommandThread()
/*  341:     */  {
/*  342: 342 */    if (this.commandThread == null)
/*  343:     */    {
/*  344: 344 */      errorMessage("Command Thread null in method 'interruptCommandThread'", 0);
/*  345:     */      
/*  346: 346 */      return;
/*  347:     */    }
/*  348:     */    
/*  349: 349 */    this.commandThread.interrupt();
/*  350:     */  }
/*  351:     */  
/*  361:     */  public void loadSound(String filename)
/*  362:     */  {
/*  363: 363 */    CommandQueue(new CommandObject(2, new FilenameURL(filename)));
/*  364:     */    
/*  366: 366 */    this.commandThread.interrupt();
/*  367:     */  }
/*  368:     */  
/*  378:     */  public void loadSound(URL url, String identifier)
/*  379:     */  {
/*  380: 380 */    CommandQueue(new CommandObject(2, new FilenameURL(url, identifier)));
/*  381:     */    
/*  383: 383 */    this.commandThread.interrupt();
/*  384:     */  }
/*  385:     */  
/*  395:     */  public void loadSound(byte[] data, AudioFormat format, String identifier)
/*  396:     */  {
/*  397: 397 */    CommandQueue(new CommandObject(3, identifier, new SoundBuffer(data, format)));
/*  398:     */    
/*  401: 401 */    this.commandThread.interrupt();
/*  402:     */  }
/*  403:     */  
/*  415:     */  public void unloadSound(String filename)
/*  416:     */  {
/*  417: 417 */    CommandQueue(new CommandObject(4, filename));
/*  418:     */    
/*  419: 419 */    this.commandThread.interrupt();
/*  420:     */  }
/*  421:     */  
/*  435:     */  public void queueSound(String sourcename, String filename)
/*  436:     */  {
/*  437: 437 */    CommandQueue(new CommandObject(5, sourcename, new FilenameURL(filename)));
/*  438:     */    
/*  440: 440 */    this.commandThread.interrupt();
/*  441:     */  }
/*  442:     */  
/*  455:     */  public void queueSound(String sourcename, URL url, String identifier)
/*  456:     */  {
/*  457: 457 */    CommandQueue(new CommandObject(5, sourcename, new FilenameURL(url, identifier)));
/*  458:     */    
/*  460: 460 */    this.commandThread.interrupt();
/*  461:     */  }
/*  462:     */  
/*  471:     */  public void dequeueSound(String sourcename, String filename)
/*  472:     */  {
/*  473: 473 */    CommandQueue(new CommandObject(6, sourcename, filename));
/*  474:     */    
/*  476: 476 */    this.commandThread.interrupt();
/*  477:     */  }
/*  478:     */  
/*  497:     */  public void fadeOut(String sourcename, String filename, long milis)
/*  498:     */  {
/*  499: 499 */    FilenameURL fu = null;
/*  500: 500 */    if (filename != null) {
/*  501: 501 */      fu = new FilenameURL(filename);
/*  502:     */    }
/*  503: 503 */    CommandQueue(new CommandObject(7, sourcename, fu, milis));
/*  504:     */    
/*  506: 506 */    this.commandThread.interrupt();
/*  507:     */  }
/*  508:     */  
/*  527:     */  public void fadeOut(String sourcename, URL url, String identifier, long milis)
/*  528:     */  {
/*  529: 529 */    FilenameURL fu = null;
/*  530: 530 */    if ((url != null) && (identifier != null)) {
/*  531: 531 */      fu = new FilenameURL(url, identifier);
/*  532:     */    }
/*  533: 533 */    CommandQueue(new CommandObject(7, sourcename, fu, milis));
/*  534:     */    
/*  536: 536 */    this.commandThread.interrupt();
/*  537:     */  }
/*  538:     */  
/*  560:     */  public void fadeOutIn(String sourcename, String filename, long milisOut, long milisIn)
/*  561:     */  {
/*  562: 562 */    CommandQueue(new CommandObject(8, sourcename, new FilenameURL(filename), milisOut, milisIn));
/*  563:     */    
/*  567: 567 */    this.commandThread.interrupt();
/*  568:     */  }
/*  569:     */  
/*  590:     */  public void fadeOutIn(String sourcename, URL url, String identifier, long milisOut, long milisIn)
/*  591:     */  {
/*  592: 592 */    CommandQueue(new CommandObject(8, sourcename, new FilenameURL(url, identifier), milisOut, milisIn));
/*  593:     */    
/*  597: 597 */    this.commandThread.interrupt();
/*  598:     */  }
/*  599:     */  
/*  612:     */  public void checkFadeVolumes()
/*  613:     */  {
/*  614: 614 */    CommandQueue(new CommandObject(9));
/*  615:     */    
/*  616: 616 */    this.commandThread.interrupt();
/*  617:     */  }
/*  618:     */  
/*  632:     */  public void backgroundMusic(String sourcename, String filename, boolean toLoop)
/*  633:     */  {
/*  634: 634 */    CommandQueue(new CommandObject(12, true, true, toLoop, sourcename, new FilenameURL(filename), 0.0F, 0.0F, 0.0F, 0, 0.0F, false));
/*  635:     */    
/*  638: 638 */    CommandQueue(new CommandObject(24, sourcename));
/*  639:     */    
/*  640: 640 */    this.commandThread.interrupt();
/*  641:     */  }
/*  642:     */  
/*  655:     */  public void backgroundMusic(String sourcename, URL url, String identifier, boolean toLoop)
/*  656:     */  {
/*  657: 657 */    CommandQueue(new CommandObject(12, true, true, toLoop, sourcename, new FilenameURL(url, identifier), 0.0F, 0.0F, 0.0F, 0, 0.0F, false));
/*  658:     */    
/*  663: 663 */    CommandQueue(new CommandObject(24, sourcename));
/*  664:     */    
/*  665: 665 */    this.commandThread.interrupt();
/*  666:     */  }
/*  667:     */  
/*  684:     */  public void newSource(boolean priority, String sourcename, String filename, boolean toLoop, float x, float y, float z, int attmodel, float distOrRoll)
/*  685:     */  {
/*  686: 686 */    CommandQueue(new CommandObject(10, priority, false, toLoop, sourcename, new FilenameURL(filename), x, y, z, attmodel, distOrRoll));
/*  687:     */    
/*  690: 690 */    this.commandThread.interrupt();
/*  691:     */  }
/*  692:     */  
/*  710:     */  public void newSource(boolean priority, String sourcename, URL url, String identifier, boolean toLoop, float x, float y, float z, int attmodel, float distOrRoll)
/*  711:     */  {
/*  712: 712 */    CommandQueue(new CommandObject(10, priority, false, toLoop, sourcename, new FilenameURL(url, identifier), x, y, z, attmodel, distOrRoll));
/*  713:     */    
/*  717: 717 */    this.commandThread.interrupt();
/*  718:     */  }
/*  719:     */  
/*  739:     */  public void newStreamingSource(boolean priority, String sourcename, String filename, boolean toLoop, float x, float y, float z, int attmodel, float distOrRoll)
/*  740:     */  {
/*  741: 741 */    CommandQueue(new CommandObject(10, priority, true, toLoop, sourcename, new FilenameURL(filename), x, y, z, attmodel, distOrRoll));
/*  742:     */    
/*  745: 745 */    this.commandThread.interrupt();
/*  746:     */  }
/*  747:     */  
/*  766:     */  public void newStreamingSource(boolean priority, String sourcename, URL url, String identifier, boolean toLoop, float x, float y, float z, int attmodel, float distOrRoll)
/*  767:     */  {
/*  768: 768 */    CommandQueue(new CommandObject(10, priority, true, toLoop, sourcename, new FilenameURL(url, identifier), x, y, z, attmodel, distOrRoll));
/*  769:     */    
/*  772: 772 */    this.commandThread.interrupt();
/*  773:     */  }
/*  774:     */  
/*  791:     */  public void rawDataStream(AudioFormat audioFormat, boolean priority, String sourcename, float x, float y, float z, int attModel, float distOrRoll)
/*  792:     */  {
/*  793: 793 */    CommandQueue(new CommandObject(11, audioFormat, priority, sourcename, x, y, z, attModel, distOrRoll));
/*  794:     */    
/*  796: 796 */    this.commandThread.interrupt();
/*  797:     */  }
/*  798:     */  
/*  817:     */  public String quickPlay(boolean priority, String filename, boolean toLoop, float x, float y, float z, int attmodel, float distOrRoll)
/*  818:     */  {
/*  819: 819 */    String sourcename = "Source_" + this.randomNumberGenerator.nextInt() + "_" + this.randomNumberGenerator.nextInt();
/*  820:     */    
/*  824: 824 */    CommandQueue(new CommandObject(12, priority, false, toLoop, sourcename, new FilenameURL(filename), x, y, z, attmodel, distOrRoll, true));
/*  825:     */    
/*  828: 828 */    CommandQueue(new CommandObject(24, sourcename));
/*  829:     */    
/*  830: 830 */    this.commandThread.interrupt();
/*  831:     */    
/*  833: 833 */    return sourcename;
/*  834:     */  }
/*  835:     */  
/*  855:     */  public String quickPlay(boolean priority, URL url, String identifier, boolean toLoop, float x, float y, float z, int attmodel, float distOrRoll)
/*  856:     */  {
/*  857: 857 */    String sourcename = "Source_" + this.randomNumberGenerator.nextInt() + "_" + this.randomNumberGenerator.nextInt();
/*  858:     */    
/*  862: 862 */    CommandQueue(new CommandObject(12, priority, false, toLoop, sourcename, new FilenameURL(url, identifier), x, y, z, attmodel, distOrRoll, true));
/*  863:     */    
/*  867: 867 */    CommandQueue(new CommandObject(24, sourcename));
/*  868:     */    
/*  869: 869 */    this.commandThread.interrupt();
/*  870:     */    
/*  872: 872 */    return sourcename;
/*  873:     */  }
/*  874:     */  
/*  898:     */  public String quickStream(boolean priority, String filename, boolean toLoop, float x, float y, float z, int attmodel, float distOrRoll)
/*  899:     */  {
/*  900: 900 */    String sourcename = "Source_" + this.randomNumberGenerator.nextInt() + "_" + this.randomNumberGenerator.nextInt();
/*  901:     */    
/*  905: 905 */    CommandQueue(new CommandObject(12, priority, true, toLoop, sourcename, new FilenameURL(filename), x, y, z, attmodel, distOrRoll, true));
/*  906:     */    
/*  909: 909 */    CommandQueue(new CommandObject(24, sourcename));
/*  910:     */    
/*  911: 911 */    this.commandThread.interrupt();
/*  912:     */    
/*  914: 914 */    return sourcename;
/*  915:     */  }
/*  916:     */  
/*  938:     */  public String quickStream(boolean priority, URL url, String identifier, boolean toLoop, float x, float y, float z, int attmodel, float distOrRoll)
/*  939:     */  {
/*  940: 940 */    String sourcename = "Source_" + this.randomNumberGenerator.nextInt() + "_" + this.randomNumberGenerator.nextInt();
/*  941:     */    
/*  945: 945 */    CommandQueue(new CommandObject(12, priority, true, toLoop, sourcename, new FilenameURL(url, identifier), x, y, z, attmodel, distOrRoll, true));
/*  946:     */    
/*  950: 950 */    CommandQueue(new CommandObject(24, sourcename));
/*  951:     */    
/*  952: 952 */    this.commandThread.interrupt();
/*  953:     */    
/*  955: 955 */    return sourcename;
/*  956:     */  }
/*  957:     */  
/*  965:     */  public void setPosition(String sourcename, float x, float y, float z)
/*  966:     */  {
/*  967: 967 */    CommandQueue(new CommandObject(13, sourcename, x, y, z));
/*  968:     */    
/*  969: 969 */    this.commandThread.interrupt();
/*  970:     */  }
/*  971:     */  
/*  976:     */  public void setVolume(String sourcename, float value)
/*  977:     */  {
/*  978: 978 */    CommandQueue(new CommandObject(14, sourcename, value));
/*  979:     */    
/*  980: 980 */    this.commandThread.interrupt();
/*  981:     */  }
/*  982:     */  
/*  989:     */  public float getVolume(String sourcename)
/*  990:     */  {
/*  991: 991 */    synchronized (SoundSystemConfig.THREAD_SYNC)
/*  992:     */    {
/*  993: 993 */      if (this.soundLibrary != null) {
/*  994: 994 */        return this.soundLibrary.getVolume(sourcename);
/*  995:     */      }
/*  996: 996 */      return 0.0F;
/*  997:     */    }
/*  998:     */  }
/*  999:     */  
/* 1005:     */  public void setPitch(String sourcename, float value)
/* 1006:     */  {
/* 1007:1007 */    CommandQueue(new CommandObject(15, sourcename, value));
/* 1008:     */    
/* 1009:1009 */    this.commandThread.interrupt();
/* 1010:     */  }
/* 1011:     */  
/* 1017:     */  public float getPitch(String sourcename)
/* 1018:     */  {
/* 1019:1019 */    if (this.soundLibrary != null) {
/* 1020:1020 */      return this.soundLibrary.getPitch(sourcename);
/* 1021:     */    }
/* 1022:1022 */    return 1.0F;
/* 1023:     */  }
/* 1024:     */  
/* 1031:     */  public void setPriority(String sourcename, boolean pri)
/* 1032:     */  {
/* 1033:1033 */    CommandQueue(new CommandObject(16, sourcename, pri));
/* 1034:     */    
/* 1035:1035 */    this.commandThread.interrupt();
/* 1036:     */  }
/* 1037:     */  
/* 1042:     */  public void setLooping(String sourcename, boolean lp)
/* 1043:     */  {
/* 1044:1044 */    CommandQueue(new CommandObject(17, sourcename, lp));
/* 1045:     */    
/* 1046:1046 */    this.commandThread.interrupt();
/* 1047:     */  }
/* 1048:     */  
/* 1055:     */  public void setAttenuation(String sourcename, int model)
/* 1056:     */  {
/* 1057:1057 */    CommandQueue(new CommandObject(18, sourcename, model));
/* 1058:     */    
/* 1059:1059 */    this.commandThread.interrupt();
/* 1060:     */  }
/* 1061:     */  
/* 1068:     */  public void setDistOrRoll(String sourcename, float dr)
/* 1069:     */  {
/* 1070:1070 */    CommandQueue(new CommandObject(19, sourcename, dr));
/* 1071:     */    
/* 1072:1072 */    this.commandThread.interrupt();
/* 1073:     */  }
/* 1074:     */  
/* 1081:     */  public void changeDopplerFactor(float dopplerFactor)
/* 1082:     */  {
/* 1083:1083 */    CommandQueue(new CommandObject(20, dopplerFactor));
/* 1084:     */    
/* 1085:1085 */    this.commandThread.interrupt();
/* 1086:     */  }
/* 1087:     */  
/* 1094:     */  public void changeDopplerVelocity(float dopplerVelocity)
/* 1095:     */  {
/* 1096:1096 */    CommandQueue(new CommandObject(21, dopplerVelocity));
/* 1097:     */    
/* 1098:1098 */    this.commandThread.interrupt();
/* 1099:     */  }
/* 1100:     */  
/* 1110:     */  public void setVelocity(String sourcename, float x, float y, float z)
/* 1111:     */  {
/* 1112:1112 */    CommandQueue(new CommandObject(22, sourcename, x, y, z));
/* 1113:     */    
/* 1114:1114 */    this.commandThread.interrupt();
/* 1115:     */  }
/* 1116:     */  
/* 1125:     */  public void setListenerVelocity(float x, float y, float z)
/* 1126:     */  {
/* 1127:1127 */    CommandQueue(new CommandObject(23, x, y, z));
/* 1128:     */    
/* 1129:1129 */    this.commandThread.interrupt();
/* 1130:     */  }
/* 1131:     */  
/* 1136:     */  public float millisecondsPlayed(String sourcename)
/* 1137:     */  {
/* 1138:1138 */    synchronized (SoundSystemConfig.THREAD_SYNC)
/* 1139:     */    {
/* 1140:1140 */      return this.soundLibrary.millisecondsPlayed(sourcename);
/* 1141:     */    }
/* 1142:     */  }
/* 1143:     */  
/* 1157:     */  public void feedRawAudioData(String sourcename, byte[] buffer)
/* 1158:     */  {
/* 1159:1159 */    CommandQueue(new CommandObject(25, sourcename, buffer));
/* 1160:     */    
/* 1161:1161 */    this.commandThread.interrupt();
/* 1162:     */  }
/* 1163:     */  
/* 1167:     */  public void play(String sourcename)
/* 1168:     */  {
/* 1169:1169 */    CommandQueue(new CommandObject(24, sourcename));
/* 1170:1170 */    this.commandThread.interrupt();
/* 1171:     */  }
/* 1172:     */  
/* 1176:     */  public void pause(String sourcename)
/* 1177:     */  {
/* 1178:1178 */    CommandQueue(new CommandObject(26, sourcename));
/* 1179:1179 */    this.commandThread.interrupt();
/* 1180:     */  }
/* 1181:     */  
/* 1185:     */  public void stop(String sourcename)
/* 1186:     */  {
/* 1187:1187 */    CommandQueue(new CommandObject(27, sourcename));
/* 1188:1188 */    this.commandThread.interrupt();
/* 1189:     */  }
/* 1190:     */  
/* 1194:     */  public void rewind(String sourcename)
/* 1195:     */  {
/* 1196:1196 */    CommandQueue(new CommandObject(28, sourcename));
/* 1197:1197 */    this.commandThread.interrupt();
/* 1198:     */  }
/* 1199:     */  
/* 1203:     */  public void flush(String sourcename)
/* 1204:     */  {
/* 1205:1205 */    CommandQueue(new CommandObject(29, sourcename));
/* 1206:1206 */    this.commandThread.interrupt();
/* 1207:     */  }
/* 1208:     */  
/* 1214:     */  public void cull(String sourcename)
/* 1215:     */  {
/* 1216:1216 */    CommandQueue(new CommandObject(30, sourcename));
/* 1217:1217 */    this.commandThread.interrupt();
/* 1218:     */  }
/* 1219:     */  
/* 1225:     */  public void activate(String sourcename)
/* 1226:     */  {
/* 1227:1227 */    CommandQueue(new CommandObject(31, sourcename));
/* 1228:1228 */    this.commandThread.interrupt();
/* 1229:     */  }
/* 1230:     */  
/* 1243:     */  public void setTemporary(String sourcename, boolean temporary)
/* 1244:     */  {
/* 1245:1245 */    CommandQueue(new CommandObject(32, sourcename, temporary));
/* 1246:     */    
/* 1247:1247 */    this.commandThread.interrupt();
/* 1248:     */  }
/* 1249:     */  
/* 1254:     */  public void removeSource(String sourcename)
/* 1255:     */  {
/* 1256:1256 */    CommandQueue(new CommandObject(33, sourcename));
/* 1257:     */    
/* 1258:1258 */    this.commandThread.interrupt();
/* 1259:     */  }
/* 1260:     */  
/* 1266:     */  public void moveListener(float x, float y, float z)
/* 1267:     */  {
/* 1268:1268 */    CommandQueue(new CommandObject(34, x, y, z));
/* 1269:     */    
/* 1270:1270 */    this.commandThread.interrupt();
/* 1271:     */  }
/* 1272:     */  
/* 1278:     */  public void setListenerPosition(float x, float y, float z)
/* 1279:     */  {
/* 1280:1280 */    CommandQueue(new CommandObject(35, x, y, z));
/* 1281:     */    
/* 1282:1282 */    this.commandThread.interrupt();
/* 1283:     */  }
/* 1284:     */  
/* 1289:     */  public void turnListener(float angle)
/* 1290:     */  {
/* 1291:1291 */    CommandQueue(new CommandObject(36, angle));
/* 1292:     */    
/* 1293:1293 */    this.commandThread.interrupt();
/* 1294:     */  }
/* 1295:     */  
/* 1299:     */  public void setListenerAngle(float angle)
/* 1300:     */  {
/* 1301:1301 */    CommandQueue(new CommandObject(37, angle));
/* 1302:     */    
/* 1303:1303 */    this.commandThread.interrupt();
/* 1304:     */  }
/* 1305:     */  
/* 1315:     */  public void setListenerOrientation(float lookX, float lookY, float lookZ, float upX, float upY, float upZ)
/* 1316:     */  {
/* 1317:1317 */    CommandQueue(new CommandObject(38, lookX, lookY, lookZ, upX, upY, upZ));
/* 1318:     */    
/* 1319:1319 */    this.commandThread.interrupt();
/* 1320:     */  }
/* 1321:     */  
/* 1326:     */  public void setMasterVolume(float value)
/* 1327:     */  {
/* 1328:1328 */    CommandQueue(new CommandObject(39, value));
/* 1329:     */    
/* 1330:1330 */    this.commandThread.interrupt();
/* 1331:     */  }
/* 1332:     */  
/* 1337:     */  public float getMasterVolume()
/* 1338:     */  {
/* 1339:1339 */    return SoundSystemConfig.getMasterGain();
/* 1340:     */  }
/* 1341:     */  
/* 1347:     */  public ListenerData getListenerData()
/* 1348:     */  {
/* 1349:1349 */    synchronized (SoundSystemConfig.THREAD_SYNC)
/* 1350:     */    {
/* 1351:1351 */      return this.soundLibrary.getListenerData();
/* 1352:     */    }
/* 1353:     */  }
/* 1354:     */  
/* 1361:     */  public boolean switchLibrary(Class libraryClass)
/* 1362:     */    throws SoundSystemException
/* 1363:     */  {
/* 1364:1364 */    synchronized (SoundSystemConfig.THREAD_SYNC)
/* 1365:     */    {
/* 1366:1366 */      initialized(true, false);
/* 1367:     */      
/* 1368:1368 */      HashMap<String, Source> sourceMap = null;
/* 1369:1369 */      ListenerData listenerData = null;
/* 1370:     */      
/* 1371:1371 */      boolean wasMidiChannel = false;
/* 1372:1372 */      MidiChannel midiChannel = null;
/* 1373:1373 */      FilenameURL midiFilenameURL = null;
/* 1374:1374 */      String midiSourcename = "";
/* 1375:1375 */      boolean midiToLoop = true;
/* 1376:     */      
/* 1377:1377 */      if (this.soundLibrary != null)
/* 1378:     */      {
/* 1379:1379 */        currentLibrary(true, null);
/* 1380:1380 */        sourceMap = copySources(this.soundLibrary.getSources());
/* 1381:1381 */        listenerData = this.soundLibrary.getListenerData();
/* 1382:1382 */        midiChannel = this.soundLibrary.getMidiChannel();
/* 1383:1383 */        if (midiChannel != null)
/* 1384:     */        {
/* 1385:1385 */          wasMidiChannel = true;
/* 1386:1386 */          midiToLoop = midiChannel.getLooping();
/* 1387:1387 */          midiSourcename = midiChannel.getSourcename();
/* 1388:1388 */          midiFilenameURL = midiChannel.getFilenameURL();
/* 1389:     */        }
/* 1390:     */        
/* 1391:1391 */        this.soundLibrary.cleanup();
/* 1392:1392 */        this.soundLibrary = null;
/* 1393:     */      }
/* 1394:1394 */      message("", 0);
/* 1395:1395 */      message("Switching to " + SoundSystemConfig.getLibraryTitle(libraryClass), 0);
/* 1396:     */      
/* 1397:1397 */      message("(" + SoundSystemConfig.getLibraryDescription(libraryClass) + ")", 1);
/* 1398:     */      
/* 1400:     */      try
/* 1401:     */      {
/* 1402:1402 */        this.soundLibrary = ((Library)libraryClass.newInstance());
/* 1403:     */      }
/* 1404:     */      catch (InstantiationException ie)
/* 1405:     */      {
/* 1406:1406 */        errorMessage("The specified library did not load properly", 1);
/* 1407:     */      }
/* 1408:     */      catch (IllegalAccessException iae)
/* 1409:     */      {
/* 1410:1410 */        errorMessage("The specified library did not load properly", 1);
/* 1411:     */      }
/* 1412:     */      catch (ExceptionInInitializerError eiie)
/* 1413:     */      {
/* 1414:1414 */        errorMessage("The specified library did not load properly", 1);
/* 1415:     */      }
/* 1416:     */      catch (SecurityException se)
/* 1417:     */      {
/* 1418:1418 */        errorMessage("The specified library did not load properly", 1);
/* 1419:     */      }
/* 1420:     */      
/* 1421:1421 */      if (errorCheck(this.soundLibrary == null, "Library null after initialization in method 'switchLibrary'", 1))
/* 1422:     */      {
/* 1424:1424 */        SoundSystemException sse = new SoundSystemException(this.className + " did not load properly.  " + "Library was null after initialization.", 4);
/* 1425:     */        
/* 1428:1428 */        lastException(true, sse);
/* 1429:1429 */        initialized(true, true);
/* 1430:1430 */        throw sse;
/* 1431:     */      }
/* 1432:     */      
/* 1433:     */      try
/* 1434:     */      {
/* 1435:1435 */        this.soundLibrary.init();
/* 1436:     */      }
/* 1437:     */      catch (SoundSystemException sse)
/* 1438:     */      {
/* 1439:1439 */        lastException(true, sse);
/* 1440:1440 */        initialized(true, true);
/* 1441:1441 */        throw sse;
/* 1442:     */      }
/* 1443:     */      
/* 1444:1444 */      this.soundLibrary.setListenerData(listenerData);
/* 1445:1445 */      if (wasMidiChannel)
/* 1446:     */      {
/* 1447:1447 */        if (midiChannel != null)
/* 1448:1448 */          midiChannel.cleanup();
/* 1449:1449 */        midiChannel = new MidiChannel(midiToLoop, midiSourcename, midiFilenameURL);
/* 1450:     */        
/* 1451:1451 */        this.soundLibrary.setMidiChannel(midiChannel);
/* 1452:     */      }
/* 1453:1453 */      this.soundLibrary.copySources(sourceMap);
/* 1454:     */      
/* 1455:1455 */      message("", 0);
/* 1456:     */      
/* 1457:1457 */      lastException(true, null);
/* 1458:1458 */      initialized(true, true);
/* 1459:     */      
/* 1460:1460 */      return true;
/* 1461:     */    }
/* 1462:     */  }
/* 1463:     */  
/* 1470:     */  public boolean newLibrary(Class libraryClass)
/* 1471:     */    throws SoundSystemException
/* 1472:     */  {
/* 1473:1473 */    initialized(true, false);
/* 1474:     */    
/* 1475:1475 */    CommandQueue(new CommandObject(40, libraryClass));
/* 1476:     */    
/* 1477:1477 */    this.commandThread.interrupt();
/* 1478:     */    
/* 1479:1479 */    for (int x = 0; (!initialized(false, false)) && (x < 100); x++)
/* 1480:     */    {
/* 1481:1481 */      snooze(400L);
/* 1482:1482 */      this.commandThread.interrupt();
/* 1483:     */    }
/* 1484:     */    
/* 1485:1485 */    if (!initialized(false, false))
/* 1486:     */    {
/* 1487:1487 */      SoundSystemException sse = new SoundSystemException(this.className + " did not load after 30 seconds.", 4);
/* 1488:     */      
/* 1491:1491 */      lastException(true, sse);
/* 1492:1492 */      throw sse;
/* 1493:     */    }
/* 1494:     */    
/* 1496:1496 */    SoundSystemException sse = lastException(false, null);
/* 1497:1497 */    if (sse != null) {
/* 1498:1498 */      throw sse;
/* 1499:     */    }
/* 1500:1500 */    return true;
/* 1501:     */  }
/* 1502:     */  
/* 1511:     */  private void CommandNewLibrary(Class libraryClass)
/* 1512:     */  {
/* 1513:1513 */    initialized(true, false);
/* 1514:     */    
/* 1515:1515 */    String headerMessage = "Initializing ";
/* 1516:1516 */    if (this.soundLibrary != null)
/* 1517:     */    {
/* 1518:1518 */      currentLibrary(true, null);
/* 1519:     */      
/* 1520:1520 */      headerMessage = "Switching to ";
/* 1521:1521 */      this.soundLibrary.cleanup();
/* 1522:1522 */      this.soundLibrary = null;
/* 1523:     */    }
/* 1524:1524 */    message(headerMessage + SoundSystemConfig.getLibraryTitle(libraryClass), 0);
/* 1525:     */    
/* 1526:1526 */    message("(" + SoundSystemConfig.getLibraryDescription(libraryClass) + ")", 1);
/* 1527:     */    
/* 1529:     */    try
/* 1530:     */    {
/* 1531:1531 */      this.soundLibrary = ((Library)libraryClass.newInstance());
/* 1532:     */    }
/* 1533:     */    catch (InstantiationException ie)
/* 1534:     */    {
/* 1535:1535 */      errorMessage("The specified library did not load properly", 1);
/* 1536:     */    }
/* 1537:     */    catch (IllegalAccessException iae)
/* 1538:     */    {
/* 1539:1539 */      errorMessage("The specified library did not load properly", 1);
/* 1540:     */    }
/* 1541:     */    catch (ExceptionInInitializerError eiie)
/* 1542:     */    {
/* 1543:1543 */      errorMessage("The specified library did not load properly", 1);
/* 1544:     */    }
/* 1545:     */    catch (SecurityException se)
/* 1546:     */    {
/* 1547:1547 */      errorMessage("The specified library did not load properly", 1);
/* 1548:     */    }
/* 1549:     */    
/* 1550:1550 */    if (errorCheck(this.soundLibrary == null, "Library null after initialization in method 'newLibrary'", 1))
/* 1551:     */    {
/* 1553:1553 */      lastException(true, new SoundSystemException(this.className + " did not load properly.  " + "Library was null after initialization.", 4));
/* 1554:     */      
/* 1557:1557 */      importantMessage("Switching to silent mode", 1);
/* 1558:     */      
/* 1559:     */      try
/* 1560:     */      {
/* 1561:1561 */        this.soundLibrary = new Library();
/* 1562:     */      }
/* 1563:     */      catch (SoundSystemException sse)
/* 1564:     */      {
/* 1565:1565 */        lastException(true, new SoundSystemException("Silent mode did not load properly.  Library was null after initialization.", 4));
/* 1566:     */        
/* 1569:1569 */        initialized(true, true);
/* 1570:1570 */        return;
/* 1571:     */      }
/* 1572:     */    }
/* 1573:     */    
/* 1574:     */    try
/* 1575:     */    {
/* 1576:1576 */      this.soundLibrary.init();
/* 1577:     */    }
/* 1578:     */    catch (SoundSystemException sse)
/* 1579:     */    {
/* 1580:1580 */      lastException(true, sse);
/* 1581:1581 */      initialized(true, true);
/* 1582:1582 */      return;
/* 1583:     */    }
/* 1584:     */    
/* 1585:1585 */    lastException(true, null);
/* 1586:1586 */    initialized(true, true);
/* 1587:     */  }
/* 1588:     */  
/* 1595:     */  private void CommandInitialize()
/* 1596:     */  {
/* 1597:     */    try
/* 1598:     */    {
/* 1599:1599 */      if (errorCheck(this.soundLibrary == null, "Library null after initialization in method 'CommandInitialize'", 1))
/* 1600:     */      {
/* 1603:1603 */        SoundSystemException sse = new SoundSystemException(this.className + " did not load properly.  " + "Library was null after initialization.", 4);
/* 1604:     */        
/* 1607:1607 */        lastException(true, sse);
/* 1608:1608 */        throw sse;
/* 1609:     */      }
/* 1610:1610 */      this.soundLibrary.init();
/* 1611:     */    }
/* 1612:     */    catch (SoundSystemException sse)
/* 1613:     */    {
/* 1614:1614 */      lastException(true, sse);
/* 1615:1615 */      initialized(true, true);
/* 1616:     */    }
/* 1617:     */  }
/* 1618:     */  
/* 1624:     */  private void CommandLoadSound(FilenameURL filenameURL)
/* 1625:     */  {
/* 1626:1626 */    if (this.soundLibrary != null) {
/* 1627:1627 */      this.soundLibrary.loadSound(filenameURL);
/* 1628:     */    } else {
/* 1629:1629 */      errorMessage("Variable 'soundLibrary' null in method 'CommandLoadSound'", 0);
/* 1630:     */    }
/* 1631:     */  }
/* 1632:     */  
/* 1641:     */  private void CommandLoadSound(SoundBuffer buffer, String identifier)
/* 1642:     */  {
/* 1643:1643 */    if (this.soundLibrary != null) {
/* 1644:1644 */      this.soundLibrary.loadSound(buffer, identifier);
/* 1645:     */    } else {
/* 1646:1646 */      errorMessage("Variable 'soundLibrary' null in method 'CommandLoadSound'", 0);
/* 1647:     */    }
/* 1648:     */  }
/* 1649:     */  
/* 1655:     */  private void CommandUnloadSound(String filename)
/* 1656:     */  {
/* 1657:1657 */    if (this.soundLibrary != null) {
/* 1658:1658 */      this.soundLibrary.unloadSound(filename);
/* 1659:     */    } else {
/* 1660:1660 */      errorMessage("Variable 'soundLibrary' null in method 'CommandLoadSound'", 0);
/* 1661:     */    }
/* 1662:     */  }
/* 1663:     */  
/* 1673:     */  private void CommandQueueSound(String sourcename, FilenameURL filenameURL)
/* 1674:     */  {
/* 1675:1675 */    if (this.soundLibrary != null) {
/* 1676:1676 */      this.soundLibrary.queueSound(sourcename, filenameURL);
/* 1677:     */    } else {
/* 1678:1678 */      errorMessage("Variable 'soundLibrary' null in method 'CommandQueueSound'", 0);
/* 1679:     */    }
/* 1680:     */  }
/* 1681:     */  
/* 1690:     */  private void CommandDequeueSound(String sourcename, String filename)
/* 1691:     */  {
/* 1692:1692 */    if (this.soundLibrary != null) {
/* 1693:1693 */      this.soundLibrary.dequeueSound(sourcename, filename);
/* 1694:     */    } else {
/* 1695:1695 */      errorMessage("Variable 'soundLibrary' null in method 'CommandDequeueSound'", 0);
/* 1696:     */    }
/* 1697:     */  }
/* 1698:     */  
/* 1714:     */  private void CommandFadeOut(String sourcename, FilenameURL filenameURL, long milis)
/* 1715:     */  {
/* 1716:1716 */    if (this.soundLibrary != null) {
/* 1717:1717 */      this.soundLibrary.fadeOut(sourcename, filenameURL, milis);
/* 1718:     */    } else {
/* 1719:1719 */      errorMessage("Variable 'soundLibrary' null in method 'CommandFadeOut'", 0);
/* 1720:     */    }
/* 1721:     */  }
/* 1722:     */  
/* 1739:     */  private void CommandFadeOutIn(String sourcename, FilenameURL filenameURL, long milisOut, long milisIn)
/* 1740:     */  {
/* 1741:1741 */    if (this.soundLibrary != null) {
/* 1742:1742 */      this.soundLibrary.fadeOutIn(sourcename, filenameURL, milisOut, milisIn);
/* 1743:     */    }
/* 1744:     */    else {
/* 1745:1745 */      errorMessage("Variable 'soundLibrary' null in method 'CommandFadeOutIn'", 0);
/* 1746:     */    }
/* 1747:     */  }
/* 1748:     */  
/* 1761:     */  private void CommandCheckFadeVolumes()
/* 1762:     */  {
/* 1763:1763 */    if (this.soundLibrary != null) {
/* 1764:1764 */      this.soundLibrary.checkFadeVolumes();
/* 1765:     */    } else {
/* 1766:1766 */      errorMessage("Variable 'soundLibrary' null in method 'CommandCheckFadeVolumes'", 0);
/* 1767:     */    }
/* 1768:     */  }
/* 1769:     */  
/* 1788:     */  private void CommandNewSource(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float x, float y, float z, int attModel, float distORroll)
/* 1789:     */  {
/* 1790:1790 */    if (this.soundLibrary != null)
/* 1791:     */    {
/* 1792:1792 */      if ((filenameURL.getFilename().matches(SoundSystemConfig.EXTENSION_MIDI)) && (!SoundSystemConfig.midiCodec()))
/* 1793:     */      {
/* 1796:1796 */        this.soundLibrary.loadMidi(toLoop, sourcename, filenameURL);
/* 1797:     */      }
/* 1798:     */      else
/* 1799:     */      {
/* 1800:1800 */        this.soundLibrary.newSource(priority, toStream, toLoop, sourcename, filenameURL, x, y, z, attModel, distORroll);
/* 1801:     */      }
/* 1802:     */      
/* 1804:     */    }
/* 1805:     */    else {
/* 1806:1806 */      errorMessage("Variable 'soundLibrary' null in method 'CommandNewSource'", 0);
/* 1807:     */    }
/* 1808:     */  }
/* 1809:     */  
/* 1825:     */  private void CommandRawDataStream(AudioFormat audioFormat, boolean priority, String sourcename, float x, float y, float z, int attModel, float distOrRoll)
/* 1826:     */  {
/* 1827:1827 */    if (this.soundLibrary != null) {
/* 1828:1828 */      this.soundLibrary.rawDataStream(audioFormat, priority, sourcename, x, y, z, attModel, distOrRoll);
/* 1829:     */    }
/* 1830:     */    else {
/* 1831:1831 */      errorMessage("Variable 'soundLibrary' null in method 'CommandRawDataStream'", 0);
/* 1832:     */    }
/* 1833:     */  }
/* 1834:     */  
/* 1855:     */  private void CommandQuickPlay(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float x, float y, float z, int attModel, float distORroll, boolean temporary)
/* 1856:     */  {
/* 1857:1857 */    if (this.soundLibrary != null)
/* 1858:     */    {
/* 1859:1859 */      if ((filenameURL.getFilename().matches(SoundSystemConfig.EXTENSION_MIDI)) && (!SoundSystemConfig.midiCodec()))
/* 1860:     */      {
/* 1862:1862 */        this.soundLibrary.loadMidi(toLoop, sourcename, filenameURL);
/* 1863:     */      }
/* 1864:     */      else
/* 1865:     */      {
/* 1866:1866 */        this.soundLibrary.quickPlay(priority, toStream, toLoop, sourcename, filenameURL, x, y, z, attModel, distORroll, temporary);
/* 1867:     */      }
/* 1868:     */      
/* 1870:     */    }
/* 1871:     */    else {
/* 1872:1872 */      errorMessage("Variable 'soundLibrary' null in method 'CommandQuickPlay'", 0);
/* 1873:     */    }
/* 1874:     */  }
/* 1875:     */  
/* 1885:     */  private void CommandSetPosition(String sourcename, float x, float y, float z)
/* 1886:     */  {
/* 1887:1887 */    if (this.soundLibrary != null) {
/* 1888:1888 */      this.soundLibrary.setPosition(sourcename, x, y, z);
/* 1889:     */    } else {
/* 1890:1890 */      errorMessage("Variable 'soundLibrary' null in method 'CommandMoveSource'", 0);
/* 1891:     */    }
/* 1892:     */  }
/* 1893:     */  
/* 1900:     */  private void CommandSetVolume(String sourcename, float value)
/* 1901:     */  {
/* 1902:1902 */    if (this.soundLibrary != null) {
/* 1903:1903 */      this.soundLibrary.setVolume(sourcename, value);
/* 1904:     */    } else {
/* 1905:1905 */      errorMessage("Variable 'soundLibrary' null in method 'CommandSetVolume'", 0);
/* 1906:     */    }
/* 1907:     */  }
/* 1908:     */  
/* 1915:     */  private void CommandSetPitch(String sourcename, float value)
/* 1916:     */  {
/* 1917:1917 */    if (this.soundLibrary != null) {
/* 1918:1918 */      this.soundLibrary.setPitch(sourcename, value);
/* 1919:     */    } else {
/* 1920:1920 */      errorMessage("Variable 'soundLibrary' null in method 'CommandSetPitch'", 0);
/* 1921:     */    }
/* 1922:     */  }
/* 1923:     */  
/* 1931:     */  private void CommandSetPriority(String sourcename, boolean pri)
/* 1932:     */  {
/* 1933:1933 */    if (this.soundLibrary != null) {
/* 1934:1934 */      this.soundLibrary.setPriority(sourcename, pri);
/* 1935:     */    } else {
/* 1936:1936 */      errorMessage("Variable 'soundLibrary' null in method 'CommandSetPriority'", 0);
/* 1937:     */    }
/* 1938:     */  }
/* 1939:     */  
/* 1946:     */  private void CommandSetLooping(String sourcename, boolean lp)
/* 1947:     */  {
/* 1948:1948 */    if (this.soundLibrary != null) {
/* 1949:1949 */      this.soundLibrary.setLooping(sourcename, lp);
/* 1950:     */    } else {
/* 1951:1951 */      errorMessage("Variable 'soundLibrary' null in method 'CommandSetLooping'", 0);
/* 1952:     */    }
/* 1953:     */  }
/* 1954:     */  
/* 1963:     */  private void CommandSetAttenuation(String sourcename, int model)
/* 1964:     */  {
/* 1965:1965 */    if (this.soundLibrary != null) {
/* 1966:1966 */      this.soundLibrary.setAttenuation(sourcename, model);
/* 1967:     */    } else {
/* 1968:1968 */      errorMessage("Variable 'soundLibrary' null in method 'CommandSetAttenuation'", 0);
/* 1969:     */    }
/* 1970:     */  }
/* 1971:     */  
/* 1979:     */  private void CommandSetDistOrRoll(String sourcename, float dr)
/* 1980:     */  {
/* 1981:1981 */    if (this.soundLibrary != null) {
/* 1982:1982 */      this.soundLibrary.setDistOrRoll(sourcename, dr);
/* 1983:     */    } else {
/* 1984:1984 */      errorMessage("Variable 'soundLibrary' null in method 'CommandSetDistOrRoll'", 0);
/* 1985:     */    }
/* 1986:     */  }
/* 1987:     */  
/* 1996:     */  private void CommandChangeDopplerFactor(float dopplerFactor)
/* 1997:     */  {
/* 1998:1998 */    if (this.soundLibrary != null)
/* 1999:     */    {
/* 2000:2000 */      SoundSystemConfig.setDopplerFactor(dopplerFactor);
/* 2001:2001 */      this.soundLibrary.dopplerChanged();
/* 2002:     */    }
/* 2003:     */    else {
/* 2004:2004 */      errorMessage("Variable 'soundLibrary' null in method 'CommandSetDopplerFactor'", 0);
/* 2005:     */    }
/* 2006:     */  }
/* 2007:     */  
/* 2016:     */  private void CommandChangeDopplerVelocity(float dopplerVelocity)
/* 2017:     */  {
/* 2018:2018 */    if (this.soundLibrary != null)
/* 2019:     */    {
/* 2020:2020 */      SoundSystemConfig.setDopplerVelocity(dopplerVelocity);
/* 2021:2021 */      this.soundLibrary.dopplerChanged();
/* 2022:     */    }
/* 2023:     */    else {
/* 2024:2024 */      errorMessage("Variable 'soundLibrary' null in method 'CommandSetDopplerFactor'", 0);
/* 2025:     */    }
/* 2026:     */  }
/* 2027:     */  
/* 2039:     */  private void CommandSetVelocity(String sourcename, float x, float y, float z)
/* 2040:     */  {
/* 2041:2041 */    if (this.soundLibrary != null) {
/* 2042:2042 */      this.soundLibrary.setVelocity(sourcename, x, y, z);
/* 2043:     */    } else {
/* 2044:2044 */      errorMessage("Variable 'soundLibrary' null in method 'CommandVelocity'", 0);
/* 2045:     */    }
/* 2046:     */  }
/* 2047:     */  
/* 2058:     */  private void CommandSetListenerVelocity(float x, float y, float z)
/* 2059:     */  {
/* 2060:2060 */    if (this.soundLibrary != null) {
/* 2061:2061 */      this.soundLibrary.setListenerVelocity(x, y, z);
/* 2062:     */    } else {
/* 2063:2063 */      errorMessage("Variable 'soundLibrary' null in method 'CommandSetListenerVelocity'", 0);
/* 2064:     */    }
/* 2065:     */  }
/* 2066:     */  
/* 2073:     */  private void CommandPlay(String sourcename)
/* 2074:     */  {
/* 2075:2075 */    if (this.soundLibrary != null) {
/* 2076:2076 */      this.soundLibrary.play(sourcename);
/* 2077:     */    } else {
/* 2078:2078 */      errorMessage("Variable 'soundLibrary' null in method 'CommandPlay'", 0);
/* 2079:     */    }
/* 2080:     */  }
/* 2081:     */  
/* 2090:     */  private void CommandFeedRawAudioData(String sourcename, byte[] buffer)
/* 2091:     */  {
/* 2092:2092 */    if (this.soundLibrary != null) {
/* 2093:2093 */      this.soundLibrary.feedRawAudioData(sourcename, buffer);
/* 2094:     */    } else {
/* 2095:2095 */      errorMessage("Variable 'soundLibrary' null in method 'CommandFeedRawAudioData'", 0);
/* 2096:     */    }
/* 2097:     */  }
/* 2098:     */  
/* 2104:     */  private void CommandPause(String sourcename)
/* 2105:     */  {
/* 2106:2106 */    if (this.soundLibrary != null) {
/* 2107:2107 */      this.soundLibrary.pause(sourcename);
/* 2108:     */    } else {
/* 2109:2109 */      errorMessage("Variable 'soundLibrary' null in method 'CommandPause'", 0);
/* 2110:     */    }
/* 2111:     */  }
/* 2112:     */  
/* 2118:     */  private void CommandStop(String sourcename)
/* 2119:     */  {
/* 2120:2120 */    if (this.soundLibrary != null) {
/* 2121:2121 */      this.soundLibrary.stop(sourcename);
/* 2122:     */    } else {
/* 2123:2123 */      errorMessage("Variable 'soundLibrary' null in method 'CommandStop'", 0);
/* 2124:     */    }
/* 2125:     */  }
/* 2126:     */  
/* 2132:     */  private void CommandRewind(String sourcename)
/* 2133:     */  {
/* 2134:2134 */    if (this.soundLibrary != null) {
/* 2135:2135 */      this.soundLibrary.rewind(sourcename);
/* 2136:     */    } else {
/* 2137:2137 */      errorMessage("Variable 'soundLibrary' null in method 'CommandRewind'", 0);
/* 2138:     */    }
/* 2139:     */  }
/* 2140:     */  
/* 2146:     */  private void CommandFlush(String sourcename)
/* 2147:     */  {
/* 2148:2148 */    if (this.soundLibrary != null) {
/* 2149:2149 */      this.soundLibrary.flush(sourcename);
/* 2150:     */    } else {
/* 2151:2151 */      errorMessage("Variable 'soundLibrary' null in method 'CommandFlush'", 0);
/* 2152:     */    }
/* 2153:     */  }
/* 2154:     */  
/* 2168:     */  private void CommandSetTemporary(String sourcename, boolean temporary)
/* 2169:     */  {
/* 2170:2170 */    if (this.soundLibrary != null) {
/* 2171:2171 */      this.soundLibrary.setTemporary(sourcename, temporary);
/* 2172:     */    } else {
/* 2173:2173 */      errorMessage("Variable 'soundLibrary' null in method 'CommandSetActive'", 0);
/* 2174:     */    }
/* 2175:     */  }
/* 2176:     */  
/* 2182:     */  private void CommandRemoveSource(String sourcename)
/* 2183:     */  {
/* 2184:2184 */    if (this.soundLibrary != null) {
/* 2185:2185 */      this.soundLibrary.removeSource(sourcename);
/* 2186:     */    } else {
/* 2187:2187 */      errorMessage("Variable 'soundLibrary' null in method 'CommandRemoveSource'", 0);
/* 2188:     */    }
/* 2189:     */  }
/* 2190:     */  
/* 2198:     */  private void CommandMoveListener(float x, float y, float z)
/* 2199:     */  {
/* 2200:2200 */    if (this.soundLibrary != null) {
/* 2201:2201 */      this.soundLibrary.moveListener(x, y, z);
/* 2202:     */    } else {
/* 2203:2203 */      errorMessage("Variable 'soundLibrary' null in method 'CommandMoveListener'", 0);
/* 2204:     */    }
/* 2205:     */  }
/* 2206:     */  
/* 2214:     */  private void CommandSetListenerPosition(float x, float y, float z)
/* 2215:     */  {
/* 2216:2216 */    if (this.soundLibrary != null) {
/* 2217:2217 */      this.soundLibrary.setListenerPosition(x, y, z);
/* 2218:     */    } else {
/* 2219:2219 */      errorMessage("Variable 'soundLibrary' null in method 'CommandSetListenerPosition'", 0);
/* 2220:     */    }
/* 2221:     */  }
/* 2222:     */  
/* 2230:     */  private void CommandTurnListener(float angle)
/* 2231:     */  {
/* 2232:2232 */    if (this.soundLibrary != null) {
/* 2233:2233 */      this.soundLibrary.turnListener(angle);
/* 2234:     */    } else {
/* 2235:2235 */      errorMessage("Variable 'soundLibrary' null in method 'CommandTurnListener'", 0);
/* 2236:     */    }
/* 2237:     */  }
/* 2238:     */  
/* 2245:     */  private void CommandSetListenerAngle(float angle)
/* 2246:     */  {
/* 2247:2247 */    if (this.soundLibrary != null) {
/* 2248:2248 */      this.soundLibrary.setListenerAngle(angle);
/* 2249:     */    } else {
/* 2250:2250 */      errorMessage("Variable 'soundLibrary' null in method 'CommandSetListenerAngle'", 0);
/* 2251:     */    }
/* 2252:     */  }
/* 2253:     */  
/* 2267:     */  private void CommandSetListenerOrientation(float lookX, float lookY, float lookZ, float upX, float upY, float upZ)
/* 2268:     */  {
/* 2269:2269 */    if (this.soundLibrary != null) {
/* 2270:2270 */      this.soundLibrary.setListenerOrientation(lookX, lookY, lookZ, upX, upY, upZ);
/* 2271:     */    }
/* 2272:     */    else {
/* 2273:2273 */      errorMessage("Variable 'soundLibrary' null in method 'CommandSetListenerOrientation'", 0);
/* 2274:     */    }
/* 2275:     */  }
/* 2276:     */  
/* 2284:     */  private void CommandCull(String sourcename)
/* 2285:     */  {
/* 2286:2286 */    if (this.soundLibrary != null) {
/* 2287:2287 */      this.soundLibrary.cull(sourcename);
/* 2288:     */    } else {
/* 2289:2289 */      errorMessage("Variable 'soundLibrary' null in method 'CommandCull'", 0);
/* 2290:     */    }
/* 2291:     */  }
/* 2292:     */  
/* 2298:     */  private void CommandActivate(String sourcename)
/* 2299:     */  {
/* 2300:2300 */    if (this.soundLibrary != null) {
/* 2301:2301 */      this.soundLibrary.activate(sourcename);
/* 2302:     */    } else {
/* 2303:2303 */      errorMessage("Variable 'soundLibrary' null in method 'CommandActivate'", 0);
/* 2304:     */    }
/* 2305:     */  }
/* 2306:     */  
/* 2313:     */  private void CommandSetMasterVolume(float value)
/* 2314:     */  {
/* 2315:2315 */    if (this.soundLibrary != null) {
/* 2316:2316 */      this.soundLibrary.setMasterVolume(value);
/* 2317:     */    } else {
/* 2318:2318 */      errorMessage("Variable 'soundLibrary' null in method 'CommandSetMasterVolume'", 0);
/* 2319:     */    }
/* 2320:     */  }
/* 2321:     */  
/* 2339:     */  protected void ManageSources() {}
/* 2340:     */  
/* 2357:     */  public boolean CommandQueue(CommandObject newCommand)
/* 2358:     */  {
/* 2359:2359 */    synchronized (SoundSystemConfig.THREAD_SYNC)
/* 2360:     */    {
/* 2361:2361 */      if (newCommand == null)
/* 2362:     */      {
/* 2364:2364 */        boolean activations = false;
/* 2365:     */        
/* 2368:2368 */        while ((this.commandQueue != null) && (this.commandQueue.size() > 0))
/* 2369:     */        {
/* 2371:2371 */          CommandObject commandObject = (CommandObject)this.commandQueue.remove(0);
/* 2372:     */          
/* 2373:2373 */          if (commandObject != null)
/* 2374:     */          {
/* 2375:2375 */            switch (commandObject.Command)
/* 2376:     */            {
/* 2377:     */            case 1: 
/* 2378:2378 */              CommandInitialize();
/* 2379:2379 */              break;
/* 2380:     */            case 2: 
/* 2381:2381 */              CommandLoadSound((FilenameURL)commandObject.objectArgs[0]);
/* 2382:     */              
/* 2383:2383 */              break;
/* 2384:     */            case 3: 
/* 2385:2385 */              CommandLoadSound((SoundBuffer)commandObject.objectArgs[0], commandObject.stringArgs[0]);
/* 2386:     */              
/* 2388:2388 */              break;
/* 2389:     */            case 4: 
/* 2390:2390 */              CommandUnloadSound(commandObject.stringArgs[0]);
/* 2391:2391 */              break;
/* 2392:     */            case 5: 
/* 2393:2393 */              CommandQueueSound(commandObject.stringArgs[0], (FilenameURL)commandObject.objectArgs[0]);
/* 2394:     */              
/* 2395:2395 */              break;
/* 2396:     */            case 6: 
/* 2397:2397 */              CommandDequeueSound(commandObject.stringArgs[0], commandObject.stringArgs[1]);
/* 2398:     */              
/* 2399:2399 */              break;
/* 2400:     */            case 7: 
/* 2401:2401 */              CommandFadeOut(commandObject.stringArgs[0], (FilenameURL)commandObject.objectArgs[0], commandObject.longArgs[0]);
/* 2402:     */              
/* 2404:2404 */              break;
/* 2405:     */            case 8: 
/* 2406:2406 */              CommandFadeOutIn(commandObject.stringArgs[0], (FilenameURL)commandObject.objectArgs[0], commandObject.longArgs[0], commandObject.longArgs[1]);
/* 2407:     */              
/* 2410:2410 */              break;
/* 2411:     */            case 9: 
/* 2412:2412 */              CommandCheckFadeVolumes();
/* 2413:2413 */              break;
/* 2414:     */            case 10: 
/* 2415:2415 */              CommandNewSource(commandObject.boolArgs[0], commandObject.boolArgs[1], commandObject.boolArgs[2], commandObject.stringArgs[0], (FilenameURL)commandObject.objectArgs[0], commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2], commandObject.intArgs[0], commandObject.floatArgs[3]);
/* 2416:     */              
/* 2425:2425 */              break;
/* 2426:     */            case 11: 
/* 2427:2427 */              CommandRawDataStream((AudioFormat)commandObject.objectArgs[0], commandObject.boolArgs[0], commandObject.stringArgs[0], commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2], commandObject.intArgs[0], commandObject.floatArgs[3]);
/* 2428:     */              
/* 2436:2436 */              break;
/* 2437:     */            case 12: 
/* 2438:2438 */              CommandQuickPlay(commandObject.boolArgs[0], commandObject.boolArgs[1], commandObject.boolArgs[2], commandObject.stringArgs[0], (FilenameURL)commandObject.objectArgs[0], commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2], commandObject.intArgs[0], commandObject.floatArgs[3], commandObject.boolArgs[3]);
/* 2439:     */              
/* 2449:2449 */              break;
/* 2450:     */            case 13: 
/* 2451:2451 */              CommandSetPosition(commandObject.stringArgs[0], commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2]);
/* 2452:     */              
/* 2455:2455 */              break;
/* 2456:     */            case 14: 
/* 2457:2457 */              CommandSetVolume(commandObject.stringArgs[0], commandObject.floatArgs[0]);
/* 2458:     */              
/* 2459:2459 */              break;
/* 2460:     */            case 15: 
/* 2461:2461 */              CommandSetPitch(commandObject.stringArgs[0], commandObject.floatArgs[0]);
/* 2462:     */              
/* 2463:2463 */              break;
/* 2464:     */            case 16: 
/* 2465:2465 */              CommandSetPriority(commandObject.stringArgs[0], commandObject.boolArgs[0]);
/* 2466:     */              
/* 2467:2467 */              break;
/* 2468:     */            case 17: 
/* 2469:2469 */              CommandSetLooping(commandObject.stringArgs[0], commandObject.boolArgs[0]);
/* 2470:     */              
/* 2471:2471 */              break;
/* 2472:     */            case 18: 
/* 2473:2473 */              CommandSetAttenuation(commandObject.stringArgs[0], commandObject.intArgs[0]);
/* 2474:     */              
/* 2475:2475 */              break;
/* 2476:     */            case 19: 
/* 2477:2477 */              CommandSetDistOrRoll(commandObject.stringArgs[0], commandObject.floatArgs[0]);
/* 2478:     */              
/* 2479:2479 */              break;
/* 2480:     */            case 20: 
/* 2481:2481 */              CommandChangeDopplerFactor(commandObject.floatArgs[0]);
/* 2482:     */              
/* 2483:2483 */              break;
/* 2484:     */            case 21: 
/* 2485:2485 */              CommandChangeDopplerVelocity(commandObject.floatArgs[0]);
/* 2486:     */              
/* 2487:2487 */              break;
/* 2488:     */            case 22: 
/* 2489:2489 */              CommandSetVelocity(commandObject.stringArgs[0], commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2]);
/* 2490:     */              
/* 2494:2494 */              break;
/* 2495:     */            case 23: 
/* 2496:2496 */              CommandSetListenerVelocity(commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2]);
/* 2497:     */              
/* 2501:2501 */              break;
/* 2502:     */            
/* 2507:     */            case 24: 
/* 2508:2508 */              this.sourcePlayList.add(commandObject);
/* 2509:2509 */              break;
/* 2510:     */            case 25: 
/* 2511:2511 */              this.sourcePlayList.add(commandObject);
/* 2512:2512 */              break;
/* 2513:     */            
/* 2514:     */            case 26: 
/* 2515:2515 */              CommandPause(commandObject.stringArgs[0]);
/* 2516:2516 */              break;
/* 2517:     */            case 27: 
/* 2518:2518 */              CommandStop(commandObject.stringArgs[0]);
/* 2519:2519 */              break;
/* 2520:     */            case 28: 
/* 2521:2521 */              CommandRewind(commandObject.stringArgs[0]);
/* 2522:2522 */              break;
/* 2523:     */            case 29: 
/* 2524:2524 */              CommandFlush(commandObject.stringArgs[0]);
/* 2525:2525 */              break;
/* 2526:     */            case 30: 
/* 2527:2527 */              CommandCull(commandObject.stringArgs[0]);
/* 2528:2528 */              break;
/* 2529:     */            case 31: 
/* 2530:2530 */              activations = true;
/* 2531:2531 */              CommandActivate(commandObject.stringArgs[0]);
/* 2532:2532 */              break;
/* 2533:     */            case 32: 
/* 2534:2534 */              CommandSetTemporary(commandObject.stringArgs[0], commandObject.boolArgs[0]);
/* 2535:     */              
/* 2536:2536 */              break;
/* 2537:     */            case 33: 
/* 2538:2538 */              CommandRemoveSource(commandObject.stringArgs[0]);
/* 2539:2539 */              break;
/* 2540:     */            case 34: 
/* 2541:2541 */              CommandMoveListener(commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2]);
/* 2542:     */              
/* 2544:2544 */              break;
/* 2545:     */            case 35: 
/* 2546:2546 */              CommandSetListenerPosition(commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2]);
/* 2547:     */              
/* 2550:2550 */              break;
/* 2551:     */            case 36: 
/* 2552:2552 */              CommandTurnListener(commandObject.floatArgs[0]);
/* 2553:2553 */              break;
/* 2554:     */            case 37: 
/* 2555:2555 */              CommandSetListenerAngle(commandObject.floatArgs[0]);
/* 2556:     */              
/* 2557:2557 */              break;
/* 2558:     */            case 38: 
/* 2559:2559 */              CommandSetListenerOrientation(commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2], commandObject.floatArgs[3], commandObject.floatArgs[4], commandObject.floatArgs[5]);
/* 2560:     */              
/* 2566:2566 */              break;
/* 2567:     */            case 39: 
/* 2568:2568 */              CommandSetMasterVolume(commandObject.floatArgs[0]);
/* 2569:     */              
/* 2570:2570 */              break;
/* 2571:     */            case 40: 
/* 2572:2572 */              CommandNewLibrary(commandObject.classArgs[0]);
/* 2573:     */            }
/* 2574:     */            
/* 2575:     */          }
/* 2576:     */        }
/* 2577:     */        
/* 2583:2583 */        if (activations) {
/* 2584:2584 */          this.soundLibrary.replaySources();
/* 2585:     */        }
/* 2586:     */        
/* 2589:2589 */        while ((this.sourcePlayList != null) && (this.sourcePlayList.size() > 0))
/* 2590:     */        {
/* 2592:2592 */          CommandObject commandObject = (CommandObject)this.sourcePlayList.remove(0);
/* 2593:2593 */          if (commandObject != null)
/* 2594:     */          {
/* 2596:2596 */            switch (commandObject.Command)
/* 2597:     */            {
/* 2598:     */            case 24: 
/* 2599:2599 */              CommandPlay(commandObject.stringArgs[0]);
/* 2600:2600 */              break;
/* 2601:     */            case 25: 
/* 2602:2602 */              CommandFeedRawAudioData(commandObject.stringArgs[0], commandObject.buffer);
/* 2603:     */            }
/* 2604:     */            
/* 2605:     */          }
/* 2606:     */        }
/* 2607:     */        
/* 2610:2610 */        return (this.commandQueue != null) && (this.commandQueue.size() > 0);
/* 2611:     */      }
/* 2612:     */      
/* 2615:2615 */      if (this.commandQueue == null) {
/* 2616:2616 */        return false;
/* 2617:     */      }
/* 2618:2618 */      this.commandQueue.add(newCommand);
/* 2619:     */      
/* 2621:2621 */      return true;
/* 2622:     */    }
/* 2623:     */  }
/* 2624:     */  
/* 2631:     */  public void removeTemporarySources()
/* 2632:     */  {
/* 2633:2633 */    synchronized (SoundSystemConfig.THREAD_SYNC)
/* 2634:     */    {
/* 2635:2635 */      if (this.soundLibrary != null) {
/* 2636:2636 */        this.soundLibrary.removeTemporarySources();
/* 2637:     */      }
/* 2638:     */    }
/* 2639:     */  }
/* 2640:     */  
/* 2645:     */  public boolean playing(String sourcename)
/* 2646:     */  {
/* 2647:2647 */    synchronized (SoundSystemConfig.THREAD_SYNC)
/* 2648:     */    {
/* 2649:2649 */      if (this.soundLibrary == null) {
/* 2650:2650 */        return false;
/* 2651:     */      }
/* 2652:2652 */      Source src = (Source)this.soundLibrary.getSources().get(sourcename);
/* 2653:     */      
/* 2654:2654 */      if (src == null) {
/* 2655:2655 */        return false;
/* 2656:     */      }
/* 2657:2657 */      return src.playing();
/* 2658:     */    }
/* 2659:     */  }
/* 2660:     */  
/* 2665:     */  public boolean playing()
/* 2666:     */  {
/* 2667:2667 */    synchronized (SoundSystemConfig.THREAD_SYNC)
/* 2668:     */    {
/* 2669:2669 */      if (this.soundLibrary == null) {
/* 2670:2670 */        return false;
/* 2671:     */      }
/* 2672:2672 */      HashMap<String, Source> sourceMap = this.soundLibrary.getSources();
/* 2673:2673 */      if (sourceMap == null) {
/* 2674:2674 */        return false;
/* 2675:     */      }
/* 2676:2676 */      Set<String> keys = sourceMap.keySet();
/* 2677:2677 */      Iterator<String> iter = keys.iterator();
/* 2678:     */      
/* 2681:2681 */      while (iter.hasNext())
/* 2682:     */      {
/* 2683:2683 */        String sourcename = (String)iter.next();
/* 2684:2684 */        Source source = (Source)sourceMap.get(sourcename);
/* 2685:2685 */        if ((source != null) && 
/* 2686:2686 */          (source.playing())) {
/* 2687:2687 */          return true;
/* 2688:     */        }
/* 2689:     */      }
/* 2690:2690 */      return false;
/* 2691:     */    }
/* 2692:     */  }
/* 2693:     */  
/* 2702:     */  private HashMap<String, Source> copySources(HashMap<String, Source> sourceMap)
/* 2703:     */  {
/* 2704:2704 */    Set<String> keys = sourceMap.keySet();
/* 2705:2705 */    Iterator<String> iter = keys.iterator();
/* 2706:     */    
/* 2710:2710 */    HashMap<String, Source> returnMap = new HashMap();
/* 2711:     */    
/* 2714:2714 */    while (iter.hasNext())
/* 2715:     */    {
/* 2716:2716 */      String sourcename = (String)iter.next();
/* 2717:2717 */      Source source = (Source)sourceMap.get(sourcename);
/* 2718:2718 */      if (source != null)
/* 2719:2719 */        returnMap.put(sourcename, new Source(source, null));
/* 2720:     */    }
/* 2721:2721 */    return returnMap;
/* 2722:     */  }
/* 2723:     */  
/* 2730:     */  public static boolean libraryCompatible(Class libraryClass)
/* 2731:     */  {
/* 2732:2732 */    SoundSystemLogger logger = SoundSystemConfig.getLogger();
/* 2733:     */    
/* 2734:2734 */    if (logger == null)
/* 2735:     */    {
/* 2736:2736 */      logger = new SoundSystemLogger();
/* 2737:2737 */      SoundSystemConfig.setLogger(logger);
/* 2738:     */    }
/* 2739:2739 */    logger.message("", 0);
/* 2740:2740 */    logger.message("Checking if " + SoundSystemConfig.getLibraryTitle(libraryClass) + " is compatible...", 0);
/* 2741:     */    
/* 2744:2744 */    boolean comp = SoundSystemConfig.libraryCompatible(libraryClass);
/* 2745:     */    
/* 2746:2746 */    if (comp) {
/* 2747:2747 */      logger.message("...yes", 1);
/* 2748:     */    } else {
/* 2749:2749 */      logger.message("...no", 1);
/* 2750:     */    }
/* 2751:2751 */    return comp;
/* 2752:     */  }
/* 2753:     */  
/* 2758:     */  public static Class currentLibrary()
/* 2759:     */  {
/* 2760:2760 */    return currentLibrary(false, null);
/* 2761:     */  }
/* 2762:     */  
/* 2767:     */  public static boolean initialized()
/* 2768:     */  {
/* 2769:2769 */    return initialized(false, false);
/* 2770:     */  }
/* 2771:     */  
/* 2776:     */  public static SoundSystemException getLastException()
/* 2777:     */  {
/* 2778:2778 */    return lastException(false, null);
/* 2779:     */  }
/* 2780:     */  
/* 2786:     */  public static void setException(SoundSystemException e)
/* 2787:     */  {
/* 2788:2788 */    lastException(true, e);
/* 2789:     */  }
/* 2790:     */  
/* 2797:     */  private static boolean initialized(boolean action, boolean value)
/* 2798:     */  {
/* 2799:2799 */    synchronized (SoundSystemConfig.THREAD_SYNC)
/* 2800:     */    {
/* 2801:2801 */      if (action == true)
/* 2802:2802 */        initialized = value;
/* 2803:2803 */      return initialized;
/* 2804:     */    }
/* 2805:     */  }
/* 2806:     */  
/* 2814:     */  private static Class currentLibrary(boolean action, Class value)
/* 2815:     */  {
/* 2816:2816 */    synchronized (SoundSystemConfig.THREAD_SYNC)
/* 2817:     */    {
/* 2818:2818 */      if (action == true)
/* 2819:2819 */        currentLibrary = value;
/* 2820:2820 */      return currentLibrary;
/* 2821:     */    }
/* 2822:     */  }
/* 2823:     */  
/* 2832:     */  private static SoundSystemException lastException(boolean action, SoundSystemException e)
/* 2833:     */  {
/* 2834:2834 */    synchronized (SoundSystemConfig.THREAD_SYNC)
/* 2835:     */    {
/* 2836:2836 */      if (action == true)
/* 2837:2837 */        lastException = e;
/* 2838:2838 */      return lastException;
/* 2839:     */    }
/* 2840:     */  }
/* 2841:     */  
/* 2845:     */  protected static void snooze(long milliseconds)
/* 2846:     */  {
/* 2847:     */    try
/* 2848:     */    {
/* 2849:2849 */      Thread.sleep(milliseconds);
/* 2850:     */    }
/* 2851:     */    catch (InterruptedException e) {}
/* 2852:     */  }
/* 2853:     */  
/* 2859:     */  protected void message(String message, int indent)
/* 2860:     */  {
/* 2861:2861 */    this.logger.message(message, indent);
/* 2862:     */  }
/* 2863:     */  
/* 2869:     */  protected void importantMessage(String message, int indent)
/* 2870:     */  {
/* 2871:2871 */    this.logger.importantMessage(message, indent);
/* 2872:     */  }
/* 2873:     */  
/* 2881:     */  protected boolean errorCheck(boolean error, String message, int indent)
/* 2882:     */  {
/* 2883:2883 */    return this.logger.errorCheck(error, this.className, message, indent);
/* 2884:     */  }
/* 2885:     */  
/* 2891:     */  protected void errorMessage(String message, int indent)
/* 2892:     */  {
/* 2893:2893 */    this.logger.errorMessage(this.className, message, indent);
/* 2894:     */  }
/* 2895:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.SoundSystem
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */