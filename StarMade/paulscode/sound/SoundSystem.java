/*      */ package paulscode.sound;
/*      */ 
/*      */ import java.net.URL;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import javax.sound.sampled.AudioFormat;
/*      */ 
/*      */ public class SoundSystem
/*      */ {
/*      */   private static final boolean GET = false;
/*      */   private static final boolean SET = true;
/*      */   private static final boolean XXX = false;
/*      */   protected SoundSystemLogger logger;
/*      */   protected Library soundLibrary;
/*      */   protected List<CommandObject> commandQueue;
/*      */   private List<CommandObject> sourcePlayList;
/*      */   protected CommandThread commandThread;
/*      */   public Random randomNumberGenerator;
/*  115 */   protected String className = "SoundSystem";
/*      */ 
/*  120 */   private static Class currentLibrary = null;
/*      */ 
/*  125 */   private static boolean initialized = false;
/*      */ 
/*  130 */   private static SoundSystemException lastException = null;
/*      */ 
/*      */   public SoundSystem()
/*      */   {
/*  142 */     this.logger = SoundSystemConfig.getLogger();
/*      */ 
/*  144 */     if (this.logger == null)
/*      */     {
/*  146 */       this.logger = new SoundSystemLogger();
/*  147 */       SoundSystemConfig.setLogger(this.logger);
/*      */     }
/*      */ 
/*  150 */     linkDefaultLibrariesAndCodecs();
/*      */ 
/*  152 */     LinkedList libraries = SoundSystemConfig.getLibraries();
/*      */ 
/*  154 */     if (libraries != null)
/*      */     {
/*  156 */       ListIterator i = libraries.listIterator();
/*      */ 
/*  158 */       while (i.hasNext())
/*      */       {
/*  160 */         Class c = (Class)i.next();
/*      */         try
/*      */         {
/*  163 */           init(c);
/*  164 */           return;
/*      */         }
/*      */         catch (SoundSystemException sse)
/*      */         {
/*  168 */           this.logger.printExceptionMessage(sse, 1);
/*      */         }
/*      */       }
/*      */     }
/*      */     try
/*      */     {
/*  174 */       init(Library.class);
/*  175 */       return;
/*      */     }
/*      */     catch (SoundSystemException sse)
/*      */     {
/*  179 */       this.logger.printExceptionMessage(sse, 1);
/*      */     }
/*      */   }
/*      */ 
/*      */   public SoundSystem(Class libraryClass)
/*      */     throws SoundSystemException
/*      */   {
/*  192 */     this.logger = SoundSystemConfig.getLogger();
/*      */ 
/*  194 */     if (this.logger == null)
/*      */     {
/*  196 */       this.logger = new SoundSystemLogger();
/*  197 */       SoundSystemConfig.setLogger(this.logger);
/*      */     }
/*  199 */     linkDefaultLibrariesAndCodecs();
/*      */ 
/*  201 */     init(libraryClass);
/*      */   }
/*      */ 
/*      */   protected void linkDefaultLibrariesAndCodecs()
/*      */   {
/*      */   }
/*      */ 
/*      */   protected void init(Class libraryClass)
/*      */     throws SoundSystemException
/*      */   {
/*  225 */     message("", 0);
/*  226 */     message("Starting up " + this.className + "...", 0);
/*      */ 
/*  229 */     this.randomNumberGenerator = new Random();
/*      */ 
/*  231 */     this.commandQueue = new LinkedList();
/*      */ 
/*  233 */     this.sourcePlayList = new LinkedList();
/*      */ 
/*  236 */     this.commandThread = new CommandThread(this);
/*  237 */     this.commandThread.start();
/*      */ 
/*  239 */     snooze(200L);
/*      */ 
/*  241 */     newLibrary(libraryClass);
/*  242 */     message("", 0);
/*      */   }
/*      */ 
/*      */   public void cleanup()
/*      */   {
/*  251 */     boolean killException = false;
/*  252 */     message("", 0);
/*  253 */     message(this.className + " shutting down...", 0);
/*      */     try
/*      */     {
/*  258 */       this.commandThread.kill();
/*  259 */       this.commandThread.interrupt();
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*  263 */       killException = true;
/*      */     }
/*      */ 
/*  266 */     if (!killException)
/*      */     {
/*  269 */       for (int i = 0; i < 50; i++)
/*      */       {
/*  271 */         if (!this.commandThread.alive())
/*      */           break;
/*  273 */         snooze(100L);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  278 */     if ((killException) || (this.commandThread.alive()))
/*      */     {
/*  280 */       errorMessage("Command thread did not die!", 0);
/*  281 */       message("Ignoring errors... continuing clean-up.", 0);
/*      */     }
/*      */ 
/*  284 */     initialized(true, false);
/*  285 */     currentLibrary(true, null);
/*      */     try
/*      */     {
/*  289 */       if (this.soundLibrary != null)
/*  290 */         this.soundLibrary.cleanup();
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*  294 */       errorMessage("Problem during Library.cleanup()!", 0);
/*  295 */       message("Ignoring errors... continuing clean-up.", 0);
/*      */     }
/*      */ 
/*      */     try
/*      */     {
/*  301 */       if (this.commandQueue != null)
/*  302 */         this.commandQueue.clear();
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*  306 */       errorMessage("Unable to clear the command queue!", 0);
/*  307 */       message("Ignoring errors... continuing clean-up.", 0);
/*      */     }
/*      */ 
/*      */     try
/*      */     {
/*  313 */       if (this.sourcePlayList != null)
/*  314 */         this.sourcePlayList.clear();
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*  318 */       errorMessage("Unable to clear the source management list!", 0);
/*  319 */       message("Ignoring errors... continuing clean-up.", 0);
/*      */     }
/*      */ 
/*  323 */     this.randomNumberGenerator = null;
/*  324 */     this.soundLibrary = null;
/*  325 */     this.commandQueue = null;
/*  326 */     this.sourcePlayList = null;
/*  327 */     this.commandThread = null;
/*      */ 
/*  329 */     importantMessage("Author: Paul Lamb, www.paulscode.com", 1);
/*  330 */     message("", 0);
/*      */   }
/*      */ 
/*      */   public void interruptCommandThread()
/*      */   {
/*  342 */     if (this.commandThread == null)
/*      */     {
/*  344 */       errorMessage("Command Thread null in method 'interruptCommandThread'", 0);
/*      */ 
/*  346 */       return;
/*      */     }
/*      */ 
/*  349 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void loadSound(String filename)
/*      */   {
/*  363 */     CommandQueue(new CommandObject(2, new FilenameURL(filename)));
/*      */ 
/*  366 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void loadSound(URL url, String identifier)
/*      */   {
/*  380 */     CommandQueue(new CommandObject(2, new FilenameURL(url, identifier)));
/*      */ 
/*  383 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void loadSound(byte[] data, AudioFormat format, String identifier)
/*      */   {
/*  397 */     CommandQueue(new CommandObject(3, identifier, new SoundBuffer(data, format)));
/*      */ 
/*  401 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void unloadSound(String filename)
/*      */   {
/*  417 */     CommandQueue(new CommandObject(4, filename));
/*      */ 
/*  419 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void queueSound(String sourcename, String filename)
/*      */   {
/*  437 */     CommandQueue(new CommandObject(5, sourcename, new FilenameURL(filename)));
/*      */ 
/*  440 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void queueSound(String sourcename, URL url, String identifier)
/*      */   {
/*  457 */     CommandQueue(new CommandObject(5, sourcename, new FilenameURL(url, identifier)));
/*      */ 
/*  460 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void dequeueSound(String sourcename, String filename)
/*      */   {
/*  473 */     CommandQueue(new CommandObject(6, sourcename, filename));
/*      */ 
/*  476 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void fadeOut(String sourcename, String filename, long milis)
/*      */   {
/*  499 */     FilenameURL fu = null;
/*  500 */     if (filename != null) {
/*  501 */       fu = new FilenameURL(filename);
/*      */     }
/*  503 */     CommandQueue(new CommandObject(7, sourcename, fu, milis));
/*      */ 
/*  506 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void fadeOut(String sourcename, URL url, String identifier, long milis)
/*      */   {
/*  529 */     FilenameURL fu = null;
/*  530 */     if ((url != null) && (identifier != null)) {
/*  531 */       fu = new FilenameURL(url, identifier);
/*      */     }
/*  533 */     CommandQueue(new CommandObject(7, sourcename, fu, milis));
/*      */ 
/*  536 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void fadeOutIn(String sourcename, String filename, long milisOut, long milisIn)
/*      */   {
/*  562 */     CommandQueue(new CommandObject(8, sourcename, new FilenameURL(filename), milisOut, milisIn));
/*      */ 
/*  567 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void fadeOutIn(String sourcename, URL url, String identifier, long milisOut, long milisIn)
/*      */   {
/*  592 */     CommandQueue(new CommandObject(8, sourcename, new FilenameURL(url, identifier), milisOut, milisIn));
/*      */ 
/*  597 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void checkFadeVolumes()
/*      */   {
/*  614 */     CommandQueue(new CommandObject(9));
/*      */ 
/*  616 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void backgroundMusic(String sourcename, String filename, boolean toLoop)
/*      */   {
/*  634 */     CommandQueue(new CommandObject(12, true, true, toLoop, sourcename, new FilenameURL(filename), 0.0F, 0.0F, 0.0F, 0, 0.0F, false));
/*      */ 
/*  638 */     CommandQueue(new CommandObject(24, sourcename));
/*      */ 
/*  640 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void backgroundMusic(String sourcename, URL url, String identifier, boolean toLoop)
/*      */   {
/*  657 */     CommandQueue(new CommandObject(12, true, true, toLoop, sourcename, new FilenameURL(url, identifier), 0.0F, 0.0F, 0.0F, 0, 0.0F, false));
/*      */ 
/*  663 */     CommandQueue(new CommandObject(24, sourcename));
/*      */ 
/*  665 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void newSource(boolean priority, String sourcename, String filename, boolean toLoop, float x, float y, float z, int attmodel, float distOrRoll)
/*      */   {
/*  686 */     CommandQueue(new CommandObject(10, priority, false, toLoop, sourcename, new FilenameURL(filename), x, y, z, attmodel, distOrRoll));
/*      */ 
/*  690 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void newSource(boolean priority, String sourcename, URL url, String identifier, boolean toLoop, float x, float y, float z, int attmodel, float distOrRoll)
/*      */   {
/*  712 */     CommandQueue(new CommandObject(10, priority, false, toLoop, sourcename, new FilenameURL(url, identifier), x, y, z, attmodel, distOrRoll));
/*      */ 
/*  717 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void newStreamingSource(boolean priority, String sourcename, String filename, boolean toLoop, float x, float y, float z, int attmodel, float distOrRoll)
/*      */   {
/*  741 */     CommandQueue(new CommandObject(10, priority, true, toLoop, sourcename, new FilenameURL(filename), x, y, z, attmodel, distOrRoll));
/*      */ 
/*  745 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void newStreamingSource(boolean priority, String sourcename, URL url, String identifier, boolean toLoop, float x, float y, float z, int attmodel, float distOrRoll)
/*      */   {
/*  768 */     CommandQueue(new CommandObject(10, priority, true, toLoop, sourcename, new FilenameURL(url, identifier), x, y, z, attmodel, distOrRoll));
/*      */ 
/*  772 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void rawDataStream(AudioFormat audioFormat, boolean priority, String sourcename, float x, float y, float z, int attModel, float distOrRoll)
/*      */   {
/*  793 */     CommandQueue(new CommandObject(11, audioFormat, priority, sourcename, x, y, z, attModel, distOrRoll));
/*      */ 
/*  796 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public String quickPlay(boolean priority, String filename, boolean toLoop, float x, float y, float z, int attmodel, float distOrRoll)
/*      */   {
/*  819 */     String sourcename = "Source_" + this.randomNumberGenerator.nextInt() + "_" + this.randomNumberGenerator.nextInt();
/*      */ 
/*  824 */     CommandQueue(new CommandObject(12, priority, false, toLoop, sourcename, new FilenameURL(filename), x, y, z, attmodel, distOrRoll, true));
/*      */ 
/*  828 */     CommandQueue(new CommandObject(24, sourcename));
/*      */ 
/*  830 */     this.commandThread.interrupt();
/*      */ 
/*  833 */     return sourcename;
/*      */   }
/*      */ 
/*      */   public String quickPlay(boolean priority, URL url, String identifier, boolean toLoop, float x, float y, float z, int attmodel, float distOrRoll)
/*      */   {
/*  857 */     String sourcename = "Source_" + this.randomNumberGenerator.nextInt() + "_" + this.randomNumberGenerator.nextInt();
/*      */ 
/*  862 */     CommandQueue(new CommandObject(12, priority, false, toLoop, sourcename, new FilenameURL(url, identifier), x, y, z, attmodel, distOrRoll, true));
/*      */ 
/*  867 */     CommandQueue(new CommandObject(24, sourcename));
/*      */ 
/*  869 */     this.commandThread.interrupt();
/*      */ 
/*  872 */     return sourcename;
/*      */   }
/*      */ 
/*      */   public String quickStream(boolean priority, String filename, boolean toLoop, float x, float y, float z, int attmodel, float distOrRoll)
/*      */   {
/*  900 */     String sourcename = "Source_" + this.randomNumberGenerator.nextInt() + "_" + this.randomNumberGenerator.nextInt();
/*      */ 
/*  905 */     CommandQueue(new CommandObject(12, priority, true, toLoop, sourcename, new FilenameURL(filename), x, y, z, attmodel, distOrRoll, true));
/*      */ 
/*  909 */     CommandQueue(new CommandObject(24, sourcename));
/*      */ 
/*  911 */     this.commandThread.interrupt();
/*      */ 
/*  914 */     return sourcename;
/*      */   }
/*      */ 
/*      */   public String quickStream(boolean priority, URL url, String identifier, boolean toLoop, float x, float y, float z, int attmodel, float distOrRoll)
/*      */   {
/*  940 */     String sourcename = "Source_" + this.randomNumberGenerator.nextInt() + "_" + this.randomNumberGenerator.nextInt();
/*      */ 
/*  945 */     CommandQueue(new CommandObject(12, priority, true, toLoop, sourcename, new FilenameURL(url, identifier), x, y, z, attmodel, distOrRoll, true));
/*      */ 
/*  950 */     CommandQueue(new CommandObject(24, sourcename));
/*      */ 
/*  952 */     this.commandThread.interrupt();
/*      */ 
/*  955 */     return sourcename;
/*      */   }
/*      */ 
/*      */   public void setPosition(String sourcename, float x, float y, float z)
/*      */   {
/*  967 */     CommandQueue(new CommandObject(13, sourcename, x, y, z));
/*      */ 
/*  969 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void setVolume(String sourcename, float value)
/*      */   {
/*  978 */     CommandQueue(new CommandObject(14, sourcename, value));
/*      */ 
/*  980 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public float getVolume(String sourcename)
/*      */   {
/*  991 */     synchronized (SoundSystemConfig.THREAD_SYNC)
/*      */     {
/*  993 */       if (this.soundLibrary != null) {
/*  994 */         return this.soundLibrary.getVolume(sourcename);
/*      */       }
/*  996 */       return 0.0F;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setPitch(String sourcename, float value)
/*      */   {
/* 1007 */     CommandQueue(new CommandObject(15, sourcename, value));
/*      */ 
/* 1009 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public float getPitch(String sourcename)
/*      */   {
/* 1019 */     if (this.soundLibrary != null) {
/* 1020 */       return this.soundLibrary.getPitch(sourcename);
/*      */     }
/* 1022 */     return 1.0F;
/*      */   }
/*      */ 
/*      */   public void setPriority(String sourcename, boolean pri)
/*      */   {
/* 1033 */     CommandQueue(new CommandObject(16, sourcename, pri));
/*      */ 
/* 1035 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void setLooping(String sourcename, boolean lp)
/*      */   {
/* 1044 */     CommandQueue(new CommandObject(17, sourcename, lp));
/*      */ 
/* 1046 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void setAttenuation(String sourcename, int model)
/*      */   {
/* 1057 */     CommandQueue(new CommandObject(18, sourcename, model));
/*      */ 
/* 1059 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void setDistOrRoll(String sourcename, float dr)
/*      */   {
/* 1070 */     CommandQueue(new CommandObject(19, sourcename, dr));
/*      */ 
/* 1072 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void changeDopplerFactor(float dopplerFactor)
/*      */   {
/* 1083 */     CommandQueue(new CommandObject(20, dopplerFactor));
/*      */ 
/* 1085 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void changeDopplerVelocity(float dopplerVelocity)
/*      */   {
/* 1096 */     CommandQueue(new CommandObject(21, dopplerVelocity));
/*      */ 
/* 1098 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void setVelocity(String sourcename, float x, float y, float z)
/*      */   {
/* 1112 */     CommandQueue(new CommandObject(22, sourcename, x, y, z));
/*      */ 
/* 1114 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void setListenerVelocity(float x, float y, float z)
/*      */   {
/* 1127 */     CommandQueue(new CommandObject(23, x, y, z));
/*      */ 
/* 1129 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public float millisecondsPlayed(String sourcename)
/*      */   {
/* 1138 */     synchronized (SoundSystemConfig.THREAD_SYNC)
/*      */     {
/* 1140 */       return this.soundLibrary.millisecondsPlayed(sourcename);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void feedRawAudioData(String sourcename, byte[] buffer)
/*      */   {
/* 1159 */     CommandQueue(new CommandObject(25, sourcename, buffer));
/*      */ 
/* 1161 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void play(String sourcename)
/*      */   {
/* 1169 */     CommandQueue(new CommandObject(24, sourcename));
/* 1170 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void pause(String sourcename)
/*      */   {
/* 1178 */     CommandQueue(new CommandObject(26, sourcename));
/* 1179 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void stop(String sourcename)
/*      */   {
/* 1187 */     CommandQueue(new CommandObject(27, sourcename));
/* 1188 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void rewind(String sourcename)
/*      */   {
/* 1196 */     CommandQueue(new CommandObject(28, sourcename));
/* 1197 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void flush(String sourcename)
/*      */   {
/* 1205 */     CommandQueue(new CommandObject(29, sourcename));
/* 1206 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void cull(String sourcename)
/*      */   {
/* 1216 */     CommandQueue(new CommandObject(30, sourcename));
/* 1217 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void activate(String sourcename)
/*      */   {
/* 1227 */     CommandQueue(new CommandObject(31, sourcename));
/* 1228 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void setTemporary(String sourcename, boolean temporary)
/*      */   {
/* 1245 */     CommandQueue(new CommandObject(32, sourcename, temporary));
/*      */ 
/* 1247 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void removeSource(String sourcename)
/*      */   {
/* 1256 */     CommandQueue(new CommandObject(33, sourcename));
/*      */ 
/* 1258 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void moveListener(float x, float y, float z)
/*      */   {
/* 1268 */     CommandQueue(new CommandObject(34, x, y, z));
/*      */ 
/* 1270 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void setListenerPosition(float x, float y, float z)
/*      */   {
/* 1280 */     CommandQueue(new CommandObject(35, x, y, z));
/*      */ 
/* 1282 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void turnListener(float angle)
/*      */   {
/* 1291 */     CommandQueue(new CommandObject(36, angle));
/*      */ 
/* 1293 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void setListenerAngle(float angle)
/*      */   {
/* 1301 */     CommandQueue(new CommandObject(37, angle));
/*      */ 
/* 1303 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void setListenerOrientation(float lookX, float lookY, float lookZ, float upX, float upY, float upZ)
/*      */   {
/* 1317 */     CommandQueue(new CommandObject(38, lookX, lookY, lookZ, upX, upY, upZ));
/*      */ 
/* 1319 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void setMasterVolume(float value)
/*      */   {
/* 1328 */     CommandQueue(new CommandObject(39, value));
/*      */ 
/* 1330 */     this.commandThread.interrupt();
/*      */   }
/*      */ 
/*      */   public float getMasterVolume()
/*      */   {
/* 1339 */     return SoundSystemConfig.getMasterGain();
/*      */   }
/*      */ 
/*      */   public ListenerData getListenerData()
/*      */   {
/* 1349 */     synchronized (SoundSystemConfig.THREAD_SYNC)
/*      */     {
/* 1351 */       return this.soundLibrary.getListenerData();
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean switchLibrary(Class libraryClass)
/*      */     throws SoundSystemException
/*      */   {
/* 1364 */     synchronized (SoundSystemConfig.THREAD_SYNC)
/*      */     {
/* 1366 */       initialized(true, false);
/*      */ 
/* 1368 */       HashMap sourceMap = null;
/* 1369 */       ListenerData listenerData = null;
/*      */ 
/* 1371 */       boolean wasMidiChannel = false;
/* 1372 */       MidiChannel midiChannel = null;
/* 1373 */       FilenameURL midiFilenameURL = null;
/* 1374 */       String midiSourcename = "";
/* 1375 */       boolean midiToLoop = true;
/*      */ 
/* 1377 */       if (this.soundLibrary != null)
/*      */       {
/* 1379 */         currentLibrary(true, null);
/* 1380 */         sourceMap = copySources(this.soundLibrary.getSources());
/* 1381 */         listenerData = this.soundLibrary.getListenerData();
/* 1382 */         midiChannel = this.soundLibrary.getMidiChannel();
/* 1383 */         if (midiChannel != null)
/*      */         {
/* 1385 */           wasMidiChannel = true;
/* 1386 */           midiToLoop = midiChannel.getLooping();
/* 1387 */           midiSourcename = midiChannel.getSourcename();
/* 1388 */           midiFilenameURL = midiChannel.getFilenameURL();
/*      */         }
/*      */ 
/* 1391 */         this.soundLibrary.cleanup();
/* 1392 */         this.soundLibrary = null;
/*      */       }
/* 1394 */       message("", 0);
/* 1395 */       message("Switching to " + SoundSystemConfig.getLibraryTitle(libraryClass), 0);
/*      */ 
/* 1397 */       message("(" + SoundSystemConfig.getLibraryDescription(libraryClass) + ")", 1);
/*      */       try
/*      */       {
/* 1402 */         this.soundLibrary = ((Library)libraryClass.newInstance());
/*      */       }
/*      */       catch (InstantiationException ie)
/*      */       {
/* 1406 */         errorMessage("The specified library did not load properly", 1);
/*      */       }
/*      */       catch (IllegalAccessException iae)
/*      */       {
/* 1410 */         errorMessage("The specified library did not load properly", 1);
/*      */       }
/*      */       catch (ExceptionInInitializerError eiie)
/*      */       {
/* 1414 */         errorMessage("The specified library did not load properly", 1);
/*      */       }
/*      */       catch (SecurityException se)
/*      */       {
/* 1418 */         errorMessage("The specified library did not load properly", 1);
/*      */       }
/*      */ 
/* 1421 */       if (errorCheck(this.soundLibrary == null, "Library null after initialization in method 'switchLibrary'", 1))
/*      */       {
/* 1424 */         SoundSystemException sse = new SoundSystemException(this.className + " did not load properly.  " + "Library was null after initialization.", 4);
/*      */ 
/* 1428 */         lastException(true, sse);
/* 1429 */         initialized(true, true);
/* 1430 */         throw sse;
/*      */       }
/*      */ 
/*      */       try
/*      */       {
/* 1435 */         this.soundLibrary.init();
/*      */       }
/*      */       catch (SoundSystemException sse)
/*      */       {
/* 1439 */         lastException(true, sse);
/* 1440 */         initialized(true, true);
/* 1441 */         throw sse;
/*      */       }
/*      */ 
/* 1444 */       this.soundLibrary.setListenerData(listenerData);
/* 1445 */       if (wasMidiChannel)
/*      */       {
/* 1447 */         if (midiChannel != null)
/* 1448 */           midiChannel.cleanup();
/* 1449 */         midiChannel = new MidiChannel(midiToLoop, midiSourcename, midiFilenameURL);
/*      */ 
/* 1451 */         this.soundLibrary.setMidiChannel(midiChannel);
/*      */       }
/* 1453 */       this.soundLibrary.copySources(sourceMap);
/*      */ 
/* 1455 */       message("", 0);
/*      */ 
/* 1457 */       lastException(true, null);
/* 1458 */       initialized(true, true);
/*      */ 
/* 1460 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean newLibrary(Class libraryClass)
/*      */     throws SoundSystemException
/*      */   {
/* 1473 */     initialized(true, false);
/*      */ 
/* 1475 */     CommandQueue(new CommandObject(40, libraryClass));
/*      */ 
/* 1477 */     this.commandThread.interrupt();
/*      */ 
/* 1479 */     for (int x = 0; (!initialized(false, false)) && (x < 100); x++)
/*      */     {
/* 1481 */       snooze(400L);
/* 1482 */       this.commandThread.interrupt();
/*      */     }
/*      */ 
/* 1485 */     if (!initialized(false, false))
/*      */     {
/* 1487 */       SoundSystemException sse = new SoundSystemException(this.className + " did not load after 30 seconds.", 4);
/*      */ 
/* 1491 */       lastException(true, sse);
/* 1492 */       throw sse;
/*      */     }
/*      */ 
/* 1496 */     SoundSystemException sse = lastException(false, null);
/* 1497 */     if (sse != null) {
/* 1498 */       throw sse;
/*      */     }
/* 1500 */     return true;
/*      */   }
/*      */ 
/*      */   private void CommandNewLibrary(Class libraryClass)
/*      */   {
/* 1513 */     initialized(true, false);
/*      */ 
/* 1515 */     String headerMessage = "Initializing ";
/* 1516 */     if (this.soundLibrary != null)
/*      */     {
/* 1518 */       currentLibrary(true, null);
/*      */ 
/* 1520 */       headerMessage = "Switching to ";
/* 1521 */       this.soundLibrary.cleanup();
/* 1522 */       this.soundLibrary = null;
/*      */     }
/* 1524 */     message(headerMessage + SoundSystemConfig.getLibraryTitle(libraryClass), 0);
/*      */ 
/* 1526 */     message("(" + SoundSystemConfig.getLibraryDescription(libraryClass) + ")", 1);
/*      */     try
/*      */     {
/* 1531 */       this.soundLibrary = ((Library)libraryClass.newInstance());
/*      */     }
/*      */     catch (InstantiationException ie)
/*      */     {
/* 1535 */       errorMessage("The specified library did not load properly", 1);
/*      */     }
/*      */     catch (IllegalAccessException iae)
/*      */     {
/* 1539 */       errorMessage("The specified library did not load properly", 1);
/*      */     }
/*      */     catch (ExceptionInInitializerError eiie)
/*      */     {
/* 1543 */       errorMessage("The specified library did not load properly", 1);
/*      */     }
/*      */     catch (SecurityException se)
/*      */     {
/* 1547 */       errorMessage("The specified library did not load properly", 1);
/*      */     }
/*      */ 
/* 1550 */     if (errorCheck(this.soundLibrary == null, "Library null after initialization in method 'newLibrary'", 1))
/*      */     {
/* 1553 */       lastException(true, new SoundSystemException(this.className + " did not load properly.  " + "Library was null after initialization.", 4));
/*      */ 
/* 1557 */       importantMessage("Switching to silent mode", 1);
/*      */       try
/*      */       {
/* 1561 */         this.soundLibrary = new Library();
/*      */       }
/*      */       catch (SoundSystemException sse)
/*      */       {
/* 1565 */         lastException(true, new SoundSystemException("Silent mode did not load properly.  Library was null after initialization.", 4));
/*      */ 
/* 1569 */         initialized(true, true);
/* 1570 */         return;
/*      */       }
/*      */     }
/*      */ 
/*      */     try
/*      */     {
/* 1576 */       this.soundLibrary.init();
/*      */     }
/*      */     catch (SoundSystemException sse)
/*      */     {
/* 1580 */       lastException(true, sse);
/* 1581 */       initialized(true, true);
/* 1582 */       return;
/*      */     }
/*      */ 
/* 1585 */     lastException(true, null);
/* 1586 */     initialized(true, true);
/*      */   }
/*      */ 
/*      */   private void CommandInitialize()
/*      */   {
/*      */     try
/*      */     {
/* 1599 */       if (errorCheck(this.soundLibrary == null, "Library null after initialization in method 'CommandInitialize'", 1))
/*      */       {
/* 1603 */         SoundSystemException sse = new SoundSystemException(this.className + " did not load properly.  " + "Library was null after initialization.", 4);
/*      */ 
/* 1607 */         lastException(true, sse);
/* 1608 */         throw sse;
/*      */       }
/* 1610 */       this.soundLibrary.init();
/*      */     }
/*      */     catch (SoundSystemException sse)
/*      */     {
/* 1614 */       lastException(true, sse);
/* 1615 */       initialized(true, true);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void CommandLoadSound(FilenameURL filenameURL)
/*      */   {
/* 1626 */     if (this.soundLibrary != null)
/* 1627 */       this.soundLibrary.loadSound(filenameURL);
/*      */     else
/* 1629 */       errorMessage("Variable 'soundLibrary' null in method 'CommandLoadSound'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandLoadSound(SoundBuffer buffer, String identifier)
/*      */   {
/* 1643 */     if (this.soundLibrary != null)
/* 1644 */       this.soundLibrary.loadSound(buffer, identifier);
/*      */     else
/* 1646 */       errorMessage("Variable 'soundLibrary' null in method 'CommandLoadSound'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandUnloadSound(String filename)
/*      */   {
/* 1657 */     if (this.soundLibrary != null)
/* 1658 */       this.soundLibrary.unloadSound(filename);
/*      */     else
/* 1660 */       errorMessage("Variable 'soundLibrary' null in method 'CommandLoadSound'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandQueueSound(String sourcename, FilenameURL filenameURL)
/*      */   {
/* 1675 */     if (this.soundLibrary != null)
/* 1676 */       this.soundLibrary.queueSound(sourcename, filenameURL);
/*      */     else
/* 1678 */       errorMessage("Variable 'soundLibrary' null in method 'CommandQueueSound'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandDequeueSound(String sourcename, String filename)
/*      */   {
/* 1692 */     if (this.soundLibrary != null)
/* 1693 */       this.soundLibrary.dequeueSound(sourcename, filename);
/*      */     else
/* 1695 */       errorMessage("Variable 'soundLibrary' null in method 'CommandDequeueSound'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandFadeOut(String sourcename, FilenameURL filenameURL, long milis)
/*      */   {
/* 1716 */     if (this.soundLibrary != null)
/* 1717 */       this.soundLibrary.fadeOut(sourcename, filenameURL, milis);
/*      */     else
/* 1719 */       errorMessage("Variable 'soundLibrary' null in method 'CommandFadeOut'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandFadeOutIn(String sourcename, FilenameURL filenameURL, long milisOut, long milisIn)
/*      */   {
/* 1741 */     if (this.soundLibrary != null) {
/* 1742 */       this.soundLibrary.fadeOutIn(sourcename, filenameURL, milisOut, milisIn);
/*      */     }
/*      */     else
/* 1745 */       errorMessage("Variable 'soundLibrary' null in method 'CommandFadeOutIn'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandCheckFadeVolumes()
/*      */   {
/* 1763 */     if (this.soundLibrary != null)
/* 1764 */       this.soundLibrary.checkFadeVolumes();
/*      */     else
/* 1766 */       errorMessage("Variable 'soundLibrary' null in method 'CommandCheckFadeVolumes'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandNewSource(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float x, float y, float z, int attModel, float distORroll)
/*      */   {
/* 1790 */     if (this.soundLibrary != null)
/*      */     {
/* 1792 */       if ((filenameURL.getFilename().matches(SoundSystemConfig.EXTENSION_MIDI)) && (!SoundSystemConfig.midiCodec()))
/*      */       {
/* 1796 */         this.soundLibrary.loadMidi(toLoop, sourcename, filenameURL);
/*      */       }
/*      */       else
/*      */       {
/* 1800 */         this.soundLibrary.newSource(priority, toStream, toLoop, sourcename, filenameURL, x, y, z, attModel, distORroll);
/*      */       }
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 1806 */       errorMessage("Variable 'soundLibrary' null in method 'CommandNewSource'", 0);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void CommandRawDataStream(AudioFormat audioFormat, boolean priority, String sourcename, float x, float y, float z, int attModel, float distOrRoll)
/*      */   {
/* 1827 */     if (this.soundLibrary != null) {
/* 1828 */       this.soundLibrary.rawDataStream(audioFormat, priority, sourcename, x, y, z, attModel, distOrRoll);
/*      */     }
/*      */     else
/* 1831 */       errorMessage("Variable 'soundLibrary' null in method 'CommandRawDataStream'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandQuickPlay(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float x, float y, float z, int attModel, float distORroll, boolean temporary)
/*      */   {
/* 1857 */     if (this.soundLibrary != null)
/*      */     {
/* 1859 */       if ((filenameURL.getFilename().matches(SoundSystemConfig.EXTENSION_MIDI)) && (!SoundSystemConfig.midiCodec()))
/*      */       {
/* 1862 */         this.soundLibrary.loadMidi(toLoop, sourcename, filenameURL);
/*      */       }
/*      */       else
/*      */       {
/* 1866 */         this.soundLibrary.quickPlay(priority, toStream, toLoop, sourcename, filenameURL, x, y, z, attModel, distORroll, temporary);
/*      */       }
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 1872 */       errorMessage("Variable 'soundLibrary' null in method 'CommandQuickPlay'", 0);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void CommandSetPosition(String sourcename, float x, float y, float z)
/*      */   {
/* 1887 */     if (this.soundLibrary != null)
/* 1888 */       this.soundLibrary.setPosition(sourcename, x, y, z);
/*      */     else
/* 1890 */       errorMessage("Variable 'soundLibrary' null in method 'CommandMoveSource'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandSetVolume(String sourcename, float value)
/*      */   {
/* 1902 */     if (this.soundLibrary != null)
/* 1903 */       this.soundLibrary.setVolume(sourcename, value);
/*      */     else
/* 1905 */       errorMessage("Variable 'soundLibrary' null in method 'CommandSetVolume'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandSetPitch(String sourcename, float value)
/*      */   {
/* 1917 */     if (this.soundLibrary != null)
/* 1918 */       this.soundLibrary.setPitch(sourcename, value);
/*      */     else
/* 1920 */       errorMessage("Variable 'soundLibrary' null in method 'CommandSetPitch'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandSetPriority(String sourcename, boolean pri)
/*      */   {
/* 1933 */     if (this.soundLibrary != null)
/* 1934 */       this.soundLibrary.setPriority(sourcename, pri);
/*      */     else
/* 1936 */       errorMessage("Variable 'soundLibrary' null in method 'CommandSetPriority'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandSetLooping(String sourcename, boolean lp)
/*      */   {
/* 1948 */     if (this.soundLibrary != null)
/* 1949 */       this.soundLibrary.setLooping(sourcename, lp);
/*      */     else
/* 1951 */       errorMessage("Variable 'soundLibrary' null in method 'CommandSetLooping'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandSetAttenuation(String sourcename, int model)
/*      */   {
/* 1965 */     if (this.soundLibrary != null)
/* 1966 */       this.soundLibrary.setAttenuation(sourcename, model);
/*      */     else
/* 1968 */       errorMessage("Variable 'soundLibrary' null in method 'CommandSetAttenuation'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandSetDistOrRoll(String sourcename, float dr)
/*      */   {
/* 1981 */     if (this.soundLibrary != null)
/* 1982 */       this.soundLibrary.setDistOrRoll(sourcename, dr);
/*      */     else
/* 1984 */       errorMessage("Variable 'soundLibrary' null in method 'CommandSetDistOrRoll'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandChangeDopplerFactor(float dopplerFactor)
/*      */   {
/* 1998 */     if (this.soundLibrary != null)
/*      */     {
/* 2000 */       SoundSystemConfig.setDopplerFactor(dopplerFactor);
/* 2001 */       this.soundLibrary.dopplerChanged();
/*      */     }
/*      */     else {
/* 2004 */       errorMessage("Variable 'soundLibrary' null in method 'CommandSetDopplerFactor'", 0);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void CommandChangeDopplerVelocity(float dopplerVelocity)
/*      */   {
/* 2018 */     if (this.soundLibrary != null)
/*      */     {
/* 2020 */       SoundSystemConfig.setDopplerVelocity(dopplerVelocity);
/* 2021 */       this.soundLibrary.dopplerChanged();
/*      */     }
/*      */     else {
/* 2024 */       errorMessage("Variable 'soundLibrary' null in method 'CommandSetDopplerFactor'", 0);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void CommandSetVelocity(String sourcename, float x, float y, float z)
/*      */   {
/* 2041 */     if (this.soundLibrary != null)
/* 2042 */       this.soundLibrary.setVelocity(sourcename, x, y, z);
/*      */     else
/* 2044 */       errorMessage("Variable 'soundLibrary' null in method 'CommandVelocity'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandSetListenerVelocity(float x, float y, float z)
/*      */   {
/* 2060 */     if (this.soundLibrary != null)
/* 2061 */       this.soundLibrary.setListenerVelocity(x, y, z);
/*      */     else
/* 2063 */       errorMessage("Variable 'soundLibrary' null in method 'CommandSetListenerVelocity'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandPlay(String sourcename)
/*      */   {
/* 2075 */     if (this.soundLibrary != null)
/* 2076 */       this.soundLibrary.play(sourcename);
/*      */     else
/* 2078 */       errorMessage("Variable 'soundLibrary' null in method 'CommandPlay'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandFeedRawAudioData(String sourcename, byte[] buffer)
/*      */   {
/* 2092 */     if (this.soundLibrary != null)
/* 2093 */       this.soundLibrary.feedRawAudioData(sourcename, buffer);
/*      */     else
/* 2095 */       errorMessage("Variable 'soundLibrary' null in method 'CommandFeedRawAudioData'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandPause(String sourcename)
/*      */   {
/* 2106 */     if (this.soundLibrary != null)
/* 2107 */       this.soundLibrary.pause(sourcename);
/*      */     else
/* 2109 */       errorMessage("Variable 'soundLibrary' null in method 'CommandPause'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandStop(String sourcename)
/*      */   {
/* 2120 */     if (this.soundLibrary != null)
/* 2121 */       this.soundLibrary.stop(sourcename);
/*      */     else
/* 2123 */       errorMessage("Variable 'soundLibrary' null in method 'CommandStop'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandRewind(String sourcename)
/*      */   {
/* 2134 */     if (this.soundLibrary != null)
/* 2135 */       this.soundLibrary.rewind(sourcename);
/*      */     else
/* 2137 */       errorMessage("Variable 'soundLibrary' null in method 'CommandRewind'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandFlush(String sourcename)
/*      */   {
/* 2148 */     if (this.soundLibrary != null)
/* 2149 */       this.soundLibrary.flush(sourcename);
/*      */     else
/* 2151 */       errorMessage("Variable 'soundLibrary' null in method 'CommandFlush'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandSetTemporary(String sourcename, boolean temporary)
/*      */   {
/* 2170 */     if (this.soundLibrary != null)
/* 2171 */       this.soundLibrary.setTemporary(sourcename, temporary);
/*      */     else
/* 2173 */       errorMessage("Variable 'soundLibrary' null in method 'CommandSetActive'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandRemoveSource(String sourcename)
/*      */   {
/* 2184 */     if (this.soundLibrary != null)
/* 2185 */       this.soundLibrary.removeSource(sourcename);
/*      */     else
/* 2187 */       errorMessage("Variable 'soundLibrary' null in method 'CommandRemoveSource'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandMoveListener(float x, float y, float z)
/*      */   {
/* 2200 */     if (this.soundLibrary != null)
/* 2201 */       this.soundLibrary.moveListener(x, y, z);
/*      */     else
/* 2203 */       errorMessage("Variable 'soundLibrary' null in method 'CommandMoveListener'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandSetListenerPosition(float x, float y, float z)
/*      */   {
/* 2216 */     if (this.soundLibrary != null)
/* 2217 */       this.soundLibrary.setListenerPosition(x, y, z);
/*      */     else
/* 2219 */       errorMessage("Variable 'soundLibrary' null in method 'CommandSetListenerPosition'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandTurnListener(float angle)
/*      */   {
/* 2232 */     if (this.soundLibrary != null)
/* 2233 */       this.soundLibrary.turnListener(angle);
/*      */     else
/* 2235 */       errorMessage("Variable 'soundLibrary' null in method 'CommandTurnListener'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandSetListenerAngle(float angle)
/*      */   {
/* 2247 */     if (this.soundLibrary != null)
/* 2248 */       this.soundLibrary.setListenerAngle(angle);
/*      */     else
/* 2250 */       errorMessage("Variable 'soundLibrary' null in method 'CommandSetListenerAngle'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandSetListenerOrientation(float lookX, float lookY, float lookZ, float upX, float upY, float upZ)
/*      */   {
/* 2269 */     if (this.soundLibrary != null) {
/* 2270 */       this.soundLibrary.setListenerOrientation(lookX, lookY, lookZ, upX, upY, upZ);
/*      */     }
/*      */     else
/* 2273 */       errorMessage("Variable 'soundLibrary' null in method 'CommandSetListenerOrientation'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandCull(String sourcename)
/*      */   {
/* 2286 */     if (this.soundLibrary != null)
/* 2287 */       this.soundLibrary.cull(sourcename);
/*      */     else
/* 2289 */       errorMessage("Variable 'soundLibrary' null in method 'CommandCull'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandActivate(String sourcename)
/*      */   {
/* 2300 */     if (this.soundLibrary != null)
/* 2301 */       this.soundLibrary.activate(sourcename);
/*      */     else
/* 2303 */       errorMessage("Variable 'soundLibrary' null in method 'CommandActivate'", 0);
/*      */   }
/*      */ 
/*      */   private void CommandSetMasterVolume(float value)
/*      */   {
/* 2315 */     if (this.soundLibrary != null)
/* 2316 */       this.soundLibrary.setMasterVolume(value);
/*      */     else
/* 2318 */       errorMessage("Variable 'soundLibrary' null in method 'CommandSetMasterVolume'", 0);
/*      */   }
/*      */ 
/*      */   protected void ManageSources()
/*      */   {
/*      */   }
/*      */ 
/*      */   public boolean CommandQueue(CommandObject newCommand)
/*      */   {
/* 2359 */     synchronized (SoundSystemConfig.THREAD_SYNC)
/*      */     {
/* 2361 */       if (newCommand == null)
/*      */       {
/* 2364 */         boolean activations = false;
/*      */ 
/* 2368 */         while ((this.commandQueue != null) && (this.commandQueue.size() > 0))
/*      */         {
/* 2371 */           CommandObject commandObject = (CommandObject)this.commandQueue.remove(0);
/*      */ 
/* 2373 */           if (commandObject != null)
/*      */           {
/* 2375 */             switch (commandObject.Command)
/*      */             {
/*      */             case 1:
/* 2378 */               CommandInitialize();
/* 2379 */               break;
/*      */             case 2:
/* 2381 */               CommandLoadSound((FilenameURL)commandObject.objectArgs[0]);
/*      */ 
/* 2383 */               break;
/*      */             case 3:
/* 2385 */               CommandLoadSound((SoundBuffer)commandObject.objectArgs[0], commandObject.stringArgs[0]);
/*      */ 
/* 2388 */               break;
/*      */             case 4:
/* 2390 */               CommandUnloadSound(commandObject.stringArgs[0]);
/* 2391 */               break;
/*      */             case 5:
/* 2393 */               CommandQueueSound(commandObject.stringArgs[0], (FilenameURL)commandObject.objectArgs[0]);
/*      */ 
/* 2395 */               break;
/*      */             case 6:
/* 2397 */               CommandDequeueSound(commandObject.stringArgs[0], commandObject.stringArgs[1]);
/*      */ 
/* 2399 */               break;
/*      */             case 7:
/* 2401 */               CommandFadeOut(commandObject.stringArgs[0], (FilenameURL)commandObject.objectArgs[0], commandObject.longArgs[0]);
/*      */ 
/* 2404 */               break;
/*      */             case 8:
/* 2406 */               CommandFadeOutIn(commandObject.stringArgs[0], (FilenameURL)commandObject.objectArgs[0], commandObject.longArgs[0], commandObject.longArgs[1]);
/*      */ 
/* 2410 */               break;
/*      */             case 9:
/* 2412 */               CommandCheckFadeVolumes();
/* 2413 */               break;
/*      */             case 10:
/* 2415 */               CommandNewSource(commandObject.boolArgs[0], commandObject.boolArgs[1], commandObject.boolArgs[2], commandObject.stringArgs[0], (FilenameURL)commandObject.objectArgs[0], commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2], commandObject.intArgs[0], commandObject.floatArgs[3]);
/*      */ 
/* 2425 */               break;
/*      */             case 11:
/* 2427 */               CommandRawDataStream((AudioFormat)commandObject.objectArgs[0], commandObject.boolArgs[0], commandObject.stringArgs[0], commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2], commandObject.intArgs[0], commandObject.floatArgs[3]);
/*      */ 
/* 2436 */               break;
/*      */             case 12:
/* 2438 */               CommandQuickPlay(commandObject.boolArgs[0], commandObject.boolArgs[1], commandObject.boolArgs[2], commandObject.stringArgs[0], (FilenameURL)commandObject.objectArgs[0], commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2], commandObject.intArgs[0], commandObject.floatArgs[3], commandObject.boolArgs[3]);
/*      */ 
/* 2449 */               break;
/*      */             case 13:
/* 2451 */               CommandSetPosition(commandObject.stringArgs[0], commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2]);
/*      */ 
/* 2455 */               break;
/*      */             case 14:
/* 2457 */               CommandSetVolume(commandObject.stringArgs[0], commandObject.floatArgs[0]);
/*      */ 
/* 2459 */               break;
/*      */             case 15:
/* 2461 */               CommandSetPitch(commandObject.stringArgs[0], commandObject.floatArgs[0]);
/*      */ 
/* 2463 */               break;
/*      */             case 16:
/* 2465 */               CommandSetPriority(commandObject.stringArgs[0], commandObject.boolArgs[0]);
/*      */ 
/* 2467 */               break;
/*      */             case 17:
/* 2469 */               CommandSetLooping(commandObject.stringArgs[0], commandObject.boolArgs[0]);
/*      */ 
/* 2471 */               break;
/*      */             case 18:
/* 2473 */               CommandSetAttenuation(commandObject.stringArgs[0], commandObject.intArgs[0]);
/*      */ 
/* 2475 */               break;
/*      */             case 19:
/* 2477 */               CommandSetDistOrRoll(commandObject.stringArgs[0], commandObject.floatArgs[0]);
/*      */ 
/* 2479 */               break;
/*      */             case 20:
/* 2481 */               CommandChangeDopplerFactor(commandObject.floatArgs[0]);
/*      */ 
/* 2483 */               break;
/*      */             case 21:
/* 2485 */               CommandChangeDopplerVelocity(commandObject.floatArgs[0]);
/*      */ 
/* 2487 */               break;
/*      */             case 22:
/* 2489 */               CommandSetVelocity(commandObject.stringArgs[0], commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2]);
/*      */ 
/* 2494 */               break;
/*      */             case 23:
/* 2496 */               CommandSetListenerVelocity(commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2]);
/*      */ 
/* 2501 */               break;
/*      */             case 24:
/* 2508 */               this.sourcePlayList.add(commandObject);
/* 2509 */               break;
/*      */             case 25:
/* 2511 */               this.sourcePlayList.add(commandObject);
/* 2512 */               break;
/*      */             case 26:
/* 2515 */               CommandPause(commandObject.stringArgs[0]);
/* 2516 */               break;
/*      */             case 27:
/* 2518 */               CommandStop(commandObject.stringArgs[0]);
/* 2519 */               break;
/*      */             case 28:
/* 2521 */               CommandRewind(commandObject.stringArgs[0]);
/* 2522 */               break;
/*      */             case 29:
/* 2524 */               CommandFlush(commandObject.stringArgs[0]);
/* 2525 */               break;
/*      */             case 30:
/* 2527 */               CommandCull(commandObject.stringArgs[0]);
/* 2528 */               break;
/*      */             case 31:
/* 2530 */               activations = true;
/* 2531 */               CommandActivate(commandObject.stringArgs[0]);
/* 2532 */               break;
/*      */             case 32:
/* 2534 */               CommandSetTemporary(commandObject.stringArgs[0], commandObject.boolArgs[0]);
/*      */ 
/* 2536 */               break;
/*      */             case 33:
/* 2538 */               CommandRemoveSource(commandObject.stringArgs[0]);
/* 2539 */               break;
/*      */             case 34:
/* 2541 */               CommandMoveListener(commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2]);
/*      */ 
/* 2544 */               break;
/*      */             case 35:
/* 2546 */               CommandSetListenerPosition(commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2]);
/*      */ 
/* 2550 */               break;
/*      */             case 36:
/* 2552 */               CommandTurnListener(commandObject.floatArgs[0]);
/* 2553 */               break;
/*      */             case 37:
/* 2555 */               CommandSetListenerAngle(commandObject.floatArgs[0]);
/*      */ 
/* 2557 */               break;
/*      */             case 38:
/* 2559 */               CommandSetListenerOrientation(commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2], commandObject.floatArgs[3], commandObject.floatArgs[4], commandObject.floatArgs[5]);
/*      */ 
/* 2566 */               break;
/*      */             case 39:
/* 2568 */               CommandSetMasterVolume(commandObject.floatArgs[0]);
/*      */ 
/* 2570 */               break;
/*      */             case 40:
/* 2572 */               CommandNewLibrary(commandObject.classArgs[0]);
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/* 2583 */         if (activations) {
/* 2584 */           this.soundLibrary.replaySources();
/*      */         }
/*      */ 
/* 2589 */         while ((this.sourcePlayList != null) && (this.sourcePlayList.size() > 0))
/*      */         {
/* 2592 */           CommandObject commandObject = (CommandObject)this.sourcePlayList.remove(0);
/* 2593 */           if (commandObject != null)
/*      */           {
/* 2596 */             switch (commandObject.Command)
/*      */             {
/*      */             case 24:
/* 2599 */               CommandPlay(commandObject.stringArgs[0]);
/* 2600 */               break;
/*      */             case 25:
/* 2602 */               CommandFeedRawAudioData(commandObject.stringArgs[0], commandObject.buffer);
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/* 2610 */         return (this.commandQueue != null) && (this.commandQueue.size() > 0);
/*      */       }
/*      */ 
/* 2615 */       if (this.commandQueue == null) {
/* 2616 */         return false;
/*      */       }
/* 2618 */       this.commandQueue.add(newCommand);
/*      */ 
/* 2621 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void removeTemporarySources()
/*      */   {
/* 2633 */     synchronized (SoundSystemConfig.THREAD_SYNC)
/*      */     {
/* 2635 */       if (this.soundLibrary != null)
/* 2636 */         this.soundLibrary.removeTemporarySources();
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean playing(String sourcename)
/*      */   {
/* 2647 */     synchronized (SoundSystemConfig.THREAD_SYNC)
/*      */     {
/* 2649 */       if (this.soundLibrary == null) {
/* 2650 */         return false;
/*      */       }
/* 2652 */       Source src = (Source)this.soundLibrary.getSources().get(sourcename);
/*      */ 
/* 2654 */       if (src == null) {
/* 2655 */         return false;
/*      */       }
/* 2657 */       return src.playing();
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean playing()
/*      */   {
/* 2667 */     synchronized (SoundSystemConfig.THREAD_SYNC)
/*      */     {
/* 2669 */       if (this.soundLibrary == null) {
/* 2670 */         return false;
/*      */       }
/* 2672 */       HashMap sourceMap = this.soundLibrary.getSources();
/* 2673 */       if (sourceMap == null) {
/* 2674 */         return false;
/*      */       }
/* 2676 */       Set keys = sourceMap.keySet();
/* 2677 */       Iterator iter = keys.iterator();
/*      */ 
/* 2681 */       while (iter.hasNext())
/*      */       {
/* 2683 */         String sourcename = (String)iter.next();
/* 2684 */         Source source = (Source)sourceMap.get(sourcename);
/* 2685 */         if ((source != null) && 
/* 2686 */           (source.playing())) {
/* 2687 */           return true;
/*      */         }
/*      */       }
/* 2690 */       return false;
/*      */     }
/*      */   }
/*      */ 
/*      */   private HashMap<String, Source> copySources(HashMap<String, Source> sourceMap)
/*      */   {
/* 2704 */     Set keys = sourceMap.keySet();
/* 2705 */     Iterator iter = keys.iterator();
/*      */ 
/* 2710 */     HashMap returnMap = new HashMap();
/*      */ 
/* 2714 */     while (iter.hasNext())
/*      */     {
/* 2716 */       String sourcename = (String)iter.next();
/* 2717 */       Source source = (Source)sourceMap.get(sourcename);
/* 2718 */       if (source != null)
/* 2719 */         returnMap.put(sourcename, new Source(source, null));
/*      */     }
/* 2721 */     return returnMap;
/*      */   }
/*      */ 
/*      */   public static boolean libraryCompatible(Class libraryClass)
/*      */   {
/* 2732 */     SoundSystemLogger logger = SoundSystemConfig.getLogger();
/*      */ 
/* 2734 */     if (logger == null)
/*      */     {
/* 2736 */       logger = new SoundSystemLogger();
/* 2737 */       SoundSystemConfig.setLogger(logger);
/*      */     }
/* 2739 */     logger.message("", 0);
/* 2740 */     logger.message("Checking if " + SoundSystemConfig.getLibraryTitle(libraryClass) + " is compatible...", 0);
/*      */ 
/* 2744 */     boolean comp = SoundSystemConfig.libraryCompatible(libraryClass);
/*      */ 
/* 2746 */     if (comp)
/* 2747 */       logger.message("...yes", 1);
/*      */     else {
/* 2749 */       logger.message("...no", 1);
/*      */     }
/* 2751 */     return comp;
/*      */   }
/*      */ 
/*      */   public static Class currentLibrary()
/*      */   {
/* 2760 */     return currentLibrary(false, null);
/*      */   }
/*      */ 
/*      */   public static boolean initialized()
/*      */   {
/* 2769 */     return initialized(false, false);
/*      */   }
/*      */ 
/*      */   public static SoundSystemException getLastException()
/*      */   {
/* 2778 */     return lastException(false, null);
/*      */   }
/*      */ 
/*      */   public static void setException(SoundSystemException e)
/*      */   {
/* 2788 */     lastException(true, e);
/*      */   }
/*      */ 
/*      */   private static boolean initialized(boolean action, boolean value)
/*      */   {
/* 2799 */     synchronized (SoundSystemConfig.THREAD_SYNC)
/*      */     {
/* 2801 */       if (action == true)
/* 2802 */         initialized = value;
/* 2803 */       return initialized;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static Class currentLibrary(boolean action, Class value)
/*      */   {
/* 2816 */     synchronized (SoundSystemConfig.THREAD_SYNC)
/*      */     {
/* 2818 */       if (action == true)
/* 2819 */         currentLibrary = value;
/* 2820 */       return currentLibrary;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static SoundSystemException lastException(boolean action, SoundSystemException e)
/*      */   {
/* 2834 */     synchronized (SoundSystemConfig.THREAD_SYNC)
/*      */     {
/* 2836 */       if (action == true)
/* 2837 */         lastException = e;
/* 2838 */       return lastException;
/*      */     }
/*      */   }
/*      */ 
/*      */   protected static void snooze(long milliseconds)
/*      */   {
/*      */     try
/*      */     {
/* 2849 */       Thread.sleep(milliseconds);
/*      */     }
/*      */     catch (InterruptedException e)
/*      */     {
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void message(String message, int indent)
/*      */   {
/* 2861 */     this.logger.message(message, indent);
/*      */   }
/*      */ 
/*      */   protected void importantMessage(String message, int indent)
/*      */   {
/* 2871 */     this.logger.importantMessage(message, indent);
/*      */   }
/*      */ 
/*      */   protected boolean errorCheck(boolean error, String message, int indent)
/*      */   {
/* 2883 */     return this.logger.errorCheck(error, this.className, message, indent);
/*      */   }
/*      */ 
/*      */   protected void errorMessage(String message, int indent)
/*      */   {
/* 2893 */     this.logger.errorMessage(this.className, message, indent);
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.SoundSystem
 * JD-Core Version:    0.6.2
 */