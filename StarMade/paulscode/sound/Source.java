/*      */ package paulscode.sound;
/*      */ 
/*      */ import java.net.URL;
/*      */ import java.util.LinkedList;
/*      */ import java.util.ListIterator;
/*      */ import javax.sound.sampled.AudioFormat;
/*      */ 
/*      */ public class Source
/*      */ {
/*   53 */   protected Class libraryType = Library.class;
/*      */   private static final boolean GET = false;
/*      */   private static final boolean SET = true;
/*      */   private static final boolean XXX = false;
/*      */   private SoundSystemLogger logger;
/*   80 */   public boolean rawDataStream = false;
/*      */ 
/*   85 */   public AudioFormat rawDataFormat = null;
/*      */ 
/*   90 */   public boolean temporary = false;
/*      */ 
/*   96 */   public boolean priority = false;
/*      */ 
/*  101 */   public boolean toStream = false;
/*      */ 
/*  106 */   public boolean toLoop = false;
/*      */ 
/*  112 */   public boolean toPlay = false;
/*      */ 
/*  118 */   public String sourcename = "";
/*      */ 
/*  123 */   public FilenameURL filenameURL = null;
/*      */   public Vector3D position;
/*  133 */   public int attModel = 0;
/*      */ 
/*  138 */   public float distOrRoll = 0.0F;
/*      */   public Vector3D velocity;
/*  150 */   public float gain = 1.0F;
/*      */ 
/*  155 */   public float sourceVolume = 1.0F;
/*      */ 
/*  160 */   protected float pitch = 1.0F;
/*      */ 
/*  165 */   public float distanceFromListener = 0.0F;
/*      */ 
/*  170 */   public Channel channel = null;
/*      */ 
/*  175 */   public SoundBuffer soundBuffer = null;
/*      */ 
/*  180 */   private boolean active = true;
/*      */ 
/*  185 */   private boolean stopped = true;
/*      */ 
/*  190 */   private boolean paused = false;
/*      */ 
/*  195 */   protected ICodec codec = null;
/*      */ 
/*  200 */   protected ICodec nextCodec = null;
/*      */ 
/*  205 */   protected LinkedList<SoundBuffer> nextBuffers = null;
/*      */ 
/*  211 */   protected LinkedList<FilenameURL> soundSequenceQueue = null;
/*      */ 
/*  216 */   protected final Object soundSequenceLock = new Object();
/*      */ 
/*  222 */   public boolean preLoad = false;
/*      */ 
/*  228 */   protected float fadeOutGain = -1.0F;
/*      */ 
/*  234 */   protected float fadeInGain = 1.0F;
/*      */ 
/*  239 */   protected long fadeOutMilis = 0L;
/*      */ 
/*  244 */   protected long fadeInMilis = 0L;
/*      */ 
/*  249 */   protected long lastFadeCheck = 0L;
/*      */ 
/*      */   public Source(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, SoundBuffer soundBuffer, float x, float y, float z, int attModel, float distOrRoll, boolean temporary)
/*      */   {
/*  272 */     this.logger = SoundSystemConfig.getLogger();
/*      */ 
/*  274 */     this.priority = priority;
/*  275 */     this.toStream = toStream;
/*  276 */     this.toLoop = toLoop;
/*  277 */     this.sourcename = sourcename;
/*  278 */     this.filenameURL = filenameURL;
/*  279 */     this.soundBuffer = soundBuffer;
/*  280 */     this.position = new Vector3D(x, y, z);
/*  281 */     this.attModel = attModel;
/*  282 */     this.distOrRoll = distOrRoll;
/*  283 */     this.velocity = new Vector3D(0.0F, 0.0F, 0.0F);
/*  284 */     this.temporary = temporary;
/*      */ 
/*  286 */     if ((toStream) && (filenameURL != null))
/*  287 */       this.codec = SoundSystemConfig.getCodec(filenameURL.getFilename());
/*      */   }
/*      */ 
/*      */   public Source(Source old, SoundBuffer soundBuffer)
/*      */   {
/*  298 */     this.logger = SoundSystemConfig.getLogger();
/*      */ 
/*  300 */     this.priority = old.priority;
/*  301 */     this.toStream = old.toStream;
/*  302 */     this.toLoop = old.toLoop;
/*  303 */     this.sourcename = old.sourcename;
/*  304 */     this.filenameURL = old.filenameURL;
/*  305 */     this.position = old.position.clone();
/*  306 */     this.attModel = old.attModel;
/*  307 */     this.distOrRoll = old.distOrRoll;
/*  308 */     this.velocity = old.velocity.clone();
/*  309 */     this.temporary = old.temporary;
/*      */ 
/*  311 */     this.sourceVolume = old.sourceVolume;
/*      */ 
/*  313 */     this.rawDataStream = old.rawDataStream;
/*  314 */     this.rawDataFormat = old.rawDataFormat;
/*      */ 
/*  316 */     this.soundBuffer = soundBuffer;
/*      */ 
/*  318 */     if ((this.toStream) && (this.filenameURL != null))
/*  319 */       this.codec = SoundSystemConfig.getCodec(this.filenameURL.getFilename());
/*      */   }
/*      */ 
/*      */   public Source(AudioFormat audioFormat, boolean priority, String sourcename, float x, float y, float z, int attModel, float distOrRoll)
/*      */   {
/*  338 */     this.logger = SoundSystemConfig.getLogger();
/*      */ 
/*  340 */     this.priority = priority;
/*  341 */     this.toStream = true;
/*  342 */     this.toLoop = false;
/*  343 */     this.sourcename = sourcename;
/*  344 */     this.filenameURL = null;
/*  345 */     this.soundBuffer = null;
/*  346 */     this.position = new Vector3D(x, y, z);
/*  347 */     this.attModel = attModel;
/*  348 */     this.distOrRoll = distOrRoll;
/*  349 */     this.velocity = new Vector3D(0.0F, 0.0F, 0.0F);
/*  350 */     this.temporary = false;
/*      */ 
/*  352 */     this.rawDataStream = true;
/*  353 */     this.rawDataFormat = audioFormat;
/*      */   }
/*      */ 
/*      */   public void cleanup()
/*      */   {
/*  362 */     if (this.codec != null) {
/*  363 */       this.codec.cleanup();
/*      */     }
/*  365 */     synchronized (this.soundSequenceLock)
/*      */     {
/*  367 */       if (this.soundSequenceQueue != null)
/*  368 */         this.soundSequenceQueue.clear();
/*  369 */       this.soundSequenceQueue = null;
/*      */     }
/*      */ 
/*  372 */     this.sourcename = null;
/*  373 */     this.filenameURL = null;
/*  374 */     this.position = null;
/*  375 */     this.soundBuffer = null;
/*  376 */     this.codec = null;
/*      */   }
/*      */ 
/*      */   public void queueSound(FilenameURL filenameURL)
/*      */   {
/*  387 */     if (!this.toStream)
/*      */     {
/*  389 */       errorMessage("Method 'queueSound' may only be used for streaming and MIDI sources.");
/*      */ 
/*  391 */       return;
/*      */     }
/*  393 */     if (filenameURL == null)
/*      */     {
/*  395 */       errorMessage("File not specified in method 'queueSound'");
/*  396 */       return;
/*      */     }
/*      */ 
/*  399 */     synchronized (this.soundSequenceLock)
/*      */     {
/*  401 */       if (this.soundSequenceQueue == null)
/*  402 */         this.soundSequenceQueue = new LinkedList();
/*  403 */       this.soundSequenceQueue.add(filenameURL);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void dequeueSound(String filename)
/*      */   {
/*  415 */     if (!this.toStream)
/*      */     {
/*  417 */       errorMessage("Method 'dequeueSound' may only be used for streaming and MIDI sources.");
/*      */ 
/*  419 */       return;
/*      */     }
/*  421 */     if ((filename == null) || (filename.equals("")))
/*      */     {
/*  423 */       errorMessage("Filename not specified in method 'dequeueSound'");
/*  424 */       return;
/*      */     }
/*      */ 
/*  427 */     synchronized (this.soundSequenceLock)
/*      */     {
/*  429 */       if (this.soundSequenceQueue != null)
/*      */       {
/*  431 */         ListIterator i = this.soundSequenceQueue.listIterator();
/*  432 */         while (i.hasNext())
/*      */         {
/*  434 */           if (((FilenameURL)i.next()).getFilename().equals(filename))
/*      */           {
/*  436 */             i.remove();
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void fadeOut(FilenameURL filenameURL, long milis)
/*      */   {
/*  457 */     if (!this.toStream)
/*      */     {
/*  459 */       errorMessage("Method 'fadeOut' may only be used for streaming and MIDI sources.");
/*      */ 
/*  461 */       return;
/*      */     }
/*  463 */     if (milis < 0L)
/*      */     {
/*  465 */       errorMessage("Miliseconds may not be negative in method 'fadeOut'.");
/*      */ 
/*  467 */       return;
/*      */     }
/*      */ 
/*  470 */     this.fadeOutMilis = milis;
/*  471 */     this.fadeInMilis = 0L;
/*  472 */     this.fadeOutGain = 1.0F;
/*  473 */     this.lastFadeCheck = System.currentTimeMillis();
/*      */ 
/*  475 */     synchronized (this.soundSequenceLock)
/*      */     {
/*  477 */       if (this.soundSequenceQueue != null) {
/*  478 */         this.soundSequenceQueue.clear();
/*      */       }
/*  480 */       if (filenameURL != null)
/*      */       {
/*  482 */         if (this.soundSequenceQueue == null)
/*  483 */           this.soundSequenceQueue = new LinkedList();
/*  484 */         this.soundSequenceQueue.add(filenameURL);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void fadeOutIn(FilenameURL filenameURL, long milisOut, long milisIn)
/*      */   {
/*  504 */     if (!this.toStream)
/*      */     {
/*  506 */       errorMessage("Method 'fadeOutIn' may only be used for streaming and MIDI sources.");
/*      */ 
/*  508 */       return;
/*      */     }
/*  510 */     if (filenameURL == null)
/*      */     {
/*  512 */       errorMessage("Filename/URL not specified in method 'fadeOutIn'.");
/*  513 */       return;
/*      */     }
/*  515 */     if ((milisOut < 0L) || (milisIn < 0L))
/*      */     {
/*  517 */       errorMessage("Miliseconds may not be negative in method 'fadeOutIn'.");
/*      */ 
/*  519 */       return;
/*      */     }
/*      */ 
/*  522 */     this.fadeOutMilis = milisOut;
/*  523 */     this.fadeInMilis = milisIn;
/*      */ 
/*  525 */     this.fadeOutGain = 1.0F;
/*  526 */     this.lastFadeCheck = System.currentTimeMillis();
/*      */ 
/*  528 */     synchronized (this.soundSequenceLock)
/*      */     {
/*  530 */       if (this.soundSequenceQueue == null)
/*  531 */         this.soundSequenceQueue = new LinkedList();
/*  532 */       this.soundSequenceQueue.clear();
/*  533 */       this.soundSequenceQueue.add(filenameURL);
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean checkFadeOut()
/*      */   {
/*  546 */     if (!this.toStream) {
/*  547 */       return false;
/*      */     }
/*  549 */     if ((this.fadeOutGain == -1.0F) && (this.fadeInGain == 1.0F)) {
/*  550 */       return false;
/*      */     }
/*  552 */     long currentTime = System.currentTimeMillis();
/*  553 */     long milisPast = currentTime - this.lastFadeCheck;
/*  554 */     this.lastFadeCheck = currentTime;
/*      */ 
/*  556 */     if (this.fadeOutGain >= 0.0F)
/*      */     {
/*  558 */       if (this.fadeOutMilis == 0L)
/*      */       {
/*  560 */         this.fadeOutGain = -1.0F;
/*  561 */         this.fadeInGain = 0.0F;
/*  562 */         if (!incrementSoundSequence())
/*      */         {
/*  564 */           stop();
/*      */         }
/*  566 */         positionChanged();
/*  567 */         this.preLoad = true;
/*  568 */         return false;
/*      */       }
/*      */ 
/*  572 */       float fadeOutReduction = (float)milisPast / (float)this.fadeOutMilis;
/*  573 */       this.fadeOutGain -= fadeOutReduction;
/*  574 */       if (this.fadeOutGain <= 0.0F)
/*      */       {
/*  576 */         this.fadeOutGain = -1.0F;
/*  577 */         this.fadeInGain = 0.0F;
/*  578 */         if (!incrementSoundSequence())
/*  579 */           stop();
/*  580 */         positionChanged();
/*  581 */         this.preLoad = true;
/*  582 */         return false;
/*      */       }
/*      */ 
/*  585 */       positionChanged();
/*  586 */       return true;
/*      */     }
/*      */ 
/*  589 */     if (this.fadeInGain < 1.0F)
/*      */     {
/*  591 */       this.fadeOutGain = -1.0F;
/*  592 */       if (this.fadeInMilis == 0L)
/*      */       {
/*  594 */         this.fadeOutGain = -1.0F;
/*  595 */         this.fadeInGain = 1.0F;
/*      */       }
/*      */       else
/*      */       {
/*  599 */         float fadeInIncrease = (float)milisPast / (float)this.fadeInMilis;
/*  600 */         this.fadeInGain += fadeInIncrease;
/*  601 */         if (this.fadeInGain >= 1.0F)
/*      */         {
/*  603 */           this.fadeOutGain = -1.0F;
/*  604 */           this.fadeInGain = 1.0F;
/*      */         }
/*      */       }
/*  607 */       positionChanged();
/*  608 */       return true;
/*      */     }
/*  610 */     return false;
/*      */   }
/*      */ 
/*      */   public boolean incrementSoundSequence()
/*      */   {
/*  622 */     if (!this.toStream)
/*      */     {
/*  624 */       errorMessage("Method 'incrementSoundSequence' may only be used for streaming and MIDI sources.");
/*      */ 
/*  626 */       return false;
/*      */     }
/*      */ 
/*  629 */     synchronized (this.soundSequenceLock)
/*      */     {
/*  631 */       if ((this.soundSequenceQueue != null) && (this.soundSequenceQueue.size() > 0))
/*      */       {
/*  633 */         this.filenameURL = ((FilenameURL)this.soundSequenceQueue.remove(0));
/*  634 */         if (this.codec != null)
/*  635 */           this.codec.cleanup();
/*  636 */         this.codec = SoundSystemConfig.getCodec(this.filenameURL.getFilename());
/*  637 */         return true;
/*      */       }
/*      */     }
/*  640 */     return false;
/*      */   }
/*      */ 
/*      */   public boolean readBuffersFromNextSoundInSequence()
/*      */   {
/*  652 */     if (!this.toStream)
/*      */     {
/*  654 */       errorMessage("Method 'readBuffersFromNextSoundInSequence' may only be used for streaming sources.");
/*      */ 
/*  656 */       return false;
/*      */     }
/*      */ 
/*  659 */     synchronized (this.soundSequenceLock)
/*      */     {
/*  661 */       if ((this.soundSequenceQueue != null) && (this.soundSequenceQueue.size() > 0))
/*      */       {
/*  663 */         if (this.nextCodec != null)
/*  664 */           this.nextCodec.cleanup();
/*  665 */         this.nextCodec = SoundSystemConfig.getCodec(((FilenameURL)this.soundSequenceQueue.get(0)).getFilename());
/*      */ 
/*  667 */         this.nextCodec.initialize(((FilenameURL)this.soundSequenceQueue.get(0)).getURL());
/*      */ 
/*  669 */         SoundBuffer buffer = null;
/*  670 */         for (int i = 0; 
/*  672 */           (i < SoundSystemConfig.getNumberStreamingBuffers()) && (!this.nextCodec.endOfStream()); 
/*  673 */           i++)
/*      */         {
/*  675 */           buffer = this.nextCodec.read();
/*  676 */           if (buffer != null)
/*      */           {
/*  678 */             if (this.nextBuffers == null)
/*  679 */               this.nextBuffers = new LinkedList();
/*  680 */             this.nextBuffers.add(buffer);
/*      */           }
/*      */         }
/*  683 */         return true;
/*      */       }
/*      */     }
/*  686 */     return false;
/*      */   }
/*      */ 
/*      */   public int getSoundSequenceQueueSize()
/*      */   {
/*  696 */     if (this.soundSequenceQueue == null)
/*  697 */       return 0;
/*  698 */     return this.soundSequenceQueue.size();
/*      */   }
/*      */ 
/*      */   public void setTemporary(boolean tmp)
/*      */   {
/*  707 */     this.temporary = tmp;
/*      */   }
/*      */ 
/*      */   public void listenerMoved()
/*      */   {
/*      */   }
/*      */ 
/*      */   public void setPosition(float x, float y, float z)
/*      */   {
/*  724 */     this.position.x = x;
/*  725 */     this.position.y = y;
/*  726 */     this.position.z = z;
/*      */   }
/*      */ 
/*      */   public void positionChanged()
/*      */   {
/*      */   }
/*      */ 
/*      */   public void setPriority(boolean pri)
/*      */   {
/*  743 */     this.priority = pri;
/*      */   }
/*      */ 
/*      */   public void setLooping(boolean lp)
/*      */   {
/*  752 */     this.toLoop = lp;
/*      */   }
/*      */ 
/*      */   public void setAttenuation(int model)
/*      */   {
/*  761 */     this.attModel = model;
/*      */   }
/*      */ 
/*      */   public void setDistOrRoll(float dr)
/*      */   {
/*  771 */     this.distOrRoll = dr;
/*      */   }
/*      */ 
/*      */   public void setVelocity(float x, float y, float z)
/*      */   {
/*  782 */     this.velocity.x = x;
/*  783 */     this.velocity.y = y;
/*  784 */     this.velocity.z = z;
/*      */   }
/*      */ 
/*      */   public float getDistanceFromListener()
/*      */   {
/*  793 */     return this.distanceFromListener;
/*      */   }
/*      */ 
/*      */   public void setPitch(float value)
/*      */   {
/*  802 */     float newPitch = value;
/*  803 */     if (newPitch < 0.5F)
/*  804 */       newPitch = 0.5F;
/*  805 */     else if (newPitch > 2.0F)
/*  806 */       newPitch = 2.0F;
/*  807 */     this.pitch = newPitch;
/*      */   }
/*      */ 
/*      */   public float getPitch()
/*      */   {
/*  816 */     return this.pitch;
/*      */   }
/*      */ 
/*      */   public boolean reverseByteOrder()
/*      */   {
/*  826 */     return SoundSystemConfig.reverseByteOrder(this.libraryType);
/*      */   }
/*      */ 
/*      */   public void changeSource(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, SoundBuffer soundBuffer, float x, float y, float z, int attModel, float distOrRoll, boolean temporary)
/*      */   {
/*  849 */     this.priority = priority;
/*  850 */     this.toStream = toStream;
/*  851 */     this.toLoop = toLoop;
/*  852 */     this.sourcename = sourcename;
/*  853 */     this.filenameURL = filenameURL;
/*  854 */     this.soundBuffer = soundBuffer;
/*  855 */     this.position.x = x;
/*  856 */     this.position.y = y;
/*  857 */     this.position.z = z;
/*  858 */     this.attModel = attModel;
/*  859 */     this.distOrRoll = distOrRoll;
/*  860 */     this.temporary = temporary;
/*      */   }
/*      */ 
/*      */   public int feedRawAudioData(Channel c, byte[] buffer)
/*      */   {
/*  871 */     if (!active(false, false))
/*      */     {
/*  873 */       this.toPlay = true;
/*  874 */       return -1;
/*      */     }
/*  876 */     if (this.channel != c)
/*      */     {
/*  878 */       this.channel = c;
/*  879 */       this.channel.close();
/*  880 */       this.channel.setAudioFormat(this.rawDataFormat);
/*  881 */       positionChanged();
/*      */     }
/*      */ 
/*  885 */     stopped(true, false);
/*  886 */     paused(true, false);
/*      */ 
/*  888 */     return this.channel.feedRawAudioData(buffer);
/*      */   }
/*      */ 
/*      */   public void play(Channel c)
/*      */   {
/*  897 */     if (!active(false, false))
/*      */     {
/*  899 */       if (this.toLoop)
/*  900 */         this.toPlay = true;
/*  901 */       return;
/*      */     }
/*  903 */     if (this.channel != c)
/*      */     {
/*  905 */       this.channel = c;
/*  906 */       this.channel.close();
/*      */     }
/*      */ 
/*  909 */     stopped(true, false);
/*  910 */     paused(true, false);
/*      */   }
/*      */ 
/*      */   public boolean stream()
/*      */   {
/*  920 */     if (this.channel == null) {
/*  921 */       return false;
/*      */     }
/*  923 */     if (this.preLoad)
/*      */     {
/*  925 */       if (this.rawDataStream)
/*  926 */         this.preLoad = false;
/*      */       else {
/*  928 */         return preLoad();
/*      */       }
/*      */     }
/*  931 */     if (this.rawDataStream)
/*      */     {
/*  933 */       if ((stopped()) || (paused()))
/*  934 */         return true;
/*  935 */       if (this.channel.buffersProcessed() > 0)
/*  936 */         this.channel.processBuffer();
/*  937 */       return true;
/*      */     }
/*      */ 
/*  941 */     if (this.codec == null)
/*  942 */       return false;
/*  943 */     if (stopped())
/*  944 */       return false;
/*  945 */     if (paused()) {
/*  946 */       return true;
/*      */     }
/*  948 */     int processed = this.channel.buffersProcessed();
/*      */ 
/*  950 */     SoundBuffer buffer = null;
/*  951 */     for (int i = 0; i < processed; i++)
/*      */     {
/*  953 */       buffer = this.codec.read();
/*  954 */       if (buffer != null)
/*      */       {
/*  956 */         if (buffer.audioData != null)
/*  957 */           this.channel.queueBuffer(buffer.audioData);
/*  958 */         buffer.cleanup();
/*  959 */         buffer = null;
/*  960 */         return true;
/*      */       }
/*  962 */       if (this.codec.endOfStream())
/*      */       {
/*  964 */         synchronized (this.soundSequenceLock)
/*      */         {
/*  966 */           if (SoundSystemConfig.getStreamQueueFormatsMatch())
/*      */           {
/*  968 */             if ((this.soundSequenceQueue != null) && (this.soundSequenceQueue.size() > 0))
/*      */             {
/*  971 */               if (this.codec != null)
/*  972 */                 this.codec.cleanup();
/*  973 */               this.filenameURL = ((FilenameURL)this.soundSequenceQueue.remove(0));
/*  974 */               this.codec = SoundSystemConfig.getCodec(this.filenameURL.getFilename());
/*      */ 
/*  976 */               this.codec.initialize(this.filenameURL.getURL());
/*  977 */               buffer = this.codec.read();
/*  978 */               if (buffer != null)
/*      */               {
/*  980 */                 if (buffer.audioData != null)
/*  981 */                   this.channel.queueBuffer(buffer.audioData);
/*  982 */                 buffer.cleanup();
/*  983 */                 buffer = null;
/*  984 */                 return true;
/*      */               }
/*      */             }
/*  987 */             else if (this.toLoop)
/*      */             {
/*  989 */               this.codec.initialize(this.filenameURL.getURL());
/*  990 */               buffer = this.codec.read();
/*  991 */               if (buffer != null)
/*      */               {
/*  993 */                 if (buffer.audioData != null)
/*  994 */                   this.channel.queueBuffer(buffer.audioData);
/*  995 */                 buffer.cleanup();
/*  996 */                 buffer = null;
/*  997 */                 return true;
/*      */               }
/*      */ 
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1040 */     return false;
/*      */   }
/*      */ 
/*      */   public boolean preLoad()
/*      */   {
/* 1049 */     if (this.channel == null) {
/* 1050 */       return false;
/*      */     }
/* 1052 */     if (this.codec == null) {
/* 1053 */       return false;
/*      */     }
/* 1055 */     SoundBuffer buffer = null;
/*      */ 
/* 1057 */     boolean noNextBuffers = false;
/* 1058 */     synchronized (this.soundSequenceLock)
/*      */     {
/* 1060 */       if ((this.nextBuffers == null) || (this.nextBuffers.isEmpty())) {
/* 1061 */         noNextBuffers = true;
/*      */       }
/*      */     }
/* 1064 */     if ((this.nextCodec != null) && (!noNextBuffers))
/*      */     {
/* 1066 */       this.codec = this.nextCodec;
/* 1067 */       this.nextCodec = null;
/* 1068 */       synchronized (this.soundSequenceLock)
/*      */       {
/* 1070 */         while (!this.nextBuffers.isEmpty())
/*      */         {
/* 1072 */           buffer = (SoundBuffer)this.nextBuffers.remove(0);
/* 1073 */           if (buffer != null)
/*      */           {
/* 1075 */             if (buffer.audioData != null)
/* 1076 */               this.channel.queueBuffer(buffer.audioData);
/* 1077 */             buffer.cleanup();
/* 1078 */             buffer = null;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 1085 */       this.nextCodec = null;
/* 1086 */       URL url = this.filenameURL.getURL();
/*      */ 
/* 1088 */       this.codec.initialize(url);
/* 1089 */       for (int i = 0; i < SoundSystemConfig.getNumberStreamingBuffers(); 
/* 1090 */         i++)
/*      */       {
/* 1092 */         buffer = this.codec.read();
/* 1093 */         if (buffer != null)
/*      */         {
/* 1095 */           if (buffer.audioData != null)
/* 1096 */             this.channel.queueBuffer(buffer.audioData);
/* 1097 */           buffer.cleanup();
/* 1098 */           buffer = null;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1103 */     return true;
/*      */   }
/*      */ 
/*      */   public void pause()
/*      */   {
/* 1111 */     this.toPlay = false;
/* 1112 */     paused(true, true);
/* 1113 */     if (this.channel != null)
/* 1114 */       this.channel.pause();
/*      */     else
/* 1116 */       errorMessage("Channel null in method 'pause'");
/*      */   }
/*      */ 
/*      */   public void stop()
/*      */   {
/* 1124 */     this.toPlay = false;
/* 1125 */     stopped(true, true);
/* 1126 */     paused(true, false);
/* 1127 */     if (this.channel != null)
/* 1128 */       this.channel.stop();
/*      */     else
/* 1130 */       errorMessage("Channel null in method 'stop'");
/*      */   }
/*      */ 
/*      */   public void rewind()
/*      */   {
/* 1138 */     if (paused(false, false))
/*      */     {
/* 1140 */       stop();
/*      */     }
/* 1142 */     if (this.channel != null)
/*      */     {
/* 1144 */       boolean rePlay = playing();
/* 1145 */       this.channel.rewind();
/* 1146 */       if ((this.toStream) && (rePlay))
/*      */       {
/* 1148 */         stop();
/* 1149 */         play(this.channel);
/*      */       }
/*      */     }
/*      */     else {
/* 1153 */       errorMessage("Channel null in method 'rewind'");
/*      */     }
/*      */   }
/*      */ 
/*      */   public void flush()
/*      */   {
/* 1161 */     if (this.channel != null)
/* 1162 */       this.channel.flush();
/*      */     else
/* 1164 */       errorMessage("Channel null in method 'flush'");
/*      */   }
/*      */ 
/*      */   public void cull()
/*      */   {
/* 1173 */     if (!active(false, false))
/* 1174 */       return;
/* 1175 */     if ((playing()) && (this.toLoop))
/* 1176 */       this.toPlay = true;
/* 1177 */     if (this.rawDataStream)
/* 1178 */       this.toPlay = true;
/* 1179 */     active(true, false);
/* 1180 */     if (this.channel != null)
/* 1181 */       this.channel.close();
/* 1182 */     this.channel = null;
/*      */   }
/*      */ 
/*      */   public void activate()
/*      */   {
/* 1190 */     active(true, true);
/*      */   }
/*      */ 
/*      */   public boolean active()
/*      */   {
/* 1199 */     return active(false, false);
/*      */   }
/*      */ 
/*      */   public boolean playing()
/*      */   {
/* 1208 */     if ((this.channel == null) || (this.channel.attachedSource != this))
/* 1209 */       return false;
/* 1210 */     if ((paused()) || (stopped())) {
/* 1211 */       return false;
/*      */     }
/* 1213 */     return this.channel.playing();
/*      */   }
/*      */ 
/*      */   public boolean stopped()
/*      */   {
/* 1222 */     return stopped(false, false);
/*      */   }
/*      */ 
/*      */   public boolean paused()
/*      */   {
/* 1231 */     return paused(false, false);
/*      */   }
/*      */ 
/*      */   public float millisecondsPlayed()
/*      */   {
/* 1240 */     if (this.channel == null) {
/* 1241 */       return -1.0F;
/*      */     }
/* 1243 */     return this.channel.millisecondsPlayed();
/*      */   }
/*      */ 
/*      */   private synchronized boolean active(boolean action, boolean value)
/*      */   {
/* 1252 */     if (action == true)
/* 1253 */       this.active = value;
/* 1254 */     return this.active;
/*      */   }
/*      */ 
/*      */   private synchronized boolean stopped(boolean action, boolean value)
/*      */   {
/* 1263 */     if (action == true)
/* 1264 */       this.stopped = value;
/* 1265 */     return this.stopped;
/*      */   }
/*      */ 
/*      */   private synchronized boolean paused(boolean action, boolean value)
/*      */   {
/* 1274 */     if (action == true)
/* 1275 */       this.paused = value;
/* 1276 */     return this.paused;
/*      */   }
/*      */ 
/*      */   public String getClassName()
/*      */   {
/* 1285 */     String libTitle = SoundSystemConfig.getLibraryTitle(this.libraryType);
/*      */ 
/* 1287 */     if (libTitle.equals("No Sound")) {
/* 1288 */       return "Source";
/*      */     }
/* 1290 */     return "Source" + libTitle;
/*      */   }
/*      */ 
/*      */   protected void message(String message)
/*      */   {
/* 1298 */     this.logger.message(message, 0);
/*      */   }
/*      */ 
/*      */   protected void importantMessage(String message)
/*      */   {
/* 1307 */     this.logger.importantMessage(message, 0);
/*      */   }
/*      */ 
/*      */   protected boolean errorCheck(boolean error, String message)
/*      */   {
/* 1318 */     return this.logger.errorCheck(error, getClassName(), message, 0);
/*      */   }
/*      */ 
/*      */   protected void errorMessage(String message)
/*      */   {
/* 1327 */     this.logger.errorMessage(getClassName(), message, 0);
/*      */   }
/*      */ 
/*      */   protected void printStackTrace(Exception e)
/*      */   {
/* 1336 */     this.logger.printStackTrace(e, 1);
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.Source
 * JD-Core Version:    0.6.2
 */