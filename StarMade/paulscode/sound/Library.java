package paulscode.sound;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.sound.sampled.AudioFormat;

public class Library
{
  private SoundSystemLogger logger = SoundSystemConfig.getLogger();
  protected ListenerData listener = new ListenerData(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F);
  protected HashMap<String, SoundBuffer> bufferMap = null;
  protected HashMap<String, Source> sourceMap = new HashMap();
  private MidiChannel midiChannel;
  protected List<Channel> streamingChannels = new LinkedList();
  protected List<Channel> normalChannels = new LinkedList();
  private String[] streamingChannelSourceNames = new String[SoundSystemConfig.getNumberStreamingChannels()];
  private String[] normalChannelSourceNames = new String[SoundSystemConfig.getNumberNormalChannels()];
  private int nextStreamingChannel = 0;
  private int nextNormalChannel = 0;
  protected StreamThread streamThread = new StreamThread();
  protected boolean reverseByteOrder = false;
  
  public Library()
    throws SoundSystemException
  {
    this.streamThread.start();
  }
  
  public void cleanup()
  {
    this.streamThread.kill();
    this.streamThread.interrupt();
    for (int local_i = 0; (local_i < 50) && (this.streamThread.alive()); local_i++) {
      try
      {
        Thread.sleep(100L);
      }
      catch (Exception local_e) {}
    }
    if (this.streamThread.alive())
    {
      errorMessage("Stream thread did not die!");
      message("Ignoring errors... continuing clean-up.");
    }
    if (this.midiChannel != null)
    {
      this.midiChannel.cleanup();
      this.midiChannel = null;
    }
    Channel local_i = null;
    if (this.streamingChannels != null)
    {
      while (!this.streamingChannels.isEmpty())
      {
        local_i = (Channel)this.streamingChannels.remove(0);
        local_i.close();
        local_i.cleanup();
        local_i = null;
      }
      this.streamingChannels.clear();
      this.streamingChannels = null;
    }
    if (this.normalChannels != null)
    {
      while (!this.normalChannels.isEmpty())
      {
        local_i = (Channel)this.normalChannels.remove(0);
        local_i.close();
        local_i.cleanup();
        local_i = null;
      }
      this.normalChannels.clear();
      this.normalChannels = null;
    }
    Set<String> local_e = this.sourceMap.keySet();
    Iterator<String> iter = local_e.iterator();
    while (iter.hasNext())
    {
      String sourcename = (String)iter.next();
      Source source = (Source)this.sourceMap.get(sourcename);
      if (source != null) {
        source.cleanup();
      }
    }
    this.sourceMap.clear();
    this.sourceMap = null;
    this.listener = null;
    this.streamThread = null;
  }
  
  public void init()
    throws SoundSystemException
  {
    Channel channel = null;
    for (int local_x = 0; local_x < SoundSystemConfig.getNumberStreamingChannels(); local_x++)
    {
      channel = createChannel(1);
      if (channel == null) {
        break;
      }
      this.streamingChannels.add(channel);
    }
    for (int local_x = 0; local_x < SoundSystemConfig.getNumberNormalChannels(); local_x++)
    {
      channel = createChannel(0);
      if (channel == null) {
        break;
      }
      this.normalChannels.add(channel);
    }
  }
  
  public static boolean libraryCompatible()
  {
    return true;
  }
  
  protected Channel createChannel(int type)
  {
    return new Channel(type);
  }
  
  public boolean loadSound(FilenameURL filenameURL)
  {
    return true;
  }
  
  public boolean loadSound(SoundBuffer buffer, String identifier)
  {
    return true;
  }
  
  public LinkedList<String> getAllLoadedFilenames()
  {
    LinkedList<String> filenames = new LinkedList();
    Set<String> keys = this.bufferMap.keySet();
    Iterator<String> iter = keys.iterator();
    while (iter.hasNext()) {
      filenames.add(iter.next());
    }
    return filenames;
  }
  
  public LinkedList<String> getAllSourcenames()
  {
    LinkedList<String> sourcenames = new LinkedList();
    Set<String> keys = this.sourceMap.keySet();
    Iterator<String> iter = keys.iterator();
    if (this.midiChannel != null) {
      sourcenames.add(this.midiChannel.getSourcename());
    }
    while (iter.hasNext()) {
      sourcenames.add(iter.next());
    }
    return sourcenames;
  }
  
  public void unloadSound(String filename)
  {
    this.bufferMap.remove(filename);
  }
  
  public void rawDataStream(AudioFormat audioFormat, boolean priority, String sourcename, float posX, float posY, float posZ, int attModel, float distOrRoll)
  {
    this.sourceMap.put(sourcename, new Source(audioFormat, priority, sourcename, posX, posY, posZ, attModel, distOrRoll));
  }
  
  public void newSource(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float posX, float posY, float posZ, int attModel, float distOrRoll)
  {
    this.sourceMap.put(sourcename, new Source(priority, toStream, toLoop, sourcename, filenameURL, null, posX, posY, posZ, attModel, distOrRoll, false));
  }
  
  public void quickPlay(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float posX, float posY, float posZ, int attModel, float distOrRoll, boolean tmp)
  {
    this.sourceMap.put(sourcename, new Source(priority, toStream, toLoop, sourcename, filenameURL, null, posX, posY, posZ, attModel, distOrRoll, tmp));
  }
  
  public void setTemporary(String sourcename, boolean temporary)
  {
    Source mySource = (Source)this.sourceMap.get(sourcename);
    if (mySource != null) {
      mySource.setTemporary(temporary);
    }
  }
  
  public void setPosition(String sourcename, float local_x, float local_y, float local_z)
  {
    Source mySource = (Source)this.sourceMap.get(sourcename);
    if (mySource != null) {
      mySource.setPosition(local_x, local_y, local_z);
    }
  }
  
  public void setPriority(String sourcename, boolean pri)
  {
    Source mySource = (Source)this.sourceMap.get(sourcename);
    if (mySource != null) {
      mySource.setPriority(pri);
    }
  }
  
  public void setLooping(String sourcename, boolean local_lp)
  {
    Source mySource = (Source)this.sourceMap.get(sourcename);
    if (mySource != null) {
      mySource.setLooping(local_lp);
    }
  }
  
  public void setAttenuation(String sourcename, int model)
  {
    Source mySource = (Source)this.sourceMap.get(sourcename);
    if (mySource != null) {
      mySource.setAttenuation(model);
    }
  }
  
  public void setDistOrRoll(String sourcename, float local_dr)
  {
    Source mySource = (Source)this.sourceMap.get(sourcename);
    if (mySource != null) {
      mySource.setDistOrRoll(local_dr);
    }
  }
  
  public void setVelocity(String sourcename, float local_x, float local_y, float local_z)
  {
    Source mySource = (Source)this.sourceMap.get(sourcename);
    if (mySource != null) {
      mySource.setVelocity(local_x, local_y, local_z);
    }
  }
  
  public void setListenerVelocity(float local_x, float local_y, float local_z)
  {
    this.listener.setVelocity(local_x, local_y, local_z);
  }
  
  public void dopplerChanged() {}
  
  public float millisecondsPlayed(String sourcename)
  {
    if ((sourcename == null) || (sourcename.equals("")))
    {
      errorMessage("Sourcename not specified in method 'millisecondsPlayed'");
      return -1.0F;
    }
    if (midiSourcename(sourcename))
    {
      errorMessage("Unable to calculate milliseconds for MIDI source.");
      return -1.0F;
    }
    Source source = (Source)this.sourceMap.get(sourcename);
    if (source == null) {
      errorMessage("Source '" + sourcename + "' not found in " + "method 'millisecondsPlayed'");
    }
    return source.millisecondsPlayed();
  }
  
  public int feedRawAudioData(String sourcename, byte[] buffer)
  {
    if ((sourcename == null) || (sourcename.equals("")))
    {
      errorMessage("Sourcename not specified in method 'feedRawAudioData'");
      return -1;
    }
    if (midiSourcename(sourcename))
    {
      errorMessage("Raw audio data can not be fed to the MIDI channel.");
      return -1;
    }
    Source source = (Source)this.sourceMap.get(sourcename);
    if (source == null) {
      errorMessage("Source '" + sourcename + "' not found in " + "method 'feedRawAudioData'");
    }
    return feedRawAudioData(source, buffer);
  }
  
  public int feedRawAudioData(Source source, byte[] buffer)
  {
    if (source == null)
    {
      errorMessage("Source parameter null in method 'feedRawAudioData'");
      return -1;
    }
    if (!source.toStream)
    {
      errorMessage("Only a streaming source may be specified in method 'feedRawAudioData'");
      return -1;
    }
    if (!source.rawDataStream)
    {
      errorMessage("Streaming source already associated with a file or URL in method'feedRawAudioData'");
      return -1;
    }
    if ((!source.playing()) || (source.channel == null))
    {
      Channel channel;
      Channel channel;
      if ((source.channel != null) && (source.channel.attachedSource == source)) {
        channel = source.channel;
      } else {
        channel = getNextChannel(source);
      }
      int processed = source.feedRawAudioData(channel, buffer);
      channel.attachedSource = source;
      this.streamThread.watch(source);
      this.streamThread.interrupt();
      return processed;
    }
    return source.feedRawAudioData(source.channel, buffer);
  }
  
  public void play(String sourcename)
  {
    if ((sourcename == null) || (sourcename.equals("")))
    {
      errorMessage("Sourcename not specified in method 'play'");
      return;
    }
    if (midiSourcename(sourcename))
    {
      this.midiChannel.play();
    }
    else
    {
      Source source = (Source)this.sourceMap.get(sourcename);
      if (source == null) {
        errorMessage("Source '" + sourcename + "' not found in " + "method 'play'");
      }
      play(source);
    }
  }
  
  public void play(Source source)
  {
    if (source == null) {
      return;
    }
    if (source.rawDataStream) {
      return;
    }
    if (!source.active()) {
      return;
    }
    if (!source.playing())
    {
      Channel channel = getNextChannel(source);
      if ((source != null) && (channel != null))
      {
        if ((source.channel != null) && (source.channel.attachedSource != source)) {
          source.channel = null;
        }
        channel.attachedSource = source;
        source.play(channel);
        if (source.toStream)
        {
          this.streamThread.watch(source);
          this.streamThread.interrupt();
        }
      }
    }
  }
  
  public void stop(String sourcename)
  {
    if ((sourcename == null) || (sourcename.equals("")))
    {
      errorMessage("Sourcename not specified in method 'stop'");
      return;
    }
    if (midiSourcename(sourcename))
    {
      this.midiChannel.stop();
    }
    else
    {
      Source mySource = (Source)this.sourceMap.get(sourcename);
      if (mySource != null) {
        mySource.stop();
      }
    }
  }
  
  public void pause(String sourcename)
  {
    if ((sourcename == null) || (sourcename.equals("")))
    {
      errorMessage("Sourcename not specified in method 'stop'");
      return;
    }
    if (midiSourcename(sourcename))
    {
      this.midiChannel.pause();
    }
    else
    {
      Source mySource = (Source)this.sourceMap.get(sourcename);
      if (mySource != null) {
        mySource.pause();
      }
    }
  }
  
  public void rewind(String sourcename)
  {
    if (midiSourcename(sourcename))
    {
      this.midiChannel.rewind();
    }
    else
    {
      Source mySource = (Source)this.sourceMap.get(sourcename);
      if (mySource != null) {
        mySource.rewind();
      }
    }
  }
  
  public void flush(String sourcename)
  {
    if (midiSourcename(sourcename))
    {
      errorMessage("You can not flush the MIDI channel");
    }
    else
    {
      Source mySource = (Source)this.sourceMap.get(sourcename);
      if (mySource != null) {
        mySource.flush();
      }
    }
  }
  
  public void cull(String sourcename)
  {
    Source mySource = (Source)this.sourceMap.get(sourcename);
    if (mySource != null) {
      mySource.cull();
    }
  }
  
  public void activate(String sourcename)
  {
    Source mySource = (Source)this.sourceMap.get(sourcename);
    if (mySource != null)
    {
      mySource.activate();
      if (mySource.toPlay) {
        play(mySource);
      }
    }
  }
  
  public void setMasterVolume(float value)
  {
    SoundSystemConfig.setMasterGain(value);
    if (this.midiChannel != null) {
      this.midiChannel.resetGain();
    }
  }
  
  public void setVolume(String sourcename, float value)
  {
    if (midiSourcename(sourcename))
    {
      this.midiChannel.setVolume(value);
    }
    else
    {
      Source mySource = (Source)this.sourceMap.get(sourcename);
      if (mySource != null)
      {
        float newVolume = value;
        if (newVolume < 0.0F) {
          newVolume = 0.0F;
        } else if (newVolume > 1.0F) {
          newVolume = 1.0F;
        }
        mySource.sourceVolume = newVolume;
        mySource.positionChanged();
      }
    }
  }
  
  public float getVolume(String sourcename)
  {
    if (midiSourcename(sourcename)) {
      return this.midiChannel.getVolume();
    }
    Source mySource = (Source)this.sourceMap.get(sourcename);
    if (mySource != null) {
      return mySource.sourceVolume;
    }
    return 0.0F;
  }
  
  public void setPitch(String sourcename, float value)
  {
    if (!midiSourcename(sourcename))
    {
      Source mySource = (Source)this.sourceMap.get(sourcename);
      if (mySource != null)
      {
        float newPitch = value;
        if (newPitch < 0.5F) {
          newPitch = 0.5F;
        } else if (newPitch > 2.0F) {
          newPitch = 2.0F;
        }
        mySource.setPitch(newPitch);
        mySource.positionChanged();
      }
    }
  }
  
  public float getPitch(String sourcename)
  {
    if (!midiSourcename(sourcename))
    {
      Source mySource = (Source)this.sourceMap.get(sourcename);
      if (mySource != null) {
        return mySource.getPitch();
      }
    }
    return 1.0F;
  }
  
  public void moveListener(float local_x, float local_y, float local_z)
  {
    setListenerPosition(this.listener.position.field_2107 + local_x, this.listener.position.field_2108 + local_y, this.listener.position.field_2109 + local_z);
  }
  
  public void setListenerPosition(float local_x, float local_y, float local_z)
  {
    this.listener.setPosition(local_x, local_y, local_z);
    Set<String> keys = this.sourceMap.keySet();
    Iterator<String> iter = keys.iterator();
    while (iter.hasNext())
    {
      String sourcename = (String)iter.next();
      Source source = (Source)this.sourceMap.get(sourcename);
      if (source != null) {
        source.positionChanged();
      }
    }
  }
  
  public void turnListener(float angle)
  {
    setListenerAngle(this.listener.angle + angle);
    Set<String> keys = this.sourceMap.keySet();
    Iterator<String> iter = keys.iterator();
    while (iter.hasNext())
    {
      String sourcename = (String)iter.next();
      Source source = (Source)this.sourceMap.get(sourcename);
      if (source != null) {
        source.positionChanged();
      }
    }
  }
  
  public void setListenerAngle(float angle)
  {
    this.listener.setAngle(angle);
    Set<String> keys = this.sourceMap.keySet();
    Iterator<String> iter = keys.iterator();
    while (iter.hasNext())
    {
      String sourcename = (String)iter.next();
      Source source = (Source)this.sourceMap.get(sourcename);
      if (source != null) {
        source.positionChanged();
      }
    }
  }
  
  public void setListenerOrientation(float lookX, float lookY, float lookZ, float upX, float upY, float upZ)
  {
    this.listener.setOrientation(lookX, lookY, lookZ, upX, upY, upZ);
    Set<String> keys = this.sourceMap.keySet();
    Iterator<String> iter = keys.iterator();
    while (iter.hasNext())
    {
      String sourcename = (String)iter.next();
      Source source = (Source)this.sourceMap.get(sourcename);
      if (source != null) {
        source.positionChanged();
      }
    }
  }
  
  public void setListenerData(ListenerData local_l)
  {
    this.listener.setData(local_l);
  }
  
  public void copySources(HashMap<String, Source> srcMap)
  {
    if (srcMap == null) {
      return;
    }
    Set<String> keys = srcMap.keySet();
    Iterator<String> iter = keys.iterator();
    this.sourceMap.clear();
    while (iter.hasNext())
    {
      String sourcename = (String)iter.next();
      Source srcData = (Source)srcMap.get(sourcename);
      if (srcData != null)
      {
        loadSound(srcData.filenameURL);
        this.sourceMap.put(sourcename, new Source(srcData, null));
      }
    }
  }
  
  public void removeSource(String sourcename)
  {
    Source mySource = (Source)this.sourceMap.get(sourcename);
    if (mySource != null) {
      mySource.cleanup();
    }
    this.sourceMap.remove(sourcename);
  }
  
  public void removeTemporarySources()
  {
    Set<String> keys = this.sourceMap.keySet();
    Iterator<String> iter = keys.iterator();
    while (iter.hasNext())
    {
      String sourcename = (String)iter.next();
      Source srcData = (Source)this.sourceMap.get(sourcename);
      if ((srcData != null) && (srcData.temporary) && (!srcData.playing()))
      {
        srcData.cleanup();
        iter.remove();
      }
    }
  }
  
  private Channel getNextChannel(Source source)
  {
    if (source == null) {
      return null;
    }
    String sourcename = source.sourcename;
    if (sourcename == null) {
      return null;
    }
    String[] sourceNames;
    int nextChannel;
    List<Channel> channelList;
    String[] sourceNames;
    if (source.toStream)
    {
      int nextChannel = this.nextStreamingChannel;
      List<Channel> channelList = this.streamingChannels;
      sourceNames = this.streamingChannelSourceNames;
    }
    else
    {
      nextChannel = this.nextNormalChannel;
      channelList = this.normalChannels;
      sourceNames = this.normalChannelSourceNames;
    }
    int channels = channelList.size();
    for (int local_x = 0; local_x < channels; local_x++) {
      if (sourcename.equals(sourceNames[local_x])) {
        return (Channel)channelList.get(local_x);
      }
    }
    int local_n = nextChannel;
    for (local_x = 0; local_x < channels; local_x++)
    {
      String name = sourceNames[local_n];
      Source src;
      Source src;
      if (name == null) {
        src = null;
      } else {
        src = (Source)this.sourceMap.get(name);
      }
      if ((src == null) || (!src.playing()))
      {
        if (source.toStream)
        {
          this.nextStreamingChannel = (local_n + 1);
          if (this.nextStreamingChannel >= channels) {
            this.nextStreamingChannel = 0;
          }
        }
        else
        {
          this.nextNormalChannel = (local_n + 1);
          if (this.nextNormalChannel >= channels) {
            this.nextNormalChannel = 0;
          }
        }
        sourceNames[local_n] = sourcename;
        return (Channel)channelList.get(local_n);
      }
      local_n++;
      if (local_n >= channels) {
        local_n = 0;
      }
    }
    local_n = nextChannel;
    for (local_x = 0; local_x < channels; local_x++)
    {
      String name = sourceNames[local_n];
      Source src;
      Source src;
      if (name == null) {
        src = null;
      } else {
        src = (Source)this.sourceMap.get(name);
      }
      if ((src == null) || (!src.playing()) || (!src.priority))
      {
        if (source.toStream)
        {
          this.nextStreamingChannel = (local_n + 1);
          if (this.nextStreamingChannel >= channels) {
            this.nextStreamingChannel = 0;
          }
        }
        else
        {
          this.nextNormalChannel = (local_n + 1);
          if (this.nextNormalChannel >= channels) {
            this.nextNormalChannel = 0;
          }
        }
        sourceNames[local_n] = sourcename;
        return (Channel)channelList.get(local_n);
      }
      local_n++;
      if (local_n >= channels) {
        local_n = 0;
      }
    }
    return null;
  }
  
  public void replaySources()
  {
    Set<String> keys = this.sourceMap.keySet();
    Iterator<String> iter = keys.iterator();
    while (iter.hasNext())
    {
      String sourcename = (String)iter.next();
      Source source = (Source)this.sourceMap.get(sourcename);
      if ((source != null) && (source.toPlay) && (!source.playing()))
      {
        play(sourcename);
        source.toPlay = false;
      }
    }
  }
  
  public void queueSound(String sourcename, FilenameURL filenameURL)
  {
    if (midiSourcename(sourcename))
    {
      this.midiChannel.queueSound(filenameURL);
    }
    else
    {
      Source mySource = (Source)this.sourceMap.get(sourcename);
      if (mySource != null) {
        mySource.queueSound(filenameURL);
      }
    }
  }
  
  public void dequeueSound(String sourcename, String filename)
  {
    if (midiSourcename(sourcename))
    {
      this.midiChannel.dequeueSound(filename);
    }
    else
    {
      Source mySource = (Source)this.sourceMap.get(sourcename);
      if (mySource != null) {
        mySource.dequeueSound(filename);
      }
    }
  }
  
  public void fadeOut(String sourcename, FilenameURL filenameURL, long milis)
  {
    if (midiSourcename(sourcename))
    {
      this.midiChannel.fadeOut(filenameURL, milis);
    }
    else
    {
      Source mySource = (Source)this.sourceMap.get(sourcename);
      if (mySource != null) {
        mySource.fadeOut(filenameURL, milis);
      }
    }
  }
  
  public void fadeOutIn(String sourcename, FilenameURL filenameURL, long milisOut, long milisIn)
  {
    if (midiSourcename(sourcename))
    {
      this.midiChannel.fadeOutIn(filenameURL, milisOut, milisIn);
    }
    else
    {
      Source mySource = (Source)this.sourceMap.get(sourcename);
      if (mySource != null) {
        mySource.fadeOutIn(filenameURL, milisOut, milisIn);
      }
    }
  }
  
  public void checkFadeVolumes()
  {
    if (this.midiChannel != null) {
      this.midiChannel.resetGain();
    }
    for (int local_x = 0; local_x < this.streamingChannels.size(); local_x++)
    {
      Channel local_c = (Channel)this.streamingChannels.get(local_x);
      if (local_c != null)
      {
        Source local_s = local_c.attachedSource;
        if (local_s != null) {
          local_s.checkFadeOut();
        }
      }
    }
    Channel local_c = null;
    Source local_s = null;
  }
  
  public void loadMidi(boolean toLoop, String sourcename, FilenameURL filenameURL)
  {
    if (filenameURL == null)
    {
      errorMessage("Filename/URL not specified in method 'loadMidi'.");
      return;
    }
    if (!filenameURL.getFilename().matches(SoundSystemConfig.EXTENSION_MIDI))
    {
      errorMessage("Filename/identifier doesn't end in '.mid' or'.midi' in method loadMidi.");
      return;
    }
    if (this.midiChannel == null) {
      this.midiChannel = new MidiChannel(toLoop, sourcename, filenameURL);
    } else {
      this.midiChannel.switchSource(toLoop, sourcename, filenameURL);
    }
  }
  
  public void unloadMidi()
  {
    if (this.midiChannel != null) {
      this.midiChannel.cleanup();
    }
    this.midiChannel = null;
  }
  
  public boolean midiSourcename(String sourcename)
  {
    if ((this.midiChannel == null) || (sourcename == null)) {
      return false;
    }
    if ((this.midiChannel.getSourcename() == null) || (sourcename.equals(""))) {
      return false;
    }
    return sourcename.equals(this.midiChannel.getSourcename());
  }
  
  public Source getSource(String sourcename)
  {
    return (Source)this.sourceMap.get(sourcename);
  }
  
  public MidiChannel getMidiChannel()
  {
    return this.midiChannel;
  }
  
  public void setMidiChannel(MidiChannel local_c)
  {
    if ((this.midiChannel != null) && (this.midiChannel != local_c)) {
      this.midiChannel.cleanup();
    }
    this.midiChannel = local_c;
  }
  
  public void listenerMoved()
  {
    Set<String> keys = this.sourceMap.keySet();
    Iterator<String> iter = keys.iterator();
    while (iter.hasNext())
    {
      String sourcename = (String)iter.next();
      Source srcData = (Source)this.sourceMap.get(sourcename);
      if (srcData != null) {
        srcData.listenerMoved();
      }
    }
  }
  
  public HashMap<String, Source> getSources()
  {
    return this.sourceMap;
  }
  
  public ListenerData getListenerData()
  {
    return this.listener;
  }
  
  public boolean reverseByteOrder()
  {
    return this.reverseByteOrder;
  }
  
  public static String getTitle()
  {
    return "No Sound";
  }
  
  public static String getDescription()
  {
    return "Silent Mode";
  }
  
  public String getClassName()
  {
    return "Library";
  }
  
  protected void message(String message)
  {
    this.logger.message(message, 0);
  }
  
  protected void importantMessage(String message)
  {
    this.logger.importantMessage(message, 0);
  }
  
  protected boolean errorCheck(boolean error, String message)
  {
    return this.logger.errorCheck(error, getClassName(), message, 0);
  }
  
  protected void errorMessage(String message)
  {
    this.logger.errorMessage(getClassName(), message, 0);
  }
  
  protected void printStackTrace(Exception local_e)
  {
    this.logger.printStackTrace(local_e, 1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.Library
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */