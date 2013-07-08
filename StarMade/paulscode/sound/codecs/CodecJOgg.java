package paulscode.sound.codecs;

import de.jarnbjo.ogg.CachedUrlStream;
import de.jarnbjo.ogg.EndOfOggStreamException;
import de.jarnbjo.ogg.LogicalOggStream;
import de.jarnbjo.vorbis.IdentificationHeader;
import de.jarnbjo.vorbis.VorbisStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.Collection;
import java.util.Iterator;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import paulscode.sound.ICodec;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemLogger;

public class CodecJOgg
  implements ICodec
{
  private static final boolean GET = false;
  private static final boolean SET = true;
  private static final boolean XXX = false;
  private boolean endOfStream = false;
  private boolean initialized = false;
  private boolean reverseBytes = false;
  private CachedUrlStream cachedUrlStream = null;
  private LogicalOggStream myLogicalOggStream = null;
  private VorbisStream myVorbisStream = null;
  private OggInputStream myOggInputStream = null;
  private IdentificationHeader myIdentificationHeader = null;
  private AudioFormat myAudioFormat = null;
  private AudioInputStream myAudioInputStream = null;
  private SoundSystemLogger logger = SoundSystemConfig.getLogger();
  
  public void reverseByteOrder(boolean local_b)
  {
    this.reverseBytes = local_b;
  }
  
  public boolean initialize(URL url)
  {
    initialized(true, false);
    cleanup();
    if (url == null)
    {
      errorMessage("url null in method 'initialize'");
      cleanup();
      return false;
    }
    try
    {
      this.cachedUrlStream = new CachedUrlStream(url);
      this.myLogicalOggStream = ((LogicalOggStream)this.cachedUrlStream.getLogicalStreams().iterator().next());
      this.myVorbisStream = new VorbisStream(this.myLogicalOggStream);
      this.myOggInputStream = new OggInputStream(this.myVorbisStream);
      this.myIdentificationHeader = this.myVorbisStream.getIdentificationHeader();
      this.myAudioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, this.myIdentificationHeader.getSampleRate(), 16, this.myIdentificationHeader.getChannels(), this.myIdentificationHeader.getChannels() * 2, this.myIdentificationHeader.getSampleRate(), true);
      this.myAudioInputStream = new AudioInputStream(this.myOggInputStream, this.myAudioFormat, -1L);
    }
    catch (Exception local_e)
    {
      errorMessage("Unable to set up input streams in method 'initialize'");
      printStackTrace(local_e);
      cleanup();
      return false;
    }
    if (this.myAudioInputStream == null)
    {
      errorMessage("Unable to set up audio input stream in method 'initialize'");
      cleanup();
      return false;
    }
    endOfStream(true, false);
    initialized(true, true);
    return true;
  }
  
  public boolean initialized()
  {
    return initialized(false, false);
  }
  
  public SoundBuffer read()
  {
    if (this.myAudioInputStream == null)
    {
      endOfStream(true, true);
      return null;
    }
    AudioFormat audioFormat = this.myAudioInputStream.getFormat();
    if (audioFormat == null)
    {
      errorMessage("Audio Format null in method 'read'");
      endOfStream(true, true);
      return null;
    }
    int bytesRead = 0;
    int cnt = 0;
    byte[] streamBuffer = new byte[SoundSystemConfig.getStreamingBufferSize()];
    try
    {
      while ((!endOfStream(false, false)) && (bytesRead < streamBuffer.length))
      {
        if ((cnt = this.myAudioInputStream.read(streamBuffer, bytesRead, streamBuffer.length - bytesRead)) <= 0)
        {
          endOfStream(true, true);
          break;
        }
        bytesRead += cnt;
      }
    }
    catch (IOException ioe)
    {
      endOfStream(true, true);
      return null;
    }
    if (bytesRead <= 0)
    {
      endOfStream(true, true);
      return null;
    }
    if (this.reverseBytes) {
      reverseBytes(streamBuffer, 0, bytesRead);
    }
    if (bytesRead < streamBuffer.length) {
      streamBuffer = trimArray(streamBuffer, bytesRead);
    }
    byte[] ioe = convertAudioBytes(streamBuffer, audioFormat.getSampleSizeInBits() == 16);
    SoundBuffer buffer = new SoundBuffer(ioe, audioFormat);
    return buffer;
  }
  
  public SoundBuffer readAll()
  {
    if (this.myAudioFormat == null)
    {
      errorMessage("Audio Format null in method 'readAll'");
      return null;
    }
    byte[] fullBuffer = null;
    int fileSize = this.myAudioFormat.getChannels() * (int)this.myAudioInputStream.getFrameLength() * this.myAudioFormat.getSampleSizeInBits() / 8;
    if (fileSize > 0)
    {
      fullBuffer = new byte[this.myAudioFormat.getChannels() * (int)this.myAudioInputStream.getFrameLength() * this.myAudioFormat.getSampleSizeInBits() / 8];
      int read = 0;
      int total = 0;
      try
      {
        while (((read = this.myAudioInputStream.read(fullBuffer, total, fullBuffer.length - total)) != -1) && (total < fullBuffer.length)) {
          total += read;
        }
      }
      catch (IOException local_e)
      {
        errorMessage("Exception thrown while reading from the AudioInputStream (location #1).");
        printStackTrace(local_e);
        return null;
      }
    }
    else
    {
      int read = 0;
      int total = 0;
      int local_e = 0;
      byte[] smallBuffer = null;
      smallBuffer = new byte[SoundSystemConfig.getFileChunkSize()];
      while ((!endOfStream(false, false)) && (read < SoundSystemConfig.getMaxFileSize()))
      {
        total = 0;
        local_e = 0;
        try
        {
          while (total < smallBuffer.length)
          {
            if ((local_e = this.myAudioInputStream.read(smallBuffer, total, smallBuffer.length - total)) <= 0)
            {
              endOfStream(true, true);
              break;
            }
            total += local_e;
          }
        }
        catch (IOException local_e1)
        {
          errorMessage("Exception thrown while reading from the AudioInputStream (location #2).");
          printStackTrace(local_e1);
          return null;
        }
        if (this.reverseBytes) {
          reverseBytes(smallBuffer, 0, total);
        }
        read += total;
        fullBuffer = appendByteArrays(fullBuffer, smallBuffer, total);
      }
    }
    byte[] read = convertAudioBytes(fullBuffer, this.myAudioFormat.getSampleSizeInBits() == 16);
    SoundBuffer total = new SoundBuffer(read, this.myAudioFormat);
    try
    {
      this.myAudioInputStream.close();
    }
    catch (IOException local_e) {}
    return total;
  }
  
  public boolean endOfStream()
  {
    return endOfStream(false, false);
  }
  
  public void cleanup()
  {
    if (this.myLogicalOggStream != null) {
      try
      {
        this.myLogicalOggStream.close();
      }
      catch (Exception local_e) {}
    }
    if (this.myVorbisStream != null) {
      try
      {
        this.myVorbisStream.close();
      }
      catch (Exception local_e) {}
    }
    if (this.myOggInputStream != null) {
      try
      {
        this.myOggInputStream.close();
      }
      catch (Exception local_e) {}
    }
    if (this.myAudioInputStream != null) {
      try
      {
        this.myAudioInputStream.close();
      }
      catch (Exception local_e) {}
    }
    this.myLogicalOggStream = null;
    this.myVorbisStream = null;
    this.myOggInputStream = null;
    this.myAudioInputStream = null;
  }
  
  public AudioFormat getAudioFormat()
  {
    return this.myAudioFormat;
  }
  
  private synchronized boolean initialized(boolean action, boolean value)
  {
    if (action == true) {
      this.initialized = value;
    }
    return this.initialized;
  }
  
  private synchronized boolean endOfStream(boolean action, boolean value)
  {
    if (action == true) {
      this.endOfStream = value;
    }
    return this.endOfStream;
  }
  
  private static byte[] trimArray(byte[] array, int maxLength)
  {
    byte[] trimmedArray = null;
    if ((array != null) && (array.length > maxLength))
    {
      trimmedArray = new byte[maxLength];
      System.arraycopy(array, 0, trimmedArray, 0, maxLength);
    }
    return trimmedArray;
  }
  
  private static byte[] convertAudioBytes(byte[] audio_bytes, boolean two_bytes_data)
  {
    ByteBuffer dest = ByteBuffer.allocateDirect(audio_bytes.length);
    dest.order(ByteOrder.nativeOrder());
    ByteBuffer src = ByteBuffer.wrap(audio_bytes);
    src.order(ByteOrder.LITTLE_ENDIAN);
    if (two_bytes_data)
    {
      ShortBuffer dest_short = dest.asShortBuffer();
      ShortBuffer src_short = src.asShortBuffer();
      while (src_short.hasRemaining()) {
        dest_short.put(src_short.get());
      }
    }
    else
    {
      while (src.hasRemaining()) {
        dest.put(src.get());
      }
    }
    dest.rewind();
    if (!dest.hasArray())
    {
      byte[] dest_short = new byte[dest.capacity()];
      dest.get(dest_short);
      dest.clear();
      return dest_short;
    }
    return dest.array();
  }
  
  private static byte[] appendByteArrays(byte[] arrayOne, byte[] arrayTwo, int length)
  {
    if ((arrayOne == null) && (arrayTwo == null)) {
      return null;
    }
    byte[] newArray;
    if (arrayOne == null)
    {
      byte[] newArray = new byte[length];
      System.arraycopy(arrayTwo, 0, newArray, 0, length);
      arrayTwo = null;
    }
    else if (arrayTwo == null)
    {
      byte[] newArray = new byte[arrayOne.length];
      System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
      arrayOne = null;
    }
    else
    {
      newArray = new byte[arrayOne.length + length];
      System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
      System.arraycopy(arrayTwo, 0, newArray, arrayOne.length, length);
      arrayOne = null;
      arrayTwo = null;
    }
    return newArray;
  }
  
  public static void reverseBytes(byte[] buffer)
  {
    reverseBytes(buffer, 0, buffer.length);
  }
  
  public static void reverseBytes(byte[] buffer, int offset, int size)
  {
    for (int local_i = offset; local_i < offset + size; local_i += 2)
    {
      byte local_b = buffer[local_i];
      buffer[local_i] = buffer[(local_i + 1)];
      buffer[(local_i + 1)] = local_b;
    }
  }
  
  private void errorMessage(String message)
  {
    this.logger.errorMessage("CodecJOgg", message, 0);
  }
  
  private void printStackTrace(Exception local_e)
  {
    this.logger.printStackTrace(local_e, 1);
  }
  
  private class OggInputStream
    extends InputStream
  {
    private VorbisStream myVorbisStream;
    
    public OggInputStream(VorbisStream source)
    {
      this.myVorbisStream = source;
    }
    
    public int read()
      throws IOException
    {
      return 0;
    }
    
    public int read(byte[] buffer)
      throws IOException
    {
      return read(buffer, 0, buffer.length);
    }
    
    public int read(byte[] buffer, int offset, int length)
      throws IOException
    {
      try
      {
        return this.myVorbisStream.readPcm(buffer, offset, length);
      }
      catch (EndOfOggStreamException local_e) {}
      return -1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.codecs.CodecJOgg
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */