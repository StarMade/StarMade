package org.newdawn.slick.openal;

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
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.lwjgl.BufferUtils;
import org.newdawn.slick.util.Log;

public class OggInputStream
  extends InputStream
  implements AudioInputStream
{
  private int convsize = 16384;
  private byte[] convbuffer = new byte[this.convsize];
  private InputStream input;
  private Info oggInfo = new Info();
  private boolean endOfStream;
  private SyncState syncState = new SyncState();
  private StreamState streamState = new StreamState();
  private Page page = new Page();
  private Packet packet = new Packet();
  private Comment comment = new Comment();
  private DspState dspState = new DspState();
  private Block vorbisBlock = new Block(this.dspState);
  byte[] buffer;
  int bytes = 0;
  boolean bigEndian = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
  boolean endOfBitStream = true;
  boolean inited = false;
  private int readIndex;
  private ByteBuffer pcmBuffer = BufferUtils.createByteBuffer(2048000);
  private int total;
  
  public OggInputStream(InputStream input)
    throws IOException
  {
    this.input = input;
    this.total = input.available();
    init();
  }
  
  public int getLength()
  {
    return this.total;
  }
  
  public int getChannels()
  {
    return this.oggInfo.channels;
  }
  
  public int getRate()
  {
    return this.oggInfo.rate;
  }
  
  private void init()
    throws IOException
  {
    initVorbis();
    readPCM();
  }
  
  public int available()
  {
    return this.endOfStream ? 0 : 1;
  }
  
  private void initVorbis()
  {
    this.syncState.init();
  }
  
  private boolean getPageAndPacket()
  {
    int index = this.syncState.buffer(4096);
    this.buffer = this.syncState.data;
    if (this.buffer == null)
    {
      this.endOfStream = true;
      return false;
    }
    try
    {
      this.bytes = this.input.read(this.buffer, index, 4096);
    }
    catch (Exception local_e)
    {
      Log.error("Failure reading in vorbis");
      Log.error(local_e);
      this.endOfStream = true;
      return false;
    }
    this.syncState.wrote(this.bytes);
    if (this.syncState.pageout(this.page) != 1)
    {
      if (this.bytes < 4096) {
        return false;
      }
      Log.error("Input does not appear to be an Ogg bitstream.");
      this.endOfStream = true;
      return false;
    }
    this.streamState.init(this.page.serialno());
    this.oggInfo.init();
    this.comment.init();
    if (this.streamState.pagein(this.page) < 0)
    {
      Log.error("Error reading first page of Ogg bitstream data.");
      this.endOfStream = true;
      return false;
    }
    if (this.streamState.packetout(this.packet) != 1)
    {
      Log.error("Error reading initial header packet.");
      this.endOfStream = true;
      return false;
    }
    if (this.oggInfo.synthesis_headerin(this.comment, this.packet) < 0)
    {
      Log.error("This Ogg bitstream does not contain Vorbis audio data.");
      this.endOfStream = true;
      return false;
    }
    int local_e = 0;
    while (local_e < 2)
    {
      while (local_e < 2)
      {
        int result = this.syncState.pageout(this.page);
        if (result == 0) {
          break;
        }
        if (result == 1)
        {
          this.streamState.pagein(this.page);
          while (local_e < 2)
          {
            result = this.streamState.packetout(this.packet);
            if (result == 0) {
              break;
            }
            if (result == -1)
            {
              Log.error("Corrupt secondary header.  Exiting.");
              this.endOfStream = true;
              return false;
            }
            this.oggInfo.synthesis_headerin(this.comment, this.packet);
            local_e++;
          }
        }
      }
      index = this.syncState.buffer(4096);
      this.buffer = this.syncState.data;
      try
      {
        this.bytes = this.input.read(this.buffer, index, 4096);
      }
      catch (Exception result)
      {
        Log.error("Failed to read Vorbis: ");
        Log.error(result);
        this.endOfStream = true;
        return false;
      }
      if ((this.bytes == 0) && (local_e < 2))
      {
        Log.error("End of file before finding all Vorbis headers!");
        this.endOfStream = true;
        return false;
      }
      this.syncState.wrote(this.bytes);
    }
    this.convsize = (4096 / this.oggInfo.channels);
    this.dspState.synthesis_init(this.oggInfo);
    this.vorbisBlock.init(this.dspState);
    return true;
  }
  
  private void readPCM()
    throws IOException
  {
    boolean wrote = false;
    for (;;)
    {
      if (this.endOfBitStream)
      {
        if (!getPageAndPacket()) {
          break;
        }
        this.endOfBitStream = false;
      }
      if (!this.inited)
      {
        this.inited = true;
        return;
      }
      float[][][] _pcm = new float[1][][];
      int[] _index = new int[this.oggInfo.channels];
      while (!this.endOfBitStream)
      {
        while (!this.endOfBitStream)
        {
          int result = this.syncState.pageout(this.page);
          if (result == 0) {
            break;
          }
          if (result == -1)
          {
            Log.error("Corrupt or missing data in bitstream; continuing...");
          }
          else
          {
            this.streamState.pagein(this.page);
            for (;;)
            {
              result = this.streamState.packetout(this.packet);
              if (result == 0) {
                break;
              }
              if (result != -1)
              {
                if (this.vorbisBlock.synthesis(this.packet) == 0) {
                  this.dspState.synthesis_blockin(this.vorbisBlock);
                }
                int samples;
                while ((samples = this.dspState.synthesis_pcmout(_pcm, _index)) > 0)
                {
                  float[][] pcm = _pcm[0];
                  int bout = samples < this.convsize ? samples : this.convsize;
                  for (int local_i = 0; local_i < this.oggInfo.channels; local_i++)
                  {
                    int ptr = local_i * 2;
                    int mono = _index[local_i];
                    for (int local_j = 0; local_j < bout; local_j++)
                    {
                      int val = (int)(pcm[local_i][(mono + local_j)] * 32767.0D);
                      if (val > 32767) {
                        val = 32767;
                      }
                      if (val < -32768) {
                        val = -32768;
                      }
                      if (val < 0) {
                        val |= 32768;
                      }
                      if (this.bigEndian)
                      {
                        this.convbuffer[ptr] = ((byte)(val >>> 8));
                        this.convbuffer[(ptr + 1)] = ((byte)val);
                      }
                      else
                      {
                        this.convbuffer[ptr] = ((byte)val);
                        this.convbuffer[(ptr + 1)] = ((byte)(val >>> 8));
                      }
                      ptr += 2 * this.oggInfo.channels;
                    }
                  }
                  int local_i = 2 * this.oggInfo.channels * bout;
                  if (local_i >= this.pcmBuffer.remaining()) {
                    Log.warn("Read block from OGG that was too big to be buffered: " + local_i);
                  } else {
                    this.pcmBuffer.put(this.convbuffer, 0, local_i);
                  }
                  wrote = true;
                  this.dspState.synthesis_read(bout);
                }
              }
            }
            if (this.page.eos() != 0) {
              this.endOfBitStream = true;
            }
            if ((!this.endOfBitStream) && (wrote)) {
              return;
            }
          }
        }
        if (!this.endOfBitStream)
        {
          this.bytes = 0;
          int result = this.syncState.buffer(4096);
          if (result >= 0)
          {
            this.buffer = this.syncState.data;
            try
            {
              this.bytes = this.input.read(this.buffer, result, 4096);
            }
            catch (Exception samples)
            {
              Log.error("Failure during vorbis decoding");
              Log.error(samples);
              this.endOfStream = true;
              return;
            }
          }
          else
          {
            this.bytes = 0;
          }
          this.syncState.wrote(this.bytes);
          if (this.bytes == 0) {
            this.endOfBitStream = true;
          }
        }
      }
      this.streamState.clear();
      this.vorbisBlock.clear();
      this.dspState.clear();
      this.oggInfo.clear();
    }
    this.syncState.clear();
    this.endOfStream = true;
  }
  
  public int read()
    throws IOException
  {
    if (this.readIndex >= this.pcmBuffer.position())
    {
      this.pcmBuffer.clear();
      readPCM();
      this.readIndex = 0;
    }
    if (this.readIndex >= this.pcmBuffer.position()) {
      return -1;
    }
    int value = this.pcmBuffer.get(this.readIndex);
    if (value < 0) {
      value = 256 + value;
    }
    this.readIndex += 1;
    return value;
  }
  
  public boolean atEnd()
  {
    return (this.endOfStream) && (this.readIndex >= this.pcmBuffer.position());
  }
  
  public int read(byte[] local_b, int off, int len)
    throws IOException
  {
    for (int local_i = 0; local_i < len; local_i++) {
      try
      {
        int value = read();
        if (value >= 0)
        {
          local_b[local_i] = ((byte)value);
        }
        else
        {
          if (local_i == 0) {
            return -1;
          }
          return local_i;
        }
      }
      catch (IOException value)
      {
        Log.error(value);
        return local_i;
      }
    }
    return len;
  }
  
  public int read(byte[] local_b)
    throws IOException
  {
    return read(local_b, 0, local_b.length);
  }
  
  public void close()
    throws IOException
  {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.openal.OggInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */