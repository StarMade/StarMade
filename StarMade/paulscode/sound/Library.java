/*      */ package paulscode.sound;
/*      */ 
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import javax.sound.sampled.AudioFormat;
/*      */ 
/*      */ public class Library
/*      */ {
/*      */   private SoundSystemLogger logger;
/*      */   protected ListenerData listener;
/*   68 */   protected HashMap<String, SoundBuffer> bufferMap = null;
/*      */   protected HashMap<String, Source> sourceMap;
/*      */   private MidiChannel midiChannel;
/*      */   protected List<Channel> streamingChannels;
/*      */   protected List<Channel> normalChannels;
/*      */   private String[] streamingChannelSourceNames;
/*      */   private String[] normalChannelSourceNames;
/*  103 */   private int nextStreamingChannel = 0;
/*      */ 
/*  108 */   private int nextNormalChannel = 0;
/*      */   protected StreamThread streamThread;
/*  118 */   protected boolean reverseByteOrder = false;
/*      */ 
/*      */   public Library()
/*      */     throws SoundSystemException
/*      */   {
/*  129 */     this.logger = SoundSystemConfig.getLogger();
/*      */ 
/*  132 */     this.bufferMap = new HashMap();
/*      */ 
/*  135 */     this.sourceMap = new HashMap();
/*      */ 
/*  137 */     this.listener = new ListenerData(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F);
/*      */ 
/*  142 */     this.streamingChannels = new LinkedList();
/*  143 */     this.normalChannels = new LinkedList();
/*  144 */     this.streamingChannelSourceNames = new String[SoundSystemConfig.getNumberStreamingChannels()];
/*      */ 
/*  146 */     this.normalChannelSourceNames = new String[SoundSystemConfig.getNumberNormalChannels()];
/*      */ 
/*  149 */     this.streamThread = new StreamThread();
/*  150 */     this.streamThread.start();
/*      */   }
/*      */ 
/*      */   public void cleanup()
/*      */   {
/*  166 */     this.streamThread.kill();
/*  167 */     this.streamThread.interrupt();
/*      */ 
/*  170 */     for (int i = 0; i < 50; i++)
/*      */     {
/*  172 */       if (!this.streamThread.alive())
/*      */         break;
/*      */       try
/*      */       {
/*  176 */         Thread.sleep(100L);
/*      */       }
/*      */       catch (Exception e)
/*      */       {
/*      */       }
/*      */     }
/*  182 */     if (this.streamThread.alive())
/*      */     {
/*  184 */       errorMessage("Stream thread did not die!");
/*  185 */       message("Ignoring errors... continuing clean-up.");
/*      */     }
/*      */ 
/*  188 */     if (this.midiChannel != null)
/*      */     {
/*  190 */       this.midiChannel.cleanup();
/*  191 */       this.midiChannel = null;
/*      */     }
/*      */ 
/*  194 */     Channel channel = null;
/*  195 */     if (this.streamingChannels != null)
/*      */     {
/*  197 */       while (!this.streamingChannels.isEmpty())
/*      */       {
/*  199 */         channel = (Channel)this.streamingChannels.remove(0);
/*  200 */         channel.close();
/*  201 */         channel.cleanup();
/*  202 */         channel = null;
/*      */       }
/*  204 */       this.streamingChannels.clear();
/*  205 */       this.streamingChannels = null;
/*      */     }
/*  207 */     if (this.normalChannels != null)
/*      */     {
/*  209 */       while (!this.normalChannels.isEmpty())
/*      */       {
/*  211 */         channel = (Channel)this.normalChannels.remove(0);
/*  212 */         channel.close();
/*  213 */         channel.cleanup();
/*  214 */         channel = null;
/*      */       }
/*  216 */       this.normalChannels.clear();
/*  217 */       this.normalChannels = null;
/*      */     }
/*      */ 
/*  220 */     Set keys = this.sourceMap.keySet();
/*  221 */     Iterator iter = keys.iterator();
/*      */ 
/*  226 */     while (iter.hasNext())
/*      */     {
/*  228 */       String sourcename = (String)iter.next();
/*  229 */       Source source = (Source)this.sourceMap.get(sourcename);
/*  230 */       if (source != null)
/*  231 */         source.cleanup();
/*      */     }
/*  233 */     this.sourceMap.clear();
/*  234 */     this.sourceMap = null;
/*      */ 
/*  236 */     this.listener = null;
/*  237 */     this.streamThread = null;
/*      */   }
/*      */ 
/*      */   public void init()
/*      */     throws SoundSystemException
/*      */   {
/*  245 */     Channel channel = null;
/*      */ 
/*  248 */     for (int x = 0; x < SoundSystemConfig.getNumberStreamingChannels(); x++)
/*      */     {
/*  250 */       channel = createChannel(1);
/*  251 */       if (channel == null)
/*      */         break;
/*  253 */       this.streamingChannels.add(channel);
/*      */     }
/*      */ 
/*  256 */     for (int x = 0; x < SoundSystemConfig.getNumberNormalChannels(); x++)
/*      */     {
/*  258 */       channel = createChannel(0);
/*  259 */       if (channel == null)
/*      */         break;
/*  261 */       this.normalChannels.add(channel);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static boolean libraryCompatible()
/*      */   {
/*  271 */     return true;
/*      */   }
/*      */ 
/*      */   protected Channel createChannel(int type)
/*      */   {
/*  283 */     return new Channel(type);
/*      */   }
/*      */ 
/*      */   public boolean loadSound(FilenameURL filenameURL)
/*      */   {
/*  293 */     return true;
/*      */   }
/*      */ 
/*      */   public boolean loadSound(SoundBuffer buffer, String identifier)
/*      */   {
/*  306 */     return true;
/*      */   }
/*      */ 
/*      */   public LinkedList<String> getAllLoadedFilenames()
/*      */   {
/*  315 */     LinkedList filenames = new LinkedList();
/*  316 */     Set keys = this.bufferMap.keySet();
/*  317 */     Iterator iter = keys.iterator();
/*      */ 
/*  320 */     while (iter.hasNext())
/*      */     {
/*  322 */       filenames.add(iter.next());
/*      */     }
/*      */ 
/*  325 */     return filenames;
/*      */   }
/*      */ 
/*      */   public LinkedList<String> getAllSourcenames()
/*      */   {
/*  334 */     LinkedList sourcenames = new LinkedList();
/*  335 */     Set keys = this.sourceMap.keySet();
/*  336 */     Iterator iter = keys.iterator();
/*      */ 
/*  338 */     if (this.midiChannel != null) {
/*  339 */       sourcenames.add(this.midiChannel.getSourcename());
/*      */     }
/*      */ 
/*  342 */     while (iter.hasNext())
/*      */     {
/*  344 */       sourcenames.add(iter.next());
/*      */     }
/*      */ 
/*  347 */     return sourcenames;
/*      */   }
/*      */ 
/*      */   public void unloadSound(String filename)
/*      */   {
/*  359 */     this.bufferMap.remove(filename);
/*      */   }
/*      */ 
/*      */   public void rawDataStream(AudioFormat audioFormat, boolean priority, String sourcename, float posX, float posY, float posZ, int attModel, float distOrRoll)
/*      */   {
/*  376 */     this.sourceMap.put(sourcename, new Source(audioFormat, priority, sourcename, posX, posY, posZ, attModel, distOrRoll));
/*      */   }
/*      */ 
/*      */   public void newSource(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float posX, float posY, float posZ, int attModel, float distOrRoll)
/*      */   {
/*  399 */     this.sourceMap.put(sourcename, new Source(priority, toStream, toLoop, sourcename, filenameURL, null, posX, posY, posZ, attModel, distOrRoll, false));
/*      */   }
/*      */ 
/*      */   public void quickPlay(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float posX, float posY, float posZ, int attModel, float distOrRoll, boolean tmp)
/*      */   {
/*  424 */     this.sourceMap.put(sourcename, new Source(priority, toStream, toLoop, sourcename, filenameURL, null, posX, posY, posZ, attModel, distOrRoll, tmp));
/*      */   }
/*      */ 
/*      */   public void setTemporary(String sourcename, boolean temporary)
/*      */   {
/*  439 */     Source mySource = (Source)this.sourceMap.get(sourcename);
/*  440 */     if (mySource != null)
/*  441 */       mySource.setTemporary(temporary);
/*      */   }
/*      */ 
/*      */   public void setPosition(String sourcename, float x, float y, float z)
/*      */   {
/*  453 */     Source mySource = (Source)this.sourceMap.get(sourcename);
/*  454 */     if (mySource != null)
/*  455 */       mySource.setPosition(x, y, z);
/*      */   }
/*      */ 
/*      */   public void setPriority(String sourcename, boolean pri)
/*      */   {
/*  466 */     Source mySource = (Source)this.sourceMap.get(sourcename);
/*  467 */     if (mySource != null)
/*  468 */       mySource.setPriority(pri);
/*      */   }
/*      */ 
/*      */   public void setLooping(String sourcename, boolean lp)
/*      */   {
/*  479 */     Source mySource = (Source)this.sourceMap.get(sourcename);
/*  480 */     if (mySource != null)
/*  481 */       mySource.setLooping(lp);
/*      */   }
/*      */ 
/*      */   public void setAttenuation(String sourcename, int model)
/*      */   {
/*  491 */     Source mySource = (Source)this.sourceMap.get(sourcename);
/*  492 */     if (mySource != null)
/*  493 */       mySource.setAttenuation(model);
/*      */   }
/*      */ 
/*      */   public void setDistOrRoll(String sourcename, float dr)
/*      */   {
/*  503 */     Source mySource = (Source)this.sourceMap.get(sourcename);
/*  504 */     if (mySource != null)
/*  505 */       mySource.setDistOrRoll(dr);
/*      */   }
/*      */ 
/*      */   public void setVelocity(String sourcename, float x, float y, float z)
/*      */   {
/*  517 */     Source mySource = (Source)this.sourceMap.get(sourcename);
/*  518 */     if (mySource != null)
/*  519 */       mySource.setVelocity(x, y, z);
/*      */   }
/*      */ 
/*      */   public void setListenerVelocity(float x, float y, float z)
/*      */   {
/*  530 */     this.listener.setVelocity(x, y, z);
/*      */   }
/*      */ 
/*      */   public void dopplerChanged()
/*      */   {
/*      */   }
/*      */ 
/*      */   public float millisecondsPlayed(String sourcename)
/*      */   {
/*  545 */     if ((sourcename == null) || (sourcename.equals("")))
/*      */     {
/*  547 */       errorMessage("Sourcename not specified in method 'millisecondsPlayed'");
/*      */ 
/*  549 */       return -1.0F;
/*      */     }
/*      */ 
/*  552 */     if (midiSourcename(sourcename))
/*      */     {
/*  554 */       errorMessage("Unable to calculate milliseconds for MIDI source.");
/*  555 */       return -1.0F;
/*      */     }
/*      */ 
/*  559 */     Source source = (Source)this.sourceMap.get(sourcename);
/*  560 */     if (source == null)
/*      */     {
/*  562 */       errorMessage("Source '" + sourcename + "' not found in " + "method 'millisecondsPlayed'");
/*      */     }
/*      */ 
/*  565 */     return source.millisecondsPlayed();
/*      */   }
/*      */ 
/*      */   public int feedRawAudioData(String sourcename, byte[] buffer)
/*      */   {
/*  578 */     if ((sourcename == null) || (sourcename.equals("")))
/*      */     {
/*  580 */       errorMessage("Sourcename not specified in method 'feedRawAudioData'");
/*      */ 
/*  582 */       return -1;
/*      */     }
/*      */ 
/*  585 */     if (midiSourcename(sourcename))
/*      */     {
/*  587 */       errorMessage("Raw audio data can not be fed to the MIDI channel.");
/*      */ 
/*  589 */       return -1;
/*      */     }
/*      */ 
/*  593 */     Source source = (Source)this.sourceMap.get(sourcename);
/*  594 */     if (source == null)
/*      */     {
/*  596 */       errorMessage("Source '" + sourcename + "' not found in " + "method 'feedRawAudioData'");
/*      */     }
/*      */ 
/*  599 */     return feedRawAudioData(source, buffer);
/*      */   }
/*      */ 
/*      */   public int feedRawAudioData(Source source, byte[] buffer)
/*      */   {
/*  613 */     if (source == null)
/*      */     {
/*  615 */       errorMessage("Source parameter null in method 'feedRawAudioData'");
/*      */ 
/*  617 */       return -1;
/*      */     }
/*  619 */     if (!source.toStream)
/*      */     {
/*  621 */       errorMessage("Only a streaming source may be specified in method 'feedRawAudioData'");
/*      */ 
/*  623 */       return -1;
/*      */     }
/*  625 */     if (!source.rawDataStream)
/*      */     {
/*  627 */       errorMessage("Streaming source already associated with a file or URL in method'feedRawAudioData'");
/*      */ 
/*  629 */       return -1;
/*      */     }
/*      */ 
/*  632 */     if ((!source.playing()) || (source.channel == null))
/*      */     {
/*      */       Channel channel;
/*      */       Channel channel;
/*  635 */       if ((source.channel != null) && (source.channel.attachedSource == source))
/*      */       {
/*  637 */         channel = source.channel;
/*      */       }
/*  639 */       else channel = getNextChannel(source);
/*      */ 
/*  641 */       int processed = source.feedRawAudioData(channel, buffer);
/*  642 */       channel.attachedSource = source;
/*  643 */       this.streamThread.watch(source);
/*  644 */       this.streamThread.interrupt();
/*  645 */       return processed;
/*      */     }
/*      */ 
/*  648 */     return source.feedRawAudioData(source.channel, buffer);
/*      */   }
/*      */ 
/*      */   public void play(String sourcename)
/*      */   {
/*  657 */     if ((sourcename == null) || (sourcename.equals("")))
/*      */     {
/*  659 */       errorMessage("Sourcename not specified in method 'play'");
/*  660 */       return;
/*      */     }
/*      */ 
/*  663 */     if (midiSourcename(sourcename))
/*      */     {
/*  665 */       this.midiChannel.play();
/*      */     }
/*      */     else
/*      */     {
/*  669 */       Source source = (Source)this.sourceMap.get(sourcename);
/*  670 */       if (source == null)
/*      */       {
/*  672 */         errorMessage("Source '" + sourcename + "' not found in " + "method 'play'");
/*      */       }
/*      */ 
/*  675 */       play(source);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void play(Source source)
/*      */   {
/*  685 */     if (source == null) {
/*  686 */       return;
/*      */     }
/*      */ 
/*  690 */     if (source.rawDataStream) {
/*  691 */       return;
/*      */     }
/*  693 */     if (!source.active()) {
/*  694 */       return;
/*      */     }
/*  696 */     if (!source.playing())
/*      */     {
/*  698 */       Channel channel = getNextChannel(source);
/*      */ 
/*  700 */       if ((source != null) && (channel != null))
/*      */       {
/*  702 */         if ((source.channel != null) && (source.channel.attachedSource != source))
/*      */         {
/*  704 */           source.channel = null;
/*  705 */         }channel.attachedSource = source;
/*  706 */         source.play(channel);
/*  707 */         if (source.toStream)
/*      */         {
/*  709 */           this.streamThread.watch(source);
/*  710 */           this.streamThread.interrupt();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void stop(String sourcename)
/*      */   {
/*  722 */     if ((sourcename == null) || (sourcename.equals("")))
/*      */     {
/*  724 */       errorMessage("Sourcename not specified in method 'stop'");
/*  725 */       return;
/*      */     }
/*  727 */     if (midiSourcename(sourcename))
/*      */     {
/*  729 */       this.midiChannel.stop();
/*      */     }
/*      */     else
/*      */     {
/*  733 */       Source mySource = (Source)this.sourceMap.get(sourcename);
/*  734 */       if (mySource != null)
/*  735 */         mySource.stop();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void pause(String sourcename)
/*      */   {
/*  745 */     if ((sourcename == null) || (sourcename.equals("")))
/*      */     {
/*  747 */       errorMessage("Sourcename not specified in method 'stop'");
/*  748 */       return;
/*      */     }
/*  750 */     if (midiSourcename(sourcename))
/*      */     {
/*  752 */       this.midiChannel.pause();
/*      */     }
/*      */     else
/*      */     {
/*  756 */       Source mySource = (Source)this.sourceMap.get(sourcename);
/*  757 */       if (mySource != null)
/*  758 */         mySource.pause();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void rewind(String sourcename)
/*      */   {
/*  768 */     if (midiSourcename(sourcename))
/*      */     {
/*  770 */       this.midiChannel.rewind();
/*      */     }
/*      */     else
/*      */     {
/*  774 */       Source mySource = (Source)this.sourceMap.get(sourcename);
/*  775 */       if (mySource != null)
/*  776 */         mySource.rewind();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void flush(String sourcename)
/*      */   {
/*  786 */     if (midiSourcename(sourcename)) {
/*  787 */       errorMessage("You can not flush the MIDI channel");
/*      */     }
/*      */     else {
/*  790 */       Source mySource = (Source)this.sourceMap.get(sourcename);
/*  791 */       if (mySource != null)
/*  792 */         mySource.flush();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void cull(String sourcename)
/*      */   {
/*  803 */     Source mySource = (Source)this.sourceMap.get(sourcename);
/*  804 */     if (mySource != null)
/*  805 */       mySource.cull();
/*      */   }
/*      */ 
/*      */   public void activate(String sourcename)
/*      */   {
/*  814 */     Source mySource = (Source)this.sourceMap.get(sourcename);
/*  815 */     if (mySource != null)
/*      */     {
/*  817 */       mySource.activate();
/*  818 */       if (mySource.toPlay)
/*  819 */         play(mySource);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setMasterVolume(float value)
/*      */   {
/*  829 */     SoundSystemConfig.setMasterGain(value);
/*  830 */     if (this.midiChannel != null)
/*  831 */       this.midiChannel.resetGain();
/*      */   }
/*      */ 
/*      */   public void setVolume(String sourcename, float value)
/*      */   {
/*  841 */     if (midiSourcename(sourcename))
/*      */     {
/*  843 */       this.midiChannel.setVolume(value);
/*      */     }
/*      */     else
/*      */     {
/*  847 */       Source mySource = (Source)this.sourceMap.get(sourcename);
/*  848 */       if (mySource != null)
/*      */       {
/*  850 */         float newVolume = value;
/*  851 */         if (newVolume < 0.0F)
/*  852 */           newVolume = 0.0F;
/*  853 */         else if (newVolume > 1.0F) {
/*  854 */           newVolume = 1.0F;
/*      */         }
/*  856 */         mySource.sourceVolume = newVolume;
/*  857 */         mySource.positionChanged();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public float getVolume(String sourcename)
/*      */   {
/*  870 */     if (midiSourcename(sourcename))
/*      */     {
/*  872 */       return this.midiChannel.getVolume();
/*      */     }
/*      */ 
/*  876 */     Source mySource = (Source)this.sourceMap.get(sourcename);
/*  877 */     if (mySource != null) {
/*  878 */       return mySource.sourceVolume;
/*      */     }
/*  880 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   public void setPitch(String sourcename, float value)
/*      */   {
/*  891 */     if (!midiSourcename(sourcename))
/*      */     {
/*  893 */       Source mySource = (Source)this.sourceMap.get(sourcename);
/*  894 */       if (mySource != null)
/*      */       {
/*  896 */         float newPitch = value;
/*  897 */         if (newPitch < 0.5F)
/*  898 */           newPitch = 0.5F;
/*  899 */         else if (newPitch > 2.0F) {
/*  900 */           newPitch = 2.0F;
/*      */         }
/*  902 */         mySource.setPitch(newPitch);
/*  903 */         mySource.positionChanged();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public float getPitch(String sourcename)
/*      */   {
/*  915 */     if (!midiSourcename(sourcename))
/*      */     {
/*  917 */       Source mySource = (Source)this.sourceMap.get(sourcename);
/*  918 */       if (mySource != null)
/*  919 */         return mySource.getPitch();
/*      */     }
/*  921 */     return 1.0F;
/*      */   }
/*      */ 
/*      */   public void moveListener(float x, float y, float z)
/*      */   {
/*  932 */     setListenerPosition(this.listener.position.x + x, this.listener.position.y + y, this.listener.position.z + z);
/*      */   }
/*      */ 
/*      */   public void setListenerPosition(float x, float y, float z)
/*      */   {
/*  945 */     this.listener.setPosition(x, y, z);
/*      */ 
/*  947 */     Set keys = this.sourceMap.keySet();
/*  948 */     Iterator iter = keys.iterator();
/*      */ 
/*  953 */     while (iter.hasNext())
/*      */     {
/*  955 */       String sourcename = (String)iter.next();
/*  956 */       Source source = (Source)this.sourceMap.get(sourcename);
/*  957 */       if (source != null)
/*  958 */         source.positionChanged();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void turnListener(float angle)
/*      */   {
/*  969 */     setListenerAngle(this.listener.angle + angle);
/*      */ 
/*  971 */     Set keys = this.sourceMap.keySet();
/*  972 */     Iterator iter = keys.iterator();
/*      */ 
/*  977 */     while (iter.hasNext())
/*      */     {
/*  979 */       String sourcename = (String)iter.next();
/*  980 */       Source source = (Source)this.sourceMap.get(sourcename);
/*  981 */       if (source != null)
/*  982 */         source.positionChanged();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setListenerAngle(float angle)
/*      */   {
/*  993 */     this.listener.setAngle(angle);
/*      */ 
/*  995 */     Set keys = this.sourceMap.keySet();
/*  996 */     Iterator iter = keys.iterator();
/*      */ 
/* 1001 */     while (iter.hasNext())
/*      */     {
/* 1003 */       String sourcename = (String)iter.next();
/* 1004 */       Source source = (Source)this.sourceMap.get(sourcename);
/* 1005 */       if (source != null)
/* 1006 */         source.positionChanged();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setListenerOrientation(float lookX, float lookY, float lookZ, float upX, float upY, float upZ)
/*      */   {
/* 1022 */     this.listener.setOrientation(lookX, lookY, lookZ, upX, upY, upZ);
/*      */ 
/* 1024 */     Set keys = this.sourceMap.keySet();
/* 1025 */     Iterator iter = keys.iterator();
/*      */ 
/* 1030 */     while (iter.hasNext())
/*      */     {
/* 1032 */       String sourcename = (String)iter.next();
/* 1033 */       Source source = (Source)this.sourceMap.get(sourcename);
/* 1034 */       if (source != null)
/* 1035 */         source.positionChanged();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setListenerData(ListenerData l)
/*      */   {
/* 1046 */     this.listener.setData(l);
/*      */   }
/*      */ 
/*      */   public void copySources(HashMap<String, Source> srcMap)
/*      */   {
/* 1055 */     if (srcMap == null)
/* 1056 */       return;
/* 1057 */     Set keys = srcMap.keySet();
/* 1058 */     Iterator iter = keys.iterator();
/*      */ 
/* 1063 */     this.sourceMap.clear();
/*      */ 
/* 1066 */     while (iter.hasNext())
/*      */     {
/* 1068 */       String sourcename = (String)iter.next();
/* 1069 */       Source srcData = (Source)srcMap.get(sourcename);
/* 1070 */       if (srcData != null)
/*      */       {
/* 1072 */         loadSound(srcData.filenameURL);
/* 1073 */         this.sourceMap.put(sourcename, new Source(srcData, null));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void removeSource(String sourcename)
/*      */   {
/* 1084 */     Source mySource = (Source)this.sourceMap.get(sourcename);
/* 1085 */     if (mySource != null)
/* 1086 */       mySource.cleanup();
/* 1087 */     this.sourceMap.remove(sourcename);
/*      */   }
/*      */ 
/*      */   public void removeTemporarySources()
/*      */   {
/* 1095 */     Set keys = this.sourceMap.keySet();
/* 1096 */     Iterator iter = keys.iterator();
/*      */ 
/* 1101 */     while (iter.hasNext())
/*      */     {
/* 1103 */       String sourcename = (String)iter.next();
/* 1104 */       Source srcData = (Source)this.sourceMap.get(sourcename);
/* 1105 */       if ((srcData != null) && (srcData.temporary) && (!srcData.playing()))
/*      */       {
/* 1108 */         srcData.cleanup();
/* 1109 */         iter.remove();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private Channel getNextChannel(Source source)
/*      */   {
/* 1130 */     if (source == null) {
/* 1131 */       return null;
/*      */     }
/* 1133 */     String sourcename = source.sourcename;
/* 1134 */     if (sourcename == null)
/* 1135 */       return null;
/*      */     String[] sourceNames;
/*      */     int nextChannel;
/*      */     List channelList;
/*      */     String[] sourceNames;
/* 1144 */     if (source.toStream)
/*      */     {
/* 1146 */       int nextChannel = this.nextStreamingChannel;
/* 1147 */       List channelList = this.streamingChannels;
/* 1148 */       sourceNames = this.streamingChannelSourceNames;
/*      */     }
/*      */     else
/*      */     {
/* 1152 */       nextChannel = this.nextNormalChannel;
/* 1153 */       channelList = this.normalChannels;
/* 1154 */       sourceNames = this.normalChannelSourceNames;
/*      */     }
/*      */ 
/* 1157 */     int channels = channelList.size();
/*      */ 
/* 1160 */     for (int x = 0; x < channels; x++)
/*      */     {
/* 1162 */       if (sourcename.equals(sourceNames[x])) {
/* 1163 */         return (Channel)channelList.get(x);
/*      */       }
/*      */     }
/* 1166 */     int n = nextChannel;
/*      */ 
/* 1169 */     for (x = 0; x < channels; x++)
/*      */     {
/* 1171 */       String name = sourceNames[n];
/*      */       Source src;
/*      */       Source src;
/* 1172 */       if (name == null)
/* 1173 */         src = null;
/*      */       else {
/* 1175 */         src = (Source)this.sourceMap.get(name);
/*      */       }
/* 1177 */       if ((src == null) || (!src.playing()))
/*      */       {
/* 1179 */         if (source.toStream)
/*      */         {
/* 1181 */           this.nextStreamingChannel = (n + 1);
/* 1182 */           if (this.nextStreamingChannel >= channels)
/* 1183 */             this.nextStreamingChannel = 0;
/*      */         }
/*      */         else
/*      */         {
/* 1187 */           this.nextNormalChannel = (n + 1);
/* 1188 */           if (this.nextNormalChannel >= channels)
/* 1189 */             this.nextNormalChannel = 0;
/*      */         }
/* 1191 */         sourceNames[n] = sourcename;
/* 1192 */         return (Channel)channelList.get(n);
/*      */       }
/* 1194 */       n++;
/* 1195 */       if (n >= channels) {
/* 1196 */         n = 0;
/*      */       }
/*      */     }
/* 1199 */     n = nextChannel;
/*      */ 
/* 1201 */     for (x = 0; x < channels; x++)
/*      */     {
/* 1203 */       String name = sourceNames[n];
/*      */       Source src;
/*      */       Source src;
/* 1204 */       if (name == null)
/* 1205 */         src = null;
/*      */       else {
/* 1207 */         src = (Source)this.sourceMap.get(name);
/*      */       }
/* 1209 */       if ((src == null) || (!src.playing()) || (!src.priority))
/*      */       {
/* 1211 */         if (source.toStream)
/*      */         {
/* 1213 */           this.nextStreamingChannel = (n + 1);
/* 1214 */           if (this.nextStreamingChannel >= channels)
/* 1215 */             this.nextStreamingChannel = 0;
/*      */         }
/*      */         else
/*      */         {
/* 1219 */           this.nextNormalChannel = (n + 1);
/* 1220 */           if (this.nextNormalChannel >= channels)
/* 1221 */             this.nextNormalChannel = 0;
/*      */         }
/* 1223 */         sourceNames[n] = sourcename;
/* 1224 */         return (Channel)channelList.get(n);
/*      */       }
/* 1226 */       n++;
/* 1227 */       if (n >= channels) {
/* 1228 */         n = 0;
/*      */       }
/*      */     }
/* 1231 */     return null;
/*      */   }
/*      */ 
/*      */   public void replaySources()
/*      */   {
/* 1241 */     Set keys = this.sourceMap.keySet();
/* 1242 */     Iterator iter = keys.iterator();
/*      */ 
/* 1247 */     while (iter.hasNext())
/*      */     {
/* 1249 */       String sourcename = (String)iter.next();
/* 1250 */       Source source = (Source)this.sourceMap.get(sourcename);
/* 1251 */       if ((source != null) && 
/* 1253 */         (source.toPlay) && (!source.playing()))
/*      */       {
/* 1255 */         play(sourcename);
/* 1256 */         source.toPlay = false;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void queueSound(String sourcename, FilenameURL filenameURL)
/*      */   {
/* 1271 */     if (midiSourcename(sourcename))
/*      */     {
/* 1273 */       this.midiChannel.queueSound(filenameURL);
/*      */     }
/*      */     else
/*      */     {
/* 1277 */       Source mySource = (Source)this.sourceMap.get(sourcename);
/* 1278 */       if (mySource != null)
/* 1279 */         mySource.queueSound(filenameURL);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void dequeueSound(String sourcename, String filename)
/*      */   {
/* 1292 */     if (midiSourcename(sourcename))
/*      */     {
/* 1294 */       this.midiChannel.dequeueSound(filename);
/*      */     }
/*      */     else
/*      */     {
/* 1298 */       Source mySource = (Source)this.sourceMap.get(sourcename);
/* 1299 */       if (mySource != null)
/* 1300 */         mySource.dequeueSound(filename);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void fadeOut(String sourcename, FilenameURL filenameURL, long milis)
/*      */   {
/* 1320 */     if (midiSourcename(sourcename))
/*      */     {
/* 1322 */       this.midiChannel.fadeOut(filenameURL, milis);
/*      */     }
/*      */     else
/*      */     {
/* 1326 */       Source mySource = (Source)this.sourceMap.get(sourcename);
/* 1327 */       if (mySource != null)
/* 1328 */         mySource.fadeOut(filenameURL, milis);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void fadeOutIn(String sourcename, FilenameURL filenameURL, long milisOut, long milisIn)
/*      */   {
/* 1349 */     if (midiSourcename(sourcename))
/*      */     {
/* 1351 */       this.midiChannel.fadeOutIn(filenameURL, milisOut, milisIn);
/*      */     }
/*      */     else
/*      */     {
/* 1355 */       Source mySource = (Source)this.sourceMap.get(sourcename);
/* 1356 */       if (mySource != null)
/* 1357 */         mySource.fadeOutIn(filenameURL, milisOut, milisIn);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void checkFadeVolumes()
/*      */   {
/* 1374 */     if (this.midiChannel != null) {
/* 1375 */       this.midiChannel.resetGain();
/*      */     }
/*      */ 
/* 1378 */     for (int x = 0; x < this.streamingChannels.size(); x++)
/*      */     {
/* 1380 */       Channel c = (Channel)this.streamingChannels.get(x);
/* 1381 */       if (c != null)
/*      */       {
/* 1383 */         Source s = c.attachedSource;
/* 1384 */         if (s != null)
/* 1385 */           s.checkFadeOut();
/*      */       }
/*      */     }
/* 1388 */     Channel c = null;
/* 1389 */     Source s = null;
/*      */   }
/*      */ 
/*      */   public void loadMidi(boolean toLoop, String sourcename, FilenameURL filenameURL)
/*      */   {
/* 1401 */     if (filenameURL == null)
/*      */     {
/* 1403 */       errorMessage("Filename/URL not specified in method 'loadMidi'.");
/* 1404 */       return;
/*      */     }
/*      */ 
/* 1407 */     if (!filenameURL.getFilename().matches(SoundSystemConfig.EXTENSION_MIDI))
/*      */     {
/* 1410 */       errorMessage("Filename/identifier doesn't end in '.mid' or'.midi' in method loadMidi.");
/*      */ 
/* 1412 */       return;
/*      */     }
/*      */ 
/* 1415 */     if (this.midiChannel == null)
/*      */     {
/* 1417 */       this.midiChannel = new MidiChannel(toLoop, sourcename, filenameURL);
/*      */     }
/*      */     else
/*      */     {
/* 1421 */       this.midiChannel.switchSource(toLoop, sourcename, filenameURL);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void unloadMidi()
/*      */   {
/* 1430 */     if (this.midiChannel != null)
/* 1431 */       this.midiChannel.cleanup();
/* 1432 */     this.midiChannel = null;
/*      */   }
/*      */ 
/*      */   public boolean midiSourcename(String sourcename)
/*      */   {
/* 1442 */     if ((this.midiChannel == null) || (sourcename == null)) {
/* 1443 */       return false;
/*      */     }
/* 1445 */     if ((this.midiChannel.getSourcename() == null) || (sourcename.equals(""))) {
/* 1446 */       return false;
/*      */     }
/* 1448 */     if (sourcename.equals(this.midiChannel.getSourcename())) {
/* 1449 */       return true;
/*      */     }
/* 1451 */     return false;
/*      */   }
/*      */ 
/*      */   public Source getSource(String sourcename)
/*      */   {
/* 1462 */     return (Source)this.sourceMap.get(sourcename);
/*      */   }
/*      */ 
/*      */   public MidiChannel getMidiChannel()
/*      */   {
/* 1472 */     return this.midiChannel;
/*      */   }
/*      */ 
/*      */   public void setMidiChannel(MidiChannel c)
/*      */   {
/* 1482 */     if ((this.midiChannel != null) && (this.midiChannel != c)) {
/* 1483 */       this.midiChannel.cleanup();
/*      */     }
/* 1485 */     this.midiChannel = c;
/*      */   }
/*      */ 
/*      */   public void listenerMoved()
/*      */   {
/* 1493 */     Set keys = this.sourceMap.keySet();
/* 1494 */     Iterator iter = keys.iterator();
/*      */ 
/* 1499 */     while (iter.hasNext())
/*      */     {
/* 1501 */       String sourcename = (String)iter.next();
/* 1502 */       Source srcData = (Source)this.sourceMap.get(sourcename);
/* 1503 */       if (srcData != null)
/*      */       {
/* 1505 */         srcData.listenerMoved();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public HashMap<String, Source> getSources()
/*      */   {
/* 1516 */     return this.sourceMap;
/*      */   }
/*      */ 
/*      */   public ListenerData getListenerData()
/*      */   {
/* 1525 */     return this.listener;
/*      */   }
/*      */ 
/*      */   public boolean reverseByteOrder()
/*      */   {
/* 1535 */     return this.reverseByteOrder;
/*      */   }
/*      */ 
/*      */   public static String getTitle()
/*      */   {
/* 1543 */     return "No Sound";
/*      */   }
/*      */ 
/*      */   public static String getDescription()
/*      */   {
/* 1552 */     return "Silent Mode";
/*      */   }
/*      */ 
/*      */   public String getClassName()
/*      */   {
/* 1561 */     return "Library";
/*      */   }
/*      */ 
/*      */   protected void message(String message)
/*      */   {
/* 1570 */     this.logger.message(message, 0);
/*      */   }
/*      */ 
/*      */   protected void importantMessage(String message)
/*      */   {
/* 1579 */     this.logger.importantMessage(message, 0);
/*      */   }
/*      */ 
/*      */   protected boolean errorCheck(boolean error, String message)
/*      */   {
/* 1590 */     return this.logger.errorCheck(error, getClassName(), message, 0);
/*      */   }
/*      */ 
/*      */   protected void errorMessage(String message)
/*      */   {
/* 1599 */     this.logger.errorMessage(getClassName(), message, 0);
/*      */   }
/*      */ 
/*      */   protected void printStackTrace(Exception e)
/*      */   {
/* 1608 */     this.logger.printStackTrace(e, 1);
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.Library
 * JD-Core Version:    0.6.2
 */