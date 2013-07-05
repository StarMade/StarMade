/*      */ package paulscode.sound;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.net.URL;
/*      */ import java.util.LinkedList;
/*      */ import java.util.ListIterator;
/*      */ import javax.sound.midi.InvalidMidiDataException;
/*      */ import javax.sound.midi.MetaEventListener;
/*      */ import javax.sound.midi.MetaMessage;
/*      */ import javax.sound.midi.MidiDevice;
/*      */ import javax.sound.midi.MidiDevice.Info;
/*      */ import javax.sound.midi.MidiSystem;
/*      */ import javax.sound.midi.MidiUnavailableException;
/*      */ import javax.sound.midi.Receiver;
/*      */ import javax.sound.midi.Sequence;
/*      */ import javax.sound.midi.Sequencer;
/*      */ import javax.sound.midi.ShortMessage;
/*      */ import javax.sound.midi.Synthesizer;
/*      */ import javax.sound.midi.Transmitter;
/*      */ 
/*      */ public class MidiChannel
/*      */   implements MetaEventListener
/*      */ {
/*      */   private SoundSystemLogger logger;
/*      */   private FilenameURL filenameURL;
/*      */   private String sourcename;
/*      */   private static final int CHANGE_VOLUME = 7;
/*      */   private static final int END_OF_TRACK = 47;
/*      */   private static final boolean GET = false;
/*      */   private static final boolean SET = true;
/*      */   private static final boolean XXX = false;
/*  105 */   private Sequencer sequencer = null;
/*      */ 
/*  110 */   private Synthesizer synthesizer = null;
/*      */ 
/*  115 */   private MidiDevice synthDevice = null;
/*      */ 
/*  120 */   private Sequence sequence = null;
/*      */ 
/*  125 */   private boolean toLoop = true;
/*      */ 
/*  130 */   private float gain = 1.0F;
/*      */ 
/*  135 */   private boolean loading = true;
/*      */ 
/*  140 */   private LinkedList<FilenameURL> sequenceQueue = null;
/*      */ 
/*  145 */   private final Object sequenceQueueLock = new Object();
/*      */ 
/*  151 */   protected float fadeOutGain = -1.0F;
/*      */ 
/*  157 */   protected float fadeInGain = 1.0F;
/*      */ 
/*  162 */   protected long fadeOutMilis = 0L;
/*      */ 
/*  167 */   protected long fadeInMilis = 0L;
/*      */ 
/*  172 */   protected long lastFadeCheck = 0L;
/*      */ 
/*  177 */   private FadeThread fadeThread = null;
/*      */ 
/*      */   public MidiChannel(boolean toLoop, String sourcename, String filename)
/*      */   {
/*  188 */     loading(true, true);
/*      */ 
/*  191 */     this.logger = SoundSystemConfig.getLogger();
/*      */ 
/*  194 */     filenameURL(true, new FilenameURL(filename));
/*  195 */     sourcename(true, sourcename);
/*  196 */     setLooping(toLoop);
/*      */ 
/*  199 */     init();
/*      */ 
/*  202 */     loading(true, false);
/*      */   }
/*      */ 
/*      */   public MidiChannel(boolean toLoop, String sourcename, URL midiFile, String identifier)
/*      */   {
/*  218 */     loading(true, true);
/*      */ 
/*  221 */     this.logger = SoundSystemConfig.getLogger();
/*      */ 
/*  224 */     filenameURL(true, new FilenameURL(midiFile, identifier));
/*  225 */     sourcename(true, sourcename);
/*  226 */     setLooping(toLoop);
/*      */ 
/*  229 */     init();
/*      */ 
/*  232 */     loading(true, false);
/*      */   }
/*      */ 
/*      */   public MidiChannel(boolean toLoop, String sourcename, FilenameURL midiFilenameURL)
/*      */   {
/*  245 */     loading(true, true);
/*      */ 
/*  248 */     this.logger = SoundSystemConfig.getLogger();
/*      */ 
/*  251 */     filenameURL(true, midiFilenameURL);
/*  252 */     sourcename(true, sourcename);
/*  253 */     setLooping(toLoop);
/*      */ 
/*  256 */     init();
/*      */ 
/*  259 */     loading(true, false);
/*      */   }
/*      */ 
/*      */   private void init()
/*      */   {
/*  268 */     getSequencer();
/*      */ 
/*  271 */     setSequence(filenameURL(false, null).getURL());
/*      */ 
/*  274 */     getSynthesizer();
/*      */ 
/*  278 */     resetGain();
/*      */   }
/*      */ 
/*      */   public void cleanup()
/*      */   {
/*  286 */     loading(true, true);
/*  287 */     setLooping(true);
/*      */ 
/*  289 */     if (this.sequencer != null)
/*      */     {
/*      */       try
/*      */       {
/*  293 */         this.sequencer.stop();
/*  294 */         this.sequencer.close();
/*  295 */         this.sequencer.removeMetaEventListener(this);
/*      */       }
/*      */       catch (Exception e)
/*      */       {
/*      */       }
/*      */     }
/*  301 */     this.logger = null;
/*  302 */     this.sequencer = null;
/*  303 */     this.synthesizer = null;
/*  304 */     this.sequence = null;
/*      */ 
/*  306 */     synchronized (this.sequenceQueueLock)
/*      */     {
/*  308 */       if (this.sequenceQueue != null)
/*  309 */         this.sequenceQueue.clear();
/*  310 */       this.sequenceQueue = null;
/*      */     }
/*      */ 
/*  314 */     if (this.fadeThread != null)
/*      */     {
/*  316 */       boolean killException = false;
/*      */       try
/*      */       {
/*  319 */         this.fadeThread.kill();
/*  320 */         this.fadeThread.interrupt();
/*      */       }
/*      */       catch (Exception e)
/*      */       {
/*  324 */         killException = true;
/*      */       }
/*      */ 
/*  327 */       if (!killException)
/*      */       {
/*  330 */         for (int i = 0; i < 50; i++)
/*      */         {
/*  332 */           if (!this.fadeThread.alive()) break;
/*      */           try {
/*  334 */             Thread.sleep(100L);
/*      */           } catch (InterruptedException e) {
/*      */           }
/*      */         }
/*      */       }
/*  339 */       if ((killException) || (this.fadeThread.alive()))
/*      */       {
/*  341 */         errorMessage("MIDI fade effects thread did not die!");
/*  342 */         message("Ignoring errors... continuing clean-up.");
/*      */       }
/*      */     }
/*      */ 
/*  346 */     this.fadeThread = null;
/*      */ 
/*  348 */     loading(true, false);
/*      */   }
/*      */ 
/*      */   public void queueSound(FilenameURL filenameURL)
/*      */   {
/*  357 */     if (filenameURL == null)
/*      */     {
/*  359 */       errorMessage("Filename/URL not specified in method 'queueSound'");
/*  360 */       return;
/*      */     }
/*      */ 
/*  363 */     synchronized (this.sequenceQueueLock)
/*      */     {
/*  365 */       if (this.sequenceQueue == null)
/*  366 */         this.sequenceQueue = new LinkedList();
/*  367 */       this.sequenceQueue.add(filenameURL);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void dequeueSound(String filename)
/*      */   {
/*  379 */     if ((filename == null) || (filename.equals("")))
/*      */     {
/*  381 */       errorMessage("Filename not specified in method 'dequeueSound'");
/*  382 */       return;
/*      */     }
/*      */ 
/*  385 */     synchronized (this.sequenceQueueLock)
/*      */     {
/*  387 */       if (this.sequenceQueue != null)
/*      */       {
/*  389 */         ListIterator i = this.sequenceQueue.listIterator();
/*  390 */         while (i.hasNext())
/*      */         {
/*  392 */           if (((FilenameURL)i.next()).getFilename().equals(filename))
/*      */           {
/*  394 */             i.remove();
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void fadeOut(FilenameURL filenameURL, long milis)
/*      */   {
/*  415 */     if (milis < 0L)
/*      */     {
/*  417 */       errorMessage("Miliseconds may not be negative in method 'fadeOut'.");
/*      */ 
/*  419 */       return;
/*      */     }
/*      */ 
/*  422 */     this.fadeOutMilis = milis;
/*  423 */     this.fadeInMilis = 0L;
/*  424 */     this.fadeOutGain = 1.0F;
/*  425 */     this.lastFadeCheck = System.currentTimeMillis();
/*      */ 
/*  427 */     synchronized (this.sequenceQueueLock)
/*      */     {
/*  429 */       if (this.sequenceQueue != null) {
/*  430 */         this.sequenceQueue.clear();
/*      */       }
/*  432 */       if (filenameURL != null)
/*      */       {
/*  434 */         if (this.sequenceQueue == null)
/*  435 */           this.sequenceQueue = new LinkedList();
/*  436 */         this.sequenceQueue.add(filenameURL);
/*      */       }
/*      */     }
/*  439 */     if (this.fadeThread == null)
/*      */     {
/*  441 */       this.fadeThread = new FadeThread(null);
/*  442 */       this.fadeThread.start();
/*      */     }
/*  444 */     this.fadeThread.interrupt();
/*      */   }
/*      */ 
/*      */   public void fadeOutIn(FilenameURL filenameURL, long milisOut, long milisIn)
/*      */   {
/*  462 */     if (filenameURL == null)
/*      */     {
/*  464 */       errorMessage("Filename/URL not specified in method 'fadeOutIn'.");
/*  465 */       return;
/*      */     }
/*  467 */     if ((milisOut < 0L) || (milisIn < 0L))
/*      */     {
/*  469 */       errorMessage("Miliseconds may not be negative in method 'fadeOutIn'.");
/*      */ 
/*  471 */       return;
/*      */     }
/*      */ 
/*  474 */     this.fadeOutMilis = milisOut;
/*  475 */     this.fadeInMilis = milisIn;
/*  476 */     this.fadeOutGain = 1.0F;
/*  477 */     this.lastFadeCheck = System.currentTimeMillis();
/*      */ 
/*  479 */     synchronized (this.sequenceQueueLock)
/*      */     {
/*  481 */       if (this.sequenceQueue == null)
/*  482 */         this.sequenceQueue = new LinkedList();
/*  483 */       this.sequenceQueue.clear();
/*  484 */       this.sequenceQueue.add(filenameURL);
/*      */     }
/*  486 */     if (this.fadeThread == null)
/*      */     {
/*  488 */       this.fadeThread = new FadeThread(null);
/*  489 */       this.fadeThread.start();
/*      */     }
/*  491 */     this.fadeThread.interrupt();
/*      */   }
/*      */ 
/*      */   private synchronized boolean checkFadeOut()
/*      */   {
/*  503 */     if ((this.fadeOutGain == -1.0F) && (this.fadeInGain == 1.0F)) {
/*  504 */       return false;
/*      */     }
/*  506 */     long currentTime = System.currentTimeMillis();
/*  507 */     long milisPast = currentTime - this.lastFadeCheck;
/*  508 */     this.lastFadeCheck = currentTime;
/*      */ 
/*  510 */     if (this.fadeOutGain >= 0.0F)
/*      */     {
/*  512 */       if (this.fadeOutMilis == 0L)
/*      */       {
/*  514 */         this.fadeOutGain = 0.0F;
/*  515 */         this.fadeInGain = 0.0F;
/*  516 */         if (!incrementSequence())
/*  517 */           stop();
/*  518 */         rewind();
/*  519 */         resetGain();
/*  520 */         return false;
/*      */       }
/*      */ 
/*  524 */       float fadeOutReduction = (float)milisPast / (float)this.fadeOutMilis;
/*      */ 
/*  526 */       this.fadeOutGain -= fadeOutReduction;
/*  527 */       if (this.fadeOutGain <= 0.0F)
/*      */       {
/*  529 */         this.fadeOutGain = -1.0F;
/*  530 */         this.fadeInGain = 0.0F;
/*  531 */         if (!incrementSequence())
/*  532 */           stop();
/*  533 */         rewind();
/*  534 */         resetGain();
/*  535 */         return false;
/*      */       }
/*      */ 
/*  538 */       resetGain();
/*  539 */       return true;
/*      */     }
/*      */ 
/*  542 */     if (this.fadeInGain < 1.0F)
/*      */     {
/*  544 */       this.fadeOutGain = -1.0F;
/*  545 */       if (this.fadeInMilis == 0L)
/*      */       {
/*  547 */         this.fadeOutGain = -1.0F;
/*  548 */         this.fadeInGain = 1.0F;
/*      */       }
/*      */       else
/*      */       {
/*  552 */         float fadeInIncrease = (float)milisPast / (float)this.fadeInMilis;
/*  553 */         this.fadeInGain += fadeInIncrease;
/*  554 */         if (this.fadeInGain >= 1.0F)
/*      */         {
/*  556 */           this.fadeOutGain = -1.0F;
/*  557 */           this.fadeInGain = 1.0F;
/*      */         }
/*      */       }
/*  560 */       resetGain();
/*      */     }
/*      */ 
/*  563 */     return false;
/*      */   }
/*      */ 
/*      */   private boolean incrementSequence()
/*      */   {
/*  572 */     synchronized (this.sequenceQueueLock)
/*      */     {
/*  575 */       if ((this.sequenceQueue != null) && (this.sequenceQueue.size() > 0))
/*      */       {
/*  578 */         filenameURL(true, (FilenameURL)this.sequenceQueue.remove(0));
/*      */ 
/*  581 */         loading(true, true);
/*      */ 
/*  584 */         if (this.sequencer == null)
/*      */         {
/*  587 */           getSequencer();
/*      */         }
/*      */         else
/*      */         {
/*  592 */           this.sequencer.stop();
/*      */ 
/*  594 */           this.sequencer.setMicrosecondPosition(0L);
/*      */ 
/*  596 */           this.sequencer.removeMetaEventListener(this);
/*      */           try {
/*  598 */             Thread.sleep(100L); } catch (InterruptedException e) {
/*      */           }
/*      */         }
/*  601 */         if (this.sequencer == null)
/*      */         {
/*  603 */           errorMessage("Unable to set the sequence in method 'incrementSequence', because there wasn't a sequencer to use.");
/*      */ 
/*  608 */           loading(true, false);
/*      */ 
/*  611 */           return false;
/*      */         }
/*      */ 
/*  614 */         setSequence(filenameURL(false, null).getURL());
/*      */ 
/*  616 */         this.sequencer.start();
/*      */ 
/*  619 */         resetGain();
/*      */ 
/*  621 */         this.sequencer.addMetaEventListener(this);
/*      */ 
/*  624 */         loading(true, false);
/*      */ 
/*  627 */         return true;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  632 */     return false;
/*      */   }
/*      */ 
/*      */   public void play()
/*      */   {
/*  641 */     if (!loading())
/*      */     {
/*  644 */       if (this.sequencer == null) {
/*  645 */         return;
/*      */       }
/*      */ 
/*      */       try
/*      */       {
/*  650 */         this.sequencer.start();
/*      */ 
/*  652 */         this.sequencer.addMetaEventListener(this);
/*      */       }
/*      */       catch (Exception e)
/*      */       {
/*  656 */         errorMessage("Exception in method 'play'");
/*  657 */         printStackTrace(e);
/*  658 */         SoundSystemException sse = new SoundSystemException(e.getMessage());
/*      */ 
/*  660 */         SoundSystem.setException(sse);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void stop()
/*      */   {
/*  670 */     if (!loading())
/*      */     {
/*  673 */       if (this.sequencer == null) {
/*  674 */         return;
/*      */       }
/*      */ 
/*      */       try
/*      */       {
/*  679 */         this.sequencer.stop();
/*      */ 
/*  681 */         this.sequencer.setMicrosecondPosition(0L);
/*      */ 
/*  683 */         this.sequencer.removeMetaEventListener(this);
/*      */       }
/*      */       catch (Exception e)
/*      */       {
/*  687 */         errorMessage("Exception in method 'stop'");
/*  688 */         printStackTrace(e);
/*  689 */         SoundSystemException sse = new SoundSystemException(e.getMessage());
/*      */ 
/*  691 */         SoundSystem.setException(sse);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void pause()
/*      */   {
/*  701 */     if (!loading())
/*      */     {
/*  704 */       if (this.sequencer == null) {
/*  705 */         return;
/*      */       }
/*      */ 
/*      */       try
/*      */       {
/*  710 */         this.sequencer.stop();
/*      */       }
/*      */       catch (Exception e)
/*      */       {
/*  714 */         errorMessage("Exception in method 'pause'");
/*  715 */         printStackTrace(e);
/*  716 */         SoundSystemException sse = new SoundSystemException(e.getMessage());
/*      */ 
/*  718 */         SoundSystem.setException(sse);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void rewind()
/*      */   {
/*  728 */     if (!loading())
/*      */     {
/*  731 */       if (this.sequencer == null) {
/*  732 */         return;
/*      */       }
/*      */ 
/*      */       try
/*      */       {
/*  737 */         this.sequencer.setMicrosecondPosition(0L);
/*      */       }
/*      */       catch (Exception e)
/*      */       {
/*  741 */         errorMessage("Exception in method 'rewind'");
/*  742 */         printStackTrace(e);
/*  743 */         SoundSystemException sse = new SoundSystemException(e.getMessage());
/*      */ 
/*  745 */         SoundSystem.setException(sse);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setVolume(float value)
/*      */   {
/*  756 */     this.gain = value;
/*  757 */     resetGain();
/*      */   }
/*      */ 
/*      */   public float getVolume()
/*      */   {
/*  766 */     return this.gain;
/*      */   }
/*      */ 
/*      */   public void switchSource(boolean toLoop, String sourcename, String filename)
/*      */   {
/*  781 */     loading(true, true);
/*      */ 
/*  784 */     filenameURL(true, new FilenameURL(filename));
/*  785 */     sourcename(true, sourcename);
/*  786 */     setLooping(toLoop);
/*      */ 
/*  788 */     reset();
/*      */ 
/*  791 */     loading(true, false);
/*      */   }
/*      */ 
/*      */   public void switchSource(boolean toLoop, String sourcename, URL midiFile, String identifier)
/*      */   {
/*  809 */     loading(true, true);
/*      */ 
/*  812 */     filenameURL(true, new FilenameURL(midiFile, identifier));
/*  813 */     sourcename(true, sourcename);
/*  814 */     setLooping(toLoop);
/*      */ 
/*  816 */     reset();
/*      */ 
/*  819 */     loading(true, false);
/*      */   }
/*      */ 
/*      */   public void switchSource(boolean toLoop, String sourcename, FilenameURL filenameURL)
/*      */   {
/*  834 */     loading(true, true);
/*      */ 
/*  837 */     filenameURL(true, filenameURL);
/*  838 */     sourcename(true, sourcename);
/*  839 */     setLooping(toLoop);
/*      */ 
/*  841 */     reset();
/*      */ 
/*  844 */     loading(true, false);
/*      */   }
/*      */ 
/*      */   private void reset()
/*      */   {
/*  852 */     synchronized (this.sequenceQueueLock)
/*      */     {
/*  854 */       if (this.sequenceQueue != null) {
/*  855 */         this.sequenceQueue.clear();
/*      */       }
/*      */     }
/*      */ 
/*  859 */     if (this.sequencer == null)
/*      */     {
/*  862 */       getSequencer();
/*      */     }
/*      */     else
/*      */     {
/*  867 */       this.sequencer.stop();
/*      */ 
/*  869 */       this.sequencer.setMicrosecondPosition(0L);
/*      */ 
/*  871 */       this.sequencer.removeMetaEventListener(this);
/*      */       try {
/*  873 */         Thread.sleep(100L); } catch (InterruptedException e) {
/*      */       }
/*      */     }
/*  876 */     if (this.sequencer == null)
/*      */     {
/*  878 */       errorMessage("Unable to set the sequence in method 'reset', because there wasn't a sequencer to use.");
/*      */ 
/*  881 */       return;
/*      */     }
/*      */ 
/*  885 */     setSequence(filenameURL(false, null).getURL());
/*      */ 
/*  887 */     this.sequencer.start();
/*      */ 
/*  890 */     resetGain();
/*      */ 
/*  892 */     this.sequencer.addMetaEventListener(this);
/*      */   }
/*      */ 
/*      */   public void setLooping(boolean value)
/*      */   {
/*  901 */     toLoop(true, value);
/*      */   }
/*      */ 
/*      */   public boolean getLooping()
/*      */   {
/*  910 */     return toLoop(false, false);
/*      */   }
/*      */ 
/*      */   private synchronized boolean toLoop(boolean action, boolean value)
/*      */   {
/*  921 */     if (action == true)
/*  922 */       this.toLoop = value;
/*  923 */     return this.toLoop;
/*      */   }
/*      */ 
/*      */   public boolean loading()
/*      */   {
/*  931 */     return loading(false, false);
/*      */   }
/*      */ 
/*      */   private synchronized boolean loading(boolean action, boolean value)
/*      */   {
/*  942 */     if (action == true)
/*  943 */       this.loading = value;
/*  944 */     return this.loading;
/*      */   }
/*      */ 
/*      */   public void setSourcename(String value)
/*      */   {
/*  953 */     sourcename(true, value);
/*      */   }
/*      */ 
/*      */   public String getSourcename()
/*      */   {
/*  962 */     return sourcename(false, null);
/*      */   }
/*      */ 
/*      */   private synchronized String sourcename(boolean action, String value)
/*      */   {
/*  973 */     if (action == true)
/*  974 */       this.sourcename = value;
/*  975 */     return this.sourcename;
/*      */   }
/*      */ 
/*      */   public void setFilenameURL(FilenameURL value)
/*      */   {
/*  984 */     filenameURL(true, value);
/*      */   }
/*      */ 
/*      */   public String getFilename()
/*      */   {
/*  993 */     return filenameURL(false, null).getFilename();
/*      */   }
/*      */ 
/*      */   public FilenameURL getFilenameURL()
/*      */   {
/* 1002 */     return filenameURL(false, null);
/*      */   }
/*      */ 
/*      */   private synchronized FilenameURL filenameURL(boolean action, FilenameURL value)
/*      */   {
/* 1014 */     if (action == true)
/* 1015 */       this.filenameURL = value;
/* 1016 */     return this.filenameURL;
/*      */   }
/*      */ 
/*      */   public void meta(MetaMessage message)
/*      */   {
/* 1025 */     if (message.getType() == 47)
/*      */     {
/* 1028 */       SoundSystemConfig.notifyEOS(this.sourcename, this.sequenceQueue.size());
/*      */ 
/* 1031 */       if (this.toLoop)
/*      */       {
/* 1035 */         if (!checkFadeOut())
/*      */         {
/* 1039 */           if (!incrementSequence())
/*      */           {
/*      */             try
/*      */             {
/* 1044 */               this.sequencer.setMicrosecondPosition(0L);
/* 1045 */               this.sequencer.start();
/*      */ 
/* 1047 */               resetGain();
/*      */             } catch (Exception e) {
/*      */             }
/*      */           }
/*      */         }
/* 1052 */         else if (this.sequencer != null)
/*      */         {
/*      */           try
/*      */           {
/* 1057 */             this.sequencer.setMicrosecondPosition(0L);
/* 1058 */             this.sequencer.start();
/*      */ 
/* 1060 */             resetGain();
/*      */           }
/*      */           catch (Exception e)
/*      */           {
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/* 1068 */       else if (!checkFadeOut())
/*      */       {
/* 1070 */         if (!incrementSequence())
/*      */         {
/*      */           try
/*      */           {
/* 1075 */             this.sequencer.stop();
/*      */ 
/* 1077 */             this.sequencer.setMicrosecondPosition(0L);
/*      */ 
/* 1079 */             this.sequencer.removeMetaEventListener(this);
/*      */           }
/*      */           catch (Exception e)
/*      */           {
/*      */           }
/*      */         }
/*      */       }
/*      */       else
/*      */         try
/*      */         {
/* 1089 */           this.sequencer.stop();
/*      */ 
/* 1091 */           this.sequencer.setMicrosecondPosition(0L);
/*      */ 
/* 1093 */           this.sequencer.removeMetaEventListener(this);
/*      */         }
/*      */         catch (Exception e)
/*      */         {
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void resetGain()
/*      */   {
/* 1107 */     if (this.gain < 0.0F)
/* 1108 */       this.gain = 0.0F;
/* 1109 */     if (this.gain > 1.0F) {
/* 1110 */       this.gain = 1.0F;
/*      */     }
/* 1112 */     int midiVolume = (int)(this.gain * SoundSystemConfig.getMasterGain() * Math.abs(this.fadeOutGain) * this.fadeInGain * 127.0F);
/*      */ 
/* 1115 */     if (this.synthesizer != null)
/*      */     {
/* 1117 */       javax.sound.midi.MidiChannel[] channels = this.synthesizer.getChannels();
/* 1118 */       for (int c = 0; (channels != null) && (c < channels.length); c++)
/*      */       {
/* 1120 */         channels[c].controlChange(7, midiVolume);
/*      */       }
/*      */     }
/* 1123 */     else if (this.synthDevice != null)
/*      */     {
/*      */       try
/*      */       {
/* 1127 */         ShortMessage volumeMessage = new ShortMessage();
/* 1128 */         for (int i = 0; i < 16; i++)
/*      */         {
/* 1130 */           volumeMessage.setMessage(176, i, 7, midiVolume);
/*      */ 
/* 1132 */           this.synthDevice.getReceiver().send(volumeMessage, -1L);
/*      */         }
/*      */       }
/*      */       catch (Exception e)
/*      */       {
/* 1137 */         errorMessage("Error resetting gain on MIDI device");
/* 1138 */         printStackTrace(e);
/*      */       }
/*      */     }
/* 1141 */     else if ((this.sequencer != null) && ((this.sequencer instanceof Synthesizer)))
/*      */     {
/* 1143 */       this.synthesizer = ((Synthesizer)this.sequencer);
/* 1144 */       javax.sound.midi.MidiChannel[] channels = this.synthesizer.getChannels();
/* 1145 */       for (int c = 0; (channels != null) && (c < channels.length); c++)
/*      */       {
/* 1147 */         channels[c].controlChange(7, midiVolume);
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*      */       try
/*      */       {
/* 1154 */         Receiver receiver = MidiSystem.getReceiver();
/* 1155 */         ShortMessage volumeMessage = new ShortMessage();
/* 1156 */         for (int c = 0; c < 16; c++)
/*      */         {
/* 1158 */           volumeMessage.setMessage(176, c, 7, midiVolume);
/*      */ 
/* 1160 */           receiver.send(volumeMessage, -1L);
/*      */         }
/*      */       }
/*      */       catch (Exception e)
/*      */       {
/* 1165 */         errorMessage("Error resetting gain on default receiver");
/* 1166 */         printStackTrace(e);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void getSequencer()
/*      */   {
/*      */     try
/*      */     {
/* 1180 */       this.sequencer = MidiSystem.getSequencer();
/* 1181 */       if (this.sequencer != null)
/*      */       {
/*      */         try
/*      */         {
/* 1185 */           this.sequencer.getTransmitter();
/*      */         }
/*      */         catch (MidiUnavailableException mue)
/*      */         {
/* 1189 */           message("Unable to get a transmitter from the default MIDI sequencer");
/*      */         }
/*      */ 
/* 1192 */         this.sequencer.open();
/*      */       }
/*      */     }
/*      */     catch (MidiUnavailableException mue)
/*      */     {
/* 1197 */       message("Unable to open the default MIDI sequencer");
/* 1198 */       this.sequencer = null;
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/* 1202 */       if ((e instanceof InterruptedException))
/*      */       {
/* 1204 */         message("Caught InterruptedException while attempting to open the default MIDI sequencer.  Trying again.");
/*      */ 
/* 1206 */         this.sequencer = null;
/*      */       }
/*      */       try
/*      */       {
/* 1210 */         this.sequencer = MidiSystem.getSequencer();
/* 1211 */         if (this.sequencer != null)
/*      */         {
/*      */           try
/*      */           {
/* 1215 */             this.sequencer.getTransmitter();
/*      */           }
/*      */           catch (MidiUnavailableException mue)
/*      */           {
/* 1219 */             message("Unable to get a transmitter from the default MIDI sequencer");
/*      */           }
/*      */ 
/* 1222 */           this.sequencer.open();
/*      */         }
/*      */       }
/*      */       catch (MidiUnavailableException mue)
/*      */       {
/* 1227 */         message("Unable to open the default MIDI sequencer");
/* 1228 */         this.sequencer = null;
/*      */       }
/*      */       catch (Exception e2)
/*      */       {
/* 1232 */         message("Unknown error opening the default MIDI sequencer");
/* 1233 */         this.sequencer = null;
/*      */       }
/*      */     }
/*      */ 
/* 1237 */     if (this.sequencer == null)
/* 1238 */       this.sequencer = openSequencer("Real Time Sequencer");
/* 1239 */     if (this.sequencer == null)
/* 1240 */       this.sequencer = openSequencer("Java Sound Sequencer");
/* 1241 */     if (this.sequencer == null)
/*      */     {
/* 1243 */       errorMessage("Failed to find an available MIDI sequencer");
/* 1244 */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void setSequence(URL midiSource)
/*      */   {
/* 1256 */     if (this.sequencer == null)
/*      */     {
/* 1258 */       errorMessage("Unable to update the sequence in method 'setSequence', because variable 'sequencer' is null");
/*      */ 
/* 1261 */       return;
/*      */     }
/*      */ 
/* 1264 */     if (midiSource == null)
/*      */     {
/* 1266 */       errorMessage("Unable to load Midi file in method 'setSequence'.");
/* 1267 */       return;
/*      */     }
/*      */ 
/*      */     try
/*      */     {
/* 1272 */       this.sequence = MidiSystem.getSequence(midiSource);
/*      */     }
/*      */     catch (IOException ioe)
/*      */     {
/* 1276 */       errorMessage("Input failed while reading from MIDI file in method 'setSequence'.");
/*      */ 
/* 1278 */       printStackTrace(ioe);
/* 1279 */       return;
/*      */     }
/*      */     catch (InvalidMidiDataException imde)
/*      */     {
/* 1283 */       errorMessage("Invalid MIDI data encountered, or not a MIDI file in method 'setSequence' (1).");
/*      */ 
/* 1285 */       printStackTrace(imde);
/* 1286 */       return;
/*      */     }
/* 1288 */     if (this.sequence == null)
/*      */     {
/* 1290 */       errorMessage("MidiSystem 'getSequence' method returned null in method 'setSequence'.");
/*      */     }
/*      */     else
/*      */     {
/*      */       try
/*      */       {
/* 1297 */         this.sequencer.setSequence(this.sequence);
/*      */       }
/*      */       catch (InvalidMidiDataException imde)
/*      */       {
/* 1301 */         errorMessage("Invalid MIDI data encountered, or not a MIDI file in method 'setSequence' (2).");
/*      */ 
/* 1303 */         printStackTrace(imde);
/* 1304 */         return;
/*      */       }
/*      */       catch (Exception e)
/*      */       {
/* 1308 */         errorMessage("Problem setting sequence from MIDI file in method 'setSequence'.");
/*      */ 
/* 1310 */         printStackTrace(e);
/* 1311 */         return;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void getSynthesizer()
/*      */   {
/* 1324 */     if (this.sequencer == null)
/*      */     {
/* 1326 */       errorMessage("Unable to load a Synthesizer in method 'getSynthesizer', because variable 'sequencer' is null");
/*      */ 
/* 1329 */       return;
/*      */     }
/*      */ 
/* 1333 */     String overrideMIDISynthesizer = SoundSystemConfig.getOverrideMIDISynthesizer();
/*      */ 
/* 1335 */     if ((overrideMIDISynthesizer != null) && (!overrideMIDISynthesizer.equals("")))
/*      */     {
/* 1339 */       this.synthDevice = openMidiDevice(overrideMIDISynthesizer);
/*      */ 
/* 1341 */       if (this.synthDevice != null)
/*      */       {
/*      */         try
/*      */         {
/* 1346 */           this.sequencer.getTransmitter().setReceiver(this.synthDevice.getReceiver());
/*      */ 
/* 1349 */           return;
/*      */         }
/*      */         catch (MidiUnavailableException mue)
/*      */         {
/* 1354 */           errorMessage("Unable to link sequencer transmitter with receiver for MIDI device '" + overrideMIDISynthesizer + "'");
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1364 */     if ((this.sequencer instanceof Synthesizer))
/*      */     {
/* 1366 */       this.synthesizer = ((Synthesizer)this.sequencer);
/*      */     }
/*      */     else
/*      */     {
/*      */       try
/*      */       {
/* 1373 */         this.synthesizer = MidiSystem.getSynthesizer();
/* 1374 */         this.synthesizer.open();
/*      */       }
/*      */       catch (MidiUnavailableException mue)
/*      */       {
/* 1378 */         message("Unable to open the default synthesizer");
/* 1379 */         this.synthesizer = null;
/*      */       }
/*      */ 
/* 1383 */       if (this.synthesizer == null)
/*      */       {
/* 1386 */         this.synthDevice = openMidiDevice("Java Sound Synthesizer");
/* 1387 */         if (this.synthDevice == null)
/* 1388 */           this.synthDevice = openMidiDevice("Microsoft GS Wavetable");
/* 1389 */         if (this.synthDevice == null)
/* 1390 */           this.synthDevice = openMidiDevice("Gervill");
/* 1391 */         if (this.synthDevice == null)
/*      */         {
/* 1394 */           errorMessage("Failed to find an available MIDI synthesizer");
/*      */ 
/* 1396 */           return;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1401 */       if (this.synthesizer == null)
/*      */       {
/*      */         try
/*      */         {
/* 1406 */           this.sequencer.getTransmitter().setReceiver(this.synthDevice.getReceiver());
/*      */         }
/*      */         catch (MidiUnavailableException mue)
/*      */         {
/* 1411 */           errorMessage("Unable to link sequencer transmitter with MIDI device receiver");
/*      */         }
/*      */ 
/*      */       }
/* 1418 */       else if (this.synthesizer.getDefaultSoundbank() == null)
/*      */       {
/*      */         try
/*      */         {
/* 1423 */           this.sequencer.getTransmitter().setReceiver(MidiSystem.getReceiver());
/*      */         }
/*      */         catch (MidiUnavailableException mue)
/*      */         {
/* 1428 */           errorMessage("Unable to link sequencer transmitter with default receiver");
/*      */         }
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */         try
/*      */         {
/* 1437 */           this.sequencer.getTransmitter().setReceiver(this.synthesizer.getReceiver());
/*      */         }
/*      */         catch (MidiUnavailableException mue)
/*      */         {
/* 1442 */           errorMessage("Unable to link sequencer transmitter with synthesizer receiver");
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private Sequencer openSequencer(String containsString)
/*      */   {
/* 1458 */     Sequencer s = null;
/* 1459 */     s = (Sequencer)openMidiDevice(containsString);
/* 1460 */     if (s == null)
/* 1461 */       return null;
/*      */     try
/*      */     {
/* 1464 */       s.getTransmitter();
/*      */     }
/*      */     catch (MidiUnavailableException mue)
/*      */     {
/* 1468 */       message("    Unable to get a transmitter from this sequencer");
/* 1469 */       s = null;
/* 1470 */       return null;
/*      */     }
/*      */ 
/* 1473 */     return s;
/*      */   }
/*      */ 
/*      */   private MidiDevice openMidiDevice(String containsString)
/*      */   {
/* 1484 */     message("Searching for MIDI device with name containing '" + containsString + "'");
/*      */ 
/* 1486 */     MidiDevice device = null;
/* 1487 */     MidiDevice.Info[] midiDevices = MidiSystem.getMidiDeviceInfo();
/* 1488 */     for (int i = 0; i < midiDevices.length; i++)
/*      */     {
/* 1490 */       device = null;
/*      */       try
/*      */       {
/* 1493 */         device = MidiSystem.getMidiDevice(midiDevices[i]);
/*      */       }
/*      */       catch (MidiUnavailableException e)
/*      */       {
/* 1497 */         message("    Problem in method 'getMidiDevice':  MIDIUnavailableException was thrown");
/*      */ 
/* 1499 */         device = null;
/*      */       }
/* 1501 */       if ((device != null) && (midiDevices[i].getName().contains(containsString)))
/*      */       {
/* 1504 */         message("    Found MIDI device named '" + midiDevices[i].getName() + "'");
/*      */ 
/* 1506 */         if ((device instanceof Synthesizer))
/* 1507 */           message("        *this is a Synthesizer instance");
/* 1508 */         if ((device instanceof Sequencer))
/* 1509 */           message("        *this is a Sequencer instance");
/*      */         try
/*      */         {
/* 1512 */           device.open();
/*      */         }
/*      */         catch (MidiUnavailableException mue)
/*      */         {
/* 1516 */           message("    Unable to open this MIDI device");
/* 1517 */           device = null;
/*      */         }
/* 1519 */         return device;
/*      */       }
/*      */     }
/* 1522 */     message("    MIDI device not found");
/* 1523 */     return null;
/*      */   }
/*      */ 
/*      */   protected void message(String message)
/*      */   {
/* 1532 */     this.logger.message(message, 0);
/*      */   }
/*      */ 
/*      */   protected void importantMessage(String message)
/*      */   {
/* 1541 */     this.logger.importantMessage(message, 0);
/*      */   }
/*      */ 
/*      */   protected boolean errorCheck(boolean error, String message)
/*      */   {
/* 1552 */     return this.logger.errorCheck(error, "MidiChannel", message, 0);
/*      */   }
/*      */ 
/*      */   protected void errorMessage(String message)
/*      */   {
/* 1561 */     this.logger.errorMessage("MidiChannel", message, 0);
/*      */   }
/*      */ 
/*      */   protected void printStackTrace(Exception e)
/*      */   {
/* 1570 */     this.logger.printStackTrace(e, 1);
/*      */   }
/*      */ 
/*      */   private class FadeThread extends SimpleThread
/*      */   {
/*      */     private FadeThread()
/*      */     {
/*      */     }
/*      */ 
/*      */     public void run()
/*      */     {
/* 1586 */       while (!dying())
/*      */       {
/* 1589 */         if ((MidiChannel.this.fadeOutGain == -1.0F) && (MidiChannel.this.fadeInGain == 1.0F))
/* 1590 */           snooze(3600000L);
/* 1591 */         MidiChannel.this.checkFadeOut();
/*      */ 
/* 1593 */         snooze(50L);
/*      */       }
/*      */ 
/* 1596 */       cleanup();
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.MidiChannel
 * JD-Core Version:    0.6.2
 */