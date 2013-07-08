package paulscode.sound;

import java.net.URL;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.sound.sampled.AudioFormat;

public class Source
{
  protected Class libraryType = Library.class;
  private static final boolean GET = false;
  private static final boolean SET = true;
  private static final boolean XXX = false;
  private SoundSystemLogger logger = SoundSystemConfig.getLogger();
  public boolean rawDataStream = false;
  public AudioFormat rawDataFormat = null;
  public boolean temporary = false;
  public boolean priority = false;
  public boolean toStream = false;
  public boolean toLoop = false;
  public boolean toPlay = false;
  public String sourcename = "";
  public FilenameURL filenameURL = null;
  public Vector3D position;
  public int attModel = 0;
  public float distOrRoll = 0.0F;
  public Vector3D velocity;
  public float gain = 1.0F;
  public float sourceVolume = 1.0F;
  protected float pitch = 1.0F;
  public float distanceFromListener = 0.0F;
  public Channel channel = null;
  public SoundBuffer soundBuffer = null;
  private boolean active = true;
  private boolean stopped = true;
  private boolean paused = false;
  protected ICodec codec = null;
  protected ICodec nextCodec = null;
  protected LinkedList<SoundBuffer> nextBuffers = null;
  protected LinkedList<FilenameURL> soundSequenceQueue = null;
  protected final Object soundSequenceLock = new Object();
  public boolean preLoad = false;
  protected float fadeOutGain = -1.0F;
  protected float fadeInGain = 1.0F;
  protected long fadeOutMilis = 0L;
  protected long fadeInMilis = 0L;
  protected long lastFadeCheck = 0L;
  
  public Source(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, SoundBuffer soundBuffer, float local_x, float local_y, float local_z, int attModel, float distOrRoll, boolean temporary)
  {
    this.priority = priority;
    this.toStream = toStream;
    this.toLoop = toLoop;
    this.sourcename = sourcename;
    this.filenameURL = filenameURL;
    this.soundBuffer = soundBuffer;
    this.position = new Vector3D(local_x, local_y, local_z);
    this.attModel = attModel;
    this.distOrRoll = distOrRoll;
    this.velocity = new Vector3D(0.0F, 0.0F, 0.0F);
    this.temporary = temporary;
    if ((toStream) && (filenameURL != null)) {
      this.codec = SoundSystemConfig.getCodec(filenameURL.getFilename());
    }
  }
  
  public Source(Source old, SoundBuffer soundBuffer)
  {
    this.priority = old.priority;
    this.toStream = old.toStream;
    this.toLoop = old.toLoop;
    this.sourcename = old.sourcename;
    this.filenameURL = old.filenameURL;
    this.position = old.position.clone();
    this.attModel = old.attModel;
    this.distOrRoll = old.distOrRoll;
    this.velocity = old.velocity.clone();
    this.temporary = old.temporary;
    this.sourceVolume = old.sourceVolume;
    this.rawDataStream = old.rawDataStream;
    this.rawDataFormat = old.rawDataFormat;
    this.soundBuffer = soundBuffer;
    if ((this.toStream) && (this.filenameURL != null)) {
      this.codec = SoundSystemConfig.getCodec(this.filenameURL.getFilename());
    }
  }
  
  public Source(AudioFormat audioFormat, boolean priority, String sourcename, float local_x, float local_y, float local_z, int attModel, float distOrRoll)
  {
    this.priority = priority;
    this.toStream = true;
    this.toLoop = false;
    this.sourcename = sourcename;
    this.filenameURL = null;
    this.soundBuffer = null;
    this.position = new Vector3D(local_x, local_y, local_z);
    this.attModel = attModel;
    this.distOrRoll = distOrRoll;
    this.velocity = new Vector3D(0.0F, 0.0F, 0.0F);
    this.temporary = false;
    this.rawDataStream = true;
    this.rawDataFormat = audioFormat;
  }
  
  public void cleanup()
  {
    if (this.codec != null) {
      this.codec.cleanup();
    }
    synchronized (this.soundSequenceLock)
    {
      if (this.soundSequenceQueue != null) {
        this.soundSequenceQueue.clear();
      }
      this.soundSequenceQueue = null;
    }
    this.sourcename = null;
    this.filenameURL = null;
    this.position = null;
    this.soundBuffer = null;
    this.codec = null;
  }
  
  public void queueSound(FilenameURL filenameURL)
  {
    if (!this.toStream)
    {
      errorMessage("Method 'queueSound' may only be used for streaming and MIDI sources.");
      return;
    }
    if (filenameURL == null)
    {
      errorMessage("File not specified in method 'queueSound'");
      return;
    }
    synchronized (this.soundSequenceLock)
    {
      if (this.soundSequenceQueue == null) {
        this.soundSequenceQueue = new LinkedList();
      }
      this.soundSequenceQueue.add(filenameURL);
    }
  }
  
  public void dequeueSound(String filename)
  {
    if (!this.toStream)
    {
      errorMessage("Method 'dequeueSound' may only be used for streaming and MIDI sources.");
      return;
    }
    if ((filename == null) || (filename.equals("")))
    {
      errorMessage("Filename not specified in method 'dequeueSound'");
      return;
    }
    synchronized (this.soundSequenceLock)
    {
      if (this.soundSequenceQueue != null)
      {
        ListIterator<FilenameURL> local_i = this.soundSequenceQueue.listIterator();
        while (local_i.hasNext()) {
          if (((FilenameURL)local_i.next()).getFilename().equals(filename)) {
            local_i.remove();
          }
        }
      }
    }
  }
  
  public void fadeOut(FilenameURL filenameURL, long milis)
  {
    if (!this.toStream)
    {
      errorMessage("Method 'fadeOut' may only be used for streaming and MIDI sources.");
      return;
    }
    if (milis < 0L)
    {
      errorMessage("Miliseconds may not be negative in method 'fadeOut'.");
      return;
    }
    this.fadeOutMilis = milis;
    this.fadeInMilis = 0L;
    this.fadeOutGain = 1.0F;
    this.lastFadeCheck = System.currentTimeMillis();
    synchronized (this.soundSequenceLock)
    {
      if (this.soundSequenceQueue != null) {
        this.soundSequenceQueue.clear();
      }
      if (filenameURL != null)
      {
        if (this.soundSequenceQueue == null) {
          this.soundSequenceQueue = new LinkedList();
        }
        this.soundSequenceQueue.add(filenameURL);
      }
    }
  }
  
  public void fadeOutIn(FilenameURL filenameURL, long milisOut, long milisIn)
  {
    if (!this.toStream)
    {
      errorMessage("Method 'fadeOutIn' may only be used for streaming and MIDI sources.");
      return;
    }
    if (filenameURL == null)
    {
      errorMessage("Filename/URL not specified in method 'fadeOutIn'.");
      return;
    }
    if ((milisOut < 0L) || (milisIn < 0L))
    {
      errorMessage("Miliseconds may not be negative in method 'fadeOutIn'.");
      return;
    }
    this.fadeOutMilis = milisOut;
    this.fadeInMilis = milisIn;
    this.fadeOutGain = 1.0F;
    this.lastFadeCheck = System.currentTimeMillis();
    synchronized (this.soundSequenceLock)
    {
      if (this.soundSequenceQueue == null) {
        this.soundSequenceQueue = new LinkedList();
      }
      this.soundSequenceQueue.clear();
      this.soundSequenceQueue.add(filenameURL);
    }
  }
  
  public boolean checkFadeOut()
  {
    if (!this.toStream) {
      return false;
    }
    if ((this.fadeOutGain == -1.0F) && (this.fadeInGain == 1.0F)) {
      return false;
    }
    long currentTime = System.currentTimeMillis();
    long milisPast = currentTime - this.lastFadeCheck;
    this.lastFadeCheck = currentTime;
    if (this.fadeOutGain >= 0.0F)
    {
      if (this.fadeOutMilis == 0L)
      {
        this.fadeOutGain = -1.0F;
        this.fadeInGain = 0.0F;
        if (!incrementSoundSequence()) {
          stop();
        }
        positionChanged();
        this.preLoad = true;
        return false;
      }
      float fadeOutReduction = (float)milisPast / (float)this.fadeOutMilis;
      this.fadeOutGain -= fadeOutReduction;
      if (this.fadeOutGain <= 0.0F)
      {
        this.fadeOutGain = -1.0F;
        this.fadeInGain = 0.0F;
        if (!incrementSoundSequence()) {
          stop();
        }
        positionChanged();
        this.preLoad = true;
        return false;
      }
      positionChanged();
      return true;
    }
    if (this.fadeInGain < 1.0F)
    {
      this.fadeOutGain = -1.0F;
      if (this.fadeInMilis == 0L)
      {
        this.fadeOutGain = -1.0F;
        this.fadeInGain = 1.0F;
      }
      else
      {
        float fadeOutReduction = (float)milisPast / (float)this.fadeInMilis;
        this.fadeInGain += fadeOutReduction;
        if (this.fadeInGain >= 1.0F)
        {
          this.fadeOutGain = -1.0F;
          this.fadeInGain = 1.0F;
        }
      }
      positionChanged();
      return true;
    }
    return false;
  }
  
  public boolean incrementSoundSequence()
  {
    if (!this.toStream)
    {
      errorMessage("Method 'incrementSoundSequence' may only be used for streaming and MIDI sources.");
      return false;
    }
    synchronized (this.soundSequenceLock)
    {
      if ((this.soundSequenceQueue != null) && (this.soundSequenceQueue.size() > 0))
      {
        this.filenameURL = ((FilenameURL)this.soundSequenceQueue.remove(0));
        if (this.codec != null) {
          this.codec.cleanup();
        }
        this.codec = SoundSystemConfig.getCodec(this.filenameURL.getFilename());
        return true;
      }
    }
    return false;
  }
  
  public boolean readBuffersFromNextSoundInSequence()
  {
    if (!this.toStream)
    {
      errorMessage("Method 'readBuffersFromNextSoundInSequence' may only be used for streaming sources.");
      return false;
    }
    synchronized (this.soundSequenceLock)
    {
      if ((this.soundSequenceQueue != null) && (this.soundSequenceQueue.size() > 0))
      {
        if (this.nextCodec != null) {
          this.nextCodec.cleanup();
        }
        this.nextCodec = SoundSystemConfig.getCodec(((FilenameURL)this.soundSequenceQueue.get(0)).getFilename());
        this.nextCodec.initialize(((FilenameURL)this.soundSequenceQueue.get(0)).getURL());
        SoundBuffer buffer = null;
        for (int local_i = 0; (local_i < SoundSystemConfig.getNumberStreamingBuffers()) && (!this.nextCodec.endOfStream()); local_i++)
        {
          buffer = this.nextCodec.read();
          if (buffer != null)
          {
            if (this.nextBuffers == null) {
              this.nextBuffers = new LinkedList();
            }
            this.nextBuffers.add(buffer);
          }
        }
        return true;
      }
    }
    return false;
  }
  
  public int getSoundSequenceQueueSize()
  {
    if (this.soundSequenceQueue == null) {
      return 0;
    }
    return this.soundSequenceQueue.size();
  }
  
  public void setTemporary(boolean tmp)
  {
    this.temporary = tmp;
  }
  
  public void listenerMoved() {}
  
  public void setPosition(float local_x, float local_y, float local_z)
  {
    this.position.field_2107 = local_x;
    this.position.field_2108 = local_y;
    this.position.field_2109 = local_z;
  }
  
  public void positionChanged() {}
  
  public void setPriority(boolean pri)
  {
    this.priority = pri;
  }
  
  public void setLooping(boolean local_lp)
  {
    this.toLoop = local_lp;
  }
  
  public void setAttenuation(int model)
  {
    this.attModel = model;
  }
  
  public void setDistOrRoll(float local_dr)
  {
    this.distOrRoll = local_dr;
  }
  
  public void setVelocity(float local_x, float local_y, float local_z)
  {
    this.velocity.field_2107 = local_x;
    this.velocity.field_2108 = local_y;
    this.velocity.field_2109 = local_z;
  }
  
  public float getDistanceFromListener()
  {
    return this.distanceFromListener;
  }
  
  public void setPitch(float value)
  {
    float newPitch = value;
    if (newPitch < 0.5F) {
      newPitch = 0.5F;
    } else if (newPitch > 2.0F) {
      newPitch = 2.0F;
    }
    this.pitch = newPitch;
  }
  
  public float getPitch()
  {
    return this.pitch;
  }
  
  public boolean reverseByteOrder()
  {
    return SoundSystemConfig.reverseByteOrder(this.libraryType);
  }
  
  public void changeSource(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, SoundBuffer soundBuffer, float local_x, float local_y, float local_z, int attModel, float distOrRoll, boolean temporary)
  {
    this.priority = priority;
    this.toStream = toStream;
    this.toLoop = toLoop;
    this.sourcename = sourcename;
    this.filenameURL = filenameURL;
    this.soundBuffer = soundBuffer;
    this.position.field_2107 = local_x;
    this.position.field_2108 = local_y;
    this.position.field_2109 = local_z;
    this.attModel = attModel;
    this.distOrRoll = distOrRoll;
    this.temporary = temporary;
  }
  
  public int feedRawAudioData(Channel local_c, byte[] buffer)
  {
    if (!active(false, false))
    {
      this.toPlay = true;
      return -1;
    }
    if (this.channel != local_c)
    {
      this.channel = local_c;
      this.channel.close();
      this.channel.setAudioFormat(this.rawDataFormat);
      positionChanged();
    }
    stopped(true, false);
    paused(true, false);
    return this.channel.feedRawAudioData(buffer);
  }
  
  public void play(Channel local_c)
  {
    if (!active(false, false))
    {
      if (this.toLoop) {
        this.toPlay = true;
      }
      return;
    }
    if (this.channel != local_c)
    {
      this.channel = local_c;
      this.channel.close();
    }
    stopped(true, false);
    paused(true, false);
  }
  
  public boolean stream()
  {
    if (this.channel == null) {
      return false;
    }
    if (this.preLoad) {
      if (this.rawDataStream) {
        this.preLoad = false;
      } else {
        return preLoad();
      }
    }
    if (this.rawDataStream)
    {
      if ((stopped()) || (paused())) {
        return true;
      }
      if (this.channel.buffersProcessed() > 0) {
        this.channel.processBuffer();
      }
      return true;
    }
    if (this.codec == null) {
      return false;
    }
    if (stopped()) {
      return false;
    }
    if (paused()) {
      return true;
    }
    int processed = this.channel.buffersProcessed();
    SoundBuffer buffer = null;
    for (int local_i = 0; local_i < processed; local_i++)
    {
      buffer = this.codec.read();
      if (buffer != null)
      {
        if (buffer.audioData != null) {
          this.channel.queueBuffer(buffer.audioData);
        }
        buffer.cleanup();
        buffer = null;
        return true;
      }
      if (this.codec.endOfStream()) {
        synchronized (this.soundSequenceLock)
        {
          if (SoundSystemConfig.getStreamQueueFormatsMatch()) {
            if ((this.soundSequenceQueue != null) && (this.soundSequenceQueue.size() > 0))
            {
              if (this.codec != null) {
                this.codec.cleanup();
              }
              this.filenameURL = ((FilenameURL)this.soundSequenceQueue.remove(0));
              this.codec = SoundSystemConfig.getCodec(this.filenameURL.getFilename());
              this.codec.initialize(this.filenameURL.getURL());
              buffer = this.codec.read();
              if (buffer != null)
              {
                if (buffer.audioData != null) {
                  this.channel.queueBuffer(buffer.audioData);
                }
                buffer.cleanup();
                buffer = null;
                return true;
              }
            }
            else if (this.toLoop)
            {
              this.codec.initialize(this.filenameURL.getURL());
              buffer = this.codec.read();
              if (buffer != null)
              {
                if (buffer.audioData != null) {
                  this.channel.queueBuffer(buffer.audioData);
                }
                buffer.cleanup();
                buffer = null;
                return true;
              }
            }
          }
        }
      }
    }
    return false;
  }
  
  public boolean preLoad()
  {
    if (this.channel == null) {
      return false;
    }
    if (this.codec == null) {
      return false;
    }
    SoundBuffer buffer = null;
    boolean noNextBuffers = false;
    synchronized (this.soundSequenceLock)
    {
      if ((this.nextBuffers == null) || (this.nextBuffers.isEmpty())) {
        noNextBuffers = true;
      }
    }
    if ((this.nextCodec != null) && (!noNextBuffers))
    {
      this.codec = this.nextCodec;
      this.nextCodec = null;
      synchronized (this.soundSequenceLock)
      {
        while (!this.nextBuffers.isEmpty())
        {
          buffer = (SoundBuffer)this.nextBuffers.remove(0);
          if (buffer != null)
          {
            if (buffer.audioData != null) {
              this.channel.queueBuffer(buffer.audioData);
            }
            buffer.cleanup();
            buffer = null;
          }
        }
      }
    }
    else
    {
      this.nextCodec = null;
      URL url = this.filenameURL.getURL();
      this.codec.initialize(url);
      for (int local_i = 0; local_i < SoundSystemConfig.getNumberStreamingBuffers(); local_i++)
      {
        buffer = this.codec.read();
        if (buffer != null)
        {
          if (buffer.audioData != null) {
            this.channel.queueBuffer(buffer.audioData);
          }
          buffer.cleanup();
          buffer = null;
        }
      }
    }
    return true;
  }
  
  public void pause()
  {
    this.toPlay = false;
    paused(true, true);
    if (this.channel != null) {
      this.channel.pause();
    } else {
      errorMessage("Channel null in method 'pause'");
    }
  }
  
  public void stop()
  {
    this.toPlay = false;
    stopped(true, true);
    paused(true, false);
    if (this.channel != null) {
      this.channel.stop();
    } else {
      errorMessage("Channel null in method 'stop'");
    }
  }
  
  public void rewind()
  {
    if (paused(false, false)) {
      stop();
    }
    if (this.channel != null)
    {
      boolean rePlay = playing();
      this.channel.rewind();
      if ((this.toStream) && (rePlay))
      {
        stop();
        play(this.channel);
      }
    }
    else
    {
      errorMessage("Channel null in method 'rewind'");
    }
  }
  
  public void flush()
  {
    if (this.channel != null) {
      this.channel.flush();
    } else {
      errorMessage("Channel null in method 'flush'");
    }
  }
  
  public void cull()
  {
    if (!active(false, false)) {
      return;
    }
    if ((playing()) && (this.toLoop)) {
      this.toPlay = true;
    }
    if (this.rawDataStream) {
      this.toPlay = true;
    }
    active(true, false);
    if (this.channel != null) {
      this.channel.close();
    }
    this.channel = null;
  }
  
  public void activate()
  {
    active(true, true);
  }
  
  public boolean active()
  {
    return active(false, false);
  }
  
  public boolean playing()
  {
    if ((this.channel == null) || (this.channel.attachedSource != this)) {
      return false;
    }
    if ((paused()) || (stopped())) {
      return false;
    }
    return this.channel.playing();
  }
  
  public boolean stopped()
  {
    return stopped(false, false);
  }
  
  public boolean paused()
  {
    return paused(false, false);
  }
  
  public float millisecondsPlayed()
  {
    if (this.channel == null) {
      return -1.0F;
    }
    return this.channel.millisecondsPlayed();
  }
  
  private synchronized boolean active(boolean action, boolean value)
  {
    if (action == true) {
      this.active = value;
    }
    return this.active;
  }
  
  private synchronized boolean stopped(boolean action, boolean value)
  {
    if (action == true) {
      this.stopped = value;
    }
    return this.stopped;
  }
  
  private synchronized boolean paused(boolean action, boolean value)
  {
    if (action == true) {
      this.paused = value;
    }
    return this.paused;
  }
  
  public String getClassName()
  {
    String libTitle = SoundSystemConfig.getLibraryTitle(this.libraryType);
    if (libTitle.equals("No Sound")) {
      return "Source";
    }
    return "Source" + libTitle;
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
 * Qualified Name:     paulscode.sound.Source
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */