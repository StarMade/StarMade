package paulscode.sound.libraries;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;
import javax.sound.sampled.AudioFormat;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import paulscode.sound.Channel;
import paulscode.sound.SoundBuffer;
import paulscode.sound.Source;

public class ChannelLWJGLOpenAL
  extends Channel
{
  public IntBuffer ALSource;
  public int ALformat;
  public int sampleRate;
  public float millisPreviouslyPlayed = 0.0F;
  
  public ChannelLWJGLOpenAL(int type, IntBuffer src)
  {
    super(type);
    this.libraryType = LibraryLWJGLOpenAL.class;
    this.ALSource = src;
  }
  
  public void cleanup()
  {
    if (this.ALSource != null)
    {
      try
      {
        AL10.alSourceStop(this.ALSource);
        AL10.alGetError();
      }
      catch (Exception localException) {}
      try
      {
        AL10.alDeleteSources(this.ALSource);
        AL10.alGetError();
      }
      catch (Exception localException1) {}
      this.ALSource.clear();
    }
    this.ALSource = null;
    super.cleanup();
  }
  
  public boolean attachBuffer(IntBuffer buf)
  {
    if (errorCheck(this.channelType != 0, "Sound buffers may only be attached to normal sources.")) {
      return false;
    }
    AL10.alSourcei(this.ALSource.get(0), 4105, buf.get(0));
    if ((this.attachedSource != null) && (this.attachedSource.soundBuffer != null) && (this.attachedSource.soundBuffer.audioFormat != null)) {
      setAudioFormat(this.attachedSource.soundBuffer.audioFormat);
    }
    return checkALError();
  }
  
  public void setAudioFormat(AudioFormat audioFormat)
  {
    int soundFormat = 0;
    if (audioFormat.getChannels() == 1)
    {
      if (audioFormat.getSampleSizeInBits() == 8) {
        soundFormat = 4352;
      } else if (audioFormat.getSampleSizeInBits() == 16) {
        soundFormat = 4353;
      } else {
        errorMessage("Illegal sample size in method 'setAudioFormat'");
      }
    }
    else if (audioFormat.getChannels() == 2)
    {
      if (audioFormat.getSampleSizeInBits() == 8) {
        soundFormat = 4354;
      } else if (audioFormat.getSampleSizeInBits() == 16) {
        soundFormat = 4355;
      } else {
        errorMessage("Illegal sample size in method 'setAudioFormat'");
      }
    }
    else
    {
      errorMessage("Audio data neither mono nor stereo in method 'setAudioFormat'");
      return;
    }
    this.ALformat = soundFormat;
    this.sampleRate = ((int)audioFormat.getSampleRate());
  }
  
  public void setFormat(int format, int rate)
  {
    this.ALformat = format;
    this.sampleRate = rate;
  }
  
  public boolean preLoadBuffers(LinkedList<byte[]> bufferList)
  {
    if (errorCheck(this.channelType != 1, "Buffers may only be queued for streaming sources.")) {
      return false;
    }
    if (errorCheck(bufferList == null, "Buffer List null in method 'preLoadBuffers'")) {
      return false;
    }
    boolean playing = playing();
    if (playing)
    {
      AL10.alSourceStop(this.ALSource.get(0));
      checkALError();
    }
    int processed = AL10.alGetSourcei(this.ALSource.get(0), 4118);
    if (processed > 0)
    {
      IntBuffer streamBuffers = BufferUtils.createIntBuffer(processed);
      AL10.alGenBuffers(streamBuffers);
      if (errorCheck(checkALError(), "Error clearing stream buffers in method 'preLoadBuffers'")) {
        return false;
      }
      AL10.alSourceUnqueueBuffers(this.ALSource.get(0), streamBuffers);
      if (errorCheck(checkALError(), "Error unqueuing stream buffers in method 'preLoadBuffers'")) {
        return false;
      }
    }
    if (playing)
    {
      AL10.alSourcePlay(this.ALSource.get(0));
      checkALError();
    }
    IntBuffer streamBuffers = BufferUtils.createIntBuffer(bufferList.size());
    AL10.alGenBuffers(streamBuffers);
    if (errorCheck(checkALError(), "Error generating stream buffers in method 'preLoadBuffers'")) {
      return false;
    }
    ByteBuffer byteBuffer = null;
    for (int local_i = 0; local_i < bufferList.size(); local_i++)
    {
      byteBuffer = (ByteBuffer)BufferUtils.createByteBuffer(((byte[])bufferList.get(local_i)).length).put((byte[])bufferList.get(local_i)).flip();
      try
      {
        AL10.alBufferData(streamBuffers.get(local_i), this.ALformat, byteBuffer, this.sampleRate);
      }
      catch (Exception local_e)
      {
        errorMessage("Error creating buffers in method 'preLoadBuffers'");
        printStackTrace(local_e);
        return false;
      }
      if (errorCheck(checkALError(), "Error creating buffers in method 'preLoadBuffers'")) {
        return false;
      }
    }
    try
    {
      AL10.alSourceQueueBuffers(this.ALSource.get(0), streamBuffers);
    }
    catch (Exception local_i)
    {
      errorMessage("Error queuing buffers in method 'preLoadBuffers'");
      printStackTrace(local_i);
      return false;
    }
    if (errorCheck(checkALError(), "Error queuing buffers in method 'preLoadBuffers'")) {
      return false;
    }
    AL10.alSourcePlay(this.ALSource.get(0));
    return !errorCheck(checkALError(), "Error playing source in method 'preLoadBuffers'");
  }
  
  public boolean queueBuffer(byte[] buffer)
  {
    if (errorCheck(this.channelType != 1, "Buffers may only be queued for streaming sources.")) {
      return false;
    }
    ByteBuffer byteBuffer = (ByteBuffer)BufferUtils.createByteBuffer(buffer.length).put(buffer).flip();
    IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
    AL10.alSourceUnqueueBuffers(this.ALSource.get(0), intBuffer);
    if (checkALError()) {
      return false;
    }
    if (AL10.alIsBuffer(intBuffer.get(0))) {
      this.millisPreviouslyPlayed += millisInBuffer(intBuffer.get(0));
    }
    checkALError();
    AL10.alBufferData(intBuffer.get(0), this.ALformat, byteBuffer, this.sampleRate);
    if (checkALError()) {
      return false;
    }
    AL10.alSourceQueueBuffers(this.ALSource.get(0), intBuffer);
    return !checkALError();
  }
  
  public int feedRawAudioData(byte[] buffer)
  {
    if (errorCheck(this.channelType != 1, "Raw audio data can only be fed to streaming sources.")) {
      return -1;
    }
    ByteBuffer byteBuffer = (ByteBuffer)BufferUtils.createByteBuffer(buffer.length).put(buffer).flip();
    int processed = AL10.alGetSourcei(this.ALSource.get(0), 4118);
    if (processed > 0)
    {
      IntBuffer intBuffer = BufferUtils.createIntBuffer(processed);
      AL10.alGenBuffers(intBuffer);
      if (errorCheck(checkALError(), "Error clearing stream buffers in method 'feedRawAudioData'")) {
        return -1;
      }
      AL10.alSourceUnqueueBuffers(this.ALSource.get(0), intBuffer);
      if (errorCheck(checkALError(), "Error unqueuing stream buffers in method 'feedRawAudioData'")) {
        return -1;
      }
      intBuffer.rewind();
      while (intBuffer.hasRemaining())
      {
        int local_i = intBuffer.get();
        if (AL10.alIsBuffer(local_i)) {
          this.millisPreviouslyPlayed += millisInBuffer(local_i);
        }
        checkALError();
      }
      AL10.alDeleteBuffers(intBuffer);
      checkALError();
    }
    IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
    AL10.alGenBuffers(intBuffer);
    if (errorCheck(checkALError(), "Error generating stream buffers in method 'preLoadBuffers'")) {
      return -1;
    }
    AL10.alBufferData(intBuffer.get(0), this.ALformat, byteBuffer, this.sampleRate);
    if (checkALError()) {
      return -1;
    }
    AL10.alSourceQueueBuffers(this.ALSource.get(0), intBuffer);
    if (checkALError()) {
      return -1;
    }
    if ((this.attachedSource != null) && (this.attachedSource.channel == this) && (this.attachedSource.active()) && (!playing()))
    {
      AL10.alSourcePlay(this.ALSource.get(0));
      checkALError();
    }
    return processed;
  }
  
  public float millisInBuffer(int alBufferi)
  {
    return AL10.alGetBufferi(alBufferi, 8196) / AL10.alGetBufferi(alBufferi, 8195) / (AL10.alGetBufferi(alBufferi, 8194) / 8.0F) / this.sampleRate * 1000.0F;
  }
  
  public float millisecondsPlayed()
  {
    float offset = AL10.alGetSourcei(this.ALSource.get(0), 4134);
    float bytesPerFrame = 1.0F;
    switch (this.ALformat)
    {
    case 4352: 
      bytesPerFrame = 1.0F;
      break;
    case 4353: 
      bytesPerFrame = 2.0F;
      break;
    case 4354: 
      bytesPerFrame = 2.0F;
      break;
    case 4355: 
      bytesPerFrame = 4.0F;
      break;
    }
    offset = offset / bytesPerFrame / this.sampleRate * 1000.0F;
    if (this.channelType == 1) {
      offset += this.millisPreviouslyPlayed;
    }
    return offset;
  }
  
  public int buffersProcessed()
  {
    if (this.channelType != 1) {
      return 0;
    }
    int processed = AL10.alGetSourcei(this.ALSource.get(0), 4118);
    if (checkALError()) {
      return 0;
    }
    return processed;
  }
  
  public void flush()
  {
    if (this.channelType != 1) {
      return;
    }
    int queued = AL10.alGetSourcei(this.ALSource.get(0), 4117);
    if (checkALError()) {
      return;
    }
    IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
    while (queued > 0)
    {
      try
      {
        AL10.alSourceUnqueueBuffers(this.ALSource.get(0), intBuffer);
      }
      catch (Exception local_e)
      {
        return;
      }
      if (checkALError()) {
        return;
      }
      queued--;
    }
    this.millisPreviouslyPlayed = 0.0F;
  }
  
  public void close()
  {
    try
    {
      AL10.alSourceStop(this.ALSource.get(0));
      AL10.alGetError();
    }
    catch (Exception localException) {}
    if (this.channelType == 1) {
      flush();
    }
  }
  
  public void play()
  {
    AL10.alSourcePlay(this.ALSource.get(0));
    checkALError();
  }
  
  public void pause()
  {
    AL10.alSourcePause(this.ALSource.get(0));
    checkALError();
  }
  
  public void stop()
  {
    AL10.alSourceStop(this.ALSource.get(0));
    if (!checkALError()) {
      this.millisPreviouslyPlayed = 0.0F;
    }
  }
  
  public void rewind()
  {
    if (this.channelType == 1) {
      return;
    }
    AL10.alSourceRewind(this.ALSource.get(0));
    if (!checkALError()) {
      this.millisPreviouslyPlayed = 0.0F;
    }
  }
  
  public boolean playing()
  {
    int state = AL10.alGetSourcei(this.ALSource.get(0), 4112);
    if (checkALError()) {
      return false;
    }
    return state == 4114;
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
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.libraries.ChannelLWJGLOpenAL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */