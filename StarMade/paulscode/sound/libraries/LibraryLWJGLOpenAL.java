package paulscode.sound.libraries;

import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.sound.sampled.AudioFormat;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.class_1434;
import paulscode.sound.Channel;
import paulscode.sound.FilenameURL;
import paulscode.sound.ICodec;
import paulscode.sound.Library;
import paulscode.sound.ListenerData;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.Source;
import paulscode.sound.Vector3D;

public class LibraryLWJGLOpenAL
  extends Library
{
  private static final boolean GET = false;
  private static final boolean SET = true;
  private static final boolean XXX = false;
  private FloatBuffer listenerPositionAL = null;
  private FloatBuffer listenerOrientation = null;
  private FloatBuffer listenerVelocity = null;
  private HashMap<String, IntBuffer> ALBufferMap = null;
  private static boolean alPitchSupported = true;
  
  public LibraryLWJGLOpenAL()
    throws SoundSystemException
  {
    this.reverseByteOrder = true;
  }
  
  public void init()
    throws SoundSystemException
  {
    boolean errors = false;
    try
    {
      class_1434.create();
      errors = checkALError();
    }
    catch (LWJGLException local_e)
    {
      errorMessage("Unable to initialize OpenAL.  Probable cause: OpenAL not supported.");
      printStackTrace(local_e);
      throw new Exception(local_e.getMessage(), 101);
    }
    if (errors) {
      importantMessage("OpenAL did not initialize properly!");
    } else {
      message("OpenAL initialized.");
    }
    this.listenerPositionAL = BufferUtils.createFloatBuffer(3).put(new float[] { this.listener.position.field_2107, this.listener.position.field_2108, this.listener.position.field_2109 });
    this.listenerOrientation = BufferUtils.createFloatBuffer(6).put(new float[] { this.listener.lookAt.field_2107, this.listener.lookAt.field_2108, this.listener.lookAt.field_2109, this.listener.field_1833.field_2107, this.listener.field_1833.field_2108, this.listener.field_1833.field_2109 });
    this.listenerVelocity = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F });
    this.listenerPositionAL.flip();
    this.listenerOrientation.flip();
    this.listenerVelocity.flip();
    AL10.alListener(4100, this.listenerPositionAL);
    errors = (checkALError()) || (errors);
    AL10.alListener(4111, this.listenerOrientation);
    errors = (checkALError()) || (errors);
    AL10.alListener(4102, this.listenerVelocity);
    errors = (checkALError()) || (errors);
    AL10.alDopplerFactor(SoundSystemConfig.getDopplerFactor());
    errors = (checkALError()) || (errors);
    AL10.alDopplerVelocity(SoundSystemConfig.getDopplerVelocity());
    errors = (checkALError()) || (errors);
    if (errors)
    {
      importantMessage("OpenAL did not initialize properly!");
      throw new Exception("Problem encountered while loading OpenAL or creating the listener.  Probable cause:  OpenAL not supported", 101);
    }
    super.init();
    ChannelLWJGLOpenAL local_e = (ChannelLWJGLOpenAL)this.normalChannels.get(1);
    try
    {
      AL10.alSourcef(local_e.ALSource.get(0), 4099, 1.0F);
      if (checkALError())
      {
        alPitchSupported(true, false);
        throw new Exception("OpenAL: AL_PITCH not supported.", 108);
      }
      alPitchSupported(true, true);
    }
    catch (Exception local_e1)
    {
      alPitchSupported(true, false);
      throw new Exception("OpenAL: AL_PITCH not supported.", 108);
    }
  }
  
  public static boolean libraryCompatible()
  {
    if (class_1434.isCreated()) {
      return true;
    }
    try
    {
      class_1434.create();
    }
    catch (Exception local_e)
    {
      return false;
    }
    try
    {
      class_1434.destroy();
    }
    catch (Exception localException1) {}
    return true;
  }
  
  protected Channel createChannel(int type)
  {
    IntBuffer ALSource = BufferUtils.createIntBuffer(1);
    try
    {
      AL10.alGenSources(ALSource);
    }
    catch (Exception local_e)
    {
      AL10.alGetError();
      return null;
    }
    if (AL10.alGetError() != 0) {
      return null;
    }
    ChannelLWJGLOpenAL channel = new ChannelLWJGLOpenAL(type, ALSource);
    return channel;
  }
  
  public void cleanup()
  {
    super.cleanup();
    Set<String> keys = this.bufferMap.keySet();
    Iterator<String> iter = keys.iterator();
    while (iter.hasNext())
    {
      String filename = (String)iter.next();
      IntBuffer buffer = (IntBuffer)this.ALBufferMap.get(filename);
      if (buffer != null)
      {
        AL10.alDeleteBuffers(buffer);
        checkALError();
        buffer.clear();
      }
    }
    this.bufferMap.clear();
    class_1434.destroy();
    this.bufferMap = null;
    this.listenerPositionAL = null;
    this.listenerOrientation = null;
    this.listenerVelocity = null;
  }
  
  public boolean loadSound(FilenameURL filenameURL)
  {
    if (this.bufferMap == null)
    {
      this.bufferMap = new HashMap();
      importantMessage("Buffer Map was null in method 'loadSound'");
    }
    if (this.ALBufferMap == null)
    {
      this.ALBufferMap = new HashMap();
      importantMessage("Open AL Buffer Map was null in method'loadSound'");
    }
    if (errorCheck(filenameURL == null, "Filename/URL not specified in method 'loadSound'")) {
      return false;
    }
    if (this.bufferMap.get(filenameURL.getFilename()) != null) {
      return true;
    }
    ICodec codec = SoundSystemConfig.getCodec(filenameURL.getFilename());
    if (errorCheck(codec == null, "No codec found for file '" + filenameURL.getFilename() + "' in method 'loadSound'")) {
      return false;
    }
    codec.reverseByteOrder(true);
    URL url = filenameURL.getURL();
    if (errorCheck(url == null, "Unable to open file '" + filenameURL.getFilename() + "' in method 'loadSound'")) {
      return false;
    }
    codec.initialize(url);
    SoundBuffer buffer = codec.readAll();
    codec.cleanup();
    codec = null;
    if (errorCheck(buffer == null, "Sound buffer null in method 'loadSound'")) {
      return false;
    }
    this.bufferMap.put(filenameURL.getFilename(), buffer);
    AudioFormat audioFormat = buffer.audioFormat;
    int soundFormat = 0;
    if (audioFormat.getChannels() == 1)
    {
      if (audioFormat.getSampleSizeInBits() == 8)
      {
        soundFormat = 4352;
      }
      else if (audioFormat.getSampleSizeInBits() == 16)
      {
        soundFormat = 4353;
      }
      else
      {
        errorMessage("Illegal sample size in method 'loadSound'");
        return false;
      }
    }
    else if (audioFormat.getChannels() == 2)
    {
      if (audioFormat.getSampleSizeInBits() == 8)
      {
        soundFormat = 4354;
      }
      else if (audioFormat.getSampleSizeInBits() == 16)
      {
        soundFormat = 4355;
      }
      else
      {
        errorMessage("Illegal sample size in method 'loadSound'");
        return false;
      }
    }
    else
    {
      errorMessage("File neither mono nor stereo in method 'loadSound'");
      return false;
    }
    IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
    AL10.alGenBuffers(intBuffer);
    if (errorCheck(AL10.alGetError() != 0, "alGenBuffers error when loading " + filenameURL.getFilename())) {
      return false;
    }
    AL10.alBufferData(intBuffer.get(0), soundFormat, (ByteBuffer)BufferUtils.createByteBuffer(buffer.audioData.length).put(buffer.audioData).flip(), (int)audioFormat.getSampleRate());
    if (errorCheck(AL10.alGetError() != 0, "alBufferData error when loading " + filenameURL.getFilename())) {
      if (errorCheck(intBuffer == null, "Sound buffer was not created for " + filenameURL.getFilename())) {
        return false;
      }
    }
    this.ALBufferMap.put(filenameURL.getFilename(), intBuffer);
    return true;
  }
  
  public boolean loadSound(SoundBuffer buffer, String identifier)
  {
    if (this.bufferMap == null)
    {
      this.bufferMap = new HashMap();
      importantMessage("Buffer Map was null in method 'loadSound'");
    }
    if (this.ALBufferMap == null)
    {
      this.ALBufferMap = new HashMap();
      importantMessage("Open AL Buffer Map was null in method'loadSound'");
    }
    if (errorCheck(identifier == null, "Identifier not specified in method 'loadSound'")) {
      return false;
    }
    if (this.bufferMap.get(identifier) != null) {
      return true;
    }
    if (errorCheck(buffer == null, "Sound buffer null in method 'loadSound'")) {
      return false;
    }
    this.bufferMap.put(identifier, buffer);
    AudioFormat audioFormat = buffer.audioFormat;
    int soundFormat = 0;
    if (audioFormat.getChannels() == 1)
    {
      if (audioFormat.getSampleSizeInBits() == 8)
      {
        soundFormat = 4352;
      }
      else if (audioFormat.getSampleSizeInBits() == 16)
      {
        soundFormat = 4353;
      }
      else
      {
        errorMessage("Illegal sample size in method 'loadSound'");
        return false;
      }
    }
    else if (audioFormat.getChannels() == 2)
    {
      if (audioFormat.getSampleSizeInBits() == 8)
      {
        soundFormat = 4354;
      }
      else if (audioFormat.getSampleSizeInBits() == 16)
      {
        soundFormat = 4355;
      }
      else
      {
        errorMessage("Illegal sample size in method 'loadSound'");
        return false;
      }
    }
    else
    {
      errorMessage("File neither mono nor stereo in method 'loadSound'");
      return false;
    }
    IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
    AL10.alGenBuffers(intBuffer);
    if (errorCheck(AL10.alGetError() != 0, "alGenBuffers error when saving " + identifier)) {
      return false;
    }
    AL10.alBufferData(intBuffer.get(0), soundFormat, (ByteBuffer)BufferUtils.createByteBuffer(buffer.audioData.length).put(buffer.audioData).flip(), (int)audioFormat.getSampleRate());
    if (errorCheck(AL10.alGetError() != 0, "alBufferData error when saving " + identifier)) {
      if (errorCheck(intBuffer == null, "Sound buffer was not created for " + identifier)) {
        return false;
      }
    }
    this.ALBufferMap.put(identifier, intBuffer);
    return true;
  }
  
  public void unloadSound(String filename)
  {
    this.ALBufferMap.remove(filename);
    super.unloadSound(filename);
  }
  
  public void setMasterVolume(float value)
  {
    super.setMasterVolume(value);
    AL10.alListenerf(4106, value);
    checkALError();
  }
  
  public void newSource(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float local_x, float local_y, float local_z, int attModel, float distOrRoll)
  {
    IntBuffer myBuffer = null;
    if (!toStream)
    {
      myBuffer = (IntBuffer)this.ALBufferMap.get(filenameURL.getFilename());
      if ((myBuffer == null) && (!loadSound(filenameURL)))
      {
        errorMessage("Source '" + sourcename + "' was not created " + "because an error occurred while loading " + filenameURL.getFilename());
        return;
      }
      myBuffer = (IntBuffer)this.ALBufferMap.get(filenameURL.getFilename());
      if (myBuffer == null)
      {
        errorMessage("Source '" + sourcename + "' was not created " + "because a sound buffer was not found for " + filenameURL.getFilename());
        return;
      }
    }
    SoundBuffer buffer = null;
    if (!toStream)
    {
      buffer = (SoundBuffer)this.bufferMap.get(filenameURL.getFilename());
      if ((buffer == null) && (!loadSound(filenameURL)))
      {
        errorMessage("Source '" + sourcename + "' was not created " + "because an error occurred while loading " + filenameURL.getFilename());
        return;
      }
      buffer = (SoundBuffer)this.bufferMap.get(filenameURL.getFilename());
      if (buffer == null)
      {
        errorMessage("Source '" + sourcename + "' was not created " + "because audio data was not found for " + filenameURL.getFilename());
        return;
      }
    }
    this.sourceMap.put(sourcename, new SourceLWJGLOpenAL(this.listenerPositionAL, myBuffer, priority, toStream, toLoop, sourcename, filenameURL, buffer, local_x, local_y, local_z, attModel, distOrRoll, false));
  }
  
  public void rawDataStream(AudioFormat audioFormat, boolean priority, String sourcename, float local_x, float local_y, float local_z, int attModel, float distOrRoll)
  {
    this.sourceMap.put(sourcename, new SourceLWJGLOpenAL(this.listenerPositionAL, audioFormat, priority, sourcename, local_x, local_y, local_z, attModel, distOrRoll));
  }
  
  public void quickPlay(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float local_x, float local_y, float local_z, int attModel, float distOrRoll, boolean temporary)
  {
    IntBuffer myBuffer = null;
    if (!toStream)
    {
      myBuffer = (IntBuffer)this.ALBufferMap.get(filenameURL.getFilename());
      if (myBuffer == null) {
        loadSound(filenameURL);
      }
      myBuffer = (IntBuffer)this.ALBufferMap.get(filenameURL.getFilename());
      if (myBuffer == null)
      {
        errorMessage("Sound buffer was not created for " + filenameURL.getFilename());
        return;
      }
    }
    SoundBuffer buffer = null;
    if (!toStream)
    {
      buffer = (SoundBuffer)this.bufferMap.get(filenameURL.getFilename());
      if ((buffer == null) && (!loadSound(filenameURL)))
      {
        errorMessage("Source '" + sourcename + "' was not created " + "because an error occurred while loading " + filenameURL.getFilename());
        return;
      }
      buffer = (SoundBuffer)this.bufferMap.get(filenameURL.getFilename());
      if (buffer == null)
      {
        errorMessage("Source '" + sourcename + "' was not created " + "because audio data was not found for " + filenameURL.getFilename());
        return;
      }
    }
    SourceLWJGLOpenAL local_s = new SourceLWJGLOpenAL(this.listenerPositionAL, myBuffer, priority, toStream, toLoop, sourcename, filenameURL, buffer, local_x, local_y, local_z, attModel, distOrRoll, false);
    this.sourceMap.put(sourcename, local_s);
    play(local_s);
    if (temporary) {
      local_s.setTemporary(true);
    }
  }
  
  public void copySources(HashMap<String, Source> srcMap)
  {
    if (srcMap == null) {
      return;
    }
    Set<String> keys = srcMap.keySet();
    Iterator<String> iter = keys.iterator();
    if (this.bufferMap == null)
    {
      this.bufferMap = new HashMap();
      importantMessage("Buffer Map was null in method 'copySources'");
    }
    if (this.ALBufferMap == null)
    {
      this.ALBufferMap = new HashMap();
      importantMessage("Open AL Buffer Map was null in method'copySources'");
    }
    this.sourceMap.clear();
    while (iter.hasNext())
    {
      String sourcename = (String)iter.next();
      Source source = (Source)srcMap.get(sourcename);
      if (source != null)
      {
        SoundBuffer buffer = null;
        if (!source.toStream)
        {
          loadSound(source.filenameURL);
          buffer = (SoundBuffer)this.bufferMap.get(source.filenameURL.getFilename());
        }
        if ((source.toStream) || (buffer != null)) {
          this.sourceMap.put(sourcename, new SourceLWJGLOpenAL(this.listenerPositionAL, (IntBuffer)this.ALBufferMap.get(source.filenameURL.getFilename()), source, buffer));
        }
      }
    }
  }
  
  public void setListenerPosition(float local_x, float local_y, float local_z)
  {
    super.setListenerPosition(local_x, local_y, local_z);
    this.listenerPositionAL.put(0, local_x);
    this.listenerPositionAL.put(1, local_y);
    this.listenerPositionAL.put(2, local_z);
    AL10.alListener(4100, this.listenerPositionAL);
    checkALError();
  }
  
  public void setListenerAngle(float angle)
  {
    super.setListenerAngle(angle);
    this.listenerOrientation.put(0, this.listener.lookAt.field_2107);
    this.listenerOrientation.put(2, this.listener.lookAt.field_2109);
    AL10.alListener(4111, this.listenerOrientation);
    checkALError();
  }
  
  public void setListenerOrientation(float lookX, float lookY, float lookZ, float upX, float upY, float upZ)
  {
    super.setListenerOrientation(lookX, lookY, lookZ, upX, upY, upZ);
    this.listenerOrientation.put(0, lookX);
    this.listenerOrientation.put(1, lookY);
    this.listenerOrientation.put(2, lookZ);
    this.listenerOrientation.put(3, upX);
    this.listenerOrientation.put(4, upY);
    this.listenerOrientation.put(5, upZ);
    AL10.alListener(4111, this.listenerOrientation);
    checkALError();
  }
  
  public void setListenerData(ListenerData local_l)
  {
    super.setListenerData(local_l);
    this.listenerPositionAL.put(0, local_l.position.field_2107);
    this.listenerPositionAL.put(1, local_l.position.field_2108);
    this.listenerPositionAL.put(2, local_l.position.field_2109);
    AL10.alListener(4100, this.listenerPositionAL);
    checkALError();
    this.listenerOrientation.put(0, local_l.lookAt.field_2107);
    this.listenerOrientation.put(1, local_l.lookAt.field_2108);
    this.listenerOrientation.put(2, local_l.lookAt.field_2109);
    this.listenerOrientation.put(3, local_l.field_1833.field_2107);
    this.listenerOrientation.put(4, local_l.field_1833.field_2108);
    this.listenerOrientation.put(5, local_l.field_1833.field_2109);
    AL10.alListener(4111, this.listenerOrientation);
    checkALError();
    this.listenerVelocity.put(0, local_l.velocity.field_2107);
    this.listenerVelocity.put(1, local_l.velocity.field_2108);
    this.listenerVelocity.put(2, local_l.velocity.field_2109);
    AL10.alListener(4102, this.listenerVelocity);
    checkALError();
  }
  
  public void setListenerVelocity(float local_x, float local_y, float local_z)
  {
    super.setListenerVelocity(local_x, local_y, local_z);
    this.listenerVelocity.put(0, this.listener.velocity.field_2107);
    this.listenerVelocity.put(1, this.listener.velocity.field_2108);
    this.listenerVelocity.put(2, this.listener.velocity.field_2109);
    AL10.alListener(4102, this.listenerVelocity);
  }
  
  public void dopplerChanged()
  {
    super.dopplerChanged();
    AL10.alDopplerFactor(SoundSystemConfig.getDopplerFactor());
    checkALError();
    AL10.alDopplerVelocity(SoundSystemConfig.getDopplerVelocity());
    checkALError();
  }
  
  private boolean checkALError()
  {
    switch ()
    {
    case 0: 
      return false;
    case 40961: 
      errorMessage("Invalid name parameter.");
      return true;
    case 40962: 
      errorMessage("Invalid parameter.");
      return true;
    case 40963: 
      errorMessage("Invalid enumerated parameter value.");
      return true;
    case 40964: 
      errorMessage("Illegal call.");
      return true;
    case 40965: 
      errorMessage("Unable to allocate memory.");
      return true;
    }
    errorMessage("An unrecognized error occurred.");
    return true;
  }
  
  public static boolean alPitchSupported()
  {
    return alPitchSupported(false, false);
  }
  
  private static synchronized boolean alPitchSupported(boolean action, boolean value)
  {
    if (action) {
      alPitchSupported = value;
    }
    return alPitchSupported;
  }
  
  public static String getTitle()
  {
    return "LWJGL OpenAL";
  }
  
  public static String getDescription()
  {
    return "The LWJGL binding of OpenAL.  For more information, see http://www.lwjgl.org";
  }
  
  public String getClassName()
  {
    return "LibraryLWJGLOpenAL";
  }
  
  public static class Exception
    extends SoundSystemException
  {
    private static final long serialVersionUID = -7502452059037798035L;
    public static final int CREATE = 101;
    public static final int INVALID_NAME = 102;
    public static final int INVALID_ENUM = 103;
    public static final int INVALID_VALUE = 104;
    public static final int INVALID_OPERATION = 105;
    public static final int OUT_OF_MEMORY = 106;
    public static final int LISTENER = 107;
    public static final int NO_AL_PITCH = 108;
    
    public Exception(String message)
    {
      super();
    }
    
    public Exception(String message, int type)
    {
      super(type);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.libraries.LibraryLWJGLOpenAL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */