package de.jarnbjo.vorbis;

import de.jarnbjo.util.io.BitInputStream;
import java.io.IOException;

public class IdentificationHeader
{
  private int version;
  private int channels;
  private int sampleRate;
  private int bitrateMaximum;
  private int bitrateNominal;
  private int bitrateMinimum;
  private int blockSize0;
  private int blockSize1;
  private boolean framingFlag;
  private MdctFloat[] mdct = new MdctFloat[2];
  private static final long HEADER = 126896460427126L;
  
  public IdentificationHeader(BitInputStream source)
    throws VorbisFormatException, IOException
  {
    long leading = source.getLong(48);
    if (leading != 126896460427126L) {
      throw new VorbisFormatException("The identification header has an illegal leading.");
    }
    this.version = source.getInt(32);
    this.channels = source.getInt(8);
    this.sampleRate = source.getInt(32);
    this.bitrateMaximum = source.getInt(32);
    this.bitrateNominal = source.getInt(32);
    this.bitrateMinimum = source.getInt(32);
    int local_bs = source.getInt(8);
    this.blockSize0 = (1 << (local_bs & 0xF));
    this.blockSize1 = (1 << (local_bs >> 4));
    this.mdct[0] = new MdctFloat(this.blockSize0);
    this.mdct[1] = new MdctFloat(this.blockSize1);
    this.framingFlag = (source.getInt(8) != 0);
  }
  
  public int getSampleRate()
  {
    return this.sampleRate;
  }
  
  public int getMaximumBitrate()
  {
    return this.bitrateMaximum;
  }
  
  public int getNominalBitrate()
  {
    return this.bitrateNominal;
  }
  
  public int getMinimumBitrate()
  {
    return this.bitrateMinimum;
  }
  
  public int getChannels()
  {
    return this.channels;
  }
  
  public int getBlockSize0()
  {
    return this.blockSize0;
  }
  
  public int getBlockSize1()
  {
    return this.blockSize1;
  }
  
  protected MdctFloat getMdct0()
  {
    return this.mdct[0];
  }
  
  protected MdctFloat getMdct1()
  {
    return this.mdct[1];
  }
  
  public int getVersion()
  {
    return this.version;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.vorbis.IdentificationHeader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */