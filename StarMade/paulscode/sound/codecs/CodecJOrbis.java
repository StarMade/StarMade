package paulscode.sound.codecs;

import com.jcraft.jogg.Packet;
import com.jcraft.jogg.Page;
import com.jcraft.jogg.StreamState;
import com.jcraft.jogg.SyncState;
import com.jcraft.jorbis.Block;
import com.jcraft.jorbis.Comment;
import com.jcraft.jorbis.DspState;
import com.jcraft.jorbis.Info;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownServiceException;
import javax.sound.sampled.AudioFormat;
import paulscode.sound.ICodec;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemLogger;

public class CodecJOrbis
  implements ICodec
{
  private static final boolean GET = false;
  private static final boolean SET = true;
  private static final boolean XXX = false;
  private URL url;
  private URLConnection urlConnection = null;
  private InputStream inputStream;
  private AudioFormat audioFormat;
  private boolean endOfStream = false;
  private boolean initialized = false;
  private byte[] buffer = null;
  private int bufferSize;
  private int count = 0;
  private int index = 0;
  private int convertedBufferSize;
  private byte[] convertedBuffer = null;
  private float[][][] pcmInfo;
  private int[] pcmIndex;
  private Packet joggPacket = new Packet();
  private Page joggPage = new Page();
  private StreamState joggStreamState = new StreamState();
  private SyncState joggSyncState = new SyncState();
  private DspState jorbisDspState = new DspState();
  private Block jorbisBlock = new Block(this.jorbisDspState);
  private Comment jorbisComment = new Comment();
  private Info jorbisInfo = new Info();
  private SoundSystemLogger logger = SoundSystemConfig.getLogger();
  
  public void reverseByteOrder(boolean local_b) {}
  
  public boolean initialize(URL url)
  {
    initialized(true, false);
    if (this.joggStreamState != null) {
      this.joggStreamState.clear();
    }
    if (this.jorbisBlock != null) {
      this.jorbisBlock.clear();
    }
    if (this.jorbisDspState != null) {
      this.jorbisDspState.clear();
    }
    if (this.jorbisInfo != null) {
      this.jorbisInfo.clear();
    }
    if (this.joggSyncState != null) {
      this.joggSyncState.clear();
    }
    if (this.inputStream != null) {
      try
      {
        this.inputStream.close();
      }
      catch (IOException ioe) {}
    }
    this.url = url;
    this.bufferSize = 8192;
    this.buffer = null;
    this.count = 0;
    this.index = 0;
    this.joggStreamState = new StreamState();
    this.jorbisBlock = new Block(this.jorbisDspState);
    this.jorbisDspState = new DspState();
    this.jorbisInfo = new Info();
    this.joggSyncState = new SyncState();
    try
    {
      this.urlConnection = url.openConnection();
    }
    catch (UnknownServiceException ioe)
    {
      errorMessage("Unable to create a UrlConnection in method 'initialize'.");
      printStackTrace(ioe);
      cleanup();
      return false;
    }
    catch (IOException ioe)
    {
      errorMessage("Unable to create a UrlConnection in method 'initialize'.");
      printStackTrace(ioe);
      cleanup();
      return false;
    }
    if (this.urlConnection != null) {
      try
      {
        this.inputStream = this.urlConnection.getInputStream();
      }
      catch (IOException ioe)
      {
        errorMessage("Unable to acquire inputstream in method 'initialize'.");
        printStackTrace(ioe);
        cleanup();
        return false;
      }
    }
    endOfStream(true, false);
    this.joggSyncState.init();
    this.joggSyncState.buffer(this.bufferSize);
    this.buffer = this.joggSyncState.data;
    try
    {
      if (!readHeader())
      {
        errorMessage("Error reading the header");
        return false;
      }
    }
    catch (IOException ioe)
    {
      errorMessage("Error reading the header");
      return false;
    }
    this.convertedBufferSize = (this.bufferSize * 2);
    this.jorbisDspState.synthesis_init(this.jorbisInfo);
    this.jorbisBlock.init(this.jorbisDspState);
    int ioe = this.jorbisInfo.channels;
    int rate = this.jorbisInfo.rate;
    this.audioFormat = new AudioFormat(rate, 16, ioe, true, false);
    this.pcmInfo = new float[1][][];
    this.pcmIndex = new int[this.jorbisInfo.channels];
    initialized(true, true);
    return true;
  }
  
  public boolean initialized()
  {
    return initialized(false, false);
  }
  
  public SoundBuffer read()
  {
    byte[] returnBuffer = null;
    while ((!endOfStream(false, false)) && ((returnBuffer == null) || (returnBuffer.length < SoundSystemConfig.getStreamingBufferSize()))) {
      if (returnBuffer == null) {
        returnBuffer = readBytes();
      } else {
        returnBuffer = appendByteArrays(returnBuffer, readBytes());
      }
    }
    if (returnBuffer == null) {
      return null;
    }
    return new SoundBuffer(returnBuffer, this.audioFormat);
  }
  
  public SoundBuffer readAll()
  {
    byte[] returnBuffer = null;
    while (!endOfStream(false, false)) {
      if (returnBuffer == null) {
        returnBuffer = readBytes();
      } else {
        returnBuffer = appendByteArrays(returnBuffer, readBytes());
      }
    }
    if (returnBuffer == null) {
      return null;
    }
    return new SoundBuffer(returnBuffer, this.audioFormat);
  }
  
  public boolean endOfStream()
  {
    return endOfStream(false, false);
  }
  
  public void cleanup()
  {
    this.joggStreamState.clear();
    this.jorbisBlock.clear();
    this.jorbisDspState.clear();
    this.jorbisInfo.clear();
    this.joggSyncState.clear();
    if (this.inputStream != null) {
      try
      {
        this.inputStream.close();
      }
      catch (IOException ioe) {}
    }
    this.joggStreamState = null;
    this.jorbisBlock = null;
    this.jorbisDspState = null;
    this.jorbisInfo = null;
    this.joggSyncState = null;
    this.inputStream = null;
  }
  
  public AudioFormat getAudioFormat()
  {
    return this.audioFormat;
  }
  
  private boolean readHeader()
    throws IOException
  {
    this.index = this.joggSyncState.buffer(this.bufferSize);
    int bytes = this.inputStream.read(this.joggSyncState.data, this.index, this.bufferSize);
    if (bytes < 0) {
      bytes = 0;
    }
    this.joggSyncState.wrote(bytes);
    if (this.joggSyncState.pageout(this.joggPage) != 1)
    {
      if (bytes < this.bufferSize) {
        return true;
      }
      errorMessage("Ogg header not recognized in method 'readHeader'.");
      return false;
    }
    this.joggStreamState.init(this.joggPage.serialno());
    this.jorbisInfo.init();
    this.jorbisComment.init();
    if (this.joggStreamState.pagein(this.joggPage) < 0)
    {
      errorMessage("Problem with first Ogg header page in method 'readHeader'.");
      return false;
    }
    if (this.joggStreamState.packetout(this.joggPacket) != 1)
    {
      errorMessage("Problem with first Ogg header packet in method 'readHeader'.");
      return false;
    }
    if (this.jorbisInfo.synthesis_headerin(this.jorbisComment, this.joggPacket) < 0)
    {
      errorMessage("File does not contain Vorbis header in method 'readHeader'.");
      return false;
    }
    int local_i = 0;
    while (local_i < 2)
    {
      while (local_i < 2)
      {
        int result = this.joggSyncState.pageout(this.joggPage);
        if (result == 0) {
          break;
        }
        if (result == 1)
        {
          this.joggStreamState.pagein(this.joggPage);
          while (local_i < 2)
          {
            result = this.joggStreamState.packetout(this.joggPacket);
            if (result == 0) {
              break;
            }
            if (result == -1)
            {
              errorMessage("Secondary Ogg header corrupt in method 'readHeader'.");
              return false;
            }
            this.jorbisInfo.synthesis_headerin(this.jorbisComment, this.joggPacket);
            local_i++;
          }
        }
      }
      this.index = this.joggSyncState.buffer(this.bufferSize);
      bytes = this.inputStream.read(this.joggSyncState.data, this.index, this.bufferSize);
      if (bytes < 0) {
        bytes = 0;
      }
      if ((bytes == 0) && (local_i < 2))
      {
        errorMessage("End of file reached before finished readingOgg header in method 'readHeader'");
        return false;
      }
      this.joggSyncState.wrote(bytes);
    }
    this.index = this.joggSyncState.buffer(this.bufferSize);
    this.buffer = this.joggSyncState.data;
    return true;
  }
  
  private byte[] readBytes()
  {
    if (!initialized(false, false)) {
      return null;
    }
    if (endOfStream(false, false)) {
      return null;
    }
    if (this.convertedBuffer == null) {
      this.convertedBuffer = new byte[this.convertedBufferSize];
    }
    byte[] returnBuffer = null;
    switch (this.joggSyncState.pageout(this.joggPage))
    {
    case -1: 
    case 0: 
      break;
    default: 
      this.joggStreamState.pagein(this.joggPage);
      if (this.joggPage.granulepos() == 0L)
      {
        endOfStream(true, true);
        return null;
      }
      for (;;)
      {
        switch (this.joggStreamState.packetout(this.joggPacket))
        {
        case 0: 
          break;
        case -1: 
          break;
        default: 
          if (this.jorbisBlock.synthesis(this.joggPacket) == 0) {
            this.jorbisDspState.synthesis_blockin(this.jorbisBlock);
          }
          int samples;
          while ((samples = this.jorbisDspState.synthesis_pcmout(this.pcmInfo, this.pcmIndex)) > 0)
          {
            float[][] pcmf = this.pcmInfo[0];
            int bout = samples < this.convertedBufferSize ? samples : this.convertedBufferSize;
            for (int local_i = 0; local_i < this.jorbisInfo.channels; local_i++)
            {
              int ptr = local_i * 2;
              int mono = this.pcmIndex[local_i];
              for (int local_j = 0; local_j < bout; local_j++)
              {
                int val = (int)(pcmf[local_i][(mono + local_j)] * 32767.0D);
                if (val > 32767) {
                  val = 32767;
                }
                if (val < -32768) {
                  val = -32768;
                }
                if (val < 0) {
                  val |= 32768;
                }
                this.convertedBuffer[ptr] = ((byte)val);
                this.convertedBuffer[(ptr + 1)] = ((byte)(val >>> 8));
                ptr += 2 * this.jorbisInfo.channels;
              }
            }
            this.jorbisDspState.synthesis_read(bout);
            returnBuffer = appendByteArrays(returnBuffer, this.convertedBuffer, 2 * this.jorbisInfo.channels * bout);
          }
        }
      }
      if (this.joggPage.eos() != 0) {
        endOfStream(true, true);
      }
      break;
    }
    if (!endOfStream(false, false))
    {
      this.index = this.joggSyncState.buffer(this.bufferSize);
      this.buffer = this.joggSyncState.data;
      try
      {
        this.count = this.inputStream.read(this.buffer, this.index, this.bufferSize);
      }
      catch (Exception local_e)
      {
        printStackTrace(local_e);
        return null;
      }
      if (this.count == -1) {
        return returnBuffer;
      }
      this.joggSyncState.wrote(this.count);
      if (this.count == 0) {
        endOfStream(true, true);
      }
    }
    return returnBuffer;
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
  
  private static byte[] appendByteArrays(byte[] arrayOne, byte[] arrayTwo, int arrayTwoBytes)
  {
    int bytes = arrayTwoBytes;
    if ((arrayTwo == null) || (arrayTwo.length == 0)) {
      bytes = 0;
    } else if (arrayTwo.length < arrayTwoBytes) {
      bytes = arrayTwo.length;
    }
    if ((arrayOne == null) && ((arrayTwo == null) || (bytes <= 0))) {
      return null;
    }
    byte[] newArray;
    if (arrayOne == null)
    {
      byte[] newArray = new byte[bytes];
      System.arraycopy(arrayTwo, 0, newArray, 0, bytes);
      arrayTwo = null;
    }
    else if ((arrayTwo == null) || (bytes <= 0))
    {
      byte[] newArray = new byte[arrayOne.length];
      System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
      arrayOne = null;
    }
    else
    {
      newArray = new byte[arrayOne.length + bytes];
      System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
      System.arraycopy(arrayTwo, 0, newArray, arrayOne.length, bytes);
      arrayOne = null;
      arrayTwo = null;
    }
    return newArray;
  }
  
  private static byte[] appendByteArrays(byte[] arrayOne, byte[] arrayTwo)
  {
    if ((arrayOne == null) && (arrayTwo == null)) {
      return null;
    }
    byte[] newArray;
    if (arrayOne == null)
    {
      byte[] newArray = new byte[arrayTwo.length];
      System.arraycopy(arrayTwo, 0, newArray, 0, arrayTwo.length);
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
      newArray = new byte[arrayOne.length + arrayTwo.length];
      System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
      System.arraycopy(arrayTwo, 0, newArray, arrayOne.length, arrayTwo.length);
      arrayOne = null;
      arrayTwo = null;
    }
    return newArray;
  }
  
  private void errorMessage(String message)
  {
    this.logger.errorMessage("CodecJOrbis", message, 0);
  }
  
  private void printStackTrace(Exception local_e)
  {
    this.logger.printStackTrace(local_e, 1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.codecs.CodecJOrbis
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */