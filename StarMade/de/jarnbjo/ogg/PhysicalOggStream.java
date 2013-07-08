package de.jarnbjo.ogg;

import java.io.IOException;
import java.util.Collection;

public abstract interface PhysicalOggStream
{
  public abstract Collection getLogicalStreams();
  
  public abstract OggPage getOggPage(int paramInt)
    throws OggFormatException, IOException;
  
  public abstract boolean isOpen();
  
  public abstract void close()
    throws IOException;
  
  public abstract void setTime(long paramLong)
    throws OggFormatException, IOException;
  
  public abstract boolean isSeekable();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.ogg.PhysicalOggStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */