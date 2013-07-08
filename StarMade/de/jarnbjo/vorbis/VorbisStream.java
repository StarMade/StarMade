package de.jarnbjo.vorbis;

import de.jarnbjo.ogg.LogicalOggStream;
import de.jarnbjo.util.io.BitInputStream;
import de.jarnbjo.util.io.ByteArrayBitInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class VorbisStream
{
  private LogicalOggStream oggStream;
  private IdentificationHeader identificationHeader;
  private CommentHeader commentHeader;
  private SetupHeader setupHeader;
  private AudioPacket lastAudioPacket;
  private AudioPacket nextAudioPacket;
  private LinkedList audioPackets = new LinkedList();
  private byte[] currentPcm;
  private int currentPcmIndex;
  private int currentPcmLimit;
  private static final int IDENTIFICATION_HEADER = 1;
  private static final int COMMENT_HEADER = 3;
  private static final int SETUP_HEADER = 5;
  private int bitIndex = 0;
  private byte lastByte = 0;
  private boolean initialized = false;
  private Object streamLock = new Object();
  private int pageCounter = 0;
  private int currentBitRate = 0;
  private long currentGranulePosition;
  public static final int BIG_ENDIAN = 0;
  public static final int LITTLE_ENDIAN = 1;
  
  public VorbisStream() {}
  
  public VorbisStream(LogicalOggStream oggStream)
    throws VorbisFormatException, IOException
  {
    this.oggStream = oggStream;
    for (int local_i = 0; local_i < 3; local_i++)
    {
      BitInputStream source = new ByteArrayBitInputStream(oggStream.getNextOggPacket());
      int headerType = source.getInt(8);
      switch (headerType)
      {
      case 1: 
        this.identificationHeader = new IdentificationHeader(source);
        break;
      case 3: 
        this.commentHeader = new CommentHeader(source);
        break;
      case 5: 
        this.setupHeader = new SetupHeader(this, source);
      }
    }
    if (this.identificationHeader == null) {
      throw new VorbisFormatException("The file has no identification header.");
    }
    if (this.commentHeader == null) {
      throw new VorbisFormatException("The file has no commentHeader.");
    }
    if (this.setupHeader == null) {
      throw new VorbisFormatException("The file has no setup header.");
    }
    this.currentPcm = new byte[this.identificationHeader.getChannels() * this.identificationHeader.getBlockSize1() * 2];
  }
  
  public IdentificationHeader getIdentificationHeader()
  {
    return this.identificationHeader;
  }
  
  public CommentHeader getCommentHeader()
  {
    return this.commentHeader;
  }
  
  protected SetupHeader getSetupHeader()
  {
    return this.setupHeader;
  }
  
  public boolean isOpen()
  {
    return this.oggStream.isOpen();
  }
  
  public void close()
    throws IOException
  {
    this.oggStream.close();
  }
  
  public int readPcm(byte[] buffer, int offset, int length)
    throws IOException
  {
    synchronized (this.streamLock)
    {
      int channels = this.identificationHeader.getChannels();
      if (this.lastAudioPacket == null) {
        this.lastAudioPacket = getNextAudioPacket();
      }
      if ((this.currentPcm == null) || (this.currentPcmIndex >= this.currentPcmLimit))
      {
        AudioPacket local_ap = getNextAudioPacket();
        try
        {
          local_ap.getPcm(this.lastAudioPacket, this.currentPcm);
          this.currentPcmLimit = (local_ap.getNumberOfSamples() * this.identificationHeader.getChannels() * 2);
        }
        catch (ArrayIndexOutOfBoundsException local_e)
        {
          return 0;
        }
        this.currentPcmIndex = 0;
        this.lastAudioPacket = local_ap;
      }
      int local_ap = 0;
      int local_e = 0;
      int arrIx = 0;
      for (local_e = this.currentPcmIndex; (local_e < this.currentPcmLimit) && (arrIx < length); local_e++)
      {
        buffer[(offset + arrIx++)] = this.currentPcm[local_e];
        local_ap++;
      }
      this.currentPcmIndex = local_e;
      return local_ap;
    }
  }
  
  private AudioPacket getNextAudioPacket()
    throws VorbisFormatException, IOException
  {
    this.pageCounter += 1;
    byte[] data = this.oggStream.getNextOggPacket();
    AudioPacket res = null;
    while (res == null) {
      try
      {
        res = new AudioPacket(this, new ByteArrayBitInputStream(data));
      }
      catch (ArrayIndexOutOfBoundsException local_e) {}
    }
    this.currentGranulePosition += res.getNumberOfSamples();
    this.currentBitRate = (data.length * 8 * this.identificationHeader.getSampleRate() / res.getNumberOfSamples());
    return res;
  }
  
  public long getCurrentGranulePosition()
  {
    return this.currentGranulePosition;
  }
  
  public int getCurrentBitRate()
  {
    return this.currentBitRate;
  }
  
  public byte[] processPacket(byte[] packet)
    throws VorbisFormatException, IOException
  {
    if (packet.length == 0) {
      throw new VorbisFormatException("Cannot decode a vorbis packet with length = 0");
    }
    if ((packet[0] & 0x1) == 1)
    {
      BitInputStream source = new ByteArrayBitInputStream(packet);
      switch (source.getInt(8))
      {
      case 1: 
        this.identificationHeader = new IdentificationHeader(source);
        break;
      case 3: 
        this.commentHeader = new CommentHeader(source);
        break;
      case 5: 
        this.setupHeader = new SetupHeader(this, source);
      }
      return null;
    }
    if ((this.identificationHeader == null) || (this.commentHeader == null) || (this.setupHeader == null)) {
      throw new VorbisFormatException("Cannot decode audio packet before all three header packets have been decoded.");
    }
    AudioPacket source = new AudioPacket(this, new ByteArrayBitInputStream(packet));
    this.currentGranulePosition += source.getNumberOfSamples();
    if (this.lastAudioPacket == null)
    {
      this.lastAudioPacket = source;
      return null;
    }
    byte[] res = new byte[this.identificationHeader.getChannels() * source.getNumberOfSamples() * 2];
    try
    {
      source.getPcm(this.lastAudioPacket, res);
    }
    catch (IndexOutOfBoundsException local_e)
    {
      Arrays.fill(res, (byte)0);
    }
    this.lastAudioPacket = source;
    return res;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.vorbis.VorbisStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */