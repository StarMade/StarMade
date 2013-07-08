package paulscode.sound;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;

public class MidiChannel
  implements MetaEventListener
{
  private SoundSystemLogger logger;
  private FilenameURL filenameURL;
  private String sourcename;
  private static final int CHANGE_VOLUME = 7;
  private static final int END_OF_TRACK = 47;
  private static final boolean GET = false;
  private static final boolean SET = true;
  private static final boolean XXX = false;
  private Sequencer sequencer = null;
  private Synthesizer synthesizer = null;
  private MidiDevice synthDevice = null;
  private Sequence sequence = null;
  private boolean toLoop = true;
  private float gain = 1.0F;
  private boolean loading = true;
  private LinkedList<FilenameURL> sequenceQueue = null;
  private final Object sequenceQueueLock = new Object();
  protected float fadeOutGain = -1.0F;
  protected float fadeInGain = 1.0F;
  protected long fadeOutMilis = 0L;
  protected long fadeInMilis = 0L;
  protected long lastFadeCheck = 0L;
  private FadeThread fadeThread = null;
  
  public MidiChannel(boolean toLoop, String sourcename, String filename)
  {
    loading(true, true);
    this.logger = SoundSystemConfig.getLogger();
    filenameURL(true, new FilenameURL(filename));
    sourcename(true, sourcename);
    setLooping(toLoop);
    init();
    loading(true, false);
  }
  
  public MidiChannel(boolean toLoop, String sourcename, URL midiFile, String identifier)
  {
    loading(true, true);
    this.logger = SoundSystemConfig.getLogger();
    filenameURL(true, new FilenameURL(midiFile, identifier));
    sourcename(true, sourcename);
    setLooping(toLoop);
    init();
    loading(true, false);
  }
  
  public MidiChannel(boolean toLoop, String sourcename, FilenameURL midiFilenameURL)
  {
    loading(true, true);
    this.logger = SoundSystemConfig.getLogger();
    filenameURL(true, midiFilenameURL);
    sourcename(true, sourcename);
    setLooping(toLoop);
    init();
    loading(true, false);
  }
  
  private void init()
  {
    getSequencer();
    setSequence(filenameURL(false, null).getURL());
    getSynthesizer();
    resetGain();
  }
  
  public void cleanup()
  {
    loading(true, true);
    setLooping(true);
    if (this.sequencer != null) {
      try
      {
        this.sequencer.stop();
        this.sequencer.close();
        this.sequencer.removeMetaEventListener(this);
      }
      catch (Exception local_e) {}
    }
    this.logger = null;
    this.sequencer = null;
    this.synthesizer = null;
    this.sequence = null;
    synchronized (this.sequenceQueueLock)
    {
      if (this.sequenceQueue != null) {
        this.sequenceQueue.clear();
      }
      this.sequenceQueue = null;
    }
    if (this.fadeThread != null)
    {
      boolean local_e = false;
      try
      {
        this.fadeThread.kill();
        this.fadeThread.interrupt();
      }
      catch (Exception local_e1)
      {
        local_e = true;
      }
      if (!local_e) {
        for (int local_e1 = 0; (local_e1 < 50) && (this.fadeThread.alive()); local_e1++) {
          try
          {
            Thread.sleep(100L);
          }
          catch (InterruptedException local_e2) {}
        }
      }
      if ((local_e) || (this.fadeThread.alive()))
      {
        errorMessage("MIDI fade effects thread did not die!");
        message("Ignoring errors... continuing clean-up.");
      }
    }
    this.fadeThread = null;
    loading(true, false);
  }
  
  public void queueSound(FilenameURL filenameURL)
  {
    if (filenameURL == null)
    {
      errorMessage("Filename/URL not specified in method 'queueSound'");
      return;
    }
    synchronized (this.sequenceQueueLock)
    {
      if (this.sequenceQueue == null) {
        this.sequenceQueue = new LinkedList();
      }
      this.sequenceQueue.add(filenameURL);
    }
  }
  
  public void dequeueSound(String filename)
  {
    if ((filename == null) || (filename.equals("")))
    {
      errorMessage("Filename not specified in method 'dequeueSound'");
      return;
    }
    synchronized (this.sequenceQueueLock)
    {
      if (this.sequenceQueue != null)
      {
        ListIterator<FilenameURL> local_i = this.sequenceQueue.listIterator();
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
    if (milis < 0L)
    {
      errorMessage("Miliseconds may not be negative in method 'fadeOut'.");
      return;
    }
    this.fadeOutMilis = milis;
    this.fadeInMilis = 0L;
    this.fadeOutGain = 1.0F;
    this.lastFadeCheck = System.currentTimeMillis();
    synchronized (this.sequenceQueueLock)
    {
      if (this.sequenceQueue != null) {
        this.sequenceQueue.clear();
      }
      if (filenameURL != null)
      {
        if (this.sequenceQueue == null) {
          this.sequenceQueue = new LinkedList();
        }
        this.sequenceQueue.add(filenameURL);
      }
    }
    if (this.fadeThread == null)
    {
      this.fadeThread = new FadeThread(null);
      this.fadeThread.start();
    }
    this.fadeThread.interrupt();
  }
  
  public void fadeOutIn(FilenameURL filenameURL, long milisOut, long milisIn)
  {
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
    synchronized (this.sequenceQueueLock)
    {
      if (this.sequenceQueue == null) {
        this.sequenceQueue = new LinkedList();
      }
      this.sequenceQueue.clear();
      this.sequenceQueue.add(filenameURL);
    }
    if (this.fadeThread == null)
    {
      this.fadeThread = new FadeThread(null);
      this.fadeThread.start();
    }
    this.fadeThread.interrupt();
  }
  
  private synchronized boolean checkFadeOut()
  {
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
        this.fadeOutGain = 0.0F;
        this.fadeInGain = 0.0F;
        if (!incrementSequence()) {
          stop();
        }
        rewind();
        resetGain();
        return false;
      }
      float fadeOutReduction = (float)milisPast / (float)this.fadeOutMilis;
      this.fadeOutGain -= fadeOutReduction;
      if (this.fadeOutGain <= 0.0F)
      {
        this.fadeOutGain = -1.0F;
        this.fadeInGain = 0.0F;
        if (!incrementSequence()) {
          stop();
        }
        rewind();
        resetGain();
        return false;
      }
      resetGain();
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
      resetGain();
    }
    return false;
  }
  
  private boolean incrementSequence()
  {
    synchronized (this.sequenceQueueLock)
    {
      if ((this.sequenceQueue != null) && (this.sequenceQueue.size() > 0))
      {
        filenameURL(true, (FilenameURL)this.sequenceQueue.remove(0));
        loading(true, true);
        if (this.sequencer == null)
        {
          getSequencer();
        }
        else
        {
          this.sequencer.stop();
          this.sequencer.setMicrosecondPosition(0L);
          this.sequencer.removeMetaEventListener(this);
          try
          {
            Thread.sleep(100L);
          }
          catch (InterruptedException local_e) {}
        }
        if (this.sequencer == null)
        {
          errorMessage("Unable to set the sequence in method 'incrementSequence', because there wasn't a sequencer to use.");
          loading(true, false);
          return false;
        }
        setSequence(filenameURL(false, null).getURL());
        this.sequencer.start();
        resetGain();
        this.sequencer.addMetaEventListener(this);
        loading(true, false);
        return true;
      }
    }
    return false;
  }
  
  public void play()
  {
    if (!loading())
    {
      if (this.sequencer == null) {
        return;
      }
      try
      {
        this.sequencer.start();
        this.sequencer.addMetaEventListener(this);
      }
      catch (Exception local_e)
      {
        errorMessage("Exception in method 'play'");
        printStackTrace(local_e);
        SoundSystemException sse = new SoundSystemException(local_e.getMessage());
        SoundSystem.setException(sse);
      }
    }
  }
  
  public void stop()
  {
    if (!loading())
    {
      if (this.sequencer == null) {
        return;
      }
      try
      {
        this.sequencer.stop();
        this.sequencer.setMicrosecondPosition(0L);
        this.sequencer.removeMetaEventListener(this);
      }
      catch (Exception local_e)
      {
        errorMessage("Exception in method 'stop'");
        printStackTrace(local_e);
        SoundSystemException sse = new SoundSystemException(local_e.getMessage());
        SoundSystem.setException(sse);
      }
    }
  }
  
  public void pause()
  {
    if (!loading())
    {
      if (this.sequencer == null) {
        return;
      }
      try
      {
        this.sequencer.stop();
      }
      catch (Exception local_e)
      {
        errorMessage("Exception in method 'pause'");
        printStackTrace(local_e);
        SoundSystemException sse = new SoundSystemException(local_e.getMessage());
        SoundSystem.setException(sse);
      }
    }
  }
  
  public void rewind()
  {
    if (!loading())
    {
      if (this.sequencer == null) {
        return;
      }
      try
      {
        this.sequencer.setMicrosecondPosition(0L);
      }
      catch (Exception local_e)
      {
        errorMessage("Exception in method 'rewind'");
        printStackTrace(local_e);
        SoundSystemException sse = new SoundSystemException(local_e.getMessage());
        SoundSystem.setException(sse);
      }
    }
  }
  
  public void setVolume(float value)
  {
    this.gain = value;
    resetGain();
  }
  
  public float getVolume()
  {
    return this.gain;
  }
  
  public void switchSource(boolean toLoop, String sourcename, String filename)
  {
    loading(true, true);
    filenameURL(true, new FilenameURL(filename));
    sourcename(true, sourcename);
    setLooping(toLoop);
    reset();
    loading(true, false);
  }
  
  public void switchSource(boolean toLoop, String sourcename, URL midiFile, String identifier)
  {
    loading(true, true);
    filenameURL(true, new FilenameURL(midiFile, identifier));
    sourcename(true, sourcename);
    setLooping(toLoop);
    reset();
    loading(true, false);
  }
  
  public void switchSource(boolean toLoop, String sourcename, FilenameURL filenameURL)
  {
    loading(true, true);
    filenameURL(true, filenameURL);
    sourcename(true, sourcename);
    setLooping(toLoop);
    reset();
    loading(true, false);
  }
  
  private void reset()
  {
    synchronized (this.sequenceQueueLock)
    {
      if (this.sequenceQueue != null) {
        this.sequenceQueue.clear();
      }
    }
    if (this.sequencer == null)
    {
      getSequencer();
    }
    else
    {
      this.sequencer.stop();
      this.sequencer.setMicrosecondPosition(0L);
      this.sequencer.removeMetaEventListener(this);
      try
      {
        Thread.sleep(100L);
      }
      catch (InterruptedException local_e) {}
    }
    if (this.sequencer == null)
    {
      errorMessage("Unable to set the sequence in method 'reset', because there wasn't a sequencer to use.");
      return;
    }
    setSequence(filenameURL(false, null).getURL());
    this.sequencer.start();
    resetGain();
    this.sequencer.addMetaEventListener(this);
  }
  
  public void setLooping(boolean value)
  {
    toLoop(true, value);
  }
  
  public boolean getLooping()
  {
    return toLoop(false, false);
  }
  
  private synchronized boolean toLoop(boolean action, boolean value)
  {
    if (action == true) {
      this.toLoop = value;
    }
    return this.toLoop;
  }
  
  public boolean loading()
  {
    return loading(false, false);
  }
  
  private synchronized boolean loading(boolean action, boolean value)
  {
    if (action == true) {
      this.loading = value;
    }
    return this.loading;
  }
  
  public void setSourcename(String value)
  {
    sourcename(true, value);
  }
  
  public String getSourcename()
  {
    return sourcename(false, null);
  }
  
  private synchronized String sourcename(boolean action, String value)
  {
    if (action == true) {
      this.sourcename = value;
    }
    return this.sourcename;
  }
  
  public void setFilenameURL(FilenameURL value)
  {
    filenameURL(true, value);
  }
  
  public String getFilename()
  {
    return filenameURL(false, null).getFilename();
  }
  
  public FilenameURL getFilenameURL()
  {
    return filenameURL(false, null);
  }
  
  private synchronized FilenameURL filenameURL(boolean action, FilenameURL value)
  {
    if (action == true) {
      this.filenameURL = value;
    }
    return this.filenameURL;
  }
  
  public void meta(MetaMessage message)
  {
    if (message.getType() == 47)
    {
      SoundSystemConfig.notifyEOS(this.sourcename, this.sequenceQueue.size());
      if (this.toLoop)
      {
        if (!checkFadeOut())
        {
          if (!incrementSequence()) {
            try
            {
              this.sequencer.setMicrosecondPosition(0L);
              this.sequencer.start();
              resetGain();
            }
            catch (Exception local_e) {}
          }
        }
        else if (this.sequencer != null) {
          try
          {
            this.sequencer.setMicrosecondPosition(0L);
            this.sequencer.start();
            resetGain();
          }
          catch (Exception local_e) {}
        }
      }
      else if (!checkFadeOut())
      {
        if (!incrementSequence()) {
          try
          {
            this.sequencer.stop();
            this.sequencer.setMicrosecondPosition(0L);
            this.sequencer.removeMetaEventListener(this);
          }
          catch (Exception local_e) {}
        }
      }
      else {
        try
        {
          this.sequencer.stop();
          this.sequencer.setMicrosecondPosition(0L);
          this.sequencer.removeMetaEventListener(this);
        }
        catch (Exception local_e) {}
      }
    }
  }
  
  public void resetGain()
  {
    if (this.gain < 0.0F) {
      this.gain = 0.0F;
    }
    if (this.gain > 1.0F) {
      this.gain = 1.0F;
    }
    int midiVolume = (int)(this.gain * SoundSystemConfig.getMasterGain() * Math.abs(this.fadeOutGain) * this.fadeInGain * 127.0F);
    if (this.synthesizer != null)
    {
      javax.sound.midi.MidiChannel[] channels = this.synthesizer.getChannels();
      for (int local_c = 0; (channels != null) && (local_c < channels.length); local_c++) {
        channels[local_c].controlChange(7, midiVolume);
      }
    }
    else if (this.synthDevice != null)
    {
      try
      {
        ShortMessage channels = new ShortMessage();
        for (int local_c = 0; local_c < 16; local_c++)
        {
          channels.setMessage(176, local_c, 7, midiVolume);
          this.synthDevice.getReceiver().send(channels, -1L);
        }
      }
      catch (Exception channels)
      {
        errorMessage("Error resetting gain on MIDI device");
        printStackTrace(channels);
      }
    }
    else if ((this.sequencer != null) && ((this.sequencer instanceof Synthesizer)))
    {
      this.synthesizer = ((Synthesizer)this.sequencer);
      javax.sound.midi.MidiChannel[] channels = this.synthesizer.getChannels();
      for (int local_c = 0; (channels != null) && (local_c < channels.length); local_c++) {
        channels[local_c].controlChange(7, midiVolume);
      }
    }
    else
    {
      try
      {
        Receiver channels = MidiSystem.getReceiver();
        ShortMessage local_c = new ShortMessage();
        for (int local_c1 = 0; local_c1 < 16; local_c1++)
        {
          local_c.setMessage(176, local_c1, 7, midiVolume);
          channels.send(local_c, -1L);
        }
      }
      catch (Exception channels)
      {
        errorMessage("Error resetting gain on default receiver");
        printStackTrace(channels);
      }
    }
  }
  
  private void getSequencer()
  {
    try
    {
      this.sequencer = MidiSystem.getSequencer();
      if (this.sequencer != null)
      {
        try
        {
          this.sequencer.getTransmitter();
        }
        catch (MidiUnavailableException mue)
        {
          message("Unable to get a transmitter from the default MIDI sequencer");
        }
        this.sequencer.open();
      }
    }
    catch (MidiUnavailableException mue)
    {
      message("Unable to open the default MIDI sequencer");
      this.sequencer = null;
    }
    catch (Exception mue)
    {
      if ((mue instanceof InterruptedException))
      {
        message("Caught InterruptedException while attempting to open the default MIDI sequencer.  Trying again.");
        this.sequencer = null;
      }
      try
      {
        this.sequencer = MidiSystem.getSequencer();
        if (this.sequencer != null)
        {
          try
          {
            this.sequencer.getTransmitter();
          }
          catch (MidiUnavailableException mue)
          {
            message("Unable to get a transmitter from the default MIDI sequencer");
          }
          this.sequencer.open();
        }
      }
      catch (MidiUnavailableException mue)
      {
        message("Unable to open the default MIDI sequencer");
        this.sequencer = null;
      }
      catch (Exception mue)
      {
        message("Unknown error opening the default MIDI sequencer");
        this.sequencer = null;
      }
    }
    if (this.sequencer == null) {
      this.sequencer = openSequencer("Real Time Sequencer");
    }
    if (this.sequencer == null) {
      this.sequencer = openSequencer("Java Sound Sequencer");
    }
    if (this.sequencer == null)
    {
      errorMessage("Failed to find an available MIDI sequencer");
      return;
    }
  }
  
  private void setSequence(URL midiSource)
  {
    if (this.sequencer == null)
    {
      errorMessage("Unable to update the sequence in method 'setSequence', because variable 'sequencer' is null");
      return;
    }
    if (midiSource == null)
    {
      errorMessage("Unable to load Midi file in method 'setSequence'.");
      return;
    }
    try
    {
      this.sequence = MidiSystem.getSequence(midiSource);
    }
    catch (IOException ioe)
    {
      errorMessage("Input failed while reading from MIDI file in method 'setSequence'.");
      printStackTrace(ioe);
      return;
    }
    catch (InvalidMidiDataException ioe)
    {
      errorMessage("Invalid MIDI data encountered, or not a MIDI file in method 'setSequence' (1).");
      printStackTrace(ioe);
      return;
    }
    if (this.sequence == null) {
      errorMessage("MidiSystem 'getSequence' method returned null in method 'setSequence'.");
    } else {
      try
      {
        this.sequencer.setSequence(this.sequence);
      }
      catch (InvalidMidiDataException ioe)
      {
        errorMessage("Invalid MIDI data encountered, or not a MIDI file in method 'setSequence' (2).");
        printStackTrace(ioe);
        return;
      }
      catch (Exception ioe)
      {
        errorMessage("Problem setting sequence from MIDI file in method 'setSequence'.");
        printStackTrace(ioe);
        return;
      }
    }
  }
  
  private void getSynthesizer()
  {
    if (this.sequencer == null)
    {
      errorMessage("Unable to load a Synthesizer in method 'getSynthesizer', because variable 'sequencer' is null");
      return;
    }
    String overrideMIDISynthesizer = SoundSystemConfig.getOverrideMIDISynthesizer();
    if ((overrideMIDISynthesizer != null) && (!overrideMIDISynthesizer.equals("")))
    {
      this.synthDevice = openMidiDevice(overrideMIDISynthesizer);
      if (this.synthDevice != null) {
        try
        {
          this.sequencer.getTransmitter().setReceiver(this.synthDevice.getReceiver());
          return;
        }
        catch (MidiUnavailableException mue)
        {
          errorMessage("Unable to link sequencer transmitter with receiver for MIDI device '" + overrideMIDISynthesizer + "'");
        }
      }
    }
    if ((this.sequencer instanceof Synthesizer))
    {
      this.synthesizer = ((Synthesizer)this.sequencer);
    }
    else
    {
      try
      {
        this.synthesizer = MidiSystem.getSynthesizer();
        this.synthesizer.open();
      }
      catch (MidiUnavailableException mue)
      {
        message("Unable to open the default synthesizer");
        this.synthesizer = null;
      }
      if (this.synthesizer == null)
      {
        this.synthDevice = openMidiDevice("Java Sound Synthesizer");
        if (this.synthDevice == null) {
          this.synthDevice = openMidiDevice("Microsoft GS Wavetable");
        }
        if (this.synthDevice == null) {
          this.synthDevice = openMidiDevice("Gervill");
        }
        if (this.synthDevice == null)
        {
          errorMessage("Failed to find an available MIDI synthesizer");
          return;
        }
      }
      if (this.synthesizer == null) {
        try
        {
          this.sequencer.getTransmitter().setReceiver(this.synthDevice.getReceiver());
        }
        catch (MidiUnavailableException mue)
        {
          errorMessage("Unable to link sequencer transmitter with MIDI device receiver");
        }
      } else if (this.synthesizer.getDefaultSoundbank() == null) {
        try
        {
          this.sequencer.getTransmitter().setReceiver(MidiSystem.getReceiver());
        }
        catch (MidiUnavailableException mue)
        {
          errorMessage("Unable to link sequencer transmitter with default receiver");
        }
      } else {
        try
        {
          this.sequencer.getTransmitter().setReceiver(this.synthesizer.getReceiver());
        }
        catch (MidiUnavailableException mue)
        {
          errorMessage("Unable to link sequencer transmitter with synthesizer receiver");
        }
      }
    }
  }
  
  private Sequencer openSequencer(String containsString)
  {
    Sequencer local_s = null;
    local_s = (Sequencer)openMidiDevice(containsString);
    if (local_s == null) {
      return null;
    }
    try
    {
      local_s.getTransmitter();
    }
    catch (MidiUnavailableException mue)
    {
      message("    Unable to get a transmitter from this sequencer");
      local_s = null;
      return null;
    }
    return local_s;
  }
  
  private MidiDevice openMidiDevice(String containsString)
  {
    message("Searching for MIDI device with name containing '" + containsString + "'");
    MidiDevice device = null;
    MidiDevice.Info[] midiDevices = MidiSystem.getMidiDeviceInfo();
    for (int local_i = 0; local_i < midiDevices.length; local_i++)
    {
      device = null;
      try
      {
        device = MidiSystem.getMidiDevice(midiDevices[local_i]);
      }
      catch (MidiUnavailableException local_e)
      {
        message("    Problem in method 'getMidiDevice':  MIDIUnavailableException was thrown");
        device = null;
      }
      if ((device != null) && (midiDevices[local_i].getName().contains(containsString)))
      {
        message("    Found MIDI device named '" + midiDevices[local_i].getName() + "'");
        if ((device instanceof Synthesizer)) {
          message("        *this is a Synthesizer instance");
        }
        if ((device instanceof Sequencer)) {
          message("        *this is a Sequencer instance");
        }
        try
        {
          device.open();
        }
        catch (MidiUnavailableException local_e)
        {
          message("    Unable to open this MIDI device");
          device = null;
        }
        return device;
      }
    }
    message("    MIDI device not found");
    return null;
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
    return this.logger.errorCheck(error, "MidiChannel", message, 0);
  }
  
  protected void errorMessage(String message)
  {
    this.logger.errorMessage("MidiChannel", message, 0);
  }
  
  protected void printStackTrace(Exception local_e)
  {
    this.logger.printStackTrace(local_e, 1);
  }
  
  private class FadeThread
    extends SimpleThread
  {
    private FadeThread() {}
    
    public void run()
    {
      while (!dying())
      {
        if ((MidiChannel.this.fadeOutGain == -1.0F) && (MidiChannel.this.fadeInGain == 1.0F)) {
          snooze(3600000L);
        }
        MidiChannel.this.checkFadeOut();
        snooze(50L);
      }
      cleanup();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.MidiChannel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */