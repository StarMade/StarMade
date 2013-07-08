package de.jarnbjo.ogg;

import java.io.IOException;

public abstract interface LogicalOggStream
{
  public static final String FORMAT_UNKNOWN = "application/octet-stream";
  public static final String FORMAT_VORBIS = "audio/x-vorbis";
  public static final String FORMAT_FLAC = "audio/x-flac";
  public static final String FORMAT_THEORA = "video/x-theora";
  
  public abstract OggPage getNextOggPage()
    throws OggFormatException, IOException;
  
  public abstract byte[] getNextOggPacket()
    throws OggFormatException, IOException;
  
  public abstract boolean isOpen();
  
  public abstract void close()
    throws IOException;
  
  public abstract void reset()
    throws OggFormatException, IOException;
  
  public abstract long getMaximumGranulePosition();
  
  public abstract void setTime(long paramLong)
    throws IOException;
  
  public abstract long getTime();
  
  public abstract String getFormat();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.ogg.LogicalOggStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */