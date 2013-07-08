package paulscode.sound;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;
import javax.sound.sampled.AudioFormat;

public class SoundSystem
{
  private static final boolean GET = false;
  private static final boolean SET = true;
  private static final boolean XXX = false;
  protected SoundSystemLogger logger = SoundSystemConfig.getLogger();
  protected Library soundLibrary;
  protected List<CommandObject> commandQueue;
  private List<CommandObject> sourcePlayList;
  protected CommandThread commandThread;
  public Random randomNumberGenerator;
  protected String className = "SoundSystem";
  private static Class currentLibrary = null;
  private static boolean initialized = false;
  private static SoundSystemException lastException = null;
  
  public SoundSystem()
  {
    if (this.logger == null)
    {
      this.logger = new SoundSystemLogger();
      SoundSystemConfig.setLogger(this.logger);
    }
    linkDefaultLibrariesAndCodecs();
    LinkedList<Class> libraries = SoundSystemConfig.getLibraries();
    if (libraries != null)
    {
      ListIterator<Class> local_i = libraries.listIterator();
      while (local_i.hasNext())
      {
        Class local_c = (Class)local_i.next();
        try
        {
          init(local_c);
          return;
        }
        catch (SoundSystemException sse)
        {
          this.logger.printExceptionMessage(sse, 1);
        }
      }
    }
    try
    {
      init(Library.class);
      return;
    }
    catch (SoundSystemException local_i)
    {
      this.logger.printExceptionMessage(local_i, 1);
    }
  }
  
  public SoundSystem(Class libraryClass)
    throws SoundSystemException
  {
    if (this.logger == null)
    {
      this.logger = new SoundSystemLogger();
      SoundSystemConfig.setLogger(this.logger);
    }
    linkDefaultLibrariesAndCodecs();
    init(libraryClass);
  }
  
  protected void linkDefaultLibrariesAndCodecs() {}
  
  protected void init(Class libraryClass)
    throws SoundSystemException
  {
    message("", 0);
    message("Starting up " + this.className + "...", 0);
    this.randomNumberGenerator = new Random();
    this.commandQueue = new LinkedList();
    this.sourcePlayList = new LinkedList();
    this.commandThread = new CommandThread(this);
    this.commandThread.start();
    snooze(200L);
    newLibrary(libraryClass);
    message("", 0);
  }
  
  public void cleanup()
  {
    boolean killException = false;
    message("", 0);
    message(this.className + " shutting down...", 0);
    try
    {
      this.commandThread.kill();
      this.commandThread.interrupt();
    }
    catch (Exception local_e)
    {
      killException = true;
    }
    if (!killException) {
      for (int local_e = 0; (local_e < 50) && (this.commandThread.alive()); local_e++) {
        snooze(100L);
      }
    }
    if ((killException) || (this.commandThread.alive()))
    {
      errorMessage("Command thread did not die!", 0);
      message("Ignoring errors... continuing clean-up.", 0);
    }
    initialized(true, false);
    currentLibrary(true, null);
    try
    {
      if (this.soundLibrary != null) {
        this.soundLibrary.cleanup();
      }
    }
    catch (Exception local_e)
    {
      errorMessage("Problem during Library.cleanup()!", 0);
      message("Ignoring errors... continuing clean-up.", 0);
    }
    try
    {
      if (this.commandQueue != null) {
        this.commandQueue.clear();
      }
    }
    catch (Exception local_e)
    {
      errorMessage("Unable to clear the command queue!", 0);
      message("Ignoring errors... continuing clean-up.", 0);
    }
    try
    {
      if (this.sourcePlayList != null) {
        this.sourcePlayList.clear();
      }
    }
    catch (Exception local_e)
    {
      errorMessage("Unable to clear the source management list!", 0);
      message("Ignoring errors... continuing clean-up.", 0);
    }
    this.randomNumberGenerator = null;
    this.soundLibrary = null;
    this.commandQueue = null;
    this.sourcePlayList = null;
    this.commandThread = null;
    importantMessage("Author: Paul Lamb, www.paulscode.com", 1);
    message("", 0);
  }
  
  public void interruptCommandThread()
  {
    if (this.commandThread == null)
    {
      errorMessage("Command Thread null in method 'interruptCommandThread'", 0);
      return;
    }
    this.commandThread.interrupt();
  }
  
  public void loadSound(String filename)
  {
    CommandQueue(new CommandObject(2, new FilenameURL(filename)));
    this.commandThread.interrupt();
  }
  
  public void loadSound(URL url, String identifier)
  {
    CommandQueue(new CommandObject(2, new FilenameURL(url, identifier)));
    this.commandThread.interrupt();
  }
  
  public void loadSound(byte[] data, AudioFormat format, String identifier)
  {
    CommandQueue(new CommandObject(3, identifier, new SoundBuffer(data, format)));
    this.commandThread.interrupt();
  }
  
  public void unloadSound(String filename)
  {
    CommandQueue(new CommandObject(4, filename));
    this.commandThread.interrupt();
  }
  
  public void queueSound(String sourcename, String filename)
  {
    CommandQueue(new CommandObject(5, sourcename, new FilenameURL(filename)));
    this.commandThread.interrupt();
  }
  
  public void queueSound(String sourcename, URL url, String identifier)
  {
    CommandQueue(new CommandObject(5, sourcename, new FilenameURL(url, identifier)));
    this.commandThread.interrupt();
  }
  
  public void dequeueSound(String sourcename, String filename)
  {
    CommandQueue(new CommandObject(6, sourcename, filename));
    this.commandThread.interrupt();
  }
  
  public void fadeOut(String sourcename, String filename, long milis)
  {
    FilenameURL local_fu = null;
    if (filename != null) {
      local_fu = new FilenameURL(filename);
    }
    CommandQueue(new CommandObject(7, sourcename, local_fu, milis));
    this.commandThread.interrupt();
  }
  
  public void fadeOut(String sourcename, URL url, String identifier, long milis)
  {
    FilenameURL local_fu = null;
    if ((url != null) && (identifier != null)) {
      local_fu = new FilenameURL(url, identifier);
    }
    CommandQueue(new CommandObject(7, sourcename, local_fu, milis));
    this.commandThread.interrupt();
  }
  
  public void fadeOutIn(String sourcename, String filename, long milisOut, long milisIn)
  {
    CommandQueue(new CommandObject(8, sourcename, new FilenameURL(filename), milisOut, milisIn));
    this.commandThread.interrupt();
  }
  
  public void fadeOutIn(String sourcename, URL url, String identifier, long milisOut, long milisIn)
  {
    CommandQueue(new CommandObject(8, sourcename, new FilenameURL(url, identifier), milisOut, milisIn));
    this.commandThread.interrupt();
  }
  
  public void checkFadeVolumes()
  {
    CommandQueue(new CommandObject(9));
    this.commandThread.interrupt();
  }
  
  public void backgroundMusic(String sourcename, String filename, boolean toLoop)
  {
    CommandQueue(new CommandObject(12, true, true, toLoop, sourcename, new FilenameURL(filename), 0.0F, 0.0F, 0.0F, 0, 0.0F, false));
    CommandQueue(new CommandObject(24, sourcename));
    this.commandThread.interrupt();
  }
  
  public void backgroundMusic(String sourcename, URL url, String identifier, boolean toLoop)
  {
    CommandQueue(new CommandObject(12, true, true, toLoop, sourcename, new FilenameURL(url, identifier), 0.0F, 0.0F, 0.0F, 0, 0.0F, false));
    CommandQueue(new CommandObject(24, sourcename));
    this.commandThread.interrupt();
  }
  
  public void newSource(boolean priority, String sourcename, String filename, boolean toLoop, float local_x, float local_y, float local_z, int attmodel, float distOrRoll)
  {
    CommandQueue(new CommandObject(10, priority, false, toLoop, sourcename, new FilenameURL(filename), local_x, local_y, local_z, attmodel, distOrRoll));
    this.commandThread.interrupt();
  }
  
  public void newSource(boolean priority, String sourcename, URL url, String identifier, boolean toLoop, float local_x, float local_y, float local_z, int attmodel, float distOrRoll)
  {
    CommandQueue(new CommandObject(10, priority, false, toLoop, sourcename, new FilenameURL(url, identifier), local_x, local_y, local_z, attmodel, distOrRoll));
    this.commandThread.interrupt();
  }
  
  public void newStreamingSource(boolean priority, String sourcename, String filename, boolean toLoop, float local_x, float local_y, float local_z, int attmodel, float distOrRoll)
  {
    CommandQueue(new CommandObject(10, priority, true, toLoop, sourcename, new FilenameURL(filename), local_x, local_y, local_z, attmodel, distOrRoll));
    this.commandThread.interrupt();
  }
  
  public void newStreamingSource(boolean priority, String sourcename, URL url, String identifier, boolean toLoop, float local_x, float local_y, float local_z, int attmodel, float distOrRoll)
  {
    CommandQueue(new CommandObject(10, priority, true, toLoop, sourcename, new FilenameURL(url, identifier), local_x, local_y, local_z, attmodel, distOrRoll));
    this.commandThread.interrupt();
  }
  
  public void rawDataStream(AudioFormat audioFormat, boolean priority, String sourcename, float local_x, float local_y, float local_z, int attModel, float distOrRoll)
  {
    CommandQueue(new CommandObject(11, audioFormat, priority, sourcename, local_x, local_y, local_z, attModel, distOrRoll));
    this.commandThread.interrupt();
  }
  
  public String quickPlay(boolean priority, String filename, boolean toLoop, float local_x, float local_y, float local_z, int attmodel, float distOrRoll)
  {
    String sourcename = "Source_" + this.randomNumberGenerator.nextInt() + "_" + this.randomNumberGenerator.nextInt();
    CommandQueue(new CommandObject(12, priority, false, toLoop, sourcename, new FilenameURL(filename), local_x, local_y, local_z, attmodel, distOrRoll, true));
    CommandQueue(new CommandObject(24, sourcename));
    this.commandThread.interrupt();
    return sourcename;
  }
  
  public String quickPlay(boolean priority, URL url, String identifier, boolean toLoop, float local_x, float local_y, float local_z, int attmodel, float distOrRoll)
  {
    String sourcename = "Source_" + this.randomNumberGenerator.nextInt() + "_" + this.randomNumberGenerator.nextInt();
    CommandQueue(new CommandObject(12, priority, false, toLoop, sourcename, new FilenameURL(url, identifier), local_x, local_y, local_z, attmodel, distOrRoll, true));
    CommandQueue(new CommandObject(24, sourcename));
    this.commandThread.interrupt();
    return sourcename;
  }
  
  public String quickStream(boolean priority, String filename, boolean toLoop, float local_x, float local_y, float local_z, int attmodel, float distOrRoll)
  {
    String sourcename = "Source_" + this.randomNumberGenerator.nextInt() + "_" + this.randomNumberGenerator.nextInt();
    CommandQueue(new CommandObject(12, priority, true, toLoop, sourcename, new FilenameURL(filename), local_x, local_y, local_z, attmodel, distOrRoll, true));
    CommandQueue(new CommandObject(24, sourcename));
    this.commandThread.interrupt();
    return sourcename;
  }
  
  public String quickStream(boolean priority, URL url, String identifier, boolean toLoop, float local_x, float local_y, float local_z, int attmodel, float distOrRoll)
  {
    String sourcename = "Source_" + this.randomNumberGenerator.nextInt() + "_" + this.randomNumberGenerator.nextInt();
    CommandQueue(new CommandObject(12, priority, true, toLoop, sourcename, new FilenameURL(url, identifier), local_x, local_y, local_z, attmodel, distOrRoll, true));
    CommandQueue(new CommandObject(24, sourcename));
    this.commandThread.interrupt();
    return sourcename;
  }
  
  public void setPosition(String sourcename, float local_x, float local_y, float local_z)
  {
    CommandQueue(new CommandObject(13, sourcename, local_x, local_y, local_z));
    this.commandThread.interrupt();
  }
  
  public void setVolume(String sourcename, float value)
  {
    CommandQueue(new CommandObject(14, sourcename, value));
    this.commandThread.interrupt();
  }
  
  public float getVolume(String sourcename)
  {
    synchronized (SoundSystemConfig.THREAD_SYNC)
    {
      if (this.soundLibrary != null) {
        return this.soundLibrary.getVolume(sourcename);
      }
      return 0.0F;
    }
  }
  
  public void setPitch(String sourcename, float value)
  {
    CommandQueue(new CommandObject(15, sourcename, value));
    this.commandThread.interrupt();
  }
  
  public float getPitch(String sourcename)
  {
    if (this.soundLibrary != null) {
      return this.soundLibrary.getPitch(sourcename);
    }
    return 1.0F;
  }
  
  public void setPriority(String sourcename, boolean pri)
  {
    CommandQueue(new CommandObject(16, sourcename, pri));
    this.commandThread.interrupt();
  }
  
  public void setLooping(String sourcename, boolean local_lp)
  {
    CommandQueue(new CommandObject(17, sourcename, local_lp));
    this.commandThread.interrupt();
  }
  
  public void setAttenuation(String sourcename, int model)
  {
    CommandQueue(new CommandObject(18, sourcename, model));
    this.commandThread.interrupt();
  }
  
  public void setDistOrRoll(String sourcename, float local_dr)
  {
    CommandQueue(new CommandObject(19, sourcename, local_dr));
    this.commandThread.interrupt();
  }
  
  public void changeDopplerFactor(float dopplerFactor)
  {
    CommandQueue(new CommandObject(20, dopplerFactor));
    this.commandThread.interrupt();
  }
  
  public void changeDopplerVelocity(float dopplerVelocity)
  {
    CommandQueue(new CommandObject(21, dopplerVelocity));
    this.commandThread.interrupt();
  }
  
  public void setVelocity(String sourcename, float local_x, float local_y, float local_z)
  {
    CommandQueue(new CommandObject(22, sourcename, local_x, local_y, local_z));
    this.commandThread.interrupt();
  }
  
  public void setListenerVelocity(float local_x, float local_y, float local_z)
  {
    CommandQueue(new CommandObject(23, local_x, local_y, local_z));
    this.commandThread.interrupt();
  }
  
  public float millisecondsPlayed(String sourcename)
  {
    synchronized (SoundSystemConfig.THREAD_SYNC)
    {
      return this.soundLibrary.millisecondsPlayed(sourcename);
    }
  }
  
  public void feedRawAudioData(String sourcename, byte[] buffer)
  {
    CommandQueue(new CommandObject(25, sourcename, buffer));
    this.commandThread.interrupt();
  }
  
  public void play(String sourcename)
  {
    CommandQueue(new CommandObject(24, sourcename));
    this.commandThread.interrupt();
  }
  
  public void pause(String sourcename)
  {
    CommandQueue(new CommandObject(26, sourcename));
    this.commandThread.interrupt();
  }
  
  public void stop(String sourcename)
  {
    CommandQueue(new CommandObject(27, sourcename));
    this.commandThread.interrupt();
  }
  
  public void rewind(String sourcename)
  {
    CommandQueue(new CommandObject(28, sourcename));
    this.commandThread.interrupt();
  }
  
  public void flush(String sourcename)
  {
    CommandQueue(new CommandObject(29, sourcename));
    this.commandThread.interrupt();
  }
  
  public void cull(String sourcename)
  {
    CommandQueue(new CommandObject(30, sourcename));
    this.commandThread.interrupt();
  }
  
  public void activate(String sourcename)
  {
    CommandQueue(new CommandObject(31, sourcename));
    this.commandThread.interrupt();
  }
  
  public void setTemporary(String sourcename, boolean temporary)
  {
    CommandQueue(new CommandObject(32, sourcename, temporary));
    this.commandThread.interrupt();
  }
  
  public void removeSource(String sourcename)
  {
    CommandQueue(new CommandObject(33, sourcename));
    this.commandThread.interrupt();
  }
  
  public void moveListener(float local_x, float local_y, float local_z)
  {
    CommandQueue(new CommandObject(34, local_x, local_y, local_z));
    this.commandThread.interrupt();
  }
  
  public void setListenerPosition(float local_x, float local_y, float local_z)
  {
    CommandQueue(new CommandObject(35, local_x, local_y, local_z));
    this.commandThread.interrupt();
  }
  
  public void turnListener(float angle)
  {
    CommandQueue(new CommandObject(36, angle));
    this.commandThread.interrupt();
  }
  
  public void setListenerAngle(float angle)
  {
    CommandQueue(new CommandObject(37, angle));
    this.commandThread.interrupt();
  }
  
  public void setListenerOrientation(float lookX, float lookY, float lookZ, float upX, float upY, float upZ)
  {
    CommandQueue(new CommandObject(38, lookX, lookY, lookZ, upX, upY, upZ));
    this.commandThread.interrupt();
  }
  
  public void setMasterVolume(float value)
  {
    CommandQueue(new CommandObject(39, value));
    this.commandThread.interrupt();
  }
  
  public float getMasterVolume()
  {
    return SoundSystemConfig.getMasterGain();
  }
  
  public ListenerData getListenerData()
  {
    synchronized (SoundSystemConfig.THREAD_SYNC)
    {
      return this.soundLibrary.getListenerData();
    }
  }
  
  public boolean switchLibrary(Class libraryClass)
    throws SoundSystemException
  {
    synchronized (SoundSystemConfig.THREAD_SYNC)
    {
      initialized(true, false);
      HashMap<String, Source> sourceMap = null;
      ListenerData listenerData = null;
      boolean wasMidiChannel = false;
      MidiChannel midiChannel = null;
      FilenameURL midiFilenameURL = null;
      String midiSourcename = "";
      boolean midiToLoop = true;
      if (this.soundLibrary != null)
      {
        currentLibrary(true, null);
        sourceMap = copySources(this.soundLibrary.getSources());
        listenerData = this.soundLibrary.getListenerData();
        midiChannel = this.soundLibrary.getMidiChannel();
        if (midiChannel != null)
        {
          wasMidiChannel = true;
          midiToLoop = midiChannel.getLooping();
          midiSourcename = midiChannel.getSourcename();
          midiFilenameURL = midiChannel.getFilenameURL();
        }
        this.soundLibrary.cleanup();
        this.soundLibrary = null;
      }
      message("", 0);
      message("Switching to " + SoundSystemConfig.getLibraryTitle(libraryClass), 0);
      message("(" + SoundSystemConfig.getLibraryDescription(libraryClass) + ")", 1);
      try
      {
        this.soundLibrary = ((Library)libraryClass.newInstance());
      }
      catch (InstantiationException local_ie)
      {
        errorMessage("The specified library did not load properly", 1);
      }
      catch (IllegalAccessException local_ie)
      {
        errorMessage("The specified library did not load properly", 1);
      }
      catch (ExceptionInInitializerError local_ie)
      {
        errorMessage("The specified library did not load properly", 1);
      }
      catch (SecurityException local_ie)
      {
        errorMessage("The specified library did not load properly", 1);
      }
      if (errorCheck(this.soundLibrary == null, "Library null after initialization in method 'switchLibrary'", 1))
      {
        SoundSystemException local_ie = new SoundSystemException(this.className + " did not load properly.  " + "Library was null after initialization.", 4);
        lastException(true, local_ie);
        initialized(true, true);
        throw local_ie;
      }
      try
      {
        this.soundLibrary.init();
      }
      catch (SoundSystemException local_ie)
      {
        lastException(true, local_ie);
        initialized(true, true);
        throw local_ie;
      }
      this.soundLibrary.setListenerData(listenerData);
      if (wasMidiChannel)
      {
        if (midiChannel != null) {
          midiChannel.cleanup();
        }
        midiChannel = new MidiChannel(midiToLoop, midiSourcename, midiFilenameURL);
        this.soundLibrary.setMidiChannel(midiChannel);
      }
      this.soundLibrary.copySources(sourceMap);
      message("", 0);
      lastException(true, null);
      initialized(true, true);
      return true;
    }
  }
  
  public boolean newLibrary(Class libraryClass)
    throws SoundSystemException
  {
    initialized(true, false);
    CommandQueue(new CommandObject(40, libraryClass));
    this.commandThread.interrupt();
    for (int local_x = 0; (!initialized(false, false)) && (local_x < 100); local_x++)
    {
      snooze(400L);
      this.commandThread.interrupt();
    }
    if (!initialized(false, false))
    {
      SoundSystemException local_x = new SoundSystemException(this.className + " did not load after 30 seconds.", 4);
      lastException(true, local_x);
      throw local_x;
    }
    SoundSystemException local_x = lastException(false, null);
    if (local_x != null) {
      throw local_x;
    }
    return true;
  }
  
  private void CommandNewLibrary(Class libraryClass)
  {
    initialized(true, false);
    String headerMessage = "Initializing ";
    if (this.soundLibrary != null)
    {
      currentLibrary(true, null);
      headerMessage = "Switching to ";
      this.soundLibrary.cleanup();
      this.soundLibrary = null;
    }
    message(headerMessage + SoundSystemConfig.getLibraryTitle(libraryClass), 0);
    message("(" + SoundSystemConfig.getLibraryDescription(libraryClass) + ")", 1);
    try
    {
      this.soundLibrary = ((Library)libraryClass.newInstance());
    }
    catch (InstantiationException local_ie)
    {
      errorMessage("The specified library did not load properly", 1);
    }
    catch (IllegalAccessException local_ie)
    {
      errorMessage("The specified library did not load properly", 1);
    }
    catch (ExceptionInInitializerError local_ie)
    {
      errorMessage("The specified library did not load properly", 1);
    }
    catch (SecurityException local_ie)
    {
      errorMessage("The specified library did not load properly", 1);
    }
    if (errorCheck(this.soundLibrary == null, "Library null after initialization in method 'newLibrary'", 1))
    {
      lastException(true, new SoundSystemException(this.className + " did not load properly.  " + "Library was null after initialization.", 4));
      importantMessage("Switching to silent mode", 1);
      try
      {
        this.soundLibrary = new Library();
      }
      catch (SoundSystemException local_ie)
      {
        lastException(true, new SoundSystemException("Silent mode did not load properly.  Library was null after initialization.", 4));
        initialized(true, true);
        return;
      }
    }
    try
    {
      this.soundLibrary.init();
    }
    catch (SoundSystemException local_ie)
    {
      lastException(true, local_ie);
      initialized(true, true);
      return;
    }
    lastException(true, null);
    initialized(true, true);
  }
  
  private void CommandInitialize()
  {
    try
    {
      if (errorCheck(this.soundLibrary == null, "Library null after initialization in method 'CommandInitialize'", 1))
      {
        SoundSystemException sse = new SoundSystemException(this.className + " did not load properly.  " + "Library was null after initialization.", 4);
        lastException(true, sse);
        throw sse;
      }
      this.soundLibrary.init();
    }
    catch (SoundSystemException sse)
    {
      lastException(true, sse);
      initialized(true, true);
    }
  }
  
  private void CommandLoadSound(FilenameURL filenameURL)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.loadSound(filenameURL);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandLoadSound'", 0);
    }
  }
  
  private void CommandLoadSound(SoundBuffer buffer, String identifier)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.loadSound(buffer, identifier);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandLoadSound'", 0);
    }
  }
  
  private void CommandUnloadSound(String filename)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.unloadSound(filename);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandLoadSound'", 0);
    }
  }
  
  private void CommandQueueSound(String sourcename, FilenameURL filenameURL)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.queueSound(sourcename, filenameURL);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandQueueSound'", 0);
    }
  }
  
  private void CommandDequeueSound(String sourcename, String filename)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.dequeueSound(sourcename, filename);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandDequeueSound'", 0);
    }
  }
  
  private void CommandFadeOut(String sourcename, FilenameURL filenameURL, long milis)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.fadeOut(sourcename, filenameURL, milis);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandFadeOut'", 0);
    }
  }
  
  private void CommandFadeOutIn(String sourcename, FilenameURL filenameURL, long milisOut, long milisIn)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.fadeOutIn(sourcename, filenameURL, milisOut, milisIn);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandFadeOutIn'", 0);
    }
  }
  
  private void CommandCheckFadeVolumes()
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.checkFadeVolumes();
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandCheckFadeVolumes'", 0);
    }
  }
  
  private void CommandNewSource(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float local_x, float local_y, float local_z, int attModel, float distORroll)
  {
    if (this.soundLibrary != null)
    {
      if ((filenameURL.getFilename().matches(SoundSystemConfig.EXTENSION_MIDI)) && (!SoundSystemConfig.midiCodec())) {
        this.soundLibrary.loadMidi(toLoop, sourcename, filenameURL);
      } else {
        this.soundLibrary.newSource(priority, toStream, toLoop, sourcename, filenameURL, local_x, local_y, local_z, attModel, distORroll);
      }
    }
    else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandNewSource'", 0);
    }
  }
  
  private void CommandRawDataStream(AudioFormat audioFormat, boolean priority, String sourcename, float local_x, float local_y, float local_z, int attModel, float distOrRoll)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.rawDataStream(audioFormat, priority, sourcename, local_x, local_y, local_z, attModel, distOrRoll);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandRawDataStream'", 0);
    }
  }
  
  private void CommandQuickPlay(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float local_x, float local_y, float local_z, int attModel, float distORroll, boolean temporary)
  {
    if (this.soundLibrary != null)
    {
      if ((filenameURL.getFilename().matches(SoundSystemConfig.EXTENSION_MIDI)) && (!SoundSystemConfig.midiCodec())) {
        this.soundLibrary.loadMidi(toLoop, sourcename, filenameURL);
      } else {
        this.soundLibrary.quickPlay(priority, toStream, toLoop, sourcename, filenameURL, local_x, local_y, local_z, attModel, distORroll, temporary);
      }
    }
    else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandQuickPlay'", 0);
    }
  }
  
  private void CommandSetPosition(String sourcename, float local_x, float local_y, float local_z)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.setPosition(sourcename, local_x, local_y, local_z);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandMoveSource'", 0);
    }
  }
  
  private void CommandSetVolume(String sourcename, float value)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.setVolume(sourcename, value);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandSetVolume'", 0);
    }
  }
  
  private void CommandSetPitch(String sourcename, float value)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.setPitch(sourcename, value);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandSetPitch'", 0);
    }
  }
  
  private void CommandSetPriority(String sourcename, boolean pri)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.setPriority(sourcename, pri);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandSetPriority'", 0);
    }
  }
  
  private void CommandSetLooping(String sourcename, boolean local_lp)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.setLooping(sourcename, local_lp);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandSetLooping'", 0);
    }
  }
  
  private void CommandSetAttenuation(String sourcename, int model)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.setAttenuation(sourcename, model);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandSetAttenuation'", 0);
    }
  }
  
  private void CommandSetDistOrRoll(String sourcename, float local_dr)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.setDistOrRoll(sourcename, local_dr);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandSetDistOrRoll'", 0);
    }
  }
  
  private void CommandChangeDopplerFactor(float dopplerFactor)
  {
    if (this.soundLibrary != null)
    {
      SoundSystemConfig.setDopplerFactor(dopplerFactor);
      this.soundLibrary.dopplerChanged();
    }
    else
    {
      errorMessage("Variable 'soundLibrary' null in method 'CommandSetDopplerFactor'", 0);
    }
  }
  
  private void CommandChangeDopplerVelocity(float dopplerVelocity)
  {
    if (this.soundLibrary != null)
    {
      SoundSystemConfig.setDopplerVelocity(dopplerVelocity);
      this.soundLibrary.dopplerChanged();
    }
    else
    {
      errorMessage("Variable 'soundLibrary' null in method 'CommandSetDopplerFactor'", 0);
    }
  }
  
  private void CommandSetVelocity(String sourcename, float local_x, float local_y, float local_z)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.setVelocity(sourcename, local_x, local_y, local_z);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandVelocity'", 0);
    }
  }
  
  private void CommandSetListenerVelocity(float local_x, float local_y, float local_z)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.setListenerVelocity(local_x, local_y, local_z);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandSetListenerVelocity'", 0);
    }
  }
  
  private void CommandPlay(String sourcename)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.play(sourcename);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandPlay'", 0);
    }
  }
  
  private void CommandFeedRawAudioData(String sourcename, byte[] buffer)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.feedRawAudioData(sourcename, buffer);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandFeedRawAudioData'", 0);
    }
  }
  
  private void CommandPause(String sourcename)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.pause(sourcename);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandPause'", 0);
    }
  }
  
  private void CommandStop(String sourcename)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.stop(sourcename);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandStop'", 0);
    }
  }
  
  private void CommandRewind(String sourcename)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.rewind(sourcename);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandRewind'", 0);
    }
  }
  
  private void CommandFlush(String sourcename)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.flush(sourcename);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandFlush'", 0);
    }
  }
  
  private void CommandSetTemporary(String sourcename, boolean temporary)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.setTemporary(sourcename, temporary);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandSetActive'", 0);
    }
  }
  
  private void CommandRemoveSource(String sourcename)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.removeSource(sourcename);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandRemoveSource'", 0);
    }
  }
  
  private void CommandMoveListener(float local_x, float local_y, float local_z)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.moveListener(local_x, local_y, local_z);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandMoveListener'", 0);
    }
  }
  
  private void CommandSetListenerPosition(float local_x, float local_y, float local_z)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.setListenerPosition(local_x, local_y, local_z);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandSetListenerPosition'", 0);
    }
  }
  
  private void CommandTurnListener(float angle)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.turnListener(angle);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandTurnListener'", 0);
    }
  }
  
  private void CommandSetListenerAngle(float angle)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.setListenerAngle(angle);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandSetListenerAngle'", 0);
    }
  }
  
  private void CommandSetListenerOrientation(float lookX, float lookY, float lookZ, float upX, float upY, float upZ)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.setListenerOrientation(lookX, lookY, lookZ, upX, upY, upZ);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandSetListenerOrientation'", 0);
    }
  }
  
  private void CommandCull(String sourcename)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.cull(sourcename);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandCull'", 0);
    }
  }
  
  private void CommandActivate(String sourcename)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.activate(sourcename);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandActivate'", 0);
    }
  }
  
  private void CommandSetMasterVolume(float value)
  {
    if (this.soundLibrary != null) {
      this.soundLibrary.setMasterVolume(value);
    } else {
      errorMessage("Variable 'soundLibrary' null in method 'CommandSetMasterVolume'", 0);
    }
  }
  
  protected void ManageSources() {}
  
  public boolean CommandQueue(CommandObject newCommand)
  {
    synchronized (SoundSystemConfig.THREAD_SYNC)
    {
      if (newCommand == null)
      {
        boolean activations = false;
        while ((this.commandQueue != null) && (this.commandQueue.size() > 0))
        {
          CommandObject commandObject = (CommandObject)this.commandQueue.remove(0);
          if (commandObject != null) {
            switch (commandObject.Command)
            {
            case 1: 
              CommandInitialize();
              break;
            case 2: 
              CommandLoadSound((FilenameURL)commandObject.objectArgs[0]);
              break;
            case 3: 
              CommandLoadSound((SoundBuffer)commandObject.objectArgs[0], commandObject.stringArgs[0]);
              break;
            case 4: 
              CommandUnloadSound(commandObject.stringArgs[0]);
              break;
            case 5: 
              CommandQueueSound(commandObject.stringArgs[0], (FilenameURL)commandObject.objectArgs[0]);
              break;
            case 6: 
              CommandDequeueSound(commandObject.stringArgs[0], commandObject.stringArgs[1]);
              break;
            case 7: 
              CommandFadeOut(commandObject.stringArgs[0], (FilenameURL)commandObject.objectArgs[0], commandObject.longArgs[0]);
              break;
            case 8: 
              CommandFadeOutIn(commandObject.stringArgs[0], (FilenameURL)commandObject.objectArgs[0], commandObject.longArgs[0], commandObject.longArgs[1]);
              break;
            case 9: 
              CommandCheckFadeVolumes();
              break;
            case 10: 
              CommandNewSource(commandObject.boolArgs[0], commandObject.boolArgs[1], commandObject.boolArgs[2], commandObject.stringArgs[0], (FilenameURL)commandObject.objectArgs[0], commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2], commandObject.intArgs[0], commandObject.floatArgs[3]);
              break;
            case 11: 
              CommandRawDataStream((AudioFormat)commandObject.objectArgs[0], commandObject.boolArgs[0], commandObject.stringArgs[0], commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2], commandObject.intArgs[0], commandObject.floatArgs[3]);
              break;
            case 12: 
              CommandQuickPlay(commandObject.boolArgs[0], commandObject.boolArgs[1], commandObject.boolArgs[2], commandObject.stringArgs[0], (FilenameURL)commandObject.objectArgs[0], commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2], commandObject.intArgs[0], commandObject.floatArgs[3], commandObject.boolArgs[3]);
              break;
            case 13: 
              CommandSetPosition(commandObject.stringArgs[0], commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2]);
              break;
            case 14: 
              CommandSetVolume(commandObject.stringArgs[0], commandObject.floatArgs[0]);
              break;
            case 15: 
              CommandSetPitch(commandObject.stringArgs[0], commandObject.floatArgs[0]);
              break;
            case 16: 
              CommandSetPriority(commandObject.stringArgs[0], commandObject.boolArgs[0]);
              break;
            case 17: 
              CommandSetLooping(commandObject.stringArgs[0], commandObject.boolArgs[0]);
              break;
            case 18: 
              CommandSetAttenuation(commandObject.stringArgs[0], commandObject.intArgs[0]);
              break;
            case 19: 
              CommandSetDistOrRoll(commandObject.stringArgs[0], commandObject.floatArgs[0]);
              break;
            case 20: 
              CommandChangeDopplerFactor(commandObject.floatArgs[0]);
              break;
            case 21: 
              CommandChangeDopplerVelocity(commandObject.floatArgs[0]);
              break;
            case 22: 
              CommandSetVelocity(commandObject.stringArgs[0], commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2]);
              break;
            case 23: 
              CommandSetListenerVelocity(commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2]);
              break;
            case 24: 
              this.sourcePlayList.add(commandObject);
              break;
            case 25: 
              this.sourcePlayList.add(commandObject);
              break;
            case 26: 
              CommandPause(commandObject.stringArgs[0]);
              break;
            case 27: 
              CommandStop(commandObject.stringArgs[0]);
              break;
            case 28: 
              CommandRewind(commandObject.stringArgs[0]);
              break;
            case 29: 
              CommandFlush(commandObject.stringArgs[0]);
              break;
            case 30: 
              CommandCull(commandObject.stringArgs[0]);
              break;
            case 31: 
              activations = true;
              CommandActivate(commandObject.stringArgs[0]);
              break;
            case 32: 
              CommandSetTemporary(commandObject.stringArgs[0], commandObject.boolArgs[0]);
              break;
            case 33: 
              CommandRemoveSource(commandObject.stringArgs[0]);
              break;
            case 34: 
              CommandMoveListener(commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2]);
              break;
            case 35: 
              CommandSetListenerPosition(commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2]);
              break;
            case 36: 
              CommandTurnListener(commandObject.floatArgs[0]);
              break;
            case 37: 
              CommandSetListenerAngle(commandObject.floatArgs[0]);
              break;
            case 38: 
              CommandSetListenerOrientation(commandObject.floatArgs[0], commandObject.floatArgs[1], commandObject.floatArgs[2], commandObject.floatArgs[3], commandObject.floatArgs[4], commandObject.floatArgs[5]);
              break;
            case 39: 
              CommandSetMasterVolume(commandObject.floatArgs[0]);
              break;
            case 40: 
              CommandNewLibrary(commandObject.classArgs[0]);
            }
          }
        }
        if (activations) {
          this.soundLibrary.replaySources();
        }
        while ((this.sourcePlayList != null) && (this.sourcePlayList.size() > 0))
        {
          CommandObject commandObject = (CommandObject)this.sourcePlayList.remove(0);
          if (commandObject != null) {
            switch (commandObject.Command)
            {
            case 24: 
              CommandPlay(commandObject.stringArgs[0]);
              break;
            case 25: 
              CommandFeedRawAudioData(commandObject.stringArgs[0], commandObject.buffer);
            }
          }
        }
        return (this.commandQueue != null) && (this.commandQueue.size() > 0);
      }
      if (this.commandQueue == null) {
        return false;
      }
      this.commandQueue.add(newCommand);
      return true;
    }
  }
  
  public void removeTemporarySources()
  {
    synchronized (SoundSystemConfig.THREAD_SYNC)
    {
      if (this.soundLibrary != null) {
        this.soundLibrary.removeTemporarySources();
      }
    }
  }
  
  public boolean playing(String sourcename)
  {
    synchronized (SoundSystemConfig.THREAD_SYNC)
    {
      if (this.soundLibrary == null) {
        return false;
      }
      Source src = (Source)this.soundLibrary.getSources().get(sourcename);
      if (src == null) {
        return false;
      }
      return src.playing();
    }
  }
  
  public boolean playing()
  {
    synchronized (SoundSystemConfig.THREAD_SYNC)
    {
      if (this.soundLibrary == null) {
        return false;
      }
      HashMap<String, Source> sourceMap = this.soundLibrary.getSources();
      if (sourceMap == null) {
        return false;
      }
      Set<String> keys = sourceMap.keySet();
      Iterator<String> iter = keys.iterator();
      while (iter.hasNext())
      {
        String sourcename = (String)iter.next();
        Source source = (Source)sourceMap.get(sourcename);
        if ((source != null) && (source.playing())) {
          return true;
        }
      }
      return false;
    }
  }
  
  private HashMap<String, Source> copySources(HashMap<String, Source> sourceMap)
  {
    Set<String> keys = sourceMap.keySet();
    Iterator<String> iter = keys.iterator();
    HashMap<String, Source> returnMap = new HashMap();
    while (iter.hasNext())
    {
      String sourcename = (String)iter.next();
      Source source = (Source)sourceMap.get(sourcename);
      if (source != null) {
        returnMap.put(sourcename, new Source(source, null));
      }
    }
    return returnMap;
  }
  
  public static boolean libraryCompatible(Class libraryClass)
  {
    SoundSystemLogger logger = SoundSystemConfig.getLogger();
    if (logger == null)
    {
      logger = new SoundSystemLogger();
      SoundSystemConfig.setLogger(logger);
    }
    logger.message("", 0);
    logger.message("Checking if " + SoundSystemConfig.getLibraryTitle(libraryClass) + " is compatible...", 0);
    boolean comp = SoundSystemConfig.libraryCompatible(libraryClass);
    if (comp) {
      logger.message("...yes", 1);
    } else {
      logger.message("...no", 1);
    }
    return comp;
  }
  
  public static Class currentLibrary()
  {
    return currentLibrary(false, null);
  }
  
  public static boolean initialized()
  {
    return initialized(false, false);
  }
  
  public static SoundSystemException getLastException()
  {
    return lastException(false, null);
  }
  
  public static void setException(SoundSystemException local_e)
  {
    lastException(true, local_e);
  }
  
  private static boolean initialized(boolean action, boolean value)
  {
    synchronized (SoundSystemConfig.THREAD_SYNC)
    {
      if (action == true) {
        initialized = value;
      }
      return initialized;
    }
  }
  
  private static Class currentLibrary(boolean action, Class value)
  {
    synchronized (SoundSystemConfig.THREAD_SYNC)
    {
      if (action == true) {
        currentLibrary = value;
      }
      return currentLibrary;
    }
  }
  
  private static SoundSystemException lastException(boolean action, SoundSystemException local_e)
  {
    synchronized (SoundSystemConfig.THREAD_SYNC)
    {
      if (action == true) {
        lastException = local_e;
      }
      return lastException;
    }
  }
  
  protected static void snooze(long milliseconds)
  {
    try
    {
      Thread.sleep(milliseconds);
    }
    catch (InterruptedException local_e) {}
  }
  
  protected void message(String message, int indent)
  {
    this.logger.message(message, indent);
  }
  
  protected void importantMessage(String message, int indent)
  {
    this.logger.importantMessage(message, indent);
  }
  
  protected boolean errorCheck(boolean error, String message, int indent)
  {
    return this.logger.errorCheck(error, this.className, message, indent);
  }
  
  protected void errorMessage(String message, int indent)
  {
    this.logger.errorMessage(this.className, message, indent);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.SoundSystem
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */